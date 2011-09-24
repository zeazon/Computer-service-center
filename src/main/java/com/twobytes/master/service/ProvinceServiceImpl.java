package com.twobytes.master.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twobytes.master.dao.ProvinceDAO;
import com.twobytes.model.Province;

@Service
public class ProvinceServiceImpl implements ProvinceService {

	@Autowired
	private ProvinceDAO provinceDAO;
	
	@Override
	@Transactional
	public List<Province> getAll() {
		return provinceDAO.getAll();
	}

	@Override
	@Transactional
	public Province selectByID(Integer provinceID) {
		return provinceDAO.selectByID(provinceID);
	}

}
