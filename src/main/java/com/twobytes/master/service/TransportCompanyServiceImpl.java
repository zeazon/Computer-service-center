package com.twobytes.master.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twobytes.master.dao.TransportCompanyDAO;
import com.twobytes.model.TransportCompany;

@Service
public class TransportCompanyServiceImpl implements TransportCompanyService {

	@Autowired
	private TransportCompanyDAO transportCompanyDAO;
	
	@Override
	@Transactional
	public List<TransportCompany> getAll() throws Exception{
		return transportCompanyDAO.getAll();
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean save(TransportCompany tc) throws Exception{
		return transportCompanyDAO.save(tc);
	}

	@Override
	@Transactional
	public TransportCompany selectByID(Integer tcID) throws Exception{
		return transportCompanyDAO.selectByID(tcID);
	}

	@Override
	@Transactional
	public List<TransportCompany> selectByCriteria(String name, Integer rows,
			Integer page, String orderBy, String orderType) throws Exception{
		if(null != name && !name.equals("")) {
			name = "%"+name+"%";
		}
		return transportCompanyDAO.selectByCriteria(name, rows, page, orderBy, orderType);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean edit(TransportCompany tc) throws Exception{
		return transportCompanyDAO.edit(tc);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean delete(Integer tcID) throws Exception{
		TransportCompany tc = transportCompanyDAO.selectByID(tcID);
		if(null != tc){
			return transportCompanyDAO.delete(tc);
		}else{
			return false;
		}
	}

}
