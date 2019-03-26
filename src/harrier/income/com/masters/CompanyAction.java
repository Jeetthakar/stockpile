/*
 * Created on Mar 1, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.masters;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sysconfig.model.*;
/**
 * @author naresh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CompanyAction extends Action{
	public ActionForward execute( ActionMapping mapping, ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response )
	throws IOException, ServletException {
		CompanyForm cf		=	(CompanyForm)form;
		String comp			=	cf.getSelectCompany().split("e")[1];
		int companyId		=	Integer.parseInt(comp);
		String operation	=	cf.getOperation();
		if(operation!=null && operation.equals("Save")){
			Company ca			=	new Company();
			ca.addCompany(companyId,cf.getSelectedCheckbox());
		}
		return  mapping.getInputForward();
	}
}
