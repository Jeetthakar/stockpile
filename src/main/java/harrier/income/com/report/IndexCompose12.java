package harrier.income.com.report;

public class IndexCompose12
{
	private String stockid;
	private String stockname1;
	private String currency;
	private String tis;
	private String iwf;
	private String adjusted;
	private String mcv_new;
	private String mcv;
	
	private String stockprice;
	private String market;
	private String last;
	private String curr_exch_convIratecomp;
	private String strweightage;
	private String adjustedmarketcap;;

	
	public IndexCompose12(String stockid,String stockname1,String tis,String iwf,String market,String adjusted,String last,String currency,String curr_exch_convIratecomp,String mcv,String adjustedmarketcap,String strweightage,String stockprice)
	{
		this.stockid=stockid;
		this.stockname1=stockname1;
		this.currency=currency;
		this.tis=tis;
		this.iwf=iwf;
		this.adjusted=adjusted;
		this.mcv=mcv;
		this.stockprice=stockprice;
		this.market=market;
		this.last=last;
		this.curr_exch_convIratecomp=curr_exch_convIratecomp;
		this.strweightage=strweightage;		
		this.adjustedmarketcap = adjustedmarketcap;
		
	}
	
	
	/**
	 * 
	 */
	
	public IndexCompose12(String stockid,String stockname1,String currency,String tis,String iwf,String adjusted,String mcv,String stockprice,String market,String last,String curr_exch_convIratecomp,String strweightage)
	{
		this.stockid=stockid;
		this.stockname1=stockname1;
		this.currency=currency;
		this.tis=tis;
		this.iwf=iwf;
		this.adjusted=adjusted;
		this.mcv=mcv;
		this.stockprice=stockprice;
		this.market=market;
		this.last=last;
		this.curr_exch_convIratecomp=curr_exch_convIratecomp;
		this.strweightage=strweightage;		
	}	
	
	
	
	/**
	 * @return Returns the date.
	 */	
	public String getAdjustedmarketcap() {
		return adjustedmarketcap;
	}
	public void setDate(String adjustedmarketcap) {
		this.adjustedmarketcap = adjustedmarketcap;
	}
	/**
	 * @return Returns the adjusted.
	 */
	public String getAdjusted() {
		return adjusted;
	}
	/**
	 * @param adjusted The adjusted to set.
	 */
	public void setAdjusted(String adjusted) {
		this.adjusted = adjusted;
	}
	/**
	 * @return Returns the currency.
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency The currency to set.
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
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
	 * @return Returns the last.
	 */
	public String getLast() {
		return last;
	}
	/**
	 * @param last The last to set.
	 */
	public void setLast(String last) {
		this.last = last;
	}
	/**
	 * @return Returns the mcv.
	 */
	public String getMcv() {
		return mcv;
	}
	/**
	 * @param mcv The mcv to set.
	 */
	public void setMcv(String mcv) {
		this.mcv = mcv;
	}
	public String getMarket() {
		return market;
	}
	/**
	 * @param adjusted The adjusted to set.
	 */
	public void setMarket(String market) {
		this.market = market;
	
	}
	/**
	 * @return Returns the stockid.
	 */
	public String getStockid() {
		return stockid;
	}
	/**
	 * @param stockid The stockid to set.
	 */
	public void setStockid(String stockid) {
		this.stockid = stockid;
	}
	/**
	 * @return Returns the stockname.
	 */
	public String getStockname1() {
		return stockname1;
	}
	/**
	 * @param stockname The stockname to set.
	 */
	public void setStockname1(String stockname1) {
		this.stockname1= stockname1;
	}
	/**
	 * @return Returns the stockprice.
	 */
	public String getStockprice() {
		return stockprice;
	}
	/**
	 * @param stockprice The stockprice to set.
	 */
	public void setStockprice(String stockprice) {
		this.stockprice = stockprice;
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
	 * @return Returns the curr_exch_convIratecomp.
	 */
	public String getCurr_exch_convIratecomp() {
		return curr_exch_convIratecomp;
	}
	/**
	 * @param curr_exch_convIratecomp The curr_exch_convIratecomp to set.
	 */
	public void setCurr_exch_convIratecomp(String curr_exch_convIratecomp) {
		this.curr_exch_convIratecomp = curr_exch_convIratecomp;
	}
	/**
	 * @return Returns the strweightage.
	 */
	public String getStrweightage() {
		return strweightage;
	}
	/**
	 * @param strweightage The strweightage to set.
	 */
	public void setStrweightage(String strweightage) {
		this.strweightage = strweightage;
	}	
}
