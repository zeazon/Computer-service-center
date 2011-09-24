package com.twobytes.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="outsiteCompany")
public class OutsiteCompany implements Serializable {

	private static final long serialVersionUID = 7424557868775330010L;
	
	private Integer outsiteCompanyID;
	private String name;
	private String addr;
	private Subdistrict subdistrict;
	private District district;
	private Province province;
	private Integer zipcode;
	private Integer createdBy;
	private Date createdDate;
	private Integer updatedBy;
	private Date updatedDate;
	
	@Id
    @Column(name="outsiteCompanyID")
    @GeneratedValue
	public Integer getOutsiteCompanyID() {
		return outsiteCompanyID;
	}
	
	public void setOutsiteCompanyID(Integer outsiteCompanyID) {
		this.outsiteCompanyID = outsiteCompanyID;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="addr")
	public String getAddr() {
		return addr;
	}
	
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	@OneToOne
	@JoinColumn(name = "subdistrictID")
	public Subdistrict getSubdistrict() {
		return subdistrict;
	}
	
	public void setSubdistrict(Subdistrict subdistrict) {
		this.subdistrict = subdistrict;
	}
	
	@OneToOne
	@JoinColumn(name = "districtID")
	public District getDistrict() {
		return district;
	}
	
	public void setDistrict(District district) {
		this.district = district;
	}
	
	@OneToOne
	@JoinColumn(name = "provinceID")
	public Province getProvince() {
		return province;
	}
	
	public void setProvince(Province province) {
		this.province = province;
	}
	
	@Column(name="zipcode")
	public Integer getZipcode() {
		return zipcode;
	}
	
	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}
	
	@Column(name="createdBy")
	public Integer getCreatedBy() {
		return createdBy;
	}
	
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name="createdDate")
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(name="updatedBy")
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Column(name="updatedDate")
	public Date getUpdatedDate() {
		return updatedDate;
	}
	
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}
