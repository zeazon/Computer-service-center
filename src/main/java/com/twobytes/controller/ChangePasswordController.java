package com.twobytes.controller;

import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.twobytes.master.service.EmployeeService;
import com.twobytes.model.Employee;
import com.twobytes.security.form.ChangePasswordForm;
import com.twobytes.security.form.LoginForm;
import com.twobytes.security.service.SecurityService;

@Controller
public class ChangePasswordController {
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private MessageSource messages;
	
	public void setMessages(MessageSource messages) {
		this.messages = messages;
	}
	
	private String VIEWNAME_FORM = "changePassword.form";
	
	@RequestMapping(value = "/changePassword")
	public String view(ModelMap model, HttpServletRequest request) {
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		ChangePasswordForm form = new ChangePasswordForm();
		model.addAttribute("form", form);
		return VIEWNAME_FORM;
	}
	
	@RequestMapping(value = "/changePassword", params = "do=save")
	public String doSave(@ModelAttribute("form") ChangePasswordForm form, HttpServletRequest request, ModelMap model){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		Date now = new Date();
		Employee user = (Employee)request.getSession().getAttribute("UserLogin");
		String errMsg = "";
		String msg = "";
		Employee emp = new Employee();
		try{
			emp = securityService.login(user.getLogin(), form.getOldPassword());
		}catch(Exception e){
			errMsg = this.messages.getMessage("error.passwordMismatch", null, new Locale("th", "TH"));
			model.addAttribute("errMsg", errMsg);
			form = new ChangePasswordForm();
			return VIEWNAME_FORM;
		}
		
		if(emp != null){
			emp.setPassword(form.getNewPassword());
			emp.setUpdatedBy(user.getEmployeeID());
			emp.setUpdatedDate(now);
			
			try{
				empService.save(emp);
			}catch(Exception e){
				errMsg = e.getMessage();
				model.addAttribute("errMsg", errMsg);
				form = new ChangePasswordForm();
				return VIEWNAME_FORM;
			}
			
			msg = this.messages.getMessage("msg.updateComplete", null, new Locale("th", "TH"));
		}else{
			errMsg = this.messages.getMessage("error.passwordMismatch", null, new Locale("th", "TH"));
			model.addAttribute("errMsg", errMsg);
			form = new ChangePasswordForm();
			return VIEWNAME_FORM;
		}
		form = new ChangePasswordForm();
		model.addAttribute("form", form);
		model.addAttribute("msg", msg);
		return VIEWNAME_FORM;
	}
}
