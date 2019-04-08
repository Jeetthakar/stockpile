/*
 * Created on Jun 16, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package app;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.harrier.initializeation.ConnectInit;

/**
 * @author Vivek
 * 
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Connect {
	static Logger log = Logger.getLogger(Connect.class);
	Statement st;

	// public static RefreshCache cache;
	PreparedStatement pst;
	double str11 = 0.00;
	String strdt = null;
	ResultSet rst;
	public Connection dynaCon = null;
	public static Connection con;
	public static Connection conForTransaction11;
	public static Connection conForHistTransaction;
	public static Connection conForMergerTransaction;

	int index1;

	// public Properties queries;
	public static String coolmenus;
	public static String resourceurl;
	public static String res_url; // Variable Added for Linux that will be use
	// to obtain default path as per the Linux
	// requirement...
	public static String os_name;

	// JFrame frame;
	// Logger log;

	public Connect() {
		System.out.println("Connect Constructor ****");
		os_name = System.getProperty("os.name");
		getPropertiespath("Open16.gif");
		// String f1=getCoolMenuspath();

		// Logging.debug(" f1 is :"+f1);
		// Logging.debug(" f1 by static way is :"+Connect.resourceurl);

		// queries = new Properties();

		// log = Logger.getLogger(LogonAction.class.getName());
		// PropertyConfigurator.configure(resourceurl+"resources/log4j.properties");

		try {
			/*
			 * Following code fragement added on 23-Nov-07, as per Linux
			 * Requirement
			 */
			int l = 0;
			int len = 0;
			log.debug("contructor check  :  =========   " + resourceurl
					+ "resources/query.properties");
			if (os_name.startsWith("Windows")) {

			} else {
				l = resourceurl.lastIndexOf(":");
				len = resourceurl.length();
				resourceurl = resourceurl.substring(l + 1, len);
				// Logging.debug("In Connect Linux: "+resourceurl);
			}
			PropertyConfigurator.configure(resourceurl
					+ "resources/log4j.properties");
			// queries.load(new FileInputStream(resourceurl+
			// "resources/query.properties"));// For linux

			// queries.load(getServletContext().getResourceAsStream("resources/query.properties"));
		} catch (Exception e) {

			log.error(e);
			/*
			 * frame = new JFrame(); JOptionPane.showMessageDialog(frame,
			 * "Original : Unable To Find Query.properties File "+e, "ERROR!",
			 * JOptionPane.ERROR_MESSAGE); //System.exit(0);
			 */
		}
	}

	public Connection getConnection() {
		try {

			/*
			 * Properties rs = new Properties(); rs.load(new
			 * FileInputStream(resourceurl+"resources/database.properties"));
			 * String machine = rs.getProperty("machine"); String port =
			 * rs.getProperty("port"); String database =
			 * rs.getProperty("database"); String user = rs.getProperty("user");
			 * String password = rs.getProperty("password"); //
			 * Class.forName("org.postgresql.Driver").newInstance(); String url
			 * = "jdbc:postgresql://" + machine + ":" + port + "/" + database;
			 */
			System.out.println("Inside connect class getConnection method");
			if (con == null) {
				log.debug("creating new connection object");
				System.out.println("creating new connection object");
				// con = DriverManager.getConnection(url, user, password);
				InitialContext ic = new InitialContext();
				log.debug("creating new connection object1");
				System.out.println("creating new connection object1");
				DataSource ds = (DataSource) ic
						.lookup("java:comp/env/mypostgres");
				log.debug("creating new connection object2");
				System.out.println("creating new connection object2");
				con = ds.getConnection();
				log.debug("creating new connection object3");
				System.out.println("creating new connection object3");
			}
			// log.debug(url+user+password);

		} catch (SQLException e) {
			log.debug("connection" + e);
			/*
			 * frame = new JFrame(); JOptionPane.showMessageDialog(frame,
			 * "Unable To Connect DataBase", "ERROR!",
			 * JOptionPane.ERROR_MESSAGE);
			 */
			// System.exit(0);

		} catch (Exception e) {
			log.error("connection1" + e);
			/*
			 * frame = new JFrame(); JOptionPane.showMessageDialog(frame,
			 * "Unable To Connect DataBase", "ERROR!",
			 * JOptionPane.ERROR_MESSAGE);
			 */
		}
		return con;
	}

	/**
	 * method to get connection object
	 * 
	 * @return
	 */
	public Connection getdbConnection() {
		Connection connection = null;
		try {

			log.debug("Connection getdbConnection()");
			System.out.println("Connection getdbConnection()");
			InitialContext ic = new InitialContext();
			log.debug("InitialContext creating ");
			System.out.println("InitialContext creating");
			DataSource ds = (DataSource) ic.lookup("java:comp/env/mypostgres");
			log.debug("DataSource AND lookup");
			System.out.println("DataSource AND lookup");
			if (ds != null) {
				connection = ds.getConnection();
			} else {
				log.debug("Failed to lookup datasource.");
				System.out.println("Failed to lookup datasource.");
			}
			log.debug("GETTING POOLED connection");
			System.out.println("GETTING POOLED connection");

		} catch (SQLException e) {
			log.debug("connection" + e.getMessage());
			System.out.println("SQLEXCEPTION ****" + e);
			// Logging.debug("Error" + e);
			/*
			 * frame = new JFrame(); JOptionPane.showMessageDialog(frame,
			 * "Unable To Connect DataBase", "ERROR!",
			 * JOptionPane.ERROR_MESSAGE);
			 */
		} catch (Exception e) {
			log.error("connection1" + e.getMessage());
			System.out.println("GENERAL EXCEPTION");
			// Logging.debug("Error" + e);
			/*
			 * frame = new JFrame(); JOptionPane.showMessageDialog(frame,
			 * "Unable To Connect DataBase", "ERROR!",
			 * JOptionPane.ERROR_MESSAGE);
			 */
		}
		return connection;
	}

	/*
	 * public Connection getConnectionForTransaction() { try {
	 * 
	 * Properties rs = new Properties(); rs.load(new
	 * FileInputStream(resourceurl+"resources/database.properties")); String
	 * machine = rs.getProperty("machine"); String port =
	 * rs.getProperty("port"); String database = rs.getProperty("database");
	 * String user = rs.getProperty("user"); String password =
	 * rs.getProperty("password");
	 * Class.forName("org.postgresql.Driver").newInstance(); String url =
	 * "jdbc:postgresql://" + machine + ":" + port + "/" + database;
	 * log.debug(conForTransaction); if(conForTransaction==null){
	 * log.debug("creating new connection object"); conForTransaction =
	 * DriverManager.getConnection(url, user, password);
	 * 
	 * } log.debug(url+user+password); } catch (SQLException e) {
	 * log.debug("connection" + e); frame = new JFrame();
	 * JOptionPane.showMessageDialog(frame, "Unable To Connect DataBase",
	 * "ERROR!", JOptionPane.ERROR_MESSAGE); //System.exit(0);
	 * 
	 * } catch (Exception e) { log.error("connection1" + e); }
	 * log.debug("connection object in getConnectionForTransaction() is : "
	 * +conForTransaction); return conForTransaction; }
	 */

	public Connection getConnectionForTransaction() throws Exception {
		Connection Compute = null;
		try {
			log.debug("Retriving a new Connection from pool");
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource) ic.lookup("java:comp/env/mypostgres");
			Compute = ds.getConnection();
			log.debug("Got connection from pool");

		} catch (Exception e) {
			log.error("Unable to get connection from pool.");
			log.error("This either means either Cygwin is not Up Or Tomcat \n is not properlly configured");
			// e.printStackTrace();
			throw new Exception(
					"Unable to Establish Connection with postgres Database");
		}
		log.debug("connection object in getConnectionForTransaction() is : "
				+ Compute);
		return Compute;
	}

	public Connection getConnectionForHistTransaction() throws Exception {
		try {
			log.debug("Retriving a new Connection from pool");
			/*
			 * Properties rs = new Properties(); rs.load(new
			 * FileInputStream(resourceurl+"resources/database.properties"));
			 * String machine = rs.getProperty("machine"); String port =
			 * rs.getProperty("port"); String database =
			 * rs.getProperty("database"); String user = rs.getProperty("user");
			 * String password = rs.getProperty("password");
			 * 
			 * String url = "jdbc:postgresql://" + machine + ":" + port + "/" +
			 * database;
			 */
			if (conForHistTransaction == null) {
				log.debug("creating new connection object");
				InitialContext ic = new InitialContext();
				log.debug("creating new connection object1");
				DataSource ds = (DataSource) ic
						.lookup("java:comp/env/mypostgres");
				log.debug("creating new connection object2");
				conForHistTransaction = ds.getConnection();
				log.debug("creating new connection object3");
			}
			// log.debug(url+user+password);

			log.debug("Got connection from pool");

		} catch (Exception e) {
			log.error("Unable to get connection from pool.");
			log.error("This either means either Cygwin is not Up Or Tomcat \n is not properlly configured");
			// e.printStackTrace();
			throw new Exception(
					"Unable to Establish Connection with postgres Database");
		}
		/*
		 * try {
		 * 
		 * Properties rs = new Properties(); rs.load(new
		 * FileInputStream(resourceurl+"resources/database.properties")); String
		 * machine = rs.getProperty("machine"); String port =
		 * rs.getProperty("port"); String database = rs.getProperty("database");
		 * String user = rs.getProperty("user"); String password =
		 * rs.getProperty("password");
		 * Class.forName("org.postgresql.Driver").newInstance(); String url =
		 * "jdbc:postgresql://" + machine + ":" + port + "/" + database;
		 * log.debug(conForHistTransaction); if(conForHistTransaction==null){
		 * log.debug("creating new connection object"); conForHistTransaction =
		 * DriverManager.getConnection(url, user, password);
		 * 
		 * } log.debug(url+user+password); } catch (SQLException e) {
		 * log.debug("connection" + e); frame = new JFrame();
		 * JOptionPane.showMessageDialog(frame, "Unable To Connect DataBase",
		 * "ERROR!", JOptionPane.ERROR_MESSAGE); //System.exit(0);
		 * 
		 * } catch (Exception e) { log.error("connection1" + e); }
		 */
		log.debug("connection object in getConnectionForHistTransaction() is : "
				+ conForHistTransaction);
		return conForHistTransaction;
	}

	public Connection getConnectionForMergerTransaction() throws Exception {
		try {
			log.debug("Retriving a new Connection from pool");
			/*
			 * Properties rs = new Properties(); rs.load(new
			 * FileInputStream(resourceurl+"resources/database.properties"));
			 * String machine = rs.getProperty("machine"); String port =
			 * rs.getProperty("port"); String database =
			 * rs.getProperty("database"); String user = rs.getProperty("user");
			 * String password = rs.getProperty("password");
			 * 
			 * String url = "jdbc:postgresql://" + machine + ":" + port + "/" +
			 * database;
			 */
			if (conForMergerTransaction == null) {
				log.debug("creating new connection object");
				InitialContext ic = new InitialContext();
				log.debug("creating new connection object1");
				DataSource ds = (DataSource) ic
						.lookup("java:comp/env/mypostgres");
				log.debug("creating new connection object2");
				conForMergerTransaction = ds.getConnection();
				log.debug("creating new connection object3");
			}
			// log.debug(url+user+password);

			log.debug("Got connection from pool");

		} catch (Exception e) {
			log.error("Unable to get connection from pool.");
			log.error("This either means either Cygwin is not Up Or Tomcat \n is not properlly configured");
			// e.printStackTrace();
			throw new Exception(
					"Unable to Establish Connection with postgres Database");
		}
		log.debug("connection object in getConnectionForMergeTransaction() is : "
				+ conForHistTransaction);
		return conForMergerTransaction;
	}

	public void exitConnection() {
		try {
			con.close();

		} catch (SQLException e) {
			log.debug("Closing connection" + e);
		}
	}

	/* Used for SELECT operation */
	/*
	 * public ResultSet select(String index) {
	 * 
	 * try{
	 * 
	 * //String Str=getQuery(index); st =con.createStatement();
	 * rst=st.executeQuery(); rst.next(); }catch(SQLException e){
	 * Logging.debug("Select"+e); } return rst; }
	 */

	public boolean checkId(String index, String id) {
		boolean b = false;
		try {
			String Query = ConnectInit.queries.getProperty(index);
			pst = con.prepareStatement(Query);
			pst.setString(1, id);
			rst = pst.executeQuery();
			log.debug("1");
			b = rst.next();

		} catch (SQLException e) {
			log.error("Select" + e);
		}
		return b;

	}

	/* Select for Login page */
	public ResultSet select(String index, String username, String password) {
		try {
			String Query = ConnectInit.queries.getProperty(index);
			log.debug("query in select is " + Query + "  con is " + con
					+ " username is " + username + " password is " + password);
			pst = con.prepareStatement(Query);
			pst.setString(1, username);
			pst.setString(2, password);
			log.debug("pst in select is " + pst);
			rst = pst.executeQuery();
			log.debug("rst in select is " + rst);
			log.debug("1");
		} catch (SQLException e) {
			log.error("Select" + e);
		}
		return rst;

	}

	/* Select for Login page */
	public ResultSet select(Connection connection, String index,
			String username, String password) {
		try {
			String Query = ConnectInit.queries.getProperty(index);
			log.debug("query in select is " + Query + "  con is " + con
					+ " username is " + username + " password is " + password);
			pst = connection.prepareStatement(Query);
			pst.setString(1, username);
			pst.setString(2, password);
			log.debug("pst in select is " + pst);
			rst = pst.executeQuery();
			log.debug("rst in select is " + rst);
			log.debug("1");
		} catch (SQLException e) {
			log.error("Select" + e.getMessage());
		}
		return rst;

	}

	/* Select for Login page */
	public ResultSet returnResult(String index, String index_id) {

		try {
			String Query = ConnectInit.queries.getProperty(index);
			log.debug(Query);

			// Change by Manoj Adekar for Dynamic connection using dynaCon
			// instead of con
			/*
			 * if(dynaCon==null) { dynaCon=getdbConnection(); }
			 */
			dynaCon = getdbConnection(); // changed by Pranay
			pst = dynaCon.prepareStatement(Query);
			pst.setString(1, index_id);
			rst = pst.executeQuery();
			log.debug("1");
		} catch (SQLException e) {
			log.error("Select" + e);
		}
		return rst;

	}

	/* Select for Login page */
	public ResultSet returnResult(Connection connection, String index,
			String index_id) {

		try {
			String Query = ConnectInit.queries.getProperty(index);
			log.debug(Query);
			pst = connection.prepareStatement(Query);
			pst.setString(1, index_id);
			rst = pst.executeQuery();
			log.debug("1");
		} catch (SQLException e) {
			log.error("Select" + e.getMessage());
		}
		return rst;

	}

	/**
	 * To get resultset for index composition report.
	 * 
	 * @param index
	 * @param index_id
	 * @return
	 */
	public ResultSet indexComposeResult(Connection connection, String index,
			String index_id) {
		String id1, id2;
		id1 = id2 = index_id;
		try {
			String Query = ConnectInit.queries.getProperty(index);
			log.debug(Query);
			pst = connection.prepareStatement(Query);
			pst.setString(1, id1);
			pst.setString(2, id2);
			pst.setString(3, id1);
			rst = pst.executeQuery();
			log.debug("1");
		} catch (SQLException e) {
			log.error("Select" + e.getMessage());
		}
		return rst;

	}

	public ResultSet stiockweightageLatestResult(Connection connection,
			String index, String index_id) {
		String id1, id2;
		id1 = id2 = index_id;
		try {
			String Query = ConnectInit.queries.getProperty(index);
			log.debug(Query);
			pst = connection.prepareStatement(Query);
			pst.setString(1, id1);
			pst.setString(2, id2);
			rst = pst.executeQuery();
			log.debug("1");
		} catch (SQLException e) {
			log.error("Select" + e.getMessage());
		}
		return rst;

	}

	public ResultSet returnResult(String index, String index_id,
			String fieldName) {

		try {
			String Query = ConnectInit.queries.getProperty(index);
			log.debug(Query);
			log.debug(fieldName);
			pst = con.prepareStatement(Query + fieldName);
			pst.setString(1, index_id);

			rst = pst.executeQuery();
			log.debug("1");
		} catch (SQLException e) {
			log.error("Select" + e);
		}
		return rst;

	}

	public ResultSet indwtResult(Connection connection, String index,
			String index_id, String fdate) {

		try {
			// String index_id2,index_id3;
			String Query = ConnectInit.queries.getProperty(index);
			log.debug(Query);
			// index_id2=index_id3=index_id;
			pst = connection.prepareStatement(Query);
			pst.setString(1, index_id);
			pst.setString(2, fdate);
			// pst.setString(3, index_id3);
			rst = pst.executeQuery();
			log.debug("1");
		} catch (SQLException e) {
			log.error("Select" + e.getMessage());
		}
		return rst;

	}

	public ResultSet indweightResult(String index, String index_id) {

		try {
			String index_id2, index_id3;
			String Query = ConnectInit.queries.getProperty(index);
			log.debug(Query);
			index_id2 = index_id3 = index_id;

			// Changes for Static Connection to Dyanamic connection : By Manoj
			// Adekar
			/*
			 * if (dynaCon == null) dynaCon = getdbConnection();
			 */
			dynaCon = getdbConnection();
			pst = dynaCon.prepareStatement(Query);
			pst.setString(1, index_id);
			pst.setString(2, index_id2);
			pst.setString(3, index_id3);

			rst = pst.executeQuery();
			log.debug("1");
		} catch (SQLException e) {
			log.error("Select" + e);
		}
		return rst;

	}

	public ResultSet companyweightageResult(String index, String index_id,
			String fdate) {

		try {
			String index_id2;
			String Query = ConnectInit.queries.getProperty(index);
			log.debug(Query);
			index_id2 = index_id;
			// Changes for Static Connection to Dyanamic connection : By Manoj
			// Adekar
			/*
			 * if (dynaCon == null) dynaCon = getdbConnection();
			 */
			dynaCon = getdbConnection();
			pst = dynaCon.prepareStatement(Query);
			pst.setString(1, index_id);
			pst.setString(2, fdate);
			pst.setString(3, index_id2);
			rst = pst.executeQuery();
			log.debug("1");
		} catch (SQLException e) {
			log.error("Select" + e);
		}
		return rst;

	}

	public ResultSet ModifiedcompanyweightageResult(String index,
			String index_id, String fdate) {

		try {
			// String index_id2;
			String Query = ConnectInit.queries.getProperty(index);
			log.debug(Query);
			// index_id2 = index_id;
			// Changes for Static Connection to Dyanamic connection : By Manoj
			// Adekar
			/*
			 * if (dynaCon == null) dynaCon = getdbConnection();
			 */
			dynaCon = getdbConnection();
			pst = dynaCon.prepareStatement(Query);

			pst.setString(1, index_id);
			pst.setString(2, fdate);
			rst = pst.executeQuery();
			log.debug("1");
		} catch (SQLException e) {
			log.error("Select" + e);
		}
		return rst;

	}

	public ResultSet highlowResult(String index, String sid, String fdate,
			String tdate) {
		try {
			log.debug("inside high low result");
			log.debug(fdate + " " + "  " + tdate + "  " + sid);
			String Query = ConnectInit.queries.getProperty(index);
			log.debug(Query);

			// Changes for Static Connection to Dyanamic connection : By Manoj
			// Adekar
			/*
			 * if (dynaCon == null) dynaCon = getdbConnection();
			 */
			dynaCon = getdbConnection();
			pst = dynaCon.prepareStatement(Query);
			pst.setString(1, sid);
			pst.setString(2, fdate);
			pst.setString(3, tdate);
			rst = pst.executeQuery();
			log.debug("1");
		} catch (SQLException e) {
			log.error("Error : " + e.getMessage());
		}
		return rst;

	}

	public ResultSet highlowResultmktStatus(String index, String sid,
			String fdate, String tdate) {
		try {
			log.debug("inside high low result highlowResultmktStatus");
			log.debug(fdate + " " + "  " + tdate + "  " + sid);
			String Query = ConnectInit.queries.getProperty(index);
			log.debug(Query);
			// Change by Manoj Adekar for Dynamic connection using
			// getdbConnection() instead of getConnection()

			/*
			 * if (dynaCon == null) dynaCon = getdbConnection();
			 */
			dynaCon = getdbConnection();
			pst = dynaCon.prepareStatement(Query);
			pst.setString(1, fdate);
			pst.setString(2, tdate);
			pst.setString(3, sid);
			rst = pst.executeQuery();
			log.debug("1 highlowResultmktStatus");
		} catch (SQLException e) {
			log.error("Error : " + e.getMessage());
		}
		return rst;

	}

	/**
	 * to get the resultset for stock having traded volume greater than or equal
	 * to the traded volume parameter value passed (exchange wise/index wise).
	 * 
	 * @param index
	 * @param sid
	 * @param trd_volume
	 * @param fdate
	 * @param tdate
	 * @return
	 */
	public ResultSet tradedVolumeResult(String index, String sid,
			String trd_volume, String fdate, String tdate) {
		try {
			log.debug("inside tradedVolumeResult");
			log.debug(fdate + " " + "  " + tdate + " id  " + sid
					+ " traded vol. " + trd_volume);
			String Query = ConnectInit.queries.getProperty(index);
			log.debug(Query);
			// Change by Manoj Adekar for Dynamic connection using
			// getdbConnection() instead of getConnection()
			/*
			 * if (dynaCon == null) dynaCon = getdbConnection();
			 */
			dynaCon = getdbConnection();
			pst = dynaCon.prepareStatement(Query);

			pst.setString(1, sid);
			pst.setString(2, trd_volume);
			pst.setString(3, fdate);
			pst.setString(4, tdate);
			rst = pst.executeQuery();
		} catch (SQLException e) {
			log.error("Error :" + e.getMessage());
		}
		return rst;
	}

	public ResultSet changeInStockDetailResult(String index, String sid,
			String fdate, String tdate) {
		try {
			log.debug("inside high low result");
			log.debug(fdate + " " + "  " + tdate + "  " + sid);
			String Query = ConnectInit.queries.getProperty(index);
			log.debug(Query);
			// Changes for Static Connection to Dyanamic connection : By Manoj
			// Adekar
			/*
			 * if (dynaCon == null) dynaCon = getdbConnection();
			 */
			dynaCon = getdbConnection();
			pst = dynaCon.prepareStatement(Query);

			pst.setString(1, sid);
			pst.setString(2, fdate);
			pst.setString(3, tdate);
			rst = pst.executeQuery();
			log.debug("1");
		} catch (SQLException e) {
			log.error("Error :" + e.getMessage());
		}
		return rst;

	}

	public ResultSet IndexPerformanceResult(String index, String date,
			String m1date, String m3date, String m6date, String y1date,
			String useridS) {
		try {
			log.debug("inside index performance result");

			log.debug(date + " " + "  " + m1date + "  " + m3date + " " + m6date
					+ " " + y1date);
			String Query = ConnectInit.queries.getProperty(index);
			log.debug(Query);
			// Changes for Static Connection to Dyanamic connection : By Manoj
			// Adekar
			/*
			 * if (dynaCon == null) dynaCon = getdbConnection();
			 */
			dynaCon = getdbConnection();
			pst = dynaCon.prepareStatement(Query);

			pst.setString(1, date);
			pst.setString(2, m1date);
			pst.setString(3, m3date);
			pst.setString(4, m6date);
			pst.setString(5, y1date);
			pst.setString(6, useridS);
			rst = pst.executeQuery();
			log.debug("1");
		} catch (SQLException e) {
			log.error("Error : " + e.getMessage());
		}
		return rst;

	}

	public ResultSet StockcontriIndexResult(String index, String sid,
			String fdate, String tdate) {

		try {
			String id1, id2, id3, id4, id5, id6, fd1, fd2, td1, td2;
			id1 = id2 = id3 = id4 = id5 = id6 = sid;
			fd1 = fd2 = fdate;
			td1 = td2 = tdate;
			log.debug("inside StockcontriIndexResult result");
			log.debug(fdate + " " + "  " + tdate + "  " + sid);
			String Query = ConnectInit.queries.getProperty(index);
			log.debug(Query);
			// Changes for static connection to dynamic connection :By Manoj
			// Adekar
			/*
			 * if (dynaCon == null) { dynaCon = getdbConnection(); }
			 */
			dynaCon = getdbConnection();
			pst = dynaCon.prepareStatement(Query);
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
			log.debug("1");
		} catch (SQLException e) {
			log.error("Error : " + e.getMessage());
		}
		return rst;

	}

	public ResultSet IndexCorrelationResult(String index, String id1,
			String id2, String fdate, String tdate) {
		try {
			log.debug("inside IndexCorrelationResult result");
			log.debug(fdate + " " + "  " + tdate + "  " + id1 + " " + id2);
			String Query = ConnectInit.queries.getProperty(index);
			log.debug(Query);
			// Changes for static connection to dynamic connection :By Manoj
			// Adekar
			/*
			 * if (dynaCon == null) dynaCon = getdbConnection();
			 */dynaCon = getdbConnection();
			pst = dynaCon.prepareStatement(Query);
			pst.setString(1, id1);
			pst.setString(2, id2);
			pst.setString(3, id1);
			pst.setString(4, id2);
			pst.setString(5, fdate);
			pst.setString(6, tdate);
			rst = pst.executeQuery();
			log.debug("1");
		} catch (SQLException e) {
			log.error("Error : " + e.getMessage());
		}
		return rst;
	}

	public ResultSet StockPerformanceResult(String index, String sid,
			String fdate, String tdate) {

		try {
			log.debug("inside StockPerformanceResult result");
			log.debug(fdate + " " + "  " + tdate + "  " + sid);
			String Query = ConnectInit.queries.getProperty(index);
			log.debug(Query);
			// Changes for static connection to dynamic connection :By Manoj
			// Adekar
			/*
			 * if (dynaCon == null) dynaCon = getdbConnection();
			 */
			dynaCon = getdbConnection();

			pst = dynaCon.prepareStatement(Query);

			pst.setString(1, sid);
			pst.setString(2, fdate);
			pst.setString(3, tdate);
			rst = pst.executeQuery();
			log.debug("1");
		} catch (SQLException e) {
			log.error("Error : " + e.getMessage());
		}
		return rst;

	}

	public ResultSet StockcontriSidlResult(String index, String sid,
			String fdate, String tdate) {

		try {
			String id1, id2, id3;
			id1 = id2 = id3 = sid;
			log.debug("inside StockcontriIndexResult result");
			log.debug(fdate + " " + "  " + tdate + "  " + sid);
			String Query = ConnectInit.queries.getProperty(index);
			log.debug(Query);
			// Changes for static connection to dynamic connection :By Manoj
			// Adekar
			/*
			 * if (dynaCon == null) dynaCon = getdbConnection();
			 */
			dynaCon = getdbConnection();
			pst = dynaCon.prepareStatement(Query);

			pst.setString(1, id1);
			pst.setString(2, id2);
			pst.setString(3, tdate);
			pst.setString(4, id3);
			pst.setString(5, fdate);
			rst = pst.executeQuery();
			log.debug("1");
		} catch (SQLException e) {
			log.error("Error : " + e.getMessage());
		}
		return rst;

	}

	public ResultSet stockcontriIndResult(String index, String Index_id,
			String sid, String fdate, String tdate) {

		try {
			String id1, id2, id3, id4, fd1, fd2, td1, td2, s_id1, s_id2;
			id1 = id2 = id3 = id4 = Index_id;
			s_id1 = s_id2 = sid;
			fd1 = fd2 = fdate;
			td1 = td2 = tdate;
			log.debug("inside StockcontriIndexResult result");
			log.debug(fdate + " " + "  " + tdate + "  " + sid);
			String Query = ConnectInit.queries.getProperty(index);
			log.debug(Query);
			// Changes for static connection to dynamic connection :By Manoj
			// Adekar
			/*
			 * if (dynaCon == null) dynaCon = getdbConnection();
			 */
			dynaCon = getdbConnection();
			pst = dynaCon.prepareStatement(Query);
			pst.setString(1, id1);
			pst.setString(2, id2);
			pst.setString(3, td1);
			pst.setString(4, id3);
			pst.setString(5, id4);
			pst.setString(6, fd1);
			pst.setString(7, s_id1);
			pst.setString(8, td2);
			pst.setString(9, s_id2);
			pst.setString(10, fd2);
			rst = pst.executeQuery();
			log.debug("1");
		} catch (SQLException e) {
			log.error("Error : " + e.getMessage());
		}finally{
			try {
				if (dynaCon != null)
					dynaCon.close();
				if (pst != null)
					pst.close();
				if (rst != null)
					rst.close();
			} catch (Exception ee) {
				log.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		return rst;

	}

	public ResultSet delistingResult(String index, String sid, String fdate,
			String tdate) {

		try {
			log.debug("inside delisting result");
			log.debug(fdate + " " + "  " + tdate + "  " + sid);
			String Query = ConnectInit.queries.getProperty(index);
			log.debug(Query);
			// Changes for static connection to dynamic connection :By Manoj
			// Adekar
			/*
			 * if (dynaCon == null) dynaCon = getdbConnection();
			 */
			dynaCon = getdbConnection();
			pst = dynaCon.prepareStatement(Query);

			pst.setString(1, sid);
			pst.setString(2, fdate);
			pst.setString(3, tdate);
			rst = pst.executeQuery();
			log.debug("1");
		} catch (SQLException e) {
			log.error("Error : " + e.getMessage());
		}
		return rst;

	}

	public ResultSet compareResult(String index, String sid, String fdate) {

		try {
			String Query = ConnectInit.queries.getProperty(index);
			log.debug(Query);
			// Changes for static connection to dynamic connection :By Manoj
			// Adekar
			/*
			 * if (dynaCon == null) dynaCon = getdbConnection();
			 */
			dynaCon = getdbConnection();
			pst = dynaCon.prepareStatement(Query);
			pst.setString(1, fdate);
			pst.setString(2, sid);
			rst = pst.executeQuery();
			log.debug("1");
		} catch (SQLException e) {
			log.error("Error : " + e.getMessage());
		}
		return rst;

	}

	/* Method to get list of stocks */

	public ResultSet getStockList(Connection connection, String index,
			String sid) {

		try {
			// Connection changed from static to local on 25 AUG 06 by
			// P.Bhende...
			String Query = ConnectInit.queries.getProperty(index);
			log.debug(Query);
			if (connection == null)
				connection = getdbConnection();

			pst = connection.prepareStatement(Query);
			pst.setString(1, sid);
			rst = pst.executeQuery();
			// log.debug("1");
		} catch (SQLException e) {
			log.error("Error : " + e.getMessage());
		}
		return rst;

	}

	/* Method to get list of latest divisor for indices */

	public ResultSet divisorResult(String index, String user) {

		try {

			String Query = ConnectInit.queries.getProperty(index);
			// Changes for static connection to dynamic connection :By Manoj
			// Adekar
			/*
			 * if (dynaCon == null) dynaCon = getdbConnection();
			 */
			dynaCon = getdbConnection();
			PreparedStatement pst = dynaCon.prepareStatement(Query);
			pst.setString(1, user);
			// pst.setString(1, sid);
			rst = pst.executeQuery();
			log.debug("1");
		} catch (SQLException e) {
			log.error("Error : " + e.getMessage());
		}
		return rst;

	}

	/* Method to Change password */
	/**
	 * @param index
	 * @param id
	 * @param pass
	 * @param pass1
	 * @return
	 */
	public ResultSet changepass(String index, String id, String pass,
			String pass1, Connection connection) {

		try {

			log.debug(id + pass);
			String Query = ConnectInit.queries
					.getProperty("change_user_password");
			log.debug("pass " + pass + " userid " + " password " + pass1);
			pst = connection.prepareStatement(Query);
			pst.setString(1, pass);
			pst.setString(2, id);
			pst.setString(3, pass1);
			rst = pst.executeQuery();
			log.debug(rst);

		} catch (SQLException e) {
			log.error("Select11" + e);
		}
		return rst;

	}

	/* Select for query with two arguments */
	public ResultSet forgotpass(String index, String id, String country,
			String dob, String zipcode) {

		try {
			log.debug(id + country + dob + zipcode + "11");
			String Query = ConnectInit.queries.getProperty(index);
			log.debug(id + country + dob + zipcode + Query + "22");
			pst = con.prepareStatement(Query);
			pst.setString(1, dob);
			pst.setString(2, zipcode);
			pst.setString(3, country);
			log.debug("forgotpass");
			pst.setString(4, id);

			log.debug(id + country + dob + zipcode + Query);
			rst = pst.executeQuery();
			log.debug("forgotpass1");

		} catch (SQLException e) {
			log.error("Select password" + e);
		}
		log.debug("out of try1");
		return rst;

	}

	public ResultSet getPass(String index, String id, String ans, String id2,
			String que) {

		try {
			log.debug("\tGetpass Starts here");
			log.debug(id + id2 + que + ans + "11");
			String Query = ConnectInit.queries.getProperty(index);
			log.debug(id + id2 + que + ans + Query + "22");
			pst = con.prepareStatement(Query);
			pst.setString(1, id);
			pst.setString(2, ans);
			pst.setString(3, id2);
			pst.setString(4, que);
			log.debug("forgotpass");

			log.debug("method getpass\t" + id + id2 + ans + que + Query);
			rst = pst.executeQuery();
			log.debug("forgotpass1");

		} catch (SQLException e) {
			log.error("Select password" + e);
		}
		log.debug("out of try1");
		return rst;

	}

	public ResultSet getPass(String index, String id) {
		return rst;

	}

	/* Insert */
	/*
	 * public int setData(String index) { int count=0; try{
	 * 
	 * String Str=getQuery(index); PreparedStatement pst
	 * =con.prepareStatement(Str); count=pst.executeUpdate(Str); rst.next();
	 * //Logging.debug(rst.getString(1));
	 * 
	 * }catch(SQLException e){ Logging.debug(e); } return count; }
	 */

	/*
	 * @author W
	 * 
	 * 
	 * Window - Preferences - Java - Code Style - Code Templates
	 */

	/*
	 * used for insert,update,delete operation use this
	 */
	/*
	 * public boolean insert(String index) { int count=0; try{
	 * 
	 * String Str=getQuery(index); st =con.createStatement(); st.execute(Str);
	 * Logging.debug(count=(st.getUpdateCount()));
	 * 
	 * }catch(SQLException e){ Logging.debug(e); }
	 * 
	 * if(count>0) return true; else return false; }
	 */

	/* Use this */
	public boolean insert(Data_userdetails data, String index, String id) {
		int count = 0;
		try {

			log.debug("user id is \t" + id);

			// user_id,first_name,middle_name,last_name,date_of_birth,
			// designation,branch,add1,add2,city,country,zipcode,phone_no,
			// mobile_no,username,password,email,gender,security_question,answer
			String Query = ConnectInit.queries.getProperty(index);
			pst = con.prepareStatement(Query);
			pst.setString(1, id);
			pst.setString(2, data.firstname);
			if (data.middlename == null || data.middlename.trim().length() == 0) {
				pst.setString(3, null);
			} else {
				pst.setString(3, data.middlename);
			}
			if (data.lastname == null || data.lastname.trim().length() == 0) {
				pst.setString(4, null);
			} else {
				pst.setString(4, data.lastname);
			}

			pst.setString(5, data.dob);
			if (data.designation == null
					|| data.designation.trim().length() == 0) {
				pst.setString(6, null);
			} else {
				pst.setString(6, data.designation);
			}
			if (data.branch == null || data.branch.trim().length() == 0) {
				pst.setString(7, null);
			} else {
				pst.setString(7, data.branch);
			}

			pst.setString(8, data.address1);
			if (data.address2 == null || data.address2.trim().length() == 0) {
				pst.setString(9, null);
			} else {
				pst.setString(9, data.address2);
			}

			pst.setString(10, data.city);
			pst.setString(11, data.country);
			pst.setString(12, data.zipcode);
			if (data.phone == null || data.phone.trim().length() == 0) {
				pst.setString(13, null);
			} else {
				pst.setString(13, data.phone);
			}
			if (data.mobile == null || data.mobile.trim().length() == 0) {
				pst.setString(14, null);
			} else {
				pst.setString(14, data.mobile);
			}

			pst.setString(15, data.userid);
			pst.setString(16, data.password);
			pst.setString(17, data.email);
			String gender = new Character(data.gender).toString();
			pst.setString(18, gender);
			pst.setString(19, data.sequrityque);
			pst.setString(20, data.answer);
			String Client_name = data.clientname;
			log.debug(data.country + data.clientname + data.sequrityque
					+ data.branch);
			if (Client_name.equals("0")) {
				pst.setString(21, null);
			} else {
				pst.setString(21, Client_name);

			}
			count = pst.executeUpdate();

		} catch (SQLException e) {
			log.debug(e);
			/*
			 * frame = new JFrame(); JOptionPane.showMessageDialog(frame,
			 * "Unable To Connect DataBase", "ERROR!",
			 * JOptionPane.ERROR_MESSAGE);
			 */
		}
		if (count > 0)
			return true;
		else
			return false;

	}

	public int getUserId(String index) {
		int j = 0;
		try {
			// String Query = queries.getProperty(index);
			pst = Connect.con.prepareStatement(index);
			rst = pst.executeQuery();
			rst.first();
			Integer i = new Integer(rst.getString(1));
			j = i.intValue();

		} catch (SQLException e) {
			log.error("Select" + e);
		}
		return j;

	}

	public ResultSet returnResultcorrelation(String Query) {
		try {
			log.debug(" " + Query);
			// Changes for static connection to dynamic connection :By Manoj
			// Adekar
			/*
			 * if (dynaCon == null) { dynaCon = getdbConnection(); }
			 */
			dynaCon = getdbConnection();
			pst = dynaCon.prepareStatement(Query);

			rst = pst.executeQuery();
		} catch (SQLException e) {
			log.error("SQL Error : " + e.getMessage());
		}
		log.debug("out of try");
		return rst;
	}

	public ResultSet getClientList(String index) {
		try {
			/*
			 * queries = new Properties(); try { log.debug("contructor"+index);
			 * queries.load(new FileInputStream("resources/query.properties"));
			 * } catch (IOException e) { log.debug("IOException"); log.debug(e);
			 * } log.debug("vivek1");
			 */
			String Query = ConnectInit.queries.getProperty(index);
			log.debug(" " + Query);
			// Changes for static connection to dynamic connection :By Manoj
			// Adekar
			/*
			 * if (dynaCon == null) { dynaCon = getdbConnection(); }
			 */
			dynaCon = getdbConnection();
			pst = dynaCon.prepareStatement(Query);

			rst = pst.executeQuery();
		} catch (SQLException e) {
			log.error("Select password" + e);
		}
		log.debug("out of try");
		return rst;
	}

	/**
	 * method to return resulset if connection and query key is passed to it.
	 * 
	 * @param connection
	 * @param index
	 * @return
	 */
	public ResultSet getClientList(Connection connection, String index) {
		try {
			String Query = ConnectInit.queries.getProperty(index);
			log.debug(" " + Query);
			pst = connection.prepareStatement(Query);
			rst = pst.executeQuery();
		} catch (SQLException e) {
			log.error("Error : " + e.getMessage());
		}
		log.debug("out of try");
		return rst;
	}

	public ResultSet getStockBondsList(Connection connection, String query1) {
		try {
			String Query = ConnectInit.queries.getProperty(query1);
			log.debug(" " + Query);
			pst = connection.prepareStatement(Query);
			rst = pst.executeQuery();
		} catch (SQLException e) {
			log.error("Error : " + e.getMessage());
		}
		log.debug("out of try");
		return rst;
	}

	public ResultSet getIndexDetails(String index, String dt) {
		try {
			log.debug(dt);
			String dt1 = dt;
			// queries = new Properties();
			/*
			 * try { log.debug("contructor" + index); queries.load(new
			 * FileInputStream(resourceurl+ "resources/query.properties")); }
			 * catch (IOException e) { log.debug("IOException"); log.debug(e); }
			 */
			// Changes for static connection to dynamic connection :By Manoj
			// Adekar
			/*
			 * if (dynaCon == null) dynaCon = getdbConnection();
			 */
			dynaCon = getdbConnection();
			String Query = ConnectInit.queries.getProperty(index);
			log.debug("query is " + Query);
			pst = dynaCon.prepareStatement(Query);
			pst.setString(1, dt);
			pst.setString(2, dt1);
			rst = pst.executeQuery();
		} catch (SQLException e) {
			log.error("Select password" + e);
		}
		log.debug("out of try");
		return rst;
	}

	// Change by Manoj Adekar for Index List Report
	public ResultSet getIndexDetails2(String index, String dt, String userid) {
		try {
			log.debug(dt);
			String dt1 = dt;
			// queries = new Properties();
			/*
			 * try { log.debug("contructor" + index); queries.load(new
			 * FileInputStream(resourceurl + "resources/query.properties")); }
			 * catch (IOException e) { log.debug("IOException"); log.debug(e); }
			 */
			// Changes for static connection to dynamic connection :By Manoj
			// Adekar
			/*
			 * if (dynaCon == null) dynaCon = getdbConnection();
			 */
			dynaCon = getdbConnection();
			String Query = ConnectInit.queries.getProperty(index);
			log.debug("query is " + Query);
			pst = dynaCon.prepareStatement(Query);
			pst.setString(1, dt);
			pst.setString(2, userid);
			pst.setString(3, dt1);
			rst = pst.executeQuery();
		} catch (SQLException e) {
			log.error("Select password" + e);
		}
		log.debug("out of try");
		return rst;
	}

	public ResultSet getIndexValues(String index, String dt) {
		try {
			log.debug(dt);
			// String dt1 = dt;
			// queries = new Properties();
			/*
			 * try { log.debug("contructor" + index); queries.load(new
			 * FileInputStream(resourceurl + "resources/query.properties")); }
			 * catch (IOException e) { log.debug("IOException"); log.debug(e); }
			 */
			/*
			 * if (con == null) con = getConnection();
			 */
			con = getConnection();
			String Query = ConnectInit.queries.getProperty(index);
			log.debug("query is " + Query);
			pst = con.prepareStatement(Query);
			pst.setString(1, dt);
			// pst.setString(2,dt1);
			rst = pst.executeQuery();
		} catch (SQLException e) {
			log.error("Select password" + e);
		}
		log.debug("out of try");
		return rst;
	}

	public double getIndexValue(String index, String dt, String indid) {

		// Connection con=null;
		try {
			log.debug(dt);
			String indid1 = indid;
			// Statement stmt;
			// queries = new Properties();
			/*
			 * try { log.debug("contructor" + index); queries.load(new
			 * FileInputStream(resourceurl + "resources/query.properties")); }
			 * 
			 * catch (IOException e) { log.debug("IOException"); log.debug(e); }
			 */
			// Change by Manoj Adekar for Dynamic connection using
			// getdbConnection() instead of getConnection()
			/*
			 * if (dynaCon == null) { dynaCon = getdbConnection(); }
			 */
			dynaCon = getdbConnection();
			String Query = ConnectInit.queries.getProperty(index);
			log.debug("query is " + Query);
			// stmt=con.createStatement();
			// ResultSet rs=stmt.executeQuery("Query");
			pst = dynaCon.prepareStatement(Query);
			pst.setString(1, dt);
			pst.setString(2, indid);
			pst.setString(3, indid1);
			rst = pst.executeQuery();
			str11 = 0.00;
			while (rst.next()) {
				str11 = rst.getDouble("index_closing_value");

			}

		} catch (SQLException e) {
			log.error("Select password" + e);
		}
		finally {
			try {
				if (dynaCon != null)
					dynaCon.close();
				if (pst != null)
					pst.close();
				if (rst != null)
					rst.close();
			} catch (Exception ee) {
				log.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		log.debug("out of try");
		return str11;
	}

	public String getMaxDate(String index, String dt, String indid) {

		try {
			log.debug(dt);
			// String dt1=dt;
			// Statement stmt;
			// queries = new Properties();
			/*
			 * try { log.debug("contructor" + index); queries.load(new
			 * FileInputStream(resourceurl + "resources/query.properties")); }
			 * 
			 * catch (IOException e) { log.debug("IOException"); log.debug(e); }
			 */
			// Change by Manoj Adekar for Dynamic connection using
			// getdbConnection() instead of getConnection()
			/*
			 * if (dynaCon == null) { dynaCon = getdbConnection(); }
			 */
			dynaCon = getdbConnection();
			String Query = ConnectInit.queries.getProperty(index);
			log.debug("query is " + Query);
			// stmt=dynaCon.createStatement();
			// ResultSet rs=stmt.executeQuery("Query");
			pst = dynaCon.prepareStatement(Query);
			pst.setString(1, dt);
			pst.setString(2, indid);
			rst = pst.executeQuery();

			Date ndt = new Date();
			while (rst.next()) {
				// sdate=rst.getString("maxdate1");

				ndt = rst.getDate("maxdate1");
				SimpleDateFormat ft1 = new SimpleDateFormat("dd-MM-yyyy");
				String newdate = ft1.format(ndt);
				strdt = newdate;

			}

		} catch (SQLException e) {
			log.error("Select password" + e);
		}finally {
			try {
				if (dynaCon != null)
					dynaCon.close();
				if (pst != null)
					pst.close();
				if (rst != null)
					rst.close();
			} catch (Exception ee) {
				log.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}

		log.debug("out of try");
		return strdt;
	}

	public ResultSet getLatestIndexDetails(String index, String dt, String user) {
		/*
		 * try { log.debug(dt);
		 * 
		 * queries = new Properties();
		 * 
		 * if(con==null) con=getConnection();
		 */

		log.debug(dt);
		// queries = new Properties();
		// Connection con=null;
		// Connect c = ConnectInit.getConnect();
		// Change by Manoj Adekar for Dynamic connection using getdbConnection()
		// instead of getConnection()
		try {

			/*
			 * try {
			 * 
			 * log.debug("contructor" + index); queries.load(new
			 * FileInputStream(resourceurl + "resources/query.properties")); }
			 * catch (IOException e) { log.debug("IOException");
			 * log.error("Error : " + e.getMessage()); }
			 */
			dynaCon = getdbConnection();
			String Query = ConnectInit.queries.getProperty(index);
			log.debug("query is " + Query);
			pst = dynaCon.prepareStatement(Query);
			pst.setString(1, dt);
			pst.setString(2, user);
			rst = pst.executeQuery();
		} catch (SQLException e) {
			log.error("Error in Connection :" + e);
		}
		
		log.debug("out of try");
		return rst;
	}

	public ResultSet getLatestIndexDetails1(String index, String dt) {
		/*
		 * try { log.debug(dt);
		 * 
		 * queries = new Properties(); if(dynaCon==null)
		 * dynaCon=getConnection();
		 */

		log.debug(dt);
		// queries = new Properties();
		// Connection dynaCon=null;
		// Connect c = ConnectInit.getConnect();
		// Change by Manoj Adekar for Dynamic connection using getdbConnection()
		// instead of getConnection()
		try {
			/*
			 * if (dynaCon == null) { dynaCon = getdbConnection(); }
			 */
			/*
			 * try { log.debug("contructor" + index); queries.load(new
			 * FileInputStream(resourceurl + "resources/query.properties")); }
			 * catch (IOException e) { log.debug("IOException");
			 * log.error("Error : " + e.getMessage()); }
			 */
			String Query = ConnectInit.queries.getProperty(index);
			log.debug("query is " + Query);
			// Changes for static connection to dynamic connection :By Manoj
			// Adekar
			/*
			 * if (dynaCon == null) dynaCon = getdbConnection();
			 */
			dynaCon = getdbConnection();
			pst = dynaCon.prepareStatement(Query);
			pst.setString(1, dt);
			rst = pst.executeQuery();
		} catch (SQLException e) {
			log.error("Select password" + e);
		}finally {
			try {
				if (dynaCon != null)
					dynaCon.close();
				if (pst != null)
					pst.close();
				if (rst != null)
					rst.close();
			} catch (Exception ee) {
				log.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}

		log.debug("out of try");
		return rst;
	}

	/*
	 * public static void main(String z[]) { app.Connect c = new app.Connect();
	 * c.getConnection(); c.select("get_username_and_password", "sudhir",
	 * "panhware"); }
	 */

	public static String getPropertiespath(String path) {
		if (resourceurl != null) {
			/*
			 * Following code fragement added on 23-11-07 for Linux
			 */
			int l = 0;
			int len = 0;
			// log.debug("contructor check  :  =========   "+resourceurl+"resources/query.properties");
			if (os_name.startsWith("Windows")) {
				// Logging.debug("**Windows OS**");
			} else {
				l = resourceurl.lastIndexOf(":");
				len = resourceurl.length();
				resourceurl = resourceurl.substring(l + 1, len);
			}
			File f = new File(resourceurl + "resources/query.properties");

			// Logging.debug("Inside getPropertiespath For OS : File "+resourceurl+"resources/query.properties");
			if (f.exists()) {
				return resourceurl;
			}
		}
		String resourcepth = null;
		// Logging.debug("path : " + path);
		java.net.URL imgURL = Connect.class.getResource(path);

		resourcepth = imgURL.toString();

		// resourceurl = resourceurl.substring(6);
		try {
			resourcepth = resourcepth
					.substring(resourcepth.lastIndexOf(":") - 1);

			resourcepth = resourcepth.substring(0,
					resourcepth.lastIndexOf("classes"));

			if (os_name.startsWith("Windows")) {
				resourcepth = resourcepth + "classes\\"; // for windows
			} else {
				resourcepth = resourcepth + "classes/"; // for linux
			}

			resourcepth = resourcepth.replaceAll("%20", " ");

			File resource = new File(resourcepth);
			if (resource.exists()) {

				// + resource.exists());
			} else {
				// Logging.debug("Resource " + resourcepth + "  "
				// + resource.exists());
			}
			if (resourcepth != null) {
				resourceurl = resourcepth;
				return resourcepth;
			} else {
				log.debug("Couldn't find file: " + path);
				System.err.println("Couldn't find file: " + path);
				return resourcepth;
			}
		} catch (Exception e) {
			// Logging.debug("In Catch Block , resourceurl is : " +
			// resourcepth);

			return resourcepth;
		}
	}

	public static String getCoolMenuspath() {
		coolmenus = resourceurl;

		coolmenus = coolmenus.substring(0, resourceurl.lastIndexOf("classes"));// For
		// Windows
		if (os_name.startsWith("Windows")) {
			coolmenus = coolmenus + "classes\\"; // for windows
		} else {
			coolmenus = coolmenus + "classes/"; // for linuxx
		}
		// Logging.debug("coolmenus : " + coolmenus);

		File resource = new File(coolmenus);
		if (resource.exists()) {
			// Logging.debug("Resource " + coolmenus + " "
			// + resource.exists());
		} else {
			log.debug("Resource " + coolmenus + " " + resource.exists());
		}
		if (coolmenus != null) {
			return coolmenus;
		} else {
			log.debug("Couldn't find file : " + coolmenus);
			System.err.println("Couldn't find file : " + coolmenus);
			return resourceurl;
		}
	}

	public ResultSet corporateDetails(String index1, String sid, String fdate,
			String toDate) {
		try {
			log.debug("inside high low result");
			log.debug(fdate + " " + "    " + sid);
			String Query = ConnectInit.queries.getProperty(index1);
			log.debug(Query);
			// Changes for the dynamic connection by Manaoj Adekar
			/*
			 * if (dynaCon == null) dynaCon = getdbConnection();
			 */
			dynaCon = getdbConnection();
			pst = dynaCon.prepareStatement(Query);
			pst.setString(1, sid);
			pst.setString(2, fdate);
			pst.setString(3, toDate);
			rst = pst.executeQuery();
			log.debug("1");
		} catch (SQLException e) {
			log.error("Error : " + e.getMessage());
		}
		return rst;

	}

	public ResultSet Top10Details(String index2, String sid, String fdate,
			String toDate) {
		try {
			log.debug("inside Top 10 result");
			log.debug(fdate + "  " + sid + "  ");
			String Query = ConnectInit.queries.getProperty(index2);
			log.debug(Query);
			pst = con.prepareStatement(Query);
			pst.setString(1, sid);
			pst.setString(2, fdate);
			pst.setString(3, toDate);
			rst = pst.executeQuery();
			log.debug("1");
		} catch (SQLException e) {
			log.error("Error : " + e.getMessage());
		}
		return rst;

	}

	public void closeDynaCon() {
		try {
			// Changes for static connection to dynamic connection :By Manoj
			// Adekar
			/*
			 * if (dynaCon != null) { dynaCon.close(); }
			 */
			dynaCon.close();
		} catch (Exception e) {
			log.debug("Error while Closing Dynamic Connection" + e.getMessage());
		}
	}
}