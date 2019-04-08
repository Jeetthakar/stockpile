package harrier.income.com.report;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * @author Lokesh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IndexGraphAction extends Action
{
	Logger Logging = Logger.getLogger(IndexGraphAction.class);

	/**
	 * Action forward class for IndexCalculator.
	 */
	
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws IOException, ServletException 
		{
			ActionForward fr = null;
			
	    	String indid="0";
	    	String report_path="";
	    	IndexGraphForm Igf=(IndexGraphForm) form;
	    	
	    	HttpSession sess=request.getSession();
	       		    	
	    	//indid=Igf.getIndex();
			
	    	//indid=request.getParameter("index");
			
	    	String indid12="0";
	    	indid12=request.getParameter("index");
	    	//Igf.setIndex(indid);
			String varSelectIndex =indid12;
			sess.removeAttribute("varIndexId");
			sess.setAttribute("varIndexId",varSelectIndex);
						
	    	String varSelectToDate =Igf.getTo();
			sess.removeAttribute("to");
			sess.setAttribute("to",varSelectToDate);
			
			String varSelectFromDate =Igf.getFrom();
			sess.removeAttribute("from");
			sess.setAttribute("from",varSelectFromDate);
	    	
	   
	    	
			
			String clear=Igf.getClear();
			String go= Igf.getCompute();
			
			//Igf.setRep_path("/pages/reports/");
			if(clear!=null)
			{
			if((clear.equals("yes")))
				{
					Logging.debug("In Action : value is yessssssssssssssssss");
					Igf.setFrom(null);
					Igf.setTo(null);
				}
			}
			
			return fr= new ActionForward("/pages/reports/IndexGraph.jsp?");
			
			}
}