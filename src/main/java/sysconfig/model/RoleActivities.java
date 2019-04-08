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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import app.Connect;
import java.io.*;

import org.apache.log4j.Logger;

import com.harrier.initializeation.ConnectInit;



/**
 * @author manoj
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RoleActivities {
	Logger Logging = Logger.getLogger(RoleActivities.class);
	String query;
	public static Connection cmp=null;
	ResultSet rst;
	PreparedStatement stmt;
	String c;
	Vector roles = new Vector(10,10);
	int roleId;
	String sRName;
	String des,name;
	String[] act;
	Connect con=ConnectInit.getConnect();
	public static void main(String[] args) {
	}
	
	/**
	 * Adding Entries In Table role_activities
	 * */
	public void AddRoleActivity(String selectRoleName,String activities ){
		/* Get The Activities*/	
		dbconnect();
		StringTokenizer st = new StringTokenizer(activities,",");		
		/* Delete Entries From Table */
		try{
			
			/*Getting the id*/
			sRName=selectRoleName.split("e")[1];
			int num=Integer.parseInt(sRName);
			cmp.commit();
			cmp.setAutoCommit(false);
			if(!(sRName.equals("0"))){
				PreparedStatement stmt = cmp.prepareStatement(ConnectInit.queries.getProperty("roleActivities_delete_from_role_activities"));	
				stmt.setInt(1,num);
			    stmt.executeUpdate();	
				stmt.close();
			}
		}catch(Exception e){
				Logging.debug(e);
				try {
					cmp.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					Logging.debug(e1);
				}
				return;
			}
	
		/*Getting the id*/
		sRName=selectRoleName.split("e")[1];
		int num=Integer.parseInt(sRName);
		/* Add Entries In Table*/
		if(!(sRName.equals("0"))){
			try {
				stmt = cmp.prepareStatement(ConnectInit.queries.getProperty("roleActivities_insert"));
					while (st.hasMoreTokens()) {
		     	     	/*Put The Activity_ids One By One In Table*/
					 	String activityId=st.nextToken();
		          		String actID=activityId.split("e")[1];
			    		int numactID=Integer.parseInt(actID);
						stmt.setInt(1,numactID);
						stmt.setInt(2,num);
					    stmt.addBatch();
					} 
					stmt.executeBatch();	
					stmt.close();
					cmp.setAutoCommit(true);
					cmp.close();
			}catch (SQLException e1) {
		     		//e1.printStackTrace();
				Logging.debug(e1);
					try {
						cmp.rollback();
						cmp.setAutoCommit(true);
						cmp.close();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						//e2.printStackTrace();
						Logging.debug(e2);
					}
		     	
		     	}
	     
		}
	}
	

	public void dbconnect(){
		
		try{
			cmp=con.getConnectionForTransaction();
		

		} catch (Exception e) {	Logging.debug(e);} 
		
		
	}
	
	
}
