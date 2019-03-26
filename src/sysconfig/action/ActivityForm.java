/*
 * Created on Jan 28, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package sysconfig.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.TreeMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.validator.ValidatorForm;

import app.AcessControl;

import com.harrier.initializeation.ConnectInit;
/**
 * @author manoj
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ActivityForm extends ValidatorForm{
	Logger Logging = Logger.getLogger(ActivityForm.class);
	private String selectActivityName		=	null;
	private Collection activityCollection	=	null;
	private String operation				=	null;
	private String activityName				=	null;
	private String activityDescription		=	null;
	private String activityCode				=	null;
	private String activity_Code			=	null;
	public String activity_Code_flag		=	null;
	private int flag						=	0;
	
	private Connection connection=null;
	
	private static TreeMap map				=	new TreeMap();
	  Statement stmt;
	  String queryduplicateRoleName=null;
	  ResultSet rst;
	  app.Connect con=ConnectInit.getConnect();
	  int duplicateID=0,duplicateCode=0;	
	
	  /**RESEST ALL FORM FIELDS**/
	  public void reset(ActionMapping mapping,HttpServletRequest request){
	   	this.selectActivityName			=	null;
	  	this.operation					=	null;
	   	this.activityName				=	null;
	  	this.activityDescription		=	null;
	  	this.activityCode				=	null;
	  	this.activity_Code_flag			=	null;
	  }
	
	  /**
	   * VALIDATE FORM DATA
	  * **/
	  public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request)
	  {
	  	ActionErrors errors 		= new ActionErrors();
	    boolean nameEntered 		= false;
	    boolean activityCodeEntered = false;
	   
	 //   Connect con=ConnectInit.getConnect();
	    /***
	     * IF Form Is Submitted
	     * */
	    if(operation!=null && operation.equals("changeFields")){
	    	activity_Code_flag="disable";
	    	return errors;
	    }
	    
	    /**
	     * ELSE VALIDATE THE ERRORS
	     * **/
	    else{
	    	try {
	    			    		
	    		errors.add(null, new ActionError("errors.validation"));
	    		try {
	    			//dbconnect();//Commented by Manoj Adekar for Dynamic connection
				
//	    			Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
	    			if(connection==null)
	    				connection=con.getdbConnection();
	    			/**
					 * Check For The Duplicate Role Name 
					 * */
					
	    			PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("activities_duplicate_activity_name"));
					stmt.setString(1,activityName);
					rst = stmt.executeQuery();
					while(rst.next()){
						duplicateID			=	rst.getInt(1);
						
					}
					rst.close();
					stmt.close();
					if(activityCode!=null){	
						stmt = connection.prepareStatement(ConnectInit.queries.getProperty("activities_duplicate_activity_code"));
						stmt.setString(1,activityCode);
						rst = stmt.executeQuery();
						while(rst.next()){
							duplicateCode			=	rst.getInt(1);
							
						}
						rst.close();
						stmt.close();
					}
					
				} catch (SQLException e1) {	e1.printStackTrace();}
//				Close the Dynamic Connection : Manoj Adekar
				finally {
					try {
						if (connection != null)
							connection.close();
					} catch (Exception ee) {
						Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
					}
				}
				
				
				String duplicateRoleName = (String)map.get(selectActivityName);
				if(duplicateID!=0){
	    			if(!(activityName.equalsIgnoreCase(duplicateRoleName))){
	    				errors.add(null,
								new ActionError("errors.duplicateActivityName"));flag=1;
	    			}
	    			
	    		}
				if(duplicateCode!=0){
	    				errors.add(null,
								new ActionError("errors.duplicateActivitycode"));flag=1;
	    			
	    			
	    		}
	    		
			
	    	
	    	} catch (RuntimeException e) {						
	    		e.printStackTrace();
	    		return errors;
	    	}
	    	
	    	/**
	    	 * IF NO ERRORS ARE PRESENT THEN CLEAR THE ERRORS
	    	 * **/
	    	if(flag==0){
	    		activity_Code_flag="disable";
	    		errors.clear();
	    	}
	    	else{
	    		activity_Code_flag="notDisable";
	    	}
			
		 return errors;
	    
	    }
	 }
	  

	
	/**
	 * @return Returns the activityCode.
	 */
	public String getActivityCode() {
		return activityCode;
	}
	/**
	 * @param activityCode The activityCode to set.
	 */
	public void setActivityCode(String activityCode) {
		if(activityCode!=null){
			this.activityCode = activityCode.trim();
		}
		else
		this.activityCode = activityCode;
	}
	/**
	 * @return Returns the activityDescription.
	 */
	public String getActivityDescription() {
		return activityDescription;
	}
	/**
	 * @param activityDescription The activityDescription to set.
	 */
	public void setActivityDescription(String activityDescription) {
		if(activityDescription!=null){
			this.activityDescription = activityDescription.trim();
		}
		else
		this.activityDescription = activityDescription;
	}
	
	/**
	 * @return Returns the activityName.
	 */
	public String getActivityName() {
		return activityName;
	}
	/**
	 * @param activityName The activityName to set.
	 */
	public void setActivityName(String activityName) {
		if(activityName!=null){
			this.activityName = activityName.trim();
		}
		else
		this.activityName = activityName;
	}
	
	/**
	 * @return Returns the flag.
	 */
	public int getFlag() {
		return flag;
	}
	/**
	 * @param flag The flag to set.
	 */
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	/**
	 * @return Returns the selectActivityName.
	 */
	public String getSelectActivityName() {
		return selectActivityName;
	}
	/**
	 * @param selectActivityName The selectActivityName to set.
	 */
	public void setSelectActivityName(String selectActivityName) {
		this.selectActivityName = selectActivityName;
	}
		
	/**
	 * @return Returns the operation.
	 */
	public String getOperation() {
		return operation;
	}
	/**
	 * @param operation The operation to set.
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	/**
	 * @return Returns the activity_Code.
	 */
	public String getActivity_Code() {
		return activity_Code;
	}
	/**
	 * @param activity_Code The activity_Code to set.
	 */
	public void setActivity_Code(String activity_Code) {
		this.activity_Code = activity_Code;
	}
	
	/**
	 * @return Returns the activity_Code_flag.
	 */
	public String getActivity_Code_flag() {
		return activity_Code_flag;
	}
	/**
	 * @param activity_Code_flag The activity_Code_flag to set.
	 */
	public void setActivity_Code_flag(String activity_Code_flag) {
		this.activity_Code_flag = activity_Code_flag;
	}
	/**
	 * @return Returns the activityCollection.
	 */
	public Collection getActivityCollection() {
		 Vector activity = new Vector(10); 
		 AcessControl asc = ConnectInit.getAcessControl(); 
	//	AcessControl asc=new AcessControl();
		String NotSelected=asc.getLangValues("Masters.NotSelected");
		Logging.debug(" Inside getIndexcollection(): Not Selected ="+NotSelected);
			
		 
		 activity.add(new LabelValueBean(NotSelected,"value0"));
		 activityCollection=null;
			//dbconnect();	//Commented by Manoj Adekar for Dynamic connection
		 		try{
//		 			Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		 			if(connection==null)
		 			{
		 				connection=con.getdbConnection();
		 			}
		 			
		 			PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("activities_select_*_from_activties"));
		 			rst = stmt.executeQuery();
		 				
		 				map.put("value0",NotSelected);
			        	while(rst.next()){
							int count=rst.getInt(1);
							activity.add(new LabelValueBean(rst.getString(2),"value"+count));
							map.put("value"+count,rst.getString(2));
						}	
			        	rst.close();
						stmt.close();
			   	}catch(Exception e){Logging.debug(e);Logging.debug("getActivityName");}
//			  Close the Dynamic Connection : Manoj Adekar
			   	finally {
			   		try {
			   			if (connection != null)
			   				connection.close();
			   		} catch (Exception ee) {
			   			Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
			   		}
			   	}
	        	   		
	        	activityCollection =activity ;
	        	 
		return activityCollection;
	}
	/**
	 * @param activityCollection The activityCollection to set.
	 */
	public void setActivityCollection(Collection activityCollection) {
		this.activityCollection = activityCollection;
	}
	/**
	 * Database Connectivity
	 * */
	//Commented by Manoj Adekar
	/*public void dbconnect(){
		
		try {
  		  	if(app.Connect.con==null)
		{
			con.getConnection();
		}

		} catch (Exception e) {	Logging.debug(e);} 
		
	}*/
}
