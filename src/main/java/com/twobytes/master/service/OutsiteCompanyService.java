package com.twobytes.master.service;

import java.util.List;

import com.twobytes.model.OutsiteCompany;

public interface OutsiteCompanyService {
	public boolean save(OutsiteCompany oc) throws Exception;
	public OutsiteCompany selectByID(Integer ocID) throws Exception;
	public List<OutsiteCompany> selectByCriteria(String name, Integer rows, Integer page, String orderBy, String orderType) throws Exception;
	public boolean edit(OutsiteCompany oc) throws Exception;
	public boolean delete(Integer ocID) throws Exception;
	public List<OutsiteCompany> getAll() throws Exception;
}
