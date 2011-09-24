package com.twobytes.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="model")
public class Model extends BaseColumn implements Serializable{

	private static final long serialVersionUID = 3776768385717436085L;
	private Integer modelID;
	private String name;
	private Type type;
	private Brand brand;
	
	@Id
    @Column(name="modelID")
    @GeneratedValue
	public Integer getModelID() {
		return modelID;
	}
	
	public void setModelID(Integer modelID) {
		this.modelID = modelID;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="brandID")
	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="typeID")
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
}
