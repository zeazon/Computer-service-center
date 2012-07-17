package com.twobytes.repair.form;

import com.twobytes.model.ServiceOrder;

public class OutsiteServiceForm {
	private String outsiteServiceID;
	private String outsiteServiceDate;
	private String serviceType;
	private String accessories;
	private String problem;
	private String serviceOrderID;
	private String refOutsiteJobID;
	private Integer outsiteCompanyID;
	private String outsiteCompanyName;
	private Integer transportCompanyID;
	private String transportCompanyName;
	private String customerName;
	private String tel;
	private String mobileTel;
	private String typeID;
	private Integer brandID;
	private Integer modelID;
	private String serialNo;
	
	private String typeName;
	private String brandName;
	private String modelName;
	
	private ServiceOrder serviceOrder;
	
	private String sentDate;
	private String sentTransportNo;
	
	private String receivedDate;
	private Integer receivedTransportCompanyID;
	private String receivedTransportNo;
	private String repairing;
	private String costing;
	private String[] serviceDesc;
	private Double[] servicePrice;
	private String[] repairDesc;
	private Double[] repairPrice;
	private Double netAmount;
	
	private String serviceDesc_1;
	private String serviceDesc_2;
	private String serviceDesc_3;
	private String serviceDesc_4;
	private Double servicePrice_1;
	private Double servicePrice_2;
	private Double servicePrice_3;
	private Double servicePrice_4;
	
	private String status;

	public String getOutsiteServiceID() {
		return outsiteServiceID;
	}
	public void setOutsiteServiceID(String outsiteServiceID) {
		this.outsiteServiceID = outsiteServiceID;
	}
	public String getOutsiteServiceDate() {
		return outsiteServiceDate;
	}
	public void setOutsiteServiceDate(String outsiteServiceDate) {
		this.outsiteServiceDate = outsiteServiceDate;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getServiceOrderID() {
		return serviceOrderID;
	}
	public void setServiceOrderID(String serviceOrderID) {
		this.serviceOrderID = serviceOrderID;
	}
	public Integer getOutsiteCompanyID() {
		return outsiteCompanyID;
	}
	public void setOutsiteCompanyID(Integer outsiteCompanyID) {
		this.outsiteCompanyID = outsiteCompanyID;
	}
	public Integer getTransportCompanyID() {
		return transportCompanyID;
	}
	public void setTransportCompanyID(Integer transportCompanyID) {
		this.transportCompanyID = transportCompanyID;
	}
	public ServiceOrder getServiceOrder() {
		return serviceOrder;
	}
	public void setServiceOrder(ServiceOrder serviceOrder) {
		this.serviceOrder = serviceOrder;
	}
	public String getRefOutsiteJobID() {
		return refOutsiteJobID;
	}
	public void setRefOutsiteJobID(String refOutsiteJobID) {
		this.refOutsiteJobID = refOutsiteJobID;
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
	public String getSentDate() {
		return sentDate;
	}
	public void setSentDate(String sentDate) {
		this.sentDate = sentDate;
	}
	public String getSentTransportNo() {
		return sentTransportNo;
	}
	public void setSentTransportNo(String sentTransportNo) {
		this.sentTransportNo = sentTransportNo;
	}
	public String getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}
	public Integer getReceivedTransportCompanyID() {
		return receivedTransportCompanyID;
	}
	public void setReceivedTransportCompanyID(Integer receivedTransportCompanyID) {
		this.receivedTransportCompanyID = receivedTransportCompanyID;
	}
	public String getReceivedTransportNo() {
		return receivedTransportNo;
	}
	public void setReceivedTransportNo(String receivedTransportNo) {
		this.receivedTransportNo = receivedTransportNo;
	}
	public String getOutsiteCompanyName() {
		return outsiteCompanyName;
	}
	public void setOutsiteCompanyName(String outsiteCompanyName) {
		this.outsiteCompanyName = outsiteCompanyName;
	}
	public String getTransportCompanyName() {
		return transportCompanyName;
	}
	public void setTransportCompanyName(String transportCompanyName) {
		this.transportCompanyName = transportCompanyName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getRepairing() {
		return repairing;
	}
	public void setRepairing(String repairing) {
		this.repairing = repairing;
	}
	public String getCosting() {
		return costing;
	}
	public void setCosting(String costing) {
		this.costing = costing;
	}
	public Double getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}
	public String[] getServiceDesc() {
		return serviceDesc;
	}
	public void setServiceDesc(String[] serviceDesc) {
		this.serviceDesc = serviceDesc;
	}
	public Double[] getServicePrice() {
		return servicePrice;
	}
	public void setServicePrice(Double[] servicePrice) {
		this.servicePrice = servicePrice;
	}
	public String[] getRepairDesc() {
		return repairDesc;
	}
	public void setRepairDesc(String[] repairDesc) {
		this.repairDesc = repairDesc;
	}
	public Double[] getRepairPrice() {
		return repairPrice;
	}
	public void setRepairPrice(Double[] repairPrice) {
		this.repairPrice = repairPrice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getServiceDesc_1() {
		return serviceDesc_1;
	}
	public void setServiceDesc_1(String serviceDesc_1) {
		this.serviceDesc_1 = serviceDesc_1;
	}
	public String getServiceDesc_2() {
		return serviceDesc_2;
	}
	public void setServiceDesc_2(String serviceDesc_2) {
		this.serviceDesc_2 = serviceDesc_2;
	}
	public String getServiceDesc_3() {
		return serviceDesc_3;
	}
	public void setServiceDesc_3(String serviceDesc_3) {
		this.serviceDesc_3 = serviceDesc_3;
	}
	public String getServiceDesc_4() {
		return serviceDesc_4;
	}
	public void setServiceDesc_4(String serviceDesc_4) {
		this.serviceDesc_4 = serviceDesc_4;
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
	
}
