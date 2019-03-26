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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import app.AcessControl;
import app.Connect;

import com.harrier.initializeation.ConnectInit;

public class StockContributionForm extends ActionForm {// ValidatorForm
														// implements
														// Serializable{
	Logger Logging = Logger.getLogger(StockContributionForm.class);
	private String from = null, go = null, clear = null, to = null,
			defaultVal = null, check = null, checkChart = null,
			selectIndex = null, selectStock = null, text = null;
	private Collection selectIndexCollection = null;
	static double total = 0.00;
//	AdjustDecimal ad = new AdjustDecimal();
	String tval, tvol, indexName, computetotalreturns, b_showChild, query,
			index, lastclosingvalue, cdate = null, hist_Date, CompareDate,
			compute;
	String tdate, fdate;
	ArrayList indweighttable = null;
	ArrayList stockcotriIndexchange = null;
	Vector vector_remStockid = null;
	Hashtable IndexNameHash = new Hashtable();
	IndexComposeReportMethod Icr = new IndexComposeReportMethod();
	// stockDetails sdf;
	private ResultSet rst;
	private String user_id = null, role_id1;

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

	private Vector vi;
	// app.Connect con=new app.Connect();
	Connect con = ConnectInit.getConnect();

	/** RESEST ALL FORM FIELDS **/
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		selectIndexCollection = null;
		from = null;
		go = null;
		to = null;
		clear = null;
		defaultVal = null;
		b_showChild = null;
	}

	/**
	 * VALIDATE FORM DATA
	 * **/
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		return errors;
	}

	public String getB_showChild() {
		return b_showChild;
	}

	public void setB_showChild(String child) {
		b_showChild = child;

	}

	public void setComputetotalreturns(String computetotalreturns) {
		this.computetotalreturns = computetotalreturns;
	}

	Connect c = ConnectInit.getConnect();

	Collection indexcollection;

	public Collection getIndexcollection() {
		Connection connection = null;
		Logging.debug("b_ShowChild  :: " + getB_showChild());
		if (getB_showChild() != null && getB_showChild().trim().equals("on")) {
			query = ConnectInit.queries.getProperty("index_list");
		} else {
			query = ConnectInit.queries.getProperty("index_list_without_child");
		}

		String id1 = null;
		Vector v = new Vector();
		ResultSet rs;
		try {
			if (connection == null)
				connection = c.getdbConnection();

			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			AcessControl asc = ConnectInit.getAcessControl();
		//	AcessControl asc = new AcessControl();
			String NotSelected = asc.getLangValues("Masters.NotSelected");
			Logging.debug(" Inside getIndexcollection(): Not Selected ="
					+ NotSelected);

			v.add(new LabelValueBean("Not Selected", "0"));
			while (rs.next()) {
				id1 = rs.getString(1);
				v.add(new LabelValueBean(rs.getString(2), id1));
				IndexNameHash.put(id1, rs.getString(2));
			}
			// Change by Manoj adekar
			int role_id2 = Integer.parseInt(role_id1);
			if (role_id2 != 1) {
				ResultSet rbs = Icr.benchindecolection(connection,
						"index_list_without_child_bench");
				while (rbs.next()) {
					id1 = rbs.getString(1);
					v.add(new LabelValueBean(rbs.getString(2), id1));

				}
			}

			indexcollection = v;
		} catch (Exception e) {
			// TODO: handle exception
			Logging.error(" Error :" + e.getMessage());
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
					Logging.error(" Error : Unable to close connection "
							+ ex.getMessage());
				}
				Logging.error(" Error : Unable to close connection "
						+ ee.getMessage());
			}
		}
		return indexcollection;
	}

	public void setIndexcollection(Collection indexcollection) {
		this.indexcollection = indexcollection;
	}

	/**
	 * @return Returns the indexName.
	 */
	public String getIndexName() {

		try {
			String local_d1 = index;

			Enumeration e;
			String str;
			String iname = "", ival = "";
			e = IndexNameHash.keys();
			while (e.hasMoreElements()) {
				str = (String) e.nextElement();
				iname = (String) IndexNameHash.get(str);
				if (str.equals(local_d1)) {

					indexName = iname;
					break;
				}

			}

		} catch (Exception e) {

		}
		return indexName;
	}

	/**
	 * @param indexName
	 *            The indexName to set.
	 */
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public String getIndex() {
		return index;
	}

	/**
	 * @param index
	 *            The index to set.
	 */
	public void setIndex(String index) {
		Logging.debug("setIndex index " + index);
		if (index != null)
			this.index = index;
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
	 * @return Returns the from.
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            The fromDate to set.
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * Database Connectivity
	 * */
	/*
	 * public void dbconnect(){
	 * 
	 * try {if(app.Connect.con==null){ con.getConnection(); } } catch (Exception
	 * e) { System.out.println(e);}
	 * 
	 * }
	 */
	/**
	 * @return Returns the toDate.
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to
	 *            The toDate to set.
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * 
	 * @return checkChart Returns the checkChart
	 */
	public String getCheckChart() {
		return checkChart;
	}

	/**
	 * 
	 * @param checkChart
	 *            The checkChart to set
	 */
	public void setCheckChart(String checkChart) {
		this.checkChart = checkChart;
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

	/**
	 * @return Returns the vector_stockcotriIndexchange.
	 */
	public Vector getVi() {
		return vi;
	}

	/**
	 * @param companyWeightageVector
	 *            The companyWeightageVector to set.
	 */
	public void setVi(Vector vi) {
		this.vi = vi;
	}

	public ArrayList getStockcotriIndexchange() {

		Logging.debug("Inside Vector_stockcotriIndexchange");
		String index1 = index;
		String fodate = from;
		String todate = to;
		vi = new Vector();
		String stockname = null, indexmarket = null, stockmarket = null, weightage = null;
		stockcotriIndexchange = new ArrayList();
		ArrayList tempdata = new ArrayList();
		StockContri stockcontri;
		try {
			Vector date = new Vector();
			/*
			 * app.Connect con=new app.Connect(); if(Connect.con==null) {
			 * con.getConnection(); }
			 */

			Connection conect = null;
			Connect con = ConnectInit.getConnect();
			// Change by Manoj Adekar for Dynamic connection using
			// getdbConnection() instead of getConnection()

			if (con == null) {
				conect = con.getdbConnection();
			}

			rst = con.StockcontriIndexResult(
					"stock_contribution_to_change_in_index", index1, fodate,
					todate);
			int i = 0, q = 0;
			Logging.debug("setVector_stockcotriIndexchange");
			try {
				while (rst.next()) {
					Logging.debug("inside first while end " + rst);

					try {
						// app.Logging.getDebug("get tring "+rst.getString(1));
						if (rst.getString(1) == null) {
							stockname = "0";
							Logging.debug("after get");
						} else {
							stockname = (rst.getString(1).trim());
							vi.add(i, rst.getString(1));
						}
					} catch (Exception e) {
						Logging.debug("Error while returning resultset"
								+ e.getMessage());
					}

					i++;
					if (rst.getString(2) == null) {
						indexmarket = "0";
					} else {

						String str = rst.getString(2);
						String str2 = str.substring(str.indexOf(46), (str
								.indexOf(46) + 3));
						String str1 = str.substring(0, str.indexOf(46)) + str2;
						indexmarket = str1;
						vi.add(i, indexmarket);
						// app.Logging.getDebug((String)rst.getString(2));
					}
					i++;

					if (rst.getString(3) == null) {
						stockmarket = "0";
					} else {
						String str = rst.getString(3);
						String str2 = str.substring(str.indexOf(46), (str
								.indexOf(46) + 3));
						String str1 = str.substring(0, str.indexOf(46)) + str2;
						stockmarket = str1;
						vi.add(i, stockmarket);
						// app.Logging.getDebug((String)rst.getString(3));
					}
					i++;
					if (rst.getString(4) == null) {
						weightage = "0";
					} else {
						weightage = rst.getString(4);
						vi.add(i, rst.getString(4));
						// app.Logging.getDebug((String)rst.getString(4));
					}
					i++;
					date.add(q, rst.getString(5));

					q++;

					// app.Logging.getDebug((String)rst.getString(5));
					date.add(q, rst.getString(6));

					// app.Logging.getDebug((String)rst.getString(6));
					q++;

					stockcontri = new StockContri(stockname, indexmarket,
							stockmarket, weightage);
					tempdata.add(stockcontri);

				}
			} catch (SQLException sqlexp) {
				Logging.error("SQL Error :" + sqlexp.getMessage());
			}

			Logging.debug("" + stockcotriIndexchange.size());

			Logging.debug("After first while end");
			vector_remStockid = new Vector();
			if (date.size() > 1) {
				tdate = (String) date.get(0);
				fdate = (String) date.get(1);
			}
			Logging.debug("in bean" + index + "  " + fdate + " " + "  "
					+ tdate);
			ResultSet rst1 = con.StockcontriSidlResult(
					"stock_contribution_stock_id_left", index1, fdate, tdate);
			int j = 0;
			while (rst1.next()) {
				vector_remStockid.add(j, rst1.getString(1));
			}
			if (vector_remStockid.size() != 0) {
				for (int k = 0; k < (vector_remStockid.size()); k++) {
					String s_id = (String) vector_remStockid.get(k);
					ResultSet rst2 = con.stockcontriIndResult(
							"stock_contribution_to_change_in_index_individual",
							index1, s_id, todate, fodate);
					Logging.debug("setVector_stockcotriIndexchange");

					while (rst2.next()) {
						i++;
						if (rst.getString(1) == null) {
							stockname = "0";

						} else {
							stockname = (rst.getString(1).trim());

						}

						if (rst.getString(2) == null) {
							indexmarket = "0";
						} else {

							String str = rst.getString(2);
							String str2 = str.substring(str.indexOf(46), (str
									.indexOf(46) + 3));
							String str1 = str.substring(0, str.indexOf(46))
									+ str2;
							indexmarket = str1;
							// app.Logging.getDebug((String)rst.getString(2));
						}

						if (rst.getString(3) == null) {
							stockmarket = "0";
						} else {
							String str = rst.getString(3);
							String str2 = str.substring(str.indexOf(46), (str
									.indexOf(46) + 3));
							String str1 = str.substring(0, str.indexOf(46))
									+ str2;
							stockmarket = str1;
							// app.Logging.getDebug((String)rst.getString(3));
						}
						if (rst.getString(4) == null) {
							weightage = "0";
						} else {
							weightage = rst.getString(4);
							// app.Logging.getDebug((String)rst.getString(4));
						}
						// stockcontri = new
						// StockContri(stockname,indexmarket,stockmarket,weightage);

					}
					rst.close();

				}
			}
			// Closing the connection : Manoj Adekar
			con.closeDynaCon();
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :" + sqlexp.getMessage());
		}
		stockcotriIndexchange = tempdata;
		Logging
				.info("SQL Error before return :" + stockcotriIndexchange);
		setVi(vi);

		return stockcotriIndexchange;
	}

	/**
	 * @param vector_stockcotriIndexchange
	 *            The vector_stockcotriIndexchange to set.
	 */
	public void setStockcotriIndexchange(ArrayList stockcotriIndexchange) {
		this.stockcotriIndexchange = stockcotriIndexchange;
	}

	public String getRole_id1() {
		return role_id1;
	}

	public void setRole_id1(String role_id1) {
		this.role_id1 = role_id1;
	}

}
