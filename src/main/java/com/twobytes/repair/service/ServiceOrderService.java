package com.twobytes.repair.service;

import java.util.List;
import java.util.Map;

import com.twobytes.model.IssuePart;
import com.twobytes.model.ServiceList;
import com.twobytes.model.ServiceOrder;
import com.twobytes.report.form.NumRepairByEmpReportForm;
import com.twobytes.report.form.NumRepairReportForm;
import com.twobytes.report.form.SumAmountReportForm;

public interface ServiceOrderService {
	public String save(ServiceOrder serviceOrder) throws Exception;
	public ServiceOrder selectByID(String serviceOrderID);
	public List<ServiceOrder> selectByCriteria(String name, String startDate, String endDate, String type, String serialNo, Integer rows, Integer page, String orderBy, String orderType);
	public Map<String, Object> selectByCriteria(String name, String startDate, String endDate, String type, String serialNo, String employee, Integer rows, Integer page, String orderBy, String orderType);
	public Map<String, Object> selectByCriteria2(String serviceOrderID, String name, String mobileTel, String startDate, String endDate, String type, String serialNo, String employee, Integer rows, Integer page, String orderBy, String orderType);
	public boolean edit(ServiceOrder serviceOrder) throws Exception;
	public boolean delete(String serviceOrderID, Integer employeeID) throws Exception;
	
	public List<ServiceOrder> selectNewSOByCriteria(String name, String date, String type, Integer rows, Integer page, String orderBy, String orderType);
	public List<ServiceOrder> selectSOForCloseByCriteria(String name, String startDate, String endDate, String type, String serialNo, Integer rows, Integer page, String orderBy, String orderType);
	public List<ServiceOrder> selectSOForCloseByCriteria(Integer employeeID, String name, String startDate, String endDate, String type, String serialNo, Integer rows, Integer page, String orderBy, String orderType);
	public List<ServiceOrder> selectSOForCloseByCriteria(String name, String startDate, String endDate, String type, String serialNo, String empFixID, Integer rows, Integer page, String orderBy, String orderType);
	public boolean close(ServiceOrder so, List<IssuePart> issuePartList, List<ServiceList> serviceList) throws Exception;
	public List<ServiceOrder> selectFixedSOByCriteria(String name, String date, String type, Integer rows, Integer page, String orderBy, String orderType);
	
	public List<ServiceOrder> getRepairReport(String startDate, String endDate, String status);
	public NumRepairReportForm getNumRepairReport(String date);
	public List<NumRepairByEmpReportForm> getNumRepairByEmpReport(String startDate, String endDate, Integer employeeID);
	public List<SumAmountReportForm> getSumAmountReport(String startDate, String endDate);
}
