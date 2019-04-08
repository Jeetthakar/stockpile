package harrier.income.com.report;

import harrier.income.com.FormBean.ListTypeClass;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.Vector;

/**
 * @author Manoj
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IndexDivisorActionNew extends Action{
	 Logger Logging = Logger.getLogger(IndexDivisorActionNew.class);
	 public static final String FORWARD_start2 = "success123";
	
		
		 public ActionForward execute(ActionMapping mapping,ActionForm form,
		 		HttpServletRequest request,HttpServletResponse response)throws Exception{	
			 
			 	ActionForward fr = null;
				HttpSession varSession = request.getSession();
				IndexDivisorForm objIndexDivisorForm = (IndexDivisorForm)form;
		
				String varSelectIndex =objIndexDivisorForm.getSelectIndex();
				Logging.debug("Selected Index is "+varSelectIndex);
				varSession.removeAttribute("varIndexId");
				varSession.setAttribute("varIndexId",varSelectIndex);
				 
				String varSelectToDate =objIndexDivisorForm.getTo();
				Logging.debug("Date to "+varSelectToDate);
				varSession.removeAttribute("to");
				varSession.setAttribute("to",varSelectToDate);
					
				String varSelectFromDate =objIndexDivisorForm.getFrom();
				Logging.debug("Date From "+varSelectFromDate);
				varSession.removeAttribute("from");
				varSession.setAttribute("from",varSelectFromDate);
			 
				String varSpan =objIndexDivisorForm.getAvgSpan();
				//System.out.print("avgspan value"+request.getParameter("avgSpan"));
				Logging.debug(varSpan);
				varSession.removeAttribute("varSpan");
				varSession.setAttribute("varSpan",varSpan);
				
				String varChart =objIndexDivisorForm.getChart();
				Logging.debug(varChart);
				varSession.removeAttribute("varChart");
				varSession.setAttribute("varChart",varChart);
				
				/**
				 * Setting variable name "chartType" 
				 */
			
				varSession.removeAttribute("chartType");
				varSession.setAttribute("chartType","inddivisor");
			
				return fr= new ActionForward("/pages/reports/Index_DivisorSNew.jsp?ajax1=yes");
				//return mapping.findForward(FORWARD_start2);
		 	
		 }
		 
		 
}
