package com.vithu.uscms.session;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.vithu.uscms.entities.Customer;
import com.vithu.uscms.entities.Employee;

/**
 * @author M.Vithusanth
 * @CreatedOn 20th April 2018
 * @Purpose Holds the information about the current user
 */
public class CurrentUser {

	private Employee employee;
	private Customer customer;
	private String token;
	private Date tokenExpiryDate;
	private Map<String, Boolean> authorityMap = new HashMap<String, Boolean>();

	// *************************************************************
	// ** CONSTRUCTORS
	// *************************************************************

	// *************************************************************
	// ** GETTERS AND SETTERS
	// *************************************************************

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTokenExpiryDate() {
		return tokenExpiryDate;
	}

	public void setTokenExpiryDate(Date tokenExpiryDate) {
		this.tokenExpiryDate = tokenExpiryDate;
	}

	public Map<String, Boolean> getAuthorityMap() {
		return authorityMap;
	}

	public void setAuthorityMap(Map<String, Boolean> authorityMap) {
		this.authorityMap = authorityMap;
	}

}
