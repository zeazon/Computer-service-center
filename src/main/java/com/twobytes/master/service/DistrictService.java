package com.twobytes.master.service;

import java.util.List;

import com.twobytes.model.District;

public interface DistrictService {
	public List<District> getByProvince(Integer provinceID);
	public District selectByID(Integer districtID);
}