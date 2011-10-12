package com.twobytes.sale.form;

public class SaleOrderForm {
	private Integer saleOrderID;
	private String saleDate;
	private String customerID;
	private String productID;
	private String description;
	private String typeID;
	private Integer brandID;
	private Integer modelID;
	private String serialNo;
	private String warrantyDate;
	private String warrantyExpire;
	private String remark;
	private String employeeID;
	
	public Integer getSaleOrderID() {
		return saleOrderID;
	}

	public void setSaleOrderID(Integer saleOrderID) {
		this.saleOrderID = saleOrderID;
	}

	public String getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getWarrantyDate() {
		return warrantyDate;
	}

	public void setWarrantyDate(String warrantyDate) {
		this.warrantyDate = warrantyDate;
	}

	public String getWarrantyExpire() {
		return warrantyExpire;
	}

	public void setWarrantyExpire(String warrantyExpire) {
		this.warrantyExpire = warrantyExpire;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTypeID() {
		return typeID;
	}

	public void setTypeID(String typeID) {
		this.typeID = typeID;
	}

	public Integer getBrandID() {
		return brandID;
	}

	public void setBrandID(Integer brandID) {
		this.brandID = brandID;
	}

	public Integer getModelID() {
		return modelID;
	}

	public void setModelID(Integer modelID) {
		this.modelID = modelID;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	
}
