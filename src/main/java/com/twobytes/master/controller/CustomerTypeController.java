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

import com.twobytes.master.form.CustomerTypeForm;
import com.twobytes.master.form.CustomerTypeGridData;
import com.twobytes.master.form.CustomerTypeSearchForm;
import com.twobytes.master.service.CustomerTypeService;
import com.twobytes.model.CustomGenericResponse;
import com.twobytes.model.CustomerType;
import com.twobytes.model.Employee;
import com.twobytes.model.GridResponse;
import com.twobytes.security.form.LoginForm;

@Controller
public class CustomerTypeController {

	@Autowired
	private CustomerTypeService customerTypeService;
	
	@Autowired
	private MessageSource messages;
	
	public void setMessages(MessageSource messages) {
		this.messages = messages;
	}
	
	private static final Logger logger = LoggerFactory
	.getLogger(CustomerTypeController.class);
	
	private String VIEWNAME_SEARCH = "customerType.search";
	private String VIEWNAME_FORM = "customerType.form";
	
	@RequestMapping(value = "/customerType")
	public String view(ModelMap model, HttpServletRequest request) {
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		CustomerTypeSearchForm searchForm = new CustomerTypeSearchForm();
		model.addAttribute("searchForm", searchForm);
		return VIEWNAME_SEARCH;
	}
	
	@RequestMapping(value="/searchCustomerType")
	public @ResponseBody GridResponse getData(@RequestParam(value="name", required=false) String name, @RequestParam("rows") Integer rows, @RequestParam("page") Integer page, @RequestParam("sidx") String sidx, @RequestParam("sord") String sord){
		// Because default Tomcat URI encoding is iso-8859-1 so it must encode back to tis620
		try{
			if(null != name){
				name = new String(name.getBytes("iso-8859-1"), "tis620");	
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		List<CustomerType> customerTypeList = new ArrayList<CustomerType>();
		
		try {
			customerTypeList = customerTypeService.selectByCriteria(name, rows, page, sidx, sord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		GridResponse response = new GridResponse();
		
		List<CustomerTypeGridData> rowsList = new ArrayList<CustomerTypeGridData>();
		
		Integer total_pages = 0;
		if(customerTypeList.size() > 0){
			int endData = 0;
			if(customerTypeList.size() < (rows*page)){
				endData = customerTypeList.size();
			}else{
				endData = (rows*page);
			}
			for(int i=(rows*page - rows); i<endData; i++){
				CustomerType customerType = customerTypeList.get(i);
				CustomerTypeGridData gridData = new CustomerTypeGridData();
				gridData.setCustomerTypeID(customerType.getCustomerTypeID());
				gridData.setName(customerType.getName());
				
				rowsList.add(gridData);
			}
			total_pages = new Double(Math.ceil(((double)customerTypeList.size()/(double)rows))).intValue();
		}

		if (page > total_pages) page=total_pages;
		response.setPage(page.toString());
		response.setRecords(String.valueOf(customerTypeList.size()));
		response.setTotal(total_pages.toString());
		response.setRows(rowsList);
		return response;
	}
	
	@RequestMapping(value = "/customerType", params = "do=preAdd")
	public String preAdd(ModelMap model, HttpServletRequest request){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		CustomerTypeForm form = new CustomerTypeForm();
		
		model.addAttribute("form", form);
		model.addAttribute("mode", "add");
		return VIEWNAME_FORM;
	}
	
	@RequestMapping(value = "/customerType", params = "do=save")
	public String doSave(@ModelAttribute("form") CustomerTypeForm form, HttpServletRequest request, ModelMap model, @RequestParam String mode){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		Date now = new Date();
		Employee user = (Employee)request.getSession().getAttribute("UserLogin");
		CustomerType customerType = new CustomerType();
		String msg = "";
		if(mode.equals("edit")){
			// update
			try{
				customerType = customerTypeService.selectByID(form.getCustomerTypeID());
			}catch(Exception e){
				e.printStackTrace();
				model.addAttribute("errMsg", e.getMessage());
				return VIEWNAME_FORM;
			}
			msg = this.messages.getMessage("msg.updateComplete", null, new Locale("th", "TH"));
		}else{
			// add
			customerType.setCustomerTypeID(form.getCustomerTypeID());
			
			customerType.setCreatedBy(user.getEmployeeID());
			customerType.setCreatedDate(now);
			msg = this.messages.getMessage("msg.addComplete", null, new Locale("th", "TH"));
		}
		customerType.setName(form.getName());
		
		customerType.setUpdatedBy(user.getEmployeeID());
		customerType.setUpdatedDate(now);
		
		try {
			customerTypeService.save(customerType);
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			model.addAttribute("mode", mode);
			return VIEWNAME_FORM;
		}
		
		model.addAttribute("msg", msg);
		CustomerTypeSearchForm searchForm = new CustomerTypeSearchForm();
		model.addAttribute("searchForm", searchForm);
		return VIEWNAME_SEARCH;
	}
	
	@RequestMapping(value = "/customerType", params = "do=preEdit")
	public String preEdit(@RequestParam(value="customerTypeID") String customerTypeID, ModelMap model, HttpServletRequest request){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		logger.debug("edit customerTypeID = "+request.getParameter("customerTypeID"));
		CustomerType customerType = new CustomerType();
		try{
			customerType = customerTypeService.selectByID(customerTypeID);
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			CustomerTypeSearchForm searchForm = new CustomerTypeSearchForm();
			model.addAttribute("searchForm", searchForm);
			return VIEWNAME_SEARCH;
		}
		CustomerTypeForm form = new CustomerTypeForm();
		form.setCustomerTypeID(customerType.getCustomerTypeID());
		form.setName(customerType.getName());
		model.addAttribute("form", form);
		model.addAttribute("mode", "edit");
		return VIEWNAME_FORM;
	}
	
	@RequestMapping(value = "/customerType", params = "do=delete")
	public @ResponseBody CustomGenericResponse delete(HttpServletRequest request){
		logger.debug("delete customerTypeID = "+request.getParameter("customerTypeID"));
		CustomGenericResponse response = new CustomGenericResponse();
		response.setSuccess(true);
		try{
			customerTypeService.delete(request.getParameter("customerTypeID"));
			response.setMessage(this.messages.getMessage("msg.deleteSuccess", null, new Locale("th", "TH")));
		}catch(Exception e){
			e.printStackTrace();
			response.setSuccess(false);
			response.setMessage(this.messages.getMessage("error.cannotDelete", null, new Locale("th", "TH")));
		}
		return response;
	}
}
