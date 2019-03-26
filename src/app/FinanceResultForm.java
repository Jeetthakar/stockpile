/*
 * Created on Mar 13, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.util.LabelValueBean;

import com.harrier.initializeation.ConnectInit;

/**
 * @author pankajb
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class FinanceResultForm extends ActionForm {
	Logger Logging = Logger.getLogger(FinanceResultForm.class);
	String s_companyName, months, currency, frmdate, todate, units, netSales,
			provisionTax, otherIncome, fringBefTax, nonRecurringIncome,
			defferedTaxExpenses, grossIncome, profitAftTax, incDecStock,
			rawMaterial, empCost, otherExpenditure, nonRecurringExpenditure,
			paidupShareCapital, reserveExcludingreval, eps, dividend,
			totalDebt, netWorth, interest, profitBeforeDepreciation,
			depriciation, profitBeforeTax, numberOfShares,
			percentageOfShareHolding, bookValue, start_date, to_date,
			operation, check_flag, b1;

	boolean audited = false;
	boolean cumulative = false;
	boolean consolidated = false;

	private ArrayList alllist = new ArrayList();
	private Collection companyListCollection = null;
	private Collection monthListCollection = null;
	private Collection currencyListCollection = null;
	private Collection unitListCollection = null;

	public boolean bodycheck(HttpServletRequest request) {
		boolean bodychk = false;
		String prs = b1;
		String from = request.getParameter("from");
		Logging.debug("&&&&& From " + from);
		Logging.debug("&&&&& From " + from);
		if (from != null && from.equals("menu")) {
			reset_form();
			bodychk = true;
		}

		return bodychk;
	}

	public void reset_form() {
		s_companyName = "0";
		months = "0";
		currency = "0";
		units = "";
		netSales = "";
		provisionTax = "";
		otherIncome = "";
		fringBefTax = "";
		nonRecurringIncome = "";
		defferedTaxExpenses = "";
		grossIncome = "";
		profitAftTax = "";
		incDecStock = "";
		rawMaterial = "";
		empCost = "";
		otherExpenditure = "";
		nonRecurringExpenditure = "";
		paidupShareCapital = "";
		reserveExcludingreval = "";
		eps = "";
		dividend = "";
		totalDebt = "";
		netWorth = "";
		interest = "";
		profitBeforeDepreciation = "";
		depriciation = "";
		profitBeforeTax = "";
		numberOfShares = "";
		percentageOfShareHolding = "";
		bookValue = "";
		start_date = "";
		to_date = "";
		check_flag = "";
		audited = false;
		cumulative = false;
		consolidated = false;

	} // End reset_form().......

	/**
	 * @return Returns the audited.
	 */
	public boolean isAudited() {
		return audited;
	}

	/**
	 * @param audited
	 *            The audited to set.
	 */
	public void setAudited(boolean audited) {
		this.audited = audited;
	}

	/**
	 * @return Returns the bookValue.
	 */
	public String getBookValue() {
		return bookValue;
	}

	/**
	 * @param bookValue
	 *            The bookValue to set.
	 */
	public void setBookValue(String bookValue) {
		this.bookValue = bookValue;
	}

	/**
	 * @return Returns the consolidated.
	 */
	public boolean isConsolidated() {
		return consolidated;
	}

	/**
	 * @param consolidated
	 *            The consolidated to set.
	 */
	public void setConsolidated(boolean consolidated) {
		this.consolidated = consolidated;
	}

	/**
	 * @return Returns the cumulative.
	 */
	public boolean isCumulative() {
		return cumulative;
	}

	/**
	 * @param cumulative
	 *            The cumulative to set.
	 */
	public void setCumulative(boolean cumulative) {
		this.cumulative = cumulative;
	}

	/**
	 * @return Returns the currency.
	 */
	public String getCurrency() {
		if (currency == null || currency.equals("")) {
			currency = "0";
		} else {
			currency = currency.replaceAll("'", "\'");
		}
		return currency;
	}

	/**
	 * @param currency
	 *            The currency to set.
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return Returns the defferedTaxExpenses.
	 */
	public String getDefferedTaxExpenses() {
		return defferedTaxExpenses;
	}

	/**
	 * @param defferedTaxExpenses
	 *            The defferedTaxExpenses to set.
	 */
	public void setDefferedTaxExpenses(String defferedTaxExpenses) {
		this.defferedTaxExpenses = defferedTaxExpenses;
	}

	/**
	 * @return Returns the depriciation.
	 */
	public String getDepriciation() {
		return depriciation;
	}

	/**
	 * @param depriciation
	 *            The depriciation to set.
	 */
	public void setDepriciation(String depriciation) {
		this.depriciation = depriciation;
	}

	/**
	 * @return Returns the dividend.
	 */
	public String getDividend() {
		return dividend;
	}

	/**
	 * @param dividend
	 *            The dividend to set.
	 */
	public void setDividend(String dividend) {
		this.dividend = dividend;
	}

	/**
	 * @return Returns the empCost.
	 */
	public String getEmpCost() {
		return empCost;
	}

	/**
	 * @param empCost
	 *            The empCost to set.
	 */
	public void setEmpCost(String empCost) {
		this.empCost = empCost;
	}

	/**
	 * @return Returns the eps.
	 */
	public String getEps() {
		return eps;
	}

	/**
	 * @param eps
	 *            The eps to set.
	 */
	public void setEps(String eps) {
		this.eps = eps;
	}

	/**
	 * @return Returns the fringBefTax.
	 */
	public String getFringBefTax() {
		return fringBefTax;
	}

	/**
	 * @param fringBefTax
	 *            The fringBefTax to set.
	 */
	public void setFringBefTax(String fringBefTax) {
		this.fringBefTax = fringBefTax;
	}

	/**
	 * @return Returns the frmdate.
	 */
	public String getFrmdate() {
		return frmdate;
	}

	/**
	 * @param frmdate
	 *            The frmdate to set.
	 */
	public void setFrmdate(String frmdate) {
		this.frmdate = frmdate;
	}

	/**
	 * @return Returns the grossIncome.
	 */
	public String getGrossIncome() {
		Logging.debug("GET Gross " + grossIncome);
		return grossIncome;
	}

	/**
	 * @param grossIncome
	 *            The grossIncome to set.
	 */
	public void setGrossIncome(String grossIncome) {
		this.grossIncome = grossIncome;
		Logging.debug("SET Gross " + grossIncome);
	}

	/**
	 * @return Returns the incDecStock.
	 */
	public String getIncDecStock() {
		return incDecStock;
	}

	/**
	 * @param incDecStock
	 *            The incDecStock to set.
	 */
	public void setIncDecStock(String incDecStock) {
		this.incDecStock = incDecStock;
	}

	/**
	 * @return Returns the interest.
	 */
	public String getInterest() {
		return interest;
	}

	/**
	 * @param interest
	 *            The interest to set.
	 */
	public void setInterest(String interest) {
		this.interest = interest;
	}

	/**
	 * @return Returns the months.
	 */
	public String getMonths() {
		return months;
	}

	/**
	 * @param months
	 *            The months to set.
	 */
	public void setMonths(String months) {
		this.months = months;
	}

	/**
	 * @return Returns the netSales.
	 */
	public String getNetSales() {
		return netSales;
	}

	/**
	 * @param netSales
	 *            The netSales to set.
	 */
	public void setNetSales(String netSales) {
		this.netSales = netSales;
	}

	/**
	 * @return Returns the netWorth.
	 */
	public String getNetWorth() {
		return netWorth;
	}

	/**
	 * @param netWorth
	 *            The netWorth to set.
	 */
	public void setNetWorth(String netWorth) {
		this.netWorth = netWorth;
	}

	/**
	 * @return Returns the nonRecurringExpenditure.
	 */
	public String getNonRecurringExpenditure() {
		return nonRecurringExpenditure;
	}

	/**
	 * @param nonRecurringExpenditure
	 *            The nonRecurringExpenditure to set.
	 */
	public void setNonRecurringExpenditure(String nonRecurringExpenditure) {
		this.nonRecurringExpenditure = nonRecurringExpenditure;
	}

	/**
	 * @return Returns the nonRecurringIncome.
	 */
	public String getNonRecurringIncome() {
		return nonRecurringIncome;
	}

	/**
	 * @param nonRecurringIncome
	 *            The nonRecurringIncome to set.
	 */
	public void setNonRecurringIncome(String nonRecurringIncome) {
		this.nonRecurringIncome = nonRecurringIncome;
	}

	/**
	 * @return Returns the numberOfShares.
	 */
	public String getNumberOfShares() {
		return numberOfShares;
	}

	/**
	 * @param numberOfShares
	 *            The numberOfShares to set.
	 */
	public void setNumberOfShares(String numberOfShares) {
		this.numberOfShares = numberOfShares;
	}

	/**
	 * @return Returns the otherExpenditure.
	 */
	public String getOtherExpenditure() {
		return otherExpenditure;
	}

	/**
	 * @param otherExpenditure
	 *            The otherExpenditure to set.
	 */
	public void setOtherExpenditure(String otherExpenditure) {
		this.otherExpenditure = otherExpenditure;
	}

	/**
	 * @return Returns the otherIncome.
	 */
	public String getOtherIncome() {
		return otherIncome;
	}

	/**
	 * @param otherIncome
	 *            The otherIncome to set.
	 */
	public void setOtherIncome(String otherIncome) {
		this.otherIncome = otherIncome;
	}

	/**
	 * @return Returns the paidupShareCapital.
	 */
	public String getPaidupShareCapital() {
		return paidupShareCapital;
	}

	/**
	 * @param paidupoShareCapital
	 *            The paidupShareCapital to set.
	 */
	public void setPaidupShareCapital(String paidupShareCapital) {
		this.paidupShareCapital = paidupShareCapital;
	}

	/**
	 * @return Returns the percentageOfShareHolding.
	 */
	public String getPercentageOfShareHolding() {
		return percentageOfShareHolding;
	}

	/**
	 * @param percentageOfShareHolding
	 *            The percentageOfShareHolding to set.
	 */
	public void setPercentageOfShareHolding(String percentageOfShareHolding) {
		this.percentageOfShareHolding = percentageOfShareHolding;
	}

	/**
	 * @return Returns the profitAftTax.
	 */
	public String getProfitAftTax() {
		return profitAftTax;
	}

	/**
	 * @param profitAftTax
	 *            The profitAftTax to set.
	 */
	public void setProfitAftTax(String profitAftTax) {
		this.profitAftTax = profitAftTax;
	}

	/**
	 * @return Returns the profitBeforeDepreciation.
	 */
	public String getProfitBeforeDepreciation() {
		return profitBeforeDepreciation;
	}

	/**
	 * @param profitBeforeDepreciation
	 *            The profitBeforeDepreciation to set.
	 */
	public void setProfitBeforeDepreciation(String profitBeforeDepreciation) {
		this.profitBeforeDepreciation = profitBeforeDepreciation;
	}

	/**
	 * @return Returns the profitBeforeTax.
	 */
	public String getProfitBeforeTax() {
		return profitBeforeTax;
	}

	/**
	 * @param profitBeforeTax
	 *            The profitBeforeTax to set.
	 */
	public void setProfitBeforeTax(String profitBeforeTax) {
		this.profitBeforeTax = profitBeforeTax;
	}

	/**
	 * @return Returns the provisionTax.
	 */
	public String getProvisionTax() {
		return provisionTax;
	}

	/**
	 * @param provisionTax
	 *            The provisionTax to set.
	 */
	public void setProvisionTax(String provisionTax) {
		this.provisionTax = provisionTax;
	}

	/**
	 * @return Returns the rawMaterial.
	 */
	public String getRawMaterial() {
		return rawMaterial;
	}

	/**
	 * @param rawMaterial
	 *            The rawMaterial to set.
	 */
	public void setRawMaterial(String rawMaterial) {
		this.rawMaterial = rawMaterial;
	}

	/**
	 * @return Returns the s_companyName.
	 */
	public String getS_companyName() {
		if (s_companyName == null || s_companyName.equals("")) {
			s_companyName = "0";
		} else {
			s_companyName = s_companyName.replaceAll("'", "\'");
		}
		return s_companyName;
	}

	/**
	 * @param name
	 *            The s_companyName to set.
	 */
	public void setS_companyName(String name) {
		s_companyName = name;
	}

	/**
	 * @return Returns the todate.
	 */
	public String getTodate() {
		return todate;
	}

	/**
	 * @param todate
	 *            The todate to set.
	 */
	public void setTodate(String todate) {
		this.todate = todate;
	}

	/**
	 * @return Returns the totalDebt.
	 */
	public String getTotalDebt() {
		return totalDebt;
	}

	/**
	 * @param totalDebt
	 *            The totalDebt to set.
	 */
	public void setTotalDebt(String totalDebt) {
		this.totalDebt = totalDebt;
	}

	/**
	 * @return Returns the units.
	 */
	public String getUnits() {
		return units;
	}

	/**
	 * @param units
	 *            The units to set.
	 */
	public void setUnits(String units) {
		this.units = units;
	}

	/**
	 * @return Returns the start_date.
	 */
	public String getStart_date() {
		return start_date;
	}

	/**
	 * @param start_date
	 *            The start_date to set.
	 */
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	/**
	 * @return Returns the to_date.
	 */
	public String getTo_date() {
		return to_date;
	}

	/**
	 * @param to_date
	 *            The to_date to set.
	 */
	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}

	/**
	 * @return Returns the companyListCollection.(company list)
	 */
	public Collection getCompanyListCollection() {
		try {
			Vector companyList = new Vector();
			ResultSet rst = null;
			// app.Connect con=new app.Connect();
			Connect con = ConnectInit.getConnect();
			Connection connection = null;
			/*
			 * if(Connect.con==null) { con.getConnection(); }
			 */
			// AcessControl asc=new AcessControl();
			AcessControl asc = ConnectInit.getAcessControl();
			// String
			// SelCompany=asc.getLangValues("classCompany.selectCompany");
			String SelCompany = "Select Company";
			if (companyListCollection == null) {
				try {
					if (connection == null) {
						connection = con.getdbConnection();
					}
					rst = StockResult.getCompanyList(connection);
					Logging.debug(" in company collection " + rst);
					companyList.add(new LabelValueBean(SelCompany, "0"));
					while (rst.next()) {
						String count = rst.getString(1);
						companyList.add(new LabelValueBean(rst.getString(2),
								count));
					}
					rst.close();
				} catch (SQLException e) {
					Logging.error("Error  :" + e.getMessage());
				//	e.printStackTrace();
				} finally {
					try {
						if (connection != null)
							connection.close();
					} catch (Exception ee) {
						Logging.error(" Error : Unable to close Connection "
								+ ee.getMessage());
					}
				} // Finally method added on 25 AUG 06
				companyListCollection = companyList;
			}
		} catch (Exception e) {
			Logging.error(e.getMessage());
		}
		return companyListCollection;
	} // End getCompanyListCollection().....

	/**
	 * @return Returns the monthListCollection.(company list)
	 */

	public Collection getMonthListCollection() {
		try {
			Vector monthList = new Vector();

			String SelMonth = "Select Month";

			if (monthListCollection == null) {

				Logging.debug(" in months collection ");
				monthList.add(new LabelValueBean(SelMonth, "0"));
				monthList.add(new LabelValueBean("3", "3"));
				monthList.add(new LabelValueBean("6", "6"));
				monthList.add(new LabelValueBean("9", "9"));
				monthList.add(new LabelValueBean("12", "12"));
				monthList.add(new LabelValueBean("15", "15"));
				monthList.add(new LabelValueBean("18", "18"));
				monthList.add(new LabelValueBean("24", "24"));

				monthListCollection = monthList;
			}
		} catch (Exception e) {
			Logging.debug("Error in Month Collectiion " + e.getMessage());
		}
		return monthListCollection;
	} // End getCompanyListCollection().....

	/**
	 * @return Returns the currencyListCollection.(currency List)
	 */
	public Collection getCurrencyListCollection() {
		Vector stkExcList = new Vector();
		Connection connection = null;
		ResultSet rst = null;
		// app.Connect con=new app.Connect();
		Connect con = ConnectInit.getConnect();
		// if(Connect.con==null)
		// con.getConnection();
		// AcessControl asc=new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		// String SelCurrency=asc.getLangValues("Masters.SelectCurrency");
		String SelCurrency = "Select Currency";
		if (currencyListCollection == null) {
			try {
				if (connection == null) {
					connection = con.getdbConnection();
				}
				rst = StockResult.getCurrencyList(connection);
				Logging.debug(" in stock Exchange collection " + rst);
				stkExcList.add(new LabelValueBean(SelCurrency, "0"));
				while (rst.next()) {
					String count = rst.getString(1);
					stkExcList.add(new LabelValueBean(rst.getString(2), count));
				}
				rst.close();
			} catch (SQLException e) {
				Logging.error("Error  :" + e.getMessage());
			//	e.printStackTrace();
			} finally {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ee) {
					Logging.error(" Error : Unable to close Connection "
							+ ee.getMessage());
				}
			}
			currencyListCollection = stkExcList;
		}
		return currencyListCollection;

	} // End getCurrencyListCollection().......

	public Collection getUnitListCollection() {

		Vector unitList = new Vector();

		String SelMonth = "Select Unit";

		if (unitListCollection == null) {

			Logging.debug(" in months collection ");
			unitList.add(new LabelValueBean(SelMonth, "0"));
			// unitList.add(new LabelValueBean("lacs","lacs"));
			unitList.add(new LabelValueBean("million", "million"));
			// unitList.add(new LabelValueBean("crores","crores"));
			unitList.add(new LabelValueBean("billion", "billion"));

			unitListCollection = unitList;
		}

		return unitListCollection;
	} // End getCompanyListCollection().....

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

	/**
	 * @return Returns the reserveExcludingreval.
	 */
	public String getReserveExcludingreval() {
		return reserveExcludingreval;
	}

	/**
	 * @param reserveExcludingreval
	 *            The reserveExcludingreval to set.
	 */
	public void setReserveExcludingreval(String reserveExcludingreval) {
		this.reserveExcludingreval = reserveExcludingreval;
	}

	/**
	 * @return Returns the check_flag.
	 */
	public String getCheck_flag() {
		return check_flag;
	}

	/**
	 * @param check_flag
	 *            The check_flag to set.
	 */
	public void setCheck_flag(String check_flag) {
		this.check_flag = check_flag;
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
} // End FinanceResultForm
