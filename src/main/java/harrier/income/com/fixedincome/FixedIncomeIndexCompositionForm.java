/*
 * Created on Jun 12, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.fixedincome;

/**
 * @author neha
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;
import org.jfree.chart.demo.servlet.AdjustDecimal;

import app.Connect;

import com.harrier.initializeation.ConnectInit;


public class FixedIncomeIndexCompositionForm extends ActionForm {
	Logger Logging = Logger.getLogger(FixedIncomeIndexCompositionForm.class);
	String exchange, b_isIndexIsChildOrCustomized, exchangeflag, baseDate,
			 indexId, indexType, parentIndexId,mcap_iwf_check = "no",
			operation, fromCreate, indexTypebackup, from = "ICompose",
			 checktocreatechild, letter = null;

	long totalStocks;
  
	public String totalMCV = "0.0";
	int stkidcmp=0;
	String stknamecmp=null;
     String stockID=null,stockName=null,hv1=null;
     private boolean marked=false;
	/**
	 * @return Returns the tabledata.
	 */
	public ArrayList getTabledata() {
		return tabledata;
	}
	/**
	 * @param tabledata The tabledata to set.
	 */
	public void setTabledata(ArrayList tabledata) {
		this.tabledata = tabledata;
	}
     ArrayList tabledata =new ArrayList();
	
	
     /**RESEST ALL FORM FIELDS**/
	  public void reset(ActionMapping mapping,HttpServletRequest request){
	  	
	  	   setStockID("0");
	  	   setStockName("0");
	  	 marked= false;
	  }
	/**
	 * @return Returns the stockID.
	 */
	public String getStockID() {
		return stockID;
	}
	/**
	 * @param stockID The stockID to set.
	 */
	public void setStockID(String stockID) {
		this.stockID = stockID;
	}
	/**
	 * @return Returns the stockName.
	 */
	public String getStockName() {
		return stockName;
	}
	/**
	 * @param stockName The stockName to set.
	 */
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String ParentCurrencyId;
     public String cmpbutton=null;
	/**
	 * @return Returns the cmpbutton.
	 */
	public String getCmpbutton() {
		return cmpbutton;
	}
	/**
	 * @param cmpbutton The cmpbutton to set.
	 */
	public void setCmpbutton(String cmpbutton) {
		this.cmpbutton = cmpbutton;
	}
	/**
	 * @return Returns the totalStocks.
	 */
	public long getTotalStocks() {
		long stocks = 0, count = 0;
		for (Enumeration e = destinationTable.keys(); e.hasMoreElements();) {
			String id = e.nextElement().toString();
			StockDetails rs = (StockDetails) destinationTable.get(id);
			stocks += rs.getOutStanding();
			count++;
		}
		//Logging.getDebug("no of stocks " + stocks + " count " + count);
		return count;
	}

	/**
	 * @param totalStocks
	 *            The totalStocks to set.
	 */
	public void setTotalStocks(long totalStocks) {
		this.totalStocks = totalStocks;
	}

	Collection exchangeCollection;

	public static Hashtable sourceTable = new Hashtable();

	Hashtable destinationTable = new Hashtable();

	Hashtable importHashtable = new Hashtable();
	
	Hashtable  cmptable=new Hashtable();
    Hashtable cmpmaindata =new Hashtable();
	/**
	 * @return Returns the cmptable.
	 */
	public Hashtable getCmptable() {
		return cmptable;
	}
	/**
	 * @param cmptable The cmptable to set.
	 */
	public void setCmptable(Hashtable cmptable) {
		this.cmptable = cmptable;
	}
	/**
	 * @return Returns the importHashtable.
	 */
	public Hashtable getImportHashtable() {
		//Logging.getDebug("returning source table with imported hashtable");
		return importHashtable;
	}

	/**
	 * @param importHashtable
	 *            The importHashtable to set.
	 */
	public void setImportHashtable(Hashtable importHashtable) {
		//Logging.getDebug("Filling source table with imported hashtable : "		+ importHashtable.size());
		this.sourceTable.clear();
		this.sourceTable.putAll(importHashtable);
		this.importHashtable = importHashtable;
	}

	String query;

	Connect c = ConnectInit.getConnect();

	Connection dbcon = null;

	StringBuffer sourcetableString, destinationtableString;

	String[] sourceids, destinationids;

	/**
	 * @return Returns the destinationids.
	 */
	public String[] getDestinationids() {
		return destinationids;
	}

	/**
	 * @param destinationids
	 *            The destinationids to set.
	 */
	public void setDestinationids(String[] destinationids) {
		this.destinationids = destinationids;
	}

	/**
	 * @return Returns the sourceids.
	 */
	public String[] getSourceids() {
		return sourceids;
	}

	/**
	 * @param sourceids
	 *            The sourceids to set.
	 */
	public void setSourceids(String[] sourceids) {
		this.sourceids = sourceids;
	}

	/**
	 * @return Returns the fromCreate.
	 */
	public String getFromCreate() {
		return fromCreate;
	}

	/**
	 * @param fromCreate
	 *            The fromCreate to set.
	 */
	public void setFromCreate(String fromCreate) {
		if (fromCreate.equals("yes")) {
			
			if (b_isIndexIsChildOrCustomized != null
					&& (b_isIndexIsChildOrCustomized.trim().equals("2"))) {
				exchange = "0";
			}
			sourceTable.clear();
			destinationTable.clear();
			blanksourcetable();
			blankDestinationTable();
			try {
				fillSourceTable(this);
				if (sourceTable.size() > 0)
					sourcetableString = new harrier.income.com.fixedincome.FillTables().fixedIncomeAddRowsInTable(sourceTable, letter);
			} catch (Exception e) {
				//Logging.getError(" Error : " + e.getMessage());
				// TODO: handle exception
			}
		}
	}

	protected Logger logger;

	ResultSet rs = null;

	/**
	 *  
	 */
	public FixedIncomeIndexCompositionForm() {
		super();

		//    con = c.getConnection();
		//    c.getConnectionForTransaction();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns the exchange.
	 */
	public String getExchange() {
		if (exchange == null || exchange.equals("null") || exchange.equals("")) {
			if (b_isIndexIsChildOrCustomized == null) {
				exchangeflag = "yes";
				try {
					Connect c = ConnectInit.getConnect();
					
					ResultSet rsconfig = dbcon.createStatement().executeQuery(ConnectInit.queries.getProperty("get_exc_country_curr_from_cnfig")); //get
																							  // exchange_id,country_id,currency_id
																							  // from
																							  // system
																							  // configuration
					while (rsconfig.next()) {
						
						exchange = (String) rsconfig.getString(1);
						
					}																	  // table.
					//Logging.getDebug(" rsconfig " + rsconfig.next());
				//exchange = (String) rsconfig.getString(1);
					Logging.debug("exchange is " + exchange);
//				
				} catch (Exception e) {
					Logging.debug("Error :" + e.getMessage());
					//Logging.getError("Error :" + e.getMessage());
//					
				  }
			}
		}
		return exchange;
	}

	/**
	 * @param exchange
	 *            The exchange to set.
	 */
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	/**
	 * @return Returns the exchangeCollection.
	 */
	/**
	 * @return
	 */
	public Collection getExchangeCollection() {
		String id1 = null;
		query = ConnectInit.queries.getProperty("stock_exchange_list");
		Vector v = new Vector();
		try {
			Statement stmt = dbcon.createStatement();
			rs = stmt.executeQuery(query);
			v.add(new LabelValueBean("Not Selected", "0"));
			while (rs.next()) {
				id1 = rs.getString(1);
				v.add(new LabelValueBean(rs.getString(2), id1));
			}
			exchangeCollection = v;
		} catch (Exception e) {
			// TODO: handle exception
			//Logging.getError(" Error : " + e.getMessage());
		}

		return exchangeCollection;
	}

	/**
	 * @param exchangeCollection
	 *            The exchangeCollection to set.
	 */
	public void setExchangeCollection(Collection exchangeCollection) {
		this.exchangeCollection = exchangeCollection;
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

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		//Logging.getDebug("Validation method called : " + sourceTable.size());
		String letter11 = request.getParameter("letter");
		//Logging.getDebug("letter in validate is " + letter11);
		sourceids = request.getParameterValues("stockid");
		destinationids = request.getParameterValues("stockid1");
		//destinationids=request.getParameterValues("");
		ActionErrors actionErrors = new ActionErrors();

		try {
			if (exchangeflag.equals("yes"))
				fillSourceTableexchangeWise(this);
		} catch (Exception e) {
			//Logging.getError(" Error : " + e.getMessage());
			// TODO: handle exception
		}
		//construct tables
		HttpSession session = request.getSession();
		if (session.getAttribute("indexTypesession") != null
				&& ((String) session.getAttribute("indexTypesession")).length() > 0) {
			indexTypebackup = new String((String) session
					.getAttribute("indexTypesession"));
		}
		//Logging.getDebug("indexTypebackup is " + indexTypebackup);
		if (!operation.equals("-999")) {
			if (operation.equalsIgnoreCase("add")) {
				if (sourceids != null && sourceids.length > 0) {
					mcap_iwf_check = new harrier.income.com.fixedincome.FillTables()
							.removeStocksFromSourceTable(sourceids,
									indexTypebackup, sourceTable,
									destinationTable);
				}
				
			} else if (operation.equalsIgnoreCase("Remove")) {
				if (destinationids != null && destinationids.length > 0) {
					new harrier.income.com.fixedincome.FillTables().addStocksInSourceTable(destinationids,
							sourceTable, destinationTable);
				}
			} else if (operation.equalsIgnoreCase("Submit")) {
				if (indexTypebackup.equals("2")) {
					new harrier.income.com.fixedincome.FillTables().validateIWF(destinationTable, request,
							actionErrors);
					if (!actionErrors.isEmpty()) {
						return actionErrors;
					}
				}
				setTotalMCV();
				if (totalStocks <= 0) {
					actionErrors.add(null, new ActionError(
							"indexcompose.zerostocks"));
					return actionErrors;
				}
				if (new Double(totalMCV).doubleValue() <= 0) {
					actionErrors.add(null, new ActionError(
							"indexcompose.zeromcv"));
					return actionErrors;
				}
			}
		}

		setTotalMCV();
		if (sourceTable.size() > 0) {
			//Logging.getDebug("letter before calling add method of filltable "+ letter11);
			//sourcetableString = new
			// FillTables().addRowsInTable(sourceTable,letter11);
		} else {
			blanksourcetable();
		}
		if (destinationTable.size() > 0) {
			//if (destinationTable.size() > 0) {
			destinationtableString = new harrier.income.com.fixedincome.FillTables().fixedIncomeAddRowsInSecondTable(
					destinationTable, indexTypebackup, request);
			// }
		} else {
			blankDestinationTable();
		}
		return actionErrors;
	}

	public void blanksourcetable() {
		this.sourcetableString = new StringBuffer(
				"<tr><td width=\"7%\" align=\"center\">&nbsp;</td><td width=\"24%\" align=\"center\"><p>&nbsp;</p></td><td width=\"9%\" align=\"center\">&nbsp;</td><td width=\"9%\" align=\"center\">&nbsp;</td><td width=\"8%\" align=\"center\">&nbsp;</td><td width=\"11%\" align=\"center\">&nbsp;</td><td width=\"11%\" align=\"center\">&nbsp;</td><td width=\"11%\" align=\"center\">&nbsp;</td></tr>");

	}

	public void blankDestinationTable() {
		this.destinationtableString = new StringBuffer(
				"<tr><td width=\"5%\" align=\"center\">&nbsp;</td><td width=\"16%\" align=\"center\">&nbsp;</td><td width=\"9%\" align=\"center\"><font size=\"1\" face=\"Arial\"><input type=\"text\" name=\"T1\" size=\"11\"></font></td><td width=\"10%\" align=\"center\">&nbsp;</td><td width=\"12%\" align=\"center\">&nbsp;</td><td width=\"13%\" align=\"center\">&nbsp;</td><td width=\"11%\" align=\"center\">&nbsp;</td><td width=\"10%\" align=\"center\">&nbsp;</td><td width=\"26%\" align=\"center\">&nbsp;</td></tr>");
	}

	/**
	 * @return Returns the exchangeflag.
	 */
	public String getExchangeflag() {
		return exchangeflag;
	}

	/**
	 * @param exchangeflag
	 *            The exchangeflag to set.
	 */
	public void setExchangeflag(String exchangeflag) {
		this.exchangeflag = exchangeflag;
	}

	/**
	 * @return Returns the baseDate.
	 */
	public String getBaseDate() {
		return baseDate;
	}

	/**
	 * @param baseDate
	 *            The baseDate to set.
	 */
	public void setBaseDate(String baseDate) {
		this.baseDate = baseDate;
	}

	/**
	 * @return Returns the indexId.
	 */
	public String getIndexId() {
		return indexId;
	}

	/**
	 * @param indexId
	 *            The indexId to set.
	 */
	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}

	/**
	 * @return Returns the indexType.
	 */
	public String getIndexType() {
		return indexType;
	}

	/**
	 * @param indexType
	 *            The indexType to set.
	 */
	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}

	/**
	 * @return Returns the parentIndexId.
	 */
	public String getParentIndexId() {
		return parentIndexId;
	}

	/**
	 * @param parentIndexId
	 *            The parentIndexId to set.
	 */
	public void setParentIndexId(String parentIndexId) {
		this.parentIndexId = parentIndexId;
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

	public void fillSourceTable(FixedIncomeIndexCompositionForm compositionForm)
			throws SQLException {
		PreparedStatement preparedStatement = null;
		Connect c = ConnectInit.getConnect();
		ResultSet resultSet;
		//select proper query
		if (compositionForm.getB_isIndexIsChildOrCustomized() != null
				&& compositionForm.getB_isIndexIsChildOrCustomized().trim()
						.equals("2")) {
			if (compositionForm.getExchange() != null
					&& !compositionForm.getExchange().trim().equals("0")) {
				//for customized index when a Exchange is selected

				String query = ConnectInit.queries
						.getProperty("select_mcap_for_fixed_income_index_composition");
				try {
					preparedStatement = Connect.con.prepareStatement(query);
					preparedStatement.setString(1, compositionForm
							.getExchange());
					preparedStatement.setString(2, compositionForm
							.getBaseDate());

				} catch (Exception e) {
				//	Logging.getError(" Error : " + e.getMessage());
					// TODO: handle exception
				}
			} else {
				//				  for customized index when came from new index define

				String query = ConnectInit.queries
						.getProperty("select_mcap_for_child_index_composition");
				try {
					preparedStatement = dbcon.prepareStatement(query);
					preparedStatement.setString(1, compositionForm
							.getParentIndexId());
					preparedStatement.setString(2, compositionForm
							.getBaseDate());

				} catch (Exception e) {
					//Logging.getError(" Error : " + e.getMessage());
					// TODO: handle exception
				}
			}
		} else if (compositionForm.getB_isIndexIsChildOrCustomized() != null
				&& compositionForm.getB_isIndexIsChildOrCustomized().trim()
						.equals("1")) {

			String query = ConnectInit.queries
					.getProperty("select_mcap_for_child_index_composition");
			try {
				preparedStatement = dbcon.prepareStatement(query);
				preparedStatement.setString(1, compositionForm
						.getParentIndexId());
				preparedStatement.setString(2, compositionForm.getBaseDate());

			} catch (Exception e) {
			//	Logging.getError(" Error : " + e.getMessage());
				Logging.debug(" Error : " + e.getMessage());
				// TODO: handle exception
			}

		} else if (compositionForm.getB_isIndexIsChildOrCustomized() == null
				&& compositionForm.getExchange() != null) {
			//child of Index

			String query = ConnectInit.queries
					.getProperty("select_mcap_for_fixed_income_index_composition");

			try {
				preparedStatement = dbcon.prepareStatement(query);

				preparedStatement.setString(1, compositionForm.getExchange());
				preparedStatement.setString(2, compositionForm.getBaseDate());

			} catch (Exception e) {
				//Logging.getError(" Error : " + e.getMessage());
				Logging.debug(" Error : " + e.getMessage());
				// TODO: handle exception
			}
		} else {
			//Logging.getDebug("Number of entries : 0");
			return;
		}

		//Logging.getDebug("preparedStatement  : " + preparedStatement);
		resultSet = preparedStatement.executeQuery();
		if (resultSet != null) {
			int i = 0;
			double iwf, ltp;
			long tis, market_lot;
			String stk_id;
			Hashtable hashtable = new Hashtable();
			while (resultSet.next()) {

				++i;
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				iwf = resultSet.getDouble(3);
				String date = resultSet.getString(4);
				ltp = resultSet.getDouble(5);
				String currency = resultSet.getString(6);
				tis = resultSet.getLong(7);
				market_lot = resultSet.getLong(8);
				stk_id = resultSet.getString(9);
				hashtable.put(String.valueOf(id), new StockDetails(id, name,
						iwf, ltp, currency, tis, market_lot, date, stk_id));
			}
		//	Logging.getDebug("Number of entries : " + hashtable.size());
			sourceTable.putAll(hashtable);
		}
	}

	public void fillSourceTableexchangeWise(FixedIncomeIndexCompositionForm compositionForm)
			throws SQLException {
		PreparedStatement preparedStatement = null;
		Connect c = ConnectInit.getConnect();
		
		ResultSet resultSet;
		if (compositionForm.getExchange() != null) {
			//child of Index

			String query = ConnectInit.queries.getProperty("select_mcap_for_fixed_income_index_composition");
       
			Logging.debug("For Test "+query);
			try {
				preparedStatement = dbcon.prepareStatement(query);

				preparedStatement.setString(1, compositionForm.getExchange());
				preparedStatement.setString(2, compositionForm.getBaseDate());

			} catch (Exception e) {
				Logging.debug(" Error : " + e.getMessage());
//				Logging.getError(" Error : " + e.getMessage());
				// TODO: handle exception
			}
		} else {
			return;
		}

		//add into hashtable
		//Logging.getDebug("preparedStatement  : " + preparedStatement);
		resultSet = preparedStatement.executeQuery();
		if (resultSet != null) {
			int i = 0;
			double iwf, ltp;
			long tis, market_lot;
			String stk_id;
			Hashtable hashtable = new Hashtable();
			while (resultSet.next()) {

				++i;
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				iwf = resultSet.getDouble(3);
				String date = resultSet.getString(4);
				ltp = resultSet.getDouble(5);
				String currency = resultSet.getString(6);
				tis = resultSet.getLong(7);
				market_lot = resultSet.getLong(8);
				stk_id = resultSet.getString(9);
				hashtable.put(String.valueOf(id), new StockDetails(id, name,
						iwf, ltp, currency, tis, market_lot, date, stk_id));
			}
			//Logging.getDebug("2Number of entries : " + hashtable.size());
			sourceTable.clear();
			sourceTable.putAll(hashtable);
		}
	}

	/**
	 * @return Returns the destinationTable.
	 */
	public Hashtable getDestinationTable() {
		return destinationTable;
	}

	/**
	 * @param destinationTable
	 *            The destinationTable to set.
	 */
	public void setDestinationTable(Hashtable destinationTable) {
		this.destinationTable = destinationTable;
	}

	/**
	 * @return Returns the destinationtableString.
	 */
	public StringBuffer getDestinationtableString() {
		return destinationtableString;
	}

	/**
	 * @param destinationtableString
	 *            The destinationtableString to set.
	 */
	public void setDestinationtableString(StringBuffer destinationtableString) {
		this.destinationtableString = destinationtableString;
	}

	/**
	 * @return Returns the sourceTable.
	 */
	public Hashtable getSourceTable() {
		return sourceTable;
	}

	/**
	 * @param sourceTable
	 *            The sourceTable to set.
	 */
	public void setSourceTable(Hashtable sourceTable) {
		this.sourceTable = sourceTable;
	}

	/**
	 * @return Returns the sourcetableString.
	 */
	public StringBuffer getSourcetableString(String sortletter) {
		//String sortletter=(String)new Character(sortchar).toString();
		if (sortletter == null) {
			this.letter = null;
		} else {
			this.letter = sortletter;
		}
		return sourcetableString;
	}

	/**
	 * @param sourcetableString
	 *            The sourcetableString to set.
	 */
	public void setSourcetableString(StringBuffer sourcetableString) {
		this.sourcetableString = sourcetableString;
	}

	/**
	 * @return Returns the from.
	 */
	public String getFrom() {
		return "ICompose";
	}

	/**
	 * @param from
	 *            The from to set.
	 */
	public void setFrom(String from) {
		this.from = "ICompose";
	}

	public String getTotalMCV() {
		return totalMCV;
	}

	public void setTotalMCV() {
		double total = 0;
		int typeindex;
		if (indexType != null && indexType.trim().equals("1")) {
			typeindex = 1;
			;
		} else {
			typeindex = 2;
			;
		}
		for (Enumeration e = destinationTable.keys(); e.hasMoreElements();) {
			String id = e.nextElement().toString();
			StockDetails rs = (StockDetails) destinationTable.get(id);
			//pass indexcurrency id
			double mcv = rs.getMktCapital1(typeindex, ParentCurrencyId, rs
					.getCurrencyId());
			total += mcv;
			//Logging.getDebug("mcv is " + mcv);
			Logging.debug("mcv is " + mcv);
		//	Logging.getDebug("total tmcv is " + total);
			Logging.debug("total tmcv is " + total);
			rs.setTotalMktCapital(total);
		}
//		org.jfree.chart.demo.servlet.AdjustDecimal ad = new org.jfree.chart.demo.servlet.AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		//this.totalMCV=new Double(ad.shareholdingpatt(new
		// Double(total).toString())).doubleValue();
		this.totalMCV = ad.shareholdingpatt(new Double(total).toString());
	}

	/*
	public String getApplyclassification() {
		return applyclassification;
	}

	
	public void setApplyclassification(String applyclassification) {
		this.applyclassification = applyclassification;
	}
*/
	/**
	 * @return Returns the checktocreatechild.
	 */
	public String getChecktocreatechild() {
		return checktocreatechild;
	}

	/**
	 * @param checktocreatechild
	 *            The checktocreatechild to set.
	 */
	public void setChecktocreatechild(String checktocreatechild) {
		this.checktocreatechild = checktocreatechild;
	}

	/**
	 * @return Returns the dbcon.
	 */
	public Connection getDbcon() {
		return dbcon;
	}

	/**
	 * @param dbcon
	 *            The dbcon to set.
	 */
	public void setDbcon(Connection dbcon) {
		this.dbcon = dbcon;
	}

	/**
	 * @return Returns the parentCurrencyId.
	 */
	public String getParentCurrencyId() {
		return ParentCurrencyId;
	}

	/**
	 * @param parentCurrencyId
	 *            The parentCurrencyId to set.
	 */
	public void setParentCurrencyId(String parentCurrencyId) {
		ParentCurrencyId = parentCurrencyId;
	}

	/**
	 * @return Returns the letter.
	 */
	public String getLetter() {
		return letter;
	}

	/**
	 * @param letter
	 *            The letter to set.
	 */
	public void setLetter(String letter) {
		this.letter = letter;
	}

	
	public String getMcap_iwf_check() {
		return mcap_iwf_check;
	}

	
	public void setMcap_iwf_check(String mcap_iwf_check) {
		this.mcap_iwf_check = mcap_iwf_check;
	}
	
	// code added by neha for index compliance 
	public Hashtable getcmpdata(){
      	Hashtable destinationTable = getDestinationTable();
		if (!destinationTable.isEmpty()) {
			//Logging.getDebug("Inside isEmpty  " + destinationTable.isEmpty());
			for (Enumeration e = destinationTable.keys(); e.hasMoreElements();) {
		
		               String id = e.nextElement().toString();
		               StockDetails sd = (StockDetails) destinationTable.get(id);
	
	                  stkidcmp =sd.getStockID();
	                  
		               stknamecmp=sd.getStockName(); 
		               cmptable.put(""+stkidcmp,stknamecmp);
		                Logging.debug("stocki  for cmp"+stkidcmp);
		                Logging.debug("stockn  for cmp"+stknamecmp);
			         }
			
		           }
		          return cmptable;
                 }
	
	
	
	
	
	
	
	/**
	 * @return Returns the cmpmaindata.
	 */
	public Hashtable getCmpmaindata() {
		return cmpmaindata;
	}
	/**
	 * @param cmpmaindata The cmpmaindata to set.
	 */
	public void setCmpmaindata(Hashtable cmpmaindata) {
		this.cmpmaindata = cmpmaindata;
	}
	/**
	 * @return Returns the hv1.
	 */
	public String getHv1() {
		return hv1;
	}
	/**
	 * @param hv1 The hv1 to set.
	 */
	public void setHv1(String hv1) {
		this.hv1 = hv1;
	}
	/**
	 * @return Returns the marked.
	 */
	public boolean isMarked() {
		return marked;
	}
	/**
	 * @param marked The marked to set.
	 */
	public void setMarked(boolean marked) {
		this.marked = marked;
	}
}