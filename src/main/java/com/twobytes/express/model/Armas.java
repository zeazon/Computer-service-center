package com.twobytes.express.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ARMAS")
public class Armas implements Serializable{
	
	private static final long serialVersionUID = -533420955689864986L;
	private String cuscod;
	private String custyp;
	private String prenam;
	private String cusnam;
	private String addr01;
	private String addr02;
	private String addr03;
	private String zipcod;
	private String telnum;
	private String contact;
	private String remark;
	
	@Id
    @Column(name="CUSCOD")
	public String getCuscod() {
		return cuscod;
	}
	
	public void setCuscod(String cuscod) {
		this.cuscod = cuscod;
	}
	
	@Column(name="CUSTYP")
	public String getCustyp() {
		return custyp;
	}

	public void setCustyp(String custyp) {
		this.custyp = custyp;
	}

	@Column(name="PRENAM")
	public String getPrenam() {
		return prenam;
	}
	
	public void setPrenam(String prenam) {
		this.prenam = prenam;
	}
	
	@Column(name="CUSNAM")
	public String getCusnam() {
		return cusnam;
	}
	
	public void setCusnam(String cusnam) {
		this.cusnam = cusnam;
	}
	
	@Column(name="ADDR01")
	public String getAddr01() {
		return addr01;
	}
	
	public void setAddr01(String addr01) {
		this.addr01 = addr01;
	}
	
	@Column(name="ADDR02")
	public String getAddr02() {
		return addr02;
	}
	
	public void setAddr02(String addr02) {
		this.addr02 = addr02;
	}
	
	@Column(name="ADDR03")
	public String getAddr03() {
		return addr03;
	}
	
	public void setAddr03(String addr03) {
		this.addr03 = addr03;
	}
	
	@Column(name="ZIPCOD")
	public String getZipcod() {
		return zipcod;
	}
	
	public void setZipcod(String zipcod) {
		this.zipcod = zipcod;
	}
	
	@Column(name="TELNUM")
	public String getTelnum() {
		return telnum;
	}
	
	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}
	
	@Column(name="CONTACT")
	public String getContact() {
		return contact;
	}
	
	public void setContact(String contact) {
		this.contact = contact;
	}

	@Column(name="REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
