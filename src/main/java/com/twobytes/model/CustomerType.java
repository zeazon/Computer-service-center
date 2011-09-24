package com.twobytes.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customerType")
public class CustomerType extends BaseColumn implements Serializable {

	private static final long serialVersionUID = -6858666774892470054L;
	private String customerTypeID;
	private String name;
	
	@Id
	@Column(name="customerTypeID")
	public String getCustomerTypeID() {
		return customerTypeID;
	}
	
	public void setCustomerTypeID(String customerTypeID) {
		this.customerTypeID = customerTypeID;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
