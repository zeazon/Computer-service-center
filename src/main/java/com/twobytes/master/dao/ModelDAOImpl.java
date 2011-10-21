package com.twobytes.master.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.twobytes.model.Model;

@Repository
public class ModelDAOImpl implements ModelDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean save(Model model) throws Exception {
		sessionFactory.getCurrentSession().saveOrUpdate(model);
		return true;
	}

	@Override
	public Model selectByID(Integer modelID) throws Exception {
		Model model = new Model();
		model = (Model)sessionFactory.getCurrentSession().get(Model.class, modelID);
		return model;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Model> selectByCriteria(String name, String typeID, Integer brandID, Integer rows,
			Integer page, String orderBy, String orderType) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("from Model as m where 1=1 ");
		if(null != name && !name.equals("")){
			sql.append("and m.name like :name ");
		}
		if(null != typeID && !typeID.equals("")){
			sql.append("and m.type.typeID = :typeID ");
		}
		if(null != brandID){
			sql.append("and m.brand.brandID = :brandID ");
		}
		
		if(!orderBy.equals("")){
			if(orderBy.equals("typeName")){
				sql.append("order by m.type.name "+orderType);
			}else if(orderBy.equals("brandName")){
				sql.append("order by m.brand.name "+orderType);
			}else{
				sql.append("order by "+orderBy+" "+orderType);
			}
		}
		Query q = sessionFactory.getCurrentSession().createQuery(sql.toString());
		if(null != name && !name.equals("")) {
			q.setString("name", name);
		}
		if(null != typeID && !typeID.equals("")){
			q.setString("typeID", typeID);
		}
		if(null != brandID){
			q.setInteger("brandID", brandID);
		}

		List<Model> result = q.list();
		return result;
	}

	@Override
	public boolean edit(Model model) throws Exception {
		sessionFactory.getCurrentSession().update(model);
		return true;
	}

	@Override
	public boolean delete(Model model) throws Exception {
		sessionFactory.getCurrentSession().delete(model);
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Model> getModelByTypeAndBrand(String typeID, Integer brandID) {
		StringBuilder sql = new StringBuilder();
		sql.append("from Model as m where m.type.typeID = :typeID and m.brand.brandID = :brandID ");
		
		Query q = sessionFactory.getCurrentSession().createQuery(sql.toString());
		q.setString("typeID", typeID);
		q.setInteger("brandID", brandID);

		List<Model> result = q.list();
		return result;
	}

}
