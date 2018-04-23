package com.vithu.uscms.service.otherMaster;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.vithu.uscms.entities.Bank;
import com.vithu.uscms.others.DBConnection;
import com.vithu.uscms.others.GenericResult;
import com.vithu.uscms.others.JsonFormer;
import com.vithu.uscms.others.MessageConstant;

/**
 * @author M.Vithusanth
 * @CreatedOn 23rd April 2018
 * @Purpose Services for Add/Edit/Delete/View Single/View All Banks
 */
public class BankManagementService {

	private DBConnection conn = new DBConnection();

	private PreparedStatement stmt;

	private Connection newConn;

	private ResultSet res;

	// METHOD TO VIEW ALL BRANDS
	public GenericResult getAllBrands() {
		try {
			newConn = conn.getCon();
			stmt = newConn.prepareStatement(
					"SELECT * FROM `bank_accs`  WHERE `is_deleted`=FALSE;");
			res = stmt.executeQuery();
			List<Bank> bankList = new ArrayList<Bank>();
			while (res.next()) {
				System.out.println("id : " + res.getInt("id"));
				Bank bank = new Bank();
				bank.setId(res.getInt("id"));
				bank.setName(res.getString("name"));
				bank.setNumber(res.getString("number"));
				bank.setBankName(res.getString("bank"));
				bankList.add(bank);
			}

			GenericResult rs = new GenericResult();
			rs.setStatus(true);
			rs.setResult(bankList);

			return new GenericResult(true, MessageConstant.MSG_SUCCESS, "Retriveed successfully", bankList,
					JsonFormer.form(rs), " ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new GenericResult(false, MessageConstant.MSG_FAILED, e.getMessage());
		}

	}
}
