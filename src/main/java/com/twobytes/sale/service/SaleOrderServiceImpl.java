package com.twobytes.sale.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twobytes.master.service.ProductService;
import com.twobytes.model.Customer;
import com.twobytes.model.Product;
import com.twobytes.model.SaleOrder;
import com.twobytes.sale.dao.SaleOrderDAO;

@Service
public class SaleOrderServiceImpl implements SaleOrderService {

	@Autowired
	private SaleOrderDAO saleOrderDAO;
	
	@Autowired
	private ProductService productService;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean save(SaleOrder saleOrder, Product product) throws Exception {
		productService.save(product);
		saleOrder.setProduct(product);
		return saleOrderDAO.save(saleOrder);
	}

	@Override
	@Transactional
	public SaleOrder selectByID(Integer saleOrderID) {
		SaleOrder model = new SaleOrder();
		try {
			model = saleOrderDAO.selectByID(saleOrderID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@Override
	@Transactional
	public Map<String, Object> selectByCriteria(String date, String employeeID,
			Integer rows, Integer page, String orderBy, String orderType) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = saleOrderDAO.selectByCriteria(date, employeeID, rows, page, orderBy, orderType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean delete(Integer saleOrderID) throws Exception {
		SaleOrder so = saleOrderDAO.selectByID(saleOrderID);
		if(null != so){
			return saleOrderDAO.delete(so);
		}else{
			return false;
		}
	}

	@Override
	@Transactional
	public Customer getCustomerByProduct(String productID) {
		Customer customer = null;
		try{
			customer = saleOrderDAO.getCustomerByProduct(productID);
		}catch(Exception e){
			e.printStackTrace();
		}
		return customer;
	}

}
