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
	
	private String serviceList_1;
	private String serviceList_2;
	private String serviceList_3;
	private String serviceList_4;
	private Double servicePrice_1;
	private Double servicePrice_2;
	private Double servicePrice_3;
	private Double servicePrice_4;
	
	private String issuePartCode_1;
	private String issuePartCode_2;
	private String issuePartCode_3;
	private String issuePartCode_4;
	private String issuePartCode_5;
	private String issuePartCode_6;
	private String issuePartCode_7;
	private String issuePartCode_8;
	private String issuePartCode_9;
	private String issuePartCode_10;
	private String issuePartCode_11;
	
	private String issuePartName_1;
	private String issuePartName_2;
	private String issuePartName_3;
	private String issuePartName_4;
	private String issuePartName_5;
	private String issuePartName_6;
	private String issuePartName_7;
	private String issuePartName_8;
	private String issuePartName_9;
	private String issuePartName_10;
	private String issuePartName_11;
	
	private Integer issuePartQty_1;
	private Integer issuePartQty_2;
	private Integer issuePartQty_3;
	private Integer issuePartQty_4;
	private Integer issuePartQty_5;
	private Integer issuePartQty_6;
	private Integer issuePartQty_7;
	private Integer issuePartQty_8;
	private Integer issuePartQty_9;
	private Integer issuePartQty_10;
	private Integer issuePartQty_11;
	
	private Double issuePartPrice_1;
	private Double issuePartPrice_2;
	private Double issuePartPrice_3;
	private Double issuePartPrice_4;
	private Double issuePartPrice_5;
	private Double issuePartPrice_6;
	private Double issuePartPrice_7;
	private Double issuePartPrice_8;
	private Double issuePartPrice_9;
	private Double issuePartPrice_10;
	private Double issuePartPrice_11;
	
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

	public String getServiceList_1() {
		return serviceList_1;
	}

	public void setServiceList_1(String serviceList_1) {
		this.serviceList_1 = serviceList_1;
	}

	public String getServiceList_2() {
		return serviceList_2;
	}

	public void setServiceList_2(String serviceList_2) {
		this.serviceList_2 = serviceList_2;
	}

	public String getServiceList_3() {
		return serviceList_3;
	}

	public void setServiceList_3(String serviceList_3) {
		this.serviceList_3 = serviceList_3;
	}

	public String getServiceList_4() {
		return serviceList_4;
	}

	public void setServiceList_4(String serviceList_4) {
		this.serviceList_4 = serviceList_4;
	}

	public Double getServicePrice_1() {
		return servicePrice_1;
	}

	public void setServicePrice_1(Double servicePrice_1) {
		this.servicePrice_1 = servicePrice_1;
	}

	public Double getServicePrice_2() {
		return servicePrice_2;
	}

	public void setServicePrice_2(Double servicePrice_2) {
		this.servicePrice_2 = servicePrice_2;
	}

	public Double getServicePrice_3() {
		return servicePrice_3;
	}

	public void setServicePrice_3(Double servicePrice_3) {
		this.servicePrice_3 = servicePrice_3;
	}

	public Double getServicePrice_4() {
		return servicePrice_4;
	}

	public void setServicePrice_4(Double servicePrice_4) {
		this.servicePrice_4 = servicePrice_4;
	}

	public String getIssuePartCode_1() {
		return issuePartCode_1;
	}

	public void setIssuePartCode_1(String issuePartCode_1) {
		this.issuePartCode_1 = issuePartCode_1;
	}

	public String getIssuePartCode_2() {
		return issuePartCode_2;
	}

	public void setIssuePartCode_2(String issuePartCode_2) {
		this.issuePartCode_2 = issuePartCode_2;
	}

	public String getIssuePartCode_3() {
		return issuePartCode_3;
	}

	public void setIssuePartCode_3(String issuePartCode_3) {
		this.issuePartCode_3 = issuePartCode_3;
	}

	public String getIssuePartCode_4() {
		return issuePartCode_4;
	}

	public void setIssuePartCode_4(String issuePartCode_4) {
		this.issuePartCode_4 = issuePartCode_4;
	}

	public String getIssuePartCode_5() {
		return issuePartCode_5;
	}

	public void setIssuePartCode_5(String issuePartCode_5) {
		this.issuePartCode_5 = issuePartCode_5;
	}

	public String getIssuePartCode_6() {
		return issuePartCode_6;
	}

	public void setIssuePartCode_6(String issuePartCode_6) {
		this.issuePartCode_6 = issuePartCode_6;
	}

	public String getIssuePartCode_7() {
		return issuePartCode_7;
	}

	public void setIssuePartCode_7(String issuePartCode_7) {
		this.issuePartCode_7 = issuePartCode_7;
	}

	public String getIssuePartCode_8() {
		return issuePartCode_8;
	}

	public void setIssuePartCode_8(String issuePartCode_8) {
		this.issuePartCode_8 = issuePartCode_8;
	}

	public String getIssuePartCode_9() {
		return issuePartCode_9;
	}

	public void setIssuePartCode_9(String issuePartCode_9) {
		this.issuePartCode_9 = issuePartCode_9;
	}

	public String getIssuePartCode_10() {
		return issuePartCode_10;
	}

	public void setIssuePartCode_10(String issuePartCode_10) {
		this.issuePartCode_10 = issuePartCode_10;
	}

	public String getIssuePartCode_11() {
		return issuePartCode_11;
	}

	public void setIssuePartCode_11(String issuePartCode_11) {
		this.issuePartCode_11 = issuePartCode_11;
	}

	public String getIssuePartName_1() {
		return issuePartName_1;
	}

	public void setIssuePartName_1(String issuePartName_1) {
		this.issuePartName_1 = issuePartName_1;
	}

	public String getIssuePartName_2() {
		return issuePartName_2;
	}

	public void setIssuePartName_2(String issuePartName_2) {
		this.issuePartName_2 = issuePartName_2;
	}

	public String getIssuePartName_3() {
		return issuePartName_3;
	}

	public void setIssuePartName_3(String issuePartName_3) {
		this.issuePartName_3 = issuePartName_3;
	}

	public String getIssuePartName_4() {
		return issuePartName_4;
	}

	public void setIssuePartName_4(String issuePartName_4) {
		this.issuePartName_4 = issuePartName_4;
	}

	public String getIssuePartName_5() {
		return issuePartName_5;
	}

	public void setIssuePartName_5(String issuePartName_5) {
		this.issuePartName_5 = issuePartName_5;
	}

	public String getIssuePartName_6() {
		return issuePartName_6;
	}

	public void setIssuePartName_6(String issuePartName_6) {
		this.issuePartName_6 = issuePartName_6;
	}

	public String getIssuePartName_7() {
		return issuePartName_7;
	}

	public void setIssuePartName_7(String issuePartName_7) {
		this.issuePartName_7 = issuePartName_7;
	}

	public String getIssuePartName_8() {
		return issuePartName_8;
	}

	public void setIssuePartName_8(String issuePartName_8) {
		this.issuePartName_8 = issuePartName_8;
	}

	public String getIssuePartName_9() {
		return issuePartName_9;
	}

	public void setIssuePartName_9(String issuePartName_9) {
		this.issuePartName_9 = issuePartName_9;
	}

	public String getIssuePartName_10() {
		return issuePartName_10;
	}

	public void setIssuePartName_10(String issuePartName_10) {
		this.issuePartName_10 = issuePartName_10;
	}

	public String getIssuePartName_11() {
		return issuePartName_11;
	}

	public void setIssuePartName_11(String issuePartName_11) {
		this.issuePartName_11 = issuePartName_11;
	}

	public Integer getIssuePartQty_1() {
		return issuePartQty_1;
	}

	public void setIssuePartQty_1(Integer issuePartQty_1) {
		this.issuePartQty_1 = issuePartQty_1;
	}

	public Integer getIssuePartQty_2() {
		return issuePartQty_2;
	}

	public void setIssuePartQty_2(Integer issuePartQty_2) {
		this.issuePartQty_2 = issuePartQty_2;
	}

	public Integer getIssuePartQty_3() {
		return issuePartQty_3;
	}

	public void setIssuePartQty_3(Integer issuePartQty_3) {
		this.issuePartQty_3 = issuePartQty_3;
	}

	public Integer getIssuePartQty_4() {
		return issuePartQty_4;
	}

	public void setIssuePartQty_4(Integer issuePartQty_4) {
		this.issuePartQty_4 = issuePartQty_4;
	}

	public Integer getIssuePartQty_5() {
		return issuePartQty_5;
	}

	public void setIssuePartQty_5(Integer issuePartQty_5) {
		this.issuePartQty_5 = issuePartQty_5;
	}

	public Integer getIssuePartQty_6() {
		return issuePartQty_6;
	}

	public void setIssuePartQty_6(Integer issuePartQty_6) {
		this.issuePartQty_6 = issuePartQty_6;
	}

	public Integer getIssuePartQty_7() {
		return issuePartQty_7;
	}

	public void setIssuePartQty_7(Integer issuePartQty_7) {
		this.issuePartQty_7 = issuePartQty_7;
	}

	public Integer getIssuePartQty_8() {
		return issuePartQty_8;
	}

	public void setIssuePartQty_8(Integer issuePartQty_8) {
		this.issuePartQty_8 = issuePartQty_8;
	}

	public Integer getIssuePartQty_9() {
		return issuePartQty_9;
	}

	public void setIssuePartQty_9(Integer issuePartQty_9) {
		this.issuePartQty_9 = issuePartQty_9;
	}

	public Integer getIssuePartQty_10() {
		return issuePartQty_10;
	}

	public void setIssuePartQty_10(Integer issuePartQty_10) {
		this.issuePartQty_10 = issuePartQty_10;
	}

	public Integer getIssuePartQty_11() {
		return issuePartQty_11;
	}

	public void setIssuePartQty_11(Integer issuePartQty_11) {
		this.issuePartQty_11 = issuePartQty_11;
	}

	public Double getIssuePartPrice_1() {
		return issuePartPrice_1;
	}

	public void setIssuePartPrice_1(Double issuePartPrice_1) {
		this.issuePartPrice_1 = issuePartPrice_1;
	}

	public Double getIssuePartPrice_2() {
		return issuePartPrice_2;
	}

	public void setIssuePartPrice_2(Double issuePartPrice_2) {
		this.issuePartPrice_2 = issuePartPrice_2;
	}

	public Double getIssuePartPrice_3() {
		return issuePartPrice_3;
	}

	public void setIssuePartPrice_3(Double issuePartPrice_3) {
		this.issuePartPrice_3 = issuePartPrice_3;
	}

	public Double getIssuePartPrice_4() {
		return issuePartPrice_4;
	}

	public void setIssuePartPrice_4(Double issuePartPrice_4) {
		this.issuePartPrice_4 = issuePartPrice_4;
	}

	public Double getIssuePartPrice_5() {
		return issuePartPrice_5;
	}

	public void setIssuePartPrice_5(Double issuePartPrice_5) {
		this.issuePartPrice_5 = issuePartPrice_5;
	}

	public Double getIssuePartPrice_6() {
		return issuePartPrice_6;
	}

	public void setIssuePartPrice_6(Double issuePartPrice_6) {
		this.issuePartPrice_6 = issuePartPrice_6;
	}

	public Double getIssuePartPrice_7() {
		return issuePartPrice_7;
	}

	public void setIssuePartPrice_7(Double issuePartPrice_7) {
		this.issuePartPrice_7 = issuePartPrice_7;
	}

	public Double getIssuePartPrice_8() {
		return issuePartPrice_8;
	}

	public void setIssuePartPrice_8(Double issuePartPrice_8) {
		this.issuePartPrice_8 = issuePartPrice_8;
	}

	public Double getIssuePartPrice_9() {
		return issuePartPrice_9;
	}

	public void setIssuePartPrice_9(Double issuePartPrice_9) {
		this.issuePartPrice_9 = issuePartPrice_9;
	}

	public Double getIssuePartPrice_10() {
		return issuePartPrice_10;
	}

	public void setIssuePartPrice_10(Double issuePartPrice_10) {
		this.issuePartPrice_10 = issuePartPrice_10;
	}

	public Double getIssuePartPrice_11() {
		return issuePartPrice_11;
	}

	public void setIssuePartPrice_11(Double issuePartPrice_11) {
		this.issuePartPrice_11 = issuePartPrice_11;
	}

}
