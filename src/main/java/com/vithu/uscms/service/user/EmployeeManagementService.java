package com.vithu.uscms.service.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.vithu.uscms.entities.Employee;
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
					"INSERT INTO `users`( `name`, `mobile`, `password`, `email`, `user_name`, `access_token`) VALUES ('"
							+ newEmployee.getUser().getName() + "' ,'" + newEmployee.getUser().getMobile() + "', '"
							+ newEmployee.getUser().getPassword() + "', '" + newEmployee.getUser().getEmail() + "','"
							+ newEmployee.getUser().getUserName() + "', '" + tm.getToken() + "');",
					Statement.RETURN_GENERATED_KEYS);
			addUserStmt.executeUpdate();

			// get the previous IDs
			rs = addUserStmt.getGeneratedKeys();
			if (rs.next()) {
				last_inserted_id = rs.getInt(1);
			}

			// Add employee with both user and employee Credentials
			addEmployeeStmt = newConn.prepareStatement(
					"INSERT INTO employees( `nic`, `contact`, `added_by`, `address`, `dob`, `role_id`, `user_id` ) VALUES ('"
							+ newEmployee.getNic() + "', '" + newEmployee.getContact() + "', '"
							+ newEmployee.getAddedBy() + "','" + newEmployee.getAddress() + "', '"
							+ newEmployee.getDob() + "', '" + newEmployee.getroleId() + "', '" + last_inserted_id
							+ "');",
					Statement.RETURN_GENERATED_KEYS);
			addEmployeeStmt.executeUpdate();

			// get the previous ID
			rs = addEmployeeStmt.getGeneratedKeys();
			if (rs.next()) {
				last_inserted_id = rs.getInt(1);
			}

			// Get authorities & Save employee_id with authority emp_authority table
			getAllAuthoritiesForRole = newConn.prepareStatement("SELECT `name` \r\n" + "FROM `authority` a\r\n"
					+ "LEFT JOIN `role_authority` r \r\n" + "ON r.`authority_id`= a.`id`\r\n" + "WHERE `role_id` = '"
					+ newEmployee.getroleId() + "';");
			rs = getAllAuthoritiesForRole.executeQuery();

			while (rs.next()) {
				addEmployeeAuthority = newConn
						.prepareStatement("INSERT INTO `emp_authority`(`emp_id`, `authority` ) VALUES('"
								+ last_inserted_id + "','" + rs.getString("name") + "');");
				addEmployeeAuthority.executeUpdate();
			}

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
					"select e.`id` as empId, r.`id` as roleId, r.`name` as role, u.`id` as uId, u.`name`, u.`mobile`, u.`email`, u.`user_name` as userName,\r\n"
							+ "e.`nic`, e.`contact`, e.`address`, e.`dob`\r\n" + "from `employees` e\r\n"
							+ "left join `users` u\r\n" + "on u.`id`=e.`user_id`\r\n" + "left join `roles` r\r\n"
							+ "on e.`role_id` = r.`id`\r\n" + "where u.`is_deleted` = false");

			rs = getEmployeeStmt.executeQuery();
			List<Employee> employeeList = new ArrayList<Employee>();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("uId"));
				user.setName(rs.getString("name"));
				user.setMobile(rs.getString("mobile"));
				user.setEmail(rs.getString("email"));
				user.setUserName(rs.getString("userName"));

				Employee employee = new Employee();
				employee.setUser(user);
				employee.setId(rs.getInt("empId"));
				employee.setRole(rs.getString("role"));
				employee.setDob(rs.getDate("dob").toString());
				employee.setNic(rs.getString("nic"));
				employee.setContact(rs.getString("contact"));
				employee.setAddress(rs.getString("address"));
				employee.setRoleId(rs.getInt("roleId"));
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

	// VIEW SINGLE EMPLOYEE
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
		PreparedStatement getAllAuthoritiesForRole = null;
		PreparedStatement addEmployeeAuthority = null;
		PreparedStatement deleteAuthStmt = null;

		try {
			newConn = conn.getCon();

			// Add user crediantials
			updateUserStmt = newConn.prepareStatement("UPDATE users SET user_name ='" + employee.getUser().getUserName()
					+ "' , name='" + employee.getUser().getName() + "' , mobile='" + employee.getUser().getMobile()
					+ "',  password='" + employee.getUser().getPassword() + "', email='" + employee.getUser().getEmail()
					+ "' WHERE id = '" + employee.getUser().getId() + "';");
			System.out.println("updateUserStmt-------"+updateUserStmt);
			updateUserStmt.executeUpdate();

			// Add employee with both user and employee Credentials
			updateEmployeeStmt = newConn.prepareStatement(
					"UPDATE employees SET nic ='" + employee.getNic() + "', contact='" + employee.getContact()
							+ "', added_by='" + employee.getAddedBy() + "',  address='" + employee.getAddress()
							+ "', dob='" + java.sql.Date.valueOf(employee.getDob()) + "', role_id='"
							+ employee.getroleId() + "' WHERE user_id='" + employee.getId() + "';",
					Statement.RETURN_GENERATED_KEYS);
			System.out.println("updateEmployeeStmt-------"+updateEmployeeStmt);
			updateEmployeeStmt.executeUpdate();
			
			deleteAuthStmt = newConn.prepareStatement("DELETE FROM `emp_authority` WHERE `emp_id` ='"+employee.getId()+"';");
			System.out.println("deleteAuthStmt-------"+deleteAuthStmt);
			deleteAuthStmt.executeUpdate();
			
			
			// Get authorities & Save employee_id with authority emp_authority table
			getAllAuthoritiesForRole = newConn.prepareStatement("SELECT `name` \r\n" + "FROM `authority` a\r\n"
					+ "LEFT JOIN `role_authority` r \r\n" + "ON r.`authority_id`= a.`id`\r\n" + "WHERE `role_id` = '"
					+ employee.getroleId() + "';");
			System.out.println("getAllAuthoritiesForRole-------"+getAllAuthoritiesForRole);
			rs = getAllAuthoritiesForRole.executeQuery();

			while (rs.next()) {
				addEmployeeAuthority = newConn
						.prepareStatement("INSERT INTO `emp_authority`(`emp_id`, `authority` ) VALUES('"
								+ employee.getId() + "','" + rs.getString("name") + "');");
				addEmployeeAuthority.executeUpdate();
			}

			
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
