package com.twobytes.report.form;

import java.io.Serializable;

public class NumSaleByEmpReportForm implements Serializable {
	private static final long serialVersionUID = 8474452782612806381L;
	
	private String fullName;
	private Integer numNOT;
	private Integer numCOM;
	private Integer numPRN;
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Integer getNumNOT() {
		return numNOT;
	}
	public void setNumNOT(Integer numNOT) {
		this.numNOT = numNOT;
	}
	public Integer getNumCOM() {
		return numCOM;
	}
	public void setNumCOM(Integer numCOM) {
		this.numCOM = numCOM;
	}
	public Integer getNumPRN() {
		return numPRN;
	}
	public void setNumPRN(Integer numPRN) {
		this.numPRN = numPRN;
	}
	
}
