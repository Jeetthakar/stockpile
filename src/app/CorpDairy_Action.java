/*
 * Created on Jan 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.harrier.initializeation.ConnectInit;

/**
 * @author pranoti
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class CorpDairy_Action extends Action {
	static Logger Logging = Logger.getLogger(CorpDairy_Action.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Corporate corp = (Corporate) form; // bean object

		Connect connect = ConnectInit.getConnect(); // connection
		Connection con = null;
		// Change by Manoj Adekar for Dynamic connection using getdbConnection()
		// instead of getConnection()
		try {
			if (con == null) {
				con = connect.getdbConnection();
			}

			String button = request.getParameter("ap_co_button"); // any action
																	// related
																	// to diary
			if (button != null) {
				if (button.equals("Ctype")) // to check which
											// event(stock,index,event)
				{
					String ctype = request.getParameter("r_type");
					return new ActionForward(
							"/pages/CorporateDiary.jsp?r_type=" + ctype);
				}
				if (button.equals("Dairy_go")) // to view different date's
												// corporate action
				{
					Hashtable hash = corp.getHash();
					hash.clear();
					corp.setHash(hash);
					String val = request.getParameter("from_date");
					corp.setFdate(val);
					String val1 = request.getParameter("to_date");
					corp.setTdate(val1);
					return new ActionForward("/pages/CorporateDiary.jsp");
				}
				if (button.equals("Check")) // to check which type of data user
											// wants to view(only
											// exchange,index,stock,etc..)
				{
					String rad_val = corp.getExc_stk();
					if ((rad_val.equals("e")) | (rad_val.equals("es"))) // if
																		// only
																		// exchange
																		// or
																		// exchange
																		// and
																		// stock
					{
						try {
							String qry = ConnectInit.queries
									.getProperty("select_system_config");
							ResultSet rs1 = ListTypeClass1.resultCorporate(con,
									qry);
							rs1.next();
							corp.setExch(rs1.getString("stock_ex_id"));
							corp.setStkid(null);
							corp.setI_index(null);
						} catch (Exception e) {
						}
					}
					if ((rad_val.equals("s")) | (rad_val.equals("is")))// if
																		// only
																		// stock
																		// or
																		// index
																		// and
																		// stock
					{
						corp.setExch(null);
						corp.setStkid(null);
					}
					if (rad_val.equals("s"))
						corp.setI_index(null);
					if ((rad_val.equals("i")) | (rad_val.equals("is")))// if
																		// only
																		// index
																		// or
																		// index
																		// and
																		// stock
					{
						corp.setI_index(null);
						corp.setStkid(null);
						corp.setExch(null);
					}
				}
			}

			String button1 = request.getParameter("ap_co_button1");// any action
																	// related
																	// to stock
																	// event
			if (button1 != null) {
				if (button1.equals("New"))// to add new corporate action
				{
					try {
						corp.setButton(null);
						corp.setAmt_per(null);
						corp.setUpflg("New");
						corp.setPercent(null);
						// get default stock exchange id
						String qry = ConnectInit.queries
								.getProperty("select_system_config");
						ResultSet rs1 = ListTypeClass1
								.resultCorporate(con, qry);
						rs1.next();
						corp.setExc(rs1.getString("stock_ex_id"));
						rs1.close();
						// refresh data
						corp.setS_stock(null);
						corp.reset_event();
						corp.reset3();
						corp.setCorpid(null);
						// corp.setApplied_date(null);
						corp.setTime(null);
						corp.setExist(null);
					} catch (Exception e) {
						Logging.error(" error=" + e.getMessage());
					}
					return new ActionForward("/pages/NewCorporateAction.jsp");
				}
				if (button1.equals("NDelete"))// to cancel delete action
				{
					corp.setC_Cad(null);
					return new ActionForward(
							"/pages/CorporateDiary.jsp?r_type=stock event");
				}
				if (button1.equals("YDelete"))// to delete any record
				{
					String query = ConnectInit.queries
							.getProperty("delete_corp");
					String cad[] = request.getParameterValues("c_Cad");

					int len = cad.length; // get the length of selected checkbox
					for (int i = 0; i < len; i++) // delete one by one all the
													// selected item
					{
						corp.setEvent_id(cad[i]);
						UpdateCorp.delete_event(con, query, corp);
					}
					corp.setEvent_id(null);
					corp.setR_type("stock event");
					return new ActionForward("/pages/CorporateDiary.jsp");
				}
				if (button1.equals("Apply")) // on click of apply button
				{
					corp.setCommit_butt(null);
					String butt = corp.getButton();
					if (butt != null)
						if (butt.equals(""))
							butt = null;

					if (butt == null)// if there is no undo
					{
						String str[] = request.getParameterValues("c_Cad"); // get
																			// how
																			// much
																			// record
																			// at
																			// a
																			// time
																			// is
																			// to
																			// be
																			// apply
						int len = str.length;
						float temp_amt = 0;
						int temp_share = 0, flg = 0;
						ref_hash(corp);
						Hashtable hash1 = corp.getHash1();
						for (int i = 0; i < len; i++) // get the details of
														// respective diary id
						{
							try {
								hash1.put(new String(str[i]),
										new String(str[i]));
								String query = ConnectInit.queries
										.getProperty("select_rep_cad");
								ResultSet rs = ListTypeClass1.getResult1(con,
										query, str[i]);
								rs.next();
								String cam = rs.getString("cam_id");
								String get_name = ConnectInit.queries
										.getProperty("get_corp_name");
								String cam_name = ActionCorp.getcorpname(con,
										get_name, cam);
								StringTokenizer st = new StringTokenizer(
										cam_name, " ");
								int val = st.countTokens();
								if (val > 1) {
									String div[] = ActionCorp.token2(cam_name);
									corp.setCorpid((div[0] + div[1])
											.toLowerCase().trim());
								} else {
									corp.setCorpid(cam_name.toLowerCase()
											.trim());
								}
								String qry = ConnectInit.queries
										.getProperty("select_corp_diary");
								ResultSet rs1 = ListTypeClass1.getResult1(con,
										qry, str[i]);
								rs1.next();
								corp.setStid(rs1.getString("stock_id"));
								corp.setS_stock(rs.getString("stock_id"));
								corp.setExc(rs1.getString("stock_exchange_id"));
								cam_name = cam_name.toLowerCase().trim();
								if (cam_name.equals("cash dividend")) {
									float amnt = rs.getFloat("amount");
									temp_amt = temp_amt + amnt;
									flg = 1;
								}
								if (cam_name.equals("shares buyback")) {
									float amnt = rs.getFloat("amount");
									temp_amt = temp_amt + amnt;
									int share = rs.getInt("values");
									temp_share = temp_share + share;
									flg = 1;
								}
								if ((cam_name.equals("share issuance"))
										| (cam_name.equals("adr issue"))) {
									int share = rs.getInt("values");
									temp_share = temp_share + share;
									flg = 1;
								}
								if (cam_name.equals("capital reduce")) {
									temp_amt = rs.getFloat("amount");
									int share = rs.getInt("values");
									temp_share = temp_share + share;
									flg = 1;
								}
								if (flg == 1) {
									corp.setAmt(Float.toString(temp_amt));
									corp.setShare(Integer.toString(temp_share));
								} else {
									corp.setAmt(rs.getString("amount"));
									corp.setShare(rs.getString("values"));
								}
								corp
										.setRatio1(rs
												.getString("ratio_for_shares"));
								corp.setRatio2(rs
										.getString("ratio_shares_offered"));
								corp.setApply_date(rs
										.getString("apply_on_date"));
								rs.close();
								rs1.close();
							} catch (Exception e) {
								Logging.error("error==" + e.getMessage());
							}
						}
						corp.reset_stock();
						corp.setHash1(hash1);
						if (corp.getAmt() != null)
							if (corp.getAmt().equals("0"))
								corp.setAmt(null);
						if (corp.getShare() != null) {
							if (corp.getShare().equals("0"))
								corp.setShare(null);
						}

						// get the stock detail
						String query = ConnectInit.queries
								.getProperty("stock_details_for_ca");
						ResultSet rs = ListTypeClass1.getResult12(con, query,
								corp.getS_stock());
						try {
							corp.setResult(rs);
						} catch (Exception e) {
							Logging.error("ListTypeClass1:Error in result set "
									+ e.getMessage());
						}
						return new ActionForward(
								"/pages/NCorporateAction.jsp?flag=Exist");
					} else // if there is undo
					{
						// at a time only one record has to be undone
						try {
							corp.setLeng(null);
							corp.setC_Cad(request.getParameter("c_Cad"));
							// get the details of respective diary id
							String query = ConnectInit.queries
									.getProperty("select_rep_cad");
							ResultSet rs = ListTypeClass1.getAffected(con,
									query, corp.getC_Cad());
							rs.next();
							String stock = rs.getString("stock_id");
							corp.setStid(stock);
							corp.setApplied_date(rs.getString("applied_date"));
							corp.setTime(rs.getString("applied_time"));
							String apply = rs.getString("apply_on_date");
							if (apply == null)
								apply = corp.getApplied_date();
							corp.setApply_date(apply);
							rs.close();
						} catch (Exception e) {
						}
						// call undoaction page to check whether any action
						// applied on this stock or its repective index
						return new ActionForward("/pages/UndoAction.jsp");
					}
				}
				if (button1.equals("Update")) // to update any record from diary
				{
					ref_hash(corp);
					corp.setApplied_date(null);
					corp.setTime(null);
					corp.setAmt_per(null);
					corp.setExist(null);
					String str[] = request.getParameterValues("c_Cad");
					int len = str.length;
					Hashtable hash = corp.getHash();
					for (int i = 0; i < len; i++)
						hash.put(new String(str[i]), new String(str[i]));
					corp.setHash(hash);
					corp.setUpflg("Update");
					ListTypeClass1.corp_detail(corp); // get the detail of
														// record to be updated
					corp.setButton(null);
					return new ActionForward(
							"/pages/NewCorporateAction.jsp?upflg=Update");
				}
				if (button1.equals("Import")) // on click of import from file
												// button
				{
					corp.setButton(null);
					return new ActionForward("/pages/ImportNewStock.jsp");
				}
				if (button1.equals("Undo")) // to enable all checkbox with
											// status='y' and disable with
											// status='n'
				{
					corp.setButton("Undo");
					corp.setR_type("stock event");
					return new ActionForward("/pages/CorporateDiary.jsp");
				}
				if (button1.equals("Reset")) // to reset the effect of undo
				{
					corp.setButton(null);
					corp.setR_type("stock event");
					return new ActionForward("/pages/CorporateDiary.jsp");
				}
			}

			String new_corp = corp.getNew_corp_but();// any action related to
														// new or update CA in
														// stock event
			if (new_corp != null) {
				if (new_corp.equals("Exc"))// on select of exchange
				{
					String flag = request.getParameter("upflg");
					if (flag != null) // value persistent in case of update
					{
						if (flag.equals("Update")) {
							corp.setRatio1(request.getParameter("s_ratio1"));
							corp.setRatio2(request.getParameter("s_ratio2"));
							corp.setAmt(request.getParameter("amount"));
						}
					}
					corp.setNew_corp_but(null);
					return new ActionForward("/pages/NewCorporateAction.jsp");
				}
				if (new_corp.equals("Action")) // on select of corporate action
				{
					corp.setAmt_per(null);
					String flag = request.getParameter("upflg");
					if (flag != null) {
						if (flag.equals("Update")) {
							corp.setRatio1(request.getParameter("s_ratio1"));
							corp.setRatio2(request.getParameter("s_ratio2"));
							corp.setAmt(request.getParameter("amount"));

							String corp_val = request.getParameter("corpid");
							// get CA id
							String qry = ConnectInit.queries
									.getProperty("get_corporate_list_stock");
							try {
								ResultSet rs = ListTypeClass1.resultCorporate(
										con, qry);
								ListTypeClass1.check_corp_name(rs, corp_val,
										corp);
								rs.close();
							} catch (Exception e) {
								Logging.error("error==" + e.getMessage());
							}

							// check whether this CA is already present in diary
							// with status='n'
							String query = ConnectInit.queries
									.getProperty("check_corp_existance");
							String stock = corp.getS_stock();
							String apply = request.getParameter("apply_date");
							try {
								ResultSet rs = ListTypeClass1.getresp_cad(con,
										query, corp.getCorpid(), stock, apply);
								if (rs.next()) {
									corp.setCorpid(corp_val);
									corp.setExist("2");
									corp.setNew_corp_but(null);
									return new ActionForward(
											"/pages/NewCorporateAction.jsp?upflg=Update");
								} else {
									corp.setCorpid(corp_val);
									corp.setNew_corp_but(null);
									return new ActionForward(
											"/pages/NewCorporateAction.jsp?upflg=Update");
								}
							} catch (Exception e) {
								Logging.error("Error=" + e.getMessage());
							}
						}
					} else {
						corp.setNew_corp_but(null);
						return new ActionForward(
								"/pages/NewCorporateAction.jsp");
					}
					return new ActionForward("/pages/NewCorporateAction.jsp");
				}
				synchronized (corp.StopRepetition) // for duplicate entry
				{
					corp.StopRepetition = corp.StopRepetition + "";
					if (new_corp.equals("NewSubmit")
							&& (!(corp.StopRepetition.trim().equals("end"))))// on
																				// click
																				// of
																				// submit
																				// button
																				// to
																				// insert
																				// new
																				// record
					{
						corp.StopRepetition = "end";
						corp.setR_type("stock event");
						corp.setRatio1(request.getParameter("s_ratio1").trim());
						corp.setRatio2(request.getParameter("s_ratio2").trim());
						corp.setBc_start(request.getParameter("bc_start"));
						corp.setBc_end(request.getParameter("bc_end"));
						corp.setNc_start(request.getParameter("nc_start"));
						corp.setNc_end(request.getParameter("nc_end"));
						String corp_val = request.getParameter("corpid");
						// get CA id
						String qry = ConnectInit.queries
								.getProperty("get_corporate_list_stock");
						try {
							ResultSet rs = ListTypeClass1.resultCorporate(con,
									qry);
							ListTypeClass1.check_corp_name(rs, corp_val, corp);
							rs.close();
						} catch (Exception e) {
							Logging.error("error==" + e.getMessage());
						}
						corp.setApplied_date(null);
						corp.setStid(request.getParameter("s_stock"));
						corp.setStatus("n");
						corp.setInd_comp(null);
						String flag = request.getParameter("upflg");

						// check whether this CA is already present in diary
						// with status='n'
						String query = ConnectInit.queries
								.getProperty("check_corp_existance");
						String stock = request.getParameter("s_stock");
						String apply = request.getParameter("apply_date");
						corp.setNew_corp_but("");
						try {
							ResultSet rs = ListTypeClass1.getresp_cad(con,
									query, corp.getCorpid(), stock, apply);
							if (rs.next()) // if present
							{
								// send the response back with an alert
								corp.setCorpid(corp_val);
								corp.setExist("1");
								corp.setNew_corp_but(null);
								return new ActionForward(
										"/pages/NewCorporateAction.jsp");
							} else // if not present
							{
								String amount = request.getParameter("amount");
								check_diary(corp, amount); // with respect to
															// amount calculate
															// percent
								// insert into diary
								query = ConnectInit.queries
										.getProperty("insert_into_cad_values");
								String nextval = ConnectInit.queries
										.getProperty("get_sequence_cad_id");
								UpdateCorp.insert_into_cad(con, query, nextval,
										corp, null);

								corp.setCorpid(corp_val);
								corp.setUpflg(null);
								corp.setNew_corp_but(null);
								corp.setR_type("stock event");
								return new ActionForward(
										"/pages/CorporateDiary.jsp?ref_flag=1");
							}
						} catch (Exception e) {
							Logging.error("error=" + e.getMessage());
						}
					} else {
						corp.StopRepetition = "a";
					}
				}
				if (new_corp.equals("Submit")) // on click of submit button,
												// update the existing record
				{
					corp.setRatio1(request.getParameter("s_ratio1").trim());
					corp.setRatio2(request.getParameter("s_ratio2").trim());
					corp.setBc_start(request.getParameter("bc_start"));
					corp.setBc_end(request.getParameter("bc_end"));
					corp.setNc_start(request.getParameter("nc_start"));
					corp.setNc_end(request.getParameter("nc_end"));
					String corp_val = request.getParameter("corpid");
					// get CA id
					String qry = ConnectInit.queries
							.getProperty("get_corporate_list_stock");
					try {
						ResultSet rs = ListTypeClass1.resultCorporate(con, qry);
						ListTypeClass1.check_corp_name(rs, corp_val, corp);
						rs.close();
					} catch (Exception e) {
						Logging.error("error==" + e.getMessage());
					}

					corp.setApplied_date(null);
					// corp.setStid(request.getParameter("s_stock"));
					corp.setStatus("n");
					corp.setInd_comp(null);
					String flag = request.getParameter("upflg");
					// String stock=request.getParameter("s_stock");
					String apply = request.getParameter("apply_date");
					String amount = request.getParameter("amount");
					check_diary(corp, amount); // with respect to amount
												// calculate percent

					String query = ConnectInit.queries
							.getProperty("update_resp_cad"); // update the
																// record
					String nextval = ConnectInit.queries
							.getProperty("get_sequence_cad_id");
					UpdateCorp.insert_into_cad(con, query, nextval, corp, flag);
					corp.setUpflg(null);
					corp.setCorpid(corp_val);
					corp.setR_type("stock event");
					return new ActionForward("/pages/CorporateDiary.jsp");
				}
				if (new_corp.equals("Radio"))// in case of new entry if the
												// record is already
												// present,then to continue or
												// to stocp
				{
					String ind_val = corp.getInd_comp();
					if (ind_val != null) {
						if (ind_val.equals("b")) // back to the diary
						{
							corp.setExist(null);
							corp.setR_type("stock event");
							return new ActionForward(
									"/pages/CorporateDiary.jsp");
						}
						if (ind_val.equals("c"))// continue inserting new record
						{
							String corp_val = corp.getCorpid();
							String amount = request.getParameter("amount");
							// get CA id
							String chk_exist = corp.getExist();
							try {
								String qry = ConnectInit.queries
										.getProperty("get_corporate_list_stock");
								ResultSet rs = ListTypeClass1.resultCorporate(
										con, qry);
								ListTypeClass1.check_corp_name(rs, corp_val,
										corp);
								rs.close();
							} catch (Exception e) {
								Logging.error("error==" + e.getMessage());
							}

							if (chk_exist.equals("1")) {

								check_diary(corp, amount); // with respect to
															// amount calculate
															// percent
								// insert new record
								String query = ConnectInit.queries
										.getProperty("insert_into_cad_values");
								String nextval = ConnectInit.queries
										.getProperty("get_sequence_cad_id");
								UpdateCorp.insert_into_cad(con, query, nextval,
										corp, null);
							}
							if (chk_exist.equals("2")) {
								corp.setInd_comp(null);
								String flag = request.getParameter("upflg");
								check_diary(corp, amount); // with respect to
															// amount calculate
															// percent

								String query = ConnectInit.queries
										.getProperty("update_resp_cad"); // update
																			// the
																			// record
								String nextval = ConnectInit.queries
										.getProperty("get_sequence_cad_id");
								UpdateCorp.insert_into_cad(con, query, nextval,
										corp, flag);
							}
							corp.setUpflg(null);
							corp.setCorpid(corp_val);
							corp.setExist(null);
							corp.setR_type("stock event");
							return new ActionForward(
									"/pages/CorporateDiary.jsp");

						}
					}
				}
				corp.setUpflg(null);
				corp.setNew_corp_but(null);
				corp.setR_type("stock event");
				return new ActionForward("/pages/CorporateDiary.jsp?ref_flag=1");
			}

			String button2 = request.getParameter("ap_co_button2");// any action
																	// related
																	// to index
																	// event
			if (button2 != null) {
				if (button2.equals("Undo"))// to enable all checkbox with
											// status='y' and disable with
											// status='n'
				{
					corp.setButton("Undo");
					corp.setR_type("index event");
					return new ActionForward("/pages/CorporateDiary.jsp");
				}
				if (button2.equals("Reset")) // to reset undo effect
				{
					corp.setButton(null);
					corp.setR_type("index event");
					return new ActionForward("/pages/CorporateDiary.jsp");
				}
				if (button2.equals("Apply")) // apply any CA
				{
					corp.setCommit_butt(null);
					String butt = corp.getButton();
					Hashtable hash = corp.getHash_error();
					hash.clear();
					corp.setHash_error(hash);
					hash = corp.getHash_stock_error();
					hash.clear();
					corp.setHash_stock_error(hash);
					if (butt == null) // if there is no undo
					{
						// refresh data
						Hashtable hash_aff = corp.getHash_affind();
						hash_aff.clear();
						corp.setHash_affind(hash_aff);
						Hashtable data_error = corp.getHash_error();
						Hashtable stock_error = corp.getHash_stock_error();
						data_error.clear();
						stock_error.clear();
						corp.setHash_error(data_error);
						corp.setHash_stock_error(stock_error);

						String chk[] = request.getParameterValues("c_Cad");// get
																			// the
																			// list
																			// of
																			// records
																			// to
																			// be
																			// apply
						int len = chk.length;// get the length of selected
												// records
						String corp_name = null;
						ref_hash(corp);
						Hashtable hash1 = corp.getHash1();
						Hashtable hash5 = corp.getHash5();
						Hashtable hash2 = corp.getHash2();
						Hashtable hash6 = corp.getHash6();
						for (int i = 0; i < len; i++) // with the respective
														// type of action store
														// the data in hash
														// tables
						{
							String query = ConnectInit.queries
									.getProperty("select_rep_cad");
							String get_name = ConnectInit.queries
									.getProperty("get_corp_name");
							try {
								ResultSet rs = ListTypeClass1.getAffected(con,
										query, chk[i]);
								rs.next();
								String stock = rs.getString("stock_id");
								String corp_nm = rs.getString("cam_id");
								corp.setI_index(rs.getString("index_id"));
								corp.setApply_date(rs
										.getString("apply_on_date"));
								ResultSet rs1 = ListTypeClass1.getAffected(con,
										get_name, corp_nm);
								rs1.next();
								corp_name = rs1.getString("cam_shortname")
										.toLowerCase().trim();
								rs1.close();
								rs.close();
								if ((corp_name.equals("add stock"))
										| (corp_name.equals("change iwf"))) {
									hash1.put(new Integer(stock).toString(),
											new String(chk[i]));
									hash5.put(new Integer(stock).toString(),
											new String(chk[i]));
								}
								if (corp_name.equals("delete stock")) {
									hash2.put(new Integer(stock).toString(),
											new String(chk[i]));
									hash6.put(new Integer(stock).toString(),
											new String(chk[i]));
								}
								corp.setHash1(hash1);
								corp.setHash2(hash2);
								corp.setHash5(hash5);
								corp.setHash6(hash6);
							} catch (Exception e) {
								Logging.error("error==" + e.getMessage());
							}
						}
						if (!(corp_name.equals("rebasing")))// if there is no CA
															// rebasing
						{
							// forward to next page
							try {
								String query = ConnectInit.queries
										.getProperty("select_index_name");
								ResultSet rs = ListTypeClass1.getAffected(con,
										query, corp.getI_index());
								rs.next();
								corp.setI_name(rs.getString("index_name"));
							} catch (Exception e) {
								Logging.error("error=" + e.getMessage());
							}
							return new ActionForward(
									"/pages/IndexEventComposition.jsp");
						}
					} else // if there is undo
					{
						try {
							corp.setLeng(null);
							// get the selected record details
							corp.setC_Cad(request.getParameter("c_Cad"));
							String query = ConnectInit.queries
									.getProperty("select_rep_cad");
							ResultSet rs = ListTypeClass1.getAffected(con,
									query, corp.getC_Cad());
							rs.next();
							String stock = rs.getString("stock_id");
							corp.setStid(stock);
							corp.setApplied_date(rs.getString("applied_date"));
							corp.setTime(rs.getString("applied_time"));
							corp.setI_index(rs.getString("index_id"));
							String apply = rs.getString("apply_on_date");
							if (apply == null)
								apply = corp.getApplied_date();
							corp.setApply_date(apply);
							rs.close();
							corp.setResult2();
						} catch (Exception e) {
						}
						// forward the response to undoaction page to check any
						// CA apply on this stock or its repective indices
						return new ActionForward("/pages/UndoAction.jsp");
					}
				}
				if (button2.equals("New"))// to insert new CA
				{
					try {
						Hashtable hash = corp.getHash();
						hash.clear();
						corp.reset3();
						corp.setHash(hash);
						corp.setApplied_date(null);
						corp.setTime(null);
						corp.setI_index(null);
						corp.setCorpid(null);
						corp.setExc(null);
						corp.setExist(null);
					} catch (Exception e) {
						Logging.error("Error=" + e.getMessage());
					}
					return new ActionForward("/pages/NewIndexCorpAction.jsp");
				}
				if (button2.equals("NDelete")) // to cancel delete action
				{
					corp.setC_Cad(null);
					corp.setR_type("index event");
					return new ActionForward("/pages/CorporateDiary.jsp");
				}
				if (button2.equals("YDelete"))// to delete any record
				{
					String query = ConnectInit.queries
							.getProperty("delete_corp");
					String cad[] = request.getParameterValues("c_Cad"); // get
																		// the
																		// list
																		// of
																		// record
																		// to be
																		// deleted
					int len = cad.length;
					for (int i = 0; i < len; i++) // delete one by one record
					{
						corp.setEvent_id(cad[i]);
						UpdateCorp.delete_event(con, query, corp);
					}
					corp.setEvent_id(null);
					corp.setR_type("index event");
					return new ActionForward("/pages/CorporateDiary.jsp");
				}
				if (button2.equals("Update")) // to update any record
				{
					corp.setApplied_date(null);
					corp.setTime(null);
					Hashtable hash = corp.getHash();
					try {
						String[] app = request.getParameterValues("apply");
						String[] based = request.getParameterValues("basedate");
						String[] val = request.getParameterValues("value");
						String[] ids = request.getParameterValues("id");

						for (int i = 0; i < ids.length; i++) // update one by
																// one all the
																// record within
																// that date
						{
							// get the details of record to be updated
							String query = ConnectInit.queries
									.getProperty("select_rep_cad");
							ResultSet rs = ListTypeClass1.getAffected(con,
									query, ids[i]);
							rs.next();
							corp.setCad_id(ids[i]);
							corp
									.setAnnounce_date(rs
											.getString("announce_date"));
							corp.setEx_date(rs.getString("ex_date"));
							corp.setRecord_date(rs.getString("record_date"));
							corp.setApplied_date(rs.getString("applied_date"));
							corp.setCorpid(rs.getString("cam_id"));
							corp.setStid(rs.getString("stock_id"));
							corp.setI_index(rs.getString("index_id"));
							corp.setStatus(rs.getString("status"));
							if (app[i].equals(""))
								app[i] = null;
							corp.setApply_date(app[i]);
							if (based[i].equals(""))
								based[i] = null;
							corp.setBase_date(based[i]);
							if (val[i].equals(""))
								val[i] = null;
							corp.setValues(val[i]);
							query = null;
							// update record
							query = ConnectInit.queries
									.getProperty("update_index_cad");
							UpdateCorp.update_index_cad(con, query, corp);
						}
					} catch (Exception e) {
						Logging.error("Error=" + e.getMessage());
					}
					corp.setR_type("index event");
					return new ActionForward("/pages/CorporateDiary.jsp");
				}
				if (button2.equals("NUpdate")) // to cancel updation
				{
					corp.setCad_id(null);
					corp.setR_type("index event");
					return new ActionForward("/pages/CorporateDiary.jsp");
				}
			}

			String new_index = request.getParameter("new_index_but");// any
																		// action
																		// related
																		// to
																		// new
																		// CA in
																		// index
																		// event
			if (new_index != null) {
				if (new_index.equals("Index")) // on select of index
				{
					corp.setCorpid(null);
					return new ActionForward("/pages/NewIndexCorpAction.jsp");
				}
				if (new_index.equals("Action")) // on select of action
				{
					// get default exchange from system configuration
					try {
						corp.setStock(null);
						String qry = ConnectInit.queries
								.getProperty("select_system_config");
						ResultSet rs1 = ListTypeClass1
								.resultCorporate(con, qry);
						rs1.next();
						corp.setExc(rs1.getString("stock_ex_id"));
						rs1.close();
					} catch (Exception e) {
						Logging.error("Error=" + e.getMessage());
					}
					return new ActionForward("/pages/NewIndexCorpAction.jsp");
				}
				if (new_index.equals("Radio"))// if new record is already
												// present in diary, then for
												// further processing
				{
					String radval = corp.getInd_comp();
					corp.setExist(null);
					if (radval.equals("b"))// back to diary
					{
						corp.setR_type("index event");
						return new ActionForward(
								"/pages/CorporateDiary.jsp?r_type=index event");
					}
					if (radval.equals("c"))// continue inserting new record
					{
						String corp_nm = corp.getCorpid();
						String stock[] = request.getParameterValues("stock"); // get
																				// stock
																				// list
						String query = ConnectInit.queries
								.getProperty("insert_corp");
						String nextval = ConnectInit.queries
								.getProperty("get_sequence_cad_id");
						String corp_name = ConnectInit.queries
								.getProperty("get_corporate_list");
						if (stock != null) // if stock's are there
						{
							int len = stock.length;
							String iwf_val = request.getParameter("value");
							corp.setValues(iwf_val);
							corp.setStatus("n");
							for (int i = 0; i < len; i++)// for each stock
															// insert one record
							{
								String stk = stock[i];
								corp.setStid(stk);
								UpdateCorp.insert_corp(con, query, corp_name,
										nextval, corp);
							}
						} else // if there is no stock selection for any CA
						{
							String iwf_val = request.getParameter("value");
							corp.setValues(iwf_val);
							corp.setStatus("n");
							// insert new record
							UpdateCorp.insert_corp(con, query, corp_name,
									nextval, corp);
						}
						corp.setR_type("index event");
						return new ActionForward(
								"/pages/CorporateDiary.jsp?r_type=index event");
					}
				}
				if (new_index.equals("Back")) // back from new page to dairy
				{
					corp.setR_type("index event");
					return new ActionForward("/pages/CorporateDiary.jsp");
				}
				if (new_index.equals("Submit")) // to submit new record
				{
					String corp_nm = corp.getCorpid();
					String stock[] = request.getParameterValues("stock");// get
																			// stock
																			// list
					String qry = ConnectInit.queries
							.getProperty("get_corporate_list_index");
					int cid = 0;
					int flag = 0;
					try {
						// get CA id
						ResultSet rs = ListTypeClass1.resultCorporate(con, qry);
						cid = ListTypeClass1.check_corp_name1(rs, corp_nm);
						rs.close();

						Hashtable hash = corp.getHash();
						hash.clear();
						corp.setHash(hash);
						hash = corp.getHash();
						if (stock != null) // if stock's are there
						{
							int len = stock.length;
							for (int i = 0; i < len; i++) // for each stock
							{
								// check whether the new record is already
								// present in diary or not
								String stk = stock[i];
								hash.put(new String(stk), new String(stk));
								String date = request
										.getParameter("apply_date");
								if (corp_nm.equals("changeiwf")) {
									String query = ConnectInit.queries
											.getProperty("check_iwf_exist");
									rs = ListTypeClass1.check_dairy_exist(con,
											query, Integer.toString(cid), corp
													.getI_index(), stk, date);
									if (rs.next()) // if present
									{
										flag = 1;
									}
								}
								corp.setHash(hash);
							}
						}
						// for CA rebasing, check whether new record is already
						// present in diary or not
						if (corp_nm.equals("rebasing")) {
							String query = ConnectInit.queries
									.getProperty("check_base_exist");
							String apply = request.getParameter("apply_date");// UpdateCorp.accept_date();
							rs = ListTypeClass1.getresp_cad(con, query, Integer
									.toString(cid), corp.getI_index(), apply);
							if (rs.next())// if present
								flag = 1;
						}
					} catch (Exception e) {
						Logging.error("error=" + e.getMessage());
					}
					if (flag == 1)// if record is already present
					{
						// send an alert
						corp.setInd_comp(null);
						return new ActionForward(
								"/pages/NewIndexCorpAction.jsp?exist=1");
					} else // if not present
					{
						// insert new record
						String query = ConnectInit.queries
								.getProperty("insert_corp");
						String corp_name = ConnectInit.queries
								.getProperty("get_corporate_list");
						String nextval = ConnectInit.queries
								.getProperty("get_sequence_cad_id");
						if (stock != null) {
							int len = stock.length;
							String iwf_val = request.getParameter("value");
							corp.setValues(iwf_val);
							corp.setStatus("n");
							for (int i = 0; i < len; i++) {
								String stk = stock[i];
								corp.setStid(stk);
								UpdateCorp.insert_corp(con, query, corp_name,
										nextval, corp);
							}
						} else {
							String iwf_val = request.getParameter("value");
							corp.setValues(iwf_val);
							corp.setStatus("n");
							UpdateCorp.insert_corp(con, query, corp_name,
									nextval, corp);
						}
						corp.setR_type("index event");
						return new ActionForward(
								"/pages/CorporateDiary.jsp?r_type=index event");
					}
				}
			}

			String ebutton = request.getParameter("ap_co_button3");// any action
																	// related
																	// to event
			if (ebutton != null) {
				if (ebutton.equals("NDelete")) // to cancel delete action
				{
					corp.setC_Cad(null);
					corp.setR_type("event");
					return new ActionForward("/pages/CorporateDiary.jsp");
				}
				if (ebutton.equals("YDelete")) // to delete any record
				{
					String cad = request.getParameter("c_Cad");
					corp.setEvent_id(cad);
					// delete the record
					String query = ConnectInit.queries
							.getProperty("delete_event");
					UpdateCorp.delete_event(con, query, corp);
					corp.setEvent_id(null);
					corp.setR_type("event");
					return new ActionForward("/pages/CorporateDiary.jsp");
				}
				if (ebutton.equals("Update")) // to update any record
				{
					try {
						corp.reset3();
						corp.reset_event();
						// get the details of the selected record
						corp.setEvent_id(corp.getC_Cad());
						String query = ConnectInit.queries
								.getProperty("select_event");
						ResultSet rs = ListTypeClass1.getResult1(con, query,
								corp.getC_Cad());
						ListTypeClass1.event_list(corp, rs);
					} catch (Exception e) {
						Logging.error("error==" + e.getMessage());
					}
					return new ActionForward("/pages/EventCorpAction.jsp");
				}
			}

			String up_event = request.getParameter("event_button");// any action
																	// related
																	// to update
																	// event
			if (up_event != null) {
				if (up_event.equals("Amt"))// on click of amount
				{
					// with respect to amount calculate percent
					try {
						String amt = request.getParameter("amt");
						double close = 0.0, per = 0.0;
						String query = ConnectInit.queries
								.getProperty("select_stock_price_detail");
						ResultSet rs1 = ListTypeClass1.getResult12(con, query,
								corp.getStid());
						if (rs1.next()) {
							close = rs1.getDouble("adjusted_price");
							if (close == 0.0)
								close = rs1.getDouble("stock_closing_value");
							if (close == 0.0)
								per = 0.0;
							else
								per = (Double.parseDouble(amt) / close) * 100;
							corp.setPercent(Double.toString(per));
						}
						rs1.close();
					} catch (Exception e) {
						Logging.error("error==" + e.getMessage());
					}
				}
				if (up_event.equals("Radio")) // if this record is already
												// present in diary, then for
												// further processing
				{
					String radval = corp.getInd_comp();
					if (radval.equals("b")) // back to the diary
					{
						corp.setR_type("event");
						return new ActionForward(
								"/pages/CorporateDiary.jsp?r_type=event");
					} else // continue inserting new record
					{
						try {
							corp.setCorpid(request.getParameter("corpid"));
							String corp_val = corp.getCorpid();
							// get CA id
							String qry = ConnectInit.queries
									.getProperty("get_corporate_list_stock");
							ResultSet rs = ListTypeClass1.resultCorporate(con,
									qry);
							ListTypeClass1.check_corp_name(rs, corp_val, corp);
							rs.close();
							// insert into diary new record
							String query1 = ConnectInit.queries
									.getProperty("insert_into_cad_values");
							UpdateCorp.check_date(corp);
							String nextval = ConnectInit.queries
									.getProperty("get_sequence_cad_id");
							UpdateCorp.insert_into_cad(con, query1, nextval,
									corp, null);
							corp.setCorpid(corp_val);
							query1 = null;
							// delete this record from event table
							query1 = ConnectInit.queries
									.getProperty("delete_event");
							UpdateCorp.delete_event(con, query1, corp);
							corp.setR_type("event");
							return new ActionForward(
									"/pages/CorporateDiary.jsp?r_type=event");
						} catch (Exception e) {
							Logging.error("error==" + e.getMessage());
						}
					}
				}
				if (up_event.equals("Submit"))// to submit new record in diary
				{
					// get details of new record
					corp.setCorpid(request.getParameter("corpid"));
					corp.setAmt(request.getParameter("amt"));
					corp.setPercent(request.getParameter("percent"));
					corp.setRatio1(request.getParameter("ratio1"));
					corp.setRatio2(request.getParameter("ratio2"));
					corp.setValues(request.getParameter("share"));
					corp
							.setAnnounce_date(request
									.getParameter("announce_date"));
					corp.setApply_date(request.getParameter("apply_date"));
					corp.setApplied_date(null);
					corp.setRecord_date(request.getParameter("record_date"));
					corp.setEx_date(request.getParameter("ex_date"));
					corp.setBc_start(request.getParameter("bc_start"));
					corp.setNc_start(request.getParameter("nc_start"));
					corp.setBc_end(request.getParameter("bc_end"));
					corp.setNc_end(request.getParameter("nc_end"));
					corp.setStatus("n");
					corp.setDescription(request.getParameter("description"));
					String corp_val = corp.getCorpid();
					String qry = ConnectInit.queries
							.getProperty("get_corporate_list_stock");
					try {
						// get CA id
						ResultSet rs = ListTypeClass1.resultCorporate(con, qry);
						ListTypeClass1.check_corp_name(rs, corp_val, corp);
						rs.close();
						int flag = 0;
						// check whether this record exist in diary or not
						String query = ConnectInit.queries
								.getProperty("check_corp_existance");
						String apply = request.getParameter("apply_date");// UpdateCorp.accept_date();
						rs = ListTypeClass1.getresp_cad(con, query, corp
								.getCorpid(), corp.getStid(), apply);
						if (rs.next())
							flag = 1;
						if (flag == 1)// if present
						{
							// send an alert
							corp.setInd_comp(null);
							corp.setCorpid(corp_val);
							return new ActionForward(
									"/pages/EventCorpAction.jsp?exist=1");
						} else // if not present
						{
							// insert into diary this new record
							String query1 = ConnectInit.queries
									.getProperty("insert_into_cad_values");
							UpdateCorp.check_date(corp);
							String nextval = ConnectInit.queries
									.getProperty("get_sequence_cad_id");
							UpdateCorp.insert_into_cad(con, query1, nextval,
									corp, null);
							corp.setCorpid(corp_val);
							// delete this record from event table
							query1 = null;
							query1 = ConnectInit.queries
									.getProperty("delete_event");
							UpdateCorp.delete_event(con, query1, corp);
							corp.setR_type("event");
							return new ActionForward(
									"/pages/CorporateDiary.jsp");
						}
					} catch (Exception e) {
						Logging.error("error==" + e.getMessage());
					}

				}
			}
			// change by Manoj Adekar for Dynamic connection
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

		String undo_butt = request.getParameter("undo_button");// any action
																// related to
																// undo
		if (undo_butt != null) {
			// if any action applied on the stock, then to back to the diary
			if (undo_butt.equals("Back")) {
				corp.setR_type(corp.getR_type());
				String type = corp.getR_type();
				return new ActionForward("/pages/CorporateDiary.jsp?r_type="
						+ type);
			}
		}
		return mapping.getInputForward();

	}

	// this method is used to calculate percent with respect to amount or
	// vice-versa
	public static void check_diary(Corporate corp, String amount) {
		Connect connect = ConnectInit.getConnect();
		Connection con = null;
		// Change by Manoj Adekar for Dynamic connection using getdbConnection()
		// instead of getConnection()
		try {
			if (con == null) {
				con = connect.getdbConnection();
			}
			try {
				String query = ConnectInit.queries
						.getProperty("select_stock_price_detail");
				ResultSet rs = ListTypeClass1.getResult12(con, query, corp
						.getStid());
				double close = 0.0, per = 0.0;
				if (rs.next()) {
					close = rs.getDouble("adjusted_price");
					if (close == 0.0)
						close = rs.getDouble("stock_closing_value");
				} else
					close = 0.0;

				if (corp.getAmt_per() != null) {
					if (corp.getAmt_per().equals("a")) {
						corp.setAmt(amount.trim());
						if (close == 0.0)
							corp.setPercent(Double.toString(close));
						else {
							per = (Double.parseDouble(corp.getAmt()) / close) * 100;
							corp.setPercent(Double.toString(per));
						}
					}
					if (corp.getAmt_per().equals("p")) {
						corp.setPercent(amount.trim());
						if (close == 0.0)
							corp.setAmt(Double.toString(close));
						else
							corp.setAmt(Double
									.toString((Double.parseDouble(corp
											.getPercent()) * close) / 100));
					}
				} else if (corp.getAmt_per() == null) {
					corp.setAmt(null);
					corp.setPercent(null);
				}

				corp.setNew_corp_but(null);
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

	}

	// this method is used to refresh the hash contents
	public static void ref_hash(Corporate corp) {
		Hashtable hash1 = corp.getHash1();
		hash1.clear();
		corp.setHash1(hash1);
		hash1 = corp.getHash1();
		Hashtable hash5 = corp.getHash5();
		hash5.clear();
		corp.setHash5(hash5);
		hash5 = corp.getHash5();
		Hashtable hash2 = corp.getHash2();
		hash2.clear();
		corp.setHash2(hash2);
		hash2 = corp.getHash2();
		Hashtable hash6 = corp.getHash6();
		hash6.clear();
		corp.setHash6(hash6);
		hash6 = corp.getHash6();
		Hashtable hash4 = corp.getHash4();
		hash4.clear();
		corp.setHash4(hash4);
		Hashtable hash3 = corp.getHash3();
		hash3.clear();
		corp.setHash3(hash3);
		Hashtable hash = corp.getHash();
		hash.clear();
		corp.setHash(hash);
	}

}
