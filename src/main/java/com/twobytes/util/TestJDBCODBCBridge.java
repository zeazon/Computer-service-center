package com.twobytes.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestJDBCODBCBridge {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String data = "jdbc:odbc:Express-vfp";
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection conn = DriverManager.getConnection(data);
//			Connection conn = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft FoxPro VFP Driver (*.dbf)};DBQ=d:/งาน/2bytes/DB ExpressI/xp54.dbf","","");
//			conn = DriverManager.getConnection("jdbc:odbc:Driver=MicroSoft Access Driver (*.mdb);DBQ="+System.getProperty("user.dir")+"/db.mdb","","");
			Statement st = conn.createStatement();
//			ResultSet rec = st.executeQuery("SELECT CUSNAM FROM schema.ARMAS");
			ResultSet rec = st.executeQuery("SELECT CUSNAM FROM ARMAS");
			while(rec.next()){
				System.out.println(rec.getString("CUSNAM"));
			}
			st.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
