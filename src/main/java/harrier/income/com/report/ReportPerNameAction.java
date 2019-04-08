package harrier.income.com.report;

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
 * @author neha
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReportPerNameAction extends Action {
	Logger Logging = Logger.getLogger(ReportPerNameAction.class);
	public static final String FORWARD_start = "success";
	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */

	Connection connection = null;

	Connect connect = ConnectInit.getConnect();

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		int flag = 0;
		ReportPerNameForm form1 = (ReportPerNameForm) form;
		String add=form1.getAdditem();
		String update=form1.getUpdate();
		 int ids = form1.getId();
		String prefer_name = form1.getPrefer_name();
				
		/**
		 * check if user wants to update data. If yes then call update funtion
		 * else check if user wants to add new element. after update or add
		 * reset bean variables.
		 */
		 if(update!=null) {
		 	form1.setUpdate(null);
			updateData(ids,prefer_name);
			form1.setId(0);
			return (new ActionForward("/pages/masters/roleAdded.jsp?flag=AddPreferenceName&value=update"));
		   } 
		   if(add!=null) {
			form1.setAdditem(null);
			storedata(prefer_name);
			form1.setId(0);
			return (new ActionForward("/pages/masters/roleAdded.jsp?flag=AddPreferenceName&value=insert"));
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
				
				
				
				pstseq = connection.prepareStatement(ConnectInit.queries.getProperty("select_max_prefer_id"));
				int curr_seq = 0;

				seqno = pstseq.executeQuery();
				while (seqno.next()) {
					curr_seq = Integer.parseInt(seqno.getString(1));
				}
				curr_seq++;

				pstdata = connection.prepareStatement(ConnectInit.queries.getProperty("insert_new_prefername"));
						
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

	public void updateData(int id,String pname) {
		int id_int = id;
		PreparedStatement psub;
		connection = null;
		try {
			if (connection == null)
				connection = connect.getdbConnection();
			try {
				psub = connection.prepareStatement(ConnectInit.queries.getProperty("update_new_prefername"));
						
				psub.setString(1,pname);
				psub.setInt(2,id_int);
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