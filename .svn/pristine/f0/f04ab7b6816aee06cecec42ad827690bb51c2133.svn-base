/**
 * @author Manoj Adekar
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;
import harrier.income.com.FormBean.ListTypeClass;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.io.IOException;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class IndexListReportAction extends Action
{ Logger Logging = Logger.getLogger(IndexListReportAction.class);
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws IOException, ServletException 
		{
			ActionForward fr = null;
			String message=null;
	    	String indid="0";
	    	
	    	IndexListReportForm Idf=(IndexListReportForm) form;
			indid=Idf.getIndex();
			Logging.debug("In Action : ");
			String clear=Idf.getClear();
					
			if(clear!=null)
			{
			if((clear.equals("yes")))
			{
				Logging.debug("In Action : value is yessssssssssssssssss");
								
			}
			}
			return  mapping.getInputForward();
			//return mapping.findForward("success");
			}
}

