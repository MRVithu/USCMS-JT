package com.vithu.uscms.service.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vithu.uscms.entities.Supplier;
import com.vithu.uscms.entities.User;
import com.vithu.uscms.others.DBConnection;
import com.vithu.uscms.others.GenericResult;
import com.vithu.uscms.others.JsonFormer;
import com.vithu.uscms.others.MessageConstant;

/**
 * @author M.Vithusanth
 * @CreatedOn 23rd April 2018
 * @Purpose Services for Add/Edit/Delete/View Single/View All Supplier
 */

@Service
public class SupplierManagementService {
	private DBConnection conn = new DBConnection();

	private PreparedStatement stmt;

	private Connection newConn;

	private ResultSet res;

	// METHOD TO VIEW ALL CUSTOMER
	public GenericResult getAllSuppliers() {
		try {
			newConn = conn.getCon();
			stmt = newConn.prepareStatement(
					"SELECT s.id AS sid, s.`company`, u.* FROM `suppliers` s LEFT JOIN `users` u ON s.user_id = u.id WHERE u.`is_deleted`=FALSE;");
			res = stmt.executeQuery();
			List<Supplier> supplierList = new ArrayList<Supplier>();
			while (res.next()) {
				System.out.println("id : " + res.getInt("id"));
				User user = new User();
				user.setId(res.getInt("id"));
				user.setName(res.getString("name"));
				user.setUserName(res.getString("user_name"));
				user.setEmail(res.getString("email"));
				user.setMobile(res.getString("mobile"));

				Supplier supplier = new Supplier();
				supplier.setUser(user);
				supplier.setId(res.getInt("sid"));
				supplier.setCompany(res.getString("company"));
				supplierList.add(supplier);
			}

			GenericResult rs = new GenericResult();
			rs.setStatus(true);
			rs.setResult(supplierList);

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "Retriveed successfully", supplierList,
					JsonFormer.form(rs), " ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		}

	}

	// METHORD TO DISABLE CUSTOMER
	public GenericResult deleteCustomer(String id) {
		try {
			newConn = conn.getCon();
			stmt = newConn.prepareStatement("UPDATE `users` SET `is_deleted` = TRUE WHERE `id` =" + id);
			stmt.executeUpdate();

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		}

	}

	// METHOD TO ADD CUSTOMER
	public GenericResult addCustomer(Supplier newCustomer) {
		try {
			int lastInsertedId = 0;
			newConn = conn.getCon();

			// Add user
			stmt = newConn.prepareStatement(
					"INSERT INTO`users` (`name`,`email`,`mobile`,`contact`,`user_name`,`password`,`is_deleted`) VALUES ('"
							+ newCustomer.getUser().getName() + "','" + newCustomer.getUser().getEmail() + "','"
							+ newCustomer.getUser().getMobile() + "','" + newCustomer.getUser().getUserName() + "','"
							+ newCustomer.getUser().getPassword() + "',false)",
					Statement.RETURN_GENERATED_KEYS);
			// stmt.setString(1, "value");
			stmt.executeUpdate();

			// get the previous IDs
			res = stmt.getGeneratedKeys();
			if (res.next()) {
				lastInsertedId = res.getInt(1);
			}

			// Add customer
			stmt = newConn.prepareStatement("INSERT INTO`customers` (`user_id`) VALUES ('" + lastInsertedId + "')");
			stmt.executeUpdate();

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		}
	}

	// METHOD TO EDIT CUSTOMER
	public GenericResult editCustomer(String id, Supplier newCustomer) {
		try {
			newConn = conn.getCon();

			// Edit user
			stmt = newConn.prepareStatement("UPDATE `users` SET `name`='" + newCustomer.getUser().getName()
					+ "',`mobile`='" + newCustomer.getUser().getMobile() + "', `email`='"
					+ newCustomer.getUser().getEmail() + "',`user_name`='" + newCustomer.getUser().getUserName()
					+ "', `password`='" + newCustomer.getUser().getPassword() + "' WHERE `id`='" + id + "'");
			// stmt.setString(1, "value");
			stmt.executeUpdate();

			// Add customer
			/*
			 * stmt = newConn.prepareStatement( "" ); stmt.executeUpdate();
			 */

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		}
	}

	// METHOD TO VIEW SINGLE CUSTOMER
	public GenericResult getSingleCustomer(int id) {
		try {
			newConn = conn.getCon();
			stmt = newConn.prepareStatement(
					"SELECT c.id AS cid, u.* FROM `customers` c LEFT JOIN `users` u ON c.user_id = u.id WHERE u.`is_deleted`=FALSE AND u.id='"
							+ id + "'");
			res = stmt.executeQuery();
			List<Supplier> customerList = new ArrayList<Supplier>();
			// System.out.println( res.next() );

			while (res.next()) {
				System.out.println(res.getInt("cid"));
				User user = new User();
				user.setId(res.getInt("id"));
				user.setName(res.getString("name"));
				user.setName(res.getString("user_name"));
				user.setPassword(res.getString("password"));
				user.setEmail(res.getString("email"));
				user.setMobile(res.getString("mobile"));

				Supplier customer = new Supplier();
				customer.setUser(user);
				customer.setId(res.getInt("cid"));
				customerList.add(customer);
			}
			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "", customerList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		}
	}
}
