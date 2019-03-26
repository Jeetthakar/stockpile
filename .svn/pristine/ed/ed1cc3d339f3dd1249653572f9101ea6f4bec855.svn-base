/**
 * @author lokesh
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
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;
import org.jfree.chart.demo.servlet.AdjustDecimal;

import app.AcessControl;
import app.Connect;

import com.harrier.initializeation.ConnectInit;

public class PortfolioReportForm extends ActionForm {
	Logger Logging = Logger.getLogger(PortfolioReportForm.class);
	private String from = null, go = null, clear = null, to = null,
			defaultVal = null, check = null, checkChart = null,
			selectIndex = "3259", rep_path = "", userid1, roleId_port;
	private Collection selectIndexCollection = null;
	private Collection daysCollection = null;
	private int days = 5;

	public Vector move14, move24, move34, move44, move54, move64, move74;
	private Vector vw;
	static int intRep = 0;
	double total = 0.00;
	// AdjustDecimal ad = new AdjustDecimal();
	IndexComposeReportMethod Icr = new IndexComposeReportMethod();
	String indexName, graph, filename, computetotalreturns, b_showChild, query,
			index = "3259", indexcmp = "3252", index3 = "3391", compute,
			calc = "no";
	ArrayList tabledata = null;
	ArrayList compareData = null;
	ArrayList sectorData = null;
	Hashtable IndexNameHash = new Hashtable();
	PreparedStatement pst;
	ResultSet rs, rs1;
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
	private String[] indMulti = { "3252", "3391" };

	public PortfolioReportForm() {
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
		if (getIndMulti().length > 2)
			errors.add(null, new ActionError(
					"Error.message.Do_not_select_More_than_two_benchmark"));
		// errors.add(null,new
		// ActionError("Error.message.from_date_greaterthan_todate"));

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
		//	AcessControl asc = new AcessControl();
			AcessControl asc = ConnectInit.getAcessControl();
			String NotSelected = asc.getLangValues("Masters.NotSelected");
			Logging.debug(" Inside getIndexcollection(): Not Selected ="
					+ NotSelected);

			v.add(new LabelValueBean("Not Selected", "0"));
			while (rs.next()) {
				id1 = rs.getString(1);
				v.add(new LabelValueBean(rs.getString(2), id1));

			}

			// Change by Manoj adekar
			int role_id2 = Integer.parseInt(roleId_port);
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

	public Vector getVw() {
		if (vw != null)
			Logging.debug(" size of vector " + vw.size());
		return vw;
	}

	/**
	 * @return Returns the vw.
	 */
	public void setVw(Vector vw) {
		this.vw = vw;
	}

	/**
	 * @return Returns the indexName.
	 */
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
			Logging.debug(e);
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
	 * @return Returns the total.
	 */
	public double getTotal() {
//		org.jfree.chart.demo.servlet.AdjustDecimal ad = new org.jfree.chart.demo.servlet.AdjustDecimal();
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

	/**
	 * @param indexcollection
	 *            The indexcollection to set.
	 */
	public void setIndexcollection(Collection indexcollection) {
		this.indexcollection = indexcollection;
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
	 * @return Returns the selectCollection.
	 */
	public ArrayList getTabledata() {
		String stockid = null, stockname = null, currency = null, tis = null, iwf = null, adjusted = null, mcv = null, stockprice = null, market = null, last = null, curr_exch_convIratecomp1 = null, strweightage1 = null;
		String index12 = getIndex();
		Connection connection = null;

		double total1 = 0.00;
		vw = new Vector();
		double tmcv = 0.0;

		ArrayList tempdata = new ArrayList();
		String index_id, date;
//		org.jfree.chart.demo.servlet.AdjustDecimal ad = new org.jfree.chart.demo.servlet.AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		try {
			if (connection == null)
				connection = c.getdbConnection();
			ResultSet tmcvrst = Icr.stiockweightageLatestResult(connection,
					"get_tmcv_for_composition", index12);
			Logging.debug("get tmcv of Compose Index");
			try {
				while (tmcvrst.next()) {
					index_id = tmcvrst.getString(1);
					tmcv = tmcvrst.getDouble(2);
					Logging.debug("tmcv is " + tmcv);
					date = tmcvrst.getString(3);

				}
			} catch (SQLException sqlexp) {
				Logging.error("SQL Error :" + sqlexp.getMessage());
			}

			String curr_exch_convIratecomp = null, strmcv = null;
			double weightage = 0.0, mcve = 0.0;
			rst = Icr.indexComposeResult(connection,
					"get_composition_for_compose_report", index12);
			int i = 0;
			int n = 0;

			tabledata = new ArrayList();
			IndexCompose12 indexcompose1;
			Logging.debug("setVector_tabledata of Compose Index");
			try {
				while (rst.next()) {
					// Paragraph p1= new Paragraph();
					StringBuffer sb1 = new StringBuffer();
					// sb.append("<stock>");
					if (rst.getString(1) == null) {
						stockid = "0";
					} else {
						stockid = rst.getString(1);
						vw.add(i, rst.getString(1));

					}
					i++;
					n++;
					if (rst.getString(2) == null) {
						stockname = "0";
					} else {
						stockname = rst.getString(2);
						vw.add(i, rst.getString(2));
						// sb.append("<stock_name>"+"<![CDATA["+stockname+"]]>"+"</stock_name>");
						sb1.append(stockname);
						int rr = stockname.trim().length();
						int jj = 90;
						int kk = jj - rr;
						for (int g = 0; g < kk; g++) {
							sb1.append(" ");
						}
						// p1.add(n,stockname);
					}
					i++;
					if (rst.getString(4) == null) {
						tis = "0";
					} else {
						tis = rst.getString(4);
						tis = AdjustDecimal.ArrangeAsNumeric(tis);
						vw.add(i, rst.getString(4));
						// sb.append("<tis>"+tis+"</tis>");
						sb1.append(tis);
						int rr = tis.trim().length();
						int jj = 20;
						int kk = jj - rr;
						for (int g = 0; g < kk; g++) {
							sb1.append(" ");
						}
						// p1.add(n,tis);
					}
					i++;
					if (rst.getString(5) == null) {
						iwf = "0";
					} else {
						iwf = rst.getString(5);
						iwf = ad.indexcompose(iwf);
						iwf = AdjustDecimal.ArrangeAsNumeric(iwf);
						// sb.append("<iwf>"+iwf+"</iwf>");
						vw.add(i, rst.getString(5));
						sb1.append(iwf);
						int rr = iwf.trim().length();
						int jj = 20;
						int kk = jj - rr;
						for (int g = 0; g < kk; g++) {
							sb1.append(" ");
						}
						// p1.add(n,iwf);
					}
					i++;
					if (rst.getString(9) == null) {
						market = "0";
					} else {
						market = rst.getString(9);
						vw.add(i, rst.getString(9));
						// sb.append("<market>"+market+"</market>");
						sb1.append(market);
						int rr = market.trim().length();
						int jj = 20;
						int kk = jj - rr;
						for (int g = 0; g < kk; g++) {
							sb1.append(" ");
						}
						// p1.add(n,market);
					}
					i++;
					if (rst.getString(6) == null) {
						adjusted = "0";
					} else {
						adjusted = rst.getString(6);
						adjusted = ad.indexcompose(adjusted);
						adjusted = AdjustDecimal.ArrangeAsNumeric(adjusted);
						// sb.append("<adjusted>"+adjusted+"</adjusted>");
						vw.add(i, rst.getString(6));
						sb1.append(adjusted);
						int rr = adjusted.trim().length();
						int jj = 20;
						int kk = jj - rr;
						for (int g = 0; g < kk; g++) {
							sb1.append(" ");
						}
						// p1.add(n,adjusted);
					}
					i++;
					if (rst.getString(10) == null) {
						last = "0";
					} else {
						last = rst.getString(10);
						last = ad.indexcompose(last);
						last = AdjustDecimal.ArrangeAsNumeric(last);
						// sb.append("<last>"+last+"</last>");
						vw.add(i, rst.getString(10));
						sb1.append(last);
						int rr = last.trim().length();
						int jj = 20;
						int kk = jj - rr;
						for (int g = 0; g < kk; g++) {
							sb1.append(" ");
						}
						// p1.add(n,last);
					}
					i++;
					if (rst.getString(3) == null) {
						currency = "0";
					} else {
						currency = rst.getString(3);
						vw.add(i, rst.getString(3));
						// sb.append("<currency>"+currency+"</currency>");
						sb1.append(currency);
						int rr = currency.trim().length();
						int jj = 20;
						int kk = jj - rr;
						for (int g = 0; g < kk; g++) {
							sb1.append(" ");
						}
						// p1.add(n,currency);
					}

					i++;
					curr_exch_convIratecomp = getCurrancyExchRate(index12,
							stockid);
					// app.Logging.getDebug("curr_exch_convIrate is "+curr_exch_convIrate);
					curr_exch_convIratecomp = ad
							.indexcompose4digit(curr_exch_convIratecomp);
					if (curr_exch_convIratecomp == null) {
						curr_exch_convIratecomp = "0";
						vw.add(i, curr_exch_convIratecomp);
					} else {
						curr_exch_convIratecomp1 = curr_exch_convIratecomp;
						// sb.append("<curr_exch>"+curr_exch_convIratecomp+"</curr_exch>");
						vw.add(i, curr_exch_convIratecomp);
						sb1.append(curr_exch_convIratecomp);
						int rr = curr_exch_convIratecomp.trim().length();
						int jj = 20;
						int kk = jj - rr;
						for (int g = 0; g < kk; g++) {
							sb1.append(" ");
						}
						// p1.add(n,curr_exch_convIratecomp);
					}

					i++;
					if (rst.getString(7) == null) {
						mcv = "0";
					} else {
						mcv = rst.getString(7);
						mcv = ad.indexcompose(mcv);
						mcv = AdjustDecimal.ArrangeAsNumeric(mcv);
						// sb.append("<mcv>"+mcv+"</mcv>");
						vw.add(i, rst.getString(7));
						sb1.append(mcv);
						int rr = mcv.trim().length();
						int jj = 30;
						int kk = jj - rr;
						for (int g = 0; g < kk; g++) {
							sb1.append(" ");
						}
						// p1.add(n,mcv);
					}
					i++;
					if (rst.getString(7) == null) {
						mcv = "0";
					} else {
						mcv = rst.getString(7);
						mcv = ad.indexcompose(mcv);
						mcv = AdjustDecimal.ArrangeAsNumeric(mcv);
						vw.add(i, rst.getString(7));
					}
					i++;
					strmcv = rst.getString(7);
					mcve = Double.parseDouble(strmcv);
					if (tmcv != 0.0) {
						weightage = (mcve / tmcv) * 100.00;
					}
					total1 = total1 + weightage;
					String strweightage = new Double(weightage).toString();
					strweightage = ad.shareholdingpatt(strweightage);
					strweightage = ad.indexcompose4digit(strweightage);
					strweightage = AdjustDecimal.ArrangeAsNumeric(strweightage);
					strweightage1 = strweightage;
					// sb.append("<weightage>"+strweightage1+"</weightage>");
					sb1.append(strweightage1);
					// p1.add(n,strweightage1);
					vw.add(i, strweightage1);
					// weightage
					/*
					 * if (rst.getString(8) == null) { vector_tabledata.add(i,
					 * "0"); } else { vector_tabledata.add(i, rst.getString(8));
					 * }
					 */

					i++;
					if (rst.getString(8) == null) {
						stockprice = "-0";
					} else {
						stockprice = rst.getString(8);
						vw.add(i, rst.getString(8));
					}
					i++;
					indexcompose1 = new IndexCompose12(stockid, stockname,
							currency, tis, iwf, adjusted, mcv, stockprice,
							market, last, curr_exch_convIratecomp1,
							strweightage1);
					tempdata.add(indexcompose1);
					// sb.append("</stock>");
					// document.add(new Paragraph(sb1.toString()));

				}
				rst.close();
				// sb.append("</Index_report>");
				/*
				 * try{ boolean flag1=new File("c:\\report\\Harrier").mkdir();
				 * String rep="report"+intRep; BufferedWriter bufferedWriter =
				 * new BufferedWriter(new OutputStreamWriter(new
				 * FileOutputStream("c:\\report\\Harrier"+rep+".xml")));
				 * 
				 * bufferedWriter.write(sb.toString()); bufferedWriter.flush();
				 * bufferedWriter.close();
				 * 
				 * Logging.getError("naresh"+sb.toString()); }catch(Exception
				 * e){ Logging.getError("nareshhhhhhhhh"+e); }
				 */
			} catch (SQLException sqlexp) {
				Logging.error("SQL Error :" + sqlexp.getMessage());
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

		tabledata = tempdata;

		setTotal(total1);
		setVw(vw);

		/*
		 * } catch(IOException de) { System.err.println(de.getMessage()); }
		 * catch(Exception ioe) { System.err.println(ioe.getMessage()); }
		 * document.close(); document1.close();
		 */
		return tabledata;
	}

	/**
	 * @return Returns the selectCollection.
	 */

	public void setTabledata(ArrayList tabledata) {
		this.tabledata = tabledata;
	}

	public String getCurrancyExchRate(String index12, String stockid) {
		String cexch_rate = null;
		String stk_currency_id = null, ind_currency_id = null;
		// app.Connect con=new app.Connect();
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		if (connection == null) {
			connection = con.getdbConnection();
		}
		try {
			// app.Logging.getDebug("inside getCurrancyExchRate");
			ResultSet rstexc = Icr.indwtResult(connection,
					"get_index_and_stock_currency_id", stockid, index12);
			int i = 0;
			Logging.debug("rst is " + rstexc);
			while (rstexc.next()) {
				if (rstexc.getString(1) == null) {
					stk_currency_id = "0";
				} else {
					stk_currency_id = (String) rstexc.getString(1);
				}
				if (rstexc.getString(2) == null) {
					ind_currency_id = "0";
				} else {
					ind_currency_id = (String) rstexc.getString(2);
				}
			}
			// app.Logging.getDebug("stk_currency_id is "+stk_currency_id+" ind_currency_id is "+ind_currency_id);
			if (stk_currency_id.equals(ind_currency_id)) {
				cexch_rate = "1.00";
			} else {
				/*
				 * ResultSet rst11 =
				 * con.indwtResult("get_currency_exchange_rate",
				 * ind_currency_id,stk_currency_id); while (rst11.next()) { if
				 * (rst.getString(1) == null) { cexch_rate="0"; }else{
				 * cexch_rate=(String)rst11.getString(1); } }
				 */

				String temp = Icr.getIndexCurrancyExchRate(connection,
						stk_currency_id, ind_currency_id);
				double exch = 0.0;
				if (temp != null) {
					exch = new Double(temp).doubleValue();
				} else {
					temp = Icr.getIndexCurrancyExchRate(connection,
							ind_currency_id, stk_currency_id);
					if (temp == null) {
						exch = 1.0;
					} else {
						exch = 1 / new Double(temp).doubleValue();
					}
				}
				cexch_rate = new Double(exch).toString();
				Logging.debug("currency exchange rate is " + cexch_rate);
			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :" + sqlexp.getMessage());
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
		return cexch_rate;
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
	 * @return
	 */
	public String getCheck() {
		return check;
	}

	/**
	 * 
	 * @param check
	 *            The check to set
	 */
	public void setCheck(String check) {
		if (getCheckChart() != null && getCheckChart().trim().equals("checked"))
			this.check = check;
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
	 * @return Returns the from.
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

	/**
	 * @param from
	 *            The fromDate to set.
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return Returns the toDate.
	 */
	public String getTo() {
		java.util.Date s3 = new Date();
		long t2 = s3.getTime();
		Date s1 = new Date(t2);
		SimpleDateFormat ft1 = new SimpleDateFormat("dd-MM-yyyy");
		to = ft1.format(s1);
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

	public String getSelectIndex() {
		return selectIndex;
	}

	/**
	 * @param select
	 *            The select to set.
	 */
	public void setSelectIndex(String selectIndex) {
		this.selectIndex = selectIndex;
	}

	/**
	 * @return Returns the days.
	 */
	public int getDays() {
		return days;
	}

	/**
	 * @param days
	 *            The days to set.
	 */
	public void setDays(int days) {
		this.days = days;
	}

	/**
	 * @return Returns the daysCollection.
	 */
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

	/**
	 * @param daysCollection
	 *            The daysCollection to set.
	 */
	public void setDaysCollection(Collection daysCollection) {
		this.daysCollection = daysCollection;
	}

	// from for moving index

	private ArrayList indexMovingTable = null;
	private Vector var_Table_data_vector = null;

	public ArrayList getIndexMovingTable() {
		ArrayList Table_data = new ArrayList();
		Vector Table_data_vector = new Vector();
		String tradingDate = null;
		String close = null;
		String mCap = null;
		String divisor = null;
		// String fodate =getMove_from();
		// String todate= getMove_to();
		// String index= getSelectIndex();
		String fodate = getFrom();
		// setFrom("01-08-2007");
		String todate = getTo();
		// setTo("22-08-2007");
		// String index= index;

		// app.Connect con=new app.Connect();
		Connect con = ConnectInit.getConnect();
		Connection connection = null;

//		AdjustDecimal ad = new AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		try {
			if (connection == null)
				connection = null;
			connection = con.getdbConnection();
			if (fodate != null && todate != null) {
				try {

					PreparedStatement pst;

					pst = connection.prepareStatement(ConnectInit.queries
							.getProperty("moving_index_value1"));
					pst.setString(1, index);
					pst.setString(2, fodate);
					pst.setString(3, todate);
					ResultSet rst = pst.executeQuery();
					// vExcel.clear();
					int i = 0;
					int ii = 0;
					while (rst.next()) {
						if (rst.getString(1) == null) {
							tradingDate = "--";
						} else {
							tradingDate = rst.getString(1);
							Table_data_vector.add(i, tradingDate);
							i++;
						}
						// vExcel.add(ii,tradingDate);
						ii++;

						if (rst.getString(2) == null) {
							close = "0";
						} else {
							String strclose = (String) rst.getString(2);
							close = ad.indexcompose(strclose);
							close = AdjustDecimal.ArrangeAsNumeric(close);
							Table_data_vector.add(i, close);
							i++;
						}
						// vExcel.add(ii,close);
						ii++;

						if (rst.getString(3) == null) {
							mCap = "0";
						} else {
							double mcv = (double) rst.getDouble(3);
							mcv = mcv / 1000000.0;
							String strmcv = new Double(mcv).toString();
							strmcv = ad.shareholdingpatt(strmcv);
							mCap = ad.indexcompose(strmcv);
							mCap = AdjustDecimal.ArrangeAsNumeric(mCap);
							Table_data_vector.add(i, mCap);
							i++;

						}
						// vExcel.add(ii,mCap);
						ii++;

						if (rst.getString(4) == null) {
							divisor = "0";
						} else {
							double mcv = (double) rst.getDouble(4);
							String strmcv = new Double(mcv).toString();
							strmcv = ad.shareholdingpatt(strmcv);
							divisor = ad.indexcompose(strmcv);
							divisor = AdjustDecimal.ArrangeAsNumeric(divisor);
							Table_data_vector.add(i, divisor);
							i++;

						}
						// vExcel.add(ii,divisor);
						ii++;

						// Table_data.add(new
						// IndexMovingDetail(tradingDate,close,mCap,divisor, pe,
						// pb,divYield));
						Table_data.add(new IndexMovingDetail(tradingDate,
								close, mCap, divisor));

					}
					setVar_Table_data_vector(Table_data_vector);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
				//	e.printStackTrace();
					Logging.debug(e);
				}
			}
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}

		indexMovingTable = Table_data;

		return indexMovingTable;
	}

	public void setIndexMovingTable(ArrayList indexMovingTable) {
		this.indexMovingTable = indexMovingTable;
	}

	public Vector getVar_Table_data_vector() {
		return var_Table_data_vector;
	}

	public void setVar_Table_data_vector(Vector var_Table_data_vector) {
		this.var_Table_data_vector = var_Table_data_vector;
	}

	// industry weightage
	ArrayList indweighttable = null;
	private Vector vi;
	private String val = null;

	public Vector getVi() {
		return vi;
	}

	/**
	 * @param indWeightageVector
	 *            The indWeightageVector to set.
	 */
	public void setVi(Vector vi) {
		this.vi = vi;
	}

	public String getCalc() {
		return calc;
	}

	/**
	 * @param calc
	 *            The calc to set.
	 */
	public void setCalc(String calc) {
		this.calc = calc;
	}

	/**
	 * @return Returns the indexcmp.
	 */
	public String getIndexcmp() {
		return indexcmp;
	}

	/**
	 * @param index3
	 *            The indexcmp to set.
	 */
	public void setIndexcmp(String indexcmp) {
		this.indexcmp = indexcmp;
	}

	/**
	 * @return Returns the index3.
	 */
	public String getIndex3() {
		return index3;
	}

	/**
	 * @param index3
	 *            The index3 to set.
	 */
	public void setIndex3(String index3) {
		this.index3 = index3;
	}

	/**
	 * @return Returns the indMulti.
	 */
	public String[] getIndMulti() {
		return indMulti;
	}

	/**
	 * @param indMulti
	 *            The indMulti to set.
	 */
	public void setIndMulti(String[] indMulti) {
		this.indMulti = indMulti;
	}

	public ArrayList getIndweighttable() {
		String industryname = null, marketcap = null, weightage = null, Strmvc = null, mar = null;
		vi = new Vector();
		Connection connection = null;
		double total1 = 0.00, total2 = 0.00;
		double strweightage = 0.0, market = 0.00;
		String index12 = index;
		String tno1 = null;

		// app.Connect con=new app.Connect();
		Connect con = ConnectInit.getConnect();
		indweighttable = new ArrayList();
		ArrayList tempdata = new ArrayList();
		IndexWise indexwise;
		try {
			AdjustDecimal ad = ConnectInit.getAdjustDecimal();
			if (connection == null) {
				connection = con.getdbConnection();
			}

			rst = Icr.indweightResult(connection, "industry_wise_weightage",
					index12);
			int i = 0;
			Logging
					.debug("setVector_indweighttable of Industry wise weightage");
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
			String[] s2 = getIndMulti();
			int i;
			String ide = null;
			// app.Connect con = new app.Connect();
			Connect con = ConnectInit.getConnect();
			for (i = 0; i <= s2.length; i++) {
				// Change by Manoj Adekar for Dynamic connection using
				// getdbConnection() instead of getConnection()
				if (connection == null) {
					connection = con.getdbConnection();
				}
				if (i == 0) {
					ide = s1;
				} else {
					ide = s2[i - 1];
				}

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

			// //////////////////////////////

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
				/*
				 * String year=d.substring(6,10); String month=d.substring(3,5);
				 * String day=d.substring(0,2); move14.add(year);
				 * move24.add(month); move34.add(day);
				 */
				IMdetails = new IndexMovingDetail((String) ParSect.get(j),
						ind1, ind2, ind3);
				indcompare1.add(IMdetails);
			}
		} catch (SQLException e) {
			Logging.debug(e + "");
	/*	JFrame frame = new JFrame();
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
				if (connection != null) {
				}
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

	/**
	 * @return Returns the rep_path.
	 */
	public String getRep_path() {
		return rep_path;
	}

	/**
	 * @param rep_path
	 *            The rep_path to set.
	 */
	public void setRep_path(String rep_path) {
		this.rep_path = rep_path;
	}

	public void setSectorData(ArrayList sectorData) {
		this.sectorData = sectorData;
	}

	public ArrayList getSectorData() {
		Connection connection = null;
		// String fdate=getFrom();
		// String tdate=getTo();
		IndexMovingDetail IMdetails = null;
		ArrayList indSector1 = new ArrayList();
		try {

			sect11 = new Vector();
			sect12 = new Vector();

			sect21 = new Vector();
			sect22 = new Vector();

			sect31 = new Vector();
			sect32 = new Vector();

			String s1 = getIndex();
			String[] s2 = getIndMulti();
			int i;
			String ide = null;
			// app.Connect con = new app.Connect();
			Connect con = ConnectInit.getConnect();
			for (i = 0; i <= s2.length; i++) {
				// Change by Manoj Adekar for Dynamic connection using
				// getdbConnection() instead of getConnection()
				if (connection == null) {
					connection = con.getdbConnection();
				}
				if (i == 0) {
					ide = s1;
				} else {
					ide = s2[i - 1];
				}

				// PreparedStatement pst =
				// Connect.con.prepareStatement(con.queries.getProperty("select_sectorwise_index"));
				PreparedStatement pst = connection.prepareStatement(ConnectInit.queries
						.getProperty("industry_wise_weightage"));
				pst.setString(1, ide);
			//	pst.setString(2, ide);
			//	pst.setString(3, ide);

				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					String sector = rs.getString(1);
					// sect2.add(rs.getString(2));
					String wtg1 = rs.getString(3);

					if (sector != null)
						if (sector.equals(""))
							sector = null;
					if (i == 0) {
						if (sector == null) {
							sect11.add("0.00");

						} else {
							sect11.add(sector);

						}
						if (wtg1 == null) {
							sect12.add("0.00");

						} else {
							sect12.add(wtg1);

						}
					}
					if (i == 1) {
						if (sector == null) {
							sect21.add("0.00");

						} else {
							sect21.add(sector);

						}
						if (wtg1 == null) {
							sect22.add("0.00");

						} else {
							sect22.add(wtg1);

						}
					}
					if (i == 2) {
						if (sector == null) {
							sect31.add("0.00");

						} else {
							sect31.add(sector);

						}
						if (wtg1 == null) {
							sect32.add("0.00");

						} else {
							sect32.add(wtg1);

						}
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
			int leng1 = sect11.size();
			int leng2 = sect21.size();
			int leng3 = 0;
			int indLength = 0;
			if (indLength > 2)
				leng3 = sect31.size();

			if (indLength > 2) {
				if (leng1 < leng2) {
					if (leng2 < leng3) {
						leng = leng3;
						ParSect = sect31;
					} else {
						leng = leng2;
						ParSect = sect21;
					}

				} else {
					if (leng1 < leng3) {
						leng = leng3;
						ParSect = sect31;
					} else {
						leng = leng1;
						ParSect = sect11;
					}

				}
			} else {
				if (leng1 < leng2) {
					leng = leng2;
					ParSect = sect21;
				} else {
					leng = leng1;
					ParSect = sect11;
				}

			}

			// //////////////////////////////

			for (j = 0; j <= (leng - 1); j++) {
				try {
					ind1 = (String) sect12.get(j);

				} catch (Exception E) {
					ind1 = "0.0";
				}
				try {
					ind2 = (String) sect22.get(j);

				} catch (Exception E) {
					ind2 = "0.0";
				}
				try {
					ind3 = (String) sect32.get(j);

				} catch (Exception E) {
					ind3 = "0.0";
				}

				IMdetails = new IndexMovingDetail((String) ParSect.get(j),
						ind1, ind2, ind3);
				indSector1.add(IMdetails);
			}
		} catch (SQLException e) {
			Logging.debug(e + "");
		/*	JFrame frame = new JFrame();
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
				if (connection != null) {
				}
				connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		Logging.debug("abc");
		sectorData = indSector1;
		return sectorData;
	}

	public String getUserid1() {
		return userid1;
	}

	public void setUserid1(String userid1) {
		this.userid1 = userid1;
	}

	public String getRoleId_port() {
		return roleId_port;
	}

	public void setRoleId_port(String roleId_port) {
		this.roleId_port = roleId_port;
	}

}
