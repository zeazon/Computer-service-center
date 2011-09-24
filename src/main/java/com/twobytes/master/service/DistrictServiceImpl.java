package com.twobytes.master.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twobytes.master.dao.DistrictDAO;
import com.twobytes.model.District;

@Service
public class DistrictServiceImpl implements DistrictService {

	@Autowired
	private DistrictDAO districtDAO;
	
	@Override
	@Transactional
	public List<District> getByProvince(Integer provinceID) {
		return districtDAO.getByProvince(provinceID);
	}

	@Override
	@Transactional
	public District selectByID(Integer districtID) {
		return districtDAO.selectByID(districtID);
	}

}
