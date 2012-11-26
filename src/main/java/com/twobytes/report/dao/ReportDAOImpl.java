package com.twobytes.report.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.twobytes.report.form.CountCustomerRegionReportForm;
import com.twobytes.report.form.IssuePartReportForm;
import com.twobytes.report.form.NumInstalledByEmpReportForm;
import com.twobytes.report.form.NumSaleByEmpReportForm;

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
		
		//2nd query
		/*SELECT CONCAT(emp.name,' ',emp.surname) fullName, t.name type, b.name brand, m.name model, count(p.productID) num
		FROM product p, employee emp, type t, brand b, model m
		WHERE p.installedBy is not null 
		AND p.installedBy = emp.employeeID 
		AND t.typeID = p.typeID
		AND b.brandID = p.brandID
		AND m.modelID = p.modelID
		GROUP BY fullName, t.name, b.name, m.name
		ORDER BY fullName, t.name, b.name, m.name;*/
		
		//1st query
		// SELECT CONCAT(emp.name,' ',emp.surname) fullName, count(p.productID) num FROM product p, employee emp WHERE p.installedBy is not null AND p.installedBy = emp.employeeID GROUP BY fullName;
		
		StringBuilder sql = new StringBuilder();
//		sql.append("SELECT CONCAT(emp.name,' ',emp.surname) fullName, count(p.productID) num " +
//				"FROM product p, employee emp " +
//				"WHERE p.installedBy is not null " +
//				"AND p.installedBy = emp.employeeID ");
		sql.append("SELECT CONCAT(emp.name,' ',emp.surname) fullName, t.name type, b.name brand, m.name model, count(p.productID) num " +
				"FROM product p, employee emp, type t, brand b, model m " +
				"WHERE p.installedBy is not null " +
				"AND p.installedBy = emp.employeeID " +
				"AND t.typeID = p.typeID " +
				"AND b.brandID = p.brandID " +
				"AND m.modelID = p.modelID ");
				
		
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
		
		sql.append("GROUP BY fullName, t.name, b.name, m.name ");
		sql.append("ORDER BY fullName, t.name, b.name, m.name ");
//		sql.append("GROUP BY fullName ");
		
		Query q = sessionFactory.getCurrentSession().createSQLQuery(sql.toString())
		.addScalar("fullName", new StringType())
		.addScalar("type", new StringType())
		.addScalar("brand", new StringType())
		.addScalar("model", new StringType())
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

	@Override
	@SuppressWarnings("unchecked")
	public List<NumSaleByEmpReportForm> numSale(Integer month, Integer year)
			throws Exception {
		
//		select CONCAT(e.name, ' ', e.surname) fullName, IFNULL(n.numNOT,0) numNOT, IFNULL(c.numCOM,0) numCOM, IFNULL(p.numPRN,0) numPRN from employee e left join (select  so.employeeID, CONCAT(e.name,' ',e.surname) fullName, count(so.saleOrderID)  numNOT from saleOrder so, product p, employee e where MONTH(so.saleDate) = 11 and YEAR(so.saleDate) = 2011 and so.productID = p.productID and p.typeID = 'NOT' and so.employeeID = e.employeeID group by fullName) n on e.employeeID = n.employeeID
//		left join (select  so.employeeID, CONCAT(e.name,' ',e.surname) fullName, count(so.saleOrderID)  numCOM from saleOrder so, product p, employee e where MONTH(so.saleDate) = 11 and YEAR(so.saleDate) = 2011 and so.productID = p.productID and p.typeID = 'COM' and so.employeeID = e.employeeID group by fullName) c on e.employeeID = c.employeeID
//		left join (select  so.employeeID, CONCAT(e.name,' ',e.surname) fullName, count(so.saleOrderID)  numPRN from saleOrder so, product p, employee e where MONTH(so.saleDate) = 11 and YEAR(so.saleDate) = 2011 and so.productID = p.productID and p.typeID = 'PRN' and so.employeeID = e.employeeID group by fullName) p on e.employeeID = p.employeeID
//		where e.roleID in (2,4);
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT CONCAT(e.name, ' ', e.surname) fullName, IFNULL(n.numNOT,0) numNOT, IFNULL(c.numCOM,0) numCOM, IFNULL(p.numPRN,0) numPRN FROM employee e LEFT JOIN (SELECT so.employeeID, CONCAT(e.name,' ',e.surname) fullName, count(so.saleOrderID) numNOT FROM saleOrder so, product p, employee e WHERE MONTH(so.saleDate) = :month AND YEAR(so.saleDate) = :year AND so.productID = p.productID AND p.typeID = 'NOT' AND so.employeeID = e.employeeID GROUP BY fullName) n ON e.employeeID = n.employeeID " +
				"LEFT JOIN (SELECT so.employeeID, CONCAT(e.name,' ',e.surname) fullName, COUNT(so.saleOrderID) numCOM FROM saleOrder so, product p, employee e WHERE MONTH(so.saleDate) = :month AND YEAR(so.saleDate) = :year AND so.productID = p.productID AND p.typeID = 'COM' AND so.employeeID = e.employeeID GROUP BY fullName) c ON e.employeeID = c.employeeID " +
				"LEFT JOIN (SELECT so.employeeID, CONCAT(e.name,' ',e.surname) fullName, COUNT(so.saleOrderID) numPRN FROM saleOrder so, product p, employee e WHERE MONTH(so.saleDate) = :month AND YEAR(so.saleDate) = :year AND so.productID = p.productID AND p.typeID = 'PRN' AND so.employeeID = e.employeeID GROUP BY fullName) p ON e.employeeID = p.employeeID " +
				"WHERE e.roleID in (2,4) " +
				"ORDER BY fullName");
		
		Query q = sessionFactory.getCurrentSession().createSQLQuery(sql.toString())
		.addScalar("fullName", new StringType())
		.addScalar("numNOT", new IntegerType())
		.addScalar("numCOM", new IntegerType())
		.addScalar("numPRN", new IntegerType())
		.setResultTransformer(Transformers.aliasToBean(NumSaleByEmpReportForm.class));
		
		q.setInteger("month", month);
		q.setInteger("year", year);
		
		List<NumSaleByEmpReportForm> retList = q.list();
		return retList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> issuePart(String startDate, String endDate, String code,
			Integer rows, Integer page, String orderBy, String orderType)
			throws Exception {
		
//		SELECT so.serviceOrderID, CONCAT(emp.name,' ',emp.surname) fixEmp_name, so.serviceOrderDate, so.returnDate, totalPrice, quantity 
//		FROM serviceorder so, issuepart ip, employee emp
//		WHERE so.status = 'close' 
//		and so.serviceOrderID = ip.serviceOrderID 
//		and emp.employeeID = so.empFix 
//		and ip.code='SITTOOSILGLO'
//		order by so.serviceOrderDate desc
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT so.serviceOrderID, CONCAT(emp.name,' ',emp.surname) fixEmp_name, so.serviceOrderDate, so.returnDate, totalPrice, quantity " + 
					"FROM serviceorder so, issuepart ip, employee emp "+
					"WHERE so.status = 'close' " + 
					"and so.serviceOrderID = ip.serviceOrderID " + 
					"and emp.employeeID = so.empFix "); 
		
		if((null != startDate && !startDate.equals("")) && (null != endDate && !endDate.equals(""))){
			sql.append("and DATE(so.returnDate) between :startDate and :endDate ");
		}else if((null != startDate && !startDate.equals("")) && (null == endDate || endDate.equals(""))){
			sql.append("and DATE(so.returnDate) >= :startDate ");
		}else if((null == startDate || startDate.equals("")) && (null != endDate && !endDate.equals(""))){
			sql.append("and DATE(so.returnDate) <= :endDate ");
		}
		
		if(code != null && !code.equals("")){
			sql.append("and ip.code = :code ");
		}
		sql.append("order by so.serviceOrderDate desc");
		
		Query q = sessionFactory.getCurrentSession().createSQLQuery(sql.toString())
		.addScalar("serviceOrderID", new StringType())
		.addScalar("fixEmp_name", new StringType())
		.addScalar("serviceOrderDate", new DateType())
		.addScalar("returnDate", new DateType())
		.addScalar("totalPrice", new DoubleType())
		.addScalar("quantity", new IntegerType())
		.setResultTransformer(Transformers.aliasToBean(IssuePartReportForm.class))
		.setFirstResult(rows*page - rows).setMaxResults(rows).setFetchSize(rows);
		
		if((null != startDate && !startDate.equals("")) && (null != endDate && !endDate.equals(""))){
			q.setString("startDate", startDate);
			q.setString("endDate", endDate);
		}else if((null != startDate && !startDate.equals("")) && (null == endDate || endDate.equals(""))){
			q.setString("startDate", startDate);
		}else if((null == startDate || startDate.equals("")) && (null != endDate && !endDate.equals(""))){
			q.setString("endDate", endDate);
		}
		if(code != null && !code.equals("")){
			q.setString("code", code);
		}
		
		List<IssuePartReportForm> retList = q.list();
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", retList);
		
		q = sessionFactory.getCurrentSession().createSQLQuery(sql.toString())
		.addScalar("serviceOrderID", new StringType())
		.addScalar("fixEmp_name", new StringType())
		.addScalar("serviceOrderDate", new DateType())
		.addScalar("returnDate", new DateType())
		.addScalar("totalPrice", new DoubleType())
		.addScalar("quantity", new IntegerType())
		.setResultTransformer(Transformers.aliasToBean(IssuePartReportForm.class));
		
		if((null != startDate && !startDate.equals("")) && (null != endDate && !endDate.equals(""))){
			q.setString("startDate", startDate);
			q.setString("endDate", endDate);
		}else if((null != startDate && !startDate.equals("")) && (null == endDate || endDate.equals(""))){
			q.setString("startDate", startDate);
		}else if((null == startDate || startDate.equals("")) && (null != endDate && !endDate.equals(""))){
			q.setString("endDate", endDate);
		}
		if(code != null && !code.equals("")){
			q.setString("code", code);
		}
		
		retList = q.list();
		result.put("maxRows", retList.size());
		
		return result;
	}

}