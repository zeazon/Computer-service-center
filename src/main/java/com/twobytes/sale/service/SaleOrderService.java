package com.twobytes.sale.service;

import java.util.List;

import com.twobytes.model.Customer;
import com.twobytes.model.Product;
import com.twobytes.model.SaleOrder;

public interface SaleOrderService {
	public boolean save(SaleOrder saleOrder, Product product) throws Exception;
	public SaleOrder selectByID(Integer saleOrderID);
	public List<SaleOrder> selectByCriteria(String date, String employeeID, Integer rows, Integer page, String orderBy, String orderType);
	public boolean delete(Integer saleOrderID) throws Exception;
	public Customer getCustomerByProduct(String productID);
}
