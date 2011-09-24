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
import com.twobytes.model.CustomGenericResponse;
import com.twobytes.model.Customer;
import com.twobytes.model.Employee;
import com.twobytes.model.GridResponse;
import com.twobytes.model.OutsiteCompany;
import com.twobytes.model.OutsiteService;
import com.twobytes.model.ServiceOrder;
import com.twobytes.model.TransportCompany;
import com.twobytes.model.Type;
import com.twobytes.repair.form.OutsiteServiceDocForm;
import com.twobytes.repair.form.OutsiteServiceForm;
import com.twobytes.repair.form.OutsiteServiceGridData;
import com.twobytes.repair.form.OutsiteServiceSearchForm;
import com.twobytes.repair.service.OutsiteServiceService;
import com.twobytes.repair.service.ServiceOrderService;
import com.twobytes.security.form.LoginForm;

@Controller
public class OutsiteServiceController {

	@Autowired
	private OutsiteServiceService osService;
	
	@Autowired
	private TypeService typeService;
	
	@Autowired
	private OutsiteCompanyService outsiteCompanyService;
	
	@Autowired
	private TransportCompanyService transportCompanyService;
	
	@Autowired
	private ServiceOrderService serviceOrderService;
	
	@Autowired
	private MessageSource messages;
	
	public void setMessages(MessageSource messages) {
		this.messages = messages;
	}
	
	private String VIEWNAME_SEARCH = "outsiteService.search";
	private String VIEWNAME_FORM = "outsiteService.form";
	
	private SimpleDateFormat sdfDateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("th", "TH"));
	private SimpleDateFormat sdfDateTimeMin = new SimpleDateFormat("d-MMM-yy HH:mm", new Locale("th", "TH"));
	
	@RequestMapping(value = "/outsiteService")
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
	
	@RequestMapping(value="/searchOutsiteService")
	public @ResponseBody GridResponse getData(@RequestParam(value="name", required=false) String name, @RequestParam(value="surname", required=false) String surname, @RequestParam(value="date", required=false) String date, @RequestParam(value="type", required=false) String type, @RequestParam("rows") Integer rows, @RequestParam("page") Integer page, @RequestParam("sidx") String sidx, @RequestParam("sord") String sord){
		String[] datePart;
		String searchDate = null;
		// Because default Tomcat URI encoding is iso-8859-1 so it must encode back to tis620
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
		
//		System.out.println("searchDate = "+searchDate);
		List<OutsiteService> osList = osService.selectByCriteria(name, surname, searchDate, type, rows, page, sidx, sord);
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
//				System.out.println("so.getServiceOrderDate() = "+so.getServiceOrderDate());
//				System.out.println("sdf.format(so.getServiceOrderDate()) = "+sdf.format(so.getServiceOrderDate()));
//				System.out.println("sdfDateTime.format(so.getServiceOrderDate()) = "+sdfDateTime.format(so.getServiceOrderDate()));
//				System.out.println("sdfDateTime2.format(so.getServiceOrderDate()) = "+sdfDateTime2.format(so.getServiceOrderDate()));
//				System.out.println("sdfDateTimeNoLocale.format(so.getServiceOrderDate()) = "+sdfDateTimeNoLocale.format(so.getServiceOrderDate()));
//				System.out.println("sdfDateTimeUSLocale.format(so.getServiceOrderDate()) = "+sdfDateTimeUSLocale.format(so.getServiceOrderDate()));
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
	
	@RequestMapping(value = "/outsiteService", params = "do=preAdd")
	public String preAdd(ModelMap model, HttpServletRequest request) throws ParseException{
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		OutsiteServiceForm form = new OutsiteServiceForm();
		Date now = new Date();
		form.setOutsiteServiceDate(sdfDateTime.format(now));
		form.setServiceType("warranty");
//		form.setServiceType(1);
//		List<Type> typeList = typeService.getAll();
//		Type type = typeList.get(0);
//		form.setTypeID(type.getTypeID());
//		List<Brand> brandList = new ArrayList<Brand>();
//		if(type.getBrands().size()>0){
//			brandList = type.getBrands();
//			Brand brand = brandList.get(0);
//			form.setBrandID(brand.getBrandID());
//		}else{
//			Brand blankBrand = new Brand();
//			blankBrand.setBrandID(null);
//			blankBrand.setName("");
//			brandList.add(blankBrand);
//		}

		model.addAttribute("form", form);
//		model.addAttribute("typeList", typeList);
//		model.addAttribute("brandList", brandList);
		
		List<OutsiteCompany> ocList = new ArrayList<OutsiteCompany>();
		try{
			ocList = outsiteCompanyService.getAll();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(ocList.size() > 0){
			OutsiteCompany oc = new OutsiteCompany();
			oc = ocList.get(0);
			form.setOutsiteCompanyID(oc.getOutsiteCompanyID());
		}else{
			form.setOutsiteCompanyID(0);
		}
		
		List<TransportCompany> tcList = new ArrayList<TransportCompany>(); 
		try{	
			tcList = transportCompanyService.getAll();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(tcList.size() > 0){
			TransportCompany tc = tcList.get(0);
			form.setTransportCompanyID(tc.getTransportCompanyID());
		}else{
			form.setTransportCompanyID(0);
		}
		
		model.addAttribute("outsiteCompanyList", ocList);
		model.addAttribute("transportCompanyList", tcList);
		
		OutsiteServiceDocForm docForm =  new OutsiteServiceDocForm();
		model.addAttribute("docForm", docForm);
		return VIEWNAME_FORM;
	}
	
	@RequestMapping(value = "/outsiteService", params = "do=save")
	public String doSave(@ModelAttribute("form") OutsiteServiceForm form, HttpServletRequest request, ModelMap model){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		Date now = new Date();
		Employee user = (Employee)request.getSession().getAttribute("UserLogin");
		OutsiteService os = new OutsiteService();
		String msg = "";
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
//		if(null != form.getOutsiteServiceID()){
		if(!form.getOutsiteServiceID().equals("")){
			// update
			os = osService.selectByID(Integer.parseInt(form.getOutsiteServiceID()));
			if(os.getOutsiteCompany().getOutsiteCompanyID() != form.getOutsiteCompanyID()){
				OutsiteCompany oc = new OutsiteCompany();
				try{
					oc = outsiteCompanyService.selectByID(form.getOutsiteCompanyID());
				}catch(Exception e){
					e.printStackTrace();
					model.addAttribute("errMsg", e.getMessage());
					
					model.addAttribute("outsiteCompanyList", ocList);
					model.addAttribute("transportCompanyList", tcList);

					OutsiteServiceDocForm docForm = new OutsiteServiceDocForm();
					model.addAttribute("docForm", docForm);
					return VIEWNAME_FORM;
				}
				os.setOutsiteCompany(oc);
			}
			if(os.getTransportCompany().getTransportCompanyID() != form.getTransportCompanyID()){
				TransportCompany tc = new TransportCompany();
				try{
					tc = transportCompanyService.selectByID(form.getTransportCompanyID());
				}catch(Exception e){
					e.printStackTrace();
					model.addAttribute("errMsg", e.getMessage());
					
					model.addAttribute("outsiteCompanyList", ocList);
					model.addAttribute("transportCompanyList", tcList);

					OutsiteServiceDocForm docForm = new OutsiteServiceDocForm();
					model.addAttribute("docForm", docForm);
					return VIEWNAME_FORM;
				}
				os.setTransportCompany(tc);
			}
			msg = this.messages.getMessage("msg.updateComplete", null, new Locale("th", "TH"));
		}else{
			// add
//			String serviceOrderID = docRunningUtil.genDoc("SO");
			try {
				os.setOutsiteServiceDate(sdfDateTime.parse(form.getOutsiteServiceDate()));
//				so.setServiceOrderDate(sdfDateTimeUSLocale.parse(form.getServiceOrderDate()));
//				System.out.println("form.getServiceOrderDate() = "+form.getServiceOrderDate());
//				System.out.println("sdfDateTime.parse(form.getServiceOrderDate()) = "+sdfDateTime.parse(form.getServiceOrderDate()));
//				System.out.println("so.getServiceOrderDate = "+so.getServiceOrderDate());
			} catch (ParseException e) {
				e.printStackTrace();
				os.setOutsiteServiceDate(new Date());
			}
			OutsiteCompany oc = new OutsiteCompany();
			try{
				oc = outsiteCompanyService.selectByID(form.getOutsiteCompanyID());
			}catch(Exception e){
				e.printStackTrace();
				model.addAttribute("errMsg", e.getMessage());
				
				model.addAttribute("outsiteCompanyList", ocList);
				model.addAttribute("transportCompanyList", tcList);

				OutsiteServiceDocForm docForm = new OutsiteServiceDocForm();
				model.addAttribute("docForm", docForm);
				return VIEWNAME_FORM;
			}
			os.setOutsiteCompany(oc);
			TransportCompany tc = new TransportCompany();
			try{
				tc = transportCompanyService.selectByID(form.getTransportCompanyID());
			}catch(Exception e){
				e.printStackTrace();
				model.addAttribute("errMsg", e.getMessage());
				
				model.addAttribute("outsiteCompanyList", ocList);
				model.addAttribute("transportCompanyList", tcList);

				OutsiteServiceDocForm docForm = new OutsiteServiceDocForm();
				model.addAttribute("docForm", docForm);
				return VIEWNAME_FORM;
			}
			os.setTransportCompany(tc);
			os.setEmpOpen(user);
			os.setCreatedBy(user.getEmployeeID());
			os.setCreatedDate(now);
			os.setStatus(OutsiteService.NEW);
			msg = this.messages.getMessage("msg.addComplete", null, new Locale("th", "TH"));
		}
		ServiceOrder so = serviceOrderService.selectByID(form.getServiceOrderID());
		os.setServiceOrder(so);
		os.setServiceType(form.getServiceType());
		form.setServiceOrder(so);
		os.setAccessories(form.getAccessories());
	
		os.setUpdatedBy(user.getEmployeeID());
		os.setUpdatedDate(now);
		
		model.addAttribute("outsiteCompanyList", ocList);
		model.addAttribute("transportCompanyList", tcList);
		boolean canSave;
		try{
			canSave = osService.save(os);
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			
			model.addAttribute("outsiteCompanyList", ocList);
			model.addAttribute("transportCompanyList", tcList);

			OutsiteServiceDocForm docForm = new OutsiteServiceDocForm();
			model.addAttribute("docForm", docForm);
			return VIEWNAME_FORM;
		}
		if(!canSave){
			model.addAttribute("errMsg", this.messages.getMessage("error.cannotSave", null, new Locale("th", "TH")));
			
			model.addAttribute("outsiteCompanyList", ocList);
			model.addAttribute("transportCompanyList", tcList);
			
			OutsiteServiceDocForm docForm = new OutsiteServiceDocForm();
			model.addAttribute("docForm", docForm);
			
			return VIEWNAME_FORM;
		}
		model.addAttribute("msg", msg);
		
		model.addAttribute("fullAddr", os.getServiceOrder().getCustomer().getAddress()+" "+this.messages.getMessage("subdistrict_abbr", null, new Locale("th", "TH"))+" "+os.getServiceOrder().getCustomer().getSubdistrict().getName()+" "+this.messages.getMessage("district_abbr", null, new Locale("th", "TH"))+" "+os.getServiceOrder().getCustomer().getDistrict().getName()+" "+this.messages.getMessage("province_abbr", null, new Locale("th", "TH"))+" "+os.getServiceOrder().getCustomer().getProvince().getName());

		if(ocList.size() > 0){
			OutsiteCompany oc = new OutsiteCompany();
			oc = ocList.get(0);
			form.setOutsiteCompanyID(oc.getOutsiteCompanyID());
		}else{
			form.setOutsiteCompanyID(0);
		}
		
		if(tcList.size() > 0){
			TransportCompany tc = tcList.get(0);
			form.setTransportCompanyID(tc.getTransportCompanyID());
		}else{
			form.setTransportCompanyID(0);
		}
		
		model.addAttribute("outsiteCompanyList", ocList);
		model.addAttribute("transportCompanyList", tcList);		
		
		OutsiteServiceDocForm docForm =  new OutsiteServiceDocForm();
		docForm.setOutsiteCompany(os.getOutsiteCompany().getName());
		docForm.setOutsiteServiceDate(sdfDateTimeMin.format(os.getOutsiteServiceDate()));
//		docForm.setCustomerName(os.getServiceOrder().getCustomer().getName()+" "+os.getServiceOrder().getCustomer().getSurname());
		docForm.setTel(os.getServiceOrder().getCustomer().getTel());
		docForm.setMobileTel(os.getServiceOrder().getCustomer().getMobileTel());
		docForm.setType(os.getServiceOrder().getProduct().getType().getName());
		docForm.setBrandModel(os.getServiceOrder().getProduct().getBrand().getName()+" "+os.getServiceOrder().getProduct().getModel());
		docForm.setSerialNo(os.getServiceOrder().getProduct().getSerialNo());
		docForm.setAccessories(os.getAccessories());
		docForm.setTransportCompany(os.getTransportCompany().getName());
		docForm.setServiceOrderID(os.getServiceOrder().getServiceOrderID());
		docForm.setEmpOpen(os.getEmpOpen().getName()+" "+os.getEmpOpen().getSurname());
		model.addAttribute("docForm", docForm);
		
		model.addAttribute("action", "print");
		return VIEWNAME_FORM;
	}
	
	@RequestMapping(value = "/outsiteService", params = "do=preEdit")
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
		
		List<OutsiteCompany> ocList = new ArrayList<OutsiteCompany>(); 
		try {
			ocList = outsiteCompanyService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<TransportCompany> tcList = new ArrayList<TransportCompany>();
		try {
			tcList = transportCompanyService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("outsiteCompanyList", ocList);
		model.addAttribute("transportCompanyList", tcList);

		model.addAttribute("form", form);
		model.addAttribute("fullAddr", os.getServiceOrder().getCustomer().getAddress()+" "+this.messages.getMessage("subdistrict_abbr", null, new Locale("th", "TH"))+" "+os.getServiceOrder().getCustomer().getSubdistrict().getName()+" "+this.messages.getMessage("district_abbr", null, new Locale("th", "TH"))+" "+os.getServiceOrder().getCustomer().getDistrict().getName()+" "+this.messages.getMessage("province_abbr", null, new Locale("th", "TH"))+" "+os.getServiceOrder().getCustomer().getProvince().getName());

		OutsiteServiceDocForm docForm =  new OutsiteServiceDocForm();
		docForm.setOutsiteCompany(os.getOutsiteCompany().getName());
		docForm.setOutsiteServiceDate(sdfDateTimeMin.format(os.getOutsiteServiceDate()));
//		docForm.setCustomerName(os.getServiceOrder().getCustomer().getName()+" "+os.getServiceOrder().getCustomer().getSurname());
		docForm.setTel(os.getServiceOrder().getCustomer().getTel());
		docForm.setMobileTel(os.getServiceOrder().getCustomer().getMobileTel());
		docForm.setType(os.getServiceOrder().getProduct().getType().getName());
		docForm.setBrandModel(os.getServiceOrder().getProduct().getBrand().getName()+" "+os.getServiceOrder().getProduct().getModel());
		docForm.setSerialNo(os.getServiceOrder().getProduct().getSerialNo());
		docForm.setAccessories(os.getAccessories());
		docForm.setTransportCompany(os.getTransportCompany().getName());
		docForm.setServiceOrderID(os.getServiceOrder().getServiceOrderID());
		docForm.setEmpOpen(os.getEmpOpen().getName()+" "+os.getEmpOpen().getSurname());
		model.addAttribute("docForm", docForm);
		
		return VIEWNAME_FORM;
	}
	
	@RequestMapping(value = "/outsiteService", params = "do=delete")
	public @ResponseBody CustomGenericResponse delete(HttpServletRequest request, @RequestParam String outsiteServiceID){
		Employee user = (Employee)request.getSession().getAttribute("UserLogin");
		CustomGenericResponse response = new CustomGenericResponse();
		response.setSuccess(true);
		try{
			osService.delete(Integer.parseInt(outsiteServiceID), user.getEmployeeID());
			response.setMessage(this.messages.getMessage("msg.deleteSuccess", null, new Locale("th", "TH")));
		}catch(Exception e){
			response.setSuccess(false);
			response.setMessage(this.messages.getMessage("error.cannotDelete", null, new Locale("th", "TH")));
		}
		return response;
	}
	
	@RequestMapping(value = "/outsiteService", params = "do=print")
	public String doPrint(@ModelAttribute OutsiteServiceDocForm docForm, HttpServletRequest request, ModelMap model){
		
		List<OutsiteServiceDocForm> resultList = new ArrayList<OutsiteServiceDocForm>();
		
		resultList.add(docForm);
		
		model.addAttribute("serviceOrderResultList", resultList);
		return "outsiteServiceDoc";
	}

}