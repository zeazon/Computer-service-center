package com.twobytes.master.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
	public Map<String, Object> selectByCriteria(String typeID, String brandID,
			String modelID, String serialNo, Integer rows, Integer page, String orderBy,
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
		if(null != serialNo && !serialNo.equals("")){
			sql.append("and serialNo like :serialNo ");
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
		
		Query q = sessionFactory.getCurrentSession().createQuery(sql.toString()).setFirstResult(rows*page - rows).setMaxResults(rows).setFetchSize(rows);
		if(null != typeID && !typeID.equals("")){
			q.setString("type", typeID);
		}
		if(null != brandID && !brandID.equals("")){
			q.setInteger("brand", Integer.valueOf(brandID));
		}
		if(null != modelID && !modelID.equals("")){
			q.setInteger("model", Integer.valueOf(modelID));
		}
		if(null != serialNo && !serialNo.equals("")){
			q.setString("serialNo", serialNo);
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<Product> list = q.list();
		result.put("list", list);
		
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Product.class, "product");
		if(null != typeID && !typeID.equals("")){
			criteria.createCriteria("product.type" , "type");
			criteria.add(Restrictions.eq("type.typeID", typeID));
		}
		if(null != brandID && !brandID.equals("")){
			criteria.createCriteria("product.brand" , "brand");
			criteria.add(Restrictions.eq("brand.brandID", Integer.valueOf(brandID)));
		}
		if(null != modelID && !modelID.equals("")){
			criteria.createCriteria("product.model" , "model");
			criteria.add(Restrictions.eq("model.modelID", Integer.valueOf(modelID)));
		}
		if(null != serialNo && !serialNo.equals("")){
			criteria.add(Restrictions.like("serialNo", serialNo));
		}
		criteria.setProjection(Projections.rowCount());
		
		result.put("maxRows", criteria.list().get(0));
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
