/*
 * Created on Oct 7, 2004 
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import harrier.income.com.report.IndexComposeReportMethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.StringTokenizer;
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
 * @author kena,pranoti TODO To change the template for this generated type
 *         comment go to Window - Preferences - Java - Code Style - Code
 *         Templates
 */
public class Corporate extends ActionForm {
	Logger Logging = Logger.getLogger(Corporate.class);
	String role_idc = null;
	String success_flag = null;
	String stk_flag = null;
	String[] affIndexNoClosing = new String[100];
	PreparedStatement pst;
	ResultSet rs, rs1;
	IndexComposeReportMethod Icr = new IndexComposeReportMethod();
	String userid1;

	/**
	 * @return Returns the bc_end.
	 */
	public String getBc_end() {
		return bc_end;
	}

	/**
	 * @param bc_end
	 *            The bc_end to set.
	 */
	public void setBc_end(String bc_end) {
		this.bc_end = bc_end;
	}

	/**
	 * @return Returns the bc_start.
	 */
	public String getBc_start() {
		return bc_start;
	}

	/**
	 * @param bc_start
	 *            The bc_start to set.
	 */
	public void setBc_start(String bc_start) {
		this.bc_start = bc_start;
	}

	/**
	 * @return Returns the nc_end.
	 */
	public String getNc_end() {
		return nc_end;
	}

	/**
	 * @param nc_end
	 *            The nc_end to set.
	 */
	public void setNc_end(String nc_end) {
		this.nc_end = nc_end;
	}

	/**
	 * @return Returns the nc_start.
	 */
	public String getNc_start() {
		return nc_start;
	}

	/**
	 * @param nc_start
	 *            The nc_start to set.
	 */
	public void setNc_start(String nc_start) {
		this.nc_start = nc_start;
	}

	private String exc, stid, ratio, ratio1, ratio2, corpid, percent = "", amt,
			newLTP, newTIS, adjust, affect, share, cad_id, leng, i_index,
			values, fdate = null, tdate, bc_start, amt_per, bc_end, nc_start,
			nc_end, ltp, description, symbol, series, newTmcv, event_id,
			button, corp_name, type, chk_but, r_type = null, c_Cad, c_Cad1,
			upflg, ncorp_button, compo_button, ind_comp, check_type, exc_stk,
			n_share, s_exc, time = null, s_stock = null, s_ratio1, s_ratio2,
			exist, new_corp_but = null, s_affectedIndex, id, ind_div,
			ftcurrency, currencyid, curr_val, mark_lot = null,
			commit_butt = null, succ_butt = null;
	String StopRepetition = "a";

	StringBuffer str = new StringBuffer();

	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}

	String[] stock = null;

	/**
	 * @return Returns the stock.
	 */
	public String[] getStock() {
		return stock;
	}

	/**
	 * @param stock
	 *            The stock to set.
	 */
	public void setStock(String[] stock) {
		this.stock = stock;
	}

	/**
	 * @return Returns the str.
	 */
	public StringBuffer getStr() {
		return str;
	}

	/**
	 * @param str
	 *            The str to set .
	 */
	public void setStr(StringBuffer str) {
		this.str = str;
	}

	private Collection excCollection = null;
	private Collection exc1Collection = null;
	private Collection ftCollection = null;

	/**
	 * @return Returns the exist.
	 */
	public String getExist() {
		return exist;
	}

	/**
	 * @param exist
	 *            The exist to set.
	 */
	public void setExist(String exist) {
		this.exist = exist;
	}

	private Vector v = new Vector();
	private Collection stkCollection = null;
	private Collection s_stkCollection = null;

	private Collection indCollection = null;
	private Collection corpCollection = null;
	private Collection indcorpCollection = null;
	private Collection stkmulCollection = null;
	private Collection affindCollection = null;
	private Collection affstkCollection = null;

	private ArrayList stocklist = new ArrayList();
	private ArrayList indlist = new ArrayList();
	private ArrayList alllist = new ArrayList();
	private ArrayList undolist = new ArrayList();
	private ArrayList historiclist = new ArrayList();
	stocklist sl;

	/**
	 * @return Returns the indlist.
	 */
	public ArrayList getIndlist() {
		ArrayList Pp = new ArrayList();
		Connection con = null;
		Connect c = ConnectInit.getConnect();
		try {
			if (con == null) {
				con = c.getdbConnection();
			}
			try {
				Hashtable hash = getHash();
				if (hash.isEmpty()) {
					int val = stock.length;
					if (val != 0) {
						for (int i = 0; i < val; i++) {
							String stk = stock[i];
							hash.put(new Integer(stk).toString(), new String(
									corpid));
						}
					}
					setHash(hash);
					hash = getHash();
				}
				boolean flag = true;
				for (Enumeration enum1 = hash.keys(); enum1.hasMoreElements();) {
					String id2 = (String) enum1.nextElement();
					String corp_query = ConnectInit.queries
							.getProperty("get_corporate_list_index");
					ResultSet rs2 = ListTypeClass1.resultCorporate(con,
							corp_query);
					int cid = ListTypeClass1.check_corp_name1(rs2, corpid);
					rs2.close();
					String query = ConnectInit.queries
							.getProperty("select_index_corpdiary");
					ResultSet rs = ListTypeClass1.check_dairy_exist(con, query,
							Integer.toString(cid), i_index, id2, apply_date);
					if (rs != null)
						flag = rs.next();
					if (flag == true) {
						rs.beforeFirst();
						while (rs.next()) {
							String tid = "true:" + rs.getString("cad_id");
							sl = new stocklist(tid, rs.getString("index_name"),
									rs.getString("stock_name"),
									rs.getString("cam_name"),
									rs.getString("apply_on_date"),
									rs.getString("values"),
									rs.getString("status"));
							Pp.add(sl);
						}// while
					}// flag true
					else {
						query = ConnectInit.queries
								.getProperty("select_stock_corp_index");
						ResultSet rs1 = ListTypeClass1.getresp_cad1(con, query,
								Integer.toString(cid), id2, i_index);
						rs1.next();
						String tid = "false:" + id2;
						sl = new stocklist(tid, rs1.getString("index_name"),
								rs1.getString("stock_name"),
								rs1.getString("cam_name"), "", "", "n");
						Pp.add(sl);
					}// flag false
				}// for end
			} catch (Exception e) {
				Logging.error("Error= " + e.getMessage());
			}
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		indlist = Pp;
		return indlist;
	}

	/**
	 * @param indlist
	 *            The indlist to set.
	 */
	public void setIndlist(ArrayList indlist) {
		this.indlist = indlist;
	}

	/**
	 * @return Returns the alllist.
	 */
	public ArrayList getAlllist() {
		ArrayList Pp = new ArrayList();
		Connection con = null;
		Connect c = ConnectInit.getConnect();
		try {

			if (con == null) {
				con = c.getdbConnection();
			}
			try {
				if (r_type.equals("stock event")) {
					ResultSet rs = app.ListTypeClass1.check_type_diary(this,
							fdate, tdate, r_type);
					while (rs != null && rs.next()) {
						String cid = rs.getString("cad_id");
						String query1 = null;
						query1 = ConnectInit.queries
								.getProperty("cad_id_details");
						ResultSet rs1 = ListTypeClass1.getResult1(con, query1,
								cid);
						rs1.next();
						sl = new stocklist(rs.getString("cad_id"),
								rs1.getString("cam_name"),
								rs1.getString("stock_ex_name"),
								rs1.getString("stock_name"),
								rs1.getString("apply_on_date"),
								rs1.getString("applied_date"),
								rs1.getString("amount"),
								rs1.getString("percentage"),
								rs1.getString("ratio_for_shares"),
								rs1.getString("ratio_shares_offered"),
								rs1.getString("values"),
								rs1.getString("status"));
						Pp.add(sl);
					}
				}
				if (r_type.equals("index event")) {
					hash.clear();
					hash2.clear();
					ResultSet rs = app.ListTypeClass1.check_type_diary(this,
							fdate, tdate, r_type);
					while (rs != null && rs.next()) {
						String cid = rs.getString("cad_id");
						String query1 = null;
						query1 = ConnectInit.queries
								.getProperty("index_cad_details");
						ResultSet rs1 = ListTypeClass1.getResult1(con, query1,
								cid);
						rs1.next();
						String srtname = rs1.getString("cam_shortname")
								.toLowerCase().trim();
						sl = new stocklist(rs.getString("cad_id"),
								rs1.getString("cam_name"),
								rs1.getString("index_name"),
								rs1.getString("exchange_name"),
								rs1.getString("stock_name"),
								rs1.getString("apply_on_date"),
								rs1.getString("applied_date"),
								rs1.getString("base_date"),
								rs1.getString("values"),
								rs1.getString("status"));
						Pp.add(sl);
						hash.put(new String(cid), new String(srtname));
						if (rs1.getString("status").equals("y")
								& srtname.equals("change iwf"))
							hash2.put(new String(cid),
									new String(rs1.getString("values")));

					}
					setHash(hash);
					setHash2(hash2);

				}
				if (r_type.equals("event")) {
					ResultSet rs = app.ListTypeClass1.check_type_diary(this,
							fdate, tdate, r_type);
					while (rs != null && rs.next()) {
						String event = rs.getString("event_id");
						String stock_id = rs.getString("stock_id");
						String query1 = ConnectInit.queries
								.getProperty("select_stock_name");
						ResultSet rs1 = ListTypeClass1.getAffected(con, query1,
								stock_id);
						rs1.next();
						String stock_name = rs1.getString("stock_name");
						String eid = rs1.getString("stock_exchange_id");
						rs1.close();
						query1 = ConnectInit.queries
								.getProperty("get_stock_exchange_name");
						rs1 = ListTypeClass1.getAffected(con, query1, eid);
						rs1.next();
						String ex_name = rs1.getString("stock_ex_name");
						rs1.close();
						String apply = rs.getString("apply_on_date");
						String amt = rs.getString("amount");
						String per = rs.getString("percentage");
						String ratio1 = rs.getString("ratio_for_shares");
						String ratio2 = rs.getString("ratio_shares_offered");
						String desc = rs.getString("description");
						String value = rs.getString("values");
						sl = new stocklist(event, ex_name, stock_name,
								rs.getString("apply_on_date"),
								rs.getString("amount"),
								rs.getString("percentage"),
								rs.getString("ratio_for_shares"),
								rs.getString("ratio_shares_offered"),
								rs.getString("description"),
								rs.getString("values"), "n");
						Pp.add(sl);
					}
				}

			} catch (Exception e) {
				Logging.error("Error=" + e.getMessage());
			}

		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		alllist = Pp;
		return alllist;
	}

	/**
	 * @param alllist
	 *            The alllist to set.
	 */
	public void setAlllist(ArrayList alllist) {
		this.alllist = alllist;
	}

	ResultSet rst;

	/**
	 * @return Returns the affindCollection.
	 */
	public Collection getAffindCollection() {
		Vector roles = new Vector();
		// AcessControl asc=new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		String AffChildInd = asc.getLangValues("Masters.AffectedChildInd");
		Logging.debug(" Inside getAffindCollection(): Affected Child Indices ="
				+ AffChildInd);
		System.out.println("AffChildInd *** " + AffChildInd);
		Connection con = null;
		Connect c = ConnectInit.getConnect();
		try {
			int chk_dt = ComputeIndexForm.CompareDate(apply_date,
					UpdateCorp.accept_date()); // check for the current date and
												// user's
												// entered date

			try {
				if (chk_dt == 0)
					if (con == null)
						con = c.getdbConnection();
					else
						con = c.getConnectionForHistTransaction();

				Logging.debug("con in affect==" + con);

				roles.add(new LabelValueBean(AffChildInd, ""));
				Hashtable affect = getHash_affind();
				boolean chk_affect = affect.isEmpty();
				// if (chk_affect == false) {
				if (!chk_affect) {
					Iterator it = affect.keySet().iterator();
					Vector v = new Vector(affect.keySet());
					Collections.sort(v);
					it = v.iterator();
					while (it.hasNext()) {
						String ele = (String) it.next();
						String div[] = ActionCorp.token2(ele);
						String element = div[1];

						String qry1 = ConnectInit.queries
								.getProperty("index_name");
						ResultSet rs = ListTypeClass1.getAffected(con, qry1,
								element);
						rs.next();
						String iname = rs.getString("index_name");

						if (affect != null)
							if ((affect.equals(""))
									| (affect.equals(AffChildInd)))
								affect = null;
						if (affect == null)
							roles.add(new LabelValueBean(iname, element));
						else {
							if (element.equals(affect)) {
								setAffect(element);
								roles.add(new LabelValueBean(iname, element));
							} else
								roles.add(new LabelValueBean(iname, element));

						}
					}// while end

				}// chk_affect
			} catch (Exception e) {
				Logging.debug("Error :" + e);
			} finally {
				try {
					if (con != null)
						con.close();
				} catch (Exception ee) {
					Logging.error(" Error : Unable to close Connection "
							+ ee.getMessage());
				}
			}
		} catch (Exception e) {
			Logging.error("Error=" + e.getMessage());
		}
		affindCollection = roles;
		return affindCollection;
	}

	/**
	 * @param affindCollection
	 *            The affindCollection to set.
	 */
	public void setAffindCollection(Collection affindCollection) {
		this.affindCollection = affindCollection;
	}

	/**
	 * @return Returns the indcorpCollection.
	 */
	public Collection getIndcorpCollection() {
		Vector roles = new Vector();

		Connection con = null;
		Connect c = ConnectInit.getConnect();
		try {
			if (con == null) {
				con = c.getdbConnection();
			}
			try {
				roles.add(new LabelValueBean("Select Action", ""));
				if (corpid != null)
					if ((corpid.equals("Select Action")) | (corpid.equals("")))
						corpid = null;
				String query = ConnectInit.queries
						.getProperty("get_corporate_list_index");
				ResultSet rs = ListTypeClass1.resultCorporate(con, query);
				if (corpid == null) {
					while (rs.next()) {
						String corp_val = null;
						String count = rs.getString("cam_shortname");
						StringTokenizer st = new StringTokenizer(count, " ");
						int val = st.countTokens();
						if (val > 1) {
							String div[] = ActionCorp.token2(count);
							corp_val = (div[0] + div[1]).toLowerCase();
							corp_val = corp_val.trim();
						} else {
							corp_val = count.toLowerCase();
							corp_val = corp_val.trim();
						}
						roles.add(new LabelValueBean(rs.getString("cam_name"),
								corp_val));
					}
				} else {
					while (rs.next()) {
						String corp_val = null;
						String count = rs.getString("cam_shortname");
						StringTokenizer st = new StringTokenizer(count, " ");
						int val = st.countTokens();
						if (val > 1) {
							String div[] = ActionCorp.token2(count);
							corp_val = (div[0] + div[1]).toLowerCase();
							corp_val = corp_val.trim();
						} else {
							corp_val = count.toLowerCase();
							corp_val = corp_val.trim();
						}
						if (corpid.equals(corp_val)) {
							setCorpid(corp_val);
							roles.add(new LabelValueBean(rs
									.getString("cam_name"), corp_val));
						} else
							roles.add(new LabelValueBean(rs
									.getString("cam_name"), corp_val));
					}
				}
			} catch (Exception e) {
				Logging.error("Error=" + e.getMessage());
			}

		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
				if (rs != null)
					rs.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		indcorpCollection = roles;
		return indcorpCollection;
	}

	/**
	 * @param indcorpCollection
	 *            The indcorpCollection to set.
	 */
	public void setIndcorpCollection(Collection indcorpCollection) {
		this.indcorpCollection = indcorpCollection;
	}

	Connection con;
	private boolean check;
	private boolean check_curr;

	/**
	 * @return Returns the check_type.
	 */
	public String getCheck_type() {
		return check_type;
	}

	/**
	 * @param check_type
	 *            The check_type to set.
	 */
	public void setCheck_type(String check_type) {
		this.check_type = check_type;
	}

	/**
	 * @return Returns the c_Cad1.
	 */
	public String getC_Cad1() {
		return c_Cad1;
	}

	/**
	 * @param cad1
	 *            The c_Cad1 to set.
	 */
	public void setC_Cad1(String cad1) {
		c_Cad1 = cad1;
	}

	private double newmcv, outstanding;

	/**
	 * @param newmcv
	 *            The newmcv to set.
	 */

	public void setNewmcv(double newmcv) {
		this.newmcv = newmcv;
	}

	/**
	 * @return Returns the fdate.
	 */
	public String getFdate() {
		return fdate;
	}

	/**
	 * @param fdate
	 *            The fdate to set.
	 */
	public void setFdate(String fdate) {
		this.fdate = fdate;
	}

	/**
	 * @return Returns the tdate.
	 */
	public String getTdate() {
		return tdate;
	}

	/**
	 * @param tdate
	 *            The tdate to set.
	 */
	public void setTdate(String tdate) {
		this.tdate = tdate;
	}

	private String sedol = "", isin = "", ric = "", cus = "", exch = "",
			tic = "", stkid = "", date = "", name = "", iwfstk = "", parent_id;

	private String com = "", tis = "", excname = "", coun = "", face = "",
			rate = "", sub = "", adr = "", nature = "", base_date, amount;

	/**
	 * @return Returns the base_date.
	 */
	public String getBase_date() {
		return base_date;
	}

	/**
	 * @param base_date
	 *            The base_date to set.
	 */
	public void setBase_date(String base_date) {
		this.base_date = base_date;
	}

	private String tmcv, divisor, indexval, newtmcv, newdivisor, tmcv1,
			divisor1, newtmcv1, newdivisor1;

	/**
	 * @return Returns the divisor1.
	 */
	public String getDivisor1() {
		return divisor1;
	}

	/**
	 * @param divisor1
	 *            The divisor1 to set.
	 */
	public void setDivisor1(String divisor1) {
		this.divisor1 = divisor1;
	}

	/**
	 * @return Returns the newdivisor1.
	 */
	public String getNewdivisor1() {
		return newdivisor1;
	}

	/**
	 * @param newdivisor1
	 *            The newdivisor1 to set.
	 */
	public void setNewdivisor1(String newdivisor1) {
		this.newdivisor1 = newdivisor1;
	}

	/**
	 * @return Returns the newtmcv1.
	 */
	public String getNewtmcv1() {
		return newtmcv1;
	}

	/**
	 * @param newtmcv1
	 *            The newtmcv1 to set.
	 */
	public void setNewtmcv1(String newtmcv1) {
		this.newtmcv1 = newtmcv1;
	}

	/**
	 * @return Returns the tmcv1.
	 */
	public String getTmcv1() {
		return tmcv1;
	}

	/**
	 * @param tmcv1
	 *            The tmcv1 to set.
	 */
	public void setTmcv1(String tmcv1) {
		this.tmcv1 = tmcv1;
	}

	private String oldltp, oldtis;
	private int val;
	private String apply_date, ex_date, record_date, announce_date,
			applied_date, status, newFace;

	/**
	 * @return Returns the newFace.
	 */
	public String getNewFace() {
		return newFace;
	}

	/**
	 * @param newFace
	 *            The newFace to set.
	 */
	public void setNewFace(String newFace) {
		this.newFace = newFace;
	}

	private String i_name = "", i_cdate = "", i_basedate = "",
			i_basevalue = "", m_start_time = "", n_stop_time = "",
			compute_interval = "", is_capture = "", cap_from = "", o_ric = "",
			cancel, close;
	private String is_child = "", parent = "", currency = "",
			is_customized = "", industry_class_code = "", industry_class = "",
			b_natureindex = "", cur;

	/**
	 * @return Returns the applied_date.
	 */
	public String getApplied_date() {
		return applied_date;
	}

	/**
	 * @param applied_date
	 *            The applied_date to set.
	 */
	public void setApplied_date(String applied_date) {
		this.applied_date = applied_date;
	}

	/**
	 * @return Returns the apply_date.
	 */
	public String getApply_date() {
		return apply_date;
	}

	/**
	 * @param apply_date
	 *            The apply_date to set.
	 */
	public void setApply_date(String apply_date) {
		this.apply_date = apply_date;
	}

	/**
	 * @return Returns the ex_date.
	 */
	public String getEx_date() {
		return ex_date;
	}

	/**
	 * @param ex_date
	 *            The ex_date to set.
	 */
	public void setEx_date(String ex_date) {
		this.ex_date = ex_date;
	}

	/**
	 * @return Returns the record_date.
	 */
	public String getRecord_date() {
		return record_date;
	}

	/**
	 * @param record_date
	 *            The record_date to set.
	 */
	public void setRecord_date(String record_date) {
		this.record_date = record_date;
	}

	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	private Hashtable hash = new Hashtable();
	private Hashtable hash12 = new Hashtable();
	private Hashtable hash1 = new Hashtable();
	private Hashtable hash2 = new Hashtable();
	private Hashtable hash3 = new Hashtable();
	private Hashtable hash4 = new Hashtable();
	private Hashtable hash5 = new Hashtable();
	private Hashtable hash6 = new Hashtable();
	private Hashtable hash_error = new Hashtable();
	private Hashtable hash_stock_error = new Hashtable();
	private Hashtable hash_affind = new Hashtable();
	private Hashtable copy_hash = new Hashtable();

	ArrayList stk_status = new ArrayList();

	private String errorMessage = "null";
	private String value, index_type_name, index_type_id;

	/**
	 * @return Returns the hash5.
	 */
	public Hashtable getHash5() {
		return hash5;
	}

	/**
	 * @param hash5
	 *            The hash5 to set.
	 */
	public void setHash5(Hashtable hash5) {
		this.hash5 = hash5;
	}

	/**
	 * @return Returns the hash6.
	 */
	public Hashtable getHash6() {
		return hash6;
	}

	/**
	 * @param hash6
	 *            The hash6 to set.
	 */
	public void setHash6(Hashtable hash6) {
		this.hash6 = hash6;
	}

	/**
	 * @return Returns the hash3.
	 */
	public Hashtable getHash3() {
		return hash3;
	}

	/**
	 * @param hash3
	 *            The hash3 to set.
	 */
	public void setHash3(Hashtable hash3) {
		this.hash3 = hash3;
	}

	/**
	 * @return Returns the hash.
	 */
	public Hashtable getHash() {
		return hash;
	}

	/**
	 * @param hash
	 *            The hash to set.
	 */
	public void setHash(Hashtable hash) {
		this.hash = hash;
	}

	/**
	 * @return Returns the oldltp.
	 */
	public String getOldltp() {
		return oldltp;
	}

	/**
	 * @param oldltp
	 *            The oldltp to set.
	 */
	public void setOldltp(String oldltp) {
		this.oldltp = oldltp;
	}

	/**
	 * @return Returns the oldtis.
	 */
	public String getOldtis() {
		return oldtis;
	}

	/**
	 * @param oldtis
	 *            The oldtis to set.
	 */
	public void setOldtis(String oldtis) {
		this.oldtis = oldtis;
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
	 * @return Returns the indexval.
	 */
	public String getIndexval() {
		return indexval;
	}

	/**
	 * @param indexval
	 *            The indexval to set.
	 */
	public void setIndexval(String indexval) {
		this.indexval = indexval;
	}

	/**
	 * @return Returns the tmcv.
	 */
	public String getTmcv() {
		return tmcv;
	}

	/**
	 * @return Returns the stk_status.
	 */
	public ArrayList getStk_status() {
		return stk_status;
	}

	/**
	 * @param stk_status
	 *            The stk_status to set.
	 */
	public void setStk_status(ArrayList stk_status) {
		this.stk_status = stk_status;
	}

	/**
	 * @param tmcv
	 *            The tmcv to set.
	 */
	public void setTmcv(String tmcv) {
		this.tmcv = tmcv;
	}

	public void setResult1(ResultSet rs) {
		try {
			if (rs != null)
				rs.next();
		} catch (Exception e) {
			Logging.error("corporate1:Error in result set " + e.getMessage());
		}
		try {
			if (rs.getString(1) != null) {
				tmcv = rs.getString("tmcv");
				setTmcv(tmcv);
			}
			if (rs.getString(2) != null) {
				divisor = rs.getString("divisor");
				setDivisor(divisor);
			}
			if (rs.getString(3) != null) {
				indexval = rs.getString("index_closing_value");
				setIndexval(indexval);
			}
		} catch (Exception e) {
			Logging.error("corporate1:Error in result set " + e.getMessage());
		}
	}

	public void setResult(ResultSet rs) {
		try {
			if (rs != null)
				rs.next();
		} catch (Exception e) {
			Logging.error("corporate1:Error in result set " + e.getMessage());
		}
		try {
			if (rs.getString(1) != null) {
				cur = rs.getString(1);
				setCur(cur);
			}
			if (rs.getString(2) != null) {
				sedol = rs.getString(2);
				setSedol(sedol);
			}
			if (rs.getString(3) != null) {
				isin = rs.getString(3);
				setIsin(isin);
			}
			if (rs.getString(4) != null) {
				ric = rs.getString(4);
				setRic(ric);
			}
			if (rs.getString(5) != null) {
				cus = rs.getString(5);
				setCus(cus);
			}
			if (rs.getString(6) != null) {
				exch = rs.getString(6);
				setExch(exch);
			}
			if (rs.getString(7) != null) {
				tic = rs.getString(7);
				setTic(tic);
			}
			if (rs.getString(8) != null) {
				stkid = rs.getString(8);
				setStkid(stkid);
			}
			if (rs.getString(9) != null) {
				date = rs.getString(9);
				setDate(date);
			}
			if (rs.getString(10) != null) {
				name = rs.getString(10);
				setName(name);
			}
			if (rs.getString(11) != null) {
				iwfstk = rs.getString(11);
				setIwfstk(iwfstk);
			}
			if (rs.getString(12) != null) {
				com = rs.getString(12);
				setCom(com);
			}
			if (rs.getString(13) != null) {
				tis = rs.getString(13);
				setTis(tis);
			}
			if (rs.getString(14) != null) {
				excname = rs.getString(14);
				setExcname(excname);
			}
			if (rs.getString(15) != null) {
				coun = rs.getString(15);
				setCoun(coun);
			}
			if (rs.getString(16) != null) {
				face = rs.getString(16);
				setFace(face);
			}
			if (rs.getString(17) != null) {
				rate = rs.getString(17);
				setRate(rate);
			}
			if (rs.getString(18) != null) {
				sub = rs.getString(18);
				setSub(sub);
			}
			if (rs.getString(19) != null) {
				adr = rs.getString(19);
				setAdr(adr);
			}
			if (rs.getString("adjusted_price") != null) {
				close = rs.getString("adjusted_price");
				setClose(close);
			} else {
				if (rs.getString("stock_closing_value") != null) {
					close = rs.getString("stock_closing_value");
					setClose(close);
				} else {
					close = "0";
					setClose(close);
				}
			}
			if (rs.getString(20) != null) {
				if (rs.getString(20) == "g") {
					nature = "g";
					setNature(nature);
				}
				if (rs.getString(20) == "v") {
					nature = "v";
					setNature(nature);
				}
			}
			if (rs.getString("market_lot") != null)
				mark_lot = rs.getString("market_lot");
			else
				mark_lot = "1";
		} catch (Exception e) {
			Logging.error("corporate1:Error in result set " + e.getMessage());
		}
	}

	public void setResult2() {
		Connect connect = ConnectInit.getConnect();
		Connection con = null;
		try {
			if (con == null) {
				con = connect.getdbConnection();
			}

			String query12 = ConnectInit.queries.getProperty("index_details");
			ResultSet rs = ListTypeClass1.getResult12(con, query12, i_index);
			boolean flag = true;
			try {
				if (rs != null)
					flag = rs.next();
			} catch (Exception e) {
				Logging.error("corporate1:Error in result set "
						+ e.getMessage());
			}
			if (flag == false)
				reset_index();
			else {
				try {
					if (rs.getString(1) != null) {
						i_name = rs.getString("index_name");
						setI_name(i_name);
					}
					if (rs.getString(2) != null) {
						i_cdate = rs.getString("creation_date");
						setI_cdate(i_cdate);
					}
					if (rs.getString(3) != null) {
						i_basedate = rs.getString("base_date");
						setI_basedate(i_basedate);
					}
					if (rs.getString(4) != null) {
						i_basevalue = rs.getString("base_value");
						setI_basevalue(i_basevalue);
					}
					if (rs.getString(5) != null) {
						compute_interval = rs.getString("computation_interval");
						setCompute_interval(compute_interval);
					}
					if (rs.getString(6) != null) {
						is_capture = rs.getString("is_captured");
						setIs_capture(is_capture);
					}
					if (rs.getString(7) != null) {
						cap_from = rs.getString("captured_from");
						setCap_from(cap_from);
					}
					if (rs.getString(8) != null) {
						m_start_time = rs.getString("m_start_time");
						setM_start_time(m_start_time);
					}
					if (rs.getString(9) != null) {
						n_stop_time = rs.getString("n_stop_time");
						setN_stop_time(n_stop_time);
					}
					if (rs.getString(10) != null) {
						o_ric = rs.getString("o_ric");
						setO_ric(o_ric);
					}
					if (rs.getString(11) != null) {
						is_child = rs.getString("is_child");
						setIs_child(is_child);
					}
					if (rs.getString(12) != null) {
						parent_id = rs.getString("parent_id");
						setParent_id(parent_id);
					} else
						setParent_id(null);
					if (rs.getString(13) != null) {
						parent = rs.getString("parent");
						setParent(parent);
					} else
						setParent(null);
					if (rs.getString(14) != null) {
						currency = rs.getString("currency_name");
						setCurrencyid(rs.getString("currency_id"));
						setCurrency(currency);
					}
					if (rs.getString(15) != null) {
						industry_class_code = rs
								.getString("industry_classification_code");
						setIndustry_class_code(industry_class_code);
					}
					if (rs.getString(16) != null) {
						is_customized = rs.getString("is_customized");
						setIs_customized(is_customized);
					}
					if (rs.getString(17) != null) {
						if (rs.getString(17).equals("g")) {
							b_natureindex = "g";
							setB_natureindex(b_natureindex);
						}
						if (rs.getString(17).equals("v")) {
							b_natureindex = "v";
							setB_natureindex(b_natureindex);
						}
					} else
						setB_natureindex("");
					if (rs.getString(18) != null) {
						isin = rs.getString("isin");
						setIsin(isin);
					}
					if (rs.getString(19) != null) {
						industry_class = rs
								.getString("industry_classification_name");
						setIndustry_class(industry_class);
					}
					if (rs.getString("index_type_name") != null) {
						name = rs.getString("index_type_name");
						setIndex_type_name(name);
					}
					if (rs.getString("index_type_id") != null) {
						String index = rs.getString("index_type_name");
						setIndex_type_id(index);
					}
				} catch (Exception e) {
					Logging.error("corporate1:Error in result set "
							+ e.getMessage());
				}

			}
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
	}

	/**
	 * @return Returns the exc.
	 */
	public String getExc() {
		return exc;
	}

	/**
	 * @param exc
	 *            The exc to set.
	 */
	public void setExc(String exc) {
		this.exc = exc;
	}

	/**
	 * @return Returns the adr.
	 */
	public String getAdr() {
		return adr;
	}

	/**
	 * @param adr
	 *            The adr to set.
	 */
	public void setAdr(String adr) {
		this.adr = adr;
	}

	/**
	 * @return Returns the com.
	 */
	public String getCom() {
		return com;
	}

	/**
	 * @param com
	 *            The com to set.
	 */
	public void setCom(String com) {
		this.com = com;
	}

	/**
	 * @return Returns the coun.
	 */
	public String getCoun() {
		return coun;
	}

	/**
	 * @param coun
	 *            The coun to set.
	 */
	public void setCoun(String coun) {
		this.coun = coun;
	}

	/**
	 * @return Returns the cus.
	 */
	public String getCus() {
		return cus;
	}

	/**
	 * @param cus
	 *            The cus to set.
	 */
	public void setCus(String cus) {
		this.cus = cus;
	}

	/**
	 * @return Returns the date.
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            The date to set.
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return Returns the exch.
	 */
	public String getExch() {
		return exch;
	}

	/**
	 * @param exch
	 *            The exch to set.
	 */
	public void setExch(String exch) {
		this.exch = exch;
	}

	/**
	 * @return Returns the excname.
	 */
	public String getExcname() {
		return excname;
	}

	/**
	 * @param excname
	 *            The excname to set.
	 */
	public void setExcname(String excname) {
		this.excname = excname;
	}

	/**
	 * @return Returns the face.
	 */
	public String getFace() {
		return face;
	}

	/**
	 * @param face
	 *            The face to set.
	 */
	public void setFace(String face) {
		this.face = face;
	}

	/**
	 * @return Returns the isin.
	 */
	public String getIsin() {
		return isin;
	}

	/**
	 * @param isin
	 *            The isin to set.
	 */
	public void setIsin(String isin) {
		this.isin = isin;
	}

	/**
	 * @return Returns the iwfstk.
	 */
	public String getIwfstk() {
		return iwfstk;
	}

	/**
	 * @param iwfstk
	 *            The iwfstk to set.
	 */
	public void setIwfstk(String iwfstk) {
		this.iwfstk = iwfstk;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the nature.
	 */
	public String getNature() {
		return nature;
	}

	/**
	 * @param nature
	 *            The nature to set.
	 */
	public void setNature(String nature) {
		this.nature = nature;
	}

	/**
	 * @return Returns the rate.
	 */
	public String getRate() {
		return rate;
	}

	/**
	 * @param rate
	 *            The rate to set.
	 */
	public void setRate(String rate) {
		this.rate = rate;
	}

	/**
	 * @return Returns the ric.
	 */
	public String getRic() {
		return ric;
	}

	/**
	 * @param ric
	 *            The ric to set.
	 */
	public void setRic(String ric) {
		this.ric = ric;
	}

	/**
	 * @return Returns the sedol.
	 */
	public String getSedol() {
		return sedol;
	}

	/**
	 * @param sedol
	 *            The sedol to set.
	 */
	public void setSedol(String sedol) {
		this.sedol = sedol;
	}

	/**
	 * @return Returns the stkid.
	 */
	public String getStkid() {
		return stkid;
	}

	/**
	 * @param stkid
	 *            The stkid to set.
	 */
	public void setStkid(String stkid) {
		this.stkid = stkid;
	}

	/**
	 * @return Returns the sub.
	 */
	public String getSub() {
		return sub;
	}

	/**
	 * @param sub
	 *            The sub to set.
	 */
	public void setSub(String sub) {
		this.sub = sub;
	}

	/**
	 * @return Returns the tic.
	 */
	public String getTic() {
		return tic;
	}

	/**
	 * @param tic
	 *            The tic to set.
	 */
	public void setTic(String tic) {
		this.tic = tic;
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
	 * @return Returns the stid.
	 */
	public String getStid() {
		return stid;
	}

	/**
	 * @param stid
	 *            The stid to set.
	 */
	public void setStid(String stid) {
		this.stid = stid;
	}

	/**
	 * @return Returns the ratio.
	 */
	public String getRatio() {
		return ratio;
	}

	/**
	 * @param ratio
	 *            The ratio to set.
	 */
	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	/**
	 * @return Returns the corpid.
	 */
	public String getCorpid() {
		return corpid;
	}

	/**
	 * @param corpid
	 *            The corpid to set.
	 */
	public void setCorpid(String corpid) {
		this.corpid = corpid;
	}

	/**
	 * @return Returns the amt.
	 */
	public String getAmt() {
		return amt;
	}

	/**
	 * @param amt
	 *            The amt to set.
	 */
	public void setAmt(String amt) {
		// if(amt!=null && !amt.equals(""))
		this.amt = amt;
	}

	/**
	 * @return Returns the percent.
	 */
	public String getPercent() {
		return percent;
	}

	/**
	 * @param percent
	 *            The percent to set.
	 */
	public void setPercent(String percent) {
		// if(percent!=null && !percent.equals(""))
		this.percent = percent;
	}

	/**
	 * @return Returns the newLTP.
	 */
	public String getNewLTP() {
		return newLTP;
	}

	/**
	 * @param newLTP
	 *            The newLTP to set.
	 */
	public void setNewLTP(String newLTP) {
		this.newLTP = newLTP;
	}

	/**
	 * @return Returns the adjust.
	 */
	public String getAdjust() {
		return adjust;
	}

	/**
	 * @param adjust
	 *            The adjust to set.
	 */
	public void setAdjust(String adjust) {
		this.adjust = adjust;
	}

	/**
	 * @return Returns the newTIS.
	 */
	public String getNewTIS() {
		return newTIS;
	}

	/**
	 * @param newTIS
	 *            The newTIS to set.
	 */
	public void setNewTIS(String newTIS) {
		this.newTIS = newTIS;
	}

	/**
	 * @return Returns the affect.
	 */
	public String getAffect() {
		return affect;
	}

	/**
	 * @param affect
	 *            The affect to set.
	 */
	public void setAffect(String affect) {
		this.affect = affect;
	}

	/**
	 * @return Returns the newdivisor.
	 */
	public String getNewdivisor() {
		return newdivisor;
	}

	/**
	 * @param newdivisor
	 *            The newdivisor to set.
	 */
	public void setNewdivisor(String newdivisor) {
		this.newdivisor = newdivisor;
	}

	/**
	 * @return Returns the newtmcv.
	 */
	public String getNewtmcv() {
		return newtmcv;
	}

	/**
	 * @param newtmcv
	 *            The newtmcv to set.
	 */
	public void setNewtmcv(String newtmcv) {
		this.newtmcv = newtmcv;
	}

	/**
	 * @return Returns the errorMessage.
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            The errorMessage to set.
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return Returns the ratio1.
	 */
	public String getRatio1() {
		return ratio1;
	}

	/**
	 * @param ratio1
	 *            The ratio1 to set.
	 */
	public void setRatio1(String ratio1) {
		this.ratio1 = ratio1;
	}

	/**
	 * @return Returns the ratio2.
	 */
	public String getRatio2() {
		return ratio2;
	}

	/**
	 * @param ratio2
	 *            The ratio2 to set.
	 */
	public void setRatio2(String ratio2) {
		this.ratio2 = ratio2;
	}

	public String getShare() {
		return share;
	}

	public void setShare(String share) {
		this.share = share;
	}

	/**
	 * @return Returns the cad_id.
	 */
	public String getCad_id() {
		return cad_id;
	}

	/**
	 * @param cad_id
	 *            The cad_id to set.
	 */
	public void setCad_id(String cad_id) {
		this.cad_id = cad_id;
	}

	/**
	 * @return Returns the val.
	 */
	public int getVal() {
		return val;
	}

	/**
	 * @param val
	 *            The val to set.
	 */
	public void setVal(int val) {
		this.val = val;
	}

	/**
	 * @return Returns the hash1.
	 */
	public Hashtable getHash1() {
		return hash1;
	}

	/**
	 * @param hash1
	 *            The hash1 to set.
	 */
	public void setHash1(Hashtable hash1) {
		this.hash1 = hash1;
	}

	/**
	 * @return Returns the hash12.
	 */
	public Hashtable getHash12() {
		return hash12;
	}

	/**
	 * @param hash12
	 *            The hash12 to set.
	 */
	public void setHash12(Hashtable hash12) {
		this.hash12 = hash12;
	}

	/**
	 * @return Returns the value.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            The value to set.
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return Returns the announce_date.
	 */
	public String getAnnounce_date() {
		return announce_date;
	}

	/**
	 * @param announce_date
	 *            The announce_date to set.
	 */
	public void setAnnounce_date(String announce_date) {
		this.announce_date = announce_date;
	}

	/**
	 * @return Returns the newmcv.
	 */

	/**
	 * @return Returns the newmcv.
	 */
	public double getNewmcv() {
		return newmcv;
	}

	/**
	 * @param newmcv
	 *            The newmcv to set.
	 */
	/**
	 * @return Returns the leng.
	 */
	public String getLeng() {
		return leng;
	}

	/**
	 * @param leng
	 *            The leng to set.
	 */
	public void setLeng(String leng) {
		this.leng = leng;
	}

	/**
	 * @return Returns the chk_date.
	 */

	/**
	 * @return Returns the i_index.
	 */
	public String getI_index() {
		return i_index;
	}

	/**
	 * @param i_index
	 *            The i_index to set.
	 */
	public void setI_index(String i_index) {
		this.i_index = i_index;
	}

	/**
	 * @return Returns the values.
	 */
	public String getValues() {
		return values;
	}

	/**
	 * @param values
	 *            The values to set.
	 */
	public void setValues(String values) {
		this.values = values;
	}

	/**
	 * @return Returns the is_customized.
	 */
	public String getIs_customized() {
		return is_customized;
	}

	/**
	 * @param is_customized
	 *            The is_customized to set.
	 */
	public void setIs_customized(String is_customized) {
		this.is_customized = is_customized;
	}

	/**
	 * @return Returns the m_start_time.
	 */
	public String getM_start_time() {
		return m_start_time;
	}

	/**
	 * @param m_start_time
	 *            The m_start_time to set.
	 */
	public void setM_start_time(String m_start_time) {
		this.m_start_time = m_start_time;
	}

	/**
	 * @return Returns the n_stop_time.
	 */
	public String getN_stop_time() {
		return n_stop_time;
	}

	/**
	 * @param n_stop_time
	 *            The n_stop_time to set.
	 */
	public void setN_stop_time(String n_stop_time) {
		this.n_stop_time = n_stop_time;
	}

	/**
	 * @return Returns the o_ric.
	 */
	public String getO_ric() {
		return o_ric;
	}

	/**
	 * @param o_ric
	 *            The o_ric to set.
	 */
	public void setO_ric(String o_ric) {
		this.o_ric = o_ric;
	}

	/**
	 * @return Returns the b_natureindex.
	 */
	public String getB_natureindex() {
		return b_natureindex;
	}

	/**
	 * @param b_natureindex
	 *            The b_natureindex to set.
	 */
	public void setB_natureindex(String b_natureindex) {
		this.b_natureindex = b_natureindex;
	}

	/**
	 * @return Returns the cap_from.
	 */
	public String getCap_from() {
		return cap_from;
	}

	/**
	 * @param cap_from
	 *            The cap_from to set.
	 */
	public void setCap_from(String cap_from) {
		this.cap_from = cap_from;
	}

	/**
	 * @return Returns the compute_interval.
	 */
	public String getCompute_interval() {
		return compute_interval;
	}

	/**
	 * @param compute_interval
	 *            The compute_interval to set.
	 */
	public void setCompute_interval(String compute_interval) {
		this.compute_interval = compute_interval;
	}

	/**
	 * @return Returns the currency.
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency
	 *            The currency to set.
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return Returns the i_basedate.
	 */
	public String getI_basedate() {
		return i_basedate;
	}

	/**
	 * @param i_basedate
	 *            The i_basedate to set.
	 */
	public void setI_basedate(String i_basedate) {
		this.i_basedate = i_basedate;
	}

	/**
	 * @return Returns the i_basevalue.
	 */
	public String getI_basevalue() {
		return i_basevalue;
	}

	/**
	 * @param i_basevalue
	 *            The i_basevalue to set.
	 */
	public void setI_basevalue(String i_basevalue) {
		this.i_basevalue = i_basevalue;
	}

	/**
	 * @return Returns the i_cdate.
	 */
	public String getI_cdate() {
		return i_cdate;
	}

	/**
	 * @param i_cdate
	 *            The i_cdate to set.
	 */
	public void setI_cdate(String i_cdate) {
		this.i_cdate = i_cdate;
	}

	/**
	 * @return Returns the i_name.
	 */
	public String getI_name() {
		return i_name;
	}

	/**
	 * @param i_name
	 *            The i_name to set.
	 */
	public void setI_name(String i_name) {
		this.i_name = i_name;
	}

	/**
	 * @return Returns the industry_class.
	 */
	public String getIndustry_class() {
		return industry_class;
	}

	/**
	 * @param industry_class
	 *            The industry_class to set.
	 */
	public void setIndustry_class(String industry_class) {
		this.industry_class = industry_class;
	}

	/**
	 * @return Returns the industry_class_code.
	 */
	public String getIndustry_class_code() {
		return industry_class_code;
	}

	/**
	 * @param industry_class_code
	 *            The industry_class_code to set.
	 */
	public void setIndustry_class_code(String industry_class_code) {
		this.industry_class_code = industry_class_code;
	}

	/**
	 * @return Returns the is_capture.
	 */
	public String getIs_capture() {
		return is_capture;
	}

	/**
	 * @param is_capture
	 *            The is_capture to set.
	 */
	public void setIs_capture(String is_capture) {
		this.is_capture = is_capture;
	}

	/**
	 * @return Returns the is_child.
	 */
	public String getIs_child() {
		return is_child;
	}

	/**
	 * @param is_child
	 *            The is_child to set.
	 */
	public void setIs_child(String is_child) {
		this.is_child = is_child;
	}

	/**
	 * @return Returns the parent.
	 */
	public String getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            The parent to set.
	 */
	public void setParent(String parent) {
		this.parent = parent;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		r_type = "stock event";
		exc_stk = "a";
		c_Cad = null;
		tmcv = null;
		tmcv1 = null;
		divisor = null;
		divisor1 = null;
		affect = null;
		newmcv = 0.0;
		newdivisor = null;
		newdivisor1 = null;
		newtmcv = null;
		newTmcv = null;
		newtmcv1 = null;
		ratio1 = null;
		ratio2 = null;
		amt = null;
		share = null;
		ind_comp = null;

	}

	public void reset() {
		this.newdivisor = null;
		this.adjust = null;
		this.adr = null;
		this.affect = null;
		this.divisor = null;
		this.newLTP = null;
		this.newTIS = null;
		this.newtmcv = null;
		this.tmcv = null;

	}

	public void reset_stock() {
		divisor = null;
		newdivisor = null;
		tmcv = null;
		newtmcv = null;
		newmcv = 0;
		newTIS = null;
		newLTP = null;
		adjust = null;
		indexval = null;
		affect = null;
	}

	public void reset1() {
		this.cus = "";
		this.sedol = "";
		this.isin = "";
		this.ric = "";
		this.exch = "";
		this.ltp = "";
		this.tic = "";
		this.stkid = "";
		this.iwfstk = "";
		this.date = "";
		this.com = "";
		this.tis = "";
		this.excname = "";
		this.coun = "";
		this.face = "";
		this.rate = "";
		this.sub = "";
		this.adr = "";
		this.nature = "";
		this.name = "";
		this.affect = "";
		this.cur = "";
		this.close = "";
	}

	public void reset2() {
		this.divisor = "";
		this.newdivisor = "";
		this.tmcv = "";
		this.newtmcv = "";
		this.newTmcv = "";
		this.tmcv1 = "";
		this.newtmcv1 = "";
		this.divisor1 = "";
		this.newdivisor1 = "";
		this.affect = null;
	}

	public void reset_affect1() {
		this.tmcv = null;
		this.newtmcv = null;
		this.divisor = null;
		this.newdivisor = null;
		this.indexval = null;
	}

	public void reset_affect() {
		this.tmcv1 = "";
		this.newtmcv1 = "";
		this.divisor1 = "";
		this.newdivisor1 = "";
	}

	public void reset3() {
		this.stid = "";
		this.announce_date = "";
		this.ex_date = "";
		this.record_date = "";
		this.apply_date = UpdateCorp.accept_date();
		this.applied_date = "";
		this.corpid = "";
		this.values = "";
		this.value = "";
		this.corp_name = "";
	}

	public void reset_index() {
		this.i_name = "";
		this.i_cdate = "";
		this.i_basedate = "";
		this.i_basevalue = "";
		this.m_start_time = "";
		this.n_stop_time = "";
		this.compute_interval = "";
		this.i_index = "";
		this.is_capture = "";
		this.cap_from = "";
		this.is_child = "";
		this.o_ric = "";
		this.isin = "";
		this.parent_id = "";
		this.parent = "";
		this.currency = "";
		this.is_customized = "";
		this.industry_class_code = "";
		this.industry_class = "";
		this.b_natureindex = "";
		this.index_type_id = "";
		this.index_type_name = "";
		this.currencyid = null;
		this.ftcurrency = null;
		this.curr_val = "";
		this.ind_div = null;
	}

	public void reset_hash() {
		this.hash_affind.clear();
		this.hash1.clear();
		this.hash2.clear();
		this.hash_error.clear();
		this.hash_stock_error.clear();
		this.nature = null;
		this.i_index = null;
		this.affect = "";
		this.errorMessage = null;

	}

	public void reset_event() {
		this.amt = null;
		this.percent = null;
		this.ratio1 = null;
		this.ratio2 = null;
		this.share = null;
		this.bc_end = null;
		this.bc_start = null;
		this.nc_start = null;
		this.nc_end = null;
		this.description = null;
		this.amount = null;
		this.s_ratio1 = null;
		this.s_ratio2 = null;
		apply_date = UpdateCorp.accept_date();
	}

	public void reset_indevent() {
		Connect connect = ConnectInit.getConnect();
		Connection con = null;
		try {
			if (con == null) {
				con = connect.getdbConnection();
			}
			try {
				String qry = ConnectInit.queries
						.getProperty("select_system_config");
				ResultSet rs1 = ListTypeClass1.resultCorporate(con, qry);
				rs1.next();
				setExc(rs1.getString("stock_ex_id"));
				rs1.close();
			} catch (Exception e) {
				Logging.error("error=" + e.getMessage());
			}
			stock = null;
			this.ind_comp = null;
			this.check_curr = false;
			leng = null;
			reset3();
			reset2();
			reset_hash();
			setButton(null);
			hash_error.clear();
			setHash_error(hash_error);
			chk_but = null;
			reset_index();
			apply_date = UpdateCorp.accept_date();
			// setApply_date(null);
			setCommit_butt(null);

		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
	}

	public void reset_stkevent() {
		Connect connect = ConnectInit.getConnect();
		Connection con = null;
		try {
			if (con == null) {
				con = connect.getdbConnection();
			}

			newFace = null;
			exc_stk = null;
			s_stock = null;
			ind_comp = null;
			leng = null;
			chk_but = null;
			reset1();
			button = null;
			apply_date = UpdateCorp.accept_date();
			reset_stock();
			reset();
			reset_event();
			commit_butt = null;
			try {
				String qry = ConnectInit.queries
						.getProperty("select_system_config");
				ResultSet rs1 = ListTypeClass1.resultCorporate(con, qry);
				rs1.next();
				setExc(rs1.getString("stock_ex_id"));
			} catch (Exception e) {
				Logging.error("Error=" + e.getMessage());
			}
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
	}

	public void selectdate() {
		Connect connect = ConnectInit.getConnect();
		Connection con = null;
		try {

			if (con == null) {
				con = connect.getdbConnection();
			}
			try {
				String query = ConnectInit.queries
						.getProperty("select_system_config");
				ResultSet rs = ListTypeClass1.resultCorporate(con, query);
				rs.next();
				int dt_diff = rs.getInt("date_difference");
				Date d = new Date();
				long l1 = ((d.getTime()) - (86400000 * dt_diff));
				java.sql.Date ds = new java.sql.Date(l1);
				String mnt = Integer.toString(ds.getMonth() + 1);
				int len = mnt.length();
				if (len == 1)
					mnt = "0" + mnt;
				String yer = Integer.toString(ds.getYear() + 1900);
				String tdate = Integer.toString(ds.getDate()) + "-" + mnt + "-"
						+ yer;
				setFdate(tdate);

				long l2 = ((d.getTime()) + (86400000 * dt_diff));
				java.sql.Date ds1 = new java.sql.Date(l2);
				String mnt1 = Integer.toString(ds1.getMonth() + 1);
				int len1 = mnt1.length();
				if (len1 == 1)
					mnt1 = "0" + mnt1;
				String yer1 = Integer.toString(ds1.getYear() + 1900);
				String tdate1 = Integer.toString(ds1.getDate()) + "-" + mnt1
						+ "-" + yer1;
				setTdate(tdate1);

				applied_date = null;
				time = null;
				c_Cad = null;
				button = null;
			} catch (Exception e) {
				Logging.error("Error=" + e.getMessage());
			}
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
	}

	public int check_stkstatus(Connection con, Connect connect, String stock,
			String date) {
		int merge_chk = -1;
		try {
			String stk_qry = ConnectInit.queries
					.getProperty("check_stock_status");
			ResultSet rs = ListTypeClass1.getAffected(con, stk_qry, stock);
			if (rs.next()) {
				String mdate = rs.getString("applied_date");
				merge_chk = ComputeIndexForm.CompareDate(date, mdate);
			}
		} catch (Exception e) {
			Logging.debug("Error=" + e.getMessage());
		}
		Logging.debug("out of check");
		return merge_chk;
	}

	/**
	 * @return Returns the ltp.
	 */
	public String getLtp() {
		return ltp;
	}

	/**
	 * @param ltp
	 *            The ltp to set.
	 */
	public void setLtp(String ltp) {
		this.ltp = ltp;
	}

	/**
	 * @return Returns the cancel.
	 */
	public String getCancel() {
		return cancel;
	}

	/**
	 * @param cancel
	 *            The cancel to set.
	 */
	public void setCancel(String cancel) {
		this.cancel = cancel;
	}

	/**
	 * @return Returns the close.
	 */
	public String getClose() {
		return close;
	}

	/**
	 * @param close
	 *            The close to set.
	 */
	public void setClose(String close) {
		this.close = close;
	}

	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return Returns the series.
	 */
	public String getSeries() {
		return series;
	}

	/**
	 * @param series
	 *            The series to set.
	 */
	public void setSeries(String series) {
		this.series = series;
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
	 * @return Returns the hash2.
	 */
	public Hashtable getHash2() {
		return hash2;
	}

	/**
	 * @param hash2
	 *            The hash2 to set.
	 */
	public void setHash2(Hashtable hash2) {
		this.hash2 = hash2;
	}

	/**
	 * @return Returns the cur.
	 */
	public String getCur() {
		return cur;
	}

	/**
	 * @param cur
	 *            The cur to set.
	 */
	public void setCur(String cur) {
		this.cur = cur;
	}

	/**
	 * @return Returns the hash4.
	 */
	public Hashtable getHash4() {
		return hash4;
	}

	/**
	 * @param hash4
	 *            The hash4 to set.
	 */
	public void setHash4(Hashtable hash4) {
		this.hash4 = hash4;
	}

	/**
	 * @return Returns the newTmcv.
	 */
	public String getNewTmcv() {
		return newTmcv;
	}

	/**
	 * @param newTmcv
	 *            The newTmcv to set.
	 */
	public void setNewTmcv(String newTmcv) {
		this.newTmcv = newTmcv;
	}

	/**
	 * @return Returns the outstanding.
	 */
	public double getOutstanding() {
		return outstanding;
	}

	/**
	 * @param outstanding
	 *            The outstanding to set.
	 */
	public void setOutstanding(double outstanding) {
		this.outstanding = outstanding;
	}

	/**
	 * @return Returns the hash_error.
	 */
	public Hashtable getHash_error() {
		return hash_error;
	}

	/**
	 * @param hash_error
	 *            The hash_error to set.
	 */
	public void setHash_error(Hashtable hash_error) {
		this.hash_error = hash_error;
	}

	/**
	 * @return Returns the event_id.
	 */
	public String getEvent_id() {
		return event_id;
	}

	/**
	 * @param event_id
	 *            The event_id to set.
	 */
	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}

	/**
	 * @return Returns the button.
	 */
	public String getButton() {
		return button;
	}

	/**
	 * @param button
	 *            The button to set.
	 */
	public void setButton(String button) {
		this.button = button;
	}

	/**
	 * @return Returns the parent_id.
	 */
	public String getParent_id() {
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
	 * @return Returns the hash_affind.
	 */
	public Hashtable getHash_affind() {
		return hash_affind;
	}

	/**
	 * @param hash_affind
	 *            The hash_affind to set.
	 */
	public void setHash_affind(Hashtable hash_affind) {
		this.hash_affind = hash_affind;
	}

	/**
	 * @return Returns the check.
	 */
	/**
	 * @return Returns the check.
	 */
	public boolean isCheck() {
		return check;
	}

	/**
	 * @param check
	 *            The check to set.
	 */
	public void setCheck(boolean check) {
		this.check = check;
	}

	/**
	 * @return Returns the index_type_id.
	 */
	public String getIndex_type_id() {
		return index_type_id;
	}

	/**
	 * @param index_type_id
	 *            The index_type_id to set.
	 */
	public void setIndex_type_id(String index_type_id) {
		this.index_type_id = index_type_id;
	}

	/**
	 * @return Returns the index_type_name.
	 */
	public String getIndex_type_name() {
		return index_type_name;
	}

	/**
	 * @param index_type_name
	 *            The index_type_name to set.
	 */
	public void setIndex_type_name(String index_type_name) {
		this.index_type_name = index_type_name;
	}

	/**
	 * @return Returns the corp_name.
	 */
	public String getCorp_name() {
		return corp_name;
	}

	/**
	 * @param corp_name
	 *            The corp_name to set.
	 */
	public void setCorp_name(String corp_name) {
		this.corp_name = corp_name;
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		Connect connect = ConnectInit.getConnect();
		Connection con = null;
		try {
			if (con == null) {
				con = connect.getdbConnection();
			}

			// code added for subscription version no. of stock validation while
			// add stock index event : By Neha

			String buttonnew = request.getParameter("ap_co_button");
			ActionErrors actionErrors = new ActionErrors();
			// Connection connection = null;
			PreparedStatement stmt4 = null, stmt1 = null, stmt2 = null, stmt3 = null;
			ResultSet rst4 = null, rst1 = null, rst2 = null, rst3 = null;
			// Connect c = ConnectInit.getConnect();
			String uid = getUserid1();
			String idxid = getI_index();
			int count_stk = 0, count_index = 0;
			int roleid = 0;
			int order_id = 0;
			int sub_id = 0;
			int no_of_stocks = 0;
			try {

				if (buttonnew != null) {
					if (buttonnew.equals("Commit")) {
						if (uid != null) {

							try {
								if (con == null)
									con = connect.getdbConnection();
								stmt4 = con
										.prepareStatement(ConnectInit.queries
												.getProperty("select_stock_id_from_index_composition"));
								stmt4.setString(1, idxid);
								rst4 = stmt4.executeQuery();

								while (rst4.next()) {

									count_stk++;

								}
								if (con == null)
									con = connect.getdbConnection();
								stmt1 = con
										.prepareStatement(ConnectInit.queries
												.getProperty("select_role_id_of_user"));
								stmt1.setString(1, uid);
								rst1 = stmt1.executeQuery();

								if (rst1.next()) {

									roleid = rst1.getInt(1);

								}
								// subscribe user 0r register user
								if (roleid == 76 || roleid == 75) {
									if (con == null)
										con = connect.getdbConnection();
									stmt2 = con
											.prepareStatement(ConnectInit.queries
													.getProperty("select_order_id_subscription_id_of_user"));
									stmt2.setInt(1, Integer.parseInt(uid));
									rst2 = stmt2.executeQuery();
									boolean flag = false;
									while (rst2.next()) {
										order_id = rst2.getInt(1);
										sub_id = rst2.getInt(2);
										if (con == null)
											con = connect.getdbConnection();
										PreparedStatement psmt2 = con
												.prepareStatement(ConnectInit.queries
														.getProperty("insert_order_id_into_index_master"));
										psmt2.setInt(1,
												Integer.parseInt(userid1));
										psmt2.setInt(2, order_id);
										ResultSet rstnew2 = psmt2
												.executeQuery();
										while (rstnew2.next()) {
											if (con == null)
												con = connect.getdbConnection();
											stmt3 = con
													.prepareStatement(ConnectInit.queries
															.getProperty("select_no_of_stocks_userwise"));
											stmt3.setInt(1, sub_id);
											rst3 = stmt3.executeQuery();
											if (rst3.next()) {
												no_of_stocks = rst3.getInt(1);
											}
											if (count_stk >= no_of_stocks) {
												actionErrors
														.add(null,
																new ActionError(
																		"Error.message.addstocks"));
												return actionErrors;
											}
										}
									}

								}
								rst4.close();
								stmt4.close();
								rst1.close();
								stmt1.close();
								rst2.close();
								stmt2.close();
								rst3.close();
								stmt3.close();
							} catch (Exception e) {
								Logging.error(" Error : " + e.getMessage());
							} finally {
								try {
									if (con != null)
										con.close();
								} catch (Exception ee) {
									Logging.error(" Error : Unable to close connection "
											+ ee.getMessage());
								}
							}

						}
					}
				}

			} catch (Exception e) {
				Logging.error(" Error : Unable to close Connection "
						+ e.getMessage());
				Logging.debug("Error in Validation ");
			}

			// code ended

			try {

				String button = request.getParameter("ap_co_button");
				if (button != null) {
					if (button.equals("Index")) {
						ind_comp = null;
						curr_val = null;
						ftcurrency = null;
						ind_div = null;
						errors.clear();
					}
					if (button.equals("Currency"))
						errors.clear();
					if (button.equals("Action")) {
						ind_comp = null;
						errors.clear();
						if ((i_index.equals(""))
								| (i_index.equals("Select Index")))
							errors.add("i_index", new ActionError(
									"Error.message.Index"));
					}
					if (button.equals("Commit")) {
						String butt = getButton();
						if (butt == null) {
							if (corpid.equals("changeindcurr")) {
								if (con.isClosed())
									con = connect.getdbConnection();
								String qry = ConnectInit.queries
										.getProperty("get_corporate_list_index");
								int cid = 0;
								ResultSet rs = ListTypeClass1.resultCorporate(
										con, qry);
								cid = ListTypeClass1.check_corp_name1(rs,
										request.getParameter("corpid"));
								rs.close();

								String query = ConnectInit.queries
										.getProperty("check_diarycurr_exist");
								String date = UpdateCorp.accept_date();
								rs = ListTypeClass1.getresp_cad1(con, query,
										getI_index(), Integer.toString(cid),
										date);
								if (rs.next()) {
									errors.add("Values", new ActionError(
											"Error.message.ActionApplied"));
								}
							} else {
								if (con.isClosed())
									con = connect.getdbConnection();
								String qry = ConnectInit.queries
										.getProperty("get_corporate_list_index");
								int cid = 0;
								ResultSet rs = ListTypeClass1.resultCorporate(
										con, qry);
								cid = ListTypeClass1.check_corp_name1(rs,
										request.getParameter("corpid"));
								rs.close();

								String query = ConnectInit.queries
										.getProperty("check_index_action");
								String date = UpdateCorp.accept_date();
								String stk_val[] = request
										.getParameterValues("stock");
								// if(stk_val==null){
								// errors.add("Values",new
								// ActionError("Error.message.ActionApplied"));
								// }else{
								int l1 = stk_val.length;
								for (int i = 0; i < l1; i++) {
									String sid = stk_val[i];
									rs = ListTypeClass1.check_dairy_exist(con,
											query, Integer.toString(cid),
											i_index, sid, date);

									boolean chk_rs = rs.next();
									if (chk_rs) {

										errors.add("Values", new ActionError(
												"Error.message.ActionApplied"));
										break;
									}
								}
								// }
							}
						} else {
							Hashtable hash = getHash2();
							for (Enumeration enumm = hash.keys(); enumm
									.hasMoreElements();) {
								String id = (String) enumm.nextElement();
								String div[] = ActionCorp.token(id);
								setC_Cad(div[1]);
								try {
									String query = ConnectInit.queries
											.getProperty("select_rep_cad");
									ResultSet rs = ListTypeClass1.getAffected(
											con, query, getC_Cad());
									rs.next();
									String status = rs.getString("status");
									if (status.equals("n"))
										errors.add("Values", new ActionError(
												"Error.message.ActionApplied"));
								} catch (Exception e) {
									Logging.error("Error=" + e.getMessage());
								}
							}
						}
					}
					if (button.equals("Apply")) {
						int flg = 0;
						String butt = getButton();
						ind_comp = null;
						int chk_dt = 0;
						Hashtable hash = getHash_stock_error();
						hash.clear();
						setHash_stock_error(hash);
						hash = getHash_error();
						hash.clear();
						setHash_error(hash);
						if (butt == null) {
							if ((i_index.equals(""))
									| (i_index.equals("Select Index")))
								errors.add("i_index", new ActionError(
										"Error.message.Index"));

							if ((apply_date == null) | (apply_date.equals("")))
								errors.add("i_index", new ActionError(
										"Error.message.ApplyDate"));
							else {
								String dt = UpdateCorp.accept_date(); // get the
																		// current
																		// date
								String apply = apply_date;
								chk_dt = ComputeIndexForm.CompareDate(
										apply_date, dt);// check for the current
														// date and user's
														// entered date
								if (chk_dt > 0) {
									flg = 1;
									errors.add("chk", new ActionError(
											"Error.message.GreaterDate"));
								}
							}
							if (flg == 0) {
								if ((corpid.equals("Select Action"))
										| (corpid.equals(""))) {
									flg = 1;
									errors.add("i_index", new ActionError(
											"Error.message.CorpAction"));
								} else {
									String corp_val = request
											.getParameter("corpid");
									int type_id = 0;
									try {
										String query = ConnectInit.queries
												.getProperty("select_index_type");
										ResultSet rs = ListTypeClass1
												.getResult1(con, query, i_index);
										if (rs != null && rs.next())
											type_id = rs
													.getInt("index_type_id");
									} catch (Exception e) {
										System.out.println("Exce[ption --- "
												+ e.getMessage());
										Logging.error("error=" + e.getMessage());
									}
									if ((type_id == 1 & corp_val
											.equals("changeiwf"))
											| (type_id == 3 & corp_val
													.equals("changeiwf"))
											| (type_id == 4 & ((corp_val
													.equals("changeiwf"))
													| (corp_val
															.equals("addstock")) | (corp_val
														.equals("deletestock"))))
											| (type_id == 5 & ((corp_val
													.equals("addstock"))
													| (corp_val
															.equals("deletestock"))
													| (corp_val
															.equals("changeiwf")) | (corp_val
														.equals("rebasing"))))) {
										flg = 1;
										errors.add("chk_but", new ActionError(
												"Error.message.TypeCheck"));
									}
								}
							}
							if (flg == 0) {
								if ((corpid.equals("addstock"))
										| (corpid.equals("deletestock"))
										| (corpid.equals("changeiwf"))) {
									String stock = request
											.getParameter("stock");
									if (stock == null) {
										flg = 1;
										errors.add("stock", new ActionError(
												"Error.message.Stock"));
									}
									if ((stock.equals("Select Stock"))
											| (stock.equals(""))) {
										flg = 1;
										errors.add("stock", new ActionError(
												"Error.message.Stock"));
									}
								}
								if (corpid.equals("changeiwf")) {
									String values = request
											.getParameter("values");
									if (values.equals("")) {
										flg = 1;
										errors.add("Values", new ActionError(
												"Error.message.Value"));
									} else {
										float val = Float.parseFloat(values);
										if (val <= 0.0 | val > 1.0) {
											flg = 1;
											errors.add(
													"Values",
													new ActionError(
															"Error.message.Value"));
										}
									}
								}
								if (flg == 0) {
									if (chk_dt != 0) {
										if (!(corpid.equals("changeindcurr"))) {
											int len = stock.length;
											if (len > 1) {
												errors.add(
														"Values",
														new ActionError(
																"Error.message.Histmultiple"));
											}
										}
									}
								}
								if (flg == 0) {
									if (chk_dt != 0) {
										if (corpid.equals("addstock")) {
											String qry = ConnectInit.queries
													.getProperty("check_hist_add_del");
											String cam_name = "delete stock";
											String stk = stock[0];
											ResultSet rs = ListTypeClass1
													.check_dairy_exist(con,
															qry, i_index, stk,
															apply_date,
															cam_name);
											boolean chk_rs = rs.next();
											if (chk_rs == true) {
												flg = 1;
												errors.add(
														"chk",
														new ActionError(
																"Error.message.Histadd"));
											}
										}
										if (corpid.equals("deletestock")) {
											String qry = ConnectInit.queries
													.getProperty("check_hist_add_del");
											String cam_name = "add stock";
											String stk = stock[0];
											ResultSet rs = ListTypeClass1
													.check_dairy_exist(con,
															qry, i_index, stk,
															apply_date,
															cam_name);
											boolean chk_rs = rs.next();
											if (chk_rs == true) {
												flg = 1;
												errors.add(
														"chk",
														new ActionError(
																"Error.message.Histdel"));
											}
										}
										if (corpid.equals("changeiwf")) {
											String qry = ConnectInit.queries
													.getProperty("check_hist_iwf");
											String stk = stock[0];
											ResultSet rs = ListTypeClass1
													.getresp_cad(con, qry,
															i_index, stk,
															apply_date);
											rs.next();
											String ca_name = rs
													.getString("cam_shortname");
											if (ca_name.equals("add stock")) {
												flg = 1;
												errors.add(
														"chk",
														new ActionError(
																"Error.message.Histiwf"));
											}
										}
									}
								}
								if (corpid.equals("changeindcurr")) {
									if (ftcurrency.equals("")
											| ftcurrency
													.equals("Select Currency")) {
										flg = 1;
										errors.add("Values", new ActionError(
												"Error.message.Currency"));
									}
									if (ind_div == null) {
										flg = 1;
										errors.add("Values", new ActionError(
												"Error.message.Radio"));
									}
									if (flg == 0) {
										String qry = ConnectInit.queries
												.getProperty("get_corporate_list_index");
										int cid = 0;
										ResultSet rs = ListTypeClass1
												.resultCorporate(con, qry);
										cid = ListTypeClass1.check_corp_name1(
												rs,
												request.getParameter("corpid"));
										if (rs != null)
											rs.close();

										String query = ConnectInit.queries
												.getProperty("check_diarycurr_exist");
										String date = UpdateCorp.accept_date();
										rs = ListTypeClass1.getresp_cad1(con,
												query, getI_index(),
												Integer.toString(cid), date);
										if (rs.next()) {
											flg = 1;
											errors.add(
													"Values",
													new ActionError(
															"Error.message.ActionApplied"));
										}

									}
									if (flg == 0) {
										String query = ConnectInit.queries
												.getProperty("resp_child_indcurr");
										ListTypeClass1.affect_child_list(con,
												this, query);
										ActionCorp.check_curr(this);
										if (isCheck_curr() == true) {
											flg = 1;
											errors.add(
													"Values",
													new ActionError(
															"Error.message.Currerror"));
										}
									}
									if (flg == 0) {
										if (chk_dt == 0) {
											if (ind_div.equals("td")) {
												String query = ConnectInit.queries
														.getProperty("select_index_detail");
												ResultSet rs = ListTypeClass1
														.getResult12(con,
																query,
																getI_index());
												if (rs.next()) {
													String close = rs
															.getString("index_closing_value");
													if (close == null) {
														flg = 1;
														errors.add(
																"chk",
																new ActionError(
																		"Error.message.IndexCloseParent"));
													} else {
														if (close.equals("0")) {
															flg = 1;
															errors.add(
																	"chk",
																	new ActionError(
																			"Error.message.IndexCloseParent"));
														}
													}
												} else {
													flg = 1;
													errors.add(
															"chk",
															new ActionError(
																	"Error.message.IndexCloseParent"));
												}
												ActionCorp.check_affect_index(
														con, this, query);
												if (isCheck() == false) {
													flg = 1;
													errors.add(
															"chk",
															new ActionError(
																	"Error.message.Childerr"));
												}
											}
										}
									}

								} // if(corpid.equals("capitalreduce")){

								if (flg == 0
										& (!corpid.equals("changeindcurr"))) {
									String qry = ConnectInit.queries
											.getProperty("get_corporate_list_index");
									int cid = 0;
									ResultSet rs = ListTypeClass1
											.resultCorporate(con, qry);
									cid = ListTypeClass1.check_corp_name1(rs,
											corpid);
									if (rs != null)
										rs.close();
									String query = ConnectInit.queries
											.getProperty("check_index_action");
									String date = UpdateCorp.accept_date();
									String stk_val[] = request
											.getParameterValues("stock");
									if (stk_val != null) {
										int l1 = stk_val.length;
										for (int i = 0; i < l1; i++) {
											String sid = stk_val[i];
											rs = ListTypeClass1
													.check_dairy_exist(
															con,
															query,
															Integer.toString(cid),
															i_index, sid, date);
											if (rs.next()) {
												// flg=1;
												// errors.add("Values",new
												// ActionError("Error.message.ActionApplied"));
												// break;
											}
										}
									}

								}
								if (flg == 0) {
									// check stock status //ok
									String stk_val[] = (String[]) request
											.getParameterValues("stock"); // here
																			// is
																			// the
																			// problem
									if (stk_val != null) {
										int l1 = stk_val.length;
										for (int i = 0; i < l1; i++) {
											String sid = stk_val[i];
											int merge_chk = check_stkstatus(
													con, connect, sid,
													apply_date);
											Logging.debug("merger chk ==="
													+ merge_chk);
											if (merge_chk > 0 || merge_chk == 0) {
												flg = 1;
												errors.add(
														"chk",
														new ActionError(
																"Error.message.HistStkStatus"));
												break;
											}
										}
									}
								}
							}// butt null
						}
					}

					if (button.equals("Ok")) {
						if (chk_but == null)
							errors.add("chk_but", new ActionError(
									"Error.message.Check"));
						else {
							String corp_id = getCorpid();// request.getParameter("s_corporateAction");
							if (corp_id != null) {
								if (corp_id.equals("changeiwf")) {
									String chkbut[] = request
											.getParameterValues("chk_but");
									int l1 = chkbut.length;
									if (l1 > 1) {
										errors.add("chk_but", new ActionError(
												"Error.message.MultipleSelect"));
									}
								}
							}
						}
					}
				}
				String new_ind_but = request.getParameter("new_index_but");
				if (new_ind_but != null) {
					if (new_ind_but.equals("Action")) {
						String corp_val = request.getParameter("corpid");
						int type_id = 0;
						int flg = 0;
						try {

							String query = ConnectInit.queries
									.getProperty("select_index_type");
							ResultSet rs = ListTypeClass1.getResult1(con,
									query, i_index);
							rs.next();
							type_id = rs.getInt("index_type_id");
						} catch (Exception e) {
							Logging.error("error=" + e.getMessage());
						}
						if ((type_id == 1 & corp_val.equals("changeiwf"))
								| (type_id == 3 & corp_val.equals("changeiwf"))
								| (type_id == 4 & ((corp_val
										.equals("changeiwf"))
										| (corp_val.equals("addstock")) | (corp_val
											.equals("deletestock"))))
								| (type_id == 5 & ((corp_val.equals("addstock"))
										| (corp_val.equals("deletestock"))
										| (corp_val.equals("changeiwf")) | (corp_val
											.equals("rebasing"))))) {
							errors.add("chk_but", new ActionError(
									"Error.message.TypeCheck"));
						}
						if (corp_val.equals("changeindcurr"))
							errors.add("chk_but", new ActionError(
									"Error.message.NewIndCur"));

					}
					if (new_ind_but.equals("Submit")) {
						if (i_index != null)
							if ((i_index.equals("Select Index"))
									| (i_index.equals("")))
								errors.add("i_index", new ActionError(
										"Error.message.Index"));
						if (corpid == null)
							errors.add("corpid", new ActionError(
									"Error.message.CorpAction"));
						if (corpid != null) {
							if ((corpid.equals("Select Action"))
									| (corpid.equals(""))) {
								corpid = null;
								errors.add("corpid", new ActionError(
										"Error.message.CorpAction"));
							}
							if ((corpid.equals("addstock"))
									| (corpid.equals("changeiwf"))
									| (corpid.equals("deletestock"))) {
								String stock = request.getParameter("stock");
								if (stock == null)
									errors.add("stock", new ActionError(
											"Error.message.Stock"));
							}
							if (corpid.equals("changeiwf")) {
								String val = request.getParameter("value");
								if (val != null) {
									if (val.equals(""))
										errors.add("val", new ActionError(
												"Error.message.Value"));
									else {
										float val1 = Float.parseFloat(val);
										if (val1 <= 0 | val1 > 1)
											errors.add("val", new ActionError(
													"Error.message.Value"));
									}
								}
							}
						}
						String corp_nm = getCorpid();
						String stock[] = request.getParameterValues("stock");
						String qry = ConnectInit.queries
								.getProperty("get_corporate_list_index");
						int cid = 0;
						try {
							//getting events such as add stock,delete stock, change in iwf
							ResultSet rs = ListTypeClass1.resultCorporate(con,
									qry);
							cid = ListTypeClass1.check_corp_name1(rs, corp_nm);
							rs.close();

							int flag = 0;
							if ((corp_nm.equals("addstock"))
									| (corp_nm.equals("deletestock"))) {
								String query = ConnectInit.queries
										.getProperty("check_corp_adddel_exist");
								int len = stock.length;
								hash = getHash();
								hash.clear();
								setHash(hash);
								hash = getHash();
								for (int i = 0; i < len; i++) {
									String stk = stock[i];
									hash.put(new String(stk), new String(stk));
									try {
										rs = ListTypeClass1.getresp_cad(con,
												query, Integer.toString(cid),
												getI_index(), stk);
										if (rs.next()) {
											flag = 1;
										}
									} catch (Exception e) {
										Logging.error("Error=" + e.getMessage());
									}
								}
								setHash(hash);
								if (flag == 1) {
									errors.add("val", new ActionError(
											"Error.message.AlreadyStock"));
								}
							}
						} catch (Exception e) {
							Logging.error("error==" + e.getMessage());
						}
						// check stock status

						String stk[] = request.getParameterValues("stock");
						int len = 0;
						if (stk != null)
							len = stk.length;
						for (int i = 0; i < len; i++) {
							int merge_chk = check_stkstatus(con, connect,
									stk[i], apply_date);
							if (merge_chk > 0 || merge_chk == 0) {
								errors.add("chk", new ActionError(
										"Error.message.HistStkStatus"));
								break;
							}
						}
					}
				}
				String ap_co_but2 = request.getParameter("ap_co_button2");
				if (ap_co_but2 != null) {
					if (ap_co_but2.equals("YDelete")) {
						String chk[] = request.getParameterValues("c_Cad");
						if (chk == null)
							errors.add("chk", new ActionError(
									"Error.message.Check"));
					}
					if (ap_co_but2.equals("Update")) {
						if (hash.isEmpty())
							errors.add("hash", new ActionError(
									"Error.message.Hash"));
						else {
							String[] app = request.getParameterValues("apply");
							String[] based = request
									.getParameterValues("basedate");
							String[] val = request.getParameterValues("value");
							String[] ids = request.getParameterValues("id");
							for (int i = 0; i < ids.length; i++) {
								Object obj = hash.get(ids[i]);
								String name = obj.toString();
								if (name.equals("change iwf")) {
									if (val[i].equals("")) {
										errors.add("val", new ActionError(
												"Error.message.Value"));
										break;
									}
									float val1 = Float.parseFloat(val[i]);
									if (val1 > 1.0 | val1 <= 0.0) {
										errors.add("val", new ActionError(
												"Error.message.Value"));
										break;
									}
								}
							}
						}

					}
					if (ap_co_but2.equals("Apply")) {
						String butt = getButton();
						String chk[] = request.getParameterValues("c_Cad");
						String query = ConnectInit.queries
								.getProperty("select_rep_cad");
						if (butt == null) {
							if (chk == null)
								errors.add("chk", new ActionError(
										"Error.message.Check"));
							else {
								int len = chk.length;
								int itemp = 0, index = 0;
								String itemp1 = null, chk_apply = null;
								String dt = UpdateCorp.accept_date();
								Logging.debug("len is===" + len);
								Logging.debug("chk value==" + chk[0]);
								if (len == 1) {
									String cid = request.getParameter("c_Cad");
									ResultSet rs = ListTypeClass1.getAffected(
											con, query, cid);
									rs.next();
									String apply_date = rs
											.getString("apply_on_date");
									String stock = rs.getString("stock_id");
									rs.close();
									// check stock status
									int merge_chk = check_stkstatus(con,
											connect, stock, apply_date);
									Logging.debug("merger chk===" + merge_chk);
									if (merge_chk > 0 || merge_chk == 0)
										errors.add("chk", new ActionError(
												"Error.message.HistStkStatus"));
								} else {
									for (int i = 0; i < len; i++) {
										try {
											Logging.debug("in for loop");
											Logging.debug("cad id in for==="
													+ chk[i]);
											String query1 = ConnectInit.queries
													.getProperty("check_applyon_date");
											String get_name = ConnectInit.queries
													.getProperty("get_corp_name");
											String check_compo = ConnectInit.queries
													.getProperty("index_comp_detail");

											ResultSet rs = ListTypeClass1
													.getAffected(con, query,
															chk[i]);
											rs.next();
											String apply_date = rs
													.getString("apply_on_date");
											String stock = rs
													.getString("stock_id");
											String corp = rs
													.getString("cam_id");
											int iid = rs.getInt("index_id");
											ResultSet rs1 = ListTypeClass1
													.getAffected(con, get_name,
															corp);
											rs1.next();
											String corp_name = rs1
													.getString("cam_shortname")
													.toLowerCase().trim();
											rs1.close();

											if (chk_apply == null)
												chk_apply = apply_date;
											else {
												if (!(chk_apply
														.equals(apply_date))) {
													errors.add(
															"date",
															new ActionError(
																	"Error.message.DifferentApply"));
													break;
												}
											}
											Logging.debug("stock in for="
													+ stock + "aappl="
													+ apply_date);
											// check stock status
											int merge_chk = check_stkstatus(
													con, connect, stock,
													apply_date);
											Logging.debug("merger chk==="
													+ merge_chk);
											if (merge_chk > 0 || merge_chk == 0) {
												errors.add(
														"chk",
														new ActionError(
																"Error.message.HistStkStatus"));
												break;
											}
											if (corp_name.equals("add stock")) {
												rs1 = ListTypeClass1
														.getResult_corp(
																con,
																check_compo,
																Integer.toString(iid),
																stock);
												if (rs1.next())
													errors.add(
															"chk",
															new ActionError(
																	"Error.message.Alreadyadd"));
											}
											if ((corp_name
													.equals("delete stock"))
													| (corp_name
															.equals("change iwf"))) {
												rs1 = ListTypeClass1
														.getResult_corp(
																con,
																check_compo,
																Integer.toString(iid),
																stock);
												if (!rs1.next())
													errors.add(
															"chk",
															new ActionError(
																	"Error.message.Alreadydelete"));
											}
											String date1 = UpdateCorp
													.accept_date();
											if (corp_name.equals("change iwf")) {
												String qry = ConnectInit.queries
														.getProperty("check_index_action");
												rs = ListTypeClass1
														.check_dairy_exist(
																con,
																qry,
																corp,
																Integer.toString(iid),
																stock, date1);
												if (rs.next())
													errors.add(
															"chk",
															new ActionError(
																	"Error.message.ActionApplied"));
											}

											int chk_dt = ComputeIndexForm
													.CompareDate(apply_date, dt); // check
																					// for
																					// the
																					// current
																					// date
																					// and
																					// user's
																					// entered
																					// date
											if (chk_dt > 0) {
												errors.add(
														"chk",
														new ActionError(
																"Error.message.GreaterDate"));
												break;
											}
											if (chk_dt < 0) {
												if (len > 1) {
													errors.add(
															"chk",
															new ActionError(
																	"Error.message.Histmultiple"));
													break;
												}

											} else {
												if (itemp1 == null)
													itemp1 = corp_name;
												else {
													if (((itemp1
															.equals("add stock"))
															| (itemp1
																	.equals("delete stock")) | (itemp1
																.equals("change iwf")))
															& corp_name
																	.equals("rebasing"))
														errors.add(
																"chk",
																new ActionError(
																		"Error.message.CombCorp"));
													if (((itemp1
															.equals("rebasing")) & ((corp_name
															.equals("add stock"))
															| (corp_name
																	.equals("delete stock")) | (corp_name
																.equals("change iwf")))))
														errors.add(
																"chk",
																new ActionError(
																		"Error.message.CombCorp"));
												}
												setI_index(rs1
														.getString("index_id"));
												if (index == 0)
													index = iid;
												else if (index != iid)
													errors.add(
															"chk",
															new ActionError(
																	"Error.message.SameIndex"));

												if (len != 1)
													if (itemp1
															.equals(corp_name))
														if (itemp1
																.equals("rebasing"))
															errors.add(
																	"chk",
																	new ActionError(
																			"Error.message.MultipleSelect"));
											}
										} catch (Exception e) {
											Logging.debug("Error="
													+ e.getMessage());
										}
									}// for end
								}
							}// if id is selected
						} else // if there is undo
						{
							if (chk == null)
								errors.add("chk", new ActionError(
										"Error.message.Check"));
							else {
								int l1 = chk.length;
								if (l1 > 1)
									errors.add("chk", new ActionError(
											"Error.message.MultipleSelect"));
								else {
									// check stock status
									String cad = request.getParameter("c_Cad");
									ResultSet rs = ListTypeClass1.getAffected(
											con, query, cad);
									rs.next();
									int merge_chk = check_stkstatus(con,
											connect, rs.getString("stock_id"),
											rs.getString("applied_date"));
									rs.close();
									if (merge_chk > 0 || merge_chk == 0) {
										errors.add("chk", new ActionError(
												"Error.message.HistStkStatus"));
									}
								}
							}
						}
					}
				}
				String ind_comp_but = request.getParameter("compo_button");
				if (ind_comp_but != null) {
					if (ind_comp_but.equals("Add")) {
						String chk[] = request.getParameterValues("c_Cad");
						if (chk == null)
							errors.add("chk", new ActionError(
									"Error.message.Check"));
					}
					if (ind_comp_but.equals("Remove")) {
						setHash2(getHash2());
						String chk[] = request.getParameterValues("c_Cad1");
						if (chk == null)
							errors.add("chk", new ActionError(
									"Error.message.Check"));
					}
					if (ind_comp_but.equals("Commit")) {
						Hashtable hash3 = getHash3();
						int flag = 0;
						if (!(hash3.isEmpty())) {
							for (Enumeration enum1 = hash3.keys(); enum1
									.hasMoreElements();) {
								String id = (String) enum1.nextElement();
								Object obj = hash3.get(id);
								String cid = obj.toString();

								String query = ConnectInit.queries
										.getProperty("select_rep_cad");
								ResultSet rs = ListTypeClass1.getAffected(con,
										query, cid);
								rs.next();
								String camid = rs.getString("cam_id");
								String index_id = rs.getString("index_id");
								rs.close();
								query = ConnectInit.queries
										.getProperty("check_index_action");
								String date = apply_date;// UpdateCorp.accept_date();
								rs = ListTypeClass1.check_dairy_exist(con,
										query, camid, index_id, id, date);
								if (rs.next()) {
									flag = 1;
									errors.add("chk", new ActionError(
											"Error.message.ActionApplied"));
									break;
								}
								rs.close();
							}
						}
						Hashtable hash4 = getHash4();
						if (flag == 0) {
							if (!(hash4.isEmpty())) {
								for (Enumeration enum1 = hash4.keys(); enum1
										.hasMoreElements();) {
									String id = (String) enum1.nextElement();
									Object obj = hash4.get(id);
									String cid = obj.toString();

									String query = ConnectInit.queries
											.getProperty("select_rep_cad");
									ResultSet rs = ListTypeClass1.getAffected(
											con, query, cid);
									rs.next();
									String camid = rs.getString("cam_id");
									String index_id = rs.getString("index_id");
									rs.close();

									query = ConnectInit.queries
											.getProperty("check_index_action");
									String date = apply_date;// UpdateCorp.accept_date();
									rs = ListTypeClass1.check_dairy_exist(con,
											query, camid, index_id, id, date);
									if (rs.next()) {
										flag = 1;
										errors.add("chk", new ActionError(
												"Error.message.ActionApplied"));
										break;
									}
									rs.close();
								}
							}
						}
					}
					if (ind_comp_but.equals("Apply")) {
						ind_comp = null;
						String dt = UpdateCorp.accept_date(); // get the current
																// date
						String apply = getApply_date();
						int chk_dt = ComputeIndexForm.CompareDate(apply_date,
								dt); // check for the current date and user's
										// entered date
						if (chk_dt != 0) {
							Hashtable data = getHash1();// for deletion
							for (Enumeration enum1 = data.keys(); enum1
									.hasMoreElements();) {
								String id2 = (String) enum1.nextElement();
								String qry = ConnectInit.queries
										.getProperty("check_hist_add_del");
								String cam_name = "add stock";
								ResultSet rs = ListTypeClass1
										.check_dairy_exist(con, qry, i_index,
												id2, apply_date, cam_name);
								boolean chk_rs = rs.next();
								if (chk_rs == true) {
									errors.add("chk", new ActionError(
											"Error.message.Histdel"));
									break;
								}
							}
							Hashtable data1 = getHash2();// for addition
							for (Enumeration enum1 = data1.keys(); enum1
									.hasMoreElements();) {
								String id2 = (String) enum1.nextElement();
								Object obj1 = data1.get(id2);
								String cad = obj1.toString();

								String cquery = ConnectInit.queries
										.getProperty("select_rep_cad");
								ResultSet rs1 = ListTypeClass1.getResult1(con,
										cquery, cad);
								rs1.next();
								String corp_id = rs1.getString("cam_id");
								rs1.close();
								String get_name = ConnectInit.queries
										.getProperty("get_corp_name");
								rs1 = ListTypeClass1.getResult1(con, get_name,
										corp_id);
								rs1.next();
								String corp_nm = rs1.getString("cam_shortname")
										.toLowerCase().trim();
								rs1.close();

								if (corp_nm.equals("add stock")) {
									String qry = ConnectInit.queries
											.getProperty("check_hist_add_del");
									String cam_name = "delete stock";
									ResultSet rs = ListTypeClass1
											.check_dairy_exist(con, qry,
													i_index, id2, apply_date,
													cam_name);
									boolean chk_rs = rs.next();
									if (chk_rs == true) {
										errors.add("chk", new ActionError(
												"Error.message.Histadd"));
										break;
									}
								}
							}
						}
					}
				}
				String new_corp = new_corp_but;
				if (new_corp != null) {
					if ((new_corp.equals("Submit"))
							| (new_corp.equals("NewSubmit"))) {
						int flg = 0;
						if ((exc.equals("Select Exchange")) | (exc.equals(""))) {
							flg = 1;
							errors.add("exc", new ActionError(
									"Error.message.StockExchName"));
						}
						if ((s_stock.equals("Select Stock"))
								| (s_stock.equals(""))) {
							flg = 1;
							errors.add("exc", new ActionError(
									"Error.message.Stock"));
						}
						if ((corpid.equals("Select Action"))
								| (corpid.equals(""))) {
							flg = 1;
							errors.add("exc", new ActionError(
									"Error.message.CorpAction"));
						} else {
							String r1 = request.getParameter("s_ratio1");
							String r2 = request.getParameter("s_ratio2");
							String amt = request.getParameter("amount");
							if ((corpid.equals("stockdividend/bonus"))
									| (corpid.equals("split"))
									| (corpid.equals("reversesplit"))
									| (corpid.equals("rightsoffering"))
									| (corpid.equals("warrantconversion")))
								if ((r1.equals("")) | (r2.equals(""))) {
									flg = 1;
									errors.add("r1", new ActionError(
											"Error.message.Ratio"));
								}
							if ((corpid.equals("cashdividend"))
									| (corpid.equals("rightsoffering"))
									| (corpid.equals("sharesbuyback"))
									| (corpid.equals("spin-off"))
									| (corpid.equals("specialdividend"))) {
								if (amt_per == null) {
									flg = 1;
									errors.add("amt_per", new ActionError(
											"Error.message.Radio"));
								}
								if (amt.equals("")) {
									flg = 1;
									errors.add("amt", new ActionError(
											"Error.message.Amount"));
								} else {
									boolean chk_amt = amt.startsWith("-");
									if (chk_amt == true) {
										flg = 1;
										errors.add("amt", new ActionError(
												"Error.message.NegativeAmount"));
									}
								}
							}
							if (corpid.equals("sharesbuyback")
									| (corpid.equals("warrantconversion"))
									| (corpid.equals("shareissuance"))
									| (corpid.equals("capitalreduce"))
									| (corpid.equals("adrissue")))
								if (share.equals("")) {
									flg = 1;
									errors.add("share", new ActionError(
											"Error.message.Share"));
								} else {
									boolean chk_shr = share.startsWith("-");
									if (chk_shr == true) {
										flg = 1;
										errors.add("share", new ActionError(
												"Error.message.NegativeShare"));
									}
								}
						}
						if (flg == 0) {
							// check stock status
							int merge_chk = check_stkstatus(con, connect,
									getS_stock(), apply_date);
							if (merge_chk > 0 || merge_chk == 0) {
								flg = 1;
								errors.add("chk", new ActionError(
										"Error.message.HistStkStatus"));
							}
						}
						if (flg == 0) {
							int r_val1 = Integer.parseInt(s_ratio1);
							int r_val2 = Integer.parseInt(s_ratio2);
							if (corpid.equals("split")) {
								if (r_val1 > r_val2) {
									errors.add("share", new ActionError(
											"Error.message.Ratiosplit"));
								}
							}
							if (corpid.equals("reversesplit")) {
								if (r_val2 > r_val1) {
									errors.add("share", new ActionError(
											"Error.message.Ratiorevsplit"));
								}
							}
						}
					}// submit or newsubmit
				}
				String ap_but1 = request.getParameter("ap_co_button1");
				if (ap_but1 != null) {
					if (ap_but1.equals("Update")) {
						if (c_Cad == null)
							errors.add("chk", new ActionError(
									"Error.message.Check"));
						else {
							String str[] = request.getParameterValues("c_Cad");
							int len = str.length;
							if (len > 1)
								errors.add("chk", new ActionError(
										"Error.message.Update"));
						}
					}
					if (ap_but1.equals("YDelete")) {
						if (c_Cad == null)
							errors.add("chk", new ActionError(
									"Error.message.Check"));
					}
					if (ap_but1 != null) {
						if (ap_but1.equals("Apply")) {
							adjust = null;
							newTIS = null;
							newLTP = null;
							newFace = null;
							String butt = getButton();
							if (butt != null)
								if (butt.equals(""))
									butt = null;

							if (butt == null) {
								if (c_Cad == null)
									errors.add("chk", new ActionError(
											"Error.message.Check"));
								else {
									String str[] = request
											.getParameterValues("c_Cad");
									int len = str.length;
									int flg = 0;
									String temp = null, temp1 = null, cam = null, apply = null;
									String get_name = ConnectInit.queries
											.getProperty("get_corp_name");
									Logging.debug("before for");
									for (int i = 0; i < len; i++) {
										String query = ConnectInit.queries
												.getProperty("select_rep_cad");
										ResultSet rs = ListTypeClass1
												.getAffected(con, query, str[i]);
										rs.next();
										String apply_date = rs
												.getString("apply_on_date");
										String stock = rs.getString("stock_id");
										cam = rs.getString("cam_id");
										String sid = rs.getString("stock_id");
										String date1 = UpdateCorp.accept_date();
										ResultSet rs1 = null;
										String query1 = ConnectInit.queries
												.getProperty("check_applyon_date");
										rs1 = ListTypeClass1.getResult_apply(
												con, query1, str[i], date1);

										if (!(rs1.next())) {
											errors.add(
													"chk",
													new ActionError(
															"Error.message.GreaterDate"));
											rs1.close();
										} else {
											if (temp == null & temp1 == null
													& apply == null) {
												temp = cam;
												temp1 = sid;
												apply = apply_date;
											} else {
												if ((!(temp.equals(cam)))
														& (!(temp1.equals(sid)))) {
													flg = 1;
													errors.add(
															"chk",
															new ActionError(
																	"Error.message.Corp1"));
												} else {
													if (!(temp.equals(cam))) {
														flg = 1;
														errors.add(
																"chk",
																new ActionError(
																		"Error.message.Corp2"));
													} else {
														if (!(temp1.equals(sid))) {
															flg = 1;
															errors.add(
																	"chk",
																	new ActionError(
																			"Error.message.Corp3"));
														}
													}
												}
												if (flg == 0) {
													if (!(apply
															.equals(apply_date))) {
														flg = 1;
														errors.add(
																"chk",
																new ActionError(
																		"Error.message.DifferentApply"));
													}
												}
												if (flg == 0) {
													// check stock status
													int merge_chk = check_stkstatus(
															con, connect,
															stock, apply_date);
													if (merge_chk > 0
															|| merge_chk == 0) {
														flg = 1;
														errors.add(
																"chk",
																new ActionError(
																		"Error.message.HistStkStatus"));
													}
												}
												if (flg == 1)
													break;
											}
										}
									}// for end
									if (len == 1) {
										String str1 = request
												.getParameter("c_Cad");
										String query = ConnectInit.queries
												.getProperty("select_rep_cad");
										ResultSet rs = ListTypeClass1
												.getAffected(con, query, str1);
										rs.next();
										String apply_date = rs
												.getString("apply_on_date");
										String stock = rs.getString("stock_id");
										rs.close();
										// check stock status
										int merge_chk = check_stkstatus(con,
												connect, stock, apply_date);
										Logging.debug("merge_chk==="
												+ merge_chk);
										if (merge_chk > 0 || merge_chk == 0) {
											flg = 1;
											errors.add(
													"chk",
													new ActionError(
															"Error.message.HistStkStatus"));
										}
									}
									if (len > 1) {
										String cam_name = ActionCorp
												.getcorpname(con, get_name, cam);
										cam_name = cam_name.toLowerCase()
												.trim();
										if ((cam_name.equals("spin-off"))
												| (cam_name
														.equals("stock dividend/bonus"))
												| (cam_name
														.equals("special dividend"))
												| (cam_name.equals("split"))
												| (cam_name
														.equals("reverse split"))
												| (cam_name
														.equals("rights offering"))
												| (cam_name
														.equals("warrant conversion")))
											errors.add(
													"chk",
													new ActionError(
															"Error.message.MultipleSelect"));
									}
								}
							}
							if (butt != null) {
								if (c_Cad == null)
									errors.add("chk", new ActionError(
											"Error.message.Check"));
								else {
									String str[] = request
											.getParameterValues("c_Cad");
									int len = str.length;
									if (len > 1)
										errors.add("chk", new ActionError(
												"Error.message.MultipleSelect"));
									else {
										String str1 = request
												.getParameter("c_Cad");
										String query = ConnectInit.queries
												.getProperty("undo_merger");
										ResultSet rs = ListTypeClass1
												.getAffected(con, query, str1);
										if (rs.next())
											errors.add("chk", new ActionError(
													"Error.message.UndoMerge"));
									}

								}
							}
						}// apply if
					}
				}
				String ncorp = request.getParameter("ncorp_button");
				if (ncorp != null) {
					if (ncorp.equals("Apply")) {
						String butt = getButton();
						if (butt == null) {
							int flg = 0;
							String qry = ConnectInit.queries
									.getProperty("get_undo_close_value");
							PreparedStatement stmt = con.prepareStatement(qry);
							stmt.setString(1, s_stock);
							stmt.setString(2, apply_date);
							ResultSet rs = stmt.executeQuery();
							boolean chk_rs = rs.next();
							if (chk_rs == false)
								errors.add("chk", new ActionError(
										"Error.message.StockCloseDate"));
							else {
								String adj = rs.getString("adjusted_price");
								if (adj == null)
									adj = rs.getString("stock_closing_value");
								if (adj == null)
									errors.add("chk", new ActionError(
											"Error.message.StockCloseDate"));
								else {
									if (adj.equals("0"))
										errors.add("chk", new ActionError(
												"Error.message.StockCloseDate"));
									else {
										setClose(adj);
										String corp = request
												.getParameter("corpid");
										if (corp.equals("sharesbuyback")
												| (corp.equals("warrantconversion"))
												| (corp.equals("shareissuance"))
												| (corp.equals("capitalreduce"))
												| (corp.equals("adrissue"))) {
											String share = request
													.getParameter("share");
											setShare(share);
											boolean chk_shr = share
													.startsWith("-");
											if (chk_shr == true) {
												flg = 1;
												errors.add(
														"chk",
														new ActionError(
																"Error.message.NegativeShare"));
											} else {
												if ((corp
														.equals("sharesbuyback"))
														| (corp.equals("capitalreduce"))) {
													float shr = Float
															.parseFloat(share);
													String query = ConnectInit.queries
															.getProperty("detail_stock_master");
													ResultSet rs1 = ListTypeClass1
															.getAffected(con,
																	query,
																	s_stock);
													rs1.next();
													float tis = rs1
															.getFloat("tis");
													if (shr >= tis) {
														flg = 1;
														errors.add(
																"share",
																new ActionError(
																		"Error.message.Greatershare"));
													}
												}
											}
										}
										if ((corp.equals("cashdividend"))
												| (corp.equals("rightsoffering"))
												| (corp.equals("sharesbuyback"))
												| (corp.equals("spin-off"))
												| (corp.equals("specialdividend"))) {
											String amt = request
													.getParameter("amt");// request.getParameter("amount");
											setAmt(amt);
											boolean chk_amt = amt
													.startsWith("-");
											if (chk_amt == true) {
												flg = 1;
												errors.add(
														"share",
														new ActionError(
																"Error.message.NegativeAmount"));
											} else {
												String close = getClose();
												float amount = Float
														.parseFloat(amt);
												float ltp = Float
														.parseFloat(close);
												if (amount > ltp) {
													flg = 1;
													errors.add(
															"share",
															new ActionError(
																	"Error.message.Greateramount"));
												}
											}
										}
									}
								}
								if (flg == 0) {
									String corp = request
											.getParameter("corpid");
									qry = ConnectInit.queries
											.getProperty("get_corporate_list_stock");
									int cid = 0;
									rs = ListTypeClass1.resultCorporate(con,
											qry);
									cid = ListTypeClass1.check_corp_name1(rs,
											corp);
									rs.close();
									String query = ConnectInit.queries
											.getProperty("check_action");
									String apply = UpdateCorp.accept_date();
									String stock = request
											.getParameter("s_stock");
									ResultSet rs1 = ListTypeClass1.getresp_cad(
											con, query, Integer.toString(cid),
											stock, apply);
									if (rs1.next()) {
										setAmt(request.getParameter("amt"));
										setShare(request.getParameter("share"));
										setRatio1(request
												.getParameter("ratio1"));
										setRatio2(request
												.getParameter("ratio2"));
										errors.add("chk", new ActionError(
												"Error.message.ActionApplied"));
									}
								}
							}
							rs.close();
						}
						if (butt != null) {
							String qry = ConnectInit.queries
									.getProperty("get_undo_close_value");
							PreparedStatement stmt = con.prepareStatement(qry);
							stmt.setString(1, s_stock);
							stmt.setString(2, getApplied_date());
							ResultSet rs = stmt.executeQuery();
							boolean chk_rs = rs.next();
							if (chk_rs == false)
								errors.add("chk", new ActionError(
										"Error.message.StockClose"));
							else {
								String adj = rs.getString("adjusted_price");
								if (adj == null)
									adj = rs.getString("stock_closing_value");
								if (adj == null)
									errors.add("chk", new ActionError(
											"Error.message.StockClose"));
								else {
									if (adj.equals("0"))
										errors.add("chk", new ActionError(
												"Error.message.StockClose"));
									else
										setClose(adj);
								}
							}
						}
					}
					if (ncorp.equals("NApply")) {
						float tis = 0;
						newFace = null;
						// s_affectedIndex=null;
						int flg = 0;
						if (getExc().equals("Select Exchange"))
							errors.add("chk", new ActionError(
									"Error.message.SelectStockExc"));
						if ((apply_date.equals("")) | (apply_date == null))
							errors.add("chk", new ActionError(
									"Error.message.ApplyDate"));
						String stock = request.getParameter("s_stock");
						if ((stock.equals("Select Stock")) | (stock.equals("")))
							errors.add("chk", new ActionError(
									"Error.message.Stock"));
						else {
							if (apply_date != null) {
								String dt = UpdateCorp.accept_date();
								Logging.debug("appply datew in ==" + apply_date);
								Logging.debug("dt in date==" + dt);
								int ap_chk = ComputeIndexForm.CompareDate(
										apply_date, dt);
								Logging.debug("apply chk in commit" + ap_chk);
								if (ap_chk > 0) {
									flg = 1;
									errors.add("chk", new ActionError(
											"Error.message.GreaterDate"));
								}
								if (flg == 0) {
									int flg_val = 0;
									// check stock status
									int merge_chk = check_stkstatus(con,
											connect, getS_stock(), apply_date);
									if (merge_chk > 0 || merge_chk == 0) {
										flg_val = 1;
										errors.add("chk", new ActionError(
												"Error.message.HistStkStatus"));
									}

									if (ap_chk < 0) {
										if (flg_val == 0) {
											String qry = ConnectInit.queries
													.getProperty("get_undo_close_value");
											PreparedStatement stmt = con
													.prepareStatement(qry);
											stmt.setString(1, stock);
											stmt.setString(2, apply_date);
											ResultSet rs = stmt.executeQuery();
											boolean chk = rs.next();
											if (chk == false)
												errors.add(
														"chk",
														new ActionError(
																"Error.message.StockCloseDate"));
											else {
												String close = rs
														.getString("adjusted_price");
												if (close == null)
													close = rs
															.getString("stock_closing_value");

												if (close == null)
													errors.add(
															"chk",
															new ActionError(
																	"Error.message.StockCloseDate"));
												else {
													if (close.equals("0"))
														errors.add(
																"chk",
																new ActionError(
																		"Error.message.StockCloseDate"));
													else
														setClose(close);
												}
											}
											// tis=rs.getFloat("tis");
											rs.close();
										}
										Logging.debug("close in corp-=="
												+ getClose());
									}
									if (ap_chk == 0) {
										if (flg_val == 0) {
											String qry = ConnectInit.queries
													.getProperty("get_latest_stock_closing_value_tis");
											PreparedStatement stmt = con
													.prepareStatement(qry);
											stmt.setString(1, stock);
											stmt.setString(2, stock);
											ResultSet rs = stmt.executeQuery();
											boolean chk = rs.next();
											if (chk == false)
												errors.add(
														"chk",
														new ActionError(
																"Error.message.StockClose"));
											else {
												String close = rs
														.getString("adjusted_price");
												if (close == null)
													close = rs
															.getString("stock_closing_value");

												if (close == null)
													errors.add(
															"chk",
															new ActionError(
																	"Error.message.StockClose"));
												else {
													if (close.equals("0"))
														errors.add(
																"chk",
																new ActionError(
																		"Error.message.StockClose"));
													else
														setClose(close);

												}
											}
											rs.close();
										}
									}
								}
							}// apply date
						}
						String corp = request.getParameter("corpid");
						if ((corp.equals("Select Action")) | (corp.equals("")))
							errors.add("chk", new ActionError(
									"Error.message.CorpAction"));
						else {
							if ((corp.equals("stockdividend/bonus"))
									| (corp.equals("split"))
									| (corp.equals("reversesplit"))
									| (corp.equals("rightsoffering"))
									| (corp.equals("warrantconversion"))
									| (corp.equals("specialdividend"))) {
								String r1 = request.getParameter("ratio1");
								String r2 = request.getParameter("ratio2");
								if (r1.equals("") | r2.equals("")) {
									flg = 1;
									errors.add("r1", new ActionError(
											"Error.message.Ratio"));
								}
							}
							if ((corp.equals("cashdividend"))
									| (corp.equals("rightsoffering"))
									| (corp.equals("sharesbuyback"))
									| (corp.equals("spin-off"))
									| (corp.equals("specialdividend"))) {
								String amt = request.getParameter("amt");
								if (amt.equals("")) {
									flg = 1;
									errors.add("amt", new ActionError(
											"Error.message.Amount"));
								}
							}
							if (corp.equals("sharesbuyback")
									| (corp.equals("warrantconversion"))
									| (corp.equals("shareissuance"))
									| (corp.equals("capitalreduce"))
									| (corp.equals("adrissue"))) {
								String shr = request.getParameter("share");
								if (shr.equals("")) {
									flg = 1;
									errors.add("share", new ActionError(
											"Error.message.Share"));
								} else {
									boolean chk_shr = shr.startsWith("-");
									if (chk_shr == true) {
										flg = 1;
										errors.add("amt", new ActionError(
												"Error.message.NegativeShare"));

									}
								}

							}
							if (flg == 0) {
								if ((corp.equals("sharesbuyback"))
										| (corp.equals("capitalreduce"))) {
									String shr = request.getParameter("share");
									boolean chk_shr = shr.startsWith("-");
									if (chk_shr == true) {
										flg = 1;
										errors.add("amt", new ActionError(
												"Error.message.NegativeShare"));

									} else {
										float shrval = Float.parseFloat(shr);
										String query = ConnectInit.queries
												.getProperty("detail_stock_master");
										ResultSet rs1 = ListTypeClass1
												.getAffected(con, query,
														s_stock);
										rs1.next();
										tis = rs1.getFloat("tis");
										if (shrval >= tis) {
											flg = 1;
											errors.add(
													"share",
													new ActionError(
															"Error.message.Greatershare"));
										}
									}
								}
								if ((corp.equals("cashdividend"))
										| (corp.equals("rightsoffering"))
										| (corp.equals("sharesbuyback"))
										| (corp.equals("spin-off"))
										| (corp.equals("specialdividend"))) {
									String amt = request.getParameter("amt");
									boolean chk_amt = amt.startsWith("-");
									if (chk_amt == true) {
										flg = 1;
										errors.add("share", new ActionError(
												"Error.message.NegativeAmount"));
									} else {
										String close = getClose();
										float amount = Float.parseFloat(amt);
										float ltp = Float.parseFloat(close);
										if (amount > ltp) {
											flg = 1;
											errors.add(
													"share",
													new ActionError(
															"Error.message.Greateramount"));
										}
									}
								}
								if (corp.equals("split")) {
									String r1 = request.getParameter("ratio1");
									int r1_val = Integer.parseInt(r1);
									String r2 = request.getParameter("ratio2");
									int r2_val = Integer.parseInt(r2);
									if (r1_val > r2_val) {
										flg = 1;
										errors.add("ratio", new ActionError(
												"Error.message.Ratiosplit"));
									}
								}
								if (corp.equals("reversesplit")) {
									String r1 = request.getParameter("ratio1");
									int r1_val = Integer.parseInt(r1);
									String r2 = request.getParameter("ratio2");
									int r2_val = Integer.parseInt(r2);
									if (r2_val > r1_val) {
										flg = 1;
										errors.add("ratio", new ActionError(
												"Error.message.Ratiorevsplit"));
									}
								}
							}
							if (flg == 0) {
								corp = request.getParameter("corpid");
								String qry = ConnectInit.queries
										.getProperty("get_corporate_list_stock");
								int cid = 0;
								try {
									ResultSet rs = ListTypeClass1
											.resultCorporate(con, qry);
									cid = ListTypeClass1.check_corp_name1(rs,
											corp);
									if (rs != null)
										rs.close();
									String query = ConnectInit.queries
											.getProperty("check_action");
									stock = request.getParameter("s_stock");
									ResultSet rs1 = ListTypeClass1.getresp_cad(
											con, query, Integer.toString(cid),
											stock, apply_date);
									if (rs1.next())
										errors.add("chk", new ActionError(
												"Error.message.ActionApplied"));
								} catch (Exception e) {
									Logging.error("error=" + e.getMessage());
								}
							}
						}

					}
					if (ncorp.equals("Commit")) {
						String butt = getButton();
						String dt = UpdateCorp.accept_date();
						int ap_chk = ComputeIndexForm.CompareDate(apply_date,
								dt);
						int flg_val = 0;
						if (ap_chk == 0) {
							if (butt == null) {
								if (hash.isEmpty() == false) {
									String qry = ConnectInit.queries
											.getProperty("get_latest_index_value_index_wise");
									try {
										for (Enumeration enumm = hash.keys(); enumm
												.hasMoreElements();) {
											String id = (String) enumm
													.nextElement();
											ResultSet rs = ListTypeClass1
													.getResult12(con, qry, id);
											if (rs != null && rs.next()) {
												String ind_val = rs
														.getString("index_closing_value");
												if (ind_val == null) {
													errors.add(
															"chk",
															new ActionError(
																	"Error.message.AffectIndex"));
													flg_val = 1;
													break;
												} else {
													if (ind_val.equals("0")) {
														errors.add(
																"chk",
																new ActionError(
																		"Error.message.AffectIndex"));
														flg_val = 1;
														break;
													}
												}
											}
										}
									} catch (Exception e) {
										Logging.error("Error=" + e.getMessage());
									}
								}// hash
							}
						}// dates are equal
						else {
							if (hash.isEmpty() == false) {
								String qry = ConnectInit.queries
										.getProperty("get_undo_index_close");
								try {
									for (Enumeration enumm = hash.keys(); enumm
											.hasMoreElements();) {
										String id = (String) enumm
												.nextElement();
										ResultSet rs = ListTypeClass1
												.getResult_apply(con, qry, id,
														apply_date);
										boolean chk_rs = rs.next();
										if (chk_rs == true) {
											String ind_val = rs
													.getString("index_closing_value");
											if (ind_val == null) {
												errors.add(
														"chk",
														new ActionError(
																"Error.message.AffectIndex"));
												flg_val = 1;
												break;
											} else {
												if (ind_val.equals("0")) {
													errors.add(
															"chk",
															new ActionError(
																	"Error.message.AffectIndex"));
													flg_val = 1;
													break;
												}
											}
										} else {
											errors.add(
													"chk",
													new ActionError(
															"Error.message.AffectIndex"));
											flg_val = 1;
											break;
										}
									}
								} catch (Exception e) {
									Logging.error("Error=" + e.getMessage());
								}
							}// hash
						}
						if (flg_val == 0) {
							if (butt == null) {
								String corp = request.getParameter("corpid");
								String qry = ConnectInit.queries
										.getProperty("get_corporate_list_stock");
								int cid = 0;
								try {
									ResultSet rs = ListTypeClass1
											.resultCorporate(con, qry);
									cid = ListTypeClass1.check_corp_name1(rs,
											corp);
									rs.close();

									String query = ConnectInit.queries
											.getProperty("check_action");
									String apply = apply_date;// UpdateCorp.accept_date();
									String stock = request
											.getParameter("s_stock");
									ResultSet rs1 = ListTypeClass1.getresp_cad(
											con, query, Integer.toString(cid),
											stock, apply);
									if (rs1.next()) {
										errors.add("chk", new ActionError(
												"Error.message.ActionApplied"));
									}
								} catch (Exception e) {
									Logging.error("error=" + e.getMessage());
								}
							} else {
								Hashtable hash1 = getHash1();
								for (Enumeration enumm = hash1.keys(); enumm
										.hasMoreElements();) {
									String id = (String) enumm.nextElement();
									setC_Cad(id);
									try {
										String query = ConnectInit.queries
												.getProperty("select_rep_cad");
										ResultSet rs = ListTypeClass1
												.getAffected(con, query,
														getC_Cad());
										rs.next();
										String status = rs.getString("status");
										if (status.equals("n")) {
											errors.add(
													"chk",
													new ActionError(
															"Error.message.ActionApplied"));
										}
									} catch (Exception e) {
										Logging.error("Error=" + e.getMessage());
									}
								}
							}
						}// if flg_val
					}
				}
				String ebutton = request.getParameter("ap_co_button3");
				if (ebutton != null) {
					if (ebutton.equals("Update")) {
						if (c_Cad == null)
							errors.add("chk", new ActionError(
									"Error.message.Radio"));
					}
					if (ebutton.equals("YDelete")) {
						if (c_Cad == null)
							errors.add("chk", new ActionError(
									"Error.message.Radio"));
					}

				}
				String event_but = request.getParameter("event_button");
				if (event_but != null) {
					if (event_but.equals("Amt")) {
						String corp = request.getParameter("corpid");
						String amt = request.getParameter("amt");
						if (corp != null) {
							if ((corp.equals("Select Action"))
									| (corp.equals(""))) {
								if (amt.equals("")) {
									percent = "";
									errors.add("chk", new ActionError(
											"Error.message.Amount"));
								}
							} else {
								if (amt.equals(""))
									percent = "";
							}
						}
					}
					if (event_but.equals("Submit")) {
						String amt = request.getParameter("amt");
						String corp = request.getParameter("corpid");
						String r1 = request.getParameter("ratio1");
						String r2 = request.getParameter("ratio2");
						String share = request.getParameter("share");
						String ap_date = request.getParameter("apply_date");
						if (corp != null) {
							if ((corp.equals("Select Action"))
									| corp.equals(""))
								errors.add("chk", new ActionError(
										"Error.message.CorpAction"));
						}
						if ((corp.equals("stockdividend/bonus"))
								| (corp.equals("split"))
								| (corp.equals("reversesplit"))
								| (corp.equals("rightsoffering"))
								| (corp.equals("warrantconversion")))
							if ((r1.equals("")) | (r2.equals("")))
								errors.add("r1", new ActionError(
										"Error.message.Ratio"));
						if ((corp.equals("cashdividend"))
								| (corp.equals("rightsoffering"))
								| (corp.equals("sharesbuyback"))
								| (corp.equals("spin-off"))
								| (corp.equals("specialdividend"))) {
							if (amt.equals("")) {
								percent = "";
								errors.add("chk", new ActionError(
										"Error.message.Amount"));
							}
						}
						if (corp.equals("sharesbuyback")
								| (corp.equals("warrantconversion"))
								| (corp.equals("shareissuance"))
								| (corp.equals("capitalreduce"))
								| (corp.equals("adrissue")))
							if (share.equals(""))
								errors.add("share", new ActionError(
										"Error.message.Share"));
						if (ap_date != null)
							if (ap_date.equals(""))
								errors.add("chk", new ActionError(
										"Error.message.ApplyDate"));
						// check stock status
						int merge_chk = check_stkstatus(con, connect,
								getStid(), ap_date);
						if (merge_chk > 0 || merge_chk == 0)
							errors.add("chk", new ActionError(
									"Error.message.HistStkStatus"));
					}
				}
				String radbutton = getInd_comp();
				if (radbutton != null & new_ind_but == null) {
					String dt = UpdateCorp.accept_date(); // get the current
															// date
					String apply = apply_date;
					int chk_dt = ComputeIndexForm.CompareDate(apply_date, dt); // check
																				// for
																				// the
																				// current
																				// date
																				// and
																				// user's
																				// entered
																				// date
					if (chk_dt == 0) {
						if (radbutton.equals("c")) {
							String query = ConnectInit.queries
									.getProperty("select_index_detail");
							// added by sonali to get the closing value of
							// affected indices
							Collection affectedIndexids = getAffstkCollection();
							Iterator it = affectedIndexids.iterator();
							LabelValueBean indexId = (LabelValueBean) it.next();
							String noClosing = " ";
							int closingFlag = 0;
							int i = 0;
							while (it.hasNext()) {
								indexId = (LabelValueBean) it.next();
								String indexVal = (String) indexId.getValue();
								ResultSet rs = ListTypeClass1.getResult12(con,
										query, indexVal);
								if (rs != null && rs.next()) {
									String close = rs
											.getString("index_closing_value");
									if (close == null) {
										ind_comp = null;

										affIndexNoClosing[i] = indexVal;
										i++;
										closingFlag = 1;
										// errors.add("chk",new
										// ActionError("Error.message.IndexCloseParent"));

									} else {
										if (close.equals("0")) {
											ind_comp = null;

											affIndexNoClosing[i] = indexVal;
											i++;
											closingFlag = 1;
											// errors.add("chk",new
											// ActionError("Error.message.IndexCloseParent"+noClosing));
										}
									}
								} else {
									ind_comp = null;
									affIndexNoClosing[i] = indexVal;
									i++;
									// errors.add("chk",new
									// ActionError("Error.message.IndexCloseParent"+noClosing));
									closingFlag = 1;
								}
								if (hash_affind.isEmpty() == false) {
									if (isCheck() == false) {
										ind_comp = null;
										affIndexNoClosing[i] = indexVal;
										i++;
										closingFlag = 1;
										// errors.add("chk",new
										// ActionError("Error.message.IndexCloseChild"));
									}
								}
							}
							if (closingFlag == 1) {

								setAffIndexNoClosing(affIndexNoClosing);
								errors.add("chk", new ActionError(
										"Error.message.IndexCloseParent"));

							}
						}
					}
					if (radbutton.equals("i")) {
						String query = ConnectInit.queries
								.getProperty("Historic_compo_parent");
						ResultSet rs = ListTypeClass1.getResult_apply(con,
								query, i_index, apply_date);
						if (!(rs.next())) {
							ind_comp = null;
							errors.add("chk", new ActionError(
									"Error.message.HistindComp"));
						}
					}
				}
				String detail_button = request.getParameter("cdetail_button");
				if (detail_button != null) {
					if (detail_button.equals("Ok")) {
						if (chk_but == null)
							errors.add("chk", new ActionError(
									"Error.message.Radio"));

					}
					if (detail_button.equals("Radio")) {
						if (nature != null) {
							if (nature.equals("o")) {
								String query = ConnectInit.queries
										.getProperty("select_rep_cad");
								ResultSet rs = ListTypeClass1.getResult1(con,
										query, chk_but);
								rs.next();
								String amt = rs.getString("amount");
								String shr = rs.getString("values");
								rs.close();
								String corp = request.getParameter("corpid");
								String stock = request.getParameter("s_stock");
								String qry = ConnectInit.queries
										.getProperty("get_latest_stock_closing_value_tis");
								PreparedStatement stmt = con
										.prepareStatement(qry);
								stmt.setString(1, stock);
								stmt.setString(2, stock);
								rs = stmt.executeQuery();
								rs.next();
								// float tis=rs.getFloat("tis");
								float close = rs.getFloat("adjusted_price");
								if (close == 0.0)
									close = rs.getFloat("stock_closing_value");
								if ((corp.equals("sharesbuyback"))
										| (corp.equals("capitalreduce"))) {
									float shrval = Float.parseFloat(shr);
									query = ConnectInit.queries
											.getProperty("detail_stock_master");
									ResultSet rs1 = ListTypeClass1.getAffected(
											con, query, s_stock);
									rs1.next();
									float tis = rs1.getFloat("tis");
									if (shrval >= tis) {
										errors.add("share", new ActionError(
												"Error.message.Greatershare"));
									}
								}
								if ((corp.equals("cashdividend"))
										| (corp.equals("rightsoffering"))
										| (corp.equals("sharesbuyback"))
										| (corp.equals("spin-off"))
										| (corp.equals("specialdividend"))) {
									float amount = Float.parseFloat(amt);
									if (amount > close) {
										errors.add("share", new ActionError(
												"Error.message.Greateramount"));
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				Logging.error(e.getMessage());
			}

		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				// if (con != null)
				// con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		return errors;
	}

	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return Returns the chk_but.
	 */
	public String getChk_but() {
		return chk_but;
	}

	/**
	 * @param chk_but
	 *            The chk_but to set.
	 */
	public void setChk_but(String chk_but) {
		this.chk_but = chk_but;
	}

	/**
	 * @return Returns the r_type.
	 */
	public String getR_type() {
		return r_type;
	}

	/**
	 * @param r_type
	 *            The r_type to set.
	 */
	public void setR_type(String r_type) {
		this.r_type = r_type;
	}

	/**
	 * @return Returns the c_Cad.
	 */
	public String getC_Cad() {
		return c_Cad;
	}

	/**
	 * @param cad
	 *            The c_Cad to set.
	 */
	public void setC_Cad(String cad) {
		c_Cad = cad;
	}

	/**
	 * @return Returns the amt_per.
	 */
	public String getAmt_per() {
		return amt_per;
	}

	/**
	 * @param amt_per
	 *            The amt_per to set.
	 */
	public void setAmt_per(String amt_per) {
		this.amt_per = amt_per;
	}

	/**
	 * @return Returns the upflg.
	 */
	public String getUpflg() {
		return upflg;
	}

	/**
	 * @param upflg
	 *            The upflg to set.
	 */
	public void setUpflg(String upflg) {
		this.upflg = upflg;
	}

	/**
	 * @return Returns the ncorp_button.
	 */
	public String getNcorp_button() {
		return ncorp_button;
	}

	/**
	 * @param ncorp_button
	 *            The ncorp_button to set.
	 */
	public void setNcorp_button(String ncorp_button) {
		this.ncorp_button = ncorp_button;
	}

	/**
	 * @return Returns the compo_button.
	 */
	public String getCompo_button() {
		return compo_button;
	}

	/**
	 * @param compo_button
	 *            The compo_button to set.
	 */
	public void setCompo_button(String compo_button) {
		this.compo_button = compo_button;
	}

	/**
	 * @return Returns the ind_comp.
	 */
	public String getInd_comp() {
		return ind_comp;
	}

	/**
	 * @param ind_comp
	 *            The ind_comp to set.
	 */
	public void setInd_comp(String ind_comp) {
		this.ind_comp = ind_comp;
	}

	/**
	 * @return Returns the hash_stock_error.
	 */
	public Hashtable getHash_stock_error() {

		return hash_stock_error;
	}

	/**
	 * @param hash_stock_error
	 *            The hash_stock_error to set.
	 */
	public void setHash_stock_error(Hashtable hash_stock_error) {
		this.hash_stock_error = hash_stock_error;
	}

	/**
	 * @return Returns the amount.
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            The amount to set.
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return Returns the exc_stk.
	 */
	public String getExc_stk() {
		return exc_stk;
	}

	/**
	 * @param exc_stk
	 *            The exc_stk to set.
	 */
	public void setExc_stk(String exc_stk) {
		this.exc_stk = exc_stk;
	}

	/**
	 * @return Returns the excCollection.
	 */
	public Collection getExcCollection() {
		Vector roles = new Vector();
		Connection con = null;
		Connect c = ConnectInit.getConnect();
		try {
			if (con == null) {
				con = c.getdbConnection();
			}

			try {
				String query = ConnectInit.queries
						.getProperty("stock_exchange_online_list");
				if (exch != null)
					if ((exch.equals("")) | (exch.equals("Select Exchange")))
						exch = null;
				if (exch == null) {
					String qry = ConnectInit.queries
							.getProperty("select_system_config");
					ResultSet rs1 = ListTypeClass1.resultCorporate(con, qry);
					rs1.next();
					exc = rs1.getString("stock_ex_id");
					ResultSet rs = ListTypeClass1.resultCorporate(con, query);
					rs.next();
					while (rs.next()) {
						String count = rs.getString("stock_ex_id");
						if (exc.equals(rs.getString("stock_ex_id"))) {
							setExch(rs.getString("stock_ex_id"));
							roles.add(new LabelValueBean(rs.getString(2), count));
						} else {
							roles.add(new LabelValueBean(rs.getString(2), count));
						}
					}
				} else {
					ResultSet rs = ListTypeClass1.resultCorporate(con, query);
					// rs.next();
					while (rs.next()) {
						String count = rs.getString("stock_ex_id");
						if (exch.equals(rs.getString("stock_ex_id"))) {
							setExch(exch);
							roles.add(new LabelValueBean(rs.getString(2), count));
						} else {
							roles.add(new LabelValueBean(rs.getString(2), count));
						}
					}
				}
			} catch (Exception e) {
				Logging.error("error==" + e.getMessage());
			}
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		excCollection = roles;
		return excCollection;
	}

	/**
	 * @param excCollection
	 *            The excCollection to set.
	 */
	public void setExcCollection(Collection excCollection) {
		this.excCollection = excCollection;

	}

	/**
	 * @return Returns the stkCollection.
	 */
	public Collection getStkCollection() {
		Vector roles = new Vector();

		Connection con = null;
		Connect c = ConnectInit.getConnect();
		// Change by Manoj Adekar for Dynamic connection using getdbConnection()
		// instead of getConnection()
		try {
			if (con == null) {
				con = c.getdbConnection();
			}
			Logging.debug("Exch in stock colle==" + exch);
			Logging.debug("index in collect==" + i_index);
			Logging.debug("stock in coll===" + stkid);

			// for inactive stock display
			ArrayList a2 = getStk_status();
			a2.clear();
			setStk_status(a2);
			a2 = getStk_status();
			int position = 1;
			if (exch == null) {
				try {
					Logging.debug("1");
					ResultSet rs = null;
					if (i_index == null) {
						Logging.debug("2");
						String query = ConnectInit.queries
								.getProperty("select_from_stock_master");
						rs = ListTypeClass1.resultCorporate(con, query);
					} else {
						String query = ConnectInit.queries
								.getProperty("select_index_wise_stock_id");
						rs = ListTypeClass1.getAffected(con, query, i_index);
					}
					roles.add(new LabelValueBean("Select Stock", ""));
					if (stkid == null) {
						Logging.debug("3");
						while (rs.next()) {
							String count = rs.getString("stock_id");
							Logging.debug("*** stockid = " + count);
							String active = rs.getString("is_active");
							if (active.equals("d"))
								a2.add(Integer.toString(position));
							roles.add(new LabelValueBean(rs
									.getString("stock_name"), count));
							position++;
						}
						Logging.debug("4");
					} else {
						while (rs.next()) {
							String count = rs.getString("stock_id");
							String active = rs.getString("is_active");
							if (active.equals("d"))
								a2.add(Integer.toString(position));
							if (stkid.equals(rs.getString("stock_id"))) {
								setStkid(rs.getString("stock_id"));
								roles.add(new LabelValueBean(rs
										.getString("stock_name"), count));
							} else
								roles.add(new LabelValueBean(rs
										.getString("stock_name"), count));
							position++;
						}
					}
					Logging.debug("out");
				} catch (Exception e) {
					Logging.error("Error in stock collection=" + e.getMessage());
				}
			} else {
				try {
					position = 1;
					Logging.debug("1n exch not null");
					String query = ConnectInit.queries
							.getProperty("stock_list");
					ResultSet rs = ListTypeClass1.getAffected(con, query, exch);
					roles.add(new LabelValueBean("Select Stock", ""));
					if (stkid == null) {
						while (rs.next()) {
							String count = rs.getString("stock_id");
							String active = rs.getString("is_active");
							if (active.equals("d"))
								a2.add(Integer.toString(position));
							roles.add(new LabelValueBean(rs
									.getString("stock_name"), count));
							position++;
						}
					} else {
						while (rs.next()) {
							String count = rs.getString("stock_id");
							String active = rs.getString("is_active");
							if (active.equals("d"))
								a2.add(Integer.toString(position));
							if (stkid.equals(rs.getString("stock_id"))) {
								setStkid(rs.getString("stock_id"));
								roles.add(new LabelValueBean(rs
										.getString("stock_name"), count));
							} else
								roles.add(new LabelValueBean(rs
										.getString("stock_name"), count));
							position++;
						}
					}
				} catch (Exception e) {
					Logging.error("Error in stock collection=" + e.getMessage());
				}
			}

			setStk_status(a2);
			stkCollection = roles;
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		return stkCollection;
	}

	/**
	 * @param stkCollection
	 *            The stkCollection to set.
	 */
	public void setStkCollection(Collection stkCollection) {
		this.stkCollection = stkCollection;
	}

	/**
	 * @return Returns the indCollection.
	 */
	public Collection getIndCollection() {
		// AcessControl asc=new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		String SelIndex = asc.getLangValues("indexUpdate.selectIndex");
		Logging.debug(" Inside getIndCollection(): select Index =" + SelIndex);
		Vector roles = new Vector();
		try {
			Connect c = ConnectInit.getConnect();
			con = c.getdbConnection();
			if (i_index != null) {
				if ((i_index.equals("")) || (i_index.equals(SelIndex)))
					i_index = null;
			}
			String query = ConnectInit.queries
					.getProperty("index_list_without_child");
			pst = con.prepareStatement(query);
			pst.setString(1, userid1);
			rs = pst.executeQuery();
			roles.add(new LabelValueBean("Not Selected", "0"));
			// if(i_index==null)
			// {
			while (rs.next()) {
				String count = rs.getString(1);
				roles.add(new LabelValueBean(rs.getString(2), count));
			}

			// ResultSet rbs =
			// Icr.benchindecolection(con,"index_list_without_child_bench");
			// while (rbs.next()) {
			// String count=rbs.getString(1);
			// roles.add(new LabelValueBean(rbs.getString(2),count));
			// }
			// }
			// //comented code till checking is not done

			// String query =c.queries.getProperty("select_parent_index");
			// ResultSet rs=ListTypeClass1.resultCorporate(con,query);
			// roles.add(new LabelValueBean(SelIndex,""));
			/*
			 * if(i_index==null) { while(rs.next()) { String
			 * count=rs.getString("index_id"); roles.add(new
			 * LabelValueBean(rs.getString("index_name"),count)); } } else {
			 * while(rs.next()) { String count=rs.getString("index_id");
			 * if(i_index.equals(count)) { setI_index(i_index); roles.add(new
			 * LabelValueBean(rs.getString("index_name"),count)); } else
			 * roles.add(new LabelValueBean(rs.getString("index_name"),count));
			 * } }
			 */
		} catch (Exception e) {
			System.out.println("Exception *** " + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
				if (pst != null)
					pst.close();
				if (rs != null)
					rs.close();
			} catch (Exception ee) {
				Logging.debug("Error : Unable to close connection "
						+ ee.getMessage());
			}
		}
		indCollection = roles;
		return indCollection;
	}

	/**
	 * @param indCollection
	 *            The indCollection to set.
	 */
	public void setIndCollection(Collection indCollection) {
		this.indCollection = indCollection;
	}

	/**
	 * @return Returns the n_share.
	 */
	public String getN_share() {
		return n_share;
	}

	/**
	 * @param n_share
	 *            The n_share to set.
	 */
	public void setN_share(String n_share) {
		this.n_share = n_share;
	}

	/**
	 * @return Returns the s_exc.
	 */

	/**
	 * @return Returns the s_exc.
	 */
	public String getS_exc() {
		return s_exc;
	}

	/**
	 * @param s_exc
	 *            The s_exc to set.
	 */
	public void setS_exc(String s_exc) {
		this.s_exc = s_exc;
	}

	/**
	 * @return Returns the time.
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time
	 *            The time to set.
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return Returns the exc1Collection.
	 */
	public Collection getExc1Collection() {
		Vector roles = new Vector();
		try {
			Connect c = ConnectInit.getConnect();
			con = c.getdbConnection();
			String query = ConnectInit.queries
					.getProperty("stock_exchange_online_list");
			AcessControl asc = ConnectInit.getAcessControl();
			// AcessControl asc=new AcessControl();
			String SelExch = asc.getLangValues("Masters.SelectExch");
			Logging.debug(" Inside getS_stkCollection: select Stock ="
					+ SelExch);

			if (exc != null)
				if ((exc.equals("")) | (exc.equals(SelExch)))
					exc = null;
			if (exc == null) {
				roles.add(new LabelValueBean(SelExch, ""));
				String qry = ConnectInit.queries
						.getProperty("select_system_config");
				ResultSet rs1 = ListTypeClass1.resultCorporate(con, qry);
				rs1.next();
				String exc_val = rs1.getString("stock_ex_id");
				rs1.close();
				ResultSet rs = ListTypeClass1.resultCorporate(con, query);
				// rs.next();
				while (rs.next()) {
					String count = rs.getString("stock_ex_id");
					if (exc_val.equals(count)) {
						setExc(rs.getString("stock_ex_id"));
						roles.add(new LabelValueBean(rs.getString(2), count));
					} else {
						roles.add(new LabelValueBean(rs.getString(2), count));
					}
				}
			} else {
				roles.add(new LabelValueBean(SelExch, ""));
				ResultSet rs = ListTypeClass1.resultCorporate(con, query);
				// rs.next();
				while (rs.next()) {
					String count = rs.getString("stock_ex_id");
					if (exc.equals(rs.getString("stock_ex_id"))) {
						setExc(exc);
						roles.add(new LabelValueBean(rs.getString(2), count));
					} else {
						roles.add(new LabelValueBean(rs.getString(2), count));
					}
				}
			}

		} catch (Exception e) {
			Logging.error("error=" + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
				if (rs1 != null)
					rs1.close();
				if (rs != null)
					rs.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		exc1Collection = roles;
		return exc1Collection;
	}

	/**
	 * @param exc1Collection
	 *            The exc1Collection to set.
	 */
	public void setExc1Collection(Collection exc1Collection) {
		this.exc1Collection = exc1Collection;
	}

	/**
	 * @return Returns the s_stock.
	 */
	public String getS_stock() {
		return s_stock;
	}

	/**
	 * @param s_stock
	 *            The s_stock to set.
	 */
	public void setS_stock(String s_stock) {
		this.s_stock = s_stock;
	}

	/**
	 * @return Returns the s_stkCollection.
	 */
	public Collection getS_stkCollection() {
		Vector roles = new Vector();

		Connection con = null;
		Connect c = ConnectInit.getConnect();
		// Change by Manoj Adekar for Dynamic connection using getdbConnection()
		// instead of getConnection()
		try {
			if (con == null) {
				con = c.getdbConnection();
			}

			// for inactive stock display
			try {
				ArrayList a2 = getStk_status();
				a2.clear();
				setStk_status(a2);
				a2 = getStk_status();

				int position = 1;
				AcessControl asc = ConnectInit.getAcessControl();
				// AcessControl asc=new AcessControl();
				String SelStock = asc
						.getLangValues("StockDetailFromDate.Stock");
				String SelExch = asc.getLangValues("Masters.SelectExch");
				Logging.debug(" Inside getS_stkCollection: select Stock ="
						+ SelStock);

				if (exc != null)
					if ((exc.equals("")) | (exc.equals(SelExch)))
						exc = null;
				if (s_stock != null)
					if ((s_stock.equals("")) | (s_stock.equals(SelStock)))
						s_stock = null;

				if (corpid == null)
					corpid = "";
				roles.add(new LabelValueBean(SelStock, ""));
				if ((!(corpid.equals("addstock")))
						& (!(corpid.equals("deletestock")))
						& (!(corpid.equals("changeiwf")))) {
					String query = ConnectInit.queries
							.getProperty("stock_list");
					ResultSet rs = ListTypeClass1.getAffected(con, query, exc);
					if (s_stock == null) {
						while (rs.next()) {
							String count = rs.getString("stock_id");
							String active = rs.getString("is_active");
							if (active.equals("d"))
								a2.add(Integer.toString(position));
							roles.add(new LabelValueBean(rs
									.getString("stock_name"), count));
							position++;
						}
					} else {
						while (rs.next()) {
							String count = rs.getString("stock_id");
							String active = rs.getString("is_active");
							if (active.equals("d"))
								a2.add(Integer.toString(position));
							if (s_stock.equals(count)) {
								setS_stock(rs.getString("stock_id"));
								roles.add(new LabelValueBean(rs
										.getString("stock_name"), count));
							} else
								roles.add(new LabelValueBean(rs
										.getString("stock_name"), count));
							position++;
						}
					}
				} else {
					ResultSet rs = null;
					if (corpid.equals("addstock")) {
						String query = ConnectInit.queries
								.getProperty("select_resp_stock");
						rs = ListTypeClass1.getResult_corp(con, query, i_index,
								exc);
					}
					if ((corpid.equals("deletestock"))
							| (corpid.equals("changeiwf"))) {
						String query = ConnectInit.queries
								.getProperty("select_stock_index");
						rs = ListTypeClass1.getResult1(con, query, i_index);
					}
					if (s_stock == null) {
						while (rs.next()) {
							String count = rs.getString("stock_id");
							String active = rs.getString("is_active");
							if (active.equals("d"))
								a2.add(Integer.toString(position));
							roles.add(new LabelValueBean(rs
									.getString("stock_name"), count));
							position++;
						}
					} else {
						while (rs.next()) {
							String count = rs.getString("stock_id");
							String active = rs.getString("is_active");
							if (active.equals("d"))
								a2.add(Integer.toString(position));
							if (s_stock.equals(count)) {
								setS_stock(rs.getString("stock_id"));
								roles.add(new LabelValueBean(rs
										.getString("stock_name"), count));
							} else
								roles.add(new LabelValueBean(rs
										.getString("stock_name"), count));
							position++;
						}
					}
				}
				if (corpid.equals(""))
					corpid = null;
				setStk_status(a2);
				Logging.debug("object in collection===" + getStk_status());

			} catch (Exception e) {
				Logging.error("error=" + e.getMessage());
			}
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}

		s_stkCollection = roles;
		return s_stkCollection;
	}

	/**
	 * @param collection
	 *            The s_stkCollection to set.
	 */
	public void setS_stkCollection(Collection s_stkCollection) {
		this.s_stkCollection = s_stkCollection;
	}

	/**
	 * @return Returns the corpCollection.
	 */
	public Collection getCorpCollection() {
		Vector roles = new Vector();

		Connection con = null;
		Connect c = ConnectInit.getConnect();
		// Change by Manoj Adekar for Dynamic connection using getdbConnection()
		// instead of getConnection()
		try {
			if (con == null) {
				con = c.getdbConnection();
			}

			try {
				// AcessControl asc=new AcessControl();
				AcessControl asc = ConnectInit.getAcessControl();
				String SelAction = asc
						.getLangValues("indCompliance.Select_Action");
				Logging.debug(" Inside getIndcorpCollection: select Action ="
						+ SelAction);

				String query = ConnectInit.queries
						.getProperty("get_corporate_list_stock");
				roles.add(new LabelValueBean(SelAction, ""));
				if (corpid != null)
					if ((corpid.equals(SelAction)) | (corpid.equals("")))
						corpid = null;
				ResultSet rs = ListTypeClass1.resultCorporate(con, query);
				if (corpid == null) {
					while (rs.next()) {
						String corp_val = null;
						String count = rs.getString("cam_shortname");
						StringTokenizer st = new StringTokenizer(count, " ");
						int val = st.countTokens();
						if (val > 1) {
							String div[] = ActionCorp.token2(count);
							corp_val = (div[0] + div[1]).toLowerCase();
							corp_val = corp_val.trim();
						} else {
							corp_val = count.toLowerCase();
							corp_val = corp_val.trim();
						}
						roles.add(new LabelValueBean(rs.getString("cam_name"),
								corp_val));
					}
				} else {
					while (rs.next()) {
						String corp_val = null;
						String count = rs.getString("cam_shortname");
						StringTokenizer st = new StringTokenizer(count, " ");
						int val = st.countTokens();
						if (val > 1) {
							String div[] = ActionCorp.token2(count);
							corp_val = (div[0] + div[1]).toLowerCase();
							corp_val = corp_val.trim();
						} else {
							corp_val = count.toLowerCase();
							corp_val = corp_val.trim();
						}
						if (corpid.equals(corp_val)) {
							setCorpid(corp_val);
							roles.add(new LabelValueBean(rs
									.getString("cam_name"), corp_val));
						} else
							roles.add(new LabelValueBean(rs
									.getString("cam_name"), corp_val));
					}
				}
			} catch (Exception e) {
				Logging.error("Error=" + e.getMessage());
			}
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}

		corpCollection = roles;
		return corpCollection;
	}

	/**
	 * @param corpCollection
	 *            The corpCollection to set.
	 */
	public void setCorpCollection(Collection corpCollection) {
		this.corpCollection = corpCollection;
	}

	/**
	 * @return Returns the s_ratio1.
	 */
	public String getS_ratio1() {
		return s_ratio1;
	}

	/**
	 * @param s_ratio1
	 *            The s_ratio1 to set.
	 */
	public void setS_ratio1(String s_ratio1) {
		this.s_ratio1 = s_ratio1;
	}

	/**
	 * @return Returns the s_ratio2.
	 */
	public String getS_ratio2() {
		return s_ratio2;
	}

	/**
	 * @param s_ratio2
	 *            The s_ratio2 to set.
	 */
	public void setS_ratio2(String s_ratio2) {
		this.s_ratio2 = s_ratio2;
	}

	/**
	 * @return Returns the new_corp_but.
	 */
	public String getNew_corp_but() {
		return new_corp_but;
	}

	/**
	 * @param new_corp_but
	 *            The new_corp_but to set.
	 */
	public void setNew_corp_but(String new_corp_but) {
		this.new_corp_but = new_corp_but;
	}

	/**
	 * @return Returns the stkmulCollection.
	 */
	public Collection getStkmulCollection() {
		Vector roles = new Vector();

		Connection con = null;
		Connect c = ConnectInit.getConnect();
		// Change by Manoj Adekar for Dynamic connection using getdbConnection()
		// instead of getConnection()
		try {
			if (con == null) {
				con = c.getdbConnection();
			}

			try {
				ResultSet rs = null;

				// for inactive stock display
				ArrayList a2 = getStk_status();
				a2.clear();
				setStk_status(a2);
				a2 = getStk_status();
				int position = 1;

				String butt = getButton();
				if (butt == null) {
					if (corpid.equals("addstock")) {
						String query = ConnectInit.queries
								.getProperty("select_resp_stock");
						rs = ListTypeClass1.getResult_corp(con, query, i_index,
								exc);
					}
					if ((corpid.equals("deletestock"))
							| (corpid.equals("changeiwf"))) {
						String query = ConnectInit.queries
								.getProperty("select_stock_index");
						rs = ListTypeClass1.getResult1(con, query, i_index);
					}
				} else {
					if (corpid.equals("deletestock")) {
						String query = ConnectInit.queries
								.getProperty("select_resp_stock");
						rs = ListTypeClass1.getResult_corp(con, query, i_index,
								exc);
					}
					if ((corpid.equals("addstock"))
							| (corpid.equals("changeiwf"))) {
						String query = ConnectInit.queries
								.getProperty("select_stock_index");
						rs = ListTypeClass1.getResult1(con, query, i_index);
					}
				}
				// AcessControl asc=new AcessControl();
				AcessControl asc = ConnectInit.getAcessControl();
				String SelStock = asc
						.getLangValues("StockDetailFromDate.Stock");
				Logging.debug(" Inside getS_stkCollection: select Stock ="
						+ SelStock);

				roles.add(new LabelValueBean(SelStock, ""));
				if (stock == null) {
					// check for null resultset added by sunil 04-AUG-06
					while (rs != null && rs.next()) {
						String count = rs.getString("stock_id");
						String active = rs.getString("is_active");
						if (active.equals("d"))
							a2.add(Integer.toString(position));
						roles.add(new LabelValueBean(
								rs.getString("stock_name"), count));
						position++;
					}
				} else {
					String[] arr = null;
					int cnt = 0;
					while (rs.next()) {
						String count = rs.getString("stock_id");
						String active = rs.getString("is_active");
						if (active.equals("d"))
							a2.add(Integer.toString(position));
						if (stock.equals(count)) {
							arr[cnt] = rs.getString("stock_id");
							setStock(arr);
							cnt++;
							roles.add(new LabelValueBean(rs
									.getString("stock_name"), count));
						} else
							roles.add(new LabelValueBean(rs
									.getString("stock_name"), count));
						position++;
					}
				}
				setStk_status(a2);
			} catch (Exception e) {
				Logging.error("Error=" + e.getMessage());
			}
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		stkmulCollection = roles;
		return stkmulCollection;
	}

	/**
	 * @param stkmulCollection
	 *            The stkmulCollection to set.
	 */
	public void setStkmulCollection(Collection stkmulCollection) {
		this.stkmulCollection = stkmulCollection;
	}

	/**
	 * @return Returns the s_affectedIndex.
	 */
	public String getS_affectedIndex() {
		return s_affectedIndex;
	}

	/**
	 * @param index
	 *            The s_affectedIndex to set.
	 */
	public void setS_affectedIndex(String index) {
		s_affectedIndex = index;
	}

	/**
	 * @return Returns the affstkCollection.
	 */
	public Collection getAffstkCollection() {
		Vector roles = new Vector();
		try {
			Connect c = ConnectInit.getConnect();
			int chk_dt = ComputeIndexForm.CompareDate(apply_date,
					UpdateCorp.accept_date()); // check for the current date and
												// user's
												// entered date
			if (chk_dt == 0)
				// Change by Manoj Adekar for Dynamic connection using
				// getdbConnection() instead of getConnection()
				con = c.getdbConnection();
			else
				con = c.getConnectionForHistTransaction();
			ResultSet rs = null;
			hash = getHash();
			hash.clear();
			AcessControl asc = ConnectInit.getAcessControl();
			// AcessControl asc=new AcessControl();
			String AffectedInd = asc.getLangValues("Masters.AffectedIndex");
			Logging.debug(" Inside getS_stkCollection: select Stock ="
					+ AffectedInd);

			String qry = ConnectInit.queries
					.getProperty("affected_index_by_ca");
			rs = ListTypeClass1.getResult1(con, qry, s_stock);
			roles.add(new LabelValueBean(AffectedInd, ""));
			if (s_affectedIndex != null) {
				if ((s_affectedIndex.equals(""))
						| (s_affectedIndex.equals(AffectedInd)))
					s_affectedIndex = null;
			}
			if (s_affectedIndex == null) {
				while (rs != null && rs.next()) {
					String count = rs.getString("index_id");
					roles.add(new LabelValueBean(rs.getString("index_name"),
							count));

				}
			} else {
				while (rs != null && rs.next()) {
					String count = rs.getString("index_id");
					if (count.equals(affect))
						setS_affectedIndex(count);
					roles.add(new LabelValueBean(rs.getString("index_name"),
							count));

				}
			}
			if (rs != null)
				rs.beforeFirst();
			while (rs != null && rs.next()) {
				hash.put(new Integer(rs.getString("index_id")).toString(),
						new String(rs.getString("index_name")));
			}
			setHash(hash);
		} catch (Exception e) {
			Logging.error("Error=" + e.getMessage());
		}
		affstkCollection = roles;
		return affstkCollection;
	}

	/**
	 * @param affstkCollection
	 *            The affstkCollection to set.
	 */
	public void setAffstkCollection(Collection affstkCollection) {
		this.affstkCollection = affstkCollection;
	}

	/**
	 * @return Returns the stocklist.
	 */
	public ArrayList getStocklist() {
		ArrayList Pp = new ArrayList();
		Connection con = null;
		Connect c = ConnectInit.getConnect();
		// Change by Manoj Adekar for Dynamic connection using getdbConnection()
		// instead of getConnection()
		try {
			if (con == null) {
				con = c.getdbConnection();
			}

			try {

				String corp_val = getCorpid();
				String query = ConnectInit.queries
						.getProperty("get_corporate_list_stock");
				ResultSet rs = ListTypeClass1.resultCorporate(con, query);
				int cid = ListTypeClass1.check_corp_name1(rs, corp_val);
				rs.close();

				query = ConnectInit.queries
						.getProperty("select_stock_corpdiary");
				rs = ListTypeClass1.getresp_cad(con, query,
						Integer.toString(cid), s_stock, apply_date);
				while (rs.next()) {
					setId(rs.getString("cad_id"));
					sl = new stocklist(rs.getString("cad_id"),
							rs.getString("cam_name"),
							rs.getString("stock_name"),
							rs.getString("apply_on_date"),
							rs.getString("amount"),
							rs.getString("ratio_for_shares"),
							rs.getString("ratio_shares_offered"),
							rs.getString("percentage"), rs.getString("values"));
					Pp.add(sl);
				}
			} catch (Exception e) {
				Logging.error("Error=" + e.getMessage());
			}

			stocklist = Pp;

		} catch (Exception e) {
			Logging.debug("Error :" + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		return stocklist;
	}

	/**
	 * @param stocklist
	 *            The stocklist to set.
	 */
	public void setStocklist(ArrayList stocklist) {
		this.stocklist = stocklist;
	}

	/**
	 * @return Returns the undolist.
	 */
	public ArrayList getUndolist() {
		ArrayList Pp = new ArrayList();
		Connection con = null;
		Connect c = ConnectInit.getConnect();
		// Change by Manoj Adekar for Dynamic connection using getdbConnection()
		// instead of getConnection()
		try {
			if (con == null) {
				con = c.getdbConnection();
			}

			try {
				// Check no CA applied on this stock,
				// after the selected CAD ID for undo.

				if (r_type.equals("stock event")) {
					String query = null;
					query = ConnectInit.queries.getProperty("select_undo_list");
					ResultSet rs = ListTypeClass1.check_dairy_exist(con, query,
							c_Cad, stid, applied_date, time);
					boolean chk_rs = rs.next();
					if (chk_rs == true)// if applied
					{
						rs.beforeFirst();
						int count = 0;
						while (rs.next()) {
							count++;
							String cid = rs.getString("cad_id");
							String qry = ConnectInit.queries
									.getProperty("cad_stkind_details");
							ResultSet rs1 = ListTypeClass1.getresp_cad(con,
									qry, cid, cid, cid);
							rs1.next();
							sl = new stocklist(Integer.toString(count),
									rs1.getString("cam_name"),
									rs1.getString("stock_ex_name"),
									rs1.getString("stock_name"),
									rs1.getString("index_name"),
									rs1.getString("apply_on_date"),
									rs1.getString("applied_date"),
									rs1.getString("amount"),
									rs1.getString("percentage"),
									rs1.getString("ratio_for_shares"),
									rs1.getString("ratio_shares_offered"),
									rs1.getString("values"),
									rs1.getString("status"));
							Pp.add(sl);
						}
					}// chk_rs true
					if (chk_rs == false) {
						int cad_id = Integer.parseInt(getC_Cad());
						int flag = 0;
						// get all the index belonging to the selected stock.
						query = null;
						query = ConnectInit.queries
								.getProperty("select_index_composition");
						rs = ListTypeClass1.getAffected(con, query, stid);
						boolean chk_stk = rs.next();
						if (chk_stk == true)// if stock belongs to any index
						{
							rs.beforeFirst();
							int count = 0;
							while (rs.next()) {
								String index_id = rs.getString("index_id");
								// check any CA apply on that index
								String qry = null;
								qry = ConnectInit.queries
										.getProperty("check_ind_exist");
								ResultSet rs1 = ListTypeClass1
										.getResult_event2(con, qry, c_Cad,
												index_id, applied_date, time);
								boolean chk_ind = rs1.next();
								if (chk_ind == true)// if applied on index
								{
									rs1.beforeFirst();
									while (rs1.next()) {
										count++;
										String cadid = rs1.getString("cad_id");
										String qry1 = ConnectInit.queries
												.getProperty("cad_stkind_details");
										ResultSet rs2 = ListTypeClass1
												.getresp_cad(con, qry1, cadid,
														cadid, cadid);
										rs2.next();
										sl = new stocklist(
												Integer.toString(count),
												rs2.getString("cam_name"),
												rs2.getString("stock_ex_name"),
												rs2.getString("stock_name"),
												rs2.getString("index_name"),
												rs2.getString("apply_on_date"),
												rs2.getString("applied_date"),
												rs2.getString("amount"),
												rs1.getString("percentage"),
												rs2.getString("ratio_for_shares"),
												rs2.getString("ratio_shares_offered"),
												rs2.getString("values"), rs2
														.getString("status"));
										Pp.add(sl);
										flag = 1;
									}// while rs1
								}// chk_ind true
							}// while rs
							if (flag == 0) {
								String type = getR_type();
								setLeng("1");

								hash.clear();
								hash.put(new String(c_Cad), new String(c_Cad));
								setHash(hash);
								ListTypeClass1.corp_detail(this);
								query = ConnectInit.queries
										.getProperty("stock_details_for_ca");
								rs = ListTypeClass1.getResult12(con, query,
										s_stock);
								try {
									setResult(rs);
								} catch (Exception e) {
									Logging.error("ListTypeClass1:Error in result set "
											+ e.getMessage());
								}
							}
						}// chk_stk true
						if (chk_stk == false) {
							setLeng("1");
							Hashtable hash = getHash();
							hash.clear();
							setHash(hash);
							hash = getHash();
							hash.put(new String(c_Cad), new String(c_Cad));
							setHash(hash);
							ListTypeClass1.corp_detail(this);
							query = ConnectInit.queries
									.getProperty("stock_details_for_ca");
							rs = ListTypeClass1
									.getResult12(con, query, s_stock);
							try {
								setResult(rs);
							} catch (Exception e) {
								Logging.error("ListTypeClass1:Error in result set "
										+ e.getMessage());
							}
						}// chk_stk false
					}// chk_rs false
				}// if stock event
				if (r_type.equals("index event")) {
					String query = ConnectInit.queries
							.getProperty("select_undo_list_index");
					ResultSet rs = ListTypeClass1.check_dairy_exist(con, query,
							c_Cad, i_index, applied_date, time);
					boolean chk_rs = rs.next();
					if (chk_rs == true)// if applied
					{
						rs.beforeFirst();
						int count = 0;
						while (rs.next()) {
							count++;
							String cid = rs.getString("cad_id");
							String qry = ConnectInit.queries
									.getProperty("cad_stkind_details");
							ResultSet rs1 = ListTypeClass1.getresp_cad(con,
									qry, cid, cid, cid);
							rs1.next();
							sl = new stocklist(Integer.toString(count),
									rs1.getString("cam_name"),
									rs1.getString("stock_ex_name"),
									rs1.getString("stock_name"),
									rs1.getString("index_name"),
									rs1.getString("apply_on_date"),
									rs1.getString("applied_date"),
									rs1.getString("amount"),
									rs1.getString("percentage"),
									rs1.getString("ratio_for_shares"),
									rs1.getString("ratio_shares_offered"),
									rs1.getString("values"),
									rs1.getString("status"));
							Pp.add(sl);
						}// while
					}// if chk_rs
					if (chk_rs == false) {
						int cad_id = Integer.parseInt(getC_Cad());
						int flag = 0;
						// get all the stock belonging to the selected Index.
						query = null;
						query = ConnectInit.queries
								.getProperty("check_ind_exist_stock");
						rs = ListTypeClass1.check_dairy_exist(con, query,
								c_Cad, applied_date, time, i_index);
						boolean chk_stk = rs.next();
						if (chk_stk == true)// if stock belongs to any index
						{
							rs.beforeFirst();
							int count = 0;
							while (rs.next()) {
								count++;
								String cadid = rs.getString("cad_id");
								String qry1 = ConnectInit.queries
										.getProperty("cad_stkind_details");
								ResultSet rs2 = ListTypeClass1.getresp_cad(con,
										qry1, cadid, cadid, cadid);
								rs2.next();
								sl = new stocklist(Integer.toString(count),
										rs2.getString("cam_name"),
										rs2.getString("stock_ex_name"),
										rs2.getString("stock_name"),
										rs2.getString("index_name"),
										rs2.getString("apply_on_date"),
										rs2.getString("applied_date"),
										rs2.getString("amount"),
										rs.getString("percentage"),
										rs2.getString("ratio_for_shares"),
										rs2.getString("ratio_shares_offered"),
										rs2.getString("values"),
										rs2.getString("status"));
								Pp.add(sl);
								flag = 1;
							}// while
						}// if chk_stk
						if (chk_stk == false) {
							setLeng("1");
							setCad_id(c_Cad);
							ListTypeClass1.stock_corpdetail(this);
						}// if chk_stk false
					}// if chk_rs is false
				}// if index event
			} catch (Exception e) {
				Logging.error("Error ==" + e.getMessage());
			}

		} catch (Exception e) {
			Logging.debug("Error :" + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		undolist = Pp;

		return undolist;
	}

	/**
	 * @param undolist
	 *            The undolist to set.
	 */
	public void setUndolist(ArrayList undolist) {
		this.undolist = undolist;
	}

	/**
	 * @return Returns the ind_div.
	 */
	public String getInd_div() {
		return ind_div;
	}

	/**
	 * @param ind_div
	 *            The ind_div to set.
	 */
	public void setInd_div(String ind_div) {
		this.ind_div = ind_div;
	}

	/**
	 * @return Returns the ftcurrency.
	 */
	public String getFtcurrency() {
		return ftcurrency;
	}

	/**
	 * @param ftcurrency
	 *            The ftcurrency to set.
	 */
	public void setFtcurrency(String ftcurrency) {
		this.ftcurrency = ftcurrency;
	}

	/**
	 * @return Returns the ftCollection.
	 */
	public Collection getFtCollection() {
		Vector roles = new Vector();

		Connection con = null;
		Connect c = ConnectInit.getConnect();
		// Change by Manoj Adekar for Dynamic connection using getdbConnection()
		// instead of getConnection()
		try {
			if (con == null) {
				con = c.getdbConnection();
			}

			try {
				// AcessControl asc=new AcessControl();
				AcessControl asc = ConnectInit.getAcessControl();
				String SelCurr = asc.getLangValues("Masters.SelectCurrency");
				Logging.debug(" Inside getS_stkCollection: select Stock ="
						+ SelCurr);

				roles.add(new LabelValueBean(SelCurr, ""));
				if (ftcurrency != null) {
					if ((ftcurrency.equals("")) | (ftcurrency.equals(SelCurr)))
						ftcurrency = null;
				}
				String query = ConnectInit.queries
						.getProperty("resp_frto_currency");
				ResultSet rs = ListTypeClass1.getResult12(con, query,
						currencyid);
				if (ftcurrency == null) {
					while (rs.next()) {
						String count = rs.getString("from_to_currency_id");
						roles.add(new LabelValueBean(rs
								.getString("description"), count));
					}
				} else {
					while (rs.next()) {
						String count = rs.getString("from_to_currency_id");
						if (count.equals(ftcurrency))
							setFtcurrency(count);
						roles.add(new LabelValueBean(rs
								.getString("description"), count));
					}
				}
			} catch (Exception e) {
				Logging.error("Error==" + e.getMessage());
			}

		} catch (Exception e) {
			Logging.debug("Error :" + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		ftCollection = roles;
		return ftCollection;
	}

	/**
	 * @param ftCollection
	 *            The ftCollection to set.
	 */
	public void setFtCollection(Collection ftCollection) {
		this.ftCollection = ftCollection;
	}

	/**
	 * @return Returns the currencyid.
	 */
	public String getCurrencyid() {
		return currencyid;
	}

	/**
	 * @param currencyid
	 *            The currencyid to set.
	 */
	public void setCurrencyid(String currencyid) {
		this.currencyid = currencyid;
	}

	/**
	 * @return Returns the curr_val.
	 */
	public String getCurr_val() {
		return curr_val;
	}

	/**
	 * @param curr_val
	 *            The curr_val to set.
	 */
	public void setCurr_val(String curr_val) {
		this.curr_val = curr_val;
	}

	/**
	 * @return Returns the check_curr.
	 */
	public boolean isCheck_curr() {
		return check_curr;
	}

	/**
	 * @param check_curr
	 *            The check_curr to set.
	 */
	public void setCheck_curr(boolean check_curr) {
		this.check_curr = check_curr;
	}

	/**
	 * @return Returns the historiclist.
	 */
	public ArrayList getHistoriclist() {
		ArrayList Pp = new ArrayList();
		Connection con = null;
		Connect c = ConnectInit.getConnect();
		// Change by Manoj Adekar for Dynamic connection using getdbConnection()
		// instead of getConnection()
		try {
			if (con == null) {
				con = c.getdbConnection();
			}

			Logging.debug("in history list");
			Logging.debug("r type   in history=" + r_type);

			v.clear();

			try {
				int flg = 0;
				if (r_type.equals("stock event")) {
					String query = ConnectInit.queries
							.getProperty("historic_list");
					ResultSet rs = ListTypeClass1.hist_list(con, query,
							s_stock, apply_date);
					boolean chk_rs = rs.next();
					if (chk_rs == true)// if applied
					{
						flg = 1;
						rs.beforeFirst();
						int count = 0;
						while (rs.next()) {
							count++;
							String cid = rs.getString("cad_id");
							int ccid = rs.getInt("cad_id");
							if (!(v.contains(new String(cid))))
								v.addElement(new String(cid));
						}
					}
				}
				if (r_type.equals("index event")) {
					Logging.debug("in index event diary");
					boolean chk_rs = false;
					ResultSet rs = null;
					Logging.debug("corpid in index eve==" + corpid);
					if (corpid.equals("changeindcurr")) {
						String query = ConnectInit.queries
								.getProperty("Hist_currind_list");
						rs = ListTypeClass1.getResult_apply(con, query,
								i_index, apply_date);
						chk_rs = rs.next();
					} else {
						String query = ConnectInit.queries
								.getProperty("historic_list_index");
						Logging.debug("in index event diary" + stock[0]);
						String stk = stock[0];
						rs = ListTypeClass1.hist_list1(con, query, i_index,
								apply_date, stk);
						chk_rs = rs.next();
					}
					if (chk_rs == true)// if applied
					{
						flg = 1;
						rs.beforeFirst();
						int count = 0;
						while (rs.next()) {
							count++;
							String cid = rs.getString("cad_id");
							int ccid = rs.getInt("cad_id");
							if (!(v.contains(new String(cid))))
								v.addElement(new String(cid));
						}
					}
				}
				if (r_type.equals("index event diary")) {
					Logging.debug("in index event diary");
					String query = ConnectInit.queries
							.getProperty("historic_list_index1");
					ResultSet rs = ListTypeClass1.hist_list(con, query,
							i_index, apply_date);
					boolean chk_rs = rs.next();
					if (chk_rs == true)// if applied
					{
						flg = 1;
						rs.beforeFirst();
						int count = 0;
						while (rs.next()) {
							count++;
							String cid = rs.getString("cad_id");
							int ccid = rs.getInt("cad_id");
							if (!(v.contains(new String(cid))))
								v.addElement(new String(cid));
						}
					}

				}
				Logging.debug("vactor after hist==" + v);
				if (v.isEmpty() == false) {
					int count = 0;
					for (Enumeration enumm = v.elements(); enumm
							.hasMoreElements();) {
						count++;
						String id = (String) enumm.nextElement();

						String qry = ConnectInit.queries
								.getProperty("cad_stkind_details");
						ResultSet rs1 = ListTypeClass1.getresp_cad(con, qry,
								id, id, id);
						rs1.next();
						String stkexname = rs1.getString("stock_ex_name");
						if (stkexname == null)
							stkexname = "-";
						String sname = rs1.getString("stock_name");
						if (sname == null)
							sname = "-";
						String iname = rs1.getString("index_name");
						if (iname == null)
							iname = "-";
						String ap_date = rs1.getString("apply_on_date");
						if (ap_date == null)
							ap_date = "-";
						String amt = rs1.getString("amount");
						if (amt == null)
							amt = "-";
						String per = rs1.getString("percentage");
						if (per == null)
							per = "-";
						String r1 = rs1.getString("ratio_for_shares");
						if (r1 == null)
							r1 = "-";
						String r2 = rs1.getString("ratio_shares_offered");
						if (r2 == null)
							r2 = "-";
						String val = rs1.getString("values");
						if (val == null)
							val = "-";
						sl = new stocklist(Integer.toString(count),
								rs1.getString("cam_name"), stkexname, sname,
								iname, ap_date, rs1.getString("applied_date"),
								amt, per, r1, r2, val, rs1.getString("status"));
						Pp.add(sl);
					}
				}
				Logging.debug("r type in history==" + r_type);
				if (r_type.equals("stock event")) {
					Logging.debug("close in historic==" + getClose());
					if (flg == 0) {
						String corp_act = corpid;
						String chk_flg = check_type; // check action is from
														// diary or action page
						if (chk_flg != null)
							if (chk_flg.equals(""))
								chk_flg = null;

						if (chk_flg != null) {
							if (chk_flg.equals("New")) {
								try {
									String qry = ConnectInit.queries
											.getProperty("get_corporate_list_stock");
									ResultSet rs = ListTypeClass1
											.resultCorporate(con, qry);
									ListTypeClass1.check_corp_name(rs,
											corp_act, this);
									rs.close();

									// check whether this combination is present
									// in dairy or not.
									String query2 = ConnectInit.queries
											.getProperty("select_corporate_action_diary");
									ResultSet rs2 = ListTypeClass1.getresp_cad(
											con, query2, corpid, s_stock,
											apply_date);
									boolean chk_exist = rs2.next();
									corpid = corp_act;
									if (chk_exist == true) {
										setR_type("stock event");
										setLeng("1");
									} else {
										int flg_val = NCorp_Action.CalApply(
												con, c, this); // calculate the
																// new values
										setCommit_butt("1");
										setLeng("2");
									}
								} catch (Exception e) {
									Logging.error("Error=" + e.getMessage());
								}
							}
							if (chk_flg.equals("Exist")) {
								int flg_val = NCorp_Action.CalApply(con, c,
										this); // calculate the new values
								setCommit_butt("1");
								setLeng("2");
							}
						} else {
							try {
								String qry = ConnectInit.queries
										.getProperty("get_corporate_list_stock");
								ResultSet rs = ListTypeClass1.resultCorporate(
										con, qry);
								ListTypeClass1.check_corp_name(rs, corp_act,
										this);
								rs.close();

								// check whether this combination is present in
								// dairy or not.
								String query2 = ConnectInit.queries
										.getProperty("select_corporate_action_diary");
								ResultSet rs2 = ListTypeClass1.getresp_cad(con,
										query2, corpid, s_stock, apply_date);
								boolean chk_exist = rs2.next();
								corpid = corp_act;
								if (chk_exist == true) {
									setR_type("stock event");
									setLeng("1");
								} else {
									int flg_val = NCorp_Action.CalApply(con, c,
											this); // calculate the new values
									setCommit_butt("1");
									setLeng("2");
								}
							} catch (Exception e) {
								Logging.error("Error=" + e.getMessage());
							}
						}
					}
				}// stock event
				if (r_type.equals("index event")) {
					if (flg == 0) {
						try {
							// check whether this action is persent in diary or
							// not
							String corpnm = corpid;
							if (corpnm.equals("changeindcurr")) {
								String chk = ind_div;
								// check whether the action is for only tmcv
								// change or both tmcv & divisor
								if (chk.equals("td"))// for tmcv&divisor
								{
									// calculate new value
									ActionCorp.cal_curr_ind(con, c, this,
											i_index);
								} else // for tmcv only
								{
									// calculate new value
									ActionCorp.cal_curr_ind(con, c, this,
											i_index);
									// for change in tmcv only newdivisor is
									// equals to olddivisor
									if (ind_div.equals("t")) {
										setNewdivisor(getDivisor());
									}
								}
								IndexCA_Action.tmcv_div_adj(this);// remove E
																	// power
								String query = ConnectInit.queries
										.getProperty("resp_child_indcurr");
								ListTypeClass1.affect_child_list(con, this,
										query);
								setLeng("2");
								setCommit_butt("1");
							} else {

								// con paramter is added by samiksha
								boolean flag = IndexCA_Action.check_exist(
										i_index, this, corpid, stock, con);
								corpid = corpnm;
								if (flag == true) {
									setR_type("index event");
									setLeng("1");
								} else // if not present in diary
								{
									IndexCA_Action.insert_hash(stock, this); // insert
																				// into
																				// hash
																				// all
																				// selected
																				// stock
									// check out for index closing value
									int flg_val = 0;
									System.out.println("Connection --- " + con);
									System.out
											.println("Connection is closed----"
													+ con.isClosed());
									if (con.isClosed()) {
										con = c.getdbConnection();
										// con = c.getConnection();
									}
									flg_val = IndexCA_Action.check_close(con,
											c, this);
									if (flg_val == 1) {
										setLeng("2");
										setCommit_butt("1");
										ActionCorp.Hist_apply(this, 1);
										IndexCA_Action.tmcv_div_adj(this);
									}
									if (flg_val == 0) {
										setCommit_butt(null);
										setLeng("3");
									}
								}
							}
						} catch (Exception e) {
							Logging.error("Error=" + e.getMessage());
						}
					}// if flg
				}
				if (r_type.equals("index event diary")) {
					Logging.debug("flg in index event diary==" + flg);
					if (flg == 0) {
						int flg_val = 0;
						flg_val = IndexCA_Action.check_close(con, c, this);
						if (flg_val == 1) {
							setLeng("2");
							ActionCorp.Hist_applyindex(this, 1);
							setCommit_butt("1");
						}
						if (flg_val == 0)
							setLeng("3");
					}
				}
				setV(v);
			} catch (Exception e) {
				Logging.error("Error=" + e.getMessage());
			}
		} catch (Exception e) {
			Logging.debug("Error :" + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		historiclist = Pp;
		return historiclist;

	}

	/**
	 * @param historiclist
	 *            The historiclist to set.
	 */
	public void setHistoriclist(ArrayList historiclist) {
		this.historiclist = historiclist;
	}

	/**
	 * @return Returns the v.
	 */
	public Vector getV() {
		return v;
	}

	/**
	 * @param v
	 *            The v to set.
	 */
	public void setV(Vector v) {
		this.v = v;
	}

	/**
	 * @return Returns the mark_lot.
	 */
	public String getMark_lot() {
		return mark_lot;
	}

	/**
	 * @param mark_lot
	 *            The mark_lot to set.
	 */
	public void setMark_lot(String mark_lot) {
		this.mark_lot = mark_lot;
	}

	/**
	 * @return Returns the commit_butt.
	 */
	public String getCommit_butt() {
		return commit_butt;
	}

	/**
	 * @param commit_butt
	 *            The commit_butt to set.
	 */
	public void setCommit_butt(String commit_butt) {
		this.commit_butt = commit_butt;
	}

	/**
	 * @return Returns the succ_butt.
	 */
	public String getSucc_butt() {
		return succ_butt;
	}

	/**
	 * @param succ_butt
	 *            The succ_butt to set.
	 */
	public void setSucc_butt(String succ_butt) {
		this.succ_butt = succ_butt;
	}

	/**
	 * @return Returns the copy_hash.
	 */
	public Hashtable getCopy_hash() {
		return copy_hash;
	}

	/**
	 * @param copy_hash
	 *            The copy_hash to set.
	 */
	public void setCopy_hash(Hashtable copy_hash) {
		this.copy_hash = copy_hash;
	}

	/**
	 * @return Returns the success_flag.
	 */
	public String getSuccess_flag() {
		return success_flag;
	}

	/**
	 * @param success_flag
	 *            The success_flag to set.
	 */
	public void setSuccess_flag(String success_flag) {
		this.success_flag = success_flag;
	}

	/**
	 * @return Returns the stk_flag.
	 */
	public String getStk_flag() {
		return stk_flag;
	}

	/**
	 * @param stk_flag
	 *            The stk_flag to set.
	 */
	public void setStk_flag(String stk_flag) {
		this.stk_flag = stk_flag;
	}

	/**
	 * @return Returns the affIndexNoClosing.
	 */
	public String[] getAffIndexNoClosing() {
		return affIndexNoClosing;
	}

	/**
	 * @param affIndexNoClosing
	 *            The affIndexNoClosing to set.
	 */
	public void setAffIndexNoClosing(String[] affIndexNoClosing) {
		this.affIndexNoClosing = affIndexNoClosing;
	}

	public String getUserid1() {
		return userid1;
	}

	public void setUserid1(String userid1) {
		this.userid1 = userid1;
	}

	public String getRole_idc() {
		return role_idc;
	}

	public void setRole_idc(String role_idc) {
		this.role_idc = role_idc;
	}
}