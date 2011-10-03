package com.twobytes.repair.service;

import java.util.List;

import com.twobytes.model.OutsiteService;
import com.twobytes.model.OutsiteServiceDetail;

public interface OutsiteServiceService {
	public boolean save(OutsiteService outsiteService) throws Exception;
	public OutsiteService selectByID(Integer outsiteServiceID);
	public List<OutsiteService> selectByCriteria(String name, String surname, String date, String type, Integer rows, Integer page, String orderBy, String orderType);
	public boolean edit(OutsiteService outsiteService) throws Exception;
	public boolean delete(Integer outsiteServiceID, Integer employeeID) throws Exception;
	public boolean sent(OutsiteService outsiteService) throws Exception;
	public boolean received(OutsiteService outsiteService, List<OutsiteServiceDetail> outsiteServiceDetailList) throws Exception;
	
	public List<OutsiteService> selectNewOSByCriteria(String name, String date, String type, Integer rows, Integer page, String orderBy, String orderType);
	public List<OutsiteService> selectSentOSByCriteria(String name, String date, String type, Integer rows, Integer page, String orderBy, String orderType);
}
