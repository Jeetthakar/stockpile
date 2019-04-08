package com.harrier.initializeation;

import harrier.income.com.compute.CIndexCalculator;
import harrier.income.com.entities.CFormula;
import harrier.income.com.report.GraphMethodsPf;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.jfree.chart.demo.servlet.AdjustDecimal;
import org.jfree.chart.demo.servlet.ComposeIndex;

import app.AcessControl;
import app.Connect;

public class ConnectInit extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static Logger Logging = Logger.getLogger(ConnectInit.class);
	private static Connect connect;
	private static CFormula cFormula;
	private static CIndexCalculator cIndexCalculator;
	private static AdjustDecimal adjustDecimal;
	private static AcessControl acessControl;
	private static GraphMethodsPf graphMethodsPf;
	public static Properties queries, dbprops, userqueryprops;
	private static ComposeIndex composeIndex;

	public void init() throws ServletException {
		Logging.debug("connectInit is initialised Pranay...");
		connect = new Connect();
		cFormula = new CFormula();
		cIndexCalculator = new CIndexCalculator();
		adjustDecimal = new AdjustDecimal();
		acessControl = new AcessControl();
		graphMethodsPf = new GraphMethodsPf();
		composeIndex = new ComposeIndex();
		// Logging.getTrace(connect+ "ConnectInit is Initialized & got the
		// connect...");
		queries = new Properties();
		/*
		 * dbprops = new Properties(); userqueryprops = new Properties();
		 */
		try {
			// System.out.println("inside try...");

			queries.load(ConnectInit.class.getClassLoader()
					.getResourceAsStream("resources/query.properties"));
			/*
			 * dbprops.load(ConnectInit.class.getClassLoader()
			 * .getResourceAsStream("resources/database.properties"));
			 * userqueryprops.load(ConnectInit.class.getClassLoader()
			 * .getResourceAsStream("resources/user_query.properties"));
			 */
			Logging.debug("Properties File loaded ...");
			// Logging.debug(queries.getProperty("query_copy_indexmaster"));
		} catch (IOException e) {
			Logging.debug("Error in ConnectInit while loading Properties File: "
					+ e);
			// e.printStackTrace();
			Logging.debug(e);
		}
	}

	public static Connect getConnect() {

		// Logging.getTrace(connect+ "for Pranay1 in ConnectInit got by
		// getConnect()...");
		// if (connect == null) {
		connect = new Connect();
		// }
		Logging.info(connect);
		System.out.println(" ****  ConnectInit **** "+connect);
		return connect;
	}

	public static CFormula getCFormula() {
		// if (cFormula == null) {
		cFormula = new CFormula();
		// }
		Logging.info(cFormula);
		return cFormula;

	}

	public static CIndexCalculator getCIndexCalculator() {
		// if (cIndexCalculator == null) {
		cIndexCalculator = new CIndexCalculator();
		// }
		Logging.info(cIndexCalculator);
		return cIndexCalculator;

	}

	public static AdjustDecimal getAdjustDecimal() {
		// if (adjustDecimal == null) {
		adjustDecimal = new AdjustDecimal();
		// }
		Logging.info(adjustDecimal);
		return adjustDecimal;
	}

	public static AcessControl getAcessControl() {
		// if (acessControl == null) {
		acessControl = new AcessControl();
		// }
		Logging.info(acessControl);
		return acessControl;
	}

	public static GraphMethodsPf getGraphMethodsPf() {
		// if (graphMethodsPf == null) {
		graphMethodsPf = new GraphMethodsPf();
		// }
		Logging.info(graphMethodsPf);
		return graphMethodsPf;
	}

	public static ComposeIndex getComposeIndex() {
		// if (composeIndex == null) {
		composeIndex = new ComposeIndex();
		// }
		Logging.info(composeIndex);
		return composeIndex;
	}

}
