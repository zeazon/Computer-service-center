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

import com.twobytes.master.service.SubdistrictService;
import com.twobytes.model.Subdistrict;

@Controller
public class SubdistrictController {
	
	@Autowired
	private SubdistrictService sdService;
	
	private static final Logger logger = LoggerFactory
	.getLogger(SubdistrictController.class);
	
	@RequestMapping(value = "/findSubdistrict", method = RequestMethod.GET)
	public @ResponseBody
	List<Subdistrict> subdistrictForDistrict(
			@RequestParam(value = "districtID", required = true) Integer districtID) {
		logger.debug("finding subdistrict for districtID " + districtID);
		return this.sdService.getByDistrict(districtID);
	}
	
	@RequestMapping(value = "/findZipcode", method = RequestMethod.GET)
	public @ResponseBody Integer findZipcode(@RequestParam Integer subdistrictID){
		if(subdistrictID != null){
			Subdistrict sd = sdService.selectByID(subdistrictID);
			return sd.getZipcode();
		}else return new Integer(0);
	}
}
