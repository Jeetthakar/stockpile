/*
 * Created on Mar 22, 2006
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
public class classesForm extends ValidatorForm{
	Logger Logging = Logger.getLogger(classesForm.class);
	private String selectICName					=	null;
	private Collection icCollection				=	null;
	public static TreeMap classMap				=	new TreeMap();
	public static TreeMap levelMap				=	new TreeMap();
	private String selectClassLevel     		=	null;
	private String newClassName					=	null;
	private String classCode				    =	null;
	private String shortName					=	null;
	private String operation 		    		=	"changeFields";
	private Collection levelCollection  		=	null;
	private int levelNumber						=	0;
	private String description					=	null;
	private String selectParentClassLevel		=	null;
	private Collection parentLevelCollection    =	null;
	private String radioButton					=	null;
	private String selectClass					=	null;
	private Collection classCollection 			=	null;
	Connection connection=null;
	Statement stmt;
	String query,duplicateicName=null,icID1,duplicateClassCode;
	ResultSet rst;
	int duplicateID,duplicateCode;
//	app.Connect con=new app.Connect();	
	Connect con = ConnectInit.getConnect();
	String duplicateRoleName1,duplicateNumber1,duplicateCode1;
	/**RESEST ALL FORM FIELDS**/
	  public void reset(ActionMapping mapping,HttpServletRequest request){
	  	selectICName			=	null;
   		icCollection			=	null;
   		selectClassLevel     	=	null;
   		newClassName			=	null;
   		shortName				=	null;
   		levelCollection  		=	null;
   		levelNumber				=	0;
   		description				=	null;
   		duplicateID				=	0;
   		radioButton				=	null;
   		selectParentClassLevel	=	null;
		parentLevelCollection	=	null;
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
	    	try{
				if(connection==null)
					connection=con.getdbConnection();
	    	/**
			 * Check For The Duplicate class_name to be added 
			 * */
	    	try {
    			String strLId=selectClassLevel.split("e")[1];
				int LId=Integer.parseInt(strLId);
    			PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("classes_add_duplicate_class_name"));
				stmt.setString(1,newClassName);
				stmt.setInt(2,LId);
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
			try {
					PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("classes_duplicate_classification_code"));
					stmt.setString(1,classCode);
					rst = stmt.executeQuery();
					while(rst.next()){
						duplicateCode			=	rst.getInt(1);
					}
					rst.close();
					stmt.close();
				} catch (SQLException e) {
					Logging.error("Error  :"+e.getMessage());
				//	e.printStackTrace();
				}
			
			/**If Add RadioButton Is On**/
	    	if(radioButton!=null ){
	    		if(radioButton.equals("Add")){
	    			if(duplicateID!=0){
	    				errors.add(null,new ActionError("errors.duplicateClassName"));
	    				flag=1;
			    	}
	    			if(duplicateCode!=0){
	    				errors.add(null,new ActionError("errors.duplicateClasscode"));
	    				flag=1;
					}
	    		}
	    		/**If Update RadioButton Is On**/
	    		else{
	    			try {
						PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("classes_duplicate_classification_code1"));
						String strSId=selectClass.split("e")[1];
						int SId=Integer.parseInt(strSId);
						stmt.setInt(1,SId);
						rst = stmt.executeQuery();
						while(rst.next()){
							duplicateCode1			=	rst.getString(1);
						}
						rst.close();
						stmt.close();
					} catch (NumberFormatException e) {
						Logging.error("Error  :"+e.getMessage());
					//	e.printStackTrace();
					} catch (SQLException e) {
						Logging.error("Error  :"+e.getMessage());
					//	e.printStackTrace();
					}
					String duplicateRoleName 	= (String)classMap.get(selectClass);
					if(duplicateID!=0){
		    			if(!(newClassName.equalsIgnoreCase(duplicateRoleName.trim()))){
		    				errors.add(null,
									new ActionError("errors.duplicateClassName"));flag=1;
		    			}
		    			if(duplicateCode!=0){
		    	    		if(!(classCode.equalsIgnoreCase(duplicateCode1.trim()))){
		    	    			errors.add(null,new ActionError("errors.duplicateClasscode"));
		    					flag=1;
		    	    		}
		    			}
					}
	    		}
	    	}
	    }
		/**
	     * IF NO ERRORS ARE PRESENT THEN CLEAR THE ERRORS
	     * **/
	    finally{
			try{
				if(connection!=null)
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
		 Vector activity = new Vector();
	//	 AcessControl asc=new AcessControl();
		 AcessControl asc = ConnectInit.getAcessControl();
		 String NotSelected=asc.getLangValues("Masters.NotSelected");
		 Logging.debug(" Inside getIndexcollection(): Not Selected ="+NotSelected);
		 activity.add(new LabelValueBean(NotSelected,"value0"));
		 icCollection=null;
		 connection=null;
		 try{
			if(connection==null)
				connection=con.getdbConnection();
		 	try{
		 		PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("icm_select_*_from_industry_classification_master"));
		 		rst = stmt.executeQuery();
		 		while(rst.next()){
		 			int count=rst.getInt(1);
		 			activity.add(new LabelValueBean(rst.getString(2),"value"+count));
				}	
		 		rst.close();
		 		stmt.close();
			}catch(Exception e){
				Logging.error(e+"");
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
	 * @return Returns the levelCollection.
	 */
	public Collection getLevelCollection() {
		levelCollection	=	null;
		Vector activity 	=	new Vector(10);
	//	AcessControl asc=new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		String NotSelected=asc.getLangValues("Masters.NotSelected");
		Logging.debug(" Inside getIndexcollection(): Not Selected ="+NotSelected);
		activity.add(new LabelValueBean(NotSelected,"value0"));
		levelMap.put("value0",NotSelected);
		levelCollection	=	activity;
		int intIndustryId	=	0;
		connection=null;
		try {
			if(selectICName!=null){
				String industryId	=	selectICName.split("e")[1];
				intIndustryId		=	Integer.parseInt(industryId);
				/**If selectICName is Not Selected*/
				if(intIndustryId==0){
					return levelCollection;
				}
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
		 			while(rst.next()){
						int count=rst.getInt(1);
						activity.add(new LabelValueBean(rst.getString(3)+": "+rst.getString(2),"value"+count));
						levelMap.put("value"+count,rst.getString(3)+": "+rst.getString(2));
					}	
		 			rst.close();
					stmt.close();
			   	}catch(Exception e){
			   		Logging.error(e+"");
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
	 * @return Returns the newClassName.
	 */
	public String getNewClassName() {
		return newClassName;
	}
	/**
	 * @param newClassName The newClassName to set.
	 */
	public void setNewClassName(String newClassName) {
		if(newClassName!=null){
			this.newClassName = newClassName.trim();
		}
		else
			this.newClassName = newClassName;
	}
	/**
	 * @return Returns the classCode.
	 */
	public String getClassCode() {
		return classCode;
	}
	/**
	 * @param classCode The classCode to set.
	 */
	public void setClassCode(String classCode) {
		if(classCode!=null){
			this.classCode = classCode.trim();
		}
		else
			this.classCode = classCode;
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
		if(shortName==null || shortName.trim().equals("")){
			this.shortName=null;
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
			this.description = null;
		}
		else
			this.description = description.trim();
	}
	
	/**
	 * @return Returns the parentLevelCollection.
	 */
	public Collection getParentLevelCollection() {
		int intCLId			=	0;
		Vector activity 		=	new Vector(); 
		Vector activity2 		=	new Vector(); 
		Vector activity1 		=	new Vector(); 
	//	AcessControl asc=new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		String NotSelected=asc.getLangValues("Masters.NotSelected");
		activity1.add(new LabelValueBean(NotSelected,"value0"));
		activity2.add(new LabelValueBean(NotSelected,"value0"));
		if(operation.equals("changeFields") || (selectClassLevel!=null && selectClassLevel.equals("value0"))){
			activity.add(new LabelValueBean(NotSelected,"value0"));
			parentLevelCollection	=	activity;
			return parentLevelCollection;
		}
		try {
			if(selectClassLevel!=null){
				String classLevelId	=	selectICName.split("e")[1];
				intCLId				=	Integer.parseInt(classLevelId);
				String lev=(String)levelMap.get(selectClassLevel);
				connection=null;
				if(lev.trim().charAt(0)!='1'){
					try{
						if(connection==null)
							connection=con.getdbConnection();
						try{
							String levelNo	=lev.trim().charAt(0)+"";
							int ln			=Integer.parseInt(levelNo);
							ln--;
							PreparedStatement stmt 	= connection.prepareStatement(ConnectInit.queries.getProperty("classes_select_class_name_for_class_id"));
							stmt.setInt(1,ln);
							stmt.setInt(2,intCLId);
			 				rst = stmt.executeQuery();
			 				while(rst.next()){
								int count=rst.getInt(1);
								activity2.add(new LabelValueBean(rst.getString(2),"value"+count));
							}	
			 				rst.close();
							stmt.close();
			 				parentLevelCollection	=	activity2;
			 				return parentLevelCollection;
						}catch(Exception e){
							Logging.error(e+"");
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
					parentLevelCollection	=	activity1;
					return parentLevelCollection;
				}
			}
		} catch (NumberFormatException e1) {
			Logging.error("Error  :"+e1.getMessage());
			//e1.printStackTrace();
		}
		parentLevelCollection = activity1 ;
		return parentLevelCollection;
	}
	/**
	 * @param parentLevelCollection The parentLevelCollection to set.
	 */
	public void setParentLevelCollection(Collection ParentLevelCollection) {
		parentLevelCollection = ParentLevelCollection;
	}
	/**
	 * @return Returns the selectParentClassLevel.
	 */
	public String getSelectParentClassLevel() {
		return selectParentClassLevel;
	}
	/**
	 * @param selectParentClassLevel The selectParentClassLevel to set.
	 */
	public void setSelectParentClassLevel(String selectParentClassLevel) {
		this.selectParentClassLevel = selectParentClassLevel;
	}
	
	/**
	 * @return Returns the radioButton.
	 */
	public String getRadioButton() {
		if(radioButton==null || radioButton.trim().length()==0){
			this.radioButton="Add";
		}
		return radioButton;
	}
	/**
	 * @param radioButton The radioButton to set.
	 */
	public void setRadioButton(String radioButton) {
		this.radioButton = radioButton;
	}
	
	/**
	 * @return Returns the classCollection.
	 */
	public Collection getClassCollection() {
		Vector activity 		=	new Vector(10); 
		Vector activity1 		=	new Vector(10);
	//	AcessControl asc=new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		String NotSelected=asc.getLangValues("Masters.NotSelected");
		activity1.add(new LabelValueBean(NotSelected,"value0"));
		activity.add(new LabelValueBean(NotSelected,"value0"));
		//classMap.put("value0","Not Selected");
		classCollection			=	activity;
		int intCLId				=	0;
		if(operation.equals("changeFields") || (selectClassLevel!=null && selectClassLevel.equals("value0"))){
			return classCollection;
		}
		try {
			if(selectClassLevel!=null){
				String classLevelId	=	selectClassLevel.split("e")[1];
				intCLId				=	Integer.parseInt(classLevelId);
				connection=null;
				try{
					if(connection==null)
						connection=con.getdbConnection();
					try{
			 			PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("classes_select_class_name_for_class_id1"));
			 			stmt.setInt(1,intCLId);
			 			rst = stmt.executeQuery();
			 			int count=0;
			 			while(rst.next()){
							count=rst.getInt(1);
							activity1.add(new LabelValueBean(rst.getString(2),"value"+count));
							classMap.put("value"+count,rst.getString(2));
						}
			 			rst.close();
						stmt.close();
			 			if(count==0){
			 				return classCollection;
			 			}
			 			classCollection = activity1 ;
						return classCollection;
					}catch(Exception e){
							Logging.error(e+"");
					}
				} catch (NumberFormatException e1) {
					Logging.error("Error  :"+e1.getMessage());
				//	e1.printStackTrace();
				}
			}	
			classCollection = activity ;
			return classCollection;
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
	 * @param classCollection The classCollection to set.
	 */
	public void setClassCollection(Collection classCollection) {
		this.classCollection = classCollection;
	}
	/**
	 * @return Returns the selectClass.
	 */
	public String getSelectClass() {
		return selectClass;
	}
	/**
	 * @param selectClass The selectClass to set.
	 */
	public void setSelectClass(String selectClass) {
		this.selectClass = selectClass;
	}
	
}
