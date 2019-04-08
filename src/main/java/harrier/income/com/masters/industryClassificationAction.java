/*
 * Created on Mar 12, 2006
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
public class industryClassificationAction extends Action {
	industryClassification ic, ic1;

	public int numIndex;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		/**
		 * If changeFields occurs then Return To Main Page alongwith setting the
		 * fields
		 */

		industryClassificationForm rForm = (industryClassificationForm) form;
		/** Get the selected industry_classification_id */
		String sIName = rForm.getSelectICName().split("e")[1];
		numIndex = Integer.parseInt(sIName);
		String operation = rForm.getOperation();
		if (operation != null && operation.equals("changeFields")) {
			ic = new industryClassification();
			ic.getAllFieldsValue(numIndex);
			rForm.setIcName(ic.getName());
			rForm.setDescription(ic.getDescription());
			rForm.setShortName(ic.getShortName());
			return (new ActionForward(
					"/pages/masters/industryClassificationMaster.jsp"));
		}

		/***********************************************************************
		 * Call The Constructor AddICM of industryClassifiction.java To Make
		 * Database Entries
		 **********************************************************************/
		ic1 = new industryClassification();
		ic1.AddICM(numIndex, rForm.getIcName(), rForm.getShortName(), rForm
				.getDescription());

		if (ic1.roleAdded == 1) {
			rForm.setSelectICName("value" + ic1.icmId);

			return (new ActionForward(
					"/pages/masters/roleAdded.jsp?flag=icm&value=insert"));
		}
		if (ic1.roleAdded == 0) {

			return (new ActionForward(
					"/pages/masters/roleAdded.jsp?flag=icm&value=update"));
		}
		return mapping.getInputForward();
	}
}