package com.twobytes.repair.form;

import java.util.Date;
import java.util.List;

import com.twobytes.model.OutsiteServiceDetail;

public class OutsiteServiceDisplayForm {
	private String outsiteServiceID;
	private Date outsiteServiceDate;
	private String serviceType;
	private Date sentDate;
	private Date receivedDate;
	private String repairing;
	private String costing;
	private Double netAmount;
	private List<OutsiteServiceDetail> detailList;
	
	public String getOutsiteServiceID() {
		return outsiteServiceID;
	}
	public void setOutsiteServiceID(String outsiteServiceID) {
		this.outsiteServiceID = outsiteServiceID;
	}
	public Date getOutsiteServiceDate() {
		return outsiteServiceDate;
	}
	public void setOutsiteServiceDate(Date outsiteServiceDate) {
		this.outsiteServiceDate = outsiteServiceDate;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public Date getSentDate() {
		return sentDate;
	}
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
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
	public List<OutsiteServiceDetail> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<OutsiteServiceDetail> detailList) {
		this.detailList = detailList;
	}
	
}
