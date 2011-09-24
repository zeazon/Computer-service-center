package com.twobytes.master.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.twobytes.model.OutsiteCompany;

@Repository
public class OutsiteCompanyDAOImpl implements OutsiteCompanyDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<OutsiteCompany> getAll() throws Exception{
		Query q = sessionFactory.getCurrentSession().createQuery("from OutsiteCompany order by name ");
		List <OutsiteCompany> retList = q.list();
		return retList;
	}

	@Override
	public boolean save(OutsiteCompany oc) throws Exception{
		Session session = sessionFactory.getCurrentSession();
//		Transaction tx = session.beginTransaction();
//		try{
			session.saveOrUpdate(oc);
//		}catch (Exception e) {
//			e.printStackTrace();
//			tx.rollback();
//			return false;
//		}
		return true;
	}

	@Override
	public OutsiteCompany selectByID(Integer ocID) throws Exception{
		OutsiteCompany oc = new OutsiteCompany();
		oc = (OutsiteCompany)sessionFactory.getCurrentSession().get(OutsiteCompany.class, ocID);
		return oc;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<OutsiteCompany> selectByCriteria(String name, Integer rows,
			Integer page, String orderBy, String orderType) {
		StringBuilder sql = new StringBuilder();
		sql.append("from OutsiteCompany where 1=1 ");
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

		List<OutsiteCompany> result = q.list();
		return result;
	}

	@Override
	public boolean edit(OutsiteCompany oc) throws Exception{
//		try{
			Session session = sessionFactory.getCurrentSession();
//			Transaction tx = session.beginTransaction();
			session.update(oc);
//			tx.commit();
//		}catch(Exception e){
//			e.printStackTrace();
//			return false;
//		}
		return true;
	}

	@Override
	public boolean delete(OutsiteCompany oc) throws Exception{
//		try{
			sessionFactory.getCurrentSession().delete(oc);
//		}catch(Exception e){
//			e.printStackTrace();
//			return false;
//		}
		return true;
	}

}
