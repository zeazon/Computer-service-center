package com.twobytes.report.form;

import java.io.Serializable;

public class NumInstalledByEmpReportForm implements Serializable{

	private static final long serialVersionUID = 9176523451286961980L;
	private String fullName;
	private Integer num;
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}

}
