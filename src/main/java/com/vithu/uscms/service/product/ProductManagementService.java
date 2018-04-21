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

import com.vithu.uscms.entities.Brand;
import com.vithu.uscms.entities.ConsumerType;
import com.vithu.uscms.entities.ItemType;
import com.vithu.uscms.entities.Product;
import com.vithu.uscms.entities.ProductType;
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
					"SELECT p.`id`, p.`name`, b.`name` as brand, p.`code`, p.`description`, i.`name` as itemType, p.`size`, p.`selling_price` as salesPrice,\r\n"
							+ "p.`last_purchase_price` as purchasePrice, p.`min_price` as minPrice, p.`dicount_par` as discount, c.`name` as consumerType \r\n"
							+ "FROM `products` p\r\n" + "left join `pro_brands` b\r\n" + "on p.`brand`=b.`id`\r\n"
							+ "left join `pro_item_types` i\r\n" + "on p.`pro_item_type`=i.`id`\r\n"
							+ "left join `consumer_types` c\r\n" + "on p.`consumer_type`=c.`id`\r\n"
							+ "WHERE p.`is_deleted`=FALSE;");
			res = stmt.executeQuery();
			List<Product> productList = new ArrayList<Product>();
			while (res.next()) {
				System.out.println("id : " + res.getInt("id"));
				Product product = new Product();
				product.setId(res.getInt("id"));
				product.setName(res.getString("name"));
				product.setCode(res.getString("code"));
				product.setDescription(res.getString("description"));
				product.setDiscount(res.getDouble("discount"));
				product.setLastPurchasePrice(res.getDouble("purchasePrice"));
				product.setMinPrice(res.getDouble("minPrice"));
				
				Brand brand =new Brand();
				brand.setName(res.getString("brand"));
				product.setBrand(brand);
				
				ItemType itemType=new ItemType();
				itemType.setName(res.getString("itemType"));
				product.setItemType(itemType);
				
				ConsumerType consumerType=new ConsumerType();
				consumerType.setName(res.getString("consumertype"));
				product.setConsumerType(consumerType);				
				
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
