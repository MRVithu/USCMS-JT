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
import com.vithu.uscms.entities.Sales;
import com.vithu.uscms.entities.SalesProduct;
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
					"select s.`id`,s.`code`, s.`t_date`, u.`name` as customer, us.`name` as salesOfficer, ua.`name` as addedBy, d.`name` as dept\r\n"
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
				sales.setCode(res.getString("code"));
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
						"SELECT so.`id`, p.`id` AS proId, p.`name` AS proName, p.`code` AS proCode, so.`quantity`, so.`unit_price`, so.`total_discount` \r\n"
								+ "FROM `sale_products` so\r\n" + "LEFT JOIN `products` p\r\n"
								+ "ON so.`product`=p.`id`\r\n" + "WHERE so.`sale`='" + res.getInt("id") + "';");
				result = getSalesProductsStmt.executeQuery();

				List<SalesProduct> salesProductList = new ArrayList<SalesProduct>();

				while (result.next()) {
					SalesProduct salesProduct = new SalesProduct();

					salesProduct.setId(result.getInt("id"));
					salesProduct.setQty(result.getDouble("quantity"));
					salesProduct.setUnitPrice(result.getDouble("unit_price"));
					salesProduct.setTotDiscount(result.getDouble("total_discount"));

					Product product = new Product();
					product.setId(result.getInt("proId"));
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
	public GenericResult addSales(Sales newSales) {
		PreparedStatement addSalesStmt = null;
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
							+ newSales.gettDate() + "', '" + newSales.getPay().getAmount() + "', '"
							+ newSales.getAddedBy().getId() + "', '" + newSales.getDept().getId() + "');",
					Statement.RETURN_GENERATED_KEYS);
			addPayStmt.executeUpdate();

			// get the previous IDs
			res = addPayStmt.getGeneratedKeys();
			if (res.next()) {
				lastPayId = res.getInt(1);
			}

			if (newSales.getPay().getPayCash().getAmount() > 0) {
				// Add pay cash credentials
				addPayCashStmt = newConn.prepareStatement("insert into `pay_cash` (`pay`, `amount`)\r\n" + "value('"
						+ lastPayId + "', '" + newSales.getPay().getPayCash().getAmount() + "');");
				addPayCashStmt.executeUpdate();
			}

			if (newSales.getPay().getPayCredit().getAmount() > 0) {
				// Add pay credit credentials
				addPayCreditStmt = newConn.prepareStatement("INSERT INTO `pay_credit` (`pay`, `amount`)\r\n" + "VALUE('"
						+ lastPayId + "', '" + newSales.getPay().getPayCredit().getAmount() + "');");
				addPayCreditStmt.executeUpdate();
			}

			// Add pay cheque Credentials
			List<PayCheque> chequeList = new ArrayList<PayCheque>();
			chequeList = newSales.getPay().getPayCheques();
			for (PayCheque payCheque : chequeList) {

				addPayChequeStmt = newConn.prepareStatement(
						"INSERT INTO `pay_cheque` (`pay`, `number`, `bank`,`change_date`,`amount`)\r\n" + "VALUE ('"
								+ lastPayId + "', '" + payCheque.getNumber() + "', '"
								+ payCheque.getBank().getBankName() + "', '" + payCheque.getChequeDate() + "', '"
								+ payCheque.getAmount() + "');");
				addPayChequeStmt.executeUpdate();
			}

			// Add sales credentials
			addSalesStmt = newConn.prepareStatement(
					"INSERT INTO `sales` (`pay`, `code`, `customer`, `t_date`, `sales_officer`, `added_by`, `dept`) VALUE('"
							+ lastPayId + "','"+newSales.getCode()+"', '" + newSales.getCustomer().getId() + "', '" + newSales.gettDate()
							+ "', '" + newSales.getSalesOfficer().getId() + "', '" + newSales.getAddedBy().getId()
							+ "', '" + newSales.getDept().getId() + "');",
					Statement.RETURN_GENERATED_KEYS);
			addSalesStmt.executeUpdate();

			// get the previous IDs
			res = addSalesStmt.getGeneratedKeys();
			if (res.next()) {
				last_inserted_id = res.getInt(1);
			}

			// Add Sales Product Credentials
			List<SalesProduct> salesProductsList = new ArrayList<SalesProduct>();
			salesProductsList = newSales.getSalesProduct();

			for (SalesProduct salesProduct : salesProductsList) {
				PreparedStatement addSPStmt = null;
				System.out.println();

				addSPStmt = newConn.prepareStatement(
						"INSERT INTO `sale_products` (`sale`, `product`, `quantity`, `unit_price`) VALUE('"
								+ last_inserted_id + "', '" + salesProduct.getProduct().getId() + "', '"
								+ salesProduct.getQty() + "', '" + salesProduct.getUnitPrice() + "'); ");
				addSPStmt.executeUpdate();
			}

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "Sales Added Successfully.");

		} catch (Exception e) {
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		} finally {
			try {
				addSalesStmt.close();
				conn.closeCon();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	// METHOD TO GET MAX SALES ID
	public GenericResult getMaxSalesId() {
		PreparedStatement stmt = null;
		try {
			newConn = conn.getCon();
			stmt = newConn.prepareStatement("SELECT * FROM `sales` ORDER BY id DESC LIMIT 0, 1;");
			res = stmt.executeQuery();

			GenericResult rs = new GenericResult();
			rs.setStatus(true);

			while (res.next()) {
				rs.setResult(res.getInt("id"));
			}

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "Retriveed successfully", JsonFormer.form(rs));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		}

	}

}
