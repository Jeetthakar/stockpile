/*
 * Created on Mar 24, 2005
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
 * @author manoj
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class classes {
	Logger Logging = Logger.getLogger(classes.class);
	app.Connect con=ConnectInit.getConnect();
	String query;
	ResultSet rst;
	Connection connection=null;
	public int classId=0;
	
	public int addClasses(String newClassName,String description,int LId,String PId,String classCode,String shortName){
	
//		dbconnect();	//Commented by Manoj Adekar for Dynamic connection
 		try{
// 			Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
 			if(connection==null)
 			{
 				connection=con.getdbConnection();
 			}
 
		/**Get the MAX class_Id from sequence */
	 	try {
			PreparedStatement stmt = connection.prepareStatement("select nextval('class_id')");      	
			rst = stmt.executeQuery();	
			while(rst.next()){
				classId=rst.getInt(1);
				
			}
			rst.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
			Logging.debug(e);
		}
		
		/**To insert a new class in class table */
		//try {
			PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("classes_insert"));
			stmt.setInt(1,classId);
			if(newClassName!=null ){
				if(newClassName.trim().length()!=0){
					stmt.setString(2,newClassName.trim());
				}
				else{
					stmt.setString(2,null);
				}
			}
			else{
				stmt.setString(2,null);
			}
			if(description!=null ){
				if(description.trim().length()!=0){
					stmt.setString(3,description.trim());
				}
				else{
					stmt.setString(3,null);
				}
				
			}
			else{
				stmt.setString(3,null);
			}
			stmt.setInt(4,LId);
			
			if(PId.equals("0")){
				stmt.setString(5,null);
			}
			else{
				stmt.setString(5,PId);
			}
			if(classCode!=null ){
				if(classCode.trim().length()!=0){
					stmt.setString(6,classCode.trim());
				}
				else{
					stmt.setString(6,null);
				}
				
			}
			else{
				stmt.setString(6,null);
			}
			if(shortName!=null){
				if(shortName.trim().length()!=0){
					stmt.setString(7,shortName.trim());
				}
				else{
					stmt.setString(7,null);
				}
				
			}
			else{
				stmt.setString(7,null);
			}
			
			
			stmt.executeUpdate();
			stmt.close();
			return 1;
			
		
//		Close the Dynamic Connection : Manoj Adekar
 		} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
 			Logging.debug(e);
			return 0;
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
	
	public int  updateClasses(int CId,String newClassName,String description,String classCode,String shortName){
		/**To insert a new class in datbase table class*/
		try {
			if(connection==null)
				connection=con.getdbConnection();
			
			PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("classes_update"));
			
			if(newClassName!=null ){
				if(newClassName.trim().length()!=0){
					stmt.setString(1,newClassName.trim());
				}
				else{
					stmt.setString(1,null);
				}
			}
			else{
				stmt.setString(1,null);
			}
			if(description!=null ){
				if(description.trim().length()!=0){
					stmt.setString(2,description.trim());
				}
				else{
					stmt.setString(2,null);
				}
				
			}
			else{
				stmt.setString(2,null);
			}
			
			if(classCode!=null ){
				if(classCode.trim().length()!=0){
					stmt.setString(3,classCode.trim());
				}
				else{
					stmt.setString(3,null);
				}
				
			}
			else{
				stmt.setString(3,null);
			}
			if(shortName!=null){
				if(shortName.trim().length()!=0){
					stmt.setString(4,shortName.trim());
				}
				else{
					stmt.setString(4,null);
				}
				
			}
			else{
				stmt.setString(4,null);
			}
			stmt.setInt(5,CId);
			
			stmt.executeUpdate();	
		
			stmt.close();
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Logging.debug(e);
			return 0;
		}

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

		} catch (Exception e) {	System.out.println(e);} 
		
	}*/


}
