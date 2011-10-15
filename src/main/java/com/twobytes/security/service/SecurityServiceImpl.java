package com.twobytes.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twobytes.model.Employee;
import com.twobytes.model.Menu;
import com.twobytes.security.dao.SecurityDAO;

@Service
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private SecurityDAO securityDAO;
	
	@Transactional
	public Employee login(String login, String password) {
		return securityDAO.login(login, password);
	}

	@Transactional
	public List<Menu> selectMainMenu(Integer roleID) {
		return securityDAO.selectMainMenu(roleID);
	}

	@Transactional
	public List<Menu> selectMainMenu() {
		return securityDAO.selectMainMenu();
	}
	
	@Transactional
	public List<Menu> selectSubMenu(Integer roleID, Integer menuID) {
		return securityDAO.selectSubMenu(roleID, menuID);
	}

	@Override
	@Transactional
	public List<Menu> selectSubMenu(Integer menuID) {
		return securityDAO.selectSubMenu(menuID);
	}
	
}
