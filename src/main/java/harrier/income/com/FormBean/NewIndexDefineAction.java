/*
 * Created on Sep 10, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.FormBean;

import javax.servlet.http.*;

import org.apache.log4j.Logger;
import org.apache.struts.action.*;
import java.util.*;

/**
 * @author manish
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public final class NewIndexDefineAction extends Action {
	Logger Logging = Logger.getLogger(NewIndexDefineAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String query = "insert into information_schema.index_master  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			// Logging.debug("Before QueryClass");
			QueryClass.insertIntoIndexMaster(query, form);
			// Logging.debug("after QueryClass");

		} catch (Exception e) {
			Logging.debug("NewIndexDefineAction:Error in execute Method");
		}

		return (new ActionForward("/pages/NewIndexDefine.jsp"));
		// return mapping.getInputForward();
	}

}
