/*
 * Created on Mar 19, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.masters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * @author naresh
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ExchangeHolidaysForm extends ActionForm {
	Logger Logging = Logger.getLogger(ExchangeHolidaysForm.class);
	Connect con = ConnectInit.getConnect();

	Connection connection = null;

	Vector exch_list_vector;

	private Collection selectExchangeCollection = null;

	public String selectExchange = null, selectCountry = null,
			selectContinent = null, defaultVal = null;

	private Collection selectExistingHolidaysCollection = null;

	public String selectExistingHolidays = null;

	static Vector holiday_list_vector;

	String exchange_list, exchange_id, exchange_l;

	String name_h, desc_h, date_h, update, new1;

	static String stk_ex_list;

	String name, desc, date_sel;

	static int holi_id;

	int exch_id;

	static int holidayID_h=0, exchange_h=0, temp_var=0;

	static String flag=null, update_flag=null, update_flag_copy=null, con_flag;

	public Collection getSelectExchangeCollection() {
		Vector vec = new Vector();
		vec.add(new LabelValueBean("Not Selected", "0"));
		connection = null;
		try {
			if (connection == null)
				connection = con.getdbConnection();
			try {
				PreparedStatement stmt = connection
						.prepareStatement(ConnectInit.queries
								.getProperty("stock_exch_list"));
				ResultSet rst = stmt.executeQuery();
				while (rst.next()) {
					vec.add(new LabelValueBean(rst.getString(2), rst
							.getString(1)));
				}
				rst.close();
				stmt.close();
				selectExchangeCollection = vec;
				return selectExchangeCollection;
			} catch (SQLException e) {
				Logging.error(" Error : " + e.getMessage());
			//	e.printStackTrace();
			}
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}

		return selectExchangeCollection;
	}

	/**
	 * @param selectCollection
	 *            The selectCollection to set.
	 */

	public void setSelectExchangeCollection(Collection selectExchangeCollection) {
		this.selectExchangeCollection = selectExchangeCollection;
	}

	public String getSelectExchange() {
		Connection conectn = null;
		try {
			if (conectn == null)
				conectn = con.getdbConnection();
			if (defaultVal != null && defaultVal.equals("yes")) {
				try {
					PreparedStatement stmt = conectn
							.prepareStatement(ConnectInit.queries
									.getProperty("select_system_config"));
					ResultSet rst = stmt.executeQuery();
					while (rst.next()) {
						selectExchange = rst.getString(18);
					}
					rst.close();
					stmt.close();
				} catch (SQLException e) {
					Logging.error("Error  :" + e.getMessage());
				//	e.printStackTrace();
				}
			}
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (conectn != null)
					conectn.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		return selectExchange;
	}

	/**
	 * @param select
	 *            The select to set.
	 */
	public void setSelectExchange(String selectExchange) {
		Logging.debug("Inside setSelectIndex selectIndex = "
				+ selectExchange);
		Logging.debug("EExchange iss:" + selectExchange);
		this.selectExchange = selectExchange;
		Logging.debug("EExchange iss:" + selectExchange);
	}

	public Collection getSelectExistingHolidaysCollection() {
		Vector vec = new Vector();
		vec.add(new LabelValueBean("Not Selected", "0"));
		connection = null;

		try {
			if (connection == null)
				connection = con.getdbConnection();

			PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries
					.getProperty("exch_holiday_list"));
			//String sRName = selectExchange.split("e")[1];
			String sRName = getSelectExchange();
			//int num = Integer.parseInt(sRName);
			Logging.debug("zxzxzxzxzxzxzxzxzxzxz" + sRName);
			//	int num = 15;
			stmt.setString(1, sRName);
			//Logging.getDebug(stmt);
			ResultSet rst = stmt.executeQuery();
			while (rst.next()) {
				if (rst.getString(2) == null || rst.getString(2) == "") {
					vec.add(new LabelValueBean("0", rst.getString(1)));
					Logging.debug("Holiday id..........."
							+ rst.getString(1));
				} else {
					vec.add(new LabelValueBean(rst.getString(2), rst
							.getString(1)));
				}
			}
			rst.close();
			stmt.close();
			selectExistingHolidaysCollection = vec;
			return selectExistingHolidaysCollection;

		} catch (SQLException e) {
			Logging.error("Error  :" + e.getMessage());
		//	e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		return selectExistingHolidaysCollection;
	}

	/**
	 * @param selectCollection
	 *            The selectCollection to set.
	 */

	public void setSelectExistingHolidaysCollection(
			Collection selectExistingHolidaysCollection) {
		this.selectExistingHolidaysCollection = selectExistingHolidaysCollection;
	}

	public String getSelectExistingHolidays() {
		connection = null;
		try {
			if (connection == null)
				connection = con.getdbConnection();
			if (defaultVal != null && defaultVal.equals("yes")) {
				try {
					PreparedStatement stmt = connection
							.prepareStatement(ConnectInit.queries
									.getProperty("select_system_config"));
					ResultSet rst = stmt.executeQuery();
					while (rst.next()) {
						selectExistingHolidays = rst.getString(18);
					}
					rst.close();
					stmt.close();
				} catch (SQLException e) {
					Logging.error("Error  :" + e.getMessage());
				//	e.printStackTrace();
				}
			}
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		return selectExistingHolidays;
	}

	/**
	 * @param select
	 *            The select to set.
	 */
	public void setSelectExistingHolidays(String selectExistingHolidays) {
		Logging.debug("Inside setSelectIndex selectIndex = "
				+ selectExistingHolidays);
		this.selectExistingHolidays = selectExistingHolidays;
	}

	/**
	 * @return Returns the con_flag.
	 */
	public static String getCon_flag() {
		return con_flag;
	}

	/**
	 * @param con_flag
	 *            The con_flag to set.
	 */
	public static void setCon_flag(String con_flag) {
		ExchangeHolidaysForm.con_flag = con_flag;
	}

	/**
	 * @return Returns the update_flag.
	 */
	public String getUpdate_flag() {
		return update_flag;
	}

	/**
	 * @param update_flag
	 *            The update_flag to set.
	 */
	public void setUpdate_flag(String update_flag) {
		ExchangeHolidaysForm.update_flag = update_flag;
	}

	/**
	 * @return Returns the update_flag_copy.
	 */
	public String getUpdate_flag_copy() {
		return update_flag_copy;
	}

	/**
	 * @param update_flag_copy
	 *            The update_flag_copy to set.
	 */
	public void setUpdate_flag_copy(String update_flag_copy) {
		ExchangeHolidaysForm.update_flag_copy = update_flag_copy;
	}

	/**
	 * @return Returns the stk_ex_list.
	 */
	public static String getStk_ex_list() {
		return stk_ex_list;
	}

	/**
	 * @param stk_ex_list
	 *            The stk_ex_list to set.
	 */
	public static void setStk_ex_list(String stk_ex_list) {
		ExchangeHolidaysForm.stk_ex_list = stk_ex_list;
	}

	/**
	 * @return Returns the date_sel.
	 */
	public String getDate_sel() {
		return date_sel;
	}

	/**
	 * @param date_sel
	 *            The date_sel to set.
	 */
	public void setDate_sel(String date_sel) {
		if (date_sel != null) {
			this.date_sel = date_sel.trim();
		} else
			this.date_sel = date_sel;
	}

	/**
	 * @return Returns the desc.
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc
	 *            The desc to set.
	 */
	public void setDesc(String desc) {
		if (desc != null) {
			this.desc = desc.trim();
		} else
			this.desc = desc;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name) {
		if (name != null) {
			this.name = name.trim();
		} else
			this.name = name;
	}

	/**
	 * @return Returns the exch_id.
	 */
	public int getExch_id() {
		return exch_id;
	}

	/**
	 * @param exch_id
	 *            The exch_id to set.
	 */
	public void setExch_id(int exch_id) {
		this.exch_id = exch_id;
	}

	/**
	 * @return Returns the holi_id.
	 */
	public static int getHoli_id() {
		return holi_id;
	}

	/**
	 * @param exch_id
	 *            The exch_id to set.
	 */
	public static void setHoli_id(int holi_id) {
		ExchangeHolidaysForm.holi_id = holi_id;
	}

	/**
	 * @return Returns the new1.
	 */
	public String getNew1() {
		return new1;
	}

	/**
	 * @param new1
	 *            The new1 to set.
	 */
	public void setNew1(String new1) {
		this.new1 = new1;
	}

	/**
	 * @return Returns the update.
	 */
	public String getUpdate() {
		return update;
	}

	/**
	 * @param update
	 *            The update to set.
	 */
	public void setUpdate(String update) {
		this.update = update;
	}

	/**
	 * @return Returns the date_h.
	 */
	public String getDate_h() {
		return date_h;
	}

	/**
	 * @param date_h
	 *            The date_h to set.
	 */
	public void setDate_h(String date_h) {
		this.date_h = date_h;
	}

	/**
	 * @return Returns the desc_h.
	 */
	public String getDesc_h() {
		return desc_h;
	}

	/**
	 * @param desc_h
	 *            The desc_h to set.
	 */
	public void setDesc_h(String desc_h) {
		this.desc_h = desc_h;
	}

	/**
	 * @return Returns the name_h.
	 */
	public String getName_h() {
		return name_h;
	}

	/**
	 * @param name_h
	 *            The name_h to set.
	 */
	public void setName_h(String name_h) {
		this.name_h = name_h;
	}

	/**
	 * @return Returns the holidayID_h.
	 */
	public int getHolidayID_h() {
		return holidayID_h;
	}

	/**
	 * @param holidayID_h
	 *            The holidayID_h to set.
	 */
	public void setHolidayID_h(int holidayID_h) {
		ExchangeHolidaysForm.holidayID_h = holidayID_h;
		Logging.debug("PPPPPPPPPPPPPPPPPPPPPPholidayID_h" + holidayID_h);
		get_name_desc(holidayID_h);
	}

	/**
	 * @return Returns the flag.
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            The flag to set.
	 */
	public void setFlag(String flag) {
		ExchangeHolidaysForm.flag = flag;
	}

	/**
	 * @return Returns the temp_var.
	 */
	public static int getTemp_var() {
		return temp_var;
	}

	/**
	 * @param temp_var
	 *            The temp_var to set.
	 */
	public static void setTemp_var(int temp_var) {
		ExchangeHolidaysForm.temp_var = temp_var;
	}

	/**
	 * @return Returns the exchange_l.
	 */
	public String getExchange_l() {
		return exchange_l;
	}

	/**
	 * @param exchange_l
	 *            The exchange_l to set.
	 */
	public void setExchange_l(String exchange_l) {
		this.exchange_l = exchange_l;
	}

	/**
	 * @return Returns the exchange_h.
	 */
	public int getExchange_h() {
		return exchange_h;
	}

	/**
	 * @param exchange_h
	 *            The exchange_h to set.
	 */
	public void setExchange_h(int exchange_h) {
		if (exchange_h != 0) {
			setTemp_var(exchange_h);
			ExchangeHolidaysForm.exchange_h = exchange_h;
		}
	}

	/**
	 * @return Returns the exchange_id.
	 */
	public String getExchange_id() {
		return exchange_id;
	}

	/**
	 * @param exchange_id
	 *            The exchange_id to set.
	 */
	public void setExchange_id(String exchange_id) {
		this.exchange_id = exchange_id;
	}

	/**
	 * @return Returns the exchange_list.
	 */
	public String getExchange_list() {
		return exchange_list;
	}

	/**
	 * @param exchange_list
	 *            The exchange_list to set.
	 */
	public void setExchange_list(String exchange_list) {
		setStk_ex_list(exchange_list);
		this.exchange_list = exchange_list;
	}

	/*
	 * public Vector getExch_list_vector() { setExch_id(0);
	 * setExch_list_vector(); setCon_flag(null); return exch_list_vector; }
	 */
	/**
	 * @param exch_list
	 *            The exch_list to set.
	 */
	/*
	 * public void setExch_list_vector() { ResultSet rset; exch_list_vector =
	 * new Vector(); rset = get_exch_List(); int i = 0; try { while
	 * (rset.next()) { exch_list_vector.add(i, rset.getString(1)); i++;
	 * exch_list_vector.add(i, rset.getString(2)); i++; } rset.close(); } catch
	 * (Exception sqlexp) { Logging.getError("in setexch :"+sqlexp); } }
	 */
	/*
	 * public ResultSet get_exch_List() { PreparedStatement pst; ResultSet rst;
	 * if(connection==null) { connection=con.getdbConnection(); } try { pst =
	 * connection.prepareStatement(con.queries.getProperty("stock_exch_list"));
	 * rst = pst.executeQuery(); rst.close(); pst.close(); return rst; }catch
	 * (Exception e) { Logging.getError("Error in get_time_list:"+e); return
	 * null; } finally{ try{if(connection!=null) connection.close();
	 * }catch(Exception ee){ Logging.getError(" Error : Unable to close
	 * Connection "+ee.getMessage()); } } }
	 */

	public Vector getHoliday_list_vector() {
		return holiday_list_vector;
	}

	/**
	 * @param exch_list
	 *            The exch_list to set.
	 */
	public void setHoliday_list_vector(String id) {
		ResultSet rset;
		Connection connection = null;
		holiday_list_vector = new Vector();

		int i = 0;
		try {
			if (connection == null) {
				connection = con.getdbConnection();
			}
			rset = get_holiday_List(connection, id);
			setCon_flag(null);
			while (rset.next()) {
				holiday_list_vector.add(i, rset.getString(1));
				i++;
				holiday_list_vector.add(i, rset.getString(2));
				i++;
			}
			rset.close();
		} catch (Exception sqlexp) {
			Logging.error("in setholiday  :" + sqlexp);
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

	public ResultSet get_holiday_List(Connection connection, String id) {
		PreparedStatement pst;
		ResultSet rst;
		int id_int = Integer.parseInt(id);

		try {
			pst = connection.prepareStatement(ConnectInit.queries
					.getProperty("exch_holiday_list"));
			pst.setInt(1, id_int);
			rst = pst.executeQuery();
			return rst;
		} catch (Exception e) {
			Logging.error("Error in get_time_list:" + e);
			return null;
		}

	}

	public void get_name_desc(int id) {
		PreparedStatement pst;
		ResultSet rst;
		int idt_code = id;
		connection = null;
		setNew1(null);
		if (connection == null) {
			connection = con.getdbConnection();
		}
		try {
			pst = connection.prepareStatement(ConnectInit.queries
					.getProperty("get_holiday_desc"));
			pst.setInt(1, idt_code);
			rst = pst.executeQuery();
			while (rst.next()) {
				holi_id = rst.getInt(1);
				setHoli_id(holi_id);
				name_h = rst.getString(2);
				setTemp_var(exch_id);
				setName(name_h);
				setExchange_id(name_h); //ok ok
				setName_h(name_h);
				date_h = rst.getString(3);
				setDate_sel(date_h);
				setDate_h(date_h);
				desc_h = rst.getString(4);
				setDesc(desc_h);
				setDesc_h(desc_h);
				exch_id = rst.getInt(5);
				setExch_id(exch_id);
				Logging.debug("Exchangeid isss:" + exch_id);
				Logging
						.debug("Exchangeid isssgetExch_id():" + getExch_id());

			}
			rst.close();
			pst.close();
		} catch (Exception e) {
			Logging.error("Error get_desc:" + e);
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

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		String newvalue = getNew1();
		String updatevalue = getUpdate();
		if (newvalue != null && updatevalue==null) {
			ActionErrors errors = new ActionErrors();
			boolean flag = true, flag1 = true;
			try {
				Logging.debug("name is ......." + name);
				int check = checkData(name);
				if (check == 1) {
					setNew1(null);

					setHoli_id(0);
					//setExch_id(0);
					errors.add("DuplicateEntry", new ActionError(
							"Error.message.DuplicateEntry"));
				}
			} catch (Exception e) {
				errors.add(null, new ActionError("Error.message.msg"));
			}
			return errors;
		}
		if (newvalue != null && updatevalue!=null) {
			ActionErrors errors = new ActionErrors();
			setUpdate(null);
			setNew1(null);
			errors.add("DuplicateEntry", new ActionError(
			"Error.message.selectfromlist"));
			return errors;
		}
		if (updatevalue != null && newvalue==null) {
			Logging
					.debug("Holiday id1:" + ExchangeHolidaysForm.holidayID_h);
			Logging.debug("Holiday id1:" + ExchangeHolidaysForm.holi_id);
			//if (ExchangeHolidaysForm.holidayID_h != 0) 
			{
				
				ActionErrors errors = new ActionErrors();
				boolean flag = true, flag1 = true;
				CountriesAction act = new CountriesAction();
				try {
					int idname_t = ExchangeHolidaysForm.holi_id;
					if (idname_t == 0) {
						setUpdate(null);
						setHoli_id(0);
						//setExch_id(0);
						errors.add("DuplicateEntry", new ActionError(
								"Error.message.selectfromlist"));
					}
					if (!getName().equalsIgnoreCase(getName_h())) {
						try {
							int check = checkData(name);
							if (check == 1) {
								errors.add("DuplicateEntry", new ActionError(
										"Error.message.DuplicateEntry"));
								setNew1(null);
								setUpdate(null);
								setHoli_id(0);
								//setExch_id(0);
							}
						} catch (Exception e) {
							Logging.error("Error  :" + e.getMessage());
						}
					}
				} catch (Exception e) {
					errors.add(null, new ActionError("Error.message.msg"));
				}
				return errors;
			}
		}
		return null;
	}

	public int checkData(String name) {
		int ans = 0;
		String nm1 = null;
		PreparedStatement pst_chk;
		ResultSet rst_chk;
		connection = null;
		if (connection == null) {
			connection = con.getdbConnection();
		}
		Logging.debug("Inside checkData . " + name);
		try {
			pst_chk = connection.prepareStatement(ConnectInit.queries
					.getProperty("chk_holiday_master"));
			pst_chk.setString(1, name.trim());
			pst_chk.setString(2, getSelectExchange());
			rst_chk = pst_chk.executeQuery();
			while (rst_chk.next()) {
				nm1 = rst_chk.getString(1);
				Logging.debug("Cou Name is . " + nm1);
			}
			if (nm1 != null && nm1.equalsIgnoreCase(name)) {
				ans = 1;
			}
			rst_chk.close();
			pst_chk.close();
			
			
		} catch (Exception e) {
			Logging.error("Error check() :" + e);
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		return ans;
	}

	public void reset() {
		Logging.debug(" in reset ");
		this.selectExchange = null;
		holidayID_h=0;
		exchange_h=0;
		update_flag=null;
		update_flag_copy=null;
		this.selectExchangeCollection=null;
		this.selectExistingHolidays = null;
		this.date_sel = null;
		this.desc = null;
		new1 = null;
		update = null;
	}
}