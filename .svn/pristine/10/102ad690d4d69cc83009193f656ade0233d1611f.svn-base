/*
 * Created on Sep 12, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.FormBean;

import java.sql.*;

import org.apache.log4j.Logger;
/**
 * @author manish
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ListTypeClass {
static Logger Logging = Logger.getLogger(ListTypeClass.class);
public static StringBuffer getListType(Connection con ,String query){
	
	  ResultSet rs =null;
      StringBuffer buffer =new StringBuffer();
	  try {
         //  	Logging.debug("before statement");
	  		Statement stmt = con.createStatement();
         	//String query ="select index_id,index_name from information_schema.index_master order by index_name";
    		rs = stmt.executeQuery(query);
    		if(rs != null){
			   while(rs.next()){              
             	
			   	buffer.append("<option value="+rs.getInt(1)+">"+rs.getString(2)+"</option>");
       
      		}
      	  }
      	}catch(Exception e) {Logging.debug("Error in CreateStatement"); }                
       
      	//	Logging.debug(buffer.toString());
      		return buffer;


}
	public static StringBuffer addIndustryCode(Connection con ,String query,String code)
	{
		StringBuffer buffer = new StringBuffer();
		ResultSet rs =null;
		try {
          // 	Logging.debug("before statement");
	  		Statement stmt = con.createStatement();
         	rs = stmt.executeQuery(query);
    		if(rs != null){
			   while(rs.next()){              
             	
			   	buffer.append("<option value="+rs.getInt(1)+">"+code+"-"+rs.getString(2)+"</option>");
       
      		}
      	  }
      	}catch(Exception e) {Logging.debug("Error in CreateStatement"); }                
      	return buffer;
	
	}

}
