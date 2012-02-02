package com.twobytes.master.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
		session.saveOrUpdate(customer);
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
	public Map<String, Object> selectByCriteria(String name,
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
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		Query q = sessionFactory.getCurrentSession().createQuery(sql.toString()).setFirstResult(rows*page - rows).setMaxResults(rows).setFetchSize(rows);
		if(null != name && !name.equals("")) {
			q.setString("name", name);
		}
		
		List<Customer> list = q.list();
		result.put("list", list);
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);	
		if(null != name && !name.equals("")) {
			criteria.add(Restrictions.like("name", name));
		}
		criteria.setProjection(Projections.rowCount());
		
		result.put("maxRows", criteria.list().get(0));
		return result;
	}

	
	@Override
	public boolean edit(Customer customer) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		session.update(customer);
		return true;
	}

	@Override
	public boolean delete(Customer customer) throws Exception{
		sessionFactory.getCurrentSession().delete(customer);
		return true;
	}

}
