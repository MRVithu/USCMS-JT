package com.vithu.uscms.service.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vithu.uscms.entities.Brand;
import com.vithu.uscms.entities.ProductType;
import com.vithu.uscms.others.DBConnection;
import com.vithu.uscms.others.GenericResult;
import com.vithu.uscms.others.JsonFormer;
import com.vithu.uscms.others.MessageConstant;

/**
 * @author M.Vithusanth
 * @CreatedOn 21th April 2018
 * @Purpose Services for Add/Edit/Delete/View Single/View All Product types
 */
public class ProductTypeManagementService {
	private DBConnection conn = new DBConnection();

	private PreparedStatement stmt;

	private Connection newConn;

	private ResultSet res;

	// METHOD TO VIEW ALL PRODUCT TYPES
	public GenericResult getAllProductTypes() {
		try {
			newConn = conn.getCon();
			stmt = newConn.prepareStatement("SELECT * FROM `pro_types`  WHERE `is_deleted`=FALSE;");
			res = stmt.executeQuery();
			List<ProductType> productTypeList = new ArrayList<ProductType>();
			while (res.next()) {
				// System.out.println("id : " + res.getInt("id"));
				ProductType productType = new ProductType();
				productType.setId(res.getInt("id"));
				productType.setName(res.getString("name"));
				productType.setDescription(res.getString("description"));
				productTypeList.add(productType);
			}

			GenericResult rs = new GenericResult();
			rs.setStatus(true);
			rs.setResult(productTypeList);

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "retriveed successfully", productTypeList,
					JsonFormer.form(rs), " ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		}

	}

	// METHORD TO DISABLE PRODUCT TYPE
	public GenericResult deleteProType(int id) throws ClassNotFoundException {
		PreparedStatement deleteStmt = null;
		try {
			newConn = conn.getCon();

			deleteStmt = newConn
					.prepareStatement("UPDATE `pro_types` SET `is_deleted` = TRUE WHERE `id` = '" + id + "';");

			deleteStmt.executeUpdate();

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "Delete Successfully");
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

	// ADD PRODUCT TYPE METHOD
	public GenericResult addProType(ProductType newProType) {
		PreparedStatement addStmt = null;

		try {
			newConn = conn.getCon();

			// Add Brand Credentials
			addStmt = newConn.prepareStatement(
					"INSERT INTO `pro_types` (`name`, `description`, `added_by`) VALUE ('" + newProType.getName()
							+ "', '" + newProType.getDescription() + "', '" + newProType.getAddedBy().getId() + "');");
			addStmt.executeUpdate();

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "New Product type Added Successfully");

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

	// UPDATE EXISTING PRODUCT TYPE
	public GenericResult updateProType(ProductType newProType) {
		PreparedStatement updateStmt = null;

		try {
			newConn = conn.getCon();

			// Update Brand Credentials
			updateStmt = newConn
					.prepareStatement("update `pro_types` set `name` = '" + newProType.getName() + "', `description` = '"
							+ newProType.getDescription() + "' where `id` = '" + newProType.getId() + "';");
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
