/*
 * Created on Feb 16, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;


import harrier.income.com.FormBean.ListTypeClass;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import java.io.PrintWriter;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

//import java.io.*;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Locale;
import java.util.Vector;
//import java.util.ArrayList;
//import java.io.*;
//import org.jfree.chart.demo.servlet.*;

//import app.Logging;

/**
 * @author pankaj
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IndexDivisorAction extends Action{
	 Logger Logging = Logger.getLogger(IndexDivisorAction.class);
	 public static final String FORWARD_start2 = "success123";
	
		
		 public ActionForward execute(ActionMapping mapping,ActionForm form,
		 		HttpServletRequest request,HttpServletResponse response)throws Exception{	
			 
			 	ActionForward fr = null;
				HttpSession varSession = request.getSession();
				IndexDivisorForm objIndexDivisorForm = (IndexDivisorForm)form;
				 
				 /**
					 * Setting variable name var_Table_Vector for table vector
					 */
				Vector collected_Table_Vector =objIndexDivisorForm.getVar_Table_data_vector();
				//Logging.debug("Selected Table Vecor "+collected_Table_Vector);
				varSession.removeAttribute("var_Table_Vector");
				varSession.setAttribute("var_Table_Vector",collected_Table_Vector);
						
				 
				 String varSelectIndex =objIndexDivisorForm.getSelectIndex();
				 Logging.debug("Selected Index is "+varSelectIndex);
				 varSession.removeAttribute("varIndexId");
				 varSession.setAttribute("varIndexId",varSelectIndex);
				 
				String varSelectToDate =objIndexDivisorForm.getTo();
				Logging.debug("Date to "+varSelectToDate);
				varSession.removeAttribute("to");
				varSession.setAttribute("to",varSelectToDate);
					
				String varSelectFromDate =objIndexDivisorForm.getFrom();
				Logging.debug("Date From "+varSelectFromDate);
				varSession.removeAttribute("from");
				varSession.setAttribute("from",varSelectFromDate);
			 
			 	String varCheckMAvg =objIndexDivisorForm.getCheck_mavg();
				Logging.debug(varCheckMAvg);
				varSession.removeAttribute("varCheckAvg");
				varSession.setAttribute("varCheckAvg",varCheckMAvg);
				
				
				String varSpan =objIndexDivisorForm.getAvgSpan();
				//System.out.print("avgspan value"+request.getParameter("avgSpan"));
				Logging.debug(varSpan);
				varSession.removeAttribute("varSpan");
				varSession.setAttribute("varSpan",varSpan);
				
				String varChart =objIndexDivisorForm.getChart();
				Logging.debug(varChart);
				varSession.removeAttribute("varChart");
				varSession.setAttribute("varChart",varChart);
				
				/**
				 * Setting variable name "chartType" 
				 */
			
				varSession.removeAttribute("chartType");
				varSession.setAttribute("chartType","inddivisor");
				
				Vector v2=objIndexDivisorForm.getVExcel();
				varSession.setAttribute("ci2",v2);
				
				return fr= new ActionForward("/pages/reports/Index_DivisorS.jsp?ajax1=yes");
				//return mapping.findForward(FORWARD_start2);
		 	
		 }
		 
		 
}
