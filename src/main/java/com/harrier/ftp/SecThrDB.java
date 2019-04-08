/**
 * IndThrDB
 * @author abhijit thakare
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.harrier.ftp;
import java.io.*;
import java.util.*;
import java.util.Date;
import java.sql.*;

import org.apache.log4j.Logger;
/**
 * Class MktThrDB is used to store  Mkt file data into database.The file is generated after every 5 minutes 
 * at NSE server and downloaded through File transfer Protocol at our local directory.The file is read from 
 * local directory and it's contents are stored fieldwise in our postgresql database.
 * 
 */
class SecThrDB
{
	Logger Logging = Logger.getLogger(SecThrDB.class);
	String name;
	Thread thr;
	String destination;
	String query=null;
	String username = "sudhir";
	String password ="panchware";
	String dbdriver="jdbc:postgresql://192.168.0.22:5432/income";
	FileInputStream File_In;
	SecThrDB(String fn,String dest)
{
	
	name=fn.trim();
	destination=dest;
	Logging.debug("Name of file "+name);
	secdmp();
}

	public void  secdmp()
	{
		
		//try{
		int j=1;
		
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
		 char last=0;
		 String Messagelast=null;
		//String Message1=null;
		//String Message11=null;
		char ommess=0;
		
		Connection connection=null;
		PreparedStatement  stmt=null;
		try
		{
		
			//File TransFileIn = new File("D://DownLoad1//"+"Securities.dat");
			
			File TransFileIn= new File(destination+name);
			DataInputStream Data_In = new DataInputStream(
				      new BufferedInputStream(
				        new FileInputStream(TransFileIn)));	
		try 
		{
			Logging.debug("Transfileln"+TransFileIn);
			Class.forName("org.postgresql.Driver").newInstance();
			Logging.debug("forName sec");
	  		connection=DriverManager.getConnection(dbdriver,username,password);
	  		Logging.debug("Connection obj"+connection);
	  		query="insert into secdata (trancode,timestamp,msglength,token_number,symbol,series,issued_capital,warning_percent,freeze_percent,credit_rating,issue_rate,issue_start_date,issue_pdate,issue_maturity_dat,board_lot_quantity,tick_size,name_of_company,record_date,expiry_date,no_delivery_start_date,no_delivery_end_date,book_closure_start_date,book_closure_end_date)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	  		stmt = connection.prepareStatement(query);
	  			  			//Logging.debug("In for loop "+i);
	  		while(Data_In.available()!=0){
	  			
	  			
	  			if(Data_In.available()!=0){
	  			   Trans_c=Short.reverseBytes((short)Data_In.readShort());
	  			 stmt.setShort(1,Trans_c);
	  			   }
	  			Logging.debug("data available afer 1"+Data_In.available());
	  			  
	  			 Logging.debug("Trans_c"+Trans_c);
	  			
	  			if(Data_In.available()!=0){
	  				Time_stamp = Integer.reverseBytes(Data_In.readInt());
		  			
		  			long ddt=Math.abs(Time_stamp);
		  			ddt=ddt*1000;
		  			
		  			Date dd=new Date(ddt);
		  			
		  			stmt.setDate(2,new java.sql.Date(ddt));
		  			Logging.debug("Time_stamp"+ddt); 
	  			}
	  			Logging.debug("data available afer 2"+Data_In.available());  
	  			
	  			if(Data_In.available()!=0){
	  				
	  				Message_l = Short.reverseBytes((short)Data_In.readShort());
	  				stmt.setShort(3,Message_l);
		  			}
	  			Logging.debug("Message_l"+Message_l); 
	  				
	  			
	  			if(Data_In.available()!=0){
		  			 Token_Number = Short.reverseBytes((short)Data_In.readShort());
		  			stmt.setShort(4,Token_Number);
		  		}
	  			Logging.debug("Token_Number"+Token_Number); 
			  		Logging.debug("data available afer 3"+Data_In.available());	
	  			
	  			if(Data_In.available()!=0){
	  				for (int i=0;i<10;i++)
	  				{
	  					symbol=symbol+(char)Data_In.readByte();
	  					
	  				}
	  			}
	  			Logging.debug("data available afer 4"+Data_In.available());
	  			Logging.debug("symbol"+symbol); 
	  			stmt.setString(5,symbol);
	  			
	  			if(Data_In.available()!=0){
	  				for (int i=0;i<2;i++)
	  				{
	  					Series=Series+(char)Data_In.readByte();
	  					
	  				}
	  			}
	  			Logging.debug("data available afer 5"+Data_In.available());
	  			Logging.debug("Series"+Series); 
	  			stmt.setString(6,Series);
	  			
	  			if(Data_In.available()!=0){
	  				Issued_Capital =Long.reverseBytes(Data_In.readLong());
	  				stmt.setLong(7,Issued_Capital);
	  			}
	  			
	  			Logging.debug("Issued_Capital"+Issued_Capital); 
	  			Logging.debug("data available afer 6"+Data_In.available());
	  			
	  			if(Data_In.available()!=0){
	  				Warning_Percent = Short.reverseBytes((short)Data_In.readShort());
	  				stmt.setShort(8,Warning_Percent);
	  			}
	  			
	  			Logging.debug("Warning_Percent"+Warning_Percent); 
	  			Logging.debug("data available afer 7"+Data_In.available());
	  			
	  			if(Data_In.available()!=0){
	  				Freeze_Percent = Short.reverseBytes((short)Data_In.readShort());
	  				stmt.setShort(9,Freeze_Percent);
	  			}
	  			Logging.debug("Freeze_Percent"+Freeze_Percent); 
	  			Logging.debug("data available afer 8"+Data_In.available());
	  			if(Data_In.available()!=0){
	  				for (int i=0;i<12;i++){
	  				Credit_Rating=Credit_Rating+(char)Data_In.readByte();
	  				}
	  			}
	  			Logging.debug("Credit_Rating"+Credit_Rating); 
	  			Logging.debug("data available afer 9"+Data_In.available());
	  			stmt.setString(10,Credit_Rating);
	  			
	  			
	  			if(Data_In.available()!=0){
	  				Issue_Rate = Short.reverseBytes((short)Data_In.readShort());
	  				stmt.setShort(11,Issue_Rate);
	  			}
	  			Logging.debug("Issue_Rate"+Issue_Rate); 
	  			Logging.debug("data available afer 10"+Data_In.available());
	  			
	  			if(Data_In.available()!=0){
	  				Issue_Start_Date = Integer.reverseBytes(Data_In.readInt());
	  				
	  				long ddt=Math.abs(Issue_Start_Date);
		  			ddt=ddt*1000;
		  			
		  			Date dd=new Date(ddt);
		  			
		  			stmt.setDate(12,new java.sql.Date(ddt));
		  			}
	  			Logging.debug("Issue_Start_Date"+Issue_Start_Date);
	  			Logging.debug("data available afer 11"+Data_In.available());
	  			
	  			if(Data_In.available()!=0){
	  				Issue_Pdate = Integer.reverseBytes(Data_In.readInt());
	  				
	  				long ddt=Math.abs(Issue_Pdate);
		  			ddt=ddt*1000;
		  			Date dd=new Date(ddt);
		  			stmt.setDate(13,new java.sql.Date(ddt));
		  			
		  			}
	  			Logging.debug("Issue_Pdate"+Issue_Pdate);
	  			Logging.debug("data available afer 12"+Data_In.available());
		  			
	  			if(Data_In.available()!=0){
	  				Issue_Maturity_Date = Integer.reverseBytes(Data_In.readInt());
	  				
	  				long ddt=Math.abs(Issue_Maturity_Date);
		  			ddt=ddt*1000;
		  			Date dd=new Date(ddt);
		  			stmt.setDate(14,new java.sql.Date(ddt));
		  			
		  			}
	  			Logging.debug("Issue_Maturity_Date"+Issue_Maturity_Date);
	  			Logging.debug("data available afer 13"+Data_In.available());
	  			
	  			if(Data_In.available()!=0){
	  				Board_Lot_Quantity= Integer.reverseBytes(Data_In.readInt());
	  				stmt.setInt(15,Board_Lot_Quantity);
	  			}
	  			
	  			Logging.debug("Board_Lot_Quantity"+Board_Lot_Quantity);
	  			
	  			Logging.debug("data available afer 14"+Data_In.available());
	  			if(Data_In.available()!=0){
	  				Tick_Size= Integer.reverseBytes(Data_In.readInt());
	  				stmt.setInt(16,Tick_Size);
	  			}
	  			Logging.debug("Tick_Size"+Tick_Size);
	  			Logging.debug("data available afer 16"+Data_In.available());
	  			
	  			if(Data_In.available()!=0){
	  				for (int i=0;i<25;i++){
	  					Name_of_Company = Name_of_Company+(char)Data_In.readByte();
	  				}
	  			}
	  			Logging.debug("Name_of_Company"+Name_of_Company);
	  			stmt.setString(17,Name_of_Company);
	  			Logging.debug("data available afer 17"+Data_In.available());
	  			
	  			if(Data_In.available()!=0){
	  				Record_Date= Integer.reverseBytes(Data_In.readInt());
	  				long ddt=Math.abs(Record_Date);
		  			ddt=ddt*1000;
		  			Date dd=new Date(ddt);
		  			stmt.setDate(18,new java.sql.Date(ddt));
		  			}
	  			Logging.debug("Record_Date"+Record_Date);
	  			Logging.debug("data available afer 18"+Data_In.available());
	  			
	  			if(Data_In.available()!=0){
	  				Expiry_Date= Integer.reverseBytes(Data_In.readInt());
	  				long ddt=Math.abs(Expiry_Date);
		  			ddt=ddt*1000;
		  			Date dd=new Date(ddt);
		  			stmt.setDate(19,new java.sql.Date(ddt));
		  			}
	  			Logging.debug("Expiry_Date"+Expiry_Date);
	  			Logging.debug("data available afer 19"+Data_In.available());
	  			
	  			if(Data_In.available()!=0){
	  				No_Delivery_Start_Date= Integer.reverseBytes(Data_In.readInt());
	  				long ddt=Math.abs(No_Delivery_Start_Date);
		  			ddt=ddt*1000;
		  			Date dd=new Date(ddt);
		  			stmt.setDate(20,new java.sql.Date(ddt));
		  			
		  			}
	  			Logging.debug("No_Delivery_Start_Date"+No_Delivery_Start_Date);
	  			Logging.debug("data available afer 20"+Data_In.available());
	  			if(Data_In.available()!=0){
	  				 No_Delivery_End_Date= Integer.reverseBytes(Data_In.readInt());
	  				long ddt=Math.abs(No_Delivery_End_Date);
		  			ddt=ddt*1000;
		  			Date dd=new Date(ddt);
		  			stmt.setDate(21,new java.sql.Date(ddt));
		  			
		  			}
	  			Logging.debug("No_Delivery_End_Date"+No_Delivery_End_Date);
	  			Logging.debug("data available afer 21"+Data_In.available());
	  			
	  			if(Data_In.available()!=0){
	  				Book_Closure_Start_Date= Integer.reverseBytes(Data_In.readInt());
	  				long ddt=Math.abs(Book_Closure_Start_Date);
		  			ddt=ddt*1000;
		  			Date dd=new Date(ddt);
		  			stmt.setDate(22,new java.sql.Date(ddt));
		  		
		  			}
	  			Logging.debug("Book_Closure_Start_Date"+Book_Closure_Start_Date);	
	  			Logging.debug("data available afer 22"+Data_In.available());
	  			if(Data_In.available()!=0)
	  			{
	  				
	  				Book_Closure_End_Date= Integer.reverseBytes(Data_In.readInt());
	  				long ddt=Math.abs(Book_Closure_End_Date);
		  			ddt=ddt*1000;
		  			Date dd=new Date(ddt);
		  			stmt.setDate(23,new java.sql.Date(ddt));
		  			
		  		}
	  			Logging.debug("Book_Closure_End_Date"+Book_Closure_Start_Date);
	  			Logging.debug("data available afer 23"+Data_In.available());
	  			//table.put(stmt.getSymbol()+stmt.getSeries(),stmt);
	  			
	  		}	
			
	  	Thread.sleep(64500000);			
	  		Logging.debug("End Of File");
	  		j++;
	  		
		}			
		catch(Exception e)
		{
			Logging.debug(e);
		}//end of catch
		finally{
			try
			{
				 if(Data_In!=null)
				 	Data_In.close();
				
			}
			catch(Exception erd){}
	   }
	}//end of outer try
	catch(Exception e)
	{
			Logging.debug(e);
	}//end of catch	
		finally{
					try
					{
						 if(stmt!=null)
						 	 stmt.close();
						if(connection!=null)
							connection.close();
					}
					catch(Exception er){}
			   }
		
	
	}

}//end of MktThrDB
