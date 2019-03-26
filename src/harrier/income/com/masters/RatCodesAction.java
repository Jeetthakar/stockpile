/*
 * Created on Mar 17, 2006
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
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * Once data is validated, record is updated or added as per the action selected
 * by the user. Appropriate messges are displayed to the user for the action.
 *  
 */
public class RatCodesAction extends org.apache.struts.action.Action {
	Logger Logging = Logger.getLogger(RatCodesAction.class);
	public static final String FORWARD_start = "success";

	Connection con;

	Connect connect = ConnectInit.getConnect();

	PreparedStatement psub, pseq;

	ResultSet rs1;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int check = 0;
		RatingCodes rat = (RatingCodes) form;
		String new1 = null;
		String update = null;
		try {
			new1 = (rat.getNew1());
			update = (rat.getUpdate());
		} catch (Exception u) {
			Logging.error("Error  :" + u.getMessage());
		}
		String rname = (rat.getRat_name());
		if (rname != null) {
			rname = rname.trim();
		}
		String detail = (rat.getRat_desc());
		if (detail != null) {
			detail = detail.trim();
		}
		int ids = 0;
		try {
			//ids = rat.getRat_id();
		   //ids = rat.getRat_id();
			ids = RatingCodes.rat_id;
		} catch (Exception t) {
			Logging.error("Error  :" + t.getMessage());
		}
		/**
		 * check if user wants to update data. If yes then call update funtion
		 * else check if user wants to add new element. after update or add
		 * reset bean variables.
		 */
		if (update != null) {
			updateData(ids, rname, detail);
			RatingCodes.setConf_flag("U");
			RatingCodes.rat_id = 0;
			return (new ActionForward(
					"/pages/masters/roleAdded.jsp?flag=RatingCodes&value=update"));
		} else if (new1 != null) {
			submitData(rname, detail);
			RatingCodes.setConf_flag("N");
			RatingCodes.rat_id = 0;
			return (new ActionForward(
					"/pages/masters/roleAdded.jsp?flag=RatingCodes&value=insert"));
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
		int curr_seq = 0;
		int r_value = 0;
		PreparedStatement pst;
		ResultSet rst, seqno;
		con = null;
		if (con == null)
			con = connect.getdbConnection();
		Logging.debug("stname : " + stname);
		try {
			String seqQuery = "select nextval('rating_code_id')";
			pseq = con.prepareStatement(seqQuery);
			seqno = pseq.executeQuery();
			while (seqno.next()) {
				curr_seq = Integer.parseInt(seqno.getString(1));
				Logging.debug("Present Seq No. " + curr_seq);
			}
			pst = con.prepareStatement(ConnectInit.queries
					.getProperty("in_rating_codes"));
			pst.setInt(1, curr_seq);
			pst.setString(2, stname);
			pst.setString(3, detail);
			pst.executeUpdate();
			pst.close();
			seqno.close();
			pseq.close();
		} catch (Exception e) {
			Logging.error("Inside submit_data error " + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		r_value = 1;
	}

	/**
	 * function to check duplicate entries in the table. funtion is called only
	 * when new data is inserted.
	 */
	public int checkData(String name, String str) {
		if (name != null) {
			name = name.trim();
		}
		int ans = 0, id = 0;
		String nm1 = null;
		PreparedStatement pst_chk;
		ResultSet rst_chk;
		con = null;
		try {
			if (con == null)
				con = connect.getdbConnection();
			Logging.debug("Inside checkData . " + name);
			try {
				pst_chk = con.prepareStatement(ConnectInit.queries
						.getProperty("chk_rating_codes"));
				pst_chk.setString(1, name);
				rst_chk = pst_chk.executeQuery();
				while (rst_chk.next()) {
					nm1 = rst_chk.getString(1);
					id = rst_chk.getInt(2);
					Logging.debug("Cou Name is . " + nm1);
				}
				if (str.equals("update")) {
					ans = id;
				} else if (nm1 != null && nm1.equalsIgnoreCase(name)) {
					ans = 1;
				}
				rst_chk.close();
				pst_chk.close();
			} catch (Exception e) {
				Logging.error("Error check() :" + e);
			}
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

	public void updateData(int id_up, String stname, String detail) {
		PreparedStatement pst_up;
		con = null;
		try {
			if (con == null)
				con = connect.getdbConnection();
			Logging.debug("stname : " + stname);
			Logging.debug("stname : " + detail);
			try {
				pst_up = con.prepareStatement(ConnectInit.queries
						.getProperty("up_rating_codes"));
				pst_up.setString(1, stname);
				pst_up.setString(2, detail);
				pst_up.setInt(3, id_up);
				pst_up.executeUpdate();
				pst_up.close();
			} catch (Exception e) {
				Logging.error("Inside submit_data error " + e);
			}
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