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
import java.util.Collection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.validator.ValidatorForm;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * @author manoj
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UserRolesForm extends ValidatorForm{
	Logger Logging = Logger.getLogger(UserRolesForm.class);
	private String operation				=	null;
	private String selectUName				=	null;
	private String selectRName				=	null;
	private Collection userCollection 		= 	null;
	private Collection roleCollection       = 	null;
	private Collection assignRolesCollection  = 	null;
	private String formTwoRoleName			=	null;
	private String[] ac_selections			=	null;
	private String[] Rem					=	null;
	private int flag						=	0;
	
    String query;
	ResultSet rst;
	
	ActionMapping mapping;
	HttpServletRequest request;
	app.Connect con=ConnectInit.getConnect();
	
	/**RESEST ALL FORM FIELDS**/
	  public void reset(ActionMapping mapping,HttpServletRequest request){
	  	operation				=	null;
		selectUName				=	null;
		selectRName				=	null;
		userCollection 			= 	null;
		formTwoRoleName			=	null;
		Rem						=	null;
		flag					=	0;
	   
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
	 * @return Returns the selectUName.
	 */
	public String getSelectUName() {
		return this.selectUName;
	}
	/**
	 * @param selectUName The selectUName to set.
	 */
	public void setSelectUName(String selectUName) {
		this.selectUName = selectUName;
	}
	
	  /**
	 *Get All The Role Names 
	 * **/

    public Collection getUserCollection() {
        if (userCollection == null) {
            Vector roles = new Vector(10);
            //dbconnect();	//Commented by Manoj Adekar for Dynamic connection
            Connection connection=null;
	 		try{
	 			//Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
	 			if(connection==null)
	 			{
	 				connection=con.getdbConnection();
	 			}
         		try{
         					PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("userRoles_select_*_from_users"));	
         					rst = stmt.executeQuery();	
         			       	roles.add(new LabelValueBean("Not Selected","value0"));
        	        	while(rst.next()){
        					int count=rst.getInt(1);
        					roles.add(new LabelValueBean(rst.getString(2),"value"+count));
          				}	
        	   	}catch(Exception e){Logging.debug(e);}
        	   	userCollection  = roles;
        	   		return userCollection ;
        	   		
        	   		//Close the Dynamic Connection : Manoj Adekar
	 			} catch (Exception e) {
	 				Logging.debug("Error :" + e);
	 			} 

	 			//Close the Dynamic Connection : Manoj Adekar
	 			finally {
	 				try {
	 					if (connection != null)
	 						connection.close();
	 				} catch (Exception ee) {
	 					Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
	 				}
	 			}
          }
	 		

        return (userCollection );
    }

    public void setUserCollection(Collection userCollection) {
        this.userCollection  = userCollection;
        Logging.debug("Role Collection :"+userCollection);
    }
 
	/**
	 * @return Returns the selectRName.
	 */
	public String getSelectRName() {
		return selectRName;
	}
	/**
	 * @param selectRName The selectRName to set.
	 */
	public void setSelectRName(String selectRName) {
		this.selectRName = selectRName;
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

	

    /**
	 * Database Connectivity
	 * */
//	public void dbconnect(){
		
//		try {
	//		if(app.Connect.con==null){
		//	con.getConnection();
		//}
	//	} catch (Exception e) {	Logging.debug(e);} 
		
//	}
	
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
	
	
	/**
	 * @return Returns the roleCollection.
	 */
	public Collection getRoleCollection() {
		Vector roles = new Vector(10);
		
		//dbconnect();	//Commented by Manoj Adekar for Dynamic connection
        Connection connection=null;
        try{
 			//Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
 			if(connection==null)
 			{
 				connection=con.getdbConnection();
 			}

		
 		try{
 				PreparedStatement stmt = Connect.con.prepareStatement(ConnectInit.queries.getProperty("roles_select_*_from_roles"));	
 			 	rst = stmt.executeQuery();	
 			   	while(rst.next()){
					int count=rst.getInt(1);
					roles.add(new LabelValueBean(rst.getString(2),"value"+count));
					
				}	
	   	}catch(Exception e){Logging.debug(e);}
	   	
	   	//Close the Dynamic Connection : Manoj Adekar
 		} catch (Exception e) {
 			Logging.debug("Error :" + e);
 		} 

 		//Close the Dynamic Connection : Manoj Adekar
 		finally {
 			try {
 				if (connection != null)
 					connection.close();
 			} catch (Exception ee) {
 				Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
 			}
 		}
	   	roleCollection=roles;
		return roleCollection;
	}
	/**
	 * @param roleCollection The roleCollection to set.
	 */
	public void setRoleCollection(Collection roleCollection) {
		this.roleCollection = roleCollection;
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
	 * @return Returns the assignRolesCollection.
	 */
	public Collection getAssignRolesCollection() {
		Vector selActivities=new Vector(20);
		if(selectUName	!=null){
			
				try{
					
					String sRName			=	selectUName.split("e")[1];
					int num					=	Integer.parseInt(sRName);
					PreparedStatement stmt1 = Connect.con.prepareStatement(ConnectInit.queries.getProperty("userRoles_get_assigned_roles"));
	         		stmt1.setInt(1,num);
	         		rst=stmt1.executeQuery();	
					while(rst.next()){
		        			selActivities.add(new LabelValueBean(rst.getString(2),"value"+rst.getInt(1)));
		        	}	
		        	assignRolesCollection=selActivities;
		        
				}catch(Exception e){Logging.debug(e);}
		}
		else{
			assignRolesCollection=selActivities;
		}
		return assignRolesCollection;
	}
	/**
	 * @param assignRolesCollection The assignRolesCollection to set.
	 */
	public void setAssignRolesCollection(Collection assignRolesCollection) {
		this.assignRolesCollection = assignRolesCollection;
	}
}
