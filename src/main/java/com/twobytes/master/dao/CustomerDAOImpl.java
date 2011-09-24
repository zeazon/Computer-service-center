package com.twobytes.master.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.twobytes.model.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean save(Customer customer) throws Exception{
		Session session = sessionFactory.getCurrentSession();
//		try{
			session.saveOrUpdate(customer);
//		}catch(Exception e){
//			e.printStackTrace();
//			return false;
//		}
		return true;
	}

	@Override
	public Customer selectByID(String customerID) throws Exception{
		Customer customer = new Customer();
		customer = (Customer)sessionFactory.getCurrentSession().get(Customer.class, customerID);
		return customer;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Customer> selectByCriteria(String name,
			Integer rows, Integer page, String orderBy, String orderType) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("from Customer where 1=1 ");
		if(null != name && !name.equals("")){
			sql.append("and name like :name ");
		}
		
		if(!orderBy.equals("")){
			if(orderBy.equals("fullName")){
				sql.append("order by name "+orderType+", surname "+orderType);
			}else{
				sql.append("order by "+orderBy+" "+orderType);
			}
		}
//		Query q = sessionFactory.getCurrentSession().createQuery(sql.toString()).setFirstResult(rows*page - rows).setMaxResults(rows);
		Query q = sessionFactory.getCurrentSession().createQuery(sql.toString());
		if(null != name && !name.equals("")) {
			q.setString("name", name);
		}
		List<Customer> result = q.list();
		return result;
	}

	@Override
	public boolean edit(Customer customer) throws Exception{
//		try{
			Session session = sessionFactory.getCurrentSession();
//			Transaction tx = session.beginTransaction();
			session.update(customer);
//			tx.commit();
//		}catch(Exception e){
//			e.printStackTrace();
//			return false;
//		}
		return true;
	}

	@Override
	public boolean delete(Customer customer) throws Exception{
//		try{
			sessionFactory.getCurrentSession().delete(customer);
//		}catch(Exception e){
//			e.printStackTrace();
//			return false;
//		}
		return true;
	}

}
