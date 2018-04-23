package com.vithu.uscms.service.otherMaster;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.vithu.uscms.entities.Department;
import com.vithu.uscms.others.DBConnection;
import com.vithu.uscms.others.GenericResult;
import com.vithu.uscms.others.JsonFormer;
import com.vithu.uscms.others.MessageConstant;

/**
 * @author M.Vithusanth
 * @CreatedOn 23rd April 2018
 * @Purpose Services for Add/Edit/Delete/View Single/View All Departments
 */
public class DepartmentManagementService {
	private DBConnection conn = new DBConnection();

	private PreparedStatement stmt;

	private Connection newConn;

	private ResultSet res;

	// METHOD TO VIEW ALL DEPARTMENTS
	public GenericResult getAllDepartments() {
		try {
			newConn = conn.getCon();
			stmt = newConn.prepareStatement("SELECT * FROM `departments`  WHERE `is_deleted`=FALSE;");
			res = stmt.executeQuery();
			List<Department> departmentList = new ArrayList<Department>();
			while (res.next()) {
				System.out.println("id : " + res.getInt("id"));
				Department dept = new Department();
				dept.setId(res.getInt("id"));
				dept.setName(res.getString("name"));
				dept.setType(res.getString("type"));
				dept.setDescription(res.getString("description"));
				departmentList.add(dept);
			}

			GenericResult rs = new GenericResult();
			rs.setStatus(true);
			rs.setResult(departmentList);

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "Retriveed successfully", departmentList,
					JsonFormer.form(rs), " ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		}

	}
}
