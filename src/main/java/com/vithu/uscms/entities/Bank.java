package com.vithu.uscms.entities;

/**
 * @author M.Vithusanth
 * @CreatedOn 23rd April 2018
 * @Purpose Bank Entity Class
 */
public class Bank {
	private Integer id;
	private String name;
	private String number;
	private String bankName;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	

}
