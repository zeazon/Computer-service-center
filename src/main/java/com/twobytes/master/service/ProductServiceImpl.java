package com.twobytes.master.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twobytes.master.dao.ProductDAO;
import com.twobytes.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean save(Product product) throws Exception {
		return productDAO.save(product);
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
	public List<Product> selectByCriteria(String typeID, String brandID,
			String modelID, String description, Integer rows, Integer page, String orderBy,
			String orderType){
		if(null != description && !description.equals("")) {
			description = "%"+description+"%";
		}
		List<Product> modelList = new ArrayList<Product>();
		try {
			modelList = productDAO.selectByCriteria(typeID, brandID, modelID, description, rows, page, orderBy, orderType);
		}catch(Exception e){
			e.printStackTrace();
		}
		return modelList;
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

}
