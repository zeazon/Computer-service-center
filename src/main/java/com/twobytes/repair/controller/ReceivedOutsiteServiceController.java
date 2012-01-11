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
import com.twobytes.security.form.LoginForm;

@Controller
public class ReceivedOutsiteServiceController {
	
	@Autowired
	private OutsiteServiceService osService;
	
	@Autowired
	private TypeService typeService;
	
	@Autowired
	private MessageSource messages;
	
	public void setMessages(MessageSource messages) {
		this.messages = messages;
	}
	
	private String VIEWNAME_SEARCH = "receivedOutsiteService.search";
	private String VIEWNAME_FORM = "receivedOutsiteService.form";
	
	private SimpleDateFormat sdfDateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("US"));
	private SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy", new Locale("US"));
	
	@RequestMapping(value = "/receivedOutsiteService")
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
	
	@RequestMapping(value="/searchReceivedOutsiteService")
	public @ResponseBody GridResponse getData(@RequestParam(value="name", required=false) String name, @RequestParam(value="date", required=false) String date, @RequestParam(value="type", required=false) String type, @RequestParam("rows") Integer rows, @RequestParam("page") Integer page, @RequestParam("sidx") String sidx, @RequestParam("sord") String sord){
		// Because default Tomcat URI encoding is iso-8859-1 so it must encode back to tis620
		String[] datePart;
		String searchDate = null;
		try{
			if(null != name){
				name = new String(name.getBytes("iso-8859-1"), "tis620");	
			}
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
		
		List<OutsiteService> osList = osService.selectSentOSByCriteria(name, searchDate, type, rows, page, sidx, sord);
		GridResponse response = new GridResponse();
		
		List<OutsiteServiceGridData> rowsList = new ArrayList<OutsiteServiceGridData>();
		
		Integer total_pages = 0;
		if(osList.size() > 0){
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
//				Customer customer = os.getServiceOrder().getCustomer();
//				gridData.setName(customer.getName());
//				gridData.setSurname(customer.getSurname());
				gridData.setName(os.getCustomerName());
//				gridData.setType(os.getServiceOrder().getProduct().getType().getName());
				if(os.getType() != null)
					gridData.setType(os.getType().getName());
//				gridData.setBrand(os.getServiceOrder().getProduct().getBrand().getName());
				if(os.getBrand() != null)
					gridData.setBrand(os.getBrand().getName());
//				gridData.setModel(os.getServiceOrder().getProduct().getModel().getName());
				if(os.getModel() != null)
					gridData.setModel(os.getModel().getName());
				gridData.setProblem(os.getProblem());
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
	
	@RequestMapping(value = "/receivedOutsiteService", params = "do=preEdit")
	public String preEdit(@RequestParam(value="outsiteServiceID") String outsiteServiceID, ModelMap model, HttpServletRequest request){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		OutsiteService os = osService.selectByID(outsiteServiceID);
		OutsiteServiceForm form = new OutsiteServiceForm();
		form.setOutsiteServiceID(os.getOutsiteServiceID().toString());
		form.setOutsiteServiceDate(sdfDateTime.format(os.getOutsiteServiceDate()));
		form.setServiceType(os.getServiceType());
		if(os.getServiceOrder() != null){
			form.setServiceOrderID(os.getServiceOrder().getServiceOrderID());
			form.setServiceOrder(os.getServiceOrder());
		}
		form.setCustomerName(os.getCustomerName());
		form.setTel(os.getTel());
		form.setMobileTel(os.getMobileTel());
		if(os.getType() != null){
			form.setTypeID(os.getType().getTypeID());
			form.setTypeName(os.getType().getName());
		}
		if(os.getBrand() != null){
			form.setBrandID(os.getBrand().getBrandID());
			form.setBrandName(os.getBrand().getName());
		}
		if(os.getModel() != null){
			form.setModelID(os.getModel().getModelID());
			form.setModelName(os.getModel().getName());
		}
		form.setSerialNo(os.getSerialNo());
		form.setOutsiteCompanyID(os.getOutsiteCompany().getOutsiteCompanyID());
		form.setTransportCompanyID(os.getTransportCompany().getTransportCompanyID());
		
		form.setAccessories(os.getAccessories());
		form.setProblem(os.getProblem());
		form.setTransportCompanyName(os.getTransportCompany().getName());
		form.setOutsiteCompanyName(os.getOutsiteCompany().getName());
		
		form.setSentDate(sdfDate.format(os.getSentDate()));
		form.setSentTransportNo(os.getSentTransportNo());
		
		form.setCosting("free");
		form.setNetAmount(0.00);
		
//		String[] serviceDesc = {this.messages.getMessage("serviceCost", null, new Locale("th", "TH")),"kkkk"};
//		System.out.println("serviceDesc------>"+serviceDesc);
//		System.out.println("serviceDesc[0]------>"+serviceDesc[0]);
		
//		form.setServiceDesc(serviceDesc);
		
		model.addAttribute("form", form);
//		model.addAttribute("fullAddr", os.getServiceOrder().getCustomer().getAddress()+" "+this.messages.getMessage("subdistrict_abbr", null, new Locale("th", "TH"))+" "+os.getServiceOrder().getCustomer().getSubdistrict().getName()+" "+this.messages.getMessage("district_abbr", null, new Locale("th", "TH"))+" "+os.getServiceOrder().getCustomer().getDistrict().getName()+" "+this.messages.getMessage("province_abbr", null, new Locale("th", "TH"))+" "+os.getServiceOrder().getCustomer().getProvince().getName());
		
		return VIEWNAME_FORM;
	}
	
	@RequestMapping(value = "/receivedOutsiteService", params = "do=save")
	public String doSave(@ModelAttribute("form") OutsiteServiceForm form, HttpServletRequest request, ModelMap model){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		Date now = new Date();
		Employee user = (Employee)request.getSession().getAttribute("UserLogin");
		OutsiteService os = osService.selectByID(form.getOutsiteServiceID());
		try {
			os.setReceivedDate(sdfDate.parse(form.getReceivedDate()));
		} catch (ParseException e) {
			e.printStackTrace();
			os.setSentDate(now);
		}
		os.setReceivedTransportNo(form.getReceivedTransportNo());
//		os.setRepairing(form.getRepairing());
//		os.setCosting(form.getCosting());
		
//		os.setNetAmount(form.getNetAmount());
		os.setStatus(OutsiteService.CLOSE);
		os.setUpdatedBy(user.getEmployeeID());
		os.setUpdatedDate(now);
		
		if(os.getServiceOrder() != null){
			Integer count = osService.countUncloseOutsiteService(os.getServiceOrder().getServiceOrderID(), os.getOutsiteServiceID()); 
			if(count != null && count == 0){
				ServiceOrder so = os.getServiceOrder();
				so.setStatus(ServiceOrder.FIXING);
				so.setUpdatedBy(user.getEmployeeID());
				so.setUpdatedDate(now);
				os.setServiceOrder(so);
			}
		}
		
		/*String[] serviceDescArry = form.getServiceDesc();
		Double[] servicePriceArry = form.getServicePrice();
		
		String[] repairDescArry = form.getRepairDesc();
		Double[] repairPriceArry = form.getRepairPrice();
		
		List<OutsiteServiceDetail> osdList = new ArrayList<OutsiteServiceDetail>();
		
		if(form.getCosting().equals("cost")){
//			for(String serviceDesc:form.getServiceDesc()){
			for(int i=0; i<serviceDescArry.length; i++){
				String serviceDesc = serviceDescArry[i];
//				System.out.println("i = "+i+" serviceDesc = "+serviceDesc);
				if(!serviceDesc.equals("")){
//					System.out.println("insert detail");
					OutsiteServiceDetail osd = new OutsiteServiceDetail();
					osd.setOutsiteService(os);
					osd.setType(OutsiteServiceDetail.TYPE_SERVICE);
					osd.setDesc(serviceDesc);
					Double servicePrice = servicePriceArry[i];
//					System.out.println("servicePrice = "+servicePrice);
					osd.setPrice(servicePrice);
					osd.setCreatedBy(user.getEmployeeID());
					osd.setCreatedDate(now);
					osd.setUpdatedBy(user.getEmployeeID());
					osd.setUpdatedDate(now);
					
					osdList.add(osd);
				}
			}
			
			for(int j=0; j<repairDescArry.length; j++){
				String repairDesc = repairDescArry[j];
				if(!repairDesc.equals("")){
					OutsiteServiceDetail osd = new OutsiteServiceDetail();
					osd.setOutsiteService(os);
					osd.setType(OutsiteServiceDetail.TYPE_REPAIR);
					osd.setDesc(repairDesc);
					Double repairPrice = repairPriceArry[j];
//					System.out.println("repairPrice = "+repairPrice);
					osd.setPrice(repairPrice);
					osd.setCreatedBy(user.getEmployeeID());
					osd.setCreatedDate(now);
					osd.setUpdatedBy(user.getEmployeeID());
					osd.setUpdatedDate(now);
					
					osdList.add(osd);
				}
			}
		}*/
		
//		System.out.println("form.getServiceDesc().length = "+form.getServiceDesc().length);
//		for(String serviceDesc:form.getServiceDesc()){
//			System.out.println("serviceDesc = "+serviceDesc);	
//		}
		
//		System.out.println("form.getServicePrice().length = "+form.getServicePrice().length);
//		for(Double servicePrice:form.getServicePrice()){
//			System.out.println("servicePrice = "+servicePrice);	
//		}
		
		boolean canSave;
		try{
			//canSave = osService.received(os, osdList);
			canSave = osService.received(os);
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			model.addAttribute("form",form);
//			model.addAttribute("fullAddr", os.getServiceOrder().getCustomer().getAddress()+" "+this.messages.getMessage("subdistrict_abbr", null, new Locale("th", "TH"))+" "+os.getServiceOrder().getCustomer().getSubdistrict().getName()+" "+this.messages.getMessage("district_abbr", null, new Locale("th", "TH"))+" "+os.getServiceOrder().getCustomer().getDistrict().getName()+" "+this.messages.getMessage("province_abbr", null, new Locale("th", "TH"))+" "+os.getServiceOrder().getCustomer().getProvince().getName());
			return VIEWNAME_FORM;
		}
		
		if(!canSave){
			model.addAttribute("errMsg", this.messages.getMessage("error.cannotSave", null, new Locale("th", "TH")));
			model.addAttribute("form",form);
//			model.addAttribute("fullAddr", os.getServiceOrder().getCustomer().getAddress()+" "+this.messages.getMessage("subdistrict_abbr", null, new Locale("th", "TH"))+" "+os.getServiceOrder().getCustomer().getSubdistrict().getName()+" "+this.messages.getMessage("district_abbr", null, new Locale("th", "TH"))+" "+os.getServiceOrder().getCustomer().getDistrict().getName()+" "+this.messages.getMessage("province_abbr", null, new Locale("th", "TH"))+" "+os.getServiceOrder().getCustomer().getProvince().getName());
			return VIEWNAME_FORM;
		}
		
		String msg = this.messages.getMessage("msg.updateComplete", null, new Locale("th", "TH"));
		
		model.addAttribute("msg", msg);
		OutsiteServiceSearchForm searchForm = new OutsiteServiceSearchForm();
		model.addAttribute("searchForm", searchForm);
		return VIEWNAME_SEARCH;
	}
}
