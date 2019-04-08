/**
 * @author Ashish
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import app.AcessControl;
import app.Connect;

import com.harrier.initializeation.ConnectInit;

public class IndexCurrencyWiseForm extends ActionForm {
	Logger Logging = Logger.getLogger(IndexCurrencyWiseForm.class);
	private String from = null, go = null, clear = null, to = null,
			defaultVal = null, check = null, checkChart = null,
			selectIndex = null, selectStock = null, text = null, val = null;
	private Collection IndexCollection = null;
//	AdjustDecimal ad = new AdjustDecimal();
	String b1, graph, indexName, indexValue, currency_id, b_showChild, query,
			index, lastclosingvalue, cdate = null, hist_Date, CompareDate,
			compute;
	String index_id = "3252", userid1;
	IndexComposeReportMethod Icr = new IndexComposeReportMethod();
	Connection connection;
	// stockDetails sdf;
	private ResultSet rst;

	private Collection indexListCollection = null;

	/** RESEST ALL FORM FIELDS **/
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		from = null;
		go = null;
		to = null;
		clear = null;
		defaultVal = null;
		b_showChild = null;
		checkChart = null;
		this.check = null;
	}

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
	 * @return Returns the clear.
	 */
	public String getClear() {
		return clear;
	}

	/**
	 * @param clear
	 *            The clear to set.
	 */
	public void setClear(String clear) {
		this.clear = clear;
	}

	/**
	 * /**
	 * 
	 * @return Returns the index_id.
	 */
	public String getIndex_id() {
		/*
		 * if(index_id==null || index_id.equals("0")){ index_id="1"; }
		 */
		return index_id;
	}

	/**
	 * @param index_id
	 *            The index_id to set.
	 */
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
		Vector indexList = new Vector();

		ResultSet rst = null;
		// app.Connect con=new app.Connect();
		Connect con = ConnectInit.getConnect();
	//	AcessControl asc = new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		String NotSelected = asc.getLangValues("Masters.NotSelected");
		Logging.debug(" Inside getIndexListCollection(): Not Selected ="
				+ NotSelected);
		Connection connection = null;

		if (connection == null) {
			connection = con.getdbConnection();
		}
		if (indexListCollection == null) {

			try {

				PreparedStatement stmt = connection
						.prepareStatement(ConnectInit.queries
								.getProperty("index_list_without_child"));
				stmt.setString(1, userid1);
				rst = stmt.executeQuery();

				indexList.add(new LabelValueBean(NotSelected, "0"));// commented
																	// by lokesh
																	// 9/04/07
				while (rst.next()) {
					String count = rst.getString(1);
					indexList.add(new LabelValueBean(rst.getString(2), count));
				}
				ResultSet rbs = Icr.benchindecolection(connection,
						"index_list_without_child_bench");
				while (rbs.next()) {

					indexList.add(new LabelValueBean(rbs.getString(2), rbs
							.getString(1)));

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
				Logging.debug(e);
			}

			indexListCollection = indexList;
		}
		return indexListCollection;
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
	 * @return Returns the indexValue.
	 */
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
	 * @param index_id
	 *            The index_id to set.
	 */
	public void setIndex_id(String index_id) {
		this.index_id = index_id;
	}

	public String getUserid1() {
		return userid1;
	}

	public void setUserid1(String userid1) {
		this.userid1 = userid1;
	}
}