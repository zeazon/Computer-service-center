package com.twobytes.master.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twobytes.master.dao.CustomerDAO;
import com.twobytes.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean save(Customer customer) throws Exception{
		return customerDAO.save(customer);
	}

	@Override
	@Transactional
	public Customer selectByID(String customerID) throws Exception{
		return customerDAO.selectByID(customerID);
	}

	@Override
	@Transactional
	public List<Customer> selectByCriteria(String name,
			Integer rows, Integer page, String orderBy, String orderType) throws Exception{
		if(null != name && !name.equals("")) {
			name = "%"+name+"%";
		}
		return customerDAO.selectByCriteria(name, rows, page, orderBy, orderType);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean edit(Customer customer) throws Exception{
		return customerDAO.edit(customer);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean delete(String customerID) throws Exception{
		Customer customer = customerDAO.selectByID(customerID);
		if(null != customer){
			return customerDAO.delete(customer);
		}else{
			return false;
		}
	}

}
