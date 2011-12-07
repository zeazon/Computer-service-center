package com.twobytes.report.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.twobytes.model.ServiceOrder;
import com.twobytes.repair.form.ServiceOrderSearchForm;
import com.twobytes.repair.service.ServiceOrderService;
import com.twobytes.report.form.RepairReportForm;
import com.twobytes.security.form.LoginForm;

@Controller
public class RepairReportController {
	
	@Autowired
	private ServiceOrderService soService;
	
	private String VIEWNAME_SEARCH = "repairReport.search";
	
	private SimpleDateFormat sdfDateTime = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm", new Locale("US"));
	
	@RequestMapping(value = "/repairReport")
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
	
	@RequestMapping(value = "/repairReport", params = "do=printReport")
	public String doPrintReport(@ModelAttribute ServiceOrderSearchForm form,
			HttpServletRequest request, ModelMap model) {
		String[] datePart;
		String searchStartDate = null;
		String searchEndDate = null;
		if (null != form.getStartDate() && !form.getStartDate().equals("")) {
//			form.setStartDate(new String(form.getStartDate().getBytes("iso-8859-1"),"tis620"));
			datePart = form.getStartDate().split("/");
			// Change year to Christ year
			/*Integer year = Integer.parseInt(datePart[2]);
			year = year - 543;*/
			//searchStartDate = year.toString() + "-" + datePart[1] + "-"
			//		+ datePart[0];
			searchStartDate = datePart[2] + "-" + datePart[1] + "-"
					+ datePart[0];
		}
		if (null != form.getEndDate() && !form.getEndDate().equals("")) {
//			form.setEndDate(new String(form.getEndDate().getBytes("iso-8859-1"), "tis620"));
			datePart = form.getEndDate().split("/");
			// Change year to Christ year
			/*Integer year = Integer.parseInt(datePart[2]);
			year = year - 543;*/
			//searchEndDate = year.toString() + "-" + datePart[1] + "-"
			//		+ datePart[0];
			searchEndDate = datePart[2] + "-" + datePart[1] + "-"
					+ datePart[0];
		}
		
		List<ServiceOrder> retList = soService.getRepairReport(searchStartDate, searchEndDate);
		List<RepairReportForm> reportResultList = new ArrayList<RepairReportForm>();
		for(ServiceOrder so:retList){
			RepairReportForm rrf = new RepairReportForm();
			rrf.setServiceOrderID(so.getServiceOrderID());
			rrf.setServiceOrderDate(sdfDateTime.format(so.getServiceOrderDate()));
			if(so.getStartFix() != null){
				rrf.setStartFix(sdfDateTime.format(so.getStartFix()));
			}else{
				rrf.setStartFix("-");
			}
			if(so.getEndFix() != null){
				rrf.setEndFix(sdfDateTime.format(so.getEndFix()));
			}else{
				rrf.setEndFix("-");
			}
			if(so.getReturnDate() != null){
				rrf.setReturnDate(sdfDateTime.format(so.getReturnDate()));
			}else{
				rrf.setReturnDate("-");
			}
			reportResultList.add(rrf);
		}
		
		model.addAttribute("reportResultList", reportResultList);
		model.addAttribute("startDate",form.getStartDate());
		model.addAttribute("endDate",form.getEndDate());
		return "repairReportDoc";
	}
}
