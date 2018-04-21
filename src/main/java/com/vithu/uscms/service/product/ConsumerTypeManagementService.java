package com.vithu.uscms.service.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.vithu.uscms.entities.ConsumerType;
import com.vithu.uscms.others.DBConnection;
import com.vithu.uscms.others.GenericResult;
import com.vithu.uscms.others.JsonFormer;
import com.vithu.uscms.others.MessageConstant;

/**
 * @author M.Vithusanth
 * @CreatedOn 21th April 2018
 * @Purpose Services for Add/Edit/Delete/View Single/View All Consumer types
 */
public class ConsumerTypeManagementService {
	private DBConnection conn = new DBConnection();

	private PreparedStatement stmt;

	private Connection newConn;

	private ResultSet res;

	// METHOD TO VIEW ALL CONSUMER TYPES
	public GenericResult getAllConsumerTypes() {
		try {
			newConn = conn.getCon();
			stmt = newConn.prepareStatement(
					"SELECT * FROM `pro_brands`  WHERE `is_deleted`=FALSE;");
			res = stmt.executeQuery();
			List<ConsumerType> consumerTypeList = new ArrayList<ConsumerType>();
			while (res.next()) {
				System.out.println("id : " + res.getInt("id"));
				ConsumerType consumeType = new ConsumerType();
				consumeType.setId(res.getInt("id"));
				consumeType.setName(res.getString("name"));
				consumerTypeList.add(consumeType);
			}

			GenericResult rs = new GenericResult();
			rs.setStatus(true);
			rs.setResult(consumerTypeList);

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "retriveed successfully", consumerTypeList,
					JsonFormer.form(rs), " ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		}

	}
}
