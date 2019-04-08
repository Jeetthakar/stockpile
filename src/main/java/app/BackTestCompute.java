/*
 * Created on Dec 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import harrier.income.com.compute.CIndexCalculator;
import harrier.income.com.entities.CFormula;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jfree.chart.demo.servlet.AdjustDecimal;

import com.harrier.initializeation.ConnectInit;

/**
 * @author neha
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class BackTestCompute {
	static Logger Logging = Logger.getLogger(BackTestCompute.class);
	double btvalue = 0.0;

	String testvalue = null;

	int index_type = 0;

	// private CFormula cFor = new CFormula();

	private double ltp = 0, iwf = 0, mcv = 0, exch = 1, tmcv = 0, divisor = 0,
			base_value = 0, indexVal = 0, flag = 0, fto_exch = 1,
			icomp_mcv = 0, icomp_iwf = 0;

	double newdivisor = 0.0;

	double new_tmcv = 0.0;

	private Vector v = new Vector();

	private long ml = 0, tis = 0, stkid = 0, curridStk = 0, curridIndex = 0;

	private long stkId = 0, currId = 0;

	private int type_of_index;

	BackTestIndexForm btf = new BackTestIndexForm();

	Corporate corp = new Corporate();

	// harrier.income.com.compute.CIndexCalculator cic = new
	// harrier.income.com.compute.CIndexCalculator();

	String indexid = btf.getIndex();

	String basedate = btf.getBase_date();

	String idxfromdate = btf.getIndex_from();

	String basevalue = btf.getBase_value();

	// String excl_date=btf.getExcl_date();
	private static String Stock_id = null;

	private String Stock_id_incl = null;

	String excl_stock_id = btf.getStkselect();

	String returnString = null;

	String stk[] = new String[1];

	double newmcv1 = 0.0;

	Connect c = ConnectInit.getConnect();

	Connection connection = null;
	String inclutionsArray[] = null;
	String exclusionsArray[] = null;

	public String computebacktestindex(String indexid, String basedate,
			String idxfromdate, String basevalue, HttpServletRequest req,
			String settlement, String close, String toComputechildindexes,
			String ListOfChildIndices, String[] excl_stocks,
			ArrayList<String> exclusionDates, String[] incl_stocks,
			ArrayList<String> inclusionDates) {

		String[] exclustionsDaatesArray = exclusionDates
				.toArray(new String[exclusionDates.size()]);
		String[] inclustionsDaatesArray = inclusionDates
				.toArray(new String[inclusionDates.size()]);
		AdjustDecimal ad = new AdjustDecimal();
		ArrayList datelistbeforebasedate = new ArrayList();
		ArrayList arrayList = new ArrayList();

		PreparedStatement statement = null;
		ResultSet resultSet = null;

		int diff = CompareDate(basedate, idxfromdate);

		if (diff > 0) {
			IncomeLibrary incomeLibrary = new IncomeLibrary();
			arrayList = incomeLibrary.getListOfDates(basedate, indexid);
			datelistbeforebasedate = getDateListBeforeBaseDate(basedate,
					idxfromdate);
		}
		try {

			if (incl_stocks != null) {
				inclutionsArray = new String[incl_stocks.length];
			}
			if (excl_stocks != null) {
				exclusionsArray = new String[excl_stocks.length];
			}
			// PreparedStatement stmt1, stmt2, stmt3 = null;
			// ResultSet rst1, rst2, rst3 = null;
			try {

				if (connection == null)
					connection = c.getdbConnection();
				statement = connection.prepareStatement(ConnectInit.queries
						.getProperty("get_index_type"));
				statement.setString(1, indexid);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					index_type = resultSet.getInt("index_type_id");
				}
				if (incl_stocks != null) {
					for (int i = 0; i < incl_stocks.length; i++) {
						String value = "";
						connection = c.getdbConnection();
						statement = connection
								.prepareStatement(ConnectInit.queries
										.getProperty("get_stock_id"));
						statement.setString(1, incl_stocks[i]);
						resultSet = statement.executeQuery();
						while (resultSet.next()) {
							value = resultSet.getString("stock_id");
						}
						inclutionsArray[i] = value;
					}
				}
				if (excl_stocks != null) {
					for (int i = 0; i < excl_stocks.length; i++) {
						String value = "";
						connection = c.getdbConnection();
						statement = connection
								.prepareStatement(ConnectInit.queries
										.getProperty("get_stock_id"));
						statement.setString(1, excl_stocks[i]);
						resultSet = statement.executeQuery();
						while (resultSet.next()) {
							value = resultSet.getString("stock_id");
						}
						exclusionsArray[i] = value;
					}
				}

			} catch (Exception e) {
				Logging.error(" Error : " + e.getMessage());
			}
			Logging.debug("index type for calculation is : " + index_type);
			if (index_type == 1 || index_type == 2) {
				boolean temp = false;
				Logging.debug("before base date arraylist size is "
						+ datelistbeforebasedate.size());
				if (datelistbeforebasedate.size() != 0) {
					Logging.debug("before base date for loop");
					for (int k = 0; k < datelistbeforebasedate.size(); k++) {
						String element = (String) datelistbeforebasedate.get(k);
						Logging.debug("element : : " + (element));
						Logging.debug(connection
								+ "Before111 computing parent index with index value : ");
						boolean cpricestatus = false;
						cpricestatus = checkPriceAvailable(element, indexid);
						if (cpricestatus == true) {
							if ((String) datelistbeforebasedate.get(k) != null) {
								returnString = computeIndexNormallyBeforeBaseDate(
										indexid, "n", "y", element, temp,
										connection);
							} else {
								temp = true;
								returnString = computeIndexNormallyBeforeBaseDate(
										indexid, settlement, close, element,
										temp, connection);
							}
						}
						if (exclustionsDaatesArray != null
								&& excl_stocks != null) {
							// Corporate corp = new Corporate();
							for (int i = 0; i < exclustionsDaatesArray.length; i++) {
								if (exclustionsDaatesArray[i].equals(element)) {
									String stk[] = new String[1];
									stk[0] = exclusionsArray[i];
									insert_hash(stk, corp);
									applyIndexDetail(connection, c, corp,
											indexid, "deletestock",
											exclustionsDaatesArray[i], i);
								}
							}
						}
						if (inclustionsDaatesArray != null
								&& incl_stocks != null) {
							// Corporate corp = new Corporate();
							for (int i = 0; i < inclusionDates.size(); i++) {
								if (inclustionsDaatesArray[i].equals(element)) {
									String stk[] = new String[1];
									stk[0] = inclutionsArray[i];
									insert_hash(stk, corp);
									applyIndexDetail(connection, c, corp,
											indexid, "addstock",
											inclustionsDaatesArray[i], i);
								}
							}
						}
					}
				}
				Logging.debug("after calculating index value before base date");
				Logging.debug("this again  : " + this);
				returnString = ad.shareholdingpatt(returnString);
				Logging.debug("index value after calculation " + returnString);
				return returnString;
			}
		} catch (Exception e) {
			Logging.debug(" Error : " + e.getMessage());
			return "---";
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (statement != null)
					statement.close();
				if (resultSet != null)
					resultSet.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close connection "
						+ ee.getMessage());
			}
		}
		testvalue = returnString;
		return testvalue;
	}

	public String computebacktestindex(String indexid, String basedate,
			String idxfromdate, String basevalue, HttpServletRequest req,
			String settlement, String close, String toComputechildindexes,
			String ListOfChildIndices, String excl_stock, String excl_date,
			String incl_stock, String incl_date) {

		// Logging.debug("value
		// ==>"+indexid+"basedate"+basedate+"idexfrm"+idxfromdate+"basevalue"+basevalue);
		// AdjustDecimal ad = new AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		ArrayList datelistbeforebasedate = new ArrayList();
		ArrayList arrayList = new ArrayList();
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;
		ResultSet rst1 = null;
		ResultSet rst2 = null;
		ResultSet rst3 = null;

		int diff = CompareDate(basedate, idxfromdate);
		// Logging.debug("difference in dates"+diff);
		// Logging.getDebug("indexID is "+indexID +" before base date size of
		// datelistbeforebasedate is : "+datelistbeforebasedate.size());

		if (diff > 0) {
			IncomeLibrary incomeLibrary = new IncomeLibrary();
			arrayList = incomeLibrary.getListOfDates(basedate, indexid);
			datelistbeforebasedate = getDateListBeforeBaseDate(basedate,
					idxfromdate);
		}
		// Logging.getDebug(indexID +" before base date size of
		// datelistbeforebasedate is : "+datelistbeforebasedate.size());
		// Logging.getDebug(indexID +"arrayList.size() : "+arrayList.size());
		// Logging.debug(indexid +" before base date size of
		// datelistbeforebasedate is : "+datelistbeforebasedate.size());
		// Logging.debug(indexid +"arrayList.size() : "+arrayList.size());
		try {

			try {
				if (connection == null)
					connection = c.getdbConnection();
				stmt1 = connection.prepareStatement(ConnectInit.queries
						.getProperty("get_index_type"));
				stmt1.setString(1, indexid);
				rst1 = stmt1.executeQuery();
				stmt2 = connection.prepareStatement(ConnectInit.queries
						.getProperty("get_stock_id"));
				stmt2.setString(1, excl_stock);
				rst2 = stmt2.executeQuery();
				stmt3 = connection.prepareStatement(ConnectInit.queries
						.getProperty("get_stock_id"));
				stmt3.setString(1, incl_stock);
				rst3 = stmt3.executeQuery();
				while (rst1.next()) {

					index_type = rst1.getInt("index_type_id");
				}

				while (rst2.next()) {

					Stock_id = rst2.getString("stock_id");
					Logging.debug("stock_id+ ==>>" + Stock_id);
				}

				while (rst3.next()) {

					Stock_id_incl = rst3.getString("stock_id");
					Logging.debug("stock_id incl+ ==>>" + Stock_id_incl);
				}

				rst1.close();
				rst2.close();
				rst3.close();
				stmt1.close();
				stmt2.close();
				stmt3.close();
			} catch (Exception e) {
				Logging.error(" Error : " + e.getMessage());
			}

			// Logging.debug("index type for calculation is : " +
			// index_type);
			Logging.debug("index type for calculation is : " + index_type);

			if (index_type == 1 || index_type == 2) {

				boolean temp = false;
				Logging.debug("before base date arraylist size is "
						+ datelistbeforebasedate.size());
				if (datelistbeforebasedate.size() != 0) {
					Logging.debug("before base date for loop");
					for (int k = 0; k < datelistbeforebasedate.size(); k++) {
						String element = (String) datelistbeforebasedate.get(k);
						// String predate=(String)
						// datelistbeforebasedate.get(k-1);
						Logging.debug("element : : " + (element));

						Logging.debug(connection
								+ "Before111 computing parent index with index value : ");
						boolean cpricestatus = false;
						cpricestatus = checkPriceAvailable(element, indexid);
						if (cpricestatus == true) {
							if ((String) datelistbeforebasedate.get(k) != null) {
								returnString = computeIndexNormallyBeforeBaseDate(
										indexid, "n", "y", element, temp,
										connection);
							} else {
								temp = true;
								returnString = computeIndexNormallyBeforeBaseDate(
										indexid, settlement, close, element,
										temp, connection);
							}
						}

						// code for index event started ==>

						// Logging.debug("excldate : : " + excl_date);

						// Logging.debug("exclstock : : " + excl_stock);
						// Logging.debug("incldate : : " + incl_date);
						// Logging.debug("inclstock : : " + incl_stock);
						if (excl_date != null && excl_stock != null) {

							if (excl_date.equals(element)) {
								// Logging.debug(" exclusion date gets
								// matched ");
								stk[0] = excl_stock;
								insert_hash(stk, corp);
								applyIndexDetail(connection, c, corp, indexid,
										"deletestock", excl_date); // calculate
																	// new value

								// Logging.debug("stock gets deleted");
							}

						}
						if (incl_date != null && incl_stock != null) {

							if (incl_date.equals(element)) {
								// Logging.debug(" inclusion date gets
								// matched ");
								stk[0] = incl_stock;
								insert_hash(stk, corp);
								applyIndexDetail(connection, c, corp, indexid,
										"addstock", incl_date); // calculate new
																// value

								// Logging.debug("stock gets added");
							}

						}
						// index event code ended <==

					}
				}
				Logging.debug("after calculating index value before base date");
				/*
				 * Logging.getDebug("arrayList.size() : "+arrayList.size());
				 * Iterator iter = arrayList.iterator(); while (iter.hasNext())
				 * { String element = (String) iter.next();
				 * Logging.getDebug("element : : " + (element));
				 * Logging.debug("elementarraylist : : " + (element));
				 * Logging.debug(connection+"Before111 computing parent index
				 * with index value : "); boolean cpricestatus=false;
				 * cpricestatus=checkPriceAvailable(element,indexid);
				 * if(cpricestatus==true){ if (iter.hasNext()) { returnString
				 * =cic.computeIndexNormally(indexid, "n", "y",element,
				 * temp,connection); } else { temp = true; returnString =
				 * cic.computeIndexNormally
				 * (indexid,settlement,close,element,temp,connection); } } }
				 * 
				 * Logging.getDebug(connection+"After computing parent index
				 * with index value : " + returnString); //remove comment if
				 * (req!=null && req.getAttribute("tmcverror") != null)
				 * req.removeAttribute("tmcverror"); if (req!=null &&
				 * returnString.equals("----")) { req.setAttribute("tmcverror",
				 * "yes"); }
				 * 
				 * Logging.getDebug("After computing parent index with index
				 * value : " + returnString); iter = arrayList.iterator(); temp
				 * = false; while (iter.hasNext()) { String element = (String)
				 * iter.next(); Logging.getDebug("element : : " + (element));
				 * boolean cpricestatus=false;
				 * cpricestatus=checkPriceAvailable(element,indexid);
				 * if(cpricestatus==true){ if (iter.hasNext()) {
				 * cic.ComputeChildIndexes(indexid, "n",
				 * "y",toComputechildindexes, ListOfChildIndices,
				 * element,temp,connection); } else { temp = true;
				 * cic.ComputeChildIndexes(indexid, settlement,
				 * close,toComputechildindexes, ListOfChildIndices,
				 * element,temp,connection); } } }
				 */

				Logging.debug("this again  : " + this);
				returnString = ad.shareholdingpatt(returnString);
				Logging.debug("index value after calculation " + returnString);
				// Logging.debug("index value after calculation
				// "+returnString);
				return returnString;
			}

		} catch (Exception e) {
			Logging.debug(" Error : " + e.getMessage());
			return "---";
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (rst1 != null && rst2 != null && rst3 != null) {
					rst1.close();
					rst2.close();
					rst3.close();
				}

				if (stmt1 != null && stmt2 != null && stmt3 != null) {
					stmt1.close();
					stmt2.close();
					stmt3.close();
				}
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close connection "
						+ ee.getMessage());
			}
		}

		testvalue = returnString;
		return testvalue;
	}

	/**
	 * to compare two dates passed in string format. returns zero if two dates
	 * are equal ,+ve value second date less than first date parameter and -ve
	 * value if second date greater than first date parameter.
	 * 
	 * @param basedate
	 * @param idxfromdate
	 * @return
	 */
	public static int CompareDate(String basedate, String idxfromdate) {
		Date creationDate = new Date(new Integer(basedate.trim().substring(6,
				10)).intValue(),
				new Integer(basedate.trim().substring(3, 5)).intValue(),
				new Integer(basedate.trim().substring(0, 2)).intValue());
		Date hDate = new Date(
				new Integer(idxfromdate.trim().substring(6, 10)).intValue(),
				new Integer(idxfromdate.trim().substring(3, 5)).intValue(),
				new Integer(idxfromdate.trim().substring(0, 2)).intValue());
		int diff = creationDate.compareTo(hDate);
		return diff;
	}

	public ArrayList getDateListBeforeBaseDate(String bdate, String idxfromdate) {
		ArrayList arr = new ArrayList();

		int i = 0;
		int diffdate = CompareDate(bdate, idxfromdate);
		while (diffdate > 0) {
			bdate = getPreviousDate(bdate);
			Logging.debug("bdate is " + bdate);
			arr.add(i, bdate);
			i++;
			diffdate = CompareDate(bdate, idxfromdate);
			Logging.debug("diff is " + diffdate);

		}
		return arr;
	}

	public static String getPreviousDate(String dt) {

		int date;
		int month;
		int year;
		int maxdate;
		int maxmonth;
		int maxyear;
		int i = 0;
		String sdate[] = new String[3];
		// String dt="01-07-2005";
		dt = dt.replace('/', '-');
		dt = dt.trim();

		StringTokenizer st = new StringTokenizer(dt, "-");

		while (st.hasMoreTokens()) {

			sdate[i] = st.nextToken();
			i++;
		}

		date = Integer.parseInt(sdate[0]);
		month = Integer.parseInt(sdate[1]);
		year = Integer.parseInt(sdate[2]);

		GregorianCalendar gc = new GregorianCalendar(year, (month - 1), date);

		maxdate = gc.getActualMaximum(gc.DATE);
		maxmonth = gc.getActualMaximum(gc.MONTH);// returns maximum 12

		if ((date > maxdate) || (date <= 0))
			throw new NumberFormatException("invalid date");

		if ((month > 12) || (month <= 0))
			throw new NumberFormatException("invalid month");

		if (date > 1) {
			date = date - 1;
		}

		else if ((date == 1) && (month > 1)) {
			month--;
			gc = new GregorianCalendar(year, (month - 1), date);
			date = gc.getActualMaximum(gc.DATE);
		} else if ((date == 1) && (month == 1)) {
			month = 12;
			year = year - 1;
			gc = new GregorianCalendar(year, (month - 1), date);
			date = gc.getActualMaximum(gc.DATE);
		}
		String dd = new Integer(date).toString();
		String mm = new Integer(month).toString();
		if (dd.length() < 2)
			dd = "0" + date;
		if (mm.length() < 2)
			mm = "0" + month;
		String finaldate = dd + "-" + mm + "-" + year;
		// Logging.debug("finaldate is "+finaldate);
		return finaldate;

	}

	/**
	 * to check if prices available for date or not. return true if prices are
	 * available or false if prices are not available on date.
	 * 
	 * @param hist_Date
	 * @param indid
	 * @return
	 */
	public boolean checkPriceAvailable(String hist_Date, String indid) {
		boolean flag = false;

		Logging.debug("in checkPriceAvailable");
		Logging.debug("in hist_Date " + hist_Date + "  indid is  " + indid);
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			if (connection == null)
				connection = c.getdbConnection();
			pst = connection.prepareStatement(ConnectInit.queries
					.getProperty("check_for_price_on_date"));
			pst.setString(1, hist_Date);
			pst.setString(2, indid);
			rst = pst.executeQuery();
			while (rst.next()) {
				flag = true;
				break;
			}
		} catch (Exception e) {
			Logging.error("Error : " + e.getMessage());
		} finally {
			try {
				if (rst != null)
					rst.close();
				if (pst != null)
					pst.close();

			} catch (Exception ee) {
				Logging.error(" Error : " + ee.getMessage());
			}
		}
		Logging.debug("flag is " + flag);
		return flag;
	}

	// query to get values required in index computation
	public String computeIndexNormallyBeforeBaseDate(String indexID,
			String settlement, String close, String date,
			boolean updateIndexcompose, Connection connection) {
		Logging.debug("Computing Index for Index Id : " + indexID
				+ "  with settlement value = " + settlement + " date is "
				+ date);
		// CFormula cFor = new CFormula();
		CFormula cFor = ConnectInit.getCFormula();
		CIndexCalculator cic = ConnectInit.getCIndexCalculator();
		String time = getTime();
		// AdjustDecimal ad = new AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		long l_indexID = Long.parseLong(indexID);
		double high, low;
		String date1 = "";
		boolean firstDailyValue = false;
		long id, baseCurrencyIdForCurrencyTypeIndex = 66;
		long childIndexId = 0;
		double newmcv = 0.0; // added by neha 9th dec2007
		PreparedStatement pst_preStat = null;
		PreparedStatement pst3 = null;
		PreparedStatement ppt = null;
		ResultSet rst = null;
		ResultSet rst2 = null;
		ResultSet rstnew = null;

		tmcv = 0.0;
		v.clear();
		Logging.debug("initial tmcv for  Id : " + indexID + " is : " + tmcv);
		try {
			// doing normal calculation
			if (!firstDailyValue) {
				pst_preStat = connection.prepareStatement(ConnectInit.queries
						.getProperty("compute_index"));// changed by neha
														// 9thdec2007
				pst_preStat.setLong(1, l_indexID);
				// date = QueryClass.formatDate();
				pst_preStat.setString(2, date);
				// pst_preStat.setString(1, date);
				Logging.debug(pst_preStat.toString());
				rst = pst_preStat.executeQuery();// executes query to get
													// data
				// for
				// index computation
				// get all ltp values and stock id for the given date
				while (rst.next()) {
					Logging.debug("Row no." + rst.getRow());
					ltp = rst.getDouble("ltp");// get ltp
					iwf = rst.getDouble("iwf");// get iwf
					ml = rst.getLong("market_lot");// get market lot
					tis = (long) rst.getDouble("tis");// get tis
					stkid = rst.getLong("stock_id");// get stock id
					curridStk = rst.getLong("stock_currency_id");// get
					// currencyId
					// for stock

					if (type_of_index == 5) {
						curridIndex = baseCurrencyIdForCurrencyTypeIndex;
					} else {
						curridIndex = rst.getLong("base_currency_id");
					}
					date1 = rst.getString("price_date");
					Logging.debug("Index curr id" + curridIndex);
					Logging.debug("Stock curr id" + curridStk);
					if ((curridStk != curridIndex) && (flag == 0)) {
						rst2 = cic.getExchCode(connection);
						Logging.debug("Get ResultSet");
						rst2.next();
						stkId = rst2.getLong("from_currency_id");
						if (type_of_index == 5) {
							currId = baseCurrencyIdForCurrencyTypeIndex;
						} else {
							currId = rst2.getLong("to_currency_id");
						}

						fto_exch = rst2.getDouble("intra_day_ex_rate_value");
						flag = 1;
					}
					if ((curridStk != curridIndex)) {
						rst2.beforeFirst();
					}

					Logging.debug("Finally Exchange rate is : " + exch);
					try {
						String string = (new Long(curridStk)).toString();
						String string2 = (new Long(curridIndex)).toString();
						String temp = IndexCalculatorCollection
								.getIndexCurrancyExchRate(string, string2);
						if (temp != null) {
							exch = new Double(temp).doubleValue();
						} else {
							temp = IndexCalculatorCollection
									.getIndexCurrancyExchRate(string2, string);
							if (temp == null) {
								exch = 1.0;
							} else {
								exch = 1 / new Double(temp).doubleValue();
							}
						}

					} catch (Exception e) {
						// e.printStackTrace();
						exch = 1.0;
						Logging.debug(e);
						// TODO: handle exception
					}
					String exch_rate = new Double(exch).toString();
					exch_rate = ad.indexcompose4digit(exch_rate);
					exch = (double) Double.parseDouble(exch_rate);
					Logging.debug("Finally Exchange rate is : " + exch);
					Logging.debug(" " + tis);
					base_value = rst.getDouble("base_value");// get base
																// value
					// for
					// index
					// ///// mcv = cFor.calMarketCap(ltp, ml, exch, tis,
					// iwf);//calculates //////////

					ppt = connection.prepareStatement(ConnectInit.queries
							.getProperty("compute_index_back_test"));// changed
																		// by
																		// neha
																		// 9thdec2007
					ppt.setLong(1, stkid);
					// date = QueryClass.formatDate();
					ppt.setString(2, date);
					// pst_preStat.setString(1, date);

					rstnew = ppt.executeQuery();// executes query to get data
					while (rstnew.next()) {
						newmcv = rstnew.getDouble("mcv");
					}

					if (index_type == 1) {
						mcv = newmcv;// added by neha for market cap

					} else if (index_type == 2) {
						mcv = (newmcv * iwf);// added by neha
					}

					Logging.debug("mcv is " + mcv);
					// Logging.debug("mcv is "+mcv);

					v.addElement((new Double(mcv)));// collects mcv in vector
					// update mcv in index composition table

					icomp_mcv = mcv;
					try {
						if (updateIndexcompose) {
							// put mcv into database
							// Logging.getDebug("Inside try");
							Logging.debug("mcv " + icomp_mcv);
							Logging.debug("index id " + l_indexID);
							Logging.debug("stock id " + stkid);
							pst3 = connection
									.prepareStatement(ConnectInit.queries
											.getProperty("update_index_compose_mcv"));
							// Logging.getDebug("after firirng query " + pst3);
							Logging.debug("icom mcv " + icomp_mcv);
							pst3.setDouble(1, icomp_mcv);
							pst3.setLong(2, l_indexID);

							pst3.setDouble(3, stkid);

							pst3.executeUpdate();// execute query
							// Logging.getDebug("Index Composition Updated : " +
							// pst3);
						}
					} catch (SQLException e) {
						Logging.debug(e.getMessage());
						Logging.debug("index values" + e);
					}
				}
				Logging.debug("initial1 tmcv for  Id : " + indexID + " is : "
						+ tmcv + "v size is : " + v.size());
				// Logging.debug("initial1 tmcv for Id : " + indexID + " is
				// : " + tmcv + "v size is : " + v.size());
				tmcv = cFor.totalMarketCap(v);// calculate tmcv
				Logging.debug("initial2 tmcv for  Id : " + indexID + " is : "
						+ tmcv + "v size is : " + v.size());
				// Logging.debug("initial2 tmcv for Id : " + indexID + " is
				// : " + tmcv + "v size is : " + v.size());
				Logging.debug("Total Market Cap " + tmcv);
				if (tmcv == 0) {
					return "----";
				}
				// divisor=cic.getDivisorValue(indexID);
				divisor = getDivisorValue(indexID);// takes divisor from min
													// index_vale_date
				Logging.debug("divisor value is ==>" + divisor);
				indexVal = cFor.index(tmcv, divisor);// index compute
				Logging.debug("value calculated = " + indexVal);
				Logging.debug("value calculated = " + indexVal);

			}
			// normal calculation ended

			// inserts value in intra day indices
			try {
				/*
				 * try { stm = connection.createStatement(); //remove query rst
				 * = stm
				 * .executeQuery("select nextval('intra_day_indices_id')");
				 * Logging.getDebug(rst); rst.next(); } catch (SQLException e) {
				 * Logging.getDebug("error in Query"); e.getMessage(); } id =
				 * rst.getLong(1);
				 */
				pst_preStat = connection.prepareStatement(ConnectInit.queries
						.getProperty("insert_into_intra_day_indices"));

				/* pst_preStat.setLong(1, id); */
				if (type_of_index == 4) {
					pst_preStat.setLong(4, childIndexId);

				} else {
					pst_preStat.setLong(4, l_indexID);

				}
				pst_preStat.setDouble(1, indexVal);
				pst_preStat.setDouble(5, tmcv);
				// date = QueryClass.formatDate();
				pst_preStat.setString(2, date);

				pst_preStat.setString(3, time);

				pst_preStat.executeUpdate();// execute query

			} catch (SQLException e) {
				Logging.error("ERROR");
				Logging.error("index values" + e);
			}

			// select index_lowest_value,index_highest_value from index value
			// daily

			try {
				pst_preStat = connection.prepareStatement(ConnectInit.queries
						.getProperty("get_high_low_index_value"));
				if (type_of_index == 5) {
					pst_preStat.setLong(1, childIndexId);
				} else {
					pst_preStat.setLong(1, l_indexID);// set ? for index id
				}

				pst_preStat.setString(2, date);
				Logging.debug("pst_preStat to find get_high_low_index_value : "
						+ pst_preStat);
				rst2 = pst_preStat.executeQuery();// execute query
			} catch (Exception e) {
				Logging.error("ERROR");
				Logging.error("index values" + e);
			}
			// if no enteries
			rst2.next();

			if (rst2.getRow() == 0) {
				try {
					Vector avgPe_pb_dividend = cic.getAvgPe_Pb_dividend(
							indexID, date);
					Logging.debug("avgPe_pb_dividend size is "
							+ avgPe_pb_dividend.size());
					/*
					 * pst_preStat = connection .prepareStatement(p_queries
					 * .getProperty("insert_into_index_value_daily1"));
					 */
					pst_preStat = connection
							.prepareStatement(ConnectInit.queries
									.getProperty("index_compute_insert_into_index_value_daily_pe_pb"));
					if (type_of_index == 5) {
						pst_preStat.setLong(4, childIndexId);

					} else {
						pst_preStat.setLong(4, l_indexID);

					}
					// pst_preStat.setLong(6, l_indexID);
					pst_preStat.setDouble(8, tmcv);
					pst_preStat.setDouble(1, indexVal);
					pst_preStat.setDouble(2, indexVal);
					pst_preStat.setDouble(3, indexVal);
					// pst_preStat.setDouble(5, indexVal);
					pst_preStat.setString(5, date);
					pst_preStat.setDouble(6, divisor);
					// pst_preStat.setString(11, settlement);
					pst_preStat.setDouble(7, tmcv);
					pst_preStat.setDouble(9, divisor);
					if (avgPe_pb_dividend.size() == 3) {
						pst_preStat.setString(10,
								((String) avgPe_pb_dividend.get(0)));
						pst_preStat.setString(11,
								((String) avgPe_pb_dividend.get(1)));
						pst_preStat.setString(12,
								((String) avgPe_pb_dividend.get(2)));
					} else {
						pst_preStat.setString(10, "0.0");
						pst_preStat.setString(11, "0.0");
						pst_preStat.setString(12, "0.0");
					}
					Logging.debug("initially insert into  index value daily : "
							+ pst_preStat + "\nand closing value is " + close);
					pst_preStat.executeUpdate();// execute query

					cic.insertclosing_settlement(indexVal, null, settlement,
							close, date, l_indexID, connection);
				} catch (SQLException e) {
					Logging.error("ERROR");
					Logging.error("index values" + e);
				}
			} else {
				Logging.debug("privious settlement value is settlement = "
						+ rst2.getString("is_settlement_value"));
				if (rst2.getString("is_settlement_value") == null) {
					cic.insertclosing_settlement(indexVal, null, settlement,
							close, date, l_indexID, connection);
				} else {
					cic.insertclosing_settlement(indexVal,
							rst2.getString("is_settlement_value"), settlement,
							close, date, l_indexID, connection);

				}
				low = rst2.getDouble("index_lowest_value");
				high = rst2.getDouble("index_highest_value");
				if (indexVal > high) {
					// update_high_index_value

					pst_preStat = connection
							.prepareStatement(ConnectInit.queries
									.getProperty("update_high_index_value"));
					pst_preStat.setDouble(1, indexVal);
					if (type_of_index == 5) {
						pst_preStat.setLong(2, childIndexId);

					} else {
						pst_preStat.setLong(2, l_indexID);

					}
					// pst_preStat.setLong(2, l_indexID);
					pst_preStat.setString(3, date);
					pst_preStat.executeUpdate();
				}
				if (indexVal < low) {
					// update_low_index_value

					pst_preStat = connection
							.prepareStatement(ConnectInit.queries
									.getProperty("update_low_index_value"));
					pst_preStat.setDouble(1, indexVal);
					if (type_of_index == 5) {
						pst_preStat.setLong(2, childIndexId);

					} else {
						pst_preStat.setLong(2, l_indexID);

					}
					// pst_preStat.setLong(2, l_indexID);
					pst_preStat.setString(3, date);
					pst_preStat.executeUpdate();
				}
			}

		} catch (SQLException e) {
			Logging.error(e.getMessage());
		}
		// if (close == "yes") {
		if (close.trim().equals("yes")) {
			// update information_schema.index_value_daily set

			Logging.debug("in closing part");
			try {
				pst_preStat = connection
						.prepareStatement(ConnectInit.queries
								.getProperty("update_index_value_daily_set_closing_value"));
				pst_preStat.setDouble(1, indexVal);
				pst_preStat.setDouble(2, tmcv);
				if (type_of_index == 5) {
					pst_preStat.setLong(3, childIndexId);

				} else {
					pst_preStat.setLong(3, l_indexID);

				}
				// pst_preStat.setLong(3, l_indexID);
				pst_preStat.setString(4, date);
				pst_preStat.executeUpdate();

			} catch (SQLException e) {
				Logging.error(e.getMessage());
			}
		}
		String str = Double.toString(indexVal);
		Logging.debug("Index Value Calculated for Index id : " + indexID
				+ "  is : " + str);

		try {
			if (pst_preStat != null && pst3 != null && ppt != null) {
				pst_preStat.close();
				pst3.close();
				ppt.close();
			}
			if (rst != null && rst2 != null && rstnew != null) {
				rst.close();
				rst2.close();
				rstnew.close();
			}

		} catch (SQLException sq) {
			sq.printStackTrace();
		}

		if (tmcv == 0) {
			return "----";
		}
		return str;
	}

	public String getTime() {
		java.util.Date dt = new java.util.Date();
		dt.getDate();
		return dt.toString().split(" ")[3];
	}

	// this method is used to remove E power, from calculated tmcv and divisor
	public static void tmcv_div_adj(Corporate corp) {
		try {
			check_hash_error(corp);
			// org.jfree.chart.demo.servlet.AdjustDecimal adj = new
			// org.jfree.chart.demo.servlet.AdjustDecimal();
			AdjustDecimal adj = ConnectInit.getAdjustDecimal();
			int val = 0;

			// new TMCV
			String ntmcv = corp.getNewTmcv();
			StringTokenizer st = new StringTokenizer(ntmcv, ",");
			val = st.countTokens();
			if (val == 1) {
				if ((ntmcv != null) | (!(ntmcv.equals("")))) {
					ntmcv = adj.shareholdingpatt(ntmcv);
					ntmcv = AdjustDecimal.ArrangeAsNumeric(ntmcv);
					corp.setNewTmcv(ntmcv);
				}
			}
			// new Divisor
			String ndiv = corp.getNewdivisor();
			st = new StringTokenizer(ndiv, ",");
			val = st.countTokens();
			if (val == 1) {
				if ((ndiv != null) | (!(ndiv.equals("")))) {
					ndiv = adj.shareholdingpatt(ndiv);
					ndiv = AdjustDecimal.ArrangeAsNumeric(ndiv);
					corp.setNewdivisor(ndiv);
				}
			}
			// old TMCV
			String otmcv = corp.getTmcv();
			st = new StringTokenizer(otmcv, ",");
			val = st.countTokens();
			if (val == 1) {
				otmcv = AdjustDecimal.ArrangeAsNumeric(otmcv);
				corp.setTmcv(otmcv);
			}
			// old Divisor
			String odiv = corp.getDivisor();
			st = new StringTokenizer(odiv, ",");
			val = st.countTokens();
			if (val == 1) {
				odiv = AdjustDecimal.ArrangeAsNumeric(odiv);
				corp.setDivisor(odiv);
			}

			// new child TMCV
			String ntmcv1 = corp.getNewtmcv1();
			if ((ntmcv1 != null) | (!(ntmcv1.equals("")))) {
				ntmcv1 = adj.shareholdingpatt(ntmcv1);
				ntmcv1 = AdjustDecimal.ArrangeAsNumeric(ntmcv1);
				corp.setNewtmcv1(ntmcv1);
			}
			// new child Divisor
			String ndiv1 = corp.getNewdivisor1();
			if ((ndiv1 != null) | (!(ndiv1.equals("")))) {
				ndiv1 = adj.shareholdingpatt(ndiv1);
				ndiv1 = AdjustDecimal.ArrangeAsNumeric(ndiv1);
				corp.setNewdivisor1(ndiv1);
			}
			// old child TMCV
			String otmcv1 = corp.getTmcv1();
			st = new StringTokenizer(otmcv1, ",");
			otmcv1 = AdjustDecimal.ArrangeAsNumeric(otmcv1);
			corp.setTmcv1(otmcv1);
			// old child Divisor
			String odiv1 = corp.getDivisor1();
			odiv1 = AdjustDecimal.ArrangeAsNumeric(odiv1);
			corp.setDivisor1(odiv1);
		} catch (Exception e) {
			Logging.error("Error=" + e.getMessage());
		}
	}

	// this method is used to display stock's, who doesn't have the closing
	// value
	public static void check_hash_error(Corporate corp) {
		Connect connect = ConnectInit.getConnect();
		Connection con = null;
		ResultSet rs = null;

		// Change by Manoj Adekar for Dynamic connection using getdbConnection()
		// instead of getConnection()
		try {
			if (con == null) {
				con = connect.getdbConnection();
			}
			try {
				StringBuffer stb = corp.getStr();
				int ls = stb.length();
				stb.delete(0, ls);
				Hashtable hash_error = corp.getHash_error();
				Logging.debug("hash error in action=" + hash_error);
				if (hash_error.isEmpty() == false) {
					for (Enumeration enum1 = hash_error.keys(); enum1
							.hasMoreElements();) {
						String id2 = (String) enum1.nextElement();
						Logging.debug("id2 in err=" + id2);
						String query = ConnectInit.queries
								.getProperty("detail_stock_master");
						rs = ListTypeClass1.getResult1(con, query, id2);
						rs.next();
						String stock_id = rs.getString("stock_name");
						stb.append(stock_id);
					}
					corp.setStr(stb);
				}
				Logging.debug("string buff==" + corp.getStr());
			} catch (Exception e) {
				Logging.error("Error==" + e.getMessage());
			}
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
				if (rs != null)
					rs.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
	}

	public static void insert_hash(String stk[], Corporate corp) {
		ref_hash(corp);
		Hashtable hash1 = corp.getHash1();
		hash1.clear();
		corp.setHash1(hash1);
		hash1 = corp.getHash1();
		Hashtable hash2 = corp.getHash2();
		hash2.clear();
		corp.setHash2(hash2);
		hash2 = corp.getHash2();
		int val = 0;
		if (stk != null) {
			val = stk.length;
		}
		for (int i = 0; i < val; i++) {
			String stk1 = "false:" + stk[i];
			hash1.put(new String(stk1), new String(stk[i]));
			hash2.put(new String(stk1), new String(stk[i]));
		}
		corp.setHash1(hash1);
		corp.setHash2(hash2);
	}

	public static void ref_hash(Corporate corp) {
		Hashtable hash1 = corp.getHash1();
		Hashtable hash2 = corp.getHash2();
		hash1.clear();
		hash2.clear();
		corp.setHash1(hash1);
		corp.setHash2(hash2);

	}

	public void applyIndexDetail(Connection con, Connect connect,
			Corporate corporateact, String indexid, String corpid,
			String ex_date, int arrayID) {
		Logging.debug("connection after rec==" + con);
		CFormula cf = new CFormula();
		double iwf = 0.0;
		String query = ConnectInit.queries
				.getProperty("select_stock_price_detail");
		String stk_qry = ConnectInit.queries
				.getProperty("get_undo_close_value");
		String query1 = ConnectInit.queries
				.getProperty("get_latest_index_value_index_wise");// "select_index_detail");
		String ind_query = ConnectInit.queries.getProperty("select_resp_close"); // get
																					// index
																					// values
																					// for
																					// particular
																					// date
		String query2 = ConnectInit.queries.getProperty("select_rep_cad");
		String query3 = ConnectInit.queries.getProperty("select_stock_detail");
		String affect_index = ConnectInit.queries
				.getProperty("select_affect_index");
		String child_index = ConnectInit.queries
				.getProperty("select_child_index");
		String index_comp = ConnectInit.queries
				.getProperty("select_index_comp");
		String stk_query = ConnectInit.queries
				.getProperty("detail_stock_master");
		String delete = ConnectInit.queries.getProperty("delete_index_comp");
		String ind_val_daily = ConnectInit.queries
				.getProperty("update_index_value_daily_backtrack");
		String insert_index_comp = ConnectInit.queries
				.getProperty("insert_into_index_composition");
		// String corp1=corpid ;
		String corp = corpid;// corporateact.getCorpid();
		String index = indexid; // corporateact.getI_index();
		Hashtable hash_affect = corporateact.getHash_affind();
		hash_affect.clear();
		corporateact.setHash_affind(hash_affect);

		ResultSet rs1 = null;
		ResultSet rs = null;
		PreparedStatement ppt = null;
		PreparedStatement stmt = null;
		ResultSet rstnew = null;

		try {
			// as this method is used both for historic and general CA,so there
			// should be date comparison
			String dt = UpdateCorp.accept_date(); // get the current date
			String apply = ex_date; // corporateact.getApply_date();
			int chk_dt = ComputeIndexForm.CompareDate(apply, dt); // check for
																	// the
																	// current
																	// date and
																	// user's
																	// entered
																	// date
			corporateact.setApply_date(apply);
			Hashtable hash_error = corporateact.getHash_error();
			hash_error.clear();
			corporateact.setHash_error(hash_error);
			hash_error = corporateact.getHash_error();

			// if(chk_dt==0)
			// rs=ListTypeClass1.getResult12(con,query1,index);
			// else
			rs = ListTypeClass1.getResult_apply(con, ind_query, index, ex_date); // 1
			String tmcv = null;
			String div = null;
			String index_close = null;

			if (rs != null && rs.next()) {
				tmcv = rs.getString("tmcv");
				Logging.debug("tmcv in apply==" + tmcv);
				div = rs.getString("divisor");
				Logging.debug("divisor in applyyy== " + div);
				index_close = rs.getString("index_closing_value");
				Logging.debug("index close value==" + index_close);
				rs.close();
			}

			Hashtable hash1 = corporateact.getHash1();
			double mcv = 0.0;
			// double new_tmcv=0.0;
			String close = null, tis = null, ml = null;
			String value = null, stock = null, stock_incl = null;
			String stockid = null;
			boolean check_rs = false;
			StringBuffer str = new StringBuffer();
			for (Enumeration enum1 = hash1.keys(); enum1.hasMoreElements();) {
				String id2 = (String) enum1.nextElement();
				String div1[] = token(id2);
				stock = div1[1];

				if (div1[0].equals("false")) {
					Logging.debug("-----------in false------------");
					// if(chk_dt==0)
					// rs=ListTypeClass1.getResult12(con,query,div1[1]);
					// else
					// rs=ListTypeClass1.getResult_apply(con,stk_qry,Stock_id,excl_date);
					if ((corp.equals("deletestock"))
							| (corp.equals("changeiwf"))) {
						stockid = exclusionsArray[arrayID];
					}
					if (corp.equals("addstock")) {
						stockid = inclutionsArray[arrayID];
					}
					rs = getResult_apply(con, stk_qry, stockid, ex_date);
					check_rs = rs.next();
					if (check_rs == false)
						hash_error
								.put(new String(div1[1]), new String(div1[1]));
					if (check_rs == true) {
						close = rs.getString("adjusted_price");
						if (close == null)
							close = rs.getString("stock_closing_value");
						if (close == null) {
							hash_error.put(new String(div1[1]), new String(
									div1[1]));
							check_rs = false;
						} else {
							if (close.equals("0")) {
								hash_error.put(new String(div1[1]), new String(
										div1[1]));
								check_rs = false;
							} else {
								corporateact.setTmcv(tmcv);
								corporateact.setDivisor(div);
								corporateact.setStid(div1[1]);
								// stock=div1[1];
								// /stock=Stock_id;
								// //stock_incl=Stock_id_incl;
								if ((corp.equals("deletestock"))
										| (corp.equals("changeiwf"))) {
									affect_index_list(con, corporateact,
											affect_index, indexid,
											exclusionsArray[arrayID]);// 2
									stock = exclusionsArray[arrayID];
								}
								if (corp.equals("addstock")) {
									affect_index_list(con, corporateact,
											child_index, indexid,
											inclutionsArray[arrayID]);
									stock = inclutionsArray[arrayID];
								}
								if (rs != null)
									rs.close();
								rs1 = ListTypeClass1.getAffected(con,
										stk_query, stock);
								if (rs1 != null && rs1.next()) {
									tis = rs1.getString("tis");
									ml = rs1.getString("market_lot");
									rs1.close();
								}
								if (corp != null) {
									if (corp.equals("addstock")) {
										rs = ListTypeClass1.getResult12(con,
												query3,
												inclutionsArray[arrayID]);
										rs.next();
										value = rs.getString("iwf");
									}
									if (corp.equals("deletestock")) {
										rs = ListTypeClass1.getResult_corp(con,
												index_comp, indexid,
												exclusionsArray[arrayID]);
										rs.next();
										value = rs.getString("iwf");
									}
									if (corp.equals("changeiwf"))
										value = corporateact.getValues();
								}
							}
						}
					}// check rs true
				}// div false
				if (div1[0].equals("true")) {
					Logging.debug("-----------in true------------");
					rs = ListTypeClass1.getAffected(con, query2, div1[1]);
					rs.next();
					stock = rs.getString("stock_id");
					String val = rs.getString("values");
					rs.close();
					if (chk_dt == 0)
						rs = ListTypeClass1.getResult12(con, query, stock);
					else
						rs = ListTypeClass1.getResult_apply(con, stk_qry,
								stock, ex_date);
					check_rs = rs.next();
					if (check_rs == false) {
						hash_error.put(new String(stock), new String(stock));
					}
					if (check_rs == true) {
						close = rs.getString("adjusted_price");
						if (close == null)
							close = rs.getString("stock_closing_value");
						if (close == null) {
							hash_error
									.put(new String(stock), new String(stock));
							check_rs = false;
						} else {
							if (close.equals("0")) {
								hash_error.put(new String(stock), new String(
										stock));
								check_rs = false;
							} else {
								corporateact.setTmcv(tmcv);
								corporateact.setDivisor(div);
								if ((corp.equals("deletestock"))
										| (corp.equals("changeiwf")))
									ListTypeClass1.affect_index_list(con,
											corporateact, affect_index, stock);
								if (corp.equals("addstock"))
									ListTypeClass1.affect_index_list(con,
											corporateact, child_index, stock);

								rs1 = ListTypeClass1.getAffected(con,
										stk_query, stock);
								rs1.next();
								tis = rs1.getString("tis");
								ml = rs1.getString("market_lot");
								rs1.close();
								String nature = corporateact.getNature();
								if (corp != null) {
									if (corp.equals("addstock")) {
										rs = ListTypeClass1.getResult12(con,
												query3, stock);
										rs.next();
										value = rs.getString("iwf");
										iwf = Double.parseDouble("value");
									}
									if (corp.equals("deletestock")) {
										rs = ListTypeClass1.getResult_corp(con,
												index_comp, index, stock);
										rs.next();
										value = rs.getString("iwf");
										iwf = Double.parseDouble("value");
									}
									if (corp.equals("changeiwf")) {
										if (nature != null) {
											if (nature.equals("n"))
												value = corporateact
														.getValues();
											if (nature.equals("o")) {
												value = val;
												corporateact.setValues(val);
											}
										} else
											value = corporateact.getValues();
									}
								}
							}
						}
					}
				}
				if (check_rs == true) {
					// get currency exchange rate
					NCorp_Action.get_currency(con, connect, corporateact,
							index, stock);
					// mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(value));
					Logging.debug("mcv===" + mcv);

					// double newmcv1=0.0;
					long stkid = Long.parseLong(stock);

					try {
						if (connection == null)
							connection = c.getdbConnection();
						ppt = connection.prepareStatement(ConnectInit.queries
								.getProperty("compute_index_back_test"));// changed
																			// by
																			// neha
																			// 9thdec2007
						ppt.setLong(1, stkid);
						// date = QueryClass.formatDate();
						ppt.setString(2, ex_date);
						// pst_preStat.setString(1, date);

						rstnew = ppt.executeQuery();// executes query to get
													// data
						while (rstnew.next()) {
							newmcv1 = rstnew.getDouble("mcv");
						}

						if (index_type == 1) {
							mcv = newmcv1;// added by neha for market cap

						} else if (index_type == 2) {
							mcv = (newmcv1 * iwf);// added by neha
						}
					} catch (Exception e) {
						Logging.error(" Error : " + e.getMessage());
					}
					corporateact.setValues(value);
					if (corp != null) {
						if (corp.equals("changeiwf")) {
							rs = ListTypeClass1.getResult_corp(con, index_comp,
									index, stock);
							rs.next();
							String val = rs.getString("iwf");
							double mcv_old = cf.calMarketCap(Double
									.parseDouble(close), Long.parseLong(ml),
									Double.parseDouble(corporateact
											.getCurr_val()), Long
											.parseLong(tis), Double
											.parseDouble(val));
							double mcv_new = mcv;
							mcv = mcv_new - mcv_old;
							if (new_tmcv == 0.0)
								new_tmcv = Double.parseDouble(tmcv) + mcv;
							else
								new_tmcv = new_tmcv + mcv;
						}
						if (corp.equals("addstock")) {
							if (new_tmcv == 0.0)
								new_tmcv = Double.parseDouble(tmcv) + mcv;
							else
								new_tmcv = new_tmcv + mcv;
						}
						if (corp.equals("deletestock")) {
							if (new_tmcv == 0.0)
								new_tmcv = Double.parseDouble(tmcv) - mcv;
							else
								new_tmcv = new_tmcv - mcv;
							System.out.println("new tmcv ==>" + new_tmcv);
						}
					}
				}
			}
			corporateact.setNewTmcv(Double.toString(new_tmcv));
			double divi = Double.parseDouble(div);
			if (divi == 0.0) {
				newdivisor = (new_tmcv / Double.parseDouble(index_close));
				corporateact.setNewdivisor(Double.toString(newdivisor));

				System.out.println("new divisor ==>" + newdivisor);
			} else {
				double diff = cf.diffTMCV(Double.parseDouble(tmcv), new_tmcv);

				// newdivisor=cf.newDivisorCorp(Double.parseDouble(tmcv),diff,Double.parseDouble(div));
				newdivisor = newDivisorCorp(Double.parseDouble(tmcv), diff,
						Double.parseDouble(div));
				corporateact.setNewdivisor(Double.toString(newdivisor));
			}

			corporateact.setHash_error(hash_error);
			// check_affect_index(con,corporateact,query1);
		} catch (Exception e) {
			Logging.error("ERROR :" + e.getMessage());
		}
		tmcv_div_adj(corporateact);
		if (corp != null) {
			if (corp.equals("changeiwf")) {

			}
			if (corp.equals("addstock")) {
				insert_index_comp(con, insert_index_comp, corporateact,
						ex_date, indexid, inclutionsArray[arrayID]);
			}
			if (corp.equals("deletestock")) {
				try {
					stmt = con.prepareStatement(delete);
					stmt.setString(1, indexid);
					stmt.setString(2, exclusionsArray[arrayID]);
					stmt.execute();
				} catch (Exception e) {
					Logging.error("ListTypeClass:Error in DeleteStatement "
							+ e.getMessage());
				}
			}
		}

		try {
			if (ppt != null && stmt != null) {
				ppt.close();
				stmt.close();
			}
			if (rs1 != null && rs != null && rstnew != null) {
				rs1.close();
				rs.close();
				rstnew.close();
			}
		} catch (SQLException sq) {
			sq.printStackTrace();
		}

		update_index_daily(con, ind_val_daily, corporateact, indexid);

	}

	public void applyIndexDetail(Connection con, Connect connect,
			Corporate corporateact, String indexid, String corpid,
			String ex_date) {
		Logging.debug("connection after rec==" + con);
		// CFormula cf = new CFormula();
		CFormula cf = ConnectInit.getCFormula();
		double iwf = 0.0;
		String query = ConnectInit.queries
				.getProperty("select_stock_price_detail");
		String stk_qry = ConnectInit.queries
				.getProperty("get_undo_close_value");
		String query1 = ConnectInit.queries
				.getProperty("get_latest_index_value_index_wise");// "select_index_detail");
		String ind_query = ConnectInit.queries.getProperty("select_resp_close"); // get
																					// index
																					// values
																					// for
																					// particular
																					// date
		String query2 = ConnectInit.queries.getProperty("select_rep_cad");
		String query3 = ConnectInit.queries.getProperty("select_stock_detail");
		String affect_index = ConnectInit.queries
				.getProperty("select_affect_index");
		String child_index = ConnectInit.queries
				.getProperty("select_child_index");
		String index_comp = ConnectInit.queries
				.getProperty("select_index_comp");
		String stk_query = ConnectInit.queries
				.getProperty("detail_stock_master");
		String delete = ConnectInit.queries.getProperty("delete_index_comp");
		String ind_val_daily = ConnectInit.queries
				.getProperty("update_index_value_daily_backtrack");
		String insert_index_comp = ConnectInit.queries
				.getProperty("insert_into_index_composition");
		// String corp1=corpid ;
		String corp = corpid;// corporateact.getCorpid();
		String index = indexid; // corporateact.getI_index();
		Hashtable hash_affect = corporateact.getHash_affind();
		hash_affect.clear();
		corporateact.setHash_affind(hash_affect);

		ResultSet rs = null;
		ResultSet rs1 = null;
		PreparedStatement ppt = null;
		PreparedStatement stmt = null;
		ResultSet rstnew = null;

		try {
			// as this method is used both for historic and general CA,so there
			// should be date comparison
			String dt = UpdateCorp.accept_date(); // get the current date
			String apply = ex_date; // corporateact.getApply_date();
			int chk_dt = ComputeIndexForm.CompareDate(apply, dt); // check for
																	// the
																	// current
																	// date and
																	// user's
																	// entered
																	// date

			Hashtable hash_error = corporateact.getHash_error();
			hash_error.clear();
			corporateact.setHash_error(hash_error);
			hash_error = corporateact.getHash_error();

			// if(chk_dt==0)
			// rs=ListTypeClass1.getResult12(con,query1,index);
			// else
			rs = ListTypeClass1.getResult_apply(con, ind_query, index, ex_date); // 1
			String tmcv = null;
			String div = null;
			String index_close = null;

			if (rs != null && rs.next()) {
				tmcv = rs.getString("tmcv");
				Logging.debug("tmcv in apply==" + tmcv);
				div = rs.getString("divisor");
				Logging.debug("divisor in applyyy== " + div);
				index_close = rs.getString("index_closing_value");
				Logging.debug("index close value==" + index_close);
				rs.close();
			}

			Hashtable hash1 = corporateact.getHash1();
			double mcv = 0.0;
			// double new_tmcv=0.0;
			String close = null, tis = null, ml = null;
			String value = null, stock = null, stock_incl = null;
			String stockid = null;
			boolean check_rs = false;
			StringBuffer str = new StringBuffer();
			for (Enumeration enum1 = hash1.keys(); enum1.hasMoreElements();) {
				String id2 = (String) enum1.nextElement();
				String div1[] = token(id2);
				stock = div1[1];

				if (div1[0].equals("false")) {
					Logging.debug("-----------in false------------");
					// if(chk_dt==0)
					// rs=ListTypeClass1.getResult12(con,query,div1[1]);
					// else
					// rs=ListTypeClass1.getResult_apply(con,stk_qry,Stock_id,excl_date);
					if ((corp.equals("deletestock"))
							| (corp.equals("changeiwf"))) {

						stockid = Stock_id;
					}
					if (corp.equals("addstock")) {

						stockid = Stock_id_incl;
					}
					rs = getResult_apply(con, stk_qry, stockid, ex_date);
					check_rs = rs.next();
					if (check_rs == false)
						hash_error
								.put(new String(div1[1]), new String(div1[1]));
					if (check_rs == true) {
						close = rs.getString("adjusted_price");
						if (close == null)
							close = rs.getString("stock_closing_value");
						if (close == null) {
							hash_error.put(new String(div1[1]), new String(
									div1[1]));
							check_rs = false;
						} else {
							if (close.equals("0")) {
								hash_error.put(new String(div1[1]), new String(
										div1[1]));
								check_rs = false;
							} else {
								corporateact.setTmcv(tmcv);
								corporateact.setDivisor(div);
								corporateact.setStid(div1[1]);
								// stock=div1[1];
								// /stock=Stock_id;
								// //stock_incl=Stock_id_incl;
								if ((corp.equals("deletestock"))
										| (corp.equals("changeiwf"))) {
									affect_index_list(con, corporateact,
											affect_index, indexid, Stock_id);// 2
									stock = Stock_id;
								}
								if (corp.equals("addstock")) {
									affect_index_list(con, corporateact,
											child_index, indexid, Stock_id_incl);
									stock = Stock_id_incl;
								}
								if (rs != null)
									rs.close();
								rs1 = ListTypeClass1.getAffected(con,
										stk_query, stock);
								if (rs1 != null && rs1.next()) {
									tis = rs1.getString("tis");
									ml = rs1.getString("market_lot");
									rs1.close();
								}
								if (corp != null) {
									if (corp.equals("addstock")) {
										rs = ListTypeClass1.getResult12(con,
												query3, Stock_id_incl);
										rs.next();
										value = rs.getString("iwf");
									}
									if (corp.equals("deletestock")) {
										rs = ListTypeClass1.getResult_corp(con,
												index_comp, indexid, Stock_id);
										rs.next();
										value = rs.getString("iwf");
									}
									if (corp.equals("changeiwf"))
										value = corporateact.getValues();
								}
							}
						}
					}// check rs true
				}// div false
				if (div1[0].equals("true")) {
					Logging.debug("-----------in true------------");
					rs = ListTypeClass1.getAffected(con, query2, div1[1]);
					rs.next();
					stock = rs.getString("stock_id");
					String val = rs.getString("values");
					rs.close();
					if (chk_dt == 0)
						rs = ListTypeClass1.getResult12(con, query, stock);
					else
						rs = ListTypeClass1.getResult_apply(con, stk_qry,
								stock, ex_date);
					check_rs = rs.next();
					if (check_rs == false) {
						hash_error.put(new String(stock), new String(stock));
					}
					if (check_rs == true) {
						close = rs.getString("adjusted_price");
						if (close == null)
							close = rs.getString("stock_closing_value");
						if (close == null) {
							hash_error
									.put(new String(stock), new String(stock));
							check_rs = false;
						} else {
							if (close.equals("0")) {
								hash_error.put(new String(stock), new String(
										stock));
								check_rs = false;
							} else {
								corporateact.setTmcv(tmcv);
								corporateact.setDivisor(div);
								if ((corp.equals("deletestock"))
										| (corp.equals("changeiwf")))
									ListTypeClass1.affect_index_list(con,
											corporateact, affect_index, stock);
								if (corp.equals("addstock"))
									ListTypeClass1.affect_index_list(con,
											corporateact, child_index, stock);

								rs1 = ListTypeClass1.getAffected(con,
										stk_query, stock);
								rs1.next();
								tis = rs1.getString("tis");
								ml = rs1.getString("market_lot");
								rs1.close();
								String nature = corporateact.getNature();
								if (corp != null) {
									if (corp.equals("addstock")) {
										rs = ListTypeClass1.getResult12(con,
												query3, stock);
										rs.next();
										value = rs.getString("iwf");
										iwf = Double.parseDouble("value");
									}
									if (corp.equals("deletestock")) {
										rs = ListTypeClass1.getResult_corp(con,
												index_comp, index, stock);
										rs.next();
										value = rs.getString("iwf");
										iwf = Double.parseDouble("value");
									}
									if (corp.equals("changeiwf")) {
										if (nature != null) {
											if (nature.equals("n"))
												value = corporateact
														.getValues();
											if (nature.equals("o")) {
												value = val;
												corporateact.setValues(val);
											}
										} else
											value = corporateact.getValues();
									}
								}
							}
						}
					}
				}
				if (check_rs == true) {
					// get currency exchange rate
					NCorp_Action.get_currency(con, connect, corporateact,
							index, stock);
					// mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(value));
					Logging.debug("mcv===" + mcv);

					// double newmcv1=0.0;
					long stkid = Long.parseLong(stock);

					try {
						if (connection == null)
							connection = c.getdbConnection();
						ppt = connection.prepareStatement(ConnectInit.queries
								.getProperty("compute_index_back_test"));// changed
																			// by
																			// neha
																			// 9thdec2007
						ppt.setLong(1, stkid);
						// date = QueryClass.formatDate();
						ppt.setString(2, ex_date);
						// pst_preStat.setString(1, date);

						rstnew = ppt.executeQuery();// executes query to get
													// data
						while (rstnew.next()) {
							newmcv1 = rstnew.getDouble("mcv");
						}

						if (index_type == 1) {
							mcv = newmcv1;// added by neha for market cap

						} else if (index_type == 2) {
							mcv = (newmcv1 * iwf);// added by neha
						}
					} catch (Exception e) {
						Logging.error(" Error : " + e.getMessage());
					}
					corporateact.setValues(value);
					if (corp != null) {
						if (corp.equals("changeiwf")) {
							rs = ListTypeClass1.getResult_corp(con, index_comp,
									index, stock);
							rs.next();
							String val = rs.getString("iwf");
							double mcv_old = cf.calMarketCap(Double
									.parseDouble(close), Long.parseLong(ml),
									Double.parseDouble(corporateact
											.getCurr_val()), Long
											.parseLong(tis), Double
											.parseDouble(val));
							double mcv_new = mcv;
							mcv = mcv_new - mcv_old;
							if (new_tmcv == 0.0)
								new_tmcv = Double.parseDouble(tmcv) + mcv;
							else
								new_tmcv = new_tmcv + mcv;
						}
						if (corp.equals("addstock")) {
							if (new_tmcv == 0.0)
								new_tmcv = Double.parseDouble(tmcv) + mcv;
							else
								new_tmcv = new_tmcv + mcv;
						}
						if (corp.equals("deletestock")) {
							if (new_tmcv == 0.0)
								new_tmcv = Double.parseDouble(tmcv) - mcv;
							else
								new_tmcv = new_tmcv - mcv;
							Logging.debug("new tmcv ==>" + new_tmcv);
						}
					}
				}
			}
			corporateact.setNewTmcv(Double.toString(new_tmcv));
			double divi = Double.parseDouble(div);
			if (divi == 0.0) {
				newdivisor = (new_tmcv / Double.parseDouble(index_close));
				corporateact.setNewdivisor(Double.toString(newdivisor));

				Logging.debug("new divisor ==>" + newdivisor);
			} else {
				double diff = cf.diffTMCV(Double.parseDouble(tmcv), new_tmcv);

				// newdivisor=cf.newDivisorCorp(Double.parseDouble(tmcv),diff,Double.parseDouble(div));
				newdivisor = newDivisorCorp(Double.parseDouble(tmcv), diff,
						Double.parseDouble(div));
				corporateact.setNewdivisor(Double.toString(newdivisor));
			}

			corporateact.setHash_error(hash_error);
			// check_affect_index(con,corporateact,query1);
		} catch (Exception e) {
			Logging.error("ERROR :" + e.getMessage());
		}
		tmcv_div_adj(corporateact);
		if (corp != null) {
			if (corp.equals("changeiwf")) {

			}
			if (corp.equals("addstock")) {
				insert_index_comp(con, insert_index_comp, corporateact,
						ex_date, indexid, Stock_id_incl);
			}
			if (corp.equals("deletestock")) {
				try {
					stmt = con.prepareStatement(delete);
					stmt.setString(1, indexid);
					stmt.setString(2, Stock_id);

					stmt.execute();

				} catch (Exception e) {
					Logging.error("ListTypeClass:Error in DeleteStatement "
							+ e.getMessage());
				}
			}
		}
		try {
			if (ppt != null && stmt != null) {
				ppt.close();
				stmt.close();
			}
			if (rs != null && rs1 != null && rstnew != null) {
				rs.close();
				rs1.close();
				rstnew.close();
			}
		} catch (SQLException sq) {
			sq.printStackTrace();
		}

		update_index_daily(con, ind_val_daily, corporateact, indexid);

	}

	public void insert_index_comp(Connection con, String query,
			Corporate corporateact, String incl_date, String indexid,
			String stkid) {
		double mcv1 = 0.0;
		Statement stmt1 = null;
		PreparedStatement stmt = null;
		try {
			stmt1 = con.createStatement();
			stmt = con.prepareStatement(query);
			stmt.setString(1, corporateact.getValues());

			stmt.setString(2, incl_date);
			stmt.setString(3, indexid);
			stmt.setString(4, stkid);
			// String mcv=Double.toString(corporateact.getNewmcv());
			if (index_type == 1) {
				mcv1 = newmcv1;// added by neha for market cap

			} else if (index_type == 2) {
				mcv1 = (newmcv1 * iwf);// added by neha
			}
			String mcv_new = Double.toString(mcv1);
			stmt.setString(5, mcv_new);
			stmt.executeUpdate();
			Logging.info("com");
		} catch (Exception e) {
			Logging.error("Exception in second1 updation" + e.getMessage());
		} finally {
			try {
				if (stmt1 != null && stmt != null) {
					stmt1.close();
					stmt.close();
				}

			} catch (SQLException sq) {
				sq.printStackTrace();
			}
		}
	}

	public static String[] token(String ratio) {
		String str[] = new String[2];
		if (ratio != null) {
			StringTokenizer st = new StringTokenizer(ratio, ":");
			while (st.hasMoreTokens()) {
				for (int i = 0; i < str.length; i++) {
					str[i] = st.nextToken();
				}
			}
		}
		return str;
	}

	public static ResultSet getResult_apply(Connection con, String query,
			String Stock_id, String date) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(query);

			stmt.setString(1, Stock_id);
			stmt.setString(2, date);
			rs = stmt.executeQuery();
			Logging.debug("complete ");
		} catch (Exception e) {
			Logging.error("ListTypeClass:Error in CreateStatement "
					+ e.getMessage());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return rs;
	}

	public static void affect_index_list(Connection con,
			Corporate corporateact, String query, String indexid, String stock) {
		ResultSet rs = null;
		try {
			Hashtable hash_affect = corporateact.getHash_affind();
			rs = getResult_corp(con, query, indexid, stock);
			int count = 0;
			while (rs.next()) {
				String ind_name = rs.getString("index_name");
				Logging.debug("aff index name==" + ind_name);
				hash_affect.put(
						new String(count + " " + rs.getString("index_id")),
						new String(stock));
				count++;
			}
			corporateact.setHash_affind(hash_affect);
		} catch (Exception e) {
			Logging.error("ListTypeClass:Error in CreateStatement "
					+ e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static ResultSet getResult_corp(Connection con, String query,
			String cid, String sid) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			Logging.debug("query==" + query);
			Logging.debug("cid ===" + cid + "sid===" + sid);
			stmt = con.prepareStatement(query);
			if (cid != null)
				stmt.setInt(1, Integer.parseInt(cid));
			else
				stmt.setString(1, null);
			if (sid != null)
				stmt.setInt(2, Integer.parseInt(sid));
			rs = stmt.executeQuery();
		} catch (Exception e) {
			Logging.error("ListTypeClass:Error in CreateStatement "
					+ e.getMessage());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return rs;
	}

	public double getDivisorValue(String indexid) {
		double div = 0.00;
		PreparedStatement pst_preStat = null;
		ResultSet rst1 = null;
		Connection connection = null;
		try {
			// Change by Manoj Adekar for Dynamic connection using
			// getdbConnection() instead of getConnection()

			connection = c.getdbConnection();
			String query_divisor = ConnectInit.queries
					.getProperty("select_backtrack_divisor");

			pst_preStat = connection.prepareStatement(query_divisor);
			Logging.debug("pst_preStat is " + pst_preStat + " l_indexID is "
					+ indexid);
			;
			pst_preStat.setString(1, indexid);// set ? for index id
			pst_preStat.setString(2, indexid);
			Logging.debug("pst_preStat is " + pst_preStat);
			rst1 = pst_preStat.executeQuery();// execute query
			Logging.debug("rst1 is " + rst1);
			if (rst1.next())// (rst1.getRow() == 0)//check
			{
				div = rst1.getDouble(1);

				Logging.debug("divisor taken " + div);
			}
		} catch (SQLException e) {
			Logging.debug(e.getMessage());
			Logging.debug("index values" + e);
		} finally {
			if (pst_preStat != null && rst1 != null && connection != null) {
				try {
					rst1.close();
					connection.close();
					pst_preStat.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return div;
	}

	public double newDivisorCorp(double d_oldTMCV, double d_diffTMCV,
			double d_oldDivisor) {
		double d_newDivisor = 0;
		double d_midDivisor = 0;
		d_midDivisor = ((d_oldTMCV + d_diffTMCV) / d_oldTMCV);
		d_newDivisor = (d_oldDivisor * d_midDivisor);
		return d_newDivisor;
	}

	public void update_index_daily(Connection con, String query,
			Corporate corporateact, String indexid) {
		PreparedStatement stmt = null;
		try {
			String tmcv_val = corporateact.getNewTmcv();
			String div_val = corporateact.getNewdivisor();
			stmt = con.prepareStatement(query);
			stmt.setString(1, indexid);
			if (corporateact.getNewTmcv().equals("")) {
				stmt.setDouble(2, 0.0);
			} else {
				stmt.setDouble(2, new_tmcv);
			}
			if (corporateact.getNewdivisor().equals("")) {
				stmt.setDouble(3, 0.0);
			} else {

				stmt.setDouble(3, newdivisor);
			}
			stmt.setString(4, indexid);
			stmt.setString(5, indexid);
			stmt.executeUpdate();
			Logging.info("after exceutioncom");
		} catch (Exception e) {
			Logging.error("Exception in second1 updation" + e.getMessage());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
	}
}
