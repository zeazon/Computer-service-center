package com.twobytes.master.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twobytes.master.dao.ProductDAO;
import com.twobytes.model.Product;
import com.twobytes.util.DocRunningUtil;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private DocRunningUtil docRunningUtil;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String save(Product product) throws Exception {
		if (product.getProductID() == null) {
			String productID = docRunningUtil.genDoc("computer");
			product.setProductID(productID);
		}
		if(productDAO.save(product)){
			return product.getProductID();
		}else{
			return "false";
		}
	}

	@Override
	@Transactional
	public Product selectByID(String productID){		
		Product product = new Product();
		try{
			product = productDAO.selectByID(productID); 
		}catch(Exception e){
			e.printStackTrace();
		}
		return product;
	}

	@Override
	@Transactional
	public Map<String, Object> selectByCriteria(String typeID, String brandID,
			String modelID, String serialNo, Integer rows, Integer page, String orderBy,
			String orderType){
		if(null != serialNo && !serialNo.equals("")) {
			serialNo = "%"+serialNo+"%";
		}
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = productDAO.selectByCriteria(typeID, brandID, modelID, serialNo, rows, page, orderBy, orderType);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean edit(Product product) throws Exception {
		return productDAO.edit(product);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean delete(String productID) throws Exception {
		Product product = productDAO.selectByID(productID);
		if(null != product){
			return productDAO.delete(product);
		}else{
			return false;
		}
	}

	@Override
	@Transactional
	public Long countBySerialNo(String serialNo) {
		Long result = new Long(0);
		try {
			result = productDAO.countBySerialNo(serialNo);
		} catch (Exception e) {
			result = new Long(-1);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	@Transactional
	public Long countBySerialNoForEdit(String serialNo, String productID) {
		Long result = new Long(0);
		try {
			result = productDAO.countBySerialNoForEdit(serialNo, productID);
		} catch (Exception e) {
			result = new Long(-1);
			e.printStackTrace();
		}
		return result;
	}

}
