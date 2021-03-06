package com.twobytes.master.dao;

import java.util.List;

import com.twobytes.model.Employee;

public interface EmployeeDAO {

	public boolean save(Employee emp) throws Exception;
	public Employee selectByID(Integer employeeID) throws Exception;
	public List<Employee> selectByCriteria(String name, String surname, Integer rows, Integer page, String orderBy, String orderType) throws Exception;
	public boolean edit(Employee emp) throws Exception;
	public boolean delete(Employee emp) throws Exception;
	public boolean checkValidLogin(String login) throws Exception;
	
	/**
	 * Check valid login before change login of specific employee
	 * 
	 * @param login
	 * @param employeeID
	 * @return
	 */
	public boolean checkValidLogin(String login, Integer employeeID) throws Exception;
	
	public List<Employee> getAll() throws Exception;
	public List<Employee> getByRole(Integer roleID) throws Exception;
	public List<Employee> getByRole(List<Integer> roleList) throws Exception;
}
