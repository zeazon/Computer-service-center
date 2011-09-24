package com.twobytes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="type")
public class Type implements Serializable {

	private static final long serialVersionUID = 4208568308673621672L;

	private String typeID;
	private String name;
	private Integer createdBy;
	private Date createdDate;
	private Integer updatedBy;
	private Date updatedDate;
	
	private List<Brand> brands = new ArrayList<Brand>();
	
	@Id
    @Column(name="typeID")
	public String getTypeID() {
		return typeID;
	}
	
	public void setTypeID(String typeID) {
		this.typeID = typeID;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
	
	//@ManyToMany(cascade = CascadeType.ALL)
	@ManyToMany
	@JoinTable(name = "type_brand", joinColumns = { @JoinColumn(name = "typeID") }, inverseJoinColumns = { @JoinColumn(name = "brandID") })
	@Fetch(FetchMode.JOIN)
	public List<Brand> getBrands() {
		return this.brands;
	}

	public void setBrands(List<Brand> brands){
		this.brands = brands;
	}

}
