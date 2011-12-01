package com.twobytes.repair.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twobytes.model.ServiceList;
import com.twobytes.repair.dao.ServiceListDAO;

@Service
public class ServiceListServiceImpl implements ServiceListService {

	@Autowired
	private ServiceListDAO serviceListDAO; 
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean save(ServiceList serviceList) throws Exception {
		return serviceListDAO.save(serviceList);
	}

	@Override
	@Transactional
	public List<ServiceList> getByServiceOrder(String serviceOrderID) {
		List<ServiceList> retList = new ArrayList<ServiceList>();
		try {
			retList = serviceListDAO.getByServiceOrder(serviceOrderID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retList;
	}

}
