package com.twobytes.master.controller;

import java.io.UnsupportedEncodingException;
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

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.twobytes.master.form.EmployeeForm;
import com.twobytes.master.form.EmployeeGridData;
import com.twobytes.master.form.EmployeeSearchForm;
import com.twobytes.master.service.EmployeeService;
import com.twobytes.model.CustomGenericResponse;
import com.twobytes.model.Employee;
import com.twobytes.model.GridResponse;
import com.twobytes.model.Role;
import com.twobytes.security.form.LoginForm;
import com.twobytes.security.service.RoleService;

@Controller
//@RequestMapping("/employee")
public class EmployeeController{
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private MessageSource messages;
	
	public void setMessages(MessageSource messages) {
		this.messages = messages;
	}
	
	private String VIEWNAME_SEARCH = "employee.search";
	private String VIEWNAME_FORM = "employee.form";
	
	@RequestMapping(value = "/employee")
//	@RequestMapping(params = "do=view")
//	public String view(HttpServletRequest request,
//			HttpServletResponse response) {
	public String view(ModelMap model, HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName(VIEWNAME_SEARCH);
//		return mav;
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		EmployeeSearchForm searchForm = new EmployeeSearchForm();
		model.addAttribute("searchForm", searchForm);
		return VIEWNAME_SEARCH;
	}
	
	/**
	 * 
	 * @param name		Employee's name (not required)
	 * @param surname	Employee's surname (not required)
	 * @param rows		Number of row that showed in grid
	 * @param page		Page Number
	 * @param sidx		Name column that sorted
	 * @param sord		Type of sorting asc or desc
	 * @return			Data as json type
	 */
	@RequestMapping(value="/searchEmp")
	public @ResponseBody GridResponse getData(@RequestParam(value="name", required=false) String name, @RequestParam(value="surname", required=false) String surname, @RequestParam("rows") Integer rows, @RequestParam("page") Integer page, @RequestParam("sidx") String sidx, @RequestParam("sord") String sord){
		
		// Because default Tomcat URI encoding is iso-8859-1 so it must encode back to tis620
		try{
			if(null != name){
				name = new String(name.getBytes("iso-8859-1"), "tis620");	
			}
			if(null != surname){
				surname = new String(surname.getBytes("iso-8859-1"), "tis620");	
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		List<Employee> empList = new ArrayList<Employee>();
		try{
			empList = employeeService.selectByCriteria(name, surname, rows, page, sidx, sord);
		}catch(Exception e){
			e.printStackTrace();
		}
		GridResponse response = new GridResponse();
		
		List<EmployeeGridData> rowsList = new ArrayList<EmployeeGridData>();
		
		Integer total_pages = 0;
		if(empList.size() > 0){
			int endData = 0;
			if(empList.size() < (rows*page)){
				endData = empList.size();
			}else{
				endData = (rows*page);
			}
			for(int i=(rows*page - rows); i<endData; i++){
				Employee emp = empList.get(i);
				EmployeeGridData gridForm = new EmployeeGridData();
				gridForm.setEmployeeID(emp.getEmployeeID().toString());
				gridForm.setEmployeeCode(emp.getEmployeeCode());
				gridForm.setName(emp.getName());
				gridForm.setSurname(emp.getSurname());
				gridForm.setLogin(emp.getLogin());
				rowsList.add(gridForm);
			}
			total_pages = new Double(Math.ceil(((double)empList.size()/(double)rows))).intValue();
		}
		
		if (page > total_pages) page=total_pages;
		
		response.setPage(page.toString());
		response.setRecords(String.valueOf(empList.size()));
		response.setTotal(total_pages.toString());
		response.setRows(rowsList);
		return response;
	}
	
	@RequestMapping(value = "/employee", params = "do=preAdd")
	public String preAdd(ModelMap model, HttpServletRequest request){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		EmployeeForm form = new EmployeeForm();
		model.addAttribute("form", form);
		List<Role> roleList = roleService.getAll();
		model.addAttribute("roleList", roleList);
		return VIEWNAME_FORM;
	}
	
	@RequestMapping(value = "/employee", params = "do=save")
	public String doSave(@ModelAttribute("form") EmployeeForm form, HttpServletRequest request, ModelMap model){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		Date now = new Date();
		Employee user = (Employee)request.getSession().getAttribute("UserLogin");
		Employee emp = new Employee();
		String msg = "";
		if(form.getEmployeeID() != ""){
			// update
			boolean validLogin = false;
			try{
				validLogin = employeeService.checkValidLogin(form.getLogin(), Integer.valueOf(form.getEmployeeID()));
			}catch(MySQLIntegrityConstraintViolationException e){
				e.printStackTrace();
				// error occur go back to employee form screen
				model.addAttribute("errMsg", e.getMessage());
				List<Role> roleList = roleService.getAll();
				model.addAttribute("roleList", roleList);
				return VIEWNAME_FORM;
			}
			catch(Exception e){
				e.printStackTrace();
				// error occur go back to employee form screen
				model.addAttribute("errMsg", e.getMessage());
				List<Role> roleList = roleService.getAll();
				model.addAttribute("roleList", roleList);
				return VIEWNAME_FORM;
			}
			if(!validLogin){
				// invalid login go back to employee form screen
				model.addAttribute("errMsg", this.messages.getMessage("error.duplicateUsername", null, new Locale("th", "TH")));
				List<Role> roleList = roleService.getAll();
				model.addAttribute("roleList", roleList);
				return VIEWNAME_FORM;
			}
			try{
				emp = employeeService.selectByID(Integer.valueOf(form.getEmployeeID()));
			}catch(Exception e){
				e.printStackTrace();
				// error occur go back to employee form screen
				model.addAttribute("errMsg", e.getMessage());
				List<Role> roleList = roleService.getAll();
				model.addAttribute("roleList", roleList);
				return VIEWNAME_FORM;
			}
			msg = this.messages.getMessage("msg.updateComplete", null, new Locale("th", "TH"));
		}else{
			// add
			// check login
			boolean validLogin = false;
			try{
				validLogin = employeeService.checkValidLogin(form.getLogin());
			}catch(Exception e){
				e.printStackTrace();
				// error occur go back to employee form screen
				model.addAttribute("errMsg", e.getMessage());
				List<Role> roleList = roleService.getAll();
				model.addAttribute("roleList", roleList);
				return VIEWNAME_FORM;
			}
			if(!validLogin){
				// invalid login go back to employee form screen
				model.addAttribute("errMsg", this.messages.getMessage("error.duplicateUsername", null, new Locale("th", "TH")));
				List<Role> roleList = roleService.getAll();
				model.addAttribute("roleList", roleList);
				return VIEWNAME_FORM;
			}
			emp.setCreatedBy(user.getEmployeeID());
			emp.setCreatedDate(now);
			msg = this.messages.getMessage("msg.addComplete", null, new Locale("th", "TH"));
		}
		emp.setEmployeeCode(form.getEmployeeCode());
		emp.setName(form.getName());
		emp.setSurname(form.getSurname());
		emp.setLogin(form.getLogin());
		emp.setPassword(form.getPassword());
		Role role = roleService.getRole(Integer.valueOf(form.getRoleID()));
		emp.setRoleID(role);
		
		emp.setUpdatedBy(user.getEmployeeID());
		emp.setUpdatedDate(now);
		boolean canSave;
		try{
			canSave = employeeService.save(emp);
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			List<Role> roleList = roleService.getAll();
			model.addAttribute("roleList", roleList);
			return VIEWNAME_FORM;
		}
		if(!canSave){
			model.addAttribute("errMsg", this.messages.getMessage("error.cannotSave", null, new Locale("th", "TH")));
			List<Role> roleList = roleService.getAll();
			model.addAttribute("roleList", roleList);
			return VIEWNAME_FORM;
		}
		model.addAttribute("msg", msg);
		EmployeeSearchForm searchForm = new EmployeeSearchForm();
		model.addAttribute("searchForm", searchForm);
		return VIEWNAME_SEARCH;
	}
	
	@RequestMapping(value = "/employee", params = "do=preEdit")
	public String preEdit(@RequestParam(value="employeeID") Integer employeeID, ModelMap model, HttpServletRequest request){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		Employee emp = new Employee();
		try{
			emp = employeeService.selectByID(employeeID);
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			EmployeeSearchForm searchForm = new EmployeeSearchForm();
			model.addAttribute("searchForm", searchForm);
			return VIEWNAME_SEARCH;
		}
		EmployeeForm form = new EmployeeForm();
		form.setEmployeeID(emp.getEmployeeID().toString());
		form.setEmployeeCode(emp.getEmployeeCode());
		form.setName(emp.getName());
		form.setSurname(emp.getSurname());
		form.setRoleID(emp.getRoleID().getRoleID().toString());
		form.setLogin(emp.getLogin());
		form.setPassword(emp.getPassword());
		form.setConfirmPwd(emp.getPassword());
		model.addAttribute("form", form);
		List<Role> roleList = roleService.getAll();
		model.addAttribute("roleList", roleList);
		return VIEWNAME_FORM;
	}
	
	@RequestMapping(value = "/employee", params = "do=delete")
	public @ResponseBody CustomGenericResponse delete(HttpServletRequest request){
		CustomGenericResponse response = new CustomGenericResponse();
		response.setSuccess(true);
		try{
			employeeService.delete(Integer.valueOf(request.getParameter("employeeID")));
			response.setMessage(this.messages.getMessage("msg.deleteSuccess", null, new Locale("th", "TH")));
		}catch(Exception e){
			response.setSuccess(false);
			response.setMessage(this.messages.getMessage("error.cannotDelete", null, new Locale("th", "TH")));
		}
		return response;
	}
}
