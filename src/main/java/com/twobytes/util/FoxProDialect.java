package com.twobytes.util;

import org.hibernate.dialect.Dialect;
import org.hibernate.sql.CaseFragment;

public class FoxProDialect extends Dialect {
	
	public boolean supportsForUpdate(){
		return false;
	}
	
	public CaseFragment createCaseFragment(){
		return new FoxProCaseFragment();
	}
}
