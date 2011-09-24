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

import com.twobytes.master.form.TransportCompanyForm;
import com.twobytes.master.form.TransportCompanyGridData;
import com.twobytes.master.form.TransportCompanySearchForm;
import com.twobytes.master.service.TransportCompanyService;
import com.twobytes.model.CustomGenericResponse;
import com.twobytes.model.Employee;
import com.twobytes.model.GridResponse;
import com.twobytes.model.TransportCompany;
import com.twobytes.security.form.LoginForm;

@Controller
public class TransportCompanyController {
	
	@Autowired
	private TransportCompanyService tcService;
	
	@Autowired
	private MessageSource messages;
	
	public void setMessages(MessageSource messages) {
		this.messages = messages;
	}
	
	private String VIEWNAME_SEARCH = "transportCompany.search";
	private String VIEWNAME_FORM = "transportCompany.form";
	
	@RequestMapping(value = "/transportCompany")
	public String view(ModelMap model, HttpServletRequest request) {
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		TransportCompanySearchForm searchForm = new TransportCompanySearchForm();
		model.addAttribute("searchForm", searchForm);
		return VIEWNAME_SEARCH;
	}
	
	@RequestMapping(value="/searchTc")
	public @ResponseBody GridResponse getData(@RequestParam(value="name", required=false) String name, @RequestParam("rows") Integer rows, @RequestParam("page") Integer page, @RequestParam("sidx") String sidx, @RequestParam("sord") String sord){
		// Because default Tomcat URI encoding is iso-8859-1 so it must encode back to tis620
		try{
			if(null != name){
				name = new String(name.getBytes("iso-8859-1"), "tis620");	
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		List<TransportCompany> tcList = new ArrayList<TransportCompany>(); 
		try {
			tcList = tcService.selectByCriteria(name, rows, page, sidx, sord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		GridResponse response = new GridResponse();
		
		List<TransportCompanyGridData> rowsList = new ArrayList<TransportCompanyGridData>();
		
		Integer total_pages = 0;
		if(tcList.size() > 0){
			int endData = 0;
			if(tcList.size() < (rows*page)){
				endData = tcList.size();
			}else{
				endData = (rows*page);
			}
			for(int i=(rows*page - rows); i<endData; i++){
				TransportCompany tc = tcList.get(i);
				TransportCompanyGridData gridData = new TransportCompanyGridData();
				gridData.setTcID(tc.getTransportCompanyID().toString());
				gridData.setName(tc.getName());
				rowsList.add(gridData);
			}
			total_pages = new Double(Math.ceil(((double)tcList.size()/(double)rows))).intValue();
		}
		
		if (page > total_pages) page=total_pages;
		response.setPage(page.toString());
		response.setRecords(String.valueOf(tcList.size()));
		response.setTotal(total_pages.toString());
		response.setRows(rowsList);
		return response;
	}
	
	@RequestMapping(value = "/transportCompany", params = "do=preAdd")
	public String preAdd(ModelMap model, HttpServletRequest request){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		TransportCompanyForm form = new TransportCompanyForm();
		model.addAttribute("form", form);
		return VIEWNAME_FORM;
	}
	
	@RequestMapping(value = "/transportCompany", params = "do=save")
	public String doSave(@ModelAttribute("form") TransportCompanyForm form, HttpServletRequest request, ModelMap model){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		Date now = new Date();
		Employee user = (Employee)request.getSession().getAttribute("UserLogin");
		TransportCompany tc = new TransportCompany();
		String msg = "";
		if(null != form.getTcID()){
			// update
			try {
				tc = tcService.selectByID(form.getTcID());
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errMsg", e.getMessage());
				return VIEWNAME_FORM;
			}
			msg = this.messages.getMessage("msg.updateComplete", null, new Locale("th", "TH"));
		}else{
			// add
			tc.setCreatedBy(user.getEmployeeID());
			tc.setCreatedDate(now);
			msg = this.messages.getMessage("msg.addComplete", null, new Locale("th", "TH"));
		}
		tc.setName(form.getName());
		
		tc.setUpdatedBy(user.getEmployeeID());
		tc.setUpdatedDate(now);
		boolean canSave;
		try{
			canSave = tcService.save(tc);
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			return VIEWNAME_FORM;
		}
		if(!canSave){
			model.addAttribute("errMsg", this.messages.getMessage("error.cannotSave", null, new Locale("th", "TH")));
			return VIEWNAME_FORM;
		}
		model.addAttribute("msg", msg);
		TransportCompanySearchForm searchForm = new TransportCompanySearchForm();
		model.addAttribute("searchForm", searchForm);
		return VIEWNAME_SEARCH;
	}
	
	@RequestMapping(value = "/transportCompany", params = "do=preEdit")
	public String preEdit(@RequestParam(value="tcID") Integer tcID, ModelMap model, HttpServletRequest request){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		TransportCompany tc = new TransportCompany();
		try {
			tc = tcService.selectByID(tcID);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			TransportCompanySearchForm searchForm = new TransportCompanySearchForm();
			model.addAttribute("searchForm", searchForm);
			return VIEWNAME_SEARCH;
		}
		TransportCompanyForm form = new TransportCompanyForm();
		form.setTcID(tc.getTransportCompanyID());
		form.setName(tc.getName());
		model.addAttribute("form", form);
		return VIEWNAME_FORM;
	}
	
	@RequestMapping(value = "/transportCompany", params = "do=delete")
	public @ResponseBody CustomGenericResponse delete(HttpServletRequest request){
		CustomGenericResponse response = new CustomGenericResponse();
		response.setSuccess(true);
		try{
			tcService.delete(Integer.valueOf(request.getParameter("tcID")));
			response.setMessage(this.messages.getMessage("msg.deleteSuccess", null, new Locale("th", "TH")));
		}catch(Exception e){
			response.setSuccess(false);
			response.setMessage(this.messages.getMessage("error.cannotDelete", null, new Locale("th", "TH")));
		}
		return response;
	}
}
