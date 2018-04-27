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
import com.vithu.uscms.entities.PurchaseOrder;
import com.vithu.uscms.entities.PurchaseOrderProduct;
import com.vithu.uscms.entities.Supplier;
import com.vithu.uscms.entities.User;
import com.vithu.uscms.others.DBConnection;
import com.vithu.uscms.others.GenericResult;
import com.vithu.uscms.others.JsonFormer;
import com.vithu.uscms.others.MessageConstant;

/**
 * @author M.Vithusanth
 * @CreatedOn 23rd April 2018
 * @Purpose Services for Purchase order
 */
public class PurchaseOrderManagementService {
	private DBConnection conn = new DBConnection();

	private PreparedStatement stmt = null;
	PreparedStatement getPOProductsStmt = null;

	private Connection newConn;

	private ResultSet res;
	private ResultSet result;

	// METHOD TO VIEW ALL PURCHASE ORDERS
	public GenericResult getAllPurchaseOrders() {
		try {
			newConn = conn.getCon();
			stmt = newConn.prepareStatement(
					"SELECT po.`id`,po.`t_date`,u.`name` AS supplier, po.`expected_date`,po.`code`, po.`is_closed`, ui.`name` AS addedBy, po.`note`, d.`name` as dept\r\n"
							+ "FROM `purchase_order` po\r\n" + "LEFT JOIN `suppliers` s\r\n"
							+ "ON s.`id`=po.`supplier`\r\n" + "LEFT JOIN `users` u\r\n" + "ON u.`id`=s.`user_id`\r\n"
							+ "LEFT JOIN `employees` e\r\n" + "ON e.`id`=po.`added_by`\r\n" + "LEFT JOIN `users` ui\r\n"
							+ "ON ui.`id`=e.`user_id`\r\n" + "left join `departments` d\r\n" + "on d.`id`=po.`dept`");
			res = stmt.executeQuery();

			List<PurchaseOrder> purchaseOrderList = new ArrayList<PurchaseOrder>();
			while (res.next()) {
				System.out.println("id : " + res.getInt("id"));

				PurchaseOrder purOrder = new PurchaseOrder();

				purOrder.setId(res.getInt("id"));
				purOrder.setCode(res.getString("code"));
				purOrder.setExpectedDate(res.getString("expected_date"));
				purOrder.settDate(res.getString("t_date"));
				purOrder.setIsClosed(res.getBoolean("is_closed"));
				purOrder.setNote(res.getString("note"));

				Department dept = new Department();
				dept.setName(res.getString("dept"));
				purOrder.setDept(dept);

				Employee employee = new Employee();
				User addedBy = new User();
				addedBy.setName(res.getString("addedBy"));
				employee.setUser(addedBy);
				purOrder.setAddedBy(employee);

				Supplier supplier = new Supplier();
				User user = new User();
				user.setName(res.getString("supplier"));

				supplier.setUser(user);
				purOrder.setSupplier(supplier);

				getPOProductsStmt = newConn.prepareStatement(
						"SELECT  pop.`id`,p.`id` AS proId, p.`name` AS proName, p.`code` AS proCode,pop.`quantity`,pop.`ex_unit_price`\r\n"
								+ "FROM `purchase_order_products` pop\r\n" + "LEFT JOIN `products` p\r\n"
								+ "ON p.`id`=pop.`prdouct`\r\n" + "WHERE pop.`purchase_order`='" + res.getInt("id")
								+ "';");
				result = getPOProductsStmt.executeQuery();
				List<PurchaseOrderProduct> poProductList = new ArrayList<PurchaseOrderProduct>();

				while (result.next()) {
					PurchaseOrderProduct poProduct = new PurchaseOrderProduct();
					poProduct.setId(result.getInt("proId"));
					poProduct.setQty(result.getDouble("quantity"));
					poProduct.setUnitPrice(result.getDouble("ex_unit_price"));

					Product product = new Product();
					product.setId(result.getInt("id"));
					product.setName(result.getString("proName"));
					product.setCode(result.getString("proCode"));
					poProduct.setProduct(product);

					poProductList.add(poProduct);
				}

				purOrder.setPoProduct(poProductList);
				purchaseOrderList.add(purOrder);
			}

			GenericResult rs = new GenericResult();
			rs.setStatus(true);
			rs.setResult(purchaseOrderList);

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "Retriveed successfully", purchaseOrderList,
					JsonFormer.form(rs), " ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		}

	}

	// METHORD TO CLOSE PURCHASE ORDER
	public GenericResult closepurchaseOrder(int id) {
		PreparedStatement Stmt = null;
		try {
			newConn = conn.getCon();
			Stmt = newConn
					.prepareStatement("UPDATE `purchase_order` SET `is_closed` = TRUE WHERE `id` = '" + id + "';");
			Stmt.executeUpdate();

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		} finally {
			try {
				Stmt.close();
				conn.closeCon();
			} catch (SQLException e2) {
				// TODO: handle exception
			}
		}
	}

	// ADD PURCHASE ORDER METHOD
	public GenericResult addPurchaseOrder(PurchaseOrder newPurchaseOrder) {
		PreparedStatement addPurchaseOrderStmt = null;

		try {
			int last_inserted_id = 0;

			newConn = conn.getCon();

			// Add purchase order credentials
			addPurchaseOrderStmt = newConn.prepareStatement(
					"INSERT INTO `purchase_order`( `code`, `supplier`,`t_date`, `expected_date`, `dept`,`added_by`,`note` ) \r\n"
							+ "VALUES ('" + newPurchaseOrder.getCode() + "','" + newPurchaseOrder.getSupplier().getId()
							+ "','" + newPurchaseOrder.gettDate() + "','" + newPurchaseOrder.getExpectedDate() + "','"
							+ newPurchaseOrder.getDept().getId() + "','"
							+ newPurchaseOrder.getAddedBy().getUser().getId() + "','" + newPurchaseOrder.getNote()
							+ "');",
					Statement.RETURN_GENERATED_KEYS);
			addPurchaseOrderStmt.executeUpdate();

			// get the previous IDs
			res = addPurchaseOrderStmt.getGeneratedKeys();
			if (res.next()) {
				last_inserted_id = res.getInt(1);
			}

			// Add Purchase Order Product Credentials
			List<PurchaseOrderProduct> poProList = new ArrayList<PurchaseOrderProduct>();
			poProList = newPurchaseOrder.getPoProduct();

			for (PurchaseOrderProduct poProduct : poProList) {
				PreparedStatement addPOPStmt = null;
				addPOPStmt = newConn.prepareStatement(
						"INSERT INTO `purchase_order_products` (`purchase_order`,`quantity`,`ex_unit_price`,`prdouct`)\r\n"
								+ "VALUES('" + last_inserted_id + "','" + poProduct.getQty() + "','"
								+ poProduct.getUnitPrice() + "','" + poProduct.getProduct().getId() + "');");
				addPOPStmt.executeUpdate();
			}

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "Purchase order Added Successfully.");

		} catch (Exception e) {
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		} finally {
			try {
				addPurchaseOrderStmt.close();
				conn.closeCon();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	// METHOD TO GET MAX PURCHASE ID
	public GenericResult getMaxPurchaseOrderId() {
		try {
			newConn = conn.getCon();
			stmt = newConn.prepareStatement("");
			res = stmt.executeQuery();
			List<PurchaseOrder> purchaseOrderList = new ArrayList<PurchaseOrder>();
			while (res.next()) {

				PurchaseOrder purOrder = new PurchaseOrder();

				purOrder.setId(res.getInt("id"));
				purchaseOrderList.add(purOrder);
			}
			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "Retriveed successfully", purchaseOrderList, "",
					" ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		}

	}

}
