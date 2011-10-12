package com.twobytes.sale.service;

import com.twobytes.model.Product;
import com.twobytes.model.SaleOrder;

public interface SaleOrderService {
	public boolean save(SaleOrder saleOrder, Product product) throws Exception;
}
