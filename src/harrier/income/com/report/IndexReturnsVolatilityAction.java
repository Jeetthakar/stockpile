/*
 * Created on Feb 28, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;

import harrier.income.com.FormBean.ListTypeClass;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author pankaj
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IndexReturnsVolatilityAction extends Action{
	 Logger Logging = Logger.getLogger(IndexReturnsVolatilityAction.class);
	 public static final String FORWARD_start1 = "success111";
	
	 public ActionForward execute(ActionMapping mapping,ActionForm form,
	 		HttpServletRequest request,HttpServletResponse response)throws Exception{
		 
		 ActionForward fr = null;
		 HttpSession varSession = request.getSession();
		 IndexReturnsVolatilityForm objIndexReturnsVolatilityForm = (IndexReturnsVolatilityForm)form;
		 
		 /**
		  * Setting variable name "type" for type
		  */
		 varSession.removeAttribute("type");
		 varSession.setAttribute("type","14");
		 
		 String arr[] =objIndexReturnsVolatilityForm.getIndexList();
		 Logging.debug("Selected Index is "+arr);
		 varSession.removeAttribute("indexList");
		 varSession.setAttribute("indexList",arr);
		 
		 String varSelectToDate =objIndexReturnsVolatilityForm.getTo();
		 Logging.debug("Date to "+varSelectToDate);
		 varSession.removeAttribute("to");
		 varSession.setAttribute("to",varSelectToDate);
				
		 String varSelectFromDate =objIndexReturnsVolatilityForm.getFrom();
		 Logging.debug("Date From "+varSelectFromDate);
		 varSession.removeAttribute("from");
		 varSession.setAttribute("from",varSelectFromDate);
		 
		 Vector v2=objIndexReturnsVolatilityForm.getVExcel();
		 varSession.setAttribute("ci2",v2);
	 	
		 return fr= new ActionForward("/pages/reports/IndexReturns_VolatilityS.jsp?ajax1=yes");
		 //return mapping.findForward(FORWARD_start1);
	 	
	 }

}
