package com.twobytes.security.service;

import java.util.List;

import com.twobytes.model.Role;

public interface RoleService {

	public Role getRole(Integer roleID);
	public List<Role> getAll();
}
