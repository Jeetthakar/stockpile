/*
 * Created on Oct 9, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.jfree.chart.demo.servlet.AdjustDecimal;

import com.harrier.ftp.Bhav;
import com.harrier.initializeation.ConnectInit;
import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;

/**
 * @author Administrator
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */

public class PopFileDialog {
	static Logger Logging = Logger.getLogger(PopFileDialog.class);
	// private String exchange_id;
	// private String extension="";
	String start = null;
	private static String str_fileName = null;
	public String errorMessage = null;
	private app.CStockPriceComponents priceComp;
	private app.CStockPriceComponentsfi priceComp1;// = new
													// CStockPriceComponents();
	public Hashtable table = new Hashtable(2000);
	public Hashtable mktcap_list = new Hashtable(2000);
	public Hashtable reject_per_list = new Hashtable(2000);
	public Hashtable stkid_list = null;
	public Hashtable last_value_list = null;
	private Hashtable tislist1 = new Hashtable();
	private Hashtable tislist_id1 = new Hashtable();
	private Hashtable tislist = null;
	private Hashtable stockid_cad_list = null;
	private ArrayList arr_PriceData = null;
	private ArrayList arr_PriceData1 = null;
	private double total = 0.0;
	private static int flag = 0;
	private static String filedate = null;
	private static String correctedFile = null;
	private static int arrlength = 0;
	private static int nfilename = 0;
	private static int extcheck = 0;
	public static String filetype = null;
	public static String hist_date = null;
	public static String exchangeid = null;

	protected void finalize() {

		// this.exchange_id = null;
		// this.extension = null;
		this.start = null;
		this.errorMessage = null;
		this.priceComp = null;
		this.table = null;
		this.tislist1 = null;
		this.tislist_id1 = null;
		this.tislist = null;
		this.stkid_list = null;
		this.arr_PriceData = null;
		// this.total =null;
		this.buffer12 = null;
	}

	private static int inrformat = 0;
	private static Vector v_alert_reject = new Vector();
	static Connect con1 = ConnectInit.getConnect();
	StringBuffer buffer12 = null;

	/**
	 * @return Returns the exchange_id.
	 */
	/*
	 * public String getExchange_id() { return exchange_id; }
	 *//**
	 * @param exchange_id
	 *            The exchange_id to set.
	 */
	/*
	 * public void setExchange_id(String exchange_id) { this.exchange_id =
	 * exchange_id; }
	 */
	/**
	 * @return Returns the priceComp.
	 */
	public CStockPriceComponents getPriceComp() {
		return priceComp;
	}

	/**
	 * @param priceComp
	 *            The priceComp to set.
	 */
	public void setPriceComp(CStockPriceComponents priceComp) {
		this.priceComp = priceComp;
	}

	/**
	 * @return Returns the str_fileName.
	 */
	public String getStr_fileName() {
		return str_fileName;
	}

	public void setStr_fileName(String str_file) {
		str_fileName = str_file;
		Logging.info("File Name PopFile " + str_fileName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.filechooser.FileSystemView#createNewFolder(java.io.File)
	 */
	public void showdlg() {
		/*
		 * JFileChooser j = new JFileChooser(); j.addChoosableFileFilter(new
		 * MyFilter()); j.showOpenDialog(new JFrame());
		 */
		// str_fileName = "E:/bhavcopy.csv";//j.getSelectedFile().getPath();
		/* Logging.debug(str_fileName); */
	}

	/**
	 * check for file extension i.e. csv or xml or dbf.
	 */
	/*
	 * private boolean checkExt(){ String temp =""; temp =
	 * str_fileName.substring(str_fileName.lastIndexOf(".")+1).trim();
	 * Logging.getDebug(temp); if((temp.equals("csv"))||(temp.equals("dbf"))
	 * ||(temp.equals("xml"))){ //extension = temp; return true; } else return
	 * false; }
	 */

	/**
	 * method to display the file content
	 */

	public StringBuffer display(String extenstion, String exchange_id,
			String fileName) {
		// AdjustDecimal ad=new AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		flag = 0;
		extcheck = 0;
		inrformat = 0;
		long start, end;
		start = System.currentTimeMillis();
		Logging.debug("start time is h9ighgfh" + start);
		loadDataFromPriceFile(extenstion, fileName);
		StringBuffer buffer = new StringBuffer();
		// Logging.getDebug("Inside display extcheck "+extcheck+" inrformat "+inrformat);
		try {
			// Logging.getDebug("exchange id is "+exchange_id);
			tislist = getTisList(1, exchange_id);
			Enumeration e = tislist.keys();
			Logging.debug("Tis array is " + tislist.size());
			int count = -1;
			String style = null;
			total = 0.0;
			if (flag == 2) {
				count++;
				if (count == 0) {
					buffer.append("<tr width = '100%'>");
					if (count == 0)
						style = "gridStyle-header color='blue' ";
					else
						style = "tab color='black' ";
					buffer.append("</td>");
					buffer.append("<td width='7%' align='center' class='"
							+ style + "' valign='center' height='12'>");
					buffer.append("SYMBOL");// symbol.
					buffer.append("</td>");
					buffer.append("</td>");
					buffer.append("<td width='9%' align='center' class='"
							+ style + "' valign='center' height='12'>");
					buffer.append("Price(L.T.P.)");// closing price taken as
													// last.
					buffer.append("</td>");
					buffer.append("<td width='9%' align='center' class='"
							+ style + "' valign='center' height='12'>");
					buffer.append("OUTSTANDING SHARES");// tis used as traded
														// volume to display.
					buffer.append("</td>");
					buffer.append("<td width='9%' align='center' class='"
							+ style + "' valign='center' height='12'>");
					buffer.append("MARKET CAP.");// market capital value.
					buffer.append("</td>");
					// buffer.append("</font>");
					buffer.append("</tr>");

				}
			}
			// Logging.getDebug(" arr_PriceData.size() "+arr_PriceData.size());
			for (int i = 0; i < arr_PriceData.size(); i++) {

				priceComp = (CStockPriceComponents) arr_PriceData.get(i);
				count++;

				buffer.append("<tr width = '100%'>");
				if (count == 0)
					style = "gridStyle-header color='blue' ";
				else
					style = "tab color='black' ";
				String str1 = priceComp.getStr_symbol();
				buffer.append("<td width='9%' align=left class='" + style
						+ "' valign= center height='12'>");
				if (flag == 2) {
					String symbol = priceComp.getStr_series().toString() + str1;
					Logging.debug("symbol is " + symbol);
					buffer.append(symbol);// Symbol
				} else {
					buffer.append(str1);// Symbol
				}
				if (tislist.containsKey(str1)) {
					String str2 = tislist.get(str1).toString();
					priceComp.setTis(str2);
				}
				Logging.debug("flag is " + flag);
				if (flag == 2) {
					Logging.debug("inside flag is 2");
					buffer.append("</td>");
					buffer.append("<td width='7%' align='right' class='"
							+ style + "' valign='center' height='12'>");
					// buffer.append("<font size='"+size+ "' face='"+style+"' "
					// +
					// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
					if (count != 0) {
						String last = priceComp.getStr_last();
						last = AdjustDecimal.ArrangeAsNumeric(last);
						buffer.append(last);// Last
					}
					// priceComp.setStr_last(arr[i-1]);
					// buffer.append("</font>");
					buffer.append("</td>");
					buffer.append("</td>");
					buffer.append("<td width='9%' align='right' class='"
							+ style + "' valign='center' height='12'>");
					// buffer.append("<font size='"+size+ "' face='"+style+"' "
					// +
					// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
					if (count != 0) {
						String trvol1 = priceComp.getStr_totTradedVol();
						trvol1 = ad.shareholdingpatt(trvol1);
						buffer.append(AdjustDecimal.ArrangeAsNumeric(trvol1));// TOtal
																				// traded
																				// quantity
					}
					// buffer.append("</font>");
					buffer.append("</td>");
					buffer.append("<td width='12%' align='right' class='"
							+ style + "' valign='center' height='12'>");
					// buffer.append("<font size='"+size+ "' face='"+style+"' "
					// +
					// "bgcolor='"+bgcol+"' color='"+fcol+"'>");

					if (count != 0) {
						String str3 = priceComp.getMkt_cap();
						mktcap_list.put(priceComp.getStr_symbol(), str3);
						total = total + (double) Double.parseDouble(str3);
						// priceComp.setMkt_cap(str3);
						// Logging.getDebug(str3);
						str3 = ad.shareholdingpatt(str3);
						str3 = ad.indexcompose(str3);
						str3 = AdjustDecimal.ArrangeAsNumeric(str3);
						buffer.append(str3);// market cap.
						filedate = hist_date;
						// buffer.append("</font>");
						buffer.append("</td>");
						// buffer.append("</font>");
						buffer.append("</tr>");
						table.put(priceComp.getStr_symbol(), priceComp);
					}
				} else {
					if (flag != 1) {
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='5%' align='left' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");

						buffer.append(priceComp.getStr_series());// Series
						// priceComp.setStr_series(arr[i-1]);
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='7%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						String open = priceComp.getStr_open();
						if (count != 0) {
							open = AdjustDecimal.ArrangeAsNumeric(open);
						}
						buffer.append(open);// Open
						// priceComp.setStr_open(arr[i-1]);
						// buffer.append("</font>");
						// buffer.append("<p></p>");
						buffer.append("</td>");
						buffer.append("<td width='7%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						String high = priceComp.getStr_high();
						if (count != 0) {
							high = AdjustDecimal.ArrangeAsNumeric(high);
						}
						buffer.append(high);// High
						// priceComp.setStr_high(arr[i-1]);
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='7%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						String low = priceComp.getStr_low();
						if (count != 0) {
							low = AdjustDecimal.ArrangeAsNumeric(low);
						}
						buffer.append(low);// Low
						// priceComp.setStr_low(arr[i-1]);
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='7%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						String close = priceComp.getStr_close();
						if (count != 0) {
							close = AdjustDecimal.ArrangeAsNumeric(close);
						}
						buffer.append(close);// Close
						// priceComp.setStr_close(arr[i-1]);
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='7%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						String last = priceComp.getStr_last();
						if (count != 0) {
							last = AdjustDecimal.ArrangeAsNumeric(last);
						}
						buffer.append(last);// Last
						// priceComp.setStr_last(arr[i-1]);
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='9%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						String pclose = priceComp.getStr_prevClose();
						if (count != 0) {
							pclose = AdjustDecimal.ArrangeAsNumeric(pclose);
						}
						buffer.append(pclose);// Previous
						// priceComp.setStr_prevClose(arr[i-1]);
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='9%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						if (count != 0) {
							buffer.append(AdjustDecimal
									.ArrangeAsNumeric(priceComp
											.getStr_totTradedQty()));// TOtal
																		// traded
																		// quantity
						} else {
							buffer.append(priceComp.getStr_totTradedQty());// TOtal
																			// traded
																			// quantity
						}
						// priceComp.setStr_totTradedQty(arr[i-1]);
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='9%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						String trvol1 = priceComp.getStr_totTradedVol();
						trvol1 = ad.shareholdingpatt(trvol1);
						if (count != 0) {
							buffer.append(AdjustDecimal
									.ArrangeAsNumeric(trvol1));// TOtal traded
																// quantity
						} else {
							buffer.append(trvol1);// TOtal traded quantity
						}
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='12%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						if (count == 0) {
							buffer.append("MARKET CAP.");// market cap.
							// priceComp.setMkt_cap("10");
							// buffer.append("</font>");
							buffer.append("</td>");
							buffer.append("<td width='12%' align='right' class='"
									+ style + "' valign='center' height='12'>");
							// buffer.append("<font size='"+size+
							// "' face='"+style+"' " +
							// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
							String time = priceComp.getStr_date();// timestamp
							buffer.append(time);// timestamp
						} else {
							String str3 = priceComp.getMkt_cap();
							mktcap_list.put(priceComp.getStr_symbol(), str3);
							total = total + (double) Double.parseDouble(str3);
							// priceComp.setMkt_cap(str3);
							// Logging.getDebug(str3);
							str3 = ad.shareholdingpatt(str3);
							str3 = ad.indexcompose(str3);
							str3 = AdjustDecimal.ArrangeAsNumeric(str3);
							buffer.append(str3);// market cap.
							// buffer.append("</font>");
							buffer.append("</td>");
							buffer.append("<td width='12%' align='right' class='"
									+ style + "' valign='center' height='12'>");
							// buffer.append("<font size='"+size+
							// "' face='"+style+"' " +
							// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
							String time = priceComp.getStr_date();// timestamp
							// Logging.getDebug("time "+time);
							filedate = time;
							buffer.append(time);// timestamp
						}
					} else {
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='9%' align='left' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						String csp = priceComp.getCusip();
						buffer.append(csp);// cusip code
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='9%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						String open = priceComp.getStr_open();
						if (count != 0) {
							open = AdjustDecimal.ArrangeAsNumeric(open);
						}
						buffer.append(open);// Open
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='9%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// / "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						String high = priceComp.getStr_high();
						if (count != 0)
							high = AdjustDecimal.ArrangeAsNumeric(high);
						buffer.append(high);// High
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='9%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						String low = priceComp.getStr_low();
						if (count != 0)
							low = AdjustDecimal.ArrangeAsNumeric(low);
						buffer.append(low);// Low
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='9%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						priceComp.setStr_close("0");
						String last = priceComp.getStr_last();
						if (count != 0)
							last = AdjustDecimal.ArrangeAsNumeric(last);
						buffer.append(last);// Last
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='9%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						String net_change = priceComp.getNet_change();
						buffer.append(net_change);// net change
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='9%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						String trvol = priceComp.getStr_totTradedVol();
						if (count != 0) {
							trvol = ad.shareholdingpatt(trvol);
							trvol = AdjustDecimal.ArrangeAsNumeric(trvol);
						}
						buffer.append(trvol);// traded volume
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='16%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						if (count == 0) {
							buffer.append("MARKET CAP.");// market cap.
							// buffer.append("</font>");
							buffer.append("</td>");
							buffer.append("<td width='12%' align='right' class='"
									+ style + "' valign='center' height='12'>");
							// buffer.append("<font size='"+size+
							// "' face='"+style+"' " +
							// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
							String time = priceComp.getStr_date();// timestamp
							buffer.append(time);// timestamp
						} else {
							String str3 = priceComp.getMkt_cap();
							mktcap_list.put(priceComp.getStr_symbol(), str3);
							total = total + (double) Double.parseDouble(str3);
							// priceComp.setMkt_cap(str3);
							str3 = ad.shareholdingpatt(str3);
							str3 = ad.indexcompose(str3);
							str3 = AdjustDecimal.ArrangeAsNumeric(str3);
							buffer.append(str3);// market cap.
							// buffer.append("</font>");
							buffer.append("</td>");
							buffer.append("<td width='12%' align='right' class='"
									+ style + "' valign='center' height='12'>");
							// buffer.append("<font size='"+size+
							// "' face='"+style+"' " +
							// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
							String time = priceComp.getStr_date();// timestamp
							// Logging.getDebug("time "+time);
							filedate = time;
							// PopFileDialogNewStock.filedate=filedate;
							buffer.append(time);// timestamp
						}
					}

					// buffer.append("</font>");
					buffer.append("</td>");
					// buffer.append("</font>");
					buffer.append("</tr>");
					if (exchange_id.equals("84")) {
						table.put(
								priceComp.getStr_series()
										+ priceComp.getStr_symbol(), priceComp);
					} else {
						table.put(priceComp.getStr_symbol(), priceComp);
					}
				}
			}

			if (count > -1) {
				String tmcv = ad.shareholdingpatt(total);
				/*
				 * fcol = "blue";style = "Arial Black"; bgcol = "#eeefff";size =
				 * "2";
				 */
				if (flag == 2) {
					Logging.debug("in flag is 2");
					buffer.append("<tr>");
					buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
					buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
				} else {
					if (flag == 1) {
						Logging.debug("in column count 10");
						buffer.append("<tr>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
					} else {
						Logging.debug("in column count 12");
						buffer.append("<tr>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
						;
					}
				}
				// buffer.append("<font size='"+size+ "' face='"+style+"' " +
				// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
				buffer.append("<td width='9%' class='gridStyle-header' color='blue' align=left valign= center height='12'>");
				// buffer.append("<font size='"+size+ "' face='"+style+"' " +
				// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
				buffer.append("Total Mkt.Cap.");// label for total market cap.
				// buffer.append("</font>");
				buffer.append("</td>");
				buffer.append("<td width='12%' align='right' class='gridStyle-header' color='blue' valign='center' height='12'>");
				// buffer.append("<font size='"+size+ "' face='"+style+"' " +
				// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
				tmcv = ad.shareholdingpatt(tmcv);
				tmcv = ad.indexcompose(tmcv);
				tmcv = AdjustDecimal.ArrangeAsNumeric(tmcv);
				buffer.append(tmcv);// display value for total market cap.
				// buffer.append("</font>");
				buffer.append("</td>");
				if (flag != 2) {
					buffer.append("<td width='12%' align='right' class='gridStyle-header' valign='center' height='12'>");
					// buffer.append("<font size='"+size+ "' face='"+style+"' "
					// +
					// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
					buffer.append("&nbsp;</td></tr>");
				} else {
					buffer.append("</tr>");
				}
				Logging.info("Total mky cap " + total);
			}
			// str_fileName = null;
		} catch (Exception e) {
			errorMessage = e.getMessage();
			Logging.debug(e.getMessage());
			e.getStackTrace();
			return null;
		}
		// Logging.debug(table);
		errorMessage = "file loaded successfully";
		end = System.currentTimeMillis();
		Logging.debug("table size is " + table.size());
		Logging.info("time required to display is " + (end - start)
				+ "miliSecs");
		return buffer;
	}

	public StringBuffer displaydbf(String extenstion, String exchange_id,
			String fileName) {
		// AdjustDecimal ad=new AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		flag = 0;
		extcheck = 0;
		inrformat = 0;
		long start, end;
		start = System.currentTimeMillis();
		Logging.debug("start time is h9ighgfh" + start);
		loadDataFromPriceFile(extenstion, fileName);
		StringBuffer buffer = new StringBuffer();
		// Logging.getDebug("Inside display extcheck "+extcheck+" inrformat "+inrformat);
		try {
			// Logging.getDebug("exchange id is "+exchange_id);
			tislist = getTisList(1, exchange_id);
			Enumeration e = tislist.keys();
			Logging.debug("Tis array is " + tislist.size());
			int count = -1;
			String style = null;
			total = 0.0;
			if (flag == 2) {
				count++;
				if (count == 0) {
					buffer.append("<tr width = '100%'>");
					if (count == 0)
						style = "gridStyle-header color='blue' ";
					else
						style = "tab color='black' ";
					buffer.append("</td>");
					buffer.append("<td width='7%' align='center' class='"
							+ style + "' valign='center' height='12'>");
					buffer.append("SYMBOL");// symbol.
					buffer.append("</td>");
					buffer.append("</td>");
					buffer.append("<td width='9%' align='center' class='"
							+ style + "' valign='center' height='12'>");
					buffer.append("Price(L.T.P.)");// closing price taken as
													// last.
					buffer.append("</td>");
					buffer.append("<td width='9%' align='center' class='"
							+ style + "' valign='center' height='12'>");
					buffer.append("OUTSTANDING SHARES");// tis used as traded
														// volume to display.
					buffer.append("</td>");
					buffer.append("<td width='9%' align='center' class='"
							+ style + "' valign='center' height='12'>");
					buffer.append("MARKET CAP.");// market capital value.
					buffer.append("</td>");
					// buffer.append("</font>");
					buffer.append("</tr>");

				}
			}
			// Logging.getDebug(" arr_PriceData.size() "+arr_PriceData.size());
			for (int i = 0; i < arr_PriceData.size(); i++) {

				priceComp = (CStockPriceComponents) arr_PriceData.get(i);
				count++;

				buffer.append("<tr width = '100%'>");
				if (count == 0)
					style = "gridStyle-header color='blue' ";
				else
					style = "tab color='black' ";
				String str1 = priceComp.getStr_symbol();
				buffer.append("<td width='9%' align=left class='" + style
						+ "' valign= center height='12'>");
				if (flag == 2) {
					String symbol = priceComp.getStr_series().toString() + str1;
					Logging.debug("symbol is " + symbol);
					buffer.append(symbol);// Symbol
				} else {
					buffer.append(str1);// Symbol
				}
				if (tislist.containsKey(str1)) {
					String str2 = tislist.get(str1).toString();
					priceComp.setTis(str2);
				}
				Logging.debug("flag is " + flag);
				if (flag == 2) {
					Logging.debug("inside flag is 2");
					buffer.append("</td>");
					buffer.append("<td width='7%' align='right' class='"
							+ style + "' valign='center' height='12'>");
					// buffer.append("<font size='"+size+ "' face='"+style+"' "
					// +
					// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
					if (count != 0) {
						String last = priceComp.getStr_last();
						last = AdjustDecimal.ArrangeAsNumeric(last);
						buffer.append(last);// Last
					}
					// priceComp.setStr_last(arr[i-1]);
					// buffer.append("</font>");
					buffer.append("</td>");
					buffer.append("</td>");
					buffer.append("<td width='9%' align='right' class='"
							+ style + "' valign='center' height='12'>");
					// buffer.append("<font size='"+size+ "' face='"+style+"' "
					// +
					// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
					if (count != 0) {
						String trvol1 = priceComp.getStr_totTradedVol();
						trvol1 = ad.shareholdingpatt(trvol1);
						buffer.append(AdjustDecimal.ArrangeAsNumeric(trvol1));// TOtal
																				// traded
																				// quantity
					}
					// buffer.append("</font>");
					buffer.append("</td>");
					buffer.append("<td width='12%' align='right' class='"
							+ style + "' valign='center' height='12'>");
					// buffer.append("<font size='"+size+ "' face='"+style+"' "
					// +
					// "bgcolor='"+bgcol+"' color='"+fcol+"'>");

					if (count != 0) {
						String str3 = priceComp.getMkt_cap();
						mktcap_list.put(priceComp.getStr_symbol(), str3);
						total = total + (double) Double.parseDouble(str3);
						// priceComp.setMkt_cap(str3);
						// Logging.getDebug(str3);
						str3 = ad.shareholdingpatt(str3);
						str3 = ad.indexcompose(str3);
						str3 = AdjustDecimal.ArrangeAsNumeric(str3);
						buffer.append(str3);// market cap.
						filedate = hist_date;
						// buffer.append("</font>");
						buffer.append("</td>");
						// buffer.append("</font>");
						buffer.append("</tr>");
						table.put(priceComp.getStr_symbol(), priceComp);
					}
				} else {
					if (flag != 1) {
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='5%' align='left' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");

						buffer.append(priceComp.getStr_series());// Series
						// priceComp.setStr_series(arr[i-1]);
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='7%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						String open = priceComp.getStr_open();
						if (count != 0) {
							open = AdjustDecimal.ArrangeAsNumeric(open);
						}
						buffer.append(open);// Open
						// priceComp.setStr_open(arr[i-1]);
						// buffer.append("</font>");
						// buffer.append("<p></p>");
						buffer.append("</td>");
						buffer.append("<td width='7%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						String high = priceComp.getStr_high();
						if (count != 0) {
							high = AdjustDecimal.ArrangeAsNumeric(high);
						}
						buffer.append(high);// High
						// priceComp.setStr_high(arr[i-1]);
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='7%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						String low = priceComp.getStr_low();
						if (count != 0) {
							low = AdjustDecimal.ArrangeAsNumeric(low);
						}
						buffer.append(low);// Low
						// priceComp.setStr_low(arr[i-1]);
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='7%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						String close = priceComp.getStr_close();
						if (count != 0) {
							close = AdjustDecimal.ArrangeAsNumeric(close);
						}
						buffer.append(close);// Close
						// priceComp.setStr_close(arr[i-1]);
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='7%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						String last = priceComp.getStr_last();
						if (count != 0) {
							last = AdjustDecimal.ArrangeAsNumeric(last);
						}
						buffer.append(last);// Last
						// priceComp.setStr_last(arr[i-1]);
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='9%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						String pclose = priceComp.getStr_prevClose();
						if (count != 0) {
							pclose = AdjustDecimal.ArrangeAsNumeric(pclose);
						}
						buffer.append(pclose);// Previous
						// priceComp.setStr_prevClose(arr[i-1]);
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='9%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						if (count != 0) {
							buffer.append(AdjustDecimal
									.ArrangeAsNumeric(priceComp
											.getStr_totTradedQty()));// TOtal
																		// traded
																		// quantity
						} else {
							buffer.append(priceComp.getStr_totTradedQty());// TOtal
																			// traded
																			// quantity
						}
						// priceComp.setStr_totTradedQty(arr[i-1]);
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='9%' align='right' class='"
								+ style + "' valign='center' height='12'>");

						if (count != 0) {
							double trvol = ((double) Double
									.parseDouble(priceComp.getStr_close()))
									* ((double) Double.parseDouble(priceComp
											.getStr_totTradedQty()));
							priceComp.setStr_totTradedVol("" + trvol);
							buffer.append(trvol);// TOtal traded quantity
						} else {
							buffer.append("Tol_Trated_vol");// TOtal traded
															// volume
						}
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='12%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						if (count == 0) {
							buffer.append("MARKET CAP.");// market cap.
							// priceComp.setMkt_cap("10");
							// buffer.append("</font>");
							buffer.append("</td>");
							buffer.append("<td width='12%' align='right' class='"
									+ style + "' valign='center' height='12'>");
							// buffer.append("<font size='"+size+
							// "' face='"+style+"' " +
							// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
							String time = "TIMESTAMP";// timestamp
							buffer.append(time);// timestamp
						} else {
							String str3 = priceComp.getMkt_cap();
							mktcap_list.put(priceComp.getStr_symbol(), str3);
							total = total + (double) Double.parseDouble(str3);
							// priceComp.setMkt_cap(str3);
							// Logging.getDebug(str3);
							str3 = ad.shareholdingpatt(str3);
							str3 = ad.indexcompose(str3);
							str3 = AdjustDecimal.ArrangeAsNumeric(str3);
							buffer.append(str3);// market cap.
							// buffer.append("</font>");
							buffer.append("</td>");
							buffer.append("<td width='12%' align='right' class='"
									+ style + "' valign='center' height='12'>");
							// buffer.append("<font size='"+size+
							// "' face='"+style+"' " +
							// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
							String time = priceComp.getStr_date();// timestamp
							// Logging.getDebug("time "+time);
							filedate = time;
							buffer.append(time);// timestamp
						}
					} else {
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='9%' align='left' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						String csp = priceComp.getCusip();
						buffer.append(csp);// cusip code
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='9%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						String open = priceComp.getStr_open();
						if (count != 0) {
							open = AdjustDecimal.ArrangeAsNumeric(open);
						}
						buffer.append(open);// Open
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='9%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// / "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						String high = priceComp.getStr_high();
						if (count != 0)
							high = AdjustDecimal.ArrangeAsNumeric(high);
						buffer.append(high);// High
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='9%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						String low = priceComp.getStr_low();
						if (count != 0)
							low = AdjustDecimal.ArrangeAsNumeric(low);
						buffer.append(low);// Low
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='9%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						priceComp.setStr_close("0");
						String last = priceComp.getStr_last();
						if (count != 0)
							last = AdjustDecimal.ArrangeAsNumeric(last);
						buffer.append(last);// Last
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='9%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						String net_change = priceComp.getNet_change();
						buffer.append(net_change);// net change
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='9%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						String trvol = priceComp.getStr_totTradedVol();
						if (count != 0) {
							trvol = ad.shareholdingpatt(trvol);
							trvol = AdjustDecimal.ArrangeAsNumeric(trvol);
						}
						buffer.append(trvol);// traded volume
						// buffer.append("</font>");
						buffer.append("</td>");
						buffer.append("<td width='16%' align='right' class='"
								+ style + "' valign='center' height='12'>");
						// buffer.append("<font size='"+size+
						// "' face='"+style+"' " +
						// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
						if (count == 0) {
							buffer.append("MARKET CAP.");// market cap.
							// buffer.append("</font>");
							buffer.append("</td>");
							buffer.append("<td width='12%' align='right' class='"
									+ style + "' valign='center' height='12'>");
							// buffer.append("<font size='"+size+
							// "' face='"+style+"' " +
							// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
							String time = priceComp.getStr_date();// timestamp
							buffer.append(time);// timestamp
						} else {
							String str3 = priceComp.getMkt_cap();
							mktcap_list.put(priceComp.getStr_symbol(), str3);
							total = total + (double) Double.parseDouble(str3);
							// priceComp.setMkt_cap(str3);
							str3 = ad.shareholdingpatt(str3);
							str3 = ad.indexcompose(str3);
							str3 = AdjustDecimal.ArrangeAsNumeric(str3);
							buffer.append(str3);// market cap.
							// buffer.append("</font>");
							buffer.append("</td>");
							buffer.append("<td width='12%' align='right' class='"
									+ style + "' valign='center' height='12'>");
							// buffer.append("<font size='"+size+
							// "' face='"+style+"' " +
							// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
							String time = priceComp.getStr_date();// timestamp
							// Logging.getDebug("time "+time);
							filedate = time;
							// PopFileDialogNewStock.filedate=filedate;
							buffer.append(time);// timestamp
						}
					}

					// buffer.append("</font>");
					buffer.append("</td>");
					// buffer.append("</font>");
					buffer.append("</tr>");
					if (exchange_id.equals("84")) {
						table.put(
								priceComp.getStr_series()
										+ priceComp.getStr_symbol(), priceComp);
					} else {
						table.put(priceComp.getStr_symbol(), priceComp);
					}
				}
			}

			if (count > -1) {
				String tmcv = ad.shareholdingpatt(total);
				/*
				 * fcol = "blue";style = "Arial Black"; bgcol = "#eeefff";size =
				 * "2";
				 */
				if (flag == 2) {
					Logging.debug("in flag is 2");
					buffer.append("<tr>");
					buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
					buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
				} else {
					if (flag == 1) {
						Logging.debug("in column count 10");
						buffer.append("<tr>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
					} else {
						Logging.debug("in column count 12");
						buffer.append("<tr>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
						buffer.append("<td class='gridStyle-header'>&nbsp;</td>");
						;
					}
				}
				// buffer.append("<font size='"+size+ "' face='"+style+"' " +
				// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
				buffer.append("<td width='9%' class='gridStyle-header' color='blue' align=left valign= center height='12'>");
				// buffer.append("<font size='"+size+ "' face='"+style+"' " +
				// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
				buffer.append("Total Mkt.Cap.");// label for total market cap.
				// buffer.append("</font>");
				buffer.append("</td>");
				buffer.append("<td width='12%' align='right' class='gridStyle-header' color='blue' valign='center' height='12'>");
				// buffer.append("<font size='"+size+ "' face='"+style+"' " +
				// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
				tmcv = ad.shareholdingpatt(tmcv);
				tmcv = ad.indexcompose(tmcv);
				tmcv = AdjustDecimal.ArrangeAsNumeric(tmcv);
				buffer.append(tmcv);// display value for total market cap.
				// buffer.append("</font>");
				buffer.append("</td>");
				if (flag != 2) {
					buffer.append("<td width='12%' align='right' class='gridStyle-header' valign='center' height='12'>");
					// buffer.append("<font size='"+size+ "' face='"+style+"' "
					// +
					// "bgcolor='"+bgcol+"' color='"+fcol+"'>");
					buffer.append("&nbsp;</td></tr>");
				} else {
					buffer.append("</tr>");
				}
				Logging.info("Total mky cap " + total);
			}
			// str_fileName = null;
		} catch (Exception e) {
			errorMessage = e.getMessage();
			Logging.debug(e.getMessage());
			e.getStackTrace();
			return null;
		}
		// Logging.debug(table);
		errorMessage = "file loaded successfully";
		end = System.currentTimeMillis();
		Logging.debug("table size is " + table.size());
		Logging.info("time required to display is " + (end - start)
				+ "miliSecs");
		return buffer;
	}

	public StringBuffer displayfi(String extenstion, String exchange_id,
			String fileName) {
		// AdjustDecimal ad=new AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		flag = 0;
		extcheck = 0;
		inrformat = 0;
		long start, end;
		start = System.currentTimeMillis();
		Logging.debug("start time is h9ighgfh" + start);
		loadDataFromPriceFilefi(extenstion, fileName);
		StringBuffer buffer = new StringBuffer();

		try {
			int count = -1;
			String style = null;
			total = 0.0;

			for (int i = 0; i < arr_PriceData1.size(); i++) {

				priceComp1 = (CStockPriceComponentsfi) arr_PriceData1.get(i);
				count++;

				buffer.append("<tr width = '100%'>");
				if (count == 0)
					style = "gridStyle-header color='blue' ";
				else
					style = "tab color='black' ";
				String str1 = priceComp1.getStr_symbol();
				buffer.append("<td width='7%' align=left class='" + style
						+ "' valign= center height='12'>");

				buffer.append(str1);// Symbol

				if (flag != 1) {
					buffer.append("</td>");
					buffer.append("<td width='14%' align='left' class='"
							+ style + "' valign='center' height='12'>");

					buffer.append(priceComp1.getStr_series());// Name

					buffer.append("</td>");
					buffer.append("<td width='7%' align='right' class='"
							+ style + "' valign='center' height='12'>");

					String open = priceComp1.getStr_open();
					if (count != 0) {
						open = AdjustDecimal.ArrangeAsNumeric(open);
					}
					buffer.append(open);// Bid

					buffer.append("</td>");
					buffer.append("<td width='7%' align='right' class='"
							+ style + "' valign='center' height='12'>");

					String high = priceComp1.getStr_high();
					if (count != 0) {
						high = AdjustDecimal.ArrangeAsNumeric(high);
					}
					buffer.append(high);// Ask

					buffer.append("</td>");
					buffer.append("<td width='7%' align='right' class='"
							+ style + "' valign='center' height='12'>");

					String low = priceComp1.getStr_low();
					if (count != 0) {
						low = AdjustDecimal.ArrangeAsNumeric(low);
					}
					buffer.append(low);// LTP

					buffer.append("</td>");
					buffer.append("<td width='7%' align='right' class='"
							+ style + "' valign='center' height='12'>");

					String close = priceComp1.getStr_close();
					if (count != 0) {
						close = AdjustDecimal.ArrangeAsNumeric(close);
					}
					buffer.append(close);// Close

					buffer.append("</td>");
					buffer.append("<td width='7%' align='right' class='"
							+ style + "' valign='center' height='12'>");

					String last = priceComp1.getStr_last();
					if (count != 0) {
						last = AdjustDecimal.ArrangeAsNumeric(last);
					}
					buffer.append(last);// Acured Intrest

					buffer.append("</td>");
					buffer.append("<td width='9%' align='right' class='"
							+ style + "' valign='center' height='12'>");

					String pclose = priceComp1.getStr_prevClose();
					if (count != 0) {
						pclose = AdjustDecimal.ArrangeAsNumeric(pclose);
					}
					buffer.append(pclose);// nominal outsanding

					buffer.append("</td>");
					buffer.append("<td width='9%' align='right' class='"
							+ style + "' valign='center' height='12'>");
					buffer.append(priceComp1.getStr_date());// Date

					buffer.append("</td>");
				}
				buffer.append("</tr>");

				table.put(priceComp1.getStr_symbol(), priceComp1);
			}

		} catch (Exception e) {
			errorMessage = e.getMessage();
			Logging.debug(e.getMessage());
			e.getStackTrace();
			return null;
		}
		// Logging.debug(table);
		errorMessage = "file loaded successfully";
		end = System.currentTimeMillis();
		Logging.debug("table size is " + table.size());
		Logging.info("time required to display is " + (end - start)
				+ "miliSecs");
		return buffer;
	}

	/**
	 * method to read the file content for files with different extensions like
	 * csv,xml,dbf and to fill the hashtable wilh bhavcopy values along with
	 * market cap value.
	 */
	private boolean loadDataFromPriceFile(String extension, String str_fileName) {
		app.Testdate tdt = new app.Testdate();
		/*
		 * Logging.debug("inside loadDataFromPriceFile"); errorMessage = null;
		 * String temp =""; String path=Connect.getCoolMenuspath();
		 * //Logging.getDebug("path is : "+path); String str_dirName =
		 * path+"/CoolMenus/";/// //Logging.getDebug(str_dirName);
		 * if(str_fileName!=null) str_fileName = str_dirName + str_fileName;
		 * else{ nfilename=1; return false; } if(!checkExt()){ errorMessage =
		 * "Please choose *.csv/ *.xml/ " + "*.dbf/ *.xls file extension";
		 * Logging.getDebug(temp); extcheck=1; return false; }
		 */

		try {
			String symbolOld = "";
			arr_PriceData = new ArrayList();
			if (arr_PriceData.size() > 0)
				arr_PriceData.clear();
			int count1 = -1;
			if (extension.equals("csv")) {
				FileReader file = new FileReader(str_fileName);
				BufferedReader br = new BufferedReader(file);
				String str;
				while ((str = br.readLine()) != null) {
					Logging.debug("file line is " + str);
					count1++;
					priceComp = new CStockPriceComponents();
					String fcol = "", bgcol = "", size = "", style = "";
					String[] arr = str.split(",");
					// start=new Integer(arr.length).toString();
					Logging.debug("arr.length is " + arr.length);
					if ((arr.length != 11) && (arr.length != 13)
							&& (arr.length != 9) && (arr.length != 3)
							&& (arr.length != 16)) {
						inrformat = 1;
						return false;
					}
					if (arr.length == 11) {
						for (int i = 0; i < arr.length;) {
							String str1 = arr[i++];
							priceComp.setStr_symbol(str1);
							priceComp.setStr_series(arr[i++]);
							priceComp.setStr_open(arr[i++]);
							priceComp.setStr_high(arr[i++]);
							priceComp.setStr_low(arr[i++]);
							priceComp.setStr_close(arr[i++]);
							priceComp.setStr_last(arr[i++]);
							priceComp.setStr_prevClose(arr[i++]);
							priceComp.setStr_totTradedQty(arr[i++]);
							priceComp.setStr_totTradedVol(arr[i++]);
							String mcv = priceComp.getExchange_id();
							String time = arr[i++];
							// Logging.Logging.getDebug("time "+time);
							if (time == null || time.equals("0/0/0")
									|| time.equals("")) {
								time = "Invalid Date";
								// Logging.Logging.getDebug("In invalid date");
							}
							String date = "";
							if (count1 != 0) {
								if (!(time.equals("Invalid Date"))) {
									date = formatDate(time);
								}
								priceComp.setStr_date(date);
							} else {
								priceComp.setStr_date(time);
							}

						}
					}

					if (arr.length == 13) {
						for (int i = 0; i < arr.length;) {
							String str1 = arr[(i++)];
							symbolOld = str1;
							this.priceComp.setStr_symbol(str1);
							this.priceComp.setStr_series(arr[(i++)]);
							this.priceComp.setStr_open(arr[(i++)]);
							this.priceComp.setStr_high(arr[(i++)]);
							this.priceComp.setStr_low(arr[(i++)]);
							this.priceComp.setStr_close(arr[(i++)]);
							this.priceComp.setStr_last(arr[(i++)]);
							this.priceComp.setStr_prevClose(arr[(i++)]);
							this.priceComp.setStr_totTradedQty(arr[(i++)]);
							this.priceComp.setStr_totTradedVol(arr[(i++)]);
							String mcv = this.priceComp.getExchange_id();
							String time = arr[(i++)];
							i++;
							i++;

							if ((time == null) || (time.equals("0/0/0"))
									|| (time.equals(""))) {
								time = "Invalid Date";
							}

							String date = "";
							if (count1 != 0) {
								if (!time.equals("Invalid Date")) {
									date = formatDate(time);
								}
								this.priceComp.setStr_date(date);
							} else {
								this.priceComp.setStr_date(time);
							}
						}
					}

					if (arr.length == 16) {
						symbolOld = arr[2];
						String temp = "";
						if (!(symbolOld.equals("")) && (symbolOld != null)
								&& !(symbolOld.equals(" "))) {
							for (int i = 0; i < arr.length - 5;) {
								String str1 = arr[i++];
								priceComp.setStr_series(arr[i++]);
								priceComp.setStr_symbol(arr[i++]);
								temp = arr[i++];// SECURITY
								priceComp.setStr_prevClose(arr[i++]);

								priceComp.setStr_open(arr[i++]);
								priceComp.setStr_high(arr[i++]);
								priceComp.setStr_low(arr[i++]);
								temp = arr[i++];
								priceComp.setStr_close(temp);
								priceComp.setStr_totTradedVol(arr[i++]);
								priceComp.setStr_totTradedQty(arr[i++]);
								priceComp.setStr_last(temp);

								String mcv = priceComp.getExchange_id();
								/*
								 * String time=arr[i++];
								 * 
								 * //Logging.Logging.getDebug("time "+time);
								 * if(time==null || time.equals("0/0/0") ||
								 * time.equals("")){ time="Invalid Date";
								 * //Logging
								 * .Logging.getDebug("In invalid date"); }
								 */
								String date = "";
								if (count1 != 0) {
									SimpleDateFormat fr = new SimpleDateFormat(
											"dd-MM-yyyy");
									Date dt = new Date();
									dt.setDate(dt.getDate());
									date = fr.format(dt).toString();
									priceComp.setStr_date(date);
								} else {
									priceComp.setStr_date("Date");
								}
							}
						}
					}

					if (arr.length == 9) {
						flag = 1;
						for (int i = 0; i < arr.length;) {
							String str1 = arr[i++];
							priceComp.setStr_symbol(str1);
							priceComp.setCusip(arr[i++]);
							priceComp.setStr_open(arr[i++]);
							priceComp.setStr_high(arr[i++]);
							priceComp.setStr_low(arr[i++]);
							priceComp.setStr_close("0");
							String last = arr[i++];
							priceComp.setStr_last(last);// last
							String net_change = arr[i++]; // net change
							priceComp.setNet_change(net_change);
							priceComp.setStr_totTradedVol(arr[i++]);
							String time = arr[i++];
							// Logging.getDebug("time "+time);
							if (time == null || time.equals("0/0/0")
									|| time.equals("")) {
								time = "Invalid Date";
								// Logging.getDebug("In invalid date");
							}
							String date = "";
							if (count1 != 0) {
								if (!(time.equals("Invalid Date"))) {
									date = formatDate(time);
								}
								priceComp.setStr_date(date);
							} else {
								priceComp.setStr_date(time);
							}

						}
					}
					if (arr.length == 3) {
						flag = 2;
						Logging.debug("inside array length 3 flag is " + flag);
						for (int i = 0; i < arr.length;) {
							String str1 = arr[i++];
							String series = str1.substring(0, 2);
							String symbol = str1.substring(2);
							priceComp.setStr_symbol(symbol);
							priceComp.setStr_series(series);
							priceComp.setStr_close("0");
							String last = arr[i++];
							priceComp.setStr_last(last);// last
							priceComp.setStr_totTradedVol(arr[i++]);
							String time = hist_date;
							priceComp.setStr_date(time);
						}
					}
					arr_PriceData.add(priceComp);
					/*
					 * if(!(symbolOld.equals(""))&&(symbolOld!=null)&&
					 * !(symbolOld.equals(" "))) { arr_PriceData.add(priceComp);
					 * }
					 */
				}
				file.close();
				Logging.debug("arr_PriceData.size() is " + arr_PriceData.size());
				return true;
			}
			// xls format
			if (extension.equals("xls")) {

			}
			// dbf format
			if (extension.trim().equals("DBF")) {
				try {
					InputStream inputStream = new FileInputStream(str_fileName);
					DBFReader reader = new DBFReader(inputStream);
					int numberOfFields = reader.getFieldCount();
					/*
					 * if((numberOfFields!=11)){ if((numberOfFields!=9)){
					 * //buffer.append(
					 * "<font size='2' face='Arial' color='Red'><tr>File not in correct format </tr></font>"
					 * ); return false; } }
					 */
					priceComp = new CStockPriceComponents();
					String[] temp_Storage = new String[numberOfFields];
					if ((numberOfFields == 15)) {
						for (int i = 0; i < numberOfFields;) {
							// Symbol
							DBFField field = reader.getField(i++);
							priceComp.setStr_symbol(field.getName());
							// Logging.Logging.getDebug(field.getName()+"  symbol : ");
							// Series
							field = reader.getField(i++);
							priceComp.setStr_series(field.getName());
							field = reader.getField(i++);
							field = reader.getField(i++);
							// Open
							field = reader.getField(i++);
							priceComp.setStr_open(field.getName());
							// High
							field = reader.getField(i++);
							priceComp.setStr_high(field.getName());
							// Low
							field = reader.getField(i++);
							priceComp.setStr_low(field.getName());
							// Close
							field = reader.getField(i++);
							priceComp.setStr_close(field.getName());
							field = reader.getField(i++);
							field = reader.getField(i++);
							// totalqantity
							field = reader.getField(i++);
							priceComp.setStr_totTradedQty(field.getName());
							// netturnover
							field = reader.getField(i++);
							priceComp.setNet_change(field.getName());
							field = reader.getField(i++);
							// Last
							field = reader.getField(i++);
							priceComp.setStr_last(field.getName());
							// PrevClose
							field = reader.getField(i++);
							priceComp.setStr_prevClose(field.getName());
							// String date = str_fileName.
						}
						arr_PriceData.add(priceComp);

						Object[] rowObjects;
						while ((rowObjects = reader.nextRecord()) != null) {
							priceComp = new CStockPriceComponents();
							for (int i = 0; i < rowObjects.length;) {
								// Logging.Logging.getDebug(" row object "+rowObjects[i]);
								priceComp.setStr_symbol(rowObjects[i]
										.toString().trim());
								i++;
								priceComp.setStr_series(rowObjects[i]
										.toString().trim());
								i++;
								rowObjects[i].toString().trim();
								i++;
								rowObjects[i].toString().trim();
								i++;
								priceComp.setStr_open(rowObjects[i].toString()
										.trim());
								i++;
								priceComp.setStr_high(rowObjects[i].toString()
										.trim());
								i++;
								priceComp.setStr_low(rowObjects[i].toString()
										.trim());
								i++;
								priceComp.setStr_close(rowObjects[i].toString()
										.trim());
								i++;
								rowObjects[i].toString().trim();
								i++;
								rowObjects[i].toString().trim();
								i++;

								priceComp.setStr_totTradedQty(rowObjects[i]
										.toString().trim());
								i++;
								priceComp.setNet_change(rowObjects[i]
										.toString().trim());
								i++;
								rowObjects[i].toString().trim();
								i++;
								priceComp.setStr_last(rowObjects[i].toString()
										.trim());
								i++;
								priceComp.setStr_prevClose(rowObjects[i]
										.toString().trim());
								i++;
								String date = tdt.getdate(str_fileName);
								priceComp.setStr_date(date);
							}
							arr_PriceData.add(priceComp);
						}
						inputStream.close();
					}
				} catch (DBFException e) {
					errorMessage = e.getMessage();
					Logging.debug(e.getMessage());
				} catch (IOException e) {
					errorMessage = e.getMessage();
					Logging.debug(e.getMessage());
				}
			}
			// xml format
			if (extension.equals("xml")) {

			}
		} catch (Exception e) {

		}
		return true;
	}

	private boolean loadDataFromPriceFilefi(String extension,
			String str_fileName) {
		app.Testdate tdt = new app.Testdate();
		try {
			arr_PriceData1 = new ArrayList();
			if (arr_PriceData1.size() > 0)
				arr_PriceData1.clear();
			int count1 = -1;
			if (extension.equals("csv")) {
				FileReader file = new FileReader(str_fileName);
				BufferedReader br = new BufferedReader(file);
				String str;
				while ((str = br.readLine()) != null) {
					Logging.debug("file line is " + str);
					count1++;
					priceComp1 = new CStockPriceComponentsfi();
					String fcol = "", bgcol = "", size = "", style = "";
					String[] arr = str.split(",");
					// start=new Integer(arr.length).toString();
					Logging.debug("arr.length is " + arr.length);
					if ((arr.length != 9)) {
						inrformat = 1;
						return false;
					}
					if (arr.length == 9) {
						for (int i = 0; i < arr.length;) {
							String str1 = arr[i++];
							priceComp1.setStr_symbol(str1);
							priceComp1.setStr_series(arr[i++]);
							priceComp1.setStr_open(arr[i++]);
							priceComp1.setStr_high(arr[i++]);
							priceComp1.setStr_low(arr[i++]);
							priceComp1.setStr_close(arr[i++]);
							priceComp1.setStr_last(arr[i++]);
							priceComp1.setStr_prevClose(arr[i++]);
							String time = arr[i++];
							// Logging.Logging.getDebug("time "+time);
							if (time == null || time.equals("0/0/0")
									|| time.equals("")) {
								time = "Invalid Date";
								// Logging.Logging.getDebug("In invalid date");
							}
							String date = "";
							if (count1 != 0) {
								if (!(time.equals("Invalid Date"))) {
									date = formatDate(time);
								}
								priceComp1.setStr_date(date);
							} else {
								priceComp1.setStr_date(time);
							}

						}
					}
					arr_PriceData1.add(priceComp1);
				}
				file.close();
				Logging.debug("arr_PriceData.size() is "
						+ arr_PriceData1.size());
				return true;
			}
		} catch (Exception e) {

		}
		return true;
	}

	/**
	 * method returns the total market capital value calulated by adding the
	 * market capital values of all the stocks present in the file chosen.
	 */
	public double getTmcv() {
		return total;
	}

	/**
	 * method to store bhavcopy by ftp file data into database for all the
	 * stocks present in the selected bhavcopy file modifed by Ashish Dated
	 * 28/08/2006 bhavcopy data is stored in tables like stock_price_daily and
	 * intra_day_stock_prices.
	 */

	// cust for chaking off code
	public StringBuffer storeRecord1(String exchange_id, String pdate,
			String correctedFile) {

		StringBuffer buffer = new StringBuffer();
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		try {
			// Class.forName("org.postgresql.Driver").newInstance();
			// Logging.getDebug("forName");
			// connection=DriverManager.getConnection(dbdriver,username,password);

			if (connection == null)
				connection = con.getConnectionForTransaction();
			connection.setAutoCommit(false);
			int counter = 0;

			PreparedStatement stmt = null;
			Logging.debug("Connection obj" + connection);
			// query="insert into bhavdata (symbol,series,trade_high_price,trade_low_price,opening_price,closing_price,previous_close_price,total_traded_quantity,total_traded_value,carriage_return)values(?,?,?,?,?,?,?,?,?,?)";
			// stmt = connection.prepareStatement(query);
			Enumeration e = table.keys();
			Logging.debug("table size is " + table.size());
			String key = "";

			for (e = table.keys(); e.hasMoreElements();) {
				counter++;
				if (counter % 10 == 0) {
					connection.commit();
					connection.setAutoCommit(true);
					Logging.debug("counter after commit 3 IS" + counter);
					counter = 0;
					if (connection != null)
						connection.close();
					connection = null;
					connection = con.getConnectionForTransaction();
				}
				key = (String) e.nextElement();
				Logging.debug("key is " + key);
				Bhav bhav1 = (Bhav) table.get(key);
				String query = ConnectInit.queries
						.getProperty("Insert_Bhave_Data");
				stmt = connection.prepareStatement(query);
				Logging.debug("nseindexdetail is " + bhav1);
				stmt.setString(1, key);
				stmt.setString(2, (bhav1.getSeries()));
				stmt.setString(3, (bhav1.getTrade_High_Price()));
				stmt.setString(4, (bhav1.getTrade_Low_Price()));
				stmt.setString(5, (bhav1.getOpening_Price()));
				stmt.setString(6, (bhav1.getClosing_Price()));
				stmt.setString(7, (bhav1.getPrevious_Close_Price()));
				stmt.setString(8, (bhav1.getTotal_Traded_Quantity()));
				stmt.setString(9, (bhav1.getTotal_Traded_Value()));
				stmt.setString(10, (bhav1.getCarriage_Return()));
				stmt.executeUpdate();

			}
			buffer.append("<tr><td>");
			buffer.append("");
			buffer.append("</td><td><font color='blue'>Prices Entered Successfully For All Stock.</font></td>");

		}

		catch (Exception e) {
			Logging.debug(e);
		}// end of catch
		finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ex) {
				Logging.error(" Error : Unable to close connection "
						+ ex.getMessage());
			}
		}
		return buffer;
	}

	/**
	 * method to store bhavcopy file data into database for all the stocks
	 * present in the selected bhavcopy file. bhavcopy data is stored in tables
	 * like stock_price_daily and intra_day_stock_prices.
	 */
	public StringBuffer storeRecordfi(String exchange_id, String pdate,
			String correctedFile) {
		Logging.debug("hellostorestock bhavcopy" + filedate + " flag is "
				+ flag);
		System.out.println("*** storeRecordfi***" + filedate + " flag is "
				+ flag);
		StringBuffer buffer = new StringBuffer();
		StringBuffer buffernew = new StringBuffer();
		errorMessage = null;
		int inscounter = 0;
		int updcounter = 0;
		int unimpcounter = 0;
		int counter1 = 0;
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		try {
			if (connection != null)
				connection.close();
			connection = con.getConnectionForTransaction();
			Logging.debug("connection is before rollback() " + con);
			connection.rollback();
			Logging.debug("connection is after rollback()" + Connect.con);
			PreparedStatement pst;
			String stockID = null;
			Enumeration e = table.keys();
			String key = "";
			int count = 0;
			int i = 0;
			int ii = 0;
			boolean allow = true;
			Runtime runtime = Runtime.getRuntime();
			Logging.debug("table size :" + table.size());

			stkid_list = getTisListfi(2, exchange_id);
			// Changees by Ashish commented by neha
			/*
			 * if(correctedFile!=null && correctedFile.equals("on")){ try{
			 * PreparedStatement pr = connection.prepareStatement(con.queries
			 * .getProperty("deleteStockPriceDailyQuery")); pr.setString(1,
			 * pdate); //price_date pr.setString(2,exchange_id);
			 * pr.executeUpdate(); if(pr!=null) pr.close(); }catch(Exception
			 * ee){ Logging.getError("Error : "+ee.getMessage()); } }
			 */
			int counter = 0;
			while (e.hasMoreElements()) {
				counter1++;
				if (counter % 10 == 0) {
					connection.commit();
					connection.setAutoCommit(true);
					Logging.debug("counter after commit 3 IS" + counter);
					counter = 0;
					if (connection != null)
						connection.close();
					connection = null;
					connection = con.getConnectionForTransaction();
				}
				allow = true;
				count++;
				stockID = null;
				key = (String) e.nextElement();
				if (key.equals("Symbol")
						|| (key.toUpperCase().equals("SYMBOL")))
					continue;
				priceComp1 = (CStockPriceComponentsfi) table.get(key);
				try {
					if (stkid_list.containsKey(key)) {
						stockID = stkid_list.get(key).toString();

					}
					// Logging.getDebug(" stockid is "+stockID);
					if (stockID == null || stockID.equals("")) {
						continue;
						/*
						 * String price_unimprt_query=con.queries.getProperty(
						 * "insert_into_stock_prices_unimported");
						 * insertUnimportedStockPrices
						 * (connection,key,exchange_id
						 * ,stockID,price_unimprt_query,priceComp1);
						 * unimpcounter++; buffer.append("<tr><td>");
						 * buffer.append(key); buffer.append(
						 * "</td><td><font color='black'>Stock Does Not Exist.</font></td>"
						 * );
						 */
					} else {
						if (allow == true) {
							// insert the data into table intra_day_stock_price
							pst = connection
									.prepareStatement(ConnectInit.queries
											.getProperty("insert_into_intra_day_stock_prices_fi"));
							pst.setDouble(1,
									Double.parseDouble(priceComp1.getStr_low()));
							pst.setString(2, pdate);
							pst.setString(3, priceComp1.getStr_date());
							pst.setString(4, stockID);
							pst.executeUpdate();
							// Fixed_income_stock_price_daily till database
							// notcompleted
							pst = connection
									.prepareStatement(ConnectInit.queries
											.getProperty("insert_into_stock_price_daily_fi"));

							// pst.setInt(1,StkpriceDailyID);
							if (flag == 2) {
								pst.setString(1, null);
								pst.setString(2, null);
								pst.setString(3, null);
							} else {
								// coment for null value insertion feature
								// provision Ashish
								// pst.setDouble(1,Double.parseDouble(""));
								// pst.setDouble(2,Double.parseDouble(""));
								// pst.setDouble(3,Double.parseDouble(""));
							}
							if (flag == 1 || flag == 2) {
								pst.setDouble(4, Double.parseDouble(priceComp1
										.getStr_last()));
							} else {
								double stk_close = Double
										.parseDouble(priceComp1.getStr_close());
								pst.setDouble(1, stk_close);
							}
							pst.setString(2, stockID);
							// coment for null value insertion feature provision
							// Ashish
							// pst.setDouble(6,Double.parseDouble(""));
							pst.setString(3, priceComp1.getStr_date());
							pst.setDouble(4, Double.parseDouble(priceComp1
									.getStr_close()));
							// Logging.getDebug("STockid is="+stockID+"the closingStockPrice is ="+Double.parseDouble(priceComp.getStr_close()));
							// Logging.getDebug("STockid is="+stockID+"the lastPrice is ="+Double.parseDouble(priceComp.getStr_last()));

							double mcap = 0.00;
							mcap = ((Double.parseDouble(priceComp1
									.getStr_close())) * (Double
									.parseDouble(priceComp1.getStr_prevClose())));
							pst.setDouble(5, mcap);

							// Logging.getDebug("The mcap value isisis= "+Double.parseDouble(priceComp.getMkt_cap()));

							pst.setDouble(6,
									Double.parseDouble(priceComp1.getStr_low()));
							// pst.setDouble(11,Double.parseDouble(""));
							pst.setDouble(7, mcap);
							pst.setDouble(8, Double.parseDouble(priceComp1
									.getStr_prevClose()));
							pst.setDouble(9, Double.parseDouble(priceComp1
									.getStr_open()));
							pst.setDouble(10, Double.parseDouble(priceComp1
									.getStr_high()));
							pst.setDouble(11, Double.parseDouble(priceComp1
									.getStr_last()));
							// pst.setDouble(17,Double.parseDouble(""));
							pst.executeUpdate();
							inscounter++;
							Logging.debug("values inserted into stock_price_daily ");
							Logging.debug("end of while loop" + key);
							buffer.append("<tr><td>");
							buffer.append(key);
							buffer.append("</td><td><font color='blue'>Stock Prices Entered Successfully.</font></td>");

						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					/*
					 * try{ Connect.con.rollback(); Connect.con.close();
					 * Connect.con=null; }catch (Exception ee) { // TODO: handle
					 * exception }
					 */
					Logging.error("My SQL error" + e1.getMessage());
					errorMessage = e1.getMessage();
					// e1.printStackTrace();
					break;
				} catch (Exception em) {
					Logging.error("My error" + em.getMessage());
				}
				/*
				 * catch (Throwable throwable) { // TODO Auto-generated catch
				 * block try{ Connect.con.rollback(); Connect.con.close();
				 * Connect.con=null; }catch (Exception ee) { // TODO: handle
				 * exception } Logging.getError("My error" + throwable);
				 * //throwable.printStackTrace();
				 * 
				 * }
				 */
				i++;
				ii++;
				if (i > 100) {
					// Logging.getError("memmory available before: " +
					// runtime.freeMemory());
					System.gc();
					if (ii > 350)
						Logging.info("memmory available After: "
								+ runtime.freeMemory());
					i = 0;
				}
				/*
				 * if(ii>350){ long x=runtime.freeMemory(); if(x<10000000){
				 * System.gc(); } Logging.getError("memmory available After: " +
				 * runtime.freeMemory()); }
				 */
				// Logging.getInfo("Stock Number : "+ii);
				// size=buffer.length()+table.size()+e.toString().length();
				// Logging.getInfo("buffer size : "+size);
				priceComp = null;

			}
			buffer.append("<tr><td>");
			buffer.append("");
			buffer.append("</td><td><font color='blue'>Prices Entered Successfully For All Other Stock.</font></td>");

			table.clear();

			buffernew
					.append("<br><tr><font color=Blue><td>Values Inserted :</td><td>");
			buffernew.append(inscounter);
			buffernew.append("</td></font></tr>");
			buffernew
					.append("<br><tr><font color=Blue><td>Values Updated :</td><td>");
			buffernew.append(updcounter);
			buffernew.append("</td></font></tr>");
			buffernew
					.append("<br><tr><font color=Blue><td>Unimported Values:</td><td>");
			buffernew.append(unimpcounter);
			buffernew
					.append("<br><tr><font color=Blue><td>Total Rows :</td><td>");
			buffernew.append(counter1 - 1);
			buffernew.append("</td></font></tr>");
			buffernew.append(buffer);
			buffer = null;
			Logging.info("Bhavcopy Inserted Successfully");
		} catch (Exception e) {
			Logging.error("Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ex) {
				Logging.error(" Error : Unable to close connection "
						+ ex.getMessage());
			}
		}
		return buffernew;

	}

	public String returnAccruedInterest(String stockidS, String currentDateS) {
		this.priceComp1.getStr_date();
		long diffInDays = 0L;
		Vector vCopDates = new Vector();
		double accruedInt = 0.0D;
		double faceVal = 0.0D;
		double rateOfInt = 0.0D;
		double intRateDaysInYear = 0.0D;
		Connect c = new Connect();
		Connection connection = null;
		try {
			connection = c.getdbConnection();

			PreparedStatement pst = connection
					.prepareStatement(ConnectInit.queries
							.getProperty("get_coupon_dates_List11"));
			pst.setString(1, stockidS);

			ResultSet rst = pst.executeQuery();
			String couponDate = null;

			while (rst.next()) {
				couponDate = rst.getString(2);
				vCopDates.add(couponDate);
			}
			rst.close();
			pst.close();

			PreparedStatement pstat = connection
					.prepareStatement(ConnectInit.queries
							.getProperty("get_fixed_income_stock_master_details"));
			pstat.setString(1, stockidS);
			ResultSet rset = pstat.executeQuery();
			while (rset.next()) {
				faceVal = rset.getDouble("face_value");
				rateOfInt = rset.getDouble("coupon_percentage");
				intRateDaysInYear = rset.getDouble("interest_rate_daysinyear");
			}
			rset.close();
			pstat.close();

			String sCouponDate = (String) vCopDates.elementAt(0);
			diffInDays = returnDayDiff(currentDateS, sCouponDate);

			if (diffInDays < 0L)
				accruedInt = 0.0D;
			else if (diffInDays == 0L)
				accruedInt = 0.0D;
			else if (diffInDays > 0L) {
				for (int i = 0; i < vCopDates.size(); i++) {
					if (vCopDates.lastElement() == vCopDates.elementAt(i)) {
						sCouponDate = (String) vCopDates.elementAt(i);
						diffInDays = returnDayDiff(currentDateS, sCouponDate);
						if (diffInDays > 0L) {
							accruedInt = faceVal * (rateOfInt / 100.0D)
									* (diffInDays / intRateDaysInYear);
							break;
						}
					} else {
						sCouponDate = (String) vCopDates.elementAt(i + 1);
						diffInDays = returnDayDiff(currentDateS, sCouponDate);
					}
					if (diffInDays < 0L) {
						sCouponDate = (String) vCopDates.elementAt(i);
						diffInDays = returnDayDiff(currentDateS, sCouponDate);
						accruedInt = faceVal * (rateOfInt / 100.0D)
								* (diffInDays / intRateDaysInYear);
						break;
					}
					if (diffInDays == 0L) {
						accruedInt = 0.0D;
						break;
					}

				}

			}

		} catch (Exception localException1) {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception localException2) {
				}
			}
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null) {
						connection.close();
					}
				} catch (Exception localException3) {
				}
			}

		}

		String sAccruedInt = new Double(accruedInt).toString();

		return sAccruedInt;
	}

	public long returnDayDiff(String curDate, String copDate) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();

		String cDays = curDate.substring(0, 2);
		String cMonths = curDate.substring(3, 5);
		String cYear = curDate.substring(6, 10);

		int cDayInt = Integer.parseInt(cDays);
		int cMonthInt = Integer.parseInt(cMonths);
		int cYearInt = Integer.parseInt(cYear);

		String copDays = copDate.substring(0, 2);
		String copMonths = copDate.substring(3, 5);
		String copYear = copDate.substring(6, 10);

		int copDayInt = Integer.parseInt(copDays);
		int copMonthInt = Integer.parseInt(copMonths);
		int copYearInt = Integer.parseInt(copYear);

		cal1.set(copYearInt, copMonthInt, copDayInt);
		cal2.set(cYearInt, cMonthInt, cDayInt);

		long milis1 = cal1.getTimeInMillis();
		long milis2 = cal2.getTimeInMillis();

		long diff = milis2 - milis1;

		long diffDays = diff / 86400000L;

		System.out.println("In milliseconds: " + diff + " milliseconds.");
		System.out.println("In days: " + diffDays + " days.");
		return diffDays;
	}

	public StringBuffer storeRecord(String exchange_id, String pdate,
			String correctedFile) {
		System.out.println("storeRecord Method ***" + pdate);
		Logging.debug("hellostorestock bhavcopy" + filedate + " flag is "
				+ flag);
		StringBuffer buffer = new StringBuffer();
		StringBuffer buffernew = new StringBuffer();
		errorMessage = null;
		int inscounter = 0;
		int updcounter = 0;
		int unimpcounter = 0;
		int counter1 = 0;
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		try {
			/*
			 * if(Connect.con == null){ con.getConnection(); }
			 */
			if (connection != null)
				connection.close();
			connection = con.getConnectionForTransaction();
			Logging.debug("connection is before rollback() " + con);
			connection.rollback();
			Logging.debug("connection is after rollback()" + Connect.con);
			PreparedStatement pst, pst1, pst3;
			Statement stm;
			String creationDate = null;
			int intradayStkID = 0;
			double stkHigh = 0, stkLow = 0;
			ResultSet result, result1, result3;
			String query = "", stockID = null;
			/*
			 * String maxdate=getMaxDate(exchange_id);
			 * Logging.getDebug("After Simpledate"+maxdate); if(maxdate!=null){
			 * creationDate
			 * =maxdate.trim().substring(8,10)+"-"+maxdate.trim().substring(5,
			 * 7)+"-"+maxdate.trim().substring(0,4);
			 */

			// Comented by ashishi for back date and corrected file option
			/*
			 * int datediff=CompareDate(QueryClass.formatDate(),filedate);
			 * Logging.getDebug("datediff is "+datediff); if(datediff<0){
			 * Logging.getDebug(
			 * "File Can  Not Be Imported , Date Greater Than Current Date.");
			 * buffer.append("<tr><td>"); buffer.append(""); buffer.append(
			 * "</td><td><font color='black'>File Can  Not Be Imported , Date Greater Than Current Date.</font></td></tr>"
			 * ); return buffer; }
			 */
			Enumeration e = table.keys();
			System.out.println("table keys *** " + table);
			System.out.println("Enumeration *** " + e.toString());
			// Logging.getDebug("Is Empty "+table.isEmpty()+" exchange_id is "+exchange_id);
			String key = "", identifier_code_name = "";
			int count = 0;
			int i = 0;
			int ii = 0;
			boolean allow = true;
			// String de=priceComp.getStr_date();
			Runtime runtime = Runtime.getRuntime();
			Logging.debug("table size :" + table.size());
			long tis = 0;
			long size = 0;
			stkid_list = getTisList(2, exchange_id);
			last_value_list = checkRejection(exchange_id);
			stockid_cad_list = getCADStocks(filedate);
			System.out.println("stkid_list***" + stkid_list.size() + " &&& "
					+ stkid_list);
			System.out.println("last_value_list***" + last_value_list.size()
					+ " &&& " + last_value_list);
			System.out.println("stockid_cad_list***" + stockid_cad_list.size()
					+ " &&& " + stockid_cad_list);

			// Changees by Ashish commented by neha
			if (correctedFile != null && correctedFile.equals("on")) {
				try {
					PreparedStatement pr = connection
							.prepareStatement(ConnectInit.queries
									.getProperty("deleteStockPriceDailyQuery"));
					pr.setString(1, pdate); // price_date
					pr.setString(2, exchange_id);
					pr.executeUpdate();
					if (pr != null)
						pr.close();
				} catch (Exception ee) {
					Logging.error("Error : " + ee.getMessage());
					System.out.println("Error storeRecord: " + ee.getMessage());
				}
			}
			int counter = 0;
			while (e.hasMoreElements()) {
				counter1++;
				if (counter % 10 == 0) {
					connection.commit();
					connection.setAutoCommit(true);
					Logging.debug("counter after commit 3 IS" + counter);
					counter = 0;
					if (connection != null)
						connection.close();
					connection = null;
					connection = con.getConnectionForTransaction();
				}
				allow = true;
				count++;
				/*
				 * if(count>4) return buffer;
				 */
				stockID = null;
				key = (String) e.nextElement();
				Logging.debug("symbol is " + key);
				if (key.equals("EQSTER")) {
					Logging.debug("symbol is " + key);
				}
				if (key.equals("Symbol")
						|| (key.toUpperCase().equals("SYMBOL")))
					continue;
				priceComp = (CStockPriceComponents) table.get(key);
				// exchange_id=getExchange_id();
				String series = priceComp.getStr_symbol();
				/*
				 * if(key.equals("ELTL") && series.equals("P1") ) {
				 * Logging.getDebug("Start Debug"); }
				 */
				try {
					// get stock id of the stock passing stock symbol and
					// exchange ID
					Logging.debug("flag value is " + flag
							+ " stkid_list size is " + stkid_list.size());
					/*
					 * if(flag==2){
					 * stockID=getStockId_NSE(connection,exchange_id
					 * ,key,priceComp.getStr_series()); }else{
					 * if(stkid_list.containsKey(key)){
					 * stockID=stkid_list.get(key).toString(); } }
					 */
					if (flag == 2) {
						stockID = getStockId_NSE(connection, exchange_id, key,
								priceComp.getStr_series());
					} else {
						if (exchange_id.equals("84")) {
							if (stkid_list.containsKey(series)) {
								stockID = stkid_list.get(series).toString();
							}
						} else {
							if (stkid_list.containsKey(key)) {
								stockID = stkid_list.get(key).toString();
							}
						}
					}
					// Logging.getDebug(" stockid is "+stockID);
					if (stockID == null || stockID.equals("")) {
						// Logging.getDebug("inside stockid null "+stockID);
						String price_unimprt_query = ConnectInit.queries
								.getProperty("insert_into_stock_prices_unimported");
						insertUnimportedStockPrices(connection, key,
								exchange_id, stockID, price_unimprt_query,
								priceComp);
						unimpcounter++;
						buffer.append("<tr><td>");
						buffer.append(key);
						buffer.append("</td><td><font color='black'>Stock Does Not Exist.</font></td>");
					} else {
						if (!(stockid_cad_list.isEmpty())
								&& stockid_cad_list.containsKey(stockID)) {
							allow = false; // neha commented 3rd jan
							String price_unimprt_query = ConnectInit.queries
									.getProperty("insert_into_stock_prices_unimported"); // ////
							insertUnimportedStockPrices(connection, key,
									exchange_id, stockID, price_unimprt_query,
									priceComp);
							unimpcounter++;
							buffer.append("<tr><td>");
							buffer.append(key);
							buffer.append("</td><td><font color='black'>Can Not Store, Corporate Action Applied On Stock.</font></td>");
							continue;
						}
						String pvalue = null;
						double reject_per = 0.00;
						double alertdiff = 0.00;
						Logging.debug("priceComp.getStr_last() is "
								+ priceComp.getStr_last());
						Logging.debug("stockID" + stockID);
						if (last_value_list.containsKey(stockID)) {
							if (reject_per_list.containsKey(stockID)) {
								reject_per = Double.parseDouble(reject_per_list
										.get(stockID).toString());
							}
							Logging.debug("inside alertdiff check");
							pvalue = last_value_list.get(stockID).toString();
							String lvalue = priceComp.getStr_last();
							Logging.debug("pvalue is " + pvalue + " lvalue is "
									+ lvalue);
							alertdiff = (((Double.parseDouble(lvalue)) - (Double
									.parseDouble(pvalue))) / (Double
									.parseDouble(lvalue))) * 100;
							Logging.debug("alertdiff inside check before abs is "
									+ alertdiff);
							alertdiff = Math.abs(alertdiff);
							Logging.debug("alertdiff inside check after abs is "
									+ alertdiff);
						}
						Logging.debug("alertdiff is " + alertdiff
								+ " reject_per is " + reject_per);
						if (alertdiff != 0.00 && alertdiff > reject_per) {
							allow = false;
							String price_unimprt_query = ConnectInit.queries
									.getProperty("insert_into_stock_prices_unimported");
							insertUnimportedStockPrices(connection, key,
									exchange_id, stockID, price_unimprt_query,
									priceComp);
							unimpcounter++;
							buffer.append("<tr><td>");
							buffer.append(key);
							buffer.append("</td><td><font color='black'>Can Not Store, Prices For Stock Greater Than Rejection Percentage.</font></td>");
							continue;
						}
						if (allow == true) {
							// insert the data into table intra_day_stock_price
							pst = connection
									.prepareStatement(ConnectInit.queries
											.getProperty("insert_into_intra_day_stock_prices"));
							// Logging.debug("Inside insert_into_intra_day_stock_prices");
							pst.setDouble(1,
									Double.parseDouble(priceComp.getStr_last()));
							pst.setString(2, pdate);
							pst.setString(3, priceComp.getStr_time());
							pst.setString(4, stockID);
							// Logging.debug("before  executeUpdate "+pst);
							int recordCount = pst.executeUpdate();
							// Logging.getInfo("Values inserted into intra_day_stock_prices");

							// get the high and low value for todays date ie
							// timestamp in bhavcopy
							pst = connection
									.prepareStatement(ConnectInit.queries
											.getProperty("get_stock_high_low_value"));
							// Logging.debug("Inside get_stock_high_low_value");
							pst.setString(1, pdate);
							pst.setString(2, stockID);
							result = pst.executeQuery();
							// check if result set has values in it. true
							// results indicate that entry
							// for todays date for a particular stock is already
							// done so compare last
							// price with the stored high low value
							if (result.next()) {
								stkHigh = result.getDouble(1);
								stkLow = result.getDouble(2);
								// if ltp greater update high, close, passing
								// stock id
								if (Double.parseDouble(priceComp.getStr_last()) > stkHigh) {
									pst = connection
											.prepareStatement(ConnectInit.queries
													.getProperty("update_high_close_stock_price"));
									pst.setDouble(1, Double
											.parseDouble(priceComp
													.getStr_high()));
									if (flag == 1 || flag == 2) {
										pst.setDouble(2, Double
												.parseDouble(priceComp
														.getStr_last()));
									} else {
										pst.setDouble(2, Double
												.parseDouble(priceComp
														.getStr_close()));
									}
									pst.setString(3, stockID);
									pst.setString(4, pdate);
									int updatedRecordCount = pst
											.executeUpdate();
									updcounter++;
									/*
									 * buffer.append("<tr><td>");
									 * buffer.append(key); buffer.append(
									 * "</td><td><font color='blue'>High close Price For Stock updated succesfully.</font></td>"
									 * );
									 */
								} else {
									// if ltp greater update low, close, passing
									// stock id
									if (Double.parseDouble(priceComp
											.getStr_last()) < stkLow) {
										pst = connection
												.prepareStatement(ConnectInit.queries
														.getProperty("update_low_close_stock_price"));
										if (flag == 2) {
											pst.setDouble(1, Double
													.parseDouble(priceComp
															.getStr_last()));
										} else {
											pst.setDouble(1, Double
													.parseDouble(priceComp
															.getStr_low()));
										}
										if (flag == 1 || flag == 2) {
											pst.setDouble(2, Double
													.parseDouble(priceComp
															.getStr_close()));
										} else {
											pst.setDouble(2, Double
													.parseDouble(priceComp
															.getStr_close()));
										}
										pst.setString(3, stockID);
										pst.setString(4, pdate);
										int updatedCount = pst.executeUpdate();
										updcounter++;
										/*
										 * buffer.append("<tr><td>");
										 * buffer.append(key); buffer.append(
										 * "</td><td><font color='blue'>Low close Price For Stock updated succesfully.</font></td>"
										 * );
										 */
									}
								}
								/*
								 * if(!(Double.parseDouble(priceComp.getStr_last(
								 * ))>stkHigh) &&
								 * !(Double.parseDouble(priceComp.
								 * getStr_last())<stkLow)){
								 * buffer.append("<tr><td>");
								 * buffer.append(key); buffer.append(
								 * "</td><td><font color='blue'>Mkt Cap. Value For Stock updated succesfully.</font></td>"
								 * ); }
								 */
							} else {
								// this indicate that no entry has been made for
								// 2days date for a

								// insert pk, open hig low close stkID tradedVol
								// date, tis in
								// stock_price_daily
								// String query1 = ConnectInit.queries
								// .getProperty("insert_into_stock_price_daily");//previous
								// code before modified by samiksha

								/*
								 * String query1 = ConnectInit.queries
								 * .getProperty
								 * ("insert_into_stock_price_daily_if_not_exists"
								 * ); // here // query // is // modified // by
								 * // samiksha // bcoz // getting // ERROR: //
								 * duplicate // key // value // violates //
								 * unique // constraint //
								 * "stock_price_daily_pkey1" pst =
								 * connection.prepareStatement(query1); //
								 * Logging
								 * .debug("Inside insert_into_stock_price_daily "
								 * ); // pst.setInt(1,StkpriceDailyID); if (flag
								 * == 2) { pst.setString(1, null);
								 * pst.setString(2, null); pst.setString(3,
								 * null); } else { String param1 =
								 * priceComp.getStr_open(); String param2 =
								 * priceComp.getStr_high(); String param3 =
								 * priceComp.getStr_low();
								 * System.out.println("param 1 ***" + param1);
								 * System.out.println("param 2 ***" + param2);
								 * System.out.println("param 3 ***" + param3);
								 * pst.setDouble(1, Double
								 * .parseDouble(priceComp .getStr_open()));
								 * pst.setDouble(2, Double
								 * .parseDouble(priceComp .getStr_high()));
								 * pst.setDouble(3, Double.parseDouble(priceComp
								 * .getStr_low())); } double stk_close; if (flag
								 * == 1 || flag == 2) {
								 * 
								 * pst.setDouble(4, Double
								 * .parseDouble(priceComp .getStr_last()));
								 * 
								 * } else { stk_close =
								 * Double.parseDouble(priceComp
								 * .getStr_close()); pst.setDouble(4,
								 * stk_close);
								 * 
								 * }
								 * 
								 * // stk_close=Double.parseDouble(priceComp.
								 * getStr_close()); pst.setString(5, stockID);
								 * //
								 * pst.setDouble(6,Double.parseDouble(priceComp
								 * .getStr_totTradedVol())); pst.setDouble(6,
								 * Double.parseDouble(priceComp
								 * .getStr_totTradedQty())); pst.setString(7,
								 * pdate); pst.setDouble(8,
								 * Double.parseDouble(priceComp
								 * .getStr_close()));
								 * Logging.debug("STockid is=" + stockID +
								 * "the closingStockPrice is =" +
								 * Double.parseDouble(priceComp
								 * .getStr_close()));
								 * Logging.debug("STockid is=" + stockID +
								 * "the lastPrice is =" +
								 * Double.parseDouble(priceComp
								 * .getStr_last()));
								 * 
								 * if(flag==1 || flag==2){
								 * pst.setDouble(8,Double
								 * .parseDouble(priceComp.getStr_close()));
								 * }else{
								 * 
								 * pst.setDouble(8,Double.parseDouble(priceComp.
								 * getStr_close())); }
								 * 
								 * Logging.debug(
								 * "Inside insert_into_stock_price_daily market cap is "
								 * + priceComp.getMkt_cap());
								 * 
								 * String mcap = "aa";
								 * if(mktcap_list.containsKey(key)){
								 * mcap=mktcap_list.get(key).toString(); double
								 * mcap_double = Double.parseDouble(mcap);
								 * pst.setDouble(9,mcap_double); }else{
								 * pst.setDouble(9,Double.parseDouble("0.00"));
								 * }
								 * 
								 * 
								 * double tis;
								 * pst3=connection.prepareStatement(con.queries.
								 * getProperty("select_TIS_for_SockId"));
								 * pst3.setInt(1,stockID);
								 * result3=pst3.executeQuery();
								 * if(result3.next()) tis =
								 * result3.getDouble(1); result3.close();
								 * pst3.close();
								 * 
								 * // double mcap=0.00; // double //
								 * stk_close=Double
								 * .parseDouble(priceComp.getStr_close()); //
								 * mcap=stk_close*tis; pst.setDouble(9,
								 * Double.parseDouble(priceComp .getMkt_cap()));
								 * // pst.setDouble(9,priceComp.getMkt_cap());
								 * Logging.debug("The mcap value isisis= " +
								 * Double.parseDouble(priceComp .getMkt_cap()));
								 * // Double.parseDouble("0.00")); //
								 * pst.setLong(9,tis);//naresh pst.setDouble(10,
								 * Double.parseDouble(priceComp
								 * .getStr_last())); pst.setDouble(11,
								 * Double.parseDouble(priceComp .getMkt_cap()));
								 */

								// ****************modified by
								// Samiksha******STARTS*************************

								Double stock_lowest_value = null, stockOpeningValue = null, stockHighestValue = null, tradedVolume = null, adjustedPrice = null, mcv = null, last = null, adjustedMCap = null;
								if (flag == 2) {
									stockOpeningValue = null;
									stockHighestValue = null;
									stock_lowest_value = null;
								} else {
									stockOpeningValue = Double
											.parseDouble(priceComp
													.getStr_open());
									stockHighestValue = Double
											.parseDouble(priceComp
													.getStr_high());
									stock_lowest_value = Double
											.parseDouble(priceComp.getStr_low());
								}
								double stk_close;
								if (flag == 1 || flag == 2) {
									stk_close = Double.parseDouble(priceComp
											.getStr_last());
								} else {
									stk_close = Double.parseDouble(priceComp
											.getStr_close());
								}
								tradedVolume = Double.parseDouble(priceComp
										.getStr_totTradedQty());
								adjustedPrice = Double.parseDouble(priceComp
										.getStr_close());
								mcv = Double
										.parseDouble(priceComp.getMkt_cap());
								last = Double.parseDouble(priceComp
										.getStr_last());
								adjustedMCap = Double.parseDouble(priceComp
										.getMkt_cap());
								System.out.println("PDATE*****" + pdate);

										

//								String query1 = "INSERT INTO  stock_price_daily (stock_price_daily_id,stock_opening_value,stock_highest_value,stock_lowest_value,stock_closing_value,stock_id,traded_volume,stock_price_date,adjusted_price,mcv,last,adjusted_mcap) SELECT (select nextval('stock_price_daily_id')),"
//										+ stockOpeningValue
//										+ ","
//										+ stockHighestValue
//										+ ","
//										+ stock_lowest_value
//										+ ","
//										+ stk_close
//										+ ","
//										+ stockID
//										+ ","
//										+ tradedVolume
//										+ ","
//										+ pdate
//										+ ","
//										+ adjustedPrice
//										+ ","
//										+ mcv
//										+ ","
//										+ last
//										+ ","
//										+ adjustedMCap
//										+ " where not exists (select stock_price_daily_id from stock_price_daily where stock_price_daily_id=(select nextval('stock_price_daily_id')))";
//								
								String query1 = "insert into stock_price_daily (stock_price_daily_id,stock_opening_value,stock_highest_value,stock_lowest_value,stock_closing_value,stock_id,traded_volume,stock_price_date,adjusted_price,mcv,last,adjusted_mcap) values(((SELECT MAX(stock_price_daily_id) FROM stock_price_daily)+1),"
										+ stockOpeningValue
										+ ","
										+ stockHighestValue
										+ ","
										+ stock_lowest_value
										+ ","
										+ stk_close
										+ ","
										+ stockID
										+ ","
										+ tradedVolume
										+ ",'"
										+ pdate
										+ "',"
										+ adjustedPrice
										+ ","
										+ mcv
										+ ","
										+ last
										+ ","
										+ adjustedMCap
										+")";
								pst = connection.prepareStatement(query1);
								// ****************modified by
								// Samiksha******ENDS*************************
								System.out.println("query1 ***** " + query1);
								int insertedCount = pst.executeUpdate();
								System.out.println("insertedCount *** "
										+ insertedCount);
								if (insertedCount == 0) {
									String tempQuery2 = "select nextval('stock_price_daily_id')";
									pst = connection
											.prepareStatement(tempQuery2);
									ResultSet rs2 = pst.executeQuery();
									int dailyId2 = rs2.getInt(1);
									System.out.println("Daily ID 222 *** "
											+ dailyId2);
									query1 = "update stock_price_daily set stock_id ="
											+ stockID
											+ ", stock_highest_value="
											+ stockHighestValue
											+ ",stock_lowest_value="
											+ stock_lowest_value
											+ ",stock_closing_value="
											+ stk_close
											+ ",stock_opening_value="
											+ stockOpeningValue
											+ ",traded_volume="
											+ tradedVolume
											+ ",stock_price_date="
											+ pdate
											+ ",adjusted_price="
											+ adjustedPrice
											+ ",mcv="
											+ mcv
											+ ",last="
											+ last
											+ ",adjusted_mcap="
											+ adjustedMCap
											+ " where stock_price_daily_id =(select nextval('stock_price_daily_id'))";

									pst = connection.prepareStatement(query1); // execute
																				// update
																				// SQL
																				// stetement
																				// int
									int updatedRecordCount = pst
											.executeUpdate();

									if (updatedRecordCount > 0) {
										System.out
												.println("Existing record updated successfully");
										updcounter++;
										System.out.println("Update counter ***"
												+ updcounter);
									}

								}
								inscounter++;
								Logging.debug("values inserted into stock_price_daily ");
								Logging.debug("end of while loop" + key);
								buffer.append("<tr><td>");
								buffer.append(key);
								buffer.append("</td><td><font color='blue'>Stock Prices Entered Successfully.</font></td>");

							}
						}
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					/*
					 * try{ Connect.con.rollback(); Connect.con.close();
					 * Connect.con=null; }catch (Exception ee) { // TODO: handle
					 * exception }
					 */
					Logging.error("My SQL error" + e1.getMessage());
					System.out.println("Exception *** " + e1.getMessage());
					errorMessage = e1.getMessage();
					// e1.printStackTrace();
					break;
				} catch (Exception em) {
					Logging.error("My error" + em.getMessage());
				}
				/*
				 * catch (Throwable throwable) { // TODO Auto-generated catch
				 * block try{ Connect.con.rollback(); Connect.con.close();
				 * Connect.con=null; }catch (Exception ee) { // TODO: handle
				 * exception } Logging.getError("My error" + throwable);
				 * //throwable.printStackTrace();
				 * 
				 * }
				 */
				i++;
				ii++;
				if (i > 100) {
					// Logging.getError("memmory available before: " +
					// runtime.freeMemory());
					System.gc();
					if (ii > 350)
						Logging.info("memmory available After: "
								+ runtime.freeMemory());
					i = 0;
				}
				/*
				 * if(ii>350){ long x=runtime.freeMemory(); if(x<10000000){
				 * System.gc(); } Logging.getError("memmory available After: " +
				 * runtime.freeMemory()); }
				 */
				// Logging.getInfo("Stock Number : "+ii);
				// size=buffer.length()+table.size()+e.toString().length();
				// Logging.getInfo("buffer size : "+size);
				priceComp = null;

			}
			buffer.append("<tr><td>");
			buffer.append("");
			buffer.append("</td><td><font color='blue'>Prices Entered Successfully For All Other Stock.</font></td>");

			table.clear();

			buffernew
					.append("<br><tr><font color=Blue><td>Values Inserted :</td><td>");
			buffernew.append(inscounter);
			buffernew.append("</td></font></tr>");
			buffernew
					.append("<br><tr><font color=Blue><td>Values Updated :</td><td>");
			buffernew.append(updcounter);
			buffernew.append("</td></font></tr>");
			buffernew
					.append("<br><tr><font color=Blue><td>Unimported Values:</td><td>");
			buffernew.append(unimpcounter);
			buffernew
					.append("<br><tr><font color=Blue><td>Total Rows :</td><td>");
			buffernew.append(counter1 - 1);
			buffernew.append("</td></font></tr>");
			buffernew.append(buffer);
			buffer = null;
			Logging.info("Bhavcopy Inserted Successfully");
		} catch (Exception e) {
			Logging.error("Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ex) {
				Logging.error(" Error : Unable to close connection "
						+ ex.getMessage());
			}
		}
		return buffernew;

	}

	public Hashtable getTisListfi(int type, String exchange) {
		Connect con = ConnectInit.getConnect();
		// Change by Manoj Adekar for Dynamic connection using getdbConnection()
		// instead of getConnection()
		Connection connection = null;
		try {
			if (connection == null) {
				connection = con.getdbConnection();
			}

			// Logging.getDebug("exchange id is  "+exchange);
			try {
				PreparedStatement pst = connection
						.prepareStatement(ConnectInit.queries
								.getProperty("get_tis_symbol_wise_fi"));
				pst.setString(1, exchange);
				ResultSet result = pst.executeQuery();
				tislist_id1.clear();
				while (result.next()) {
					tislist_id1.put(result.getString(1).trim(),
							result.getString(2));
				}
			} catch (Exception sqlexp) {
				Logging.error("Error while getting tis " + sqlexp);
			}
			// Close the Dynamic Connection : Manoj Adekar
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		if (type == 2) {
			return tislist_id1;
		}

		// returns the hashtable containing symbol and tis value
		return tislist1;
	}

	/**
	 * @return Returns the Hashtable showing list of tis values.
	 */
	public Hashtable getTisList(int type, String exchange) {
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		// Change by Manoj Adekar for Dynamic connection using getdbConnection()
		// instead of getConnection()
		try {
			if (connection == null) {
				connection = con.getdbConnection();
			}

			// Logging.getDebug("exchange id is  "+exchange);
			try {
				String query = ConnectInit.queries
						.getProperty("get_tis_symbol_wise");
				System.out.println("get_tis_symbol_wise query*** " + query);
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setString(1, exchange);
				ResultSet result = pst.executeQuery();
				// Logging.getDebug("tislist1  "+tislist1);
				// Logging.getDebug("tislist_id1  "+tislist_id1);
				tislist1.clear();
				tislist_id1.clear();
				while (result.next()) {
					tislist1.put(result.getString(1).trim(),
							String.valueOf(result.getLong(2)));
					tislist_id1.put(result.getString(1).trim(),
							result.getString(3));
				}
			} catch (Exception sqlexp) {
				Logging.error("Error while getting tis " + sqlexp);
			}
			// Close the Dynamic Connection : Manoj Adekar
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		// Enumeration e=tislist1.keys();
		/*
		 * while(e.hasMoreElements()){ String st=(String)e.nextElement(); String
		 * string=(String)tislist1.get(st);
		 * Logging.debug("Value in hashtable : "+st+"  : "+string);
		 * 
		 * }
		 */
		System.out.println("Hashtable Symbols from DB list ***" + tislist1);
		System.out.println("Hashtable Symbols from DB list1111***"
				+ tislist_id1);
		Logging.debug("size hashtable : " + tislist1.size());
		if (type == 2) {
			// returns the hashtable containing stockid and tis value
			return tislist_id1;
		}
		// returns the hashtable containing symbol and tis value
		return tislist1;
	}

	public static void main(String[] args) {
		PopFileDialog p = new PopFileDialog();
		String exhange = "1";
		p.showdlg();
		// p.display(String exchange);
		// p.storeRecord();
	}

	/**
	 * to get the hashtable containing stock(key) and last price values for
	 * stock on max date also set the rejection percentage for stock.
	 * 
	 * @param exchangeid
	 * @return
	 */
	public Hashtable checkRejection(String exchangeid) {
		int alertcount = 0;
		Hashtable lvalue_list = new Hashtable();
		Logging.debug("exchangeid is " + exchangeid);
		if (exchangeid != null) {
			try {
				System.out.println("Connetion conn***" + Connect.con);
				Connect connect1 = ConnectInit.getConnect();
				String query = ConnectInit.queries
						.getProperty("get_alert_on_price_import");
				System.out.println("Check Reject method query ***" + query);
				PreparedStatement alertpst = null;
				if (Connect.con != null) {
					alertpst = Connect.con.prepareStatement(query);
				} else {
					alertpst = connect1.getdbConnection().prepareStatement(
							query);
				}

				alertpst.setString(1, exchangeid);
				System.out.println("Check Reject method repare statement ***"
						+ alertpst);
				Logging.debug("before  executeUpdate " + alertpst);
				ResultSet alertresult = alertpst.executeQuery();
				System.out.println("Check Reject method resultset *** "
						+ alertresult);
				if (alertresult != null) {
					while (alertresult.next()) {
						// Logging.getDebug("stockid is "+alertresult.getString(1));
						lvalue_list.put(alertresult.getString(1),
								alertresult.getString(2));
						reject_per_list.put(alertresult.getString(1),
								alertresult.getString(3));
					}
					System.out.println("Value_list ***" + lvalue_list);
					System.out.println("reject_per_list ***" + reject_per_list);
				}
			} catch (Exception e) {
				Logging.error("Error : " + e.getMessage());
			}
		}
		return lvalue_list;
	}

	/**
	 * to get the max date on which prices are avialable.
	 * 
	 * @param exchangeid
	 * @return
	 */
	public String getMaxDate(String exchangeid) {
		String mdate = null;
		Logging.debug("exchangeid is " + exchangeid);
		if (exchangeid != null) {
			try {
				Connect connect1 = ConnectInit.getConnect();
				PreparedStatement alertpst = Connect.con
						.prepareStatement(ConnectInit.queries
								.getProperty("get_maxdate_for_which_prices_exist"));
				alertpst.setString(1, exchangeid);
				ResultSet alertresult = alertpst.executeQuery();
				while (alertresult.next()) {
					mdate = alertresult.getString(1);
					Logging.debug("after formatting mdate is " + mdate);
				}
				// mdate=formatDate(mdate);
				// Logging.getDebug("after formatting mdate is "+mdate);
			} catch (Exception e) {
				Logging.error("Error : " + e.getMessage());
			}
		}
		return mdate;
	}

	/**
	 * to get the max date on which prices are avialable.
	 * 
	 * @param exchangeid
	 * @return
	 */
	public Hashtable getCADStocks(String fdate) {
		String mdate = null;
		Hashtable stkCAD_list = new Hashtable();
		Logging.debug("exchangeid is " + fdate);
		if (fdate != null) {
			try {
				Connect connect1 = ConnectInit.getConnect();
				String query = ConnectInit.queries
						.getProperty("get_stockid_cad_exist");
				PreparedStatement alertpst = null;
				if (Connect.con != null) {
					alertpst = Connect.con.prepareStatement(query);
				} else {
					alertpst = connect1.getdbConnection().prepareStatement(
							query);
				}
				alertpst.setString(1, fdate);
				ResultSet alertresult = alertpst.executeQuery();
				while (alertresult.next()) {
					stkCAD_list.put(alertresult.getString(1),
							alertresult.getString(1));
				}
			} catch (Exception e) {
				Logging.error("Error : " + e.getMessage());
			}
		}
		return stkCAD_list;
	}

	/**
	 * @return Returns the table.
	 */
	public Hashtable getTable() {
		return table;
	}

	/**
	 * @param table
	 *            The table to set.
	 */
	public void setTable(Hashtable table) {
		this.table = table;
	}

	/**
	 * to get current date
	 * 
	 * @return
	 */
	public static String CurrentDate() {
		SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
		Date dt = new Date();
		return fr.format(dt).toString();
	}

	/**
	 * to get stock_id for stock in NSEI with series.
	 * 
	 * @param exchange_id
	 * @param key
	 * @param series
	 * @return
	 */
	public String getStockId_NSE(Connection connection, String exchange_id,
			String key, String series) {
		String stock_id = null;
		try {
			Connect connect1 = ConnectInit.getConnect();
			String ticker = (series.trim() + key.trim()).trim();
			Logging.debug("ticker in getStockId_NSE is " + ticker);
			PreparedStatement alertpst = connection
					.prepareStatement(ConnectInit.queries
							.getProperty("get_stockid_for_nsestock_with_series"));
			alertpst.setString(1, ticker);
			ResultSet alertresult = alertpst.executeQuery();
			while (alertresult.next()) {
				stock_id = alertresult.getString(1);
				Logging.debug("stockid in getStockId_NSE is " + stock_id);
			}
			// mdate=formatDate(mdate);
			// Logging.getDebug("after formatting mdate is "+mdate);
		} catch (Exception e) {
			Logging.error("Error : " + e.getMessage());
		}
		return stock_id;
	}

	/**
	 * method to format date to format 'dd-mm-yyyy' input parameter and return
	 * of this method is string.
	 */
	private static String formatDate(String date) {
		Logging.debug("before date " + date);
		java.util.Date d = new java.util.Date(date.trim());
		// Logging.debug("After date");
		SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
		Logging.debug("After Simpledate" + fr.format(d).toString());
		return fr.format(d).toString();
		/*
		 * Date creationDate = new Date(new Integer(date.trim().substring(6,
		 * 10)).intValue(), new Integer(date.trim().substring(3, 5)).intValue(),
		 * new Integer(date.trim().substring(0, 2)).intValue());
		 * Logging.getDebug("After Simpledate"+creationDate.toString()); return
		 * creationDate.toString();
		 */
	}

	/**
	 * @return Returns the v_alert_reject.
	 */
	public static Vector getV_alert_reject() {
		return v_alert_reject;
	}

	public static int CompareDate(String basedate, String history_Date) {
		Date creationDate = new Date(new Integer(basedate.trim().substring(6,
				10)).intValue(),
				new Integer(basedate.trim().substring(3, 5)).intValue(),
				new Integer(basedate.trim().substring(0, 2)).intValue());
		Date hDate = new Date(
				new Integer(history_Date.trim().substring(6, 10)).intValue(),
				new Integer(history_Date.trim().substring(3, 5)).intValue(),
				new Integer(history_Date.trim().substring(0, 2)).intValue());
		int diff = creationDate.compareTo(hDate);
		return diff;
	}

	/**
	 * insert the details of unimported stock prices in stock_price_unimported.
	 */
	public static void insertUnimportedStockPrices(Connection connection,
			String key, String exc_id, String stock_id, String query,
			CStockPriceComponents form) {
		try {

			// Comment the below connection code : by Manoj Adekar
			/*
			 * Connect con = ConnectInit.getConnect(); if(Connect.con == null){
			 * con.getConnection(); //Connect.con = con1; }
			 */

			app.CStockPriceComponents form1 = (CStockPriceComponents) form;

			Logging.debug("Inside insert Stock price unimported");
			Logging.debug("Exchange id is " + exc_id + " key is " + key);
			PreparedStatement psmt = connection.prepareStatement(query);
			Logging.debug("in query psmt is " + psmt);
			/*
			 * Statement stmt = Connect.con.createStatement();
			 * Logging.getDebug("in query stmt is "+stmt);
			 */
			ResultSet rs = null;
			/*
			 * int para=0; rs= stmt.executeQuery("SELECT NEXTVAL('spu_id')");
			 * Logging.getDebug("rs is "+rs); while(rs.next()){
			 * Logging.getDebug("inside rs.next() para is "+rs.getInt(1));
			 * para=rs.getInt(1); Logging.getDebug("para is "+para); }
			 * psmt.setInt(1,para);//smu_id (stock_master_unimported id).
			 * Logging.getDebug("para is "+para); rs.close();
			 */
			psmt.setString(1, stock_id);// stock_id
			psmt.setString(2, key);// symbol
			psmt.setString(3, form1.getStr_series());// series (in case of NSEI)
			psmt.setString(4, form1.getCusip());// cusip code.
			Logging.debug("form1.getCusip() is " + form1.getCusip()
					+ " form1.getSeries() is " + form1.getStr_series());
			psmt.setString(5, form1.getStr_open());// isin code.
			psmt.setString(6, form1.getStr_high());// cusip code.
			psmt.setString(7, form1.getStr_low());// market lot.
			psmt.setString(8, form1.getStr_close());// paid_value
			psmt.setString(9, form1.getStr_last());// face_value
			psmt.setString(10, form1.getStr_prevClose());// face_value
			psmt.setString(11, form1.getStr_totTradedQty());// face_value
			psmt.setString(12, form1.getStr_totTradedVol());// face_value
			psmt.setString(13, exc_id);// exchyange_id
			psmt.setString(14, form1.getStr_date());// exchyange_id
			psmt.executeUpdate();
			Logging.debug("after inserting values in stock_price_unimported");

		} catch (Exception e) {
			Logging.error("Error : " + e.getMessage());
		}
	}

	/**
	 * @return Returns the exchangeid.
	 */
	public static String getExchangeid() {
		return exchangeid;
	}

	/**
	 * @param exchangeid
	 *            The exchangeid to set.
	 */
	public static void setExchangeid(String exchangeid) {
		PopFileDialog.exchangeid = exchangeid;
	}

	/**
	 * @return Returns the filetype.
	 */
	public static String getFiletype() {

		return filetype;
	}

	/**
	 * @param filetype
	 *            The filetype to set.
	 */
	public static void setFiletype(String filetype) {
		PopFileDialog.filetype = filetype;
	}

	/**
	 * to check if prices already available on filedate. return true if prices
	 * already available on filedate else return false.
	 * 
	 * @param exch_id
	 * @return
	 */
	public boolean CheckForPricesOnSameDate(String exch_id) {
		boolean flag = false;
		try {
			Connect connect1 = ConnectInit.getConnect();
			Logging.debug("fdate is " + filedate);
			PreparedStatement alertpst = Connect.con
					.prepareStatement(ConnectInit.queries
							.getProperty("importFile.check_stock_prices_on_date"));
			alertpst.setString(1, exch_id);
			alertpst.setString(2, filedate);
			ResultSet alertresult = alertpst.executeQuery();
			while (alertresult.next()) {
				flag = true;
				break;
			}
			// mdate=formatDate(mdate);
			// Logging.getDebug("after formatting mdate is "+mdate);
		} catch (Exception e) {
			Logging.error("Error : " + e.getMessage());
		}
		return flag;
	}

	/**
	 * @return Returns the hist_date.
	 */
	public static String getHist_date() {
		Logging.info("Historical date is :" + hist_date);
		return hist_date;
	}

	/**
	 * @param hist_date
	 *            The hist_date to set.
	 */
	public static void setHist_date(String hist_date) {
		PopFileDialog.hist_date = hist_date;
	}

	/**
	 * @return Returns the correctedFile.
	 */
	public static String getCorrectedFile() {
		return correctedFile;
	}

	/**
	 * @param correctedFile
	 *            The correctedFile to set.
	 */
	public static void setCorrectedFile(String correctedFile) {
		PopFileDialog.correctedFile = correctedFile;
	}

	/**
	 * method to Display bhavcopy by ftp file data into database for all the
	 * stocks present in the selected bhavcopy file modifed by Ashish Dated
	 * 28/08/2006
	 * 
	 */
	public StringBuffer bhvdisplay(String exchange_id, String fileName) {
		StringBuffer buffer = new StringBuffer();
		Logging.debug("In while mkt run");
		// try{
		int j = 1;
		Logging.debug("In while loooooop");
		String Symbol1;
		String Series1;
		String Trade_High_Price1;
		String Trade_Low_Price1;
		String Opening_Price1;
		String Closing_Price1;
		String Previous_Close_Price1;
		String Total_Traded_Quantity1;
		String Total_Traded_Value1;
		String Carriage_Return1 = "0.00";
		String Symbol;
		String Series;
		String Trade_High_Price;
		String Trade_Low_Price;
		String Opening_Price;
		String Closing_Price;
		String Previous_Close_Price;
		String Total_Traded_Quantity;
		String Total_Traded_Value;
		String Carriage_Return;
		String Message_CR = null;
		// String Message1=null;
		// String Message11=null;
		char ommess = 0;

		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			String str = null;
			String str1 = null;
			String token = null;
			String[] arr = new String[50];
			String[] arr1 = new String[100];
			int l = 0;
			int i = 0;

			String style = null;
			buffer.append("<tr width = '100%'>");
			style = "gridStyle-header color='blue' ";

			buffer.append("<td width='7%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("SYMBOL");// symbol.
			buffer.append("</td>");

			buffer.append("<td width='9%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("SERIES");// closing price taken as last.
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("Trade High Price");// tis used as traded volume to
												// display.
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("Trade Low Price");// market capital value.
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("Open Price");// market capital value.
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("Close Price");// market capital value.
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("Previous Close");// market capital value.
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("Total Trade Qut");// market capital value.
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("Total Traded Vale");// market capital value.
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("Carriage Return");// market capital value.
			buffer.append("</td>");
			// buffer.append("</font>");
			buffer.append("</tr>");
			buffer.append("<tr>");
			FileReader file = new FileReader("D://DownLoadBhav//" + fileName);
			BufferedReader br = new BufferedReader(file);
			while ((str = br.readLine()) != null) {
				Logging.debug("`````````str is " + str.trim());
				str1 = str.trim();
				StringTokenizer stk = new StringTokenizer(str1.toString(), " ");
				priceComp = new CStockPriceComponents();
				String textname = null;
				i = 0;
				// Bhav bhav = new Bhav();
				while (stk.hasMoreTokens()) {
					token = stk.nextToken();
					arr1[i] = token;
					Logging.debug("strin tokaneiser*************+"
							+ stk.countTokens());
					Logging.debug("Strin token*************+" + token);
					i++;
				}
				String first = arr1[0].substring(arr1[0].length() - 2,
						arr1[0].length());
				String currDate = QueryClass.formatDate();
				Logging.debug("Strin token*************+" + first);
				if (first.equals("EQ") || first.equals("BE")
						|| first.equals("IL") || first.equals("P1")
						|| first.equals("N1")) {
					Symbol1 = arr1[0].substring(0, arr1[0].length() - 2);
					Logging.debug("Smbole@@@@@@@@2" + Symbol1);
					Series1 = first;
					Logging.debug("Series@@@@@@@@2" + Series1);
					Trade_High_Price1 = arr1[1];
					Logging.debug("Trade_High_Price1@@@@@@@@2"
							+ Trade_High_Price1);
					Trade_Low_Price1 = arr1[2];
					Logging.debug("Trade_Low_Price1@@@@@@@@2"
							+ Trade_Low_Price1);
					Opening_Price1 = arr1[3];
					Logging.debug("Opening_Price1@@@@@@@@2" + Opening_Price1);
					Closing_Price1 = arr1[4];
					Logging.debug("Closing_Price1@@@@@@@@2" + Closing_Price1);
					Previous_Close_Price1 = arr1[5];
					Logging.debug("Previous_Close_Price1@@@@@@@@2"
							+ Previous_Close_Price1);
					Total_Traded_Quantity1 = arr1[6];
					Logging.debug("Total_Traded_Quantity1@@@@@@@@2"
							+ Total_Traded_Quantity1);
					Total_Traded_Value1 = arr1[7];
					Logging.debug("Total_Traded_Value1@@@@@@@@2"
							+ Total_Traded_Value1);
					String str11 = Symbol1;
					priceComp.setStr_symbol(str11);

					buffer.append("<td> ");
					buffer.append(str11);
					buffer.append(" </td>");
					priceComp.setStr_series(Series1);
					buffer.append("<td> ");
					buffer.append(Series1);
					buffer.append(" </td>");
					priceComp.setStr_high(Trade_High_Price1);
					buffer.append("<td> ");
					buffer.append(Trade_High_Price1);
					buffer.append(" </td>");
					priceComp.setStr_low(Trade_Low_Price1);
					buffer.append("<td> ");
					buffer.append(Trade_Low_Price1);
					buffer.append(" </td>");
					priceComp.setStr_open(Opening_Price1);
					buffer.append("<td> ");
					buffer.append(Opening_Price1);
					buffer.append(" </td>");
					priceComp.setStr_close(Closing_Price1);
					buffer.append("<td> ");
					buffer.append(Closing_Price1);
					buffer.append(" </td>");
					priceComp.setStr_prevClose(Previous_Close_Price1);
					priceComp.setStr_last(Previous_Close_Price1);
					buffer.append("<td> ");
					buffer.append(Previous_Close_Price1);
					buffer.append(" </td>");
					priceComp.setStr_totTradedQty(Total_Traded_Quantity1);
					buffer.append("<td> ");
					buffer.append(Total_Traded_Quantity1);
					buffer.append(" </td>");
					priceComp.setStr_totTradedVol(Total_Traded_Value1);
					buffer.append("<td> ");
					buffer.append(Total_Traded_Value1);
					buffer.append(" </td>");
					priceComp.setStr_time(currDate);
					buffer.append("<td> ");
					buffer.append(0.00);
					buffer.append(" </td>");

				} else {
					Symbol1 = arr1[0];
					Logging.debug("Symbol@@@@@@@@2" + Symbol1);
					Series1 = arr1[1];
					Logging.debug("Series@@@@@@@@2" + Series1);
					Trade_High_Price1 = arr1[2];
					Logging.debug("Trade_High_Price1@@@@@@@@2"
							+ Trade_High_Price1);
					Trade_Low_Price1 = arr1[3];
					Logging.debug("Trade_Low_Price1@@@@@@@@2"
							+ Trade_Low_Price1);
					Opening_Price1 = arr1[4];
					Logging.debug("Opening_Price1@@@@@@@@2" + Opening_Price1);
					Closing_Price1 = arr1[5];
					Logging.debug("Closing_Price1@@@@@@@@2" + Closing_Price1);
					Previous_Close_Price1 = arr1[6];
					Logging.debug("Previous_Close_Price1@@@@@@@@2"
							+ Previous_Close_Price1);
					Total_Traded_Quantity1 = arr1[7];
					Logging.debug("Total_Traded_Quantity1@@@@@@@@2"
							+ Total_Traded_Quantity1);
					Total_Traded_Value1 = arr1[8];
					Logging.debug("Total_Traded_Value1@@@@@@@@2"
							+ Total_Traded_Value1);
					String str11 = Symbol1;
					priceComp.setStr_symbol(str11);
					buffer.append("<tr>");
					buffer.append("<td> ");
					buffer.append(str11);
					buffer.append(" </td>");
					priceComp.setStr_series(Series1);
					buffer.append("<td> ");
					buffer.append(Series1);
					buffer.append(" </td>");
					priceComp.setStr_high(Trade_High_Price1);
					buffer.append("<td> ");
					buffer.append(Trade_High_Price1);
					buffer.append(" </td>");
					priceComp.setStr_low(Trade_Low_Price1);
					buffer.append("<td> ");
					buffer.append(Trade_Low_Price1);
					buffer.append(" </td>");
					priceComp.setStr_open(Opening_Price1);
					buffer.append("<td> ");
					buffer.append(Opening_Price1);
					buffer.append(" </td>");
					priceComp.setStr_close(Closing_Price1);
					buffer.append(" <td>");
					buffer.append(Closing_Price1);
					buffer.append("</td> ");
					priceComp.setStr_prevClose(Previous_Close_Price1);
					priceComp.setStr_last(Previous_Close_Price1);
					buffer.append("<td> ");
					buffer.append(Previous_Close_Price1);
					buffer.append(" </td>");
					priceComp.setStr_totTradedQty(Total_Traded_Quantity1);
					buffer.append("<td> ");
					buffer.append(Total_Traded_Quantity1);
					buffer.append(" </td>");
					priceComp.setStr_totTradedVol(Total_Traded_Value1);
					buffer.append("<td> ");
					buffer.append(Total_Traded_Value1);
					buffer.append(" </td>");
					priceComp.setStr_time(currDate);
					buffer.append("<td> ");
					buffer.append(0.00);
					buffer.append(" </td>");

				}
				Logging.debug("completed ***********  ");
				table.put(
						priceComp.getStr_series() + priceComp.getStr_symbol(),
						priceComp);
				Logging.debug("completed after puting to Hashtable***********  ");
				buffer.append("</tr>");
			}
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		}
		return buffer;
	}

	public String[] getLatestFinancDate(String stockId, Connection connection) {
		String[] rawData = new String[2];
		String origDate = "";
		String bseCode = "";
		String[] dateArr = new String[20];
		String yearS = "";
		String monthS = "";
		String maxDate = "";
		String tempDate2 = "";
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date d2 = null;

		Date d1 = null;
		int cnt = 0;
		Connect con = new Connect();
		try {
			PreparedStatement pstdate = connection
					.prepareStatement(ConnectInit.queries
							.getProperty("get_pe_pb_divident_from_earning"));
			pstdate.setString(1, stockId);
			pstdate.setString(2, stockId);
			ResultSet rstDate = pstdate.executeQuery();

			while (rstDate.next()) {
				dateArr[cnt] = rstDate.getString("finance_year");
				bseCode = rstDate.getString("bse_code");
				cnt++;
			}

			for (int k = 0; (k < cnt) && (dateArr[k] != null); k++) {
				yearS = dateArr[k].substring(0, 4);

				monthS = dateArr[k].substring(4, 6);

				maxDate = "01-" + monthS + "-" + yearS;

				d1 = df.parse(maxDate);

				for (int j = 0; (j < cnt) && (dateArr[j] != null); j++) {
					yearS = dateArr[j].substring(0, 4);
					monthS = dateArr[j].substring(4, 6);
					tempDate2 = "01-" + monthS + "-" + yearS;

					d2 = df.parse(tempDate2);

					if (d2.after(d1)) {
						d1 = d2;
						maxDate = df.format(d1);
					}

				}

			}

			if (cnt != 0) {
				origDate = maxDate.substring(maxDate.indexOf("-") + 1);
				origDate = origDate.substring(3, 7) + origDate.substring(0, 2);

				System.out.println("Original Date :" + origDate + "Max Date==>"
						+ maxDate);
			}

			rawData[0] = bseCode;
			rawData[1] = origDate;
		} catch (Exception e) {
			System.out.println("Error in MAX DATE : " + e.getMessage());
		}
		return rawData;
	}
}

// class implementing the file filter
class MyFilter extends javax.swing.filechooser.FileFilter {
	public boolean accept(File file) {
		String filename = file.getName();
		return filename.endsWith(".csv");
	}

	public String getDescription() {
		return "*.csv";
	}
}
