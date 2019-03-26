/*
 * Created on Sep 10, 2004
 *
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.compute;

import harrier.income.com.entities.CFormula;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jfree.chart.demo.servlet.AdjustDecimal;

import app.ComputeIndexForm;
import app.Connect;
import app.IncomeLibrary;
import app.IndexCalculatorCollection;
import app.QueryClass;
import app.QueryClass1;

import com.harrier.initializeation.ConnectInit;

/**
 * @author Vivek
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class CIndexCalculator {
	Logger Logging = Logger.getLogger(CIndexCalculator.class);
	private PreparedStatement pst_preStat, pst, pst3, pst4, pst5, pst6, pst7;

	private Statement stm;

	private ResultSet rst, rst1, rst2, rst3, rst4, rs5, rs6;

	private static app.Connect con_connect;

	// private Properties p_queries, prop;

	// private CFormula cFor = new CFormula();

	private double ltp = 0, iwf = 0, mcv = 0, mcv1 = 0, exch = 1, tmcv = 0,
			divisor = 0, adjusted_divisor = 0, last_tmcv = 0, base_value = 0,
			indexVal = 0, flag = 0, fto_exch = 1, icomp_mcv = 0, icomp_iwf = 0;
	private String last_date = "";
	private Vector v = new Vector();

	private long ml = 0, tis = 0, stkid = 0, curridStk = 0, curridIndex = 0;

	private long stkId = 0, currId = 0;

	private int type_of_index;
	/*
	 * Two variables added as check condition for Historic calculation on
	 * 3-Nov-07
	 */
	public String check_history = "no";
	boolean histforlast = false;
	double[] fiftytwo_min_max = new double[2];

	public CIndexCalculator() {
		con_connect = ConnectInit.getConnect();
		// con_connect.getConnectionForTransaction();
		// loads file required
		/*
		 * prop = p_queries = new Properties(); try { p_queries.load(new
		 * FileInputStream(Connect.resourceurl + "resources/query.properties"));
		 * Logging.debug("File Loaded"); } catch (IOException e) {
		 * Logging.debug("unable to get file"); Logging.error("error in file");
		 * }
		 */
	}

	public ResultSet getExchCode(Connection connection) {
		ResultSet rs = null;
		try {
			Logging.debug("in exchange code query");
			pst_preStat = connection.prepareStatement(ConnectInit.queries
					.getProperty("get_exchange_rate"));
			Logging.debug("pst_preStat for exchange code currency index : "
					+ pst_preStat);
			rs = pst_preStat.executeQuery();
			// executes query for getting exchange codes
			// Logging.getDebug("Result set is :" + rs.)
			Logging.debug("Query executed");

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			Logging.debug(e1);
		}
		Logging.debug("Returning resultset");
		return rs;
	}

	public int getExchCodeForTotalReturns(int to, int from,
			Connection connection) {
		int exchangecurrencyid = -999;
		ResultSet rs = null;
		try {
			if (to == from) {
				Logging.debug("to==from");

				return 1;
			}
			Logging.debug("in exchange code query");
			String temp = ConnectInit.queries
					.getProperty("currency_combination");
			Logging.debug(" query for exchange code  : " + temp + "\n to :"
					+ to + "\n from : " + from);
			pst_preStat = connection.prepareStatement(temp);
			pst_preStat.setInt(1, to);
			pst_preStat.setInt(2, from);
			rs = pst_preStat.executeQuery();
			if (rs.next()) {
				Logging.debug("getExchCodeForTotalReturns");
				exchangecurrencyid = rs.getInt("from_to_currency_id");
				// Logging.debug("returning exchangecurrencyid as"+
				// exchangecurrencyid);

				return exchangecurrencyid;
			}
			Logging.debug("Query executed");

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			Logging.debug(e1);
			return exchangecurrencyid;
		}
		Logging.debug("Returning exchangecurrencyid");
		return exchangecurrencyid;
	}

	public double getExchCodeForCurrencyIndex(int to, int from, String date,
			Connection connection) {
		double exchangecurrencyid = -999;
		ResultSet rs = null;
		try {
			if (to == from) {
				Logging.debug("to==from");
				return 1;
			}
			Logging.debug("in exchange code query");
			String temp = ConnectInit.queries
					.getProperty("currency_combination");
			Logging.debug(" query for exchange code  : " + temp + "\n to :"
					+ to + "\n from : " + from);
			pst_preStat = connection.prepareStatement(temp);
			pst_preStat.setInt(1, to);
			pst_preStat.setInt(2, from);
			// Logging.debug(" query for exchange code  : " + temp + "\n to :"
			// + to + "\n from : " + from);
			rs = pst_preStat.executeQuery();
			if (rs.next()) {
				Logging.debug("getExchCodeForTotalReturns");
				exchangecurrencyid = rs.getInt("from_to_currency_id");
				// Logging.debug("returning exchangecurrencyid as"
				// + exchangecurrencyid);
				temp = ConnectInit.queries
						.getProperty("currency_index_ex_rate");
				// Logging.getDebug(" query for exchange code : " + temp);
				pst_preStat = connection.prepareStatement(temp);
				pst_preStat.setDouble(1, exchangecurrencyid);
				pst_preStat.setString(2, date);
				Logging.debug("another query for exchange rate   : "
						+ pst_preStat);
				rs = pst_preStat.executeQuery();
				if (rs.next()) {
					exchangecurrencyid = rs.getDouble("ex_closing_value");
				} else {
					temp = ConnectInit.queries
							.getProperty("currency_index_exrate_date");
					// Logging.getDebug(" query for exchange code : " + temp);
					pst_preStat = connection.prepareStatement(temp);
					pst_preStat.setDouble(1, exchangecurrencyid);
					pst_preStat.setDouble(2, exchangecurrencyid);
					pst_preStat.setString(3, date);
					Logging.debug("another query for exchange rate   : "
							+ pst_preStat);
					rs = pst_preStat.executeQuery();
					if (rs.next()) {
						exchangecurrencyid = rs.getDouble("ex_closing_value");
					} else {
						return -11;
					}
				}
				return exchangecurrencyid;
			}
			Logging.debug("Query executed");

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			Logging.debug(e1);
			return exchangecurrencyid;
		}
		Logging.debug("Returning exchangecurrencyid");
		return exchangecurrencyid;
	}

	public String getTime() {
		java.util.Date dt = new java.util.Date();
		dt.getDate();
		return dt.toString().split(" ")[3];
	}

	public double ExchangeRateForCurrencyIndex(int childBaseCurrency,
			int ParentBaseCurrency, String basedate, Connection connection) {
		double exchangerate;
		if (childBaseCurrency == ParentBaseCurrency) {
			exchangerate = 1;
		} else {
			exchangerate = getExchCodeForCurrencyIndex((int) childBaseCurrency,
					(int) ParentBaseCurrency, basedate, connection);
			if (exchangerate == -11) {
				return -999;
			}
			if (exchangerate == -999) {
				exchangerate = getExchCodeForCurrencyIndex(
						(int) ParentBaseCurrency, (int) childBaseCurrency,
						basedate, connection);
				if (exchangerate == -11) {
					return -999;
				}
				if (exchangerate == -999) {
					exchangerate = 1;
				} else {
					exchangerate = 1 / exchangerate;
				}
			} else if (exchangerate == -11) {
				return -999;
			}
		}
		Logging.debug("For currency index the exchange rate is " + exchangerate);

		return exchangerate;
	}

	public double getValue(long l_indexID, long l_stkid, String date,
			Connection connection) {
		double ltp1 = 0, adjustprice = 0, closeprice = 0;
		try {

			pst_preStat = connection.prepareStatement(ConnectInit.queries
					.getProperty("get_adjusted_stock_price"));
			pst_preStat.setLong(1, l_indexID);
			pst_preStat.setLong(2, l_stkid);
			pst_preStat.setString(3, date);
			rst2 = pst_preStat.executeQuery();
			rst2.next();
			adjustprice = rst2.getDouble("adjusted_price");
			closeprice = rst2.getDouble("stock_closing_price");
			if (adjustprice == 0) {
				ltp1 = closeprice;
			} else {
				ltp1 = adjustprice;
			}
		} catch (SQLException e) {
			Logging.debug(e.getMessage());
			Logging.debug("index values" + e);
		}
		return ltp1;
	}

	public String computeIndex(String indexID, String settlement, String close,
			String toComputechildindexes, String ListOfChildIndices,
			HttpServletRequest req, String BaseDate, Connection connection) {
		Logging.debug("this : " + this);
		// AdjustDecimal ad=new AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		ArrayList datelistbeforebasedate = new ArrayList();
		ArrayList arrayList = null;
		if (BaseDate == null) {
			BaseDate = QueryClass.formatDate();
		}
		int diff2 = ComputeIndexForm.CompareDate(BaseDate,
				QueryClass.formatDate());
		Logging.debug("indexID is " + indexID
				+ " before base date size of datelistbeforebasedate is : "
				+ datelistbeforebasedate.size());
		if (diff2 == 0) {
			arrayList = new ArrayList();
			arrayList.add(BaseDate);
		} else {
			IncomeLibrary incomeLibrary = new IncomeLibrary();
			arrayList = incomeLibrary.getListOfDates(BaseDate, indexID);
			// null check added by samiksha
			if (indexID != null) {
				datelistbeforebasedate = getDateListBeforeBaseDate(BaseDate,
						indexID);
			}
		}
		Logging.debug(indexID
				+ " before base date size of datelistbeforebasedate is : "
				+ datelistbeforebasedate.size());
		Logging.debug(indexID + "arrayList.size() : " + arrayList.size());
		try {
			// null check added by samiksha
			long l_indexID = 0;
			if (indexID != null) {
				l_indexID = Long.parseLong(indexID);
			}

			PreparedStatement pst_check;
			// remove query
			pst_check = connection.prepareStatement(ConnectInit.queries
					.getProperty("get_index_type"));
			/*
			 * pst_check = Connect.con .prepareStatement("select index_type_id
			 * from index_master where index_id=?")
			 */;
			pst_check.setLong(1, l_indexID);
			ResultSet rs_check = pst_check.executeQuery();
			Logging.debug("result set values = " + rs_check);
			// rs_check.next();
			int index_type = 0;
			while (rs_check.next()) {
				index_type = rs_check.getInt("index_type_id");

			}
			// Logging.debug("index id = "+rs_check.getInt("index_type_id"));
			Logging.debug("index id = " + index_type);
			// int index_type = rs_check.getInt("index_type_id");

			type_of_index = index_type;
			Logging.debug("index type for calculation is : " + index_type);
			/*
			 * New Code Added on 25-10-07 for Average Index Type
			 */

			if (index_type == 6) {

				String returnString = null;
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
						cpricestatus = checkPriceAvailable(element, indexID,
								connection);
						if (cpricestatus == true) {
							if ((String) datelistbeforebasedate.get(k) != null) {
								returnString = computeIndexNormallyBeforeBaseDate(
										indexID, "n", "y", element, temp,
										connection);
							} else {
								temp = true;
								returnString = computeIndexNormallyBeforeBaseDate(
										indexID, settlement, close, element,
										temp, connection);
							}
						}
					}
				}
				Logging.debug("after calculating index value before base date");
				Logging.debug("arrayList.size() : " + arrayList.size());
				Iterator iter = arrayList.iterator();
				while (iter.hasNext()) {
					String element = (String) iter.next();

					Logging.debug("element : : " + (element));
					// Logging.debug("Before111 computing parent index with index value : ");
					boolean cpricestatus = false;

					if (diff2 == -1) {
						histforlast = true;
					}
					cpricestatus = checkPriceAvailable(element, indexID,
							connection);
					if (cpricestatus == true || diff2 == 0) {
						if (iter.hasNext()) {
							check_history = "yes";
							// Logging.debug("Check Historic "+check_history);
							returnString = computeIndexNormally1(indexID, "n",
									"y", element, temp, connection);
						} else {
							temp = true;
							if (histforlast) {
								returnString = computeIndexNormally1(indexID,
										"n", "y", element, temp, connection);
							} else {
								returnString = computeIndexNormally1(indexID,
										settlement, close, element, temp,
										connection);
							}
						}
					}
				}

				Logging.debug(connection
						+ "After computing parent index with index value : "
						+ returnString);
				// remove comment
				if (req != null && req.getAttribute("tmcverror") != null)
					req.removeAttribute("tmcverror");
				if (req != null && returnString.equals("----")) {
					req.setAttribute("tmcverror", "yes");
				}

				Logging.debug("After computing parent index with index value : "
						+ returnString);
				iter = arrayList.iterator();
				temp = false;
				while (iter.hasNext()) {
					String element = (String) iter.next();
					Logging.debug("element : : " + (element));
					boolean cpricestatus = false;
					cpricestatus = checkPriceAvailable(element, indexID,
							connection);
					if (cpricestatus == true || diff2 == 0) {
						if (iter.hasNext()) {
							check_history = "yes";
							ComputeChildIndexes(indexID, "n", "y",
									toComputechildindexes, ListOfChildIndices,
									element, temp, connection);
						} else {
							temp = true;
							ComputeChildIndexes(indexID, settlement, close,
									toComputechildindexes, ListOfChildIndices,
									element, temp, connection);
						}
					}
				}
				Logging.debug("this again  : " + this);
				returnString = ad.shareholdingpatt(returnString);
				Logging.debug("index value after calculation " + returnString);
				return returnString;

			}
			/*
			 * NEw Addition End Here
			 */
			if (index_type == 4) {
				String returnString = null;
				int count = 0;
				// implement historic
				Iterator iter = arrayList.iterator();
				boolean temp = false;
				ArrayList arr = new ArrayList();

				while (iter.hasNext()) {
					String element = (String) iter.next();
					Logging.debug("element : : " + (element));

					returnString = computeIndexforTotalReturns(indexID,
							settlement, close, element, connection);
					Logging.debug(arr.size() + "returnString : : "
							+ (returnString));
					try {
						Logging.debug("Checking value : : " + (returnString));
						double d = Double.parseDouble(returnString);
						arr.add(returnString);
						count++;
						Logging.debug("adding value : : " + (arr.size()));
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
				Iterator iterator = arr.iterator();
				// Logging.debug(": arr.size(); returning  : : " +
				// iterator.hasNext());
				while (iterator.hasNext()) {
					returnString = (String) iterator.next();
				}
				// returnString = computeIndexforTotalReturns(indexID,
				// settlement,
				// close,BaseDate);
				returnString = ad.shareholdingpatt(returnString);
				Logging.debug("index value after calculation " + returnString);
				return returnString;
			} else if (index_type == 5) {

				String returnString = null;
				boolean temp = false;
				// implement historic
				Logging.debug("before base date arraylist size is "
						+ datelistbeforebasedate.size());
				if (datelistbeforebasedate.size() != 0) {
					Logging.debug("before base date for loop");
					for (int k = 0; k < datelistbeforebasedate.size(); k++) {
						String element = (String) datelistbeforebasedate.get(k);
						Logging.debug("element : : " + (element));
						Logging.debug(connection
								+ "Before111 computing parent index with index value : ");
						if ((String) datelistbeforebasedate.get(k) != null) {
							returnString = computeCurrencyIndexBeforeBaseDate(
									indexID, "n", "y", element, connection);
						} else {
							temp = true;
							returnString = computeCurrencyIndexBeforeBaseDate(
									indexID, settlement, close, element,
									connection);
						}
					}
				}
				Logging.debug("after calculating index value before base date");

				Iterator iter = arrayList.iterator();

				while (iter.hasNext()) {
					String element = (String) iter.next();
					Logging.debug("element : : " + (element));

					if (iter.hasNext()) {
						returnString = computeCurrencyIndex(indexID, "n", "y",
								element, connection);
					} else {
						temp = true;
						returnString = computeCurrencyIndex(indexID,
								settlement, close, element, connection);
					}
				}

				// returnString = computeCurrencyIndex(indexID, settlement,
				// close,temp);

				// remove comment
				if (req != null && req.getAttribute("tmcverror") != null)
					req.removeAttribute("tmcverror");
				if (req != null && returnString.equals("----")) {
					Logging.debug("setting tmcv error : " + returnString);
					req.setAttribute("tmcverror", "yes");
				}
				returnString = ad.shareholdingpatt(returnString);
				Logging.debug("index value after calculation " + returnString);
				return returnString;
			} else {
				String returnString = null;
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
						// null check added by samiksha
						if (indexID != null) {
							cpricestatus = checkPriceAvailable(element,
									indexID, connection);
						}
						if (cpricestatus == true) {
							if ((String) datelistbeforebasedate.get(k) != null) {
								returnString = computeIndexNormallyBeforeBaseDate(
										indexID, "n", "y", element, temp,
										connection);

							} else {

								temp = true;
								returnString = computeIndexNormallyBeforeBaseDate(
										indexID, settlement, close, element,
										temp, connection);

							}
						} 
//						else {
//							System.out
//									.println("In else part 111111111 computeIndex method ");
//							returnString = "Stocks are not available on selected date. Please change the date or select other stocks from list.";
//						}
					}
				}
				Logging.debug("after calculating index value before base date");
				Logging.debug("arrayList.size() : " + arrayList.size());
				Iterator iter = arrayList.iterator();
				int count = 0;
				if (diff2 == -1) {
					histforlast = true;
				}
				while (iter.hasNext()) {
					count++;
					String element = (String) iter.next();
					Logging.debug("element : : " + (element));
					// Logging.debug("Before111 computing parent index with index value : "+count);
					boolean cpricestatus = false;
					// null check added by samiksha
					if (indexID != null) {
						cpricestatus = checkPriceAvailable(element, indexID,
								connection);
					}
					if (cpricestatus == true || diff2 == 0) {
						if (iter.hasNext()) {

							check_history = "yes";
							returnString = computeIndexNormally(indexID, "n",
									"y", element, temp, connection);
						} else {
							if (histforlast) {
								returnString = computeIndexNormally(indexID,
										"n", "y", element, temp, connection);
							} else {
								temp = true;
								// null check added by samiksha
								if (indexID != null) {
									returnString = computeIndexNormally(
											indexID, settlement, close,
											element, temp, connection);
								}
							}
						}
					} 
//					else {
//						System.out
//								.println("In else part 222222222 computeIndex method ");
//						returnString = "Stocks are not available on selected date. Please change the date or select other stocks from list.";
//					}
				}

				Logging.debug(connection
						+ "After computing parent index with index value : "
						+ returnString);
				// remove comment
				if (req != null && req.getAttribute("tmcverror") != null)
					req.removeAttribute("tmcverror");
				if (req != null && returnString.equals("----")) {
					req.setAttribute("tmcverror", "yes");
				}

				Logging.debug("After computing parent index with index value : "
						+ returnString);
				iter = arrayList.iterator();
				temp = false;
				while (iter.hasNext()) {
					String element = (String) iter.next();
					Logging.debug("element : : " + (element));
					// Logging.debug("Before111 computing parent index with index value : ");
					boolean cpricestatus = false;
					cpricestatus = checkPriceAvailable(element, indexID,
							connection);

					if (cpricestatus == true || diff2 == 0) {
						if (iter.hasNext()) {
							ComputeChildIndexes(indexID, "n", "y",
									toComputechildindexes, ListOfChildIndices,
									element, temp, connection);
						} else {
							temp = true;
							ComputeChildIndexes(indexID, settlement, close,
									toComputechildindexes, ListOfChildIndices,
									element, temp, connection);
						}
					}
				}
				Logging.debug("this again  : " + this);
				returnString = ad.shareholdingpatt(returnString);
				Logging.debug("index value after calculation " + returnString);
				return returnString;
			}

		} catch (Exception e) {
			Logging.debug(" Error : " + e.getMessage());
			System.out.println(" Error  CIndexxcAlculator :--------"
					+ e.getMessage());
			return "---";
		}

	}

	// query to get values required in index computation
	public String computeIndexNormally1(String indexID, String settlement,
			String close, String date, boolean updateIndexcompose,
			Connection connection) {
		Logging.debug("Computing Index for Index Id : " + indexID
				+ "  with settlement value = " + settlement);
		CFormula cFor = ConnectInit.getCFormula();
		String time = getTime();
		// AdjustDecimal ad=new AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		long l_indexID = Long.parseLong(indexID);
		double high, low;
		String date1 = "";
		boolean firstDailyValue = false;
		long id, baseCurrencyIdForCurrencyTypeIndex = 66;
		long childIndexId = 0;
		tmcv = 0.0;
		v.clear();
		Logging.debug("initial tmcv for  Id : " + indexID + " is : " + tmcv);

		// checking of Divisor for first day for Average marketcap
		try {
			// doing normal calculation
			Hashtable currWiseExcRate = getCsExcForScripCompose(connection,
					type_of_index, l_indexID);

			pst4 = connection.prepareStatement(ConnectInit.queries
					.getProperty("select_daily_divisor"));
			pst4.setLong(1, l_indexID);// set ? for index id

			rst1 = pst4.executeQuery();// execute query

			if (!rst1.next())// (rst1.getRow() == 0)//check if rst1 dosent retun
								// any thing get the avreage market acp and tmcv
			{
				try {
					pst = connection
							.prepareStatement(ConnectInit.queries
									.getProperty("get_mvc_from_indexcomposition_directaly"));
					pst.setLong(1, l_indexID);
					rst3 = pst.executeQuery();

					while (rst3.next()) {
						mcv1 = rst3.getDouble(1);
						v.addElement((new Double(mcv1)));
					}
					tmcv = cFor.totalMarketCap(v);// calculate tmcv

					pst5 = connection.prepareStatement(ConnectInit.queries
							.getProperty("get_base_value_from_indexmaster"));
					pst5.setLong(1, l_indexID);
					rst4 = pst5.executeQuery();
					while (rst4.next()) {
						base_value = rst4.getDouble(1);
					}
				} catch (Exception e) {
					Logging.debug(e.getMessage());
					Logging.debug("index values" + e);
				}

				divisor = cFor.divisor(tmcv, base_value);
				Logging.debug("divisor value computed " + divisor);

			} else {
				divisor = rst1.getDouble(1);

				// doing normal calculation
				if (!firstDailyValue) {
					pst_preStat = connection
							.prepareStatement(ConnectInit.queries
									.getProperty("compute_index"));
					pst_preStat.setLong(1, l_indexID);
					pst_preStat.setString(2, date);
					rst = pst_preStat.executeQuery();// executes query to get
														// data
					// for
					// index computation
					// get all ltp values and stock id for the given date
					while (rst.next()) {
						// Logging.debug("Row no." + rst.getRow());
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
						// added by sunil 12-JUN-2006.
						String exch_rate = "0.0";
						if (currWiseExcRate.containsKey(new Long(curridStk)
								.toString())) {
							exch_rate = (String) currWiseExcRate.get(new Long(
									curridStk).toString());
						}

						exch_rate = ad.indexcompose4digit(exch_rate);
						exch = (double) Double.parseDouble(exch_rate);
						Logging.debug("Finally Exchange rate is : " + exch);
						Logging.debug(" " + tis);
						base_value = rst.getDouble("base_value");// get base
																	// value
						// for
						// index
						// Logging.debug("Check Value befor function "+check_history);
						if (type_of_index == 1) {
							if (check_history.equals("yes")) {
								// mcv = cFor.calMarketCap(ltp, ml, exch, tis,
								// iwf);//calculates

								// Logging.debug("stock id  "+stkid);
								// Logging.debug("date :  "+date);
								pst6 = connection
										.prepareStatement(ConnectInit.queries
												.getProperty("select_mcv_from_stock_master"));

								pst6.setLong(1, stkid);
								pst6.setString(2, date);
								rs5 = pst6.executeQuery();
								while (rs5.next()) {
									mcv = Double.parseDouble(rs5.getString(1));
								}
								mcv = mcv * exch * iwf;
								// Logging.debug("Check Value After NO function "+check_history+mcv);
							} else {
								mcv = cFor
										.calMarketCap(ltp, ml, exch, tis, 1.0);// calculates
							}

						} else {
							if (check_history.equals("yes")) {
								// mcv = cFor.calMarketCap(ltp, ml, exch, tis,
								// iwf);//calculates

								// Logging.debug("stock id  "+stkid);
								// Logging.debug("date :  "+date);
								pst6 = connection
										.prepareStatement(ConnectInit.queries
												.getProperty("select_mcv_from_stock_master"));

								pst6.setLong(1, stkid);
								pst6.setString(2, date);
								rs5 = pst6.executeQuery();
								while (rs5.next()) {
									mcv = Double.parseDouble(rs5.getString(1));
								}
								mcv = mcv * exch * iwf;
								// Logging.debug("Check Value After NO function "+check_history+mcv);
							} else {
								mcv = cFor
										.calMarketCap(ltp, ml, exch, tis, iwf);// calculates
							}
						}

						v.addElement((new Double(mcv)));// Change made
														// here******
						// update mcv in index composition table
						icomp_mcv = mcv;
						try {
							if (updateIndexcompose) {
								// put mcv into database

								Logging.debug("mcv " + icomp_mcv);
								Logging.debug("index id " + l_indexID);
								Logging.debug("stock id " + stkid);
								pst3 = connection
										.prepareStatement(ConnectInit.queries
												.getProperty("update_index_compose_mcv"));

								Logging.debug("icom mcv " + icomp_mcv);
								pst3.setDouble(1, icomp_mcv);
								pst3.setLong(2, l_indexID);

								pst3.setDouble(3, stkid);

								pst3.executeUpdate();// execute query

							}
						} catch (SQLException e) {
							Logging.debug(e.getMessage());
							Logging.debug("index values" + e);
						}
					}
					// check_history="no";
					tmcv = cFor.totalMarketCap(v);// calculate tmcv

				}
			}

			if (tmcv == 0) {
				return "----";
			}
			indexVal = cFor.index(tmcv, divisor);// index compute
			Logging.debug("value calculated = " + indexVal);

			try {

				pst_preStat = connection.prepareStatement(ConnectInit.queries
						.getProperty("insert_into_intra_day_indices"));

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
					Vector avgPe_pb_dividend = getAvgPe_Pb_dividend(indexID,
							date);

					fiftytwo_min_max = getFiftytwo_Week_HighLow(indexID);
					if (indexVal > fiftytwo_min_max[0]) {
						fiftytwo_min_max[0] = indexVal;
					}
					if (indexVal < fiftytwo_min_max[1]) {
						fiftytwo_min_max[1] = indexVal;
					}
					// code for 52 week low and high to
					pst_preStat = connection
							.prepareStatement(ConnectInit.queries
									.getProperty("index_compute_insert_into_index_value_daily_pe_pb"));

					pst_preStat.setDouble(1, indexVal);
					pst_preStat.setDouble(2, indexVal);
					pst_preStat.setDouble(3, indexVal);
					pst_preStat.setDouble(4, indexVal);
					if (type_of_index == 5) {
						pst_preStat.setLong(5, childIndexId);

					} else {
						pst_preStat.setLong(5, l_indexID);

					}
					pst_preStat.setString(6, date);
					pst_preStat.setDouble(7, divisor);
					// pst_preStat.setString(11, settlement);
					pst_preStat.setDouble(8, tmcv);
					pst_preStat.setDouble(9, tmcv);
					pst_preStat.setDouble(10, divisor);
					if (avgPe_pb_dividend.size() == 3) {
						pst_preStat.setString(11,
								((String) avgPe_pb_dividend.get(0)));
						pst_preStat.setString(12,
								((String) avgPe_pb_dividend.get(1)));
						pst_preStat.setString(13,
								((String) avgPe_pb_dividend.get(2)));
					} else {
						pst_preStat.setString(11, "0.0");
						pst_preStat.setString(12, "0.0");
						pst_preStat.setString(13, "0.0");
					}
					pst_preStat.setDouble(14, fiftytwo_min_max[0]);
					pst_preStat.setDouble(15, fiftytwo_min_max[1]);
					pst_preStat.executeUpdate();// execute query
					Logging.debug("initially insert into  index value daily : "
							+ pst_preStat + "\nand closing value is " + close);

					insertclosing_settlement(indexVal, null, settlement, close,
							date, l_indexID, connection);

				} catch (SQLException e) {
					Logging.error("ERROR");
					Logging.error("index values" + e);
				}
			} else {
				Logging.debug("privious settlement value is settlement = "
						+ rst2.getString("is_settlement_value"));

				if (rst2.getString("is_settlement_value") == null) {
					insertclosing_settlement(indexVal, null, settlement, close,
							date, l_indexID, connection);
				} else {
					insertclosing_settlement(indexVal,
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
			Logging.error(" Error : " + e.getMessage());
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
		if (tmcv == 0) {
			return "----";
		}
		return str;
	}

	// query to get values required in index computation
	public String computeIndexNormally(String indexID, String settlement,
			String close, String date, boolean updateIndexcompose,
			Connection connection) {
		Logging.debug("Computing Index for Index Id : " + indexID
				+ "  with settlement value = " + settlement);
		CFormula cFor = ConnectInit.getCFormula();
		String time = getTime();
		// AdjustDecimal ad=new AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		long l_indexID = Long.parseLong(indexID);
		double high, low;
		String date1 = "";
		boolean firstDailyValue = false;
		long id, baseCurrencyIdForCurrencyTypeIndex = 66;
		long childIndexId = 0;
		tmcv = 0.0;
		v.clear();
		Logging.debug("initial tmcv for  Id : " + indexID + " is : " + tmcv);
		try {
			// doing normal calculation
			Hashtable currWiseExcRate = getCsExcForScripCompose(connection,
					type_of_index, l_indexID);
			// doing normal calculation
			if (!firstDailyValue) {
				pst_preStat = connection.prepareStatement(ConnectInit.queries
						.getProperty("compute_index"));
				pst_preStat.setLong(1, l_indexID);
				pst_preStat.setString(2, date);
				rst = pst_preStat.executeQuery();// executes query to get data
				// for
				// index computation
				// get all ltp values and stock id for the given date
				while (rst.next()) {
					// Logging.debug("Row no." + rst.getRow());
					ltp = rst.getDouble("ltp");// get ltp
					// ltp
					// =(double)Double.parseDouble(ad.twodigitdeci(rst.getDouble("ltp")));//get
					// ltp
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
					// added by sunil 12-JUN-2006.
					String exch_rate = "0.0";
					if (currWiseExcRate.containsKey(new Long(curridStk)
							.toString())) {
						exch_rate = (String) currWiseExcRate.get(new Long(
								curridStk).toString());
					}
					exch_rate = ad.indexcompose4digit(exch_rate);
					exch = (double) Double.parseDouble(exch_rate);
					Logging.debug("Finally Exchange rate is : " + exch);
					Logging.debug(" " + tis);
					base_value = rst.getDouble("base_value");// get base value
					// for
					// index
					if (type_of_index == 1) {

						if (check_history.equals("yes")) {
							// mcv = cFor.calMarketCap(ltp, ml, exch, tis,
							// iwf);//calculates

							// Logging.debug("stock id  "+stkid);
							// Logging.debug("date :  "+date);
							try {

								pst7 = connection
										.prepareStatement(ConnectInit.queries
												.getProperty("select_mcv_from_stock_master"));

								pst7.setLong(1, stkid);
								pst7.setString(2, date);
								rs6 = pst7.executeQuery();
								while (rs6.next()) {
									mcv = Double.parseDouble(rs6.getString(1));
								}
								mcv = mcv * exch * iwf;

							} catch (Exception e) {

							}

						} else {
							mcv = cFor.calMarketCap(ltp, ml, exch, tis, 1.0);// calculates

						}

					} else {
						// for free float index

						if (check_history.equals("yes")) {
							// mcv = cFor.calMarketCap(ltp, ml, exch, tis,
							// iwf);//calculates

							// Logging.debug("stock id  "+stkid);
							// Logging.debug("date :  "+date);
							try {

								pst7 = connection
										.prepareStatement(ConnectInit.queries
												.getProperty("select_mcv_from_stock_master"));

								pst7.setLong(1, stkid);
								pst7.setString(2, date);
								rs6 = pst7.executeQuery();
								while (rs6.next()) {
									mcv = Double.parseDouble(rs6.getString(1));
								}
								mcv = mcv * exch * iwf;
							} catch (Exception e) {

							}

						} else {
							mcv = cFor.calMarketCap(ltp, ml, exch, tis, iwf);// calculates

						}

					}

					v.addElement((new Double(mcv)));// Change made here******

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

							Logging.debug("icom mcv " + icomp_mcv);
							pst3.setDouble(1, icomp_mcv);
							pst3.setLong(2, l_indexID);

							pst3.setDouble(3, stkid);

							int updatedRecordCount = pst3.executeUpdate();// execute
																			// query

						}
					} catch (SQLException e) {
						Logging.debug(e.getMessage());
						Logging.debug("index values" + e);
					}

					// code !! to log mcv for index composition
					try {
						// Create file
						// FileWriter fstream = new FileWriter(
						// "F:/ritz/StockpileFiles/logme.txt");
						// path is modified by samiksha
						FileWriter fstream = new FileWriter(
								"D:/SAMIKSHA/ANDROID PROJECTS/Stockpile/logme.txt");

						BufferedWriter out = new BufferedWriter(fstream);
						out.append(date + "," + stkid + "," + mcv);
						// Close the output stream
						out.close();
					} catch (Exception e) {// Catch exception if any
						System.err.println("Error: " + e.getMessage());
					}

					// code !! ends

				}

				tmcv = cFor.totalMarketCap(v);// calculate tmcv

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
						adjusted_divisor = divisor;
						Logging.debug("divisor value computed " + divisor);
						last_tmcv = tmcv;
						last_date = date;

					} else {
						divisor = rst1.getDouble(1);
						// adjusted_divisor=divisor;
						// logic for changing divisor
						if (CorporateActionExistsForThisDate(date)) {
							// CFormula formula = new CFormula();
							// double diffInTMCV = 0.0, newdivisor = 0.0;
							double index_value_prev_day = indexVal;
							// In progress
							adjusted_divisor = getAdjustedDivisor(date,
									last_date, l_indexID, index_value_prev_day);

						}
						last_tmcv = tmcv;
						last_date = date;
						Logging.debug(" Divisor" + divisor);
						Logging.debug("divisor taken " + divisor);
					}
				} catch (SQLException e) {
					Logging.debug(e.getMessage());
					Logging.debug("index values" + e);
				}
				indexVal = cFor.index(tmcv, divisor);// index compute
				Logging.debug("value calculated = " + indexVal);

			}
			// normal calculation ended

			// inserts value in intra day indices
			try {
				pst_preStat = connection.prepareStatement(ConnectInit.queries
						.getProperty("insert_into_intra_day_indices"));

				if (type_of_index == 4) {
					pst_preStat.setLong(4, childIndexId);

				} else {
					pst_preStat.setLong(4, l_indexID);

				}
				pst_preStat.setDouble(1, indexVal);
				pst_preStat.setDouble(5, tmcv);

				pst_preStat.setString(2, date);

				pst_preStat.setString(3, time);

			int recordInserted=	pst_preStat.executeUpdate();// execute query
System.out.println("Intra day indices count **** "+recordInserted);
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
					Vector avgPe_pb_dividend = getAvgPe_Pb_dividend(indexID,
							date);
					Logging.debug("avgPe_pb_dividend size is "
							+ avgPe_pb_dividend.size());

					// code for 52 week low and high from

					fiftytwo_min_max = getFiftytwo_Week_HighLow(indexID);
					if (indexVal > fiftytwo_min_max[0]) {
						fiftytwo_min_max[0] = indexVal;
					}
					if (indexVal < fiftytwo_min_max[1]) {
						fiftytwo_min_max[1] = indexVal;
					}
					// code for 52 week low and high to
					
//					  pst_preStat = connection
//					.prepareStatement(ConnectInit.queries
//					 .getProperty("insert_into_index_value_daily1"));
//					 
					pst_preStat = connection
							.prepareStatement(ConnectInit.queries
									.getProperty("index_compute_insert_into_index_value_daily_pe_pb"));
					// pst_preStat.setLong(1, id);

					// pst_preStat.setLong(6, l_indexID);
					//
					pst_preStat.setDouble(1, indexVal);
					pst_preStat.setDouble(2, indexVal);
					pst_preStat.setDouble(3, indexVal);
					pst_preStat.setDouble(4, indexVal);
					if (type_of_index == 5) {
						pst_preStat.setLong(5, childIndexId);

					} else {
						pst_preStat.setLong(5, l_indexID);

					}
					pst_preStat.setString(6, date);
					pst_preStat.setDouble(7, divisor);
					// pst_preStat.setString(11, settlement);
					pst_preStat.setDouble(8, tmcv);
					pst_preStat.setDouble(9, tmcv);
					pst_preStat.setDouble(10, adjusted_divisor);
					if (avgPe_pb_dividend.size() == 3) {
						pst_preStat.setString(11,
								((String) avgPe_pb_dividend.get(0)));
						pst_preStat.setString(12,
								((String) avgPe_pb_dividend.get(1)));
						pst_preStat.setString(13,
								((String) avgPe_pb_dividend.get(2)));
					} else {
						pst_preStat.setString(11, "0.0");
						pst_preStat.setString(12, "0.0");
						pst_preStat.setString(13, "0.0");
					}
					pst_preStat.setDouble(14, fiftytwo_min_max[0]);
					pst_preStat.setDouble(15, fiftytwo_min_max[1]);
				int count=	pst_preStat.executeUpdate();// execute query
					Logging.debug("initially insert into  index value daily : "
							+ pst_preStat + "\nand closing value is " + close);

					insertclosing_settlement(indexVal, null, settlement, close,
							date, l_indexID, connection);

				} catch (SQLException e) {
					Logging.error("ERROR");
					Logging.error("index values" + e);
				}
			} else {
				Logging.debug("privious settlement value is settlement = "
						+ rst2.getString("is_settlement_value"));
				if (rst2.getString("is_settlement_value") == null) {
					insertclosing_settlement(indexVal, null, settlement, close,
							date, l_indexID, connection);
				} else {
					insertclosing_settlement(indexVal,
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
			Logging.error(" Error : " + e.getMessage());
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
		if (tmcv == 0) {
			return "----";
		}
		return str;
	}

	private double getAdjustedDivisor(String current_date, String last_date,
			long index_id, double prevIndexValue) {
		double TotalMCV = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rst = null;
		double adjusted_divisor = 0;
		try {
			connection = ConnectInit.getConnect().getdbConnection();
			statement = connection
					.prepareStatement(ConnectInit.queries
							.getProperty("get_indexWiseIwf_closingValue_divisorAdjustemnt"));
			statement.setLong(2, index_id);
			statement.setString(1, last_date);
			Logging.debug(statement.toString());
			rst = statement.executeQuery();
			while (rst.next()) {
				String stockId = rst.getString("stock_id");
				String iwf = rst.getString("iwf");
				String stockClosingValue = rst.getString("stock_closing_value");
				double tis = getTisForAdjustedDivisor(stockId, current_date);
				double mcv = tis * Double.parseDouble(iwf)
						* Double.parseDouble(stockClosingValue);
				TotalMCV = TotalMCV + mcv;
			}
			adjusted_divisor = TotalMCV / prevIndexValue;
			connection.close();
			statement.close();
			rst.close();
		} catch (Exception e) {
			Logging.debug("CorporateActionExistsForThisDate :" + e);
		} finally {
			try {
				if (connection != null && statement != null && rst != null) {
					connection.close();
					statement.close();
					rst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return adjusted_divisor;
	}

	private double getTisForAdjustedDivisor(String stockId, String current_date) {
		double TIS = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rst = null;
		try {
			connection = ConnectInit.getConnect().getdbConnection();
			statement = connection.prepareStatement(ConnectInit.queries
					.getProperty("fn_getTisForAdjustedDivisor"));
			statement.setString(1, current_date);
			statement.setString(2, stockId);
			Logging.debug(statement.toString());
			rst = statement.executeQuery();
			while (rst.next()) {
				if (!rst.getString(1).toString().isEmpty()) {
					TIS = Double.parseDouble(rst.getString(1).toString());
				}
			}
			connection.close();
			statement.close();
			rst.close();
			return TIS;
		} catch (Exception e) {
			return TIS;
		} finally {
			try {
				if (connection != null && statement != null && rst != null) {
					connection.close();
					statement.close();
					rst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean CorporateActionExistsForThisDate(String date) {
		try {
			int CA_FOR_DATE = 0;
			Connection connection = ConnectInit.getConnect().getdbConnection();
			PreparedStatement statementToCheckCAExist = connection
					.prepareStatement(ConnectInit.queries
							.getProperty("check_ca_for_date"));
			statementToCheckCAExist.setString(1, date);
			Logging.debug(statementToCheckCAExist.toString());
			ResultSet rst = statementToCheckCAExist.executeQuery();
			while (rst.next()) {
				CA_FOR_DATE = Integer.parseInt(rst.getString(1).toString());
				if (CA_FOR_DATE == 0) {
					return false;
				} else {
					return true;
				}
			}
		} catch (Exception e) {
			Logging.debug("CorporateActionExistsForThisDate :" + e);
			return false;
		}
		return false;
	}

	// query to get values required in index computation
	public String computeIndexNormallyBeforeBaseDate(String indexID,
			String settlement, String close, String date,
			boolean updateIndexcompose, Connection connection) {
		Logging.debug("Computing Index for Index Id : " + indexID
				+ "  with settlement value = " + settlement + " date is "
				+ date);
		CFormula cFor = ConnectInit.getCFormula();
		String time = getTime();
		// AdjustDecimal ad=new AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		long l_indexID = Long.parseLong(indexID);
		double high, low;
		String date1 = "";
		boolean firstDailyValue = false;
		long id, baseCurrencyIdForCurrencyTypeIndex = 66;
		long childIndexId = 0;
		tmcv = 0.0;
		v.clear();
		Logging.debug("initial tmcv for  Id : " + indexID + " is : " + tmcv);
		try {
			// doing normal calculation
			if (!firstDailyValue) {
				pst_preStat = connection.prepareStatement(ConnectInit.queries
						.getProperty("compute_index"));
				pst_preStat.setLong(1, l_indexID);
				// date = QueryClass.formatDate();
				pst_preStat.setString(2, date);
				// pst_preStat.setString(1, date);
				Logging.debug(pst_preStat.toString());
				rst = pst_preStat.executeQuery();// executes query to get data
				// for
				// index computation
				// get all ltp values and stock id for the given date
				while (rst.next()) {
					// Logging.debug("Row no." + rst.getRow());
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
						rst2 = getExchCode(connection);
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
						Logging.debug(e);
						exch = 1.0;
						// TODO: handle exception
					}
					String exch_rate = new Double(exch).toString();
					exch_rate = ad.indexcompose4digit(exch_rate);
					exch = (double) Double.parseDouble(exch_rate);
					Logging.debug("Finally Exchange rate is : " + exch);
					Logging.debug(" " + tis);
					base_value = rst.getDouble("base_value");// get base value
					// for
					// index
					mcv = cFor.calMarketCap(ltp, ml, exch, tis, iwf);// calculates
					Logging.debug("mcv is " + mcv);
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
				tmcv = cFor.totalMarketCap(v);// calculate tmcv
				Logging.debug("initial2 tmcv for  Id : " + indexID + " is : "
						+ tmcv + "v size is : " + v.size());
				Logging.debug("Total Market Cap " + tmcv);
				if (tmcv == 0) {
					return "----";
				}
				divisor = getDivisorValue(indexID);
				indexVal = cFor.index(tmcv, divisor);// index compute
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
					Vector avgPe_pb_dividend = getAvgPe_Pb_dividend(indexID,
							date);
					Logging.debug("avgPe_pb_dividend size is "
							+ avgPe_pb_dividend.size());

					// code for 52 week low and high from

					fiftytwo_min_max = getFiftytwo_Week_HighLow(indexID);
					if (indexVal > fiftytwo_min_max[0]) {
						fiftytwo_min_max[0] = indexVal;
					}
					if (indexVal < fiftytwo_min_max[1]) {
						fiftytwo_min_max[1] = indexVal;
					}
					// code for 52 week low and high to
					/*
					 * pst_preStat = connection
					 * .prepareStatement(ConnectInit.queries
					 * .getProperty("insert_into_index_value_daily1"));
					 */
					pst_preStat = connection
							.prepareStatement(ConnectInit.queries
									.getProperty("index_compute_insert_into_index_value_daily_pe_pb"));
					if (type_of_index == 5) {
						pst_preStat.setLong(5, childIndexId);

					} else {
						pst_preStat.setLong(5, l_indexID);

					}
					// pst_preStat.setLong(6, l_indexID);
					pst_preStat.setDouble(9, tmcv);
					pst_preStat.setDouble(1, indexVal);
					pst_preStat.setDouble(2, indexVal);
					pst_preStat.setDouble(3, indexVal);
					pst_preStat.setDouble(4, indexVal);

					// pst_preStat.setDouble(5, indexVal);
					pst_preStat.setString(6, date);
					pst_preStat.setDouble(7, divisor);
					// pst_preStat.setString(11, settlement);
					pst_preStat.setDouble(8, tmcv);
					pst_preStat.setDouble(10, divisor);
					if (avgPe_pb_dividend.size() == 3) {
						pst_preStat.setString(11,
								((String) avgPe_pb_dividend.get(0)));
						pst_preStat.setString(12,
								((String) avgPe_pb_dividend.get(1)));
						pst_preStat.setString(13,
								((String) avgPe_pb_dividend.get(2)));
					} else {
						pst_preStat.setString(11, "0.0");
						pst_preStat.setString(12, "0.0");
						pst_preStat.setString(13, "0.0");
					}
					pst_preStat.setDouble(14, fiftytwo_min_max[0]);
					pst_preStat.setDouble(15, fiftytwo_min_max[1]);
					Logging.debug("initially insert into  index value daily : "
							+ pst_preStat + "\nand closing value is " + close);
					pst_preStat.executeUpdate();// execute query

					insertclosing_settlement(indexVal, null, settlement, close,
							date, l_indexID, connection);
				} catch (SQLException e) {
					Logging.error("ERROR");
					Logging.error("index values" + e);
				}
			} else {
				Logging.debug("privious settlement value is settlement = "
						+ rst2.getString("is_settlement_value"));
				if (rst2.getString("is_settlement_value") == null) {
					insertclosing_settlement(indexVal, null, settlement, close,
							date, l_indexID, connection);
				} else {
					insertclosing_settlement(indexVal,
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
		if (tmcv == 0) {
			return "----";
		}
		return str;
	}

	public void insertclosing_settlement(double indexVal,
			String is_settlement_val, String settlement, String close,
			String date, long l_indexID, Connection connection) {
		try {
			// Logging.getDebug("privious settlement value is settlement = "
			// + rst2.getString("is_settlement_value")+" and settlement variable
			// : "+settlement);

			if (is_settlement_val == null
					|| is_settlement_val.trim().equals("")) {

				if (settlement.trim().equals("y")) {
					pst_preStat = connection
							.prepareStatement(ConnectInit.queries
									.getProperty("update_settlement_value"));
					pst_preStat.setDouble(1, indexVal);

					pst_preStat.setLong(2, l_indexID);

					// pst_preStat.setLong(2, l_indexID);
					pst_preStat.setString(3, date);
					pst_preStat.executeUpdate();
				}
			}
			if (close != null && close.equals("y")) {
				pst_preStat = connection.prepareStatement(ConnectInit.queries
						.getProperty("update_close_value"));
				pst_preStat.setDouble(1, indexVal);

				pst_preStat.setLong(2, l_indexID);

				// pst_preStat.setLong(2, l_indexID);

				pst_preStat.setString(3, date);
				Logging.debug("Updating closing value :" + pst_preStat);
				pst_preStat.executeUpdate();
			}

		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
			Logging.error("Error : " + e.getMessage());
		}
	}

	// query to get values required in index computation
	public String computeCurrencyIndex(String indexID, String settlement,
			String close, String basedate, Connection connection) {
		Logging.debug("Computing Currency Index for Index Id : " + indexID
				+ "  with settlement value = " + settlement);
		String time = getTime();
		// AdjustDecimal ad=new AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		long l_indexID = Long.parseLong(indexID);
		double high, low;
		String date = "";
		long id, ParentBaseCurrency = 66, childBaseCurrency = 66;
		long childIndexId = 0;
		tmcv = 0.0;
		double exchangerate = 1;
		String multiplierstring;
		PreparedStatement pst_multiplier;
		v.clear();
		try {

			ResultSet resultSetToFindindexDetails = new QueryClass1()
					.ReturnParentDetails(indexID, connection);
			// Logging.getDebug("aa " + indexID + " is : " + tmcv);
			resultSetToFindindexDetails.next();
			// Logging.getDebug("bb " + indexID + " is : "
			// + resultSetToFindindexDetails.first());

			// child details
			// Logging.getDebug("bbb " + indexID + " is : " + tmcv);
			String parentIndexId = resultSetToFindindexDetails
					.getString("parent_id");
			// Logging.getDebug("bbbb " + indexID + " is : " + tmcv);
			l_indexID = resultSetToFindindexDetails.getLong("parent_id");
			childBaseCurrency = resultSetToFindindexDetails
					.getLong("base_currency_id");
			childIndexId = resultSetToFindindexDetails.getLong("index_id");
			// Logging.getDebug("cc " + indexID + " is : " + tmcv);

			resultSetToFindindexDetails = new QueryClass1()
					.ReturnParentDetails(parentIndexId, connection);
			resultSetToFindindexDetails.next();
			// parent details

			Logging.debug("dd " + indexID + " is : " + tmcv);
			ParentBaseCurrency = resultSetToFindindexDetails
					.getLong("base_currency_id");

			// exchange rate

			exchangerate = ExchangeRateForCurrencyIndex(
					(int) childBaseCurrency, (int) ParentBaseCurrency,
					basedate, connection);
			if (exchangerate == -999) {
				return "---";
			}
			Logging.debug("For currency index the exchange rate is "
					+ exchangerate);

			// get tmcv and divisor for parent
			pst_multiplier = connection.prepareStatement(ConnectInit.queries
					.getProperty("compute_currency_index_tmcv_divisor"));
			pst_multiplier.setString(1, basedate);
			pst_multiplier.setString(2, parentIndexId);
			rst = pst_multiplier.executeQuery();
			Logging.debug("first query to find tmcv and divisor = "
					+ pst_multiplier);
			if (rst.next()) {
				tmcv = rst.getDouble("tmcv");
				divisor = rst.getDouble("divisor");
			} else {
				pst_multiplier = connection
						.prepareStatement(ConnectInit.queries
								.getProperty("compute_currency_index_tmcv_divisor1"));

				pst_multiplier.setString(1, parentIndexId);
				pst_multiplier.setString(2, parentIndexId);
				pst_multiplier.setString(3, basedate);

				rst = pst_multiplier.executeQuery();
				Logging.debug("Second query to find tmcv and divisor = "
						+ pst_multiplier);
				if (rst.next()) {
					tmcv = rst.getDouble("tmcv");
					divisor = rst.getDouble("divisor");
				} else {
					return "---";
				}
			}

			if (tmcv == 0) {
				return "----";
			}
			// index compute
			String exch_rate = new Double(exchangerate).toString();
			exch_rate = ad.indexcompose4digit(exch_rate);
			exchangerate = (double) Double.parseDouble(exch_rate);

			indexVal = (tmcv / divisor) * (exchangerate);

			// add code here to compute currency index

			Logging.debug("value calculated = " + indexVal);

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

				// pst_preStat.setLong(1, id);
				// if (type_of_index == 5) {
				pst_preStat.setLong(4, childIndexId);

				// }
				pst_preStat.setDouble(1, indexVal);
				Logging.debug("tmcv before exchange rate multiplication "
						+ tmcv);
				tmcv = (tmcv) * (exchangerate);
				Logging.debug("tmcv after exchange rate multiplication " + tmcv);
				pst_preStat.setDouble(5, tmcv);
				// date = QueryClass.formatDate();
				date = basedate;
				pst_preStat.setString(2, date);

				pst_preStat.setString(3, time);

				pst_preStat.executeUpdate();// execute query

			} catch (SQLException e) {
				Logging.error("ERROR");
				Logging.error("index values" + e.getMessage());
			}

			// select index_lowest_value,index_highest_value from index value
			// daily

			try {
				pst_preStat = connection.prepareStatement(ConnectInit.queries
						.getProperty("get_high_low_index_value"));
				// if (type_of_index == 5) {
				pst_preStat.setLong(1, childIndexId);
				// }

				pst_preStat.setString(2, date);
				rst2 = pst_preStat.executeQuery();// execute query
			} catch (Exception e) {
				Logging.error("ERROR");
				Logging.error("index values" + e.getMessage());
			}
			// if no enteries
			rst2.next();

			if (rst2.getRow() == 0) {
				try {

					/*
					 * try { stm = connection.createStatement(); // remove query
					 * rst = stm
					 * .executeQuery("select nextval('index_value_daily_id')");
					 * Logging.getDebug(rst); rst.next(); } catch (SQLException
					 * e) { Logging.getDebug("error in Query"); e.getMessage();
					 * } id = rst.getLong(1); Logging.getDebug(settlement);
					 * System.out
					 * .println("before insert query in index value daily");
					 */

					// code for 52 week low and high from

					fiftytwo_min_max = getFiftytwo_Week_HighLow(indexID);
					if (indexVal > fiftytwo_min_max[0]) {
						fiftytwo_min_max[0] = indexVal;
					}
					if (indexVal < fiftytwo_min_max[1]) {
						fiftytwo_min_max[1] = indexVal;
					}
					// code for 52 week low and high to
					pst_preStat = connection
							.prepareStatement(ConnectInit.queries
									.getProperty("insert_into_index_value_daily1"));
					// pst_preStat.setLong(1, id);

					pst_preStat.setLong(4, childIndexId);

					// pst_preStat.setLong(6, l_indexID);
					pst_preStat.setDouble(8, tmcv);
					pst_preStat.setDouble(1, indexVal);
					pst_preStat.setDouble(2, indexVal);
					pst_preStat.setDouble(3, indexVal);
					// pst_preStat.setDouble(5, indexVal);
					pst_preStat.setString(5, date);
					pst_preStat.setDouble(6, divisor);
					// pst_preStat.setString(8, settlement);
					pst_preStat.setDouble(7, tmcv);
					pst_preStat.setDouble(9, divisor);
					pst_preStat.setDouble(10, fiftytwo_min_max[0]);
					pst_preStat.setDouble(11, fiftytwo_min_max[1]);

					Logging.debug("Inser query of index value daily : "
							+ pst_preStat);
					pst_preStat.executeUpdate();// execute query
					insertclosing_settlement(indexVal, null, settlement, close,
							date, childIndexId, connection);

				} catch (SQLException e) {
					Logging.error("ERROR");
					Logging.error("index values" + e.getMessage());
				}
			} else {
				Logging.debug("privious settlement value is settlement = "
						+ rst2.getString("is_settlement_value"));
				if (rst2.getString("is_settlement_value") != null) {
					insertclosing_settlement(indexVal,
							rst2.getString("is_settlement_value"), settlement,
							close, date, childIndexId, connection);
				} else {
					insertclosing_settlement(indexVal, null, settlement, close,
							date, childIndexId, connection);

				}

				/*
				 * if (rst2.getString("is_settlement_value").trim().equals("n"))
				 * {
				 * 
				 * pst_preStat = connection
				 * .prepareStatement(ConnectInit.queries
				 * .getProperty("update_settlement_value"));
				 * pst_preStat.setString(1, settlement); if (type_of_index == 5)
				 * { pst_preStat.setLong(2, childIndexId); } //
				 * pst_preStat.setLong(2, l_indexID); pst_preStat.setString(3,
				 * date); pst_preStat.executeUpdate(); }
				 */
				low = rst2.getDouble("index_lowest_value");
				high = rst2.getDouble("index_highest_value");
				if (indexVal > high) {
					// update_high_index_value

					pst_preStat = connection
							.prepareStatement(ConnectInit.queries
									.getProperty("update_high_index_value"));
					pst_preStat.setDouble(1, indexVal);
					// if (type_of_index == 5) {
					pst_preStat.setLong(2, childIndexId);

					// }
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
					// if (type_of_index == 5) {
					pst_preStat.setLong(2, childIndexId);

					// }
					// pst_preStat.setLong(2, l_indexID);
					pst_preStat.setString(3, date);
					pst_preStat.executeUpdate();
				}
			}

		} catch (SQLException e) {
			Logging.debug(e + "");
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

				}
				// pst_preStat.setLong(3, l_indexID);
				pst_preStat.setString(4, date);
				pst_preStat.executeUpdate();

			} catch (SQLException e) {
				Logging.error("Error : " + e.getMessage());
			}
		}
		String str = Double.toString(indexVal);
		Logging.debug("Index Value Calculated for Index id : " + indexID
				+ "  is : " + str);
		if (tmcv == 0) {
			return "----";
		}
		return str;
	}

	/**
	 * compute currency index before base date.take divisor value at base date
	 * and prices on respective dates and start computing index values from
	 * (basedate-1) until historic date decrementing date by one.
	 * 
	 * @param indexID
	 * @param settlement
	 * @param close
	 * @param basedate
	 * @param connection
	 * @return
	 */
	public String computeCurrencyIndexBeforeBaseDate(String indexID,
			String settlement, String close, String basedate,
			Connection connection) {
		Logging.debug("Computing Currency Index for Index Id : " + indexID
				+ "  with settlement value = " + settlement);
		String time = getTime();
		// AdjustDecimal ad=new AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		long l_indexID = Long.parseLong(indexID);
		double high, low;
		String date = "";
		long id, ParentBaseCurrency = 66, childBaseCurrency = 66;
		long childIndexId = 0;
		tmcv = 0.0;
		double exchangerate = 1;
		String multiplierstring;
		PreparedStatement pst_multiplier;
		v.clear();
		try {

			ResultSet resultSetToFindindexDetails = new QueryClass1()
					.ReturnParentDetails(indexID, connection);
			// Logging.getDebug("aa " + indexID + " is : " + tmcv);
			resultSetToFindindexDetails.next();
			// Logging.getDebug("bb " + indexID + " is : "
			// + resultSetToFindindexDetails.first());

			// child details
			// Logging.getDebug("bbb " + indexID + " is : " + tmcv);
			String parentIndexId = resultSetToFindindexDetails
					.getString("parent_id");
			// Logging.getDebug("bbbb " + indexID + " is : " + tmcv);
			l_indexID = resultSetToFindindexDetails.getLong("parent_id");
			childBaseCurrency = resultSetToFindindexDetails
					.getLong("base_currency_id");
			childIndexId = resultSetToFindindexDetails.getLong("index_id");
			// Logging.getDebug("cc " + indexID + " is : " + tmcv);

			resultSetToFindindexDetails = new QueryClass1()
					.ReturnParentDetails(parentIndexId, connection);
			resultSetToFindindexDetails.next();
			// parent details

			Logging.debug("dd " + indexID + " is : " + tmcv);
			ParentBaseCurrency = resultSetToFindindexDetails
					.getLong("base_currency_id");

			// exchange rate

			exchangerate = ExchangeRateForCurrencyIndex(
					(int) childBaseCurrency, (int) ParentBaseCurrency,
					basedate, connection);
			if (exchangerate == -999) {
				return "---";
			}
			Logging.debug("For currency index the exchange rate is "
					+ exchangerate);

			// get tmcv and divisor for parent

			pst_multiplier = connection
					.prepareStatement(ConnectInit.queries
							.getProperty("index_compute_compute_currency_index_tmcv_divisor1"));

			pst_multiplier.setString(1, parentIndexId);
			pst_multiplier.setString(2, parentIndexId);

			rst = pst_multiplier.executeQuery();
			Logging.debug("Second query to find tmcv and divisor = "
					+ pst_multiplier);
			if (rst.next()) {
				tmcv = rst.getDouble("tmcv");
				divisor = rst.getDouble("divisor");
			} else {
				return "---";
			}

			if (tmcv == 0) {
				return "----";
			}
			// index compute
			String exch_rate = new Double(exchangerate).toString();
			exch_rate = ad.indexcompose4digit(exch_rate);
			exchangerate = (double) Double.parseDouble(exch_rate);

			indexVal = (tmcv / divisor) * (exchangerate);

			// add code here to compute currency index

			Logging.debug("value calculated = " + indexVal);

			// inserts value in intra day indices
			try {
				pst_preStat = connection.prepareStatement(ConnectInit.queries
						.getProperty("insert_into_intra_day_indices"));
				pst_preStat.setLong(4, childIndexId);
				pst_preStat.setDouble(1, indexVal);
				Logging.debug("tmcv before exchange rate multiplication "
						+ tmcv);
				tmcv = (tmcv) * (exchangerate);
				Logging.debug("tmcv after exchange rate multiplication " + tmcv);
				pst_preStat.setDouble(5, tmcv);
				// date = QueryClass.formatDate();
				date = basedate;
				pst_preStat.setString(2, date);

				pst_preStat.setString(3, time);

				pst_preStat.executeUpdate();// execute query

			} catch (SQLException e) {
				Logging.error("ERROR");
				Logging.error("index values" + e.getMessage());
			}

			// select index_lowest_value,index_highest_value from index value
			// daily

			try {
				pst_preStat = connection.prepareStatement(ConnectInit.queries
						.getProperty("get_high_low_index_value"));
				// if (type_of_index == 5) {
				pst_preStat.setLong(1, childIndexId);
				// }

				pst_preStat.setString(2, date);
				rst2 = pst_preStat.executeQuery();// execute query
			} catch (Exception e) {
				Logging.error("ERROR");
				Logging.error("index values" + e.getMessage());
			}
			// if no enteries
			rst2.next();

			if (rst2.getRow() == 0) {
				try {

					// code for 52 week low and high from

					fiftytwo_min_max = getFiftytwo_Week_HighLow(indexID);
					if (indexVal > fiftytwo_min_max[0]) {
						fiftytwo_min_max[0] = indexVal;
					}
					if (indexVal < fiftytwo_min_max[1]) {
						fiftytwo_min_max[1] = indexVal;
					}
					// code for 52 week low and high to

					pst_preStat = connection
							.prepareStatement(ConnectInit.queries
									.getProperty("insert_into_index_value_daily1"));
					// pst_preStat.setLong(1, id);

					pst_preStat.setLong(4, childIndexId);

					// pst_preStat.setLong(6, l_indexID);
					pst_preStat.setDouble(8, tmcv);
					pst_preStat.setDouble(1, indexVal);
					pst_preStat.setDouble(2, indexVal);
					pst_preStat.setDouble(3, indexVal);
					// pst_preStat.setDouble(5, indexVal);
					pst_preStat.setString(5, date);
					pst_preStat.setDouble(6, divisor);
					// pst_preStat.setString(8, settlement);
					pst_preStat.setDouble(7, tmcv);
					pst_preStat.setDouble(9, divisor);
					pst_preStat.setDouble(10, fiftytwo_min_max[0]);
					pst_preStat.setDouble(11, fiftytwo_min_max[1]);

					Logging.debug("Inser query of index value daily : "
							+ pst_preStat);
					pst_preStat.executeUpdate();// execute query
					insertclosing_settlement(indexVal, null, settlement, close,
							date, childIndexId, connection);

				} catch (SQLException e) {
					Logging.error("ERROR");
					Logging.error("index values" + e.getMessage());
				}
			} else {
				Logging.debug("privious settlement value is settlement = "
						+ rst2.getString("is_settlement_value"));
				if (rst2.getString("is_settlement_value") != null) {
					insertclosing_settlement(indexVal,
							rst2.getString("is_settlement_value"), settlement,
							close, date, childIndexId, connection);
				} else {
					insertclosing_settlement(indexVal, null, settlement, close,
							date, childIndexId, connection);

				}
				low = rst2.getDouble("index_lowest_value");
				high = rst2.getDouble("index_highest_value");
				if (indexVal > high) {
					// update_high_index_value

					pst_preStat = connection
							.prepareStatement(ConnectInit.queries
									.getProperty("update_high_index_value"));
					pst_preStat.setDouble(1, indexVal);
					// if (type_of_index == 5) {
					pst_preStat.setLong(2, childIndexId);

					// }
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
					// if (type_of_index == 5) {
					pst_preStat.setLong(2, childIndexId);

					// }
					// pst_preStat.setLong(2, l_indexID);
					pst_preStat.setString(3, date);
					pst_preStat.executeUpdate();
				}
			}

		} catch (SQLException e) {
			Logging.debug(e + "");
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

				}
				// pst_preStat.setLong(3, l_indexID);
				pst_preStat.setString(4, date);
				pst_preStat.executeUpdate();

			} catch (SQLException e) {
				Logging.error("Error : " + e.getMessage());
			}
		}
		String str = Double.toString(indexVal);
		Logging.debug("Index Value Calculated for Index id : " + indexID
				+ "  is : " + str);
		if (tmcv == 0) {
			return "----";
		}
		return str;
	}

	public void ComputeChildIndexes(String indexID, String settlement,
			String close, String toComputechildindexes,
			String ListOfChildIndices, String baseDate, boolean temp,
			Connection connection) {
		try {
			Logging.debug("1 : " + toComputechildindexes);
			Logging.debug(connection + "1.1 : " + ListOfChildIndices);

			if (toComputechildindexes != null
					&& toComputechildindexes.trim().equalsIgnoreCase("yes")) {
				Logging.debug(indexID + "2" + connection);
				// write code here to fetch list of child indexes
				try {
					CallableStatement cstmt;
					cstmt = connection
							.prepareCall("{ call public.get_all_child_indices(?) }");
					// write code here to compute child indexes 1 by 1
					cstmt.setString(1, indexID);
					Logging.debug("3 " + cstmt);
					ResultSet rs_cstmt = null;
					try {
						rs_cstmt = cstmt.executeQuery();
					} catch (Exception e) {
						Logging.error("Error : " + e.getMessage());
						// TODO: handle exception
					}
					Logging.debug("4 " + cstmt);
					String childindexid;
					while (rs_cstmt.next()) {
						Logging.debug("5 " + cstmt);
						childindexid = rs_cstmt.getString(1);
						Logging.info(baseDate
								+ "Computing child indices for indexid : "
								+ childindexid);
						ResultSet rsindexdetails = new QueryClass1()
								.ReturnParentDetails(childindexid, connection);
						rsindexdetails.next();
						if (rsindexdetails.getLong("index_type_id") == 4) {
							computeIndexforTotalReturns(childindexid,
									settlement, close, baseDate, connection);
						} else if (rsindexdetails.getLong("index_type_id") == 5) {

							computeCurrencyIndex(childindexid, settlement,
									close, baseDate, connection);

						} else {
							// check here for date
							computeIndexNormally(childindexid, settlement,
									close, baseDate, temp, connection);
						}

					}
					Logging.debug("6 " + cstmt);
				} catch (Exception e) {
					// TODO: handle exception
					// e.printStackTrace();
					Logging.error("Error in creating child indices : "
							+ e.getMessage());
				}
			} else if (!ListOfChildIndices.trim().equals("")) {
				Logging.debug("3");
				// write code here to fetch list of child indexes
				CallableStatement cstmt;
				Logging.info("Before Calling Calling Procedure call public.get_all_child_indices(?) : "
						+ indexID);
				cstmt = connection
						.prepareCall("{ call public.get_all_child_indices(?) }");
				// write code here to compute child indexes 1 by 1
				try {
					cstmt.setString(1, indexID);
					ResultSet rs_cstmt = cstmt.executeQuery();
					String childindexid;
					while (rs_cstmt.next()) {
						childindexid = rs_cstmt.getString(1);
						Logging.info("Calculating  indices for child index : "
								+ childindexid);
						if (ListOfChildIndices.indexOf("1") != -1) {
							ResultSet rsindexdetails = new QueryClass1()
									.ReturnParentDetails(childindexid,
											connection);
							rsindexdetails.next();
							if (rsindexdetails.getLong("index_type_id") == 4) {
								computeIndexforTotalReturns(childindexid,
										settlement, close, baseDate, connection);
							} else if (rsindexdetails.getLong("index_type_id") == 5) {
								computeIndexforTotalReturns(childindexid,
										settlement, close, baseDate, connection);
							} else {
								computeIndexNormally(childindexid, settlement,
										close, baseDate, true, connection);
							}
						} else {
							if (ListOfChildIndices.indexOf("2") != -1) {
								ResultSet rsindexdetails = new QueryClass1()
										.ReturnParentDetails(childindexid,
												connection);
								rsindexdetails.next();
								if (rsindexdetails.getString("index_type_id")
										.trim().equals(indexID)) {
									if (rsindexdetails.getLong("index_type_id") == 4) {
										computeIndexforTotalReturns(
												childindexid, settlement,
												close, baseDate, connection);
									} else {
										computeIndexNormally(childindexid,
												settlement, close, baseDate,
												true, connection);
									}
								}
							}
							if (ListOfChildIndices.indexOf("3") != -1) {
								ResultSet rsindexdetails = new QueryClass1()
										.ReturnParentDetails(childindexid,
												connection);
								rsindexdetails.next();
								if (rsindexdetails.getLong("index_type_id") == 5) {
									computeCurrencyIndex(indexID, settlement,
											close, baseDate, connection);

								}
							}
							if (ListOfChildIndices.indexOf("4") != -1) {
								ResultSet rsindexdetails = new QueryClass1()
										.ReturnParentDetails(childindexid,
												connection);
								rsindexdetails.next();
								if (rsindexdetails.getLong("index_type_id") == 4) {

									computeIndexforTotalReturns(childindexid,
											settlement, close, baseDate,
											connection);

								}
							}
						}

					}
				} catch (Exception e) {
					// TODO: handle exception
					Logging.error("Error in creating child indices : "
							+ e.getMessage());
				}

			}
		} catch (Exception ex) {
			Logging.debug("Error in computing child index " + ex);
		}
	}

	// query to get values required in index computation
	public String computeIndexforTotalReturns(String indexID,
			String settlement, String close, String basedate,
			Connection connection) {
		String time = getTime();
		// AdjustDecimal ad=new AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		long l_indexID = Long.parseLong(indexID);
		PreparedStatement pst_indexDetails, pst_stockDetails, pst_multiplier;
		String indexquery, stockquery;
		double previousClose, todaysClose;
		double todaysdivisor;
		int indexcurrencyid;
		double high, low;
		String date = "";
		boolean flagforcurrency = false;
		ArrayList summation = new ArrayList();
		long id;
		try {
			indexquery = ConnectInit.queries.getProperty("query_totalreturns");
			Logging.debug("indexquery :" + indexquery);
			pst_indexDetails = connection.prepareStatement(indexquery);
			Logging.debug("in computeIndexforTotalReturns 11 : " + l_indexID);
			pst_indexDetails.setString(1, basedate);
			pst_indexDetails.setLong(2, l_indexID);
			pst_indexDetails.setString(3, basedate);
			pst_indexDetails.setLong(4, l_indexID);

			Logging.debug("query 1 in total returns : " + pst_indexDetails);
			rst = pst_indexDetails.executeQuery();
			Logging.debug("query 1 in total returns : " + pst_indexDetails);

			if (rst.next()) {
				Logging.debug("in computeIndexforTotalReturns 2");
				previousClose = rst.getDouble("last_closing");
				todaysClose = rst.getDouble("today");
				todaysdivisor = rst.getDouble("adjusted_divisor");
				indexcurrencyid = rst.getInt("base_currency_id");
				Logging.debug("in computeIndexforTotalReturns 3");
				Logging.debug("previousClose  " + previousClose);
				Logging.debug("todaysClose  " + todaysClose);
				Logging.debug("todaysdivisor  " + todaysdivisor);
				Logging.debug("indexcurrencyid  " + indexcurrencyid);

				if (previousClose != 0 && todaysClose != 0
						&& todaysdivisor != 0 && indexcurrencyid >= 0) {
					Logging.debug("in computeIndexforTotalReturns 4");
					PreparedStatement psmt = connection
							.prepareStatement(ConnectInit.queries
									.getProperty("totalreturns_lastclosingdate"));
					psmt.setString(1, basedate);
					psmt.setLong(2, l_indexID);
					Logging.debug("query 2 in total returns : " + psmt);
					ResultSet indexdetails = psmt.executeQuery();
					if (!indexdetails.next()) {
						return "---";
					}
					String lastClosingPrice = indexdetails
							.getString("index_value_date");
					stockquery = ConnectInit.queries
							.getProperty("totalReturns_cashdividend_details");
					Logging.debug("query while calculation is : " + stockquery);
					pst_stockDetails = connection.prepareStatement(stockquery);
					pst_stockDetails.setString(2, basedate);
					pst_stockDetails.setString(1, lastClosingPrice);
					pst_stockDetails.setLong(3, l_indexID);
					// rst.close();
					Logging.debug("Calculating total returns index with pst_stockDetails"
							+ pst_stockDetails);
					rst = pst_stockDetails.executeQuery();
					// Logging.getDebug ("rst.first() exists : "+rst.first());
					double dividend;
					int stockcurrencyid, exchangeCurrencyId;
					float currencymultiplier = 1;
					String multiplierstring;
					Logging.debug("in computeIndexforTotalReturns 5");
					while (rst.next()) {
						// while(rst.next()){
						Logging.debug("in computeIndexforTotalReturns 6");
						dividend = rst.getDouble("dividend");
						stockcurrencyid = rst.getInt("stock_currency_id");
						if (dividend != 0 && stockcurrencyid >= 0) {
							boolean flagtocalculatecurrencymultiplier = false;
							Logging.debug("in computeIndexforTotalReturns 7");
							if (indexcurrencyid == stockcurrencyid) {
								Logging.debug("indexcurrencyid == stockcurrencyid ");
								exchangeCurrencyId = 1;
								currencymultiplier = 1;
								flagtocalculatecurrencymultiplier = true;
								flagforcurrency = true;
							} else {
								currencymultiplier = (float) ExchangeRateForCurrencyIndex(
										(int) indexcurrencyid,
										(int) stockcurrencyid, basedate,
										connection);
								Logging.debug("indexcurrencyid != stockcurrencyid "
										+ currencymultiplier);
								/*
								 * //code for exchange rate starts from here //
								 * exchangeCurrencyId =
								 * getExchCodeForTotalReturns( //
								 * indexcurrencyid, stockcurrencyid); if
								 * (exchangeCurrencyId == -999) {
								 * exchangeCurrencyId =
								 * getExchCodeForTotalReturns( stockcurrencyid,
								 * indexcurrencyid); if (exchangeCurrencyId !=
								 * -999) { System.out
								 * .println("in computeIndexforTotalReturns 8");
								 * multiplierstring =
								 * ConnectInit.getConnect().queries
								 * .getProperty("exchange_rate"); Logging.log
								 * .debug("in multiplierstring :" +
								 * multiplierstring); pst_multiplier =
								 * connection
								 * .prepareStatement(multiplierstring);
								 * Logging.getDebug("pst_multiplier : " +
								 * pst_multiplier); pst_multiplier.setInt(1,
								 * exchangeCurrencyId);
								 * Logging.getDebug("pst_multiplier 1: " +
								 * pst_multiplier); rst1 =
								 * pst_multiplier.executeQuery();
								 * Logging.getDebug("pst_multiplier 2: " +
								 * pst_multiplier); rst1.next();
								 * Logging.getDebug("pst_multiplier 3: " +
								 * pst_multiplier); currencymultiplier = rst1
								 * .getFloat("ex_highest_value");
								 * currencymultiplier = 1 / currencymultiplier;
								 * System.out
								 * .println("currency multiplier is(inversed)" +
								 * currencymultiplier); flagforcurrency = true;
								 * } } else { System.out
								 * .println("in computeIndexforTotalReturns 9");
								 * multiplierstring =
								 * ConnectInit.getConnect().queries
								 * .getProperty("currency_index_ex_rate");
								 * System.out .println(
								 * "Before finding exchange rate :\n multiplierstring : "
								 * + multiplierstring +
								 * "and \nexchangeCurrencyId :" +
								 * exchangeCurrencyId); pst_multiplier =
								 * connection
								 * .prepareStatement(multiplierstring);
								 * pst_preStat.setDouble(1, exchangeCurrencyId);
								 * pst_preStat.setString(2, date); //
								 * pst_multiplier // .setInt(1,
								 * exchangeCurrencyId); rst1 =
								 * pst_multiplier.executeQuery();
								 * if(rst1.next()){ currencymultiplier = rst1
								 * .getFloat("ex_highest_value"); }else{
								 * multiplierstring =
								 * ConnectInit.getConnect().queries
								 * .getProperty("currency_index_ex_rate");
								 * 
								 * pst_multiplier = connection
								 * .prepareStatement(multiplierstring);
								 * pst_preStat.setDouble(1, exchangeCurrencyId);
								 * pst_preStat.setDouble(2, exchangeCurrencyId);
								 * pst_preStat.setString(3, date); //
								 * pst_multiplier // .setInt(1,
								 * exchangeCurrencyId); rst1 =
								 * pst_multiplier.executeQuery();
								 * if(rst1.next()){ currencymultiplier = rst1
								 * .getFloat("ex_highest_value"); }else{ return
								 * "---"; } }
								 * Logging.getDebug("currency multiplier is" +
								 * currencymultiplier); flagforcurrency = true;
								 * }
								 */
								// code for exchange rate end here
							}

							double product = dividend / todaysdivisor;
							Logging.debug("dividend/todaysdivisor " + product);
							if (currencymultiplier == -999) {
								return "---";
							}
							String exch_rate = new Double(currencymultiplier)
									.toString();
							exch_rate = ad.indexcompose4digit(exch_rate);
							currencymultiplier = (float) Double
									.parseDouble(exch_rate);
							product = product * currencymultiplier;
							Logging.debug("currency multiplier : "
									+ currencymultiplier + "  ; dividend : "
									+ dividend + " ; todaysdivisor : "
									+ todaysdivisor + "  ; product : "
									+ product);

							if (currencymultiplier == -999) {
								return "---";
							}
							summation.add(new Double(product));
							/*
							 * if (exchangeCurrencyId != -999) { System.out
							 * .println("in computeIndexforTotalReturns 10");
							 * System.out
							 * .println("1st step for individual stock");
							 * Logging.getDebug("dividend " + dividend);
							 * Logging.getDebug("todaysdivisor " +
							 * todaysdivisor);
							 * Logging.getDebug("currencymultiplier " +
							 * currencymultiplier); double product = dividend /
							 * todaysdivisor;
							 * Logging.getDebug("dividend/todaysdivisor " +
							 * product); product = product * currencymultiplier;
							 * Logging.getDebug("product " + product);
							 * System.out
							 * .println("product for individual stock is" +
							 * product); summation.add(new Double(product)); }
							 */
						}
					}
					Logging.debug("flag value for next calculation is : "
							+ flagforcurrency);
					// if (flagforcurrency) {
					if (true) {
						int size = summation.size();
						double d1, indexvalue, d2;
						Logging.debug("Array Size is " + size);
						double sumofdividendsbydivisorbyexchangerate = 0;
						for (int count = 0; count < size; count++) {
							sumofdividendsbydivisorbyexchangerate = sumofdividendsbydivisorbyexchangerate
									+ Double.parseDouble(summation.get(count)
											.toString());
						}
						Logging.debug("Summation of dividends is : "
								+ sumofdividendsbydivisorbyexchangerate);
						/*
						 * d2=sumofdividendsbydivisorbyexchangerate/todaysdivisor
						 * ; Logging.getDebug("Summation of 2nd term : "+d2);
						 */
						Logging.debug("previousClose : " + previousClose);
						Logging.debug("todaysdivisor : " + todaysdivisor);
						d1 = previousClose
								+ (sumofdividendsbydivisorbyexchangerate);
						Logging.debug("Numerator : " + d1);
						indexvalue = d1 / todaysClose;
						// insert into database

						// inserts value in intra day indices
						time = getTime();
						try {
							try {
								stm = connection.createStatement();
								// remove query
								rst = stm
										.executeQuery("select nextval('intra_day_indices_id')");
								// Logging.debug(rst+"");
								rst.next();
							} catch (SQLException e) {
								Logging.debug("error in Query");
								e.getMessage();
							}
							id = rst.getLong(1);
							pst_preStat = connection
									.prepareStatement(ConnectInit.queries
											.getProperty("insert_into_intra_day_indices"));
							Logging.debug("Values being inserted into intra_day_indices_id for index of type total returns");
							Logging.debug("id  : " + id);
							Logging.debug("index id : " + l_indexID);
							Logging.debug("index value  : " + indexvalue);

							// date = QueryClass.formatDate();
							Logging.debug("date  : " + basedate);
							Logging.debug("time  : " + time);
							pst_preStat.setLong(1, id);
							pst_preStat.setLong(5, l_indexID);
							pst_preStat.setDouble(2, indexvalue);
							pst_preStat.setDouble(6, 0);
							pst_preStat.setString(3, basedate);
							pst_preStat.setString(4, time);
							pst_preStat.executeUpdate();// execute query

						} catch (SQLException e) {
							Logging.error("ERROR");
							Logging.error("index values" + e.getMessage());
						}
						// insert into index value daily
						// select index_lowest_value,index_highest_value from
						// index value
						// daily

						try {
							try {
								pst_preStat = connection
										.prepareStatement(ConnectInit.queries
												.getProperty("get_high_low_index_value"));

								pst_preStat.setLong(1, l_indexID);

								pst_preStat.setString(2, basedate);
								rst2 = pst_preStat.executeQuery();// execute
								// query
							} catch (Exception e) {
								Logging.error("ERROR");
								Logging.error("index values" + e.getMessage());
							}
							// if no enteries
							rst2.next();
							date = basedate;
							if (rst2.getRow() == 0) {
								try {

									/*
									 * try { stm = connection
									 * .createStatement(); // remove query rst =
									 * stm .executeQuery(
									 * "select nextval('index_value_daily_id')"
									 * ); Logging.getDebug(rst); rst.next(); }
									 * catch (SQLException e) {
									 * Logging.getDebug("error in Query");
									 * e.getMessage(); } id = rst.getLong(1);
									 * Logging.getDebug(settlement); System.out
									 * .println(
									 * "before insert query in index value daily"
									 * );
									 */

									// code for 52 week low and high from

									fiftytwo_min_max = getFiftytwo_Week_HighLow(indexID);
									if (indexVal > fiftytwo_min_max[0]) {
										fiftytwo_min_max[0] = indexVal;
									}
									if (indexVal < fiftytwo_min_max[1]) {
										fiftytwo_min_max[1] = indexVal;
									}
									// code for 52 week low and high to

									pst_preStat = connection
											.prepareStatement(ConnectInit.queries
													.getProperty("insert_into_index_value_daily1"));
									// pst_preStat.setLong(1, id);

									pst_preStat.setLong(4, l_indexID);

									// pst_preStat.setLong(6, l_indexID);
									tmcv = 0;
									pst_preStat.setDouble(8, tmcv);
									pst_preStat.setDouble(1, indexvalue);
									pst_preStat.setDouble(2, indexvalue);
									pst_preStat.setDouble(3, indexvalue);
									// pst_preStat.setDouble(5, indexvalue);
									pst_preStat.setString(5, basedate);
									pst_preStat.setDouble(6, todaysdivisor);
									// pst_preStat.setString(8, settlement);
									pst_preStat.setDouble(7, tmcv);
									pst_preStat.setDouble(9, todaysdivisor);
									pst_preStat.setDouble(10,
											fiftytwo_min_max[0]);
									pst_preStat.setDouble(11,
											fiftytwo_min_max[1]);
									Logging.debug("Inser query for index value daily : "
											+ pst_preStat);
									pst_preStat.executeUpdate();// execute query
									insertclosing_settlement(indexVal, null,
											settlement, close, basedate,
											l_indexID, connection);

								} catch (Exception e) {
									// e.printStackTrace();
									Logging.error("ERROR");
									Logging.error("index values"
											+ e.getMessage());
								}
							} else {
								low = rst2.getDouble("index_lowest_value");
								high = rst2.getDouble("index_highest_value");
								if (rst2.getString("is_settlement_value") != null) {
									insertclosing_settlement(
											indexVal,
											rst2.getString("is_settlement_value"),
											settlement, close, date, l_indexID,
											connection);
								} else {
									insertclosing_settlement(indexVal, null,
											settlement, close, date, l_indexID,
											connection);

								}

								if (indexvalue > high) {
									// update_high_index_value

									pst_preStat = connection
											.prepareStatement(ConnectInit.queries
													.getProperty("update_high_index_value"));
									pst_preStat.setDouble(1, indexvalue);

									pst_preStat.setLong(2, l_indexID);

									// pst_preStat.setLong(2, l_indexID);
									pst_preStat.setString(3, date);
									pst_preStat.executeUpdate();
								}
								if (indexVal < low) {
									// update_low_index_value

									pst_preStat = connection
											.prepareStatement(ConnectInit.queries
													.getProperty("update_low_index_value"));
									pst_preStat.setDouble(1, indexvalue);

									pst_preStat.setLong(2, l_indexID);

									// pst_preStat.setLong(2, l_indexID);
									pst_preStat.setString(3, date);
									pst_preStat.executeUpdate();
								}
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						// return the value
						Logging.debug("Denominator : " + todaysClose);
						Logging.debug("Total : " + indexvalue);
						Logging.debug("Index Value Calculated for Index id : "
								+ indexID + "  is : " + indexvalue);
						return Double.toString(indexvalue);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Logging.debug("In catch due to : " + e);
			return "---";
		}
		Logging.debug("After catch due to : ");
		return "---";
	}

	/*
	 * public static void main(String srgs[]) { long l_index = 1; String index1;
	 * long tm;
	 * 
	 * CIndexCalculator cindexCal = new CIndexCalculator();
	 * Logging.getDebug("Called"); // cindexCal.returnResult(l_index);//insert
	 * index id // HttpServletRequest request1=new
	 * HttpServletRequest(HttpServletRequest // req,httpServletRes); // index1 =
	 * cindexCal.computeIndex("1750", "y", "y", "yes", "2"); //
	 * Logging.getDebug("index value is :" + index1); Logging.getDebug("Tmcv " +
	 * cindexCal.getTmcv()); tm = (long) cindexCal.getTmcv();
	 * Logging.getDebug(""+tm); Logging.getDebug("Divisor " +
	 * cindexCal.getDivisor()); }
	 */
	public boolean checkClosing(String l_indexID, String basedate,
			Connection connection) {
		boolean firstDailyValue = false;
		if (basedate == null) {
			basedate = QueryClass.formatDate();
		}
		try {
			pst_preStat = connection.prepareStatement(ConnectInit.queries
					.getProperty("compute_index_first_time"));
			pst_preStat.setString(1, l_indexID);// set ? for index id
			pst_preStat.setString(2, basedate);
			Logging.debug("pst_preStat for adjusted values  " + pst_preStat);
			rst1 = pst_preStat.executeQuery();
			if (rst1.next()) {
				// either opening or closing value available
				if (rst1.getString("index_closing_value") != null
						&& !rst1.getString("index_closing_value").equals("")) {
					// closing value available
					// ask whether to overwrite if yes do normal calculation

					/*
					 * int n = JOptionPane.showConfirmDialog(null, "Closing
					 * value already calculated !!\n Are You sure You want to
					 * OverWrite it?? ", "OverWrite closing value??",
					 * JOptionPane.YES_NO_OPTION); if (n ==
					 * JOptionPane.YES_OPTION) {
					 * 
					 * }else { Logging.getDebug("Closing value present so need
					 * not calculate again "); firstDailyValue=true; return
					 * firstDailyValue; }
					 */
				} else if (rst1.getString("index_closing_value") != null
						&& !rst1.getString("index_closing_value").equals("")) {
					// opening value available, do normal calculation
					Logging.debug("Only opening found so doing normal calculation");
				}
			} else {
				// neither opening nor closing available
				firstDailyValue = false;
				/*
				 * pst_preStat = connection
				 * .prepareStatement(ConnectInit.queries
				 * .getProperty("get_adjusted_price_divisor"));
				 * pst_preStat.setString(1, l_indexID); String date =
				 * QueryClass.formatDate(); pst_preStat.setString(2, date);
				 * Logging.getDebug("adjusted_tmcv,adjusted_divisor query
				 * "+pst_preStat); rst1 = pst_preStat.executeQuery();
				 * rst1.next(); tmcv=rst1.getDouble("adjusted_tmcv");
				 * divisor=rst1.getDouble("adjusted_divisor");
				 * indexVal=tmcv/divisor; Logging.getDebug("index value
				 * calculated for adjusted prices is "+indexVal);
				 */
				return firstDailyValue;

			}

			return firstDailyValue;
		} catch (Exception e) {
			Logging.error("Error : " + e.getMessage());
			// e.printStackTrace();
			// TODO: handle exception
		}
		return firstDailyValue;
	}

	public boolean getClosing(String l_indexID, Connection connection) {
		boolean firstDailyValue = false;
		Logging.debug("Checking closing value for index with id :   "
				+ l_indexID);
		try {
			pst_preStat = connection.prepareStatement(ConnectInit.queries
					.getProperty("compute_index_first_time1"));
			pst_preStat.setString(1, l_indexID);// set ? for index id
			Logging.debug("pst_preStat for adjusted values  " + pst_preStat);
			rst1 = pst_preStat.executeQuery();
			if (rst1.next()) {
				// either opening or closing value available
				if (rst1.getString("index_closing_value") != null
						&& !rst1.getString("index_closing_value").equals("")) {
					return true;
				}
			} else {
				return false;

			}

			return false;
		} catch (Exception e) {
			// e.printStackTrace();
			Logging.error("Error : " + e.getMessage());
			// TODO: handle exception
		}
		return false;
	}

	/**
	 * @return Returns the curridStk.
	 */
	public long getCurridStk() {
		return curridStk;
	}

	/**
	 * @param curridStk
	 *            The curridStk to set.
	 */
	public void setCurridStk(long curridStk) {
		this.curridStk = curridStk;
	}

	/**
	 * @return Returns the divisor.
	 */
	public double getDivisor() {
		return divisor;
	}

	/**
	 * @param divisor
	 *            The divisor to set.
	 */
	public void setDivisor(double divisor) {
		this.divisor = divisor;
	}

	/**
	 * @return Returns the exch.
	 */
	public double getExch() {
		return exch;
	}

	/**
	 * @param exch
	 *            The exch to set.
	 */
	public void setExch(double exch) {
		this.exch = exch;
	}

	/**
	 * @return Returns the indexVal.
	 */
	public double getIndexVal() {
		return indexVal;
	}

	/**
	 * @param indexVal
	 *            The indexVal to set.
	 */
	public void setIndexVal(double indexVal) {
		this.indexVal = indexVal;
	}

	/**
	 * @return Returns the iwf.
	 */
	public double getIwf() {
		return iwf;
	}

	/**
	 * @param iwf
	 *            The iwf to set.
	 */
	public void setIwf(double iwf) {
		this.iwf = iwf;
	}

	/**
	 * @return Returns the ltp.
	 */
	public double getLtp() {
		return ltp;
	}

	/**
	 * @param ltp
	 *            The ltp to set.
	 */
	public void setLtp(double ltp) {
		this.ltp = ltp;
	}

	/**
	 * @return Returns the mcv.
	 */
	public double getMcv() {
		return mcv;
	}

	/**
	 * @param mcv
	 *            The mcv to set.
	 */
	public void setMcv(double mcv) {
		this.mcv = mcv;
	}

	/**
	 * @return Returns the ml.
	 */
	public long getMl() {
		return ml;
	}

	/**
	 * @param ml
	 *            The ml to set.
	 */
	public void setMl(long ml) {
		this.ml = ml;
	}

	/**
	 * @return Returns the stkid.
	 */
	public long getStkid() {
		return stkid;
	}

	/**
	 * @param stkid
	 *            The stkid to set.
	 */
	public void setStkid(long stkid) {
		this.stkid = stkid;
	}

	/**
	 * @return Returns the stkId.
	 */
	public long getStkId() {
		return stkId;
	}

	/**
	 * @param stkId
	 *            The stkId to set.
	 */
	public void setStkId(long stkId) {
		this.stkId = stkId;
	}

	/**
	 * @return Returns the tis.
	 */
	public long getTis() {
		return tis;
	}

	/**
	 * @param tis
	 *            The tis to set.
	 */
	public void setTis(long tis) {
		this.tis = tis;
	}

	/**
	 * @return Returns the tmcv.
	 */
	public double getTmcv() {
		return tmcv;
	}

	/**
	 * @param tmcv
	 *            The tmcv to set.
	 */
	public void setTmcv(double tmcv) {
		this.tmcv = tmcv;
	}

	/**
	 * @return Returns the rst.
	 */
	public ResultSet getRst() {
		return rst;
	}

	/**
	 * @param rst
	 *            The rst to set.
	 */
	public void setRst(ResultSet rst) {
		this.rst = rst;
	}

	public ArrayList getDateListBeforeBaseDate(String bdate, String indid) {
		ArrayList arr = new ArrayList();
		String actual_bdate = null;

		actual_bdate = ComputeIndexForm.getBaseDate(indid);
		Logging.debug("actual_bdate is " + actual_bdate);
		int i = 0;
		int diff = 0;
		if (actual_bdate != null) {
			diff = ComputeIndexForm.CompareDate(actual_bdate, bdate);
		}
		while (diff != 0 && diff > 0) {
			actual_bdate = getPreviousDate(actual_bdate);
			Logging.debug("actual_bdate is " + actual_bdate);
			arr.add(i, actual_bdate);
			i++;
			diff = ComputeIndexForm.CompareDate(actual_bdate, bdate);
			Logging.debug("diff is " + diff);

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

		return finaldate;

	}

	/*
	 * public String getPreviousDate(String date){ SimpleDateFormat fr=new
	 * SimpleDateFormat("dd-MM-yyyy"); //String date="01-03-2005";
	 * Logging.getDebug(" current  date is "+date); Date dd = new Date(new
	 * Integer(date.trim().substring(6, 10)).intValue(), new
	 * Integer(date.trim().substring(3, 5)).intValue(), new
	 * Integer(date.trim().substring(0, 2)).intValue());
	 * Logging.getDebug("  date is "+dd); long l1=((dd.getTime())-(86400000 *
	 * 1)); Date ds=new Date(l1); Logging.getDebug(" previous date is "+ds);
	 * String month=new Integer(ds.getMonth()).toString(); String day=new
	 * Integer(ds.getDate()).toString(); if(day.length()!=0 && day.length()<2)
	 * day="0"+day; if(month.length()!=0 && month.length()<2) month="0"+month;
	 * String ldate=day+"-"+month+"-"+ds.getYear();
	 * Logging.getDebug(" previous date is "+ldate); return ldate; }
	 */
	public double getDivisorValue(String indexid) {
		double div = 0.00;
		// Change by Manoj Adekar for Dynamic connection using getdbConnection()
		// instead of getConnection()
		Connection connection = null;
		try {
			Connect con = ConnectInit.getConnect();
			if (connection == null) {
				connection = con.getdbConnection();
			}
			Logging.debug("ConnectInit.queries is " + ConnectInit.queries);
			String query_divisor = ConnectInit.queries
					.getProperty("indexCompute_select_divisor_before_base_date");
			Logging.debug("query_divisor is " + query_divisor
					+ " l_indexID is " + indexid);
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
				Logging.debug(" Divisor" + divisor);
				Logging.debug("divisor taken " + div);
			}
		} catch (SQLException e) {
			Logging.debug(e.getMessage());
			Logging.debug("index values" + e);
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
		return div;
	}

	/**
	 * to check if prices available for date or not. return true if prices are
	 * available or false if prices are not available on date.
	 * 
	 * @param hist_Date
	 * @param indid
	 * @return
	 */
	public boolean checkPriceAvailable(String hist_Date, String indid,
			Connection connection) {
		boolean flag = false;
		Connect connect = ConnectInit.getConnect();
		Logging.debug("in checkPriceAvailable");
		Logging.debug("in hist_Date " + hist_Date + "  indid is  " + indid);
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
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

	public Vector getAvgPe_Pb_dividend(String indid, String date) {

		Vector v1 = new Vector();
		Connect connect = ConnectInit.getConnect();
		Logging.debug("in getAvgPe_Pb_dividend");
		Logging.debug("  indid is  " + indid);
		Connection con = null;
		PreparedStatement pst;
		ResultSet rst;
		try {
			// Change by Manoj Adekar for Dynamic connection using
			// getdbConnection() instead of getConnection()
			if (con == null) {
				con = connect.getdbConnection();
			}
			try {
				pst = con
						.prepareStatement(ConnectInit.queries
								.getProperty("index_compute_pe_pb_dividend_index_wise"));
				pst.setString(1, indid);
				pst.setString(2, date);
				pst.setString(3, indid);
				pst.setString(4, date);
				pst.setString(5, indid);
				pst.setString(6, date);
				rst = pst.executeQuery();
				while (rst.next()) {
					v1.add(0, rst.getString(1));
					v1.add(1, rst.getString(2));
					v1.add(2, rst.getString(3));
					// Logging.debug("inside while loop  pe is "+rst.getString(1)+" pb is "+rst.getString(2)+" dividend is "+rst.getString(3));
				}
			} catch (Exception e) {
				Logging.error("Error : " + e.getMessage());
			}
			Logging.debug("v1 size is " + v1.size());

			// Close the Dynamic Connection : Manoj Adekar
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		return v1;
	}

	/**
	 * METHOD RETURNS COLLECTION OF DISTINCT CURRENCY_MAST_ID ALONG WITH
	 * CURRENCY EXCHANGE RATE FOR COMPOSITION SCRIPS OF AN INDEX PASSED TO IT.
	 * 
	 * @param connection
	 * @param type_of_index
	 * @param index_id
	 * @return
	 */
	public Hashtable getCsExcForScripCompose(Connection connection,
			int type_of_index, long index_id) {
		Hashtable scripCurrList = new Hashtable();
		Connect connect = ConnectInit.getConnect();
		PreparedStatement pst = null;
		ResultSet rst = null;
		Logging.debug("Finally Exchange rate is : " + exch);
		try {
			pst = connection
					.prepareStatement(ConnectInit.queries
							.getProperty("get_distinct_currency_id_for_composition_scrips"));
			pst.setLong(1, index_id);
			rst = pst.executeQuery();
			while (rst.next()) {
				String string2 = "0";
				/*
				 * if (type_of_index == 5) { string2 = rst.getString(3); } else
				 * {
				 */
				string2 = rst.getString(2);
				// }
				String string = rst.getString(1);
				String temp = IndexCalculatorCollection
						.getIndexCurrancyExchRate(string, string2, connection);
				if (temp != null) {
					exch = new Double(temp).doubleValue();
				} else {
					temp = IndexCalculatorCollection.getIndexCurrancyExchRate(
							string2, string, connection);
					if (temp == null) {
						exch = 1.0;
					} else {
						exch = 1 / new Double(temp).doubleValue();
					}
				}
				scripCurrList.put(string, (new Double(exch).toString()));
			}
			if (rst != null)
				rst.close();
			if (pst != null)
				pst.close();
		} catch (Exception e) {
			Logging.error("Error : " + e.getMessage());
			System.out.println("Error in getCsExcForScripCompose method ***  "+e.getMessage());
			exch = 1.0;
		} finally {
			try {
				if (rst != null)
					rst.close();
				if (pst != null)
					pst.close();
			} catch (Exception ex) {
				Logging.error("Error : unable to close resultset,preparedStatement "
						+ ex.getMessage());
			}
		}
		Logging.debug("Finally Exchange rate is : " + exch);
		return scripCurrList;
	}

	public double[] getFiftytwo_Week_HighLow(String indid) {
		double[] v_ftw = new double[2];
		Connect connect = ConnectInit.getConnect();
		Logging.debug("in getFiftytwo_Week_HighLow");
		Logging.debug("  indid is  " + indid);
		Connection con = null;
		PreparedStatement pst;
		ResultSet rst;
		try {
			// Change by Manoj Adekar for Dynamic connection using
			// getdbConnection() instead of getConnection()
			if (con == null) {
				con = connect.getdbConnection();
			}
			try {
				pst = con.prepareStatement(ConnectInit.queries
						.getProperty("get_Fiftytwo_Week_HighLow"));
				pst.setString(1, indid);

				rst = pst.executeQuery();
				while (rst.next()) {
					v_ftw[0] = rst.getDouble(1);
					v_ftw[1] = rst.getDouble(2);
					// Logging.debug("max(index closing value) is "+v_ftw[0]+" min(index closing value) is "+v_ftw[1]);
				}
			} catch (Exception e) {
				Logging.error("Error : " + e.getMessage());
			}
			Logging.debug("v1 size is " + v_ftw.length);

			// Close the Dynamic Connection : Manoj Adekar
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		return v_ftw;
	}
}

