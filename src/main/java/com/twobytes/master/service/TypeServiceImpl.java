package com.twobytes.master.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twobytes.master.dao.TypeDAO;
import com.twobytes.model.Type;

@Service
public class TypeServiceImpl implements TypeService {

	@Autowired
	private TypeDAO typeDAO;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean save(Type type) throws Exception{
		return typeDAO.save(type);
	}

	@Override
	@Transactional
	public Type selectByID(String typeID) throws Exception{
		return typeDAO.selectByID(typeID);
	}

	@Override
	@Transactional
	public List<Type> selectByCriteria(String name, Integer rows, Integer page,
			String orderBy, String orderType) throws Exception{
		if(null != name && !name.equals("")) {
			name = "%"+name+"%";
		}
		return typeDAO.selectByCriteria(name, rows, page, orderBy, orderType);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean edit(Type type) throws Exception{
		return typeDAO.edit(type);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean delete(String typeID) throws Exception{
		Type type = typeDAO.selectByID(typeID);
		if(null != type){
			return typeDAO.delete(type);
		}else{
			return false;
		}
	}

	@Override
	@Transactional
	public List<Type> getAll() throws Exception{
		return typeDAO.getAll();
	}

}
