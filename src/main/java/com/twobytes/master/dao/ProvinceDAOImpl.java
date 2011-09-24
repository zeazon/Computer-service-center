package com.twobytes.master.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.twobytes.model.Province;

@Repository
public class ProvinceDAOImpl implements ProvinceDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Province> getAll() {
		Query q = sessionFactory.getCurrentSession().createQuery("from Province order by name ");
		List<Province> retList = q.list();
		return retList;
	}

	@Override
	public Province selectByID(Integer provinceID) {
		Province province = new Province();
		province = (Province)sessionFactory.getCurrentSession().get(Province.class, provinceID);
		return province;
	}

}
