package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.harrier.initializeation.ConnectInit;

/**
 * @author ashishi
 *
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class PopFileDialogNewStockDat {
	static Logger Logging = Logger.getLogger(PopFileDialogNewStockDat.class);
	Hashtable targethashtable = new Hashtable();
	Hashtable tablen = new Hashtable();
	private static String str_fileName = null;

	private Corporate corp = new Corporate();
	private String stock_exid = "";
	private boolean FileFormatOK = true;
	private boolean ExchangeOK = true;
	public static String filedates;
	Vector vector_importfilelist;
	private static String filedate = null;
	public static Hashtable table = new Hashtable(20000);// static
	private String file_type = null;
	private static String hist_Date = null;
	private String file_type_name = null;
	Connection con = null;
	PopFileDialog pfd = null;
	String fromIndexcomposition = null;

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

	/**
	 * @param filedate
	 *            The filedate to set.
	 */
	public static void setFiledate(String filedate) {
		PopFileDialogNewStockDat.filedates = filedate;
		Logging.debug("Date in Pop" + filedate);
	}

	/**
	 * @return Returns the filedate.
	 */
	public static String getFiledate() {
		return filedates;
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
	 * method to check extension(like csv,dbf,xml) of file and returns true if
	 * chosen file extension is allowed.
	 */
	private boolean checkExt() {
		Logging.debug("Inside check ext");
		String temp = "";
		Logging.debug("file is null");
		temp = str_fileName.substring(str_fileName.lastIndexOf(".") + 1);
		Logging.debug(temp);
		if (temp.equalsIgnoreCase("DAT"))
			return true;
		else
			return false;
	}

	public StringBuffer displaydat(String str_fileName, DatFileUploadForm dfu) {
		String errorMessage;
		Logging.debug("Display File Name is " + str_fileName);
		Logging.debug("inside display");
		if ((str_fileName == null) || (str_fileName.trim().length() == 0)) {
			Logging.debug("file is null using or");
			return null;
		}
		errorMessage = null;
		StringBuffer buffer = new StringBuffer();
		String path = Connect.getCoolMenuspath();
		Logging.debug("path is : " + path);
		String str_dirName = path + "/CoolMenus/";// get directory path upto
													// CoolMenus Folder.
		Logging.debug(str_dirName);
		Logging.debug("file name is using or " + str_fileName);
		str_fileName = str_dirName + str_fileName;
		Logging.debug("File Nmae is " + str_fileName);
		ExchangeOK = true;
		String temp = "";
		temp = str_fileName.substring(str_fileName.lastIndexOf(".") + 1);
		Logging.debug("Exchang id after ifs" + stock_exid);

		if (temp.equalsIgnoreCase("DAT")) {
			try {
				Logging.debug("File path is " + str_fileName);
				FileReader file = new FileReader(str_fileName);
				BufferedReader br = new BufferedReader(file);
				String str = "";
				int i;
				FileFormatOK = true;
				String file_type = dfu.getFileType();
				Logging.debug("File type after ifs " + file_type);
				String[] arr;
				int count = 0;
				int counter = 0;// Counter used to craete unique hashkey.
				while ((str = br.readLine()) != null) {
					counter++;
					arr = str.split(",");
					i = 0;
					file_type_name = null;
					UpdateDatForm FD = new UpdateDatForm();
					Logging.debug("Before switch" + arr.length);
					switch (arr.length)// work out using table file import and
										// related
					{
					case 10:
						if (file_type.equals("1"))
							file_type_name = "Open_High_Low_Close_File";
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

					Integer c1 = new Integer(counter);
					String count_int = c1.toString();
					String key_fr_hashtable = "Index" + count_int;
					buffer.append(key_fr_hashtable);
					buffer.append(",");
					while (i < arr.length) {
						switch (i) {
						case 0:
							FD.setIndex_Name(arr[i]);

							break;
						case 1:
							FD.setPrevious_Close(arr[i]);
							break;
						case 2:
							FD.setOpen_Close(arr[i]);
							break;
						case 3:
							FD.setHigh_Close(arr[i]);
							break;
						case 4:
							FD.setLow_Close(arr[i]);
							break;
						case 5:
							FD.setPercentage_Change(arr[i]);
							break;
						case 6:
							FD.setFiFty_Two_week_High(arr[i]);
							break;
						case 7:
							FD.setFifty_Two_Week_Low(arr[i]);
						case 8:
							FD.setClose(arr[i]);
							break;
						case 9:
							FD.setDate(arr[i]);
							break;

						default:
							Logging.debug("Default switch case : updatef");
							break;
						}
						if (arr[i].equals("") || arr[i] == null) {
						} else {
							buffer.append(arr[i]);
							buffer.append(",");
						}
						i++;
					}
					table.put(key_fr_hashtable, FD);
				}
			} catch (IOException e) {
				errorMessage = e.getMessage();
				Logging.error("Error : " + e.getMessage());
				e.getStackTrace();
				return null;
			}
		}
		Logging.debug("Display Complete");
		return buffer;
	}

	public Vector getVector_importfilelist() {
		return vector_importfilelist;
	}

	public void setVector_importfilelist() {
		vector_importfilelist = new Vector();
		int i = 1;
		vector_importfilelist.add(new Integer(i));
		vector_importfilelist.add("Open_High_Low_Close_File");// 1
		Logging.debug("Inside setvector importfile");
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

	/**
	 * @return Returns the hist_Date.
	 */
	public static String getHist_Date() {
		return hist_Date;
	}

	/**
	 * @param hist_Date
	 *            The hist_Date to set.
	 */
	public static void setHist_Date(String hist_Date) {
		PopFileDialogNewStockDat.hist_Date = hist_Date;
	}

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

	public StringBuffer storeStock(HttpServletRequest request) {

		StringBuffer buffer = new StringBuffer();
		String str = null;
		Enumeration e = table.keys();
		String oldIndexName = null;
		String newIndexName = null, date = null;
		String User_Date = null;
		Connect con = ConnectInit.getConnect();
		ResultSet rs = null;
		PreparedStatement pst = null;
		PreparedStatement pst1 = null;
		Connection connection = null;
		int count = 0;
		try {
			if (connection == null)
				connection = con.getdbConnection();
			for (e = table.keys(); e.hasMoreElements();) {

				boolean flag = false;
				Logging.debug("inside loop");
				str = (String) e.nextElement();
				UpdateDatForm FD = (UpdateDatForm) table.get(str);
				oldIndexName = FD.getIndex_Name();
				oldIndexName = oldIndexName.trim();
				int indexid = 0;
				double Open_Index_Value = Double
						.parseDouble(FD.getOpen_Close());
				double high_Index_value = Double
						.parseDouble(FD.getHigh_Close());
				double low_Index_value = Double.parseDouble(FD.getLow_Close());
				double closeIndex_value = Double.parseDouble(FD
						.getPercentage_Change());
				User_Date = PopFileDialogNewStockDat.getHist_Date();
				// SimpleDateFormat fr1=new SimpleDateFormat("dd-MM-yyyy");
				// String User_Input=fr1.format(User_Date).toString();

				newIndexName = request.getParameter(str);
				Logging.debug("new str value " + str);
				Logging.debug("old index name is " + oldIndexName
						+ " new index name is " + newIndexName);
				pst = null;
				rs = null;
				pst = connection.prepareStatement(ConnectInit.queries
						.getProperty("get_indexid_for_nsestock"));
				pst.setString(1, newIndexName.trim());
				rs = pst.executeQuery();
				buffer.append("<tr><td>Index Name</td><td>Result Status</td></tr>");
				while (rs.next()) {
					indexid = rs.getInt(1);
					flag = true;
					Logging.debug(indexid);
					pst = null;

					try {
						Logging.debug("Before first try");
						pst = connection.prepareStatement(ConnectInit.queries
								.getProperty("select_indexes"));
						pst.setInt(1, indexid);
						pst.setString(2, User_Date);

						ResultSet rs1 = pst.executeQuery();
						Logging.debug("After resultset");

						while (rs1.next()) {
							count = Integer.parseInt(rs1.getString(1));
						}
						Logging.debug("After Query1");

						if (count != 0) {
							try {
								pst1 = connection
										.prepareStatement(ConnectInit.queries
												.getProperty("update_daily_indices_open"));
								pst1.setDouble(1, Open_Index_Value);
								pst1.setDouble(2, high_Index_value);
								pst1.setDouble(3, low_Index_value);
								pst1.setDouble(4, closeIndex_value);
								pst1.setInt(5, indexid);
								pst1.setString(6, User_Date);
								pst1.executeUpdate();
								buffer.append("<tr><td>");
								buffer.append(newIndexName);
								buffer.append("</td><td><font color='blue'>Index Value Updated</font></td>");
								Logging.info("Updated Indices Successfully");
							} catch (Exception e2) {
								Logging.error("Error : " + e2.getMessage());
							}
						}
						if (count == 0) {
							buffer.append("<tr><td>");
							buffer.append(newIndexName);
							Logging.debug("the indexid is=" + indexid);
							buffer.append("<tr><td>");
							buffer.append("No Index Found On Users Entered Date");
							buffer.append("<tr><td>");
						}
					} catch (Exception e1) {
						Logging.error("Error : " + e1.getMessage());
					}

				}
				if (flag == false) {
					buffer.append("<tr><td>");
					buffer.append(newIndexName);
					buffer.append("</td><td><font color='blue'>No Index Found</font></td>");
					buffer.append("<tr><td>");
					Logging.info("Insert Index_id value is not present");
				}
				/*
				 * buffer.append("<tr><td>"); buffer.append(""); buffer.append(
				 * "</td><td><font color='blue'>Prices Updated Successfully For Indices</font></td>"
				 * ); Logging.getInfo("Updated Indices Successfully");
				 */
				// tablen.clear();//Clear the hashtable which is used to store
				// unique hashkeys of csv file.

			}
		} catch (Exception ee) {
			Logging.error(" Error : " + ee.getMessage());
		} finally {
			try {
				if (pst1 != null)
					pst1.close();
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (connection != null)
					connection.close();

			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		} /* end of finally */
		Logging.debug("*********************b " + buffer);
		return buffer;
	}// method complet
}
