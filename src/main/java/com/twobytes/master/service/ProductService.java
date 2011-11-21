package com.twobytes.master.service;

import java.util.List;

import com.twobytes.model.Product;

public interface ProductService {
	public String save(Product product) throws Exception;
	public Product selectByID(String productID);
	public List<Product> selectByCriteria(String typeID, String brandID, String modelID, String serialNo, Integer rows, Integer page, String orderBy, String orderType);
	public boolean edit(Product product) throws Exception;
	public boolean delete(String productID) throws Exception;
}
