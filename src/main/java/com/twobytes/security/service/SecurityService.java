package com.twobytes.security.service;

import java.util.List;

import com.twobytes.model.Employee;
import com.twobytes.model.Menu;

public interface SecurityService {
	
	public Employee login(String login, String password);
	public List<Menu> selectMainMenu(Integer roleID);
	public List<Menu> selectSubMenu(Integer roleID, Integer menuID);
	
}
