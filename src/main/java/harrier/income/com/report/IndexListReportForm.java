/**
 * @author Manoj Adekar
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import app.*;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;
import org.jfree.chart.demo.servlet.AdjustDecimal;

import com.harrier.initializeation.ConnectInit;

import java.lang.Object;
import java.text.DateFormat;

public class IndexListReportForm extends ActionForm {
	Logger Logging = Logger.getLogger(IndexListReportForm.class);
	private String from = null, go = null, clear = null, to = null,
			check = null, checkChart = null, text = null;
	// private Collection selectIndexCollection=null;

	// static double total=0.00;,compute,indexid1,oneday=null,
//	AdjustDecimal ad = new AdjustDecimal();
	String reset, computetotalreturns, b_showChild, query, index, newdt = null,
			one_month = null, predate = null, userid1 = null, roleid1 = null;
	ArrayList details = null;
	// private Vector vw,index_details,va;
	private ResultSet rst;
	private ResultSet rst1;

	// app.Connect con=new app.Connect();

	/** RESEST ALL FORM FIELDS **/
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// selectIndexCollection=null;
		from = null;
		go = null;
		to = null;
		clear = null;

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
		// va=new Vector();
		// Vector ve=new Vector();
		// indexclsv=null,currency=null,indextime=null,,close=null
		String l_date = "";

		String value = null, indexname = null, indexid = null, onemonth = null, current = null, check1 = null, indexclosing = null, indexdate = null, oneweek = null, vachange = null, minmax = null, status1W = null, status1M = null, fwh = null, fwl = null;
		Logging.debug("date null inside if");
		SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
		Date dt = new Date();
		l_date = fr.format(dt).toString();
		Logging.debug(l_date);

		// int i = 0;
		// Double dd = new Double("0");
		// Logging.getDebug("setIndex_details of Compose Index");
		// String value=null;

		ArrayList tempdata = new ArrayList();
		details = new ArrayList();
		IndexListReportDetails detailindex;

		// Connect con=new app.Connect();
		Connect con = ConnectInit.getConnect();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		try {
			if (connection == null)
				connection = con.getdbConnection();

			// Change by Manoj Adekar: Dated 4-11-2008
			if (roleid1.equals("1")) {
				rst = con.getLatestIndexDetails(
						"new_get_latest_all_index_details_user", l_date,
						userid1);
				// rst1 =
				// con.getLatestIndexDetails1("new_get_latest_all_index_details_bench",l_date);
			} else {
				rst = con.getLatestIndexDetails(
						"new_get_latest_all_index_details_user", l_date,
						userid1);
				rst1 = con.getLatestIndexDetails1(
						"new_get_latest_all_index_details_bench", l_date);
			}

			newdt = getNewdt(7);
			one_month = getNewDate(30);

			// System.out.println("NEW Date ="+newdt);
			// System.out.println("NEW Month ="+one_month);
			try {
				while (rst.next()) {
					String strpchange = "0.00", status = null, mnmx = "0.00";
					String curindid = null, curdate = null;
					curindid = rst.getString("index_id");
					curdate = rst.getString("index_value_date");

					predate = con.getMaxDate("get_before_max_date", curdate,
							curindid);
					double preVal = 0.00;

					preVal = con
							.getIndexValue("get_oneweek_before_index_value",
									predate, curindid);

					// System.out.println("Previous Date=="+predate);
					// System.out.println("Previous Index Value=="+preVal);

					if ((((double) rst.getDouble("index_closing_value")) != 0.00)
							&& (preVal != 0.00)) {
						double pchange = (((double) rst
								.getDouble("index_closing_value") - (double) preVal) / (double) preVal) * 100;
						strpchange = new Double(pchange).toString();
						Logging.debug("strpchange before adjusting is "
								+ strpchange);
						strpchange = ad.indexcompose(strpchange);
						vachange = strpchange;
						Logging.debug("strpchange after adjusting is "
								+ strpchange);

						double pmnmx = (double) rst
								.getDouble("index_closing_value")
								- (double) preVal;
						if (pmnmx < 0.00) {
							pmnmx = pmnmx * (-1);
						}
						mnmx = new Double(pmnmx).toString();
						Logging.debug("Minmax before adjusting is " + mnmx);
						mnmx = ad.indexcompose(mnmx);
						minmax = mnmx;
						Logging.debug("Minmax after adjusting is " + mnmx);

					} else {
						vachange = "0.00";
						minmax = "0.00";
					}

					String oneweek1 = null;
					double s = 0.00;
					s = con.getIndexValue("get_oneweek_before_index_value",
							newdt, curindid);
					// System.out.println("One Week Before Index value=="+s);

					if ((rst.getString("index_closing_value") != null)
							&& (s != 0.00)) {
						double pweek1 = (((double) rst
								.getDouble("index_closing_value") - (double) s) / (double) s) * 100;
						oneweek1 = new Double(pweek1).toString();
						Logging.debug("oneweek before adjusting is "
								+ oneweek1);
						oneweek1 = ad.indexcompose(oneweek1);
						oneweek = oneweek1;
						Logging.debug("oneweek after adjusting is "
								+ oneweek1);
					} else {
						oneweek = "0.00";
					}

					if (rst.getString("index_closing_value") == null) {
						status1W = "--";
					} else {
						double changeW = (double) Double.parseDouble(oneweek);
						if (changeW > 0.00) {
							status1W = "up1W";
						} else {
							if (changeW == 0.00) {
								status1W = "mid1W";
							} else {
								status1W = "down1W";
							}
						}
					}

					double s1 = 0.00;
					s1 = con.getIndexValue("get_oneweek_before_index_value",
							one_month, curindid);
					// System.out.println("One Month Before Index value =="+s1);
					String onemonth1 = null;
					/*
					 * String ndt2=null;
					 * 
					 * 
					 * if(s1==0.00) {
					 * 
					 * 
					 * ndt2=con.getMaxDate("get_before_max_date",one_month,curindid
					 * ); System.out.println("One week Before New Date=="+ndt2);
					 * double newval2 =0.00; newval2=
					 * con.getIndexValue("get_oneweek_before_index_value"
					 * ,ndt2,curindid);
					 * 
					 * if((rst.getString("index_closing_value")!=null)&&
					 * (newval2!=0.00)) { double
					 * pmonth1=(((double)rst.getDouble(
					 * "index_closing_value")-(double
					 * )newval2)/(double)newval2)*100; onemonth1=new
					 * Double(pmonth1).toString();
					 * Logging.getDebug("onemonth before adjusting is "
					 * +onemonth); onemonth1=ad.indexcompose(onemonth1);
					 * onemonth=onemonth1;
					 * Logging.getDebug("onemonth after adjusting is "
					 * +onemonth); } else { onemonth="0.00"; }
					 * 
					 * } else {
					 */

					if ((rst.getString("index_closing_value") != null)
							&& (s1 != 0.00)) {
						double pmonth1 = (((double) rst
								.getDouble("index_closing_value") - (double) s1) / (double) s1) * 100;
						onemonth1 = new Double(pmonth1).toString();
						Logging.debug("onemonth before adjusting is "
								+ onemonth);
						onemonth1 = ad.indexcompose(onemonth1);
						onemonth = onemonth1;
						Logging.debug("onemonth after adjusting is "
								+ onemonth);
					} else {
						onemonth = "0.00";
					}

					if (rst.getString("index_closing_value") == null) {
						status1M = "--";
					} else {
						double changeM = (double) Double.parseDouble(onemonth);
						if (changeM > 0.00) {
							status1M = "up1M";
						} else {
							if (changeM == 0.00) {
								status1M = "mid1M";
							} else {
								status1M = "down1M";
							}
						}
					}

					if (rst.getString("index_id") == null) {
						indexid = "0";

					} else {
						indexid = rst.getString("index_id");
						// ve.add(indexid);
					}

					if (rst.getString("index_name") == null) {
						indexname = "0";
					} else {
						indexname = rst.getString("index_name");

					}

					if (rst.getString("current") == null) {
						current = "0";
					} else {
						current = rst.getString("current");
						current = ad.indexcompose(current);
						current = AdjustDecimal.ArrangeAsNumeric(current);

					}

					if (rst.getString("current") == null) {
						status = "--";
					} else {
						double change = (double) Double.parseDouble(strpchange);
						if (change > 0) {
							status = "up";
						} else {
							if (change == 0.00) {
								status = "mid";
							} else {
								status = "down";
							}
						}
					}

					String tempc;
					if (rst.getString("index_closing_value") == null) {
						tempc = "0.00";
						tempc = ad.indexcompose(tempc);
						tempc = AdjustDecimal.ArrangeAsNumeric(tempc);
						indexclosing = tempc;
					} else {
						tempc = rst.getString("index_closing_value");
						tempc = ad.indexcompose(tempc);
						tempc = AdjustDecimal.ArrangeAsNumeric(tempc);
						indexclosing = tempc;
					}

					if (rst.getString("index_value_date") == null) {
						indexdate = "0";
					} else {
						indexdate = rst.getString("index_value_date");
					}

					if (rst.getString("FWH") == null) {
						fwh = "0";
					} else {
						fwh = rst.getString("FWH");
						fwh = ad.indexcompose(fwh);
						fwh = AdjustDecimal.ArrangeAsNumeric(fwh);

					}

					if (rst.getString("FWL") == null) {
						fwl = "0";
					} else {
						fwl = rst.getString("FWL");
						fwl = ad.indexcompose(fwl);
						fwl = AdjustDecimal.ArrangeAsNumeric(fwl);

					}

					// app.Logging.getDebug("Vec Values =" + indexname +" : "+
					// indexid
					// +" : "+current+" : "+indexclosing+" : "+vachange+" :  "+indexdate+": "+indextime);
					detailindex = new IndexListReportDetails(indexname,
							indexid, current, status, minmax, oneweek,
							onemonth, indexclosing, vachange, indexdate,
							check1, status1W, status1M, fwh, fwl);
					tempdata.add(detailindex);
				}

				while (rst1.next()) {
					String strpchange = "0.00", status = null, mnmx = "0.00";
					String curindid = null, curdate = null;
					curindid = rst1.getString("index_id");
					curdate = rst1.getString("index_value_date");

					predate = con.getMaxDate("get_before_max_date", curdate,
							curindid);
					double preVal = 0.00;

					preVal = con
							.getIndexValue("get_oneweek_before_index_value",
									predate, curindid);

					// System.out.println("Previous Date=="+predate);
					// System.out.println("Previous Index Value=="+preVal);

					if ((((double) rst1.getDouble("index_closing_value")) != 0.00)
							&& (preVal != 0.00)) {
						double pchange = (((double) rst1
								.getDouble("index_closing_value") - (double) preVal) / (double) preVal) * 100;
						strpchange = new Double(pchange).toString();
						Logging.debug("strpchange before adjusting is "
								+ strpchange);
						strpchange = ad.indexcompose(strpchange);
						vachange = strpchange;
						Logging.debug("strpchange after adjusting is "
								+ strpchange);

						double pmnmx = (double) rst1
								.getDouble("index_closing_value")
								- (double) preVal;
						if (pmnmx < 0.00) {
							pmnmx = pmnmx * (-1);
						}
						mnmx = new Double(pmnmx).toString();
						Logging.debug("Minmax before adjusting is " + mnmx);
						mnmx = ad.indexcompose(mnmx);
						minmax = mnmx;
						Logging.debug("Minmax after adjusting is " + mnmx);

					} else {
						vachange = "0.00";
						minmax = "0.00";
					}

					String oneweek1 = null;
					double s = 0.00;
					s = con.getIndexValue("get_oneweek_before_index_value",
							newdt, curindid);
					// System.out.println("One Week Before Index value=="+s);

					if ((rst1.getString("index_closing_value") != null)
							&& (s != 0.00)) {
						double pweek1 = (((double) rst1
								.getDouble("index_closing_value") - (double) s) / (double) s) * 100;
						oneweek1 = new Double(pweek1).toString();
						Logging.debug("oneweek before adjusting is "
								+ oneweek1);
						oneweek1 = ad.indexcompose(oneweek1);
						oneweek = oneweek1;
						Logging.debug("oneweek after adjusting is "
								+ oneweek1);
					} else {
						oneweek = "0.00";
					}

					if (rst1.getString("index_closing_value") == null) {
						status1W = "--";
					} else {
						double changeW = (double) Double.parseDouble(oneweek);
						if (changeW > 0.00) {
							status1W = "up1W";
						} else {
							if (changeW == 0.00) {
								status1W = "mid1W";
							} else {
								status1W = "down1W";
							}
						}
					}

					double s1 = 0.00;
					s1 = con.getIndexValue("get_oneweek_before_index_value",
							one_month, curindid);
					// System.out.println("One Month Before Index value =="+s1);
					String onemonth1 = null;
					/*
					 * String ndt2=null;
					 * 
					 * 
					 * if(s1==0.00) {
					 * 
					 * 
					 * ndt2=con.getMaxDate("get_before_max_date",one_month,curindid
					 * ); System.out.println("One week Before New Date=="+ndt2);
					 * double newval2 =0.00; newval2=
					 * con.getIndexValue("get_oneweek_before_index_value"
					 * ,ndt2,curindid);
					 * 
					 * if((rst1.getString("index_closing_value")!=null)&&
					 * (newval2!=0.00)) { double
					 * pmonth1=(((double)rst1.getDouble
					 * ("index_closing_value")-(double
					 * )newval2)/(double)newval2)*100; onemonth1=new
					 * Double(pmonth1).toString();
					 * Logging.getDebug("onemonth before adjusting is "
					 * +onemonth); onemonth1=ad.indexcompose(onemonth1);
					 * onemonth=onemonth1;
					 * Logging.getDebug("onemonth after adjusting is "
					 * +onemonth); } else { onemonth="0.00"; }
					 * 
					 * } else {
					 */

					if ((rst1.getString("index_closing_value") != null)
							&& (s1 != 0.00)) {
						double pmonth1 = (((double) rst1
								.getDouble("index_closing_value") - (double) s1) / (double) s1) * 100;
						onemonth1 = new Double(pmonth1).toString();
						Logging.debug("onemonth before adjusting is "
								+ onemonth);
						onemonth1 = ad.indexcompose(onemonth1);
						onemonth = onemonth1;
						Logging.debug("onemonth after adjusting is "
								+ onemonth);
					} else {
						onemonth = "0.00";
					}

					if (rst1.getString("index_closing_value") == null) {
						status1M = "--";
					} else {
						double changeM = (double) Double.parseDouble(onemonth);
						if (changeM > 0.00) {
							status1M = "up1M";
						} else {
							if (changeM == 0.00) {
								status1M = "mid1M";
							} else {
								status1M = "down1M";
							}
						}
					}

					if (rst1.getString("index_id") == null) {
						indexid = "0";

					} else {
						indexid = rst1.getString("index_id");
						// ve.add(indexid);
					}

					if (rst1.getString("index_name") == null) {
						indexname = "0";
					} else {
						indexname = rst1.getString("index_name");

					}

					if (rst1.getString("current") == null) {
						current = "0";
					} else {
						current = rst1.getString("current");
						current = ad.indexcompose(current);
						current = AdjustDecimal.ArrangeAsNumeric(current);

					}

					if (rst1.getString("current") == null) {
						status = "--";
					} else {
						double change = (double) Double.parseDouble(strpchange);
						if (change > 0) {
							status = "up";
						} else {
							if (change == 0.00) {
								status = "mid";
							} else {
								status = "down";
							}
						}
					}

					String tempc;
					if (rst1.getString("index_closing_value") == null) {
						tempc = "0.00";
						tempc = ad.indexcompose(tempc);
						tempc = AdjustDecimal.ArrangeAsNumeric(tempc);
						indexclosing = tempc;
					} else {
						tempc = rst1.getString("index_closing_value");
						tempc = ad.indexcompose(tempc);
						tempc = AdjustDecimal.ArrangeAsNumeric(tempc);
						indexclosing = tempc;
					}

					if (rst1.getString("index_value_date") == null) {
						indexdate = "0";
					} else {
						indexdate = rst1.getString("index_value_date");
					}

					if (rst1.getString("FWH") == null) {
						fwh = "0";
					} else {
						fwh = rst1.getString("FWH");
						fwh = ad.indexcompose(fwh);
						fwh = AdjustDecimal.ArrangeAsNumeric(fwh);

					}

					if (rst1.getString("FWL") == null) {
						fwl = "0";
					} else {
						fwl = rst1.getString("FWL");
						fwl = ad.indexcompose(fwl);
						fwl = AdjustDecimal.ArrangeAsNumeric(fwl);

					}

					// app.Logging.getDebug("Vec Values =" + indexname +" : "+
					// indexid
					// +" : "+current+" : "+indexclosing+" : "+vachange+" :  "+indexdate+": "+indextime);
					detailindex = new IndexListReportDetails(indexname,
							indexid, current, status, minmax, oneweek,
							onemonth, indexclosing, vachange, indexdate,
							check1, status1W, status1M, fwh, fwl);
					tempdata.add(detailindex);
				}
			} catch (SQLException sqlexp) {
				Logging.error("SQL Error :" + sqlexp.getMessage());
			}

			con.closeDynaCon();
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
		// Logging.getDebug("The vector is......... "+ve);
		// setVa(ve);
		return details;
	}

	public void setDetails(ArrayList details) {
		this.details = details;
	}

	/*
	 * public Vector getVa() {
	 * app.Logging.getDebug(" ******** value oof indexid "+ va); return va; }
	 * 
	 * public void setVa(Vector va) { this.va = va; }
	 * 
	 * 
	 * public Vector getVw() { if(vw!=null)
	 * Logging.getDebug(" size of vector "+vw.size()); return vw; }
	 * 
	 * public void setVw(Vector vw) { this.vw = vw; }
	 * 
	 * public void setComputetotalreturns(String computetotalreturns) {
	 * this.computetotalreturns = computetotalreturns; }
	 * 
	 * Connect c = ConnectInit.getConnect();
	 * 
	 * Collection indexcollection;
	 * 
	 * 
	 * public Collection getIndexcollection() { Connection connection=null;
	 * Logging.getDebug("b_ShowChild  :: " + getB_showChild());
	 * 
	 * if (getB_showChild() != null && getB_showChild().trim().equals("on")) {
	 * query = c.queries.getProperty("index_list"); } else { query =
	 * c.queries.getProperty("index_list_without_child"); }
	 * 
	 * 
	 * String id1 = null; Vector v = new Vector(); ResultSet rs; try {
	 * if(connection==null) connection=c.getdbConnection();
	 * 
	 * 
	 * if (Connect.con == null) { c.getConnection(); } Statement stmt =
	 * connection.createStatement(); rs = stmt.executeQuery(query);
	 * 
	 * AcessControl asc=new AcessControl(); String
	 * NotSelected=asc.getLangValues("Masters.NotSelected");
	 * Logging.getDebug(" Inside getIndexcollection(): Not Selected ="
	 * +NotSelected);
	 * 
	 * 
	 * v.add(new LabelValueBean("Not Selected","0")); while (rs.next()) { id1 =
	 * rs.getString(1); v.add(new LabelValueBean(rs.getString(2), id1)); }
	 * indexcollection = v; } catch (Exception e) { // TODO: handle exception
	 * Logging.getError(" Error :"+e.getMessage()); e.printStackTrace(); }
	 * finally{ try{ if(connection!=null) connection.close(); }catch(Exception
	 * ee){ try{ if(connection!=null) connection.close(); }catch(Exception ex){
	 * Logging.getError(" Error : Unable to close connection "+ex.getMessage());
	 * }
	 * Logging.getError(" Error : Unable to close connection "+ee.getMessage());
	 * } } return indexcollection; }
	 * 
	 * public void setIndexcollection(Collection indexcollection) {
	 * this.indexcollection = indexcollection; }
	 */

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		Logging.debug("setIndex index " + index);
		if (index != null)
			this.index = index;
	}

	/*
	 * public String getCompute() { return compute; }
	 * 
	 * public void setCompute(String compute) { this.compute = compute; }
	 * 
	 * 
	 * public String getText(){ return text; }
	 * 
	 * public void setText(String text) { this.text=text; }
	 */

	public String getClear() {
		return clear;
	}

	public void setClear(String clear) {
		this.clear = clear;
	}

	/*
	 * public String getFrom() { return from; }
	 * 
	 * public void setFrom(String from) { this.from = from; }
	 * 
	 * 
	 * public String getCheck() { return check; }
	 * 
	 * public void setCheck(String check){ this.check=check; }
	 * 
	 * 
	 * public String getCheckChart() { return checkChart; }
	 * 
	 * public void setCheckChart(String checkChart){ this.checkChart =
	 * checkChart; }
	 * 
	 * 
	 * public String getSelectStock() {
	 * 
	 * return selectStock; }
	 * 
	 * public void setSelectStock(String selectStock) { this.selectStock =
	 * selectStock; }
	 */

	/*
	 * public void dbconnect(){
	 * 
	 * try {if(app.Connect.con==null){ con.getConnection(); } } catch (Exception
	 * e) { Logging.getError(" Error : "+e.getMessage()); }
	 * 
	 * }
	 */
	/*
	 * public String getB_showChild() { return b_showChild; } public void
	 * setB_showChild(String child) { b_showChild = child; }
	 */

	public String getNewdt(int days) {
		int daydiff = 0;
		if (days == 7) {
			daydiff = 7;
		}
		if (days == 30) {
			daydiff = 30;
		}
		if (days == 52) {
			daydiff = 52;
		}

		long newd = daydiff * 24 * 60 * 60 * 1000L;
		java.util.Date s4 = new Date();
		long t2 = s4.getTime();
		long t1 = t2 - newd;
		Date s1 = new Date(t1);
		SimpleDateFormat ft1 = new SimpleDateFormat("dd-MM-yyyy");
		String newdt1 = ft1.format(s1);

		return newdt1;
	}

	public String getNewDate(int days) {
		int daydiff = 0;
		if (days == 7) {
			daydiff = 7;
		}
		if (days == 30) {
			daydiff = 30;
		}
		if (days == 28) {
			daydiff = 28;
		}
		if (days == 52) {
			daydiff = 52;
		}

		long newd = daydiff * 24 * 60 * 60 * 1000L;
		java.util.Date s4 = new Date();
		long t2 = s4.getTime();
		long t1 = t2 - newd;
		Date s1 = new Date(t1);
		SimpleDateFormat ft1 = new SimpleDateFormat("dd-MM-yyyy");
		String newdate = ft1.format(s1);

		return newdate;
	}

	public String getUserid1() {
		return userid1;
	}

	public void setUserid1(String userid1) {
		this.userid1 = userid1;
	}

	public String getRoleid1() {
		return roleid1;
	}

	public void setRoleid1(String roleid1) {
		this.roleid1 = roleid1;
	}

	/*
	 * public String getBeforeDate(int days) { int daydiff=0; if(days==1){
	 * daydiff=1; } if(days==2){ daydiff=2; }
	 * 
	 * long newd = daydiff * 24 * 60 * 60 * 1000L; java.util.Date s4 = new
	 * Date(); long t2 =s4.getTime(); long t1 = t2 - newd; Date s1 = new
	 * Date(t1); SimpleDateFormat ft1 = new SimpleDateFormat("dd-MM-yyyy");
	 * String newdate = ft1.format(s1);
	 * System.out.println("Previous NewDate ==>"+newdate); return newdate; }
	 */

}
