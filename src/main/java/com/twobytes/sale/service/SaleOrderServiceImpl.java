package com.twobytes.sale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twobytes.master.dao.ProductDAO;
import com.twobytes.model.Product;
import com.twobytes.model.SaleOrder;
import com.twobytes.sale.dao.SaleOrderDAO;

@Service
public class SaleOrderServiceImpl implements SaleOrderService {

	@Autowired
	private SaleOrderDAO saleOrderDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean save(SaleOrder saleOrder, Product product) throws Exception {
		productDAO.save(product);
		saleOrder.setProduct(product);
		return saleOrderDAO.save(saleOrder);
	}

}
