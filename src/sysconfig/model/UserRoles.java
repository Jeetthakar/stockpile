/*
 * Created on Feb 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package sysconfig.model;

/**
 * @author manoj
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import org.apache.log4j.Logger;

import com.harrier.initializeation.ConnectInit;

import app.Connect;

/**
 * @author manoj
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UserRoles {
	Logger Logging = Logger.getLogger(UserRoles.class);
	String query;
	ResultSet rst;
	String sUName;
	TreeMap roles = new TreeMap();
	Connect con=ConnectInit.getConnect();	
	int num;
	private Connection connection=null;
	public static void main(String[] args) {
	}
	/**
	 * Adding Entries In Table user_roles
	 * */
	public void userRole(String selectUserName,String roles ){
		/* Get The Activities*/	
		//dbconnect();
		
		
		StringTokenizer st = new StringTokenizer(roles,",");
		
		/* Delete Entries From Table */
		try{
			//Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
			if(connection==null)
 			{
 				connection=con.getdbConnection();
 			}
			/*Getting the id*/
			sUName=selectUserName.split("e")[1];
		    num=Integer.parseInt(sUName);
		    connection.commit();
		    connection.setAutoCommit(false);
			if(!(sUName.equals("0"))){
				PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("userRoles_delete"));	
				stmt.setInt(1,num);
			    stmt.executeUpdate();	
				
			}
		}catch(Exception e){Logging.debug(e);}
	
		/* Add Entries In Table*/
		while (st.hasMoreTokens()) {
	     	
	     	/*Put The Activity_ids One By One In Table*/
	       	String roleId=st.nextToken();
	    	String splitRole=roleId.split("e")[1];
	    	int intRoleId=Integer.parseInt(splitRole);
	       
	     	try {
				
	     		
				if(!(sUName.equals("0"))){
					try {
						PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("userRoles_insert"));	
						stmt.setInt(1,num);
						stmt.setInt(2,intRoleId);
					    stmt.executeUpdate();	
					    connection.setAutoCommit(true);
					} catch (RuntimeException e1) {
						//e1.printStackTrace();
						Logging.debug(e1);
						connection.rollback();
						connection.setAutoCommit(true);
							}
			
				}
			
	     	} catch (SQLException e) {
	     		//e.printStackTrace();
				Logging.debug(e);
				}
//	     	Close the Dynamic Connection : Manoj Adekar
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
		
	
	
		
/*	public void dbconnect(){
		
		try {
  		  	if(app.Connect.con==null)
		{
			con.getConnection();
		}

		} catch (Exception e) {	Logging.debug(e);} 
		
	}*/
	
	
}
