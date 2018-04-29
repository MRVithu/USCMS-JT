package com.vithu.uscms.entities;

/**
 * @author M.Vithusanth
 * @CreatedOn 28th April 2018
 * @Purpose Pay Cash Entity Class
 */
public class PayCash {
	private Integer id;
	private Payment pay;
	private Double amount;

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

	public Payment getPay() {
		return pay;
	}

	public void setPay(Payment pay) {
		this.pay = pay;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	

}
