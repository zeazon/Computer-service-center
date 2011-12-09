package com.twobytes.repair.form;

import com.twobytes.model.ServiceOrder;

public class OutsiteServiceForm {
	private String outsiteServiceID;
	private String outsiteServiceDate;
	private String serviceType;
	private String accessories;
	private String problem;
	private String serviceOrderID;
	private Integer outsiteCompanyID;
	private String outsiteCompanyName;
	private Integer transportCompanyID;
	private String transportCompanyName;
	
	private ServiceOrder serviceOrder;
	
	private String sentDate;
	private String sentTransportNo;
	
	private String receivedDate;
	private String receivedTransportNo;
	private String repairing;
	private String costing;
	private String[] serviceDesc;
	private Double[] servicePrice;
	private String[] repairDesc;
	private Double[] repairPrice;
	private Double netAmount;
	
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
	
}
