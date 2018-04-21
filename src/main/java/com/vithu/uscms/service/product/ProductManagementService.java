package com.vithu.uscms.service.product;
/**
 * @author M.Vithusanth
 * @CreatedOn 20th April 2018
 * @Purpose Services for Add/Edit/Delete/View Single/View All Brands
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.vithu.uscms.entities.Product;
import com.vithu.uscms.others.DBConnection;
import com.vithu.uscms.others.GenericResult;
import com.vithu.uscms.others.JsonFormer;
import com.vithu.uscms.others.MessageConstant;

public class ProductManagementService {
	private DBConnection conn = new DBConnection();

	private PreparedStatement stmt;

	private Connection newConn;

	private ResultSet res;

	// METHOD TO VIEW ALL BRANDS
	public GenericResult getAllProducts() {
		try {
			newConn = conn.getCon();
			stmt = newConn.prepareStatement(
					"SELECT * FROM `pro_brands`  WHERE `is_deleted`=FALSE;");
			res = stmt.executeQuery();
			List<Product> productList = new ArrayList<Product>();
			while (res.next()) {
				System.out.println("id : " + res.getInt("id"));
				Product product = new Product();
				product.setId(res.getInt("id"));
				product.setName(res.getString("name"));
				productList.add(product);
			}

			GenericResult rs = new GenericResult();
			rs.setStatus(true);
			rs.setResult(productList);

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "retriveed successfully", productList,
					JsonFormer.form(rs), " ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		}

	}
}
