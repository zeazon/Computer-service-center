package com.twobytes.master.dao;

import java.util.List;

import com.twobytes.model.Product;

public interface ProductDAO {
	public boolean save(Product product) throws Exception;
	public Product selectByID(String productID) throws Exception;
	public List<Product> selectByCriteria(String typeID, String brandID, String modelID, String serialNo, Integer rows, Integer page, String orderBy, String orderType) throws Exception;
	public boolean edit(Product product) throws Exception;
	public boolean delete(Product product) throws Exception;
}
