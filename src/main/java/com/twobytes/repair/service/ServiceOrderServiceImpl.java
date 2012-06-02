package com.twobytes.repair.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twobytes.model.IssuePart;
import com.twobytes.model.ServiceList;
import com.twobytes.model.ServiceOrder;
import com.twobytes.repair.dao.IssuePartDAO;
import com.twobytes.repair.dao.ServiceListDAO;
import com.twobytes.repair.dao.ServiceOrderDAO;
import com.twobytes.report.form.NumRepairByEmpReportForm;
import com.twobytes.report.form.NumRepairReportForm;
import com.twobytes.report.form.SumAmountReportForm;
import com.twobytes.util.DocRunningUtil;

@Service
public class ServiceOrderServiceImpl implements ServiceOrderService {

	@Autowired
	private ServiceOrderDAO soDAO;
	
	@Autowired
	private IssuePartDAO ipDAO;
	
	@Autowired
	private ServiceListDAO slDAO;
	
	@Autowired
	private DocRunningUtil docRunningUtil;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String save(ServiceOrder serviceOrder) throws Exception{
		if (serviceOrder.getServiceOrderID() == null) {
			String serviceOrderID = docRunningUtil.genDoc("SO");
			serviceOrder.setServiceOrderID(serviceOrderID);
		}
		if(soDAO.save(serviceOrder)){
			return serviceOrder.getServiceOrderID();
		}else{
			return "false";
		}
	}

	@Override
	@Transactional
	public ServiceOrder selectByID(String serviceOrderID) {
		ServiceOrder model = new ServiceOrder();
		try {
			model = soDAO.selectByID(serviceOrderID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@Override
	@Transactional
	public List<ServiceOrder> selectByCriteria(String name,
			String startDate, String endDate, String type, String serialNo,
			Integer rows, Integer page, String orderBy, String orderType) {
		if(null != name && !name.equals("")) {
			name = "%"+name+"%";
		}
		if(null != serialNo && !serialNo.equals("")) {
			serialNo = "%"+serialNo+"%";
		}
		List<ServiceOrder> modelList = new ArrayList<ServiceOrder>();
		try {
			modelList = soDAO.selectByCriteria(name, startDate, endDate, type, serialNo, rows, page, orderBy, orderType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelList;
	}

	@Override
	@Transactional
	public Map<String, Object> selectByCriteria(String name, String startDate,
			String endDate, String type, String serialNo, String employee,
			Integer rows, Integer page, String orderBy, String orderType) {
		if(null != name && !name.equals("")) {
			name = "%"+name+"%";
		}
		if(null != serialNo && !serialNo.equals("")) {
			serialNo = "%"+serialNo+"%";
		}
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = soDAO.selectByCriteria(name, startDate, endDate, type, serialNo, employee, rows, page, orderBy, orderType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean edit(ServiceOrder serviceOrder) throws Exception{
		return soDAO.edit(serviceOrder);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean delete(String serviceOrderID, Integer employeeID) throws Exception{
		ServiceOrder so = soDAO.selectByID(serviceOrderID);
		if(null != so){
			return soDAO.delete(so, employeeID);
		}else{
			return false;
		}
	}

	@Override
	@Transactional
	public List<ServiceOrder> selectNewSOByCriteria(String name,
			String date, String type, Integer rows,
			Integer page, String orderBy, String orderType) {
		if(null != name && !name.equals("")) {
			name = "%"+name+"%";
		}
		List<ServiceOrder> modelList = new ArrayList<ServiceOrder>();
		try {
			modelList = soDAO.selectNewSOByCriteria(name, date, type, rows, page, orderBy, orderType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelList;
	}

	@Override
	@Transactional
	public List<ServiceOrder> selectSOForCloseByCriteria(String name,
			String startDate, String endDate, String type, String serialNo,
			Integer rows, Integer page, String orderBy, String orderType) {
		if(null != name && !name.equals("")) {
			name = "%"+name+"%";
		}
		if(null != serialNo && !serialNo.equals("")) {
			serialNo = "%"+serialNo+"%";
		}
		List<ServiceOrder> modelList = new ArrayList<ServiceOrder>();
		try {
			modelList = soDAO.selectSOForCloseByCriteria(name, startDate, endDate, type, serialNo, rows, page, orderBy, orderType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelList;
	}

	@Override
	@Transactional
	public List<ServiceOrder> selectSOForCloseByCriteria(Integer employeeID, String name,
			String startDate, String endDate, String type, String serialNo,
			Integer rows, Integer page, String orderBy, String orderType) {
		if(null != name && !name.equals("")) {
			name = "%"+name+"%";
		}
		if(null != serialNo && !serialNo.equals("")) {
			serialNo = "%"+serialNo+"%";
		}
		List<ServiceOrder> modelList = new ArrayList<ServiceOrder>();
		try {
			modelList = soDAO.selectSOForCloseByCriteria(employeeID, name, startDate, endDate, type, serialNo, rows, page, orderBy, orderType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelList;
	}
	
	@Override
	@Transactional
	public List<ServiceOrder> selectSOForCloseByCriteria(String name,
			String startDate, String endDate, String type, String serialNo,
			String empFixID, Integer rows, Integer page, String orderBy,
			String orderType) {
		if(null != name && !name.equals("")) {
			name = "%"+name+"%";
		}
		if(null != serialNo && !serialNo.equals("")) {
			serialNo = "%"+serialNo+"%";
		}
		List<ServiceOrder> modelList = new ArrayList<ServiceOrder>();
		try {
			modelList = soDAO.selectSOForCloseByCriteria(name, startDate, endDate, type, serialNo, empFixID, rows, page, orderBy, orderType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelList;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean close(ServiceOrder so, List<IssuePart> issuePartList,
			List<ServiceList> serviceList) throws Exception {
		soDAO.save(so);
		ipDAO.delete(so.getServiceOrderID());
		slDAO.delete(so.getServiceOrderID());
		for(int i=0; i<issuePartList.size(); i++){
			IssuePart ip = issuePartList.get(i);
			ipDAO.save(ip);
		}
		for(int j=0; j<serviceList.size(); j++){
			ServiceList sl = serviceList.get(j);
			slDAO.save(sl);
		}
		return true;
	}

	@Override
	@Transactional
	public List<ServiceOrder> selectFixedSOByCriteria(String name, String date,
			String type, Integer rows, Integer page, String orderBy,
			String orderType) {
		if(null != name && !name.equals("")) {
			name = "%"+name+"%";
		}
		List<ServiceOrder> modelList = new ArrayList<ServiceOrder>();
		try {
			modelList = soDAO.selectFixedSOByCriteria(name, date, type, rows, page, orderBy, orderType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelList;
	}

	@Override
	@Transactional
	public List<ServiceOrder> getRepairReport(String startDate, String endDate, String status) {
		List<ServiceOrder> retList = new ArrayList<ServiceOrder>();
		try {
			retList = soDAO.getRepairReport(startDate, endDate, status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retList;
	}

	@Override
	@Transactional
	public NumRepairReportForm getNumRepairReport(String date) {
		NumRepairReportForm reportForm = new NumRepairReportForm();
		try {
			reportForm = soDAO.getNumRepairReport(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reportForm;
	}

	@Override
	@Transactional
	public List<NumRepairByEmpReportForm> getNumRepairByEmpReport(
			String startDate, String endDate, Integer employeeID) {
		List<NumRepairByEmpReportForm> retList = new ArrayList<NumRepairByEmpReportForm>();
		try{
			retList = soDAO.getNumRepairByEmpReport(startDate, endDate, employeeID);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return retList;
	}

	@Override
	@Transactional
	public List<SumAmountReportForm> getSumAmountReport(String startDate,
			String endDate) {
		List<SumAmountReportForm> retList = new ArrayList<SumAmountReportForm>();
		try{
			retList = soDAO.getSumAmountReport(startDate, endDate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return retList;
	}

}
