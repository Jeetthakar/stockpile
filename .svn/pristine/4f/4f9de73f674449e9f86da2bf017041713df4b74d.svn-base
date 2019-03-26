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
public class classificationLevelForm extends ValidatorForm{
	Logger Logging = Logger.getLogger(classificationLevelForm.class);
	private String selectICName			=	null;
	private Collection icCollection		=	null;
	public static TreeMap map			=	new TreeMap();
	public static TreeMap levelMap		=	new TreeMap();
	public static Vector activity 		=	null;
	public static Vector af				=   new Vector();	
	private String selectClassLevel     =	null;
	private String newClassLevel		=	null;
	private String shortName			=	null;
	private String operation 		    =	null;
	private Collection levelCollection  =	null;
	private Collection afterCollection  =	null;
	private String afterLevel			=	null;
	private String radioButton			=	null;
	private int levelNumber				=	0;
	private String description			=	null;
	Connection connection=null;
	Statement stmt;
	String new1,update;
	String query,duplicateicName=null,icID1,clLevelId="0";
	ResultSet rst;
	int duplicateID,duplicateNumber,intICID,duplicateClass;
//	app.Connect con=new app.Connect();	
	Connect con = ConnectInit.getConnect();
	String duplicateRoleName1="",duplicateNumber1="",duplicateRoleName="";
	
	/**RESEST ALL FORM FIELDS**/
	  public void reset(ActionMapping mapping,HttpServletRequest request){
	  	selectICName			=	null;
   		icCollection			=	null;
   		selectClassLevel     	=	null;
   		newClassLevel			=	null;
   		shortName				=	null;
   		operation 		    	=	null;
   		levelCollection  		=	null;
   		levelNumber				=	0;
   		description				=	null;
   		duplicateID				=	0;
	 }
	 
	 /**
	  * VALIDATE FORM DATA
	  * **/
	  public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request)
	  {
	  	ActionErrors errors = new ActionErrors();
	    boolean nameEntered = false;
	    int flag=0;
	    if(operation.equals("Save")){
	    	try {
	    		try{
	    			if(connection==null)
	    				connection=con.getdbConnection();
					
	    			/**
	    			 * Check the existing of class in class table if user wants to insert classification level in top or between 
	    			 * */
			    	if(radioButton!=null){
			    		if(radioButton.equals("Top") || radioButton.equals("After")){
	    					if(selectICName!=null){
								intICID		=	Integer.parseInt(selectICName.split("e")[1]);
							}
							PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("classLevel_insert_in_between"));
							stmt.setInt(1,intICID);
							rst = stmt.executeQuery();
							while(rst.next()){
								duplicateClass			=	rst.getInt(1);
								break;
							}
							rst.close();
							stmt.close();
							if(duplicateClass!=0){
								errors.add(null,
										new ActionError("errors.classExists"));flag=1;
								return errors;
							}
			    		}
			    	}
	    			/**
					 * Check For The Duplicate Level Name 
					 * */
			    	intICID		=	Integer.parseInt(selectICName.split("e")[1]);
			    	PreparedStatement stmt=null;
					stmt = connection.prepareStatement(ConnectInit.queries.getProperty("classLevel_duplicate_role_name"));
					stmt.setString(1,newClassLevel);
					stmt.setInt(2,intICID);
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
	    		duplicateRoleName 	= (String)levelMap.get(selectClassLevel);
		//		AcessControl asc=new AcessControl();
	    		AcessControl asc = ConnectInit.getAcessControl();
	    		String NotSelected=asc.getLangValues("Masters.NotSelected");
				if(duplicateRoleName!=null  && !(duplicateRoleName.equals("Not Selected"))){
					duplicateRoleName1 	= duplicateRoleName.split(":")[1];
				}
				if(duplicateID!=0){
	    			if(!(newClassLevel.equalsIgnoreCase(duplicateRoleName1.trim()))){
	    				errors.add(null,new ActionError("errors.duplicateLevelName"));
						flag=1;
	    			}
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
	    /**
	     * IF NO ERRORS ARE PRESENT THEN CLEAR THE ERRORS
	     * **/
	    if(flag==0){
	    	errors.clear();
	    }
	    return errors;
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
	 * @return Returns the icCollection.
	 */
	public Collection getIcCollection() {
		 activity=null;
		 activity = new Vector(); 
	//	 AcessControl asc=new AcessControl();
		 AcessControl asc = ConnectInit.getAcessControl();
		 String NotSelected=asc.getLangValues("Masters.NotSelected");
		 activity.add(new LabelValueBean(NotSelected,"value0"));
		 icCollection=null;
		 connection=null;
		 try{
			if(connection==null)
				connection=con.getdbConnection();
		 	try{
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
	        icCollection =activity ;
	        return icCollection;
		 }
		 finally{
			try{if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
	}
	/**
	 * @param icCollection The icCollection to set.
	 */
	public void setIcCollection(Collection icCollection) {
		this.icCollection = icCollection;
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
	 * @return Returns the levelCollection.
	 */
	public Collection getLevelCollection() {
		 activity=null;
		 activity 	=	new Vector(); 
	//	 AcessControl asc=new AcessControl();
		 AcessControl asc = ConnectInit.getAcessControl();
		 String NotSelected=asc.getLangValues("Masters.NotSelected");
		 activity.add(new LabelValueBean(NotSelected,"value0"));
		 levelCollection	=	null;
		 int intIndustryId	=	0;
		 connection=null;
		 Vector test =new Vector();
		 af=test;
		 try {
			if(selectICName!=null){
			String industryId	=	selectICName.split("e")[1];
			intIndustryId	=	Integer.parseInt(industryId);
			}
		} catch (NumberFormatException e1) {
			Logging.error("Error  :"+e1.getMessage());
		//	e1.printStackTrace();
		}
		try{
			if(connection==null)
				connection=con.getdbConnection();
		 	try{
		 		PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("classLevel_select_*_from_class_level"));
		 		stmt.setInt(1,intIndustryId);
		 		rst = stmt.executeQuery();
		 		levelMap.put("value0",NotSelected);
		 		while(rst.next()){
		 			int count=rst.getInt(1);
		 			af.add(new LabelValueBean(rst.getString(3)+": "+rst.getString(2),rst.getString(3)));
		 			activity.add(new LabelValueBean(rst.getString(3)+": "+rst.getString(2),"value"+count));
		 			levelMap.put("value"+count,rst.getString(3)+": "+rst.getString(2));
				}	
		 		rst.close();
		 		stmt.close();
			}catch(Exception e){
				Logging.error("Error  :"+e.getMessage());
			}
			levelCollection =activity ;
			return levelCollection;
		}
		finally{
			try{if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
	}
	/**
	 * @param levelCollection The levelCollection to set.
	 */
	public void setLevelCollection(Collection levelCollection) {
		this.levelCollection = levelCollection;
	}
	
	/**
	 * @return Returns the afterCollection.
	 */
	public Collection getAfterCollection() {
	//	AcessControl asc=new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		String NotSelected=asc.getLangValues("Masters.NotSelected");
		if(af.isEmpty()){
			af.add(new LabelValueBean(NotSelected,"0"));
		}
		afterCollection=af;
		return afterCollection;
	}
	/**
	 * @param afterCollection The afterCollection to set.
	 */
	public void setAfterCollection(Collection afterCollection) {
		this.afterCollection = afterCollection;
	}
	/**
	 * @return Returns the selectClassLevel.
	 */
	public String getSelectClassLevel() {
		return selectClassLevel;
	}
	/**
	 * @param selectClassLevel The selectClassLevel to set.
	 */
	public void setSelectClassLevel(String selectClassLevel) {
		this.selectClassLevel = selectClassLevel;
	}
	
	/**
	 * @return Returns the newClassLevel.
	 */
	public String getNewClassLevel() {
		return newClassLevel;
	}
	/**
	 * @param newClassLevel The newClassLevel to set.
	 */
	public void setNewClassLevel(String newClassLevel) {
		if(newClassLevel!=null){
			this.newClassLevel = newClassLevel.trim();
		}
		else
			this.newClassLevel = newClassLevel;
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
		if(shortName==null ){
			this.shortName = shortName;
		}
		else
			this.shortName = shortName.trim();
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
	 * @return Returns the levelNumber.
	 */
	public int getLevelNumber() {
		return levelNumber;
	}
	/**
	 * @param levelNumber The levelNumber to set.
	 */
	public void setLevelNumber(int levelNumber) {
		this.levelNumber = levelNumber;
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
		if(description==null || description.trim().equals("")){
			this.description =null;
		}
		else
			this.description = description.trim();
	}
	
	/**
	 * @return Returns the afterLevel.
	 */
	public String getAfterLevel() {
		
		return afterLevel;
	}
	/**
	 * @param afterLevel The afterLevel to set.
	 */
	public void setAfterLevel(String afterLevel) {
		this.afterLevel = afterLevel;
	}
	
	/**
	 * @return Returns the radioButton.
	 */
	public String getRadioButton() {
		if(radioButton==null){
			radioButton="Bottom";
		}
		return radioButton;
	}
	/**
	 * @param radioButton The radioButton to set.
	 */
	public void setRadioButton(String radioButton) {
		this.radioButton = radioButton;
	}
	
}
