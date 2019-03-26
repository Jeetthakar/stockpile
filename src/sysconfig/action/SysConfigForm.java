/*
 * Created on Jan 28, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package sysconfig.action;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.validator.ValidatorForm;

import com.harrier.initializeation.ConnectInit;

/**
 * @author manoj
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class SysConfigForm extends ValidatorForm implements Serializable {
	Logger Logging = Logger.getLogger(SysConfigForm.class);
	private String operation = null;
	private int configurationId = 0;
	private int computationInterval = 0;
	private int monitorRefreshRate = 0;
	private int precisionValue = 0;
	private int rateOfPriceFeed = 0;
	private String customerName = null;
	private String nameLogoVerticalAlign = null;
	private String nameLogoHorizontalAlign = null;
	private int maxNoOfCompanies = 0;
	private float alertPercentage = 0;
	private float rejectionPercentage = 0;
	private int intraDayArchivalInterval = 0;
	private String industryClassificationId = null;
	private String timeZoneId = null;
	private String customerLogoPath = null;
	private String dateFormat = null;
	private String date = null;
	private String month = null;
	private String year = null;
	private char validator;
	private int marketLot = 0;
	private String stockExId = null;
	private String currencyId = null;
	private String countryId = null;
	private String s_indexType = null;
	private String s_stockType = null;
	private int dateDifference = 0;
	private String adjustStockPrice = null;
	private float percentage_change_in_share = 0;
	private Collection industryClassificationCollection = null;
	private Collection timeZoneCollection = null;
	private Collection stockExCollection = null;
	private Collection currencyIdCollection = null;
	private Collection countryIdCollection = null;
	private Collection s_indexTypeIndexCollection = null;
	private Collection s_stockTypeCollection = null;
	private String resetButton = null;
	private String saveButton = null;
	private String language = null, index_id = null;
	private Collection languageCollection = null, indexUpdateCollection = null;
	private float faceValue = 0;
	private float paidValue = 0;
	private int flag = 0;
	private int chk;
	private String query, check_flag;
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getChk() {
		return chk;
	}

	public void setChk(int chk) {
		this.chk = chk;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getCheck_flag() {
		return check_flag;
	}

	public void setCheck_flag(String checkFlag) {
		check_flag = checkFlag;
	}

	public ResultSet getRst() {
		return rst;
	}

	public void setRst(ResultSet rst) {
		this.rst = rst;
	}

	public app.Connect getCon() {
		return con;
	}

	public void setCon(app.Connect con) {
		this.con = ConnectInit.getConnect();
	}

	ResultSet rst;
	app.Connect con = ConnectInit.getConnect();

	public SysConfigForm() {
		Logging.debug("inside constructor");
	}

	/** RESEST ALL FORM FIELDS **/
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		Connection connection = null;

		try {
			String reset = request.getParameter("resetButton");
			String save = request.getParameter("saveButton");

			if (reset == null && save == null) {

				if (connection == null)
					connection = con.getdbConnection();
				PreparedStatement stmt = connection
						.prepareStatement(ConnectInit.queries
								.getProperty("sysconfig_select_*_from_systemConfiguration"));
				rst = stmt.executeQuery();
				while (rst.next()) {
					computationInterval = rst.getInt(2);
					monitorRefreshRate = rst.getInt(3);
					precisionValue = rst.getInt(4);
					rateOfPriceFeed = rst.getInt(5);
					customerName = rst.getString(6);
					nameLogoVerticalAlign = rst.getString(7);
					nameLogoHorizontalAlign = rst.getString(8);
					maxNoOfCompanies = rst.getInt(9);
					alertPercentage = rst.getFloat(10);
					rejectionPercentage = rst.getFloat(11);
					intraDayArchivalInterval = rst.getInt(12);
					industryClassificationId = "value" + rst.getInt(13);
					timeZoneId = "value" + rst.getInt(14);
					customerLogoPath = rst.getString(15);
					dateFormat = rst.getString(16);
					if (dateFormat.charAt(1) == '-') {
						validator = dateFormat.charAt(1);
					} else if (dateFormat.charAt(2) == '-') {
						validator = dateFormat.charAt(2);
					} else if (dateFormat.charAt(4) == '-') {
						validator = dateFormat.charAt(4);
					} else {
						validator = '/';
					}
					StringTokenizer token = new StringTokenizer(dateFormat, ""
							+ validator + "");
					ArrayList list = new ArrayList();
					while (token.hasMoreElements()) {
						list.add(token.nextElement());

					}
					date = (String) list.get(0);
					month = (String) list.get(1);
					year = (String) list.get(2);
					marketLot = rst.getInt(17);
					stockExId = "value" + rst.getInt(18);
					currencyId = "value" + rst.getInt(19);
					countryId = "value" + rst.getInt(20);

					dateDifference = rst.getInt(21);
					adjustStockPrice = rst.getString(22);
					percentage_change_in_share = rst.getFloat(23);
					language = "value" + rst.getInt(24);

					if (rst.getInt(25) == 0) {
						index_id = "value0";
					} else
						index_id = "value" + rst.getInt(25);

					faceValue = rst.getFloat(26);
					paidValue = rst.getFloat(27);

					s_indexType = "value" + rst.getInt(28);
					s_stockType = "value" + rst.getInt(29);
					Logging.debug("s_indextpype in foerm is=" + s_indexType);
					Logging.debug("s_stocktype in foerm is=" + s_stockType);
				}
				rst.close();
				stmt.close();
			}

		} catch (SQLException err) {
			Logging.debug(" Error : " + err.getMessage());
			//err.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ex) {
				Logging.error(" Error : Unable to close Connection "
						+ ex.getMessage());
			}
		}
	}

	/** RESEST ALL FORM FIELDS **/
	public void reset() {
		Connection connection = null;

		try {

			if (connection == null)
				connection = con.getdbConnection();
			PreparedStatement stmt = connection
					.prepareStatement(ConnectInit.queries
							.getProperty("sysconfig_select_*_from_systemConfiguration"));
			rst = stmt.executeQuery();
			while (rst.next()) {
				computationInterval = rst.getInt(2);
				monitorRefreshRate = rst.getInt(3);
				precisionValue = rst.getInt(4);
				rateOfPriceFeed = rst.getInt(5);
				customerName = rst.getString(6);
				nameLogoVerticalAlign = rst.getString(7);
				nameLogoHorizontalAlign = rst.getString(8);
				maxNoOfCompanies = rst.getInt(9);
				alertPercentage = rst.getFloat(10);
				rejectionPercentage = rst.getFloat(11);
				intraDayArchivalInterval = rst.getInt(12);
				industryClassificationId = "value" + rst.getInt(13);
				timeZoneId = "value" + rst.getInt(14);
				customerLogoPath = rst.getString(15);
				dateFormat = rst.getString(16);
				if (dateFormat.charAt(1) == '-') {
					validator = dateFormat.charAt(1);
				} else if (dateFormat.charAt(2) == '-') {
					validator = dateFormat.charAt(2);
				} else if (dateFormat.charAt(4) == '-') {
					validator = dateFormat.charAt(4);
				} else {
					validator = '/';
				}
				StringTokenizer token = new StringTokenizer(dateFormat, ""
						+ validator + "");
				ArrayList list = new ArrayList();
				while (token.hasMoreElements()) {
					list.add(token.nextElement());

				}
				date = (String) list.get(0);
				month = (String) list.get(1);
				year = (String) list.get(2);
				marketLot = rst.getInt(17);
				stockExId = "value" + rst.getInt(18);
				currencyId = "value" + rst.getInt(19);
				countryId = "value" + rst.getInt(20);

				dateDifference = rst.getInt(21);
				adjustStockPrice = rst.getString(22);
				percentage_change_in_share = rst.getFloat(23);
				language = "value" + rst.getInt(24);
				if (rst.getInt(25) == 0) {
					index_id = "value0";
				} else
					index_id = "value" + rst.getInt(25);

				faceValue = rst.getFloat(26);
				paidValue = rst.getFloat(27);
				s_indexType = "value" + rst.getInt(28);
				s_stockType = "value" + rst.getInt(29);
				Logging.debug("s_indextpype in foerm is=" + s_indexType);
				Logging.debug("s_stocktype in foerm is=" + s_stockType);
			}
			rst.close();
			stmt.close();
		} catch (SQLException err) {
			Logging.debug(" Error : " + err.getMessage());
			//err.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ex) {
				Logging.error(" Error : Unable to close Connection "
						+ ex.getMessage());
			}
		}
	}

	/**
	 * VALIDATE FORM DATA
	 * **/
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();

		return errors;

	}

	/**
	 * @return Returns the date.
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            The date to set.
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return Returns the operation.
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * @param operation
	 *            The operation to set.
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * @return Returns the alertPercentage.
	 */
	public float getAlertPercentage() {
		return alertPercentage;
	}

	/**
	 * @param alertPercentage
	 *            The alertPercentage to set.
	 */
	public void setAlertPercentage(float alertPercentage) {
		this.alertPercentage = alertPercentage;
	}

	/**
	 * @return Returns the computationInterval.
	 */
	public int getComputationInterval() {
		return computationInterval;
	}

	/**
	 * @param computationInterval
	 *            The computationInterval to set.
	 */
	public void setComputationInterval(int computationInterval) {
		this.computationInterval = computationInterval;
	}

	/**
	 * @return Returns the configurationId.
	 */
	public int getConfigurationId() {
		return configurationId;
	}

	/**
	 * @param configurationId
	 *            The configurationId to set.
	 */
	public void setConfigurationId(int configurationId) {
		this.configurationId = configurationId;
	}

	/**
	 * @return Returns the countryId.
	 */
	public String getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId
	 *            The countryId to set.
	 */
	public void setCountryId(String coId) {
		this.countryId = coId;
	}

	/**
	 * @return Returns the s_indexType.
	 */
	public String getS_indexType() {
		return s_indexType;
	}

	/**
	 * @param s_indexType
	 *            The s_indexType to set.
	 */
	public void setS_indexType(String s_indexType) {
		this.s_indexType = s_indexType;
	}

	/**
	 * @return Returns the s_stockType.
	 */
	public String getS_stockType() {
		return s_stockType;
	}

	/**
	 * @param s_stockType
	 *            The s_stockType to set.
	 */
	public void setS_stockType(String s_stockType) {
		this.s_stockType = s_stockType;
	}

	/**
	 * @return Returns the currencyId.
	 */
	public String getCurrencyId() {
		return currencyId;
	}

	/**
	 * @param currencyId
	 *            The currencyId to set.
	 */
	public void setCurrencyId(String cuId) {
		this.currencyId = cuId;
	}

	/**
	 * @return Returns the customerLogoPath.
	 */
	public String getCustomerLogoPath() {
		return customerLogoPath;
	}

	/**
	 * @param customerLogoPath
	 *            The customerLogoPath to set.
	 */
	public void setCustomerLogoPath(String customerLogoPath) {
		this.customerLogoPath = customerLogoPath;
	}

	/**
	 * @return Returns the month.
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month
	 *            The month to set.
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * @return Returns the year.
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year
	 *            The year to set.
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return Returns the validator.
	 */
	public char getValidator() {
		return validator;
	}

	/**
	 * @param validator
	 *            The validator to set.
	 */
	public void setValidator(char validator) {
		this.validator = validator;
	}

	/**
	 * @return Returns the customerName.
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName
	 *            The customerName to set.
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return Returns the dateDifference.
	 */
	public int getDateDifference() {
		return dateDifference;
	}

	/**
	 * @param dateDifference
	 *            The dateDifference to set.
	 */
	public void setDateDifference(int dateDifference) {
		this.dateDifference = dateDifference;
	}

	/**
	 * @return Returns the dateFormat.
	 */
	public String getDateFormat() {
		return dateFormat;
	}

	/**
	 * @param dateFormat
	 *            The dateFormat to set.
	 */
	public void setDateFormat(String dateFormat) {
		// this.dateFormat = dateFormat;
	}

	/**
	 * @return Returns the industryClassificationId.
	 */
	public String getIndustryClassificationId() {
		return industryClassificationId;
	}

	/**
	 * @param industryClassificationId
	 *            The industryClassificationId to set.
	 */

	public void setIndustryClassificationId(String industryClassId) {
		this.industryClassificationId = industryClassId;
	}

	/**
	 * @return Returns the intraDayArchivalInterval.
	 */
	public int getIntraDayArchivalInterval() {
		return intraDayArchivalInterval;
	}

	/**
	 * @param intraDayArchivalInterval
	 *            The intraDayArchivalInterval to set.
	 */
	public void setIntraDayArchivalInterval(int intraDayArchivalInterval) {
		this.intraDayArchivalInterval = intraDayArchivalInterval;
	}

	/**
	 * @return Returns the marketLot.
	 */
	public int getMarketLot() {
		return marketLot;
	}

	/**
	 * @param marketLot
	 *            The marketLot to set.
	 */
	public void setMarketLot(int marketLot) {
		this.marketLot = marketLot;
	}

	/**
	 * @return Returns the maxNoOfCompanies.
	 */
	public int getMaxNoOfCompanies() {
		return maxNoOfCompanies;
	}

	/**
	 * @param maxNoOfCompanies
	 *            The maxNoOfCompanies to set.
	 */
	public void setMaxNoOfCompanies(int maxNoOfCompanies) {
		this.maxNoOfCompanies = maxNoOfCompanies;
	}

	/**
	 * @return Returns the monitorRefreshRate.
	 */
	public int getMonitorRefreshRate() {
		return monitorRefreshRate;
	}

	/**
	 * @param monitorRefreshRate
	 *            The monitorRefreshRate to set.
	 */
	public void setMonitorRefreshRate(int monitorRefreshRate) {
		this.monitorRefreshRate = monitorRefreshRate;
	}

	/**
	 * @return Returns the nameLogoHorizontalAlign.
	 */
	public String getNameLogoHorizontalAlign() {
		return nameLogoHorizontalAlign;
	}

	/**
	 * @param nameLogoHorizontalAlign
	 *            The nameLogoHorizontalAlign to set.
	 */
	public void setNameLogoHorizontalAlign(String nameLogoHorizontalAlign) {
		this.nameLogoHorizontalAlign = nameLogoHorizontalAlign;
	}

	/**
	 * @return Returns the nameLogoVerticalAlign.
	 */
	public String getNameLogoVerticalAlign() {
		return nameLogoVerticalAlign;
	}

	/**
	 * @param nameLogoVerticalAlign
	 *            The nameLogoVerticalAlign to set.
	 */
	public void setNameLogoVerticalAlign(String nameLogoVerticalAlign) {
		this.nameLogoVerticalAlign = nameLogoVerticalAlign;
	}

	/**
	 * @return Returns the precisionValue.
	 */
	public int getPrecisionValue() {
		return precisionValue;
	}

	/**
	 * @param precisionValue
	 *            The precisionValue to set.
	 */
	public void setPrecisionValue(int precisionValue) {
		this.precisionValue = precisionValue;
	}

	/**
	 * @return Returns the rateOfPriceFeed.
	 */
	public int getRateOfPriceFeed() {
		return rateOfPriceFeed;
	}

	/**
	 * @param rateOfPriceFeed
	 *            The rateOfPriceFeed to set.
	 */
	public void setRateOfPriceFeed(int rateOfPriceFeed) {
		this.rateOfPriceFeed = rateOfPriceFeed;
	}

	/**
	 * @return Returns the rejectionPercentage.
	 */
	public float getRejectionPercentage() {
		return rejectionPercentage;
	}

	/**
	 * @param rejectionPercentage
	 *            The rejectionPercentage to set.
	 */
	public void setRejectionPercentage(float rejectionPercentage) {
		this.rejectionPercentage = rejectionPercentage;
	}

	/**
	 * @return Returns the stockExId.
	 */
	public String getStockExId() {
		return stockExId;
	}

	/**
	 * @param stockExId
	 *            The stockExId to set.
	 */
	public void setStockExId(String stockExId) {
		this.stockExId = stockExId;
	}

	/**
	 * @return Returns the timeZoneId.
	 */
	public String getTimeZoneId() {
		return timeZoneId;
	}

	/**
	 * @param timeZoneId
	 *            The timeZoneId to set.
	 */
	public void setTimeZoneId(String timeZone) {
		this.timeZoneId = timeZone;
	}

	/**
	 * @return Returns the adjustStockPrice.
	 */
	public String getAdjustStockPrice() {
		return adjustStockPrice;
	}

	/**
	 * @param adjustStockPrice
	 *            The adjustStockPrice to set.
	 */
	public void setAdjustStockPrice(String adjustStockPrice) {
		this.adjustStockPrice = adjustStockPrice;
	}

	/**
	 * @return Returns the percentage_change_in_share.
	 */
	public float getPercentage_change_in_share() {
		return percentage_change_in_share;
	}

	/**
	 * @param percentage_change_in_share
	 *            The percentage_change_in_share to set.
	 */
	public void setPercentage_change_in_share(float percentage_change_in_share) {
		this.percentage_change_in_share = percentage_change_in_share;
	}

	/**
	 * @return Returns the resetButton.
	 */
	public String getResetButton() {
		return resetButton;
	}

	/**
	 * @param resetButton
	 *            The resetButton to set.
	 */
	public void setResetButton(String resetButtonValue) {
		this.resetButton = resetButtonValue;

	}

	/**
	 * @return Returns the saveButton.
	 */
	public String getSaveButton() {
		return saveButton;
	}

	/**
	 * @param saveButton
	 *            The saveButton to set.
	 */
	public void setSaveButton(String sButton) {
		this.saveButton = sButton;

	}

	/**
	 * @return Returns the industryClassificationCollection.
	 */
	public Collection getIndustryClassificationCollection() {
		Vector options = new Vector(10);
		Connection connection = null;
		options.add(new LabelValueBean("Not Selected", "value0"));

		try {
			if (connection == null)
				connection = con.getdbConnection();
			PreparedStatement stmt = connection
					.prepareStatement(ConnectInit.queries
							.getProperty("sysConfig_select_*_from_index_classification_master"));
			rst = stmt.executeQuery();

			while (rst.next()) {
				int count = rst.getInt(1);
				options.add(new LabelValueBean(rst.getString(2), "value"
						+ count));
			}
			rst.close();
			stmt.close();
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ex) {
				Logging.error(" Error : Unable to close Connection "
						+ ex.getMessage());
			}
		}
		industryClassificationCollection = options;

		return industryClassificationCollection;
	}

	/**
	 * @param industryClassificationCollection
	 *            The industryClassificationCollection to set.
	 */
	public void setIndustryClassificationCollection(
			Collection industryClassificationCollection) {
		this.industryClassificationCollection = industryClassificationCollection;
	}

	/**
	 * @return Returns the timeZoneCollection.
	 */
	public Collection getTimeZoneCollection() {
		Vector time = new Vector(30, 30);
		Connection connection = null;
		time.add(new LabelValueBean("Not Selected", "value0"));

		try {
			if (connection == null)
				connection = con.getdbConnection();
			PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries
					.getProperty("sysconfig_select_time"));
			rst = stmt.executeQuery();

			while (rst.next()) {

				int timeZoneId = rst.getInt(1);
				String description = rst.getString(3);
				if (rst.getFloat(2) < 0.0) {
					time.add(new LabelValueBean("(GMT" + rst.getString(2)
							+ ") " + description, "value" + timeZoneId));
				} else if (rst.getFloat(2) >= 0.0) {
					time.add(new LabelValueBean("(GMT+" + rst.getString(2)
							+ ") " + description, "value" + timeZoneId));
				}

			}
			rst.close();
			stmt.close();
		} catch (SQLException e) {
			Logging.error(" Error : " + e.getMessage());
			//e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ex) {
				Logging.error(" Error : Unable to close Connection "
						+ ex.getMessage());
			}
		}
		timeZoneCollection = time;

		return timeZoneCollection;
	}

	/**
	 * @param timeZoneCollection
	 *            The timeZoneCollection to set.
	 */
	public void setTimeZoneCollection(Collection timeZoneCollection) {
		this.timeZoneCollection = timeZoneCollection;
	}

	/**
	 * @return Returns the countryIdCollection.
	 */
	public Collection getCountryIdCollection() {
		Vector countries = new Vector(300, 100);
		countries.add(new LabelValueBean("Not Selected", "value0"));
		Connection connection = null;
		try {
			if (connection == null)
				connection = con.getdbConnection();
			PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries
					.getProperty("sysconfig_select_countries"));
			rst = stmt.executeQuery();

			while (rst.next()) {
				int count = rst.getInt(1);
				countries.add(new LabelValueBean(rst.getString(2), "value"
						+ count));
			}
			rst.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Logging.error(" Error : " + e.getMessage());
			//e.printStackTrace();
			
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ex) {
				Logging.error(" Error : Unable to close Connection "
						+ ex.getMessage());
			}
		}
		countryIdCollection = countries;

		return countryIdCollection;
	}

	/**
	 * @param countryIdCollection
	 *            The countryIdCollection to set.
	 */
	public void setCountryIdCollection(Collection countryIdCollection) {

		this.countryIdCollection = countryIdCollection;
	}

	/**
	 * @return Returns the s_indexTypeIndexCollection.
	 */
	public Collection getS_indexTypeIndexCollection() {

		Vector v = new Vector(50, 50);
		v.add(new LabelValueBean("Not Selected", "value0"));
		Connection connection = null;
		try {
			if (connection == null)
				connection = con.getdbConnection();
			PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries
					.getProperty("index_type_list"));
			rst = stmt.executeQuery();

			while (rst.next()) {
				int count = rst.getInt(1);
				v.add(new LabelValueBean(rst.getString(2), "value" + count));
				Logging.debug("indexcollectionlist=" + rst.getString(2));
			}
			rst.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Logging.error(" Error : " + e.getMessage());
			//e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ex) {
				Logging.error(" Error : Unable to close Connection "
						+ ex.getMessage());
			}
		}
		s_indexTypeIndexCollection = v;
		return s_indexTypeIndexCollection;

		/*
		 * 
		 * query = con.queries.getProperty("index_type_list"); String id1 =
		 * null; Connection connection = null; Statement stmt = null; rst =
		 * null; Vector v = new Vector(); AcessControl asc = new AcessControl();
		 * String NotSelected = asc.getLangValues("Masters.NotSelected");
		 * 
		 * try { if (connection == null) connection = con.getdbConnection();
		 * stmt = connection.createStatement(); rst = stmt.executeQuery(query);
		 * v.add(new LabelValueBean(NotSelected, "0")); while (rst.next()) { id1
		 * = rst.getString(1); v.add(new LabelValueBean(rst.getString(2), id1));
		 * } if (rst != null) rst.close(); s_indexTypeIndexCollection = v; }
		 * catch (Exception e) { // TODO: handle exception
		 * Logging.getError(" Error : " + e.getMessage());
		 * //e.printStackTrace(); } finally { try { if (connection != null)
		 * connection.close(); } catch (Exception ee) { try { if (connection !=
		 * null) connection.close(); } catch (Exception ex) {
		 * Logging.getError(" Error : " + ex.getMessage()); }
		 * Logging.getError(" Error : " + ee.getMessage()); } }
		 * 
		 * 
		 * return s_indexTypeIndexCollection;
		 */
	}

	/**
	 * @param typeIndexCollection
	 *            The s_indexTypeIndexCollection to set.
	 */
	public void setS_indexTypeIndexCollection(Collection typeIndexCollection) {
		s_indexTypeIndexCollection = typeIndexCollection;
	}

	/**
	 * @return Returns the s_stockTypeCollection.(stock typr list)
	 */
	public Collection getS_stockTypeCollection() {

		Vector indexList = new Vector(50, 50);
		indexList.add(new LabelValueBean("Not Selected", "value0"));
		Connection connection = null;
		try {
			if (connection == null)
				connection = con.getdbConnection();
			PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries
					.getProperty("s_stock_type_list"));
			rst = stmt.executeQuery();

			while (rst.next()) {
				int count = rst.getInt(1);
				indexList.add(new LabelValueBean(rst.getString(2), "value"
						+ count));
				Logging.debug("indexlist=" + rst.getString(2));
			}
			rst.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Logging.error(" Error : " + e.getMessage());
		//	e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ex) {
				Logging.error(" Error : Unable to close Connection "
						+ ex.getMessage());
			}
		}
		s_stockTypeCollection = indexList;
		return s_stockTypeCollection;

		/*
		 * 
		 * Vector indexList=new Vector(); ResultSet rst=null; Connection
		 * connection = null; app.Connect con=new app.Connect();
		 * if(connection==null) { connection = con.getdbConnection(); }
		 * AcessControl asc=new AcessControl(); String
		 * SelStockType=asc.getLangValues("Masters.SelStockType"); if
		 * (s_stockTypeCollection == null) { try { rst =
		 * StockResult.getStockTypeList(connection); indexList.add(new
		 * LabelValueBean(SelStockType,"0")); while(rst.next()){ String
		 * count=rst.getString(1); indexList.add(new
		 * LabelValueBean(rst.getString(1),count)); } } catch (SQLException e) {
		 * Logging.getError("Error  :"+e.getMessage()); e.printStackTrace();
		 * }finally{ try{if(connection!=null) connection.close();
		 * }catch(Exception ee){
		 * Logging.getError(" Error : Unable to close Connection "
		 * +ee.getMessage()); } } s_stockTypeCollection =indexList ; } return
		 * s_stockTypeCollection;
		 */

	}

	/**
	 * @param stockTypeCollection
	 *            The s_stockTypeCollection to set.
	 */
	public void setS_stockTypeCollection(Collection stockTypeCollection) {
		s_stockTypeCollection = stockTypeCollection;
	}

	/**
	 * @return Returns the currencyIdCollection.
	 */
	public Collection getCurrencyIdCollection() {
		Vector currencies = new Vector(200, 50);
		currencies.add(new LabelValueBean("Not Selected", "value0"));
		Connection connection = null;
		try {
			if (connection == null)
				connection = con.getdbConnection();
			PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries
					.getProperty("sysconfig_select_currencies"));
			rst = stmt.executeQuery();

			while (rst.next()) {
				int count = rst.getInt(1);
				currencies.add(new LabelValueBean(rst.getString(2) + ": "
						+ rst.getString(3), "value" + count));
			}
			rst.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Logging.error(" Error : " + e.getMessage());
			//e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ex) {
				Logging.error(" Error : Unable to close Connection "
						+ ex.getMessage());
			}
		}
		currencyIdCollection = currencies;

		return currencyIdCollection;
	}

	/**
	 * @param currencyIdCollection
	 *            The currencyIdCollection to set.
	 */
	public void setCurrencyIdCollection(Collection currencyIdCollection) {
		this.currencyIdCollection = currencyIdCollection;
	}

	/**
	 * @return Returns the stockExCollection.
	 */
	public Collection getStockExCollection() {
		Vector stocksEx = new Vector(150, 50);
		stocksEx.add(new LabelValueBean("Not Selected", "value0"));
		Connection connection = null;

		try {
			if (connection == null)
				connection = con.getdbConnection();
			PreparedStatement stmt = connection
					.prepareStatement(ConnectInit.queries
							.getProperty("sysconfig_select_from_stock_exchange_master"));
			rst = stmt.executeQuery();

			while (rst.next()) {
				int count = rst.getInt(1);
				stocksEx.add(new LabelValueBean(rst.getString(2), "value"
						+ count));
			}
			rst.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Logging.error(" Error : " + e.getMessage());
			//e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ex) {
				Logging.error(" Error : Unable to close Connection "
						+ ex.getMessage());
			}
		}
		stockExCollection = stocksEx;

		return stockExCollection;
	}

	/**
	 * @param stockExCollection
	 *            The stockExCollection to set.
	 */
	public void setStockExCollection(Collection stockExCollection) {
		this.stockExCollection = stockExCollection;
	}

	/**
	 * @return Returns the language.
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language
	 *            The language to set.
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return Returns the languageCollection.
	 */
	public Collection getLanguageCollection() {
		Vector lan = new Vector(10, 10);
		Connection connection = null;
		if (languageCollection == null) {

			try {
				if (connection == null)
					connection = con.getdbConnection();
				PreparedStatement stmt = connection
						.prepareStatement(ConnectInit.queries
								.getProperty("sysconfig_select_language"));
				rst = stmt.executeQuery();
				while (rst.next()) {
					int count = rst.getInt(1);
					lan.add(new LabelValueBean(rst.getString(2), "value"
							+ count));
				}
				rst.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Logging.error(" Error : " + e.getMessage());
				//e.printStackTrace();
			} finally {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error(" Error : Unable to close Connection "
							+ ex.getMessage());
				}
			}
			if (lan.equals(null)) {
				lan.add(new LabelValueBean("Not Selected", "value0"));
			}
			languageCollection = lan;
		}
		return languageCollection;
	}

	/**
	 * @param languageCollection
	 *            The languageCollection to set.
	 */
	public void setLanguageCollection(Collection languageCollection) {
		this.languageCollection = languageCollection;
	}

	/**
	 * @return Returns the indexUpdateCollection.
	 */
	public Collection getIndexUpdateCollection() {

		Vector update = new Vector(10);
		update.add(new LabelValueBean("Not Selected", "value0"));
		Connection connection = null;
		try {
			if (connection == null)
				connection = con.getdbConnection();
			PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries
					.getProperty("indexUpdate_getall1"));
			rst = stmt.executeQuery();

			while (rst.next()) {
				int count = rst.getInt(1);
				update
						.add(new LabelValueBean(rst.getString(2), "value"
								+ count));
			}

			rst.close();
			stmt.close();
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ex) {
				Logging.error(" Error : Unable to close Connection "
						+ ex.getMessage());
			}
		}
		indexUpdateCollection = update;
		return indexUpdateCollection;
	}

	/**
	 * @return Returns the index_id.
	 */
	public String getIndex_id() {
		return index_id;
	}

	/**
	 * @param index_id
	 *            The index_id to set.
	 */
	public void setIndex_id(String indexid) {
		this.index_id = indexid;
	}

	/**
	 * @return Returns the faceValue.
	 */
	public float getFaceValue() {
		return faceValue;
	}

	/**
	 * @param faceValue
	 *            The faceValue to set.
	 */
	public void setFaceValue(float faceValue) {
		this.faceValue = faceValue;
	}

	/**
	 * @return Returns the paidValue.
	 */
	public float getPaidValue() {
		return paidValue;
	}

	/**
	 * @param paidValue
	 *            The paidValue to set.
	 */
	public void setPaidValue(float paidValue) {
		this.paidValue = paidValue;
	}

	/**
	 * @param indexUpdateCollection
	 *            The indexUpdateCollection to set.
	 */
	public void setIndexUpdateCollection(Collection indexUpdateCollection) {
		this.indexUpdateCollection = indexUpdateCollection;
	}
	/**
	 * Database Connectivity
	 * */
	// commented By Manoj Adekar
	// public void dbconnect(){

	// try {
	// if(app.Connect.con==null)
	// {
	// con.getConnection();
	// }

	// } catch (Exception e) { Logging.debug(e);}

	// }

}
