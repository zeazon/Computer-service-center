package com.twobytes.report.form;

import java.io.Serializable;
import java.util.Date;

public class NumRepairByEmpReportForm implements Serializable{

	private static final long serialVersionUID = -4541012303214338263L;
	
	private Integer numOfDoc;
	private String name;
	private String surname;
	private String fullName;
	private String serviceOrderID;
	private Date serviceOrderDate;
	private Date startFix;
	private Date endFix;
	private Date appointmentDate;
	private Date returnDate;
	private String diffStartFix;
	private String diffFix;
	private String diffFinish;
	private String diffReturn;
	
	public Integer getNumOfDoc() {
		return numOfDoc;
	}
	public void setNumOfDoc(Integer numOfDoc) {
		this.numOfDoc = numOfDoc;
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
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getServiceOrderID() {
		return serviceOrderID;
	}
	public void setServiceOrderID(String serviceOrderID) {
		this.serviceOrderID = serviceOrderID;
	}
	public Date getServiceOrderDate() {
		return serviceOrderDate;
	}
	public void setServiceOrderDate(Date serviceOrderDate) {
		this.serviceOrderDate = serviceOrderDate;
	}
	public Date getStartFix() {
		return startFix;
	}
	public void setStartFix(Date startFix) {
		this.startFix = startFix;
	}
	public Date getEndFix() {
		return endFix;
	}
	public void setEndFix(Date endFix) {
		this.endFix = endFix;
	}
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public String getDiffStartFix() {
		return diffStartFix;
	}
	public void setDiffStartFix(String diffStartFix) {
		this.diffStartFix = diffStartFix;
	}
	public String getDiffFix() {
		return diffFix;
	}
	public void setDiffFix(String diffFix) {
		this.diffFix = diffFix;
	}
	public String getDiffFinish() {
		return diffFinish;
	}
	public void setDiffFinish(String diffFinish) {
		this.diffFinish = diffFinish;
	}
	public String getDiffReturn() {
		return diffReturn;
	}
	public void setDiffReturn(String diffReturn) {
		this.diffReturn = diffReturn;
	}

}
