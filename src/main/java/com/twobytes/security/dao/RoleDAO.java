package com.twobytes.security.dao;

import java.util.List;

import com.twobytes.model.Brand;
import com.twobytes.model.Role;

public interface RoleDAO {

	public boolean save(Role role) throws Exception;
	public Role getRole(Integer roleID);
	public List<Role> getAll();
	public List<Role> selectByCriteria(String name, Integer rows, Integer page, String orderBy, String orderType) throws Exception;
	public boolean delete(Role role) throws Exception;
	
}
