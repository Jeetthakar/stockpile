/*
 * Created on Jan 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.chart.demo.servlet.AdjustDecimal;

import com.harrier.initializeation.ConnectInit;

/**
 * @author pranoti
 *
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class IndexCA_Action extends Action {
	static Logger Logging = Logger.getLogger(IndexCA_Action.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		Corporate corp = (Corporate) form; // bean object

		Connect connect = ConnectInit.getConnect(); // connection
		Connection con = null;
		if (con == null) {
			con = connect.getdbConnection();
		}

		String hist_butt = request.getParameter("hist_but");
		// historic action
		if (hist_butt != null) {
			if (hist_butt.equals("Back")) {
				return new ActionForward(
						"/pages/IndexEvents.jsp?ref_flag=1&corp_name="
								+ corp.getCorpid());
			}
			if (hist_butt.equals("Continue")) {
				// check whether this action already present in diary or not
				String corp_id = corp.getCorpid();
				boolean flag = check_exist(corp.getI_index(), corp,
						corp.getCorpid(), corp.getStock());
				corp.setCorpid(corp_id);
				if (flag == true) // if exist
				{
					ref_hash(corp);
					Hashtable hash = corp.getHash();
					hash.clear();
					corp.setHash(hash);
					corp.setChk_but(null);
					corp.setR_type("index event");
					return new ActionForward("/pages/IndexCorporateDetails.jsp");
				} else {
					String ori_iwf = corp.getValues();
					corp.setR_type("index event");
					String arr[] = corp.getStock();
					if (!(corp_id.equals("changeindcurr")))
						insert_hash(corp.getStock(), corp);
					ActionCorp.Historic_cal(corp);

					// check for compute or recalculate
					String corp_nm = corp.getCorpid();
					if (corp_nm.equals("changeindcurr")) {
						corp.setCommit_butt("1");
						return new ActionForward("/pages/IndexEvents.jsp");
					} else {
						int flg = check_close(con, connect, corp);
						if (flg == 0) {
							corp.setCommit_butt(null);
							return new ActionForward(
									"/pages/IndexEvents.jsp?close=Close");
						} else {
							ActionCorp.Hist_apply(corp, 1);
							corp.setCommit_butt("1");
							return new ActionForward("/pages/IndexEvents.jsp");
						}
					}
				}
			}
		}
		String button = request.getParameter("ap_co_button");
		String type = request.getParameter("type");
		String stk[] = request.getParameterValues("stock");// collect the stock
															// in an array
		int type_id = 0;
		if (type != null)
			type_id = Integer.parseInt(type);

		String corp_id = request.getParameter("corpid");
		corp.setValues(request.getParameter("values").trim());
		if (button != null) {
			if (button.equals(""))
				button = null;
		}
		if (button != null) // check respective actions
		{
			if (button.equals("Index")) // on selection of Index
			{
				corp.setCommit_butt(null);
				corp.reset2();
				Hashtable hash1 = corp.getHash_error();
				hash1.clear();
				corp.setHash_error(hash1);
				hash1 = corp.getHash_stock_error();
				hash1.clear();
				corp.setHash_stock_error(hash1);
				Hashtable hash = corp.getHash_affind();
				hash.clear();
				corp.setHash_affind(hash);
				corp.setInd_comp(null);
				corp.setCheck_curr(false);
				corp.setStock(null); // refresh stock
				try { // get default exchange
					String qry = ConnectInit.queries
							.getProperty("select_system_config");
					ResultSet rs1 = ListTypeClass1.resultCorporate(con, qry);
					if (rs1 != null && rs1.next()) {
						corp.setExc(rs1.getString("stock_ex_id"));
						rs1.close();
					}
				} catch (Exception e) {
					Logging.error("error=" + e.getMessage());
				}
				String index = corp.getI_index();
				if (((index.equals("")) | (index == null))
						| (index.equals("Select Index")))
					corp.reset_index();
				else
					corp.setResult2(); // get the index detail
				return new ActionForward("/pages/IndexEvents.jsp");
			}
			if (button.equals("Action")) // on selection of action
			{
				corp.setCommit_butt(null);
				corp.reset2();
				Hashtable hash1 = corp.getHash_error();
				hash1.clear();
				corp.setHash_error(hash1);
				hash1 = corp.getHash_stock_error();
				hash1.clear();
				corp.setHash_stock_error(hash1);
				corp.setCheck_curr(false);
				Hashtable hash = corp.getHash_affind();
				hash.clear();
				corp.setHash_affind(hash);
				String index = corp.getI_index();
				if (index != null)
					if (!(index.equals("")))
						return new ActionForward("/pages/IndexEvents.jsp");
			}
			if (button.equals("Check_Curr")) {
				corp.setCommit_butt(null);
				String apply = corp.getApply_date();
				if (apply != null)
					if (apply.equals(""))
						apply = null;

				if (apply != null) {
					check_curr(con, connect, corp);
				}
				corp.reset2();
			}
			if (button.equals("Currency")) // on selection of currency for
											// change in index currency action
			{
				corp.setCommit_butt(null);
				check_curr(con, connect, corp);
				corp.reset2();
				return new ActionForward("/pages/IndexEvents.jsp");
			}
			if (button.equals("Apply")) // on click of apply button
			{
				String butt = corp.getButton();
				if (butt == null) // if there is no undo
				{
					String dt = UpdateCorp.accept_date(); // get the current
															// date
					String apply = corp.getApply_date();
					int chk_dt = ComputeIndexForm.CompareDate(apply, dt); // check
																			// for
																			// the
																			// current
																			// date
																			// and
																			// user's
																			// entered
																			// date

					Hashtable hash1 = corp.getHash_error();
					hash1.clear();
					corp.setHash_error(hash1);
					hash1 = corp.getHash_stock_error();
					hash1.clear();
					corp.setHash_stock_error(hash1);
					corp.reset2();
					corp.setInd_comp(null);
					String index = request.getParameter("i_index");
					int flg = 0;

					String corp_val = corp.getCorpid();
					if (chk_dt == 0) {
						// for corporate action change index currency
						if (corp_val.equals("changeindcurr")) {
							corp.setCommit_butt("1");
							if (corp.isCheck_curr() == false) {
								String chk = corp.getInd_div();

								// check whether the action is for only tmcv
								// change or both tmcv & divisor

								if (chk.equals("td"))// for tmcv&divisor
								{
									// calculate new value
									ActionCorp.cal_curr_ind(con, connect, corp,
											corp.getI_index());
									tmcv_div_adj(corp);
									return new ActionForward(
											"/pages/IndexEvents.jsp");
								} else // for tmcv only
								{
									// calculate new value
									ActionCorp.cal_curr_ind(con, connect, corp,
											corp.getI_index());
									// for change in tmcv only newdivisor is
									// equals to olddivisor
									if (corp.getInd_div().equals("t")) {
										corp.setNewdivisor(corp.getDivisor());
									}
									tmcv_div_adj(corp);// remove E power
									return new ActionForward(
											"/pages/IndexEvents.jsp");
								}
							}
						} else // for other corporate action
						{
							try {
								// check the index closing value for the
								// particular date
								String date = UpdateCorp.accept_date();
								String query = ConnectInit.queries
										.getProperty("select_resp_close");
								ResultSet rs = ListTypeClass1.getResult_apply(
										con, query, corp.getI_index(), date);
								if (rs.next()) {
									String close = rs
											.getString("index_closing_value");
									rs.close();
									if (close == null)
										flg = 0;
									else
										flg = 1;
								} else
									flg = 0;

							} catch (Exception e) {
								Logging.error("error =" + e.getMessage());
							}

							// if there is no index closing value
							if (flg == 0) {
								Hashtable hash = corp.getHash_affind();
								hash.clear();
								corp.setHash_affind(hash);
								corp.setAffect(null);
								corp.setCommit_butt(null);
								insert_hash(stk, corp);// insert into hash
														// selected stock

								// get the affected child indices
								String affect_index = ConnectInit.queries
										.getProperty("select_affect_index");
								String child_index = ConnectInit.queries
										.getProperty("select_child_index");
								hash1 = corp.getHash1();
								for (Enumeration enum1 = hash1.keys(); enum1
										.hasMoreElements();) {
									String id2 = (String) enum1.nextElement();
									Object obj = hash1.get(id2);
									String stid = obj.toString();
									if (corp_val.equals("addstock")) {
										ListTypeClass1.affect_index_list(con,
												corp, child_index, stid);
									}
									if ((corp_val.equals("deletestock"))
											| (corp_val.equals("changeiwf"))) {
										ListTypeClass1.affect_index_list(con,
												corp, affect_index, stid);
									}
								}
								// send response back, to give alert either to
								// recalculate or recompute index parameter
								String query = ConnectInit.queries
										.getProperty("get_latest_index_value_index_wise");
								ActionCorp.check_affect_index(con, corp, query);
								return new ActionForward(
										"/pages/IndexEvents.jsp?close=Close");

							}
							// if there exist index closing value
							if (flg == 1) {
								// check whether this action exist in diary or
								// not
								boolean flag = check_exist(index, corp,
										corp_id, stk);
								corp.setCorpid(corp_id);
								if (flag == true) // if exist
								{
									corp.setCommit_butt("1");
									ref_hash(corp);
									Hashtable hash = corp.getHash();
									hash.clear();
									corp.setHash(hash);
									corp.setChk_but(null);
									return new ActionForward(
											"/pages/IndexCorporateDetails.jsp");
								}
								if (flag == false) // if not
								{
									corp.setCommit_butt("1");
									insert_hash(stk, corp); // insert into hash
															// all selected
															// stock
									ActionCorp.applyIndexDetail(con, connect,
											corp); // calculate new value
									tmcv_div_adj(corp); // Remove E power
									return new ActionForward(
											"/pages/IndexEvents.jsp");
								}
							}
						}
					}// dates are equal
					else {
						ref_hash(corp);
						Hashtable hash = corp.getHash();
						hash.clear();
						corp.setHash(hash);
						corp.setLeng(null);
						corp.setR_type("index event");
						return new ActionForward(
								"/pages/HistoricAction.jsp?flag=New");
					}
				}// butt if
				else // if there is undo
				{
					corp.setCommit_butt("1");
					String corp_val = corp.getCorpid();
					if (corp_val.equals("changeindcurr")) {
						Logging.debug("in undo curr");
						// recalculate curr index
						ActionCorp.cal_curr_ind(con, connect, corp,
								corp.getI_index());
						// get child indices
						String query = ConnectInit.queries
								.getProperty("resp_child_indcurr");
						ListTypeClass1.affect_child_list(con, corp, query);
					} else {
						int flg = 0;
						try {
							// get the index closing value for the particulare
							// date
							String date = corp.getApplied_date();
							String query = ConnectInit.queries
									.getProperty("select_resp_close");
							ResultSet rs = ListTypeClass1.getResult_apply(con,
									query, corp.getI_index(), date);
							if (rs.next()) {
								String close = rs
										.getString("index_closing_value");
								rs.close();
								if (close == null)
									flg = 0;
								else
									flg = 1;
							} else
								flg = 0;

						} catch (Exception e) {
							Logging.error("error=" + e.getMessage());
						}

						if (flg == 0) // if there is no index closing value
						{
							// get the affected child indices list
							String affect_index = ConnectInit.queries
									.getProperty("select_affect_index");
							String child_index = ConnectInit.queries
									.getProperty("select_child_index");
							Hashtable hash1 = corp.getHash1();

							Hashtable hash = corp.getHash_affind();
							hash.clear();
							corp.setHash_affind(hash);
							corp.setAffect(null);
							for (Enumeration enum1 = hash1.keys(); enum1
									.hasMoreElements();) {
								String id2 = (String) enum1.nextElement();
								Object obj = hash1.get(id2);
								String stid = obj.toString();
								if (corp_val.equals("deletestock")) {
									ListTypeClass1.affect_index_list(con, corp,
											child_index, stid);
								}
								if ((corp_val.equals("addstock"))
										| (corp_val.equals("changeiwf"))) {
									ListTypeClass1.affect_index_list(con, corp,
											affect_index, stid);
								}
							}
							// send the response back with alert,either to
							// recompute or to recalculate index parameter
							String query = ConnectInit.queries
									.getProperty("get_latest_index_value_index_wise");
							ActionCorp.check_affect_index(con, corp, query);

							corp.setInd_comp("c");
							ActionCorp.undo_recal_applyIndexDetail(corp); // recalculate
																			// old
																			// index
																			// value
							tmcv_div_adj(corp); // remove E power
							return new ActionForward(
									"/pages/IndexEvents.jsp?flag=Exist");
						}
						if (flg == 1) // if there is index closing value
							ActionCorp.undoapplyIndexDetail(corp); // calculate
																	// new value
					}
					tmcv_div_adj(corp);// remove E power
					return new ActionForward(
							"/pages/IndexEvents.jsp?flag=Exist");
				}// if undo
			}
			synchronized (corp.StopRepetition) { // for duplicate entry
				corp.StopRepetition = corp.StopRepetition + "";
				if (button.equals("Commit")
						&& (!(corp.StopRepetition.trim().equals("end")))) // to
																			// commit
																			// the
																			// action
				{
					corp.setCommit_butt(null);
					corp.setSucc_butt("1");
					corp.StopRepetition = "end";
					corp.setCoun(corp.getValues());
					String rad_val = request.getParameter("ind_comp");
					if (rad_val != null)
						if (rad_val.equals(""))
							rad_val = null;

					remove_token(corp);
					String butt = corp.getButton();
					String corp_val = corp.getCorpid();
					if (butt == null) // if there is no undo
					{
						String dt = UpdateCorp.accept_date(); // get the current
																// date
						String apply = corp.getApply_date();
						int chk_dt = ComputeIndexForm.CompareDate(apply, dt); // check
																				// for
																				// the
																				// current
																				// date
																				// and
																				// user's
																				// entered
																				// date
						if (chk_dt == 0) {
							if (rad_val != null) // if the selected action is
													// present in diary
							{
								String ind_val = corp.getI_index();
								// update parent index
								UpdateCorp.recal_assign_index_detail(con,
										connect, corp);
								// update child indices
								UpdateCorp.recal_update_affect_value(corp);
								corp.setI_index(ind_val);
							} else // if the action is new
							{
								// for action change index currency
								if (corp_val.equals("changeindcurr")) {
									// update parent index
									UpdateCorp.chagecurrupd(con, connect, corp);

									// for value persistant
									String otmcv = corp.getTmcv();
									String ntmcv = corp.getNewTmcv();
									String odiv = corp.getDivisor();
									String ndiv = corp.getNewdivisor();

									String otmcv1 = corp.getTmcv1();
									String ntmcv1 = corp.getNewtmcv1();
									String odiv1 = corp.getDivisor1();
									String ndiv1 = corp.getNewdivisor1();

									String ori_index = corp.getI_index();
									// update child indices
									UpdateCorp.update_affectInd_curr(con,
											connect, corp);

									corp.setTmcv(otmcv);
									corp.setNewTmcv(ntmcv);
									corp.setDivisor(odiv);
									corp.setNewdivisor(ndiv);

									corp.setTmcv1(otmcv1);
									corp.setNewtmcv1(ntmcv1);
									corp.setDivisor1(odiv1);
									corp.setNewdivisor1(ndiv1);

									corp.setI_index(ori_index);
									corp.setCorpid(corp_val);
								} else {
									UpdateCorp.assign_index_detail(corp); // update
																			// parent
																			// index
									UpdateCorp.update_affect_index(con,
											connect, corp); // update child
															// indices
								}
								if (corp_val.equals("rebasing")) {
									Logging.info("Corporate action = rebasing");
									// UpdateCorp.rebasingAction(corp);
								}
							}
							corp.setValues("");
							return new ActionForward(
									"/pages/IndexEvents.jsp?ap_co_button=null");
						}// dates are equal
						else // if dates are not equal
						{
							String ori_corp = corp.getCorpid(); // data
																// persistent
							String ori_val = corp.getValues();
							String ori_stock[] = corp.getStock();
							String ori_index = corp.getI_index();

							String otmcv = corp.getTmcv();
							String otmcv1 = corp.getTmcv1();
							String ntmcv = corp.getNewTmcv();
							String ntmcv1 = corp.getNewtmcv1();

							String odiv = corp.getDivisor();
							String odiv1 = corp.getDivisor1();
							String ndiv = corp.getNewdivisor();
							String ndiv1 = corp.getNewdivisor1();
							String ori_affect = corp.getAffect();
							String ori_apply = corp.getApply_date();

							// String ntmcv1=corp.get
							if (rad_val != null)
								UpdateCorp.update_historic(corp);
							else {
								if (corp_val.equals("changeindcurr")) {
									UpdateCorp.update_historic(corp);
								} else
									UpdateCorp.hist_assign_index_detail(corp); // update
																				// parent
																				// index
							}

							Hashtable aff = corp.getHash_affind();
							Hashtable copy = corp.getCopy_hash();
							copy.clear();
							corp.setCopy_hash(copy);
							copy = corp.getCopy_hash();

							if (!(aff.isEmpty())) {
								for (Enumeration enum1 = aff.keys(); enum1
										.hasMoreElements();) {
									String id2 = (String) enum1.nextElement();
									Object obj = aff.get(id2);
									String stid = obj.toString();
									copy.put(new String(id2), new String(stid));
								}
								corp.setCopy_hash(copy);
							}
							UpdateCorp.reapply_action(corp); // commit the
																// temporary
																// undo actions
							corp.setI_index(ori_index); // set back original
														// data
							corp.setStock(ori_stock);
							corp.setValues(ori_val);
							corp.setCorpid(ori_corp);

							corp.setTmcv(otmcv);
							corp.setTmcv1(otmcv1);
							corp.setNewTmcv(ntmcv);
							corp.setNewtmcv1(ntmcv1);

							corp.setDivisor(odiv);
							corp.setDivisor1(odiv1);
							corp.setNewdivisor(ndiv);
							corp.setNewdivisor1(ndiv1);
							corp.setAffect(ori_affect);
							corp.setApply_date(ori_apply);

							copy = corp.getCopy_hash();
							aff = corp.getHash_affind();
							aff.clear();
							corp.setHash_affind(aff);
							aff = corp.getHash_affind();
							if (!(copy.isEmpty())) {
								for (Enumeration enum1 = copy.keys(); enum1
										.hasMoreElements();) {
									String id2 = (String) enum1.nextElement();
									Object obj = copy.get(id2);
									String stid = obj.toString();
									aff.put(new String(id2), new String(stid));
								}
								corp.setHash_affind(aff);
							}
						}// dates are not equal
					}// butt if
					else // if there is undo
					{
						if (corp_val.equals("changeindcurr")) {
							// update parent index
							UpdateCorp.undocurr_ind(corp, 0);
							// for value persistent
							String ori_index = corp.getI_index();// original
																	// parent
																	// index

							String otmcv = corp.getTmcv();
							String otmcv1 = corp.getTmcv1();
							String ntmcv = corp.getNewTmcv();
							String ntmcv1 = corp.getNewtmcv1();

							String odiv = corp.getDivisor();
							String odiv1 = corp.getDivisor1();
							String ndiv = corp.getNewdivisor();
							String ndiv1 = corp.getNewdivisor1();
							String ori_affect = corp.getAffect();
							// update child index
							Hashtable hash = corp.getHash_affind();
							if (hash.isEmpty() == false) {
								for (Enumeration enumm = hash.keys(); enumm
										.hasMoreElements();) {
									String id = (String) enumm.nextElement();
									Object obj = hash.get(id);
									String index = obj.toString();
									Logging.debug("index child in commit=="
											+ index);
									corp.setI_index(index);
									UpdateCorp.undocurr_ind(corp, 1);
								}
							}
							corp.setI_index(ori_index);// set original index

							corp.setTmcv(otmcv);
							corp.setTmcv1(otmcv1);
							corp.setNewTmcv(ntmcv);
							corp.setNewtmcv1(ntmcv1);

							corp.setDivisor(odiv);
							corp.setDivisor1(odiv1);
							corp.setNewdivisor(ndiv);
							corp.setNewdivisor1(ndiv1);
							corp.setAffect(ori_affect);
						} else {
							UpdateCorp.undoassign_index_detail(corp); // update
																		// parent
																		// index
							UpdateCorp.undoupdate_affect_index(corp); // update
																		// child
																		// indices
						}
					}
					// corp.setCorpid(corp_val);
					if (butt == null) {
						tmcv_div_adj(corp);
						return new ActionForward("/pages/IndexEvents.jsp");
					} else {
						tmcv_div_adj(corp);
						return new ActionForward(
								"/pages/IndexEvents.jsp?flag=Exist");
					}
				} else {
					corp.StopRepetition = "a";
				}
			}
			if (button.equals("Radio")) // radio button either to recompute or
										// to recalculate index parameter
			{
				corp.setCommit_butt("1");
				Hashtable hash = corp.getHash_error();
				hash.clear();
				corp.setHash_error(hash);
				String radval = corp.getInd_comp();
				if (radval != null)
					if (radval.equals(""))
						radval = null;
				// compare date
				String dt = UpdateCorp.accept_date(); // get the current date
				String apply = corp.getApply_date();
				int chk_dt = ComputeIndexForm.CompareDate(apply, dt); // check
																		// for
																		// the
																		// current
																		// date
																		// and
																		// user's
																		// entered
																		// date
				Logging.debug("rad val is=" + radval);
				if (radval != null) {
					if (radval.equals("i")) // if recompute
					{
						if (chk_dt == 0)
							return new ActionForward("/pages/IndexHome.jsp?D1="
									+ corp.getI_index());
						else {
							Logging.debug("in rad val for index compute");
							ActionCorp.Hist_apply(corp, 2);
							Logging.debug("after index");
							return new ActionForward("/pages/IndexEvents.jsp");
						}
					}
					if (radval.equals("c")) // if recalculate
					{
						corp.setChk_but(null);
						corp_id = request.getParameter("corpid");
						// check whether this action is present in diary or not
						if (chk_dt == 0) {
							boolean flag = check_exist(corp.getI_index(), corp,
									corp_id, stk);
							corp.setCorpid(corp_id);
							if (flag == true) // if present
							{
								ref_hash(corp);
								hash = corp.getHash();
								hash.clear();
								corp.setHash(hash);
								return new ActionForward(
										"/pages/IndexCorporateDetails.jsp");
							}
							if (flag == false) // if not present
							{
								ActionCorp.recal_applyIndexDetail(con, connect,
										corp); // recalculate new index value
								tmcv_div_adj(corp); // remove E power
								return new ActionForward(
										"/pages/IndexEvents.jsp");
							}
						} else {
							ActionCorp.Hist_apply(corp, 0);
							return new ActionForward("/pages/IndexEvents.jsp");
						}
					}
				}
			}
			// if the selected action is present in diary, then on selecting
			// particular entry
			if (button.equals("Ok")) // on click of ok button
			{
				corp.setCommit_butt("1");
				String[] stock1 = new String[15];
				int cnt = 0;
				String rad_val = corp.getInd_comp();
				if (rad_val != null) {
					if (rad_val.equals(""))
						rad_val = null;
				}
				if (rad_val != null) // to recalculate or recomputed index
										// parameter
				{
					if (rad_val.equals("c")) // to recalculate
					{
						try {
							corp.reset2();
							corp.setNature(null);
							String corp_value = request
									.getParameter("corp_val");
							String stk1[] = request
									.getParameterValues("chk_but"); // collect
																	// the
																	// selected
																	// diary
																	// entry in
																	// an array
							if ((corp_value.equals("addstock"))
									| (corp_value.equals("deletestock"))) {
								int val = stk1.length;
								ref_hash(corp);
								// from dairy id get the respective stock id's
								// and store in hash
								Hashtable hash1 = corp.getHash1();
								Hashtable hash2 = corp.getHash2();
								for (int i = 0; i < val; i++) {
									String div[] = ActionCorp.token(stk1[i]);
									String stock = null;
									if (div[0].equals("true")) {
										String id = div[1];
										String query = ConnectInit.queries
												.getProperty("select_rep_cad");
										try {
											ResultSet rs = ListTypeClass1
													.getResult1(con, query, id);
											rs.next();
											stock = rs.getString("stock_id");
										} catch (Exception e) {
											Logging.error("error="
													+ e.getMessage());
										}
									}
									if (div[0].equals("false"))
										stock = div[1];

									stock1[cnt] = stock;
									cnt++;
									hash1.put(new String(stk1[i]), new String(
											stock));
									hash2.put(new String(stk1[i]), new String(
											stock));
								}
								corp.setHash1(hash1);
								corp.setHash2(hash2);
								ActionCorp.recal_applyIndexDetail(con, connect,
										corp);// recalculate new index value
								tmcv_div_adj(corp); // remove E power
								corp.setStock(stock1);
								return new ActionForward(
										"/pages/IndexEvents.jsp");
							}
							if (corp_value.equals("changeiwf")) {
								boolean flag = check_iwf(stk1, corp);// check
																		// whether
																		// selected
																		// action
																		// is
																		// exist
																		// in
																		// dairy
																		// or
																		// not
								String value = request.getParameter("values");
								if (flag == false) // if not
								{
									ref_hash(corp);
									// get stock id and put in hash
									Hashtable hash1 = corp.getHash1();
									Hashtable hash2 = corp.getHash2();
									int val = stk1.length;
									for (int i = 0; i < val; i++) {
										String div[] = ActionCorp
												.token(stk1[i]);
										String stock = null;
										if (div[0].equals("true")) {
											String id = div[1];
											String query = ConnectInit.queries
													.getProperty("select_rep_cad");
											try {
												ResultSet rs = ListTypeClass1
														.getResult1(con, query,
																id);
												rs.next();
												stock = rs
														.getString("stock_id");
											} catch (Exception e) {
												Logging.error("error="
														+ e.getMessage());
											}
										}
										if (div[0].equals("false"))
											stock = div[1];
										stock1[cnt] = stock;
										cnt++;
										hash1.put(new String(stk1[i]),
												new String(stock));
										hash2.put(new String(stk1[i]),
												new String(stock));
									}
									corp.setHash1(hash1);
									corp.setHash2(hash2);
									ActionCorp.recal_applyIndexDetail(con,
											connect, corp); // recalculate new
															// index value
									tmcv_div_adj(corp); // remove E power
									corp.setStock(stock1);
									return new ActionForward(
											"/pages/IndexEvents.jsp");
								}
								if (flag == true) // if exist in diary
								{
									boolean flg = check_value(stk1, corp); // check
																			// whether
																			// the
																			// entered
																			// value
																			// is
																			// equal
																			// to
																			// the
																			// existing
																			// value
									ref_hash(corp);
									// get stock id and store in hash
									Hashtable hash1 = corp.getHash1();
									Hashtable hash2 = corp.getHash2();
									int val = stk1.length;
									for (int i = 0; i < val; i++) {
										String div[] = ActionCorp
												.token(stk1[i]);
										String stock = null;
										if (div[0].equals("true")) {
											String id = div[1];
											String query = ConnectInit.queries
													.getProperty("select_rep_cad");
											try {
												ResultSet rs = ListTypeClass1
														.getResult1(con, query,
																id);
												rs.next();
												stock = rs
														.getString("stock_id");
											} catch (Exception e) {
												Logging.error("error="
														+ e.getMessage());
											}
										}
										if (div[0].equals("false"))
											stock = div[1];

										stock1[cnt] = stock;
										cnt++;
										hash1.put(new String(stk1[i]),
												new String(stock));
										hash2.put(new String(stk1[i]),
												new String(stock));
									}
									corp.setHash1(hash1);
									corp.setHash2(hash2);
									if (flg == true) // if value's are not equal
									{
										corp.setNature(null);
										corp.setStock(stock1);
										return new ActionForward(
												"/pages/IndexCorporateDetails.jsp");
									} else // if value's are equal
									{
										ActionCorp.recal_applyIndexDetail(con,
												connect, corp);// recalculate
																// new index
																// value
										tmcv_div_adj(corp); // remove E power
										corp.setStock(stock1);
										return new ActionForward(
												"/pages/IndexEvents.jsp");
									}
								}
							}
						} catch (Exception e) {
							Logging.error("error=" + e.getMessage());
						}
					}
				} else// if there is index closing value for particular date
				{
					corp.reset2();
					corp.setNature(null);
					String corp_value = request.getParameter("corp_val");
					String stk1[] = request.getParameterValues("chk_but");
					if ((corp_value.equals("addstock"))
							| (corp_value.equals("deletestock"))) {
						int val = stk1.length;
						ref_hash(corp);
						// store stock id in hash
						Hashtable hash1 = corp.getHash1();
						Hashtable hash2 = corp.getHash2();
						for (int i = 0; i < val; i++) {
							String div[] = ActionCorp.token(stk1[i]);
							String stock = null;
							if (div[0].equals("true")) {
								String id = div[1];
								String query = ConnectInit.queries
										.getProperty("select_rep_cad");
								try {
									ResultSet rs = ListTypeClass1.getResult1(
											con, query, id);
									rs.next();
									stock = rs.getString("stock_id");
								} catch (Exception e) {
									Logging.error("error=" + e.getMessage());
								}
							}
							if (div[0].equals("false"))
								stock = div[1];

							stock1[cnt] = stock;
							cnt++;
							// hash1.put(new String(stk1[i]),new
							// String(stk1[i]));
							// hash2.put(new String(stk1[i]),new
							// String(stk1[i]));
							hash1.put(new String(stk1[i]), new String(stock));
							hash2.put(new String(stk1[i]), new String(stock));
						}
						corp.setHash1(hash1);
						corp.setHash2(hash2);
						String dt = UpdateCorp.accept_date(); // get the current
																// date
						String apply = corp.getApply_date();
						int chk_dt = ComputeIndexForm.CompareDate(apply, dt);

						if (chk_dt == 0) {
							ActionCorp.applyIndexDetail(con, connect, corp);// calculate
																			// new
																			// values
							tmcv_div_adj(corp); // remove E power
							corp.setStock(stock1);
							return new ActionForward("/pages/IndexEvents.jsp");
						} else {
							corp.setR_type("index event");
							ActionCorp.Historic_cal(corp);
							// check for compute or recalculate
							String corp_nm = corp.getCorpid();
							if (corp_nm.equals("changeindcurr")) {
								tmcv_div_adj(corp); // remove E power
								return new ActionForward(
										"/pages/IndexEvents.jsp");
							} else {
								int flg = check_close(con, connect, corp);
								if (flg == 0) {
									corp.setStock(stock1);
									corp.setCommit_butt(null);
									return new ActionForward(
											"/pages/IndexEvents.jsp?close=Close");
								} else {
									ActionCorp.Hist_apply(corp, 1);
									tmcv_div_adj(corp); // remove E power
									corp.setStock(stock1);
									return new ActionForward(
											"/pages/IndexEvents.jsp");
								}
							}
						}
					}
					if (corp_value.equals("changeiwf")) {
						boolean flag = check_iwf(stk1, corp); // check exist in
																// diary or not
						String value = request.getParameter("values");
						if (flag == false) // if not
						{
							ref_hash(corp);
							// store stock in hash
							Hashtable hash1 = corp.getHash1();
							Hashtable hash2 = corp.getHash2();
							int val = stk1.length;
							for (int i = 0; i < val; i++) {
								String div[] = ActionCorp.token(stk1[i]);
								String stock = null;
								if (div[0].equals("true")) {
									String id = div[1];
									String query = ConnectInit.queries
											.getProperty("select_rep_cad");
									try {
										ResultSet rs = ListTypeClass1
												.getResult1(con, query, id);
										rs.next();
										stock = rs.getString("stock_id");
									} catch (Exception e) {
										Logging.error("error=" + e.getMessage());
									}
								}
								if (div[0].equals("false"))
									stock = div[1];

								stock1[cnt] = stock;
								cnt++;
								hash1.put(new String(stk1[i]),
										new String(stock));
								hash2.put(new String(stk1[i]),
										new String(stock));
							}
							corp.setHash1(hash1);
							corp.setHash2(hash2);
							String dt = UpdateCorp.accept_date(); // get the
																	// current
																	// date
							String apply = corp.getApply_date();
							int chk_dt = ComputeIndexForm
									.CompareDate(apply, dt);
							if (chk_dt == 0) {
								ActionCorp.applyIndexDetail(con, connect, corp);// calculate
																				// new
																				// value
								tmcv_div_adj(corp);// remove E power
								corp.setStock(stock1);
								return new ActionForward(
										"/pages/IndexEvents.jsp");
							} else {
								corp.setR_type("index event");
								ActionCorp.Historic_cal(corp);

								// check for compute or recalculate
								String corp_nm = corp.getCorpid();
								if (corp_nm.equals("changeindcurr")) {
									tmcv_div_adj(corp); // remove E power
									return new ActionForward(
											"/pages/IndexEvents.jsp");
								} else {
									int flg = check_close(con, connect, corp);
									if (flg == 0) {
										corp.setStock(stock1);
										return new ActionForward(
												"/pages/IndexEvents.jsp?close=Close");
									} else {
										ActionCorp.Hist_apply(corp, 1);
										tmcv_div_adj(corp); // remove E power
										corp.setStock(stock1);
										return new ActionForward(
												"/pages/IndexEvents.jsp");
									}
								}
							}
						}
						if (flag == true) // if exist in diary
						{
							boolean flg = check_value(stk1, corp);// check
																	// entered
																	// value is
																	// equal to
																	// existing
																	// value
							ref_hash(corp);
							// store stock in hash
							Hashtable hash1 = corp.getHash1();
							Hashtable hash2 = corp.getHash2();
							int val = stk1.length;
							for (int i = 0; i < val; i++) {
								String div[] = ActionCorp.token(stk1[i]);
								String stock = null;
								if (div[0].equals("true")) {
									String id = div[1];
									String query = ConnectInit.queries
											.getProperty("select_rep_cad");
									try {
										ResultSet rs = ListTypeClass1
												.getResult1(con, query, id);
										rs.next();
										stock = rs.getString("stock_id");
									} catch (Exception e) {
										Logging.error("error=" + e.getMessage());
									}
								}
								if (div[0].equals("false"))
									stock = div[1];

								stock1[cnt] = stock;
								cnt++;

								hash1.put(new String(stk1[i]),
										new String(stock));
								hash2.put(new String(stk1[i]),
										new String(stock));
							}
							corp.setHash1(hash1);
							corp.setHash2(hash2);
							if (flg == true) // if value mismatch
							{
								corp.setNature(null);
								corp.setStock(stock1);
								return new ActionForward(
										"/pages/IndexCorporateDetails.jsp");
							} else // if value match
							{
								String dt = UpdateCorp.accept_date(); // get the
																		// current
																		// date
								String apply = corp.getApply_date();
								int chk_dt = ComputeIndexForm.CompareDate(
										apply, dt);
								if (chk_dt == 0) {
									ActionCorp.applyIndexDetail(con, connect,
											corp);// calculate new value
									tmcv_div_adj(corp); // remove E power
									corp.setStock(stock1);
									return new ActionForward(
											"/pages/IndexEvents.jsp");
								} else {
									corp.setR_type("index event");
									ActionCorp.Historic_cal(corp);
									// check for compute or recalculate
									String corp_nm = corp.getCorpid();
									if (corp_nm.equals("changeindcurr")) {
										tmcv_div_adj(corp); // remove E power
										return new ActionForward(
												"/pages/IndexEvents.jsp");
									} else {
										int flg_val = check_close(con, connect,
												corp);
										if (flg_val == 0) {
											corp.setStock(stock1);
											return new ActionForward(
													"/pages/IndexEvents.jsp?close=Close");
										} else {
											ActionCorp.Hist_apply(corp, 1);
											tmcv_div_adj(corp); // remove E
																// power
											corp.setStock(stock1);
											return new ActionForward(
													"/pages/IndexEvents.jsp");
										}
									}
								}
							}
						}
					}
				}
			}
			if (button.equals("Go")) // if there is change in value then after
										// selecting original or new value
			{
				corp.setCommit_butt("1");
				corp.reset2();
				String rad_val = corp.getInd_comp();
				if (rad_val != null)
					if (rad_val.equals(""))
						rad_val = null;
				Logging.debug("iwf in historic===" + corp.getValues()
						+ "in request==" + request.getParameter("values"));
				if (rad_val != null) // to recalculate or recompute
				{
					if (rad_val.equals("c"))
						ActionCorp.recal_applyIndexDetail(con, connect, corp);// recalculate
																				// new
																				// index
																				// value
				} else // exist index closing valie
				{
					String dt = UpdateCorp.accept_date(); // get the current
															// date
					String apply = corp.getApply_date();
					int chk_dt = ComputeIndexForm.CompareDate(apply, dt);
					if (chk_dt == 0) {
						ActionCorp.applyIndexDetail(con, connect, corp); // calculate
																			// index
																			// value
						Logging.debug("iwf after apply==" + corp.getValues()
								+ "in req==" + request.getParameter("values"));
						tmcv_div_adj(corp); // remove E power
						return new ActionForward("/pages/IndexEvents.jsp");
					} else {
						corp.setR_type("index event");
						Logging.debug("iwf in historic===" + corp.getValues()
								+ "in request=="
								+ request.getParameter("values"));
						ActionCorp.Historic_cal(corp);

						// check for compute or recalculate
						String corp_nm = corp.getCorpid();
						if (corp_nm.equals("changeindcurr")) {
							tmcv_div_adj(corp); // remove E power
							return new ActionForward("/pages/IndexEvents.jsp");
						} else {
							int flg = check_close(con, connect, corp);
							if (flg == 0) {
								return new ActionForward(
										"/pages/IndexEvents.jsp?close=Close");
							} else {
								ActionCorp.Hist_apply(corp, 1);
								tmcv_div_adj(corp); // remove E power
								return new ActionForward(
										"/pages/IndexEvents.jsp");
							}
						}
					}
				}
			}
			if (button.equals("Affect")) // to get the details of affected
											// indices(child index)
			{
				String butt = corp.getButton();
				String corpnm = corp.getCorpid();
				String affect = corp.getAffect();
				if (affect != null)
					if ((affect.equals(""))
							| (affect.equals("Affected Chile Index"))) {
						affect = null;
						corp.reset_affect();
					}
				if (affect != null) {
					String rad_val = corp.getInd_comp();
					if (rad_val != null)
						if (rad_val.equals(""))
							rad_val = null;
					String dt = UpdateCorp.accept_date(); // get the current
															// date
					String apply = corp.getApply_date();
					int chk_dt = ComputeIndexForm.CompareDate(apply, dt); // check
																			// for
																			// the
																			// current
																			// date
																			// and
																			// user's
																			// entered
																			// date

					if (rad_val != null) // if the index doesn't have closing
											// value
					{
						if (butt == null) // if there is no undo
						{

							if (rad_val.equals("c")) // to recalculate
							{
								if (chk_dt == 0)
									ActionCorp.recal_affect_value(con, connect,
											corp, corp.getAffect()); // recalculate
																		// new
																		// value
								else
									ActionCorp.hist_affect_ind(corp,
											corp.getAffect());
							}
						} else // if there is undo
						{
							if (rad_val.equals("c")) // to recalculate
								ActionCorp.undo_recal_affect_value(corp,
										corp.getAffect()); // recalculate old
															// values
						}
					} else // if index have closing value
					{
						if (butt == null) // if there is no undo
						{
							if (chk_dt == 0) {
								if (corpnm.equals("changeindcurr")) // for
																	// change in
																	// index
																	// currency
								{
									// for value persistent
									String oritmcv = corp.getTmcv();
									String ntmcv = corp.getNewTmcv();
									String oridiv = corp.getDivisor();
									String ndiv = corp.getNewdivisor();
									ActionCorp.cal_curr_ind(con, connect, corp,
											corp.getAffect()); // calculate new
																// value
									corp.setTmcv1(corp.getTmcv());
									corp.setDivisor1(corp.getDivisor());
									corp.setNewtmcv1(corp.getNewTmcv());
									if (corp.getInd_div().equals("t"))
										corp.setNewdivisor1(corp.getDivisor1());
									else
										corp.setNewdivisor1(corp
												.getNewdivisor());
									corp.setTmcv(oritmcv);
									corp.setNewTmcv(ntmcv);
									corp.setDivisor(oridiv);
									corp.setNewdivisor(ndiv);
								} else
									// for other corporate action
									ActionCorp.affected_index_value(corp,
											corp.getAffect());// calculate new
																// value
							}// if dates are equal
							else // if dates are not equal
							{
								if (corpnm.equals("changeindcurr"))
									ActionCorp.hist_affect_ind(corp,
											corp.getAffect()); // calculate new
																// value
								else
									ActionCorp.hist_affected_index_value(corp,
											corp.getAffect());// calculate new
																// value
							}
						} else // if there is undo
						{
							if (corpnm.equals("changeindcurr")) {
								Logging.debug("in curr affect");
								// store old values
								String oritmcv = corp.getTmcv();
								String ntmcv = corp.getNewTmcv();
								String oridiv = corp.getDivisor();
								String ndiv = corp.getNewdivisor();
								// calculate affect index value
								ActionCorp.cal_curr_ind(con, connect, corp,
										affect);
								// store new values
								corp.setTmcv1(corp.getTmcv());
								corp.setDivisor1(corp.getDivisor());
								corp.setNewtmcv1(corp.getNewTmcv());
								if (corp.getInd_div().equals("t"))
									corp.setNewdivisor1(corp.getDivisor1());
								else
									corp.setNewdivisor1(corp.getNewdivisor());
								corp.setTmcv(oritmcv);
								corp.setNewTmcv(ntmcv);
								corp.setDivisor(oridiv);
								corp.setNewdivisor(ndiv);
							} else
								ActionCorp.undoaffected_index_value(corp,
										corp.getAffect()); // claculate old
															// values
						}

					}
					tmcv_div_adj(corp); // remove E power
					if (butt == null)
						return new ActionForward("/pages/IndexEvents.jsp");
					else
						return new ActionForward(
								"/pages/IndexEvents.jsp?flag=Exist");
				}
			}
		}
		return mapping.getInputForward();
	}

	// this method is used to check whether the selected action is present in
	// diary or not with status='n'
	// con paramter is added by samiksha and the new method is created
	public static boolean check_exist(String index, Corporate corp,
			String corp_id, String stk[], Connection con) {
		boolean flag = false;
		ResultSet rs=null;
		// Connection con=null;
		try {
			Connect connect = ConnectInit.getConnect(); // connection

			if (con == null) {
				con = connect.getdbConnection();
			}

			int val = 0;
			if (stk != null)
				val = stk.length; // get selected stock length
			for (int i = 0; i < val; i++) // check whether this action is
											// present in diary or not
			{
				String stock = stk[i];
				String qry = ConnectInit.queries
						.getProperty("get_corporate_list_index");
				 rs = ListTypeClass1.resultCorporate(con, qry);
				ListTypeClass1.check_corp_name(rs, corp_id, corp);
				if (rs != null)
					rs.close();

				String query = ConnectInit.queries
						.getProperty("select_index_corpdiary");
				rs = ListTypeClass1.check_dairy_exist(con, query,
						corp.getCorpid(), index, stock, corp.getApply_date());

				flag = rs.next();
				if (flag == true)
					break;
			}

		} catch (Exception e) {
			Logging.error("ERROR :" + e.getMessage());
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
		return flag;
	}

	// this method is used to check whether the selected action is present in
	// diary or not with status='n'
	public static boolean check_exist(String index, Corporate corp,
			String corp_id, String stk[]) {
		boolean flag = false;
		Connection con = null;
		try {
			Connect connect = ConnectInit.getConnect(); // connection

			// Change by Manoj Adekar for Dynamic connection using
			// getdbConnection() instead of getConnection()
			if (con == null) {
				con = connect.getdbConnection();
			}

			int val = 0;
			if (stk != null)
				val = stk.length; // get selected stock length
			for (int i = 0; i < val; i++) // check whether this action is
											// present in diary or not
			{
				String stock = stk[i];
				String qry = ConnectInit.queries
						.getProperty("get_corporate_list_index");
				ResultSet rs = ListTypeClass1.resultCorporate(con, qry);
				ListTypeClass1.check_corp_name(rs, corp_id, corp);
				if (rs != null)
					rs.close();

				String query = ConnectInit.queries
						.getProperty("select_index_corpdiary");
				rs = ListTypeClass1.check_dairy_exist(con, query,
						corp.getCorpid(), index, stock, corp.getApply_date());

				flag = rs.next();
				if (flag == true)
					break;
			}

		} catch (Exception e) {
			Logging.error("ERROR :" + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		return flag;
	}

	// this method is used to check whether the entered value is match with the
	// existing value
	public static boolean check_value(String stk[], Corporate corp) {
		Connect connect = ConnectInit.getConnect(); // connection
		Connection con = null;
		// Change by Manoj Adekar for Dynamic connection using getdbConnection()
		// instead of getConnection()
		if (con == null) {
			con = connect.getdbConnection();
		}

		boolean flag = false;
		try {
			int val = 0;
			if (stk != null)
				val = stk.length; // get the selected stock length
			String value = corp.getValues(); // get the entered value
			for (int i = 0; i < val; i++) // check whether values are equal or
											// not
			{
				String div[] = ActionCorp.token(stk[i]);
				if (div[0].equals("true")) {
					String query = ConnectInit.queries
							.getProperty("select_rep_cad");
					ResultSet rs = ListTypeClass1.getAffected(con, query,
							div[1]);
					rs.next();
					String iwf_val = rs.getString("values");
					if (!(iwf_val.equals(value))) {
						flag = true;
						break;
					}
				}
			}
		} catch (Exception e) {
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		return flag;

	}

	// this method is used to clear out hash content
	public static void ref_hash(Corporate corp) {
		Hashtable hash1 = corp.getHash1();
		Hashtable hash2 = corp.getHash2();
		hash1.clear();
		hash2.clear();
		corp.setHash1(hash1);
		corp.setHash2(hash2);

	}

	// check whether 'change in iwf' action exist in diary or not
	public static boolean check_iwf(String stk1[], Corporate corp) {
		boolean flag = false;
		int val = stk1.length;
		for (int i = 0; i < val; i++) {
			String div[] = ActionCorp.token(stk1[i]);
			if (div[0].equals("true")) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	// insert in hash all the selected stock
	public static void insert_hash(String stk[], Corporate corp) {
		ref_hash(corp);
		Hashtable hash1 = corp.getHash1();
		hash1.clear();
		corp.setHash1(hash1);
		hash1 = corp.getHash1();
		Hashtable hash2 = corp.getHash2();
		hash2.clear();
		corp.setHash2(hash2);
		hash2 = corp.getHash2();
		int val = 0;
		if (stk != null) {
			val = stk.length;
		}
		for (int i = 0; i < val; i++) {
			String stk1 = "false:" + stk[i];
			hash1.put(new String(stk1), new String(stk[i]));
			hash2.put(new String(stk1), new String(stk[i]));
		}
		corp.setHash1(hash1);
		corp.setHash2(hash2);
	}

	// this method is used to display stock's, who doesn't have the closing
	// value
	public static void check_hash_error(Corporate corp) {
		Connect connect = ConnectInit.getConnect();
		Connection con = null;
		try {
			// Change by Manoj Adekar for Dynamic connection using
			// getdbConnection() instead of getConnection()
			if (con == null) {
				con = connect.getdbConnection();
			}
			try {
				StringBuffer stb = corp.getStr();
				int ls = stb.length();
				stb.delete(0, ls);
				Hashtable hash_error = corp.getHash_error();
				Logging.debug("hash error in action=" + hash_error);
				if (hash_error.isEmpty() == false) {
					for (Enumeration enum1 = hash_error.keys(); enum1
							.hasMoreElements();) {
						String id2 = (String) enum1.nextElement();
						Logging.debug("id2 in err=" + id2);
						String query = ConnectInit.queries
								.getProperty("detail_stock_master");
						ResultSet rs = ListTypeClass1.getResult1(con, query,
								id2);
						rs.next();
						String stock_id = rs.getString("stock_name");
						stb.append(stock_id);
					}
					corp.setStr(stb);
				}
				Logging.debug("string buff==" + corp.getStr());
			} catch (Exception e) {
				Logging.error("Error==" + e.getMessage());
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

	// this method is used to remove E power, from calculated tmcv and divisor
	public static void tmcv_div_adj(Corporate corp) {
		try {
			check_hash_error(corp);
			// org.jfree.chart.demo.servlet.AdjustDecimal adj=new
			// org.jfree.chart.demo.servlet.AdjustDecimal();
			AdjustDecimal adj = ConnectInit.getAdjustDecimal();
			int val = 0;

			// new TMCV
			String ntmcv = corp.getNewTmcv();
			StringTokenizer st = new StringTokenizer(ntmcv, ",");
			val = st.countTokens();
			if (val == 1) {
				if ((ntmcv != null) | (!(ntmcv.equals("")))) {
					ntmcv = adj.shareholdingpatt(ntmcv);
					ntmcv = AdjustDecimal.ArrangeAsNumeric(ntmcv);
					corp.setNewTmcv(ntmcv);
				}
			}
			// new Divisor
			String ndiv = corp.getNewdivisor();
			st = new StringTokenizer(ndiv, ",");
			val = st.countTokens();
			if (val == 1) {
				if ((ndiv != null) | (!(ndiv.equals("")))) {
					ndiv = adj.shareholdingpatt(ndiv);
					ndiv = AdjustDecimal.ArrangeAsNumeric(ndiv);
					corp.setNewdivisor(ndiv);
				}
			}
			// old TMCV
			String otmcv = corp.getTmcv();
			st = new StringTokenizer(otmcv, ",");
			val = st.countTokens();
			if (val == 1) {
				otmcv = AdjustDecimal.ArrangeAsNumeric(otmcv);
				corp.setTmcv(otmcv);
			}
			// old Divisor
			String odiv = corp.getDivisor();
			st = new StringTokenizer(odiv, ",");
			val = st.countTokens();
			if (val == 1) {
				odiv = AdjustDecimal.ArrangeAsNumeric(odiv);
				corp.setDivisor(odiv);
			}

			// new child TMCV
			String ntmcv1 = corp.getNewtmcv1();
			if ((ntmcv1 != null) | (!(ntmcv1.equals("")))) {
				ntmcv1 = adj.shareholdingpatt(ntmcv1);
				ntmcv1 = AdjustDecimal.ArrangeAsNumeric(ntmcv1);
				corp.setNewtmcv1(ntmcv1);
			}
			// new child Divisor
			String ndiv1 = corp.getNewdivisor1();
			if ((ndiv1 != null) | (!(ndiv1.equals("")))) {
				ndiv1 = adj.shareholdingpatt(ndiv1);
				ndiv1 = AdjustDecimal.ArrangeAsNumeric(ndiv1);
				corp.setNewdivisor1(ndiv1);
			}
			// old child TMCV
			String otmcv1 = corp.getTmcv1();
			st = new StringTokenizer(otmcv1, ",");
			otmcv1 = AdjustDecimal.ArrangeAsNumeric(otmcv1);
			corp.setTmcv1(otmcv1);
			// old child Divisor
			String odiv1 = corp.getDivisor1();
			odiv1 = AdjustDecimal.ArrangeAsNumeric(odiv1);
			corp.setDivisor1(odiv1);
		} catch (Exception e) {
			Logging.error("Error=" + e.getMessage());
		}
	}

	public static void check_curr(Connection con, Connect connect,
			Corporate corp) {
		try {
			String ft = corp.getFtcurrency();
			if (ft != null) {
				if ((ft.equals("")) | (ft.equals("Select Currency"))) {
					ft = null;
					corp.setCurr_val("");
				}
			}
			if (ft != null) {
				// considering that this combination 'id' will never change in
				// exchange_rate_dialy table
				corp.setCheck_curr(false);
				// compare date's
				String dt = UpdateCorp.accept_date(); // get the current date
				String apply = corp.getApply_date();
				int chk_dt = ComputeIndexForm.CompareDate(apply, dt); // check
																		// for
																		// the
																		// current
																		// date
																		// and
																		// user's
																		// entered
																		// date

				// get the respective currency value
				ResultSet rs = null;
				if (chk_dt == 0) {
					String query = ConnectInit.queries
							.getProperty("resp_exrate_value");
					rs = ListTypeClass1.getResult12(con, query,
							corp.getFtcurrency());
				} else {
					String query = ConnectInit.queries
							.getProperty("resp_undo_curr_val");
					rs = ListTypeClass1.getResult_apply(con, query,
							corp.getFtcurrency(), corp.getApply_date());
				}
				if (rs != null) {
					rs.next();
					corp.setCurr_val(rs.getString("ex_last_value"));
					rs.close();
				}

				int oldcur = 0;
				int newcur = 0;
				String query1 = ConnectInit.queries.getProperty("resp_ftcurr");
				ResultSet rs2 = ListTypeClass1.getAffected(con, query1,
						corp.getFtcurrency());
				int fcurr = 0;
				int tcurr = 0;
				if (rs2 != null && rs2.next()) {
					fcurr = rs2.getInt("from_currency_id");
					tcurr = rs2.getInt("to_currency_id");
					rs2.close();
				}
				int curid = Integer.parseInt(corp.getCurrencyid());
				if (curid == fcurr) {
					oldcur = fcurr;
					newcur = tcurr;
				}
				if (curid == tcurr) {
					oldcur = tcurr;
					newcur = fcurr;
				}
				double currval = 0.0;
				query1 = ConnectInit.queries
						.getProperty("currency_combination");
				rs2 = ListTypeClass1.getResult_corp(con, query1,
						Integer.toString(newcur), Integer.toString(oldcur));
				if (rs2 != null && rs2.next()) {
					currval = Double.parseDouble(corp.getCurr_val());
				} else {
					query1 = ConnectInit.queries
							.getProperty("currency_combination");
					rs2 = ListTypeClass1.getResult_corp(con, query1,
							Integer.toString(oldcur), Integer.toString(newcur));
					if (rs2 != null && rs2.next()) {
						currval = (1 / Double.parseDouble(corp.getCurr_val()));
					}
					rs2.close();
				}
				corp.setCurr_val(Double.toString(currval));
			}
		} catch (Exception e) {
			Logging.error("Error=" + e.getMessage());
		}

	}

	// this method is used to check whether index has closing value or not for
	// apply on date
	public static int check_close(Connection con, Connect c, Corporate corp) {
		int flag = 0;
		//added by samiksha
		//starts
		Connect connect = ConnectInit.getConnect(); // connection
		if (con == null) {
			con = connect.getdbConnection();
		}
		
		//ends
		String query = ConnectInit.queries.getProperty("select_resp_close");
		try {
			ResultSet rs = ListTypeClass1.getResult_apply(con, query,
					corp.getI_index(), corp.getApply_date());
			if (rs.next()) {
				String close = rs.getString("index_closing_value");
				rs.close();
				if (close == null)
					flag = 0;
				else
					flag = 1;
			} else
				flag = 0;
		} catch (Exception e) {
			Logging.debug("Error=" + e.getMessage());
			System.out.println("Exception ---------"+e.getMessage());
		}
		return flag;
	}

	// this method is used to remove ',' from index parameters
	public static void remove_token(Corporate corp) {
		try {
			// old TMCV
			String otmcv = corp.getTmcv();
			StringTokenizer st = new StringTokenizer(otmcv, ",");
			int val = st.countTokens();
			String str = null;
			if (val > 1) {
				String ttmcv = null;
				String div[] = new String[10];
				div = ActionCorp.token4(otmcv); // remove ','
				
				//Samiksha- why need to calculate TMCV again?
				for (int i = 0; i < val; i++) {
					if (ttmcv == null)
						ttmcv = div[i];
					else
						ttmcv = ttmcv + div[i];
				}
				corp.setTmcv(ttmcv);
			}
			// new TMCV
			String ntmcv = corp.getNewTmcv();
			st = new StringTokenizer(ntmcv, ",");
			val = st.countTokens();
			str = null;
			if (val > 1) {
				String ttmcv = null;
				String div[] = new String[10];
				div = ActionCorp.token4(ntmcv); // remove ','
				for (int i = 0; i < val; i++) {
					if (ttmcv == null)
						ttmcv = div[i];
					else
						ttmcv = ttmcv + div[i];
				}
				corp.setNewTmcv(ttmcv);
			}

			// old Divisor
			String odiv = corp.getDivisor();
			st = new StringTokenizer(odiv, ",");
			val = st.countTokens();
			str = null;
			if (val > 1) {
				String ttmcv = null;
				String div[] = new String[10];
				div = ActionCorp.token4(odiv); // remove ','
				for (int i = 0; i < val; i++) {
					if (ttmcv == null)
						ttmcv = div[i];
					else
						ttmcv = ttmcv + div[i];
				}
				corp.setDivisor(ttmcv);
			}

			// new Divisor
			String ndiv = corp.getNewdivisor();
			st = new StringTokenizer(ndiv, ",");
			val = st.countTokens();
			str = null;
			if (val > 1) {
				String ttmcv = null;
				String div[] = new String[10];
				div = ActionCorp.token4(ndiv); // remove ','
				for (int i = 0; i < val; i++) {
					if (ttmcv == null)
						ttmcv = div[i];
					else
						ttmcv = ttmcv + div[i];
				}
				corp.setNewdivisor(ttmcv);
			}

			// old child TMCV
			String otmcv1 = corp.getTmcv1();
			st = new StringTokenizer(otmcv1, ",");
			val = st.countTokens();
			str = null;
			if (val > 1) {
				String ttmcv = null;
				String div[] = new String[10];
				div = ActionCorp.token4(otmcv1); // remove ','
				for (int i = 0; i < val; i++) {
					if (ttmcv == null)
						ttmcv = div[i];
					else
						ttmcv = ttmcv + div[i];
				}
				corp.setTmcv1(ttmcv);
			}
			// new child TMCV
			String ntmcv1 = corp.getNewtmcv1();
			st = new StringTokenizer(ntmcv1, ",");
			val = st.countTokens();
			str = null;
			if (val > 1) {
				String ttmcv = null;
				String div[] = new String[10];
				div = ActionCorp.token4(ntmcv1); // remove ','
				for (int i = 0; i < val; i++) {
					if (ttmcv == null)
						ttmcv = div[i];
					else
						ttmcv = ttmcv + div[i];
				}
				corp.setNewtmcv1(ttmcv);
			}

			// old child Divisor
			String odiv1 = corp.getDivisor1();
			st = new StringTokenizer(odiv1, ",");
			val = st.countTokens();
			str = null;
			if (val > 1) {
				String ttmcv = null;
				String div[] = new String[10];
				div = ActionCorp.token4(odiv1); // remove ','
				for (int i = 0; i < val; i++) {
					if (ttmcv == null)
						ttmcv = div[i];
					else
						ttmcv = ttmcv + div[i];
				}
				corp.setDivisor1(ttmcv);
			}

			// new child Divisor
			String ndiv1 = corp.getNewdivisor1();
			st = new StringTokenizer(ndiv1, ",");
			val = st.countTokens();
			str = null;
			if (val > 1) {
				String ttmcv = null;
				String div[] = new String[10];
				div = ActionCorp.token4(ndiv1); // remove ','
				for (int i = 0; i < val; i++) {
					if (ttmcv == null)
						ttmcv = div[i];
					else
						ttmcv = ttmcv + div[i];
				}
				corp.setNewdivisor1(ttmcv);
			}
		} catch (Exception e) {
			Logging.error("Error==" + e.getMessage());
		}
	}
}
