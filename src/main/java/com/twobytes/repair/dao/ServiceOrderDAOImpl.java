package com.twobytes.repair.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.twobytes.model.ServiceOrder;

@Repository
public class ServiceOrderDAOImpl implements ServiceOrderDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean save(ServiceOrder serviceOrder) throws Exception{
		Session session = sessionFactory.getCurrentSession();
//		try{
			session.saveOrUpdate(serviceOrder);
//		}catch(Exception e){
//			e.printStackTrace();
//			return false;
//		}
		//throw new Exception();
		return true;
	}

	@Override
	public ServiceOrder selectByID(String serviceOrderID) throws Exception{
		ServiceOrder so = new ServiceOrder();
		so = (ServiceOrder)sessionFactory.getCurrentSession().get(ServiceOrder.class, serviceOrderID);
		return so;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ServiceOrder> selectByCriteria(String name,
			String startDate, String endDate, String type, String serialNo,
			Integer rows, Integer page, String orderBy, String orderType) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("from ServiceOrder as serviceOrder where 1=1 ");
		if(null != name && !name.equals("")){
			sql.append("and serviceOrder.customer.name like :name ");
		}
//		if(null != surname && !surname.equals("")){
//			sql.append("and serviceOrder.customer.surname like :surname ");
//		}
		if((null != startDate && !startDate.equals("")) && (null != endDate && !endDate.equals(""))){
			sql.append("and DATE(serviceOrderDate) between :startDate and :endDate ");
		}else if((null != startDate && !startDate.equals("")) && (null == endDate || endDate.equals(""))){
			sql.append("and DATE(serviceOrderDate) >= :startDate ");
		}else if((null == startDate || startDate.equals("")) && (null != endDate && !endDate.equals(""))){
			sql.append("and DATE(serviceOrderDate) <= :endDate ");
		}
		if(null != type && !type.equals("")){
			sql.append("and serviceOrder.product.type.typeID = :type ");
		}
		if(null != serialNo && !serialNo.equals("")){
			sql.append("and serviceOrder.product.serialNo like :serialNo ");
		}
		
		sql.append("and serviceOrder.status != 'cancel' ");
		
		if(!orderBy.equals("")){
			if(orderBy.equals("name")){
				orderBy = "serviceOrder.customer.name";
			}else if(orderBy.equals("surname")){
				orderBy = "serviceOrder.customer.surname";
			}else if(orderBy.equals("fullName")){
				orderBy = "serviceOrder.customer.name "+orderType+", serviceOrder.customer.surname"; 
			}
			sql.append("order by "+orderBy+" "+orderType);
		}else{
			sql.append("order by serviceOrder.serviceOrderDate desc");
		}
		
		Query q = sessionFactory.getCurrentSession().createQuery(sql.toString());
		if(null != name && !name.equals("")) {
			q.setString("name", name);
		}
//		if(null != surname && !surname.equals("")) {
//			q.setString("surname", surname);
//		}
		if((null != startDate && !startDate.equals("")) && (null != endDate && !endDate.equals(""))){
			q.setString("startDate", startDate);
			q.setString("endDate", endDate);
		}else if((null != startDate && !startDate.equals("")) && (null == endDate || endDate.equals(""))){
			q.setString("startDate", startDate);
		}else if((null == startDate || startDate.equals("")) && (null != endDate && !endDate.equals(""))){
			q.setString("endDate", endDate);
		}
		if(null != type && !type.equals("")) {
			q.setString("type", type);
		}
		if(null != serialNo && !serialNo.equals("")) {
			q.setString("serialNo", serialNo);
		}
		List<ServiceOrder> result = q.list();
		return result;
	}
	
	@Override
	public boolean edit(ServiceOrder serviceOrder) throws Exception{
		Session session = sessionFactory.getCurrentSession();
//		try{
			session.update(serviceOrder);
//		}catch(Exception e){
//			e.printStackTrace();
//			return false;
//		}
		return true;
	}

	@Override
	public boolean delete(ServiceOrder serviceOrder, Integer employeeID) throws Exception{
//		try{
			serviceOrder.setStatus(ServiceOrder.CANCEL);
			serviceOrder.setUpdatedDate(new Date());
			serviceOrder.setUpdatedBy(employeeID);
			sessionFactory.getCurrentSession().update(serviceOrder);
//		}catch(Exception e){
//			e.printStackTrace();
//			return false;
//		}
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ServiceOrder> selectNewSOByCriteria(String name,
			String date, String type, Integer rows,
			Integer page, String orderBy, String orderType) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("from ServiceOrder as serviceOrder where 1=1 ");
		if(null != name && !name.equals("")){
			sql.append("and serviceOrder.customer.name like :name ");
		}
		if(null != date && !date.equals("")){
			sql.append("and DATE(serviceOrderDate) = :serviceOrderDate ");
		}
		if(null != type && !type.equals("")){
			sql.append("and type = :type ");
		}
		
		sql.append("and serviceOrder.status = 'new' ");
		
		if(!orderBy.equals("")){
			if(orderBy.equals("name")){
				orderBy = "serviceOrder.customer.name";
			}else if(orderBy.equals("tel")){
				orderBy = "serviceOrder.customer.tel";
			}else if(orderBy.equals("mobileTel")){
				orderBy = "serviceOrder.customer.mobileTel";
			}
			sql.append("order by "+orderBy+" "+orderType);
		}else{
			sql.append("order by serviceOrder.serviceOrderDate desc");
		}
		
		Query q = sessionFactory.getCurrentSession().createQuery(sql.toString());
		if(null != name && !name.equals("")) {
			q.setString("name", name);
		}
		if(null != date && !date.equals("")) {
			q.setString("serviceOrderDate", date);
		}
		if(null != type && !type.equals("")) {
			q.setString("type", type);
		}
		List<ServiceOrder> result = q.list();
		return result;

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ServiceOrder> selectSOForCloseByCriteria(String name,
			String startDate, String endDate, String type, String serialNo,
			Integer rows, Integer page, String orderBy, String orderType)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("from ServiceOrder as serviceOrder where 1=1 ");
		if(null != name && !name.equals("")){
			sql.append("and serviceOrder.customer.name like :name ");
		}else
		if((null != startDate && !startDate.equals("")) && (null != endDate && !endDate.equals(""))){
			sql.append("and DATE(serviceOrderDate) between :startDate and :endDate ");
		}else if((null != startDate && !startDate.equals("")) && (null == endDate || endDate.equals(""))){
			sql.append("and DATE(serviceOrderDate) >= :startDate ");
		}else if((null == startDate || startDate.equals("")) && (null != endDate && !endDate.equals(""))){
			sql.append("and DATE(serviceOrderDate) <= :endDate ");
		}
		if(null != type && !type.equals("")){
			sql.append("and serviceOrder.product.type.typeID = :type ");
		}
		if(null != serialNo && !serialNo.equals("")){
			sql.append("and serviceOrder.product.serialNo like :serialNo ");
		}
		
//		sql.append("and serviceOrder.status in ('fixing','received') ");
		
		if(!orderBy.equals("")){
			if(orderBy.equals("name")){
				orderBy = "serviceOrder.customer.name";
			}else if(orderBy.equals("surname")){
				orderBy = "serviceOrder.customer.surname";
			}else if(orderBy.equals("fullName")){
				orderBy = "serviceOrder.customer.name "+orderType+", serviceOrder.customer.surname"; 
			}
			sql.append("order by "+orderBy+" "+orderType);
		}else{
			sql.append("order by serviceOrder.serviceOrderDate desc");
		}
		
		Query q = sessionFactory.getCurrentSession().createQuery(sql.toString());
		if(null != name && !name.equals("")) {
			q.setString("name", name);
		}
		if((null != startDate && !startDate.equals("")) && (null != endDate && !endDate.equals(""))){
			q.setString("startDate", startDate);
			q.setString("endDate", endDate);
		}else if((null != startDate && !startDate.equals("")) && (null == endDate || endDate.equals(""))){
			q.setString("startDate", startDate);
		}else if((null == startDate || startDate.equals("")) && (null != endDate && !endDate.equals(""))){
			q.setString("endDate", endDate);
		}
		if(null != type && !type.equals("")) {
			q.setString("type", type);
		}
		if(null != serialNo && !serialNo.equals("")) {
			q.setString("serialNo", serialNo);
		}
		List<ServiceOrder> result = q.list();
		return result;
	}

}