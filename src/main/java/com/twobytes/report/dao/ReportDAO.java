package com.twobytes.report.dao;

import java.util.List;

import com.twobytes.report.form.CountCustomerRegionReportForm;
import com.twobytes.report.form.NumInstalledByEmpReportForm;

public interface ReportDAO {
	public List<CountCustomerRegionReportForm> countCustomerRegion(String startDate, String endDate, Integer numRow) throws Exception;
	public List<NumInstalledByEmpReportForm> numInstalled(String startDate, String endDate, Integer employeeID) throws Exception;
}
