/*
 * Created on Mar 22, 2005
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
public class industryClassificationForm extends ValidatorForm{
	Logger Logging = Logger.getLogger(industryClassificationForm.class);
	private String selectICName			=	null;
	private String icName				=	null;
	private String shortName			=	null;
	private String description			=	null;
	private String operation			=	null;
	Connection connection=null;
	private Collection icCollection		=	null;
	public static TreeMap map			=	new TreeMap();
	Statement stmt;
	String query;
	ResultSet rst;
//	app.Connect con=new app.Connect();
	Connect con = ConnectInit.getConnect();
	int duplicateID;
	/**RESEST ALL FORM FIELDS**/
	  public void reset(ActionMapping mapping,HttpServletRequest request){
	  	selectICName		=	null;
		icName				=	null;
		shortName			=	null;
		description			=	null;
		operation			=	null;
		icCollection		=	null;
	 }
	  /**
	   * VALIDATE FORM DATA
	  * **/
	  public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request)
	  {
	  	ActionErrors errors = new ActionErrors();
	    boolean nameEntered = false;
	    errors.add(null, new ActionError("errors.validation"));
	    int flag=0;
	    connection=null;
	    if(operation.equals("Save")){
	    	try {
	    		try{
	    			if(connection==null)
	    				connection=con.getdbConnection();
					/**
					 * Check For The Duplicate Industry Classification Name 
					 * */
					PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("icm_duplicateName"));
					stmt.setString(1,icName);
					rst = stmt.executeQuery();
					while(rst.next()){
						duplicateID			=	rst.getInt(1);
					}
					rst.close();
					stmt.close();
				} catch (SQLException e1) {	
					Logging.error("Error  :"+e1.getMessage());
				//	e1.printStackTrace();
					}
				}
	    	finally{
				try{if(connection!=null)
					connection.close();
				}catch(Exception ee){
					Logging.error(" Error : Unable to close Connection "+ee.getMessage());
				}
			}
	    	String duplicateRoleName 	= (String)map.get(selectICName);
			if(duplicateID!=0){
	    		if(!(icName.equalsIgnoreCase(duplicateRoleName.trim()))){
	    			errors.add(null,new ActionError("errors.duplicateIcName"));flag=1;
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
	 * @return Returns the icCollection.
	 */
	public Collection getIcCollection() {
		 Vector activity = new Vector(10);
	//	 AcessControl asc=new AcessControl();
		 AcessControl asc = ConnectInit.getAcessControl();
		 String NotSelected=asc.getLangValues("Masters.NotSelected");
		 activity.add(new LabelValueBean(NotSelected,"value0"));
		 icCollection=null;
		 connection=null;
		 try{
		 	try{
				if(connection==null)
					connection=con.getdbConnection();	
		 		PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("icm_select_*_from_industry_classification_master"));
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
			 }
		}
		finally{
			try{if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		} 
	    icCollection =activity ;
		return icCollection;
	}
	
	/**
	 * @return Returns the selectICName.
	 */
	public String getSelectICName() {
		return selectICName;
	}
	/**
	 * @param selectICName The selectICName to set.
	 */
	public void setSelectICName(String selectICName) {
		this.selectICName = selectICName;
	}
	/**
	 * @param icCollection The icCollection to set.
	 */
	public void setIcCollection(Collection icCollection) {
		this.icCollection = icCollection;
	}
	/**
	 * @return Returns the icName.
	 */
	public String getIcName() {
		return icName;
	}
	/**
	 * @param icName The icName to set.
	 */
	public void setIcName(String icName) {
		if(icName!=null){
			this.icName = icName.trim();
		}
		else
			this.icName = icName;
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
		if(description!=null){
			this.description = description.trim();
		}
		else
			this.description = description;
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
		if(shortName!=null){
			this.shortName = shortName.trim();
		}
		else
			this.shortName = shortName;
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
