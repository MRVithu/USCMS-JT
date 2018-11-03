package com.vithu.uscms.service.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.vithu.uscms.entities.Brand;
import com.vithu.uscms.entities.ItemType;
import com.vithu.uscms.entities.Product;
import com.vithu.uscms.entities.Report;
import com.vithu.uscms.others.DBConnection;
import com.vithu.uscms.others.GenericResult;
import com.vithu.uscms.others.JsonFormer;
import com.vithu.uscms.others.MessageConstant;

public class ReportManagementService {
	private DBConnection conn = new DBConnection();

	private Connection newConn;

	private ResultSet res;

	// METHOD TO VIEW ALL SALES
	public GenericResult getSalesAmountVsProduct() {

		PreparedStatement stmt = null;

		try {
			newConn = conn.getCon();
			stmt = newConn.prepareStatement(
					"SELECT p.`name`,p.`brand`,p.`pro_item_type`, SUM(sp.`unit_price`) AS amount, COUNT(p.`id`) AS qty\r\n"
							+ "FROM `sales` s\r\n" + "LEFT JOIN `sale_products` sp ON sp.`sale`=s.`id`\r\n"
							+ "LEFT JOIN `products` p ON p.`id`=sp.`product`\r\n" + "GROUP BY p.`id`\r\n"
							+ "ORDER BY SUM(sp.`unit_price`) DESC;");
			res = stmt.executeQuery();

			List<Report> rList = new ArrayList<Report>();
			while (res.next()) {
				System.out.println("id : " + res.getInt("brand"));

				Product product = new Product();
				product.setName(res.getString("name"));

				Brand brand = new Brand();
				brand.setId(res.getInt("brand"));

				ItemType it = new ItemType();
				it.setId(res.getInt("pro_item_type"));

				Report rt = new Report();
				rt.setBrand(brand);
				rt.setProduct(product);
				rt.setItemType(it);
				rt.setAmount(res.getDouble("amount"));
				rt.setQty(res.getInt("qty"));

				rList.add(rt);
			}

			GenericResult rs = new GenericResult();
			rs.setStatus(true);
			rs.setResult(rList);

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "Retriveed successfully", rList,
					JsonFormer.form(rs), " ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		}
	}

}
