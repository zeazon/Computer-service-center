package com.twobytes.master.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twobytes.master.dao.BrandDAO;
import com.twobytes.model.Brand;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandDAO brandDAO;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean save(Brand brand) throws Exception{
		return brandDAO.save(brand);
	}

	@Override
	@Transactional
	public Brand selectByID(Integer brandID) throws Exception{
		return brandDAO.selectByID(brandID);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean edit(Brand brand) throws Exception{
		return brandDAO.edit(brand);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean delete(Integer brandID) throws Exception{
		Brand brand = brandDAO.selectByID(brandID);
		if(null != brand){
			return brandDAO.delete(brand);
		}else{
			return false;
		}
	}

	@Override
	@Transactional
	public List<Brand> selectByCriteria(String name, Integer rows,
			Integer page, String orderBy, String orderType) throws Exception{
		if(null != name && !name.equals("")) {
			name = "%"+name+"%";
		}
		return brandDAO.selectByCriteria(name, rows, page, orderBy, orderType);
	}

	@Override
	@Transactional
	public List<Brand> getAll() {
		List<Brand> retList = new ArrayList<Brand>();
		try{
			retList = brandDAO.getAll();
		}catch(Exception e){
			e.printStackTrace();
		}
		return retList;
	}

}
