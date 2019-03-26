
package harrier.income.com.report;

import java.io.IOException;
import java.util.Vector;

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
 * @author Ashish
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IndexComposeAction extends Action
{
	Logger Logging = Logger.getLogger(IndexComposeAction.class);
	/**
	 * Action forward class for IndexCalculator.
	 */
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws IOException, ServletException 
		{
			ActionForward fr = null;
			String message=null;
	    	String indid="0";
	    	IndexComposeForm Idf=(IndexComposeForm) form;
	    	Idf.getTabledata();
	    	HttpSession sess=request.getSession();
	    	Vector var_colected_vector=Idf.getVw();
	    	Logging.debug("Vector value from action class = "+var_colected_vector);
	    	sess.setAttribute("colected_vector",Idf.getVw());
	    	request.getSession().setAttribute("ci2",Idf.getVw());
	    	
	    	//request.getSession().setAttribute("ajax1","1");
	    	indid=Idf.getIndex();
			//IndexCompose indexcompose = new IndexCompose();
	    	
	    	
			String clear=Idf.getClear();
			String go= Idf.getCompute();
			Logging.debug(go);
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
			
			return fr= new ActionForward("/pages/reports/IndexComposeS.jsp?ajax1=yes");
			//return  mapping.getInputForward();
			
			}
}
