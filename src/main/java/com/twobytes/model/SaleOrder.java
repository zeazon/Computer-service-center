package com.twobytes.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="saleOrder")
public class SaleOrder extends BaseColumn implements Serializable {

	private static final long serialVersionUID = -7844654829567099062L;

	private Integer saleOrderID;
	private Date saleDate;
	private Customer customer;
	private Product product;
	private Employee employee;
	
	@Id
    @Column(name="saleOrderID")
    @GeneratedValue
	public Integer getSaleOrderID() {
		return saleOrderID;
	}
	
	public void setSaleOrderID(Integer saleOrderID) {
		this.saleOrderID = saleOrderID;
	}
	
	@Column(name="saleDate")
	public Date getSaleDate() {
		return saleDate;
	}
	
	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}
	
	@ManyToOne
	@JoinColumn(name="customerID")
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@OneToOne
	@JoinColumn(name="productID")
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	@OneToOne
	@JoinColumn(name="employeeID")
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
