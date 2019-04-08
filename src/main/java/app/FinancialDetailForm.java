/*
 * Created on Jan 29, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * @author sudhir
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FinancialDetailForm {
	String company_id,symbol,series,net_sales,other_income,gross_income,increase_decrease_in_stock,
	consumption_of_raw_material,staff_cost,total_expenditure_excluding_other_expenditure,
	other_expenditure,total_expenditure,interest,profit_loss_before_depriciation_tax,provision_for_taxation,
	other_provisions,misc_expd_w_o,net_profit_loss,non_recurring_income,non_recurring_expenses,
	adjusted_net_profit_loss,paid_up_equity_share_capital,reserves_excluding_revaluation_reservers,
	dividend,basic_eps,diluted_eps,from_date,to_date,result_type,fin_year,book_value,depreciation,Profit_loss_before_tax,
	is_Audited,is_Cumulative,is_Consolidated,net_worth ;
	public static void main(String[] args) {
	}
	/**
	 * @return Returns the adjusted_net_profit_loss.
	 */
	public String getAdjusted_net_profit_loss() {
		return adjusted_net_profit_loss;
	}
	/**
	 * @param adjusted_net_profit_loss The adjusted_net_profit_loss to set.
	 */
	public void setAdjusted_net_profit_loss(String adjusted_net_profit_loss) {
		this.adjusted_net_profit_loss = adjusted_net_profit_loss;
	}
	/**
	 * @return Returns the basic_eps.
	 */
	public String getBasic_eps() {
		return basic_eps;
	}
	/**
	 * @param basic_eps The basic_eps to set.
	 */
	public void setBasic_eps(String basic_eps) {
		this.basic_eps = basic_eps;
	}
	/**
	 * @return Returns the book_value.
	 */
	public String getBook_value() {
		return book_value;
	}
	/**
	 * @param book_value The book_value to set.
	 */
	public void setBook_value(String book_value) {
		this.book_value = book_value;
	}
	/**
	 * @return Returns the company_id.
	 */
	public String getCompany_id() {
		return company_id;
	}
	/**
	 * @param company_id The company_id to set.
	 */
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	/**
	 * @return Returns the consumption_of_raw_material.
	 */
	public String getConsumption_of_raw_material() {
		return consumption_of_raw_material;
	}
	/**
	 * @param consumption_of_raw_material The consumption_of_raw_material to set.
	 */
	public void setConsumption_of_raw_material(
			String consumption_of_raw_material) {
		this.consumption_of_raw_material = consumption_of_raw_material;
	}
	/**
	 * @return Returns the diluted_eps.
	 */
	public String getDiluted_eps() {
		return diluted_eps;
	}
	/**
	 * @param diluted_eps The diluted_eps to set.
	 */
	public void setDiluted_eps(String diluted_eps) {
		this.diluted_eps = diluted_eps;
	}
	/**
	 * @return Returns the dividend.
	 */
	public String getDividend() {
		return dividend;
	}
	/**
	 * @param dividend The dividend to set.
	 */
	public void setDividend(String dividend) {
		this.dividend = dividend;
	}
	/**
	 * @return Returns the fin_year.
	 */
	public String getFin_year() {
		return fin_year;
	}
	/**
	 * @param fin_year The fin_year to set.
	 */
	public void setFin_year(String fin_year) {
		this.fin_year = fin_year;
	}
	/**
	 * @return Returns the from_date.
	 */
	public String getFrom_date() {
		return from_date;
	}
	/**
	 * @param from_date The from_date to set.
	 */
	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}
	/**
	 * @return Returns the gross_income.
	 */
	public String getGross_income() {
		return gross_income;
	}
	/**
	 * @param gross_income The gross_income to set.
	 */
	public void setGross_income(String gross_income) {
		this.gross_income = gross_income;
	}
	/**
	 * @return Returns the increase_decrease_in_stock.
	 */
	public String getIncrease_decrease_in_stock() {
		return increase_decrease_in_stock;
	}
	/**
	 * @param increase_decrease_in_stock The increase_decrease_in_stock to set.
	 */
	public void setIncrease_decrease_in_stock(String increase_decrease_in_stock) {
		this.increase_decrease_in_stock = increase_decrease_in_stock;
	}
	/**
	 * @return Returns the interest.
	 */
	public String getInterest() {
		return interest;
	}
	/**
	 * @param interest The interest to set.
	 */
	public void setInterest(String interest) {
		this.interest = interest;
	}
	/**
	 * @return Returns the misc_expd_w_o.
	 */
	public String getMisc_expd_w_o() {
		return misc_expd_w_o;
	}
	/**
	 * @param misc_expd_w_o The misc_expd_w_o to set.
	 */
	public void setMisc_expd_w_o(String misc_expd_w_o) {
		this.misc_expd_w_o = misc_expd_w_o;
	}
	/**
	 * @return Returns the net_profit_loss.
	 */
	public String getNet_profit_loss() {
		return net_profit_loss;
	}
	/**
	 * @param net_profit_loss The net_profit_loss to set.
	 */
	public void setNet_profit_loss(String net_profit_loss) {
		this.net_profit_loss = net_profit_loss;
	}
	/**
	 * @return Returns the net_sales.
	 */
	public String getNet_sales() {
		return net_sales;
	}
	/**
	 * @param net_sales The net_sales to set.
	 */
	public void setNet_sales(String net_sales) {
		this.net_sales = net_sales;
	}
	/**
	 * @return Returns the non_recurring_expenses.
	 */
	public String getNon_recurring_expenses() {
		return non_recurring_expenses;
	}
	/**
	 * @param non_recurring_expenses The non_recurring_expenses to set.
	 */
	public void setNon_recurring_expenses(String non_recurring_expenses) {
		this.non_recurring_expenses = non_recurring_expenses;
	}
	/**
	 * @return Returns the non_recurring_income.
	 */
	public String getNon_recurring_income() {
		return non_recurring_income;
	}
	/**
	 * @param non_recurring_income The non_recurring_income to set.
	 */
	public void setNon_recurring_income(String non_recurring_income) {
		this.non_recurring_income = non_recurring_income;
	}
	/**
	 * @return Returns the other_expenditure.
	 */
	public String getOther_expenditure() {
		return other_expenditure;
	}
	/**
	 * @param other_expenditure The other_expenditure to set.
	 */
	public void setOther_expenditure(String other_expenditure) {
		this.other_expenditure = other_expenditure;
	}
	/**
	 * @return Returns the other_income.
	 */
	public String getOther_income() {
		return other_income;
	}
	/**
	 * @param other_income The other_income to set.
	 */
	public void setOther_income(String other_income) {
		this.other_income = other_income;
	}
	/**
	 * @return Returns the other_provisions.
	 */
	public String getOther_provisions() {
		return other_provisions;
	}
	/**
	 * @param other_provisions The other_provisions to set.
	 */
	public void setOther_provisions(String other_provisions) {
		this.other_provisions = other_provisions;
	}
	/**
	 * @return Returns the paid_up_equity_share_capital.
	 */
	public String getPaid_up_equity_share_capital() {
		return paid_up_equity_share_capital;
	}
	/**
	 * @param paid_up_equity_share_capital The paid_up_equity_share_capital to set.
	 */
	public void setPaid_up_equity_share_capital(
			String paid_up_equity_share_capital) {
		this.paid_up_equity_share_capital = paid_up_equity_share_capital;
	}
	/**
	 * @return Returns the profit_loss_before_tax.
	 */
	public String getProfit_loss_before_depriciation_tax_tax() {
		return profit_loss_before_depriciation_tax;
	}
	/**
	 * @param profit_loss_before_tax The profit_loss_before_tax to set.
	 */
	public void setProfit_loss_before_depriciation_tax(String profit_loss_before_depriciation_tax) {
		this.profit_loss_before_depriciation_tax=profit_loss_before_depriciation_tax;
	}
	/**
	 * @return Returns the provision_for_taxation.
	 */
	public String getProvision_for_taxation() {
		return provision_for_taxation;
	}
	/**
	 * @param provision_for_taxation The provision_for_taxation to set.
	 */
	public void setProvision_for_taxation(String provision_for_taxation) {
		this.provision_for_taxation = provision_for_taxation;
	}
	/**
	 * @return Returns the reserves_excluding_revaluation_reservers.
	 */
	public String getReserves_excluding_revaluation_reservers() {
		return reserves_excluding_revaluation_reservers;
	}
	/**
	 * @param reserves_excluding_revaluation_reservers The reserves_excluding_revaluation_reservers to set.
	 */
	public void setReserves_excluding_revaluation_reservers(
			String reserves_excluding_revaluation_reservers) {
		this.reserves_excluding_revaluation_reservers = reserves_excluding_revaluation_reservers;
	}
	/**
	 * @return Returns the result_type.
	 */
	public String getResult_type() {
		return result_type;
	}
	/**
	 * @param result_type The result_type to set.
	 */
	public void setResult_type(String result_type) {
		this.result_type = result_type;
	}
	/**
	 * @return Returns the staff.
	 */
	public String getStaff_cost() {
		return staff_cost;
	}
	/**
	 * @param staff The staff to set.
	 */
	public void setStaff_cost(String staff) {
		this.staff_cost = staff_cost;
	}
	/**
	 * @return Returns the to_date.
	 */
	public String getTo_date() {
		return to_date;
	}
	/**
	 * @param to_date The to_date to set.
	 */
	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}
	/**
	 * @return Returns the total_expenditure.
	 */
	public String getTotal_expenditure() {
		return total_expenditure;
	}
	/**
	 * @param total_expenditure The total_expenditure to set.
	 */
	public void setTotal_expenditure(String total_expenditure) {
		this.total_expenditure = total_expenditure;
	}
	/**
	 * @return Returns the total_expenditure_excluding_other_expenditure.
	 */
	public String getTotal_expenditure_excluding_other_expenditure() {
		return total_expenditure_excluding_other_expenditure;
	}
	/**
	 * @param total_expenditure_excluding_other_expenditure The total_expenditure_excluding_other_expenditure to set.
	 */
	public void setTotal_expenditure_excluding_other_expenditure(
			String total_expenditure_excluding_other_expenditure) {
		this.total_expenditure_excluding_other_expenditure = total_expenditure_excluding_other_expenditure;
	}
	/**
	 * @return Returns the series.
	 */
	public String getSeries() {
		 
		return series;
	}
	/**
	 * @param series The series to set.
	 */
	public void setSeries(String series) {
		this.series = series;
	}
	/**
	 * @return Returns the symbol.
	 */
	public String getSymbol() {
		return symbol;
	}
	/**
	 * @param symbol The symbol to set.
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	/**
	 * @return Returns the depreciation.
	 */
	public String getDepreciation() {
		return depreciation;
	}
	/**
	 * @param depreciation The depreciation to set.
	 */
	public void setDepreciation(String depreciation) {
		this.depreciation = depreciation;
	}
	/**
	 * @return Returns the profit_loss_before_tax.
	 */
	public String getProfit_loss_before_tax() {
		return Profit_loss_before_tax;
	}
	/**
	 * @param profit_loss_before_tax The profit_loss_before_tax to set.
	 */
	public void setProfit_loss_before_tax(String profit_loss_before_tax) {
		Profit_loss_before_tax = profit_loss_before_tax;
	}
	/**
	 * @return Returns the profit_loss_before_depriciation_tax.
	 */
	public String getProfit_loss_before_depriciation_tax() {
		return profit_loss_before_depriciation_tax;
	}
	/**
	 * @return Returns the is_Audited.
	 */
	public String getIs_Audited() {
		return is_Audited;
	}
	/**
	 * @param is_Audited The is_Audited to set.
	 */
	public void setIs_Audited(String is_Audited) {
		this.is_Audited = is_Audited;
	}
	/**
	 * @return Returns the is_Consolidated.
	 */
	public String getIs_Consolidated() {
		return is_Consolidated;
	}
	/**
	 * @param is_Consolidated The is_Consolidated to set.
	 */
	public void setIs_Consolidated(String is_Consolidated) {
		this.is_Consolidated = is_Consolidated;
	}
	/**
	 * @return Returns the is_Cumulative.
	 */
	public String getIs_Cumulative() {
		return is_Cumulative;
	}
	/**
	 * @param is_Cumulative The is_Cumulative to set.
	 */
	public void setIs_Cumulative(String is_Cumulative) {
		this.is_Cumulative = is_Cumulative;
	}
	/**
	 * @return Returns the net_worth.
	 */
	public String getNet_worth() {
		return net_worth;
	}
	/**
	 * @param net_worth The net_worth to set.
	 */
	public void setNet_worth(String net_worth) {
		this.net_worth = net_worth;
	}
}
