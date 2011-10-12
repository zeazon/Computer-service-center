package com.twobytes.master.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.twobytes.model.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean save(Employee emp) throws Exception{
//		Session session = sessionFactory.getCurrentSession();
//		Transaction tx = session.beginTransaction();
//		try{
//			session.save(emp);
//			if (!tx.wasCommitted()) tx.commit();
//		}catch (Exception e) {
//			e.printStackTrace();
//			tx.rollback();
//		}
		
		Session session = sessionFactory.openSession();
//		Transaction tx = session.beginTransaction();
//		try{
			session.saveOrUpdate(emp);
//			if (!tx.wasCommitted()) tx.commit();
//		}catch (Exception e) {
//			e.printStackTrace();
//			tx.rollback();
//			return false;
//		}finally{
//			session.close();
//		}
		return true;
	}

	@Override
	public Employee selectByID(Integer employeeID) throws Exception{
		Employee emp = new Employee();
		emp = (Employee)sessionFactory.getCurrentSession().get(Employee.class, employeeID);
		return emp;
	}

	@Override
	public boolean edit(Employee emp) throws Exception{
//		try{
			Session session = sessionFactory.getCurrentSession();
//			Transaction tx = session.beginTransaction();
			session.update(emp);
//			tx.commit();
//		}catch(Exception e){
//			e.printStackTrace();
//			return false;
//		}
		return true;
	}

	@Override
	public boolean delete(Employee emp) throws Exception{
//		try{
			sessionFactory.getCurrentSession().delete(emp);
//		}catch(Exception e){
//			e.printStackTrace();
//			return false;
//		}
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> selectByCriteria(String name, String surname, Integer rows, Integer page, String orderBy, String orderType) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("from Employee emp where 1=1 ");
		if(null != name && !name.equals("")){
			sql.append("and emp.name like :name ");
		}
		if(null != surname && !surname.equals("")){
			sql.append("and emp.surname like :surname ");
		}
		if(!orderBy.equals("")){
			sql.append("order by "+orderBy+" "+orderType);
		}
//		Query q = sessionFactory.getCurrentSession().createQuery(sql.toString()).setFirstResult(rows*page - rows).setMaxResults(rows);
		Query q = sessionFactory.getCurrentSession().createQuery(sql.toString());
		if(null != name && !name.equals("")) {
			q.setString("name", name);
		}
		if(null != surname && !surname.equals("")) {
			q.setString("surname", surname);
		}
		List<Employee> result = q.list();
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean checkValidLogin(String login) throws Exception{
		Query q = sessionFactory.getCurrentSession().createQuery("from Employee emp where emp.login = :login ");
		q.setString("login", login);
		List<Employee> result = q.list();
		if(result.size() > 0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean checkValidLogin(String login, Integer employeeID) throws Exception{
		Query q = sessionFactory.getCurrentSession().createQuery("from Employee emp where emp.login = :login and emp.employeeID != :employeeID ");
		q.setString("login", login);
		q.setInteger("employeeID", employeeID);
		List<Employee> result = q.list();
		if(result.size() > 0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> getAll() throws Exception {
		Query q = sessionFactory.getCurrentSession().createQuery("from Employee order by name ");
		List<Employee> retList = q.list();
		return retList;
	}
	
}
