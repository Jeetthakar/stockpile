package subscription.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import subscription.form.subscribeUserForm;
import app.Connect;

import com.harrier.initializeation.ConnectInit;

public class UserDao {
	
	
	
	Logger Logging = Logger.getLogger(UserDao.class);
	 String Encript(String str)
		{
			/**
			 * Encription : The password string is concatenated with the same string again
			 * it is then converted as first half of the string str1 characters are added with 5 to there 
			 * ascii values and rest half string str2 characters are added by 7 in there ascii values.
			 */
			String ret_str= null;
			String str1 = "", str2 =  "" ;
			int num;
			char [] inp_str1 = str.toCharArray();
			char [] inp_str2 = str.toCharArray();
			for(int i= 0 ; i < inp_str1.length; i++)
			{
				num = inp_str1[i] + 5;
				inp_str1[i] = (char)num;
			}
			for(int i= 0 ; i < inp_str1.length; i++)
			{
				str1 = str1 + inp_str1[i]; 
			}
			for(int i= 0 ; i < inp_str2.length; i++)
			{
				num = inp_str2[i] + 7;
				inp_str2[i] = (char)num;
			}
			for(int i= 0 ; i < inp_str2.length; i++)
			{
				str2 = str2 + inp_str2[i]; 
			}
			ret_str = str1 + str2;
			return ret_str;
		}
	
	
	
	public  synchronized String  saveUser(subscribeUserForm uform )  
	{
	//	Properties queries=null;
		PreparedStatement pst=null;	
		Connection con=null;
		String status="success";
		status=CheckUser(uform);
		Connect c=ConnectInit.getConnect();
		con=c.getdbConnection();
		if(status.equalsIgnoreCase("success"))
		{
			
			
		
		try {
			 
		//	queries=ConnectInit.queries;
			String Query =ConnectInit.queries.getProperty("insert_user");
			  //enter encripted password
			String password =Encript( uform.getPassword().trim());
			pst = con.prepareStatement(Query);
			pst.setString(1, uform.getUserid().trim());
			pst.setString(2, uform.getUserid().trim());
			pst.setString(3, password.trim()  );
			pst.setString(4, uform.getFirstname().trim()   );
			pst.setString(5, uform.getLastname().trim()   );
			pst.setString(6, uform.getContactno().trim()  );
			pst.setString(7, uform.getAdd1().trim().concat(" ".concat(uform.getAdd2().trim()))  );
			pst.setString(8, uform.getCity().trim()  );
			pst.setString(9, uform.getZipcode().trim()  );
			pst.setString(10, uform.getState().trim() );
			pst.setString(11, uform.getCountry().trim() );
			pst.setString(12,"DisplayIndexS");
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			status="datafail";
			Logging.debug("subscription.form.subscribeUserForm.saveUser Fail due to.." + e);
			
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				Logging.debug(e);
			}
		}
		
		}
		
		
		return status;
		
		
	}
	
	public synchronized  String CheckUser(subscribeUserForm uform )
	{
		
		PreparedStatement pst=null;	
		Connection con=null;
		String status="success";
		ResultSet rs=null;
	//	Properties queries=null;
		try {
			Connect c=ConnectInit.getConnect();
			con=c.getdbConnection(); 
		//	queries=ConnectInit.queries;
			String Query = ConnectInit.queries.getProperty("check_user");
			
			pst = con.prepareStatement(Query);
			pst.setString(1, uform.getUserid().trim());
			rs=pst.executeQuery();
			String user_id=null;
			int count=0;
			if(rs.next() )
			{
				count++;
				user_id=rs.getString(1);
				Logging.debug(rs.getString(1));
			}
			if(count>0)
			{
				status="exist";
			}
			Logging.debug("No of rows"+count);
			
		} catch (SQLException e) {
			status="datafail";
			Logging.debug("subscription.form.subscribeUserForm.saveUser Fail due to.." + e);
			
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				Logging.debug(e);
			}
		}
		
		
		return status;
		
		
		
		
	}
	
	public synchronized  String ConfirmUser(subscribeUserForm uform )
	{
		//here we check password against the email id and if found correct we confirm user as well as 
		//add it with free subscription id 
		
		PreparedStatement pst=null;	
		Connection con=null;
		String status="success";
		ResultSet rs=null;
		
		   
		try {
			Connect c=ConnectInit.getConnect();
			con=c.getdbConnection(); 
		//	Properties queries=null;
			String password= Encript(uform.getPassword());
		//	queries=ConnectInit.queries;
			String Query = ConnectInit.queries.getProperty("get_userid");
			pst = con.prepareStatement(Query);
			pst.setString(1, uform.getUserid());
			pst.setString(2,password);
			rs=pst.executeQuery();
			String user_id=null;
			int count=0;
			if(rs.next() )
			{
				user_id=rs.getString(1);
				count++;
				
			}
			
			if(count==0)
			{
				status="noexist";
			}
			else
			{
			pst=null;
			rs=null;
			con.setAutoCommit(false );
		    Query = ConnectInit.queries.getProperty("confirm_user");
			pst=con.prepareStatement(Query);
			pst.setString(1,"y");
			pst.setString(2,uform.getUserid());
		    pst.setString(3,password);
		   pst.executeUpdate();	
		  //  pst.execute();
		    ///////////add a free subscription/////////////
			//chandra
		    SubscriptionDao s =new SubscriptionDao();
		    status=s.AdduserSubscription(user_id ,"1",con );

		    if(status.equalsIgnoreCase("success"))
		    {
		    ///////////add a role for free user/////////////
		//    queries=ConnectInit.queries;
		    Query = ConnectInit.queries.getProperty("get_roleid");
		    pst=con.prepareStatement(Query);
		    rs=pst.executeQuery(Query) ;
		    String roleid=null;
		    while(rs.next() )
			{
		    	roleid=rs.getString(1) ;
				
			}
		    ///////////add a role/////////////
		  
		    Query = ConnectInit.queries.getProperty("check_user");
		    pst=con.prepareStatement(Query);
		    pst.setString(1,uform.getUserid());
		    rs=pst.executeQuery() ;
		    String userid=null;
		    
		    while(rs.next() )
			{
		    	userid=rs.getString(1) ;
				
			}
		    Query = ConnectInit.queries.getProperty("insert_userrole");
		    pst=con.prepareStatement(Query);
		    pst.setString(1,userid);
		    pst.setString(2,roleid);
		    pst.executeUpdate();
		    
		    
			con.setAutoCommit(true);
			pst.close() ;
		    rs.close() ;
			}
			
			}	//pst.close();
		//	rs.close();
			
		} catch (SQLException e) {
			status="datafail";
			Logging.debug("subscription.form.subscribeUserForm.saveUser Fail due to.." + e);
			
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
				Logging.debug(e);
			}
		}
		
		
		return status;
		
		
		
		
	}
	
	
	/**************LOGIN ACTION**********/
	public synchronized String UserLogin(subscribeUserForm uform ,HttpSession session )
	{
		
		PreparedStatement pst=null;	
		Connection con=null;
		String status="fail";
		ResultSet rs=null;
	//	Properties queries=null;
		try {
			Connect c=ConnectInit.getConnect();
			con=c.getdbConnection(); 
		//	queries=ConnectInit.queries;
			String Query = ConnectInit.queries.getProperty("user_login");
			String password= Encript(uform.getPassword());
		//	String Query="select user_id from users  r where r.username=? and r.password=?" ;
			pst = con.prepareStatement(Query);
			pst.setString(1, uform.getUserid().trim());
			pst.setString(2, password);
			rs=pst.executeQuery();
			
			int count=0;
			if(rs.next() )
			{
				String user_id=rs.getString(1);
//				session.setAttribute("user_id",user_id );
				count++;
				
				
			}
			if(count==1)
			{
				status="success";
			}
			if(count==0)
			{
				status="noexist";
			}
			
			Logging.debug("No of rows"+count);
			
		} catch (SQLException e) {
			status="datafail";
			Logging.debug("subscription.form.subscribeUserForm.UserLogin Fail due to.." + e);
			
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				Logging.debug(e);
			}
		}
		return status;
		
	}
	
	/*************************/
	
	public synchronized  String userRole(String userid )
	{
		
		PreparedStatement pst=null;	
		Connection con=null;
		String status="success";
		ResultSet rs=null;
	//	Properties queries=null;
		String user_role=null;
		try {
			Connect c=ConnectInit.getConnect();
			con=c.getdbConnection(); 
	//		queries=ConnectInit.queries;
			String Query = ConnectInit.queries.getProperty("user_role");
			
			pst = con.prepareStatement(Query);
			pst.setString(1,userid);
			rs=pst.executeQuery();
			
			int count=0;
			if(rs.next() )
			{
				count++;
				user_role=rs.getString(1);
				
			}
			
			
			
		} catch (SQLException e) {
			status="datafail";
			Logging.debug("..saveUser Fail due to.." + e);
			
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				Logging.debug(e);
			}
		}
		
		
		return user_role;
		
		
		
		
	}
	
	public String getPassword(String username)
 {
	 
	 PreparedStatement pst=null;	
		Connection con=null;
		String status="success";
		ResultSet rs=null;
	//	Properties queries=null;
		String password=null;
		try {
			Connect c=ConnectInit.getConnect();
			con=c.getdbConnection(); 
		//	queries=ConnectInit.queries;
			String Query = ConnectInit.queries.getProperty("getpassword");
			
			pst = con.prepareStatement(Query);
			pst.setString(1,username);
			rs=pst.executeQuery();
			
			int count=0;
			if(rs.next() )
			{
				count++;
				password=rs.getString(1);
			}
			if(count==0)
			{
				password="noexist";
			}
			
			Logging.debug("No of rows"+count);
			
		} catch (SQLException e) {
			password="datafail";
			Logging.debug("..saveUser Fail due to.." + e);
			
		} 
	 
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				Logging.debug(e);
			}
		}
	return password;
	 
	 
	 
 }
	
	
	public synchronized  String insertCanceldata(int[] order,String user_id,String username )
	{
		String status="success";
		Connection con=null;
        try{
           PreparedStatement pst=null;	
		    
			Connect c=ConnectInit.getConnect();
			//con.setAutoCommit(false);
		//	Properties queries=null;
		//	queries=ConnectInit.queries;
			String Query = ConnectInit.queries.getProperty("insert_cancel_data");
			//insert_cancel_data=INSERT  INTO cancel_subscription_data(order_id, user_id, cancel_date) VALUES (?, ?, ?);
			 con=c.getdbConnection();
			
		    SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
		    Date dt = new Date();
	        int length1=order.length;
		  
		   String s=Integer.toString(order[length1-1]);
		 
	        while(length1>0)
		    {
		    	pst = con.prepareStatement(Query);
		    	String orderno=Integer.toString(order[length1-1]);
		    	pst.setString(1,username );
		    	pst.setString(2,orderno);  
			    pst.setString(3,user_id );
			    pst.setString(4,fr.format(dt).toString() );
			   
			    pst.executeUpdate();
		        length1=length1-1;
		        pst=null; 
		    }
		    
		    
		    
          
		          
		} catch (SQLException e) {
			status="datafail";
			Logging.debug("subscription.dao.SubscriptionDao.insertCanceldata Fail due to.." + e);
			
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				Logging.debug(e);
			}
		}
		
		
		
		return status;
		
	


	}
	
	public synchronized  String CancelSubscription(int[] order)
	{
		String status="success";
		Connection con=null;
        try{
           PreparedStatement pst=null;	
		    
			Connect c=ConnectInit.getConnect();
			
		//	Properties queries=null;
		//	queries=ConnectInit.queries;
			String Query = ConnectInit.queries.getProperty("cancel_subscription");
			con=c.getdbConnection();
			int length1=order.length;
			con.setAutoCommit(false);
		   String s=Integer.toString(order[length1-1]);
		    while(length1>0)
		    {
		    	pst = con.prepareStatement(Query);
		    	String orderno=Integer.toString(order[length1-1]);
		    	pst.setString(1,orderno);  
			    pst.executeUpdate();
		        length1=length1-1;
		        pst=null; 
		    }
		    length1=order.length;
		    Query = ConnectInit.queries.getProperty("delete_cancel_data");
		    while(length1>0)
		    {
		    	pst = con.prepareStatement(Query);
		    	String orderno=Integer.toString(order[length1-1]);
		    	pst.setString(1,orderno);  
			    pst.executeUpdate();
		        length1=length1-1;
		        pst=null; 
		    }
		    
		    con.setAutoCommit(true);
          
		          
		} catch (SQLException e) {
			status="datafail";
			Logging.debug("subscription.dao.SubscriptionDao.insertCanceldata Fail due to.." + e);
			
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				Logging.debug(e);
			}
		}
		
		
		
		return status;
		
	


	}
	


}
