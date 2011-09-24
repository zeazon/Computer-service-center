package com.twobytes.master.dao;

import java.util.List;

import com.twobytes.model.Subdistrict;

public interface SubdistrictDAO {
	public List<Subdistrict> getByDistrict(Integer districtID);
	public Subdistrict selectByID(Integer subdistrictID);
}
