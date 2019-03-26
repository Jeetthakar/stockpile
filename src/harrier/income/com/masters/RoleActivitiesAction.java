/*
 * Created on Mar 11, 2006
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
import java.util.StringTokenizer;
/**
 * @author naresh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public final class RoleActivitiesAction extends Action{
	
	public Activity activity;
	public ActionForward execute( ActionMapping mapping, ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response )
	throws IOException, ServletException {
		RoleActivitiesForm raForm 	= 	(RoleActivitiesForm)form;
		
		/**
		 * Get The Assign Activities
		 * **/
		String rName				=	raForm.getSelectRName();
		String[] str				=	raForm.getRem();
		String saveButton			=	raForm.getOperation();
		String buff					=	str[0].toString();
		
		/**
		 * Call The method to make database entries
		 * **/
		if(saveButton.equals("Save")){
			RoleActivities raActivity = new RoleActivities();
			raActivity.AddRoleActivity(rName,buff);
		}
		return mapping.getInputForward();
	}

}
