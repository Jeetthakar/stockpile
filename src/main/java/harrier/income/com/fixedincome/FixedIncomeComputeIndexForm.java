/*
 * Created on Jun 13, 2008
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



import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;
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
import app.IncomeLibrary;

import com.harrier.initializeation.ConnectInit;

public class FixedIncomeComputeIndexForm extends ActionForm {
	static Logger Logging = Logger.getLogger(FixedIncomeComputeIndexForm.class);
	String fromcomposition, basedate1, index, b_showChild, query,
			indexcalculated, indexid, user_id = null;

	String computeall, computesame, computecurrency, computetotalreturns,
			close, settlement, ComputeIndex;

	FixedIncomeDefineIndexForm newIndexForm = null, newIndexForm1 = null;

	String closingvalue, avoidclosingvalue, closingvalueerror = "no",
			CAcheck = "no", lastclosingvalue, cdate = null;

	String indexType, rejectAlert = "no";

	String Stoprepitation = "";

	/**
	 * @return Returns the hist_Date.
	 */
	public String getHist_Date() {
		if (hist_Date == null || hist_Date.equals("")
				|| hist_Date.equals("null")) {
			hist_Date = QueryClass.formatDate();
		}
		return hist_Date;
	}

	/**
	 * @param hist_Date
	 *            The hist_Date to set.
	 */
	public void setHist_Date(String hist_Date) {
		this.hist_Date = hist_Date;
	}

	String alertvalue, indexval, indexvalcal, change, indname, rejectvalue,
			alertreject, hist_Date;

	Connection connection;

	/**
	 * @return Returns the alertvalue.
	 */
	public String getAlertvalue() {
		return alertvalue;
	}

	/**
	 * @param alertvalue
	 *            The alertvalue to set.
	 */
	public void setAlertvalue(String alertvalue) {
		this.alertvalue = alertvalue;
	}

	/**
	 * @return Returns the change.
	 */
	public String getChange() {
		return change;
	}

	/**
	 * @param change
	 *            The change to set.
	 */
	public void setChange(String change) {
		this.change = change;
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
	 * @return Returns the indexvalcal.
	 */
	public String getIndexvalcal() {
		return indexvalcal;
	}

	/**
	 * @param indexvalcal
	 *            The indexvalcal to set.
	 */
	public void setIndexvalcal(String indexvalcal) {
		this.indexvalcal = indexvalcal;
	}

	/**
	 * @return Returns the indname.
	 */
	public String getIndname() {
		return indname;
	}

	/**
	 * @param indname
	 *            The indname to set.
	 */
	public void setIndname(String indname) {
		this.indname = indname;
	}

	/**
	 * @return Returns the newIndexForm.
	 */
	public FixedIncomeDefineIndexForm getNewIndexForm() {
		return newIndexForm;
	}

	/**
	 * @param newIndexForm
	 *            The newIndexForm to set.
	 */
	public void setNewIndexForm(FixedIncomeDefineIndexForm newIndexForm) {
		if (newIndexForm != null
				&& newIndexForm.getCon() != null) {
			connection = newIndexForm.getCon();
		}
		newIndexForm1 = newIndexForm;
		this.newIndexForm = newIndexForm;
	}

	/**
	 * @return Returns the computeall.
	 */
	public String getComputeall() {
		return computeall;
	}

	/**
	 * @param computeall
	 *            The computeall to set.
	 */
	public void setComputeall(String computeall) {
		this.computeall = computeall;
	}

	/**
	 * @return Returns the computecurrency.
	 */
	public String getComputecurrency() {
		return computecurrency;
	}

	/**
	 * @param computecurrency
	 *            The computecurrency to set.
	 */
	public void setComputecurrency(String computecurrency) {
		this.computecurrency = computecurrency;
	}

	/**
	 * @return Returns the computesame.
	 */
	public String getComputesame() {
		return computesame;
	}

	/**
	 * @param computesame
	 *            The computesame to set.
	 */
	public void setComputesame(String computesame) {
		this.computesame = computesame;
	}

	/**
	 * @return Returns the computetotalreturns.
	 */
	public String getComputetotalreturns() {
		return computetotalreturns;
	}

	/**
	 * @param computetotalreturns
	 *            The computetotalreturns to set.
	 */
	public void setComputetotalreturns(String computetotalreturns) {
		this.computetotalreturns = computetotalreturns;
	}

	Connect c = ConnectInit.getConnect();

	Collection indexcollection;

	/**
	 * @return Returns the indexcollection.
	 */
	public Collection getIndexcollection() {
		Connection connection = null;
		Properties prop = new Properties();
		Properties prop1 = new Properties();
		//Logging.getDebug("resource url is " + ConnectInit.resourceurl);
		try {
			java.net.URL imgURL = FixedIncomeComputeIndexForm.class
					.getResource("FixedIncomeComputeIndexForm.class");
			
			String resourcepth = imgURL.toString();
			
			resourcepth = resourcepth.substring(6, (resourcepth
					.lastIndexOf("/WEB-INF/") + 8));
			
			String resourcepth1 = resourcepth
					+ "/classes/resources/user_query.properties";
			prop1.load(new FileInputStream(resourcepth1));
			resourcepth = resourcepth
					+ "/classes/resources/database.properties";
			prop.load(new FileInputStream(resourcepth));
		} catch (Exception ex) {
			//Logging.getError(" Error : " + ex.getMessage());
			Logging.debug(" Error : " + ex.getMessage());
		}
		String id1 = null;
		Vector v = new Vector();
		ResultSet rs = null;
		try {
			if (connection == null) {
				connection = c.getdbConnection();
			}
			String use_user = prop.getProperty("use_user");
			//Logging.getDebug("b_ShowChild  :: " + getB_showChild());
			if (use_user != null && use_user.equals("yes")) {
				if (getB_showChild() != null
						&& getB_showChild().trim().equals("on")) {
					query = ConnectInit.queries.getProperty("index_list");
				} else {
					query = ConnectInit.queries.getProperty("fixed_income_index_list_without_child");
				}
				PreparedStatement stmt = connection.prepareStatement(query);
				stmt.setString(1, user_id);
				rs = stmt.executeQuery();
			} else {
				if (getB_showChild() != null
						&& getB_showChild().trim().equals("on")) {
					query = ConnectInit.queries.getProperty("index_list");
				} else {
					query = ConnectInit.queries.getProperty("fixed_income_index_list_without_child");
				}
				Statement stmt = connection.createStatement();
				rs = stmt.executeQuery(query);
			}
			AcessControl asc = ConnectInit.getAcessControl();
	//		AcessControl asc = new AcessControl();
			String NotSelected = asc.getLangValues("Masters.NotSelected");
		//	Logging.getDebug(" Inside getIndexcollection(): Not Selected ="+ NotSelected);

			v.add(new LabelValueBean(NotSelected, "0"));
			while (rs.next()) {
				id1 = rs.getString(1);
				v.add(new LabelValueBean(rs.getString(2), id1));
			}
			indexcollection = v;
		} catch (Exception e) {
			// TODO: handle exception
			Logging.error(" Error :" + e.getMessage());
		//	e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ex) {
				//Logging.getError(" Error : Unable to close connection "+ ex.getMessage());
				Logging.debug(" Error : Unable to close connection "+ ex.getMessage());
			}
		}
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
	 * @return Returns the fromcomposition.
	 */
	public String getFromcomposition() {
		return fromcomposition;
	}

	/**
	 * @param fromcomposition
	 *            The fromcomposition to set.
	 */
	public void setFromcomposition(String fromcomposition) {
		if (fromcomposition == null
				|| (fromcomposition != null && fromcomposition.equals("no"))) {
			//  c.getConnectionForTransaction();
			try {
				try {
					//Logging.getDebug("connection is " + connection);
					Logging.debug("connection is " + connection);
					if (connection != null && !(connection.isClosed())) {
						connection.commit();
						connection.close();
					}
					//Logging.getDebug("connection is " + connection);
					connection = null;
					connection = ConnectInit.getConnect().getConnectionForTransaction();
					//Logging.getDebug("connection is " + connection);
					connection.rollback();
					//	indexcalculated="";
					//	initializeFields();
				} catch (Exception e) {
					// TODO: handle exception
					//Logging.getError(" Error :" + e.getMessage());
					Logging.debug(" Error :" + e.getMessage());
				}
				connection.setAutoCommit(false);

			} catch (Exception e) {
				//Logging.getDebug("database commited ");
				Logging.debug(" Error :" + e.getMessage());
				// TODO: handle exception
			}
		}
		this.fromcomposition = fromcomposition;
	}

	public void initializeFields() {

	}

	/**
	 * @return Returns the index.
	 */
	public String getIndex() {
		if(index==null || (index!=null && index.equals("null")))
			index="0";
		Logging.debug("index value afetr complition"+index);
		return index;
	}

	/**
	 * @param index
	 *            The index to set.
	 */
	public void setIndex(String index) {
		//Logging.getDebug("setIndex index " + index);
		if (index != null)
			this.index = index;
	}

	/**
	 * @return Returns the indexcalculated.
	 */
	public String getIndexcalculated() {
		return indexcalculated;
	}

	/**
	 * @param indexcalculated
	 *            The indexcalculated to set.
	 */
	public void setIndexcalculated(String indexcalculated) {
		this.indexcalculated = indexcalculated;
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
	 * @return Returns the settlement.
	 */
	public String getSettlement() {
		return settlement;
	}

	/**
	 * @param settlement
	 *            The settlement to set.
	 */
	public void setSettlement(String settlement) {
		this.settlement = settlement;
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

	public FixedIncomeDefineIndexForm nullNewIndexForm() {
		newIndexForm = null;
		return null;
	}

	/**
	 * @return Returns the computeIndex.
	 */
	public String getComputeIndex() {
		Logging.debug("1get");
		return ComputeIndex;
	}

	/**
	 * @param computeIndex
	 *            The computeIndex to set.
	 */
	public void setComputeIndex(String request) {
		//Logging.getDebug(indexid + "1 : ");
		harrier.income.com.compute.FixedIncomeCIndexCalculator cic = new harrier.income.com.compute.FixedIncomeCIndexCalculator();
		String settlement = "n";
		try {
			settlement = newIndexForm.getB_computeSettlementValue();
			if (settlement == null || !settlement.equals("on")) {
				settlement = "n";
			} else {
				settlement = "y";
			}
		} catch (Exception e) {
			settlement = "n";
			// TODO: handle exception
		}
		Logging.debug("2");
		index = new String(newIndexForm.getI_indexID());
		ComputeIndex = cic.computeIndex(newIndexForm.getI_indexID(),settlement, "n", "yes", null, null, basedate1, connection);
		Logging.debug("3 newindex form : " + newIndexForm);
		try {
			try {
				Double tempIndexValue;
				tempIndexValue = Double.valueOf(ComputeIndex);
				Double d = new Double(ComputeIndex);
				if (d.isNaN()) {
					throw new Exception();
				}
				if (d.doubleValue() == 0) {
					throw new Exception();
				}
				connection.commit();
				connection.setAutoCommit(true);
				indexcalculated = ComputeIndex;
				if (newIndexForm.getB_isIndexIsChildOrCustomized() != null
						&& newIndexForm.getB_isIndexIsChildOrCustomized()
								.trim().equals("1")) {
					b_showChild = "on";
				}
				index = new String(indexid);
				Logging
						.debug("Commiting index creation and composition as index value found is proper : "
								+ ComputeIndex);
			} catch (Exception e) {
				indexcalculated = "---";
				// TODO: handle exception
			//	e.printStackTrace();
				Logging
						.error(e.getMessage()
								+ "Roll backing index creation and composition as index value found is "
								+ ComputeIndex);
				connection.rollback();
				connection.setAutoCommit(true);
			}
		} catch (Exception e) {
		//	e.printStackTrace();
			Logging.error(" Error :" + e.getMessage());
			// TODO: handle exception
		}
		try {
			connection.close();
			connection = null;
			connection = c.getConnectionForTransaction();
		} catch (Exception e) {
			// TODO: handle exception
			Logging.error(" Error :" + e.getMessage());
		}
	}

	/**
	 * @return Returns the closingvalue.
	 */
	public String getClosingvalue() {
		return closingvalue;
	}

	/**
	 * @param closingvalue
	 *            The closingvalue to set.
	 */
	public synchronized void setClosingvalue(String closingvalue) {
		String query = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		if (index != null && !index.equals("0")) {
			query = ConnectInit.queries.getProperty("indexcomputation.todayclosing");
			try {
				if (con == null) {
					con = c.getdbConnection();
				}
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, index);
				pstmt.setString(2, QueryClass.formatDate());
				Logging.debug("checking closing " + pstmt);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					if (rs.getString("index_closing_value") != null) {
						Logging.debug("checking closing " + pstmt);
						this.lastclosingvalue = rs.getString("index_closing_value");
						this.closingvalue = "yes";
						return;
					} else {
						this.closingvalue = "no";
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				Logging.error(" Error :" + e.getMessage());
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
					if (rs != null)
						rs.close();
					if (con != null)
						con.close();
				} catch (Exception ex) {
					Logging.error(" Error : Unable to close Connection "
							+ ex.getMessage());
				}
			}
		}

		this.closingvalue = "no";
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		if (request.getParameter("operation1") != null
				&& request.getParameter("operation1").equals("Change")) {
			b_showChild = request.getParameter("b_showChild");
		}
		ActionErrors actionErrors = new ActionErrors();
		Logging
				.debug("base date in validate compute index is " + basedate1);
		String currdate = QueryClass.formatDate();
		int diff2 = CompareDate(hist_Date, currdate);
		/*
		 * if(diff>0){ actionErrors.add(null, new ActionError(
		 * "compute.error.historic_date")); return actionErrors; }
		 */
		this.Stoprepitation = "a";
		if ((request.getParameter("combo") != null
				&& request.getParameter("combo").trim().equals("yes")) ||
				(request.getParameter("operation1") != null
						&& request.getParameter("operation1").equals("Change"))){
			indexcalculated = "";
			computeall = "";
			computecurrency = "";
			computesame = "";
			computetotalreturns = "";
			settlement = "";
			close = "";
			boolean check = false;
			check = check_CA(hist_Date, request.getParameter("D1"));
			if (check == true) {
				this.CAcheck = "yes";
			} else {
				this.CAcheck = "no";
			}
			return actionErrors;
		}
		int diff1 = CompareDate(basedate1, currdate);
		if (diff1 > 0) {
			//String date=cdate;
			actionErrors.add(null, new ActionError(
					"compute.error.Can_not_compute"));
			return actionErrors;
		}
		Logging.debug("  diff1 is " + diff1 + " diff2 is " + diff2);
		boolean composecheck = checkComposition(indexid);
		if (composecheck == false) {
			actionErrors.add(null, new ActionError(
					"compute.error.Can_not_compute_no_composition"));
			return actionErrors;
		}

		/*
		 * getLastclosingvalue(); Logging.getDebug("lastclosingvalue is
		 * "+this.lastclosingvalue); String lcvalue=this.lastclosingvalue;
		 * Logging.getDebug("lcvalue is "+lcvalue); Logging.getDebug("cdate is
		 * "+cdate); if(lcvalue==null){ String clsMessage="First compute closing
		 * value for Date "+cdate; Logging.getDebug(clsMessage); }
		 */
		/*
		 * boolean check=false;
		 * check=check_CA(hist_Date,request.getParameter("D1")); if(check=true){
		 * this.CAcheck="yes"; }else{ this.CAcheck="no"; }
		 */
		if (diff1 <= 0 && diff2 != 0) {
			basedate1 = hist_Date;
			Logging.debug("base date in check is " + basedate1);
		}
		IncomeLibrary incomeLibrary = new IncomeLibrary();
		Hashtable exch_holidays = incomeLibrary.getHolidaysList(basedate1,
				currdate, indexid);
		if (exch_holidays.containsKey(basedate1)) {
			actionErrors.add(null, new ActionError(
					"compute.error.Can_not_compute_exch_holiday"));
			this.CAcheck = "no";
			return actionErrors;
		}
		boolean cstatus = false;
		if (diff2 == 0) {
			cstatus = checkPriceAvailable(hist_Date, request.getParameter("D1"));
			if (cstatus == false) {
				actionErrors.add(null, new ActionError(
						"compute.error.Can_not_compute_no_price"));
				this.CAcheck = "no";
				return actionErrors;
			}
		}

		getIndexType();
		String message = null;
		message = getLastclosingvalue();
		if (request.getParameter("operation") != null
				&& request.getParameter("operation").equals("Compute")
				&& message == null
				&& !(request.getParameter("combo") != null && request
						.getParameter("combo").trim().equals("yes"))) {
			/*
			 * String date=hist_Date; if(true){ actionErrors.add(null, new
			 * ActionError( "compute.error.ComputeOnLastDate")); return
			 * actionErrors; }
			 */
			//ind_compute_status="Compute";
			//compute Index
			synchronized (this) {
				this.Stoprepitation = this.Stoprepitation + "";
				if (!this.Stoprepitation.trim().equals("end")) {
					this.Stoprepitation = "end";

					Logging.debug("avoidclosingvalue : "
							+ request.getParameter("avoidclosingvalue"));
					Logging.debug(request.getParameter("avoidclosingvalue")
							+ "1 : " + index);
					setClosingvalue("");
					closingvalueerror = "no";
					/*
					 * if (request.getParameter("index") != null) { index =
					 * request.getParameter("index"); }
					 * 
					 * b_showChild = index =
					 * request.getParameter("b_showChild");
					 */
					Logging.debug(index + request.getParameter("index")
							+ "validate 1" + request.getParameter("operation"));
					if (closingvalue.equals("yes")
							&& request.getParameter("operation") != null
							&& request.getParameter("operation").equals(
									"Compute")) {
						Logging.debug("validate 2"
								+ request.getParameter("avoidclosingvalue"));

						b_showChild = request.getParameter("b_showChild");
						if (request.getParameter("avoidclosingvalue") == null
								|| !request.getParameter("avoidclosingvalue")
										.equals("Compute")) {
							Logging.debug("validate 3");
							closingvalueerror = "yes";
							return actionErrors;
						}
					}
					Logging.debug(index + "validate 2"
							+ request.getParameter("index"));
					getIndexType();
					Logging.debug("validate 2" + indexType);
					if (indexType != null && indexType.trim().equals("4")) {
						validateTotalReturns(actionErrors);
						if (!actionErrors.isEmpty()) {
							Logging.debug("validate TotalReturns "
									+ actionErrors.size());
							return actionErrors;
						}
					}
					try {
						if (connection == null) {
							connection = c.getConnectionForTransaction();
						}
						connection.rollback();
						connection.setAutoCommit(true);
					} catch (Exception e) {
					//	e.printStackTrace();
						Logging.error(" Error :" + e.getMessage());
						// TODO: handle exception
					}

					Logging.debug("validate 4");
					alertreject = "nothing";
					harrier.income.com.compute.FixedIncomeCIndexCalculator cic = new harrier.income.com.compute.FixedIncomeCIndexCalculator();
					String settlement = request.getParameter("settlement");
					if (settlement == null || !settlement.equals("on")) {
						settlement = "n";
					} else {
						settlement = "y";
					}
					String close = request.getParameter("close");
					if (close == null || !close.equals("on")) {
						close = "n";
					} else {
						close = "y";
					}
					Logging.debug("2");
					String listchildindices = "";
					String s_tocomputechild = null;
					if (request.getParameter("computeall") != null
							&& request.getParameter("computeall").trim()
									.equals("on")) {
						listchildindices = listchildindices + "1";
						s_tocomputechild = "yes";
					} else {
						if (request.getParameter("computesame") != null
								&& request.getParameter("computesame").trim()
										.equals("on")) {
							listchildindices = listchildindices + "2";
						}
						if (request.getParameter("computecurrency") != null
								&& request.getParameter("computecurrency")
										.trim().equals("on")) {
							listchildindices = listchildindices + "3";
						}
						if (request.getParameter("computetotalreturns") != null
								&& request.getParameter("computetotalreturns")
										.trim().equals("on")) {
							listchildindices = listchildindices + "4";
						}
					}
					String indexvalue = null;
					Logging.debug("this.Stoprepitation"
							+ this.Stoprepitation);
					this.Stoprepitation = this.Stoprepitation + " ";

					this.Stoprepitation = "end";
					Logging.debug("this.Stoprepitation in if"
							+ this.Stoprepitation);
					indexvalue = cic.computeIndex(index, settlement, close,
							s_tocomputechild, listchildindices, request,
							basedate1, connection);
					if (request.getAttribute("tmcverror") != null
							&& request.getAttribute("tmcverror").equals("yes")) {
						Logging
								.debug("setting zero mcv action error in bean");
						indexcalculated = "----";
						actionErrors.add(null, new ActionError(
								"compute.error.zeromcvmessage"));
						request.removeAttribute("tmcverror");
						try {
							connection.rollback();
							connection.setAutoCommit(true);
						} catch (Exception e) {
							// TODO: handle exception
							Logging.error(" Error :" + e.getMessage());
						}
						this.Stoprepitation = "end";
						Logging.debug(this.Stoprepitation
								+ "returning zero messaGE ACTION ERROR "
								+ actionErrors.size());
						if (!actionErrors.isEmpty())
							return actionErrors;
					}
					Logging.debug("MCV IS not zero");

					indexcalculated = indexvalue;
					try {
						try {
							Double tempIndexValue;
							tempIndexValue = Double.valueOf(indexvalue);
							Double d = new Double(indexvalue);
							if (d.isNaN()) {
								throw new Exception();
							}
							if (d.doubleValue() == 0) {
								throw new Exception();
							}
							Logging.debug("4 : " + indexvalue);
							//alert rejection logic
							try {

								PreparedStatement pst = connection
										.prepareStatement(ConnectInit.queries
												.getProperty("query.lastclosingvalue"));
								Logging.debug("5 : " + pst);
								pst.setString(1, index);
								pst.setString(2, index);
								Logging.debug("pst : " + pst);
								ResultSet resultSet = pst.executeQuery();
								if (resultSet.next()) {
									double e = resultSet
											.getDouble("index_value");
									double f = d.doubleValue();
									Logging.debug("e : " + e);
									Logging.debug("f : " + f);
									double g = 0;
									g = ((f - e) / e) * 100;
									g = Math.abs(g);
									//CLEAR....
									pst = connection
											.prepareStatement(ConnectInit.queries
													.getProperty("query.sysconfiguration"));
									pst.setString(1, index);
									resultSet = pst.executeQuery();
									Logging.debug("6 : " + pst);
									if (resultSet.next()) {
										double h = resultSet.getDouble("alert");
										double i = resultSet
												.getDouble("reject");
										Logging.debug("7 : " + indexvalue);
										String in_name = resultSet
												.getString("name");
										if (g >= i) {
											Logging.debug("g>=i reject: "
													+ (g >= i));
											connection.rollback();
											alertvalue = new Double(i)
													.toString();
											indexval = new Double(e).toString();
											indexvalcal = new Double(f)
													.toString();
											change = new Double(g).toString();
											indname = in_name;
											alertreject = "reject";
											Logging.debug("7 : "
													+ indexvalue);
											return actionErrors;

										} else if (g >= h) {
											Logging.debug("g>=i alert: "
													+ (g >= i));
											rejectvalue = new Double(h)
													.toString();
											indexval = new Double(e).toString();
											indexvalcal = new Double(f)
													.toString();
											change = new Double(g).toString();
											indname = in_name;
											alertreject = "alert";
											if (request
													.getParameter("rejectAlert") != null
													&& request.getParameter(
															"rejectAlert")
															.equals("yes")) {
												connection.commit();
												connection.setAutoCommit(true);
												return actionErrors;
											}

										} else {
											Logging.debug("8 : "
													+ indexvalue);
											connection.commit();
											connection.setAutoCommit(true);
											return actionErrors;
										}
										connection.rollback();
										connection.setAutoCommit(true);
										return actionErrors;
									}

								}
							} catch (Exception e) {
								connection.rollback();
								connection.setAutoCommit(true);
								Logging.error(" Error :" + e.getMessage());
								// TODO: handle exception
							}

						} catch (Exception e) {
							indexcalculated = "---";
							// TODO: handle exception
							Logging
									.error("Roll backing index creation and composition as index value found is "
											+ ComputeIndex);
							connection.rollback();
							connection.setAutoCommit(true);
						}
					} catch (Exception e) {

						Logging.error(" Error :" + e.getMessage());
						// TODO: handle exception
					}
					this.Stoprepitation = "end";
				} else {
					Logging.debug(actionErrors.size()
							+ " this.Stoprepitation in else"
							+ this.Stoprepitation);
					this.Stoprepitation = "a";
					if (indexcalculated.trim().equals("----")
							&& actionErrors.isEmpty()) {
						actionErrors.add(null, new ActionError(
								"compute.error.zeromcvmessage"));
						this.CAcheck = "no";
						return actionErrors;
					}
				}

			}

			Logging.debug("this for bean : " + this);

		}

		Logging.debug("validate 4");

		return actionErrors;
	}

	public void ref_val() {
		boolean check = false;
		check = check_CA(hist_Date, "1");
		if (check = true) {
			this.CAcheck = "yes";
		} else {
			this.CAcheck = "no";
		}
	}

	/**
	 * @return Returns the closingvalueerror.
	 */
	public String getClosingvalueerror() {
		return closingvalueerror;
	}

	/**
	 * @param closingvalueerror
	 *            The closingvalueerror to set.
	 */
	public void setClosingvalueerror(String closingvalueerror) {
		this.closingvalueerror = closingvalueerror;
	}

	/**
	 * @return Returns the avoidclosingvalue.
	 */
	public String getAvoidclosingvalue() {
		return avoidclosingvalue;
	}

	/**
	 * @param avoidclosingvalue
	 *            The avoidclosingvalue to set.
	 */
	public void setAvoidclosingvalue(String avoidclosingvalue) {
		this.avoidclosingvalue = avoidclosingvalue;
	}

	/**
	 * @return Returns the lastclosingvalue.
	 */
	public String getLastclosingvalue() {
		String query = null;
		String clsMessage = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		if (index != null && !index.equals("0")) {
			int cmpdate = CompareDate(hist_Date, (harrier.income.com.fixedincome.QueryClass.formatDate()));
			Logging.debug("hist_date is " + hist_Date
					+ " harrier.income.com.fixedincome.QueryClass.formatDate() is " + harrier.income.com.fixedincome.QueryClass.formatDate()
					+ " cmpdate is " + cmpdate);
			if (cmpdate == 0) {
				query = ConnectInit.queries
						.getProperty("indexcomputation.computeOnLast_date");
				try {
					if (con == null) {
						con = c.getdbConnection();
					}
					pstmt = con.prepareStatement(query);
					pstmt.setString(1, index);
					pstmt.setString(2, QueryClass.formatDate());
					pstmt.setString(3, index);
					Logging.debug("checking closing not exist" + pstmt);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						if (rs.getString("index_closing_value") != null) {
							Logging.debug("checking closing not exist"
									+ pstmt);
							this.lastclosingvalue = rs
									.getString("index_closing_value");
							this.cdate = rs.getString("index_value_date");
						} else {
							this.lastclosingvalue = null;
							this.cdate = rs.getString("index_value_date");
						}
					}
					Logging.debug("lastclosingvalue is "
							+ this.lastclosingvalue);
					Logging.debug("cdate is " + cdate);
					if (this.lastclosingvalue == null) {
						if (cdate != null) {
							clsMessage = "First compute closing value for Date "
									+ cdate;
							Logging.debug(clsMessage);
						} else {
							clsMessage = null;
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					Logging.error(" Error :" + e.getMessage());
				} finally {
					try {
						if (pstmt != null)
							pstmt.close();
						if (rs != null)
							rs.close();
						if (con != null)
							con.close();
					} catch (Exception ex) {
						Logging
								.error(" Error : Unable to close Connection "
										+ ex.getMessage());
					}
				}
			}
		}
		return clsMessage;
	}

	/**
	 * @param actionErrors
	 *            Validates Total Return Index.
	 */
	public void validateTotalReturns(ActionErrors errors) {
		Logging.debug("Starting validateTotalReturns with : " + basedate1);
		int temp = QueryClass.getIndexType(index, basedate1, connection);
		Logging.debug("Starting new index creation 2" + temp);
		switch (temp) {
		case 0:

			break;
		case 1:
			//	errors="This index is not either not difined or not
			// composed";
			errors.add(null, new ActionError("error.compute.indexnotdefined"));

			break;
		case 2:
			errors
					.add(null, new ActionError(
							"error.totalreturn.todaysdivisor"));

			break;
		case 3:
			errors
					.add(null, new ActionError(
							"error.totalreturn.todaysdivisor"));

			break;
		case 4:

			errors.add(null, new ActionError(
					"error.totalreturn.previousdivisor"));

			break;
		case 5:

			errors
					.add(null, new ActionError(
							"error.totalreturns.cashdividend"));

			break;
		case 6:
			errors.add(null, new ActionError("totalreturns.nodata"));

			break;
		default:
			break;

		}

	}

	/**
	 * Return type of index selected.
	 */
	public synchronized void getIndexType() {
		String query = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		if (index != null && !index.equals("0")) {
			query = ConnectInit.queries.getProperty("query_copy_indexmaster");
			try {
				if (con == null) {
					con = c.getdbConnection();
				}
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, index);
				Logging.debug("checking Index Type " + pstmt);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					if (rs.getString("index_type_id") != null) {
						Logging.debug("checking Index Type " + pstmt);
						this.indexType = rs.getString("index_type_id");
						return;
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				Logging.error(" Error :" + e.getMessage());
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
					if (rs != null)
						rs.close();
					if (con != null)
						con.close();
				} catch (Exception ex) {
					Logging.error(" Error : Unable to close Connection "
							+ ex.getMessage());
				}
			}
		}

	}

	/**
	 * @return Returns the alertreject.
	 */
	public String getAlertreject() {
		return alertreject;
	}

	/**
	 * @param alertreject
	 *            The alertreject to set.
	 */
	public void setAlertreject(String alertreject) {
		this.alertreject = alertreject;
	}

	/**
	 * @return Returns the rejectAlert.
	 */
	public String getRejectAlert() {
		return rejectAlert;
	}

	/**
	 * @param rejectAlert
	 *            The rejectAlert to set.
	 */
	public void setRejectAlert(String rejectAlert) {
		this.rejectAlert = rejectAlert;
	}

	/**
	 * @return Returns the stoprepitation.
	 */
	public String getStoprepitation() {
		return Stoprepitation;
	}

	/**
	 * @param stoprepitation
	 *            The stoprepitation to set.
	 */
	public void setStoprepitation(String stoprepitation) {
		Stoprepitation = stoprepitation;
	}

	/**
	 * @return Returns the basedate1.
	 */
	public String getBasedate1() {
		return basedate1;
	}

	/**
	 * @param basedate1
	 *            The basedate1 to set.
	 */
	public void setBasedate1(String basedate1) {
		this.basedate1 = basedate1;
	}

	/**
	 * @return Returns the b_showChild.
	 */
	public String getB_showChild() {
		return b_showChild;
	}

	/**
	 * @param child
	 *            The b_showChild to set.
	 */
	public void setB_showChild(String child) {
		b_showChild = child;

	}

	public boolean check_CA(String date, String id) {
		boolean flag = false;
		Connect connect = ConnectInit.getConnect();
		//app.Connect con=new app.Connect();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			if (con == null) {
				con = connect.getdbConnection();
			}
			pst = con.prepareStatement(ConnectInit.queries
					.getProperty("check_corporate_action_in_index_compute"));
			pst.setString(1, date);
			pst.setString(2, id);
			pst.setString(3, id);
			rst = pst.executeQuery();
			while (rst.next()) {
				flag = true;
				break;
			}
		} catch (Exception e) {
			Logging.error("Error : " + e.getMessage());
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (rst != null)
					rst.close();
				if (con != null)
					con.close();
			} catch (Exception ex) {
				Logging.error(" Error : Unable to close Connection "
						+ ex.getMessage());
			}
		}
		return flag;
	}

	public boolean checkPriceAvailable(String hist_Date, String indid) {
		boolean flag = false;
		Connect connect = ConnectInit.getConnect();
		Logging.debug("in checkPriceAvailable");
		Logging.debug("in hist_Date " + hist_Date + "  indid is  " + indid);
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rst = null;

		try {
			if (con == null) {
				con = connect.getdbConnection();
			}
			pst = con.prepareStatement(ConnectInit.queries
					.getProperty("fixed_income_check_for_price_on_date"));
			pst.setString(1, hist_Date);
			pst.setString(2, indid);
			rst = pst.executeQuery();
			while (rst.next()) {
				flag = true;
				break;
			}
		} catch (Exception e) {
			Logging.error("Error : " + e.getMessage());
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (rst != null)
					rst.close();
				if (con != null)
					con.close();
			} catch (Exception ex) {
				Logging.error(" Error : Unable to close Connection "
						+ ex.getMessage());
			}
		}
		return flag;
	}

	protected void finalize() {
		try {
			connection.close();
			connection = null;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * to compare two dates passed in string format. returns zero if two dates
	 * are equal ,+ve value second date less than first date parameter and -ve
	 * value if second date greater than first date parameter.
	 * 
	 * @param basedate
	 * @param history_Date
	 * @return
	 */
	public static int CompareDate(String basedate, String history_Date) {
		Date creationDate = new Date(new Integer(basedate.trim().substring(6,
				10)).intValue(), new Integer(basedate.trim().substring(3, 5))
				.intValue(), new Integer(basedate.trim().substring(0, 2))
				.intValue());
		Date hDate = new Date(new Integer(history_Date.trim().substring(6, 10))
				.intValue(), new Integer(history_Date.trim().substring(3, 5))
				.intValue(), new Integer(history_Date.trim().substring(0, 2))
				.intValue());
		int diff = creationDate.compareTo(hDate);
		return diff;
	}

	/**
	 * to check if composition is available for an index or not. returns false
	 * if no stock is there in index composition else return true.
	 * 
	 * @param id
	 * @return
	 */
	public static boolean checkComposition(String indexid) {
		boolean flag = false;
		Connect connect = ConnectInit.getConnect();
		//app.Connect con=new app.Connect();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			if (con == null) {
				con = connect.getdbConnection();
			}
			pst = con.prepareStatement(ConnectInit.queries
					.getProperty("check_for_index_composition"));
			pst.setString(1, indexid);
			rst = pst.executeQuery();
			while (rst.next()) {
				flag = true;
				break;
			}
		} catch (Exception e) {
			Logging.error("Error : " + e.getMessage());
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (rst != null)
					rst.close();
				if (con != null)
					con.close();
			} catch (Exception ex) {
				Logging.error(" Error : Unable to close Connection "
						+ ex.getMessage());
			}
		}
		return flag;
	}

	/**
	 * to get base date of an index.
	 * 
	 * @param id
	 * @return
	 */
	public static String getBaseDate(String id) {
		String bdate = null;
		Connect connect = ConnectInit.getConnect();
		//app.Connect con=new app.Connect();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			if (con == null)
				con = connect.getdbConnection();
			pst = con.prepareStatement(ConnectInit.queries
					.getProperty("get_base_date_for_index"));
			pst.setString(1, id);
			rst = pst.executeQuery();
			while (rst.next()) {
				bdate = rst.getString(1);
			}
		} catch (Exception e) {
			Logging.error("Error : " + e.getMessage());
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (rst != null)
					rst.close();
				if (con != null)
					con.close();
			} catch (Exception ex) {
				Logging.error(" Error : Unable to close Connection "
						+ ex.getMessage());
			}
		}
		//SimpleDateFormat sf=new SimpleDateFormat("dd-MM-yyyy");
		//Logging.getDebug("bdate in getBaseDate is "+bdate);
		return bdate;
	}

	/**
	 * @return Returns the cdate.
	 */
	public String getCdate() {
		return cdate;
	}

	/**
	 * @param cdate
	 *            The cdate to set.
	 */
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public StringBuffer getMessages(String message) {
		StringBuffer buffer1 = new StringBuffer();
		String display = null;
		buffer1
				.append("<td width='75%'><b><font face='Arial' color='Red' size='2'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
						+ message + "</font></b></td> ");
		buffer1.append("<br> ");
		return buffer1;
	}

	/**
	 * @return Returns the cAcheck.
	 */
	public String getCAcheck() {
		return CAcheck;
	}

	/**
	 * @param acheck
	 *            The cAcheck to set.
	 */
	public void setCAcheck(String acheck) {
		CAcheck = acheck;
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
}