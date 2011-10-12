package com.twobytes.master.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twobytes.master.dao.EmployeeDAO;
import com.twobytes.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean save(Employee emp) throws Exception{
		return employeeDAO.save(emp);
	}

	@Override
	@Transactional
	public Employee selectByID(Integer employeeID) throws Exception{
		return employeeDAO.selectByID(employeeID);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean edit(Employee emp) throws Exception{
		return employeeDAO.edit(emp);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean delete(Integer employeeID) throws Exception{
		Employee emp = employeeDAO.selectByID(employeeID);
		if(null != emp){
			return employeeDAO.delete(emp);
		}else{
			return false;
		}
		
	}

	@Override
	@Transactional
	public List<Employee> selectByCriteria(String name, String surname, Integer rows, Integer page, String orderBy, String orderType) throws Exception{
		if(null != name && !name.equals("")) {
			name = "%"+name+"%";
		}
		if(null != surname && !surname.equals("")) {
			surname = "%"+surname+"%";
		}
		return employeeDAO.selectByCriteria(name, surname, rows, page, orderBy, orderType);
	}

	@Override
	@Transactional
	public boolean checkValidLogin(String login) throws Exception{
		return employeeDAO.checkValidLogin(login);
	}

	@Override
	@Transactional
	public boolean checkValidLogin(String login, Integer employeeID) throws Exception{
		return employeeDAO.checkValidLogin(login, employeeID);
	}

	@Override
	@Transactional
	public List<Employee> getAll() {
		List<Employee> retList = new ArrayList<Employee>();
		try{
			retList = employeeDAO.getAll();
		}catch(Exception e){
			e.printStackTrace();
		}
		return retList;
	}

}
