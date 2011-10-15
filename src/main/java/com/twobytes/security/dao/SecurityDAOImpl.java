package com.twobytes.security.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.twobytes.model.Employee;
import com.twobytes.model.Menu;

@Repository
public class SecurityDAOImpl implements SecurityDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@SuppressWarnings("unchecked")
	public Employee login(String login, String password) {
		Employee emp = null;
		Query q = sessionFactory.getCurrentSession().createQuery("from Employee emp where emp.login = :login and emp.password = :password ");
		q.setString("login", login);
		q.setString("password", password);
		List<Employee> result = q.list();
		if(result.size() > 0){
			emp = (Employee)result.get(0);
		}
		return emp;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Menu> selectMainMenu(Integer roleID) {
		List<Menu> resultList = new ArrayList<Menu>();
		Query q = sessionFactory.getCurrentSession().createQuery("select menu from Menu menu join menu.roles r where menu.parentMenu = :parentMenu and r.roleID = :roleID order by menu.menuID ");
		q.setInteger("parentMenu",0);
		q.setInteger("roleID", roleID);
		resultList = q.list();
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Menu> selectMainMenu() {
		List<Menu> resultList = new ArrayList<Menu>();
		Query q = sessionFactory.getCurrentSession().createQuery("select distinct menu from Menu menu join menu.roles r where menu.parentMenu = :parentMenu order by menu.menuID ");
		q.setInteger("parentMenu",0);
		resultList = q.list();
		return resultList;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Menu> selectSubMenu(Integer roleID, Integer menuID) {
		List<Menu> resultList = null;
		Query q = sessionFactory.getCurrentSession().createQuery("select menu from Menu menu join menu.roles r where menu.parentMenu = :parentMenu and r.roleID = :roleID order by menu.menuID ");
		q.setInteger("parentMenu",menuID);
		q.setInteger("roleID", roleID);
		resultList = q.list();
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Menu> selectSubMenu(Integer menuID) {
		List<Menu> resultList = null;
		Query q = sessionFactory.getCurrentSession().createQuery("select menu from Menu menu where menu.parentMenu = :parentMenu order by menu.menuID ");
		q.setInteger("parentMenu",menuID);
		resultList = q.list();
		return resultList;
	}

}
