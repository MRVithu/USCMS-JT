package com.vithu.uscms.entities;

import java.util.List;

/**
 * @author M.Vithusanth
 * @CreatedOn 20th April 2018
 * @Purpose User role Entity Class
 */
public class UserRole {
	private Integer id;
	private String name;
	private String description;
	private List<UserAuthority> authorityList;

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

	public List<UserAuthority> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<UserAuthority> authorityList) {
		this.authorityList = authorityList;
	}

}
