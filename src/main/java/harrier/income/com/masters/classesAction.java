/*
 * Created on Mar 2, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.masters;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sysconfig.model.classes;
import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * @author naresh
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class classesAction extends Action {
	Logger Logging = Logger.getLogger(classesAction.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		String query;
		ResultSet rst;
		classesForm cForm = (classesForm) form;
		/**
		 * If changeFields occurs then Return To Main Page alongwith setting the
		 * fields
		 */
		String operation = cForm.getOperation();
		if (operation != null) {
			if (operation.equals("changeFieldClassLevel")) {
				return (new ActionForward("/pages/masters/classes.jsp"));
			} else if (operation.equals("selectClass")) {
				try {
					if (connection == null)
						connection = con.getdbConnection();
					String strLId = cForm.getSelectClass().split("e")[1];
					int LId = Integer.parseInt(strLId);
					PreparedStatement stmt = connection
							.prepareStatement(ConnectInit.queries
									.getProperty("classes_select_*"));
					stmt.setInt(1, LId);
					rst = stmt.executeQuery();
					while (rst.next()) {
						cForm.setNewClassName(rst.getString(2));
						cForm.setDescription(rst.getString(3));
						cForm.setClassCode(rst.getString(6));
						cForm.setShortName(rst.getString(7));
					}
					rst.close();
					stmt.close();
				} catch (Exception e) {
					Logging.error("Error  :" + e.getMessage());
				} finally {
					try {
						if (connection != null)
							connection.close();
					} catch (Exception ee) {
						Logging
								.error(" Error : Unable to close Connection "
										+ ee.getMessage());
					}
				}
				return (new ActionForward("/pages/masters/classes.jsp"));
			}

			if (operation.equals("Save")) {
				classes cla = new classes();
				if (cForm.getRadioButton().equals("Add")) {
					String strLId = cForm.getSelectClassLevel().split("e")[1];
					int LId = Integer.parseInt(strLId);
					String strPId = cForm.getSelectParentClassLevel()
							.split("e")[1];
					int flag = cla.addClasses(cForm.getNewClassName(), cForm
							.getDescription(), LId, strPId, cForm
							.getClassCode(), cForm.getShortName());
					if (flag == 1) {
						return (new ActionForward(
								"/pages/masters/roleAdded.jsp?flag=classes&value=insert"));
					}
				} else if (cForm.getRadioButton().equals("Update")) {
					String strCId = cForm.getSelectClass().split("e")[1];
					int CId = Integer.parseInt(strCId);
					int flag = cla.updateClasses(CId, cForm.getNewClassName(),
							cForm.getDescription(), cForm.getClassCode(), cForm
									.getShortName());
					if (flag == 1) {
						return (new ActionForward(
								"/pages/masters/roleAdded.jsp?flag=classes&value=update"));
					}
				}
			}
		}
		return mapping.getInputForward();
	}
}