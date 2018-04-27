package com.vithu.uscms.service.purchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.vithu.uscms.entities.Department;
import com.vithu.uscms.entities.Employee;
import com.vithu.uscms.entities.Product;
import com.vithu.uscms.entities.Purchase;
import com.vithu.uscms.entities.PurchaseOrder;
import com.vithu.uscms.entities.PurchaseOrderProduct;
import com.vithu.uscms.entities.PurchaseProduct;
import com.vithu.uscms.entities.Supplier;
import com.vithu.uscms.entities.User;
import com.vithu.uscms.others.DBConnection;
import com.vithu.uscms.others.GenericResult;
import com.vithu.uscms.others.JsonFormer;
import com.vithu.uscms.others.MessageConstant;

/**
 * @author M.Vithusanth
 * @CreatedOn 27th April 2018
 * @Purpose Services for Purchase
 */
public class PurchaseManagementService {
	private DBConnection conn = new DBConnection();

	private PreparedStatement stmt = null;
	PreparedStatement getPurProductsStmt = null;

	private Connection newConn;

	private ResultSet res;
	private ResultSet result;

	// METHOD TO VIEW ALL PURCHASES
	public GenericResult getAllPurchases() {
		try {
			newConn = conn.getCon();
			stmt = newConn.prepareStatement("SELECT p.`id`, p.`t_date`, p.`code`, u.`name` AS supplier, p.`pay`,\r\n"
					+ "ui.`name` AS addedBy,  d.`name` as dept\r\n" + "FROM `purchases` p\r\n"
					+ "LEFT JOIN `suppliers` s\r\n" + "ON s.`id`=p.`supplier`\r\n" + "LEFT JOIN `users` u\r\n"
					+ "ON u.`id`=s.`user_id`\r\n" + "LEFT JOIN `employees` e\r\n" + "ON e.`id`=p.`added_by`\r\n"
					+ "LEFT JOIN `users` ui\r\n" + "ON ui.`id`=e.`user_id`\r\n" + "left join `departments` d\r\n"
					+ "on d.`id`=p.`dept`");
			res = stmt.executeQuery();

			List<Purchase> purchaseList = new ArrayList<Purchase>();
			while (res.next()) {
				System.out.println("id : " + res.getInt("id"));

				Purchase purchase = new Purchase();

				purchase.setId(res.getInt("id"));
				purchase.setCode(res.getString("code"));
				purchase.settDate(res.getString("t_date"));

				Department dept = new Department();
				dept.setName(res.getString("dept"));
				purchase.setDept(dept);

				Employee employee = new Employee();
				User addedBy = new User();
				addedBy.setName(res.getString("addedBy"));
				employee.setUser(addedBy);
				purchase.setAddedBy(employee);

				Supplier supplier = new Supplier();
				User user = new User();
				user.setName(res.getString("supplier"));

				supplier.setUser(user);
				purchase.setSupplier(supplier);

				getPurProductsStmt = newConn.prepareStatement(
						"SELECT  pp.`id`, p.`id` AS proId, p.`name` AS proName, p.`code` AS proCode, pp.`total_given`,\r\n"
								+ "pp.`quantity`, pp.`unit_price`, pp.`total_discount`, pp.`total_given`, pp.`expiry_date`,\r\n"
								+ "pp.`selling_price`\r\n" + "FROM `purchase_products` pp\r\n"
								+ "LEFT JOIN `products` p\r\n" + "ON p.`id`=pp.`product`\r\n" + "WHERE pp.`purchase`='"
								+ res.getInt("id") + "';");
				result = getPurProductsStmt.executeQuery();
				List<PurchaseProduct> purProductList = new ArrayList<PurchaseProduct>();

				while (result.next()) {
					PurchaseProduct purProduct = new PurchaseProduct();
					
					purProduct.setId(result.getInt("proId"));
					purProduct.setQty(result.getDouble("quantity"));
					purProduct.setUnitPrice(result.getDouble("unit_price"));
					purProduct.setExpDate(result.getString("expiry_date"));
					purProduct.setTotDiscount(result.getDouble("total_discount"));
					purProduct.setTotGiven(result.getDouble("total_given"));

					Product product = new Product();
					product.setId(result.getInt("id"));
					product.setName(result.getString("proName"));
					product.setCode(result.getString("proCode"));
					purProduct.setProduct(product);

					purProductList.add(purProduct);
				}

				purchase.setPurProduct(purProductList);
				purchaseList.add(purchase);
			}

			GenericResult rs = new GenericResult();
			rs.setStatus(true);
			rs.setResult(purchaseList);

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "Retriveed successfully", purchaseList,
					JsonFormer.form(rs), " ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		}

	}
	

	// ADD PURCHASE  METHOD
	public GenericResult addPurchase(Purchase newPurchase) {
		PreparedStatement addPurchaseStmt = null;

		try {
			int last_inserted_id = 0;

			newConn = conn.getCon();

			// Add purchase credentials
			addPurchaseStmt = newConn.prepareStatement(
					"INSERT INTO `purchases`(`code`, `supplier`, `t_date`, `added_by`,`dept`)\r\n" + 
					"VALUES('"+newPurchase.getCode()+"','"+newPurchase.getSupplier().getId()+"', '"+newPurchase.gettDate()+"', '"+newPurchase.getAddedBy().getId()+"', '"+newPurchase.getDept().getId()+"');",
					Statement.RETURN_GENERATED_KEYS);
			addPurchaseStmt.executeUpdate();

			// get the previous IDs
			res = addPurchaseStmt.getGeneratedKeys();
			if (res.next()) {
				last_inserted_id = res.getInt(1);
			}

			// Add Purchase Product Credentials
			List<PurchaseProduct> purProductsList = new ArrayList<PurchaseProduct>();
			purProductsList = newPurchase.getPurProduct();

			for (PurchaseProduct purProduct : purProductsList) {
				PreparedStatement addPOPStmt = null;
				addPOPStmt = newConn.prepareStatement(
						"insert into `purchase_products`(`purchase`, `product`, `quantity`, `unit_price`)\r\n" + 
						"value ('"+last_inserted_id+"','"+purProduct.getProduct().getId()+"', '"+purProduct.getQty()+"', '"+purProduct.getUnitPrice()+"');");
				addPOPStmt.executeUpdate();
			}

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "New Purchase order Added Successfully");

		} catch (Exception e) {
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		} finally {
			try {
				addPurchaseStmt.close();
				conn.closeCon();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

}
