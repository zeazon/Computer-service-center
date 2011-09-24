package com.twobytes.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.twobytes.model.Employee;
import com.twobytes.model.Menu;
import com.twobytes.model.Role;
import com.twobytes.security.form.LoginForm;
import com.twobytes.security.service.RoleService;
import com.twobytes.security.service.SecurityService;

@Controller
public class SecurityOldController {
	
	@Autowired
	private SecurityService securityService;
	
	@Resource
	private RoleService roleService;
	
	/*@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody String login(@RequestParam String login, @RequestParam String password) {
		String pass = "true";
		Employee emp = securityService.login(login, password);
		if(null == emp)
			pass = "false";
		return pass;
	}*/
	
	@RequestMapping(value = "/loginOld", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		LoginForm form = new LoginForm();
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		form.setLogin(login);
		form.setPassword(password);
		String errMsg = "";
		Employee emp = new Employee();
		try{
			// If cannot login securityService.login will return null
			emp = securityService.login(login, password);
		}catch(Exception e){
			errMsg = e.getMessage();
			mav.addObject("errMsg", errMsg);
			mav.addObject("form", form);
			mav.setViewName("indexScreen");
			return mav;
		}
		
		if(null == emp){
			// Can't login
			errMsg = "Can not login";
			mav.addObject("errMsg", errMsg);
			mav.addObject("form", form);
			mav.setViewName("indexScreen");
		}else{
			// Can login
			request.getSession().setAttribute("UserLogin", emp);
			StringBuffer menuStr = new StringBuffer();
			Role role = emp.getRoleID();
			try{
				List<Menu> mainMenu = securityService.selectMainMenu(role.getRoleID());
				
				// open ul 's main
				menuStr.append("<ul id=\"nav\">");
				for(Menu menu: mainMenu){
	//			for(int i=0; i<mainMenu.size(); i++){
	//				Menu menu = mainMenu.get(i);
					// open li 's main
					menuStr.append("<li class=\"top\">");
					menuStr.append("<a href=\""+request.getContextPath()+menu.getLink()+"\" class=\"top_link\" onclick='return false'><span>"+menu.getName()+"</span></a>");
					/*
					 * get submenu lv 1
					 */
					List<Menu> subMenu1 = securityService.selectSubMenu(role.getRoleID(), menu.getMenuID());
					
					if(subMenu1.size()>0){
						// open ul 's sub menu 1
						menuStr.append("<ul class=\"sub\">");
						for(Menu subMenu1M:subMenu1){
	//					for(int j=0; j<subMenu1.size(); j++){
	//						Menu subMenu1M = subMenu1.get(j);
							// open li 's sub menu 1
							menuStr.append("<li>");
							menuStr.append("<a href=\""+request.getContextPath()+subMenu1M.getLink()+"\">"+subMenu1M.getName()+"</a>");
							// close li 's sub menu 1
							menuStr.append("</li>");
						}
						// close ul 's sub menu 1
						menuStr.append("</ul>");
					}
					// close li 's main
					menuStr.append("</li>");
				}
				// close ul 's main
				menuStr.append("</ul>");
			}catch(Exception e){
				e.printStackTrace();
			}
			mav.addObject("menuStr", menuStr);
			mav.setViewName("firstScreen");
		}
		return mav;
	}
	
	@RequestMapping(value = "/logoutOld")
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		request.getSession().removeAttribute("UserLogin");
		mav.setViewName("indexScreen");
		return mav;
	}

}
