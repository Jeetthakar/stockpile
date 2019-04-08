/*
 * Created on Jun 10, 2005 
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import harrier.income.com.compute.CIndexCalculator;
import harrier.income.com.entities.CFormula;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jfree.chart.demo.servlet.AdjustDecimal;

import com.harrier.initializeation.ConnectInit;

/**
 * @author pranoti
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class ActionMerge {
	static Logger Logging = Logger.getLogger(ActionMerge.class);

	// This method is used to check Merger Type
	public static void Check_mergerType(StockMergerForm stockmerger) {
		Connect connect = ConnectInit.getConnect();
		Connection merge_con = null;
		try {
			// Connect connect = ConnectInit.getConnect();

			if (merge_con == null) {
				merge_con = connect.getConnectionForMergerTransaction();
			}
			merge_con.rollback();
			merge_con.setAutoCommit(false);
			Logging.debug("connection  is=" + merge_con);
			// check out which type of Merger
			Hashtable hash_aff1 = stockmerger.hash_aff1;
			Hashtable hash_aff2 = stockmerger.hash_aff2;
			boolean chk_aff1 = hash_aff1.isEmpty();
			boolean chk_aff2 = hash_aff2.isEmpty();

			if (chk_aff1 == true & chk_aff2 == false) // Outside-Index merging
			{
				stockmerger.setMerge_type("1");
				stockmerger.setTemp_type("1");
			}
			if (chk_aff1 == false & chk_aff2 == true) {
				stockmerger.setMerge_type("2"); // Index-Outside merging
				stockmerger.setTemp_type("2");
			}
			if ((chk_aff1 == false) & (chk_aff2 == false)) {
				stockmerger.setMerge_type("3"); // Index-Index merging
				stockmerger.setTemp_type("3");
			}
			if ((chk_aff1 == true) & (chk_aff2 == true)) // Outside-Outside
															// merging
			{
				stockmerger.setMerge_type("4");
				stockmerger.setTemp_type("4");
			}
			stockmerger.reset_store();
		} catch (Exception e) {
			Logging.error("Error=" + e.getMessage());
		} finally {
			if (merge_con != null) {
				try {
					merge_con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	// This method is used for Affected Indices computation
	public static void compute_ind(StockMergerForm stockmerger) {
		Connect connect = ConnectInit.getConnect();
		Connection merge_con = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		try {
			// Connect connect = ConnectInit.getConnect();
			merge_con = connect.getConnectionForMergerTransaction();

			Hashtable hash_str1 = stockmerger.getHash_store1(); // store
																// affected
																// index tmcv
																// value
			Hashtable hash_str2 = stockmerger.getHash_store2(); // store
																// affected
																// index divisor
																// value

			// queries
			String query = ConnectInit.queries.getProperty("select_resp_close");

			// CIndexCalculator cind=new CIndexCalculator();
			CIndexCalculator cind = ConnectInit.getCIndexCalculator();
			HttpServletRequest request = null;
			int flag = 0;
			String rad_val = stockmerger.getRad_butt();
			if (rad_val != null & (!(rad_val.equals("")))) {
				if (rad_val.equals("ct")) {
					Hashtable hash1 = stockmerger.getHash_aff1();
					Logging.debug("hash1 in compute===" + hash1);
					String id = null;
					for (Enumeration enumm = hash1.keys(); enumm
							.hasMoreElements();) {
						id = (String) enumm.nextElement();
						Logging.debug("index id in computation===" + id);
						flag = 0;
						rs = ListTypeClass1.getResult_apply(merge_con, query,
								id, stockmerger.getApply_date());
						if (rs.next()) {
							String close = rs.getString("index_closing_value");
							if (close == null)
								flag = 1;
						} else
							flag = 1;

						if (flag == 1)
							cind.computeIndex(id, "n", "y", "yes", null,
									request, stockmerger.getApply_date(),
									merge_con);

						stockmerger.setAffectedIndex1(id);
						rs1 = ListTypeClass1.getResult_apply(merge_con, query,
								id, stockmerger.getApply_date());
						rs1.next();
						stockmerger.setIndexval1(rs1
								.getString("index_closing_value"));
						stockmerger.setTmcv1(rs1.getString("adjusted_tmcv"));
						stockmerger.setNewtmcv1(rs1.getString("adjusted_tmcv"));
						stockmerger.setDivisor1(rs1
								.getString("adjusted_divisor"));
						stockmerger.setNewdivisor1(rs1
								.getString("adjusted_divisor"));
						// store value for affected index display
						boolean chk_hash1 = false;
						chk_hash1 = hash_str1.containsKey(id);
						if (chk_hash1 == false) {
							hash_str1.put(new String(id),
									stockmerger.getTmcv1());
							hash_str2.put(new String(id),
									stockmerger.getDivisor1());
						}
					}
					tmcv_div1(stockmerger);
				}
				if (rad_val.equals("cd")) {
					Hashtable hash2 = stockmerger.getHash_aff2();
					Logging.debug("hash2 in compute===" + hash2);
					String id = null;
					for (Enumeration enumm = hash2.keys(); enumm
							.hasMoreElements();) {
						id = (String) enumm.nextElement();
						Logging.debug("index in hash2=" + id);
						flag = 0;
						rs = ListTypeClass1.getResult_apply(merge_con, query,
								id, stockmerger.getApply_date());
						if (rs.next()) {
							String close = rs.getString("index_closing_value");
							if (close == null)
								flag = 1;
						} else
							flag = 1;

						if (flag == 1)
							cind.computeIndex(id, "n", "y", "yes", null,
									request, stockmerger.getApply_date(),
									merge_con);

						stockmerger.setAffectedIndex2(id);
						rs1 = ListTypeClass1.getResult_apply(merge_con, query,
								id, stockmerger.getApply_date());
						rs1.next();
						stockmerger.setIndexval2(rs1
								.getString("index_closing_value"));
						stockmerger.setTmcv2(rs1.getString("adjusted_tmcv"));
						stockmerger.setNewtmcv2(rs1.getString("adjusted_tmcv"));
						stockmerger.setDivisor2(rs1
								.getString("adjusted_divisor"));
						stockmerger.setNewdivisor2(rs1
								.getString("adjusted_divisor"));

						// store value for affected index display
						boolean chk_hash1 = false;
						chk_hash1 = hash_str1.containsKey(id);
						if (chk_hash1 == false) {
							hash_str1.put(new String(id),
									stockmerger.getTmcv2());
							hash_str2.put(new String(id),
									stockmerger.getDivisor2());
						}
					}
					tmcv_div2(stockmerger);
				}
				stockmerger.setHash_store1(hash_str1);
				stockmerger.setHash_store2(hash_str2);
			}
		} catch (Exception e) {
			Logging.error("Error=" + e.getMessage());
		} finally {
			try {
				if (merge_con != null)
					merge_con.close();
				if (rs != null)
					rs.close();
				if (rs1 != null)
					rs1.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// This method is used for Recalculate Index parameter
	public static void recal_index(StockMergerForm stockmerger) {
		Connect connect = ConnectInit.getConnect();
		Connection merge_con = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		try {
			// Connect connect = ConnectInit.getConnect();
			merge_con = connect.getConnectionForMergerTransaction();

			Hashtable hash_str1 = stockmerger.getHash_store1();
			Hashtable hash_str2 = stockmerger.getHash_store2();

			// queries
			String ind_resp = ConnectInit.queries
					.getProperty("select_resp_close");
			String query = ConnectInit.queries
					.getProperty("get_latest_index_value_index_wise");

			String rad_val = stockmerger.getRad_butt();
			if (rad_val != null & (!(rad_val.equals("")))) {
				if (rad_val.equals("rt")) {
					Hashtable hash1 = stockmerger.getHash_aff1();
					String id = null;
					int flag = 0;
					for (Enumeration enumm = hash1.keys(); enumm
							.hasMoreElements();) {
						id = (String) enumm.nextElement();
						flag = 0;
						rs = ListTypeClass1.getResult_apply(merge_con,
								ind_resp, id, stockmerger.getApply_date());
						if (rs.next()) {
							String close = rs.getString("index_closing_value");
							if (close == null)
								flag = 1;
						} else
							flag = 1;

						if (flag == 1)
							recal_para(merge_con, connect, stockmerger, id);

						stockmerger.setAffectedIndex1(id);
						rs1 = ListTypeClass1.getResult12(merge_con, query, id);
						rs1.next();
						stockmerger.setTmcv1(rs1.getString("tmcv"));
						stockmerger.setNewtmcv1(rs1.getString("tmcv"));
						stockmerger.setDivisor1(rs1.getString("divisor"));
						stockmerger.setNewdivisor1(rs1.getString("divisor"));
						stockmerger.setIndexval1(rs1
								.getString("index_closing_value"));
						rs1.close();
						// store value for affected index display
						boolean chk_hash = false;
						chk_hash = hash_str1.containsKey(id);
						if (chk_hash == false) {
							hash_str1.put(new String(id),
									stockmerger.getTmcv1());
							hash_str2.put(new String(id),
									stockmerger.getDivisor1());
						}
					}// for hash1
					tmcv_div1(stockmerger);
				}
				if (rad_val.equals("rd")) {
					Hashtable hash2 = stockmerger.getHash_aff2();
					String id = null;
					int flag = 0;
					for (Enumeration enumm = hash2.keys(); enumm
							.hasMoreElements();) {
						id = (String) enumm.nextElement();
						flag = 0;
						rs = ListTypeClass1.getResult_apply(merge_con,
								ind_resp, id, stockmerger.getApply_date());
						if (rs.next()) {
							String close = rs.getString("index_closing_value");
							if (close == null)
								flag = 1;
						} else
							flag = 1;

						if (flag == 1)
							recal_para(merge_con, connect, stockmerger, id);

						stockmerger.setAffectedIndex2(id);
						rs1 = ListTypeClass1.getResult12(merge_con, query, id);
						rs1.next();
						stockmerger.setTmcv2(rs1.getString("tmcv"));
						stockmerger.setNewtmcv2(rs1.getString("tmcv"));
						stockmerger.setDivisor2(rs1.getString("divisor"));
						stockmerger.setNewdivisor2(rs1.getString("divisor"));
						stockmerger.setIndexval2(rs1
								.getString("index_closing_value"));
						rs1.close();

						boolean chk_hash = false;
						chk_hash = hash_str1.containsKey(id);
						if (chk_hash == false) {
							hash_str1.put(new String(id),
									stockmerger.getTmcv2());
							hash_str2.put(new String(id),
									stockmerger.getDivisor2());
						}
					}// for hash1
					tmcv_div2(stockmerger);
				}
				stockmerger.setHash_store1(hash_str1);
				stockmerger.setHash_store2(hash_str2);
			}
		} catch (Exception e) {
			Logging.error("Error=" + e.getMessage());
		} finally {
			try {
				if (merge_con != null)
					merge_con.close();
				if (rs != null)
					rs.close();
				if (rs1 != null)
					rs1.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// this method is used to recalculate index parameter
	public static void recal_para(Connection merge_con, Connect connect,
			StockMergerForm stockmerger, String index) {
		ResultSet rs = null;
		ResultSet rs2 = null;
		try {
			// queries
			String stk_qry = ConnectInit.queries
					.getProperty("select_stock_price_detail");
			String stk_query = ConnectInit.queries
					.getProperty("detail_stock_master");
			CFormula cf = ConnectInit.getCFormula();
			// CFormula cf=new CFormula();
			Corporate corp = new Corporate();
			corp.setButton(null);
			corp.setApply_date(stockmerger.getApply_date());
			corp.setI_index(index);

			// get latest index parameter
			String query = ConnectInit.queries
					.getProperty("get_latest_index_value_index_wise");// "select_index_detail");
			rs = ListTypeClass1.getResult12(merge_con, query, index);
			rs.next();
			String tmcv = rs.getString("tmcv");
			String div = rs.getString("divisor");
			String index_val = rs.getString("index_closing_value");
			rs.close();

			double newtmcv = 0.0;

			// get composition
			String ind_com_query = ConnectInit.queries
					.getProperty("get_composition_of_parent");
			rs = ListTypeClass1.getResult1(merge_con, ind_com_query, index);
			while (rs.next()) {
				String sid = rs.getString("stock_id");
				String iwf = rs.getString("iwf");
				corp.setValues(iwf);

				rs2 = ListTypeClass1.getResult12(merge_con, stk_qry, sid);
				rs2.next();
				String close = rs2.getString("adjusted_price");
				if (close == null)
					close = rs2.getString("stock_closing_value");
				rs2.close();

				// get currency exchange value
				NCorp_Action.get_currency(merge_con, connect, corp, index, sid);
				String curr_val = corp.getCurr_val();

				rs2 = ListTypeClass1.getAffected(merge_con, stk_query, sid);
				rs2.next();
				String tis = rs2.getString("tis");
				String ml = rs2.getString("market_lot");
				rs2.close();

				double mcv = cf.calMarketCap(Double.parseDouble(close),
						Long.parseLong(ml), Double.parseDouble(curr_val),
						Long.parseLong(tis), Double.parseDouble(iwf));
				corp.setNewmcv(mcv);
				newtmcv = newtmcv + mcv;

				// update index composition
				String indexcomp = ConnectInit.queries
						.getProperty("update_index");
				UpdateCorp.update_index_comp(merge_con, indexcomp, corp);
			}
			corp.setNewTmcv(Double.toString(newtmcv));
			double newdivisor = newtmcv / Double.parseDouble(index_val);
			corp.setNewdivisor(Double.toString(newdivisor));

			// update index value daily
			String ind_val_daily = ConnectInit.queries
					.getProperty("update_index_value_daily");
			UpdateCorp.update_index_daily(merge_con, ind_val_daily, corp);

		} catch (Exception e) {
			Logging.debug("Error=" + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (rs2 != null)
					rs2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// This method is used for Deletion of stock
	public static void delete_stk(StockMergerForm stockmerger) {
		Connect connect = ConnectInit.getConnect();
		Connection merge_con = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			// Connect connect = ConnectInit.getConnect();
			merge_con = connect.getConnectionForMergerTransaction();

			// queries
			String ind_val = ConnectInit.queries
					.getProperty("get_latest_index_value_index_wise");
			String delete = ConnectInit.queries
					.getProperty("delete_index_comp");
			String index_his = ConnectInit.queries
					.getProperty("insert_index_com_his");

			// CFormula cf=new CFormula();
			CFormula cf = ConnectInit.getCFormula();
			// get stock1 detail
			String stk_qry = ConnectInit.queries
					.getProperty("detail_stock_master");
			rs = ListTypeClass1.getAffected(merge_con, stk_qry,
					stockmerger.getStock1());
			rs.next();
			String tis = rs.getString("tis");
			String ml = rs.getString("market_lot");
			String s_curr = rs.getString("stock_currency_id");
			rs.close();

			// to get currency exchange rate
			Corporate corp = new Corporate();
			corp.setApply_date(stockmerger.getApply_date());
			corp.setButton(null);

			// Merged Affected Indices
			Hashtable hash1 = stockmerger.getHash_aff1();
			// For Affected Indices
			Hashtable hash_str1 = stockmerger.getHash_store1();
			Hashtable hash_str2 = stockmerger.getHash_store2();
			boolean chk_str = hash_str1.isEmpty();
			for (Enumeration enumm = hash1.keys(); enumm.hasMoreElements();) {
				String id = (String) enumm.nextElement();
				corp.setI_index(id);
				stockmerger.setAffectedIndex1(id);

				// get index detail
				String ind_qry = ConnectInit.queries
						.getProperty("select_index_name");
				rs = ListTypeClass1.getAffected(merge_con, ind_qry, id);
				rs.next();
				String i_curr = rs.getString("base_currency_id");
				String parent = rs.getString("parent_id");
				rs.close();
				if (parent == null) // only parent index entry should be made in
									// dairy
				{
					insert_parent_dairy(merge_con, connect, stockmerger, corp,
							id, stockmerger.getStock1(), "deletestock");
				}
				// get composition
				String ind_comp = ConnectInit.queries
						.getProperty("index_comp_detail");
				rs = ListTypeClass1.getResult_apply(merge_con, ind_comp, id,
						stockmerger.getStock1());
				rs.next();
				String iwf = rs.getString("iwf");
				rs.close();

				// calculate mcv
				NCorp_Action.get_currency(merge_con, connect, corp, id,
						stockmerger.getStock1());
				String curr_val = corp.getCurr_val();
				double mcv = cf.calMarketCap(
						Double.parseDouble(stockmerger.getClose1()),
						Long.parseLong(ml), Double.parseDouble(curr_val),
						Long.parseLong(tis), Double.parseDouble(iwf));
				corp.setNewmcv(mcv);

				// get Tmcv and divisor
				rs = ListTypeClass1.getResult12(merge_con, ind_val, id);
				rs.next();
				stockmerger.setIndexval1(rs.getString("index_closing_value"));
				String tmcv = rs.getString("tmcv");
				stockmerger.setTmcv1(tmcv);
				String div = rs.getString("divisor");
				stockmerger.setDivisor1(div);
				// for Affected Index display
				if (chk_str == true) {
					Logging.debug("in delete stock");
					hash_str1.put(new String(id), stockmerger.getTmcv1());
					hash_str2.put(new String(id), stockmerger.getDivisor1());
				}

				double new_tmcv = Double.parseDouble(tmcv) - mcv;
				Logging.debug("new tmcv del==" + new_tmcv);
				stockmerger.setNewtmcv1(Double.toString(new_tmcv));
				corp.setNewTmcv(Double.toString(new_tmcv));

				double diff = cf.diffTMCV(Double.parseDouble(tmcv), new_tmcv);
				double newdivisor = cf.newDivisorCorp(Double.parseDouble(tmcv),
						diff, Double.parseDouble(div));
				stockmerger.setNewdivisor1(Double.toString(newdivisor));
				corp.setNewdivisor(Double.toString(newdivisor));

				// make a temporary updatation
				// delete stock1
				try {
					stmt = merge_con.prepareStatement(delete);
					stmt.setString(1, id);
					stmt.setString(2, stockmerger.getStock1());
					stmt.executeQuery();
				} catch (Exception e) {
					Logging.error("ListTypeClass:Error in DeleteStatement "
							+ e.getMessage());
				}
				// update index value daily
				String ind_val_daily = ConnectInit.queries
						.getProperty("update_index_value_daily");
				UpdateCorp.update_index_daily(merge_con, ind_val_daily, corp);

				// insert index composition history
				if (parent == null)
					UpdateCorp
							.insert_index_comp_his(merge_con, index_his, corp);
			}// for end
			tmcv_div1(stockmerger);
			stockmerger.setHash_store1(hash_str1);
			stockmerger.setHash_store2(hash_str2);
			Logging.debug("hash in delete stock="
					+ stockmerger.getHash_store1() + "2nd="
					+ stockmerger.getHash_store2());

		} catch (Exception e) {
			Logging.error("Error=" + e.getMessage());
		} finally {
			try {
				if (merge_con != null)
					merge_con.close();
				if (stmt != null)
					stmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// This method is used for deactive stock
	public static void deactive_stk(StockMergerForm stockmerger) {
		Connect connect = ConnectInit.getConnect();
		Connection merge_con = null;
		PreparedStatement pstmt = null;
		try {
			// Connect connect = ConnectInit.getConnect();
			merge_con = connect.getConnectionForMergerTransaction();
			Logging.debug("connection is=" + merge_con);
			Corporate corp = new Corporate();
			// make a temporary updation
			insert_stock_diary(merge_con, connect, corp,
					stockmerger.getStock1(), stockmerger.getTis1(),
					"capitalreduce");

			// insert into merger
			insert_merger(merge_con, connect, stockmerger, corp.getCad_id());

			// update stock master
			String query = ConnectInit.queries
					.getProperty("update_stock_active");
			pstmt = merge_con.prepareStatement(query);
			pstmt.setString(1, "d"); // d for delist
			pstmt.setString(2, "0"); // set tis=0
			pstmt.setString(3, stockmerger.getStock1());
			Logging.debug("pstmt in deactive====" + pstmt);
			pstmt.executeUpdate();
			Logging.debug("pstmt afetr deactive====" + pstmt);

			// update Merged Company stock price daily
			String update_qry = ConnectInit.queries
					.getProperty("update_tis_after_ca");
			corp.setNewmcv(0.0);
			corp.setStkid(stockmerger.getStock1());
			corp.setNewLTP(stockmerger.getClose1());
			UpdateCorp.updateAction(merge_con, update_qry, corp);

			// insert into stock master history
			Hashtable hash = corp.getHash1();
			hash.clear();
			corp.setHash1(hash);
			String qry = ConnectInit.queries.getProperty("insert_stock_master");
			String stk_qry = ConnectInit.queries
					.getProperty("detail_stock_master");
			UpdateCorp.insertstkmaster(merge_con, stk_qry, qry, corp,
					stockmerger.getFace1());

		} catch (Exception e) {
			Logging.error("Error=" + e.getMessage());
		} finally {
			try {
				if (merge_con != null)
					merge_con.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// This method is used for Addition of stock
	public static void add_stk(StockMergerForm stockmerger) {
		Connect connect = ConnectInit.getConnect();
		Connection merge_con = null;
		ResultSet rs = null;
		try {
			// Connect connect = ConnectInit.getConnect();

			merge_con = connect.getConnectionForMergerTransaction();

			// Queries
			String ind_val = ConnectInit.queries
					.getProperty("get_latest_index_value_index_wise");
			String index_his = ConnectInit.queries
					.getProperty("insert_index_com_his");
			String insert_index_comp = ConnectInit.queries
					.getProperty("insert_into_index_composition");
			CFormula cf = ConnectInit.getCFormula();
			// CFormula cf=new CFormula();
			// get stock1 detail
			String stk_qry = ConnectInit.queries
					.getProperty("detail_stock_master");
			rs = ListTypeClass1.getAffected(merge_con, stk_qry,
					stockmerger.getStock2());
			rs.next();
			String tis = rs.getString("tis");
			String ml = rs.getString("market_lot");
			String iwf = rs.getString("iwf");
			String s_curr = rs.getString("stock_currency_id");
			rs.close();

			// to get currency exchange rate
			Corporate corp = new Corporate();
			corp.setValues(iwf);
			corp.setApply_date(stockmerger.getApply_date());
			corp.setButton(null);

			corp.setStid(stockmerger.getStock2());
			// Merged Affected Indices
			Hashtable hash1 = stockmerger.getHash_aff1();
			Hashtable hash2 = stockmerger.getHash_aff2();
			for (Enumeration enumm = hash1.keys(); enumm.hasMoreElements();) {
				String id = (String) enumm.nextElement();
				boolean chk_hash = hash2.containsKey(id);
				if (chk_hash == false) {
					corp.setI_index(id);
					stockmerger.setAffectedIndex1(id);

					// get index detail
					String ind_qry = ConnectInit.queries
							.getProperty("select_index_name");
					rs = ListTypeClass1.getAffected(merge_con, ind_qry, id);
					rs.next();
					String i_curr = rs.getString("base_currency_id");
					String parent = rs.getString("parent_id");
					rs.close();
					if (parent == null) // only parent index entry should be
										// made in dairy
					{
						insert_parent_dairy(merge_con, connect, stockmerger,
								corp, id, stockmerger.getStock2(), "addstock");
					}
					// calculate mcv
					NCorp_Action.get_currency(merge_con, connect, corp, id,
							stockmerger.getStock2());
					String curr_val = corp.getCurr_val();
					double mcv = cf.calMarketCap(
							Double.parseDouble(stockmerger.getClose2()),
							Long.parseLong(ml), Double.parseDouble(curr_val),
							Long.parseLong(tis), Double.parseDouble(iwf));
					Logging.debug("mcv in add==" + mcv);
					corp.setNewmcv(mcv);

					// get Tmcv and divisor
					rs = ListTypeClass1.getResult12(merge_con, ind_val, id);
					rs.next();
					String tmcv = rs.getString("tmcv");
					stockmerger.setTmcv1(tmcv);
					Logging.debug("old tmcv==" + tmcv);
					String div = rs.getString("divisor");
					stockmerger.setDivisor1(div);
					stockmerger.setIndexval1(rs
							.getString("index_closing_value"));

					double new_tmcv = Double.parseDouble(tmcv) + mcv;
					Logging.debug("new tmcv==" + new_tmcv);
					stockmerger.setNewtmcv1(Double.toString(new_tmcv));
					corp.setNewTmcv(Double.toString(new_tmcv));

					double diff = cf.diffTMCV(Double.parseDouble(tmcv),
							new_tmcv);
					double newdivisor = cf.newDivisorCorp(
							Double.parseDouble(tmcv), diff,
							Double.parseDouble(div));
					stockmerger.setNewdivisor1(Double.toString(newdivisor));
					corp.setNewdivisor(Double.toString(newdivisor));

					// make a temporary updatation

					// insert index composition
					corp.setValues(iwf);
					UpdateCorp.insert_index_comp(merge_con, insert_index_comp,
							corp);
					// update index value daily
					String ind_val_daily = ConnectInit.queries
							.getProperty("update_index_value_daily");
					UpdateCorp.update_index_daily(merge_con, ind_val_daily,
							corp);

					// insert index composition history
					if (parent == null)
						UpdateCorp.insert_index_comp_his(merge_con, index_his,
								corp);
				}// check hash
			}// for end
			tmcv_div1(stockmerger);
		} catch (Exception e) {
			Logging.error("Error=" + e.getMessage());
		} finally {
			try {
				if (merge_con != null) {
					merge_con.close();
				}
				if (rs != null) {
					rs.close();
				}

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	// This method is used for share issuance
	public static void Share_iss(StockMergerForm stockmerger) {
		// CFormula cf=new CFormula();
		CFormula cf = ConnectInit.getCFormula();
		Corporate corp = new Corporate();

		Connect connect = ConnectInit.getConnect();
		Connection merge_con = null;
		ResultSet rs = null;

		try {
			// Connect connect = ConnectInit.getConnect();

			merge_con = connect.getConnectionForMergerTransaction();

			Logging.debug("in share iss");

			String no_share = stockmerger.getNo_share();
			String ratio1 = stockmerger.getShare1();
			String ratio2 = stockmerger.getShare2();
			if (no_share != null & (!(no_share.equals("")))) {
				long new_tis = cf.newTISIssue(
						Long.parseLong(stockmerger.getTis2()),
						Long.parseLong(no_share));
				stockmerger.setNewTIS(Long.toString(new_tis));
				stockmerger.setAdjust(no_share);
			} else {
				if ((ratio1 != null & (!(ratio1.equals(""))))
						| ((ratio2 != null & (!(ratio2.equals("")))))) {
					long add_tis = cf.newRecalTISSplitRev(
							Long.parseLong(stockmerger.getTis1()),
							Double.parseDouble(ratio1),
							Double.parseDouble(ratio2));
					String adjust = Long.toString(add_tis);
					stockmerger.setAdjust(adjust);
					long new_tis = cf.newTISIssue(
							Long.parseLong(stockmerger.getTis2()), add_tis);
					stockmerger.setNewTIS(Long.toString(new_tis));
				}
			}

			// make a temporary updation
			insert_stock_diary(merge_con, connect, corp,
					stockmerger.getStock2(), stockmerger.getNewTIS(),
					"shareissuance");

			// update Acquiring Company tis in stock master
			String query = ConnectInit.queries
					.getProperty("update_tis_stockmaster");
			corp.setNewTIS(stockmerger.getNewTIS());
			corp.setStid(stockmerger.getStock2());
			UpdateCorp.updatestkmaster(merge_con, query, corp,
					stockmerger.getFace2());

			// update Acquring company stock price daily
			// get stock detail
			String update_qry = ConnectInit.queries
					.getProperty("update_tis_after_ca");
			String stk_qry = ConnectInit.queries
					.getProperty("detail_stock_master");
			rs = ListTypeClass1.getAffected(merge_con, stk_qry,
					stockmerger.getStock2());
			rs.next();
			String ml = rs.getString("market_lot");
			rs.close();

			double mcv = cf.calMarketCap(
					Double.parseDouble(stockmerger.getClose2()),
					Long.parseLong(ml), 1,
					Long.parseLong(stockmerger.getNewTIS()),
					Double.parseDouble(stockmerger.getIwfstk2()));
			corp.setNewmcv(mcv);
			corp.setStkid(stockmerger.getStock2());
			corp.setNewLTP(stockmerger.getClose2());
			UpdateCorp.updateAction(merge_con, update_qry, corp);

			// insert stock master history
			Hashtable hash = corp.getHash1();
			hash.clear();
			corp.setHash1(hash);
			String qry = ConnectInit.queries.getProperty("insert_stock_master");
			UpdateCorp.insertstkmaster(merge_con, stk_qry, qry, corp,
					stockmerger.getFace2());

			Logging.debug("call share iss");
			String type = stockmerger.getMerge_type();
			Logging.debug("type===" + type);
			if (type != null) {
				if (!(type.equals("4"))) {
					String rad_val = stockmerger.getRad_butt();
					if (rad_val != null)
						if (rad_val.equals(""))
							rad_val = null;

					share_iss(stockmerger, stockmerger.getHash_aff2());
				}
			}
		} catch (Exception e) {
			Logging.error("Error=" + e.getMessage());
		} finally {
			try {
				if (merge_con != null) {
					merge_con.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

	}

	// this method is used for share issue action
	public static void share_iss(StockMergerForm stockmerger, Hashtable hash) {
		Connect connect = ConnectInit.getConnect();
		Connection merge_con = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		try {
			// Connect connect = ConnectInit.getConnect();

			merge_con = connect.getConnectionForMergerTransaction();
			Logging.debug("connection is=" + merge_con);
			// queries
			String type = stockmerger.getMerge_type();
			String ind_val = ConnectInit.queries
					.getProperty("get_latest_index_value_index_wise");
			CFormula cf = ConnectInit.getCFormula();
			// CFormula cf=new CFormula();
			Corporate corp = new Corporate();

			Logging.debug("in child share issf");
			corp.setStid(stockmerger.getStock2());
			// get stock detail
			String stk_qry = ConnectInit.queries
					.getProperty("detail_stock_master");
			rs = ListTypeClass1.getAffected(merge_con, stk_qry,
					stockmerger.getStock2());
			rs.next();
			String ml = rs.getString("market_lot");
			rs.close();

			// to get currency exchange rate
			corp.setApply_date(stockmerger.getApply_date());
			corp.setButton(null);

			Hashtable hash1 = stockmerger.getHash_aff1();

			// for Affected Indices
			Hashtable hash_str1 = stockmerger.getHash_store1();
			Hashtable hash_str2 = stockmerger.getHash_store2();

			boolean chk_hashstr = hash_str1.isEmpty();
			if (!(hash.isEmpty())) {
				for (Enumeration enumm = hash.keys(); enumm.hasMoreElements();) {
					String id = (String) enumm.nextElement();
					Logging.debug("id in share iss==" + id);
					boolean chk_hash = false;
					corp.setI_index(id);
					stockmerger.setAffectedIndex2(id);
					String tmcv = null, div = null;

					rs = ListTypeClass1.getResult12(merge_con, ind_val, id);
					rs.next();
					tmcv = rs.getString("tmcv");
					Logging.debug("old tmcv==" + tmcv);
					div = rs.getString("divisor");
					rs.close();
					if (type != null) {
						if (type.equals("1")) {
							stockmerger.setIndexval2(rs
									.getString("index_closing_value"));
							stockmerger.setTmcv2(tmcv);
							stockmerger.setDivisor2(div);
							boolean chk_str = false;
							// store for Affected Indices
							if (chk_hashstr == true) {
								Logging.debug("in share issue loop");
								hash_str1.put(new String(id),
										stockmerger.getTmcv2());
								hash_str2.put(new String(id),
										stockmerger.getDivisor2());
							}
						} else {
							stockmerger.setIndexval2(rs
									.getString("index_closing_value"));
							stockmerger.setTmcv2(tmcv);
							stockmerger.setDivisor2(div);
							// for Affected Index Display
							boolean chk_str = hash_str1.containsKey(id);
							if (chk_str == false) {
								Logging.debug("in add stock loop");
								hash_str1.put(new String(id),
										stockmerger.getTmcv2());
								hash_str2.put(new String(id),
										stockmerger.getDivisor2());
							}
						}
					}
					// get composition
					String ind_comp = ConnectInit.queries
							.getProperty("index_comp_detail");
					rs = ListTypeClass1.getResult_apply(merge_con, ind_comp,
							id, stockmerger.getStock2());
					rs.next();
					String iwf = rs.getString("iwf");
					corp.setValues(iwf);
					rs.close();

					// calculate mcv
					NCorp_Action.get_currency(merge_con, connect, corp, id,
							stockmerger.getStock2());
					String curr_val = corp.getCurr_val();
					// old mcv
					double old_mcv = cf.calMarketCap(
							Double.parseDouble(stockmerger.getClose2()),
							Long.parseLong(ml), Double.parseDouble(curr_val),
							Long.parseLong(stockmerger.getTis2()),
							Double.parseDouble(iwf));
					Logging.debug("old mcv===" + old_mcv);

					// new mcv
					double new_mcv = cf.calMarketCap(
							Double.parseDouble(stockmerger.getClose2()),
							Long.parseLong(ml), Double.parseDouble(curr_val),
							Long.parseLong(stockmerger.getNewTIS()),
							Double.parseDouble(iwf));
					Logging.debug("new mcv==" + new_mcv);
					corp.setNewmcv(new_mcv);

					// new TMCV
					double newtmcv = ((Double.parseDouble(tmcv) - old_mcv) + (new_mcv));
					corp.setNewTmcv(Double.toString(newtmcv));

					// new divisor
					double diff = cf
							.diffTMCV(Double.parseDouble(tmcv), newtmcv);
					double newdivisor = cf.newDivisorCorp(
							Double.parseDouble(tmcv), diff,
							Double.parseDouble(div));
					corp.setNewdivisor(Double.toString(newdivisor));

					stockmerger.setNewtmcv2(Double.toString(newtmcv));
					stockmerger.setNewdivisor2(Double.toString(newdivisor));

					// update index composition
					String indexcomp = ConnectInit.queries
							.getProperty("update_index");
					UpdateCorp.update_index_comp(merge_con, indexcomp, corp);

					// update index value daily
					String ind_val_daily = ConnectInit.queries
							.getProperty("update_index_value_daily");
					UpdateCorp.update_index_daily(merge_con, ind_val_daily,
							corp);
				}// for end
			}// hash empty
			stockmerger.setHash_store1(hash_str1);
			stockmerger.setHash_store2(hash_str2);
			if ((type.equals("1")) | (type.equals("3")))
				tmcv_div2(stockmerger);
			// to display merged affected indices if Acquiring company only is
			// added to merged Affected indices
			if (type.equals("2") | type.equals("3")) {
				Hashtable hash_aff1 = stockmerger.getHash_aff1();
				Hashtable hashstr1 = stockmerger.getHash_store1();
				Hashtable hashstr2 = stockmerger.getHash_store2();
				for (Enumeration enumm = hash_aff1.keys(); enumm
						.hasMoreElements();) {
					String id = (String) enumm.nextElement();
					if (hashstr1.containsKey(id)) {
						stockmerger.setTmcv1((hashstr1.get(id)).toString());
						stockmerger.setDivisor1((hashstr2.get(id)).toString());
					}
					rs1 = ListTypeClass1.getResult12(merge_con, ind_val, id);
					rs1.next();
					stockmerger.setNewtmcv1(rs1.getString("tmcv"));
					stockmerger.setNewdivisor1(rs1.getString("divisor"));
					stockmerger.setIndexval1(rs1
							.getString("index_closing_value"));
					rs1.close();
				}
				tmcv_div1(stockmerger);
			}

		} catch (Exception e) {
			Logging.error("Error in share" + e.getMessage());
		} finally {
			try {
				if (merge_con != null) {
					merge_con.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (rs1 != null) {
					rs1.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

	}

	// this method is used to insert parent index into diary
	public static void insert_parent_dairy(Connection merge_con,
			Connect connect, StockMergerForm stockmerger,
			Corporate corporateact, String index, String stock, String corp_nm) {
		ResultSet rs2 = null;
		try {
			String insert_cad = ConnectInit.queries
					.getProperty("insert_index_cad");
			String corp_query = ConnectInit.queries
					.getProperty("get_corporate_list_index");

			String date = stockmerger.getApply_date();// UpdateCorp.accept_date();
			corporateact.setAnnounce_date(date);
			corporateact.setEx_date(date);
			corporateact.setRecord_date(date);
			corporateact.setApply_date(date);
			corporateact.setApplied_date(date);
			corporateact.setStatus("y");
			corporateact.setBase_date(null);
			corporateact.setBc_start(null);
			corporateact.setBc_end(null);
			corporateact.setNc_start(null);
			corporateact.setNc_end(null);
			corporateact.setStid(stock);
			corporateact.setI_index(index);
			if ((corp_nm.equals("addstock")) | (corp_nm.equals("deletestock")))
				corporateact.setValues(null);
			rs2 = ListTypeClass1.resultCorporate(merge_con, corp_query);
			ListTypeClass1.check_corp_name(rs2, corp_nm, corporateact);
			rs2.close();
			String nextval = ConnectInit.queries
					.getProperty("get_sequence_cad_id");
			UpdateCorp.insert_index_cad(merge_con, insert_cad, nextval,
					corporateact);

		} catch (Exception e) {
			Logging.error("Error=" + e.getMessage());
		} finally {
			if (rs2 != null) {
				try {
					rs2.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	// this method is used to insert share iss. action into diary
	public static void insert_stock_diary(Connection merge_con,
			Connect connect, Corporate corp, String stock, String share,
			String corp_nm) {
		ResultSet rs = null;
		try {
			String date = UpdateCorp.accept_date();
			corp.setAnnounce_date(date);
			corp.setRecord_date(date);
			corp.setApplied_date(date);
			corp.setApply_date(date);
			corp.setEx_date(date);
			corp.setBc_start(date);
			corp.setBc_end(date);
			corp.setNc_start(date);
			corp.setNc_end(date);
			corp.setStatus("y");
			corp.setShare(share);
			corp.setStid(stock);
			String query = ConnectInit.queries
					.getProperty("get_corporate_list_stock");
			rs = ListTypeClass1.resultCorporate(merge_con, query);
			ListTypeClass1.check_corp_name(rs, corp_nm, corp);
			query = ConnectInit.queries
					.getProperty("insert_into_cad_values_with_time");
			String nextval = ConnectInit.queries
					.getProperty("get_sequence_cad_id");
			UpdateCorp.insert_into_cad_with_time(merge_con, query, nextval,
					corp, null);
		} catch (Exception e) {
			Logging.error("Error= =" + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	// this method is used to insert merger entry
	public static void insert_merger(Connection merge_con, Connect connect,
			StockMergerForm stockmerger, String cad_id) {
		PreparedStatement pstmt = null;
		try {
			String query = ConnectInit.queries
					.getProperty("insert_into_merger");
			pstmt = merge_con.prepareStatement(query);
			pstmt.setString(1, cad_id);
			pstmt.setString(2, stockmerger.getStock1());
			pstmt.setString(3, stockmerger.getStock2());
			pstmt.executeUpdate();
			Logging.debug("completed");
		} catch (Exception e) {
			Logging.error("Error=" + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	// this method is used to see affected index values for Merged Indices
	public static void affect_ind1(StockMergerForm stockmerger) {
		Connect connect = ConnectInit.getConnect();
		Connection merge_con = null;
		ResultSet rs = null;
		try {
			// Connect connect = ConnectInit.getConnect();

			merge_con = connect.getConnectionForMergerTransaction();

			String affect1 = stockmerger.getAffectedIndex1();
			// get old values from hash tables
			Hashtable hash_str1 = stockmerger.getHash_store1();
			Hashtable hash_str2 = stockmerger.getHash_store2();

			if (hash_str1.containsKey(affect1)) {
				String tmcv = hash_str1.get(affect1).toString();
				stockmerger.setTmcv1(tmcv);
			}
			if (hash_str2.containsKey(affect1)) {
				String div = hash_str2.get(affect1).toString();
				stockmerger.setDivisor1(div);
			}

			// get new values from DB
			String query = ConnectInit.queries
					.getProperty("get_latest_index_value_index_wise");
			rs = ListTypeClass1.getResult12(merge_con, query, affect1);
			rs.next();
			stockmerger.setNewtmcv1(rs.getString("tmcv"));
			stockmerger.setNewdivisor1(rs.getString("divisor"));
			stockmerger.setIndexval1(rs.getString("index_closing_value"));
			rs.close();
			tmcv_div1(stockmerger);
		} catch (Exception e) {
			Logging.error("Error=" + e.getMessage());
		} finally {
			try {
				if (merge_con != null)
					merge_con.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// this method is used to see affected index values for Acquiring Indices
	public static void affect_ind2(StockMergerForm stockmerger) {
		Connect connect = ConnectInit.getConnect();
		Connection merge_con = null;
		ResultSet rs = null;
		try {
			// Connect connect = ConnectInit.getConnect();
			merge_con = connect.getConnectionForMergerTransaction();

			String affect2 = stockmerger.getAffectedIndex2();
			// get old values from hash tables
			Hashtable hash_str1 = stockmerger.getHash_store1();
			Hashtable hash_str2 = stockmerger.getHash_store2();

			if (hash_str1.containsKey(affect2)) {
				String tmcv = hash_str1.get(affect2).toString();
				stockmerger.setTmcv2(tmcv);
			}
			if (hash_str2.containsKey(affect2)) {
				String div = hash_str2.get(affect2).toString();
				stockmerger.setDivisor2(div);
			}

			// get new values from DB
			String query = ConnectInit.queries
					.getProperty("get_latest_index_value_index_wise");
			rs = ListTypeClass1.getResult12(merge_con, query, affect2);
			rs.next();
			stockmerger.setNewtmcv2(rs.getString("tmcv"));
			stockmerger.setNewdivisor2(rs.getString("divisor"));
			stockmerger.setIndexval2(rs.getString("index_closing_value"));
			rs.close();
			tmcv_div2(stockmerger);
		} catch (Exception e) {
			Logging.error("Error=" + e.getMessage());
		} finally {
			try {
				if (merge_con != null)
					merge_con.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// this method is used to display proper values for Merger company Affected
	// indices
	public static void tmcv_div1(StockMergerForm stockMerger) {
		try {
			// AdjustDecimal adj=new AdjustDecimal();
			AdjustDecimal adj = ConnectInit.getAdjustDecimal();
			String oldtmcv = stockMerger.getTmcv1();
			oldtmcv = AdjustDecimal.ArrangeAsNumeric(oldtmcv);
			stockMerger.setTmcv1(oldtmcv);

			String odiv = stockMerger.getDivisor1();
			odiv = AdjustDecimal.ArrangeAsNumeric(odiv);
			stockMerger.setDivisor1(odiv);

			String ntmcv = stockMerger.getNewtmcv1();
			ntmcv = adj.shareholdingpatt(ntmcv); // Remove E power
			ntmcv = AdjustDecimal.ArrangeAsNumeric(ntmcv); // assign ','
			stockMerger.setNewtmcv1(ntmcv);

			String ndiv = stockMerger.getNewdivisor1();
			ndiv = adj.shareholdingpatt(ndiv);
			ndiv = AdjustDecimal.ArrangeAsNumeric(ndiv);
			stockMerger.setNewdivisor1(ndiv);
		} catch (Exception e) {
			Logging.error("Error=" + e.getMessage());
		}
	}

	// this method is used to display proper values for Acquiring company
	// Affected indices
	public static void tmcv_div2(StockMergerForm stockMerger) {
		try {
			// AdjustDecimal adj=new AdjustDecimal();
			AdjustDecimal adj = ConnectInit.getAdjustDecimal();
			String oldtmcv = stockMerger.getTmcv2();
			oldtmcv = AdjustDecimal.ArrangeAsNumeric(oldtmcv);
			stockMerger.setTmcv2(oldtmcv);

			String odiv = stockMerger.getDivisor2();
			odiv = AdjustDecimal.ArrangeAsNumeric(odiv);
			stockMerger.setDivisor2(odiv);

			String ntmcv = stockMerger.getNewtmcv2();
			ntmcv = adj.shareholdingpatt(ntmcv); // Remove E power
			ntmcv = AdjustDecimal.ArrangeAsNumeric(ntmcv); // assign ','
			stockMerger.setNewtmcv2(ntmcv);

			String ndiv = stockMerger.getNewdivisor2();
			ndiv = adj.shareholdingpatt(ndiv);
			ndiv = AdjustDecimal.ArrangeAsNumeric(ndiv);
			stockMerger.setNewdivisor2(ndiv);
		} catch (Exception e) {
			Logging.error("Error=" + e.getMessage());
		}
	}

	// this method is used to make all temporary change into permanent one
	public static void commit_change(StockMergerForm stockmerger) {
		Connect connect = ConnectInit.getConnect();
		Connection merge_con = null;
		ResultSet rs = null;
		try {
			// Connect connect = ConnectInit.getConnect();
			merge_con = connect.getConnectionForMergerTransaction();
			merge_con.commit();

			String query = ConnectInit.queries
					.getProperty("stock_details_for_ca");
			rs = ListTypeClass1.getResult12(merge_con, query,
					stockmerger.getStock1());
			try {
				stockmerger.setStkResult1(rs);
			} catch (Exception e) {
				Logging.error(" ListTypeClass1: Error  in result set "
						+ e.getMessage());
			}
		} catch (Exception e) {
			Logging.debug("Error=" + e.getMessage());
		} finally {
			try {
				if (merge_con != null)
					merge_con.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void finalize() {
		Logging.debug("In finalize method");
		try {
			Connect.conForMergerTransaction.close();
			Connect.conForMergerTransaction = null;
		} catch (Exception e) {
			Logging.error("Error =" + e.getMessage());
		}
	}
}
