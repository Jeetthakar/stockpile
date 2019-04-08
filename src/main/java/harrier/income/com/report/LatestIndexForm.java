/**
 * @author Ashish
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import app.*;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.jfree.chart.demo.servlet.AdjustDecimal;

import com.harrier.initializeation.ConnectInit;

public class LatestIndexForm extends ActionForm {
	Logger Logging = Logger.getLogger(LatestIndexForm.class);
	ArrayList latestdivisor = null;
	private Vector vi, vector_latestdivisor;
	Latest latest;
	String userid1;
	java.sql.ResultSet rst;
//	AdjustDecimal ed = new AdjustDecimal();
	IndexComposeReportMethod Icr = new IndexComposeReportMethod();

	// Connect con=new app.Connect();

	public ArrayList getLatestdivisor() {
		Connection connection = null;
		String Indexname = null, Divisor = null, ID = null;
		Logging.debug("Inside latestdivisor");
		// app.Connect con=new app.Connect();
		Connect con = ConnectInit.getConnect();
		ArrayList arr = new ArrayList();
		latestdivisor = new ArrayList();
		try {
			AdjustDecimal ed  = ConnectInit.getAdjustDecimal();
			if (connection == null)
				connection = con.getdbConnection();

			rst = Icr.divisorResult(connection, "get_all_latest_divisor",
					userid1);

			int i = 0;
			Logging
					.debug("setVector_latestdivisor of list of latest divisor");

			try {
				while (rst.next()) {
					if (rst.getString(2) == null) {
						Indexname = "0";

					} else {
						Indexname = rst.getString(2);

					}

					if (rst.getString(3) == null) {
						Divisor = "0";
					} else {
						Divisor = ed.indexcompose(rst.getString(3));
						Divisor = AdjustDecimal.ArrangeAsNumeric(Divisor);

					}

					ID = rst.getString(1);
					latest = new Latest(Indexname, Divisor, ID);
					arr.add(latest);
				}
				rst.close();
			} catch (SQLException sqlexp) {
				Logging.error("Error : " + sqlexp.getMessage());
			}
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error(" Error : Unable to close connection "
							+ ex.getMessage());
				}
				Logging.error(" Error : Unable to close connection "
						+ ee.getMessage());
			}
		}
		latestdivisor = arr;
		Logging.debug("arraylist" + latestdivisor);
		return latestdivisor;
	}

	public void setLatestdivisor(ArrayList latestdivisor) {
		this.latestdivisor = latestdivisor;
	}

	/**
	 * @return Returns the vector_latestdivisor.
	 */

	public Vector getVector_latestdivisor() {
		Logging.debug("Inside latestdivisor");
		// app.Connect con=new app.Connect();
		Connect con = ConnectInit.getConnect();
		Connection connects = null;
		// Connect c = ConnectInit.getConnect();
		if (Connect.con == null) {
			connects = con.getdbConnection();
		}
		vector_latestdivisor = new Vector();
		rst = con.divisorResult("get_all_latest_divisor", userid1);
		/*
		 * app.Connect c = ConnectInit.getConnect(); java.sql.Connection con1
		 * =c.getConnection(); Statement st = con1.createStatement(); String
		 * query=c.queries.getProperty("get_all_latest_divior");
		 * rst=st.executeQuery(query);
		 */
		int i = 0;
		Logging
				.debug("setVector_latestdivisor of list of latest divisor");
		try {
			while (rst.next()) {

				if (rst.getString(1) == null) {
					vector_latestdivisor.add(i, "--");
				} else {
					vector_latestdivisor.add(i, rst.getString(1));
				}
				i++;

				if (rst.getString(2) == null) {
					vector_latestdivisor.add(i, "--");
				} else {
					vector_latestdivisor.add(i, rst.getString(2));
				}
				i++;

				if (rst.getString(3) == null) {
					vector_latestdivisor.add(i, "--");
				} else {
					vector_latestdivisor.add(i, rst.getString(3));
				}
				i++;
			}
		} catch (SQLException sqlexp) {
			Logging.error("Error : " + sqlexp.getMessage());
		} finally {
			try {
				if (connects != null)
					connects.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		return vector_latestdivisor;
	}

	/**
	 * @param vector_latestdivisor
	 *            The vector_latestdivisor to set.
	 */
	public void setVector_latestdivisor(Vector vector_latestdivisor) {
		this.vector_latestdivisor = vector_latestdivisor;

	}

	/*
	 * public void dbconnect(){
	 * 
	 * try {if(app.Connect.con==null){ con.getConnection(); } } catch (Exception
	 * e) { Logging.debug(e);}
	 * 
	 * }
	 */

	public String getUserid1() {
		return userid1;
	}

	public void setUserid1(String userid1) {
		this.userid1 = userid1;
	}
}
