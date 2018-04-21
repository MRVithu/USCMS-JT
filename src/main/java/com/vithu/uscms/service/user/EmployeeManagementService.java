package com.vithu.uscms.service.user;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.vithu.uscms.entities.Employee;
import com.vithu.uscms.entities.Region;
import com.vithu.uscms.entities.User;
import com.vithu.uscms.entities.UserRole;
import com.vithu.uscms.others.DBConnection;
import com.vithu.uscms.others.GenericResult;
import com.vithu.uscms.others.JsonFormer;
import com.vithu.uscms.others.MessageConstant;
import com.vithu.uscms.session.TokenManager;

/**
 * @author M.Vithusanth
 * @CreatedOn 20th April 2018
 * @Purpose Services for Add/Edit/Delete/View Single/View All Employees
 */

@Service
public class EmployeeManagementService {

	private DBConnection conn = new DBConnection();
	private Connection newConn;
	private ResultSet rs;

	// ADD EMPLOYEE METHOD
	public GenericResult addUser(Employee newEmployee) {
		PreparedStatement addUserStmt = null;
		PreparedStatement addEmployeeStmt = null;
		PreparedStatement getAllAuthoritiesForRole = null;
		PreparedStatement addEmployeeAuthority = null;
		TokenManager tm = new TokenManager();
		try {
			int last_inserted_id = 0;

			newConn = conn.getCon();

			// Add user crediantials
			addUserStmt = newConn.prepareStatement(
					"INSERT INTO users( name, mobile, is_deleted, password, email, user_name, access_token) VALUES (?,?,?,?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			addUserStmt.setString(1, newEmployee.getUser().getName());
			addUserStmt.setString(2, newEmployee.getUser().getMobile());
			addUserStmt.setBoolean(3, false);
			addUserStmt.setString(4, newEmployee.getUser().getPassword());
			addUserStmt.setString(5, newEmployee.getUser().getEmail());
			addUserStmt.setString(6, newEmployee.getUser().getUserName());
			addUserStmt.setString(7,  tm.getToken());
			addUserStmt.executeUpdate();

			// get the previous IDs
			rs = addUserStmt.getGeneratedKeys();
			if (rs.next()) {
				last_inserted_id = rs.getInt(1);
			}

			// Add employee with both user and employee Credentials
			addEmployeeStmt = newConn.prepareStatement(
					"INSERT INTO employees( nic, contact, added_by, region_id, address, dob, role_id, user_id ) VALUES (?,?,?,?,?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			addEmployeeStmt.setString(1, newEmployee.getNic());
			addEmployeeStmt.setString(2, newEmployee.getContact());
			addEmployeeStmt.setInt(3, newEmployee.getAddedBy());
			addEmployeeStmt.setInt(4, newEmployee.getRegionId());
			addEmployeeStmt.setString(5, newEmployee.getAddress());
			addEmployeeStmt.setDate(6, java.sql.Date.valueOf(newEmployee.getDob()));
			addEmployeeStmt.setInt(7, newEmployee.getroleId());
			addEmployeeStmt.setInt(8, last_inserted_id);
			addEmployeeStmt.executeUpdate();

			// get the previous ID
			rs = addEmployeeStmt.getGeneratedKeys();
			if (rs.next()) {
				last_inserted_id = rs.getInt(1);
			}

			// Get authorities & Save employee_id with authority emp_authority table
			getAllAuthoritiesForRole = newConn.prepareStatement("SELECT NAME FROM authority a\r\n"
					+ "LEFT JOIN role_authority r ON r.`authority_id`= a.id\r\n" + "WHERE role_id =?;");
			getAllAuthoritiesForRole.setInt(1, newEmployee.getroleId());
			rs = getAllAuthoritiesForRole.executeQuery();

			while (rs.next()) {
				addEmployeeAuthority = newConn
						.prepareStatement("INSERT INTO emp_authority(emp_id, authority ) VALUES(?,?);");
				addEmployeeAuthority.setInt(1, last_inserted_id);
				addEmployeeAuthority.setString(2, rs.getString("name"));
				addEmployeeAuthority.executeUpdate();
			}
			
			
			// save activity

			/**
			 * query table addQueryStmt = newConn.prepareStatement("INSERT INTO queries(
			 * query_ran_by, query_sql ) VALUES (?,?);"); addQueryStmt.setInt(1,
			 * newEmployee.getId()); addQueryStmt.setString(2, sql);
			 * addQueryStmt.executeUpdate();
			 */

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "New Employee Added Successfully");

		} catch (Exception e) {
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		} finally {
			try {
				getAllAuthoritiesForRole.close();
				addEmployeeStmt.close();
				addUserStmt.close();
				addEmployeeAuthority.close();
				conn.closeCon();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// GET EMPLOYEES METHOD
	public GenericResult getAllUser() {
		PreparedStatement getEmployeeStmt = null;
		try {

			newConn = conn.getCon();
			getEmployeeStmt = newConn.prepareStatement(
					"SELECT e.id AS eid, u.mobile, u.user_name, e.region_id, e.*, u.name, u.email, u.id AS uid, r.name AS region, w.name AS role, u.password AS empPassword, e.role_id \r\n"
							+ "FROM `employees` e LEFT JOIN `users` u ON e.user_id = u.id \r\n"
							+ "LEFT JOIN `roles` w ON e.user_id = w.id\r\n"
							+ "LEFT JOIN `regions` r ON r.id = region_id WHERE u.is_deleted = 'false';");

			rs = getEmployeeStmt.executeQuery();
			List<Employee> employeeList = new ArrayList<Employee>();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("uid"));
				user.setName(rs.getString("name"));
				user.setMobile(rs.getString("mobile"));
				user.setEmail(rs.getString("email"));
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("empPassword"));

				Employee employee = new Employee();
				employee.setUser(user);
				employee.setId(rs.getInt("eid"));
				employee.setRole(rs.getString("role"));
				employee.setDob(rs.getDate("dob").toString());
				employee.setNic(rs.getString("nic"));
				employee.setContact(rs.getString("contact"));
				employee.setAddress(rs.getString("address"));
				employee.setRegionId(rs.getInt("region_id"));
				employee.setRoleId(rs.getInt("role_id"));
				employee.setRegion(rs.getString("region"));
				employeeList.add(employee);
			}

			// save activity

			GenericResult rs = new GenericResult();
			rs.setStatus(true);
			rs.setResult(employeeList);
			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "Retrieved Successfully", employeeList,
					JsonFormer.form(rs), " ");
		} catch (Exception e) {
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		} finally {
			try {
				getEmployeeStmt.close();
				conn.closeCon();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// DELETE EMPLOYEE METHOD
	public GenericResult deleteUser(int user_id) {

		PreparedStatement deleteStmt = null;
		try {
			newConn = conn.getCon();
			deleteStmt = newConn.prepareStatement("UPDATE users SET is_deleted = TRUE WHERE id = ?;");
			deleteStmt.setInt(1, user_id);

			deleteStmt.executeUpdate();

			// save activity

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "Deleted Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		} finally {
			try {
				deleteStmt.close();
				conn.closeCon();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// VIEW SINGLE VIEW EMPLOYEE METHOD
	public GenericResult getSingleUser(int eid) {
		PreparedStatement getSingleViewStmt = null;
		try {
			newConn = conn.getCon();
			getSingleViewStmt = newConn.prepareStatement(
					"SELECT e.*, u.* FROM `employees` e LEFT JOIN `users` u ON e.user_id = u.id WHERE u.is_deleted = 'false' AND u.id = ?;");
			getSingleViewStmt.setInt(1, eid);
			rs = getSingleViewStmt.executeQuery();
			Employee employee = new Employee();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setName(rs.getString("name"));
				user.setMobile(rs.getString("mobile"));

				employee.setUser(user);
				employee.setNic(rs.getString("nic"));
				employee.setContact(rs.getString("contact"));
				employee.setAddress(rs.getString("address"));
				employee.setDob(rs.getDate("dob").toString());
				employee.setRegionId(rs.getInt("region_id"));

			}
			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "Retrieved Successfully", employee);
		} catch (Exception e) {
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		} finally {
			try {
				getSingleViewStmt.close();
				conn.closeCon();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// UPDATE EMPLOYEE METHOD
	public GenericResult updateUser(Employee employee) {

		PreparedStatement updateUserStmt = null;
		PreparedStatement updateEmployeeStmt = null;
		// PreparedStatement addQueryStmt = null;;

		try {
			newConn = conn.getCon();

			// Add user crediantials
			updateUserStmt = newConn.prepareStatement(
					"UPDATE users SET user_name =? , name=? , mobile=?, is_deleted=false, password=?, email=? WHERE id = ?;");
			updateUserStmt.setString(1, employee.getUser().getUserName());
			updateUserStmt.setString(2, employee.getUser().getName());
			updateUserStmt.setString(3, employee.getUser().getMobile());
			updateUserStmt.setString(5, employee.getUser().getPassword());
			updateUserStmt.setString(6, employee.getUser().getEmail());
			updateUserStmt.setInt(7, employee.getUser().getId());
			updateUserStmt.executeUpdate();

			// Add employee with both user and employee Credentials
			updateEmployeeStmt = newConn.prepareStatement(
					"UPDATE employees SET nic =?, contact=?, added_by=?, region_id=?, address=?, dob=?, role_id=? WHERE user_id=?;",
					Statement.RETURN_GENERATED_KEYS);
			updateEmployeeStmt.setString(1, employee.getNic());
			updateEmployeeStmt.setString(2, employee.getContact());
			updateEmployeeStmt.setInt(3, employee.getAddedBy());
			updateEmployeeStmt.setInt(4, employee.getRegionId());
			updateEmployeeStmt.setString(5, employee.getAddress());
			updateEmployeeStmt.setDate(6, java.sql.Date.valueOf(employee.getDob()));
			updateEmployeeStmt.setInt(7, employee.getroleId());
			updateEmployeeStmt.setInt(8, employee.getId());
			updateEmployeeStmt.executeUpdate();

			// save activity

			/**
			 * query table addQueryStmt = newConn.prepareStatement("INSERT INTO queries(
			 * query_ran_by, query_sql ) VALUES (?,?);"); addQueryStmt.setInt(1,
			 * newEmployee.getId()); addQueryStmt.setString(2, sql);
			 * addQueryStmt.executeUpdate();
			 */

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "Employee updated Successfully");

		} catch (Exception e) {
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		} finally {
			try {
				updateEmployeeStmt.close();
				updateUserStmt.close();
				conn.closeCon();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// GET ALL REGION
	public GenericResult getAllRegion() {
		PreparedStatement getRegionStmt = null;
		try {
			newConn = conn.getCon();
			getRegionStmt = newConn.prepareStatement("Select id, name from regions;");
			rs = getRegionStmt.executeQuery();
			List<Region> regionlist = new ArrayList<Region>();
			while (rs.next()) {
				Region region = new Region();
				region.setId(rs.getInt("id"));
				region.setName(rs.getString("name"));
				regionlist.add(region);
			}

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "Retrieved Successfully", regionlist);
		} catch (Exception e) {
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		} finally {
			try {
				getRegionStmt.close();
				conn.closeCon();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// GET ALL ROLE
	public GenericResult getAllRole() {
		PreparedStatement getRoleStmt = null;
		try {
			newConn = conn.getCon();
			getRoleStmt = newConn.prepareStatement("Select id, name from roles;");
			rs = getRoleStmt.executeQuery();
			List<UserRole> roleList = new ArrayList<UserRole>();
			while (rs.next()) {
				UserRole role = new UserRole();
				role.setId(rs.getInt("id"));
				role.setName(rs.getString("name"));
				roleList.add(role);
			}

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "Retrieved Successfully", roleList);
		} catch (Exception e) {
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		} finally {
			try {
				getRoleStmt.close();
				conn.closeCon();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// GET EMPLOYEE BY ROLE
	public GenericResult getEmployeeForRole(int id) {
		PreparedStatement getEmployeeForRoleStmt = null;
		try {
			newConn = conn.getCon();
			getEmployeeForRoleStmt = newConn.prepareStatement(
					"SELECT u.id, u.name FROM employees e LEFT JOIN users u ON e.user_id = u.id LEFT JOIN roles r ON e.role_id = r.id WHERE e.role_id = ?;");
			getEmployeeForRoleStmt.setInt(1, id);
			rs = getEmployeeForRoleStmt.executeQuery();

			List<Employee> employeeList = new ArrayList<Employee>();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				Employee employee = new Employee();
				employee.setUser(user);
				employeeList.add(employee);
			}

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "Retrieved Successfully", employeeList);
		} catch (Exception e) {
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		} finally {
			try {
				getEmployeeForRoleStmt.close();
				conn.closeCon();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
