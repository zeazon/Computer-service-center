package com.twobytes.sale.dao;

import java.util.Map;

import com.twobytes.model.Customer;
import com.twobytes.model.SaleOrder;

public interface SaleOrderDAO {
	public boolean save(SaleOrder saleOrder) throws Exception;
	public SaleOrder selectByID(Integer saleOrderID) throws Exception;
	public Map<String, Object> selectByCriteria(String date, String employeeID, Integer rows, Integer page, String orderBy, String orderType) throws Exception;
	public boolean delete(SaleOrder saleOrder) throws Exception;
	
	public Customer getCustomerByProduct(String productID) throws Exception;
}
