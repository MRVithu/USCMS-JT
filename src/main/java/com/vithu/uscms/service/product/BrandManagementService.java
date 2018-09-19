package com.vithu.uscms.service.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.vithu.uscms.entities.Brand;
import com.vithu.uscms.entities.Product;
import com.vithu.uscms.others.DBConnection;
import com.vithu.uscms.others.GenericResult;
import com.vithu.uscms.others.JsonFormer;
import com.vithu.uscms.others.MessageConstant;

/**
 * @author M.Vithusanth
 * @CreatedOn 20th April 2018
 * @Purpose Services for Add/Edit/Delete/View Single/View All Brands
 */
public class BrandManagementService {
	private DBConnection conn = new DBConnection();

	private PreparedStatement stmt;

	private Connection newConn;

	private ResultSet res;

	// METHOD TO VIEW ALL BRANDS
	public GenericResult getAllBrands() {
		try {
			newConn = conn.getCon();
			stmt = newConn.prepareStatement("SELECT * FROM `pro_brands`  WHERE `is_deleted`=FALSE;");
			res = stmt.executeQuery();
			List<Brand> brandList = new ArrayList<Brand>();
			while (res.next()) {
				System.out.println("id : " + res.getInt("id"));
				Brand brand = new Brand();
				brand.setId(res.getInt("id"));
				brand.setName(res.getString("name"));
				brand.setDescription(res.getString("description"));
				brandList.add(brand);
			}

			GenericResult rs = new GenericResult();
			rs.setStatus(true);
			rs.setResult(brandList);

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "retriveed successfully", brandList,
					JsonFormer.form(rs), " ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		}
	}

	// METHORD TO DISABLE BRAND
	public GenericResult deleteBrand(int id) {
		PreparedStatement deleteStmt = null;
		try {
			newConn = conn.getCon();
			deleteStmt = newConn
					.prepareStatement("UPDATE `pro_brands` SET `is_deleted` = TRUE WHERE `id` = '" + id + "';");
			deleteStmt.executeUpdate();

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		} finally {
			try {
				deleteStmt.close();
				conn.closeCon();
			} catch (SQLException e2) {
				// TODO: handle exception
			}
		}
	}

	// ADD BRAND METHOD
	public GenericResult addBrand(Brand newBrand) {
		PreparedStatement addStmt = null;

		try {
			newConn = conn.getCon();

			// Add Brand Credentials
			addStmt = newConn.prepareStatement("INSERT INTO `pro_brands` (`name`, `description`) VALUE ('"
					+ newBrand.getName() + "', '" + newBrand.getDescription() + "');");
			addStmt.executeUpdate();

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "New Brand Added Successfully");

		} catch (Exception e) {
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		} finally {
			try {
				addStmt.close();
				conn.closeCon();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	// UPDATE EXISTING BRAND
	public GenericResult updateBrand(Brand newBrand) {
		PreparedStatement updateStmt = null;

		try {
			newConn = conn.getCon();

			// Update Brand Credentials
			updateStmt = newConn
					.prepareStatement("update `pro_brands` set `name` = '" + newBrand.getName() + "', `description` = '"
							+ newBrand.getDescription() + "' where `id` = '" + newBrand.getId() + "';");
			updateStmt.executeUpdate();

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "Updated Successfully");

		} catch (Exception e) {
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		} finally {
			try {
				updateStmt.close();
				conn.closeCon();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

}
