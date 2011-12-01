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
@Table(name="serviceList")
public class ServiceList extends BaseColumn implements Serializable{

	private static final long serialVersionUID = -8118453500555092613L;
	
	private Integer serviceListID;
	private ServiceOrder serviceOrder;
	private String serviceName;
	private Double price;
	
	@Id
    @Column(name="serviceListID")
    @GeneratedValue
	public Integer getServiceListID() {
		return serviceListID;
	}
	
	public void setServiceListID(Integer serviceListID) {
		this.serviceListID = serviceListID;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="serviceOrderID")
	public ServiceOrder getServiceOrder() {
		return serviceOrder;
	}
	
	public void setServiceOrder(ServiceOrder serviceOrder) {
		this.serviceOrder = serviceOrder;
	}
	
	@Column(name="serviceName")
	public String getServiceName() {
		return serviceName;
	}
	
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	@Column(name="price")
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

}
