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
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.LabelValueBean;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * @author rahul modified by neha 13th july,2007 TODO To change the template for
 *         this generated type comment go to Window - Preferences - Java - Code
 *         Style - Code Templates
 */
public class IndComplianceMasterAction extends DispatchAction {
	Logger Logging = Logger.getLogger(IndComplianceMasterAction.class);
	public static final String FORWARD_start = "success";

	Connect connect = ConnectInit.getConnect();

	//Connection con = null;
	PreparedStatement pst, stmt;

	ResultSet rst;

	int index_compliance_class_id, index_compliance_id;

	IndComplianceMasterForm form1;

	/** Reset All Fields After Updation Or Insertion */
	public void resetAllFields() {
		form1.setD1("0");
		form1.setName1("");
		form1.setName_list("0");
		form1.setName1("");
		form1.setSh_name("");
		form1.setDesc("");
		form1.setMcap_rank(0);
		form1.setMcapRankFrom(0);
		form1.setMcapRankTo(0);
		//form1.setMcapDuration(0);
		form1.setWeightage(0);
		form1.setMaxWeightage(0);
		form1.setAvg_trading_rank(0);
		form1.setVolumeRankFrom(0);
		form1.setVolumeRankTo(0);
		form1.setValueRankTo(0);
		//form1.setVolDuration(0);
		form1.setAvg_trading_value_rank(0);
		form1.setValueRankFrom(0);
		form1.setValueRankTo(0);
		//form1.setValDuration(0);
		form1.setListed_history(0);
		form1.setStockExange("0");
		form1.setCountry("0");
		form1.setFloating_stock(0);
		form1.setD3Collection(null);
		form1.setStockType("0");
		form1.setCurrency("0");
		//form1.setMcapDuration(0);
		//form1.setVolDuration(0);
		//form1.setValDuration(0);
		form1.setRankingType("t");
		form1.setTomcap("");
		form1.setToval("");
		form1.setTovol("");
		form1.setFrommcap("");
		form1.setFromval("");
		form1.setFromvol("");
	}

	public ActionForward notDo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		form1 = (IndComplianceMasterForm) form;
		String id = form1.getId();
		String d_list = form1.getD1();
		String name_list = form1.getName_list();
		int complainceID = 0;
		if (name_list != null) {
			complainceID = Integer.parseInt(name_list);
		}

		String cla_id = name_list;
		PreparedStatement pst;
		ResultSet rst;
		float floating_stoc = 0, weightag = 0;
		String i = null, n = null, d = null, shn = null, stockTypeId = null, currencyId = null, ranking_type = null, stock_ex_id = null, country_id = null;
		int mcap_ran = 0, avg_trading_ran = 0, avg_trading_value_ran = 0, ranking_perio = 0, listed_histor = 0;
		//int mcap_ranking_duration = 0, avg_traded_volume_duration = 0,
		// avg_traded_value_duration = 0;
		double avg_traded_volume_range2 = 0, mcap_range1 = 0, mcap_range2 = 0, maximum_weight = 0, avg_traded_volume_range1 = 0, avg_traded_value_range1 = 0, avg_traded_value_range2 = 0;
		String mcap_from_duration = null, mcap_to_duration = null;
		String value_from_duration = null, value_to_duration = null;
		String volume_from_duration = null, volume_to_duration = null;
		/***/
		Vector update = new Vector(50);
		if (cla_id != null || cla_id != "0") {
			Connection con = null;
			try {
				if (con == null)
					con = connect.getdbConnection();
				PreparedStatement stmt = con.prepareStatement(ConnectInit.queries
						.getProperty("industry_classification_list_selected"));
				stmt.setInt(1, Integer.parseInt(cla_id));
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

		}
		form1.setD3Collection(update);
		Connection con = null;
		try {

			if (con == null)
				con = connect.getdbConnection();
			pst = con.prepareStatement(ConnectInit.queries
					.getProperty("get_compliance_name_desc"));

			pst.setString(1, cla_id);
			pst.setInt(2, Integer.parseInt(form1.getUser_id()));
			rst = pst.executeQuery();
			while (rst.next()) {

				i = rst.getString(1);
				n = rst.getString(2);
				shn = rst.getString(3);
				d = rst.getString(4);
				mcap_ran = rst.getInt(5);
				weightag = rst.getFloat(6);
				avg_trading_ran = rst.getInt(7);
				avg_trading_value_ran = rst.getInt(8);
				listed_histor = rst.getInt(9);
				floating_stoc = rst.getFloat(10);
				stockTypeId = rst.getString(11);
				currencyId = rst.getString(12);
				ranking_type = rst.getString(13);
				mcap_range1 = rst.getDouble(14);
				mcap_range2 = rst.getDouble(15);
				maximum_weight = rst.getDouble(16);
				avg_traded_volume_range1 = rst.getDouble(17);
				avg_traded_value_range1 = rst.getDouble(18);
				avg_traded_value_range2 = rst.getDouble(19);
				stock_ex_id = rst.getString(20);
				country_id = rst.getString(21);
				avg_traded_volume_range2 = rst.getDouble(22);
				mcap_from_duration = rst.getString(23);
				mcap_to_duration = rst.getString(24);
				value_from_duration = rst.getString(25);
				value_to_duration = rst.getString(26);
				volume_from_duration = rst.getString(27);
				volume_to_duration = rst.getString(28);
			}
			rst.close();
			pst.close();

			form1.setId(i);
			form1.setName1(n);
			form1.setSh_name(shn);
			form1.setDesc(d);
			form1.setMcap_rank(mcap_ran);
			form1.setWeightage(weightag);
			form1.setAvg_trading_rank(avg_trading_ran);
			form1.setAvg_trading_value_rank(avg_trading_value_ran);
			form1.setListed_history(listed_histor);
			form1.setFloating_stock(floating_stoc);
			form1.setStockType(stockTypeId);
			form1.setCurrency(currencyId);
			form1.setRankingType(ranking_type);
			form1.setMcapRankFrom(mcap_range1);
			form1.setMcapRankTo(mcap_range2);
			form1.setMaxWeightage(maximum_weight);
			form1.setVolumeRankFrom(avg_traded_volume_range1);
			form1.setValueRankFrom(avg_traded_value_range1);
			form1.setValueRankTo(avg_traded_value_range2);
			form1.setStockExange(stock_ex_id);
			form1.setCountry(country_id);
			form1.setVolumeRankTo(avg_traded_volume_range2);
			form1.setFrommcap(mcap_from_duration);
			form1.setTomcap(mcap_to_duration);
			form1.setFromval(value_from_duration);
			form1.setToval(value_to_duration);
			form1.setFromvol(volume_from_duration);
			form1.setTovol(volume_to_duration);
			//form1.setMcapDuration(mcap_ranking_duration);
			//form1.setVolDuration(avg_traded_volume_duration);
			//form1.setValDuration(avg_traded_value_duration);
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

		//form1.setD1("0");

		ActionForward conf = null;
		String str_ret = "/pages/masters/IndComplianceMaster.jsp";
		Logging.debug("action 2...." + str_ret);
		return conf = new ActionForward(str_ret);

	}

	public ActionForward Do(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			form1 = (IndComplianceMasterForm) form;
			String id = form1.getId();
			String d_list = form1.getD1();
			String name_list = form1.getName_list();
			int complainceID = 0;
			if (name_list != null) {
				complainceID = Integer.parseInt(name_list);
			}
			Logging.debug("Compid" + complainceID);
			Connection con = null;

			String action = form1.getD1();
			//action can be either 1 or 2;
			/** Get All The fields From IndComplainceMaster */
			String name = form1.getName1();
			String shName = form1.getSh_name();
			String desc = form1.getDesc();
			int mRank = form1.getMcap_rank();
			float wei = form1.getWeightage();
			int avg = form1.getAvg_trading_rank();
			int avgVal = form1.getAvg_trading_value_rank();
			int his = form1.getListed_history();
			float stock = form1.getFloating_stock();
			String stock_type_id = form1.getStockType();
			String currency_id = form1.getCurrency();
			String ranking_type = form1.getRankingType();
			double mcap_range1 = form1.getMcapRankFrom();
			double mcap_range2 = form1.getMcapRankTo();
			double maximum_weight = form1.getMaxWeightage();
			double avg_traded_volume_range1 = form1.volumeRankFrom;
			double avg_traded_value_range1 = form1.valueRankFrom;
			double avg_traded_value_range2 = form1.valueRankTo;
			String stock_ex_id = form1.getStockExange();
			String country_id = form1.getCountry();
			double avg_traded_volume_range2 = form1.volumeRankTo;
			String mcapfrom = form1.getFrommcap();
			String mcapto = form1.getTomcap();
			String valuefrom = form1.getFromval();
			String valueto = form1.getToval();
			String volumefrom = form1.getFromvol();
			String volumeto = form1.getTovol();
			//int mcap_ranking_duration = form1.getMcapDuration();
			//int avg_traded_volume_duration = form1.getVolDuration();
			//int avg_traded_value_duration = form1.getValDuration();

			if (action.equals("2")) {
				//update

				if (con == null) {
					con = connect.getdbConnection();
				}
				try {
					con.commit();
					con.setAutoCommit(false);

					pst = con.prepareStatement(ConnectInit.queries
							.getProperty("indCompliance.update"));

					if (name == null || name.trim().length() == 0) {
						pst.setString(1, null);
					} else {
						pst.setString(1, name.trim());
					}

					if (shName == null || shName.trim().length() == 0) {
						pst.setString(2, null);
					} else {
						pst.setString(2, shName.trim());
					}

					if (desc == null || desc.trim().length() == 0) {
						pst.setString(3, null);
					} else {
						pst.setString(3, desc.trim());
					}

					if (mRank != 0) {
						pst.setInt(4, mRank);
					} else {
						pst.setString(4, null);
					}

					if (wei != 0) {
						pst.setFloat(5, wei);
					} else {
						pst.setString(5, null);
					}

					if (avg != 0) {
						pst.setInt(6, avg);
					} else {
						pst.setString(6, null);
					}

					if (avgVal != 0) {
						pst.setInt(7, avgVal);
					} else {
						pst.setString(7, null);
					}

					if (his != 0) {
						pst.setInt(8, his);
					} else {
						pst.setString(8, null);
					}

					if (stock != 0) {
						pst.setFloat(9, stock);
					} else {
						pst.setString(9, null);
					}

					if (stock_type_id == null
							|| stock_type_id.trim().equals("0")) {
						pst.setString(10, null);

					} else {
						pst.setString(10, stock_type_id);
					}

					if (currency_id == null || currency_id.trim().equals("0")) {
						pst.setString(11, null);

					} else {
						pst.setString(11, currency_id);
					}

					if (ranking_type.equals("t")) {
						pst.setString(12, "t");
					} else if (ranking_type.equals("b")) {
						pst.setString(12, "b");
					} else {
						pst.setString(12, "t");
					}

					if (mcap_range1 != 0) {
						pst.setDouble(13, mcap_range1);
					} else {
						pst.setString(13, null);
					}

					if (mcap_range2 != 0) {
						pst.setDouble(14, mcap_range2);
					} else {
						pst.setString(14, null);
					}

					if (maximum_weight != 0) {
						pst.setDouble(15, maximum_weight);
					} else {
						pst.setString(15, null);
					}

					if (avg_traded_volume_range1 != 0) {
						pst.setDouble(16, avg_traded_volume_range1);
					} else {
						pst.setString(16, null);
					}

					if (avg_traded_value_range1 != 0) {
						pst.setDouble(17, avg_traded_value_range1);
					} else {
						pst.setString(17, null);
					}

					if (avg_traded_value_range2 != 0) {
						pst.setDouble(18, avg_traded_value_range2);
					} else {
						pst.setString(18, null);
					}

					if (stock_ex_id == null || stock_ex_id.trim().equals("0")) {
						pst.setString(19, null);

					} else {
						pst.setString(19, stock_ex_id);
					}

					if (country_id == null || country_id.trim().equals("0")) {
						pst.setString(20, null);

					} else {
						pst.setString(20, country_id);
					}

					if (avg_traded_volume_range2 != 0) {
						pst.setDouble(21, avg_traded_volume_range2);
					} else {
						pst.setString(21, null);
					}

					if (mcapfrom == null || mcapfrom.trim().length() == 0) {
						pst.setString(22, null);
					} else {
						pst.setString(22, mcapfrom.trim());
					}

					if (mcapto == null || mcapto.trim().length() == 0) {
						pst.setString(23, null);
					} else {
						pst.setString(23, mcapto.trim());
					}
					if (valuefrom == null || valuefrom.trim().length() == 0) {
						pst.setString(24, null);
					} else {
						pst.setString(24, valuefrom.trim());
					}

					if (valueto == null || valueto.trim().length() == 0) {
						pst.setString(25, null);
					} else {
						pst.setString(25, valueto.trim());
					}
					if (volumefrom == null || volumefrom.trim().length() == 0) {
						pst.setString(26, null);
					} else {
						pst.setString(26, volumefrom.trim());
					}

					if (volumeto == null || volumeto.trim().length() == 0) {
						pst.setString(27, null);
					} else {
						pst.setString(27, volumeto.trim());
					}

					pst.setInt(28, complainceID);
					pst.setInt(29, Integer.parseInt(form1.getUser_id()));
					pst.executeUpdate();
					pst.close();
					/**
					 * deleting entries from index_compliance_class respective
					 * to index_compliance_class_id AND UPDATING
					 * index_compliance_class
					 */
					stmt = con.prepareStatement(ConnectInit.queries
							.getProperty("index_compliance_class_delete"));
					stmt.setInt(1, complainceID);
					stmt.executeUpdate();
					stmt.close();
					/** Insert into table index_compliance_class */
					String[] str = form1.getSendNode();
					String buff = str[0].toString();
					StringTokenizer st = new StringTokenizer(buff, ",");

					while (st.hasMoreTokens()) {

						/* Put The index_compliance_class One By One In Table */
						String activityId = st.nextToken();
						int numactID = Integer.parseInt(activityId);
						try {
							stmt = con
									.prepareStatement(ConnectInit.queries
											.getProperty("index_compliance_class_insert"));
							stmt.setInt(1, numactID);
							stmt.setInt(2, complainceID);
							stmt.executeUpdate();

						} catch (RuntimeException e1) {
						//	e1.printStackTrace();
							Logging.debug(e1);
							con.rollback();
							con.setAutoCommit(true);
						}

					}
					con.setAutoCommit(true);

				} catch (Exception e) {
					Logging.debug("Error :" + e);
					try {
						con.rollback();
						con.setAutoCommit(true);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						//e2.printStackTrace();
						Logging.debug(e2);
					}
				}
				//resetAllFields();

				return (new ActionForward(
						"/pages/masters/roleAdded.jsp?flag=complaince&value=update"));
			}

			else if (action.equals("1")) {
				// add new
				if (con == null) {
					con = connect.getdbConnection();
				}
				try {

					/** Get The New Complaince id from index_compliance */

					stmt = con
							.prepareStatement("select nextval('compliance_id')");
					rst = stmt.executeQuery();
					while (rst.next()) {
						index_compliance_id = Integer
								.parseInt(rst.getString(1));
						//index_compliance_id++;
					}
					rst.close();
					stmt.close();
					/** Insert int table index_compliance */
					pst = con.prepareStatement(ConnectInit.queries
							.getProperty("indCompliance.insert"));
					pst.setInt(1, index_compliance_id);
					if (name == null || name.trim().length() == 0) {
						pst.setString(2, null);
					} else {
						pst.setString(2, name.trim());
					}
					if (shName == null || shName.trim().length() == 0) {
						pst.setString(3, null);
					} else {
						pst.setString(3, shName.trim());
					}
					if (desc == null || desc.trim().length() == 0) {
						pst.setString(4, null);
					} else {
						pst.setString(4, desc.trim());
					}

					if (mRank != 0) {
						pst.setInt(5, mRank);
					} else {
						pst.setString(5, null);
					}

					if (wei != 0) {
						pst.setFloat(6, wei);
					} else {
						pst.setString(6, null);
					}

					if (avg != 0) {
						pst.setInt(7, avg);
					} else {
						pst.setString(7, null);
					}

					if (avgVal != 0) {
						pst.setInt(8, avgVal);
					} else {
						pst.setString(8, null);
					}

					if (his != 0) {
						pst.setInt(9, his);
					} else {
						pst.setString(9, null);
					}

					if (stock != 0) {
						pst.setFloat(10, stock);
					} else {
						pst.setString(10, null);
					}

					if (stock_type_id == null
							|| stock_type_id.trim().equals("0")) {
						pst.setString(11, null);

					} else {
						pst.setString(11, stock_type_id);
					}

					if (currency_id == null || currency_id.trim().equals("0")) {
						pst.setString(12, null);

					} else {
						pst.setString(12, currency_id);
					}

					if (ranking_type.equals("t")) {
						pst.setString(13, "t");
					} else if (ranking_type.equals("b")) {
						pst.setString(13, "b");
					} else {
						pst.setString(13, "t");
					}

					if (mcap_range1 != 0) {
						pst.setDouble(14, mcap_range1);
					} else {
						pst.setString(14, null);
					}

					if (mcap_range2 != 0) {
						pst.setDouble(15, mcap_range2);
					} else {
						pst.setString(15, null);
					}

					if (maximum_weight != 0) {
						pst.setDouble(16, maximum_weight);
					} else {
						pst.setString(16, null);
					}

					if (avg_traded_volume_range1 != 0) {
						pst.setDouble(17, avg_traded_volume_range1);
					} else {
						pst.setString(17, null);
					}

					if (avg_traded_value_range1 != 0) {
						pst.setDouble(18, avg_traded_value_range1);
					} else {
						pst.setString(18, null);
					}

					if (avg_traded_value_range2 != 0) {
						pst.setDouble(19, avg_traded_value_range2);
					} else {
						pst.setString(19, null);
					}

					if (stock_ex_id == null || stock_ex_id.trim().equals("0")) {
						pst.setString(20, null);

					} else {
						pst.setString(20, stock_ex_id);
					}

					if (country_id == null || country_id.trim().equals("0")) {
						pst.setString(21, null);

					} else {
						pst.setString(21, country_id);
					}

					if (avg_traded_volume_range2 != 0) {
						pst.setDouble(22, avg_traded_volume_range2);
					} else {
						pst.setString(22, null);
					}
					if (mcapfrom == null || mcapfrom.trim().length() == 0) {
						pst.setString(23, null);
					} else {
						pst.setString(23, mcapfrom.trim());
					}

					if (mcapto == null || mcapto.trim().length() == 0) {
						pst.setString(24, null);
					} else {
						pst.setString(24, mcapto.trim());
					}
					if (valuefrom == null || valuefrom.trim().length() == 0) {
						pst.setString(25, null);
					} else {
						pst.setString(25, valuefrom.trim());
					}

					if (valueto == null || valueto.trim().length() == 0) {
						pst.setString(26, null);
					} else {
						pst.setString(26, valueto.trim());
					}
					if (volumefrom == null || volumefrom.trim().length() == 0) {
						pst.setString(27, null);
					} else {
						pst.setString(27, volumefrom.trim());
					}

					if (volumeto == null || volumeto.trim().length() == 0) {
						pst.setString(28, null);
					} else {
						pst.setString(28, volumeto.trim());
					}

					pst.setInt(29, Integer.parseInt(form1.getUser_id()));
					pst.executeUpdate();

					/** Insert into table index_compliance_class */
					String[] str = form1.getSendNode();
					String buff = str[0].toString();
					StringTokenizer st = new StringTokenizer(buff, ",");

					while (st.hasMoreTokens()) {

						/* Put The index_compliance_class One By One In Table */
						String activityId = st.nextToken();
						int numactID = Integer.parseInt(activityId);
						try {
							stmt = con
									.prepareStatement(ConnectInit.queries
											.getProperty("index_compliance_class_insert"));
							stmt.setInt(1, numactID);
							stmt.setInt(2, index_compliance_id);
							stmt.executeUpdate();
							stmt.close();
							con.setAutoCommit(true);
						} catch (RuntimeException e1) {
						//	e1.printStackTrace();
							Logging.debug(e1);
							con.rollback();
							con.setAutoCommit(true);
						}

					}

				} catch (Exception e) {
					Logging.debug("Error :" + e);
					try {

					} catch (Exception e2) {
						// TODO Auto-generated catch block
						//e2.printStackTrace();
						Logging.debug(e2);
					}
				}
				resetAllFields();
				return (new ActionForward(
						"/pages/masters/roleAdded.jsp?flag=complaince&value=insert"));
			}//else if finish
		} catch (Exception e) {
			Logging.debug("new message" + e);
		}

		return mapping.getInputForward();
	}

}

