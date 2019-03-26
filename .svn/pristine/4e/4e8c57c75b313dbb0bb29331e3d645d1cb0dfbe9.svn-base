/*
 * Created on 28th Nov,2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author neha TODO To change the template for this generated type comment go
 *         to Window - Preferences - Java - Code Style - Code Templates
 */
public class BackTestIndexAction extends Action {
	Logger Logging = Logger.getLogger(BackTestIndexAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		BackTestIndexForm btest = (BackTestIndexForm) form;

		String reset = btest.getReset();
		String v1 = btest.getView();
		String v2 = btest.getV1();
		String compute = btest.getCompute();
		String index = btest.getIndex();
		String basedate = btest.getBase_date();
		String idxfromdate = btest.getIndex_from();
		String basevalue = btest.getBase_value();
		String basecurrency = btest.getBase_currency();
		String ret = btest.getResetbutton();
		String remove = btest.getRemove();
		String add = btest.getAdd();

		String stockselect = btest.getExcl_stock();
		String exchstockselect = btest.getIncl_stock();
		String exdate = btest.getExcl_date();
		String incldate = btest.getIncl_date();
		String[] strExclArray = btest.getExcl_scrip();
		String[] strInclArray = btest.getIncl_scrip();

		ArrayList<String> exclusionDates = btest.getExcl_date_array();
		ArrayList<String> inclusionDates = btest.getIncl_date_array();

		try {
			if (reset.equals("Reset")) {
				reset(btest);
			} else {

				if (index != null && index.equals("0")) {
					resetAllFields(btest);
				}

				if (remove != null && remove.trim().equals("Remove")) {
					btest.setExcl_stock(stockselect);
					Logging.debug("stock selected");
				}

				if (add != null && add.trim().equals("Add")) {
					btest.setIncl_stock(exchstockselect);
					Logging.debug("stock under exch selected");

				}
				if (v2 != null && v2.equals("View")) {
					btest.setOperation("yes");

				}

				if (compute != null && compute.equals("Compute")) {

					Connection connection = null;
					// Connect c = ConnectInit.getConnect();
					app.NewIndexForm ndf = new app.NewIndexForm();
					// harrier.income.com.compute.CIndexCalculator cic = new
					// harrier.income.com.compute.CIndexCalculator();
					BackTestCompute btc = new BackTestCompute();
					String settlement = "n";
					try {

						try {
							settlement = ndf.getB_computeSettlementValue();
							if (settlement == null || !settlement.equals("on")) {
								settlement = "n";
							} else {
								settlement = "y";
							}
						} catch (Exception e) {
							settlement = "n";
							// TODO: handle exception
						}
						// String ComputeIndex = btc.computebacktestindex(index,
						// basedate, idxfromdate, basevalue, null,
						// settlement, "n", "yes", null, stockselect,
						// exdate, exchstockselect, incldate);

						String ComputeIndex = btc.computebacktestindex(index,
								basedate, idxfromdate, basevalue, null,
								settlement, "n", "yes", null, strExclArray,
								exclusionDates, strInclArray, inclusionDates);

						btest.setBtvalue(ComputeIndex);
					} catch (Exception e) {
						Logging.error(" Error : " + e.getMessage());
					} finally {
						try {
							if (connection != null)
								connection.close();
						} catch (Exception ee) {
							Logging.error(" Error : Unable to close connection "
									+ ee.getMessage());
						}
					}
				}
			}

		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		}

		return mapping.getInputForward();
	}

	public void resetAllFields(BackTestIndexForm btest) {
		btest.setIndex(null);
		btest.setBase_currency(null);
		btest.setBase_date(null);
		btest.setBase_value(null);
		btest.setBtvalue(null);
		btest.setIndex_from(null);
		btest.setIndex_name(null);
		btest.setIndex_type(null);

	}

	private void reset(BackTestIndexForm btest) {
		btest.setIncl_scrip(null);
		btest.setExcl_scrip(null);

		// btest.setIncludedScripCollection(null);
		// btest.setExcludedScripCollection(null);
	}
}
