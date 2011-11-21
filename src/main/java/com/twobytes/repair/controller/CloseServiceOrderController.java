package com.twobytes.repair.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.twobytes.master.service.ModelService;
import com.twobytes.master.service.TypeService;
import com.twobytes.model.Brand;
import com.twobytes.model.Customer;
import com.twobytes.model.GridResponse;
import com.twobytes.model.Model;
import com.twobytes.model.ServiceOrder;
import com.twobytes.model.Type;
import com.twobytes.repair.form.ServiceOrderForm;
import com.twobytes.repair.form.ServiceOrderGridData;
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
	private ModelService modelService;
	
	@Autowired
	private MessageSource messages;
	
	public void setMessages(MessageSource messages) {
		this.messages = messages;
	}
	
	private String VIEWNAME_SEARCH = "closeServiceOrder.search";
	private String VIEWNAME_FORM = "closeServiceOrder.form";
	
	private SimpleDateFormat sdfDateTime = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm", new Locale ( "US" ));
	
	@RequestMapping(value = "/closeServiceOrder")
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
	
	
	@RequestMapping(value="/searchCloseServiceOrder")
	public @ResponseBody GridResponse getData(@RequestParam(value="name", required=false) String name, @RequestParam(value="startDate", required=false) String startDate, @RequestParam(value="endDate", required=false) String endDate, @RequestParam(value="type", required=false) String type, @RequestParam(value="serialNo", required=false) String serialNo, @RequestParam("rows") Integer rows, @RequestParam("page") Integer page, @RequestParam("sidx") String sidx, @RequestParam("sord") String sord){
		// Because default Tomcat URI encoding is iso-8859-1 so it must encode back to tis620
		String[] datePart;
		String searchStartDate = null;
		String searchEndDate = null;
		try{
			if(null != name){
				name = new String(name.getBytes("iso-8859-1"), "tis620");	
			}
			if(null != startDate && !startDate.equals("")){
				startDate = new String(startDate.getBytes("iso-8859-1"), "tis620");
				datePart = startDate.split("/");
				// Change year to Christ year
//				Integer year = Integer.parseInt(datePart[2]);				
//				year = year - 543;
				searchStartDate = datePart[2]+"-"+datePart[1]+"-"+datePart[0];
			}
			if (null != endDate && !endDate.equals("")) {
				endDate = new String(endDate.getBytes("iso-8859-1"), "tis620");
				datePart = endDate.split("/");
				searchEndDate = datePart[2] + "-" + datePart[1] + "-"
						+ datePart[0];
			}
			if(null != type){
				type = new String(type.getBytes("iso-8859-1"), "tis620");	
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		
		List<ServiceOrder> soList = soService.selectSOForCloseByCriteria(name,
				searchStartDate, searchEndDate, type, serialNo, rows, page,
				sidx, sord);
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
				gridData.setType(so.getProduct().getType().getName());
				gridData.setBrand(so.getProduct().getBrand().getName());
				gridData.setModel(so.getProduct().getModel().getName());
				gridData.setSerialNo(so.getProduct().getSerialNo());
				gridData.setStatus(so.getStatus());
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
	
	@RequestMapping(value = "/closeServiceOrder", params = "do=preCloseServiceOrder")
	public String preCloseServiceOrder(
			@RequestParam(value = "serviceOrderID") String serviceOrderID,
			ModelMap model, HttpServletRequest request) {
		if (null == request.getSession().getAttribute("UserLogin")) {
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		
		ServiceOrder so = soService.selectByID(serviceOrderID);
		ServiceOrderForm form = new ServiceOrderForm();
		form.setServiceOrderID(so.getServiceOrderID());
		form.setServiceOrderDate(sdfDateTime.format(so.getServiceOrderDate()));
		form.setServiceType(so.getServiceType());
		if(so.getServiceType() == 1){
			form.setGuaranteeNo(so.getGuaranteeNo());
		}else if(so.getServiceType() == 4){
			form.setRefJobID(so.getRefJobID());
		}
		form.setAppointmentDate(sdfDateTime.format(so.getAppointmentDate()));
		form.setRefServiceOrder(so.getRefServiceOrder());
		form.setCustomerType(so.getCustomerType());
		form.setCustomerID(so.getCustomer().getCustomerID().toString());
		form.setProductID(so.getProduct().getProductID());
		form.setTypeID(so.getProduct().getType().getTypeID());
		form.setBrandID(so.getProduct().getBrand().getBrandID());
		form.setModel(so.getProduct().getModel().getName());
		form.setSerialNo(so.getProduct().getSerialNo());
		form.setAccessories(so.getAccessories());
		form.setDesc(so.getDescription());
		form.setProblem(so.getProblem());
		List<Type> typeList = new ArrayList<Type>();
		try {
			typeList = typeService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		form.setEmpOpen(so.getEmpOpen());
		
		if(so.getStartFix() != null){
			form.setStartFix(sdfDateTime.format(so.getStartFix()));
		}else{
			form.setStartFix("-");
		}
		if(so.getEndFix() != null){
			form.setEndFix(sdfDateTime.format(so.getEndFix()));
		}else{
			form.setEndFix("-");
		}
		
		if(form.getServiceType() == 1){
			form.setCosting("warranty");
		}else if(form.getServiceType() == 2 || form.getServiceType() == 3 || form.getServiceType() == 4){
			form.setCosting("cost");
		}else if(form.getServiceType() == 5){
			form.setCosting("free");
		}
		
		form.setIssuePart("noIssuedPart");
		
		form.setNetAmount(0.00);
		
		model.addAttribute("form", form);

		model.addAttribute("customer", so.getCustomer());

		model.addAttribute(
				"fullAddr",
				so.getCustomer().getAddress()
						+ " "
						+ this.messages.getMessage("subdistrict_abbr", null,
								new Locale("th", "TH"))
						+ " "
						+ so.getCustomer().getSubdistrict().getName()
						+ " "
						+ this.messages.getMessage("district_abbr", null,
								new Locale("th", "TH"))
						+ " "
						+ so.getCustomer().getDistrict().getName()
						+ " "
						+ this.messages.getMessage("province_abbr", null,
								new Locale("th", "TH")) + " "
						+ so.getCustomer().getProvince().getName());

		model.addAttribute("product", so.getProduct());
		
		Type type = typeList.get(0);
		List<Brand> brandList = new ArrayList<Brand>();
		List<Model> modelList = new ArrayList<Model>();
		if (so.getProduct().getType().getBrands().size() > 0) {
			brandList = so.getProduct().getType().getBrands();
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

		return VIEWNAME_FORM;
	}
}
