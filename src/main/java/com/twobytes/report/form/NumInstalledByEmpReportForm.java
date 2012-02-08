package com.twobytes.report.form;

import java.io.Serializable;

public class NumInstalledByEmpReportForm implements Serializable{

	private static final long serialVersionUID = 9176523451286961980L;
	private String fullName;
	private String type;
	private String brand;
	private String model;
	private Integer num;
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}

}
