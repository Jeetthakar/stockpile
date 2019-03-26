/*
 * Created on Nov 28th,2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import com.harrier.initializeation.ConnectInit;

/**
 * @author neha
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class BackTestIndexForm extends ActionForm {
	Logger Logging = Logger.getLogger(BackTestIndexForm.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String index = null;
	private Collection indexcollection = null;
	private String index_name = null;
	private String index_type = null;
	private Collection indextypecollection = null;
	private String index_from = null;
	private String base_date = null;
	private String index_upto = null;
	private String base_currency = null;
	private String base_value = null;
	private String stkselect = null;
	private Collection stkcollection = null;
	private String exchselect = null;
	private Collection exchcollection = null;
	private String exchstkselect = null;
	private Collection exchstkcollection = null;
	private String excl_date = null;
	private String incl_date = null;
	private String excl_stock = null;
	private String incl_stock = null;
	private String operation = "no";
	private String compute = null;
	private String saveButton = null;
	private String resetButton = null;
	private String cancelButton = null;
	private String indexid = null;
	private String view = null;
	private String v1 = null;
	private String btvalue = null;
	private String shwFrom = null;
	private String shwexcl = null;
	private String shwincl = null;
	private String resetbutton = null;
	private String remove = null;
	private String add = null;
	private String user_id = null;
	private String reset = "no";

	private String exclDates = null;
	private String inclDates = null;
	private String exclStkIds = null;
	private String inclStkIds = null;
	private String exclStkNames = null;
	private String inclStkNames = null;

	private Collection excl_stockcollection = null;
	private Collection incl_stockcollection = null;

	public Collection getExcl_stockcollection() {
		return excl_stockcollection;
	}

	public void setExcl_stockcollection(Collection excl_stockcollection) {
		this.excl_stockcollection = excl_stockcollection;
	}

	public Collection getIncl_stockcollection() {
		return incl_stockcollection;
	}

	public void setIncl_stockcollection(Collection incl_stockcollection) {
		this.incl_stockcollection = incl_stockcollection;
	}

	/**
	 * @return Returns the exclStkNames.
	 */
	public String getExclStkNames() {
		return exclStkNames;
	}

	/**
	 * @param exclStkNames
	 *            The exclStkNames to set.
	 */
	public void setExclStkNames(String exclStkNames) {
		this.exclStkNames = exclStkNames;
	}

	/**
	 * @return Returns the inclStkNames.
	 */
	public String getInclStkNames() {
		return inclStkNames;
	}

	/**
	 * @param inclStkNames
	 *            The inclStkNames to set.
	 */
	public void setInclStkNames(String inclStkNames) {
		this.inclStkNames = inclStkNames;
	}

	/**
	 * @return Returns the add.
	 */
	public String getAdd() {
		return add;
	}

	/**
	 * @param add
	 *            The add to set.
	 */
	public void setAdd(String add) {
		this.add = add;
	}

	/**
	 * @return Returns the remove.
	 */
	public String getRemove() {
		return remove;
	}

	/**
	 * @param remove
	 *            The remove to set.
	 */
	public void setRemove(String remove) {
		this.remove = remove;
	}

	/**
	 * @return Returns the shwFrom.
	 */
	public String getShwFrom() {
		return shwFrom;
	}

	/**
	 * @param shwFrom
	 *            The shwFrom to set.
	 */
	public void setShwFrom(String shwFrom) {
		this.shwFrom = shwFrom;
	}

	public String getReset() {
		return reset;
	}

	public void setReset(String reset) {
		this.reset = reset;
	}

	/**
	 * @return Returns the user_id.
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id
	 *            The user_id to set.
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/** RESEST ALL FORM FIELDS **/
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		index = null;
		index_name = null;
		index_type = null;
		index_from = null;
		base_date = null;
		index_upto = null;
		base_currency = null;
		base_value = null;
		stkcollection = null;
		includedScripCollection = null;
		excludedScripCollection = null;
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		try {
			if (getCompute() != null && getCompute().equals("Compute")) {

				if (getIndex_from().equals("")) {
					errors.add(null,
							new ActionError("Error.message.backtrack1"));
					return errors;
				} else {
					int diffdate = CompareDate(getBase_date(), getIndex_from());
					if (diffdate < 0) {
						errors.add(null, new ActionError(
								"Error.message.backtrack2"));
						return errors;
					}

				}
			}

		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		}

		return null;
	}

	/**
	 * @return Returns the view.
	 */
	public String getView() {
		return view;
	}

	/**
	 * @param view
	 *            The view to set.
	 */
	public void setView(String view) {
		this.view = view;
	}

	/**
	 * @return Returns the base_currency.
	 */
	public String getBase_currency() {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		Connect c = ConnectInit.getConnect();
		String indexid = getIndex();

		try {
			if (connection == null)
				connection = c.getdbConnection();
			stmt = connection.prepareStatement(ConnectInit.queries
					.getProperty("getbasecurrency"));
			stmt.setString(1, indexid);
			rst = stmt.executeQuery();

			while (rst.next()) {

				base_currency = rst.getString(1);

			}
			rst.close();
			stmt.close();
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close connection "
						+ ee.getMessage());
			}
		}

		return base_currency;
	}

	/**
	 * @param base_currency
	 *            The base_currency to set.
	 */
	public void setBase_currency(String base_currency) {
		this.base_currency = base_currency;
	}

	/**
	 * @return Returns the base_date.
	 */
	public String getBase_date() {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		Connect c = ConnectInit.getConnect();
		String indexid = getIndex();

		try {
			if (connection == null)
				connection = c.getdbConnection();
			stmt = connection.prepareStatement(ConnectInit.queries
					.getProperty("getbasedate"));
			stmt.setString(1, indexid);
			rst = stmt.executeQuery();

			while (rst.next()) {

				base_date = rst.getString(1);

			}
			rst.close();
			stmt.close();
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close connection "
						+ ee.getMessage());
			}
		}

		return base_date;
	}

	/**
	 * @param base_date
	 *            The base_date to set.
	 */
	public void setBase_date(String base_date) {
		this.base_date = base_date;
	}

	/**
	 * @return Returns the base_value.
	 */
	public String getBase_value() {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		Connect c = ConnectInit.getConnect();
		String indexid = getIndex();

		try {
			if (connection == null)
				connection = c.getdbConnection();
			stmt = connection.prepareStatement(ConnectInit.queries
					.getProperty("getbasevalue"));
			stmt.setString(1, indexid);
			rst = stmt.executeQuery();

			while (rst.next()) {

				base_value = rst.getString(1);

			}
			rst.close();
			stmt.close();
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close connection "
						+ ee.getMessage());
			}
		}
		return base_value;
	}

	/**
	 * @param base_value
	 *            The base_value to set.
	 */
	public void setBase_value(String base_value) {
		this.base_value = base_value;
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
	 * @return Returns the exchcollection.
	 */
	public Collection getExchcollection() {

		Vector r3 = new Vector();

		Connection connection = null;
		Statement stmt = null;
		ResultSet rst = null;
		Connect c = ConnectInit.getConnect();

		try {
			if (connection == null)
				connection = c.getdbConnection();
			stmt = connection.createStatement();

			String query = ConnectInit.queries
					.getProperty("stock_exchange_online_list");

			rst = stmt.executeQuery(query);

			while (rst.next()) {

				r3.add(new LabelValueBean(rst.getString(2), rst.getString(1)));

			}
			rst.close();
			stmt.close();
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close connection "
						+ ee.getMessage());
			}
		}
		exchcollection = r3;

		return exchcollection;
	}

	/**
	 * @param exchcollection
	 *            The exchcollection to set.
	 */
	public void setExchcollection(Collection exchcollection) {

		this.exchcollection = exchcollection;
	}

	/**
	 * @return Returns the exchselect.
	 */
	public String getExchselect() {

		return exchselect;
	}

	/**
	 * @param exchselect
	 *            The exchselect to set.
	 */
	public void setExchselect(String exchselect) {
		this.exchselect = exchselect;
	}

	/**
	 * @return Returns the index.
	 */
	public String getIndex() {
		return index;
	}

	/**
	 * @param index
	 *            The index to set.
	 */
	public void setIndex(String index) {
		this.index = index;
	}

	/**
	 * @return Returns the index_from.
	 */
	public String getIndex_from() {
		return index_from;
	}

	/**
	 * @param index_from
	 *            The index_from to set.
	 */
	public void setIndex_from(String index_from) {
		this.index_from = index_from;
	}

	/**
	 * @return Returns the index_name.
	 */
	public String getIndex_name() {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		Connect c = ConnectInit.getConnect();
		String indexid = getIndex();

		try {
			if (connection == null)
				connection = c.getdbConnection();
			stmt = connection.prepareStatement(ConnectInit.queries
					.getProperty("getidxname"));
			stmt.setString(1, indexid);
			rst = stmt.executeQuery();

			while (rst.next()) {

				index_name = rst.getString(1);

			}
			rst.close();
			stmt.close();
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close connection "
						+ ee.getMessage());
			}
		}

		return index_name;
	}

	/**
	 * @param index_name
	 *            The index_name to set.
	 */
	public void setIndex_name(String index_name) {
		this.index_name = index_name;
	}

	/**
	 * @return Returns the index_type.
	 */
	public String getIndex_type() {

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		Connect c = ConnectInit.getConnect();
		String indexid = getIndex();

		try {
			if (connection == null)
				connection = c.getdbConnection();
			stmt = connection.prepareStatement(ConnectInit.queries
					.getProperty("getindextypename"));
			stmt.setString(1, indexid);
			rst = stmt.executeQuery();

			while (rst.next()) {

				index_type = rst.getString(1);

			}
			rst.close();
			stmt.close();
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close connection "
						+ ee.getMessage());
			}
		}

		return index_type;
	}

	/**
	 * @param index_type
	 *            The index_type to set.
	 */
	public void setIndex_type(String index_type) {

		this.index_type = index_type;
	}

	/**
	 * @return Returns the indexcollection.
	 */
	public Collection getIndexcollection() {

		Vector r1 = new Vector();

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		Connect c = ConnectInit.getConnect();

		try {
			if (connection == null)
				connection = c.getdbConnection();
			stmt = connection.prepareStatement(ConnectInit.queries
					.getProperty("index_list"));
			stmt.setInt(1, Integer.parseInt(getUser_id()));
			rst = stmt.executeQuery();

			r1.add(new LabelValueBean("Not Selected", "0"));
			while (rst.next()) {

				r1.add(new LabelValueBean(rst.getString(2), rst.getString(1)));

			}
			rst.close();
			stmt.close();
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close connection "
						+ ee.getMessage());
			}
		}
		indexcollection = r1;

		return indexcollection;
	}

	/**
	 * @param indexcollection
	 *            The indexcollection to set.
	 */
	public void setIndexcollection(Collection indexcollection) {
		this.indexcollection = indexcollection;
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
	 * @return Returns the stkcollection.
	 */
	public Collection getStkcollection() {
		Vector r2 = new Vector();

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		Connect c = ConnectInit.getConnect();
		String indexid = getIndex();

		try {
			if (connection == null)
				connection = c.getdbConnection();
			stmt = connection.prepareStatement(ConnectInit.queries
					.getProperty("stkcomposition"));
			stmt.setString(1, indexid);
			rst = stmt.executeQuery();

			while (rst.next()) {

				r2.add(new LabelValueBean(rst.getString(2), rst.getString(1)));

			}
			rst.close();
			stmt.close();
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close connection "
						+ ee.getMessage());
			}
		}

		stkcollection = r2;

		return stkcollection;
	}

	/**
	 * @param stkcollection
	 *            The stkcollection to set.
	 */
	public void setStkcollection(Collection stkcollection) {

		this.stkcollection = stkcollection;
	}

	/**
	 * @return Returns the stkselect.
	 */
	public String getStkselect() {

		return stkselect;
	}

	/**
	 * @param stkselect
	 *            The stkselect to set.
	 */
	public void setStkselect(String stkselect) {
		this.stkselect = stkselect;
	}

	/**
	 * @return Returns the cancelButton.
	 */
	public String getCancelButton() {
		return cancelButton;
	}

	/**
	 * @param cancelButton
	 *            The cancelButton to set.
	 */
	public void setCancelButton(String cancelButton) {
		this.cancelButton = cancelButton;
	}

	/**
	 * @return Returns the excl_date.
	 */
	public String getExcl_date() {
		return excl_date;
	}

	/**
	 * @param excl_date
	 *            The excl_date to set.
	 */
	public void setExcl_date(String excl_date) {
		this.excl_date = excl_date;
	}

	/**
	 * @return Returns the excl_stock.
	 */
	public String getExcl_stock() {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		Connect c = ConnectInit.getConnect();
		String stkid = getStkselect();
		String exstockname = null;
		try {
			if (connection == null)
				connection = c.getdbConnection();
			stmt = connection.prepareStatement(ConnectInit.queries
					.getProperty("excludedstockname"));
			stmt.setString(1, stkid);
			rst = stmt.executeQuery();

			while (rst.next()) {

				exstockname = rst.getString("stock_name");
			}
			rst.close();
			stmt.close();
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close connection "
						+ ee.getMessage());
			}
		}
		excl_stock = exstockname;
		return excl_stock;
	}

	/**
	 * @param excl_stock
	 *            The excl_stock to set.
	 */
	public void setExcl_stock(String excl_stock) {

		this.excl_stock = excl_stock;
	}

	/**
	 * @return Returns the incl_date.
	 */
	public String getIncl_date() {
		return incl_date;
	}

	/**
	 * @param incl_date
	 *            The incl_date to set.
	 */
	public void setIncl_date(String incl_date) {
		this.incl_date = incl_date;
	}

	/**
	 * @return Returns the incl_stock.
	 */
	public String getIncl_stock() {

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		Connect c = ConnectInit.getConnect();
		String stkid = getExchstkselect();
		String instockname = null;
		try {
			if (connection == null)
				connection = c.getdbConnection();
			stmt = connection.prepareStatement(ConnectInit.queries
					.getProperty("excludedstockname"));
			stmt.setString(1, stkid);
			rst = stmt.executeQuery();

			while (rst.next()) {

				instockname = rst.getString("stock_name");
			}
			rst.close();
			stmt.close();
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close connection "
						+ ee.getMessage());
			}
		}
		incl_stock = instockname;
		return incl_stock;
	}

	/**
	 * @param incl_stock
	 *            The incl_stock to set.
	 */
	public void setIncl_stock(String incl_stock) {
		this.incl_stock = incl_stock;
	}

	/**
	 * @return Returns the index_upto.
	 */
	public String getIndex_upto() {
		return index_upto;
	}

	/**
	 * @param index_upto
	 *            The index_upto to set.
	 */
	public void setIndex_upto(String index_upto) {
		this.index_upto = index_upto;
	}

	/**
	 * @return Returns the indexid.
	 */
	public String getIndexid() {
		return indexid;
	}

	/**
	 * @param indexid
	 *            The indexid to set.
	 */
	public void setIndexid(String indexid) {
		this.indexid = indexid;
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
	public void setResetButton(String resetButton) {
		this.resetButton = resetButton;
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
	public void setSaveButton(String saveButton) {
		this.saveButton = saveButton;
	}

	/**
	 * @return Returns the exchstkcollection.
	 */
	public Collection getExchstkcollection() {

		Vector r5 = new Vector();

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		Connect c = ConnectInit.getConnect();
		String exchid = getExchselect();

		try {
			if (connection == null)
				connection = c.getdbConnection();
			stmt = connection.prepareStatement(ConnectInit.queries
					.getProperty("ecxhstocks"));
			stmt.setString(1, exchid);
			rst = stmt.executeQuery();

			while (rst.next()) {

				r5.add(new LabelValueBean(rst.getString(2), rst.getString(1)));

			}
			rst.close();
			stmt.close();
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close connection "
						+ ee.getMessage());
			}
		}

		exchstkcollection = r5;

		return exchstkcollection;
	}

	/**
	 * @param exchstkcollection
	 *            The exchstkcollection to set.
	 */
	public void setExchstkcollection(Collection exchstkcollection) {
		this.exchstkcollection = exchstkcollection;
	}

	/**
	 * @return Returns the exchstkselect.
	 */
	public String getExchstkselect() {

		return exchstkselect;
	}

	/**
	 * @param exchstkselect
	 *            The exchstkselect to set.
	 */
	public void setExchstkselect(String exchstkselect) {
		this.exchstkselect = exchstkselect;
	}

	/**
	 * @return Returns the v1.
	 */
	public String getV1() {
		return v1;
	}

	/**
	 * @param v1
	 *            The v1 to set.
	 */
	public void setV1(String v1) {
		this.v1 = v1;
	}

	/**
	 * @return Returns the btvalue.
	 */
	public String getBtvalue() {
		return btvalue;
	}

	/**
	 * @param btvalue
	 *            The btvalue to set.
	 */
	public void setBtvalue(String btvalue) {

		this.btvalue = btvalue;
	}

	/**
	 * @return Returns the indextypecollection.
	 */
	public Collection getIndextypecollection() {
		return indextypecollection;
	}

	/**
	 * @param indextypecollection
	 *            The indextypecollection to set.
	 */
	public void setIndextypecollection(Collection indextypecollection) {
		this.indextypecollection = indextypecollection;
	}

	/**
	 * @return Returns the resetbutton.
	 */
	public String getResetbutton() {
		return resetbutton;
	}

	/**
	 * @param resetbutton
	 *            The resetbutton to set.
	 */
	public void setResetbutton(String resetbutton) {
		this.resetbutton = resetbutton;
	}

	public int CompareDate(String basedate, String idxfromdate) {
		Date creationDate = new Date(new Integer(basedate.trim().substring(6,
				10)).intValue(),
				new Integer(basedate.trim().substring(3, 5)).intValue(),
				new Integer(basedate.trim().substring(0, 2)).intValue());
		Date hDate = new Date(
				new Integer(idxfromdate.trim().substring(6, 10)).intValue(),
				new Integer(idxfromdate.trim().substring(3, 5)).intValue(),
				new Integer(idxfromdate.trim().substring(0, 2)).intValue());
		int diff = creationDate.compareTo(hDate);
		return diff;
	}

	/**
	 * @return Returns the exclStkIds.
	 */
	public String getExclStkIds() {
		return exclStkIds;
	}

	/**
	 * @param exclStkIds
	 *            The exclStkIds to set.
	 */
	public void setExclStkIds(String exclStkIds) {
		this.exclStkIds = exclStkIds;
	}

	/**
	 * @return Returns the inclstkIds.
	 */
	public String getInclStkIds() {
		return inclStkIds;
	}

	/**
	 * @param inclstkIds
	 *            The inclstkIds to set.
	 */
	public void setInclStkIds(String inclstkIds) {
		this.inclStkIds = inclstkIds;
	}

	/**
	 * @return Returns the exclDates.
	 */
	public String getExclDates() {
		return exclDates;
	}

	/**
	 * @param exclDates
	 *            The exclDates to set.
	 */
	public void setExclDates(String exclDates) {
		this.exclDates = exclDates;
	}

	/**
	 * @return Returns the inclDates.
	 */
	public String getInclDates() {
		return inclDates;
	}

	/**
	 * @param inclDates
	 *            The inclDates to set.
	 */
	public void setInclDates(String inclDates) {
		this.inclDates = inclDates;
	}

	private Collection indexCollection = null;
	private String indName = null;
	private String ic_selections = null;
	private Collection compScripCollection = null;

	private String operationSubmit = null;
	private String exchoperation = null;
	private String[] Rem = null;

	private String ind_compute_typ = null;

	private String base_date_frm = null;
	private String base_date_to = null;

	private String parent_id = null;
	private String exchName = null;
	private Collection exchangeCollection = null;
	private Collection exchScripCollection = null;
	private String excl_selections = null;

	private String[] incl_scrip = null;
	private String[] excl_scrip = null;

	private ArrayList<String> excl_date_array;
	private ArrayList<String> incl_date_array;

	private Collection includedScripCollection = null;
	private Collection excludedScripCollection = null;
	public static String message = null;
	private String cnt_sel = null;
	private String clear = null;

	/**
	 * @return Returns the cnt_sel.
	 */
	public String getCnt_sel() {
		return cnt_sel;
	}

	/**
	 * @param cnt_sel
	 *            The cnt_sel to set.
	 */
	public void setCnt_sel(String cnt_sel) {
		this.cnt_sel = cnt_sel;
	}

	/**
	 * @return Returns the message.
	 */
	public static String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            The message to set.
	 */
	public static void setMessage(String message) {
		BackTestIndexForm.message = message;
	}

	/**
	 * @return Returns the exchoperation.
	 */
	public String getExchoperation() {
		return exchoperation;
	}

	/**
	 * @param exchoperation
	 *            The exchoperation to set.
	 */
	public void setExchoperation(String exchoperation) {
		this.exchoperation = exchoperation;
	}

	/**
	 * @return Returns the exchScripCollection.
	 */
	public Collection getExchScripCollection() {
		Vector roles = new Vector(10);
		roles.add(new LabelValueBean("Not Selected", "value0"));
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		Connect c = new Connect();
		if ((operation != null && operation.equals("Save"))
				&& (indName != null)
				&& (parent_id != null && !(parent_id.equals("0")))) {
			try {
				if (connection == null)
					connection = c.getdbConnection();
				stmt = connection
						.prepareStatement(ConnectInit.queries
								.getProperty("IndexCompute_get_Composition_for_index_OnDate"));
				stmt.setString(1, parent_id);
				stmt.setString(2, index_from);
				stmt.setString(3, parent_id);
				stmt.setString(4, parent_id);
				stmt.setString(5, index_from);
				stmt.setString(6, parent_id);
				stmt.setString(7, index_from);
				stmt.setString(8, parent_id);
				stmt.setString(9, index_from);
				rst = stmt.executeQuery();

				while (rst.next()) {
					// int count=rst.getInt(1);
					roles.add(new LabelValueBean(rst.getString(2), rst
							.getString(1)));
				}
				rst.close();
				stmt.close();
			} catch (Exception e) {
				Logging.error(" Error : " + e.getMessage());
			} finally {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ee) {
					Logging.error(" Error : Unable to close connection "
							+ ee.getMessage());
				}
			}
		} else {
			if ((operation != null && operation.equals("Save"))
					&& (indName != null)
					&& (exchoperation != null && exchoperation.equals("exch"))) {
				try {
					if (connection == null)
						connection = c.getdbConnection();
					stmt = connection
							.prepareStatement(ConnectInit.queries
									.getProperty("indexBackTest_get_list_of_composition_scrips_exchange1"));
					stmt.setString(1, exchName);
					rst = stmt.executeQuery();

					while (rst.next()) {
						// int count=rst.getInt(1);
						roles.add(new LabelValueBean(rst.getString(2), rst
								.getString(1)));
					}
					rst.close();
					stmt.close();
				} catch (Exception e) {
					Logging.error(" Error : " + e.getMessage());
				} finally {
					try {
						if (connection != null)
							connection.close();
					} catch (Exception ee) {
						Logging.error(" Error : Unable to close connection "
								+ ee.getMessage());
					}
				}
			}
		}
		exchScripCollection = roles;
		return exchScripCollection;
	}

	/**
	 * @param exchScripCollection
	 *            The exchScripCollection to set.
	 */
	public void setExchScripCollection(Collection exchScripCollection) {
		this.exchScripCollection = exchScripCollection;
	}

	/**
	 * @return Returns the excl_selections.
	 */
	public String getExcl_selections() {
		return excl_selections;
	}

	/**
	 * @param excl_selections
	 *            The excl_selections to set.
	 */
	public void setExcl_selections(String excl_selections) {
		this.excl_selections = excl_selections;
	}

	/**
	 * @return Returns the exchangeCollection.
	 */
	public Collection getExchangeCollection() {
		Vector roles = new Vector(10);
		roles.add(new LabelValueBean("Not Selected", "value0"));
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		Connect c = new Connect();
		if ((operation != null && operation.equals("Save"))
				&& (indName != null)) {
			try {
				if (connection == null)
					connection = c.getdbConnection();
				stmt = connection
						.prepareStatement(ConnectInit.queries
								.getProperty("indexBackTest_get_list_of_exchange_omps_scrips_belong"));
				stmt.setString(1, indName);
				rst = stmt.executeQuery();

				while (rst.next()) {
					// int count=rst.getInt(1);
					roles.add(new LabelValueBean(rst.getString(2), rst
							.getString(1)));
				}
				rst.close();
				stmt.close();
			} catch (Exception e) {
				Logging.error(" Error : " + e.getMessage());
			} finally {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ee) {
					Logging.error(" Error : Unable to close connection "
							+ ee.getMessage());
				}
			}
		}
		exchangeCollection = roles;
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
	 * @return Returns the exchName.
	 */
	public String getExchName() {
		return exchName;
	}

	/**
	 * @param exchName
	 *            The exchName to set.
	 */
	public void setExchName(String exchName) {
		this.exchName = exchName;
	}

	/**
	 * @return Returns the base_date_frm.
	 */
	public String getBase_date_frm() {
		return base_date_frm;
	}

	/**
	 * @param base_date_frm
	 *            The base_date_frm to set.
	 */
	public void setBase_date_frm(String base_date_frm) {
		this.base_date_frm = base_date_frm;
	}

	/**
	 * @return Returns the parent_id.
	 */
	public String getParent_id() {
		if (parent_id == null)
			parent_id = "0";
		return parent_id;
	}

	/**
	 * @param parent_id
	 *            The parent_id to set.
	 */
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	/**
	 * @return Returns the rem.
	 */
	public String[] getRem() {
		return Rem;
	}

	/**
	 * @param rem
	 *            The rem to set.
	 */
	public void setRem(String[] rem) {
		Rem = rem;
	}

	// /**
	// * Validate Errors
	// * */
	// public ActionErrors validate(ActionMapping mapping,
	// HttpServletRequest request) {
	//
	// ActionErrors errors = new ActionErrors();
	// if ((operation != null && operation.equals("Save"))
	// || (operationSubmit != null && operationSubmit.equals("Submit"))) {
	// if (indName == null || (indName != null && indName.equals("0"))) {
	// errors.add(null, new ActionError("Error.message.IndexName"));
	// }
	// }
	//
	// return errors;
	//
	// }

	/**
	 * @return Returns the compScripCollection.
	 */
	public Collection getCompScripCollection() {
		Vector roles = new Vector(10);
		roles.add(new LabelValueBean("Not Selected", "value0"));
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		Connect c = new Connect();
		if ((operation != null && operation.equals("Save"))
				&& (indName != null)) {
			try {
				if (connection == null)
					connection = c.getdbConnection();
				stmt = connection
						.prepareStatement(ConnectInit.queries
								.getProperty("IndexCompute_get_Composition_for_index_OnDate1"));
				stmt.setString(1, indName);
				stmt.setString(2, index_from);// base_date
				stmt.setString(3, indName);
				stmt.setString(4, indName);
				stmt.setString(5, index_from);
				stmt.setString(6, indName);
				stmt.setString(7, index_from);
				stmt.setString(8, indName);
				stmt.setString(9, index_from);
				rst = stmt.executeQuery();

				while (rst.next()) {
					// int count=rst.getInt(1);
					roles.add(new LabelValueBean(rst.getString(2), rst
							.getString(1)));
				}
				rst.close();
				stmt.close();
			} catch (Exception e) {
				Logging.error(" Error : " + e.getMessage());
			} finally {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ee) {
					Logging.error(" Error : Unable to close connection "
							+ ee.getMessage());
				}
			}
		}
		compScripCollection = roles;
		return compScripCollection;
	}

	/**
	 * @param compScripCollection
	 *            The compScripCollection to set.
	 */
	public void setCompScripCollection(Collection compScripCollection) {
		this.compScripCollection = compScripCollection;
	}

	/**
	 * @return Returns the ic_selections.
	 */
	public String getIc_selections() {
		return ic_selections;
	}

	/**
	 * @param ic_selections
	 *            The ic_selections to set.
	 */
	public void setIc_selections(String ic_selections) {
		this.ic_selections = ic_selections;
	}

	/**
	 * @return Returns the indexCollection.
	 */
	public Collection getIndexCollection() {
		Vector roles = new Vector(10);
		// roles.add(new LabelValueBean("Not Selected","0"));
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		Connect c = new Connect();
		// dbconnect();
		try {
			if (connection == null)
				connection = c.getdbConnection();
			stmt = connection.prepareStatement(ConnectInit.queries
					.getProperty("index_list"));
			rst = stmt.executeQuery();

			while (rst.next()) {
				// int count=rst.getInt(1);
				roles.add(new LabelValueBean(rst.getString(2), rst.getString(1)));
			}
			rst.close();
			stmt.close();
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close connection "
						+ ee.getMessage());
			}
		}
		indexCollection = roles;
		return indexCollection;
	}

	/**
	 * @param indexCollection
	 *            The indexCollection to set.
	 */
	public void setIndexCollection(Collection indexCollection) {
		this.indexCollection = indexCollection;
	}

	/**
	 * @return Returns the indName.
	 */
	public String getIndName() {
		return indName;
	}

	/**
	 * @param indName
	 *            The indName to set.
	 */
	public void setIndName(String indName) {
		this.indName = indName;
	}

	/**
	 * method responsible for displaying the details of an selected index.
	 * 
	 * @param index_id
	 */
	public void fillIndexDetail(String subButton) {
		Connection connection = null;
		Connect c = new Connect();
		PreparedStatement stmt = null;
		ResultSet rst = null;
		try {
			if (connection == null)
				connection = c.getdbConnection();
			stmt = connection.prepareStatement(ConnectInit.queries
					.getProperty("indexBackTest_fill_index_details"));
			stmt.setString(1, indName);
			rst = stmt.executeQuery();
			while (rst.next()) {
				this.index_name = rst.getString(1);
				if (rst.getString(2) != null && rst.getString(2).equals("M")) {
					this.ind_compute_typ = "Market Cap.";
				} else {
					if (rst.getString(2) != null
							&& rst.getString(2).equals("A")) {
						this.ind_compute_typ = "Free Float";
					} else {
						this.ind_compute_typ = null;
					}
				}
				if (!(subButton != null && subButton.equals("Submit"))
						&& !(exchoperation != null && exchoperation
								.equals("exch"))) {
					this.index_from = rst.getString(3);
				}
				this.index_upto = rst.getString(4);
				this.base_date = rst.getString(5);
				this.base_date_frm = rst.getString(6);
				this.base_date_to = rst.getString(7);
				if ((rst.getString(9) != null)
						&& (rst.getString(9).trim().equals("1"))) {
					this.parent_id = "0";
				} else {
					this.parent_id = rst.getString(8);
				}
			}
			rst.close();
			stmt.close();
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close connection "
						+ ee.getMessage());
			}
		}
	}

	/**
	 * @return Returns the base_date_to.
	 */
	public String getBase_date_to() {
		return base_date_to;
	}

	/**
	 * @param base_date_to
	 *            The base_date_to to set.
	 */
	public void setBase_date_to(String base_date_to) {
		this.base_date_to = base_date_to;
	}

	public ArrayList<String> getExcl_date_array() {
		return excl_date_array;
	}

	public void setExcl_date_array(ArrayList<String> excl_date_array) {
		this.excl_date_array = excl_date_array;
	}

	public ArrayList<String> getIncl_date_array() {
		return incl_date_array;
	}

	public void setIncl_date_array(ArrayList<String> incl_date_array) {
		this.incl_date_array = incl_date_array;
	}

	/**
	 * @return Returns the ind_compute_typ.
	 */
	public String getInd_compute_typ() {
		return ind_compute_typ;
	}

	/**
	 * @param ind_compute_typ
	 *            The ind_compute_typ to set.
	 */
	public void setInd_compute_typ(String ind_compute_typ) {
		this.ind_compute_typ = ind_compute_typ;
	}

	/**
	 * @return Returns the excl_scrip.
	 */
	public String[] getExcl_scrip() {
		return excl_scrip;
	}

	/**
	 * @param excl_scrip
	 *            The excl_scrip to set.
	 */
	public void setExcl_scrip(String[] excl_scrip) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		Connect c = null;
		if (excl_scrip != null) {
			String stockNamesList[] = new String[excl_scrip.length];
			String stockDatesList[] = new String[excl_scrip.length];
			if (excl_scrip != null && excl_scrip.length != 0) {
				excl_date_array = new ArrayList<String>();
				for (int i = 0; i < excl_scrip.length; i++) {
					c = new Connect();
					String instockname = null;
					String instockdate = null;
					String[] temp = excl_scrip[i].split("::");
					String stock_id = temp[0];
					instockdate = temp[1];
					if (temp.length > 1) {
						excl_date_array.add(temp[1]);
					} else if (temp.length == 1) {
						excl_date_array.add(getExcl_date());
					}
					try {
						connection = c.getdbConnection();
						stmt = connection.prepareStatement(ConnectInit.queries
								.getProperty("excludedstockname"));
						stmt.setString(1, stock_id);
						rst = stmt.executeQuery();
						while (rst.next()) {
							instockname = rst.getString("stock_name");
						}
						rst.close();
						stmt.close();
					} catch (Exception e) {
						Logging.error(" Error : " + e.getMessage());
					} finally {
						try {
							if (connection != null)
								connection.close();
						} catch (Exception ee) {
							Logging.error(" Error : Unable to close connection "
									+ ee.getMessage());
						}
					}
					stockNamesList[i] = instockname;
					stockDatesList[i] = instockdate;
				}
			}

			this.excl_scrip = stockNamesList;

			Vector roles = new Vector(stockNamesList.length);
			for (int i = 0; i < stockNamesList.length; i++) {
				roles.add(new LabelValueBean(stockNamesList[i] + "::"
						+ stockDatesList[i], "value" + i));
			}
			setExcludedScripCollection(roles);
		} else {
			Vector roles = new Vector(10);
			roles.add(new LabelValueBean("", "value0"));
			excludedScripCollection = roles;
			setExcludedScripCollection(roles);
		}
		// setExcludedScripCollection(new
		// Vector(Arrays.asList(stockNamesList)));
	}

	/**
	 * @return Returns the incl_scrip.
	 */
	public String[] getIncl_scrip() {
		return incl_scrip;
	}

	/**
	 * @param incl_scrip
	 *            The incl_scrip to set.
	 */
	public void setIncl_scrip(String[] incl_scrip) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		Connect c = null;
		if (incl_scrip != null) {
			String stockNamesList[] = new String[incl_scrip.length];
			String stockDatesList[] = new String[incl_scrip.length];
			if (incl_scrip != null && incl_scrip.length != 0) {
				incl_date_array = new ArrayList<String>();
				for (int i = 0; i < incl_scrip.length; i++) {
					c = new Connect();
					String instockname = null;
					String instockdate = null;
					String[] temp = incl_scrip[i].split("::");
					String stock_id = temp[0];
					instockdate = temp[1];
					if (temp.length > 1) {
						incl_date_array.add(temp[1]);
					} else if (temp.length == 1) {
						incl_date_array.add(getExcl_date());
					}
					try {
						connection = c.getdbConnection();
						stmt = connection.prepareStatement(ConnectInit.queries
								.getProperty("excludedstockname"));
						stmt.setString(1, stock_id);
						rst = stmt.executeQuery();
						while (rst.next()) {
							instockname = rst.getString("stock_name");
						}
						rst.close();
						stmt.close();
					} catch (Exception e) {
						Logging.error(" Error : " + e.getMessage());
					} finally {
						try {
							if (connection != null)
								connection.close();
						} catch (Exception ee) {
							Logging.error(" Error : Unable to close connection "
									+ ee.getMessage());
						}
					}
					stockNamesList[i] = instockname;
					stockDatesList[i] = instockdate;
				}
			}
			this.incl_scrip = stockNamesList;

			Vector roles = new Vector(stockNamesList.length);
			for (int i = 0; i < stockNamesList.length; i++) {
				roles.add(new LabelValueBean(stockNamesList[i] + "::"
						+ stockDatesList[i], "value" + i));
			}
			setIncludedScripCollection(roles);
		} else {
			Vector roles = new Vector(10);
			roles.add(new LabelValueBean("", "value0"));
			includedScripCollection = roles;
			setIncludedScripCollection(roles);
		}
	}

	/**
	 * @return Returns the includedScripCollection.
	 */
	public Collection getIncludedScripCollection() {
		if (includedScripCollection == null) {
			Vector roles = new Vector(10);
			roles.add(new LabelValueBean("", "value0"));
			includedScripCollection = roles;
		}
		return includedScripCollection;
	}

	/**
	 * @param includedScripCollection
	 *            The includedScripCollection to set.
	 */
	public void setIncludedScripCollection(Collection includedScripCollection) {
		// System.out.println(includedScripCollection + " p_include");
		this.includedScripCollection = includedScripCollection;
	}

	/**
	 * @return Returns the excludedScripCollection.
	 */
	public Collection getExcludedScripCollection() {
		if (excludedScripCollection == null) {
			Vector roles = new Vector(10);
			roles.add(new LabelValueBean("", "value0"));
			excludedScripCollection = roles;
		}
		return excludedScripCollection;
	}

	/**
	 * @param excludedScripCollection
	 *            The excludedScripCollection to set.
	 */
	public void setExcludedScripCollection(Collection excludedScripCollection) {
		System.out.println(excludedScripCollection + " p_exclude");
		this.excludedScripCollection = excludedScripCollection;
	}

	/**
	 * @return Returns the operationSubmit.
	 */
	public String getOperationSubmit() {
		return operationSubmit;
	}

	/**
	 * @param operationSubmit
	 *            The operationSubmit to set.
	 */
	public void setOperationSubmit(String operationSubmit) {
		this.operationSubmit = operationSubmit;
	}

	public String getClear() {
		return clear;
	}

	public void setClear(String clear) {
		this.clear = clear;
	}
}
