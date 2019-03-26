/*
 * Created on Mar 25, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.masters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.validator.ValidatorForm;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * @author rahul modified by neha 13th July,2007
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class IndComplianceMasterForm extends ValidatorForm {
	Logger Logging = Logger.getLogger(IndComplianceMasterForm.class);
	Vector list_val, ind_list, selected_list;

	Connect connect = ConnectInit.getConnect();

	//Connection con = null;

	String name1, sh_name, desc, id, list1, methodToCall;

	String name_list, d1 = null, d3 = "";

	String fromvol = null, tovol = null;

	String fromval = null, toval = null;

	String frommcap = null, tomcap = null;

	String user_id = null;

	/**
	 * @return Returns the frommcap.
	 */
	public String getFrommcap() {
		return frommcap;
	}

	/**
	 * @param frommcap
	 *            The frommcap to set.
	 */
	public void setFrommcap(String frommcap) {
		this.frommcap = frommcap;
	}

	/**
	 * @return Returns the tomcap.
	 */
	public String getTomcap() {
		return tomcap;
	}

	/**
	 * @param tomcap
	 *            The tomcap to set.
	 */
	public void setTomcap(String tomcap) {
		this.tomcap = tomcap;
	}

	/**
	 * @return Returns the fromval.
	 */
	public String getFromval() {
		return fromval;
	}

	/**
	 * @param fromval
	 *            The fromval to set.
	 */
	public void setFromval(String fromval) {
		this.fromval = fromval;
	}

	/**
	 * @return Returns the toval.
	 */
	public String getToval() {
		return toval;
	}

	/**
	 * @param toval
	 *            The toval to set.
	 */
	public void setToval(String toval) {
		this.toval = toval;
	}

	/**
	 * @return Returns the fromvol.
	 */
	public String getFromvol() {
		return fromvol;
	}

	/**
	 * @param fromvol
	 *            The fromvol to set.
	 */
	public void setFromvol(String fromvol) {
		this.fromvol = fromvol;
	}

	/**
	 * @return Returns the tovol.
	 */
	public String getTovol() {
		return tovol;
	}

	/**
	 * @param tovol
	 *            The tovol to set.
	 */
	public void setTovol(String tovol) {
		this.tovol = tovol;
	}

	int mcap_rank = 0, avg_trading_rank = 0, avg_trading_value_rank = 0,
			listed_history = 0;

	float weightage = 0, floating_stock = 0;

	String[] sendNode = null, sendNodeHTML;

	Collection nameListCollection = null, list1Collection = null,
			d3Collection = null;

	public static TreeMap map = new TreeMap();

	String stockExange = null, country = null, stockType = null;

	String currency = null, rankingType;

	Collection currencyIdCollection = null, stockTypeCollection = null,
			countryIdCollection = null, stockExCollection = null;

	double maxWeightage = 0, volumeRankFrom = 0, volumeRankTo = 0,
			valueRankFrom = 0, valueRankTo = 0, mcapRankFrom = 0,
			mcapRankTo = 0;

	//int mcapDuration = 0, valDuration = 0, volDuration = 0;
	String compid = null;

	/**
	 * @return Returns the rankingType.
	 */
	public String getRankingType() {
		return rankingType;
	}

	/**
	 * @param rankingType
	 *            The rankingType to set.
	 */
	public void setRankingType(String rankingType) {
		this.rankingType = rankingType;
	}

	/*
	 * public int getMcapDuration() { return mcapDuration; }
	 * 
	 * 
	 * public void setMcapDuration(int mcapDuration) { this.mcapDuration =
	 * mcapDuration; }
	 * 
	 * 
	 * public int getValDuration() { return valDuration; }
	 * 
	 * 
	 * public void setValDuration(int valDuration) { this.valDuration =
	 * valDuration; }
	 * 
	 * 
	 * public int getVolDuration() { return volDuration; }
	 * 
	 * 
	 * public void setVolDuration(int volDuration) { this.volDuration =
	 * volDuration; }
	 */

	/**
	 * @return Returns the stockExCollection.
	 */
	public Collection getStockExCollection() {
		Vector countries = new Vector();
		ResultSet rst;
		Connection con = null;
		try {
			if (con == null)
				con = connect.getdbConnection();
			PreparedStatement stmt = con.prepareStatement(ConnectInit.queries
					.getProperty("stock_exchange_online_list"));
			rst = stmt.executeQuery();
			countries.add(new LabelValueBean("Not Selected", "0"));
			while (rst.next()) {
				String count = rst.getString(1);
				countries.add(new LabelValueBean(rst.getString(2), count));
			}
			rst.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
			Logging.debug(e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}

		stockExCollection = countries;
		return stockExCollection;
	}

	/**
	 * @param stockExCollection
	 *            The stockExCollection to set.
	 */
	public void setStockExCollection(Collection stockExCollection) {
		this.stockExCollection = stockExCollection;
	}

	/**
	 * @return Returns the countryIdCollection.
	 */
	public Collection getCountryIdCollection() {
		Vector countries = new Vector();
		ResultSet rst;
		Connection con = null;
		try {
			if (con == null)
				con = connect.getdbConnection();
			PreparedStatement stmt = con.prepareStatement(ConnectInit.queries
					.getProperty("sysconfig_select_countries"));
			rst = stmt.executeQuery();
			countries.add(new LabelValueBean("Not Selected", "0"));
			while (rst.next()) {
				String count = rst.getString(1);
				countries.add(new LabelValueBean(rst.getString(2), count));
			}
			rst.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Logging.debug(e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}

		countryIdCollection = countries;

		return countryIdCollection;
	}

	/**
	 * @param countryIdCollection
	 *            The countryIdCollection to set.
	 */
	public void setCountryIdCollection(Collection countryIdCollection) {
		this.countryIdCollection = countryIdCollection;
	}

	/**
	 * @return Returns the stockTypeCollection.
	 */
	public Collection getStockTypeCollection() {
		Vector currencies = new Vector();
		ResultSet rst;
		Connection con = null;
		try {
			if (con == null)
				con = connect.getdbConnection();

			PreparedStatement stmt = con.prepareStatement(ConnectInit.queries
					.getProperty("indCompliance.select_stock_type"));
			rst = stmt.executeQuery();
			currencies.add(new LabelValueBean("Not Selected", "value0"));
			while (rst.next()) {
				String count = rst.getString(1);
				currencies.add(new LabelValueBean(rst.getString(2), count));
			}
			rst.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
			Logging.debug(e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}

		stockTypeCollection = currencies;

		return stockTypeCollection;
	}

	/**
	 * @param stockTypeCollection
	 *            The stockTypeCollection to set.
	 */
	public void setStockTypeCollection(Collection stockTypeCollection) {
		this.stockTypeCollection = stockTypeCollection;
	}

	/**
	 * @return Returns the currencyIdCollection.
	 */
	public Collection getCurrencyIdCollection() {
		Vector currencies = new Vector();
		ResultSet rst;
		Connection con = null;

		try {
			if (con == null)
				con = connect.getdbConnection();

			PreparedStatement stmt = con.prepareStatement(ConnectInit.queries
					.getProperty("sysconfig_select_currencies"));
			rst = stmt.executeQuery();
			currencies.add(new LabelValueBean("Not Selected", "value0"));
			while (rst.next()) {
				String count = rst.getString(1);
				currencies.add(new LabelValueBean(rst.getString(2) + ": "
						+ rst.getString(3), count));
			}
			rst.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
			Logging.debug(e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		currencyIdCollection = currencies;
		return currencyIdCollection;
	}

	/**
	 * @param currencyIdCollection
	 *            The currencyIdCollection to set.
	 */
	public void setCurrencyIdCollection(Collection currencyIdCollection) {
		this.currencyIdCollection = currencyIdCollection;
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
	 * @return Returns the sendNodeHTML.
	 */
	public String[] getSendNodeHTML() {

		return sendNodeHTML;
	}

	/**
	 * @param sendNodeHTML
	 *            The sendNodeHTML to set.
	 */
	public void setSendNodeHTML(String[] sendNodeHTML) {
		this.sendNodeHTML = sendNodeHTML;
		if (sendNodeHTML != null) {
			Vector up = new Vector();
			String buff = sendNodeHTML[0].toString();
			StringTokenizer st = new StringTokenizer(buff, ",");
			String count = "0";

			while (st.hasMoreTokens()) {

				String activityId = st.nextToken();
				up.add(new LabelValueBean(activityId, count));
			}
			setD3Collection(up);
		}

	}

	/**
	 * @return Returns the methodToCall.
	 */
	public String getMethodToCall() {
		return methodToCall;
	}

	/**
	 * @param methodToCall
	 *            The methodToCall to set.
	 */
	public void setMethodToCall(String methodToCall) {
		this.methodToCall = methodToCall;
	}

	/**
	 * @return Returns the d3Collection.
	 */
	public Collection getD3Collection() {

		if (d3Collection == null) {
			return (new Vector());
		}
		return d3Collection;
	}

	/**
	 * @param collection
	 *            The d3Collection to set.
	 */
	public void setD3Collection(Collection collection) {
		d3Collection = collection;
	}

	/**
	 * @return Returns the list1Collection.
	 */
	public Collection getList1Collection() {

		Connection con = null;

		ResultSet rst;
		Vector update = new Vector(50);
		try {
			if (con == null) {
				con = connect.getdbConnection();
			}
			PreparedStatement stmt = con.prepareStatement(ConnectInit.queries
					.getProperty("industry_classification_list"));
			rst = stmt.executeQuery();
			while (rst.next()) {
				String count = rst.getString(1);
				update.add(new LabelValueBean(rst.getString(2), count));

			}
			rst.close();
			stmt.close();
		} catch (Exception e) {
			Logging.debug(e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}

		list1Collection = update;

		return list1Collection;
	}

	/**
	 * @param list1Collection
	 *            The list1Collection to set.
	 */
	public void setList1Collection(Collection list1Collection) {
		this.list1Collection = list1Collection;
	}

	/**
	 * @return Returns the list1.
	 */
	public String getList1() {

		return list1;
	}

	/**
	 * @param list1
	 *            The list1 to set.
	 */
	public void setList1(String list1) {
		this.list1 = list1;
	}

	/**
	 * @return Returns the nameListCollection.
	 */
	public Collection getNameListCollection() {
		ResultSet rst;

		Connection con = null;

		Vector update = new Vector(50);
		try {
			if (con == null)
				con = connect.getdbConnection();
			PreparedStatement stmt = con.prepareStatement(ConnectInit.queries
					.getProperty("sel_ind_classification_list"));
			stmt.setInt(1, Integer.parseInt(getUser_id()));
			rst = stmt.executeQuery();
			update.add(new LabelValueBean("Not Selected", "0"));

			while (rst.next()) {
				String count = rst.getString(1);

				update.add(new LabelValueBean(rst.getString(2), count));
				map.put(count, rst.getString(2));
			}
			rst.close();
			stmt.close();
		} catch (Exception e) {
			Logging.debug(e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}

		nameListCollection = update;

		return nameListCollection;
	}

	/**
	 * @param nameListCollection
	 *            The nameListCollection to set.
	 */
	public void setNameListCollection(Collection nameListCollection) {
		this.nameListCollection = nameListCollection;
	}

	/**
	 * @return Returns the sendNode.
	 */
	public String[] getSendNode() {
		Vector up = new Vector(10);

		return sendNode;
	}

	/**
	 * @param sendNode
	 *            The sendNode to set.
	 */
	public void setSendNode(String[] sendNode) {
		this.sendNode = sendNode;

	}

	/**
	 * @return Returns the d3.
	 */
	public String getD3() {
		return d3;
	}

	/**
	 * @param d3
	 *            The d3 to set.
	 */
	public void setD3(String d3) {
		this.d3 = d3;

	}

	/**
	 * @return Returns the avg_trading_rank.
	 */
	public int getAvg_trading_rank() {
		return avg_trading_rank;
	}

	/**
	 * @param avg_trading_rank
	 *            The avg_trading_rank to set.
	 */
	public void setAvg_trading_rank(int avg_trading_rank) {
		this.avg_trading_rank = avg_trading_rank;
	}

	/**
	 * @return Returns the avg_trading_value_rank.
	 */
	public int getAvg_trading_value_rank() {
		return avg_trading_value_rank;
	}

	/**
	 * @param avg_trading_value_rank
	 *            The avg_trading_value_rank to set.
	 */
	public void setAvg_trading_value_rank(int avg_trading_value_rank) {
		this.avg_trading_value_rank = avg_trading_value_rank;
	}

	/**
	 * @return Returns the floating_stock.
	 */
	public float getFloating_stock() {
		return floating_stock;
	}

	/**
	 * @param floating_stock
	 *            The floating_stock to set.
	 */
	public void setFloating_stock(float floating_stock) {
		this.floating_stock = floating_stock;
	}

	/**
	 * @return Returns the listed_history.
	 */
	public int getListed_history() {
		return listed_history;
	}

	/**
	 * @param listed_history
	 *            The listed_history to set.
	 */
	public void setListed_history(int listed_history) {
		this.listed_history = listed_history;
	}

	/**
	 * @return Returns the mcap_rank.
	 */
	public int getMcap_rank() {
		return mcap_rank;
	}

	/**
	 * @param mcap_rank
	 *            The mcap_rank to set.
	 */
	public void setMcap_rank(int mcap_rank) {
		this.mcap_rank = mcap_rank;
	}

	/**
	 * @return Returns the weightage.
	 */
	public float getWeightage() {
		return weightage;
	}

	/**
	 * @param weightage
	 *            The weightage to set.
	 */
	public void setWeightage(float weightage) {
		this.weightage = weightage;
	}

	public String getD1() {
		return d1;
	}

	public void setD1(String d1) {

		this.d1 = d1;
	}

	public String getName_list() {

		return name_list;
	}

	public void setName_list(String name_list) {
		this.name_list = name_list;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesc() {
		if (desc == null)
			desc = "";
		return desc;
	}

	public void setDesc(String desc) {

		this.desc = desc;
	}

	public String getName1() {
		if (name1 == null)
			name1 = "";
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getSh_name() {
		if (sh_name == null)
			sh_name = "";
		return sh_name;
	}

	public void setSh_name(String sh_name) {
		this.sh_name = sh_name;
	}

	public void getCollection() {

	}

	public int checkData(String name) {
		PreparedStatement checkcon;
		ResultSet rs1;
		int ans = 0;
		String nm1 = null;
		Connection con = null;
		if (con == null) {
			con = connect.getdbConnection();
		}
		try {
			checkcon = con.prepareStatement(ConnectInit.queries
					.getProperty("check_compliance_dupl"));
			checkcon.setString(1, name);
			rs1 = checkcon.executeQuery();
			while (rs1.next()) {
				nm1 = rs1.getString(1);
			}
			rs1.close();
			checkcon.close();
			if (nm1 != null && nm1.trim().length() != 0) {
				if (nm1.equalsIgnoreCase(name)) {
					ans = 1;
				}
			}
		} catch (Exception e) {
			Logging.debug("Error check() :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		return ans;
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		try {

			if (getD1().equals("0") || getD1().equals("")) {
				reset();
			}

			if (methodToCall != null && methodToCall.equals("Do")) {
				// int action = Integer.parseInt(getD1());

				String action = getD1();
				if (action == null) {
					reset();
				}
				Logging.debug("value of action" + action);
				String nm = getName1();
				if (!action.equals("0") && action != null) {
					if (action.equals("1")) {
						//new
						ActionErrors errors = new ActionErrors();
						try {
							int check = checkData(nm);
							if (check == 1) {
								errors.add("DuplicateEntry", new ActionError(
										"Error.message.DuplicateEntry"));
							}
							float iwf = getFloating_stock();
							if (iwf > 1.0 || iwf < 0) {
								errors.add(null, new ActionError(
										"Error.message.iwf"));
							}

						} catch (Exception e) {
							errors.add(null, new ActionError(
									"Error.message.msg"));
							Logging.debug("Error in Validation ");
						}
						return errors;
					} else if (action.equals("2")) {
						//update
						ActionErrors errors = new ActionErrors();

						// code added by neha for ind classification validation
						Connection connection = null;
						PreparedStatement stmt = null, stmt1 = null, stmt2 = null;
						ResultSet rst = null, rst1 = null, rst2 = null;
						Connect c = ConnectInit.getConnect();
						String icode1 = null, icode2 = null;
						int ic1 = 0, ic2 = 0;

						try {
							if (connection == null)
								connection = c.getdbConnection();
							stmt = connection.prepareStatement(ConnectInit.queries
									.getProperty("select_*_from_idxcmpid"));
							stmt.setString(1, getName1());
							rst = stmt.executeQuery();
							if (rst.next()) {
								compid = rst.getString(1);

							}
							stmt1 = connection.prepareStatement(ConnectInit.queries
									.getProperty("select_*_from_indclass1"));
							rst1 = stmt1.executeQuery();
							if (rst1.next()) {

								ic1 = rst1.getInt(2);

							}
							//ic1=Integer.parseInt(icode1);

							stmt2 = connection.prepareStatement(ConnectInit.queries
									.getProperty("select_*_from_indclass2"));
							stmt2.setString(1, getCompid());
							rst2 = stmt2.executeQuery();

							if (rst2.next()) {

								ic2 = rst2.getInt(2);
							}
							//ic2=Integer.parseInt(icode2);
							if (ic1 != ic2) {
								errors.add(null, new ActionError(
										"Error.message.indclass"));
							}
							rst.close();
							stmt.close();
							rst1.close();
							stmt1.close();
							rst2.close();
							stmt2.close();
						} catch (Exception e) {
							Logging.error(" Error : " + e.getMessage());
						} finally {
							try {
								if (connection != null)
									connection.close();
							} catch (Exception ee) {
								Logging
										.error(" Error : Unable to close connection "
												+ ee.getMessage());
							}
						}
						// code completed
						try {

							int check = checkData(nm);
							if (check == 1) {
								String duplicateRoleName = (String) map
										.get(name_list);
								if (!(nm.equals(duplicateRoleName))) {
									errors
											.add(
													"DuplicateEntry",
													new ActionError(
															"Error.message.DuplicateEntry"));
								}
							}
							float iwf = getFloating_stock();
							if (iwf > 1.0 || iwf < 0) {
								errors.add(null, new ActionError(
										"Error.message.iwf"));
							}

						} catch (Exception e) {
							errors.add(null, new ActionError(
									"Error.message.msg"));
							Logging.debug("Error in Validation ");
						}
						return errors;

					}
				}//

			}

		} catch (Exception e) {
			Logging.debug("message" + e);
		}
		return null;
	}

	/**
	 * @return Returns the country.
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            The country to set.
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return Returns the stockExange.
	 */
	public String getStockExange() {
		return stockExange;
	}

	/**
	 * @param stockExange
	 *            The stockExange to set.
	 */
	public void setStockExange(String stockExange) {
		this.stockExange = stockExange;
	}

	/**
	 * @return Returns the mcapRankFrom.
	 */
	public double getMcapRankFrom() {
		return mcapRankFrom;
	}

	/**
	 * @param mcapRankFrom
	 *            The mcapRankFrom to set.
	 */
	public void setMcapRankFrom(double mcapRankFrom) {
		this.mcapRankFrom = mcapRankFrom;
	}

	/**
	 * @return Returns the mcapRankTo.
	 */
	public double getMcapRankTo() {
		return mcapRankTo;
	}

	/**
	 * @param mcapRankTo
	 *            The mcapRankTo to set.
	 */
	public void setMcapRankTo(double mcapRankTo) {
		this.mcapRankTo = mcapRankTo;
	}

	/**
	 * @return Returns the valueRankFrom.
	 */
	public double getValueRankFrom() {
		return valueRankFrom;
	}

	/**
	 * @param valueRankFrom
	 *            The valueRankFrom to set.
	 */
	public void setValueRankFrom(double valueRankFrom) {
		this.valueRankFrom = valueRankFrom;
	}

	/**
	 * @return Returns the valueRankTo.
	 */
	public double getValueRankTo() {
		return valueRankTo;
	}

	/**
	 * @param valueRankTo
	 *            The valueRankTo to set.
	 */
	public void setValueRankTo(double valueRankTo) {
		this.valueRankTo = valueRankTo;
	}

	/**
	 * @return Returns the volumeRankFrom.
	 */
	public double getVolumeRankFrom() {
		return volumeRankFrom;
	}

	/**
	 * @param volumeRankFrom
	 *            The volumeRankFrom to set.
	 */
	public void setVolumeRankFrom(double volumeRankFrom) {
		this.volumeRankFrom = volumeRankFrom;
	}

	/**
	 * @return Returns the volumeRankTo.
	 */
	public double getVolumeRankTo() {
		return volumeRankTo;
	}

	/**
	 * @param volumeRankTo
	 *            The volumeRankTo to set.
	 */
	public void setVolumeRankTo(double volumeRankTo) {
		this.volumeRankTo = volumeRankTo;
	}

	/**
	 * @return Returns the stockType.
	 */
	public String getStockType() {
		return stockType;
	}

	/**
	 * @param stockType
	 *            The stockType to set.
	 */
	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	/**
	 * @return Returns the maxWeightage.
	 */
	public double getMaxWeightage() {
		return maxWeightage;
	}

	/**
	 * @param maxWeightage
	 *            The maxWeightage to set.
	 */
	public void setMaxWeightage(double maxWeightage) {
		this.maxWeightage = maxWeightage;
	}

	/** RESEST ALL FORM FIELDS* */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		name1 = null;
		sh_name = null;
		desc = null;
		d1 = "0";
		d3 = "";
		name_list = null;
		mcap_rank = 0;
		avg_trading_rank = 0;
		avg_trading_value_rank = 0;
		maxWeightage = 0;
		listed_history = 0;
		weightage = 0;
		floating_stock = 0;
		nameListCollection = null;
		list1Collection = null;
		d3Collection = null;
		volumeRankFrom = 0;
		volumeRankTo = 0;
		valueRankFrom = 0;
		valueRankTo = 0;
		countryIdCollection = null;
		stockExCollection = null;
		fromvol = null;
		tovol = null;
		fromval = null;
		toval = null;
		frommcap = null;
		tomcap = null;
	}

	public void reset() {
		name1 = null;
		sh_name = null;
		desc = null;
		d1 = "0";
		d3 = "";
		name_list = null;
		mcap_rank = 0;
		avg_trading_rank = 0;
		avg_trading_value_rank = 0;
		maxWeightage = 0;
		listed_history = 0;
		weightage = 0;
		floating_stock = 0;
		stockType = null;
		currency = null;
		rankingType = "t";
		stockExange = null;
		country = null;
		nameListCollection = null;
		list1Collection = null;
		d3Collection = null;
		volumeRankFrom = 0;
		volumeRankTo = 0;
		valueRankFrom = 0;
		valueRankTo = 0;
		countryIdCollection = null;
		stockExCollection = null;
		fromvol = null;
		tovol = null;
		fromval = null;
		toval = null;
		frommcap = null;
		tomcap = null;
	}

	/**
	 * @return Returns the compid.
	 */
	public String getCompid() {
		return compid;
	}

	/**
	 * @param compid
	 *            The compid to set.
	 */
	public void setCompid(String compid) {
		this.compid = compid;
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