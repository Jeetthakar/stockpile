package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.harrier.initializeation.ConnectInit;

/**
 * Form bean for the user profile page. This form has the following fields, with
 * default values in square brackets:
 * <ul>
 * <li><b>password</b> - Entered password value
 * <li><b>username</b> - Entered username value
 * </ul>
 * 
 * @author Vivek
 * @version $Revision: 1.1 $ $Date: 2013/02/14 13:14:08 $
 */
public final class LogonForm extends ActionForm {
	Logger Logging = Logger.getLogger(LogonForm.class);
	// ------------------------------------------------ Instance Variables

	/**
	 * The password.
	 */
	private String password = null;

	/**
	 * The username.
	 */
	private String username = null;

	private String referer = null;

	private String fname = null;

	/**
	 * 
	 * the language
	 */
	private String userLang = null;

	// ------------------------------------------------------ Properties

	/**
	 * Returns the user Language
	 */
	public String getUserLang() {
		return (this.userLang);
	}

	/**
	 * Sets the user Language
	 */
	public void setUserLang(String userLang) {
		this.userLang = userLang;
	}

	/**
	 * Return the password.
	 */
	public String getPassword() {

		return (this.password);

	}

	/**
	 * Set the password.
	 * 
	 * @param password
	 *            The new password
	 */
	public void setPassword(String password) {

		this.password = password;

	}

	/**
	 * Return the username.
	 */
	public String getUsername() {
		return (this.username);

	}

	/**
	 * Set the username.
	 * 
	 * @param username
	 *            The new username
	 */
	public void setUsername(String uname) {
		if (uname != null) {
			uname = uname.trim();
		}
		this.username = uname;

	}

	// -------------------------------------------------- Public Methods

	/**
	 * Reset all properties to their default values.
	 * 
	 * @param mapping
	 *            The mapping used to select this instance
	 * @param request
	 *            The servlet request we are processing
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		setPassword(null);
		setUsername(null);
		referer = null;
	}

	/**
	 * Ensure that both fields have been input.
	 * 
	 * @param mapping
	 *            The mapping used to select this instance
	 * @param request
	 *            The servlet request we are processing
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		ResultSet rst = null;
		ResultSet rst1 = null;
		PreparedStatement pst = null;
		PreparedStatement pst1 = null;
//	 Connect connect = ConnectInit.getConnect();
		Connect connect = ConnectInit.getConnect();
	 Logging.debug("connect outside for pranay3 in form = "+connect);
		Connection con = null;
		try {
			if (con == null) {
				con = connect.getdbConnection();

				Logging.debug(con + "for Pranay in LogonForm");
			}

			pst = con
					.prepareStatement("Select  * from users  where username=? and password=?");
			pst.setString(1, username);
			pst.setString(2, password);
			rst = pst.executeQuery();

			pst1 = con
					.prepareStatement("Select first_name from users  where username=? ");
			pst1.setString(1, username);
			rst1 = pst1.executeQuery();
			// if(!rst.next()){
			// errors.add("invalidLogin",
			// new ActionError("error.logon.invalid"));
			// }
			if (rst1.next()) {
				fname = rst1.getString("first_name");
				setFname(fname);
			}

			/*
			 * if ((username == null) || (username.length() < 1))
			 * errors.add("username", new
			 * ActionError("error.username.required"));
			 * 
			 * if ((password == null) || (password.length() < 1))
			 * errors.add("password", new
			 * ActionError("error.password.required"));
			 * 
			 */

		} catch (SQLException e) {
			Logging.error("Select11" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
				Logging.error("Error" + e);
			}

		}
		return errors;
	}

	/**
	 * @return Returns the referer.
	 */
	public String getReferer() {
		return referer;
	}

	/**
	 * @param referer
	 *            The referer to set.
	 */
	public void setReferer(String referer) {
		this.referer = referer;

	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String lastLoginofUser(String user_id) {
		String result = "";
		// ResultSet rst=null;
		PreparedStatement pstmt = null;
		Connection connection = null;
		Connect con = ConnectInit.getConnect();
		try {
			SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			Date dt = new Date();
			dt.setDate(dt.getDate());

			if (connection == null)
				connection = con.getdbConnection();

			pstmt = connection.prepareStatement(ConnectInit.queries
					.getProperty("update_user_lastlogin_datetime"));
			pstmt.setString(1, fr.format(dt).toString());
			pstmt.setString(2, user_id);
			pstmt.executeUpdate();

		} catch (Exception e) {

			Logging.debug("Error in last login change :" + e.getMessage());
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {

				Logging.debug("Unable to close Database Connection.");
			}
		}

		return result;
	}
} // End LogonForm
