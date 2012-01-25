package com.twobytes.report.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.twobytes.report.form.CountCustomerRegionReportForm;
import com.twobytes.report.form.NumInstalledByEmpReportForm;

@Repository
public class ReportDAOImpl implements ReportDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<CountCustomerRegionReportForm> countCustomerRegion(
			String startDate, String endDate, Integer numRow) throws Exception {
//		select CONCAT(TRIM(p.name),', ', TRIM(d.name),', ', TRIM(sd.name)) addrName, count(distinct so.customerID, c.name, p.name, d.name, sd.name) num from serviceOrder so, customer c, province p, district d, subdistrict sd where so.customerID = c.customerID and p.provinceID = c.provinceID and d.districtID = c.districtID and sd.subdistrictID = c.subdistrictID group by p.name, d.name, sd.name order by num desc
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT CONCAT(TRIM(p.name),', ', TRIM(d.name),', ', TRIM(sd.name)) addrName, COUNT(distinct so.customerID, c.name, p.name, d.name, sd.name) num " +
				"FROM serviceOrder so, customer c, province p, district d, subdistrict sd " +
				"WHERE so.customerID = c.customerID " +
				"AND p.provinceID = c.provinceID " +
				"AND d.districtID = c.districtID " +
				"AND sd.subdistrictID = c.subdistrictID " +
				"AND so.status != 'cancel' ");
		if((null != startDate && !startDate.equals("")) && (null != endDate && !endDate.equals(""))){
			sql.append("AND DATE(so.serviceOrderDate) between :startDate and :endDate ");
		}else if((null != startDate && !startDate.equals("")) && (null == endDate || endDate.equals(""))){
			sql.append("AND DATE(so.serviceOrderDate) >= :startDate ");
		}else if((null == startDate || startDate.equals("")) && (null != endDate && !endDate.equals(""))){
			sql.append("AND DATE(so.serviceOrderDate) <= :endDate ");
		}
		
		sql.append("GROUP BY p.name, d.name, sd.name ORDER BY num desc");
		
		Query q = sessionFactory.getCurrentSession().createSQLQuery(sql.toString())
		.addScalar("AddrName", new StringType())
		.addScalar("num", new IntegerType())
		.setResultTransformer(Transformers.aliasToBean(CountCustomerRegionReportForm.class))
		.setMaxResults(numRow)
		.setFetchSize(numRow);
		
		if((null != startDate && !startDate.equals("")) && (null != endDate && !endDate.equals(""))){
			q.setString("startDate", startDate);
			q.setString("endDate", endDate);
		}else if((null != startDate && !startDate.equals("")) && (null == endDate || endDate.equals(""))){
			q.setString("startDate", startDate);
		}else if((null == startDate || startDate.equals("")) && (null != endDate && !endDate.equals(""))){
			q.setString("endDate", endDate);
		}
		
		List<CountCustomerRegionReportForm> retList = q.list();
		return retList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<NumInstalledByEmpReportForm> numInstalled(String startDate,
			String endDate, Integer employeeID) throws Exception {
		// SELECT CONCAT(emp.name,' ',emp.surname) fullName, count(p.productID) num FROM product p, employee emp WHERE p.installedBy is not null AND p.installedBy = emp.employeeID GROUP BY fullName;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT CONCAT(emp.name,' ',emp.surname) fullName, count(p.productID) num " +
				"FROM product p, employee emp " +
				"WHERE p.installedBy is not null " +
				"AND p.installedBy = emp.employeeID ");
		
		if((null != startDate && !startDate.equals("")) && (null != endDate && !endDate.equals(""))){
			sql.append("and DATE(p.installedDate) between :startDate and :endDate ");
		}else if((null != startDate && !startDate.equals("")) && (null == endDate || endDate.equals(""))){
			sql.append("and DATE(p.installedDate) >= :startDate ");
		}else if((null == startDate || startDate.equals("")) && (null != endDate && !endDate.equals(""))){
			sql.append("and DATE(p.installedDate) <= :endDate ");
		}
		if(employeeID != null){
			sql.append("and p.installedBy = :empID ");
		}
		
		sql.append("GROUP BY fullName ");
		
		Query q = sessionFactory.getCurrentSession().createSQLQuery(sql.toString())
		.addScalar("fullName", new StringType())
		.addScalar("num", new IntegerType())
		.setResultTransformer(Transformers.aliasToBean(NumInstalledByEmpReportForm.class));
		
		if((null != startDate && !startDate.equals("")) && (null != endDate && !endDate.equals(""))){
			q.setString("startDate", startDate);
			q.setString("endDate", endDate);
		}else if((null != startDate && !startDate.equals("")) && (null == endDate || endDate.equals(""))){
			q.setString("startDate", startDate);
		}else if((null == startDate || startDate.equals("")) && (null != endDate && !endDate.equals(""))){
			q.setString("endDate", endDate);
		}
		if(employeeID != null){
			q.setInteger("empID", employeeID);
		}
		
		List<NumInstalledByEmpReportForm> retList = q.list();
		return retList;
	}

}
