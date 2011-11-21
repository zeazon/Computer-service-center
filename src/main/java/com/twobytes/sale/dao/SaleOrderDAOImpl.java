package com.twobytes.sale.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.twobytes.model.Customer;
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
	
	@Override
	public SaleOrder selectByID(Integer saleOrderID) throws Exception {
		SaleOrder so = new SaleOrder();
		so = (SaleOrder)sessionFactory.getCurrentSession().get(SaleOrder.class, saleOrderID);
		return so;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SaleOrder> selectByCriteria(String date, String employeeID,
			Integer rows, Integer page, String orderBy, String orderType)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("from SaleOrder as saleOrder where 1=1 ");
		if(null != date && !date.equals("")){
			sql.append("and DATE(saleOrder.saleDate) = :date ");
		}
		if(null != employeeID && !employeeID.equals("")){
			sql.append("and saleOrder.employee.employeeID like :employeeID ");
		}
		
		if(!orderBy.equals("")){
			if(orderBy.equals("name")){
				sql.append("order by saleOrder.employee.name "+orderType);
			}else if(orderBy.equals("customerName")){
				sql.append("order by saleOrder.customer.name "+orderType);
			}else{
				sql.append("order by "+orderBy+" "+orderType);
			}
		}else{
			sql.append("order by saleOrder.saleOrderID desc");
		}
		
		Query q = sessionFactory.getCurrentSession().createQuery(sql.toString());
		if(null != date && !date.equals("")) {
			q.setString("date", date);
		}
		if(null != employeeID && !employeeID.equals("")) {
			q.setInteger("employeeID", Integer.parseInt(employeeID));
		}

		List<SaleOrder> result = q.list();
		return result;
	}

	@Override
	public boolean delete(SaleOrder saleOrder) throws Exception {
		sessionFactory.getCurrentSession().delete(saleOrder);
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Customer getCustomerByProduct(String productID) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("from SaleOrder as saleOrder where saleOrder.product.productID = :productID ");
		
		Query q = sessionFactory.getCurrentSession().createQuery(sql.toString());
		q.setString("productID", productID);
		
		List<SaleOrder> result = q.list();
		SaleOrder so = result.get(0);
		Customer customer = so.getCustomer();
		return customer;
	}
	
}
