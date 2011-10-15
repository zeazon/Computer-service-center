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
	public boolean save(Role role) throws Exception {
		sessionFactory.getCurrentSession().saveOrUpdate(role);
		return true;
	}

	@Override
	public Role getRole(Integer roleID) {
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

	@Override
	@SuppressWarnings("unchecked")
	public List<Role> selectByCriteria(String name, Integer rows, Integer page,
			String orderBy, String orderType) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("from Role where 1=1 ");
		if(null != name && !name.equals("")){
			sql.append("and name like :name ");
		}
		
		if(!orderBy.equals("")){
			sql.append("order by "+orderBy+" "+orderType);
		}
		Query q = sessionFactory.getCurrentSession().createQuery(sql.toString());
		if(null != name && !name.equals("")) {
			q.setString("name", name);
		}

		List<Role> result = q.list();
		return result;
	}
	
	public boolean delete(Role role) throws Exception{
		Query q = sessionFactory.getCurrentSession().createSQLQuery("delete from role_menu where roleID = "+role.getRoleID().toString());
		q.executeUpdate();
		q = sessionFactory.getCurrentSession().createSQLQuery("delete from role where roleID = "+role.getRoleID().toString());
		q.executeUpdate();
		return true;
	}
}
