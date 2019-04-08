/*
 * Created on Feb 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package sysconfig.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import sysconfig.model.*;

import java.util.StringTokenizer;



/**
 * @author manoj
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public final class UserRolesAction extends Action{
	
	public Activity activity;
	public ActionForward execute( ActionMapping mapping, ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response )
	throws IOException, ServletException {
		UserRolesForm urForm 	= 	(UserRolesForm)form;
		
		/**
		 * Get The Assign Activities
		 * **/
		String uName				=	urForm.getSelectUName();
		String[] str				=	urForm.getRem();
		String saveButton			=	urForm.getOperation();
		String buff					=	str[0].toString();
	//	Logging.debug("Look for the values:"+uName+saveButton+buff);
		
		/**
		 * Call The method to make database entries
		 * **/
		if(saveButton.equals("Save")){
			UserRoles ur 			= new UserRoles();
			ur.userRole(uName,buff);
		}
		
		
		return mapping.getInputForward();
	}

}
