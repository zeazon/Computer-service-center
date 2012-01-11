package com.twobytes.repair.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.twobytes.model.ServiceList;

@Repository
public class ServiceListDAOImpl implements ServiceListDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean save(ServiceList serviceList) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(serviceList);
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ServiceList> getByServiceOrder(String serviceOrderID)
			throws Exception {
		Query q = sessionFactory.getCurrentSession().createQuery("from ServiceList sl where sl.serviceOrder.serviceOrderID = :serviceOrderID order by serviceListID ");
		q.setString("serviceOrderID", serviceOrderID);
		List<ServiceList> retList = q.list();
		return retList;
	}

	@Override
	public boolean delete(String serviceOrderID) throws Exception {
		Query q = sessionFactory.getCurrentSession().createSQLQuery("delete from ServiceList where serviceOrderID = :serviceOrderID ");
		q.setString("serviceOrderID", serviceOrderID);
		q.executeUpdate();
		return true;
	}

}
