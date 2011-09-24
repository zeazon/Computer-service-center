package com.twobytes.util;

import java.io.Serializable;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.CallbackException;
import org.hibernate.EntityMode;
import org.hibernate.Interceptor;
import org.hibernate.Transaction;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;

public class FoxProInterceptor implements Interceptor, Serializable {

	static protected Log log = LogFactory.getLog(FoxProInterceptor.class);
	
	@Override
	public void afterTransactionBegin(Transaction arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterTransactionCompletion(Transaction arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeTransactionCompletion(Transaction arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public int[] findDirty(Object entity, Serializable id, Object[] currentState,
			Object[] previousState, String[] propertyNames, Type[] types) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getEntity(String arg0, Serializable arg1)
			throws CallbackException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEntityName(Object arg0) throws CallbackException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object instantiate(String arg0, EntityMode arg1, Serializable arg2)
			throws CallbackException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Boolean isTransient(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCollectionRecreate(Object arg0, Serializable arg1)
			throws CallbackException {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCollectionRemove(Object arg0, Serializable arg1)
			throws CallbackException {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCollectionUpdate(Object arg0, Serializable arg1)
			throws CallbackException {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDelete(Object arg0, Serializable arg1, Object[] arg2,
			String[] arg3, Type[] arg4) throws CallbackException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onFlushDirty(Object arg0, Serializable arg1, Object[] arg2,
			Object[] arg3, String[] arg4, Type[] arg5) throws CallbackException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onLoad(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) throws CallbackException {
		if(types!=null)
		for (int i = 0; i < types.length; i++) {
			if(types[i] instanceof StringType){
				if(state[i] !=null ){ 
					state[i] =""+state[i].toString().trim();
				}
			}
		}
		return false;
	}
	
	@Override
	public String onPrepareStatement(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onSave(Object arg0, Serializable arg1, Object[] arg2,
			String[] arg3, Type[] arg4) throws CallbackException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void postFlush(Iterator arg0) throws CallbackException {
		// TODO Auto-generated method stub

	}

	@Override
	public void preFlush(Iterator arg0) throws CallbackException {
		// TODO Auto-generated method stub

	}

	public Object instantiate(Class clazz, Serializable id) throws CallbackException {
		return null;
	}
	
	public Boolean isUnsaved(Object entity) {
		return null;
	}

//	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
//	}

//	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types)
//	      throws CallbackException {
//		return false;
//	}

//	public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
//		if(types!=null)
//			for (int i = 0; i < types.length; i++) {
//				if(types[i] instanceof StringType){
//					if(state[i] !=null ){ 
//						state[i] =""+state[i].toString().trim();
//					}
//				}
//			}
//		return false;
//	}

//	   public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
//	      return false;
//	   }

//	   public void postFlush(Iterator entities) throws CallbackException {
//	  }

//	   public void preFlush(Iterator entities) throws CallbackException {
//	   }
}
