package com.twobytes.master.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.twobytes.model.CustomerType;

@Repository
public class CustomerTypeDAOImpl implements CustomerTypeDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean save(CustomerType customerType) throws Exception {
		sessionFactory.getCurrentSession().saveOrUpdate(customerType);
		return true;
	}

	@Override
	public CustomerType selectByID(String customerTypeID) throws Exception {
		CustomerType customerType = new CustomerType();
		customerType = (CustomerType)sessionFactory.getCurrentSession().get(CustomerType.class, customerTypeID);
		return customerType;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<CustomerType> selectByCriteria(String name, Integer rows,
			Integer page, String orderBy, String orderType) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("from CustomerType where 1=1 ");
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
		
		List<CustomerType> result = q.list();
		return result;
	}

	@Override
	public boolean edit(CustomerType customerType) throws Exception {
		sessionFactory.getCurrentSession().update(customerType);
		return true;
	}

	@Override
	public boolean delete(CustomerType customerType) throws Exception {
		sessionFactory.getCurrentSession().delete(customerType);
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<CustomerType> getAll() throws Exception {
		Query q = sessionFactory.getCurrentSession().createQuery("from CustomerType where customerTypeID != '' order by customerTypeID ");
		List<CustomerType> retList = q.list();
		return retList;
	}

}
