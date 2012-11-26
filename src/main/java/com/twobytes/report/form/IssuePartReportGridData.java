package com.twobytes.report.form;

public class IssuePartReportGridData {
	
	private String serviceOrderID;
	private String cus_name;
	private String cus_surname;
	private String fixEmp_name;
	private String fixEmp_surname;
	private String serviceOrderDate;
	private String returnDate;
	private Integer quantity;
	private Double totalPrice;
	
	public String getServiceOrderID() {
		return serviceOrderID;
	}
	public void setServiceOrderID(String serviceOrderID) {
		this.serviceOrderID = serviceOrderID;
	}
	public String getCus_name() {
		return cus_name;
	}
	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}
	public String getCus_surname() {
		return cus_surname;
	}
	public void setCus_surname(String cus_surname) {
		this.cus_surname = cus_surname;
	}
	public String getFixEmp_name() {
		return fixEmp_name;
	}
	public void setFixEmp_name(String fixEmp_name) {
		this.fixEmp_name = fixEmp_name;
	}
	public String getFixEmp_surname() {
		return fixEmp_surname;
	}
	public void setFixEmp_surname(String fixEmp_surname) {
		this.fixEmp_surname = fixEmp_surname;
	}
	public String getServiceOrderDate() {
		return serviceOrderDate;
	}
	public void setServiceOrderDate(String serviceOrderDate) {
		this.serviceOrderDate = serviceOrderDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}