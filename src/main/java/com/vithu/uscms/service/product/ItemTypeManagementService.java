package com.vithu.uscms.service.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
			stmt = newConn.prepareStatement(
					"SELECT * FROM `pro_item_types` it\r\n" + 
					"LEFT JOIN `pro_types` pt \r\n" + 
					"ON pt.`id`=it.`pro_type`\r\n" + 
					" WHERE it.`is_deleted`=FALSE;");
			res = stmt.executeQuery();
			List<ItemType> itemTypeList = new ArrayList<ItemType>();
			while (res.next()) {
				System.out.println("id : " + res.getInt("id"));
				ItemType itemType = new ItemType();
				itemType.setId(res.getInt("id"));
				itemType.setName(res.getString("name"));
				itemType.setDescription(res.getString("description"));
				
				ProductType proType= new ProductType();
				proType.setName(res.getString("pro_type"));
				
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
}
