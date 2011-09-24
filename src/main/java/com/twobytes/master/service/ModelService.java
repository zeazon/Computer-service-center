package com.twobytes.master.service;

import java.util.List;

import com.twobytes.model.Model;

public interface ModelService {
	public boolean save(Model model) throws Exception;
	public Model selectByID(Integer modelID);
	public List<Model> selectByCriteria(String name, String typeID, Integer brandID, Integer rows, Integer page, String orderBy, String orderType);
	public boolean edit(Model model) throws Exception;
	public boolean delete(Integer modelID) throws Exception;
	public List<Model> getModelByTypeAndBrand(String typeID, Integer brandID);
}
