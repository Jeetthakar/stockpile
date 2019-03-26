/*
 * Created on Mar 2, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.masters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * @author naresh
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AddCompanyAction extends Action {
	Logger Logging = Logger.getLogger(AddCompanyAction.class);
	public static final String FORWARD_start = "success";

	Connection connection = null;

	Connect connect = ConnectInit.getConnect();

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		int flag = 0;
		AddCompanyForm form1 = (AddCompanyForm) form;
		String new1 = form1.getNew1();
		String update = form1.getUpdate();
		int ids = form1.getId();
		String cmp_name = form1.getCmp_name();
		/**
		 * check if user wants to update data. If yes then call update funtion
		 * else check if user wants to add new element. after update or add
		 * reset bean variables.
		 */
		if (update != null) {
			form1.setUpdate(null);
			updateData(ids, cmp_name);
			form1.setId(0);
			AddCompanyForm.setCon_flag("U");
			return (new ActionForward(
					"/pages/masters/roleAdded.jsp?flag=AddNewCompany&value=update"));
		} else if (new1 != null) {
			form1.setNew1(null);
			storedata(cmp_name);
			form1.setId(0);
			AddCompanyForm.setCon_flag("N");
			return (new ActionForward(
					"/pages/masters/roleAdded.jsp?flag=AddNewCompany&value=insert"));
		}
		return mapping.findForward(FORWARD_start);
	}

	public void storedata(String name) {
		ResultSet seqno;
		PreparedStatement pstdata, pstseq;
		connection = null;
		try {
			if (connection == null) {
				connection = connect.getdbConnection();
			}
			try {
				//String seqQuery = "select nextval('company_id')";
				String seqQuery = "select MAX(company_id) from company";
				pstseq = connection.prepareStatement(seqQuery);
				int curr_seq = 0;

				seqno = pstseq.executeQuery();
				while (seqno.next()) {
					curr_seq = Integer.parseInt(seqno.getString(1));
				}
				curr_seq++;

				pstdata = connection.prepareStatement(ConnectInit.queries
						.getProperty("insert_new_company"));
				pstdata.setInt(1, curr_seq);
				pstdata.setString(2, name);
				pstdata.executeUpdate();

				//pstdata.close();
				//seqno.close();
				//pstseq.close();
			} catch (Exception ee) {
				Logging.debug("Error :" + ee);
			}
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
	}

	public void updateData(int id, String cname) {
		int id_int = id;
		PreparedStatement psub;
		connection = null;
		try {
			if (connection == null)
				connection = connect.getdbConnection();
			try {
				psub = connection.prepareStatement(ConnectInit.queries
						.getProperty("update_new_company"));
				psub.setString(1, cname);
				psub.setInt(2, id_int);
				psub.executeUpdate();
				psub.close();
				
			} catch (Exception et) {
				Logging.debug("Error :" + et);
			}
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
	}
}