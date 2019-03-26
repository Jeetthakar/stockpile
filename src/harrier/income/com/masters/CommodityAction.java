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

public class CommodityAction extends Action {
	Logger Logging = Logger.getLogger(CommodityAction.class);
	//static Connect con1 = ConnectInit.getConnect();
	//static Connect c =ConnectInit.getConnect();
	//static Connection con = null;
	Connect c = ConnectInit.getConnect();

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {

			StockMasterBondsCommodities form1 = (StockMasterBondsCommodities) form;
			ActionForward fr = null;
			String chk_but = form1.getB1();
			String chk_but1 = form1.getB2();
			Logging.debug("inside action " + chk_but + " inside action "
					+ chk_but1);

			if (chk_but != null && chk_but.equals("Submit")) {
				Logging.debug("Inside button  submit");
				String query = null;
				String qry1 = null;
				int flag = 0;
				//QueryClass1.setOldValues(form1);
				String stk_id = form1.getS_stockID();

				Logging.debug(stk_id);

				form1.setVarify("fill");

				if (stk_id == null) {
					Logging.debug("button is submit");
					query = null;
					qry1 = null;
					query = ConnectInit.queries
							.getProperty("insert_into_stock_master_commodities");
					//QueryClass1.insertIntoStockMasterCommodity(query,form);
					//Calling Method for Insertion...
					insertIntoStockMasterCommodity(query, form1);

					// form1.setS_stockID("12");
					return fr = new ActionForward(
							"/pages/masters/commodities.jsp?from=action");

				}//Close if(stk_id==null)........

			} //Close if(chk_but!=null && chk_but.equals("Submit")).......

		} catch (Exception ae) {
			Logging.error("Exception :" + ae.getMessage());
		}

		return mapping.getInputForward();
	}//Close execute().....

	public void insertIntoStockMasterCommodity(String query,
			StockMasterBondsCommodities form1) {
		//StockMasterBondsCommodities form1 =
		// (StockMasterBondsCommodities)form;
		Connection con = null;
		try {

			if (con == null) {
				con = c.getdbConnection();
			}

			String act = form1.getB_isActive();
			Logging.debug("active=" + act);
			String act_val = null;
			if (act == null) {
				act_val = "n";
			} else
				act_val = "y";
			PreparedStatement psmt = con.prepareStatement(query);

			Logging.debug("in query");

			Statement stmt = con.createStatement();
			ResultSet rs = null;
			int para = 0;
			rs = stmt.executeQuery(ConnectInit.queries
					.getProperty("get_sequence_stock_id_commodities"));
			while (rs.next()) {
				Logging.debug("stock max" + rs.getInt(1));
				para = rs.getInt(1);
				form1.setS_stockID(String.valueOf(rs.getInt(1)));
			}
			Logging.debug("para is " + para);
			rs.close();

			psmt.setInt(1, para);
			psmt.setInt(2, 2);//form1.getS_stockType());
			if (form1.getD_iwf().equals("")) {
				psmt.setString(3, null);
			} else
				psmt.setString(3, form1.getD_iwf());

			psmt.setString(4, form1.getS_stockName().trim());
			psmt.setString(5, form1.getD_listingDate());

			if (form1.getF_alertPercent().equals("")) {
				psmt.setString(6, null);
			} else
				psmt.setString(6, form1.getF_alertPercent());

			if (form1.getF_rejectionPercent().equals("")) {
				psmt.setString(7, null);
			} else
				psmt.setString(7, form1.getF_rejectionPercent());

			if (form1.getS_marketLot().equals("")) {
				psmt.setString(8, null);
			} else
				psmt.setString(8, form1.getS_marketLot());

			psmt.setString(9, form1.getS_stockExchange());

			if (form1.getS_countryName().equals("")) {
				psmt.setString(10, null);
			} else
				psmt.setString(10, form1.getS_countryName());

			psmt.setString(11, act_val);//11

			if (form1.getS_stockCurrency().equals("")) {
				psmt.setString(12, null);
			} else
				psmt.setString(12, form1.getS_stockCurrency());

			psmt.setString(13, form1.getB_isPriceForLot());
			psmt.setString(14, null);
			psmt.setString(15, form1.getExp_date());
			psmt.setString(16, form1.getDeliverycenter());
			psmt.setString(17, form1.getMeasure());
			psmt.setString(18, form1.getB_sdl());
			psmt.setString(19, form1.getB_isn());
			psmt.setString(20, form1.getB_ric());
			psmt.setString(21, form1.getB_crisil());
			psmt.setString(22, form1.getB_csp());
			psmt.setString(23, form1.getB_exc_code());
			psmt.setString(24, form1.getB_tkr());

			psmt.executeUpdate();

			form1.setCheck_flag("0");
			psmt.close();
			Logging.debug("insert into stock master" + psmt);

		} catch (Exception e) {
			form1.setCheck_flag("1");
			Logging.error("Error is : " + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
	}//Close insertIntoStockMasterCommodity().....

}//Close CommodityAction().........

