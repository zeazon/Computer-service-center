package com.twobytes.master.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.twobytes.model.District;

@Repository
public class DistrictDAOImpl implements DistrictDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<District> getByProvince(Integer provinceID) {
		Query q = sessionFactory.getCurrentSession().createQuery("from District where provinceID = :provinceID order by name ");
		q.setInteger("provinceID", provinceID);
		List<District> retList = q.list();
		return retList;
	}

	@Override
	public District selectByID(Integer districtID) {
		District district = new District();
		district = (District)sessionFactory.getCurrentSession().get(District.class, districtID);
		return district;
	}

}
