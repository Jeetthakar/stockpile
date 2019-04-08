package harrier.income.com.report;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/*
 * Created on Feb 21, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author sonali
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IndexCompareAction extends Action {
	 public ActionForward execute(ActionMapping mapping, ActionForm form,
	 		HttpServletRequest request, HttpServletResponse response)
	 		{
		 		
		 ActionForward fr = null;
	 			String fromdate="";
	 			String toDate="";
	 			String[] var1=null;//{"","",""};
	 			
	 			try{
			 			IndexCompareForm f=(IndexCompareForm)form;
			 			HttpSession sess=request.getSession();
			 			PrintWriter pw=response.getWriter();
			 			var1=f.getD2();
			 			fromdate=f.getFrom();
			 			toDate=f.getTo();
			 			sess.setAttribute("indexids",var1);
			 			sess.removeAttribute("sfdate");
			 			sess.setAttribute("sfdate",fromdate);
			 			sess.removeAttribute("stdate");
			 			sess.setAttribute("stdate",toDate);
			 			
			 			//sess.setAttribute("varIndexId",var1[0]);
			 	   		//sess.setAttribute("indMulti",var1[1]);
			 	   		//sess.setAttribute("from",fromdate);
					    //sess.setAttribute("to",toDate);
			 			
	 			}catch(Exception e){
	 				
	 			}
	 			return fr= new ActionForward("/pages/reports/IndexCompare1S.jsp?ajax1=yes");
	 			//return mapping.findForward("success");
	 			
	 		}
}
