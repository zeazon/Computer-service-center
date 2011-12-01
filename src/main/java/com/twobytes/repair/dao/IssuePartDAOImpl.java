package com.twobytes.repair.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.twobytes.model.IssuePart;

@Repository
public class IssuePartDAOImpl implements IssuePartDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean save(IssuePart issuePart) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(issuePart);
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<IssuePart> getByServiceOrder(String serviceOrderID) throws Exception {
		Query q = sessionFactory.getCurrentSession().createQuery("from IssuePart ip where ip.serviceOrder.serviceOrderID = :serviceOrderID order by issuePartID ");
		q.setString("serviceOrderID", serviceOrderID);
		List<IssuePart> retList = q.list();
		return retList;
	}

}
