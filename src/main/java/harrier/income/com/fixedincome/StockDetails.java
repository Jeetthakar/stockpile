/*
 * Created on jun 12,2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.fixedincome;
import org.apache.log4j.Logger;
import org.jfree.chart.demo.servlet.AdjustDecimal;

import com.harrier.initializeation.ConnectInit;

import app.*;
import harrier.income.com.entities.*;

/**
 * @author neha
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StockDetails {
	Logger Logging = Logger.getLogger(StockDetails.class);
	int stockID;
	String stockName;
	double iwf;
	double adjustedIWF=0.0;
	double ltp;
	String currency;
	long tis;
	long market_lot;
	String date;
	String currencyId;
	String inclDate=null;
	String exclDate=null;
	long outStanding =0;
	double mktCapital=0.0f;
	static double totalMktCapital=0.0f;
	double adjustedMarket=0.0f;
	double wightage=0.0f;
//	CFormula formula = new CFormula();

	/**
	 * @return Returns the exclDate.
	 */
	public String getExclDate() {
		return exclDate;
	}
	/**
	 * @param exclDate The exclDate to set.
	 */
	public void setExclDate(String exclDate) {
		this.exclDate = exclDate;
	}
	/**
	 * @return Returns the inclDate.
	 */
	public String getInclDate() {
		return inclDate;
	}
	/**
	 * @param inclDate The inclDate to set.
	 */
	public void setInclDate(String inclDate) {
		this.inclDate = inclDate;
	}
	/**
	 * @return Returns the mktCapital.
	 */
	public double getMktCapital() {
		return mktCapital;
	}
	
	public void setMarket_lot(long market_lot) {
		this.market_lot = market_lot;
	}
	/**
	 * @return Returns the inclusion exclusion date.
	 */
	public  StockDetails(){
		
	}
	public StockDetails(int id,String stockName,String inclusion_date,String exclusion_date,String currency_id)
	{
	  this.stockID = id; 	
	  this.stockName = stockName;
	 this.inclDate=inclusion_date;
	 this.exclDate=exclusion_date;
	 this.currencyId=currency_id;
	}
	/**
	 * @return Returns the currency.
	 */
	public StockDetails(int id,String stockName,double iwf,double ltp,String currency,long tis,long market_lot,String date)
	{
	  this.stockID = id; 	
	  this.stockName = stockName;
	  this.iwf = iwf;
	  this.date=date;
	  this.ltp = ltp;
	  this.currency = currency;
	  this.tis =tis;
	  this.market_lot=market_lot;
	}
	/**
	 * @return Returns the currency.
	 */
	public StockDetails(int id,String stockName,double iwf,double ltp,String currency,long tis,long market_lot,String date,String currencyId)
	{
	  this.stockID = id; 	
	  this.stockName = stockName;
	  this.iwf = iwf;
	  this.date=date;
	  this.ltp = ltp;
	  this.currency = currency;
	  this.tis =tis;
	  this.market_lot=market_lot;
	  this.currencyId=currencyId;
	}
	
	public StockDetails(int id,String stockName){
		this.stockID = id; 	
		  this.stockName = stockName;
	}
	public double getAdjustedIWF() {
		if(adjustedIWF==0.0)
			adjustedIWF =iwf;
		return adjustedIWF;
	}
	/**
	 * @param adjustedIWF The adjustedIWF to set.
	 */
	public void setAdjustedIWF(double adjustedIWF) {
		this.adjustedIWF = adjustedIWF;
	}
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
	 * @return Returns the market_lot.
	 */
	public long getMarket_lot() {
		return market_lot;
	}
	/**
	 * @param market_lot The market_lot to set.
	 */
	public double getIwf() {
		return iwf;
	}
	/**
	 * @param iwf The iwf to set.
	 */
	public void setIwf(double iwf) {
		this.iwf = iwf;
	}
	/**
	 * @return Returns the ltp.
	 */
	public double getLtp() {
		return ltp;
	}
	/**
	 * @param ltp The ltp to set.
	 */
	public void setLtp(double ltp) {
		this.ltp = ltp;
	}
	/**
	 * @return Returns the stockID.
	 */
	public int getStockID() {
		return stockID;
	}
	/**
	 * @param stockID The stockID to set.
	 */
	public void setStockID(int stockID) {
		this.stockID = stockID;
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
	public long getTis() {
		//System.out.println("TIS "+tis);
		return tis;
	}
	/**
	 * @param tis The tis to set.
	 */
	public void setTis(long tis) {
		this.tis = tis;
	}
	/**
	 * @return Returns the adjustedMarket.
	 */
	public double getAdjustedMarket(int indextype) {
		CFormula formula = ConnectInit.getCFormula();
		adjustedMarket = formula.calMarketCap1(ltp,1,1,tis,adjustedIWF,indextype);
		if(adjustedMarket==0.0)
			adjustedMarket =mktCapital;
			return adjustedMarket;
	}
	/**
	 * @param adjustedMarket The adjustedMarket to set.
	 */
	public void setAdjustedMarket(double adjustedMarket) {
		this.adjustedMarket = adjustedMarket;
	}
	/**
	 * @return Returns the mktCapital.
	 */
	public double getMktCapital(int indextype) {
		CFormula formula = ConnectInit.getCFormula();
		//compute currency rate and pass it as 3rd variable
		mktCapital = formula.calMarketCap1(ltp,1,1,tis,iwf,indextype);
		return mktCapital;
	}
	/**
	 * @return Returns the mktCapital.
	 */
	public double getMktCapital1(int indextype,String parentCurrencyId,String stkCurrencyid) {
		//compute currency rate and pass it as 3rd variable
		CFormula formula = ConnectInit.getCFormula();
	//	AdjustDecimal ad=new AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		double exch=1.0;
		try{
        	String string=stkCurrencyid;
        	String string2=parentCurrencyId;
        	
        	String temp=IndexCalculatorCollection.getIndexCurrancyExchRate(string,string2);
        	if(temp!=null){
        		exch=new Double(temp).doubleValue();
        	}else{
        		temp=IndexCalculatorCollection.getIndexCurrancyExchRate(string2,string);
        		if(temp==null){
        			exch=1.0;
        		}else{
        			exch=1/new Double(temp).doubleValue();
        		}
        	}
        
        	
        }catch (Exception e) {
        //	e.printStackTrace();
        	Logging.debug(e);
        	exch=1.0;
			// TODO: handle exception
		}
        String exch_rate=new Double(exch).toString();
        exch_rate=ad.indexcompose4digit(exch_rate);
        exch=(double)Double.parseDouble(exch_rate);
        double d=new Double(exch).doubleValue();
        Logging.debug("market_lot is "+market_lot);
		mktCapital = formula.calMarketCap1(ltp,market_lot,d,tis,iwf,indextype);
		return mktCapital;
	}
	/**
	 * @param mktCapital The mktCapital to set.
	 */
	public void setMktCapital(double mktCapital) {
		this.mktCapital = mktCapital;
	}
	/**
	 * @return Returns the outStanding.
	 */
	public long getOutStanding() {
		
		this.outStanding = (long)(tis*iwf);  
		return outStanding;
	}
	/**
	 * @param outStanding The outStanding to set.
	 */
	public void setOutStanding(long outStanding) {
		this.outStanding = outStanding;
	}
	/**
	 * @return Returns the wightage.
	 */
	public double getWightage() {
		 wightage = (mktCapital/totalMktCapital)*100;
		//System.out.println("Total mkt capital "+totalMktCapital);
		return wightage;
	}
	/**
	 * @param wightage The wightage to set.
	 */
	public void setWightage(double wightage) {
		this.wightage = wightage;
	}

	public double getTotalMktCapital() {
		return totalMktCapital;
	}
	/**
	 * @param totalMktCapital The totalMktCapital to set.
	 */
	public void setTotalMktCapital(double totalMktCapital) {
		this.totalMktCapital = totalMktCapital;
	}
	/**
	 * @return Returns the wightage.
	 */

	/**
	 * @return Returns the currencyId.
	 */
	public String getCurrencyId() {
		return currencyId;
	}
	/**
	 * @param currencyId The currencyId to set.
	 */
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}
}
	


