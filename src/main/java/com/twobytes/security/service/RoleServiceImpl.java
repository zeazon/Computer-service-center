package com.twobytes.security.service;

import java.util.ArrayList;
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
	public boolean save(Role role) throws Exception {
		return roleDAO.save(role);
	}

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

	@Override
	@Transactional
	public List<Role> selectByCriteria(String name, Integer rows,
			Integer page, String orderBy, String orderType) {
		if(null != name && !name.equals("")) {
			name = "%"+name+"%";
		}
		List<Role> modelList = new ArrayList<Role>();
		try {
			modelList = roleDAO.selectByCriteria(name, rows, page, orderBy, orderType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelList;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean delete(Integer roleID) throws Exception {
		Role role = roleDAO.getRole(roleID);
		if(null != role){
			return roleDAO.delete(role);
		}else{
			return false;
		}
	}

}
