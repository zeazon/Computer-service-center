package com.twobytes.repair.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twobytes.master.service.BrandService;
import com.twobytes.master.service.ModelService;
import com.twobytes.master.service.OutsiteCompanyService;
import com.twobytes.master.service.TransportCompanyService;
import com.twobytes.master.service.TypeService;
import com.twobytes.model.Brand;
import com.twobytes.model.CustomGenericResponse;
import com.twobytes.model.Employee;
import com.twobytes.model.GridResponse;
import com.twobytes.model.Model;
import com.twobytes.model.OutsiteCompany;
import com.twobytes.model.OutsiteService;
import com.twobytes.model.OutsiteServiceDetail;
import com.twobytes.model.ServiceOrder;
import com.twobytes.model.TransportCompany;
import com.twobytes.model.Type;
import com.twobytes.repair.form.OutsiteServiceDocForm;
import com.twobytes.repair.form.OutsiteServiceForm;
import com.twobytes.repair.form.OutsiteServiceGridData;
import com.twobytes.repair.form.OutsiteServiceSearchForm;
import com.twobytes.repair.service.OutsiteServiceDetailService;
import com.twobytes.repair.service.OutsiteServiceService;
import com.twobytes.repair.service.ServiceOrderService;
import com.twobytes.security.form.LoginForm;

@Controller
public class OutsiteServiceController {

	@Autowired
	private OutsiteServiceService osService;
	
	@Autowired
	private OutsiteServiceDetailService osdService;
	
	@Autowired
	private TypeService typeService;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private ModelService modelService;
	
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
	
	private SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy", new Locale("US"));
	private SimpleDateFormat sdfDateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("US"));
	private SimpleDateFormat sdfDateTimeMin = new SimpleDateFormat("d-MMM-yy HH:mm", new Locale("US"));
	
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
	@SuppressWarnings("unchecked")
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
				searchDate = datePart[2]+"-"+datePart[1]+"-"+datePart[0];
			}
			if(null != type){
				type = new String(type.getBytes("iso-8859-1"), "tis620");	
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		
		Map<String, Object> ret = new HashMap<String, Object>();
		ret = osService.selectByCriteria(name, surname, searchDate, type, rows, page, sidx, sord);
		
		List<OutsiteService> osList = (List<OutsiteService>) ret.get("list");
		GridResponse response = new GridResponse();
		
		List<OutsiteServiceGridData> rowsList = new ArrayList<OutsiteServiceGridData>();
		
		Integer total_pages = 0;
		if(osList.size() > 0){
			for(OutsiteService os:osList){
				OutsiteServiceGridData gridData = new OutsiteServiceGridData();
				gridData.setOutsiteServiceID(os.getOutsiteServiceID().toString());
				gridData.setOutsiteServiceDate(sdfDateTime.format(os.getOutsiteServiceDate()));
				gridData.setName(os.getCustomerName());
				if(os.getType() != null)
					gridData.setType(os.getType().getName());
				if(os.getBrand() != null)
					gridData.setBrand(os.getBrand().getName());
				if(os.getModel() != null)
					gridData.setModel(os.getModel().getName());
				gridData.setProblem(os.getProblem());
				if(os.getSentDate() != null){
					gridData.setSentDate(sdfDateTime.format(os.getSentDate()));
				}
				if(os.getSentTransportNo() != null){
					gridData.setSentTransportNo(os.getSentTransportNo());
				}
				gridData.setStatus(os.getStatus());
				if(os.getServiceOrder() != null){
					gridData.setServiceOrderID(os.getServiceOrder().getServiceOrderID());
				}
				rowsList.add(gridData);
			}
			total_pages = new Double(Math.ceil(((double)(Long) ret.get("maxRows")/(double)rows))).intValue();
		}
		
		if (page > total_pages) page=total_pages;
		response.setPage(page.toString());
		response.setRecords(((Long) ret.get("maxRows")).toString());
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
		List<Type> typeList = new ArrayList<Type>();
		try {
			typeList = typeService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Type type = typeList.get(0);
		form.setTypeID(type.getTypeID());
		List<Brand> brandList = new ArrayList<Brand>();
		if (type.getBrands().size() > 0) {
			brandList = type.getBrands();
			Brand brand = brandList.get(0);
			form.setBrandID(brand.getBrandID());
		} else {
			Brand blankBrand = new Brand();
			blankBrand.setBrandID(null);
			blankBrand.setName("");
			brandList.add(blankBrand);
		}

		List<Model> modelList = new ArrayList<Model>();
		if (type.getBrands().size() > 0) {
			brandList = type.getBrands();
			form.setBrandID(form.getBrandID());
			
			Brand brand = brandList.get(0);
			modelList = modelService.getModelByTypeAndBrand(type.getTypeID(), brand.getBrandID());
		} else {
			Brand blankBrand = new Brand();
			blankBrand.setBrandID(null);
			blankBrand.setName("");
			brandList.add(blankBrand);
		}

		
		
		model.addAttribute("form", form);
		model.addAttribute("typeList", typeList);
		model.addAttribute("brandList", brandList);
		model.addAttribute("modelList", modelList);
		
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
			os = osService.selectByID(form.getOutsiteServiceID());
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
			
			if(os.getStatus().equals(OutsiteService.SENT) || os.getStatus().equals(OutsiteService.RECEIVED)){
				try {
					os.setSentDate(sdfDate.parse(form.getSentDate()));
				} catch (ParseException e) {
					e.printStackTrace();
					os.setSentDate(null);
				}
				os.setSentTransportNo(form.getSentTransportNo());
			}
			
			if(os.getStatus().equals(OutsiteService.RECEIVED)){
				try {
					os.setReceivedDate(sdfDate.parse(form.getReceivedDate()));
				} catch (ParseException e) {
					e.printStackTrace();
					os.setSentDate(now);
				}
				os.setReceivedTransportNo(form.getReceivedTransportNo());
			}
			
			msg = this.messages.getMessage("msg.updateComplete", null, new Locale("th", "TH"));
		}else{
			// add
//			String serviceOrderID = docRunningUtil.genDoc("SO");
			try {
				os.setOutsiteServiceDate(sdfDateTime.parse(form.getOutsiteServiceDate()));
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

				List<Type> typeList = new ArrayList<Type>();
				try {
					typeList = typeService.getAll();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				Type type = typeList.get(0);
				
				List<Model> modelList = new ArrayList<Model>();
				List<Brand> brandList = new ArrayList<Brand>();
				if (type.getBrands().size() > 0) {
					brandList = type.getBrands();
					form.setBrandID(form.getBrandID());
					
					Brand brand = brandList.get(0);
					modelList = modelService.getModelByTypeAndBrand(type.getTypeID(), brand.getBrandID());
				} else {
					Brand blankBrand = new Brand();
					blankBrand.setBrandID(null);
					blankBrand.setName("");
					brandList.add(blankBrand);
				}

				model.addAttribute("typeList", typeList);
				model.addAttribute("brandList", brandList);
				model.addAttribute("modelList", modelList);
				
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

				List<Type> typeList = new ArrayList<Type>();
				try {
					typeList = typeService.getAll();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				Type type = typeList.get(0);
				
				List<Model> modelList = new ArrayList<Model>();
				List<Brand> brandList = new ArrayList<Brand>();
				if (type.getBrands().size() > 0) {
					brandList = type.getBrands();
					form.setBrandID(form.getBrandID());
					
					Brand brand = brandList.get(0);
					modelList = modelService.getModelByTypeAndBrand(type.getTypeID(), brand.getBrandID());
				} else {
					Brand blankBrand = new Brand();
					blankBrand.setBrandID(null);
					blankBrand.setName("");
					brandList.add(blankBrand);
				}

				model.addAttribute("typeList", typeList);
				model.addAttribute("brandList", brandList);
				model.addAttribute("modelList", modelList);
				
				OutsiteServiceDocForm docForm = new OutsiteServiceDocForm();
				model.addAttribute("docForm", docForm);
				return VIEWNAME_FORM;
			}
			os.setCustomerName(form.getCustomerName());
			os.setTel(form.getTel());
			os.setMobileTel(form.getMobileTel());
			Type type = new Type();
			try {
				type = typeService.selectByID(form.getTypeID());
			} catch (Exception e) {
				e.printStackTrace();
			}
			os.setType(type);
			Brand brand = new Brand();
			try {
				brand = brandService.selectByID(form.getBrandID());
			} catch (Exception e) {
				e.printStackTrace();
			}
			os.setBrand(brand);
			Model modelObj = new Model();
			modelObj = modelService.selectByID(form.getModelID());
			os.setModel(modelObj);
			os.setSerialNo(form.getSerialNo());
			os.setTransportCompany(tc);
			os.setEmpOpen(user);
			os.setCreatedBy(user.getEmployeeID());
			os.setCreatedDate(now);
			os.setStatus(OutsiteService.NEW);
			msg = this.messages.getMessage("msg.addComplete", null, new Locale("th", "TH"));
		}
		os.setProblem(form.getProblem());
		
		ServiceOrder so = serviceOrderService.selectByID(form.getServiceOrderID());
		os.setServiceOrder(so);
		os.setServiceType(form.getServiceType());
		form.setServiceOrder(so);
		os.setAccessories(form.getAccessories());
	
		os.setUpdatedBy(user.getEmployeeID());
		os.setUpdatedDate(now);
		
		model.addAttribute("outsiteCompanyList", ocList);
		model.addAttribute("transportCompanyList", tcList);
		String result;
		try{
			result = osService.save(os);
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			
			model.addAttribute("outsiteCompanyList", ocList);
			model.addAttribute("transportCompanyList", tcList);

			List<Type> typeList = new ArrayList<Type>();
			try {
				typeList = typeService.getAll();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			Type type = typeList.get(0);
			
			List<Model> modelList = new ArrayList<Model>();
			List<Brand> brandList = new ArrayList<Brand>();
			if (type.getBrands().size() > 0) {
				brandList = type.getBrands();
				form.setBrandID(form.getBrandID());
				
				Brand brand = brandList.get(0);
				modelList = modelService.getModelByTypeAndBrand(type.getTypeID(), brand.getBrandID());
			} else {
				Brand blankBrand = new Brand();
				blankBrand.setBrandID(null);
				blankBrand.setName("");
				brandList.add(blankBrand);
			}

			model.addAttribute("typeList", typeList);
			model.addAttribute("brandList", brandList);
			model.addAttribute("modelList", modelList);
			
			OutsiteServiceDocForm docForm = new OutsiteServiceDocForm();
			model.addAttribute("docForm", docForm);
			return VIEWNAME_FORM;
		}
		if(result.equals("false")){
			model.addAttribute("errMsg", this.messages.getMessage("error.cannotSave", null, new Locale("th", "TH")));
			
			model.addAttribute("outsiteCompanyList", ocList);
			model.addAttribute("transportCompanyList", tcList);
			
			OutsiteServiceDocForm docForm = new OutsiteServiceDocForm();
			model.addAttribute("docForm", docForm);
			
			return VIEWNAME_FORM;
		}
		os.setOutsiteServiceID(result);
		form.setOutsiteServiceID(result);
		model.addAttribute("msg", msg);
		
//		model.addAttribute("fullAddr", os.getServiceOrder().getCustomer().getAddress()+" "+this.messages.getMessage("subdistrict_abbr", null, new Locale("th", "TH"))+" "+os.getServiceOrder().getCustomer().getSubdistrict().getName()+" "+this.messages.getMessage("district_abbr", null, new Locale("th", "TH"))+" "+os.getServiceOrder().getCustomer().getDistrict().getName()+" "+this.messages.getMessage("province_abbr", null, new Locale("th", "TH"))+" "+os.getServiceOrder().getCustomer().getProvince().getName());

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
		
		List<Type> typeList = new ArrayList<Type>();
		try {
			typeList = typeService.getAll();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Type type = os.getType();
		
		List<Model> modelList = new ArrayList<Model>();
		List<Brand> brandList = new ArrayList<Brand>();
		if (type.getBrands().size() > 0) {
			brandList = type.getBrands();
			form.setBrandID(form.getBrandID());
			
			Brand brand = os.getBrand();
			modelList = modelService.getModelByTypeAndBrand(type.getTypeID(), brand.getBrandID());
		} else {
			Brand blankBrand = new Brand();
			blankBrand.setBrandID(null);
			blankBrand.setName("");
			brandList.add(blankBrand);
		}

		model.addAttribute("typeList", typeList);
		model.addAttribute("brandList", brandList);
		model.addAttribute("modelList", modelList);
		
		OutsiteServiceDocForm docForm =  new OutsiteServiceDocForm();
		docForm.setOutsiteCompany(os.getOutsiteCompany().getName());
		docForm.setOutsiteServiceDate(sdfDateTimeMin.format(os.getOutsiteServiceDate()));
//		docForm.setCustomerName(os.getServiceOrder().getCustomer().getName()+" "+os.getServiceOrder().getCustomer().getSurname());
//		docForm.setCustomerName(os.getServiceOrder().getCustomer().getName());
		docForm.setCustomerName(os.getCustomerName());
		docForm.setTel(os.getTel());
		docForm.setMobileTel(os.getMobileTel());
		if(os.getType() != null){
			docForm.setType(os.getType().getName());
		}else{
			docForm.setType("-");
		}
		if(os.getBrand() != null && os.getModel() != null){
			docForm.setBrandModel(os.getBrand().getName()+" "+os.getModel().getName());
		}else if(os.getBrand() != null && os.getModel() == null){
			docForm.setBrandModel(os.getBrand().getName());
		}else if(os.getBrand() == null && os.getModel() != null){
			docForm.setBrandModel(os.getModel().getName());
		}else if(os.getBrand() == null && os.getModel() == null){
			docForm.setBrandModel("-");
		}
//		docForm.setBrandModel(os.getServiceOrder().getProduct().getBrand().getName()+" "+os.getServiceOrder().getProduct().getModel().getName());
		docForm.setSerialNo(os.getSerialNo());
		docForm.setAccessories(os.getAccessories());
		docForm.setProblem(os.getProblem());
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
		if(os.getType() != null)
			form.setTypeID(os.getType().getTypeID());
		if(os.getBrand() != null)
			form.setBrandID(os.getBrand().getBrandID());
		if(os.getModel() != null)
			form.setModelID(os.getModel().getModelID());
		form.setSerialNo(os.getSerialNo());
		form.setOutsiteCompanyID(os.getOutsiteCompany().getOutsiteCompanyID());
		form.setTransportCompanyID(os.getTransportCompany().getTransportCompanyID());
		
		form.setAccessories(os.getAccessories());
		form.setProblem(os.getProblem());
		
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
		
		if(os.getSentDate() != null){
			form.setSentDate(sdfDate.format(os.getSentDate()));
		}
		form.setSentTransportNo(os.getSentTransportNo());
		if(os.getReceivedDate() != null){
			form.setReceivedDate(sdfDate.format(os.getReceivedDate()));
		}
		if(os.getReceivedTransportCompany() != null){
			form.setReceivedTransportCompanyID(os.getReceivedTransportCompany().getTransportCompanyID());
		}
		form.setReceivedTransportNo(os.getReceivedTransportNo());
		form.setRepairing(os.getRepairing());
		form.setCosting(os.getCosting());
		form.setNetAmount(os.getNetAmount());
		
		List<OutsiteServiceDetail> osdList = osdService.getByOutsiteService(os.getOutsiteServiceID());
		model.addAttribute("osdList", osdList);
		
		form.setStatus(os.getStatus());
		
		model.addAttribute("outsiteCompanyList", ocList);
		model.addAttribute("transportCompanyList", tcList);

		List<Type> typeList = new ArrayList<Type>();
		try {
			typeList = typeService.getAll();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Type type = os.getType();
		
		List<Model> modelList = new ArrayList<Model>();
		List<Brand> brandList = new ArrayList<Brand>();
		if (type.getBrands().size() > 0) {
			brandList = type.getBrands();
			if(os.getBrand() != null){
				form.setBrandID(os.getBrand().getBrandID());
				
				modelList = modelService.getModelByTypeAndBrand(type.getTypeID(), os.getBrand().getBrandID());
			}else{
				Brand brand = brandList.get(0);
				modelList = modelService.getModelByTypeAndBrand(type.getTypeID(), brand.getBrandID());
			}
		} else {
			Brand blankBrand = new Brand();
			blankBrand.setBrandID(null);
			blankBrand.setName("");
			brandList.add(blankBrand);
		}

		model.addAttribute("typeList", typeList);
		model.addAttribute("brandList", brandList);
		model.addAttribute("modelList", modelList);
		
		model.addAttribute("form", form);
//		model.addAttribute("fullAddr", os.getServiceOrder().getCustomer().getAddress()+" "+this.messages.getMessage("subdistrict_abbr", null, new Locale("th", "TH"))+" "+os.getServiceOrder().getCustomer().getSubdistrict().getName()+" "+this.messages.getMessage("district_abbr", null, new Locale("th", "TH"))+" "+os.getServiceOrder().getCustomer().getDistrict().getName()+" "+this.messages.getMessage("province_abbr", null, new Locale("th", "TH"))+" "+os.getServiceOrder().getCustomer().getProvince().getName());

		OutsiteServiceDocForm docForm =  new OutsiteServiceDocForm();
		docForm.setOutsiteCompany(os.getOutsiteCompany().getName());
		docForm.setOutsiteServiceDate(sdfDateTimeMin.format(os.getOutsiteServiceDate()));
//		docForm.setCustomerName(os.getServiceOrder().getCustomer().getName()+" "+os.getServiceOrder().getCustomer().getSurname());
//		docForm.setCustomerName(os.getServiceOrder().getCustomer().getName());
		docForm.setCustomerName(os.getCustomerName());
		docForm.setTel(os.getTel());
		docForm.setMobileTel(os.getMobileTel());
		if(os.getType() != null){
			docForm.setType(os.getType().getName());
		}else{
			docForm.setType("-");
		}
		if(os.getBrand() != null && os.getModel() != null){
			docForm.setBrandModel(os.getBrand().getName()+" "+os.getModel().getName());
		}else if(os.getBrand() != null && os.getModel() == null){
			docForm.setBrandModel(os.getBrand().getName());
		}else if(os.getBrand() == null && os.getModel() != null){
			docForm.setBrandModel(os.getModel().getName());
		}else if(os.getBrand() == null && os.getModel() == null){
			docForm.setBrandModel("-");
		}
		docForm.setSerialNo(os.getSerialNo());
		docForm.setAccessories(os.getAccessories());
		docForm.setProblem(os.getProblem());
		docForm.setTransportCompany(os.getTransportCompany().getName());
		if(os.getServiceOrder() != null){
			docForm.setServiceOrderID(os.getServiceOrder().getServiceOrderID());
		}else{
			docForm.setServiceOrderID("-");
		}
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
			osService.delete(outsiteServiceID, user.getEmployeeID());
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
		
		model.addAttribute("outsiteServiceResultList", resultList);
		return "outsiteServiceDoc";
	}

}