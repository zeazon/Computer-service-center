package com.twobytes.express.dao;

import java.util.List;

import com.twobytes.express.model.Armas;

public interface ArmasDAO {
	public List<Armas> getAll();
	public List<Armas> selectByCriteria(String name, Integer rows, Integer page, String orderBy, String orderType) throws Exception;
	public Armas selectByID(String cuscod) throws Exception;
}
