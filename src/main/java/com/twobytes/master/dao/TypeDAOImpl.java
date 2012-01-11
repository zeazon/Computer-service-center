package com.twobytes.master.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.twobytes.model.Type;

@Repository
public class TypeDAOImpl implements TypeDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean save(Type type) throws Exception{
		Session session = sessionFactory.getCurrentSession();
//		Transaction tx = session.beginTransaction();
//		try{
			session.saveOrUpdate(type);
//			if (!tx.wasCommitted()) tx.commit();
//			session.flush();
//		}catch (Exception e) {
//			e.printStackTrace();
//			tx.rollback();
//			return false;
//		}
		return true;
	}

	@Override
	public Type selectByID(String typeID) throws Exception{
		Type type = new Type();
		type = (Type)sessionFactory.getCurrentSession().get(Type.class, typeID);
		return type;
	}

	@Override
	public boolean edit(Type type) throws Exception{
//		try{
			Session session = sessionFactory.getCurrentSession();
//			Transaction tx = session.beginTransaction();
			session.update(type);
//			tx.commit();
//		}catch(Exception e){
//			e.printStackTrace();
//			return false;
//		}
		return true;
	}

	@Override
	public boolean delete(Type type) throws Exception{
//		try{
			sessionFactory.getCurrentSession().delete(type);
//		}catch(Exception e){
//			e.printStackTrace();
//			return false;
//		}
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Type> selectByCriteria(String name, Integer rows, Integer page, String orderBy, String orderType) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("from Type where 1=1 ");
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

		List<Type> result = q.list();
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Type> getAll() throws Exception{
		Query q = sessionFactory.getCurrentSession().createQuery("from Type order by name ");
		List<Type> retList = q.list();
		return retList;
	}
	
}
