/*
 * Created on Feb 24, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.*;
/**
 * @author sonali
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Indexpe_pbAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
	 		HttpServletRequest request, HttpServletResponse response)
	 		{
				ActionForward fr = null;
				Indexpe_pbForm f =(Indexpe_pbForm)form;
				HttpSession session=request.getSession();
				
				Vector v2=f.getVExcel();
				session.setAttribute("ci2",v2);
				String id=f.getD1();
				session.setAttribute("var",id);
				session.setAttribute("varpepb",id);
				String fdate=f.getFrom();
				session.setAttribute("fromDate",fdate);
				String tdate=f.getTo();
				session.setAttribute("toDate",tdate);
				
				
				String ajax1=(String)request.getSession().getAttribute("ajax1");
				String frd="/pages/reports/Indexpe_pbS.jsp?ajax1="+ajax1;
				return fr= new ActionForward(frd);
				//return mapping.findForward("success");
	 		}
}
