package com.vithu.uscms.service.product;

/**
 * @author M.Vithusanth
 * @CreatedOn 20th April 2018
 * @Purpose Services for Add/Edit/Delete/View Single/View All Brands
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.vithu.uscms.entities.Brand;
import com.vithu.uscms.entities.Employee;
import com.vithu.uscms.entities.ItemType;
import com.vithu.uscms.entities.Product;
import com.vithu.uscms.entities.ProductType;
import com.vithu.uscms.entities.User;
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
					"SELECT p.`id`, p.`name`,b.`id` AS brandId, b.`name` AS brandName, p.`code`, p.`description`,\r\n"
							+ "i.`id` AS itemTypeId, i.`name` AS itemTypeName, p.`size`, p.`selling_price` AS salesPrice,\r\n"
							+ "p.`last_purchase_price` AS purchasePrice, p.`min_price` AS minPrice, p.`dicount_par` AS discount,u.name as addedBy\r\n"
							+ "FROM `products` p\r\n" + "LEFT JOIN `pro_brands` b\r\n" + "ON p.`brand`=b.`id`\r\n"
							+ "LEFT JOIN `pro_item_types` i\r\n" + "ON p.`pro_item_type`=i.`id`\r\n"
							+ "LEFT JOIN `employees` e\r\n" + "ON e.`id`=p.`added_by`\r\n" + "LEFT JOIN `users` u\r\n"
							+ "ON u.`id`=e.`user_id`\r\n" + "WHERE p.`is_deleted`=FALSE;");
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
				product.setSize(res.getString("size"));
				product.setSelleingPrice(res.getDouble("salesPrice"));
				product.setLastPurchasePrice(res.getDouble("purchasePrice"));
				product.setMinPrice(res.getDouble("minPrice"));

				Employee addedBy=new Employee();
				User user=new User();
				user.setName(res.getString("addedBy"));
				addedBy.setUser(user);
				product.setAddedBy(addedBy);
				
				
				Brand brand = new Brand();
				brand.setId(res.getInt("brandId"));
				brand.setName(res.getString("brandName"));
				product.setBrand(brand);

				ItemType itemType = new ItemType();
				itemType.setId(res.getInt("itemTypeId"));
				itemType.setName(res.getString("itemTypeName"));
				product.setItemType(itemType);

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

	// METHORD TO DISABLE PRODUCT
	public GenericResult deleteProduct(int id) {
		PreparedStatement deleteStmt = null;
		try {
			newConn = conn.getCon();
			deleteStmt = newConn
					.prepareStatement("UPDATE `products` SET `is_deleted` = TRUE WHERE `id` = '" + id + "';");
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

	// ADD PRODUCT METHOD
	public GenericResult addProduct(Product newProduct) {
		PreparedStatement addStmt = null;

		try {
			newConn = conn.getCon();

			// Add Vehicle Credentials
			addStmt = newConn.prepareStatement(
					"INSERT INTO `products`(`name`, `brand`, `code`, `description`, `pro_item_type`, `size`, `selling_price`, `last_purchase_price`, `min_price`, `dicount_par`,`added_by`) \r\n"
							+ "VALUES ('" + newProduct.getName() + "','" + newProduct.getBrand().getId() + "','"
							+ newProduct.getCode() + "','" + newProduct.getDescription() + "','"
							+ newProduct.getItemType().getId() + "','" + newProduct.getSize() + "','"
							+ newProduct.getSelleingPrice() + "','" + newProduct.getLastPurchasePrice() + "', '"
							+ newProduct.getMinPrice() + "', '" + newProduct.getDiscount() + "','"
							+ newProduct.getAddedBy().getUser().getId() + "');",
					Statement.RETURN_GENERATED_KEYS);
			addStmt.executeUpdate();

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "New Vehicle Added Successfully");

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

	// UPDATE EXISTING PRODUCT
	public GenericResult updateProduct(Product newProduct) {
		PreparedStatement updateStmt = null;

		try {
			newConn = conn.getCon();

			// Update Product Credentials
			updateStmt = newConn.prepareStatement("UPDATE `products` SET `name`='" + newProduct.getName()
					+ "', `brand`='" + newProduct.getBrand().getId() + "', `code`='" + newProduct.getCode()
					+ "', `description`='" + newProduct.getDescription() + "', `pro_item_type`='"
					+ newProduct.getItemType().getId() + "', `size`='" + newProduct.getSize() + "', `selling_price`='"
					+ newProduct.getSelleingPrice() + "', `last_purchase_price`='" + newProduct.getLastPurchasePrice()
					+ "', `min_price`='" + newProduct.getMinPrice() + "', `dicount_par`='" + newProduct.getMinPrice()
					+ "' WHERE `id`='" + newProduct.getId() + "'", Statement.RETURN_GENERATED_KEYS);
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
