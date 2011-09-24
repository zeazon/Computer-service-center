package com.twobytes.master.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twobytes.master.dao.CustomerTypeDAO;
import com.twobytes.model.CustomerType;

@Service
public class CustomerTypeServiceImpl implements CustomerTypeService {

	@Autowired
	private CustomerTypeDAO customerTypeDAO;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean save(CustomerType customerType) throws Exception {
		return customerTypeDAO.save(customerType);
	}

	@Override
	@Transactional
	public CustomerType selectByID(String customerTypeID) {
		CustomerType ct = new CustomerType();
		try{
			ct = customerTypeDAO.selectByID(customerTypeID);
		}catch(Exception e){
			e.printStackTrace();
		}
		return ct;
	}

	@Override
	@Transactional
	public List<CustomerType> selectByCriteria(String name, Integer rows,
			Integer page, String orderBy, String orderType) {
		if(null != name && !name.equals("")) {
			name = "%"+name+"%";
		}
		List<CustomerType> retList = new ArrayList<CustomerType>();
		try{
			retList = customerTypeDAO.selectByCriteria(name, rows, page, orderBy, orderType);
		}catch(Exception e){
			e.printStackTrace();
		}
		return retList;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean edit(CustomerType customerType) throws Exception {
		return customerTypeDAO.edit(customerType);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean delete(String customerTypeID) throws Exception {
		CustomerType customerType = customerTypeDAO.selectByID(customerTypeID);
		if(null != customerType){
			return customerTypeDAO.delete(customerType);
		}else{
			return false;
		}
	}

	@Override
	@Transactional
	public List<CustomerType> getAll() {
		List<CustomerType> retList = new ArrayList<CustomerType>();
		try{
			retList = customerTypeDAO.getAll();
		}catch(Exception e){
			e.printStackTrace();
		}
		return retList;
	}

}
