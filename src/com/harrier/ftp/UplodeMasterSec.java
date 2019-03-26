/*
 * Created on Aug 31, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.harrier.ftp;

/**
 * @author ashishi
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

import app.Connect;

public class UplodeMasterSec {
	Logger Logging = Logger.getLogger(UplodeMasterSec.class);
	/**
	 * method to upload the latest Security.dat file from FTP server also return
	 * the filename of the latest Security.dat file uploaded at destination
	 * folder (ftpDetails.properties - "mkt_ind_destination").
	 * 
	 * @return filename
	 */
	public String upload() {

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
			source = rs.getProperty("security_source");
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

			filename = "Securities.dat";

			for (int a = 0; a < files.length; a++) {
				String str = files[a].getName();//File name
				Logging.debug("File name is " + str);
				if (str.equals(filename)) {
					//long len=files[a].getSize();// Gives the files Size in
					// bytes
					//Logging.debug("Size of file: " + len + "Bytes");
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
					boolean extension12 = filename.endsWith("dat");
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
		Logging.debug("before returning the value the name of file "
				+ filename);
		return filename;
	}

}