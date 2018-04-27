package com.vithu.uscms.entities;

/**
 * @author M.Vithusanth
 * @CreatedOn 27th April 2018
 * @Purpose Purchase Product Entity Class
 */

public class PurchaseProduct {
	private Integer id;
	private Product product;
	private Purchase purchase;
	private Double qty;
	private Double unitPrice;
	private Double totDiscount;
	private String expDate;
	private Double totGiven;

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

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
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

	public Double getTotDiscount() {
		return totDiscount;
	}

	public void setTotDiscount(Double totDiscount) {
		this.totDiscount = totDiscount;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public Double getTotGiven() {
		return totGiven;
	}

	public void setTotGiven(Double totGiven) {
		this.totGiven = totGiven;
	}

}
