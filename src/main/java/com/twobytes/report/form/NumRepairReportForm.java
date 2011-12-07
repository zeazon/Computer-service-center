package com.twobytes.report.form;

import java.io.Serializable;

public class NumRepairReportForm implements Serializable {
	private static final long serialVersionUID = -6357496099944126898L;
	
	private String date;
	private Integer numOpen;
	private Integer numFixing;
	private Integer numFixed;
	private Integer numReturn;
	
	public Integer getNumOpen() {
		return numOpen;
	}
	public void setNumOpen(Integer numOpen) {
		this.numOpen = numOpen;
	}
	public Integer getNumFixing() {
		return numFixing;
	}
	public void setNumFixing(Integer numFixing) {
		this.numFixing = numFixing;
	}
	public Integer getNumFixed() {
		return numFixed;
	}
	public void setNumFixed(Integer numFixed) {
		this.numFixed = numFixed;
	}
	public Integer getNumReturn() {
		return numReturn;
	}
	public void setNumReturn(Integer numReturn) {
		this.numReturn = numReturn;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
