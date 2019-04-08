/*
 * Created on Sep 5, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.harrier.ftp;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
public class MessageReadFile {
	Logger Logging = Logger.getLogger(MessageReadFile.class);
	public Hashtable table = new Hashtable();	
	MessageBean msgBean=null;

	public StringBuffer msgdisplay(String exchange_id, String fileName) {
		StringBuffer buffer = new StringBuffer();
		int j = 1;
		short trans_c = 0;//tranc code (6 for message file)
		int time_stamp = 0; // Time Stamp
		short message_l = 0; //Message length
		String message = "", style = ""; // message
		try {
			buffer.append("<tr width = '100%'>");
			style = "gridStyle-header color='blue' ";
			buffer.append("<td width='9%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("Time Stamp");//Security Token.
			buffer.append("</td>");
			buffer.append("<td width='9%' align='center' class='" + style
					+ "' valign='center' height='12'>");
			buffer.append("Message");//Latest Traded price.
			buffer.append("</td>");
			buffer.append("</tr>");
			buffer.append("<tr>");
			Properties rs = new Properties();
			rs.load(new FileInputStream(Connect.resourceurl
					+ "resources/ftpDetails.properties"));
			String destination = rs.getProperty("mkt_ind_destination");
			File TransFileIn = new File(destination + fileName);
			FileInputStream File_In = new FileInputStream(TransFileIn);
			DataInputStream Data_In = new DataInputStream(File_In);
			while (Data_In.available() != 0) {
				 msgBean=new MessageBean();
				trans_c = 0;time_stamp = 0;message_l = 0;message = "";

				trans_c = Short.reverseBytes((short) Data_In.readShort());
				msgBean.setTrans_c(trans_c);
				Logging.debug(" Transc is " + trans_c);
				time_stamp = Integer.reverseBytes(Data_In.readInt());
				Logging.debug(" Time_stamp is " + time_stamp);
				long ddt = Math.abs(time_stamp);
				ddt = ddt * 1000;
				Logging.debug(" ddt is " + ddt);
				/*Date dd = new Date(ddt);
				Logging.getDebug(" date is " + dd);*/
				msgBean.setTime_stamp(ddt);

				message_l = Short.reverseBytes((short) Data_In.readShort());
				msgBean.setMessage_l(message_l);
				Logging.debug("message length " + message_l);

				if (trans_c == 6) {
					int mlength=message_l-1;
					StringBuffer sb=new StringBuffer();
					if(mlength>239)
						mlength=239;
					for (int i = 0; i < 239; i++) {
						//message=message+(char) Data_In.readByte();
						char ch=(char) Data_In.readByte();
						//if(Character.isLetterOrDigit(ch) || ch=='.' || ch==';' || ch=='(' || ch==')' || ch==' ')
						if(i<=mlength && ch>=32 && ch<=126)
							sb.append(ch);
						//sb.append((char) Data_In.readByte());
						//System.out.print( Message);
						//System.out.flush();
					}
					message=sb.toString();
					sb=null;
					//message=message.substring(0,(message.indexOf(".")+1));
					msgBean.setMessage(message);
					buffer.append("<td>");
					buffer.append(new Timestamp(ddt));
					buffer.append("</td>");
					buffer.append("<td>");
					buffer.append(message);
					buffer.append("</td>");
					
					table.put(message,msgBean);
					//Logging.getDebug(" data available after 4th
					// "+Data_In.available());
					//stmt.setString(4,Message);
				}else{
					
				}
				// stmt.executeUpdate();

				Logging.debug("after query");
				Logging.debug("Trans code =" + trans_c + ","
						+ "Time Stamp =" + time_stamp + ","
						+ "Message lenght =" + message_l + "," + " Message="
						+ message + "");
				//break;
				
			}
			buffer.append("</tr>");

		} catch (Exception ee) {
			Logging.error(" Error : " + ee.getMessage());
		}
		return buffer;
	}
	/**
	 * method to save the details for messages contained in the latest
	 * .dat message file uploaded from FTP server.
	 * 
	 * @return StringBuffer
	 */
	public StringBuffer storeFtpMessageFileDetails() {
		StringBuffer buffer = new StringBuffer();
		int counter1 = 0;
		Connect connect = ConnectInit.getConnect();
		Connection connection = null;
		boolean flag=false;
		try {
			String str = "";
			int i;
			try {
				if (connection == null)
					connection = connect.getConnectionForTransaction();
			} catch (Exception e) {
				Logging.error(" Error : " + e.getMessage());
			}
			PreparedStatement pst=null;
			ResultSet result = null;
			Enumeration e = table.keys();
			String key = "";
			int counter=0;
			Logging.debug("size of hashtable......" + table.size());
			for (e = table.keys(); e.hasMoreElements();) {
				counter1++;counter++;
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
				key = (String) e.nextElement();
				msgBean = (MessageBean) table.get(key);
				try {
					pst=null;
					String msg=msgBean.getMessage().trim();
					if(msgBean.getMessage().indexOf(".")>msgBean.getMessage_l()) {
						msg=msgBean.getMessage().trim().substring(0,(msgBean.getMessage().trim().indexOf(".")+1));
					}
					pst=connection.prepareStatement(ConnectInit.queries.getProperty("ftpMessage_check_exist"));
					pst.setString(1,msg);
					pst.setTimestamp(2,new Timestamp(msgBean.getTime_stamp()));
					ResultSet rs=pst.executeQuery();
					while(rs.next()) {
						flag=true;
						break;
					}
					if(flag==false) {
						pst=null;
						pst=connection.prepareStatement(ConnectInit.queries.getProperty("ftpMessage_file_insert"));
						Logging.debug(" msgBean.getTrans_c() is "+msgBean.getTrans_c());
						pst.setShort(1,msgBean.getTrans_c());
						Logging.debug(" new Timestamp(msgBean.getTime_stamp()) is "+new Timestamp(msgBean.getTime_stamp()));
						pst.setTimestamp(2,new Timestamp(msgBean.getTime_stamp()));
						Logging.debug(" msgBean.getMessage_l() is "+msgBean.getMessage_l());
						pst.setShort(3,msgBean.getMessage_l());
						Logging.debug(" msgBean.getMessage() is "+msgBean.getMessage());
						
						pst.setString(4,msg);
						pst.executeUpdate();
						pst.close();
						buffer.append("<tr>");
						buffer.append("<td>");
						buffer.append(msgBean.getMessage());
						buffer.append("</td>");
						buffer.append("<td>");
						buffer.append("Message Inserted Successfully .");
						buffer.append("</td>");
						buffer.append("</tr>");
					} else {
						buffer.append("<tr>");
						buffer.append("<td>");
						buffer.append(msgBean.getMessage());
						buffer.append("</td>");
						buffer.append("<td>");
						buffer.append("Message Already Exist .");
						buffer.append("</td>");
						buffer.append("</tr>");
					}
				} catch (Exception ee) {
					Logging.error(" Error : "+ee.getMessage());
				}
			}
		} catch (Exception e) {
			Logging.error(" Error : "+e.getMessage());
		}
		finally {
			try {
				if(connection!=null)
					connection.close();
			} catch (Exception ex) {
				Logging.error(" Error : Unable to close Connection "+ex.getMessage());
			}
		}
		return buffer;
	}

}