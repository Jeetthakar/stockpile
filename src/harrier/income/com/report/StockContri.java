package harrier.income.com.report;

public class StockContri
{

	private String stockname;
	private String indexmarket;
	private String stockmarket;
	private String weightage;
	
	public StockContri(String stockname,String indexmarket,String stockmarket,String weightage)
	
	{
		
		this.stockname=stockname;
		this.indexmarket=indexmarket;
		this.stockmarket=stockmarket;
		this.weightage=weightage;
		
	}
	/**
	 * @return Returns the indexmarket.
	 */
	public String getIndexmarket() {
		return indexmarket;
	}
	/**
	 * @param indexmarket The indexmarket to set.
	 */
	public void setIndexmarket(String indexmarket) {
		this.indexmarket = indexmarket;
	}
	/**
	 * @return Returns the stockmarket.
	 */
	public String getStockmarket() {
		return stockmarket;
	}
	/**
	 * @param stockmarket The stockmarket to set.
	 */
	public void setStockmarket(String stockmarket) {
		this.stockmarket = stockmarket;
	}
	/**
	 * @return Returns the stockname.
	 */
	public String getStockname() {
		return stockname;
	}
	/**
	 * @param stockname The stockname to set.
	 */
	public void setStockname(String stockname) {
		this.stockname = stockname;
	}
	/**
	 * @return Returns the weightage.
	 */
	public String getWeightage() {
		return weightage;
	}
	/**
	 * @param weightage The weightage to set.
	 */
	public void setWeightage(String weightage) {
		this.weightage = weightage;
	}
}
	