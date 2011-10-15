package com.twobytes.sale.dao;

import java.util.List;

import com.twobytes.model.SaleOrder;

public interface SaleOrderDAO {
	public boolean save(SaleOrder saleOrder) throws Exception;
	public SaleOrder selectByID(Integer saleOrderID) throws Exception;
	public List<SaleOrder> selectByCriteria(String date, String employeeID, Integer rows, Integer page, String orderBy, String orderType) throws Exception;
	public boolean delete(SaleOrder saleOrder) throws Exception;
}
