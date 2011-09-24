package com.twobytes.util;

import java.util.Iterator;
import java.util.Map;

import org.hibernate.sql.CaseFragment;

public class FoxProCaseFragment extends CaseFragment {

	@Override
	public String toFragmentString() {
		StringBuffer buf = new StringBuffer(cases.size() * 15 + 10);
		StringBuffer buf2 = new StringBuffer(cases.size());
		
		Iterator iter = cases.entrySet().iterator();
		while (iter.hasNext()){
			Map.Entry me = (Map.Entry) iter.next();
			buf.append(" iif( NOT ISNULL(")
			   .append(me.getKey())
			   .append(") , ")
			   .append(me.getValue())
			   .append(", ");
			buf2.append(")");
		}
		
		buf.append(".NULL.");
		buf.append(buf2);
		if (returnColumnName!=null){
			buf.append(" as ")
			   .append(returnColumnName);
		}
		return buf.toString();
	}

}
