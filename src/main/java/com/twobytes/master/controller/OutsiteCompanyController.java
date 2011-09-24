package com.twobytes.master.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twobytes.master.form.OutsiteCompanyForm;
import com.twobytes.master.form.OutsiteCompanyGridData;
import com.twobytes.master.form.OutsiteCompanySearchForm;
import com.twobytes.master.service.DistrictService;
import com.twobytes.master.service.OutsiteCompanyService;
import com.twobytes.master.service.ProvinceService;
import com.twobytes.master.service.SubdistrictService;
import com.twobytes.model.CustomGenericResponse;
import com.twobytes.model.District;
import com.twobytes.model.Employee;
import com.twobytes.model.GridResponse;
import com.twobytes.model.OutsiteCompany;
import com.twobytes.model.Province;
import com.twobytes.model.Subdistrict;
import com.twobytes.security.form.LoginForm;

@Controller
public class OutsiteCompanyController {
	
	@Autowired
	private OutsiteCompanyService ocService;
	
	@Autowired
	private SubdistrictService sdService;
	
	@Autowired
	private DistrictService districtService;
	
	@Autowired
	private ProvinceService provinceService;
	
	@Autowired
	private MessageSource messages;
	
	public void setMessages(MessageSource messages) {
		this.messages = messages;
	}
	
	private String VIEWNAME_SEARCH = "outsiteCompany.search";
	private String VIEWNAME_FORM = "outsiteCompany.form";
	
	@RequestMapping(value = "/outsiteCompany")
	public String view(ModelMap model, HttpServletRequest request) {
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		OutsiteCompanySearchForm searchForm = new OutsiteCompanySearchForm();
		model.addAttribute("searchForm", searchForm);
		return VIEWNAME_SEARCH;
	}
	
	@RequestMapping(value="/searchOutsiteCompany")
	public @ResponseBody GridResponse getData(@RequestParam(value="name", required=false) String name, @RequestParam("rows") Integer rows, @RequestParam("page") Integer page, @RequestParam("sidx") String sidx, @RequestParam("sord") String sord){
		// Because default Tomcat URI encoding is iso-8859-1 so it must encode back to tis620
		try{
			if(null != name){
				name = new String(name.getBytes("iso-8859-1"), "tis620");	
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		List<OutsiteCompany> ocList = new ArrayList<OutsiteCompany>();
		try{
			ocList = ocService.selectByCriteria(name, rows, page, sidx, sord);
		}catch(Exception e){
			e.printStackTrace();
		}
		GridResponse response = new GridResponse();
		
		List<OutsiteCompanyGridData> rowsList = new ArrayList<OutsiteCompanyGridData>();
		
		Integer total_pages = 0;
		if(ocList.size() > 0){
			int endData = 0;
			if(ocList.size() < (rows*page)){
				endData = ocList.size();
			}else{
				endData = (rows*page);
			}
			for(int i=(rows*page - rows); i<endData; i++){
				OutsiteCompany oc = ocList.get(i);
				OutsiteCompanyGridData gridData = new OutsiteCompanyGridData();
				gridData.setOcID(oc.getOutsiteCompanyID().toString());
				gridData.setName(oc.getName());
				rowsList.add(gridData);
			}
			total_pages = new Double(Math.ceil(((double)ocList.size()/(double)rows))).intValue();
		}
		
		if (page > total_pages) page=total_pages;
		response.setPage(page.toString());
		response.setRecords(String.valueOf(ocList.size()));
		response.setTotal(total_pages.toString());
		response.setRows(rowsList);
		return response;
	}
	
	@RequestMapping(value = "/outsiteCompany", params = "do=preAdd")
	public String preAdd(ModelMap model, HttpServletRequest request){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		OutsiteCompanyForm form = new OutsiteCompanyForm();
		// set default province to Chanthaburi
		form.setProvinceID(7);
		// set default district to Muang
		form.setDistrictID(160);
		model.addAttribute("form", form);
		List<Province> provinceList = provinceService.getAll();
		
		List<District> districtList = districtService.getByProvince(7);
		
		// set subdistrict from Muang district
		List<Subdistrict> subdistrictList = sdService.getByDistrict(160);
				
		model.addAttribute("provinceList", provinceList);
		model.addAttribute("districtList", districtList);
		model.addAttribute("subdistrictList", subdistrictList);
		return VIEWNAME_FORM;
	}
	
	@RequestMapping(value = "/outsiteCompany", params = "do=save")
	public String doSave(@ModelAttribute("form") OutsiteCompanyForm form, HttpServletRequest request, ModelMap model){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		Date now = new Date();
		Employee user = (Employee)request.getSession().getAttribute("UserLogin");
		OutsiteCompany oc = new OutsiteCompany();
		String msg = "";
		if(null != form.getOcID()){
			// update
			try{
				oc = ocService.selectByID(form.getOcID());
			}catch(Exception e){
				e.printStackTrace();
			}
			msg = this.messages.getMessage("msg.updateComplete", null, new Locale("th", "TH"));
		}else{
			// add
			oc.setCreatedBy(user.getEmployeeID());
			oc.setCreatedDate(now);
			msg = this.messages.getMessage("msg.addComplete", null, new Locale("th", "TH"));
		}
		oc.setName(form.getName());
		oc.setAddr(form.getAddr());
		Subdistrict sd = sdService.selectByID(form.getSubdistrictID());
		District district = districtService.selectByID(form.getDistrictID());
		Province province = provinceService.selectByID(form.getProvinceID());
		oc.setSubdistrict(sd);
		oc.setDistrict(district);
		oc.setProvince(province);
		
		oc.setUpdatedBy(user.getEmployeeID());
		oc.setUpdatedDate(now);
		
		boolean canSave;
		try{
			canSave = ocService.save(oc);
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			List<Province> provinceList = provinceService.getAll();
			List<District> districtList = districtService.getByProvince(form.getProvinceID());
			List<Subdistrict> subdistrictList = sdService.getByDistrict(form.getDistrictID());

			model.addAttribute("provinceList", provinceList);
			model.addAttribute("districtList", districtList);
			model.addAttribute("subdistrictList", subdistrictList);
			return VIEWNAME_FORM;
		}
		if(!canSave){
			model.addAttribute("errMsg", this.messages.getMessage("error.cannotSave", null, new Locale("th", "TH")));
			List<Province> provinceList = provinceService.getAll();
			List<District> districtList = districtService.getByProvince(form.getProvinceID());
			List<Subdistrict> subdistrictList = sdService.getByDistrict(form.getDistrictID());

			model.addAttribute("provinceList", provinceList);
			model.addAttribute("districtList", districtList);
			model.addAttribute("subdistrictList", subdistrictList);
		}
		model.addAttribute("msg", msg);
		OutsiteCompanySearchForm searchForm = new OutsiteCompanySearchForm();
		model.addAttribute("searchForm", searchForm);
		return VIEWNAME_SEARCH;
	}
	
	@RequestMapping(value = "/outsiteCompany", params = "do=preEdit")
	public String preEdit(@RequestParam(value="ocID") Integer ocID, ModelMap model, HttpServletRequest request){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		OutsiteCompany oc = new OutsiteCompany();
		try{
			oc = ocService.selectByID(ocID);
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			OutsiteCompanySearchForm searchForm = new OutsiteCompanySearchForm();
			model.addAttribute("searchForm", searchForm);
		}
		OutsiteCompanyForm form = new OutsiteCompanyForm();
		form.setOcID(oc.getOutsiteCompanyID());
		form.setName(oc.getName());
		form.setAddr(oc.getAddr());
		form.setSubdistrictID(oc.getSubdistrict().getSubdistrictID());
		form.setDistrictID(oc.getDistrict().getDistrictID());
		form.setProvinceID(oc.getProvince().getProvinceID());
		
		List<Province> provinceList = provinceService.getAll();
		List<District> districtList = districtService.getByProvince(form.getProvinceID());
		List<Subdistrict> subdistrictList = sdService.getByDistrict(form.getDistrictID());

		model.addAttribute("form", form);
		model.addAttribute("provinceList", provinceList);
		model.addAttribute("districtList", districtList);
		model.addAttribute("subdistrictList", subdistrictList);
		return VIEWNAME_FORM;
	}
	
	@RequestMapping(value = "/outsiteCompany", params = "do=delete")
	public @ResponseBody CustomGenericResponse delete(HttpServletRequest request){
		CustomGenericResponse response = new CustomGenericResponse();
		response.setSuccess(true);
		try{
			ocService.delete(Integer.valueOf(request.getParameter("ocID")));
			response.setMessage(this.messages.getMessage("msg.deleteSuccess", null, new Locale("th", "TH")));
		}catch(Exception e){
			response.setSuccess(false);
			response.setMessage(this.messages.getMessage("error.cannotDelete", null, new Locale("th", "TH")));
		}
		return response;
	}
}
