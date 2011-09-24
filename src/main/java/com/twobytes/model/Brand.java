package com.twobytes.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="brand")
public class Brand extends BaseColumn implements Serializable {

	private static final long serialVersionUID = 6587140108225223488L;

	private Integer brandID;
	private String name;
	
	private Set<Type> types = new HashSet<Type>();
	
	@Id
    @Column(name="brandID")
    @GeneratedValue
	public Integer getBrandID() {
		return brandID;
	}
	
	public void setBrandID(Integer brandID) {
		this.brandID = brandID;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "type_brand", joinColumns = { @JoinColumn(name = "brandID") }, inverseJoinColumns = { @JoinColumn(name = "typeID") })
	@Fetch(FetchMode.JOIN)
	public Set<Type> getTypes() {
		return types;
	}

	public void setTypes(Set<Type> types) {
		this.types = types;
	}

}
