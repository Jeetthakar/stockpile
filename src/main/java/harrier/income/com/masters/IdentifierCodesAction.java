/*
 * Created on Mar 16, 2006
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
public class IdentifierCodesAction extends org.apache.struts.action.Action {
	Logger Logging = Logger.getLogger(IdentifierCodesAction.class);
	public static final String FORWARD_start = "success";

	Connect connect = ConnectInit.getConnect();

	Connection con;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		IdentifierCodes code = (IdentifierCodes) form;
		String update = code.getUpdate();
		String new1 = code.getNew1();
		String name = code.getCname();
		String desc = code.getDesc();
		int ids = code.getIden_id();
		/**
		 * check if user wants to update data. If yes then call update funtion
		 * else check if user wants to add new element. after update or add
		 * reset bean variables.
		 */
		if (update != null) {
			ids = IdentifierCodes.iden_id;
			name = code.getCname();
			desc = code.getDesc();
			updateData(ids, name, desc);
			code.setUpdate(null);
			code.setIden_id(0);
			IdentifierCodes.setCon_flag("U");
			return (new ActionForward(
					"/pages/masters/roleAdded.jsp?flag=IdentifierCodes&value=update"));
		} else if (new1 != null) {
			submitData(name, desc);
			code.setNew1(null);
			code.setName(null);
			IdentifierCodes.setCon_flag("N");
			return (new ActionForward(
					"/pages/masters/roleAdded.jsp?flag=IdentifierCodes&value=insert"));
		}
		return mapping.findForward(FORWARD_start);
	}

	/**
	 * Ones data is validated, record is updated or added as per the action
	 * selected by the user. Appropriate messges are displayed to the user for
	 * the action.
	 *  
	 */
	public void submitData(String stname, String detail) {
		if (stname != null) {
			stname = stname.trim();
		}
		if (detail != null) {
			detail = detail.trim();
		}
		PreparedStatement pseq, psub;
		ResultSet seqno;
		int curr_seq = 0;
		int r_value = 0;
		con = null;
		if (con == null)
			con = connect.getdbConnection();
		try {
			String seqQuery = "select nextval('identifier_code_id')";
			pseq = con.prepareStatement(seqQuery);
			seqno = pseq.executeQuery();
			while (seqno.next()) {
				curr_seq = Integer.parseInt(seqno.getString(1));
				Logging.debug("Present Seq No. " + curr_seq);
			}
			psub = con.prepareStatement(ConnectInit.queries
					.getProperty("insert_identifier_codes"));
			psub.setInt(1, curr_seq);
			psub.setString(2, stname);
			psub.setString(3, detail);
			psub.executeUpdate();
			r_value = 1;
			seqno.close();
			psub.close();
			pseq.close();
		} catch (Exception et) {
			Logging.error("Error :" + et);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
	}

	/**
	 * function to check duplicate entries in the table. funtion is called only
	 * when new data is inserted.
	 */
	public int checkData(String name) {
		PreparedStatement checkcon;
		ResultSet rs1;
		int ans = 0;
		String nm1 = null;
		con = null;
		if (con == null)
			con = connect.getdbConnection();
		try {
			checkcon = con.prepareStatement(ConnectInit.queries
					.getProperty("check_identifier_codes"));
			checkcon.setString(1, name);
			rs1 = checkcon.executeQuery();
			while (rs1.next()) {
				nm1 = rs1.getString(1);
			}
			if (nm1 != null) {
				if (nm1.equalsIgnoreCase(name)) {
					ans = 1;
				}
			}
			rs1.close();
			checkcon.close();
		} catch (Exception e) {
			Logging.error("Error check() :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		return ans;
	}

	public void updateData(int id, String stname, String detail) {
		if (stname != null) {
			stname = stname.trim();
		}
		if (detail != null) {
			detail = detail.trim();
		}
		if (id != 0) {
			int id_int = id;
			PreparedStatement psub;
			con = null;
			if (con == null)
				con = connect.getdbConnection();
			try {
				psub = con.prepareStatement(ConnectInit.queries
						.getProperty("update_identifier_codes"));
				psub.setString(1, stname);
				psub.setString(2, detail);
				psub.setInt(3, id_int);
				Logging.debug("statement........" + psub);
				psub.executeUpdate();
				psub.close();
			} catch (Exception et) {
				Logging.error("Error :" + et);
			} finally {
				try {
					if (con != null)
						con.close();
				} catch (Exception ee) {
					Logging.error(" Error : Unable to close Connection "
							+ ee.getMessage());
				}
			}
		}
	}
}