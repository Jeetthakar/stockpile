/*
 * Created on Oct 11, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import java.util.Date;

import org.apache.log4j.Logger;
import org.jfree.chart.demo.servlet.AdjustDecimal;

import com.harrier.initializeation.ConnectInit;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CStockPriceComponents {
	Logger Logging = Logger.getLogger(CStockPriceComponents.class);
	private String str_symbol;
	private String str_series;
	private String str_open;
	private String str_high;
	private String str_low;
	private String str_close;
	private String str_last;
	private String str_prevClose;
	private String str_totTradedQty;
	private String str_totTradedVol;
	private String str_date;
	private String str_time;
	private String tis;
	private String mkt_cap;
	private String exchange_id;
	private String cusip;
	private String net_change;
	
	protected void finalize() {
		
		this.str_symbol = null;
		this.str_series = null;
		this.str_open = null;
		this.str_high = null;
		this.str_low = null;
		this.str_close = null;
		this.str_last = null;
		this.str_prevClose = null;
		this.str_totTradedQty = null;
		this.str_totTradedVol = null;
		this.str_date = null;
		this.str_time = null;
		this.tis = null;
		this.mkt_cap = null;
		this.exchange_id =null;
		this.cusip =null;
		this.net_change = null;
	}
	/**
	 * @return Returns the net_change.
	 */
	public String getNet_change() {
		return net_change;
	}
	/**
	 * @param net_change The net_change to set.
	 */
	public void setNet_change(String net_change) {
		this.net_change = net_change;
	}
	/**
	 * @return Returns the cusip.
	 */
	public String getCusip() {
		return cusip;
	}
	/**
	 * @param cusip The cusip to set.
	 */
	public void setCusip(String cusip) {
		this.cusip = cusip;
	}
	/**
	 * @return Returns the exchange_id.
	 */
	public String getExchange_id() {
		return exchange_id;
	}
	/**
	 * @param exchange_id The exchange_id to set.
	 */
	public void setExchange_id(String exchange_id) {
		this.exchange_id = exchange_id;
	}
	/**
	 * @return Returns the mkt_cap.
	 */
	public String getMkt_cap() {
		Logging.debug("Inside get Mkt cap");
		Logging.debug(""+Double.parseDouble(str_last));
		Logging.debug("tis is "+tis);
	//	 org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		if((tis==null)||(tis.equals("0")))
		{
			mkt_cap="0.0";
		}else{
			if((Double.parseDouble(str_close))==0)
			{
				double mcv=(Double.parseDouble(str_last))*(Double.parseDouble(tis));
				mkt_cap=new Double(mcv).toString();
			}else{
				double mcv=(Double.parseDouble(str_close))*(Double.parseDouble(tis));
				mkt_cap=new Double(mcv).toString();
			}		
		}
		Logging.debug("mkt_cap is "+mkt_cap);
		mkt_cap=ad.shareholdingpatt(mkt_cap);
		return mkt_cap;
	}
	/**
	 * @param mkt_cap The mkt_cap to set.
	 */
	public void setMkt_cap(String mkt_cap) {
		this.mkt_cap = mkt_cap;
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
	 * 
	 */
	public CStockPriceComponents() {

		// TODO Auto-generated constructor stub
		Date dt = new Date();
		dt.getDate();
		str_time = dt.toString().split(" ")[3];		
	}
	/**
	 * @return Returns the str_close.
	 */
	public String getStr_close() {
		return str_close;
	}
	/**
	 * @param str_close The str_close to set.
	 */
	public void setStr_close(String str_close) {
		this.str_close = str_close;
	}
	/**
	 * @return Returns the str_date.
	 */
	public String getStr_date() {
		return str_date;
	}
	/**
	 * @param str_date The str_date to set.
	 */
	public void setStr_date(String str_date) {
		this.str_date = str_date;
	}
	/**
	 * @return Returns the str_high.
	 */
	public String getStr_high() {
		return str_high;
	}
	/**
	 * @param str_high The str_high to set.
	 */
	public void setStr_high(String str_high) {
		this.str_high = str_high;
	}
	/**
	 * @return Returns the str_last.
	 */
	public String getStr_last() {
		return str_last;
	}
	/**
	 * @param str_last The str_last to set.
	 */
	public void setStr_last(String str_last) {
		this.str_last = str_last;
	}
	/**
	 * @return Returns the str_low.
	 */
	public String getStr_low() {
		return str_low;
	}
	/**
	 * @param str_low The str_low to set.
	 */
	public void setStr_low(String str_low) {
		this.str_low = str_low;
	}
	/**
	 * @return Returns the str_open.
	 */
	public String getStr_open() {
		return str_open;
	}
	/**
	 * @param str_open The str_open to set.
	 */
	public void setStr_open(String str_open) {
		this.str_open = str_open;
	}
	/**
	 * @return Returns the str_prevClose.
	 */
	public String getStr_prevClose() {
		return str_prevClose;
	}
	/**
	 * @param str_prevClose The str_prevClose to set.
	 */
	public void setStr_prevClose(String str_prevClose) {
		this.str_prevClose = str_prevClose;
	}
	/**
	 * @return Returns the str_series.
	 */
	public String getStr_series() {
		return str_series;
	}
	/**
	 * @param str_series The str_series to set.
	 */
	public void setStr_series(String str_series) {
		this.str_series = str_series;
	}
	/**
	 * @return Returns the str_symbol.
	 */
	public String getStr_symbol() {
		return str_symbol;
	}
	/**
	 * @param str_symbol The str_symbol to set.
	 */
	public void setStr_symbol(String str_symbol) {
		this.str_symbol = str_symbol;
	}
	/**
	 * @return Returns the str_time.
	 */
	public String getStr_time() {
		return str_time;
	}
	/**
	 * @param str_time The str_time to set.
	 */
	public void setStr_time(String str_time) {
		this.str_time = str_time;
	}
	/**
	 * @return Returns the str_totTradedQty.
	 */
	public String getStr_totTradedQty() {
		return str_totTradedQty;
	}
	/**
	 * @param str_totTradedQty The str_totTradedQty to set.
	 */
	public void setStr_totTradedQty(String str_totTradedQty) {
		this.str_totTradedQty = str_totTradedQty;
	}
	/**
	 * @return Returns the str_totTradedVol.
	 */
	public String getStr_totTradedVol() {
		return str_totTradedVol;
	}
	/**
	 * @param str_totTradedVol The str_totTradedVol to set.
	 */
	public void setStr_totTradedVol(String str_totTradedVol) {
		this.str_totTradedVol = str_totTradedVol;
	}
}
