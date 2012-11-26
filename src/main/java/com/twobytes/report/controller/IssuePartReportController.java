package com.twobytes.report.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twobytes.master.service.BrandService;
import com.twobytes.master.service.ModelService;
import com.twobytes.master.service.TypeService;
import com.twobytes.model.Brand;
import com.twobytes.model.GridResponse;
import com.twobytes.model.Model;
import com.twobytes.model.Type;
import com.twobytes.repair.service.IssuePartService;
import com.twobytes.report.form.IssuePartReportForm;
import com.twobytes.report.form.IssuePartReportGridData;
import com.twobytes.report.form.IssuePartReportSearchForm;
import com.twobytes.report.service.ReportService;
import com.twobytes.security.form.LoginForm;

@Controller
public class IssuePartReportController {
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private TypeService typeService;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private ModelService modelService;

	@Autowired
	private IssuePartService ipService;
	
	private String VIEWNAME_SEARCH = "issuePartReport.search";
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale ("US"));
	
	@RequestMapping(value = "/issuePartReport")
	public String view(ModelMap model, HttpServletRequest request) {
		if (null == request.getSession().getAttribute("UserLogin")) {
			LoginForm loginForm = new LoginForm();
			model.addAttribute(loginForm);
			return "loginScreen";
		}
		
		IssuePartReportSearchForm searchForm = new IssuePartReportSearchForm();
		model.addAttribute("searchForm", searchForm);
		
		List<String> codeList = ipService.getPart();
		model.addAttribute("codeList", codeList);
		
		List<Type> typeList = new ArrayList<Type>();
		try {
			typeList = typeService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Type type = typeList.get(0);
		searchForm.setTypeID(type.getTypeID());
		List<Brand> brandList = new ArrayList<Brand>();
		if (type.getBrands().size() > 0) {
			brandList = type.getBrands();
			Brand brand = brandList.get(0);
			searchForm.setBrandID(brand.getBrandID());
		} else {
			Brand blankBrand = new Brand();
			blankBrand.setBrandID(null);
			blankBrand.setName("");
			brandList.add(blankBrand);
		}

		List<Model> modelList = new ArrayList<Model>();
		if (type.getBrands().size() > 0) {
			brandList = type.getBrands();
			searchForm.setBrandID(searchForm.getBrandID());
			
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
		return VIEWNAME_SEARCH;
	}
	
	@RequestMapping(value = "/searchIssuePart")
	@SuppressWarnings("unchecked")
	public @ResponseBody
	GridResponse getData(
			@RequestParam(required = false) String code,
			@RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate,
			@RequestParam("rows") Integer rows,
			@RequestParam("page") Integer page,
			@RequestParam("sidx") String sidx, @RequestParam("sord") String sord) {
		String[] datePart;
		String searchStartDate = null;
		String searchEndDate = null;	
		// Because default Tomcat URI encoding is iso-8859-1 so it must encode
		// back to tis620
		try {
			if (null != startDate && !startDate.equals("")) {
				startDate = new String(startDate.getBytes("iso-8859-1"),
						"tis620");
				datePart = startDate.split("/");
				searchStartDate = datePart[2] + "-" + datePart[1] + "-"
						+ datePart[0];
			}
			if (null != endDate && !endDate.equals("")) {
				endDate = new String(endDate.getBytes("iso-8859-1"), "tis620");
				datePart = endDate.split("/");
				searchEndDate = datePart[2] + "-" + datePart[1] + "-"
						+ datePart[0];
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		Map<String, Object> ret = new HashMap<String, Object>();
		ret = reportService.issuePart(searchStartDate, searchEndDate, code, rows, page, sidx, sord);
		
		List<IssuePartReportForm> iprList = (List<IssuePartReportForm>) ret.get("list");
		GridResponse response = new GridResponse();
		List<IssuePartReportGridData> rowsList = new ArrayList<IssuePartReportGridData>();
		
		Integer total_pages = 0;
		if (iprList.size() > 0) {
			for(IssuePartReportForm ipr:iprList){
				IssuePartReportGridData gridData = new IssuePartReportGridData();
				gridData.setServiceOrderID(ipr.getServiceOrderID());
				gridData.setServiceOrderDate(sdf.format(ipr.getServiceOrderDate()));
				gridData.setFixEmp_name(ipr.getFixEmp_name());
				gridData.setReturnDate(sdf.format(ipr.getReturnDate()));
				gridData.setTotalPrice(ipr.getTotalPrice());
				gridData.setQuantity(ipr.getQuantity());
				
				rowsList.add(gridData);
			}
			//total_pages = new Double(Math.ceil(((double)(Long) ret.get("maxRows")/(double)rows))).intValue();
			total_pages = new Double(Math.ceil(((double)(Integer) ret.get("maxRows")/(double)rows))).intValue();
		}
		if (page > total_pages) page=total_pages;
		response.setPage(page.toString());
//		response.setRecords(((Long) ret.get("maxRows")).toString());
		response.setRecords(((Integer) ret.get("maxRows")).toString());
		response.setTotal(total_pages.toString());
		response.setRows(rowsList);
		return response;
	}
	
}