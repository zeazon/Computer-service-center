package com.twobytes.report.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.twobytes.repair.form.ServiceOrderSearchForm;
import com.twobytes.repair.service.ServiceOrderService;
import com.twobytes.report.form.SumAmountReportForm;
import com.twobytes.security.form.LoginForm;

@Controller
public class SumAmountReportController {

	@Autowired
	private ServiceOrderService soService;
	
	private String VIEWNAME_SEARCH = "sumAmountReport.search";
	
	@RequestMapping(value = "/sumAmountReport")
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
	
	@RequestMapping(value = "/sumAmountReport", params = "do=printReport")
	public String doPrintReport(@ModelAttribute ServiceOrderSearchForm form,
			HttpServletRequest request, ModelMap model) throws ParseException {
		String[] datePart;
		String searchStartDate = null;
		String searchEndDate = null;

		if (null != form.getStartDate() && !form.getStartDate().equals("")) {
			datePart = form.getStartDate().split("/");
			searchStartDate = datePart[2] + "-" + datePart[1] + "-"
					+ datePart[0];
		}
		if (null != form.getEndDate() && !form.getEndDate().equals("")) {
			datePart = form.getEndDate().split("/");
			searchEndDate = datePart[2] + "-" + datePart[1] + "-"
					+ datePart[0];
		}
				
		List<SumAmountReportForm> reportResultList = soService.getSumAmountReport(searchStartDate, searchEndDate);
		
		model.addAttribute("reportResultList", reportResultList);
		model.addAttribute("startDate",form.getStartDate());
		model.addAttribute("endDate",form.getEndDate());
		return "sumAmountReportDoc";
	}
}
