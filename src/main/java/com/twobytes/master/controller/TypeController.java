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

import com.twobytes.master.form.TypeForm;
import com.twobytes.master.form.TypeGridData;
import com.twobytes.master.form.TypeSearchForm;
import com.twobytes.master.service.TypeService;
import com.twobytes.model.CustomGenericResponse;
import com.twobytes.model.Employee;
import com.twobytes.model.GridResponse;
import com.twobytes.model.Type;
import com.twobytes.security.form.LoginForm;

@Controller
public class TypeController {
	
	@Autowired
	private TypeService typeService;
	
	@Autowired
	private MessageSource messages;
	
	public void setMessages(MessageSource messages) {
		this.messages = messages;
	}
	
	private String VIEWNAME_SEARCH = "type.search";
	private String VIEWNAME_FORM = "type.form";
	
	@RequestMapping(value = "/type")
	public String view(ModelMap model, HttpServletRequest request) {
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		TypeSearchForm searchForm = new TypeSearchForm();
		model.addAttribute("searchForm", searchForm);
		return VIEWNAME_SEARCH;
	}
	
	@RequestMapping(value="/searchType")
	public @ResponseBody GridResponse getData(@RequestParam(value="name", required=false) String name, @RequestParam("rows") Integer rows, @RequestParam("page") Integer page, @RequestParam("sidx") String sidx, @RequestParam("sord") String sord){
		// Because default Tomcat URI encoding is iso-8859-1 so it must encode back to tis620
		try{
			if(null != name){
				name = new String(name.getBytes("iso-8859-1"), "tis620");	
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		List<Type> typeList = new ArrayList<Type>(); 
			try {
				typeList = typeService.selectByCriteria(name, rows, page, sidx, sord);
			} catch (Exception e) {
				e.printStackTrace();
			}
		GridResponse response = new GridResponse();
		
		List<TypeGridData> rowsList = new ArrayList<TypeGridData>();
		
		Integer total_pages = 0;
		if(typeList.size() > 0){
			int endData = 0;
			if(typeList.size() < (rows*page)){
				endData = typeList.size();
			}else{
				endData = (rows*page);
			}
			for(int i=(rows*page - rows); i<endData; i++){
				Type type = typeList.get(i);
				TypeGridData gridData = new TypeGridData();
				gridData.setTypeID(type.getTypeID().toString());
				gridData.setName(type.getName());
				rowsList.add(gridData);
			}
			total_pages = new Double(Math.ceil(((double)typeList.size()/(double)rows))).intValue();
		}
		
		if (page > total_pages) page=total_pages;
		response.setPage(page.toString());
		response.setRecords(String.valueOf(typeList.size()));
		response.setTotal(total_pages.toString());
		response.setRows(rowsList);
		return response;
	}
	
	@RequestMapping(value = "/type", params = "do=preAdd")
	public String preAdd(ModelMap model, HttpServletRequest request){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		TypeForm form = new TypeForm();
		model.addAttribute("form", form);
		model.addAttribute("mode", "add");
		return VIEWNAME_FORM;
	}
	
	@RequestMapping(value = "/type", params = "do=save")
	public String doSave(@ModelAttribute("form") TypeForm form, HttpServletRequest request, ModelMap model,@RequestParam String mode){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		Date now = new Date();
		Employee user = (Employee)request.getSession().getAttribute("UserLogin");
		Type type = new Type();
		String msg = "";
//		if(null != form.getTypeID()){
//		if (!form.getTypeID().equals("")) {
		if(mode.equals("edit")){
			// update
			try {
				type = typeService.selectByID(form.getTypeID());
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errMsg", e.getMessage());
				return VIEWNAME_FORM;
			}
			msg = this.messages.getMessage("msg.updateComplete", null, new Locale("th", "TH"));
		}else{
			// add
			type.setCreatedBy(user.getEmployeeID());
			type.setCreatedDate(now);
			msg = this.messages.getMessage("msg.addComplete", null, new Locale("th", "TH"));
		}
		type.setTypeID(form.getTypeID());
		type.setName(form.getName());
		
		type.setUpdatedBy(user.getEmployeeID());
		type.setUpdatedDate(now);
		boolean canSave;
		try{
			canSave = typeService.save(type);
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			model.addAttribute("mode", mode);
			return VIEWNAME_FORM;
		}
		if(!canSave){
			model.addAttribute("errMsg", this.messages.getMessage("error.cannotSave", null, new Locale("th", "TH")));
			model.addAttribute("mode", mode);
			return VIEWNAME_FORM;
		}
		model.addAttribute("msg", msg);
		TypeSearchForm searchForm = new TypeSearchForm();
		model.addAttribute("searchForm", searchForm);
		return VIEWNAME_SEARCH;
	}
	
	@RequestMapping(value = "/type", params = "do=preEdit")
	public String preEdit(@RequestParam(value="typeID") String typeID, ModelMap model, HttpServletRequest request){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		Type type = new Type();
			try {
				type = typeService.selectByID(typeID);
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errMsg", e.getMessage());
				TypeSearchForm searchForm = new TypeSearchForm();
				model.addAttribute("searchForm", searchForm);
				return VIEWNAME_SEARCH;
			}
		TypeForm form = new TypeForm();
		form.setTypeID(type.getTypeID());
		form.setName(type.getName());
		model.addAttribute("form", form);
		model.addAttribute("mode", "edit");
		return VIEWNAME_FORM;
	}
	
	@RequestMapping(value = "/type", params = "do=delete")
	public @ResponseBody CustomGenericResponse delete(HttpServletRequest request){
		CustomGenericResponse response = new CustomGenericResponse();
		response.setSuccess(true);
		try{
			typeService.delete(String.valueOf(request.getParameter("typeID")));
//			response.setMessage("Action successful!");
			response.setMessage(this.messages.getMessage("msg.deleteSuccess", null, new Locale("th", "TH")));
		}catch(Exception e){
			e.printStackTrace();
			response.setSuccess(false);
//			response.setMessage("Action failure!");
			response.setMessage(this.messages.getMessage("error.cannotDelete", null, new Locale("th", "TH")));
		}
		return response;
	}
	
}
