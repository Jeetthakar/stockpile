/*
 * Created on Nov 19, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import harrier.income.com.compute.CIndexCalculator;
import harrier.income.com.masters.CapturedIndexForm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.jfree.chart.demo.servlet.AdjustDecimal;

import com.harrier.initializeation.ConnectInit;

/**
 * @author sunil
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class CapturedIndexCollection {
	static Logger Logging = Logger.getLogger(CapturedIndexCollection.class);
	public static Hashtable table = new Hashtable();
	public static Hashtable destinationTable = new Hashtable();
	static String id;
	static String button;
	static long tableid;
	public static Vector v = new Vector();
	// private static CapturedIndexForm indexcomp;
	static String iname, cname, l_date;

	static String indexid;

	static Connect con1 = ConnectInit.getConnect();
	static String ivalue, ovalue, hvalue, lvalue, cvalue, pchange, mcvalue,
			dvalue;
	public static double[] fiftytwo_min_max = new double[2];

	// static CIndexCalculator ICalculator=new CIndexCalculator();
	/**
	 * method to set the values for captured indices in bean as well as
	 * hashtable.
	 */
	// Changes of Static coneection problem in method addStocksInSourceTable and
	// insertIndexValues by ashish dated 02/08/2006
	public static void addStocksInSourceTable(String option, String[] indlist,
			HttpServletRequest request) {
		// Connect con=new app.Connect();
		Connect con = ConnectInit.getConnect();
		Connection con1 = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			// org.jfree.chart.demo.servlet.AdjustDecimal ad=new
			// org.jfree.chart.demo.servlet.AdjustDecimal();
			AdjustDecimal ad = ConnectInit.getAdjustDecimal();

			long count = 0;
			SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
			Date dt = new Date();
			l_date = fr.format(dt).toString();
			// getDebug(l_date);

			if (con1 == null) {
				con1 = con.getdbConnection();
			}
			if ((option != null) && (option.equals("Submit"))) {
				v.clear();
				for (int j = 0; j < indlist.length; j++) {
					Logging.debug(indlist[j]);
					v.add(indlist[j]);
				}
			}
			stmt = con1.prepareStatement(ConnectInit.queries
					.getProperty("get_latest_all_captured_index_details"));
			Logging.debug(stmt + "");
			stmt.setString(1, l_date);
			rs = stmt.executeQuery();
			if (rs != null) {
				Logging.debug(option);
				int i = 0;
				count++;
				String ldate = null;
				while (rs.next()) {

					++i;
					Logging.debug("Inside while");
					try {
						id = rs.getString(1);
						Logging.debug("Inside while" + id);
						iname = rs.getString(2);
						if ((option != null) && (option.equals("Submit"))) {
							ivalue = request.getParameter("index_value:"
									+ rs.getString(1));

						} else {
							ivalue = rs.getString(3);
							if (ivalue == null) {
								ivalue = "0.00";
							}
						}
						Logging.debug("Inside while" + iname + " index value "
								+ ivalue);
						ivalue = ad.indexcompose(ivalue);
						if ((option != null) && (option.equals("Submit"))) {
							ovalue = request.getParameter("open_value:"
									+ rs.getString(1));

						} else {
							ovalue = rs.getString(4);
							if (ovalue == null) {
								ovalue = "0.00";
							}
						}
						ovalue = ad.indexcompose(ovalue);
						if ((option != null) && (option.equals("Submit"))) {
							hvalue = request.getParameter("high_value:"
									+ rs.getString(1));

						} else {
							hvalue = rs.getString(5);
							if (hvalue == null) {
								hvalue = "0.00";
							}
						}
						hvalue = ad.indexcompose(hvalue);
						if ((option != null) && (option.equals("Submit"))) {
							lvalue = request.getParameter("low_value:"
									+ rs.getString(1));

						} else {
							lvalue = rs.getString(6);
							if (lvalue == null) {
								lvalue = "0.00";
							}
						}
						lvalue = ad.indexcompose(lvalue);
						if ((option != null) && (option.equals("Submit"))) {
							cvalue = request.getParameter("closing_value:"
									+ rs.getString(1));

						} else {
							cvalue = rs.getString(7);
							if (cvalue == null) {
								cvalue = "0.00";
							}
						}
						cvalue = ad.indexcompose(cvalue);
						if ((option != null) && (option.equals("Submit"))) {
							pchange = request.getParameter("per_change:"
									+ rs.getString(1));
						} else {
							if (rs.getDouble(8) != 0.00) {
								double dpchange = (((Double.parseDouble(ivalue) - (rs
										.getDouble(8))) / rs.getDouble(8)) * 100);
								pchange = new Double(dpchange).toString();
							} else {
								pchange = "0.00";
							}
							Logging.debug("pchange value is in captured index "
									+ pchange);
							if (pchange == null) {
								pchange = "0.00";
							}
						}
						pchange = ad.indexcompose(pchange);
						if ((option != null) && (option.equals("Submit"))) {
							mcvalue = request.getParameter("mkt_cap_value:"
									+ rs.getString(1));

						} else {
							mcvalue = rs.getString(9);
							if (mcvalue == null) {
								mcvalue = "0.00";
							}
						}
						mcvalue = ad.indexcompose(mcvalue);
						if ((option != null) && (option.equals("Submit"))) {
							dvalue = request.getParameter("divisor_value:"
									+ rs.getString(1));

						} else {
							dvalue = rs.getString(10);
							if (dvalue == null) {
								dvalue = "0.00";
							}
						}
						dvalue = ad.indexcompose(dvalue);
						if ((option != null) && (option.equals("Submit"))) {
							cname = request.getParameter("currancy_name:"
									+ rs.getString(1));

						} else {
							cname = rs.getString(11);
						}
						if ((option != null) && (option.equals("Submit"))) {
							for (int k = 0; k < v.size(); k++) {
								Logging.debug("Inside for" + v.get(k) + "  "
										+ rs.getString(1));
								if (((String) v.get(k)).equals(rs.getString(1))) {
									ldate = request.getParameter("fdate");
								} else {
									ldate = rs.getString(12);
								}
							}
							if (ldate == null) {
								ldate = rs.getString(12);
							}
						} else {
							ldate = rs.getString(12);
							if (ldate == null) {
								ldate = l_date;
							}
							;
						}
					} catch (Exception e) {
						Logging.error("ERROR: " + e.getMessage());
					}
					if (option == null) {
						table.put(String.valueOf(id), new CapturedIndexForm(id,
								iname, ivalue, ovalue, hvalue, lvalue, cvalue,
								pchange, mcvalue, dvalue, cname, ldate));
						destinationTable
								.put(String.valueOf(id), new CapturedIndexForm(
										id, iname, ivalue, ovalue, hvalue,
										lvalue, cvalue, pchange, mcvalue,
										dvalue, cname, ldate));
					}
					if ((option != null) && (option.equals("Reset"))) {
						table.put(String.valueOf(id), new CapturedIndexForm(id,
								iname, "0.00", "0.00", "0.00", "0.00", "0.00",
								"0.00", "0.00", "0.00", cname, l_date));

					}

					if ((option != null) && (option.equals("Submit"))) {
						Logging.debug("Inside button  is submit ");
						table.remove(String.valueOf(id));
						table.put(String.valueOf(id), new CapturedIndexForm(id,
								iname, ivalue, ovalue, hvalue, lvalue, cvalue,
								pchange, mcvalue, dvalue, cname, ldate));
						destinationTable.remove(String.valueOf(id));
						destinationTable
								.put(String.valueOf(id), new CapturedIndexForm(
										id, iname, ivalue, ovalue, hvalue,
										lvalue, cvalue, pchange, mcvalue,
										dvalue, cname, ldate));

					}
				}
				Logging.debug("Number of Stocks " + i);
			}
		} catch (SQLException e) {
			Logging.error("Error in sourceTable " + e.getMessage());
		} finally {
			try {
				if (con1 != null)
					con1.close();
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}

	}

	public Vector GetID() {
		return v;
	}

	/**
	 * method to insert the values of captured indices into the
	 * database(index_value_daily,intra_day_indices tables).
	 */
	public static void insertIndexValues(ActionForm form) {
		// Connect con=new app.Connect();
		Connect con = ConnectInit.getConnect();
		CIndexCalculator ICalculator = ConnectInit.getCIndexCalculator();
		Connection con1 = null;
		ResultSet rst = null, rst2 = null;
		Statement stm = null;
		PreparedStatement pst_preStat = null, pst = null, pst3 = null;
		String ind_nameList = "";
		String status;

		if (con1 == null) {
			con1 = con.getdbConnection();
		}
		Logging.debug("Inside insert Index VAlues");
		Logging.debug("size of vector is " + v.size());
		CapturedIndexForm indexcomp1 = (CapturedIndexForm) form;
		String id = indexcomp1.getIndexID();
		Logging.debug("inside for loop" + indexid + "  " + id);
		String time = getTime();
		try {
			for (int i = 0; i < v.size(); i++) {
				indexid = (String) v.get(i);
				iname = null;
				CapturedIndexForm indexcomp = (CapturedIndexForm) table
						.get(indexid);
				// inserts value in intra day indices
				String ivalue = indexcomp.getIndex_value();
				String ovalue = indexcomp.getOpen_value();
				String hvalue = indexcomp.getHigh_value();
				String lvalue = indexcomp.getLow_value();
				String cvalue = indexcomp.getClosing_value();
				String pchange = indexcomp.getPer_change();
				String mcvalue = indexcomp.getMkt_cap_value();
				String dvalue = indexcomp.getDivisor_value();
				String cname = indexcomp.getCurrancy_name();
				String ldate = indexcomp.getDate();
				try {
					try {
						pst = null;
						rst = null;
						Logging.debug("Inside try of index Name" + id);
						pst = con1.prepareStatement(ConnectInit.queries
								.getProperty("get_captured_index_name"));
						pst.setString(1, indexid);
						rst = pst.executeQuery();// execute query
						if (rst.next()) {
							iname = null;
							iname = (String) rst.getString(1);
							Logging.debug(rst.getString(1));
							// indexcomp1.setIndex_name(iname);
						}
						indexcomp1.ind_nameList.put(indexid, iname);
						if (rst != null)
							rst.close();
						if (pst != null)
							pst.close();
					} catch (SQLException e) {
						Logging.error("ERROR: " + e.getMessage());
					}
					Logging.debug(iname);
					Logging.debug(iname + " " + ivalue + " " + ovalue + " "
							+ hvalue + " " + lvalue + " " + cvalue + " "
							+ pchange + " " + mcvalue + " " + dvalue + " "
							+ cname + " " + ldate);
					if (destinationTable.containsKey(String.valueOf(id))) {
						destinationTable.remove(String.valueOf(id));
						destinationTable
								.put(String.valueOf(id), new CapturedIndexForm(
										id, iname, ivalue, ovalue, hvalue,
										lvalue, cvalue, pchange, mcvalue,
										dvalue, cname, ldate));
					}
					if (table.containsKey(String.valueOf(id))) {
						table.remove(String.valueOf(id));
						table.put(String.valueOf(id), new CapturedIndexForm(id,
								iname, ivalue, ovalue, hvalue, lvalue, cvalue,
								pchange, mcvalue, dvalue, cname, ldate));
					}
				} catch (Exception e) {
					Logging.error("ERROR: " + e.getMessage());
				}
				try {
					try {
						pst_preStat = con1.prepareStatement(ConnectInit.queries
								.getProperty("insert_into_intra_day_indices"));
						pst_preStat.setString(4, indexid);
						pst_preStat.setDouble(5, Double.parseDouble(indexcomp
								.getMkt_cap_value()));
						pst_preStat.setDouble(1,
								Double.parseDouble(indexcomp.getIndex_value()));
						pst_preStat.setString(2, indexcomp.getDate());
						pst_preStat.setString(3, time);
						pst_preStat.executeUpdate();// execute query
						if (pst_preStat != null)
							pst_preStat.close();
					} catch (Exception e) {
						Logging.debug("ERROR" + e.getMessage());
					}
					// select index_lowest_value,index_highest_value from index
					// value daily

					try {

						pst_preStat = con1.prepareStatement(ConnectInit.queries
								.getProperty("get_high_low_index_value"));
						pst_preStat.setString(1, indexid);// set ? for index id
						pst_preStat.setString(2, indexcomp.getDate());
						rst2 = pst_preStat.executeQuery();// execute query
						// if no enteries
						// Change as per Income dated 03-08-2006 by ashish
						if (rst2.next()) {
							Logging.debug("rst2.getRow()" + rst2.getRow());
							pst_preStat = con1
									.prepareStatement(ConnectInit.queries
											.getProperty("update_index_value_daily_add_captured_index"));
							pst_preStat.setDouble(1, Double
									.parseDouble(indexcomp.getOpen_value()));
							pst_preStat.setDouble(2, Double
									.parseDouble(indexcomp.getHigh_value()));
							pst_preStat.setDouble(3, Double
									.parseDouble(indexcomp.getLow_value()));
							pst_preStat.setDouble(4, Double
									.parseDouble(indexcomp.getClosing_value()));
							pst_preStat.setString(5, indexid);// INDEX ID
							pst_preStat.setString(6, indexcomp.getDate());
							pst_preStat.setDouble(7, Double
									.parseDouble(indexcomp.getMkt_cap_value()));
							pst_preStat.setString(8, indexid);// INDEX ID
							pst_preStat.setString(9, indexcomp.getDate());
							pst_preStat.executeUpdate();// execute query
							indexcomp1.setCheck_flag("0");
							status = indexcomp1.getCheck_flag();
							indexcomp1.ind_statusList.put(indexid, status);
							if (pst_preStat != null)
								pst_preStat.close();
						} else {
							String settlement = "y";
							// Logging.getDebug(settlement);

							// code for 52 week low and high from
							double index_close = Double.parseDouble(indexcomp
									.getClosing_value());
							fiftytwo_min_max = ICalculator
									.getFiftytwo_Week_HighLow(indexid);
							if (index_close > fiftytwo_min_max[0]) {
								fiftytwo_min_max[0] = index_close;
							}
							if (index_close < fiftytwo_min_max[1]) {
								fiftytwo_min_max[1] = index_close;
							}
							// code for 52 week low and high to
							Logging.debug("before insert query in index value daily");
							pst_preStat = con1
									.prepareStatement(ConnectInit.queries
											.getProperty("insert_into_index_value_daily"));
							// pst_preStat.setLong(1, tableid);
							pst_preStat.setDouble(1, Double
									.parseDouble(indexcomp.getOpen_value()));
							pst_preStat.setDouble(2, Double
									.parseDouble(indexcomp.getHigh_value()));
							pst_preStat.setDouble(3, Double
									.parseDouble(indexcomp.getLow_value()));
							pst_preStat.setDouble(4, Double
									.parseDouble(indexcomp.getClosing_value()));
							pst_preStat.setString(5, indexid);
							pst_preStat.setString(6, indexcomp.getDate());
							pst_preStat.setString(7, null);
							pst_preStat.setDouble(8, Double
									.parseDouble(indexcomp.getDivisor_value()));
							pst_preStat.setDouble(9, Double
									.parseDouble(indexcomp.getMkt_cap_value()));
							pst_preStat.setDouble(10, Double
									.parseDouble(indexcomp.getMkt_cap_value()));
							pst_preStat.setDouble(11, Double
									.parseDouble(indexcomp.getDivisor_value()));
							pst_preStat.setDouble(12, fiftytwo_min_max[0]);
							pst_preStat.setDouble(13, fiftytwo_min_max[1]);

							Logging.debug(" index_value_daily insert query "
									+ pst_preStat);
							pst_preStat.executeUpdate();// execute query
							indexcomp1.setCheck_flag("2");
							status = indexcomp1.getCheck_flag();
							indexcomp1.ind_statusList.put(indexid, status);
							if (pst_preStat != null)
								pst_preStat.close();
						}

					} catch (SQLException e) {
						indexcomp1.setCheck_flag("1");
						status = indexcomp1.getCheck_flag();
						indexcomp1.ind_statusList.put(indexid, status);
						Logging.error("Error : " + e.getMessage());
					}
				} catch (Exception ee) {
					Logging.error("Error : " + ee.getMessage());
				}
			}
		} catch (Exception exe) {
			Logging.error("ERROR: " + exe.getMessage());
		} finally {
			try {
				if (pst_preStat != null)
					pst_preStat.close();
				if (con1 != null)
					con1.close();
				if (pst != null)
					pst.close();
				if (pst3 != null)
					pst3.close();
				if (stm != null)
					stm.close();
				if (rst != null)
					rst.close();
				if (rst2 != null)
					rst2.close();
			} catch (Exception ex) {
				Logging.error("Error : Error while closing connection,resultset,preparedStatement "
						+ ex.getMessage());
			}
		}

	}

	public static void resetTable() {
		Logging.debug("Inside reset table");
		button = "Reset";
	}

	/**
	 * method to get the system's current time. returns string.
	 */
	public static String getTime() {
		java.util.Date dt = new java.util.Date();
		dt.getDate();
		return dt.toString().split(" ")[3];
	}

}
