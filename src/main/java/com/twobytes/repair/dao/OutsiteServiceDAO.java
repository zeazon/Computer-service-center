package com.twobytes.repair.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.twobytes.model.OutsiteService;

public interface OutsiteServiceDAO {
	public boolean save(OutsiteService outsiteService) throws Exception;
	public OutsiteService selectByID(String outsiteServiceID) throws Exception;
	public Map<String, Object> selectByCriteria(String name, String surname, String date, String type, String serialNo, Integer rows, Integer page, String orderBy, String orderType) throws Exception;
	public boolean edit(OutsiteService outsiteService) throws Exception;
	public boolean delete(OutsiteService outsiteService, Integer employeeID, Date time) throws Exception;
	public List<OutsiteService> selectByServiceOrderID(String serviceOrderID) throws Exception;
	
	public List<OutsiteService> selectNewOSByCriteria(String name, String date, String type, String serialNo, Integer rows, Integer page, String orderBy, String orderType) throws Exception;
	public List<OutsiteService> selectSentOSByCriteria(String name, String date, String type, String serialNo, Integer rows, Integer page, String orderBy, String orderType) throws Exception;
	
	public Integer countUncloseOutsiteService(String serviceOrderID, String outsiteServiceID) throws Exception;
}
