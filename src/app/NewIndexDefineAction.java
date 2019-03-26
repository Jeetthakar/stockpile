/*
 * Created on Mar 10, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.harrier.initializeation.ConnectInit;

/**
 * @author neha
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public final class NewIndexDefineAction extends Action {
	Logger Logging = Logger.getLogger(NewIndexDefineAction.class);
	Connect c = ConnectInit.getConnect();

	ActionErrors errors;

	String user_id = null;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		errors = new ActionErrors();
		NewIndexForm pageForm = (NewIndexForm) form;
		String cmpname=pageForm.getName_list();
		
		HttpSession session = request.getSession();
		Properties prop = new Properties();
		Properties prop1 = new Properties();
		try {
			java.net.URL imgURL = ComputeIndexForm.class
					.getResource("ComputeIndexForm.class");
			Logging.debug("imgURL : " + imgURL);
			String resourcepth = imgURL.toString();
			Logging.debug("resourceurl : " + resourcepth);
			resourcepth = resourcepth.substring(6, (resourcepth
					.lastIndexOf("/WEB-INF/") + 8));
			Logging.debug("resourceurl : " + resourcepth);
			String resourcepth1 = resourcepth
					+ "/classes/resources/user_query.properties";
			prop1.load(new FileInputStream(resourcepth1));
			resourcepth = resourcepth
					+ "/classes/resources/database.properties";
			prop.load(new FileInputStream(resourcepth));
		} catch (Exception ex) {
			Logging.error(" Error : " + ex.getMessage());
		}
		String use_user = prop.getProperty("use_user");
		user_id = session.getAttribute("userid").toString();
		
		Logging.debug("inside new NewIndexDefineAction :"+user_id);
//		session.setAttribute("user_id",user_id);
		Logging.debug("user_id set into session :"+user_id);
		//session.setAttribute("form",pageForm);
		session.setAttribute("indexTypesession", pageForm.getS_indexType());
		Logging.info("before forwardparam");
		ActionForwardParameters par = new ActionForwardParameters();
		Logging.info("after forwardparam");
		Hashtable table = new Hashtable();
		Logging.debug("getCopyparent_before : : == "
				+ pageForm.getCopyparent_before());
		//handling copy of another index
		//filss the fields with the values of that index
		if (pageForm.getCopyparent_before().trim().equals("copy")) {
			try {
				Connection con = c.getdbConnection();
				PreparedStatement psmt = con.prepareStatement(ConnectInit.queries
						.getProperty("query_copy_indexmaster"));
				psmt.setString(1, pageForm.getS_parentIndex());
				ResultSet indexdetails = psmt.executeQuery();
				if (indexdetails.next()) {
					Logging.info("parent index is : "
							+ indexdetails.getString("index_name")
							+ "with id as : "
							+ indexdetails.getString("index_id"));
					pageForm.setS_indexType(indexdetails
							.getString("index_type_id"));
					pageForm.setS_industryClassificationID(indexdetails
							.getString("industry_classification_code"));
					pageForm.setD_creationDate(indexdetails
							.getString("creation_date"));
					pageForm.setD_baseDate(indexdetails.getString("base_date"));
					pageForm.setS_applyClassification(indexdetails
							.getString("industry_classification_id"));
					pageForm.setS_alertPercent(indexdetails
							.getString("alert_percentage"));
					pageForm.setS_rejectionPercent(indexdetails
							.getString("rejection_percentage"));
					try {
						pageForm.setS_reutersCode(indexdetails
								.getString("o_ric"));
					} catch (Exception e) {
						pageForm.setS_reutersCode(" ");
					}
					pageForm.setS_baseCurrency(indexdetails
							.getString("base_currency_id"));
					pageForm.setD_baseValue(indexdetails
							.getString("base_value"));
					try {
						pageForm.setS_ISIN(indexdetails.getString("isin"));
					} catch (Exception e) {
						pageForm.setS_ISIN(null);
					}
					pageForm.setD_startTime(indexdetails
							.getString("m_start_time"));
					pageForm.setD_stopTime(indexdetails
							.getString("n_stop_time"));
					try {
						pageForm.setB_computeSettlementValue(indexdetails
								.getString("calculate_settlement_value"));
					} catch (Exception e) {
						pageForm.setB_computeSettlementValue("n");
					}
					try {
						if (indexdetails.getString("is_active").trim().equals(
								"y")) {
							pageForm.setB_isActive(null);
						} else {
							pageForm.setB_isActive("y");
						}
					} catch (Exception e) {
						pageForm.setB_isActive(null);
					}
					pageForm.setI_timeInterval(indexdetails
							.getString("computation_interval"));
					try {
					} catch (Exception e) {
						pageForm.setB_isCaptured(null);
					}
					try {
						pageForm.setS_capturedFrom(indexdetails
								.getString("captured_from"));
						Logging
								.debug("In IncomeLibrary.java after captured from try with : "
										+ indexdetails
												.getString("captured_from"));
					} catch (Exception e) {
						Logging
								.error("In IncomeLibrary.java In captured from Else "
										+ e.getMessage());
						pageForm.setS_capturedFrom("0");
					}
					try {
						pageForm.setS_type(indexdetails
								.getString("growth_or_value"));
					} catch (Exception e) {
						pageForm.setS_type(null);
					}
					
				}
			} catch (Exception e) {
				//e.printStackTrace();
				Logging.error("Error : " + e.getMessage());
			}
			
		} else {
			pageForm.setD_creationDate(QueryClass.formatDate());
		}
		if (pageForm.getSubmitvalue().equals("fromParent")) {
			return new ActionForward("/pages/NewIndexDefine.jsp");
		}
		if (pageForm.getB_isActive() == null) {
			pageForm.setB_isActive("y");
		} else {
			pageForm.setB_isActive("n");
		}
		if (pageForm.getSubmitvalue().equals("Submit")) {
			pageForm.setSubmitresult("true");
			request.setAttribute("submitresult1", "false");
			//copy of another index with submit button pressed
			if (pageForm.getB_isIndexIsChildOrCustomized() != null
					&& pageForm.getB_isIndexIsChildOrCustomized().equals("3")) {
				boolean b = copyOfAnotherIndex(pageForm, mapping, request,
						use_user);
				Logging.info("b >> " + b);
				if (b) {
					request.setAttribute("submitresult1", "false");
					//implement child index
					new ActionForward(response
							.encodeURL("/pages/IndexHome.jsp?D1="
									+ pageForm.getI_indexID()
									+ "&B2=Compute&basedate="
									+ pageForm.getD_baseDate()));
				} else {
					Logging.info("returning to same page "
							+ pageForm.getSubmitresult());
					//   pageForm.setSubmitresult("false");
					request.setAttribute("submitresult1", "false");
				}
				return (mapping.getInputForward());
			}
			//captured index
			Logging.info("returning to same page "
					+ pageForm.getB_isCaptured());
			if (pageForm.getB_isCaptured() != null
					&& pageForm.getB_isCaptured().equals("on")) {
				boolean b = capturedIndex(pageForm, mapping, request, use_user);
				if (b) {
					pageForm.setSubmitresult("true");
					request.setAttribute("submitresult1", "true");
				} else {
					pageForm.setSubmitresult("false");
					request.setAttribute("submitresult1", "false");
				}
				return (mapping.getInputForward());
			}
		} else if (pageForm.getSubmitvalue().equals("Next >>")) {
			request.setAttribute("submitresult1", "false");
			//		  copy of another index with Compose button pressed
			if (pageForm.getB_isIndexIsChildOrCustomized() != null
					&& pageForm.getB_isIndexIsChildOrCustomized().equals("3")) {
				boolean b = copyOfAnotherIndex(pageForm, mapping, request,
						use_user);
				if (b) {
					request.setAttribute("submitresult1", "false");
					//implement child index
					return (new ActionForward(response
							.encodeURL("/pages/IndexHome.jsp?D1="
									+ pageForm.getI_indexID()
									+ "&B2=Compute&basedate="
									+ pageForm.getD_baseDate())));
				} else {
					pageForm.setSubmitresult("false");
					request.setAttribute("submitresult1", "false");
					return (mapping.getInputForward());
				}
			}
			//captured index
			if (!pageForm.getB_isCaptured().equals("n")) {
				boolean b = capturedIndex(pageForm, mapping, request, use_user);
				if (b) {
					pageForm.setSubmitresult("true");
					request.setAttribute("submitresult1", "true");
				} else {
					pageForm.setSubmitresult("false");
					request.setAttribute("submitresult1", "false");
				}
				return (mapping.getInputForward());
			}
		}
		//create normal indexes here
		//createIndex(pageForm,request);
		Logging.debug("Starting new index creation + " + errors.size());
		//   if (!errors.empty()) {
		//      return (new ActionForward(mapping.getInput()));
		//   }
		if (((pageForm.getB_isCaptured()).equals("n"))
				&& (pageForm.getS_indexType().equals("4") || pageForm
						.getS_indexType().equals("5"))) {
			if (pageForm.getS_indexType().equals("5")) {
				createIndex(pageForm, request, use_user);
				if (errors.isEmpty()) {
					return (new ActionForward(response
							.encodeURL("/pages/IndexHome.jsp?D1="
									+ pageForm.getI_indexID()
									+ "&B2=Compute&basedate="
									+ pageForm.getD_baseDate())));
				} else {
					return (mapping.getInputForward());
				}
			} else if (pageForm.getS_indexType().equals("4")) {
				
				int temp = new QueryClass().getTotalReturnError(pageForm
						.getS_parentIndex(), pageForm.getD_baseDate(), pageForm
						.getDatabaseConnection());
				
				switch (temp) {
				case 0:

					break;
				case 1:
					//	errors="This index is not either not difined or not
					// composed";
					errors.add(null, new ActionError(
							"error.compute.indexnotdefined"));
					break;
				case 2:
					errors.add(null, new ActionError(
							"error.totalreturn.todaysdivisor"));
					break;
				case 3:
					errors.add(null, new ActionError(
							"error.totalreturn.todaysdivisor"));
					break;
				case 4:
					errors.add(null, new ActionError(
							"error.totalreturn.previousdivisor"));
					break;
				case 5:
					errors.add(null, new ActionError(
							"error.totalreturns.cashdividend"));
					break;
				case 6:
					errors.add(null, new ActionError("totalreturns.nodata"));
					break;
				default:
					break;
				}
				
				if (errors.isEmpty()) {
					
					createIndex(pageForm, request, use_user);
					
					return (new ActionForward(response
							.encodeURL("/pages/IndexHome.jsp?D1="
									+ pageForm.getI_indexID()
									+ "&B2=Compute&basedate="
									+ pageForm.getD_baseDate())));
				} else {
					saveErrors(request, errors);
					return (mapping.getInputForward());
				}
			}
		} else {
			return (new ActionForward(response
					.encodeURL("/pages/IndexComposition.jsp?from=create")));
		}
		return (mapping.getInputForward());
	}

	public boolean capturedIndex(NewIndexForm pageForm, ActionMapping mapping,
			HttpServletRequest request, String use_user) {
		Properties prop = new Properties();
		if (!pageForm.getB_isCaptured().equals("n")) {
			//	  index is created here
			try {
				String query = null;
				if (use_user != null && use_user.equals("yes")) {
					try {
						prop.load(new FileInputStream(Connect.resourceurl
								+ "resources/user_query.properties"));
						query = prop.getProperty("check_index_name");
					} catch (Exception ex) {
						Logging.error("Error : " + ex.getMessage());
					}
				} else {
					query = ConnectInit.queries.getProperty("insert_into_index_master");
				}
				c.getConnectionForTransaction().commit();
				//    Connect.conForTransaction.setAutoCommit(false);
				QueryClass.insertIntoIndexMaster(query, pageForm, use_user,
						user_id);
				//session.setAttribute("form",form);
				// request.setAttribute("copyparam",new String("yes"));
				pageForm.getDatabaseConnection().setAutoCommit(true);
				return true;
			} catch (Exception e) {
				Logging.error(" Error : " + e.getMessage());
				return false;
			}
		}
		return false;
	}

	public boolean copyOfAnotherIndex(NewIndexForm pageForm,
			ActionMapping mapping, HttpServletRequest request, String use_user) {
		//      copy of another index
		String query = null;
		Properties prop = new Properties();
		if (use_user != null && use_user.equals("yes")) {
			try {
				prop.load(new FileInputStream(Connect.resourceurl
						+ "resources/user_query.properties"));
				query = prop.getProperty("copy_of_another_index");
			} catch (Exception ex) {
				Logging.error("Error : " + ex.getMessage());
			}
		} else {
			query = ConnectInit.queries.getProperty("copy_of_another_index");
		}
		if (pageForm.getB_isIndexIsChildOrCustomized() != null
				&& pageForm.getB_isIndexIsChildOrCustomized().equals("3")) {
			ActionForward fr = null;
			// String query = c.queries.getProperty("query_copyofanotherindex");
			//  query = new
			// Connect().queries.getProperty("copy_of_another_index");
			Logging.info("data for copy of another index");
			Logging.info("query  : " + query);
			Logging.info("pageForm.getS_parentIndex()  : "
					+ pageForm.getS_parentIndex());
			Logging.info("pageForm.getS_indexName()  : "
					+ pageForm.getS_indexName());
			QueryClass.storeCopyOfParent(query, pageForm.getS_parentIndex(),
					pageForm.getS_indexName(), pageForm, use_user, user_id);
			try {
				query = ConnectInit.queries.getProperty("get_composition_of_parent");
				Logging.info("Button values is "
						+ pageForm.getSubmitvalue());
				Logging.info("Button values is "
						+ pageForm.getSubmitvalue());
				if (pageForm.getSubmitvalue().equals("Submit")) {
					QueryClass.insertCompositionOfParent(query, pageForm
							.getI_indexID(), pageForm.getS_parentIndex(),
							pageForm.getDatabaseConnection());
					//  request.setAttribute("copyparam",new String("yes"));
					//    Connect.conForTransaction.setAutoCommit(true);
					//    Connect.conForTransaction.commit();
					return true;
				} else {
					if (pageForm.getSubmitvalue().equals("Next >>")) {
						query = ConnectInit.queries
								.getProperty("get_composition_of_parent");
						//HttpSession session=request.getSession();
						//session.setAttribute("form",pageForm);
						QueryClass.insertCompositionOfParent(query, pageForm
								.getI_indexID(), pageForm.getS_parentIndex(),
								pageForm.getDatabaseConnection());
						//  request.setAttribute("copyparam",new String("yes"));
						// String pid=pageForm.getI_indexID();
						//  return (new ActionForward(mapping.getInput()));
						return true;
					}
				}
			} catch (Exception e) {
				//e.printStackTrace();
				Logging.error(" Error : " + e.getMessage());
				return false;
			}
		}
		return false;
	}

	public void createIndex(NewIndexForm pageForm, HttpServletRequest request,
			String use_user) {
		Properties prop = new Properties();
		String queryforspecialindex = null;
		String query = null;
		if (use_user != null && use_user.equals("yes")) {
			try {
				prop.load(new FileInputStream(Connect.resourceurl
						+ "resources/user_query.properties"));
				query = prop.getProperty("check_index_name");
				queryforspecialindex = prop
						.getProperty("insert_into_index_master");
			} catch (Exception ex) {
				Logging.error("Error : " + ex.getMessage());
			}
		} else {
			query = ConnectInit.queries.getProperty("check_index_name");
			queryforspecialindex = ConnectInit.queries
					.getProperty("insert_into_index_master");
		}
		boolean flag = QueryClass1.chkIndexkname(pageForm.getS_indexName(),
				use_user, user_id, query);
		Logging.info("flag for duplicate Index : " + flag);
		if (!flag) {
			errors.add("s_indexName", new ActionError(
					"Error.message.SameIndexName"));
			saveErrors(request, errors);
			return;
		}
		//if index not a captured one and is either of type currency or total
		// returns

		try {
			c.getConnectionForTransaction().commit();
			pageForm.getDatabaseConnection().setAutoCommit(false);
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		}
		try {
			QueryClass.insertIntoIndexMaster(queryforspecialindex, pageForm,
					use_user, user_id);
		} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"indexcompose.createindex"));
			saveErrors(request, errors);
			// return to input page
			try {
				try {
					pageForm.getDatabaseConnection().rollback();
				} catch (Exception ee) {
					Logging.error(" Error : " + e.getMessage());
				}
				pageForm.getDatabaseConnection().setAutoCommit(true);
			} catch (Exception exception) {
				Logging.error(" Error : " + e.getMessage());
			}
			Logging
					.error("StockDetailsCollection: Error inserting in table "
							+ e.getMessage());
		}
		//QueryClass.insertIntoIndexMaster(queryforspecialindex, pageForm);
	}
}