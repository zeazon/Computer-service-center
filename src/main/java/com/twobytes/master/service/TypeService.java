package com.twobytes.master.service;

import java.util.List;

import com.twobytes.model.Type;

public interface TypeService {
	public boolean save(Type type) throws Exception;
	public Type selectByID(String typeID) throws Exception;
	public List<Type> selectByCriteria(String name, Integer rows, Integer page, String orderBy, String orderType) throws Exception;
	public boolean edit(Type type) throws Exception;
	public boolean delete(String typeID) throws Exception;
	public List<Type> getAll() throws Exception;
}
