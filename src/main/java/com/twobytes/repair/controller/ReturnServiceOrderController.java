package com.twobytes.repair.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twobytes.master.service.TypeService;
import com.twobytes.model.CustomGenericResponse;
import com.twobytes.model.Customer;
import com.twobytes.model.Employee;
import com.twobytes.model.GridResponse;
import com.twobytes.model.ServiceOrder;
import com.twobytes.model.Type;
import com.twobytes.repair.form.ServiceOrderGridData;
import com.twobytes.repair.form.ServiceOrderSearchForm;
import com.twobytes.repair.service.ServiceOrderService;
import com.twobytes.security.form.LoginForm;

@Controller
public class ReturnServiceOrderController {

	@Autowired
	private ServiceOrderService soService;
	
	@Autowired
	private TypeService typeService;
	
	@Autowired
	private MessageSource messages;
	
	public void setMessages(MessageSource messages) {
		this.messages = messages;
	}
	
	private String VIEWNAME_SEARCH = "returnServiceOrder.search";
	
	private SimpleDateFormat sdfDateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale ( "US" ));
	
	@RequestMapping(value = "/returnServiceOrder")
	public String view(ModelMap model, HttpServletRequest request) {
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
	
	@RequestMapping(value="/searchReturnServiceOrder")
	public @ResponseBody GridResponse getData(@RequestParam(value="name", required=false) String name, @RequestParam(value="date", required=false) String date, @RequestParam(value="type", required=false) String type, @RequestParam("rows") Integer rows, @RequestParam("page") Integer page, @RequestParam("sidx") String sidx, @RequestParam("sord") String sord){
		// Because default Tomcat URI encoding is iso-8859-1 so it must encode back to tis620
		String[] datePart;
		String searchDate = null;
		try{
			if(null != name){
				name = new String(name.getBytes("iso-8859-1"), "tis620");	
			}
//			if(null != surname){
//				surname = new String(surname.getBytes("iso-8859-1"), "tis620");	
//			}
			if(null != date && !date.equals("")){
				date = new String(date.getBytes("iso-8859-1"), "tis620");
				datePart = date.split("/");
				// Change year to Christ year
//				Integer year = Integer.parseInt(datePart[2]);				
//				year = year - 543;
				searchDate = datePart[2]+"-"+datePart[1]+"-"+datePart[0];
			}
			if(null != type){
				type = new String(type.getBytes("iso-8859-1"), "tis620");	
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		
		List<ServiceOrder> soList = soService.selectFixedSOByCriteria(name, searchDate, type, rows, page, sidx, sord);
		GridResponse response = new GridResponse();
		
		List<ServiceOrderGridData> rowsList = new ArrayList<ServiceOrderGridData>();
		
		Integer total_pages = 0;
		if(soList.size() > 0){
			int endData = 0;
			if(soList.size() < (rows*page)){
				endData = soList.size();
			}else{
				endData = (rows*page);
			}
			for(int i=(rows*page - rows); i<endData; i++){
				ServiceOrder so = soList.get(i);
				ServiceOrderGridData gridData = new ServiceOrderGridData();
				gridData.setServiceOrderID(so.getServiceOrderID());
				gridData.setServiceOrderDate(sdfDateTime.format(so.getServiceOrderDate()));
				Customer customer = so.getCustomer();
				gridData.setName(customer.getName());
				gridData.setTel(customer.getTel());
				gridData.setMobileTel(customer.getMobileTel());
				gridData.setStatus(so.getStatus());
				gridData.setCannotMakeContact(so.getCannotMakeContact());
				rowsList.add(gridData);
			}
			total_pages = new Double(Math.ceil(((double)soList.size()/(double)rows))).intValue();
		}
		
		if (page > total_pages) page=total_pages;
		response.setPage(page.toString());
		response.setRecords(String.valueOf(soList.size()));
		response.setTotal(total_pages.toString());
		response.setRows(rowsList);
		return response;
	}
	
	@RequestMapping(value = "/returnServiceOrder", params = "do=returnServiceOrder")
	public @ResponseBody CustomGenericResponse returnServiceOrder(HttpServletRequest request){
		Employee user = (Employee)request.getSession().getAttribute("UserLogin");
		CustomGenericResponse response = new CustomGenericResponse();
		response.setSuccess(true);
		response.setMessage(this.messages.getMessage("msg.returnServiceOrder_success", null, new Locale("th", "TH")));
		
		boolean success = true;
		try{
			Date now = new Date();
			ServiceOrder so = soService.selectByID(request.getParameter("serviceOrderID"));
			
			so.setReturnDate(now);
			so.setStatus(ServiceOrder.CLOSE);
			so.setUpdatedBy(user.getEmployeeID());
			so.setUpdatedDate(now);
			success = soService.edit(so);
		}catch(Exception e){
			response.setSuccess(false);
			response.setMessage(this.messages.getMessage("msg.returnServiceOrder_failure", null, new Locale("th", "TH")));
			success = false;
		}
		if(!success){
			response.setSuccess(false);
			response.setMessage(this.messages.getMessage("msg.getServiceOrder_failure", null, new Locale("th", "TH")));
		}
		return response;
	}

}
