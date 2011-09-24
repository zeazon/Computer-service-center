package com.twobytes.master.dao;

import java.util.List;

import com.twobytes.model.TransportCompany;

public interface TransportCompanyDAO {
	public boolean save(TransportCompany tc) throws Exception;
	public TransportCompany selectByID(Integer tcID) throws Exception;
	public List<TransportCompany> selectByCriteria(String name, Integer rows, Integer page, String orderBy, String orderType) throws Exception;
	public boolean edit(TransportCompany tc) throws Exception;
	public boolean delete(TransportCompany tc) throws Exception;
	public List<TransportCompany> getAll() throws Exception;
}
