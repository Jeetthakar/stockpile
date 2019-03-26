package harrier.income.com.report;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.jfree.chart.demo.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import org.apache.struts.action.*;


public class SectorwiseAction extends Action {
	
		 
	public ActionForward execute(ActionMapping mapping, ActionForm form,
		 		HttpServletRequest request, HttpServletResponse response)
		 		{
			 		ActionForward fr = null;
		 			
		 			
		 			try{
		 				SectorwiseForm f=(SectorwiseForm)form;
				 			HttpSession sess=request.getSession();
				 			PrintWriter pw=response.getWriter();
				 			sess.setAttribute("indexes",f.getD2());
				 			
				 			
		 			}catch(Exception e){
		 				
		 			}
		 			return fr= new ActionForward("/pages/reports/SectorwiseCmp.jsp?FlagForReset=yes&ajax1=yes");
		 		
		 		}
	
}
