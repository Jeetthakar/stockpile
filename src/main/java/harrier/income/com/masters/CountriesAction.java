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
public class CountriesAction extends org.apache.struts.action.Action {
	Logger Logging = Logger.getLogger(CountriesAction.class);
	public static final String FORWARD_start = "success";

	Connect connect = ConnectInit.getConnect();

	Connection con = null;

	static int ids, cont_id, curr_id;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		countries cont = (countries) form;
		String name = cont.getName1();
		String shname = cont.getShname();
		ids = cont.getIdname_check();
		Logging.debug("idname>>>>>" + ids);
		cont_id = cont.getContinent();
		curr_id = cont.getCurrency();
		String update = cont.getUpdate();
		String new1 = cont.getNew1();
		//if(update != null && ids != null)
		/**
		 * check if user wants to update data. If yes then call update funtion
		 * else check if user wants to add new element. after update or add
		 * reset bean variables.
		 */
		if (update != null)
		//update != null)
		//IMPortant//&& ids != 0)
		{
			cont = (countries) form;
			name = cont.getName1();
			shname = cont.getShname();
			ids = countries.idname_check;
			Logging.debug("idname>>>>>" + ids);
			cont_id = countries.cont_list_value;
			curr_id = countries.curr_list_value;

			String continent_id = cont.getSelectContinent();
			String currency_id = cont.getSelectCurrency();

			update = cont.getUpdate();
			new1 = cont.getNew1();
			updateData(ids, name, shname, continent_id, currency_id);
			cont.setUpdate(null);
			cont.setIdname_check(0);
			ids = 0;
			countries.setCon_flag("U");
			return (new ActionForward(
					"/pages/masters/roleAdded.jsp?flag=countriesaction&value=update"));
		} else if (new1 != null) {
			cont = (countries) form;
			name = cont.getName1();
			shname = cont.getShname();
			ids = countries.idname_check;
			Logging.debug("idname>>>>>" + ids);
			cont_id = countries.cont_list_value;
			curr_id = countries.curr_list_value;
			cont_id = cont.getCont_list_value();
			curr_id = cont.getCurr_list_value();

			String continent_id = cont.getSelectContinent();
			String currency_id = cont.getSelectCurrency();
			submitData(name, shname, continent_id, currency_id);
			cont.setNew1(null);
			ids = 0;
			countries.setCon_flag("N");
			return (new ActionForward(
					"/pages/masters/roleAdded.jsp?flag=countriesaction&value=insert"));
		}
		return mapping.findForward(FORWARD_start);
	}

	/**
	 * Ones data is validated, record is updated or added as per the action
	 * selected by the user. Appropriate messges are displayed to the user for
	 * the action.
	 *  
	 */
	public void submitData(String stname, String shname, String cont_id,
			String curr_id) {
		int check = checkData(stname);
		if (check != 1) {
			PreparedStatement pseq, psub;
			ResultSet seqno;
			int curr_seq = 0;
			int curr_seq_final = 0;
			int r_value = 0;
			String cont_id_int = cont_id;
			String curr_id_int = curr_id;
			con = null;

			try {
				if (con == null) {
					con = connect.getdbConnection();
				}
				//String seqQuery = "select nextval('country_id')";
				String seqQuery = "select MAX(country_id) from countries";
				pseq = con.prepareStatement(seqQuery);
				seqno = pseq.executeQuery();
				while (seqno.next()) {
					curr_seq = Integer.parseInt(seqno.getString(1));

				}
				curr_seq_final = curr_seq + 1;
				psub = con.prepareStatement(ConnectInit.queries
						.getProperty("insert_countries"));
				psub.setInt(1, curr_seq_final);
				psub.setString(2, stname);
				psub.setString(3, shname);

				if (cont_id_int == null
						|| cont_id_int.equals("")
						|| (cont_id_int != null && cont_id_int.trim().length() == 0)) {
					psub.setString(4, null);
				} else {
					psub.setString(4, cont_id_int);
				}

				if (curr_id_int == null
						|| curr_id_int.equals("")
						|| (curr_id_int != null && curr_id_int.trim().length() == 0)) {
					psub.setString(5, null);
				} else {
					psub.setString(5, curr_id_int);
				}

				psub.executeUpdate();
				r_value = 1;
				seqno.close();
				pseq.close();
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
					.getProperty("check_country"));
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

	/**
	 * This updateData method is used to change the present values with the
	 * values passed by the respective parameters.
	 * 
	 * @param id
	 * @param stname
	 * @param shname
	 * @param contid
	 * @param currid
	 */
	public void updateData(int id, String stname, String shname, String contid,
			String currid) {
		int id_int = id;
		String cont_id = contid;
		String curr_id = currid;
		PreparedStatement psub;
		con = null;
		if (con == null) {
			con = connect.getdbConnection();
		}
		try {
			psub = con.prepareStatement(ConnectInit.queries
					.getProperty("update_countries"));
			Logging.debug("Query :" + psub);
			psub.setString(1, stname);
			psub.setString(2, shname);
			if (cont_id.equals("")) {
				psub.setString(3, null);
			}
			psub.setString(3, cont_id);
			if (curr_id.equals("")) {
				psub.setString(4, null);
			}
			psub.setString(4, curr_id);
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