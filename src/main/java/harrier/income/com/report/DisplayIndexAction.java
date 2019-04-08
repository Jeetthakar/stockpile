
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
/**
 * @author Ashish
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayIndexAction extends Action
{ Logger Logging = Logger.getLogger(DisplayIndexAction.class);

	/**
	 * Action forward class for IndexCalculator.
	 */
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws IOException, ServletException 
		{
			ActionForward fr = null;
			String message=null;
	    	String indid="0";
	    	
			DisplayIndexForm Idf=(DisplayIndexForm) form;
			
			request.getSession().setAttribute("ci2",Idf.getIndex_details());
			indid=Idf.getIndex();
			Logging.debug("In Action : ");
			String clear=Idf.getClear();
			String go= Idf.getCompute();
			Vector temp = new Vector();
			temp = (Vector)request.getSession().getAttribute("ci2");
			Logging.debug("vector frmo sesssion = " + temp.toString());
			Logging.debug("vector = " + Idf.getIndex_details().toString());
			Logging.debug(" go = "+ go);
			
			if(clear!=null)
			{
			if((clear.equals("yes")))
			{
				Logging.debug("In Action : value is yessssssssssssssssss");
				Idf.setFrom(null);
				Idf.setTo(null);
			}
			}
			return  mapping.getInputForward();
			//return mapping.findForward("success");
			}
}
