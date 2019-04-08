/*
 * Created on Mar 8, 2006
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
import app.LogonAction;

import com.harrier.initializeation.ConnectInit;

/**
 * @author naresh
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TimeZoneAction extends Action {
	Logger Logging = Logger.getLogger(TimeZoneAction.class);
	Connection con = null;

	Connect connect = ConnectInit.getConnect();

	PreparedStatement psub, pseq, checkcon;

	ResultSet rs1, seqno;

	public static final String FORWARD_start = "success";

	Logger log;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log = Logger.getLogger(LogonAction.class.getName());
		TimeZones tobj = (TimeZones) form;
		int tid = 0;
		float time_diff = 0;
		try {
			time_diff = Float.parseFloat(tobj.getTime_d());

			String tid_str = TimeZones.getTid();
			if (tid_str != null) {
				tid = Integer.parseInt(tid_str);
			}
		} catch (Exception e) {
			Logging.error("Error  :" + e.getMessage());
		}
		String desc = tobj.getDesc();
		if (desc != null) {
			desc = desc.trim();
		}
		String new1 = tobj.getNew1();
		//String upd = tobj.getUpdate();
		String rChk = tobj.getRadioChk();
		log.debug("$$$$$$$$$$$$$$$" + new1);
		log.debug("$$$$$$$$$$$$$$$" + desc);
		log.debug("$$$$$$$$$$$$$$$" + rChk);
		//if(upd != null)
		if (rChk != null && rChk.equals("update")) {
			log.debug("$$$$$$$$$$$$$$$ calling update");
			updateData(tid, time_diff, desc);
			TimeZones.setConf_flag("U");
			tobj.setUpdate(null);
			tobj.setTime_d(null);
			tobj.setDesc(null);
			return (new ActionForward(
					"/pages/masters/roleAdded.jsp?flag=TimeZone&value=update"));
		} else if (rChk != null && rChk.equals("new1")) {
			log.debug("$$$$$$$$$$$$$$$ calling insert");
			int call = 0;
			log.debug("$$$$$$$$$$$$$$$ call " + call + time_diff + desc);
			if (call != 1 && time_diff != 0 && desc != null) {
				submitData(time_diff, desc);
				TimeZones.setConf_flag("N");
				tobj.setTime_d(null);
				tobj.setDesc(null);
				tobj.setNew1(null);
				return (new ActionForward(
						"/pages/masters/roleAdded.jsp?flag=TimeZone&value=insert"));
			}
		}
		return mapping.findForward(FORWARD_start);
	}

	public void submitData(float time, String detail) {
		int curr_seq = 0;
		int r_value = 0;
		con = null;
		try {
			if (con == null)
				con = connect.getdbConnection();
			log.debug("stname : " + time);
			try {
				String seqQuery = "select nextval('time_zone_id')";
				pseq = con.prepareStatement(seqQuery);
				seqno = pseq.executeQuery();
				while (seqno.next()) {
					curr_seq = Integer.parseInt(seqno.getString(1));
					log.debug("Present Seq No. " + curr_seq);
				}
				psub = con.prepareStatement(ConnectInit.queries
						.getProperty("insert_time_zone"));
				psub.setInt(1, curr_seq);
				psub.setFloat(2, time);
				psub.setString(3, detail);
				psub.executeUpdate();
				r_value = 1;
				psub.close();
				seqno.close();
				pseq.close();
			} catch (Exception et) {
				Logging.error("Error :" + et);
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

	public void updateData(int time_id, float stname, String detail) {
		PreparedStatement pseq, psub;
		ResultSet seqno;
		int curr_seq = 0;
		int r_value = 0;
		con = null;
		try {
			if (con == null)
				con = connect.getdbConnection();
			Logging.debug("stname : " + stname);
			try {
				psub = con.prepareStatement(ConnectInit.queries
						.getProperty("update_time_zone"));
				psub.setFloat(1, stname);
				psub.setString(2, detail);
				psub.setInt(3, time_id);
				psub.executeUpdate();
				psub.close();
			} catch (Exception et) {
				Logging.error("Error :" + et);
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