package com.twobytes.report.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twobytes.report.dao.ReportDAO;
import com.twobytes.report.form.CountCustomerRegionReportForm;
import com.twobytes.report.form.NumInstalledByEmpReportForm;
import com.twobytes.report.form.NumSaleByEmpReportForm;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportDAO reportDAO;
	
	@Override
	@Transactional
	public List<CountCustomerRegionReportForm> countCustomerRegion(
			String startDate, String endDate, Integer numRow) {
		List<CountCustomerRegionReportForm> retList = new ArrayList<CountCustomerRegionReportForm>();
		try{
			retList = reportDAO.countCustomerRegion(startDate, endDate, numRow);
		}catch(Exception e){
			e.printStackTrace();
		}
		return retList;
	}

	@Override
	@Transactional
	public List<NumInstalledByEmpReportForm> numInstalled(String startDate,
			String endDate, Integer employeeID) {
		List<NumInstalledByEmpReportForm> retList = new ArrayList<NumInstalledByEmpReportForm>();
		try{
			retList = reportDAO.numInstalled(startDate, endDate, employeeID);
		}catch(Exception e){
			e.printStackTrace();
		}
		return retList;
	}

	@Override
	@Transactional
	public List<NumSaleByEmpReportForm> numSale(Integer month, Integer year) {
		List<NumSaleByEmpReportForm> retList = new ArrayList<NumSaleByEmpReportForm>();
		try{
			retList = reportDAO.numSale(month, year);
		}catch(Exception e){
			e.printStackTrace();
		}
		return retList;
	}

	@Override
	@Transactional
	public Map<String, Object> issuePart(String startDate, String endDate,
			String code, Integer rows, Integer page, String orderBy,
			String orderType) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = reportDAO.issuePart(startDate, endDate, code, rows, page, orderBy, orderType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}