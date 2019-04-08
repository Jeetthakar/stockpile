/*
 * Created on Sep 12, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import harrier.income.com.entities.CFormula;
import harrier.income.com.masters.CapturedIndexForm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jfree.chart.demo.servlet.AdjustDecimal;

import com.harrier.initializeation.ConnectInit;

/**
 * @author manish
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ListTypeClass {
	static Logger Logging = Logger.getLogger(ListTypeClass.class);
public static Map.Entry [] entries1;
public static StringBuffer getListType(Connection con ,String query,String id){
	
	  ResultSet rs =null;
      StringBuffer buffer =new StringBuffer();
	  try {	  		
	  		Statement stmt = con.createStatement();
	  		rs = stmt.executeQuery(query);	  		
	  		int id1 =0;
	  		int i=0;
	  		while(rs.next()){              
	  			id1 = rs.getInt(1); 	
	  			if(id != null)  
    				{  
             	 		int param = Integer.parseInt(id);             	 		      	 		
         	 		 	if(param==id1){         	 		 		             	 		 	
         	 		 		buffer.append("<option selected value="+id1+">"+rs.getString(2)+"</option>");
         	 		 	}else{	
         	 		 		buffer.append("<option value="+id1+">"+rs.getString(2)+"</option>");
         	 		 	}    				 
    				}else{ 
    					buffer.append("<option value="+id1+">"+rs.getString(2)+"</option>");
    					}
             	 	
	  		}	  		
      	}catch(Exception e) {Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage()); }                
      	return buffer;
}
  
public static StringBuffer getListTypeForComposition(Connection con ,String query,String id){
	
	  ResultSet rs =null;
      StringBuffer buffer =new StringBuffer();
	  try {
	  		Statement stmt = con.createStatement();
	  		if(id!=null)
	  		  return getListType(con,query,id);
    		rs = stmt.executeQuery(query);
    		if(rs != null){
			   int i = 1;
    			while(rs.next()){              
             	 if(i==1)
             	 	buffer.append("<option value="+rs.getInt(1)+" selected >"+rs.getString(2)+"</option>");
             	 else
             	 	buffer.append("<option value="+rs.getInt(1)+">"+rs.getString(2)+"</option>");
             	 i++;	
      		 }
      	  }
      	}catch(Exception e) {Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage()); }                
      		return buffer;
}

public static StringBuffer addRowsInTable2(Corporate corporateact,String query,Connection con )
{
	StringBuffer buffer = new StringBuffer();
	Hashtable data=corporateact.getHash1();
	Hashtable data1=corporateact.getHash4();
	Hashtable hash6=corporateact.getHash6();	
	String index=corporateact.getI_index();
	
	ResultSet rs=null,rs1=null;
	try
	{	
		if(data1!=null)
			data.putAll(data1);
		
		if(data!=null)
		{
	for(Enumeration enumm =data.keys();enumm.hasMoreElements();)
	{	
		
		 String id =(String)enumm.nextElement();
		 rs=ListTypeClass1.getResult12(con,query,id);
		 rs.next();
		 String name1=rs.getString("stock_name");
		 int val=0;
		 int leng=hash6.size();
		 if(leng>0)
		 {
		 for(Enumeration enum1 =hash6.keys();enum1.hasMoreElements();)
		 {
		 	String id2 =(String)enum1.nextElement();
			 rs1=ListTypeClass1.getResult12(con,query,id2);
			rs1.next();
			String name=rs1.getString("stock_name");
			if(name.equals(name1))
			{
				val=1;
				break;
			}
		 }
		 }
		 if(val==1)
		 {
			buffer.append("<tr>");
			buffer.append("<td width='7%' align=center valign= center height='5'>");
			buffer.append("<input type='checkbox' name='stockid' disabled value="+id+">");
			buffer.append("</td>");
		 }	
		 else
		 {
		 	buffer.append("<tr>");
			buffer.append("<td width='7%' align=center valign= center height='5'>");
			buffer.append("<input type='checkbox' name='stockid' value="+id+">");
			buffer.append("</td>");
		 }
			buffer.append("<td width='24%' align='left' valign='center' height='5'>");
	   		buffer.append("<font face='Arial' size='1' color='blue'>");	   		
			buffer.append(rs.getString("stock_name"));
	   		buffer.append("</font>");
			buffer.append("</td>");
	   		
	   		buffer.append("<td width='9%' align='right' valign='center' height='5'>");
	   		buffer.append("<font face='Arial' size='1' color='blue'>");
	   		buffer.append( rs.getString("iwf"));
	   		buffer.append("</font>");
	   		buffer.append("<p></p>");
	   		buffer.append("</td>");
	   		
	   		buffer.append("<td width='9%' align='right' valign='center' height='5'>");
	   		buffer.append("<font face='Arial' size='1' color='blue'>");
	   		if(rs.getString("stock_closing_value")==null)
	   			buffer.append("");
	   		else
	   			buffer.append(rs.getString("stock_closing_value"));
	   		buffer.append("</font>");
	   		buffer.append("</td>");
	   		
	   		buffer.append("<td width='8%' align='left' valign='center' height='5'>");
	   		buffer.append("<font face='Arial' size='1' color='blue'>");
	   		buffer.append(rs.getString("currency_code"));
	   		buffer.append("</font>");
	   		buffer.append("</td>");
	     	
	     	
	     	buffer.append("<td width='11%' align='right' valign='center' height='5'>");
	   		buffer.append("<font face='Arial' size='1' color='blue'>");
	   		buffer.append(rs.getString("tis"));
	   		buffer.append("</font>");
	   		buffer.append("</td>");
	     							     	
	     	buffer.append("<td width='11%' align='right' valign='center' height='5'>");
	   		buffer.append("<font face='Arial' size='1' color='blue'>");
	   		buffer.append(rs.getString("market_lot"));
	   		buffer.append("</font>");
	   		buffer.append("</td>");						     
	     	
	     	buffer.append("<td width='11%' align='right' valign='center' height='5'>");
	   		buffer.append("<font face='Arial' size='1' color='blue'>");
	   		if(rs.getString("stock_price_date")==null)
	   			buffer.append("");
	   		else
	   			buffer.append(rs.getString("stock_price_date"));
	   		buffer.append("</font>");
	   		buffer.append("</td>");
	     	buffer.append("</tr>");    		
			
		}
		}
	}catch(Exception e){
		Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());		
	}
	return buffer;
}

public static StringBuffer addRowsInTable1(Corporate corporateact)
{
	StringBuffer buffer = new StringBuffer();
	Connect connect = ConnectInit.getConnect();
	Connection con=null;
	try{
		if(con == null)
		{
			 con = connect.getdbConnection();	
		}

//as this method is used both for historic and general CA, so there should be date comparison
	String query=ConnectInit.queries.getProperty("select_stock_detail"); 
    String select_cad=ConnectInit.queries.getProperty("select_rep_cad");
 
    
	String dt=UpdateCorp.accept_date();   //get the current date
	String apply=corporateact.getApply_date();
	int chk_dt=ComputeIndexForm.CompareDate(apply,dt);		//check for the current date and user's entered date					

	
	Hashtable data=corporateact.getHash1();
	Hashtable data1=corporateact.getHash4();
	Hashtable hash6=corporateact.getHash6();	
	String index=corporateact.getI_index();
	ResultSet rs=null,rs1=null,rs2=null;
	String rs_iwf=null;
	Object obj=null;
	try
	{	
		if(data1!=null)
			data.putAll(data1);
		
		if(data!=null)
		{
	for(Enumeration enumm =data.keys();enumm.hasMoreElements();)
	{	
		
		 String id =(String)enumm.nextElement();		 
		 obj=data.get(id);
		 String cad_id=obj.toString();
		 ResultSet rs3=ListTypeClass1.getAffected(con,select_cad,cad_id);		 
		 rs3.next();
		 rs_iwf=rs3.getString("values");
		 rs=ListTypeClass1.getResult12(con,query,id);
		 rs.next();
		 String name1=rs.getString("stock_name");
		 String close=null;
		 int val=0;
		 int leng=hash6.size();
		 if(leng>0)
		 {
		 for(Enumeration enum1 =hash6.keys();enum1.hasMoreElements();)
		 {
		 	String id2 =(String)enum1.nextElement();
			 rs1=ListTypeClass1.getResult12(con,query,id2);
			rs1.next();
			String name=rs1.getString("stock_name");
			if(name.equals(name1))
			{
				val=1;
				break;
			}
		 }
		 }
		 if(val==1)
		 {
			buffer.append("<tr>");
			buffer.append("<td width='7%' align=center valign= center height='5'>");
			buffer.append("<input type='checkbox' name='c_Cad' disabled value="+id+">");
			buffer.append("</td>");
		 }	
		 else
		 {
		 	buffer.append("<tr>");
			buffer.append("<td width='7%' align=center valign= center height='5'>");
			buffer.append("<input type='checkbox' name='c_Cad' value="+id+">");
			buffer.append("</td>");
		 }
			buffer.append("<td width='24%' align='left' valign='center' height='5'>");
	   		buffer.append("<font face='Arial' size='1' color='blue'>");	   		
			buffer.append(rs.getString("stock_name"));
	   		buffer.append("</font>");
			buffer.append("</td>");
	   		
	   		buffer.append("<td width='9%' align='right' valign='center' height='5'>");
	   		buffer.append("<font face='Arial' size='1' color='blue'>");
	   		if(rs_iwf==null)
	   			buffer.append( rs.getString("iwf"));
	   		else
	   			buffer.append( rs_iwf);
	   		buffer.append("</font>");
	   		buffer.append("<p></p>");
	   		buffer.append("</td>");
	   		
	   		buffer.append("<td width='9%' align='right' valign='center' height='5'>");
	   		buffer.append("<font face='Arial' size='1' color='blue'>");
	   		if(chk_dt==0)
	   		{
		   		if(rs.getString("adjusted_price")==null)
		   		{
		   			if(rs.getString("stock_closing_value")==null)
		   			{
		   				buffer.append("");
		   				close="0";
		   			}
		   			else
		   			{
		   				buffer.append(rs.getString("stock_closing_value"));
		   				close=rs.getString("stock_closing_value");
		   			}
		   		}		   			
		   		else
		   		{
		   			buffer.append(rs.getString("adjusted_price"));
		   			close=rs.getString("adjusted_price");
		   		}		   		
	   		}
	   		else
	   		{
	   			String qry=ConnectInit.queries.getProperty("get_undo_close_value");
	   			rs2=ListTypeClass1.getResult_apply(con,qry,id,corporateact.getApply_date());
	   			rs2.next();
	   			if(rs2.getString("adjusted_price")==null)
		   		{
		   			if(rs2.getString("stock_closing_value")==null)
		   			{
		   				buffer.append("");
		   				close="0";
		   			}
		   			else
		   			{
		   				buffer.append(rs2.getString("stock_closing_value"));
		   				close=rs2.getString("stock_closing_value");
		   			}
		   		}		   			
		   		else
		   		{
		   			buffer.append(rs2.getString("adjusted_price"));
		   			close=rs2.getString("adjusted_price");
		   		}
	   		}
	   		buffer.append("</font>");
	   		buffer.append("</td>");
	   		
	   		buffer.append("<td width='8%' align='left' valign='center' height='5'>");
	   		buffer.append("<font face='Arial' size='1' color='blue'>");
	   		buffer.append(rs.getString("currency_code"));
	   		buffer.append("</font>");
	   		buffer.append("</td>");
	     	
	     	
	     	buffer.append("<td width='11%' align='right' valign='center' height='5'>");
	   		buffer.append("<font face='Arial' size='1' color='blue'>");
	   		buffer.append(rs.getString("tis"));
	   		buffer.append("</font>");
	   		buffer.append("</td>");
	     							     	
	     	buffer.append("<td width='11%' align='right' valign='center' height='5'>");
	   		buffer.append("<font face='Arial' size='1' color='blue'>");
	   		buffer.append(rs.getString("market_lot"));
	   		buffer.append("</font>");
	   		buffer.append("</td>");						     
	     	
	     	buffer.append("<td width='11%' align='right' valign='center' height='5'>");
	   		buffer.append("<font face='Arial' size='1' color='blue'>");
	   		if(chk_dt==0)
	   		{
		   		if(rs.getString("stock_price_date")==null)
		   			buffer.append("");
		   		else
		   			buffer.append(rs.getString("stock_price_date"));
	   		}
	   		else
	   		{
	   			if(rs2.getString("stock_price_date")==null)
		   			buffer.append("");
		   		else
		   			buffer.append(rs2.getString("stock_price_date"));
	   		}
	   		buffer.append("</font>");
	   		buffer.append("</td>");
	     	buffer.append("</tr>");
		}
	}
	}catch(Exception e){
		Logging.error("ListTypeClass:Error in CreateStatement "+e.getMessage());		
	}
	}catch(Exception e){ Logging.error("ERROR: "+e.getMessage()); }
	finally{
		try{if(con!=null)
			con.close();
		}catch(Exception ee){
			Logging.error(" Error : Unable to close Connection "+ee.getMessage());
		}
	}
	
	return buffer;
}

public static StringBuffer addRowsInTable(Hashtable data )  
	{
				
		StringBuffer buffer = new StringBuffer();
		try{
				if(data.isEmpty())
				{
					 return null;
				}
				
				//change here if problem occurs after sorting
				
				Set set = data.entrySet();
				Map.Entry [] entries = (Map.Entry[])set.toArray(new Map.Entry[set.size()]);
				Arrays.sort(entries, new Comparator()
				{
				public int compare(Object o1, Object o2)
				{
				//	StockDetails rs1 = (StockDetails)data.get(id);
					StockDetails v11 = (StockDetails)((Map.Entry)o1).getValue();
					StockDetails v22 = (StockDetails)((Map.Entry)o2).getValue();
					Object v1=v11.getStockName();
					Object v2=v22.getStockName();
				return ((Comparable)v1).compareTo(v2);
				}
				});
				int ix=0;			
		//		for(  Enumeration e = data.keys();e.hasMoreElements();ix++)//while(rs.next())
		//			{
			//	System.out.println("Befor for");
					for(int i=0; i<entries.length; i++)
					{
				    	//	System.out.println("Inside while");
					
						String id =(String) entries[i].getKey();
						//	String id = e.nextElement().toString();
							StockDetails rs = (StockDetails)data.get(id);
						//	System.out.println("for id :"+id+" stockid : "+new Integer(ix));
						//	StockDetails rs = (StockDetails)data.get((Object)new Integer(ix));
					//			System.out.println("for id :"+id+" stockid and name is : "+rs.getStockID()+" & "+rs.getStockName());
							buffer.append("<tr>");
						
								
								buffer.append("<td width='7%' align=center valign= center height='5'>");
								buffer.append("<input type='checkbox' name='stockid' value="+rs.getStockID()+">");
								buffer.append("</td>");
						   		
								buffer.append("<td width='24%' align='left' valign='center' height='5'>");
						   		buffer.append("<font face='Arial' size='1' color='blue'>");
								buffer.append(rs.getStockName());
						   		buffer.append("</font>");
								buffer.append("</td>");
						   		
						   		buffer.append("<td width='9%' align='right' valign='center' height='5'>");
						   		buffer.append("<font face='Arial' size='1' color='blue'>");
						   		buffer.append(rs.getIwf());
						   		buffer.append("</font>");
						   		buffer.append("<p></p>");
						   		buffer.append("</td>");
						   		
						   		buffer.append("<td width='9%' align='right' valign='center' height='5'>");
						   		buffer.append("<font face='Arial' size='1' color='blue'>");
						   		buffer.append(rs.getLtp());
						   		buffer.append("</font>");
						   		buffer.append("</td>");
						   		
						   		buffer.append("<td width='8%' align='left' valign='center' height='5'>");
						   		buffer.append("<font face='Arial' size='1' color='blue'>");
						   		buffer.append(rs.getCurrency());
						   		buffer.append("</font>");
						   		buffer.append("</td>");
						     	
						     	
						     	buffer.append("<td width='11%' align='right' valign='center' height='5'>");
						   		buffer.append("<font face='Arial' size='1' color='blue'>");
						   		buffer.append(rs.getTis());
						   		buffer.append("</font>");
						   		buffer.append("</td>");
						     							     	
						     	buffer.append("<td width='11%' align='right' valign='center' height='5'>");
						   		buffer.append("<font face='Arial' size='1' color='blue'>");
						   		buffer.append(rs.getMarket_lot());
						   		buffer.append("</font>");
						   		buffer.append("</td>");						     
						     	
						     	buffer.append("<td width='11%' align='right' valign='center' height='5'>");
						   		buffer.append("<font face='Arial' size='1' color='blue'>");
						   		buffer.append(rs.getDate());
						   		buffer.append("</font>");
						   		buffer.append("</td>");
						     	buffer.append("</tr>");	
						     	
						     	
						}
									
		}catch(Exception e){Logging.error("ListTypeClass:Error in inserting rows in Table "+e.getMessage()); }
		
	 return buffer;
		
 }


public static StringBuffer addRowsIncIndexTable(Hashtable data,HttpServletRequest request,String option)  
{
			
	StringBuffer buffer = new StringBuffer();
	try{
			
			
			for(Enumeration e = data.keys();e.hasMoreElements();)//while(rs.next())
				{
			    	
						String id = e.nextElement().toString();
						CapturedIndexForm rs = (CapturedIndexForm)data.get(id);
				
						buffer.append("<tr>");
					
							
							buffer.append(" <td width='4%' align='center' class='gridStyle-odd' height='1'>");
							buffer.append("<input type='checkbox' height='1' name='indexID' value="+rs.getIndexID()+">");
							buffer.append("</td>");
					   		
							buffer.append("<td width='22%' class='gridStyle-odd' height='1' align='left' bgcolor='#FFFFFF' height='5'>");
					   
							buffer.append(rs.getIndex_name());
					   	
							buffer.append("</td>");
							
							/*buffer.append("<td width='8%'  class='gridStyle-odd' height='1'>");
							
							String ivalue="index_value:"+rs.getIndexID();
							ivalue=request.getParameter(ivalue);
							if(ivalue==null || ivalue.equals("0.00") || option.equals("Reset")){
							buffer.append("<input type= text name =index_value:"+rs.getIndexID());
							buffer.append(" size='10' align='left' value="+rs.getIndex_value()+">");
							}else{
								buffer.append("<input type= text name =index_value:"+rs.getIndexID());
								buffer.append(" size='10' align='left' value="+ivalue+ ">");
							}
						
							buffer.append("</td>");*/
					   		
							buffer.append("<td width='8%' align='center' class='gridStyle-odd' height='1'>");
					
					   		String ovalue="open_value:"+rs.getIndexID();
							ovalue=request.getParameter(ovalue);
							if(ovalue==null || ovalue.equals("0.00") || option.equals("Reset")){
					   		buffer.append("<input type= text name =open_value:"+rs.getIndexID());
							buffer.append(" size='10' align='right' value="+rs.getOpen_value()+">");
							}else{
								buffer.append("<input type= text name =open_value:"+rs.getIndexID());
								buffer.append(" size='10' align='right' value="+ovalue+">");
							}
							
							buffer.append("</td>");
							
							buffer.append("<td width='8%' align='center' class='gridStyle-odd' height='1'>");
					   		
					   		String hvalue="high_value:"+rs.getIndexID();
							hvalue=request.getParameter(hvalue);
							if(hvalue==null || hvalue.equals("0.00") || option.equals("Reset")){
						   		buffer.append("<input type= text name =high_value:"+rs.getIndexID());
								buffer.append(" size='10' align='right' value="+rs.getHigh_value()+">");
							}else{
								buffer.append("<input type= text name =high_value:"+rs.getIndexID());
								buffer.append(" size='10' align='right' value="+hvalue+">");
							}
					   		
							buffer.append("</td>");
							
							buffer.append("<td width='8%' align='center' class='gridStyle-odd' height='1'>");
				
					   		String lvalue="low_value:"+rs.getIndexID();
							lvalue=request.getParameter(lvalue);
							if(lvalue==null || lvalue.equals("0.00") || option.equals("Reset")){
						   		buffer.append("<input type= text name =low_value:"+rs.getIndexID());
								buffer.append(" size='10' align='right' value="+rs.getLow_value()+">");
							}else{
								buffer.append("<input type= text name =low_value:"+rs.getIndexID());
								buffer.append(" size='10' align='right' value="+lvalue+">");
							}
					   		
							buffer.append("</td>");
							
							buffer.append("<td width='8%' align='center' class='gridStyle-odd' height='1'>");
					   	
					   		String cvalue="closing_value:"+rs.getIndexID();
							cvalue=request.getParameter(cvalue);
							if(cvalue==null || cvalue.equals("0.00") || option.equals("Reset")){
						   		buffer.append("<input type= text name =closing_value:"+rs.getIndexID());
								buffer.append(" size='10' align='right' value="+rs.getClosing_value()+">");
							}else{
								buffer.append("<input type= text name =closing_value:"+rs.getIndexID());
								buffer.append(" size='10' align='right' value="+cvalue+">");
							}
					   		
							buffer.append("</td>");
							
							
							
							buffer.append("<td width='12%' align='center' class='gridStyle-odd' height='1'>");
					   		
					   		String mcvalue="mkt_cap_value:"+rs.getIndexID();
							mcvalue=request.getParameter(mcvalue);
							if(mcvalue==null || mcvalue.equals("0.00") || option.equals("Reset")){
						   		buffer.append("<input type= text name =mkt_cap_value:"+rs.getIndexID());
								buffer.append(" size='10' align='right' value="+rs.getMkt_cap_value()+">");
							}else{
								buffer.append("<input type= text name =mkt_cap_value:"+rs.getIndexID());
								buffer.append(" size='10' align='right' value="+mcvalue+">");
							}
					   		
							buffer.append("</td>");
							
							
							
							buffer.append("<td width='3%' align='center' class='gridStyle-odd' height='1'>");
					   	
					   		String cname="currancy_name:"+rs.getIndexID();
							cname=request.getParameter(cname);
							if(cname==null){
						   		buffer.append("<input type= text name =currancy_name:"+rs.getIndexID());
								buffer.append(" size='10' align='left' value="+rs.getCurrancy_name()+">");
							}else{
								buffer.append("<input type= text name =currancy_name:"+rs.getIndexID());
								buffer.append(" size='10' align='left' value="+cname+">");
							}
					  
							buffer.append("</td>");
							
							buffer.append("<td width='8%' align='center' class='gridStyle-odd' height='1'>");
					   		
					   		buffer.append("<input type='text' name='date' size='10' readonly='true'  value="+rs.getDate()+"  />");
					  
							buffer.append("</td>");
							
					     	buffer.append("</tr>");	
	  	
				
				}
								
	}catch(Exception e){Logging.error("ListTypeClass:Error in inserting rows in Table "+e.getMessage()); }
	
 return buffer;
	
}
public static StringBuffer addRowsIncIndexTable(Hashtable data,HttpServletRequest request)  
{
			
	StringBuffer buffer = new StringBuffer();
	try{
			
			
			for(Enumeration e = data.keys();e.hasMoreElements();)//while(rs.next())
				{
			    	//	System.out.println("Inside while");
					
						String id = e.nextElement().toString();
						CapturedIndexForm rs = (CapturedIndexForm)data.get(id);
				
						buffer.append("<tr>");
					
							
							buffer.append(" <td width='4%' align='center' height='5'>");
							buffer.append("<input type='checkbox' name='indexID' value="+rs.getIndexID()+">");
							buffer.append("</td>");
					   		
							buffer.append("<td width='22%' align='left' bgcolor='#FFFFFF' height='5'>");
					   		buffer.append("<font face='Arial' size='1' color='blue'>");
							buffer.append(rs.getIndex_name());
					   		buffer.append("</font>");
							buffer.append("</td>");
							
							/*buffer.append("<td width='8%'  bgcolor='#FFFFFF' height='20'>");
							buffer.append("<font face='Arial' size='1' color='blue'>");
							String ivalue="index_value:"+rs.getIndexID();
							ivalue=request.getParameter(ivalue);
							if(ivalue==null || ivalue.equals("0.00")){
							buffer.append("<input type= text name =index_value:"+rs.getIndexID());
							buffer.append(" size='10' align='right' value="+rs.getIndex_value()+">");
							}else{
								buffer.append("<input type= text name =index_value:"+rs.getIndexID());
								buffer.append(" size='10' align='right' value="+ivalue+ ">");
							}
							
					   		buffer.append("</font>");
							buffer.append("</td>");*/
					   		
							buffer.append("<td width='8%' align='center' bgcolor='#FFFFFF' height='5'>");
					   		buffer.append("<font face='Arial' size='1' color='blue'>");
					   		String ovalue="open_value:"+rs.getIndexID();
							ovalue=request.getParameter(ovalue);
							if(ovalue==null || ovalue.equals("0.00")){
					   		buffer.append("<input type= text name =open_value:"+rs.getIndexID());
							buffer.append(" size='10' align='right' value="+rs.getOpen_value()+">");
							}else{
								buffer.append("<input type= text name =open_value:"+rs.getIndexID());
								buffer.append(" size='10' align='right' value="+ovalue+">");
							}
							
					   		buffer.append("</font>");
							buffer.append("</td>");
							
							buffer.append("<td width='8%' align='center' bgcolor='#FFFFFF' height='5'>");
					   		buffer.append("<font face='Arial' size='1' color='blue'>");
					   		String hvalue="high_value:"+rs.getIndexID();
							hvalue=request.getParameter(hvalue);
							if(hvalue==null || hvalue.equals("0.00")){
						   		buffer.append("<input type= text name =high_value:"+rs.getIndexID());
								buffer.append(" size='10' align='right' value="+rs.getHigh_value()+">");
							}else{
								buffer.append("<input type= text name =high_value:"+rs.getIndexID());
								buffer.append(" size='10' align='right' value="+hvalue+">");
							}
					   		
					   		buffer.append("</font>");
							buffer.append("</td>");
							
							buffer.append("<td width='8%' align='center' bgcolor='#FFFFFF' height='5'>");
					   		buffer.append("<font face='Arial' size='1' color='blue'>");
					   		String lvalue="low_value:"+rs.getIndexID();
							lvalue=request.getParameter(lvalue);
							if(lvalue==null || lvalue.equals("0.00")){
						   		buffer.append("<input type= text name =low_value:"+rs.getIndexID());
								buffer.append(" size='10' align='right' value="+rs.getLow_value()+">");
							}else{
								buffer.append("<input type= text name =low_value:"+rs.getIndexID());
								buffer.append(" size='10' align='right' value="+lvalue+">");
							}
					   		
					   		buffer.append("</font>");
							buffer.append("</td>");
							
							buffer.append("<td width='8%' align='center' bgcolor='#FFFFFF' height='5'>");
					   		buffer.append("<font face='Arial' size='1' color='blue'>");
					   		String cvalue="closing_value:"+rs.getIndexID();
							cvalue=request.getParameter(cvalue);
							if(cvalue==null || cvalue.equals("0.00")){
						   		buffer.append("<input type= text name =closing_value:"+rs.getIndexID());
								buffer.append(" size='10' align='right' value="+rs.getClosing_value()+">");
							}else{
								buffer.append("<input type= text name =closing_value:"+rs.getIndexID());
								buffer.append(" size='10' align='right' value="+cvalue+">");
							}
					   		buffer.append("</font>");
							buffer.append("</td>");
							buffer.append("<td width='12%' align='center' bgcolor='#FFFFFF' height='5'>");
					   		buffer.append("<font face='Arial' size='1' color='blue'>");
					   		String mcvalue="mkt_cap_value:"+rs.getIndexID();
							mcvalue=request.getParameter(mcvalue);
							if(mcvalue==null || mcvalue.equals("0.00")){
						   		buffer.append("<input type= text name =mkt_cap_value:"+rs.getIndexID());
								buffer.append(" size='10' align='right' value="+rs.getMkt_cap_value()+">");
							}else{
								buffer.append("<input type= text name =mkt_cap_value:"+rs.getIndexID());
								buffer.append(" size='10' align='right' value="+mcvalue+">");
							}
					   		buffer.append("</font>");
							buffer.append("</td>");
							buffer.append("<td width='3%' align='center' bgcolor='#FFFFFF' height='5'>");
					   		buffer.append("<font face='Arial' size='1' color='blue'>");
					   		String cname="currancy_name:"+rs.getIndexID();
							cname=request.getParameter(cname);
							if(cname==null){
						   		buffer.append("<input type= text name =currancy_name:"+rs.getIndexID());
								buffer.append(" size='10' align='left' value="+rs.getCurrancy_name()+">");
							}else{
								buffer.append("<input type= text name =currancy_name:"+rs.getIndexID());
								buffer.append(" size='10' align='left' value="+cname+">");
							}
					   		buffer.append("</font>");
							buffer.append("</td>");
							
							buffer.append("<td width='8%' align='center' bgcolor='#FFFFFF' height='5'>");
					   		buffer.append("<font face='Arial' size='1' color='blue'>");
					   		
					   		buffer.append("<input type='text' name='date' size='10' readonly='true'  value="+rs.getDate()+"  />");
					   		buffer.append("</font>");
							buffer.append("</td>");
							
					     	buffer.append("</tr>");	
	  	
				
				}
								
	}catch(Exception e){Logging.error("ListTypeClass:Error in inserting rows in Table "+e.getMessage()); }
	
 return buffer;
	
}

public static StringBuffer addIndustryCode(Connection con ,String query,String code,String id)
	{
		StringBuffer buffer = new StringBuffer();
		ResultSet rs =null;
		try {
         
	  		Statement stmt = con.createStatement();
         	rs = stmt.executeQuery(query);
    		if(rs != null){
			   int id1 =0;
			  
			   while(rs.next())
    			 {              
			   	  id1 = rs.getInt(1);
			   	  if(id!=null)
    				{
    					int param =Integer.parseInt(id);
    					if(param==id1)
    					  	buffer.append("<option selected value="+id1+">"+code+"-"+rs.getString(2)+"</option>");
    					else
    					  	buffer.append("<option value="+id1+">"+code+"-"+rs.getString(2)+"</option>");
    				}else
    					buffer.append("<option value="+id1+">"+code+"-"+rs.getString(2)+"</option>");
       
      		}
      	  }
      	}catch(Exception e) {Logging.error("Error in CreateStatement"); }                
      	return buffer;
	
	}
public static StringBuffer addRowsInSecondTable1(Corporate corporateact,HttpServletRequest request)//,String query,String qry,String query1,String qry2,Connection con,HttpServletRequest request,String select_cad)
{
	StringBuffer buffer = new StringBuffer();	
	ResultSet rs=null,rs1=null,rs2=null,rs4=null;	
	Connect connect = ConnectInit.getConnect();
	Connection con=null;
	try
	{	
		if(con == null)
		{
			 con = connect.getdbConnection();	
		}
//		AdjustDecimal adj=new AdjustDecimal();
		AdjustDecimal adj = ConnectInit.getAdjustDecimal();
		String  query=ConnectInit.queries.getProperty("select_stock_detail"); //ries.getProperty("get_stock_details_for_child_index");     
        String qry=ConnectInit.queries.getProperty("select_stock_price_detail");       
        String qry2=ConnectInit.queries.getProperty("select_index_type");
		String select_cad=ConnectInit.queries.getProperty("select_rep_cad"); 
		String dt=UpdateCorp.accept_date();   //get the current date
		String apply=corporateact.getApply_date();
		int chk_dt=ComputeIndexForm.CompareDate(apply,dt);	//check for the current date and user's entered date
			
		Hashtable data=corporateact.getHash2();		
		Hashtable data1=corporateact.getHash3();
		Hashtable hash5=corporateact.getHash5();
		Hashtable hash12=corporateact.getHash12();
		hash12.clear();
		Object obj=null;
		String rs_iwf=null;
//		CFormula cf=new CFormula();
		CFormula cf = ConnectInit.getCFormula();	
		if(data1!=null)
		{
			data.putAll(data1);
		}
		if(data!=null)
		{
		for(Enumeration enumm =data.keys();enumm.hasMoreElements();)
		{			
			 String id =(String)enumm.nextElement();
			 obj=data.get(id);
			 String cid=obj.toString();
			 ResultSet rs3=ListTypeClass1.getAffected(con,select_cad,cid);
			 rs3.next();
			 rs_iwf=rs3.getString("values");
			 rs3.close();
			 rs=ListTypeClass1.getResult12(con,query,id);
			 rs.next();
			 String name1=rs.getString("stock_name");
			 int val=0;
			 String close=null; 
			 int leng=hash5.size();
			 if(leng>0)
			 {
			 for(Enumeration enum1 =hash5.keys();enum1.hasMoreElements();)
			 {
			 	String id2 =(String)enum1.nextElement();
				 rs1=ListTypeClass1.getResult12(con,query,id2);
				rs1.next();
				String name=rs1.getString("stock_name");
				if(name.equals(name1))
				{
					val=1;
					break;
				}
			 }			 
			 rs1.close();
			 }
			 if(val==1)
			 {			
			 	buffer.append("<tr>");
				buffer.append("<td width='5%' align='center'>");
				buffer.append("<input type='checkbox' name='c_Cad1'disabled value="+id+">");
				buffer.append("</td>");
			 }
			 else
			 {
			 	buffer.append("<tr>");
				buffer.append("<td width='5%' align='center'>");
				buffer.append("<input type='checkbox' name='c_Cad1' value="+id+">");
				buffer.append("</td>");
			 }
			 
			 rs1=ListTypeClass1.getResult12(con,qry,id);
			 rs1.next();
			 rs1.close();
			 
			buffer.append("<td width='16%' align='left'>");
	   		buffer.append("<font face='Arial' size='1' color='blue'>");
			buffer.append(rs.getString("stock_name"));
	   		buffer.append("</font>");
			buffer.append("</td>");					
					String iwf_val=id;
					String value=request.getParameter("iwf:"+id);
					Logging.debug("iwf val in list=="+value);
					if(value==null)
					{
						if(val==1)
						{
							if(rs_iwf==null)
								value=rs.getString("iwf");
							else
								value=rs_iwf;
						}
						else
							value=rs.getString("iwf");
					}
					hash12.put(new Integer(id).toString(),new String(value));
					buffer.append("<td width='12%' align='right'>");
					buffer.append("<font face='Arial' size='1' color='blue'>");
					if(val==1)
					{
						rs3=ListTypeClass1.getResult1(con,qry2,corporateact.getI_index());
						rs3.next();
						int type_id=rs3.getInt("index_type_id");						
						if(type_id==1)
						{
							buffer.append("<input type= text disabled dir=rtl name =iwf:"+id);
							buffer.append(" size='11' align='right' value="+value+">");
						}
						else
						{
							buffer.append("<input type= text dir=rtl name =iwf:"+id);
							buffer.append(" size='11' align='right' value="+value+">");
						}
					}
					else
					{
						buffer.append("<input type= text dir=rtl disabled name =iwf:"+id);
						buffer.append(" size='11' align='right' value="+value+">");
					}
					buffer.append("</font>");
					buffer.append("</td>");			
						
					
				
		   		buffer.append("<td width='12%' align='right'");
			   	buffer.append("<font face='Arial' size='1' color='blue'>");
			   	if(chk_dt==0)
			   	{
				   	if(rs.getString("stock_closing_value")==null)
				   		buffer.append("0");
				   	else
				   		buffer.append(rs.getString("stock_closing_value"));
				   	close=rs.getString("stock_closing_value");
			   	}
			   	else
			   	{
			   		String stk_qry=ConnectInit.queries.getProperty("get_undo_close_value");
			   		rs4=ListTypeClass1.getResult_apply(con,stk_qry,id,corporateact.getApply_date());
			   		rs4.next();
			   		if(rs4.getString("stock_closing_value")==null)
				   		buffer.append("0");
				   	else
				   		buffer.append(rs4.getString("stock_closing_value"));
			   		close=rs4.getString("stock_closing_value");
			   	}
			   	buffer.append("</font>");
			   	buffer.append("</td>");
			   	
		   		buffer.append("<td width='12%' align='left'>");
		   		buffer.append("<font face='Arial' size='1' color='blue'>");
		   		buffer.append(rs.getString("currency_code"));
		   		buffer.append("</font>");
		   		buffer.append("</td>");		   		
		   		
		   		double out=rs.getDouble("tis")*Double.parseDouble(value);	//change		   		
		   		String out1=adj.shareholdingpatt(out);
		   				   		
		   		corporateact.setOutstanding(out);
		   		buffer.append("<td width='13%' align='right'>");
		   		buffer.append("<font face='Arial' size='1' color='blue'>");
		   		buffer.append(out1);
		   		buffer.append("</font>");
		   		buffer.append("</td>");		   		
		   		
		   	
		   		//get currency exchange value
		   		NCorp_Action.get_currency(con,connect,corporateact,corporateact.getI_index(),id);
		   		double market=0.0;
		   		if(close==null)
		   			market=0.0;
		   		else
		   			market=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(rs.getString("market_lot")),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(rs.getString("tis")),Double.parseDouble(value));
		   			
		   		String market1=adj.shareholdingpatt(market);
		   		buffer.append("<td width='11%' align='right'>");
		   		buffer.append("<font face='Arial' size='1' color='blue'>");
		   		buffer.append(market1);
		   		buffer.append("</font>");
		   		buffer.append("</td>");		   		
		   		
		   		double adj_market=0.0;
		   		if(close==null)
		   			adj_market=out*0;
		   		else
		   			adj_market=out*Double.parseDouble(close);
		   		String adj_market1=adj.shareholdingpatt(adj_market);
		   		buffer.append("<td width='10%' align='right'>");
		   		buffer.append("<font face='Arial' size='1' color='blue'>");
		   		buffer.append(adj_market1);
		   		buffer.append("</font>");
		   		buffer.append("</td>");		   		
		   		
		   		String index=corporateact.getI_index();
		   		if(chk_dt==0)
		   		{
		   		  String query1=ConnectInit.queries.getProperty("select_index_detail");
		   		  rs1=ListTypeClass1.getResult12(con,query1,index);
		   		}
		   		else
		   		{
		   			String query1=ConnectInit.queries.getProperty("get_undo_index_close");
		   			rs1=ListTypeClass1.getResult_apply(con,query1,index,corporateact.getApply_date());
		   		}
				rs1.next();				 
				double tmarket=rs1.getDouble("tmcv");
				 rs1.close();				 	
				 
				 market1=new Double(market).toString();
		   		double weight=((market/tmarket)*100);		
		   				   		
		   		String weight1=new Double(weight).toString();
		   		StringTokenizer st = new StringTokenizer(weight1,"-");
		   		val=st.countTokens();
		   		if(val==1)
		   		 weight1=adj.shareholdingpatt(weight);   		

		   		buffer.append("<td width='26%' align='right'>");
		   		buffer.append("<font face='Arial' size='1' color='blue'>");
		   		buffer.append(weight1);
		   		buffer.append("</font>");
		   		buffer.append("</td>");		   		
		   		buffer.append("</tr>");
			}  		
		}		
		corporateact.setHash12(hash12);
	}catch(Exception e){
		Logging.error("Error in CreateStatement"+e.getMessage());		
	}
	finally{
		try{if(con!=null)
			con.close();
		}catch(Exception ee){
			Logging.error(" Error : Unable to close Connection "+ee.getMessage());
		}
	}
	return buffer;
}


public static StringBuffer addRowsInSecondTable(Hashtable data,HttpServletRequest request) 
{
	/*instead of creating object of index composition to get parent index currency id
	 * try to get the currency id from index composition form since this method is being 
	 * is being called from the index composition page .
	*/
	IndexCompositionForm icf=new IndexCompositionForm();//created object of indexcomposition form class 
	String indexCurrencyId=icf.getParentCurrencyId();//get the index currency id from object
	StringBuffer buffer = new StringBuffer();
	boolean readOnly =true;	
	double total=0.0;
//	org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
	AdjustDecimal ad = ConnectInit.getAdjustDecimal();
	if(data.isEmpty())
			return null;	
		if(request.getParameter("indexType").equals("2"))
		{
			readOnly=false;
		}
		for(Enumeration e=data.keys();e.hasMoreElements();)
		{
			String id = e.nextElement().toString();
			StockDetails rs1 = (StockDetails)data.get(id);
			 total+=rs1.getMktCapital(2);
			rs1.setTotalMktCapital(total);
		}
		
		Set set = data.entrySet();
		Map.Entry [] entries = (Map.Entry[])set.toArray(new Map.Entry[set.size()]);
		Arrays.sort(entries, new Comparator()
		{
		public int compare(Object o1, Object o2)
		{
		//	StockDetails rs1 = (StockDetails)data.get(id);
			StockDetails v11 = (StockDetails)((Map.Entry)o1).getValue();
			StockDetails v22 = (StockDetails)((Map.Entry)o2).getValue();
			Object v1=v11.getStockName();
			Object v2=v22.getStockName();
		return ((Comparable)v1).compareTo(v2);
		}
		});
		int ix=0;	
		entries1=new Map.Entry[entries.length];
		for(int i=0; i<entries.length; i++)
		{
	    	//	System.out.println("Inside while");
		    entries1[i]=entries[i];
			String id =(String) entries[i].getKey();
		/*for(Enumeration e = data.keys();e.hasMoreElements();)
		 {
			String id = e.nextElement().toString();*/
			StockDetails rs = (StockDetails)data.get(id);
	
			buffer.append("<tr>");
			buffer.append("<td width='5%' align='center'>");
			buffer.append("<input type='checkbox' name='stockid1' value="+rs.getStockID()+">");
			buffer.append("</td>");
		 
			buffer.append("<td width='16%' align='left'>");
	   		buffer.append("<font face='Arial' size='1' color='blue'>");
			buffer.append(rs.getStockName());
	   		buffer.append("</font>");
			buffer.append("</td>");
				
			buffer.append("<td width='9%' align='right'>");
			buffer.append("<font face='Arial' size='1' color='blue'>");
			String iwf =null;
			if(request.getParameter("iwf:"+rs.getStockID())==null)
			  iwf = String.valueOf(rs.getIwf());
			else
			  iwf = request.getParameter("iwf:"+rs.getStockID());
					
			if(!readOnly)
				{
					buffer.append("<input type= text name =iwf:"+rs.getStockID());
					buffer.append(" size='11' align='right' value="+iwf+">");
				}		
			else
				buffer.append(iwf);
			
			buffer.append("</font>");
	   		buffer.append("</td>");
	   		
	   		buffer.append("<td width='10%' align='right'>");
	   		buffer.append("<font face='Arial' size='1' color='blue'>");
	   		buffer.append(rs.getLtp());
	   		buffer.append("</font>");
	   		buffer.append("</td>");
	   		
	   		buffer.append("<td width='12%' align='left'>");
	   		buffer.append("<font face='Arial' size='1' color='blue'>");
	   		buffer.append(rs.getCurrency());
	   		buffer.append("</font>");
	   		buffer.append("</td>");
	   	
	   		buffer.append("<td width='13%' align='right'>");
	   		buffer.append("<font face='Arial' size='1' color='blue'>");
	   		buffer.append(rs.getOutStanding());
	   		buffer.append("</font>");
	   		buffer.append("</td>");
	   		
	   		String mkt_cap=ad.shareholdingpatt(rs.getMktCapital(2));
	   		buffer.append("<td width='11%' align='right'>");
	   		buffer.append("<font face='Arial' size='1' color='blue'>");
	   		buffer.append(mkt_cap);
	   		buffer.append("</font>");
	   		buffer.append("</td>");
	   		String adjmkt_cap=ad.shareholdingpatt(rs.getAdjustedMarket(2));
	   		buffer.append("<td width='10%' align='right'>");
	   		buffer.append("<font face='Arial' size='1' color='blue'>");
	   		buffer.append(adjmkt_cap);
	   		buffer.append("</font>");
	   		buffer.append("</td>");
	   		
	   		buffer.append("<td width='26%' align='right'>");
	   		buffer.append("<font face='Arial' size='1' color='blue'>");
	   		buffer.append(rs. getWightage());
	   		buffer.append("</font>");
	   		buffer.append("</td>");
	   		
	   		buffer.append("</tr>");
		 }
		
		return buffer;
	}
public static String[] getCurrency(Connection con,String query, String indexId)
 {
	String arr[] = new String[2];
	if(indexId!=null)
	{
//		System.out.println("Inside indexId null");
	try {
		PreparedStatement stmt = con.prepareStatement(query);
	//	System.out.println("after preprare");
		stmt.setInt(1,Integer.parseInt(indexId));
		ResultSet rs = stmt.executeQuery();
//		System.out.println("after rs");
		if(rs.next())
		{
			arr[0] = String.valueOf(rs.getInt(1));
			arr[1] = rs.getString(2);
		}
	
		stmt=null;
		rs=null;
		} catch(Exception e)
		{
			Logging.error("Inside catch "+e.getMessage());
		}
	}
	
	return arr;
	
}


}
