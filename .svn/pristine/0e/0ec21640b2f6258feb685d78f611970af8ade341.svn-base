/*
 * Created on Apr 5, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.masters;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import app.Connect;

import com.harrier.initializeation.ConnectInit;
/**
 * @author naresh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TimeZone
{
	Logger Logging = Logger.getLogger(TimeZone.class);
	Connection con=null,con1=null;
	Connect connect = ConnectInit.getConnect();
	PreparedStatement psub,pseq,checkcon;
	ResultSet rs1,seqno;
	public void submitData(String stname,String detail)
	{
		int curr_seq = 0;
		int r_value = 0;
		
		con=null;
		try
		{
			if(con == null)
				con = connect.getdbConnection();
			Logging.debug("stname : "+stname);
			try
			{
				String seqQuery = "select nextval('time_zone_id')";
				pseq = con.prepareStatement(seqQuery);
				seqno = pseq.executeQuery();
				while(seqno.next())
				{
					curr_seq = Integer.parseInt(seqno.getString(1));
					Logging.debug("Present Seq No. "+curr_seq);
				}
				String Query1 ="insert into time_zone "+
								" values("+curr_seq+",'"+stname+"','"+detail+"')";
				psub = con.prepareStatement(Query1);
				psub.executeUpdate();
				psub.close();
				seqno.close();
				pseq.close();
				r_value = 1;
			}catch (Exception et)
			{
				Logging.error("Error :"+et);
			}
		}
		finally{
			try{if(con!=null)
				con.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
	}
	
	public int checkData(float name)
	{
		int ans=0;
		float tdiff_1 = 0;
		//float tdiff_2 = name;
		con1=null;
		try
		{
			if(con1 == null)
				con1 = connect.getdbConnection();
			Logging.debug("Inside . " +name);
			try
			{
				String query = "Select time_difference " +
							   " from time_zone " +
							   " where time_difference = "+name+"";
				checkcon = con1.prepareStatement(query);
				rs1 = checkcon.executeQuery();
				while(rs1.next())
				{
					tdiff_1 = rs1.getFloat(1);
					Logging.debug("Cou Name is . " +tdiff_1);
				}
				if(tdiff_1 == name)
				{
					ans = 1;
				}
				rs1.close();
				checkcon.close();
			}catch(Exception e)
			{
				Logging.error("Error check() :"+e);
			}
		}
		finally{
			try{if(con1!=null)
				con1.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
		Logging.debug("Inside Check().");
		return ans;
	}
}
