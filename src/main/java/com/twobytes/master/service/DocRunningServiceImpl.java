package com.twobytes.master.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twobytes.master.dao.DocRunningDAO;
import com.twobytes.model.DocRunning;

@Service
public class DocRunningServiceImpl implements DocRunningService {

	@Autowired
	private DocRunningDAO docRunningDAO;
	
	@Override
	@Transactional
	public DocRunning getDoc(String document) {
		return docRunningDAO.getDoc(document);
	}

	@Override
	@Transactional
	public DocRunning getDocByYearMonth(String document, Integer year,
			Integer month) {
		return docRunningDAO.getDocByYearMonth(document, year, month);
	}

	@Override
	@Transactional
	public boolean createNewDocRunning(DocRunning docRunning) {
		return docRunningDAO.createNewDocRunning(docRunning);
	}

	@Override
	@Transactional
	public boolean updateDocRunning(DocRunning docRunning) {
		return docRunningDAO.updateDocRunning(docRunning);
	}

	@Override
	@Transactional
	public DocRunning getDocByYear(String document, Integer year) {
		return docRunningDAO.getDocByYear(document, year);
	}

	@Override
	@Transactional
	public DocRunning getDocByYearMonthDate(String document, Integer year,
			Integer month, Integer date) {
		return docRunningDAO.getDocByYearMonthDate(document, year, month, date);
	}
}
