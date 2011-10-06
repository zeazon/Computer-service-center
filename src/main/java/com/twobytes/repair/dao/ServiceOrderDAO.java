package com.twobytes.repair.dao;

import java.util.List;

import com.twobytes.model.ServiceOrder;

public interface ServiceOrderDAO {
	public boolean save(ServiceOrder serviceOrder) throws Exception;
	public ServiceOrder selectByID(String serviceOrderID) throws Exception;
	public List<ServiceOrder> selectByCriteria(String name, String startDate, String endDate, String type, String serialNo, Integer rows, Integer page, String orderBy, String orderType) throws Exception;
	public boolean edit(ServiceOrder serviceOrder) throws Exception;
	public boolean delete(ServiceOrder serviceOrder, Integer employeeID) throws Exception;
	
	public List<ServiceOrder> selectNewSOByCriteria(String name, String date, String type, Integer rows, Integer page, String orderBy, String orderType) throws Exception;
	public List<ServiceOrder> selectSOForCloseByCriteria(String name, String startDate, String endDate, String type, String serialNo, Integer rows, Integer page, String orderBy, String orderType) throws Exception;
}
