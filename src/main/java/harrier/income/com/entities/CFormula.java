/*
 * Created on Sep 9, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.entities;
import java.util.*;

import org.apache.log4j.Logger;

/**
 * @author kena
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CFormula {
	Logger Logging = Logger.getLogger(CFormula.class);
	/** 
	 * Calculates Market Capitalization value
	 * Formula to Calculate Market Capitalization i.e., MCV
	 * MCV = ((LTP/ML* BCV) * TIS * IWF)
	 * @param d_ltp - latest traded price
	 * @param l_mLot - Market Lot
	 * @param d_baseCVal - Base Currency Base
	 * @param l_tis - Total issuable shares
	 * @param d_iwf - investible weight factor
	 * @return 
	 */
	public  double calMarketCap(double d_ltp,long l_mLot,
			double d_exch,long l_tis,double d_iwf){
		double d_midCap=0;
		double d_marketCap=0;
		d_midCap = ((d_ltp/l_mLot)* d_exch);
		d_marketCap = (d_midCap * l_tis * d_iwf); 
		return d_marketCap;
	}
	
	/** 
	 * Calculates Market Capitalization value
	 * Formula to Calculate Market Capitalization i.e., MCV
	 * MCV = ((LTP/ML* BCV) * TIS * IWF)
	 * @param d_ltp - latest traded price
	 * @param l_mLot - Market Lot
	 * @param d_baseCVal - Base Currency Base
	 * @param l_tis - Total issuable shares
	 * @param d_iwf - investible weight factor
	 * @return 
	 */
	public  double calMarketCap1(double d_ltp,long l_mLot,
			double d_exch,long l_tis,double d_iwf,int indextype){
		double d_midCap=0;
		double d_marketCap=0;
		d_midCap = ((d_ltp/l_mLot)* d_exch);
		if(indextype==1){
		    d_marketCap = (d_midCap * l_tis * (1.0)); 
		}else{
		    d_marketCap = (d_midCap * l_tis * d_iwf);    
			
		}
		return d_marketCap;
	}
	
	/** written by neha **/
	public  double calMarketCapt(double d_ltp,long l_mLot,
			double d_exch,long l_tis,int indextype){
		double d_midCap=0;
		double d_marketCap=0;
		d_midCap = ((d_ltp/l_mLot)* d_exch);
		if(indextype==1){
		    d_marketCap = (d_midCap * l_tis * (1.0)); 
		}else{
		 
			d_marketCap = (d_midCap * l_tis);
		}
		return d_marketCap;
	}
	
	
	
	
	
	
	
	
	
	/**
	 * Formula to Calculate Divisor i.e., D
	 * D = (TMVC / BCV)
	 * @param d_tmcv
	 * @param d_baseCVal
	 * @return
	 */
	public  double divisor(double d_tmcv,double d_baseVal){
		double d_divisor=0.0d;
		d_divisor=(d_tmcv / d_baseVal);
		return d_divisor;
	}
	
	/**
	 * Formula to Total Market CApitalization Value i.e., TMCV
	 * @param v
	 * @return
	 */
	public  double totalMarketCap(Vector v){
		double d_tmcv=0;
		Logging.debug("tmcv in totalMarketCap is "+d_tmcv);
		Logging.debug("vector size is "+v.size());
		try{
		for(int i=0;i<v.size();i++)
		{
			//app.Logging.getDebug("mcv in totalMarketCap is "+Double.parseDouble((String)v.get(i)));
			Double D_tmcv = (Double) v.get(i);
			String str = D_tmcv.toString();
			
			//d_tmcv = d_tmcv+Double.parseDouble((String)v.get(i));
 			d_tmcv = d_tmcv + Double.valueOf(str.trim()).doubleValue();
			//app.Logging.getDebug("TMCV after adding  "+d_tmcv);
		}
		}catch(Exception e)
		{
		Logging.error("Error in Casting :  "+e.getMessage());
		}
		//app.Logging.getDebug("TMCV in Formula "+d_tmcv);
		return d_tmcv;
	}
	/**
	 * Formula to Calculate Index i.e., I
	 * I = TMVC / D
	 * @param d_tmcv
	 * @param d_divisor
	 * @return
	 */
	public  double index(double d_tmcv,double d_divisor){
		double d_index=0;
		d_index=(d_tmcv / d_divisor);
		return d_index;
	}
	
	/** formula for clean price index for fixed income 
	 *   
	 * 
	 */
	public double fixedincomeindex(double oldindexvalue,double oldtmcv,double tmcv){
		double fi_index=0;
		fi_index=(oldindexvalue*(tmcv/oldtmcv));
		return fi_index;
	}
	
	
	
	/**
	 * Formula to Calculate new LTP value for stock dividend
	 * LTP new  = LTP old  * A / (A + B)
	 * @param d_oldLTP
	 * @param l_aRatio
	 * @param l_bRatio
	 * @return
	 */
	public  double newLTPStkDivi(double d_oldLTP,
			double l_aRatio,double l_bRatio){
		double d_newLTP=0;
		d_newLTP=((d_oldLTP * l_aRatio)/ (l_aRatio + l_bRatio));
		return d_newLTP;
	}	
	public  double newRecalLTPStkDivi(double d_newLTP,
			double l_aRatio,double l_bRatio){
		double d_oldLTP=0;
		double value=(l_aRatio+ l_bRatio);
		d_oldLTP=((d_newLTP * value)/ (l_aRatio ));		
		return d_oldLTP;
	}
	/**
	 * Formula to Calculate new TIS value for stock dividend
	 * TIS new = TIS old *(A+B) / A 
	 * @param l_oldTIS
	 * @param l_aRatio
	 * @param l_bRatio
	 * @return
	 */
	public  long newTISStkDivi(long l_oldTIS,
			double l_aRatio,double l_bRatio){
		long l_newTIS=0;
		l_newTIS=(long)Math.round(((l_oldTIS * (l_aRatio + l_bRatio))/ l_aRatio));		
		return l_newTIS;
	}	
	public  long newRecalTISStkDivi(long l_newTIS,
			double l_aRatio,double l_bRatio){
		long l_oldTIS=0;
		l_oldTIS=(long)Math.round((l_newTIS * l_aRatio)/ (l_aRatio + l_bRatio)); 
		return l_oldTIS;
	}
	/** Formula to calculate the difference between two Total
	 * Market Capitalization value i.e, TMCV new - TMCV old  
	 * @param d_oldTMCV
	 * @param d_newTMCV
	 * @return
	 */
	public  double diffTMCV(double d_oldTMCV,double d_newTMCV){
		double d_diffTMCV=0;		
		d_diffTMCV=(d_newTMCV - d_oldTMCV);		
		return d_diffTMCV;
	}	
	
	public  long newTISIssue(long d_oldTis,long d_newTis){
		long l_newTIS=0;
		l_newTIS=(d_oldTis + d_newTis);
		return l_newTIS;
	}	
	public  long newRecalTISIssue(long d_oldTis,long d_newTis){
		long l_newTIS=0;
		l_newTIS=(d_oldTis - d_newTis);
		return l_newTIS;
	}	
	
	public  long newTISReduce(long d_oldTis,long d_newTis){
		long l_newTIS=0;
		l_newTIS=(d_oldTis - d_newTis);
		return l_newTIS;
	}	
	public  long newRecalTISReduce(long d_oldTis,long d_newTis){
		long l_newTIS=0;
		l_newTIS=(d_oldTis + d_newTis);
		return l_newTIS;
	}
	/** Formula to calculate change in divisor 
	 * in case of Stock Dividend i.e.,
	 * D new = D old  *((TMCV old  + ? TMCV) / (TMCV old)
	 * @param d_oldTMCV
	 * @param d_diffTMCV
	 * @param d_oldDivisor
	 * @return
	 */
	public  double newDivisorCorp(double d_oldTMCV,
			double d_diffTMCV,double d_oldDivisor){
		double d_newDivisor=0;
		double d_midDivisor=0;
		d_midDivisor=((d_oldTMCV + d_diffTMCV)/d_oldTMCV );
		d_newDivisor=(d_oldDivisor * d_midDivisor);		
		return d_newDivisor;
	}	
	public  double RecalnewDivisorCorp(double d_oldTMCV,
			double d_diffTMCV,double d_oldDivisor){
		double d_newDivisor=0;
		double d_midDivisor=0;	
		d_midDivisor=(d_oldTMCV + d_diffTMCV);		
		d_newDivisor=(d_oldDivisor * d_oldTMCV)/d_midDivisor;
		return d_newDivisor;
	}	
	/**
	 * Calculates the change in latest traded price 
	 * due to split or reverse split
	 * Formula to Calculate new LTP value for split
	 *  and Reverse Split
	 * LTPnew = (LTPold * A / B)
	 * @param d_oldLTP - old latest traded price
	 * @param l_aRatio - share 'A' held 
	 * @param l_bRatio - new shares for every 'A' share held 
	 * @return
	 */
	public  double newLTPSplitRev(double d_oldLTP,
			double l_aRatio,double l_bRatio){
		double d_newLTP=0;
		d_newLTP=((d_oldLTP * l_aRatio )/ l_bRatio);
		return d_newLTP;
	}	
	public  double newRecalLTPSplitRev(double d_newLTP,
			double l_aRatio,double l_bRatio){
		double d_oldLTP=0;
		d_oldLTP=((d_newLTP * l_bRatio )/ l_aRatio);
		return d_oldLTP;
	}
	  
	/**
	 * Formula to Calculate new TIS value for stock dividend
	 * TIS new = TIS old *( B/ A)
	 * @param l_oldTIS
	 * @param l_aRatio
	 * @param l_bRatio
	 * @return
	 */
	public  long newTISSplitRev(long l_oldTIS,
			double l_aRatio,double l_bRatio){
		long l_newTIS;
		double value=(l_bRatio/l_aRatio);		
		l_newTIS=(long)Math.round(l_oldTIS * value);
		return l_newTIS;
	}	
	public  long newRecalTISSplitRev(long l_newTIS,
			double l_aRatio,double l_bRatio){
		long l_oldTIS;
		double value=(l_aRatio/l_bRatio);		
		l_oldTIS=(long)Math.round(l_newTIS * value);		 
		return l_oldTIS;
	}
	/**
	 * Calculates the change in latest traded price 
	 * due to rights offering
	 * Formula to Calculate new LTP value for rights offering
	 * LTP new  = (LTP old * A + subscription price * B) / (A + B)
	 * @param d_oldLTP
	 * @param l_aRatio
	 * @param l_bRatio
	 * @param d_subPrice
	 * @return
	 */
	public  double newLTPRightsOff(double d_oldLTP,
			double l_aRatio,double l_bRatio,double d_subPrice ){
		double d_newLTP=0;
		d_newLTP= ((d_oldLTP * l_aRatio) +
				(d_subPrice * l_bRatio))/(l_aRatio + l_bRatio) ;
		return d_newLTP;
	}	
	public  double newRecalLTPRightsOff(double d_newLTP,
			double l_aRatio,double l_bRatio,double d_subPrice ){
		double d_oldLTP=0;
		d_oldLTP= ((d_newLTP*(l_aRatio+l_bRatio))-(l_bRatio*d_subPrice))/l_aRatio;
		return d_oldLTP;
	}
	

	/**
	 * calculates change in total number of issuable shares
	 * due to Rights Offering
	 * Formula to Calculate new TIS value for stock dividend
	 * TIS new = TIS old * (A + B) / A
	 * @param l_oldTIS
	 * @param l_aRatio
	 * @param l_bRatio
	 * @return
	 */
	public  long newTISRightsOff(long l_oldTIS,
			double l_aRatio,double l_bRatio){
		long l_newTIS=0;
		l_newTIS=(long)Math.round(l_oldTIS * ((l_bRatio+l_aRatio)/l_aRatio));
		return l_newTIS;
	}	
	public  long newRecalTISRightsOff(long l_newTIS,
			double l_aRatio,double l_bRatio){
		long l_oldTIS=0;
		l_oldTIS=(long)Math.round(l_newTIS * ((l_aRatio)/(l_aRatio+l_bRatio)));
		return l_oldTIS;
	}
	/**
	 * Calculates the change in latest traded price 
	 * due to Cash Dividend
	 * Formula to Calculate new LTP value for cash Dividend
	 * LTP new = LTP old  - Dividend announced by company.
	 * @param d_oldLTP
	 * @param d_cashDivi
	 * @return
	 */
	public  double newLTPCashDivi(double d_oldLTP,
			double d_cashDivi ){
		double d_newLTP=0;
		d_newLTP=(d_oldLTP - d_cashDivi);
		return d_newLTP;
	}	
	/**
	 * Calculates the change in latest traded price 
	 * due to rights offering
	 * Formula to Calculate new LTP value for rights offering
	 * LTP new  = [(LTP old  * TIS old) � (TP * T)]/ (TIS old �T)
	 * @param d_oldLTP
	 * @param l_oldTIS
	 * @param l_tenShares
	 * @param d_tenPrice
	 * @return
	 */
	public  double newLTPRepurchase(double d_oldLTP,
			long l_oldTIS,long l_tenShares,double d_tenPrice ){
		double d_newLTP=0;
		d_newLTP= (((d_oldLTP * l_oldTIS) - (d_tenPrice * l_tenShares))/ 
				(l_oldTIS -l_tenShares ));
		return d_newLTP;
	}	
	public  double newRecalLTPRepurchase(double d_newLTP,
			long l_oldTIS,long l_tenShares,double d_tenPrice ){
		double d_oldLTP=0;
		d_oldLTP=((d_newLTP*(l_oldTIS-l_tenShares))+(d_tenPrice*l_tenShares))/l_oldTIS;
		return d_oldLTP;
	}
	/** 
	 * calculates change in total number of issuable shares
	 * due to Rights Offering
	 * Formula to Calculate new TIS value for stock dividend
	 * TIS new = TIS old  - T
	 * @param l_oldTIS
	 * @param l_tenShares
	 * @return
	 */
	public  long newTISRepurchase(long l_oldTIS,long l_tenShares){
		long l_newTIS=0;
		l_newTIS=(l_oldTIS - l_tenShares);
		return l_newTIS;
	}
	public  long newRecalTISRepurchase(long l_newTIS,long l_tenShares){
		long l_oldTIS=0;
		l_oldTIS=(l_newTIS+l_tenShares);
		return l_oldTIS;
	}
	/**
	 * Calculates the change in latest traded price 
	 * due to rights offering
	 * Formula to Calculate new LTP value for rights offering
	 * LTPnew = ((LTPold * A- price of spun-off shares * B ) / A)
	 * @param d_oldLTP
	 * @param l_aRatio
	 * @param l_bRatio
	 * @param d_spinPrice
	 * @return
	 */
	public  double newLTPSpinOff(double d_oldLTP,
			double l_aRatio,double l_bRatio,double d_spinPrice){
		double d_newLTP=0;
		d_newLTP= ((d_oldLTP*l_aRatio-d_spinPrice*l_bRatio)/ l_aRatio);
		return d_newLTP;
	}
	public long newTISSpinOff(long l_oldTIS,double l_aRatio,double l_bRatio){
		long l_newTIS=0;
		double value=((l_aRatio)/(l_aRatio+l_bRatio));
		l_newTIS=(long)Math.round(l_oldTIS * value);
		return l_newTIS;
	}
	public  double newRecalLTPSpinOff(double d_newLTP,
			double l_aRatio,double l_bRatio,double d_spinPrice){
		double d_oldLTP=0;
		d_oldLTP=((d_newLTP*l_aRatio+d_spinPrice*l_bRatio)/l_aRatio); 
		return d_oldLTP;
	}
	public long newRecalTISSpinOff(long l_newTIS,double l_aRatio,double l_bRatio){
		long l_oldTIS=0;
		double value=l_aRatio;
		l_oldTIS=(long)Math.round((l_newTIS * (l_aRatio+l_bRatio)/value));
		return l_oldTIS;
	}
	/**
	 * Calculates the number of new shares getting added to the old number of shares 
	 * for a particular company
	 * due to warrant conversion
	 * Formula to Calculate new LTP value for rights offering
	 * T = N * W
	 * @param l_noOWarr
	 * @param l_noFOneWarr
	 * @return
	 */
	public  long noOSharesFWar(long l_perShares,long l_nOfShares,long l_noFOneWarr){
		long l_noOShares=0;
		l_noOShares=Math.round(l_nOfShares * l_noFOneWarr)/l_perShares;
		return l_noOShares;
	}	
	/** 
	 * calculates change in total number of issuable shares
	 * due to Warrant Conversion
	 * Formula to Calculate new TIS value for Warrant Conversion
	 * TIS new = TIS old  + T */
	public  long newTISWarrantCon(long l_oldTIS,long l_warShares){
		long l_newTIS=0;
		l_newTIS=(l_oldTIS + l_warShares);
		return l_newTIS;
	}	
	public  long newRecalTISWarrantCon(long l_newTIS,long l_warShares){
		long l_oldTIS=0;
		l_oldTIS=(l_newTIS - l_warShares);
		return l_oldTIS;
	}
	public double alert_reject(double oldmcv,double newmcv)
	{
		double val=0.0;
		val=((oldmcv-newmcv)/oldmcv)*100;
		return val;
	}
	public long merge_tis(long r1, long r2,long oldtis1,long oldtis2)
	{
		long newtis=0;
		long no_share=oldtis1*(r2/r1);
		newtis=no_share+newtis;
		return newtis;
	}
/*	public static void main(String args[])
	{
		CFormula cf=new CFormula();
		double ltp=cf.newLTPSpinOff(10,5,1,8);
		long tis=cf.newTISSpinOff(20,5,1);
	}
*/	
}