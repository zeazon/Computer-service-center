package com.twobytes.master.service;

import com.twobytes.model.DocRunning;

public interface DocRunningService {
	public DocRunning getDoc(String document);
	public DocRunning getDocByYear(String document, Integer year);
	public DocRunning getDocByYearMonth(String document, Integer year, Integer month);
	public DocRunning getDocByYearMonthDate(String document, Integer year, Integer month, Integer date);
	public boolean createNewDocRunning(DocRunning docRunning);
	public boolean updateDocRunning(DocRunning docRunning);
}
