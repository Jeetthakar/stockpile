/*
 * Created on Mar 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * @author Vivek
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ComputeIndexAction extends Action {
	Logger Logging = Logger.getLogger(ComputeIndexAction.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ActionForward fr = null;
		String message = null;
		String indid = "0";
		ComputeIndexForm computeIndexForm = (ComputeIndexForm) form;
		indid = computeIndexForm.getIndex();
		if (request.getParameter("operation") != null
				&& request.getParameter("operation").equals("Compute")) {
			message = computeIndexForm.getLastclosingvalue();
			Logging.debug("message is " + message);
		}
		if (message != null) {
			String url = "/pages/IndexHome.jsp?D1=" + indid + "&Message="
					+ message;
			return fr = new ActionForward(url);
		}
		return (mapping.getInputForward());
	}
}