package com.twobytes.master.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.twobytes.model.TransportCompany;

@Repository
public class TransportCompanyDAOImpl implements TransportCompanyDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean save(TransportCompany tc) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try{
			session.saveOrUpdate(tc);
		}catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			return false;
		}
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<TransportCompany> getAll() throws Exception{
		Query q = sessionFactory.getCurrentSession().createQuery("from TransportCompany order by name ");
		List<TransportCompany> retList = q.list();
		return retList;
	}

	@Override
	public TransportCompany selectByID(Integer tcID) throws Exception{
		TransportCompany tc = new TransportCompany();
		tc = (TransportCompany)sessionFactory.getCurrentSession().get(TransportCompany.class, tcID);
		return tc;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<TransportCompany> selectByCriteria(String name, Integer rows,
			Integer page, String orderBy, String orderType) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("from TransportCompany where 1=1 ");
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

		List<TransportCompany> result = q.list();
		return result;
	}

	@Override
	public boolean edit(TransportCompany tc) throws Exception{
		try{
			Session session = sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.update(tc);
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(TransportCompany tc) throws Exception{
		try{
			sessionFactory.getCurrentSession().delete(tc);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
