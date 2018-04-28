package com.vithu.uscms.service.sales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.vithu.uscms.entities.Customer;
import com.vithu.uscms.entities.Department;
import com.vithu.uscms.entities.Employee;
import com.vithu.uscms.entities.PayCheque;
import com.vithu.uscms.entities.Product;
import com.vithu.uscms.entities.Purchase;
import com.vithu.uscms.entities.PurchaseOrder;
import com.vithu.uscms.entities.PurchaseOrderProduct;
import com.vithu.uscms.entities.PurchaseProduct;
import com.vithu.uscms.entities.Sales;
import com.vithu.uscms.entities.SalesProduct;
import com.vithu.uscms.entities.Supplier;
import com.vithu.uscms.entities.User;
import com.vithu.uscms.others.DBConnection;
import com.vithu.uscms.others.GenericResult;
import com.vithu.uscms.others.JsonFormer;
import com.vithu.uscms.others.MessageConstant;

/**
 * @author M.Vithusanth
 * @CreatedOn 28th April 2018
 * @Purpose Services for Sales
 */
public class SalesManagementService {
	private DBConnection conn = new DBConnection();

	private Connection newConn;

	private ResultSet res;
	private ResultSet result;

	// METHOD TO VIEW ALL SALES
	public GenericResult getAllSales() {

		PreparedStatement stmt = null;
		PreparedStatement getSalesProductsStmt = null;

		try {
			newConn = conn.getCon();
			stmt = newConn.prepareStatement(
					"select s.`id`, s.`t_date`, u.`name` as customer, us.`name` as salesOfficer, ua.`name` as addedBy, d.`name` as dept\r\n"
							+ "from `sales` s\r\n" + "left join `customers` c\r\n" + "on s.`customer`=c.`id`\r\n"
							+ "	left join `users` u\r\n" + "	on c.`user_id`=u.`id`\r\n"
							+ "left join `employees` so\r\n" + "on s.`sales_officer`=so.`id`\r\n"
							+ "	LEFT JOIN `users` us\r\n" + "	ON so.`user_id`=us.`id`\r\n"
							+ "left join `employees` ab\r\n" + "on s.`added_by`=ab.`id`\r\n"
							+ "	LEFT JOIN `users` ua\r\n" + "	ON ab.`user_id`=ua.`id`\r\n"
							+ "left join `departments` d\r\n" + "on s.`dept`=d.`id`");
			res = stmt.executeQuery();

			List<Sales> salesList = new ArrayList<Sales>();
			while (res.next()) {
				System.out.println("id : " + res.getInt("id"));

				Sales sales = new Sales();

				sales.setId(res.getInt("id"));
//				sales.setCode(res.getString("code"));
				sales.settDate(res.getString("t_date"));

				Department dept = new Department();
				dept.setName(res.getString("dept"));
				sales.setDept(dept);

				Employee addedBy = new Employee();
				User user1 = new User();
				user1.setName(res.getString("addedBy"));
				addedBy.setUser(user1);
				sales.setAddedBy(addedBy);

				Employee so = new Employee();
				User user2 = new User();
				user2.setName(res.getString("salesOfficer"));
				so.setUser(user2);
				sales.setSalesOfficer(so);
				
				Customer customer = new Customer();
				User user3 = new User();
				user3.setName(res.getString("customer"));
				customer.setUser(user3);
				sales.setCustomer(customer);

				getSalesProductsStmt = newConn.prepareStatement(
						"select so.`id`, p.`id` as proId, p.`name` as proName, p.`code` as proCode, so.`quantity`, so.`unit_price`, so.`total_discount` \r\n"
								+ "from `sale_products` so\r\n" + "left join `products` p\r\n"
								+ "on so.`product`=p.`id`\r\n" + "");
				result = getSalesProductsStmt.executeQuery();

				List<SalesProduct> salesProductList = new ArrayList<SalesProduct>();

				while (result.next()) {
					SalesProduct salesProduct = new SalesProduct();

					salesProduct.setId(result.getInt("proId"));
					salesProduct.setQty(result.getDouble("quantity"));
					salesProduct.setUnitPrice(result.getDouble("unit_price"));
					salesProduct.setTotDiscount(result.getDouble("total_discount"));

					Product product = new Product();
					product.setId(result.getInt("id"));
					product.setName(result.getString("proName"));
					product.setCode(result.getString("proCode"));
					salesProduct.setProduct(product);

					salesProductList.add(salesProduct);
				}

				sales.setSalesProduct(salesProductList);
				salesList.add(sales);
			}

			GenericResult rs = new GenericResult();
			rs.setStatus(true);
			rs.setResult(salesList);

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "Retriveed successfully", salesList,
					JsonFormer.form(rs), " ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		}

	}

	// ADD SALES METHOD
	public GenericResult addSales(Sales newPurchase) {
		PreparedStatement addPurchaseStmt = null;
		PreparedStatement addPayStmt = null;
		PreparedStatement addPayCashStmt = null;
		PreparedStatement addPayCreditStmt = null;
		PreparedStatement addPayChequeStmt = null;

		try {
			int last_inserted_id = 0;
			int lastPayId = 0;

			newConn = conn.getCon();

			// Add payment credentials
			addPayStmt = newConn.prepareStatement(
					"INSERT INTO `payments` (`t_date`, `total`, `added_by`, `dept`)\r\n" + "VALUE('"
							+ newPurchase.gettDate() + "', '" + newPurchase.getPay().getAmount() + "', '"
							+ newPurchase.getAddedBy().getId() + "', '" + newPurchase.getDept().getId() + "');",
					Statement.RETURN_GENERATED_KEYS);
			addPayStmt.executeUpdate();

			// get the previous IDs
			res = addPayStmt.getGeneratedKeys();
			if (res.next()) {
				lastPayId = res.getInt(1);
			}

			if (newPurchase.getPay().getPayCash().getAmount() > 0) {
				// Add pay cash credentials
				addPayCashStmt = newConn.prepareStatement("insert into `pay_cash` (`pay`, `amount`)\r\n" + "value('"
						+ lastPayId + "', '" + newPurchase.getPay().getPayCash().getAmount() + "');");
				addPayCashStmt.executeUpdate();
			}

			if (newPurchase.getPay().getPayCredit().getAmount() > 0) {
				// Add pay credit credentials
				addPayCreditStmt = newConn.prepareStatement("INSERT INTO `pay_credit` (`pay`, `amount`)\r\n" + "VALUE('"
						+ lastPayId + "', '" + newPurchase.getPay().getPayCredit().getAmount() + "');");
				addPayCreditStmt.executeUpdate();
			}

			// Add pay cheque Credentials
			List<PayCheque> chequeList = new ArrayList<PayCheque>();
			chequeList = newPurchase.getPay().getPayCheques();
			for (PayCheque payCheque : chequeList) {

				addPayChequeStmt = newConn.prepareStatement(
						"INSERT INTO `pay_cheque` (`pay`, `number`, `bank`,`change_date`,`amount`)\r\n" + "VALUE ('"
								+ lastPayId + "', '" + payCheque.getNumber() + "', '"
								+ payCheque.getBank().getBankName() + "', '" + payCheque.getChequeDate() + "', '"
								+ payCheque.getAmount() + "');");
				addPayChequeStmt.executeUpdate();
			}

			// Add purchase credentials
			addPurchaseStmt = newConn.prepareStatement(
					"INSERT INTO `purchases`(`code`, `supplier`, `t_date`, `added_by`,`dept`, `pay`)\r\n" + "VALUES('"
							+ newPurchase.getCode() + "','', '" + newPurchase.gettDate() + "', '"
							+ newPurchase.getAddedBy().getId() + "', '" + newPurchase.getDept().getId() + "' ,'"
							+ lastPayId + "');",
					Statement.RETURN_GENERATED_KEYS);
			addPurchaseStmt.executeUpdate();

			// get the previous IDs
			res = addPurchaseStmt.getGeneratedKeys();
			if (res.next()) {
				last_inserted_id = res.getInt(1);
			}

			// Add Purchase Product Credentials
			List<PurchaseProduct> purProductsList = new ArrayList<PurchaseProduct>();
			// purProductsList = newPurchase.getPurProduct();

			for (PurchaseProduct purProduct : purProductsList) {
				PreparedStatement addPOPStmt = null;
				addPOPStmt = newConn.prepareStatement(
						"insert into `purchase_products`(`purchase`, `product`, `quantity`, `unit_price`)\r\n"
								+ "value ('" + last_inserted_id + "','" + purProduct.getProduct().getId() + "', '"
								+ purProduct.getQty() + "', '" + purProduct.getUnitPrice() + "');");
				addPOPStmt.executeUpdate();
			}

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "Purchase Added Successfully.");

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
