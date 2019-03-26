/*
 * Created on Jun 17, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author neha
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package org.jfree.chart.demo.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import app.AcessControl;
import app.Connect;
import app.IndexCalculatorCollection;
import app.ListTypeClass1;
import app.LogonForm;
import app.StockResult;

import com.harrier.initializeation.ConnectInit;


public class FixedIncomeComposeIndex {
	static Logger Logging = Logger.getLogger(FixedIncomeComposeIndex.class);
	Vector vector_indexlist, index_details, vector_exchangelist,
			vector_stock_exchange, tree_data_index, vector_indtable,
			vector_highlowtable, vector_stocklist, vector_compareOHLC,
			vector_latestdivisor, vector_marketstatus,
			vector_marketstatusdatewise, vector_companywiseweightage,
			vector_Listedcompany, vector_delistedcompanies,
			vector_stockcotriIndexchange, vector_remStockid,
			vector_capitalchangetouniv, vector_companylist,
			vector_shareholdingPattern, vector_stockchangeTypelist,
			vector_stockchangeDetail, vector_tabledatacompute,
			vector_indweighttable, index_performance;

	Vector v1, vector_stock_performance1, vector_stock_performance,
			vector_index_rv, vector_index_rv1, vector_ftdate, vector_StockList,
			vector_stocklistexcwise, vector_ExchangeList,
			vector_IdentifierCodeBelogsTo, vector_stockDivident,
			vector_tradedvol;

	String tdate, fdate, param;

	java.sql.ResultSet rst1, rst;

	static double total = 0.00;

	public static String filename = null;

	public static String graphURL = null;

	public static boolean showcolumn = false;

	public Vector vector_tabledata;

	Object obj[][], obj1[][];

	static Connect con1 = ConnectInit.getConnect();

	/**
	 * get the stock name if stock id is passed as a parameter.
	 * 
	 * @param id
	 * @return
	 */
	public String get_stock_name(int id) {
		String ind_name = null;
		Connect connect = ConnectInit.getConnect();
		//app.Connect con=new app.Connect();
		Connection con = null;
		PreparedStatement pst;
		ResultSet rst;
		if (con == null) {
			con = connect. getdbConnection();
		}
		try {
			pst = Connect.con.prepareStatement(ConnectInit.queries
					.getProperty("fixed_income_stock_name_select"));
			pst.setInt(1, id);
			rst = pst.executeQuery();
			while (rst.next()) {
				ind_name = rst.getString(1);
			}
		} catch (Exception e) {
		}

		return ind_name;
	}

	public Vector getStockExwise(String id) {
		Vector vec = new Vector();
		Logging.debug("Inside setVector_indexList");
	//	Connect con1 = new app.Connect();
		Connect con1 = ConnectInit.getConnect();
		Connection con = null;
		ResultSet rst = null;
		PreparedStatement pst = null;
		try {
			if (con == null)
				con = con1.getdbConnection();
			con.rollback();
			con.setAutoCommit(true);
			pst = con.prepareStatement(ConnectInit.queries
					.getProperty("beta_stock_list"));
			pst.setString(1, id);
			int i = 0;

			rst = pst.executeQuery();
			while (rst.next()) {
				if (rst.getString(1) == null) {
					vec.add(i, " ");
				} else {
					vec.add(i, rst.getString(1));
					Logging.debug("seeeeeeeeeeeeeeeeeeee"
							+ rst.getString(1));
				}
				i++;
				if (rst.getString(2) == null) {
					vec.add(i, " ");
				} else {
					vec.add(i, rst.getString(2));
				}
				i++;
				Logging.debug("see222222222222222eeee" + rst.getString(2));
			}
			Logging.debug("The Vectooooor is" + vec);
			if (rst != null)
				rst.close();
		} catch (SQLException sqlexp) {
			Logging.error("Error : " + sqlexp.getMessage());
		} finally {
			try {
				if (rst != null)
					rst.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();

			} catch (SQLException ee) {
				try {
					if (rst != null)
						rst.close();
					if (pst != null)
						pst.close();
					if (con != null)
						con.close();

				} catch (SQLException ex) {
					Logging.error("SQL Error :" + ee.getMessage());
				}
				Logging.error("SQL Error :" + ee.getMessage());
			}
		}
		return vec;
	}

	public static String FormatDateMon1(String basedate) {
		basedate = basedate.trim();
		if (basedate.length() != 10)
			return basedate;
		String date = basedate.trim().substring(0, 2);
		int month = Integer.parseInt(basedate.trim().substring(3, 5));
		String year = basedate.trim().substring(6, 10);
		Logging.debug("date is " + date + " month is " + month
				+ " year is " + year);
		String mon = null;
		switch (month) {
		case 1:
			mon = "JAN";
			break;
		case 2:
			mon = "FEB";
			break;
		case 3:
			mon = "MAR";
			break;
		case 4:
			mon = "APR";
			break;
		case 5:
			mon = "MAY";
			break;
		case 6:
			mon = "JUN";
			break;
		case 7:
			mon = "JUL";
			break;
		case 8:
			mon = "AUG";
			break;
		case 9:
			mon = "SEP";
			break;
		case 10:
			mon = "OCT";
			break;
		case 11:
			mon = "NOV";
			break;
		case 12:
			mon = "DEC";
			break;
		}
		String ret_date = date + "-" + mon + "-" + year;
		Logging.debug(" date to be returned is " + ret_date);
		return ret_date;
	}

	public Vector getIndexscripname(String id, String exchange,
			HttpServletRequest request) {

		Vector vec = new Vector();
		Logging.debug("Inside setVector_indexList");
//		app.Connect con1 = new app.Connect();
		Connect con1 = ConnectInit.getConnect();
		Connection con = null;
		ResultSet rst = null;
		PreparedStatement pst = null;
		try {
			if (con == null)
				con = con1.getdbConnection();
			con.rollback();
			con.setAutoCommit(true);
			String select = request.getParameter("check");
			int i = 0;
			if (select != null && select.equals("checked")) {
				pst = con.prepareStatement(ConnectInit.queries
						.getProperty("index_name_scrip_all"));
				pst.setString(1, exchange);
				rst = pst.executeQuery();
			} else {
				String str = ConnectInit.queries.getProperty("index_name_scrip");
				String str2 = str.replaceAll("XYZ", id);
				Logging.debug(str2);
				pst = con.prepareStatement(str2);
				rst = pst.executeQuery();
			}
			while (rst.next()) {

				if (rst.getString(1) == null) {
					vec.add(i, " ");
				} else {
					vec.add(i, rst.getString(1));
				}
				i++;

				if (rst.getString(2) == null) {
					vec.add(i, " ");
				} else {
					vec.add(i, rst.getString(2));
				}
				i++;

				if (rst.getString(3) == null) {
					vec.add(i, " ");
				} else {
					vec.add(i, rst.getString(3));
				}
				i++;
				if (rst.getString(4) == null) {
					vec.add(i, " ");
				} else {
					vec.add(i, rst.getString(4));
				}
				i++;

			}
			Logging.debug("Vector check.." + vec);
		} catch (SQLException sqlexp) {
			Logging.error("Error : " + sqlexp.getMessage());
		} finally {
			try {
				if (rst != null)
					rst.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();

			} catch (SQLException ee) {
				try {
					if (rst != null)
						rst.close();
					if (pst != null)
						pst.close();
					if (con != null)
						con.close();

				} catch (SQLException ex) {
					Logging.error("SQL Error :" + ee.getMessage());
				}
				Logging.error("SQL Error :" + ee.getMessage());
			}
		}
		return vec;
	}

	public Vector getInclusionExclusionVec(String indexid, String fodate,
			String todate, HttpSession session) {
		//String index1,index2;
		//		String index1,index2;
//		org.jfree.chart.demo.servlet.AdjustDecimal ad = new org.jfree.chart.demo.servlet.AdjustDecimal();
//		app.Connect con1 = new Connect();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		Connect con1 = ConnectInit.getConnect();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		Vector vec = new Vector();
		Vector dec = new Vector();
		if (((fodate != null || fodate.trim().equals("")) && fodate.length() != 0)
				&& ((todate != null || todate.trim().equals("")) && todate
						.length() != 0)) {
			fodate = ComposeIndex.FormatDateMon(fodate);
			todate = ComposeIndex.FormatDateMon(todate);
		}
		int i = 0;
		int y = 0;

		try {
			if (con == null)
				con = con1.getdbConnection();
			con.rollback();
			con.setAutoCommit(true);
			String inc = ConnectInit.queries.getProperty("new_inclusion_exclusion");
			String str2 = inc.replaceAll("XYZ", indexid);
			pst = con.prepareStatement(str2);
			//pst.setString(1, indexid);
			pst.setString(1, fodate);
			pst.setString(2, todate);
			rst = pst.executeQuery();

			while (rst.next()) {

				if (rst.getString(1) == null) {
					vec.add(i, "--");
					dec.add(y, "-- ");
				} else {
					vec.add(i, rst.getString(1));
					dec.add(y, rst.getString(1));

				}
				i++;
				y++;

				if (rst.getString(2) == null) {
					vec.add(i, "-- ");
					dec.add(y, "-- ");

				} else {
					vec.add(i, rst.getString(2));
					dec.add(y, rst.getString(2));
				}
				i++;

				if (rst.getString(3) == null) {
					vec.add(i, " ");

				} else {
					vec.add(i, rst.getString(3));

				}
				i++;
				y++;
				if (rst.getString(4) == null) {
					vec.add(i, " ");
					dec.add(y, " ");

				} else {
					vec.add(i, rst.getString(4));
					dec.add(y, rst.getString(4));
				}
				i++;
				y++;
				if (rst.getString(5) == null) {
					vec.add(i, "-- ");
					dec.add(y, "--");
				} else {
					double omcv = (double) rst.getDouble(5);
					omcv = omcv / 1000000.0;
					String strmcv = new Double(omcv).toString();
					vec.add(i, ad.indexcompose(strmcv));
					dec.add(y, ad.indexcompose4digit(strmcv));
				}
				i++;
				y++;

				if (rst.getString(6) == null) {
					vec.add(i, "--");
					dec.add(y, "--");
				} else {
					vec.add(i, rst.getString(6));
					dec.add(y, rst.getString(6));
				}
				i++;
				y++;
				if (rst.getString(7) == null) {
					vec.add(i, "--");
					dec.add(y, "--");
				} else {
					vec.add(i, rst.getString(7));
					dec.add(y, rst.getString(7));
				}
				i++;
				y++;
			}
			Object ci2 = null;
			session.setAttribute("ci2", new Vector(dec));
			Logging.debug("VVVVEEECtttor is" + vec);
			Logging.debug("VVVVEEECtttor is" + dec);
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error : " + sqlexp.getMessage());
		} finally {
			try {
				if (rst != null)
					rst.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();

			} catch (SQLException ex) {
				Logging.error("SQL Error :" + ex.getMessage());
			}
		}
		return vec;
	}

	public static String FormatDateMon(String basedate) {
		basedate = basedate.trim();
		if (basedate.length() != 10)
			return basedate;
		String date = basedate.trim().substring(0, 2);
		int month = Integer.parseInt(basedate.trim().substring(3, 5));
		String year = basedate.trim().substring(6, 10);
		Logging.debug("date is " + date + " month is " + month
				+ " year is " + year);
		String mon = null;
		switch (month) {
		case 1:
			mon = "JAN";
			break;
		case 2:
			mon = "FEB";
			break;
		case 3:
			mon = "MAR";
			break;
		case 4:
			mon = "APR";
			break;
		case 5:
			mon = "MAY";
			break;
		case 6:
			mon = "JUN";
			break;
		case 7:
			mon = "JUL";
			break;
		case 8:
			mon = "AUG";
			break;
		case 9:
			mon = "SEP";
			break;
		case 10:
			mon = "OCT";
			break;
		case 11:
			mon = "NOV";
			break;
		case 12:
			mon = "DEC";
			break;
		}
		String ret_date = date + "-" + mon + "-" + year;
		Logging.debug(" date to be returned is " + ret_date);
		return ret_date;
	}

	/**
	 * @return Returns the vector_indexlist.
	 */
	public Vector getVector_indexlist() {
		return vector_indexlist;
	}

	/**
	 * @param vector_indexlist
	 *            The vector_indexlist to set.
	 */
	public void setVector_indexlist() {
		Logging.debug("Inside setVector_indexList");
//		app.Connect con = new app.Connect();
		Connect con = ConnectInit.getConnect();
		//if(Connect.con==null)
		//{
		//Logging.debug("Inside con if loop of indexlist vector");
		con.getConnection();
		//}
		//Logging.debug("Inside setVector_indexList after connection");
		vector_indexlist = new Vector();
		rst = con.getClientList("index_list");
		Logging.debug("In SetVector_indexlit " + rst);

		int i = 0;
		obj = new Object[1][2];
		try {

			while (rst.next()) {
				vector_indexlist.add(i, rst.getString(1));
				i++;
				vector_indexlist.add(i, rst.getString(2));
				i++;
			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :" + sqlexp.getMessage());
		}
	}

	/**
	 * @param vector_indexlist
	 *            The vector_indexlist to set.
	 */
	public void setVector_indexlist(String chk) {
		Logging.debug("Inside setVector_indexList");
	//	app.Connect con = new app.Connect();
		
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			//Logging.debug("Inside con if loop of indexlist vector");
			con.getConnection();
		}
		//Logging.debug("Inside setVector_indexList after connection");
		vector_indexlist = new Vector();
		if (chk != null && chk.equals("checked")) {
			rst = con.getClientList("index_list");
		} else {
			rst = con.getClientList("index_list_without_child");
		}
		Logging.debug("In SetVector_indexlist " + rst);

		int i = 0;
		obj = new Object[1][2];
		try {

			while (rst.next()) {
				vector_indexlist.add(i, rst.getString(1));
				i++;
				vector_indexlist.add(i, rst.getString(2));
				i++;
			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :" + sqlexp.getMessage());
		}
	}

	/**
	 * @return Returns the vector_companylist.
	 */
	public Vector getVector_companylist() {
		return vector_companylist;
	}

	/**
	 * @param vector_companylist
	 *            The vector_companylist to set.
	 */
	public void setVector_companylist() {
		Logging.debug("Inside setVector_companylist");
	//	Connect con = new app.Connect();
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			Logging
					.debug("Inside if condition for checking connection");
			con.getConnection();
		}
		Logging.debug("Inside setVector_companylist after connection");
		vector_companylist = new Vector();
		rst = con.getClientList("company_list");
		int i = 0;
		obj = new Object[1][2];
		try {
			while (rst.next()) {
				vector_companylist.add(i, rst.getString(1));
				i++;
				vector_companylist.add(i, rst.getString(2));
				i++;
			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :" + sqlexp.getMessage());
		}
	}

	/**
	 * @return Returns the vector_exchangelist.
	 */
	public Vector getVector_exchangelist() {
		return vector_exchangelist;
	}

	/**
	 * @param vector_exchangelist
	 *            The vector_exchangelist to set.
	 */
	public void setVector_exchangelist() {
		Logging.debug("Inside setVector_exchangelist");
//		Connect con = new Connect();
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			con.getConnection();
		}
		Logging.debug("Inside setVector_exchangelist after connection");
		vector_exchangelist = new Vector();
		rst = con.getClientList("stock_exchange_list");
		Logging.debug("In SetVector_exchangelist " + rst);

		int i = 0;
		obj = new Object[1][2];
		try {

			while (rst.next()) {
				vector_exchangelist.add(i, rst.getString(1));
				i++;
				vector_exchangelist.add(i, rst.getString(2));
				i++;
			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :" + sqlexp.getMessage());
		}
	}

	/**
	 * @return Returns the vector_tabledata.
	 */
	public Vector getVector_tabledata() {
		return vector_tabledata;
	}

	public void setVector_tabledata1(String index) {

	//	Connect con = new Connect();
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			con.getConnection();
		}
		vector_tabledata = new Vector();
		rst = con.returnResult("stock_weightage_latest", index);
		int i = 0;
		Logging.debug("setVector_tabledata of Compose Index");
		try {

			while (rst.next()) {
				vector_tabledata.add(i, rst);
			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :" + sqlexp.getMessage());
		}

	}

	public void setTree_data_index() {
		Connect con = ConnectInit.getConnect();
	//	new Connect().getConnection();
		con.getConnection();
		tree_data_index = new Vector();
	//	rst = new Connect().getClientList("tree_index");
		rst = con.getClientList("tree_index");
		int i = 0;
		Logging.debug("setTree_data of Compose Index");
		try {

			while (rst.next()) {
				System.out.print("5");
				System.out.print(rst.getString(1) + "  " + rst.getString(2));
				tree_data_index.add(i, rst);
			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :" + sqlexp.getMessage());
		}

	}

	public Vector getTree_data_index() {
		Logging.debug("" + tree_data_index.size());
		return tree_data_index;
	}

	/**
	 * @param vector_tabledata
	 *            The vector_tabledata to set.
	 */
	public void setVector_tabledata(String index) {
	//	Connect con = new Connect();
		Connect con = ConnectInit.getConnect();
		total = 0.00;
//		org.jfree.chart.demo.servlet.AdjustDecimal ad = new org.jfree.chart.demo.servlet.AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		Connection connection = null;
		try {
			if (connection == null)
				connection = con.getdbConnection();
			double tmcv = 0.0;
			String index_id, date;
			ResultSet tmcvrst = con.stiockweightageLatestResult(connection,
					"get_tmcv_for_composition", index);
			Logging.debug("get tmcv of Compose Index");
			try {
				while (tmcvrst.next()) {
					index_id = tmcvrst.getString(1);
					tmcv = tmcvrst.getDouble(2);
					Logging.debug("tmcv is " + tmcv);
					date = tmcvrst.getString(3);
				}
				tmcvrst.close();
			} catch (SQLException sqlexp) {
				Logging.error("SQL Error :" + sqlexp.getMessage());
			}
			vector_tabledata = new Vector();
			String curr_exch_convIratecomp = null, tshare = null, price = null, mcap = null, strmcv = null;
			double weightage = 0.0, mcv = 0.0;
			rst = con.indexComposeResult(connection,
					"get_composition_for_compose_report", index);
			int i = 0;
			Logging.debug("setVector_tabledata of Compose Index");
			try {
				while (rst.next()) {
					String stockid = (String) rst.getString(1);
					if (rst.getString(1) == null) {
						vector_tabledata.add(i, "0");
					} else {
						vector_tabledata.add(i, rst.getString(1));
					}
					i++;
					if (rst.getString(2) == null) {
						vector_tabledata.add(i, "--");
					} else {
						vector_tabledata.add(i, rst.getString(2));
					}
					i++;
					if (rst.getString(4) == null) {
						vector_tabledata.add(i, "0");
					} else {
						tshare = rst.getString(4);
						vector_tabledata.add(i, tshare);
					}
					i++;

					if (rst.getString(5) == null) {
						vector_tabledata.add(i, "0");
					} else {
						price = rst.getString(5);
						price = ad.indexcompose(price);
						vector_tabledata.add(i, price);
					}
					i++;

					if (rst.getString(9) == null) {
						vector_tabledata.add(i, "0");
					} else {
						vector_tabledata.add(i, rst.getString(9));
					}
					i++;

					if (rst.getString(6) == null) {
						vector_tabledata.add(i, "0");
					} else {
						price = rst.getString(6);
						price = ad.indexcompose(price);
						vector_tabledata.add(i, price);
					}
					i++;

					if (rst.getString(10) == null) {
						vector_tabledata.add(i, "0");
					} else {
						price = rst.getString(10);
						price = ad.indexcompose(price);
						vector_tabledata.add(i, price);
					}
					i++;
					if (rst.getString(3) == null) {
						vector_tabledata.add(i, "--");
					} else {
						vector_tabledata.add(i, rst.getString(3));
					}
					i++;

					curr_exch_convIratecomp = getCurrancyExchRate(connection,
							index, stockid);
					//Logging.getDebug("curr_exch_convIrate is
					// "+curr_exch_convIrate);
					curr_exch_convIratecomp = ad
							.indexcompose4digit(curr_exch_convIratecomp);
					if (curr_exch_convIratecomp == null) {
						vector_tabledata.add(i, "0.00");
					} else {
						vector_tabledata.add(i, curr_exch_convIratecomp);
					}
					i++;

					if (rst.getString(7) == null) {
						vector_tabledata.add(i, "0");
					} else {
						mcap = (String) rst.getString(7);
						mcap = ad.indexcompose(mcap);
						vector_tabledata.add(i, mcap);
					}
					i++;

					if (rst.getString(7) == null) {
						vector_tabledata.add(i, "0");
					} else {
						mcap = (String) rst.getString(7);
						mcap = ad.indexcompose(mcap);
						vector_tabledata.add(i, mcap);
					}
					i++;

					strmcv = rst.getString(7);
					mcv = Double.parseDouble(strmcv);
					if (tmcv != 0.0) {
						weightage = (mcv / tmcv) * 100.00;
					}
					total = total + weightage;
					String strweightage = new Double(weightage).toString();
					strweightage = ad.shareholdingpatt(strweightage);
					strweightage = ad.indexcompose4digit(strweightage);
					vector_tabledata.add(i, strweightage);
					//weightage
					/*
					 * if (rst.getString(8) == null) { vector_tabledata.add(i,
					 * "0"); } else { vector_tabledata.add(i, rst.getString(8)); }
					 */
					i++;

					if (rst.getString(8) == null) {
						vector_tabledata.add(i, "--");
					} else {
						vector_tabledata.add(i, rst.getString(8));
					}
					i++;

				}
				rst.close();
			} catch (SQLException sqlexp) {
				Logging.error("SQL Error :" + sqlexp.getMessage());
			}
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ex) {
				Logging.error(" Error : Unable to close Connection "
						+ ex.getMessage());
			}
		}
	}

	/**
	 * @return Returns the vector_tabledata.
	 */
	public Vector getVector_tabledatacompute() {
		return vector_tabledatacompute;
	}

	/**
	 * @param vector_tabledatacompute
	 *            The vector_tabledatacompute to set.
	 */
	public void setVector_tabledatacompute(String index) {
	//	AdjustDecimal ad = new AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
	//	app.Connect con = new Connect();
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		vector_tabledatacompute = new Vector();
		String curr_exch_convIrate = null;
		int i = 0;
		String str = null;
		rst = null;
		Logging.debug("setVector_tabledatacompute of Compose Index");
		try {
			if(index!=null && (index!=null && !(index.equals("0") || index.equals("null") || index.trim().length()==0))) {
				if (connection == null)
					connection = con.getdbConnection();
				Logging.debug(index);
				rst = con.returnResult(connection,
						"fixed_income_stock_weightage_latest_for_compute_index", index);
				while (rst.next()) {
					String stockid = (String) rst.getString(1);
					if (rst.getString(1) == null) {
						vector_tabledatacompute.add(i, "--");
					} else {
						vector_tabledatacompute.add(i, rst.getString(1));
					}
					i++;
	
					if (rst.getString(2) == null) {
						vector_tabledatacompute.add(i, "--");
					} else {
						vector_tabledatacompute.add(i, rst.getString(2));
					}
					i++;
	
					if (rst.getString(4) == null) {
						vector_tabledatacompute.add(i, "0");
					} else {
						str = rst.getString(4);
						str = ad.indexcompose(str);
						vector_tabledatacompute.add(i, str);
					}
					i++;
	
					if (rst.getString(5) == null) {
						vector_tabledatacompute.add(i, "0");
					} else {
						str = rst.getString(5);
						str = ad.indexcompose(str);
						vector_tabledatacompute.add(i, str);
					}
					i++;
					//Logging.getDebug("mkt lot is "+rst.getString(9));
					//Logging.getDebug("mkt lot is "+rst.getString(10));
					if (rst.getString(10) == null) {
						vector_tabledatacompute.add(i, "0");
					} else {
						vector_tabledatacompute.add(i, rst.getString(10));
					}
					i++;
	
					if (rst.getString(6) == null) {
						vector_tabledatacompute.add(i, "0");
					} else {
						str = rst.getString(6);
						str = ad.indexcompose(str);
						vector_tabledatacompute.add(i, str);
					}
					i++;
	
					if (rst.getString(11) == null) {
						vector_tabledatacompute.add(i, "0");
					} else {
						str = rst.getString(11);
						str = ad.indexcompose(str);
						vector_tabledatacompute.add(i, str);
					}
					i++;
	
					if (rst.getString(3) == null) {
						vector_tabledatacompute.add(i, "--");
					} else {
						vector_tabledatacompute.add(i, rst.getString(3));
					}
					i++;
	
					curr_exch_convIrate = getCurrancyExchRate(connection, index,
							stockid);
					curr_exch_convIrate = ad
							.indexcompose4digit(curr_exch_convIrate);
					//Logging.getDebug("curr_exch_convIrate is
					// "+curr_exch_convIrate);
					if (curr_exch_convIrate == null) {
						vector_tabledatacompute.add(i, "0.00");
					} else {
						vector_tabledatacompute.add(i, curr_exch_convIrate);
					}
					i++;
	
					if (rst.getString(7) == null) {
						vector_tabledatacompute.add(i, "0");
					} else {
						str = rst.getString(7);
						str = ad.indexcompose(str);
						vector_tabledatacompute.add(i, str);
					}
					i++;
	
					if (rst.getString(7) == null) {
						vector_tabledatacompute.add(i, "0");
					} else {
						str = rst.getString(7);
						str = ad.indexcompose(str);
						vector_tabledatacompute.add(i, str);
					}
					i++;
	
					if (rst.getString(8) == null) {
						vector_tabledatacompute.add(i, "0");
					} else {
						String weightage = rst.getString(8);
						weightage = ad.shareholdingpatt(weightage);
						weightage = ad.indexcompose4digit(weightage);
						Logging
								.debug("weightage after formatting " + weightage);
						vector_tabledatacompute.add(i, weightage);
					}
					i++;
	
					if (rst.getString(9) == null) {
						vector_tabledatacompute.add(i, "--");
					} else {
						vector_tabledatacompute.add(i, rst.getString(9));
					}
					i++;
	
				}
		}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :" + sqlexp.getMessage());
		} finally {
			try {
				if (rst != null)
					rst.close();
				if (connection != null)
					connection.close();
			} catch (Exception ex) {
				Logging.error(" Error : Unable to close connection "
						+ ex.getMessage());
			}
		}
	}

	public StringBuffer getCompositionBuffer(HttpServletRequest request,
			String var) {
		org.jfree.chart.demo.servlet.FixedIncomeComposeIndex ci = new org.jfree.chart.demo.servlet.FixedIncomeComposeIndex();
		org.jfree.chart.demo.servlet.FieldSort sort = new org.jfree.chart.demo.servlet.FieldSort();
		StringBuffer buffer = new StringBuffer();
		LogonForm form = (LogonForm) request.getSession().getAttribute(
				"user");
	//	AcessControl asc = new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		boolean flag = false;
		if (form != null) {
			Vector uname = new Vector();
			uname = asc.getUseActivitiesId(form);
			flag = asc.HasAcess("2", uname);
			//	Logging.debug("flag is "+flag);
		}
		int fieldno, colno;
		String field = (String) request.getParameter("FieldNo");
		String col = (String) request.getParameter("ColNo");
		if (col == null) {
			col = "13";
		}
		if (field == null) {
			field = "12";
		}
		fieldno = Integer.parseInt(field);
		colno = Integer.parseInt(col);
		ci.setVector_tabledatacompute(var);

		Vector v = ci.getVector_tabledatacompute();
		int dir = 0, dir1 = 0, dir2 = 0, dir3 = 0, dir4 = 0, dir5 = 0, dir6 = 0, dir7 = 0, dir8 = 0, dir9 = 0, dir10 = 0, dir11 = 0;
		if (v.size() == 0) {

			buffer
					.append("<table border='1' align='center' class='griStyle' width='631' height='222' cellspacing='0' cellpadding='0'>");
			buffer.append("<tr>");
			buffer
					.append("<td  class='griStyle-message' align='center' valign='middle'>");
			buffer.append(" <p style='margin-left: 9'><b>"
					+ "No Data Available For Criteria You Have Selected"
					+ "</b></p>");
			buffer.append("</td>");
			buffer.append("</tr>");
			buffer.append("</table>");
		} else {
			if (fieldno == 1) {
				dir = sort.getcount();
				v = sort.SetOrderSort(v, 1, 13);
			}
			if (fieldno == 2) {
				dir1 = sort.getcount1();
				v = sort.SetOrderSortNo(v, 2, 13);
			}
			if (fieldno == 3) {
				dir2 = sort.getcount2();
				v = sort.SetOrderSortNo(v, 3, 13);
			}
			if (fieldno == 4) {

				dir3 = sort.getcount3();
				v = sort.SetOrderSortNo(v, 4, 13);
			}
			if (fieldno == 5) {
				dir4 = sort.getcount4();
				v = sort.SetOrderSortNo(v, 5, 13);
			}
			if (fieldno == 6) {
				dir5 = sort.getcount5();
				v = sort.SetOrderSortNo(v, 6, 13);
			}
			if (fieldno == 7) {
				dir6 = sort.getcount6();
				v = sort.SetOrderSort(v, 7, 13);
			}
			if (fieldno == 8) {

				dir7 = sort.getcount7();
				v = sort.SetOrderSortNo(v, 8, 13);
			}
			if (fieldno == 9) {

				dir8 = sort.getcount8();
				v = sort.SetOrderSortNo(v, 9, 13);
			}
			if (fieldno == 10) {

				dir9 = sort.getcount9();
				v = sort.SetOrderSortNo(v, 10, 13);
			}
			if (fieldno == 11) {

				dir10 = sort.getcount10();
				v = sort.SetOrderSortNo(v, 11, 13);
			}
			//    Logging.getInfo("Size of table "+v.size());

			buffer.append("<br>");
			buffer
					.append("<table border='1' align='center' width='95%' class='gridStyle' cellspacing='0'  >");
			buffer.append("<tr>");
			String hlink1 = "FixedIncomeIndexHome.jsp?D1=" + var
					+ "&B1=Go&T1=++&FieldNo=1&ColNo=13";
			buffer
					.append("<td width='15' class='gridStyle-header' align='center' valign='middle' height='1'><a href="
							+ hlink1
							+ " onmouseover='window.status='';return true'>"
							+ "Stock&nbsp;Name"
							+ "</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");

			if (fieldno == 1) {
				if (dir % 2 == 0) {
					buffer
							.append("<img border='0' src='/Stockpile/pages/images/down.jpg' width='10' align='middle' height='14'>");
				} else {
					buffer
							.append(" <img border='0' src='/Stockpile/pages/images/up.jpg' width='10' align='middle' height='144'>");
				}
			}
			buffer.append(" </td>");
			String hlink2 = "FixedIncomeIndexHome.jsp?D1=" + var
					+ "&B1=Go&T1=++&FieldNo=2&ColNo=13";
			buffer
					.append("<td width='8%' class='gridStyle-header' align='center' valign='middle' height='1'><a href="
							+ hlink2
							+ " onmouseover='window.status='';return true'>"
							+ "Outstanding Shares" + "</a>");

			if (fieldno == 2) {
				if (dir1 % 2 == 0) {
					buffer
							.append("<img border='0' src='/Stockpile/pages/images/down.jpg' width='10' align='middle' height='14'>");
				} else {
					buffer
							.append("<img border='0' src='/Stockpile/pages/images/up.jpg' width='10' align='middle' height='14'>");
				}
			}
			buffer.append("</td>");
			String hlink3 = "FixedIncomeIndexHome.jsp?D1=" + var
					+ "&B1=Go&T1=++&FieldNo=3&ColNo=13";
			buffer
					.append("<td width='6%' class='gridStyle-header' align='center' valign='middle' height='1'><a href="
							+ hlink3
							+ " onmouseover='window.status='';return true'>"
							+ "IWF" + "</a>");
			if (fieldno == 3) {
				if (dir2 % 2 == 0) {
					buffer
							.append("<img border='0' src='/Stockpile/pages/images/down.jpg' width='10' align='middle' height='14'>");
				} else {
					buffer
							.append("<img border='0' src='/Income/pages/images/up.jpg' width='10' align='middle' height='14'>");
				}
			}
			buffer.append("</td>");
			String hlink4 = "FixedIncomeIndexHome.jsp?D1=" + var
					+ "&B1=Go&T1=++&FieldNo=4&ColNo=13";
			buffer
					.append("<td width='6%' class='gridStyle-header' align='center' valign='middle' height='1' nowrap=\"nowrap\"><a href="
							+ hlink4
							+ " onmouseover='window.status='';return true'>"
							+ "Mkt. Lot" + "</a>");

			if (fieldno == 4) {
				if (dir3 % 2 == 0) {
					buffer
							.append("<img border='0' src='/Income/pages/images/down.jpg' width='10' align='middle' height='14'> ");
				} else {
					buffer
							.append("<img border='0' src='/Income/pages/images/up.jpg' width='10' align='middle' height='14'>");
				}
			}
			buffer.append("</td>");
			String hlink5 = "FixedIncomeIndexHome.jsp?D1=" + var
					+ "&B1=Go&T1=++&FieldNo=5&ColNo=13";
			buffer
					.append("<td width='8%' class='gridStyle-header' align='center' valign='middle' height='1' nowrap=\"nowrap\"><a href="
							+ hlink5
							+ " onmouseover='window.status='';return true'>"
							+ "Close Price" + "</a>");

			if (fieldno == 5) {
				if (dir4 % 2 == 0) {
					buffer
							.append("<img border='0' src='/Income/pages/images/down.jpg' width='10' align='middle' height='14'>");
				} else {
					buffer
							.append("<img border='0' src='/Income/pages/images/up.jpg' width='10' align='middle' height='14'>");
				}
			}
			buffer.append(" </td>");
			String hlink6 = "FixedIncomeIndexHome.jsp?D1=" + var
					+ "&B1=Go&T1=++&FieldNo=6&ColNo=13";
			buffer
					.append("<td width='8%' class='gridStyle-header' align='center' valign='middle' height='1' nowrap=\"nowrap\"><a href="
							+ hlink5
							+ " onmouseover='window.status='';return true'>"
							+ "Price (LTP)" + "</a>");

			if (fieldno == 6) {
				if (dir5 % 2 == 0) {
					buffer
							.append("<img border='0' src='/Income/pages/images/down.jpg' width='10' align='middle' height='14'>");
				} else {
					buffer
							.append("<img border='0' src='/Income/pages/images/up.jpg' width='10' align='middle' height='14'>");
				}
			}
			buffer.append(" </td>");
			String hlink7 = "FixedIncomeIndexHome.jsp?D1=" + var
					+ "&B1=Go&T1=++&FieldNo=7&ColNo=13";
			buffer
					.append("<td width='6%' class='gridStyle-header' align='center' valign='middle' height='1'><a href="
							+ hlink6
							+ " onmouseover='window.status='';return true'>"
							+ "Currency" + "</a>");

			if (fieldno == 7) {
				if (dir6 % 2 == 0) {
					buffer
							.append("<img border='0' src='/Income/pages/images/down.jpg' width='10' align='middle' height='14'>");
				} else {
					buffer
							.append(" <img border='0' src='/Income/pages/images/up.jpg' width='10' align='middle' height='14'>");
				}
			}
			buffer.append(" </td>");
			String hlink8 = "FixedIncomeIndexHome.jsp?D1=" + var
					+ "&B1=Go&T1=++&FieldNo=8&ColNo=13";
			buffer
					.append("<td width='6%' class='gridStyle-header' align='center' valign='middle' height='1'><a href="
							+ hlink7
							+ " onmouseover='window.status='';return true'>"
							+ "Currency Exch. Rate" + "</a>");

			if (fieldno == 8) {
				if (dir7 % 2 == 0) {
					buffer
							.append("<img border='0' src='/Income/pages/images/down.jpg' width='10' align='middle' height='14'>");
				} else {
					buffer
							.append("<img border='0' src='/Income/pages/images/up.jpg' width='10' align='middle' height='14'>");
				}
			}
			buffer.append("</td>");
			String hlink9 = "FixedIncomeIndexHome.jsp?D1=" + var
					+ "&B1=Go&T1=++&FieldNo=9&ColNo=13";
			buffer
					.append("<td width='10%' class='gridStyle-header' align='center' valign='middle' height='1'><a href="
							+ hlink8
							+ " onmouseover='window.status='';return true'>"
							+ "Market cap" + "</a>");

			if (fieldno == 9) {
				if (dir8 % 2 == 0) {
					buffer
							.append("<img border='0' src='/Income/pages/images/down.jpg' width='10' align='middle' height='14'>");
				} else {
					buffer
							.append(" <img border='0' src='/Income/pages/images/up.jpg' width='10' align='middle' height='14'>");
				}
			}
			buffer.append("</td>");
			String hlink10 = "FixedIncomeIndexHome.jsp?D1=" + var
					+ "&B1=Go&T1=++&FieldNo=10&ColNo=13";
			buffer
					.append("<td width='10%' class='gridStyle-header' align='center' valign='middle' height='1'><a href="
							+ hlink9
							+ " onmouseover='window.status='';return true'>"
							+ "Adjusted Market Cap" + "</a>");

			if (fieldno == 10) {
				if (dir9 % 2 == 0) {
					buffer
							.append("<img border='0' src='/Income/pages/images/down.jpg' width='10' align='middle' height='14'> ");
				} else {
					buffer
							.append("<img border='0' src='/Income/pages/images/up.jpg' width='10' align='middle' height='14'>");
				}
			}
			buffer.append("</td>");
			String hlink11 = "FixedIncomeIndexHome.jsp?D1=" + var
					+ "&B1=Go&T1=++&FieldNo=11&ColNo=13";
			buffer
					.append("<td width='8%' class='gridStyle-header' align='center' valign='middle' height='1'><a href="
							+ hlink10
							+ " onmouseover='window.status='';return true'>"
							+ "Weightage" + "</a>");

			if (fieldno == 11) {
				if (dir10 % 2 == 0) {
					buffer
							.append("<img border='0' src='/Income/pages/images/down.jpg' width='10' align='middle' height='14'> ");
				} else {
					buffer
							.append("<img border='0' src='/Income/pages/images/up.jpg' width='10' align='middle' height='14'>");
				}
			}
			buffer.append("</td>");
			String hlink12 = "FixedIncomeIndexHome.jsp?D1=" + var
					+ "&B1=Go&T1=++&FieldNo=12&ColNo=13";
			buffer
					.append("<td width='12%' class='gridStyle-header' align='center' valign='middle' height='1' nowrap=\"nowrap\">&nbsp;&nbsp;<a href="
							+ hlink11
							+ " onmouseover='window.status='';return true'>"
							+ "Date" + " </a>&nbsp;&nbsp;");

			if (fieldno == 12) {
				dir10 = sort.getcount11();
				if (dir11 % 2 == 0) {
					buffer
							.append("<img border='0' src='/Income/pages/images/down.jpg' width='10' align='middle' height='14'> ");
				} else {
					buffer
							.append("<img border='0' src='/Income/pages/images/up.jpg' width='10' align='middle' height='14'>");
				}
			}
			buffer.append("&nbsp;&nbsp;</td>");
			buffer.append("</tr>");
			AdjustDecimal ad = ConnectInit.getAdjustDecimal();
	//		org.jfree.chart.demo.servlet.AdjustDecimal ad = new org.jfree.chart.demo.servlet.AdjustDecimal();
			Iterator i = v.iterator();
			int count = 0;
			double total = 0.00;
			while (i.hasNext()) {
				count++;
				String id = (String) i.next();
				if (count % 2 != 0) {
					String temp = "/Stockpile/pages/fixedincome/StockMasterBonds.jsp?s_stockID="
							+ id;

					buffer.append(" <tr>");
					buffer
							.append(" <td width='15' align='left' class='gridStyle-odd' height='1'>");
					String temp_stock_name = (String) i.next();
					if (temp_stock_name.length() < 25) {
						//   Logging.debug("avoiding space for stock
						// :"+temp_stock_name);
						temp_stock_name = temp_stock_name.replaceAll(" ",
								"&nbsp;");
					} else {
						StringTokenizer st = new StringTokenizer(
								temp_stock_name);
						String temp1 = "", temp2, temp3 = "", temp4 = "";
						while (st.hasMoreTokens()) {
							temp2 = (String) st.nextToken();
							if ((temp4.length() + temp2.length()) < 25) {
								temp1 = "&nbsp;";
								temp4 = temp3;
							} else {
								temp1 = " ";
								temp4 = "";
							}
							temp3 = temp3 + temp1 + temp2;
						}
						temp_stock_name = temp3;
					}
					if (flag == true) {
						buffer
								.append("<p style='margin-left: 5; margin-right: 5'><a href="
										+ temp
										+ " onmouseover='window.status='';return true'>"
										+ temp_stock_name + "</a> </p>");
					} else {
						buffer
								.append("<p style='margin-left: 5; margin-right: 5'>"
										+ temp_stock_name + "</p>");
					}
					buffer.append("</td>");
					buffer
							.append("<td width='8%' align='right' class='gridStyle-odd' height='1'>");
					buffer
							.append(" <p style='margin-left: 5; margin-right: 5'>"
									+ org.jfree.chart.demo.servlet.AdjustDecimal
											.ArrangeAsNumeric((String) i.next())
									+ "</p>");
					buffer.append(" </td>");
					buffer
							.append(" <td width='6%' align='right' class='gridStyle-odd' height='1'>");
					buffer
							.append(" <p style='margin-left: 5; margin-right: 5'>"
									+ (String) i.next() + "</p>");
					buffer.append("</td>");
					buffer
							.append("<td width='6%' align='right' class='gridStyle-odd' height='1'>");
					buffer.append("<p style='margin-left: 5; margin-right: 5'>"
							+ (String) i.next() + "</p>");
					buffer.append("</td>");
					buffer
							.append("<td width='8%' align='right' class='gridStyle-odd' height='1'>");
					buffer
							.append(" <p style='margin-left: 5; margin-right: 5'>"
									+ org.jfree.chart.demo.servlet.AdjustDecimal
											.ArrangeAsNumeric((String) i.next())
									+ "</p>");
					buffer.append("</td>");
					buffer
							.append("<td width='8%' align='right' class='gridStyle-odd' height='1'>");
					buffer
							.append(" <p style='margin-left: 5; margin-right: 5'>"
									+ org.jfree.chart.demo.servlet.AdjustDecimal
											.ArrangeAsNumeric((String) i.next())
									+ "</p>");
					buffer.append("</td>");

					buffer
							.append(" <td width='6%' align='left' class='gridStyle-odd' height='1'>");
					buffer.append("<p style='margin-left: 5; margin-right: 5'>"
							+ (String) i.next() + "</p>");
					buffer.append("</td>");

					buffer
							.append("<td width='6%' align='right' class='gridStyle-odd' height='1'>");
					buffer
							.append("  <p style='margin-left: 5; margin-right: 5'>"
									+ (String) i.next() + "</p>");
					buffer.append(" </td>");
					buffer
							.append(" <td width='10%' align='right' class='gridStyle-odd' height='1'>");
					buffer.append("<p style='margin-left: 5; margin-right: 5'>"
							+ org.jfree.chart.demo.servlet.AdjustDecimal
									.ArrangeAsNumeric((String) i.next())
							+ "</p>");
					buffer.append("</td>");
					buffer
							.append("<td width='10%' align='right' class='gridStyle-odd' height='1'>");
					buffer.append("<p style='margin-left: 5; margin-right: 5'>"
							+ org.jfree.chart.demo.servlet.AdjustDecimal
									.ArrangeAsNumeric((String) i.next())
							+ "</p>");
					buffer.append(" </td>");

					String tp2 = (String) i.next();

					total += (double) Double.parseDouble(tp2);

					buffer
							.append("<td width='8%' align='right' class='gridStyle-odd' height='1'>");
					buffer
							.append(" <p style='margin-left: 5; margin-right: 5'>"
									+ tp2 + "%</p>");
					buffer.append(" </td>");
					buffer
							.append(" <td width='12%' align='center' class='gridStyle-odd' height='1'>");
					buffer
							.append(" <p style='margin-left: 5; margin-right: 5'>"
									+ (String) i.next() + "</p>");
					buffer.append(" </td>");
					buffer.append("</tr>");

				} else {
					String temp = "/Stockpile/pages/fixedincome/StockMasterBonds.jsp?s_stockID="
							+ id;

					buffer.append(" <tr>");
					buffer
							.append("<td width='15' class='gridStyle-even' align='left' height='1'>");
					String temp_stock_name = (String) i.next();
					if (temp_stock_name.length() < 25) {
						//   Logging.debug("avoiding space for stock
						// :"+temp_stock_name);
						temp_stock_name = temp_stock_name.replaceAll(" ",
								"&nbsp;");
					} else {
						StringTokenizer st = new StringTokenizer(
								temp_stock_name);
						String temp1 = "", temp2, temp3 = "", temp4 = "";
						while (st.hasMoreTokens()) {
							temp2 = (String) st.nextToken();
							if ((temp4.length() + temp2.length()) < 25) {
								temp1 = "&nbsp;";
								temp4 = temp3;
							} else {
								temp1 = " ";
								temp4 = "";
							}
							temp3 = temp3 + temp1 + temp2;
						}
						temp_stock_name = temp3;
					}
					if (flag == true) {
						buffer
								.append("<p style='margin-left: 5; margin-right: 5'><a href="
										+ temp
										+ " onmouseover='window.status='';return true'>"
										+ temp_stock_name + "</a> </p>");
					} else {
						buffer
								.append("<p style='margin-left: 5; margin-right: 5'>"
										+ temp_stock_name + "</p>");
					}
					buffer.append("</td>");
					buffer
							.append("<td width='8%' class='gridStyle-even' align='right' height='1'>");
					buffer
							.append(" <p style='margin-left: 5; margin-right: 5'>"
									+ org.jfree.chart.demo.servlet.AdjustDecimal
											.ArrangeAsNumeric((String) i.next())
									+ "</p>");
					buffer.append("</td>");
					buffer
							.append("<td width='6%' class='gridStyle-even' align='right' height='1'>");
					buffer
							.append(" <p style='margin-left: 5; margin-right: 5'>"
									+ (String) i.next() + "</p>");
					buffer.append(" </td>");
					buffer
							.append("<td width='6%' class='gridStyle-even' align='right' height='1'>");
					buffer.append("<p style='margin-left: 5; margin-right: 5'>"
							+ (String) i.next() + "</p>");
					buffer.append(" </td>");
					buffer
							.append("<td width='8%' class='gridStyle-even' align='right' height='1'>");
					buffer
							.append("  <p style='margin-left: 5; margin-right: 5'>"
									+ org.jfree.chart.demo.servlet.AdjustDecimal
											.ArrangeAsNumeric((String) i.next())
									+ "</p>");
					buffer.append(" </td>");
					buffer
							.append("<td width='8%' class='gridStyle-even' align='right' height='1'>");
					buffer
							.append("  <p style='margin-left: 5; margin-right: 5'>"
									+ org.jfree.chart.demo.servlet.AdjustDecimal
											.ArrangeAsNumeric((String) i.next())
									+ "</p>");
					buffer.append(" </td>");

					buffer
							.append("<td width='6%' align='left' class='gridStyle-even' height='1'>");
					buffer.append("<p style='margin-left: 5; margin-right: 5'>"
							+ (String) i.next() + "</p>");
					buffer.append("</td>");

					buffer
							.append("<td width='6%' align='right' class='gridStyle-even' height='1'>");
					buffer.append("<p style='margin-left: 5; margin-right: 5'>"
							+ (String) i.next() + "</p>");
					buffer.append("</td>");
					buffer
							.append("<td width='10%' align='right' class='gridStyle-even' height='1'>");
					buffer.append("<p style='margin-left: 5; margin-right: 5'>"
							+ org.jfree.chart.demo.servlet.AdjustDecimal
									.ArrangeAsNumeric((String) i.next())
							+ "</p>");
					buffer.append("</td>");
					buffer
							.append(" <td width='10%' align='right' class='gridStyle-even' height='1'>");
					buffer.append("<p style='margin-left: 5; margin-right: 5'>"
							+ org.jfree.chart.demo.servlet.AdjustDecimal
									.ArrangeAsNumeric((String) i.next())
							+ "</p>");
					buffer.append("</td>");

					String tp1 = (String) i.next();
					total += (double) Double.parseDouble(tp1);

					buffer
							.append(" <td width='8%' align='right' class='gridStyle-even' height='1'>");
					buffer
							.append("  <p style='margin-left: 5; margin-right: 5'>"
									+ tp1 + "%</p>");
					buffer.append(" </td>");
					buffer
							.append("<td width='12%' align='center' class='gridStyle-even' height='1'>");
					buffer
							.append("  <p style='margin-left: 5; margin-right: 5'>"
									+ (String) i.next() + "</p>");
					buffer.append("</td>");
					buffer.append("</tr>");
				}
			}

			buffer.append("<tr>");
			buffer
					.append("<td width='15%' class='gridStyle-header' height='1'>");
			buffer.append("<p style='margin-left: 5; margin-right: 5'>"
					+ "&nbsp;" + "</a> </p>");
			buffer.append("</td>");
			buffer
					.append("<td width='8%' class='gridStyle-header' align='right'>");
			buffer.append("<p style='margin-left: 5; margin-right: 5'>"
					+ "&nbsp;" + "</p>");
			buffer.append("</td>");
			buffer
					.append("<td width='6%' class='gridStyle-header' align='right'>");
			buffer.append(" <p style='margin-left: 5; margin-right: 5'>"
					+ "&nbsp;" + "</p>");
			buffer.append("</td>");
			buffer
					.append("<td width='6%' class='gridStyle-header' align='right'>");
			buffer.append("<p style='margin-left: 5; margin-right: 5'>"
					+ "&nbsp;" + "</p>");
			buffer.append("</td>");
			buffer
					.append("<td width='8%' class='gridStyle-header' align='center'>");
			buffer.append("<p style='margin-left: 5; margin-right: 5'>"
					+ "&nbsp;" + "</p>");
			buffer.append("</td>");
			buffer
					.append("<td width='8%' class='gridStyle-header' align='center'>");
			buffer.append("<p style='margin-left: 5; margin-right: 5'>"
					+ "&nbsp;" + "</p>");
			buffer.append("</td>");

			buffer
					.append(" <td width='6%' class='gridStyle-header' align='right'>");
			buffer.append("<p style='margin-left: 5; margin-right: 5'>"
					+ "&nbsp;" + "</p>");
			buffer.append("</td>");
			buffer
					.append("<td width='6%' class='gridStyle-header' align='right'>");
			buffer.append("<p style='margin-left: 5; margin-right: 5'>"
					+ "&nbsp;" + "</p>");
			buffer.append("</td> ");
			buffer
					.append("<td width='10%' class='gridStyle-header' align='right'>");
			buffer.append("<p style='margin-left: 5; margin-right: 5'>"
					+ "&nbsp;" + "</p>");
			buffer.append("</td> ");
			buffer
					.append("<td width='10%' class='gridStyle-header' align='right'>");
			buffer.append(" <p style='margin-left: 5; margin-right: 5'>"
					+ "Total Weightage" + "</p>");
			buffer.append("</td>");

			if (total >= 99.9) {
				total = 100;
			}
			String total1 = new Double(total).toString();
			String tot = ad.indexcompose(total1);

			buffer
					.append("<td width='8%' class='gridStyle-header' align='right'>");
			buffer.append("<p style='margin-left: 5; margin-right: 5'>" + tot
					+ " %</p>");
			buffer.append("</td>");
			buffer
					.append("<td width='12%' class='gridStyle-header' align='center'>");
			buffer.append(" <p style='margin-left: 5; margin-right: 5'>"
					+ "&nbsp;" + "</p>");
			buffer.append("</td>");
			buffer.append("</tr>");
		}
		buffer.append("</table>");
		Logging.info("finally Size of table   " + v.size());
		return buffer;
	}

	/**
	 * 
	 * @return
	 */
	/*
	 * public Vector getVector_stockWiseWeightage() { //return
	 * vector_stockWiseWeightage; }
	 */
	/**
	 * @param vector_stockWiseWeightage
	 *            The vector_stockWiseWeightage to set.
	 */
	public void setVector_stockWiseWeightage(String index, String date) {
	//	app.Connect con = new app.Connect();
		Connect con = ConnectInit.getConnect();
		total = 0.00;
		Connection connection = null;
	//	org.jfree.chart.demo.servlet.AdjustDecimal ad = new org.jfree.chart.demo.servlet.AdjustDecimal();
		try {
			if (connection == null)
				connection = con.getdbConnection();
			double tmcv = 0.0;
			Hashtable iwf_sweightage = new Hashtable();
			Hashtable price_sweightage = new Hashtable();
			String index_id, price = null, iwf = null;
			ResultSet tmcvrst = con.indwtResult(connection,
					"get_iwf_for_stock_weightage", index, date);
			Logging.debug("get tmcv of Compose Index");
			try {
				while (tmcvrst.next()) {
					iwf_sweightage.put(tmcvrst.getString(1), tmcvrst
							.getString(2));
				}
				tmcvrst.close();
			} catch (SQLException sqlexp) {
				Logging.error("SQL Error :" + sqlexp.getMessage());
			}
			ResultSet rst = con.indwtResult(connection,
					"get_price_for_stock_weightage", index, date);
			Logging.debug("get tmcv of Compose Index");
			try {
				while (rst.next()) {
					price_sweightage.put(tmcvrst.getString(1), tmcvrst
							.getString(2));
				}
				rst.close();
			} catch (SQLException sqlexp) {
				Logging.error("SQL Error :" + sqlexp.getMessage());
			}
			Vector sweightage_mcv = new Vector();
			String curr_exch_convIratecomp = null;
			rst = con.stiockweightageLatestResult(connection,
					"get_detail_for_stock_weightage", index);
			int i = 0;
			Logging.debug("setVector_tabledata of Compose Index");
			try {
				while (rst.next()) {
					String stockid = (String) rst.getString(1);
					if (rst.getString(1) == null) {
						sweightage_mcv.add(i, "0");
					} else {
						sweightage_mcv.add(i, rst.getString(1));
					}
					i++;
					if (rst.getString(5) == null) {
						sweightage_mcv.add(i, "--");
					} else {
						sweightage_mcv.add(i, rst.getString(5));
					}
					i++;
					if (rst.getString(2) == null) {
						sweightage_mcv.add(i, "0");
					} else {
						sweightage_mcv.add(i, rst.getString(2));
					}
					i++;

					if (iwf_sweightage.containsKey(stockid)) {
						iwf = iwf_sweightage.get(stockid).toString();
					}
					sweightage_mcv.add(i, iwf);
					i++;

					if (rst.getString(4) == null) {
						sweightage_mcv.add(i, "0");
					} else {
						sweightage_mcv.add(i, rst.getString(4));
					}
					i++;

					if (price_sweightage.containsKey(stockid)) {
						price = price_sweightage.get(stockid).toString();
					}
					sweightage_mcv.add(i, price);
					i++;

					if (rst.getString(6) == null) {
						sweightage_mcv.add(i, "--");
					} else {
						sweightage_mcv.add(i, rst.getString(6));
					}
					i++;

					curr_exch_convIratecomp = getCurrancyExchRate(connection,
							index, stockid);
					Logging.debug("curr_exch_convIrate is "
							+ curr_exch_convIratecomp);
					if (curr_exch_convIratecomp == null) {
						sweightage_mcv.add(i, "0.00");
					} else {
						sweightage_mcv.add(i, curr_exch_convIratecomp);
					}
					i++;

					if (rst.getString(7) == null) {
						sweightage_mcv.add(i, "0");
					} else {
						sweightage_mcv.add(i, rst.getString(7));
					}
					i++;

					/*
					 * double weightage=0.0,mcv=0.0; String
					 * strmcv=rst.getString(7); mcv=Double.parseDouble(strmcv);
					 * if(tmcv!=0.0){ weightage=(mcv/tmcv)*100.00; }
					 * total=total+weightage; String strweightage=new
					 * Double(weightage).toString();
					 * strweightage=ad.indexcompose(strweightage);
					 * vector_stockWiseWeightage.add(i, strweightage);
					 * //weightage
					 *//*
					    * if (rst.getString(8) == null) {
					    * vector_tabledata.add(i, "0"); } else {
					    * vector_tabledata.add(i, rst.getString(8)); }
					    */
					i++;

				}
				rst.close();
			} catch (SQLException sqlexp) {
				Logging.error("SQL Error :" + sqlexp.getMessage());
			}
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ex) {
				Logging.error(" Error : Unable to close Connection "
						+ ex.getMessage());
			}
		}
	}

	public String getCurrancyExchRate(Connection connection, String index,
			String stockid) {
		String cexch_rate = null;
		String stk_currency_id = null, ind_currency_id = null;
//		Connect con = new Connect();
		Connect con = ConnectInit.getConnect();
		try {
			//Logging.getDebug("inside getCurrancyExchRate");
			ResultSet rstexc = con.indwtResult(connection,
					"get_index_and_stock_currency_id", stockid, index);
			int i = 0;
			Logging.debug("rst is " + rstexc);
			while (rstexc.next()) {
				if (rstexc.getString(1) == null) {
					stk_currency_id = "0";
				} else {
					stk_currency_id = (String) rstexc.getString(1);
				}
				if (rstexc.getString(2) == null) {
					ind_currency_id = "0";
				} else {
					ind_currency_id = (String) rstexc.getString(2);
				}
			}
			//Logging.getDebug("stk_currency_id is "+stk_currency_id+"
			// ind_currency_id is "+ind_currency_id);
			if (stk_currency_id.equals(ind_currency_id)) {
				cexch_rate = "1.00";
			} else {
				/*
				 * ResultSet rst11 =
				 * con.indwtResult("get_currency_exchange_rate",
				 * ind_currency_id,stk_currency_id); while (rst11.next()) { if
				 * (rst.getString(1) == null) { cexch_rate="0"; }else{
				 * cexch_rate=(String)rst11.getString(1); } }
				 */
				String temp = IndexCalculatorCollection
						.getIndexCurrancyExchRate(stk_currency_id,
								ind_currency_id);
				double exch = 0.0;
				if (temp != null) {
					exch = new Double(temp).doubleValue();
				} else {
					temp = IndexCalculatorCollection
							.getIndexCurrancyExchRate(ind_currency_id,
									stk_currency_id);
					if (temp == null) {
						exch = 1.0;
					} else {
						exch = 1 / new Double(temp).doubleValue();
					}
				}
				cexch_rate = new Double(exch).toString();
				Logging.debug("currency exchange rate is " + cexch_rate);
			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :" + sqlexp.getMessage());
		}
		return cexch_rate;
	}

	public Vector getVector_shareholdingPattern() {
		return vector_shareholdingPattern;
	}

	/**
	 * @param vector_shareholdingPattern
	 *            The vector_shareholdingPattern to set.
	 */
	public void setVector_shareholdingPattern(String index) {
	//	Connect con = new Connect();
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			Logging.debug("Inside if con");
			con.getConnection();
		}
		vector_shareholdingPattern = new Vector();
		rst = con.returnResult("stock_holding_pattern", index);
		int i = 0;
		Logging.debug("setVector_shareholdingPattern ");
		try {

			while (rst.next()) {
				//Logging.getDebug(rst.getString(1)+" "+rst.getString(2)+"
				// "+rst.getString(3)+" "+rst.getString(4));
				if (rst.getString(1) == null) {
					vector_shareholdingPattern.add(i, "--");
				} else {
					vector_shareholdingPattern.add(i, rst.getString(1));
				}
				i++;
				if (rst.getString(2) == null) {
					vector_shareholdingPattern.add(i, "--");
				} else {
					vector_shareholdingPattern.add(i, rst.getString(2));
				}
				i++;

				if (rst.getString(3) == null) {
					vector_shareholdingPattern.add(i, "0");
				} else {
					vector_shareholdingPattern.add(i, rst.getString(3));
				}
				i++;

				if (rst.getString(4) == null) {
					vector_shareholdingPattern.add(i, "0");
				} else {
					vector_shareholdingPattern.add(i, rst.getString(4));
				}
				i++;

			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :" + sqlexp.getMessage());
		}
	}

	/**
	 * @return Returns the vector_StockList.
	 */
	public Vector getVector_StockList() {
		return vector_StockList;
	}

	/**
	 * @param vector_StockList
	 *            The vector_StockList to set.
	 */
	public void setVector_StockList(String index, String isactive) {
		Logging.debug("inside setVector_StockList of Compose Index");
	//	org.jfree.chart.demo.servlet.AdjustDecimal ad = new org.jfree.chart.demo.servlet.AdjustDecimal();
	//	Connect con = new Connect();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			con = null;
			con.getConnection();
		}
		double mcap = 0.00;
		vector_StockList = new Vector();
		if (isactive != null && (isactive.equals("inactive"))) {
			rst = con.returnResult("get_stock_list_for_inactive_stock", index);
		} else {
			rst = con.returnResult("get_stock_list_for_report", index);
		}
		int i = 0;
		Logging.debug("setVector_StockList of Compose Index");
		try {

			while (rst.next()) {

				if (rst.getString(1) == null) {
					vector_StockList.add(i, "0");
				} else {
					vector_StockList.add(i, rst.getString(1));
				}
				i++;

				if (rst.getString(2) == null) {
					vector_StockList.add(i, "--");
				} else {
					vector_StockList.add(i, rst.getString(2));
				}
				i++;

				if (rst.getString(3) == null) {
					vector_StockList.add(i, "0");
				} else {
					vector_StockList.add(i, rst.getString(3));
				}
				i++;
				if (rst.getString(4) == null) {
					vector_StockList.add(i, "0");
				} else {
					String price = (String) rst.getString(4);
					price = ad.indexcompose(price);
					vector_StockList.add(i, price);
				}
				i++;
				if ((rst.getString(3) != null) && (rst.getString(4) != null)) {
					mcap = ((double) Double.parseDouble(rst.getString(3)) * (double) Double
							.parseDouble(rst.getString(4)));
				} else {
					mcap = 0.00;
				}
				if (mcap == 0.00) {
					vector_StockList.add(i, "0.00");
				} else {
					String strmcap = new Double(mcap).toString();
					strmcap = ad.indexcompose(strmcap);
					vector_StockList.add(i, strmcap);
				}
				i++;

				if (rst.getString(5) == null) {
					vector_StockList.add(i, "0");
				} else {
					vector_StockList.add(i, rst.getString(5));
				}
				i++;

				if (rst.getString(6) == null) {
					vector_StockList.add(i, "0");
				} else {
					vector_StockList.add(i, rst.getString(6));
				}
				i++;

			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :" + sqlexp.getMessage());
		}
	}

	/**
	 * @param vector_StockList
	 *            The vector_StockList to set.
	 */
	public void setInactive_Vector_StockList(String index, String isactive) {
		Logging
				.debug("inside setInactive_Vector_StockList of Compose Index");
//		Connect con = new Connect();
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			con = null;
			con.getConnection();
		}
		double mcap = 0.00;
		vector_StockList = new Vector();
		if (isactive != null && (isactive.equals("inactive"))) {
			rst = con.returnResult("get_stock_list_for_inactive_stock", index);
		}
		int i = 0;
		Logging.debug("setInactive_Vector_StockList of Compose Index");
		try {

			while (rst.next()) {

				if (rst.getString(1) == null) {
					vector_StockList.add(i, "0");
				} else {
					vector_StockList.add(i, rst.getString(1));
				}
				i++;

				if (rst.getString(2) == null) {
					vector_StockList.add(i, "--");
				} else {
					vector_StockList.add(i, rst.getString(2));
				}
				i++;

				if (rst.getString(3) == null) {
					vector_StockList.add(i, "0");
				} else {
					vector_StockList.add(i, rst.getString(3));
				}
				i++;
				if (rst.getString(4) == null) {
					vector_StockList.add(i, "0");
				} else {
					String price = (String) rst.getString(4);
					vector_StockList.add(i, price);
				}
				i++;

				if (rst.getString(5) == null) {
					vector_StockList.add(i, "0");
				} else {
					vector_StockList.add(i, rst.getString(5));
				}
				i++;

			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :" + sqlexp.getMessage());
		}
	}

	/**
	 * @return Returns the vector_companywiseweightage.
	 */
	public Vector getVector_companywiseweightage() {
		return vector_companywiseweightage;
	}

	/**
	 * @param vector_companywiseweightage
	 *            The vector_companywiseweightage to set.
	 */
	public void setVector_companywiseweightage(String index, String fdate) {
		Logging
				.debug("inside setVector_companywiseweightage of Compose Index");
//		app.Connect con = new app.Connect();
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			con = null;
			con.getConnection();
		}
		vector_companywiseweightage = new Vector();
		rst = con.ModifiedcompanyweightageResult("company_wise_weightage",
				index, fdate);
		int i = 0;
		Logging
				.debug("setVector_companywiseweightage of Compose Index");
		try {

			while (rst.next()) {

				if (rst.getString(1) == null) {
					vector_companywiseweightage.add(i, "--");
				} else {
					vector_companywiseweightage.add(i, rst.getString(1));
				}
				i++;

				if (rst.getString(2) == null) {
					vector_companywiseweightage.add(i, "0");
				} else {
					vector_companywiseweightage.add(i, rst.getString(2));
				}
				i++;

				if (rst.getString(3) == null) {
					vector_companywiseweightage.add(i, "0");
				} else {
					vector_companywiseweightage.add(i, rst.getString(3));
				}
				i++;

			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :" + sqlexp.getMessage());
		}
	}

	/**
	 * @return Returns the index_details.
	 */
	public Vector getIndex_details() {
		Logging.debug("Return Index_details of size "
				+ index_details.size());
		return index_details;
	}

	public String formatDate() {
		SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
		Date dt = new Date();
		return fr.format(dt).toString();
	}

	/**
	 * @param index_details
	 *            The index_details to set.
	 */
	public void setIndex_details(String dt_today) {
		String l_date = "";
	//	org.jfree.chart.demo.servlet.AdjustDecimal ad = new org.jfree.chart.demo.servlet.AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		Logging.debug("Before if  " + dt_today);
		//Logging.getDebug(dt_today.equals(null));
		if ((dt_today == null) || (dt_today.equals("null"))) {
			Logging.debug("date null inside if");
			SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
			Date dt = new Date();
			l_date = fr.format(dt).toString();
		}
		Logging.debug(l_date);
		index_details = new Vector();
//		Connect con = new Connect();
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			con.getConnection();
		}
		if ((dt_today == null) || (dt_today.equals("null"))) {
			rst = con.getLatestIndexDetails1("get_latest_all_index_details",l_date);
		} else {
			rst = con.getIndexDetails("all_index_details", dt_today);
		}
		int i = 0;
		Double dd = new Double("0");
		Logging.debug("setIndex_details of Compose Index");
		String value = null, open = null, high = null, low = null, close = null, mcap = null, divisor = null;
		try {

			while (rst.next()) {
				String strpchange = "0.00";
				if (((double) rst.getDouble(7)) != 0.00) {
					double pchange = (((double) rst.getDouble(2) - (double) rst
							.getDouble(7)) / (double) rst.getDouble(7)) * 100;
					strpchange = new Double(pchange).toString();
					Logging.debug("strpchange before adjusting is "
							+ strpchange);
					strpchange = ad.indexcompose(strpchange);
					Logging.debug("strpchange after adjusting is "
							+ strpchange);
				}
				if (rst.getString(13) == null) {

					index_details.add(i, "0");
				} else {
					index_details.add(i, rst.getString(13));
				}
				i++;

				//Logging.getDebug("inside while");
				if (rst.getString(1) == null) {
					index_details.add(i, "--");
				} else {

					index_details.add(i, rst.getString(1));

				}
				i++;

				if (rst.getString(2) == null) {
					index_details.add(i, "0.00");
				} else {
					value = rst.getString(2);
					value = ad.indexcompose(value);
					index_details.add(i, value);
				}
				i++;

				if (rst.getString(3) == null) {
					index_details.add(i, "--");
				} else {
					double change = (double) Double.parseDouble(strpchange);
					if (change > 0) {
						index_details.add(i, "up");
					} else {
						if (change == 0.00) {
							index_details.add(i, "mid");
						} else {
							//dd = new Double(change);
							index_details.add(i, "down");
						}
					}
				}
				i++;

				if (rst.getString(3) == null) {
					index_details.add(i, "0.00");
				} else {
					open = rst.getString(3);
					open = ad.indexcompose(open);
					index_details.add(i, open);
				}
				i++;

				if (rst.getString(4) == null) {
					index_details.add(i, "0.00");
				} else {
					high = rst.getString(4);
					high = ad.indexcompose(high);
					index_details.add(i, high);
				}
				i++;

				if (rst.getString(5) == null) {
					index_details.add(i, "0.00");
				} else {
					low = rst.getString(5);
					low = ad.indexcompose(low);
					index_details.add(i, low);
				}
				i++;

				if (rst.getString(12) == null) {
					index_details.add(i, "0");
				} else {
					int time = comapreTime(rst.getString(12));
					if (time > 0) {
						String temp = rst.getString(7);
						if (temp == null) {
							temp = "0.00";
						}
						temp = ad.indexcompose(temp);
						index_details.add(i, temp);
					} else {
						String temp1 = rst.getString(6);
						if (temp1 == null) {
							temp1 = "0.00";
						}
						temp1 = ad.indexcompose(temp1);
						index_details.add(i, temp1);
					}
				}
				i++;

				/*
				 * if (rst.getString(8) == null) { index_details.add(i, "0.00"); }
				 * else { String temp=dd.toString(); if(temp==null){
				 * temp="0.00"; } int k=temp.indexOf("."); if(k==-1){
				 * index_details.add(i,temp ); }else{ temp=temp+"00";
				 * temp=temp.substring(0,k+2); index_details.add(i, temp); } }
				 */
				index_details.add(i, strpchange);
				i++;

				if (rst.getString(8) == null) {
					index_details.add(i, "0.00");
				} else {
					String temp = rst.getString(8);
					if (temp == null) {
						temp = "0.00";
					}
					int k = temp.indexOf(".");
					if (k == -1) {
						index_details.add(i, temp);
					} else {
						temp = temp + "00";
						temp = temp.substring(0, k + 2);
						temp = ad.indexcompose(temp);
						index_details.add(i, temp);
					}
				}
				i++;
				if (rst.getString(9) == null) {
					index_details.add(i, "0.00");
				} else {
					String temp = rst.getString(9);
					if (temp == null) {
						temp = "0.00";
					}
					int k = temp.indexOf(".");
					if (k == -1) {
						index_details.add(i, temp);
					} else {
						temp = temp + "00";
						temp = temp.substring(0, k + 2);
						temp = ad.indexcompose(temp);
						index_details.add(i, temp);
					}
				}
				i++;
				if (rst.getString(10) == null) {
					index_details.add(i, "--");
				} else {
					index_details.add(i, rst.getString(10));
				}
				i++;
				if (rst.getString(11) == null) {
					index_details.add(i, "--");
				} else {
					index_details.add(i, rst.getString(11));
				}
				i++;

			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :" + sqlexp.getMessage());
		}
	}

	/**
	 * @return Returns the index_performance.
	 */
	public Vector getIndex_performance() {
		Logging.debug("Return Index_performance of size "
				+ index_performance.size());
		return index_performance;
	}

	/**
	 * @param index_performance
	 *            The index_performance to set.
	 */
	public void setIndex_performance(String dt_today) {
	//	AdjustDecimal ad = new AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		String m1date, m3date, m6date, y1date, year11;
		Logging.debug("Before if  " + dt_today);
		String year = dt_today.substring(6, 10);
		year11 = year;
		String month = dt_today.substring(3, 5);
		String day = dt_today.substring(0, 2);
		int dd = Integer.parseInt(day);
		if (dd >= 28) {
			dd = 28;
		}
		String day1 = (new Integer(dd)).toString();
		int mm = Integer.parseInt(month) - 01;
		if (mm == 0 || mm < 0) {
			mm = 12 + mm;
			int yy = Integer.parseInt(year) - 01;
			year11 = (new Integer(yy)).toString();
		}
		String month1 = (new Integer(mm)).toString();
		if (month1.length() == 1) {
			month1 = "0" + month1;
		}
		m1date = day1 + "-" + month1 + "-" + year11;
		mm = Integer.parseInt(month) - 03;
		if (mm == 0 || mm < 0) {
			mm = 12 + mm;
			int yy = Integer.parseInt(year) - 01;
			year11 = (new Integer(yy)).toString();
		}
		String month3 = (new Integer(mm)).toString();
		if (month3.length() == 1) {
			month3 = "0" + month3;
		}
		m3date = day1 + "-" + month3 + "-" + year11;
		mm = Integer.parseInt(month) - 06;
		if (mm == 0 || mm < 0) {
			mm = 12 + mm;
			int yy = Integer.parseInt(year) - 01;
			year11 = (new Integer(yy)).toString();
		}
		String month6 = (new Integer(mm)).toString();
		if (month6.length() == 1) {
			month6 = "0" + month6;
		}
		m6date = day1 + "-" + month6 + "-" + year11;
		mm = Integer.parseInt(year) - 01;
		String year1 = (new Integer(mm)).toString();
		y1date = day + "-" + month + "-" + year1;
		//Logging.getDebug(dt_today.equals(null));
		Logging.debug(dt_today + " " + m1date + " " + m3date + " "
				+ m6date + " " + y1date);
		index_performance = new Vector();
//		Connect con = new Connect();
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			con.getConnection();
		}
		rst = con.IndexPerformanceResult(
				"index_performance_over_month_quater_half_annual", dt_today,
				m1date, m3date, m6date, y1date,"1");
		int i = 0;
		Logging.debug("setIndex_performance of Compose Index" + rst);
		try {
			while (rst.next()) {
				if (rst.getString(1) == null) {
					index_performance.add(i, "0");
				} else {
					index_performance.add(i, rst.getString(1));
				}
				i++;

				//Logging.getDebug("inside while");
				if (rst.getString(2) == null) {
					index_performance.add(i, "--");
				} else {

					index_performance.add(i, rst.getString(2));

				}
				i++;

				if (rst.getString(4) == null) {
					index_performance.add(i, "0");
				} else {
					month = null;
					if ((double) rst.getDouble(3) != 0.00) {
						double per1 = ((double) rst.getDouble(4) / (double) rst
								.getDouble(3)) * 100;
						month = new Double(per1).toString();
						month = ad.indexcompose(month);
					} else {
						month = "0.00";
					}
					index_performance.add(i, month);
				}
				i++;

				if (rst.getString(5) == null) {
					index_performance.add(i, "0");
				} else {
					month = null;
					if ((double) rst.getDouble(3) != 0.00) {
						double per1 = ((double) rst.getDouble(5) / (double) rst
								.getDouble(3)) * 100;
						month = new Double(per1).toString();
						month = ad.indexcompose(month);
					} else {
						month = "0.00";
					}
					index_performance.add(i, month);
				}
				i++;

				if (rst.getString(6) == null) {
					index_performance.add(i, "0");
				} else {
					month = null;
					if ((double) rst.getDouble(3) != 0.00) {
						double per1 = ((double) rst.getDouble(6) / (double) rst
								.getDouble(3)) * 100;
						month = new Double(per1).toString();
						month = ad.indexcompose(month);
					} else {
						month = "0.00";
					}
					index_performance.add(i, month);
				}
				i++;

				if (rst.getString(7) == null) {
					index_performance.add(i, "0");
				} else {
					month = null;
					if ((double) rst.getDouble(3) != 0.00) {
						double per1 = ((double) rst.getDouble(7) / (double) rst
								.getDouble(3)) * 100;
						month = new Double(per1).toString();
						month = ad.indexcompose(month);
					} else {
						month = "0.00";
					}
					index_performance.add(i, month);
				}
				i++;
			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :" + sqlexp.getMessage());
		}

	}

	/**
	 * @return Returns the vector_stockcotriIndexchange.
	 */
	public Vector getVector_stockcotriIndexchange() {
		return vector_stockcotriIndexchange;
	}

	/**
	 * @param vector_stockcotriIndexchange
	 *            The vector_stockcotriIndexchange to set.
	 */
	public void setVector_stockcotriIndexchange(String index, String fodate,
			String todate) {
		Logging.debug("Inside Vector_stockcotriIndexchange");
		try {
			Vector date = new Vector();
	//		app.Connect con = new Connect();
			Connect con = ConnectInit.getConnect();
			if (Connect.con == null) {
				con.getConnection();
			}
			vector_stockcotriIndexchange = new Vector();
			//app.Logging.getDebug(index+" "+fodate+" "+" "+todate);
			rst = con.StockcontriIndexResult(
					"stock_contribution_to_change_in_index", index, fodate,
					todate);
			int i = 0, q = 0;
			Logging.debug("setVector_stockcotriIndexchange");
			while (rst.next()) {
				Logging.debug("inside first while end " + rst);
				try {
					//Logging.getDebug("get tring "+rst.getString(1));
					if (rst.getString(1) == null) {

						vector_stockcotriIndexchange.add(i, "--");
						Logging.debug("after get");
					} else {
						vector_stockcotriIndexchange.add(i, rst.getString(1)
								.trim());
						Logging.debug("befor get "
								+ vector_stockcotriIndexchange);
					}
				} catch (Exception e) {
					Logging.debug("Error while returning resultset"
							+ e.getMessage());

				}
				i++;

				if (rst.getString(2) == null) {
					vector_stockcotriIndexchange.add(i, "0");
				} else {

					String str = rst.getString(2);
					String str2 = str.substring(str.indexOf(46), (str
							.indexOf(46) + 3));
					String str1 = str.substring(0, str.indexOf(46)) + str2;
					vector_stockcotriIndexchange.add(i, str1);
					//Logging.getDebug((String)rst.getString(2));
				}
				i++;

				if (rst.getString(3) == null) {
					vector_stockcotriIndexchange.add(i, "0");
				} else {
					String str = rst.getString(3);
					String str2 = str.substring(str.indexOf(46), (str
							.indexOf(46) + 3));
					String str1 = str.substring(0, str.indexOf(46)) + str2;
					vector_stockcotriIndexchange.add(i, str1);
					//Logging.getDebug((String)rst.getString(3));
				}
				i++;
				if (rst.getString(4) == null) {
					vector_stockcotriIndexchange.add(i, "0");
				} else {
					vector_stockcotriIndexchange.add(i, rst.getString(4));
					//Logging.getDebug((String)rst.getString(4));
				}
				i++;
				date.add(q, rst.getString(5));
				q++;
				//Logging.getDebug((String)rst.getString(5));
				date.add(q, rst.getString(6));
				//Logging.getDebug((String)rst.getString(6));
				q++;
			}
			Logging.debug("" + vector_stockcotriIndexchange.size());
			/*
			 * for(int l=0;l <vector_stockcotriIndexchange.size();l++) { String
			 * str=(""+(String)vector_stockcotriIndexchange.get(l));
			 * app.Logging.getDebug(str); } while(rst.next()) {
			 * app.Logging.getDebug(rst.getString(1)+" "+rst.getString(2)+"
			 * "+rst.getString(3)+" "+rst.getString(4)+" "+rst.getString(5)+"
			 * "+rst.getString(6)); }
			 */
			Logging.debug("After first while end");
			vector_remStockid = new Vector();
			if (date.size() > 1) {
				tdate = (String) date.get(0);
				fdate = (String) date.get(1);
			}
			Logging.debug(index + "  " + fdate + " " + "  " + tdate);
			ResultSet rst1 = new Connect().StockcontriSidlResult(
					"stock_contribution_stock_id_left", index, fdate, tdate);
			int j = 0;
			while (rst1.next()) {
				vector_remStockid.add(j, rst1.getString(1));
			}
			if (vector_remStockid.size() != 0) {
				for (int k = 0; k < (vector_remStockid.size()); k++) {
					String s_id = (String) vector_remStockid.get(k);
					ResultSet rst2 = new Connect().stockcontriIndResult(
							"stock_contribution_to_change_in_index_individual",
							index, s_id, todate, fodate);
					Logging.debug("setVector_stockcotriIndexchange");

					while (rst2.next()) {

						if (rst.getString(1) == null) {
							vector_stockcotriIndexchange.add(i, "--");
						} else {
							vector_stockcotriIndexchange.add(i, rst
									.getString(1));
						}
						i++;

						if (rst.getString(2) == null) {
							vector_stockcotriIndexchange.add(i, "0");
						} else {

							String str = (String) rst.getString(2);
							String str2 = str.substring(str.indexOf(46), (str
									.indexOf(46) + 3));
							String str1 = str.substring(0, str.indexOf(46))
									+ str2;
							vector_stockcotriIndexchange.add(i, str1);
						}
						i++;

						if (rst.getString(3) == null) {
							vector_stockcotriIndexchange.add(i, "0");
						} else {
							String str = (String) rst.getString(3);
							String str2 = str.substring(str.indexOf(46), (str
									.indexOf(46) + 3));
							String str1 = str.substring(0, str.indexOf(46))
									+ str2;
							vector_stockcotriIndexchange.add(i, str1);
						}
						i++;
						if (rst.getString(4) == null) {
							vector_stockcotriIndexchange.add(i, "0");
						} else {
							vector_stockcotriIndexchange.add(i, rst
									.getString(4));
						}
						i++;
					}
				}
			}

		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :" + sqlexp.getMessage());
		}
	}

	/**
	 * @return Returns the vector_stock_performance.
	 */
	public Vector getVector_stock_performance() {
		return vector_stock_performance;
	}

	/**
	 * @param vector_stock_performance
	 *            The vector_stock_performance to set.
	 */
	public void setVector_stock_performance(String index, String fodate,
			String todate) {
		Logging.debug("Inside Vector_stock_performance");
		try {
	//		org.jfree.chart.demo.servlet.AdjustDecimal ad = new org.jfree.chart.demo.servlet.AdjustDecimal();
	//		Connect con = new Connect();
		
			Connect con = ConnectInit.getConnect();
			if (Connect.con == null) {
				con.getConnection();
			}
			vector_stock_performance = new Vector();
			vector_stock_performance1 = new Vector();
			Logging.debug(index + "  " + fodate + " " + "  " + todate);
			rst = con.StockPerformanceResult("stock_performance_report", index,
					fodate, todate);
			int i = 0;
			double tmcv = 0.00;
			Logging.debug("setVector_stock_performance");
			while (rst.next()) {
				if (rst.getString(1) == null) {
					vector_stock_performance1.add(i, "0");
				} else {
					vector_stock_performance1.add(i, rst.getString(1));
				}
				i++;

				if (rst.getString(2) == null) {
					vector_stock_performance1.add(i, "--");
				} else {
					vector_stock_performance1.add(i, rst.getString(2));
				}
				i++;

				if (rst.getString(3) == null) {
					vector_stock_performance1.add(i, "0");
				} else {
					double iscap = (double) rst.getDouble(3);
					iscap = iscap / 1000000.0;
					String striscap = new Double(iscap).toString();
					/*
					 * striscap=ad.shareholdingpatt(striscap);
					 * striscap=ad.indexcompose(striscap);
					 */
					vector_stock_performance1.add(i, striscap);
				}
				i++;
				if (rst.getString(4) == null) {
					vector_stock_performance1.add(i, "0");
				} else {
					double mcv = (double) rst.getDouble(4);
					mcv = mcv / 1000000.0;
					String strmcv = (String) (new Double(mcv).toString());
					/*
					 * app.Logging.getDebug("strmcv is "+strmcv);
					 * strmcv=ad.shareholdingpatt(strmcv);
					 * app.Logging.getDebug("strmcv is "+strmcv);
					 * strmcv=ad.indexcompose(strmcv);
					 * app.Logging.getDebug("strmcv is "+strmcv);
					 */
					vector_stock_performance1.add(i, strmcv);
					tmcv = tmcv + mcv;
				}
				i++;
			}
			Logging.debug("vector size "
					+ vector_stock_performance1.size() + " tmcv is " + tmcv);
			i = 0;
			int j = 0;
			while (j < vector_stock_performance1.size()) {
				vector_stock_performance.add(i,
						(String) vector_stock_performance1.get(j));
				i++;
				j++;
				vector_stock_performance.add(i,
						(String) vector_stock_performance1.get(j));
				i++;
				j++;
				vector_stock_performance.add(i,
						(String) vector_stock_performance1.get(j));
				i++;
				j++;
				vector_stock_performance.add(i,
						(String) vector_stock_performance1.get(j));
				double weightage = (((double) Double
						.parseDouble((String) vector_stock_performance1.get(j))) / tmcv) * 100;
				i++;
				vector_stock_performance.add(i, (new Double(weightage)
						.toString()));
				i++;
				j++;
			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :" + sqlexp.getMessage());
		}
	}

	/**
	 * @return Returns the vector_index_rv.
	 */
	public Vector getVector_index_rv() {
		return vector_index_rv;
	}

	/**
	 * @param vector_index_rv
	 *            The vector_index_rv to set.
	 */
	public void setVector_index_rv(String[] index, String fodate, String todate) {
		Logging.debug("Inside vector_index_rv");
		try {
	//		org.jfree.chart.demo.servlet.AdjustDecimal ad = new org.jfree.chart.demo.servlet.AdjustDecimal();
	//		app.Connect con = new app.Connect();
			AdjustDecimal ad = ConnectInit.getAdjustDecimal();
			Connect con = ConnectInit.getConnect();
			if (Connect.con == null) {
				con.getConnection();
			}
			vector_index_rv = new Vector();
			int j = 0;
			Logging.debug(index.length + "  " + fodate + " " + "  "
					+ todate);
			for (int k = 0; k < index.length; k++) {
				//vector_index_rv1.clear();
				vector_index_rv1 = new Vector();
				rst = con.StockPerformanceResult(
						"indexwise_returns_and_volatility", index[k], fodate,
						todate);
				Logging.debug("setVector_index_rv1");
				int i = 0;
				double tmcv = 0.00;
				while (rst.next()) {
					if (rst.getString(1) == null) {
						vector_index_rv1.add(i, "0");
					} else {
						vector_index_rv1.add(i, rst.getString(1));
					}
					i++;

					if (rst.getString(2) == null) {
						vector_index_rv1.add(i, "--");
					} else {
						vector_index_rv1.add(i, rst.getString(2));
					}
					i++;

					if (rst.getString(3) == null) {
						vector_index_rv1.add(i, "0");
					} else {
						vector_index_rv1.add(i, rst.getString(3));
						tmcv = tmcv
								+ (double) Double.parseDouble(rst.getString(3));
					}
					i++;
				}
				Logging.debug("vector size " + vector_index_rv1.size());
				int m = 0;
				if (vector_index_rv1.size() != 0) {
					vector_index_rv.add(j, (String) vector_index_rv1.get(m));
					m++;
					j++;
					vector_index_rv.add(j, (String) vector_index_rv1.get(m));
					m++;
					j++;
					Logging.debug("before getMonthlyReturns");
					double mr = getMonthlyReturns(vector_index_rv1);
					String mrstr = new Double(mr).toString();
					mrstr = ad.indexcompose(mrstr);
					vector_index_rv.add(j, mrstr);
					m++;
					j++;
					double volret = getAvgDailyVolatility(vector_index_rv1,
							tmcv);
					String volretstr = new Double(volret).toString();
					volretstr = ad.indexcompose(volretstr);
					vector_index_rv.add(j, volretstr);
					j++;
					Logging.debug("vector size 1 "
							+ vector_index_rv.size());
				}
			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :" + sqlexp.getMessage());
		}
	}

	public double getAvgDailyVolatility(Vector v, double indexmean) {
		Logging.debug("Inside getAvgDailyVolatility()");
		double sum_volatility = 0.0, vratio = 0.0, sum_indexvolatility = 0.0, sum_indexvalue = 0.0;
		Vector vol = new Vector();
		int i = 0, m = 0;
		indexmean = indexmean / (v.size() / 3);
		Logging.debug("indexmean " + indexmean);
		i = 0;
		while (i < v.size()) {
			i++;
			i++;
			double indval1 = (double) Double.parseDouble((String) v.get(i));
			Logging.debug("indval1 " + indval1 + " indexmean "
					+ indexmean);
			sum_indexvalue = (indval1 - indexmean);
			vol.add(new Double(sum_indexvalue).toString());
			Logging.debug("sum_indexvalue " + sum_indexvalue
					+ " sum_indexvolatility " + sum_indexvolatility);
			sum_indexvolatility = sum_indexvolatility + sum_indexvalue;
			Logging
					.debug(" sum_indexvolatility " + sum_indexvolatility);
			i++;
		}
		i = 0;
		while (i < vol.size()) {
			double mult1 = ((double) Double.parseDouble((String) vol.get(i)) - sum_indexvolatility);
			Logging.debug(" mult1 " + mult1);
			double mult = (((double) Double.parseDouble((String) vol.get(i)) - sum_indexvolatility) * ((double) Double
					.parseDouble((String) vol.get(i)) - sum_indexvolatility));
			Logging.debug(" sum_volatility " + sum_volatility
					+ " mult " + mult);
			sum_volatility = sum_volatility + mult;
			i++;
		}
		vratio = 1.00 / (((vol.size())));
		Logging.debug(" sum_volatility " + sum_volatility);
		double avgdailyvol = (Math.sqrt((vratio * sum_volatility)));
		Logging.debug("vector size " + v.size() + " avgdailyvol is "
				+ avgdailyvol);
		return avgdailyvol;
		//return (sum_stockvolatility/(v_stock.size()/3));
	}

	public double getMonthlyReturns(Vector v) {
		Logging.debug("Inside getMonthlyReturns()");
		double mreturn = 0.0, lmr = 0.0, fmr = 0.0;
		int l = v.size();
		Logging.debug("Inside getMonthlyReturns()" + l);
		if (v.size() != 0) {
			lmr = (double) Double.parseDouble((String) v.get(l - 1));
			fmr = (double) Double.parseDouble((String) v.get(2));
		}
		Logging.debug(" l size " + l + " lmr " + lmr + " fmr" + fmr);
		if (fmr != 0.00) {
			mreturn = ((lmr - fmr) / fmr);
		} else {
			mreturn = 0.00;
		}
		Logging.debug("mreturn " + mreturn);
		return mreturn;
	}

	/**
	 * method to generate the chart list and set the given chart name selected.
	 * 
	 * @param id
	 * @return StringBuffer
	 */
	public static StringBuffer getChartList(String id) {
		Vector chart_list = new Vector();
		chart_list.add(0, "0");
		chart_list.add(1, "Not Selected");
		chart_list.add(2, "1");
		chart_list.add(3, "Line Chart");
		chart_list.add(4, "2");
		chart_list.add(5, "Bar Chart");
		chart_list.add(6, "3");
		chart_list.add(7, "Area Chart");
		chart_list.add(8, "4");
		chart_list.add(9, "Moving Average Chart");

		StringBuffer buffer = new StringBuffer();
		try {
			String id1 = null;
			//String id2=id;
			int k = 0;
			Logging.debug("Param Value is ListTypeClass " + id);
			while (k < chart_list.size()) {
				id1 = (String) chart_list.get(k);
				k++;
				if (id != null) {
					if (id.equals(id1)) {
						Logging.debug("inside param equals id");
						buffer.append("<option selected value=" + id1 + ">"
								+ (String) chart_list.get(k) + "</option>");
					} else {
						buffer.append("<option value=" + id1 + ">"
								+ (String) chart_list.get(k) + "</option>");
					}

				} else {
					buffer.append("<option  value=" + id1 + ">"
							+ (String) chart_list.get(k) + "</option>");
				}
				k++;
			}
		} catch (Exception e) {
			Logging.debug("ListTypeClass:Error in CreateStatement "
					+ e.getMessage());
		}

		//	Logging.debug(buffer.toString());

		return buffer;
	}

	/**
	 * method to generate the chart list and set the given chart name selected.
	 * 
	 * @param id
	 * @return StringBuffer
	 */
	/*public static void DisplayChart(String report, HttpServletRequest request,
			String mavg, PrintWriter pw) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.UK);
		String var = request.getParameter("D1");
		String var1 = request.getParameter("D2");
		String var3 = request.getParameter("D3");
		filename = null;
		graphURL = null;
		if (var3 == null)
			var3 = "4";
		Logging.getDebug(" var3 in displayChart : " + var3);
		String fromdate = request.getParameter("from");
		String toDate = request.getParameter("to");
		Date dDate = null;
		try {
			dDate = sdf.parse("01-Aug-2002");
		} catch (Exception e) {
			Logging.getDebug(" Error : " + e.getMessage());
		}
		StringBuffer buffer = new StringBuffer();
		try {
			Logging.debug("var is" + var);
			if ((var3.equals("2"))) {
				//Logging.getDebug("in bar chart");
				if (report.equals("maverage")) {
					DatasetFactory1.Readdata(var, fromdate, toDate);
					filename = CombinedChart1.generateChart(dDate, request
							.getSession(), pw, var, "b");
				}
				if (report.equals("inddivisor")) {
					DatasetFactory1.DivisorReaddata(var, fromdate, toDate);
					filename = CombinedChart1.generateChart(dDate, request
							.getSession(), pw, var, "b");
				}
				graphURL = request.getContextPath()
						+ "/servlet/DisplayChart?filename=" + filename;
				Logging.debug("graphURL is" + graphURL);
				Logging.debug("filename is" + filename);
				buffer.append("<img src='" + graphURL
						+ "' width='700' height='500' border='0' usemap='#"
						+ filename + "' >");
			}
			if ((var3.equals("1"))) {
				//Logging.getDebug("in line chart");
				if (report.equals("maverage")) {
					DatasetFactory1.Readdata(var, fromdate, toDate);
				}
				if (report.equals("inddivisor")) {
					DatasetFactory1.DivisorReaddata(var, fromdate, toDate);
				}
				filename = CombinedChart1.generateChart(dDate, request
						.getSession(), pw, var, "l");
				graphURL = request.getContextPath()
						+ "/servlet/DisplayChart?filename=" + filename;
				Logging.debug("graphURL is" + graphURL);
				Logging.debug("filename is" + filename);
				buffer.append("<img src='" + graphURL
						+ "' width='700' height='500' border='0' usemap='#"
						+ filename + "' >");
			}
			if ((var3.equals("4"))) {
				Logging.getDebug("in moving average chart");
				if (report.equals("maverage")) {
					DatasetFactory1.Readdata(var, fromdate, toDate);
				}
				if (report.equals("inddivisor")) {
					DatasetFactory1.DivisorReaddata(var, fromdate, toDate);
				}
				filename = CMovingAverage.generatePieChart(dDate, request
						.getSession(), pw, var1, mavg);
				graphURL = request.getContextPath()
						+ "/servlet/DisplayChart?filename=" + filename;
				Logging.debug("graphURL is" + graphURL);
				Logging.debug("filename is" + filename);
				buffer.append("<img src='" + graphURL
						+ "' width='700' height='500' border='0' usemap='#"
						+ filename + "' >");
			}
			if ((var3.equals("3"))) {
				//Logging.getDebug("in area chart");
				if (report.equals("maverage")) {
					DatasetFactory1.Readdata(var, fromdate, toDate);
				}
				if (report.equals("inddivisor")) {
					DatasetFactory1.DivisorReaddata(var, fromdate, toDate);
				}
				//Logging.getDebug("before filename");
				filename = AreaChart.generateChart(dDate, request.getSession(),
						pw, var);
				//Logging.getDebug("after filename"+filename);
				graphURL = request.getContextPath()
						+ "/servlet/DisplayChart?filename=" + filename;
				Logging.debug("graphURL is" + graphURL);
				Logging.debug("filename is" + filename);
				buffer.append("<img src='" + graphURL
						+ "' width='700' height='500' border='0' usemap='#"
						+ filename + "' >");
			}
		} catch (Exception e) {
			Logging.debug("Error : " + e.getMessage());
		}

		//	Logging.debug(buffer.toString());

		//	return buffer;
	}*/

	/**
	 * @return Returns the vector_indtable.
	 */
	public Vector getVector_indtable() {
		return vector_indtable;
	}

	/**
	 * @param vector_indtable
	 *            The vector_indtable to set.
	 */
	public void setVector_indtable(String index, String date) {
		String index1, index2;
	//	Connect con = new Connect();
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		vector_indtable = new Vector();
		int i = 0;
		Logging.debug("setVector_indtable of Industry wise weightage");
		try {
			if (connection == null)
				connection = con.getdbConnection();
			rst = con.indwtResult(connection, "industry_wise_weightage", index,
					date);
			while (rst.next()) {

				if (rst.getString(1) == null) {
					vector_indtable.add(i, "--");
				} else {
					vector_indtable.add(i, rst.getString(1));
				}
				i++;

				if (rst.getString(2) == null) {
					vector_indtable.add(i, "--");
				} else {
					vector_indtable.add(i, rst.getString(2));
				}
				i++;

				if (rst.getString(3) == null) {
					vector_indtable.add(i, "--");
				} else {
					vector_indtable.add(i, rst.getString(3));
				}
				i++;
			}
			rst.close();
		} catch (Exception sqlexp) {
			Logging.error(" Error : " + sqlexp.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ex) {
				Logging.error(" Error : Unable to close Connection "
						+ ex.getMessage());
			}
		}
	}

	/**
	 * @return Returns the vector_IdentifierCodeBelogsTo.
	 */
	public Vector getIdentifierCodeBelogsTo() {
		return vector_IdentifierCodeBelogsTo;
	}

	/**
	 * @param vector_indtable
	 *            The vector_indtable to set.
	 */
	public void setIdentifierCodeBelogsTo(String sedol, String isin,
			String ric, String crisil, String cusip, String exchange_code,
			String ticker, String stockid) {
		Vector icode_list = new Vector();
		int l = -1;
		if (sedol != null && !sedol.equals("")) {
			l++;
			icode_list.add(l, sedol);
		}
		if (isin != null && !isin.equals("")) {
			l++;
			icode_list.add(l, isin);
		}
		if (ric != null && !ric.equals("")) {
			l++;
			icode_list.add(l, ric);
		}
		if (crisil != null && !crisil.equals("")) {
			l++;
			icode_list.add(l, crisil);
		}
		if (cusip != null && !cusip.equals("")) {
			l++;
			icode_list.add(l, cusip);
		}
		if (exchange_code != null && !exchange_code.equals("")) {
			l++;
			icode_list.add(l, exchange_code);
		}
		if (ticker != null && !ticker.equals("")) {
			l++;
			icode_list.add(l, ticker);
		}
	//	Connect con = new Connect();
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		java.sql.ResultSet rs = null;
		
		
		vector_IdentifierCodeBelogsTo = new Vector();

		int i = 0;
		Logging
				.debug("setVector_IdentifierCodeBelogsTo of setIdentifierCodeBelogsTo");
		try {
			  if (connection == null) {
				connection = con.getdbConnection();
			  }
			for (int m = 0; m < icode_list.size(); m++) {
				rs = con.getStockList(connection,"identifier_code_belongs_to",
						(String) icode_list.get(m));
				while (rs.next()) {
					if (!(rs.getString(2).equals(stockid))) {
						if (rs.getString(1) == null) {
							vector_IdentifierCodeBelogsTo.add(i, "--");
						} else {
							vector_IdentifierCodeBelogsTo.add(i, rs
									.getString(1));
						}
						i++;

						if (rs.getString(2) == null) {
							vector_IdentifierCodeBelogsTo.add(i, "0");
						} else {
							vector_IdentifierCodeBelogsTo.add(i, rs
									.getString(2));
						}
						i++;

						if (rs.getString(3) == null) {
							vector_IdentifierCodeBelogsTo.add(i, "--");
						} else {
							vector_IdentifierCodeBelogsTo.add(i, rs
									.getString(3));
						}
						i++;
						if (rs.getString(4) == null) {
							vector_IdentifierCodeBelogsTo.add(i, "--");
						} else {
							vector_IdentifierCodeBelogsTo.add(i, rs
									.getString(4));
						}
						i++;
						if (rs.getString(5) == null) {
							vector_IdentifierCodeBelogsTo.add(i, "--");
						} else {
							vector_IdentifierCodeBelogsTo.add(i, rs
									.getString(5));
						}
						i++;

						if (rs.getString(6) == null) {
							vector_IdentifierCodeBelogsTo.add(i, "--");
						} else {
							vector_IdentifierCodeBelogsTo.add(i, rs
									.getString(6));
						}
						i++;
					}
				}
				
			}
			rs.close();
		} catch (SQLException sqlexp) {
			Logging.error("Error : " + sqlexp.getMessage());
		}finally{
			try{if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
	}

	/**
	 * @return Returns the vector_indtable.
	 */
	public Vector getVector_indweighttable() {
		return vector_indweighttable;
	}

	/**
	 * @param vector_indtable
	 *            The vector_indtable to set.
	 */
	public void setVector_indweighttable(String index) {
		String index1, index2;
		index1 = index2 = index;
	//	app.Connect con = new Connect();
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			con.getConnection();
		}
		vector_indweighttable = new Vector();
		rst = con.indweightResult("industry_wise_weightage", index);
		int i = 0;
		Logging
				.debug("setVector_indweighttable of Industry wise weightage");
		try {

			while (rst.next()) {

				if (rst.getString(1) == null) {
					vector_indweighttable.add(i, "--");
				} else {
					vector_indweighttable.add(i, rst.getString(1));
				}
				i++;

				if (rst.getString(2) == null) {
					vector_indweighttable.add(i, "--");
				} else {
					vector_indweighttable.add(i, rst.getString(2));
				}
				i++;

				if (rst.getString(3) == null) {
					vector_indweighttable.add(i, "--");
				} else {
					vector_indweighttable.add(i, rst.getString(3));
				}
				i++;
			}
		} catch (SQLException sqlexp) {
			Logging.error("Error : " + sqlexp.getMessage());
		}
	}

	/**
	 * @return Returns the vector_Listedcompany.
	 */
	public Vector getVector_Listedcompany() {
		return vector_Listedcompany;
	}

	/**
	 * @param vector_Listedcompany
	 *            The vector_Listedcompany to set.
	 */
	public void setVector_Listedcompany(String index) {
	//	app.Connect con = new app.Connect();
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			con.getConnection();
		}
		vector_Listedcompany = new Vector();
		rst = con.returnResult("stock_listing", index);
		int i = 0;
		Logging.debug("setVector_Listedcompany ");
		try {

			while (rst.next()) {

				if (rst.getString(1) == null) {
					vector_Listedcompany.add(i, "--");
				} else {
					vector_Listedcompany.add(i, rst.getString(1));
				}
				i++;

				if (rst.getString(2) == null) {
					vector_Listedcompany.add(i, "--");
				} else {
					vector_Listedcompany.add(i, rst.getString(2));
				}
				i++;
			}
		} catch (SQLException sqlexp) {
			Logging.error("Error : " + sqlexp.getMessage());
		}
	}

	/**
	 * @return Returns the vector_latestdivisor.
	 */
	public Vector getVector_latestdivisor() {
		return vector_latestdivisor;
	}

	/**
	 * @param vector_latestdivisor
	 *            The vector_latestdivisor to set.
	 */
	public void setVector_latestdivisor() {

		Logging.debug("Inside latestdivisor");
	//	app.Connect con = new app.Connect();
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			con.getConnection();
		}
		vector_latestdivisor = new Vector();
		rst = con.divisorResult("get_all_latest_divisor","1");
		/*
		 * app.Connect c = ConnectInit.getConnect(); java.sql.Connection con1
		 * =c.getConnection(); Statement st = con1.createStatement(); String
		 * query=c.queries.getProperty("get_all_latest_divior");
		 * rst=st.executeQuery(query);
		 */
		int i = 0;
		Logging
				.debug("setVector_latestdivisor of list of latest divisor");
		try {
			while (rst.next()) {

				if (rst.getString(1) == null) {
					vector_latestdivisor.add(i, "--");
				} else {
					vector_latestdivisor.add(i, rst.getString(1));
				}
				i++;

				if (rst.getString(2) == null) {
					vector_latestdivisor.add(i, "--");
				} else {
					vector_latestdivisor.add(i, rst.getString(2));
				}
				i++;

				if (rst.getString(3) == null) {
					vector_latestdivisor.add(i, "--");
				} else {
					vector_latestdivisor.add(i, rst.getString(3));
				}
				i++;
			}
		} catch (SQLException sqlexp) {
			Logging.error("Error : " + sqlexp.getMessage());
		}
	}

	/**
	 * @return Returns the vector_capitalchangetouniv.
	 */
	public Vector getVector_capitalchangetouniv() {
		return vector_capitalchangetouniv;
	}

	/**
	 * @param vector_capitalchangetouniv
	 *            The vector_capitalchangetouniv to set.
	 */
	public void setVector_capitalchangetouniv(String fodate, String todate,
			String ind_id) {

		Logging.debug("Inside vector_capitalchangetouniv");
	//	app.Connect con = new app.Connect();
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			con.getConnection();
		}
		vector_capitalchangetouniv = new Vector();
		rst = con.highlowResultmktStatus("capital_change_to_universe", ind_id,
				fodate, todate);
		/*
		 * app.Connect c = ConnectInit.getConnect(); java.sql.Connection con1
		 * =c.getConnection(); Statement st = con1.createStatement(); String
		 * query=c.queries.getProperty("get_all_latest_divior");
		 * rst=st.executeQuery(query);
		 */
		int i = 0;
		Logging
				.debug("set vector_capitalchangetouniv of capital change to universe");
		try {
			while (rst.next()) {

				if (rst.getString(1) == null) {
					vector_capitalchangetouniv.add(i, "--");
				} else {
					vector_capitalchangetouniv.add(i, rst.getString(1));
				}
				i++;

				if (rst.getString(2) == null) {
					vector_capitalchangetouniv.add(i, "--");
				} else {
					vector_capitalchangetouniv.add(i, rst.getString(2));
				}
				i++;

				if (rst.getString(3) == null) {
					vector_capitalchangetouniv.add(i, "--");
				} else {
					vector_capitalchangetouniv.add(i, rst.getString(3));
				}
				i++;
				if (rst.getString(4) == null) {
					vector_capitalchangetouniv.add(i, "0.00");
				} else {
					vector_capitalchangetouniv.add(i, rst.getString(4));
				}
				i++;

				if (rst.getString(5) == null) {
					vector_capitalchangetouniv.add(i, "0");
				} else {
					vector_capitalchangetouniv.add(i, rst.getString(5));
				}
				i++;
				if (rst.getString(6) == null) {
					vector_capitalchangetouniv.add(i, "--");
				} else {
					vector_capitalchangetouniv.add(i, rst.getString(6));
				}
				i++;
				if (rst.getString(8) == null) {
					vector_capitalchangetouniv.add(i, "--");
				} else {
					vector_capitalchangetouniv.add(i, rst.getString(8));
				}
				i++;
				if (rst.getString(7) == null) {
					vector_capitalchangetouniv.add(i, "--");
				} else {
					vector_capitalchangetouniv.add(i, rst.getString(7));
				}
				i++;
			}
		} catch (SQLException sqlexp) {
			Logging.error("Error : " + sqlexp.getMessage());
		}
	}

	/**
	 * The vector_stockDivident to get.
	 * 
	 * @return vector_stockDivident
	 */
	public Vector getVector_stockDivident() {
		return vector_stockDivident;
	}

	/**
	 * The vector_stockDivident to set.
	 * 
	 * @param fodate
	 * @param todate
	 * @param exch_id
	 * @param ind_id
	 */
	public void setVector_stockDivident(String fodate, String todate,
			String exch_id, String ind_id) {
		ResultSet rst = null;
	//	org.jfree.chart.demo.servlet.AdjustDecimal ad = new org.jfree.chart.demo.servlet.AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		Logging
				.debug("Inside setVector_stockDivident exchange_id and indexid is "
						+ exch_id + " , " + ind_id);
	//	Connect con = new app.Connect();
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			con.getConnection();
		}
		try {
			vector_stockDivident = new Vector();
			vector_stockDivident.clear();
			Logging.debug("before firing query exchange  " + exch_id
					+ " index " + ind_id + " from date" + fodate + "  todate "
					+ todate);
			if (exch_id != null && !(exch_id.equals("null"))) {
				Logging.debug("in stock_divident_exchange_wise "
						+ exch_id);
				rst = con
						.changeInStockDetailResult(
								"stock_divident_exchange_wise", exch_id,
								fodate, todate);
			} else {
				if (ind_id != null && !(ind_id.equals("null"))) {
					Logging.debug("in stock_divident_index_wise "
							+ ind_id);
					rst = con
							.changeInStockDetailResult(
									"stock_divident_index_wise", ind_id,
									fodate, todate);
				}
			}
			int i = 0;
			//app.Logging.getDebug("set vector_stockDivident of stock
			// divident"+rst);

			while (rst.next()) {

				if (rst.getString(1) == null) {
					vector_stockDivident.add(i, "0");
				} else {
					vector_stockDivident.add(i, rst.getString(1));
				}
				i++;

				if (rst.getString(2) == null) {
					vector_stockDivident.add(i, "--");
				} else {
					vector_stockDivident.add(i, rst.getString(2));
				}
				i++;

				if (rst.getString(3) == null) {
					vector_stockDivident.add(i, "0");
				} else {
					vector_stockDivident.add(i, rst.getString(3));
				}
				i++;
				if (rst.getString(4) == null) {
					vector_stockDivident.add(i, "0");
				} else {
					vector_stockDivident.add(i, rst.getString(4));
				}
				i++;

				if (rst.getString(5) == null) {
					vector_stockDivident.add(i, "0.00");
				} else {
					String ad1 = (String) rst.getString(5);
					ad1 = ad.indexcompose(ad1);
					vector_stockDivident.add(i, ad1);
				}
				i++;
				if (rst.getString(6) == null) {
					vector_stockDivident.add(i, "0.00");
				} else {
					vector_stockDivident.add(i, rst.getString(6));
				}
				i++;
				if (rst.getString(7) == null) {
					vector_stockDivident.add(i, "--");
				} else {
					vector_stockDivident.add(i, rst.getString(7));
				}
				i++;
			}
			Logging.debug("in compose afeter populating vector"
					+ vector_stockDivident.size());
		} catch (SQLException sqlexp) {
			Logging.error("Error : " + sqlexp.getMessage());
		}
	}

	/**
	 * The vector_stockDivident to get.
	 * 
	 * @return vector_stockDivident
	 */
	public Vector getVector_traded_volume() {
		return vector_tradedvol;
	}

	/**
	 * vector traded volume to set.
	 * 
	 * @param fodate
	 * @param todate
	 * @param exch_id
	 * @param ind_id
	 */
	public void setVector_traded_volume(String fodate, String todate,
			String exch_id, String ind_id, String trd_volume) {
		ResultSet rst = null;
	//	org.jfree.chart.demo.servlet.AdjustDecimal ad = new org.jfree.chart.demo.servlet.AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		Logging
				.debug("Inside setVector_traded_volume exchange_id and indexid is "
						+ exch_id + " , " + ind_id);
	//	app.Connect con = new app.Connect();
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			con.getConnection();
		}
		try {
			vector_tradedvol = new Vector();
			//vector_tradedvol.clear();
			if (trd_volume == null || trd_volume.equals("null")) {
				trd_volume = "0";
			}
			Logging.debug("fodate.length() is " + fodate.length()
					+ " todate.length()" + todate.length());
			Logging.debug("before firing query exchange  " + exch_id
					+ " index " + ind_id + " from date" + fodate + "  todate "
					+ todate);
			if (((fodate != null || fodate.trim().equals("")) && fodate
					.length() != 0)
					&& ((todate != null || todate.trim().equals("")) && todate
							.length() != 0)) {
				Logging.debug("before firing query exchange  "
						+ exch_id + " index " + ind_id + " from date" + fodate
						+ "  todate " + todate);
				if (exch_id != null && !(exch_id.equals("null"))
						&& !(exch_id.equals("0")) && (ind_id == null)) {
					Logging
							.debug("in traded_volume_list_exchange_wise "
									+ exch_id);
					rst = con.tradedVolumeResult(
							"traded_volume_list_exchange_wise", exch_id,
							trd_volume, fodate, todate);
				} else {
					if (ind_id != null && !(ind_id.equals("null"))
							&& !(ind_id.equals("0")) && (exch_id == null)) {
						Logging
								.debug("in traded_volume_list_index_wise "
										+ ind_id);
						rst = con.tradedVolumeResult(
								"traded_volume_list_index_wise", ind_id,
								trd_volume, fodate, todate);
					}
				}
				Logging.debug("rst in traded volume is " + rst);
				int i = 0;
				while (rst.next()) {

					if (rst.getString(1) == null) {
						vector_tradedvol.add(i, "0");
					} else {
						vector_tradedvol.add(i, rst.getString(1));
					}
					i++;

					if (rst.getString(2) == null) {
						vector_tradedvol.add(i, "--");
					} else {
						vector_tradedvol.add(i, rst.getString(2));
					}
					i++;
					if (rst.getString(3) == null) {
						vector_tradedvol.add(i, "0.00");
					} else {
						String ad1 = (String) rst.getString(3);
						ad1 = ad.indexcompose(ad1);
						vector_tradedvol.add(i, ad1);
					}
					i++;
					Logging.debug("vector counr is " + i);
				}
			}
			Logging.debug("in compose after populating vector "
					+ vector_tradedvol.size());
		} catch (Exception sqlexp) {
			Logging.error("Error : " + sqlexp.getMessage());
		}
	}

	/**
	 * @return Returns the vector_marketstatus.
	 */
	public Vector getVector_marketstatus() {
		return vector_marketstatus;
	}

	/**
	 * @param vector_latestdivisor
	 *            The vector_latestdivisor to set.
	 */
	public void setVector_marketstatus(int status){
			//, String nuResults) {

		//Logging.getDebug("Inside vector_marketstatus:d2 value is:"
			//	+ nuResults);
		Logging.debug("Inside vector_marketstatus");
	//	Connect con = new Connect();
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			con.getConnection();
		}
		vector_marketstatus = new Vector();
		if (status == 1) {
			Logging.debug("status is " + status);
			rst = con.divisorResult("stock_status_gain","1");
		}
		if (status == 2) {
			Logging.debug("" + status);
			rst = con.divisorResult("stock_status_loss","1");
		}
		int i = 0;
		Logging
				.debug("setVector_latestdivisor of list of latest divisor");
		try {
			while (rst.next()) {

				if (rst.getString(1) == null) {
					vector_marketstatus.add(i, "--");
				} else {
					vector_marketstatus.add(i, rst.getString(1));
				}
				i++;

				if (rst.getString(2) == null) {
					vector_marketstatus.add(i, "0");
				} else {
					vector_marketstatus.add(i, rst.getString(2));
				}
				i++;

				if (rst.getString(3) == null) {
					vector_marketstatus.add(i, "0");
				} else {
					vector_marketstatus.add(i, rst.getString(3));
				}
				i++;
			}
		} catch (SQLException sqlexp) {
			Logging.error("Error : " + sqlexp.getMessage());
		}
	}

	/**
	 * @return Returns the vector_marketstatus.
	 */
	public Vector getVector_marketstatusdatewise() {
		return vector_marketstatusdatewise;
	}

	/**
	 * @param vector_latestdivisor
	 *            The vector_latestdivisor to set.
	 */
	public void setVector_marketstatusdatewise(int status, String fodate,
			String todate, String limit, String criteria) {
		Logging.debug("Inside vector_marketstatusdatewise");
//	app.Connect con = new app.Connect();
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			con.getConnection();
		}
		vector_marketstatusdatewise = new Vector();
		Logging.debug(fodate + " " + "  " + todate + "  " + limit);
		if (criteria.equals("L.T.P.")) {
			if (status == 1) {
				Logging.debug("" + status);
				rst = con.highlowResultmktStatus(
						"stock_status_gain_between_date", limit.trim(), fodate,
						todate);
			}
			if (status == 2) {
				Logging.debug("" + status);
				rst = con.highlowResultmktStatus(
						"stock_status_loss_between_date", limit.trim(), fodate,
						todate);
			}
		} else {
			Logging.debug("Inside traded volume loop");
			if (status == 1) {
				Logging.debug("" + status);
				rst = con.highlowResultmktStatus("stock_status_top_traded",
						limit.trim(), fodate, todate);
			}
			if (status == 2) {
				Logging.debug("" + status);
				rst = con.highlowResultmktStatus("stock_status_least_traded",
						limit.trim(), fodate, todate);
			}
		}
		int i = 0;
		Logging
				.debug("setVector_latestdivisor of list of latest divisor");
		try {
			while (rst.next()) {

				if (rst.getString(1) == null) {
					vector_marketstatusdatewise.add(i, "--");
				} else {
					vector_marketstatusdatewise.add(i, rst.getString(1));
				}
				i++;

				if (rst.getString(2) == null) {
					vector_marketstatusdatewise.add(i, "0");
				} else {
					vector_marketstatusdatewise.add(i, rst.getString(2));
				}
				i++;

				if (rst.getString(3) == null) {
					vector_marketstatusdatewise.add(i, "0");
				} else {
					vector_marketstatusdatewise.add(i, rst.getString(3));
				}
				i++;
			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error : " + sqlexp.getMessage());
		}
	}

	/**
	 * @return Returns the vector_delistedcompanies.
	 */
	public Vector getVector_delistedcompanies() {
		return vector_delistedcompanies;
	}

	/**
	 * @param vector_latestdivisor
	 *            The vector_latestdivisor to set.
	 */
	public void setVector_delistedcompanies(int status, String fodate,
			String todate, String exchangeid) {
		Logging.debug("Inside vector_delistedcompanies");
//		Connect con = new app.Connect();
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			con.getConnection();
		}
		vector_delistedcompanies = new Vector();
		Logging.debug(fodate + " " + "  " + todate + "  " + exchangeid);
		if (status == 1) {
			Logging.debug("" + status);
			rst = con.returnResult("stock_delisting", exchangeid);
		}
		if (status == 2) {
			Logging.debug("" + status);
			rst = con.delistingResult("stock_delisting_between_date",
					exchangeid, fodate, todate);
		}
		/*
		 * try { while (rst.next()) {
		 * Logging.getDebug((String)rst.getString(1));
		 *  } /*app.Connect c = ConnectInit.getConnect(); java.sql.Connection con1
		 * =c.getConnection(); Statement st = con1.createStatement(); String
		 * query=c.queries.getProperty("get_all_latest_divior");
		 * rst=st.executeQuery(query);
		 */
		int i = 0;
		Logging.debug("setVector_delistedcompanies ");
		try {
			while (rst.next()) {

				if (rst.getString(1) == null) {
					vector_delistedcompanies.add(i, "--");
				} else {
					vector_delistedcompanies.add(i, rst.getString(1));
				}
				i++;

				if (rst.getString(2) == null) {
					vector_delistedcompanies.add(i, "--");
				} else {
					vector_delistedcompanies.add(i, rst.getString(2));
				}
				i++;
			}
		} catch (SQLException sqlexp) {
			Logging.error("Error : " + sqlexp.getMessage());
		}
	}

	/**
	 * @return Returns the vector_stocklist.
	 */
	public Vector getVector_stocklist() {
		return vector_stocklist;
	}

	/**
	 * @param vector_stocklist
	 *            The vector_stocklist to set.
	 */
	public void setVector_stocklist(String indexid) {
	//	Connect con = new app.Connect();
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		ResultSet rs = null;
		vector_stocklist = new Vector();
		
		int i = 0;
		obj = new Object[1][2];
		try {
			Logging.debug("inside stock list");
			
			if (connection  == null) {
				connection = con.getdbConnection();
			}
			rs = con.getStockList(connection,"index_wise_stock_name", indexid);
			while (rs.next()) {
				if (rs.getString(1) == null) {
					vector_stocklist.add(i, "0");
				} else {
					vector_stocklist.add(i, rs.getString(1));
				}
				i++;
				if (rs.getString(2) == null) {
					vector_stocklist.add(i, "--");
				} else {
					vector_stocklist.add(i, rs.getString(2));
				}
				i++;
			}
			rs.close();
		} catch (SQLException sqlexp) {
			Logging.error("Error : " + sqlexp.getMessage());
		}finally{
			try{if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
	}

	/**
	 * @param vector_stocklist
	 *            The vector_stocklist to set.
	 */
	public void setVector_stocklist() {
	//	Connect con = new app.Connect();
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			con.getConnection();
		}
		vector_stocklist = new Vector();
		rst = con.getClientList("stock_name_list");
		int i = 0;
		obj = new Object[1][2];
		try {

			while (rst.next()) {
				if (rst.getString(1) == null) {
					vector_stocklist.add(i, "0");
				} else {
					vector_stocklist.add(i, rst.getString(1));
				}
				i++;
				if (rst.getString(2) == null) {
					vector_stocklist.add(i, "--");
				} else {
					vector_stocklist.add(i, rst.getString(2));
				}
				i++;
			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error : " + sqlexp.getMessage());
		}
	}

	/**
	 * @return Returns the vector_ExchangeList.
	 */
	public Vector getVector_ExchangeList() {
		return vector_ExchangeList;
	}

	/**
	 * @param vector_stocklistexcwise
	 *            The vector_stocklistexcwise to set.
	 */
	public void setVector_ExchangeList() {
	//	app.Connect con = new app.Connect();
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			con.getConnection();
		}
		vector_ExchangeList = new Vector();
		rst = con.getClientList("stock_exchange_list");
		int i = 0;
		obj = new Object[1][2];
		try {

			while (rst.next()) {
				if (rst.getString(1) == null) {
					vector_ExchangeList.add(i, "0");
				} else {
					vector_ExchangeList.add(i, rst.getString(1));
				}
				i++;
				if (rst.getString(2) == null) {
					vector_ExchangeList.add(i, "--");
				} else {
					vector_ExchangeList.add(i, rst.getString(2));
				}
				i++;
			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error : " + sqlexp.getMessage());
		}
	}

	/**
	 * @return Returns the vector_stocklistexcwise.
	 */
	public Vector getVector_stocklistexcwise() {
		return vector_stocklistexcwise;
	}

	/**
	 * @param vector_stocklistexcwise
	 *            The vector_stocklistexcwise to set.
	 */
	public void setVector_stocklistexcwise(String exchangeid) {
	//	app.Connect con = new app.Connect();
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		ResultSet rs = null;
		
		vector_stocklistexcwise = new Vector();
		
		int i = 0;
		obj = new Object[1][2];
		try {
			if (connection == null) {
				connection = con.getdbConnection();
			}
			rst = con.getStockList(connection,"stock_name_list_exchange_wise", exchangeid);
			while (rs.next()) {
				if (rs.getString(1) == null) {
					vector_stocklistexcwise.add(i, "0");
				} else {
					vector_stocklistexcwise.add(i, rs.getString(1));
				}
				i++;
				if (rs.getString(2) == null) {
					vector_stocklistexcwise.add(i, "--");
				} else {
					vector_stocklistexcwise.add(i, rs.getString(2));
				}
				i++;
			}
			 rs.close();
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error : " + sqlexp.getMessage());
		}finally{
			try{if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
	}

	/**
	 * @return Returns the vector_stockchangeTypeklist.
	 */
	public Vector getVector_stockchangeTypelist() {
		return vector_stockchangeTypelist;
	}

	/**
	 * @param vector_stocklist
	 *            The vector_stockchangeTypeklist to set.
	 */
	public void setVector_stockchangeTypelist() {
	//	app.Connect con = new app.Connect();
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			con.getConnection();
		}
		vector_stockchangeTypelist = new Vector();
		rst = con.getClientList("stock_change_types_list");
		int i = 0;

		try {

			while (rst.next()) {
				vector_stockchangeTypelist.add(i, rst.getString(1));
				i++;

			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error : " + sqlexp.getMessage());
		}
	}

	/**
	 * @return Returns the vector_highlowtable.
	 */
	public Vector getVector_highlowtable() {
		return vector_highlowtable;
	}

	/**
	 * @param vector_ihighlowtable
	 *            The vector_indtable to set.
	 */
	public void setVector_highlowtable(String stockid, String fd, String td) {
		//String index1,index2;
	//	AdjustDecimal ad = new AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
	//	Connect con = new app.Connect();
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			con.getConnection();
		}
		vector_highlowtable = new Vector();
		showcolumn = false;
		rst = con.highlowResult("stock_price_daily_between_date", stockid, fd,
				td);
		int i = 0;
		Logging.debug("setVector_highlowtable of stock details");
		try {
			while (rst.next()) {
				if (rst.getString(2) == null) {
					vector_highlowtable.add(i, "--");
				} else {
					vector_highlowtable.add(i, rst.getString(2));
				}
				i++;

				if (rst.getString(3) == null) {
					vector_highlowtable.add(i, "0");
				} else {
					vector_highlowtable.add(i, rst.getString(3));
				}
				i++;

				if (rst.getString(4) == null) {
					vector_highlowtable.add(i, "0");
				} else {
					vector_highlowtable.add(i, rst.getString(4));
				}
				i++;
				if (rst.getString(5) == null) {
					vector_highlowtable.add(i, "0");
				} else {
					vector_highlowtable.add(i, rst.getString(5));
				}
				i++;
				if (rst.getString(6) == null) {
					vector_highlowtable.add(i, "0");
				} else {
					vector_highlowtable.add(i, rst.getString(6));
				}
				i++;
				if (rst.getString(7) == null) {
					vector_highlowtable.add(i, "0");
				} else {
					vector_highlowtable.add(i, rst.getString(7));
				}
				i++;
				if (rst.getString(10) == null) {
					vector_highlowtable.add(i, "--");
				} else {
					vector_highlowtable.add(i, ad.indexcompose(rst
							.getString(10)));
				}
				i++;
				if (rst.getString(9) == null) {
					vector_highlowtable.add(i, "--");
				} else {
					vector_highlowtable.add(i, ad
							.indexcompose(rst.getString(9)));
				}
				i++;
				if (rst.getString(11) == null) {
					vector_highlowtable.add(i, "0");
				} else {
					vector_highlowtable.add(i, rst.getString(11));
					showcolumn = true;
				}
				i++;
				if (rst.getString(8) == null) {
					vector_highlowtable.add(i, "--");
				} else {
					vector_highlowtable.add(i, rst.getString(8));
				}
				i++;
			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error : " + sqlexp.getMessage());
		}
	}

	/**
	 * @return Returns the showcolumn.
	 */
	public static boolean isShowcolumn() {
		return showcolumn;
	}

	/**
	 * @return Returns the vector_stockhangeDetail.
	 */
	public Vector getVectorstockchangeDetail() {
		return vector_stockchangeDetail;
	}

	/**
	 * @param vector_stockhangeDetail
	 *            The vector_stockhangeDetail to set.
	 */
	public void setVectorstockchangeDetail(String stockid, String fd, String td) {
		//String index1,index2;
//		app.Connect con = new app.Connect();
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			con.getConnection();
		}
		vector_stockchangeDetail = new Vector();
		rst = con.changeInStockDetailResult("stock_detail_changes", stockid,
				fd, td);
		int i = 0;
		Logging.debug("setVector_stockhangeDetail of stock details");
		try {
			while (rst.next()) {
				//app.Logging.getDebug("Inside while loop");
				//app.Logging.getDebug(rst.getString(1)+" "+rst.getString(2)+"
				// "+rst.getString(3)+" "+rst.getString(4)+"
				// "+rst.getString(5)+" "+rst.getString(6)+"
				// "+rst.getString(7)+" "+rst.getString(8)+"
				// "+rst.getString(9)+" "+rst.getString(10)+"
				// "+rst.getString(11));
				String str = "";
				Logging.debug("ir");
				if (rst.getString(1) == null) {
					vector_stockchangeDetail.add(i, "--");
				} else {
					vector_stockchangeDetail.add(i, rst.getString(1));
					str += "  " + rst.getString(1);
				}
				i++;

				if (rst.getString(2) == null) {
					vector_stockchangeDetail.add(i, "0");
				} else {
					vector_stockchangeDetail.add(i, rst.getString(2));
					str += "  " + rst.getString(2);
				}
				i++;
				if (rst.getString(3) == null) {
					vector_stockchangeDetail.add(i, "0");
				} else {
					vector_stockchangeDetail.add(i, rst.getString(3));
					str += "  " + rst.getString(3);
				}
				i++;

				if (rst.getString(4) == null) {
					vector_stockchangeDetail.add(i, "0");
				} else {
					vector_stockchangeDetail.add(i, rst.getString(4));
					str += "  " + rst.getString(4);
				}
				i++;
				if (rst.getString(5) == null) {
					vector_stockchangeDetail.add(i, "0");
				} else {
					vector_stockchangeDetail.add(i, rst.getString(5));
					str += "  " + rst.getString(5);
				}
				i++;
				if (rst.getString(6) == null) {
					vector_stockchangeDetail.add(i, "0");
				} else {
					vector_stockchangeDetail.add(i, rst.getString(6));
					str += "  " + rst.getString(6);
				}
				i++;
				if (rst.getString(7) == null) {
					vector_stockchangeDetail.add(i, "0");
				} else {
					vector_stockchangeDetail.add(i, rst.getString(7));
					str += "  " + rst.getString(7);
				}
				i++;
				if (rst.getString(8) == null) {
					vector_stockchangeDetail.add(i, "--");
				} else {
					vector_stockchangeDetail.add(i, rst.getString(8));
					str += "  " + rst.getString(8);
				}
				i++;
				if (rst.getString(9) == null) {
					vector_stockchangeDetail.add(i, "0");
				} else {
					vector_stockchangeDetail.add(i, rst.getString(9));
					str += "  " + rst.getString(9);
				}
				i++;
				if (rst.getString(10) == null) {
					vector_stockchangeDetail.add(i, "0");
				} else {
					vector_stockchangeDetail.add(i, rst.getString(10));
					str += "  " + rst.getString(10);
				}
				i++;
				if (rst.getString(11) == null) {
					vector_stockchangeDetail.add(i, "0");
				} else {
					vector_stockchangeDetail.add(i, rst.getString(11));
					str += "  " + rst.getString(11);
				}
				i++;

				Logging.debug(str);

			}
		} catch (SQLException sqlexp) {
		Logging.error("SQL Error : " + sqlexp.getMessage());
		}
	}

	/**
	 * @return Returns the vector_highlowtable.
	 */
	public Vector getVector_compareOHLC() {
		return vector_compareOHLC;
	}

	/**
	 * @param vector_ihighlowtable
	 *            The vector_indtable to set.
	 */
	public static String getIndexName(String index) {
		java.sql.ResultSet rst12;
		String str = "";
//		app.Connect con = new app.Connect();
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			con.getConnection();
		}
		rst12 = con.returnResult("index_name", index);
		try {
			while (rst12.next()) {
				str = (String) rst12.getString(1);
			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error : " + sqlexp.getMessage());
		}
		return str;
	}

	/**
	 * @param vector_ftdate
	 *            The vector_ftdate to set.
	 */
	public Vector getFromToDate() {
		java.sql.ResultSet rst;
		Vector vector_ftdate = new Vector();
		String str = "";
//		app.Connect con = new app.Connect();
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			con.getConnection();
		}
		int i = 0;
		try {

			rst = con.getClientList("get_from_to_date");
			//Logging.getDebug("inside try "+rst);
			while (rst.next()) {
				//app.Logging.getDebug("inside while");
				if (rst.getString(1) == null) {
					vector_ftdate.add(i, "--");
				} else {
					vector_ftdate.add(i, rst.getString(1));
				}
				i++;

				if (rst.getString(2) == null) {
					vector_ftdate.add(i, "--");
				} else {
					vector_ftdate.add(i, rst.getString(2));
				}
				i++;
				//Logging.getDebug("fdate "+rst.getString(1)+" tdate
				// "+rst.getString(2));
			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error : " + sqlexp.getMessage());
		}
		Logging.debug("vector_ftdate size " + vector_ftdate.size());
		return vector_ftdate;
	}

	public String getExchangeName(String index) {
		java.sql.ResultSet rst12;
		String str = null;
//		Connect con = new app.Connect();
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			con.getConnection();
		}
		rst12 = con.returnResult("get_stock_exchange_name", index);
		try {
			while (rst12.next()) {
				str = (String) rst12.getString(1);
			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error : " + sqlexp.getMessage());
		}
		return str;
	}

	public String getCompanyName(String index) {
		java.sql.ResultSet rst12;
		String str = null;
//		Connect con = new app.Connect();
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			con.getConnection();
		}
		rst12 = con.returnResult("get_company_name", index);
		try {
			while (rst12.next()) {
				str = (String) rst12.getString(1);
			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error : " + sqlexp.getMessage());
		}
		return str;
	}

	public void setVector_compareOHLC(String stockid, String fd) {
		//String index1,index2;
	//	app.Connect con = new app.Connect();
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			con.getConnection();
		}
		vector_compareOHLC = new Vector();
		rst = con.compareResult("index_details_on_date", stockid, fd);
		int i = 0;
		Logging
				.debug("setVector_compareOHLC of stock details 0 " + rst);
		try {
			/*
			 * while (rst.next()) { app.Logging.getDebug(rst.getString(1)+"
			 * "+rst.getString(2)+" "+rst.getString(3)+" "+rst.getString(4)+"
			 * "); }
			 */

			while (rst.next()) {
				String str = "";
				Logging.debug("ir");

				if (rst.getString(1) == null) {
					vector_compareOHLC.add(i, "0");
				} else {
					vector_compareOHLC.add(i, rst.getString(1));
					str += "  " + rst.getString(1);
				}
				i++;

				if (rst.getString(2) == null) {
					vector_compareOHLC.add(i, "0");
				} else {
					vector_compareOHLC.add(i, rst.getString(2));
					str += "  " + rst.getString(2);
				}
				i++;

				if (rst.getString(3) == null) {
					vector_compareOHLC.add(i, "0");
				} else {
					vector_compareOHLC.add(i, rst.getString(3));
					str += "  " + rst.getString(3);
				}
				i++;
				if (rst.getString(4) == null) {
					vector_compareOHLC.add(i, "0");
				} else {
					vector_compareOHLC.add(i, rst.getString(4));
					str += "  " + rst.getString(4);
				}

				Logging.debug(str);
			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error : " + sqlexp.getMessage());
		}
	}

	public int comapreTime(String time) {
		String[] time1 = time.split(":");
		Date dt = new Date();
		dt.getDate();
		String[] time2 = dt.toString().split(" ");
		time2 = time2[3].toString().split(":");
		for (int i = 0; i < time1.length; i++) {
			if (Integer.parseInt(time1[i]) > Integer.parseInt(time2[i]))
				return 1;
			else if (Integer.parseInt(time1[i]) < Integer.parseInt(time2[i]))
				return -1;
		}
		return 2;
	}

	/**
	 * 
	 * @return Returns the vector as per selected field for Change in stock
	 *         Detail report
	 */
	public Vector getVectorchangeInStockDetail(Vector v, int pos) {
		v1 = new Vector();
		//app.Logging.getDebug("inside getVectorchangeInStockDetail(Vector
		// v,int pos)");
		//app.Logging.getDebug(v.size());
		//app.Logging.getDebug("position ="+pos);
		for (int i = 0; i < (v.size()); i += 11) {
			//app.Logging.getDebug("inside for loop");
			String str1 = (String) v.get(i);
			//app.Logging.getDebug(str1);
			v1.add(str1);
			//app.Logging.getDebug((i+pos));
			String str2 = (String) v.get((i + pos));
			//app.Logging.getDebug(str2);
			v1.add(str2);
			//app.Logging.getDebug((i+10));
			String str3 = (String) v.get((i + 10));
			//app.Logging.getDebug(str3);
			v1.add(str3);
		}
	Logging.debug("vector v1");
		/*
		 * for(int l=0;l <v1.size();l++) { String st=(String)v1.get(l);
		 * app.Logging.getDebug(st); }
		 */
		return v1;
	}

	/**
	 * method to generate the exchange list and get the set the given exchange
	 * selected.
	 * 
	 * @param id
	 * @return StringBuffer
	 */
	public static StringBuffer getExchangeList(String id) {

		ResultSet rs = null;
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			con.getConnection();
		}
		StringBuffer buffer = new StringBuffer();
		try {
			String query = ConnectInit.queries.getProperty("stock_exchange_list");
			Statement stmt = Connect.con.createStatement();
			rs = stmt.executeQuery(query);
			Logging.debug("query in Listtypeclass is " + query);
			int id1 = 0;
			int i = 0;
			//String id2=id;
			Logging.debug("Param Value is ListTypeClass " + id);
			while (rs.next()) {
				id1 = rs.getInt(1);
				if (id != null) {
					int param = Integer.parseInt(id);
					//  Logging.debug("param "+id2+" id1"+id1);
					if (param == id1) {
						Logging.debug("inside param equals id");
						buffer.append("<option selected value=" + id1 + ">"
								+ rs.getString(2) + "</option>");
					} else {
						buffer.append("<option value=" + id1 + ">"
								+ rs.getString(2) + "</option>");
					}

				} else {
					buffer.append("<option value=" + id1 + ">"
							+ rs.getString(2) + "</option>");
				}

			}
		} catch (Exception e) {
			Logging.debug("ListTypeClass:Error in CreateStatement "
					+ e.getMessage());
		}

		//	Logging.debug(buffer.toString());

		return buffer;
	}

	/**
	 * 
	 * @return Returns the vector as per selected field for Change in stock
	 *         Detail report
	 */
	public Vector getVectorchangeInStockDetail1(Vector v, int pos1, int pos2) {
		Vector v2 = new Vector();
		for (int i = 0; i < (v.size()); i += 11) {
			String st1 = (String) v.get(i);
			v2.add(st1);
			String st2 = (String) v.get(i + pos1);
			v2.add(st2);
			String st3 = (String) v.get(i + pos2);
			v2.add(st3);
			String st4 = (String) v.get(i + 10);
			v2.add(st4);
		}
		return v2;
	}

	public boolean getflag(String[] var, int id1) {
		boolean flag = false;
		for (int i = 0; i < var.length; i++) {
			int id = (int) Integer.parseInt(var[i]);
			if (id == id1) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * To get the list of moving average days.
	 * 
	 * @return
	 */
	public int[] getVector_Mavgspan() {
		int[] avg_span = new int[29];
		Logging.debug("in getVector_Mavgspan ");
		int k = 1;
		for (int i = 1; i < 30; i++) {
			//String span=(String)(new Integer(k).toString());
			avg_span[i] = i;
			k++;
		}
		Logging.debug("avg_span size is " + avg_span.length);
		for (int j = 0; j < avg_span.length; j++) {
			int str = avg_span[j];
			Logging.debug(" str is " + str);
		}
		return avg_span;
	}

	public String get_index_name(int id) {
		String ind_name = null;
		Connect connect = ConnectInit.getConnect();
		//app.Connect con=new app.Connect();
		Connection con = null;
		PreparedStatement pst;
		ResultSet rst;
		if (con == null) {
			con = connect.getConnection();
		}
		try {
			pst = Connect.con.prepareStatement(ConnectInit.queries
					.getProperty("get_index_name_from_id"));
			pst.setInt(1, id);
			rst = pst.executeQuery();
			while (rst.next()) {
				ind_name = rst.getString(1);
			}
		} catch (Exception e) {
		}

		return ind_name;
	}

	/**
	 * @return Returns the stock_exchange.
	 */
	public Vector getStock_exchange() {
		return vector_stock_exchange;
	}

	/**
	 * @param stock_exchange
	 *            The stock_exchange to set.
	 */

	public void setVector_stock_exchange(String stockid) {
//		app.Connect con = new app.Connect();
		Connect con = ConnectInit.getConnect();
		if (Connect.con == null) {
			con.getConnection();
		}
		vector_stock_exchange = new Vector();
		rst = con.returnResult("stock_exchange_wise_stock_details", stockid);
		int i = 0;
		Logging.debug("setStock_exchange of Compose Index");
		try {

			while (rst.next()) {

				if (rst.getString(1) == null) {
					vector_stock_exchange.add(i, "--");
				} else {
					vector_stock_exchange.add(i, rst.getString(1));
				}
				i++;

				if (rst.getString(2) == null) {
					vector_stock_exchange.add(i, "--");
				} else {
					vector_stock_exchange.add(i, rst.getString(2));
				}
				i++;

				if (rst.getString(3) == null) {
					vector_stock_exchange.add(i, "--");
				} else {
					vector_stock_exchange.add(i, rst.getString(3));
				}
				i++;

				if (rst.getString(4) == null) {
					vector_stock_exchange.add(i, "--");
				} else {
					vector_stock_exchange.add(i, rst.getString(4));
				}
				i++;

			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error : " + sqlexp.getMessage());
		}

	}

	public String getIndexListUrl(HttpServletRequest request) {
		String url = null;
	//	org.jfree.chart.demo.servlet.ComposeIndex ci = new org.jfree.chart.demo.servlet.ComposeIndex();
		ComposeIndex ci =ConnectInit.getComposeIndex();
		String[] indid = request.getParameterValues("indexid");
		String button = request.getParameter("operation");
		if (button != null) {
			String idcorr = "D1=";
			String idcorr1 = null;
			String report = request.getParameter("D1");
			if (indid != null) {
				Logging.debug("index id array length is "
						+ indid.length);
				for (int i = 0; i < indid.length; i++) {
					Logging.debug("index id array element is "
							+ indid[i]);
					if (i == 0) {
						idcorr = "D1=" + indid[i];
					} else {
						idcorr = idcorr + "&D1=" + indid[i];
						if (report.equals("2")) {
							idcorr1 = idcorr1 + "&D2=" + indid[i];
						}
					}
				}
				if (button.equals("Go") && indid != null && indid.length > 0) {

					Vector vdate = new Vector();
					vdate = ci.getFromToDate();
					Logging.debug("vector size " + vdate.size());
					if (vdate.size() != 0) {
						String fdate = (String) vdate.get(0);
						String tdate = (String) vdate.get(1);
						Logging.debug("fromdate is " + fdate
								+ "  to date is " + tdate);
						Logging.debug("Inside go" + report);
						if (report.equals("1")) {
							url = "/pages/IndexCorrelation.jsp?" + idcorr
									+ "&from=" + fdate + "&to=" + tdate
									+ "&B1=View";
							Logging.debug("url is " + url);
						}

						if (report.equals("2")) {
							url = "/pages/IndexCompare1.jsp?" + idcorr1
									+ "&from=" + fdate + "&to=" + tdate
									+ "&B1=Compare";
							Logging.debug("url is " + url);
						}

						if (report.equals("3")) {
							url = "/pages/IndexReturnsVolatility.jsp?" + idcorr
									+ "&from=" + fdate + "&to=" + tdate
									+ "&B1=View";
							Logging.debug("getIndexListUrl " + url);
						}
					}
				}
			}

		}
		return url;
	}

	/**
	 * to get the default exchange id from system configuration table.
	 * 
	 * @return
	 */
	public static String getDefaultExchange() {
		String exchange = "0";
		try {
			ResultSet rsconfig = StockResult.getExch_countr_curr();
			Logging.debug("set default values " + rsconfig);
			while (rsconfig.next()) {
				exchange = (String) rsconfig.getString(1);
				Logging.debug("s_stockExchange " + exchange);
			}

		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		}
		return exchange;
	}

	/**
	 * @return Returns the total.
	 */
	public static String getTotal() {
	//	org.jfree.chart.demo.servlet.AdjustDecimal ad = new org.jfree.chart.demo.servlet.AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		if (total >= 99.9999)
			total = 100.00;
		String strtotal = (String) new Double(total).toString();
		strtotal = ad.indexcompose(strtotal);
		return strtotal;
	}

	/**
	 * @return Returns the filename.
	 */
	public static String getFilename() {
		return filename;
	}

	/**
	 * @return Returns the graphURL.
	 */
	public static String getGraphURL() {
		return graphURL;
	}

	public String reset_stkevent() {
		String str = "0";
		Connect connect = ConnectInit.getConnect();
		Connection con = null;
		if (con == null) {
			con = connect.getConnection();
		}
		try {
			String qry = ConnectInit.queries.getProperty("select_system_config");
			ResultSet rs1 = ListTypeClass1.resultCorporate(con, qry);
			rs1.next();
			str = rs1.getString("stock_ex_id");
			return str;
		} catch (Exception e) {
			Logging.error("Error=" + e.getMessage());
			return str;
		}

	}
}

