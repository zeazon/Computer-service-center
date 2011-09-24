package com.twobytes.master.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twobytes.master.dao.SubdistrictDAO;
import com.twobytes.model.Subdistrict;

@Service
public class SubdistrictServiceImpl implements SubdistrictService {

	@Autowired
	private SubdistrictDAO subdistrictDAO;
	
	@Override
	@Transactional
	public List<Subdistrict> getByDistrict(Integer districtID) {
		return subdistrictDAO.getByDistrict(districtID);
	}

	@Override
	@Transactional
	public Subdistrict selectByID(Integer subdistrictID) {
		return subdistrictDAO.selectByID(subdistrictID);
	}

}
