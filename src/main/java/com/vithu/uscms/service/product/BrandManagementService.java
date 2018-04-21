package com.vithu.uscms.service.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.vithu.uscms.entities.Brand;
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
			stmt = newConn.prepareStatement(
					"SELECT * FROM `pro_brands`  WHERE `is_deleted`=FALSE;");
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
}
