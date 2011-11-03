package com.twobytes.master.form;

public class CustomerForm {
	private String customerID;
	private String name;
	private String customerTypeID;
	private String address;
	private Integer subdistrictID;
	private Integer districtID;
	private Integer provinceID;
	private String zipcode;
	private String tel;
	private String mobileTel;
	private String email;
	
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getSubdistrictID() {
		return subdistrictID;
	}
	public void setSubdistrictID(Integer subdistrictID) {
		this.subdistrictID = subdistrictID;
	}
	public Integer getDistrictID() {
		return districtID;
	}
	public void setDistrictID(Integer districtID) {
		this.districtID = districtID;
	}
	public Integer getProvinceID() {
		return provinceID;
	}
	public void setProvinceID(Integer provinceID) {
		this.provinceID = provinceID;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMobileTel() {
		return mobileTel;
	}
	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCustomerTypeID() {
		return customerTypeID;
	}
	public void setCustomerTypeID(String customerTypeID) {
		this.customerTypeID = customerTypeID;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
}
