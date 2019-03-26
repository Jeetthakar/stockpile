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

public class UseraccountinfoForm extends ActionForm  {
	Logger Logging = Logger.getLogger(UseraccountinfoForm.class);
private String subname;
private String subid;
private String subdate;
private String validfor;
private String validity;
private String charges;
private	String stock;
private	Collection useraccountinfo;
private int[] subscriprion_id_list;	
private String orderid;
private String user_id;
private String indexname;
private String path;
private String role_id;
private String fromdate;
private String todate;
public void setUseraccountinfo(Collection useraccountinfo) {
	this.useraccountinfo = useraccountinfo;
}
public int[] getSubscriprion_id_list() {
	return subscriprion_id_list;
}
public void setSubscriprion_id_list(int[] subscriprion_id_list) {
	this.subscriprion_id_list = subscriprion_id_list;
}
public String getCharges() {
	return charges;
}
public void setCharges(String charges) {
	this.charges = charges;
}
public String getSubdate() {
	return subdate;
}
public void setSubdate(String subdate) {
	this.subdate = subdate;
}
public String getSubid() {
	return subid;
}
public void setSubid(String subid) {
	this.subid = subid;
}
public String getSubname() {
	return subname;
}
public void setSubname(String subname) {
	this.subname = subname;
}
public String getValidfor() {
	return validfor;
}
public void setValidfor(String validfor) {
	this.validfor = validfor;
}

public String getValidity() {
	return validity;
}
public void setValidity(String validity) {
	this.validity = validity;
}

private 		int[]     order_id_list;





public Collection getUseraccountinfo() {

	Collection col = null;
	Connect con =ConnectInit.getConnect();
	Connection conn=con.getdbConnection();
	try {
	
	Vector vec = new Vector(10);	
	
	PreparedStatement pst;
	ResultSet rs=null;
//	Properties queries=null;
//	queries=ConnectInit.queries;
	String Query = ConnectInit.queries.getProperty("user_account_info");
	//select us.subscription_id,us.sub_date,sm.duration,sm.textduration,sm.charges,sm.sub_name,sm.no_of_stock  from user_subscription us,subscription_master sm  where us.subscription_id=sm.subscription_id and  us.user_id=?
	pst=conn.prepareStatement(Query);
	pst.setString(1,this.user_id);
	rs=pst.executeQuery();	
	
	 while(rs.next())
	 {
		 
		 UseraccountinfoForm t=new UseraccountinfoForm();
		 
		 t.setSubid(rs.getString(1));
		 t.setSubdate(rs.getString(2));
		 t.setValidity(rs.getString(4));
		 t.setCharges(rs.getString(5));
		 t.setSubname(rs.getString(6));
		 t.setStock(rs.getString(7));
	//this is for those user whose index is not created
		 if(this.role_id.equals("75")  &&  rs.getString(9)==null)
		 {
			 t.setPath("../pages/NewIndexDefineRegister.jsp");
			 t.setIndexname("Create Index");
		 }
		 else{	
		    if(this.role_id.equals("76") &&  rs.getString(9)==null)
			 {
				 
				 t.setPath("../pages/NewIndexDefine.jsp");
				 t.setIndexname("Create Index");
			 }	 
			 
		 }
		 
		 if(rs.getString(9)!=null)
		 {
				 t.setIndexname(rs.getString(9) );
		        String p="../pages/reports/IndexComposeS.jsp?index=".trim().concat(rs.getString(10)).trim().concat("&compute=yes&ajax1=yes'");
				 t.setPath(p.trim());
                  		
		 }
		 t.setOrderid(rs.getString(8)) ;
		
		 //for registor
		 //here we added the been in key value pair(subid-1 ,its been) so that in LoadCollection action we
		//can retrive It for getting subscription id
		 vec.add(t);
		 t=null;
		 
	 }
		
	// col=vec;
	 col= vec;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
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

public String getStock() {
	return stock;
}
public void setStock(String stock) {
	this.stock = stock;
}
public String getUser_id() {
	return user_id;
}
public void setUser_id(String user_id) {
	this.user_id = user_id;
}
public String getIndexname() {
	return indexname;
}
public void setIndexname(String indexname) {
	this.indexname = indexname;
}
public String getOrderid() {
	return orderid;
}
public void setOrderid(String orderid) {
	this.orderid = orderid;
}
public int[] getOrder_id_list() {
	return order_id_list;
}
public void setOrder_id_list(int[] order_id_list) {
	this.order_id_list = order_id_list;
}
public String getPath() {
	return path;
}
public void setPath(String path) {
	this.path = path;
}
public String getRole_id() {
	return role_id;
}
public void setRole_id(String role_id) {
	this.role_id = role_id;
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
	
}
