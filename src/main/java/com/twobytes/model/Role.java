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
@Table(name="role")
public class Role implements Serializable {

	private static final long serialVersionUID = 8421657876999147750L;
	
	private Integer roleID;	
	private String name;
	
	private Set<Menu> menus = new HashSet<Menu>(0);

	@Id
    @Column(name="roleID")
    @GeneratedValue
	public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "role_menu", joinColumns = { @JoinColumn(name = "roleID") }, inverseJoinColumns = { @JoinColumn(name = "menuID") })
	@Fetch(FetchMode.JOIN)
	public Set<Menu> getMenus() {
		return this.menus;
	}

	public void setMenus(Set<Menu> menus){
		this.menus = menus;
	}
}
