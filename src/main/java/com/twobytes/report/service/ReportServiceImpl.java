package com.twobytes.report.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twobytes.report.dao.ReportDAO;
import com.twobytes.report.form.CountCustomerRegionReportForm;
import com.twobytes.report.form.NumInstalledByEmpReportForm;

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

}
