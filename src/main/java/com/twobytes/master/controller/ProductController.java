package com.twobytes.master.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twobytes.master.form.ProductForm;
import com.twobytes.master.form.ProductGridData;
import com.twobytes.master.form.ProductSearchForm;
import com.twobytes.master.service.BrandService;
import com.twobytes.master.service.EmployeeService;
import com.twobytes.master.service.ModelService;
import com.twobytes.master.service.ProductService;
import com.twobytes.master.service.TypeService;
import com.twobytes.model.Brand;
import com.twobytes.model.CustomGenericResponse;
import com.twobytes.model.Employee;
import com.twobytes.model.GridResponse;
import com.twobytes.model.Model;
import com.twobytes.model.Product;
import com.twobytes.model.Type;
import com.twobytes.security.form.LoginForm;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private TypeService typeService;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private ModelService modelService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private MessageSource messages;
	
	public void setMessages(MessageSource messages) {
		this.messages = messages;
	}
	
	private static final Logger logger = LoggerFactory
	.getLogger(ProductController.class);
	
	private String VIEWNAME_SEARCH = "product.search";
	private String VIEWNAME_FORM = "product.form";
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale ("US"));
	
	@RequestMapping(value = "/product")
	public String view(ModelMap model, HttpServletRequest request) {
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		ProductSearchForm searchForm = new ProductSearchForm();
		model.addAttribute("searchForm", searchForm);
		
		List<Brand> brandList = new ArrayList<Brand>();
		List<Type> typeList = new ArrayList<Type>();
		try {
			typeList = typeService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("typeList", typeList);
		model.addAttribute("brandList", brandList);
		
		return VIEWNAME_SEARCH;
	}
	
	@RequestMapping(value="/searchProduct")
	public @ResponseBody GridResponse getData(@RequestParam(value="serialNo", required=false) String serialNo, @RequestParam(value="typeID", required=false) String typeID, @RequestParam(value="brandID", required=false) String brandID, @RequestParam(value="modelID", required=false) String modelID, @RequestParam("rows") Integer rows, @RequestParam("page") Integer page, @RequestParam("sidx") String sidx, @RequestParam("sord") String sord){
		// Because default Tomcat URI encoding is iso-8859-1 so it must encode back to tis620
		try{
			if(null != serialNo){
				serialNo = new String(serialNo.getBytes("iso-8859-1"), "tis620");	
			}
			if(null != typeID){
				typeID = new String(typeID.getBytes("iso-8859-1"), "tis620");	
			}
			if(null != brandID){
				brandID = new String(brandID.getBytes("iso-8859-1"), "tis620");	
			}
			if(null != modelID){
				modelID = new String(modelID.getBytes("iso-8859-1"), "tis620");	
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		List<Product> productList = new ArrayList<Product>();

		productList = productService.selectByCriteria(typeID, brandID, modelID, serialNo, rows, page, sidx, sord);
		
		GridResponse response = new GridResponse();
		List<ProductGridData> rowsList = new ArrayList<ProductGridData>();
		
		Integer total_pages = 0;
		if(productList.size() > 0){
			int endData = 0;
			if(productList.size() < (rows*page)){
				endData = productList.size();
			}else{
				endData = (rows*page);
			}
			for(int i=(rows*page - rows); i<endData; i++){
				Product product = productList.get(i);
				ProductGridData gridData = new ProductGridData();
				gridData.setProductID(product.getProductID());
				gridData.setTypeName(product.getType().getName());
				if(null != product.getBrand()){
					gridData.setBrandName(product.getBrand().getName());
				}
				if(null != product.getModel()){
					gridData.setModelName(product.getModel().getName());
				}
				gridData.setDescription(product.getDescription());
				gridData.setSerialNo(product.getSerialNo());
				rowsList.add(gridData);
			}
			total_pages = new Double(Math.ceil(((double)productList.size()/(double)rows))).intValue();
		}
		
		if (page > total_pages) page=total_pages;
		response.setPage(page.toString());
		response.setRecords(String.valueOf(productList.size()));
		response.setTotal(total_pages.toString());
		response.setRows(rowsList);
		return response;
	}
	
	@RequestMapping(value = "/product", params = "do=preAdd")
	public String preAdd(ModelMap model, HttpServletRequest request){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		ProductForm form = new ProductForm();
		
		List<Brand> brandList = new ArrayList<Brand>();
		List<Type> typeList = new ArrayList<Type>();
		List<Model> modelList = new ArrayList<Model>();
		try {
			typeList = typeService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(typeList.size() > 0){
			Type type = typeList.get(0);
			brandList = type.getBrands();
			form.setTypeID(type.getTypeID());
		}
		
		if(brandList.size() > 0){
			Type type = typeList.get(0);
			Brand brand = brandList.get(0);
			modelList = modelService.getModelByTypeAndBrand(type.getTypeID(), brand.getBrandID());
			form.setBrandID(brand.getBrandID());
			form.setModelID(modelList.get(0).getModelID());
		}
		
		if(brandList.size() == 0){
			Brand brand = new Brand();
			brand.setBrandID(null);
			brand.setName(" ");
			brandList.add(brand);
		}
		if(modelList.size() == 0){
			modelList.add(new Model());
		}
		
		model.addAttribute("form", form);
		model.addAttribute("typeList", typeList);
		model.addAttribute("brandList", brandList);
		model.addAttribute("modelList", modelList);
		
		List<Employee> empList = employeeService.getAll();
		model.addAttribute("employeeList", empList);
		
		model.addAttribute("mode", "add");
		return VIEWNAME_FORM;
	}
	
	@RequestMapping(value = "/product", params = "do=save")
	public String doSave(@ModelAttribute("form") ProductForm form, HttpServletRequest request, ModelMap model, @RequestParam String mode){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		Date now = new Date();
		Employee user = (Employee)request.getSession().getAttribute("UserLogin");
		Product product = new Product();
		String msg = "";
		if(mode.equals("edit")){
			// update
			try{
				product = productService.selectByID(form.getProductID());
			}catch(Exception e){
				e.printStackTrace();
				model.addAttribute("errMsg", e.getMessage());
				List<Brand> brandList = new ArrayList<Brand>();
				List<Type> typeList = new ArrayList<Type>();
				List<Model> modelList = new ArrayList<Model>();
				try {
					typeList = typeService.getAll();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				Type type = new Type();
				try {
					type = typeService.selectByID(form.getTypeID());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				if(typeList.size() > 0){
					brandList = type.getBrands();
				}
				
				if(brandList.size() > 0){
					Brand brand = new Brand();
					try {
						brand = brandService.selectByID(form.getBrandID());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					modelList = modelService.getModelByTypeAndBrand(type.getTypeID(), brand.getBrandID());
				}
				
				if(brandList.size() == 0){
					Brand brand = new Brand();
					brand.setBrandID(null);
					brand.setName(" ");
					brandList.add(brand);
				}
				if(modelList.size() == 0){
					modelList.add(new Model());
				}
				
				model.addAttribute("typeList", typeList);
				model.addAttribute("brandList", brandList);
				model.addAttribute("modelList", modelList);
				
				List<Employee> empList = employeeService.getAll();
				model.addAttribute("employeeList", empList);
				
				model.addAttribute("mode", mode);
				return VIEWNAME_FORM;
			}
			msg = this.messages.getMessage("msg.updateComplete", null, new Locale("th", "TH"));
		}else{
			// add
//			product.setProductID(form.getProductID());
			
			product.setCreatedBy(user.getEmployeeID());
			product.setCreatedDate(now);
			msg = this.messages.getMessage("msg.addComplete", null, new Locale("th", "TH"));
		}
		product.setDescription(form.getDescription());
		product.setSerialNo(form.getSerialNo());
		
		if(form.getInstalledBy() != null){
			try {
				product.setInstalledBy(employeeService.selectByID(form.getInstalledBy()));
			} catch (Exception e3) {
				e3.printStackTrace();
			}
		}else{
			product.setInstalledBy(null);
		}
		if(form.getInstalledDate() != null && form.getInstalledDate() != ""){
			try {
				product.setInstalledDate(sdf.parse(form.getInstalledDate()));
			} catch (ParseException e) {
			}
		}else{
			product.setInstalledDate(null);
		}
		
		try {
			product.setWarrantyDate(sdf.parse(form.getWarrantyDate()));
		} catch (ParseException e2) {
		}
		try {
			product.setWarrantyExpire(sdf.parse(form.getWarrantyExpire()));
		} catch (ParseException e2) {
		}
		product.setRemark(form.getRemark());
		
		Type type = new Type();
		try {
			type = typeService.selectByID(form.getTypeID());
		} catch (Exception e) {
			e.printStackTrace();
		}
		product.setType(type);
		Brand brand = new Brand();
		try {
			brand = brandService.selectByID(form.getBrandID());
		} catch (Exception e) {
			e.printStackTrace();
		}
		product.setBrand(brand);
		Model modelObj = modelService.selectByID(form.getModelID());
		product.setModel(modelObj);
		
		product.setUpdatedBy(user.getEmployeeID());
		product.setUpdatedDate(now);
		
		try {
			productService.save(product);
		} catch (Exception e) {
			e.printStackTrace();
			
			List<Brand> brandList = new ArrayList<Brand>();
			List<Type> typeList = new ArrayList<Type>();
			List<Model> modelList = new ArrayList<Model>();
			try {
				typeList = typeService.getAll();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			if(typeList.size() > 0){
				brandList = type.getBrands();
			}
			
			if(brandList.size() > 0){
				modelList = modelService.getModelByTypeAndBrand(type.getTypeID(), brand.getBrandID());
			}
			
			if(brandList.size() == 0){
				Brand brand1 = new Brand();
				brand1.setBrandID(null);
				brand1.setName(" ");
				brandList.add(brand1);
			}
			if(modelList.size() == 0){
				modelList.add(new Model());
			}
			
			model.addAttribute("typeList", typeList);
			model.addAttribute("brandList", brandList);
			model.addAttribute("modelList", modelList);
			
			List<Employee> empList = employeeService.getAll();
			model.addAttribute("employeeList", empList);
			
			model.addAttribute("errMsg", e.getMessage());
			model.addAttribute("mode", mode);
			return VIEWNAME_FORM;
		}
		
		model.addAttribute("msg", msg);
		ProductSearchForm searchForm = new ProductSearchForm();
		model.addAttribute("searchForm", searchForm);
		
		List<Brand> brandList = new ArrayList<Brand>();
		List<Type> typeList = new ArrayList<Type>();
		try {
			typeList = typeService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("typeList", typeList);
		model.addAttribute("brandList", brandList);
		
		return VIEWNAME_SEARCH;
	}
	
	@RequestMapping(value = "/product", params = "do=preEdit")
	public String preEdit(@RequestParam(value="productID") String productID, ModelMap model, HttpServletRequest request){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		logger.debug("edit productID = "+(String)request.getParameter("productID"));
		Product product = new Product();
		try{
			product = productService.selectByID(productID);
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			ProductSearchForm searchForm = new ProductSearchForm();
			model.addAttribute("searchForm", searchForm);
			
			List<Brand> brandList = new ArrayList<Brand>();
			List<Type> typeList = new ArrayList<Type>();
			try {
				typeList = typeService.getAll();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			model.addAttribute("typeList", typeList);
			model.addAttribute("brandList", brandList);
			return VIEWNAME_SEARCH;
		}
		ProductForm form = new ProductForm();
		form.setProductID(product.getProductID());
		form.setSerialNo(product.getSerialNo());
		form.setDescription(product.getDescription());
		form.setTypeID(product.getType().getTypeID());
		form.setBrandID(product.getBrand().getBrandID());
		form.setModelID(product.getModel().getModelID());
		if(null != product.getInstalledBy()){
			form.setInstalledBy(product.getInstalledBy().getEmployeeID());
		}
		if(null != product.getInstalledDate()){
			form.setInstalledDate(sdf.format(product.getInstalledDate()));
		}
		if(null != product.getWarrantyDate()){
			form.setWarrantyDate(sdf.format(product.getWarrantyDate()));
		}
		if(null != product.getWarrantyExpire()){
			form.setWarrantyExpire(sdf.format(product.getWarrantyExpire()));
		}
		form.setRemark(product.getRemark());
		
		List<Brand> brandList = new ArrayList<Brand>();
		List<Type> typeList = new ArrayList<Type>();
		List<Model> modelList = new ArrayList<Model>();
		try {
			typeList = typeService.getAll();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		if(typeList.size() > 0){
			brandList = product.getType().getBrands();
		}
		
		if(brandList.size() > 0){
			modelList = modelService.getModelByTypeAndBrand(product.getType().getTypeID(), product.getBrand().getBrandID());
		}
		
		if(brandList.size() == 0){
			Brand brand1 = new Brand();
			brand1.setBrandID(null);
			brand1.setName(" ");
			brandList.add(brand1);
		}
		if(modelList.size() == 0){
			modelList.add(new Model());
		}
		
		model.addAttribute("typeList", typeList);
		model.addAttribute("brandList", brandList);
		model.addAttribute("modelList", modelList);
		
		List<Employee> empList = employeeService.getAll();
		model.addAttribute("employeeList", empList);
		
		model.addAttribute("mode", "edit");
		model.addAttribute("form", form);
		return VIEWNAME_FORM;
	}
	
	@RequestMapping(value = "/product", params = "do=delete")
	public @ResponseBody CustomGenericResponse delete(HttpServletRequest request){
		logger.debug("delete productID = "+(String)request.getParameter("productID"));
		CustomGenericResponse response = new CustomGenericResponse();
		response.setSuccess(true);
		try{
			productService.delete((String)request.getParameter("productID"));
			response.setMessage(this.messages.getMessage("msg.deleteSuccess", null, new Locale("th", "TH")));
		}catch(Exception e){
			e.printStackTrace();
			response.setSuccess(false);
			response.setMessage(this.messages.getMessage("error.cannotDelete", null, new Locale("th", "TH")));
		}
		return response;
	}
	
	@RequestMapping(value = "/product", params = "do=savePopup")
	public @ResponseBody CustomGenericResponse doSavePopup(@ModelAttribute ProductForm form, HttpServletRequest request, ModelMap model){
		CustomGenericResponse response = new CustomGenericResponse();
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			response.setSuccess(false);
			response.setMessage(this.messages.getMessage("error.cannotSave", null, new Locale("th", "TH")));
			return response;
		}
		
		// Because default Tomcat URI encoding is iso-8859-1 so it must encode back to tis620
		try{
			if(null != form.getDescription()){
//				form.setDescription(new String(form.getDescription().getBytes("iso-8859-1"), "tis620"));
				form.setDescription(new String(form.getDescription().getBytes("iso-8859-1"), "UTF-8"));
			}
			if(null != form.getRemark()){
//				form.setRemark(new String(form.getRemark().getBytes("iso-8859-1"), "tis620"));
				form.setRemark(new String(form.getRemark().getBytes("iso-8859-1"), "UTF-8"));
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		
		Date now = new Date();
		Employee user = (Employee)request.getSession().getAttribute("UserLogin");
		Product product = new Product();
//		product.setProductID(form.getProductID());
		
		product.setCreatedBy(user.getEmployeeID());
		product.setCreatedDate(now);
		
		product.setDescription(form.getDescription());
		product.setSerialNo(form.getSerialNo());
		try {
			product.setWarrantyDate(sdf.parse(form.getWarrantyDate()));
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		try {
			product.setWarrantyExpire(sdf.parse(form.getWarrantyExpire()));
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		product.setRemark(form.getRemark());
		
		Type type = new Type();
		try {
			type = typeService.selectByID(form.getTypeID());
		} catch (Exception e) {
			e.printStackTrace();
		}
		product.setType(type);
		Brand brand = new Brand();
		try {
			brand = brandService.selectByID(form.getBrandID());
		} catch (Exception e) {
			e.printStackTrace();
		}
		product.setBrand(brand);
		Model modelObj = modelService.selectByID(form.getModelID());
		product.setModel(modelObj);
		
		product.setUpdatedBy(user.getEmployeeID());
		product.setUpdatedDate(now);
		
//		boolean canSave = false;
		String result = "";
		try {
			result = productService.save(product);
			response.setData(result);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setMessage(this.messages.getMessage("error.cannotSave", null, new Locale("th", "TH")));
			return response;
		}
		if(result.equals("false")){
			response.setSuccess(false);
			response.setMessage(this.messages.getMessage("error.cannotSave", null, new Locale("th", "TH")));
			return response;
		}
		response.setSuccess(true);
		response.setMessage(this.messages.getMessage("msg.addComplete", null, new Locale("th", "TH")));
		return response;
	}
}
