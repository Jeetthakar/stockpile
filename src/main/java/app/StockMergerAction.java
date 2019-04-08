/*
 * Created on Feb 16, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

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

import com.harrier.initializeation.ConnectInit;

/**
 * @author pranoti 
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StockMergerAction extends Action{
	Logger Logging = Logger.getLogger(StockMergerAction.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Connect connect = ConnectInit.getConnect();  //connection
		Connection con=null;
//		Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		try{
			if(con == null){
				 con = connect.getdbConnection();
			}
		
		StockMergerForm stockMerge=(StockMergerForm)form;	
		String mergeButt=request.getParameter("mergeButt");
		Logging.debug("merge butt = ="+mergeButt);
		if(mergeButt!=null)
		{
			if(mergeButt.equals("Exc1"))
			{				
				stockMerge.setStock1(null);
				stockMerge.setStock3(null);
				stockMerge.ref_hash1();  //refresh affected indices
				stockMerge.Stkreset1();  //refresh stock details	
				stockMerge.reset_val(); //refresh values
				stockMerge.reset_parameter();  //refresh index parameter 
			}
			if(mergeButt.equals("Exc2"))
			{
				stockMerge.setStock2(null);
				stockMerge.Stkreset2();  //refresh stock details
				stockMerge.ref_hash2();  //refresh affected indices	
				String stk3=stockMerge.getStock3();
				Logging.debug("stk3 in exc=="+stk3);				
				if(stk3==null)
					stockMerge.reset_val(); //refresh values
				Logging.debug("merger type in stock2=="+stockMerge.getMerge_type());
				stockMerge.reset_parameter();  //refresh index parameter
			}
			if(mergeButt.equals("Stock1"))
			{
				String stock=request.getParameter("stock1");				
				if((stock.equals(""))|(stock.equals("Select Stock")))
					stock=null;
				if(stock!=null)			//get stock details
				{
					 String query =ConnectInit.queries.getProperty("stock_details_for_ca");
				       ResultSet rs = ListTypeClass1.getResult12(con,query,stock);
					    try{
							stockMerge.setStkResult1(rs);
				        }catch(Exception e) {
						Logging.error(" ListTypeClass1: Error  in result set "+e.getMessage());
						}
				}
				else		
					stockMerge.Stkreset1();  //refresh stock details
				stockMerge.reset_val(); //refresh values				
				stockMerge.reset_parameter();  //refresh index parameter
				stockMerge.setCommit_butt("2");
				stockMerge.setStock3(null);
				return new ActionForward("/pages/StockMerger.jsp");
			}
			if(mergeButt.equals("Stock2"))
			{
				String stock=request.getParameter("stock2");				
				if((stock.equals(""))|(stock.equals("Select Stock")))
					stock=null;
				if(stock!=null)			//get stock details
				{
					 String query =ConnectInit.queries.getProperty("stock_details_for_ca");
				       ResultSet rs = ListTypeClass1.getResult12(con,query,stock);
					    try{
							stockMerge.setStkResult2(rs);
				        }catch(Exception e) {
						Logging.error("ListTypeClass1: Error  in result set "+e.getMessage());
						}
				}
				else		
					stockMerge.Stkreset2();  //refresh stock details
				String stk3=stockMerge.getStock3();
				Logging.debug("stk3 in exc=="+stk3);
				if(stk3==null)
					stockMerge.reset_val();				
				stockMerge.reset_parameter();  //refresh index parameter
				stockMerge.setCommit_butt("2");				
				Logging.debug("merger type in stock2=="+stockMerge.getMerge_type());
				return new ActionForward("/pages/StockMerger.jsp");
			}
			
			//----------------Apply------------------			
			if(mergeButt.equals("Apply"))  //Intial Apply
			{	
				stockMerge.setCommit_butt(null);
				stockMerge.setRad_trans(null);
				stockMerge.setRad_butt(null);
				
				stockMerge.reset_store();
				ActionMerge.Check_mergerType(stockMerge);				
				return new ActionForward("/pages/StockMerger.jsp");
			}
			if(mergeButt.equals("Apply1")) //For Deactivate Stock
			{	
				ActionMerge.deactive_stk(stockMerge);
				stockMerge.setMerge_info("d");  //deactivate stock
				stockMerge.setTemp_type(null);
				return new ActionForward("/pages/StockMerger.jsp");
			}
			if(mergeButt.equals("Trans"))  //To continue further transaction or not
			{
				String rad_tran=stockMerge.getRad_trans();
				if(rad_tran!=null)
				{
					if(rad_tran.equals("s")) //stop the transaction
					{
						stockMerge.setMerge_info("st");
						stockMerge.setTemp_type(null);
						stockMerge.setCommit_butt("1");
						if(stockMerge.getStock3()!=null)
						{
							stockMerge.setStock2(stockMerge.getStock3());
							stockMerge.setExc2(stockMerge.getExc3());
							stockMerge.setExc3(null);
							stockMerge.setStock3(null);
							String query =ConnectInit.queries.getProperty("stock_details_for_ca");
					       ResultSet rs = ListTypeClass1.getResult12(con,query,stockMerge.getStock2());
						    try{
								stockMerge.setStkResult2(rs);
					        }catch(Exception e) {
							Logging.error("ListTypeClass1: Error  in result set "+e.getMessage());
							}
					        stockMerge.getAff2Collection();
						}
						return new ActionForward("/pages/StockMerger.jsp");
					}
					if(rad_tran.equals("ct")) //continue transaction After Merged Transactions are over
					{
						int flag=0;
						String type=stockMerge.getMerge_type();
						if(type!=null)
						{
							if(type.equals("4"))  //outside-outside merging
							{
								stockMerge.setMerge_info("s");  //share issuance
								ActionMerge.Share_iss(stockMerge);
							}
							if(type.equals("1"))//Outside-Index merging
							{
								flag=check_close(stockMerge.getHash_aff2(),stockMerge.getApply_date());
								if(flag==1)  //For Acquiring Indices
								{
									stockMerge.setMerge_info(null);
									stockMerge.setTemp_type(null);
									return new ActionForward("/pages/StockMerger.jsp?Close=dest");
								}
								if(flag==0)
								{
									stockMerge.setMerge_info("s");  //share issuance								
									ActionMerge.Share_iss(stockMerge);
								}
							}							
							if((type.equals("2")) | (type.equals("3"))) //Index-Outside merging
							{
								//for Addition of stock
								stockMerge.setMerge_info(null);
								stockMerge.setStock3(stockMerge.getStock2());
								stockMerge.setExc3(stockMerge.getExc2());
								try{
									String qry=ConnectInit.queries.getProperty("select_system_config");						
									ResultSet rs1=ListTypeClass1.resultCorporate(con,qry);
									rs1.next();
									stockMerge.setExc2(rs1.getString("stock_ex_id"));
									rs1.close();
								}catch(Exception e){
									Logging.debug("Error="+e.getMessage());
									}
								stockMerge.setStock2(null);
								stockMerge.Stkreset2();								
								return new ActionForward("/pages/StockMerger.jsp?Addstk=Add");
							}							
						}
					}//rad teans ct
					if(rad_tran.equals("cd")) //continue transaction after add stock 
					{
						Logging.debug("merger type=="+stockMerge.getMerge_type());
						//if Added company is acquiring company only, then for share iss copy all affeted indices of merged company into acquring company
						if(stockMerge.getStock3()!=null)
						{
							String type=stockMerge.getMerge_type();
							if((type.equals("2")) | (type.equals("3")))
							{
								Hashtable hash1=stockMerge.getHash_aff2();
								if(type.equals("2"))
								{
									hash1.clear();
									stockMerge.setHash_aff2(hash1);
									hash1=stockMerge.getHash_aff2();
								}
								if(stockMerge.getStock2().equals(stockMerge.getStock3()))
								{
									Hashtable hash=stockMerge.getHash_aff1();									
									for(Enumeration enumm=hash.keys();enumm.hasMoreElements();)
									{
										String id=(String)enumm.nextElement();
										hash1.put(new String(id),id);
									}
									stockMerge.setHash_aff2(hash1);
								}
							}
							stockMerge.setStock2(stockMerge.getStock3());
							stockMerge.setExc2(stockMerge.getExc3());
							String query =ConnectInit.queries.getProperty("stock_details_for_ca");
					        ResultSet rs = ListTypeClass1.getResult12(con,query,stockMerge.getStock2());
						    try{
								stockMerge.setStkResult2(rs);
					        }catch(Exception e) {
							Logging.error("ListTypeClass1: Error  in result set "+e.getMessage());
							}
							stockMerge.setStock3(null);
							stockMerge.setExc3(null);
							stockMerge.getAff2Collection();
						}						
						int flag=0;
						flag=check_close(stockMerge.getHash_aff2(),stockMerge.getApply_date());
						if(flag==1)  //For Acquiring Indices
						{
							stockMerge.setMerge_info(null);
							stockMerge.setTemp_type(null);
							return new ActionForward("/pages/StockMerger.jsp?Close=dest");
						}
						if(flag==0)
						{
							stockMerge.setMerge_info("s");  //share issuance
							ActionMerge.Share_iss(stockMerge);
							return new ActionForward("/pages/StockMerger.jsp");
						}
					}
				}
			}
			if(mergeButt.equals("Add"))
			{
				Logging.debug("merger type in add=="+stockMerge.getMerge_type());
				stockMerge.setCommit_butt(null);
				int flag=0;
				flag=check_close(stockMerge.getHash_aff1(),stockMerge.getApply_date());
				if(flag==1)  //For Acquiring Indices
				{
					stockMerge.setMerge_info(null);
					stockMerge.setTemp_type(null);
					return new ActionForward("/pages/StockMerger.jsp?Close=target");
				}
				if(flag==0)
				{
					stockMerge.setMerge_info("a");  //Add Stock
					ActionMerge.add_stk(stockMerge);
					return new ActionForward("/pages/StockMerger.jsp");
				}
			}				
			if(mergeButt.equals("Radio"))// For Affected Indices not having a closing value
			{
				String rad_val=stockMerge.getRad_butt();
				if(rad_val!=null)
				{
					if((rad_val.equals("ct")) | (rad_val.equals("cd")))
					{
						ActionMerge.compute_ind(stockMerge);
						stockMerge.setMerge_info("c");  //delete stock						
						return new ActionForward("/pages/StockMerger.jsp");
					}
					if((rad_val.equals("rt")) | (rad_val.equals("rd")))
					{
						ActionMerge.recal_index(stockMerge);
						stockMerge.setMerge_info("r");
						return new ActionForward("/pages/StockMerger.jsp");		
					}
				}				
			}
			if(mergeButt.equals("Apply2"))  //For Share Issuance
			{
				Logging.debug("type in shareiss=="+stockMerge.getMerge_type());				
				stockMerge.setMerge_info("s");  //share issuance
				ActionMerge.Share_iss(stockMerge);
				return new ActionForward("/pages/StockMerger.jsp");
			}
			if(mergeButt.equals("Apply3"))  //For Delete Stock
			{
				try{					
					int flag=0;					
					String type=stockMerge.getMerge_type();					
					if(type!=null)  //check type of merging
					{
						if(type.equals("2") | type.equals("3"))
						{
							//check for merged company Affected indices closing value
							flag=check_close(stockMerge.getHash_aff1(),stockMerge.getApply_date());							
						}
					}//type not null										
					if(flag==1)//Affected Indices doesn't have latest closing value
					{
						stockMerge.setTemp_type(null);
						return new ActionForward("/pages/StockMerger.jsp?Close=target");												
					}
					if(flag==0)
					{					
						stockMerge.setRad_butt(null);
						ActionMerge.delete_stk(stockMerge);
						stockMerge.setTemp_type(null);
						stockMerge.setMerge_info("de");  //delete stock						
						return new ActionForward("/pages/StockMerger.jsp");					
					}				
				}catch(Exception e){
					Logging.debug("Error ="+e.getMessage());
					}
			}
			if(mergeButt.equals("Apply5")) //For Recalculate or Recompute, delete stock
			{
				ActionMerge.delete_stk(stockMerge);
				stockMerge.setMerge_info("de");  //Add stock				
				return new ActionForward("/pages/StockMerger.jsp");
			}
			if(mergeButt.equals("Apply4")) //For Add Stock
			{
				ActionMerge.add_stk(stockMerge);
				stockMerge.setMerge_info("a");  //Add stock
				return new ActionForward("/pages/StockMerger.jsp");
			}
			if(mergeButt.equals("Comp"))
			{
				stockMerge.setMerge_info("st");
				stockMerge.setCommit_butt("1");
				return new ActionForward("/pages/StockMerger.jsp");
			}
			
			//------------Affect-----------------
			if(mergeButt.equals("Affect1"))  //for Merged Indices
			{
				String affect=stockMerge.getAffectedIndex1();
				if(affect!=null)
					if(affect.equals("Affected Index"))
						affect=null;
				stockMerge.setMerge_info(null);					
				if(affect==null)
				{
					stockMerge.setIndexval1(null);
					stockMerge.setTmcv1(null);
					stockMerge.setDivisor1(null);
					stockMerge.setNewtmcv1(null);
					stockMerge.setNewdivisor1(null);
				}
				else
					ActionMerge.affect_ind1(stockMerge);				
				
				return new ActionForward("/pages/StockMerger.jsp");
			}
			if(mergeButt.equals("Affect2")) //for Acquiring Indices
			{
				String affect=stockMerge.getAffectedIndex2();
				if(affect!=null)
					if(affect.equals("Affected Index"))
						affect=null;
					
				stockMerge.setMerge_info(null);
				if(affect==null)
				{
					stockMerge.setIndexval2(null);
					stockMerge.setTmcv2(null);
					stockMerge.setDivisor2(null);
					stockMerge.setNewtmcv2(null);
					stockMerge.setNewdivisor2(null);
				}
				else
					ActionMerge.affect_ind2(stockMerge);
				
				return new ActionForward("/pages/StockMerger.jsp");
			}
			//------------Commit------------------			
			if(mergeButt.equals("Commit"))
			{
				stockMerge.setCommit_butt(null);				
				Logging.debug("in commit action");
				
				//Make out changes
				ActionMerge.commit_change(stockMerge);
				String type=stockMerge.getMerge_type();				
				Hashtable hash2=stockMerge.getHash_aff2();
				stockMerge.setTmcv1(null);
				stockMerge.setDivisor1(null);
				stockMerge.setNewtmcv1(null);
				stockMerge.setNewdivisor1(null);
				stockMerge.setIndexval1(null);
				stockMerge.setCommit_butt("2");
				stockMerge.setMerge_info("f");				
				return new ActionForward("/pages/StockMerger.jsp");
			}
		}
		
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		return mapping.getInputForward();
	}
	
//this method is used to check Affected Index Closing value
	public int check_close(Hashtable hash,String date)
	{
		int flag=0;
		try{
			Connect connect = ConnectInit.getConnect();  //connection
			Connection con=null;
			if(con == null){
				 con = connect.getConnectionForMergerTransaction();//getConnection
			}

			String query=ConnectInit.queries.getProperty("select_resp_close");									
			for(Enumeration enumm=hash.keys();enumm.hasMoreElements();)
			{
				String id=(String)enumm.nextElement();
				ResultSet rs=ListTypeClass1.getResult_apply(con,query,id,date);
				if(rs.next())
				{
					String ind_close=rs.getString("index_closing_value");
					if(ind_close==null)
					{
						flag=1;
						break;
					}
				}
				else
				{
					flag=1;
					break;
				}
			}
		}catch(Exception e){
			Logging.debug("Error="+e.getMessage());
			}
		return flag;
	}
}