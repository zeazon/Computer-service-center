package com.twobytes.repair.dao;

import java.util.List;

import org.hibernate.Query;
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

	@Override
	public boolean delete(String outsiteServiceID) throws Exception {
		Query q = sessionFactory.getCurrentSession().createSQLQuery("delete from OutsiteServiceDetail where outsiteServiceID = :outsiteServiceID ");
		q.setString("outsiteServiceID", outsiteServiceID);
		q.executeUpdate();
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<OutsiteServiceDetail> getByOutsiteService(
			String outsiteServiceID) throws Exception {
		Query q = sessionFactory.getCurrentSession().createQuery("from OutsiteServiceDetail osd where osd.outsiteService.outsiteServiceID = :outsiteServiceID order by outsiteServiceDetailID ");
		q.setString("outsiteServiceID", outsiteServiceID);
		List<OutsiteServiceDetail> retList = q.list();
		return retList;
	}
	
}
