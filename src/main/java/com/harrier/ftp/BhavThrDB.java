/**
 * IndThrDB
 * @author abhijit thakare
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.harrier.ftp;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
/**
 * Class MktThrDB is used to store  Mkt file data into database.The file is generated after every 5 minutes 
 * at NSE server and downloaded through File transfer Protocol at our local directory.The file is read from 
 * local directory and it's contents are stored fieldwise in our postgresql database.
 * 
 */
class BhavThrDB
{
	Logger Logging = Logger.getLogger(BhavThrDB.class);
	String name;
	Thread thr;
	String destination;
	String query=null;
	public Hashtable table = new Hashtable(2000);
	String username = "sudhir";
	String password ="panchware";
	String dbdriver="jdbc:postgresql://192.168.0.22:5432/income";
	FileInputStream File_In;
	
	BhavThrDB(String fn,String dest)
	{
		name=fn.trim();
		destination=dest;
		Logging.debug("Name of file "+name);
		bhavdmp();
	}

	public void  bhavdmp()
	{
		Logging.debug("In while mkt run");
		//try{
		int j=1;
		Logging.debug("In while loooooop");
		String Symbol1	;				
		String Series1	;		 		
		String Trade_High_Price1;			
		String Trade_Low_Price1;				
		String Opening_Price1;				
		String Closing_Price1;				
		String Previous_Close_Price1;			
		String Total_Traded_Quantity1;			
		String Total_Traded_Value1;			
		String Carriage_Return1="0.00";  		
		String Symbol;
		String Series;
		String Trade_High_Price;
		String Trade_Low_Price;
		String Opening_Price;
		String Closing_Price;
		String Previous_Close_Price;
		String Total_Traded_Quantity;
		String Total_Traded_Value;
		String Carriage_Return;
		String Message_CR=null;
		
		
		//String Message1=null;
		//String Message11=null;
		char ommess=0;
		String back="\0";
		Connection connection=null;
		PreparedStatement  stmt=null;
		try
		{
		Logging.info("Filename is here"+name);
		String str=null;
		String str1=null;
		String token=null;
		String[] arr=new String[50];
		String[] arr1=new String[100];
		int l=0;
		int i=0;
		Logging.info("Filename is here"+name);
		FileReader file = new FileReader(destination+name);
		BufferedReader br = new BufferedReader(file);
		while((str=br.readLine())!=null )
		{
		Logging.debug("`````````str is "+str.trim());
		str1=str.trim();
		StringTokenizer stk = new StringTokenizer(str1.toString()," ");
		
		String textname=null;
		i=0;
		Bhav bhav = new Bhav();
		while(stk.hasMoreTokens()){
			token=stk.nextToken();
			arr1[i]=token;
			Logging.debug("strin tokaneiser*************+"+stk.countTokens());
			Logging.debug("Strin token*************+"+token);
			i++;
			}
		String first =arr1[0].substring(arr1[0].length()-2, arr1[0].length());
		
		Logging.debug("Strin token*************+"+first);
		if(first.equals("EQ") || first.equals("BE") || first.equals("IL") || first.equals("P1") || first.equals("N1"))
		{
			Symbol1=arr1[0].substring(0, arr1[0].length()-2);
			Logging.debug("Smbole@@@@@@@@2"+Symbol1);
			Series1=first;
			Logging.debug("Series@@@@@@@@2"+Series1);
			Trade_High_Price1=arr1[1];
			Logging.debug("Trade_High_Price1@@@@@@@@2"+Trade_High_Price1);
			Trade_Low_Price1=arr1[2];
			Logging.debug("Trade_Low_Price1@@@@@@@@2"+Trade_Low_Price1);
			Opening_Price1=arr1[3];
			Logging.debug("Opening_Price1@@@@@@@@2"+Opening_Price1);
			Closing_Price1=arr1[4];
			Logging.debug("Closing_Price1@@@@@@@@2"+Closing_Price1);
			Previous_Close_Price1=arr1[5];
			Logging.debug("Previous_Close_Price1@@@@@@@@2"+Previous_Close_Price1);
			Total_Traded_Quantity1=arr1[6];
			Logging.debug("Total_Traded_Quantity1@@@@@@@@2"+Total_Traded_Quantity1);
			Total_Traded_Value1=arr1[7];
			Logging.debug("Total_Traded_Value1@@@@@@@@2"+Total_Traded_Value1);
			String str11=Symbol1;
			bhav.setSymbol(str11);
			bhav.setSeries(Series1);
			bhav.setTrade_High_Price(Trade_High_Price1);
			bhav.setTrade_Low_Price(Trade_Low_Price1);
			bhav.setOpening_Price(Opening_Price1);
			bhav.setClosing_Price(Closing_Price1);
			bhav.setPrevious_Close_Price(Previous_Close_Price1);
			bhav.setTotal_Traded_Quantity(Total_Traded_Quantity1);
			bhav.setTotal_Traded_Value(Total_Traded_Value1);
			bhav.setCarriage_Return("0.00");
			
		}
		else{
			Symbol1=arr1[0];
			Logging.debug("Symbol@@@@@@@@2"+Symbol1);
			Series1=arr1[1];
			Logging.debug("Series@@@@@@@@2"+Series1);
			Trade_High_Price1=arr1[2];
			Logging.debug("Trade_High_Price1@@@@@@@@2"+Trade_High_Price1);
			Trade_Low_Price1=arr1[3];
			Logging.debug("Trade_Low_Price1@@@@@@@@2"+Trade_Low_Price1);
			Opening_Price1=arr1[4];
			Logging.debug("Opening_Price1@@@@@@@@2"+Opening_Price1);
			Closing_Price1=arr1[5];
			Logging.debug("Closing_Price1@@@@@@@@2"+Closing_Price1);
			Previous_Close_Price1=arr1[6];
			Logging.debug("Previous_Close_Price1@@@@@@@@2"+Previous_Close_Price1);
			Total_Traded_Quantity1=arr1[7];
			Logging.debug("Total_Traded_Quantity1@@@@@@@@2"+Total_Traded_Quantity1);
			Total_Traded_Value1=arr1[8];
			Logging.debug("Total_Traded_Value1@@@@@@@@2"+Total_Traded_Value1);
			String str11=Symbol1;
			bhav.setSymbol(str11);
			bhav.setSeries(Series1);
			bhav.setTrade_High_Price(Trade_High_Price1);
			bhav.setTrade_Low_Price(Trade_Low_Price1);
			bhav.setOpening_Price(Opening_Price1);
			bhav.setClosing_Price(Closing_Price1);
			bhav.setPrevious_Close_Price(Previous_Close_Price1);
			bhav.setTotal_Traded_Quantity(Total_Traded_Quantity1);
			bhav.setTotal_Traded_Value(Total_Traded_Value1);
			bhav.setCarriage_Return("0.00");
		}
		Logging.debug("completed ***********  ");
		table.put(bhav.getSymbol(),bhav);
		Logging.debug("completed after puting to Hashtable***********  ");
		}
		try 
		{
			Class.forName("org.postgresql.Driver").newInstance();
			Logging.debug("forName");
	  		connection=DriverManager.getConnection(dbdriver,username,password);
	  		Logging.debug("Connection obj"+connection);
	  		query="insert into bhavdata (symbol,series,trade_high_price,trade_low_price,opening_price,closing_price,previous_close_price,total_traded_quantity,total_traded_value,carriage_return)values(?,?,?,?,?,?,?,?,?,?)";
	  		stmt = connection.prepareStatement(query);
	  		Enumeration e = table.keys();
			Logging.debug("table size is "+table.size());
			String key="";
			
			for(e=table.keys();e.hasMoreElements();)
			{
				key = (String)e.nextElement();	
				Logging.debug("key is "+key);
				Bhav  bhav1 = (Bhav)table.get(key);
				Logging.debug("nseindexdetail is "+bhav1);
				stmt.setString(1,key);
				stmt.setString(2,(bhav1.getSeries()));
				stmt.setString(3,(bhav1.getTrade_High_Price()));
				stmt.setString(4,(bhav1.getTrade_Low_Price()));
				stmt.setString(5,(bhav1.getOpening_Price()));
				stmt.setString(6,(bhav1.getClosing_Price()));
				stmt.setString(7,(bhav1.getPrevious_Close_Price()));
				stmt.setString(8,(bhav1.getTotal_Traded_Quantity()));
				stmt.setString(9,(bhav1.getTotal_Traded_Value()));
				stmt.setString(10,(bhav1.getCarriage_Return()));
				stmt.executeUpdate();
			}
			Thread.sleep(64500000);
		}
		
		catch(Exception e)
		{
			Logging.info(e);
		}//end of catch
	}//end of outer try
		
	catch(Exception e)
	{
			Logging.info(e);
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
		

	}//end of mktdmp
	public void finalize()
	{
		try
		{
			if(File_In!=null)
				File_In.close();
			
		}
			catch(Exception ee){Logging.info(ee.getMessage());}
	}
	
}//end of MktThrDB
