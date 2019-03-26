/*
 * Created on Jun 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import java.io.BufferedReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.jfree.chart.demo.servlet.ComposeIndex;

import com.harrier.initializeation.ConnectInit;

/**
 * @author sunil
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EpsReadFile {
	static Logger Logging = Logger.getLogger(EpsReadFile.class);
	public static Hashtable table1 = new Hashtable();
	Connection con=null;
	boolean improperFormat=true;
	 public static StringBuffer getHashnBuffer(StringBuffer buffer,BufferedReader br)
	 {
	 	Logging.debug("INside EpsReadFile");
	 	String str;
	 	try
		{
	 		Logging.debug("Inside EpsReadFile try");
	 		String[] arr ;	
	 		int i,count=-1;
	 		while((str=br.readLine())!=null )
	 		{
	 			count++;
	 			buffer.append("<tr>");
	 			arr= str.split(",");
				i=0;
				if(arr.length==0) continue;
				EPSDetailForm   FD=new EPSDetailForm();
				int arrlen=arr.length;
				Logging.debug("Inside EpsReadFile after new PEDetailForm();  "+arrlen);
				if(arrlen!=12){
					buffer.append("<font size='2' face='Arial' color='Red'><tr>File with improper format </tr></font>");
					return buffer;
				}
				if(arrlen==12)
				{
					Logging.debug("Inside eps file");
					String time=null,date=null;
					while(i<arrlen)
					{						
						switch(i)
						{
							case 	0:
								FD.setCompany(arr[i]);
								break;
							case 	1:
								FD.setBSE_demat_code(arr[i]);
							case 	2:
								 FD.setYear(arr[i]);
							case 	3:
								FD.setNo_of_months(arr[i]);
								break;
							case 	4:
								FD.setPat(arr[i]);
								break;	
							case 	5:
								FD.setNet_worth(arr[i]);
								break;
							case 	6:
								FD.setInterest_payment(arr[i]);
							case 	7:
								 FD.setTax_provisions(arr[i]);
							case 	8:
								FD.setIssued_capital(arr[i]);
								break;
							case 	9:
								FD.setBorrowing(arr[i]);
								break;	
							case 	10:
								 FD.setSales(arr[i]);
							case 	11:
								FD.setEquity_divident(arr[i]);
								break;							
							default :
								Logging.debug("Default switch case : EPSDetail");
								break;								
						}
						if(arr[i].equals("") || arr[i]==null )
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
				/*
				 * if exchange is not considered then there is 
				 * possiblity of more than 1 company getting selected
				 */
				String FDseries="";
				//FDseries=FD.getSeries();
				 
				if(FD.getCompany()!=null || !(FD.getCompany().equals(null))) 
					table1.put(FD.getCompany(),FD);
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
	 //	method added as per Enhancement in Stockpile for next Version by Ashishi 10-07-2006
	 /**
	  * method returning String buffer after reading EPS .text file.
	  * @param buffer
	  * @param br
	  * @return
	  */
	 public static StringBuffer getHashnBuffer_Text(StringBuffer buffer,BufferedReader br)
	 {
	 	Logging.debug("INside EpsReadFile");
	 	String str;
	 	int blankCounter=0,lineCounter=0,keyCounter=0;
	 	try
		{
	 		//Logging.debug("Inside EpsReadFile try");
	 		String[] arr ;	
	 		int i,count=-1;
	 		while((str=br.readLine())!=null ){
	 			lineCounter++;
	 			count++;
	 			buffer.append("<tr>");
	 			arr= str.split("\t");
				i=0;
				if(arr.length==0){
					blankCounter++;
					continue;
				}
				EPSDetailForm   FD=new EPSDetailForm();
				int arrlen=arr.length;
				//Logging.getDebug("Inside EpsReadFile after new PEDetailForm();  "+arrlen);
				if(arrlen!=12){
					Logging.debug("Improper file format "+str);
					if(arrlen >0 )
						continue;
					buffer.append("<font size='2' face='Arial' color='Red'><tr>File with improper format </tr></font>");
					return buffer;					
				}
				if(arrlen==12)
				{
					//Logging.getDebug("Inside eps file");
					String time=null,date=null;
					while(i<arrlen)
					{						
						switch(i)
						{
							case 	0:
								FD.setCompany(arr[i]);
								break;
							case 	1:
								FD.setBSE_demat_code(arr[i]);
							case 	2:
								 FD.setYear(arr[i]);
							case 	3:
								FD.setNo_of_months(arr[i]);
								break;
							case 	4:
								FD.setPat(arr[i]);
								break;	
							case 	5:
								FD.setNet_worth(arr[i]);
								break;
							case 	6:
								FD.setInterest_payment(arr[i]);
							case 	7:
								 FD.setTax_provisions(arr[i]);
							case 	8:
								FD.setIssued_capital(arr[i]);
								break;
							case 	9:
								FD.setBorrowing(arr[i]);
								break;	
							case 	10:
								 FD.setSales(arr[i]);
							case 	11:
								FD.setEquity_divident(arr[i]);
								break;							
							default :
								//Logging.getDebug("Default switch case : EPSDetail");
								break;								
						}
						if(arr[i].equals("") || arr[i]==null )
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
				/*
				 * if exchange is not considered then there is 
				 * possiblity of more than 1 company getting selected
				 */
				String FDseries="";
				//FDseries=FD.getSeries();				 
				if(FD.getCompany()!=null || !(FD.getCompany().equals(null))){ 
					table1.put(FD.getCompany(),FD);
				}else
					blankCounter++;
				buffer.append("</tr>"); 				 
		 	}	 			 		
		}catch(Exception e){
	 		Logging.error("Error : "+e.getMessage());
	 		return null;
		}finally{
			//Logging.getDebug("Inside FDR before return buffer ");
			Logging.debug("Key counter is "+table1.size());
			Logging.debug("line counter is "+ lineCounter +" Key counter is "+table1.size());
		}
	 	return buffer;
	 }
	////////////////////////naresh new epsdetail method
	 public static StringBuffer StoreEPSDetail22222222(String Exchangeid) //StringBuffer
	 {
	 	StringBuffer buffer=new StringBuffer();
	 	StringBuffer buffernew=new StringBuffer();
	 	String stock_id="";	 	
	 	Connection connection=null;
	 	PreparedStatement pst=null;	
		ResultSet result=null;
		CallableStatement stmt=null;
		//ResultSet boolRs=null;
	 	try
		{	
	 		
			String str="";
			int i;
			
			
			Connect connect = ConnectInit.getConnect();
			try{
				if(connection!=null)
					connection.close();
			connection = connect.getConnectionForTransaction();
			Logging.debug("connection is before rollback() "+connect );
			connection.rollback();
			Logging.debug("connection is after rollback()"+Connect.con);
			}catch(SQLException ex){
				Logging.error("Error : Unable to get Transaction connection "+ex.getMessage());
			}	
			Enumeration e = table1.keys();
			int counter=0;
			int insUpdCounter=0,cNotFoundCounter=0;
			String key="";			
			String getCompanyIdQuery=ConnectInit.queries.getProperty("importFile_get_company_id_eps_file");
			String getFDetailIdQuery=ConnectInit.queries.getProperty("importFile_get_fdetail_id");
			String updFDetailQuery=ConnectInit.queries.getProperty("importFile_update_into_financial_detail");
			String insertCimmData=ConnectInit.queries.getProperty("insert_into_financial_detail");
			String getDatesForInsertEPS=ConnectInit.queries.getProperty("get_dates_for_insertEPSData");
			String cimm_xdate="";
			String cimm_fromdate="";
			String cimm_year="";
			String cimm_values="";			
			int cimm_id=0;
			String no_of_months="";
		    String company_id=null;
			int pos=0;
			String year="";
			String fromDate="",toDate="";
			String month="";
			String day="";
			String fdate="";
			String month1="";
			String tdate="";
			int counter1=0;
			int updcounter=0;
			String demat_code=null,noofmonths=null,net_worth=null,equity_divident=null,
				interest_payment=null,tax_payment=null,issued_capital=null,borrowing=null,sales=null,
				pat=null;
			
			for(e=table1.keys();e.hasMoreElements();)			
			{									
				counter++;
				counter1++;
				/*if(counter>5)
					return buffer;*/
				key = (String)e.nextElement();	
				//FinancialDetailReadFile FD=new FinancialDetailReadFile();
				EPSDetailForm  FD = (EPSDetailForm)table1.get(key);
				try
				{		
					company_id=null;
					pos=0;
					Logging.debug("Counter is "+counter1);
					if(counter==5){						
						int a=result.CLOSE_CURSORS_AT_COMMIT;				
						connection.commit();
						connection.setAutoCommit(true);
						//Logging.getDebug("counter after commit 5 IS "+counter1);					
						counter=0;					
					}	
					if(counter1%100==0)
					{
						connection.commit();
						connection.close();
						try{
							if(connection==null)
								connection.close();
							connection = connect.getConnectionForTransaction();
							Logging.debug("connection after counter 100 is "+connect );
							connection.rollback();
							Logging.debug("connection after counter 100 is "+Connect.con);
							}catch(SQLException ex){
								if(connection!=null){
									connection.rollback();
									connection.close();
								}
								Logging.error("Error : Unable to get Transaction connection "+ex.getMessage());
							}
					}
					/*if(pst!=null)
						pst.close();
					pst = Connect.con.prepareStatement(getCompanyIdQuery);						
					pst.setString(1,key.trim());	
					if(result!=null)
						result.close();
					result = pst.executeQuery();
					Logging.getDebug("get_company_id_where_exchange_id_symbol_series"+result);
					if(!(result.next()))
					{				
						buffer.append("<tr><td>");
					    buffer.append(key);					   
					    buffer.append("</td><td><font color='blue'>Company Not Found</font></td></tr>");
						continue;
					}		
					Logging.getDebug("got company_id"+result.getString(1));
					company_id=result.getString(1);						
					pst.close();
					result.close();*/
					/*Logging.getDebug("got company_id"+result.getString(1));
					company_id=result.getString(1);*/
					/*check if the record already exists
					 * If yes then update the existing record
					 * else enter new record
					 */	
					
					year=FD.getYear();
					no_of_months=FD.getNo_of_months();						
					if(pst!=null)
						pst.close();
					pst = connection.prepareStatement(getDatesForInsertEPS);
					pst.setString(1,year);//
					pst.setString(2,year);//
					int nmonth=(int)Integer.parseInt(no_of_months);
					pst.setInt(3,nmonth-1);//
					pst.setString(4,year);//	
					pst.setString(5,year);//
					if(result!=null)
						result.close();
					result = pst.executeQuery();
					if(result.next()){
						if(result.getString(1)!=null)
							fromDate=result.getString(1).trim();
						toDate=result.getString(2).trim();	
					}
					if(result!=null)
						result.close();
					if(pst!=null)
						pst.close();
					//Logging.getDebug("year length is "+year.length());						
					month=year.substring(4);
					/*if(nmonth!=12 && nmonth>12){
						nmonth=nmonth-12;
					}*/
					/* USE THIS INSIDE PROCEDURE
					 * SELECT LAST_DAY(TO_CHAR(TO_DATE('01-'||(SUBSTR('200403',5,2)) || '-'|| (SUBSTR('200403',1,4)),'DD-MM-YYYY'),'DD-MON-YYYY')) FROM DUAL;
					 */
	//naresh				//day=ComposeIndex.getLastDayOfMonth(year);
					month1=new Integer(Integer.parseInt(month)+1).toString();
					if(month1.length()==1)
						month1="0"+month1;
					if(fromDate.equals("")){							
						fdate="01-"+month1+"-"+(Integer.parseInt(year.substring(0,4))-1);
						fdate=ComposeIndex.FormatDateMon(fdate);
					}else
						fdate=fromDate;
					if(toDate.equals("")){
						tdate=day+"-"+ month+"-"+year.substring(0,4);
						tdate=ComposeIndex.FormatDateMon(tdate);
					}else
						tdate=toDate;		
					//stmt = connection.prepareCall("BEGIN PROC_INSERTEPSDATA_new(?,?,?,?,?,?,?,?,?,?,?,?,?);END;");
					stmt = connection.prepareCall("{?=call INSERTEPSDATA_new(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
					// //REF CURSOR ("(?=call PROC_INSERTEPSDATA_new(?)}")
					stmt.registerOutParameter(1,Types.VARCHAR);
					
					// set input parameters.
					stmt.setString(2,fdate);
					stmt.setString(3,tdate);
					demat_code=FD.getBSE_demat_code();
					//if(demat_code.equals("509930"))
						//Logging.getDebug("Hello");
					if((demat_code==null)|| demat_code.equals("")){
						cNotFoundCounter++;
						buffer.append("<tr><td>");
						buffer.append(key);
						buffer.append("</td><font color='Red'><td>Company not found</td></font></tr>");
						continue;
					}
					noofmonths=FD.getNo_of_months();
					if((noofmonths==null)|| (noofmonths.equals("")) || (noofmonths.equals("0"))){
						cNotFoundCounter++;
						buffer.append("<tr><td>");
						buffer.append(key);
						buffer.append("</td><font color='Red'><td>Months not found</td></font></tr>");
						continue;
					}					
					net_worth=FD.getNet_worth();
					if((net_worth==null)|| (net_worth.equals(""))){
						net_worth="0";
					}
					equity_divident=FD.getEquity_divident();
					if((equity_divident==null)|| (equity_divident.equals(""))){
						equity_divident="0";
					}
					interest_payment=FD.getInterest_payment();
					if((interest_payment==null)|| (interest_payment.equals(""))){
						interest_payment="0";
					}
					tax_payment=FD.getTax_provisions();
					if((tax_payment==null)|| (tax_payment.equals(""))){
						tax_payment="0";
					}
					issued_capital=FD.getIssued_capital();
					if((issued_capital==null)|| (issued_capital.equals(""))){
						issued_capital="0";
					}
					borrowing=FD.getBorrowing();
					if((borrowing==null)|| (borrowing.equals(""))){
						borrowing="0";
					}
					sales=FD.getSales();
					if((sales==null)|| (sales.equals(""))){
						sales="0";
					}
					pat=FD.getPat();
					if((pat==null)|| (pat.equals(""))){
						pat="0";
					}					
			
					/*}else{							
						cNotFoundCounter++;
						buffer.append("<tr><td>");
					    buffer.append(key);
					    buffer.append("</td><font color='blue'><td>Company not found</td></font></tr>");
					}*/
					result.close();
					stmt.close();
					/*pst = connection.prepareStatement(getFDetailIdQuery);
					pst.setInt(1,cimm_id);//from_date
					pst.setString(2,company_id);//to_date	
					pst.setString(3,fdate);//from_date
					pst.setString(4,tdate);//to_date							
					result = pst.executeQuery();
					
					for(int id=2;id<8;id++){
						cimm_id=id;
						if(id==1)
							continue;
						switch(id){
						case 2:
							cimm_values=FD.getNet_worth();
							break;
						case 3:
							cimm_values=FD.getEquity_divident();
							break;
						case 4:
							cimm_values=FD.getInterest_payment();
							break;
						case 5:
							cimm_values=FD.getTax_provisions();
							break;
						case 6:
							cimm_values=FD.getIssued_capital();
							break;
						case 7:
							cimm_values=FD.getBorrowing();
							break;
						case 8:
							cimm_values=FD.getSales();
							break;
						}*/
						/*if(pst!=null)
							pst.close();
						pst = connection.prepareStatement(getFDetailIdQuery);
						pst.setInt(1,cimm_id);//import type id
						pst.setString(2,company_id);//crisil code	
						pst.setString(3,fdate);//from_date
						pst.setString(4,tdate);//cimm_xdate
						if(result!=null)
							result.close();
						result = pst.executeQuery();							
						if(result.next()){
							//update if 
						}else{
							//insert new record
							pst.close();																
							//(cimm_id,cimm_xdate,crsl_code,cimm_year,cimm_value,cimm_fromdate)
							pst = connection.prepareStatement(insertCimmData);
							pst.setInt(1,cimm_id);
							pst.setString(2,tdate);
							pst.setString(3,company_id);
							pst.setString(4,no_of_months);
							pst.setString(5,cimm_values);
							pst.setString(6,fdate);
							pst.executeUpdate();
							pst.close();
						}			*/				
						//}
						
						/*if(!(result.next()))
						{
							continue;
						}
						String fdetail_id=null;
						fdetail_id=result.getString(1);
						if((fdetail_id==null) || (fdetail_id.equals("")))
							continue;
						Logging.getDebug("Got Next val"+fdetail_id);
						result.close();
						pst.close();
					    pst=connection.prepareStatement(updFDetailQuery);						
					    pst.setString(1,FD.getPat());//pat(profit after tax)
					    pst.setString(2,FD.getNet_worth());//net_worth								
						pst.setString(3,FD.getInterest_payment());//interest
						pst.setString(4,FD.getTax_provisions());//provision_for_taxation
						pst.setString(5,FD.getIssued_capital());//paid_up_equity_share_capital 
						pst.setString(6,FD.getBorrowing());//borrowings
						pst.setString(7,FD.getSales());//  net_sales
						pst.setString(8,FD.getEquity_divident());//dividend 
						pst.setString(9,"n");//adjusted flag
						pst.setString(10,fdetail_id);//fdetail_id 
						pst.setString(11,company_id);//company_id							
						Logging.getDebug("pst2 "+pst);
						pst.execute();*/
						//Logging.getDebug("Insert into financial details"+pst);
						//Logging.getDebug("keys are "+key);
					    			
					    
				}catch(Exception ex){
					Logging.error("Error : "+ex.getMessage());
				}finally{
					try{
						if(result!=null)
							result.close();
						if(pst!=null)
							pst.close();
						if(stmt!=null)
							stmt.close();						
					}catch(SQLException ex){
						if(connection!=null){
							connection.rollback();
							connection.close();
						}
						Logging.error("Error :  "+ex.getMessage());
					}
				}
			}
			buffernew.append("<br><tr><font color=Blue><td>Values Inserted :</td><td>");
		    buffernew.append(insUpdCounter);
		    buffernew.append("</td></font></tr>");
		    buffernew.append("<br><tr><font color=Blue><td>Values Updated :</td><td>");
		    buffernew.append(updcounter);
		    buffernew.append("</td></font></tr>");
		    buffernew.append("<br><tr><font color=Red><td>Company Not found :</td><td>");
		    buffernew.append(cNotFoundCounter);
		    buffernew.append("</td></font></tr>");
		    buffernew.append("<br><tr><font color=BLACK><td>Total rows:</td><td>");
		    buffernew.append(counter1);
		    buffernew.append("</td></font></tr>");
		    buffernew.append(buffer);
		    buffer=null;
			//Logging.getDebug("Key counter is : "+ counter + "InsertUpdate counter is  : "+insUpdCounter);
		}catch(Exception e){
			Logging.error("Error : "+e.getMessage());
		}finally{
			try{
				if(result!=null)
					result.close();
				if(pst!=null)
					pst.close();
				if(stmt!=null)
					stmt.close();
				if(connection!=null){
					connection.rollback();
					connection.close();
				}		
			}catch(SQLException ex){
				
				Logging.error("Error : "+ex.getMessage());
			}
		}
		table1.clear();
		Logging.debug("sending buffer");
		
		return buffernew;
	} 
	 
	 
	 
	////////////////////////////////////////////////////////////////// 
	 public static StringBuffer StoreEPSDetail(String Exchangeid) //StringBuffer
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
	 		Logging.debug("inside try StoreEPSDetail");
			String str="";
			int i;
			Logging.debug("storeFD3 Before con");
			/*Connect connect = ConnectInit.getConnect();
			if(Connect.con == null){				
				Connection con = connect.getConnection();
			}*/
			try{
				if(connection==null)
					connection = connect.getConnectionForTransaction();
					//getdbConnection();
					
			}catch(Exception e) {
				Logging.error(" Error : "+e.getMessage());
			}
			PreparedStatement pst;	
			ResultSet result=null;				 
			Enumeration e = table1.keys();
			int counter=0;			
			String key="";
			String cimm_values="";			
			int cimm_id=0;
			String no_of_months="";
		 	String insertCimmData=ConnectInit.queries.getProperty("insert_into_financial_detail");
		 	String getFDetailIdQuery=ConnectInit.queries.getProperty("importFile_get_fdetail_id_new");
		 	int n=0;
		
			for(e=table1.keys();e.hasMoreElements();e.nextElement())	
					//naresh///////  e=table1.keys();e.hasMoreElements();)			
			{	
				
				counter1++;
				/*if(counter>5)
					return buffer;*/
				if(counter==5){						
					int a=result.CLOSE_CURSORS_AT_COMMIT;				
					connection.commit();
					connection.setAutoCommit(true);
					Logging.debug("counter after commit 5 IS "+counter1);					
					counter=0;					
				}	
				/*if(counter1%100==0){
					connection.commit();
					connection.close();
					//Logging.getDebug("connection after counter 100 is "+connect );
					try{
						try
						{
						if(connection==null)
							connection = connect.getConnectionForTransaction();
						}catch(Exception e2) {
							Logging.getError(" Error : "+e2.getMessage());
						}
						Logging.getDebug("connection after counter 100 is "+connect );
						connection.rollback();
						Logging.getDebug("connection after counter 100 is "+connection);
						}catch(SQLException ex){
							connection.close();
							Logging.getError("Error : Unable to get Transaction connection "+ex.getMessage());
						}
				}*/
				key = (String)e.nextElement();	
				//FinancialDetailReadFile FD=new FinancialDetailReadFile();
				EPSDetailForm  FD = (EPSDetailForm)table1.get(key);
				try
				{		
					int pos=0;
					String company_id=null;						
						Logging.debug(" Exchangeid is "+Exchangeid+" key is "+key);
						pst = connection.prepareStatement(ConnectInit.queries.getProperty("importFile_get_company_id_eps_file"));
						//pst.setString(1,Exchangeid);
						pst.setString(1,key);
						//pst.setString(3,keyP2);
						result = pst.executeQuery();
						Logging.debug("get_company_id_where_exchange_id_symbol_series"+result);
						if(result.next())
						{			
							Logging.debug("got company_id"+result.getString(1));
							company_id=result.getString(1);
							//check if the record already exists
							PreparedStatement pst1=null;	
							Statement stmt = connection.createStatement();
							ResultSet result1=null;
							String year=FD.getYear();
							Logging.debug("year length is "+year.length());
							String month=year.substring(4);
							String tdate="01-"+month+"-"+year.substring(0,4);
							String fdate="01-"+month+"-"+(Integer.parseInt(year.substring(0,4))-1);
							Logging.debug("fdate is "+fdate+" tdate is "+tdate);
							/////////////////naresh///////////////
							if(pst!=null)
							pst.close();
							pst = connection.prepareStatement(getFDetailIdQuery);
							pst.setInt(1,cimm_id);//from_date
							pst.setString(2,company_id);//to_date	
							pst.setString(3,fdate);//from_date
							pst.setString(4,tdate);//to_date							
							result = pst.executeQuery();
							if(result.next()){
								//update if 
							}else{
							for(int id=2;id<8;id++){
								cimm_id=id;
								if(id==1)
									continue;
								switch(id){
								case 2:
									cimm_values=FD.getNet_worth();
									break;
								case 3:
									cimm_values=FD.getEquity_divident();
									break;
								case 4:
									cimm_values=FD.getInterest_payment();
									break;
								case 5:
									cimm_values=FD.getTax_provisions();
									break;
								case 6:
									cimm_values=FD.getIssued_capital();
									break;
								case 7:
									cimm_values=FD.getBorrowing();
									break;
								case 8:
									cimm_values=FD.getSales();
									break;
								}
														
								
									//insert new record
									pst.close();																
									//(cimm_id,cimm_xdate,crsl_code,cimm_year,cimm_value,cimm_fromdate)
									pst = connection.prepareStatement(insertCimmData);
									pst.setInt(1,cimm_id);
									pst.setString(2,tdate);
									pst.setString(3,company_id);
									pst.setInt(4,12);//naresh
									pst.setDouble(5,Double.parseDouble(cimm_values));
									pst.setString(6,fdate);
									pst.executeUpdate();
									pst.close();
										
								}	
							inscounter++;
						    buffer.append("<tr><td>");
						    buffer.append(key);
						    buffer.append("</td><td>Financial Details Saved</td></tr>");
							/////////////////////////////////////////////
															 		
						
						
							  }				
						}else{	
							updcounter++;
							buffer.append("<tr><td>");
						    buffer.append(key);					   
						    buffer.append("</td><td><font color='blue'>Company Not Found</font></td></tr>");
							continue;
						}					
				}catch(Exception ex){
					Logging.error("Error : "+ex.getMessage());
				}			
		
			}		
		
		
		table1.clear();
		
		buffernew.append("<br><tr><font color=Blue><td>Values Inserted :</td><td>");
	    buffernew.append(inscounter);
	    buffernew.append("</td></font></tr>");
		buffernew.append("<br><tr><font color=Blue><td>Company Not Found :</td><td>");
	    buffernew.append(updcounter);
	    buffernew.append("</td></font></tr>");
	    buffernew.append("<br><tr><font color=Blue><td>Total Rows :</td><td>");
	    buffernew.append(counter1);
	    buffernew.append("</td></font></tr>");
	    buffernew.append(buffer);
	    Logging.debug("The buffer is sis"+buffer);
	    buffer=null;
			
			}catch(Exception e)
		{
			Logging.error("Error naresh : "+e.getMessage());
		}
		finally{
			try{
				if(connection!=null)
					connection.close();
			}catch(Exception ex){
				Logging.error(" Error : naresh Unable to close connection "+ex.getMessage());
			}
		}
		Logging.debug("sending buffer naresh is "+buffernew);
		
		return buffernew;
	} 
	 /**
	  * to get date formatted in date format "dd-MM-yyyy"
	  * @param date
	  * @return
	  */
	 public static String formatDate(String date)
	 {
	 	//Logging.getDebug("before date "+date);
	 	java.util.Date d = new java.util.Date(date.trim());
//	 	Logging.debug("After date");
	 	SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
	 	//Logging.getDebug("After Simpledate"+fr.format(d).toString());
	 	return fr.format(d).toString();	 	
	 }
	 public static boolean checkForDateQuarterly(String date,String date1,String consolidate1){
	 	boolean flag=false;
	 	try{
		 	Logging.debug(" current  date is "+date+" current  date1 is "+date1);
			int m1=new Integer(date.trim().substring(3,5)).intValue();
			int m2=new Integer(date1.trim().substring(3,5)).intValue();
			Logging.debug("m1 is "+m1+" m2 is "+m2+" diff is "+Math.abs(m1-m2));
			if((Math.abs(m1-m2)==2) && (consolidate1.trim().equals("Consolidated")))
				flag=true;					 	
	 	}catch(Exception e){
	 		Logging.debug(" Error : "+e.getMessage());
	 	}
	 	return flag;
	 }
}
