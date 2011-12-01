package com.twobytes.repair.service;

import java.util.List;

import com.twobytes.model.ServiceList;

public interface ServiceListService {
	public boolean save(ServiceList serviceList) throws Exception;
	public List<ServiceList> getByServiceOrder(String serviceOrderID);
}
