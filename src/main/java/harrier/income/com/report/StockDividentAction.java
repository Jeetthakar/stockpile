/*
 * Created on March 06, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

//import app.MakeExcelOld;

/**
 * @author abhijit
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StockDividentAction extends Action{
	public ActionForward execute( ActionMapping mapping, ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response )
	throws IOException, ServletException {
		ActionForward fr = null;
		StockDividentForm stkList=(StockDividentForm)form;
		StockDetails stockDet = new StockDetails();
				
		stkList.getTableData();
		request.getSession().setAttribute("ci2",stkList.getStkDividentVec());
			
		
		String ajax1=(String)request.getSession().getAttribute("ajax1");
		String frd="/pages/reports/StockDividentS.jsp?ajax1="+ajax1;
		return fr= new ActionForward(frd);
		//return  mapping.getInputForward();
		//return (new ActionForward("/pages/stockDetailFromDate.jsp"));
	}
}
