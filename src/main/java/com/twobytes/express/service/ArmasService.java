package com.twobytes.express.service;

import java.util.List;

import com.twobytes.express.model.Armas;

public interface ArmasService {
	public List<Armas> getAll();
	public List<Armas> selectByCriteria(String name, Integer rows, Integer page, String orderBy, String orderType);
	public Armas selectByID(String cuscod);
}
