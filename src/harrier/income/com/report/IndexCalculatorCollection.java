/*
 * Created on Mar 12, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;

import harrier.income.com.entities.CFormula;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jfree.chart.demo.servlet.AdjustDecimal;
import org.jfree.chart.demo.servlet.ComposeIndex;

import app.Connect;
import app.QueryClass;

import com.harrier.initializeation.ConnectInit;

/**
 * @author sunil
 *
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class IndexCalculatorCollection {
	static Logger Logging = Logger
			.getLogger(app.IndexCalculatorCollection.class);
	// static private CFormula cFor = new CFormula();
	static ResultSet rst, rst1, rst2, rst3;
	public static Hashtable table = new Hashtable();
	public static Vector v = new Vector();
	static double tmcv = 0;
	public static Hashtable datacollection = null;
	Connect con = ConnectInit.getConnect();
	Connection connection = null;
	private static Vector index_calctr;
	static String ivalue, ovalue, hvalue, lvalue, cvalue, pchange, mcvalue,
			dvalue;

	/**
	 * method to set the values for index Calculator (mcv used) in bean as well
	 * as hashtable.
	 */
	public static void addStocksInTisCalculatorTable(String index_id,
			HttpServletRequest request, String option) {
		int i = 0, j = 0;
		String srno = null;
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		index_calctr = new Vector();
		/*
		 * if (rst.getString(13) == null) {
		 * 
		 * index_details.add(i, "0"); } else { index_details.add(i,
		 * rst.getString(13)); } i++;
		 */
		try {
			request.setAttribute("coun", "2");
			if (index_id == null)
				index_id = "1592";
			if (!(option != null && option.equals("Submit"))) {
				Logging.debug("not in submit");
				table.clear();
				// org.jfree.chart.demo.servlet.AdjustDecimal ad=new
				// org.jfree.chart.demo.servlet.AdjustDecimal();
				AdjustDecimal ad = ConnectInit.getAdjustDecimal();
				String strtmcv = "0.00";
				IndexCalculatorForm.setCount(1);
				if (connection == null) {
					connection = con.getdbConnection();
				}
				PreparedStatement stmt = connection
						.prepareStatement(ConnectInit.queries
								.getProperty("portpolio_tis_calculator_report"));
				stmt.setString(1, index_id);
				stmt.setString(2, index_id);
				Logging.debug(stmt + "");
				tmcv = 0.0;
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					j = j + 1;
					srno = String.valueOf(j);
					index_calctr.add(i, srno);

					String symbol = rs.getString(7);
					i++;
					if (symbol != null) {
						String newSymbol = symbol.replace("&", "-");
						index_calctr.add(i, newSymbol);
					} else {
						index_calctr.add(i, "0");
					}

					String price = rs.getString(6);
					i++;
					if (price != null) {
						index_calctr.add(i, price);
					} else {
						index_calctr.add(i, "0");
					}

					String mcv = rs.getString(4);
					i++;
					String tis = rs.getString(2);
					String stk_id = rs.getString(1);
					String iwf = rs.getString(3);
					String stk_currency_id = rs.getString(8);
					String mkt_lot = rs.getString(9);
					// Add by manoj
					double price_value = (double) Double.parseDouble(price);
					double iwf_value = (double) Double.parseDouble(iwf);
					double tis_value = (double) Double.parseDouble(tis);
					double mcv_value = tis_value * price_value * iwf_value;
					String strmcvN = new Double(mcv_value).toString();
					strmcvN = ad.shareholdingpatt(strmcvN);
					if (strmcvN != null) {
						index_calctr.add(i, strmcvN);
					} else {
						index_calctr.add(i, "0");
					}

					i++;

					if (tis != null) {
						index_calctr.add(i, tis);
					} else {
						index_calctr.add(i, "0");
					}
					i++;

					// upto
					tmcv = tmcv + mcv_value;
					strtmcv = new Double(tmcv).toString();
					strtmcv = ad.shareholdingpatt(strtmcv);

					String strmcv = new Double(mcv_value).toString();
					strmcv = ad.shareholdingpatt(strmcv);

					Logging.debug("tmcv is " + tmcv);
					table.put(String.valueOf(symbol), new IndexCalculatorForm(
							stk_id, tis, iwf, strmcv, price, symbol,
							stk_currency_id, mkt_lot));// populating hashatable
														// with
														// stock_id,symbol,mcv &
														// price,symbol is used
														// as key.
					request.setAttribute("coun", "1");
				}
				IndexCalculatorForm.setTmcv(strtmcv);
			} else {
				getMultiplyingFactor(table, index_id, request);
			}
		} catch (Exception e) {
			Logging.error("Error in sourceTable " + e.getMessage());
		}

	}

	public static String userStockwisePrice(String stkidS, String priceList) {
		String userprice = null, stk_id = null;
		String indexdata[] = new String[100];
		int countpara = 0, j = 0;
		String stockdata[] = new String[100];
		StringTokenizer token = new StringTokenizer(priceList, "|");
		while (token.hasMoreTokens()) {
			indexdata[countpara] = token.nextToken();
			countpara++;
		}
		StringTokenizer tokendata = null;
		for (int i = 0; i < countpara; i++) {
			j = 0;
			tokendata = new StringTokenizer(indexdata[i], "_");
			while (tokendata.hasMoreTokens()) {
				stockdata[j] = tokendata.nextToken();
				j = j + 1;
			}
			stk_id = stockdata[1];
			userprice = stockdata[2];
			if (stkidS.equalsIgnoreCase(stk_id)) {
				return userprice;
			}
		}
		System.out.println("userprice ==== " + userprice);
		return userprice;
	}
//
//	public static String computeIndexNormallyNew(String indexID,
//			String priceArr, HttpServletRequest request) {
		public static String computeIndexNormallyNew(String indexID,
				String priceArr, HttpServletRequest request,IndexCalculatorForm indexCalculatorForm) {
		Logging.debug("Computing Index for Index Id : " + indexID);
		CFormula cFor = ConnectInit.getCFormula();
		String time = getTime();
		long l_indexID = Long.parseLong(indexID);
		double high, low;
		double ltp = 0, iwf = 0, mcv = 0, exch = 1, tmcv = 0, flag = 0, divisor = 0, base_value = 0, indexVal = 0, fto_exch = 1, icomp_mcv = 0, icomp_iwf = 0;
		String date = "";
		boolean firstDailyValue = false;
		long id, baseCurrencyIdForCurrencyTypeIndex = 66;
		long childIndexId = 0, ml = 0, tis = 0, stkid = 0, curridStk = 0, curridIndex = 0, stkId = 0, currId = 0;
		Connect con = ConnectInit.getConnect();
		Connection connection = null;

		v.clear();
		Logging.debug("initial tmcv for  Id : " + indexID + " is : " + tmcv);
		try {
			if (connection == null)
				connection = con.getdbConnection();
			// doing normal calculation
			if (!firstDailyValue) {
				PreparedStatement pst_preStat = connection
						.prepareStatement(ConnectInit.queries
								.getProperty("compute_index"));
				pst_preStat.setLong(1, l_indexID);
				date = QueryClass.formatDate();
				Logging.debug("l_indexID " + l_indexID + " date " + date);
				pst_preStat.setString(2, date);
				Logging.debug(pst_preStat.toString());
				rst = pst_preStat.executeQuery();// executes query to get data
													// for
				// index computation
				// get all ltp values and stock id for the given date
				while (rst.next()) {
					Logging.debug("Row no." + rst.getRow());
					stkid = rst.getLong("stock_id");// get stock id
					System.out.println(" Stock id *** " + stkid);
					String tempstkid = String.valueOf(stkid);
					System.out.println(" tempstkid *** " + tempstkid);
					// String ovalue ="uprice:"+tempstkid;
					// String user_price=request.getParameter(ovalue);//get user
					// entered value for price.
					// Logging.getDebug("user_price "+user_price);
					// if(user_price==null || (user_price.equals("")) ){
					//commented by samiksha
//					String user_price1 = userStockwisePrice(tempstkid, priceArr);
					String user_price1 =indexCalculatorForm.getIndexValueLtp();
					System.out.println(" user_price1 *** " + user_price1);
					if (user_price1 != null) {
						ltp = (double) Double.parseDouble(user_price1);
						System.out.println(" ltp *** " + ltp);
					} else {
						ltp = rst.getDouble("ltp");// get ltp
						System.out.println(" ltp *** " + ltp);
					}
					iwf = rst.getDouble("iwf");// get iwf
					System.out.println(" iwf *** " + iwf);
					ml = rst.getLong("market_lot");// get market lot
					System.out.println(" ml *** " + ml);
					tis = (long) rst.getDouble("tis");// get tis
					System.out.println(" ml *** " + ml);
					curridStk = rst.getLong("stock_currency_id");// get
																	// currencyId
					// for stock
					curridIndex = rst.getLong("base_currency_id");

					date = rst.getString("price_date");
					Logging.debug("Index curr id" + curridIndex);
					Logging.debug("Stock curr id" + curridStk);
					if ((curridStk != curridIndex) && (flag == 0)) {
						rst2 = getExchCode();
						Logging.debug("Get ResultSet");
						rst2.next();
						stkId = rst2.getLong("from_currency_id");
						currId = rst2.getLong("to_currency_id");

						fto_exch = rst2.getDouble("intra_day_ex_rate_value");
						flag = 1;
					}
					if ((curridStk != curridIndex)) {
						while (rst2.next()) {
							Logging.debug("In Exchange Code checking");
							if ((curridStk == stkId) && (curridIndex == currId)) {
								exch = fto_exch;
								Logging.debug("Exchange Found : " + exch);
								break;
							}
						}
						rst2.beforeFirst();
					}
					Logging.debug("Finally Exchange rate is : " + exch);
					System.out.print("tis is  " + tis);
					base_value = rst.getDouble("base_value");// get base value
																// for
					// index
					mcv = cFor.calMarketCap(ltp, ml, exch, tis, iwf);// calculates

					v.addElement(new Double(mcv));// collects mcv in vector

					icomp_mcv = mcv;
				}
				Logging.debug("initial1 tmcv for  Id : " + indexID + " is : "
						+ tmcv + "v size is : " + v.size());

				tmcv = cFor.totalMarketCap(v);// calculate tmcv
				Logging.debug("initial2 tmcv for  Id : " + indexID + " is : "
						+ tmcv + "v size is : " + v.size());
				Logging.debug("Total Market Cap " + tmcv);

				if (tmcv == 0) {
					return "----";
				}
				try {
					pst_preStat = connection
							.prepareStatement(ConnectInit.queries
									.getProperty("select_daily_divisor"));
					pst_preStat.setLong(1, l_indexID);// set ? for index id
					rst1 = pst_preStat.executeQuery();// execute query

					if (!rst1.next())// (rst1.getRow() == 0)//check
					{
						divisor = cFor.divisor(tmcv, base_value);
						Logging.debug("divisor value computed " + divisor);

					} else {
						divisor = rst1.getDouble(1);
						Logging.debug(" Divisor" + divisor);
						Logging.debug("divisor taken " + divisor);
					}
				} catch (SQLException e) {
					Logging.error("Error : index values" + e.getMessage());
				}
				// indexVal = cFor.index(tmcv, divisor);// index compute
				indexVal = (tmcv / divisor);
				Logging.debug("******** value calculated = " + indexVal);

			}
		} catch (Exception e) {
			Logging.debug("Error : " + e.getMessage());
		}
		// normal calculation ended
		finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				Logging.debug("Unable to close Connection :" + e);
			}
		}
		return (new Double(indexVal).toString());
	}

	public String dbIndexValue(String indId) {
		String indval = null;
		Connection connection = null;
		Connect con = ConnectInit.getConnect();
		ResultSet rs = null;
		try {
			if (connection == null)
				connection = con.getdbConnection();
			try {
				// PreparedStatement pstmt
				// =connection.prepareStatement(con.queries.getProperty("select_indexvalue_to_calculator_report_price"));
				PreparedStatement pstmt = connection
						.prepareStatement(ConnectInit.queries
								.getProperty("select_indexvalue_to_calculator_report_price"));
				pstmt.setString(1, indId);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					indval = rs.getString(1);
				}
			} catch (Exception e) {
				// TODO: handle exception
				Logging.error(" Error : " + e.getMessage());
			}
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error(" Error : Unable to close Connection "
							+ ex.getMessage());
				}
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}

		return indval;
	}

	/**
	 * method to set the values for index Calculator in bean as well as
	 * hashtable.
	 */
	public static void addStocksInIndexCalculatorTablePrice(String index_id,
			HttpServletRequest request) {
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		try {

			// request.setAttribute("coun","2");
			table.clear();
			String price = null;
			IndexCalculatorForm.setCount(1);
			// Change by Manoj Adekar for Dynamic connection using
			// getdbConnection() instead of getConnection()
			if (connection == null) {
				connection = con.getdbConnection();
			}
			PreparedStatement stmt = connection
					.prepareStatement(ConnectInit.queries
							.getProperty("index_calculator_report_price"));
			stmt.setString(1, index_id);
			// stmt.setString(2,index_id);
			Logging.debug(stmt + "");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String stk_id = rs.getString(1);
				String symbol = rs.getString(2);
				if (rs.getString(3) != null) {
					price = rs.getString(3);
				} else {
					price = "0.00";
				}
				String ovalue = "uprice:" + stk_id;
				ovalue = request.getParameter(ovalue);
				Logging.debug("ovalue is while setting bean " + ovalue);
				// app.Logging.getDebug("id "+stk_id+" symbol "+symbol+" price "+price);
				table.put(String.valueOf(symbol), new IndexCalculatorForm(
						stk_id, symbol, ovalue, price));// populating hashatable
														// with
														// stock_id,symbol,mcv &
														// price,
				// request.setAttribute("coun","1"); //symbol is used as key.
			}
		} catch (Exception e) {
			Logging.error("Error in sourceTable " + e.getMessage());
		}
		// Close the Dynamic Connection : Manoj Adekar
		finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
	}

	/**
	 * Method to populate the hashtable with the tmcv,divisor and index values
	 * in different currencies for which exchange rate is available including
	 * the base currency for the index.
	 * 
	 * @param index_id
	 * @param request
	 */
	public static void addInIndexCurrencyWise(String index_id,
			HttpServletRequest request) {
		Connection connection = null;
		Connect con = ConnectInit.getConnect();
		try {
			table.clear();
			if (index_id == null)
				index_id = "1";
			Logging.debug("in addInIndexCurrencyWise ");
			// org.jfree.chart.demo.servlet.ComposeIndex ci=new
			// org.jfree.chart.demo.servlet.ComposeIndex();
			ComposeIndex ci = ConnectInit.getComposeIndex();
			String price = null, base_currency_id = null, strtmcv = null, tmcv = null, divisor = null, index_value = null, base_currency_name = null, ind_valuestr = null;
			Vector curr_list = new Vector();
			IndexCalculatorForm.setCount(1);

			// Change by Manoj Adekar for Dynamic connection using
			// getdbConnection() instead of getConnection()
			if (connection == null) {
				connection = con.getdbConnection();
			}
			PreparedStatement stmt = connection
					.prepareStatement(ConnectInit.queries
							.getProperty("get_index_detail_for_index_currency_wise"));
			stmt.setString(1, index_id);
			stmt.setString(2, index_id);
			Logging.debug("stmt is " + stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				base_currency_id = rs.getString(1);
				tmcv = rs.getString(2);
				divisor = rs.getString(3);
				index_value = rs.getString(4);
			}
			int count = 0;
			Logging.debug(" base_currency_id " + base_currency_id + " tmcv "
					+ tmcv + " divisor " + divisor + " index_value "
					+ index_value);
			PreparedStatement stmt1 = connection
					.prepareStatement(ConnectInit.queries
							.getProperty("get_currency_list_for_index"));
			stmt1.setString(1, base_currency_id);
			stmt1.setString(2, base_currency_id);
			Logging.debug(stmt1 + "");
			ResultSet rs1 = stmt1.executeQuery();
			while (rs1.next()) {
				count++;
				if (count == 1) {
					base_currency_name = getCurrencyName(base_currency_id);
					base_currency_name = base_currency_name
							+ " -  (base currency for index)";
					table.put(String.valueOf(base_currency_id),
							new IndexCalculatorForm(base_currency_id,
									base_currency_name, tmcv, divisor,
									index_value));// populating hashatable with
													// currency_id,currency_name,tmcv,
													// divisorand index_value
													// currency_id is used as
													// key.(for first record
													// proving index detail with
													// index base currency)
				}
				String currency_id = rs1.getString(1);
				String currency_name = rs1.getString(2);
				String curr_exch_rate = null;
				Logging.debug("before getIndexCurrancyExchRate ");
				curr_exch_rate = getIndexCurrancyExchRate(base_currency_id,
						currency_id);
				Logging.debug("after getIndexCurrancyExchRate ");
				if (curr_exch_rate == null) {
					curr_exch_rate = getIndexCurrancyExchRate(currency_id,
							base_currency_id);
					if (curr_exch_rate != null) {
						double exch_rate = (double) Double
								.parseDouble(curr_exch_rate);
						exch_rate = (1 / exch_rate);
						Logging.debug(" before multiplying exch_rate "
								+ exch_rate + " index_value " + index_value);
						double ind_value = exch_rate
								* ((double) Double.parseDouble(index_value));
						double dtmcv = exch_rate
								* ((double) Double.parseDouble(tmcv));
						ind_valuestr = new Double(ind_value).toString();
						strtmcv = new Double(dtmcv).toString();
					}
				} else {
					double exch_rate = (double) Double
							.parseDouble(curr_exch_rate);
					Logging.debug(" before multiplying exch_rate " + exch_rate
							+ " index_value " + index_value);
					double ind_value = exch_rate
							* ((double) Double.parseDouble(index_value));
					double dtmcv = exch_rate
							* ((double) Double.parseDouble(tmcv));
					ind_valuestr = new Double(ind_value).toString();
					strtmcv = new Double(dtmcv).toString();
				}
				Logging.debug(" currency_id " + currency_id + " currency_name "
						+ currency_name + " tmcv " + strtmcv + " divisor "
						+ divisor + " index_value " + ind_valuestr);
				table.put(String.valueOf(currency_id), new IndexCalculatorForm(
						currency_id, currency_name, strtmcv, divisor,
						ind_valuestr));// populating hashatable with
										// currency_id,currency_name,tmcv,
				// divisor and index_value currency_id is used as key.
			}
		} catch (Exception e) {
			Logging.error("Error in sourceTable " + e.getMessage());
		}
		// Close the Dynamic Connection : Manoj Adekar
		finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
	}

	/**
	 * get currency name when currency id is inputed.
	 * 
	 * @param curr_id
	 * @return currency name (String).
	 */
	public static String getCurrencyName(String curr_id) {
		String curr_name = null;
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		try {
			Logging.debug(" in getCurrencyName");
			// Change by Manoj Adekar for Dynamic connection using
			// getdbConnection() instead of getConnection()
			if (connection == null) {
				connection = con.getdbConnection();
			}
			PreparedStatement stmt1 = connection
					.prepareStatement(ConnectInit.queries
							.getProperty("get_currency_name"));
			stmt1.setString(1, curr_id);
			Logging.debug(stmt1 + "");
			ResultSet rs1 = stmt1.executeQuery();
			while (rs1.next()) {
				curr_name = (String) rs1.getString(2);
			}
		} catch (SQLException e) {
			Logging.debug(" Error : " + e.getMessage());
		}
		// Close the Dynamic Connection : Manoj Adekar
		finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		Logging.debug(" CurrencyName is " + curr_name);
		return curr_name;
	}

	/**
	 * to get currency exchange rate between to currencies passed.
	 * 
	 * @param id1
	 * @param id2
	 * @return
	 */
	public static String getIndexCurrancyExchRate(String id1, String id2) {
		String cexch_rate = null;
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		Logging.debug("in getIndexCurrancyExchRate");
		try {
			if (connection == null)
				connection = con.getdbConnection();
			if (id1.equals(id2)) {
				cexch_rate = "1.00";
			} else {
				ResultSet rst11 = con.indwtResult(connection,
						"get_currency_exchange_rate", id1, id2);
				while (rst11.next()) {
					if (rst11.getString(1) == null) {
						cexch_rate = "0";
					} else {
						cexch_rate = (String) rst11.getString(1);
					}
				}
				rst11.close();
				Logging.debug("cexch_rate is " + cexch_rate);
			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :" + sqlexp.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ex) {
				Logging.error(" Error : Unable to close Connection "
						+ ex.getMessage());
			}
		}
		return cexch_rate;
	}

	/**
	 * method to compute the indexconsidering the user entered prices.
	 */
	public static String computeIndexNormally(String indexID,
			HttpServletRequest request) {
		Connection connection = null;
		Connect con = ConnectInit.getConnect();
		CFormula cFor = ConnectInit.getCFormula();
		Logging.debug("Computing Index for Index Id : " + indexID);
		String time = getTime();
		long l_indexID = Long.parseLong(indexID);
		double high, low;
		double ltp = 0, iwf = 0, mcv = 0, exch = 1, tmcv = 0, flag = 0, divisor = 0, base_value = 0, indexVal = 0, fto_exch = 1, icomp_mcv = 0, icomp_iwf = 0;
		String date = "";
		boolean firstDailyValue = false;
		long id, baseCurrencyIdForCurrencyTypeIndex = 66;
		long childIndexId = 0, ml = 0, tis = 0, stkid = 0, curridStk = 0, curridIndex = 0, stkId = 0, currId = 0;
		;
		v.clear();
		Logging.debug("initial tmcv for  Id : " + indexID + " is : " + tmcv);
		try {
			// doing normal calculation
			if (connection == null) {
				connection = con.getdbConnection();
			}
			if (!firstDailyValue) {
				PreparedStatement pst_preStat = connection
						.prepareStatement(ConnectInit.queries
								.getProperty("compute_index"));
				pst_preStat.setLong(1, l_indexID);
				date = QueryClass.formatDate();
				Logging.debug("l_indexID " + l_indexID + " date " + date);
				pst_preStat.setString(2, date);
				Logging.debug(pst_preStat.toString());
				rst = pst_preStat.executeQuery();// executes query to get data
													// for
				// index computation
				// get all ltp values and stock id for the given date
				while (rst.next()) {
					Logging.debug("Row no." + rst.getRow());
					stkid = rst.getLong("stock_id");// get stock id
					String ovalue = "uprice:" + stkid;
					String user_price = request.getParameter(ovalue);// get user
																		// entered
																		// value
																		// for
																		// price.
					Logging.debug("user_price " + user_price);
					if (user_price == null || (user_price.equals(""))) {
						ltp = rst.getDouble("ltp");// get ltp
					} else {
						ltp = (double) Double.parseDouble(user_price);
					}
					iwf = rst.getDouble("iwf");// get iwf
					ml = rst.getLong("market_lot");// get market lot
					tis = (long) rst.getDouble("tis");// get tis
					curridStk = rst.getLong("stock_currency_id");// get
																	// currencyId
					// for stock
					curridIndex = rst.getLong("base_currency_id");

					date = rst.getString("price_date");
					Logging.debug("Index curr id" + curridIndex);
					Logging.debug("Stock curr id" + curridStk);
					if ((curridStk != curridIndex) && (flag == 0)) {
						rst2 = getExchCode();
						Logging.debug("Get ResultSet");
						rst2.next();
						stkId = rst2.getLong("from_currency_id");
						currId = rst2.getLong("to_currency_id");

						fto_exch = rst2.getDouble("intra_day_ex_rate_value");
						flag = 1;
					}
					if ((curridStk != curridIndex)) {
						while (rst2.next()) {
							Logging.debug("In Exchange Code checking");
							if ((curridStk == stkId) && (curridIndex == currId)) {
								exch = fto_exch;
								Logging.debug("Exchange Found : " + exch);
								break;
							}
						}
						rst2.beforeFirst();
					}
					Logging.debug("Finally Exchange rate is : " + exch);
					System.out.print("tis is  " + tis);
					base_value = rst.getDouble("base_value");// get base value
																// for
					// index
					mcv = cFor.calMarketCap(ltp, ml, exch, tis, iwf);// calculates

					v.addElement(new Double(mcv));// collects mcv in vector

					icomp_mcv = mcv;
				}
				Logging.debug("initial1 tmcv for  Id : " + indexID + " is : "
						+ tmcv + "v size is : " + v.size());

				tmcv = cFor.totalMarketCap(v);// calculate tmcv
				Logging.debug("initial2 tmcv for  Id : " + indexID + " is : "
						+ tmcv + "v size is : " + v.size());
				Logging.debug("Total Market Cap " + tmcv);
				if (tmcv == 0) {
					return "----";
				}
				try {
					pst_preStat = connection
							.prepareStatement(ConnectInit.queries
									.getProperty("select_daily_divisor"));
					pst_preStat.setLong(1, l_indexID);// set ? for index id
					rst1 = pst_preStat.executeQuery();// execute query

					if (!rst1.next())// (rst1.getRow() == 0)//check
					{
						divisor = cFor.divisor(tmcv, base_value);
						Logging.debug("divisor value computed " + divisor);

					} else {
						divisor = rst1.getDouble(1);
						Logging.debug(" Divisor" + divisor);
						Logging.debug("divisor taken " + divisor);
					}
				} catch (SQLException e) {
					Logging.error("Error : index values" + e.getMessage());
				}
				// indexVal = cFor.index(tmcv, divisor);// index compute
				indexVal = (tmcv / divisor);
				Logging.debug("******** value calculated = " + indexVal);

			}
		} catch (Exception e) {
			Logging.debug("Error : " + e.getMessage());
		}
		// normal calculation ended
		finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ex) {
				Logging.error(" Error : nable to close Connection "
						+ ex.getMessage());
			}
		}
		return (new Double(indexVal).toString());
	}

	public static String formatDate() {
		SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
		Date dt = new Date();
		return fr.format(dt).toString();
	}

	/*
	 * 
	 * By Manoj C. Adekar
	 */

	/*
	 * Method to Calculate Index Value According to the LTP
	 */

	public static String computeIndexNormallyLtp(String indexID) {
		Logging.debug("Computing Index for Index Id : " + indexID);
		String time = getTime();
		CFormula cFor = ConnectInit.getCFormula();
		long l_indexID = 0;
		// long l_indexID = Long.parseLong(indexID);
		if (indexID != null) {
			l_indexID = Long.parseLong(indexID);
		}
		double high, low;
		double ltp = 0, iwf = 0, mcv = 0, exch = 1, tmcv = 0, flag = 0, divisor = 0, base_value = 0, indexVal = 0, fto_exch = 1, icomp_mcv = 0, icomp_iwf = 0;
		String date = "";
		boolean firstDailyValue = false;
		long id, baseCurrencyIdForCurrencyTypeIndex = 66;
		long childIndexId = 0, ml = 0, tis = 0, stkid = 0, curridStk = 0, curridIndex = 0, stkId = 0, currId = 0;
		Connect con = ConnectInit.getConnect();
		Connection connection = null;

		v.clear();
		Logging.debug("initial tmcv for  Id : " + indexID + " is : " + tmcv);
		try {
			if (connection == null) {
				connection = con.getdbConnection();
			}
			// doing normal calculation
			if (!firstDailyValue) {
				// PreparedStatement pst_preStat =
				// connection.prepareStatement(con.queries.getProperty("compute_indexltp"));
				PreparedStatement pst_preStat = connection
						.prepareStatement(ConnectInit.queries
								.getProperty("compute_indexltp"));
				pst_preStat.setLong(1, l_indexID);
				date = QueryClass.formatDate();
				Logging.debug("l_indexID " + l_indexID + " date " + date);
				pst_preStat.setString(2, date);
				Logging.debug(pst_preStat.toString());
				rst = pst_preStat.executeQuery();// executes query to get data
													// for
				// index computation
				// get all ltp values and stock id for the given date
				while (rst.next()) {
					Logging.debug("Row no." + rst.getRow());
					stkid = rst.getLong("stock_id");// get stock id
					String tempstkid = String.valueOf(stkid);

					String ltpstr = rst.getString("ltp");// get ltp
					ltp = (double) Double.parseDouble(ltpstr);

					iwf = rst.getDouble("iwf");// get iwf
					ml = rst.getLong("market_lot");// get market lot
					tis = (long) rst.getDouble("tis");// get tis
					curridStk = rst.getLong("stock_currency_id");// get
																	// currencyId
					// for stock
					curridIndex = rst.getLong("base_currency_id");

					date = rst.getString("price_date");
					Logging.debug("Index curr id" + curridIndex);
					Logging.debug("Stock curr id" + curridStk);
					if ((curridStk != curridIndex) && (flag == 0)) {
						rst2 = getExchCode();
						Logging.debug("Get ResultSet");
						rst2.next();
						stkId = rst2.getLong("from_currency_id");
						currId = rst2.getLong("to_currency_id");

						fto_exch = rst2.getDouble("intra_day_ex_rate_value");
						flag = 1;
					}
					if ((curridStk != curridIndex)) {
						while (rst2.next()) {
							Logging.debug("In Exchange Code checking");
							if ((curridStk == stkId) && (curridIndex == currId)) {
								exch = fto_exch;
								Logging.debug("Exchange Found : " + exch);
								break;
							}
						}
						rst2.beforeFirst();
					}
					Logging.debug("Finally Exchange rate is : " + exch);
					System.out.print("tis is  " + tis);
					base_value = rst.getDouble("base_value");// get base value
																// for
					// index
					mcv = cFor.calMarketCap(ltp, ml, exch, tis, iwf);// calculates

					v.addElement(new Double(mcv));// collects mcv in vector

					icomp_mcv = mcv;
				}
				Logging.debug("initial1 tmcv for  Id : " + indexID + " is : "
						+ tmcv + "v size is : " + v.size());

				tmcv = cFor.totalMarketCap(v);// calculate tmcv
				System.out.println("Total market cap value ====" + tmcv);
				Logging.debug("initial2 tmcv for  Id : " + indexID + " is : "
						+ tmcv + "v size is : " + v.size());
				Logging.debug("Total Market Cap " + tmcv);
				// added by samiksha starts
				IndexCalculatorForm.setTmcv(tmcv + "");
				System.out.println("Total market cap value 11111 ===="
						+ IndexCalculatorForm.getTmcv());
				// ends
				if (tmcv == 0) {
					return "----";
				}
				try {
					pst_preStat = connection
							.prepareStatement(ConnectInit.queries
									.getProperty("select_daily_divisor"));
					pst_preStat.setLong(1, l_indexID);// set ? for index id
					rst1 = pst_preStat.executeQuery();// execute query

					if (!rst1.next())// (rst1.getRow() == 0)//check
					{
						divisor = cFor.divisor(tmcv, base_value);
						Logging.debug("divisor value computed " + divisor);

					} else {
						divisor = rst1.getDouble(1);
						Logging.debug(" Divisor" + divisor);
						Logging.debug("divisor taken " + divisor);
					}
				} catch (SQLException e) {
					Logging.error("Error : index values" + e.getMessage());
				}
				// indexVal = cFor.index(tmcv, divisor);// index compute
				indexVal = (tmcv / divisor);
				Logging.debug("******** value calculated = " + indexVal);

			}
		} catch (Exception e) {
			Logging.debug("Error : " + e.getMessage());
		}
		// normal calculation ended
		finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				Logging.debug("Unable to close Connection :" + e);
			}
		}
		return (new Double(indexVal).toString());
	}

	/**
	 * method to get the current system time.
	 */
	public static String getTime() {
		java.util.Date dt = new java.util.Date();
		dt.getDate();
		return dt.toString().split(" ")[3];
	}

	/**
	 * method to generate the string buffer to generate the table content for
	 * Index Currency Wise.
	 * 
	 * @param data
	 * @param request
	 * @return
	 */
	public static StringBuffer addRowsInIndexCurrencyWise(Hashtable data,
			HttpServletRequest request) {
		// AdjustDecimal ad=new AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		StringBuffer buffer = new StringBuffer();
		try {
			IndexCalculatorForm.setCount(1);

			for (Enumeration e = data.keys(); e.hasMoreElements();)// while(rs.next())
			{
				String id = e.nextElement().toString();
				IndexCalculatorForm rs = (IndexCalculatorForm) data.get(id);

				buffer.append("<tr>");
				buffer.append("<td width='5%' align='center' class='gridStyle-blue' height='1'>");
				// buffer.append("<font face='Arial' size='1' color='blue'>");
				buffer.append("<input type='checkbox' name='currencyid'  value='"
						+ (String) rs.getCurrency_id() + "' />");
				// buffer.append("</font>");
				buffer.append("</td>");
				buffer.append("<td width='30%' align='left' class='gridStyle-blue' height='1'>");
				// buffer.append("<font face='Arial' size='1' color='blue'>");
				buffer.append(rs.getCurrency_name());
				// buffer.append("</font>");
				buffer.append("</td>");

				buffer.append("<td width='20%' align='right'  class='gridStyle-blue' height='1'>");
				// buffer.append("<font face='Arial' size='1' color='blue'>");
				buffer.append("<input type= text name =tmcv:"
						+ rs.getCurrency_id());
				String tmcv = rs.getIndcurr_tmcv();
				tmcv = ad.shareholdingpatt(tmcv);
				tmcv = ad.indexcompose(tmcv);
				buffer.append(" size='25' align='left' value='" + tmcv
						+ "' readonly='true'/>");
				// buffer.append("</font>");
				buffer.append("</td>");

				buffer.append("<td width='20%' align='right' class='gridStyle-blue' height='1'>");
				// buffer.append("<font face='Arial' size='1' color='blue'>");
				buffer.append("<input type= text name =divisor:"
						+ rs.getCurrency_id());
				String divisor = rs.getDivisor();
				divisor = ad.shareholdingpatt(divisor);
				divisor = ad.indexcompose(divisor);
				buffer.append(" size='25' align='left' value='" + divisor
						+ "' readonly='true' />");

				// buffer.append("</font>");
				buffer.append("</td>");

				buffer.append("<td width='25%' align='right' class='gridStyle-blue' height='1'>");
				// buffer.append("<font face='Arial' size='1' color='blue'>");
				buffer.append("<input type= text name =indValue:"
						+ rs.getCurrency_id());
				String index_value = rs.getIndex_value();
				index_value = ad.shareholdingpatt(index_value);
				index_value = ad.indexcompose4digit(index_value);
				buffer.append(" size='30' align='left' value='" + index_value
						+ "' readonly='true' />");

				// buffer.append("</font>");
				buffer.append("</td>");

				buffer.append("</tr>");

			}

		} catch (Exception e) {
			Logging.debug("Error : " + e.getMessage());
		}
		// Logging.debug("Buffer is "+buffer);
		return buffer;

	}

	/**
	 * method to generate the string buffer to generate the table content for
	 * index calculator.
	 */
	public static StringBuffer addRowsIncCalculatorTablePrice(Hashtable data,
			HttpServletRequest request, String option) {

		StringBuffer buffer = new StringBuffer();
		try {
			IndexCalculatorForm.setCount(1);
			// AdjustDecimal ad=new AdjustDecimal();
			AdjustDecimal ad = ConnectInit.getAdjustDecimal();
			String value = null;
			for (Enumeration e = data.keys(); e.hasMoreElements();)// while(rs.next())
			{

				String id = e.nextElement().toString();
				IndexCalculatorForm rs = (IndexCalculatorForm) data.get(id);

				buffer.append("<tr>");
				buffer.append("<td width='10%' align='left' class='gridStyle-blue' height='1'>");
				// buffer.append("<font face='Arial' size='1' color='blue'>");
				buffer.append((new Integer(IndexCalculatorForm.getCount())
						.toString()));
				// buffer.append("</font>");
				buffer.append("</td>");
				buffer.append("<td width='30%' align='left' class='gridStyle-blue' height='1'>");
				// buffer.append("<font face='Arial' size='1' color='blue'>");
				buffer.append(rs.getSymbol());
				// buffer.append("</font>");
				buffer.append("</td>");

				buffer.append("<td width='20%' align='right'  class='gridStyle-blue' height='1'>");
				// buffer.append("<font face='Arial' size='1' color='blue'>");
				value = rs.getPrice();
				value = ad.indexcompose(value);
				value = AdjustDecimal.ArrangeAsNumeric(value) + "  ";
				buffer.append(value);
				// buffer.append("</font>");
				buffer.append("</td>");

				buffer.append("<td width='20%' align='right' class='gridStyle-blue' height='1'>");
				// buffer.append("<font face='Arial' size='1' color='blue'>");
				String ovalue = "uprice:" + rs.getStock_id();
				ovalue = request.getParameter(ovalue);
				Logging.debug("ovalue is" + ovalue);
				if (ovalue == null || ovalue.equals("")
						|| (option != null && option.equals("Reset"))) {
					buffer.append("<input type= text name =uprice:"
							+ rs.getStock_id());
					buffer.append(" size='30' align='right' value=" + "" + ">");
				} else {
					Logging.debug("In else buffer " + ovalue);
					buffer.append("<input type= text name =uprice:"
							+ rs.getStock_id());
					buffer.append(" size='30' align='right' value=" + ovalue
							+ ">");
				}
				// buffer.append("</font>");
				buffer.append("</td>");
				buffer.append("</tr>");

			}

		} catch (Exception e) {
			Logging.debug("Error : " + e.getMessage());
		}
		return buffer;

	}

	/**
	 * method to generate the string buffer to generate the table content for
	 * portpolio tis calculator.
	 */
	public static StringBuffer addStocksIncCalculatorTable(Hashtable data,
			HttpServletRequest request, String option) {

		StringBuffer buffer = new StringBuffer();
		try {
			// AdjustDecimal ad=new AdjustDecimal();
			AdjustDecimal ad = ConnectInit.getAdjustDecimal();
			IndexCalculatorForm.setCount(1);
			String value = null;
			for (Enumeration e = data.keys(); e.hasMoreElements();)// while(rs.next())
			{
				String id = e.nextElement().toString();
				IndexCalculatorForm rs = (IndexCalculatorForm) data.get(id);

				buffer.append("<tr bgcolor='#84AADE'>");
				buffer.append("<td width='10%' align='left' height='1'> <font face='Arial'  size='2'>");
				// buffer.append("<font face='Arial' size='1' color='blue'>");
				buffer.append("  "
						+ (new Integer(IndexCalculatorForm.getCount())
								.toString()));
				// buffer.append("</font>");
				buffer.append("</font></td>");
				buffer.append("<td width='25%' align='left'  height='1'> <font face='Arial'  size='2'>");
				// buffer.append("<font face='Arial' size='1' color='blue'>");
				buffer.append(" " + rs.getSymbol());
				// buffer.append("</font>");
				buffer.append("</font></td>");
				value = null;
				value = rs.getPrice();
				value = ad.indexcompose(value);
				value = AdjustDecimal.ArrangeAsNumeric(value) + "  ";
				buffer.append("<td width='20%' align='right'  height='1'> <font face='Arial'  size='2'>");
				// buffer.append("<font face='Arial' size='1' color='blue'>");
				buffer.append(value);
				// buffer.append("</font>");
				buffer.append("</font></td>");
				value = null;
				value = rs.getMcv();
				value = ad.indexcompose(value);
				value = AdjustDecimal.ArrangeAsNumeric(value) + "  ";
				buffer.append("<td width='25%' align='right'  height='1'> <font face='Arial'  size='2'>");
				// buffer.append("<font face='Arial' size='1' color='blue'>");
				buffer.append(value);
				// buffer.append("</font>");
				buffer.append("</font></td>");

				// value=null;
				value = rs.getTis();
				value = ad.indexcompose(value);
				value = AdjustDecimal.ArrangeAsNumeric(value) + "  ";
				buffer.append("<td width='20%' align='right'  height='1'> <font face='Arial'  size='2'>");
				// buffer.append("<font face='Arial' size='1' color='blue'>");
				buffer.append(value);
				// buffer.append("<input type='text' name='open_value' size='10' dir='rtl' value="+rs.getOpen_value()+"  />");
				// buffer.append("</font>");
				buffer.append("</font></td>");

				buffer.append("</tr>");

			}

		} catch (Exception e) {
			Logging.debug("Error : " + e.getMessage());
		}
		return buffer;

	}

	/**
	 * method to get the exchange code ResultSet.
	 */
	public static ResultSet getExchCode() {
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		ResultSet rs = null;
		try {
			if (connection == null) {
				connection = con.getdbConnection();
			}
			Logging.debug("in exchange code query");
			PreparedStatement pst_preStat = connection
					.prepareStatement(ConnectInit.queries
							.getProperty("get_exchange_rate"));
			Logging.debug("pst_preStat for exchange code currency index : "
					+ pst_preStat);
			rs = pst_preStat.executeQuery();
			// executes query for getting exchange codes

			Logging.debug("Query executed");

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			Logging.error("SQL Error : " + e1.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ex) {
				Logging.error(" Error : nable to close Connection "
						+ ex.getMessage());
			}
		}
		return rs;
	}

	/**
	 * method to calculate total issuable shares of individual stocks for the
	 * user entered total market capital.
	 */
	public static void getMultiplyingFactor(Hashtable data, String indexID,
			HttpServletRequest request) {
		double tmcv = 0.0, usertmcv = 0.0, multfact = 1.0, tis = 0.0;
		String strtmcv = null, strusertmcv = null, strmcv = null;
		// org.jfree.chart.demo.servlet.AdjustDecimal ad=new
		// org.jfree.chart.demo.servlet.AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		Hashtable newdata = new Hashtable();
		for (Enumeration e = data.keys(); e.hasMoreElements();)// while(rs.next())
		{

			String id = e.nextElement().toString();
			IndexCalculatorForm rs = (IndexCalculatorForm) data.get(id);
			double mcv = (double) Double.parseDouble(rs.getMcv());
			/* By Manoj Adekar */
			strmcv = new Double(mcv).toString();
			strmcv = ad.shareholdingpatt(strmcv);
			mcv = (double) Double.parseDouble(strmcv);
			/* upto here */
			tmcv = tmcv + mcv;
			strtmcv = new Double(tmcv).toString();
			strtmcv = ad.shareholdingpatt(strtmcv);
		}
		IndexCalculatorForm.setTmcv(strtmcv);

		if (request.getParameter("tis_value") != null
				|| (request.getParameter("tis_value")).equals("")) {
			usertmcv = (double) Double.parseDouble(request
					.getParameter("tis_value"));
			strusertmcv = new Double(usertmcv).toString();
			strusertmcv = ad.shareholdingpatt(strusertmcv);
			IndexCalculatorForm.setTmcv(strusertmcv);
		} else {
			usertmcv = 0.0;
		}
		// app.Logging.getDebug("strtmcv is "+strtmcv+" strusertmcv is "+strusertmcv);
		if (tmcv != 0) {
			multfact = (usertmcv / tmcv);
		} else {
			multfact = 1.0;
		}
		Logging.debug(" getMultiplyingFactor " + multfact);

		for (Enumeration e = data.keys(); e.hasMoreElements();)// while(rs.next())
		{
			String id = e.nextElement().toString();
			IndexCalculatorForm rs = (IndexCalculatorForm) data.get(id);
			String stk_id = rs.getStock_id();
			String iwf = rs.getIwf();
			String mcv = rs.getMcv();
			String price = rs.getPrice();
			if (price == null)
				price = "0.00";
			Logging.debug("price is " + price);
			String symbol = rs.getSymbol();
			String stk_currency_id = rs.getStock_currency_id();
			String market_lot = rs.getMkt_lot();
			double mcv_value = (double) Double.parseDouble(mcv);
			double mkt_value = (double) Double.parseDouble(market_lot);
			double iwf_value = (double) Double.parseDouble(iwf);
			double price_value = (double) Double.parseDouble(price);
			Logging.debug("price_value is " + price_value);
			mcv_value = mcv_value * multfact;
			mcv = new Double(mcv_value).toString();
			double currexcRate = getCurrancyExchRate(indexID, stk_id);
			if (mcv_value != 0.0) {
				tis = mcv_value
						/ (mkt_value * iwf_value * price_value * currexcRate);
			} else {
				tis = 0.0;
			}
			// tis=
			long ltis = (long) Math.floor(tis + 0.5);
			// String strtis=new Double(tis).toString();
			String strtis = new Long(ltis).toString();
			// Double ddtis= new Double(strtis);
			// long lngTis= longValue(ddtis);
			// long l = Long.parseLong(strtis);
			// strtis=new Long(l).toString();
			strmcv = ad.shareholdingpatt(mcv);
			strtis = ad.shareholdingpatt(strtis);
			table.remove(String.valueOf(symbol));
			table.put(String.valueOf(symbol), new IndexCalculatorForm(stk_id,
					strtis, iwf, strmcv, price, symbol, stk_currency_id,
					market_lot));// populating hashatable with
									// stock_id,symbol,mcv & price,symbol is
									// used as key.
		}
		/*
		 * Logging.debug("size hashtable : "+table.size()); Enumeration
		 * e=table.keys(); while(e.hasMoreElements()){ String
		 * st=(String)e.nextElement(); }
		 */
	}

	/**
	 * get currency exchange rate for the stock included in an index.
	 * 
	 * @param index
	 * @param stockid
	 * @return exchange rate value(double).
	 */
	public static double getCurrancyExchRate(String index, String stockid) {
		String cexch_rate = null;
		String stk_currency_id = null, ind_currency_id = null;
		// app.Connect con=new app.Connect();
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		try {
			if (connection == null)
				connection = con.getdbConnection();
			ResultSet rstexc = con.indwtResult(connection,
					"get_index_and_stock_currency_id", stockid, index);
			int i = 0;
			// app.Logging.getDebug("rst is "+rstexc);
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
			rstexc.close();
			if (stk_currency_id.equals(ind_currency_id)) {
				cexch_rate = "1.00";
			} else {
				cexch_rate = getIndexCurrancyExchRate(ind_currency_id,
						stk_currency_id);
			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :" + sqlexp.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ex) {
				Logging.error(" Error : nable to close Connection "
						+ ex.getMessage());
			}
		}
		return ((double) Double.parseDouble(cexch_rate));
	}

	public static Vector getIndex_calctr() {
		return index_calctr;
	}

	public static void setIndex_calctr(Vector index_calctr) {
		IndexCalculatorCollection.index_calctr = index_calctr;
	}

}
