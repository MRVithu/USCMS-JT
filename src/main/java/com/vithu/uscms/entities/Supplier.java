package com.vithu.uscms.entities;

/**
 * @author M.Vithusanth
 * @CreatedOn 23rd April 2018
 * @Purpose Supplier Entity Class
 */
public class Supplier {
	private Integer id;
	private User user;
	private String company;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}
