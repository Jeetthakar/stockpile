/*
 * Created on Jan 28, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.masters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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
public class RoleActivitiesForm extends ValidatorForm{
	Logger Logging = Logger.getLogger(RoleActivitiesForm.class);
	private String operation						=	null;
	private String selectRName						=	null;
	private Collection roleCollection 				= 	null;
	private Collection activityCollection			=	null;
	private Collection assignActivitiesCollection	=	null;
	private String selectAName						=	null;
	private String selectActivityName				=	null;
	private String formTwoRoleName					=	null;
	private int flag								=	0;
	private String select_rname						=	null;
	Connection connection=null;
    String query;
	ResultSet rst;
	ActionMapping mapping;
	HttpServletRequest request;
//	app.Connect con=new app.Connect();	
	Connect con = ConnectInit.getConnect();
	/**RESEST ALL FORM FIELDS**/
	  public void reset(ActionMapping mapping,HttpServletRequest request){
	  	operation				=	null;
		roleCollection 			= 	null;
		activityCollection		=	null;
		selectAName				=	null;
		selectActivityName		=	null;
		formTwoRoleName			=	null;
		flag					=	0;
	  }
	  
    /**
     * Validate Errors
     * */
	  public ActionErrors validate(ActionMapping mapping,
		    HttpServletRequest request)
	  {
	  	ActionErrors errors 		= new ActionErrors();
	  	return errors;
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
	 *Get All The Role Names 
	 * **/

    public Collection getRoleCollection() {
    	Vector roles = new Vector(10);
    //	AcessControl asc=new AcessControl();
    	AcessControl asc = ConnectInit.getAcessControl();
    	String NotSelected=asc.getLangValues("Masters.NotSelected");
    	Logging.debug(" Inside getIndexcollection(): Not Selected ="+NotSelected);
    	roles.add(new LabelValueBean(NotSelected,"value0"));
    	connection=null;
    	try{
    		if(connection==null)
    			connection=con.getdbConnection();
         	try{
         		PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("roleActivities_select_*_from_role"));	
         		rst = stmt.executeQuery();		
         		while(rst.next()){
         			int count=rst.getInt(1);
         			roles.add(new LabelValueBean(rst.getString(2),"value"+count));
          		}	
         		rst.close();
         		stmt.close();
        	 }catch(Exception e){
        	 	Logging.error("Error  :"+e.getMessage());
        	 }
        }
    	finally{
    		try{if(connection!=null)
    			connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
    	roleCollection = roles;
    	return roleCollection;
    }

    public void setRoleCollection(Collection roleCollection) {
        this.roleCollection = roleCollection;
        
    }
    
    
    /**
  	 *Get All The Activity Names 
  	 * **/

   public Collection getActivityCollection() {
      Vector activities = new Vector(100,100);
      connection=null;
      try{
      	if(connection==null)
			connection=con.getdbConnection();
        try{
        	PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("roleActivities_select_*_from_activity"));	
        	rst = stmt.executeQuery();	
        	while(rst.next()){
        		int count=rst.getInt(1);
        		activities.add(new LabelValueBean(rst.getString(2),"value"+count));
        	}	
        	rst.close();
        	stmt.close();
        }catch(Exception e){
        	Logging.error("Error  :"+e.getMessage());
        	Logging.debug("getActivityName");
        }
      }
      finally{
      	try{if(connection!=null)
      		connection.close();
      	}catch(Exception ee){
      		Logging.error(" Error : Unable to close Connection "+ee.getMessage());
      	}
      }
      activityCollection = activities;
      return activityCollection;
   }

   public void setActivityCollection(Collection roleCollection) {
       this.roleCollection = roleCollection;
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
	 * @return Returns the selectAName.
	 */
	public String getSelectAName() {
		return selectAName;
	}
	/**
	 * @param selectAName The selectAName to set.
	 */
	public void setSelectAName(String selectAName) {
		this.selectAName = selectAName;
	}
	
	/**
	 * @return Returns the formTwoRoleName.
	 */
	public String getFormTwoRoleName() {
		return formTwoRoleName;
	}
	/**
	 * @param formTwoRoleName The formTwoRoleName to set.
	 */
	public void setFormTwoRoleName(String formTwoRoleName) {
		this.formTwoRoleName = formTwoRoleName;
	}
	
	private String[] ac_selections=null;
	
	/**
	 * @return Returns the ac_selections.
	 */
	public String[] getAc_selections() {
		return ac_selections;
	}
	/**
	 * @param ac_selections The ac_selections to set.
	 */
	public void setAc_selections(String[] ac_selections) {
		this.ac_selections = ac_selections;
		
	}
	private String[] Rem=null;
	
	/**
	 * @return Returns the rem.
	 */
	public String[] getRem() {
		return Rem;
	}
	/**
	 * @param rem The rem to set.
	 */
	public void setRem(String[] rem) {
		this.Rem = rem;
	
	}
	private String[] rhsSelection=null;
	
	
	/**
	 * @return Returns the rhsSelection.
	 */
	public String[] getRhsSelection() {
		return rhsSelection;
	}
	/**
	 * @param rhsSelection The rhsSelection to set.
	 */
	public void setRhsSelection(String[] rhsSelection) {
		this.rhsSelection = rhsSelection;
	}
	/**
	 * @return Returns the selectRName.
	 */
	public String getSelectRName() {
		return this.selectRName;
	}
	/**
	 * @param selectRName The selectRName to set.
	 */
	public void setSelectRName(String selectRName) {
		this.selectRName = selectRName;
	}
	/**
	 * @return Returns the assignActivitiesCollection.
	 */
	public Collection getAssignActivitiesCollection() {
		Vector selActivities=new Vector(20);
		if(selectRName!=null){
			connection=null;
			try{
	    		if(connection==null)
	    			connection=con.getdbConnection();
	    		try{
					String sRName			=	selectRName.split("e")[1];
					int num					=	Integer.parseInt(sRName);
					PreparedStatement stmt1 = connection.prepareStatement(ConnectInit.queries.getProperty("roleActivities_getAssignedActivities"));
		         	stmt1.setInt(1,num);
		         	rst=stmt1.executeQuery();	
					while(rst.next()){
			        		selActivities.add(new LabelValueBean(rst.getString(2),"value"+rst.getInt(1)));
			        }	
					rst.close();
					stmt1.close();
			        assignActivitiesCollection=selActivities;
			     }catch(Exception e){
			     	Logging.error("Error  :"+e.getMessage());
			     }
			}
			finally{
	    		try{if(connection!=null)
	    			connection.close();
				}catch(Exception ee){
					Logging.error(" Error : Unable to close Connection "+ee.getMessage());
				}
			}
		}
		else{
			assignActivitiesCollection=selActivities;
		}
		return assignActivitiesCollection;
	}
	/**
	 * @param assignActivitiesCollection The assignActivitiesCollection to set.
	 */
	public void setAssignActivitiesCollection(
			Collection assignActivitiesCollection) {
		this.assignActivitiesCollection = assignActivitiesCollection;
	}
	
	/**
	 * @return Returns the select_rname.
	 */
	public String getSelect_rname() {
		return select_rname;
	}
	/**
	 * @param select_rname The select_rname to set.
	 */
	public void setSelect_rname(String select_rname) {
		this.select_rname = select_rname;
	}
}
