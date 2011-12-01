package com.twobytes.repair.dao;

import java.util.List;

import com.twobytes.model.ServiceList;

public interface ServiceListDAO {
	public boolean save(ServiceList serviceList) throws Exception;
	public List<ServiceList> getByServiceOrder(String serviceOrderID) throws Exception;
}
