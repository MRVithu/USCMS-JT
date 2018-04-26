package com.vithu.uscms.entities;

/**
 * @author M.Vithusanth
 * @CreatedOn 21th April 2018
 * @Purpose Product Entity Class
 */
public class Product {
	private Integer id;
	private String name;
	private String code;
	private String description;
	private Brand brand;
	private ItemType itemType;
	private Double selleingPrice;
	private Double minPrice;
	private Double lastPurchasePrice;
	private Double discount;
	private String size;
	private Employee addedBy;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

	public Double getSelleingPrice() {
		return selleingPrice;
	}

	public void setSelleingPrice(Double selleingPrice) {
		this.selleingPrice = selleingPrice;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getLastPurchasePrice() {
		return lastPurchasePrice;
	}

	public void setLastPurchasePrice(Double lastPurchasePrice) {
		this.lastPurchasePrice = lastPurchasePrice;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Employee getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(Employee addedBy) {
		this.addedBy = addedBy;
	}

}
