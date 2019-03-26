/*
 * Created on Jan 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.chart.demo.servlet.AdjustDecimal;

import com.harrier.initializeation.ConnectInit;

/**
 * @author pranoti
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IndexCom_Action extends Action{
	static Logger Logging = Logger.getLogger(IndexCom_Action.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			Corporate corp = (Corporate)form;  //bean object			
			Connect connect = ConnectInit.getConnect();   //connection
			Connection con=null;
			try{
//			Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
			if(con == null){
				 con = connect.getdbConnection();
			}	
				
			StockDetailsCollection stockCollection=new StockDetailsCollection();  //stockdetailscollection object
			String ind_com_but=corp.getCompo_button();
			if(ind_com_but!=null)   //get respective action
			{
				if(ind_com_but.equals("Add"))  //on click of add button 
				{		
					//to add stock in an index composition
					String id[]=request.getParameterValues("c_Cad");			
					stockCollection.removeStocksFromSourceTable1(corp,id);
					 corp.setCompo_button(null);
					return new ActionForward("/pages/IndexEventComposition.jsp");
				}
				if(ind_com_but.equals("Remove"))  //on click of remove button
				{					
					//to remove stock from index composition
					String id1[]=request.getParameterValues("c_Cad1");			
					 stockCollection.addStocksInSourceTable1(corp,id1);
					 corp.setCompo_button(null);
					return new ActionForward("/pages/IndexEventComposition.jsp");
				}
				if(ind_com_but.equals("Apply"))  //onclick of apply button
				{
					Logging.debug("iwf val== ="+corp.getValues());					
					int flag=0;
					try{					
					String dt=UpdateCorp.accept_date();   //get the current date
					String apply=corp.getApply_date();
					int chk_dt=ComputeIndexForm.CompareDate(apply,dt);	//check for the current date and user's entered date					
					if(chk_dt==0)
					{
						String date=UpdateCorp.accept_date();  //get the current date
						//check whether the selected index has closing value or not for the current date
						String query=ConnectInit.queries.getProperty("select_resp_close");
						ResultSet rs=ListTypeClass1.getResult_apply(con,query,corp.getI_index(),date);
						if(rs.next())
						{					
							String close=rs.getString("index_closing_value");
							rs.close();					
							if(close==null)
								flag=0;
							else
								flag=1;
						}
						else
							flag=0;
						
//					get child indices
						get_child_indices(con,connect,corp);
						
						if(flag==0)	//if there is no closing value
						{
							//send the response back with an alert, either to recalculate or recompute index parameter 
							return new ActionForward("/pages/IndexEventComposition.jsp?close=Close");
						}
						if(flag==1)  //if there is closing value
						{
							corp.setCommit_butt("1");
							ActionCorp.applyIndex(con,connect,corp);  //calculate new value
							tmcv_div_adj(corp);  //remove E power from final values					
							check_hash_error(corp); //display stock who doesn't have the closing value
						  return new ActionForward("/pages/IndexEventComposition.jsp");
						}	
					}//if dates are equal
					else  //if not equal then it is a historic CA
					{							
						corp.setLeng(null);								
						corp.setR_type("index event diary");
						Logging.debug("iwf in hhisto=="+corp.getValues());
						Logging.debug("hash 12==="+corp.getHash12());
						return new ActionForward("/pages/HistoricAction.jsp?flag=New");
					}
					}catch(Exception e){Logging.error("error="+e.getMessage());}					
				}
				if(ind_com_but.equals("Affect"))  //on click of affect child index
				{	
					String affect=request.getParameter("affect");
					if(affect!=null)
					{
						if((affect.equals(""))|(affect.equals("Affected Child Index")))
						{
							corp.reset2();
							affect=null;
						}
					}
					corp.setTmcv(request.getParameter("tmcv"));;
			        corp.setDivisor(request.getParameter("divisor"));
			        corp.setNewTmcv(request.getParameter("newTmcv"));
			        corp.setNewdivisor(request.getParameter("newdivisor"));
					String rad_val=corp.getInd_comp();					
					String dt=UpdateCorp.accept_date();   //get the current date
					String apply=corp.getApply_date();
					int chk_dt=ComputeIndexForm.CompareDate(apply,dt);		//check for the current date and user's entered date
					if(rad_val!=null)//if there is no index closing value
					{
						if(rad_val.equals("c"))  //to recalcualte
						{
							if(chk_dt==0)
								ActionCorp.recal_affect_index(con,connect,corp,affect);  //recalculate affected index value
							else
								ActionCorp.hist_recal_affect_index(corp,affect);
						}
					}
					else	//if there is closing value
					{
						if(chk_dt==0)
							ActionCorp.affect_comp_index(con,connect,corp,affect); //calculate affected index value
						else
							ActionCorp.hist_recal_affect_index(corp,affect);	
					}					
					tmcv_div_adj(corp);	//remove E power				  
					return new ActionForward("/pages/IndexEventComposition.jsp");
				}
				synchronized(corp.StopRepetition)   //for duplicate entry
				{
					corp.StopRepetition=corp.StopRepetition+"";
				if((ind_com_but.equals("Commit")) && (!(corp.StopRepetition.trim().equals("end"))))//on click of commit button
				{	
					corp.setCommit_butt(null);
					corp.setSucc_butt("1");
					corp.StopRepetition="end";															
					Hashtable hash3=corp.getHash3();
					Hashtable hash4=corp.getHash4();
					Hashtable hash=corp.getHash();
					if(!(hash3.isEmpty()))
						hash.putAll(hash3);
					if(!(hash4.isEmpty()))
						hash.putAll(hash4);
					corp.setHash(hash);	
					
					String dt=UpdateCorp.accept_date();   //get the current date
					String apply=corp.getApply_date();
					int chk_dt=ComputeIndexForm.CompareDate(apply,dt);	//check for the current date and user's entered date
					IndexCA_Action.remove_token(corp); //remove ',' from index parameter
					if(chk_dt==0)
					{
			        	UpdateCorp.assign_index_cad(con,connect,corp);  //update parent index
			        	
			        	String rad_val=request.getParameter("ind_comp");
			        	String ind=corp.getI_index();
			        	if(rad_val!=null) //if there is no index closing value
			        	{
			        		if(rad_val.equals("c")) //to recalculate
			        		{		        			
			        			UpdateCorp.recal_update_affect_index_comp(corp);  //update child indices	        			
			        		}
			        	}
			        	else  //if there is closing value
			        		UpdateCorp.update_affect_index_comp(corp);  //update child indices
			        	
			        	corp.setI_index(ind);
					}
					else
					{
						UpdateCorp.update_hist_diary(corp);
					}
					tmcv_div_adj(corp);
					return new ActionForward("/pages/IndexEventComposition.jsp");
				}
				else
					corp.StopRepetition="a";
				}
				if(ind_com_but.equals("Radio"))  //on click of radio button, either to recalculate or recompute index parameter
				{
					String dt=UpdateCorp.accept_date();   //get the current date
					String apply=corp.getApply_date();
					int chk_dt=ComputeIndexForm.CompareDate(apply,dt);	//check for the current date and user's entered date
					corp.setCommit_butt("1");
					String rad_val=corp.getInd_comp();
					if(rad_val!=null)
					{
						if(rad_val.equals("i"))	//to recompute
						{
							if(chk_dt==0)
								return new ActionForward("/pages/IndexHome.jsp?D1="+corp.getI_index());
							else
							{
								ActionCorp.Hist_applyindex(corp,2);
								return new ActionForward("/pages/IndexEventComposition.jsp");
							}
						}						
						if(rad_val.equals("c")) //to recalculate
						{
							if(chk_dt==0)
							{
								ActionCorp.recalIndex(con,connect,corp);  //recalculate new value
								tmcv_div_adj(corp);			  //remove E power
								check_hash_error(corp);		  //check for stock's closing value
								return new ActionForward("/pages/IndexEventComposition.jsp");
							}
							else
							{
								ActionCorp.Hist_applyindex(corp,0);
								check_hash_error(corp);		  //check for stock's closing value
								return new ActionForward("/pages/IndexEventComposition.jsp");
							}
						}
					}
				}
			}
			String hist_butt=request.getParameter("hist_but");			
			if(hist_butt!=null)
			{
				if(hist_butt.equals("Back"))
				{ 
					return new ActionForward("/pages/IndexEventComposition.jsp");
				}
				if(hist_butt.equals("Continue"))
				{
					corp.setR_type("index event diary");
					ActionCorp.Historic_cal(corp);
					
					//check index has closing value or not
					int flg=IndexCA_Action.check_close(con,connect,corp);
					if(flg==0)	
					{
						corp.setCommit_butt(null);
						return new ActionForward("/pages/IndexEventComposition.jsp?close=Close");
					}
					if(flg==1)
					{
						ActionCorp.Hist_applyindex(corp,1);
						corp.setCommit_butt("1");
						return new ActionForward("/pages/IndexEventComposition.jsp");
					}
				}
			}
			
//			Close the Dynamic Connection : Manoj Adekar
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
			
		return mapping.getInputForward();
	}
	
//this method is used to remove E power from index values	
	public static void tmcv_div_adj(Corporate corp)
	{
		try{
	//		org.jfree.chart.demo.servlet.AdjustDecimal adj=new org.jfree.chart.demo.servlet.AdjustDecimal();	
			AdjustDecimal adj = ConnectInit.getAdjustDecimal();
			int val=0;
			
			//new TMCV
			String ntmcv=corp.getNewTmcv();
			StringTokenizer st = new StringTokenizer(ntmcv,",");			
			val=st.countTokens();			
			if(val==1)
			{	
				if((ntmcv!=null)|(!(ntmcv.equals(""))))
				{
					ntmcv=adj.shareholdingpatt(ntmcv);
					ntmcv=AdjustDecimal.ArrangeAsNumeric(ntmcv);
					corp.setNewTmcv(ntmcv);
				}
			}
				//new Divisor
			String ndiv=corp.getNewdivisor();
			st = new StringTokenizer(ndiv,",");
			val=st.countTokens();
			if(val==1)
			{
				if((ndiv!=null)|(!(ndiv.equals(""))))
				{
					ndiv=adj.shareholdingpatt(ndiv);
					ndiv=AdjustDecimal.ArrangeAsNumeric(ndiv);
					corp.setNewdivisor(ndiv);
				}
			}
				//old TMCV
			String otmcv=corp.getTmcv();			
			st = new StringTokenizer(otmcv,",");
			val=st.countTokens();			
			if(val==1)
			{				
				otmcv=AdjustDecimal.ArrangeAsNumeric(otmcv);
				corp.setTmcv(otmcv);
			}			
				//old Divisor
			String odiv=corp.getDivisor();
			st = new StringTokenizer(odiv,",");
			val=st.countTokens();
			if(val==1)
			{
				odiv=AdjustDecimal.ArrangeAsNumeric(odiv);
				corp.setDivisor(odiv);
			}
			
			//new child TMCV
			String ntmcv1=corp.getNewtmcv1();
			if((ntmcv1!=null)|(!(ntmcv1.equals(""))))
			{	
				ntmcv1=adj.shareholdingpatt(ntmcv1);
				ntmcv1=AdjustDecimal.ArrangeAsNumeric(ntmcv1);
				corp.setNewtmcv1(ntmcv1);
			}
			//new child Divisor
			String ndiv1=corp.getNewdivisor1();
			if((ndiv1!=null)|(!(ndiv1.equals(""))))
			{			
				ndiv1=adj.shareholdingpatt(ndiv1);
				ndiv1=AdjustDecimal.ArrangeAsNumeric(ndiv1);
				corp.setNewdivisor1(ndiv1);
			}
			//old child TMCV
			String otmcv1=corp.getTmcv1();			
			st = new StringTokenizer(otmcv1,",");			
			otmcv1=AdjustDecimal.ArrangeAsNumeric(otmcv1);
			corp.setTmcv1(otmcv1);
			//old child Divisor
			String odiv1=corp.getDivisor1();
			odiv1=AdjustDecimal.ArrangeAsNumeric(odiv1);
			corp.setDivisor1(odiv1);
		}catch(Exception e){
			Logging.error("Error="+e.getMessage());
			}
	}
	
//	this method is used to display stock's, who doesn't have the closing value  	
	public static void check_hash_error(Corporate corp)
	{
		Connect connect = ConnectInit.getConnect();
		Connection con=null;
		
		//Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		try{
		if(con == null){
			 con = connect.getdbConnection();
		}
		
		try{
			StringBuffer stb=corp.getStr();
			int ls=stb.length();
			stb.delete(0,ls);
			Hashtable hash_error=corp.getHash_error();
			for(Enumeration enum1 =hash_error.keys();enum1.hasMoreElements();)
			{
				String id2 =(String)enum1.nextElement();  					
				String query=ConnectInit.queries.getProperty("detail_stock_master");
				ResultSet rs=ListTypeClass1.getResult1(con,query,id2);
				rs.next();
				String stock_id=rs.getString("stock_name");
				stb.append(stock_id);
			}
			corp.setStr(stb);
		}catch(Exception e){
			Logging.error("Error=="+e.getMessage());
			} 
		
		//Close the Dynamic Connection : Manoj Adekar
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
	}	
	public static void get_child_indices(Connection con,Connect connect,Corporate corp)
	{
		try{
			//	clear the hashtable
			Hashtable affect=corp.getHash_affind();
			affect.clear();
			corp.setHash_affind(affect);
			
//			get the affected child indices list			
			Hashtable hash1=corp.getHash1();
			Hashtable hash2=corp.getHash2();        			
			boolean chk_hash1=hash1.isEmpty();	    		
			if(chk_hash1==false)
			{
				for(Enumeration enum1 =hash1.keys();enum1.hasMoreElements();)
				{
					String stock=(String)enum1.nextElement();
					String query=null;
					query=ConnectInit.queries.getProperty("select_affect_index");			        				
					ListTypeClass1.affect_index_list(con,corp,query,stock);
				}
			}
			chk_hash1=hash2.isEmpty();
			if(chk_hash1==false)
			{
				for(Enumeration enum1 =hash2.keys();enum1.hasMoreElements();)
				{
					String stock=(String)enum1.nextElement();
					Object obj=hash2.get(stock);
					String cid=obj.toString();
        			String qry=ConnectInit.queries.getProperty("select_cad_name");
        			String cname=null;
        			
	        		ResultSet rs=ListTypeClass1.getAffected(con,qry,cid);
        			rs.next();
        			cname=rs.getString("cam_shortname");

        			String div[]=ActionCorp.token2(cname);
        			cname=(div[0]+div[1]).toLowerCase().trim();
        			if(cname!=null)
        			{
        				if(cname.equals("changeiwf"))
        				{
							String query=null;
							query=ConnectInit.queries.getProperty("select_affect_index");			        				
							ListTypeClass1.affect_index_list(con,corp,query,stock);		        				
        				}
        				if(cname.equals("addstock"))
        				{
        					String query=null;
        					query=ConnectInit.queries.getProperty("select_child_index");			        
							ListTypeClass1.affect_index_list(con,corp,query,stock);		        				
        				}
        			}
				
				}	
			}
			String query1=ConnectInit.queries.getProperty("get_undo_index_close");
			ActionCorp.check_affect_index(con,corp,query1);//check whether child index has closing value or not	
		}catch(Exception e){
			Logging.error("Error="+e.getMessage());
			}
	}
}
