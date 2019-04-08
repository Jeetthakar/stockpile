/*
 * Created on Sep 1, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.harrier.ftp;

/**
 * @author ashishi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import java.io.*;
import java.util.*;
import java.util.Date;
import java.sql.*;

import org.apache.log4j.Logger;

import app.UpdateSeriesReadFile;

public class ReadSecurityFile {
	static Logger Logging = Logger.getLogger(ReadSecurityFile.class);
	
		/**
		 * @param args
		 */
		public static void main(String[] args) throws Exception {
			int j=1;
			//byte b[] = MktThrDB.getBytesFromFile();
			int count = 0;
			//Logging.debug("size of bytes is "+b.length);
		
			Logging.debug("In while loooooop");
			short Trans_c=0;
			int Time_stamp=0;
			short Message_l=0;
			short Sec_token=0;
			String symbol="";
			String series="";
			double issue_cap=0.0;
			short warn_per=0;
			short freez_per=0;
			String credit_rate="";
			short issue_rate=0;
			int iss_strt_date=0;
			int iss_pdate=0;
			int iss_mat_date=0;
			int blot_qty=0;
			int tick_size=0;
			String comp_name="";
			int rDate=0;
			int eDate=0;
			int nd_sDate=0;
			int nd_eDate=0;
			int bc_sDate=0;
			int bc_eDate=0;
			Connection connection=null;
			PreparedStatement  stmt=null;
			String name;
			Thread thr;
			String destination;
			String query=null;
			String username = "sudhir";
			String password ="panchware";
			String dbdriver="jdbc:postgresql://192.168.0.22:5432/income";
			File TransFileIn= new File("D://DownLoad1//"+"9690.sec");
			
			FileInputStream File_In = new FileInputStream(TransFileIn);	
			DataInputStream Data_In = new DataInputStream(File_In);
			/*DataInputStream Data_In = new DataInputStream(
		      new BufferedInputStream(
		        new FileInputStream(TransFileIn)));*/
			/*byte[] myArray;
			myArray = new byte[58];
			byte[] re ;
			re =new byte[b.length];
			Logging.debug("length when byte array"+re.length);
			int a =0;
			int len1 = Data_In.read(myArray);
			//byte leng =Data_In.readByte();
			int len =Data_In.available();
			int we =Data_In.read(re,a,re.length);
			Logging.debug("length when byte array"+we);
			int sub =len-len1;
			Logging.debug("length when byte array"+sub);
			Logging.debug("length when byte array"+len);
			//Logging.debug("length when datatype is in byte"+leng);
			Logging.debug("In for loop "+Data_In.toString());*/
			try 
			{
				
			/*	Logging.debug("Transfileln"+TransFileIn);
				Class.forName("org.postgresql.Driver").newInstance();
				Logging.debug("forName");
		  		connection=DriverManager.getConnection(dbdriver,username,password);
		  		Logging.debug("Connection obj"+connection);
		  		query="insert into mktdata1 (trancode,timestamp,msglength,sectoken,ltprice,bbquant,bbprice,bsquant,bsprice,ttquant,avgtprice,openprice,hiprice,loprice,closeprice,filler)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		  		stmt = connection.prepareStatement(query);
		*/
		  		
			while(Data_In.available()!=0)
			{
				
	  			Logging.debug("reverse value  is "+Short.reverseBytes((short)1280));
				Trans_c=Short.reverseBytes((short)Data_In.readShort());
				
				Logging.debug(" Trans_c is "+Trans_c);
				
				
				
	  			Time_stamp = Integer.reverseBytes(Data_In.readInt());
	  			Logging.debug(" Time_stamp is "+Time_stamp);
	  			long ddt=Math.abs(Time_stamp);
	  			ddt=ddt*1000;
	  			Logging.debug(" ddt is "+ddt);
	  			Date dd=new Date(ddt);
	  			Logging.debug(" date is "+dd);
	  			 
				
	  			
	  			Message_l = Short.reverseBytes((short)Data_In.readShort());
	  			Logging.debug(" Message_l is "+Message_l);
	  			
	  			
	  			Sec_token =Short.reverseBytes((short)Data_In.readShort());
	  			Logging.debug(" Sec_token is "+Sec_token);
	  			// stmt.setShort(4,Sec_token);
	  			
					for(int i=0;i<10;i++) {
	  				symbol=symbol+(char)Data_In.readByte();
	  			}
	  			//symbol =Character.reverseBytes((char)Data_In.readChar());
	  			Logging.debug(" symbol is "+symbol);
	  			
	  			for(int i=0;i<2;i++) {
	  				series=series+(char)Data_In.readByte();
	  			}
	  			Logging.debug(" series is "+series);
	  			
	  			//Logging.debug("Data_In.available() is  "+Data_In.available());
	  			issue_cap=(double)Long.reverseBytes(Data_In.readLong())/100;
	  			  Logging.debug(" issue_cap is "+issue_cap);
	  			// stmt.setLong(5,Last_tr_price); 
	  			
	  			//Logging.debug("Data_In.available() is  "+Data_In.available());
	  			warn_per= Short.reverseBytes(Data_In.readShort());
	  			Logging.debug(" warn percent is "+warn_per);
	  			//stmt.setLong(6,Best_by_quant);
	  			
	  			//Logging.debug("Data_In.available() is  "+Data_In.available());
	  			freez_per= Short.reverseBytes(Data_In.readShort());
	  			Logging.debug(" freez percent is "+freez_per);
	  			//stmt.setLong(7,Best_by_price);
	  			
	  			for(int i=0;i<12;i++) {
	  				credit_rate=credit_rate+(char)Data_In.readByte();
	  			}
	  			Logging.debug(" credit rating  is "+credit_rate);
	  			
	  			//Logging.debug("Data_In.available() is  "+Data_In.available());
	  			issue_rate= Short.reverseBytes(Data_In.readShort());
	  			Logging.debug(" issue rate is "+issue_rate);
	  			//stmt.setLong(8,Best_sell_quant);
	  			
	  			//Logging.debug("Data_In.available() is  "+Data_In.available());
	  			iss_strt_date= Integer.reverseBytes(Data_In.readInt());
	  			ddt=Math.abs(iss_strt_date);
	  			ddt=ddt*1000;
	  			dd=new Date(ddt);
	  			Logging.debug(" issue start date is "+dd);
	  			// stmt.setLong(9,Best_sell_price);
	  			
	  			//Logging.debug("Data_In.available() is  "+Data_In.available());
	  			iss_pdate= Integer.reverseBytes(Data_In.readInt());
	  			ddt=Math.abs(iss_strt_date);
	  			ddt=ddt*1000;
	  			dd=new Date(ddt);
	  			Logging.debug(" issue pdate is "+dd);
	  			//stmt.setLong(10,Total_traded_quant);
	  			
	  			//Logging.debug("Data_In.available() is  "+Data_In.available());
	  			iss_mat_date= Integer.reverseBytes(Data_In.readInt());
	  			ddt=Math.abs(iss_strt_date);
	  			ddt=ddt*1000;
	  			dd=new Date(ddt);
	  			Logging.debug(" issue maturity date is "+dd);
	  			//stmt.setLong(11,Avg_trad_price);
	  			
	  			//Logging.debug("Data_In.available() is  "+Data_In.available());
	  			blot_qty=Integer.reverseBytes(Data_In.readInt());
	  			Logging.debug(" blot_qty is "+blot_qty);
	  			// stmt.setLong(12,op);
	  			
	  			//Logging.debug("Data_In.available() is  "+Data_In.available());
	  			tick_size= Integer.reverseBytes(Data_In.readInt());
	  			Logging.debug(" tick_size is "+tick_size);
	  			//stmt.setLong(13,hp);
	  			
	  			for(int i=0;i<25;i++) {
	  				comp_name=comp_name+(char)Data_In.readByte();
	  			}
	  			Logging.debug(" company name  is "+comp_name);
	  			
	  			//Logging.debug("Data_In.available() is  "+Data_In.available());
	  			rDate= Integer.reverseBytes(Data_In.readInt());
	  			ddt=Math.abs(rDate);
	  			ddt=ddt*1000;
	  			dd=new Date(ddt);
	  			Logging.debug(" record date is "+dd);
	  			// stmt.setLong(14,lp);
	  			
	  			//Logging.debug("Data_In.available() is  "+Data_In.available());
	  			eDate= Integer.reverseBytes(Data_In.readInt());
	  			ddt=Math.abs(iss_strt_date);
	  			ddt=ddt*1000;
	  			dd=new Date(ddt);
	  			Logging.debug(" expiry date is "+dd);
	  			//stmt.setLong(15,cp);
	  			
	  			//Logging.debug("Data_In.available() is  "+Data_In.available());
	  			//filler= Integer.reverseBytes(Data_In.readInt());
	  		//	Logging.debug(" filler is "+filler);
	  			// stmt.setLong(16,filler);
	  			//Logging.debug("after after last value "+Data_In.available());
	  			//stmt.executeUpdate();
	  			Logging.debug("after query");
				Logging.debug("Trans code ="+ Trans_c+","+"Time Stamp ="+ Time_stamp+","+"Message lenght ="+ Message_l+","+"Security token="+Sec_token);//+","+"Last traded price="+Last_tr_price+","+"Best Buy quantity="+Best_by_quant+","+"Best buy price="+Best_by_price+","+"Best sell quant="+Best_sell_quant+","+" Best sell price="+Best_sell_price+","+"Total traded quantity="+Total_traded_quant+","+"Avg traded price="+Avg_trad_price+","+" Open price="+op+","+"High price="+hp+","+" Low price="+lp+","+"Close price="+cp+","+"Filler="+filler+"");
	  			
	  		//	}
	  			//break;
			}
	  			}catch(Exception e)
			{
				Logging.debug("Error : "+e.getMessage());
			}
			
			

		}

	}



