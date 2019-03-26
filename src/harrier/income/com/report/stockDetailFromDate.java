/*
 * Created on May 27, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;

/**
 * @author manoj
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class stockDetailFromDate {
	public String stockName,priceDate,isCA,series,tradedVolume,tradedValue,symbol,pusd,musd;
	public String tis;
	public String price,mcv,ica;
	public stockDetailFromDate(String stockName1,String Series1,String Tis1,String Price1,String priceDate1,String MCV1,String tradedVolume1,String tradedValue1,String ca,String ic1,String Pusd1,String Musd1){//,String symbol1){
		this.stockName=stockName1;
		this.series=Series1;
		this.tis=Tis1;
		this.price=Price1;
		this.priceDate=priceDate1;
		this.mcv=MCV1;
		this.tradedVolume=tradedVolume1;
		this.tradedValue=tradedValue1;
		this.isCA=ca;
		this.ica=ic1;
		this.pusd=Pusd1;
		this.musd=Musd1;
		//this.symbol=symbol1;
	}
	
	
	
	
	/**
	 * @return Returns the musd.
	 */
	public String getMusd() {
		return musd;
	}
	/**
	 * @param musd The musd to set.
	 */
	public void setMusd(String musd) {
		this.musd = musd;
	}
	/**
	 * @return Returns the pusd.
	 */
	public String getPusd() {
		return pusd;
	}
	/**
	 * @param pusd The pusd to set.
	 */
	public void setPusd(String pusd) {
		this.pusd = pusd;
	}
	/**
	 * @return Returns the ic.
	 */
	public String getIca() {
		return ica;
	}
	/**
	 * @param ic The ic to set.
	 */
	public void setIca(String ica) {
		this.ica = ica;
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
	 * @return Returns the isCA.
	 */
	public String getIsCA() {
		return isCA;
	}
	/**
	 * @param isCA The isCA to set.
	 */
	public void setIsCA(String isCA) {
		this.isCA = isCA;
	}
	
	
	/**
	 * @return Returns the tradedValue.
	 */
	public String getTradedValue() {
		return tradedValue;
	}
	/**
	 * @param tradedValue The tradedValue to set.
	 */
	public void setTradedValue(String tradedValue) {
		this.tradedValue = tradedValue;
	}
	/**
	 * @return Returns the tradedVolume.
	 */
	public String getTradedVolume() {
		return tradedVolume;
	}
	/**
	 * @param tradedVolume The tradedVolume to set.
	 */
	public void setTradedVolume(String tradedVolume) {
		this.tradedVolume = tradedVolume;
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
	/**
	 * @return Returns the price.
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * @param price The price to set.
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * @return Returns the priceDate.
	 */
	public String getPriceDate() {
		return priceDate;
	}
	/**
	 * @param priceDate The priceDate to set.
	 */
	public void setPriceDate(String priceDate) {
		this.priceDate = priceDate;
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
}
