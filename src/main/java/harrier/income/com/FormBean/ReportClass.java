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
public class ReportClass {
	 static Logger Logging = Logger.getLogger(ReportClass.class);
public static StringBuffer getReportTable(String query,Connection con,String title,boolean wantCheckBox)
{
	StringBuffer buffer = new StringBuffer();
	ResultSet rs =null;
	try {
		Logging.debug("Connection object "+con);
		Statement stmt = con.createStatement();
		Logging.debug("Statement object "+stmt);
		rs =  stmt.executeQuery(query);
		Logging.debug("Before rs check "+rs);
		if(rs!= null)
		{
			Logging.debug("Inside rs check "+rs);
			ResultSetMetaData meta = rs.getMetaData();
			int count =meta.getColumnCount();
			buffer.append("<font face='Ariel'><h3><center>"+title+"</center></h3></font>");
			buffer.append("<table border='1' cellspacing='1' cellpadding='1'>");
			buffer.append("<tr>");
			for(int i=1;i<=count;i++)
			{
				buffer.append("<td>"+meta.getColumnName(i)+"</td>");
					
			}
			buffer.append("</tr>");
			
			while(rs.next())
			{
				buffer.append("<tr>");
				for(int j=1;j<=count;j++)
				{
				  Object obj=  rs.getObject(j);
				  buffer.append("<td>"+obj.toString()+"</td>");
				} 
				buffer.append("</tr>");
			}
		buffer.append("</table>");	
	
	}
	
	}catch(Exception e) {Logging.debug("Error while generating report "+e.getMessage()); }
	
	return buffer;
  }

}

