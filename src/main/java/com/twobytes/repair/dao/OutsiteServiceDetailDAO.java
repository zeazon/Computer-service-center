package com.twobytes.repair.dao;

import java.util.List;

import com.twobytes.model.OutsiteServiceDetail;

public interface OutsiteServiceDetailDAO {
	public boolean save(OutsiteServiceDetail outsiteServiceDetail) throws Exception;
	public boolean delete(String outsiteServiceID) throws Exception;
	public List<OutsiteServiceDetail> getByOutsiteService(String outsiteServiceID) throws Exception;
}
