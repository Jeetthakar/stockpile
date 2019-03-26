/*
 * Created on Jan 28, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * @author pranoti
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class Changecurr extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			Corporate corp =(Corporate)form;
			String chg_butt=request.getParameter("change_button");
			if(chg_butt!=null)
			{
				String index=corp.getI_index();
				if(chg_butt.equals("Index"))
				{
					if(((index.equals(""))|(index==null))|(index.equals("Select Index")))
						corp.reset_index();
					else
						corp.setResult2();
					return new ActionForward("/pages/ChangeIndcurr.jsp");
				}
			}
			return mapping.getInputForward();
	}
	
}
