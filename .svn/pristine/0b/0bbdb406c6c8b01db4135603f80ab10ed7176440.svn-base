/*
 * Created on Jun 21, 2005
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
public class PEDailyReadFile {
	static Logger Logging = Logger.getLogger(PEDailyReadFile.class);
	public static Hashtable table1 = new Hashtable();
	public static Connection connection;
	Connection con=null;
	boolean improperFormat=true;
	 public static StringBuffer getHashnBuffer(StringBuffer buffer,BufferedReader br)
	 {
	 	Logging.debug("INside UpdateSeriesReadFile");
	 	String str;
	 	try
		{
	 		Logging.debug("Inside FDR try");
	 		String[] arr ;	
	 		int i,count=-1;
	 		while((str=br.readLine())!=null )
	 		{
	 			count++;
	 			buffer.append("<tr>");
	 			arr= str.split(",");
				i=0;
				if(arr.length==0) continue;
				PEDetailForm   FD=new PEDetailForm();
				int arrlen=arr.length;
				Logging.debug("Inside FDR after new PEDetailForm();  "+arrlen);
				if(arrlen!=5){
					buffer.append("<font size='2' face='Arial' color='Red'><tr>File with improper format </tr></font>");
					return buffer;
				}
				if(arrlen==5)
				{
					Logging.debug("Inside update series");
					String time=null,date=null;
					while(i<arrlen)
					{						
						switch(i)
						{
							case 	0:
								FD.setSymbol(arr[i]);
								break;
							case 	1:
								 time=arr[i];
								 if(count!=0){							
									date=formatDate(time);
									FD.setStart_date(date);
								 }else{
									FD.setStart_date(time);
								 }								
								 break;
							case 	2:
								time=arr[i];									
								if(count!=0){							
									date=formatDate(time);
									FD.setEnd_date(date);
								}else{
									FD.setEnd_date(time);
								}
								break;
							case 	3:
								FD.setNet_profit(arr[i]);
								break;
							case 	4:
								FD.setCons_ncons(arr[i]);
								break;							
							default :
								Logging.debug("Default switch case : PEDetail");
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
				//String FDseries="";
				//FDseries=FD.getSeries();
				 
				if(FD.getSymbol()!=null || !(FD.getSymbol().equals(""))) {
					String keype=FD.getSymbol()+"/"+FD.getStart_date()+"/"+FD.getCons_ncons();
					table1.put(keype,FD);
				}
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
	  * method returning String buffer after reading PE .text file.
	  * @param buffer
	  * @param br
	  * @return
	  */
	 public static StringBuffer getHashnBuffer_Text(StringBuffer buffer,BufferedReader br)
	 {
	 	Logging.debug("INside UpdateSeriesReadFile");
	 	String str;
	 	try
		{	 		
	 		String[] arr ;	
	 		int i,count=-1;
	 		int rowCounter=0,keyCounter=0,blankCounter=0;
	 		while((str=br.readLine())!=null)
	 		{
	 			count++;
	 			rowCounter++;
	 			buffer.append("<tr>");
	 			arr= str.split("\t");
				i=0;
				if(arr.length==0){
					blankCounter++;
					continue;
				}
				PEDetailForm   FD=new PEDetailForm();
				int arrlen=arr.length;
				Logging.debug("Inside FDR after new PEDetailForm();  "+arrlen);
				if(arrlen!=5){
					buffer.append("<font size='2' face='Arial' color='Red'><tr>File with improper format </tr></font>");
					return buffer;
				}
				if(arrlen==5)
				{
					Logging.debug("Inside update series");
					String time=null,date=null;
					while(i<arrlen)
					{						
						switch(i)
						{
							case 	0:
								FD.setSymbol(arr[i]);
								break;
							case 	1:
								 time=arr[i];
								 date=formatDate(time);
								 FD.setStart_date(date);
								 break;
							case 	2:
								time=arr[i];									
								date=formatDate(time);
								FD.setEnd_date(date);
								break;
							case 	3:
								FD.setNet_profit(arr[i]);
								break;
							case 	4:
								FD.setCons_ncons(arr[i]);
								break;							
							default :
								Logging.debug("Default switch case : PEDetail");
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
				//String FDseries="";
				//FDseries=FD.getSeries();
				 
				if(FD.getSymbol()!=null || !(FD.getSymbol().trim().equals(""))) {
					String keype=FD.getSymbol()+"/"+FD.getStart_date()+"/"+FD.getEnd_date()+"/"+FD.getCons_ncons();
					table1.put(keype,FD);
					keyCounter++;
				}
				buffer.append("</tr>"); 				 
		 	}	
	 		Logging.debug("Key counter is  "+ keyCounter +" table size is " +table1.size());
		}
	 	catch(Exception e)
		{
	 		Logging.error("Error : "+e.getMessage());
//	 		return null;
		}
	 	Logging.debug("Inside FDR before return buffer ");
	 	return buffer;
	 }
//////////////////////////////////////////////naresh//////////////	
	 public static boolean updPEData(String cimmXDate,String crslCode,String cimmYear,String cimmValue,String cimmFromDate,Connection connection){
	 	boolean retVal=false;	 	
	 	PreparedStatement pst=null;
	 	try{
			Connect connect1=ConnectInit.getConnect();
			String qry=ConnectInit.queries.getProperty("update_cimm_data");
			pst = connection.prepareStatement(qry);
			pst.setDouble(1, (Double.parseDouble(cimmValue))/10000000);
			pst.setString(2,cimmYear);
			pst.setString(3, "1");
			pst.setString(4, crslCode);
			pst.setString(5, cimmXDate);
			pst.setString(6, cimmFromDate);	
			pst.executeUpdate();
			pst.close();
			Logging.debug("cimm_year isisisis"+cimmYear);
			Logging.debug("PE Data updated sucessfully ");
			retVal=true;
		}catch(Exception e){
			Logging.error("Error : "+e.getMessage());
		}finally{
			try{
				if(pst!=null)
					pst.close();				
			}catch(Exception ex){
				Logging.error("Error : Unable to close PreparedStatment :"+ex.getMessage());
			}		
		}
	 	return retVal;
	 }	 
	 public static StringBuffer StorePEDetail111(String Exchangeid) //StringBuffer
	 {
	 	StringBuffer buffer=new StringBuffer();
	 	StringBuffer buffernew=new StringBuffer();
	 	String stock_id="";
	 	Connect connect = ConnectInit.getConnect();
	 	try
		{	
	 		try {
				try {
					Logging.debug("connection is "+connection);
			
					Logging.debug("connection is "+connection);
					connection=null;
					connection = connect.getConnectionForTransaction();
					Logging.debug("connection is "+connection);
					connection.rollback();
			
				} catch (Exception e) {
			
					Logging.error(" Error :"+e.getMessage());
				}				

			} catch (Exception e) {
				Logging.debug("database commited ");
			
			}
	 		Logging.debug("inside try StorePEDetail");
			String str="";
			int i;
			Logging.debug("storeFD3 Before con");
						
			PreparedStatement pst=null;;	
			ResultSet result=null;		
			
			Enumeration e = table1.keys();
			int counter=0;
			int checkdateq_qauterly=0;
			String key="";	
				
		    String insertQuery=ConnectInit.queries.getProperty("insert_into_financial_detail");
		    
		    String checkQuery=ConnectInit.queries.getProperty("check_if_fd_record_exist_new");
		    PEDetailForm  FD=null;
		    int pos=0;
			String company_id=null;	
			String date1=null;
			String date2=null;
			String consolidate=null;
			String symbol=null;
			String getCompanyidQuery=ConnectInit.queries.getProperty("get_company_id_where_exchange_id_symbol_series_new");
			String getMonthDiff=ConnectInit.queries.getProperty("get_month_diff");
			String start_date=null;
			int counter1=0,moreThan3MonthCounter=0,consolidatedCounter=0,blankCounter=0,compNotFoundCounter=0;
			int alreadyExistCounter=0,insertCounter=0,updCounter=0;
			boolean updPEValue=false;
			Logging.debug("Keycounter is  "+table1.size());
			for(e=table1.keys();e.hasMoreElements();)			
			{									
				counter++;
				counter1++;							
				Logging.debug("Counter1 is "+counter1);								
				if(counter==10){						
					int a=result.CLOSE_CURSORS_AT_COMMIT;
					connection.commit();
					connection.setAutoCommit(true);
					Logging.debug("counter after commit "+counter1);
					counter=0;					
				}	
				if(counter1%100==0)
				{
					connection.commit();					
					connection.close();
					Logging.debug("connection after counter 100 is "+connect );
					try{
						connection=null;
						connection = connect.getConnectionForTransaction();
						Logging.debug("connection after counter 100 is "+connect );
						connection.rollback();
						Logging.debug("connection is "+Connect.con);
					}catch(SQLException ex){
							Logging.error("Error : Unable to get Transaction connection "+ex.getMessage());
					}
				}
				connection.setAutoCommit(false);
				key = (String)e.nextElement();				
				checkdateq_qauterly=0;
				//FinancialDetailReadFile FD=new FinancialDetailReadFile();
				FD = (PEDetailForm)table1.get(key);
				try
				{		
					pos=0;
					company_id=null;	
					date1=FD.getStart_date();
					date2=FD.getEnd_date();
					date1=ComposeIndex.FormatDateMon(date1);
					date2=ComposeIndex.FormatDateMon(date2);
					consolidate=FD.getCons_ncons();
					if((consolidate.trim().toLowerCase().equals("consolidated"))){
						consolidatedCounter++;
						continue;
					//checkdateq_qauterly=checkForDateQuarterly(date1,date2,consolidate);					
					}
					/*
					 * follwing block added by sudhir to get months difference
					 */
					if(pst!=null)
						pst.close();
					pst = connection.prepareStatement(getMonthDiff);
					
				
					pst.setString(1,date1);
					pst.setString(2,date2);
					//pst.setString(3,keyP2);
					Logging.debug(pst);
					if(result!=null)
						result.close();
					result = pst.executeQuery();
					if(result.next())
					{
						checkdateq_qauterly=result.getInt(1);
			
					}					
					Logging.debug("checkdateq_qauterly is "+checkdateq_qauterly);
					if(checkdateq_qauterly==0){
						buffer.append("<tr><td>");
					    buffer.append(key);						  
					   	buffer.append("</td><td><font color='blue'>Financial Details Can Not Be Saved.</font></td></tr>");
					   	moreThan3MonthCounter++;
					 	continue;
					}else{
						symbol=key.substring(0,(key.indexOf("/")));	
						if(symbol.trim().length()==0){
							blankCounter++;
							continue;
						}
						Logging.debug(" Exchangeid is "+Exchangeid+" symbol is "+symbol);
						if(pst!=null)
							pst.close();
						pst = connection.prepareStatement(getCompanyidQuery);
						pst.setString(1,Exchangeid);
						pst.setString(2,symbol);						
						//pst.setString(3,keyP2);
						Logging.debug(pst);
						if(result!=null)
							result.close();
						result = pst.executeQuery();						
						Logging.debug("get_company_id_where_exchange_id_symbol_series"+result);
						start_date=date1;//FD.getStart_date();commented by sudhir 09Aug05
						
						String end_date=date2;//FD.getEnd_date();commented by sudhir 09Aug05
						
						if(result.next()){
							Logging.debug("got company_id"+result.getString(1));
							if(result.getString(1)!=null)
								company_id=result.getString(1);	
							else{								
								buffer.append("<tr><td>");
							    buffer.append(key);					   
							    buffer.append("</td><td><font color='blue'>Company Not Found</font></td></tr>");
							
							    result.close();							     
							    pst.close();
							    compNotFoundCounter++;
								continue;
							}
					
						}else{			
							 				
								buffer.append("<tr><td>");
							    buffer.append(key);					   
							    buffer.append("</td><td><font color='blue'>Company Not Found</font></td></tr>");
							
							    result.close();							     
							    pst.close();
							    compNotFoundCounter++;
								continue;							 
						}
				
					    result.close();							     
					    pst.close();
						//check if the record already exists
							
						//Statement stmt = Connect.con.createStatement();						
						if(FD.getStart_date()!=null && FD.getEnd_date()!=null){
							if(pst!=null)
								pst.close();							
							pst  = connection.prepareStatement(checkQuery);
							//System.out.println(pst1);
							pst.setString(1,company_id );//company_id
							pst.setString(2,date2);//cimm_xdate
							pst.setString(3,"1");//cimm_id
							/*
							 * for CRISIL CODE OF NSE STOCKS CHECK QUATERLY DATA
							 * FOR CRISIL CODE OF BSE STOCKS CHECK ANNUAL DATA
							 */
							/*if(Exchangeid.equals("2"))
								pst.setString(4,"3");
							else
								pst.setString(4,"12");*/
							pst.setInt(4,checkdateq_qauterly);
							Logging.debug(pst);
							if(result!=null)
								result.close();
							result = pst.executeQuery();						
						}
					
						if(result.next()){							
							Logging.debug("Record Already Exist");
							buffer.append("<tr><td>");
						    buffer.append(key);					    
						 	buffer.append("</td><td><font color='blue'>Record Already Exist</font></td></tr>");
						 	result.close();
						    pst.close();		
						    alreadyExistCounter++;
						 	//calling update data method
						 	updPEValue=false;
						 	if((date1==null) || (date1.equals(""))){
						 		compNotFoundCounter++;
						 		continue;
						 	}
					 		if((FD.getNet_profit()==null) || (FD.getNet_profit().trim().equals(""))){
					 			compNotFoundCounter++;
					 			continue;
					 		}
						 	updPEValue=updPEData(date2,company_id,(new Integer(checkdateq_qauterly).toString()),FD.getNet_profit(),date1,connection);						 	
					
						 	if(updPEValue==true)
						 		updCounter++;
						 	continue;
						 }else{
						 
						    result.close();
						    pst.close();						    
						 	Logging.debug("No Record Exist");
						 	
						 	if(company_id.trim().length()<1){
						 		compNotFoundCounter++;
						 		continue;
						 	}
						 	if(company_id==null){
						 		compNotFoundCounter++;
						 		continue;
						 	}
						 	pst=connection.prepareStatement(insertQuery);						
						    pst.setString(1,"1");
							pst.setString(2,date2);
							pst.setString(3,company_id);
							pst.setInt(4,checkdateq_qauterly);
							pst.setDouble(5,(Double.parseDouble(FD.getNet_profit()))/10000000);
							pst.setString(6,date1);//  increase_decrease_in_stock
	
							Logging.debug("pst2 "+pst);
							Logging.debug(pst);
							pst.executeUpdate();
							insertCounter++;
							Logging.debug("Insert into financial details"+pst);
							Logging.debug("keys are "+key);
						    buffer.append("<tr><td>");
						    buffer.append(key);
						    buffer.append("</td><td>Financial Details Saved</td></tr>");
						    pst.close();
						  }
					}					
					
					if(result!=null)
						result.close();
					if(pst!=null)
						pst.close();					
					 
				}catch(Exception ex){
				//	ex.printStackTrace();
				Logging.error("Error : "+ex.getMessage());
					Logging.error("counter is "+counter);
					connection.rollback();
					connection.setAutoCommit(true);
					break;
				}finally{
					try{
						if(!(result==null))
							result.close();
						if(!(pst==null))
							pst.close();
					}catch(SQLException ex){
						Logging.error("Error : closing resultset and preparedstatement"+ex.getMessage());
					}
				}	
			}
			Logging.debug("counter1 is "+counter1 + " moreThan3MonthCounter is " +moreThan3MonthCounter);
			Logging.debug("consolidatedCounter is "+consolidatedCounter + " blankCounter is " +blankCounter);
			Logging.debug("compNotFoundCounter is "+compNotFoundCounter + " alreadyExistCounter is " +alreadyExistCounter);
			Logging.debug("insertCounter is "+insertCounter + " updCounter is " +updCounter);
			buffernew.append("<br><tr><font color=Blue><td>Values Inserted :</td><td>");
		    buffernew.append(insertCounter);
		    buffernew.append("</td></font></tr>");
		    buffernew.append("<br><tr><font color=Blue><td>Values Updated :</td><td>");
		    buffernew.append(updCounter);
		    buffernew.append("</td></font></tr>");
		    buffernew.append("<br><tr><font color=Red><td>Company Not found :</td><td>");
		    buffernew.append(compNotFoundCounter);
		    buffernew.append("</td></font></tr>");
		    buffernew.append("<br><tr><font color=Red><td>Company Already Exist :</td><td>");
		    buffernew.append(alreadyExistCounter);
		    buffernew.append("</td></font></tr>");
		    buffernew.append("<br><tr><font color=Red><td>Consolidate Records :</td><td>");
		    buffernew.append(consolidatedCounter);
		    buffernew.append("</td></font></tr>");
		    buffernew.append("<br><tr><font color=BLACK><td>Total rows:</td><td>");
		    buffernew.append(counter1);
		    buffernew.append("</td></font></tr>");
		    buffernew.append(buffer);
		    buffer=null;
		}catch(Exception e){
			e.printStackTrace();
			Logging.error("Error : "+e.getMessage());			
		}finally{
			try{
				connection.commit();				
				connection.close();
			}catch(SQLException  e){
				Logging.error("Error : Unable to close connection after PEFile For Loop"+e.getMessage());				
			}
		}
		table1.clear();
		Logging.debug("sending buffer");				
		return buffernew;
	} 	 
	
/////////////////////////////////////////////////////////////////////////////////////	 
	 public static StringBuffer StorePEDetail(String Exchangeid) //StringBuffer
	 {
	 	StringBuffer buffer=new StringBuffer();
	 	StringBuffer buffernew=new StringBuffer();
	 	String stock_id="";
	 	int inscounter=0;
	 	int updcounter=0;
	 	int cantfind=0;
	 	int unimpcounter=0;
	 	int counter1=0;
	 	String mondiff="";
		boolean updPEValue=false;
		int checkdateq_qauterly1=0;
	 	Connect connect = ConnectInit.getConnect();
	 	Connection connection=null;
	 	try
		{	
	 		Logging.debug("inside try StorePEDetail");
			String str="";
			int i;
			Logging.debug("storeFD3 Before con");
			
			try{
				if(connection==null)
					connection = connect.getConnectionForTransaction();
			}catch(Exception e) {
				Logging.error(" Error : "+e.getMessage());
			}
			PreparedStatement pst=null;	
			ResultSet result=null;				 
			Enumeration e = table1.keys();
			int counter=0;
			boolean checkdateq_qauterly=false;
			String key="";
			int n=0;
			for(e=table1.keys();e.hasMoreElements();e.nextElement())
			//for(e=table1.keys();e.hasMoreElements();)			
			{	
				counter1++;
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
				checkdateq_qauterly=false;
				//FinancialDetailReadFile FD=new FinancialDetailReadFile();
				PEDetailForm  FD = (PEDetailForm)table1.get(key);
				try
				{		
					int pos=0;
					String company_id=null;	
					String date1=FD.getStart_date();
					String date2=FD.getEnd_date();
					String consolidate=FD.getCons_ncons();
					String getMonthDiff=ConnectInit.queries.getProperty("get_month_diff");
					checkdateq_qauterly=checkForDateQuarterly(date1,date2,consolidate);
					Logging.debug("checkdateq_qauterly is "+checkdateq_qauterly);
					if(checkdateq_qauterly==false){
						unimpcounter++;
						buffer.append("<tr><td>");
					    buffer.append(key);						  
					   	buffer.append("</td><td><font color='blue'>Financial Details Can Not Be Saved.</font></td></tr>");
					 	continue;
					}else{
						if(pst!=null)
							pst.close();
					 	String date = date1.trim().substring(0, 2);
						String  mon =date1.trim().substring(3, 5);
						String year = date1.trim().substring(6, 10);
						date1 = year+"-"+mon+"-"+date;
						date = date2.trim().substring(0, 2);
						mon =date2.trim().substring(3, 5);
						year = date2.trim().substring(6, 10);
						date2 = year+"-"+mon+"-"+date;
					 	Logging.debug("Date1 is"+date1+"Date2 is "+date2);
						pst = connection.prepareStatement(getMonthDiff);
						pst.setString(1,date1);
						pst.setString(2,date2);
						
						Logging.debug(pst);
						if(result!=null)
							result.close();
						result = pst.executeQuery();
						if(result.next())
						{
							mondiff=result.getString(1);
							mondiff=mondiff.substring(1,2);
							Logging.debug("The Month diff is "+mondiff);
							checkdateq_qauterly1=Integer.parseInt(mondiff);
						}
						String symbol=key.substring(0,(key.indexOf("/")));						
						Logging.debug(" Exchangeid is "+Exchangeid+" symbol is "+symbol);
						pst = connection.prepareStatement(ConnectInit.queries.getProperty("get_company_id_where_exchange_id_symbol_series"));
						pst.setString(1,Exchangeid);
						pst.setString(2,symbol);
						Logging.debug("the symbol gettin is "+symbol);
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
							if(FD.getStart_date()!=null && FD.getEnd_date()!=null)
							{
								
								pst1 = connection.prepareStatement(ConnectInit.queries.getProperty("check_if_fd_record_exist_new"));
								pst1.setString(1,symbol );//company_id
								pst1.setString(2,FD.getEnd_date());//to_date							 
								pst1.setString(3,"1");
								pst1.setInt(4,checkdateq_qauterly1);
								
								result1 = pst1.executeQuery();
							}
							/*else
							{
								pst1 = Connect.con.prepareStatement(connect.queries.getProperty("check_if_fd_record_exist"));
								pst1.setString(1,company_id );//company_id
								pst1.setString(2,FD.getFrom_date());//from_date
								pst1.setString(3,FD.getTo_date());//to_date
								pst1.setString(4,FD.getIs_Audited());//is_audited
								pst1.setString(5,FD.getIs_Cumulative());//is_cumulative
								pst1.setString(6,FD.getIs_Consolidated());//is_consolidated
								pst1.setString(7,FD.getFin_year());//fin_year
								result1 = pst1.executeQuery();
							}		*/				
							Logging.debug("check_if_fd_record_exist: "+pst1);
							if(result1.next())
							
							{	
							//	Logging.getDebug("No Record Exist");
								updcounter++;
								buffer.append("<tr><td>");
							   buffer.append(key);					    
							 	buffer.append("</td><td><font color='blue'>Record Already Exist</font></td></tr>");
							 	//continue;
							 	
							 	updPEValue=updPEData(date2,symbol,(new Integer(checkdateq_qauterly1).toString()),FD.getNet_profit(),date1,connection);						 	
								
									 	if(updPEValue==true)
									 		updcounter++;
									 	continue;
							 }
							 else
							 {
								
								PreparedStatement pst2;	
							    pst2=connection.prepareStatement(ConnectInit.queries.getProperty("insert_into_financial_detail"));						
							    pst2.setInt(1,1);
								pst2.setString(2,FD.getEnd_date());
								Logging.debug("Symbol isisis "+symbol+"_________"+symbol.length());
								pst2.setString(3,symbol);
										//symbol.trim());
								pst2.setInt(4,checkdateq_qauterly1);
										
								pst2.setDouble(5,(Double.parseDouble(FD.getNet_profit()))/10000000);
								pst2.setString(6,FD.getStart_date());
							    
								Logging.debug("pst2 "+pst2);
								pst2.execute();
								Logging.debug("Insert into financial details"+pst2);
								Logging.debug("keys are "+key);
								inscounter++;
								buffer.append("<tr><td>");
							    buffer.append(key);
							    buffer.append("</td><td>Financial Details Saved</td></tr>");	
							  }				
						}
						else{
							cantfind++;
							buffer.append("<tr><td>");
						    buffer.append(key);					   
						    buffer.append("</td><td><font color='blue'>Company Not Found</font></td></tr>");
							continue;
						}
					}
				}catch(Exception ex){
					Logging.error("Error : "+ex.getMessage());
				}			
			}		
		
		table1.clear();
		Logging.debug("sending buffer");
		buffernew.append("<br><tr><font color=Blue><td>Values Inserted :</td><td>");
	    buffernew.append(inscounter);
	    buffernew.append("</td></font></tr>");
		buffernew.append("<br><tr><font color=Blue><td>Record Already Exist :</td><td>");
	    buffernew.append(updcounter);
	    buffernew.append("<br><tr><font color=Blue><td>Values Updated :</td><td>");
	    buffernew.append(updcounter);
	    buffernew.append("</td></font></tr>");
	    buffernew.append("<br><tr><font color=Blue><td>Financial Details Can Not Be Saved:</td><td>");
	    buffernew.append(unimpcounter);
	    buffernew.append("</td></font></tr>");
	    buffernew.append("<br><tr><font color=Blue><td>Company Not Found:</td><td>");
	    buffernew.append(cantfind);
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
		return buffernew;
		
	} 
	 /**
	  * to get date formatted in date format "dd-MM-yyyy"
	  * @param date
	  * @return
	  */
	 public static String formatDate(String date)
	 {
	 	Logging.debug("before date "+date);
	 	java.util.Date d = new java.util.Date(date.trim());
//	 	System.out.println("After date");
	 	SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
	 	String date12=fr.format(d).toString();
	 	Logging.debug("After Simpledate"+date12);
	 	return date12;	 	
	 }
	 public static boolean checkForDateQuarterly(String date,String date1,String consolidate1){
	 	boolean flag=false;
	 	try{
		 	Logging.debug(" current  date is "+date+" current  date1 is "+date1);
			int m1=new Integer(date.trim().substring(3,5)).intValue();
			int m2=new Integer(date1.trim().substring(3,5)).intValue();
			int y1=new Integer(date.trim().substring(6,10)).intValue();
			int y2=new Integer(date1.trim().substring(6,10)).intValue();
			int mdiff=0;
			if((y2-y1)==0){
				mdiff=m1-m2;
			}
			if((y2-y1)==1){
				int monthdiff=12-m1;
				mdiff=monthdiff+m2;
			}
			Logging.debug("m1 is "+m1+" m2 is "+m2+" diff is "+mdiff);
			if((Math.abs(mdiff)==2) && !(consolidate1.trim().equals("Consolidated")))
				flag=true;					 	
	 	}catch(Exception e){
	 		Logging.debug(" Error : "+e.getMessage());
	 	}
	 	return flag;
	 }
}
