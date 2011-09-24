package com.twobytes.master.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.twobytes.model.Subdistrict;

@Repository
public class SubdistrictDAOImpl implements SubdistrictDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Subdistrict> getByDistrict(Integer districtID) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Subdistrict where districtID = :districtID order by name ");
		q.setInteger("districtID", districtID);
		List<Subdistrict> retList = q.list();
		return retList;
	}

	@Override
	public Subdistrict selectByID(Integer subdistrictID) {
		Subdistrict sd = new Subdistrict();
		sd = (Subdistrict)sessionFactory.getCurrentSession().get(Subdistrict.class, subdistrictID);
		return sd;
	}

}
