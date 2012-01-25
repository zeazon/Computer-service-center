package com.twobytes.report.service;

import java.util.List;

import com.twobytes.report.form.CountCustomerRegionReportForm;
import com.twobytes.report.form.NumInstalledByEmpReportForm;

public interface ReportService {
	public List<CountCustomerRegionReportForm> countCustomerRegion(String startDate, String endDate, Integer numRow);
	public List<NumInstalledByEmpReportForm> numInstalled(String startDate, String endDate, Integer employeeID);
}