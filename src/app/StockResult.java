/*
 * Created on Oct 27, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jfree.chart.demo.servlet.ComposeIndex;

import com.harrier.initializeation.ConnectInit;

/**
 * @author kena
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class StockResult {
	static Logger Logging = Logger.getLogger(StockResult.class);
	public static Vector identifier_list = new Vector();

	public static ResultSet resultStock(Connection con, String query,
			String stkid) {
		ResultSet rs = null;
		Logging.debug("Query is " + query);
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, Integer.parseInt(stkid));
			Logging.debug("Query atter set " + stmt);
			rs = stmt.executeQuery();
			Logging.debug("Query executed " + rs);
			// Logging.getDebug("rs is in query="+rs.next());
		} catch (Exception e) {
			Logging.error("StockResult Class error : " + e.getMessage());
		}

		Logging.debug("Result set " + rs);
		return rs;
	}

	/**
	 * method to get details for stock . input parameter is stock_id.
	 */
	public static ResultSet resultStock(Connection connection, String stkid) {

		// Connection changed from static to local on 25 AUG 06 by P.Bhende
		ResultSet rs = null;
		// app.Connect con=new app.Connect();
		Connect con = ConnectInit.getConnect();
		if (connection == null) {
			connection = con.getdbConnection();
		}
		try {
			PreparedStatement stmt = connection
					.prepareStatement(ConnectInit.queries
							.getProperty("stock_details_for_stock_master"));
			stmt.setInt(1, Integer.parseInt(stkid));
			Logging.debug("Query atter set " + stmt);
			rs = stmt.executeQuery();
			Logging.debug("Query executed " + rs);
			// Logging.getDebug("rs is in query="+rs.next());
		} catch (Exception e) {
			Logging.error("StockResult Class error : " + e.getMessage());
		}
		Logging.debug("Result set " + rs);
		return rs;
	}

	public static ResultSet resultStockBonds(Connection connection, String stkid) {

		// Connection changed from static to local on 25 AUG 06 by P.Bhende
		ResultSet rs = null;
		// app.Connect con=new app.Connect();
		Connect con = ConnectInit.getConnect();
		if (connection == null) {
			connection = con.getdbConnection();
		}
		try {
			PreparedStatement stmt = connection
					.prepareStatement(ConnectInit.queries
							.getProperty("stock_details_for_stock_master11"));
			stmt.setInt(1, Integer.parseInt(stkid));
			Logging.debug("Query atter set " + stmt);
			rs = stmt.executeQuery();
			Logging.debug("Query executed " + rs);
			// Logging.getDebug("rs is in query="+rs.next());
		} catch (Exception e) {
			Logging.error("StockResult Class error : " + e.getMessage());
		}
		Logging.debug("Result set " + rs);
		return rs;
	}

	public static ResultSet resultStock1(Connection con, String query) {
		ResultSet rs = null;

		try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(query);
		} catch (Exception e) {
			Logging.error("StockResult Class error : " + e.getMessage());
		}
		return rs;
	}

	/**
	 * method to generate the list of currencies.
	 */
	public static ResultSet getCurrencyList(Connection connection) {
		ResultSet rs = null;
		try {

			// app.Connect con=new app.Connect();
			Connect con = ConnectInit.getConnect();
			rs = connection.createStatement().executeQuery(
					ConnectInit.queries.getProperty("currency_list"));// get
																		// list
																		// of
																		// currency
																		// (currency
																		// id,currency
																		// name).
		} catch (Exception e) {
			Logging.error("StockResult Class error : " + e.getMessage());
		}
		return rs;
	}

	/**
	 * method to get the alert values for the stock.
	 */
	public static ResultSet getAlert_rejection() {
		// Dont have any references in Project.....
		Connection connection = null;
		ResultSet rs = null;
		try {
			// Change by Manoj Adekar for Dynamic connection using
			// getdbConnection() instead of getConnection()
			// app.Connect con=new app.Connect();
			Connect con = ConnectInit.getConnect();
			if (connection == null) {
				connection = con.getdbConnection();
			}
			rs = connection.createStatement().executeQuery(
					ConnectInit.queries
							.getProperty("index_alert_rejection_percentage"));// get
																				// alert
																				// rejection
																				// percentage
																				// from
																				// system
																				// configuration
																				// table.
		} catch (Exception e) {
			Logging.error("StockResult Class error : " + e.getMessage());
		}
		return rs;
	}

	/**
	 * method to generate the list of depository receipt.
	 */
	public static ResultSet getDepository_receipt(Connection connection) {
		ResultSet rs = null;
		try {
			// app.Connect con=new app.Connect();
			Connect con = ConnectInit.getConnect();
			if (connection == null) {
				connection = con.getdbConnection();
			}
			rs = connection.createStatement().executeQuery(
					ConnectInit.queries
							.getProperty("get_depository_receipt_list"));// get
																			// list
																			// of
																			// depository
																			// receipt.
		} catch (Exception e) {
			Logging.error("StockResult Class error : " + e.getMessage());
		}
		return rs;
	}

	/**
	 * method to generate the list of rating codes.
	 */
	public static ResultSet getRatingCode(Connection connection) {
		ResultSet rs = null;
		try {
			// app.Connect con=new app.Connect();
			Connect con = ConnectInit.getConnect();
			if (connection == null) {
				connection = con.getdbConnection();
			}
			rs = connection.createStatement().executeQuery(
					ConnectInit.queries.getProperty("select_rating_code"));// get
																			// list
																			// of
																			// rating
																			// codes
																			// (rating
																			// code
																			// id,rating
																			// code
																			// name).
		} catch (Exception e) {
			Logging.error("StockResult Class error : " + e.getMessage());
		}
		return rs;
	}

	/**
	 * method to generate the list of countries.
	 */
	public static ResultSet getCountryList(Connection connection) {
		ResultSet rs = null;
		try {
			// app.Connect con=new app.Connect();
			Connect con = ConnectInit.getConnect();
			rs = connection.createStatement().executeQuery(
					ConnectInit.queries.getProperty("select_from_countries"));// "select * from countries";get
																				// list
																				// of
																				// countries.
		} catch (Exception e) {
			Logging.error("StockResult Class error : " + e.getMessage());
		}
		return rs;
	}

	/**
	 * method to generate the list of stock exchanges.
	 */
	public static ResultSet getStockExchangeList(Connection connection) {
		ResultSet rs = null;
		try {
			// app.Connect con=new app.Connect();
			Connect con = ConnectInit.getConnect();
			// Change by Manoj Adekar for Dynamic connection using
			// getdbConnection() instead of getConnection()
			if (connection == null) {
				connection = con.getdbConnection();
			}
			rs = connection.createStatement().executeQuery(
					ConnectInit.queries
							.getProperty("stock_exchange_online_list")); // get
																			// stock
																			// exchange
																			// list
																			// (stock_exchange_id,stock_exchange_name).
		} catch (Exception e) {
			Logging.error("StockResult Class error : " + e.getMessage());
		}
		return rs;
	}

	/**
	 * method to get exchange_id,country_id,currency_id from system
	 * configuration table.
	 */
	public static ResultSet getExch_countr_curr() {
		System.out.println("Inside Method *** getExch_countr_curr ");
		Connection connection = null;
		// This method has reference in ComposeIndex...Not changed from static
		// to local...
		ResultSet rs = null;
		try {
			// app.Connect con=new app.Connect();
			Connect con = ConnectInit.getConnect();
			// Change by Manoj Adekar for Dynamic connection using
			// getdbConnection() instead of getConnection()
			if (connection == null) {
				connection = con.getdbConnection();
			}
			rs = connection.createStatement().executeQuery(
					ConnectInit.queries
							.getProperty("get_exc_country_curr_from_cnfig")); // get
																				// exchange_id,country_id,currency_id
																				// from
																				// system
																				// configuration
																				// table.
		} catch (Exception e) {
			Logging.error("StockResult Class error : " + e.getMessage());
		}
		return rs;
	}

	/**
	 * method to generate the list of companies.
	 */
	public static ResultSet getCompanyList(Connection connection) {
		// Connection changes from static to local on 25 AUG 06 by P.Bhende
		ResultSet rs = null;

		try {
			// app.Connect con=new app.Connect();
			Connect con = ConnectInit.getConnect();
			if (connection == null) {
				connection = con.getdbConnection();
			}
			rs = connection.createStatement().executeQuery(
					ConnectInit.queries.getProperty("company_list")); // get
																		// company
																		// list(company_id,company_name).
		} catch (Exception e) {
			Logging.error("StockResult Class error : " + e.getMessage());
		}
		return rs;
	}

	/**
	 * method to generate the list of stock type.
	 */
	public static ResultSet getStockTypeList(Connection connection) {
		ResultSet rs = null;
		try {
			// app.Connect con=new app.Connect();
			Connect con = ConnectInit.getConnect();
			if (connection == null) {
				connection = con.getdbConnection();
			}
			rs = connection.createStatement().executeQuery(
					ConnectInit.queries.getProperty("stock_type_list")); // get
																			// stock
																			// type
																			// list(stock_type_name).
		} catch (Exception e) {
			Logging.error("StockResult Class error : " + e.getMessage());
		}
		return rs;
	}

	/**
	 * metod to get the list of indices to which this stock belongs.
	 */
	public static ResultSet getAffecteIndx_ca(Connection connection,
			String param) {
		ResultSet rs = null;
		// Connection connection = null;
		try {
			if (param == null) {
				Logging.debug("Param value is " + param);
				return rs;
			}
			// app.Connect con=new app.Connect();
			Connect con = ConnectInit.getConnect();
			if (connection == null) {
				connection = con.getdbConnection();
			}
			String query = ConnectInit.queries
					.getProperty("affected_index_by_ca");
			Logging.debug("Param value is " + param + "query=====" + query);
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, Integer.parseInt(param));
			rs = stmt.executeQuery();
			Logging.debug("query" + query);
		} catch (Exception e) {
			Logging.error("Error : " + e.getMessage());
		}
		return rs;
	}

	/**
	 * metod to get the adr gdr detail for the selected stock.
	 * 
	 * @param param
	 * @return
	 */
	public static ResultSet getAdr_gdr_detail(Connection connection,
			String param) {
		// Connection changed from static to local on 25 AUG 06 by P.Bhende
		ResultSet rs = null;

		try {
			if (param == null) {
				Logging.debug("Param value is " + param);
				return rs;
			}
			// app.Connect con=new app.Connect();
			Connect con = ConnectInit.getConnect();
			if (connection == null) {
				connection = con.getdbConnection();
			}
			String query = ConnectInit.queries
					.getProperty("get_adr_gdr_detail_for_stock");
			Logging.debug("Param value is " + param + "query=====" + query);
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, param);
			rs = stmt.executeQuery();
			Logging.debug("query" + stmt);
		} catch (Exception e) {
			Logging.error("Error : " + e.getMessage());
		}
		return rs;
	}

	/**
	 * method to generate a vector containing all the identifier codes for the
	 * given stockid
	 * 
	 * @param stockid
	 * @return Vector
	 */
	public static Vector getIdentifierCode_stkid(String stockid) {
		Connection connection = null;

		ResultSet rs = null;
		// app.Connect con=new app.Connect();
		Connect con = ConnectInit.getConnect();
		try {
			if (connection == null) {
				connection = con.getdbConnection();
			}
			String[] identifier_name = { "sedol", "isin", "ric", "crisil",
					"cusip", "exchange_code", "ticker" };
			int k = 0;
			Logging
					.debug("identifier_name length is "
							+ identifier_name.length);
			for (int i = 0; i < (identifier_name.length); i++) {
				Logging.debug("i is " + i);
				if ((identifier_name[i]).equals("exchange_code")) {
					PreparedStatement stmt1 = connection
							.prepareStatement(ConnectInit.queries
									.getProperty("get_exchange_code_name_for_stockid"));
					stmt1.setString(1, stockid);
					stmt1.setString(2, stockid);
					Logging.debug("Query after set " + stmt1);
					ResultSet rs1 = stmt1.executeQuery();
					Logging.debug("rs1 is " + rs1);
					if (rs1.next()) {
						String exch_code = (String) rs1
								.getString("identifier_code_value");
						Logging.debug("exch_code is " + exch_code);
						identifier_list.add(k, exch_code);
						k++;
					} else {
						identifier_list.add(k, "");
						k++;
					}
				} else {
					rs = null;
					Logging.debug("(identifier_name[i]) is "
							+ (identifier_name[i]));
					PreparedStatement stmt = connection
							.prepareStatement(ConnectInit.queries
									.getProperty("get_identifier_code_name_for_stockid"));
					stmt.setString(1, stockid);
					stmt.setString(2, (identifier_name[i]));
					Logging.debug("Query atter set " + stmt);
					rs = stmt.executeQuery();
					if (rs.next()) {
						String identifier = ((String) rs
								.getString("identifier_code_value"));
						Logging.debug("identifier is " + identifier);
						identifier_list.add(k, identifier);
						k++;
					} else {
						identifier_list.add(k, "");
						k++;
					}
				}
			}
			rs.close();

			Logging.debug("vector size in get identifier "
					+ identifier_list.size());
		} catch (SQLException e) {
			Logging.error(" SQL Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		return identifier_list;

	}

}
