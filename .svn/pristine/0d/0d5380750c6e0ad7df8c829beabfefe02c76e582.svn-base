/*
 * Created on Mar 6, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.masters;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import app.Connect;
import app.ListTypeClass1;
import app.QueryClass1;

import com.harrier.initializeation.ConnectInit;
/**
 * @author naresh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StockMasterAction extends Action
 {	
	Logger Logging = Logger.getLogger(StockMasterAction.class);
	static Connect con1 = ConnectInit.getConnect();
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)
		{
		try{
			Connect c =ConnectInit.getConnect();
			StockMasterForm form1 = (StockMasterForm)form;
			ActionForward fr = null;
		   String chk_but=form1.getB1();
		   String chk_but1=form1.getB2();
		   Logging.debug("inside action "+chk_but+" inside action "+chk_but1);
		   Connection con=null;
		   if(con==null)
		   {
		   	con= c.getdbConnection();
		   }
		   if(chk_but!=null && chk_but.equals("Submit"))
		   {
		  
		   	
		   	Logging.debug("Inside button  submit");
		   	String query=null;
			String qry1=null;
			int flag=0;
			
			QueryClass1 qcl = new QueryClass1(); //26/07/06
			//QueryClass1.setOldValues(form1);//Not by ME
			String stk_id=form1.getS_stockID();
			Logging.debug(stk_id);
		   		if(stk_id!=null)
				{
					
		   			Logging.debug("button is Update");	 
					String id=stk_id;
					//QueryClass1.setOldValues(form1);//Tghis also
					query=null;
					qry1=null;					 
					query=ConnectInit.queries.getProperty("update_stock_master_from_sm.Action");
					qry1=ConnectInit.queries.getProperty("insert_stock_master_history_from_sm.Action");
					//QueryClass1.updateStockMaster(query,qry1,form);//26/07/06
					qcl.updateStockMaster(query,qry1,form);
					flag=1;	
					String stock="/pages/masters/stockmaster2.jsp?s_stockid="+id+"&display=Update";
					return fr= new ActionForward(stock);
				}
				if(stk_id==null){
				Logging.debug("button is submit");			
				query=null;
				qry1=null;
				query=ConnectInit.queries.getProperty("insert_into_stock_master_from_sm.Action");
				qry1=ConnectInit.queries.getProperty("insert_stock_master_history_from_sm.Action");
				//QueryClass1.insertIntoStockMaster(query,qry1,form);
				QueryClass1.insertIntoStockMaster(query,qry1,form);
				
				if(form1.isTrans_flag()==false)
				{
					if(form1.getNewIssue()!=null)
					{
						if(form1.getNewIssue().equals("NewIssue"))
						{						
							Logging.debug("stock name--===="+form1.getS_stockName());
							Logging.debug("exchange======"+form1.getS_stockExchange());						
							QueryClass1.get_newIssue_price(con,form1);
							Collection stk_coll=form1.getUnrecogstockCollection();
							stk_coll.clear();
							form1.setUnrecogstockCollection(stk_coll);
							form1.setUnrecogstockCollection(null);
						}
					}
				}
				 
				return fr= new ActionForward("/pages/masters/stockmaster2.jsp?display=Insert");
			   }
			
		  }		  
		   Logging.debug("chk_but value is "+chk_but+" chk_but1 value is "+chk_but1);
		  	if(chk_but!=null && chk_but.equals("New"))
		  	{
		   		Logging.debug("Inside new");
		   		return fr= new ActionForward("/pages/masters/stockmaster2.jsp?new_but='New'");	   		
		   		
		  	}
		  	if(chk_but!=null && chk_but.equals("View Corporate Actions"))
		  	{
		   		Logging.debug("Inside View Corporate Actions");
		   		String stockid1=form1.getS_stockID();
		   		String url="/pages/CorporateDiary.jsp?ref_flag=2&s_stock="+stockid1;
		   		//return fr= new ActionForward("/pages/CorporateDiary.jsp");
		   		return fr= new ActionForward(url);		   		
		  	}
		 
			String newissue=request.getParameter("newissues_but");
		  	if(newissue!=null && (!(newissue.equals(""))))
		  	{
		  		if(newissue.equals("Stock"))
		  		{
//		  		 commented by pranoti 6SEP05 as per IISL requirement()		  			
		  			//get unreconized stock details
		  			Connection connection = null;
		  			
		  			String se_query=ConnectInit.queries.getProperty("newissue_detail_se");
		  			String cntry_query=ConnectInit.queries.getProperty("newissue_detail_cntry");
		  			String class_query=ConnectInit.queries.getProperty("newissue_detail_class");
		  			String curr_query=ConnectInit.queries.getProperty("newissue_detail_curr");		  			
		  	/*		String div[]=ActionCorp.token(form1.getS_stockName());		  			
		  			ResultSet rs=ListTypeClass1.getResult_event(con,se_query,div[0].toLowerCase(),div[1]);
		  			ResultSet rs1=ListTypeClass1.getResult_event(con,cntry_query,div[0].toLowerCase(),div[1]);
		  			ResultSet rs2=ListTypeClass1.getResult_event(con,class_query,div[0].toLowerCase(),div[1]);
		  			ResultSet rs3=ListTypeClass1.getResult_event(con,curr_query,div[0].toLowerCase(),div[1]);*/
		  			
		  			
                  //Commented on 28 AUG 06...require queries...Improper data...
		  			String x=form1.getS_stockName();
		  			String y=form1.getS_stockExchange();
		  			
		  			try {
		  			ResultSet rs=ListTypeClass1.getResult_event(con,se_query,x.toLowerCase(),y);
		  			while(rs.next())
		  			{
		  				String sc_code = rs.getString(1);
		  				form1.setB_exc_code(sc_code);
		  			}
		  			rs.close();
		  			
		  			/*ResultSet rs1=ListTypeClass1.getResult_event(con,cntry_query,x.toLowerCase(),y);
		  			ResultSet rs2=ListTypeClass1.getResult_event(con,class_query,x.toLowerCase(),y);
		  			ResultSet rs3=ListTypeClass1.getResult_event(con,curr_query,x.toLowerCase(),y);	*/	  			
		  			
		  			//QueryClass1.get_newissue_stkdetail(rs,rs1,form1); Still not Proper method........
		  			
		  			//rs1.close();
		  			//rs2.close();rs3.close();
		  			
		  			 if((form1.getS_countryName()==null) || (form1.getS_stockCurrency()==null))	    {
			 		    	
			 		    		
			 		    		form1.setS_countryName("INDIA");		 		    		
			 		    		form1.setS_stockCurrency("INDIAN RUPEE");		
			 		    		//form1.setS_stockType("EQUITY");	
			 		    	
			 		    }
		  			}catch(Exception e){
		  				
		  			}finally{
						try{if(connection!=null)
							connection.close();
						}catch(Exception ee){
							Logging.error(" Error : Unable to close Connection "+ee.getMessage());
						}
					}
		  			return new ActionForward("/pages/masters/NewIssues.jsp");
		  		  }
		  		}
		  	
		  /*	if(newissue.equals("AddStk") )//&& (!(form1.StopRepetition.trim().equals("end"))))
	  		{
	  			try{		  		
//	  			 commented by pranoti 6SEP05 as per IISL requirement()	
	  			System.out.println("worst case...."+form1.getS_stockName());
	  			//String div[]=ActionCorp.token(form1.getS_stockName());
	  		
	  			//String val=div[0];
	  			String scripname=null;
	  			String x=form1.getS_stockName();
	  			if(x!=null)	
	  			{
	  			 scripname=form1.getScripname(x);
	  			form1.setS_stockName(scripname);		  			
	  			}
	  			 String y=form1.getS_stockExchange();
	  			
	  			
	  		//	form1.StopRepetition="end";
	  			String se_query=c.queries.getProperty("newissue_detail_se");
	  			String cntry_query=c.queries.getProperty("newissue_detail_cntry");
	  			String class_query=c.queries.getProperty("newissue_detail_class");
	  			String curr_query=c.queries.getProperty("newissue_detail_curr");
	  			String get_class_id=c.queries.getProperty("get_class_of_shares");
	  			ResultSet rs=ListTypeClass1.getResult_event(con,se_query,x.toLowerCase(),y);
	  			ResultSet rs1=ListTypeClass1.getResult_event(con,cntry_query,x.toLowerCase(),y);
//	  		 	 commented by pranoti 13SEP05 as per IISL requirement(get class_of_shares id)
	  			PreparedStatement  stmt = con.prepareStatement(get_class_id);
	  			stmt.setString(1,form1.getS_stockType().toLowerCase());
	  			ResultSet rs2 = stmt.executeQuery();		  					  			
	  			ResultSet rs3=ListTypeClass1.getResult_event(con,curr_query,x.toLowerCase(),y);
	  			QueryClass1.get_newissue_stkdetail_stock(rs,rs1,rs2,rs3,form1);
	  			rs.close();rs1.close();
	  			rs2.close();rs3.close();
	  			form1.setB1(null);	
	 		    form1.setS_marketLot("1");
	 		    form1.setD_iwf("1");
	 		    if((form1.getS_countryName()!=null) || (form1.getS_stockCurrency()!=null))
	 		    {
	 		    	if((form1.getS_countryName().equals("0"))|| (form1.getS_countryName().equals("")))
	 		    	{
	 		    		rs = con.createStatement().executeQuery(c.queries.getProperty("select_system_config"));
	 		    		rs.next();
	 		    		form1.setS_countryName(rs.getString(10));		
	 		    		rs.close();
	 		    	}
	 		    	if((form1.getS_stockCurrency().equals("0")) || (form1.getS_stockCurrency().equals("")))
	 		    	{
	 		    		rs = con.createStatement().executeQuery(c.queries.getProperty("select_system_config"));
	 		    		rs.next();
	 		    		form1.setS_stockCurrency(rs.getString(12));		
	 		    		rs.close();
	 		    	}
	 		    }
//	 		 commented by pranoti 6SEP05 as per IISL requirement()		 		  
	 		  //form1.setF_alertPercent("10");
	 		  //form1.setF_rejectionPercent("20");	
	 		   form1.getStockTypeCollection();
	 		    newissue=null;
	 		    form1.setNewissues_but(null);
	 		   if(con!=null)
	  			con.close();
	 		    con=null;
	 		   System.out.println("......before forward..."+form1.getNewissues_but());		 		   
	  			return new ActionForward("/pages/stockmaster2.jsp");
	  					  			 
	  			}catch(Exception e){
	  				Logging.getError("Error in Action =="+e.getMessage());
	  				}
	  		}*/
		  	
		
		}catch(Exception ae){
		  	Logging.error("Exception :"+ae.getMessage());
		  }
		  
		  return mapping.getInputForward();
		}
}
