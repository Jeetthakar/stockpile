/*
 * Created on Mar 22, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package sysconfig.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * @author manoj
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class industryClassification {
	Logger Logging = Logger.getLogger(industryClassification.class);
	public String description=null;
	public String shortName=null;
	public String name=null;
	public int icmId;
Connect con=ConnectInit.getConnect();
	String query;
	ResultSet rst;
	public int roleAdded=0;
	public int roleUpdated=0;
	private Connection connection=null;
	
	/**
	 * Make the entries in industry_classification_master table
	 * */
	public void AddICM(int id,String name,String shortName,String description){
		//dbconnect();
		 
		try {
			if(connection==null)
				connection=con.getdbConnection();
				
			/**Get the Max industry_classification_Id from industry_classification_master table*/
		 	PreparedStatement stmt = connection.prepareStatement("select nextval('industry_classification_id')");  
		 		
			rst = stmt.executeQuery();	
			while(rst.next()){
				icmId=rst.getInt(1);
				
			}
			rst.close();
			stmt.close();
			/**insert*/
			if(id==0){
				stmt = connection.prepareStatement(ConnectInit.queries.getProperty("icm_insert"));
				stmt.setInt(1,icmId);
				stmt.setString(2,name.trim());
				stmt.setString(3,shortName.trim());
				
				if(description==null || description.length()==0){
					stmt.setString(4,null);
				}
				else{
					stmt.setString(4,description.trim());	
				}
				stmt.executeUpdate();	
				
				stmt.close();
				roleAdded=1;
				roleUpdated=0;
				
			}
			/**update*/
			else{
				stmt = connection.prepareStatement(ConnectInit.queries.getProperty("icm_update"));
				
				stmt.setString(1,name.trim());
				stmt.setString(2,shortName.trim());
				
				if(description==null || description.length()==0){
					stmt.setString(3,null);
				}
				else{
					stmt.setString(3,description.trim());	
				}
				stmt.setInt(4,id);
				stmt.executeUpdate();
				
				stmt.close();
				roleAdded=0;
				roleUpdated=1;
				
			}
		 	
		 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Logging.debug(e);
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
	}
	
	
	
	/**
	 *Get all entries from industry_classification_master table
	 * **/
	
	public void getAllFieldsValue(int id1){
		//dbconnect();
		try{
	           
			int num=id1;
			if(connection==null)
				connection=con.getdbConnection();
			PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("icm_get_description"));	
			 	stmt.setInt(1,num);
	            rst = stmt.executeQuery();
	           	while(rst.next()){
	        		description		=	rst.getString(4);
	        		shortName		=	rst.getString(3);
	        		name			=	rst.getString(2);
				}
	           	rst.close();
				stmt.close();
	        	
	   	}catch(Exception e){Logging.debug(e);}
//	  Close the Dynamic Connection : Manoj Adekar
	   	finally {
	   		try {
	   			if (connection != null)
	   				connection.close();
	   		} catch (Exception ee) {
	   		Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
	   		}
	   	}
		
	}
	
	
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the shortName.
	 */
	public String getShortName() {
		return shortName;
	}
	/**
	 * @param shortName The shortName to set.
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	/**
	 * Database Connectivity
	 * */
	/*
	 * *public void dbconnect(){
		
		try {
  		  	if(app.Connect.con==null)
		{
			con.getConnection();
		}

		} catch (Exception e) {	Logging.debug(e);} 
		
	}
	**/
	

}
