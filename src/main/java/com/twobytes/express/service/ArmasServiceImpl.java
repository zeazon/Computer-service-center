package com.twobytes.express.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twobytes.express.dao.ArmasDAO;
import com.twobytes.express.model.Armas;

@Service
public class ArmasServiceImpl implements ArmasService {

	@Autowired
	private ArmasDAO armasDAO;
	
	@Override
	@Transactional
	public List<Armas> getAll() {
		return armasDAO.getAll();
	}

	@Override
	@Transactional
	public List<Armas> selectByCriteria(String name, Integer rows,
			Integer page, String orderBy, String orderType) {
		if(null != name && !name.equals("")) {
			name = "%"+name+"%";
		}
		List<Armas> modelList = new ArrayList<Armas>();
		try {
			modelList = armasDAO.selectByCriteria(name, rows, page, orderBy, orderType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelList;
	}

	@Override
	@Transactional
	public Armas selectByID(String cuscod) {
		Armas armas = new Armas();
		try{
			armas = armasDAO.selectByID(cuscod);
		}catch(Exception e){
			e.printStackTrace();
		}
		return armas;
	}

}
