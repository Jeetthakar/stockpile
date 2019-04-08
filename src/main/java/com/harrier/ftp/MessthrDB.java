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
class MessthrDB
{
	Logger Logging = Logger.getLogger(MessthrDB.class);
	String name;
	Thread thr;
	String destination;
	String query=null;
	String username = "sudhir";
	String password ="panchware";
	String dbdriver="jdbc:postgresql://192.168.0.22:5432/income";
	FileInputStream File_In;
	MessthrDB(String fn,String dest)
	{
		name=fn.trim();
		destination=dest;
		Logging.debug("Name of file "+name);
		messdmp();
	}

	public void  messdmp()
	{
		Logging.debug("In while mkt run");
		//try{
		int j=1;
		Logging.debug("In while loooooop");
		short Trans_c=0;
		int Time_stamp=0;
		short Message_l=0;
		String Message="";
		//String Message1=null;
		//String Message1=null;
		//String Message11=null;
		//char ommess=0;
		//String back="\0";
		Connection connection=null;
		PreparedStatement  stmt=null;
		try
		{
		Logging.debug("Filename is here"+name);
		File TransFileIn= new File(destination+name);
		/*File_In = new FileInputStream(TransFileIn);
		DataInputStream Data_In = new DataInputStream(File_In);	*/
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
	  		query="insert into messdata (trancode,timestamp,msglength,message)values(?,?,?,?)";
	  		stmt = connection.prepareStatement(query);
	  			  			
	  			while(Data_In.available()!=0){
	  				
	  				
	  			     Trans_c=Short.reverseBytes((short)Data_In.readShort());
	  			     Logging.debug(" data available after first "+Data_In.available());
	  			     stmt.setInt(1,Trans_c);
	  			     Logging.debug("After Transc");
	  				
	  				
	  				
	  				
	  				Time_stamp = Integer.reverseBytes(Data_In.readInt());
	  				Logging.debug(" data available after second "+Data_In.available());
		  			Logging.debug(" Time_stamp is "+Time_stamp);
		  			long ddt=Math.abs(Time_stamp);
		  			ddt=ddt*1000;
		  			Logging.debug(" ddt is "+ddt);
		  			Date dd=new Date(ddt);
		  			Logging.debug(" date is "+dd);
		  			stmt.setDate(2,new java.sql.Date(ddt));
	  				
	  				
	  				
	  				
	  			   Message_l = Short.reverseBytes((short)Data_In.readShort());
	  			   Logging.debug(" data available after 3rd "+Data_In.available());
	  			   stmt.setInt(3,Message_l);	  			
	  			   Logging.debug("After Messg");
	  				
	  				
	  				
	  				
					for (int i=0;i<239;i++)
					{
						Message=Message+(char)Data_In.readByte();
						Logging.debug( Message);
						System.out.flush();
					}
					Logging.debug(" data available after 4th "+Data_In.available());
					stmt.setString(4,Message);
					Logging.debug("after Message4");
	  				
	  			
	  		       stmt.executeUpdate();
					
				Logging.debug("after query");
				Logging.debug("Trans code ="+ Trans_c+","+"Time Stamp ="+ Time_stamp+","+"Message lenght ="+ Message_l+","+" Message="+Message+"");
	  			}
			
					
	  		Logging.debug("End Of File");
	  		j++;
	  		Thread.sleep(64500000);	
		//end of inner try
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
