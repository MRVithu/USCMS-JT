package com.vithu.uscms.entities;

/**
 * @author M.Vithusanth
 * @CreatedOn 21th April 2018
 * @Purpose Product type Entity Class
 */
public class ProductType {
	private Integer id;
	private String name;
	private String description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Employee getAddedBy() {
		return addedBy;
	}
	public void setAddedBy(Employee addedBy) {
		this.addedBy = addedBy;
	}

}
