/**
 * MktThrDB
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

import app.*;
/**
 * Class MktThrDB is used to store  Mkt file data into database.The file is generated after every 5 minutes 
 * at NSE server and downloaded through File transfer Protocol at our local directory.The file is read from 
 * local directory and it's contents are stored fieldwise in our postgresql database.
 * 
 */
class MktThrDB
{
	Logger Logging = Logger.getLogger(MktThrDB.class);
	String name;
	Thread thr;
	String destination;
	public Hashtable table = new Hashtable(2000);
	StringBuffer buffer = new StringBuffer();
	String query=null;
	String username = "sudhir";
	String password ="panchware";
	String dbdriver="jdbc:postgresql://192.168.0.22:5432/income";
	FileInputStream File_In;
	MktThrDB(String fn,String dest)
	{
		
		name=fn.trim();
		destination=dest;
		Logging.debug("Name of file "+name);
		mktdmp();
	}

	public void  mktdmp()
	{
		Logging.debug("In while mkt run");
		//try{
		int j=1;
		Logging.debug("In while loooooop");
		short Trans_c=0;
		int Time_stamp=0;
		short Message_l=0;
		short Sec_token=0;
		float Last_tr_price=0.0F;
		int Best_by_quant=0;
		float Best_by_price=0.0F;
		int Best_sell_quant=0;
		float Best_sell_price=0.0F;
		int Total_traded_quant=0;
		float Avg_trad_price=0.0F;
		float op=0.0F;
		float hp=0.0F;
		float lp=0.0F;
		float cp=0.0F;
		int filler=0;
		Connection connection=null;
		PreparedStatement  stmt=null;
		Market mark = new Market();
		String style=null;
		try{
			buffer.append("<tr width = '100%'>");
			style="gridStyle-header color='blue' ";
			buffer.append("<td width='7%' align='center' class='"+style+"' valign='center' height='12'>");
			buffer.append("Trans_code");//symbol.
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='"+style+"' valign='center' height='12'>");
			buffer.append("Time_stamp");//closing price taken as last.
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='"+style+"' valign='center' height='12'>");
			buffer.append("Message_length");//tis used as traded volume to display.
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='"+style+"' valign='center' height='12'>");
			buffer.append("Sec_token");//market capital value.	
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='"+style+"' valign='center' height='12'>");
			buffer.append("Last_tr_price");//market capital value.	
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='"+style+"' valign='center' height='12'>");
			buffer.append("Best_by_quant");//market capital value.	
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='"+style+"' valign='center' height='12'>");
			buffer.append("Best_sell_quant");//market capital value.	
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='"+style+"' valign='center' height='12'>");
			buffer.append("Best_sell_price");//market capital value.	
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='"+style+"' valign='center' height='12'>");
			buffer.append("Total_traded_quant");//market capital value.	
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='"+style+"' valign='center' height='12'>");
			buffer.append("Avg_trad_price");//market capital value.	
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='"+style+"' valign='center' height='12'>");
			buffer.append("open_price");//market capital value.	
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='"+style+"' valign='center' height='12'>");
			buffer.append("High_price");//market capital value.	
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='"+style+"' valign='center' height='12'>");
			buffer.append("Low_price");//market capital value.	
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='"+style+"' valign='center' height='12'>");
			buffer.append("Close_price");//market capital value.	
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='"+style+"' valign='center' height='12'>");
			buffer.append("Filler");//market capital value.	
			buffer.append("</td>");
			//buffer.append("</font>");
			buffer.append("</tr>");
			buffer.append("<tr>");
		File TransFileIn= new File(destination+name);
		//File_In = new FileInputStream(TransFileIn);	
		//DataInputStream Data_In = new DataInputStream(File_In);
		Logging.debug("Filename is here"+name);
		DataInputStream Data_In = new DataInputStream(
			      new BufferedInputStream(
			        new FileInputStream(TransFileIn)));
		try 
		{
			
			Logging.debug("Transfileln"+TransFileIn);
			Class.forName("org.postgresql.Driver").newInstance();
			Logging.debug("forName");
	  		connection=DriverManager.getConnection(dbdriver,username,password);
	  		Logging.debug("Connection obj"+connection);
	  		query="insert into mktdata1 (trancode,timestamp,msglength,sectoken,ltprice,bbquant,bbprice,bsquant,bsprice,ttquant,avgtprice,openprice,hiprice,loprice,closeprice,filler)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	  		stmt = connection.prepareStatement(query);
	  		
	  		while(Data_In.available()!=0)
			{
	  			
				//Logging.debug("In for loop "+Data_In.toString());
	  			//Logging.debug("Data_In.readLine() is  "+Data_In.readLine().getBytes().length);
				Logging.debug(" data available after begining "+Data_In.available());
	  			
	  			Trans_c=Short.reverseBytes((short)Data_In.readShort());
				Logging.debug(" data available after first "+Data_In.available());
				Logging.debug(" Trans_c is "+Trans_c);
				mark.setTrans_c(Trans_c);
				buffer.append("<td> ");	
				buffer.append(Trans_c);
				buffer.append(" </td>");
				stmt.setInt(1,Trans_c);
	  			
	  			
	  			Time_stamp = Integer.reverseBytes(Data_In.readInt());
	  			Logging.debug(" data available after second "+Data_In.available());
	  			Logging.debug(" Time_stamp is "+Time_stamp);
	  			long ddt=Math.abs(Time_stamp);
	  			ddt=ddt*1000;
	  			Logging.debug(" ddt is "+ddt);
	  			Date dd=new Date(ddt);
	  			Logging.debug(" date is "+dd);
	  			mark.setTime_stamp(ddt);
	  			buffer.append("<td> ");	
				buffer.append(new Timestamp(ddt));
				buffer.append(" </td>");
	  			stmt.setDate(2,new java.sql.Date(ddt));
	  			
	  			
	  			
	  			Message_l = Short.reverseBytes((short)Data_In.readShort());
	  			Logging.debug(" data available after third "+Data_In.available());
	  			Logging.debug(" Message_l is "+Message_l);
	  			mark.setMessage_l(Message_l);
	  			buffer.append("<td> ");	
				buffer.append(Message_l);
				buffer.append(" </td>");
	  			stmt.setShort(3,Message_l);
	  			
	  			
	  			
	  			Sec_token =Short.reverseBytes((short)Data_In.readShort());
	  			Logging.debug(" data available after forth "+Data_In.available());
	  			Logging.debug(" Sec_token is "+Sec_token);
	  			mark.setSec_token(Sec_token);
	  			buffer.append("<td> ");	
				buffer.append(Sec_token);
				buffer.append(" </td>");
	  			stmt.setShort(4,Sec_token);
	  			
	  			
	  			
	  			Last_tr_price=Integer.reverseBytes(Data_In.readInt());
	  			Last_tr_price/=100;
	  			Logging.debug(" data available after fifth "+Data_In.available());
	  			Logging.debug(" Last_tr_price is "+Last_tr_price);
	  			mark.setLast_tr_price(Last_tr_price);
	  			buffer.append("<td> ");	
				buffer.append(Last_tr_price);
				buffer.append(" </td>");
	  			stmt.setFloat(5,Last_tr_price); 
	  			
	  			
	  			
	  			Best_by_quant= Integer.reverseBytes(Data_In.readInt());
	  			Logging.debug(" data available after sixth "+Data_In.available());
	  			Logging.debug(" Best_by_quant is "+Best_by_quant);
	  			mark.setBest_by_quant(Best_by_quant);
	  			buffer.append("<td> ");	
				buffer.append(Best_by_quant);
				buffer.append(" </td>");
	  			stmt.setLong(6,Best_by_quant);
	  			
	  			
	  			Best_by_price= Integer.reverseBytes(Data_In.readInt());
	  			Best_by_price/=100;
	  			Logging.debug(" data available after seventh "+Data_In.available());
	  			Logging.debug(" Best_by_price is "+Best_by_price);
	  			mark.setBest_by_price(Best_by_price);
	  			buffer.append("<td> ");	
				buffer.append(Best_by_price);
				buffer.append(" </td>");
	  			stmt.setFloat(7,Best_by_price);
	  			
	  			
	  			
	  			Best_sell_quant= Integer.reverseBytes(Data_In.readInt());
	  			Logging.debug(" data available after eighth "+Data_In.available());
	  			Logging.debug(" Best_sell_quant is "+Best_sell_quant);
	  			mark.setBest_sell_quant(Best_sell_quant);
	  			buffer.append("<td> ");	
				buffer.append(Best_sell_quant);
				buffer.append(" </td>");
	  			stmt.setLong(8,Best_sell_quant);
	  			
	  			
	  			
	  			Best_sell_price= Integer.reverseBytes(Data_In.readInt());
	  			Best_sell_price/=100;
	  			Logging.debug(" data available after nineth "+Data_In.available());
	  			Logging.debug(" Best_sell_price is "+Best_sell_price);
	  			mark.setBest_sell_price(Best_sell_price);
	  			buffer.append("<td> ");	
				buffer.append(Best_sell_price);
				buffer.append(" </td>");
	  			stmt.setFloat(9,Best_sell_price);
	  			
	  			
	  			
	  			Total_traded_quant= Integer.reverseBytes(Data_In.readInt());
	  			Logging.debug(" data available after tenth "+Data_In.available());
	  			Logging.debug(" Total_traded_quant is "+Total_traded_quant);
	  			mark.setTotal_traded_quant(Total_traded_quant);
	  			buffer.append("<td> ");	
				buffer.append(Total_traded_quant);
				buffer.append(" </td>");
	  			stmt.setLong(10,Total_traded_quant);
	  			  
	  			
	  			
	  			Avg_trad_price= Integer.reverseBytes(Data_In.readInt());
	  			Avg_trad_price/=100;
	  			Logging.debug(" data available after eleventh "+Data_In.available());
	  			Logging.debug(" Avg_trad_price is "+Avg_trad_price);
	  			mark.setAvg_trad_price(Avg_trad_price);
	  			buffer.append("<td> ");	
				buffer.append(Avg_trad_price);
				buffer.append(" </td>");
	  			stmt.setFloat(11,Avg_trad_price);
	  			
	  			
	  			
	  			op=Integer.reverseBytes(Data_In.readInt());
	  			op/=100;
	  			Logging.debug(" data available after 12th "+Data_In.available());
	  			Logging.debug(" op is "+op);
	  			mark.setOp(op);
	  			buffer.append("<td> ");	
				buffer.append(op);
				buffer.append(" </td>");
	  			stmt.setFloat(12,op);
	  			
	  			
	  			
	  			hp= Integer.reverseBytes(Data_In.readInt());
	  			hp/=100;
	  			Logging.debug(" data available after 13th "+Data_In.available());
	  			Logging.debug(" hp is "+hp);
	  			mark.setHp(hp);
	  			buffer.append("<td> ");	
				buffer.append(hp);
				buffer.append(" </td>");
	  			stmt.setFloat(13,hp);
	  			
	  			
	  			
	  			lp= Integer.reverseBytes(Data_In.readInt());
	  			lp/=100;
	  			Logging.debug(" data available after 14th "+Data_In.available());
	  			Logging.debug(" lp is "+lp);
	  			mark.setLp(lp);
	  			buffer.append("<td> ");	
				buffer.append(lp);
				buffer.append(" </td>");
	  			stmt.setFloat(14,lp);
	  			
	  			
	  			
	  			cp= Integer.reverseBytes(Data_In.readInt());
	  			cp/=100;
	  			Logging.debug(" data available after 15th "+Data_In.available());
	  			Logging.debug(" cp is "+cp);
	  			mark.setCp(cp);
	  			buffer.append("<td> ");	
				buffer.append(cp);
				buffer.append(" </td>");
	  			stmt.setFloat(15,cp);
	  			
	  			
	  			
	  			filler= Integer.reverseBytes(Data_In.readInt());
	  			Logging.debug(" data available after 16th "+Data_In.available());
	  			Logging.debug(" filler is "+filler);
	  			mark.setFiller(filler);
	  			buffer.append("<td> ");	
				buffer.append(filler);
				buffer.append(" </td>");
	  			stmt.setLong(16,filler);
	  			
	  			stmt.executeUpdate();
	  			Logging.debug("after query");
				Logging.debug("Trans code ="+ Trans_c+","+"Time Stamp ="+ Time_stamp+","+"Message lenght ="+ Message_l+","+"Security token="+Sec_token+","+"Last traded price="+Last_tr_price+","+"Best Buy quantity="+Best_by_quant+","+"Best buy price="+Best_by_price+","+"Best sell quant="+Best_sell_quant+","+" Best sell price="+Best_sell_price+","+"Total traded quantity="+Total_traded_quant+","+"Avg traded price="+Avg_trad_price+","+" Open price="+op+","+"High price="+hp+","+" Low price="+lp+","+"Close price="+cp+","+"Filler="+filler+"");
				//table.put(mark.getSec_token(),mark);
			}
	  		
			Logging.debug("completed after puting to Hashtable***********  ");
			buffer.append("</tr>");
	  		Logging.debug("End Of File");
	  		j++;	
	  		//Thread.sleep(64500000);	
		}//end of inner try
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
		//thr.sleep(1000);
		//}//end of if
		
		//else{ j=j;thr.sleep(500);}/*end of else*/
		
		
		//end of while
		//}/*end of outer try*/
		//catch (InterruptedException e){Logging.debug("Interrupted");}
		
	}//end of mktdmp
	public void finalize()
	{
		try
		{
			if(File_In!=null)
				File_In.close();
			
			
		}
			catch(Exception ee){Logging.debug(ee.getMessage());}
	}
}//end of MktThrDB
