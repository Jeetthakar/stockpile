/*
 * Created on Sep 11, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import harrier.income.com.entities.CFormula;
import harrier.income.com.masters.StockMasterForm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

import com.harrier.initializeation.ConnectInit;

//import java.io.FileInputStream;

/**
 * @author pranoti
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class QueryClass1 {
	static Logger Logging = Logger.getLogger(QueryClass1.class);
	private static Connection con = null;
	private static String identifier_code;
	public static String stockmasterold = null;
	public static String stockadr_gdr = null;
	public static String crsidentifier_code = null;
	public static String tkridentifier_code = null;
	public static String cspidentifier_code = null;
	public static String isinidentifier_code = null;
	public static String excidentifier_code = null;
	public static String ricidentifier_code = null;
	public static String sdlidentifier_code = null;
	static Connect con1 = ConnectInit.getConnect();

	/*
	 * private static void getConnection() { if(con == null) con =
	 * con1.getConnection(); }
	 */

	public static void InsertIntoScrips(Connection Hist_con,
			String scrip_query, String face_query, String iwf_query,
			ActionForm form) {
		StockMasterForm form1 = (StockMasterForm) form;
		try {
			String stk_type = form1.getS_stockType();
			Logging.debug("Inside insert Stock Master");
			int exc_id = (int) Integer.parseInt(form1.getS_stockExchange());
			Logging.debug("Exchange id is " + exc_id);
			String query1 = null;
			boolean flag = true;
			String stk_id = null;
			int s_val;
			int r_val;
			s_val = Integer.parseInt(form1.getS_stockType());// chkStocktype(form);//for
																// stock type
			String act = form1.getB_isActive();
			Logging.debug("active=" + act);
			String act_val = null;
			if (act == null) {
				act_val = "N";
			} else
				act_val = "Y";

			// --------Insert into scrips-------------

			PreparedStatement psmt = Hist_con.prepareStatement(scrip_query);
			Logging.debug("in query");
			// Statement stmt = Hist_con.createStatement();
			ResultSet rs = null;
			int para = 0;
			String nextval = ConnectInit.queries
					.getProperty("get_sequence_stock_id");
			para = ListTypeClass1.Select_nextval(Hist_con, nextval);
			form1.setS_stockID(String.valueOf(para));
			psmt.setInt(1, para);
			psmt.setString(2, form1.getS_countryName());
			psmt.setString(3, form1.getS_stockExchange());
			psmt.setInt(4, s_val);// form1.getS_stockType())
			psmt.setString(5, form1.getS_stockCurrency());
			psmt.setString(6, "1");
			psmt.setString(7, form1.getB_tkr());
			psmt.setString(8, form1.getS_stockName().trim());
			psmt.setString(9, form1.getD_listingDate());
			psmt.setString(10, form1.getB_ric());
			psmt.setString(11, form1.getB_crisil());
			psmt.setString(12, form1.getB_isn());
			psmt.setString(13, form1.getB_sdl());
			psmt.setString(14, null);
			psmt.setString(15, null);
			psmt.setString(16, form1.getS_marketLot());
			psmt.setString(17, null);
			psmt.setString(18, null);
			psmt.setString(19, act_val);
			psmt.setString(20, null);
			psmt.executeUpdate();
			psmt.close();
			// insert into face values
			psmt = Hist_con.prepareStatement(face_query);
			psmt.setString(1, form1.getS_stockID());
			psmt.setString(2, form1.getD_listingDate());
			psmt.setString(3, form1.getF_faceValue());
			psmt.setString(4, "Y");
			psmt.executeUpdate();
			psmt.close();
			// /-------------Complete---------------------
			// insert into investible factor
			// (SCRIP_ID,INVESTIBLE_DATE,COMP_HOLDING,GOVT_HOLDING,FOREIGN_HOLDING,INVESTIBLE_FACTOR,EVENT_ID,CURRENT_FLAG)
			psmt = Hist_con.prepareStatement(iwf_query);
			psmt.setString(1, form1.getS_stockID());
			psmt.setString(2, form1.getD_listingDate());
			psmt.setString(3, null);
			psmt.setString(4, null);
			psmt.setString(5, null);
			psmt.setString(6, form1.getD_iwf());
			// psmt.setString(7,null);
			psmt.setString(7, "Y");
			psmt.executeUpdate();
			psmt.close();
			// /-------------Complete---------------------
			form1.setTrans_flag(false);
		} catch (Exception e) {
			Logging.error("Error while creating prepared Statemwent"
					+ e.getMessage());
			form1.setTrans_flag(true);
		}
	}

	public static void get_newIssue_price(Connection Hist_con,
			StockMasterForm form1) {
		try {
			// CFormula cf = new CFormula();
			CFormula cf = ConnectInit.getCFormula();
			// insert into daily scrip prices
			String insert_query = ConnectInit.queries
					.getProperty("insert_dailyscrip_prices");

			// get stock prices
			Logging.debug("issued shares===" + form1.getF_issuedShares());
			String get_price = ConnectInit.queries
					.getProperty("get_newissue_pridetail_asc");
			ResultSet rs = ListTypeClass1.getResult_event(Hist_con, get_price,
					form1.getB_tkr().toUpperCase(), form1.getS_stockExchange());
			while (rs.next()) {
				Logging.debug("rs===in start==" + rs);
				double newmcv = 0.0;// cf.calMarketCap(rs.getDouble(2),Long.parseLong(form1.getS_marketLot()),1,Long.parseLong(form1.getF_issuedShares()),Double.parseDouble(form1.getD_iwf()));
				// insert into Stock price daily
				insert_stkpri_daily(Hist_con, insert_query, rs, form1, newmcv);
				Logging.debug("insert price suucessfully");
				// insert into intra day stock prices
				// insert_intrastkpri_daily(insert_intra,rs,form1);
				Logging.debug("insert intraprice suucessfully");
				Logging.debug("rs=====" + rs);
			}
			rs.close();
			// delete from new issues
			String del_query = ConnectInit.queries
					.getProperty("delete_newIssues");
			PreparedStatement pstmt = Hist_con.prepareStatement(del_query);
			// pstmt.setString(1,form1.getS_stockName()); commented by sudhir
			// 04Oct05 to allow deletion on ticker and se_id
			pstmt.setString(1, form1.getB_tkr());
			pstmt.setString(2, form1.getS_stockExchange());
			pstmt.executeUpdate();
			pstmt.close();
			Logging.debug("delete price suucessfully");
			form1.setTrans_flag(false);
		} catch (Exception e) {
			form1.setTrans_flag(true);
			Logging.error("Error=" + e.getMessage());
		}
	}

	// commented by pranoti 6SEP05 as per IISL requirement()
	public static void insert_stkpri_daily(Connection Hist_con, String query,
			ResultSet rs, StockMasterForm form1, double mcv) {
		try {
			// insert into stock price daily
			Corporate corp = new Corporate();
			PreparedStatement pstmt = Hist_con.prepareStatement(query);
			pstmt.setString(1, form1.getS_stockID());
			pstmt.setString(2, rs.getString(1));
			pstmt.setString(3, form1.getB_tkr());
			pstmt.setDouble(4, rs.getDouble(3));
			pstmt.setDouble(5, rs.getDouble(4));
			pstmt.setDouble(6, rs.getDouble(5));
			pstmt.setDouble(7, rs.getDouble(2));
			pstmt.setDouble(8, rs.getDouble(2));
			pstmt.setDouble(9, mcv);
			pstmt.setDouble(10, mcv);
			corp.setS_stock(form1.getS_stockID());
			double exc_val = UpdateCorp.get_usd_exchange(Hist_con, con1, corp);
			double newmcv1 = mcv * exc_val;
			pstmt.setDouble(11, newmcv1);
			pstmt.setDouble(12, newmcv1);
			pstmt.setDouble(13, rs.getDouble(2));
			pstmt.setString(14, null);
			pstmt.setString(15, rs.getString(7));
			pstmt.setDouble(16, rs.getDouble(6));
			pstmt.setDouble(17, rs.getDouble(8)); // Net_Turnover value ---Rahul

			pstmt.setString(18, null);
			pstmt.setString(19, null);
			pstmt.setString(20, null);
			pstmt.executeUpdate();
			pstmt.close();
			Logging.debug("data inserted");
		} catch (Exception e) {
			Logging.error("Error r2=" + e.getMessage());
		}
	}

	/**
	 * insert the stock details in stock_master,stock_master_history and
	 * identifier_codes.
	 */
	public static void insertIntoStockMaster(String query, String qry1,
			ActionForm form) {

		Connection connection = null;
		Connect c = ConnectInit.getConnect();
		StockMasterForm form1 = (StockMasterForm) form;
		try {
			// getConnection();

			PreparedStatement pseq1 = null;
			ResultSet seqno1 = null;
			int user_id = 0;

			if (connection == null) {
				connection = c.getdbConnection();
			}

			String stk_type = form1.getS_stockType();
			Logging.debug("Inside insert Stock Master");
			int exc_id = (int) Integer.parseInt(form1.getS_stockExchange());
			Logging.debug("Exchange id is " + exc_id);
			PreparedStatement pst = connection
					.prepareStatement(ConnectInit.queries
							.getProperty("get_identifier_code"));
			pst.setInt(1, exc_id);
			ResultSet rst = pst.executeQuery();
			while (rst.next()) {
				identifier_code = rst.getString(1);
			}
			rst.close();
			pst.close();

			Logging.debug("Identifier code " + identifier_code);
			String query1 = null;
			boolean flag = true;
			String stk_id = null;
			int s_val;
			int r_val;

			s_val = chkStocktype(form);// for stock type
			r_val = chkRate(form);// for rating
			String act = form1.getB_isActive();
			Logging.debug("active=" + act);
			String act_val = null;
			if (act == null) {
				act_val = "n";
			} else
				act_val = "y";
			PreparedStatement psmt = connection.prepareStatement(query);
			Logging.debug("in query");
			Statement stmt = connection.createStatement();
			ResultSet rs = null;
			int para = 0;
			rs = stmt.executeQuery(ConnectInit.queries
					.getProperty("get_sequence_stock_id"));
			while (rs.next()) {
				Logging.debug("stock max" + rs.getInt(1));
				para = rs.getInt(1);
				form1.setS_stockID(String.valueOf(rs.getInt(1)));
			}
			stmt.close();

			psmt.setInt(1, para);
			Logging.debug("para is " + para);
			rs.close();
			psmt.setInt(2, s_val);// form1.getS_stockType());
			// psmt.setString(3,form1.getS_companyName().trim());
			int cid = Integer.parseInt(form1.getS_companyName().trim());
			psmt.setInt(3, cid);
			float iwf = Float.parseFloat(form1.getD_iwf());
			psmt.setFloat(4, iwf);
			// psmt.setString(4,form1.getD_iwf());
			psmt.setFloat(5, Float.parseFloat(form1.getF_issuedShares()));
			psmt.setFloat(24, Float.parseFloat(form1.getF_issuedShares()));
			psmt.setString(6, form1.getS_stockName().trim());
			psmt.setFloat(7, Float.parseFloat(form1.getF_faceValue()));
			psmt.setString(8, form1.getD_listingDate());
			psmt.setString(9, form1.getS_growthValueType());
			if (r_val == 0) {
				psmt.setInt(10, 0);// form1.getS_ratingCode());
			} else {
				psmt.setInt(10, r_val);// form1.getS_ratingCode());
			}
			psmt.setString(11, form1.getB_global100());
			psmt.setFloat(12, Float.parseFloat(form1.getF_alertPercent()));
			psmt.setFloat(13, Float.parseFloat(form1.getF_rejectionPercent()));
			psmt.setString(14, form1.getB_withHoldingTaxApplicable());
			String without = form1.getF_withholdingTaxPercent();
			if (without.equals("")) {
				psmt.setFloat(15, 0);
			} else {
				psmt.setFloat(15,
						Float.parseFloat(form1.getF_withholdingTaxPercent()));
			}
			psmt.setInt(16, Integer.parseInt(form1.getS_marketLot()));
			psmt.setInt(17, Integer.parseInt(form1.getS_stockExchange()));
			psmt.setInt(18, Integer.parseInt(form1.getS_countryName()));
			psmt.setString(19, act_val);// 11
			psmt.setInt(20, Integer.parseInt(form1.getS_stockCurrency()));
			psmt.setString(21, form1.getB_isPriceForLot());
			psmt.setFloat(22, Float.parseFloat(form1.getD_paidValue()));
			psmt.setFloat(23, 0);

			/*
			 * LogonForm form2= new LogonForm(); //String
			 * abc=form2.getUsername() ; String abc="sudhir";
			 * System.out.println("User is is>>"+abc); pseq1 =
			 * Connect.con.prepareStatement
			 * (con1.queries.getProperty("get_user_id_users"));
			 * pseq1.setString(1,abc); seqno1 = pseq1.executeQuery();
			 * while(seqno1.next()) { user_id = seqno1.getInt(1);
			 * System.out.println("user_id>>>>>>>>>>="+user_id); }
			 * psmt.setInt(24,user_id);
			 */
			psmt.executeUpdate();
			Logging.debug("insert into stock master" + psmt);
			// generate string containg new values for the stock.
			String stockmaster = s_val + "," + form1.getS_companyName() + ","
					+ form1.getD_iwf() + "," + form1.getF_issuedShares() + ","
					+ form1.getS_stockName() + "," + form1.getF_faceValue()
					+ "," + form1.getD_listingDate() + ","
					+ form1.getS_growthValueType() + ","
					+ form1.getB_global100() + "," + form1.getF_alertPercent()
					+ "," + form1.getF_rejectionPercent() + ","
					+ form1.getB_withHoldingTaxApplicable() + ","
					+ form1.getB_withHoldingTaxApplicable() + ","
					+ form1.getF_withholdingTaxPercent() + ","
					+ form1.getS_marketLot() + "," + form1.getS_stockExchange()
					+ "," + form1.getS_countryName() + "," + act_val + ","
					+ form1.getS_stockCurrency() + ","
					+ form1.getB_isPriceForLot() + "," + form1.getD_paidValue();
			// maintain audit trail for stock_master insert with old and new
			// values for stock.
			AuditTrail.StoreTableInsertUpdate("58", null, stockmaster);
			Logging.info("Stock values inserted in stock master");
			SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
			Date dt = new Date();
			String l_date = fr.format(dt).toString();
			// insert into stock_master_history.
			stmt = connection.createStatement();
			rs = stmt.executeQuery(ConnectInit.queries
					.getProperty("get_sequence_stock_mh_id"));
			rs.next();
			int smid = rs.getInt(1);
			rs.close();
			psmt = connection.prepareStatement(qry1);
			String sid = form1.getS_stockID();
			psmt.setInt(1, smid);
			psmt.setInt(2, Integer.parseInt(sid));
			psmt.setInt(3, s_val);// form1.getS_stockType());
			// System.out.println(s_val);

			String cnm = form1.getS_companyName();
			psmt.setInt(4, Integer.parseInt(cnm));
			psmt.setFloat(5, Float.parseFloat(form1.getD_iwf()));
			psmt.setFloat(6, Float.parseFloat(form1.getF_issuedShares()));
			psmt.setString(7, form1.getS_stockName().trim());
			psmt.setFloat(8, Float.parseFloat(form1.getF_faceValue()));
			psmt.setString(9, form1.getD_listingDate());
			// System.out.println(form1.getS_growthValueType());
			psmt.setString(10, form1.getS_growthValueType());
			if (r_val == 0) {
				psmt.setInt(11, 0);// form1.getS_ratingCode());
			} else {
				psmt.setInt(11, r_val);// form1.getS_ratingCode());
			}
			psmt.setString(12, form1.getB_global100());
			psmt.setFloat(13, Float.parseFloat(form1.getF_alertPercent()));
			psmt.setFloat(14, Float.parseFloat(form1.getF_rejectionPercent()));
			psmt.setString(15, form1.getB_withHoldingTaxApplicable());
			String without1 = form1.getF_withholdingTaxPercent();
			if (without1.equals("")) {
				psmt.setFloat(16, 0);
			} else {
				psmt.setFloat(16,
						Float.parseFloat(form1.getF_withholdingTaxPercent()));
			}

			psmt.setInt(17, Integer.parseInt(form1.getS_marketLot()));
			psmt.setInt(18, Integer.parseInt(form1.getS_stockExchange()));
			psmt.setInt(19, Integer.parseInt(form1.getS_countryName()));
			psmt.setString(20, act_val);
			psmt.setInt(21, Integer.parseInt(form1.getS_stockCurrency()));
			psmt.setString(22, form1.getB_isPriceForLot());
			psmt.setFloat(23, Float.parseFloat(form1.getD_paidValue()));
			psmt.setString(24, l_date);
			// psmt.setInt(25,0);
			// psmt.setInt(26,user_id);
			psmt.executeUpdate();
			Logging.info("Stock values inserted in stock master history");

			// generate string containg new values for the stock which are
			// inserted in stock_master_history.
			String stockmasterhistory = s_val + "," + form1.getS_companyName()
					+ "," + form1.getD_iwf() + "," + form1.getF_issuedShares()
					+ "," + form1.getS_stockName() + ","
					+ form1.getF_faceValue() + "," + form1.getD_listingDate()
					+ "," + form1.getS_growthValueType() + ","
					+ form1.getB_global100() + "," + form1.getF_alertPercent()
					+ "," + form1.getF_rejectionPercent() + ","
					+ form1.getB_withHoldingTaxApplicable() + ","
					+ form1.getF_withholdingTaxPercent() + ","
					+ form1.getS_marketLot() + "," + form1.getS_stockExchange()
					+ "," + form1.getS_countryName() + "," + act_val + ","
					+ form1.getS_stockCurrency() + ","
					+ form1.getB_isPriceForLot() + "," + form1.getD_paidValue()
					+ "," + l_date + "," + null;
			// maintain audit trail for stock_master_hostory insert with old and
			// new values for stock.
			AuditTrail.StoreTableInsertUpdate("60", null, stockmasterhistory);

			query1 = null;
			query1 = ConnectInit.queries.getProperty("get_max_stock_id");
			String stk_val = null;
			Statement stmt2 = connection.createStatement();
			ResultSet rs2 = stmt2.executeQuery(query1);
			while (rs2.next()) {
				stk_val = rs2.getString(1);
			}
			stmt2.close();

			Logging.debug("stk_val is " + stk_val);
			int stk_id_code = 0;
			String sdl = form1.getB_sdl();
			Logging.debug("sdl is " + sdl);
			String isin = form1.getB_isn();
			Logging.debug("isin is " + isin);
			String ric = form1.getB_ric();
			Logging.debug("ric is " + ric);
			String cusip = form1.getB_csp();
			Logging.debug("cusip is " + cusip);
			String exc = form1.getB_exc_code();
			Logging.debug("exc is " + exc);
			String tkr = form1.getB_tkr();
			Logging.debug("tkr is " + tkr);
			String crisil = form1.getB_crisil();
			Logging.debug("crisil is " + crisil);

			if ((sdl == null) || (sdl.equals("")))// no identifier code exist.
			{
				Logging.debug("no code");
			} else {
				String sdl_id = getIdentifierCodeId("sedol");
				stockidentifier(stk_val, sdl.trim(), sdl_id, stk_type); // insert
																		// values
																		// into
																		// identifier_code
																		// for
																		// sedol.
			}
			if ((ric == null) || (ric.equals("")))// no sedol code exist.
			{
				Logging.debug("no code");
			} else {
				String ric_id = getIdentifierCodeId("ric");
				stockidentifier(stk_val, ric.trim(), ric_id, stk_type); // insert
																		// values
																		// into
																		// identifier_code
																		// for
																		// ric.
			}
			if ((isin == null) || (isin.equals("")))// no isin code exist.
			{
				Logging.debug("no code");
			} else {
				String isin_id = getIdentifierCodeId("isin");
				Logging.debug("isin_id is " + isin_id);
				stockidentifier(stk_val, isin.trim(), isin_id, stk_type); // insert
																			// values
																			// into
																			// identifier_code
																			// for
																			// isin.
			}
			if ((cusip == null) || (cusip.equals("")))// no cusip code exist.
			{
				Logging.debug("no code");
			} else {
				String csp_id = getIdentifierCodeId("cusip");
				stockidentifier(stk_val, cusip.trim(), csp_id, stk_type); // insert
																			// values
																			// into
																			// identifier_code
																			// for
																			// cusip.
			}
			if ((exc == null) || (exc.equals("")))// no exchange code exist.
			{
				Logging.debug("no code");
			} else {
				exc_id = (int) Integer.parseInt(form1.getS_stockExchange());
				Logging.debug("Exchange id is " + exc_id);
				PreparedStatement pst11 = connection
						.prepareStatement(ConnectInit.queries
								.getProperty("get_identifier_code"));
				pst11.setInt(1, exc_id);
				ResultSet rst11 = pst11.executeQuery();
				while (rst11.next()) {
					identifier_code = rst11.getString(1);
				}
				rst11.close();
				pst11.close();
				stockidentifier(stk_val, exc.trim(), identifier_code, stk_type);// insert
																				// values
																				// into
																				// identifier_code
																				// for
																				// exchange
																				// code.
			}
			if ((tkr == null) || (tkr.equals("")))// no ticker code exist.
			{
				Logging.debug("no code");
			} else {
				String tkr_id = getIdentifierCodeId("ticker");
				stockidentifier(stk_val, tkr.trim(), tkr_id, stk_type);// insert
																		// values
																		// into
																		// identifier_code
																		// for
																		// ticker.
			}
			if ((crisil == null) || (crisil.equals("")))// no crisil code exist.
			{
				Logging.debug("no code");
			} else {
				String crisil_id = getIdentifierCodeId("crisil");
				stockidentifier(stk_val, crisil.trim(), crisil_id, stk_type);
			}
			// insert values in stock_adr_gdr_ratio.

			query1 = null;
			String adr = form1.getS_adrRatio();
			String adr1 = form1.getS_adrRatio1();
			if ((adr == null || adr.equals(""))
					|| (adr1 == null || adr1.equals(""))) {// no adr values
															// present for the
															// stock.
				// Logging.getDebug("n adr value null ");
			} else {
				query1 = ConnectInit.queries
						.getProperty("get_stock_adr_gdr_id ");
				int stk_adr_gdr_id = 0;
				Statement stmt3 = connection.createStatement();
				ResultSet rs3 = stmt3.executeQuery(query1);
				while (rs3.next()) {
					stk_adr_gdr_id = rs3.getInt(1);
				}
				rs3.close();
				String qry11 = ConnectInit.queries
						.getProperty("insert_into_stock_adr_gdr_ratio");
				psmt = connection.prepareStatement(qry11);
				String s_id = form1.getS_stockID();
				psmt.setInt(1, stk_adr_gdr_id);
				psmt.setString(2, s_id);
				String adr_gdr_id = form1.getAdr_gdr_id();
				psmt.setString(3, adr_gdr_id);
				psmt.setString(4, form1.getS_adrRatio());
				psmt.setString(5, form1.getS_adrRatio1());
				psmt.executeUpdate();
				String stktype = stk_adr_gdr_id + "," + s_id + "," + adr_gdr_id
						+ "," + form1.getS_adrRatio() + ","
						+ form1.getS_adrRatio1();

				// maintain audit trail for stock_adr_gdr_ratios insert.
				psmt.close();
				AuditTrail.StoreTableInsertUpdate("61", null, stktype);
				Logging.info("adr gdr values inserted  sucessfully");
			}
			form1.setTrans_flag(false);
			Logging.debug("final");
		} catch (Exception e) {
			Logging.error("Error while creating prepared Statemwent"
					+ e.getMessage());
			form1.setTrans_flag(true);
		} finally {
			try {
				if (connection != null)
					connection.close();

			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error("Error : Unable to close Connection "
							+ ex.getMessage());
				}
				Logging.error("Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
	}

	/**
	 * to get the identifier code id if identifier code value is passed.
	 * 
	 * @param identifier_code_value
	 * @return
	 */
	public static String getIdentifierCodeId(String identifier_code_value) {
		String id = "0";
		Connection connection = null;
		Connect c = ConnectInit.getConnect();
		try {
			String qry = null;
			// getConnection();
			if (connection == null) {
				connection = c.getdbConnection();
			}
			qry = ConnectInit.queries.getProperty("get_identifier_code_id");
			ResultSet rs1 = connection.createStatement().executeQuery(qry);
			while (rs1.next()) {
				if (identifier_code_value
						.equals(rs1.getString(2).toLowerCase())) {
					id = rs1.getString(1);
				}

			}
			rs1.close();
		} catch (Exception e) {
			Logging.error("Errro while finding identifier_code_id "
					+ e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error("Error : Unable to close Connection "
							+ ex.getMessage());
				}
				Logging.error("Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		return id;
	}

	/**
	 * to set the old values of stock for entering into audit trail.
	 * 
	 * @param stockid
	 */
	public static void setOldValues(String stockid) {
		// Connection Changed from Static to Local on 25 AUG 06 by P.Bhende
		Connection con = null;
		SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
		Date dt = new Date();
		String l_date = fr.format(dt).toString();
		try {
			Connect c = ConnectInit.getConnect();
			try {
				if (con == null) {
					con = c.getdbConnection();
				}
			} catch (Exception e) {
				Logging.error("DATABASE ERROR" + e.getMessage());
			}
			String query12 = ConnectInit.queries
					.getProperty("stock_details_for_stock_master");
			ResultSet rs = StockResult.resultStock(con, query12, stockid);
			while (rs.next()) {
				String active = null;
				if ((rs.getString("is_active")).equals("on")) {
					active = "n";
				} else {
					active = "y";
				}
				stockmasterold = rs.getString("stock_type_name") + ","
						+ rs.getString("company_name") + ","
						+ rs.getString("iwf") + "," + rs.getString("tis") + ","
						+ rs.getString("stock_name") + ","
						+ rs.getString("face_value") + ","
						+ rs.getString("listing_date") + ","
						+ rs.getString("growth_or_value") + ","
						+ rs.getString("global100") + ","
						+ rs.getString("alert_percentage") + ","
						+ rs.getString("rejection_percentage") + ","
						+ rs.getString("witholding_tax_applicable") + ","
						+ rs.getString("witholding_tax_percent") + ","
						+ rs.getString("market_lot") + ","
						+ rs.getString("stock_ex_name") + ","
						+ rs.getString("country_name") + "," + active + ","
						+ rs.getString("stock_currency_id") + ","
						+ rs.getString("is_price_for_lot") + ","
						+ rs.getString("paid_value");

				sdlidentifier_code = (String) StockResult.identifier_list
						.get(0) + "," + rs.getString("stock_type_name");
				isinidentifier_code = (String) StockResult.identifier_list
						.get(1) + "," + rs.getString("stock_type_name");
				ricidentifier_code = (String) StockResult.identifier_list
						.get(2) + "," + rs.getString("stock_type_name");
				crsidentifier_code = (String) StockResult.identifier_list
						.get(3) + "," + rs.getString("stock_type_name");
				cspidentifier_code = (String) StockResult.identifier_list
						.get(4) + "," + rs.getString("stock_type_name");
				excidentifier_code = (String) StockResult.identifier_list
						.get(5) + "," + rs.getString("stock_type_name");
				tkridentifier_code = (String) StockResult.identifier_list
						.get(6) + "," + rs.getString("stock_type_name");
			}
			String query = ConnectInit.queries
					.getProperty("get_adr_gdr_detail_for_stock");// to get the
																	// adr gdr
																	// from to
																	// ratio.
			Logging.debug("Param value is " + stockid + "query=====" + query);
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, stockid);
			ResultSet rsadrgdr = stmt.executeQuery();
			while (rsadrgdr.next()) {
				stockadr_gdr = rsadrgdr.getString("adr_gdr_ratio_from") + ","
						+ rsadrgdr.getString("adr_gdr_ratio_to") + ","
						+ stockid;
			}
		} catch (SQLException e) {
			Logging.error(" SQL ERROR :" + e.getMessage());
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
	 * to get old stock detail values.
	 * 
	 * @return
	 */
	public static String getOldValuesSm() {
		return stockmasterold;
	}

	/**
	 * get old sedol identifier code values.
	 * 
	 * @return
	 */
	public static String getOldSdlIdentifier() {
		return sdlidentifier_code;
	}

	/**
	 * get old ric identifier code values.
	 * 
	 * @return
	 */
	public static String getOldRicIdentifier() {
		return ricidentifier_code;
	}

	/**
	 * get old exchange code values.
	 * 
	 * @return
	 */
	public static String getOldExcIdentifier() {
		return excidentifier_code;
	}

	/**
	 * get old isin code values.
	 * 
	 * @return
	 */
	public static String getOldIsinIdentifier() {
		return isinidentifier_code;
	}

	/**
	 * get old cusip code values.
	 * 
	 * @return
	 */
	public static String getOldCspIdentifier() {
		return cspidentifier_code;
	}

	/**
	 * get old ticker code values.
	 * 
	 * @return
	 */
	public static String getOldTkrIdentifier() {
		return tkridentifier_code;
	}

	/**
	 * get old adr gdr ratios values.
	 * 
	 * @return
	 */
	public static String getOldValuesAdr() {
		return stockadr_gdr;
	}

	/**
	 * get old crisil code values.
	 * 
	 * @return
	 */
	public static String getOldCrsIdentifier() {
		return crsidentifier_code;
	}

	/**
	 * get adr_gdr_id if adr_gdr_name is inputed.
	 * 
	 * @param adr_gdr_name
	 * @return
	 */
	public static int getadrgdrId(String adr_gdr_name) {
		int id = 0;
		Connection connection = null;
		Connect con = ConnectInit.getConnect();
		try {
			// Change by Manoj Adekar for Dynamic connection using
			// getdbConnection() instead of getConnection()
			if (connection == null) {
				connection = con.getdbConnection();
			}
			try {
				String qry = null;
				// getConnection();
				qry = ConnectInit.queries.getProperty("get_adr_gdr_id");
				PreparedStatement psmt1 = connection.prepareStatement(qry);
				psmt1.setString(1, adr_gdr_name);
				ResultSet rs1 = psmt1.executeQuery();
				while (rs1.next()) {
					// if(adr_gdr_name.equals(rs1.getString(2).toLowerCase()))
					// {
					id = rs1.getInt(1);
					// }

				}
			} catch (Exception e) {
				Logging.error("Errro while finding adr_gdr_id "
						+ e.getMessage());
			}

		} catch (Exception e) {
			Logging.debug("Error :" + e);
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
		return id;
	}

	/**
	 * check for spaces in the inputed string.
	 * 
	 * @param checkname
	 * @return
	 */
	public static boolean chkspace(String checkname) {
		boolean flag = true;
		// Logging.getDebug("Inside check space"+checkname.indexOf(32));
		if (checkname.indexOf(32) != -1) {
			flag = false;
		}

		return flag;
	}

	/**
	 * insert the stock values in stock_identifier_code.
	 * 
	 * @param stockID
	 * @param ident_val
	 * @param ident_id
	 * @param series
	 */
	public static void stockidentifier(String stockID, String ident_val,
			String ident_id, String series) {
		Connection connection = null;
		try {
			// getConnection();

			if (connection == null) {
				connection = con1.getdbConnection();
			}
			String qry = ConnectInit.queries
					.getProperty("get_stock_identifier_code_id");
			ResultSet rs1 = connection.createStatement().executeQuery(qry);
			rs1.next();
			long stk_ident_code = rs1.getLong(1);
			rs1.close();
			Logging.debug("stk_ident_code " + stk_ident_code + " ident_id is "
					+ ident_id + " stockID " + stockID + " ident_val "
					+ ident_val + " series " + series);
			String query1 = ConnectInit.queries
					.getProperty("insert_into_stock_identifier_codes");
			PreparedStatement psmt1 = connection.prepareStatement(query1);
			psmt1.setLong(1, stk_ident_code);
			Logging.debug("after stk_ident_code" + stk_ident_code);
			psmt1.setInt(2, Integer.parseInt(ident_id));
			Logging.debug("after ident_id" + ident_id);
			psmt1.setInt(3, Integer.parseInt(stockID));
			Logging.debug("after stockID" + stockID);
			psmt1.setString(4, ident_val);
			Logging.debug("after ident_val" + ident_val);
			psmt1.setString(5, series);
			Logging.debug("after series" + series);
			psmt1.executeUpdate();
			String values = (new Long(stk_ident_code).toString()) + ","
					+ ident_id + "," + stockID + "," + ident_val + "," + series;
			Logging.debug("after values" + values);
			// maintain audit trail for stock_identifier_codes insert.
			AuditTrail.StoreTableInsertUpdate("62", null, values);
			Logging.info("identifer code values inserted in stock identifier codes sucessfully");
		} catch (Exception e) {
			Logging.error(" Errro in identifier code insert : "
					+ e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error("Error : Unable to close Connection "
							+ ex.getMessage());
				}
				Logging.error("Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
	}

	/**
	 * check for duplication of stock name.(If series(stock type ) is different
	 * in case of NSEI then duplicate stock name are allowed for other exchanges
	 * only single stock name in exchange are allowed).
	 * 
	 * @param stockname
	 * @param exchange
	 * @param type
	 * @param stockID
	 * @return
	 */
	public static boolean chkstkname(String stockname, String exchange,
			String type, String stockID) {
		boolean flg = true;
		Connection connection = null;
		try {
			String qry = null;
			// getConnection();
			if (connection == null) {
				connection = con1.getdbConnection();
			}

			qry = ConnectInit.queries.getProperty("check_stock_name");
			PreparedStatement psmt = connection.prepareStatement(qry);
			psmt.setString(1, exchange);
			ResultSet rs1 = psmt.executeQuery();
			while (rs1.next()) {
				String name = rs1.getString(2);
				if (name.equals(stockname))// stock name already exist in
											// exchange.
				{
					if ((stockID != null) && (stockID.equals(rs1.getString(1)))) {
						flg = true;
						break;
					}
					String qry12 = ConnectInit.queries
							.getProperty("select_type_from_stock_master");
					PreparedStatement psmt3 = connection
							.prepareStatement(qry12);
					psmt3.setString(1, stockname);
					ResultSet rs2 = psmt3.executeQuery();
					while (rs2.next()) {
						// Logging.getDebug("inside check name"+rs2.getString(1));
						if (!(type.equals(rs2.getString(1)))
								&& exchange.equals("84"))// check series for
															// NSEI exchange.
						{
							flg = true;
						} else {
							flg = false;
						}
						rs2.close();
						psmt3.close();
					}
					break;

				}

			}
			rs1.close();
			psmt.close();

		} catch (Exception e) {
			Logging.error("Errro while inserting copy of parent "
					+ e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		return flg;
	}

	/**
	 * check for isActive status of stock if it belongs to any index. to limit
	 * user to restrict to chage status if that stock belongs to any index.
	 * 
	 * @param isActive
	 * @param stockid
	 * @return
	 */
	public static boolean chkisActive(String isActive, String stockid) {
		boolean flag = true;
		Connection connection = null;
		try {
			String qry = null;
			// getConnection();
			if (connection == null) {
				connection = con1.getdbConnection();
			}
			Vector stkbelong = new Vector();
			stkbelong.clear();
			if (isActive != null && isActive.equals("on"))
				isActive = "y";
			String query = ConnectInit.queries
					.getProperty("affected_index_by_ca");
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, Integer.parseInt(stockid));
			ResultSet rs = stmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				stkbelong.add(i, rs.getString(1));
				i++;
				stkbelong.add(i, rs.getString(2));
				i++;
			}
			rs.close();
			stmt.close();
			// Logging.getDebug("stkbelong.size() is "+stkbelong.size());
			if (stkbelong.size() != 0) {
				String query1 = ConnectInit.queries
						.getProperty("get_is_active_value_for_stock");
				// Logging.getDebug("stockid value is "+stockid+"query====="+query1);
				PreparedStatement stmt1 = connection.prepareStatement(query1);
				stmt1.setInt(1, Integer.parseInt(stockid));
				ResultSet rs1 = stmt1.executeQuery();
				while (rs1.next()) {
					String is_active_data = rs1.getString(1);
					Logging.debug(" is_active_data is " + is_active_data
							+ " isActive is " + isActive);
					if (!(isActive.equals(is_active_data))) {
						flag = false;
					}
				}
				rs1.close();
				stmt1.close();
			}
		} catch (Exception e) {
			Logging.error("Error while checking for is Active "
					+ e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		return flag;
	}

	/**
	 * check for index name duplication.
	 * 
	 * @param indexname
	 * @return
	 */
	public static boolean chkIndexkname(String indexname, String use_user,
			String user_id, String query) {
		boolean flag = true;
		Connection connection = null;
		ResultSet rs = null;
		try {
			String qry = null;
			if (connection == null)
				connection = con1.getdbConnection();
			// System.out.println("exchange is "+exchange+"stock is "+stockname);
			if (use_user != null && use_user.equals("yes")) {
				try {
					PreparedStatement stmt = connection.prepareStatement(query);
					stmt.setString(1, user_id);
					rs = stmt.executeQuery();
					while (rs.next()) {
						String name = rs.getString(1);
						// System.out.println("stock from query is "+name);
						if (name.equalsIgnoreCase(indexname)) {
							flag = false;
						}
					}
				} catch (Exception ex) {
					Logging.error(" Error : " + ex.getMessage());
				}
			} else {
				query = ConnectInit.queries.getProperty("check_index_name");
				Statement stmt = connection.createStatement();
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					String name = rs.getString(1);
					// System.out.println("stock from query is "+name);
					if (name.equalsIgnoreCase(indexname)) {
						flag = false;
					}
				}
			}
			rs.close();
		} catch (Exception e) {
			Logging.error("Error while checking for is active "
					+ e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ex) {
				Logging.error(" Error : Unable to close connection "
						+ ex.getMessage());
			}
		}
		return flag;
	}

	/**
	 * check for index name duplication.
	 * 
	 * @param indexname
	 * @return
	 */
	public static boolean chkIndexkname(String indexname) {
		boolean flag = true;
		Connection connection = null;
		try {
			String qry = null;
			if (connection == null)
				connection = con1.getdbConnection();
			// System.out.println("exchange is "+exchange+"stock is "+stockname);
			String query = ConnectInit.queries.getProperty("check_index_name");
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String name = rs.getString(1);
				// System.out.println("stock from query is "+name);
				if (name.equalsIgnoreCase(indexname)) {
					flag = false;
				}
			}
		} catch (Exception e) {
			Logging.error("Error while checking for is active "
					+ e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ex) {
				Logging.error(" Error : Unable to close connection "
						+ ex.getMessage());
			}
		}
		return flag;
	}

	public ResultSet ReturnParentDetails(String s_parentIndex,
			Connection conforparentdeatils) {
		Logging.debug("ReturnParentDetails ");
		// if(con == null)
		// Connection conforparentdeatils = con1.getConnectionForTransaction();
		try {

			// String
			// query="select * from index_master where index_id='"+s_parentIndex+"'";
			String query = ConnectInit.queries
					.getProperty("query_copy_indexmaster");
			PreparedStatement pstmt1 = conforparentdeatils
					.prepareStatement(query);
			pstmt1.setString(1, s_parentIndex);
			Logging.debug(" Query in ReturnParentDetails" + pstmt1
					+ " and con is " + conforparentdeatils);
			ResultSet rs1 = pstmt1.executeQuery();

			return rs1;

		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
			Logging.debug(e);
		}
		return null;
	}

	public ResultSet ReturnParentDetails(String s_parentIndex) {
		Logging.debug("ReturnParentDetails ");
		// if(con == null)
		try {
			Connection conforparentdeatils = con1.getConnectionForTransaction();

			// String
			// query="select * from index_master where index_id='"+s_parentIndex+"'";
			String query = ConnectInit.queries
					.getProperty("query_copy_indexmaster");
			PreparedStatement pstmt1 = conforparentdeatils
					.prepareStatement(query);
			pstmt1.setString(1, s_parentIndex);
			// Logging.getDebug("Query in ReturnParentDetails"+query+
			// " and con is "+conforparentdeatils);
			ResultSet rs1 = pstmt1.executeQuery();

			return rs1;

		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace()
			Logging.debug(e);
		}
		return null;
	}

	/**
	 * check for duplication of exchange code.(exchange code can be duplicate in
	 * case of NSEI exchange if series is different for that stock).
	 * 
	 * @param identifier_code
	 * @param exchange
	 * @param type
	 * @param stockid
	 * @return
	 */
	public static boolean chkIdentifierCode(String identifier_code,
			String exchange, String type, String stockid) {
		boolean flg = true;
		Connection connection = null;
		Connect con = ConnectInit.getConnect();
		try {
			// Change by Manoj Adekar for Dynamic connection using
			// getdbConnection() instead of getConnection()
			if (connection == null) {
				connection = con.getdbConnection();
			}
			try {
				String qry = null, qry1 = null;
				String idcode = null;
				// getConnection();

				// Logging.getDebug("before query identifier_code is "+identifier_code+" idcode "+idcode+" stockid "+stockid);
				if (stockid != null) {
					qry1 = ConnectInit.queries
							.getProperty("get_identifier_code_for_stkid");
					// Logging.getDebug("query is "+qry1);
					PreparedStatement psmt1 = connection.prepareStatement(qry1);
					psmt1.setString(1, stockid);
					// Logging.getDebug("psmt1 is "+psmt1);
					ResultSet rs11 = psmt1.executeQuery();
					while (rs11.next()) {
						idcode = rs11.getString(1);
						// Logging.getDebug("idcode is "+idcode);
					}
				}
				qry = ConnectInit.queries
						.getProperty("select_identifier_code_value_from_sic");
				ResultSet rs1 = connection.createStatement().executeQuery(qry);
				while (rs1.next()) {
					String name = rs1.getString(1);
					if (name.equals(identifier_code)) {
						if (idcode.equals(identifier_code)) {
							flg = true;
							break;
						} else {
							flg = false;
							break;
						}
					}
				}
			} catch (Exception e) {
				Logging.error("Errro while inserting copy of parent "
						+ e.getMessage());
			}
			// Close the Dynamic Connection : Manoj Adekar
		} catch (Exception e) {
			Logging.debug("Error :" + e);
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
		return flg;
	}

	/**
	 * check for duplication of identifier code except exchange code in a
	 * selected exchange.
	 * 
	 * @param identifier_code
	 * @param exchange
	 * @param type
	 * @param stockid
	 * @return
	 */
	public static boolean chkIdentifierCode12(String identifier_code,
			String exchange, String type, String stockid) {
		boolean flg = true;
		Connection connection = null;
		try {
			String qry = null, qry1 = null;
			Vector idcode = new Vector();
			// getConnection();
			if (connection == null) {
				connection = con1.getdbConnection();
			}
			// Logging.getDebug("before query identifier_code is "+identifier_code+" idcode "+idcode+" stockid "+stockid);
			if (stockid != null) {
				// flg=false;
				qry1 = ConnectInit.queries
						.getProperty("get_identifier_code_for_stkid");
				// Logging.getDebug("query is "+qry1);
				PreparedStatement psmt1 = connection.prepareStatement(qry1);
				psmt1.setString(1, stockid);
				// Logging.getDebug("psmt1 is "+psmt1);
				ResultSet rs11 = psmt1.executeQuery();
				int k = 0;
				while (rs11.next()) {
					idcode.add(k, rs11.getString(1));
					k++;
					// Logging.getDebug("idcode is "+idcode);
				}
				rs11.close();
				psmt1.close();
			}
			// Logging.getDebug(" identifier_code is "+identifier_code+" idcode "+idcode+" stockid "+stockid);
			qry = ConnectInit.queries
					.getProperty("select_identifier_code_value_from_sic_for_exch");
			PreparedStatement psmt3 = connection.prepareStatement(qry);
			psmt3.setString(1, exchange);
			ResultSet rs1 = psmt3.executeQuery();
			while (rs1.next()) {
				String name = rs1.getString(1);
				// Logging.getDebug("name is "+name+" identifier_code is "+identifier_code);
				if (name.equals(identifier_code)) {
					if (stockid == null) {
						flg = false;
					} else {
						for (int m = 0; m < idcode.size(); m++) {
							if (((String) idcode.get(m))
									.equals(identifier_code)) {
								flg = true;
								break;
							} else {
								flg = false;
							}
						}
					}
				}
			}
			rs1.close();
			psmt3.close();
		} catch (Exception e) {
			Logging.error("Errro while inserting copy of parent "
					+ e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		return flg;
	}

	public static boolean ValidateCurrancy(String str) {
		int chk = 0;
		boolean flag1 = false;
		String qry = null;
		// getConnection();
		Connection connection = null;
		Connect con = ConnectInit.getConnect();
		try {
			// Change by Manoj Adekar for Dynamic connection using
			// getdbConnection() instead of getConnection()
			if (connection == null) {
				connection = con.getdbConnection();
			}
			if (str == null || str.trim().equals("")) {
				flag1 = false;
			} else {
				try {
					// Logging.getDebug("Inside try of check currancy");
					qry = ConnectInit.queries
							.getProperty("check_currancy_name");
					// Statement st=Connect.con.createStatement();
					// Logging.getDebug("query"+qry+" cName "+str);
					ResultSet rs11 = connection.createStatement().executeQuery(
							qry);
					// Logging.getDebug("query"+qry+"  Resultset  "+rs11);
					while (rs11.next()) {
						if (str.equals((String) rs11.getString(1))) {
							// Logging.getDebug("Inside if of check currancy");
							chk = 1;
							break;
						}
					}
				} catch (SQLException e) {
					Logging.error(" ERROR :" + e.getMessage());
				}

			}
			// Close the Dynamic Connection : Manoj Adekar
		} catch (Exception e) {
			Logging.debug("Error :" + e);
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
		if (chk == 1) {
			flag1 = true;
		}
		return flag1;
	}

	/**
	 * check for duplication of exchange code.(exchange code can be duplicate in
	 * case of NSEI exchange if series is different for that stock).
	 * 
	 * @param identifier_code
	 * @param exchange
	 * @param type
	 * @param stockid
	 * @return
	 */
	public static boolean chkIdentifierCode1(String identifier_code,
			String exchange, String type, String stockid) {

		String qry = null;
		boolean flg = true;
		Connection connection = null;
		try {
			String qry1 = null;
			Vector stockType = new Vector();
			stockType.clear();
			Vector idcode = new Vector();
			String type_sid = null;
			// getConnection();
			if (connection == null) {
				connection = con1.getdbConnection();
			}
			// Logging.getDebug("before query identifier_code is "+identifier_code+" idcode "+idcode+" stockid "+stockid);
			if (stockid != null) {
				// flg=false;
				String qrytypeid1 = ConnectInit.queries
						.getProperty("type_for_stock_id");
				// Logging.getDebug("query is "+qry1);
				PreparedStatement psmttypeid1 = connection
						.prepareStatement(qrytypeid1);
				psmttypeid1.setString(1, stockid);
				// Logging.getDebug("psmt1 is "+psmt1);
				ResultSet rstypeid1 = psmttypeid1.executeQuery();
				while (rstypeid1.next()) {
					type_sid = rstypeid1.getString(1);
				}
				rstypeid1.close();
				psmttypeid1.close();

				qry1 = ConnectInit.queries
						.getProperty("get_identifier_code_for_stkid");
				// Logging.getDebug("query is "+qry1);
				PreparedStatement psmt1 = connection.prepareStatement(qry1);
				psmt1.setString(1, stockid);
				// Logging.getDebug("psmt1 is "+psmt1);
				ResultSet rs11 = psmt1.executeQuery();
				int k = 0;
				while (rs11.next()) {
					idcode.add(k, rs11.getString(1));
					k++;
					// Logging.getDebug("idcode is "+idcode);
				}
				rs11.close();

			}
			qry = ConnectInit.queries
					.getProperty("select_type_name_from_stock_identifier_codes");
			PreparedStatement psmt2 = connection.prepareStatement(qry);
			psmt2.setString(1, identifier_code);
			// Logging.getDebug("psmt"+psmt);
			ResultSet rs2 = psmt2.executeQuery();
			int count = 0;
			while (rs2.next()) {
				stockType.add(count, rs2.getString(1));
				count++;
			}
			rs2.close();
			// Logging.getDebug(" stockType.size() is "+stockType.size());
			qry = ConnectInit.queries
					.getProperty("select_identifier_code_value_from_sic_for_exch");
			PreparedStatement psmt3 = connection.prepareStatement(qry);
			psmt3.setString(1, exchange);
			ResultSet rs1 = psmt3.executeQuery();
			while (rs1.next()) {
				String name = rs1.getString(1);
				// Logging.getDebug("name is "+name+" identifier_code is "+identifier_code);
				if (name.equals(identifier_code)) {
					int flag2 = 0;
					if (stockid == null) {
						for (int i = 0; i < stockType.size(); i++) {
							// Logging.getDebug("type is "+type+" stock type is "+(String)stockType.get(i));
							if ((type.equals((String) stockType.get(i)))
									&& exchange.equals("84")) {
								flg = false;
								flag2 = 1;
								break;
							} else {
								flag2 = 0;
							}
						}
						if (flag2 == 1) {
							flg = false;
							break;
						}
					} else {
						int flag1 = 0;
						int flag = 0;
						flg = false;
						for (int m = 0; m < idcode.size(); m++) {
							// Logging.getDebug("idcode is "+(String)idcode.get(m)+" identifier_code "+identifier_code);
							// Logging.getDebug(" idcode and identifier code is "+((String)idcode.get(m)).equals(identifier_code));
							if (((String) idcode.get(m))
									.equals(identifier_code)) {
								// Logging.getDebug("In if ");
								for (int i = 0; i < stockType.size(); i++) {
									if ((type.equals((String) stockType.get(i)))
											&& exchange.equals("84")) {
										if ((type.equals(type_sid))) {
											flg = true;
											flag = 1;
											break;
										} else {
											if (!(type
													.equals((String) stockType
															.get(i)))
													&& exchange.equals("84")) {
												flg = true;
												flag1 = 1;
												break;
											} else {
												flag1 = 0;
											}
										}
									}
								}
								// Logging.getDebug("flag is "+flag+" flag1 is "+flag1);
								if (flag == 1 || flag1 == 1) {
									flg = true;
									break;
								}
							} else {
								int flagr = 0;
								for (int i = 0; i < stockType.size(); i++) {
									if (type.equals((String) stockType.get(i))) {
										flagr = 1;
										// Logging.getDebug("In else type");
									}
								}
								if (flagr == 0) {
									// Logging.getDebug("In flagr loop");
									flg = true;
								}
							}
						}
					}
				}
			}
			rs1.close();
		} catch (Exception e) {
			Logging.error("Errro while inserting copy of parent "
					+ e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		return flg;

	}

	/**
	 * check for company name if it exist or not.
	 * 
	 * @param cname
	 * @return
	 */
	public static boolean chkCompanyName(String cname) {
		boolean flg = true;
		Connection connection = null;
		Connect con = ConnectInit.getConnect();
		try {
			// Change by Manoj Adekar for Dynamic connection using
			// getdbConnection() instead of getConnection()
			if (connection == null) {
				connection = con.getdbConnection();
			}
			try {
				String qry = null;
				// getConnection();
				String query1 = null;
				query1 = ConnectInit.queries.getProperty("company_list");
				Statement stmt1 = connection.createStatement();
				ResultSet rs2 = stmt1.executeQuery(query1);
				while (rs2.next()) {
					if (cname.trim().equalsIgnoreCase(rs2.getString(2))) {
						flg = false;
						break;
					}
				}
			} catch (Exception e) {
				Logging.error("Errro while checking company name "
						+ e.getMessage());
			}

		} catch (Exception e) {
			Logging.debug("Error :" + e);
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
		return flg;
	}

	/**
	 * check for stock type if not exist then insert values in stock_type for
	 * the the given stock type.
	 * 
	 * @param form
	 * @return
	 */
	public static int chkStocktype(ActionForm form) {
		int s_val = 0;
		Connection connection = null;
		Connect c = ConnectInit.getConnect();
		try {
			// getConnection();
			if (connection == null) {
				connection = c.getdbConnection();
			}
			StockMasterForm form1 = (StockMasterForm) form;
			String query1 = null;
			String stk_id = null;
			boolean flag = true;
			String stk_type = form1.getS_stockType();
			query1 = ConnectInit.queries.getProperty("select_from_stock_type");
			Statement stmt1 = connection.createStatement();
			ResultSet rs1 = stmt1.executeQuery(query1);
			while (rs1.next()) {
				String name = rs1.getString("stock_type_name");
				if (name.equals(stk_type)) {
					stk_id = rs1.getString("stock_type_id");
					flag = false;
					break;
				}
			}
			rs1.close();
			if (flag == true)// if stock type does not exist.
			{
				try {
					// getConnection();
					String qry = ConnectInit.queries
							.getProperty("get_sequence_stock_type_id");
					Statement stmt2 = connection.createStatement();
					ResultSet rs2 = stmt2.executeQuery(qry);
					while (rs2.next()) {
						s_val = rs2.getInt(1);
					}
					rs2.close();
					qry = ConnectInit.queries
							.getProperty("insert_into_stock_type");
					PreparedStatement psmt1 = connection.prepareStatement(qry);
					psmt1.setInt(1, s_val);
					psmt1.setString(2, stk_type);
					psmt1.executeUpdate();
					String stktype = s_val + "," + stk_type;

					// maintain audit trail for stock type insert.

					AuditTrail.StoreTableInsertUpdate("63", null, stktype);
				} catch (SQLException e) {
					Logging.error("Error: " + e.getMessage());
				}
			} else {
				s_val = Integer.parseInt(stk_id);
			}

		} catch (SQLException e) {
			Logging.error("Error: " + e.getMessage());
		} catch (Exception e) {
			Logging.error("Errro while inserting copy of parent "
					+ e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error("Error : Unable to close Connection "
							+ ex.getMessage());
				}
				Logging.error("Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		return s_val;
	}

	/**
	 * check for duplication of stock exchange name.
	 * 
	 * @param ExcName
	 * @return
	 */
	public static boolean chkstkExcName(String ExcName) {
		boolean flag = true;
		Connection connection = null;
		Connect con = ConnectInit.getConnect();
		try {
			// Change by Manoj Adekar for Dynamic connection using
			// getdbConnection() instead of getConnection()
			if (connection == null) {
				connection = con.getdbConnection();
			}
			try {
				String qry = null;
				// getConnection();
				String query1 = null;
				query1 = ConnectInit.queries
						.getProperty("Stock_exchange_name_code_list");
				Statement stmt1 = connection.createStatement();
				ResultSet rs2 = stmt1.executeQuery(query1);
				while (rs2.next()) {
					if (ExcName.trim().equalsIgnoreCase(rs2.getString(1))) {
						flag = false;
						break;
					}
				}
			} catch (Exception e) {
				Logging.error("Errro while checking Stock Exchange name "
						+ e.getMessage());
			}
			// Close the Dynamic Connection : Manoj Adekar
		} catch (Exception e) {
			Logging.debug("Error :" + e);
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
		return flag;
	}

	/**
	 * check for exchange code duplication.
	 * 
	 * @param ExcCode
	 * @return
	 */
	public static boolean chkstkExcCode(String ExcCode) {
		boolean flag = true;
		Connection connection = null;
		Connect con = ConnectInit.getConnect();
		try {
			// Change by Manoj Adekar for Dynamic connection using
			// getdbConnection() instead of getConnection()
			if (connection == null) {
				connection = con.getdbConnection();
			}
			try {
				// getConnection();
				String query1 = null;
				query1 = ConnectInit.queries
						.getProperty("Stock_exchange_name_code_list");
				Statement stmt1 = connection.createStatement();
				ResultSet rs2 = stmt1.executeQuery(query1);
				while (rs2.next()) {
					if (ExcCode.equalsIgnoreCase(rs2.getString(2))) {
						flag = false;
						break;
					}
				}
			} catch (Exception e) {
				Logging.error("Errro while checking Stock Exchange code "
						+ e.getMessage());
			}
			// Close the Dynamic Connection : Manoj Adekar
		} catch (Exception e) {
			Logging.debug("Error :" + e);
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
		return flag;
	}

	/**
	 * check if rating code exist or not if not then insert values for that
	 * rating code in rating_code table.
	 * 
	 * @param form
	 * @return
	 */
	public static int chkRate(ActionForm form) {
		int r_val = 0;
		Connection connection = null;
		Connect c = ConnectInit.getConnect();
		try {
			// getConnection();
			if (connection == null) {
				connection = c.getdbConnection();
			}

			Logging.debug("Inside chkrate ");
			StockMasterForm form1 = (StockMasterForm) form;
			String rate = form1.getS_ratingCode();
			Logging.debug("rating code value is " + rate);
			if ((rate == null) || (rate.equals("")))
				return 0;
			String rate_id = null;
			ResultSet rs1 = null;
			Statement stmt1 = null;
			String query1 = null;
			boolean flag = true;
			query1 = ConnectInit.queries
					.getProperty("select_from_rating_codes");
			stmt1 = connection.createStatement();
			rs1 = stmt1.executeQuery(query1);
			while (rs1.next()) {
				String name = rs1.getString("rating_code_id");
				Logging.debug("rating code name is " + name + " rate name is "
						+ rate);
				if (name.equals(rate)) {
					Logging.debug("rate equals name from list ");
					rate_id = rs1.getString("rating_code_id");
					Logging.debug("rate code id is  " + rate_id);
					flag = false;
					break;
				}
			}
			rs1.close();
			if (flag == true) {
				try {
					// getConnection();
					String qry = ConnectInit.queries
							.getProperty("get_sequence_rating_code_id");
					Statement stmt2 = connection.createStatement();
					ResultSet rs2 = stmt2.executeQuery(qry);
					while (rs2.next()) {
						r_val = rs2.getInt(1);
					}
					// rs2.close();

					qry = ConnectInit.queries
							.getProperty("insert_into_rating_codes");
					PreparedStatement psmt1 = connection.prepareStatement(qry);
					psmt1.setInt(1, r_val);
					psmt1.setString(2, rate);
					psmt1.executeUpdate();
					String ratingcode = r_val + "," + rate;

					// maintain audit trail for rating code insert.

					AuditTrail.StoreTableInsertUpdate("64", null, ratingcode);

				} catch (SQLException e) {
					Logging.error("Error: " + e.getMessage());
				}
			} else {
				r_val = Integer.parseInt(rate_id);
			}

		} catch (Exception e) {
			Logging.error("Errro while inserting copy of parent "
					+ e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error("Error : Unable to close Connection "
							+ ex.getMessage());
				}
				Logging.error("Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		return r_val;
	}

	/**
	 * update stock_master,stock_master_history,identifier_code and
	 * adr_gdr_ratio with new stock detail values inserted by user.
	 * 
	 * @param query
	 * @param qry1
	 * @param form
	 */
	public void updateStockMaster(String query, String qry1, ActionForm form) {

		Connection connection = null;
		Connect c = ConnectInit.getConnect();

		try {
			// getConnection();
			if (connection == null) {
				connection = c.getdbConnection();
			}
			StockMasterForm form1 = (StockMasterForm) form;

			String stockid = form1.getS_stockID();
			int exc_id = (int) Integer.parseInt(form1.getS_stockExchange());

			// PreparedStatement pst = Connect.con.prepareStatement(con1.queries
			// .getProperty("get_identifier_code"));
			PreparedStatement pst = connection
					.prepareStatement(ConnectInit.queries
							.getProperty("get_identifier_code"));
			pst.setInt(1, exc_id);
			ResultSet rst = pst.executeQuery();
			while (rst.next()) {
				identifier_code = rst.getString(1);
			}
			rst.close();

			String stk_type = form1.getS_stockType();
			String query1 = null;
			boolean flag = true;
			String stk_id = null;
			String act = form1.getB_isActive();
			String act_val = null;
			if (act == null) {
				act_val = "n";
			} else {
				if (act.equals("on")) {// isActive checkBox is checked.
					act_val = "y";
				} else {
					act_val = "n";
				}
			}
			int s_val;
			int r_val;

			s_val = chkStocktype(form);// for stock type
			r_val = chkRate(form);// for rating

			PreparedStatement psmt = connection.prepareStatement(query);
			psmt.setInt(1, s_val);// form1.getS_stockType());
			psmt.setString(2, form1.getS_companyName());
			psmt.setString(3, form1.getD_iwf());
			psmt.setString(4, form1.getF_issuedShares());
			psmt.setString(5, form1.getS_stockName());
			psmt.setString(6, form1.getF_faceValue());
			psmt.setString(7, form1.getD_listingDate());
			psmt.setString(8, form1.getS_growthValueType());
			psmt.setInt(9, r_val);// form1.getS_ratingCode());
			psmt.setString(10, form1.getB_global100());
			psmt.setString(11, form1.getF_alertPercent());
			psmt.setString(12, form1.getF_rejectionPercent());
			psmt.setString(13, form1.getB_withHoldingTaxApplicable());

			if (!form1.getF_withholdingTaxPercent().equals("")
					&& form1.getF_withholdingTaxPercent() != null)
				psmt.setString(14, form1.getF_withholdingTaxPercent());
			else
				psmt.setString(14, "0.00");

			psmt.setString(15, form1.getS_marketLot());
			psmt.setString(16, form1.getS_stockExchange());
			psmt.setString(17, form1.getS_countryName());
			psmt.setString(18, act_val);
			psmt.setString(19, form1.getS_stockCurrency());
			psmt.setString(20, form1.getB_isPriceForLot());
			psmt.setString(21, form1.getD_paidValue());
			psmt.setString(22, stockid);
			psmt.executeUpdate();
			Logging.info("values updated in stock master");

			// generate string conataining new stock values

			String stockmasterupdate = form1.getS_stockType() + ","
					+ form1.getS_companyName() + "," + form1.getD_iwf() + ","
					+ form1.getF_issuedShares() + "," + form1.getS_stockName()
					+ "," + form1.getF_faceValue() + ","
					+ form1.getD_listingDate() + ","
					+ form1.getS_growthValueType() + ","
					+ form1.getB_global100() + "," + form1.getF_alertPercent()
					+ "," + form1.getF_rejectionPercent() + ","
					+ form1.getB_withHoldingTaxApplicable() + ","
					+ form1.getB_withHoldingTaxApplicable() + ","
					+ form1.getF_withholdingTaxPercent() + ","
					+ form1.getS_marketLot() + "," + form1.getS_stockExchange()
					+ "," + form1.getS_countryName() + "," + act_val + ","
					+ form1.getS_stockCurrency() + ","
					+ form1.getB_isPriceForLot() + "," + form1.getD_paidValue()
					+ "," + stockid;
			String old_value = getOldValuesSm();

			// maintain audit trail for stock_master update.

			AuditTrail.StoreTableInsertUpdate("59", old_value,
					stockmasterupdate);

			SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
			Date dt = new Date();
			String l_date = fr.format(dt).toString();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(ConnectInit.queries
					.getProperty("get_sequence_stock_mh_id"));
			rs.next();
			int smid = rs.getInt(1);
			rs.close();
			stmt.close();
			/*
			 * insert into stock_master_history( stock_mh_id, stock_id,
			 * stock_type_id, company_id, iwf, tis, stock_name, face_value,
			 * listing_date, growth_or_value, rating_code_id, global100,
			 * alert_percentage, rejection_percentage,
			 * witholding_tax_applicable, witholding_tax_percent, market_lot,
			 * stock_exchange_id, country_id, is_active, stock_currency_id,
			 * is_price_for_lot, paid_value, history_date ) values(?
			 */
			psmt = connection.prepareStatement(qry1);
			String sid = form1.getS_stockID();
			psmt.setInt(1, smid);
			psmt.setString(2, sid);
			psmt.setInt(3, s_val);// form1.getS_stockType());
			// System.out.println(s_val);

			String cnm = form1.getS_companyName();
			psmt.setString(4, cnm);
			// System.out.println(form1.getS_companyName());
			psmt.setString(5, form1.getD_iwf());
			psmt.setString(6, form1.getF_issuedShares());
			psmt.setString(7, form1.getS_stockName());
			psmt.setString(8, form1.getF_faceValue());
			psmt.setString(9, form1.getD_listingDate());
			// System.out.println(form1.getS_growthValueType());
			psmt.setString(10, form1.getS_growthValueType());
			if (r_val == 0) {
				psmt.setString(11, null);// form1.getS_ratingCode());
			} else {
				psmt.setInt(11, r_val);// form1.getS_ratingCode());
			}
			psmt.setString(12, form1.getB_global100());
			psmt.setString(13, form1.getF_alertPercent());
			psmt.setString(14, form1.getF_rejectionPercent());
			psmt.setString(15, form1.getB_withHoldingTaxApplicable());

			if (form1.getF_withholdingTaxPercent().equals("")
					&& form1.getF_withholdingTaxPercent() != null)
				psmt.setString(16, form1.getF_withholdingTaxPercent());
			else
				psmt.setString(16, "0.00");

			psmt.setString(17, form1.getS_marketLot());
			psmt.setString(18, form1.getS_stockExchange());
			psmt.setString(19, form1.getS_countryName());
			psmt.setString(20, act_val);
			psmt.setString(21, form1.getS_stockCurrency());
			psmt.setString(22, form1.getB_isPriceForLot());
			psmt.setString(23, form1.getD_paidValue());
			psmt.setString(24, l_date);
			// psmt.setString(25,null);//Commented by manoj A on 19-04-2010
			psmt.executeUpdate();
			Logging.info("Stock values inserted in stock master history");

			// generate string with updated values in stock_master_history.

			String stockmasterhistory = s_val + "," + form1.getS_companyName()
					+ "," + form1.getD_iwf() + "," + form1.getF_issuedShares()
					+ "," + form1.getS_stockName() + ","
					+ form1.getF_faceValue() + "," + form1.getD_listingDate()
					+ "," + form1.getS_growthValueType() + ","
					+ form1.getB_global100() + "," + form1.getF_alertPercent()
					+ "," + form1.getF_rejectionPercent() + ","
					+ form1.getB_withHoldingTaxApplicable() + ","
					+ form1.getF_withholdingTaxPercent() + ","
					+ form1.getS_marketLot() + "," + form1.getS_stockExchange()
					+ "," + form1.getS_countryName() + "," + act_val + ","
					+ form1.getS_stockCurrency() + ","
					+ form1.getB_isPriceForLot() + "," + form1.getD_paidValue()
					+ "," + l_date + "," + null;

			// maintain audit trail for stock_master_history update.

			AuditTrail.StoreTableInsertUpdate("60", null, stockmasterhistory);

			String sdl = form1.getB_sdl();
			String isin = form1.getB_isn();
			String ric = form1.getB_ric();
			String cusip = form1.getB_csp();
			String exc = form1.getB_exc_code();
			String tkr = form1.getB_tkr();
			String crisil = form1.getB_crisil();
			int Sid = Integer.parseInt(stockid);

			if (sdl == null || sdl.equals(""))// no sdl code.
			{
				// Logging.getDebug("no code");
			} else {
				String sdl_id = getIdentifierCodeId("sedol");
				updatestockidentifier(stockid, sdl, sdl_id, stk_type);// update
																		// identifier_codes
																		// for
																		// sedol
																		// code.
				String sdlnew = sdl + "," + stk_type;
				String sdlold = getOldSdlIdentifier();

				// maintain audit trail for identifier_codes update with new
				// sedol code values.

				AuditTrail.StoreTableInsertUpdate("65", sdlold, sdlnew);
			}
			if (ric.equals("")) {
				Logging.debug("no code");
			} else {
				String ric_id = getIdentifierCodeId("ric");
				updatestockidentifier(stockid, ric, ric_id, stk_type); // update
																		// identifier_codes
																		// for
																		// ric
																		// code.
				String ricnew = ric + "," + stk_type;
				String ricold = getOldRicIdentifier();

				// maintain audit trail for identifier_codes update with new ric
				// code values.
				// AuditTrail.StoreTableInsertUpdate("65",ricold,ricnew);

			}
			if (isin.equals("")) {
				Logging.debug("no code");
			} else {
				String isin_id = getIdentifierCodeId("isin");
				updatestockidentifier(stockid, isin, isin_id, stk_type);// update
																		// identifier_codes
																		// for
																		// isin
																		// code.
				String isinnew = isin + "," + stk_type;
				String isinold = getOldIsinIdentifier();

				// maintain audit trail for identifier_codes update with new
				// isin code values.

				AuditTrail.StoreTableInsertUpdate("65", isinold, isinnew);
			}
			if (cusip.equals("")) {
				Logging.debug("no code");
			} else {
				String csp_id = getIdentifierCodeId("cusip");
				updatestockidentifier(stockid, cusip, csp_id, stk_type);// update
																		// identifier_codes
																		// for
																		// cusip
																		// code.
				String cspnew = cusip + "," + stk_type;
				String cspold = getOldCspIdentifier();

				// maintain audit trail for identifier_codes update with new
				// cusip code values.

				AuditTrail.StoreTableInsertUpdate("65", cspold, cspnew);
			}
			if (exc.equals("")) {
				Logging.debug("no code");
			} else {
				exc_id = (int) Integer.parseInt(form1.getS_stockExchange());
				PreparedStatement pst12 = connection
						.prepareStatement(ConnectInit.queries
								.getProperty("get_identifier_code"));
				pst.setInt(1, exc_id);
				ResultSet rst12 = pst.executeQuery();
				while (rst12.next()) {
					identifier_code = rst12.getString(1);
				}
				updatestockidentifier(stockid, exc, identifier_code, stk_type);// update
																				// identifier_codes
																				// for
																				// exchange
																				// code.
				String excnew = exc + "," + stk_type;
				String excold = getOldExcIdentifier();

				// maintain audit trail for identifier_codes update with new
				// exchange code values.

				AuditTrail.StoreTableInsertUpdate("65", excold, excnew);
			}
			if (tkr.equals("")) {
				Logging.debug("no code");
			} else {
				String tkr_id = getIdentifierCodeId("ticker");
				updatestockidentifier(stockid, tkr, tkr_id, stk_type); // update
																		// identifier_codes
																		// for
																		// ticker
																		// code.
				String tickernew = tkr + "," + stk_type;
				String tkrold = getOldTkrIdentifier();

				// maintain audit trail for identifier_codes update with new
				// ticker code values.

				AuditTrail.StoreTableInsertUpdate("65", tkrold, tickernew);
			}
			if ((crisil == null) || (crisil.equals(""))) {
				Logging.debug("no code");
			} else {
				String crisil_id = getIdentifierCodeId("crisil");
				updatestockidentifier(stockid, crisil, crisil_id, stk_type);// update
																			// identifier_codes
																			// for
																			// crisil
																			// code.
				String crisilnew = crisil + "," + stk_type;
				String crsold = getOldCrsIdentifier();

				// maintain audit trail for identifier_codes update with new
				// crisil code values.

				AuditTrail.StoreTableInsertUpdate("65", crsold, crisilnew);
			}

			Logging.info("identifier code values updated in stock identifier codes");
			String qry11 = ConnectInit.queries
					.getProperty("update_stock_adr_gdr_ratio");
			psmt = connection.prepareStatement(qry11);
			if (form1.getS_adrRatio() == null
					|| form1.getS_adrRatio().equals("")) {
				psmt.setString(1, "0");
			} else {
				psmt.setString(1, form1.getS_adrRatio());
			}
			if (form1.getS_adrRatio1() == null
					|| form1.getS_adrRatio1().equals("")) {
				psmt.setString(2, "0");
			} else {
				psmt.setString(2, form1.getS_adrRatio1());
			}
			psmt.setString(3, form1.getS_stockID());
			psmt.executeUpdate();
			String adr_gdr_new = form1.getS_adrRatio() + ","
					+ form1.getS_adrRatio1() + "," + form1.getS_stockID();
			String oldvalue = getOldValuesAdr();

			// maintain audit trail for adr_gdr_ratios update.

			AuditTrail.StoreTableInsertUpdate("66", oldvalue, adr_gdr_new);
			Logging.info("after updating adr values");
		} catch (Exception e) {
			Logging.error("Errro while inserting copy of parent "
					+ e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error("Error : Unable to close Connection "
							+ ex.getMessage());
				}
				Logging.error("Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
	}

	/**
	 * update stock_identifier_codes with new identifier code entered by user.
	 * 
	 * @param stockID
	 * @param ident_val
	 * @param ident_id
	 * @param stk_type
	 */
	public void updatestockidentifier(String stockID, String ident_val,
			String ident_id, String stk_type) {
		Connection connection = null;
		Connect c = ConnectInit.getConnect();
		try {
			// getConnection();
			if (connection == null) {
				connection = c.getdbConnection();
			}
			String qry = ConnectInit.queries
					.getProperty("select_from_stock_identifier_code");
			PreparedStatement stmt = connection.prepareStatement(qry);
			stmt.setInt(1, Integer.parseInt(stockID));
			stmt.setString(2, ident_id);
			ResultSet rs = stmt.executeQuery();
			int stk_ident_code = 0;
			while (rs.next()) {
				stk_ident_code = rs.getInt("stock_identifier_code_id");
				// Logging.getDebug("stk iden code="+stk_ident_code);
			}
			rs.close();
			String query1 = ConnectInit.queries
					.getProperty("update_stock_identifier_code");
			PreparedStatement psmt1 = connection.prepareStatement(query1);
			psmt1.setString(1, ident_val);
			psmt1.setString(2, stk_type);
			psmt1.setInt(3, stk_ident_code);
			psmt1.executeUpdate();
			// Logging.getDebug("complete");
		} catch (Exception e) {
			Logging.error("Errro while inserting copy of parent "
					+ e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error("Error : Unable to close Connection "
							+ ex.getMessage());
				}
				Logging.error("Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
	}

	/**
	 * method to get the current system time.
	 */
	public static String getTime() {
		java.util.Date dt = new java.util.Date();
		dt.getDate();
		return dt.toString().split(" ")[3];
	}

	/**
	 * insert the stock details of unimported stocks in stock_master_unimported.
	 */
	public static void insertUnimportedStock(String key, String exc_id,
			String query, ActionForm form) {
		Connection connection = null;
		Connect con = ConnectInit.getConnect();
		// Change by Manoj Adekar for Dynamic connection using getdbConnection()
		// instead of getConnection()
		try {
			if (connection == null) {
				connection = con.getdbConnection();
			}
			StockMasterForm form1 = (StockMasterForm) form;

			Logging.debug("Inside insert Stock Master unimported");
			Logging.debug("Exchange id is " + exc_id + " key is " + key);
			String date = QueryClass.formatDate();
			String time = getTime();
			Logging.debug("date is " + date + " time is " + time);
			PreparedStatement psmt = connection.prepareStatement(query);
			Logging.debug("in query psmt is " + psmt);
			Statement stmt = connection.createStatement();
			Logging.debug("in query stmt is " + stmt);
			ResultSet rs = null;
			/*
			 * int para=0; rs= stmt.executeQuery("SELECT NEXTVAL('smu_id')");
			 * Logging.getDebug("rs is "+rs); while(rs.next()){
			 * Logging.getDebug("inside rs.next() para is "+rs.getInt(1));
			 * para=rs.getInt(1); Logging.getDebug("para is "+para); }
			 * psmt.setInt(1,para);//smu_id (stock_master_unimported id).
			 * Logging.getDebug("para is "+para); rs.close();
			 */
			System.out.println("key *** " + key);
			System.out.println("Stock name *** " + form1.getS_stockName());
			System.out.println("Series *** " + form1.getSeries());
			System.out.println("ISIN *** " + form1.getB_isn());
			System.out.println("Cusip code *** " + form1.getB_csp());
			System.out.println("Market lot *** " + form1.getS_marketLot());
			System.out.println("Paid value *** " + form1.getD_paidValue());
			System.out.println("Face value *** " + form1.getF_faceValue());
			System.out.println("exc_id" + exc_id);
			System.out.println("date *** " + date);
			System.out.println("time *** " + time);
			psmt.setString(1, key);// symbol
			psmt.setString(2, form1.getS_stockName());// company name.
			psmt.setString(3, form1.getSeries());// series (in case of NSEI)
			psmt.setString(4, null);// industry
			Logging.debug("form1.getS_stockName() is " + form1.getS_stockName()
					+ " form1.getSeries() is " + form1.getSeries());
			psmt.setString(5, form1.getB_isn());// isin code.
			psmt.setString(6, form1.getB_csp());// cusip code.
			psmt.setString(7, form1.getS_marketLot());// market lot.
			psmt.setString(8, form1.getD_paidValue());// paid_value
			psmt.setString(9, form1.getF_faceValue());// face_value
			psmt.setString(10, exc_id);// exchyange_id
			psmt.setString(11, date);// exchyange_id
			psmt.setString(12, time);// exchyange_id
			int recordAdded = psmt.executeUpdate();
			System.out.println("Record added in Stock Master unimported");
			Logging.debug("after inserting vvalues in stock_master_unimported");

		} catch (Exception e) {
			Logging.error("Error : " + e.getMessage());
			System.out.println("Error :  in Stock Master unimported ***"
					+ e.getMessage());
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
	}

	public static void get_newissue_stkdetail(ResultSet rs, ResultSet rs1,
			StockMasterForm form1) {
		try {
			rs.next();
			// commented by pranoti 13SEP05 as per IISL requirement(already
			// selected)
			// form1.setS_stockExchange(rs.getString(1));
			form1.setB_tkr(rs.getString(1));
			Logging.debug("stock type====" + form1.getS_stockType());
			if (rs1.next())
				form1.setS_countryName(rs1.getString(1));
			else
				form1.setS_countryName(null);

			/*
			 * if(rs2.next()) form1.setS_stockType(rs2.getString(1)); else
			 * form1.setS_stockType(null);
			 */

			if (form1.getS_stockType() != null
					&& form1.getS_stockType().equals(""))
				form1.setS_stockType(null);

			/*
			 * if(form1.getS_stockType()==null)
			 * form1.setS_stockType(form1.getB_tkr().substring(0,2));
			 */

			/*
			 * if(rs3.next()) form1.setS_stockCurrency(rs3.getString(2));
			 */
		} catch (Exception e) {
			Logging.error("Error=" + e.getMessage());
		}
	}

}
