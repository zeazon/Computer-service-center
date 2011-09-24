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
@Table(name="subdistrict")
public class Subdistrict implements Serializable {

	private static final long serialVersionUID = -682500061164371119L;

	private Integer subdistrictID;
	private String name;
	private District district;
	private Integer zipcode;
	
	@Id
    @Column(name="subdistrictID")
    @GeneratedValue
	public Integer getSubdistrictID() {
		return subdistrictID;
	}
	
	public void setSubdistrictID(Integer subdistrictID) {
		this.subdistrictID = subdistrictID;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="districtID")
	public District getDistrict() {
		return district;
	}
	
	public void setDistrict(District district) {
		this.district = district;
	}
	
	@Column(name="zipcode")
	public Integer getZipcode() {
		return zipcode;
	}
	
	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}
	
}
