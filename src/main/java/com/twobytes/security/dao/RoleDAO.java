package com.twobytes.security.dao;

import java.util.List;

import com.twobytes.model.Role;

public interface RoleDAO {

	public Role getRole(Integer roleID);
	public List<Role> getAll();
}
