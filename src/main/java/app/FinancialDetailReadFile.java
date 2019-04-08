/*
 * Created on Feb 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;
import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.harrier.initializeation.ConnectInit;

/**
 * @author sudhir
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FinancialDetailReadFile {
	static Logger Logging = Logger.getLogger(FinancialDetailReadFile.class);
	public static Hashtable table = new Hashtable();
	Connection con=null;
	boolean improperFormat=true;
	static String FDetails="";
	 public static StringBuffer getHashnBuffer(StringBuffer buffer,BufferedReader br)
	 {
	 	Logging.debug("INside fdr");
	 	/*String fdetail_id  ,company_id ,symbol,series,net_sales ,other_income ,gross_income ,increase_decrease_in_stock ,
		consumption_of_raw_materials ,staff_cost ,total_expenditure_excluding_other_expenditure ,
		other_expenditure ,total_expenditure ,interest ,profit_loss_before_depreciation_taxes ,depreciation ,
		profit_loss_before_tax ,provision_for_taxation ,other_provisions ,misc_expd_w_o ,net_profit_loss ,
		non_recurring_income ,non_recurring_expenses ,adjusted_net_profit_loss ,paid_up_equity_share_capital ,
		reserves_excluding_revaluation_reserves ,dividend ,basic_eps ,diluted_eps ,from_date  ,to_date  ,
		result_type  ,fin_year ,book_value ; */
	 	String str;
	 	try
		{
	 		Logging.debug("Inside FDR try");
	 		String[] arr ;	
	 		int i;
	 		while((str=br.readLine())!=null )
	 		{
	 			buffer.append("<tr>");
	 			arr= str.split(",");
				i=0;
				if(arr.length==0) continue;
				FinancialDetailForm   FD=new FinancialDetailForm();
				int arrlen=arr.length;
				Logging.debug("Inside FDR after new FinancialDetailForm();  "+arrlen);
				if(arrlen!=6 && arrlen!=37){
					buffer.append("<font size='2' face='Arial' color='Red'><tr>File with improper format </tr></font>");
					return buffer;
				}
				if(arrlen==6)
				{
					FDetails="FDetailsNSE";
					Logging.debug("Inside FDetailsNSE");
					while(i<arrlen)
					{						
						switch(i)
						{
							case 	0:
								FD.setSymbol(arr[i]);
								break;
							case 	1:
								FD.setFrom_date(PopFileDialogNewStock.formatDate(arr[i]));
								break;
							case 	2:
								FD.setTo_date(PopFileDialogNewStock.formatDate(arr[i]));
								break;
							case 	3:
								FD.setNet_profit_loss(arr[i]);
								break;
							case 	4:
								FD.setNet_worth(arr[i]);
								break;
							case 	5:
								FD.setDividend(arr[i]);
								break;	
							default :
								Logging.debug("Default switch case : FDETailsNSE");
								break;								
						}
						if(arr[i].equals(""))
						{
							buffer.append("<td align='center'><font color='white'> ");	
							buffer.append(".");
							buffer.append(" </font></td>");	
						}else
						{
							buffer.append("<td> ");	
							buffer.append(arr[i]);
							buffer.append(" </td>");	
						}		
						i++;
					}
				}
				if(arrlen==37)
				{
					while(i<arrlen)
					{
						Logging.debug("Inside while i is " + i + " value "+arr[i]);
						switch(i)
						{
						case	0: 	
							 FD.setSymbol(arr[i]);
							break;
						case	1:    
							FD.setSeries(arr[i]);
							break;
						case	2:
							FD.setNet_sales(arr[i]);
							break;
						case	3:
							FD.setOther_income(arr[i]);
							break;
						case	4:   
							FD.setGross_income(arr[i]);
							break;
						case	5:   
							FD.setIncrease_decrease_in_stock(arr[i]);
							break;
						case	6:
							FD.setConsumption_of_raw_material(arr[i]);
							break;
						case	7:
							FD.setStaff_cost(arr[i]);
							break;
						case	8:
							FD.setTotal_expenditure_excluding_other_expenditure(arr[i]);
							break;
						case	9:
							FD.setOther_expenditure(arr[i]);
							break;						
						case	10:
							FD.setTotal_expenditure(arr[i]);
							break;
						case	11:
							FD.setInterest(arr[i]);
							break;
						case	12:  
							FD.setProfit_loss_before_depriciation_tax(arr[i]);
							break;
						case	13:  
							FD.setDepreciation(arr[i]);
							break;
						case	14:
							FD.setProfit_loss_before_tax(arr[i]);
							break;
						case	15:
							FD.setProvision_for_taxation(arr[i]);
							break;
						case	16:  
							FD.setOther_provisions(arr[i]);
							break;
						case	17:
							FD.setMisc_expd_w_o(arr[i]);
							break;
						case	18:  
							FD.setNet_profit_loss(arr[i]);
							break;
						case	19: 
							FD.setNon_recurring_income(arr[i]);
							break;
						case	20:  
							FD.setNon_recurring_expenses(arr[i]);
							break;
						case	21:  
							FD.setAdjusted_net_profit_loss(arr[i]);
							break;
						case	23:
							FD.setPaid_up_equity_share_capital(arr[i]);
							break;
						case	24: 
							FD.setReserves_excluding_revaluation_reservers(arr[i]);
							break;
						case	25:  
							FD.setDividend(arr[i]);
							break;
						case 	26:
							FD.setBasic_eps(arr[i]);
							break;
						case	27:  
							FD.setDiluted_eps(arr[i]);
							break;
						case	30: 
							FD.setFrom_date(PopFileDialogNewStock.formatDate(arr[i]));
							break;
						case	31:
							Logging.debug("formatDate(arr[i]) "+arr[i]);
							FD.setTo_date(PopFileDialogNewStock.formatDate(arr[i]));
							Logging.debug("formatDate(arr[i]) "+arr[i]);
							break;
						case	32:						
							FD.setIs_Audited(arr[i]);
							Logging.debug("FD.setIs_Audited(arr[i]) "+arr[i]);
							break;
						case	33: 
							FD.setIs_Cumulative(arr[i]);
							break;
						case	34:  
							FD.setIs_Consolidated(arr[i]);
							break;
						case	35: 
							FD.setFin_year(arr[i]);
							break;
						case	36: 
							FD.setBook_value(arr[i]);
							break;					 					
						}
						if(arr[i].equals(""))
						{
							buffer.append("<td align='center'><font color='white'> ");	
							buffer.append(".");
							buffer.append(" </font></td>");	
						}else
						{
							buffer.append("<td> ");	
							buffer.append(arr[i]);
							buffer.append(" </td>");	
						}		
						i++;									
					}
				}	
				Logging.debug("Line "+i);	
				/*
				 * if exchange is not considered then there is 
				 * possiblity of more than 1 company getting selected
				 */
				String FDseries="";
				//FDseries=FD.getSeries();
				 
				if(FD.getSymbol()!=null || !(FD.getSymbol().equals(null))) 
					table.put(FD.getSymbol() + ":" + FD.getSeries(),FD);
				buffer.append("</tr>"); 				 
		 	}	 		
		}
	 	catch(Exception e)
		{
	 		Logging.error("Error : "+e.getMessage());
	 		return null;
		}
	 	Logging.debug("Inside FDR before return buffer ");
	 	return buffer;
	 }
	 public static StringBuffer StoreFDetail(String Exchangeid) //StringBuffer
	 {
	 	StringBuffer buffer=new StringBuffer();
	 	StringBuffer buffernew=new StringBuffer();
	 	String stock_id="";
	 	int inscounter=0;
	 	int updcounter=0;
	 	int unimpcounter=0;
	 	int counter1=0;
	 	Connect connect = ConnectInit.getConnect();
	 	Connection connection=null;
	 	try
		{	
			Logging.debug("inside try StoreFDetail");
			String str="";
			int i;
			Logging.debug("storeFD3 Before con");
			
			/*if(Connect.con == null){				
				Connection con = connect.getConnection();
			}*/
			try{
				if(connection==null)
					connection = connect.getConnectionForTransaction();
			}catch(Exception e) {
				Logging.error(" Error : "+e.getMessage());
			}
			PreparedStatement pst;	
			ResultSet result=null;				 
			Enumeration e = table.keys();
			int counter=0;
			String key="";
			String keyP1="";//part one is symbol
			String keyP2="";//part one is series
			for(e=table.keys();e.hasMoreElements();)			
			{
				counter1++;
				if(counter==5){						
					int a=result.CLOSE_CURSORS_AT_COMMIT;				
					connection.commit();
					connection.setAutoCommit(true);
					Logging.debug("counter after commit 5 IS "+counter1);					
					counter=0;					
				}	
				if(counter1%100==0){
					connection.commit();
					connection.close();
					//Logging.getDebug("connection after counter 100 is "+connect );
					try{
						try
						{
						if(connection==null)
							connection = connect.getConnectionForTransaction();
						}catch(Exception e2) {
							Logging.error(" Error : "+e2.getMessage());
						}
						Logging.debug("connection after counter 100 is "+connect );
						connection.rollback();
						Logging.debug("connection after counter 100 is "+connection);
						}catch(SQLException ex){
							connection.close();
							Logging.error("Error : Unable to get Transaction connection "+ex.getMessage());
						}
				}
				key = (String)e.nextElement();	
				//FinancialDetailReadFile FD=new FinancialDetailReadFile();
				FinancialDetailForm  FD = (FinancialDetailForm)table.get(key);
				try
				{		
					int pos=0;
					String company_id=null;
					pos=key.indexOf(":");
					if(pos==0)
					continue;
					keyP1=key.substring(0,pos);
					keyP2=key.substring(pos+1,key.length());
					if(keyP2.equals("null"))
						keyP2="";
					Logging.debug(" Exchangeid is "+Exchangeid+" keyP1 is "+keyP1);
					pst = connection.prepareStatement(ConnectInit.queries.getProperty("get_company_id_where_exchange_id_symbol_series"));
					pst.setString(1,Exchangeid);
					pst.setString(2,keyP1);
					//pst.setString(3,keyP2);
					result = pst.executeQuery();
					Logging.debug("get_company_id_where_exchange_id_symbol_series"+pst);
					if(result.next())
					{			
						Logging.debug("got company_id"+result.getString(1));
						company_id=result.getString(1);
						//check if the record already exists
						PreparedStatement pst1;	
						Statement stmt = connection.createStatement();
						ResultSet result1;
						String finyear="";
						finyear=FD.getFin_year();
						if(finyear==null)
						{
							pst1 = connection.prepareStatement(ConnectInit.queries.getProperty("check_if_fd_record_exist1"));
							pst1.setString(1,company_id );//company_id
							pst1.setString(2,FD.getFrom_date());//from_date
							pst1.setString(3,FD.getTo_date());//to_date							 
							result1 = pst1.executeQuery();
						}
						else
						{
							pst1 = connection.prepareStatement(ConnectInit.queries.getProperty("check_if_fd_record_exist"));
							pst1.setString(1,company_id );//company_id
							pst1.setString(2,FD.getFrom_date());//from_date
							pst1.setString(3,FD.getTo_date());//to_date
							pst1.setString(4,FD.getIs_Audited());//is_audited
							pst1.setString(5,FD.getIs_Cumulative());//is_cumulative
							pst1.setString(6,FD.getIs_Consolidated());//is_consolidated
							pst1.setString(7,FD.getFin_year());//fin_year
							result1 = pst1.executeQuery();
						}						
						Logging.debug("check_if_fd_record_exist: "+pst1);
						if(result1.next())
						 {							
							Logging.debug("Record Exist");
							buffer.append("<tr><td>");
						    buffer.append(keyP1);
						    buffer.append("</td>");
						    if(keyP2.equals(""))
						    	buffer.append("<td>" +"<font color=white>"+"--" +"</td>");
						    else
						    updcounter++;	
						    buffer.append("<td>" + keyP2 +"</td>");
						 	buffer.append("<td><font color='blue'>Record Already Exist</font></td></tr>");
						 	continue;
						 }
						 else
						 {
						 	Logging.debug("No Record Exist");
						 	ResultSet rs;
						 	rs = stmt.executeQuery("SELECT nextval('findetail_id')");
							rs.next();	
							String fdetail_id=rs.getString(1);
							Logging.debug("Got Next val"+rs.getString(1));
							rs.close();							
						 	PreparedStatement pst2;	
						    pst2=connection.prepareStatement(ConnectInit.queries.getProperty("insert_into_financial_detail"));						
						    pst2.setString(1,fdetail_id);//call fdetail_id 
							pst2.setString(2,company_id);//company_id
							pst2.setString(3,FD.getNet_sales());//  net_sales
							pst2.setString(4,FD.getOther_income());//other_income
							pst2.setString(5,FD.getGross_income());//  gross_income
							pst2.setString(6,FD.getIncrease_decrease_in_stock());//  increase_decrease_in_stock
							pst2.setString(7,FD.getConsumption_of_raw_material());//consumption_of_raw_materials
							pst2.setString(8,FD.getStaff_cost());//staff_cost
							pst2.setString(9,FD.getTotal_expenditure_excluding_other_expenditure());//total_expenditure_excluding_other_expenditure
							pst2.setString(10,FD.getOther_expenditure());//other_expenditure
							pst2.setString(11,FD.getTotal_expenditure());//total_expenditure
							pst2.setString(12,FD.getInterest());//interest
							pst2.setString(13,FD.getProfit_loss_before_depriciation_tax());//profit_lost_before_depreciation_taxes
							pst2.setString(14,FD.getDepreciation());//depreciation
							pst2.setString(15,FD.getProfit_loss_before_tax());//profit_lot_before_tax
							pst2.setString(16,FD.getProvision_for_taxation());//provision_for_taxation
							pst2.setString(17,FD.getOther_provisions());//other_provisions
							pst2.setString(18,FD.getMisc_expd_w_o());//misc_expd_w_o
							pst2.setString(19,FD.getNet_profit_loss());//net_profit_loss
							pst2.setString(20,FD.getNon_recurring_income());//non_recurring_income
							pst2.setString(21,FD.getNon_recurring_expenses());//non_recurring_expenses
							pst2.setString(22,FD.getAdjusted_net_profit_loss());//adjusted_net_profit_loss
							pst2.setString(23,FD.getPaid_up_equity_share_capital());//paid_up_equity_share_capital 
							pst2.setString(24,FD.getReserves_excluding_revaluation_reservers());//reserves_excluding_revaluation_reserves 
							pst2.setString(25,FD.getDividend());//dividend 
							pst2.setString(26,FD.getBasic_eps());//basic_eps
							pst2.setString(27,FD.getDiluted_eps());//diluted_eps
							pst2.setString(28,FD.getFrom_date());//from_date
							pst2.setString(29,FD.getTo_date());//to_date
							pst2.setString(30,FD.getIs_Audited());//is_audited
							pst2.setString(31,FD.getIs_Cumulative());//is_cumulative
							pst2.setString(32,FD.getIs_Consolidated());//is_consolidated 
							pst2.setString(33,FD.getFin_year());//fin_year
							pst2.setString(34,FD.getBook_value());//book_value
							Logging.debug("pst2 "+pst2);
							pst2.execute();
							inscounter++;
							Logging.debug("Insert into financial details"+pst2);
							Logging.debug("keys are "+keyP1 + " "+keyP2);
						    buffer.append("<tr><td>");
						    buffer.append(keyP1);
						    buffer.append("</td><td>");
						    buffer.append(keyP2);
						    buffer.append("</td><td>Financial Details Saved</td></tr>");	
						  }				
					}
					else{	
						unimpcounter++;
						buffer.append("<tr><td>");
					    buffer.append(keyP1);
					    buffer.append("</td><td>");
					    buffer.append(keyP2);
					    buffer.append("</td><td><font color='blue'>Company Not Found</font></td></tr>");
						continue;
					}
				}catch(Exception ex){
					Logging.error("Error : "+ex.getMessage());
				}			
			}			
			
		table.clear();
		
		buffernew.append("<br><tr><font color=Blue><td>Values Inserted :</td><td>");
	    buffernew.append(inscounter);
	    buffernew.append("</td></font></tr>");
		buffernew.append("<br><tr><font color=Blue><td>Record Already Exist:</td><td>");
	    buffernew.append(updcounter);
	    buffernew.append("</td></font></tr>");
	    buffernew.append("<br><tr><font color=Blue><td>Company Not Found:</td><td>");
	    buffernew.append(unimpcounter);
	    buffernew.append("<br><tr><font color=Blue><td>Total Rows :</td><td>");
	    buffernew.append(counter1);
	    buffernew.append("</td></font></tr>");
	    buffernew.append(buffer);
	    buffer=null;
		}catch(Exception e)
		{
			Logging.error("Error : "+e.getMessage());
		}
		finally{
			try{
				if(connection!=null)
					connection.close();
			}catch(Exception ex){
				Logging.error(" Error : Unable to close connection "+ex.getMessage());
			}
		}
		//Logging.getDebug("sending buffer count is "+count);
		return buffernew;
	} 
	  
}
