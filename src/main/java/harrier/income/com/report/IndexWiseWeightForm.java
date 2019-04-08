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
import org.jfree.chart.demo.servlet.AdjustDecimal;

import app.AcessControl;
import app.Connect;

import com.harrier.initializeation.ConnectInit;

public class IndexWiseWeightForm extends ActionForm {
	Logger Logging = Logger.getLogger(IndexWiseWeightForm.class);
	private String from = null, go = null, clear = null, to = null,
			defaultVal = null, check = null, checkChart = null,
			selectIndex = null, selectStock = null, text = null, val = null,
			roleId_wtg = "";
	private Collection IndexCollection = null;
	double total = 0.00, totalm = 0.00;
	// AdjustDecimal ad = new AdjustDecimal();
	String tval, tvol, graph, indexName, filename, computetotalreturns,
			b_showChild, query, index, lastclosingvalue, cdate = null,
			hist_Date, CompareDate, compute, userid1;
	ArrayList indweighttable = null;
	Hashtable IndexNameHash = new Hashtable();
	PreparedStatement pst;
	ResultSet rs, rs1;
	// stockDetails sdf;
	private ResultSet rst;
	private Vector vi;
	IndexComposeReportMethod Icr = new IndexComposeReportMethod();

	// app.Connect con=new app.Connect();
	/** RESEST ALL FORM FIELDS **/
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		IndexCollection = null;
		from = null;
		go = null;
		to = null;
		clear = null;
		defaultVal = null;
		b_showChild = null;
		checkChart = null;
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

			pst = connection.prepareStatement(query);
			pst.setString(1, userid1);
			rs = pst.executeQuery();
			// AcessControl asc = new AcessControl();
			AcessControl asc = ConnectInit.getAcessControl();
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
			int role_id2 = Integer.parseInt(roleId_wtg);
			if (role_id2 != 1) {
				ResultSet rbs = Icr.benchindecolection(connection,
						"index_list_without_child_bench");
				while (rbs.next()) {
					id1 = rbs.getString(1);
					v.add(new LabelValueBean(rbs.getString(2), id1));
					IndexNameHash.put(id1, rbs.getString(2));
				}
			}
			indexcollection = v;
		} catch (Exception e) {
			// TODO: handle exception
			Logging.error(" Error :" + e.getMessage());
			// e.printStackTrace();
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

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		Logging.debug("setIndex index " + index);
		if (index != null)
			this.index = index;
	}

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

	/**
	 * @return Returns the indexName.
	 */
	public String getIndexName() {

		try {
			String local_d1 = getIndex();
			Logging.debug("INDEX id=" + local_d1);
			Enumeration e;
			String str;
			String iname = "", ival = "";
			e = IndexNameHash.keys();
			while (e.hasMoreElements()) {
				str = (String) e.nextElement();
				iname = (String) IndexNameHash.get(str);
				if (str.equals(local_d1)) {
					Logging.debug("Yes it is matched");
					indexName = iname;
					break;
				}
				Logging.debug("INDEX value from getIndexName  = " + str);
				Logging.debug("INDEX NAME from getIndexName  = " + iname);
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

	/**
	 * @param index
	 *            The index to set.
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
	 * e) { Logging.debug(e);}
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
	 * @return Returns the vector_indtable.
	 */
	/**
	 * @return Returns the toDate.
	 */
	public String getVal() {
		return val;
	}

	/**
	 * @param to
	 *            The toDate to set.
	 */
	public void setVal(String val) {
		this.val = val;
	}

	/**
	 * @return Returns the total.
	 */
	public double getTotal() {
		// org.jfree.chart.demo.servlet.AdjustDecimal ad = new
		// org.jfree.chart.demo.servlet.AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		if (total >= 99.9999)
			total = 100.00;
		String strtotal = (String) new Double(total).toString();
		strtotal = ad.indexcompose(strtotal);
		total = Double.parseDouble(strtotal);
		Logging.debug("total value" + total);
		return total;
	}

	/**
	 * @param total
	 *            The total to set.
	 */
	public void setTotal(double total) {
		this.total = total;
	}

	public ArrayList getIndweighttable() {
		String industryname = null, marketcap = null, weightage = null, Strmvc = null, mar = null;
		vi = new Vector();
		Connection connection = null;
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		double total1 = 0.00, total2 = 0.00;
		double strweightage = 0.0, market = 0.00;
		String index12 = index;
		String index1, index2, tno1 = null;
		index1 = index2 = index;
		// app.Connect con=new app.Connect();
		Connect con = ConnectInit.getConnect();
		indweighttable = new ArrayList();
		ArrayList tempdata = new ArrayList();
		IndexWise indexwise;
		try {
			if (connection == null)
				connection = con.getdbConnection();

			rst = Icr.indweightResult(connection, "industry_wise_weightage",
					index12);
			int i = 0;
			Logging.debug("setVector_indweighttable of Industry wise weightage");
			try {

				while (rst.next()) {

					if (rst.getString(1) == null) {
						industryname = "0";
					} else {
						industryname = rst.getString(1);
						vi.add(i, rst.getString(1));
					}
					i++;
					if (rst.getString(2) == null) {
						marketcap = "0";
					} else {
						marketcap = rst.getString(2);
						marketcap = ad.indexcompose(marketcap);
						marketcap = org.jfree.chart.demo.servlet.AdjustDecimal
								.ArrangeAsNumeric(marketcap);
						vi.add(i, rst.getString(2));
					}
					i++;
					mar = rst.getString(2);
					mar = ad.indexcompose(mar);
					market = Double.parseDouble(mar);
					total2 = total2 + market;
					tno1 = ad.shareholdingpatt(total2);
					tno1 = org.jfree.chart.demo.servlet.AdjustDecimal
							.ArrangeAsNumeric(tno1);
					if (rst.getString(3) == null) {
						weightage = "0";
					} else {
						weightage = rst.getString(3);
						weightage = ad.indexcompose(weightage);
						vi.add(i, rst.getString(3));
					}
					i++;
					Strmvc = rst.getString(3);
					strweightage = Double.parseDouble(Strmvc);
					total1 = total1 + strweightage;
					indexwise = new IndexWise(industryname, marketcap,
							weightage);
					tempdata.add(indexwise);
				}
				rst.close();
			} catch (SQLException sqlexp) {
				Logging.error("Error : " + sqlexp.getMessage());
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
					Logging.error(" Error : Unable to close connection "
							+ ex.getMessage());
				}
				Logging.error(" Error : Unable to close connection "
						+ ee.getMessage());
			}
		}
		indweighttable = tempdata;
		Logging.debug(indweighttable);
		setTotal(total1);
		setVal(tno1);
		setVi(vi);
		return indweighttable;
	}

	/**
	 * @param vector_indtable
	 *            The vector_indtable to set.
	 */
	public void setIndweighttable(ArrayList indweighttable) {
		this.indweighttable = indweighttable;
	}

	/**
	 * @return Returns the graph.
	 */
	public String getGraph() {
		Logging.debug("in getter" + graph);
		return graph;
	}

	/**
	 * @param graph
	 *            The graph to set.
	 */
	public void setGraph(String graph) {
		this.graph = graph;
	}

	/**
	 * @return Returns the checkChart.
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename
	 *            The filename to set.
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getUserid1() {
		return userid1;
	}

	public void setUserid1(String userid1) {
		this.userid1 = userid1;
	}

	public String getRoleId_wtg() {
		return roleId_wtg;
	}

	public void setRoleId_wtg(String roleId_wtg) {
		this.roleId_wtg = roleId_wtg;
	}
}
