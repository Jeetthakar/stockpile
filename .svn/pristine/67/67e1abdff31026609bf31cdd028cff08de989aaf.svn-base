package subscription.form;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

public class CancelForm extends ActionForm {
	Logger Logging = Logger.getLogger(CancelForm.class);
	private String username; 
	private String orderid ;
	private String userid ;
	private String canceldate ;
	private	Collection useraccountinfo;
	private String fromdate;
	private String todate;
	private String method;
	private String subname;
	
	private int[] order_id_list;
	
	public int[] getOrder_id_list() {
		return order_id_list;
	}
	public void setOrder_id_list(int[] order_id_list) {
		this.order_id_list = order_id_list;
	}
	public String getFromdate() {
		return fromdate;
	}
	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}
	public String getTodate() {
		return todate;
	}
	public void setTodate(String todate) {
		this.todate = todate;
	}
	
	
	public Collection getUseraccountinfo() {
		
		
		
		//Logging.debug();
		Collection col = null;
		Connect con =ConnectInit.getConnect();
		Connection conn=con.getdbConnection();
		try {
		
		Vector vec = new Vector(10);	
		
		PreparedStatement pst;
		ResultSet rs=null;
	//	Properties queries=null;
	//	queries=ConnectInit.queries;
		//String Query = queries.getProperty("user_account_info");
		
		String Query="SELECT user_name, order_id, user_id, cancel_date   FROM cancel_subscription_data where to_date(cancel_date,'dd-mm-yyyy')  between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy') order by to_date(cancel_date,'dd-mm-yyyy')";
		//select us.subscription_id,us.sub_date,sm.duration,sm.textduration,sm.charges,sm.sub_name,sm.no_of_stock  from user_subscription us,subscription_master sm  where us.subscription_id=sm.subscription_id and  us.user_id=?
		pst=conn.prepareStatement(Query);
		pst.setString(1,this.fromdate);
		pst.setString(2,this.todate);
		rs=pst.executeQuery();	
		
		 while(rs.next())
		 {
			 
			 CancelForm t=new CancelForm();
			t.setUsername(rs.getString(1) );
			t.setOrderid(rs.getString(2) );
			t.setUserid(rs.getString(3));
			t.setCanceldate(rs.getString(4) );
			vec.add(t);
			 t=null;
			 
		 }
			
		// col=vec;
		 col= vec;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
			Logging.debug(e);
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				Logging.debug(e);
			}
		}
		
		return col;
		
		
		
	}
	public void setUseraccountinfo(Collection useraccountinfo) {
		this.useraccountinfo = useraccountinfo;
	}
	public String getCanceldate() {
		return canceldate;
	}
	public void setCanceldate(String canceldate) {
		this.canceldate = canceldate;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getSubname() {
		return subname;
	}
	public void setSubname(String subname) {
		this.subname = subname;
	}
	
}
