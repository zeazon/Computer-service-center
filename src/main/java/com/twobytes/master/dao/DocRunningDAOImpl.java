package com.twobytes.master.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.twobytes.model.DocRunning;

@Repository
public class DocRunningDAOImpl implements DocRunningDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@SuppressWarnings("unchecked")
	public DocRunning getDoc(String document) {
		DocRunning doc = new DocRunning();
		StringBuilder sql = new StringBuilder();
		sql.append("from DocRunning where document=:document order by runningNumber desc ");
		Query q = sessionFactory.getCurrentSession().createQuery(sql.toString());
		q.setString("document", document);
		List<DocRunning> docList = q.list();
		if(docList.size()>0){
			doc = docList.get(0);
		}else{
			doc = null;
		}
		return doc;
	}

	@Override
	@SuppressWarnings("unchecked")
	public DocRunning getDocByYear(String document, Integer year) {
		DocRunning doc = new DocRunning();
		StringBuilder sql = new StringBuilder();
		sql.append("from DocRunning where document=:document and year = :year order by runningNumber desc ");
		Query q = sessionFactory.getCurrentSession().createQuery(sql.toString());
		
		q.setString("document", document);
		q.setInteger("year", year);
		List<DocRunning> docList = q.list();
		if(docList.size()>0){
			doc = docList.get(0);
		}else{
			doc = null;
		}
		return doc;
	}

	@Override
	@SuppressWarnings("unchecked")
	public DocRunning getDocByYearMonth(String document, Integer year,
			Integer month) {
		DocRunning doc = new DocRunning();
		StringBuilder sql = new StringBuilder();
//		sql.append("from DocRunning where document=:document and year = :year and month = :month ");
//		Query q = sessionFactory.getCurrentSession().createQuery(sql.toString());
		
//		sql.append("select docRunningID, document, prefix, year, month, max(runningNumber) runningNumber from DocRunning where document=:document and year = :year and month = :month ");
//		Query q = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		
		sql.append("from DocRunning where document=:document and year = :year and month = :month order by runningNumber desc ");
		Query q = sessionFactory.getCurrentSession().createQuery(sql.toString());
		
		q.setString("document", document);
		q.setInteger("year", year);
		q.setInteger("month", month);
		List<DocRunning> docList = q.list();
		if(docList.size()>0){
			doc = docList.get(0);
		}else{
			doc = null;
		}
		return doc;
	}

	@Override
	public boolean createNewDocRunning(DocRunning docRunning) {
		Session session = sessionFactory.getCurrentSession();
		try{
			session.saveOrUpdate(docRunning);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateDocRunning(DocRunning docRunning) {
		try{
			Session session = sessionFactory.getCurrentSession();
//			Transaction tx = session.beginTransaction();
			session.update(docRunning);
//			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public DocRunning getDocByYearMonthDate(String document, Integer year,
			Integer month, Integer date) {
		DocRunning doc = new DocRunning();
		StringBuilder sql = new StringBuilder();
		
		sql.append("from DocRunning where document=:document and year = :year and month = :month and date = :date order by runningNumber desc ");
		Query q = sessionFactory.getCurrentSession().createQuery(sql.toString());
		
		q.setString("document", document);
		q.setInteger("year", year);
		q.setInteger("month", month);
		q.setInteger("date", date);
		List<DocRunning> docList = q.list();
		if(docList.size()>0){
			doc = docList.get(0);
		}else{
			doc = null;
		}
		return doc;
	}

}
