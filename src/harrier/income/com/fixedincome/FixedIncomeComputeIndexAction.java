/*
 * Created on Jun 13, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.fixedincome;

/**
 * @author neha
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import app.*;
public class  FixedIncomeComputeIndexAction extends Action {
	Logger Logging = Logger.getLogger(FixedIncomeComputeIndexAction.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ActionForward fr = null;
		String message = null;
		String indid = "0";
		FixedIncomeComputeIndexForm computeIndexForm = (FixedIncomeComputeIndexForm) form;
		indid = computeIndexForm.getIndex();
		if (request.getParameter("operation") != null
				&& request.getParameter("operation").equals("Compute")) {
			message = computeIndexForm.getLastclosingvalue();
			Logging.debug("message is " + message);
		}
		if (message != null) {
			String url = "/pages/fixedincome/FixedIncomeIndexHome.jsp?D1=" + indid + "&Message="
					+ message;
			return fr = new ActionForward(url);
		}
		return (mapping.getInputForward());
	}
}