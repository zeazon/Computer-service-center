package com.twobytes.repair.dao;

import java.util.List;

import com.twobytes.model.IssuePart;

public interface IssuePartDAO {
	public boolean save(IssuePart issuePart) throws Exception;
	public List<IssuePart> getByServiceOrder(String serviceOrderID) throws Exception;
	public boolean delete(String serviceOrderID) throws Exception;
	public List<String> getPart() throws Exception;
}