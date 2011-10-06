package com.twobytes.repair.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twobytes.model.ServiceOrder;
import com.twobytes.repair.dao.ServiceOrderDAO;

@Service
public class ServiceOrderServiceImpl implements ServiceOrderService {

	@Autowired
	private ServiceOrderDAO soDAO;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean save(ServiceOrder serviceOrder) throws Exception{
		return soDAO.save(serviceOrder);
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

}
