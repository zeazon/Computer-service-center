package com.twobytes.security.service;

import java.util.List;

import com.twobytes.model.Role;

public interface RoleService {

	public boolean save(Role role) throws Exception;
	public Role getRole(Integer roleID);
	public List<Role> getAll();
	public List<Role> selectByCriteria(String name, Integer rows, Integer page, String orderBy, String orderType);
	public boolean delete(Integer roleID) throws Exception;
}
