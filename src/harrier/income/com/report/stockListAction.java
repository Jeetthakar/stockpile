/*
 * Created on Feb 23, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;

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
public class stockListAction extends Action{
	Logger Logging = Logger.getLogger(stockListAction.class);
	public ActionForward execute( ActionMapping mapping, ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response )
	throws IOException, ServletException {
		stockListForm stkList=(stockListForm)form;
		StockDetails stockDet = new StockDetails();
		String excel = stkList.getExcelFormat();
		
		String letter = request.getParameter("sorting");
		Logging.debug(" letter in action = " +  letter);
		stkList.setFilter(letter);
		if(letter != null){
			Logging.debug(" calling setter from action.");
			stkList.setFilter(letter);
		}
		
		stkList.getTableData();
		stkList.setExchName(stkList.getExchName());
		stkList.setStockName(stkList.getStockName());
		request.getSession().setAttribute("ci2",stkList.getStklistVec());		
		Logging.debug(" Comming out of stockListAction");
		
		return  mapping.getInputForward();
		//return (new ActionForward("/pages/stockDetailFromDate.jsp"));
	}
}
