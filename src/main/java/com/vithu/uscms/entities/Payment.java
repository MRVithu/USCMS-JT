package com.vithu.uscms.entities;

import java.util.List;

/**
 * @author M.Vithusanth
 * @CreatedOn 28th April 2018
 * @Purpose Payment Entity Class
 */

public class Payment {
	private Integer id;
	private String tDate;
	private Department dept;
	private Employee addedBy;
	private Double amount;
	private List<PayCheque> payCheques;
	private PayCash payCash;
	private PayCredit payCredit;

	// *************************************************************
	// ** CONSTRUCTORS
	// *************************************************************

	// *************************************************************
	// ** GETTERS AND SETTERS
	// *************************************************************

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String gettDate() {
		return tDate;
	}

	public void settDate(String tDate) {
		this.tDate = tDate;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public Employee getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(Employee addedBy) {
		this.addedBy = addedBy;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public List<PayCheque> getPayCheques() {
		return payCheques;
	}

	public void setPayCheques(List<PayCheque> payCheques) {
		this.payCheques = payCheques;
	}

	public PayCash getPayCash() {
		return payCash;
	}

	public void setPayCash(PayCash payCash) {
		this.payCash = payCash;
	}

	public PayCredit getPayCredit() {
		return payCredit;
	}

	public void setPayCredit(PayCredit payCredit) {
		this.payCredit = payCredit;
	}

}
