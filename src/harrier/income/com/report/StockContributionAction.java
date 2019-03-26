/*
 * Created on Mar 29, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
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
public class StockContributionAction extends Action{
	 Logger Logging = Logger.getLogger(StockContributionAction.class);
	/**
	 * Action forward class for IndexCalculator.
	 */
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws IOException, ServletException 
		{
			ActionForward fr = null;
			String message=null;
	    	String indid="0";
	    	
			
			StockContributionForm scf=(StockContributionForm) form;
			scf.getStockcotriIndexchange();
	    	HttpSession sess=request.getSession();
	    	Vector var_colected_vector=scf.getVi();
	    	Logging.debug("Vector value from action class = "+var_colected_vector);
	    	sess.setAttribute("colected_vector",scf.getVi());
	    	request.getSession().setAttribute("ci2",scf.getVi());
			indid=scf.getIndex();
			String clear=scf.getClear();
			String go= scf.getCompute();
			Logging.debug(go);
			Logging.debug(" go = "+ go);
			
			if(clear!=null){
			if((clear.equals("yes"))){
				Logging.debug("In Action : value is yessssssssssssssssss");
				scf.setFrom(null);
				scf.setTo(null);
				}
			}
			return fr= new ActionForward("/pages/reports/StockContriToIndexChangeS.jsp?ajax1=yes");
			//return  mapping.getInputForward();
			}
}
