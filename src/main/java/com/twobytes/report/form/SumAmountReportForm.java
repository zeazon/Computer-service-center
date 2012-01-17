package com.twobytes.report.form;

import java.io.Serializable;

public class SumAmountReportForm implements Serializable{

	private static final long serialVersionUID = 1442422355056751729L;
	private String fullName;
	private Integer numServiceOrder;
	private Double amount;
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Integer getNumServiceOrder() {
		return numServiceOrder;
	}
	public void setNumServiceOrder(Integer numServiceOrder) {
		this.numServiceOrder = numServiceOrder;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
