package harrier.income.com.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import app.AcessControl;
import app.Connect;

import com.harrier.initializeation.ConnectInit;

public class IndexCalculatorForm extends ActionForm { // ValidatorForm
														// implements
														// Serializable{
	Logger Logging = Logger.getLogger(IndexCalculatorForm.class);
	private String Stock_id, symbol, mcv, user_tmcv, b1, price, indexValue,
			tis, iwf, stock_currency_id, mkt_lot, index_id, indexValueNew,
			indexValueLtp;
	private String currency_id, currency_name, indcurr_tmcv, divisor,
			index_value, role_id1;
	private String tis_value = null;
	private static int count = 1;
	static int srno = 1;
	private String compute;
	private String defaultVal = null;
	private static String tmcv;
	private Collection indexListCollection = null;
	private String opt = " ";
	private ArrayList tableData = new ArrayList();
	private ArrayList tableDataPortpolio = new ArrayList();
	private String hiddenVar = null;
	String priceArray = null;
	ArrayList tableDataNew = new ArrayList();
	private String saveButton = null;
	private String useridc = null;
	private Vector index_cal;

	public String getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(String saveButton) {
		this.saveButton = saveButton;
	}

	public ArrayList getTableDataPortpolio() {
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String symbol = null, ltp = null, mcap = null, shares = null;
		ArrayList temp = new ArrayList();
		String local_id = getIndex_id();
		try {
			if (connection == null)
				connection = con.getdbConnection();
			try {
				String query = ConnectInit.queries
						.getProperty("portpolio_tis_calculator_report");
				Logging.debug("Query   = " + query);
				pst = connection.prepareStatement(query);
				pst.setString(1, local_id);
				pst.setString(2, local_id);
				rs = pst.executeQuery();
				int i = 0;
				while (rs.next()) {
					symbol = rs.getString(7);
					ltp = rs.getString(6);
					mcap = rs.getString(4);
					shares = rs.getString(2);

					PortpolioTisDetails ptd = new PortpolioTisDetails(symbol,
							ltp, mcap, shares);
					temp.add(ptd);
				}
			} catch (Exception e) {
				// TODO: handle exception
				Logging.error(" Error : " + e.getMessage());
			}
		} catch (Exception e) {
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
					Logging.error(" Error : Unable to close Connection "
							+ ex.getMessage());
				}
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}

		tableDataPortpolio = temp;
		return tableDataPortpolio;
	}

	/**
	 * @param tableDataPortpolio
	 *            The tableDataPortpolio to set.
	 */
	public void setTableDataPortpolio(ArrayList tableDataPortpolio) {

		this.tableDataPortpolio = tableDataPortpolio;
	}

	public ArrayList getTableData() {
		IndexCalculatorCollection indexCalculatorCollection = new IndexCalculatorCollection();
		Logging.debug("inside the gettabledata");
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String userPriceN = null;
		String priceArr = "";
		String stk_id = null, symbol = null, price = null, myRates = null;
		ArrayList temp = new ArrayList();
		String local_id = getIndex_id();
		index_cal = new Vector();
		int j = 0, k = 0;
		String srno = null;
		try {
			/*
			 * String indexV=indexCalculatorCollection.dbIndexValue(local_id);
			 * if(indexV!=null) { //setIndexValueNew(indexV); indexValueNew=
			 * indexV; }
			 */
			if (connection == null)
				connection = con.getdbConnection();
			try {
				//
				String query = ConnectInit.queries
						.getProperty("index_calculator_report_price");
				Logging.debug("Query   = " + query);
				pst = connection.prepareStatement(query);
				pst.setString(1, local_id);
				pst.setString(2, local_id);
				rs = pst.executeQuery();
				int i = 0;
				priceArr = getPriceArray();

				while (rs.next()) {

					j = j + 1;
					srno = String.valueOf(j);
					index_cal.add(k, srno);

					k++;
					stk_id = rs.getString(1);
					symbol = rs.getString(2);
					if (symbol != null) {
						String newSymbol = symbol.replace("&", "-");
						index_cal.add(k, newSymbol);
					} else {
						index_cal.add(k, "0");
					}

					k++;
					if (rs.getString(3) != null) {
						price = rs.getString(3);
						index_cal.add(k, price);
					} else {
						price = "0.00";
						index_cal.add(k, price);
					}

					k++;
					if ((priceArr == null) || (priceArr.equals(""))) {
						userPriceN = "";
						index_cal.add(k, price);
					} else {
						userPriceN = indexCalculatorCollection
								.userStockwisePrice(stk_id, priceArr);
						index_cal.add(k, userPriceN);
					}
					k++;

					IndexCalculatorDetails icd = new IndexCalculatorDetails(
							stk_id, symbol, price, userPriceN);
					temp.add(icd);
				}
			} catch (Exception e) {
				// TODO: handle exception
				Logging.error(" Error : " + e.getMessage());
			}
		} catch (Exception e) {
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
					Logging.error(" Error : Unable to close Connection "
							+ ex.getMessage());
				}
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}

		tableData = temp;
		tableDataNew = tableData;
		return tableData;

	}

	public void setTableData(ArrayList tableData) {
		this.tableData = tableData;
	}

	public String getOpt() {
		return opt;
	}

	/**
	 * @param opt
	 *            The opt to set.
	 */
	public void setOpt(String opt) {
		this.opt = opt;
	}

	/**
	 * @return Returns the tis_value.
	 */
	public String getTis_value() {
		if ((IndexCalculatorForm.tmcv) == null
				|| (IndexCalculatorForm.tmcv).equals("")) {
			tis_value = "0.0";
		} else {
			tis_value = IndexCalculatorForm.tmcv;
		}
		return tis_value;
	}

	/**
	 * @param tis_value
	 *            The tis_value to set.
	 */
	public void setTis_value(String tis_value) {
		this.tis_value = tis_value;
	}

	/**
	 * @return Returns the user_mcv.
	 */
	public String getUser_tmcv() {
		return user_tmcv;
	}

	/**
	 * @param user_mcv
	 *            The user_mcv to set.
	 */
	public void setUser_tmcv(String user_mcv) {
		this.user_tmcv = user_tmcv;
	}

	/**
	 * @return Returns the mcv.
	 */
	public String getMcv() {
		return mcv;
	}

	/**
	 * @param mcv
	 *            The mcv to set.
	 */
	public void setMcv(String mcv) {
		this.mcv = mcv;
	}

	/**
	 * @return Returns the stock_id.
	 */
	public String getStock_id() {
		return Stock_id;
	}

	/**
	 * @param stock_id
	 *            The stock_id to set.
	 */
	public void setStock_id(String stock_id) {
		Stock_id = stock_id;
	}

	/**
	 * @return Returns the symbol.
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol
	 *            The symbol to set.
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * @return Returns the tmcv.
	 */
	public static String getTmcv() {
		if (tmcv == null || tmcv.equals("")) {
			tmcv = "0.00";
		}
		return tmcv;
	}

	/**
	 * @param tmcv
	 *            The tmcv to set.
	 */
	public static void setTmcv(String utmcv) {
		IndexCalculatorForm.tmcv = utmcv;
	}

	/**
	 * constructor to set values for Index Value Currency Wise.
	 * 
	 * @param currency_id
	 * @param currency_name
	 * @param tmcv
	 * @param divisor
	 * @param index_value
	 */
	IndexCalculatorForm(String currency_id, String currency_name,
			String indcurr_tmcv, String divisor, String index_value) {
		this.currency_id = currency_id;
		this.currency_name = currency_name;
		this.indcurr_tmcv = indcurr_tmcv;
		this.divisor = divisor;
		this.index_value = index_value;
	}

	/**
	 * costructor to set the values.
	 */
	public IndexCalculatorForm(String id, String qsymbol, String qmcv,
			String qprice) {
		this.Stock_id = id;
		this.symbol = qsymbol;
		this.mcv = qmcv;
		this.price = qprice;
	}

	public IndexCalculatorForm(String stk_id, String qtis, String qiwf,
			String qmcv, String qprice, String qsymbol,
			String qstk_currency_id, String mktlot) {
		this.Stock_id = stk_id;
		this.tis = qtis;
		this.iwf = qiwf;
		this.mcv = qmcv;
		this.price = qprice;
		this.symbol = qsymbol;
		this.stock_currency_id = qstk_currency_id;
		this.mkt_lot = mktlot;
	}

	public IndexCalculatorForm() {

	}

	/**
	 * @return Returns the b1.
	 */
	public String getB1() {
		return b1;
	}

	/**
	 * @param b1
	 *            The b1 to set.
	 */
	public void setB1(String b1) {
		this.b1 = b1;
	}

	/**
	 * @return Returns the price.
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            The price to set.
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return Returns the indexValue.
	 */
	public String getIndexValue() {
		String index_value = "";
		String buttonPressed = b1;
		if (!(buttonPressed != null && buttonPressed.equals("Submit"))) {
			indexValue = index_value;
		}
		return indexValue;
	}

	/**
	 * @param indexValue
	 *            The indexValue to set.
	 */
	public void setIndexValue(String indexValue) {
		this.indexValue = indexValue;
	}

	/**
	 * method to validate field values for index Calculator.
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		addErrors(errors, request);

		return errors;
	}

	/**
	 * Add the validation errors to the error object after validating all the
	 * field values for captured index.
	 */
	public void addErrors(ActionErrors errors, HttpServletRequest request) {
		try {
			int flag = 0;
			// commented by samiksha
			// String chktmcv = request.getParameter("tis_value");
			// added by samiksha starts
			String chktmcv = IndexCalculatorForm.getTmcv();
			System.out.println("Total market cap value 2222 ===="
					+ IndexCalculatorForm.getTmcv());
			// ends
			if (chktmcv != null) {
				if (chktmcv.length() == 0) {
					chktmcv = "0.0";
				}
				// condition commented by samiksha
				// if (ValidateNumbertmcv(chktmcv) == false) {
				// errors.add(null, new ActionError("Error.message.TmcvValue"));
				// }
				// Logging.getDebug("size hashtable in form bean class : "+IndexCalculatorCollection.table.size());
				Enumeration e = IndexCalculatorCollection.table.keys();
				while (e.hasMoreElements()) {
					String id = e.nextElement().toString();
					IndexCalculatorForm rs = (IndexCalculatorForm) IndexCalculatorCollection.table
							.get(id);
					String ovalue = "uprice:" + rs.getStock_id();
					ovalue = request.getParameter(ovalue);
					if (ValidateNumbertmcv(ovalue) == false) {
						flag = 1;
						break;
					}
				}
				if (flag == 1) {
					errors.add(null, new ActionError("Error.message.UserPrice"));
				}
			}

		} catch (Exception e) {
			Logging.error("Error while Validating :" + e.getMessage());
		}
	}

	/**
	 * method to validate for the number.
	 */
	private boolean ValidateNumbertmcv(String str) {
		if (str == null || str.trim().equals("")) {
			return true;
		} else {
			for (int i = 0; i < str.length(); i++) {
				if (Character.isLetter(str.charAt(i))) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * method to validate for the number.
	 */
	private boolean ValidateNumber(String str) {
		if (str == null || str.trim().equals("")) {

		} else {
			for (int i = 0; i < str.length(); i++) {
				if (Character.isLetter(str.charAt(i))) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * @return Returns the iwf.
	 */
	public String getIwf() {
		return iwf;
	}

	/**
	 * @param iwf
	 *            The iwf to set.
	 */
	public void setIwf(String iwf) {
		this.iwf = iwf;
	}

	/**
	 * @return Returns the stock_currency_id.
	 */
	public String getStock_currency_id() {
		return stock_currency_id;
	}

	/**
	 * @param stock_currency_id
	 *            The stock_currency_id to set.
	 */
	public void setStock_currency_id(String stock_currency_id) {
		this.stock_currency_id = stock_currency_id;
	}

	/**
	 * @return Returns the tis.
	 */
	public String getTis() {
		return tis;
	}

	/**
	 * @param tis
	 *            The tis to set.
	 */
	public void setTis(String tis) {
		this.tis = tis;
	}

	/**
	 * @return Returns the mkt_lot.
	 */
	public String getMkt_lot() {
		return mkt_lot;
	}

	/**
	 * @param mkt_lot
	 *            The mkt_lot to set.
	 */
	public void setMkt_lot(String mkt_lot) {
		this.mkt_lot = mkt_lot;
	}

	/**
	 * @return Returns the index_id.
	 */
	public String getIndex_id() {
		Logging.debug("Inside the getIndex_id method ");
		PortpolioTisDetails.setSrno(0);
		IndexCalculatorDetails.setSerialNo(0);
		if (index_id == null || index_id.equals("0")) {
			index_id = "1";
		}
		return index_id;
	}

	/**
	 * @param index_id
	 *            The index_id to set.
	 */
	public void setIndex_id(String index_id) {
		this.index_id = index_id;
	}

	/**
	 * @param indexListCollection
	 *            The indexListCollection to set.
	 */
	public void setIndexListCollection(Collection IndexListCollection) {
		indexListCollection = IndexListCollection;
	}

	/**
	 * @return Returns the indexListCollection.
	 */
	public Collection getIndexListCollection() {
		IndexComposeReportMethod Icr = new IndexComposeReportMethod();
		Vector indexList = new Vector();
		ResultSet rst = null;
		// app.Connect con=new app.Connect();
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		// AcessControl asc=new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		String NotSelected = asc.getLangValues("Masters.NotSelected");
		Logging.debug(" Inside getIndexListCollection(): Not Selected ="
				+ NotSelected);
		if (connection == null) {
			connection = con.getdbConnection();
		}
		if (indexListCollection == null) {

			try {

				String userIds = getUseridc();
				PreparedStatement stmt = connection
						.prepareStatement(ConnectInit.queries
								.getProperty("index_list_without_child"));
				stmt.setString(1, userIds);
				rst = stmt.executeQuery();

				indexList.add(new LabelValueBean(NotSelected, "0"));// commented
																	// by lokesh
																	// 9/04/07
				while (rst.next()) {
					String count = rst.getString(1);
					indexList.add(new LabelValueBean(rst.getString(2), count));

					// commented by Samiksha
					// if (count.equalsIgnoreCase("3252")) {
					// // Logging.debug("In HArrier");
					// setIndex_id(count);
					// }
				}

				int role_id2 = Integer.parseInt(role_id1);
				if (role_id2 != 1) {
					ResultSet rbs = Icr.benchindecolection(connection,
							"index_list_without_child_bench");
					while (rbs.next()) {
						String id1 = rbs.getString(1);
						indexList
								.add(new LabelValueBean(rbs.getString(2), id1));

					}
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				Logging.debug(e);
			}

			indexListCollection = indexList;
		}
		return indexListCollection;
	}

	/**
	 * @return Returns the currency_id.
	 */
	public String getCurrency_id() {
		return currency_id;
	}

	/**
	 * @param currency_id
	 *            The currency_id to set.
	 */
	public void setCurrency_id(String currency_id) {
		this.currency_id = currency_id;
	}

	/**
	 * @return Returns the currency_name.
	 */
	public String getCurrency_name() {
		return currency_name;
	}

	/**
	 * @param currency_name
	 *            The currency_name to set.
	 */
	public void setCurrency_name(String currency_name) {
		this.currency_name = currency_name;
	}

	/**
	 * @return Returns the divisor.
	 */
	public String getDivisor() {
		return divisor;
	}

	/**
	 * @param divisor
	 *            The divisor to set.
	 */
	public void setDivisor(String divisor) {
		this.divisor = divisor;
	}

	/**
	 * @return Returns the indcurr_tmcv.
	 */
	public String getIndcurr_tmcv() {
		return indcurr_tmcv;
	}

	/**
	 * @param indcurr_tmcv
	 *            The indcurr_tmcv to set.
	 */
	public void setIndcurr_tmcv(String indcurr_tmcv) {
		this.indcurr_tmcv = indcurr_tmcv;
	}

	/**
	 * @return Returns the index_value.
	 */
	public String getIndex_value() {
		return index_value;
	}

	/**
	 * @param index_value
	 *            The index_value to set.
	 */
	public void setIndex_value(String index_value) {
		this.index_value = index_value;
	}

	/**
	 * @return Returns the tableDataPortpolio.
	 */

	/**
	 * @return Returns the count.
	 */
	public static int getCount() {
		return count++;
	}

	/**
	 * @param count
	 *            The count to set.
	 */
	public static void setCount(int count) {
		IndexCalculatorForm.count = count;
	}

	/**
	 * @return Returns the srno.
	 */
	public static int getSrno() {
		srno++;
		return srno;
	}

	/**
	 * @param srno
	 *            The srno to set.
	 */
	public static void setSrno(int srno) {
		IndexCalculatorForm.srno = srno;
	}

	/**
	 * @return Returns the hiddenVar.
	 */
	public String getHiddenVar() {
		return hiddenVar;
	}

	/**
	 * @param hiddenVar
	 *            The hiddenVar to set.
	 */
	public void setHiddenVar(String hiddenVar) {
		this.hiddenVar = hiddenVar;
	}

	/**
	 * @return Returns the compute.
	 */
	public String getCompute() {
		return compute;
	}

	/**
	 * @param compute
	 *            The compute to set.
	 */
	public void setCompute(String compute) {
		this.compute = compute;
	}

	/**
	 * @return Returns the defaultVal.
	 */
	public String getDefaultVal() {
		return defaultVal;
	}

	/**
	 * @param defaultVal
	 *            The defaultVal to set.
	 */
	public void setDefaultVal(String defaultVal) {
		this.defaultVal = defaultVal;
	}

	public String getPriceArray() {
		return priceArray;
	}

	public void setPriceArray(String priceArray) {
		this.priceArray = priceArray;
	}

	public String getIndexValueNew() {
		return indexValueNew;
	}

	public void setIndexValueNew(String indexValueNew) {
		this.indexValueNew = indexValueNew;
	}

	public ArrayList getTableDataNew() {
		return tableDataNew;
	}

	public void setTableDataNew(ArrayList tableDataNew) {
		this.tableDataNew = tableDataNew;
	}

	public String getUseridc() {
		return useridc;
	}

	public void setUseridc(String useridc) {
		this.useridc = useridc;
	}

	public Vector getIndex_cal() {
		return index_cal;
	}

	public void setIndex_cal(Vector index_cal) {
		this.index_cal = index_cal;
	}

	public String getIndexValueLtp() {
		return indexValueLtp;
	}

	public void setIndexValueLtp(String indexValueLtp) {
		this.indexValueLtp = indexValueLtp;
	}

	public String getRole_id1() {
		return role_id1;
	}

	public void setRole_id1(String role_id1) {
		this.role_id1 = role_id1;
	}

}
