package com.twobytes.repair.form;

import java.io.Serializable;

public class ServiceOrderDocForm implements Serializable {

	private static final long serialVersionUID = 1739152947148130593L;
	
	private String serviceOrderID;
	private String serviceOrderDate;
	private String contactName;
	private String tel;
	private String mobileTel;
	private String typeID;
	private String brandID;
	private String type;
	private String brand;
	private String model;
	private String serialNo;
	private String accessories;
	private String problem;
	private String empOpenID;
	private String empOpen;
	
	public String getServiceOrderID() {
		return serviceOrderID;
	}
	public void setServiceOrderID(String serviceOrderID) {
		this.serviceOrderID = serviceOrderID;
	}
	public String getServiceOrderDate() {
		return serviceOrderDate;
	}
	public void setServiceOrderDate(String serviceOrderDate) {
		this.serviceOrderDate = serviceOrderDate;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTypeID() {
		return typeID;
	}
	public void setTypeID(String typeID) {
		this.typeID = typeID;
	}
	public String getBrandID() {
		return brandID;
	}
	public void setBrandID(String brandID) {
		this.brandID = brandID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getAccessories() {
		return accessories;
	}
	public void setAccessories(String accessories) {
		this.accessories = accessories;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public String getEmpOpenID() {
		return empOpenID;
	}
	public void setEmpOpenID(String empOpenID) {
		this.empOpenID = empOpenID;
	}
	public String getEmpOpen() {
		return empOpen;
	}
	public void setEmpOpen(String empOpen) {
		this.empOpen = empOpen;
	}
	public String getMobileTel() {
		return mobileTel;
	}
	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}
}
