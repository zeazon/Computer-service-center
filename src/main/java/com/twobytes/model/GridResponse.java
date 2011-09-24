package com.twobytes.model;

import java.util.List;

/**
 * A simple POJO that maps to the JSON structure of a JqGrid.
 * <p>
 * The property names of this POJO must match the property names of your JqGrid's jsonReader.
 * @see <a href="http://www.trirand.com/jqgridwiki/doku.php?id=wiki:retrieving_data#json_data">JSON Data</a>
 * </p>
 */
public class GridResponse {
	/**
	 * Current page of the query
	 */
	private String page;
	
	/**
	 * Total pages for the query
	 */
	private String total;
	
	/**
	 * Total number of records for the query
	 */
	private String records;
	
	/**
	 * An array that contains the actual objects
	 */
	private List rows;
	
	public GridResponse() {
	}
	
	public String getPage() {
		return page;
	}
	
	public void setPage(String page) {
		this.page = page;
	}
	
	public String getTotal() {
		return total;
	}
	
	public void setTotal(String total) {
		this.total = total;
	}
	
	public String getRecords() {
		return records;
	}
	
	public void setRecords(String records) {
		this.records = records;
	}
	
	public List getRows() {
		return rows;
	}
	
	public void setRows(List rows) {
		this.rows = rows;
	}
}
