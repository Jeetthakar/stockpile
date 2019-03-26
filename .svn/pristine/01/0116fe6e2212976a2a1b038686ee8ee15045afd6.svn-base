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
public class RolesForm extends ValidatorForm{
	Logger Logging = Logger.getLogger(RolesForm.class);
	private String selectRoleName		=	null;
	private Collection roleCollection   =   null;
	private String roleName				=	null;
	private String roleDescription		=	null;
	private String operation			=	null;
	public static TreeMap map			=	new TreeMap();
	private int flag=0;
	int duplicateID=0;
	Connection connection=null;
	Statement stmt;
	String new1,update;
	String query,duplicateRoleName=null;
	ResultSet rst;
//	app.Connect con=new app.Connect();	
	Connect con = ConnectInit.getConnect();
	/**RESEST ALL FORM FIELDS**/
	  public void reset(ActionMapping mapping,HttpServletRequest request){
	 
	  	this.selectRoleName			=	null;
	  	this.roleCollection   		=   null;
	  }
	
	  /**
	   * VALIDATE FORM DATA
	  * **/
	  public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request)
	  {
	  	String newvalue = getNew1();
		String updatevalue = getUpdate();
	  	ActionErrors errors = new ActionErrors();
	    boolean nameEntered = false;
	    connection=null;
	    errors.add(null, new ActionError("errors.validation"));
	    if ((selectRoleName == null || (selectRoleName != null && selectRoleName.equals("value0"))) && updatevalue!=null) {
			setUpdate(null);
			errors.add("DuplicateEntry", new ActionError(
			"Error.message.selectfromlist"));
			return errors;
		}
	    if(operation.equals("Save")){
	    	try {
	    		try{
	    			if(connection==null)
	    				connection=con.getdbConnection();
					/**
					 * Check For The Duplicate Role Name 
					 * */
					PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("roles_duplicate_role_name"));
					stmt.setString(1,roleName);
					rst = stmt.executeQuery();
					while(rst.next()){
						duplicateID			=	rst.getInt(1);
					}
					rst.close();
					stmt.close();
				} catch (SQLException e1) {
					Logging.error("Error  :"+e1.getMessage());
					//e1.printStackTrace();
				}
			}
	    	finally{
				try{if(connection!=null)
					connection.close();
				}catch(Exception ee){
					Logging.error(" Error : Unable to close Connection "+ee.getMessage());
				}
			}	
			String duplicateRoleName = (String)map.get(selectRoleName);
			if(duplicateID!=0){
	    		if(!(roleName.equalsIgnoreCase(duplicateRoleName))){
	    			errors.add(null,new ActionError("errors.duplicateRoleName"));flag=1;
				}
	    	}
	    }	
	    /**
	     * IF NO ERRORS ARE PRESENT THEN CLEAR THE ERRORS
	     * **/
	    if(flag==0){
	    	errors.clear();
	    }
		return errors;
	}
	 
	/**
	 * @return Returns the roleCollection.
	 */
	public Collection getRoleCollection() {
		Vector activity = new Vector(10); 
//	    AcessControl asc=new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		String NotSelected=asc.getLangValues("Masters.NotSelected");
		Logging.debug(" Inside getIndexcollection(): Not Selected ="+NotSelected);
	    activity.add(new LabelValueBean(NotSelected,"value0"));
		roleCollection=null;
		connection=null;
		try{
			if(connection==null)
				connection=con.getdbConnection();
		 	try{
		 		PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("roleActivities_select_*_from_role"));
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
			  	Logging.error("Error  :"+e.getMessage());
			   	Logging.debug("getActivityName");}
			}
			finally{
				try{if(connection!=null)
					connection.close();
				}catch(Exception ee){
					Logging.error(" Error : Unable to close Connection "+ee.getMessage());
				}
			}   		
		  	roleCollection =activity ;
		   	return roleCollection;
	}
	/**
	 * @return Returns the new1.
	 */
	public String getNew1() {
		return new1;
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
	 * @param roleCollection The roleCollection to set.
	 */
	public void setRoleCollection(Collection roleCollection) {
		this.roleCollection = roleCollection;
	}
	/**
	 * @return Returns the selectRoleName.
	 */
	public String getSelectRoleName() {
		return selectRoleName;
	}
	/**
	 * @param selectRoleName The selectRoleName to set.
	 */
	public void setSelectRoleName(String selectRoleName) {
		this.selectRoleName = selectRoleName;
	}
	
	/**
	 * @return Returns the roleDescription.
	 */
	public String getRoleDescription() {
		return roleDescription;
	}
	/**
	 * @param roleDescription The roleDescription to set.
	 */
	public void setRoleDescription(String roleDescription) {
		if(roleDescription!=null){
			this.roleDescription = roleDescription.trim();
		}
		else
			this.roleDescription = roleDescription;
	}
	/**
	 * @return Returns the roleName.
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * @param roleName The roleName to set.
	 */
	public void setRoleName(String roleName) {
		if(roleName!=null){
			this.roleName = roleName.trim();
		}
		else
			this.roleName = roleName;
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
}
	