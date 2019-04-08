/**
 * @author Manoj Adekar
 *
 */
package harrier.income.com.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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

public class IndexGraphForm extends ActionForm {
	Logger Logging = Logger.getLogger(IndexGraphForm.class);
	private String from = null, go = null, clear = null, to = null,
			defaultVal = null, check = null, checkChart = null, rep_path = "";
	private Collection selectIndexCollection = null;
	private Collection daysCollection = null;
	private int days = 5;

	public Vector move14, move24, move34, move44, move54, move64, move74;
	static int intRep = 0;
	double total = 0.00;
//	AdjustDecimal ad = new AdjustDecimal();
	IndexComposeReportMethod Icr = new IndexComposeReportMethod();
	String indexName, graph, filename, computetotalreturns, b_showChild, query,
			index = null, compute, calc = "no";
	ArrayList tabledata = null;
	ArrayList compareData = null;
	ArrayList sectorData = null;
	Hashtable IndexNameHash = new Hashtable();

	Vector sect11 = null;
	Vector sect12 = null;

	Vector sect21 = null;
	Vector sect22 = null;

	Vector sect31 = null;
	Vector sect32 = null;

	// Vector move74=null;
	Vector move84 = null;
	Vector move94 = null;

	private ResultSet rst;

	// app.Connect con=new app.Connect();
	// private String[] indMulti={"3259","1593"};

	public IndexGraphForm() {
		from = null;
		go = null;
		to = null;
		clear = null;
		defaultVal = null;
		b_showChild = null;
		checkChart = null;
		this.check = null;
	}

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
		intRep++;
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
	 * @return Returns the filename.
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

	public String getB_showChild() {
		return b_showChild;
	}

	public void setB_showChild(String child) {
		b_showChild = child;

	}

	/*
	 * public void setComputetotalreturns(String computetotalreturns) {
	 * this.computetotalreturns = computetotalreturns; }
	 */

	Connect c = ConnectInit.getConnect();

	Collection indexcollection;

	public Collection getIndexcollection() {
		Connection connection = null;
		PreparedStatement pst = null;
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
			// Statement stmt = connection.createStatement();

			pst = connection.prepareStatement(query);
			pst.setString(1, "1");
			rs = pst.executeQuery();
			AcessControl asc = ConnectInit.getAcessControl();
	//		AcessControl asc = new AcessControl();
			String NotSelected = asc.getLangValues("Masters.NotSelected");
			Logging.debug(" Inside getIndexcollection(): Not Selected ="
					+ NotSelected);

			v.add(new LabelValueBean("Not Selected", "0"));
			while (rs.next()) {
				id1 = rs.getString(1);
				v.add(new LabelValueBean(rs.getString(2), id1));
				IndexNameHash.put(id1, rs.getString(2));
			}
			indexcollection = v;
		} catch (Exception e) {
			
			Logging.error(" Error :" + e.getMessage());
		//	e.printStackTrace();
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

	public String getIndexName() {

		try {
			String local_d1 = index;
			Logging.debug("INDEX id=" + local_d1);
			Enumeration e;
			String str;
			String iname = "", ival = "";
			e = IndexNameHash.keys();
			while (e.hasMoreElements()) {
				str = (String) e.nextElement();
				iname = (String) IndexNameHash.get(str);
				if (str.equals(local_d1)) {
					Logging.debug("Yes it is matched ");
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

	public void setIndexName(String indexName) {
		this.indexName = indexName;
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

	public String getGraph() {
		Logging.debug("in getter" + graph);
		return graph;
	}

	public void setGraph(String graph) {
		this.graph = graph;
	}

	public String getCompute() {
		return compute;
	}

	public void setCompute(String compute) {
		this.compute = compute;
	}

	public String getClear() {
		return clear;
	}

	public void setClear(String clear) {
		this.clear = clear;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		if (getCheckChart() != null && getCheckChart().trim().equals("checked"))
			this.check = check;
	}

	public String getCheckChart() {
		return checkChart;
	}

	public void setCheckChart(String checkChart) {
		this.checkChart = checkChart;
	}

	/*
	 * public void dbconnect(){
	 * 
	 * try {if(app.Connect.con==null){ con.getConnection(); } } catch (Exception
	 * e) { Logging.debug(e);}
	 * 
	 * }
	 */

	public String getFrom() {
		int daydiff = 0;
		if (days == 1) {
			daydiff = 1;
		}
		if (days == 2) {
			daydiff = 15;
		}
		if (days == 3) {
			daydiff = 30;
		}
		if (days == 4) {
			daydiff = 90;
		}
		if (days == 5) {
			daydiff = 182;
		}
		if (days == 6) {
			daydiff = 365;
		}
		long newd = daydiff * 24 * 60 * 60 * 1000L;
		java.util.Date s4 = new Date();
		long t2 = s4.getTime();
		long t1 = t2 - newd;
		Date s1 = new Date(t1);
		SimpleDateFormat ft1 = new SimpleDateFormat("dd-MM-yyyy");
		from = ft1.format(s1);

		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		java.util.Date s3 = new Date();
		long t2 = s3.getTime();
		Date s1 = new Date(t2);
		SimpleDateFormat ft1 = new SimpleDateFormat("dd-MM-yyyy");
		to = ft1.format(s1);
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getDefaultVal() {
		return defaultVal;
	}

	public void setDefaultVal(String defaultVal) {
		this.defaultVal = defaultVal;
	}

	/*
	 * public String getSelectIndex() { return selectIndex; } /**
	 * 
	 * @param select The select to set.
	 * 
	 * public void setSelectIndex(String selectIndex) { this.selectIndex =
	 * selectIndex; }
	 */

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public Collection getDaysCollection() {

		Vector v = new Vector();

		v.add(new LabelValueBean("15 day", "2"));
		v.add(new LabelValueBean("1 month", "3"));
		v.add(new LabelValueBean("3 month", "4"));
		v.add(new LabelValueBean("6 month", "5"));
		v.add(new LabelValueBean("12 month", "6"));
		// IndexNameHash.put(id1,rs.getString(2));

		daysCollection = v;

		return daysCollection;
	}

	public void setDaysCollection(Collection daysCollection) {
		this.daysCollection = daysCollection;
	}

	// from for moving index

	private Vector var_Table_data_vector = null;

	public Vector getVar_Table_data_vector() {
		return var_Table_data_vector;
	}

	public void setVar_Table_data_vector(Vector var_Table_data_vector) {
		this.var_Table_data_vector = var_Table_data_vector;
	}

	// industry weightage
	// ArrayList indweighttable=null;
	private Vector vi;
	private String val = null;

	public Vector getVi() {
		return vi;
	}

	public void setVi(Vector vi) {
		this.vi = vi;
	}

	public String getCalc() {
		return calc;
	}

	public void setCalc(String calc) {
		this.calc = calc;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public void setCompareData(ArrayList compareData) {
		this.compareData = compareData;
	}

	public ArrayList getCompareData() {
		Connection connection = null;
		String fdate = getFrom();
		String tdate = getTo();
		IndexMovingDetail IMdetails = null;
		ArrayList indcompare1 = new ArrayList();
		try {
			move14 = new Vector();
			move24 = new Vector();
			move34 = new Vector();
			move44 = new Vector();
			move54 = new Vector();
			move64 = new Vector();
			move74 = new Vector();
			move84 = new Vector();
			move94 = new Vector();

			String s1 = getIndex();
			// String[] s2=getIndMulti();
			int i;
			String ide = null;
			// app.Connect con = new app.Connect();
			Connect con = ConnectInit.getConnect();
			for (i = 0; i <= 0; i++) {
				// Change by Manoj Adekar for Dynamic connection using
				// getdbConnection() instead of getConnection()
				if (connection == null) {
					connection = con.getdbConnection();
				}
				if (i == 0) {
					ide = s1;
				}
				// else{ide=s2[i-1];}

				PreparedStatement pst = connection.prepareStatement(ConnectInit.queries
						.getProperty("moving_index_value"));
				pst.setString(1, ide);
				pst.setString(2, fdate);
				pst.setString(3, tdate);

				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					String d = rs.getString("index_value_date");
					// move74.add(d);

					String year1 = rs.getString("index_closing_value");

					if (year1 != null)
						if (year1.equals(""))
							year1 = null;
					if (i == 0) {
						if (year1 == null) {
							move44.add("0.00");
							// DatasetFactory1.field4.add("0.00");
							Logging.debug("closing value is null "
									+ year1);
						} else {
							move44.add(year1);
							// DatasetFactory1.field4.add(year1);
							Logging.debug("closing value is " + year1);
						}
						move74.add(d);
					}
					if (i == 1) {
						if (year1 == null) {
							move54.add("0.00");
							// DatasetFactory1.field4.add("0.00");
							Logging.debug("closing value is null "
									+ year1);
						} else {
							move54.add(year1);
							// DatasetFactory1.field4.add(year1);
							Logging.debug("closing value is " + year1);
						}
						move84.add(d);
					}
					if (i == 2) {
						if (year1 == null) {
							move64.add("0.00");
							// DatasetFactory1.field4.add("0.00");
							Logging.debug("closing value is null "
									+ year1);
						} else {
							move64.add(year1);
							// DatasetFactory1.field4.add(year1);
							Logging.debug("closing value is " + year1);
						}
						move94.add(d);
					}
				}
			}
			int j;
			String ind1 = "";
			String ind2 = "";
			String ind3 = "";

			Vector ParSect = new Vector();
			// ////////////////////
			int leng = 0;
			int leng1 = move94.size();
			int leng2 = move84.size();
			int leng3 = 0;
			int indLength = 0;
			if (indLength > 2)
				leng3 = move74.size();

			if (indLength > 2) {
				if (leng1 < leng2) {
					if (leng2 < leng3) {
						leng = leng3;
						ParSect = move74;
					} else {
						leng = leng2;
						ParSect = move84;
					}

				} else {
					if (leng1 < leng3) {
						leng = leng3;
						ParSect = move74;
					} else {
						leng = leng1;
						ParSect = move84;
					}

				}
			} else {
				if (leng1 < leng2) {
					leng = leng2;
					ParSect = move84;
				} else {
					leng = leng1;
					ParSect = move94;
				}

			}

			for (j = 0; j <= (ParSect.size() - 1); j++) {
				try {
					ind1 = (String) move44.get(j);

				} catch (Exception E) {
					ind1 = "0.0";
				}
				try {
					ind2 = (String) move54.get(j);

				} catch (Exception E) {
					ind2 = "0.0";
				}
				try {
					ind3 = (String) move64.get(j);

				} catch (Exception E) {
					ind3 = "0.0";
				}

				IMdetails = new IndexMovingDetail((String) ParSect.get(j),
						ind1, ind2, ind3);
				indcompare1.add(IMdetails);
			}
		} catch (SQLException e) {
			Logging.debug(e + "");
	/*		JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Unable To Connect DataBase",
					"ERROR!", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
*/
		} catch (Exception e) {
			Logging.debug(e + "");
		}
		// Close the Dynamic Connection : Manoj Adekar
		finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		Logging.debug("abc");
		compareData = indcompare1;
		return compareData;
	}

	public String getRep_path() {
		return rep_path;
	}

	public void setRep_path(String rep_path) {
		this.rep_path = rep_path;
	}

}
