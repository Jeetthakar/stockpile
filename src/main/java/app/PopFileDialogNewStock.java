/**
 * PopFileDialogNewStock.java
 *@author Ashish Ingole
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import harrier.income.com.masters.CapturedIndexForm;
import harrier.income.com.masters.StockMasterForm;

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
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.harrier.ftp.Ind_FtpReadFile;
import com.harrier.ftp.MarketReadFile;
import com.harrier.ftp.MessageReadFile;
import com.harrier.ftp.SecurityFile;
import com.harrier.ftp.UploadInd;
import com.harrier.ftp.UploadMessageFile;
import com.harrier.ftp.UploadMkt;
import com.harrier.ftp.UploadSec;
import com.harrier.ftp.UplodeBhav;
import com.harrier.ftp.UplodeMasterSec;
import com.harrier.initializeation.ConnectInit;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;

public class PopFileDialogNewStock {
	static Logger Logging = Logger.getLogger(PopFileDialogNewStock.class);
	Hashtable targethashtable = new Hashtable();

	/**
	 * @return Returns the targethashtable.
	 */
	public Hashtable getTargethashtable() {
		return targethashtable;
	}

	/**
	 * @param targethashtable
	 *            The targethashtable to set.
	 */
	public void setTargethashtable(Hashtable targethashtable) {
		this.targethashtable = targethashtable;
	}

	private static String str_fileName = null;
	private static String str_filenamedbf = null;
	private StockMasterForm smform1 = new StockMasterForm();
	private Corporate corp = new Corporate();
	private String stock_exid = "", tempFileNameForDate;
	private String currency_id;
	private String country_id;
	private String correctedFile;
	private static ResultSet rst2 = null;
	private boolean FileFormatOK = true;
	public boolean checkPriceOnDate = false;
	private boolean ExchangeOK = true;
	String D1;
	Vector vector_importfilelist;
	private int stock_currency_id;
	private String identifier_code_id;
	private static String filedate = null;
	public Hashtable table = new Hashtable();// static
	private String file_type = null;
	private static String hist_Date = null;
	private String file_type_name = null;

	Connection con = null;
	PopFileDialog pfd = null;
	Ind_FtpReadFile ifr = null;
	SecurityFile secf = null;
	MarketReadFile mar = null;
	MessageReadFile msg = null;
	public Updatef uf = null;
	String fromIndexcomposition = null;
	public static Hashtable tablendbf = new Hashtable(300000);

	private String fromDownload = null;// to detect download button is pressed
										// for ftp files.
										// JFrame frame;

	/**
	 * @return Returns the fromIndexcomposition.
	 */
	public String getFromIndexcomposition() {
		return fromIndexcomposition;
	}

	/**
	 * @param fromIndexcomposition
	 *            The fromIndexcomposition to set.
	 */
	public void setFromIndexcomposition(String fromIndexcomposition) {
		this.fromIndexcomposition = fromIndexcomposition;
	}

	CapturedIndexForm cap_index, cap_index1;

	/**
	 * @return Returns the file_type.
	 */
	/**
	 * @return Returns the filedate.
	 */
	public static String getFiledate() {
		return filedate;
	}

	/**
	 * @param filedate
	 *            The filedate to set.
	 */
	public static void setFiledate(String filedate) {
		PopFileDialogNewStock.filedate = filedate;
	}

	public String getFile_type() {
		if (file_type == null) {
			file_type = "";
		}
		return file_type;
	}

	/**
	 * @param file_type
	 *            The file_type to set.
	 */
	public void setFile_type(String File_type) {
		Logging.debug("set File type is " + File_type);
		this.file_type = "";
		this.file_type = File_type;
	}

	/**
	 * @return str_fileName.
	 */
	public static String getStr_fileName() {
		return str_fileName;
	}

	/**
	 * @param str_fileName
	 *            The str_fileName to set.
	 */
	public static void setStr_fileName(String str_fileName) {
		// Logging.getDebug("File is set"+str_fileName);
		PopFileDialogNewStock.str_fileName = null;
		PopFileDialogNewStock.str_fileName = str_fileName;
	}

	/**
	 * method to check extension(like csv,dbf,xml) of file and returns true if
	 * chosen file extension is allowed.
	 */
	private boolean checkExt() {
		Logging.debug("Inside check ext");
		String temp = "";
		Logging.debug("file is null");
		temp = str_fileName.substring(str_fileName.lastIndexOf(".") + 1);
		Logging.debug(temp);
		if (temp.equals("csv") || temp.equals("xml") || temp.equals("dbf"))
			return true;
		else
			return false;
	}

	/**
	 * method to read and display the file content of files for stock
	 * master,corporate Action,index composition,captured indices,financial
	 * Deatils, currency exchange for NSEI as well as NYSE with extensions as
	 * csv and to fill the hashtable wilh file values.
	 */
	public StringBuffer display(HttpServletRequest request)// HttpServletRequest
															// request,HttpServletResponse
															// response)
	{
		System.out.println("Inside display stock_ex_id is with debug ***"
				+ stock_exid);
		Logging.debug("Inside display stock_ex_id is with debug" + stock_exid);
		/*
		 * str_fileName="c:/CoolMenus/"+ str_fileName; StringBuffer buffer=new
		 * StringBuffer();
		 */
		Updatef uf = new Updatef();// Object of class Updatef is created.
		String errorMessage;
		tempFileNameForDate = str_fileName;
		System.out.println("Display File Name is " + str_fileName);
		Logging.debug("Display File Name is " + str_fileName);
		Logging.debug("inside display");
		errorMessage = null;
		StringBuffer buffer = new StringBuffer(300000);
		Logging.debug("in stock_type 2 request.getParameter('event') is "
				+ request.getParameter("event"));
		// / thhis code is for the FTP
		if (request.getParameter("event") != null
				&& request.getParameter("event").equals("Downlode")) {
			if (file_type != null && !(file_type.equals(""))
					&& file_type.equals("2")) {
				pfd = new PopFileDialog();
				Logging.debug(" stock_exid is " + stock_exid
						+ " str_fileName is " + str_fileName);
				UplodeBhav upl = new UplodeBhav();
				String filename = upl.storeStockbhv();
				System.out.println("Filname to download****" + filename);
				if (filename == null) {
					/*
					 * frame = new JFrame();
					 * JOptionPane.showMessageDialog(frame,
					 * "Bhavcopy Not Available On Current Date ", "INFO!",
					 * JOptionPane.INFORMATION_MESSAGE);
					 */
					StringBuffer strbuffer = new StringBuffer();
					strbuffer.append("<tr width = '100%'>");
					String style = "gridStyle-header color='red' ";

					strbuffer.append("<td width='7%' align='center' class='"
							+ style + "' valign='center' height='12'>");
					strbuffer.append("Bhavcopy Not Available On Current Date");
					strbuffer.append("</td>");
					strbuffer.append("</tr>");
					buffer = strbuffer;
				} else {
					/*
					 * frame = new JFrame();
					 * JOptionPane.showMessageDialog(frame,
					 * filename+" File Uploaded from FTP ", "INFO!",
					 * JOptionPane.INFORMATION_MESSAGE);
					 */
					buffer = pfd.bhvdisplay(stock_exid, filename);
				}

				// checkPriceOnDate=CheckForPricesOnSameDate(stock_exid);
				file_type_name = "Bhavcopy";
				return buffer;
			}
			if (file_type != null && !(file_type.equals(""))
					&& file_type.equals("4")) {
				ifr = new Ind_FtpReadFile();
				setFromDownload("yes");
				Logging.debug(" stock_exid is " + stock_exid
						+ " str_fileName is " + str_fileName);
				UploadInd upl = new UploadInd();
				String filename = upl.getLatestIndFile();
				if (filename == null) {
					/*
					 * frame = new JFrame();
					 * JOptionPane.showMessageDialog(frame,
					 * "Bhavcopy Not Available On Current Date ", "INFO!",
					 * JOptionPane.INFORMATION_MESSAGE);
					 */
					StringBuffer strbuffer = new StringBuffer();
					strbuffer.append("<tr width = '100%'>");
					String style = "gridStyle-header color='red' ";

					strbuffer.append("<td width='7%' align='center' class='"
							+ style + "' valign='center' height='12'>");
					strbuffer.append(".ind File Not Available On Current Date");
					strbuffer.append("</td>");
					strbuffer.append("</tr>");
					buffer = strbuffer;
				} else {
					/*
					 * frame = new JFrame();
					 * JOptionPane.showMessageDialog(frame,
					 * filename+" File Uploaded from FTP ", "INFO!",
					 * JOptionPane.INFORMATION_MESSAGE);
					 */
					buffer = ifr.display_IndFtpFile(stock_exid, filename);
				}
				file_type_name = "CapInd";
				return buffer;
			}
			if (file_type != null && !(file_type.equals(""))
					&& file_type.equals("16")) {
				secf = new SecurityFile();
				Logging.debug(" stock_exid is " + stock_exid
						+ " str_fileName is " + str_fileName);
				UplodeMasterSec ups = new UplodeMasterSec();
				String filename = ups.upload();
				if (filename == null) {

					StringBuffer strbuffer = new StringBuffer();
					strbuffer.append("<tr width = '100%'>");
					String style = "gridStyle-header color='red' ";
					strbuffer.append("<td width='7%' align='center' class='"
							+ style + "' valign='center' height='12'>");
					strbuffer.append("Security Not Available On Current Date");
					strbuffer.append("</td>");
					strbuffer.append("</tr>");
					buffer = strbuffer;
				} else {
					buffer = secf.secdisplay(stock_exid, filename);
				}

				// checkPriceOnDate=CheckForPricesOnSameDate(stock_exid);
				file_type_name = null;
				return buffer;
			}
			if (file_type != null && !(file_type.equals(""))
					&& file_type.equals("17")) {
				secf = new SecurityFile();
				Logging.debug(" stock_exid is " + stock_exid
						+ " str_fileName is " + str_fileName);
				UploadSec ups = new UploadSec();
				String filename = ups.getLatestSecFile();
				if (filename == null) {

					StringBuffer strbuffer = new StringBuffer();
					strbuffer.append("<tr width = '100%'>");
					String style = "gridStyle-header color='red' ";
					strbuffer.append("<td width='7%' align='center' class='"
							+ style + "' valign='center' height='12'>");
					strbuffer.append("Security Not Available On Current Date");
					strbuffer.append("</td>");
					strbuffer.append("</tr>");
					buffer = strbuffer;
				} else {
					buffer = secf.secdisplay(stock_exid, filename);
				}

				// checkPriceOnDate=CheckForPricesOnSameDate(stock_exid);
				file_type_name = null;
				return buffer;
			}
			if (file_type != null && !(file_type.equals(""))
					&& file_type.equals("15")) {
				mar = new MarketReadFile();
				Logging.debug(" stock_exid is " + stock_exid
						+ " str_fileName is " + str_fileName);
				UploadMkt ups = new UploadMkt();
				String filename = ups.getLatestmktFile();
				if (filename == null) {

					StringBuffer strbuffer = new StringBuffer();
					strbuffer.append("<tr width = '100%'>");
					String style = "gridStyle-header color='red' ";
					strbuffer.append("<td width='7%' align='center' class='"
							+ style + "' valign='center' height='12'>");
					strbuffer
							.append("Market File Not Available On Current Date");
					strbuffer.append("</td>");
					strbuffer.append("</tr>");
					buffer = strbuffer;
				} else {
					buffer = mar.marketdisplay(stock_exid, filename);
				}

				// checkPriceOnDate=CheckForPricesOnSameDate(stock_exid);
				file_type_name = "MarketFile";
				return buffer;
			}
			if (file_type != null && !(file_type.equals(""))
					&& file_type.equals("18")) {
				msg = new MessageReadFile();
				Logging.debug(" stock_exid is " + stock_exid
						+ " str_fileName is " + str_fileName);
				UploadMessageFile ups = new UploadMessageFile();
				String filename = ups.getLatestMessageFile();
				if (filename == null) {

					StringBuffer strbuffer = new StringBuffer();
					strbuffer.append("<tr width = '100%'>");
					String style = "gridStyle-header color='red' ";
					strbuffer.append("<td width='7%' align='center' class='"
							+ style + "' valign='center' height='12'>");
					strbuffer
							.append("Message File Not Available On Current Date");
					strbuffer.append("</td>");
					strbuffer.append("</tr>");
					buffer = strbuffer;
				} else {
					buffer = msg.msgdisplay(stock_exid, filename);
				}

				// checkPriceOnDate=CheckForPricesOnSameDate(stock_exid);
				file_type_name = "MessageFile";
				return buffer;
			}

		}

		// /End this code is for the FTP

		String path = Connect.getCoolMenuspath();
		Logging.debug("path is : " + path);
		String str_dirName = path + "CoolMenus/";// get directory path upto
													// CoolMenus Folder.
		Logging.debug(str_dirName);
		Logging.debug("file name is using or " + str_fileName);
		if ((str_fileName == null) || (str_fileName.trim().length() == 0)) {
			Logging.debug("file is null using or");
			return null;
		}

		str_fileName = str_dirName + str_fileName;
		/*
		 * if(!checkExt()){ buffer.append(
		 * "<font size='2' face='Arial' color='Red'><tr>Please Choose *.csv/  *.dbf/ *.xml File Extension</tr></font>"
		 * ); return buffer; }
		 */
		Logging.debug("File Nmae is " + str_fileName);
		ExchangeOK = true;
		Logging.debug("ExchangeOK = true;" + stock_exid);

		if (stock_exid == null) {
			// Logging.getDebug("Exchange is null "+ stock_exid);
			buffer.append("<font size='2' face='Arial' color='Red'><tr>Invalid Exchange"
					+ stock_exid + "</tr></font>");
			ExchangeOK = false;
		}
		if (stock_exid.equals("")) {
			if (ExchangeOK == true) {
				// Logging.getDebug("Exchange is blank  "+ stock_exid);
				buffer.append("<font size='2' face='Arial' color='Red'><tr>Invalid Exchange "
						+ stock_exid + "</tr></font>");
				ExchangeOK = false;
			}
		}
		if (stock_exid.equals("0")) {
			if (ExchangeOK == true) {
				// Logging.getDebug("Exchange is default zero  "+ stock_exid);
				buffer.append("<font size='2' face='Arial' color='Red'><tr>Invalid Exchange "
						+ stock_exid + "</tr></font>");
				ExchangeOK = false;
			}
		}
		String temp = "";
		temp = str_fileName.substring(str_fileName.lastIndexOf(".") + 1);
		if (file_type != null && !(file_type.equals(""))
				&& file_type.equals("2") && temp.equals("csv")) {
			Logging.debug("in stock_type 2");
			pfd = new PopFileDialog();
			Logging.debug("temp is " + temp + " stock_exid is " + stock_exid
					+ " str_fileName is " + str_fileName);
			buffer = pfd.display(temp, stock_exid, str_fileName);
			checkPriceOnDate = CheckForPricesOnSameDate(stock_exid);
			file_type_name = "Bhavcopy";
			return buffer;
		}
		if (file_type != null && !(file_type.equals(""))
				&& file_type.equals("2") && temp.equals("DBF")) {
			Logging.debug("in stock_type 2");
			pfd = new PopFileDialog();
			Logging.debug("temp is " + temp + " stock_exid is " + stock_exid
					+ " str_fileName is " + str_fileName);
			buffer = pfd.displaydbf(temp, stock_exid, str_fileName);
			checkPriceOnDate = CheckForPricesOnSameDate(stock_exid);
			file_type_name = "Bhavcopy";
			return buffer;
		}
		if (file_type != null && !(file_type.equals(""))
				&& file_type.equals("19") && temp.equals("csv")) {
			Logging.debug("in stock_type 2");
			pfd = new PopFileDialog();
			Logging.debug("temp is " + temp + " stock_exid is " + stock_exid
					+ " str_fileName is " + str_fileName);
			buffer = pfd.displayfi(temp, stock_exid, str_fileName);
			checkPriceOnDate = CheckForPricesOnSameDate(stock_exid);
			file_type_name = "Bhavcopy";
			return buffer;
		}
		/*
		 * if(!(stock_exid.equals("84"))) { if(ExchangeOK==true) {
		 * Logging.getDebug("Exchange is not 84  "+ stock_exid); buffer.append(
		 * "<font size='2' face='Arial' color='Red'><tr>Invalid Exchange"
		 * +stock_exid+ "</tr></font>" ); ExchangeOK =false; } }
		 */
		Logging.debug("Exchang id after ifs" + stock_exid);

		if (temp.equals("dbf")) {
			buffer = display_dbf(request, str_fileName, temp);
		}
		if (temp.equals("xml")) {
			buffer = display_xml(request);
		}
		if (temp.equals("txt")) {
			buffer = display_txt(request);
		}
		if (temp.equals("csv")) {
			try {
				Logging.debug("File path is " + str_fileName);
				FileReader file = new FileReader(str_fileName);
				BufferedReader br = new BufferedReader(file);
				CapturedIndexForm cap_index = new CapturedIndexForm();
				String str = "";
				int i;
				// int nseNewStockfileflag=1;
				FileFormatOK = true;
				if (file_type == null) {
					FileFormatOK = false;
					buffer.append("<font size='2' face='Arial' color='Red'><tr>File with improper format</tr></font>");
					return buffer;
				}
				if (file_type.equals("")) {
					if (FileFormatOK == true) {
						buffer.append("<font size='2' face='Arial' color='Red'><tr>File with improper format </tr></font>");
						FileFormatOK = false;
						return buffer;
					}
				}
				if (file_type.equals("0")) {
					if (FileFormatOK == true) {
						buffer.append("<font size='2' face='Arial' color='Red'><tr>File with improper format </tr></font>");
						FileFormatOK = false;
						return buffer;
					}
				}
				Logging.debug("File type after ifs " + file_type);
				String[] arr;
				if (file_type.equals("3")) {
					String tempry = br.readLine();
					tempry = br.readLine();
					tempry = null;
				}

				if ((str = br.readLine()) != null) {
					arr = str.split(",");
					i = 0;
					file_type_name = null;
					Logging.debug("Before switch" + arr.length);
					/*
					 * arr.length gives number of fields in the csv file,and
					 * depending upon the number of fields the file_type is set.
					 */
					switch (arr.length) {
					case 2:
						if (file_type.equals("11"))
							file_type_name = "UpdateSeries";
						break;
					case 5:
						if (file_type.equals("12"))
							file_type_name = "PEDaily";
						break;
					case 6:
						if (file_type.equals("9"))
							file_type_name = "FDetailsNSE";
						if (file_type.equals("3"))
							file_type_name = "ICompose";
						Logging.debug("case is " + file_type_name);
						break;
					case 7:
						if (file_type.equals("1"))
							file_type_name = "NStock";// New stocks
						if (file_type.equals("5"))
							file_type_name = "CADiv";
						Logging.debug("If switch");
						break;

					// case added by samiksha
					case 8:
						if (file_type.equals("1"))
							file_type_name = "NStock";// New stocks
						if (file_type.equals("5"))
							file_type_name = "CADiv";
						Logging.debug("If switch");
						break;
					case 10:
						if (file_type.equals("8")) {
							file_type_name = "CrXrate";
							Logging.debug("Inside switch " + file_type_name);
							break;
						}
						if (file_type.equals("4")) // Captured Indices
						{
							file_type_name = "CapInd";
							Logging.debug("Cap index...............");
							break;
						}
					case 12:
						if (file_type.equals("13")) {
							file_type_name = "EPSFile";// NSEI EPS file.
						}
						Logging.debug("Inside switch with " + arr.length
								+ " EPSFile");
						break;
					case 37:
						if (file_type.equals("6"))
							file_type_name = "FDetails";
						break;
					case 16:
						if (file_type.equals("10")) {
							file_type_name = "FPriceNSEIndex";// NSE Price file
																// with Index
																// detail
						}
						Logging.debug("Inside switch with " + arr.length
								+ " FPriceNSEIndex");
						if (file_type.equals("1")) {
							file_type_name = "NyseStock";// NYSE Add New Stock
						}
						Logging.debug("Inside switch with " + arr.length
								+ " NyseStock");
						break;
					case 25:
						if (file_type.equals("5")) {
							file_type_name = "NyseCADiv";// NYSE Add New Stock
						}
						Logging.debug("Inside switch with " + arr.length
								+ " NyseCADiv");
						break;
					case 15:
						if (file_type.equals("14")) {
							file_type_name = "F&O File";
						}
						Logging.debug("Inside switch with " + arr.length
								+ "F & O file ");
						break;
					default:
						FileFormatOK = false;
						Logging.debug("Inside switch with " + arr.length
								+ " No cases matched");
						break;
					}
					if (FileFormatOK == false) {
						buffer.append("<font size='2' face='Arial' color='Red'><tr>File with improper format </tr></font>");
						FileFormatOK = true;
						return buffer;
					}
					buffer.append("<tr>");
					while (i < arr.length) {
						// Logging.getDebug("Inside while of First Readline");
						buffer.append("<td>");
						buffer.append("<font size='2' face='Arial' color='Blue'>"
								+ arr[i] + "</font>");
						buffer.append("</td>");
						i++;
					}
					buffer.append("</tr>");
				}
				Logging.debug("Calling UpdateSeries");
				if (file_type.equals("11") && file_type_name != null
						&& file_type_name.equals("UpdateSeries")) {
					Logging.debug("Calling UpdateSeries");
					return UpdateSeriesReadFile.getHashnBuffer(buffer, br);

				}
				Logging.debug("Calling F&O File");
				if (file_type.equals("14") && file_type_name != null
						&& file_type_name.equals("F&O File")) {
					Logging.debug("Calling F&O File");
					/*
					 * Calls the function getHashnBufferCsv, which is used to
					 * insert data into csv file. The function also inserts
					 * unique hashkey in hash table and returns buffer object.
					 */
					return Updatef.getHashnBufferCsv(buffer, br, temp);

				}
				if (file_type.equals("12") && file_type_name != null
						&& file_type_name.equals("PEDaily")) {
					Logging.debug("Calling PEDailyReadFile");
					return PEDailyReadFile.getHashnBuffer(buffer, br);

				}

				if (file_type.equals("13") && file_type_name != null
						&& file_type_name.equals("EPSFile")) {
					Logging.debug("Calling EpsReadFile");
					return EpsReadFile.getHashnBuffer(buffer, br);

				}
				Logging.debug("Calling price file index detail");
				if (file_type.equals("10") && file_type_name != null
						&& file_type_name.equals("FPriceNSEIndex")) {
					Logging.debug("Calling price file index detail");
					filedate = QueryClass.formatDate();
					buffer = NseIndexDetailReadFile.getHashnBuffer(buffer, br);
					checkPriceOnDate = PopFileDialogNewStock
							.CheckForPricesOnSameDate(stock_exid);
					Logging.debug("filedate is " + filedate
							+ " checkPriceOnDate is " + checkPriceOnDate);
					return buffer;
				}
				Logging.debug("Calling FinancialDetailReadFile1NSE");
				if (file_type.equals("9") && file_type_name != null
						&& file_type_name.equals("FDetailsNSE")) {
					Logging.debug("Calling FinancialDetailReadFile1s");
					return FinancialDetailReadFile.getHashnBuffer(buffer, br);

				}
				Logging.debug("Calling FinancialDetailReadFile1 ");
				if (file_type.equals("6") && file_type_name != null
						&& file_type_name.equals("FDetails")) {
					Logging.debug("Calling FinancialDetailReadFile1s");
					return FinancialDetailReadFile.getHashnBuffer(buffer, br);

				}
				Logging.debug("Calling Currency Exchange Rate1");
				if (file_type.equals("8") && file_type_name.equals("CrXrate")) {
					Logging.debug("Calling Currency Exchange Rate1");
					Logging.debug(br.toString());
					buffer = CrExchangeRateFile.getHashnBuffer(buffer, br);
					return buffer;
				}
				Logging.debug("After return of fin,xrate");
				if (file_type.equals("3")) {
					file_type_name = "ICompose";
					str = br.readLine();
				}
				while ((str = br.readLine()) != null)// reads from file line by
														// line.
				{
					StockMasterForm smform = new StockMasterForm();
					if (file_type.equals("4")
							|| (file_type_name != null && file_type_name
									.equals("CapInd"))) {
						cap_index = new CapturedIndexForm();
					}
					Corporate corp = new Corporate();
					String keyforCA = null;
					int id;
					String CompanyName, IndName, symbol, series, isin, ExchangeName;// double
																					// iwf;double
																					// ltp;String
																					// currency;long
																					// tis;long
																					// market_lot;String
																					// date;
					String INSTRUMENT, SYMBOL, EXPIRY_DT, STRIKE_PR, OPTION_TYP, OPEN, HIGH, LOW, CLOSE, SETTLE_PR, CONTRACTS, VAL_INLAKH, OPEN_INT, CHG_IN_OI, TIMESTAMP;
					arr = str.split(",");
					// Logging.getDebug("array length"+arr.length);
					buffer.append("<tr>");
					i = 0;
					if (arr.length == 0)
						continue;
					int arrlen = 0;
					arrlen = arr.length;
					while (i < arrlen) {
						if (file_type_name != null
								&& file_type_name.equals("ICompose"))// set bean
																		// for
																		// index
																		// composition
																		// file
																		// for
																		// NSEI
																		// exchange.
						{
							switch (i) {
							case 0:
								CompanyName = arr[i];
								break;
							case 1:
								IndName = arr[i];
								break;
							case 2:
								symbol = arr[i];
								break;
							case 3:
								series = arr[i];
								break;
							case 4:
								isin = arr[i];
								break;
							case 5:
								ExchangeName = arr[i];
								break;
							}
						}
						if (file_type_name != null
								&& file_type_name.equals("NStock"))// set bean
																	// for stock
																	// master
																	// file for
																	// NSEI
																	// exchange.
						{
							switch (i) {
							case 0:
								smform.setB_exc_code(arr[i]);
								break;
							case 1:
								smform.setS_stockName(arr[i]);
								break;
							case 2:
								smform.setS_stockType(arr[i]);
								smform.setSeries(arr[i]);// in case of NSE
								break;
							case 3:
								smform.setD_paidValue(arr[i]);
								break;
							case 4:
								smform.setS_marketLot(arr[i]);
								break;
							case 5:
								smform.setB_isn(arr[i]);
								break;
							case 6:
								smform.setF_faceValue(arr[i]);
								break;
							}
						}
						if (file_type_name != null
								&& file_type_name.equals("NyseStock"))// set
																		// bean
																		// for
																		// stock
																		// master
																		// file
																		// for
																		// NYSE
																		// exchange.
						{
							switch (i) {
							case 0:
								smform.setB_exc_code(arr[i]);
								break;
							case 1:
								smform.setS_stockName(arr[i]);
								break;
							case 2:
								smform.setB_csp(arr[i]);// set cusip code
								break;
							case 4:
								smform.setF_issuedShares(arr[i]);
								break;
							default:
								break;
							}
						}
						if (file_type_name != null
								&& file_type_name.equals("CADiv"))// set bean
																	// for
																	// corporate
																	// action
																	// file for
																	// NSEI
																	// exchange.
						{
							switch (i) {
							case 0:
								corp.setSymbol(arr[i]);
								break;
							case 1:
								corp.setSeries(arr[i]);
								break;
							/*
							 * case 2: corp.setBc_start(arr[i]); break; case 3:
							 * corp.setBc_end(arr[i]); break; case 4:
							 * corp.setRecord_date(arr[i]); break;
							 */
							case 2:
								corp.setEx_date(formatDate(arr[i]));
								corp.setApply_date(formatDate(arr[i]));
								break;
							/*
							 * case 6: corp.setNc_start(arr[i]); break; case 7:
							 * corp.setNc_end(arr[i]); break; case 8:
							 * corp.setRatio1(arr[i]); break; case 9:
							 * corp.setRatio2(arr[i]); break;
							 */
							case 3:
								corp.setFace(arr[i]);
								break;// do we need to check the face value
							case 4:
								corp.setPercent(arr[i]);
								break;
							case 5:
								corp.setAmt(arr[i]);
								break;
							case 6:
								corp.setDescription(arr[i]);
								break;
							}
						}
						if (file_type_name != null
								&& file_type_name.equals("NyseCADiv"))// set
																		// bean
																		// for
																		// corporate
																		// action
																		// file
																		// for
																		// NYSE
																		// exchange.
						{
							switch (i) {
							case 1:
								corp.setSymbol(arr[i]);
								// corp.setSeries(arr[i]);
								break;
							case 8:
								String date = arr[i];
								Logging.debug(" date in corp action is " + date);
								if (date != null && !(date.equals(""))) {
									corp.setEx_date(formatDate(date));
									corp.setApply_date(formatDate(date));
								}
								break;
							case 14:
								corp.setCorp_name(arr[i]);
								break;
							case 15:
								corp.setAmt(arr[i]);
								break;
							case 25:
								corp.setDescription(arr[i]);
								break;
							default:
								break;
							// provision for adding fields in database
							// case 9 -declared date
							// case 12 - record date
							// case 16 -divident currancy
							// case 18 -ratio to
							// case 19 -ratio for
							// case 22- removeFlag
							// case 23 -cancellation reason
							// case 24 -cancelled date
							}
						}
						if (file_type_name != null
								&& file_type_name.equals("CapInd"))// set bean
																	// for
																	// captured
																	// index.
						{
							switch (i) {
							case 0:
								cap_index.setIndex_name(arr[i]);
								break;
							case 1:
								cap_index.setIndex_value(arr[i]);
								Logging.debug("in........."
										+ cap_index.getOpen_value());
								break;
							case 2:
								cap_index.setOpen_value(arr[i]);
								break;
							case 3:
								cap_index.setHigh_value(arr[i]);
								break;
							case 4:
								cap_index.setLow_value(arr[i]);
								break;
							case 5:
								cap_index.setClosing_value(arr[i]);
								break;
							case 6:
								cap_index.setMkt_cap_value(arr[i]);
								break;
							case 7:
								cap_index.setDivisor_value(arr[i]);
								break;
							case 8:
								cap_index.setCurrancy_name(arr[i]);
								break;
							case 9:
								cap_index.setDate(arr[i]);
								filedate = (String) arr[i];
								break;
							}
						}

						if (arr[i].equals("")) {
							buffer.append("<td align='center'><font color='white'> ");
							buffer.append(".");
							buffer.append(" </font></td>");
						} else {
							buffer.append("<td> ");
							buffer.append(arr[i]);
							buffer.append(" </td>");
						}
						i++;
					}/* End of while(i<arrlen) */
					if ((file_type == "1") || (file_type.equals("1")))// populate
																		// Hashtable
																		// for
																		// NyseStock
																		// file
																		// type.
					{
						// if(!(file_type_name.equals("NyseStock"))){
						// Logging.getDebug("Inside file_type_name.equals(NyseStock) ");
						table.put(smform.getS_stockName(), smform);
						// }
					}
					if (file_type_name != null
							&& file_type_name.equals("CADiv"))// populate
																// Hashtable for
																// CADiv file
																// type.
					{
						Logging.debug(arr.length + " " + arr[0] + " " + arr[1]);
						keyforCA = arr[0] + ":" + arr[1];
						if (!(arr[0].equals("")))
							table.put(keyforCA, corp);
						Logging.debug(arr.length + " " + arr[0] + " " + arr[1]);
					}
					if (file_type_name != null
							&& file_type_name.equals("NyseCADiv"))// populate
																	// Hashtable
																	// for
																	// NyseCADiv
																	// file
																	// type.
					{
						Logging.debug("array length " + arr.length + " key "
								+ arr[0] + " " + arr[1]);
						keyforCA = arr[1];
						if (!(arr[1].equals("")))
							table.put(keyforCA, corp);
					}
					if (file_type_name != null
							&& file_type_name.equals("ICompose"))// populate
																	// Hashtable
																	// for
																	// ICompose
																	// file
																	// type.
					{
						Logging.debug("inside if filetypename ==icompose");
						String stockid = "";
						Logging.debug("get stock id for stock_name,symbol,series");
						Logging.debug("arr[2]=" + arr[2]);// + " "+ arr[0] +" "
															// +arr[3]+ " "
															// +arr[5]);
						if (arr[2].trim().length() > 0)
							stockid = getStockid(arr[0], arr[2], arr[3], arr[5]);
						// stockid=getStockid(CompanyName,symbol,series,ExchangeName);
						if (!(stockid.equals(""))) {
							Object obj = getStockObject(stockid);
							if (!(obj == null)) {
								Logging.debug("INside table.put" + stockid);
								table.put(stockid, (StockDetails) obj);
							}
						}
					}
					if (file_type_name != null
							&& file_type_name.equals("CapInd"))// populate
																// Hashtable for
																// captured
																// indices.
					{
						table.put(arr[0], cap_index);
						Logging.debug("in table put......." + table.size());
					}

					buffer.append("</tr>");
				}
				file.close();
			} catch (IOException e) {
				errorMessage = e.getMessage();
				Logging.error("Error : " + e.getMessage());
				e.getStackTrace();
				return null;
			}
		}/* End of If(Outer) */
		Logging.debug("Display Complete");
		/*
		 * if(file_type_name.equals("ICompose")) { ActionForward fr = null;
		 * ActionForwardParameters par = new ActionForwardParameters(); fr = new
		 * ActionForward(response
		 * .encodeURL("/pages/IndexHome.jsp?D1="+pid+"&B2=Compute")); return
		 * par.add(table).forward(fr); }
		 */
		// long start=new Date().;
		request.setAttribute("hashtable", table);
		return buffer;
	}/* End of IF(outer(outer) */

	/**
	 * method to read and display the file content of files for stock
	 * master,corporate Action,index composition,captured indices,financial
	 * Deatils, currency exchange for NSEI as well as NYSE with extensions as
	 * dbf and to fill the hashtable wilh file values.
	 */
	public StringBuffer display_dbf(HttpServletRequest request, String ram,
			String temp) {
		Logging.debug("Indisplay_dbf");
		String str_fileName = ram;
		StringBuffer buffer_dbf = new StringBuffer(300000);
		try {
			Logging.debug("File path is >>>>>>>" + str_fileName);
			InputStream inputstream = new FileInputStream(str_fileName);
			Logging.debug("after FileInputsream");
			DBFReader reader = new DBFReader(inputstream);
			int numberOfFields = reader.getFieldCount();
			CapturedIndexForm cap_index = new CapturedIndexForm();
			String str = "";
			int i;
			// int nseNewStockfileflag=1;
			FileFormatOK = true;
			if (file_type == null) {
				FileFormatOK = false;
				buffer_dbf
						.append("<font size='2' face='Arial' color='Red'><tr>File with improper format</tr></font>");
				return buffer_dbf;
			}
			if (file_type.equals("")) {
				if (FileFormatOK == true) {
					buffer_dbf
							.append("<font size='2' face='Arial' color='Red'><tr>File with improper format </tr></font>");
					FileFormatOK = false;
					return buffer_dbf;
				}
			}
			if (file_type.equals("0")) {
				if (FileFormatOK == true) {
					buffer_dbf
							.append("<font size='2' face='Arial' color='Red'><tr>File with improper format </tr></font>");
					FileFormatOK = false;
					return buffer_dbf;
				}
			}
			Logging.debug("File type after ifs " + file_type);
			Object[] rowObjects;
			int rowcount = 0;
			if ((rowObjects = reader.nextRecord()) != null) {
				i = 0;
				rowcount++;
				file_type_name = "";
				Logging.debug("Before switch" + numberOfFields);
				/*
				 * numberOfFields gives number of fields in the dbf file,and
				 * depending upon the number of fields the file_type is set.
				 */
				switch (numberOfFields) {
				case 5:
					if (file_type.equals("3"))
						file_type_name = "ICompose";
					Logging.debug("case is ICompose");
					break;
				case 6:
					if (file_type.equals("9"))
						file_type_name = "FDetailsNSE";
					break;
				case 7:
					if (file_type.equals("1"))
						file_type_name = "NStock";// New stocks
					if (file_type.equals("5"))
						file_type_name = "CADiv";
					Logging.debug("If switch");
					break;
				case 10:
					if (file_type.equals("8")) {
						file_type_name = "CrXrate";
						Logging.debug("Inside switch " + file_type_name);
						break;
					}
					if (file_type.equals("4")) // Captured Indices
					{
						file_type_name = "CapInd";
						Logging.debug("Cap index...............");
						break;
					}
				case 13:
					if (file_type.equals("13")) {
						file_type_name = "EPSFile";// NSEI EPS file.
					}
					Logging.debug("Inside switch with " + numberOfFields
							+ " EPSFile");
					break;
				case 37:
					if (file_type.equals("6"))
						file_type_name = "FDetails";
					break;
				case 16:
					if (file_type.equals("10")) {
						file_type_name = "FPriceNSEIndex";// NSE Price file with
															// Index detail
					}
					Logging.debug("Inside switch with " + numberOfFields
							+ " FPriceNSEIndex");
					if (file_type.equals("1")) {
						file_type_name = "NyseStock";// NYSE Add New Stock
					}
					Logging.debug("Inside switch with " + numberOfFields
							+ " NyseStock");
					break;
				case 25:
					if (file_type.equals("5")) {
						file_type_name = "NyseCADiv";// NYSE Add New Stock
					}
					Logging.debug("Inside switch with " + numberOfFields
							+ " NyseCADiv");
					break;
				case 15:
					if (file_type.equals("14")) {
						file_type_name = "F&O File";
					}
					Logging.debug("Inside switch with " + numberOfFields
							+ "F&O File ");
					break;
				default:
					FileFormatOK = false;
					Logging.debug("Inside switch with " + numberOfFields
							+ " No cases matched");
					break;
				}
				if (FileFormatOK == false) {
					buffer_dbf
							.append("<font size='2' face='Arial' color='Red'><tr>File with improper format </tr></font>");
					FileFormatOK = true;
					return buffer_dbf;
				}
				buffer_dbf.append("<tr>");
				if (rowcount == 1) {
					i = 0;
					while (i < numberOfFields) {
						// reads first line containing field names for dbf file.
						DBFField field = reader.getField(i++);
						buffer_dbf.append("<td>");
						buffer_dbf
								.append("<font size='2' face='Arial' color='Blue'>"
										+ field.getName() + "</font>");
						buffer_dbf.append("</td>");
					}
				} else {
					// reads first line containing values for dbf file.
					i = 0;
					while (i < numberOfFields) {
						// reads first line containing field names for dbf file.
						buffer_dbf.append("<td>");
						buffer_dbf
								.append("<font size='2' face='Arial' color='Blue'>"
										+ rowObjects[i++].toString().trim()
										+ "</font>");
						buffer_dbf.append("</td>");
					}
				}
				buffer_dbf.append("</tr>");
			}
			Logging.debug("Calling F&OReadFile1s");
			if (file_type_name != null && file_type_name.equals("F&O File")) {
				Logging.debug("Calling F&OReadFile1s");
				/*
				 * Calls the function getHashnBufferDbf, which is used to insert
				 * data into dbf file. The function also inserts unique hashkey
				 * in hash table and returns buffer object.
				 */
				return Updatef.getHashnBufferDbf(buffer_dbf, temp,
						str_fileName, rowObjects);
			}
			Logging.debug("Calling FinancialDetailReadFile1NSE");
			if (file_type_name != null && file_type_name.equals("FDetailsNSE")) {
				Logging.debug("Calling FinancialDetailReadFile1s");
				// return
				// FinancialDetailReadFile.getHashnBuffer(buffer_dbf,reader);

			}
			Logging.debug("Calling price file index detail");
			if (file_type.equals("10") && file_type_name != null
					&& file_type_name.equals("FPriceNSEIndex")) {
				Logging.debug("Calling price file index detail");
				filedate = QueryClass.formatDate();
				buffer_dbf = NseIndexDetailReadFile.getHashnBuffer(buffer_dbf,
						numberOfFields, str_fileName);
				checkPriceOnDate = PopFileDialogNewStock
						.CheckForPricesOnSameDate(stock_exid);
				Logging.debug("filedate is " + filedate
						+ " checkPriceOnDate is " + checkPriceOnDate);
				return buffer_dbf;
			}
			Logging.debug("Calling FinancialDetailReadFile1 ");
			if (file_type_name != null && file_type_name.equals("FDetails")) {
				Logging.debug("Calling FinancialDetailReadFile1s");
				// return
				// FinancialDetailReadFile.getHashnBuffer(buffer_dbf,reader);

			}
			Logging.debug("Calling Currency Exchange Rate1");
			if (file_type_name != null && file_type_name.equals("CrXrate")) {
				Logging.debug("Calling Currency Exchange Rate1");
				// buffer_dbf=
				// CrExchangeRateFile.getHashnBuffer(buffer_dbf,reader);
				return buffer_dbf;
			}
			Logging.debug("After return of fin,xrate");
			if (file_type.equals("3")) {
				file_type_name = "ICompose";
				// str=br.readLine();
			}
			FileInputStream inputStream1 = new FileInputStream(str_fileName);
			DBFReader reader1 = new DBFReader(inputStream1);
			int counterdbf = 1;
			while ((rowObjects = reader1.nextRecord()) != null) {
				StockMasterForm smform = new StockMasterForm();
				UpdatefDbform DF = new UpdatefDbform();
				if (file_type_name.equals("CapInd")) {
					cap_index = new CapturedIndexForm();
				}
				Corporate corp = new Corporate();
				String keyforCA = null;
				int id;
				String CompanyName, IndName, symbol, series, isin, ExchangeName;// double
																				// iwf;double
																				// ltp;String
																				// currency;long
																				// tis;long
																				// market_lot;String
																				// date;
				buffer_dbf.append("<tr>");
				i = 0;
				int arrlen = 0;
				arrlen = reader1.getFieldCount();
				if (arrlen == 0)
					continue;
				while (i < arrlen) {
					if (file_type_name != null
							&& file_type_name.equals("F&O File")) {
						switch (i) {
						case 0:
							DF.setInstrumentdbf(rowObjects[i].toString().trim());
							break;
						case 1:
							DF.setSymboldbf(rowObjects[i].toString().trim());
							break;
						case 2:
							DF.setExpr_dtdbf(rowObjects[i].toString().trim());
							break;
						case 3:
							DF.setStrike_prdbf(rowObjects[i].toString().trim());
							break;
						case 4:
							DF.setOption_typdbf(rowObjects[i].toString().trim());
							break;
						case 5:
							DF.setOpendbf(rowObjects[i].toString().trim());
							break;
						case 6:
							DF.setHighdbf(rowObjects[i].toString().trim());
							break;
						case 7:
							DF.setLowdbf(rowObjects[i].toString().trim());
							break;
						case 8:
							DF.setClosedbf(rowObjects[i].toString().trim());
							break;
						case 9:
							DF.setSettle_prdbf(rowObjects[i].toString().trim());
							break;
						case 10:
							DF.setContractsdbf(rowObjects[i].toString().trim());
							break;
						case 11:
							DF.setVal_inlakhdbf(rowObjects[i].toString().trim());
							break;
						case 12:
							DF.setOpen_intdbf(rowObjects[i].toString().trim());
							break;
						case 13:
							DF.setChange_in_oldbf(rowObjects[i].toString()
									.trim());
							break;
						case 14:
							DF.setTimestampdbf(rowObjects[i].toString().trim());
							break;

						}

					}
					if (file_type_name != null
							&& file_type_name.equals("ICompose"))// set bean for
																	// ICompose
																	// file
																	// type.
					{
						switch (i) {
						case 0:
							CompanyName = rowObjects[i].toString().trim();
							break;
						case 1:
							IndName = rowObjects[i].toString().trim();
							break;
						case 2:
							symbol = rowObjects[i].toString().trim();
							break;
						case 3:
							series = rowObjects[i].toString().trim();
							break;
						case 4:
							isin = rowObjects[i].toString().trim();
							break;
						case 5:
							isin = rowObjects[i].toString().trim();
							break;
						}
					}
					if (file_type_name != null
							&& file_type_name.equals("NStock"))// set bean for
																// NStock file
																// type.
					{
						switch (i) {
						case 0:
							smform.setB_exc_code(rowObjects[i].toString()
									.trim());
							break;
						case 1:
							smform.setS_stockName(rowObjects[i].toString()
									.trim());
							break;
						case 2:
							smform.setS_stockType(rowObjects[i].toString()
									.trim());
							smform.setSeries(rowObjects[i].toString().trim());// in
																				// case
																				// of
																				// NSE
							break;
						case 3:
							smform.setD_paidValue(rowObjects[i].toString()
									.trim());
							break;
						case 4:
							smform.setS_marketLot(rowObjects[i].toString()
									.trim());
							break;
						case 5:
							smform.setB_isn(rowObjects[i].toString().trim());
							break;
						case 6:
							smform.setF_faceValue(rowObjects[i].toString()
									.trim());
							break;
						}
					}
					if (file_type_name != null
							&& file_type_name.equals("NyseStock"))// set bean
																	// for
																	// NyseStock
																	// file
																	// type.
					{
						switch (i) {
						case 0:
							smform.setB_exc_code(rowObjects[i].toString()
									.trim());
							break;
						case 1:
							smform.setS_stockName(rowObjects[i].toString()
									.trim());
							break;
						case 2:
							smform.setB_csp(rowObjects[i].toString().trim());// set
																				// cusip
																				// code
							break;
						case 4:
							smform.setF_issuedShares(rowObjects[i].toString()
									.trim());
							break;
						default:
							break;
						}
					}
					if (file_type_name != null
							&& file_type_name.equals("CADiv"))// set bean for
																// CADiv file
																// type.
					{
						switch (i) {
						case 0:
							corp.setSymbol(rowObjects[i].toString().trim());
							break;
						case 1:
							corp.setSeries(rowObjects[i].toString().trim());
							break;
						case 2:
							corp.setEx_date(formatDate(rowObjects[i].toString()
									.trim()));
							corp.setApply_date(formatDate(rowObjects[i]
									.toString().trim()));
							break;
						case 3:
							corp.setFace(rowObjects[i].toString().trim());
							break;// do we need to check the face value
						case 4:
							corp.setPercent(rowObjects[i].toString().trim());
							break;
						case 5:
							corp.setAmt(rowObjects[i].toString().trim());
							break;
						case 6:
							corp.setDescription(rowObjects[i].toString().trim());
							break;
						}
					}
					if (file_type_name != null
							&& file_type_name.equals("NyseCADiv"))// set bean
																	// for
																	// NyseCADiv
																	// file
																	// type.
					{
						switch (i) {
						case 1:
							corp.setSymbol(rowObjects[i].toString().trim());
							// corp.setSeries(arr[i]);
							break;
						case 8:
							String date = rowObjects[i].toString().trim();
							Logging.debug(" date in corp action is " + date);
							if (date != null && !(date.equals(""))) {
								corp.setEx_date(formatDate(date));
								corp.setApply_date(formatDate(date));
							}
							break;
						case 14:
							corp.setCorp_name(rowObjects[i].toString().trim());
							break;
						case 15:
							corp.setAmt(rowObjects[i].toString().trim());
							break;
						case 25:
							corp.setDescription(rowObjects[i].toString().trim());
							break;
						default:
							break;
						// provision for adding fields in database
						// case 9 -declared date
						// case 12 - record date
						// case 16 -divident currancy
						// case 18 -ratio to
						// case 19 -ratio for
						// case 22- removeFlag
						// case 23 -cancellation reason
						// case 24 -cancelled date
						}
					}
					if (file_type_name != null
							&& file_type_name.equals("CapInd"))// set bean for
																// CapInd file
																// type.
					{
						switch (i) {
						case 0:
							cap_index.setIndex_name(rowObjects[i].toString()
									.trim());
							break;
						case 1:
							cap_index.setIndex_value(rowObjects[i].toString()
									.trim());
							Logging.debug("in........."
									+ cap_index.getOpen_value());
							break;
						case 2:
							cap_index.setOpen_value(rowObjects[i].toString()
									.trim());
							break;
						case 3:
							cap_index.setHigh_value(rowObjects[i].toString()
									.trim());
							break;
						case 4:
							cap_index.setLow_value(rowObjects[i].toString()
									.trim());
							break;
						case 5:
							cap_index.setClosing_value(rowObjects[i].toString()
									.trim());
							break;
						case 6:
							cap_index.setMkt_cap_value(rowObjects[i].toString()
									.trim());
							break;
						case 7:
							cap_index.setDivisor_value(rowObjects[i].toString()
									.trim());
							break;
						case 8:
							cap_index.setCurrancy_name(rowObjects[i].toString()
									.trim());
							break;
						case 9:
							cap_index.setDate(rowObjects[i].toString().trim());
							filedate = rowObjects[i].toString().trim();
							break;
						}
					}

					if (rowObjects[i].toString().trim().equals("")) {
						buffer_dbf
								.append("<td align='center'><font color='white'> ");
						buffer_dbf.append(".");
						buffer_dbf.append(" </font></td>");
					} else {
						buffer_dbf.append("<td> ");
						buffer_dbf.append(rowObjects[i].toString().trim());
						buffer_dbf.append(" </td>");
					}
					i++;
				}
				String keyfordbf = null;
				/*
				 * if(file_type_name!=null &&
				 * file_type_name.equals("F&O File")){ Logging.getDebug(" "
				 * +rowObjects[0].toString().trim() + " "
				 * +rowObjects[1].toString().trim());
				 * keyfordbf=rowObjects[0].toString
				 * ().trim()+":"+rowObjects[1].toString().trim();
				 * if(!((rowObjects[0].toString().trim()).equals("")))
				 * table.put(keyfordbf,DF); Logging.getDebug(" "
				 * +rowObjects[0].toString().trim() + " "
				 * +rowObjects[1].toString().trim());
				 * 
				 * }
				 */
				if ((file_type == "1") || (file_type.equals("1"))) {
					// if(!(file_type_name.equals("NyseStock"))){
					Logging.debug("Inside file_type_name.equals(NyseStock) ");
					table.put(smform.getS_stockName(), smform);
					// }
				}
				if (file_type_name != null && file_type_name.equals("CADiv")) {
					Logging.debug(" " + rowObjects[0].toString().trim() + " "
							+ rowObjects[1].toString().trim());
					keyforCA = rowObjects[0].toString().trim() + ":"
							+ rowObjects[1].toString().trim();
					if (!((rowObjects[0].toString().trim()).equals("")))
						table.put(keyforCA, corp);
					Logging.debug(" " + rowObjects[0].toString().trim() + " "
							+ rowObjects[1].toString().trim());
				}
				if (file_type_name != null
						&& file_type_name.equals("NyseCADiv")) {
					Logging.debug(" " + rowObjects[0].toString().trim() + " "
							+ rowObjects[1].toString().trim());
					keyforCA = rowObjects[1].toString().trim();
					if (!(keyforCA.equals("")))
						table.put(keyforCA, corp);
				}
				if (file_type_name != null && file_type_name.equals("ICompose")) {
					Logging.debug("inside if filetypename ==icompose");
					String stockid = "";
					Logging.debug("get stock id for stock_name,symbol,series");
					Logging.debug("rowObjects[2].toString().trim()="
							+ rowObjects[2].toString().trim());// + " "+ arr[0]
																// +" " +arr[3]+
																// " " +arr[5]);
					if ((rowObjects[2].toString().trim()).length() > 0)
						stockid = getStockid((rowObjects[0].toString().trim()),
								(rowObjects[2].toString().trim()),
								(rowObjects[3].toString().trim()),
								(rowObjects[5].toString().trim()));
					// stockid=getStockid(CompanyName,symbol,series,ExchangeName);
					if (!(stockid.equals(""))) {
						Object obj = getStockObject(stockid);
						if (!(obj == null)) {
							Logging.debug("INside table.put" + stockid);
							table.put(stockid, (StockDetails) obj);
						}
					}
				}
				if (file_type_name != null && file_type_name.equals("CapInd")) {
					table.put((rowObjects[0].toString().trim()), cap_index);
					Logging.debug("in table put......." + table.size());
				}
				if (file_type_name != null && file_type_name.equals("F&O File")) {
					if (DF.getSymboldbf() != null
							|| !(DF.getSymboldbf().equals(null))) {
						String key_symbol_dbf = DF.getSymboldbf().trim();
						String key_expr_date_dbf = DF.getExpr_dtdbf().trim();
						Integer c2 = new Integer(counterdbf);
						String countdbf = c2.toString().trim();
						String key_fr_hashtable_dbf = key_symbol_dbf
								+ key_expr_date_dbf + countdbf;
						tablendbf.put(key_fr_hashtable_dbf, DF);
						counterdbf++;
					}
				}
				buffer_dbf.append("</tr>");
			}
			inputstream.close();
		} catch (IOException e) {
			Logging.error("Error : " + e.getMessage());
			return null;
		}
		// request.setAttribute("hashtable",table);
		return buffer_dbf;
	}

	/**
	 * method to read and display the file content of files for stock
	 * master,corporate Action,index composition,captured indices,financial
	 * Deatils, currency exchange for NSEI as well as NYSE with extensions as
	 * xml and to fill the hashtable wilh file values.
	 */
	public StringBuffer display_xml(HttpServletRequest request) {
		StringBuffer buffer_xml = new StringBuffer();
		try {
			Logging.debug("File path is " + str_fileName);
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File(str_fileName));
			// normalize text representation
			doc.getDocumentElement().normalize();
			Logging.debug("Root element of the doc is "
					+ doc.getDocumentElement().getNodeName());
			CapturedIndexForm cap_index = new CapturedIndexForm();
			String[] colname = { "Col0", "Col1", "Col2", "Col3", "Col4",
					"Col5", "Col6", "Col7", "Col8", "Col9", "Col10", "Col11",
					"Col12", "Col13", "Col14", "Col15", "Col16", "Col17",
					"Col18", "Col19", "Col20", "Col21", "Col22", "Col23",
					"Col24" };

			NodeList listOfPersons = doc.getElementsByTagName("row");
			int totalPersons = listOfPersons.getLength();
			String str = "";
			// int nseNewStockfileflag=1;
			FileFormatOK = true;
			if (file_type == null) {
				FileFormatOK = false;
				buffer_xml
						.append("<font size='2' face='Arial' color='Red'><tr>File with improper format</tr></font>");
				return buffer_xml;
			}
			if (file_type.equals("")) {
				if (FileFormatOK == true) {
					buffer_xml
							.append("<font size='2' face='Arial' color='Red'><tr>File with improper format </tr></font>");
					FileFormatOK = false;
					return buffer_xml;
				}
			}
			if (file_type.equals("0")) {
				if (FileFormatOK == true) {
					buffer_xml
							.append("<font size='2' face='Arial' color='Red'><tr>File with improper format </tr></font>");
					FileFormatOK = false;
					return buffer_xml;
				}
			}
			Logging.debug("File type after ifs " + file_type + " totalPersons "
					+ totalPersons);
			String[] arr;
			int s = 0;
			while (s < 1) {
				file_type_name = "";
				Logging.debug(" row " + s);
				Node firstPersonNode = listOfPersons.item(s);
				if (firstPersonNode.getNodeType() == Node.ELEMENT_NODE) {
					Element firstPersonElement = (Element) firstPersonNode;
					int i = colname.length;
					Logging.debug("Before switch" + colname.length);
					switch (i)// work out using table file import and related
					{
					case 5:
						if (file_type.equals("3"))
							file_type_name = "ICompose";
						Logging.debug("case is ICompose");
						break;
					case 6:
						if (file_type.equals("9"))
							file_type_name = "FDetailsNSE";
						break;
					case 7:
						if (file_type.equals("1"))
							file_type_name = "NStock";// New stocks
						if (file_type.equals("5"))
							file_type_name = "CADiv";
						Logging.debug("If switch");
						break;
					case 10:
						if (file_type.equals("8")) {
							file_type_name = "CrXrate";
							Logging.debug("Inside switch " + file_type_name);
							break;
						}
						if (file_type.equals("4")) // Captured Indices
						{
							file_type_name = "CapInd";
							Logging.debug("Cap index...............");
							break;
						}

					case 37:
						if (file_type.equals("6"))
							file_type_name = "FDetails";
						break;
					case 16:
						if (file_type.equals("1")) {
							file_type_name = "NyseStock";// NYSE Add New Stock
						}
						Logging.debug("Inside switch with " + colname.length
								+ " NyseStock");
						break;
					case 25:
						if (file_type.equals("5")) {
							file_type_name = "NyseCADiv";// NYSE Add New Stock
						}
						Logging.debug("Inside switch with " + colname.length
								+ " NyseCADiv");
						break;
					default:
						FileFormatOK = false;
						Logging.debug("Inside switch with " + colname.length
								+ " No cases matched");
						break;
					}
					if (FileFormatOK == false) {
						buffer_xml
								.append("<font size='2' face='Arial' color='Red'><tr>File with improper format </tr></font>");
						FileFormatOK = true;
						return buffer_xml;
					}
					buffer_xml.append("<tr>");
					for (int j = 0; j < colname.length; j++) {
						{
							NodeList firstNameList = firstPersonElement
									.getElementsByTagName(colname[j]);
							Element firstNameElement = (Element) firstNameList
									.item(0);
							// Logging.debug("firstNameElement.getChildNodes() "+firstNameElement.getNodeValue());
							NodeList textFNList = firstNameElement
									.getChildNodes();
							String colvalue = ((Node) textFNList.item(0))
									.getNodeValue().trim();
							Logging.debug("colvalue is " + colvalue);
							if (colvalue == null)
								colvalue = " ";
							buffer_xml.append("<td>");
							buffer_xml
									.append("<font size='2' face='Arial' color='Blue'>"
											+ colvalue + "</font>");
							buffer_xml.append("</td>");
						}

					}
					buffer_xml.append("</tr>");
					s++;
					Logging.debug("After if first row " + s);

				}
				Logging.debug("Calling FinancialDetailReadFile1NSE");
				if (file_type_name != null
						&& file_type_name.equals("FDetailsNSE")) {
					Logging.debug("Calling FinancialDetailReadFile1s");
					// return
					// FinancialDetailReadFile.getHashnBuffer(buffer_xml,br);

				}
				Logging.debug("Calling FinancialDetailReadFile1 ");
				if (file_type_name != null && file_type_name.equals("FDetails")) {
					Logging.debug("Calling FinancialDetailReadFile1s");
					// return
					// FinancialDetailReadFile.getHashnBuffer(buffer_xml,br);

				}
				Logging.debug("Calling Currency Exchange Rate1");
				if (file_type_name != null && file_type_name.equals("CrXrate")) {
					Logging.debug("Calling Currency Exchange Rate1");
					// Logging.getDebug(br.toString());
					// buffer_xml=
					// CrExchangeRateFile.getHashnBuffer(buffer_xml,br);
					return buffer_xml;
				}
				Logging.debug("After return of fin,xrate");
				if (file_type.equals("3")) {
					file_type_name = "ICompose";
					// str=br.readLine();
				}
				for (s = 1; s < totalPersons; s++)// reads from file line by
													// line.
				{

					StockMasterForm smform = new StockMasterForm();
					if (file_type_name != null
							&& file_type_name.equals("CapInd")) {
						cap_index = new CapturedIndexForm();
					}
					Corporate corp = new Corporate();
					String keyforCA = null;
					int id;
					String CompanyName, IndName, symbol, series, isin, ExchangeName;// double
																					// iwf;double
																					// ltp;String
																					// currency;long
																					// tis;long
																					// market_lot;String
																					// date;
					arr = str.split(",");
					// Logging.getDebug("array length"+arr.length);
					buffer_xml.append("<tr>");
					if (arr.length == 0)
						continue;
					int arrlen = 0;
					arrlen = arr.length;
					Node firstPersonNode1 = listOfPersons.item(s);
					if (firstPersonNode1.getNodeType() == Node.ELEMENT_NODE) {
						Element firstPersonElement1 = (Element) firstPersonNode1;
						for (int k = 0; k < colname.length; k++) {
							NodeList firstNameList1 = firstPersonElement1
									.getElementsByTagName(colname[k]);
							Element firstNameElement1 = (Element) firstNameList1
									.item(0);
							// Logging.debug("firstNameElement.getChildNodes() "+firstNameElement.getNodeValue());
							NodeList textFNList1 = firstNameElement1
									.getChildNodes();
							String colvalue1 = ((Node) textFNList1.item(0))
									.getNodeValue().trim();
							if (file_type_name != null
									&& file_type_name.equals("ICompose"))// set
																			// bean
																			// for
																			// index
																			// composition
																			// file
																			// for
																			// NSEI
																			// exchange.
							{
								switch (k) {
								case 0:
									CompanyName = colvalue1;
									break;
								case 1:
									IndName = colvalue1;
									break;
								case 2:
									symbol = colvalue1;
									break;
								case 3:
									series = colvalue1;
									break;
								case 4:
									isin = colvalue1;
									break;
								case 5:
									isin = colvalue1;
									break;
								}
							}
							if (file_type_name != null
									&& file_type_name.equals("NStock"))// set
																		// bean
																		// for
																		// stock
																		// master
																		// file
																		// for
																		// NSEI
																		// exchange.
							{
								switch (k) {
								case 0:
									smform.setB_exc_code(colvalue1);
									break;
								case 1:
									smform.setS_stockName(colvalue1);
									break;
								case 2:
									smform.setS_stockType(colvalue1);
									smform.setSeries(colvalue1);// in case of
																// NSE
									break;
								case 3:
									smform.setD_paidValue(colvalue1);
									break;
								case 4:
									smform.setS_marketLot(colvalue1);
									break;
								case 5:
									smform.setB_isn(colvalue1);
									break;
								case 6:
									smform.setF_faceValue(colvalue1);
									break;
								}
							}
							if (file_type_name != null
									&& file_type_name.equals("NyseStock"))// set
																			// bean
																			// for
																			// stock
																			// master
																			// file
																			// for
																			// NYSE
																			// exchange.
							{
								switch (k) {
								case 0:
									smform.setB_exc_code(colvalue1);
									break;
								case 1:
									smform.setS_stockName(colvalue1);
									break;
								case 2:
									smform.setB_csp(colvalue1);// set cusip code
									break;
								case 4:
									smform.setF_issuedShares(colvalue1);
									break;
								default:
									break;
								}
							}
							if (file_type_name != null
									&& file_type_name.equals("CADiv"))// set
																		// bean
																		// for
																		// corporate
																		// action
																		// file
																		// for
																		// NSEI
																		// exchange.
							{
								switch (k) {
								case 0:
									corp.setSymbol(colvalue1);
									break;
								case 1:
									corp.setSeries(colvalue1);
									break;
								/*
								 * case 2: corp.setBc_start(arr[i]); break; case
								 * 3: corp.setBc_end(arr[i]); break; case 4:
								 * corp.setRecord_date(arr[i]); break;
								 */
								case 2:
									corp.setEx_date(formatDate(colvalue1));
									corp.setApply_date(formatDate(colvalue1));
									break;
								/*
								 * case 6: corp.setNc_start(arr[i]); break; case
								 * 7: corp.setNc_end(arr[i]); break; case 8:
								 * corp.setRatio1(arr[i]); break; case 9:
								 * corp.setRatio2(arr[i]); break;
								 */
								case 3:
									corp.setFace(colvalue1);
									break;// do we need to check the face value
								case 4:
									corp.setPercent(colvalue1);
									break;
								case 5:
									corp.setAmt(colvalue1);
									break;
								case 6:
									corp.setDescription(colvalue1);
									break;
								}
							}
							if (file_type_name != null
									&& file_type_name.equals("NyseCADiv"))// set
																			// bean
																			// for
																			// corporate
																			// action
																			// file
																			// for
																			// NYSE
																			// exchange.
							{
								switch (k) {
								case 1:
									corp.setSymbol(colvalue1);
									// corp.setSeries(arr[i]);
									break;
								case 8:
									String date = colvalue1;
									Logging.debug(" date in corp action is "
											+ date);
									if (date != null && !(date.equals(""))) {
										corp.setEx_date(formatDate(date));
										corp.setApply_date(formatDate(date));
									}
									break;
								case 14:
									corp.setCorp_name(colvalue1);
									break;
								case 15:
									corp.setAmt(colvalue1);
									break;
								case 25:
									corp.setDescription(colvalue1);
									break;
								default:
									break;
								// provision for adding fields in database
								// case 9 -declared date
								// case 12 - record date
								// case 16 -divident currancy
								// case 18 -ratio to
								// case 19 -ratio for
								// case 22- removeFlag
								// case 23 -cancellation reason
								// case 24 -cancelled date
								}
							}
							if (file_type_name != null
									&& file_type_name.equals("CapInd"))// set
																		// bean
																		// for
																		// captured
																		// index.
							{
								switch (k) {
								case 0:
									cap_index.setIndex_name(colvalue1);
									break;
								case 1:
									cap_index.setIndex_value(colvalue1);
									Logging.debug("in........."
											+ cap_index.getOpen_value());
									break;
								case 2:
									cap_index.setOpen_value(colvalue1);
									break;
								case 3:
									cap_index.setHigh_value(colvalue1);
									break;
								case 4:
									cap_index.setLow_value(colvalue1);
									break;
								case 5:
									cap_index.setClosing_value(colvalue1);
									break;
								case 6:
									cap_index.setMkt_cap_value(colvalue1);
									break;
								case 7:
									cap_index.setDivisor_value(colvalue1);
									break;
								case 8:
									cap_index.setCurrancy_name(colvalue1);
									break;
								case 9:
									cap_index.setDate(colvalue1);
									filedate = (String) colvalue1;
									break;
								}
							}

							if (colvalue1.equals("") || colvalue1 == null) {
								buffer_xml
										.append("<td align='center'><font color='white'> ");
								buffer_xml.append(".");
								buffer_xml.append(" </font></td>");
							} else {
								buffer_xml.append("<td> ");
								buffer_xml.append(colvalue1);
								buffer_xml.append(" </td>");
							}

						}
						if ((file_type == "1") || (file_type.equals("1")))// populate
																			// Hashtable
																			// for
																			// NyseStock
																			// file
																			// type.
						{
							// if(!(file_type_name.equals("NyseStock"))){
							Logging.debug("Inside file_type_name.equals(NyseStock) ");
							table.put(smform.getS_stockName(), smform);
							// }
						}

						if (file_type_name != null
								&& file_type_name.equals("CADiv"))// populate
																	// Hashtable
																	// for CADiv
																	// file
																	// type.
						{
							NodeList firstNameList11 = firstPersonElement1
									.getElementsByTagName(colname[0]);
							Element firstNameElement11 = (Element) firstNameList11
									.item(0);
							// Logging.debug("firstNameElement.getChildNodes() "+firstNameElement.getNodeValue());
							NodeList textFNList11 = firstNameElement11
									.getChildNodes();
							String arr0 = ((Node) textFNList11.item(0))
									.getNodeValue().trim();
							NodeList firstNameList12 = firstPersonElement1
									.getElementsByTagName(colname[0]);
							Element firstNameElement12 = (Element) firstNameList12
									.item(1);
							// Logging.debug("firstNameElement.getChildNodes() "+firstNameElement.getNodeValue());
							NodeList textFNList12 = firstNameElement12
									.getChildNodes();
							String arr1 = ((Node) textFNList12.item(0))
									.getNodeValue().trim();
							Logging.debug(arr.length + " " + arr[0] + " "
									+ arr[1]);
							keyforCA = arr0 + ":" + arr1;
							if (!(arr0.equals("")))
								table.put(keyforCA, corp);
							Logging.debug(arr.length + " " + arr0 + " " + arr1);
						}
						if (file_type_name != null
								&& file_type_name.equals("NyseCADiv"))// populate
																		// Hashtable
																		// for
																		// NyseCADiv
																		// file
																		// type.
						{
							NodeList firstNameList12 = firstPersonElement1
									.getElementsByTagName(colname[1]);
							Element firstNameElement12 = (Element) firstNameList12
									.item(1);
							// Logging.debug("firstNameElement.getChildNodes() "+firstNameElement.getNodeValue());
							NodeList textFNList12 = firstNameElement12
									.getChildNodes();
							String arr1 = ((Node) textFNList12.item(0))
									.getNodeValue().trim();
							keyforCA = arr1;
							if (!(arr1.equals("")))
								table.put(keyforCA, corp);
						}
						if (file_type_name != null
								&& file_type_name.equals("ICompose"))// populate
																		// Hashtable
																		// for
																		// ICompose
																		// file
																		// type.
						{
							Logging.debug("inside if filetypename ==icompose");
							String stockid = "";
							Logging.debug("get stock id for stock_name,symbol,series");
							Logging.debug("arr[2]=" + arr[2]);// + " "+ arr[0]
																// +" " +arr[3]+
																// " " +arr[5]);
							if (arr[2].trim().length() > 0)
								stockid = getStockid(arr[0], arr[2], arr[3],
										arr[5]);
							// stockid=getStockid(CompanyName,symbol,series,ExchangeName);
							if (!(stockid.equals(""))) {
								Object obj = getStockObject(stockid);
								if (!(obj == null)) {
									Logging.debug("INside table.put" + stockid);
									table.put(stockid, (StockDetails) obj);
								}
							}
						}
						if (file_type_name != null
								&& file_type_name.equals("CapInd"))// populate
																	// Hashtable
																	// for
																	// captured
																	// indices.
						{
							table.put(arr[0], cap_index);
							Logging.debug("in table put......." + table.size());
						}

						buffer_xml.append("</tr>");
					}
					// file.close();
				}

			}

		} catch (IOException e) {
			Logging.error("Error : " + e.getMessage());
			e.getStackTrace();
			return null;
		} catch (SAXParseException err) {
			Logging.debug("** Parsing error" + ", line " + err.getLineNumber()
					+ ", uri " + err.getSystemId());
			Logging.debug(" " + err.getMessage());
		} catch (SAXException se) {
			Exception x = se.getException();
			((x == null) ? se : x).printStackTrace();
			Logging.debug(se);
		} catch (Exception ee) {
			Logging.debug("Error : " + ee.getMessage());
		}
		return buffer_xml;
	}

	/**
	 * method to read and display the file content of files for Stock Prices
	 * with extention .txt and to fill the hashtable with file values
	 * 
	 */
	// method added as per Enhancement in Stockpile for next Version by Ashishi
	// 10-07-2006
	public StringBuffer display_txt(HttpServletRequest request) {
		StringBuffer buffer_txt = new StringBuffer();
		try {

			FileReader file = new FileReader(str_fileName);
			BufferedReader br = new BufferedReader(file);
			// CapturedIndexForm cap_index = new CapturedIndexForm();
			String str = "";
			int i;
			String[] arr = null;
			if (file_type == null) {
				FileFormatOK = false;
				buffer_txt
						.append("<font size='2' face='Arial' color='Red'><tr>File with improper format</tr></font>");
				return buffer_txt;
			}
			if (file_type.equals("")) {
				if (FileFormatOK == true) {
					buffer_txt
							.append("<font size='2' face='Arial' color='Red'><tr>File with improper format </tr></font>");
					FileFormatOK = false;
					return buffer_txt;
				}
			}
			if (!(file_type.equals("12") || file_type.equals("13"))) {

				buffer_txt
						.append("<font size='2' face='Arial' color='Red'><tr>File with improper format </tr></font>");
				FileFormatOK = false;
				return buffer_txt;

			}
			if (file_type.equals("2")) {
				buffer_txt.append("<tr>");
				buffer_txt.append("<td>TICKER</td><td>PRICE</td>");
				buffer_txt.append("</tr>");
				while ((str = br.readLine()) != null) {
					arr = str.split("\t");
					Logging.debug("`````````Arr is" + arr.length);
					i = 0;
					buffer_txt.append("<tr>");
					// display the ticker and price
					while (i < arr.length - 1) {
						buffer_txt.append("<td>");
						buffer_txt
								.append("<font size='2' face='Arial' color='Blue'>"
										+ arr[i++] + "</font>");
						buffer_txt.append("</td>");
					}
					buffer_txt.append("</tr>");

				}// end while
					// return buffer_txt;
			} else {
				if ((str = br.readLine()) != null) {
					arr = str.split("\t");
					i = 0;
					file_type_name = null;
					Logging.debug("Before switch" + arr.length);
					switch (arr.length)// work out using table file import and
										// related
					{
					case 2:
						if (file_type.equals("11"))
							file_type_name = "UpdateSeries";
						break;
					case 5:
						if (file_type.equals("12"))
							file_type_name = "PEDaily";
						break;
					case 6:
						if (file_type.equals("9"))
							file_type_name = "FDetailsNSE";
						if (file_type.equals("3"))
							file_type_name = "ICompose";
						Logging.debug("case is " + file_type_name);
						break;
					case 7:
						if (file_type.equals("1"))
							file_type_name = "NStock";// New stocks
						Logging.debug("If switch");
						break;
					case 8:
						if (file_type.equals("5"))
							file_type_name = "CADiv";
						break;
					case 10:
						if (file_type.equals("8")) {
							file_type_name = "CrXrate";
							Logging.debug("Inside switch " + file_type_name);
							break;
						}
						if (file_type.equals("4")) // Captured Indices
						{
							file_type_name = "CapInd";
							Logging.debug("Cap index...............");
							break;
						}
					case 12:
						if (file_type.equals("13")) {
							file_type_name = "EPSFile";// NSEI EPS file.
						}
						Logging.debug("Inside switch with " + arr.length
								+ " EPSFile");
						break;
					case 37:
						if (file_type.equals("6"))
							file_type_name = "FDetails";
						break;
					case 16:
						if (file_type.equals("10")) {
							file_type_name = "FPriceNSEIndex";// NSE Price file
																// with Index
																// detail
						}
						Logging.debug("Inside switch with " + arr.length
								+ " FPriceNSEIndex");
						if (file_type.equals("1")) {
							file_type_name = "NyseStock";// NYSE Add New Stock
						}
						Logging.debug("Inside switch with " + arr.length
								+ " NyseStock");
						break;
					case 25:
						if (file_type.equals("5")) {
							file_type_name = "NyseCADiv";// NYSE Add New Stock
						}
						Logging.debug("Inside switch with " + arr.length
								+ " NyseCADiv");
						break;
					default:
						FileFormatOK = false;
						Logging.debug("Inside switch with " + arr.length
								+ " No cases matched");
						break;
					}
					if (FileFormatOK == false) {
						buffer_txt
								.append("<font size='2' face='Arial' color='Red'><tr>File with improper format </tr></font>");
						FileFormatOK = true;
						return buffer_txt;
					}
					buffer_txt.append("<tr>");
					while (i < arr.length) {
						// Logging.getDebug("Inside while of First Readline");
						buffer_txt.append("<td>");
						buffer_txt
								.append("<font size='2' face='Arial' color='Blue'>"
										+ arr[i++] + "</font>");
						buffer_txt.append("</td>");
					}
					buffer_txt.append("</tr>");
				}
				Logging.debug("Calling UpdateSeries");
				if (file_type.equals("12") && file_type_name != null
						&& file_type_name.equals("PEDaily")) {
					Logging.debug("Calling PEDailyReadFile");
					return PEDailyReadFile.getHashnBuffer_Text(buffer_txt, br);
				}

				if (file_type.equals("13") && file_type_name != null
						&& file_type_name.equals("EPSFile")) {
					Logging.debug("Calling EpsReadFile");
					return EpsReadFile.getHashnBuffer_Text(buffer_txt, br);
				}
			}
		} catch (IOException io) {
			Logging.debug("IOException" + io.getMessage());
		} catch (Exception e) {
			Logging.debug("Error in display" + e.getMessage());
		}
		return buffer_txt;
	}

	public Vector getVector_importfilelist() {
		return vector_importfilelist;
	}

	public void setVector_importfilelist() {
		System.out.println("setVector_importfilelist **");
		vector_importfilelist = new Vector();
		int i = 1;
		vector_importfilelist.add(new Integer(i));
		vector_importfilelist.add("New Stocks");
		i++;
		vector_importfilelist.add(new Integer(i));
		vector_importfilelist.add("Stock Prices");
		i++;
		vector_importfilelist.add(new Integer(i));
		vector_importfilelist.add("Index Composition");
		i++;
		vector_importfilelist.add(new Integer(i));
		vector_importfilelist.add("Captured Index Values");
		i++;
		vector_importfilelist.add(new Integer(i));
		vector_importfilelist.add("Corporate Actions");
		i++;
		vector_importfilelist.add(new Integer(i));
		vector_importfilelist.add("Financial Details(Non Banking)");
		i++;
		vector_importfilelist.add(new Integer(i));
		vector_importfilelist.add("Financial Details(Banking)");
		i++;
		vector_importfilelist.add(new Integer(i));
		vector_importfilelist.add("Currency Exchange Rate");
		i++;
		vector_importfilelist.add(new Integer(i));
		vector_importfilelist.add("Financial Details(NSE:Non Banking)");
		i++;
		vector_importfilelist.add(new Integer(i));
		vector_importfilelist.add("Price File(NSE:Index Detail)");
		i++;
		vector_importfilelist.add(new Integer(i));
		vector_importfilelist.add("Update Series");
		i++;
		vector_importfilelist.add(new Integer(i));
		vector_importfilelist.add("PEDaily File");
		i++;
		vector_importfilelist.add(new Integer(i));
		vector_importfilelist.add("EPS File");
		i++;
		vector_importfilelist.add(new Integer(i));
		vector_importfilelist.add("F&O File");
		i++;
		vector_importfilelist.add(new Integer(i));
		vector_importfilelist.add("Market Information");
		i++;
		vector_importfilelist.add(new Integer(i));
		vector_importfilelist
				.add("New Security Information - security.dat (Master File)");
		i++;
		vector_importfilelist.add(new Integer(i));
		vector_importfilelist.add("New Security Information ");
		i++;
		vector_importfilelist.add(new Integer(i));
		vector_importfilelist.add("Message File ");
		i++;
		vector_importfilelist.add(new Integer(i));
		vector_importfilelist.add("Fixed Income");
		i++;
		System.out.println("vector_importfilelist Size **"
				+ vector_importfilelist.size());
		Logging.debug("Inside setvector importfile");
	}

	public void getStockExchangeDetails(int stock_ex_id) {
		Logging.debug("inside getstockexchange");
		System.out.println("inside getStockExchangeDetails");
		Connect connect = ConnectInit.getConnect();
		Connection connection = null;
		try {
			if (connection == null) {
				connection = connect.getdbConnection();
			}

			String query = "";
			try {
				Logging.debug("connection " + connection);
				String queryexchdetail = ConnectInit.queries
						.getProperty("get_stock_exchange_wise_identifier_code");
				Logging.debug("query is " + queryexchdetail);
				PreparedStatement pst = connection
						.prepareStatement(queryexchdetail);
				pst.setString(1, new Integer(stock_ex_id).toString());
				Logging.debug(pst + "");
				ResultSet result_exdetail = pst.executeQuery();
				Logging.debug(pst + " " + stock_exid);
				// boolean rst=result.next();
				// Logging.getDebug(""+result);
				while (result_exdetail.next()) {
					Logging.debug("Inside result"
							+ result_exdetail.getString(1));
					System.out
							.println("getStockExchangeDetails setS_stockExchange *** "
									+ result_exdetail.getString(1));
					System.out
							.println("getStockExchangeDetails identifier_code_id *** "
									+ identifier_code_id);
					System.out
							.println("getStockExchangeDetails currency_id *** "
									+ currency_id);
					System.out
							.println("getStockExchangeDetails country_id *** "
									+ country_id);
					smform1.setS_stockExchange(result_exdetail.getString(1));
					identifier_code_id = result_exdetail.getString(2);
					currency_id = result_exdetail.getString(3);
					country_id = result_exdetail.getString(4);
					// smform1.setidentifier_code_id(result.getString(2));
					smform1.setB_indentifier(result_exdetail.getString(2));
					smform1.setS_stockCurrency(result_exdetail.getString(3));
					smform1.setS_countryName(result_exdetail.getString(4));
					Logging.debug("Exchange id is " + stock_ex_id
							+ "Inside result1" + identifier_code_id);
				}
			} catch (Exception e) {
				Logging.error("Error : " + e.getMessage());
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

	}

	public StringBuffer storeStock() throws Exception {
		System.out.println("Store Stock Method ***" + tempFileNameForDate);
		Logging.debug("hellostorestock");
		Logging.debug("file_type_name is " + file_type_name + "  file_type is "
				+ file_type);
		StringBuffer buffer = new StringBuffer();
		StringBuffer buffernew = new StringBuffer();

		// StringBuffer buffer1=new StringBuffer();
		int unimpcounter = 0;
		int updcounter = 0;
		int inscounter = 0;
		int counter1 = 0;
		// condition added by samiksha
		if (hist_Date == null || hist_Date.equalsIgnoreCase("null")) {

			String histDateTemp[] = tempFileNameForDate.split("\\.");
			hist_Date = histDateTemp[0];
			String part1 = hist_Date.substring(0, 2);
			int length = hist_Date.length();
			String part2 = hist_Date.substring(length - 4, length);
			System.out.println("part1 *** " + part1 + " && " + part1.length());
			System.out.println("part2 *** " + part2 + " && " + part2.length());
			System.out.println("part3 *** "
					+ hist_Date.substring(part1.length(),
							length - part2.length()));
			hist_Date = hist_Date.substring(part1.length(),
					length - part2.length());

			String month = hist_Date.substring(2, 5);
			if (month.equalsIgnoreCase("JAN")) {
				month = "01";
			} else if (month.equalsIgnoreCase("FEB")) {
				month = "02";
			} else if (month.equalsIgnoreCase("MAR")) {
				month = "03";
			} else if (month.equalsIgnoreCase("APR")) {
				month = "04";
			} else if (month.equalsIgnoreCase("MAY")) {
				month = "05";
			} else if (month.equalsIgnoreCase("JUN")) {
				month = "06";
			} else if (month.equalsIgnoreCase("JUL")) {
				month = "07";
			} else if (month.equalsIgnoreCase("AUG")) {
				month = "08";
			} else if (month.equalsIgnoreCase("SEP")) {
				month = "09";
			} else if (month.equalsIgnoreCase("OCT")) {
				month = "10";
			} else if (month.equalsIgnoreCase("NOV")) {
				month = "11";
			} else if (month.equalsIgnoreCase("DEC")) {
				month = "11";
			} else {
				month = "";
			}
			hist_Date = hist_Date.substring(0, 2) + "-" + month + "-"
					+ hist_Date.substring(5);
		}
		if ((file_type != null && file_type.equals("19"))) {
			buffer = pfd.storeRecordfi(stock_exid, hist_Date, correctedFile);
			stock_exid = "";
			FileFormatOK = true;
			ExchangeOK = true;
			file_type = null;
			str_fileName = null;
			file_type_name = null;
			return buffer;
		}
		if ((file_type != null && file_type.equals("2"))
				|| (file_type_name != null && file_type_name
						.equalsIgnoreCase("Bhavcopy"))) {
			// Updatef uf=new Updatef();
			// buffer1=uf.Storefo(stock_exid);

			buffer = pfd.storeRecord(stock_exid, hist_Date, correctedFile);
			stock_exid = "";
			FileFormatOK = true;
			ExchangeOK = true;
			file_type = null;
			str_fileName = null;
			file_type_name = null;
			return buffer;
		}
		// added by samiksha
		// if (file_type_name != null && file_type_name.equals("NStock")) {
		// storeNStock();
		//
		// }
		if (file_type_name != null && file_type_name.equals("CADiv")) {
			buffer = storeinCADiv();
			return buffer;
		}
		if (file_type_name != null && file_type_name.equals("NyseCADiv"))// Nyse
																			// Corporate
																			// Action
		{
			buffer = NysestoreinCADiv();
			return buffer;
		}
		if ((file_type.equals("9"))
				|| (file_type_name != null && file_type.trim().equals(
						"FDetailsNSE")))// FDetailsNSe
		{
			Logging.debug("Before Calling FinancialDetailReadFile.StoreFDetail(stock_exid);"
					+ stock_exid);
			buffer = null;
			if (stock_exid.equals("84")) {
				buffer = FinancialDetailReadFile.StoreFDetail(stock_exid);
				if (buffer == null) {
					buffer.append("<tr><td>");
					buffer.append("");
					buffer.append("</td><td>");
					buffer.append("");
					buffer.append("</td><td><font color='blue'>File Can Not Be Imported</font></td></tr>");
				}
			} else {
				buffer.append("<font size='2' face='Arial' color='Red'>Invalid Exchange Select National Stock Exchange</font>");
			}
			stock_exid = "";
			FileFormatOK = true;
			ExchangeOK = true;
			file_type = null;
			str_fileName = null;
			file_type_name = null;
			return buffer;
		}// StoreCrXRate
		if ((file_type.equals("6"))
				|| (file_type_name != null && file_type_name.equals("FDetails"))) {
			Logging.debug("Before Calling FinancialDetailReadFile.StoreFDetail(stock_exid);"
					+ stock_exid);
			buffer = null;
			buffer = FinancialDetailReadFile.StoreFDetail(stock_exid);
			if (buffer == null) {
				buffer.append("<tr><td>");
				buffer.append("");
				buffer.append("</td><td>");
				buffer.append("");
				buffer.append("</td><td><font color='blue'>File Can Not Be Imported</font></td></tr>");
			}
			stock_exid = "";
			FileFormatOK = true;
			ExchangeOK = true;
			file_type = null;
			str_fileName = null;
			file_type_name = null;
			return buffer;
		}
		// to save record of eps file in financial_detail table.
		if ((file_type.equals("13"))
				|| (file_type_name != null && file_type_name.equals("EPSFile"))) {
			Logging.debug("Before Calling PEDailyReadFile.StoreEPSDetail(stock_exid);"
					+ stock_exid);
			buffer = null;
			buffer = EpsReadFile.StoreEPSDetail(stock_exid);
			if (buffer == null) {
				buffer.append("<tr><td>");
				buffer.append("");
				buffer.append("</td><td>");
				buffer.append("");
				buffer.append("</td><td><font color='blue'>File Can Not Be Imported</font></td></tr>");
			}
			stock_exid = "";
			FileFormatOK = true;
			ExchangeOK = true;
			file_type = null;
			str_fileName = null;
			file_type_name = null;
			return buffer;
		}
		// to save the recors in pedaily file into financial_details table.
		if ((file_type.equals("12"))
				|| (file_type_name != null && file_type_name.equals("PEDaily"))) {
			Logging.debug("Before Calling PEDailyReadFile.StorePEDetail(stock_exid);"
					+ stock_exid);
			buffer = null;
			buffer = PEDailyReadFile.StorePEDetail(stock_exid);
			if (buffer == null) {
				buffer.append("<tr><td>");
				buffer.append("");
				buffer.append("</td><td>");
				buffer.append("");
				buffer.append("</td><td><font color='blue'>File Can Not Be Imported</font></td></tr>");
			}
			stock_exid = "";
			FileFormatOK = true;
			ExchangeOK = true;
			file_type = null;
			str_fileName = null;
			file_type_name = null;
			return buffer;
		}
		if ((file_type.equals("11"))
				|| (file_type_name != null && file_type_name
						.equals("UpdateSeries"))) {
			Logging.debug("Before Calling UpdateSeriesReadFile.StoreSeriesUpdated(stock_exid);"
					+ stock_exid);
			buffer = null;
			buffer = UpdateSeriesReadFile.StoreSeriesUpdated(stock_exid);
			if (buffer == null) {
				buffer.append("<tr><td>");
				buffer.append("");
				buffer.append("</td><td>");
				buffer.append("");
				buffer.append("</td><td><font color='blue'>File Can Not Be Imported</font></td></tr>");
			}
			stock_exid = "";
			FileFormatOK = true;
			ExchangeOK = true;
			file_type = null;
			str_fileName = null;
			file_type_name = null;
			return buffer;
		}
		if ((file_type.equals("14"))
				|| (file_type_name != null && file_type_name.equals("F&O File"))) {
			StringBuffer buffer1 = new StringBuffer(300000);
			Logging.debug("Before Calling Updatef.StoreSeriesUpdated(stock_exid);"
					+ stock_exid);
			// buffer=null;
			buffer1 = uf.Storefo(stock_exid);
			/*
			 * if(buffer==null){ buffer.append("<tr><td>"); buffer.append("");
			 * buffer.append("</td><td>"); buffer.append(""); buffer.append(
			 * "</td><td><font color='blue'>File Can Not Be Imported</font></td></tr>"
			 * ); }
			 */
			stock_exid = "";
			FileFormatOK = true;
			ExchangeOK = true;
			file_type = null;
			str_fileName = null;
			file_type_name = null;
			return buffer1;
		}
		if (file_type.equals("10") && file_type_name != null
				&& file_type_name.equals("FPriceNSEIndex")) {
			Logging.debug("Calling price file index detail");
			buffer = null;
			buffer = NseIndexDetailReadFile.StoreNseIndexDetailPrices(
					stock_exid, hist_Date, correctedFile);
			if (buffer == null) {
				buffer.append("<tr><td>");
				buffer.append("");
				buffer.append("</td><td>");
				buffer.append("");
				buffer.append("</td><td><font color='blue'>File Can Not Be Imported</font></td></tr>");
			}
			stock_exid = "";
			FileFormatOK = true;
			ExchangeOK = true;
			file_type = null;
			str_fileName = null;
			file_type_name = null;
			return buffer;

		}
		if (file_type != null && !(file_type.equals(""))
				&& file_type.equals("16")) {
			Logging.debug("Calling price file index detail");
			buffer = null;
			buffer = secf.storeSecurityDetails();
			if (buffer == null) {
				buffer.append("<tr><td>");
				buffer.append("");
				buffer.append("</td><td>");
				buffer.append("");
				buffer.append("</td><td><font color='blue'>File Can Not Be Imported</font></td></tr>");
			}
			stock_exid = "";
			FileFormatOK = true;
			ExchangeOK = true;
			file_type = null;
			str_fileName = null;
			file_type_name = null;
			return buffer;

		}
		if (file_type != null && !(file_type.equals(""))
				&& file_type.equals("15")) {
			Logging.debug("Calling price file index detail");
			buffer = null;
			buffer = mar.storeMarketInfirmation(stock_exid, hist_Date);
			if (buffer == null) {
				buffer.append("<tr><td>");
				buffer.append("");
				buffer.append("</td><td>");
				buffer.append("");
				buffer.append("</td><td><font color='blue'>File Can Not Be Imported</font></td></tr>");
			}
			stock_exid = "";
			FileFormatOK = true;
			ExchangeOK = true;
			file_type = null;
			str_fileName = null;
			file_type_name = null;
			return buffer;

		}
		if (file_type != null && !(file_type.equals(""))
				&& file_type.equals("17")) {
			Logging.debug("Calling price file index detail");
			buffer = null;
			buffer = secf.storeSecurityDetails();
			if (buffer == null) {
				buffer.append("<tr><td>");
				buffer.append("");
				buffer.append("</td><td>");
				buffer.append("");
				buffer.append("</td><td><font color='blue'>File Can Not Be Imported</font></td></tr>");
			}
			stock_exid = "";
			FileFormatOK = true;
			ExchangeOK = true;
			file_type = null;
			str_fileName = null;
			file_type_name = null;
			return buffer;

		}

		if (file_type != null && !(file_type.equals(""))
				&& file_type.equals("18")) {
			Logging.debug("Calling price file index detail");
			buffer = null;
			buffer = msg.storeFtpMessageFileDetails();
			if (buffer == null) {
				buffer.append("<tr><td>");
				buffer.append("");
				buffer.append("</td><td>");
				buffer.append("");
				buffer.append("</td><td><font color='blue'>File Can Not Be Imported</font></td></tr>");
			}
			stock_exid = "";
			FileFormatOK = true;
			ExchangeOK = true;
			file_type = null;
			str_fileName = null;
			file_type_name = null;
			return buffer;

		}
		Logging.debug("Before Calling Captured indices import file save");
		if (file_type.equals("4") && file_type_name != null
				&& file_type_name.equals("CapInd")) {
			if (this.fromDownload != null && this.fromDownload.equals("yes")) {
				buffer = ifr.storeCapIndices();
			} else {
				Logging.debug("calling function capIndices");
				buffer = storeCapIndices();
				Logging.debug("After calling storeCapIndices() ");
			}
			stock_exid = "";
			FileFormatOK = true;
			ExchangeOK = true;
			str_fileName = null;
			file_type = null;
			file_type_name = null;
			return buffer;
		}
		if (file_type_name != null && file_type_name.equals("NyseStock")) {
			Logging.debug("calling function strorenyse stock");
			buffer = storeNyseStock();
			Logging.debug("After calling storenyse stock() ");
			stock_exid = "";
			FileFormatOK = true;
			ExchangeOK = true;
			str_fileName = null;
			file_type = null;
			file_type_name = null;
			return buffer;
		}
		if (file_type_name != null && file_type_name.equals("CrXrate")) {
			ExchangeOK = true;
			Logging.debug("Before Calling Currency Exchange ReadFile");
			buffer = null;
			buffer = CrExchangeRateFile.StoreCrXRate();
			if (buffer == null)
				Logging.debug("Buffer is null");
			else
				Logging.debug("Buffer is not null");
			stock_exid = "";
			FileFormatOK = true;
			ExchangeOK = true;
			str_fileName = null;
			file_type = null;
			file_type_name = null;
			return buffer;
		}
		if (currency_id == "") {
			buffer.append("<font size='2' face='Arial' color='Red'>Invalid Exchange ,Currency not found</font>");
			table.clear();
			// return buffer;
		}
		// Logging.getDebug("Stock Exchange id is"+stock_exid);
		// Logging.getDebug("storeStock1");
		String alertPercent = "";
		String rejectionPercent = "";
		if (ExchangeOK == false) {
			buffer.append("<font size='2' face='Arial' color='Red'>Invalid Exchange , can not create stock</font>");
			table.clear();
			str_fileName = null;
			return buffer;
		}
		if (FileFormatOK == false) {
			buffer.append("<font size='2' face='Arial' color='Red'>Invalid File Selected , can not create stock</font>");
			table.clear();
			str_fileName = null;
			return buffer;
		}

		// Logging.getDebug("storeStock2");
		String str = "";
		int i;
		// Logging.getDebug("storeStock3 Before con");
		Connect connect = ConnectInit.getConnect();
		Connection connection = null;
		// Comented by Ashish for static connection problem
		/*
		 * if(Connect.con == null){ //Logging.getDebug("Failed con"); con =
		 * connect.getConnection(); //Logging.getDebug("Reconnect con"); }
		 */
		try {
			if (connection != null)
				connection.close();
			connection = connect.getConnectionForTransaction();
			Logging.debug("connection is before rollback() " + connect);
			connection.rollback();
			Logging.debug("connection is after rollback()" + Connect.con);
			// Logging.getDebug("storeStock3 After con "+Connect.con);
			PreparedStatement pst;
			PreparedStatement pstX;// for exchange code
			Statement stm;
			ResultSet result;
			ResultSet resultX;// for exchange code
			ResultSet rsquery;
			// Logging.getDebug("Before get alert query");
			pst = connection.prepareStatement(ConnectInit.queries
					.getProperty("get_alert_reject_per_from_sysconfig"));
			result = pst.executeQuery();
			// Logging.getDebug("After get after query "+pst);
			if (result.next()) {
				alertPercent = result.getString(1);
				rejectionPercent = result.getString(2);
				// Logging.getDebug("Inside If Alert is " +alertPercent
				// +" Reject is " +rejectionPercent);
			} else {
				alertPercent = "10";
				rejectionPercent = "20";
				// Logging.getDebug("Inside else Alert is " +alertPercent
				// +" Reject is " +rejectionPercent);
			}
			Hashtable identifiercode_list = new Hashtable();
			// pstX = Connect.con.prepareStatement(ConnectInit.queries
			// .getProperty("importFile.get_symbol_exchangewise"));
			pstX = connection.prepareStatement(ConnectInit.queries
					.getProperty("importFile.get_symbol_exchangewise"));
			// Logging.getDebug("pstX is 1" +pstX );
			Logging.debug(" " + stock_exid + " ");
			pstX.setInt(1, Integer.parseInt(stock_exid));
			resultX = pstX.executeQuery();
			while (resultX.next()) {
				identifiercode_list.put((String) resultX.getString(1),
						(String) resultX.getString(2));
			}
			Enumeration e = table.keys();
			String key = "";
			// Logging.getDebug("After Enum Declaration");
			int counter = 0;
			int li = 0;

			Runtime runtime = Runtime.getRuntime();
			for (e = table.keys(); e.hasMoreElements();) {
				counter1++;
				if (counter % 10 == 0) {
					connection.commit();
					connection.setAutoCommit(true);
					Logging.debug("counter after commit 3 IS" + counter);
					counter = 0;
					if (connection != null)
						connection.close();
					connection = null;
					connection = connect.getConnectionForTransaction();
				}
				key = (String) e.nextElement();
				// Logging.getDebug("Inside for loop "+key);
				smform1 = (StockMasterForm) table.get(key);
				System.out.println("smform1 pojo exc id***"
						+ smform1.getExc_id());
				System.out.println("smform1 pojo crisil***"
						+ smform1.getB_crisil());
				System.out.println("smform1 pojo ISN***" + smform1.getB_isn());
				System.out.println("smform1 pojo IWF***" + smform1.getD_iwf());
				System.out.println("smform1 pojo listingDate***"
						+ smform1.getD_listingDate());
				System.out.println("smform1 pojo paidValue***"
						+ smform1.getD_paidValue());
				System.out.println("smform1 pojo faceValue***"
						+ smform1.getF_faceValue());
				System.out.println("smform1 pojo issuedShares***"
						+ smform1.getF_issuedShares());
				System.out.println("smform1 pojo rejectionPercent***"
						+ smform1.getF_rejectionPercent());
				System.out.println("smform1 pojo companyName***"
						+ smform1.getS_companyName());
				System.out.println("smform1 pojo marketLot***"
						+ smform1.getS_marketLot());
				System.out.println("smform1 pojo stockCurrency***"
						+ smform1.getS_stockCurrency());
				System.out.println("smform1 pojo stockName***"
						+ smform1.getS_stockName());

				try {
					key = key.replaceAll("'", "\'");
					// Logging.getDebug("Inside try key.replace  "+key);
					System.out.println("Key to db *** " + key);
					pst = connection.prepareStatement(ConnectInit.queries
							.getProperty("get_company_id"));
					// Logging.getDebug("pst1 "+pst + " key is " + key);
					pst.setString(1, key);
					// Logging.getDebug("pst2 "+pst);
					result = pst.executeQuery();

					// Logging.getDebug("pst3 "+pst);
					// Logging.getDebug("INside Store Stock after result");
					if (result.next()) {
						counter++;
						smform1.setS_countryName(country_id);
						/*
						 * //Logging.getDebug("Inside Result "); pstX =
						 * Connect.con
						 * .prepareStatement(connect.queries.getProperty
						 * ("get_stock_id_where_exchange_code"));
						 * //Logging.getDebug("pstX is 1" +pstX );
						 * Logging.getDebug(" " + stock_exid + " " + key +" " +
						 * smform1.getB_exc_code() + " " + smform1.getSeries());
						 * pstX.setInt(1,Integer.parseInt(stock_exid));
						 * pstX.setString(2,key);
						 * pstX.setString(3,smform1.getB_exc_code());
						 * pstX.setString(4,smform1.getSeries());
						 * //Logging.getDebug("pstX is 2" +pstX); resultX =
						 * pstX.executeQuery(); Logging.getDebug("pstX is 3"
						 * +pstX);
						 */

						// Logging.getDebug("country is "+ country_id);
						// Logging.getDebug("country is "+
						// smform1.getS_countryName());
						// Logging.getDebug("stock type is"+smform1.getS_stockType());
						boolean stockexist = false;
						if (identifiercode_list.containsKey(smform1
								.getB_exc_code().trim())) {
							// stockexist=true;
							if (stock_exid.equals("84")) {
								pstX = connection
										.prepareStatement(ConnectInit.queries
												.getProperty("get_stock_id_where_exchange_code"));
								// Logging.getDebug("pstX is 1" +pstX );
								Logging.debug(" " + stock_exid + " " + key
										+ " " + smform1.getB_exc_code() + " "
										+ smform1.getSeries());
								pstX.setInt(1, Integer.parseInt(stock_exid));
								pstX.setString(2, key);
								pstX.setString(3, smform1.getB_exc_code());
								pstX.setString(4, smform1.getSeries());
								// Logging.getDebug("pstX is 2" +pstX);
								resultX = pstX.executeQuery();
								if (resultX.next()) {
									stockexist = true;
								} else {
									stockexist = false;
								}
							}
						}
						if (stockexist == true) {
							updcounter++;
							buffer.append("<tr><td>");
							buffer.append(key);
							buffer.append("</td><td><font color='red'>Stock Already Exist</font></td></tr>");
							continue;
						} else {
							// Logging.getDebug("Stock Not Exist");
							Logging.debug("pstX is 3" + pstX);
						}
						smform1.setS_stockCurrency(currency_id);

						smform1.setS_companyName(result.getString(1));
						smform1.setS_stockExchange(stock_exid);
						smform1.setS_indentifier(identifier_code_id);
						smform1.setS_stockCurrency(currency_id);
						smform1.setD_iwf("1");
						smform1.setB_isActive("y");
						smform1.setB_isPriceForLot("n");
						smform1.setD_listingDate(formatDate(null));
						smform1.setF_issuedShares("1");
						smform1.setS_stockName(key);
						smform1.setF_alertPercent(alertPercent);
						smform1.setF_rejectionPercent(rejectionPercent);

						String query = ConnectInit.queries
								.getProperty("insert_into_stock_master");
						String query1 = ConnectInit.queries
								.getProperty("insert_into_stock_master_history");
						QueryClass1.insertIntoStockMaster(query, query1,
								smform1);
						// smform1.reset();
					} else {
						String query = ConnectInit.queries
								.getProperty("insert_into_stock_master_unimported");
						QueryClass1.insertUnimportedStock(key, stock_exid,
								query, smform1);
						unimpcounter++;
						buffer.append("<tr><td>");
						buffer.append(key);
						buffer.append("</td><td><font color='blue'>Company Not Found</font></td></tr>");
						continue;
					}
				} catch (Exception ex) {
				}
				li++;
				inscounter++;
				if (li > 100) {
					// Logging.getError("memmory available before: " +
					// runtime.freeMemory());
					System.gc();
					if (li > 350)
						Logging.error("memmory available After: "
								+ runtime.freeMemory());
					li = 0;
				}
			}
			/*
			 * buffer.append("<tr><td>"); buffer.append("");
			 * buffer.append("</td><td>All Other Stock Entered.</td></tr>");
			 */

			table.clear();

			buffernew
					.append("<br><tr><font color=Blue><td>Values Inserted :</td><td>");
			buffernew.append(inscounter);
			buffernew.append("</td></font></tr>");
			buffernew
					.append("<br><tr><font color=Blue><td>Stock Already Exist :</td><td>");
			buffernew.append(updcounter);
			buffernew.append("</td></font></tr>");
			buffernew
					.append("<br><tr><font color=Blue><td>Company Not Found:</td><td>");
			buffernew.append(unimpcounter);
			buffernew
					.append("<br><tr><font color=Blue><td>Total Rows :</td><td>");
			buffernew.append(counter1);
			buffernew.append("</td></font></tr>");
			buffernew.append(buffer);
			str_fileName = null;
			stock_exid = "";
			FileFormatOK = true;
			ExchangeOK = true;
			str_fileName = null;
			file_type = null;
			file_type_name = null;
		} catch (SQLException ex) {
			Logging.error("Error : Unable to get Transaction connection "
					+ ex.getMessage());
		}

		finally {
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

	private void storeNStock() {

		Enumeration e = table.keys();
		System.out.println("table keys *** " + table);
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			Logging.debug("symbol is " + key);
			StockMasterForm newStock = (StockMasterForm) table.get(key);
		}
		StockMasterForm newStock = new StockMasterForm();
		// newStock.setS_stockCurrency();
		// newStock.setS_companyName();
		// newStock.setS_stockExchange();
		// newStock.setS_indentifier();
		// newStock.setS_stockCurrency();
		// newStock.setD_iwf("1");
		// newStock.setB_isActive("y");
		// newStock.setB_isPriceForLot("n");
		// newStock.setD_listingDate();
		// newStock.setF_issuedShares("1");
		// newStock.setS_stockName();
		// newStock.setF_alertPercent();
		// newStock.setF_rejectionPercent();

		String query = ConnectInit.queries
				.getProperty("insert_into_stock_master");
		Connect connect = ConnectInit.getConnect();

	}

	public synchronized StringBuffer storeNyseStock() {
		Logging.debug("hellostoreNysestock");
		StringBuffer buffer = new StringBuffer();
		Connect connect = ConnectInit.getConnect();
		Connection connection = null;
		String alertPercent = "";
		String rejectionPercent = "";
		if (ExchangeOK == false) {
			buffer.append("<font size='2' face='Arial' color='Red'>Invalid Exchange , can not create stock</font>");
			table.clear();
			str_fileName = null;
			return buffer;
		}
		if (FileFormatOK == false) {
			buffer.append("<font size='2' face='Arial' color='Red'>Invalid File Selected , can not create stock</font>");
			table.clear();
			str_fileName = null;
			return buffer;
		}
		try {
			// Logging.getDebug("storeStock2");
			String str = "";
			int i;
			// Logging.getDebug("storeStock3 Before con");

			/*
			 * if(Connect.con == null){ //Logging.getDebug("Failed con"); con =
			 * connect.getConnection(); //Logging.getDebug("Reconnect con"); }
			 */
			try {
				if (connection == null)
					connection = connect.getConnectionForTransaction();
			} catch (Exception e) {
				Logging.error(" Error : " + e.getMessage());
			}
			PreparedStatement pst;
			PreparedStatement pstX;// for exchange code
			Statement stm;
			ResultSet result;
			ResultSet resultX;// for exchange code
			ResultSet rsquery;
			// Logging.getDebug("Before get alert query");
			pst = Connect.con.prepareStatement(ConnectInit.queries
					.getProperty("get_alert_reject_per_from_sysconfig"));
			result = pst.executeQuery();
			// Logging.getDebug("After get after query "+pst);
			if (result.next()) {
				alertPercent = result.getString(1);
				rejectionPercent = result.getString(2);
				// Logging.getDebug("Inside If Alert is " +alertPercent
				// +" Reject is " +rejectionPercent);
			} else {
				alertPercent = "10";
				rejectionPercent = "20";
				// Logging.getDebug("Inside else Alert is " +alertPercent
				// +" Reject is " +rejectionPercent);
			}
			Enumeration e = table.keys();
			String key = "";
			// Logging.getDebug("After Enum Declaration");
			int counter = 0;
			int count = 0, li = 0;
			Runtime runtime = Runtime.getRuntime();
			for (e = table.keys(); e.hasMoreElements();) {
				count++;
				if (counter == 5) {
					int a = result.CLOSE_CURSORS_AT_COMMIT;
					connection.commit();
					connection.setAutoCommit(true);
					Logging.debug("counter after commit 5 IS " + count);
					counter = 0;
				}
				if (count % 100 == 0) {
					connection.commit();
					connection.close();
					// Logging.getDebug("connection after counter 100 is "+connect
					// );
					try {
						try {
							if (connection == null)
								connection = connect
										.getConnectionForTransaction();
						} catch (Exception e2) {
							Logging.error(" Error : " + e2.getMessage());
						}
						Logging.debug("connection after counter 100 is "
								+ connect);
						connection.rollback();
						Logging.debug("connection after counter 100 is "
								+ connection);
					} catch (SQLException ex) {
						connection.close();
						Logging.error("Error : Unable to get Transaction connection "
								+ ex.getMessage());
					}
				}
				key = (String) e.nextElement();
				// Logging.getDebug("Inside for loop "+key);
				smform1 = (StockMasterForm) table.get(key);
				try {
					key = key.replaceAll("'", "\'");
					String key1 = key.toLowerCase();
					// Logging.getDebug("Inside try key.replace  "+key);
					pst = connection.prepareStatement(ConnectInit.queries
							.getProperty("get_company_id"));
					// Logging.getDebug("pst1 "+pst + " key is " + key);
					pst.setString(1, key1);
					// Logging.getDebug("pst2 "+pst);
					result = pst.executeQuery();

					// Logging.getDebug("pst3 "+pst);
					// Logging.getDebug("INside Store Stock after result");
					if (result.next()) {
						// counter++;
						// if(counter>30)
						// return buffer;
						// Logging.getDebug("Inside Result ");
						pstX = connection
								.prepareStatement(ConnectInit.queries
										.getProperty("Nyse_get_stock_id_where_exchange_code"));
						// Logging.getDebug("pstX is 1" +pstX );
						Logging.debug(" " + stock_exid + " " + key + " "
								+ (smform1.getB_exc_code()).trim() + " "
								+ smform1.getSeries());
						pstX.setInt(1, Integer.parseInt(stock_exid));
						pstX.setString(2, key);
						pstX.setString(3, (smform1.getB_exc_code()).trim());
						// Logging.getDebug("pstX is 2" +pstX);
						resultX = pstX.executeQuery();
						// Logging.getDebug("pstX is in nyse" +pstX);
						smform1.setS_countryName(country_id);
						// Logging.getDebug("country is in nyse "+ country_id);
						// Logging.getDebug("country is  in nyse "+
						// smform1.getS_countryName());
						// Logging.getDebug("stock type is"+smform1.getS_stockType());
						if (resultX.next()) {
							buffer.append("<tr><td>");
							buffer.append(key);
							buffer.append("</td><td><font color='red'>Stock Already Exist</font></td></tr>");
							continue;
						}
						smform1.setS_stockCurrency(currency_id);

						smform1.setS_companyName(result.getString(1));
						smform1.setS_stockExchange(stock_exid);
						smform1.setS_indentifier(identifier_code_id);
						smform1.setS_stockCurrency(currency_id);
						smform1.setD_iwf("1");
						smform1.setB_isActive("y");
						smform1.setB_isPriceForLot("n");
						smform1.setD_listingDate(formatDate(null));
						smform1.setF_issuedShares("1");
						smform1.setS_stockName(key);
						smform1.setF_alertPercent(alertPercent);
						smform1.setF_rejectionPercent(rejectionPercent);

						String query = ConnectInit.queries
								.getProperty("insert_into_stock_master");
						String query1 = ConnectInit.queries
								.getProperty("insert_into_stock_master_history");
						NyseNewStock.insertIntoStockMaster(query, query1,
								smform1);
						// smform1.reset();
					} else {
						String query = ConnectInit.queries
								.getProperty("insert_into_stock_master_unimported");
						QueryClass1.insertUnimportedStock(key, stock_exid,
								query, smform1);
						buffer.append("<tr><td>");
						buffer.append(key);
						buffer.append("</td><td><font color='blue'>Company Not Found</font></td></tr>");
						continue;
					}
				} catch (Exception ex) {
				}
				li++;
				if (li > 100) {
					// Logging.getError("memmory available before: " +
					// runtime.freeMemory());
					System.gc();
					if (li > 350) {
						Logging.error("memmory available After: "
								+ runtime.freeMemory());
					}
					li = 0;
				}
			}
			buffer.append("<tr><td>");
			buffer.append("");
			buffer.append("</td><td>All Other Stock Entered</td></tr>");

			table.clear();
			str_fileName = null;
			stock_exid = "";
			FileFormatOK = true;
			ExchangeOK = true;
			str_fileName = null;
			file_type = null;
			file_type_name = null;
		} catch (Exception e) {
			Logging.error("File Not Found" + e.getMessage());
		} finally {
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

	public StringBuffer storeinCADiv() {
		Logging.debug("inside storecad");
		StringBuffer buffer = new StringBuffer();
		int counter1 = 0;
		Connect connect = ConnectInit.getConnect();
		Connection connection = null;
		if (ExchangeOK == false) {
			buffer.append("<font size='2' face='Arial' color='Red'>Invalid Exchange , can not create stock</font>");
			table.clear();
			str_fileName = null;
			return buffer;
		}
		if (FileFormatOK == false) {
			buffer.append("<font size='2' face='Arial' color='Red'>Invalid File Selected , can not create stock</font>");
			table.clear();
			str_fileName = null;
			return buffer;
		}
		try {
			// Logging.getDebug("inside storecad try");

			/*
			 * if(Connect.con == null){ con = connect.getConnection(); }
			 */
			try {
				if (connection == null)
					connection = connect.getConnectionForTransaction();
			} catch (Exception e) {
				Logging.error(" Error : " + e.getMessage());
			}
			Logging.debug("inside storecad after connect");
			PreparedStatement pst;
			PreparedStatement pstX;// for exchange code
			Statement stm;
			ResultSet result = null;
			ResultSet resultX;// for exchange code
			ResultSet rsquery;
			Enumeration e = table.keys();
			String key = "";
			String keyP1 = "";// part one is symbol
			String keyP2 = "";// part one is series
			int pos = 0;// position of ":" inside key
			int counter = 0;
			int cam_id = 0;
			Logging.debug("inside storecad before table.keys");
			for (e = table.keys(); e.hasMoreElements();) {
				counter1++;
				Logging.debug("inside storecad after table.keys");
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
					// Logging.getDebug("connection after counter 100 is "+connect
					// );
					try {
						try {
							if (connection == null)
								connection = connect
										.getConnectionForTransaction();
						} catch (Exception e2) {
							Logging.error(" Error : " + e2.getMessage());
						}
						Logging.debug("connection after counter 100 is "
								+ connect);
						connection.rollback();
						Logging.debug("connection after counter 100 is "
								+ connection);
					} catch (SQLException ex) {
						connection.close();
						Logging.error("Error : Unable to get Transaction connection "
								+ ex.getMessage());
					}
				}
				key = (String) e.nextElement();
				corp = (Corporate) table.get(key);
				try {
					Logging.debug("key is " + key);
					keyP1 = "";
					keyP2 = "";
					pos = 0;
					pos = key.indexOf(":");
					keyP1 = key.substring(0, pos);
					keyP2 = key.substring(pos + 1, key.length());
					buffer.append("<tr><td>");
					buffer.append(keyP1);
					buffer.append("</td>");
					buffer.append("<td>");
					buffer.append(keyP2);
					buffer.append("</td>");
					int stock_id = 0;
					Logging.debug("inside storecad keys " + pos + " " + keyP1
							+ " " + keyP2);
					pst = connection.prepareStatement(ConnectInit.queries
							.getProperty("get_stock_id_where_symbol_series"));
					pst.setString(1, keyP1);
					pst.setString(2, keyP2);
					pst.setString(3, stock_exid);
					// Logging.getDebug("inside storecad pst is1 "+pst);
					result = pst.executeQuery();
					Logging.debug("inside storecad pst is" + pst);
					if (result.next()) {
						boolean nosuchCA = false;
						stock_id = result.getInt(1);
						corp.setStid(result.getString(1));
						Logging.debug("inside storecad after result "
								+ result.getShort(1));
						// counter++;
						// if(counter>2)
						// return buffer;
						// get the corporate action id
						// check if the ca exist for the stock on same date
						cam_id = 0;
						Logging.debug("corp.getDescription() "
								+ corp.getDescription());
						cam_id = get_camid(corp.getDescription());
						Logging.debug("cam_id after call " + cam_id);
						if (cam_id == 0) {
							nosuchCA = true;
							// buffer.append("<tr><td>");
							// buffer.append(keyP1);
							// buffer.append("</td>");
							// buffer.append("<td>");
							// buffer.append(keyP2);
							// buffer.append("</td>");
							// buffer.append("<td>No Such Action Found");//</td></tr>
							Logging.debug("cam_id not found nosuchCA =true;"
									+ cam_id);
							// continue;
						}
						Logging.debug("cam_id is " + cam_id);
						pstX = connection.prepareStatement(ConnectInit.queries
								.getProperty("get_cad_id_from_cad"));
						Logging.debug("Try1");
						pstX.setInt(1, stock_id);
						Logging.debug("Try2");
						pstX.setInt(2, cam_id);
						Logging.debug("Try3");
						String exdate = (corp.getEx_date());
						Logging.debug("Try4");
						pstX.setString(3, exdate);
						Logging.debug("Try5");
						pstX.setString(4, exdate);
						Logging.debug("Try6");
						Logging.debug("pstX is 2" + pstX);
						resultX = pstX.executeQuery();
						Logging.debug("Try7");
						Logging.debug("pstX is 3" + pstX);
						// smform1.setS_countryName(country_id);
						// Logging.getDebug("country is "+ country_id);
						// Logging.getDebug("country is "+
						// smform1.getS_countryName());
						// Logging.getDebug("stock type is"+smform1.getS_stockType());
						corp.setCorpid(new Integer(cam_id).toString());
						Logging.debug("Try8");
						corp.setStatus("n");
						Logging.debug("Try9");
						corp.setShare("0");
						String query1 = "";
						String s = null;
						if (resultX.next()) {
							int c = 0;
							c = result.getInt(1);
							if (c != 0) {
								Logging.debug("Inside CA Already Exist");
								// buffer.append(" <td>");
								// buffer.append(keyP1);
								// buffer.append("</td><td>");
								// buffer.append(keyP2);
								buffer.append(" <td><font color='red'>CA Already Exist</font></td></tr>");
								continue;
							}
						} else {
							Logging.debug("Inside else CA Already Exist");
							Logging.debug("pstX is 3" + pstX);
						}
						if (nosuchCA == true) {
							Logging.debug("Inside nosuchCA");
							pstX = connection
									.prepareStatement(ConnectInit.queries
											.getProperty("get_event_id_from_event"));
							pstX.setInt(1, stock_id);
							pstX.setString(2, corp.getEx_date());
							pstX.setString(3, corp.getDescription());
							resultX = pstX.executeQuery();
							if (resultX.next()) {
								Logging.debug("Already Saved as Event");
								// buffer.append(" <td>");
								// buffer.append(keyP1);
								// buffer.append("</td><td>");
								// buffer.append(keyP2);
								buffer.append("<td>:Already Saved as Event</td></tr>");//
								continue;
							}
							Logging.debug("Inside nosuchCA=true1");
							query1 = ConnectInit.queries
									.getProperty("insert_into_event");
							Logging.debug("Inside nosuchCA=true1" + query1);
							String nextval = ConnectInit.queries
									.getProperty("get_sequence_cad_id");
							UpdateCorp.insert_into_event(connection, query1,
									nextval, corp, null);
							// buffer.append("<td>");
							// buffer.append(keyP1);
							// buffer.append("</td><td>");
							// buffer.append(keyP2);
							buffer.append("<td>:Saved as Event</td></tr>");//
							continue;
						}
						Logging.debug("Before insert into cad");
						query1 = ConnectInit.queries
								.getProperty("insert_into_cad_values");
						String nextval = ConnectInit.queries
								.getProperty("get_sequence_cad_id");
						UpdateCorp.insert_into_cad(connection, query1, nextval,
								corp, null);
						buffer.append("<td>Corporate Action Entered in Diary</td></tr>");
						// smform1.reset();
					} else {

						Logging.debug("no such stock found");
						// buffer.append(" <td>");
						// buffer.append(keyP1);
						// buffer.append("</td>");
						// buffer.append("<td>");
						// buffer.append(keyP2);
						buffer.append("<td><font color='blue'>Stock Not Found</font></td></tr>");
						continue;
					}
				} catch (Exception ex) {
					Logging.error("Error : " + ex.getMessage());
				}
			}

			table.clear();
			str_fileName = null;
			stock_exid = "";
			FileFormatOK = true;
			ExchangeOK = true;
			file_type = null;
			file_type_name = null;
		} catch (Exception e) {
			Logging.error("File Not Found");
		} finally {
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

	/*
	 * private String formatDate() { SimpleDateFormat fr = new
	 * SimpleDateFormat("dd-MM-yyyy"); Date d = new Date(); return
	 * fr.format(d).toString(); }
	 */
	public StringBuffer NysestoreinCADiv() {
		Connection connection = null;
		Logging.debug("inside Nyse storecad");
		StringBuffer buffer = new StringBuffer();
		if (ExchangeOK == false) {
			buffer.append("<font size='2' face='Arial' color='Red'>Invalid Exchange , can not create stock</font>");
			table.clear();
			str_fileName = null;
			return buffer;
		}
		if (FileFormatOK == false) {
			buffer.append("<font size='2' face='Arial' color='Red'>Invalid File Selected , can not create stock</font>");
			table.clear();
			str_fileName = null;
			return buffer;
		}
		try {
			Logging.debug("inside storecad try");
			Connect connect = ConnectInit.getConnect();
			// Change by Manoj Adekar for Dynamic connection using
			// getdbConnection() instead of getConnection()
			if (connection == null) {
				connection = connect.getdbConnection();
			}
			Logging.debug("inside storecad after connect");
			PreparedStatement pst;
			PreparedStatement pstX;// for exchange code
			Statement stm;
			ResultSet result;
			ResultSet resultX;// for exchange code
			ResultSet rsquery;
			Enumeration e = table.keys();
			String key = "";
			int pos = 0;// position of ":" inside key
			int counter = 0;
			int cam_id = 0;
			Logging.debug("inside storecad before table.keys");
			for (e = table.keys(); e.hasMoreElements();) {
				Logging.debug("inside storecad after table.keys");
				key = (String) e.nextElement();
				corp = (Corporate) table.get(key);
				try {
					Logging.debug("key is " + key);
					buffer.append("<tr><td>");
					buffer.append(key);
					buffer.append("</td>");
					int stock_id = 0;
					Logging.debug("inside storecad keys " + key);
					pst = connection.prepareStatement(ConnectInit.queries
							.getProperty("get_stock_id_where_symbol"));
					pst.setString(1, key);
					pst.setString(2, stock_exid);
					// Logging.getDebug("inside storecad pst is1 "+pst);
					result = pst.executeQuery();
					Logging.debug("inside storecad pst is" + pst);
					if (result.next()) {
						boolean nosuchCA = false;
						stock_id = result.getInt(1);
						corp.setStid(result.getString(1));
						Logging.debug("inside storecad after result "
								+ result.getShort(1));
						counter++;
						if (counter > 2)
							return buffer;
						// get the corporate action id
						// check if the ca exist for the stock on same date
						cam_id = 0;
						Logging.debug("corp.getCorp_name() "
								+ corp.getCorp_name());
						String corp_name = corp.getCorp_name();
						if (corp_name.equals("Cash")) {
							corp_name = "Cash dividend";
							cam_id = get_camid(corp_name);
						} else {
							cam_id = get_camid(corp.getCorp_name());
						}
						Logging.debug("cam_id after call " + cam_id);
						if (cam_id == 0) {
							nosuchCA = true;
							Logging.debug("cam_id not found nosuchCA =true;"
									+ cam_id);
							// continue;
						}
						Logging.debug("cam_id is " + cam_id);
						pstX = connection.prepareStatement(ConnectInit.queries
								.getProperty("get_cad_id_from_cad"));
						Logging.debug("Try1");
						pstX.setInt(1, stock_id);
						Logging.debug("Try2");
						pstX.setInt(2, cam_id);
						Logging.debug("Try3");
						String exdate = (corp.getEx_date());
						Logging.debug("Try4");
						pstX.setString(3, exdate);
						Logging.debug("Try5");
						pstX.setString(4, exdate);
						Logging.debug("Try6");
						Logging.debug("pstX is 2" + pstX);
						resultX = pstX.executeQuery();
						Logging.debug("Try7");
						Logging.debug("pstX is 3" + pstX);
						corp.setCorpid(new Integer(cam_id).toString());
						Logging.debug("Try8");
						corp.setStatus("n");
						Logging.debug("Try9");
						corp.setShare("0");
						String query1 = "";
						String s = null;
						if (resultX.next()) {
							int c = 0;
							c = result.getInt(1);
							if (c != 0) {
								Logging.debug("Inside CA Already Exist");
								buffer.append(" <td><font color='red'>CA Already Exist</font></td></tr>");
								continue;
							}
						} else {
							Logging.debug("Inside else CA Already Exist");
							Logging.debug("pstX is 3" + pstX);
						}
						if (nosuchCA == true) {
							Logging.debug("Inside nosuchCA");
							pstX = connection
									.prepareStatement(ConnectInit.queries
											.getProperty("get_event_id_from_event"));
							pstX.setInt(1, stock_id);
							pstX.setString(2, corp.getEx_date());
							pstX.setString(3, corp.getCorp_name());
							resultX = pstX.executeQuery();
							if (resultX.next()) {
								Logging.debug("Already Saved as Event");
								buffer.append("<td>:Already Saved as Event</td></tr>");//
								continue;
							}
							Logging.debug("Inside nosuchCA=true1");
							query1 = ConnectInit.queries
									.getProperty("insert_into_event");
							Logging.debug("Inside nosuchCA=true1" + query1);
							String nextval = ConnectInit.queries
									.getProperty("get_sequence_event_id");
							UpdateCorp.insert_into_event(connection, query1,
									nextval, corp, null);
							buffer.append("<td>:Saved as Event</td></tr>");
							continue;
						}
						Logging.debug("Before insert into cad");
						query1 = ConnectInit.queries
								.getProperty("insert_into_cad_values");
						String nextval = ConnectInit.queries
								.getProperty("get_sequence_event_id");
						UpdateCorp.insert_into_cad(connection, query1, nextval,
								corp, null);
						buffer.append("<td>Corporate Action Entered in Diary</td></tr>");
						// smform1.reset();
					} else {

						Logging.debug("no such stock found");
						buffer.append("<td><font color='blue'>Stock Not Found</font></td></tr>");
						continue;
					}
				} catch (Exception ex) {
					Logging.error("Error : " + ex.getMessage());
				}
			}
		} catch (Exception e) {
			Logging.error("File Not Found");
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		table.clear();
		str_fileName = null;
		stock_exid = "";
		FileFormatOK = true;
		ExchangeOK = true;
		file_type = null;
		file_type_name = null;
		return buffer;
	}

	public static String getTime() {
		// TODO Auto-generated constructor stub
		String str_time = null;
		Date dt = new Date();
		dt.getDate();
		str_time = dt.toString().split(" ")[3];
		Logging.debug("Time is " + str_time);
		return str_time;
	}

	public static String formatDate(String str) {
		SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
		String date = null;
		if (str == null) {
			Date d = new Date();
			date = fr.format(d).toString();
		} else {
			Logging.debug("before date " + str);
			java.util.Date d = new java.util.Date(str.trim());
			str = fr.format(d).toString();
			Logging.debug("After Simpledate" + str);
			return str;
		}
		return date;
	}

	private int get_camid(String camname) {
		camname = camname.toLowerCase();
		Logging.debug("Inside get camid");
		int id = 0;
		Connection connection = null;
		try {
			String qry = null;
			Connect connect = ConnectInit.getConnect();
			if (connection == null) {

				connection = connect.getdbConnection();
				// Logging.getDebug("Reconnect con");
			}
			// Logging.getDebug("storeStock3 After con "+Connect.con);
			PreparedStatement pst;
			ResultSet result;
			pst = connection.prepareStatement(ConnectInit.queries
					.getProperty("select_from_cam"));
			result = pst.executeQuery();
			while (result.next()) {
				if (camname.equals(result.getString(2).toLowerCase())) {
					id = result.getInt(1);
				}
			}
		} catch (Exception e) {
			Logging.error("Errro while finding cam_id " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		return id;
	}

	public static void main(String[] args) {
		PopFileDialogNewStock p = new PopFileDialogNewStock();
		// p.display();
		// p.storeStock();
	}

	public StringBuffer storeCapIndices() {
		StringBuffer buffer = new StringBuffer();
		Logging.debug("inside storeCap..");
		if (FileFormatOK == false) {
			buffer.append("<font size='2' face='Arial' color='Red'>Invalid File Selected , can not create Captured Index</font>");
			table.clear();
			str_fileName = null;
			return buffer;
		}
		String date = QueryClass.formatDate();
		Logging.debug("before CompareDate date is " + date);
		filedate = formatDate(filedate);
		int datediff = CompareDate(date, filedate);
		Logging.debug("after CompareDate datediff is " + datediff);
		Logging.debug("datediff is " + datediff);
		if (datediff < 0) {
			Logging.debug("File Can  Not Be Imported , Date Greater Than Current Date.");
			buffer.append("<tr><td>");
			buffer.append("");
			buffer.append("</td><td><font color='black'>File Can  Not Be Imported , Date Greater Than Current Date.</font></td></tr>");
			return buffer;
		}
		float low_value = 0, high_value = 0;
		float opening_value = 0, closing_value = 0;
		String query = null;
		int upd_case = 0;
		boolean flag = true;
		int counter1 = 0;
		Connect connect = ConnectInit.getConnect();
		Connection connection = null;
		try {
			String str = "";
			int i;

			/*
			 * if(Connect.con == null) { con = connect.getConnection(); }
			 */
			try {
				if (connection == null)
					connection = connect.getConnectionForTransaction();
			} catch (Exception e) {
				Logging.error(" Error : " + e.getMessage());
			}
			PreparedStatement pst, pst_preStat, pseq;
			Statement stm;
			ResultSet result = null, rst_id = null;
			Enumeration e = table.keys();
			String key = "";
			int counter = 0;
			Logging.debug("size of hashtable......" + table.size());
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
					// Logging.getDebug("connection after counter 100 is "+connect
					// );
					try {
						try {
							if (connection == null)
								connection = connect
										.getConnectionForTransaction();
						} catch (Exception e2) {
							Logging.error(" Error : " + e2.getMessage());
						}
						Logging.debug("connection after counter 100 is "
								+ connect);
						connection.rollback();
						Logging.debug("connection after counter 100 is "
								+ connection);
					} catch (SQLException ex) {
						connection.close();
						Logging.error("Error : Unable to get Transaction connection "
								+ ex.getMessage());
					}
				}
				flag = true;
				key = (String) e.nextElement();
				cap_index = (CapturedIndexForm) table.get(key);
				try {
					key = key.replaceAll("'", "\'");
					String ind_name = cap_index.getIndex_name();
					int ind_id = 0, ind_id_chk2 = 0;
					String index_id = "0";
					String ind_date = cap_index.getDate();
					ind_date = formatDate(ind_date);
					Logging.debug("name in try ........" + ind_name);
					String query_get_ind_id = "select index_id "
							+ " from index_master where index_name = '"
							+ ind_name + "' ";
					try {
						pseq = connection.prepareStatement(query_get_ind_id);
						rst_id = pseq.executeQuery();
						while (rst_id.next()) {
							ind_id = Integer.parseInt(rst_id.getString(1));
							index_id = new Integer(ind_id).toString();
							Logging.debug("value of id.........:" + index_id);
						}
					} catch (Exception em) {
						Logging.error("inside for catch.....4"
								+ em.getMessage());
						buffer.append("<tr><font color='red'>Index Does not exist.Cant import file.</font></tr>");
					}
					if ((((cap_index.getIndex_value()).equals("0.00")) || (((cap_index
							.getIndex_value()).equals("0"))))
							|| (((cap_index.getClosing_value()).equals("0.00")) || (((cap_index
									.getClosing_value()).equals("0"))))
							|| (((cap_index.getLow_value()).equals("0.00")) || (((cap_index
									.getLow_value()).equals("0"))))
							|| (((cap_index.getOpen_value()).equals("0.00")) || (((cap_index
									.getOpen_value()).equals("0"))))
							|| (((cap_index.getHigh_value()).equals("0.00")) || (((cap_index
									.getHigh_value()).equals("0"))))
							|| (((cap_index.getMkt_cap_value()).equals("0.00")) || (((cap_index
									.getMkt_cap_value()).equals("0"))))
							|| (((cap_index.getDivisor_value()).equals("0.00")) || (((cap_index
									.getDivisor_value()).equals("0"))))) {
						flag = false;
						buffer.append("<tr><td>");
						buffer.append(key);
						buffer.append("</td><td><font color='blue'>Can Not Store Zero Values For Captured Index.</font></td></tr>");
						continue;
					}
					if (index_id.equals("0") && flag == true) {
						flag = false;
						buffer.append("<tr><td>");
						buffer.append(key);
						buffer.append("</td><td><font color='blue'>Captured Index Does Not Exist.</font></td></tr>");
						continue;
					}
					// inserts value in intra day indices
					// Logging.getDebug("Inside for loop");
					if (flag == true) {
						Logging.debug("hashtable data is ");
						query = ConnectInit.queries
								.getProperty("insert_into_intra_day_indices");
						CapturedIndexForm.insert_in_IntraDay(cap_index, query);

						// select index_lowest_value,index_highest_value from
						// index value daily

						try {

							pst_preStat = connection
									.prepareStatement(ConnectInit.queries
											.getProperty("get_high_low_index_value"));
							// Logging.getDebug("ind_date is "+ind_date);
							// Logging.getDebug("index_id is "+index_id);
							pst_preStat.setString(1, index_id);// set ? for
																// index id
							pst_preStat.setString(2, ind_date);
							// Logging.getDebug("pst_preStat is "+pst_preStat);
							rst2 = pst_preStat.executeQuery();// execute query
							Logging.debug("rst2.next() is " + rst2.next());
							// if no enteries
							Logging.debug("rst2.getRow()" + rst2.getRow());
							if (rst2.getRow() == 0) {
								Logging.debug("no entries if zero");
								CapturedIndexForm.insert_in_IndValue(cap_index);
								buffer.append("<tr><td>");
								buffer.append(key);
								buffer.append("</td><td><font color='blue'>Captured Index Values Inserted succesfully.</font></td></tr>");
							} else {

								try {
									double low = rst2
											.getDouble("index_lowest_value");
									double high = rst2
											.getDouble("index_highest_value");
									double indexVal = (double) Double
											.parseDouble(cap_index
													.getIndex_value());
									// Logging.getDebug("In else high low");
									// Logging.getDebug(" high "+high+" low "+low+" indexVal "+indexVal);
									if (indexVal > high) {
										// update_high_index_value

										pst_preStat = connection
												.prepareStatement(ConnectInit.queries
														.getProperty("update_high_index_value"));
										pst_preStat.setDouble(1, indexVal);
										pst_preStat.setString(2, index_id);
										pst_preStat.setString(3, ind_date);
										pst_preStat.executeUpdate();
										buffer.append("<tr><td>");
										buffer.append(key);
										buffer.append("</td><td><font color='blue'>Captured Index High Value updated succesfully.</font></td></tr>");
									}
									if (indexVal < low) {
										// update_low_index_value

										pst_preStat = connection
												.prepareStatement(ConnectInit.queries
														.getProperty("update_low_index_value"));
										pst_preStat.setDouble(1, indexVal);
										pst_preStat.setString(2, index_id);
										pst_preStat.setString(3, ind_date);
										pst_preStat.executeUpdate();
										buffer.append("<tr><td>");
										buffer.append(key);
										buffer.append("</td><td><font color='blue'>Captured Index Low Value updated succesfully.</font></td></tr>");
									}
									if (!(indexVal > high) && !(indexVal < low)) {
										buffer.append("<tr><td>");
										buffer.append(key);
										buffer.append("</td><td><font color='blue'>Captured Index tmcv and Divisor Value updated succesfully.</font></td></tr>");
									}
								} catch (SQLException ae) {
									Logging.error("ERROR: " + ae.getMessage());
								}
							}
							// }
						} catch (SQLException ae) {
							Logging.error("ERROR: " + ae.getMessage());
						}
						/*
						 * buffer.append("<tr><td>"); buffer.append(key);
						 * buffer.append(
						 * "</td><td><font color='blue'><b>Captured Index updated succesfully.</b></font></td></tr>"
						 * );
						 */
					}
				} catch (Exception ex) {
					Logging.error("insidetry proc........" + ex.getMessage());
				}
			}
			// return buffer;

			table.clear();
			str_fileName = null;
			stock_exid = "";
			FileFormatOK = true;
			ExchangeOK = true;
			file_type = null;
			file_type_name = null;
		} catch (Exception e) {
			Logging.error("try procedure........" + e.getMessage());
		} finally {
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
	 * to compare the dates if two dates in string format are passed then return
	 * the integer value. +ve if (first date>second date),-ve value if (first
	 * date<second date) and 0 value if (first date=second date).
	 * 
	 * @param basedate
	 * @param history_Date
	 * @return
	 */
	public int CompareDate(String basedate, String history_Date) {
		Logging.debug("basedate is " + basedate + " history_Date is "
				+ history_Date);
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

	public String getStock_exid() {
		return stock_exid;
	}

	public void setStock_exid(String stock_exid) {
		this.stock_exid = "";
		Logging.debug("The stock_exid is" + stock_exid);
		this.stock_exid = stock_exid;
		// Logging.getDebug("setstock_exid is "+ stock_exid);
	}

	public String getStockid(String CName, String Symbol, String series,
			String ExchangeName) {
		Logging.debug("Inside getstock id");
		String stockid = "";
		Connection connection = null;
		// Change by Manoj Adekar for Dynamic connection using getdbConnection()
		// instead of getConnection()
		try {
			String qry = null;
			Connect connect = ConnectInit.getConnect();
			if (connection == null) {
				connection = connect.getdbConnection();
			}
			PreparedStatement pst;
			ResultSet result;
			Logging.debug("company name is  " + CName + "Symbol " + Symbol
					+ " series" + series + "Exchange Name is " + ExchangeName);
			if (!(Symbol.equals(""))) {
				pst = connection
						.prepareStatement(ConnectInit.queries
								.getProperty("get_stock_id_where_symbol_series_exname"));
				pst.setString(1, series);
				pst.setString(2, Symbol);
				pst.setString(3, ExchangeName);
				Logging.debug("pst" + pst);
				result = pst.executeQuery();
				if (result.next()) {
					// Logging.getDebug("stock id found is "+result.getString(0));
					stockid = result.getString(1);
				}
			}
		} catch (Exception e) {
			Logging.error("Errro while finding stock_id " + e.getMessage());
			return null;
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
		return stockid;
	}

	public Object getStockObject(String stockid) {
		Logging.debug("Inside getstockobject" + stockid);
		String stockName;
		double iwf;
		double ltp;
		String currency;
		long tis;
		long market_lot;
		String date;
		StockDetails sD = null; // / creating instance of class stockdetails
		Connection connection = null;
		// Change by Manoj Adekar for Dynamic connection using getdbConnection()
		// instead of getConnection()
		try {
			String qry = null;
			Logging.debug("Inside try");
			Connect connect = ConnectInit.getConnect();
			Logging.debug("Connect connect = ConnectInit.getConnect();");

			if (connection == null) {
				Logging.debug("Connection is null");
				connection = connect.getdbConnection();
				Logging.debug("got Connection");
			}

			PreparedStatement pst;
			ResultSet result;
			Logging.debug("before getting query");
			pst = connection.prepareStatement(ConnectInit.queries
					.getProperty("get_stock_details_where_stock_id"));
			pst.setString(1, stockid);
			pst.setString(2, stockid);
			pst.setString(3, stockid);
			Logging.debug("prepared statement is " + pst);
			result = pst.executeQuery();
			if (result.next()) {
				stockid = result.getString(1);
				stockName = result.getString(2);
				iwf = Double.parseDouble(result.getString(3));
				ltp = Double.parseDouble(result.getString(4));
				currency = result.getString(5);
				tis = Long.parseLong(result.getString(6));
				market_lot = Long.parseLong(result.getString(7));
				date = result.getString(8);
				Logging.debug("Before calling stockdetails constructor");
				sD = new StockDetails(Integer.parseInt(stockid), stockName,
						iwf, ltp, currency, tis, market_lot, date);
				Logging.debug("After calling stockdetails constructor");
			}
		} catch (Exception e) {
			Logging.error("Errro while finding stockobject " + e.getMessage());
			return null;
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
		return sD;
	}

	/**
	 * @return Returns the table.
	 */
	public Hashtable getTable() { // static
		return table;
	}

	/**
	 * @param table
	 *            The table to set.
	 */
	public void setTable(Hashtable table) { // static
		this.table = table;
	}

	/**
	 * @return Returns the checkPriceOnDate.
	 */
	public boolean isCheckPriceOnDate() {
		return checkPriceOnDate;
	}

	/**
	 * @param checkPriceOnDate
	 *            The checkPriceOnDate to set.
	 */
	public void setCheckPriceOnDate(boolean checkPriceOnDate) {
		this.checkPriceOnDate = checkPriceOnDate;
	}

	/**
	 * @return Returns the hist_Date.
	 */
	public String getHist_Date() {
		return hist_Date;
	}

	/**
	 * @param hist_Date
	 *            The hist_Date to set.
	 */
	public void setHist_Date(String hist_Date) {
		this.hist_Date = hist_Date;
	}

	/**
	 * @return Returns the correctedFile.
	 */
	public String getCorrectedFile() {
		return correctedFile;
	}

	/**
	 * @param correctedFile
	 *            The correctedFile to set.
	 */
	public void setCorrectedFile(String correctedFile) {
		this.correctedFile = correctedFile;
	}

	/**
	 * to check if prices already available on filedate. return true if prices
	 * already available on filedate else return false.
	 * 
	 * @param exch_id
	 * @return
	 */
	/*
	 * public static boolean CheckForPricesOnSameDate(String exch_id){ boolean
	 * flag=false; try{ Connect connect1=ConnectInit.getConnect();
	 * QueryClass.formatDate(); filedate=ComposeIndex.FormatDateMon1(hist_Date);
	 * Logging.getDebug("fdate is "+filedate); PreparedStatement alertpst =
	 * Connect.con.prepareStatement(connect1.queries
	 * .getProperty("importFile.check_stock_prices_on_date"));
	 * alertpst.setString(1,exch_id); alertpst.setString(2,filedate); ResultSet
	 * alertresult=alertpst.executeQuery(); while(alertresult.next()){
	 * flag=true; break; } //mdate=formatDate(mdate);
	 * //Logging.getDebug("after formatting mdate is "+mdate); }catch(Exception
	 * e){ Logging.getError("Error : "+e.getMessage()); } return flag; }
	 */
	public static boolean CheckForPricesOnSameDate(String exch_id) {
		boolean flag = false;
		Connect connect1 = ConnectInit.getConnect();
		PreparedStatement alertpst = null;
		ResultSet alertresult = null;//
		Connection con = null;
		try {
			con = connect1.getdbConnection();
			filedate = hist_Date;
			Logging.debug("fdate is " + filedate);
			Logging.debug("exch_id is " + exch_id);
			alertpst = con.prepareStatement(ConnectInit.queries
					.getProperty("importFile.check_stock_prices_on_date"));
			alertpst.setString(1, exch_id);
			alertpst.setString(2, filedate);
			alertresult = alertpst.executeQuery();
			while (alertresult.next()) {
				flag = true;
				break;
			}
			// mdate=formatDate(mdate);
			// Logging.getDebug("after formatting mdate is "+mdate)
			alertresult.close();
			alertpst.close();
		} catch (Exception e) {
			Logging.error("Error : " + e.getMessage());
		} finally {
			try {
				if (alertpst != null)
					alertpst.close();
				if (alertresult != null)
					alertresult.close();
				if (con != null)
					con.close();
			} catch (SQLException ex) {
				Logging.error("Error : Unable to close Connection object,ResultSet,PreparedStatement "
						+ ex.getMessage());
			}
		}
		return flag;
	}

	/**
	 * @return Returns the fromDownload.
	 */
	public String getFromDownload() {
		return fromDownload;
	}

	/**
	 * @param fromDownload
	 *            The fromDownload to set.
	 */
	public void setFromDownload(String fromDownload) {
		this.fromDownload = fromDownload;
	}
}
