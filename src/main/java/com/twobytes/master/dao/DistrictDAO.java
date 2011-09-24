package com.twobytes.master.dao;

import java.util.List;

import com.twobytes.model.District;

public interface DistrictDAO {
	public List<District> getByProvince(Integer provinceID);
	public District selectByID(Integer districtID);
}
