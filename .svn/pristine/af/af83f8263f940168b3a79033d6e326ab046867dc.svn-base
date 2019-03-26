/*
 * Created on Mar 9, 2006
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
public class IndexCalculatorDetails {
	private static int serialNo=0;
	String stockId=null;
	String symbolName=null;
	String lastTradedPrice=null;
	int srNo = 0;
	String lastTradedPriceNew=null;
	
	/**
	 * @param stockId
	 * @param symbolName
	 * @param lastTradedPrice
	 */
	public IndexCalculatorDetails(String stockId, String symbolName,
			String lastTradedPrice) {
		super();
		this.stockId = stockId;
		this.symbolName = symbolName;
		this.lastTradedPrice = lastTradedPrice;
	}
	
	public IndexCalculatorDetails(String stockId, String symbolName,
			String lastTradedPrice,String lastTradedPriceNew) {
		super();
		this.stockId = stockId;
		this.symbolName = symbolName;
		this.lastTradedPrice = lastTradedPrice;
		this.lastTradedPriceNew=lastTradedPriceNew;
	}
	public IndexCalculatorDetails( int srNo, String stockId, String symbolName,
			String lastTradedPrice) {
			super();
			this.srNo = srNo;
			this.stockId = stockId;
			this.symbolName = symbolName;
			this.lastTradedPrice = lastTradedPrice;
			}
	/**
	 * @return Returns the lastTradedPrice.
	 */
	public String getLastTradedPrice() {
		return lastTradedPrice;
	}
	/**
	 * @param lastTradedPrice The lastTradedPrice to set.
	 */
	public void setLastTradedPrice(String lastTradedPrice) {
		this.lastTradedPrice = lastTradedPrice;
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
	 * @return Returns the symbolName.
	 */
	public String getSymbolName() {
		return symbolName;
	}
	/**
	 * @param symbolName The symbolName to set.
	 */
	public void setSymbolName(String symbolName) {
		this.symbolName = symbolName;
	}
	/**
	 * @return Returns the srno.
	 */
	/**
	 * @return Returns the serialNo.
	 */
	public int getSerialNo() {
		try{
			serialNo=serialNo+1;
			
		}catch(Exception e){
			
		}
		return serialNo;
	}
	/**
	 * @param serialNo The serialNo to set.
	 */
	public static void setSerialNo(int serialNo) {
		IndexCalculatorDetails.serialNo = serialNo;
	}
	public int getSrNo() {
		return srNo;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}

	public String getLastTradedPriceNew() {
		return lastTradedPriceNew;
	}

	public void setLastTradedPriceNew(String lastTradedPriceNew) {
		this.lastTradedPriceNew = lastTradedPriceNew;
	}
	
}