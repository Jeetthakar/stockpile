/*
 * Created on Jul 4, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package sysconfig.model;
/**
 * @author naresh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Vector;

import org.apache.log4j.Logger;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

public class Activity {
	Logger Logging = Logger.getLogger(Activity.class);
	String query;
	Connection connection = null;
	ResultSet rst;
	String c;
	String new1,update;
	Vector activity = new Vector(10,10);
	Hashtable list = new Hashtable();
	public int activityId,returnId;
	String sAName;
	public String des,name,actCode,code;
	app.Connect con=ConnectInit.getConnect();
	public int activityAdded=3,activityUpdated=3;
	public static void main(String[] args) {
	}
	/**Adding Entries In Database
	 * */
	public int AddActivity(String selectActivityName,String activityName,String activityDescription,String activityCode,String new1,String update){
			dbconnect();
		try {
			this.new1=new1;
			this.update=update;
			PreparedStatement stmt = connection.prepareStatement("select nextval('activities_id')"); 
			rst = stmt.executeQuery();	
			while(rst.next()){
				activityId=rst.getInt(1);
			}
			rst.close();
			stmt.close();
			/**Getting the id*/
			sAName=selectActivityName.split("e")[1];
			int num=Integer.parseInt(sAName);
			
			//ActivityForm.ckeck_id = activityId ;
			//if(sAName.equals("0")){
			if(new1!=null && update==null)	{
			stmt = connection.prepareStatement(ConnectInit.queries.getProperty("activities_insert"));
				stmt.setInt(1,activityId);
				if(activityName!=null){
					stmt.setString(2,activityName.trim());
				}
				else{
					stmt.setString(2,activityName);
				}
				stmt.setString(2,activityName.trim());
				if(activityDescription==null || activityDescription.equals("")){
					stmt.setString(3,null);
				}
				else{
					stmt.setString(3,activityDescription);
				}
				if(activityCode!=null){
					stmt.setString(4,activityCode.trim());
				}
				else{
					stmt.setString(4,activityCode);
				}
				stmt.executeUpdate();
				stmt.close();
				activityAdded=1;
				activityUpdated=0;
				returnId=activityId;
			}
			if(update!=null && new1==null){
				stmt = connection.prepareStatement(ConnectInit.queries.getProperty("activities_update"));
				if(activityName!=null){
					stmt.setString(1,activityName.trim());
				}
				else{
					stmt.setString(1,activityName);
				}
				if(activityDescription!=null ){
					if(activityDescription.trim().length()!=0){
						stmt.setString(2,activityDescription.trim());
					}
					else{
						stmt.setString(2,null);
					}
				}
				else{
					stmt.setString(2,null);
				}
				stmt.setInt(3,num);
				stmt.executeUpdate();
				stmt.close();
				activityAdded=0;
				activityUpdated=1;
				returnId=activityId;
			}
		}
		catch (SQLException e) {	
		//e.printStackTrace();
			Logging.debug(e);
		}
		return returnId;		
	}
	public int AddActivity(String selectActivityName,String activityName,String activityDescription,String activityCode){
		dbconnect();
	try {
		/*this.new1=new1;
		this.update=update;*/
		PreparedStatement stmt = connection.prepareStatement("select nextval('activities_id')"); 
		rst = stmt.executeQuery();	
		while(rst.next()){
			activityId=rst.getInt(1);
		}
		rst.close();
		stmt.close();
		/**Getting the id*/
		sAName=selectActivityName.split("e")[1];
		int num=Integer.parseInt(sAName);
		if(sAName.equals("0")){
		/*if(new1!=null)	{*/
		stmt = Connect.con.prepareStatement(ConnectInit.queries.getProperty("activities_insert"));
			stmt.setInt(1,activityId);
			if(activityName!=null){
				stmt.setString(2,activityName.trim());
			}
			else{
				stmt.setString(2,activityName);
			}
			stmt.setString(2,activityName.trim());
			if(activityDescription==null || activityDescription.equals("")){
				stmt.setString(3,null);
			}
			else{
				stmt.setString(3,activityDescription);
			}
			if(activityCode!=null){
				stmt.setString(4,activityCode.trim());
			}
			else{
				stmt.setString(4,activityCode);
			}
			stmt.executeUpdate();
			stmt.close();
			activityAdded=1;
			activityUpdated=0;
			returnId=activityId;
		}
		else{
			stmt = Connect.con.prepareStatement(ConnectInit.queries.getProperty("activities_update"));
			if(activityName!=null){
				stmt.setString(1,activityName.trim());
			}
			else{
				stmt.setString(1,activityName);
			}
			if(activityDescription!=null ){
				if(activityDescription.trim().length()!=0){
					stmt.setString(2,activityDescription.trim());
				}
				else{
					stmt.setString(2,null);
				}
			}
			else{
				stmt.setString(2,null);
			}
			stmt.setInt(3,num);
			stmt.executeUpdate();
			stmt.close();
			activityAdded=0;
			activityUpdated=1;
			returnId=activityId;
		}
	}
	catch (SQLException e) {	
		//e.printStackTrace();
		Logging.debug(e);
	}
	return returnId;		
}	
	/**
	 *Get Description Code Of Activity Which Is Called From Action Class To Populate In The Form
	 * **/
	public void getnameDescriptionCode(int id1){
		dbconnect();
		try{
			int num=id1;
	        PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("activities_get_activity_name_description_code_of_activity"));
			stmt.setInt(1,num);
	        rst = stmt.executeQuery();
			while(rst.next()){
	        	name	=	rst.getString(1);
	        	des		=	rst.getString(2);
	        	code	=	rst.getString(3);
			}	
			rst.close();
			stmt.close();
	    }catch(Exception e){Logging.debug(e);Logging.debug("getDescription");}
	}
	
	/**
	 * Database Connectivity
	 * */
	public void dbconnect(){
		try {
  		  	if(connection==null)
		{
			connection = con.getdbConnection();
		}
		} catch (Exception e) {	Logging.debug(e);} 
	}
}
