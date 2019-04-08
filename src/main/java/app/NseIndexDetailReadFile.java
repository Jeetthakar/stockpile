/*
 * Created on Jun 9, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.harrier.initializeation.ConnectInit;
import com.linuxense.javadbf.DBFReader;


/**
 * @author sunil
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NseIndexDetailReadFile {
	static Logger Logging = Logger.getLogger(NseIndexDetailReadFile.class);
	public static Hashtable table12 = new Hashtable(1000);
	public static Hashtable reject_per_list = new Hashtable(2000);
	Connection con=null;
	boolean improperFormat=true;
	private static String ind_detail=null;
	private static String sp_niffty=null;
	 public static StringBuffer getHashnBuffer(StringBuffer buffer,BufferedReader br)
	 {
	 	Logging.debug("INside fdr");
	 
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
				NseIndexDetailForm   FD=new NseIndexDetailForm();
				int arrlen=arr.length;
				String security=null;
				Logging.debug("Inside FDR after new FinancialDetailForm();  "+arrlen);
				if(arrlen!=16){
					buffer.append("<font size='2' face='Arial' color='Red'><tr>File with improper format </tr></font>");
					return buffer;
				}
				if(arrlen==16)
				{
					//Logging.getDebug("Inside FDetailsNSE");
					while(i<arrlen)
					{						
						switch(i)
						{
							case 	0:
								FD.setMkt(arr[i]);
								break;
							case 	1:
								FD.setSeries(arr[i]);								
								break;
							case 	2:
								FD.setSymbol(arr[i]);
								break;
							case 	3:
								security=arr[i];
								Logging.debug("security in case is "+security);
								FD.setSecurity(security);
								break;
							case 	4:
								FD.setPrev_close(arr[i]);
								break;
							case 	5:
								FD.setOpen(arr[i]);
								break;	
							case 	6:
								FD.setHigh(arr[i]);
								break;
							case 	7:
								FD.setLow(arr[i]);
								break;
							case 	8:
								FD.setClose(arr[i]);
								break;
							case 	9:
								FD.setTraded_value(arr[i]);
								break;
							case 	10:
								FD.setTraded_qty(arr[i]);
								break;
							case 	11:
								FD.setInd_sec(arr[i]);
								break;	
							case 	12:
								FD.setCorp_ind(arr[i]);
								break;
							case 	13:
								FD.setTrades(arr[i]);
								break;
							case 	14:
								FD.setHi_52_week(arr[i]);
								break;
							case 	15:
								FD.setLi_52_week(arr[i]);
								break;
							default :
								Logging.debug("Default switch case : FDETailsNSE");
								break;								
						}
						if(arr[i]!=null && arr[i].trim().length()!=0)
						{
							buffer.append("<td> ");	
							buffer.append(arr[i]);
							buffer.append(" </td>");
						}else
						{
							buffer.append("<td align='center'><font color='Black'> ");	
							buffer.append("-");
							buffer.append(" </font></td>");	
						}		
						i++;
					}
				}
				//Logging.getDebug("Line "+i);	
				/*
				 * if exchange is not considered then there is 
				 * possiblity of more than 1 company getting selected
				 */
				String FDseries="";
				//FDseries=FD.getSeries();
		/*		Logging.getDebug("security in before filling hashtable is "+security);
				if(security!=null && security.trim().equals("SECURITY")){
					ind_detail="indexDetail";
					sp_niffty=null;
				}
				if(security!=null && security.trim().equals("S&P CNX NIFTY Sec")){
					sp_niffty="CNXNIFTY";
					ind_detail=null;
				}
				if(security==null || security.trim().equals("")){
					sp_niffty=null;
					ind_detail=null;
				}
				if(ind_detail.equals("indexDetail")){
					Logging.getDebug("In index detail");
					table_indDetail.put(FD.getSymbol(),FD);
				}
				if(ind_detail.equals("CNXNIFTY")){
					Logging.getDebug("In CNX NIFTY stocks");
					table_nifftyDetail.put(FD.getSymbol(),FD);
				}	*/			 
				if(FD.getSecurity()!=null || !(FD.getSecurity().equals(null))){					
					table12.put(FD.getSecurity(),FD);
				}
				buffer.append("</tr>"); 				 
		 	}	 		
		}
	 	catch(Exception e)
		{
	 		Logging.error("Error : "+e.getMessage());
	 		return null;
		}	 	
		//boolean checkPriceOnDate=PopFileDialogNewStock.CheckForPricesOnSameDate(stock_exid);
		//PopFileDialogNewStock.checkPriceOnDate=checkPriceOnDate;
	 	Logging.debug("Inside FDR before return buffer ");
	 	return buffer;
	 }
 public static StringBuffer getHashnBuffer(StringBuffer buffer_dbf,int numberOfFields,String str_fileName)
		{
		 	Logging.debug("INside fdr");	 	
		 	String strcolumndata;
		 	int keycounter=0;
		 	try
			{
		 		InputStream inputStream  = 
					new FileInputStream(str_fileName);
				DBFReader reader = new DBFReader( inputStream);		
		 		if(numberOfFields!=16){
					buffer_dbf.append("<font size='2' face='Arial' color='Red'><tr>File with improper format </tr></font>");
					return buffer_dbf;
				}			
		 		Object []rowObjects;
				int rowcount=0;	
				while((rowObjects = reader.nextRecord()) != null )
				{	
					NseIndexDetailForm   FD=new NseIndexDetailForm();
					
					String security=null;
					buffer_dbf.append("<tr>");
					for(int i=0;i<numberOfFields;i++){			 
						switch(i)
						{
							case 	0:
								FD.setMkt(rowObjects[i].toString().trim());
								break;
							case 	1:
								FD.setSeries(rowObjects[i].toString().trim());								
								break;
							case 	2:
								FD.setSymbol(rowObjects[i].toString().trim());
								break;
							case 	3:
								security=(rowObjects[i].toString().trim());
								Logging.debug("security in case is "+security);
								FD.setSecurity(security);
								break;
							case 	4:
								FD.setPrev_close(rowObjects[i].toString().trim());
								break;
							case 	5:
								FD.setOpen(rowObjects[i].toString().trim());
								break;	
							case 	6:
								FD.setHigh(rowObjects[i].toString().trim());
								break;
							case 	7:
								FD.setLow(rowObjects[i].toString().trim());
								break;
							case 	8:
								FD.setClose(rowObjects[i].toString().trim());
								break;
							case 	9:
								FD.setTraded_value(rowObjects[i].toString().trim());
								break;
							case 	10:
								FD.setTraded_qty(rowObjects[i].toString().trim());
								break;
							case 	11:
								FD.setInd_sec(rowObjects[i].toString().trim());
								break;	
							case 	12:
								FD.setCorp_ind(rowObjects[i].toString().trim());
								break;
							case 	13:
								FD.setTrades(rowObjects[i].toString().trim());
								break;
							case 	14:
								FD.setHi_52_week(rowObjects[i].toString().trim());
								break;
							case 	15:
								FD.setLi_52_week(rowObjects[i].toString().trim());
								break;
							default :
								Logging.debug("Default switch case : FDETailsNSE");
								break;								
						}
						strcolumndata=rowObjects[i].toString().trim();
						if(strcolumndata==null || strcolumndata.trim().equals(""))
							strcolumndata=".";
						//reads first line containing field names for dbf file.
						buffer_dbf.append("<td>");
						buffer_dbf.append("<font size='2' face='Arial' color='Black'>"+strcolumndata+"</font>");
						buffer_dbf.append("</td>");											
					}
						
					if(FD.getSecurity()!=null || !(FD.getSecurity().equals(null))){					
						//COMMENTED BY SUDHIR 12AUG2005 SINCE KEY WAS REPEATING table12.put(FD.getSecurity(),FD);
						if(FD.getSymbol().trim().length()>0){
							table12.put(FD.getSeries()+ FD.getSymbol(),FD);
							keycounter++;
						}
					}
					buffer_dbf.append("</tr>"); 	
				}							 
			 		 		
			}
		 	catch(Exception e)
			{
		 		Logging.error("Error : "+e.getMessage());
		 		return null;
			}
		 	Logging.debug("Inside FDR before return buffer ");
		 	return buffer_dbf;
		 }
	 public static StringBuffer StoreNseIndexDetailPrices(String exchange_id,String pdate,String correctedFile) //StringBuffer
	 {
	 	StringBuffer buffer=new StringBuffer();
	 	StringBuffer buffernew=new StringBuffer();
	 	int inscounter=0;
	 	int updcounter=0;
	 	int unimpcounter=0;
	 	int counter=0;
	 	int counter1=0;
	 	ResultSet result=null;
	 	String stock_id="";
	 	int count=0;
	 	Logging.debug("inside try update series");
			String str="";
			int i;
			Logging.debug("StoreSeriesUpdated Before con");
			Connect connect = ConnectInit.getConnect();
			Connection connection =null;
			//Changes r made by the requirment of Max_Curssor by Ashishi 10-07-2006
			/*if(Connect.con == null){				
				 con.getConnection();
			}*/
			
			try{
				try{
					if(connection==null)
						connection = connect.getConnectionForTransaction();
				}catch(Exception e) {
					Logging.error(" Error : "+e.getMessage());
				}
			Logging.debug("connection is before rollback() "+connect );
			connection.rollback();
			Logging.debug("connection is after rollback()"+Connect.con);
			Logging.debug("connection is "+Connect.con);
			//String date=QueryClass.formatDate();
			//pdate=ComposeIndex.FormatDateMon(pdate);
			String query = "",stockID = null;
			boolean allow=true;
			Hashtable stkid_list=getTisList(2,exchange_id);
			Hashtable stktis_list=getTisList(1,exchange_id);
			Hashtable last_value_list=checkRejection(exchange_id);
			Hashtable stockid_cad_list=getCADStocks(pdate);
			double stkHigh=0,stkLow=0;			
			String stk_id=null,stk_series=null;
			Date dt = new Date();
			dt.getDate();
			String str_time = dt.toString().split(" ")[3];	
			Enumeration e = table12.keys();
			Logging.debug("table size is "+table12.size());
			String key="";
			
			if(correctedFile!=null && correctedFile.equals("on")){
				try{
					PreparedStatement pst = connection.prepareStatement(ConnectInit.queries.getProperty("deleteStockPriceDailyQuery"));
						pst.setString(1, pdate); //price_date
						pst.setString(2,exchange_id);
					    pst.executeUpdate();
						if(pst!=null)
							pst.close();
				}catch(Exception ee){
					Logging.error("Error : "+ee.getMessage());
				}
			}
			
			for(e=table12.keys();e.hasMoreElements();)			
			{	
				counter++;
				counter1++;
				Logging.debug("counter1 before commit "+counter1);
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
				//String cur_name=result.getCursorName();
				connection.setAutoCommit(false);
				key = (String)e.nextElement();	
				Logging.debug("key is "+key);
				allow=true;
				NseIndexDetailForm  nseindexdetail = (NseIndexDetailForm)table12.get(key);
				Logging.debug("nseindexdetail is "+nseindexdetail);				
				sp_niffty=null;
				ind_detail=null;
				if(key.trim()==null ||  (key.trim().equals(""))||  (key.trim().equals("S&P CNX NIFTY Sec")) || (key.trim().equals("SECURITY"))
						|| (key.trim().equals("COMPULSORY ROLLING STOCKS")) || (key.trim().equals("TRADE FOR TRADE STOCKS")) || (key.trim().equals("OTHER SECURITIES"))){
					sp_niffty="CNXNIFTY";
					continue;
				}
				if(key==null || key.trim().equals("")){
					sp_niffty=null;
					ind_detail=null;
					continue;
				}				
				if(sp_niffty==null || sp_niffty.equals("")){
					Logging.debug("In CNX NIFTY stocks");
					if((nseindexdetail.getSeries().trim()!=null || !(nseindexdetail.getSeries().trim().equals(""))) || (nseindexdetail.getSymbol().trim()!=null || !(nseindexdetail.getSymbol().trim().equals("")))){
						String ticker=(nseindexdetail.getSeries().trim()+nseindexdetail.getSymbol().trim());
						count++;
						Logging.debug("ticker is "+ticker.trim());
						String identifier_code_name="";
							stockID=null;
							key = (String)e.nextElement();	
							Logging.debug("symbol is "+key);
							try {	
								//get stock id of the stock passing stock symbol and exchange ID				
								Logging.debug(" stkid_list size is "+stkid_list.size());
								if(stkid_list.containsKey(ticker)){
									stockID=stkid_list.get(ticker).toString();
								}
								
								Logging.debug(" stockid is "+stockID);
								if(stockID==null || stockID.equals("")){
									Logging.debug("inside stockid null "+stockID);
									String price_unimprt_query=ConnectInit.queries.getProperty("insert_into_new_issues_unimported_prices"); 
									UnimportedStockPrices(connection,exchange_id,ticker,pdate,key,price_unimprt_query,nseindexdetail);
									unimpcounter++;	
									buffer.append("<tr><td>");
									buffer.append(ticker);
									buffer.append("</td><td><font color='black'>Stock Does Not Exist.</font></td>");					
								}else{	
									if(!(stockid_cad_list.isEmpty()) && stockid_cad_list.containsKey(stockID)){
										allow=false;
										String price_unimprt_query=ConnectInit.queries.getProperty("insert_into_new_issues_unimported_prices"); 
										UnimportedStockPrices(connection,exchange_id,ticker,pdate,key,price_unimprt_query,nseindexdetail);
										unimpcounter++;
										buffer.append("<tr><td>");
										buffer.append(ticker);
										buffer.append("</td><td><font color='black'>Can Not Store, Corporate Action Applied On Stock.</font></td>");	
										continue;
									}
									String pvalue=null;
									double reject_per=0.00;
									double alertdiff=0.00;
									Logging.debug("stockID"+stockID);
									if(last_value_list.containsKey(stockID)){						
										if(reject_per_list.containsKey(stockID)){
											reject_per=Double.parseDouble(reject_per_list.get(stockID).toString());
										}
										Logging.debug("inside alertdiff check");
										pvalue=last_value_list.get(stockID).toString();
										String lvalue=nseindexdetail.getClose();
										Logging.debug("pvalue is "+pvalue+" lvalue is "+lvalue);
										alertdiff=(((Double.parseDouble(lvalue))-(Double.parseDouble(pvalue)))/(Double.parseDouble(lvalue)))*100;
										Logging.debug("alertdiff inside check before abs is "+alertdiff);
										alertdiff=Math.abs(alertdiff);
										Logging.debug("alertdiff inside check after abs is "+alertdiff);
									}
									Logging.debug("alertdiff is "+alertdiff+" reject_per is "+reject_per);
									if(alertdiff!=0.00 && alertdiff>reject_per){
										allow=false;
										String price_unimprt_query=ConnectInit.queries.getProperty("insert_into_new_issues_unimported_prices"); 
										UnimportedStockPrices(connection,exchange_id,ticker,pdate,key,price_unimprt_query,nseindexdetail);
										unimpcounter++;
										buffer.append("<tr><td>");
										buffer.append(ticker);
										buffer.append("</td><td><font color='black'>Can Not Store, Prices For Stock Greater Than Rejection Percentage.</font></td>");	
										continue;
									}
									if(allow==true){
										Logging.debug("allow is "+allow);
									//insert the data into table intra_day_stock_price
										PreparedStatement pst = connection.prepareStatement(ConnectInit.queries
												.getProperty("insert_into_intra_day_stock_prices"));
									//	Logging.debug("Inside insert_into_intra_day_stock_prices");
										pst.setDouble(1, Double.parseDouble(nseindexdetail.getClose()));
										pst.setString(2, pdate);
										pst.setString(3, str_time);
										pst.setString(4, stockID);				
						//				Logging.debug("before  executeUpdate "+pst);
										pst.executeUpdate();				
									//	Logging.getInfo("Values inserted into intra_day_stock_prices");
										
										//get the high and low value for todays date ie timestamp in bhavcopy
										pst = connection.prepareStatement(ConnectInit.queries.
												getProperty("get_stock_high_low_value"));
									//	Logging.debug("Inside get_stock_high_low_value");
										pst.setString(1,pdate);
										pst.setString(2,stockID);
										result = pst.executeQuery();
										//check if result set has values in it. true results indicate that entry
										//for todays date for a particular stock is already done so compare last
										//price with the stored high low value 
										if(result.next()){
											stkHigh = result.getDouble(1);
											stkLow = result.getDouble(2);
											//if ltp greater update high, close, passing stock id 
											if(Double.parseDouble(nseindexdetail.getClose())>stkHigh){
												pst = connection.prepareStatement(ConnectInit.queries.
														getProperty("update_high_close_stock_price"));
												pst.setDouble(1,Double.parseDouble(nseindexdetail.getHigh()));
												pst.setDouble(2,Double.parseDouble(nseindexdetail.getClose()));
												pst.setString(3,stockID);
												pst.setString(4,pdate);
												pst.executeUpdate();
												updcounter++;
												/*buffer.append("<tr><td>");
												buffer.append(key);
												buffer.append("</td><td><font color='blue'>High close Price For Stock updated succesfully.</font></td>");*/
											}else{
											//if ltp greater update low, close, passing stock id
											 if(Double.parseDouble(nseindexdetail.getClose())<stkLow){
												pst = connection.prepareStatement(ConnectInit.queries.
														getProperty("update_low_close_stock_price"));
												pst.setDouble(1,Double.parseDouble(nseindexdetail.getLow()));
												pst.setDouble(2,Double.parseDouble(nseindexdetail.getClose()));
												pst.setString(3,stockID);
												pst.setString(4,pdate);
												pst.executeUpdate();
												updcounter++;
											}
											}											
										}else{
											//this indicate that no entry has been made for 2days date for a
											
											//insert pk, open hig low close stkID tradedVol date, tis in 
											//stock_price_daily
											pst = connection.prepareStatement(ConnectInit.queries.
													getProperty("insert_into_stock_price_daily_index_detail_file"));
										//	Logging.debug("Inside insert_into_stock_price_daily ");
											//pst.setInt(1,StkpriceDailyID);
											pst.setDouble(1,Double.parseDouble(nseindexdetail.getOpen()));
											pst.setDouble(2,Double.parseDouble(nseindexdetail.getHigh()));
											pst.setDouble(3,Double.parseDouble(nseindexdetail.getLow()));
											pst.setDouble(4,Double.parseDouble(nseindexdetail.getClose()));
											pst.setString(5,stockID);
											pst.setDouble(6,Double.parseDouble(nseindexdetail.getTraded_qty()));
											pst.setString(7,pdate);
											//pst.setLong(9,tis);//column to be removed from database
											pst.setDouble(8,Double.parseDouble(nseindexdetail.getClose()));
											String tis="0",mkt_cap="0.0";
											double mcv=0.0,pe=0.0,pb=0.0,dividend=0.0;
											if(stktis_list.containsKey(stockID)){
												tis=stktis_list.get(stockID).toString();
											}
											if((tis==null)||(tis.equals("0")))
											{
												mkt_cap="0.0";
											}else{
													mcv=(Double.parseDouble(nseindexdetail.getClose()))*(Double.parseDouble(tis));
													mkt_cap=new Double(mcv).toString();
											}
											pst.setDouble(9,Double.parseDouble(mkt_cap));
											
											pst.setDouble(10,Double.parseDouble(nseindexdetail.getClose()));
											pst.setDouble(11,Double.parseDouble(nseindexdetail.getTrades()));
											Vector PePbDiv=getAveragePePbDivident(stockID);
											if(mcv!=0){
												pe=((double)Double.parseDouble((String)PePbDiv.get(0)))/mcv;
												pb=((double)Double.parseDouble((String)PePbDiv.get(1)))/mcv;
												dividend=((double)Double.parseDouble((String)PePbDiv.get(2)))/mcv;
											}
											pst.setDouble(12,pe);
											pst.setDouble(13,pb);
											pst.setDouble(14,dividend);
											pst.setString(15,nseindexdetail.getTraded_value());
											pst.executeUpdate();
											inscounter++;
										}
									}
								}
							
							} catch (SQLException e1) {
								Logging.error("My error" + e1.getMessage());
								break;
							}						
					}
				}
			}
			buffer.append("<tr><td>");
			buffer.append("");
			buffer.append("</td><td><font color='blue'>Prices Entered Successfully For All Other Stock.</font></td>");	
			
						
		table12.clear();
		
		buffernew.append("<br><tr><font color=Blue><td>Values Inserted :</td><td>");
	    buffernew.append(inscounter);
	    buffernew.append("</td></font></tr>");
		buffernew.append("<br><tr><font color=Blue><td>Values Updated :</td><td>");
	    buffernew.append(updcounter);
	    buffernew.append("</td></font></tr>");
	    buffernew.append("<br><tr><font color=Blue><td>New Issues:</td><td>");
	    buffernew.append(unimpcounter);
	    buffernew.append("<br><tr><font color=Blue><td>Total Rows :</td><td>");
	    buffernew.append(counter1);
	    buffernew.append("</td></font></tr>");
	    buffernew.append(buffer);
	    buffer=null;
		Logging.debug("sending buffer count is "+count);
		
	 }catch(SQLException ex){
		Logging.error("Error : Unable to get Transaction connection "+ex.getMessage());
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
		 * to get the max date on which prices are avialable.
		 * @param exchangeid
		 * @return
		 */
		public static Hashtable getCADStocks(String fdate){
			String mdate=null;
			Hashtable stkCAD_list=new Hashtable();
			Logging.debug("exchangeid is "+fdate);
			if(fdate!=null){
			try{
				Connect connect1=ConnectInit.getConnect();
				PreparedStatement alertpst = Connect.con.prepareStatement(ConnectInit.queries
							.getProperty("get_stockid_cad_exist"));
					alertpst.setString(1, fdate);
					ResultSet alertresult=alertpst.executeQuery();
					while(alertresult.next()){
						stkCAD_list.put(alertresult.getString(1),alertresult.getString(1));
					}				
			}catch(Exception e){
				Logging.error("Error : "+e.getMessage());
			}
			}
			return stkCAD_list;
		}
		/**
		 * to get the hashtable containing stock(key) and last price 
		 * values for stock on max date also set the rejection percentage for stock.
		 * @param exchangeid
		 * @return
		 */
		public static Hashtable checkRejection(String exchangeid){
			int alertcount=0;
			Hashtable lvalue_list=new Hashtable();
			Logging.debug("exchangeid is "+exchangeid);
			if(exchangeid!=null){
			try{
				Connect connect1=ConnectInit.getConnect();
				PreparedStatement alertpst = Connect.con.prepareStatement(ConnectInit.queries
							.getProperty("get_alert_on_price_import"));
					alertpst.setString(1, exchangeid);
					Logging.debug("before  executeUpdate "+alertpst);
					ResultSet alertresult=alertpst.executeQuery();
					while(alertresult.next()){
						//Logging.getDebug("stockid is "+alertresult.getString(1));
						lvalue_list.put(alertresult.getString(1),alertresult.getString(2));
						reject_per_list.put(alertresult.getString(1),alertresult.getString(3));
					}				
			}catch(Exception e){
				Logging.error("Error : "+e.getMessage());
			}
			}
			return lvalue_list;
		}
		/**
		 * @return Returns the Hashtable showing list of tis values.
		 */
		public static Hashtable getTisList(int type,String exchange) {

			Hashtable tislist1=new Hashtable();
			Hashtable tislist_id1=new Hashtable();
			//Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
			Connect con = ConnectInit.getConnect();
			Connection connection=null;
			try{
			if(connection == null){
				connection=con.getdbConnection();
			}
			
			//Logging.getDebug("exchange id is  "+exchange);
			try{
			PreparedStatement pst = connection.prepareStatement(ConnectInit.queries.
					getProperty("get_tis_stockid_Ticker_wise"));
			pst.setString(1,exchange);
			ResultSet result=pst.executeQuery();
			//Logging.getDebug("tislist1  "+tislist1);
			//Logging.getDebug("tislist_id1  "+tislist_id1);						
			while (result.next()) {
				tislist1.put(result.getString(3).trim(),(String)String.valueOf(result.getLong(2)));
				tislist_id1.put(result.getString(1).trim(),result.getString(3));			
			}
			}catch(Exception sqlexp) {
				Logging.error("Error while getting tis "+ sqlexp);
			}
//			Close the Dynamic Connection : Manoj Adekar
			} catch (Exception e) {
				Logging.debug("Error :" + e);
			} finally {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ee) {
					Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
				}
			}
			
			//Enumeration e=tislist1.keys();
			/*while(e.hasMoreElements()){
				String st=(String)e.nextElement();
				String string=(String)tislist1.get(st);
				Logging.debug("Value in hashtable : "+st+"  : "+string);
				
			}*/
			Logging.debug("size hashtable : "+tislist1.size());
			if(type==2)
			{
				//returns the hashtable containing stockid and tis value
				return tislist_id1;
			}
//			returns the hashtable containing symbol and tis value
			return tislist1;
			
		}
		/**
		 * insert the details of unimported stock prices in new issues.
		 */
		public static void UnimportedStockPrices(Connection connection,String exch_id,String ticker,String date,String scrip_name,String query,NseIndexDetailForm form){	
		try {
			Connect con = ConnectInit.getConnect();
			app.NseIndexDetailForm form1 = (NseIndexDetailForm)form;
			
			Logging.debug("Inside insert Stock price unimported");
			//Logging.getDebug("Exchange id is "+exc_id+" key is "+key);
			PreparedStatement psmt = connection.prepareStatement(query);
			Logging.debug("in query psmt is "+psmt);
			psmt.setString(1,exch_id);//SE_ID (new issues stock exchange id).
			Logging.debug("exch_id is "+exch_id);	
			psmt.setString(2,date);//date 
			psmt.setString(3,ticker);//ticker code
			psmt.setString(4,scrip_name);//series (in case of NSEI)
			psmt.setString(5,form1.getClose());//closing value.
			psmt.setString(6,form1.getOpen());//open value.
			psmt.setString(7,form1.getHigh());//high value.
			psmt.setString(8,form1.getLow());//low value.
			psmt.setString(9,form1.getTrades());//no of trades.
			psmt.executeUpdate();
			Logging.debug("after inserting values in new issues");
			
		}catch(Exception e){ Logging.error("Error : " +e.getMessage());}	
	}	
		/**
		 * to get annualised net_profit,net_woth and dividend.
		 * @param stockid
		 * @return
		 */
		public static Vector getAveragePePbDivident(String stockid){
			Vector v1=new Vector();
			double adj_net_profit=0.0,adj_net_worth=0.0,adj_dividend=0.0;
			double net_profit1=0.0,net_profit2=0.0,net_profit3=0.0,net_profit4=0.0,net_worth1=0.0,net_worth2=0.0,
			net_worth3=0.0,net_worth4=0.0,dividend1=0.0,dividend2=0.0,dividend3=0.0,dividend4=0.0;
			String to_date1=null,to_date2=null,to_date3=null,to_date4=null;
			Connect con = ConnectInit.getConnect();
			int count=0;
			Connection connection=null;
			
			try{
				//Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
				if(connection == null){
					connection=con.getdbConnection();
				}
				try{
					PreparedStatement pst = connection.prepareStatement(ConnectInit.queries.
							getProperty("importPrice_get_pe_pb_dividend_quartely"));
					pst.setString(1,stockid);
					ResultSet result=pst.executeQuery();
					while(result.next()){
						count++;
						switch(count){
							case 1:
								net_profit1=result.getDouble(1);
								dividend1=result.getDouble(2);
								net_worth1=result.getDouble(4);
								to_date1=result.getString(6);
							case 2:
								net_profit2=result.getDouble(1);
								dividend2=result.getDouble(2);
								net_worth2=result.getDouble(4);
								to_date2=result.getString(6);
							case 3:
								net_profit3=result.getDouble(1);
								dividend3=result.getDouble(2);
								net_worth3=result.getDouble(4);
								to_date3=result.getString(6);
							case 4:
								net_profit4=result.getDouble(1);
								dividend4=result.getDouble(2);
								net_worth4=result.getDouble(4);
								to_date4=result.getString(6);
						}
					}
					String datem=null,date3m=null;	
					switch(count){
						case 1:
							datem=to_date1;
							adj_net_profit=net_profit1;
							adj_net_worth=net_worth1;
							adj_dividend=dividend1;
							for(int i=1;i<4;i++){
								date3m=getDateQuaterly(datem);
								InsertAdjustedFinDetail(adj_net_profit,adj_dividend,adj_net_worth,datem,date3m,stockid);
								datem=date3m;
							}
							break;
						case 2:
							datem=to_date2;
							adj_net_profit=(net_profit1+net_profit2)/2;
							adj_net_worth=(net_worth1+net_worth2)/2;
							adj_dividend=(dividend1+dividend2)/2;
							for(int i=2;i<4;i++){
								date3m=getDateQuaterly(datem);
								InsertAdjustedFinDetail(adj_net_profit,adj_dividend,adj_net_worth,datem,date3m,stockid);
								datem=date3m;
							}
							break;
						case 3:
								adj_net_profit=(net_profit1+net_profit2+net_profit3)/3;
								adj_net_worth=(net_worth1+net_worth2+net_worth3)/3;
								adj_dividend=(dividend1+dividend2+dividend3)/3;
								datem=to_date3;
								for(int i=3;i<4;i++){
									date3m=getDateQuaterly(datem);
									InsertAdjustedFinDetail(adj_net_profit,adj_dividend,adj_net_worth,datem,date3m,stockid);
									datem=date3m;
								}
								break;
						case 4:
								adj_net_profit=(net_profit1+net_profit2+net_profit3+net_profit4)/4;
								adj_net_worth=(net_worth1+net_worth2+net_worth3+net_worth4)/4;
								adj_dividend=(dividend1+dividend2+dividend3+dividend4)/4;
								datem=to_date3;	
								break;
						default :
								Logging.debug("In default case pe pb calculation");
								break;
					}
					Logging.debug("values in getAveragePePbDivident are");
					Logging.debug("adj_net_profit is "+adj_net_profit+" adj_net_worth is "+adj_net_worth+" adj_dividend is "+adj_dividend);
					v1.add(0,(new Double(adj_net_profit).toString()));
					v1.add(1,(new Double(adj_net_worth).toString()));
					v1.add(2,(new Double(adj_dividend).toString()));
				}catch(Exception e){
					Logging.debug("Error : "+e.getMessage());
				}

				//Close the Dynamic Connection : Manoj Adekar
			} catch (Exception e) {
				Logging.debug("Error :" + e);
			} finally {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ee) {
					Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
				}
			}
			
			return v1;
		}
		public static String getDateQuaterly(String date){			
			Logging.debug("before date "+date);
			String day=date.trim().substring(0,2);
			String month=date.trim().substring(3,5);
			String year=date.trim().substring(6,10);
			int dd=new Integer(day).intValue();
			int mm=new Integer(month).intValue();
			int yy=new Integer(year).intValue();
			if(mm==2 && dd>=28)
				day="28";
			mm=mm+3;
			if(mm>12){
				mm=mm-12;
				yy=yy+1;
			}
			month=new Integer(mm).toString();
			year=new Integer(yy).toString();
			if(month.length()<2)
				month="0"+month;
			
			String date1=day+"-"+month+"-"+yy;
			Logging.debug("3 month before date is "+date1);
			return date1;
		}
		public static void InsertAdjustedFinDetail(double net_profit,double dividend,double net_worth,String fdate,String tdate,String stkid){
			Connect con = ConnectInit.getConnect();
			int count=0;
			Connection connection=null;
//			Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
			try{
				if(connection == null){
					connection=con.getdbConnection();
				}
				try{
					PreparedStatement pst = connection.prepareStatement(ConnectInit.queries.
							getProperty("insert_into_financial_detail_adjusted_values"));
					pst.setString(1,stkid);
					pst.setDouble(2,net_profit);
					pst.setDouble(3,dividend);
					pst.setString(4,fdate);
					pst.setString(5,tdate);
					pst.setDouble(6,net_worth);
					pst.executeUpdate();											
				}catch(Exception e){
					Logging.debug("Error : "+e.getMessage());
				}	
//			Close the Dynamic Connection : Manoj Adekar
			} catch (Exception e) {
				Logging.debug("Error :" + e);
			} finally {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ee) {
					Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
				}
			}
		}
}
