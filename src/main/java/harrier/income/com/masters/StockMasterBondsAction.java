/*
 * Created on Jul 6, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.masters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * @author sonali
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class StockMasterBondsAction extends Action {
	Logger Logging = Logger.getLogger(StockMasterBondsAction.class);
	/*
	 * static Connect c= ConnectInit.getConnect(); static Connection con=null;
	 */
	Connect c = ConnectInit.getConnect();

	//Connection con=null;
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub

		StockMasterBondsCommodities stockmaster = (StockMasterBondsCommodities) form;
		String commit_button = stockmaster.getCommit_button();
		if (commit_button.equals("commit")) {
			stockmaster.setVarify("fill");
			insertIntoStockMasterBonds(stockmaster);
		}
		//return mapping.findForward("success");
		return new ActionForward(
				"/pages/masters/StockMasterBonds.jsp?from=action");
	}

	public void insertIntoStockMasterBonds(
			StockMasterBondsCommodities stockmaster) {
		Logging.debug("inside insertIntoStockMasterBonds");
		String query = ConnectInit.queries.getProperty("insert_into_stockmasterbonds");
		String query1 = ConnectInit.queries.getProperty("get_sequence_stock_id_bonds");
		Logging.info(query);
		Connection con = null;
		PreparedStatement pst = null;
		Statement st = null;
		ResultSet rs = null;
		try {

			if (con == null) {
				con = c.getdbConnection();
			}
			pst = con.prepareStatement(query);
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				int stockid = rs.getInt(1);
				pst.setInt(1, stockid);
				Integer i = new Integer(stockid);
				String stkid = i.toString();
				stockmaster.setS_stockID(stkid);
			}
			pst.setString(2, "3");
			pst.setString(3, stockmaster.getS_companyName());
			pst.setString(4, stockmaster.getD_iwf());
			pst.setString(5, stockmaster.getF_issuedShares());
			pst.setString(6, stockmaster.getS_stockName());
			pst.setString(7, stockmaster.getF_faceValue());
			pst.setString(8, stockmaster.getD_listingDate());
			pst.setString(9, stockmaster.getS_ratingCode());
			pst.setString(10, stockmaster.getF_alertPercent());
			pst.setString(11, stockmaster.getF_rejectionPercent());
			pst.setString(12, stockmaster.getF_withholdingTaxPercent());
			pst.setString(13, stockmaster.getS_marketLot());
			pst.setString(14, stockmaster.getS_stockExchange());
			pst.setString(15, stockmaster.getS_countryName());
			pst.setString(16, stockmaster.getB_isActive());
			pst.setString(17, stockmaster.getS_stockCurrency());
			pst.setString(18, stockmaster.getB_isPriceForLot());
			pst.setString(19, stockmaster.getD_paidValue());
			pst.setString(20, stockmaster.getDirty_price());
			pst.setString(21, stockmaster.getStart_date());
			pst.setString(22, stockmaster.getMaturity_date());
			pst.setString(23, stockmaster.getCoupon_percentage());
			pst.setString(24, stockmaster.getCoupon_period());
			pst.setString(25, stockmaster.getCoupon_payment_dates());
			if (stockmaster.getInterest_basis_month().equals("")) {
				pst.setString(26, null);
			} else {
				pst.setString(26, stockmaster.getInterest_basis_month());
			}
			if (stockmaster.getInterest_basis_year().equals("")) {
				pst.setString(27, null);
			} else {
				pst.setString(27, stockmaster.getInterest_basis_year());
			}
			pst.setString(28, stockmaster.getAccrued_interest());
			pst.setString(29, stockmaster.getDescription());
			pst.setString(30, stockmaster.getB_sdl());
			pst.setString(31, stockmaster.getB_isn());
			pst.setString(32, stockmaster.getB_ric());
			pst.setString(33, stockmaster.getB_crisil());
			pst.setString(34, stockmaster.getB_csp());
			pst.setString(35, stockmaster.getB_tkr());
			pst.setString(36, stockmaster.getB_exc_code());
			pst.setString(37, stockmaster.getB_withHoldingTaxApplicable());
			pst.setString(38, stockmaster.getClean_price());
			pst.execute();
			Logging.info("stock inserted successfully");
			stockmaster.setSuccess_flag("0");
			pst.close();

		} catch (Exception e) {
			Logging.error(e.getMessage());
			stockmaster.setSuccess_flag("1");

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
}