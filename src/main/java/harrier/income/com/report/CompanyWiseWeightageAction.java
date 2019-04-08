/*
 * Created on Feb 25, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.*;

import java.util.Vector;

/**
 * @author sonali
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CompanyWiseWeightageAction extends Action{
	 public ActionForward execute(ActionMapping mapping, ActionForm form,
	 		HttpServletRequest request, HttpServletResponse response)
		 	{
		 		ActionForward fr = null;
	 			CompanyWiseWeightageForm f = (CompanyWiseWeightageForm)form;
	 			HttpSession sess=request.getSession();
	 			String s1=f.getCheck2();
 				Vector v1=f.getCompanyWeightageVector();
 				String id1=f.getD1();
				sess.removeAttribute("ci2"); 
 				sess.setAttribute("ci2",f.getCompanyWeightageVector());
 				sess.removeAttribute("var");
 				sess.setAttribute("var",id1);
 				sess.removeAttribute("type");
 				sess.setAttribute("type","2");
 				
 				String ajax1=(String)request.getSession().getAttribute("ajax1");
 				String frd="/pages/reports/CompanyWiseWeightageS.jsp?ajax1="+ajax1;
 				return fr= new ActionForward(frd);
	 			//return mapping.findForward("success");
		 	}
}
