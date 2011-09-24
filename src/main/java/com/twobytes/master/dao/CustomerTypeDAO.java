package com.twobytes.master.dao;

import java.util.List;

import com.twobytes.model.CustomerType;

public interface CustomerTypeDAO {
	public boolean save(CustomerType customerType) throws Exception;
	public CustomerType selectByID(String customerTypeID) throws Exception;
	public List<CustomerType> selectByCriteria(String name, Integer rows, Integer page, String orderBy, String orderType) throws Exception;
	public boolean edit(CustomerType customerType) throws Exception;
	public boolean delete(CustomerType customerType) throws Exception;
	public List<CustomerType> getAll() throws Exception;
}
