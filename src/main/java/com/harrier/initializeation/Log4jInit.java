package com.harrier.initializeation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import app.UpdateSeriesReadFile;



public class Log4jInit extends HttpServlet {
	Logger Logging = Logger.getLogger(Log4jInit.class);
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		String logfile = getServletConfig().getInitParameter(
				"log4jPropertiesFile");
		Logging.debug(logfile);
		if (logfile != null) {
			logfile = getServletContext().getRealPath(logfile);
			Logging.debug(logfile);
			PropertyConfigurator.configure(logfile);
		}
		Logging.debug("log4j initializes...");
	}

}
