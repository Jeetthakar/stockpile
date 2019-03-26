/*
 * Created on Jul 05, 2006
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

import com.harrier.initializeation.ConnectInit;
/**
 * @author naresh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class classificationLevel {
	Logger Logging = Logger.getLogger(classificationLevel.class);
	public String newClassLevel;
	public int levelNumber,clId,maxLevelNo;
	public String shortName;
	public String description;
	app.Connect con=ConnectInit.getConnect();
	String query;
	ResultSet rst;
	String new1,update;
	public int roleAdded=0;
	public int roleUpdated=0;
	private Connection connection=null;
	/**
	 * Method to male databse entries
	 * **/
	public void AddICM(int numIndex,String className,int intICnum, String short_Name,String des,String radioButton,int afterLevel,String new1, String update){
//		dbconnect();	//Commented by Manoj Adekar for Dynamic connection
 		try{
// 			Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
 			if(connection==null)
 			{
 				connection=con.getdbConnection();
 			}
 
		 try {
		 	/**Get the Max level_Id from classification_level table*/
		 	PreparedStatement stmt = connection.prepareStatement("select nextval('level_id')");  
		 	rst = stmt.executeQuery();	
			while(rst.next()){
				clId=rst.getInt(1);
				
			}
			
			this.new1=new1;
			this.update=update;
			/**insert*/
			//if(numIndex==0){
			if(new1!=null){
				connection.commit();
				connection.setAutoCommit(false);
				if(radioButton!=null){
					
					if(radioButton.trim().equals("Bottom")){
						/**Get the Max level_no from classification_level table respective to industry_classification_id*/
						
						stmt = connection.prepareStatement(ConnectInit.queries.getProperty("classLevel_select_Max_level_no"));	
					 	stmt.setInt(1,intICnum);
						rst = stmt.executeQuery();	
						while(rst.next()){
							maxLevelNo=rst.getInt(1);
							maxLevelNo++;
						}
						rst.close();
						stmt.close();
					}
					else if(radioButton.trim().equals("Top")){
						
						stmt = connection.prepareStatement(ConnectInit.queries.getProperty("classLevel_update_top"));
						stmt.setInt(1,intICnum);
						stmt.executeUpdate();
						maxLevelNo=1;
						stmt.close();
					}
					else if(radioButton.trim().equals("After")){
						
						stmt = connection.prepareStatement(ConnectInit.queries.getProperty("classLevel_update_after"));
						stmt.setInt(1,afterLevel);
						stmt.setInt(2,intICnum);
						stmt.executeUpdate();
						stmt.close();
						maxLevelNo=afterLevel+1;
					}
					
					stmt = connection.prepareStatement(ConnectInit.queries.getProperty("classLevel_insert"));
					stmt.setInt(1,clId);
					if(className!=null ){
						stmt.setString(2,className.trim());
					}
					else{
						stmt.setString(2,className);
					}
					stmt.setInt(3,maxLevelNo);
					stmt.setInt(4,intICnum);
					if(short_Name!=null ){
						stmt.setString(5,short_Name.trim());
					}
					else{
						stmt.setString(5,short_Name);
					}
					
					if(des==null || des.length()==0){
						stmt.setString(6,null);
					}
					else{
						stmt.setString(6,des.trim());
					}
					stmt.executeUpdate();
					stmt.close();
				}
				roleAdded=1;
				roleUpdated=0;
				connection.setAutoCommit(true);
			}
			/**update*/
			if(update!=null){
				stmt = connection.prepareStatement(ConnectInit.queries.getProperty("classLevel_update"));
				if(className!=null ){
					stmt.setString(1,className.trim());
				}
				else{
					stmt.setString(1,className);
				}
				if(short_Name!=null ){
					stmt.setString(2,short_Name.trim());
				}
				else{
					stmt.setString(2,short_Name);
				}
				
				if(des==null || des.length()==0){
					stmt.setString(3,null);
				}
				else{
					stmt.setString(3,des.trim());
				}
				stmt.setInt(4,numIndex);
				stmt.executeUpdate();
				stmt.close();
				roleAdded=0;
				roleUpdated=1;
				connection.setAutoCommit(true);
			}
		 	
		 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
			Logging.debug(e);
			try {
				connection.rollback();
				connection.setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
				Logging.debug(e);
			}
		}
//		Close the Dynamic Connection : Manoj Adekar
 		} catch (Exception e) {
 		Logging.debug("Error :" + e);
 		} 

// 		Close the Dynamic Connection : Manoj Adekar
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
			//Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
			if(connection==null)
			{
				connection=con.getdbConnection();
			 }   
			int num=id1;
	            PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("classLevel_get_all_description"));	
			 	stmt.setInt(1,num);
	            rst = stmt.executeQuery();
	           	while(rst.next()){
	           		newClassLevel	=	rst.getString(2);
	           		levelNumber		=	rst.getInt(3);
	           		shortName		=	rst.getString(5);
	           		description		=	rst.getString(6);
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
	 * @return Returns the levelNumber.
	 */
	public int getLevelNumber() {
		return levelNumber;
	}
	/**
	 * @param levelNumber The levelNumber to set.
	 */
	public void setLevelNumber(int levelNumber) {
		this.levelNumber = levelNumber;
	}
	/**
	 * @return Returns the newClassLevel.
	 */
	public String getNewClassLevel() {
		return newClassLevel;
	}
	/**
	 * @param newClassLevel The newClassLevel to set.
	 */
	public void setNewClassLevel(String newClassLevel) {
		this.newClassLevel = newClassLevel;
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
/*	public void dbconnect(){
		
		try {
  		  	if(app.Connect.con==null)
		{
			con.getConnection();
		}

		} catch (Exception e) {	Logging.debug(e);} 
		
	}*/

}
