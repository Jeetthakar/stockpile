/*
 * Created on Mar 11, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;
import java.io.IOException;
import javax.servlet.http.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

import sysconfig.action.stockDetailFromDateForm;
/**
 * @author sonali
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StockDetailFromDateAction extends Action {
	public ActionForward execute( ActionMapping mapping, ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response )
	throws IOException, ServletException {
		StockDetailFromDateForm sdf=(StockDetailFromDateForm)form;
	//	Logging.debug("FROM STOCKDETAILACTION ");
		Vector v2=sdf.getVExcel();
		HttpSession session=request.getSession();
		session.setAttribute("ci2",v2);
		String filter=sdf.getFilter();
		String select=sdf.getSelectDemo();
		String clear=sdf.getClear();
		//Logging.debug("value is yessssssssssssssssss: "+clear);
		if(filter.equals("0") ){
			sdf.setSelect("0");
			sdf.setText(null);
			sdf.setSelectStock(null);
			sdf.setFromDate(null);
			sdf.setTableDate(null);
		}
		else if(select.equals("0") ){
			sdf.setSelect("0");
			sdf.setText(null);
			sdf.setSelectStock(null);
			sdf.setFromDate(null);
			sdf.setTableDate(null);
		}
		else if(!(clear.equals("yes"))){
		//	Logging.debug("value is yessssssssssssssssss");
			sdf.setFromDate(null);
			sdf.setTableDate(null);
		}
		return  mapping.getInputForward();
		//return (new ActionForward("/pages/stockDetailFromDate.jsp"));
	}

}
