package com.twobytes.report.dao;

import java.util.List;
import java.util.Map;

import com.twobytes.report.form.CountCustomerRegionReportForm;
import com.twobytes.report.form.NumInstalledByEmpReportForm;
import com.twobytes.report.form.NumSaleByEmpReportForm;

public interface ReportDAO {
	public List<CountCustomerRegionReportForm> countCustomerRegion(String startDate, String endDate, Integer numRow) throws Exception;
	public List<NumInstalledByEmpReportForm> numInstalled(String startDate, String endDate, Integer employeeID) throws Exception;
	public List<NumSaleByEmpReportForm> numSale(Integer month, Integer year) throws Exception;
	public Map<String, Object> issuePart(String startDate, String endDate, String code, Integer rows, Integer page, String orderBy, String orderType) throws Exception;
}