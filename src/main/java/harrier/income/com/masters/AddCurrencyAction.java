/*
 * Created on Mar 13, 2006
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
public class AddCurrencyAction extends Action {
	Logger Logging = Logger.getLogger(AddCurrencyAction.class);
	public static final String FORWARD_start = "success";

	Connect connect = ConnectInit.getConnect();

	Connection con = null;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AddCurrency curr = (AddCurrency) form;
		String name = curr.getName_add();
		String code = curr.getCode_add();
		String new1 = curr.getNew1_add();
		String update = curr.getUpdate_add();
		int idname = curr.getIdname_add();
		//int ids = curr.getIds();
		int ids = AddCurrency.identifier_id_add;
		String selectCurrency = curr.getSelectCurrency();
		curr.setSelectCurrency(selectCurrency);
		/**
		 * check if user wants to update data. If yes then call update funtion
		 * else check if user wants to add new element. after update or add
		 * reset bean variables.
		 */
		if (update != null) {
			Logging.debug("calling update........" + new1);
			curr.setUpdate_add(null);
			updateData(ids, name, code);
			AddCurrency.identifier_id_add = 0;
			curr.setId_add_check(0);
			AddCurrency.setCon_flag("U");
			return (new ActionForward(
					"/pages/masters/roleAdded.jsp?flag=AddCurrency&value=update"));
		} else if (new1 != null) {
			submitData(name, code);
			Logging.debug("calling submit........" + name);
			curr.setNew1_add(null);
			AddCurrency.identifier_id_add = 0;
			AddCurrency.setCon_flag("N");
			return (new ActionForward(
					"/pages/masters/roleAdded.jsp?flag=AddCurrency&value=insert"));
		}
		return mapping.findForward(FORWARD_start);
	}

	/**
	 * Ones data is validated, record is updated or added as per the action
	 * selected by the user. Appropriate messges are displayed to the user for
	 * the action.
	 *  
	 */
	public void submitData(String stname, String code) {
		AddCurrency curr = new AddCurrency();
		int check = curr.checkData(stname);
		if (check != 1) {
			PreparedStatement pseq, psub;
			ResultSet seqno;
			int curr_seq = 0;
			int r_value = 0;
			con = null;
			if (con == null)
				con = connect.getdbConnection();
			try {
				String seqQuery = "select nextval('currency_id')";
				pseq = con.prepareStatement(seqQuery);
				seqno = pseq.executeQuery();
				while (seqno.next()) {
					curr_seq = Integer.parseInt(seqno.getString(1));
				}
				psub = con.prepareStatement(ConnectInit.queries
						.getProperty("insert_addcurrency"));
				Logging.debug("query........ :" + psub);
				psub.setInt(1, curr_seq);
				psub.setString(2, stname);
				psub.setString(3, code);
				psub.executeUpdate();
				r_value = 1;
				seqno.close();
				pseq.close();
			} catch (Exception et) {
				Logging.error("Error submit :" + et);
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

	/**
	 * function to check duplicate entries in the table. funtion is called only
	 * when new data is inserted.
	 */
	/*
	 * public int checkData(String name) { PreparedStatement checkcon; ResultSet
	 * rs1; int ans=0; String nm1 = null; if(con == null) { con =
	 * connect.getConnection(); } try { checkcon =
	 * con.prepareStatement(connect.queries.getProperty("check_addcurrency"));
	 * checkcon.setString(1,name); rs1 = checkcon.executeQuery();
	 * while(rs1.next()) { nm1 = rs1.getString(1); }
	 * if(nm1.equalsIgnoreCase(name)) { ans = 1; } }catch(Exception e) {
	 * Logging.getError("Error check() :"+e); } finally{ try{if(con!=null)
	 * con.close(); }catch(Exception ee){ Logging.getError(" Error : Unable to
	 * close Connection "+ee.getMessage()); } } return ans; }
	 */

	public void updateData(int id, String stname, String code) {
		int id_int = id;
		PreparedStatement psub;
		con = null;
		try {
			if (con == null)
				con = connect.getdbConnection();
			try {
				psub = con.prepareStatement(ConnectInit.queries
						.getProperty("update_addcurrency"));
				psub.setString(1, stname);
				psub.setString(2, code);
				psub.setInt(3, id_int);
				psub.executeUpdate();
				psub.close();
			} catch (Exception et) {
				Logging.error("Error update:" + et);
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