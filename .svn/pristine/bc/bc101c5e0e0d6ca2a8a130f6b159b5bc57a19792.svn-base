/*
 * Created on Jun 12, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.fixedincome;

/**
 * @author neha
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */



import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * @author Vivek
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class FixedIncomeIndexCompositionAction extends Action {
Logger Logging = Logger.getLogger(FixedIncomeIndexCompositionAction.class);
	PreparedStatement preparedStatement;

	Connect c = ConnectInit.getConnect();

	String user_id = null;
    
	ResultSet resultSet;
   
	ActionErrors errors = new ActionErrors();
	
	Hashtable cmpdata=new Hashtable();
	Hashtable mcapdata=new Hashtable();
	Hashtable tvoldata=new Hashtable();
	Hashtable tvaldata=new Hashtable();
	Hashtable filterdata=new Hashtable();
	Hashtable cmpmain=new Hashtable();
	 Hashtable indclassdata=new Hashtable();
	ArrayList list=new ArrayList();
	String cpbutton=null;
	int stkidcmp=0;
	String stknamecmp=null,compid=null;
	String cid = null, n = null, d = null, shn = null, stockTypeId = null, currencyId = null, ranking_type = null, stock_ex_id = null, country_id = null;
	int mcap_rank = 0, avg_trading_volume_rank = 0, avg_trading_value_rank = 0, ranking_perio = 0, listed_history = 0;
	//int mcap_ranking_duration = 0, avg_traded_volume_duration = 0, avg_traded_value_duration = 0;
	double min_weight = 0, avg_traded_volume_range2 = 0, iwf = 0, mcap_range1 = 0, mcap_range2 = 0, max_weight = 0, avg_traded_volume_range1 = 0, avg_traded_value_range1 = 0, avg_traded_value_range2 = 0;
    String mcap_from_duration=null,mcap_to_duration=null;
    String value_from_duration=null,value_to_duration=null;
    String volume_from_duration=null,volume_to_duration=null;
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// c.getConnectionForTransaction();

		FixedIncomeIndexCompositionForm compositionForm = (FixedIncomeIndexCompositionForm) form;
		
		
			if (compositionForm.getOperation() != null && compositionForm.getOperation().trim().equals("back")) {
				return new ActionForward("/pages/fixedincome/FixedIncomeDefineIndex.jsp");
			}
			try{
		                	HttpSession session = request.getSession();
			             Properties prop = new Properties();
			try {
				
				java.net.URL imgURL = FixedIncomeComputeIndexForm.class.getResource("FixedIncomeComputeIndexForm.class");
				
				String resourcepth = imgURL.toString();
				
				resourcepth = resourcepth.substring(6, (resourcepth.lastIndexOf("/WEB-INF/") + 8));
				
				resourcepth = resourcepth + "/classes/resources/database.properties";
				prop.load(new FileInputStream(resourcepth));
			  } catch (Exception ex) {
				Logging.error(" Error : " + ex.getMessage());
			  }
			  String use_user = prop.getProperty("use_user");
			  user_id = session.getAttribute("userid").toString();
			   Logging.debug("1");
			if (compositionForm.getOperation() != null && compositionForm.getOperation().trim().equals("Submit")) {
				
				FixedIncomeDefineIndexForm pageForm = (FixedIncomeDefineIndexForm) session.getAttribute("FixedIncomeDefineIndexForm");
				   Logging.debug("2");
				if (pageForm.getS_applyClassification() == null	|| !pageForm.getS_applyClassification().equals("on")) {
					  Logging.debug("3");
				
					  createIndex(pageForm, compositionForm, request, use_user);
					  Logging.debug("3.1 : " + errors.size());
				   	if (errors.size() > 0) {
						    Logging.debug("error : " + errors.get());
						    Iterator iterator = errors.get();
					        if (iterator.hasNext()) {
							   String string = (String) iterator.next();
							 //  Logging.getDebug("error1 : " + string);
						         }
						           ActionMessages actionMessages;

				     	}
					if (!errors.isEmpty()) {
						return (new ActionForward(mapping.getInput()));
					}
					Logging.debug("forwarding to indexhome with base date : " + pageForm.getD_baseDate());
			       return new ActionForward("/pages/fixedincome/FixedIncomeIndexHome.jsp?D1="	+ compositionForm.getIndexId()+ "&B2=Compute&basedate="	+ pageForm.getD_baseDate());
				} 
				else if (request.getParameter("checktocreatechild") != null && request.getParameter("checktocreatechild").equals("on")) {
					         Logging.debug("4");
					           createIndex(pageForm, compositionForm, request, use_user);
					         if (!errors.isEmpty()) {
						           return (new ActionForward(mapping.getInput()));
					             }
					return new ActionForward("/pages/fixedincome/FixedIncomeIndexHome.jsp?D1="	+ compositionForm.getIndexId()+ "&B2=Compute&basedate="+ pageForm.getD_baseDate());
				}
				else if (request.getParameter("checktocreatechild") == null) {
					                 Logging.debug("5");
					         return new ActionForward("/pages/fixedincome/GetbaseValue.jsp?fromcomposition=yes");
				       }
			    }

		} catch (Exception e) {
			Logging.error("Error : " + e.getMessage());
			
			// TODO: handle exception
		}
		

		//  start ----> 19th july 2007 ,code added by neha for index compliance implementation
		try{
		
		      cpbutton= compositionForm.getCmpbutton();
		      boolean flag=compositionForm.isMarked();
		      Logging.debug("value of mainnnnnn if"+ flag);
			HttpSession session1 = request.getSession();
			FixedIncomeDefineIndexForm idxForm=(FixedIncomeDefineIndexForm)session1.getAttribute("FixedIncomeDefineIndexForm");
			// main if started
			if(flag ==true)
			{          
				       
				      cmpdata=compositionForm.getcmpdata();
				    if (!cmpdata.isEmpty()) {
					          Logging.debug("Inside isEmpty  " +cmpdata.isEmpty());
					          for (Enumeration e = cmpdata.keys(); e.hasMoreElements();) {
				 
				                String id = e.nextElement().toString();
				                String stkname =(cmpdata.get(id)).toString();
			                   // Logging.debug("stkid ==  cmp"+id);
				               // Logging.debug("stkname == cmp"+stkname);
					         }
					
				           }//   main filter
				    String cmpname=idxForm.getName_list();
				    int compid=Integer.parseInt(cmpname);
				   Logging.debug("Compliance id ======>"+compid);
				      Connection connection = null;
						PreparedStatement stmt=null,ppt=null,stmt11=null,stmt12=null,stmt2 = null,stmt31=null,stmt32=null,stmt4=null;
						PreparedStatement stvt=null,stvt11=null,stvt12=null,stvt2 = null,stvt31=null,stvt32=null,stvt4=null;
						PreparedStatement stvat=null,stvat11=null,stvat12=null,stvat2 = null,stvat31=null,stvat32=null,stvat4=null;
						ResultSet rvt=null,pvt=null,rvt11=null,rvt12=null,rvt2 = null,rvt31=null,rvt32=null,rvt4=null;
						ResultSet rvat11=null,rvat12=null,rvat2 = null,rvat31=null,rvat32=null,rvat4=null;
						ResultSet rst=null,pst=null,rst11=null,rst12=null,rst2 = null,rst31=null,rst32=null,rst4=null;
						Connect c = ConnectInit.getConnect();
						
						
						try {
							if (connection == null)
								connection = c.getdbConnection();
							
							if(compid>0){
								 stmt = connection.prepareStatement(ConnectInit.queries.getProperty("get_compliance_name_desc"));
								stmt.setInt(1,compid);
								rst = stmt.executeQuery();
								while (rst.next()) {
									cid = rst.getString(1);
									n = rst.getString(2);
									shn = rst.getString(3);
									d = rst.getString(4);
									mcap_rank = rst.getInt(5);
									min_weight = rst.getDouble(6);
									avg_trading_volume_rank = rst.getInt(7);
									avg_trading_value_rank = rst.getInt(8);
									listed_history = rst.getInt(9);
									iwf = rst.getFloat(10);
									stockTypeId = rst.getString(11);
									currencyId = rst.getString(12);
									ranking_type = rst.getString(13);
									mcap_range1 = rst.getDouble(14);
									mcap_range2 = rst.getDouble(15);
									max_weight = rst.getDouble(16);
									avg_traded_volume_range1 = rst.getDouble(17);
									avg_traded_value_range1 = rst.getDouble(18);
									avg_traded_value_range2 = rst.getDouble(19);
									stock_ex_id = rst.getString(20);
									country_id = rst.getString(21);
									avg_traded_volume_range2 = rst.getDouble(22);
									mcap_from_duration = rst.getString(23);
									mcap_to_duration = rst.getString(24);
									value_from_duration=rst.getString(25);
									value_to_duration=rst.getString(26);
									volume_from_duration=rst.getString(27);
									volume_to_duration=rst.getString(28);
									

								}
								// industry classification filter
								ppt = connection.prepareStatement(ConnectInit.queries.getProperty("get_ind_class_filter"));
								ppt.setString(1,stock_ex_id);
								ppt.setString(2,stockTypeId);
								ppt.setInt(3,compid);
								pst = ppt.executeQuery();
								while(pst.next()){
									int stkid=pst.getInt(1);
									String stkname=pst.getString(2);
									indclassdata.put(""+stkid,stkname);
								}
								
								
							}
							//  mcap filter
							  
							if(mcap_range1>0 && mcap_range2>0){
								if(mcap_from_duration!=null && mcap_to_duration !=null){
									if(mcap_rank>0){
										if(ranking_type!=null && ranking_type.trim().equals("t")){
										        stmt11 = connection.prepareStatement(ConnectInit.queries.getProperty("mcapquery11"));
											    stmt11.setString(1,stockTypeId);
											    stmt11.setString(2,stock_ex_id);
											    stmt11.setString(3,mcap_from_duration);
											    stmt11.setString(4,mcap_to_duration);
											    stmt11.setDouble(5,mcap_range1);
											    stmt11.setDouble(6,mcap_range2);
											    stmt11.setInt(7,mcap_rank);
											    rst11 = stmt11.executeQuery();
											     while(rst11.next()){
												         int stkid=rst11.getInt(1);
												         double avgmcv=rst11.getDouble(2);
												         mcapdata.put(""+stkid,""+avgmcv);
												        }
										          }
										   else{
										      	
										    	    if(ranking_type!=null && ranking_type.trim().equals("b")){
													    stmt12 = connection.prepareStatement(ConnectInit.queries.getProperty("mcapquery12"));
														stmt12.setString(1,stockTypeId);
														stmt12.setString(2,stock_ex_id);
														stmt12.setString(3,mcap_from_duration);
														stmt12.setString(4,mcap_to_duration);
														stmt12.setDouble(5,mcap_range1);
														stmt12.setDouble(6,mcap_range2);
														stmt12.setInt(7,mcap_rank);
														rst12 = stmt12.executeQuery();
														while(rst12.next()){
															   int stkid=rst12.getInt(1);
															   double avgmcv=rst12.getDouble(2);
															   mcapdata.put(""+stkid,""+avgmcv);
															  }
													       }
										        }
											
									       }// mcap_rank if end
									     else{
									     	stmt2 = connection.prepareStatement(ConnectInit.queries.getProperty("mcapquery2"));
									     	stmt2.setString(1,stockTypeId);
											stmt2.setString(2,stock_ex_id);
											stmt2.setString(3,mcap_from_duration);
											stmt2.setString(4,mcap_to_duration);
											stmt2.setDouble(5,mcap_range1);
											stmt2.setDouble(6,mcap_range2);
											rst2 = stmt2.executeQuery();
											while(rst2.next()){
												int stkid=rst2.getInt(1);
												double avgmcv=rst2.getDouble(2);
												mcapdata.put(""+stkid,""+avgmcv);
											     }
									        }
									
								       }// end of duration if
								  else{
								  	
								     	if(mcap_rank>0){
								     		    if(ranking_type!=null && ranking_type.trim().equals("t")){
										    stmt31 = connection.prepareStatement(ConnectInit.queries.getProperty("mcapquery31"));
										    stmt31.setString(1,stockTypeId);
											stmt31.setString(2,stock_ex_id);
											stmt31.setDouble(3,mcap_range1);
											stmt31.setDouble(4,mcap_range2);
											stmt31.setInt(5,mcap_rank);
											rst31 = stmt31.executeQuery();
											while(rst31.next()){
												int stkid=rst31.getInt(1);
												double avgmcv=rst31.getDouble(2);
												mcapdata.put(""+stkid,""+avgmcv);
												 
											       }
								     		}
								     		else{
								     			 if(ranking_type!=null && ranking_type.trim().equals("b")){
													 stmt32 = connection.prepareStatement(ConnectInit.queries.getProperty("mcapquery32"));
													    stmt32.setString(1,stockTypeId);
														stmt32.setString(2,stock_ex_id);
														stmt32.setDouble(3,mcap_range1);
														stmt32.setDouble(4,mcap_range2);
														stmt32.setInt(5,mcap_rank);
														rst32 = stmt32.executeQuery();
														while(rst32.next()){
															int stkid=rst32.getInt(1);
															double avgmcv=rst32.getDouble(2);
															mcapdata.put(""+stkid,""+avgmcv);
															 
														       }
											     		}
								     			
								     	     	}
								     		
									         }//end of mcap_if
									     else{
									     	  stmt4 = connection.prepareStatement(ConnectInit.queries.getProperty("mcapquery4"));
									     	    stmt4.setString(1,stockTypeId);
												stmt4.setString(2,stock_ex_id);
												stmt4.setDouble(3,mcap_range1);
												stmt4.setDouble(4,mcap_range2);
											  rst4 = stmt4.executeQuery();
											  while(rst4.next()){
												int stkid=rst4.getInt(1);
												double avgmcv=rst4.getDouble(2);
												mcapdata.put(""+stkid,""+avgmcv);
											     }
									          }
								       }
								
							} // filter ended.
							
                              //  Average traded volume  filter
							  
							if(avg_traded_volume_range1 >0 && avg_traded_volume_range2>0){
								if(volume_from_duration!=null && volume_to_duration !=null){
									if(avg_trading_volume_rank>0){
										if(ranking_type!=null && ranking_type.trim().equals("t")){
										        stvt11 = connection.prepareStatement(ConnectInit.queries.getProperty("tvolquery11"));
											    stvt11.setString(1,stockTypeId);
											    stvt11.setString(2,stock_ex_id);
											    stvt11.setString(3,volume_from_duration);
											    stvt11.setString(4,volume_to_duration);
											    stvt11.setDouble(5,avg_traded_volume_range1);
											    stvt11.setDouble(6,avg_traded_volume_range2);
											    stvt11.setInt(7,avg_trading_volume_rank);
											    rvt11 = stvt11.executeQuery();
											     while(rvt11.next()){
												         int stkid=rvt11.getInt(1);
												         double avgtvol=rvt11.getDouble(2);
												         tvoldata.put(""+stkid,""+avgtvol);
												        }
										          }
										   else{
										      	
										    	    if(ranking_type!=null && ranking_type.trim().equals("b")){
													    stvt12 = connection.prepareStatement(ConnectInit.queries.getProperty("tvolquery12"));
														stvt12.setString(1,stockTypeId);
														stvt12.setString(2,stock_ex_id);
														stvt12.setString(3,volume_from_duration);
														stvt12.setString(4,volume_to_duration);
														stvt12.setDouble(5,avg_traded_volume_range1);
														stvt12.setDouble(6,avg_traded_volume_range2);
														stvt12.setInt(7,avg_trading_volume_rank);
														rvt12 = stvt12.executeQuery();
														while(rvt12.next()){
															   int stkid=rvt12.getInt(1);
															   double avgtvol=rvt12.getDouble(2);
															   tvoldata.put(""+stkid,""+avgtvol);
															  }
													       }
										        }
											
									       }// avg traded if end -------
									     else{
									     	stvt2 = connection.prepareStatement(ConnectInit.queries.getProperty("tvolquery2"));
									     	stvt2.setString(1,stockTypeId);
											stvt2.setString(2,stock_ex_id);
											stvt2.setString(3,volume_from_duration);
											stvt2.setString(4,volume_to_duration);
											stvt2.setDouble(5,avg_traded_volume_range1);
											stvt2.setDouble(6,avg_traded_volume_range2);
											rvt2 = stvt2.executeQuery();
											while(rvt2.next()){
												int stkid=rvt2.getInt(1);
												double avgtvol=rvt2.getDouble(2);
												tvoldata.put(""+stkid,""+avgtvol);
											     }
									        }
									
								       }// end of duration if
								  else{
								  	
								     	if(avg_trading_volume_rank>0){
								     		    if(ranking_type!=null && ranking_type.trim().equals("t")){
										    stvt31 = connection.prepareStatement(ConnectInit.queries.getProperty("tvolquery31"));
										    stvt31.setString(1,stockTypeId);
											stvt31.setString(2,stock_ex_id);
											stvt31.setDouble(3,avg_traded_volume_range1);
											stvt31.setDouble(4,avg_traded_volume_range2);
											stvt31.setInt(5,avg_trading_volume_rank);
											rvt31 = stvt31.executeQuery();
											while(rvt31.next()){
												int stkid=rvt31.getInt(1);
												double avgtvol=rvt31.getDouble(2);
												tvoldata.put(""+stkid,""+avgtvol);
												 
											       }
								     		}
								     		else{
								     			 if(ranking_type!=null && ranking_type.trim().equals("b")){
													 stvt32 = connection.prepareStatement(ConnectInit.queries.getProperty("tvolquery32"));
													    stvt32.setString(1,stockTypeId);
														stvt32.setString(2,stock_ex_id);
														stvt32.setDouble(3,avg_traded_volume_range1);
														stvt32.setDouble(4,avg_traded_volume_range2);
														stvt32.setInt(5,avg_trading_volume_rank);
														rvt32 = stvt32.executeQuery();
														while(rvt32.next()){
															int stkid=rvt32.getInt(1);
															double avgtvol=rvt32.getDouble(2);
															tvoldata.put(""+stkid,""+avgtvol);
															 
														       }
											     		}
								     			
								     	     	}
								     		
									         }//end of mcap_if
									     else{
									     	  stvt4 = connection.prepareStatement(ConnectInit.queries.getProperty("tvolquery4"));
									     	    stvt4.setString(1,stockTypeId);
												stvt4.setString(2,stock_ex_id);
												stvt4.setDouble(3,avg_traded_volume_range1);
												stvt4.setDouble(4,avg_traded_volume_range2);
											    rvt4 = stvt4.executeQuery();
											  while(rvt4.next()){
												int stkid=rvt4.getInt(1);
												double avgtvol=rvt4.getDouble(2);
												tvoldata.put(""+stkid,""+avgtvol);
											     }
									          }
								       }
								
							} // filter ended.
						    //  Average traded value  filter
							  
							if(avg_traded_value_range1 >0 && avg_traded_value_range2>0){
								if(value_from_duration!=null && value_to_duration !=null){
									if(avg_trading_value_rank>0){
										if(ranking_type!=null && ranking_type.trim().equals("t")){
										        stvat11 = connection.prepareStatement(ConnectInit.queries.getProperty("tvalquery11"));
											    stvat11.setString(1,stockTypeId);
											    stvat11.setString(2,stock_ex_id);
											    stvat11.setString(3,value_from_duration);
											    stvat11.setString(4,value_to_duration);
											    stvat11.setDouble(5,avg_traded_value_range1);
											    stvat11.setDouble(6,avg_traded_value_range2);
											    stvat11.setInt(7,avg_trading_value_rank);
											    rvat11 = stvat11.executeQuery();
											     while(rvat11.next()){
												         int stkid=rvat11.getInt(1);
												         double avgtval=rvat11.getDouble(2);
												         tvaldata.put(""+stkid,""+avgtval);
												        }
										          }
										   else{
										      	
										    	    if(ranking_type!=null && ranking_type.trim().equals("b")){
													    stvat12 = connection.prepareStatement(ConnectInit.queries.getProperty("tvalquery12"));
														stvat12.setString(1,stockTypeId);
														stvat12.setString(2,stock_ex_id);
														stvat12.setString(3,value_from_duration);
														stvat12.setString(4,value_to_duration);
														stvat12.setDouble(5,avg_traded_value_range1);
														stvat12.setDouble(6,avg_traded_value_range2);
														stvat12.setInt(7,avg_trading_value_rank);
														rvat12 = stvat12.executeQuery();
														while(rvat12.next()){
															   int stkid=rvat12.getInt(1);
															   double avgtval=rvat12.getDouble(2);
															   tvaldata.put(""+stkid,""+avgtval);
															  }
													       }
										        }
											
									       }// avg traded if end -------
									     else{
									     	stvat2 = connection.prepareStatement(ConnectInit.queries.getProperty("tvalquery2"));
									     	stvat2.setString(1,stockTypeId);
											stvat2.setString(2,stock_ex_id);
											stvat2.setString(3,value_from_duration);
											stvat2.setString(4,value_to_duration);
											stvat2.setDouble(5,avg_traded_value_range1);
											stvat2.setDouble(6,avg_traded_value_range2);
											rvat2 = stvat2.executeQuery();
											while(rvat2.next()){
												int stkid=rvat2.getInt(1);
												double avgtval=rvat2.getDouble(2);
												tvaldata.put(""+stkid,""+avgtval);
											     }
									        }
									
								       }// end of duration if
								  else{
								  	
								     	if(avg_trading_value_rank>0){
								     		    if(ranking_type!=null && ranking_type.trim().equals("t")){
										    stvat31 = connection.prepareStatement(ConnectInit.queries.getProperty("tvalquery31"));
										    stvat31.setString(1,stockTypeId);
											stvat31.setString(2,stock_ex_id);
											stvat31.setDouble(3,avg_traded_value_range1);
											stvat31.setDouble(4,avg_traded_value_range2);
											stvat31.setInt(5,avg_trading_value_rank);
											rvat31 = stvat31.executeQuery();
											while(rvat31.next()){
												int stkid=rvat31.getInt(1);
												double avgtval=rvat31.getDouble(2);
												tvaldata.put(""+stkid,""+avgtval);
												 
											       }
								     		}
								     		else{
								     			 if(ranking_type!=null && ranking_type.trim().equals("b")){
													 stvat32 = connection.prepareStatement(ConnectInit.queries.getProperty("tvalquery32"));
													    stvat32.setString(1,stockTypeId);
														stvat32.setString(2,stock_ex_id);
														stvat32.setDouble(3,avg_traded_value_range1);
														stvat32.setDouble(4,avg_traded_value_range2);
														stvat32.setInt(5,avg_trading_value_rank);
														rvat32 = stvat32.executeQuery();
														while(rvat32.next()){
															int stkid=rvat32.getInt(1);
															double avgtval=rvat32.getDouble(2);
															tvaldata.put(""+stkid,""+avgtval);
															 
														       }
											     		}
								     			
								     	     	}
								     		
									         }//end of mcap_if
									     else{
									     	  stvat4 = connection.prepareStatement(ConnectInit.queries.getProperty("tvalquery4"));
									     	    stvat4.setString(1,stockTypeId);
												stvat4.setString(2,stock_ex_id);
												stvat4.setDouble(3,avg_traded_value_range1);
												stvat4.setDouble(4,avg_traded_value_range2);
											    rvat4 = stvat4.executeQuery();
											  while(rvat4.next()){
												int stkid=rvat4.getInt(1);
												double avgtval=rvat4.getDouble(2);
												tvaldata.put(""+stkid,""+avgtval);
											     }
									          }
								       }
								
							} // filter ended.
							
							rst.close();
							stmt.close();
							rst11.close();
							rvt11.close();
							rvat11.close();
							rst12.close();
							rvt12.close();
							rvat12.close();
					        stmt11.close();
					        stvt11.close();
					        stvat11.close();
							stmt12.close();
							stvt12.close();
							stvat12.close();
							rst2.close();
							rvt2.close();
							rvat2.close();
							stmt2.close();
							stvt2.close();
							stvat2.close();
							rst31.close();
							rvt31.close();
							rvat31.close();
							rst32.close();
							rvt32.close();
							rvat32.close();
							stmt31.close();
							stvt32.close();
							stvat32.close();
							stmt32.close();
							stvt31.close();
							stvat31.close();
							rst4.close();
							rvt4.close();
							rvat4.close();
							stmt4.close();
							stvt4.close();
							stvat4.close();
							
						} catch (Exception e) {
							Logging.error(" Error : " + e.getMessage());
						} finally {
							try {
								if (connection != null)
									connection.close();
							} catch (Exception ee) {
								Logging.error(" Error : Unable to close connection "+ ee.getMessage());
							}
						}
						
					//  mcap filter o/p
						  if (!mcapdata.isEmpty()) {
					        //  Logging.getDebug("Inside isEmpty  " +mcapdata.isEmpty());
					          for (Enumeration e = mcapdata.keys(); e.hasMoreElements();) {
				 
				                String sid = e.nextElement().toString();
				                String avgmcv =(mcapdata.get(sid)).toString();
			                   // Logging.debug("stkid ==> mcap f"+sid);
				               // Logging.debug("avgmcv == mcap f"+avgmcv);
					           }
					
				            }
						  
						// ind class filter output
						  if (!indclassdata.isEmpty()) {
					          Logging.debug("Inside isEmpty  " +indclassdata.isEmpty());
					          for (Enumeration e = indclassdata.keys(); e.hasMoreElements();) {
				 
				                String sid = e.nextElement().toString();
				                String stkname =(indclassdata.get(sid)).toString();
			                   // Logging.debug("stkid ==>indi class filter"+sid);
				               // Logging.debug("stkname ==> indi class filter"+stkname);
					           }
					
				            }
						  
						  // average traded volume output
						  
						  if (!tvoldata.isEmpty()) {
					        //  Logging.getDebug("Inside isEmpty  " +tvoldata.isEmpty());
					          for (Enumeration e = tvoldata.keys(); e.hasMoreElements();) {
				 
				                String sid = e.nextElement().toString();
				                String avgtvol =(tvoldata.get(sid)).toString();
			                   // Logging.debug("stkid ==> tvol f"+sid);
				               // Logging.debug("avgtvol== tvol f"+avgtvol);      		
				                			                		
					           }
					
				            }
						  
						  
						  // average traded value output
						  if (!tvaldata.isEmpty()) {
					         // Logging.getDebug("Inside isEmpty  " +tvaldata.isEmpty());
					          for (Enumeration e = tvaldata.keys(); e.hasMoreElements();) {
				 
				                String sid = e.nextElement().toString();
				                String avgtval =(tvaldata.get(sid)).toString();
			                   // Logging.debug("stkid ==> tvalue f"+sid);
				               // Logging.debug("avgtvalue== tvalue f"+avgtval);      		
				                			                		
					           }
					
				            }
						  // comparison of four filters to find out common 
						  if(!mcapdata.isEmpty() && !indclassdata.isEmpty() && !tvoldata.isEmpty() && !tvaldata.isEmpty()){
							for(Enumeration e1 = indclassdata.keys(); e1.hasMoreElements();) {
								    String istkid = e1.nextElement().toString().trim();
					                String istkname =(indclassdata.get(istkid)).toString();
					            for(Enumeration e2 = mcapdata.keys(); e2.hasMoreElements();) {
					   				String mstkid = e2.nextElement().toString().trim();
					   			   for(Enumeration e3 = tvoldata.keys(); e3.hasMoreElements();){
					   			      String tvolstkid = e3.nextElement().toString().trim();
					   			      for(Enumeration e4 = tvaldata.keys(); e4.hasMoreElements();){
					   			        String tvalstkid = e4.nextElement().toString().trim();
					   			         if(istkid.equals(mstkid) && mstkid.equals(tvolstkid) && tvolstkid.equals(tvalstkid)){
					   			         	
					   			         	   filterdata.put(istkid,istkname);
					   			         	
					   			         }
					   			        
					   			      }  
					   			   }
								
					             }
							  }
						   }
						  
						  // filterdata common of four filters output 
						  if (!filterdata.isEmpty()) {
					         // Logging.getDebug("Inside isEmpty  " +filterdata.isEmpty());
					          for (Enumeration e = filterdata.keys(); e.hasMoreElements();) {
				 
					          	    String fstkid = e.nextElement().toString();
					                String fstkname =(filterdata.get(fstkid)).toString();
				                   // Logging.debug("filter data stock iid ======> "+fstkid);
					               // Logging.debug("filter data stkname =====> "+fstkname);    		
				                			                		
					           }
					
				            }
						  
					
						// comparison of two collection main output  
						  
						  if(!cmpdata.isEmpty() && !filterdata.isEmpty()){
						  	for (Enumeration e1 = cmpdata.keys(); e1.hasMoreElements();) {
								 
								    String id1 = e1.nextElement().toString().trim();
								    int stockID=Integer.parseInt(id1);
								    String stkname1 =(cmpdata.get(id1)).toString();
								      for (Enumeration e2 = filterdata.keys(); e2.hasMoreElements();) {
										 
										      String id2 = e2.nextElement().toString().trim();
										          if(id1.equals(id2)){  
										         
										           	cmpmain.put(id1,stkname1);
										           	StockDetails std=new StockDetails(stockID,stkname1);
										           	list.add(std); 
								   }   
							    }		          
							 }
						  	 compositionForm.setCmpmaindata(cmpmain);
						  	 compositionForm.setTabledata(list);
						  	 compositionForm.setHv1("yes");
						  	
						  }
						  
						  //display main output data of index compliance
						  if (!cmpmain.isEmpty()) {
					         // Logging.getDebug("Inside isEmpty  " +cmpmain.isEmpty());
					          for (Enumeration e = cmpmain.keys(); e.hasMoreElements();) {
				 
				                String stkid = e.nextElement().toString();
				                String stkname =(cmpmain.get(stkid)).toString();
			                   // Logging.debug("main stock  id"+stkid);
				                //Logging.debug("main  stkname"+stkname);
					           }
					
				            }
						  
						
			}//end of main if 
			
		}catch (Exception e) {
			        Logging.error("Error : " + e.getMessage());
			         
			 
		                }
		
		
		
			// --------> code ended for index compliance done by neha .
		
		
		
		
		
		return (mapping.getInputForward());
	}

	public void createIndex(FixedIncomeDefineIndexForm pageForm,
			FixedIncomeIndexCompositionForm compositionForm, HttpServletRequest request,
			String use_user) {
		Properties prop = new Properties();
		String queryforspecialindex = null;
		String query = null;
		if (use_user != null && use_user.equals("yes")) {
			try {
				prop.load(new FileInputStream(Connect.resourceurl
						+ "resources/user_query.properties"));
				query = prop.getProperty("check_index_name");
				queryforspecialindex = prop
						.getProperty("insert_into_index_master");
			} catch (Exception ex) {
				//Logging.getError("Error : " + ex.getMessage());
			}
		} else {
			query = ConnectInit.queries.getProperty("check_index_name");
			queryforspecialindex = ConnectInit.queries
					.getProperty("insert_into_index_master");
		}
		boolean flag = QueryClass1.fixedIncomeChkIndexkname(pageForm.getS_indexName(),
				use_user, user_id, query);
		Logging.info("flag for duplicate Index : " + flag);
		if (!flag) {
			errors.add("s_indexName", new ActionError(
					"Error.message.SameIndexName"));
			saveErrors(request, errors);
			return;
		}

		//if index not a captured one and is either of type currency or total
		// returns
		/*
		 * String queryforspecialindex = c.queries
		 * .getProperty("insert_into_index_master");
		 */
		//c.getConnectionForTransaction();
		try {
			try {
				pageForm.getCon().rollback();
			} catch (Exception e) {
				// TODO: handle exception
			}
			pageForm.getCon().setAutoCommit(false);

		} catch (Exception e) {
			Logging.debug("database commited ");
			// TODO: handle exception
		}

		try {
			QueryClass.fixedIncomeInsertIntoIndexMaster(queryforspecialindex, pageForm,
					use_user, user_id);
			compositionForm.setIndexId(pageForm.getI_indexID());
		} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"indexcompose.createindex"));
			saveErrors(request, errors);
			// return to input page
			try {
				try {
					pageForm.getCon().rollback();
				} catch (Exception ee) {
					// TODO: handle exception
				}
				pageForm.getCon().setAutoCommit(true);

			} catch (Exception exception) {
				Logging.debug("database commited ");
				// TODO: handle exception
			}
			//Logging.log	.debug("StockDetailsCollection: Error inserting in table "+ e.getMessage());

			// TODO: handle exception
		}
		storeComposition(pageForm.getI_indexID(), pageForm
				.getCon(), ConnectInit.queries
				.getProperty("insert_into_fixed_income_index_composition"), compositionForm,
				request, pageForm);

	/*	if (pageForm.getS_applyClassification() != null
				&& pageForm.getS_applyClassification().equals("on")) {
			//Logging.getDebug("request.getParameter(\"checktocreatechild\") : "	+ request.getParameter("checktocreatechild"));
			if (request.getParameter("checktocreatechild").equals("on")) {
				try {
					CallableStatement cs;
					cs = pageForm.getCon().prepareCall("{ call public.define_and_compose_all_child_index(?) }");

					int inid = new Integer(compositionForm.getIndexId())
							.intValue();
					cs.setInt(1, inid);
				//	Logging.getDebug("Executed procedure : " + cs.execute());
				} catch (Exception ex) {
				//	Logging.getDebug("Error in automatically creating child indices :"	+ ex);
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
							"indexcompose.automaticchildcreation"));
					saveErrors(request, errors);
					// return to input page
					try {
						try {
							pageForm.getCon().rollback();
						} catch (Exception ee) {
							// TODO: handle exception
						}
						pageForm.getCon().setAutoCommit(true);

					} catch (Exception exception) {
						//Logging.getInfo("database commited ");
						// TODO: handle exception
					}

				}

			}
		}*/

	}

	public void storeComposition(String indexID, Connection con, String query,
			FixedIncomeIndexCompositionForm compositionForm, HttpServletRequest request,
			FixedIncomeDefineIndexForm pageform) {
		Hashtable destinationTable = compositionForm.getDestinationTable();
		if (!destinationTable.isEmpty()) {
			Logging.debug("Inside isEmpty  " + destinationTable.isEmpty()+ " and  query is " + query);
			try {

				PreparedStatement psmt = con.prepareStatement(query);
				Statement st = con.createStatement();

				int id1 = 0;
				ResultSet rs = null;
				for (Enumeration e = destinationTable.keys(); e
						.hasMoreElements();) {
					/*
					 * rs = st.executeQuery("select nextval('index_stock_id')");
					 * 
					 * if (rs.next()) id1 = rs.getInt(1);
					 */
					Logging.debug("id : " + id1 + " : "	+ pageform.getS_indexType());

					String id = e.nextElement().toString();
					StockDetails sd = (StockDetails) destinationTable.get(id);
					///// added by neha for index compliance
				     stkidcmp =sd.getStockID();
					   stknamecmp=sd.getStockName(); 
					   
					Logging.debug("indexID : " + indexID + "  : "+ request.getParameter("iwf" + sd.getStockID()));
					//psmt.setInt(1, id1);
					if (compositionForm.indexTypebackup.equals("1")) {
						psmt.setDouble(1, 1.0);
					} else {
						if (pageform.getS_indexType().equals("2")) {
							String string = request.getParameter("iwf"
									+ sd.getStockID());
							psmt.setString(1, string);
						} else {
							psmt.setDouble(1, sd.getAdjustedIWF());
						}
					}
					psmt.setString(2, QueryClass.formatDate());
					psmt.setInt(3, Integer.parseInt(indexID));
					psmt.setInt(4, 0);
					if (pageform.getS_indexType().equals("6")) {
						double d=new Double(request.getParameter("adjmcap"
								+ sd.getStockID())).doubleValue();
						psmt.setDouble(5,d);
					}else{
					psmt.setDouble(5, sd.getMktCapital1(2, compositionForm
							.getParentCurrencyId(), sd.getCurrencyId()));
					}
					psmt.setInt(6, sd.getStockID());
					psmt.executeUpdate();
					rs = null;
				}
				//Logging.getDebug("Composition stored successfully!!! ");
				Logging.debug("Composition stored successfully!!! ");
				con.commit();
				pageform.getCon().commit();
			} catch (Exception e) {

				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
						"indexcompose.storecomposition"));
				saveErrors(request, errors);
				// return to input page
				try {
					try {
						pageform.getCon().rollback();
					} catch (Exception ee) {
						// TODO: handle exception
						//Logging.getError("Error : " + ee.getMessage());
						Logging.debug("Error : " + ee.getMessage());
					}
					pageform.getCon().setAutoCommit(true);

				} catch (Exception exception) {
					//Logging.getInfo("database commited ");
					// TODO: handle exception
				}
				//Logging.log	.error("StockDetailsCollection: Error inserting in table "+ e.getMessage());

			}

		}
		
	}
      
}