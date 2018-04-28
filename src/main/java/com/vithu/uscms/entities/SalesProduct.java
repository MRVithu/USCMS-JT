package com.vithu.uscms.entities;

/**
 * @author M.Vithusanth
 * @CreatedOn 28th April 2018
 * @Purpose Sales Product Entity Class
 */

public class SalesProduct {
	private Integer id;
	private Product product;
	private Sales sales;
	private Double qty;
	private Double unitPrice;
	private Double totDiscount;
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

	public Sales getSales() {
		return sales;
	}

	public void setSales(Sales sales) {
		this.sales = sales;
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

	public Double getTotGiven() {
		return totGiven;
	}

	public void setTotGiven(Double totGiven) {
		this.totGiven = totGiven;
	}

}
