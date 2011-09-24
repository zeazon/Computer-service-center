package com.twobytes.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="outsiteServiceDetail")
public class OutsiteServiceDetail implements Serializable {

	private static final long serialVersionUID = 836149565111300020L;
	private Integer outsiteServiceDetailID;
	private OutsiteService outsiteService;
	private String type;
	private String desc;
	private Double price;
	private Integer createdBy;
	private Date createdDate;
	private Integer updatedBy;
	private Date updatedDate;
	
	public static String TYPE_SERVICE = "service";
	public static String TYPE_REPAIR = "repair";
	
	@Id
    @Column(name="outsiteServiceDetailID")
    @GeneratedValue
	public Integer getOutsiteServiceDetailID() {
		return outsiteServiceDetailID;
	}
	
	public void setOutsiteServiceDetailID(Integer outsiteServiceDetailID) {
		this.outsiteServiceDetailID = outsiteServiceDetailID;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="outsiteServiceID")
	public OutsiteService getOutsiteService() {
		return outsiteService;
	}

	public void setOutsiteService(OutsiteService outsiteService) {
		this.outsiteService = outsiteService;
	}

	@Column(name="costType")
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name="description")
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Column(name="price")
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
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
