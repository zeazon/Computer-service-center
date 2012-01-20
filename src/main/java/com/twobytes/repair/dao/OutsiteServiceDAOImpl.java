package com.twobytes.repair.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.twobytes.model.OutsiteService;

@Repository
public class OutsiteServiceDAOImpl implements OutsiteServiceDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean save(OutsiteService outsiteService) throws Exception{
		Session session = sessionFactory.getCurrentSession();
//		try{
			session.saveOrUpdate(outsiteService);
//		}catch(Exception e){
//			e.printStackTrace();
//			return false;
//		}
		return true;
	}

	@Override
	public OutsiteService selectByID(String outsiteServiceID) throws Exception{
		OutsiteService outsiteService = new OutsiteService();
		outsiteService = (OutsiteService)sessionFactory.getCurrentSession().get(OutsiteService.class, outsiteServiceID);
		return outsiteService;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<OutsiteService> selectByCriteria(String name, String surname,
			String date, String type, Integer rows, Integer page,
			String orderBy, String orderType) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("from OutsiteService as outsiteService where 1=1 ");
		if(null != name && !name.equals("")){
			sql.append("and outsiteService.serviceOrder.customer.name like :name ");
		}
		if(null != surname && !surname.equals("")){
			sql.append("and outsiteService.serviceOrder.customer.surname like :surname ");
		}
		if(null != date && !date.equals("")){
			sql.append("and DATE(outsiteServiceDate) = :outsiteServiceDate ");
		}
		if(null != type && !type.equals("")){
			sql.append("and outsiteService.serviceOrder.product.type.typeID = :type ");
		}
		
		sql.append("and outsiteService.status != 'cancel' ");
		
		if(!orderBy.equals("")){
			if(orderBy.equals("name")){
				orderBy = "outsiteService.serviceOrder.customer.name";
			}else if(orderBy.equals("surname")){
				orderBy = "outsiteService.serviceOrder.customer.surname";
			}
			sql.append("order by "+orderBy+" "+orderType);
		}else{
			sql.append("order by outsiteService.outsiteServiceDate desc");
		}
		
		Query q = sessionFactory.getCurrentSession().createQuery(sql.toString());
		if(null != name && !name.equals("")) {
			q.setString("name", name);
		}
		if(null != surname && !surname.equals("")) {
			q.setString("surname", surname);
		}
		if(null != date && !date.equals("")) {
			q.setString("outsiteServiceDate", date);
		}
		if(null != type && !type.equals("")) {
			q.setString("type", type);
		}
		List<OutsiteService> result = q.list();
		return result;
	}

	@Override
	public boolean edit(OutsiteService outsiteService) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		try{
			session.update(outsiteService);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(OutsiteService outsiteService, Integer employeeID, Date time) throws Exception{
		try{
			outsiteService.setStatus(OutsiteService.CANCEL);
			outsiteService.setUpdatedDate(time);
			outsiteService.setUpdatedBy(employeeID);
			sessionFactory.getCurrentSession().update(outsiteService);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<OutsiteService> selectNewOSByCriteria(String name,
			String date, String type, Integer rows,
			Integer page, String orderBy, String orderType) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("from OutsiteService as outsiteService where 1=1 ");
		if(null != name && !name.equals("")){
			sql.append("and outsiteService.serviceOrder.customer.name like :name ");
		}
		if(null != date && !date.equals("")){
			sql.append("and DATE(outsiteServiceDate) = :outsiteServiceDate ");
		}
		if(null != type && !type.equals("")){
			sql.append("and outsiteService.serviceOrder.product.type.typeID = :type ");
		}
		
		sql.append("and outsiteService.status = '"+OutsiteService.NEW+"' ");
		
		if(!orderBy.equals("")){
			if(orderBy.equals("name")){
				orderBy = "outsiteService.serviceOrder.customer.name";
			}
			sql.append("order by "+orderBy+" "+orderType);
		}else{
			sql.append("order by outsiteService.outsiteServiceDate desc");
		}
		
		Query q = sessionFactory.getCurrentSession().createQuery(sql.toString());
		if(null != name && !name.equals("")) {
			q.setString("name", name);
		}
		if(null != date && !date.equals("")) {
			q.setString("outsiteServiceDate", date);
		}
		if(null != type && !type.equals("")) {
			q.setString("type", type);
		}
		List<OutsiteService> result = q.list();
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<OutsiteService> selectSentOSByCriteria(String name,
			String date, String type, Integer rows,
			Integer page, String orderBy, String orderType) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("from OutsiteService as outsiteService where 1=1 ");
		if(null != name && !name.equals("")){
			sql.append("and outsiteService.serviceOrder.customer.name like :name ");
		}
		if(null != date && !date.equals("")){
			sql.append("and DATE(outsiteServiceDate) = :outsiteServiceDate ");
		}
		if(null != type && !type.equals("")){
			sql.append("and outsiteService.serviceOrder.product.type.typeID = :type ");
		}
		
		sql.append("and outsiteService.status = '"+OutsiteService.SENT+"' ");
		
		if(!orderBy.equals("")){
			if(orderBy.equals("name")){
				orderBy = "outsiteService.serviceOrder.customer.name";
			}
			sql.append("order by "+orderBy+" "+orderType);
		}else{
			sql.append("order by outsiteService.outsiteServiceDate desc");
		}
		
		Query q = sessionFactory.getCurrentSession().createQuery(sql.toString());
		if(null != name && !name.equals("")) {
			q.setString("name", name);
		}
		if(null != date && !date.equals("")) {
			q.setString("outsiteServiceDate", date);
		}
		if(null != type && !type.equals("")) {
			q.setString("type", type);
		}
		List<OutsiteService> result = q.list();
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<OutsiteService> selectByServiceOrderID(String serviceOrderID) throws Exception{
		Query q = sessionFactory.getCurrentSession().createQuery("from OutsiteService os where os.serviceOrder.serviceOrderID = :serviceOrderID ");
		q.setString("serviceOrderID", serviceOrderID);
		List<OutsiteService> retList = q.list();
		return retList;
	}

	@Override
	public Integer countUncloseOutsiteService(String serviceOrderID, String outsiteServiceID)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) numUnclose from outsiteService where serviceOrderID = :serviceOrderID and status != 'close' and status != 'cancel' and outsiteServiceID != :outsiteServiceID ;");
		Query q = sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setString("serviceOrderID", serviceOrderID).setString("outsiteServiceID", outsiteServiceID);
		return ((BigInteger)q.uniqueResult()).intValue();
	}
	
}
