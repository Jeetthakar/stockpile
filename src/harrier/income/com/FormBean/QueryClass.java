/*
 * Created on Sep 11, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.FormBean;
import java.sql.*;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

import com.harrier.initializeation.ConnectInit;

import app.Connect;

import java.util.Date;
import java.util.Properties;
import java.text.*;
import java.io.FileInputStream;


/**
 * @author manish
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class QueryClass {
	 static Logger Logging = Logger.getLogger(QueryClass.class);
public static void insertIntoIndexMaster(String query,ActionForm form){
	Connection con =null;
	Connect c = ConnectInit.getConnect();
	try {
			if(con==null)
				con = c.getdbConnection();
			
			PreparedStatement psmt = con.prepareStatement(query);
			Logging.debug("After preparedstsmt");
			NewIndexForm form1 = (NewIndexForm)form;
			String child = null;
			String customized =null;
			if(form1.getB_isIndexIsChildOrCustomized()=="1")
		    	{
		    		child ="y";
		    		customized ="n";
		    	}
			else
			{
				child ="n";
	    		customized ="y";
			}
			
		    Logging.debug("After form "+form1.getS_indexName());
			
		    Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT NEXTVAL('index_id')");
			if(rs.next())
			psmt.setInt(1,rs.getInt(1));
		    Logging.debug("After preparedstsmt1");
			psmt.setString(2,form1.getS_indexName().trim());
			Logging.debug("After preparedstsmt2");
			psmt.setString(3,formatDate(form1.getD_creationDate()));
			Logging.debug("After preparedstsmt3");
			psmt.setString(4,form1.getB_isActive());
			Logging.debug("After preparedstsmt4");
			psmt.setString(5,formatDate(form1.getD_baseDate()));
			Logging.debug(formatDate(form1.getD_baseDate()));
			psmt.setFloat(6,Float.parseFloat(form1.getD_baseValue()));
			//Logging.debug(formatDate(form1.getD_baseValue));
			psmt.setInt(7,Integer.parseInt(form1.getI_timeInterval()));
			Logging.debug("After preparedstsmt7");
			psmt.setString(8,form1.getB_isCaptured());
			Logging.debug("After preparedstsmt8");
			psmt.setString(9,form1.getS_capturedFrom());
			Logging.debug("After preparedstsmt9");
			psmt.setString(10,form1.getD_startTime());
			Logging.debug("After preparedstsmt10");
			psmt.setString(11,form1.getD_stopTime());
			Logging.debug("After preparedstsmt11");
			psmt.setString(12,form1.getS_reutersCode());
			Logging.debug("After preparedstsmt12");
			psmt.setString(13,child);//form1.getB_isNewIndexIsChild());
			Logging.debug("After preparedstsmt13");
			psmt.setInt(14,Integer.parseInt(form1.getS_parentIndex()));
			Logging.debug("After preparedstsmt14");
			psmt.setInt(15,Integer.parseInt(form1.getS_baseCurrency()));
			Logging.debug("After preparedstsmt15");
			psmt.setInt(16,Integer.parseInt(form1.getS_indexType()));
			Logging.debug("After preparedstsmt16");
			psmt.setFloat(17,Float.parseFloat(form1.getS_alertPercent()));
			Logging.debug("After preparedstsmt17");
			psmt.setFloat(18,Float.parseFloat(form1.getS_rejectionPercent()));
			Logging.debug("After preparedstsmt18");
			psmt.setString(19,form1.getS_industryClassificationID());
			Logging.debug("After preparedstsmt19");
			psmt.setString(20,form1.getS_type());
			Logging.debug("After preparedstsmt20");
			psmt.setString(21,form1.getB_computeSettlementValue());
			Logging.debug("After preparedstsmt21");
			psmt.setString(22,form1.getS_ISIN());
			Logging.debug("After preparedstsmt22");
			psmt.setString(23,customized);//form1.getB_isIndexCustomizedVersion());
			Logging.debug("After preparedstsmt23");
//			Logging.debug(psmt);
			psmt.executeUpdate(); 
			Logging.debug("Record Inserted Successfully");
			
			
			

	}catch(Exception e){ Logging.debug("Error while creating prepared Statemwent" +e.getMessage());}
	
}
public static String formatDate(String date)
{ 
	SimpleDateFormat fr = new SimpleDateFormat("dd-MMM-yyyy");
	Date dt = new Date();
	return fr.format(dt).toString();
}
/*
public static Connection getConnection() {
	Connection con=null;
	try {
		
		Properties rs = new Properties();
		rs.load(new FileInputStream("resources/database.properties"));
		String machine = rs.getProperty("machine");
		String port = rs.getProperty("port");
		String database = rs.getProperty("database");
		String user = rs.getProperty("user");
		String password = rs.getProperty("password");
		Class.forName("org.postgresql.Driver").newInstance();
		String url = "jdbc:postgresql://" + machine + ":" + port + "/"
		+ database;		
		con= DriverManager.getConnection(url, user, password); 
		Logging.debug("Connected successfully");
		
	} catch (SQLException e) {
		Logging.debug("connection" + e);
		System.exit(0);
		
	} catch (Exception e) {
		Logging.debug("connection1" + e);
	}
	return con;	

}*/


}
