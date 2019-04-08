/*
 * Created on Mar 13, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.harrier.initializeation.ConnectInit;

/**
 * @author pankajb
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FinanceResultAction extends Action {
	Logger Logging = Logger.getLogger(FinanceResultAction.class);
	 Connect c = ConnectInit.getConnect();
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FinanceResultForm frslt = (FinanceResultForm) form;
		
		String operation = frslt.getOperation();
		String strch1="false";
		String strch2="false";
		String strch3="false";
		String isAudited = "NO";
		String isCumulative = "NO";
		String isConsolidated = "NO";
		float gross_income = 0;
		float total_expenditure = 0;
		float profit_Before_Depreciation = 0;
		float profit_Before_tax = 0;
		float profit_After_tax = 0;
		float book_value = 0;
		int company_id=0;
		
		
		if(operation.equals("insert")) {
			
			
			Logging.debug("Inside Insert of Finance Result ");
			
			Connection con = null;
			PreparedStatement pst = null;
			Statement st = null;
			ResultSet rs = null;
			int fdetail_id_next = 0;
			String query = ConnectInit.queries.getProperty("insert_into_finance_details");
			//String query = "Insert into financial_detail(fdetail_id,company_id,net_sales,other_income,gross_income,increase_decrease_in_stock,consumption_of_raw_materials,staff_cost,other_expenditure,total_expenditure,interest,profit_loss_before_depreciation_taxes,depreciation,profit_loss_before_tax,provision_for_taxation,other_provisions,net_profit_loss,non_recurring_income,non_recurring_expenses,paid_up_equity_share_capital,reserves_excluding_revaluation_reserves,dividend,basic_eps,from_date,to_date,is_audited,is_cumulative,is_consolidated,book_value,pat,net_worth,debt,no_of_month,no_of_share,percent_of_shareholding,unit,currency_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			//String query1 = "select MAX(fdetail_id) from financial_detail ";
			String query1 = ConnectInit.queries.getProperty("finance_id_max");
			try {

				if (con == null) {
					con = c.getdbConnection();
				}
				
				st = con.createStatement();
				rs = st.executeQuery(query1);
				if (rs.next()) {
					 int fdetail_id = rs.getInt(1);
					 fdetail_id_next = fdetail_id + 1;
				}
					pst = con.prepareStatement(query);
					pst.setInt(1,fdetail_id_next);
					
					Logging.debug("After id ");
			
		    String companyid = frslt.getS_companyName();
			if(!(companyid==null || companyid.equals(""))){
				//company_id  = Integer.parseInt(companyid);
				pst.setInt(2,Integer.parseInt(companyid));
			}
			else{
				pst.setInt(2,0);
			}
			Logging.debug("After company ");
			
			String netSales = frslt.getNetSales();
			 if(!(netSales == null || netSales.equals(""))){
			 	pst.setFloat(3,Float.parseFloat(netSales));
			 	//Logging.debug("After netsales value "+netSales);
			 }else
			 {
			 	pst.setFloat(3,0);
			 }
			 Logging.debug("After netsales ");
			 
			 String otherIncome = frslt.getOtherIncome();
			 if(!(otherIncome == null || otherIncome.equals(""))){
			 	pst.setFloat(4,Float.parseFloat(otherIncome));
			 }else
			 {
			 	pst.setFloat(4,0);
			 }
			 
			 
			 /*--  For Gross Income  --*/
			
			 if(netSales == null || netSales.equals(""))
			 	netSales = "0.0";
			 if(otherIncome == null || otherIncome.equals(""))
			 	otherIncome = "0.0";
			 
			
			 float non_Rec_Inc = 0;
			 String non_Recurring_Income = frslt.getNonRecurringIncome();
			  if(!(non_Recurring_Income == null || non_Recurring_Income.trim().equals(""))){
			  	non_Rec_Inc = Float.parseFloat(non_Recurring_Income);
			  }
			   else
			   {
			   	non_Rec_Inc = 0;
			   }
			
			 
			  gross_income = Float.parseFloat(netSales) + Float.parseFloat(otherIncome) + non_Rec_Inc ;
			  
			  //String grossIncome = frslt.getGrossIncome();
			  String grossIncome = (new Float(gross_income)).toString();
			  frslt.setGrossIncome(grossIncome);
			  
			 Logging.debug("Gross Inc "+grossIncome);
			
			 if(gross_income != 0)
			 {
			 	pst.setFloat(5,gross_income);
			 }else
			 {
			 	pst.setFloat(5,0);
			 }
			 
			 Logging.debug("After grossIncome ");
			 
			 String incDecStock = frslt.getIncDecStock().trim();
			 if(!(incDecStock == null ||incDecStock.equals(""))){
			 	pst.setFloat(6,Float.parseFloat(incDecStock));
			 }else
			 {
			 	pst.setFloat(6,0);
			 }
			 
			 String rawMaterial = frslt.getRawMaterial().trim();
			 if(!(rawMaterial == null || rawMaterial.equals(""))){
			 	pst.setFloat(7,Float.parseFloat(rawMaterial));
			 }else
			 {
			 	pst.setFloat(7,0);
			 }
			 
			 String empCost = frslt.getEmpCost().trim();
			 if(!(empCost == null || empCost.equals(""))){
			 	pst.setFloat(8,Float.parseFloat(empCost));
			 }else
			 {
			 	pst.setFloat(8,0);
			 }
			 Logging.debug("After empCost ");
			 
			 String otherExpenditure = frslt.getOtherExpenditure().trim();
			 if(!(otherExpenditure == null || otherExpenditure.equals(""))){
			 	pst.setFloat(9,Float.parseFloat(otherExpenditure));
			 }else
			 {
			 	pst.setFloat(9,0);
			 }
			 
			 /*-- For Total Expenditure --*/
			 if(incDecStock == null || incDecStock.equals(""))
			 	incDecStock = "0.0";
			 if(rawMaterial == null || rawMaterial.equals(""))
			 	rawMaterial = "0.0";
			 if(empCost == null || empCost.equals(""))
			 	empCost = "0.0";
			
			 float nor_rec_exp = 0;
			 String nonrecexp = frslt.getNonRecurringExpenditure();
			 if(!(nonrecexp == null || nonrecexp.equals(""))){
			 	nor_rec_exp = Float.parseFloat(nonrecexp);
			 }else
			 {
			 	nor_rec_exp = 0;
			 }
			 float oth_exp = 0;
			 String othexp = frslt.getOtherExpenditure();
			 if(!(othexp == null || othexp.equals(""))){
			 	oth_exp = Float.parseFloat(othexp);
			 }else
			 {
			 	oth_exp = 0;
			 }
							
			 total_expenditure =  Float.parseFloat(incDecStock) + Float.parseFloat(rawMaterial)+ Float.parseFloat(empCost) + nor_rec_exp + oth_exp ;
			 if(total_expenditure != 0){
			  	pst.setFloat(10,total_expenditure);
			  }else {
			  	pst.setFloat(10,0);
			  }
			  
			 
			 
			  String iterest = frslt.getInterest().trim();
			  if(!(iterest == null || iterest.equals(""))){
			 	pst.setFloat(11,Float.parseFloat(iterest));
			 }else
			 {
			 	pst.setFloat(11,0);
			 }
			  
			  
			  /*--- For Profit Before Depreciation --*/
			  float intrst = 0;
			  			  
			  if(!(iterest == null || iterest.trim().equals(""))){
			  	
			  	intrst = Float.parseFloat(iterest) ;
			  }
			  profit_Before_Depreciation =  gross_income - (total_expenditure + intrst)  ;
			   
			  //String profitBeforeDepreciation = frslt.getProfitBeforeDepreciation();
			    String profitBeforeDepreciation = (new Float(profit_Before_Depreciation)).toString();
			     frslt.setProfitBeforeDepreciation(profitBeforeDepreciation);
			     Logging.debug("profit_Before_Depreciation "+profit_Before_Depreciation);
			     if(profit_Before_Depreciation != 0)
			     {
			     	pst.setFloat(12,profit_Before_Depreciation);
			     }
			     else
			    {
			 	pst.setFloat(12,0);
			     }
			  
			  Logging.debug("After profitBeforeDepreciation ");
			  
			  String depriciation = frslt.getDepriciation();
			  if(!(depriciation == null || depriciation.trim().equals(""))){
			 	pst.setFloat(13,Float.parseFloat(depriciation));
			 }else
			 {
			 	pst.setFloat(13,0);
			 }
			  
			  /*------For Profit Befor TAX   ------*/
			  
			  float deprct = 0;
			  
			  if(!(depriciation == null || depriciation.trim().equals(""))){
			  	deprct = Float.parseFloat(depriciation);
			  }
			  profit_Before_tax = (profit_Before_Depreciation - deprct);
			  
			  //String profitBeforeTax = frslt.getProfitBeforeTax();
               String profitBeforeTax = (new Float(profit_Before_tax)).toString();
                frslt.setProfitBeforeTax(profitBeforeTax) ;
			  Logging.debug("profit_Before_tax "+profit_Before_tax);
			  
			  if(!(profitBeforeTax == null || profitBeforeTax.trim().equals(""))){
			 	pst.setFloat(14,Float.parseFloat(profitBeforeTax));
			 }else
			 {
			 	pst.setFloat(14,0);
			 }
			  
			  
			  String provisionTax = frslt.getProvisionTax();
			  if(!(provisionTax == null || provisionTax.trim().equals(""))){
			 	pst.setFloat(15,Float.parseFloat(provisionTax));
			 }else
			 {
			 	pst.setFloat(15,0);
			 }
			  
			  String other_provisions = frslt.getFringBefTax();
			  if(!(other_provisions == null || other_provisions.trim().equals(""))){
			 	pst.setFloat(16,Float.parseFloat(other_provisions));
			 }else
			 {
			 	pst.setFloat(16,0);
			 }
			  
			  String net_profit_loss = frslt.getDefferedTaxExpenses();
			  if(!(net_profit_loss == null || net_profit_loss.trim().equals(""))){
			 	pst.setFloat(17,Float.parseFloat(net_profit_loss));
			 }else
			 {
			 	pst.setFloat(17,0);
			 }
			  
			  String nonRecurringIncome = frslt.getNonRecurringIncome();
			  
			  if(!(nonRecurringIncome == null || nonRecurringIncome.trim().equals(""))){
			 	pst.setFloat(18,Float.parseFloat(nonRecurringIncome));
			 }else
			 {
			 	pst.setFloat(18,0);
			 }
			  Logging.debug("After nonRecurringIncome ");
			  
			  String nonRecurringExpenditure = frslt.getNonRecurringExpenditure();
			  
			  if(!(nonRecurringExpenditure == null || nonRecurringExpenditure.trim().equals(""))){
			 	pst.setFloat(19,Float.parseFloat(nonRecurringExpenditure));
			 }else
			 {
			 	pst.setFloat(19,0);
			 }
			  
			  
			  String paidupShareCapital = frslt.getPaidupShareCapital();
			  if(!(paidupShareCapital == null || paidupShareCapital.equals(""))){
			 	pst.setFloat(20,Float.parseFloat(paidupShareCapital));
			 }else
			 {
			 	pst.setFloat(20,0);
			 }
			  
			  String reserveExcludingreval = frslt.getReserveExcludingreval();
			  if(!(reserveExcludingreval == null || reserveExcludingreval.equals(""))){
			 	pst.setFloat(21,Float.parseFloat(reserveExcludingreval));
			 }else
			 {
			 	pst.setFloat(21,0);
			 }
			  
			  String dividend = frslt.getDividend();
			  if(!(dividend == null || dividend.equals(""))){
			 	pst.setFloat(22,Float.parseFloat(dividend));
			 }else
			 {
			 	pst.setFloat(22,0);
			 }
			  
			  String eps = frslt.getEps();
			  
			  if(!(eps == null || eps.equals(""))){
			 	pst.setFloat(23,Float.parseFloat(eps));
			 }else
			 {
			 	pst.setFloat(23,0);
			 }
			 
			  
			  String from_date = frslt.getStart_date();
			  if(!(from_date == null || from_date.equals(""))){
			 	pst.setString(24,from_date);
			 }else
			 {
			 	pst.setString(24,"");
			 }
			  
			  String to_date = frslt.getTo_date();
			  if(!(to_date == null || to_date.equals(""))){
			 	pst.setString(25,to_date);
			 }else
			 {
			 	pst.setString(25,"");
			 }
			  
			  boolean ch1 = frslt.isAudited();
			  if(ch1)
			  {
				isAudited="YES";
				pst.setString(26,isAudited);
			  }else
			  {
			  	pst.setString(26,isAudited);
			  }
			  
			  boolean ch2 = frslt.isCumulative();
			  
			  if(ch2){
			  	isCumulative = "YES";
			  	pst.setString(27,isCumulative );
			  }
			  else {
			  	pst.setString(27,isCumulative );
			  }
			  
			  boolean ch3 = frslt.isConsolidated();
			  if(ch3){
			  	isConsolidated = "YES";
			  	pst.setString(28,isConsolidated);
			  }
			  else {
			  	pst.setString(28,isConsolidated);
			  }
			  
			  /*--------------- For BOOK VALUE -----*/
			  float noshare = 0;
			  float netwrt = 0;
			  String no_of_share = frslt.getNumberOfShares();
			  String netWorth = frslt.getNetWorth();
			  
			  if(!(no_of_share == null || no_of_share.equals(""))){
			  	noshare = Float.parseFloat(no_of_share);
			  }
			  
			  if(!(netWorth == null || netWorth.equals(""))){
			  	netwrt = Float.parseFloat(netWorth);
			  }
			  if( (netwrt != 0) || (noshare != 0))
			  book_value = (netwrt/noshare);
			  else
			  	book_value = 0; 	
			  
			 // String bookValue = frslt.getBookValue();
			  String bookValue = (new Float(book_value)).toString();
			  frslt.setBookValue(bookValue);
			  Logging.debug("Book Value "+book_value);
			  
			  if(!(bookValue == null || bookValue.equals(""))){
			 	pst.setFloat(29,Float.parseFloat(bookValue));
			 }else
			 {
			 	pst.setFloat(29,0);
			 }
			 
			  /*-- Calculation req for PAT [Profit After TAX] --*/
			  float prov_tax = 0;
			  float fring_ben_tax = 0;
			  float def_tax_exp = 0;
			  
			  if(!(provisionTax == null || provisionTax.trim().equals(""))){
			 	prov_tax = Float.parseFloat(provisionTax);
			 }
			  			  
			  if(!(other_provisions == null || other_provisions.trim().equals(""))){
			  	fring_ben_tax = Float.parseFloat(other_provisions);
			 }
			  
			  if(!(net_profit_loss == null || net_profit_loss.trim().equals(""))){
			  	def_tax_exp = Float.parseFloat(net_profit_loss);
			 }
			  
			  profit_After_tax = profit_Before_tax - (prov_tax + fring_ben_tax + def_tax_exp);
			  
			  //String patvalue = frslt.getProfitAftTax();
			  String patvalue = (new Float(profit_After_tax)).toString();
			  Logging.debug("profit_After_tax "+profit_After_tax);
			  
			    frslt.setProfitAftTax(patvalue);
			  
			  if(!(patvalue == null || patvalue.equals(""))){
			 	pst.setFloat(30,Float.parseFloat(patvalue));
			 }else
			 {
			 	pst.setFloat(30,0);
			 }
			  
			  
			  
			  //String netWorth = frslt.getNetWorth();
			  if(!(netWorth == null || netWorth.trim().equals(""))){
			 	pst.setFloat(31,Float.parseFloat(netWorth));
			 }else
			 {
			 	pst.setFloat(31,0);
			 }
			  
			  String totalDebt = frslt.getTotalDebt();
			  if(!(totalDebt == null || totalDebt.trim().equals(""))){
			 	pst.setFloat(32,Float.parseFloat(totalDebt));
			 }else
			 {
			 	pst.setFloat(32,0);
			 }
			  
			  String months = frslt.getMonths();
			  if(!(months == null || months.trim().equals(""))){
			 	pst.setInt(33,Integer.parseInt(months));
			 }else
			 {
			 	pst.setInt(33,0);
			 }
			  			  
			// 
			  if(!(no_of_share == null || no_of_share.equals(""))){
				pst.setInt(34,Integer.parseInt(no_of_share));
			}
			else{
				pst.setInt(34,0);
			}
			  
			  String percentageOfShareHolding = frslt.getPercentageOfShareHolding();
			  if(!(percentageOfShareHolding == null || percentageOfShareHolding.equals(""))){
			 	pst.setFloat(35,Float.parseFloat(percentageOfShareHolding));
			 }else
			 {
			 	pst.setFloat(35,0);
			 }
			  
			  String units = frslt.getUnits();
			  if(!(units == null || units.equals(""))){
			 	pst.setString(36,units);
			 }else
			 {
			 	pst.setString(36,"0");
			 }
			  				  
				  String currency = frslt.getCurrency();
				  if(!(currency == null || currency.trim().equals(""))){
				 	pst.setInt(37,Integer.parseInt(currency));
				 }else
				 {
				 	pst.setInt(37,0);
				 }
			  pst.execute();
			  
			  frslt.setCheck_flag("0");
			  
				Logging.info("finance result inserted successfully");
			 pst.close();
			}catch (NumberFormatException nfe) {
				 frslt.setCheck_flag("1");
		         Logging.debug("NumberFormatException: " + nfe.getMessage());
		         
		      }
			catch(SQLException erq)
			{
				frslt.setCheck_flag("1");
				Logging.error("SQL Error in Insert "+erq.getMessage());
				
			}
			catch(Exception e)
			{
				frslt.setCheck_flag("1");
				Logging.error("Error in Insert "+e.getMessage());
			}
			
			finally {
				try {
					if (con != null)
						con.close();
				} catch (Exception ee) {
					Logging.error(" Error : Unable to close Connection "
							+ ee.getMessage());
				}
			}
		} // End if(operation.equals("insert"))
		if(operation.equals("reset")) {
			frslt.check_flag="";
			frslt.grossIncome="";
			frslt.profitBeforeDepreciation="";
			frslt.profitBeforeTax="";
			frslt.bookValue="";
			frslt.setAudited(false);
			frslt.setCumulative(false);
			frslt.setConsolidated(false);
			
		}
		
		return new ActionForward("/pages/FinanceResult.jsp");
		
	} //End Action Forward.....
	
} //End FinanceResultAction.........
