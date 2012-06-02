package com.twobytes.master.service;

import java.util.List;

import com.twobytes.model.Employee;

public interface EmployeeService {
	
	public boolean save(Employee emp) throws Exception;
	public Employee selectByID(Integer employeeID) throws Exception;
	public List<Employee> selectByCriteria(String name, String surname, Integer rows, Integer page, String orderBy, String orderType) throws Exception;
	public boolean edit(Employee emp) throws Exception;
	public boolean delete(Integer employeeID) throws Exception;
	public boolean checkValidLogin(String login) throws Exception;
	
	/**
	 * Check valid login before change login of specific employee
	 * 
	 * @param login
	 * @param employeeID
	 * @return
	 */
	public boolean checkValidLogin(String login, Integer employeeID) throws Exception;
	
	public List<Employee> getAll();
	public List<Employee> getByRole(Integer roleID);
}