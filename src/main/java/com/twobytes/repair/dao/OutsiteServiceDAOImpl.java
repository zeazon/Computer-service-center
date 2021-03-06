package com.twobytes.repair.dao;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
		session.saveOrUpdate(outsiteService);
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
	public Map<String, Object> selectByCriteria(String name, String surname,
			String date, String type, String serialNo, String refOutsiteJobID, 
			String outsiteCompanyID, String transportCompanyID, 
			String status, 
			Integer rows, Integer page,
			String orderBy, String orderType) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", new Locale ("US"));
		
		StringBuilder sql = new StringBuilder();
		sql.append("from OutsiteService as outsiteService where 1=1 ");
		if(null != name && !name.equals("")){
			sql.append("and outsiteService.customerName like :name ");
		}
		if(null != surname && !surname.equals("")){
			sql.append("and outsiteService.serviceOrder.customer.surname like :surname ");
		}
		if(null != date && !date.equals("")){
			sql.append("and DATE(outsiteServiceDate) = :outsiteServiceDate ");
		}
		if(null != type && !type.equals("")){
			sql.append("and outsiteService.type.typeID = :type ");
		}
		if(null != serialNo && !serialNo.equals("")){
			sql.append("and outsiteService.serialNo like :serialNo ");
		}
		if(null != refOutsiteJobID && !refOutsiteJobID.equals("")){
			sql.append("and outsiteService.refOutsiteJobID like :refOutsiteJobID ");
		}
		if(null != outsiteCompanyID && !outsiteCompanyID.equals("")){
			sql.append("and outsiteService.outsiteCompany.outsiteCompanyID = :outsiteCompanyID ");
		}
		if(null != transportCompanyID && !transportCompanyID.equals("")){
			sql.append("and outsiteService.transportCompany.transportCompanyID = :transportCompanyID ");
		}
		if(null != status && !status.equals("")){
			sql.append("and outsiteService.status = :status ");
		}
		
		sql.append("and outsiteService.status != '"+OutsiteService.CANCEL+"' ");
		
		if(!orderBy.equals("")){
			if(orderBy.equals("name")){
				orderBy = "outsiteService.customerName";
			}
			sql.append("order by "+orderBy+" "+orderType);
		}else{
			sql.append("order by outsiteService.outsiteServiceDate desc");
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		Query q = sessionFactory.getCurrentSession().createQuery(sql.toString()).setFirstResult(rows*page - rows).setMaxResults(rows).setFetchSize(rows);
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
		if(null != serialNo && !serialNo.equals("")) {
			q.setString("serialNo", serialNo);
		}
		if(null != refOutsiteJobID && !refOutsiteJobID.equals("")) {
			q.setString("refOutsiteJobID", refOutsiteJobID);
		}
		if(null != outsiteCompanyID && !outsiteCompanyID.equals("")) {
			q.setInteger("outsiteCompanyID", Integer.valueOf(outsiteCompanyID));
		}
		if(null != transportCompanyID && !transportCompanyID.equals("")) {
			q.setInteger("transportCompanyID", Integer.valueOf(transportCompanyID));
		}
		if(null != status && !status.equals("")) {
			q.setString("status", status);
		}
		List<OutsiteService> list = q.list();
		result.put("list", list);
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OutsiteService.class, "outsiteService");
		if(null != name && !name.equals("")) {
			criteria.add(Restrictions.like("outsiteService.customerName", name));
		}
		if(null != date && !date.equals("")) {
			Calendar nextDay = Calendar.getInstance();
			nextDay.setTime(sdf.parse(date));
			nextDay.add(Calendar.DATE, 1);
			criteria.add(Restrictions.ge("outsiteService.outsiteServiceDate", sdf.parse(date)));
			criteria.add(Restrictions.lt("outsiteService.outsiteServiceDate", nextDay.getTime()));
		}
		if(null != type && !type.equals("")) {
			criteria.createCriteria("outsiteService.type", "type");
			criteria.add(Restrictions.eq("type.typeID", type));
		}
		if(null != serialNo && !serialNo.equals("")) {
			criteria.add(Restrictions.like("outsiteService.serialNo", serialNo));
		}
		if(null != refOutsiteJobID && !refOutsiteJobID.equals("")) {
			criteria.add(Restrictions.like("outsiteService.refOutsiteJobID", refOutsiteJobID));
		}
		if(null != outsiteCompanyID && !outsiteCompanyID.equals("")) {
			criteria.createCriteria("outsiteService.outsiteCompany", "outsiteCompany");
			criteria.add(Restrictions.eq("outsiteCompany.outsiteCompanyID", Integer.valueOf(outsiteCompanyID)));
		}
		if(null != transportCompanyID && !transportCompanyID.equals("")) {
			criteria.createCriteria("outsiteService.transportCompany", "transportCompany");
			criteria.add(Restrictions.eq("transportCompany.transportCompanyID", Integer.valueOf(transportCompanyID)));
		}
		if(null != status && !status.equals("")) {
			criteria.add(Restrictions.eq("outsiteService.status", status));
		}
		criteria.add(Restrictions.ne("status", OutsiteService.CANCEL));
		criteria.setProjection(Projections.rowCount());
		
		result.put("maxRows", criteria.list().get(0));
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
			String date, String type, String serialNo, Integer rows,
			Integer page, String orderBy, String orderType) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("from OutsiteService as outsiteService where 1=1 ");
		if(null != name && !name.equals("")){
			sql.append("and outsiteService.customerName like :name ");
		}
		if(null != date && !date.equals("")){
			sql.append("and DATE(outsiteServiceDate) = :outsiteServiceDate ");
		}
		if(null != type && !type.equals("")){
			sql.append("and outsiteService.type.typeID = :type ");
		}
		if(null != serialNo && !serialNo.equals("")){
			sql.append("and outsiteService.serialNo like :serialNo ");
		}
		
		sql.append("and outsiteService.status = '"+OutsiteService.NEW+"' ");
		
		if(!orderBy.equals("")){
			if(orderBy.equals("name")){
				orderBy = "outsiteService.customerName";
			}
			sql.append("order by "+orderBy+" "+orderType);
		}else{
			sql.append("order by outsiteService.outsiteServiceDate desc");
		}
		
		Query q = sessionFactory.getCurrentSession().createQuery(sql.toString()).setFetchSize(rows);
		if(null != name && !name.equals("")) {
			q.setString("name", name);
		}
		if(null != date && !date.equals("")) {
			q.setString("outsiteServiceDate", date);
		}
		if(null != type && !type.equals("")) {
			q.setString("type", type);
		}
		if(null != serialNo && !serialNo.equals("")) {
			q.setString("serialNo", serialNo);
		}
		List<OutsiteService> result = q.list();
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<OutsiteService> selectSentOSByCriteria(String name,
			String date, String type, String serialNo, String refOutsiteJobID,
			String outsiteCompanyID, String transportCompanyID,
			Integer rows, Integer page, 
			String orderBy, String orderType) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("from OutsiteService as outsiteService where 1=1 ");
		if(null != name && !name.equals("")){
			sql.append("and outsiteService.customerName like :name ");
		}
		if(null != date && !date.equals("")){
			sql.append("and DATE(outsiteServiceDate) = :outsiteServiceDate ");
		}
		if(null != type && !type.equals("")){
			sql.append("and outsiteService.type.typeID = :type ");
		}
		if(null != serialNo && !serialNo.equals("")){
			sql.append("and outsiteService.serialNo like :serialNo ");
		}
		if(null != refOutsiteJobID && !refOutsiteJobID.equals("")){
			sql.append("and outsiteService.refOutsiteJobID like :refOutsiteJobID ");
		}
		if(null != outsiteCompanyID && !outsiteCompanyID.equals("")){
			sql.append("and outsiteService.outsiteCompany.outsiteCompanyID = :outsiteCompanyID ");
		}
		if(null != transportCompanyID && !transportCompanyID.equals("")){
			sql.append("and outsiteService.transportCompany.transportCompanyID = :transportCompanyID ");
		}
		
		sql.append("and outsiteService.status = '"+OutsiteService.SENT+"' ");
		
		if(!orderBy.equals("")){
			if(orderBy.equals("name")){
				orderBy = "outsiteService.customerName";
			}
			sql.append("order by "+orderBy+" "+orderType);
		}else{
			sql.append("order by outsiteService.outsiteServiceDate desc");
		}
		
		Query q = sessionFactory.getCurrentSession().createQuery(sql.toString()).setFetchSize(rows);
		if(null != name && !name.equals("")) {
			q.setString("name", name);
		}
		if(null != date && !date.equals("")) {
			q.setString("outsiteServiceDate", date);
		}
		if(null != type && !type.equals("")) {
			q.setString("type", type);
		}
		if(null != serialNo && !serialNo.equals("")) {
			q.setString("serialNo", serialNo);
		}
		if(null != refOutsiteJobID && !refOutsiteJobID.equals("")) {
			q.setString("refOutsiteJobID", refOutsiteJobID);
		}
		if(null != outsiteCompanyID && !outsiteCompanyID.equals("")) {
			q.setInteger("outsiteCompanyID", Integer.valueOf(outsiteCompanyID));
		}
		if(null != transportCompanyID && !transportCompanyID.equals("")) {
			q.setInteger("transportCompanyID", Integer.valueOf(transportCompanyID));
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
