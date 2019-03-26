/*
 * Created on Mar 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.harrier.initializeation.ConnectInit;

/**
 * @author Vivek
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class BaseValuesAction extends Action {
	Logger Logging = Logger.getLogger(BaseValuesAction.class);
	ActionErrors errors = new ActionErrors();
	Logger logger;
	Connect c = ConnectInit.getConnect();
	String user_id = null;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		logger = Logger.getLogger(IndexCompositionAction.class);
		PropertyConfigurator.configure(Connect.resourceurl
				+ "resources/log4j.properties");
		if (request.getParameter("B4") != null
				&& request.getParameter("B4").trim().equals("<< Back")) {
			return (new ActionForward(
					response.encodeURL("/pages/IndexComposition.jsp")));
		}
		HttpSession session = request.getSession();
		NewIndexForm pageForm = (NewIndexForm) session
				.getAttribute("newIndexForm");
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(Connect.resourceurl
					+ "resources/database.properties"));
		} catch (Exception ex) {
			Logging.error(" Error : " + ex.getMessage());
		}
		String use_user = prop.getProperty("use_user");
		user_id = session.getAttribute("userid").toString();
		IndexCompositionForm compositionForm = (IndexCompositionForm) session
				.getAttribute("indexComposition");
		Logging.debug("request.getParameter(B3) : "
				+ request.getParameter("B3"));
		if (request.getParameter("B3") != null
				&& request.getParameter("B3").trim()
						.equals("Save and Continue")) {
			createIndex(pageForm, compositionForm, request, use_user);
			if (!errors.isEmpty()) {
				return (new ActionForward(mapping.getInput()));
			}
			return new ActionForward("/pages/IndexHome.jsp?D1="
					+ pageForm.getI_indexID() + "&B2=Compute&basedate="
					+ pageForm.getD_baseDate());
		}
		return (new ActionForward(mapping.getInput()));
	}

	public void createIndex(NewIndexForm pageForm,
			IndexCompositionForm compositionForm, HttpServletRequest request,
			String use_user) {
		Properties prop = new Properties();
		String queryforspecialindex = null;
		CallableStatement cs = null;
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
		logger.info("flag for duplicate Index : " + flag);
		if (!flag) {
			errors.add("s_indexName", new ActionError(
					"Error.message.SameIndexName"));
			saveErrors(request, errors);
			return;
		}

		// if index not a captured one and is either of type currency or total
		// returns
		/*
		 * String queryforspecialindex = c.queries
		 * .getProperty("insert_into_index_master");
		 */
		// c.getConnectionForTransaction();

		try {
			try {
				pageForm.getDatabaseConnection().rollback();
			} catch (Exception e) {
				// TODO: handle exception
			}
			pageForm.getDatabaseConnection().setAutoCommit(false);

		} catch (Exception e) {
			logger.debug("database commited ");
			// TODO: handle exception
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
					// TODO: handle exception
				}
				pageForm.getDatabaseConnection().setAutoCommit(true);

			} catch (Exception exception) {
				logger.debug("database commited ");
				// TODO: handle exception
			}
			System.out
					.println("StockDetailsCollection: Error inserting in table "
							+ e.getMessage());

			// TODO: handle exception
		}
		storeComposition(pageForm.getI_indexID(),
				pageForm.getDatabaseConnection(),
				ConnectInit.queries
						.getProperty("insert_into_index_composition"),
				compositionForm, request, pageForm);

		try {
			if (pageForm.getS_applyClassification().equals("on")) {

				cs = pageForm
						.getDatabaseConnection()
						.prepareCall(
								"{ call public.define_and_compose_single_child_index(?, ?, ?) }");
				Hashtable indexesnotCreated = new Hashtable();

				try {
					String query1 = ConnectInit.queries
							.getProperty("auto_generate_child_index");
					PreparedStatement preparedStatement = pageForm
							.getDatabaseConnection().prepareStatement(query1);
					ResultSet rst = preparedStatement.executeQuery();
					String childindexname = "";
					int i = 1;
					// String[] ee=request.getParameterValues("indexname");
					String iname = pageForm.getS_indexName();
					String basevalue = pageForm.getD_baseValue();
					String classid = null;
					String indexid = pageForm.getI_indexID();
					while (rst.next()) {
						childindexname = iname + "."
								+ rst.getString("level_name") + "."
								+ rst.getString("class_name");
						if (request
								.getParameter(iname
										+ rst.getString("industry_classification_code")) != null
								&& !request
										.getParameter(
												iname
														+ rst.getString("industry_classification_code"))
										.trim().equals("")) {
							// Logging.debug("In if");
							classid = rst.getString("class_id");
							try {
								double value = new Double(
										request.getParameter(iname
												+ rst.getString("industry_classification_code")))
										.doubleValue();
								Logging.debug("for index " + childindexname
										+ " values are indexid=" + indexid
										+ "  classid :" + classid + "  value :"
										+ value);
								cs.setInt(1, new Integer(indexid).intValue());
								cs.setInt(2, new Integer(classid).intValue());
								cs.setFloat(3, new Float(value).floatValue());
								boolean f = cs.execute();
							} catch (Exception e) {
								// Logging.debug("In catch");
								// TODO: handle exception
							}

						}
						i++;
					}
				} catch (Exception e) {
					// e.printStackTrace();
					Logging.debug(e);
					// TODO: handle exception
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {
				if (cs != null) {
					cs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void storeComposition(String indexID, Connection con, String query,
			IndexCompositionForm compositionForm, HttpServletRequest request,
			NewIndexForm form) {
		PreparedStatement psmt = null;
		Statement st = null;
		ResultSet rs = null;
		Hashtable destinationTable = compositionForm.getDestinationTable();
		if (!destinationTable.isEmpty()) {
			Logging.debug("Inside isEmpty  " + destinationTable.isEmpty()
					+ " and  query is " + query);
			try {

				psmt = con.prepareStatement(query);
				st = con.createStatement();

				int id1 = 0;

				for (Enumeration e = destinationTable.keys(); e
						.hasMoreElements();) {
					rs = st.executeQuery("select nextval('index_stock_id')");

					if (rs.next())
						id1 = rs.getInt(1);
					Logging.debug("id : " + id1);
					Logging.debug("indexID : " + indexID);
					String id = e.nextElement().toString();
					StockDetails sd = (StockDetails) destinationTable.get(id);

					psmt.setInt(1, id1);
					if (compositionForm.indexTypebackup.equals("1")) {
						psmt.setDouble(2, 1.0);
					} else if (form.getS_indexType().equals("2")) {
						String string = request.getParameter("iwf"
								+ sd.getStockID());
						psmt.setString(2, string);
					} else {
						psmt.setDouble(2, sd.getAdjustedIWF());
					}
					psmt.setString(3, QueryClass.formatDate());
					psmt.setInt(4, Integer.parseInt(indexID));
					psmt.setInt(5, sd.getStockID());
					psmt.setDouble(
							6,
							sd.getMktCapital1(2,
									compositionForm.getParentCurrencyId(),
									sd.getCurrencyId()));

					psmt.executeUpdate();
					rs = null;
				}
				Logging.debug("Composition stored successfully!!! ");
			} catch (Exception e) {
				Logging.debug(e);
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
						"indexcompose.storecomposition"));
				saveErrors(request, errors);
				// return to input page
				try {
					try {
						form.getDatabaseConnection().rollback();
					} catch (Exception ee) {
						// TODO: handle exception
					}
					form.getDatabaseConnection().setAutoCommit(true);

				} catch (Exception exception) {
					logger.debug("database commited ");
					// TODO: handle exception
				}
				Logging.debug("StockDetailsCollection: Error inserting in table "
						+ e.getMessage());

			} finally {
				try {
					if (psmt != null) {
						psmt.close();
					}
					if (rs != null) {
						rs.close();
					}
					if (st != null) {
						st.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

	}

}
