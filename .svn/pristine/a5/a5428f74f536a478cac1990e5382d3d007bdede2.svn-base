/*
 * Created on Feb 15, 2005
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
public class CrExchangeRateFile {
	static Logger Logging = Logger.getLogger(CrExchangeRateFile.class);
/**
 *Declare  and initialize HastTalbe,connection object 
 */
	public static Hashtable table = new Hashtable();
	Connection con=null;
	 public static StringBuffer getHashnBuffer(StringBuffer buffer,BufferedReader br)
	 {
	 	int counter=0;
	 	Logging.debug("INside Xrate ");	 	 
	 	String str;
	 	try
		{
	 		Logging.debug("Inside Xrate try");
	 		String[] arr ;	
	 		int i;
	 		/*
	 		 * read bufferedReader line by line
	 		 * break the line into array
	 		 * read each element and set currency exchange parameters
	 		 * fill buffer
	 		 * fill hastable with the object of currency exchange form class
	 		 * return buffer to the calling method
	 		 */
	 		
	 		while((str=br.readLine())!=null )
	 		{
	 			buffer.append("<tr>");
	 			arr= str.split(",");
				i=0;				
				Logging.debug("counter is "+counter++);
				if(arr.length==0) continue;
				CrExchangeRateForm   CrXRate=new CrExchangeRateForm();
				Logging.debug("Inside Xrate after new CrExchangeRateForm(); ");
				int arrlen=0;
				arrlen=arr.length;
				while(i<arrlen)
				{
					Logging.debug("Inside while i is " + i + " value "+arr[i]);
					switch(i)
					{
					case	0: 	
						 CrXRate.setForex_code(arr[i]);
						break;
					case	1:    
						CrXRate.setOpen_value(arr[i]);
						break;
					case	2:
						CrXRate.setHigh_value(arr[i]);
						break;
					case	3:
						CrXRate.setLow_value(arr[i]);
						break;
					case	4:   
						CrXRate.setClose_value(arr[i]);
						break;
					case	5:   
						CrXRate.setLast_value(arr[i]);
						break;
					case	6:
						CrXRate.setPrev_close(arr[i]);
						break;
					case	7:
						CrXRate.setTraded_volume(arr[i]);
						break;
					case	8:
						CrXRate.setTraded_value(arr[i]);
						break;
					case	9:
						Logging.debug("before formatDate(arr[i]) "+arr[i]);
						CrXRate.setTime_stamp(arr[i]);
						Logging.debug("After formatDate(arr[i]) "+arr[i]);
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
				Logging.debug("Completed First Line of Currency Exchange rate");	
				if(!(CrXRate.getForex_code().equals(null))) 
					table.put(CrXRate.getForex_code(),CrXRate);
				buffer.append("</tr>"); 				 
	 		}	 		 
		}
	 	catch(Exception e)
		{
	 	//	e.printStackTrace();
	 		Logging.debug(e);
	 		return null;
		}
	 	Logging.debug("Inside Xrate before return buffer ");
	 	return buffer;
	 }
	 /*
	  * Declare a stringbuffer to return the reply
	  * read the hash table data using loop
	  * get the from to currency id using the hash table key
	  * which is forex code
	  * check if the record exists for the day in daily table
	  * if no then enter new record
	  * if yes
	  * compare with existing records
	  * if difference found update the existing records
	  * in any case enter new record in intra day table
	  * clear table
	  * return buffer  
	  */
	 public static StringBuffer StoreCrXRate() //StringBuffer
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
			Logging.debug("inide StoreCrXRate");
			String str="";
			int i;
			Logging.debug("StoreCrXRate Before con");
			
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
			String from_to_currency_id="";
			String key="";			 
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
				CrExchangeRateForm  CrXRate = (CrExchangeRateForm)table.get(key);
				try
				{	 				
					pst = connection.prepareStatement(ConnectInit.queries.getProperty("get_from_to_currency_id_from_from_to_currency_where_forex_code"));
					pst.setString(1,key);					 
					result = pst.executeQuery();
					Logging.debug("get_from_to_currency_id_where_forex_code"+pst);
					if(result.next())
					{			
						Logging.debug("got from to currency id"+result.getString(1) + " forex code is "+key );
						from_to_currency_id=result.getString(1);
						/*
						 * check if the record already exists
						 */
						PreparedStatement pst1;	
						Statement stmt = connection.createStatement();
						ResultSet result1;
						pst1 = connection.prepareStatement(ConnectInit.queries.getProperty("select_from_exchange_rate_daily_where_from_to_currency_id_date"));
						pst1.setString(1,from_to_currency_id );//from_to_currency_id
						pst1.setString(2,CrXRate.getTime_stamp());// date						 
						result1 = pst1.executeQuery();
						Logging.debug("check_if_XRate_record_exist"+pst1);
						if(result1.next())
						 {							
							Logging.debug("Record Exist");
							//work for compaare and update
							String ex_rate_daily_id=null;
							double h_val=0.0;
							double l_val=0.0;
							double last_of_file=0.0;
							boolean upd_high=false;
							boolean upd_low=false;
							last_of_file=Double.parseDouble(CrXRate.getLast_value());
							ex_rate_daily_id=result1.getString(1);							
							h_val=Double.parseDouble(result1.getString(4)) ;
							l_val=Double.parseDouble(result1.getString(5)) ;
							Logging.debug("last_of_file==h_val " +(last_of_file==h_val));
							Logging.debug("last_of_file == l_val " +(last_of_file == l_val));
							if(last_of_file!=0.0)
							{
								if(last_of_file==h_val)
									upd_high =false;
								if(last_of_file == l_val)
									upd_low =false;
								if(last_of_file>h_val)
								{
									upd_high =true;
									upd_low=false;
								}
								else
								{
									upd_high =false;									
									if(last_of_file<l_val)
									{
										upd_low=true;
									}
									else
									{
										upd_low=false;
									}									
								}								
							}				
							
							try
							{
								/*run update query
								 * if no change update close,traded value
								 * if high change update high , close,traded value
								 * if low change update low,close,traded value
								 */
								if(upd_high==true)
								{
									Logging.debug(" upd_high==true ex_rate_daily_id is "+ex_rate_daily_id);
									pst1 = connection.prepareStatement(ConnectInit.queries.getProperty("update_exchange_rate_daily_set_high_close_traded_value"));
									pst1.setString(1,CrXRate.getHigh_value() );//
									pst1.setString(2,CrXRate.getClose_value());//
									pst1.setString(3,CrXRate.getTraded_volume() );//
									pst1.setString(4,CrXRate.getLast_value());// LAST VALUE	
									pst1.setString(5,from_to_currency_id);//									
									pst1.setString(6,CrXRate.getTime_stamp());// date	
									pst1.executeUpdate();
									updcounter++;
									Logging.debug("UPdate query is "+pst1);
									buffer.append("<tr><td>");
								    buffer.append(key); 
								 	buffer.append("</td><td><font color='blue'>Record Updated(High,close,traded value)</font></td></tr>");
								 	continue;
								}
								if(upd_low==true)
								{
									Logging.debug(" upd_low==true ex_rate_daily_id is "+ex_rate_daily_id);
									pst1 = connection.prepareStatement(ConnectInit.queries.getProperty("update_exchange_rate_daily_set_low_close_traded_value"));
									pst1.setString(1,CrXRate.getLow_value() );//
									pst1.setString(2,CrXRate.getClose_value());//
									pst1.setString(3,CrXRate.getTraded_volume() );//
									pst1.setString(4,CrXRate.getLast_value());// LAST VALUE	
									pst1.setString(5,from_to_currency_id);//									
									pst1.setString(6,CrXRate.getTime_stamp());// date
									pst1.executeUpdate();
									updcounter++;
									Logging.debug("UPdate query is "+pst1);
									buffer.append("<tr><td>");
								    buffer.append(key); 
								 	buffer.append("</td><td><font color='blue'>Record Updated(Low,close,traded value)</font></td></tr>");
								 	continue;
								}
								if(upd_high==false && upd_low==false )
								{
									Logging.debug(" upd_high==false && upd_low==false ex_rate_daily_id is "+ex_rate_daily_id);
									pst1 = connection.prepareStatement(ConnectInit.queries.getProperty("update_exchange_rate_daily_set_close_traded_value"));									
									pst1.setString(1,CrXRate.getClose_value());//closing value
									pst1.setString(2,CrXRate.getTraded_volume() );//traded volume
									pst1.setString(3,CrXRate.getLast_value());// LAST VALUE	
									pst1.setString(4,from_to_currency_id);//from to currency id									
									pst1.setString(5,CrXRate.getTime_stamp());// date
									Logging.debug("last value is "+CrXRate.getLast_value());								
									pst1.executeUpdate();
									updcounter++;
									Logging.debug("UPdate query is "+pst1);
									buffer.append("<tr><td>");
								    buffer.append(key); 
								 	buffer.append("</td><td><font color='blue'>Record Updated(close,traded value)</font></td></tr>");
								 	continue;
								}
																
							}
							catch(Exception exp2)
							{
								Logging.error("Error : "+exp2.getMessage());
							}							
						 }
						 else
						 {
						 	Logging.debug("No Record Exist");
						 	//work for new entry in the table
						 	String xrate_daily_id="";
						 	try
							{
						 		//catching possiblity of error while getting sequence value
							 	ResultSet rs;
							 	rs = stmt.executeQuery("SELECT nextval('ex_rate_daily_id')");
								rs.next();	
								xrate_daily_id=rs.getString(1);
								Logging.debug("Got Next val"+rs.getString(1));
								rs.close();
							}
						 	catch(Exception exp)
							{
						 	//	exp.printStackTrace();
						 		Logging.debug(exp);
						 		buffer.append("<tr><td>");
							    buffer.append(key); 
							 	buffer.append("</td><td><font color='blue'>Please Re-Enter</font></td></tr>");
							 	continue;
							}
						 	PreparedStatement pst2;	
						    pst2=connection.prepareStatement(ConnectInit.queries.getProperty("insert_into_exchange_rate_daily"));						
						    pst2.setString(1,xrate_daily_id);//
							pst2.setString(2,CrXRate.getOpen_value());//  
							pst2.setString(3,CrXRate.getClose_value());// 
							pst2.setString(4,CrXRate.getHigh_value());//
							pst2.setString(5,CrXRate.getLow_value());// 														
							pst2.setString(6,CrXRate.getTraded_volume());//
							pst2.setString(7,from_to_currency_id);//
							pst2.setString(8,CrXRate.getTime_stamp());//	
							pst2.setString(9,CrXRate.getLast_value());//	
							Logging.debug("pst2 "+pst2);
							pst2.execute();
							inscounter++;
							Logging.debug("Insert into CUrrency exchange file"+pst2);
							Logging.debug("keys are "+"" + " "+"");
						    buffer.append("<tr><td>");
						    buffer.append(key);						    
						    buffer.append("</td><td>Exchange Rate Saved</td></tr>");	
						  }		
						//insert into intra day exchange rate table
						ResultSet rs;	
						PreparedStatement pst2;
						String intra_x_rate_id=null;
						try
						{
						 	rs = stmt.executeQuery("SELECT nextval('intra_day_ex_rate_id')");
							rs.next();	
							intra_x_rate_id=rs.getString(1);							
							rs.close();
							Logging.debug("Got Next val for intra table "+rs.getString(1));
						}
						catch(Exception excp)
						{
							Logging.error("Error : "+excp.getMessage());
						}
						Logging.debug("Got sequence number for intra");
					 	try
						{
						    pst2=connection.prepareStatement(ConnectInit.queries.getProperty("insert_into_intra_day_exchange_rate"));
						    Logging.debug("pst1 "+pst2);
						    Logging.debug("pst2 "+intra_x_rate_id);
						    pst2.setString(1,intra_x_rate_id);//
						    Logging.debug("pst3 "+from_to_currency_id);
							pst2.setString(2,from_to_currency_id);//CrXRate.getOpen_value()
							Logging.debug("pst4 "+CrXRate.getLast_value());
							pst2.setString(3,CrXRate.getLast_value());//
							Logging.debug("pst5 "+CrXRate.getTime_stamp());
							pst2.setString(4,CrXRate.getTime_stamp());//
							Logging.debug("pst6 "+PopFileDialogNewStock.getTime());
							pst2.setString(5,PopFileDialogNewStock.getTime());// 													
							 						
							Logging.debug("Finally pst2 for intra day xrate "+pst2);
							pst2.execute();
							inscounter++;
						}
					 	catch(Exception exp1)
						{
					 		Logging.error("Error : "+exp1.getMessage());		 		
						}
					}
					else{
						unimpcounter++;
						buffer.append("<tr><td>");
						buffer.append(key);
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
		buffernew.append("<br><tr><font color=Blue><td>Values Updated :</td><td>");
	    buffernew.append(updcounter);
	    buffernew.append("</td></font></tr>");
	    buffernew.append("<br><tr><font color=Blue><td>Company Not Found :</td><td>");
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
	public static void main(String[] args) {
	}
}
