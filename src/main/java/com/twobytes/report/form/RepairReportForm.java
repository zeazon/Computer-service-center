package com.twobytes.report.form;

import java.io.Serializable;

public class RepairReportForm implements Serializable{
	private static final long serialVersionUID = -3123909013018562357L;
	
	private String serviceOrderID;
	private String serviceOrderDate;
	private String startFix;
	private String endFix;
	private String returnDate;
	
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
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	
}
