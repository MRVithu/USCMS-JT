package com.vithu.uscms.entities;

import java.util.List;

/**
 * @author M.Vithusanth
 * @CreatedOn 27th April 2018
 * @Purpose Purchase Entity Class
 */
public class Purchase {
	private Integer id;
	private String code;
	private String tDate;
	private Supplier supplier;
	private Department dept;
	private String note;
	private Employee addedBy;
	private List<PurchaseProduct> purProduct;
	private Payment pay;
	

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

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Employee getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(Employee addedBy) {
		this.addedBy = addedBy;
	}

	public List<PurchaseProduct> getPurProduct() {
		return purProduct;
	}

	public void setPurProduct(List<PurchaseProduct> purProduct) {
		this.purProduct = purProduct;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Payment getPay() {
		return pay;
	}

	public void setPay(Payment pay) {
		this.pay = pay;
	}

}
