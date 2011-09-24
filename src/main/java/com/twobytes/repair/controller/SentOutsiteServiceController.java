package com.twobytes.repair.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twobytes.master.service.TypeService;
import com.twobytes.model.Customer;
import com.twobytes.model.Employee;
import com.twobytes.model.GridResponse;
import com.twobytes.model.OutsiteService;
import com.twobytes.model.ServiceOrder;
import com.twobytes.model.Type;
import com.twobytes.repair.form.OutsiteServiceForm;
import com.twobytes.repair.form.OutsiteServiceGridData;
import com.twobytes.repair.form.OutsiteServiceSearchForm;
import com.twobytes.repair.service.OutsiteServiceService;
import com.twobytes.repair.service.ServiceOrderService;
import com.twobytes.security.form.LoginForm;

@Controller
public class SentOutsiteServiceController {

	@Autowired
	private OutsiteServiceService osService;
	
	@Autowired
	private TypeService typeService;
	
	@Autowired
	private ServiceOrderService soService;
	
	@Autowired
	private MessageSource messages;
	
	public void setMessages(MessageSource messages) {
		this.messages = messages;
	}
	
	private String VIEWNAME_SEARCH = "sentOutsiteService.search";
	private String VIEWNAME_FORM = "sentOutsiteService.form";
	
	private SimpleDateFormat sdfDateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("th", "TH"));
	private SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy", new Locale("th", "TH"));
	
	@RequestMapping(value = "/sentOutsiteService")
	public String view(ModelMap model, HttpServletRequest request) {
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		OutsiteServiceSearchForm searchForm = new OutsiteServiceSearchForm();
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
	
	@RequestMapping(value="/searchSentOutsiteService")
	public @ResponseBody GridResponse getData(@RequestParam(value="name", required=false) String name, @RequestParam(value="surname", required=false) String surname, @RequestParam(value="date", required=false) String date, @RequestParam(value="type", required=false) String type, @RequestParam("rows") Integer rows, @RequestParam("page") Integer page, @RequestParam("sidx") String sidx, @RequestParam("sord") String sord){
		// Because default Tomcat URI encoding is iso-8859-1 so it must encode back to tis620
		String[] datePart;
		String searchDate = null;
		try{
			if(null != name){
				name = new String(name.getBytes("iso-8859-1"), "tis620");	
			}
			if(null != surname){
				surname = new String(surname.getBytes("iso-8859-1"), "tis620");	
			}
			if(null != date && !date.equals("")){
				date = new String(date.getBytes("iso-8859-1"), "tis620");
				datePart = date.split("/");
				// Change year to Christ year
				Integer year = Integer.parseInt(datePart[2]);				
				year = year - 543;
				searchDate = year.toString()+"-"+datePart[1]+"-"+datePart[0];
			}
			if(null != type){
				type = new String(type.getBytes("iso-8859-1"), "tis620");	
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		
		List<OutsiteService> osList = osService.selectNewOSByCriteria(name, surname, searchDate, type, rows, page, sidx, sord);
		GridResponse response = new GridResponse();
		
		List<OutsiteServiceGridData> rowsList = new ArrayList<OutsiteServiceGridData>();
		
		Integer total_pages = 0;
		if(osList.size() > 0){
//			int i=0;
//			for(OutsiteService os:osList){
//				if(i >= (rows*page - rows) && i <= (rows*page - 1)){
			int endData = 0;
			if(osList.size() < (rows*page)){
				endData = osList.size();
			}else{
				endData = (rows*page);
			}
			for(int i=(rows*page - rows); i<endData; i++){
				OutsiteService os = osList.get(i);
				OutsiteServiceGridData gridData = new OutsiteServiceGridData();
				gridData.setOutsiteServiceID(os.getOutsiteServiceID().toString());
				gridData.setOutsiteServiceDate(sdfDateTime.format(os.getOutsiteServiceDate()));
				Customer customer = os.getServiceOrder().getCustomer();
				gridData.setName(customer.getName());
//				gridData.setSurname(customer.getSurname());
				gridData.setType(os.getServiceOrder().getProduct().getType().getName());
				gridData.setBrand(os.getServiceOrder().getProduct().getBrand().getName());
				gridData.setModel(os.getServiceOrder().getProduct().getModel().getName());
				gridData.setProblem(os.getServiceOrder().getProblem());
				gridData.setStatus(os.getStatus());
				rowsList.add(gridData);
			}
			total_pages = new Double(Math.ceil(((double)osList.size()/(double)rows))).intValue();
		}
		
		if (page > total_pages) page=total_pages;
		response.setPage(page.toString());
		response.setRecords(String.valueOf(osList.size()));
		response.setTotal(total_pages.toString());
		response.setRows(rowsList);
		return response;
	}
	
	@RequestMapping(value = "/sentOutsiteService", params = "do=preEdit")
	public String preEdit(@RequestParam(value="outsiteServiceID") String outsiteServiceID, ModelMap model, HttpServletRequest request){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		OutsiteService os = osService.selectByID(Integer.parseInt(outsiteServiceID));
		OutsiteServiceForm form = new OutsiteServiceForm();
		form.setOutsiteServiceID(os.getOutsiteServiceID().toString());
		form.setOutsiteServiceDate(sdfDateTime.format(os.getOutsiteServiceDate()));
		form.setServiceType(os.getServiceType());
		form.setServiceOrderID(os.getServiceOrder().getServiceOrderID());
		form.setOutsiteCompanyID(os.getOutsiteCompany().getOutsiteCompanyID());
		form.setTransportCompanyID(os.getTransportCompany().getTransportCompanyID());
		
		form.setServiceOrder(os.getServiceOrder());
		
		form.setAccessories(os.getAccessories());
		form.setTransportCompanyName(os.getTransportCompany().getName());
		form.setOutsiteCompanyName(os.getOutsiteCompany().getName());
		
		model.addAttribute("form", form);
		model.addAttribute("fullAddr", os.getServiceOrder().getCustomer().getAddress()+" "+this.messages.getMessage("subdistrict_abbr", null, new Locale("th", "TH"))+" "+os.getServiceOrder().getCustomer().getSubdistrict().getName()+" "+this.messages.getMessage("district_abbr", null, new Locale("th", "TH"))+" "+os.getServiceOrder().getCustomer().getDistrict().getName()+" "+this.messages.getMessage("province_abbr", null, new Locale("th", "TH"))+" "+os.getServiceOrder().getCustomer().getProvince().getName());
		
		return VIEWNAME_FORM;
	}
	
	@RequestMapping(value = "/sentOutsiteService", params = "do=save")
	public String doSave(@ModelAttribute("form") OutsiteServiceForm form, HttpServletRequest request, ModelMap model){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		Date now = new Date();
		Employee user = (Employee)request.getSession().getAttribute("UserLogin");
		OutsiteService os = osService.selectByID(Integer.parseInt(form.getOutsiteServiceID()));
		try {
			os.setSentDate(sdfDate.parse(form.getSentDate()));
		} catch (ParseException e) {
			e.printStackTrace();
			os.setSentDate(now);
		}
		os.setSentTransportNo(form.getSentTransportNo());
		os.setStatus(OutsiteService.SENT);
		os.setUpdatedBy(user.getEmployeeID());
		os.setUpdatedDate(now);
		
		ServiceOrder so = os.getServiceOrder();
		so.setStatus(ServiceOrder.OUTSITE);
		so.setUpdatedBy(user.getEmployeeID());
		so.setUpdatedDate(now);
		os.setServiceOrder(so);
		
		boolean canSave;
		try{
			canSave = osService.sent(os);
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			return VIEWNAME_FORM;
		}
		
		if(!canSave){
			model.addAttribute("errMsg", this.messages.getMessage("error.cannotSave", null, new Locale("th", "TH")));
			return VIEWNAME_FORM;
		}
		
		String msg = this.messages.getMessage("msg.updateComplete", null, new Locale("th", "TH"));
		
		model.addAttribute("msg", msg);
		OutsiteServiceSearchForm searchForm = new OutsiteServiceSearchForm();
		model.addAttribute("searchForm", searchForm);
		return VIEWNAME_SEARCH;
	}
}
