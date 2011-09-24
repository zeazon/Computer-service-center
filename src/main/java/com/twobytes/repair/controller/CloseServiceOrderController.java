package com.twobytes.repair.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.twobytes.master.service.TypeService;
import com.twobytes.model.Type;
import com.twobytes.repair.form.ServiceOrderSearchForm;
import com.twobytes.repair.service.ServiceOrderService;
import com.twobytes.security.form.LoginForm;

@Controller
public class CloseServiceOrderController {

	@Autowired
	private ServiceOrderService soService;
	
	@Autowired
	private TypeService typeService;
	
	@Autowired
	private MessageSource messages;
	
	public void setMessages(MessageSource messages) {
		this.messages = messages;
	}
	
	private String VIEWNAME_SEARCH = "closeServiceOrder.search";
	private String VIEWNAME_FORM = "closeServiceOrder.form";
	
	@RequestMapping(value = "/closeServiceOrder")
	public String view(ModelMap model, HttpServletRequest request) {
		System.out.println("CloseServiceOrderController:view");
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		ServiceOrderSearchForm searchForm = new ServiceOrderSearchForm();
		model.addAttribute("searchForm", searchForm);
		List<Type> typeList = new ArrayList<Type>();
		try {
			typeList = typeService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("typeList", typeList);
		return VIEWNAME_SEARCH;
	}
}
