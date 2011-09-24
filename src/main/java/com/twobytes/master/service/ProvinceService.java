package com.twobytes.master.service;

import java.util.List;

import com.twobytes.model.Province;

public interface ProvinceService {
	public List<Province> getAll();
	public Province selectByID(Integer provinceID);
}
