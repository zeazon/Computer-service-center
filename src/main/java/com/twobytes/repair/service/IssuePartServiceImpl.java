package com.twobytes.repair.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twobytes.model.IssuePart;
import com.twobytes.repair.dao.IssuePartDAO;

@Service
public class IssuePartServiceImpl implements IssuePartService {

	@Autowired
	private IssuePartDAO issuePartDAO;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean save(IssuePart issuePart) throws Exception {
		return issuePartDAO.save(issuePart);
	}

	@Override
	@Transactional
	public List<IssuePart> getByServiceOrder(String serviceOrderID) {
		List<IssuePart> retList = new ArrayList<IssuePart>();
		try {
			retList = issuePartDAO.getByServiceOrder(serviceOrderID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retList;
	}

}
