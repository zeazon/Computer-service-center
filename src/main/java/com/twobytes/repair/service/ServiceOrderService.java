package com.twobytes.repair.service;

import java.util.List;

import com.twobytes.model.IssuePart;
import com.twobytes.model.ServiceList;
import com.twobytes.model.ServiceOrder;
import com.twobytes.report.form.NumRepairReportForm;

public interface ServiceOrderService {
	public String save(ServiceOrder serviceOrder) throws Exception;
	public ServiceOrder selectByID(String serviceOrderID);
	public List<ServiceOrder> selectByCriteria(String name, String startDate, String endDate, String type, String serialNo, Integer rows, Integer page, String orderBy, String orderType);
	public boolean edit(ServiceOrder serviceOrder) throws Exception;
	public boolean delete(String serviceOrderID, Integer employeeID) throws Exception;
	
	public List<ServiceOrder> selectNewSOByCriteria(String name, String date, String type, Integer rows, Integer page, String orderBy, String orderType);
	public List<ServiceOrder> selectSOForCloseByCriteria(String name, String startDate, String endDate, String type, String serialNo, Integer rows, Integer page, String orderBy, String orderType);
	public boolean close(ServiceOrder so, List<IssuePart> issuePartList, List<ServiceList> serviceList) throws Exception;
	public List<ServiceOrder> selectFixedSOByCriteria(String name, String date, String type, Integer rows, Integer page, String orderBy, String orderType);
	
	public List<ServiceOrder> getRepairReport(String startDate, String endDate);
	public NumRepairReportForm getNumRepairReport(String date);
}
