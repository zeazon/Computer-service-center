package com.twobytes.master.service;

import java.util.List;

import com.twobytes.model.CustomerType;

public interface CustomerTypeService {
	public boolean save(CustomerType customerType) throws Exception;
	public CustomerType selectByID(String customerTypeID);
	public List<CustomerType> selectByCriteria(String name, Integer rows, Integer page, String orderBy, String orderType);
	public boolean edit(CustomerType customerType) throws Exception;
	public boolean delete(String customerTypeID) throws Exception;
	public List<CustomerType> getAll();
}
