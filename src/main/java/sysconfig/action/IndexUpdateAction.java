/*
 * Created on Feb 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package sysconfig.action;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import app.Connect;

import com.harrier.initializeation.ConnectInit;


/**
 * @author manoj
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public final class IndexUpdateAction extends Action{
	Logger Logging = Logger.getLogger(IndexUpdateAction.class);
	Statement stmt;
    String query;
	ResultSet rst,rst1;
	int iType=0;
	int numCaptured	;
	String oRic,is_in; 		
	Connect connect=ConnectInit.getConnect();
	
	public ActionForward execute( ActionMapping mapping, ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response )
	throws IOException, ServletException {
	Connection con=null;
	Connection con1=null;
	try{
			if(con==null)
				con=connect.getdbConnection();
			
		IndexUpdateForm iuForm= 	(IndexUpdateForm)form;
			
	   	/**
	   	 * If show child and sector indices checbox is checked
	   	 * */
		String ret			=	iuForm.getSelChild();
		if(ret!=null && (ret.equals("yes")  )) {
			iuForm.setIndex_id("value0");
			resetAllFields(iuForm);
			return mapping.getInputForward();
		}
		
	   	/**
	   	 * IF Save Button Is Pressed Make The Entries In Database
	   	 * */
	   	String operation	=	iuForm.getOperation();
	   	if(operation!=null && operation.equals("save")){
	   		String dropTestIndex=iuForm.getDrop_test_index();
	   		if(dropTestIndex!=null && dropTestIndex.equals("on")){
	   			try {
	   				String sIName			=	iuForm.getIndex_id().split("e")[1];
					int numIndex			=	Integer.parseInt(sIName);
						try {
							con1=ConnectInit.getConnect().getConnectionForTransaction();
							con1.commit();
							con1.setAutoCommit(false);
						
							PreparedStatement stmt = con1.prepareStatement(ConnectInit.queries.getProperty("indexUpdate_del_indices"));
							stmt.setInt(1,numIndex);
							stmt.executeUpdate();
							stmt = con1.prepareStatement(ConnectInit.queries.getProperty("indexUpdate_del_indices1"));
							stmt.setInt(1,numIndex);
							stmt.executeUpdate(); //added code for deletion of index  from index composition 15-02-08 ==>
							stmt = con1.prepareStatement(ConnectInit.queries.getProperty("indexUpdate_del_compose"));
							stmt.setInt(1,numIndex);
							stmt.executeUpdate();  //  <== code ended
							stmt.close();
							con1.setAutoCommit(true);
							con1.close();
							iuForm.setDrop_test_index(null);
						} catch (Exception e2) {
							con1.rollback();
							con1.setAutoCommit(true);
							con1.close();
							//e2.printStackTrace();}
							Logging.error(" Error : "+e2.getMessage());}
				} catch (Exception e) {
					//e.printStackTrace();
					Logging.error(" Error : "+e.getMessage());
				}
				resetAllFields(iuForm);
				
	   			return (new ActionForward("/pages/indexUpdateView.jsp"));
	   		}
	   	
	   		try {
				String sIName			=	iuForm.getIndex_id().split("e")[1];
				int numIndex			=	Integer.parseInt(sIName);
				if(iuForm.getIndex_type_id()!=null){
					String indexType		=	iuForm.getIndex_type_id().split("e")[1];
					iType					=	Integer.parseInt(indexType);
				}
				
				String setValue			=	iuForm.getCalculate_settlement_value();
				String isActive			=	iuForm.getIs_active();
				if(iuForm.getCaptured_from()!=null){
					String caputedFrom	=	iuForm.getCaptured_from().split("e")[1];
					numCaptured			=	Integer.parseInt(caputedFrom);
				}
				if(iuForm.getCaptured_from()!="value0"){
					iuForm.setIs_captured("on");
				}
				
				String setGrowthValue1	=	iuForm.getGrowth_or_value();
				String setGrowthValue;
           		if(setGrowthValue1.equals("n")){
           			setGrowthValue=null;
           		}
				else if(setGrowthValue1.equals("g")){
           			setGrowthValue="g";
           		}
           		else{
           			setGrowthValue="v";
           		}
				if(isActive!=null && isActive.equals("on")){
					isActive="n";
				}
				else{
					isActive="y";
				}
				if(setValue!=null && setValue.equals("on")){
					setValue="y";
				}
				else{
					setValue="n";
				}
				
				
			
					
				/**Make The Entries In index_master Table*/
				
					PreparedStatement stmt = con.prepareStatement(ConnectInit.queries.getProperty("indexUpdate_update_index_master"));
	         		
					stmt.setString(1,iuForm.getIndex_name());
	         		stmt.setInt(2,iType);
	         		if(iuForm.getO_ric()==null || iuForm.getO_ric().equals("")){
	         			stmt.setString(3,null);
	         		}
	         		else{
	         			stmt.setString(3,iuForm.getO_ric());
	         		}
	         		stmt.setFloat(4,iuForm.getAlert_percentage());
	         		stmt.setFloat(5,iuForm.getRejection_percentage());
	         		if(iuForm.getIsin()==null || iuForm.getIsin().equals("")){
	         			stmt.setString(6,null);
	         		}
	         		else{
	         			stmt.setString(6,iuForm.getIsin());
	         		}
	         		stmt.setString(7,setValue);
	         		stmt.setString(8,iuForm.getM_start_time());
	         		stmt.setString(9,iuForm.getN_stop_time());
	         		stmt.setString(10,isActive);
	         	
	         		stmt.setInt(11,iuForm.getComputation_interval());
	         		
	         		stmt.setInt(12,numCaptured);
	         		stmt.setString(13,setGrowthValue);
	         		stmt.setInt(14,numIndex);
	         		stmt.executeUpdate();	
	         		stmt.close();
			
			} catch (NumberFormatException e1) {
				//e1.printStackTrace();
				Logging.error(" Error : "+e1.getMessage());
			} catch (SQLException e1) {
				//e1.printStackTrace();
				Logging.error(" Error : "+e1.getMessage());
			}
           	
	   	}
	   
	   	else if((iuForm.getIndex_id()).equals("value0")){
	   		    resetAllFields(iuForm);
	   	   		
	   	}
	   	else{
	   		/**
			 * Get The index_id To Get Remaining Values For Display 
			 * 
			 * */
			String sRName			=	iuForm.getIndex_id().split("e")[1];
			int num					=	Integer.parseInt(sRName);
			try{
	 				PreparedStatement stmt = con.prepareStatement(ConnectInit.queries.getProperty("indexUpdate_getall_entries_index_master"));	
	 				stmt.setInt(1,num);
	 				 rst = stmt.executeQuery();	
	 				while(rst.next()){
		           		iuForm.setIndex_name(rst.getString(23));
		           		iuForm.setCreation_date(rst.getString(1));
		           		String isActive	=	rst.getString(2);
		           		if(isActive.equals("y")){
		           			iuForm.setIs_active(null);
		           		}
		           		else{
		           			iuForm.setIs_active("on");
		           		}
		           		iuForm.setBase_date(rst.getString(3));
		           		iuForm.setBase_value(rst.getFloat(4));
		           		iuForm.setComputation_interval(rst.getInt(5));
		           		String isCaptured	=	rst.getString(6);
		           		String capturedFrom	=	rst.getString(7);
		           		iuForm.setCaptured_from("value"+capturedFrom);
		           		if(isCaptured!=null && isCaptured.equals("y")){
		           			iuForm.setIs_captured("on");
		           			
		           		}
		           		else{
		           			iuForm.setIs_captured(null);
		           			
		           		}
		           		iuForm.setM_start_time(rst.getString(8));
		           		iuForm.setN_stop_time(rst.getString(9));
		           		iuForm.setO_ric(rst.getString(10));
		           		String isChild		=	rst.getString(11);
		           		String isCustomized	=	rst.getString(21);
		           		String isTest		=	rst.getString(24);
		           		if(isTest!=null && isTest.equals("y")){
		           			iuForm.setTest_index("on");
		           		}
		           		else{
		           			iuForm.setTest_index(null);
		           		}
		           		int parentId		=	rst.getInt(12);
		           	
		           		if(isChild!=null && isChild.equals("y")){
		           			iuForm.setIs_child("child");
		           		}
		           		else if(isCustomized!=null && isCustomized.equals("y")){
		           			iuForm.setIs_child("customized");
		           		}
		           		else if(parentId!=0){
		           			iuForm.setIs_child("copy");
		           		}
		           		int baseCurrencyId		=	rst.getInt(13);
		           		int indexTypeId			= 	rst.getInt(14);
		           		iuForm.setAlert_percentage(rst.getFloat(15));
		           		iuForm.setRejection_percentage(rst.getFloat(16));
		           		String setGrowthValue	=	rst.getString(18);
		           		if(setGrowthValue==null){
		           			iuForm.setGrowth_or_value("n");
		           		}
		           		else if(setGrowthValue.equals("g")){
		           			iuForm.setGrowth_or_value("g");
		           		}
		           		else{
		           			iuForm.setGrowth_or_value("v");
		           		}
				        String setValue			=	rst.getString(19);
					        if(setValue!=null && setValue.equals("y")){
					        	iuForm.setCalculate_settlement_value("on");
					        }
					        else{
					        	iuForm.setCalculate_settlement_value(null);
					        }
				        iuForm.setIsin(rst.getString(20));
				      
				        int classCode			=	Integer.parseInt(rst.getString(17));
				        /**
						 * Fetch Out Parent Of Index 
						 * */
				        	
					        stmt = con.prepareStatement(ConnectInit.queries.getProperty("indexUpdate_select_index_name"));	
			 				stmt.setInt(1,parentId);
			 				ResultSet rst1 = stmt.executeQuery();	
			              	while(rst1.next()){
				           		iuForm.setParent_id(rst1.getString(1));
				           	}
			              	rst1.close();
			              	stmt.close();
						/**
						 * Fetch Out Base Currency 
						 * */
			                stmt = con.prepareStatement(ConnectInit.queries.getProperty("indexUpdate_select_currency_name"));	
			 				stmt.setInt(1,baseCurrencyId);
			 				ResultSet rst2 = stmt.executeQuery();	
			              	while(rst2.next()){
				           		iuForm.setBase_currency_id(rst2.getString(1));
				           	}
			              	rst2.close();
			              	stmt.close();
				       
				        /**
						 * Fetch Out Index Type
						 * */
			              	stmt = con.prepareStatement(ConnectInit.queries.getProperty("indexUpdate_select_index_type"));	
				 			stmt.setInt(1,indexTypeId);
				 			ResultSet rst3 = stmt.executeQuery();	
			              	while(rst3.next()){
			              		
				           		iuForm.setIndex_type_id("value"+rst3.getInt(1));
				           	}
			              	rst3.close();
			              	stmt.close();
				        /**
				         * Fetch Out Index Classification 
						* */
			              	stmt = con.prepareStatement(ConnectInit.queries.getProperty("indexUpdate_get_ind_classification_code"));	
				 			stmt.setInt(1,classCode);
				 			ResultSet rst4 = stmt.executeQuery();	
			              	while(rst4.next()){
					       		iuForm.setIndustry_classification_id(rst4.getString(6)+":"+rst4.getString(4)+" - "+rst4.getString(1));
					        }
			              	rst4.close();
			              	stmt.close();
				        
		           		
					}	
		   	}catch(Exception e){Logging.error(" Error : "+e.getMessage());}
	   		
	   	}
	}catch(Exception e){
		Logging.error(" Error : "+e.getMessage());
	}
	finally{
		try{
			if(con!=null)
				con.close();
		}catch(Exception e){
			Logging.error(" Error : Unable to close connection "+e.getMessage());
		}
	}
	   	
		return mapping.getInputForward();
	}
	public void resetAllFields(IndexUpdateForm iuForm){
		    iuForm.setIs_child(null);
	   		iuForm.setParent_id("");
	   		iuForm.setIndex_name("");
	   		iuForm.setIndex_type_id("value0");
	   		iuForm.setIndustry_classification_id("");
	   		iuForm.setCreation_date("");
	   		iuForm.setBase_date("");
	   	    iuForm.setBase_value(0);
	   		iuForm.setO_ric("");
	   		iuForm.setAlert_percentage(0);
	   		iuForm.setRejection_percentage(0);
			iuForm.setComputation_interval(0);
	   		iuForm.setBase_currency_id("");
	   		iuForm.setIsin("");
	   		iuForm.setCalculate_settlement_value(null);
	   		iuForm.setM_start_time("");
	   		iuForm.setN_stop_time("");
	   		iuForm.setIs_active("");
	   		iuForm.setCaptured_from("value0");
	   		iuForm.setGrowth_or_value(null);
	   		iuForm.setIs_captured(null);
	   		iuForm.setTest_index(null);
	   
	}
 	


}
