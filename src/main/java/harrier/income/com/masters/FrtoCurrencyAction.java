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
public class FrtoCurrencyAction extends Action {
	Logger Logging = Logger.getLogger(FrtoCurrencyAction.class);
	public static final String FORWARD_start = "success";

	Connect connect = ConnectInit.getConnect();

	Connection con;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int flag = 0;
		FrtoCurrency curr = (FrtoCurrency) form;
		String name = curr.getName_frto();
		String desc = curr.getDesc_frto();
		int curr1 = FrtoCurrency.curr_1;
		int curr2 = FrtoCurrency.curr_2;
		String update = curr.getUpdate();
		String new1 = curr.getNew1();
		int ids = FrtoCurrency.id_val;
		String exchange_rate = null;
		exchange_rate = curr.getExchange_rate();
		String currency1 = curr.getSelectCurrency1();
		String currency2 = curr.getSelectCurrency2();
		/**
		 * check if user wants to update data. If yes then call update funtion
		 * else check if user wants to add new element. after update or add
		 * reset bean variables.
		 */
		if (update != null) {
			curr.setUpdate(null);
			//updateData(ids,name,desc,curr1,curr2);
			updateData(ids, name, desc, currency1, currency2);
			curr.setId_name_check(0);
			FrtoCurrency.id_val = 0;
			FrtoCurrency.setCon_flag("U");
			return (new ActionForward(
					"/pages/masters/roleAdded.jsp?flag=frtocurrency&value=update"));
		} else if (new1 != null) {
			curr.setNew1(null);
			//storeData(name,desc,curr1,curr2,exchange_rate);
			storeData(name, desc, currency1, currency2, exchange_rate);
			curr.setId_name_check(0);
			FrtoCurrency.id_val = 0;
			FrtoCurrency.setCon_flag("N");
			return (new ActionForward(
					"/pages/masters/roleAdded.jsp?flag=frtocurrency&value=insert"));
		}
		return mapping.findForward(FORWARD_start);
	}

	public String storeData(String name1, String desc1, String var,
			String var1, String exchange_rate) {
		String name;
		String result = null;
		String lst_1 = var;
		String lst_2 = var1;
		ResultSet seqno;
		PreparedStatement pstdata, pstseq, pst_exc, pst_intra;
		con = null;
		try {
			if (con == null)
				con = connect.getdbConnection();
			try {
				String seqQuery = "select nextval('from_to_currency_id')";
				pstseq = con.prepareStatement(seqQuery);
				int curr_seq = 0;
				seqno = pstseq.executeQuery();
				while (seqno.next()) {
					curr_seq = Integer.parseInt(seqno.getString(1));
					Logging.debug("Present Seq No. " + curr_seq);
				}
				pstdata = con.prepareStatement(ConnectInit.queries
						.getProperty("insert_frto_currency"));
				pstdata.setInt(1, curr_seq);
				pstdata.setString(2, name1);
				if (lst_2.equals("")) {
					pstdata.setString(3, null);
				}
				pstdata.setString(3, lst_2);

				if (lst_1.equals("")) {
					pstdata.setString(4, null);
				}
				pstdata.setString(4, lst_1);

				pstdata.setString(5, desc1);
				pstdata.executeUpdate();
				pstdata.close();
				if (exchange_rate != null) {
					float exchange_rate_int = Float.parseFloat(exchange_rate);
					pst_exc = con.prepareStatement(ConnectInit.queries
							.getProperty("insert_frto_exchange_rate_dailly"));
					pst_exc.setFloat(1, exchange_rate_int);
					pst_exc.setFloat(2, exchange_rate_int);
					pst_exc.setFloat(3, exchange_rate_int);
					pst_exc.setFloat(4, exchange_rate_int);
					pst_exc.setInt(5, curr_seq);
					pst_exc.executeUpdate();
					pst_intra = con.prepareStatement(ConnectInit.queries
							.getProperty("insert_frto_intra_day"));
					pst_intra.setInt(1, curr_seq);
					pst_intra.setFloat(2, exchange_rate_int);
					pst_intra.executeUpdate();
					pst_intra.close();
					pst_exc.close();
				}
				result = "ok";
				seqno.close();
				return result;
			} catch (Exception e) {
				Logging.error("Error :" + e);
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
		return null;
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

		try {
			if (con == null) {
				con = connect.getdbConnection();
			}
			checkcon = con.prepareStatement(ConnectInit.queries
					.getProperty("check_frto_currency"));
			checkcon.setString(1, name);
			rs1 = checkcon.executeQuery();
			while (rs1.next()) {
				nm1 = rs1.getString(1);
			}
			if (nm1 != null && nm1.equalsIgnoreCase(name)) {
				ans = 1;
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

	public void updateData(int id, String stname, String detail, String curr1,
			String curr2) {
		int id_int = id;
		PreparedStatement psub;
		String curr_int1 = curr1;
		String curr_int2 = curr2;
		con = null;

		try {
			if (con == null) {
				con = connect.getdbConnection();
			}
			psub = con.prepareStatement(ConnectInit.queries
					.getProperty("update_frto_currency"));
			psub.setString(1, stname);
			if (curr_int1.equals("")) {
				psub.setString(2, null);
			}
			psub.setString(2, curr_int1);

			if (curr_int2.equals("")) {
				psub.setString(3, null);
			}
			psub.setString(3, curr_int2);
			psub.setString(4, detail);
			psub.setInt(5, id_int);
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