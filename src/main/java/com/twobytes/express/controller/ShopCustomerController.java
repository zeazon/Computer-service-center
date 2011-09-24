package com.twobytes.express.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twobytes.express.form.ShopCustomerGridData;
import com.twobytes.express.form.ShopCustomerSearchForm;
import com.twobytes.express.model.Armas;
import com.twobytes.express.service.ArmasService;
import com.twobytes.model.GridResponse;
import com.twobytes.security.form.LoginForm;

@Controller
public class ShopCustomerController {

	@Autowired
	private ArmasService armasService;
	
	@Autowired
	private MessageSource messages;
	
	public void setMessages(MessageSource messages) {
		this.messages = messages;
	}
	
	private String VIEWNAME_SEARCH = "shopCustomer.search";
	private String VIEWNAME_FORM = "shopCustomer.form";
	
	@RequestMapping(value = "/shopCustomer")
	public String view(ModelMap model, HttpServletRequest request) {
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		ShopCustomerSearchForm searchForm = new ShopCustomerSearchForm();
		model.addAttribute("searchForm", searchForm);
		return VIEWNAME_SEARCH;
	}
	
	@RequestMapping(value="/searchShopCustomer")
	public @ResponseBody GridResponse getData(@RequestParam(value="name", required=false) String name, @RequestParam("rows") Integer rows, @RequestParam("page") Integer page, @RequestParam("sidx") String sidx, @RequestParam("sord") String sord){
		// Because default Tomcat URI encoding is iso-8859-1 so it must encode back to tis620
		try{
			if(null != name){
				name = new String(name.getBytes("iso-8859-1"), "tis620");	
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		
		List<Armas> armasList = armasService.selectByCriteria(name, rows, page, sidx, sord);
		GridResponse response = new GridResponse();
		
		List<ShopCustomerGridData> rowsList = new ArrayList<ShopCustomerGridData>();
		
		Integer total_pages = 0;
		if(armasList.size() > 0){
			int endData = 0;
			if(armasList.size() < (rows*page)){
				endData = armasList.size();
			}else{
				endData = (rows*page);
			}
			for(int i=(rows*page - rows); i<endData; i++){
				Armas armas = armasList.get(i);
				ShopCustomerGridData gridData = new ShopCustomerGridData();
				gridData.setCuscod(armas.getCuscod());
				gridData.setCustyp(armas.getCustyp());
				
				StringBuilder fullName = new StringBuilder();
				if(!armas.getPrenam().trim().equals(""))
					fullName.append(armas.getPrenam().trim()+" ");
				fullName.append(armas.getCusnam().trim());
				gridData.setFullName(fullName.toString());
				
				StringBuilder fullAddr = new StringBuilder();
				if(!armas.getAddr01().trim().equals(""))
					fullAddr.append(armas.getAddr01().trim());
				if(!armas.getAddr02().trim().equals(""))
					fullAddr.append(" "+armas.getAddr02().trim());
				if(!armas.getAddr03().trim().equals(""))
					fullAddr.append(" "+armas.getAddr03().trim());
				if(!armas.getZipcod().trim().equals(""))
					fullAddr.append(" "+armas.getZipcod().trim());
				gridData.setFullAddr(fullAddr.toString());
				
				gridData.setTel(armas.getTelnum());
				gridData.setContact(armas.getContact());
				gridData.setRemark(armas.getRemark());
				
				rowsList.add(gridData);
			}
			total_pages = new Double(Math.ceil(((double)armasList.size()/(double)rows))).intValue();
		}
		
		if (page > total_pages) page=total_pages;
		response.setPage(page.toString());
		response.setRecords(String.valueOf(armasList.size()));
		response.setTotal(total_pages.toString());
		response.setRows(rowsList);
		return response;
	}
	
	@RequestMapping(value = "/shopCustomer", params = "do=viewData")
	public String viewData(@RequestParam(value="cuscod") String cuscod, ModelMap model, HttpServletRequest request){
		if(null == request.getSession().getAttribute("UserLogin")){
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		Armas armas = armasService.selectByID(cuscod);
		model.addAttribute("form", armas);
		return VIEWNAME_FORM;
	}
}
