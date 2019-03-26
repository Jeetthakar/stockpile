package app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import subscription.dao.UserDao;
import subscription.form.subscribeUserForm;

import com.harrier.initializeation.ConnectInit;

/**
 * Implementation of <strong>Action</strong> that validates a user logon.
 * 
 * @author Craig R. McClanahan
 * @author Ted Husted
 * @version $Revision: 1.1 $ $Date: 2013/02/14 13:14:04 $
 */
public final class LogonAction extends Action {
	Logger Logging = Logger.getLogger(LogonAction.class);
	String username = "";

	String password = "";

	String userLang = "";

	String user_id = null;

	String homepage = null;

	// Connect c = ConnectInit.getConnect();
	Connect c = ConnectInit.getConnect();

	PreparedStatement pst = null;

	ResultSet rs = null;

	Logger logger = Logger.getLogger(LogonAction.class);

	/**
	 * Validate credentials with business tier.
	 * 
	 * @param username
	 *            The username credential
	 * @param password
	 *            The password credential
	 */
	public boolean isUserLogon(String username, String password) {

		Connection connection = null;
		try {
			if (connection == null) {
				connection = c.getdbConnection();
		//		connection = ConnectInit.getConnection();
				Logging.debug("inside logonAction "+connection);
			}

			Logging.debug(c + "for Pranay in LogonAction");
			Logging.debug("23");
			java.sql.ResultSet rst = c.select(connection,
					"get_username_and_password", username, password);
			if (rst.next()) {
				Logging.debug("rst next exists");
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		} finally {
			try {
				if (connection != null)
					connection.close();
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

	/**
	 * Login the user. The event is logged if the debug level is >=
	 * Constants.DEBUG.
	 * 
	 * @param mapping
	 *            The ActionMapping used to select this instance
	 * @param request
	 *            The HTTP request we are processing
	 * @param response
	 *            The HTTP response we are creating
	 * 
	 * @exception IOException
	 *                if an input/output error occurs
	 * @exception ServletException
	 *                if a servlet exception occurs
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		// Obtain username and password from web tier
		username = ((LogonForm) form).getUsername();
		password = ((LogonForm) form).getPassword();

		if (session.getAttribute("already_login") != null) {
			subscribeUserForm urForm2 = new subscribeUserForm();
			urForm2.setUserid((String) session.getAttribute("user_name"));
			// urForm2.setUserid("");
			urForm2.setPassword((String) session.getAttribute("password"));
			// urForm2.setPassword("" );
			// status=u.UserLogin(urForm2,session ) ;
			session.setAttribute("userbeen", urForm2);
			username = (String) urForm2.getUserid();
			password = (String) urForm2.getPassword();

		}

		// userLang = ((LogonForm) form).getUserLang();
		userLang = "en";
		// ActionForward fr = null;
		// Validate credentials with business tier
		boolean validated = false;
		String enPass = null;
		if (session.getAttribute("already_login") == null) {
			enPass = Encript(password);
		}

		// String enPass = password;
		validated = isUserLogon(username, enPass);
		// Logging.debug("Referer is "+((LogonForm)form).getReferer());

		if (!validated) {

			return (mapping.findForward(response.encodeURL("invalid")));

		} else {

			AuditTrail.setUserId(((LogonForm) form));
			// Code to keep track of login user : by Manoj
			user_id = AuditTrail.getUserId(username, password);
			LogonForm frm = (LogonForm) form;
			frm.lastLoginofUser(user_id);
		}

		// chandra changes
		subscribeUserForm urForm1 = new subscribeUserForm();
		urForm1.setUserid(username);
		urForm1.setPassword(password);
		session.setAttribute("userbeen", urForm1);

		user_id = AuditTrail.getUserId(username, password);
		Logging.debug("inside LogonAction and user_id is :" + user_id);
		UserDao u = new UserDao();
		String user_role = u.userRole(user_id);
		session.setAttribute("role_id", user_role);
		session.setAttribute("userid", user_id);
		Logging.debug("in logon Action ,session attribute set:" + user_id);
		session.setAttribute("user_name", username);
		Connection con = null;
		try {
			if (con == null)
				con = c.getdbConnection();
			String query = ConnectInit.queries.getProperty("select_home_page");
			pst = con.prepareStatement(query);
			pst.setString(1, user_id);
			rs = pst.executeQuery();
			while (rs.next()) {
				homepage = rs.getString(1);
			}
		} catch (Exception e) {
			Logging.debug("fjadfgsdkfl" + e);

		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				try {
					if (con != null)
						con.close();
				} catch (Exception ex) {
					Logging.error("Error : Unable to close Connection "
							+ ex.getMessage());
				}
				Logging.error("Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}

		session.setAttribute(Constants.USER_KEY, form);
		session.setAttribute("queryFired", "false");
		//session.setAttribute("userid", user_id);
		Logging.debug("second time:" + user_id);
		// session.setAttribute("user_id",user_id);

		/* changed by Pranay */
		// Log this event, if appropriate
		if (servlet.getDebug() >= Constants.DEBUG) {
			// if (logger.getLevel().equals(Constants.DEBUG)) {
			StringBuffer message = new StringBuffer("LogonAction: User '");
			message.append(username);
			message.append("' logged on in session ");
			message.append(session.getId());
			servlet.log(message.toString());
		}

		if (userLang.equals("en")) {
			// Create a Locale for English language
			request.getSession().setAttribute(
					org.apache.struts.Globals.LOCALE_KEY,
					new java.util.Locale("en", "US"));

			/*
			 * // Store the required direction for English in the session
			 * request.getSession().setAttribute( JspConstants.PAGE_DIR , "LTR" );
			 *  // Store the required CSS file name for English in the session
			 * request.getSession().setAttribute( JspConstants.PAGE_CSS ,
			 * "demo_en_US.css" );
			 */
		} else if (userLang.equals("fr")) {
			// Create a Locale for French language
			request.getSession().setAttribute(
					org.apache.struts.Globals.LOCALE_KEY,
					new java.util.Locale("fr"));

		} else if (userLang.equals("de")) {
			// Create a Locale for German Language
			request.getSession().setAttribute(
					org.apache.struts.Globals.LOCALE_KEY,
					new java.util.Locale("de"));
		} else if (userLang.equals("es")) {
			// Create a Locale for Spanish Language
			request.getSession().setAttribute(
					org.apache.struts.Globals.LOCALE_KEY,
					new java.util.Locale("es"));
		} else if (userLang.equals("it")) {
			// Create a Locale for Italian Language
			request.getSession().setAttribute(
					org.apache.struts.Globals.LOCALE_KEY,
					new java.util.Locale("it"));
		}

		session.setAttribute("locale", userLang);

		Logging.debug("3");
		Logging.debug(Constants.SUCCESS);
		// Return success

		// return fr= new
		// ActionForward("/pages/reports/"+homepage+".jsp?FromLogin=yes");
		// added by neha 06-06-2007
		String homep = "";
		if (homepage == null)
			homep = "PortfolioReport";
		else
			homep = homepage.trim();

		if (homep != null) {
			return (new ActionForward("/pages/reports/" + homep
					+ ".jsp?FromLogin=yes"));
		} else {

			return (mapping.findForward(response.encodeURL(Constants.SUCCESS)));
		}
		// return (new
		// ActionForward((response.encodeURL(((LogonForm)form).getReferer()))));
		// return (mapping.findForward(response.encodeURL(Constants.SUCCESS)));

	}

	String Encript(String str) {
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

} // End LogonAction
