package com.vithu.uscms.service.purchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.vithu.uscms.entities.Product;
import com.vithu.uscms.entities.PurchaseOrder;
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

	private PreparedStatement stmt;

	private Connection newConn;

	private ResultSet res;

	// METHOD TO VIEW ALL PURCHASE ORDERS
	public GenericResult getAllPurchaseOrders() {
		try {
			newConn = conn.getCon();
			stmt = newConn.prepareStatement(
					"SELECT po.`id`,po.`t_date`,u.`name` AS supplier, po.`expected_date`,po.`code`, po.`is_closed` \r\n"
							+ "FROM `purchase_order` po\r\n" + "LEFT JOIN `suppliers` s\r\n"
							+ "ON s.`id`=po.`supplier`\r\n" + "	LEFT JOIN `users` u\r\n" + "	ON u.`id`=s.`user_id`");
			res = stmt.executeQuery();

			List<PurchaseOrder> purchaseOrderList = new ArrayList<PurchaseOrder>();
			while (res.next()) {
				System.out.println("id : " + res.getInt("id"));

				PurchaseOrder purOrder = new PurchaseOrder();

				purOrder.setId(res.getInt("id"));
				purOrder.setCode(res.getString("code"));
//				purOrder.setExpectedDate(res.getString("expected_date"));
//				purOrder.settDate(res.getString("t_date"));
				purOrder.setIsClosed(res.getBoolean("is_closed"));

				Supplier supplier = new Supplier();
				User user = new User();
				user.setName(res.getString("supplier"));

				supplier.setUser(user);
				purOrder.setSupplier(supplier);

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

}
