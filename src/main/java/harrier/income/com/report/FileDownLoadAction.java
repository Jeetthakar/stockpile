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
public class FileDownLoadAction extends Action{
	Logger Logging = Logger.getLogger(FileDownLoadAction.class);
	public ActionForward execute( ActionMapping mapping, ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response )
	throws IOException, ServletException {
	Logging.debug(" Inside FileownLoadAction");
		FileDownLoadForm fileDownload=(FileDownLoadForm)form;
		
		Vector v2 = (Vector)request.getSession().getAttribute("ci2");
		Logging.debug(" after getting vector v2 = "+ v2.toString());
		Vector temp = fileDownload.getDataVec();
		Logging.debug(" getVector = "+ temp.toString());
		fileDownload.setDataVec(v2);
	
		Logging.debug(" after setting v2");
		
		response.setContentType ("application/xls");
		Logging.debug("  After setting content type");
		response.setHeader ("Content-Disposition", "attachment; filename=\"StockHighLow.xls\"");
		Logging.debug(" After setting header");
		Logging.debug(" inside FileDownLoadAction abotu to exit");
		
		return  mapping.getInputForward();
		//return (new ActionForward("/pages/stockDetailFromDate.jsp"));
	}
}
