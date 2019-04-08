/*
 * Created on Dec 30, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.masters;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.log4j.Logger;

import app.Connect;

import com.harrier.initializeation.ConnectInit;
/**
 * @author Naresh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class frtoCurrrency 
{
	Logger Logging = Logger.getLogger(frtoCurrrency.class);
	ResultSet rst,rst1;
	public static Vector currency_list,currency_list1;
	String curr_sel_1,curr_sel_2;
	
	String forex_name,desc;
	static String sta_name,sta_desc;
	

	/**
	 * @return Returns the desc.
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc The desc to set.
	 */
	public void setDesc(String desc) {
		this.desc = desc;
		sta_desc =desc;
	}
	/**
	 * @return Returns the forex_name.
	 */
	public String getForex_name() {
		return forex_name;
	}
	/**
	 * @param forex_name The forex_name to set.
	 */
	public void setForex_name(String forex_name) {
		this.forex_name = forex_name;
		sta_name=forex_name;
	}
	
	/*
	public String getCurr_sel_1() {
		return curr_sel_1;
	}
	
	public void setCurr_sel_1(String curr_sel_1) {
		Logging.debug("Inside set_curr"+curr_sel_1);
		this.curr_sel_1 = curr_sel_1;
		
	}
	
	public String getCurr_sel_2() {
		return curr_sel_2;
	}
	
	public void setCurr_sel_2(String curr_sel_2) {
		this.curr_sel_2 = curr_sel_2;
		
	}
	*/
	/**
	 * @return Returns the currency_list.
	 */
	public Vector getCurrency_list() {
		
		return currency_list;
	}
	/**
	 * @param currency_list The currency_list to set.
	 */
	public void setCurrency_list() 
	{
		Connection connection = null;
	//	app.Connect con=new app.Connect();
		Connect con = ConnectInit.getConnect();
		currency_list = new Vector();
		
		int i = 0;
		try 
		{
			if(connection==null)
			{
				connection = con.getdbConnection();
			}
			rst = getCourrencyList(connection);
			while (rst.next())
			{				
				currency_list.add(i, rst.getString(1));
				i++;
				currency_list.add(i,(String)rst.getString(2));
				i++;
		    }
		} catch (SQLException sqlexp)
		  {
			  Logging.error(sqlexp+"");
		  }finally{
			try{
				if(connection!=null)
				 connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
		
	}
	
	
	public ResultSet getCourrencyList(Connection connection)
	{
		PreparedStatement pst;
		ResultSet rset;
		try
		{
			String Query ="select cur.currency_id,cur.currency_name ||'-'||con.country_name " +
							"from currencies cur,countries con " +
								"where cur.currency_id = con.currency_id " +
									"order by cur.currency_name ";
		
			pst = connection.prepareStatement(Query);
			rset = pst.executeQuery();
			return rset;
			
		}catch (Exception e)
		{
			Logging.error("Error :"+e);
		}
		return rst1;
	}
	
	public Vector getCurrency_list1() {
		
		return currency_list1;
	}
	/**
	 * @param currency_list The currency_list to set.
	 */
	public void setCurrency_list1() 
	{
	//	app.Connect con=new app.Connect();
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		currency_list = new Vector();
		
		int i = 0;
		try 
		{
			if(Connect.con==null)
			{
			 connection = con.getdbConnection();
			}
			rst = getCourrencyList(connection);
			while (rst.next())
			{				
				//Logging.debug(rst.getString(1));
				currency_list.add(i, rst.getString(1));
				i++;
				currency_list.add(i,(String)rst.getString(2));
				i++;
		    }
			rst.close();
			
		} catch (SQLException sqlexp)
		  {
			 Logging.error(sqlexp+"");
		  }finally{
			try{
				if(connection!=null)
				 connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
		//this.currency_list = currency_list;
	}
	
	public String storeData(String name1,String desc1,String var,String var1)
	{
		String name;
		String result= null;
		int lst_1 = Integer.parseInt(var);
		int lst_2 = Integer.parseInt(var1);
		Connection connection = null;
		ResultSet seqno;
		PreparedStatement pstdata,pstseq;
		Logging.debug("INSIDE STRDATA BFR TRY.");
		Logging.debug("name1 "+name1);
		Logging.debug("desc1 "+desc1);
		Logging.debug("var "+lst_1);
		Logging.debug("var1 "+lst_2);
		Connect con = ConnectInit.getConnect();
		try
		{
			if(connection == null){
				connection = con.getdbConnection();
			}
			String seqQuery = "select nextval('from_to_currency_id')";
			pstseq = connection.prepareStatement(seqQuery);
			
			int curr_seq = 0;		
			seqno = pstseq.executeQuery();
		    while(seqno.next())
		    {
		    	curr_seq = Integer.parseInt(seqno.getString(1));
				Logging.debug("Present Seq No. "+curr_seq);
		    }
		    
			String Query ="insert into from_to_currency(from_to_currency_id, " +
							"forex_code,to_currency_id,from_currency_id,description)  " +
								" values("+curr_seq+",'"+name1+"',"+lst_2+","+lst_1+",'"+desc1+"')"; 
									
			Logging.debug("Query"+Query);
			pstdata = Connect.con.prepareStatement(Query);
			pstdata.executeUpdate();
			result = "ok";
			return result;
			
		}catch (Exception e)
		{
			Logging.debug("Error :"+e);
		}finally{
			try{
				if(connection!=null)
				 connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
		return null;
		
		//return "ok"; // Uncomment this line for testing only.
	}
	
}
