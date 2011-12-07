package com.twobytes.report.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.twobytes.repair.form.ServiceOrderSearchForm;
import com.twobytes.repair.service.ServiceOrderService;
import com.twobytes.report.form.NumRepairReportForm;
import com.twobytes.security.form.LoginForm;

@Controller
public class NumRepairReportController {
	
	@Autowired
	private ServiceOrderService soService;
	
	private String VIEWNAME_SEARCH = "numRepairReport.search";
	
	private SimpleDateFormat sdfDateSQL = new SimpleDateFormat(
			"yyyy-MM-dd", new Locale("US"));
	private SimpleDateFormat sdfDate = new SimpleDateFormat(
			"dd/MM/yyyy", new Locale("US"));
	
	@RequestMapping(value = "/numRepairReport")
	public String view(ModelMap model, HttpServletRequest request) {
		if (null == request.getSession().getAttribute("UserLogin")) {
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		ServiceOrderSearchForm searchForm = new ServiceOrderSearchForm();
		model.addAttribute("searchForm", searchForm);
		return VIEWNAME_SEARCH;
	}
	
	@RequestMapping(value = "/numRepairReport", params = "do=printReport")
	public String doPrintReport(@ModelAttribute ServiceOrderSearchForm form,
			HttpServletRequest request, ModelMap model) throws ParseException {
		Date startDate = new Date();
		Date endDate;
		Calendar searchDate = Calendar.getInstance();
		if (null != form.getStartDate() && !form.getStartDate().equals("")) {
			startDate = sdfDate.parse(form.getStartDate());
			
			searchDate.setTime(startDate);
		}
		if (null != form.getEndDate() && !form.getEndDate().equals("")) {
			endDate = sdfDate.parse(form.getEndDate());
		}else{
			endDate = new Date();
		}
		
		List<NumRepairReportForm> retList = new ArrayList<NumRepairReportForm>();
		do{
			NumRepairReportForm reportForm = soService.getNumRepairReport(sdfDateSQL.format(searchDate.getTime()));
			retList.add(reportForm);
			searchDate.add(Calendar.DATE, 1);
		}while(searchDate.getTime().before(endDate) || searchDate.getTime().equals(endDate));
		
		model.addAttribute("reportResultList", retList);
		model.addAttribute("startDate",sdfDate.format(startDate));
		model.addAttribute("endDate",sdfDate.format(endDate));
		return "numRepairReportDoc";
	}
}
