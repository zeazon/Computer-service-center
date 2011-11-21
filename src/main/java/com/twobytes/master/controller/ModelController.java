package com.twobytes.master.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

import com.twobytes.master.form.ModelForm;
import com.twobytes.master.form.ModelGridData;
import com.twobytes.master.form.ModelSearchForm;
import com.twobytes.master.service.BrandService;
import com.twobytes.master.service.ModelService;
import com.twobytes.master.service.TypeService;
import com.twobytes.model.Brand;
import com.twobytes.model.CustomGenericResponse;
import com.twobytes.model.Employee;
import com.twobytes.model.GridResponse;
import com.twobytes.model.Model;
import com.twobytes.model.Type;
import com.twobytes.security.form.LoginForm;

@Controller
public class ModelController {

	@Autowired
	private ModelService modelService;
	
	@Autowired
	private TypeService typeService;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private MessageSource messages;
	
	public void setMessages(MessageSource messages) {
		this.messages = messages;
	}
	
	private static final Logger logger = LoggerFactory
	.getLogger(ModelController.class);
	
	private String VIEWNAME_SEARCH = "model.search";
	private String VIEWNAME_FORM = "model.form";
	
	@RequestMapping(value = "/model")
	public String view(ModelMap model, HttpServletRequest request) {
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		ModelSearchForm searchForm = new ModelSearchForm();
		model.addAttribute("searchForm", searchForm);
		
		List<Brand> brandList = new ArrayList<Brand>();
		List<Type> typeList = new ArrayList<Type>();
		try {
			typeList = typeService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("typeList", typeList);
		model.addAttribute("brandList", brandList);
		
		return VIEWNAME_SEARCH;
	}
	
	@RequestMapping(value="/searchModel")
	public @ResponseBody GridResponse getData(@RequestParam(value="name", required=false) String name, @RequestParam(value="typeID", required=false) String typeID, @RequestParam(value="brandID", required=false) String brandID, @RequestParam("rows") Integer rows, @RequestParam("page") Integer page, @RequestParam("sidx") String sidx, @RequestParam("sord") String sord){
		// Because default Tomcat URI encoding is iso-8859-1 so it must encode back to tis620
		try{
			if(null != name){
				name = new String(name.getBytes("iso-8859-1"), "tis620");	
			}
			if(null != typeID){
				typeID = new String(typeID.getBytes("iso-8859-1"), "tis620");	
			}
			if(null != brandID){
				brandID = new String(brandID.getBytes("iso-8859-1"), "tis620");	
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		List<Model> modelList = new ArrayList<Model>();
		Integer brandCri = null;
		if(null != brandID && !brandID.equals("")){
			brandCri = Integer.valueOf(brandID);
		}
		modelList = modelService.selectByCriteria(name, typeID, brandCri, rows, page, sidx, sord);
		
		GridResponse response = new GridResponse();
		
		List<ModelGridData> rowsList = new ArrayList<ModelGridData>();
		
		Integer total_pages = 0;
		if(modelList.size() > 0){
			int endData = 0;
			if(modelList.size() < (rows*page)){
				endData = modelList.size();
			}else{
				endData = (rows*page);
			}
			for(int i=(rows*page - rows); i<endData; i++){
				Model model = modelList.get(i);
				ModelGridData gridData = new ModelGridData();
				gridData.setModelID(model.getModelID().toString());
				gridData.setName(model.getName());
				gridData.setTypeName(model.getType().getName());
				gridData.setBrandName(model.getBrand().getName());
				rowsList.add(gridData);
			}
			total_pages = new Double(Math.ceil(((double)modelList.size()/(double)rows))).intValue();
		}
		
		if (page > total_pages) page=total_pages;
		response.setPage(page.toString());
		response.setRecords(String.valueOf(modelList.size()));
		response.setTotal(total_pages.toString());
		response.setRows(rowsList);
		return response;
	}
	
	@RequestMapping(value = "/model", params = "do=preAdd")
	public String preAdd(ModelMap model, HttpServletRequest request){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		ModelForm form = new ModelForm();

		List<Brand> brandList = new ArrayList<Brand>();
		List<Type> typeList = new ArrayList<Type>();
		try {
			typeList = typeService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(typeList.size() > 0){
			Type type = typeList.get(0);
			brandList = type.getBrands();
			form.setTypeID(type.getTypeID());
		}
		if(brandList.size() == 0){
			Brand brand = new Brand();
			brand.setBrandID(null);
			brand.setName(" ");
			brandList.add(brand);
		}else{
			Brand brand = brandList.get(0);
			form.setBrandID(brand.getBrandID());
		}
		
		model.addAttribute("form", form);
		model.addAttribute("typeList", typeList);
		model.addAttribute("brandList", brandList);
		return VIEWNAME_FORM;
	}
	
	@RequestMapping(value = "/model", params = "do=save")
	public String doSave(@ModelAttribute("form") ModelForm form, HttpServletRequest request, ModelMap model){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		Date now = new Date();
		Employee user = (Employee)request.getSession().getAttribute("UserLogin");
		Model modelObj = new Model();
		String msg = "";
		if(null != form.getModelID()){
			// update
			try{
				modelObj = modelService.selectByID(form.getModelID());
			}catch(Exception e){
				e.printStackTrace();
				model.addAttribute("errMsg", e.getMessage());
				
				List<Brand> brandList = new ArrayList<Brand>();
				List<Type> typeList = new ArrayList<Type>();
				try {
					typeList = typeService.getAll();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				if(typeList.size() > 0){
					try {
						Type type = typeService.selectByID(form.getTypeID());
						brandList = type.getBrands();
					}catch(Exception e2){
						e2.printStackTrace();
					}
				}
				if(brandList.size() == 0){
					Brand brand = new Brand();
					brand.setBrandID(null);
					brand.setName(" ");
					brandList.add(brand);
				}
				model.addAttribute("typeList", typeList);
				model.addAttribute("brandList", brandList);
				return VIEWNAME_FORM;
			}
			msg = this.messages.getMessage("msg.updateComplete", null, new Locale("th", "TH"));
		}else{
			// add
			modelObj.setCreatedBy(user.getEmployeeID());
			modelObj.setCreatedDate(now);
			msg = this.messages.getMessage("msg.addComplete", null, new Locale("th", "TH"));
		}
		modelObj.setName(form.getName());
		
		Type type = new Type();
		try {
			type = typeService.selectByID(form.getTypeID());
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		modelObj.setType(type);
		
		Brand brand = new Brand();
		try {
			brand = brandService.selectByID(form.getBrandID());
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelObj.setBrand(brand);
		modelObj.setUpdatedBy(user.getEmployeeID());
		modelObj.setUpdatedDate(now);
		
		try{
			modelService.save(modelObj);
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			
			List<Brand> brandList = new ArrayList<Brand>();
			List<Type> typeList = new ArrayList<Type>();
			try {
				typeList = typeService.getAll();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			if(typeList.size() > 0){
				try {
					Type type2 = typeService.selectByID(form.getTypeID());
					brandList = type2.getBrands();
				}catch(Exception e2){
					e2.printStackTrace();
				}
			}
			if(brandList.size() == 0){
				Brand brand2 = new Brand();
				brand2.setBrandID(null);
				brand2.setName(" ");
				brandList.add(brand2);
			}
			model.addAttribute("typeList", typeList);
			model.addAttribute("brandList", brandList);
			return VIEWNAME_FORM;
		}
		
		model.addAttribute("msg", msg);
		ModelSearchForm searchForm = new ModelSearchForm();
		model.addAttribute("searchForm", searchForm);
		
		List<Brand> brandList = new ArrayList<Brand>();
		List<Type> typeList = new ArrayList<Type>();
		try {
			typeList = typeService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("typeList", typeList);
		model.addAttribute("brandList", brandList);
		
		return VIEWNAME_SEARCH;
	}
	
	@RequestMapping(value = "/model", params = "do=preEdit")
	public String preEdit(@RequestParam(value="modelID") Integer modelID, ModelMap model, HttpServletRequest request){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		Model modelObj = new Model();
		modelObj = modelService.selectByID(modelID);
		if(modelObj.equals(new Model())){
			model.addAttribute("errMsg", "Cannot find data");
			ModelSearchForm searchForm = new ModelSearchForm();
			model.addAttribute("searchForm", searchForm);
			return VIEWNAME_SEARCH;
		}
		ModelForm form = new ModelForm();
		form.setModelID(modelObj.getModelID());
		form.setName(modelObj.getName());

		form.setTypeID(modelObj.getType().getTypeID());
		form.setBrandID(modelObj.getBrand().getBrandID());
		
		List<Brand> brandList = new ArrayList<Brand>();
		List<Type> typeList = new ArrayList<Type>();
		try {
			typeList = typeService.getAll();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if(typeList.size() > 0){
			try {
				Type type = typeService.selectByID(form.getTypeID());
				brandList = type.getBrands();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		if(brandList.size() == 0){
			Brand brand = new Brand();
			brand.setBrandID(null);
			brand.setName(" ");
			brandList.add(brand);
		}
		model.addAttribute("typeList", typeList);
		model.addAttribute("brandList", brandList);
		model.addAttribute("form", form);
		return VIEWNAME_FORM;
	}
	
	@RequestMapping(value = "/model", params = "do=delete")
	public @ResponseBody CustomGenericResponse delete(HttpServletRequest request){
		CustomGenericResponse response = new CustomGenericResponse();
		response.setSuccess(true);
		try{
			modelService.delete(Integer.valueOf(request.getParameter("modelID")));
			response.setMessage(this.messages.getMessage("msg.deleteSuccess", null, new Locale("th", "TH")));
		}catch(Exception e){
			e.printStackTrace();
			response.setSuccess(false);
			response.setMessage(this.messages.getMessage("error.cannotDelete", null, new Locale("th", "TH")));
		}
		return response;
	}
	
	@RequestMapping(value="/model", params = "do=getModel")
	public @ResponseBody List<Model> getModelByTypeAndBrand(@RequestParam(value="typeID") String typeID, @RequestParam(value="brandID") String brandID){
		List<Model> retList = new ArrayList<Model>();
		
		Integer brandCri = null;
		if(null != brandID && !brandID.equals("")){
			brandCri = Integer.valueOf(brandID);
			retList = modelService.getModelByTypeAndBrand(typeID, brandCri);
		}
		
		return retList;
	}
}
