package com.twobytes.repair.form;

import com.twobytes.model.Customer;

public class ServiceOrderGridData {
	private String serviceOrderID;
	private String serviceOrderDate;
	private String serviceType;
	private String appointmentDate;
	private String name;
	private String surname;
	private String fullName;
	private String email;
	private String tel;
	private String mobileTel;
	private String deliveryCustomer;
	private String deliveryEmail;
	private String deliveryTel;
	private String deliveryMobileTel;
	private String productID;
	private String typeID;
	private String type;
	private Integer brandID;
	private String brand;
	private Integer modelID;
	private String model;
	private String serialNo;
	private String accessories;
	private String description;
	private String problem;
	private String status;
	private String empOpen;
	private String empFix;
	private Integer cannotMakeContact;
	private String remark;
	
	private Customer customer;
	private String customerFullAddress;
	
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMobileTel() {
		return mobileTel;
	}
	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
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
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getCustomerFullAddress() {
		return customerFullAddress;
	}
	public void setCustomerFullAddress(String customerFullAddress) {
		this.customerFullAddress = customerFullAddress;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEmpOpen() {
		return empOpen;
	}
	public void setEmpOpen(String empOpen) {
		this.empOpen = empOpen;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDeliveryCustomer() {
		return deliveryCustomer;
	}
	public void setDeliveryCustomer(String deliveryCustomer) {
		this.deliveryCustomer = deliveryCustomer;
	}
	public String getDeliveryEmail() {
		return deliveryEmail;
	}
	public void setDeliveryEmail(String deliveryEmail) {
		this.deliveryEmail = deliveryEmail;
	}
	public String getDeliveryTel() {
		return deliveryTel;
	}
	public void setDeliveryTel(String deliveryTel) {
		this.deliveryTel = deliveryTel;
	}
	public String getDeliveryMobileTel() {
		return deliveryMobileTel;
	}
	public void setDeliveryMobileTel(String deliveryMobileTel) {
		this.deliveryMobileTel = deliveryMobileTel;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getEmpFix() {
		return empFix;
	}
	public void setEmpFix(String empFix) {
		this.empFix = empFix;
	}
	public String getTypeID() {
		return typeID;
	}
	public void setTypeID(String typeID) {
		this.typeID = typeID;
	}
	public Integer getBrandID() {
		return brandID;
	}
	public void setBrandID(Integer brandID) {
		this.brandID = brandID;
	}
	public Integer getModelID() {
		return modelID;
	}
	public void setModelID(Integer modelID) {
		this.modelID = modelID;
	}
	public Integer getCannotMakeContact() {
		return cannotMakeContact;
	}
	public void setCannotMakeContact(Integer cannotMakeContact) {
		this.cannotMakeContact = cannotMakeContact;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
