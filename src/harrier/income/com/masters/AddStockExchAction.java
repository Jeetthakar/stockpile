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
import java.sql.SQLException;

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
public class AddStockExchAction extends Action {
	Logger Logging = Logger.getLogger(AddStockExchAction.class);
	public static final String FORWARD_start = "success";

	Connection connection = null;

	Connect con = ConnectInit.getConnect();

	/**
	 * Action class for AddStockExchange
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AddStockExchForm exch = (AddStockExchForm) form;
		String new1 = exch.getNew1();
		String update = exch.getUpdate();
		String stk_name = exch.getStk_name();
		String cnt_name = exch.getSelectCountry();
		String tm_zone = exch.getSelectTimeZone();
		String username = "";
		String stk_ex_code = exch.getStk_ex_code();
		String ex_identifier_code = exch.getEx_identifier_code();
		int ids = exch.getExch_id();
		String start_time = exch.getStart_time();
		String stop_time = exch.getStop_time();
		/**
		 * check if user wants to update data. If yes then call update funtion
		 * else check if user wants to add new element. after update or add
		 * reset bean variables.
		 */
		if (update != null) {
			updateData(ids, stk_name, cnt_name, tm_zone, stk_ex_code,
					start_time, stop_time, ex_identifier_code); // call update
			// funtion
			exch.setUpdate(null);
			exch.setNew1(null);
			exch.setExch_id(0);
			AddStockExchForm.setCon_flag("U");
			return (new ActionForward(
					"/pages/masters/roleAdded.jsp?flag=AddStockExchange&value=update"));
		} else if (new1 != null) //check if user wants to add data.
		{
			submitData(stk_name, cnt_name, tm_zone, stk_ex_code, start_time,
					stop_time, ex_identifier_code); //call add funtion
			exch.setNew1(null);
			exch.setUpdate(null);
			exch.setExch_id(0);
			AddStockExchForm.setCon_flag("N");
			return (new ActionForward(
					"/pages/masters/roleAdded.jsp?flag=AddStockExchange&value=insert"));
		}
		return mapping.findForward(FORWARD_start);
	}

	/**
	 * While adding new data check if identifiercode is entered. if entered then
	 * insert with the identifier code id in the exchange_master. if not entered
	 * then use the exchange code as identifier code and also update the
	 * identifier_code table.
	 */
	public void submitData(String stkname, String cntname_id, String tmzone_id,
			String stkexcode, String start, String stop,
			String identifier_code_name) {
		PreparedStatement pseq, psub, id_check, pseq1;
		ResultSet seqno, id_res, seqno1;
		int curr_seq = 0;
		int user_id = 0;
		//int cont_id_int =10;
		int cont_id_int = Integer.parseInt(cntname_id);
		//int time_id_int =10;
		int time_id_int = Integer.parseInt(tmzone_id);
		connection = null;
		try {
			if (connection == null) {
				connection = con.getdbConnection();
			}
			//--If identifier code is not null.
			if (!identifier_code_name.equalsIgnoreCase("")) {
				try {
					id_check = connection.prepareStatement(ConnectInit.queries
							.getProperty("check_identifier_name"));
					id_check.setString(1, identifier_code_name);
					id_res = id_check.executeQuery();
					if (id_res.next() == true) //-- If identifier code exist in
					// database.
					{
						int ident_code_int = id_res.getInt(1);
						String seqQuery = "select nextval('stock_ex_id')";
						pseq = connection.prepareStatement(seqQuery);
						seqno = pseq.executeQuery();
						while (seqno.next()) {
							curr_seq = Integer.parseInt(seqno.getString(1));
						}
						Logging.debug("current seq......" + curr_seq);
						/*
						 * LogonForm form1= new LogonForm(); String
						 * abc=form1.getUsername() ; Logging.getDebug("User is
						 * is>>"+abc); pseq1 =
						 * connection.prepareStatement(con.queries.getProperty("get_user_id_users"));
						 * pseq1.setString(1,abc); seqno1 =
						 * pseq1.executeQuery(); while(seqno1.next()) { user_id =
						 * seqno1.getInt(1);
						 * Logging.getDebug("user_id>>>>>>>>>>="+user_id); }
						 */
						insert_exch_master(stkname, cntname_id, tmzone_id,
								stkexcode, start, stop, ident_code_int);
						//,user_id);
					} else //-- If identifier code does not exist in database.
					{
						//-- Insert in Identifier_codes & Stock_exchange_master
						int ret_id = insert_identifier_code(identifier_code_name);
						insert_exch_master(stkname, cntname_id, tmzone_id,
								stkexcode, start, stop, ret_id);
						//,user_id);
					}
				} catch (Exception et) {
					Logging.error("Error in submit ....:" + et);
				}
			} else if (identifier_code_name.equalsIgnoreCase("")) {
				int ret_id_n = insert_identifier_code(stkexcode);
				insert_exch_master(stkname, cntname_id, tmzone_id, stkexcode,
						start, stop, ret_id_n);
				//,user_id);
			}
		}
		/*
		 * finally{ try{if(connection!=null) connection.close();
		 */
		catch (Exception ee) {
			Logging.error(" Error : Unable to close Connection "
					+ ee.getMessage());
		}
	}

	/**
	 * data should be entered in the identifier_code table first as exchange
	 * master refers the identifier_code_id as foreign key.
	 */
	public void updateData(int id, String stk_name, String cnt_name,
			String tm_zone, String stk_ex_code, String start, String stop,
			String ex_code) {
		//int id_int = Integer.parseInt(id);
		int id_int = id;
		int ex_id_code;
		//int cont_id =10;
		int cont_id = Integer.parseInt(cnt_name);
		//int time_zone_id =10;
		int time_zone_id = Integer.parseInt(tm_zone);
		PreparedStatement psub;
		connection = null;
		try {
			if (connection == null) {
				connection = con.getdbConnection();
			}

			PreparedStatement id_check = connection
					.prepareStatement(ConnectInit.queries
							.getProperty("check_identifier_name"));
			id_check.setString(1, ex_code);
			ResultSet id_res = id_check.executeQuery();
			if (id_res.next() == true) //-- If identifier code exist in
			// database.
			{
				int ident_code_int = id_res.getInt(1);
				ex_id_code = ident_code_int;
			} else {
				int ident_code_int2 = insert_identifier_code(ex_code);
				ex_id_code = ident_code_int2;
			}
			psub = connection.prepareStatement(ConnectInit.queries
					.getProperty("update_stk_exchange"));
			psub.setString(1, stk_name);
			psub.setInt(2, cont_id);
			psub.setInt(3, time_zone_id);
			psub.setString(4, stk_ex_code);
			psub.setString(5, start);
			psub.setString(6, stop);
			psub.setInt(7, ex_id_code);
			psub.setInt(8, id_int);
			psub.executeUpdate();
			psub.close();
			id_res.close();
			id_check.close();
		} catch (SQLException sqt) {
			Logging.error("SQL Error :" + sqt);
		}

		catch (Exception et) {
			Logging.error("Error :" + et);
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

	public int insert_identifier_code(String name) {
		PreparedStatement pseq1, psub;
		ResultSet seqno1;
		int curr_seq_id = 0;
		Connection conctn = null;
		try {
			if (conctn == null)
				conctn = con.getdbConnection();

			String seqQuery = "select nextval('identifier_code_id')";
			pseq1 = conctn.prepareStatement(seqQuery);
			seqno1 = pseq1.executeQuery();
			while (seqno1.next()) {
				curr_seq_id = Integer.parseInt(seqno1.getString(1));
				Logging.debug("Present Seq No.id.... " + curr_seq_id);
			}
			psub = conctn.prepareStatement(ConnectInit.queries
					.getProperty("insert_identifier_codes"));
			psub.setInt(1, curr_seq_id);
			psub.setString(2, name);
			psub.setString(3, null);
			psub.executeUpdate();
			psub.close();
			seqno1.close();
			pseq1.close();
			return curr_seq_id;
		} catch (SQLException e) {
			Logging.error("Error in iden insert...:" + e);
		} finally {
			try {
				if (conctn != null)
					conctn.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}

		return 0;
	}

	public void insert_exch_master(String stkname, String cntname_id,
			String tmzone_id, String stkexcode, String start, String stop,
			int identifier_code)
	//,int user_id1)
	{
		int curr_seq2 = 0;
		//int cont_id_int =10;
		int cont_id_int = Integer.parseInt(cntname_id);
		//int time_id_int =10;
		int time_id_int = Integer.parseInt(tmzone_id);
		connection = null;
		try {
			if (connection == null)
				connection = con.getdbConnection();
			try {
				String seqQuery1 = "select nextval('stock_ex_id')";
				PreparedStatement pseq2 = connection
						.prepareStatement(seqQuery1);
				ResultSet seqno2 = pseq2.executeQuery();
				while (seqno2.next()) {
					curr_seq2 = Integer.parseInt(seqno2.getString(1));
				}
				Logging.debug("current seq......" + curr_seq2);
				PreparedStatement psub = connection
						.prepareStatement(ConnectInit.queries
								.getProperty("insert_stk_exchange"));
				psub.setInt(1, curr_seq2);
				psub.setString(2, stkname);
				psub.setInt(3, cont_id_int);
				psub.setInt(4, time_id_int);
				psub.setString(5, stkexcode);
				psub.setString(6, start);
				psub.setString(7, stop);
				psub.setInt(8, identifier_code);
				//psub.setInt(9,user_id1);
				Logging.debug("insertion query exchanemaster new>>>>>>>>"
						+ psub);
				psub.executeUpdate();
				psub.close();
				seqno2.close();
				pseq2.close();
			} catch (SQLException e) {
				Logging.error("Error in iden insert...:" + e);
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

