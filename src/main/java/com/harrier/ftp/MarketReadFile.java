/*
 * Created on Sep 1, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.harrier.ftp;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import org.apache.log4j.Logger;

import app.Connect;
import app.QueryClass;

import com.harrier.initializeation.ConnectInit;
/**
 * @author ashishi
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class MarketReadFile {
	static Logger Logging = Logger.getLogger(MarketReadFile.class);
	public Hashtable table = new Hashtable();

	public Hashtable stkid_list = new Hashtable();;

	public Hashtable triker_list = new Hashtable();;

	Market mark = null;

	protected void finalize() {
		this.stkid_list = null;
		this.triker_list = null;

	}

	/**
	 * method to Disply and put in Hashtable the details for MarketInformation contained in the latest
	 * .mkt file uploaded from FTP server.
	 * 
	 * @return StringBuffer
	 */

	public StringBuffer marketdisplay(String exchange_id, String fileName) {
		StringBuffer buffer = new StringBuffer();
		int j = 1;
		Logging.debug("In while loooooop");
		short Trans_c = 0;
		int Time_stamp = 0;
		short Message_l = 0;
		short Sec_token = 0;
		float Last_tr_price = 0.0F;
		int Best_by_quant = 0;
		float Best_by_price = 0.0F;
		int Best_sell_quant = 0;
		float Best_sell_price = 0.0F;
		int Total_traded_quant = 0;
		float Avg_trad_price = 0.0F;
		float op = 0.0F;
		float hp = 0.0F;
		float lp = 0.0F;
		float cp = 0.0F;
		int filler = 0;

		String style = null;
		try {
			buffer.append("<tr width = '100%'>");
			style = "gridStyle-header color='blue' ";
			buffer.append("<td width='9%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("Sec_token");//Security Token.
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("Last_tr_price");//Latest Traded price.
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("Best_buy_quant");//Best buy Quantity.
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("Best_buy_price");//Besst Buy Price.
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("Best_sell_quant");//Best Sell Quantity.
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("Best_sell_price");//Best Sell Price.
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("Total_traded_quant");//Total Traded Quantity.
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("Avg_trad_price");//Avg Trade Price.
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("open_price");//Open Price.
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("High_price");//High price.
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("Low_price");//Low price.
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("Close_price");//Close prrice.
			buffer.append("</td>");
			//buffer.append("</font>");
			buffer.append("</tr>");
			buffer.append("<tr>");
			File TransFileIn = new File("D://DownLoad1//" + fileName);

			DataInputStream Data_In = new DataInputStream(
					new BufferedInputStream(new FileInputStream(TransFileIn)));
			try {
				while (Data_In.available() != 0) {
					mark = new Market();
					Trans_c = 0;Time_stamp = 0;Message_l = 0;Sec_token = 0;
					Last_tr_price = 0.0F;Best_by_quant = 0;Best_by_price = 0.0F;
					Best_sell_quant = 0;Best_sell_price = 0.0F;Total_traded_quant = 0;
					Avg_trad_price = 0.0F;op = 0.0F;hp = 0.0F;lp = 0.0F;cp = 0.0F;filler = 0;

					Logging.debug(" data available after begining "
							+ Data_In.available());

					Trans_c = Short.reverseBytes((short) Data_In.readShort());
					Logging.debug(" data available after first "
							+ Data_In.available());
					Logging.debug(" Trans_c is " + Trans_c);
					mark.setTrans_c(Trans_c);

					Time_stamp = Integer.reverseBytes(Data_In.readInt());
					Logging.debug(" data available after second "
							+ Data_In.available());
					Logging.debug(" Time_stamp is " + Time_stamp);
					long ddt = Math.abs(Time_stamp);
					ddt = ddt * 1000;
					Logging.debug(" ddt is " + ddt);
					Date dd = new Date(ddt);
					Logging.debug(" date is " + dd);
					mark.setTime_stamp(ddt);

					Message_l = Short.reverseBytes((short) Data_In.readShort());
					Logging.debug(" data available after third "
							+ Data_In.available());
					Logging.debug(" Message_l is " + Message_l);
					mark.setMessage_l(Message_l);
					
					Sec_token = Short.reverseBytes((short) Data_In.readShort());
					Logging.debug(" data available after forth "
							+ Data_In.available());
					Logging.debug(" Sec_token is " + Sec_token);
					mark.setSec_token(Sec_token);
					buffer.append("<td> ");
					buffer.append(Sec_token);
					buffer.append(" </td>");

					Last_tr_price = Integer.reverseBytes(Data_In.readInt());
					Last_tr_price /= 100;
					Logging.debug(" data available after fifth "
							+ Data_In.available());
					Logging.debug(" Last_tr_price is " + Last_tr_price);
					mark.setLast_tr_price(Last_tr_price);
					buffer.append("<td> ");
					buffer.append(Last_tr_price);
					buffer.append(" </td>");

					Best_by_quant = Integer.reverseBytes(Data_In.readInt());
					Logging.debug(" data available after sixth "
							+ Data_In.available());
					Logging.debug(" Best_by_quant is " + Best_by_quant);
					mark.setBest_by_quant(Best_by_quant);
					buffer.append("<td> ");
					buffer.append(Best_by_quant);
					buffer.append(" </td>");

					Best_by_price = Integer.reverseBytes(Data_In.readInt());
					Best_by_price /= 100;
					Logging.debug(" data available after seventh "
							+ Data_In.available());
					Logging.debug(" Best_by_price is " + Best_by_price);
					mark.setBest_by_price(Best_by_price);
					buffer.append("<td> ");
					buffer.append(Best_by_price);
					buffer.append(" </td>");

					Best_sell_quant = Integer.reverseBytes(Data_In.readInt());
					Logging.debug(" data available after eighth "
							+ Data_In.available());
					Logging.debug(" Best_sell_quant is " + Best_sell_quant);
					mark.setBest_sell_quant(Best_sell_quant);
					buffer.append("<td> ");
					buffer.append(Best_sell_quant);
					buffer.append(" </td>");

					Best_sell_price = Integer.reverseBytes(Data_In.readInt());
					Best_sell_price /= 100;
					Logging.debug(" data available after nineth "
							+ Data_In.available());
					Logging.debug(" Best_sell_price is " + Best_sell_price);
					mark.setBest_sell_price(Best_sell_price);
					buffer.append("<td> ");
					buffer.append(Best_sell_price);
					buffer.append(" </td>");

					Total_traded_quant = Integer
							.reverseBytes(Data_In.readInt());
					Logging.debug(" data available after tenth "
							+ Data_In.available());
					Logging.debug(" Total_traded_quant is "
							+ Total_traded_quant);
					mark.setTotal_traded_quant(Total_traded_quant);
					buffer.append("<td> ");
					buffer.append(Total_traded_quant);
					buffer.append(" </td>");

					Avg_trad_price = Integer.reverseBytes(Data_In.readInt());
					Avg_trad_price /= 100;
					Logging.debug(" data available after eleventh "
							+ Data_In.available());
					Logging.debug(" Avg_trad_price is " + Avg_trad_price);
					mark.setAvg_trad_price(Avg_trad_price);
					buffer.append("<td> ");
					buffer.append(Avg_trad_price);
					buffer.append(" </td>");

					op = Integer.reverseBytes(Data_In.readInt());
					op /= 100;
					Logging.debug(" data available after 12th "
							+ Data_In.available());
					Logging.debug(" op is " + op);
					mark.setOp(op);
					buffer.append("<td> ");
					buffer.append(op);
					buffer.append(" </td>");

					hp = Integer.reverseBytes(Data_In.readInt());
					hp /= 100;
					Logging.debug(" data available after 13th "
							+ Data_In.available());
					Logging.debug(" hp is " + hp);
					mark.setHp(hp);
					buffer.append("<td> ");
					buffer.append(hp);
					buffer.append(" </td>");
					
					lp = Integer.reverseBytes(Data_In.readInt());
					lp /= 100;
					Logging.debug(" data available after 14th "
							+ Data_In.available());
					Logging.debug(" lp is " + lp);
					mark.setLp(lp);
					buffer.append("<td> ");
					buffer.append(lp);
					buffer.append(" </td>");

					cp = Integer.reverseBytes(Data_In.readInt());
					cp /= 100;
					Logging.debug(" data available after 15th "
							+ Data_In.available());
					Logging.debug(" cp is " + cp);
					mark.setCp(cp);
					buffer.append("<td> ");
					buffer.append(cp);
					buffer.append(" </td>");
					filler = Integer.reverseBytes(Data_In.readInt());
					Logging.debug(" data available after 16th "
							+ Data_In.available());
					Logging.debug(" filler is " + filler);
					mark.setFiller(filler);
					Logging.debug("after query");
					Logging.debug("Trans code =" + Trans_c + ","
							+ "Time Stamp =" + Time_stamp + ","
							+ "Message lenght =" + Message_l + ","
							+ "Security token=" + Sec_token + ","
							+ "Last traded price=" + Last_tr_price + ","
							+ "Best Buy quantity=" + Best_by_quant + ","
							+ "Best buy price=" + Best_by_price + ","
							+ "Best sell quant=" + Best_sell_quant + ","
							+ " Best sell price=" + Best_sell_price + ","
							+ "Total traded quantity=" + Total_traded_quant
							+ "," + "Avg traded price=" + Avg_trad_price + ","
							+ " Open price=" + op + "," + "High price=" + hp
							+ "," + " Low price=" + lp + "," + "Close price="
							+ cp + "," + "Filler=" + filler + "");
					table.put(new Short(mark.getSec_token()).toString(), mark);

					buffer.append("</tr>");
				}

				Logging.debug("completed after puting to Hashtable");

				Logging.debug("End Of File");
				j++;
				
			}//end of inner try
			catch (Exception e) {
				Logging.debug(e);
			}//end of catch
			finally {
				try {
					if (Data_In != null)
						Data_In.close();
				} catch (Exception erd) {
				}
			}
		} catch (Exception e) {
			Logging.debug(e);
		}//end of catch
		return buffer;
	}

	/**
	 * method to save the details for MarketInformation contained in the latest
	 * .mkt file uploaded from FTP server.
	 * 
	 * @return StringBuffer
	 */
	public StringBuffer storeMarketInfirmation(String exchange_id, String pdate) {
		StringBuffer buffer = new StringBuffer();
		int counter1 = 0;
		int counter = 0;
		boolean allow = true;
		Connect connect = ConnectInit.getConnect();
		Hashtable stkid_list = getTisList(2, exchange_id);
		Hashtable stktis_list = getTisList(1, exchange_id);
		Hashtable Company_name = new Hashtable();
		double stkHigh = 0, stkLow = 0;
		Connection connection = null;
		try {
			try {
				if (connection == null)
					connection = connect.getConnectionForTransaction();
			} catch (Exception e) {
				Logging.error(" Error : " + e.getMessage());
			}
			PreparedStatement pst = null;
			ResultSet result = null;
			Enumeration e = table.keys();
			String key = "";
			String scrip_name="";
			String ticker = "";
			String query = "", stockID = null;
			String identifier_code_name = "";
			Runtime runtime = Runtime.getRuntime();
			Logging.debug("table size :" + table.size());
			triker_list = getListOfSecandtriker(connect, connection);
			Company_name=getScripname(connect, connection);
			for (e = table.keys(); e.hasMoreElements();) {
				counter1++;
				if (counter == 5) {
					int a = result.CLOSE_CURSORS_AT_COMMIT;
					connection.commit();
					connection.setAutoCommit(true);
					Logging.debug("counter after commit 5 IS " + counter1);
					counter = 0;
				}
				if (counter1 % 100 == 0) {
					connection.commit();
					connection.close();
					connection = null;
					try {
						if (connection == null)
							connection = connect.getConnectionForTransaction();
					} catch (Exception e2) {
						Logging.error(" Error : " + e2.getMessage());
					}
				}
				try {
					key = (String) e.nextElement();
					mark = (Market) table.get(key);

					if (triker_list.containsKey(key)) {
						ticker = triker_list.get(key).toString();

						if (stkid_list.containsKey(ticker)) {
							stockID = stkid_list.get(ticker).toString();
						}
						if (Company_name.containsKey(key)) {
							scrip_name = Company_name.get(key).toString();
						}
						Logging.debug(" stockid is " + stockID);
						if (stockID == null || stockID.equals("")) {
							String price_unimprt_query = ConnectInit.queries
									.getProperty("insert_into_new_issues_unimported_prices");
							MarkUnimportedStockPrices(connection, exchange_id,
									ticker, pdate, scrip_name, price_unimprt_query,
									mark);
							buffer.append("<tr><td>");
							buffer.append(ticker);
							buffer.append("</td><td><font color='black'>Stock Does Not Exist</font></td>");
							continue;
						}
						if (allow == true) {
							//insert the data into table intra_day_stock_price
							long time = mark.getTime_stamp();
							Timestamp timestap = new Timestamp(time);
							String currDate = QueryClass.formatDate();

							pst = connection
									.prepareStatement(ConnectInit.queries
											.getProperty("insert_into_intra_day_stock_prices"));
							
							pst.setDouble(1, mark.getLast_tr_price());
							pst.setString(2, pdate);
							pst.setString(3, currDate);
							pst.setString(4, stockID);
							
							pst.executeUpdate();

							pst = connection.prepareStatement(ConnectInit.queries
									.getProperty("get_stock_high_low_value"));
							
							pst.setString(1, pdate);
							pst.setString(2, stockID);
							result = pst.executeQuery();
							//check if result set has values in it. true
							// results indicate that entry
							//for todays date for a particular stock is already
							// done so compare last
							//price with the stored high low value
							if (result.next()) {
								stkHigh = result.getDouble(1);
								stkLow = result.getDouble(2);
								//if ltp greater update high, close, passing
								// stock id
								if ((mark.getCp()) > stkHigh) {
									pst = connection
											.prepareStatement(ConnectInit.queries
													.getProperty("update_high_close_stock_price"));
									pst.setFloat(1, mark.getHp());
									pst.setFloat(2, mark.getCp());
									pst.setString(3, stockID);
									pst.setString(4, pdate);
									pst.executeUpdate();
									buffer.append("<tr><td>");
									buffer.append(scrip_name);
									buffer.append("</td><td><font color='black'>High And Close Prices Entered Successfully</font></td>");
									
								} else {
									//if ltp greater update low, close, passing
									// stock id
									if (mark.getCp() < stkLow) {
										pst = connection
												.prepareStatement(ConnectInit.queries
														.getProperty("update_low_close_stock_price"));
										pst.setDouble(1, mark.getLp());
										pst.setDouble(2, mark.getCp());
										pst.setString(3, stockID);
										pst.setString(4, pdate);
										pst.executeUpdate();
										buffer.append("<tr><td>");
										buffer.append(scrip_name);
										buffer.append("</td><td><font color='black'>Low And Close Prices Entered Successfully</font></td>");
									}
								}
							} else {
								//this indicate that no entry has been made for
								// 2days date for a

								//insert pk, open hig low close stkID tradedVol
								// date, tis in
								//stock_price_daily
								pst = connection
										.prepareStatement(ConnectInit.queries
												.getProperty("insert_into_stock_price_daily_index_detail_file"));
							
								pst.setFloat(1, mark.getOp());
								pst.setFloat(2, mark.getHp());
								pst.setFloat(3, mark.getLp());
								pst.setFloat(4, mark.getCp());
								pst.setString(5, stockID);
								pst.setInt(6, mark.getTotal_traded_quant());
								pst.setString(7, pdate);
								
								pst.setFloat(8, mark.getCp());
								String tis = "0", mkt_cap = "0.0";
								double mcv = 0.0, pe = 0.0, pb = 0.0, dividend = 0.0;
								if (stkid_list.containsKey(stockID)) {
									tis = stkid_list.get(stockID).toString();
								}
								if ((tis == null) || (tis.equals("0"))) {
									mkt_cap = "0.0";
								} else {
									mcv = ((mark.getCp()) * (Double
											.parseDouble(tis)));
									mkt_cap = new Double(mcv).toString();
								}
								pst.setDouble(9, Double.parseDouble(mkt_cap));

								pst.setFloat(10, mark.getLast_tr_price());
								pst.setInt(11, 0);
								Vector PePbDiv = getAveragePePbDivident(stockID);
								if (mcv != 0) {
									pe = ((double) Double
											.parseDouble((String) PePbDiv
													.get(0)))
											/ mcv;
									pb = ((double) Double
											.parseDouble((String) PePbDiv
													.get(1)))
											/ mcv;
									dividend = ((double) Double
											.parseDouble((String) PePbDiv
													.get(2)))
											/ mcv;
								}
								pst.setDouble(12, pe);
								pst.setDouble(13, pb);
								pst.setDouble(14, dividend);
								pst.setString(15, null);
								pst.executeUpdate();
								buffer.append("<tr><td>");
								buffer.append(scrip_name);
								buffer.append("</td><td><font color='black'>Stock Prices Entered Successfully</font></td>");
							}
						}
					} else {
						buffer.append("<tr><td>");
						buffer.append(key);
						buffer.append("</td><td><font color='black'>No data available for this Security_no.</font></td>");
					}
				} catch (Exception e1) {
					Logging.error(" Error : " + e1.getMessage());
				}
			}
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		}
		return buffer;

	}
	/**
	 * @return Returns the Hashtable showing list of triker values.
	 */
	public Hashtable getListOfSecandtriker(Connect con, Connection connection) {
		Hashtable security_list_triker = new Hashtable();
		Statement pst = null;
		ResultSet result = null;
		try {
			pst = connection.createStatement();
			result = pst.executeQuery(ConnectInit.queries
					.getProperty("securityFtp_get_security_token_and_triker"));
			while (result.next()) {
				String triker = (result.getString(3) + result.getString(2));
				security_list_triker.put(result.getString(1), triker.trim());
			}
			result.close();
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		}
		return security_list_triker;
	}
	/**
	 * @return Returns the Hashtable showing list of Scripname values.
	 */
	public Hashtable getScripname(Connect con, Connection connection) {
		Hashtable scrip_name = new Hashtable();
		Statement pst = null;
		ResultSet result = null;
		try {
			pst = connection.createStatement();
			result = pst.executeQuery(ConnectInit.queries
					.getProperty("scrip_name_from_securitydata"));
			while (result.next()) {
				String scripname = result.getString(2);
				scrip_name.put(result.getString(1), scripname.trim());
			}
			result.close();
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		}
		return scrip_name;
	}
	/**
	 * @return Returns the Hashtable showing list of tis values.
	 */
	public static Hashtable getTisList(int type, String exchange) {
		Connect con = ConnectInit.getConnect();
		Connection connection=null;
		int count = 0;
		if (connection == null) {
			connection=con.getdbConnection();
		}
		Hashtable tislist1 = new Hashtable();
		Hashtable tislist_id1 = new Hashtable();
	
		try {
			PreparedStatement pst = connection.prepareStatement(ConnectInit.queries
					.getProperty("get_tis_stockid_Ticker_wise"));
			pst.setString(1, exchange);
			ResultSet result = pst.executeQuery();
			while (result.next()) {
				tislist1.put(result.getString(3).trim(), (String) String
						.valueOf(result.getLong(2)));
				tislist_id1
						.put(result.getString(1).trim(), result.getString(3));
			}
		} catch (Exception sqlexp) {
			Logging.error("Error while getting tis " + sqlexp);
		}
		
		Logging.debug("size hashtable : " + tislist1.size());
		if (type == 2) {
			//returns the hashtable containing stockid and tis value
			return tislist_id1;
		}
		//returns the hashtable containing symbol and tis value
		return tislist1;
	}

	/**
	 * Insert the details of unimported stock prices in new issues.
	 */
	public static void MarkUnimportedStockPrices(Connection connection,
			String exch_id, String ticker, String date, String scrip_name,
			String query, Market mark) {
		try {
			Connect con = ConnectInit.getConnect();

			Logging.debug("Inside insert Stock price unimported");
			//Logging.getDebug("Exchange id is "+exc_id+" key is "+key);
			PreparedStatement psmt = connection.prepareStatement(query);
			Logging.debug("in query psmt is " + psmt);
			psmt.setString(1, exch_id);//SE_ID (new issues stock exchange id).
			Logging.debug("exch_id is " + exch_id);
			psmt.setString(2, date);//date
			psmt.setString(3, ticker);//ticker code
			psmt.setString(4, scrip_name);//series (in case of NSEI)
			psmt.setFloat(5, mark.getCp());//closing value.
			psmt.setFloat(6, mark.getOp());//open value.
			psmt.setFloat(7, mark.getHp());//high value.
			psmt.setFloat(8, mark.getLp());//low value.
			psmt.setInt(9, mark.getTotal_traded_quant());//no of trades.
			psmt.executeUpdate();
			Logging.debug("after inserting values in new issues");

		} catch (Exception e) {
			Logging.error("Error : " + e.getMessage());
		}
	}

	/**
	 * to get annualised net_profit,net_woth and dividend.
	 * 
	 * @param stockid
	 * @return
	 */
	public static Vector getAveragePePbDivident(String stockid) {
		Vector v1 = new Vector();
		double adj_net_profit = 0.0, adj_net_worth = 0.0, adj_dividend = 0.0;
		double net_profit1 = 0.0, net_profit2 = 0.0, net_profit3 = 0.0, net_profit4 = 0.0, net_worth1 = 0.0, net_worth2 = 0.0, net_worth3 = 0.0, net_worth4 = 0.0, dividend1 = 0.0, dividend2 = 0.0, dividend3 = 0.0, dividend4 = 0.0;
		String to_date1 = null, to_date2 = null, to_date3 = null, to_date4 = null;
		Connect con = ConnectInit.getConnect();
		Connection connection=null;
		int count = 0;
		if (connection == null) {
			connection=con.getdbConnection();
		}
		try {
			PreparedStatement pst = connection.prepareStatement(ConnectInit.queries
					.getProperty("importPrice_get_pe_pb_dividend_quartely"));
			pst.setString(1, stockid);
			ResultSet result = pst.executeQuery();
			while (result.next()) {
				count++;
				switch (count) {
				case 1:
					net_profit1 = result.getDouble(1);
					dividend1 = result.getDouble(2);
					net_worth1 = result.getDouble(4);
					to_date1 = result.getString(6);
				case 2:
					net_profit2 = result.getDouble(1);
					dividend2 = result.getDouble(2);
					net_worth2 = result.getDouble(4);
					to_date2 = result.getString(6);
				case 3:
					net_profit3 = result.getDouble(1);
					dividend3 = result.getDouble(2);
					net_worth3 = result.getDouble(4);
					to_date3 = result.getString(6);
				case 4:
					net_profit4 = result.getDouble(1);
					dividend4 = result.getDouble(2);
					net_worth4 = result.getDouble(4);
					to_date4 = result.getString(6);
				}
			}
			String datem = null, date3m = null;
			switch (count) {
			case 1:
				datem = to_date1;
				adj_net_profit = net_profit1;
				adj_net_worth = net_worth1;
				adj_dividend = dividend1;
				for (int i = 1; i < 4; i++) {
					date3m = getDateQuaterly(datem);
					InsertAdjustedFinDetail(connection,adj_net_profit, adj_dividend,
							adj_net_worth, datem, date3m, stockid);
					datem = date3m;
				}
				break;
			case 2:
				datem = to_date2;
				adj_net_profit = (net_profit1 + net_profit2) / 2;
				adj_net_worth = (net_worth1 + net_worth2) / 2;
				adj_dividend = (dividend1 + dividend2) / 2;
				for (int i = 2; i < 4; i++) {
					date3m = getDateQuaterly(datem);
					InsertAdjustedFinDetail(connection,adj_net_profit, adj_dividend,
							adj_net_worth, datem, date3m, stockid);
					datem = date3m;
				}
				break;
			case 3:
				adj_net_profit = (net_profit1 + net_profit2 + net_profit3) / 3;
				adj_net_worth = (net_worth1 + net_worth2 + net_worth3) / 3;
				adj_dividend = (dividend1 + dividend2 + dividend3) / 3;
				datem = to_date3;
				for (int i = 3; i < 4; i++) {
					date3m = getDateQuaterly(datem);
					InsertAdjustedFinDetail(connection,adj_net_profit, adj_dividend,
							adj_net_worth, datem, date3m, stockid);
					datem = date3m;
				}
				break;
			case 4:
				adj_net_profit = (net_profit1 + net_profit2 + net_profit3 + net_profit4) / 4;
				adj_net_worth = (net_worth1 + net_worth2 + net_worth3 + net_worth4) / 4;
				adj_dividend = (dividend1 + dividend2 + dividend3 + dividend4) / 4;
				datem = to_date3;
				break;
			default:
				Logging.debug("In default case pe pb calculation");
				break;
			}
			Logging.debug("values in getAveragePePbDivident are");
			Logging.debug("adj_net_profit is " + adj_net_profit
					+ " adj_net_worth is " + adj_net_worth
					+ " adj_dividend is " + adj_dividend);
			v1.add(0, (new Double(adj_net_profit).toString()));
			v1.add(1, (new Double(adj_net_worth).toString()));
			v1.add(2, (new Double(adj_dividend).toString()));
		} catch (Exception e) {
			Logging.debug("Error : " + e.getMessage());
		}
		finally {
			try {
				if(connection!=null)
					connection.close();
			} catch (Exception e) {
				Logging.debug("Error : Unable to close Connection " + e.getMessage());
			}
		}
		return v1;
	}

	public static String getDateQuaterly(String date) {
		Logging.debug("before date " + date);
		String day = date.trim().substring(0, 2);
		String month = date.trim().substring(3, 5);
		String year = date.trim().substring(6, 10);
		int dd = new Integer(day).intValue();
		int mm = new Integer(month).intValue();
		int yy = new Integer(year).intValue();
		if (mm == 2 && dd >= 28)
			day = "28";
		mm = mm + 3;
		if (mm > 12) {
			mm = mm - 12;
			yy = yy + 1;
		}
		month = new Integer(mm).toString();
		year = new Integer(yy).toString();
		if (month.length() < 2)
			month = "0" + month;

		String date1 = day + "-" + month + "-" + yy;
		Logging.debug("3 month before date is " + date1);
		return date1;
	}

	public static void InsertAdjustedFinDetail(Connection connection,double net_profit,
			double dividend, double net_worth, String fdate, String tdate,
			String stkid) {
		Connect con = ConnectInit.getConnect();
		int count = 0;
		try {
			PreparedStatement pst = connection
					.prepareStatement(ConnectInit.queries
							.getProperty("insert_into_financial_detail_adjusted_values"));
			pst.setString(1, stkid);
			pst.setDouble(2, net_profit);
			pst.setDouble(3, dividend);
			pst.setString(4, fdate);
			pst.setString(5, tdate);
			pst.setDouble(6, net_worth);
			pst.executeUpdate();
			pst.close();
		} catch (Exception e) {
			Logging.debug("Error : " + e.getMessage());
		}
	}
}