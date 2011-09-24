package com.twobytes.master.service;

import java.util.List;

import com.twobytes.model.Subdistrict;

public interface SubdistrictService {
	public List<Subdistrict> getByDistrict(Integer districtID);
	public Subdistrict selectByID(Integer subdistrictID);
}
