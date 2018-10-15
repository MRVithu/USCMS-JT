package com.vithu.uscms.service.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vithu.uscms.entities.Brand;
import com.vithu.uscms.entities.ItemType;
import com.vithu.uscms.entities.ProductType;
import com.vithu.uscms.others.DBConnection;
import com.vithu.uscms.others.GenericResult;
import com.vithu.uscms.others.JsonFormer;
import com.vithu.uscms.others.MessageConstant;

/**
 * @author M.Vithusanth
 * @CreatedOn 21th April 2018
 * @Purpose Services for Add/Edit/Delete/View Single/View All Item types
 */
public class ItemTypeManagementService {
	private DBConnection conn = new DBConnection();

	private PreparedStatement stmt;

	private Connection newConn;

	private ResultSet res;

	// METHOD TO VIEW ALL ITEM TYPES
	public GenericResult getAllItemTypes() {
		try {
			newConn = conn.getCon();
			stmt = newConn.prepareStatement("SELECT it.`id`,it.`name`,it.`description`,pt.`name` AS proType\r\n"
					+ "FROM `pro_item_types` it\r\n" + "LEFT JOIN `pro_types` pt \r\n" + "ON it.`pro_type`=pt.`id`\r\n"
					+ " WHERE it.`is_deleted`=FALSE;");
			res = stmt.executeQuery();
			List<ItemType> itemTypeList = new ArrayList<ItemType>();
			while (res.next()) {
				System.out.println("id : " + res.getInt("id"));
				ItemType itemType = new ItemType();
				itemType.setId(res.getInt("id"));
				itemType.setName(res.getString("name"));
				itemType.setDescription(res.getString("description"));

				ProductType proType = new ProductType();
				proType.setName(res.getString("proType"));

				itemType.setProType(proType);
				itemTypeList.add(itemType);
			}

			GenericResult rs = new GenericResult();
			rs.setStatus(true);
			rs.setResult(itemTypeList);

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "retriveed successfully", itemTypeList,
					JsonFormer.form(rs), " ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		}

	}

	// METHORD TO DISABLE ITEM TYPES
	public GenericResult deleteItemType(int id) throws ClassNotFoundException {
		PreparedStatement deleteStmt = null;
		try {
			newConn = conn.getCon();

			deleteStmt = newConn
					.prepareStatement("UPDATE `pro_item_types` SET `is_deleted` = TRUE WHERE `id` = '" + id + "';");

			deleteStmt.executeUpdate();

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "Disable Successfully");
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

	// ADD ITEM TYPES METHOD
	public GenericResult addItemtype(ItemType newItemType) {
		PreparedStatement addStmt = null;

		try {
			newConn = conn.getCon();

			// Add Item type Credentials
			addStmt = newConn.prepareStatement(
					"INSERT INTO `pro_item_types` (`name`, `description`, `pro_type`, `added_by`) VALUE ('" + newItemType.getName()
							+ "', '" + newItemType.getDescription() + "', '"+newItemType.getProType().getId()+"',' " + newItemType.getAddedBy().getId() + "');");
			addStmt.executeUpdate();

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "New Item type Added Successfully");

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

	// UPDATE EXISTING ITEM TYPES
	public GenericResult updateItemType(ItemType newItemType) {
		PreparedStatement updateStmt = null;

		try {
			newConn = conn.getCon();

			// Update Item type Credentials
			updateStmt = newConn
					.prepareStatement("update `pro_item_types` set `name` = '" + newItemType.getName() + "', `pro_type` = '"+newItemType.getProType().getId()+"', `description` = '"
							+ newItemType.getDescription() + "' where `id` = '" + newItemType.getId() + "';");
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
