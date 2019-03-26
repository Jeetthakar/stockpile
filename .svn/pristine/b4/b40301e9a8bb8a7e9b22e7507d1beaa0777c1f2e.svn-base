/*
 * Created on Sep 7, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import com.harrier.initializeation.ConnectInit;

/**
 * @author ashishi
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class PatForm1 extends ActionForm {
	Logger Logging = Logger.getLogger(PatForm1.class);
	private Vector exchangeCollection = null;

	private String indName = null;

	private String b1 = null;

	private Vector finance = null;

	private String to = null;

	private String selectfinance = null;

	private String selectExchange = null;

	private String hiddenVar = null;

	private String pat = null;

	private String number = null;

	private String resetButton = null;

	private String b11 = null;

	private String net = null;
	
	private String intrest = null;
	
	private String tax = null;
	
	private String xdate = null;
	
	private String paidup = null;
	
	private String depth = null;
	
	private String sales = null;
	
	private String divided = null;
	
	
	private String resetvalue = null;
	
	private static StringBuffer disp_buffer = new StringBuffer();

//	app.Connect con = new app.Connect();

	

	public void reset() {
		this.setNumber("");
		this.setPat("");
		this.setSelectExchange("0");
		this.setSelectfinance("0");
		this.setTo("");
		this.setXdate("");
		//PatForm1.setDisp_buffer(String ="");
	}

	/**
	 * @return Returns the indName.
	 */
	public String getIndName() {
		return indName;
	}

	/**
	 * @param indName
	 *            The indName to set.
	 */
	public void setIndName(String indName) {
		this.indName = indName;
	}

	public Vector getExchangeCollection() {
		Vector temp2 = new Vector();
	
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		ResultSet rs = null;
		String query;

		// query=stock_exchange_list

		try {
			if (connection == null)
				connection = con.getdbConnection();
			query = ConnectInit.queries.getProperty("select_*_Company");
			temp2 = new Vector();
			temp2.add(new LabelValueBean("Not Sellected", "0"));
			try {
				Statement stmt = connection.createStatement();
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					temp2.add(new LabelValueBean(rs.getString(2), rs
							.getString(1)));
				}
				exchangeCollection = temp2;

			} catch (Exception e) {
				// TODO: handle exception
				Logging.error(" Error : " + e.getMessage());
			}
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}

		return exchangeCollection;
	}

	/**
	 * @return Returns the b1.
	 */
	public String getB1() {
		return b1;
	}

	/**
	 * @param b1
	 *            The b1 to set.
	 */
	public void setB1(String b1) {
		this.b1 = b1;
	}

	public Vector getFinance() {
		Vector temp2 = new Vector();
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		ResultSet rs = null;
		String query;

		// query=stock_exchange_list

		try {
			if (connection == null)
				connection = con.getdbConnection();
			query = ConnectInit.queries.getProperty("Selectcimmimpottype");
			temp2 = new Vector();
			temp2.add(new LabelValueBean("Not Sellected", "0"));
			try {
				Statement stmt = connection.createStatement();
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					temp2.add(new LabelValueBean(rs.getString(2), rs
							.getString(1)));
				}
				finance = temp2;

			} catch (Exception e) {
				// TODO: handle exception
				Logging.error(" Error : " + e.getMessage());
			}
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}

		return finance;
	}

	public String getSelectExchange() {
		return selectExchange;
	}

	public void setSelectExchange(String selectExchange) {
		this.selectExchange = selectExchange;
	}

	public String getSelectfinance() {
		return selectfinance;
	}

	public void setSelectfinance(String selectfinance) {
		this.selectfinance = selectfinance;
	}

	public void setExchangeCollection(Vector exchangeCollection) {
		this.exchangeCollection = exchangeCollection;
	}

	public void setFinance(Vector finance) {
		this.finance = finance;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public StringBuffer storePat() {
		StringBuffer buffer = new StringBuffer();
		StringBuffer buffernew = new StringBuffer();
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		CallableStatement stmt = null;
		try {
			Connect connect = ConnectInit.getConnect();
			try {
				if (connection != null)
					connection.close();
				connection = connect.getConnectionForTransaction();
				Logging.debug("connection is before rollback() " + connect);
				connection.rollback();
				Logging.debug("connection is after rollback()"
						+ Connect.con);
			} catch (SQLException ex) {
				Logging
						.error("Error : Unable to get Transaction connection "
								+ ex.getMessage());
			}
			String getcompanyname = ConnectInit.queries
			.getProperty("get_company_name");
			String insertCimmData = ConnectInit.queries
					.getProperty("insert_into_cimm_data");
		
			//Declaring the variable and get the value
			String cimm_xdate = getXdate();
			String company_name = getSelectExchange();
			int company_id = 0;
			int noofmonth = Integer.parseInt(getNumber());
			int counter = 0;
			int counter1 = 0;
			int cimm_id ;
			String company=null;

			try {
				Logging.debug("Counter is " + counter1);
				if (counter == 5) {
					connection.commit();
					connection.setAutoCommit(true);
					counter = 0;
				}
				if (counter1 % 100 == 0) {
					connection.commit();
					connection.close();
					try {
						if (connection == null)
							connection.close();
						connection = connect.getConnectionForTransaction();
						Logging.debug("connection after counter 100 is "
								+ connect);
						connection.rollback();
						Logging.debug("connection after counter 100 is "
								+ Connect.con);
					} catch (SQLException ex) {
						if (connection != null) {
							connection.rollback();
							connection.close();
						}
						Logging
								.error("Error : Unable to get Transaction connection "
										+ ex.getMessage());
					}
				}

				if (pst != null)
					pst.close();
				if (result != null)
					result.close();
				company_id = Integer.parseInt(company_name);
					
					pst = connection.prepareStatement(getcompanyname);
					pst.setInt(1, company_id);
					result = pst.executeQuery();
					while (result.next()) {
						company = result.getString(1);
					}
					buffernew.append("<tr><td bgcolor=#CBC9C9><strong>Value Inserted For:");
					buffernew.append(company);
					buffernew.append("</strong></td></tr>");
		
					if(!(getPat().equals(""))) {
						cimm_id = 1;
						int cimm_pat = Integer.parseInt(getPat());
						pst.close();
						pst = connection.prepareStatement(insertCimmData);
						pst.setInt(1, cimm_id);
						pst.setString(2, cimm_xdate);
						pst.setInt(3, company_id);
						pst.setInt(4, noofmonth);
						pst.setInt(5, cimm_pat);
						pst.setString(6, "N");
						pst.executeUpdate();
						buffernew
								.append("<tr><td align=center bgcolor=#D8D8D8><font color=blue>PAT</font></td></tr>");
					}
					if(!(getNet().equals(""))) {
						cimm_id = 2;
						int cimm_net = Integer.parseInt(getNet());
						pst.close();
						pst = connection.prepareStatement(insertCimmData);
						pst.setInt(1, cimm_id);
						pst.setString(2, cimm_xdate);
						pst.setInt(3, company_id);
						pst.setInt(4, noofmonth);
						pst.setInt(5, cimm_net);
						pst.setString(6, "N");
						pst.executeUpdate();
						buffernew
						.append("<tr><td align=center bgcolor=#D8D8D8><font color=blue>NET WORTH</font></td></tr>");
						
					}
					if(!(getDivided().equals(""))) {
						cimm_id = 3;
						int cimm_dividend = Integer.parseInt(getDivided());
						pst.close();
						pst = connection.prepareStatement(insertCimmData);
						pst.setInt(1, cimm_id);
						pst.setString(2, cimm_xdate);
						pst.setInt(3, company_id);
						pst.setInt(4, noofmonth);
						pst.setInt(5, cimm_dividend);
						pst.setString(6, "N");
						pst.executeUpdate();
						buffernew
						.append("<tr><td align=center bgcolor=#D8D8D8><font color=blue>Divided</font></td></tr>");
					}
					if(!(getDepth().equals(""))) {
						cimm_id = 4;
						int cimm_Inrest = Integer.parseInt(getIntrest());
						pst.close();
						pst = connection.prepareStatement(insertCimmData);
						pst.setInt(1, cimm_id);
						pst.setString(2, cimm_xdate);
						pst.setInt(3, company_id);
						pst.setInt(4, noofmonth);
						pst.setInt(5, cimm_Inrest);
						pst.setString(6, "N");
						pst.executeUpdate();
						buffernew
						.append("<tr><td align=center bgcolor=#D8D8D8><font color=blue>Intrest</font></td></tr>");
					}
					if(!(getTax().equals(""))) {
						cimm_id = 5;
						int cimm_Tax = Integer.parseInt(getTax());
						pst.close();
						pst = connection.prepareStatement(insertCimmData);
						pst.setInt(1, cimm_id);
						pst.setString(2, cimm_xdate);
						pst.setInt(3, company_id);
						pst.setInt(4, noofmonth);
						pst.setInt(5, cimm_Tax);
						pst.setString(6, "N");
						pst.executeUpdate();
						buffernew
						.append("<tr><td align=center bgcolor=#D8D8D8><font color=blue>Tax</font></td></tr>");
						
					}
					if(!(getPaidup().equals(""))) {
						cimm_id = 6;
						int cimm_paidup = Integer.parseInt(getPaidup());
						pst.close();
						pst = connection.prepareStatement(insertCimmData);
						pst.setInt(1, cimm_id);
						pst.setString(2, cimm_xdate);
						pst.setInt(3, company_id);
						pst.setInt(4, noofmonth);
						pst.setInt(5, cimm_paidup);
						pst.setString(6, "N");
						pst.executeUpdate();
						buffernew
						.append("<tr><td align=center bgcolor=#D8D8D8><font color=blue>Paidup Capital</font></td></tr>");
					}
					if(!(getDepth().equals(""))) {
						cimm_id = 7;
						int cimm_totaldept = Integer.parseInt(getDepth());
						pst.close();
						pst = connection.prepareStatement(insertCimmData);
						pst.setInt(1, cimm_id);
						pst.setString(2, cimm_xdate);
						pst.setInt(3, company_id);
						pst.setInt(4, noofmonth);
						pst.setInt(5, cimm_totaldept);
						pst.setString(6, "N");
						pst.executeUpdate();
						buffernew
						.append("<tr><td align=center bgcolor=#D8D8D8><font color=blue>Total Dept</font></td></tr>");
						
					}
					if(!(getSales().equals(""))) {
						cimm_id = 8;
						int cimm_sales = Integer.parseInt(getSales());
						pst.close();
						pst = connection.prepareStatement(insertCimmData);
						pst.setInt(1, cimm_id);
						pst.setString(2, cimm_xdate);
						pst.setInt(3, company_id);
						pst.setInt(4, noofmonth);
						pst.setInt(5, cimm_sales);
						pst.setString(6, "N");
						pst.executeUpdate();
						buffernew
						.append("<tr><td align=center bgcolor=#D8D8D8><font color=blue>Sales</font></td></tr>");
						
					}
			} catch (Exception ex) {
				Logging.error("Error : " + ex.getMessage());
			} finally {
				try {
					if (result != null)
						result.close();
					if (pst != null)
						pst.close();
					if (stmt != null)
						stmt.close();
				} catch (SQLException ex) {
					if (connection != null) {
						connection.rollback();
						connection.close();
					}
					Logging.error("Error :  " + ex.getMessage());
				}
			}
			buffernew.append(buffer);
			buffer =null;

		} catch (Exception e) {
			Logging.error("Error : " + e.getMessage());
		} finally {
			try {
				if (result != null)
					result.close();
				if (pst != null)
					pst.close();
				if (stmt != null)
					stmt.close();
				if (connection != null) {
					connection.rollback();
					connection.close();
				}
			} catch (SQLException ex) {

				Logging.error("Error : " + ex.getMessage());
			}
		}

		Logging.debug("sending buffer");

		return buffernew;
	}

	/**
	 * validate stock master form values.
	 */
	public ActionErrors validate(ActionMapping mapping,HttpServletRequest request)
	 {
		ActionErrors errors = new ActionErrors();
		
		
			try{
			
			if(selectExchange==null || selectExchange.trim().equals("0") || selectExchange.trim().equals("Not Sellected")){
				errors.add("selectExchange",new ActionError("Error.message.s_companyName"));				
			}
			if(number.equals("")) {
				errors.add("number",new ActionError("Error.message.s_noofmonth"));				
			}
			if(xdate.equals("")){
				errors.add("s_companyName",new ActionError("Error.message.s_xdate"));				
			}
			if(getPat().equals("")){
				errors.add("pat",new ActionError("Error.message.s_patvalue"));				
			}
			if(getNet().equals("")){
				errors.add("net",new ActionError("Error.message.s_NET"));				
			}
			if(getDivided().equals("")){
				errors.add("divident",new ActionError("Error.message.s_DIVIDEND"));				
			}
			if(getDepth().equals("")){
				errors.add("depth",new ActionError("Error.message.s_Depth"));				
			}
			if(getPaidup().equals("")){
				errors.add("paidup",new ActionError("Error.message.s_Capital"));				
			}
			if(getSales().equals("")){
				errors.add("sales",new ActionError("Error.message.s_Salse"));				
			}
			if(getIntrest().equals("")){
				errors.add("intrest",new ActionError("Error.message.s_INTREST"));				
			}
			if(getTax().equals("")){
				errors.add("tax",new ActionError("Error.message.s_TAX"));				
			}
		}catch(Exception e){
			errors.add(null,new ActionError("Error.message.msg"));
			Logging.debug("Error in Validation ");			
		}	
		
		return errors;
	 }
	
	
	public static StringBuffer getDisp_buffer() {
		return disp_buffer;
	}

	/**
	 * @param disp_buffer
	 *            The disp_buffer to set.
	 */
	public static void setDisp_buffer(StringBuffer disp_buffer) {
		PatForm1.disp_buffer = disp_buffer;
	}

	public String getHiddenVar() {
		return hiddenVar;
	}

	public void setHiddenVar(String hiddenVar) {
		this.hiddenVar = hiddenVar;
	}

	public String getPat() {
		return pat;
	}

	public void setPat(String pat) {
		this.pat = pat;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getResetButton() {
		return resetButton;
	}

	public void setResetButton(String resetButton) {
		this.resetButton = resetButton;
	}

	public String getB11() {
		return b11;
	}

	public void setB11(String b11) {
		this.b11 = b11;
	}

	public String getXdate() {
		return xdate;
	}

	public void setXdate(String xdate) {
		this.xdate = xdate;
	}

	public String getResetvalue() {
		return resetvalue;
	}

	public void setResetvalue(String resetvalue) {
		this.resetvalue = resetvalue;
	}

	public String getDepth() {
		return depth;
	}

	public void setDepth(String depth) {
		this.depth = depth;
	}

	public String getDivided() {
		return divided;
	}

	public void setDivided(String divided) {
		this.divided = divided;
	}

	public String getIntrest() {
		return intrest;
	}

	public void setIntrest(String intrest) {
		this.intrest = intrest;
	}

	public String getNet() {
		return net;
	}

	public void setNet(String net) {
		this.net = net;
	}

	public String getPaidup() {
		return paidup;
	}

	public void setPaidup(String paidup) {
		this.paidup = paidup;
	}

	public String getSales() {
		return sales;
	}

	public void setSales(String sales) {
		this.sales = sales;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

}