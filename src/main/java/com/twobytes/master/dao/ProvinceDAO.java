package com.twobytes.master.dao;

import java.util.List;

import com.twobytes.model.Province;

public interface ProvinceDAO {
	public List<Province> getAll();
	public Province selectByID(Integer provinceID);
}
