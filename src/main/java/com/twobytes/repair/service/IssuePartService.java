package com.twobytes.repair.service;

import java.util.List;

import com.twobytes.model.IssuePart;

public interface IssuePartService {
	public boolean save(IssuePart issuePart) throws Exception;
	public List<IssuePart> getByServiceOrder(String serviceOrderID);
	public List<String> getPart();
}