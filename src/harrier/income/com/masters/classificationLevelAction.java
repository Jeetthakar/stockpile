/*
 * Created on Mar 3, 2006
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
public class classificationLevelAction extends Action {
	public int numIndex, afterLevel;

	classificationLevel cl, cl1;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		/**
		 * If changeFields occurs then Return To Main Page alongwith setting the
		 * fields
		 */
		classificationLevelForm clForm = (classificationLevelForm) form;
		String new1 = clForm.getNew1();
		String update = clForm.getUpdate();
		/** Get the selected classification_level_id */
		String scl = clForm.getSelectClassLevel();
		if (scl != null) {
			String sIName = clForm.getSelectClassLevel().split("e")[1];
			numIndex = Integer.parseInt(sIName);
		}
		String aL = clForm.getAfterLevel();
		if (aL != null) {
			afterLevel = Integer.parseInt(aL);
		}
		String operation = clForm.getOperation();
		if (operation != null && operation.equals("changeFieldClassLevel")) {
			cl = new classificationLevel();
			cl.getAllFieldsValue(numIndex);
			clForm.setNewClassLevel(cl.getNewClassLevel());
			clForm.setLevelNumber(cl.getLevelNumber());
			clForm.setShortName(cl.getShortName());
			clForm.setDescription(cl.getDescription());
			return (new ActionForward("/pages/masters/classificationLevel.jsp"));
		} else if (operation != null && operation.equals("changeFields")) {
			clForm.setNewClassLevel(null);
			clForm.setLevelNumber(0);
			clForm.setShortName(null);
			clForm.setDescription(null);
			return (new ActionForward("/pages/masters/classificationLevel.jsp"));
		}
		/***********************************************************************
		 * Call The Constructor AddICM of classificationLevel.java To Insert Or
		 * Update Database Entries
		 **********************************************************************/
		String ICnum = clForm.getSelectICName().split("e")[1];
		int intICnum = Integer.parseInt(ICnum);
		cl1 = new classificationLevel();
		cl1.AddICM(numIndex, clForm.getNewClassLevel(), intICnum, clForm
				.getShortName(), clForm.getDescription(), clForm
				.getRadioButton(), afterLevel, new1, update);
		if (cl1.roleAdded == 1) {
			clForm.setSelectICName("value" + intICnum);
			clForm.setSelectClassLevel("value" + cl1.clId);
			return (new ActionForward(
					"/pages/masters/roleAdded.jsp?flag=classificationLevel&value=insert"));
		}
		if (cl1.roleAdded == 0) {
			return (new ActionForward(
					"/pages/masters/roleAdded.jsp?flag=classificationLevel&value=update"));
		}
		return mapping.getInputForward();
	}
}