package com.twobytes.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twobytes.model.Role;
import com.twobytes.security.dao.RoleDAO;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAO roleDAO;
	
	@Override
	@Transactional
	public Role getRole(Integer roleID) {
		return roleDAO.getRole(roleID);
	}

	@Override
	@Transactional
	public List<Role> getAll() {
		return roleDAO.getAll();
	}

}
