package com.twobytes.master.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twobytes.master.dao.OutsiteCompanyDAO;
import com.twobytes.model.OutsiteCompany;

@Service
public class OutsiteCompanyServiceImpl implements OutsiteCompanyService {

	@Autowired
	private OutsiteCompanyDAO outsiteCompanyDAO;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean save(OutsiteCompany oc) throws Exception{
		return outsiteCompanyDAO.save(oc);
	}

	@Override
	@Transactional
	public List<OutsiteCompany> getAll() throws Exception{
		return outsiteCompanyDAO.getAll();
	}

	@Override
	@Transactional
	public OutsiteCompany selectByID(Integer ocID) throws Exception{
		return outsiteCompanyDAO.selectByID(ocID);
	}

	@Override
	@Transactional
	public List<OutsiteCompany> selectByCriteria(String name, Integer rows,
			Integer page, String orderBy, String orderType) throws Exception{
		if(null != name && !name.equals("")) {
			name = "%"+name+"%";
		}
		return outsiteCompanyDAO.selectByCriteria(name, rows, page, orderBy, orderType);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean edit(OutsiteCompany oc) throws Exception{
		return outsiteCompanyDAO.edit(oc);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean delete(Integer ocID) throws Exception{
		OutsiteCompany oc = outsiteCompanyDAO.selectByID(ocID);
		if(null != oc){
			return outsiteCompanyDAO.delete(oc);
		}else{
			return false;
		}
	}

}
