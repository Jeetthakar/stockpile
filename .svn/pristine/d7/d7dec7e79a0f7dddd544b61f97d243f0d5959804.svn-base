/*
 * Created on Aug 31, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.harrier.ftp;

import harrier.income.com.masters.CapturedIndexForm;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import org.apache.log4j.Logger;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * @author sunil
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Ind_FtpReadFile {
	Logger Logging = Logger.getLogger(Ind_FtpReadFile.class);
	private Hashtable table = new Hashtable();

	CapturedIndexForm cap_index = null;

	ResultSet rst2 = null;

	/**
	 * method to display the contained of latest .ind file from FTP server on
	 * current date.
	 * 
	 * @param exchange_id
	 * @param filename
	 * @return StringBuffer
	 */
	public StringBuffer display_IndFtpFile(String exchange_id, String filename) {
		StringBuffer buffer = new StringBuffer();//string buffer for displaying
												 // contnt of file
		short Trans_c = 0;//transcode for detecting file uploaded
		int Time_stamp = 0;//time stamp
		String index_name = null;//index name
		short Message_l = 0;//length of message
		short ind_token = 0;//index token for detecting captured index name
		float curr_ind_val = 0.0F;//current index value
		float high_ind_val = 0.0F;//high index value
		float low_ind_val = 0.0F;//low index value
		float per_change = 0.0F;//percentage change in index value
		int filler = 0;//filler
		try {
			String style = null;
			buffer.append("<tr width = '100%'>");
			style = "gridStyle-header color='blue' ";

			buffer.append("<td width='5%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("Index Token");//index token
			buffer.append("</td>");
			buffer.append("<td width='30%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("Index Name");//captured index name
			buffer.append("</td>");
			buffer.append("<td width='10%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("Current Index Value");//current index value
			buffer.append("</td>");
			buffer.append("<td width='10%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("High Index Value");//high index value
			buffer.append("</td>");
			buffer.append("<td width='10%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("Low Index Value");//low index value
			buffer.append("</td>");
			buffer.append("<td width='10%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("Percentage Change");//percentage change
			buffer.append("</td>");
			buffer.append("<td width='10%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("Filler");//market capital value.
			buffer.append("</td>");
			buffer.append("<td width='15%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("Time Stamp");//time stamp
			buffer.append("</td>");
			buffer.append("</tr>");
			buffer.append("<tr>");
			Hashtable indName_list = getIndexName();
			Properties rs = new Properties();
			rs.load(new FileInputStream(Connect.resourceurl
					+ "resources/ftpDetails.properties"));
			String destination = rs.getProperty("mkt_ind_destination");
			File TransFileIn = new File(destination + filename);

			FileInputStream File_In = new FileInputStream(TransFileIn);
			DataInputStream Data_In = new DataInputStream(File_In);
			while (Data_In.available() != 0) {
				cap_index = new CapturedIndexForm();
				Trans_c = Short.reverseBytes((short) Data_In.readShort());
				Logging.debug(" Trans_c is " + Trans_c);
				Time_stamp = Integer.reverseBytes(Data_In.readInt());
				Logging.debug(" Time_stamp is " + Time_stamp);
				long ddt = Math.abs(Time_stamp);
				ddt = ddt * 1000;
				Logging.debug(" ddt is " + ddt);
				Date dd = new Date(ddt);
				
				SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
				String date=fr.format(dd).toString();
				Logging.debug(" date is " + date);
				cap_index.setDate(date);
				Logging.debug(" new java.sql.Dat(ddt) is "
						+ new Timestamp(ddt));
				Message_l = Short.reverseBytes((short) Data_In.readShort());
				Logging.debug(" Message_l is " + Message_l);
				ind_token = Short.reverseBytes((short) Data_In.readShort());
				Logging.debug(" ind_token is " + ind_token);
				buffer.append("<tr>");
				buffer.append("<td>");
				buffer.append(ind_token);
				buffer.append("</td>");
				if (indName_list.containsKey(new Short(ind_token).toString())) {
					index_name = (String) indName_list.get(new Short(ind_token)
							.toString());
				}
				buffer.append("<td>");
				buffer.append(index_name);
				cap_index.setIndex_name(index_name);
				buffer.append("</td>");
				curr_ind_val = Integer.reverseBytes(Data_In.readInt());
				curr_ind_val /= 100;				
				buffer.append("<td align='right'>");
				buffer.append(curr_ind_val);
				cap_index.setIndex_value(new Float(curr_ind_val).toString());
				cap_index.setOpen_value(new Float(curr_ind_val).toString());
				cap_index.setClosing_value(new Float(curr_ind_val).toString());
				buffer.append("</td>");
				Logging.debug(" curr_ind_val is " + curr_ind_val);
				high_ind_val = Integer.reverseBytes(Data_In.readInt());
				high_ind_val /= 100;
				buffer.append("<td align='right'>");
				cap_index.setHigh_value(new Float(high_ind_val).toString());
				buffer.append(high_ind_val);
				buffer.append("</td>");
				Logging.debug(" high_ind_val is " + high_ind_val);
				low_ind_val = Integer.reverseBytes(Data_In.readInt());
				low_ind_val /= 100;
				buffer.append("<td align='right'>");
				cap_index.setLow_value(new Float(low_ind_val).toString());
				cap_index.setDivisor_value("0");
				cap_index.setMkt_cap_value("0");				
				buffer.append(low_ind_val);
				buffer.append("</td>");
				Logging.debug(" low_ind_val is " + low_ind_val);
				per_change = Integer.reverseBytes(Data_In.readInt());
				per_change /= 100;
				buffer.append("<td align='right'>");
				cap_index.setPer_change(new Float(per_change).toString());
				buffer.append(per_change);
				buffer.append("</td>");
				Logging.debug(" per_change is " + per_change);
				filler = Integer.reverseBytes(Data_In.readInt());
				buffer.append("<td align='right'>");
				buffer.append(filler);
				buffer.append("</td>");
				buffer.append("<td align='center'>");
				buffer.append(new Timestamp(ddt));
				buffer.append("</td>");
				Logging.debug(" filler is " + filler);
				Logging.debug("after query");
				Logging.debug("Trans code =" + Trans_c + ","
						+ "Time Stamp =" + Time_stamp + ","
						+ "Message lenght =" + Message_l + "," + "index token="
						+ ind_token + "," + "current index value="
						+ curr_ind_val + "," + "," + "high index value="
						+ high_ind_val + "," + "low index value=" + low_ind_val
						+ "," + "," + "percentage change=" + per_change + ","
						+ "filler =" + filler + "");
				buffer.append("</tr>");
				table.put(index_name,cap_index);
			}
		} catch (Exception e) {
			Logging.error("Error : " + e.getMessage());
		}
		return buffer;
	}

	/**
	 * method to save the details for captured indices contained in the latest
	 * .ind file uploaded from FTP server.
	 * 
	 * @return StringBuffer
	 */
	public StringBuffer storeCapIndices() {
		StringBuffer buffer = new StringBuffer();
		Logging.debug("inside storeCap..");
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
			try {
				if (connection == null)
					connection = connect.getConnectionForTransaction();
			} catch (Exception e) {
				Logging.error(" Error : " + e.getMessage());
			}
			PreparedStatement pst=null, pst_preStat=null, pseq=null;
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
					//Logging.getDebug("connection after counter 100 is
					// "+connect );
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
						Logging
								.error("Error : Unable to get Transaction connection "
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
					//ind_date=formatDate(ind_date);
					Logging.debug("name in try ........" + ind_name);
					String query_get_ind_id =ConnectInit.queries.getProperty("capIndex_get_Index_id");
					/*String query_get_ind_id = "select index_id "
							+ " from index_master where index_name = '"
							+ ind_name + "' ";*/
					try {
						pseq = connection.prepareStatement(query_get_ind_id);
						pseq.setString(1,ind_name);
						rst_id = pseq.executeQuery();
						while (rst_id.next()) {
							ind_id = Integer.parseInt(rst_id.getString(1));
							index_id = new Integer(ind_id).toString();
							Logging.debug("value of id.........:"
									+ index_id);
						}
					} catch (Exception em) {
						Logging.error("inside for catch.....4"
								+ em.getMessage());
						buffer
								.append("<tr><font color='red'>Index Does not exist.Cant import file.</font></tr>");
					}
					if ((((cap_index.getIndex_value()).equals("0.00")) || (((cap_index
							.getIndex_value()).equals("0"))))
							|| (((cap_index.getLow_value()).equals("0.00")) || (((cap_index
									.getLow_value()).equals("0"))))
							|| (((cap_index.getHigh_value()).equals("0.00")) || (((cap_index
									.getHigh_value()).equals("0"))))
							) {
						flag = false;
						buffer.append("<tr><td>");
						buffer.append(key);
						buffer
								.append("</td><td><font color='blue'>Can Not Store Zero Values For Captured Index.</font></td></tr>");
						continue;
					}
					if (index_id.equals("0") && flag == true) {
						flag = false;
						buffer.append("<tr><td>");
						buffer.append(key);
						buffer
								.append("</td><td><font color='blue'>Captured Index Does Not Exist.</font></td></tr>");
						continue;
					}
					//				inserts value in intra day indices
					//Logging.getDebug("Inside for loop");
					if (flag == true) {
						Logging.debug("hashtable data is ");
						query = ConnectInit.queries
								.getProperty("insert_into_intra_day_indices");
						CapturedIndexForm.insert_in_IntraDay(cap_index, query);

						//				 select index_lowest_value,index_highest_value from
						// index value daily

						try {

							pst_preStat = connection
									.prepareStatement(ConnectInit.queries
											.getProperty("get_high_low_index_value"));
							//Logging.getDebug("ind_date is "+ind_date);
							//Logging.getDebug("index_id is "+index_id);
							pst_preStat.setString(1, index_id);//set ? for
															   // index id
							pst_preStat.setString(2, ind_date);
							//Logging.getDebug("pst_preStat is "+pst_preStat);
							rst2 = pst_preStat.executeQuery();//execute query
							Logging.debug("rst2.next() is " + rst2.next());
							//					if no enteries
							Logging.debug("rst2.getRow()" + rst2.getRow());
							if (rst2.getRow() == 0) {
								Logging.debug("no entries if zero");
								CapturedIndexForm.insert_in_IndValue(cap_index);
								buffer.append("<tr><td>");
								buffer.append(key);
								buffer
										.append("</td><td><font color='blue'>Captured Index Values Inserted succesfully.</font></td></tr>");
							} else {

								try {
									double low = rst2
											.getDouble("index_lowest_value");
									double high = rst2
											.getDouble("index_highest_value");
									double indexVal = (double) Double
											.parseDouble(cap_index
													.getIndex_value());
									//Logging.getDebug("In else high low");
									//Logging.getDebug(" high "+high+" low
									// "+low+" indexVal "+indexVal);
									if (indexVal > high) {
										//update_high_index_value

										pst_preStat = connection
												.prepareStatement(ConnectInit.queries
														.getProperty("capIndex_update_high_index_value"));
										pst_preStat.setDouble(1, indexVal);
										pst_preStat.setDouble(2, indexVal);
										pst_preStat.setString(3, index_id);
										pst_preStat.setString(4, ind_date);
										pst_preStat.executeUpdate();
										buffer.append("<tr><td>");
										buffer.append(key);
										buffer
												.append("</td><td><font color='blue'>Captured Index High Value updated succesfully.</font></td></tr>");
									} else {
										if (indexVal < low) {
											//update_low_index_value
	
											pst_preStat = connection
													.prepareStatement(ConnectInit.queries
															.getProperty("capIndex_update_low_index_value"));
											pst_preStat.setDouble(1, indexVal);
											pst_preStat.setDouble(2, indexVal);
											pst_preStat.setString(3, index_id);
											pst_preStat.setString(4, ind_date);
											pst_preStat.executeUpdate();
											buffer.append("<tr><td>");
											buffer.append(key);
											buffer
													.append("</td><td><font color='blue'>Captured Index Low Value updated succesfully.</font></td></tr>");
										} else {
											pst_preStat = connection
											.prepareStatement(ConnectInit.queries
													.getProperty("capIndex_update_close_index_value"));
											pst_preStat.setDouble(1, indexVal);
											pst_preStat.setString(2, index_id);
											pst_preStat.setString(3, ind_date);
											pst_preStat.executeUpdate();
											buffer.append("<tr><td>");
											buffer.append(key);
											buffer
													.append("</td><td><font color='blue'>Captured Index Close Value updated succesfully.</font></td></tr>");
										}
									}
									/*
									 * if(!(indexVal > high)&& !(indexVal <
									 * low)){ buffer.append(" <tr><td> ");
									 * buffer.append(key); buffer.append(" </td>
									 * <td> <font color='blue'>Captured Index
									 * tmcv and Divisor Value updated
									 * succesfully. </font> </td></tr> "); }
									 */
								} catch (SQLException ae) {
									Logging.error("ERROR: "
											+ ae.getMessage());
								}
							}
							//}
						} catch (SQLException ae) {
							Logging.error("ERROR: " + ae.getMessage());
						}
						/*
						 * buffer.append(" <tr><td> "); buffer.append(key);
						 * buffer.append(" </td><td> <font color='blue'>
						 * <b>Captured Index updated succesfully. </b> </font>
						 * </td></tr> ");
						 */
					}
				} catch (Exception ex) {
					Logging.error(" Error : "
							+ ex.getMessage());
				}
			}
			//return buffer;

			table.clear();

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
 * method returns a hashtable containg index name for 
 * index token specified . Standard index tokens are provided by NSE for 
 * specific captured indices (NSE index). 
 * @return hashtable conatining index name for index token.
 */
	public Hashtable getIndexName() {
		Hashtable indName_list = new Hashtable();
		indName_list.put("0", "S&P CNX NIFTY");
		indName_list.put("1", "CNX IT");
		indName_list.put("2", "CNX NIFTY JUNIOR");
		indName_list.put("3", "S&P CNX DEFTY");
		indName_list.put("4", "BANK NIFTY");
		indName_list.put("5", "CNX MIDCAP 200");
		indName_list.put("6", "S&P CNX 500");
		indName_list.put("7", "S&P CNX NIFTY TEST");
		return indName_list;
	}
	/**
	 * method to format date in "dd-MM-yyyy" format 
	 * return current date in formated metioned above if date string
	 * passed is null otherwise format the date string in 
	 * above metioned format
	 * @param str
	 * @return formated date String
	 */
	/*public String formatDate(String str)
	{
		SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
		String date=null;
		if(str==null){
			Date d = new Date();
			date=fr.format(d).toString();
		}else{
			Logging.getDebug("before date "+str);
			java.util.Date d = new java.util.Date(str.trim());
			str=fr.format(d).toString();
			Logging.getDebug("After Simpledate"+str);
			return str;
		}
		return date;			
	}*/
}