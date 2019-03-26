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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import app.Connect;
import java.sql.*;
import java.util.*;
import java.io.*;

import org.apache.log4j.Logger;

import com.harrier.initializeation.ConnectInit;



/**
 * @author manoj
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Company {
	Logger Logging = Logger.getLogger(Company.class);
	String query;
	ResultSet rst;
//	app.Connect con=new app.Connect();
	Connect con = ConnectInit.getConnect();
	PreparedStatement stmt1;
	public static Connection cmp=null;
	public static void main(String[] args) {
	}
	
	/**
	 * Adding Entries In Table class_company
	 * */
	public void addCompany(int companyId,int[] selectedChkBox ){
		
		dbconnect();
		
		/* Delete Entries From Table class_company*/
		try{
			
			cmp.commit();
			cmp.setAutoCommit(false);
			PreparedStatement stmt = cmp.prepareStatement(ConnectInit.queries.getProperty("classCompany_delete"));	
			stmt.setInt(1,companyId);
			stmt.executeUpdate();	
			stmt.close();
		}catch(Exception e){
			Logging.debug(e);
			try {
				cmp.close();
				return;
			} catch (SQLException e3) {
				//e3.printStackTrace();
				Logging.debug(e3);
			return;}
		
		}
		
		if(selectedChkBox==null){
			try {
				cmp.setAutoCommit(true);
				cmp.close();
			} catch (SQLException e2) {	
				//e2.printStackTrace();
				Logging.debug(e2);}
			return;
		}
		
		try {
			
			stmt1 = cmp.prepareStatement(ConnectInit.queries.getProperty("classCompany_insert"));	
			/* Add Entries In Table class_company*/
			for(int i=0;i<selectedChkBox.length;i++) {
		     	
		     	/*Put The class_ids One By One In Table*/
			 	int classId=selectedChkBox[i];
		     	stmt1.setInt(1,classId);
				stmt1.setInt(2,companyId);
				stmt1.addBatch();
				
		     }
			stmt1.executeBatch();
			stmt1.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			Logging.debug(e1);
			try {
				cmp.rollback();
				cmp.setAutoCommit(true);
				cmp.close();
				return;
			} catch (SQLException e2) {
				//e2.printStackTrace();	
			Logging.debug(e2);
			return;}
		}
		try {
			cmp.setAutoCommit(true);
			cmp.close();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			//e2.printStackTrace();
			Logging.debug(e2);
		}
		
		
	}
	
	public void dbconnect(){
		
		try{
			cmp=con.getConnectionForTransaction();
		

		} catch (Exception e) {	Logging.debug(e);return;} 
		
		
	}
	
	
}
