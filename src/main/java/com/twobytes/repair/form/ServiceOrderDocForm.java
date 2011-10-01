package com.twobytes.repair.form;

import java.io.Serializable;

public class ServiceOrderDocForm implements Serializable {

	private static final long serialVersionUID = 1739152947148130593L;
	
	private String serviceOrderID;
	private String serviceOrderDate;
	private String serviceOrderTime;
	private String appointmentDate;
	private Integer serviceType;
	private String refServiceOrder;
	private String refJobID;
	private Integer guaranteeNo;
	private String customerID;
	private String name;
	private String address;
	private String subdistrict;
	private String district;
	private String province;
	private String zipcode;
	private String email;
	private String tel;
	private String mobileTel;
	private String deliveryCustomer;
	private String deliveryEmail;
	private String deliveryTel;
	private String deliveryMobileTel;
	private String typeID;
	private String brandID;
	private String type;
	private String brand;
	private String model;
	private String serialNo;
	private String accessories;
	private String desc;
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
	public String getServiceOrderTime() {
		return serviceOrderTime;
	}
	public void setServiceOrderTime(String serviceOrderTime) {
		this.serviceOrderTime = serviceOrderTime;
	}
	public String getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public Integer getServiceType() {
		return serviceType;
	}
	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}
	public String getRefServiceOrder() {
		return refServiceOrder;
	}
	public void setRefServiceOrder(String refServiceOrder) {
		this.refServiceOrder = refServiceOrder;
	}
	public String getRefJobID() {
		return refJobID;
	}
	public void setRefJobID(String refJobID) {
		this.refJobID = refJobID;
	}
	public Integer getGuaranteeNo() {
		return guaranteeNo;
	}
	public void setGuaranteeNo(Integer guaranteeNo) {
		this.guaranteeNo = guaranteeNo;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSubdistrict() {
		return subdistrict;
	}
	public void setSubdistrict(String subdistrict) {
		this.subdistrict = subdistrict;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
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
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
