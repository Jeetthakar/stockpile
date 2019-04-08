
package app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Implementation of <strong>Action</strong> that processes a
 * user logoff.
 *
 * @author Craig R. McClanahan
 * @author Ted Husted
 * @version $Revision: 1.1 $ $Date: 2013/02/14 13:14:08 $
 */

public final class LogoffAction extends Action {
	Logger logger = Logger.getLogger(LogoffAction.class); /*by Pranay*/

// ---------------------------------------------------- Public Methods

    /**
     * Logoff the user.
     * The event is logged if the debug level is >= Constants.DEBUG.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param actionForm The ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    public ActionForward perform(ActionMapping mapping,
         ActionForm form,
         HttpServletRequest request,
         HttpServletResponse response)
            throws IOException, ServletException {

      // Extract attributes we will need
      HttpSession session = request.getSession();
      LogonForm user = (LogonForm)
        session.getAttribute(Constants.USER_KEY);

      // Log this user logoff
      if (user != null) {
    	  servlet.log("");
    	  LogFactory.getLog(LogoffAction.class);
        if (servlet.getDebug() >= Constants.DEBUG) { /*changed by Pranay*/
    	  //if (logger.getLevel().equals(Constants.DEBUG)) {
            StringBuffer message =
                new StringBuffer("LogoffAction: User '");
            message.append(user.getUsername());
            message.append("' logged off in session ");
            message.append(session.getId());
            servlet.log(message.toString());
            //******************************make user=null when logoff called***********
           session.setAttribute("user",null);
           session=null;
        }
      }

      else {

        if (servlet.getDebug() >= Constants.DEBUG) {/*changed by Pranay*/
    	 // if (logger.getLevel().equals(Constants.DEBUG)) {
            StringBuffer message = new StringBuffer("LogoffAction: User '");
            message.append(session.getId());
            servlet.log(message.toString());
        }
      }

      // Remove user login
      session.removeAttribute(Constants.USER_KEY);

      // Return success
      return (mapping.findForward(Constants.SUCCESS));

    }
    
/*	public static void main(String z[])
	{
	ConnectInit.getConnect().getConnection();
	}
*/
} // end LogoffAction
