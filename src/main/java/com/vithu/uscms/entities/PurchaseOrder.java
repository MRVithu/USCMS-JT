package com.vithu.uscms.entities;

import java.util.List;

/**
 * @author M.Vithusanth
 * @CreatedOn 23rd April 2018
 * @Purpose Purchase Order Entity Class
 */

public class PurchaseOrder {
	private Integer id;
	private String code;
	private String tDate;
	private String expectedDate;
	private Supplier supplier;
	private Department dept;
	private Boolean isClosed;
	private String note;
	private Employee addedBy;
	private List<PurchaseOrderProduct> poProduct;

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

	public String getExpectedDate() {
		return expectedDate;
	}

	public void setExpectedDate(String expectedDate) {
		this.expectedDate = expectedDate;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Boolean getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(Boolean isClosed) {
		this.isClosed = isClosed;
	}

	public List<PurchaseOrderProduct> getPoProduct() {
		return poProduct;
	}

	public void setPoProduct(List<PurchaseOrderProduct> poProduct) {
		this.poProduct = poProduct;
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

}
