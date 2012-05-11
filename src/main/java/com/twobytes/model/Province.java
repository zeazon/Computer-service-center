package com.twobytes.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="province")
public class Province implements Serializable {

	private static final long serialVersionUID = -2699161111393599500L;
	
	private Integer provinceID;
	private String name;
	
	@Id
    @Column(name="provinceID")
    @GeneratedValue
	public Integer getProvinceID() {
		return provinceID;
	}
	
	public void setProvinceID(Integer provinceID) {
		this.provinceID = provinceID;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}