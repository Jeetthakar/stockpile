/**
 * @author Ashish
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

public class IndexComposeReportMethod {
	Logger Logging = Logger.getLogger(IndexComposeReportMethod.class);
	PreparedStatement pst;
	 Logger log;
	 ResultSet rst;
	 Connect con = ConnectInit.getConnect();
	public  String getIndexCurrancyExchRate(Connection connection,String id1,String id2)
{
	String cexch_rate=null;
//	app.Connect con=new app.Connect();
	
	Logging.debug("in getIndexCurrancyExchRate");
	try {			
		if(id1.equals(id2)){
			cexch_rate="1.00";
		}else{
			ResultSet rst11 = indwtResult(connection,"get_currency_exchange_rate", id1,id2);
			while (rst11.next()) {
				if (rst11.getString(1) == null) {
					cexch_rate="0";
				}else{
					cexch_rate=(String)rst11.getString(1);												
				}					
			}
			Logging.debug("cexch_rate is "+cexch_rate);
		}
	}catch (SQLException sqlexp) {
		Logging.error("SQL Error :"+sqlexp.getMessage());
	}
	return cexch_rate;
}
public ResultSet indexComposeResult(Connection connection,String index, String index_id ) {
	String id1,id2;
	id1=id2=index_id;
	try {
		String Query = ConnectInit.queries.getProperty(index);
		Logging.debug(Query);
		pst = connection.prepareStatement(Query);
		pst.setString(1, id1);
		pst.setString(2, id2);
		pst.setString(3, id1);
		rst = pst.executeQuery();
		Logging.debug("1");
	} catch (SQLException e) {
		Logging.error("Select" + e);
	}
	return rst;
	
}
public ResultSet stiockweightageLatestResult(Connection connection,String index, String index_id ) {
	String id1,id2;
	id1=id2=index_id;
	try {
	//	String Query = con.queries.getProperty(index);
		String Query =ConnectInit.queries.getProperty(index);
		Logging.debug(Query);
		pst = connection.prepareStatement(Query);
		pst.setString(1, id1);
		pst.setString(2, id2);
		rst = pst.executeQuery();
		Logging.debug("1");
	} catch (SQLException e) {
		Logging.error("Select" + e);
	}
	return rst;
	
}

public ResultSet benchindecolection(Connection connection,String index) {
	try {
		String Query = ConnectInit.queries.getProperty(index);
		Logging.debug(Query);
		Statement pstm = connection.createStatement();
		rst = pstm.executeQuery(Query);
		Logging.debug("1");
	} catch (SQLException e) {
		Logging.error("Select" + e);
	}
	return rst;
	
}


public ResultSet indwtResult(Connection connection,String index, String index_id,String fdate ) {
	
	try {
		//String index_id2,index_id3;
		String Query = ConnectInit.queries.getProperty(index);
		Logging.debug(Query);
		// index_id2=index_id3=index_id;
		pst = connection.prepareStatement(Query);
		pst.setString(1, index_id);
		pst.setString(2, fdate);
	//	pst.setString(3, index_id3);
		rst = pst.executeQuery();
		Logging.debug("1");
	} catch (SQLException e) {
		Logging.error("Select" + e);
	}
	return rst;
	
}
public ResultSet divisorResult(Connection connection,String index,String user) {
	
	try {
		
		String Query = ConnectInit.queries.getProperty(index);
		Logging.debug(Query);
		PreparedStatement pst = connection.prepareStatement(Query);
		pst.setString(1,user);
		 //Statement st = connection.createStatement();
		//pst.setString(1, sid);
		// System.out.println(Query);
		rst = pst.executeQuery();
		Logging.debug("1");
	} catch (SQLException e) {
		Logging.error("Error : " + e.getMessage());
	}
	return rst;
	
}

public ResultSet indweightResult(Connection connection,String index, String index_id) {
	
	try {
//		String index_id2,index_id3;
		String Query = ConnectInit.queries.getProperty(index);
		Logging.debug(Query);
//		 index_id2=index_id3=index_id;
		pst = connection.prepareStatement(Query);
		pst.setString(1, index_id);
		pst.setString(2, index_id);
		pst.setString(3,index_id);
	
		rst = pst.executeQuery();
		Logging.debug("1");
	} catch (SQLException e) {
		Logging.error("Select" + e);
	}
	return rst;
	
}
public ResultSet StockcontriIndexResult(Connection connection,String index,String sid, String fdate,String tdate ) {
	
	try {
		String id1,id2,id3,id4,id5,id6,fd1,fd2,td1,td2;
		id1=id2=id3=id4=id5=id6=sid;
		fd1=fd2=fdate;
		td1=td2=tdate;
		Logging.debug("inside StockcontriIndexResult result");
		Logging.debug(fdate+" "+"  "+tdate+"  "+sid);
		String Query = ConnectInit.queries.getProperty(index);
		Logging.debug(Query);
		 pst = connection.prepareStatement(Query);
		pst.setString(1, id1);
		pst.setString(2, id2);
		pst.setString(3, td1);
		pst.setString(4, id3);
		pst.setString(5, id4);
		pst.setString(6, fd1);
		pst.setString(7, id5);
		pst.setString(8, td2);
		pst.setString(9, id6);
		pst.setString(10, fd2);
		rst = pst.executeQuery();
		Logging.debug("1");
	} catch (SQLException e) {
		Logging.error("Error : " + e.getMessage());
	}
	return rst;
	
}
}