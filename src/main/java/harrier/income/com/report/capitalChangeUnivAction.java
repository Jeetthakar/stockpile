/*
 * Created on Feb 20, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;

import harrier.income.com.FormBean.ListTypeClass;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author abhijit
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class capitalChangeUnivAction extends Action{
	 Logger Logging = Logger.getLogger(capitalChangeUnivAction.class);
	public ActionForward execute( ActionMapping mapping, ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response )
	throws IOException, ServletException {
		
		ActionForward fr = null;
		capitalChangeUnivForm capChg=(capitalChangeUnivForm)form;
		
		capChg.getTableData();
		request.getSession().setAttribute("ci2",capChg.getCapitalChangeVec());
		
		String clear=capChg.getClear();
		String go= capChg.getCompute();
		Logging.debug("In Action :value is yessssssssssssssssss: clear = "+clear);
		//app.Logging.getDebug(" values : checkShwIndices="+ capChg.getCheckShwIndices());
		//sdf.setCheckShwIndices(null);
		
		if((clear.equals("yes"))){
			Logging.debug("In Action : value is yessssssssssssssssss");
			
			capChg.setClear(null);
			capChg.setFrom(null);
			capChg.setTo(null);
			}
		return fr= new ActionForward("/pages/reports/CapitalChangeToUniverseS.jsp?ajax1=yes");
		//return  mapping.getInputForward();
		
	}
}
