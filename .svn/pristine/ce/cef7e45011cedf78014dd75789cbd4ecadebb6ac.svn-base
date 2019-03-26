/*
 * Created on Sep 2, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.harrier.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

import app.Connect;

/**
 * @author sunil
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UploadSec {
	Logger Logging = Logger.getLogger(UploadSec.class);
	/**
	 * method to upload the latest .sec file from FTP server on current date and
	 * also return the filename of the latest .sec file uploaded at destination
	 * folder (ftpDetails.properties - "mkt_ind_destination").
	 * 
	 * @return filename
	 */
	public String getLatestSecFile() {

		FTPClient ftp = new FTPClient();
		SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
		Date dt = new Date();

		String date = fr.format(dt).toString();
		date = date.replaceAll("-", "");
		int mon = (int) Integer.parseInt(date.substring(2, 4));
		String day = date.substring(0, 2);
		String year = date.substring(4, 8);
		Logging.debug("date is " + date);
		String month = null;
		switch (mon) {
		case 1:
			month = "January";
			break;
		case 2:
			month = "February";
			break;
		case 3:
			month = "March";
			break;
		case 4:
			month = "April";
			break;
		case 5:
			month = "May";
			break;
		case 6:
			month = "June";
			break;
		case 7:
			month = "July";
			break;
		case 8:
			month = "August";
			break;
		case 9:
			month = "September";
			break;
		case 10:
			month = "October";
			break;
		case 11:
			month = "November";
			break;
		case 12:
			month = "December";
			break;
		}

		/*
		 * if(month.equals("08")) { month="August"; }else
		 * if(month.equals("09")){ month="Sept"; }
		 */
		String date1 = month + day + year;
		String source = null;
		String destination = null;
		String server = null;
		String username = null;
		String password = null;
		String rightnow = null;
		String filename = null;
		try {
			Properties rs = new Properties();
			rs.load(new FileInputStream(Connect.resourceurl
					+ "resources/ftpDetails.properties"));
			source = rs.getProperty("security_source");
			source = source + date1 + "/";
			destination = rs.getProperty("mkt_ind_destination");
			server = rs.getProperty("server");
			username = rs.getProperty("username");
			password = rs.getProperty("password");
			ftp.connect(server);
			ftp.login(username, password);//Login using Username and Password
			Logging.debug("path is" + source);
			Logging.debug("befr ftp1");
			ftp.changeWorkingDirectory(source);//change to the directory
			// contaning files
			Logging.debug("after change working directory");
			String name = ftp.getSystemName();
			Logging.debug("name is " + name);
			Logging.debug("after ftp1");
			FTPFile[] files = ftp.listFiles();// List the files in the directory
			Logging.debug("Number of files in dir: " + files.length);
			DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
			File directory = new File(destination);
			Logging.debug("file lenght " + files.length);
			ArrayList ar = new ArrayList();
			int k = 0;

			for (int i = 0; i < files.length; i++) {

				String str = files[i].getName();//File name
				Logging.debug("File name is " + str);

				String temp1 = "";
				temp1 = str.substring(str.lastIndexOf(".") + 1);
				Logging.debug("Extension of file " + temp1);
				if (temp1.equals("sec")) {
					int location = str.lastIndexOf(".");
					Logging.debug("the location off . " + location);
					String first = str.substring(0, location);
					int c = Integer.parseInt(first.trim());
					Logging.debug("first carecter of file " + c);
					ar.add(k, first);
				}
			}

			int[] arr = new int[ar.size()];

			for (int m = 0; m < ar.size(); m++) {
				arr[m] = (int) Integer.parseInt((String) ar.get(m));
			}

			int len = ar.size();
			Logging.debug("array length " + len);
			Arrays.sort(arr);
			Logging.debug("array length after sorting" + arr.length);
			int last = arr[(ar.size() - 1)];
			Logging.debug("last value from the array" + last);
			String la = new Integer(last).toString();
			String filename1 = la + "." + "sec";
			Logging.debug("name of file" + filename1);

			for (int a = 0; a < files.length; a++) {
				String str = files[a].getName();//File name
				//Logging.getDebug("File name is " + str);
				if (str.equals(filename1)) {
					Logging.debug("Size of file: " + len + "Bytes");
					File file = new File(destination + File.separator
							+ files[a].getName());
					FileOutputStream fos = new FileOutputStream(file);
					Date fileDate = files[a].getTimestamp().getTime();
					filename = files[a].getName();
					ftp.retrieveFile(files[a].getName(), fos);//writes file to
					// the file o/p
					// stream
					rightnow = "" + new Date();
					rightnow = rightnow.substring(11, 19);
					Logging.debug("System Time is " + rightnow);
					fos.close();
					file.setLastModified(fileDate.getTime());
					boolean extension12 = filename.endsWith("ind");
				}
			}
		} catch (IOException ioe) {
			Logging.error(" Error : " + ioe.getMessage());
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ee) {
					Logging.error(" Error : " + ee.getMessage());
				}
			}

		}
		Logging.debug(" filename is " + filename);
		return filename;
	}
}
