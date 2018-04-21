package com.vithu.uscms.entities;

/**
 * @author M.Vithusanth
 * @CreatedOn 20th April 2018
 * @Purpose Brand Entity Class
 */
public class Brand {
	 private Integer id;
	 private String name;
	 private String description;
	//*************************************************************
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
	    
	    
}
