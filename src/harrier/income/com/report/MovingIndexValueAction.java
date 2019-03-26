/*
 * Created on Feb 16, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;
import harrier.income.com.FormBean.ListTypeClass;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author pankaj
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MovingIndexValueAction extends Action{
	 Logger Logging = Logger.getLogger(MovingIndexValueAction.class);
		public static final String FORWARD_start1 = "success";
	public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
		HttpSession varSession = request.getSession();
		MovingIndexValueForm objMovingIndexForma = (MovingIndexValueForm)form;
		
		ActionForward fr = null;
		/**
		 * Setting variable name var_Table_Vector for table vector
		 */
		Vector collected_Table_Vector =objMovingIndexForma.getVar_Table_data_vector();
		varSession.removeAttribute("var_Table_Vector");
		varSession.setAttribute("var_Table_Vector",collected_Table_Vector);
		/**
		 * Setting variable name "var" for selected index
		 */
		String varSelectIndex =objMovingIndexForma.getSelectIndex();
		varSession.removeAttribute("varIndexId");
		varSession.setAttribute("varIndexId",varSelectIndex);
		/**
		 * Setting variable name "type" for type
		 */
	
		//varSession.removeAttribute("type");
		//varSession.setAttribute("type","24");
		
		/**
		 * Setting variable name "chartType" 
		 */
	
		varSession.removeAttribute("chartType");
		varSession.setAttribute("chartType","maverage");
		
		/**
		 * Setting variable name "filename"for excel file
		 */
		varSession.removeAttribute("filename");
		varSession.setAttribute("filename","IndexMovement.xls");
		/**
		 * Setting variable name "to" for Starting date
		 */
		String varSelectToDate =objMovingIndexForma.getMove_to();
		Logging.debug("Dateto "+varSelectToDate);
		varSession.removeAttribute("to");
		varSession.setAttribute("to",varSelectToDate);
		/**
		 * Setting variable name "from" for ending date
		 */
		String varSelectFromDate =objMovingIndexForma.getMove_from();
		Logging.debug("Datefrom "+varSelectFromDate);
		varSession.removeAttribute("from");
		varSession.setAttribute("from",varSelectFromDate);
		/**
		 * 
		 */
		String varCheckMAvg =objMovingIndexForma.getCheck_moving_avg();
		Logging.debug(varCheckMAvg);
		varSession.removeAttribute("varCheckAvg");
		varSession.setAttribute("varCheckAvg",varCheckMAvg);
		
		String varSpan =objMovingIndexForma.getSelectSpan();
		Logging.debug(varSpan);
		varSession.removeAttribute("varSpan");
		varSession.setAttribute("varSpan",varSpan);
		
		String varChart =objMovingIndexForma.getSelectChart();
		Logging.debug(varChart);
		varSession.removeAttribute("varChart");
		varSession.setAttribute("varChart",varChart);
			
		
/*		String varChart =objMovingIndexForma.getChart();
		Logging.debug(varChart);
		varSession.removeAttribute("varChart");
		varSession.setAttribute("varChart",varChart);
*/
		Vector v2=objMovingIndexForma.getVExcel();
		varSession.setAttribute("ci2",v2);
		
		return fr= new ActionForward("/pages/reports/MoveIndexValueS.jsp?ajax1=yes");
		//return mapping.findForward(FORWARD_start1);
	}

}
