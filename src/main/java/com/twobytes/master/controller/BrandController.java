package com.twobytes.master.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twobytes.master.form.BrandForm;
import com.twobytes.master.form.BrandGridData;
import com.twobytes.master.form.BrandSearchForm;
import com.twobytes.master.service.BrandService;
import com.twobytes.master.service.TypeService;
import com.twobytes.model.Brand;
import com.twobytes.model.CustomGenericResponse;
import com.twobytes.model.Employee;
import com.twobytes.model.GridResponse;
import com.twobytes.model.Type;
import com.twobytes.security.form.LoginForm;

@Controller
public class BrandController {

	@Autowired
	private BrandService brandService;
	
	@Autowired
	private TypeService typeService;
	
	private static final Logger logger = LoggerFactory
	.getLogger(BrandController.class);
	
	@Autowired
	private MessageSource messages;
	
	public void setMessages(MessageSource messages) {
		this.messages = messages;
	}
	
	private String VIEWNAME_SEARCH = "brand.search";
	private String VIEWNAME_FORM = "brand.form";
	
	@RequestMapping(value = "/brand")
	public String view(ModelMap model, HttpServletRequest request) {
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		BrandSearchForm searchForm = new BrandSearchForm();
		model.addAttribute("searchForm", searchForm);
		return VIEWNAME_SEARCH;
	}
	
	@RequestMapping(value="/searchBrand")
	public @ResponseBody GridResponse getData(@RequestParam(value="name", required=false) String name, @RequestParam("rows") Integer rows, @RequestParam("page") Integer page, @RequestParam("sidx") String sidx, @RequestParam("sord") String sord){
		// Because default Tomcat URI encoding is iso-8859-1 so it must encode back to tis620
		try{
			if(null != name){
				name = new String(name.getBytes("iso-8859-1"), "tis620");	
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		List<Brand> brandList = new ArrayList<Brand>();
		try{
			brandList = brandService.selectByCriteria(name, rows, page, sidx, sord);
		}catch(Exception e){
			e.printStackTrace();
		}
		GridResponse response = new GridResponse();
		
		List<BrandGridData> rowsList = new ArrayList<BrandGridData>();
		
		Integer total_pages = 0;
		if(brandList.size() > 0){
			int endData = 0;
			if(brandList.size() < (rows*page)){
				endData = brandList.size();
			}else{
				endData = (rows*page);
			}
			for(int i=(rows*page - rows); i<endData; i++){
				Brand brand = brandList.get(i);
				BrandGridData gridData = new BrandGridData();
				gridData.setBrandID(brand.getBrandID().toString());
				gridData.setName(brand.getName());
				rowsList.add(gridData);
			}
			total_pages = new Double(Math.ceil(((double)brandList.size()/(double)rows))).intValue();
		}
		
		if (page > total_pages) page=total_pages;
		response.setPage(page.toString());
		response.setRecords(String.valueOf(brandList.size()));
		response.setTotal(total_pages.toString());
		response.setRows(rowsList);
		return response;
	}
	
	@RequestMapping(value = "/brand", params = "do=preAdd")
	public String preAdd(ModelMap model, HttpServletRequest request){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		BrandForm form = new BrandForm();
		List<Type> typeList = new ArrayList<Type>();
		try {
			typeList = typeService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("form", form);
		model.addAttribute("typeList", typeList);
		return VIEWNAME_FORM;
	}
	
	@RequestMapping(value = "/brand", params = "do=save")
	public String doSave(@ModelAttribute("form") BrandForm form, HttpServletRequest request, ModelMap model){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		Date now = new Date();
		Employee user = (Employee)request.getSession().getAttribute("UserLogin");
		Brand brand = new Brand();
		String msg = "";
		if(null != form.getBrandID()){
			// update
			try{
				brand = brandService.selectByID(form.getBrandID());
			}catch(Exception e){
				e.printStackTrace();
				model.addAttribute("errMsg", e.getMessage());
				List<Type> typeList = new ArrayList<Type>();  
				try {
					typeList = typeService.getAll();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				model.addAttribute("typeList", typeList);
				return VIEWNAME_FORM;
			}
			msg = this.messages.getMessage("msg.updateComplete", null, new Locale("th", "TH"));
		}else{
			// add
			brand.setCreatedBy(user.getEmployeeID());
			brand.setCreatedDate(now);
			msg = this.messages.getMessage("msg.addComplete", null, new Locale("th", "TH"));
		}
		brand.setName(form.getName());
		
		brand.setUpdatedBy(user.getEmployeeID());
		brand.setUpdatedDate(now);
		if(form.getTypeID().length > 0){
			Set<Type> typeList = new HashSet<Type>();
			for(String typeID:form.getTypeID()){
				try {
					Type type = typeService.selectByID(typeID);
					typeList.add(type);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			brand.setTypes(typeList);
		}
//		boolean canSave;
		try{
//			canSave = brandService.sa ve(brand);
			brandService.save(brand);
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			List<Type> typeList = new ArrayList<Type>();
			try {
				typeList = typeService.getAll();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			model.addAttribute("typeList", typeList);
			return VIEWNAME_FORM;
		}
//		if(!canSave){
//			model.addAttribute("errMsg", this.messages.getMessage("error.cannotSave", null, new Locale("th", "TH")));
//			List<Type> typeList = typeService.getAll();
//			model.addAttribute("typeList", typeList);
//			return VIEWNAME_FORM;
//		}
		model.addAttribute("msg", msg);
		BrandSearchForm searchForm = new BrandSearchForm();
		model.addAttribute("searchForm", searchForm);
		return VIEWNAME_SEARCH;
	}
	
	@RequestMapping(value = "/brand", params = "do=preEdit")
	public String preEdit(@RequestParam(value="brandID") Integer brandID, ModelMap model, HttpServletRequest request){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		Brand brand = new Brand();
		try{
			brand = brandService.selectByID(brandID);
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			BrandSearchForm searchForm = new BrandSearchForm();
			model.addAttribute("searchForm", searchForm);
			return VIEWNAME_SEARCH;
		}
		BrandForm form = new BrandForm();
		form.setBrandID(brand.getBrandID());
		form.setName(brand.getName());
		List<String> checkedList = new ArrayList<String>();
		for(Type type:brand.getTypes()){
			checkedList.add(type.getTypeID());
		}
		String checked[] = new String[checkedList.size()];
		checked = checkedList.toArray(checked);
		form.setTypeID(checked);
//		form.setTypeID(l.toArray(a));
//		form.setTypeID(new Integer[] {1,3});
		List<Type> typeList = new ArrayList<Type>();
		try {
			typeList = typeService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("form", form);
		model.addAttribute("typeList", typeList);
		return VIEWNAME_FORM;
	}
	
	@RequestMapping(value = "/brand", params = "do=delete")
	public @ResponseBody CustomGenericResponse delete(HttpServletRequest request){
		CustomGenericResponse response = new CustomGenericResponse();
		response.setSuccess(true);
		try{
			brandService.delete(Integer.valueOf(request.getParameter("brandID")));
			response.setMessage(this.messages.getMessage("msg.deleteSuccess", null, new Locale("th", "TH")));
		}catch(Exception e){
			response.setSuccess(false);
			response.setMessage(this.messages.getMessage("error.cannotDelete", null, new Locale("th", "TH")));
		}
		return response;
	}
	
	@RequestMapping(value="/brand", params = "do=getBrandByType")
	public @ResponseBody List<Brand> getBrandByType(@RequestParam(value="typeID") String typeID){
		Type type = new Type();
		try {
			type = typeService.selectByID(typeID);
			List<Brand> retList = new ArrayList<Brand>();
			if(type.getBrands() != null){
				for(int i=0; i<type.getBrands().size(); i++){
					Brand brand = type.getBrands().get(i);
					Brand dataBrand = new Brand();
					dataBrand.setBrandID(brand.getBrandID());
					dataBrand.setName(brand.getName());
					retList.add(dataBrand);
				}
			}
			return retList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Brand>();
	}
	
}
