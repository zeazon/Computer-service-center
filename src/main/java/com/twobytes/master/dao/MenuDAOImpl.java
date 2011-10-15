package com.twobytes.master.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.twobytes.model.Menu;

@Repository
public class MenuDAOImpl implements MenuDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Menu selectByID(Integer menuID) throws Exception {
		Menu menu = new Menu();
		menu = (Menu)sessionFactory.getCurrentSession().get(Menu.class, menuID);
		return menu;
	}

}
