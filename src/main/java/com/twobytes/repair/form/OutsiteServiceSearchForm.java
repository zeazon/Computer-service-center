package com.twobytes.repair.form;

public class OutsiteServiceSearchForm {
	private String name;
	private String surname;
	private String date;
	private String type;
	private String serialNo;
	private String refOutsiteJobID;
	private String outsiteCompanyID;
	private String transportCompanyID;
	private String status;
	
	public String getTransportCompanyID() {
		return transportCompanyID;
	}
	public void setTransportCompanyID(String transportCompanyID) {
		this.transportCompanyID = transportCompanyID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getRefOutsiteJobID() {
		return refOutsiteJobID;
	}
	public void setRefOutsiteJobID(String refOutsiteJobID) {
		this.refOutsiteJobID = refOutsiteJobID;
	}
	public String getOutsiteCompanyID() {
		return outsiteCompanyID;
	}
	public void setOutsiteCompanyID(String outsiteCompanyID) {
		this.outsiteCompanyID = outsiteCompanyID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}