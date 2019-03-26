/*
 * Created on Mar 6, 2006
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

import app.AuditTrail;
import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * @author naresh
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class StockTypeAction extends Action {
	Logger Logging = Logger.getLogger(StockTypeAction.class);
	public static final String FORWARD_start = "success";

	Connect connect = ConnectInit.getConnect();

	Connection con;

	PreparedStatement psub, pseq, checkcon;

	ResultSet rs1, seqno;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		stocktype type = (stocktype) form;
		String from = null;
		ActionForward fr = null;
		String name = type.getName();
		if (name != null) {
			name = name.trim();
		}
		String new1 = type.getNew1();
		String update = type.getUpdate();
		int ids = type.getId_check();
		if (ids != 0) {
			//ids=ids.trim();
		}
		/**
		 * check if user wants to update data. If yes then call update funtion
		 * else check if user wants to add new element. after update or add
		 * reset bean variables.
		 */
		if (update != null) {
			int i = updateData(ids, name);
			type.setUpdate(null);
			type.setId_check(0);
			type.setNew1(null);
			stocktype.setConf_flag("U");
			return (new ActionForward(
					"/pages/masters/roleAdded.jsp?flag=StockType&value=update"));
		} else if (new1 != null) {
			submitData(name);
			type.setNew1(null);
			type.setId_check(0);
			type.setUpdate(null);
			stocktype.setConf_flag("N");
			return (new ActionForward(
					"/pages/masters/roleAdded.jsp?flag=StockType&value=insert"));
		}
		from = type.getFrom();
		Logging.debug(" from is " + from);
		if (from != null && from.equals("stkmaster")) {
			Logging.debug("inside return from " + from);
			return fr = new ActionForward(response
					.encodeURL("/pages/masters/stockmaster2.jsp"));
		}
		return mapping.findForward(FORWARD_start);
	}

	/**
	 * Ones data is validated, record is updated or added as per the action
	 * selected by the user. Appropriate messges are displayed to the user for
	 * the action.
	 *  
	 */
	public void submitData(String stname) {
		int curr_seq = 0;
		int r_value = 0;
		con = null;
		try {
			if (con == null)
				con = connect.getdbConnection();
			try {
				//String seqQuery = "select nextval('stock_type_id')";
				String seqQuery = "select MAX(stock_type_id) from stock_type";
				pseq = con.prepareStatement(seqQuery);
				seqno = pseq.executeQuery();
				while (seqno.next()) {
					curr_seq = Integer.parseInt(seqno.getString(1));
				}
				curr_seq++;

				String Query1 = "insert into stock_type(stock_type_id,stock_type_name) "
						+ " values(" + curr_seq + ",'" + stname + "')";
				psub = con.prepareStatement(ConnectInit.queries
						.getProperty("insert_stock_type"));
				psub.setInt(1, curr_seq);
				psub.setString(2, stname);
				psub.executeUpdate();
				String values = curr_seq + "," + stname;
				AuditTrail.StoreTableInsertUpdate("71", null, values);
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
		con = null;
		if (con == null)
			con = connect.getdbConnection();
		try {
			String query = "Select stock_type_name " + " from stock_type "
					+ " where upper(stock_type_name) = upper('" + name + "')";
			checkcon = con.prepareStatement(ConnectInit.queries
					.getProperty("check_stock_type"));
			checkcon.setString(1, name);
			rs1 = checkcon.executeQuery();
			while (rs1.next()) {
				nm1 = rs1.getString(1);
				id = rs1.getInt(2);
			}
			if (str.equals("U")) {
				ans = id;
			} else if (nm1 != null && nm1.equalsIgnoreCase(name)) {
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

	public int updateData(int id, String stname) {
		if (id != 0) {
			int id_int = id;
			PreparedStatement psub;
			con = null;
			try {
				if (con == null)
					con = connect.getdbConnection();
				try {
					String Query1 = "update stock_type "
							+ "set stock_type_name = '" + stname + "' "
							+ "where stock_type_id = " + id_int + " ";
					psub = con.prepareStatement(ConnectInit.queries
							.getProperty("update_stock_type"));
					psub.setString(1, stname);
					psub.setInt(2, id_int);
					psub.executeUpdate();
					String values = id_int + "," + stname;
					AuditTrail.StoreTableInsertUpdate("72", null, values);
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
			return 1;
		}
		return 0;
	}
}