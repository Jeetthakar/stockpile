/*
 * Created on Feb 20, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;

import java.io.IOException;
import java.util.*;

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
public class stockDetailsAction extends Action{
	Logger Logging = Logger.getLogger(stockDetailsAction.class);
	public ActionForward execute( ActionMapping mapping, ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response )
	throws IOException, ServletException {
		ActionForward fr = null;
		stockDetailsForm sdf=(stockDetailsForm)form;
		StockDetails stockDet = new StockDetails();
		//String b1=sdf.getB1();
		sdf.getTableData();
		request.getSession().setAttribute("ci2",sdf.getVecStockDetails());
		String ajax1=(String)request.getSession().getAttribute("ajax1");
		sdf.setStockName(sdf.getStockName());
		sdf.setIndexName(sdf.getIndexName());
		
		String clear=sdf.getClear();
		String go= sdf.getCompute();
		Logging.debug("In Action :value is yessssssssssssssssss: clear = "+clear);
		Logging.debug(" values : checkShwIndices="+ sdf.getCheckShwIndices());
		//sdf.setCheckShwIndices(null);
		
		if((clear.equals("yes"))){
			Logging.debug("In Action : value is yessssssssssssssssss");
			sdf.setClear(null);
			sdf.setFrom(null);
			sdf.setTo(null);
			sdf.setCheckShwIndices(null);
			sdf.setCheckChart(null);
			}
		String frd="/pages/reports/StockHighLowS.jsp?ajax1="+ajax1;
			return fr= new ActionForward(frd);
			//return  mapping.getInputForward();
			
	}
}
