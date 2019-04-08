/*
 * Created on Apr 1, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.masters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.validator.ValidatorForm;

import sysconfig.model.Companyclass;
import app.AcessControl;
import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * @author naresh
 *
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class CompanyForm extends ValidatorForm {
	Logger Logging = Logger.getLogger(CompanyForm.class);
	private String selectCompany = null;
	private Collection companyCollection = null;
	private String selectICName = null;
	private Collection icCollection = null;
	private int[] selectedCheckbox = null;
	Connection connection = null;
	private String operation = null;
	Vector list = new Vector();
	ArrayList tableData = new ArrayList();
	ResultSet rst;
	// app.Connect con=new app.Connect();
	Connect con = ConnectInit.getConnect();
	Companyclass cc;

	/** RESEST ALL FORM FIELDS **/
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		selectICName = null;
		icCollection = null;
	}

	/**
	 * VALIDATE FORM DATA
	 ***/
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		int i = 0;
		if (selectCompany != null) {
			try {
				if (connection == null)
					connection = con.getdbConnection();
				if (operation == null || !(operation.equals("Save"))) {
					try {
						PreparedStatement stmt = connection
								.prepareStatement(ConnectInit.queries
										.getProperty("classCompany_select_class_id"));
						String str = selectCompany.split("e")[1];
						int id = Integer.parseInt(str);
						stmt.setInt(1, id);
						rst = stmt.executeQuery();
						while (rst.next()) {
							list.add(rst.getInt(1) + "");
						}
						rst.close();
						stmt.close();
						int len = list.size();
						int[] arr = new int[len];
						for (int j = 0; j < len; j++) {
							arr[i] = Integer.parseInt((String) list.get(j));
							i++;
						}
						selectedCheckbox = arr;
					} catch (Exception e) {
						Logging.error("Error  :" + e.getMessage());
					}
				} else {
					System.out.println("Else****");
				}
			} finally {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ee) {
					Logging.error(" Error : Unable to close Connection "
							+ ee.getMessage());
				}
			}
		}
		return errors;
	}

	/**
	 * @return Returns the selectCompany.
	 */
	public String getSelectCompany() {
		return selectCompany;
	}

	/**
	 * @param selectCompany
	 *            The selectCompany to set.
	 */
	public void setSelectCompany(String selectCompany) {
		this.selectCompany = selectCompany;
	}

	/**
	 * @return Returns the selectICName.
	 */
	public String getSelectICName() {
		return selectICName;
	}

	/**
	 * @return Returns the companyCollection.
	 */
	public Collection getCompanyCollection() {
		Vector activity = new Vector();
		// AcessControl asc=new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		String NotSelected = asc.getLangValues("Masters.NotSelected");
		activity.add(new LabelValueBean(NotSelected, "value0"));
		companyCollection = null;
		Connect c = ConnectInit.getConnect();
		try {
			try {
				if (connection == null || connection.isClosed())
					connection = c.getdbConnection();
				try {
					PreparedStatement stmt = connection
							.prepareStatement(ConnectInit.queries
									.getProperty("classCompany_select_*_from_company"));
					rst = stmt.executeQuery();
					while (rst.next()) {
						int count = rst.getInt(1);
						activity.add(new LabelValueBean(rst.getString(2),
								"value" + count));
					}
					rst.close();
					stmt.close();
				} catch (Exception e) {
					Logging.error("Error  :" + e.getMessage());
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			companyCollection = activity;
			return companyCollection;
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}

	}

	/**
	 * @param companyCollection
	 *            The companyCollection to set.
	 */
	public void setCompanyCollection(Collection companyCollection) {
		this.companyCollection = companyCollection;
	}

	/**
	 * @param selectICName
	 *            The selectICName to set.
	 */
	public void setSelectICName(String selectICName) {
		this.selectICName = selectICName;
	}

	/**
	 * @return Returns the icCollection.
	 */
	public Collection getIcCollection() {
		Vector activity = new Vector(10);
		// AcessControl asc=new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		String NotSelected = asc.getLangValues("Masters.NotSelected");
		activity.add(new LabelValueBean(NotSelected, "value0"));
		Connect c = ConnectInit.getConnect();
		icCollection = null;

		try {
			try {
				if (connection == null || connection.isClosed())
					connection = c.getdbConnection();

				try {
					PreparedStatement stmt = connection
							.prepareStatement(ConnectInit.queries
									.getProperty("icm_select_*_from_industry_classification_master"));
					rst = stmt.executeQuery();
					while (rst.next()) {
						int count = rst.getInt(1);
						activity.add(new LabelValueBean(rst.getString(2),
								"value" + count));
					}
					rst.close();
					stmt.close();
				} catch (SQLException e) {
					Logging.error("Error  :" + e.getMessage());
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			icCollection = activity;
			return icCollection;
		}

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

	/**
	 * @param icCollection
	 *            The icCollection to set.
	 */
	public void setIcCollection(Collection icCollection) {
		this.icCollection = icCollection;
	}

	/**
	 * @return Returns the selectedCheckbox.
	 */
	public int[] getSelectedCheckbox() {
		return selectedCheckbox;

	}

	/**
	 * @param selectedCheckbox
	 *            The selectedCheckbox to set.
	 */
	public void setSelectedCheckbox(int[] selectedCheckbox) {
		this.selectedCheckbox = selectedCheckbox;

	}

	/**
	 * @return Returns the tableData.
	 */
	public ArrayList getTableData() {
		ArrayList Pp = new ArrayList();
		int icm = 1;
		Connect c = ConnectInit.getConnect();
		if (selectICName != null) {
			icm = Integer.parseInt(selectICName.split("e")[1]);
		}
		try {

			try {
				if (connection == null || connection.isClosed())
					connection = c.getdbConnection();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				PreparedStatement stmt = connection
						.prepareStatement(ConnectInit.queries
								.getProperty("classCompany_industry_classification_master_list"));
				stmt.setInt(1, icm);
				rst = stmt.executeQuery();
				while (rst.next()) {
					cc = new Companyclass(rst.getInt(1), rst.getString(2));
					Pp.add(cc);
				}
				rst.close();
				stmt.close();
			} catch (SQLException e) {
				Logging.error("Error  :" + e.getMessage());
			}

		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		tableData = Pp;
		return tableData;
	}

	/**
	 * @param tableData
	 *            The tableData to set.
	 */
	public void setTableData(ArrayList tableData) {
		this.tableData = tableData;
	}

	/**
	 * @return Returns the operation.
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * @param operation
	 *            The operation to set.
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

}
