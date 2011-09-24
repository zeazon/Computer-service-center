package com.twobytes.security.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.twobytes.model.Role;

@Repository
public class RoleDAOImpl implements RoleDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Role getRole(Integer roleID) {
//		Session session = sessionFactory.openSession();
//		Role role = (Role)session.get(Role.class, roleID);
////	session.flush();
//		session.clear();
		Role role = (Role)sessionFactory.getCurrentSession().get(Role.class, roleID);
		return role;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Role> getAll() {
		Query q = sessionFactory.getCurrentSession().createQuery("from Role order by name ");
		List<Role> retList = q.list();
		return retList;
	}

}
