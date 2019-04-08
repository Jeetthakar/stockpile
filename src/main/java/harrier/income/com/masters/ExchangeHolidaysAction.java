/*
 * Created on Mar 11, 2006
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
public class ExchangeHolidaysAction extends Action {
	Logger Logging = Logger.getLogger(ExchangeHolidaysAction.class);
	public static final String FORWARD_start = "success";

	Connect connect = ConnectInit.getConnect();

	Connection con = null;

	/**
	 * check if user wants to update data. If yes then call update funtion else
	 * check if user wants to add new element. after update or add reset bean
	 * variables.
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ExchangeHolidaysForm form1 = (ExchangeHolidaysForm) form;
		String update = form1.getUpdate();
		String new1 = form1.getNew1();
		int ids = Integer.parseInt(form1.getSelectExchange()); //for stk.
															   // exchange
		String name = form1.getName();
		String date_sel = form1.getDate_sel();
		String desc = form1.getDesc();
		String flag = form1.getFlag();
		int stk_ex_id = Integer.parseInt(form1.getSelectExchange());
		if (!flag.equalsIgnoreCase("S")) {
			/**
			 * check if user wants to update data. If yes then call update
			 * funtion else check if user wants to add new element. after update
			 * or add reset bean variables.
			 */
			if (update != null) {
				try {
					updateData(ExchangeHolidaysForm.holi_id, name, date_sel,
							desc);
					form1.setUpdate(null);
					form1.setExch_id(0);
					form1.setHoliday_list_vector("" + stk_ex_id);
					ExchangeHolidaysForm.setHoli_id(0);
					ExchangeHolidaysForm.holi_id = 0;
					ExchangeHolidaysForm.setCon_flag("U");
					
					return (new ActionForward(
							"/pages/masters/roleAdded.jsp?flag=ExchangeHolidays&value=update"));
				} catch (Exception e) {
					Logging.error("updating null id error " + e);
				}
			} else if (new1 != null) {
				//stk_ex_id = form1.getExch_id();
				form1.setNew1(null);
				form1.setHolidayID_h(0);
				ExchangeHolidaysForm.holi_id = 0;
				form1.setExch_id(0);
				submitData(name, date_sel, desc, stk_ex_id);
				form1.setHoliday_list_vector("" + stk_ex_id);
				ExchangeHolidaysForm.setHoli_id(0);
				ExchangeHolidaysForm.setCon_flag("N");
				return (new ActionForward(
						"/pages/masters/roleAdded.jsp?flag=ExchangeHolidays&value=insert"));
			}
		}
		return mapping.findForward(FORWARD_start);
	}

	/**
	 * Ones data is validated, record is updated or added as per the action
	 * selected by the user. Appropriate messges are displayed to the user for
	 * the action.
	 *  
	 */
	public void submitData(String name_s, String date_s, String desc_s,
			int stk_ex_s) {
		int curr_seq = 0;
		int r_value = 0;
		int st_ex_int = stk_ex_s;
		PreparedStatement pst, pseq;
		ResultSet rst, seqno;
		con = null;
		if (con == null) {
			con = connect.getdbConnection();
		}
		Logging.debug("stname : " + stk_ex_s);
		try {
			String seqQuery = "select nextval('stock_ex_holiday_id')";
			pseq = con.prepareStatement(seqQuery);
			seqno = pseq.executeQuery();
			while (seqno.next()) {
				curr_seq = seqno.getInt(1);
				Logging.debug("Present Seq No. " + curr_seq);
			}
			pst = con.prepareStatement(ConnectInit.queries
					.getProperty("insert_stock_ex_holiday"));
			pst.setInt(1, curr_seq);
			pst.setString(2, name_s);
			pst.setString(3, date_s);
			pst.setString(4, desc_s);
			pst.setInt(5, st_ex_int);
			pst.executeUpdate();
			ExchangeHolidaysForm.setCon_flag("N");
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
	}

	public void updateData(int id_up, String name_up, String date_up,
			String desc_up) {
		PreparedStatement pst_up;
		ResultSet rst_up;
		int id_int = id_up;
		con = null;
		try {
			if (con == null) {
				con = connect.getdbConnection();
			}
			Logging.debug("stname : " + id_up);
			try {
				pst_up = con.prepareStatement(ConnectInit.queries
						.getProperty("update_holiday_master"));
				Logging.debug("stname : " + pst_up);
				pst_up.setString(1, name_up);
				pst_up.setString(2, date_up);
				pst_up.setString(3, desc_up);
				pst_up.setInt(4, id_int);
				pst_up.executeUpdate();
				ExchangeHolidaysForm.setCon_flag("U");
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