package com.vithu.uscms.service.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	public GenericResult getAllConsumerTypes() {
		try {
			newConn = conn.getCon();
			stmt = newConn.prepareStatement(
					"SELECT * FROM `pro_brands`  WHERE `is_deleted`=FALSE;");
			res = stmt.executeQuery();
			List<ProductType> productTypeList = new ArrayList<ProductType>();
			while (res.next()) {
				System.out.println("id : " + res.getInt("id"));
				ProductType productType = new ProductType();
				productType.setId(res.getInt("id"));
				productType.setName(res.getString("name"));
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
}
