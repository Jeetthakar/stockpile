/*
 * Created on Mar 3, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.*;

import java.util.*;
/**
 * @author sonali
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InactiveStockListAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
	 		HttpServletRequest request, HttpServletResponse response)
	 		{
				ActionForward fr = null;
				HttpSession session=request.getSession();
				InactiveStockListForm f= (InactiveStockListForm)form;
				Vector v1=f.getVExcel();
				String id=f.getD1();
				session.removeAttribute("ci2");
				session.setAttribute("ci2",v1);
				session.removeAttribute("var");
				session.setAttribute("var",id);
		
				String ajax1=(String)request.getSession().getAttribute("ajax1");
 				String frd="/pages/reports/InactiveStockListS.jsp?ajax1="+ajax1;
 				return fr= new ActionForward(frd);
				
				//return mapping.findForward("success");
	 		}
}
