package com.twobytes.report.form;

import java.io.Serializable;

public class CountCustomerRegionReportForm implements Serializable{

	private static final long serialVersionUID = -4753950721902953356L;
	private String addrName;
	private Integer num;
	
	public String getAddrName() {
		return addrName;
	}
	public void setAddrName(String addrName) {
		this.addrName = addrName;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}

}
