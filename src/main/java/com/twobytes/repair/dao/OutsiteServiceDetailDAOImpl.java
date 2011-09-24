package com.twobytes.repair.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.twobytes.model.OutsiteServiceDetail;

@Repository
public class OutsiteServiceDetailDAOImpl implements OutsiteServiceDetailDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean save(OutsiteServiceDetail outsiteServiceDetail)
			throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(outsiteServiceDetail);
		return false;
	}
	
}
