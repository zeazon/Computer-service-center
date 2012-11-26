package com.twobytes.report.service;

import java.util.List;
import java.util.Map;

import com.twobytes.report.form.CountCustomerRegionReportForm;
import com.twobytes.report.form.NumInstalledByEmpReportForm;
import com.twobytes.report.form.NumSaleByEmpReportForm;

public interface ReportService {
	public List<CountCustomerRegionReportForm> countCustomerRegion(String startDate, String endDate, Integer numRow);
	public List<NumInstalledByEmpReportForm> numInstalled(String startDate, String endDate, Integer employeeID);
	public List<NumSaleByEmpReportForm> numSale(Integer month, Integer year);
	public Map<String, Object> issuePart(String startDate, String endDate, String code, Integer rows, Integer page, String orderBy, String orderType);
}