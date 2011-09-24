package com.twobytes.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="menu")
public class Menu implements Serializable {

	private static final long serialVersionUID = 5559092838557494477L;
	
	private Integer menuID;
	private String name;
	private Integer parentMenu;
	private String link;
	
	private Set<Role> roles = new HashSet<Role>(0);
	
	@Id
    @Column(name="menuID")
	public Integer getMenuID() {
		return menuID;
	}
	public void setMenuID(Integer menuID) {
		this.menuID = menuID;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="parentMenu")
	public Integer getParentMenu() {
		return parentMenu;
	}
	public void setParentMenu(Integer parentMenu) {
		this.parentMenu = parentMenu;
	}
	
	@Column(name="link")
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}	

	@ManyToMany(cascade = CascadeType.ALL, targetEntity = Role.class)
	@JoinTable(name = "role_menu", joinColumns = { @JoinColumn(name = "menuID") }, inverseJoinColumns = { @JoinColumn(name = "roleID") })
	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles){
		this.roles = roles;
	}
}
