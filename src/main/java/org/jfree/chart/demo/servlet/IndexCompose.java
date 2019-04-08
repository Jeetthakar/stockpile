/*
 * Created on Sep 14, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.jfree.chart.demo.servlet;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import app.Connect;

import com.harrier.initializeation.ConnectInit;
/**
 * @author W
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class IndexCompose {
	Logger Logging = Logger.getLogger(IndexCompose.class);
	Vector vector_clientlist, vector_tabledata;

	java.sql.ResultSet rst1, rst;

	Object obj[][], obj1[][];

	HttpSession sessionData;
	private Connection connection=null; 

	/**
	 * @return Returns the vector.
	 */

	/**
	 * @return Returns the vector_clientlist.
	 */
	public Vector getVector_clientlist() {
		return vector_clientlist;
	}

	/**
	 * @param vector_clientlist
	 *            The vector_clientlist to set.
	 */
	/*
	 * public void setSessionData(HttpSession session){ sessionData = session; }
	 */

	public HttpSession getSessionData() {
		//ListIterator i=vector_clientlist.iterator();
		return sessionData;
	}

	public void setVector_clientlist(Vector v) {
		//new app.Connect().getConnection();
		//Logging.debug("vivek in indexcompose.java");
		Connect con=ConnectInit.getConnect();
//		Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		try{
		if(connection==null){
			connection=con.getdbConnection();
		}
		rst = con.getClientList("index_list");
		int i = 0;
		try {
			while (rst.next()) {
				obj[i][0] = rst.getString(1);
				obj[i][1] = rst.getString(2);
				vector_clientlist.add(i, obj[i]);
				i++;
			}
		} catch (SQLException sqlexp) {
			Logging.debug(sqlexp);
		}
//		Close the Dynamic Connection : Manoj Adekar
		con.closeDynaCon();
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} 

//		Close the Dynamic Connection : Manoj Adekar
		finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
			}
		}
		sessionData.setAttribute("result", vector_clientlist);
	}
	

	/**
	 * @return Returns the vector_tabledata.
	 */
	public Vector getVector_tabledata() {
		return vector_tabledata;
	}

	/**
	 * @param vector_tabledata
	 *            The vector_tabledata to set.
	 */
	public void setVector_tabledata(String indexid) {
		//new app.Connect().getConnection();
		Connect con= ConnectInit.getConnect();
//		Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		try{
		if(connection==null)
		{
			connection=con.getdbConnection();
		}
		rst = con.returnResult("stock_weightage_latest", indexid);
		int i = 0;
		try {
			while (rst.next()) {
				obj[i][0] = rst.getString(1);
				obj[i][1] = rst.getString(2);
				vector_tabledata.add(i, obj[i]);
				i++;
			}
		} catch (SQLException sqlexp) {
			Logging.debug(sqlexp);
		}
		
		//Close the Dynamic Connection : Manoj Adekar
		con.closeDynaCon();
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} 
		
		//Close the Dynamic Connection : Manoj Adekar
		finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
			}
		}

	}
}