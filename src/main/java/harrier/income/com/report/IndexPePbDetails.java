/*
 * Created on Feb 24, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;

/**
 * @author sonali
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IndexPePbDetails {
String training_date=null;
String close=null;
String change=null;
String mcap=null;
String shares_traded=null;
String turnover=null;
String peratio=null;
String pbratio=null;
String dividend=null;

	/**
	 * @param training_date
	 * @param close
	 * @param change
	 * @param mcap
	 * @param shares_traded
	 * @param turnover
	 * @param peratio
	 * @param pbratio
	 * @param dividend
	 */
	public IndexPePbDetails(String training_date, String close, String change,
			String mcap, String shares_traded, String turnover, String peratio,
			String pbratio, String dividend) {
		this.training_date = training_date;
		this.close = close;
		this.change = change;
		this.mcap = mcap;
		this.shares_traded = shares_traded;
		this.turnover = turnover;
		this.peratio = peratio;
		this.pbratio = pbratio;
		this.dividend = dividend;
	}
//constructor



	
/**
 * @return Returns the change.
 */
public String getChange() {
	return change;
}
/**
 * @param change The change to set.
 */
public void setChange(String change) {
	this.change = change;
}
/**
 * @return Returns the close.
 */
public String getClose() {
	return close;
}
/**
 * @param close The close to set.
 */
public void setClose(String close) {
	this.close = close;
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
 * @return Returns the mcap.
 */
public String getMcap() {
	return mcap;
}
/**
 * @param mcap The mcap to set.
 */
public void setMcap(String mcap) {
	this.mcap = mcap;
}
/**
 * @return Returns the pbratio.
 */
public String getPbratio() {
	return pbratio;
}
/**
 * @param pbratio The pbratio to set.
 */
public void setPbratio(String pbratio) {
	this.pbratio = pbratio;
}
/**
 * @return Returns the peratio.
 */
public String getPeratio() {
	return peratio;
}
/**
 * @param peratio The peratio to set.
 */
public void setPeratio(String peratio) {
	this.peratio = peratio;
}
/**
 * @return Returns the shares_traded.
 */
public String getShares_traded() {
	return shares_traded;
}
/**
 * @param shares_traded The shares_traded to set.
 */
public void setShares_traded(String shares_traded) {
	this.shares_traded = shares_traded;
}
/**
 * @return Returns the training_date.
 */
public String getTraining_date() {
	return training_date;
}
/**
 * @param training_date The training_date to set.
 */
public void setTraining_date(String training_date) {
	this.training_date = training_date;
}
/**
 * @return Returns the turnover.
 */
public String getTurnover() {
	return turnover;
}
/**
 * @param turnover The turnover to set.
 */
public void setTurnover(String turnover) {
	this.turnover = turnover;
}
}
