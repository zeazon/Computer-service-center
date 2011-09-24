package com.twobytes.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="docRunning")
public class DocRunning implements Serializable {

	private static final long serialVersionUID = -2923616150269094649L;
	
	private Integer docRunningID;
	private String document;
	private String prefix;
	private Integer year;
	private Integer month;
	private Integer runningNumber;
	private Integer date;
	
	@Id
    @Column(name="docRunningID")
    @GeneratedValue
	public Integer getDocRunningID() {
		return docRunningID;
	}
	
	public void setDocRunningID(Integer docRunningID) {
		this.docRunningID = docRunningID;
	}
	
	@Column(name="document")
	public String getDocument() {
		return document;
	}
	
	public void setDocument(String document) {
		this.document = document;
	}
	
	@Column(name="prefix")
	public String getPrefix() {
		return prefix;
	}
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	@Column(name="date")
	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	@Column(name="year")
	public Integer getYear() {
		return year;
	}
	
	public void setYear(Integer year) {
		this.year = year;
	}
	
	@Column(name="month")
	public Integer getMonth() {
		return month;
	}
	
	public void setMonth(Integer month) {
		this.month = month;
	}
	
	@Column(name="runningNumber")
	public Integer getRunningNumber() {
		return runningNumber;
	}
	
	public void setRunningNumber(Integer runningNumber) {
		this.runningNumber = runningNumber;
	}

}
