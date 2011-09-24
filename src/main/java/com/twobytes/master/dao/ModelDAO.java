package com.twobytes.master.dao;

import java.util.List;

import com.twobytes.model.Model;

public interface ModelDAO {
	public boolean save(Model model) throws Exception;
	public Model selectByID(Integer modelID) throws Exception;
	public List<Model> selectByCriteria(String name, String typeID, Integer brandID, Integer rows, Integer page, String orderBy, String orderType) throws Exception;
	public boolean edit(Model model) throws Exception;
	public boolean delete(Model model) throws Exception;
	public List<Model> getModelByTypeAndBrand(String typeID, Integer brandID);
}
