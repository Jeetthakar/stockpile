package harrier.income.com.report;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import org.apache.struts.action.*;


public class SectorContriToIndexAction extends Action {
	
		 public ActionForward execute(ActionMapping mapping, ActionForm form,
		 		HttpServletRequest request, HttpServletResponse response)
		 		{
			 		ActionForward fr = null;
		 					 			
		 			try{
				 			SectorContriToIndexForm f=(SectorContriToIndexForm)form;
				 			f.getSectorContri();
				 			HttpSession sess=request.getSession();
				 			
				 			PrintWriter pw=response.getWriter();
				 			sess.removeAttribute("index");
				 			sess.removeAttribute("from");
				 			sess.removeAttribute("to");
				 			sess.removeAttribute("SectorContriData");
				 							 			
				 			sess.setAttribute("index",f.getIndex());
				 			sess.setAttribute("from",f.getFrom());
				 			sess.setAttribute("to",f.getTo());
				 			sess.setAttribute("SectorContriData",f.getSectorContriData());
				 			
		 			}catch(Exception e){
		 				
		 			}
		 			return fr= new ActionForward("/pages/reports/SectorContriToIndex.jsp");
		 		
		 		}
	
}
