package com.twobytes.repair.form;

import com.twobytes.model.Employee;

public class ServiceOrderForm {
	
	private String serviceOrderID;
	private String serviceOrderDate;
	private Integer serviceType;
	private String refServiceOrder;
	private String refJobID;
	private Integer guaranteeNo;
	private String customerType;
	private String shopCustomerID;
	private String customerID;
	private String deliveryCustomer;
	private String deliveryEmail;
	private String deliveryTel;
	private String deliveryMobileTel;
	private String typeID;
	private Integer brandID;
	private String model;
	private String serialNo;
	private String accessories;
	private String desc;
	private String problem;
	private String appointmentDate;
	private Employee empOpen;
	
	private String productID;
	
	private String startFix;
	private String endFix;
	private String realProblem;
	private String cause;
	private String fixDesc;
	private String costing;
	
	private String[] serviceList;
	private Double[] servicePrice;
	private String issuePart;
	private Double netAmount;
	private String remark;
	
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

	public Integer getServiceType() {
		return serviceType;
	}

	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}
	
	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getShopCustomerID() {
		return shopCustomerID;
	}

	public void setShopCustomerID(String shopCustomerID) {
		this.shopCustomerID = shopCustomerID;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
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

	public String getRefServiceOrder() {
		return refServiceOrder;
	}

	public void setRefServiceOrder(String refServiceOrder) {
		this.refServiceOrder = refServiceOrder;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
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

	public Employee getEmpOpen() {
		return empOpen;
	}

	public void setEmpOpen(Employee empOpen) {
		this.empOpen = empOpen;
	}

	public String getStartFix() {
		return startFix;
	}

	public void setStartFix(String startFix) {
		this.startFix = startFix;
	}

	public String getEndFix() {
		return endFix;
	}

	public void setEndFix(String endFix) {
		this.endFix = endFix;
	}

	public String getRealProblem() {
		return realProblem;
	}

	public void setRealProblem(String realProblem) {
		this.realProblem = realProblem;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getFixDesc() {
		return fixDesc;
	}

	public void setFixDesc(String fixDesc) {
		this.fixDesc = fixDesc;
	}

	public String getCosting() {
		return costing;
	}

	public void setCosting(String costing) {
		this.costing = costing;
	}

	public String[] getServiceList() {
		return serviceList;
	}

	public void setServiceList(String[] serviceList) {
		this.serviceList = serviceList;
	}

	public Double[] getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(Double[] servicePrice) {
		this.servicePrice = servicePrice;
	}
	
	public String getIssuePart() {
		return issuePart;
	}

	public void setIssuePart(String issuePart) {
		this.issuePart = issuePart;
	}

	public Double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
