package com.twobytes.master.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twobytes.master.dao.ModelDAO;
import com.twobytes.model.Model;

@Service
public class ModelServiceImpl implements ModelService {

	@Autowired
	private ModelDAO modelDAO;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean save(Model model) throws Exception {
		return modelDAO.save(model);
	}

	@Override
	@Transactional
	public Model selectByID(Integer modelID) {
		Model model = new Model();
		try{
			model = modelDAO.selectByID(modelID);
		}catch(Exception e){
			e.printStackTrace();
		}
		return model;
	}

	@Override
	@Transactional
	public List<Model> selectByCriteria(String name, String typeID, Integer brandID, Integer rows,
			Integer page, String orderBy, String orderType) {
		if(null != name && !name.equals("")) {
			name = "%"+name+"%";
		}
		
		List<Model> modelList = new ArrayList<Model>();
		try{
			modelList = modelDAO.selectByCriteria(name, typeID, brandID, rows, page, orderBy, orderType);
		}catch(Exception e){
			e.printStackTrace();
		}
		return modelList;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean edit(Model model) throws Exception {
		return modelDAO.edit(model);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean delete(Integer modelID) throws Exception {
		Model model = modelDAO.selectByID(modelID);
		if(null != model){
			return modelDAO.delete(model);
		}else{
			return false;	
		}
	}

	@Override
	@Transactional
	public List<Model> getModelByTypeAndBrand(String typeID, Integer brandID) {
		List<Model> modelList = new ArrayList<Model>();
		List<Model> retList = new ArrayList<Model>();
		try{
			modelList = modelDAO.getModelByTypeAndBrand(typeID, brandID);
			for(Model model:modelList){
				Model data = new Model();
				data.setModelID(model.getModelID());
				data.setName(model.getName());
				retList.add(data);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return retList;
	}

}
