package com.harrier.ftp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

import app.*;

/**
 * @author ashishi
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */

public class UplodeBhav {
	Logger Logging = Logger.getLogger(UplodeBhav.class);

	public String storeStockbhv() {

		FTPClient ftp = new FTPClient();

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
			source = rs.getProperty("bhav_source");
			destination = rs.getProperty("bhav_destination");
			server = rs.getProperty("server");
			username = rs.getProperty("username");
			password = rs.getProperty("password");
			ftp.connect(server);
			ftp.login(username, password);// Login using Username and Password
			Logging.debug("path is" + source);
			Logging.debug("befr ftp1");
			ftp.changeWorkingDirectory(source);// change to the directory
												// contaning files
			Logging.debug("after change working directory");
			String name = ftp.getSystemName();
			Logging.debug("name is " + name);
			Logging.debug("after ftp1");
			FTPFile[] files = ftp.listFiles();// List the files in the directory
			Logging.debug("Number of files in dir: " + files.length);
			DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);

			int l = files.length;
			int temp = 0;
			String div[] = new String[3];
			int position = 0;
			int value = 0;
			SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
			Date dt = new Date();
			String date = fr.format(dt).toString();
			date = date.replaceAll("-", "");
			Logging.debug("date is " + date);
			String filename2 = "CMBhavcopy_" + date + ".txt";
			String filename1 = "cmBhavcopy_" + date + ".txt";
			File directory = new File(destination);
			Logging.debug("file lenght " + files.length);
			for (int i = 0; i < files.length; i++) {
				String str = files[i].getName();// File name
				Logging.debug("File name is " + str);
				if (str.equals(filename2) || str.equals(filename1)) {
					filename = str;
					long len = files[i].getSize();// Gives the files Size in
													// bytes
					Logging.debug("Size of file: " + len + "Bytes");
					File file = new File(destination + File.separator
							+ files[i].getName());
					FileOutputStream fos = new FileOutputStream(file);
					Date fileDate = files[i].getTimestamp().getTime();
					filename = files[i].getName();
					ftp.retrieveFile(files[i].getName(), fos);// writes file to
																// the file o/p
																// stream
					rightnow = "" + new Date();
					rightnow = rightnow.substring(11, 19);
					Logging.debug("System Time is " + rightnow);
					fos.close();
					file.setLastModified(fileDate.getTime());
					boolean extension12 = filename.endsWith("txt");
				}
			}

		} catch (Exception e) {
			Logging.debug(" Error : " + e.getMessage());
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ee) {
					Logging.error(" Error : " + ee.getMessage());
				}
			}

		}
		return filename;
	}
}
