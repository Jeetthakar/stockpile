/*
 * Created on Jan 28, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.masters;

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
import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * @author naresh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ActivityForm extends ValidatorForm {
	Logger Logging = Logger.getLogger(ActivityForm.class);
	private String selectActivityName		=	null;
	private String selectActivity	=	null,defaultVal=null;
	private Collection activityCollection	=	null;
	private Collection selectActivityCollection	=	null;
	private String operation				=	null;
	private String activityName				=	null;
	private String activityDescription		=	null;
	private String activityCode				=	null;
	private String activity_Code			=	null;
	public String activity_Code_flag		=	null;
	private int flag						=	0;
	private static TreeMap map				=	new TreeMap();
	private static int ckeck_id;
	Statement stmt;
	String new1,update;
	String queryduplicateRoleName=null;
	ResultSet rst;
	int duplicateID=0,duplicateCode=0;	
	Connect con =ConnectInit.getConnect();
	Connection connection=null;
	  /**RESEST ALL FORM FIELDS**/
	public void reset(ActionMapping mapping,HttpServletRequest request){
	   	this.selectActivityName			=	null;
	  	this.operation					=	null;
	   	this.activityName				=	null;
	  	this.activityDescription		=	null;
	  	this.activityCode				=	null;
	  	this.activity_Code_flag			=	null;
	  }
	
	 public Collection getSelectActivityCollection() {
		Vector vec = new Vector();
		vec.add(new LabelValueBean("Not Selected","0"));
		connection=null;
		try{
			if(connection==null)
				connection=con.getdbConnection();
			try {
				PreparedStatement stmt =connection.prepareStatement(ConnectInit.queries.getProperty("activities_select_*_from_activties"));
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
				vec.add(new LabelValueBean(rst.getString(2),rst.getString(1)));
				}
				rst.close();
				stmt.close();
				selectActivityCollection = vec;
				return selectActivityCollection;
			} catch (SQLException e) {
				Logging.debug("Error  :"+e.getMessage());
			//	e.printStackTrace();
			}	
		} catch(Exception e){
			Logging.error(" Error : "+e.getMessage());
		}
		finally{
			try{if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
		return selectActivityCollection;
	}
	/**
	 * @param selectCollection The selectCollection to set.
	 */
	public void setSelectActivityCollection(Collection selectActivityCollection) {
		this.selectActivityCollection = selectActivityCollection;
	}
	public String getSelectActivity() {
		connection=null;
		try{
			if(connection==null)
				connection=con.getdbConnection();
			if(defaultVal!=null && defaultVal.equals("yes")){
			try {
					PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("select_system_config"));
					ResultSet rst = stmt.executeQuery();
					while(rst.next()){
						 selectActivity=rst.getString(19);
					}
					rst.close();
					stmt.close();
			} catch (SQLException e) {
				Logging.debug("Error  :"+e.getMessage());
			//	e.printStackTrace();
			}
		}	
		} catch(Exception e){
			Logging.error(" Error : "+e.getMessage());
		}
		finally{
			try{if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
		return selectActivity;
	}
	/**
	 * @param select The select to set.
	 */
	public void setSelectActivity(String selectActivity) {
		Logging.debug("Inside setSelectIndex selectIndex = "+ selectActivity);
		this.selectActivity = selectActivity;
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
	    String newvalue = getNew1();
		String updatevalue = getUpdate();
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
	    		if ((selectActivityName == null || (selectActivityName != null && selectActivityName.equals("value0"))) && updatevalue!=null) {
	    			setUpdate(null);
					errors.add("DuplicateEntry", new ActionError(
					"Error.message.selectfromlist"));
					return errors;
				}
	    		connection=null;
	    		if ((newvalue != null && updatevalue==null) || ( updatevalue!= null && newvalue==null)) {	
	    		try {
	    			if(connection==null)
	    				connection=con.getdbConnection();
	    				
	    				boolean flag11=true,flag1=true;
	    				try{
	    					
					/**
					 * Check For The Duplicate Role Name 
					 * */
					PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("activities_duplicate_activity_name"));
					stmt.setString(1,activityName);
					rst = stmt.executeQuery();
					while(rst.next()){
						duplicateID	= rst.getInt(1);
					}
					rst.close();
					stmt.close();
					if(activityCode!=null){	
						stmt = connection.prepareStatement(ConnectInit.queries.getProperty("activities_duplicate_activity_code"));
						stmt.setString(1,activityCode);
						rst = stmt.executeQuery();
						while(rst.next()){
							duplicateCode	=	rst.getInt(1);
						}
						rst.close();
						stmt.close();
					}
				} catch (SQLException e1) {	
					Logging.error("Error  :  "+e1);
				//	e1.printStackTrace();
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
	    	 
	    		}catch (RuntimeException e) {						
	        		Logging.error("Error  :"+e.getMessage());
	        		e.printStackTrace();
	        		return errors;
	        	}
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
	    	}finally{
				try{if(connection!=null)
					connection.close();
				}catch(Exception ee){
					Logging.error(" Error : Unable to close Connection "+ee.getMessage());
				}
			}}
	    	    }
	    		
	    		/*if(update != null)
    			{
    				
    				boolean flag11=true,flag1=true;
    				try{
    					
				*//**
				 * Check For The Duplicate Role Name 
				 * *//*
				PreparedStatement stmt = connection.prepareStatement(con.queries.getProperty("activities_duplicate_activity_name"));
				stmt.setString(1,activityName);
				rst = stmt.executeQuery();
				while(rst.next()){
					duplicateID	= rst.getInt(1);
				}
				rst.close();
				stmt.close();
				if(activityCode!=null){	
					stmt = connection.prepareStatement(con.queries.getProperty("activities_duplicate_activity_code"));
					stmt.setString(1,activityCode);
					rst = stmt.executeQuery();
					while(rst.next()){
						duplicateCode	=	rst.getInt(1);
					}
					rst.close();
					stmt.close();
				}
			} catch (SQLException e1) {	
				Logging.getError("Error  :  "+e1);
				e1.printStackTrace();
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
    	} 
    		}catch (RuntimeException e) {						
    		Logging.getError("Error  :"+e.getMessage());
    		e.printStackTrace();
    		return errors;
    	}
	    	*//**
	    	 * IF NO ERRORS ARE PRESENT THEN CLEAR THE ERRORS
	    	 * **//*
	    	if(flag==0){
	    		activity_Code_flag="disable";
	    		errors.clear();
	    	}
	    	else{
	    		activity_Code_flag="notDisable";
	    	}
	    	return errors;
	    }*/
	    	
	    
	
	/* public ActionErrors validate(ActionMapping mapping,HttpServletRequest request)
		 {
			String newvalue = getNew1();
			String updatevalue = getUpdate();
			Logging.getDebug("Inside validate.......... ");
			if(newvalue != null)
			{
				ActionErrors errors = new ActionErrors();
				boolean flag=true,flag1=true;
				try{
					int check = checkData(cmp_name);
					if(check == 1)
					{
						errors.add("DuplicateEntry",new ActionError("Error.message.DuplicateEntry"));
						setNew1(null);
					}
				}catch(Exception e){
				errors.add(null,new ActionError("Error.message.msg"));
			 }	
			return errors;
			}
			if(updatevalue != null)
			{
				Logging.getDebug("Error in Validation U..... "+updatevalue);
				ActionErrors errors = new ActionErrors();
				boolean flag=true,flag1=true;
				
				try{
					int idname_t = getId();
					if(idname_t == 0)
					{
						setUpdate(null);
						setId(0);
						errors.add("DuplicateEntry",new ActionError("Error.message.selectfromlist"));			
					}
					if(!getCmp_name().equalsIgnoreCase(getCmp_name_check()))
					{
					try{
						int check = checkData(cmp_name.trim());
						if(check == 1)
						{
							errors.add("DuplicateEntry",new ActionError("Error.message.DuplicateEntry"));
							setNew1(null);
							setUpdate(null);
							setId(0);
						}
					}catch(Exception e){}
					}
				}catch(Exception e){
					errors.add(null,new ActionError("Error.message.msg"));
				 }		
				return errors;
			}
			return null;
		 }
		
		public int checkData(String name_check)
		{
			PreparedStatement checkcon;
			ResultSet rs1;
			int ans=0;
			String nm1 = null;
			connection=null;
			try
			{
				if(connection==null)
					connection=con.getdbConnection();
				try
				{
					checkcon = connection.prepareStatement(connect.queries.getProperty("check_company_name"));
					checkcon.setString(1,name_check);
					rs1 = checkcon.executeQuery();
					while(rs1.next())
					{
						nm1 = rs1.getString(1);
					}
					if(nm1.equalsIgnoreCase(name_check))
					{
						ans = 1;
					}
					rs1.close();
					checkcon.close();
				}catch(Exception e)
				{
					Logging.getDebug("Error check() :"+e);
				}
			}
			finally{
				try{if(connection!=null)
					connection.close();
				}catch(Exception ee){
					Logging.getError(" Error : Unable to close Connection "+ee.getMessage());
				}
			}
			return ans;
		}*/
	/**
	 * @return Returns the new1.
	 */
	public String getNew1() {
		return new1;
	}
	/**
	 * @return Returns the ckeck_id.
	 */
	public int getCkeck_id() {
		return ckeck_id;
	}
	/**
	 * @param ckeck_id The ckeck_id to set.
	 */
	public void setCkeck_id(int ckeck_id) {
		ActivityForm.ckeck_id = ckeck_id;
	}
	/**
	 * @param new1 The new1 to set.
	 */
	public void setNew1(String new1) {
		this.new1 = new1;
	}
	/**
	 * @return Returns the update.
	 */
	public String getUpdate() {
		return update;
	}
	/**
	 * @param update The update to set.
	 */
	public void setUpdate(String update) {
		this.update = update;
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
	//	AcessControl asc=new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		String NotSelected=asc.getLangValues("Masters.NotSelected");
		Logging.debug(" Inside getIndexcollection(): Not Selected ="+NotSelected);
		activity.add(new LabelValueBean(NotSelected,"value0"));
		activityCollection=null;
		connection=null;
		try{
			if(connection==null)
				connection=con.getdbConnection();
				try{
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
			   	}catch(Exception e){
			   		Logging.error(e+"");
			   		Logging.debug("getActivityName");
			   	}
		}
		catch(Exception e){
	   		Logging.error(e+"");
	   		Logging.debug("getActivityName");
	   	}
	   	finally{
			try{if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
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
	
	
}
