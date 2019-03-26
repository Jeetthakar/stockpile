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

public class IndexComposeForm extends ActionForm {
	Logger Logging = Logger.getLogger(IndexComposeForm.class);
	private String from = null, go = null, clear = null, to = null,
			defaultVal = null, check = null, checkChart = null,
			selectIndex = null, role_id1;
	private Collection selectIndexCollection = null;
	private Vector vw;
	static int intRep = 0;
	double total = 0.00;
	// AdjustDecimal ad = new AdjustDecimal();
	IndexComposeReportMethod Icr = new IndexComposeReportMethod();
	String indexName, graph, filename, computetotalreturns, b_showChild, query,
			index, compute, userid1;
	ArrayList tabledata = null;
	Hashtable IndexNameHash = new Hashtable();
	PreparedStatement pst;
	ResultSet rs, rs1;
	private ResultSet rst;

	// app.Connect con=new app.Connect();

	public IndexComposeForm() {
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
		// String query1
		// =c.queries.getProperty("index_list_without_child_bench");
		String id1 = null;
		Vector v = new Vector();

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
			int role_id2 = Integer.parseInt(role_id1);
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
		String adjustedmarketcap = null;
		String index12 = index;
		Connection connection = null;
		/*
		 * Rectangle pageSize = new Rectangle(0,0,2382,3369); Document document
		 * = new Document(pageSize); Document document1 = new
		 * Document(pageSize);
		 */
		/*
		 * try { PdfWriter.getInstance(document, new
		 * FileOutputStream("C:\\report\\filename.pdf")); document.open(); //
		 * document.addTitle("Index Composition Report"); document.add((new
		 * Paragraph("Index Composition Report")));
		 * 
		 * // document.addTitle("Index Composition Report");
		 */

		double total1 = 0.00;
		vw = new Vector();
		double tmcv = 0.0;
		/*
		 * StringBuffer sb2= new StringBuffer(); StringBuffer sb= new
		 * StringBuffer();
		 * sb.append("<?xml version='1.0' encoding='ISO-8859-1'?>");
		 * //sb.append(
		 * "<?xml-stylesheet type='text/css' href='c:\\report\\report.css'?>");
		 * sb.append(
		 * "<?xml-stylesheet type='text/xsl' href='c:\\report\\report1.xsl'?>");
		 * 
		 * sb.append("<Index_report name='NIFTY'>");
		 */
		ArrayList tempdata = new ArrayList();
		String index_id, date;
		// org.jfree.chart.demo.servlet.AdjustDecimal ad = new
		// org.jfree.chart.demo.servlet.AdjustDecimal();
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
						// commented by samiksha
						/*
						 * last = rst.getString(10); last =
						 * ad.indexcompose(last); last =
						 * AdjustDecimal.ArrangeAsNumeric(last); //
						 * sb.append("<last>"+last+"</last>"); vw.add(i,
						 * rst.getString(10));
						 * vw.add(i, temp);
						 */

						// modified by samiksha starts
						String temp = rst.getString(10);
						System.out.println("Temp *** " + temp);
						last = ad.indexcompose(temp);
						System.out.println("Last *** " + last);
						last = AdjustDecimal.ArrangeAsNumeric(last);
						int capacity = vw.capacity();
						System.out.println("Capacity *** " + capacity);
						int size = vw.size();
						System.out.println("Size *** " + size);
						System.out.println("iiii *** " + i);
						vw.add(i-1, temp);
						// ends

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
						//modifeid by samiskha
						vw.add(i-1, rst.getString(3));
						//commented by samiksha
//						vw.add(i, rst.getString(3));
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
						//modified by samiksha
						vw.add(i-1, curr_exch_convIratecomp);
						//commented by samiksha
//						vw.add(i, curr_exch_convIratecomp);
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
						/*
						 * mcv=rst.getString(7); mcv=ad.indexcompose(mcv);
						 * mcv=AdjustDecimal.ArrangeAsNumeric(mcv);
						 */

						// sb.append("<mcv>"+mcv+"</mcv>");
						// vw.add(i,rst.getString(7)); // commented by neha
						// 15-01-2009 for editable iwf
						double mcvnew = (Double.parseDouble(rst.getString(7)))
								/ (Double.parseDouble(rst.getString(5)));
						mcv = new Double(mcvnew).toString();

						mcv = ad.shareholdingpatt(mcv);
						mcv = ad.indexcompose4digit(mcv);
						mcv = AdjustDecimal.ArrangeAsNumeric(mcv);
						//modified by samiksha 
						vw.add(i-1, mcv);
						//commented by samiksha
//						vw.add(i, mcv);
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
						adjustedmarketcap = "0";
					} else {
						adjustedmarketcap = rst.getString(7);
						adjustedmarketcap = ad.indexcompose(adjustedmarketcap);
						adjustedmarketcap = AdjustDecimal
								.ArrangeAsNumeric(adjustedmarketcap);
						//modiifed by samiskha
						vw.add(i-1, rst.getString(7));
						//commented by samiksha
//						vw.add(i, rst.getString(7));
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
					//modified by samiksha
					vw.add(i-1, strweightage1);
					//commented by samiksha
//					vw.add(i, strweightage1);
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
						//modified by samiksha
						vw.add(i-1, rst.getString(8));
						//commneted by samiksha
//						vw.add(i, rst.getString(8));
					}
					i++;
					// indexcompose1 = new
					// IndexCompose12(stockid,stockname,currency,tis,iwf,adjusted,mcv_new,mcv,stockprice,market,last,curr_exch_convIratecomp1,strweightage1);
					indexcompose1 = new IndexCompose12(stockid, stockname, tis,
							iwf, market, adjusted, last, currency,
							curr_exch_convIratecomp, mcv, adjustedmarketcap,
							strweightage, stockprice);
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
		// Connect con=new app.Connect();
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

	public String getUserid1() {
		return userid1;
	}

	public void setUserid1(String userid1) {
		Logging.debug("user id after login in page" + userid1);
		this.userid1 = userid1;
	}

	public String getRole_id1() {
		return role_id1;
	}

	public void setRole_id1(String role_id1) {
		this.role_id1 = role_id1;
	}
}
