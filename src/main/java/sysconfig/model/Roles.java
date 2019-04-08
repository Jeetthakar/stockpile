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
import harrier.income.com.masters.RolesForm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import app.Connect;

import com.harrier.initializeation.ConnectInit;
public class Roles {
	Logger Logging = Logger.getLogger(Roles.class);
	String query;
	//Connection Changed from Static to local on 07-08-06
	Connection connection = null;
	ResultSet rst;
	String c;
	public int roleId;
	String sRName;
	String new1,update;
	public String des,name;
	public int roleAdded,roleUpdated;
	Connect con=ConnectInit.getConnect();
	
	public static void main(String[] args) {
	}
	
	
	/**Adding Entries In Database
	 * */
	public void AddRole(String selectRoleName,String roleName,String roleDescription,String new1,String update){
			dbconnect();
		try {
			RolesForm form1= new RolesForm();
			
			this.new1= new1;
			Logging.debug("New1 issssssssss"+new1);
			
			this.update= update;
			Logging.debug("update issssssssss"+update);
			PreparedStatement stmt =connection.prepareStatement("select nextval('role_id')");  
			rst = stmt.executeQuery();	
			while(rst.next()){
				roleId=rst.getInt(1);
				roleId++;
			}
			rst.close();
			stmt.close();
			/**Getting the id*/
			sRName=selectRoleName.split("e")[1];
			int num=Integer.parseInt(sRName);
			/**insert*/
			if(new1!=null){
			//if(sRName.equals("0")){
				stmt = connection.prepareStatement(ConnectInit.queries.getProperty("roles_insert"));
				stmt.setInt(1,roleId);
				stmt.setString(2,roleName.trim());
				if(roleDescription==null || roleDescription.length()==0){
					stmt.setString(3,null);
				}
				else{
					stmt.setString(3,roleDescription.trim());	
				}
				stmt.executeUpdate();	
				stmt.close();
				roleAdded=1;
				roleUpdated=0;
			//}
			}
			/**update*/
			if(update!=null){
					stmt = connection.prepareStatement(ConnectInit.queries.getProperty("roles_update"));
					stmt.setString(1,roleName.trim());
					if(roleDescription==null || roleDescription.equals("")){
						stmt.setString(2,null);
					}
					else{
						stmt.setString(2,roleDescription.trim());	
					}
					stmt.setInt(3,num);
					stmt.executeUpdate();
					stmt.close();
					roleAdded=0;
					roleUpdated=1;
			}
		} catch (SQLException e) {	
			//e.printStackTrace();
			Logging.debug(e);
			}
	}
	
	/**
	 *Get Description Of Role Names 
	 * **/
	public String getDescription(int id1){
		dbconnect();
		try{
	            int num=id1;
	            PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("roles_get_description"));	
			 	stmt.setInt(1,num);
	            rst = stmt.executeQuery();
	          
	        	while(rst.next()){
	        		des		=	rst.getString(1);
	        		name	=	rst.getString(3);
				}	
	        	rst.close();
				stmt.close();
	    }catch(Exception e){Logging.debug(e);}
		return des;
	}
	
	/**
	 *Get Selected Role Name 
	 * **/
	public String getRoleName(){
			return this.name.trim();
	}
	
	/**
	 *Get  Role Id 
	 * **/
	public int getRoleId(){
			return this.roleId;
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
