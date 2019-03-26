/*
 * Created on Mar 14, 2006
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
public final class ActivityAction extends Action {
	public Activity activity;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		/**
		 * If Reset Button Or Selction Of Activity Occure then Return To Main
		 * Page
		 */

		ActivityForm aForm = (ActivityForm) form;
		String new1 = aForm.getNew1();
		String update = aForm.getUpdate();
		String operation = aForm.getOperation();
		if ((operation != null && operation.equals("Reset"))) {
			aForm.setActivityName(null);
			aForm.setActivityDescription(null);
			aForm.setActivityCode(null);
			aForm.setSelectActivityName("value0");
			return (new ActionForward("/pages/masters/activitiesView.jsp"));
		} else if (operation != null && operation.equals("changeFields")) {
			activity = new Activity();
			String sIName = aForm.getSelectActivityName().split("e")[1];
			int numIndex = Integer.parseInt(sIName);
			activity.getnameDescriptionCode(numIndex);
			aForm.setActivityName(activity.name);
			aForm.setActivityDescription(activity.des);
			aForm.setActivityCode(activity.code);

			return (new ActionForward("/pages/masters/activitiesView.jsp"));
		}

		/***********************************************************************
		 * Call The Constructor SysConfig To Make Datsbase Entries
		 **********************************************************************/
		activity = new Activity();
		int val = activity.AddActivity(aForm.getSelectActivityName(), aForm
				.getActivityName(), aForm.getActivityDescription(), aForm
				.getActivityCode(), new1, update);
		if (activity.activityAdded == 1) {
			//aForm.setSelectActivityName("value" + val);
			aForm.setSelectActivityName("value0");
			aForm.setOperation("Reset");
			return (new ActionForward(
					"/pages/masters/roleAdded.jsp?flag=activityRoles&value=insert"));
		}
		if (activity.activityAdded == 0) {
			aForm.setSelectActivityName("value0");
			return (new ActionForward(
					"/pages/masters/roleAdded.jsp?flag=activityRoles&value=update"));
		}
		return mapping.getInputForward();
	}
}