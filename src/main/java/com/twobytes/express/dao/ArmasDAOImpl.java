package com.twobytes.express.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.twobytes.express.model.Armas;

@Repository
public class ArmasDAOImpl implements ArmasDAO {

	@Autowired
	private SessionFactory expressSessionFactory;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Armas> getAll() {
		Query q = expressSessionFactory.getCurrentSession().createQuery("from Armas where cuscod != '999' order by cusnam ");
		List<Armas> retList = q.list();
		return retList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Armas> selectByCriteria(String name, Integer rows,
			Integer page, String orderBy, String orderType) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("from Armas where cuscod != '999' ");
		
		if(null != name && !name.equals("")){
			sql.append("and cusnam like :name ");
		}
		
		if(!orderBy.equals("")){
			if(orderBy.equals("fullName")){
				orderBy = "prenam "+orderType+", cusnam";
			}
			sql.append("order by "+orderBy+" "+orderType);
		}else{
			sql.append("order by prenam asc, cusnam asc");	
		}
		Query q = expressSessionFactory.getCurrentSession().createQuery(sql.toString());
		if(null != name && !name.equals("")) {
			q.setString("name", name);
		}
		List<Armas> result = q.list();
		return result;
	}

	@Override
	public Armas selectByID(String cuscod) throws Exception {
		Armas armas = (Armas)expressSessionFactory.getCurrentSession().get(Armas.class, cuscod);
		return armas;
	}

}
