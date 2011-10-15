package com.twobytes.sale.service;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	@Transactional
	public List<SaleOrder> selectByCriteria(String date, String employeeID,
			Integer rows, Integer page, String orderBy, String orderType) {
		List<SaleOrder> modelList = new ArrayList<SaleOrder>();
		try {
			modelList = saleOrderDAO.selectByCriteria(date, employeeID, rows, page, orderBy, orderType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelList;
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

}
