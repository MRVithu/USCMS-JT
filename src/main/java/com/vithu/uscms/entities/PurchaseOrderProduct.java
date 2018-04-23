package com.vithu.uscms.entities;

/**
 * @author M.Vithusanth
 * @CreatedOn 23rd April 2018
 * @Purpose Purchase Order Product Entity Class
 */

public class PurchaseOrderProduct {
	private Integer id;
	private Product product;
	private PurchaseOrder purChase;
	private Double qty;
	private Double unitPrice;

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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public PurchaseOrder getPurChase() {
		return purChase;
	}

	public void setPurChase(PurchaseOrder purChase) {
		this.purChase = purChase;
	}

	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

}
