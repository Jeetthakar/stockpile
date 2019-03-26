package harrier.income.com.report;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * @author Lokesh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PortfolioReportAction extends Action
{
	Logger Logging = Logger.getLogger(PortfolioReportAction.class);
	/**
	 * Action forward class for IndexCalculator.
	 */
	
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws IOException, ServletException 
		{
			ActionForward fr = null;
			
			
	    	String indid="0";
	    	String report_path="";
	    	PortfolioReportForm Idf=(PortfolioReportForm) form;
	    	Idf.getTabledata();
	    	
	    	HttpSession sess=request.getSession();
	    	Vector var_colected_vector=Idf.getVw();

	    	sess.setAttribute("colected_vector",Idf.getVw());
	    	request.getSession().setAttribute("ci2",Idf.getVw());
	    	
	    	indid=Idf.getIndex();
			
	    	String varSelectIndex =indid;
			sess.removeAttribute("varIndexId");
			sess.setAttribute("varIndexId",varSelectIndex);
			String indMulti[]=Idf.getIndMulti();
			sess.setAttribute("indMulti",indMulti);
			//sess.setAttribute("index2",Idf.getIndexcmp());
			//sess.setAttribute("index3",Idf.getIndex3());
			String varSelectToDate =Idf.getTo();
			sess.removeAttribute("to");
			sess.setAttribute("to",varSelectToDate);
			
			String varSelectFromDate =Idf.getFrom();
			sess.removeAttribute("from");
			sess.setAttribute("from",varSelectFromDate);
	    	
	    	//for industry weightage
			Idf.getIndweighttable();
	    	sess.setAttribute("colected_vector_iw",Idf.getVi());
	    	
	    	
			
			String clear=Idf.getClear();
			String go= Idf.getCompute();
			
			//Idf.setRep_path("/pages/reports/");
			if(clear!=null)
			{
			if((clear.equals("yes")))
				{
					Logging.debug("In Action : value is yessssssssssssssssss");
					Idf.setFrom(null);
					Idf.setTo(null);
				}
			}
			
			return fr= new ActionForward("/pages/reports/PortfolioReport.jsp?");
			
			}
}