/*
 * Created on Jun 11, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author neha
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */


package harrier.income.com.fixedincome;


//import org.apache.struts.validator.ValidatorForm;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import app.AcessControl;
import app.ComputeIndexForm;
import app.Connect;

import com.harrier.initializeation.ConnectInit;


public class  FixedIncomeDefineIndexForm extends ActionForm {
	Logger Logging = Logger.getLogger(FixedIncomeDefineIndexForm.class);
	String name_list=null; // added by neha for compliance
	Collection nameListCollection = null;

       Connection con=null;
       
       
       /**
   	 * @return Returns the databaseConnection.
   	 */
   	public Connection getCon() {
   		return con;
   	}
	/**
	 * 
	 */
	public FixedIncomeDefineIndexForm() throws Exception {
		
		//super();
		Connect c=ConnectInit.getConnect();
		try {
		if (con == null) {
			con = c.getConnectionForTransaction();
		}
		d_baseDate = QueryClass.formatDate();
		d_creationDate = QueryClass.formatDate();
		
			rs = con.createStatement().executeQuery(
					ConnectInit.queries.getProperty("select_system_config"));
			if (rs.next()) {
				
				s_alertPercent = 	rs.getString("alert_percentage");
				s_rejectionPercent 	= 	rs.getString("rejection_percentage");
				s_baseCurrency    =	rs.getString("currency_id");
				//s_indexType		=	rs.getString("indextype");
				
			}
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
			// e.printStackTrace();
			// TODO: handle exception
		}
		
		// TODO Auto-generated constructor stub
	}

	// radio b_isIndexIsChildOrCustomized;
	// parent index combo box s_parentIndex
	private String b_isNewIndexIsChild, s_alertPercent, s_rejectionPercent,
			s_parentIndex, b_isIndexIsChildOrCustomized, submitvalue,
			i_indexID, s_indexName, s_indexType, d_creationDate, d_baseDate,
			s_industryClassificationID, s_applyClassification, s_baseCurrency,
			d_baseValue,  s_ISIN,
			b_isActive, b_isCaptured,  b_computeSettlementValue,
			s_capturedFrom, b_isIndexCustomizedVersion, s_type, currencyCode,
			copyOfAnotherIndex, is_testIndex, b_ShowChild, submitresult,
			copyparent_before, requestString, index_currency;

	String comparerequestvalue = "yes", user_id = null;

	public Connection databaseConnection = null;

	public String Currency = "";

	/**
	 * @return Returns the currency.
	 */
	public String getCurrency() {
		Logging.debug("setting Currency : " + Currency);
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		if (s_parentIndex != null && !s_parentIndex.trim().equals("0")) {
			Logging.debug("setting 1 Currency : " + Currency);
			query = ConnectInit.queries.getProperty("get_index_base_currency_code");
			Logging.debug("query :" + query);
			try {
				if (connection == null)
					connection = c.getdbConnection();
				pstmt = connection.prepareStatement(query);
				pstmt.setString(1, s_parentIndex);
				resultSet = pstmt.executeQuery();
				if (resultSet.next()) {
					Currency = resultSet.getString("index_currency");
				}
				if (pstmt != null)
					pstmt.close();
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				// TODO: handle exception
				Logging.error(" Error : " + e.getMessage());
			} finally {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ee) {
					try {
						if (connection != null)
							connection.close();
					} catch (Exception ex) {
						Logging.error(" Error : " + ex.getMessage());
					}
					Logging.error(" Error : " + ee.getMessage());
				}
			}
			//Currency = currency;
		}
		return Currency;
	}

	/**
	 * @param currency The currency to set.
	 */
	public void setCurrency(String currency) {

	}

	/**
	 * @return Returns the Index currency.
	 */
	public String getIndexCurrency() {
		String curr = "0";
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Logging.debug("setting Currency : " + Currency);
		if (s_parentIndex != null && !s_parentIndex.trim().equals("0")) {
			Logging.debug("setting 1 Currency : " + Currency);
			query = ConnectInit.queries.getProperty("get_index_currency");
			Logging.debug("query :" + query);
			try {
				if (connection == null)
					connection = c.getdbConnection();
				pstmt = connection.prepareStatement(query);
				pstmt.setString(1, s_parentIndex);
				resultSet = pstmt.executeQuery();
				if (resultSet.next()) {
					curr = resultSet.getString(1);
				}
				if (pstmt != null)
					pstmt.close();
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				// TODO: handle exception
				Logging.error(" Error : " + e.getMessage());
			} finally {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ee) {
					try {
						if (connection != null)
							connection.close();
					} catch (Exception ex) {
						Logging.error(" Error : " + ex.getMessage());
					}
					Logging.error(" Error : " + ee.getMessage());
				}
			}
			//Currency = currency;
		}
		return curr;
	}

	/**
	 * @return Returns the currency.
	 */
	public String getIndexType() {
		String type = "0";
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Logging.debug("setting index type : ");
		if (s_parentIndex != null && !s_parentIndex.trim().equals("0")) {
			Logging.debug("setting 1 Currency : " + Currency);
			query = ConnectInit.queries.getProperty("get_index_type");
			Logging.debug("query :" + query);
			try {
				if (connection == null)
					connection = c.getdbConnection();
				pstmt = connection.prepareStatement(query);
				pstmt.setString(1, s_parentIndex);
				resultSet = pstmt.executeQuery();
				if (resultSet.next()) {
					type = resultSet.getString(1);
				}
				if (pstmt != null)
					pstmt.close();
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				// TODO: handle exception
				Logging.error(" Error : " + e.getMessage());
			} finally {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ee) {
					try {
						if (connection != null)
							connection.close();
					} catch (Exception ex) {
						Logging.error(" Error : " + ex.getMessage());
					}
					Logging.error(" Error : " + ee.getMessage());
				}
			}
			//Currency = currency;
		}
		return type;
	}

	/**
	 * @return Returns the comparerequestvalue.
	 */
	public String getComparerequestvalue() {
		return comparerequestvalue;
	}

	/**
	 * @param comparerequestvalue The comparerequestvalue to set.
	 */
	public void setComparerequestvalue(String comparerequestvalue) {
		this.comparerequestvalue = comparerequestvalue;
	}

	private Collection s_parentIndexCollection, s_indexTypeIndexCollection,
			s_industryClassificationIDCollection, s_baseCurrencyCollection,
			s_capturedFromCollection;

	String query;

	Connect c = ConnectInit.getConnect();

	//Connection con=null;
	ResultSet rs = null;

	/**
	 * @return Returns the b_computeSettlementValue.
	 */
	public String getB_computeSettlementValue() {
		return b_computeSettlementValue;
	}

	/**
	 * @param settlementValue
	 *            The b_computeSettlementValue to set.
	 */
	public void setB_computeSettlementValue(String settlementValue) {
		b_computeSettlementValue = settlementValue;
		Logging.debug("settlementValue");
	}

	/**
	 * @return Returns the b_isActive.
	 */
	public String getB_isActive() {
		return b_isActive;
	}

	/**
	 * @param active
	 *            The b_isActive to set.
	 */
	public void setB_isActive(String active) {
		b_isActive = active;
	}

	/**
	 * @return Returns the b_isCaptured.
	 */
	public String getB_isCaptured() {
		if (b_isCaptured == null) {
			b_isCaptured = "n";
			return b_isCaptured;
		}
		return b_isCaptured;
	}

	/**
	 * @param captured
	 *            The b_isCaptured to set.
	 */
	public void setB_isCaptured(String captured) {
		b_isCaptured = captured;
	}

	/**
	 * @return Returns the b_isIndexCustomizedVersion.
	 */
	public String getB_isIndexCustomizedVersion() {
		return b_isIndexCustomizedVersion;
	}

	/**
	 * @param indexCustomizedVersion
	 *            The b_isIndexCustomizedVersion to set.
	 */
	public void setB_isIndexCustomizedVersion(String indexCustomizedVersion) {
		b_isIndexCustomizedVersion = indexCustomizedVersion;
	}

	/**
	 * @return Returns the b_isNewIndexIsChild.
	 */
	public String getB_isNewIndexIsChild() {
		return b_isNewIndexIsChild;
	}

	/**
	 * @param newIndexIsChild
	 *            The b_isNewIndexIsChild to set.
	 */
	public void setB_isNewIndexIsChild(String newIndexIsChild) {
		b_isNewIndexIsChild = newIndexIsChild;
	}

	/**
	 * @return Returns the d_baseDate.
	 */
	public String getD_baseDate() {
		return d_baseDate;
	}

	/**
	 * @param date
	 *            The d_baseDate to set.
	 */
	public void setD_baseDate(String date) {
		Logging.debug(d_baseDate + " date : " + date);
		d_baseDate = date;
	}

	/**
	 * @return Returns the d_baseValue.
	 */
	public String getD_baseValue() {
		return d_baseValue;
	}

	/**
	 * @param value
	 *            The d_baseValue to set.
	 */
	public void setD_baseValue(String value) {
		d_baseValue = value;
	}

	/**
	 * @return Returns the d_creationDate.
	 */
	public String getD_creationDate() {
		return d_creationDate;
	}

	/**
	 * @param date
	 *            The d_creationDate to set.
	 */
	public void setD_creationDate(String date) {
		d_creationDate = date;
	}

	
	
	
	
	

	/**
	 * @return Returns the i_indexID.
	 */
	public String getI_indexID() {
		Logging.debug("index id through bean is" + i_indexID);
		return i_indexID;
	}

	/**
	 * @param i_indexid
	 *            The i_indexID to set.
	 */
	public void setI_indexID(String i_indexid) {
		i_indexID = i_indexid;
	}

	

	/**
	 * @return Returns the s_applyClassification.
	 */
	public String getS_applyClassification() {
		return s_applyClassification;
	}

	/**
	 * @param classification
	 *            The s_applyClassification to set.
	 */
	public void setS_applyClassification(String classification) {
		Logging.debug("classification match666 : " + classification);
		this.s_applyClassification = classification;
		Logging.debug("classification match666 : " + classification);
	}

	/**
	 * @return Returns the s_baseCurrency.
	 */
	public String getS_baseCurrency() {
		if (s_parentIndex != null && !s_parentIndex.trim().equals("0")
				&& getGetSet().equals("yes")) {
			s_baseCurrency = getIndexCurrency();
		}
		return s_baseCurrency;
	}

	/**
	 * @param currency
	 *            The s_baseCurrency to set.
	 */
	public void setS_baseCurrency(String currency) {
		s_baseCurrency = currency;
	}

	/**
	 * @return Returns the s_capturedFrom.
	 */
	public String getS_capturedFrom() {
		return s_capturedFrom;
	}

	/**
	 * @param from
	 *            The s_capturedFrom to set.
	 */
	public void setS_capturedFrom(String from) {
		s_capturedFrom = from;
	}

	/**
	 * @return Returns the s_indexName.
	 */
	public String getS_indexName() {
		return s_indexName;
	}

	/**
	 * @param name
	 *            The s_indexName to set.
	 */
	public void setS_indexName(String name) {
		s_indexName = name;
	}

	/**
	 * @return Returns the s_indexType.
	 */
	public String getS_indexType() {
		if (s_parentIndex != null && !s_parentIndex.trim().equals("0")
				&& getGetSet().equals("yes")) {
			s_indexType = getIndexType();
		}
		return s_indexType;
	}

	/**
	 * @param type
	 *            The s_indexType to set.
	 */
	public void setS_indexType(String type) {
		s_indexType = type;
	}

	/**
	 * @return Returns the s_industryClassificationID.
	 */
	public String getS_industryClassificationID() {
		return s_industryClassificationID;
	}

	/**
	 * @param classificationID
	 *            The s_industryClassificationID to set.
	 */
	public void setS_industryClassificationID(String classificationID) {
		s_industryClassificationID = classificationID;
	}

	/**
	 * @return Returns the s_ISIN.
	 */
	public String getS_ISIN() {
		return s_ISIN;
	}

	/**
	 * @param s_isin
	 *            The s_ISIN to set.
	 */
	public void setS_ISIN(String s_isin) {
		s_ISIN = s_isin;
	}

	

	/**
	 * @return Returns the s_type.
	 */
	public String getS_type() {
		return s_type;
	}

	/**
	 * @param s_type
	 *            The s_type to set.
	 */
	public void setS_type(String s_type) {
		this.s_type = s_type;
	}

	/**
	 * @return Returns the s_parentIndex.
	 */
	public String getS_parentIndex() {
		return s_parentIndex;
	}

	/**
	 * @param index
	 *            The s_parentIndex to set.
	 */
	public void setS_parentIndex(String index) {
		s_parentIndex = index;
	}

	/**
	 * @return Returns the s_alertPercent.
	 */
	public String getS_alertPercent() {
		return s_alertPercent;
	}

	/**
	 * @param percent
	 *            The s_alertPercent to set.
	 */
	public void setS_alertPercent(String percent) {
		s_alertPercent = percent;
	}

	/**
	 * @return Returns the s_rejectionPercent.
	 */
	public String getS_rejectionPercent() {
		return s_rejectionPercent;
	}

	/**
	 * @param percent
	 *            The s_rejectionPercent to set.
	 */
	public void setS_rejectionPercent(String percent) {
		s_rejectionPercent = percent;
	}

	/**
	 * @return Returns the b_isIndexIsChildOrCustomized.
	 */
	public String getB_isIndexIsChildOrCustomized() {

		return b_isIndexIsChildOrCustomized;
	}

	/**
	 * @param indexIsChildOrCustomized
	 *            The b_isIndexIsChildOrCustomized to set.
	 */
	public void setB_isIndexIsChildOrCustomized(String indexIsChildOrCustomized) {
		b_isIndexIsChildOrCustomized = indexIsChildOrCustomized;

	}

	/**
	 * Sets the buttons.
	 * 
	 * @param buttons
	 *            The buttons to set
	 */

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		/*b_isNewIndexIsChild = null;
		 s_alertPercent = null;
		 s_rejectionPercent = null;
		 s_parentIndex = null;
		 b_isIndexIsChildOrCustomized = null;
		 b_isIndexCustomizedVersion = null;
		 i_indexID = null;
		 s_indexName = null;
		 s_indexType = null;
		 d_creationDate = null;
		 d_baseDate = null;
		 s_industryClassificationID = null;
		 s_applyClassification = null;
		 s_baseCurrency = null;
		 d_baseValue = null;
		 s_reutersCode = null;
		 d_startTime = null;
		 d_stopTime = null;
		 s_ISIN = null;
		 b_isActive = null;
		 b_isCaptured = null;
		 i_timeInterval = null;
		 b_computeSettlementValue = null;
		 s_capturedFrom = null;
		 s_type = null;
		 //button1=null;
		 submitvalue = null;
		 currencyCode = null;*/
		 copyOfAnotherIndex = "n";
		 Currency = "";
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
	 */

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		Logging.debug("request.getParameter(s_applyClassification) : : == "
				+ request.getParameter("s_applyClassification"));
		//submitresult = null;
		submitresult = "no";
		if (request.getParameter("s_applyClassification") != null
				&& request.getParameter("s_applyClassification").trim().equals(
						"on")) {
			s_applyClassification = "on";
		} else {
			s_applyClassification = null;
		}
		if (request.getParameter("b_isActive") != null
				&& request.getParameter("b_isActive").trim().equals("on")) {
			b_isActive = "on";
		} else {
			b_isActive = null;
		}
		if (request.getParameter("b_isCaptured") != null
				&& request.getParameter("b_isCaptured").trim().equals("on")) {
			b_isCaptured = "on";
		} else {
			b_isCaptured = null;
		}
		if (request.getParameter("is_testIndex") != null
				&& request.getParameter("is_testIndex").trim().equals("on")) {
			is_testIndex = "on";
		} else {
			is_testIndex = null;
		}
		if (request.getParameter("b_computeSettlementValue") != null
				&& request.getParameter("b_computeSettlementValue").trim()
						.equals("on")) {
			b_computeSettlementValue = "on";
		} else {
			b_computeSettlementValue = null;
		}
		if (request.getParameter("b_ShowChild") != null
				&& request.getParameter("b_ShowChild").trim().equals("on")) {
			b_ShowChild = "on";
		} else {
			b_ShowChild = null;
		}
		ActionErrors errors = new ActionErrors();

		Logging.debug("copyOfAnotherIndex v : : == " + copyOfAnotherIndex);
		Logging.debug("copyparent_before v : : == " + copyparent_before);
		if (copyparent_before.trim().equals("copy")
				|| submitvalue.equals("fromParent")) {
			return errors;
		}
		try {
			Logging.debug("Starting validations");
			byMethod(errors);
			Logging.debug(" validated");

		} catch (Exception e) {
			errors.add(null, new ActionError("Error.message.msg"));
			Logging.debug("Error in Validation " + e.getMessage());
		}
		return errors;
	}

	public boolean compareDate() {
		boolean flag = false;
		try {
			//	Logging.getDebug("d_creationDate : "+d_creationDate);
			//	Logging.getDebug("d_creationDate : Year:month:date->
			// "+d_creationDate.trim().substring(6,10)+":"+d_creationDate.trim().substring(3,5)+":"+d_creationDate.trim().substring(0,2));
			//	Logging.getDebug("d_baseDate : "+d_baseDate);
			Date creationDate = new Date(new Integer(d_creationDate.trim()
					.substring(6, 10)).intValue(), new Integer(d_creationDate
					.trim().substring(3, 5)).intValue(), new Integer(
					d_creationDate.trim().substring(0, 2)).intValue());
			Date baseDate = new Date(new Integer(d_baseDate.trim().substring(6,
					10)).intValue(), new Integer(d_baseDate.trim().substring(3,
					5)).intValue(), new Integer(d_baseDate.trim().substring(0,
					2)).intValue());
			//	Logging.getDebug("creationDate :"+creationDate);
			//	Logging.getDebug("baseDate :"+baseDate);
			int result = creationDate.compareTo(baseDate);
			Logging.debug("result :" + result);
			if (result < 0)
				flag = false;
			else
				flag = true;
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		}
		return flag;
	}

	/*public boolean compareTime() {
		boolean flag = false;
		try {
			String[] arr = d_startTime.split(":");
			String[] arr1 = d_stopTime.split(":");
			int h1 = new Integer(arr[0]).intValue();
			int h2 = new Integer(arr1[0]).intValue();
			int m1 = new Integer(arr[1]).intValue();
			int m2 = new Integer(arr1[1]).intValue();
			int s1 = new Integer(arr[2]).intValue();
			int s2 = new Integer(arr1[2]).intValue();
			if (h1 < h2) {
				return true;
			} else if (m1 < m2) {
				return true;
			} else if (s1 < s2) {

				return true;
			} else
				return false;

		} catch (Exception e) {
			Logging.getError(" Error : " + e.getMessage());
		}
		return flag;
	}*/

	public void addErrors(ActionErrors errors) {
		if ((d_baseDate == null || d_baseDate.trim().equals(""))) {
			Logging.debug("d_baseDate : " + d_baseDate);
			errors.add(null, new ActionError("Error.message.BaseDate"));
			//	Logging.log..debug("Inside if 5");
		}
/*
		if (d_startTime == null || d_startTime.trim().equals("")) {
			Logging.getDebug("d_startTime : " + d_startTime);
			errors.add(null, new ActionError("Error.message.StartTime"));
			//	Logging.getDebug("Inside if 5");
		} else {
			if (!ValidateTime(d_startTime)) {
				errors.add(null, new ActionError("Error.message.StartTime"));
			}
		}

		Logging.getDebug("d_baseValue :" + d_baseValue);

		if (d_stopTime == null || d_stopTime.trim().equals("")) {
			Logging.getDebug("d_stopTime : " + d_stopTime);
			errors.add(null, new ActionError("Error.message.StoptTime"));
			//	Logging.getDebug("Inside if 5");
		} else {
			if (!ValidateTime(d_stopTime)) {
				errors.add(null, new ActionError("Error.message.StoptTime"));
			}
		}*/

		if (!s_indexType.trim().equals("4")
				&& (s_baseCurrency == null || s_baseCurrency.trim().equals("0"))) {
			Logging.debug("s_baseCurrency : " + s_baseCurrency);
			Logging.debug("adding base currency error4");
			errors.add(null, new ActionError("Error.message.BaseCurrency"));
			//	Logging.getDebug("Inside if 5");
		}

		if (d_baseValue == null || d_baseValue.trim().equals("")) {
			Logging.debug("d_baseValue : " + d_baseValue);
			errors.add(null, new ActionError("Error.message.BaseValue"));
			//	Logging.getDebug("Inside if 5");
		} else {
			try {
				float basevalue = new Float(d_baseValue).floatValue();
				if (basevalue < 0) {
					errors
							.add(null, new ActionError(
									"Error.message.BaseValue"));
				}
			} catch (Exception e) {
				errors.add(null, new ActionError("Error.message.BaseValue"));
				Logging.error(" Error : " + e.getMessage());
			}
		}
		Logging.debug("Between 2 if's ");
		/*if (ValidateNumber(i_timeInterval) == false) {
			errors.add(null, new ActionError("Error.message.TimeInterval"));

			//Logging.getDebug("Inside if 8");
		} else {
			//Long timeInterval=new Long(i_timeInterval);
			long interval = new Long(i_timeInterval).longValue();
			if (interval <= 0) {
				errors.add(null, new ActionError("Error.message.TimeInterval"));
			}
		}*/

	}

	

	private boolean ValidateNumber(String str) {
		if (str == null || str.trim().equals("")) {
			return false;
		}
		for (int i = 0; i < str.length(); i++) {
			if (Character.isLetter(str.charAt(i)))
				return false;
		}
		return true;
	}

	private String ValidatePerc(String str, String for1) {

		if (str != null || !str.trim().equals("")) {
			if (!ValidateNumber(str))
				return "invalid";
			else {
				try {
					float j = Float.parseFloat(str);//str.compareTo("100");
					if (j > 10 && for1.equals("Alert"))
						return "invalid";

					if (j > 20 && for1.equals("Rejection"))
						return "invalid";

				} catch (Exception e) {
					Logging.error(" Error : " + e.getMessage());
					return "invalid";
				}
			}
		}
		return "valid";

	}

	public int checkIndex() {
		int i = 0;

		//this function returns
		// 1 if New Index Child Index of Existing Index
		// 2 if New Index Customized version of an Existing Index
		// 3 if Copy Of Another Index
		//2 or more if child index radio button is selected

		if (b_isIndexIsChildOrCustomized != null
				&& b_isIndexIsChildOrCustomized.equals("3")) {
			i = 3;
			return i;
		}

		if (s_indexType.equals("4")) {
			i = 4;
			return i;
		}
		if (s_indexType.equals("5")) {
			i = 5;
			return i;
		}
		if (b_isIndexIsChildOrCustomized == null
				|| b_isIndexIsChildOrCustomized.equals("0")) {
			i = 6;
			/*this.s_baseCurrency=getIndexCurrency();
			 this.s_indexType=getIndexType();*/
			return i;
		} else {

			if (b_isIndexIsChildOrCustomized.equals("1")) {
				i = 1;
				/*this.s_baseCurrency=getIndexCurrency();
				 this.s_indexType=getIndexType();*/
				return i;
			}
			if (b_isIndexIsChildOrCustomized.equals("2")) {
				i = 2;
				/*this.s_baseCurrency=getIndexCurrency();
				 this.s_indexType=getIndexType();*/
				return i;
			}

		}
		return i;
	}

	private boolean validBaseCurrency() {
		if (s_baseCurrency.equals(currencyCode))
			return false;
		else
			return true;
	}

	public String getSubmitvalue() {
		return submitvalue;
	}

	/**
	 * @param submitvalue
	 *            The submitvalue to set.
	 */
	public void setSubmitvalue(String submitvalue) {
		this.submitvalue = submitvalue;
	}

	/**
	 * @return Returns the currencyCode.
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}

	/**
	 * @param currencyCode
	 *            The currencyCode to set.
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	private boolean isThisNameExist() {

		return false;
	}

	/**
	 * @return Returns the copyOfAnotherIndex.
	 */
	public String getCopyOfAnotherIndex() {
		return copyOfAnotherIndex;
	}

	/**
	 * @param copyOfAnotherIndex
	 *            The copyOfAnotherIndex to set.
	 */
	public void setCopyOfAnotherIndex(String copyOfAnotherIndex) {
		this.copyOfAnotherIndex = copyOfAnotherIndex;
	}

	public void byMethod(ActionErrors errors) {
		int check = checkIndex();

		Logging.debug("int return type for classification is : " + check);
		switch (check) {
		case 1:

			Logging
					.debug("Case \"  Is the New Index Child Index of Existing Index ? \"");

			CheckIndexName(errors);
			if (errors.isEmpty())
				parentIndex(errors);
			Logging.debug("2");
			if (errors.isEmpty())
				validateCapturedFrom(errors);
			if (errors.isEmpty())
				validateCommonFields(errors);

			Logging.debug("3");
			break;
		case 2:
			Logging
					.debug("Case \"  Is the New Index Customized version of an Existing Index ?\"");
			CheckIndexName(errors);
			Logging.debug("1");
			if (errors.isEmpty())
				parentIndex(errors);
			Logging.debug("2");
			Logging.debug("2");
			if (errors.isEmpty())
				validateCapturedFrom(errors);
			if (errors.isEmpty())
				validateCommonFields(errors);

			Logging.debug("3");
			break;
		case 3:
			Logging.debug("Case \"  Copy Of Another Index  \"");
			CheckIndexName(errors);
			

			Logging.debug("3 : " + errors.size());
			break;
		case 4:
			Logging.debug("Case \"  Total Returns Index \"");
			CheckIndexName(errors);
			Logging.debug("1");
			parentIndex(errors);
			Logging.debug("2");
			
			if (errors.isEmpty())
				validateCapturedFrom1(errors);
			if (errors.isEmpty())
				validateCommonFields(errors);

			Logging.debug("3");
			break;
		
		case 6:
			Logging.debug("Case \"  Normal Index \"");
			Logging.debug("Captured ))) " + b_isCaptured);
			CheckIndexName(errors);
			Logging.debug("1");
			parentIndex(errors);
			Logging.debug("2");
			if (errors.isEmpty())
				validateCommonFields(errors);
			break;
		default:
			break;

		}
	}

	public void validateCommonFields(ActionErrors errors) {

		alertReject(errors);
		if (errors.isEmpty()) {
			currencyCheckForNormalIndex(errors);

			if (errors.isEmpty()) {
				if ((d_baseDate == null || d_baseDate.trim().equals(""))) {
					Logging.debug("d_baseDate : " + d_baseDate);
					errors.add(null, new ActionError("Error.message.BaseDate"));
					//	Logging.getDebug("Inside if 5");
				}
				if (b_isCaptured != null && b_isCaptured.equals("on")) {
				} else {
					

					Logging.debug("d_baseValue :" + d_baseValue);

				
					}
				}
				if (d_baseValue == null || d_baseValue.trim().equals("")) {
					Logging.debug("d_baseValue : " + d_baseValue);
					errors
							.add(null, new ActionError(
									"Error.message.BaseValue"));
					//	Logging.getDebug("Inside if 5");
				} else {
					try {
						float basevalue = new Float(d_baseValue).floatValue();
						if (basevalue < 0) {
							errors.add(null, new ActionError(
									"Error.message.BaseValue"));
						}
					} catch (Exception e) {
						errors.add(null, new ActionError(
								"Error.message.BaseValue"));
					}
				}
				

			}
			if ((b_isCaptured != null && b_isCaptured.trim().equals("on"))) {
				Logging.debug("s_capturedFrom : " + s_capturedFrom);
				Logging.debug("b_isCaptured : " + b_isCaptured);
				if ((s_capturedFrom != null && s_capturedFrom.trim()
						.equals("0"))) {
					errors.add(null, new ActionError(
							"Error.message.CapturedFrom"));
				}

			}

			if ((s_applyClassification != null && s_applyClassification.trim()
					.equals("y"))) {
				Logging.debug("s_capturedFrom : " + s_applyClassification);
				Logging.debug("s_industryClassificationID : "
						+ s_industryClassificationID);
				if (!s_industryClassificationID.trim().equals("0")) {
					errors.add(null, new ActionError(
							"Error.message.IndustryClassification"));
				}

			}

			if (errors.isEmpty()) {
				boolean flag = compareDate(); //commented to start historic index calculation
				//    boolean flag =true;
				if (!flag) {
					errors.add(null, new ActionError("Error.basedate.Invalid"));
				}
				if (b_isCaptured != null && b_isCaptured.equals("on")) {
				} 
			}
		

	}

	public void parentIndex(ActionErrors errors) {
		Logging.debug(b_isIndexIsChildOrCustomized + s_parentIndex);
		if ((b_isIndexIsChildOrCustomized != null
				&& !b_isIndexIsChildOrCustomized.equals("0") && s_parentIndex
				.equals("0"))) {
			errors.add(null, new ActionError("Error.message.ParentIndex"));
		} else {
			if (s_indexType.equals("4") || s_indexType.equals("5")) {
				if (s_parentIndex.equals("0"))
					errors.add(null,
							new ActionError("index.parent.notselected"));
			}
		}
		if (s_indexType.equals("0")) {
			errors.add(null, new ActionError("Error.message.IndexType"));
		}
	}

	public void CheckIndexName(ActionErrors errors) {
		if (s_indexName == null || s_indexName.trim().equals(""))//||
		// s_indexName.charAt(0)=='
		// ')
		{
			errors.add("s_indexName",
					new ActionError("Error.message.IndexName"));
			//	Logging.getDebug("Inside if 2");
		} else if (s_indexName.length() > 50) {
			errors.add("s_indexName", new ActionError(
					"Error.message.IndexName1"));
		} else {
			boolean flag = QueryClass1. fixedIncomeChkIndexkname(s_indexName);
			Logging.debug("flag for duplicate Index : " + flag);
			if (!flag)
				errors.add("s_indexName", new ActionError(
						"Error.message.SameIndexName"));
		}
	}

	public void alertReject(ActionErrors errors) {
		Logging.debug("alertReject");
		if (s_alertPercent == null || s_alertPercent.trim().equals("")) {
			Logging.debug("s_alertPercent : " + s_alertPercent);
			errors.add(null, new ActionError("Error.message.Alert"));
			//	Logging.getDebug("Inside if 5");
		} else if (s_rejectionPercent == null
				|| s_rejectionPercent.trim().equals("")) {
			//Logging.getDebug("s_rejectionPercent : " + s_rejectionPercent);
			errors.add(null, new ActionError("Error.message.Rejection"));
			//	Logging.getDebug("Inside if 5");
		} else {
			Float alertPercent, rejectionPercent;
			try {
				alertPercent = new Float(s_alertPercent);
				try {
					rejectionPercent = new Float(s_rejectionPercent);
					//Logging.getDebug("s_alertPercent : " + s_alertPercent);
					Logging.debug("s_rejectionPercent : "
							+ s_rejectionPercent);
					float apercent, rpercent;
					apercent = alertPercent.floatValue();
					rpercent = rejectionPercent.floatValue();
					if (apercent <= 0 || apercent >= 100) {
						errors
								.add(null, new ActionError(
										"Error.message.Alert"));
					} else if (rpercent <= 0 || rpercent >= 100) {
						errors.add(null, new ActionError(
								"Error.message.Rejection"));
					} else {
						int result = alertPercent.compareTo(rejectionPercent);
						if (result >= 0) {
							//here
							Logging.debug("result>0 :" + result);
							errors.add(null, new ActionError(
									"Error.message.RejectionCriteria"));
						}
					}

				} catch (NumberFormatException nfe) {
					errors
							.add(null, new ActionError(
									"Error.message.Rejection"));
				}
			} catch (NumberFormatException nfe) {
				errors.add(null, new ActionError("Error.message.Alert"));
			}

		}
	}

	public void currencyCheckForNormalIndex(ActionErrors errors) {
		if (!s_indexType.trim().equals("4")
				&& (s_baseCurrency == null || s_baseCurrency.trim().equals("0"))) {
			Logging.debug("s_baseCurrency : " + s_baseCurrency);
			Logging.debug("adding base currency error2");
			errors.add(null, new ActionError("Error.message.BaseCurrency"));
			//	Logging.getDebug("Inside if 5");
		}
	}

	public void validateCapturedFrom(ActionErrors errors) {
		//Logging.getDebug("b_isCaptured : "+b_isCaptured);
		if (b_isCaptured != null && !b_isCaptured.trim().equals("n"))
			errors.add(null, new ActionError("error.message.capturedchild"));

	}

	public void validateCapturedFrom1(ActionErrors errors) {
		//Logging.getDebug("b_isCaptured : "+b_isCaptured);
		if (b_isCaptured != null)
			errors.add(null, new ActionError("error.message.capturedchild1"));

	}

	/**
	 * @return Returns the b_ShowChild.
	 */
	public String getB_ShowChild() {
		return b_ShowChild;
	}

	/**
	 * @param showChild The b_ShowChild to set.
	 */
	public void setB_ShowChild(String showChild) {
		b_ShowChild = showChild;
	}

	/*public void currencyCheckforTotalReturns(ActionErrors errors) {
		if (s_indexType.equals("4") || s_indexType.equals("5")) {

			//Logging.getDebug("Parent Index Id : "+s_parentIndex);

			ResultSet parentCurrency = new QueryClass1().ReturnParentDetails(
					s_parentIndex, databaseConnection);
			//s_baseCurrency = "66";
			if (parentCurrency == null) {
				errors.add(null, new ActionError("databaseError"));
			} else {

				try {
					parentCurrency.first();
					if (s_indexType.equals("5")) {
						if (s_baseCurrency.trim().equals(
								parentCurrency.getString("base_currency_id"))) {
							errors.add(null, new ActionError(
									"Error.message.BaseCurrency1"));
						}
					} else {
						this.s_baseCurrency = parentCurrency
								.getString("base_currency_id");

					}
					this.s_industryClassificationID = parentCurrency
							.getString("industry_classification_code");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					Logging.getError(" Error : " + e.getMessage());
					// e.printStackTrace();
				}

			}

		}
	}*/

/*	public void classificationonCondition(ActionErrors errors) {
		if ((s_indexType.equals("4") || s_indexType.equals("5"))) {
			if (s_applyClassification != null
					&& s_applyClassification.equals("y")) {
				//Logging.getDebug("Entered classificationonCondition : "+b_isIndexCustomizedVersion +"   :  "+b_isIndexIsChildOrCustomized);
				errors.add(null, new ActionError(
						"Error.message.ClassificationOnCondition"));
			}
			//Logging.getDebug("after type");
			if (b_isIndexIsChildOrCustomized.trim().equals("2")) {
				errors.add(null, new ActionError("customizedchild"));
			}

		}
	}*/

	/**
	 * @return Returns the is_testIndex.
	 */
	public String getIs_testIndex() {
		return is_testIndex;
	}

	/**
	 * @param is_testIndex The is_testIndex to set.
	 */
	public void setIs_testIndex(String is_testIndex) {
		this.is_testIndex = is_testIndex;
	}

	/**
	 * @return Returns the s_parentIndexCollection.
	 */
	public Collection getS_parentIndexCollection() {
		//Logging.getDebug("b_ShowChild  :: "+getB_ShowChild());
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		Properties prop = new Properties();
		Properties prop1 = new Properties();
		Logging.debug("resource url is " + Connect.resourceurl);
		try {
			java.net.URL imgURL = ComputeIndexForm.class
					.getResource("ComputeIndexForm.class");
			//System.out.println("imgURL : " + imgURL);
			String resourcepth = imgURL.toString();
			//System.out.println("resourceurl : " + resourcepth);
			resourcepth = resourcepth.substring(6, (resourcepth
					.lastIndexOf("/WEB-INF/") + 8));
			//System.out.println("resourceurl : " + resourcepth);
			String resourcepth1 = resourcepth
					+ "/classes/resources/user_query.properties";
			prop1.load(new FileInputStream(resourcepth1));
			resourcepth = resourcepth
					+ "/classes/resources/database.properties";
			prop.load(new FileInputStream(resourcepth));
		} catch (Exception ex) {
			Logging.error(" Error : " + ex.getMessage());
		}
		String id1 = null;
		Vector v = new Vector();
	//	AcessControl asc = new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		String NotSelected = asc.getLangValues("Masters.NotSelected");

		try {
			if (connection == null)
				connection = c.getdbConnection();
			String use_user = prop.getProperty("use_user");
			if (use_user != null && use_user.equals("yes")) {
				if (getB_ShowChild() != null
						&& getB_ShowChild().trim().equals("on")) {
					query = prop1.getProperty("index_list_forindexdefination");
				} else {
					query = prop1.getProperty("index_list_withoutchild");
				}
				PreparedStatement pstmt = connection.prepareStatement(query);
				pstmt.setString(1, user_id);
				rs = pstmt.executeQuery();
			} else {
				if (getB_ShowChild() != null
						&& getB_ShowChild().trim().equals("on")) {
					query = ConnectInit.queries
							.getProperty("index_list_forindexdefination");
				} else {
					query = ConnectInit.queries.getProperty("index_list_withoutchild");
				}
				stmt = connection.createStatement();
				rs = stmt.executeQuery(query);
			}
			v.add(new LabelValueBean(NotSelected, "0"));
			while (rs.next()) {
				id1 = rs.getString(1);
				v.add(new LabelValueBean(rs.getString(2), id1));
			}
			if (rs != null)
				rs.close();
			s_parentIndexCollection = v;
		} catch (Exception e) {
			// TODO: handle exception
			Logging.error(" Error : " + e.getMessage());
			//e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error(" Error : " + ex.getMessage());
				}
				Logging.error(" Error : " + ee.getMessage());
			}
		}
		return s_parentIndexCollection;
	}

	/**
	 * @return Returns the s_indexTypeIndexCollection.
	 */
	public Collection getS_indexTypeIndexCollection() {
		query = ConnectInit.queries.getProperty("fixed_income_index_type_list");
		String id1 = null;
		Connection connection = null;
		Statement stmt = null;
		rs = null;
		Vector v = new Vector();
	//	AcessControl asc = new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		String NotSelected = asc.getLangValues("Masters.NotSelected");

		try {
			if (connection == null)
				connection = c.getdbConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			v.add(new LabelValueBean(NotSelected, "0"));
			while (rs.next()) {
				id1 = rs.getString(1);
				v.add(new LabelValueBean(rs.getString(2), id1));
			}
			if (rs != null)
				rs.close();
			s_indexTypeIndexCollection = v;
		} catch (Exception e) {
			// TODO: handle exception
			Logging.error(" Error : " + e.getMessage());
			//e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error(" Error : " + ex.getMessage());
				}
				Logging.error(" Error : " + ee.getMessage());
			}
		}
		return s_indexTypeIndexCollection;
	}

	/**
	 * @param typeIndexCollection The s_indexTypeIndexCollection to set.
	 */
	public void setS_indexTypeIndexCollection(Collection typeIndexCollection) {
		s_indexTypeIndexCollection = typeIndexCollection;
	}

	/**
	 * @param indexCollection The s_parentIndexCollection to set.
	 */
	public void setS_parentIndexCollection(Collection indexCollection) {
		s_parentIndexCollection = indexCollection;
	}

	/**
	 * @return Returns the s_industryClassificationIDCollection.
	 */
	public Collection getS_industryClassificationIDCollection() {
		query = ConnectInit.queries.getProperty("industry_classification_list");
		String id1 = null;
		Statement stmt = null;
		rs = null;
		Connection connection = null;
		Vector v = new Vector();
	//	AcessControl asc = new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		String NotSelected = asc.getLangValues("Masters.NotSelected");

		try {
			if (connection == null)
				connection = c.getdbConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			v.add(new LabelValueBean(NotSelected, "0"));
			while (rs.next()) {
				id1 = rs.getString(1);
				v.add(new LabelValueBean(rs.getString(2), id1));
			}
			if (rs != null)
				rs.close();
			s_industryClassificationIDCollection = v;
		} catch (Exception e) {
			// TODO: handle exception
			Logging.error(" Error : " + e.getMessage());
			//e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error(" Error : " + ex.getMessage());
				}
				Logging.error(" Error : " + ee.getMessage());
			}
		}
		return s_industryClassificationIDCollection;
	}

	/**
	 * @param classificationIDCollection The s_industryClassificationIDCollection to set.
	 */
	public void setS_industryClassificationIDCollection(
			Collection classificationIDCollection) {
		s_industryClassificationIDCollection = classificationIDCollection;
	}

	/**
	 * @return Returns the s_baseCurrencyCollection.
	 */
	public Collection getS_baseCurrencyCollection() {
		query = ConnectInit.queries.getProperty("currency_list");
		String id1 = null;
		Statement stmt = null;
		rs = null;
		Connection connection = null;
		Vector v = new Vector();
	//	AcessControl asc = new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		String NotSelected = asc.getLangValues("Masters.NotSelected");

		try {
			if (connection == null)
				connection = c.getdbConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			v.add(new LabelValueBean(NotSelected, "0"));
			while (rs.next()) {
				id1 = rs.getString(1);
				v.add(new LabelValueBean(rs.getString(2), id1));
			}
			if (rs != null)
				rs.close();
			s_baseCurrencyCollection = v;
		} catch (Exception e) {
			// TODO: handle exception
			Logging.error(" Error : " + e.getMessage());
			//e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error(" Error : " + ex.getMessage());
				}
				Logging.error(" Error : " + ee.getMessage());
			}
		}
		return s_baseCurrencyCollection;

	}

	/**
	 * @param currencyCollection The s_baseCurrencyCollection to set.
	 */
	public void setS_baseCurrencyCollection(Collection currencyCollection) {

		s_baseCurrencyCollection = currencyCollection;
	}

	/**
	 * @return Returns the s_capturedFromCollection.
	 */
	public Collection getS_capturedFromCollection() {
		query = ConnectInit.queries.getProperty("index_feed_provider");
		String id1 = null;
		Statement stmt = null;
		rs = null;
		Connection connection = null;
		Vector v = new Vector();
	//	AcessControl asc = new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		String NotSelected = asc.getLangValues("Masters.NotSelected");

		try {
			if (connection == null)
				connection = c.getdbConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			v.add(new LabelValueBean(NotSelected, "0"));
			while (rs.next()) {
				id1 = rs.getString(1);
				v.add(new LabelValueBean(rs.getString(2), id1));
			}
			if (rs != null)
				rs.close();
			s_capturedFromCollection = v;
		} catch (Exception e) {
			// TODO: handle exception
			Logging.error(" Error : " + e.getMessage());
			//e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error(" Error : " + ex.getMessage());
				}
				Logging.error(" Error : " + ee.getMessage());
			}
		}
		return s_capturedFromCollection;
	}

	/**
	 * @param fromCollection The s_capturedFromCollection to set.
	 */
	public void setS_capturedFromCollection(Collection fromCollection) {
		s_capturedFromCollection = fromCollection;
	}

	/**
	 * @return Returns the submitresult.
	 */
	public String getSubmitresult() {
		return submitresult;
	}

	/**
	 * @param submitresult The submitresult to set.
	 */
	public void setSubmitresult(String submitresult) {
		this.submitresult = null;
	}

	/**
	 * @return Returns the copyparent_before.
	 */
	public String getCopyparent_before() {
		return copyparent_before;
	}

	/**
	 * @param copyparent_before The copyparent_before to set.
	 */
	public void setCopyparent_before(String copyparent_before) {
		this.copyparent_before = copyparent_before;
	}

	protected void finalize() {
		try {
			databaseConnection.close();
			databaseConnection = null;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/*
	public String getIndex_currency() {
		String curr = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = null;
		query = c.queries.getProperty("get_index_currency_code");
		Logging.getDebug("s_baseCurrency is " + s_baseCurrency + " query :"
				+ query);
		try {
			if (connection == null)
				connection = c.getdbConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, s_baseCurrency);
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				curr = resultSet.getString(1);
			}
			if (resultSet != null)
				resultSet.close();
		} catch (Exception e) {
			// TODO: handle exception
			Logging.getError(" Error : " + e.getMessage());
			//e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.getError(" Error : " + ex.getMessage());
				}
				Logging.getError(" Error : " + ee.getMessage());
			}
		}
		Logging.getDebug("currency name is " + curr);
		index_currency = curr;
		return index_currency;
	}

	
	public void setIndex_currency(String index_currency) {
		this.index_currency = index_currency;
	}*/

	public String getSet = "no";

	/**
	 * @return Returns the getSet.
	 */
	public String getGetSet() {
		return getSet;
	}

	/**
	 * @param getSet The getSet to set.
	 */
	public void setGetSet(String getSet) {
		this.getSet = getSet;
	}

	/**
	 * @return Returns the user_id.
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id The user_id to set.
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return Returns the name_list.
	 */
	public String getName_list() {
		return name_list;
	}
	/**
	 * @param name_list The name_list to set.
	 */
	public void setName_list(String name_list) {
		this.name_list = name_list;
	}
	/**
	 * @return Returns the nameListCollection.
	 */
	public Collection getNameListCollection() {
		ResultSet rst;

		Connection con = null;
         Connect connect =ConnectInit.getConnect();
		Vector update = new Vector(50);
		try {
			if (con == null)
				con = connect.getdbConnection();
			PreparedStatement stmt = con.prepareStatement(ConnectInit.queries.getProperty("sel_ind_classification_list"));
			rst = stmt.executeQuery();
			update.add(new LabelValueBean("Not Selected", "0"));

			while (rst.next()) {
				String count = rst.getString(1);
				
				update.add(new LabelValueBean(rst.getString(2),count));
				//map.put(count, rst.getString(2));
			}
			rst.close();
			stmt.close();
		} catch (Exception e) {
			//System.out.println(e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}

		nameListCollection = update;

		return nameListCollection;
		
	}
	/**
	 * @param nameListCollection The nameListCollection to set.
	 */
	public void setNameListCollection(Collection nameListCollection) {
		this.nameListCollection = nameListCollection;
	}
}