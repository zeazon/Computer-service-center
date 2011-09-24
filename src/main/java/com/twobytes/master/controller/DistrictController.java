package com.twobytes.master.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twobytes.master.service.DistrictService;
import com.twobytes.model.District;

@Controller
public class DistrictController {

	@Autowired
	private DistrictService districtService;

	private static final Logger logger = LoggerFactory
	.getLogger(DistrictController.class);
	
	@RequestMapping(value = "/findDistrit", method = RequestMethod.GET)
	public @ResponseBody
	List<District> districtForProvince(
			@RequestParam(value = "provinceID", required = true) Integer provinceID) {
		logger.debug("finding district for provinceID " + provinceID);
		return this.districtService.getByProvince(provinceID);
	}
}
