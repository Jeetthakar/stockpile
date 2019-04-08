/**
 * @author Ashish
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;
import org.jfree.chart.demo.servlet.AdjustDecimal;
import org.jfree.chart.demo.servlet.ComposeIndex;

import app.AcessControl;
import app.Connect;

import com.harrier.initializeation.ConnectInit;

public class DisplayIndexForm extends ActionForm {

	Logger Logging = Logger.getLogger(DisplayIndexForm.class);
	private String from = null, go = null, clear = null, to = null,
			defaultVal = null, check = null, checkChart = null,
			selectIndex = null, selectStock = null, text = null;
	private Collection selectIndexCollection = null;

	static double total = 0.00;
//	AdjustDecimal ad = new AdjustDecimal();
	String reset, computetotalreturns, b_showChild, query, index, compute,
			indexid1;
	ArrayList details = null;
	private Vector vw, index_details, va;
	private ResultSet rst, rst1;
	// app.Connect con=new app.Connect();
	String userid1;
	String roleid1;
	Connection connection = null;

	public String getUserid1() {
		return userid1;
	}

	public void setUserid1(String userid1) {
		this.userid1 = userid1;
	}

	/** RESEST ALL FORM FIELDS **/
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		selectIndexCollection = null;
		from = null;
		go = null;
		to = null;
		clear = null;
		defaultVal = null;

	}

	public void reset() {
		from = null;
	}

	/**
	 * VALIDATE FORM DATA
	 * **/
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		return errors;
	}

	/**
	 * @return Returns the total.
	 */
	public ArrayList getDetails() {
		Connection connection = null;
		va = new Vector();
		Vector ve = new Vector();
		String l_date = "";
		String date1 = from, predate = null;
		String indexname = null, indexid = null, current = null, indexopen = null, indexhigh = null, indexlow = null, indexclosing = null, tmcv = null, divisor = null, currency = null, indexdate = null, indexclsv = null, indextime = null, vachange = null;
		Logging.debug("Before if  " + date1);

		if ((date1 == null) || (date1.equals("null"))) {
			Logging.debug("date null inside if");
			SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
			Date dt = new Date();
			l_date = fr.format(dt).toString();
		}
		Logging.debug(l_date);
		int i = 0;
		Double dd = new Double("0");
		Logging.debug("setIndex_details of Compose Index");
		String value = null, open = null, high = null, low = null, close = null, mcap = null;
		ArrayList tempdata = new ArrayList();
		details = new ArrayList();
		DetailIndex detailindex;
		// String user_id="1";
	//	Connect con = new app.Connect();
		Connect con = ConnectInit.getConnect();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		try {
			if (connection == null)
				connection = con.getdbConnection();

			if ((date1 == null) || (date1.equals("null"))) {
				// Change by Manoj Adekar: Dated 4-11-2008
				if (roleid1.equals("1")) {
					rst1 = con.getLatestIndexDetails1(
							"get_latest_all_index_details_bench", l_date);
				} else {
					rst = con.getLatestIndexDetails(
							"get_latest_all_index_details", l_date, userid1);
					rst1 = con.getLatestIndexDetails1(
							"get_latest_all_index_details_bench", l_date);
				}
			} else {
				if (roleid1.equals("1")) {
					rst1 = con.getLatestIndexDetails1(
							"get_latest_all_index_details_bench", l_date);
				} else {
					rst1 = con
							.getIndexDetails("all_index_details_bench", date1);
					rst = con.getIndexDetails2("all_index_details", date1,
							userid1);
				}
			}

			try {

				while (rst.next()) {

					String strpchange = "0.00", status = null;
					/*
					 * Change by Manoj Adekar to display Previos Close value in
					 * the Report
					 */
					String curindid = null, curdate = null;
					curindid = rst.getString("index_id");
					curdate = rst.getString("index_value_date");

					predate = con.getMaxDate("get_before_max_date", curdate,
							curindid);
					double preVal = 0.00;

					preVal = con
							.getIndexValue("get_oneweek_before_index_value",
									predate, curindid);
					/* Upto Here */

					if ((((double) rst.getDouble("index_closing_value")) != 0.00)
							&& (preVal != 0.00)) {
						double pchange = (((double) rst.getDouble(2) - (double) preVal) / (double) preVal) * 100;
						strpchange = new Double(pchange).toString();
						Logging.debug("strpchange before adjusting is "
								+ strpchange);
						strpchange = ad.indexcompose(strpchange);
						vachange = strpchange;
						if (vachange == null)
							vachange = "0.00";
						Logging.debug("strpchange after adjusting is "
								+ strpchange);
					} else {
						vachange = "0.00";
					}
					if (rst.getString(13) == null) {
						indexid = "0";

					} else {
						indexid = rst.getString(13);
						ve.add(indexid);
					}

					if (rst.getString(1) == null) {
						indexname = "0";
					} else {
						indexname = rst.getString(1);

					}

					if (rst.getString(2) == null) {
						current = "0";
					} else {
						current = rst.getString(2);
						current = ad.indexcompose(current);
						current = AdjustDecimal.ArrangeAsNumeric(current);

					}

					if (rst.getString(3) == null) {
						status = "--";
					} else {
						double change = (double) Double.parseDouble(strpchange);
						if (change > 0) {
							status = "up";
						} else {
							if (change == 0.00) {
								status = "mid";
							} else {
								// dd = new Double(change);
								status = "down";
							}
						}
					}

					if (rst.getString(3) == null) {
						indexopen = "0.00";
					} else {
						indexopen = rst.getString(3);
						indexopen = ad.indexcompose(indexopen);
						indexopen = AdjustDecimal.ArrangeAsNumeric(indexopen);

					}

					if (rst.getString(4) == null) {
						indexhigh = "0.00";
					} else {
						indexhigh = rst.getString(4);
						indexhigh = ad.indexcompose(indexhigh);
						indexhigh = AdjustDecimal.ArrangeAsNumeric(indexhigh);
					}

					if (rst.getString(5) == null) {
						indexlow = "0.00";
					} else {
						indexlow = rst.getString(5);
						indexlow = ad.indexcompose(indexlow);
						indexlow = AdjustDecimal.ArrangeAsNumeric(indexlow);

					}

					if (preVal == 0.0) {
						indexclsv = "0.00";
					} else {
						indexclsv = new Double(preVal).toString();
						indexclsv = ad.indexcompose(indexclsv);
						indexclsv = AdjustDecimal.ArrangeAsNumeric(indexclsv);

					}

					/*
					 * if (rst.getString(12) == null) { indextime= "0";
					 * 
					 * } else { int time = comapreTime(rst.getString(12)); if
					 * (time > 0) { String temp = rst.getString(7);
					 * if(temp==null){ temp="0.00"; }
					 * temp=ad.indexcompose(temp);
					 * temp=AdjustDecimal.ArrangeAsNumeric(temp); indexclsv=
					 * temp;
					 * 
					 * } else { String temp1=rst.getString(6); if(temp1==null){
					 * temp1="0.00"; } temp1=ad.indexcompose(temp1);
					 * temp1=AdjustDecimal.ArrangeAsNumeric(temp1);
					 * indexclosing= temp1 ;
					 * 
					 * } }
					 */

					if (rst.getString(8) == null) {
						tmcv = "0.00";
					} else {
						String temp = rst.getString(8);
						if (temp == null) {
							temp = "0.00";
						}
						int k = temp.indexOf(".");
						if (k == -1) {
							tmcv = temp;

						} else {
							temp = temp + "00";
							temp = temp.substring(0, k + 2);
							temp = ad.indexcompose(temp);
							temp = AdjustDecimal.ArrangeAsNumeric(temp);
							tmcv = temp;

						}
					}

					if (rst.getString(9) == null) {
						divisor = "0.00";
					} else {
						String temp = rst.getString(9);
						if (temp == null) {
							temp = "0.00";
						}
						int k = temp.indexOf(".");
						if (k == -1) {
							divisor = temp;

						} else {
							temp = temp + "00";
							temp = temp.substring(0, k + 2);
							temp = ad.indexcompose(temp);
							temp = AdjustDecimal.ArrangeAsNumeric(temp);
							divisor = temp;

						}
					}

					if (rst.getString(10) == null) {
						currency = "0";
					} else {
						currency = rst.getString(10);
					}

					if (rst.getString(11) == null) {
						indexdate = "0";
					} else {
						indexdate = rst.getString(11);
					}

					Logging.debug("Vec Values =" + indexname + " : "
							+ indexid + " : " + current + " : " + indexopen
							+ " : " + indexhigh + " : " + indexlow + " : "
							+ indexclosing + " : " + vachange + " : " + tmcv
							+ " : " + divisor + " : " + currency + ": "
							+ indexdate + ": " + indexclsv + ": " + indextime);
					detailindex = new DetailIndex(indexname, indexid, current,
							status, indexopen, indexhigh, indexlow,
							indexclosing, vachange, tmcv, divisor, currency,
							indexdate, indexclsv, indextime);
					tempdata.add(detailindex);
				}
				while (rst1.next()) {

					String strpchange = "0.00", status = null;
					/*
					 * Change by Manoj Adekar to display Previos Close value in
					 * the Report
					 */
					String curindid = null, curdate = null;
					curindid = rst1.getString("index_id");
					curdate = rst1.getString("index_value_date");

					predate = con.getMaxDate("get_before_max_date", curdate,
							curindid);
					double preVal = 0.00;

					preVal = con
							.getIndexValue("get_oneweek_before_index_value",
									predate, curindid);
					/* Upto Here */

					if ((((double) rst1.getDouble("index_closing_value")) != 0.00)
							&& (preVal != 0.00)) {
						double pchange = (((double) rst1.getDouble(2) - (double) preVal) / (double) preVal) * 100;
						strpchange = new Double(pchange).toString();
						Logging.debug("strpchange before adjusting is "
								+ strpchange);
						strpchange = ad.indexcompose(strpchange);
						vachange = strpchange;
						if (vachange == null)
							vachange = "0.00";

						Logging.debug("strpchange after adjusting is "
								+ strpchange);
					} else {
						vachange = "0.00";
					}

					if (rst1.getString(13) == null) {
						indexid = "0";

					} else {
						indexid = rst1.getString(13);
						ve.add(indexid);
					}

					if (rst1.getString(1) == null) {
						indexname = "0";
					} else {
						indexname = rst1.getString(1);

					}

					if (rst1.getString(2) == null) {
						current = "0";
					} else {
						current = rst1.getString(2);
						current = ad.indexcompose(current);
						current = AdjustDecimal.ArrangeAsNumeric(current);

					}

					if (rst1.getString(3) == null) {
						status = "--";
					} else {
						double change = (double) Double.parseDouble(strpchange);
						if (change > 0) {
							status = "up";
						} else {
							if (change == 0.00) {
								status = "mid";
							} else {
								// dd = new Double(change);
								status = "down";
							}
						}
					}

					if (rst1.getString(3) == null) {
						indexopen = "0.00";
					} else {
						indexopen = rst1.getString(3);
						indexopen = ad.indexcompose(indexopen);
						indexopen = AdjustDecimal.ArrangeAsNumeric(indexopen);

					}

					if (rst1.getString(4) == null) {
						indexhigh = "0.00";
					} else {
						indexhigh = rst1.getString(4);
						indexhigh = ad.indexcompose(indexhigh);
						indexhigh = AdjustDecimal.ArrangeAsNumeric(indexhigh);
					}

					if (rst1.getString(5) == null) {
						indexlow = "0.00";
					} else {
						indexlow = rst1.getString(5);
						indexlow = ad.indexcompose(indexlow);
						indexlow = AdjustDecimal.ArrangeAsNumeric(indexlow);

					}

					if (preVal == 0.0) {
						indexclsv = "0.00";
					} else {
						indexclsv = new Double(preVal).toString();
						indexclsv = ad.indexcompose(indexclsv);
						indexclsv = AdjustDecimal.ArrangeAsNumeric(indexclsv);

					}

					/*
					 * if (rst.getString(12) == null) { indextime= "0";
					 * 
					 * } else { int time = comapreTime(rst.getString(12)); if
					 * (time > 0) { String temp = rst.getString(7);
					 * if(temp==null){ temp="0.00"; }
					 * temp=ad.indexcompose(temp);
					 * temp=AdjustDecimal.ArrangeAsNumeric(temp); indexclsv=
					 * temp;
					 * 
					 * } else { String temp1=rst.getString(6); if(temp1==null){
					 * temp1="0.00"; } temp1=ad.indexcompose(temp1);
					 * temp1=AdjustDecimal.ArrangeAsNumeric(temp1);
					 * indexclosing= temp1 ;
					 * 
					 * } }
					 */

					if (rst1.getString(8) == null) {
						tmcv = "0.00";
					} else {
						String temp = rst1.getString(8);
						if (temp == null) {
							temp = "0.00";
						}
						int k = temp.indexOf(".");
						if (k == -1) {
							tmcv = temp;

						} else {
							temp = temp + "00";
							temp = temp.substring(0, k + 2);
							temp = ad.indexcompose(temp);
							temp = AdjustDecimal.ArrangeAsNumeric(temp);
							tmcv = temp;

						}
					}

					if (rst1.getString(9) == null) {
						divisor = "0.00";
					} else {
						String temp = rst1.getString(9);
						if (temp == null) {
							temp = "0.00";
						}
						int k = temp.indexOf(".");
						if (k == -1) {
							divisor = temp;

						} else {
							temp = temp + "00";
							temp = temp.substring(0, k + 2);
							temp = ad.indexcompose(temp);
							temp = AdjustDecimal.ArrangeAsNumeric(temp);
							divisor = temp;

						}
					}

					if (rst1.getString(10) == null) {
						currency = "0";
					} else {
						currency = rst1.getString(10);
					}

					if (rst1.getString(11) == null) {
						indexdate = "0";
					} else {
						indexdate = rst1.getString(11);
					}

					Logging.debug("Vec Values =" + indexname + " : "
							+ indexid + " : " + current + " : " + indexopen
							+ " : " + indexhigh + " : " + indexlow + " : "
							+ indexclosing + " : " + vachange + " : " + tmcv
							+ " : " + divisor + " : " + currency + ": "
							+ indexdate + ": " + indexclsv + ": " + indextime);
					detailindex = new DetailIndex(indexname, indexid, current,
							status, indexopen, indexhigh, indexlow,
							indexclosing, vachange, tmcv, divisor, currency,
							indexdate, indexclsv, indextime);
					tempdata.add(detailindex);
				}

				con.closeDynaCon();
			} catch (SQLException sqlexp) {
				Logging.error("SQL Error :" + sqlexp.getMessage());
			}
		} catch (Exception ee) {
			Logging.error("DEbug" + ee.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error(" Error : Unable to close connection "
							+ ex.getMessage());
				}
				Logging.error(" Error : Unable to close connection "
						+ ee.getMessage());
			}
		}
		Logging.debug("Return Index_details of size " + details.size());
		details = tempdata;
		Logging.debug("The vector is......... " + ve);
		setVa(ve);
		return details;
	}

	public void setDetails(ArrayList details) {
		this.details = details;
	}

	/**
	 * @param index_details
	 *            The index_details to set.
	 */
	public void setIndex_details(Vector index_details) {
		this.index_details = index_details;
	}

	/**
	 * @return Returns the index_details.
	 */
	public Vector getIndex_details() {
		Connection connection = null;
		String l_date = "";
		String date1 = from;
//		org.jfree.chart.demo.servlet.AdjustDecimal ad = new org.jfree.chart.demo.servlet.AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		Logging.debug("Before if  " + date1);
		// Logging.getDebug(dt_today.equals(null));
		if ((date1 == null) || (date1.equals("null"))) {
			Logging.debug("date null inside if");
			SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
			Date dt = new Date();
			l_date = fr.format(dt).toString();
		}
		Logging.debug(l_date);
		index_details = new Vector();
		// Connect con=new Connect();
		Connect con = ConnectInit.getConnect();
		try {
			if (connection == null)
				connection = con.getdbConnection();

			if ((date1 == null) || (date1.equals("null"))) {
				rst = con.getLatestIndexDetails("get_latest_all_index_details",
						l_date, userid1);
				rst1 = con.getLatestIndexDetails1(
						"get_latest_all_index_details_bench", l_date);
			} else {
				rst = con.getIndexDetails("all_index_details", date1);
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

					// Logging.getDebug("inside while");
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
					/*
					 * if (rst.getString(3) == null) { index_details.add(i,
					 * "--"); } else { double change =
					 * (double)Double.parseDouble(strpchange); if (change > 0) {
					 * index_details.add(i, "up"); } else { if(change==0.00) {
					 * index_details.add(i, "mid"); }else{ //dd = new
					 * Double(change); index_details.add(i, "down"); } } } i++;
					 */

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
					 * if (rst.getString(8) == null) { index_details.add(i,
					 * "0.00"); } else { String temp=dd.toString();
					 * if(temp==null){ temp="0.00"; } int k=temp.indexOf(".");
					 * if(k==-1){ index_details.add(i,temp ); }else{
					 * temp=temp+"00"; temp=temp.substring(0,k+2);
					 * index_details.add(i, temp); } }
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
				// **********************Code for Export to excel By: Manoj
				// Adekar***************************

				while (rst1.next()) {
					String strpchange = "0.00";
					if (((double) rst1.getDouble(7)) != 0.00) {
						double pchange = (((double) rst1.getDouble(2) - (double) rst1
								.getDouble(7)) / (double) rst1.getDouble(7)) * 100;
						strpchange = new Double(pchange).toString();
						Logging.debug("strpchange before adjusting is "
								+ strpchange);
						strpchange = ad.indexcompose(strpchange);
						Logging.debug("strpchange after adjusting is "
								+ strpchange);
					}
					if (rst1.getString(13) == null) {

						index_details.add(i, "0");
					} else {
						index_details.add(i, rst1.getString(13));
					}
					i++;

					// Logging.getDebug("inside while");
					if (rst1.getString(1) == null) {
						index_details.add(i, "--");
					} else {

						index_details.add(i, rst1.getString(1));

					}
					i++;

					if (rst1.getString(2) == null) {
						index_details.add(i, "0.00");
					} else {
						value = rst1.getString(2);
						value = ad.indexcompose(value);
						index_details.add(i, value);
					}
					i++;
					/*
					 * if (rst1.getString(3) == null) { index_details.add(i,
					 * "--"); } else { double change =
					 * (double)Double.parseDouble(strpchange); if (change > 0) {
					 * index_details.add(i, "up"); } else { if(change==0.00) {
					 * index_details.add(i, "mid"); }else{ //dd = new
					 * Double(change); index_details.add(i, "down"); } } } i++;
					 */

					if (rst1.getString(3) == null) {
						index_details.add(i, "0.00");
					} else {
						open = rst1.getString(3);
						open = ad.indexcompose(open);
						index_details.add(i, open);
					}
					i++;

					if (rst1.getString(4) == null) {
						index_details.add(i, "0.00");
					} else {
						high = rst1.getString(4);
						high = ad.indexcompose(high);
						index_details.add(i, high);
					}
					i++;

					if (rst1.getString(5) == null) {
						index_details.add(i, "0.00");
					} else {
						low = rst1.getString(5);
						low = ad.indexcompose(low);
						index_details.add(i, low);
					}
					i++;

					if (rst1.getString(12) == null) {
						index_details.add(i, "0");
					} else {
						int time = comapreTime(rst1.getString(12));
						if (time > 0) {
							String temp = rst1.getString(7);
							if (temp == null) {
								temp = "0.00";
							}
							temp = ad.indexcompose(temp);
							index_details.add(i, temp);
						} else {
							String temp1 = rst1.getString(6);
							if (temp1 == null) {
								temp1 = "0.00";
							}
							temp1 = ad.indexcompose(temp1);
							index_details.add(i, temp1);
						}
					}
					i++;

					/*
					 * if (rst1.getString(8) == null) { index_details.add(i,
					 * "0.00"); } else { String temp=dd.toString();
					 * if(temp==null){ temp="0.00"; } int k=temp.indexOf(".");
					 * if(k==-1){ index_details.add(i,temp ); }else{
					 * temp=temp+"00"; temp=temp.substring(0,k+2);
					 * index_details.add(i, temp); } }
					 */
					index_details.add(i, strpchange);
					i++;

					if (rst1.getString(8) == null) {
						index_details.add(i, "0.00");
					} else {
						String temp = rst1.getString(8);
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
					if (rst1.getString(9) == null) {
						index_details.add(i, "0.00");
					} else {
						String temp = rst1.getString(9);
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
					if (rst1.getString(10) == null) {
						index_details.add(i, "--");
					} else {
						index_details.add(i, rst1.getString(10));
					}
					i++;
					if (rst1.getString(11) == null) {
						index_details.add(i, "--");
					} else {
						index_details.add(i, rst1.getString(11));
					}
					i++;

					// Upto Here

				}
			} catch (SQLException sqlexp) {
				Logging.error("SQL Error :" + sqlexp.getMessage());
			}
		} catch (Exception ee) {
			Logging.error("DEbug" + ee.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error(" Error : Unable to close connection "
							+ ex.getMessage());
				}
				Logging.error(" Error : Unable to close connection "
						+ ee.getMessage());
			}
		}
		Logging.debug("Return Index_details of size "
				+ index_details.size());
		return index_details;
	}

	public String getIndexListUrl(HttpServletRequest request) {
		String url = null;
//		org.jfree.chart.demo.servlet.ComposeIndex ci = new org.jfree.chart.demo.servlet.ComposeIndex();
		ComposeIndex ci=ConnectInit.getComposeIndex();
		String[] indid = request.getParameterValues("indexid1");
		String button = request.getParameter("operation");
		if (button != null) {
			String idcorr = "D1=";
			String idcorr1 = null;
			String report = request.getParameter("D1");
			if (indid != null) {
				Logging
						.debug("index id array length is " + indid.length);
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
							url = "/pages/reports/IndexCompare1S.jsp?"
									+ idcorr1 + "&from=" + fdate + "&to="
									+ tdate + "&B1=Compare";
							Logging.debug("url is " + url);
						}

						if (report.equals("3")) {
							url = "/pages/reports/IndexReturns_VolatilityS.jsp?"
									+ idcorr
									+ "&from="
									+ fdate
									+ "&to="
									+ tdate + "&B1=View";
							Logging.debug("getIndexListUrl " + url);
						}
					}
				}
			}

		}
		return url;
	}

	public void setIndexListUrl(String logi1) {
		String url = logi1;
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

	public String getIndexid1() {
		Logging.debug(" ******** value oof indexid " + indexid1);
		return indexid1;
	}

	public void setIndexid1(String logi) {
		indexid1 = logi;
	}

	public Vector getVa() {
		Logging.debug(" ******** value oof indexid " + va);
		return va;
	}

	public void setVa(Vector va) {
		this.va = va;
	}

	public String getB_showChild() {
		return b_showChild;
	}

	public void setB_showChild(String child) {
		b_showChild = child;
	}

	public Vector getVw() {
		if (vw != null)
			Logging.debug(" size of vector " + vw.size());
		return vw;
	}

	/**
	 * @return Returns the vw.
	 */
	public void setVw(Vector vw) {
		this.vw = vw;
	}

	public void setComputetotalreturns(String computetotalreturns) {
		this.computetotalreturns = computetotalreturns;
	}

	Connect c = ConnectInit.getConnect();

	Collection indexcollection;

	public Collection getIndexcollection() {
		Connection connection = null;
		Logging.debug("b_ShowChild  :: " + getB_showChild());
		if (getB_showChild() != null && getB_showChild().trim().equals("on")) {
			query = ConnectInit.queries.getProperty("index_list");
		} else {
			query = ConnectInit.queries.getProperty("index_list_without_child");
		}
		String id1 = null;
		Vector v = new Vector();
		ResultSet rs;
		try {
			if (connection == null)
				connection = c.getdbConnection();

			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			AcessControl asc = ConnectInit.getAcessControl();
		//	AcessControl asc = new AcessControl();
			String NotSelected = asc.getLangValues("Masters.NotSelected");
			Logging.debug(" Inside getIndexcollection(): Not Selected ="
					+ NotSelected);

			v.add(new LabelValueBean("Not Selected", "0"));
			while (rs.next()) {
				id1 = rs.getString(1);
				v.add(new LabelValueBean(rs.getString(2), id1));
			}
			indexcollection = v;
		} catch (Exception e) {
			// TODO: handle exception
			Logging.error(" Error :" + e.getMessage());
			//e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error(" Error : Unable to close connection "
							+ ex.getMessage());
				}
				Logging.error(" Error : Unable to close connection "
						+ ee.getMessage());
			}
		}
		return indexcollection;
	}

	/**
	 * @param indexcollection
	 *            The indexcollection to set.
	 */
	public void setIndexcollection(Collection indexcollection) {
		this.indexcollection = indexcollection;
	}

	public String getIndex() {
		return index;
	}

	/**
	 * @param index
	 *            The index to set.
	 */
	public void setIndex(String index) {
		Logging.debug("setIndex index " + index);
		if (index != null)
			this.index = index;
	}

	/**
	 * @return Returns the selectCollection.
	 */
	public String getCompute() {
		return compute;
	}

	/**
	 * @param compute
	 *            The compute to set.
	 */
	public void setCompute(String compute) {
		this.compute = compute;
	}

	public String getText() {
		return text;
	}

	/**
	 * 
	 * @param text
	 *            The text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return Returns the clear.
	 */
	public String getClear() {
		return clear;
	}

	/**
	 * @param clear
	 *            The clear to set.
	 */
	public void setClear(String clear) {
		this.clear = clear;
	}

	/**
	 * @return Returns the from.
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            The fromDate to set.
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return check Returns the check
	 */
	public String getCheck() {
		return check;
	}

	/**
	 * 
	 * @param check
	 *            The check to set
	 */
	public void setCheck(String check) {
		this.check = check;
	}

	/**
	 * 
	 * @return checkChart Returns the checkChart
	 */
	public String getCheckChart() {
		return checkChart;
	}

	/**
	 * 
	 * @param checkChart
	 *            The checkChart to set
	 */
	public void setCheckChart(String checkChart) {
		this.checkChart = checkChart;
	}

	/**
	 * @return Returns the selectStock.
	 */
	public String getSelectStock() {

		return selectStock;
	}

	/**
	 * @param selectStock
	 *            The selectStock to set.
	 */
	public void setSelectStock(String selectStock) {
		this.selectStock = selectStock;
	}

	/**
	 * Database Connectivity
	 * */
	public void dbconnect() {
		Connect con = ConnectInit.getConnect();
		try {
			if (connection == null) {
				connection = con.getdbConnection();
			}
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		}

	}

	public void reset_stkevent() {
		from = null;
		go = null;
		clear = null;
		to = null;
	}

	/**
	 * @return Returns the toDate.
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to
	 *            The toDate to set.
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return Returns the defaultVal.
	 */
	public String getDefaultVal() {
		return defaultVal;
	}

	/**
	 * @param defaultVal
	 *            The defaultVal to set.
	 */
	public void setDefaultVal(String defaultVal) {
		this.defaultVal = defaultVal;
	}

	public String getSelectIndex() {

		return selectIndex;
	}

	/**
	 * @param select
	 *            The select to set.
	 */
	public void setSelectIndex(String selectIndex) {

		this.selectIndex = selectIndex;
	}

	public String getRoleid1() {
		return roleid1;
	}

	public void setRoleid1(String roleid1) {
		this.roleid1 = roleid1;
	}

}
