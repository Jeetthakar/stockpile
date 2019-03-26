/*
 * Created on Jan 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.harrier.initializeation.ConnectInit;

/**
 * @author sunil
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class AcessControl {
	Logger Logging = Logger.getLogger(AcessControl.class);
	private static String locale;

	private static String userName;

	// Connect con1 = ConnectInit.getConnect();
	Connect con1 = ConnectInit.getConnect();
	public static Vector activity_id;

	public static boolean flagindex = false;

	public static boolean flagstock = false;

	/**
	 * method to get role_id for the user.
	 */
	public String getUseRoleId(LogonForm form) {
		userName = null;
		userName = form.getUsername();
		Logging.debug("user Name is : " + userName);
		String role_id = null;
		// Connect con = ConnectInit.getConnect();
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		// String query = con.queries.getProperty("get_role_id__for_user");
		String query = ConnectInit.queries.getProperty("get_role_id__for_user");
		try {
			if (connection == null) {
				connection = con.getdbConnection();
			}
			pst = connection.prepareStatement(query);
			pst.setString(1, userName);
			rst = pst.executeQuery();

			activity_id = new Vector();
			while (rst.next()) {
				role_id = rst.getString(1);
			}
			if (rst != null)
				rst.close();
		} catch (Exception sqlexp) {
			Logging.error("SQL Error : " + sqlexp.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (rst != null)
					rst.close();
				if (pst != null) {
					pst.close();
				}
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
					if (rst != null)
						rst.close();
					if (pst != null) {
						pst.close();
					}
				} catch (Exception ex) {
					Logging.error("Error : " + ex.getMessage());
				}
				Logging.error("Error : " + ee.getMessage());
			}
		}
		return role_id;
	}

	/**
	 * method to get vector of activities( activity_id,activity_name) for the
	 * user loged in.
	 */
	public Vector getUseActivitiesId(LogonForm form) {
		userName = form.getUsername();
		String userLang = "en";// form.getUserLang();
		Properties prop = new Properties();
		try {
			if (userLang != null && userLang.equals("en")) {
				prop.load(new FileInputStream(Connect.resourceurl
						+ "resources/application.properties"));

			}
			if (userLang != null && userLang.equals("fr")) {
				prop.load(new FileInputStream(Connect.resourceurl
						+ "resources/application_fr.properties"));
			}
			if (userLang != null && userLang.equals("de")) {
				prop.load(new FileInputStream(Connect.resourceurl
						+ "resources/application_de.properties"));
			}
			if (userLang != null && userLang.equals("es")) {
				prop.load(new FileInputStream(Connect.resourceurl
						+ "resources/application_es.properties"));
			}
			if (userLang != null && userLang.equals("it")) {
				prop.load(new FileInputStream(Connect.resourceurl
						+ "resources/application_it.properties"));
			}
		} catch (Exception ee) {
			Logging.error("Error : " + ee.getMessage());
		}
		Logging.debug("user Name is : " + userName);
		activity_id = new Vector();
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		// String query = con.queries.getProperty("get_activities_for_user");
		String query = ConnectInit.queries
				.getProperty("get_activities_for_user");
		try {
			if (connection == null)
				connection = con.getdbConnection();
			pst = connection.prepareStatement(query);
			pst.setString(1, userName);
			rst = pst.executeQuery();
			Logging.debug("resultset is " + rst);
			int i = 0;
			activity_id = new Vector();
			while (rst.next()) {
				if (rst.getString(1) == null) {
					activity_id.add(i, "0");
				} else {
					String aaa = rst.getString(1);
					activity_id.add(i, aaa);
				}
				i++;
				if (rst.getString(2) == null) {
					activity_id.add(i, "--");
				} else {
					String activity_name = (String) rst.getString(2);
					activity_name = activity_name.replaceAll(" ", "_");
					String aName = prop.getProperty(activity_name);
					activity_id.add(i, aName);
				}
				i++;
			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error : " + sqlexp.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (rst != null)
					rst.close();
				if(pst!=null){
					pst.close();
				}
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
					if (rst != null)
						rst.close();
					if(pst!=null){
						pst.close();
					}
				} catch (Exception ex) {
					Logging.error("Error : " + ex.getMessage());
				}
				Logging.error("Error : " + ee.getMessage());
			}
		}
		return activity_id;
	}

	/**
	 * method to get vector of menu heads( id,menu head name) for the user loged
	 * in.
	 */
	public Vector getMenuId(LogonForm form) {
		userName = form.getUsername();
		String userLang = "en"; // form.getUserLang();
		Properties prop = new Properties();
		try {
			if (userLang != null && userLang.equals("en")) {
				prop.load(new FileInputStream(Connect.resourceurl
						+ "resources/application.properties"));

			}
			if (userLang != null && userLang.equals("fr")) {
				prop.load(new FileInputStream(Connect.resourceurl
						+ "resources/application_fr.properties"));
			}
			if (userLang != null && userLang.equals("de")) {
				prop.load(new FileInputStream(Connect.resourceurl
						+ "resources/application_de.properties"));
			}
			if (userLang != null && userLang.equals("es")) {
				prop.load(new FileInputStream(Connect.resourceurl
						+ "resources/application_es.properties"));
			}
			if (userLang != null && userLang.equals("it")) {
				prop.load(new FileInputStream(Connect.resourceurl
						+ "resources/application_it.properties"));
			}
		} catch (Exception ee) {
			Logging.error("Error : " + ee.getMessage());
		}
		Logging.debug("user Name is : " + userName);
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		Statement pst = null;
		ResultSet rst = null;
		activity_id = new Vector();
		try {
			if (connection == null)
				connection = con.getdbConnection();
			pst = connection.createStatement();
			// rst =
			// pst.executeQuery(con.queries.getProperty("get_menuHeaders_for_user"));
			rst = pst.executeQuery(ConnectInit.queries
					.getProperty("get_menuHeaders_for_user"));
			Logging.debug("resultset is " + rst);
			int i = 0;
			activity_id = new Vector();
			while (rst.next()) {
				if (rst.getString(1) == null) {
					activity_id.add(i, "0");
				} else {
					String sss = rst.getString(1);
					activity_id.add(i, sss);
					// System.out.println(sss);
				}
				i++;
				if (rst.getString(2) == null) {
					activity_id.add(i, "--");
				} else {
					String activity_name = (String) rst.getString(2).trim();
					activity_name = activity_name.replaceAll(" ", "_");
					String aName = prop.getProperty(activity_name);
					activity_id.add(i, aName);

				}
				i++;
			}
		} catch (Exception sqlexp) {
			Logging.error("SQL Error : " + sqlexp.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (rst != null)
					rst.close();
				if(pst!=null){
					pst.close();
				}
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
					if (rst != null)
						rst.close();
					if(pst!=null){
						pst.close();
					}
				} catch (Exception ex) {
					Logging.error("Error : " + ex.getMessage());
				}
				Logging.error("Error : " + ee.getMessage());
			}
		}

		return activity_id;
	}

	/**
	 * method to check if the user loged in has acess for the activity or not.
	 * returns true if user has access for that activity to perform.
	 */
	public static boolean HasAcess(String actid, Vector uname) {
		boolean flag = false;
		// Logging.debug("size of vector activity_id is " + uname);

		for (int i = 0; i < uname.size(); i += 2) {
			String id = (String) uname.get(i);
			// Logging.getDebug("id is "+id+" actid is "+actid);
			if (id.equals(actid)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * @return Returns the flagindex.
	 */
	public static boolean isFlagindex() {
		return flagindex;
	}

	/**
	 * @param flagindex
	 *            The flagindex to set.
	 */
	public static void setFlagindex(boolean flagindex) {
		AcessControl.flagindex = flagindex;
	}

	/**
	 * @return Returns the flagstock.
	 */
	public static boolean isFlagstock() {
		return flagstock;
	}

	/**
	 * @param flagstock
	 *            The flagstock to set.
	 */
	public static void setFlagstock(boolean flagstock) {
		AcessControl.flagstock = flagstock;
	}

	public static void setLocale(String lang) {
		locale = lang;
	}

	/**
	 * 
	 * @param Entry_key
	 * @return activity_value The respective Locale value of Entry_key
	 */
	public String getLangValues(String entry_key) {

		String userLang = locale;
		Properties prop = new Properties();
		try {
			if (userLang != null && userLang.equals("en")) {
				prop.load(new FileInputStream(Connect.resourceurl
						+ "resources/application.properties"));
			}
			if (userLang != null && userLang.equals("fr")) {
				prop.load(new FileInputStream(Connect.resourceurl
						+ "resources/application_fr.properties"));
			}
			if (userLang != null && userLang.equals("de")) {
				prop.load(new FileInputStream(Connect.resourceurl
						+ "resources/application_de.properties"));
			}
			if (userLang != null && userLang.equals("es")) {
				prop.load(new FileInputStream(Connect.resourceurl
						+ "resources/application_es.properties"));
			}
			if (userLang != null && userLang.equals("it")) {
				prop.load(new FileInputStream(Connect.resourceurl
						+ "resources/application_it.properties"));
			}
		} catch (Exception ee) {
			Logging.error("Error : " + ee.getMessage());
		}

		Logging.debug("user Language is : " + userLang);

		String entry_value = prop.getProperty(entry_key);

		return entry_value;
	}

	/**
	 * method to get vector of activities for the user loged in.
	 */
	public Vector getActivitiesForUser2(LogonForm form) {
		userName = form.getUsername();
		String userLang = form.getUserLang();
		Properties prop = new Properties();
		try {
			if (userLang != null && userLang.equals("en")) {
				prop.load(new FileInputStream(Connect.resourceurl
						+ "resources/application.properties"));
			}
			if (userLang != null && userLang.equals("fr")) {
				prop.load(new FileInputStream(Connect.resourceurl
						+ "resources/application_fr.properties"));
			}
			if (userLang != null && userLang.equals("de")) {
				prop.load(new FileInputStream(Connect.resourceurl
						+ "resources/application_de.properties"));
			}
			if (userLang != null && userLang.equals("es")) {
				prop.load(new FileInputStream(Connect.resourceurl
						+ "resources/application_es.properties"));
			}
			if (userLang != null && userLang.equals("it")) {
				prop.load(new FileInputStream(Connect.resourceurl
						+ "resources/application_it.properties"));
			}
		} catch (Exception ee) {
			Logging.error("Error : " + ee.getMessage());
		}
		Logging.debug("user Name is : " + userName);
		// System.out.println("user name in**********getActivitiesForUser2
		// "+userName);

		activity_id = new Vector();
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		// String query = con.queries.getProperty("get_activities_for_user");
		String query = ConnectInit.queries
				.getProperty("get_activities_for_user");
		try {
			if (connection == null) {
				connection = con.getdbConnection();
			}
			pst = connection.prepareStatement(query);
			// String userName="user";
			pst.setString(1, userName);
			rst = pst.executeQuery();

			// Getting activity code and Activity names

			int x = 0;
			while (rst.next()) {
				if (rst.getString(1) == null) {
					activity_id.add(x, "0");
				} else {
					String activityCode = rst.getString(1);
					activity_id.add(x, activityCode);
				}
				x++;
				if (rst.getString(2) == null) {
					activity_id.add(x, "--");
				} else {
					String activity_name = (String) rst.getString(2);
					activity_name = activity_name.replaceAll(" ", "_");
					// String aName=prop.getProperty(activity_name);
					activity_id.add(x, activity_name);
				}
				x++;
			}

		} catch (Exception sqlexp) {
			Logging.error("SQL Error : " + sqlexp.getMessage());
		}finally {
			try {
				if (connection != null)
					connection.close();
				if (rst != null)
					rst.close();
				if(pst!=null){
					pst.close();
				}
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
					if (rst != null)
						rst.close();
					if(pst!=null){
						pst.close();
					}
				} catch (Exception ex) {
					Logging.error("Error : " + ex.getMessage());
				}
				Logging.error("Error : " + ee.getMessage());
			}
		}

		return activity_id;
	}

}
