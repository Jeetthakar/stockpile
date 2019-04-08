/*
 * Created on Nov 17, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.harrier.initializeation.ConnectInit;

/**
 * @author naresh
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ReadIndexOHLC {
	Logger Logging = Logger.getLogger(ReadIndexOHLC.class);
	private boolean FileFormatOK = true;

	Vector vector_importfilelist;

	public static Hashtable table = new Hashtable(20000);//static

	private String file_type = null;

	private String file_type_name = null;

	/**
	 * @param file_type
	 *            The file_type to set.
	 */
	public void setFile_type(String File_type) {
		Logging.debug("set File type is " + File_type);
		this.file_type = "";
		this.file_type = File_type;
	}
	
	public StringBuffer displaydat(String str_fileName, IndexOHLCForm dfu) {
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
		String str_dirName = path + "/CoolMenus/";//get directory path upto
		Logging.debug(str_dirName);
		Logging.debug("file name is using or " + str_fileName);
		str_fileName = str_dirName + str_fileName;
		Logging.debug("File Nmae is " + str_fileName);
		String temp = "";
		temp = str_fileName.substring(str_fileName.lastIndexOf(".") + 1);
		if(!temp.equalsIgnoreCase("csv")){
			buffer.append("<font size='2' face='Arial' color='Red'><tr>Please Choose *.csv/ File Extension</tr></font>");
			return buffer;
		}
		if (temp.equalsIgnoreCase("csv")) {
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
				int counter = 0;//Counter used to craete unique hashkey.
				while ((str = br.readLine()) != null) {
					counter++;
					arr = str.split(",");
					i = 0;

					file_type_name = null;
					IndexOHLC FD = new IndexOHLC();
					Logging.debug("Before switch" + arr.length);
					switch (arr.length)//work out using table file import and
									   // related
					{
					case 11:
						if (file_type.equals("1"))
							file_type_name = "Open_High_Low_Close_File";
						break;
					default:
						FileFormatOK = false;
						Logging.debug("Inside switch with " + arr.length
								+ " No cases matched");
						buffer.append("<tr><td>Improper file format</td></tr>");
						break;
					}
					if (FileFormatOK == false) {
						buffer
								.append("<font size='2' face='Arial' color='Red'><tr>File with improper format </tr></font>");
						FileFormatOK = true;
						return buffer;
					}
					buffer.append("<tr>");
					while (i < arr.length) {
						//Logging.getDebug("Inside while of First Readline");
						buffer.append("<td>");
						buffer
								.append("<font size='2' face='Arial' color='Blue'>"
										+ arr[i++] + "</font>");
						buffer.append("</td>");
					}
					buffer.append("</tr>");
					if (file_type.equals("1")
							&& file_type_name != null
							&& file_type_name
									.equals("Open_High_Low_Close_File")) {
						Logging.debug("Calling Open_High_Low_Close_File");
						buffer = getHashnBuffer1(buffer, br);

					}
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

	public StringBuffer getHashnBuffer1(StringBuffer buffer, BufferedReader br) {
		Logging.debug("INside fdr");
		String str;
		int linecounter = 0;
		int keycounter = 0;
		try {
			Logging.debug("Inside FDR try");
			String[] arr;
			int i;
			while ((str = br.readLine()) != null) {
				linecounter++;
				buffer.append("<tr>");
				arr = str.split(",");
				i = 0;
				if (arr.length == 0)
					continue;
				IndexOHLC FD = new IndexOHLC();
				int arrlen = 11;
				String Index_date = null, Open = null, High = null, Low = null, Close = null, Marketcap = null, Tradevolume = null, Turnover = null, Pe = null, Divident = null, Pb = null;
				buffer.append(" <tr>");
				while (i < arr.length) {

					switch (i) {
					case 0:
						Index_date = (arr[i]);
						FD.setIndex_date(Index_date);
						break;
					case 1:
						Open = (arr[i]);
						FD.setOpen(Open);
						break;
					case 2:
						High = (arr[i]);
						FD.setHigh(High);
						break;
					case 3:
						Low = (arr[i]);
						FD.setLow(Low);
						break;
					case 4:
						Close = (arr[i]);
						FD.setClose(Close);
						break;
					case 5:
						Marketcap = (arr[i]);
						FD.setMarketcap(Marketcap);
						break;
					case 6:
						Tradevolume = (arr[i]);
						FD.setTradevolume(Tradevolume);
						break;
					case 7:
						Turnover = (arr[i]);
						FD.setTurnover(Turnover);
					case 8:
						Pe = (arr[i]);
						FD.setPe(Pe);
						break;
					case 9:
						Pb = (arr[i]);
						FD.setPb(Pb);
						break;
					case 10:
						Divident = (arr[i]);
						FD.setDivident(Divident);
						break;
					default:
						Logging.debug("Default switch case : updatef");
						break;
					}
					if (arr[i] == null || arr[i].equals("")) {
						buffer
								.append("<td align='center'><font color='white'> ");
						buffer.append(".");
						buffer.append(" </font></td>");
					} else {
						buffer.append("<td> ");
						buffer.append(arr[i]);
						buffer.append(" </td>");
					}
					i++;
				}
				buffer.append(" </tr>");

				table.put(FD.getIndex_date(), FD);
			}
		} catch (Exception e) {
			Logging.error("Error : " + e.getMessage());
			return null;
		}
		return buffer;
	}

	public Vector getVector_importfilelist() {
		return vector_importfilelist;
	}

	public void setVector_importfilelist() {
		vector_importfilelist = new Vector();
		int i = 1;
		vector_importfilelist.add(new Integer(i));
		vector_importfilelist.add("Open_High_Low_Close_File");//1
		Logging.debug("Inside setvector importfile");
	}

	public Hashtable getTable() { //static
		return table;
	}

	/**
	 * @param table
	 *            The table to set.
	 */
	public void setTable(Hashtable table) { //static
		this.table = table;
	}

	public StringBuffer storeStock(HttpServletRequest request, String indexid) {

		StringBuffer buffer = new StringBuffer();
		StringBuffer buffernew = new StringBuffer();
		Enumeration e = table.keys();
		String file_date = null;
		Connect con = ConnectInit.getConnect();
		ResultSet rs = null;
		PreparedStatement pst = null;
		PreparedStatement pst1 = null;
		Connection connection = null;
		int count = 0;
		int counter1 = 0;
		int inscounter = 0;
		int updcounter = 0;
		Logging.debug("The index id isis="+indexid);
		try {
			if (connection == null)
				connection = con.getdbConnection();
		pst = connection.prepareStatement(ConnectInit.queries
					.getProperty("select_indexes"));
		pst1 = connection.prepareStatement(ConnectInit.queries
				.getProperty("update_daily_indices_open"));
		if(!e.hasMoreElements())
		{
			return null;
		}
			for (e = table.keys(); e.hasMoreElements();) {
				counter1++;
				boolean flag = false;
				Logging.debug("inside loop");
				file_date = (String) e.nextElement();
				Logging.debug("file_date isisis="+file_date);
				String file_date1=org.jfree.chart.demo.servlet.ComposeIndex.FormatDateMon(file_date);
				Logging.debug("file_date1 isisis="+file_date1);
				IndexOHLC FD = (IndexOHLC) table.get(file_date);
				IndexOHLCForm Iof = new IndexOHLCForm();
				int indid = Integer.parseInt(indexid);
				double Open_Index_Value = 0;
				Open_Index_Value = Double.parseDouble(FD.getOpen());
				double high_Index_value = 0;
				high_Index_value = Double.parseDouble(FD.getHigh());
				double low_Index_value = 0;
				low_Index_value = Double.parseDouble(FD.getLow());
				double closeIndex_value = 0;
				closeIndex_value = Double.parseDouble(FD.getClose());
				String markcap = FD.getMarketcap();
				String Pe = FD.getPe();
				String Pb = FD.getPb();
				String Tervolume = FD.getTradevolume();
				String div = FD.getDivident();
				String Netternover = FD.getTurnover();
				double Marketcap, pe, pb, volume, Ternover, Divyield;
				if (markcap == null) {
					Marketcap = 0;
				} else {
					Marketcap = Double.parseDouble(markcap);
				}
				if (Pe == null) {
					pe = 0;
				} else {
					pe = Double.parseDouble(Pe);
				}
				if (Pb == null) {
					pb = 0;
				} else {
					pb = Double.parseDouble(Pb);
				}
				if (Tervolume == null) {
					volume = 0;
				} else {
					volume = Double.parseDouble(Tervolume);
				}
				if (Netternover == null) {
					Ternover = 0;
				} else {
					Ternover = Double.parseDouble(Netternover);
				}
				if (div == null) {
					Divyield = 0;
				} else {
					Divyield = Double.parseDouble(div);
				}
				
				Logging.debug("index_id" + indexid);
				try {
					Logging.debug("Before first try");
					pst.setInt(1, indid);
					pst.setString(2, file_date);
					ResultSet rs1 = pst.executeQuery();
					Logging.debug("After resultset");
					while (rs1.next()) {
						count = Integer.parseInt(rs1.getString(1));
					}
					Logging.debug("After Query1");

					if (count != 0) {
						try {
							pst1.setDouble(1, Open_Index_Value);
							pst1.setDouble(2, high_Index_value);
							pst1.setDouble(3, low_Index_value);
							pst1.setDouble(4, closeIndex_value);
							//pst1.setDouble(5, Marketcap);
							//pst1.setDouble(6, pe);
							//pst1.setDouble(7, pb);
							//pst1.setDouble(8, Divyield);
							//pst1.setDouble(9, Ternover);
							//pst1.setDouble(10, volume);
							pst1.setInt(5, indid);
							pst1.setString(6, file_date);
							pst1.executeUpdate();
							inscounter++;
							buffer.append("<tr><td>");
							buffer.append(file_date);
							buffer
									.append("</td><td>Index Value Updated for that Date</td></tr>");
							Logging.info("Updated Indices naresh Successfully");
						} catch (Exception e2) {
							Logging.error("Error : " + e2.getMessage());
						}
					}
					if (count == 0) {
						updcounter++;
						buffer.append("<tr><td>");
						buffer.append(file_date);
						buffer
								.append("</td><td><font color='blue'>No Index value Found On particular Date</font></td></tr>");
					}
				} catch (Exception e1) {
					Logging.error("Error : " + e1.getMessage());
				}

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
		table.clear();
		Logging.debug("*********************b " + buffer);
		
		buffernew.append("<br><tr><font color=Blue><td>Total Rows :</td><td>");
		buffernew.append(counter1);
		buffernew.append("</td></font></tr>");
		buffernew
				.append("<br><tr><font color=Blue><td>Values Updated :</td><td>");
		buffernew.append(inscounter);
		buffernew.append("</td></font></tr>");
		buffernew
				.append("<br><tr><font color=Blue><td>Value Not Found :</td><td>");
		buffernew.append(updcounter);
		buffernew.append("</td></font></tr>");
		buffernew.append(buffer);
		buffer = null;
		return buffernew;
	}//met

}