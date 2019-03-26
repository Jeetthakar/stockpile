/*
 * Created on Mar 10, 2006
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
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import app.Connect;

import com.harrier.initializeation.ConnectInit;
/**
 * @author naresh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CorpMasterAction extends Action
{Logger Logging = Logger.getLogger(CorpMasterAction.class);
	public static final String FORWARD_start = "success";
	Connection con=null; 
	Connect connect = ConnectInit.getConnect();
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)
	{
		HttpSession sess_act = request.getSession();
		CorporateActionMaster form1 = (CorporateActionMaster)form;
		String update = form1.getUpdate();
		String new1 = form1.getNew1();
		String name = form1.getName();
		String shname = form1.getShname();
		String desc = form1.getDesc();
		String type_index = form1.getEvent_index();
		String type_stock = form1.getEvent_stock();
		String type = null;
		String id = form1.getId();
		String divisor = form1.getDivisor();
		String price = form1.getPrice();
		if(divisor == null)
		{
			divisor = "n";
		}
		else divisor = "y";
		if(price == null)
		{
			price = "n";
		}
		else price = "y";
		if(type_index != null)
		{
			type = "index event";
		}
		else
			type = "stock event";
		/**
		 * check if user wants to update data.
		 * If yes then call update funtion else check if user wants to add new element.
		 * after update or add reset bean variables.
		 */
		if(update != null)
		{
			int id_int = Integer.parseInt(id);
			submit_update_Data(id_int,name,desc,shname,type,divisor,price);
			sess_act.setAttribute("conf_act","U");
			form1.setUpdate(null);
			form1.setEvent_index(null);
			form1.setEvent_stock(null);
			form1.setId(null);
			form1.setDivisor(null);
			form1.setPrice(null);
		}
		else if(new1 != null)
		{
			submitData(name,desc,shname,type,divisor,price);
			sess_act.setAttribute("conf_act","N");
			form1.setNew1(null);
			form1.setEvent_index(null);
			form1.setEvent_stock(null);
			form1.setId(null);
			form1.setDivisor(null);
			form1.setPrice(null);
		}	
		return mapping.findForward(FORWARD_start);
	}
	
	/**
	 * Ones data is validated, record is updated or added as per the 
	 * action selected by the user.
	 * Appropriate messges are displayed to the user for the action.
	 * 
	 */
	public void submitData(String name2,String desc,String sh_nm,String act_type,String divisor,String price)
	{
		int curr_seq = 0;
		int r_value = 0;
		PreparedStatement pseq,psub;
		ResultSet seqno;
		con=null;
		try
		{
			if(con == null)
			{
				con = connect.getdbConnection();
			}
			int ahead =0;
			try
			{
				String seqQuery = "select nextval('cam_id')";
				pseq = con.prepareStatement(seqQuery);
				seqno = pseq.executeQuery();
			    while(seqno.next())
			    {
			    	curr_seq = Integer.parseInt(seqno.getString(1));
					Logging.debug("Present Seq No. "+curr_seq);
			    }
			    psub = con.prepareStatement(ConnectInit.queries.getProperty("insert_corp_action_master"));
				psub.setInt(1,curr_seq);
				psub.setString(2,name2);
				psub.setString(3,sh_nm);
				psub.setString(4,desc);
				psub.setString(5,act_type);
				psub.setString(6,divisor);
				psub.setString(7,price);
				psub.executeUpdate();
				r_value = 1;
				psub.close();
				seqno.close();
				pseq.close();
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
	
	public void submit_update_Data(int Uid,String Uname,String Udesc,String Ush_nm,String Uact_type,String divisor,String price)
	{
		int r_value = 0;
		PreparedStatement pseq,psub;
		con=null;
		try
		{
			if(con == null)
			{
				con = connect.getdbConnection();
			}
			int ahead =0;
			try
			{
				psub = con.prepareStatement(ConnectInit.queries.getProperty("upd_corp_action_master"));
				psub.setString(1,Uname);
				psub.setString(2,Udesc);
				psub.setString(3,Uact_type);
				psub.setString(4,divisor);
				psub.setString(5,price);
				psub.setInt(6,Uid);
				psub.executeUpdate();
				r_value = 1;
				Logging.debug("Data updated...."+psub);
				psub.close();
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
}
