
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

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author naresh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Top10Action extends Action{
	public ActionForward execute( ActionMapping mapping, ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response )
	throws IOException, ServletException {
		Top10DetailsForm sdf=(Top10DetailsForm)form;
		StockDetails stockDet = new StockDetails();
		
		sdf.getTableData();
		request.getSession().setAttribute("ci2",sdf.getVecTop10Details());
		/*//sdf.setStockName(sdf.getStockName());
		//sdf.setIndexName(sdf.getIndexName());
		
		String clear=sdf.getClear();
		String go= sdf.getCompute();
		System.out.println("In Action :value is yessssssssssssssssss: clear = "+clear);
		app.Logging.getDebug(" values : checkShwIndices="+ sdf.getCheckShwIndices());
		//sdf.setCheckShwIndices(null);
		
		if((clear.equals("yes"))){
			System.out.println("In Action : value is yessssssssssssssssss");
			sdf.setClear(null);
			sdf.setFrom(null);
			sdf.setTo(null);
			sdf.setCheckShwIndices(null);
			sdf.setCheckChart(null);
			}*/
		return  mapping.getInputForward();
		//return (new ActionForward("/pages/Top10Companies.jsp"));
	}
}
