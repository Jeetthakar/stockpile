/*
 * Created on Sep 19, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.FormBean;

/**
 * @author kena
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.sql.*;
import javax.swing.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import harrier.income.com.compute.*;
public class CorporateAction extends Action  {
	public ActionForward execute(ActionMapping mapping,ActionForm form,
			   HttpServletRequest request,
			   HttpServletResponse response)
		   throws IOException, ServletException {
		CorporateActionFormBean corporate=(CorporateActionFormBean)form;
		CCorporateAction ac=new CCorporateAction();
		String amount=corporate.getF_amount();
		      if(amount!=null){
		      	ac.dsor_CashDividend("1","5","16-09-2004",amount);
		      }
		return mapping.getInputForward();
		
	}
}
