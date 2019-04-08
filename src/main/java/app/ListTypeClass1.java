/*
 * Created on Sep 12, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;
import harrier.income.com.masters.StockMasterForm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.harrier.initializeation.ConnectInit;
/**
 * @author kena,pranoti
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
//Changes for static connection problem by ashish dated 02-08-2006
//changed by Sonali to 1) Replace the static connections 
// 2) To check the resultset for null values before using 

public class ListTypeClass1 {	
	static Logger Logging = Logger.getLogger(ListTypeClass1.class);
	public static int Select_nextval(Connection con,String query)
	{
		int id=0;
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs!=null && 	rs.next())
				id=rs.getInt(1);
			Logging.debug("id in listtype=="+id);			
		}catch(Exception e){
			Logging.error("Error="+e.getMessage());
			}
		Logging.debug("id in listtype=="+id);
		return id;
	}
	public static ResultSet resultCorporate(Connection con,String query){
		ResultSet rs=null;
		
		try{
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(query);
		}catch(Exception e) {
			Logging.error(" ListTypeClass:Error in CreateStatement "+e.getMessage()); 
		}                
		return rs;
	}
	public static StringBuffer getList(ResultSet rs){
		  StringBuffer buffer =new StringBuffer();
		  
		  try{
		  if(rs != null){
			   while(rs.next()){			   	
			  	buffer.append("<option value="+rs.getInt(1)+">"+rs.getString(2)+"</option>");
   		}
   	  }		  
		  }catch(Exception e) {Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());}  
		  return buffer;
	}
	public static StringBuffer getList1(ResultSet rs,String corp){
		  StringBuffer buffer =new StringBuffer();
		  try{
			  if(rs != null){
				   while(rs.next()){              
					   	String corp_id=rs.getString(1);
					   	if(corp.equals(corp_id))
					   	{
					   		buffer.append("<option selected value="+rs.getInt(1)+">"+rs.getString(2)+"</option>");
					   	}
					   	else
					  	buffer.append("<option value="+rs.getInt(1)+">"+rs.getString(2)+"</option>");
				   }
			  }
		  }catch(Exception e) {
		  	Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());
		  }  
		  return buffer;
	}
	
	public static StringBuffer getListCorp(Connection con,String query){
		StringBuffer buffer =new StringBuffer();
		ResultSet rs=null;
		try{
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if(rs != null){
			   while(rs.next()){              
			   		buffer.append("<option value="+rs.getInt(1)+">"+rs.getString(2)+"</option>");
			   }
			}
		 }catch(Exception e) {
		 	Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());
		 }  
		 return buffer;
	}

	public static StringBuffer getStockName(Connection con,String query){
		StringBuffer buffer =new StringBuffer();
		ResultSet rs=null;
		try{
			Statement stmt = con.createStatement();  	
			rs = stmt.executeQuery(query);
			if(rs != null){
				 while(rs.next()){              
				  	buffer.append("<option value="+rs.getInt(1)+">"+rs.getString("stock_name")+"</option>");
				 }
			}
		}catch(Exception e) {
		 	Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());
		 }  
		 return buffer;
	   }
	public static StringBuffer getListType2(Connection con,String query){
		
		  ResultSet rs =null;
	      StringBuffer buffer =new StringBuffer();
		  try {	          
		  		Statement stmt = con.createStatement();		  		
	    		rs = stmt.executeQuery(query);
	    		if(rs != null){
				   while(rs.next()){              
				  	buffer.append("<option value="+rs.getInt(1)+">"+rs.getString(2)+"</option>");
	       
	      		}
	      	  }
	      	}catch(Exception e) {Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage()); }                
      		return buffer;
	}
public static StringBuffer getListType1(Connection con,String query,String code){
	
	  ResultSet rs =null;
      StringBuffer buffer =new StringBuffer();
	  try {	
	  		Statement stmt = con.createStatement();
    		rs = stmt.executeQuery(query);
    		if(rs != null){
			   while(rs.next()){              
            	int exc=rs.getInt(1);
            	if(code!=null)
            	{
            		if(exc==Integer.parseInt(code))
            		   	buffer.append("<option selected value="+exc+">"+rs.getString(2)+"</option>");
            		else
            			 buffer.append("<option value="+exc+">"+rs.getString(2)+"</option>"); 	
            	}else
       			  buffer.append("<option value="+exc+">"+rs.getString(2)+"</option>");
            }
	  	}
  }catch(Exception e) {Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage()); }                
 		return buffer;

}
public static StringBuffer getListType1(Connection con,String query,String code,String stkid){
	
	  ResultSet rs =null;
      StringBuffer buffer =new StringBuffer();
	  try {
	  		if(code == null)
			{			 
			 return null;
			}
          	else
          	{				
				PreparedStatement  stmt = con.prepareStatement(query);
				stmt.setInt(1,Integer.parseInt(code));
				rs = stmt.executeQuery();
		    		if(rs != null){            
					    while(rs.next()){        
					    	String stk=rs.getString("stock_id");
					    	if(stk.equals(stkid))
			            		   	buffer.append("<option selected value="+stk+">"+rs.getString(2)+"</option>");
			            		else
			            			 buffer.append("<option value="+stk+">"+rs.getString(2)+"</option>");
	   		}
    	}            	
	  }
  }catch(Exception e) {Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage()); }                
  		return buffer;

}

public static StringBuffer getListType11(Connection con,String query,String param){
	ResultSet rs =null;
	String val=null;
	  StringBuffer buffer =new StringBuffer();	
      try{
		PreparedStatement  stmt = con.prepareStatement(query);
		stmt.setInt(1,Integer.parseInt(param));
		rs = stmt.executeQuery();
		if(rs != null){            
		    while(rs.next()){        
		    	val=rs.getString("stock_id");
		    	if(val.equals(param))
            		   	buffer.append("<option selected value="+val+">"+rs.getString(2)+"</option>");
            		else
            			 buffer.append("<option value="+val+">"+rs.getString(2)+"</option>"); 	
            	
		    }
		}
      
		 }catch (Exception e) {
    	Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());
    }
   		return buffer;
		 
}
public static StringBuffer getListType(Connection con,String query,String param){
	ResultSet rs =null;
    StringBuffer buffer =new StringBuffer();
    try{
		if(param == null)
		{
			 return null;
		}
		else
		{		
			PreparedStatement  stmt = con.prepareStatement(query);
			stmt.setInt(1,Integer.parseInt(param));
			rs = stmt.executeQuery();
			if(rs != null){
				   while(rs.next()){			   
				   	buffer.append("<option value="+rs.getInt(1)+">"+rs.getString(2)+"</option>");
				   }
			}
		}
    }catch (Exception e) {
    	Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());
    }
	return buffer;
}

public static StringBuffer getListTypeStk(Connection con,String query,String param,Hashtable hash){
	ResultSet rs =null;
    StringBuffer buffer =new StringBuffer();
    try{
		if(param == null)
		{			  
			 return null;
		}
		else
		{		
		PreparedStatement  stmt = con.prepareStatement(query);
		stmt.setInt(1,Integer.parseInt(param));
		rs = stmt.executeQuery();
		if(rs != null){
			   while(rs.next()){
			   		boolean chk_hash=hash.containsKey(rs.getString("stock_id"));
			   		if(chk_hash==true)
			   			buffer.append("<option value="+rs.getInt(1)+" selected>"+rs.getString(2)+"</option>");
			   		else
			   			buffer.append("<option value="+rs.getInt(1)+">"+rs.getString(2)+"</option>");
			   }
			}
		}
    }catch (Exception e) {
    	Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());
    }
	return buffer;
}

public static StringBuffer getStockList(Connection con,String query,String param,String exc){
	ResultSet rs =null;
    StringBuffer buffer =new StringBuffer();
    try{
		if(param == null)
			{			  
			 return null;
			}
		else
		{		
		PreparedStatement  stmt = con.prepareStatement(query);
		stmt.setInt(1,Integer.parseInt(param));
		stmt.setInt(2,Integer.parseInt(exc));
		rs = stmt.executeQuery();
		if(rs != null){
			   while(rs.next()){			   
			   	buffer.append("<option value="+rs.getInt(1)+">"+rs.getString(2)+"</option>");
			   }
			}
		}
    }catch (Exception e) {
    	Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());
    }
	return buffer;
}

public static StringBuffer getStockList1(Connection con,String query,String param,String exc,Hashtable hash){
	ResultSet rs =null;
    StringBuffer buffer =new StringBuffer();
    try{
		if(param == null)
		{			  
			 return null;
		}
		else
		{		
		PreparedStatement  stmt = con.prepareStatement(query);
		stmt.setInt(1,Integer.parseInt(param));
		stmt.setInt(2,Integer.parseInt(exc));
		rs = stmt.executeQuery();
		if(rs != null){
			   while(rs.next()){
			   	boolean chk_hash=hash.containsKey(rs.getString("stock_id"));
			   	if(chk_hash==true)
			   		buffer.append("<option value="+rs.getInt(1)+" selected>"+rs.getString(2)+"</option>");
			   	else
			   		buffer.append("<option value="+rs.getInt(1)+">"+rs.getString(2)+"</option>");
			   }
			}
		}
    }catch (Exception e) {
    	Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());
    }
	return buffer;
}


public static StringBuffer select_stock(Connection con,String query,String query1,String param)
{
	StringBuffer buff=null;
	try
	{
		ResultSet rs=null;
		PreparedStatement  stmt = con.prepareStatement(query1);
		stmt.setInt(1,Integer.parseInt(param));
		rs = stmt.executeQuery();		
	}catch(Exception e){
		Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());
	}
	return buff;
}
public static ResultSet getAffected(Connection con,String query,String param){
	ResultSet rs =null;
	Logging.debug("query in aaffect= ="+query);
	 try{
		if(param == null)
		{
			 return null;
		}
		PreparedStatement  stmt = con.prepareStatement(query);
		stmt.setInt(1,Integer.parseInt(param));
		rs = stmt.executeQuery();		
    }catch (Exception e) {
    	Logging.error("ERROR IN AFFECTED INDEX"+e.getMessage());
    }
	return rs;
}
  

public static ResultSet getResult1(Connection con,String query,String param){
	ResultSet rs =null;
	
    try{
		if(param == null)
			 return rs;			
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1,Integer.parseInt(param));		
		rs = stmt.executeQuery();
    }catch (Exception e) {
    	Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());
    	}
		return rs;
	}


public static ResultSet getResult12(Connection con,String query,String param){
	ResultSet rs =null;
    try{
		if(param == null)
		{
			 return rs;
		}
		Logging.debug("query in result12=="+query);
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1,Integer.parseInt(param));
		stmt.setInt(2,Integer.parseInt(param));
		rs = stmt.executeQuery();
    }catch (Exception e) {
    	Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());
    	}
		return rs;
	}

public static ResultSet getresp_cad(Connection con,String query,String cid,String sid,String apply){
	ResultSet rs =null;
    try{
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1,Integer.parseInt(cid));
		stmt.setInt(2,Integer.parseInt(sid));
		stmt.setString(3,apply);		
		rs = stmt.executeQuery();
    }catch (Exception e) {
    	Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());
    	}
		return rs;
	}
public static ResultSet getresp_cad1(Connection con,String query,String cid,String sid,String apply){
	ResultSet rs =null;
    try{
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1,Integer.parseInt(sid));
		stmt.setInt(2,Integer.parseInt(cid));
		stmt.setString(3,apply);		
		rs = stmt.executeQuery();		
    }catch (Exception e) {
    	Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());
    	}
		return rs;
	}

public static ResultSet getResult_corp(Connection con,String query,String cid,String sid){
	ResultSet rs =null;

	 try{
	 	Logging.debug("query=="+query);
	 	Logging.debug("cid ==="+cid+"sid==="+sid);
		PreparedStatement stmt = con.prepareStatement(query);
		if(cid!=null)
			stmt.setInt(1,Integer.parseInt(cid));
		else
			stmt.setString(1,null);
		if(sid!=null) 
			stmt.setInt(2,Integer.parseInt(sid));		
		rs = stmt.executeQuery();	
	    }catch (Exception e) {
	    	Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());
    	}
		return rs;
	}
public static ResultSet getResult_corp_diary(Connection con,String query,String date,String date1,String type){
	ResultSet rs =null;
	 try{		
	 	PreparedStatement stmt = con.prepareStatement(query);
	 	stmt.setString(1,type);
		stmt.setString(2,date);
		stmt.setString(3,date1);
		stmt.setString(4,date);
		stmt.setString(5,date1);		
		rs = stmt.executeQuery();
	    }catch (Exception e) {
    	Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());
    	}
		return rs;
	}
public static ResultSet getResult_corp_diary1(Connection con,String query,String date,String date1,String type,String exc){
	ResultSet rs =null;
 try{		
 	PreparedStatement stmt = con.prepareStatement(query);
 	stmt.setString(1,type);
	stmt.setString(2,date);
	stmt.setString(3,date1);
	stmt.setString(4,date);
	stmt.setString(5,date1);		
	stmt.setString(6,exc);		
	rs = stmt.executeQuery();
    }catch (Exception e) {
	Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());
	}
	return rs;
	
}
public static ResultSet getResult_corp_diary2(Connection con,String query,String date,String date1,String type,String exc,String stk){
	ResultSet rs =null;
 try{		
 	PreparedStatement stmt = con.prepareStatement(query);
 	stmt.setString(1,type);
	stmt.setString(2,date);
	stmt.setString(3,date1);
	stmt.setString(4,date);
	stmt.setString(5,date1);		
	stmt.setString(6,exc);		
	stmt.setString(7,stk);
	rs = stmt.executeQuery();	
    }catch (Exception e) {
	Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());
	}
	return rs;
	
}
public static ResultSet getResult_apply(Connection con,String query,String cid,String date){
	ResultSet rs =null;	
	 try{	 	
		PreparedStatement stmt = con.prepareStatement(query);				
		stmt.setInt(1,Integer.parseInt(cid));		
		stmt.setString(2,date);
		rs = stmt.executeQuery();		
		Logging.debug("complete ");
	    }catch (Exception e) {
    	Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());
    	}
		return rs;
	}
public static ResultSet check_dairy_exist(Connection con,String query,String cid,String index,String stock,String date)
{
	ResultSet rs=null;
	try{
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1,cid);	
		stmt.setString(2,index);		
		stmt.setString(3,stock);		
		stmt.setString(4,date);
		rs = stmt.executeQuery();	
	}catch(Exception e){
		Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());
	}
	return rs;
}
public static void delete_ca(Connection con,String query,String cid)
{
	//ResultSet rs =null;
	try
	{
	PreparedStatement stmt = con.prepareStatement(query);
	stmt.setInt(1,Integer.parseInt(cid));	
	stmt.executeQuery();	
	}catch(Exception e){
		Logging.error("ListTypeClass:Error in DeleteStatement "+e.getMessage());		
	}
}
public static ResultSet check_corp(Connection con,String query,Corporate corporateact)
{
	ResultSet rs=null;
	String stock=corporateact.getStid();
	String corp=corporateact.getCorpid();
	String date=UpdateCorp.accept_date();
	try
	{
	PreparedStatement stmt = con.prepareStatement(query);
	stmt.setInt(1,Integer.parseInt(stock));
	stmt.setInt(2,Integer.parseInt(corp));
	stmt.setString(3,date);
	stmt.setString(4,date);
	rs = stmt.executeQuery();
	}catch(Exception e)
	{
		Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());
	}
	return rs;
}

public static StringBuffer select_stock(Connection con,String query,String query1,Corporate corporateact)
{
	StringBuffer str =new StringBuffer();	
	try
	{		
		int flag=0;
		Hashtable hash=corporateact.getHash2();
		PreparedStatement  stmt = con.prepareStatement(query);
		stmt.setInt(1,Integer.parseInt(corporateact.getI_index()));
		ResultSet rs = stmt.executeQuery();
		if(rs!=null){
	    while(rs.next())
	    {        
	    	String stk=rs.getString("stock_id");
	    	flag=0;
	    	for(Enumeration enum1 =hash.keys();enum1.hasMoreElements();)
			{
				String id2 =(String)enum1.nextElement();
				String div[]=ActionCorp.token(id2);
				if(div[0].equals("false"))
				{
					if(div[1].equals(stk))
						flag=1;
				}
				if(div[0].equals("true"))
				{
					stmt = con.prepareStatement(query1);
					stmt.setString(1,div[1]);
					ResultSet rs1 = stmt.executeQuery();
					rs1.next();
					String stk_id=rs1.getString("stock_id");
					if(stk_id.equals(stk))
						flag=1;
				}
			}
	    	if(flag==1)
	    		 str.append("<option selected value="+stk+">"+rs.getString(2)+"</option>");
	    	else
	    		 str.append("<option value="+stk+">"+rs.getString(2)+"</option>");
	    }  
		}
	}catch(Exception e){
		Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());
	}
	return str;
}
public static StringBuffer select_stock1(Connection con,String query,String query1,Corporate corporateact,String exc)
{
	StringBuffer str =new StringBuffer();
	try
	{		
		int flag=0;
		Hashtable hash=corporateact.getHash2();
		ResultSet rs=null;
		PreparedStatement  stmt=null;
		stmt = con.prepareStatement(query);
		stmt.setInt(1,Integer.parseInt(corporateact.getI_index()));
		stmt.setInt(2,Integer.parseInt(exc));
		rs = stmt.executeQuery();
		if(rs!=null){
			    while(rs.next())
			    {        
			    	String stk=rs.getString("stock_id");
			    	flag=0;
			    	for(Enumeration enum1 =hash.keys();enum1.hasMoreElements();)
					{
						String id2 =(String)enum1.nextElement();
						String div[]=ActionCorp.token(id2);
						if(div[0].equals("false"))
						{
							if(div[1].equals(stk))
								flag=1;
						}
						if(div[0].equals("true"))
						{
							stmt = con.prepareStatement(query1);
							stmt.setString(1,div[1]);
							ResultSet rs1 = stmt.executeQuery();
							rs1.next();
							String stk_id=rs1.getString("stock_id");
							if(stk_id.equals(stk))
								flag=1;
						}
					}
			    	if(flag==1)
			    		 str.append("<option selected value="+stk+">"+rs.getString(2)+"</option>");
			    	else
			    		 str.append("<option value="+stk+">"+rs.getString(2)+"</option>");
			    }
		}
	}catch(Exception e){
		Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());
	}
	return str;
}

public static ResultSet getResult_event_H(Connection con,String query,String date,String date1){
	ResultSet rs =null;
	StockMasterForm form1= new StockMasterForm();
	
	try{    	
		PreparedStatement stmt = con.prepareStatement(query);
				//,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		stmt.setString(1,form1.getB_exc_code());
		stmt.setString(2,date1);
		
		Logging.debug("fffffffffB_exc_code()"+form1.getB_exc_code());
		Logging.debug("fffffffffs_stockExchange"+form1.getS_stockExchange());
		
		//stmt.setString(1,"BEBSL");
		//stmt.setString(2,"84");
		rs = stmt.executeQuery();	
	    }catch (Exception e) {
    	Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());
    	}
		return rs;
	}
public static ResultSet getResult_event(Connection con,String query,String date,String date1){
	ResultSet rs =null;
	 try{    	
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1,date);
		stmt.setString(2,date1);		
		rs = stmt.executeQuery();	
	    }catch (Exception e) {
    	Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());
    	}
		return rs;
	}

public static String getAffected121(Connection con,String query,String param,int count){
	
	Logging.debug("query in aaffect= ="+query);
	Logging.debug("query in aaffect= ="+param);
	String value=null;
	 try{
		if(param == null)
		{
			 return null;
		}
		PreparedStatement  stmt = con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		stmt.setInt(1,Integer.parseInt(param));
		ResultSet rs = stmt.executeQuery();	
		// checked for if resultset contains values or not.
		//changed by sunil 10-DEC-2005
		if(rs!=null && rs.next()){
			value=rs.getString(count);
			rs.close();
		}
		 stmt.close();
    }catch (Exception e) {
    	Logging.error("ERROR IN AFFECTED INDEX"+e.getMessage());
    }
	return value;
}
public static ResultSet getResult_event1(Connection con,String query,String date,String date1,String stk){
	ResultSet rs =null;

	 try{
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1,date);
		stmt.setString(2,date1);
		stmt.setString(3,stk);
		rs = stmt.executeQuery();
	    }catch (Exception e) {
    	Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());
    	}
		return rs;
	}
public static ResultSet getResult_event2(Connection con,String query,String date,String date1,String exc,String stk){
	ResultSet rs =null;

	 try{
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1,date);
		stmt.setString(2,date1);
		stmt.setString(3,exc);
		stmt.setString(4,stk);
		rs = stmt.executeQuery();	
	    }catch (Exception e) {
    	Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());
    	}
		return rs;
	}

	public static void event_list(Corporate corporateact,ResultSet rs)
	{
		try
		{
			Connect connect = ConnectInit.getConnect();
			Connection con=null;
			if(con == null){
				 con = connect.getdbConnection();
			}	
			
		rs.next();
		String sid=rs.getString("stock_id");
		corporateact.setStid(sid);
		
		String stock=rs.getString("stock_name");
		corporateact.setName(stock);
		String amt=rs.getString("amount");
		if(amt==null)
			amt="";	
		corporateact.setAmt(amt);
		String r1=rs.getString("ratio_for_shares");
		if(r1==null)
			r1="";
		corporateact.setRatio1(r1);
		String r2=rs.getString("ratio_shares_offered");
		if(r2==null)
			r2="";
		corporateact.setRatio2(r2);
		if(amt.equals(""))
		{
			corporateact.setPercent("");
		}
		else
		{
			double close=0.0;
			String query=ConnectInit.queries.getProperty("select_stock_price_detail");
			ResultSet rs1=ListTypeClass1.getResult12(con,query,corporateact.getStid());		
			if(rs1.next())
			{			
				close=rs1.getDouble("adjusted_price");
				 double per=(Double.parseDouble(corporateact.getAmt())/close)*100;
				 corporateact.setPercent(Double.toString(per));
			}
			else
				corporateact.setPercent("0");
			rs1.close();
		}
		String a_date=rs.getString("announce_date");
		if(a_date==null)
			a_date="";
		corporateact.setAnnounce_date(a_date);
		String e_date=rs.getString("ex_date");
		if(e_date==null)
			e_date="";
		corporateact.setEx_date(e_date);
		String r_date=rs.getString("record_date");
		if(r_date==null)
			r_date="";
		corporateact.setRecord_date(r_date);
		String ap_date=rs.getString("apply_on_date");
		if(ap_date==null)
			ap_date="";
		corporateact.setApply_date(ap_date);
		String value=rs.getString("values");
		if(value!=null)
			if(value.equals("0"))
				value="";
		corporateact.setValues(value);			
		String bc_start=rs.getString("bc_start_date");		
		if(bc_start==null)
				bc_start="";
		corporateact.setBc_start(bc_start);
		String nc_start=rs.getString("nd_start_date");
		if(nc_start==null)
				nc_start="";
		corporateact.setNc_start(nc_start);
		String bc_end=rs.getString("bc_end_date");
		if(bc_end==null)
				bc_end="";
		corporateact.setBc_end(bc_end);
		String nc_end=rs.getString("nd_end_date");
		if(nc_end==null)
				nc_end="";
		corporateact.setNc_end(nc_end);	
		String desc=rs.getString("description");
		if(desc==null)
			desc="";
		corporateact.setDescription(desc);
		}catch(Exception e){
			Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());			
		}
	}
	
	
	public static void affect_index_list(Connection con,Corporate corporateact,String query,String stock)
	{
		try
		{	
			Hashtable hash_affect=corporateact.getHash_affind();
			ResultSet rs=getResult_corp(con,query,corporateact.getI_index(),stock);
			int count=0;
			while(rs.next())
			{				
				String ind_name=rs.getString("index_name");
				Logging.debug("aff index name=="+ind_name);
				hash_affect.put(new String(count+" "+rs.getString("index_id")),new String(stock));
				count++;
			}
			corporateact.setHash_affind(hash_affect);			
		}catch(Exception e){
			Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());			
		}
	}
	
	public static void affect_child_list(Connection con,Corporate corporateact,String query)
	{
		try
		{		
			Hashtable hash_affect=corporateact.getHash_affind();
			ResultSet rs=getAffected(con,query,corporateact.getI_index());
			int count=0;
			while(rs.next())
			{
				String ind_name=rs.getString("index_name");
				hash_affect.put(new String(count+" "+rs.getString("index_id")),new String(rs.getString("index_id")));
				count++;	
			}
			corporateact.setHash_affind(hash_affect);			
		}catch(Exception e){
			Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());			
		}
	}	
	
	public static void add_affect(Connection con,String query,Corporate corporateact)
	{
		try{
			Hashtable hash1=corporateact.getHash1();
			Hashtable hash2=corporateact.getHash2();
			boolean hash1_empty=hash1.isEmpty();
			boolean hash2_empty=hash2.isEmpty();
			String cname=corporateact.getCorpid();
			if(hash1_empty==false)
			{
				for(Enumeration enum1 =hash1.keys();enum1.hasMoreElements();)
				{
					String id2 =(String)enum1.nextElement();											
					affect_index_list(con,corporateact,query,id2);				
				}
			}
			if(hash2_empty==false)
			{
				for(Enumeration enum1 =hash2.keys();enum1.hasMoreElements();)
				{
					String id2 =(String)enum1.nextElement();					
					affect_index_list(con,corporateact,query,id2);				
				}
			}			
		}catch(Exception e){
			Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());
		}
	}
	
	public static StringBuffer getcorp_list11(ResultSet rs,String corp)
	{
			  StringBuffer buffer =new StringBuffer();			  
			  try{
			  if(rs != null){
				   while(rs.next()){              
				   	String corp_id=rs.getString("cam_shortname");
				   	StringTokenizer st = new StringTokenizer(corp_id," ");
				   	int val=st.countTokens();
				   	if(corp!=null)
				   		corp=corp.toLowerCase().trim();
				   	if(val>1)
				   	{
					   	String div[]=ActionCorp.token2(corp_id);
					   	String corp_val=(div[0]+div[1]).toLowerCase();
					   	corp_val=corp_val.trim();
					   	if(corp!=null)
					   	{
						   	if(corp.equals(corp_val))
						   	{
						   		buffer.append("<option selected value="+corp_val+">"+rs.getString(2).replaceAll(" ","&nbsp;")+"</option>");
						   	}
						   	else
						  	buffer.append("<option value="+corp_val+">"+rs.getString(2)+"</option>");
					   	}
					   	else
					   	{
					   		buffer.append("<option value="+corp_val+">"+rs.getString(2)+"</option>");
					   	}
				   	}
				   	if(val==1)
				   	{
				   		String corp_val=corp_id.toLowerCase();	
				   		corp_val=corp_val.trim();
				   		if(corp!=null)
				   		{
				   			if(corp.equals(corp_val))
						   	{
						   		buffer.append("<option selected value="+corp_val+">"+rs.getString(2).replaceAll(" ","&nbsp;")+"</option>");
						   	}
						   	else
						  	buffer.append("<option value="+corp_val+">"+rs.getString(2)+"</option>");	
				   		}
				   		else
				   		buffer.append("<option value="+corp_val+">"+rs.getString(2)+"</option>");
				   	}
	 		}
	 	  }
			  }catch(Exception e) {Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());}  
			  return buffer;		
	}
	
	public static int check_corp_name1(ResultSet rs,String corp)
	{
		int cid=0;
		try{
			while(rs.next())
			{
				corp=corp.trim();
				String corp_name=rs.getString("cam_shortname");
				StringTokenizer st = new StringTokenizer(corp_name," ");
				int val=st.countTokens();
				if(val > 1)
				{
					String div[]=ActionCorp.token2(corp_name);
					String cval=(div[0]+div[1]).toLowerCase().trim();
					if(cval.equals(corp))
					{
						cid=rs.getInt("cam_id");
						break;
					}
				}
				else
				{
					String cval=(rs.getString("cam_shortname")).toLowerCase().trim();
					if(corp.equals(cval))
					{
						cid=rs.getInt("cam_id");
						break;
					}
				}
			}
		}catch(Exception e){
			Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());
		}
		return cid;
	}	
	
public static void check_corp_name(ResultSet rs,String corp,Corporate corporateact)
{	
	String cid=null;
	try{
		while(rs.next())
		{
			corp=corp.trim();
			String corp_name=rs.getString("cam_shortname");
			StringTokenizer st = new StringTokenizer(corp_name," ");
			int val=st.countTokens();
			if(val > 1)
			{
				String div[]=ActionCorp.token2(corp_name);
				String cval=(div[0]+div[1]).toLowerCase().trim();
				if(cval.equals(corp))
				{
					cid=rs.getString("cam_id");
					break;
				}
			}
			else
			{
				String cval=(rs.getString("cam_shortname")).toLowerCase().trim();
				if(corp.equals(cval))
				{
					cid=rs.getString("cam_id");
					break;
				}
			}
		}
		corporateact.setCorpid(cid);
	}catch(Exception e){
		Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());
	}		
}
	public static void stock_corpdetail(Corporate corp)
	{
		Connect connect = ConnectInit.getConnect();
		Connection con=null;
		try{
			if(con == null){
				 con = connect.getdbConnection();
			}
			
			String query=ConnectInit.queries.getProperty("index_corp_details");
			ResultSet rs=ListTypeClass1.getAffected(con,query,corp.getCad_id());
			rs.next();
			corp.setI_index(rs.getString("index_id"));
			corp.setStid(rs.getString("stock_id"));		
			
			String[] arr=new String[2];
			int cnt=0;
			arr[cnt]=rs.getString("stock_id");
			corp.setStock(arr);
			cnt++;
			corp.setExc(rs.getString("stock_exchange_id"));			
			corp.setValues(rs.getString("values"));
			String corp_nm=rs.getString("cam_shortname");
			StringTokenizer st = new StringTokenizer(corp_nm," ");
			int val=st.countTokens();
			if(val > 1)
			{
				String div[]=ActionCorp.token2(corp_nm);
				String cval=(div[0]+div[1]).toLowerCase().trim();
				corp.setCorpid(cval);
			}
			else						
				corp.setCorpid(corp_nm.toLowerCase().trim());
			String corpnm=corp.getCorpid();
			
			Hashtable hash=corp.getHash2();
			Hashtable hash1=corp.getHash1();
			hash.clear();
			hash1.clear();
			corp.setHash2(hash);
			corp.setHash1(hash1);
			hash=corp.getHash2();
			hash1=corp.getHash1();
			String stk="true:"+corp.getCad_id();
			
			if(corpnm.equals("changeindcurr"))
			{
				String curr_val=rs.getString("amount");
				corp.setFtcurrency(rs.getString("values"));
				double newcurr_val=1/(Double.parseDouble(curr_val));
				corp.setCurr_val(Double.toString(newcurr_val));
				corp.setInd_div(rs.getString("description"));
				hash.put(new String(stk),new String(rs.getString("index_id")));
				hash1.put(new String(stk),new String(rs.getString("index_id")));				
			}
			else
			{
				hash.put(new String(stk),new String(rs.getString("stock_id")));
				hash1.put(new String(stk),new String(rs.getString("stock_id")));
			}
			corp.setHash2(hash);
			corp.setHash1(hash1);		
			rs.close();
		}catch(Exception e){
			Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());			
		}
		finally{
			try{if(con!=null)
				con.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
	}
	public static void corp_detail(Corporate corp)
	{
		Connect connect = ConnectInit.getConnect();
		Connection con=null;
			
		try{
			if(con == null){
				 con = connect.getdbConnection();
			}
				Hashtable hash=corp.getHash();
				Hashtable hash1=corp.getHash1();
				hash1.clear();
				corp.setHash1(hash1);
				hash1=corp.getHash1();
				String query=null;
				for(Enumeration enum1 =hash.keys();enum1.hasMoreElements();)
				{
					String id=(String)enum1.nextElement();
					corp.setCad_id(id);
					hash1.put(new String(id),new String(id));
					query=ConnectInit.queries.getProperty("select_corp_diary");
					ResultSet rs=getAffected(con,query,id);
					rs.next();
					corp.setExc(rs.getString("stock_exchange_id"));
					corp.setStid(rs.getString("stock_id"));
					corp.setS_stock(rs.getString("stock_id"));
					String corp_nm=rs.getString("cam_shortname");
					StringTokenizer st = new StringTokenizer(corp_nm," ");
					int val=st.countTokens();
					if(val > 1)
					{
						String div[]=ActionCorp.token2(corp_nm);
						String cval=(div[0]+div[1]).toLowerCase();
						corp.setCorpid(cval.trim());
					}
					else						
						corp.setCorpid((corp_nm.toLowerCase()).trim());
					String corp_name=corp.getCorpid();
					if((corp_name.equals("cashdividend"))|(corp_name.equals("rightsoffering"))|(corp_name.equals("sharesbuyback"))|(corp_name.equals("spin-off"))|(corp_name.equals("specialdividend")))
						corp.setAmt_per("a");
					corp.setAmount(rs.getString("amount"));
					corp.setAmt(rs.getString("amount"));
					corp.setPercent(rs.getString("percentage"));
					corp.setS_ratio1(rs.getString("ratio_for_shares"));
					corp.setRatio1(rs.getString("ratio_for_shares"));
					corp.setRatio2(rs.getString("ratio_shares_offered"));
					corp.setS_ratio2(rs.getString("ratio_shares_offered"));
					corp.setShare(rs.getString("values"));
					corp.setEx_date(rs.getString("ex_date"));
					corp.setAnnounce_date(rs.getString("announce_date"));
					corp.setRecord_date(rs.getString("record_date"));
					corp.setApply_date(rs.getString("apply_on_date"));
					corp.setApplied_date(rs.getString("applied_date"));
					corp.setBc_start(rs.getString("bc_start_date"));
					corp.setBc_end(rs.getString("bc_end_date"));
					corp.setNc_start(rs.getString("nd_start_date"));
					corp.setNc_end(rs.getString("nd_end_date"));
					
				}
				corp.setHash1(hash1);
		}catch(Exception e){
			Logging.error("error=="+e.getMessage());
		}
		finally{
			try{if(con!=null)
				con.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
	}
	public static ResultSet check_type_diary(Corporate corporate,String fdate,String tdate,String type)
	{
		ResultSet rs=null;
		Connect connect = ConnectInit.getConnect();
		Connection con=null;	
		try{
			//Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
			if(con == null){
				 con = connect.getdbConnection();
			}
			String val=corporate.getExc_stk();
			if(val==null)
				val="a";
			if(type.equals("event"))
			{
				if(val!=null)
				{
					if(val.equals("a"))
					{
						String query=ConnectInit.queries.getProperty("select_event_from_cad_between_date");
						rs=getResult_event(con,query,fdate,tdate);
					}
					if((val.equals("s"))|(val.equals("is")))
					{
						String query=ConnectInit.queries.getProperty("select_event_from_cad_between_date_stock");
						rs=getResult_event1(con,query,fdate,tdate,corporate.getStkid());
					}
					if(val.equals("e"))
					{
						String query=ConnectInit.queries.getProperty("select_event_from_cad_between_date_exc");
						rs=getResult_event1(con,query,fdate,tdate,corporate.getExch());
					}
					if(val.equals("es"))
					{
						String query=ConnectInit.queries.getProperty("select_event_from_cad_between_date_excstk");
						rs=getResult_event2(con,query,fdate,tdate,corporate.getExch(),corporate.getStkid());
					}
					if(val.equals("i"))
					{
						String query=ConnectInit.queries.getProperty("select_event_from_cad_between_date_ind");
						rs=getResult_event1(con,query,fdate,tdate,corporate.getI_index());
					}
				}
			}
			else
			{
				if(val!=null)
				{
					if(val.equals("a"))
					{
						String query=ConnectInit.queries.getProperty("select_from_cad_between_date");	
						rs=getResult_corp_diary(con,query,fdate,tdate,type);
					}
					if(val.equals("e"))
					{
						String query=ConnectInit.queries.getProperty("select_from_cad_between_date_exc");
						rs=getResult_corp_diary1(con,query,fdate,tdate,type,corporate.getExch());
					}
					if(val.equals("s"))
					{
						String query=ConnectInit.queries.getProperty("select_from_cad_between_date_stock");
						rs=getResult_corp_diary1(con,query,fdate,tdate,type,corporate.getStkid());
					}
					if(val.equals("es"))
					{
						String query=ConnectInit.queries.getProperty("select_from_cad_between_date_excstk");
						rs=getResult_corp_diary2(con,query,fdate,tdate,type,corporate.getExch(),corporate.getStkid());
					}
					if(type.equals("stock event"))
					{
						if(val.equals("i"))
						{
							String query=ConnectInit.queries.getProperty("select_from_cad_between_date_ind");
							rs=getResult_corp_diary1(con,query,fdate,tdate,type,corporate.getI_index());
						}
						if(val.equals("is"))
						{
							String query=ConnectInit.queries.getProperty("select_from_cad_between_date_stock");
							rs=getResult_corp_diary1(con,query,fdate,tdate,type,corporate.getStkid());						
						}
					}
					
					if(type.equals("index event"))
					{
						if(val.equals("i"))
						{
							String query=ConnectInit.queries.getProperty("select_from_cad_between_date_resp_ind");
							rs=getResult_corp_diary1(con,query,fdate,tdate,type,corporate.getI_index());
						}
						if(val.equals("is"))
						{
							String query=ConnectInit.queries.getProperty("select_from_cad_between_date_indstk");
							rs=getResult_corp_diary2(con,query,fdate,tdate,type,corporate.getStkid(),corporate.getI_index());
						}
					}
				}
			}
		}catch(Exception e){
			Logging.error("error"+e.getMessage());
		}
		
		return rs;
	}
	public static ResultSet hist_list(Connection con,String query,String stock,String date)
	{
		ResultSet rs=null;
		try{			 
		PreparedStatement  stmt = con.prepareStatement(query);
		stmt.setInt(1,Integer.parseInt(stock));
		stmt.setString(2,date);
		stmt.setString(3,date);
		stmt.setInt(4,Integer.parseInt(stock));
		rs = stmt.executeQuery();		
		}catch(Exception e){
			Logging.error("error"+e.getMessage());
			}
		return rs;
	}
	public static ResultSet hist_list1(Connection con,String query,String index,String date,String stock)
	{
		ResultSet rs=null;
		try{			 
		PreparedStatement  stmt = con.prepareStatement(query);
		stmt.setInt(1,Integer.parseInt(index));
		stmt.setString(2,date);
		stmt.setString(3,date);
		stmt.setInt(4,Integer.parseInt(index));
		stmt.setInt(5,Integer.parseInt(stock));
		stmt.setString(6,date);
		rs = stmt.executeQuery();		
		}catch(Exception e){
			Logging.error("error"+e.getMessage());
			}
		return rs;
	}
	public static ResultSet check_hist_list(Connection con,Connect connect,String query,Corporate corp,String stock)
	{
		ResultSet rs=null;
		try{
			PreparedStatement  stmt = con.prepareStatement(query);
			stmt.setString(1,corp.getI_index());
			stmt.setString(2,stock);
			stmt.setString(3,corp.getApply_date());
			stmt.setString(4,corp.getApply_date());
			stmt.setString(5,corp.getI_index());
			stmt.setString(6,stock);
			stmt.setString(7,corp.getApply_date());
			rs=stmt.executeQuery();
		}catch(Exception e){
			Logging.error("error"+e.getMessage());
			}
		return rs;
	}
	 
}	