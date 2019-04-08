/*
 * Created on Aug 31, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.harrier.ftp;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * @author ashishi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SecurityFile {
	Logger Logging = Logger.getLogger(SecurityFile.class);
	public Hashtable table = new Hashtable();
	Security stmt=null;
	public StringBuffer secdisplay(String exchange_id,String fileName) {
		StringBuffer buffer = new StringBuffer();
		int j=0;
		long ddt=0;
		Date dd=null;
		short Trans_c=0;
		int Time_stamp=0;
		short Message_l=0;
		short Token_Number=0;
		String symbol ="";
		String Series="" ;
		long Issued_Capital=0;
		short Warning_Percent=0;
		short Freeze_Percent=0;				 	
		String Credit_Rating="";					
		short Issue_Rate=0;				 	
		int  Issue_Start_Date=0;				
		int Issue_Pdate=0;				 	
		int  Issue_Maturity_Date=0;		
		int Board_Lot_Quantity=0;  		
		int  Tick_Size =0;                       			
		String Name_of_Company="" ;      		 	
		int Record_Date =0;                			
		int Expiry_Date  =0;                  			
		int  No_Delivery_Start_Date=0;				
		int  No_Delivery_End_Date=0;     			
		int Book_Closure_Start_Date =0;		 	
		int  Book_Closure_End_Date=0;   			
        String Message11=null;
        String Message12=null;
        String Message13=null;
        String Name_of_com=null;
		String Message=null;
		String Message1=null;			
			try
			{
				String style=null;
				buffer.append("<tr width = '100%'>");
				style="gridStyle-header color='blue' ";
				buffer.append("<td width='9%' align='center' class='"+style+"' valign='center' height='12'>");
				buffer.append("Token_Number");//Secrity Token Number.	
				buffer.append("</td>");
				buffer.append("<td width='9%' align='center' class='"+style+"' valign='center' height='12'>");
				buffer.append("symbol");//Symbol.	
				buffer.append("</td>");
				buffer.append("<td width='9%' align='center' class='"+style+"' valign='center' height='12'>");
				buffer.append("Series");//Series.	
				buffer.append("</td>");
				buffer.append("<td width='9%' align='center' class='"+style+"' valign='center' height='12'>");
				buffer.append("Name_of_Company");//Name_of_Company.	
				buffer.append("</td>");
				
			buffer.append("</tr>");
				
				File TransFileIn = new File("D://DownLoad1//"+fileName);
				
				
				DataInputStream Data_In = new DataInputStream(
					      new BufferedInputStream(
					        new FileInputStream(TransFileIn)));
				try 
				{
					
			  			  			//System.out.println("In for loop "+i);
			  		while(Data_In.available()!=0){			  			
			  			Trans_c=0;Time_stamp=0;Message_l=0;Token_Number=0;symbol ="";
			  			Series="" ;Issued_Capital=0;Warning_Percent=0;Freeze_Percent=0;				 	
			  			Credit_Rating="";Issue_Rate=0;Issue_Start_Date=0;Issue_Pdate=0;				 	
			  			Issue_Maturity_Date=0;Board_Lot_Quantity=0;Tick_Size =0;Name_of_Company="" ;      		 	
			  			Record_Date =0;Expiry_Date  =0;No_Delivery_Start_Date=0;No_Delivery_End_Date=0;     			
			  			Book_Closure_Start_Date =0;Book_Closure_End_Date=0;Message11=null;
			  	        Message12=null;Message13=null;Name_of_com=null;Message=null;Message1=null;
			  			j++;
			  			Logging.debug(" row no is : "+j);
		  			   Trans_c=Short.reverseBytes((short)Data_In.readShort());
		  			   if(Trans_c!=7) {
		  			   		continue;
		  			   }
		  			 stmt = new Security();
		  			   stmt.setTrans_c(Trans_c);
		  			
		  			 
		  			   Time_stamp = Integer.reverseBytes(Data_In.readInt());			  			
			  			ddt=Math.abs(Time_stamp);
			  			ddt=ddt*1000;
			  			
			  			dd=new Date(ddt);
			  			
			  			stmt.setTime_stamp(ddt);
			  			
			  			Message_l = Short.reverseBytes((short)Data_In.readShort());
				  			stmt.setMessage_l(Message_l);
			  				
				  			 Token_Number = Short.reverseBytes((short)Data_In.readShort());
				  			if(Token_Number==0)
				  				continue;
				  			 stmt.setToken_Number(Token_Number);
				  			 buffer.append("<tr>");
				  			buffer.append("<td> ");	
				  			buffer.append(Token_Number);
				  			buffer.append(" </td>");
			  			
			  			for (int i=0;i<10;i++)
			  				{
			  					
			  					symbol=symbol+(char)Data_In.readByte();			  					
			  				}
			  			if(Token_Number==20563){
			  				Logging.debug("in token  "+Token_Number);
			  			}
			  			
			  			
			  			for (int i=0;i<2;i++){
			  				char ch=(char)Data_In.readByte();
		  					
		  					if(Character.isLetterOrDigit(ch)){
		  						Series=Series+ch;
		  					}else{
		  						if(i==0)
		  							Series="";
		  						else
		  							Series=null;
		  					}
		  					
		  				}
			  			if(Series==null || (Series!=null && (Series.trim().equals("null") || Series.trim().equals("") || Series.trim().equals("	") || Series.trim().equals("?")))){
			  				if(symbol!=null && (symbol.trim().endsWith("EQ") || symbol.trim().endsWith("BT") || symbol.trim().endsWith("BL")
			  						|| symbol.trim().endsWith("P1") || symbol.trim().endsWith("O1") || symbol.trim().endsWith("W1") || symbol.trim().endsWith("K1")
			  						|| symbol.trim().endsWith("IL") || symbol.trim().endsWith("BE") || symbol.trim().endsWith("B1") || symbol.trim().endsWith("E1"))){
			  					int i=symbol.length()-3;
			  					Series=symbol.substring(i);
			  					symbol=symbol.substring(0,i).trim();	
			  					if(Series!=null && Series.equals("null"))
			  							Series="";
			  				}
			  			}
			  			stmt.setSymbol(symbol);
			  			buffer.append("<td> ");	
			  			buffer.append(symbol);
			  			buffer.append(" </td>");
			  			stmt.setSeries(Series);
			  			buffer.append("<td> ");	
			  			buffer.append(Series);
			  			buffer.append(" </td>");
			  				Issued_Capital =Long.reverseBytes(Data_In.readLong());
			  				stmt.setIssued_Capital(Issued_Capital);
			  			
			  				Warning_Percent = Short.reverseBytes((short)Data_In.readShort());
			  				stmt.setWarning_Percent(Warning_Percent);
			  			
			  				Freeze_Percent = Short.reverseBytes((short)Data_In.readShort());
			  				stmt.setFreeze_Percent(Freeze_Percent);
			  			
			  				for (int i=0;i<12;i++){
			  				Credit_Rating=Credit_Rating+(char)Data_In.readByte();
			  				}
			  				stmt.setCredit_Rating(Credit_Rating);
			  			
			  				Issue_Rate = Short.reverseBytes((short)Data_In.readShort());
			  				stmt.setIssue_Rate(Issue_Rate);
			  			
			  				Issue_Start_Date = Integer.reverseBytes(Data_In.readInt());
			  				ddt=0;dd=null;
			  				ddt=Math.abs(Issue_Start_Date);
				  			ddt=ddt*1000;
				  			
				  			dd=new Date(ddt);
				  			
				  			stmt.setIssue_Start_Date(ddt);
				  			
			  				Issue_Pdate = Integer.reverseBytes(Data_In.readInt());
			  				ddt=0;dd=null;
			  				ddt=Math.abs(Issue_Pdate);
				  			ddt=ddt*1000;
				  			dd=new Date(ddt);
				  			stmt.setIssue_Pdate(ddt);
				  			
				  			
			  				Issue_Maturity_Date = Integer.reverseBytes(Data_In.readInt());
			  				ddt=0;dd=null;
			  				ddt=Math.abs(Issue_Maturity_Date);
				  			ddt=ddt*1000;
				  			dd=new Date(ddt);
				  			stmt.setIssue_Maturity_Date(ddt);
				  			
				  			
			  				Board_Lot_Quantity= Integer.reverseBytes(Data_In.readInt());
			  				stmt.setBoard_Lot_Quantity(Board_Lot_Quantity);
			  			
			  				Tick_Size= Integer.reverseBytes(Data_In.readInt());
			  				stmt.setTick_Size(Tick_Size);
			  				for (int i=0;i<25;i++){
			  					Name_of_Company = Name_of_Company+(char)Data_In.readByte();
			  				}
			  				Name_of_Company=Name_of_Company.replaceAll("\0","");
			  				stmt.setName_of_Company(Name_of_Company);
			  				buffer.append("<td> ");	
			  				buffer.append(Name_of_Company);
			  				buffer.append(" </td>");
			  				Record_Date= Integer.reverseBytes(Data_In.readInt());
			  				ddt=0;dd=null;
			  				ddt=Math.abs(Record_Date);
				  			ddt=ddt*1000;
				  			dd=new Date(ddt);
				  			stmt.setRecord_Date(ddt);
				  			
			  				Expiry_Date= Integer.reverseBytes(Data_In.readInt());
			  				ddt=0;dd=null;
			  				ddt=Math.abs(Expiry_Date);
				  			ddt=ddt*1000;
				  			dd=new Date(ddt);
				  			stmt.setExpiry_Date(ddt);
				  			
			  				No_Delivery_Start_Date= Integer.reverseBytes(Data_In.readInt());
			  				ddt=0;dd=null;
			  				ddt=Math.abs(No_Delivery_Start_Date);
				  			ddt=ddt*1000;
				  			dd=new Date(ddt);
				  			stmt.setNo_Delivery_Start_Date(ddt);
				  			
				  			
			  				No_Delivery_End_Date= Integer.reverseBytes(Data_In.readInt());
			  				ddt=0;dd=null;
			  				ddt=Math.abs(No_Delivery_End_Date);
				  			ddt=ddt*1000;
				  			dd=new Date(ddt);
				  			stmt.setNo_Delivery_End_Date(ddt);
				  		
			  				Book_Closure_Start_Date= Integer.reverseBytes(Data_In.readInt());
			  				ddt=0;dd=null;
			  				ddt=Math.abs(Book_Closure_Start_Date);
				  			ddt=ddt*1000;
				  			dd=new Date(ddt);
				  			stmt.setBook_Closure_Start_Date(ddt);
				  			
			  				Book_Closure_End_Date= Integer.reverseBytes(Data_In.readInt());
			  				ddt=0;dd=null;
			  				ddt=Math.abs(Book_Closure_End_Date);
				  			ddt=ddt*1000;
				  			dd=new Date(ddt);
				  			stmt.setBook_Closure_End_Date(ddt);
				  			
				  		
			  			table.put(stmt.getSeries()+stmt.getSymbol(),stmt);
			  			stmt=null;
			  			buffer.append("</tr>");			  			
			  		}	
			  		
				}			
				catch(Exception e) {
					Logging.error(" Error : "+e.getMessage());
				}//end of catchfinally{
				try
				{
					 if(Data_In!=null)
					 	Data_In.close();
					
				}
				catch(Exception erd) {
					Logging.error(" Error : "+erd.getMessage());
				}
		  
		}//end of outer try
		catch(Exception e)
		{
			Logging.error(" Error : "+e.getMessage());
		}//end of catch	
			
		return buffer;	
	}
	/**
	 * method to save the details for securities contained in the latest
	 * Security.dat file uploaded from FTP server.
	 * 
	 * @return StringBuffer
	 */
	public StringBuffer storeSecurityDetails() {
		StringBuffer buffer = new StringBuffer();
		String symbol=null,series=null;
		int counter1 = 0;
		Connect connect = ConnectInit.getConnect();
		Hashtable scrt_tokenList=new Hashtable();
		Connection connection = null;
		try {
			try {
				if (connection == null)
					connection = connect.getConnectionForTransaction();
			} catch (Exception e) {
				Logging.error(" Error : " + e.getMessage());
			}
			PreparedStatement pst=null;
			ResultSet result = null;
			Enumeration e = table.keys();
			String key = "";
			int counter = 0;
			scrt_tokenList=getListOfSecurities(connect,connection);
			Logging.debug("size of hashtable......" + table.size());
			String query=ConnectInit.queries.getProperty("Ftp_security_insert");//"insert into secdata (trancode,timestamp,msglength,token_number,symbol,series,issued_capital,warning_percent,freeze_percent,credit_rating,issue_rate,issue_start_date,issue_pdate,issue_maturity_dat,board_lot_quantity,tick_size,name_of_company,record_date,expiry_date,no_delivery_start_date,no_delivery_end_date,book_closure_start_date,book_closure_end_date)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			for (e = table.keys(); e.hasMoreElements();) {
				counter1++;
				if (counter == 5) {
					int a = result.CLOSE_CURSORS_AT_COMMIT;
					connection.commit();
					connection.setAutoCommit(true);
					Logging.debug("counter after commit 5 IS " + counter1);
					counter = 0;
				}
				if (counter1 % 100 == 0) {
					connection.commit();
					connection.close();
					connection=null;
					try {
						if (connection == null)
							connection = connect
									.getConnectionForTransaction();
					} catch (Exception e2) {
						Logging.error(" Error : " + e2.getMessage());
					}					
				}				
				try {
					key = (String) e.nextElement();
					stmt = (Security) table.get(key);	
					symbol=stmt.getSymbol();
					series=stmt.getSeries();
					buffer.append("<tr>");		
					if(scrt_tokenList.containsKey(new Short(stmt.getToken_Number()).toString())) {
						buffer.append("<td>");
				  		buffer.append(stmt.getName_of_Company()+"	( "+series+symbol+"	) ");
				  		buffer.append("</td>");
				  		buffer.append("<td>");
				  		buffer.append("Securities Already exist.");
				  		buffer.append("</td>");				  		
					} else {
						pst = connection.prepareStatement(query);
				  		pst.setShort(1,stmt.getTrans_c());
				  		pst.setTimestamp(2,new Timestamp(stmt.getTime_stamp()));//time stamp
				  		pst.setShort(3,stmt.getMessage_l());
				  		pst.setShort(4,stmt.getToken_Number());
				  		pst.setString(5,symbol);
				  		pst.setString(6,series);
				  		pst.setLong(7,stmt.getIssued_Capital());
				  		pst.setShort(8,stmt.getWarning_Percent());
				  		pst.setShort(9,stmt.getFreeze_Percent());
				  		pst.setString(10,stmt.getCredit_Rating());
				  		pst.setShort(11,stmt.getIssue_Rate());
				  		pst.setTimestamp(12,new Timestamp(stmt.getIssue_Start_Date()));//issue start date
				  		pst.setTimestamp(13,new Timestamp(stmt.getIssue_Pdate()));//issue end date
				  		pst.setTimestamp(14,new Timestamp(stmt.getIssue_Maturity_Date()));// issue maturity date
				  		pst.setInt(15,stmt.getBoard_Lot_Quantity());
				  		pst.setInt(16,stmt.getTick_Size());
				  		pst.setString(17,stmt.getName_of_Company());
				  		pst.setTimestamp(18,new Timestamp(stmt.getRecord_Date()));//record date
				  		pst.setTimestamp(19,new Timestamp(stmt.getExpiry_Date()));// expiry date
				  		pst.setTimestamp(20,new Timestamp(stmt.getNo_Delivery_Start_Date()));// no delivery start date
				  		pst.setTimestamp(21,new Timestamp(stmt.getNo_Delivery_End_Date()));// no delivery end date
				  		pst.setTimestamp(22,new Timestamp(stmt.getBook_Closure_Start_Date()));// book clouser start date 
				  		pst.setTimestamp(23,new Timestamp(stmt.getBook_Closure_End_Date()));// book clouser end date 
				  		pst.executeUpdate();
				  			  		
				  		buffer.append("<td>");
				  		buffer.append(stmt.getName_of_Company()+"	( "+series+symbol+"	) ");
				  		buffer.append("</td>");
				  		buffer.append("<td>");
				  		buffer.append("values inserted for the securities");
				  		buffer.append("</td>");
					}
			  		buffer.append("</tr>");
				} catch (Exception ee) {
					Logging.error(" Error : "+ee.getMessage());
				}
			}
		} catch (Exception ex) {
			Logging.error(" Error : "+ex.getMessage());
		}
		finally {
			try {
					if(connection!=null)
						connection.close();
			}  catch (Exception ex) {
				Logging.error(" Error : Unable to close Connection "+ex.getMessage());
			}
		}
		return buffer;
	}
	public Hashtable getListOfSecurities(Connect con,Connection connection){
		Hashtable security_list=new Hashtable();
		Statement pst=null;
		ResultSet result = null;
		try {
			pst=connection.createStatement();
			result=pst.executeQuery(ConnectInit.queries.getProperty("securityFtp_get_security_token_list"));
			while(result.next()) {
				security_list.put(result.getString(1),result.getString(1));
			}
			result.close();
		} catch (Exception e) {
			Logging.error(" Error : "+e.getMessage());
		}
		return security_list;
	}
}
