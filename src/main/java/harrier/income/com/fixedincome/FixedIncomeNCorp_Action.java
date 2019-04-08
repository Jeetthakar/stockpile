/*
 * Created on Jun 26, 2008
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


import harrier.income.com.entities.CFormula;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.chart.demo.servlet.AdjustDecimal;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

public class FixedIncomeNCorp_Action extends Action{
	static Logger Logging = Logger.getLogger(FixedIncomeNCorp_Action.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
			FixedIncomeCorporate corp = (FixedIncomeCorporate)form;  //bean object			
			Connect connect = ConnectInit.getConnect();  //connection
			Connection con=null;
			if(con == null){
				 con = connect.getdbConnection();
			}
			
			String button=corp.getNcorp_button();  //check for respective actions			
			if(button!=null)
			{
				if(button.equals("Exc"))    //on selection of exchange
				{
					corp.reset1();   //refresh stock details
					corp.reset_stock();
					corp.setS_affectedIndex(null);
					corp.reset_event();
					corp.setCommit_butt(null);
					return new ActionForward("/pages/fixedincome/FixedIncomeNCorporateAction.jsp?flag=New");
				}
				if(button.equals("Stock"))	//on selection of stock
				{
					String stock=request.getParameter("s_stock");
					if((stock.equals(""))|(stock.equals("Select Stock")))
						stock=null;
					if(stock!=null)			//get stock details
					{
						 String query =ConnectInit.queries.getProperty("fixed_income_stock_details_for_ca");
					       ResultSet rs = FixedIncomeListTypeClass1.getResult12(con,query,stock);
						    try{
								corp.setResult(rs);
					        }catch (Exception e) {
							Logging.error("ListTypeClass1: Error  in result set "+e.getMessage());
							}
					}
					else						
						corp.reset1();  //refresh stock details
					
					corp.reset_stock();
					corp.reset_event();
					corp.setCommit_butt(null);
					return new ActionForward("/pages/fixedincome/FixedIncomeNCorporateAction.jsp?flag=New");
				}
				if(button.equals("Radio"))  //on selection of radio button
				{
					String radval=corp.getInd_comp();					
					String butt=corp.getButton();
					if(butt!=null)
						if(butt.equals(""))
							butt=null;
					if(butt==null)
					{
						if(radval.equals("s"))	//if user wants to stop futher processing 
						{
							corp.setExc(null);
							corp.setS_stock(null);
							corp.setStid(null);
							corp.reset1();
							corp.reset_stock();
							corp.setAffect(null);
							corp.setNewFace(null);
							return new ActionForward("/pages/fixedincome/FixedIncomeNCorporateAction.jsp?flag=New&ref_flag=1&corp_name="+corp.getCorpid());
						}
						if(radval.equals("c")) //if user wants to continue further processing on any alert 
						{
							corp.setStid(request.getParameter("s_stock"));
							corp.setS_stock(request.getParameter("s_stock"));
							corp.setAmt(request.getParameter("amt"));
							corp.setShare(request.getParameter("share"));
							corp.setRatio1(request.getParameter("ratio1"));
							corp.setRatio2(request.getParameter("ratio2"));
							corp.setCommit_butt("1");
							return new ActionForward("/pages/fixedincome/FixedIncomeNCorporateAction.jsp?flag=New&check_flg=0&corp_name="+corp.getCorpid());
						}
					}
					else
					{
						if(butt.equals("Undo"))  //in case of undo any corporate action
						{
							if(radval.equals("s"))  //if user wants to stop further processing 
							{
								corp.setR_type("stock event");
								return new ActionForward("/pages/fixedincome/FixedIncomeCorporateDiary.jsp");
							}	
							if(radval.equals("c"))  //if user wants to continue further processing on any alert
							{
								corp.setStid(request.getParameter("s_stock"));
								corp.setS_stock(request.getParameter("s_stock"));
								corp.setAmt(request.getParameter("amt"));
								corp.setShare(request.getParameter("share"));
								corp.setRatio1(request.getParameter("ratio1"));
								corp.setRatio2(request.getParameter("ratio2"));
								corp.setCommit_butt("1");
								int flg_val=reCalApply(corp);
								return new ActionForward("/pages/fixedincome/FixedIncomeNCorporateAction.jsp?flag=Exist&check_flg="+flg_val);
							}
						}
					}
				}
				if(button.equals("NApply"))   //on click of apply button,if it is a new action
				{
					corp.setCommit_butt("1");
					corp.reset_stock();
					//corp.setAffect(null);
					String stock=request.getParameter("s_stock");
					String dt=UpdateCorp.accept_date();   //get the current date
					String apply=corp.getApply_date();
					int chk_dt=FixedIncomeComputeIndexForm.CompareDate(apply,dt);	//check for the current date and user's entered date
					
					if(stock!=null)			//get stock details
					{
					    try{
							 String query =ConnectInit.queries.getProperty("fixed_income_stock_details_for_ca");
						     ResultSet rs = FixedIncomeListTypeClass1.getResult12(con,query,stock);					
							 corp.setResult(rs);					        
					         if(chk_dt < 0)
					         {					        	
					        	String stk_qry=ConnectInit.queries.getProperty("fixed_income_get_undo_close_value");
					        	rs=FixedIncomeListTypeClass1.getResult_apply(con,stk_qry,stock,apply);
					        	if(rs!=null && rs.next()){
					        		String close=rs.getString("adjusted_price");
					        	if(close==null)
					        		close=rs.getString("stock_close_value");
					        	corp.setClose(close);
					         }
					         }
					    }catch (Exception e) {
							Logging.error("ListTypeClass1: Error  in result set "+e.getMessage());
						}
					}				
				
					//collect data in bean						
					corp.setStid(stock);	
					corp.setS_stock(stock);
					String corp_act=request.getParameter("corpid");
					corp.setAmt(request.getParameter("amt").trim());
					corp.setShare(request.getParameter("share").trim());
					corp.setRatio1(request.getParameter("ratio1").trim());
					corp.setRatio2(request.getParameter("ratio2").trim());
					
					if(chk_dt==0)				//if dates are same
					{
						try{							
							//get corporate action id.
							String qry=ConnectInit.queries.getProperty("fixed_income_get_corporate_list_stock");							
							ResultSet rs=FixedIncomeListTypeClass1.resultCorporate(con,qry);
							FixedIncomeListTypeClass1.check_corp_name(rs,corp_act,corp);
							if(rs!=null)
								rs.close();
							
							//check whether this combination is present in dairy or not.
							String query2=ConnectInit.queries.getProperty("fixed_income_select_corporate_action_diary");
							ResultSet rs2=FixedIncomeListTypeClass1.getresp_cad(con,query2,corp.getCorpid(),stock,corp.getApply_date());
							boolean chk_exist=rs2.next();
							
							corp.setCorpid(corp_act);		
							corp.setChk_but(null);
							
							if(chk_exist==false)  //if not present	
							{	
								String corp_nm=corp.getCorpid();					
								int flg_val=CalApply(con,connect,corp);   	//calculate the new values
								corp.setNcorp_button(null);
								return new ActionForward("/pages/fixedincome/FixedIncomeNCorporateAction.jsp?flag=New&check_flg="+flg_val);
							}
							else   //if present
							{														
								return new ActionForward("/pages/fixedincome/FixedIncomeCorporateDetails.jsp");
							}						
						}catch(Exception e){Logging.error("error="+e.getMessage());}
					}
					else      //if the entered date is less than current date then there is historic calculation
					{
						//corp.setNcorp_button(null);
						corp.setChk_but(null);
						String corp_nm=corp.getCorpid();						
						if(corp_nm.equals("cashdividend")) //if cashdividend then check in system configuration table
						{
							try{
								String query=ConnectInit.queries.getProperty("select_system_config");
								ResultSet rs1=FixedIncomeListTypeClass1.resultCorporate(con,query);
								rs1.next();
								String type=rs1.getString("adjust_stock_price").toLowerCase();
								if(type.equals("n"))  //no changes are made in stock parameter
								{
//									get corporate action id.
									String qry=ConnectInit.queries.getProperty("fixed_income_get_corporate_list_stock");							
									ResultSet rs=FixedIncomeListTypeClass1.resultCorporate(con,qry);
									FixedIncomeListTypeClass1.check_corp_name(rs,corp_act,corp);
									if(rs!=null)
										rs.close();
									
									//check whether this combination is present in dairy or not.
									String query2=ConnectInit.queries.getProperty("fixed_income_select_corporate_action_diary");
									ResultSet rs2=FixedIncomeListTypeClass1.getresp_cad(con,query2,corp.getCorpid(),stock,corp.getApply_date());
									boolean chk_exist=rs2.next();									
									corp.setCorpid(corp_act);		
									corp.setChk_but(null);
									if(chk_exist==false)
									{
									 	corp.setNewTIS(corp.getTis());					    
									    corp.setAdjust("0");
									    
									    qry=ConnectInit.queries.getProperty("fixed_income_get_resp_date_closevalue");					    
										rs=FixedIncomeListTypeClass1.getResult_apply(con,qry,corp.getStid(),corp.getApply_date());
										rs.next();
										corp.setNewLTP(rs.getString("adjusted_price"));
										if(rs!=null)
											rs.close();
										corp.setNcorp_button(null);
										return new ActionForward("/pages/fixedincome/FixedIncomeNCorporateAction.jsp?flag=New&check_flg=0");
									}
									if(chk_exist==true)
									{
										return new ActionForward("/pages/fixedincome/FixedIncomeCorporateDetails.jsp");
									}
								 }
								else //calculate the adjusted price
								{
									corp.setNcorp_button(null);									
									corp.setR_type("fixedincome event");
									return new ActionForward("/pages/fixedincome/FixedIncomeHistoricAction.jsp?flag=New");			
								}
							}catch(Exception e){
								Logging.error("Error="+e.getMessage());
								}
						}
						else
						{
							corp.setNcorp_button(null);							
							corp.setR_type("fixedincome event");
							Hashtable hash=corp.getHash1();
							hash.clear();
							corp.setHash1(hash);
							return new ActionForward("/pages/fixedincome/FixedIncomeHistoricAction.jsp?flag=New");
						}
					}
				}
				if(button.equals("Apply"))   //on click of apply button, if the data has come from diary
				{
					corp.setCommit_butt("1");
					String butt=corp.getButton();
					if(butt!=null)
						if(butt.equals(""))
							butt=null;						
					String stock=request.getParameter("s_stock");					
					String corp_act=request.getParameter("corpid");
					try{
						//collect the data in bean
							corp.setStid(request.getParameter("s_stock"));
							corp.setS_stock(request.getParameter("s_stock"));
							corp.setAmt(request.getParameter("amt"));
							corp.setShare(request.getParameter("share"));
							corp.setRatio1(request.getParameter("ratio1"));
							corp.setRatio2(request.getParameter("ratio2"));
							String corp_nm=corp.getCorpid();	
							
							if(butt==null)     //if there is no undo  
							{
								String dt=UpdateCorp.accept_date();   //get the current date
								String apply=corp.getApply_date();
								int chk_dt=FixedIncomeComputeIndexForm.CompareDate(apply,dt);	//check for the current date and user's entered date
								int flg_val=0;
								if(chk_dt==0)  //if dates are equal
								{
									flg_val=CalApply(con,connect,corp);  //calculate the new values
									corp.setNcorp_button(null);
									corp.setCommit_butt("1");
									return new ActionForward("/pages/fixedincome/FixedIncomeNCorporateAction.jsp?flag=Exist&check_flg="+flg_val);
								}
								else //if not then historic calculation 
								{
									//forward for historic calculation
									corp.setNcorp_button(null);									
									corp.setR_type("fixedincome event");
									String check=request.getParameter("flag");									
									return new ActionForward("/pages/fixedincome/FixedIncomeHistoricAction.jsp?flag="+check);
								}
								
							}
							else             //in case of undo
							{								
								corp.setCommit_butt("1");
								//get corporate action id.
								String qry=ConnectInit.queries.getProperty("fixed_income_get_corporate_list_stock");
								int cid=0;								
								ResultSet rs=FixedIncomeListTypeClass1.resultCorporate(con,qry);
								cid=FixedIncomeListTypeClass1.check_corp_name1(rs,corp_nm);
								if(rs!=null)
									rs.close();
								
								//check whether this combination is present in diary with status='n' 
								String apply=corp.getApplied_date();//UpdateCorp.accept_date();
								corp.setNcorp_button(null);
								String query=null;
								query=ConnectInit.queries.getProperty("fixed_income_check_corp_existance");
								rs=FixedIncomeListTypeClass1.getresp_cad(con,query,Integer.toString(cid),stock,apply);
								
								if(rs.next())//if present
								{
									//then alert
									return new ActionForward("/pages/fixedincome/FixedIncomeNCorporateAction.jsp?flag=Exist&check_flg=4");	
								}
								else    //if not present
								{									
									int flg_val=reCalApply(corp);   //recalculate the old values
									return new ActionForward("/pages/fixedincome/FixedIncomeNCorporateAction.jsp?flag=Exist&check_flg="+flg_val);
								}
							}						
					}catch(Exception e){Logging.error(" error="+e.getMessage());}
				}
				if(button.equals("Affect"))  //to get the affected index details
				{					
					corp.reset_affect1();					
					String affect=request.getParameter("s_affectedIndex");	  //get the affected index
					if((affect.equals(""))|(affect.equals("Affected Index")))
						affect=null;
					
					String butt=corp.getButton();
					int chk_dt=0;
					if(butt!=null)
						if(butt.equals(""))
							butt=null;
						if(butt==null)
						{
					String dt=UpdateCorp.accept_date();   //get the current date
					String apply=corp.getApply_date();
					chk_dt=FixedIncomeComputeIndexForm.CompareDate(apply,dt);	//check for the current date and user's entered date
						}
						else
							chk_dt=0;
						
					if(affect!=null)
					{						
						//get currency exchange value
						String apply1=corp.getApply_date();
						Logging.debug("apply date from line no 361 of ncorpaction.java  "+apply1);
						get_currency(con,connect,corp,affect,corp.getS_stock());
						//if dates are equal
						if((chk_dt==0) | butt!=null)
						{							
							if(butt==null)   //if there is no undo
							{
								//get the affected index details
								String query =ConnectInit.queries.getProperty("get_latest_index_value_index_wise");					
						        ResultSet rs1 = FixedIncomeListTypeClass1.getResult12(con,query,affect);			            
								corp.setResult1(rs1);
							}
							else              //if there is undo
							{
								//get the affected index detail for the particular date
								String date=corp.getApplied_date();
								String query1=ConnectInit.queries.getProperty("get_undo_index_close");
								ResultSet rs1=FixedIncomeListTypeClass1.getResult_apply(con,query1,affect,date);
								corp.setResult1(rs1);
							}
							
							String corp_name=corp.getCorpid().trim();				
							 
							if(corp_name.equals("cashdividend"))
							{
	//							check from system configuration table whether to adjust price or not for cash dividend
								String query=ConnectInit.queries.getProperty("select_system_config");
								try{
								 ResultSet rs=FixedIncomeListTypeClass1.resultCorporate(con,query);
								 rs.next();
								 String type=rs.getString("adjust_stock_price").toLowerCase();
								 
								 if(type.equals("n"))   //if no
								 {
								 	corp.setNewtmcv(corp.getTmcv());								 	
			    					corp.setNewdivisor(corp.getDivisor());	
			    					tmcv_div(corp);
								 }
								 if(type.equals("y"))  //if yes
								 {
								 	if(butt==null)
								 		ActionCorp.toGetaffect(corp);
								 	else
								 		ActionCorp.recaltoGetaffect(corp);
								 }
								 if(rs!=null)
								 	rs.close();
								 }catch(Exception e){Logging.error("error=="+e.getMessage());}
							}
						/*  do later
							else
							{
								//in case of stock dividend/bonus there is no change in tmcv & divisor 
								if(!(corp_name.equals("stockdividend/bonus")))
								{
									if(butt==null)
										ActionCorp.toGetaffect(corp);
									else
									{									
										ActionCorp.recaltoGetaffect(corp);
									}
								}
								if(corp_name.equals("stockdividend/bonus"))
								{
									corp.setNewtmcv(corp.getTmcv());
			    					corp.setNewdivisor(corp.getDivisor());
			    					tmcv_div(corp);
								}
							    }*/
						}//if dates are equal
						else
						{							
								ActionCorp.hist_affect_cal(corp,affect);																		
						}
						corp.setAmt(request.getParameter("amt"));
						corp.setRatio1(request.getParameter("ratio1"));
						corp.setRatio2(request.getParameter("ratio2"));
						corp.setShare(request.getParameter("share"));
						corp.setNcorp_button(null);
						
					}
					corp.setNcorp_button(null);
					tmcv_div(corp);
			        return new ActionForward("/pages/fixedincome/FixedIncomeNCorporateAction.jsp?check_flg=0");			                
				}
				synchronized(corp.StopRepetition)   //for duplicate entry
				{
					corp.StopRepetition=corp.StopRepetition+"";	
				if((button.equals("Commit")) && (!(corp.StopRepetition.trim().equals("end"))))  //to commit the action
				{	
			//		CFormula cf=new CFormula();
					CFormula cf = ConnectInit.getCFormula();
					corp.setCommit_butt(null);
					corp.setSucc_butt("1");
					corp.StopRepetition="end";
					String corpval=corp.getCorpid();										
					int flag=0;					
					//for data persistent
					String ori_amt=request.getParameter("amt");					
					String ori_r1=request.getParameter("ratio1");
					String ori_r2=request.getParameter("ratio2");					
					String ori_shr=request.getParameter("share");					
					String check=request.getParameter("flag");
					String butt=corp.getButton();
					int chk_dt=0;
					if(butt==null)
					{
						String dt=UpdateCorp.accept_date();   //get the current date
						String apply=corp.getApply_date();
						chk_dt=FixedIncomeComputeIndexForm.CompareDate(apply,dt);	//check for the current date and user's entered date
					}
					else
						chk_dt=0;
					
					if(chk_dt==0)
					{
					//check whether the entry has come from diary or not
						if(check!=null)
						{
							if(check.equals("Exist"))  //from diary
							{						
								//update corporate action diary
								String query=ConnectInit.queries.getProperty("update_cad");
								String qry=ConnectInit.queries.getProperty("select_rep_cad");
								UpdateCorp.updatecad(con,query,qry,corp);
								corp.setAmt(ori_amt);					
								corp.setShare(ori_shr);
								corp.setRatio1(ori_r1);
								corp.setRatio2(ori_r2);							
							}
							if(check.equals("New"))  //if its new i.e's from action page directly
							{
								//check whether this entry exist in diary or not.
								String chk=corp.getChk_but();
								if(chk==null)  //if it's new
								{
									//insert this data in diary
									corp.setCad_id(chk);
									String date=corp.getApply_date();//UpdateCorp.accept_date();
									corp.setAnnounce_date(date);
									corp.setRecord_date(date);
									corp.setApplied_date(date);
									corp.setStatus("y");								
									String corp_act=corp.getCorpid();
									String query =ConnectInit.queries.getProperty("fixed_income_get_corporate_list_stock");
									ResultSet rs = FixedIncomeListTypeClass1.resultCorporate(con,query);    
									FixedIncomeListTypeClass1.check_corp_name(rs,corp_act,corp);
									query=ConnectInit.queries.getProperty("fixed_income_insert_into_cad_values_with_time");
									String nextval=ConnectInit.queries.getProperty("get_sequence_cad_id");
									UpdateCorp.insert_into_cad_with_time(con,query,nextval,corp,null);
									corp.setCorpid(corp_act);
									Hashtable hash1=corp.getHash1();
									hash1.clear();
									corp.setHash1(hash1);
									hash1=corp.getHash1();
									hash1.put(new String(corp.getCad_id()),new String(corp.getCad_id()));
									corp.setHash1(hash1);
								}
								else  //if it's exist in diary 
								{
									//update data in diary
									String query=ConnectInit.queries.getProperty("update_cad");								
									Hashtable hash1=corp.getHash1();
									hash1.clear();
									corp.setHash1(hash1);
									hash1=corp.getHash1();
									hash1.put(new String(chk),new String(chk));
									corp.setHash1(hash1);
									UpdateCorp.updatecad(con,query,null,corp);		
								}
							}
						}
						
						corp.setAmt(ori_amt);					
						corp.setShare(ori_shr);
						corp.setRatio1(ori_r1);
						corp.setRatio2(ori_r2); 
						
						String corp_name=corp.getCorpid();					
						String query=null,qry=null;
						
	//					check from system configuration table whether to adjust price or not for cash dividend					
						if(corp_name.equals("cashdividend"))
						{
							query=ConnectInit.queries.getProperty("select_system_config");
							try{
							 ResultSet rs=FixedIncomeListTypeClass1.resultCorporate(con,query);
							 if(rs!=null &&  rs.next()){
							 	String type=rs.getString("adjust_stock_price");
							 	if(type.equals("y"))
							 		flag=1;						 
							 	if(type.equals("n"))
							 		flag=0;
							 }
							}catch(Exception e){Logging.error("error=="+e.getMessage());}
						}
						else
						{
							flag=1;
						}
						
						String face=corp.getNewFace();
						if(face==null)
							face=corp.getFace();
						
						if(flag==1)
						{								
						//update tis in stock master
							query=ConnectInit.queries.getProperty("fixed_income_update_tis_stockmaster");
							UpdateCorp.updatestkmaster(con,query,corp,face);
								
							if(butt==null)  //if there is no undo
							{							
								//update stock price daily
								double mcv=cf.calMarketCap(Double.parseDouble(corp.getNewLTP()),Long.parseLong(corp.getMark_lot()),1,Long.parseLong(corp.getNewTIS()),Double.parseDouble(corp.getIwfstk()));
								corp.setNewmcv(mcv);
							    query =ConnectInit.queries.getProperty("fixed_income_update_tis_after_ca");
							 	UpdateCorp.updateAction(con,query,corp);
							 	
							 	//update index value daily
							 	/*  not required for fixed income 
							 	if(!(corp_name.equals("stockdividend/bonus")))
							 	{
								 	query =ConnectInit.queries.getProperty("update_adjusted_divisor_after_ca");
									qry=connect.queries.getProperty("get_latest_index_value_index_wise");
									UpdateCorp.updateDivisor(con,qry,query,corp.getHash(),corp);
							 	}*/
							}
							else     //if there is undo
							{								
								//get stock_price_daily id
								String undo_query=ConnectInit.queries.getProperty("fixed_income_get_undo_close_value");
								
								//update stock price daily for the particular date
								double mcv=cf.calMarketCap(Double.parseDouble(corp.getNewLTP()),Long.parseLong(corp.getMark_lot()),1,Long.parseLong(corp.getNewTIS()),Double.parseDouble(corp.getIwfstk()));
								corp.setNewmcv(mcv);
								query =ConnectInit.queries.getProperty("fixed_income_update_undo_stock_price");
							 	UpdateCorp.update_undo_stkprice(con,undo_query,query,corp);								
							 	
	//						 	update index value daily for the particular date
							 	/*
							 	if(!(corp_name.equals("stockdividend/bonus")))
							 	{
								 	qry=ConnectInit.queries.getProperty("get_undo_index_close");					
								 	query =connect.queries.getProperty("update_undo_ind_close");						 	
								 	UpdateCorp.undoupdateDivisor(con,qry,query,corp.getHash(),corp);
							 	}*/
							}
							
							//update index composition 
							query=ConnectInit.queries.getProperty("fixed_income_select_index_composition");
							ResultSet rs1=FixedIncomeListTypeClass1.getAffected(con,query,corp.getStid());					
							qry=ConnectInit.queries.getProperty("fixed_income_update_index");
							UpdateCorp.update_index(con,qry,rs1,corp);
							
						}	
						/* do later to maintain history
						if(butt==null)  //if there is no undo
						{			
							//insert into stock mastor history
							corp.setCorpid(request.getParameter("corpid"));
							query=connect.queries.getProperty("detail_stock_master");					 
							qry=connect.queries.getProperty("insert_stock_master");
							UpdateCorp.insertstkmaster(con,query,qry,corp,face);
							corp.setNcorp_button(null);	
							corp.setSucc_butt("1");
							return new ActionForward("/pages/NCorporateAction.jsp?flag="+check);
						}
						else   //if there is undo
						{
							//delete from stock master history
							 Hashtable hash1=corp.getHash1();
						        for(Enumeration enum1 =hash1.keys();enum1.hasMoreElements();)
								{				
									String id2 =(String)enum1.nextElement();
									corp.setEvent_id(id2);
									query=connect.queries.getProperty("delete_stkmas_history");
									UpdateCorp.delete_event(con,query,corp);								
								}
						        corp.setNcorp_button(null);
						        corp.setSucc_butt("1");
						        return new ActionForward("/pages/NCorporateAction.jsp?flag="+check);
						}	*/
					}//if dates are equal
					else
					{						
						String chk=corp.getChk_but();					
						//data persistent						
						String ori_corpid=corp.getCorpid();
						ori_amt=corp.getAmt();
						ori_shr=corp.getShare();
						ori_r1=corp.getRatio1();
						ori_r2=corp.getRatio2();
						String ori_adjust=corp.getAdjust();
						String onew_tis=corp.getNewTIS();
						String onew_ltp=corp.getNewLTP();
						String onew_face=corp.getNewFace();
						String ori_ml=corp.getMark_lot();
						String o_div=corp.getDivisor();
						String n_div=corp.getNewdivisor();
						String o_tmcv=corp.getTmcv();
						String n_tmcv=corp.getNewtmcv();
						String ori_apply=corp.getApply_date();
						
						UpdateCorp.update_hist_value(corp);  //commit the entered CA
						
						Hashtable aff=corp.getHash_affind();
						Hashtable copy=corp.getCopy_hash();
						copy.clear();
						corp.setCopy_hash(copy);
						copy=corp.getCopy_hash();
						
						if(!(aff.isEmpty()))
						{
							for(Enumeration enum1 =aff.keys();enum1.hasMoreElements();)
							{				
								String id2 =(String)enum1.nextElement();
								Object obj=aff.get(id2);
								String stid=obj.toString();
								copy.put(new String(id2),new String(stid));
							}
							corp.setCopy_hash(copy);
						}						
						//reapply all undo action
						UpdateCorp.reapply_action(corp);  //commit the temporary undo actions
						
						//get original data back
						corp.setCorpid(ori_corpid);
						corp.setAmt(ori_amt);
						corp.setShare(ori_shr);
						corp.setRatio1(ori_r1);
						corp.setRatio2(ori_r2);
						corp.setAdjust(ori_adjust);
						corp.setNewTIS(onew_tis);
						corp.setNewLTP(onew_ltp);
						corp.setNewFace(onew_face);
						corp.setMark_lot(ori_ml);
						corp.setDivisor(o_div);
						corp.setNewdivisor(n_div);
						corp.setTmcv(o_tmcv);
						corp.setNewtmcv(n_tmcv);
						corp.setApply_date(ori_apply);
						copy=corp.getCopy_hash();
						aff=corp.getHash_affind();
						aff.clear();
						corp.setHash_affind(aff);
						aff=corp.getHash_affind();
						if(!(copy.isEmpty()))
						{
							for(Enumeration enum1 =copy.keys();enum1.hasMoreElements();)
							{				
								String id2 =(String)enum1.nextElement();
								Object obj=copy.get(id2);
								String stid=obj.toString();
								aff.put(new String(id2),new String(stid));
							}
							corp.setHash_affind(aff);
						}
						
						corp.setNcorp_button(null);			
						corp.setSucc_butt("1");
						return new ActionForward("/pages/fixedincome/FixedIncomeNCorporateAction.jsp?flag="+check);						
					}
				}				
				else
					corp.StopRepetition="a";
				}//if synchronized
			}
			
			//if the new entry exist in diary
			String detail_button=request.getParameter("cdetail_button");			
			if(detail_button!=null)
			{
				if(detail_button.equals("Back"))  //back to action page
				{				
					return new ActionForward("/pages/fixedincome/FixedIncomeNCorporateAction.jsp?flag=New&corpid="+corp.getCorpid());
				}
				if(detail_button.equals("Ok"))  
				{	
					//collect data in bean
					corp.setStid(request.getParameter("s_stock"));
					corp.setS_stock(request.getParameter("s_stock"));
					corp.setCorpid(request.getParameter("corpid"));
					corp.setShare(request.getParameter("share"));
					corp.setRatio1(request.getParameter("ratio1"));
					corp.setRatio2(request.getParameter("ratio2"));
					corp.setAmt(request.getParameter("amt"));
					corp.setCommit_butt("1");
					//check whether the entered value is match with selected data 
					boolean chk_val=check_val(corp);					
					if(chk_val==false) //if yes
					{
						String dt=UpdateCorp.accept_date();   //get the current date
						String apply=corp.getApply_date();
						int chk_dt=FixedIncomeComputeIndexForm.CompareDate(apply,dt);	//check for the current date and user's entered date
						
						if(chk_dt==0)//if dates are equal
						{
							int flg_val=CalApply(con,connect,corp);  //calculate the new value
							return new ActionForward("/pages/fixedincome/FixedIncomeNCorporateAction.jsp?flag=New&corp_name="+corp.getCorpid()+"&check_flg="+flg_val);
						}
						else//if not
						{
							String corp_nm=corp.getCorpid();
							String ori_tis=corp.getTis();  //data persistent
							String ori_close=corp.getClose();
							String ori_amt=corp.getAmt();
							String ori_shr=corp.getShare();
							String ori_r1=corp.getRatio1();
							String ori_r2=corp.getRatio2();
							String ori_corpid=corp.getCorpid();			
							if(corp_nm.equals("cashdividend")) //if cashdividend then check in system configuration table
							{
								try{
									String query=ConnectInit.queries.getProperty("select_system_config");
									ResultSet rs1=FixedIncomeListTypeClass1.resultCorporate(con,query);
									rs1.next();
									String type=rs1.getString("adjust_stock_price").toLowerCase();
									if(type.equals("n"))  //no changes are made in stock parameter
									{
										corp.setNewTIS(corp.getTis());					    
									    corp.setAdjust("0");
									    
									    String  qry=ConnectInit.queries.getProperty("fixed_income_get_resp_date_closevalue");					    
										ResultSet rs=FixedIncomeListTypeClass1.getResult_apply(con,qry,corp.getStid(),corp.getApply_date());
										rs.next();
										corp.setNewLTP(rs.getString("adjusted_price"));
										if(rs!=null)
											rs.close();
										return new ActionForward("/pages/fixedincome/FixedIncomeNCorporateAction.jsp?flag=New&check_flg=0");
									}
									else
									{
										corp.setR_type("fixedincome event");
										ActionCorp.Historic_cal(corp); //calculate new value
									}
								}catch(Exception e){
									Logging.error("Error="+e.getMessage());
									}
							}
							else
							{																				
								corp.setR_type("fixedincome event");
								ActionCorp.Historic_cal(corp); //calculate new value
							}
							corp.reset_affect1();
							corp.setCorpid(ori_corpid);
							corp.setTis(ori_tis); //set old data
							corp.setClose(ori_close);
							corp.setAmt(ori_amt);
							corp.setShare(ori_shr);
							corp.setRatio1(ori_r1);
							corp.setRatio2(ori_r2);
							return new ActionForward("/pages/fixedincome/FixedIncomeNCorporateAction.jsp?check_flg=0&corp_name="+corp.getCorpid());
						}						
					}
					else   // if no
					{
						//then alert
						corp.setNature(null);
						return new ActionForward("/pages/fixedincome/FixedIncomeCorporateDetails.jsp?flag=Exist");
					}
				}
				if(detail_button.equals("Radio"))   //to select either new or original value in case of mismatch
				{					
					corp.setStid(request.getParameter("s_stock"));
					corp.setS_stock(request.getParameter("s_stock"));
					corp.setCorpid(request.getParameter("corpid"));
					corp.setCommit_butt("1");				
					String rad_val=corp.getNature();				
					int flg_val=0;
					if(rad_val!=null)
					{
						if(rad_val.equals("o"))   //for original values
						{							
							try{
							//get the values from database and set in bean 								
							String cad=corp.getChk_but();
							String query=ConnectInit.queries.getProperty("select_rep_cad");
							ResultSet rs=FixedIncomeListTypeClass1.getResult1(con,query,corp.getChk_but());
							rs.next();
							corp.setAmt(rs.getString("amount"));
							corp.setPercent(rs.getString("percentage"));							
							corp.setShare(rs.getString("values"));							
							corp.setRatio1(rs.getString("ratio_for_shares"));
							corp.setRatio2(rs.getString("ratio_shares_offered"));
							}catch(Exception e){Logging.error("error="+e.getMessage());}
						}
						if(rad_val.equals("n"))  //for new values
						{							
							corp.setShare(request.getParameter("share"));
							corp.setRatio1(request.getParameter("ratio1"));
							corp.setRatio2(request.getParameter("ratio2"));
							corp.setAmt(request.getParameter("amt"));	
						}
						String dt=UpdateCorp.accept_date();   //get the current date
						String apply=corp.getApply_date();
						int chk_dt=FixedIncomeComputeIndexForm.CompareDate(apply,dt);		//check for the current date and user's entered date
						
						if(chk_dt==0)//if dates are equal
							flg_val=CalApply(con,connect,corp);  //calculate the new value
						else
						{
							String ori_tis=corp.getTis();  //data persistent
							String ori_close=corp.getClose();
							String ori_amt=corp.getAmt();
							String ori_shr=corp.getShare();
							String ori_r1=corp.getRatio1();
							String ori_r2=corp.getRatio2();
							String ori_corpid=corp.getCorpid();
							String corp_nm=corp.getCorpid();
							if(corp_nm.equals("cashdividend")) //if cashdividend then check in system configuration table
							{
								try{
									String query=ConnectInit.queries.getProperty("select_system_config");
									ResultSet rs1=FixedIncomeListTypeClass1.resultCorporate(con,query);
									rs1.next();
									String type=rs1.getString("adjust_stock_price").toLowerCase();
									if(type.equals("n"))  //no changes are made in stock parameter
									{
										corp.setNewTIS(corp.getTis());					    
									    corp.setAdjust("0");
									    
									    String  qry=ConnectInit.queries.getProperty("fixed_income_get_resp_date_closevalue");					    
										ResultSet rs=FixedIncomeListTypeClass1.getResult_apply(con,qry,corp.getStid(),corp.getApply_date());
										if(rs!=null && rs.next()){
											corp.setNewLTP(rs.getString("adjusted_price"));
											rs.close();
										}
										return new ActionForward("/pages/fixedincome/FixedIncomeNCorporateAction.jsp?flag=New&check_flg=0");
									}
									else
									{
										corp.setR_type("fixedincome event");
										ActionCorp.Historic_cal(corp); //calculate new value
									}
								}catch(Exception e){
									Logging.error("Error="+e.getMessage());
									}
							}
							else
							{																			
								corp.setR_type("fixedincome event");
								ActionCorp.Historic_cal(corp); //calculate new value
							}
							corp.reset_affect1();
							corp.setCorpid(ori_corpid);
							corp.setTis(ori_tis); //set old data
							corp.setClose(ori_close);
							corp.setAmt(ori_amt);
							corp.setShare(ori_shr);
							corp.setRatio1(ori_r1);
							corp.setRatio2(ori_r2);
						}
					}
					return new ActionForward("/pages/fixedincome/FixedIncomeNCorporateAction.jsp?flag=New&cval=c&corp_name="+corp.getCorpid()+"&check_flg="+flg_val);
				}
			}
			//for historic calculation
			String hist_butt=request.getParameter("hist_but");
			
			if(hist_butt!=null)
			{
				if(hist_butt.equals("Back"))//cancel the action
				{				
					String check=request.getParameter("flag");
					if(check.equals("New"))
						return new ActionForward("/pages/fixedincome/FixedIncomeNCorporateAction.jsp?flag=New&corp_name="+corp.getCorpid());
					if(check.equals("Exist"))
						return new ActionForward("/pages/fixedincome/FixedIncomeCorporateDiary.jsp");
				}
				if((hist_butt.equals("Continue")))//further processing
				{
					String flag=request.getParameter("flag");  //check whether new or existing entry					
					if(flag.equals("New"))
					{										
						try{
						String corp_act=corp.getCorpid();
						String qry=ConnectInit.queries.getProperty("fixed_income_get_corporate_list_stock");							
						ResultSet rs=FixedIncomeListTypeClass1.resultCorporate(con,qry);
						FixedIncomeListTypeClass1.check_corp_name(rs,corp_act,corp);
						if(rs!=null)
							rs.close();
						
						//check whether this combination is present in dairy or not.
						String query2=ConnectInit.queries.getProperty("fixed_income_select_corporate_action_diary");
						ResultSet rs2=FixedIncomeListTypeClass1.getresp_cad(con,query2,corp.getCorpid(),corp.getS_stock(),corp.getApply_date());
						boolean chk_exist=rs2.next();						
						corp.setCorpid(corp_act);
						if(chk_exist==true)//if present
						{
							return new ActionForward("/pages/fixedincome/FixedIncomeCorporateDetails.jsp");
						}
						else //if not
						{							
							String ori_tis=corp.getTis();  //data persistent
							String ori_close=corp.getClose();
							String ori_amt=corp.getAmt();
							String ori_shr=corp.getShare();
							String ori_r1=corp.getRatio1();
							String ori_r2=corp.getRatio2();
							String ori_corpid=corp.getCorpid();					
							corp.setR_type("fixedincome event");
							ActionCorp.Historic_cal(corp); //calculate new value
							corp.reset_affect1();
							corp.setCorpid(ori_corpid);
							corp.setTis(ori_tis); //set old data
							corp.setClose(ori_close);
							corp.setAmt(ori_amt);
							corp.setShare(ori_shr);
							corp.setRatio1(ori_r1);
							corp.setRatio2(ori_r2);
							corp.setCommit_butt("1");
							return new ActionForward("/pages/fixedincome/FixedIncomeNCorporateAction.jsp?flag=New&check_flg=0&corp_name="+corp.getCorpid());
						}
						}catch(Exception e){
							Logging.error("Error="+e.getMessage());
							}
					}//if New entry
					if(flag.equals("Exist"))//if already exist
					{
						String ori_tis=corp.getTis();  //data persistent
						String ori_close=corp.getClose();
						String ori_amt=corp.getAmt();
						String ori_shr=corp.getShare();
						String ori_r1=corp.getRatio1();
						String ori_r2=corp.getRatio2();
						String ori_corpid=corp.getCorpid();					
						corp.setR_type("fixedincome event");
						ActionCorp.Historic_cal(corp); //calculate new value
						corp.reset_affect1();
						corp.setCorpid(ori_corpid);
						corp.setTis(ori_tis); //set old data
						corp.setClose(ori_close);
						corp.setAmt(ori_amt);
						corp.setShare(ori_shr);
						corp.setRatio1(ori_r1);
						corp.setRatio2(ori_r2);
						corp.setCommit_butt("1");
						return new ActionForward("/pages/fixedincome/FixedIncomeNCorporateAction.jsp?check_flg=0&corp_name="+corp.getCorpid());
					}				
				}
			}
			return mapping.getInputForward();
		}	

	//This method is used to check whether the entered value matches with the selected value
		public static boolean check_val(FixedIncomeCorporate corp)
		{
			boolean chk_flg=false;
			Connect connect = ConnectInit.getConnect();
			Connection con=null;
//			Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
			try{
			if(con == null){
				 con = connect.getdbConnection();
			}
			try{
				String query=ConnectInit.queries.getProperty("select_rep_cad");
				ResultSet rs=FixedIncomeListTypeClass1.getResult1(con,query,corp.getChk_but());
				rs.next();
				//check amount
				String amount=rs.getString("amount");
				String amt=corp.getAmt();
				if(amt!=null & !(amt.equals("")))
				{
					if(amount.equals(amt))
						chk_flg=false;
					else				
						chk_flg=true;
				}
				
				if(chk_flg==false)
				{
					//check shares
					String shr=rs.getString("values");
					String share=corp.getShare();
					if(share!=null & !(share.equals("")))
					{
						if(share.equals(shr))
							chk_flg=false;
						else				
							chk_flg=true;
					}
					//check ratio for shares
					String r1=rs.getString("ratio_for_shares");
					String ratio1=corp.getRatio1();
					if(chk_flg==false & ratio1!=null & !(ratio1.equals("")))
					{
						if(r1.equals(ratio1))
								chk_flg=false;
						else				
							chk_flg=true;						
					}
					//check ratio shares offered
					String r2=rs.getString("ratio_shares_offered");
					String ratio2=corp.getRatio2();
					if(chk_flg==false & ratio2!=null & !(ratio2.equals("")))
					{
						if(r2.equals(ratio2))
							chk_flg=false;
					else				
						chk_flg=true;
					}
				}
			}catch(Exception e){Logging.error("error="+e.getMessage());}
			} catch (Exception e) {
				Logging.debug("Error :" + e);
			} finally {
				try {
					if (con != null)
						con.close();
				} catch (Exception ee) {
					Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
				}
			}
			return chk_flg;
		}
		
//This method is used to calculate new value 	
		public static int CalApply(Connection con,Connect connect,FixedIncomeCorporate corp)
		{			
	//		CFormula cf = new CFormula();
			CFormula cf = ConnectInit.getCFormula();
			Logging.debug("close in calapply="+corp.getClose());
			
			String corp_nm=corp.getCorpid();
			int flg_val=0;			
			try{
				
//				check from system configuration table whether to adjust price or not for cash dividend				
				String query=ConnectInit.queries.getProperty("select_system_config");
				ResultSet rs1=FixedIncomeListTypeClass1.resultCorporate(con,query);
				boolean isdata=false;
				if(rs1!=null)
					isdata= rs1.next();
				
				if(corp_nm.equals("cashdividend"))
				{
					 String type=null;
					 if(isdata)
					 	type=rs1.getString("adjust_stock_price").toLowerCase();
					 if(type.equals("n"))
					 {
					 	corp.setNewTIS(corp.getTis());					    
					    corp.setAdjust("0");
					    query=ConnectInit.queries.getProperty("fixed_income_select_stock_price_detail");					    
						ResultSet rs=FixedIncomeListTypeClass1.getResult12(con,query,corp.getStid());
						if(rs!=null && rs.next()){
							corp.setNewLTP(rs.getString("adjusted_price"));
							rs.close();
						}
					 }
					 else
					 	if(type.equals("y"))
					 	{					 		
					 		ActionCorp.actionOnApply(corp);
					 	}					
				}
				else
				{					
					ActionCorp.actionOnApply(corp);
				}
				
	//checking for errors
				
//check whether newtis or newltp is negative				
				String tis=corp.getNewTIS();
				boolean chk_tis=tis.startsWith("-");
				if(chk_tis==true)
				{
					flg_val=1;
				}
				else
				{
					String ltp=corp.getNewLTP();
					boolean chk_ltp=ltp.startsWith("-");
					if(chk_ltp==true)
						flg_val=1;
					else
						flg_val=0;
				}
				
//check whether newface value is less than 1 or not		
			/*	do later
				if((corp_nm.equals("split"))|(corp_nm.equals("reversesplit")))
				{
					if(flg_val==0)
					{
						String face=corp.getNewFace();
						float fval=Float.parseFloat(face);
						if(fval < 1.0)
							flg_val=2;
					}
				}*/
				
//	check whether ths change in tis is less than 5% or not 		do later	
			/*	if((corp_nm.equals("shareissuance"))|(corp_nm.equals("adrissue"))|(corp_nm.equals("stockdividend/bonus")))
				{
					if(flg_val==0)
					{
						double oldtis=Double.parseDouble(corp.getTis());
						double newtis=Double.parseDouble(corp.getNewTIS());
						double ttis=newtis-oldtis;						
						double per=((ttis/oldtis)*100);						
						String qry=connect.queries.getProperty("select_system_config");
						ResultSet rs=ListTypeClass1.resultCorporate(con,qry);
						rs.next();
						double chk_val=rs.getDouble("percentage_change_in_share");						
						if(per <= chk_val)
							flg_val=3;						
					}
				}*/
			}catch(Exception e){
				Logging.error("error="+e.getMessage());
			}		
			return flg_val;
		}
		
//This method is used to recalculate old value(for undo)		
		public static int reCalApply(FixedIncomeCorporate corp)
		{
			int flg_val=0;
		    try{
		        
		        String corp_nm=corp.getCorpid();
		        if(corp_nm!=null)
		        {
		            if(!(corp_nm.equals("cashdividend")))
		            {
		                ActionCorp.reCalOnApply(corp);
		            }
		            else
		            {
		            	 	corp.setNewTIS(corp.getTis());					    
						    corp.setAdjust("0");
							corp.setNewLTP(corp.getClose());							
						 					
		            }
		        }
//checking for errors
				
//check whether newtis or newltp is negative			        
		        String tis=corp.getNewTIS();
				boolean chk_tis=tis.startsWith("-");
				if(chk_tis==true)
				{
					flg_val=1;
				}
				else
				{
					String ltp=corp.getNewLTP();
					boolean chk_ltp=ltp.startsWith("-");
					if(chk_ltp==true)
						flg_val=1;
					else
						flg_val=0;
				}
		    }catch(Exception e){
		        Logging.error("error="+e.getMessage());
		    }
		    return flg_val;
		}
// This method is use to get the currency exchange value		
		public static void get_currency(Connection con,Connect connect,FixedIncomeCorporate corp,String index,String stock)
		{
			String apd=corp.getApply_date();
			try{
				String butt=corp.getButton();// for undo
				
//as this method is used both for historic and general CA, so there shold be date comparison
				int chk_dt=0;
				if(butt==null)
				{
					String dt=UpdateCorp.accept_date();   //get the current date
					String apply=corp.getApply_date();
					chk_dt=FixedIncomeComputeIndexForm.CompareDate(apply,dt);		//check for the current date and user's entered date
				}
				else
					chk_dt=0;
				
//				get index currencies
				Logging.debug("index==="+index+"stock=="+stock);				
				String qry=ConnectInit.queries.getProperty("select_index_name"); 
				ResultSet rs=FixedIncomeListTypeClass1.getAffected(con,qry,index);
				String ind_curr=null;
				if(rs!=null && rs.next()){  
					ind_curr=rs.getString("base_currency_id");
					rs.close();
				}
				
//				get stock currencies
				String qry1=ConnectInit.queries.getProperty("fixed_income_detail_stock_master");
				rs=FixedIncomeListTypeClass1.getAffected(con,qry1,stock);
				String stk_curr="ss";
				while(rs!=null && rs.next())
				{	
				 stk_curr=rs.getString("stock_currency_id");
				}
				if(rs!=null)
					rs.close();
				
				//if both currencies are equal set value to 1
				if(ind_curr.equals(stk_curr))
					corp.setCurr_val("1");
				else  //if not equal
				{
					String ft_curr=null;
					int flg=0;
					qry=null;rs=null;
					//get the respective combination
					qry=ConnectInit.queries.getProperty("currency_combination");
					rs=FixedIncomeListTypeClass1.getResult_corp(con,qry,ind_curr,stk_curr);
					if(rs.next())
					{
						ft_curr=rs.getString("from_to_currency_id");
						flg=1;
					}			
					else
					{
						rs=FixedIncomeListTypeClass1.getResult_corp(con,qry,stk_curr,ind_curr);
						if(rs.next())
						{
							ft_curr=rs.getString("from_to_currency_id");
							flg=2;
						}
						else
						{
							corp.setCurr_val("1");
							flg=0;
						}
					}
					//from combination get the exchange rate
					if(flg!=0)
					{
						if(chk_dt==0)
						{
							if(butt==null)
							{
								String query=ConnectInit.queries.getProperty("resp_exrate_value");
								rs=FixedIncomeListTypeClass1.getResult12(con,query,ft_curr);
							}
							else
							{
								String query=ConnectInit.queries.getProperty("resp_undo_curr_val");
								rs=FixedIncomeListTypeClass1.getResult_apply(con,query,ft_curr,corp.getApplied_date());
							}
						}
						else
						{
							String query=ConnectInit.queries.getProperty("resp_undo_curr_val");
							rs=FixedIncomeListTypeClass1.getResult_apply(con,query,ft_curr,corp.getApply_date());
						}
						String val="";
						while(rs.next() && rs!=null)
						{		
						  val=rs.getString("ex_last_value");
						}
						if(rs!=null)
							rs.close();
						if(flg==1)
							corp.setCurr_val(val);
						if(flg==2)
						{
							double val1=(1/(Double.parseDouble(val)));
							corp.setCurr_val(Double.toString(val1));
						}
					}
				}
				Logging.debug("out of curr");
			}catch(Exception e){
				Logging.error("Error="+e.getMessage());
				}	
		}
		public static void tmcv_div(FixedIncomeCorporate corp)
		{
			try{
		//		AdjustDecimal adj=new AdjustDecimal();
				AdjustDecimal adj = ConnectInit.getAdjustDecimal();
				String oldtmcv=corp.getTmcv();
			 	oldtmcv=AdjustDecimal.ArrangeAsNumeric(oldtmcv);
			 	corp.setTmcv(oldtmcv);
			 	
			 	String odiv=corp.getDivisor();
				odiv=AdjustDecimal.ArrangeAsNumeric(odiv);
				corp.setDivisor(odiv);
				
				String ntmcv=corp.getNewtmcv();
				ntmcv=adj.shareholdingpatt(ntmcv);   //Remove E power
				ntmcv=AdjustDecimal.ArrangeAsNumeric(ntmcv); //assign ','
				corp.setNewtmcv(ntmcv);
				
				String ndiv=corp.getNewdivisor();
				ndiv=adj.shareholdingpatt(ndiv);
				ndiv=AdjustDecimal.ArrangeAsNumeric(ndiv);
				corp.setNewdivisor(ndiv);
			}catch(Exception e){
				Logging.error("Error="+e.getMessage());
				}
		}
	}


