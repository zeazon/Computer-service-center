package com.twobytes.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="district")
public class District implements Serializable {

	private static final long serialVersionUID = -4924830683325369262L;

	private Integer districtID;
	private String name;
	private Province province;
	
	@Id
    @Column(name="districtID")
    @GeneratedValue
	public Integer getDistrictID() {
		return districtID;
	}
	
	public void setDistrictID(Integer districtID) {
		this.districtID = districtID;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="provinceID")
	public Province getProvince() {
		return province;
	}
	
	public void setProvince(Province province) {
		this.province = province;
	}
	
}
