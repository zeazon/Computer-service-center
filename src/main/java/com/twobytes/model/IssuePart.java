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
@Table(name="issuePart")
public class IssuePart extends BaseColumn implements Serializable {

	private static final long serialVersionUID = 6735865087282381821L;

	private Integer issuePartID;
	private ServiceOrder serviceOrder;
	private String code;
	private String name;
	private Integer quantity;
	private Double price;
	private Double netPrice;
	
	@Id
    @Column(name="issuePartID")
    @GeneratedValue
	public Integer getIssuePartID() {
		return issuePartID;
	}
	
	public void setIssuePartID(Integer issuePartID) {
		this.issuePartID = issuePartID;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="serviceOrderID")
	public ServiceOrder getServiceOrder() {
		return serviceOrder;
	}

	public void setServiceOrder(ServiceOrder serviceOrder) {
		this.serviceOrder = serviceOrder;
	}

	@Column(name="code")
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="quantity")
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	@Column(name="price")
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Column(name="netPrice")
	public Double getNetPrice() {
		return netPrice;
	}
	
	public void setNetPrice(Double netPrice) {
		this.netPrice = netPrice;
	}
	
}
