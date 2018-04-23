package com.vithu.uscms.service.otherMaster;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.vithu.uscms.entities.Counter;
import com.vithu.uscms.entities.Department;
import com.vithu.uscms.others.DBConnection;
import com.vithu.uscms.others.GenericResult;
import com.vithu.uscms.others.JsonFormer;
import com.vithu.uscms.others.MessageConstant;

/**
 * @author M.Vithusanth
 * @CreatedOn 23rd April 2018
 * @Purpose Controller for Add/Edit/Delete/View Single/View All Counters
 */

@Controller
public class CounterManagementService {
	private DBConnection conn = new DBConnection();

	private PreparedStatement stmt;

	private Connection newConn;

	private ResultSet res;

	// METHOD TO VIEW ALL COUNTERS
	public GenericResult getAllCounters() {
		try {
			newConn = conn.getCon();
			stmt = newConn
					.prepareStatement("SELECT c.`id`, c.`name`, c.`description`, d.`name` AS dept FROM `counters` c\r\n"
							+ "LEFT JOIN `departments` d\r\n" + "ON d.`id`=c.`dept`\r\n"
							+ "WHERE c.`is_deleted`=FALSE;\r\n" + "");
			res = stmt.executeQuery();

			List<Counter> counterList = new ArrayList<Counter>();

			while (res.next()) {
				System.out.println("id : " + res.getInt("id"));
				Counter counter = new Counter();
				counter.setId(res.getInt("id"));
				counter.setName(res.getString("name"));
				counter.setDescription(res.getString("description"));

				Department dept = new Department();
				dept.setName(res.getString("dept"));
				counter.setDept(dept);

				counterList.add(counter);
			}

			GenericResult rs = new GenericResult();
			rs.setStatus(true);
			rs.setResult(counterList);

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "Retriveed successfully", counterList,
					JsonFormer.form(rs), " ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		}

	}
}
