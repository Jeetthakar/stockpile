package harrier.income.com.report;

/**
 * @author neha
 * 10-04-2007
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

public class RemoveReportPerAction extends Action {
	Logger Logging = Logger.getLogger(RemoveReportPerAction.class);
	Connection connection = null;
	Connect connect = ConnectInit.getConnect();
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RemoveReportPerForm form1 = (RemoveReportPerForm) form;
		String rbutton=form1.getRemove();
		String dbutton=form1.getDelete();
		String pid=form1.getSelectprefrence();
		try{
		     if(rbutton!=null && rbutton.equals("Remove")){
			       if(pid!=null && !pid.equals("0")){
				form1.deletepreference();
				return (new ActionForward("/pages/masters/roleAdded.jsp?flag=Batchreport&value=remove"));
			   }
		     }
		     if(dbutton!=null && dbutton.equals("Delete")){
				
		           if(pid!=null && !pid.equals("0")){
		           	form1.deletereport();
		           	return (new ActionForward("/pages/masters/roleAdded.jsp?flag=Batchreport&value=delete"));
		   }
		     }
		     
		}catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} 
	return (mapping.getInputForward());
	}
}