package com.twobytes.report.form;

import java.io.Serializable;

public class SumAmountReportForm implements Serializable{

	private static final long serialVersionUID = 1442422355056751729L;
	private String fullName;
	private Integer numServiceOrder;
	private Double sumService;
	private Double sumOutsiteRepair;
	private Double sumPart;
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
	public Double getSumService() {
		return sumService;
	}
	public void setSumService(Double sumService) {
		this.sumService = sumService;
	}
	public Double getSumOutsiteRepair() {
		return sumOutsiteRepair;
	}
	public void setSumOutsiteRepair(Double sumOutsiteRepair) {
		this.sumOutsiteRepair = sumOutsiteRepair;
	}
	public Double getSumPart() {
		return sumPart;
	}
	public void setSumPart(Double sumPart) {
		this.sumPart = sumPart;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
