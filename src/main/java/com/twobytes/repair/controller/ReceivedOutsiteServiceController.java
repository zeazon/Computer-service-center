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

import com.twobytes.master.service.OutsiteCompanyService;
import com.twobytes.master.service.TransportCompanyService;
import com.twobytes.master.service.TypeService;
import com.twobytes.model.Employee;
import com.twobytes.model.GridResponse;
import com.twobytes.model.OutsiteCompany;
import com.twobytes.model.OutsiteService;
import com.twobytes.model.OutsiteServiceDetail;
import com.twobytes.model.ServiceOrder;
import com.twobytes.model.TransportCompany;
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
	private TransportCompanyService transportCompanyService;
	
	@Autowired
	private OutsiteCompanyService outsiteCompanyService;
	
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
		
		List<OutsiteCompany> ocList = new ArrayList<OutsiteCompany>();
		try{
			ocList = outsiteCompanyService.getAll();
		}catch(Exception e){
			e.printStackTrace();
		}
		List<TransportCompany> tcList = new ArrayList<TransportCompany>();
		try{
			tcList = transportCompanyService.getAll();
		}catch(Exception e){
			e.printStackTrace();
		}
		model.addAttribute("outsiteCompanyList", ocList);
		model.addAttribute("transportCompanyList", tcList);
		return VIEWNAME_SEARCH;
	}
	
	@RequestMapping(value="/searchReceivedOutsiteService")
	public @ResponseBody GridResponse getData(@RequestParam(value="name", required=false) String name, 
												@RequestParam(value="date", required=false) String date, @RequestParam(value="type", required=false) String type, 
												@RequestParam(value="serialNo", required=false) String serialNo, @RequestParam(value="refOutsiteJobID", required=false) String refOutsiteJobID, 
												@RequestParam(value="outsiteCompanyID", required=false) String outsiteCompanyID, @RequestParam(value="transportCompanyID", required=false) String transportCompanyID, 
												@RequestParam("rows") Integer rows, @RequestParam("page") Integer page, @RequestParam("sidx") String sidx, @RequestParam("sord") String sord){
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
			if(null != serialNo){
				serialNo = new String(serialNo.getBytes("iso-8859-1"), "tis620");	
			}
			if(null != refOutsiteJobID){
				refOutsiteJobID = new String(refOutsiteJobID.getBytes("iso-8859-1"), "tis620");	
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		
		List<OutsiteService> osList = osService.selectSentOSByCriteria(name, searchDate, type, serialNo, refOutsiteJobID, outsiteCompanyID, transportCompanyID, rows, page, sidx, sord);
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
				if(os.getSerialNo() != null)
					gridData.setSerialNo(os.getSerialNo());
				if(os.getSentTransportNo() != null){
					gridData.setSentTransportNo(os.getSentTransportNo());
				}
				if(os.getOutsiteCompany() != null){
					gridData.setOutsiteCompanyName(os.getOutsiteCompany().getName());
				}
				if(os.getRefOutsiteJobID() != null && !os.getRefOutsiteJobID().equals("")){
					gridData.setRefOutsiteJobID(os.getRefOutsiteJobID());
				}
				if(os.getTransportCompany() != null){
					gridData.setTransportCompanyName(os.getTransportCompany().getName());
				}
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
		OutsiteServiceForm form = modelToForm(os, new OutsiteServiceForm());
		
		form.setCosting("cost");
		form.setNetAmount(0.00);
		
		form.setServicePrice_1(0.00);
		form.setServicePrice_2(0.00);
		form.setServicePrice_3(0.00);
		form.setServicePrice_4(0.00);
		
		/*form.setRepairPrice_1(0.00);
		form.setRepairPrice_2(0.00);
		form.setRepairPrice_3(0.00);
		form.setRepairPrice_4(0.00);*/
		
		form.setOutsiteRepairPrice(0.00);
		
		model.addAttribute("form", form);
		
		List<TransportCompany> tcList = new ArrayList<TransportCompany>();
		try {
			tcList = transportCompanyService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("transportCompanyList", tcList);
		
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
		TransportCompany tc = new TransportCompany();
		try {
			tc = transportCompanyService.selectByID(form.getReceivedTransportCompanyID());
		} catch (Exception e2) {
			e2.printStackTrace();
			tc = null;
		}
		os.setReceivedTransportCompany(tc);
		os.setReceivedTransportNo(form.getReceivedTransportNo());
		os.setRepairing(form.getRepairing());
		os.setCosting(form.getCosting());
		
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
		
		List<OutsiteServiceDetail> osdList = new ArrayList<OutsiteServiceDetail>();		
		
		if(form.getCosting().equals("cost")){
			if(form.getServiceDesc_1() != "" && (form.getServicePrice_1() != null && form.getServicePrice_1() > 0)){
				OutsiteServiceDetail osd = new OutsiteServiceDetail();
				osd.setOutsiteService(os);
				osd.setType(OutsiteServiceDetail.TYPE_SERVICE);
				osd.setDesc(form.getServiceDesc_1());
				osd.setPrice(form.getServicePrice_1());
				osd.setCreatedBy(user.getEmployeeID());
				osd.setCreatedDate(now);
				osd.setUpdatedBy(user.getEmployeeID());
				osd.setUpdatedDate(now);
				
				osdList.add(osd);
			}
			
			if(form.getServiceDesc_2() != "" && (form.getServicePrice_2() != null && form.getServicePrice_2() > 0)){
				OutsiteServiceDetail osd = new OutsiteServiceDetail();
				osd.setOutsiteService(os);
				osd.setType(OutsiteServiceDetail.TYPE_SERVICE);
				osd.setDesc(form.getServiceDesc_2());
				osd.setPrice(form.getServicePrice_2());
				osd.setCreatedBy(user.getEmployeeID());
				osd.setCreatedDate(now);
				osd.setUpdatedBy(user.getEmployeeID());
				osd.setUpdatedDate(now);
				
				osdList.add(osd);
			}
	
			if(form.getServiceDesc_3() != "" && (form.getServicePrice_3() != null && form.getServicePrice_3() > 0)){
				OutsiteServiceDetail osd = new OutsiteServiceDetail();
				osd.setOutsiteService(os);
				osd.setType(OutsiteServiceDetail.TYPE_SERVICE);
				osd.setDesc(form.getServiceDesc_3());
				osd.setPrice(form.getServicePrice_3());
				osd.setCreatedBy(user.getEmployeeID());
				osd.setCreatedDate(now);
				osd.setUpdatedBy(user.getEmployeeID());
				osd.setUpdatedDate(now);
				
				osdList.add(osd);
			}
			
			if(form.getServiceDesc_4() != "" && (form.getServicePrice_4() != null && form.getServicePrice_4() > 0)){
				OutsiteServiceDetail osd = new OutsiteServiceDetail();
				osd.setOutsiteService(os);
				osd.setType(OutsiteServiceDetail.TYPE_SERVICE);
				osd.setDesc(form.getServiceDesc_4());
				osd.setPrice(form.getServicePrice_4());
				osd.setCreatedBy(user.getEmployeeID());
				osd.setCreatedDate(now);
				osd.setUpdatedBy(user.getEmployeeID());
				osd.setUpdatedDate(now);
				
				osdList.add(osd);
			}
			
			/*if(form.getRepairDesc_1() != "" && (form.getRepairPrice_1() != null && form.getRepairPrice_1() > 0)){
				OutsiteServiceDetail osd = new OutsiteServiceDetail();
				osd.setOutsiteService(os);
				osd.setType(OutsiteServiceDetail.TYPE_REPAIR);
				osd.setDesc(form.getRepairDesc_1());
				osd.setPrice(form.getRepairPrice_1());
				osd.setCreatedBy(user.getEmployeeID());
				osd.setCreatedDate(now);
				osd.setUpdatedBy(user.getEmployeeID());
				osd.setUpdatedDate(now);
				
				osdList.add(osd);
			}
			
			if(form.getRepairDesc_2() != "" && (form.getRepairPrice_2() != null && form.getRepairPrice_2() > 0)){
				OutsiteServiceDetail osd = new OutsiteServiceDetail();
				osd.setOutsiteService(os);
				osd.setType(OutsiteServiceDetail.TYPE_REPAIR);
				osd.setDesc(form.getRepairDesc_2());
				osd.setPrice(form.getRepairPrice_2());
				osd.setCreatedBy(user.getEmployeeID());
				osd.setCreatedDate(now);
				osd.setUpdatedBy(user.getEmployeeID());
				osd.setUpdatedDate(now);
				
				osdList.add(osd);
			}
			
			if(form.getRepairDesc_3() != "" && (form.getRepairPrice_3() != null && form.getRepairPrice_3() > 0)){
				OutsiteServiceDetail osd = new OutsiteServiceDetail();
				osd.setOutsiteService(os);
				osd.setType(OutsiteServiceDetail.TYPE_REPAIR);
				osd.setDesc(form.getRepairDesc_3());
				osd.setPrice(form.getRepairPrice_3());
				osd.setCreatedBy(user.getEmployeeID());
				osd.setCreatedDate(now);
				osd.setUpdatedBy(user.getEmployeeID());
				osd.setUpdatedDate(now);
				
				osdList.add(osd);
			}
			
			if(form.getRepairDesc_4() != "" && (form.getRepairPrice_4() != null && form.getRepairPrice_4() > 0)){
				OutsiteServiceDetail osd = new OutsiteServiceDetail();
				osd.setOutsiteService(os);
				osd.setType(OutsiteServiceDetail.TYPE_REPAIR);
				osd.setDesc(form.getRepairDesc_4());
				osd.setPrice(form.getRepairPrice_4());
				osd.setCreatedBy(user.getEmployeeID());
				osd.setCreatedDate(now);
				osd.setUpdatedBy(user.getEmployeeID());
				osd.setUpdatedDate(now);
				
				osdList.add(osd);
			}*/
			
			if(form.getOutsiteRepairPrice() > 0){
				OutsiteServiceDetail osd = new OutsiteServiceDetail();
				osd.setOutsiteService(os);
				osd.setType(OutsiteServiceDetail.TYPE_REPAIR);
				osd.setDesc(this.messages.getMessage("outsiteFixCost", null, new Locale("th", "TH")));
				osd.setPrice(form.getOutsiteRepairPrice());
				osd.setCreatedBy(user.getEmployeeID());
				osd.setCreatedDate(now);
				osd.setUpdatedBy(user.getEmployeeID());
				osd.setUpdatedDate(now);
				
				osdList.add(osd);
			}
			
			os.setNetAmount(form.getNetAmount());
		}else if(form.getCosting().equals("free")){
			os.setNetAmount(0.00);
		}
				
		boolean canSave = false;
		try{
			canSave = osService.received(os, osdList);
			//canSave = osService.received(os);
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			form = modelToForm(os, form);
			model.addAttribute("form",form);
//			model.addAttribute("fullAddr", os.getServiceOrder().getCustomer().getAddress()+" "+this.messages.getMessage("subdistrict_abbr", null, new Locale("th", "TH"))+" "+os.getServiceOrder().getCustomer().getSubdistrict().getName()+" "+this.messages.getMessage("district_abbr", null, new Locale("th", "TH"))+" "+os.getServiceOrder().getCustomer().getDistrict().getName()+" "+this.messages.getMessage("province_abbr", null, new Locale("th", "TH"))+" "+os.getServiceOrder().getCustomer().getProvince().getName());
			List<TransportCompany> tcList = new ArrayList<TransportCompany>();
			try {
				tcList = transportCompanyService.getAll();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			model.addAttribute("transportCompanyList", tcList);
			return VIEWNAME_FORM;
		}
		
		if(!canSave){
			model.addAttribute("errMsg", this.messages.getMessage("error.cannotSave", null, new Locale("th", "TH")));
			form = modelToForm(os, form);
			model.addAttribute("form",form);
			List<TransportCompany> tcList = new ArrayList<TransportCompany>();
			try {
				tcList = transportCompanyService.getAll();
			} catch (Exception e) {
				e.printStackTrace();
			}
			model.addAttribute("transportCompanyList", tcList);
			return VIEWNAME_FORM;
		}
		
		String msg = this.messages.getMessage("msg.updateComplete", null, new Locale("th", "TH"));
		
		model.addAttribute("msg", msg);
		OutsiteServiceSearchForm searchForm = new OutsiteServiceSearchForm();
		model.addAttribute("searchForm", searchForm);
		return VIEWNAME_SEARCH;
	}
	
	private OutsiteServiceForm modelToForm(OutsiteService os, OutsiteServiceForm form){
		form.setOutsiteServiceID(os.getOutsiteServiceID().toString());
		form.setOutsiteServiceDate(sdfDateTime.format(os.getOutsiteServiceDate()));
		form.setServiceType(os.getServiceType());
		if(os.getRefOutsiteJobID() != null){
			form.setRefOutsiteJobID(os.getRefOutsiteJobID());
		}
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
		return form;
	}
}
