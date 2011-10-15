package com.twobytes.master.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twobytes.master.dao.MenuDAO;
import com.twobytes.model.Menu;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDAO menuDAO;
	
	@Override
	@Transactional
	public Menu selectByID(Integer menuID) {
		Menu model = new Menu();
		try {
			model = menuDAO.selectByID(menuID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

}
