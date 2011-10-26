package com.twobytes.repair.service;

import java.util.List;

import com.twobytes.model.ServiceOrder;

public interface ServiceOrderService {
	public String save(ServiceOrder serviceOrder) throws Exception;
	public ServiceOrder selectByID(String serviceOrderID);
	public List<ServiceOrder> selectByCriteria(String name, String startDate, String endDate, String type, String serialNo, Integer rows, Integer page, String orderBy, String orderType);
	public boolean edit(ServiceOrder serviceOrder) throws Exception;
	public boolean delete(String serviceOrderID, Integer employeeID) throws Exception;
	
	public List<ServiceOrder> selectNewSOByCriteria(String name, String date, String type, Integer rows, Integer page, String orderBy, String orderType);
	public List<ServiceOrder> selectSOForCloseByCriteria(String name, String startDate, String endDate, String type, String serialNo, Integer rows, Integer page, String orderBy, String orderType);
}
