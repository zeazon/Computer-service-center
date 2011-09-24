package com.twobytes.master.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.twobytes.model.Brand;

@Repository
public class BrandDAOImpl implements BrandDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean save(Brand brand) throws Exception{
		Session session = sessionFactory.getCurrentSession();
//		Transaction tx = session.beginTransaction();
//		try{
			session.saveOrUpdate(brand);
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
	public Brand selectByID(Integer brandID) throws Exception{
		Brand brand = new Brand();
		brand = (Brand)sessionFactory.getCurrentSession().get(Brand.class, brandID);
		return brand;
	}

	@Override
	public boolean edit(Brand brand) throws Exception{
//		try{
			Session session = sessionFactory.getCurrentSession();
//			Transaction tx = session.beginTransaction();
			session.update(brand);
//			tx.commit();
//		}catch(Exception e){
//			e.printStackTrace();
//			return false;
//		}
		return true;
	}

	@Override
	public boolean delete(Brand brand) throws Exception {
//		try{
			sessionFactory.getCurrentSession().delete(brand);
//		}catch(Exception e){
//			e.printStackTrace();
//			return false;
//		}
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Brand> selectByCriteria(String name, Integer rows, Integer page, String orderBy, String orderType) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("from Brand where 1=1 ");
		if(null != name && !name.equals("")){
			sql.append("and name like :name ");
		}
		
		if(!orderBy.equals("")){
			sql.append("order by "+orderBy+" "+orderType);
		}
//		Query q = sessionFactory.getCurrentSession().createQuery(sql.toString()).setFirstResult(rows*page - rows).setMaxResults(rows);
		Query q = sessionFactory.getCurrentSession().createQuery(sql.toString());
		if(null != name && !name.equals("")) {
			q.setString("name", name);
		}

		List<Brand> result = q.list();
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Brand> getAll() throws Exception {
		Query q = sessionFactory.getCurrentSession().createQuery("from Brand order by name ");
		List<Brand> retList = q.list();
		return retList;
	}

}
