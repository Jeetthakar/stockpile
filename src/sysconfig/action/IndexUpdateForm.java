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
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
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
public class IndexUpdateForm extends ValidatorForm{
	Logger Logging = Logger.getLogger(IndexUpdateForm.class);
	 private String index_id 						=  null;
	 private Collection indexUpdateCollection		=  null;
	 private Collection indexTypeCollection			=  null;
	 private Collection indexTypeTwoCollection		=  null;
	 private Collection capturedFrom				=  null;
	 public  String parent_id						=  null;
	 private String is_child 						=  null;
	 private String is_active						=  null;
	 private String index_name						=  null; 
	 private String index_type_id					=  null; 
	 private String industry_classification_id 		=	null;
	 private String creation_date					=  null; 
	 private String base_date 						=  null;
	 private String test_index						=  null;
	 private float alert_percentage 				=  0;
	 private float rejection_percentage 			=  0;
	 private String o_ric 							=  null;
	 private float base_value						=  0;
	 private String isin							=  null; 
	 private String m_start_time 					=  null;
	 private String n_stop_time 					=  null;
	 private String calculate_settlement_value 		=  null;
	 private String operation						=  null;
	 private String formTwoRoleName					=	null;
	 private String drop_test_index					=	null;
	 private String chkTestIndex					=	null;
	 private String testIndex						=	null;
	 
	 private int computation_interval 				=  0;
	 private int next_day 							=  0;
	 private String captured_from					=  null; 
	 private String is_captured 					=  null;
	 private String base_currency_id				=  null; 
	 private String  growth_or_value 				=  null;
	 private static TreeMap typeMap					=  new TreeMap();
	
	 private String isChild							=  null;
	 private String parentIndex						=  null;
	 private String industryClassificationId		=  null;
	 private String creationDate					=  null;
	 private String baseDate						=  null;
	 private float baseValue						=  0;
	 private String isCaptured						=  null;
	 private String baseCurrencyId					=  null;
	 private String capturedForm					=  null;
	 private String indexType						=  null;
	 private String show_child_index				=  null;
	 private String selChild						=  null;
	 private  String user_id                        =  null;
	/**
	 * @return Returns the user_id.
	 */
	public String getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id The user_id to set.
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	 int flag										=	0;
	 int flag1										=	0;
	 int start[]									=  new int[3];
	 int stop[]										=  new int[3];
	 int count=0,count1=0,duplicateIndexId;
	
	  Statement stmt;
	  String query;
	  ResultSet rst;
	
	  app.Connect con=ConnectInit.getConnect();
	
	 
	  /**RESEST ALL FORM FIELDS**/
	  public void reset(ActionMapping mapping,HttpServletRequest request){
	  	
	  	 index_id 						=  null;
		 index_name						=  null; 
		 creation_date					=  null; 
		 is_active						=  null;
		 base_date 						=  null;
		 base_value						=  0;
		 computation_interval 			=  0;
		 is_captured 					=  null;
		 captured_from					=  null; 
		 m_start_time 					=  null;
		 n_stop_time 					=  null;
		 o_ric 							=  null;
		 is_child 						=  null;
		 parent_id						=  null;
		 base_currency_id				=  null; 
		 index_type_id					=  null; 
		 alert_percentage 				=  0;
		 rejection_percentage 			=  0;
		 growth_or_value 				=  null;
		 calculate_settlement_value 	=  null;
		 isin							=  null; 
		 next_day 						=  0;
		 industry_classification_id 	=  null;
		 drop_test_index				=	null;
		 show_child_index				=	null;
		 test_index						=	null;
		 selChild						=  null;
	   }
	
	 /**VALIDATE FORM DATA **/
	  public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request)
	  {
	  
	  		ActionErrors errors = new ActionErrors();
			int chkFlag=0;
			int chk=0;
		  	boolean nameEntered = false;
		  	String ret			= getSelChild();
		  	 if(operation!=null && operation.equals("save")){
			 	creation_date=creationDate;
			 	base_date=baseDate;
			 	base_currency_id=baseCurrencyId;
			 	base_value=baseValue;
			 	parent_id=parentIndex;
			 	industry_classification_id=industryClassificationId;
			 	is_child=isChild;
			 	is_captured=isCaptured;
			 	test_index=testIndex;
			 }
		  	if(this.index_id.equals("value0") || (drop_test_index!=null && drop_test_index.equals("on")) || (ret!=null && (ret.equals("yes")  ))){
		  		return errors;
		  	}
		  	if(operation.length()==0){
		  		return errors;
		  	}
		  	else if(this.operation!=null && this.operation.equals("changeFields")){
		  		return errors;
		  	}
		  
		   	else{
		   		Connection connection=null;
				  	try {
				  		if(connection==null)
         					connection=con.getdbConnection();
				   		if (index_name != null && index_name.length() > 0 ) {
				   			
				   			/**Check For The Duplicate index_name In index_master Table*/
				   			try {
								PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("indexUpdate_chk_dupicate_name"));
								stmt.setString(1,index_name);
								rst = stmt.executeQuery();
								while(rst.next()){
									duplicateIndexId=rst.getInt(1);
								
								}
								rst.close();
								stmt.close();
								
							} catch (SQLException e1) {	Logging.error(" Error : "+e1.getMessage());		}	
				   			
				   			String selectedIndex	=	this.index_id;
				   			String dup="value"+duplicateIndexId;
				   			if(duplicateIndexId!=0){
				   				if(!(dup.equals(selectedIndex))){
					   				errors.add(null,
					    					new ActionError("errors.dupilcateIndexName"));flag=1;
				   				}
				   			}
				   		
				   				nameEntered = true;
				   		
			    		}
			    		if (!nameEntered) {
			    			errors.add(null,
			    					new ActionError("errors.indexName"));flag=1;
			    		}
			    	
			    		/**Validation For Index Type*/
			    		if(index_type_id!=null && index_type_id.equals("value1")){
			    			int co=0;
			    			
			           
			         		try{			         				
				         			String indexType		=	index_id.split("e")[1];
									int inId				=	Integer.parseInt(indexType);
									PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("indexUpdate_get_index_stock_id_iwf<1"));
									stmt.setInt(1,inId);
									rst = stmt.executeQuery();
				         		   	while(rst.next()){
			        					co=rst.getInt(1);
			          				}	
				         		    rst.close();
									stmt.close();
			        	   	}catch(Exception e){Logging.error(" Error : "+e.getMessage());		}
			        	   	if(co!=0){
			        	   		errors.add(null,
				    					new ActionError("errors.iwf<1"));flag=1;
			        	   	}
			    		}
			    	
			    	/**Validation for start time should be less than stop time
			    	 *OR .......  Validation For Time Format
			    	 ** */
			    		if (!ValidateTime(m_start_time)) {
							errors.add(null, new ActionError("Error.message.StartTime"));flag=1;flag1=1;
							
						}
						if (!ValidateTime(n_stop_time)) {
							errors.add(null, new ActionError("Error.message.StoptTime"));flag=1;flag1=1;
							
						}
						if(flag1==1){
							return errors;
						}
			    		
			    		//Breaking n_stop_time
			    		StringTokenizer token1=new StringTokenizer(n_stop_time,":");
			    		while(token1.hasMoreElements()){
			    			String t1=token1.nextToken();
			    			stop[count1]=Integer.parseInt(t1);
			    			count1++;
			    		}
			    		//Breaking m_start_time
			    		StringTokenizer token=new StringTokenizer(m_start_time,":");
			    		while(token.hasMoreElements()){
			    			String t=token.nextToken();
			    			start[count]=Integer.parseInt(t);
			    			count++;
			    		}
			    		
			    		
			    		String isValid="t";
			    		if(start[0]>stop[0]){
							
                    		isValid = "f";
						}
						else if(start[0]==stop[0]){
							if(start[1]>stop[1]){
								
								isValid = "f";
							}
							else if(start[1]==stop[1]){
									if(start[2]>stop[2] || start[2]==stop[2]){
										
										isValid = "f";
									}
									else{
										isValid="t";
									}
								
							}
							else{
								isValid="t";
							}
							
						}
						else{
							isValid="t";
						}
					if(isValid.equals("f")){
						errors.add(null,
		    					new ActionError("errors.startStopPercentage"));flag=1;
		    			
					}
			    		
				    	
			    		
				    
				  	}catch(Exception ee){
		    			Logging.error(" Error : "+ee.getMessage());			
		    		}
		    		finally{
		    			try{
		    				if(connection!=null){
		    					connection.close();
		    				}
		    			}catch(Exception ex){
		    				Logging.error(" Error : Unable to close Connection "+ex.getMessage());			
		    			}
		    		}
			    	
			    	/**
			    	 * IF NO ERRORS ARE PRESENT THEN CLEAR THE ERRORS
			    	 * **/
			    	if(flag==0){
			    		errors.clear();
			    	}
		   	}
		  		  
		 return errors;
	    
	   }
	  private boolean ValidateTime(String time) {
		if (time == null || time.trim().equals("")) {
			return false;
		}
		try {
			String[] arr = time.split(":");
			String[] temp = { "23", "59", "59" };
			if (arr.length < 3 || arr.length > 3)
				return false;
			for (int i = 0; i < arr.length; i++) {
				Logging.debug(arr[i]);
				try {
					if (Integer.parseInt(arr[i]) > Integer.parseInt(temp[i])
							|| (Integer.parseInt(arr[i]) < 0))
						return false;
				} catch (Exception e) {
					return false;
				}
			}
			Logging.debug("Returned true");
			return true;
		} catch (Exception e) {
			Logging.error("Returned false");
			return false;
		}
	  }
	 /**Getters And Setters*/
	  
	
	 
		/**
		 * @return Returns the show_child_index.
		 */
		public String getShow_child_index() {
			return show_child_index;
		}
		/**
		 * @param show_child_index The show_child_index to set.
		 */
		public void setShow_child_index(String show_child_index) {
			this.show_child_index = show_child_index;
		}
		
	
	  
	  /**
	 * @return Returns the alert_percentage.
	 */
	public float getAlert_percentage() {
		return alert_percentage;
	}
	/**
	 * @param alert_percentage The alert_percentage to set.
	 */
	public void setAlert_percentage(float alert_percentage) {
		this.alert_percentage = alert_percentage;
		
	}
	
	/**
	 * @return Returns the base_currency_id.
	 */
	public String getBase_currency_id() {
		return base_currency_id;
	}
	/**
	 * @param base_currency_id The base_currency_id to set.
	 */
	public void setBase_currency_id(String base_currency_id) {
		this.base_currency_id = base_currency_id;
	}
	/**
	 * @return Returns the base_date.
	 */
	public String getBase_date() {
		return base_date;
	}
	/**
	 * @param base_date The base_date to set.
	 */
	public void setBase_date(String base_date) {
		this.base_date = base_date;
	}
	/**
	 * @return Returns the base_value.
	 */
	public float getBase_value() {
		return base_value;
	}
	/**
	 * @param base_value The base_value to set.
	 */
	public void setBase_value(float base_value) {
		this.base_value = base_value;
	}
	/**
	 * @return Returns the calculate_settlement_value.
	 */
	public String getCalculate_settlement_value() {
		return calculate_settlement_value;
	}
	/**
	 * @param calculate_settlement_value The calculate_settlement_value to set.
	 */
	public void setCalculate_settlement_value(String calculate_settlement_value) {
		this.calculate_settlement_value = calculate_settlement_value;
	}
	/**
	 * @return Returns the captured_from.
	 */
	public String getCaptured_from() {
		return captured_from;
	}
	/**
	 * @param captured_from The captured_from to set.
	 */
	public void setCaptured_from(String captured_from) {
		this.captured_from = captured_from;
		
	}
	/**
	 * @return Returns the computation_interval.
	 */
	public int getComputation_interval() {
		return computation_interval;
	}
	/**
	 * @param computation_interval The computation_interval to set.
	 */
	public void setComputation_interval(int computation_interval) {
		this.computation_interval = computation_interval;
	}
	/**
	 * @return Returns the creation_date.
	 */
	public String getCreation_date() {
		return creation_date;
	}
	/**
	 * @param creation_date The creation_date to set.
	 */
	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}
	/**
	 * @return Returns the growth_or_value.
	 */
	public String getGrowth_or_value() {
		return growth_or_value;
	}
	/**
	 * @param growth_or_value The growth_or_value to set.
	 */
	public void setGrowth_or_value(String growth_or_value) {
		this.growth_or_value = growth_or_value;
	}
	
	/**
	 * @return Returns the index_id.
	 */
	public String getIndex_id() {
		/*if(index_id!=null && index_id.equals("value0")){
			resetAll();
		}*/
		return index_id;
	}
	/**
	 * @param index_id The index_id to set.
	 */
	public void setIndex_id(String index_id) {
		this.index_id = index_id;
	}
	/**
	 * @return Returns the index_name.
	 */
	public String getIndex_name() {
		return index_name;
	}
	/**
	 * @param index_name The index_name to set.
	 */
	public void setIndex_name(String index_name) {
		if(index_name!=null ){
			this.index_name = index_name.trim();
		}
		else
		this.index_name = index_name;
		
	}
	
	/**
	 * @return Returns the index_type_id.
	 */
	public String getIndex_type_id() {
		return index_type_id;
	}
	/**
	 * @param index_type_id The index_type_id to set.
	 */
	public void setIndex_type_id(String index_type_id) {
		this.index_type_id = index_type_id;
		
	}
	
	/**
	 * @return Returns the industry_classification_id.
	 */
	public String getIndustry_classification_id() {
		return industry_classification_id;
	}
	/**
	 * @param industry_classification_id The industry_classification_id to set.
	 */
	public void setIndustry_classification_id(String industry_classification_id) {
		this.industry_classification_id = industry_classification_id;
	}
	/**
	 * @return Returns the is_active.
	 */
	public String getIs_active() {
		return is_active;
	}
	/**
	 * @param is_active The is_active to set.
	 */
	public void setIs_active(String is_active) {
		
		this.is_active = is_active;
	}
	/**
	 * @return Returns the is_captured.
	 */
	public String getIs_captured() {
		
		return is_captured;
	}
	/**
	 * @param is_captured The is_captured to set.
	 */
	public void setIs_captured(String is_captured) {
		this.is_captured = is_captured;
		
	}
	/**
	 * @return Returns the is_child.
	 */
	public String getIs_child() {
		return is_child;
	}
	/**
	 * @param is_child The is_child to set.
	 */
	public void setIs_child(String is_child) {
		this.is_child = is_child;
	}
	
	/**
	 * @return Returns the isin.
	 */
	public String getIsin() {
		return isin;
	}
	/**
	 * @param isin The isin to set.
	 */
	public void setIsin(String isin) {
		if(isin!=null){
			this.isin = isin.trim();
		}
		else
			this.isin = isin;
	}
	/**
	 * @return Returns the m_start_time.
	 */
	public String getM_start_time() {
		return m_start_time;
	}
	/**
	 * @param m_start_time The m_start_time to set.
	 */
	public void setM_start_time(String m_start_time) {
		this.m_start_time = m_start_time;
	}
	/**
	 * @return Returns the n_stop_time.
	 */
	public String getN_stop_time() {
		return n_stop_time;
	}
	/**
	 * @param n_stop_time The n_stop_time to set.
	 */
	public void setN_stop_time(String n_stop_time) {
		this.n_stop_time = n_stop_time;
	}
	/**
	 * @return Returns the next_day.
	 */
	public int getNext_day() {
		return next_day;
	}
	/**
	 * @param next_day The next_day to set.
	 */
	public void setNext_day(int next_day) {
		this.next_day = next_day;
	}
	/**
	 * @return Returns the o_ric.
	 */
	public String getO_ric() {
		return o_ric;
	}
	/**
	 * @param o_ric The o_ric to set.
	 */
	public void setO_ric(String o_ric) {
		if(o_ric!=null){
			this.o_ric = o_ric.trim();
		}
		else
			this.o_ric = o_ric;
		
	}
	
	/**
	 * @return Returns the parent_id.
	 */
	public String getParent_id() {
		return parent_id;
	}
	/**
	 * @param parent_id The parent_id to set.
	 */
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	/**
	 * @return Returns the rejection_percentage.
	 */
	public float getRejection_percentage() {
		return rejection_percentage;
	}
	/**
	 * @param rejection_percentage The rejection_percentage to set.
	 */
	public void setRejection_percentage(float rejection_percentage) {
		this.rejection_percentage = rejection_percentage;
		
	}
	
	/**
	 * @return Returns the indexUpdateCollection.
	 */
	public Collection getIndexUpdateCollection() {
		Connect c=ConnectInit.getConnect();
		 Vector update = new Vector(10); 
		 update .add(new LabelValueBean("Not Selected","value0"));
		 Connection connection=null;
		 PreparedStatement stmt = null;
		 rst=null;
       		try{
       				if(connection==null)
       					connection=c.getdbConnection();
       				if(show_child_index!=null && show_child_index.equals("show")){
     					stmt = connection.prepareStatement(ConnectInit.queries.getProperty("indexUpdate_getall1"));
     					stmt.setInt(1,Integer.parseInt(getUser_id()));
     					rst = stmt.executeQuery();	
     				}
     				else{
     					stmt = connection.prepareStatement(ConnectInit.queries.getProperty("indexUpdate_get_indices_from_index_master_online"));
     					 stmt.setInt(1,Integer.parseInt(getUser_id()));
     			         rst = stmt.executeQuery();
     					
     				}	
     				     				  	
         				while(rst.next()){
        					int count=rst.getInt(1);
        					update .add(new LabelValueBean(rst.getString(2),"value"+count));
	        			}	    	        	
       		}catch(Exception ee){
    			Logging.error(" Error : "+ee.getMessage());			
    		}
    		finally{
    			try{
    				if(rst!=null)
    					rst.close();
    				if(connection!=null){
    					connection.close();
    				}
    			}catch(Exception ex){
    				Logging.error(" Error : Unable to close Connection "+ex.getMessage());			
    			}
    		}        	   		
        indexUpdateCollection =update ;
       	return indexUpdateCollection;
	}
	/**
	 * @return Returns the indexUpdateCollection.
	 */
	public Collection getIndexTypeCollection() {
		
		Vector type = new Vector(10); 
		type.add(new LabelValueBean("Not Selected","value0"));
		Vector typeValue10=new Vector(10);
		Vector typeOther=new Vector(10);
		Connection connection=null;
		try{
				if(connection==null)
					connection=con.getdbConnection();
 				PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("indexUpdate_get_index_type_from_index_master"));
 				rst = stmt.executeQuery();
	        	
	        	typeMap.put("Not Selected","value0");
	        	while(rst.next()){
					int count=rst.getInt(1);
						type.add(new LabelValueBean(rst.getString(2),"value"+count));
						typeMap.put("value"+count,rst.getString(2));
						if(count==1 || count==2){
							typeValue10.add(new LabelValueBean(rst.getString(2),"value"+count));
						}
   				}	
	        	rst.close();
				stmt.close();
     		}catch(Exception ee){
    			Logging.error(" Error : "+ee.getMessage());			
    		}
    		finally{
    			try{
    				if(connection!=null){
    					connection.close();
    				}
    			}catch(Exception ex){
    				Logging.error(" Error : Unable to close Connection "+ex.getMessage());			
    			}
    		}
		    if(getIndex_type_id()==null){
				indexTypeCollection=type;
				return indexTypeCollection;
			}
			else if(getIndex_type_id().equals("value0")){
				indexTypeCollection=type;
				return indexTypeCollection;
			}
			else if((getIndex_type_id().equals("value1") || getIndex_type_id().equals("value2"))){
				indexTypeCollection=typeValue10;
				return indexTypeCollection;
			}
			else{
				
				String str=(String)typeMap.get(getIndex_type_id());		
				typeOther.add(new LabelValueBean(str,getIndex_type_id()));
				indexTypeCollection=typeOther;
				return indexTypeCollection;
			}
	
	}
	
	/**
	 * @param indexUpdateCollection The indexUpdateCollection to set.
	 */
	public void setIndexUpdateCollection(Collection indexUpdateCollection) {
		this.indexUpdateCollection = indexUpdateCollection;
	}
	
	/**
	 * @return Returns the capturedFrom.
	 */
	public Collection getCapturedFrom() {
		Connection connection=null;
		Vector roles = new Vector(10);
		Vector empty = new Vector(1);
		empty.add(new LabelValueBean("Not Captured","value0"));
		capturedFrom=empty;
		try{
				if(connection==null)
					connection=con.getdbConnection();
 				PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("indexUpdate_getall_feed_provider_from_feed_provider"));
 				rst = stmt.executeQuery();	  
 				
	        	while(rst.next()){
					int count=rst.getInt(1);
					roles.add(new LabelValueBean(rst.getString(2),"value"+count));
  				}	
	        	rst.close();
				stmt.close();
        	   	
		}catch(Exception ee){
			Logging.error(" Error : "+ee.getMessage());			
		}
		finally{
			try{
				if(connection!=null){
					connection.close();
				}
			}catch(Exception ex){
				Logging.error(" Error : Unable to close Connection "+ex.getMessage());			
			}
		}
		 if(getIs_captured()==null){
			
			capturedFrom = empty;
			return capturedFrom;
		}
		else if(getIs_captured().equals("on")){
			capturedFrom = roles;
			return capturedFrom;
		}
		else{
			return capturedFrom;
		}
	}
	/**
	 * @param capturedFrom The capturedFrom to set.
	 */
	public void setCapturedFrom(Collection capturedFrom) {
		this.capturedFrom = capturedFrom;
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
	 * @return Returns the baseDate.
	 */
	public String getBaseDate() {
		return baseDate;
	}
	/**
	 * @param baseDate The baseDate to set.
	 */
	public void setBaseDate(String baseDate) {
		this.baseDate = baseDate;
	}
	/**
	 * @return Returns the baseValue.
	 */
	public float getBaseValue() {
		return baseValue;
	}
	/**
	 * @param baseValue The baseValue to set.
	 */
	public void setBaseValue(float baseValue) {
		this.baseValue = baseValue;
	}
	/**
	 * @return Returns the creationDate.
	 */
	public String getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate The creationDate to set.
	 */
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * @return Returns the industryClassificationId.
	 */
	public String getIndustryClassificationId() {
		return industryClassificationId;
	}
	/**
	 * @param industryClassificationId The industryClassificationId to set.
	 */
	public void setIndustryClassificationId(String industryClassificationId) {
		this.industryClassificationId = industryClassificationId;
	}
	/**
	 * @return Returns the isCaptured.
	 */
	public String getIsCaptured() {
		return isCaptured;
	}
	/**
	 * @param isCaptured The isCaptured to set.
	 */
	public void setIsCaptured(String isCaptured) {
		this.isCaptured = isCaptured;
	}
	/**
	 * @return Returns the isChild.
	 */
	public String getIsChild() {
		return isChild;
	}
	/**
	 * @param isChild The isChild to set.
	 */
	public void setIsChild(String isChild) {
		this.isChild = isChild;
	}
	/**
	 * @return Returns the parentIndex.
	 */
	public String getParentIndex() {
		return parentIndex;
	}
	/**
	 * @param parentIndex The parentIndex to set.
	 */
	public void setParentIndex(String parentIndex) {
		this.parentIndex = parentIndex;
	}
	
	/**
	 * @return Returns the baseCurrencyId.
	 */
	public String getBaseCurrencyId() {
		return baseCurrencyId;
	}
	/**
	 * @param baseCurrencyId The baseCurrencyId to set.
	 */
	public void setBaseCurrencyId(String baseCurrencyId) {
		this.baseCurrencyId = baseCurrencyId;
	}
	
	/**
	 * @return Returns the capturedForm.
	 */
	public String getCapturedForm() {
		return capturedForm;
	}
	/**
	 * @param capturedForm The capturedForm to set.
	 */
	public void setCapturedForm(String capturedForm) {
		this.capturedForm = capturedForm;
	}
	
	/**
	 * @return Returns the indexType.
	 */
	public String getIndexType() {
		return indexType;
	}
	/**
	 * @param indexType The indexType to set.
	 */
	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}
		
	/**
	 * @return Returns the test_index.
	 */
	public String getTest_index() {
		return test_index;
	}
	/**
	 * @param test_index The test_index to set.
	 */
	public void setTest_index(String test_index) {
		this.test_index = test_index;
	}
	
	
	/**
	 * @return Returns the drop_test_index.
	 */
	public String getDrop_test_index() {
		if(test_index==null){
			drop_test_index=null;
		}
		return drop_test_index;
		
	}
	/**
	 * @param drop_test_index The drop_test_index to set.
	 */
	public void setDrop_test_index(String drop_test_index) {
		this.drop_test_index = drop_test_index;
	}
	
	/**
	 * @return Returns the chkTestIndex.
	 */
	public String getChkTestIndex() {
		if(this.test_index!=null && test_index.equals("on")){
			chkTestIndex="enableDropTestIndex";
		}
		else{
			chkTestIndex="disableDropTestIndex";
		}
		return chkTestIndex;
	}
	/**
	 * @param chkTestIndex The chkTestIndex to set.
	 */
	public void setChkTestIndex(String chkTestIndex) {
		this.chkTestIndex = chkTestIndex;
	}
	
	/**
	 * @return Returns the testIndex.
	 */
	public String getTestIndex() {
		return testIndex;
	}
	/**
	 * @param testIndex The testIndex to set.
	 */
	public void setTestIndex(String testIndex) {
		this.testIndex = testIndex;
	}
	/**
	 * Database Connectivity
	 * */
//commented by Manoj Adkar
	//	public void dbconnect(){
		
		//try {
  	//	  	if(app.Connect.con==null)
	//	{
	//		con.getConnection();
//		}

	//	} catch (Exception e) {	System.out.println(e);} 
		
	//}

	/**
	 * @return Returns the selChild.
	 */
	public String getSelChild() {
		return selChild;
	}
	/**
	 * @param selChild The selChild to set.
	 */
	public void setSelChild(String selChild) {
		this.selChild = selChild;
	}
}
