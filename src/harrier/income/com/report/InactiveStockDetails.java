/*
 * Created on Mar 22, 2006
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
public class InactiveStockDetails {

private String stockId=null;	
private String stockName=null;
private String outstandingShares=null;
private String faceValue=null;
private String date=null;


	/**
	 * @param stockId
	 * @param stockName
	 * @param outstandingShares
	 * @param faceValue
	 * @param date
	 */
	public InactiveStockDetails(String stockId, String stockName,
			String outstandingShares, String faceValue, String date) {
		super();
		this.stockId = stockId;
		this.stockName = stockName;
		this.outstandingShares = outstandingShares;
		this.faceValue = faceValue;
		this.date = date;
	}
/**
 * @return Returns the date.
 */
public String getDate() {
	return date;
}
/**
 * @param date The date to set.
 */
public void setDate(String date) {
	this.date = date;
}
/**
 * @return Returns the faceValue.
 */
public String getFaceValue() {
	return faceValue;
}
/**
 * @param faceValue The faceValue to set.
 */
public void setFaceValue(String faceValue) {
	this.faceValue = faceValue;
}
/**
 * @return Returns the outstandingShares.
 */
public String getOutstandingShares() {
	return outstandingShares;
}
/**
 * @param outstandingShares The outstandingShares to set.
 */
public void setOutstandingShares(String outstandingShares) {
	this.outstandingShares = outstandingShares;
}
/**
 * @return Returns the stockName.
 */
public String getStockName() {
	return stockName;
}
/**
 * @param stockName The stockName to set.
 */
public void setStockName(String stockName) {
	this.stockName = stockName;
}
/**
 * @return Returns the stockId.
 */
public String getStockId() {
	return stockId;
}
/**
 * @param stockId The stockId to set.
 */
public void setStockId(String stockId) {
	this.stockId = stockId;
}
}
