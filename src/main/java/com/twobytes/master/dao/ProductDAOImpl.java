package com.twobytes.master.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.twobytes.model.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean save(Product product) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		return true;
	}

	@Override
	public Product selectByID(String productID) throws Exception {
		Product product = new Product();
		product = (Product)sessionFactory.getCurrentSession().get(Product.class, productID);
		return product;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Product> selectByCriteria(String typeID, String brandID,
			String modelID, String description, Integer rows, Integer page, String orderBy,
			String orderType) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("from Product product where 1=1 ");
		if(null != typeID && !typeID.equals("")){
			sql.append("and product.type.typeID = :type ");
		}
		if(null != brandID && !brandID.equals("")){
			sql.append("and product.brand.brandID = :brand ");
		}
		if(null != modelID && !modelID.equals("")){
			sql.append("and product.model.modelID = :model ");
		}
		if(null != description && !description.equals("")){
			sql.append("and description like :description ");
		}
		
		if(!orderBy.equals("")){
			if(orderBy.equals("typeName")){
				sql.append("order by product.type.name "+orderType);
			}else if(orderBy.equals("brandName")){
				sql.append("order by product.brand.name "+orderType);
			}else if(orderBy.equals("modelName")){
				sql.append("order by product.model.name "+orderType);
			}else{
				sql.append("order by "+orderBy+" "+orderType);
			}
		}
		
		Query q = sessionFactory.getCurrentSession().createQuery(sql.toString());
		if(null != typeID && !typeID.equals("")){
			q.setString("type", typeID);
		}
		if(null != brandID && !brandID.equals("")){
			q.setInteger("brand", Integer.valueOf(brandID));
		}
		if(null != modelID && !modelID.equals("")){
			q.setInteger("model", Integer.valueOf(modelID));
		}
		if(null != description && !description.equals("")){
			q.setString("description", description);
		}
		
		List<Product> result = q.list();
		return result;
	}

	@Override
	public boolean edit(Product product) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.update(product);
		return true;
	}

	@Override
	public boolean delete(Product product) throws Exception {
		sessionFactory.getCurrentSession().delete(product);
		return true;
	}

}
