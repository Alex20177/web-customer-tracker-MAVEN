package com.customerTracker.entities;

import lombok.Data;

@Data
public class Customer {
	
	private int id;
	private String first_name;
	private String last_name;
	private String email;
	
	public Customer() {
	}//Close default constructor
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email
				+ "]";
	}//CLose toString method.
	
}//Close Customer class.
