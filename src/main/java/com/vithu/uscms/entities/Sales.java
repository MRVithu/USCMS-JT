package com.vithu.uscms.entities;

import java.util.List;

/**
 * @author M.Vithusanth
 * @CreatedOn 28th April 2018
 * @Purpose Sales Entity Class
 */
public class Sales {
	private Integer id;
	private String code;
	private String tDate;
	private Customer customer;
	private Department dept;
	private Employee addedBy;
	private List<SalesProduct> salesProduct;
	private Payment pay;
	private Employee salesOfficer;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String gettDate() {
		return tDate;
	}

	public void settDate(String tDate) {
		this.tDate = tDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

	public List<SalesProduct> getSalesProduct() {
		return salesProduct;
	}

	public void setSalesProduct(List<SalesProduct> salesProduct) {
		this.salesProduct = salesProduct;
	}

	public Payment getPay() {
		return pay;
	}

	public void setPay(Payment pay) {
		this.pay = pay;
	}

	public Employee getSalesOfficer() {
		return salesOfficer;
	}

	public void setSalesOfficer(Employee salesOfficer) {
		this.salesOfficer = salesOfficer;
	}

}
