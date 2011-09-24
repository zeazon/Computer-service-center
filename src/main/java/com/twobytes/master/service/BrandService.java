package com.twobytes.master.service;

import java.util.List;

import com.twobytes.model.Brand;

public interface BrandService {

	public boolean save(Brand brand) throws Exception;
	public Brand selectByID(Integer brandID) throws Exception;
	public List<Brand> selectByCriteria(String name, Integer rows, Integer page, String orderBy, String orderType) throws Exception;
	public boolean edit(Brand brand) throws Exception;
	public boolean delete(Integer brandID) throws Exception;
	public List<Brand> getAll();
}
