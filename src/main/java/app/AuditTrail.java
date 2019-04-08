/*
 * Created on Jan 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.harrier.initializeation.ConnectInit;

/**
 * @author sunil
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class AuditTrail {
	// private static Connection con = null;
	static Logger Logging = Logger.getLogger(AuditTrail.class);
	public static Hashtable table;

	public static String old_values = null;

	public static String user_id = null;

	// Properties prop;
	// static Connect con1 = ConnectInit.getConnect();

	public static void StoreImfPageLoad(LogonForm form, String act_id) {
		// Connect con = ConnectInit.getConnect();
		Connect con = ConnectInit.getConnect();
		// Connect con = ConnectInit.getConnect();
		Connection connection = null;
		int audit_trail_id = 0;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		try {
			if (connection == null) {
				connection = con.getdbConnection();
			}
			Logging.debug("activity id is " + act_id);
			SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
			Date dt = new Date();
			String l_date = fr.format(dt).toString();
			Logging.debug("date is " + l_date);
			/*
			 * String time = dt.getHours() + ":" + dt.getMinutes() + ":" +
			 * dt.getSeconds();
			 */// modified by Pranay
			String time = Calendar.HOUR_OF_DAY + ":" + Calendar.MINUTE + ":"
					+ Calendar.SECOND;
			Logging.debug("time is " + time);
			String user_id = getUserId(form.getUsername(), form.getPassword());
			Logging.debug("user id is " + user_id + " con is " + con);
			stmt = Connect.con.createStatement();
			rs = stmt.executeQuery("SELECT NEXTVAL('audit_trail_id')");
			Logging.debug("rs for nextval id is " + rs);
			while (rs.next()) {
				audit_trail_id = rs.getInt(1);
			}
			Logging.debug("audit trail id is " + audit_trail_id);
			pst = Connect.con.prepareStatement(ConnectInit.queries
					.getProperty("insert_into_audit_trail"));
			pst.setInt(1, audit_trail_id);
			pst.setString(2, time);
			pst.setString(3, null);
			pst.setString(4, user_id);
			pst.setString(5, l_date);
			pst.setString(6, act_id);
			pst.executeUpdate();
		} catch (SQLException e) {
			Logging.error("SQL Exception : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (stmt != null)
					stmt.close();
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error("Error : Unable to close Connection "
							+ ex.getMessage());
				}
				Logging.error("Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
	}

	public static void StoreTableInsertUpdate(String activity,
			String old_values, String cur_values) {
		// Connect con = ConnectInit.getConnect();
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		int audit_trail_id = 0;
		try {
			if (connection == null)
				connection = con.getdbConnection();
			Logging.debug("current values is " + cur_values);
			SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
			Date dt = new Date();
			String l_date = fr.format(dt).toString();
			Logging.debug("date is " + l_date);
			/*
			 * String time = dt.getHours() + ":" + dt.getMinutes() + ":" +
			 * dt.getSeconds();
			 */
			String time = Calendar.HOUR_OF_DAY + ":" + Calendar.MINUTE + ":"
					+ Calendar.SECOND;
			Logging.debug("time is " + time);
			// user_id="1";
			Logging.debug("user id is " + user_id + " con is " + con);
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT NEXTVAL('audit_trail_id')");
			Logging.debug("rs for nextval id is " + rs);
			while (rs.next()) {
				audit_trail_id = rs.getInt(1);
			}
			Logging.debug("audit trail id is " + audit_trail_id);
			pst = connection.prepareStatement(ConnectInit.queries
					.getProperty("insert_into_audit_trail_with_values"));
			pst.setInt(1, audit_trail_id);
			pst.setString(2, time);
			// pst.setInt(3,0);
			pst.setInt(3, Integer.parseInt(user_id));
			pst.setString(4, l_date);
			pst.setInt(5, Integer.parseInt(activity));
			pst.setString(6, old_values);
			pst.setString(7, cur_values);
			pst.executeUpdate();
		} catch (SQLException e) {
			Logging.error("SQL Exception : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (stmt != null)
					stmt.close();
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error("Error : Unable to close Connection "
							+ ex.getMessage());
				}
				Logging.error("Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
	}

	public String getOldValues() {
		return old_values;
	}

	public static void StorePrevData(String old_values1) {
		old_values = old_values1;
	}

	public static void setUserId(LogonForm form) {
		Logging.debug("Inside setUserId AuditTrail");
		// Connect con = ConnectInit.getConnect();
		Connect con = ConnectInit.getConnect();

		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			if (connection == null) {
				connection = con.getdbConnection();

			}
			String Username = form.getUsername();
			String password = form.getPassword();
			String enPass = Encript(password);
			 pst = connection
					.prepareStatement(ConnectInit.queries
							.getProperty("get_user_id"));

			pst.setString(1, Username);
			pst.setString(2, enPass); // changes done by neha 17-04-2007
			rst = pst.executeQuery();
			while (rst.next()) {
				user_id = rst.getString(1);
			}
		} catch (SQLException e) {
			Logging.error("SQL Exception : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (rst != null)
					rst.close();
				if (pst != null)
					pst.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error("Error : Unable to close Connection "
							+ ex.getMessage());
				}
				Logging.error("Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
	}

	public static String getUserId(String Username, String password) {
		Logging.debug("Inside get user id");
		// Connect con = ConnectInit.getConnect();
		Connect con = ConnectInit.getConnect();

		Connection connection = null;
		PreparedStatement pst =null;
		ResultSet rst = null;
		String enPass = Encript(password);

		try {
			if (connection == null) {
				connection = con.getdbConnection();
			}
			 pst = connection
					.prepareStatement(ConnectInit.queries
							.getProperty("get_user_id"));

			pst.setString(1, Username);
			pst.setString(2, enPass); // Changes done by neha 17-04-2007
			rst = pst.executeQuery();
			while (rst.next()) {
				user_id = rst.getString(1);
				Logging.debug("inside AuditTrial getUserId():" + user_id);
			}
		} catch (SQLException e) {
			Logging.error("SQL Exception : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (rst != null)
					rst.close();
				if (pst != null)
					pst.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error("Error : Unable to close Connection "
							+ ex.getMessage());
				}
				Logging.error("Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		return user_id;
	}

	/* function Encript() added by neha 17-04-2007 */

	public static String Encript(String str) {
		/**
		 * Encription : The password string is concatenated with the same string
		 * again it is then converted as first half of the string str1
		 * characters are added with 5 to there ascii values and rest half
		 * string str2 characters are added by 7 in there ascii values.
		 */
		String ret_str = null;
		String str1 = "", str2 = "";
		int num;
		char[] inp_str1 = str.toCharArray();
		char[] inp_str2 = str.toCharArray();
		for (int i = 0; i < inp_str1.length; i++) {
			num = inp_str1[i] + 5;
			inp_str1[i] = (char) num;
		}
		for (int i = 0; i < inp_str1.length; i++) {
			str1 = str1 + inp_str1[i];
		}
		for (int i = 0; i < inp_str2.length; i++) {
			num = inp_str2[i] + 7;
			inp_str2[i] = (char) num;
		}
		for (int i = 0; i < inp_str2.length; i++) {
			str2 = str2 + inp_str2[i];
		}
		ret_str = str1 + str2;
		return ret_str;
	}
	/*
	 * public static void main(String[] z) { AuditTrail adt = new AuditTrail();
	 * app.Logging.getDebug("In Main"); // StoreImfPageLoad("1"); String id =
	 * adt.getUserId("sudhir", "panchware"); app.Logging.getDebug("user id is "
	 * + id); }
	 */
}
