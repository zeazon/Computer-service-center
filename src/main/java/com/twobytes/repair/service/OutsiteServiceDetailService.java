package com.twobytes.repair.service;

import java.util.List;

import com.twobytes.model.OutsiteServiceDetail;

public interface OutsiteServiceDetailService {
	public List<OutsiteServiceDetail> getByOutsiteService(String outsiteServiceID);
}
