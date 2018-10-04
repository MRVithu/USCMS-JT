package com.vithu.uscms.service.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.vithu.uscms.entities.Employee;
import com.vithu.uscms.entities.User;
import com.vithu.uscms.others.DBConnection;
import com.vithu.uscms.others.DataEncryption;
import com.vithu.uscms.others.Encrypter;
import com.vithu.uscms.others.GenericResult;
import com.vithu.uscms.others.JsonFormer;
import com.vithu.uscms.others.MessageConstant;
import com.vithu.uscms.session.CurrentUser;
import com.vithu.uscms.session.TokenManager;

/**
 * @author M.Vithusanth
 * @CreatedOn 202th April 2018
 * @Purpose Service for users to login
 */

@Service
public class UserLoginService {

	private DBConnection conn = new DBConnection();
	private Connection newConn;
	private ResultSet rs;
	User user = new User();
	CurrentUser currUser = new CurrentUser();

	// LOGIN METHOD
	public GenericResult dologin(User logger) {
		user = new User();
		currUser = new CurrentUser();
		PreparedStatement searchStmt = null;
		PreparedStatement updateTokenStmt = null;
		PreparedStatement loginStmt = null;
		PreparedStatement getAuthoityTokenStmt = null;

		DataEncryption deService = new DataEncryption();

		try {
			int total = 0;
			newConn = conn.getCon();
			// Check logger
			searchStmt = newConn.prepareStatement(
					"SELECT COUNT(*) AS total , u.password, u.id AS uid, e.id AS eid, u.name AS NAME,u.added_on , u.mobile, u.email, u.user_name, e.nic, e.contact,  e.address, e.dob, o.name AS role\r\n"
							+ "FROM users u\r\n" + "LEFT JOIN employees e ON u.id = e.user_id\r\n"
							+ "LEFT JOIN customers c ON  u.id = c.user_id\r\n"
							+ "LEFT JOIN roles o ON e.role_id = o.id\r\n"
							+ "WHERE u.user_name = ? AND u.is_deleted = ?;");
			
			searchStmt.setString(1, logger.getUserName());
			// searchStmt.setString(2, logger.getPassword());
			searchStmt.setBoolean(2, false);
			rs = searchStmt.executeQuery();

			while (rs.next()) {

				if (rs.getString("password") != null) {
					System.out.println(logger.getPassword() + "-------------------------");
					System.out.println( rs.getString("password"));
					boolean matchedPassword = deService.checkDataMatch(logger.getPassword(), rs.getString("password"));

					if (matchedPassword) {
						// Get the user for logger name
						total = rs.getInt("total");
						user.setId(rs.getInt("uid"));
						user.setEmail(rs.getString("email"));
						user.setMobile(rs.getString("mobile"));
						user.setName(rs.getString("name"));
						user.setUserName(rs.getString("user_name"));
						user.setAddedOn(rs.getString("added_on"));

						Employee employee = new Employee();
						employee.setId(rs.getInt("eid"));
						employee.setAddress(rs.getString("address"));
						employee.setContact(rs.getString("contact"));
						System.out.println(rs.getString("user_name"));
						if( rs.getDate("dob") != null) {
							employee.setDob(rs.getDate("dob").toString());
						}
						else {
							employee.setDob("");
						}					
						employee.setNic(rs.getString("nic"));
						employee.setRole(rs.getString("role"));
						employee.setUser(user);
						currUser.setEmployee(employee);
					} else {
						System.out.println("WrongPassword");
						currUser = null;
					}
				} else {
					System.out.println("no User");
					currUser = null;
				}
			}

			if (total == 1 && currUser != null) {
				// update token
				String token = TokenManager.getToken();
				updateTokenStmt = newConn.prepareStatement(
						"UPDATE users SET access_token = ?, token_exp_date = CURDATE() WHERE id = ? AND token_exp_date < CURDATE();");
				updateTokenStmt.setString(1, token);
				updateTokenStmt.setInt(2, user.getId());
				updateTokenStmt.executeUpdate();

				// Insert logger to login table
				loginStmt = newConn.prepareStatement("INSERT INTO logins(user_id) VALUES (?);");
				loginStmt.setInt(1, user.getId());
				loginStmt.executeUpdate();

				// get authorities and populate into authority map
				getAuthoityTokenStmt = newConn.prepareStatement("SELECT authority FROM emp_authority WHERE emp_id = ?");
				getAuthoityTokenStmt.setInt(1, user.getId());
				rs = getAuthoityTokenStmt.executeQuery();

				Map<String, Boolean> authorityMap = new HashMap<String, Boolean>();

				while (rs.next()) {
					authorityMap.put(rs.getString("authority"), true);
				}
				currUser.setAuthorityMap(authorityMap);
				currUser.setToken(token);
				// ASSIGN CURRENT USER'S ENCRYPTED VALUE TO TOKEN
				currUser.setToken(Encrypter.encrypt(JsonFormer.form(currUser)));
				// System.out.println(currUser.getToken());
				// System.out.println("decrypted----:"+Encrypter.decrypt( currUser.getToken()));
				// System.out.println("currentuser----:"+JsonFormer.deForm(Encrypter.decrypt(currUser.getToken())));
				return new GenericResult(true, MessageConstant.MSG_SUCCESS, "Succesfully logged in.", currUser);
			} else {
				return new GenericResult(false, MessageConstant.MSG_FAILED, "Wrong Username & Password.");
			}

		} catch (Exception e1) {
			e1.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, "Oops...Something went wrong!");
		} finally {
			try {
				if (searchStmt != null)
					searchStmt.close();

				if (updateTokenStmt != null)
					updateTokenStmt.close();
				conn.closeCon();
			} catch (Exception e) {
				e.printStackTrace();
				return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
			}
		}
	}

	// LOGOUT METHOD
	public GenericResult dologout(int user_id) {
		PreparedStatement logoutStmt = null;
		try {
			newConn = conn.getCon();
			logoutStmt = newConn.prepareStatement("UPDATE logins SET logged_out_on = CURDATE() WHERE user_id = ? ;");
			logoutStmt.setInt(1, user_id);
			logoutStmt.executeUpdate();

			// Empty current User
			currUser.setAuthorityMap(null);
			currUser.setEmployee(null);
			currUser.setToken(null);
			currUser.setTokenExpiryDate(null);

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "Succesfully logged out.", currUser);

		} catch (SQLException e) {
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, "Error in logged out." + e.toString());
		} finally {
			try {
				logoutStmt.close();
				conn.closeCon();
			} catch (SQLException e2) {
				return new GenericResult(false, MessageConstant.MSG_FAILED, e2.getMessage());
			}
		}

	}

}