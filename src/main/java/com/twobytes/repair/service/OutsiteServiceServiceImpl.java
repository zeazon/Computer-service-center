package com.twobytes.repair.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twobytes.model.OutsiteService;
import com.twobytes.model.OutsiteServiceDetail;
import com.twobytes.repair.dao.OutsiteServiceDAO;
import com.twobytes.repair.dao.OutsiteServiceDetailDAO;
import com.twobytes.repair.dao.ServiceOrderDAO;

@Service
public class OutsiteServiceServiceImpl implements OutsiteServiceService {

	@Autowired
	private OutsiteServiceDAO osDAO;
	
	@Autowired
	private ServiceOrderDAO soDAO;

	@Autowired
	private OutsiteServiceDetailDAO osdDAO;
	
	@Override
	@Transactional
	public boolean save(OutsiteService outsiteService) throws Exception{
		return osDAO.save(outsiteService);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean sent(OutsiteService outsiteService) throws Exception{
		// Update data of outsite service document and update status of service order to 'outsite'
		osDAO.edit(outsiteService);
		soDAO.edit(outsiteService.getServiceOrder());
		return true;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean received(OutsiteService outsiteService,
			List<OutsiteServiceDetail> outsiteServiceDetailList)
			throws Exception {
		// Update data of outsite service document, update status of service order to 'close' and add outsite service detail if has any.s
		osDAO.edit(outsiteService);
		soDAO.edit(outsiteService.getServiceOrder());
		
		if(outsiteServiceDetailList.size()>0){
			for(OutsiteServiceDetail osd:outsiteServiceDetailList){
				osdDAO.save(osd);
			}
		}
		return true;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean received(OutsiteService outsiteService) throws Exception {
		// Update data of outsite service document, update status of service order to 'close' and add outsite service detail if has any.s
		osDAO.edit(outsiteService);
		soDAO.edit(outsiteService.getServiceOrder());
		return true;
	}

	@Override
	@Transactional
	public OutsiteService selectByID(Integer outsiteServiceID) {
		OutsiteService model = new OutsiteService();
		try {
			model = osDAO.selectByID(outsiteServiceID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@Override
	@Transactional
	public List<OutsiteService> selectByCriteria(String name, String surname,
			String date, String type, Integer rows, Integer page,
			String orderBy, String orderType) {
		if(null != name && !name.equals("")) {
			name = "%"+name+"%";
		}
		if(null != surname && !surname.equals("")) {
			surname = "%"+surname+"%";
		}
		List<OutsiteService> modelList = new ArrayList<OutsiteService>();
		try {
			modelList = osDAO.selectByCriteria(name, surname, date, type, rows, page, orderBy, orderType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelList;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean edit(OutsiteService outsiteService) throws Exception{
		return osDAO.edit(outsiteService);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean delete(Integer outsiteServiceID, Integer employeeID) throws Exception{
		OutsiteService os = osDAO.selectByID(outsiteServiceID);
		if(null != os){
			return osDAO.delete(os, employeeID);
		}else{
			return false;
		}
	}

	@Override
	@Transactional
	public List<OutsiteService> selectNewOSByCriteria(String name,
			String date, String type, Integer rows,
			Integer page, String orderBy, String orderType) {
		if(null != name && !name.equals("")) {
			name = "%"+name+"%";
		}
		List<OutsiteService> modelList = new ArrayList<OutsiteService>();
		try {
			modelList = osDAO.selectNewOSByCriteria(name, date, type, rows, page, orderBy, orderType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelList;
	}

	@Override
	@Transactional
	public List<OutsiteService> selectSentOSByCriteria(String name,
			String date, String type, Integer rows,
			Integer page, String orderBy, String orderType) {
		if(null != name && !name.equals("")) {
			name = "%"+name+"%";
		}
		List<OutsiteService> modelList = new ArrayList<OutsiteService>();
		try {
			modelList = osDAO.selectSentOSByCriteria(name, date, type, rows, page, orderBy, orderType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelList;
	}

	@Override
	@Transactional
	public OutsiteService selectByServiceOrderID(String serviceOrderID) {
		OutsiteService os = new OutsiteService();
		try {
			os = osDAO.selectByServiceOrderID(serviceOrderID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return os;
	}
}
