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

/**
 * @author naresh
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public final class RolesAction extends Action {
	public Roles roles;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		/**
		 * If Reset Button Is Clicked then Return To Main Page
		 */

		RolesForm rForm = (RolesForm) form;
		String operation = rForm.getOperation();
		if (operation != null && operation.equals("changeFields")) {
			roles = new Roles();
			String sIName = rForm.getSelectRoleName().split("e")[1];
			int numIndex = Integer.parseInt(sIName);
			roles.getDescription(numIndex);
			rForm.setRoleName(roles.getRoleName());
			rForm.setRoleDescription(roles.des);
			return (new ActionForward("/pages/masters/rolesView.jsp"));
		}

		/***********************************************************************
		 * Call The Constructor SysConfig To Make Datsbase Entries
		 **********************************************************************/
		roles = new Roles();
		String new1 = rForm.getNew1();
		String update = rForm.getUpdate();
		roles.AddRole(rForm.getSelectRoleName(), rForm.getRoleName(), rForm
				.getRoleDescription(), new1, update);

		if (roles.roleAdded == 1) {
			//rForm.setSelectRoleName("value" + roles.getRoleId()); //Commented on 11-08-06
			rForm.setSelectRoleName("value0"); //Added on 11-08-06
			return (new ActionForward(
					"/pages/masters/roleAdded.jsp?flag=roles&value=insert"));
		}
		if (roles.roleAdded == 0) {
			rForm.setSelectRoleName("value0"); //Added on 11-08-06
			return (new ActionForward(
					"/pages/masters/roleAdded.jsp?flag=roles&value=update"));
		}
		return mapping.getInputForward();
	}

}