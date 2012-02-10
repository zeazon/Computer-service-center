package com.twobytes.sale.service;

import java.util.Map;

import com.twobytes.model.Customer;
import com.twobytes.model.Product;
import com.twobytes.model.SaleOrder;

public interface SaleOrderService {
	public boolean save(SaleOrder saleOrder) throws Exception;
	public boolean save(SaleOrder saleOrder, Product product) throws Exception;
	public SaleOrder selectByID(Integer saleOrderID);
	public Map<String, Object> selectByCriteria(String date, String employeeID, Integer rows, Integer page, String orderBy, String orderType);
	public boolean delete(Integer saleOrderID) throws Exception;
	public Customer getCustomerByProduct(String productID);
}