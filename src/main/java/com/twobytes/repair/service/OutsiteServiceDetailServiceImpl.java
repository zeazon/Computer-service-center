package com.twobytes.repair.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twobytes.model.OutsiteServiceDetail;
import com.twobytes.repair.dao.OutsiteServiceDetailDAO;

@Service
public class OutsiteServiceDetailServiceImpl implements OutsiteServiceDetailService {

	@Autowired
	private OutsiteServiceDetailDAO osdDAO;
	
	@Override
	@Transactional
	public List<OutsiteServiceDetail> getByOutsiteService(String outsiteServiceID) {
		List<OutsiteServiceDetail> retList = new ArrayList<OutsiteServiceDetail>();
		try {
			retList = osdDAO.getByOutsiteService(outsiteServiceID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retList;
	}

}
