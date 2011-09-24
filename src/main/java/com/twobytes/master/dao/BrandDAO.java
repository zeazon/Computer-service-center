package com.twobytes.master.dao;

import java.util.List;

import com.twobytes.model.Brand;

public interface BrandDAO {
	
	public boolean save(Brand brand) throws Exception;
	public Brand selectByID(Integer brandID) throws Exception;
	public List<Brand> selectByCriteria(String name, Integer rows, Integer page, String orderBy, String orderType) throws Exception;
	public boolean edit(Brand brand) throws Exception;
	public boolean delete(Brand brand) throws Exception;
	public List<Brand> getAll() throws Exception;
}
