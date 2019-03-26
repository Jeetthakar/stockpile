/*
 * Created on Jan 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.masters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * @author rahul
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContinentsAction extends Action 
{Logger Logging = Logger.getLogger(ContinentsAction.class);
	public static final String FORWARD_start = "success";
	Connect connect = ConnectInit.getConnect();
	//Connection con;
	/**
	 * After the executon of update or delete method the all variables are reset to null.
	 *   
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		Continents code = (Continents)form;
		String update = code.getUpdate();
		String new1   = code.getNew1();
		String name = code.getName();
		String ids = code.getId_check();
		if(update != null)
		{
			updateData(ids,name);
			code.setUpdate(null);
			code.setNew1(null);
			code.setIdform1(null);
			code.setIdname(null);
			Continents.setCon_flag("U");
		}
		else if(new1 != null)
		{
			submitData(name);
			code.setNew1(null);
			code.setIdform1(null);
			code.setUpdate(null);
			Continents.setCon_flag("N");
		}
		
		return mapping.findForward(FORWARD_start);
	}
	
	public void submitData(String stname)
	{
		//Connection Changed From static to local by P.Bhende on 02/08/06
		Connection connection = null;
		PreparedStatement pseq,psub;
		ResultSet seqno;
		int curr_seq = 0;
		int r_value = 0;
		
			try
			{
				if(connection ==null){
					connection = connect.getdbConnection();
				}
				String seqQuery = "select nextval('continent_id')";
				pseq = connection.prepareStatement(seqQuery);
				seqno = pseq.executeQuery();
			    while(seqno.next())
			    {
			    	curr_seq = Integer.parseInt(seqno.getString(1));
			    	Logging.debug("Present Seq No. "+curr_seq);
			    }
			   psub = connection.prepareStatement(ConnectInit.queries.getProperty("insert_continents"));
				psub.setInt(1,curr_seq);
				psub.setString(2,stname);
				psub.executeUpdate();
				r_value = 1;
			}catch (Exception et)
			{
				Logging.error("Error :"+et);
			}finally{
				try{if(connection!=null)
					connection.close();
				}catch(Exception ee){
					Logging.error(" Error : Unable to close Connection "+ee.getMessage());
				}
			}
		
	}
	
	public int checkData(String name)
	{
		Connection connection = null;
		PreparedStatement checkcon;
		ResultSet rs1;
		int ans=0;
		String nm1 = null;
		
		Logging.debug("Inside . " +name);
		try
		{   if(connection == null){
			connection = connect.getdbConnection();			
		   }
			checkcon = connection.prepareStatement(ConnectInit.queries.getProperty("check_continent_name"));
			checkcon.setString(1,name);
			rs1 = checkcon.executeQuery();
			while(rs1.next())
			{
				nm1 = rs1.getString(1);
			}
			rs1.close();
			if(nm1 != null && nm1.equalsIgnoreCase(name))
			{
				ans = 1;
			}
		}catch(Exception e)
		{
			Logging.error("Error check() :"+e);
		}finally{
			try{if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
		return ans;
	}
	
	public void updateData(String id,String stname)
	{
		//Connection from Static to Local by P.Bhende on 02/08/06
		Connection connection = null;
		int id_int = Integer.parseInt(id);
	    PreparedStatement pseq,psub;
		ResultSet seqno;
		int r_value = 0;
		
			try
			{   if(connection ==null){
				connection = connect.getdbConnection();
			   }
			    psub = connection.prepareStatement(ConnectInit.queries.getProperty("update_continents"));
				psub.setString(1,stname);
				psub.setInt(2,id_int);
				psub.executeUpdate();
			}catch (Exception et)
			{
				Logging.error("Error :"+et);
			}finally{
				try{if(connection!=null)
					connection.close();
				}catch(Exception ee){
					Logging.error(" Error : Unable to close Connection "+ee.getMessage());
				}
			}
	    }
    }
