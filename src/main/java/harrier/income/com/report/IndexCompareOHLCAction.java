/*
 * Created on Feb 25, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import app.*;
import org.apache.struts.action.*;

/**
 * @author sonali
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IndexCompareOHLCAction extends Action {
	 public ActionForward execute(ActionMapping mapping, ActionForm form,
	 		HttpServletRequest request, HttpServletResponse response)
	 		{
	 			//Logging.debug("Inside IndexCompareOHLCAction");
	 			IndexCompareOHLCForm f=(IndexCompareOHLCForm)form;
	 			Vector v1=new Vector();

	 			
	 			Vector v2=new Vector();
	 			MakeExcel obj = new MakeExcel();
	 			HttpSession session=request.getSession();
	 			String[] id=request.getParameterValues("d1");
	 			int type=10;
	 			String fr=(String)request.getAttribute("from");
	 			String to=(String)request.getAttribute("to");
	 			v1.add(id[0]);
	 			v1.add(id[1]);
	 			ArrayList a=f.getIndexohlc();
	 			v2=f.getVexcel();
	 			
				obj.create_file(v1,type,v2,fr,to);
				response.setHeader ("Content-Disposition", "attachment; filename=\"IndexCompareOHLC.xls\"");
	 			
	 			return mapping.findForward("success");
	 			
	 		}

}
