package com.learningsession.find_payable_amount;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	
	private static final long serialVersionUID = -4603630411663937603L;
	
	private String name;
	private Date registerDate = new Date();
	private boolean isEmployee = false;
	private boolean isAffiliate = false;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public boolean isEmployee() {
		return isEmployee;
	}

	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}

	public boolean isAffiliate() {
		return isAffiliate;
	}

	public void setAffiliate(boolean isAffiliate) {
		this.isAffiliate = isAffiliate;
	}

	public User() {
		super();
	}
	
	public static User newInstance(String name) {
		return newInstance(name, null, false, false);
	}
	
	public static User newInstance(String name, boolean isEmployee, boolean isAffiliate) {
		return newInstance(name, null, isEmployee, isAffiliate);
	}
	
	public static User newInstance(String name, Date registerDate, boolean isEmployee, boolean isAffiliate) {
		User u = new User();
		u.setName(name);
		u.setRegisterDate(registerDate == null ? new Date() : registerDate);
		u.setEmployee(isEmployee);
		u.setAffiliate(isAffiliate);
		return u;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(name.toUpperCase() + "\n");
		builder.append("Employe [" + (isEmployee ? "Y" : "N") +"]" + "\n");
		builder.append("Affiliate [" + (isAffiliate ? "Y" : "N") +"]" + "\n");
		builder.append("Reg.Date " + registerDate);
		return builder.toString();
	}

}
