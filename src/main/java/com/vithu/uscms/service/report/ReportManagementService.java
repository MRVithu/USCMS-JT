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

	// METHOD TO VIEW ALL SALES
	public GenericResult getReachedMinLevelProduct() {

		PreparedStatement stmt = null;

		try {
			newConn = conn.getCon();
			stmt = newConn.prepareStatement(
					"SELECT p.`id`, p.`code`, p.`name`,b.`name` AS brand, p.`reorder_qty`,`reorder_qty`-(SUM(pp.`quantity`)-SUM(sp.`quantity`)) AS inventry\r\n"
							+ "FROM `products` p\r\n" + "LEFT JOIN `sale_products` sp ON sp.`product`=p.`id`\r\n"
							+ "LEFT JOIN `purchase_products` pp ON pp.`product`=p.`id`\r\n"
							+ "LEFT JOIN `pro_brands` b ON b.`id`=p.`brand`\r\n" + "GROUP BY p.`id`");
			res = stmt.executeQuery();

			List<Report> rList = new ArrayList<Report>();
			while (res.next()) {

				if (0 > res.getInt("inventry")) {
					Report rt = new Report();
					Product product = new Product();
					Brand brand = new Brand();

					System.out.println("--" + res.getInt("reorder_qty") + ">" + res.getInt("inventry") + "--");
					product.setName(res.getString("name"));
					product.setCode(res.getString("code"));
					product.setReOrderQty(res.getInt("reorder_qty"));

					brand.setName(res.getString("brand"));
					product.setBrand(brand);

					rt.setQty(res.getInt("inventry"));
					rt.setProduct(product);

					rList.add(rt);

				}

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

	// METHOD TO VIEW ALL SALES
	public GenericResult getReachedZeroLevelProduct() {

		PreparedStatement stmt = null;

		try {
			newConn = conn.getCon();
			stmt = newConn.prepareStatement(
					"SELECT p.`id`, p.`code`, p.`name`,b.`name` AS brand, p.`reorder_qty`,(SUM(pp.`quantity`)-SUM(sp.`quantity`)) AS inventry,SUM(pp.`quantity`) AS purchase,SUM(sp.`quantity`) AS sales\r\n"
							+ "FROM `products` p\r\n" + "LEFT JOIN `sale_products` sp ON sp.`product`=p.`id`\r\n"
							+ "LEFT JOIN `purchase_products` pp ON pp.`product`=p.`id`\r\n"
							+ "LEFT JOIN `pro_brands` b ON b.`id`=p.`brand`\r\n" + "GROUP BY p.`id`");
			res = stmt.executeQuery();

			List<Report> rList = new ArrayList<Report>();
			while (res.next()) {
				
				if (0 == (res.getInt("purchase")-res.getInt("sales"))) {
					Report rt = new Report();
					Product product = new Product();
					Brand brand = new Brand();

					product.setName(res.getString("name"));
					product.setCode(res.getString("code"));
					product.setReOrderQty(res.getInt("reorder_qty"));

					brand.setName(res.getString("brand"));
					product.setBrand(brand);

					rt.setQty(res.getInt("inventry"));
					rt.setProduct(product);

					rList.add(rt);

				}

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
