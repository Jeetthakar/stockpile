package harrier.income.com.report;

import org.apache.log4j.Logger;

public class StockDetails {
	Logger Logging = Logger.getLogger(StockDetails.class);
	private String stockId;
	private String stockName;
	private String openVal;
	private String closeVal;
	private String lowVal;
	private String highVal;
	private String tradedVal;
	private String date;
	private String mcv;
	private String tradedVol;
	private String noOfTrades;
	private String faceVal;
	private String tis;
	private String iwf;
	private String corpAction;
	private String amount;
	private String stockName1;
	private String applyDate;
	private String camName;
	private String status;
	private String caType;
	private String rank;
	private String tradedVolume;
	
	
	/**
	 * No arguments Constructor
	 */
	StockDetails() {
Logging.debug(" Inside noo args Constructor of StockDetails ***************");	
	}
	//StockDetails(stk_id, stk_name,face, tis, mcv, amt, date );
	/**
	 * Constructor for Stock Divident report
	 */
	StockDetails(String stockId, String  stockName, String faceVal, String tis, String mcv, String amount, String date, int type){
		this.stockId = stockId;
		this.stockName = stockName;
		this.faceVal = faceVal;
		this.tis = tis;
		this.mcv = mcv;
		this.amount = amount;
		this.date = date;
	}
	
	/**
	 * 
	 * Constructor for Traded Volume Ind/Exch wise
	 */
	/*StockDetails(String stockId, String  stockName, String tradedVol){
		this.stockId = stockId;
		this.stockName = stockName;
		this.tradedVol = tradedVol;
	}*/
	
	/**
	 * Constructor for Stock List Report
	 */
	StockDetails(String stockId, String stockName, String tis, String close, String mcv, 
			String faceVal, String date){
		//app.Logging.getDebug(" Inside StockDetails constructor for stockLIst");
		this.stockId = stockId;
		this.stockName = stockName;
		this.tis = tis;
		this.closeVal = close;
		this.mcv = mcv;
		this.faceVal = faceVal;
		this.date = date;
		
	}
	
	/**
	 * Constructor for Capital Change
	 */
	StockDetails(String stockId, String stockName, String faceVal, String tis, String mcv, 
				String iwf, String corpAction, String date) {
		//app.Logging.getDebug(" Inside constructor for capitalChange.");
		this.stockId = stockId;
		this.stockName = stockName;
		this.faceVal = faceVal;
		this.tis = tis;
		this.mcv =mcv;
		this.iwf = iwf;
		this.corpAction = corpAction;
		this.date = date;
		//app.Logging.getDebug(" outside constructor");
	}
	
	/**
	 * @return Returns the corpAction.
	 */
	public String getCorpAction() {
		return corpAction;
	}
	/**
	 * @param corpAction The corpAction to set.
	 */
	public void setCorpAction(String corpAction) {
		this.corpAction = corpAction;
	}
	/**
	 * @return Returns the faceVal.
	 */
	public String getFaceVal() {
		return faceVal;
	}
	/**
	 * @param faceVal The faceVal to set.
	 */
	public void setFaceVal(String faceVal) {
		this.faceVal = faceVal;
	}
	/**
	 * @return Returns the iwf.
	 */
	public String getIwf() {
		return iwf;
	}
	/**
	 * @param iwf The iwf to set.
	 */
	public void setIwf(String iwf) {
		this.iwf = iwf;
	}
	/**
	 * @return Returns the tis.
	 */
	public String getTis() {
		return tis;
	}
	/**
	 * @param tis The tis to set.
	 */
	public void setTis(String tis) {
		this.tis = tis;
	}
	/**
	 * Constructor Stock details report
	 * @param name
	 * @param open
	 * @param close
	 * @param low
	 * @param high
	 * @param tradedVal
	 * @param date
	 * @param mCap
	 * @param tradedVol
	 * @param noOfTrades
	 */
	StockDetails(String name,String open,String close, String low, String high, 
			String tradedVal, String date, String mCap, String tradedVol, 
			String noOfTrades){
		this.stockName = name;
		this.openVal = open;
		this.closeVal = close;
		this.lowVal = low;
		this.highVal = high;
		this.tradedVal = tradedVal;
		this.date = date;
		this.mcv = mCap;
		this.tradedVol = tradedVol;
		this.noOfTrades = noOfTrades;
	}
	
	/**
	 * @return Returns the closeVal.
	 */
	public String getCloseVal() {
		return closeVal;
	}
	/**
	 * @param closeVal The closeVal to set.
	 */
	public void setCloseVal(String closeVal) {
		this.closeVal = closeVal;
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
	 * @return Returns the highVal.
	 */
	public String getHighVal() {
		return highVal;
	}
	/**
	 * @param highVal The highVal to set.
	 */
	public void setHighVal(String highVal) {
		this.highVal = highVal;
	}
	/**
	 * @return Returns the lowVal.
	 */
	public String getLowVal() {
		return lowVal;
	}
	/**
	 * @param lowVal The lowVal to set.
	 */
	public void setLowVal(String lowVal) {
		this.lowVal = lowVal;
	}
	/**
	 * @return Returns the mCap.
	 */
	public String getMcv() {
		return mcv;
	}
	/**
	 * @param cap The mCap to set.
	 */
	public void setMcv(String mcv) {
		this.mcv = mcv;
	}
	/**
	 * @return Returns the noOfTrades.
	 */
	public String getNoOfTrades() {
		return noOfTrades;
	}
	/**
	 * @param noOfTrades The noOfTrades to set.
	 */
	public void setNoOfTrades(String noOfTrades) {
		this.noOfTrades = noOfTrades;
	}
	/**
	 * @return Returns the openVal.
	 */
	public String getOpenVal() {
		return openVal;
	}
	/**
	 * @param openVal The openVal to set.
	 */
	public void setOpenVal(String openVal) {
		this.openVal = openVal;
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
	 * @return Returns the tradedVal.
	 */
	public String getTradedVal() {
		return tradedVal;
	}
	/**
	 * @param tradedVal The tradedVal to set.
	 */
	public void setTradedVal(String tradedVal) {
		this.tradedVal = tradedVal;
	}
	/**
	 * @return Returns the tradedVol.
	 */
	public String getTradedVol() {
		return tradedVol;
	}
	/**
	 * @param tradedVol The tradedVol to set.
	 */
	public void setTradedVol(String tradedVol) {
		this.tradedVol = tradedVol;
	}

	/**
	 * @return Returns the amount.
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount The amount to set.
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * Constructor Stock details report
	 * @param name
	 * @param open
	 * @param close
	 * @param low
	 * @param high
	 * @param tradedVal
	 * @param date
	 * @param mCap
	 * @param tradedVol
	 * @param noOfTrades
	 */
	StockDetails(String name,String camname,String caType, String applyDate, String status ){
		this.stockName1 = name;
		this.camName = camname;
		this.caType = caType;
		this.applyDate = applyDate;
		this.status = status;
		
	}
	
	/**
	 * @return Returns the closeVal.
	 */
	public String getStockName1() {
		return stockName1;
	}
	/**
	 * @param closeVal The closeVal to set.
	 */
	public void setStockName1(String stockName1) {
		this.stockName1 = stockName1;
	}
	/**
	 * @return Returns the date.
	 */
	public String getCamName() {
		return camName;
	}
	/**
	 * @param date The date to set.
	 */
	public void setCamName(String camName) {
		this.camName = camName;
	}
	/**
	 * @return Returns the highVal.
	 */
	public String getCaType() {
		return caType;
	}
	/**
	 * @param highVal The highVal to set.
	 */
	public void setCaType(String caType) {
		this.caType = caType;
	}
	/**
	 * @return Returns the lowVal.
	 */
	public String getApplyDate() {
		return applyDate;
	}
	/**
	 * @param lowVal The lowVal to set.
	 */
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	/**
	 * @return Returns the mCap.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param cap The mCap to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	StockDetails(String rank,String name,String trdVol)
	{
		this.stockName=name;
		this.tradedVol=trdVol;
		this.rank=rank;
	}
	/**
	 * @param closeVal The closeVal to set.
	 */
	
	/**
	 * @param closeVal The closeVal to set.
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getRank() {
		return rank;
	}
	
}

