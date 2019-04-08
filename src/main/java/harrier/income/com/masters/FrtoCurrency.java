/*
 * Created on Jan 21, 2006
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
 * @author Naresh
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class FrtoCurrency extends ActionForm {
	ResultSet rst, rst1;
	Logger Logging = Logger.getLogger(FrtoCurrency.class);
	public static Vector currency_list, currency_list1;

	private Collection selectCurrencyCollection1 = null,
			selectCurrencyCollection2 = null,
			selectExistingConversionCollection = null;

	private String selectCurrency1 = null, defaultVal = null,
			selectCurrency2 = null, selectExistingConversion = null;

	Vector ex_actions;

	Connect con = ConnectInit.getConnect();

	Connection connection = null;

	String name_frto, desc_frto, curr_list1, curr_list2, exchange_rate;

	String name_h, desc_h, update, new1, curr_11, curr_22;

	static String con_flag;

	static int id_val = 0;

	int id_name_check;

	static int curr_2, curr_1;

	String id_name, name_frto_check;

	public Collection getSelectCurrencyCollection1() {
		Vector vec = new Vector();
		vec.add(new LabelValueBean("Not Selected", "0"));
		connection = null;
		try {
			if (connection == null)
				connection = con.getdbConnection();
			try {
				PreparedStatement stmt = connection
						.prepareStatement(ConnectInit.queries
								.getProperty("addcurrency_list"));
				ResultSet rst = stmt.executeQuery();
				while (rst.next()) {
					vec.add(new LabelValueBean(rst.getString(2), rst
							.getString(1)));
				}
				rst.close();
				stmt.close();
				selectCurrencyCollection1 = vec;
				return selectCurrencyCollection1;
			} catch (SQLException e) {
				Logging.error("Error  :" + e.getMessage());
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

		return selectCurrencyCollection1;
	}

	/**
	 * @param selectCollection
	 *            The selectCollection to set.
	 */
	public void setSelectCurrencyCollection1(
			Collection selectCurrencyCollection1) {
		this.selectCurrencyCollection1 = selectCurrencyCollection1;
	}

	public String getSelectCurrency1() {
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
						selectCurrency1 = rst.getString(19);
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
		return selectCurrency1;
	}

	/**
	 * @param select
	 *            The select to set.
	 */
	public void setSelectCurrency1(String selectCurrency1) {
		Logging.debug("Inside setSelectIndex selectIndex = "
				+ selectCurrency1);
		this.selectCurrency1 = selectCurrency1;
	}

	public Collection getSelectCurrencyCollection2() {
		Vector vec = new Vector();
		vec.add(new LabelValueBean("Not Selected", "0"));
		connection = null;
		try {
			if (connection == null)
				connection = con.getdbConnection();
			try {
				PreparedStatement stmt = connection
						.prepareStatement(ConnectInit.queries
								.getProperty("addcurrency_list"));
				ResultSet rst = stmt.executeQuery();
				while (rst.next()) {
					vec.add(new LabelValueBean(rst.getString(2), rst
							.getString(1)));
				}
				rst.close();
				stmt.close();
				selectCurrencyCollection2 = vec;
				return selectCurrencyCollection2;
			} catch (SQLException e) {
				Logging.error("Error  :" + e.getMessage());
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
		return selectCurrencyCollection2;
	}

	/**
	 * @param selectCollection
	 *            The selectCollection to set.
	 */
	public void setSelectCurrencyCollection2(
			Collection selectCurrencyCollection2) {
		this.selectCurrencyCollection2 = selectCurrencyCollection2;
	}

	public String getSelectCurrency2() {
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
						selectCurrency2 = rst.getString(19);
					}
					rst.close();
					stmt.close();
				} catch (SQLException e) {
				//	e.printStackTrace();
					Logging.debug(e);
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
		return selectCurrency2;
	}

	/**
	 * @param select
	 *            The select to set.
	 */
	public void setSelectCurrency2(String selectCurrency2) {
		Logging.debug("Inside setSelectIndex selectIndex = "
				+ selectCurrency2);
		this.selectCurrency2 = selectCurrency2;
	}

	public Collection getSelectExistingConversionCollection() {
		Vector vec = new Vector();
		vec.add(new LabelValueBean("Not Selected", "0"));
		connection = null;
		try {
			if (connection == null)
				connection = con.getdbConnection();
			try {
				PreparedStatement stmt = connection
						.prepareStatement(ConnectInit.queries.getProperty("frto_list"));
				ResultSet rst = stmt.executeQuery();
				while (rst.next()) {
					vec.add(new LabelValueBean(rst.getString(2), rst
							.getString(1)));
				}
				rst.close();
				stmt.close();
				selectExistingConversionCollection = vec;
				return selectExistingConversionCollection;
			} catch (SQLException e) {
				Logging.error("Error  :" + e.getMessage());
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

		return selectExistingConversionCollection;
	}

	/**
	 * @param selectCollection
	 *            The selectCollection to set.
	 */
	public void setSelectExistingConversionCollection(
			Collection selectExistingConversionCollection) {
		this.selectExistingConversionCollection = selectExistingConversionCollection;
	}

	public String getSelectExistingConversion() {
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
						selectExistingConversion = rst.getString(19);
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
		return selectExistingConversion;
	}

	/**
	 * @param select
	 *            The select to set.
	 */
	public void setSelectExistingConversion(String selectExistingConversion) {
		Logging.debug("Inside setSelectIndex selectIndex = "
				+ selectExistingConversion);
		this.selectExistingConversion = selectExistingConversion;
	}

	public int getId_name_check() {
		return id_name_check;
	}

	public void setId_name_check(int id_name_check) {
		this.id_name_check = id_name_check;
	}

	public String getName_frto_check() {
		return name_frto_check;
	}

	public void setName_frto_check(String name_frto_check) {
		this.name_frto_check = name_frto_check;
	}

	/**
	 * @return Returns the exchange_rate.
	 */
	public String getExchange_rate() {
		return exchange_rate;
	}

	/**
	 * @param exchange_rate
	 *            The exchange_rate to set.
	 */
	public void setExchange_rate(String exchange_rate) {
		if (exchange_rate != null) {
			this.exchange_rate = exchange_rate.trim();
		} else
			this.exchange_rate = exchange_rate;
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
		FrtoCurrency.con_flag = con_flag;
	}

	/**
	 * @return Returns the curr_list1.
	 */
	public String getCurr_list1() {
		return curr_list1;
	}

	/**
	 * @param curr_list1
	 *            The curr_list1 to set.
	 */
	public void setCurr_list1(String curr_list1) {
		this.curr_list1 = curr_list1;
	}

	/**
	 * @return Returns the curr_list2.
	 */
	public String getCurr_list2() {
		return curr_list2;
	}

	/**
	 * @param curr_list2
	 *            The curr_list2 to set.
	 */
	public void setCurr_list2(String curr_list2) {
		this.curr_list2 = curr_list2;
	}

	public void reset() {

		selectCurrency1 = null;
		selectCurrency2 = null;
		name_frto = null;
		desc_frto = null;
		exchange_rate = null;
		name_h = null;
		desc_h = null;
		id_name = null;
		id_val = 0;
		update = null;
		new1 = null;
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
	 * @return Returns the curr_1.
	 */
	public int getCurr_1() {
		return curr_1;
	}

	/**
	 * @param curr_1
	 *            The curr_1 to set.
	 */
	public void setCurr_1(int curr_1) {
		this.curr_1 = curr_1;
	}

	/**
	 * @return Returns the curr_2.
	 */
	public int getCurr_2() {
		return curr_2;
	}

	/**
	 * @param curr_2
	 *            The curr_2 to set.
	 */
	public void setCurr_2(int curr_2) {
		this.curr_2 = curr_2;
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
	 * @return Returns the id_name.
	 */
	public String getId_name() {
		return id_name;
	}

	/**
	 * @param id_name
	 *            The id_name to set.
	 */
	public void setId_name(String id_name) {
		this.id_name = id_name;
		get_name_desc(id_name);
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
	 * @return Returns the curr_list1.
	 */
	/*
	 * public String getCurr_list1() { return curr_list1; }
	 *//**
	    * @param curr_list1
	    *            The curr_list1 to set.
	    */
	/*
	 * public void setCurr_list1(String curr_list1) { this.curr_list1 =
	 * curr_list1; }
	 */
	/**
	 * @return Returns the desc_frto.
	 */
	public String getDesc_frto() {
		return desc_frto;
	}

	/**
	 * @param desc_frto
	 *            The desc_frto to set.
	 */
	public void setDesc_frto(String desc_frto) {
		if (desc_frto != null) {
			this.desc_frto = desc_frto.trim();
		} else
			this.desc_frto = desc_frto;
	}

	/**
	 * @return Returns the name_frto.
	 */
	public String getName_frto() {
		return name_frto;
	}

	/**
	 * @param name_frto
	 *            The name_frto to set.
	 */
	public void setName_frto(String name_frto) {
		if (name_frto != null) {
			this.name_frto = name_frto.trim();
		} else
			this.name_frto = name_frto;
	}

	/**
	 * @return Returns the ex_actions.
	 */
	public Vector getEx_actions() {
		setCon_flag(null);
		return ex_actions;
	}

	/**
	 * @param ex_actions
	 *            The ex_actions to set.
	 */
	/*
	 * public void setEx_actions() { ResultSet rset; ex_actions = new Vector();
	 * rset = get_action_List(); int i = 0; try { while (rset.next()) {
	 * ex_actions.add(i, rset.getString(1)); i++;
	 * ex_actions.add(i,(String)rset.getString(2)); i++; } rset.close(); } catch
	 * (Exception sqlexp) { Logging.getError(sqlexp); } }
	 */

	/*
	 * public ResultSet get_action_List() { PreparedStatement pst; ResultSet
	 * rst; connection=null; if(connection==null)
	 * connection=con.getdbConnection(); try { pst =
	 * connection.prepareStatement(con.queries.getProperty("frto_list")); rst =
	 * pst.executeQuery(); rst.close(); pst.close(); return rst; }catch
	 * (Exception e) { Logging.getError("Error :"+e); } finally{
	 * try{if(connection!=null) connection.close(); }catch(Exception ee){
	 * Logging.getError(" Error : Unable to close Connection
	 * "+ee.getMessage()); } } return null; }
	 */
	/**
	 * @return Returns the desc.
	 */

	/**
	 * @return Returns the currency_list.
	 */
	public Vector getCurrency_list() {

		return currency_list;
	}

	/**
	 * @param currency_list
	 *            The currency_list to set.
	 */
	public void setCurrency_list() {
	//	app.Connect con = new app.Connect();
	//	con = null;
		con = ConnectInit.getConnect();
		if (Connect.con == null)
		{
		//	con.getdbConnection();
			con = ConnectInit.getConnect();
		}
		currency_list = new Vector();
		rst = getCourrencyList();
		int i = 0;
		try {
			while (rst.next()) {
				currency_list.add(i, rst.getString(1));
				i++;
				currency_list.add(i, (String) rst.getString(2));
				i++;
			}
			rst.close();
		} catch (SQLException sqlexp) {
			Logging.error(sqlexp+"");
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

	public ResultSet getCourrencyList() {
		PreparedStatement pst;
		ResultSet rset;
		connection = null;
		try {
			if (connection == null)
				connection = con.getdbConnection();
			try {
				pst = connection.prepareStatement(ConnectInit.queries
						.getProperty("addcurrency_list"));
				rset = pst.executeQuery();
				rset.close();
				pst.close();
				return rset;
			} catch (Exception e) {
				Logging.error("Error :" + e);
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
		return rst1;
	}

	public Vector getCurrency_list1() {
		setCon_flag(null);
		return currency_list1;
	}

	/**
	 * @param currency_list
	 *            The currency_list to set.
	 */
	public void setCurrency_list1() {
	//	app.Connect con = new app.Connect();
		con = ConnectInit.getConnect();
		connection = null;
		if (Connect.con == null)
			connection = con.getdbConnection();
		currency_list = new Vector();
		rst = getCourrencyList();
		int i = 0;
		try {
			while (rst.next()) {
				currency_list.add(i, rst.getString(1));
				i++;
				currency_list.add(i, (String) rst.getString(2));
				i++;
			}
			rst.close();
		} catch (SQLException sqlexp) {
			Logging.error(sqlexp+"");
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

	public void get_name_desc(String id) {
		PreparedStatement pst;
		ResultSet rst;

		connection = null;

		try {
			int idt_code = Integer.parseInt(id);

			if (connection == null)
				connection = con.getdbConnection();

			pst = connection.prepareStatement(ConnectInit.queries
					.getProperty("get_frto_desc"));
			pst.setInt(1, idt_code);
			rst = pst.executeQuery();
			while (rst.next()) {
				id_val = rst.getInt(1);
				setId_name_check(id_val);
				name_h = rst.getString(2);
				setName_h(name_h);
				setName_frto(name_h);
				setName_frto_check(name_h);
				curr_1 = rst.getInt(3);
				curr_11 = "" + curr_1;
				setSelectCurrency1(curr_11);
				setCurr_1(curr_1);
				curr_2 = rst.getInt(4);
				curr_22 = "" + curr_2;
				setSelectCurrency2(curr_22);
				setCurr_2(curr_2);
				desc_h = rst.getString(5);
				setDesc_h(desc_h);
				setDesc_frto(desc_h);
			}
			rst.close();
			pst.close();
		} catch (Exception e) {
			Logging.error("Error :" + e);
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
		FrtoCurrencyAction act = new FrtoCurrencyAction();
		if (newvalue != null) {
			ActionErrors errors = new ActionErrors();
			boolean flag = true, flag1 = true;
			try {
				int check = act.checkData(name_frto);
				if (check == 1) {
					errors.add("DuplicateEntry", new ActionError(
							"Error.message.DuplicateEntry"));
				}
			} catch (Exception e) {
				errors.add(null, new ActionError("Error.message.msg"));
				Logging.error("Error in Validation ");
			}
			return errors;
		}
		if (updatevalue != null) {
			ActionErrors errors = new ActionErrors();
			try {
				int id_val = 0;
				id_val =FrtoCurrency.id_val;
				//id_val = getId_name_check(); //Changed on 05-08-06
				//getId_name_check();
				if (id_val == 0) {
					setUpdate(null);
					setId_name_check(0);
					setNew1(null);
					errors.add("DuplicateEntry", new ActionError(
							"Error.message.selectfromlist"));
				}
				if (!getName_frto().equalsIgnoreCase(getName_frto_check())) {
					int check = checkData(getName_frto().trim());
					if (check == 1) {
						setUpdate(null);
						setId_name(null);
						errors.add("DuplicateEntry", new ActionError(
								"Error.message.DuplicateEntry"));
					}
				}
			} catch (Exception e) {
				errors.add(null, new ActionError("Error.message.msg"));
				Logging.error("Error in Validation ");
			}
			return errors;
		}
		return null;
	}

	public int checkData(String name) {
		PreparedStatement checkcon;
		ResultSet rs1;
		int ans = 0;
		String nm1 = null;
		connection = null;
		if (connection == null)
			connection = con.getdbConnection();
		try {
			checkcon = connection.prepareStatement(ConnectInit.queries
					.getProperty("check_frto_currency"));
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
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		return ans;
	}

}