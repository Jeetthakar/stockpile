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

import app.NewusersAction;
/**
 * Class MktThrDB is used to store  Mkt file data into database.The file is generated after every 5 minutes 
 * at NSE server and downloaded through File transfer Protocol at our local directory.The file is read from 
 * local directory and it's contents are stored fieldwise in our postgresql database.
 * 
 */
class IndThrDB
{
	Logger Logging = Logger.getLogger(IndThrDB.class);
	String name;
	Thread thr;
	String destination;
	String query=null;
	String username = "sudhir";
	String password ="panchware";
	String dbdriver="jdbc:postgresql://192.168.0.22:5432/income";
	FileInputStream File_In;
	IndThrDB(String fn,String dest)
	{
		name=fn.trim();
		destination=dest;
		Logging.debug("Name of file "+name);
		inddmp();
	}

	public void  inddmp()
	{
		Logging.debug("In while mkt run");
		//try{
		int j=1;
		Logging.debug("In while loooooop");
		short Trans_c=0;
		int Time_stamp=0;
		short Message_l=0;
		short Index_Token=0;
		float curr_ind_val=0.0F;
		float open_ind_val=0.0F;
		float high_ind_val=0.0F;
		float low_ind_val=0.0F;
		float close_ind_val=0.0F;
		float per_change=0.0F;
		int filler=0;
		Connection connection=null;
		PreparedStatement  stmt=null;
		try
		{
		Logging.debug("Filename is here"+name);
		File TransFileIn= new File(destination+name);
		//File_In = new FileInputStream(TransFileIn);
		//DataInputStream Data_In = new DataInputStream(File_In);	
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
	  		query="insert into inddata (trancode,timestamp,msglength,index_token,curr_indx_value,high_indx_value,low_indx_value,perc_change_in_index,filler)values(?,?,?,?,?,?,?,?,?)";
	  		stmt = connection.prepareStatement(query);
	  		
	  		while(Data_In.available()!=0)
			{
	  			Logging.debug(" data available after begining "+Data_In.available());
	  			//System.out.println("In for loop "+i);
	  			if(Data_In.available()!=0){
	  			     Trans_c=Short.reverseBytes((short)Data_In.readShort());
	  			   Logging.debug(" data available after first "+Data_In.available());
	  			 Logging.debug(Trans_c);
	  			     stmt.setShort(1,Trans_c);
	  			   }
	  			
	  			Logging.debug("After Transc");
	  			if(Data_In.available()!=0)
	  			{ 
	  				Time_stamp = Integer.reverseBytes(Data_In.readInt());
	  				Logging.debug(" data available after second "+Data_In.available());
	  				long ddt=Math.abs(Time_stamp);
		  			ddt=ddt*1000;
		  			Logging.debug(" ddt is "+ddt);
		  			Date dd=new Date(ddt);
		  			Logging.debug(" date is "+dd);
		  			stmt.setTimestamp(2,new Timestamp(ddt));
	  			   //stmt.setLong(2,Time_stamp);
	  			   }
	  			
	  			Logging.debug("After Time");
	  			if(Data_In.available()!=0){
	  			   Message_l = Short.reverseBytes((short)Data_In.readShort());
		  		   Logging.debug(" data available after third "+Data_In.available());
		  		 Logging.debug(Message_l);
	  			   stmt.setShort(3,Message_l);
	  			 }
	  			
	  			Logging.debug("After Messg");
	  			if(Data_In.available()!=0){
	  			   Index_Token =Short.reverseBytes((short)Data_In.readShort());
		  		   Logging.debug(" data available after 4th "+Data_In.available());
		  		 Logging.debug(Index_Token);
	  			   stmt.setShort(4,Index_Token);
	  			 }
	  			
	  			Logging.debug("After indextoken");
	  			if(Data_In.available()!=0){
	  				curr_ind_val=Integer.reverseBytes(Data_In.readInt());
	  	  			curr_ind_val /= 100;
	  			   Logging.debug(" data available after 5th "+Data_In.available());
	  			 Logging.debug(curr_ind_val);
	  			   stmt.setFloat(5,curr_ind_val);
	  			 }
	  			
	  			Logging.debug("After curr_indx");
	  			/*if(Data_In.available()!=0){
		  			   open_indx_value=Integer.reverseBytes(Data_In.readInt());
		  			   Logging.debug(" data available after 6th "+Data_In.available());
		  			 Logging.debug(open_indx_value);
		  			   //stmt.setLong(5,Curr_Indx_Value);
		  			 }*/
	  			if(Data_In.available()!=0){
	  				high_ind_val= Integer.reverseBytes(Data_In.readInt());
	  	  			high_ind_val/=100;
	  			   Logging.debug(" data available after 7th "+Data_In.available());
	  			 Logging.debug(high_ind_val);
	  			   stmt.setFloat(6,high_ind_val);
	  			}
	  			
	  			Logging.debug("After HighIndx");
	  			if(Data_In.available()!=0){
	  				low_ind_val= Integer.reverseBytes(Data_In.readInt());
	  	  			low_ind_val/=100;
	  			   Logging.debug(" data available after 8th "+Data_In.available());
	  			 Logging.debug(low_ind_val);
	  			   stmt.setFloat(7,low_ind_val);
	  			 }
	  			
	  			Logging.debug("After Low indx");
	  			/*if(Data_In.available()!=0){
	  				clo_indx_value= Integer.reverseBytes(Data_In.readInt());
		  			   Logging.debug(" data available after 9th "+Data_In.available());
		  			   //stmt.setLong(7,Low_indx_value);
		  			 Logging.debug(clo_indx_value);
		  			 }*/
	  			if(Data_In.available()!=0){
	  				per_change= Integer.reverseBytes(Data_In.readInt());
	  	  			per_change/=100;
	  			   Logging.debug(" data available after 10th "+Data_In.available());
	  			 Logging.debug(per_change);
	  			   stmt.setFloat(8,per_change);
	  			 }
	  			
	  			Logging.debug("After change indx");
	  			if(Data_In.available()!=0){
	  			   filler= Integer.reverseBytes(Data_In.readInt());
	  			   Logging.debug(" data available after 11th "+Data_In.available());
	  			 Logging.debug(filler);
	  			   stmt.setLong(9,filler);
	  			 }
	  			Logging.debug(" data available after complet "+Data_In.available());
	  			Logging.debug("After filler");
	  			//stmt.setShort(1,Trans_c);
	  			//stmt.setLong(2,Time_stamp);
	  			//stmt.setShort(3,Message_l);
	  			//stmt.setShort(4,Index_Token);
	  			//stmt.setLong(5,Curr_Indx_Value);
	  			//stmt.setLong(6,High_indx_value);
	  			//stmt.setLong(7,Low_indx_value);
	  			//stmt.setLong(8,Perc_Chang_in_indx);
	  			//stmt.setLong(9,filler);
				stmt.executeUpdate();
					
				Logging.debug("after query");
				//Logging.debug("Trans code ="+ Trans_c+","+"Time Stamp ="+ Time_stamp+","+"Message lenght ="+ Message_l+","+" Index token="+Index_Token+","+"Current Index Value="+Curr_Indx_Value+","+"High Index value="+High_indx_value+","+"Low Index Value="+Low_indx_value+","+"Percentage  Change In Index="+Perc_Chang_in_indx+","+" Filler="+filler+"");
				
			}
					
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
