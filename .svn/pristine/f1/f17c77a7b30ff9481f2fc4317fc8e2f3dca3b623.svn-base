/*
 * Created on Feb 21, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;
import org.apache.struts.action.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.chart.demo.servlet.AdjustDecimal;

import app.Connect;

import harrier.income.com.report.*;

import java.sql.*;
import java.util.*;


/**
 * @author sonali
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IndexPerformanceAction extends Action {

	 public ActionForward execute(ActionMapping mapping, ActionForm form,
	 		HttpServletRequest request, HttpServletResponse response)
	 		{
		 		ActionForward fr = null;
	 		//	System.out.println("INSIDE EXECUTE");
	 			IndexPerformanceForm f=(IndexPerformanceForm)form;
	 			HttpSession session=request.getSession();
	 			Vector v2=f.getVExcel();
	 			session.setAttribute("ci2",v2);
	 			String date=f.getDate();
	 			session.setAttribute("dt",date);
	 			
	 			return fr= new ActionForward("/pages/reports/IndexPerformanceS.jsp?ajax2=yes");
				//return mapping.findForward("success");
	 			
	 		}
}

