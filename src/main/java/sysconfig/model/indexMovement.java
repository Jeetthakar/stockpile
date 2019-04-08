/*
 * Created on Jun 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package sysconfig.model;

import java.util.ArrayList;

import com.harrier.initializeation.ConnectInit;


/**
 * @author manoj
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class indexMovement {

	public ArrayList tableDate;
	public String tradingDate,close,mCap,divisor,pe,pb,divYield;
	
	app.Connect con=ConnectInit.getConnect();
	public indexMovement(String td,String c,String mc,String di,String pe1,String pb1,String dy1){//,String symbol1){
		this.tradingDate=td;
		this.close=c;
		this.mCap=mc;
		this.divisor=di;
		this.pe=pe1;
		this.pb=pb1;
		this.divYield=dy1;
		
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
	 * @return Returns the divisor.
	 */
	public String getDivisor() {
		return divisor;
	}
	/**
	 * @param divisor The divisor to set.
	 */
	public void setDivisor(String divisor) {
		this.divisor = divisor;
	}
	/**
	 * @return Returns the divYield.
	 */
	public String getDivYield() {
		return divYield;
	}
	/**
	 * @param divYield The divYield to set.
	 */
	public void setDivYield(String divYield) {
		this.divYield = divYield;
	}
	/**
	 * @return Returns the mCap.
	 */
	public String getMCap() {
		return mCap;
	}
	/**
	 * @param cap The mCap to set.
	 */
	public void setMCap(String cap) {
		mCap = cap;
	}
	/**
	 * @return Returns the pb.
	 */
	public String getPb() {
		return pb;
	}
	/**
	 * @param pb The pb to set.
	 */
	public void setPb(String pb) {
		this.pb = pb;
	}
	/**
	 * @return Returns the pe.
	 */
	public String getPe() {
		return pe;
	}
	/**
	 * @param pe The pe to set.
	 */
	public void setPe(String pe) {
		this.pe = pe;
	}
	
	/**
	 * @return Returns the tradingDate.
	 */
	public String getTradingDate() {
		return tradingDate;
	}
	/**
	 * @param tradingDate The tradingDate to set.
	 */
	public void setTradingDate(String tradingDate) {
		this.tradingDate = tradingDate;
	}
	
	/**
	 * Database Connectivity
	 * */
	/*public void dbconnect(){
		
		try {if(app.Connect.con==null){
			con.getConnection();
			}
		} catch (Exception e) {	System.out.println(e);} 
		
	}*/
	
}
