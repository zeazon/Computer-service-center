package com.twobytes.master.service;

import java.util.List;

import com.twobytes.model.TransportCompany;

public interface TransportCompanyService {
	public boolean save(TransportCompany tc) throws Exception;
	public TransportCompany selectByID(Integer tcID) throws Exception;
	public List<TransportCompany> selectByCriteria(String name, Integer rows, Integer page, String orderBy, String orderType) throws Exception;
	public boolean edit(TransportCompany tc) throws Exception;
	public boolean delete(Integer tcID) throws Exception;
	public List<TransportCompany> getAll() throws Exception;
}
