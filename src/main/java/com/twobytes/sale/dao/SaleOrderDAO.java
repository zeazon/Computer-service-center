package com.twobytes.sale.dao;

import com.twobytes.model.SaleOrder;

public interface SaleOrderDAO {
	public boolean save(SaleOrder saleOrder) throws Exception;
}
