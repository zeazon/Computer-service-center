package com.twobytes.sale.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.twobytes.model.SaleOrder;

@Repository
public class SaleOrderDAOImpl implements SaleOrderDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean save(SaleOrder saleOrder) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(saleOrder);
		return true;
	}
}
