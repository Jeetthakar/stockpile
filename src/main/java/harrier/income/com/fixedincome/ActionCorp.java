/*
 * Created on 27 jun,2008
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.fixedincome;
import harrier.income.com.entities.CFormula;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.jfree.chart.demo.servlet.AdjustDecimal;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * @author neha
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences -  Java - Code Style - Code Templates
 */

public class ActionCorp {
	static Logger Logging = Logger.getLogger(ActionCorp.class);
// This function is called when user wants to see affected index divisor and tmcv
	//used in fi event
	public static void toGetaffect(FixedIncomeCorporate corporateact)
	{
		try{
			String newltp=corporateact.getNewLTP();
    		String newtis=corporateact.getNewTIS();    		
    		String iwf=corporateact.getIwfstk();    		
    		String oldltp=corporateact.getOldltp();
    		String oldtis=corporateact.getOldtis();
    		String oldtmcv=corporateact.getTmcv();
    		String olddivisor=corporateact.getDivisor();
    //		CFormula cf = new CFormula();
    		CFormula cf = ConnectInit.getCFormula();
    		double oldmcv=  cf.calMarketCap(Double.parseDouble(oldltp),Long.parseLong(corporateact.getMark_lot()),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(oldtis),Double.parseDouble(iwf));
			Logging.debug("OLDMCV  "  + oldmcv);	
			
			double newmcv= cf.calMarketCap(Double.parseDouble(newltp),Long.parseLong(corporateact.getMark_lot()),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(newtis),Double.parseDouble(iwf));
			Logging.debug(" NewMCV "  + newmcv);			
			corporateact.setNewmcv(newmcv);
			Logging.debug("oldtmcv   ==="+oldtmcv);
			double newtmcv=((Double.parseDouble(oldtmcv) -oldmcv)+(newmcv));
			Logging.debug(" newtMCV "  + newtmcv);
			
			corporateact.setNewtmcv(Double.toString(newtmcv));
    		
    		double diff=cf.diffTMCV(Double.parseDouble(oldtmcv),newtmcv);
    		double newdivisor=cf.newDivisorCorp(Double.parseDouble(oldtmcv),diff,Double.parseDouble(olddivisor));
    		corporateact.setNewdivisor(Double.toString(newdivisor));    		
		}catch(Exception e){
			Logging.error("error=="+e.getMessage());
			}
	}
	
//  This function is called when user wants to see Undo affected index divisor and tmcv
//	used in fi event
	public static void recaltoGetaffect(FixedIncomeCorporate corporateact)
	{
		try{
			String newltp=corporateact.getNewLTP();
    		String newtis=corporateact.getNewTIS();    		
    		String iwf=corporateact.getIwfstk();
    		String oldltp=corporateact.getOldltp();    		
    		String oldtis=corporateact.getOldtis();    		
    		String oldtmcv=corporateact.getTmcv();
    		String olddivisor=corporateact.getDivisor();
    //		CFormula cf = new CFormula();
    		CFormula cf = ConnectInit.getCFormula();	
    		double oldmcv=  cf.calMarketCap(Double.parseDouble(oldltp),Long.parseLong(corporateact.getMark_lot()),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(oldtis),Double.parseDouble(iwf));
			Logging.debug("  OLD MCV "  + oldmcv);		
			double newmcv= cf.calMarketCap(Double.parseDouble(newltp),Long.parseLong(corporateact.getMark_lot()),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(newtis),Double.parseDouble(iwf));
			corporateact.setNewmcv(newmcv);
			Logging.debug("new mcv==="+newmcv);
			Logging.debug("old tmcv==="+oldtmcv);
			Logging.debug("old olddivisor==="+olddivisor);
			//in undo
			double newtmcv=((Double.parseDouble(oldtmcv) -newmcv)+(oldmcv));
			
			Logging.debug("new tmcv==="+newtmcv);
    		corporateact.setNewtmcv(Double.toString(newtmcv));
    		
    		double diff=cf.diffTMCV(Double.parseDouble(oldtmcv),newtmcv);    		
    		
    		double newdivisor=cf.RecalnewDivisorCorp(Double.parseDouble(oldtmcv),diff,Double.parseDouble(olddivisor));    		
    		corporateact.setNewdivisor(Double.toString(newdivisor));
    		
		}catch(Exception e){
			//e.printStackTrace();
			Logging.error("ERROR ="+e.getMessage());
			}
	}	
	
	
	
	public static void actionOnApply(FixedIncomeCorporate corporate){		
		//String  query=connect.queries.getProperty("detail_stock_master");
 	    String ratio =corporate.getRatio1()+":"+corporate.getRatio2();			 
		try{			
			//PreparedStatement stmt = con.prepareStatement(query);
		//	stmt.setString(1,corporate.getStid());
		//	ResultSet rs = stmt.executeQuery();			
		//	rs.next();							
		//	String tis=rs.getString("tis");
			corporate.setOldtis(corporate.getTis());
			String tis=corporate.getOldtis();
		//	rs.close();
			String ltp=corporate.getClose();
			Logging.debug("close in apply="+corporate.getClose());
			corporate.setOldltp(ltp);		
			String corp=corporate.getCorpid().trim();			
			if(corp.equals("cashdividend"))
				cashDividendAction(ltp,tis,corporate.getAmt(),corporate);
			/* do later
			if(corp.equals("stockdividend/bonus"))
				stockDividendAction(ltp,tis,ratio,corporate);
			if(corp.equals("specialdividend"))
				spinAction(ltp,tis,ratio,corporate.getAmt(),corporate);				
			if(corp.equals("split"))
				splitAction(ltp,tis,ratio,corporate);			
			if(corp.equals("reversesplit"))
				splitAction(ltp,tis,ratio,corporate);
			if(corp.equals("rightsoffering"))
				rightsOfferAction(ltp,tis,ratio,corporate.getAmt(),corporate);
			if(corp.equals("sharesbuyback"))
				repurchaseAction(ltp,tis,corporate.getAmt(),corporate.getShare(),corporate);
			if(corp.equals("spin-off"))
			{
				if(ratio.equals(":"))
				{
					ratio =1+":"+1;
					corporate.setRatio1("1");
					corporate.setRatio2("1");
				}				
				spinAction(ltp,tis,ratio,corporate.getAmt(),corporate);
			}
			if(corp.equals("warrantconversion"))
				warrantAction(ltp,tis,ratio,corporate.getShare(),corporate);
			if((corp.equals("shareissuance"))|(corp.equals("adrissue")))
				shareissueAction(ltp,tis,corporate.getShare(),corporate);
			if(corp.equals("capitalreduce"))
				reducecapAction(ltp,tis,corporate.getShare(),corporate);			*/
			CFormula cf = ConnectInit.getCFormula();	
    //		CFormula cf = new CFormula();    		
			double newmcv= cf.calMarketCap(Double.parseDouble(corporate.getNewLTP()),Long.parseLong(corporate.getMark_lot()),1,Long.parseLong(corporate.getNewTIS()),Double.parseDouble(corporate.getIwfstk()));
			corporate.setNewmcv(newmcv);
		}catch(Exception e){
			Logging.error("error="+e.getMessage());
		}
	}
	public static void reCalOnApply(FixedIncomeCorporate corporate){
		try{
		    corporate.setOldtis(corporate.getTis());
		    String tis=corporate.getOldtis();
			String ltp=corporate.getClose();
			corporate.setOldltp(ltp);
			String ratio =corporate.getRatio1()+":"+corporate.getRatio2();
			String corp=corporate.getCorpid().trim();			
			
			if(corp.equals("cashdividend"))
				cashDividendAction(ltp,tis,corporate.getAmt(),corporate);
			/*  do later
			if(corp.equals("stockdividend/bonus"))
				RecalstockDividendAction(ltp,tis,ratio,corporate);
			if(corp.equals("specialdividend"))
			    reCalSpin(ltp,tis,ratio,corporate.getAmt(),corporate);				
			if(corp.equals("split"))
				recalsplitAction(ltp,tis,ratio,corporate);			
			if(corp.equals("reversesplit"))
				recalsplitAction(ltp,tis,ratio,corporate);
			if(corp.equals("rightsoffering"))
				recalrightsOfferAction(ltp,tis,ratio,corporate.getAmt(),corporate);
			if(corp.equals("sharesbuyback"))
				recalrepurchaseAction(ltp,tis,corporate.getAmt(),corporate.getShare(),corporate);
			if(corp.equals("spin-off"))
			{
				if(ratio.equals(":"))
				{
					ratio =1+":"+1;
					corporate.setRatio1("1");
					corporate.setRatio2("1");
				}				
				reCalSpin(ltp,tis,ratio,corporate.getAmt(),corporate);
			}
			if(corp.equals("warrantconversion"))
				recalwarrantAction(ltp,tis,ratio,corporate.getShare(),corporate);
			if((corp.equals("shareissuance"))|(corp.equals("adrissue")))
				recalshareissueAction(ltp,tis,corporate.getShare(),corporate);
			if(corp.equals("capitalreduce"))
				recalreducecapAction(ltp,tis,corporate.getShare(),corporate);		*/	
			CFormula cf = ConnectInit.getCFormula();	
	//		CFormula cf = new CFormula();    		
			double newmcv= cf.calMarketCap(Double.parseDouble(corporate.getNewLTP()),Long.parseLong(corporate.getMark_lot()),1,Long.parseLong(corporate.getNewTIS()),Double.parseDouble(corporate.getIwfstk()));			
			corporate.setNewmcv(newmcv);		
			
		}catch(Exception e){
		    Logging.error("error="+e.getMessage());
		}
	}
	
	public static String[] token1(String newdivisor){
		String str[]= new String[2];
		if(newdivisor!=null){
			StringTokenizer st = new StringTokenizer(newdivisor,"E");
			while (st.hasMoreTokens()) {
				for(int i=0;i<str.length;i++)
				{
					str[i]= st.nextToken();
				}
			} 
		}		
		return str;
	}	
	public static String[] token2(String corpname)
	{
		String str[]= new String[2];
		if(corpname!=null){
			StringTokenizer st = new StringTokenizer(corpname," ");
			while (st.hasMoreTokens()) {
				for(int i=0;i<str.length;i++)
				{
					str[i]= st.nextToken();
				}
			} 
		}		
		return str;
	}
	public static String[] token(String ratio){
		String str[]= new String[2];
		if(ratio!=null){
			StringTokenizer st = new StringTokenizer(ratio,":");
			while (st.hasMoreTokens()) {
				for(int i=0;i<str.length;i++)
				{
					str[i]= st.nextToken();
				}
			} 
		}
		return str;
	}
	public static String[] token3(String ratio){
		String str[]= new String[2];
		if(ratio!=null){
			StringTokenizer st = new StringTokenizer(ratio,"-");
			while (st.hasMoreTokens()) {
				for(int i=0;i<str.length;i++)
				{
					str[i]= st.nextToken();
				}
			} 
		}
		return str;
	}
	public static String[] token4(String value)
	{
		String str[]=new String[10];
		try{
		if(value!=null){
			StringTokenizer st = new StringTokenizer(value,",");
			int val=st.countTokens();			
			while (st.hasMoreTokens()) {
				for(int i=0;i<val;i++)
				{
					str[i]= st.nextToken();					
				}
			} 
		}
		}catch(Exception e){
			Logging.error("Error=="+e.getMessage());
			}
		return str;
	}
	public static void reducecapAction(String oldLtp,String oldTis,String newTis,FixedIncomeCorporate corporateact)
	{
	//	CFormula cf = new CFormula();
		CFormula cf = ConnectInit.getCFormula();
		long tisnew;
		double ltpnew=Double.parseDouble(oldLtp);
		tisnew=cf.newTISReduce(Long.parseLong(oldTis),Long.parseLong(newTis));
		String newtis=new Long(tisnew).toString();
		String newlt=new Double(ltpnew).toString();		
		long adjust=(tisnew - Long.parseLong(oldTis));
		String adj=new Long(adjust).toString();		
		corporateact.setNewLTP(newlt);
		corporateact.setNewTIS(newtis);
		corporateact.setAdjust(adj);
	}
	public static void recalreducecapAction(String oldLtp,String oldTis,String newTis,FixedIncomeCorporate corporateact)
	{
//		CFormula cf = new CFormula();
		CFormula cf = ConnectInit.getCFormula();
		long tisnew;
		double ltpnew=Double.parseDouble(oldLtp);
		tisnew=cf.newRecalTISReduce(Long.parseLong(oldTis),Long.parseLong(newTis));
		String newtis=new Long(tisnew).toString();
		String newlt=new Double(ltpnew).toString();		
		long adjust=(tisnew - Long.parseLong(oldTis));
		String adj=new Long(adjust).toString();	
		corporateact.setNewLTP(newlt);
		corporateact.setNewTIS(newtis);
		corporateact.setAdjust(adj);
	}
	public static void shareissueAction(String oldLtp,String oldTis,String newTis,FixedIncomeCorporate corporateact)
	{
		Logging.debug("ltp in share iss for his=="+oldLtp);
		CFormula cf = ConnectInit.getCFormula();	
//		CFormula cf = new CFormula();
		long tisnew;
		double ltpnew=Double.parseDouble(oldLtp);
		tisnew=cf.newTISIssue(Long.parseLong(oldTis),Long.parseLong(newTis));
		String newtis=new Long(tisnew).toString();
		String newlt=new Double(ltpnew).toString();		
		long adjust=(tisnew - Long.parseLong(oldTis));
		String adj=new Long(adjust).toString();		
		corporateact.setNewLTP(newlt);
		corporateact.setNewTIS(newtis);
		corporateact.setAdjust(adj);				
	}
	public static void recalshareissueAction(String oldLtp,String oldTis,String newTis,FixedIncomeCorporate corporateact)
	{
//		CFormula cf = new CFormula();
		CFormula cf = ConnectInit.getCFormula();
		long tisnew;
		double ltpnew=Double.parseDouble(oldLtp);
		tisnew=cf.newRecalTISIssue(Long.parseLong(oldTis),Long.parseLong(newTis));
		String newtis=new Long(tisnew).toString();
		String newlt=new Double(ltpnew).toString();		
		long adjust=(Long.parseLong(oldTis) - tisnew);
		String adj=new Long(adjust).toString();		
		corporateact.setNewLTP(newlt);
		corporateact.setNewTIS(newtis);
		corporateact.setAdjust(adj);				
	}
	
	public static void stockDividendAction(String oldLtp,String oldtis,String ratio,FixedIncomeCorporate corporateact){
		String str[]= new String[2];		
		str=token( ratio);		
//		CFormula cf = new CFormula();
		CFormula cf = ConnectInit.getCFormula();
		double ltpnew;
		long tisnew;
		ltpnew=cf.newLTPStkDivi(Double.parseDouble(oldLtp),Double.parseDouble(str[0]),Double.parseDouble(str[1]));
		tisnew=cf.newTISStkDivi(Long.parseLong(oldtis),Double.parseDouble(str[0]),Double.parseDouble(str[1]));		
		String newlt=new Double(ltpnew).toString();
		String newtis=new Long(tisnew).toString();		
		long adjust=(tisnew - Long.parseLong(oldtis));
		String adj=new Long(adjust).toString();		
		corporateact.setNewLTP(newlt);
		corporateact.setNewTIS(newtis);
		corporateact.setAdjust(adj);
	}
	public static void RecalstockDividendAction(String oldLtp,String oldtis,String ratio,FixedIncomeCorporate corporateact){
		String str[]= new String[2];		
		str=token( ratio);
		CFormula cf = ConnectInit.getCFormula();	
//		CFormula cf = new CFormula();
		double ltpnew;
		long tisnew;
		ltpnew=cf.newRecalLTPStkDivi(Double.parseDouble(oldLtp),Double.parseDouble(str[0]),Double.parseDouble(str[1]));
		tisnew=cf.newRecalTISStkDivi(Long.parseLong(oldtis),Double.parseDouble(str[0]),Double.parseDouble(str[1]));

		String newlt=new Double(ltpnew).toString();
		String newtis=new Long(tisnew).toString();		
		long adjust=(Long.parseLong(oldtis) - tisnew );
		String adj=new Long(adjust).toString();

		corporateact.setNewLTP(newlt);
		corporateact.setNewTIS(newtis);
		corporateact.setAdjust(adj);
	}
	public static void cashDividendAction(String oldltp,String oldtis,String amt,FixedIncomeCorporate corporateact){
	//	CFormula cf = new CFormula();		
		CFormula cf = ConnectInit.getCFormula();
		double ltpnew;
		ltpnew=cf.newLTPCashDivi(Double.parseDouble(oldltp),Double.parseDouble(amt));
		String newlt=new Double(ltpnew).toString();		
		String newtis=oldtis;		
		long adjust=(Long.parseLong(newtis) - Long.parseLong(oldtis));
		String adj=new Long(adjust).toString();
		corporateact.setNewLTP(newlt);
		corporateact.setNewTIS(newtis);
		corporateact.setAdjust(adj);			
	}
	
	public static void splitAction(String oldLtp,String oldtis,String ratio,FixedIncomeCorporate corporateact){
		String str[]= new String[2];
		str=token( ratio);
		CFormula cf = ConnectInit.getCFormula();	
//		CFormula cf = new CFormula();
		double ltpnew;
		long tisnew;
		ltpnew=cf.newLTPSplitRev(Double.parseDouble(oldLtp),Double.parseDouble(str[0]),Double.parseDouble(str[1]));
		tisnew=cf.newTISSplitRev(Long.parseLong(oldtis),Double.parseDouble(str[0]),Double.parseDouble(str[1]));
		String newlt=new Double(ltpnew).toString();
		String newtis=new Long(tisnew).toString();
		long adjust=(tisnew - Long.parseLong(oldtis));
		String adj=new Long(adjust).toString();
		double nface=cf.newLTPSplitRev(Double.parseDouble(corporateact.getFace()),Double.parseDouble(str[0]),Double.parseDouble(str[1]));
		String newface=new Double(nface).toString();
		corporateact.setNewFace(newface);
		corporateact.setNewLTP(newlt);
		corporateact.setNewTIS(newtis);
		corporateact.setAdjust(adj);
		int i[] = {3,2,1}; 
		Object a[] = new Object[3]; 
		for(int j=0;j<a.length;j++) a[j] = i; 
		
	}
	public static void recalsplitAction(String oldLtp,String oldtis,String ratio,FixedIncomeCorporate corporateact){
		String str[]= new String[2];		
		str=token( ratio);
		CFormula cf = ConnectInit.getCFormula();	
//		CFormula cf = new CFormula();
		double ltpnew;
		long tisnew;
		ltpnew=cf.newRecalLTPSplitRev(Double.parseDouble(oldLtp),Double.parseDouble(str[0]),Double.parseDouble(str[1]));
		tisnew=cf.newRecalTISSplitRev(Long.parseLong(oldtis),Double.parseDouble(str[0]),Double.parseDouble(str[1]));
		
		String newlt=new Double(ltpnew).toString();
		String newtis=new Long(tisnew).toString();
		long adjust=(Long.parseLong(oldtis) - tisnew);
		String adj=new Long(adjust).toString();
		double nface=cf.newRecalLTPSplitRev(Double.parseDouble(corporateact.getFace()),Double.parseDouble(str[0]),Double.parseDouble(str[1]));
		String newface=new Double(nface).toString();
		corporateact.setNewFace(newface);
		corporateact.setNewLTP(newlt);
		corporateact.setNewTIS(newtis);
		corporateact.setAdjust(adj);
	}
	public static void rightsOfferAction(String oldLtp,String oldtis,String ratio,String amount ,FixedIncomeCorporate corporateact){
		String str[]= new String[2];
		str=token( ratio);
		CFormula cf = ConnectInit.getCFormula();
	//	CFormula cf = new CFormula();
		double ltpnew;
		long tisnew;
		ltpnew=cf.newLTPRightsOff(Double.parseDouble(oldLtp),Double.parseDouble(str[0]),Double.parseDouble(str[1]),Double.parseDouble(amount));
		tisnew=cf.newTISRightsOff(Long.parseLong(oldtis),Double.parseDouble(str[0]),Double.parseDouble(str[1]));
		
		String newlt=new Double(ltpnew).toString();
		String newtis=new Long(tisnew).toString();
		long adjust=(tisnew - Long.parseLong(oldtis));
		String adj=new Long(adjust).toString();
		corporateact.setNewLTP(newlt);
		corporateact.setNewTIS(newtis);
		corporateact.setAdjust(adj);
	}
	public static void recalrightsOfferAction(String oldLtp,String oldtis,String ratio,String amount ,FixedIncomeCorporate corporateact){
		String str[]= new String[2];
		str=token( ratio);
		CFormula cf = ConnectInit.getCFormula();	
//		CFormula cf = new CFormula();
		double ltpnew;
		long tisnew;
		ltpnew=cf.newRecalLTPRightsOff(Double.parseDouble(oldLtp),Double.parseDouble(str[0]),Double.parseDouble(str[1]),Double.parseDouble(amount));
		tisnew=cf.newRecalTISRightsOff(Long.parseLong(oldtis),Double.parseDouble(str[0]),Double.parseDouble(str[1]));
		
		String newlt=new Double(ltpnew).toString();
		String newtis=new Long(tisnew).toString();
		long adjust=(Long.parseLong(oldtis) - tisnew);
		String adj=new Long(adjust).toString();
		corporateact.setNewLTP(newlt);
		corporateact.setNewTIS(newtis);
		corporateact.setAdjust(adj);
	}
	public static void repurchaseAction(String oldLtp,String oldtis,String amount ,String percent,FixedIncomeCorporate corporateact){
//		CFormula cf = new CFormula();
		CFormula cf = ConnectInit.getCFormula();
		double ltpnew;
		long tisnew;
		
		ltpnew=cf.newLTPRepurchase(Double.parseDouble(oldLtp),Long.parseLong(oldtis),Long.parseLong(percent),Double.parseDouble(amount));
		tisnew=cf.newTISRepurchase(Long.parseLong(oldtis),Long.parseLong(percent));
		
		String newlt=new Double(ltpnew).toString();
		String newtis=new Long(tisnew).toString();
		long adjust=(tisnew - Long.parseLong(oldtis));
		String adj=new Long(adjust).toString();
		corporateact.setNewLTP(newlt);
		corporateact.setNewTIS(newtis);
		corporateact.setAdjust(adj);
	} 
	public static void recalrepurchaseAction(String oldLtp,String oldtis,String amount ,String percent,FixedIncomeCorporate corporateact){
	//	CFormula cf = new CFormula();
		CFormula cf = ConnectInit.getCFormula();
		double ltpnew;
		long tisnew;
		
		ltpnew=cf.newRecalLTPRepurchase(Double.parseDouble(oldLtp),Long.parseLong(oldtis),Long.parseLong(percent),Double.parseDouble(amount));
		tisnew=cf.newRecalTISRepurchase(Long.parseLong(oldtis),Long.parseLong(percent));
		
		String newlt=new Double(ltpnew).toString();
		String newtis=new Long(tisnew).toString();
		long adjust=(tisnew - Long.parseLong(oldtis));
		String adj=new Long(adjust).toString();
		corporateact.setNewLTP(newlt);
		corporateact.setNewTIS(newtis);
		corporateact.setAdjust(adj);
	} 
	public static void warrantAction(String oldLtp,String oldtis,String ratio,String percent ,FixedIncomeCorporate corporateact){
		String str[]= new String[2];
		str=token( ratio);
		CFormula cf = ConnectInit.getCFormula();	
//		CFormula cf = new CFormula();
		long tisnew;
		long value = cf.noOSharesFWar(Long.parseLong(str[0]),Long.parseLong(str[1]),Long.parseLong(percent));
		tisnew=cf.newTISWarrantCon(Long.parseLong(oldtis),value);
		String newlt=oldLtp;
		String newtis=new Long(tisnew).toString();
		long adjust=(tisnew - Long.parseLong(oldtis));
		String adj=new Long(adjust).toString();
		corporateact.setNewLTP(newlt);
		corporateact.setNewTIS(newtis);
		corporateact.setAdjust(adj);
	}
	public static void recalwarrantAction(String oldLtp,String oldtis,String ratio,String percent ,FixedIncomeCorporate corporateact){
		String str[]= new String[2];
		str=token( ratio);
		CFormula cf = ConnectInit.getCFormula();	
//		CFormula cf = new CFormula();
		long tisnew;
		long value = cf.noOSharesFWar(Long.parseLong(str[0]),Long.parseLong(str[1]),Long.parseLong(percent));
		tisnew=cf.newRecalTISWarrantCon(Long.parseLong(oldtis),value);
		String newlt=oldLtp;
		String newtis=new Long(tisnew).toString();
		long adjust=(Long.parseLong(oldtis) - tisnew);
		String adj=new Long(adjust).toString();
		corporateact.setNewLTP(newlt);
		corporateact.setNewTIS(newtis);
		corporateact.setAdjust(adj);
	}
	public static void spinAction(String oldLtp,String oldtis,String ratio,String amount ,FixedIncomeCorporate corporateact){
		String str[]= new String[2];	
		str=token( ratio);
		CFormula cf = ConnectInit.getCFormula();
	//	CFormula cf = new CFormula();
		double ltpnew;
		long tisnew;
		ltpnew=cf.newLTPSpinOff(Double.parseDouble(oldLtp),Double.parseDouble(str[0]),Double.parseDouble(str[1]),Double.parseDouble(amount));
		tisnew=cf.newTISSpinOff(Long.parseLong(oldtis),Double.parseDouble(str[0]),Double.parseDouble(str[1]));//Long.parseLong(oldtis);
				
		String newlt=new Double(ltpnew).toString();
		String newtis=new Long(tisnew).toString();
		long adjust=tisnew - Long.parseLong(oldtis);
		String adj=new Long(adjust).toString();
		
		corporateact.setNewLTP(newlt);		
		corporateact.setNewTIS(newtis);		
		corporateact.setAdjust(adj);	
		
	}
	public static void reCalSpin(String oldLtp,String oldtis,String ratio,String amount ,FixedIncomeCorporate corporateact)
	{
	    String str[]= new String[2];		
		str=token( ratio);
		CFormula cf = ConnectInit.getCFormula();	
//		CFormula cf = new CFormula();
		double ltpnew;
		long tisnew;
		ltpnew=cf.newRecalLTPSpinOff(Double.parseDouble(oldLtp),Double.parseDouble(str[0]),Double.parseDouble(str[1]),Double.parseDouble(amount));
		tisnew=cf.newRecalTISSpinOff(Long.parseLong(oldtis),Double.parseDouble(str[0]),Double.parseDouble(str[1]));//Long.parseLong(oldtis);   
				
		String newlt=new Double(ltpnew).toString();
		String newtis=new Long(tisnew).toString();
		long adjust=tisnew - Long.parseLong(oldtis); 
		String adj=new Long(adjust).toString();
		
		corporateact.setNewLTP(newlt);		
		corporateact.setNewTIS(newtis);		
		corporateact.setAdjust(adj);
	}
	public static String getcorpname(Connection con,String query,String id)
	{
		ResultSet rs=null;
		String name=null;
		try{
		PreparedStatement stmt = con.prepareStatement(query);		
		stmt.setInt(1,Integer.parseInt(id));
		rs = stmt.executeQuery();
		if(rs!=null && rs.next())
			name=rs.getString("cam_shortname");
		}catch(SQLException e1){
			Logging.error("ERROR :" + e1.getMessage());
		}		
		return name;
	}
	public static void recalIndex(Connection con,Connect connect,FixedIncomeCorporate corporateact)
	{
		try{
			//queries
			String query=ConnectInit.queries.getProperty("select_stock_price_detail");
			String stk_qry=ConnectInit.queries.getProperty("get_undo_close_value");//get stock closing value for respective date
			String ind_com_query=ConnectInit.queries.getProperty("get_composition_of_parent");
			String stk_query=ConnectInit.queries.getProperty("detail_stock_master");
			
			
			
//as this method is used both for historic and general CA, so there should be date comparison
			String dt=UpdateCorp.accept_date();   //get the current date
			String apply=corporateact.getApply_date();
			int chk_dt=FixedIncomeComputeIndexForm.CompareDate(apply,dt);	//check for the current date and user's entered date
			
			ResultSet rs1=null;
			if(chk_dt==0)
			{
				String query1=ConnectInit.queries.getProperty("get_latest_index_value_index_wise");
				rs1=FixedIncomeListTypeClass1.getResult12(con,query1,corporateact.getI_index());
			}
			else
			{
				String query1=ConnectInit.queries.getProperty("get_undo_index_close");
				rs1=FixedIncomeListTypeClass1.getResult_apply(con,query1,corporateact.getI_index(),corporateact.getApply_date());
			}
	 		rs1.next();
			corporateact.setTmcv(rs1.getString("tmcv"));
			corporateact.setDivisor(rs1.getString("divisor"));
			String index_val=rs1.getString("index_closing_value");
			rs1.close();		
			
			Hashtable hash1=corporateact.getHash1();			
			Hashtable hash2=corporateact.getHash2();			
			Hashtable data2=corporateact.getHash12();			
			Hashtable stock_error=corporateact.getHash_stock_error();
			stock_error.clear();
			corporateact.setHash_stock_error(stock_error);
			stock_error=corporateact.getHash_stock_error();
			
			Hashtable data_error=corporateact.getHash_error();
			ResultSet rs=FixedIncomeListTypeClass1.getResult1(con,ind_com_query,corporateact.getI_index());
			double tmcv=0.0,count=0;
	//		CFormula cf=new CFormula();
			CFormula cf = ConnectInit.getCFormula();
			while(rs.next())
			{				
				count++;
				String sid=rs.getString("stock_id");				
				String iwf=rs.getString("iwf");				
				boolean chk_hash=hash1.containsKey(sid);				
				boolean close_val=true;
				double mcv=0.0;
				//get currency exchange value
				String apply1=corporateact.getApply_date();
				Logging.debug("apply date from line no  632 of actioncorp  "+apply1);

				FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,corporateact.getI_index(),sid);
				ResultSet rs2=null;
				if(chk_dt==0)
					rs2=FixedIncomeListTypeClass1.getResult12(con,query,sid);
				else
					rs2=FixedIncomeListTypeClass1.getResult_apply(con,stk_qry,sid,corporateact.getApply_date());				
			 	close_val=rs2.next();
			 	
				if(chk_hash==false)
				{
					boolean chk_hash2=hash2.containsKey(sid);
					if(chk_hash2==true)
					{
						//------------Change IWF-----						
							 	
					 	if(close_val==true)
					 	{			 		
					 		String close=rs2.getString("adjusted_price");
					 		if(close==null)
					 			close=rs2.getString("stock_closing_value");
					 		if(close==null)
					 		{
					 			rs2.close();					 			
					 			data_error.put(new String(sid),new String(sid));
					 		}
					 		else
					 		{
					 			if(close.equals("0"))
					 			{
					 				rs2.close();					 			
						 			data_error.put(new String(sid),new String(sid));
					 			}
						 		else
						 		{					 			
						 			rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,sid);
									rs1.next();
									String tis=rs1.getString("tis");
									String ml=rs1.getString("market_lot");
									rs1.close();
									
									Object obj=null;
									for(Enumeration enumm =data2.keys();enumm.hasMoreElements();)
									{
										String val=(String)enumm.nextElement();
										if(val.equals(sid))
										{
											obj = data2.get(val);	
											break;
										}
									}
									
									iwf=obj.toString();
									mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));																
									if(tmcv==0.0)
										tmcv=mcv;
									else
										tmcv=tmcv+mcv;
						 		}
						 	}
					 	}
					 	else
					 		data_error.put(new String(sid),new String(sid));
					}
					else
					{									 	
					 	if(close_val==true)
					 	{			 		
					 		String close=rs2.getString("adjusted_price");
					 		if(close==null)
					 			close=rs2.getString("stock_closing_value");
					 		if(close==null)
					 		{
					 			rs2.close();
					 			stock_error.put(new String(sid),new String(sid));
					 		}
					 		else
					 		{
					 			if(close.equals("0"))
					 			{
					 				rs2.close();					 			
						 			stock_error.put(new String(sid),new String(sid));
					 			}
						 		else
						 		{				 		
							 		rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,sid);
									rs1.next();
									String tis=rs1.getString("tis");
									String ml=rs1.getString("market_lot");
									rs1.close();
									
									mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));								
									
									if(tmcv==0.0)
										tmcv=mcv;
									else
										tmcv=tmcv+mcv;								
						 		}
						 	}
					 	}				 	
					 	else
					 		stock_error.put(new String(sid),new String(sid));
					}
				}
				if(chk_hash==true)
				{
					//----------Deletion of stock---			
					
				 	if(close_val==false)
				 	{
				 		data_error.put(new Integer(sid).toString(),new String(sid));
				 	}
				 	if(close_val==true)
				 	{			 		
				 		String close=rs2.getString("adjusted_price");
				 		if(close==null)
				 			close=rs2.getString("stock_closing_value");
				 		if(close==null)
				 			data_error.put(new String(sid),new String(sid));
				 		else
				 		{
				 			if(close.equals("0"))
				 				data_error.put(new String(sid),new String(sid));				 				
					 		else
					 		{
						 		rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,sid);
								rs1.next();
								String tis=rs1.getString("tis");
								String ml=rs1.getString("market_lot");
								rs1.close();
					 		}
					 	}
				 	}
				}				
			}//while end	
			if(hash2.isEmpty()==false)
			{
				//---Add stock-----
				String qry=ConnectInit.queries.getProperty("index_comp_detail");
				for(Enumeration enumm =hash2.keys();enumm.hasMoreElements();)
				{
					String id=(String)enumm.nextElement();
					ResultSet rs2=FixedIncomeListTypeClass1.getResult_corp(con,qry,corporateact.getI_index(),id);
					boolean chk_rs=rs2.next();
					if(chk_rs==false)
					{
						ResultSet rs3=null;
						if(chk_dt==0)
							rs3=FixedIncomeListTypeClass1.getResult12(con,query,id);
						else
							rs3=FixedIncomeListTypeClass1.getResult_apply(con,stk_qry,id,corporateact.getApply_date());
						
					 	boolean close_val=rs3.next();					 	
					 	if(close_val==false)
					 	{
					 		data_error.put(new Integer(id).toString(),new String(id));
					 	}
					 	if(close_val==true)
					 	{			 		
					 		String close=rs3.getString("adjusted_price");					 		
					 		if(close==null)
					 			close=rs3.getString("stock_closing_value");
					 		if(close==null)
					 			data_error.put(new String(id),new String(id));
					 		else
					 		{
					 			if(close.equals("0"))
					 				data_error.put(new String(id),new String(id));
						 		else
						 		{
							 		rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,id);
									rs1.next();
									String tis=rs1.getString("tis");
									String ml=rs1.getString("market_lot");
									rs1.close();			
									//get currency exchange value
									FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,corporateact.getI_index(),id);
									Object obj1=null;
									
									for(Enumeration enum1 =data2.keys();enum1.hasMoreElements();)
									{
										String val=(String)enum1.nextElement();
										if(val.equals(id))
										{
											obj1 = data2.get(val);						
											break;
										}
									}
									
									String iwf=obj1.toString();								
									double mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));								
									Logging.debug("mcv==="+mcv);
									if(tmcv==0.0)
										tmcv=mcv;
									else
										tmcv=tmcv+mcv;	
						 		}
						 	}
					 	}
					}
				}
			}
			corporateact.setHash_error(data_error);
			corporateact.setHash_stock_error(stock_error);			
			corporateact.setNewTmcv(Double.toString(tmcv));
			double newdivisor=tmcv/Double.parseDouble(index_val);
			corporateact.setNewdivisor(Double.toString(newdivisor));			
		}catch(Exception e){Logging.error("error ="+e.getMessage());}
	}
	
	/* do later
	public static void Hist_apply(FixedIncomeCorporate corp,int flag)
	{	
		try{
			Connect connect = ConnectInit.getConnect();
		    Connection Hist_con =connect.getConnectionForHistTransaction();
			Hist_con.setAutoCommit(false);
			
		    Logging.getDebug("connection in apply is=="+Hist_con);
			if(flag==1)//Index has a closing value for respective date  
			{
				corp.setInd_comp(null);
				applyIndexDetail(Hist_con,connect,corp); //calculate index parameter
				String ind_query=connect.queries.getProperty("select_resp_close");  //get index values for particular date
				check_affect_index(Hist_con,corp,ind_query);
			}
			if(flag==2) //Index has to be Recompute
			{
				try{
					Logging.getDebug("in compute");	
					Logging.getDebug("index is=="+corp.getI_index());
					Logging.getDebug("apply date"+corp.getApply_date());
					String index=corp.getI_index();
					HttpServletRequest request=null;
					CIndexCalculator cind=new CIndexCalculator();
					Logging.getDebug("call compute method");
					//Recompute Index Parameter
					cind.computeIndex(index,"n","y","yes",null,request,corp.getApply_date(),Hist_con);
					
					Logging.getDebug("out of compute");
					corp.setInd_comp(null);
					applyIndexDetail(Hist_con,connect,corp); //calculate index parameter
					String ind_query=connect.queries.getProperty("select_resp_close");  //get index values for particular date
					check_affect_index(Hist_con,corp,ind_query);
				}catch(Exception e){
					Logging.getDebug("Error="+e.getMessage());
					}
			}
			if(flag==0) //Index has to be Recalculated
			{
				corp.setInd_comp("c");			 
				recal_applyIndexDetail(Hist_con,connect,corp);//recalculate new index value
				
//				queries
				String affect_index=connect.queries.getProperty("select_affect_index");			        
		        String child_index=connect.queries.getProperty("select_child_index");
		        
//		      get child indices
				String corp_val=corp.getCorpid();
				Hashtable hash=corp.getHash_affind();
				hash.clear();
				corp.setHash_affind(hash);
				corp.setAffect(null);
				Hashtable hash1=corp.getHash1();	
				for(Enumeration enum1 =hash1.keys();enum1.hasMoreElements();)
				{				
					String id2 =(String)enum1.nextElement();
					Object obj=hash1.get(id2);
					String stid=obj.toString();
					if(corp_val.equals("addstock"))
					{
						FixedIncomeListTypeClass1.affect_index_list(Hist_con,corp,child_index,stid);
					}
					if((corp_val.equals("deletestock"))|(corp_val.equals("changeiwf")))
					{
						FixedIncomeListTypeClass1.affect_index_list(Hist_con,corp,affect_index,stid);
					}
				}
				String ind_query=connect.queries.getProperty("get_undo_index_close");
				check_affect_index(Hist_con,corp,ind_query);
			}
			IndexCom_Action.tmcv_div_adj(corp);
		}catch(Exception e){
			Logging.getDebug("Error ="+e.getMessage());
			}
	} */
	/* do alter
	public static void Hist_applyindex(FixedIncomeCorporate corp,int flag)
	{
		try{
			Connect connect = ConnectInit.getConnect();
		    Connection Hist_con =connect.getConnectionForHistTransaction();
			Hist_con.setAutoCommit(false);
			//get child indices
			IndexCom_Action.get_child_indices(Hist_con,connect,corp);			

		    if(flag==1) //index has closing value
		    {
		    	corp.setInd_comp(null);
				applyIndex(Hist_con,connect,corp); //calculate index parameter
		    }
		    if(flag==2) //Index has to be recompute
		    {
				String index=corp.getI_index();
				HttpServletRequest request=null;
				CIndexCalculator cind=new CIndexCalculator();
				Logging.getDebug("call compute method");
				//Recompute Index Parameter
				cind.computeIndex(index,"n","y","yes",null,request,corp.getApply_date(),Hist_con);
				
				corp.setInd_comp(null);
				applyIndex(Hist_con,connect,corp); //calculate index parameter
		    }
		    if(flag==0)  //Index has to be recalculated
		    {
		    	corp.setInd_comp("c");
				recalIndex(Hist_con,connect,corp);	//recalculate index parameter	
		    }
		    IndexCom_Action.tmcv_div_adj(corp);
		}catch(Exception e){
			Logging.getDebug("Error="+e.getMessage());
			}
	}*/
	public static void applyIndex(Connection con,Connect connect,FixedIncomeCorporate corporateact)//,String query,String query1)
	{	
		//queries
		String query=ConnectInit.queries.getProperty("select_stock_price_detail");
		String stk_qry=ConnectInit.queries.getProperty("get_undo_close_value");
		String query1=ConnectInit.queries.getProperty("get_latest_index_value_index_wise");//"select_index_detail");
		String ind_query=ConnectInit.queries.getProperty("select_resp_close");  //get index values for particular date
		String stk_query=ConnectInit.queries.getProperty("detail_stock_master");
		String ind_qry=ConnectInit.queries.getProperty("index_comp_detail");
		
		Hashtable data=corporateact.getHash1();//for deletion
		Hashtable data1=corporateact.getHash2();//for addition
		Hashtable data2=corporateact.getHash12();
		Hashtable data_error=corporateact.getHash_error();
		
		data_error.clear();
//		CFormula cf = new CFormula();
		CFormula cf = ConnectInit.getCFormula();		
		int leng=data.size();
		int leng1=data1.size();
					
		ResultSet rs=null,rs1=null;
		boolean close_val=false;
		double newTmcv_del=0,mcv=0.0,index_close=0.0;
		String divisor=null,tmcv=null,index=null;
	
//as this method is used both for historic and general CA, so there should be date comparison
		String dt=UpdateCorp.accept_date();   //get the current date
		String apply=corporateact.getApply_date();
		int chk_dt=FixedIncomeComputeIndexForm.CompareDate(apply,dt);		//check for the current date and user's entered date					

		int check_val=check_val(corporateact);		
		if(check_val==0)
		{
		try
		{
			if(leng>0)
			{			
				for(Enumeration enum1 =data.keys();enum1.hasMoreElements();)
				{					
				 	String id2 =(String)enum1.nextElement();
				 	if(chk_dt==0)
				 		rs=FixedIncomeListTypeClass1.getResult12(con,query,id2);
				 	else
				 		rs=FixedIncomeListTypeClass1.getResult_apply(con,stk_qry,id2,corporateact.getApply_date());
				 	close_val=rs.next();
				 	if(close_val==false)
				 	{
				 		data_error.put(new Integer(id2).toString(),new String(id2));
				 	}
				 	if(close_val==true)
				 	{			 		
				 		String close=rs.getString("adjusted_price");
				 		if(close==null)
				 			close=rs.getString("stock_closing_value");
				 		if(close==null)
				 		{
				 			data_error.put(new Integer(id2).toString(),new String(id2));
				 		}
				 		else
				 		{
				 			if(close.equals("0"))
				 				data_error.put(new Integer(id2).toString(),new String(id2));
					 		else
					 		{
						 		index=corporateact.getI_index();
						 		if(chk_dt==0)
						 		{				 							 			
						 			rs1=FixedIncomeListTypeClass1.getResult12(con,query1,index);
						 		}
						 		else
						 		{				 			
						 			rs1=FixedIncomeListTypeClass1.getResult_apply(con,ind_query,index,corporateact.getApply_date());
						 		}
						 		rs1.next();
								tmcv=rs1.getString("tmcv");							
								corporateact.setTmcv(tmcv);
								divisor=rs1.getString("divisor");
								index_close=rs1.getDouble("index_closing_value");							
								corporateact.setDivisor(divisor);
								//String tis=rs.getString("tis");
								rs1.close();
								
								rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,id2);
								rs1.next();
								String tis=rs1.getString("tis");
								String ml=rs1.getString("market_lot");
								rs1.close();
								
								//get currency exchange value
								FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,index,id2);
								
								rs1=FixedIncomeListTypeClass1.getResult_corp(con,ind_qry,index,id2);
								rs1.next();
								String iwf=rs1.getString("iwf");
								mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));							
								Logging.debug("mcv===="+mcv); 
								if(newTmcv_del==0)	
									newTmcv_del=Double.parseDouble(tmcv)-mcv;
								else
									newTmcv_del=newTmcv_del-mcv;
					 		}
					 	}
					 }				
				}			
			}			
			if(leng1>0)
			{
				
				double out=corporateact.getOutstanding();				
				for(Enumeration enum1 =data1.keys();enum1.hasMoreElements();)
				{	
					Logging.debug("in addition!");
					String id2 =(String)enum1.nextElement();
					
					if(chk_dt==0)				
						rs=FixedIncomeListTypeClass1.getResult12(con,query,id2);
					else
						rs=FixedIncomeListTypeClass1.getResult_apply(con,stk_qry,id2,corporateact.getApply_date());
					close_val=rs.next();
					if(close_val==false)
					{
						data_error.put(new Integer(id2).toString(),new String(id2));
					}
					if(close_val==true)
					{	
						String close=rs.getString("adjusted_price");
						if(close==null)
							close=rs.getString("stock_closing_value");
						if(close==null)
						{
							data_error.put(new Integer(id2).toString(),new String(id2));
						}
						else
						{
							if(close.equals("0"))
								data_error.put(new Integer(id2).toString(),new String(id2));
							else
							{
								Object obj1=data1.get(id2);
								String cad=obj1.toString();
								
								String cquery=ConnectInit.queries.getProperty("select_rep_cad");
								rs1=FixedIncomeListTypeClass1.getResult1(con,cquery,cad);
								rs1.next();
								String corp_id=rs1.getString("cam_id");
								rs1.close();
								String get_name=ConnectInit.queries.getProperty("get_corp_name");
								rs1=FixedIncomeListTypeClass1.getResult1(con,get_name,corp_id);
								rs1.next();
								String corp_nm=rs1.getString("cam_shortname").toLowerCase().trim();
								rs1.close();
								
								index=corporateact.getI_index();
								if(chk_dt==0)
									rs1=FixedIncomeListTypeClass1.getResult12(con,query1,index);
								else
									rs1=FixedIncomeListTypeClass1.getResult_apply(con,ind_query,index,corporateact.getApply_date());
								rs1.next();
								tmcv=rs1.getString("tmcv");						
								corporateact.setTmcv(tmcv);
								divisor=rs1.getString("divisor");
								corporateact.setDivisor(divisor);
								index_close=rs1.getDouble("index_closing_value");
								rs1.close();
								rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,id2);
								rs1.next();
								String tis=rs1.getString("tis");
								String ml=rs1.getString("market_lot");
								rs1.close();
								
								String iwf=null;
								Object obj=null;
								double mcv1;
								
								for(Enumeration enumm =data2.keys();enumm.hasMoreElements();)
								{
									String val=(String)enumm.nextElement();
									if(val.equals(id2))
									{
										obj = data2.get(val);						
										
									}
								}							
								iwf=obj.toString();
								float iwf_val=Float.parseFloat(iwf);
								
								//get currency exchange value
								FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,index,id2);
								
								if(corp_nm.equals("change iwf"))
								{
									rs1=FixedIncomeListTypeClass1.getResult_corp(con,ind_qry,index,id2);
									rs1.next();
									String val=rs1.getString("iwf");
									double mcv_old=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(val));									
									double mcv_new=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),iwf_val);
									mcv=mcv_new-mcv_old;
									if(newTmcv_del==0)	
										newTmcv_del=Double.parseDouble(tmcv) +mcv;
									else
									newTmcv_del=newTmcv_del +mcv;
								}
								if(corp_nm.equals("add stock"))
								{
										mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));									
										Logging.debug("mcv===="+mcv);
										if(newTmcv_del==0)	
											newTmcv_del=Double.parseDouble(tmcv) +mcv;
										else
										newTmcv_del=newTmcv_del +mcv;
								}
							}
						}
					}//if close_val					
				}
			}			
			String newtmcv1=null;
									
			corporateact.setNewTmcv(Double.toString(newTmcv_del));
				double div=Double.parseDouble(divisor);
				if(div==0.0)
				{
					double newdivisor=(newTmcv_del/index_close);
					corporateact.setNewdivisor(Double.toString(newdivisor));
					
				}
				else
				{
					double diff=cf.diffTMCV(Double.parseDouble(tmcv),newTmcv_del);					
					double newdivisor=cf.newDivisorCorp(Double.parseDouble(tmcv),diff,Double.parseDouble(divisor));			 
					corporateact.setNewdivisor(Double.toString(newdivisor));					
				}
			
			corporateact.setHash_error(data_error);			
		}catch(Exception e)
		{
			Logging.error("ERROR :" + e.getMessage());
		}
		}		
	}
	
	public static void undo_recal_applyIndexDetail(FixedIncomeCorporate corporateact)
	{
		Connect connect = ConnectInit.getConnect();
		Connection con=null;
//		Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		try{
		if(con == null){
			 con = connect.getdbConnection();
		}
		String query1=ConnectInit.queries.getProperty("get_undo_index_close");//"select_index_detail");
		String ind_com_query=ConnectInit.queries.getProperty("get_composition_of_parent");
		//String query=connect.queries.getProperty("select_stock_price_detail");
		String query=ConnectInit.queries.getProperty("get_undo_close_value");
		String stk_query=ConnectInit.queries.getProperty("detail_stock_master");
        String query2=ConnectInit.queries.getProperty("select_rep_cad");					
		try{
	//		CFormula cf=new CFormula();
			CFormula cf = ConnectInit.getCFormula();
			String corp=corporateact.getCorpid();		
			String index=corporateact.getI_index();
			
			ResultSet rs=FixedIncomeListTypeClass1.getResult_apply(con,query1,index,corporateact.getApplied_date());			
			rs.next();
			String tmcv=rs.getString("tmcv");			
			corporateact.setTmcv(tmcv);
			String div=rs.getString("divisor");
			corporateact.setDivisor(div);
			String index_val=rs.getString("index_closing_value");
			rs.close();
			
			Hashtable hash_error=corporateact.getHash_error();
			hash_error.clear();	
			corporateact.setHash_error(hash_error);
			hash_error=corporateact.getHash_error();
			Hashtable hash_stk_error=corporateact.getHash_stock_error();
			hash_stk_error.clear();	
			corporateact.setHash_stock_error(hash_stk_error);
			hash_stk_error=corporateact.getHash_stock_error();					
			Hashtable hash1=corporateact.getHash1();
			String stock=null;			
			double newtmcv=0.0;
			ResultSet rs1=FixedIncomeListTypeClass1.getResult1(con,ind_com_query,corporateact.getI_index());			
			while(rs1.next())
			{
				double mcv=0.0;
				String sid=rs1.getString("stock_id");				
				String iwf=rs1.getString("iwf");
				boolean chk_hash=hash1.containsValue(sid);
				
				boolean close_val=false;
				ResultSet rs2=FixedIncomeListTypeClass1.getResult_apply(con,query,sid,corporateact.getApplied_date());
			 	close_val=rs2.next();			 	
			 	if(close_val==true)
			 	{			 		
			 		String close=rs2.getString("adjusted_price");
			 		if(close==null)
			 			close=rs2.getString("stock_closing_value");
			 		if(close==null)
			 		{
			 			rs2.close();
			 			if(chk_hash==true)
			 				hash_error.put(new String(sid),new String(sid));
			 			else
			 				hash_stk_error.put(new String(sid),new String(sid));
			 		}
			 		else
			 		{
			 			if(close.equals("0"))
			 			{
			 				rs2.close();
				 			if(chk_hash==true)
				 				hash_error.put(new String(sid),new String(sid));
				 			else
				 				hash_stk_error.put(new String(sid),new String(sid));
			 			}
				 		else
				 		{	
				 			//get currency exchange value
				 			FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,index,sid);
				 			
				 			rs=FixedIncomeListTypeClass1.getAffected(con,stk_query,sid);
							rs.next();
							String tis=rs.getString("tis");
							String ml=rs.getString("market_lot");
							if(chk_hash==true)
							{
								if(corp.equals("addstock"))
				 				{				 					
				 					newtmcv = newtmcv;
				 				}
								if(corp.equals("changeiwf"))
								{
									for(Enumeration enum1 =hash1.keys();enum1.hasMoreElements();)
				 					{
				 						String id2 =(String)enum1.nextElement();
				 						String div1[]=ActionCorp.token(id2);
				 						rs=FixedIncomeListTypeClass1.getAffected(con,query2,div1[1]);			
										rs.next();
										String stid=rs.getString("stock_id");
										String val=rs.getString("values");
										if(stid.equals(sid))
			 							{
											mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(val));										
			 							}																		
				 					}
									if(newtmcv==0.0)
										newtmcv=mcv;
									else
										newtmcv=newtmcv+mcv;
								}
							}
							if(chk_hash==false)
							{
								mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));							
								if(newtmcv==0.0)
									newtmcv=mcv;
								else
									newtmcv=newtmcv+mcv;		
							}
							
				 		}
				 	}	
			 	}//close val true
			 	else
			 	{
			 		if(chk_hash==true)
			 			hash_error.put(new String(sid),new String(sid));
			 		else
			 			hash_stk_error.put(new String(sid),new String(sid));
			 	}
			}//while rs1
			if(corp.equals("deletestock"))
			{
				for(Enumeration enum1 =hash1.keys();enum1.hasMoreElements();)
				{				
					String id2 =(String)enum1.nextElement();
					Object obj=hash1.get(id2);
					String stid=obj.toString();
					ResultSet rs2=FixedIncomeListTypeClass1.getResult_apply(con,query,stid,corporateact.getApplied_date());
				 	boolean close_val=rs2.next();			 	
				 	if(close_val==true)
				 	{	
				 		String close=rs2.getString("adjusted_price");
				 		if(close==null)
				 			close=rs2.getString("stock_closing_value");
				 		if(close==null)
				 		{
				 			rs2.close();					 			
				 			hash_error.put(new String(stid),new String(stid));
				 		}
				 		else
				 		{
				 			if(close.equals("0"))
				 			{
				 				rs2.close();					 			
					 			hash_error.put(new String(stid),new String(stid));
				 			}
					 		else
					 		{
					 			rs=FixedIncomeListTypeClass1.getAffected(con,stk_query,stid);
								rs.next();
								String tis=rs.getString("tis");		
								String ml=rs.getString("market_lot");
								String iwf=rs.getString("iwf");
								//get currency exchange value
								FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,index,stid);
					 			double mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));				 			
					 			Logging.debug("mcv==="+mcv);
					 			if(newtmcv==0.0)
					 				newtmcv=mcv;
					 			else
					 				newtmcv=newtmcv+mcv;				 	
					 		}
					 	}
				 	}//close val
				 	else
				 		hash_error.put(new String(stid),new String(stid));
				}
			}//delete stock
			corporateact.setNewTmcv(Double.toString(newtmcv));			
			double newdivisor=newtmcv/Double.parseDouble(index_val);
			corporateact.setNewdivisor(Double.toString(newdivisor));
			
		}catch(Exception e){
			Logging.error("Error="+e.getMessage());
			}
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
			}
		}
	}
	public static void recal_applyIndexDetail(Connection con,Connect connect,FixedIncomeCorporate corporateact)
	{		
		String query=ConnectInit.queries.getProperty("select_stock_price_detail");
		String stk_qry=ConnectInit.queries.getProperty("get_undo_close_value");//get stock closing value for respective date					
        String query2=ConnectInit.queries.getProperty("select_rep_cad");			
        String query3=ConnectInit.queries.getProperty("select_stock_detail");
        String index_comp=ConnectInit.queries.getProperty("select_index_comp");
        String stk_query=ConnectInit.queries.getProperty("detail_stock_master");
        String ind_com_query=ConnectInit.queries.getProperty("get_composition_of_parent");
//as this method is used both for historic and general CA, so there should be date comparison        
		try{
			String butt=corporateact.getButton();
			String corp=corporateact.getCorpid();		
			String index=corporateact.getI_index();
			String dt=UpdateCorp.accept_date();   //get the current date
			String apply=corporateact.getApply_date();
			int chk_dt=FixedIncomeComputeIndexForm.CompareDate(apply,dt);	//check for the current date and user's entered date
			
			ResultSet rs=null;
			if(chk_dt==0)
			{
				String query1=ConnectInit.queries.getProperty("get_latest_index_value_index_wise");//"select_index_detail");
				rs=FixedIncomeListTypeClass1.getResult12(con,query1,index);
			}
			else
			{
				String query1=ConnectInit.queries.getProperty("get_undo_index_close");
				rs=FixedIncomeListTypeClass1.getResult_apply(con,query1,index,corporateact.getApply_date());
			}
			rs.next();
			String tmcv=rs.getString("tmcv");			
			corporateact.setTmcv(tmcv);
			String div=rs.getString("divisor");
			corporateact.setDivisor(div);
			String index_val=rs.getString("index_closing_value");
			rs.close();
			CFormula cf = ConnectInit.getCFormula();	
	//		CFormula cf = new CFormula();
			Hashtable hash_error=corporateact.getHash_error();
			hash_error.clear();	
			corporateact.setHash_error(hash_error);
			hash_error=corporateact.getHash_error();
			
			Hashtable hash_stk_error=corporateact.getHash_stock_error();
			hash_stk_error.clear();	
			corporateact.setHash_stock_error(hash_stk_error);
			hash_stk_error=corporateact.getHash_stock_error();	
			
			Hashtable hash1=corporateact.getHash1();
			Logging.debug("hash1 in start=="+hash1);
			String stock=null;
			Logging.debug("corp act=="+corp);
			double newtmcv=0.0;
			int count=0;
			ResultSet rs1=FixedIncomeListTypeClass1.getResult1(con,ind_com_query,corporateact.getI_index());			
			while(rs1.next())
			{			
				count++;
				Logging.debug("count==="+count);
				double mcv=0.0;
				String sid=rs1.getString("stock_id");			
				Logging.debug("stock id=intially=="+sid);				
				String iwf=rs1.getString("iwf");
				boolean chk_hash=false;
				chk_hash=hash1.containsValue(sid);				
				boolean close_val=false;
				String value=null;
				ResultSet rs2=null;
				if(chk_dt==0)
					rs2=FixedIncomeListTypeClass1.getResult12(con,query,sid);
				else
					rs2=FixedIncomeListTypeClass1.getResult_apply(con,stk_qry,sid,corporateact.getApply_date());
			 	close_val=rs2.next();			 	
			 	if(close_val==true)
			 	{			 		
			 		String close=rs2.getString("adjusted_price");
			 		if(close==null)
			 			close=rs2.getString("stock_closing_value");
			 		
			 		if(close==null)
			 		{
			 			rs2.close();
			 			
			 			Object object=hash_error;
			 			Object object2=hash_stk_error;
			 			
			 			if(chk_hash==true)
			 			{
			 				hash_error.put(new String(sid),new String(sid));
			 			}
			 			if(chk_hash==false)
			 			{			 				
			 				hash_stk_error.put(new String(sid),new String(sid));			 			
			 			}			 			
			 		}
			 		else
			 		{
			 			if(close.equals("0"))
			 			{
			 				rs2.close();
				 			
				 			Object object=hash_error;
				 			Object object2=hash_stk_error;
				 			
				 			if(chk_hash==true)
				 			{
				 				hash_error.put(new String(sid),new String(sid));
				 			}
				 			if(chk_hash==false)
				 			{			 				
				 				hash_stk_error.put(new String(sid),new String(sid));			 			
				 			}	
			 			}
				 		else
				 		{			
				 			//get currency exchange value
				 			FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,index,sid);
				 			
				 			rs=FixedIncomeListTypeClass1.getAffected(con,stk_query,sid);
							rs.next();
							String tis=rs.getString("tis");	
							String ml=rs.getString("market_lot");
							Logging.debug("corp action=="+corp+"chk_hash=="+chk_hash);
				 			if(chk_hash==true)
				 			{
				 				if(corp.equals("deletestock"))
				 				{			 					
				 					newtmcv=newtmcv;
				 				}
				 				if(corp.equals("changeiwf"))
				 				{			 			
				 					for(Enumeration enum1 =hash1.keys();enum1.hasMoreElements();)
				 					{
				 						String id2 =(String)enum1.nextElement();
				 						String div1[]=ActionCorp.token(id2);		 						
				 						Logging.debug("id2 is="+id2+"div is=="+div1[0]+" "+div1[1]);
				 						if(div1[0].equals("true"))
				 						{
				 							rs=FixedIncomeListTypeClass1.getAffected(con,query2,div1[1]);			
											rs.next();
											String stid=rs.getString("stock_id");
											Logging.debug("stid==="+stid);
											String val=rs.getString("values");
				 							if(stid.equals(sid))
				 							{
					 							String nature=corporateact.getNature();
					 							Logging.debug("nature in recal=="+nature);
					 							if(nature!=null)
					 							{
					 								if(nature.equals("n"))
					 								{				 									
														mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(corporateact.getValues()));																		 									
					 								}
													if(nature.equals("o"))
													{	
														corporateact.setValues(val);													
														mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(corporateact.getValues()));													
					 									Logging.debug("value=mac="+mcv);													
													}
					 							}
					 							else
					 								mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(corporateact.getValues()));				 								
					 							break;
				 							}
				 							else
				 								mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(corporateact.getValues()));
				 						}
				 						if(div1[0].equals("false"))
				 						{
				 							mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(corporateact.getValues()));			 							
				 						}
				 					}
				 					if(newtmcv==0.0)
				 						newtmcv=mcv;
				 					else
				 						newtmcv=newtmcv+mcv;			 					
				 				}
				 			}
				 			if(chk_hash==false)
				 			{			 			
								mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));							
								Logging.debug("mcv==="+mcv);
								if(newtmcv==0.0)
									newtmcv=mcv;
								else
									newtmcv=newtmcv+mcv;
				 			}
				 			rs.close();
				 		}
				 	}
				 }
			 	else
			 	{
			 		if(chk_hash==true)
			 			hash_error.put(new String(sid),new String(sid));
			 		if(chk_hash==false)
			 			hash_stk_error.put(new String(sid),new String(sid));
			 	}
			}//while	
			
			if(corp.equals("addstock"))
			{
				Logging.debug("in addstock");
				for(Enumeration enum1 =hash1.keys();enum1.hasMoreElements();)
				{				
					String id2 =(String)enum1.nextElement();
					String div2[]=token(id2);					
					String stid=div2[1];
					ResultSet rs2=null;
					if(chk_dt==0)
						rs2=FixedIncomeListTypeClass1.getResult12(con,query,stid);
					else
						rs2=FixedIncomeListTypeClass1.getResult_apply(con,stk_qry,stid,corporateact.getApply_date());
				 	boolean close_val=rs2.next();			 	
				 	if(close_val==true)
				 	{	
				 		String close=rs2.getString("adjusted_price");
				 		if(close==null)
				 			close=rs2.getString("stock_closing_value");
				 		if(close==null)
				 		{
				 			rs2.close();					 			
				 			hash_error.put(new String(stid),new String(stid));
				 		}
				 		else
				 		{
				 			if(close.equals("0"))
				 			{
				 				rs2.close();					 			
					 			hash_error.put(new String(stid),new String(stid));
				 			}
					 		else
					 		{
					 			rs=FixedIncomeListTypeClass1.getAffected(con,stk_query,stid);
								rs.next();
								String tis=rs.getString("tis");		
								String ml=rs.getString("market_lot");
								String iwf=rs.getString("iwf");
								
								//get currency exchange value
								FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,index,stid);
								
					 			double mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));				 			
					 			Logging.debug("mcv==="+mcv);
					 			if(newtmcv==0.0)
					 				newtmcv=mcv;
					 			else
					 				newtmcv=newtmcv+mcv;				 			
					 		}
					 	}
				 	}
				 	else
				 		hash_error.put(new String(stid),new String(stid));
				}
			}
			
			corporateact.setHash_error(hash_error);
			corporateact.setHash_stock_error(hash_stk_error);			
			corporateact.setNewTmcv(Double.toString(newtmcv));			
			double newdivisor=newtmcv/Double.parseDouble(index_val);
			corporateact.setNewdivisor(Double.toString(newdivisor));
			Logging.debug("iwf aft recal=="+corporateact.getValues());
		}catch(Exception e){
			Logging.error("Error="+e.getMessage());
		}
	}
	public static void undoapplyIndexDetail(FixedIncomeCorporate corporateact)
	{
		Connect connect = ConnectInit.getConnect();
		Connection con=null;
//		Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		try{
			if(con == null){
				 con = connect.getdbConnection();
			}	
	
			String query1=ConnectInit.queries.getProperty("select_resp_close");
			String query=ConnectInit.queries.getProperty("get_undo_close_value");		
	        String affect_index=ConnectInit.queries.getProperty("select_affect_index");
	        String child_index=ConnectInit.queries.getProperty("select_child_index");
	        String query3=ConnectInit.queries.getProperty("select_stock_detail");
	        String index_comp=ConnectInit.queries.getProperty("select_index_comp");        
		try{			
		//	CFormula cf = new CFormula();
			CFormula cf = ConnectInit.getCFormula();
			String corp=corporateact.getCorpid();		
			String index=corporateact.getI_index();
			String date=corporateact.getApplied_date();
			ResultSet rs=FixedIncomeListTypeClass1.getResult_apply(con,query1,index,date);
			rs.next();
			String tmcv=rs.getString("tmcv");						
			String div=rs.getString("divisor");
			String index_close=rs.getString("index_closing_value");						
			rs.close();
			
			Hashtable hash1=corporateact.getHash1();
			Hashtable hash_affect=corporateact.getHash_affind();
			hash_affect.clear();
			corporateact.setHash_affind(hash_affect);	
			
			Hashtable hash_error=corporateact.getHash_error();
			hash_error.clear();	
			corporateact.setHash_error(hash_error);
			hash_error=corporateact.getHash_error();
			
			double mcv=0.0;
			double new_tmcv=0.0;
			boolean check_rs=false;
			String close=null,tis=null,stock=null,value=null,ml=null;			
			for(Enumeration enum1 =hash1.keys();enum1.hasMoreElements();)
			{
				String id2 =(String)enum1.nextElement();				
				String div1[]=token(id2);
				Object obj=hash1.get(id2);				
				stock=obj.toString();				
				rs=FixedIncomeListTypeClass1.getResult_apply(con,query,stock,date);			
				check_rs=rs.next();
				if(check_rs==false)
				{
					hash_error.put(new String(stock),new String(stock)); 													
				}	
				else
				{
					close=rs.getString("adjusted_price");
					if(close==null)
						close=rs.getString("stock_closing_value");
					if(close==null)
					{
						hash_error.put(new String(stock),new String(stock));
					}
					else
					{
						if(close.equals("0"))
							hash_error.put(new String(stock),new String(stock));
						else
						{
							rs.close();
							corporateact.setTmcv(tmcv);
							corporateact.setDivisor(div);	
							corporateact.setStid(stock);
							
							if((corp.equals("addstock"))|(corp.equals("changeiwf")))
								FixedIncomeListTypeClass1.affect_index_list(con,corporateact,affect_index,stock);
							if(corp.equals("deletestock"))						
								FixedIncomeListTypeClass1.affect_index_list(con,corporateact,child_index,stock);
							
							if((corp.equals("addstock"))|(corp.equals("changeiwf")))
							{
								rs=FixedIncomeListTypeClass1.getResult_corp(con,index_comp,index,stock);
							    rs.next();
								value=rs.getString("iwf");	
							}
							if(corp.equals("deletestock"))
							{
								rs=FixedIncomeListTypeClass1.getResult12(con,query3,stock);
								rs.next();
								value=rs.getString("iwf");	
							}
							
							ResultSet rs1=FixedIncomeListTypeClass1.getResult12(con,query3,stock);
							rs1.next();
							tis=rs1.getString("tis");
							ml=rs1.getString("market_lot");
							rs1.close();
							
							//get currency exchange rate
							FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,index,stock);
							
							if(corp.equals("addstock"))
							{
								mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(value));						
								Logging.debug("mcv in add=="+mcv);
								if(new_tmcv==0.0)
									new_tmcv=Double.parseDouble(tmcv)-mcv;
								else
									new_tmcv=new_tmcv-mcv;
							}
							if(corp.equals("deletestock"))
							{
								mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(value));							
								Logging.debug("mcv in del=="+mcv);
								if(new_tmcv==0.0)
									new_tmcv=Double.parseDouble(tmcv)+mcv;
								else
									new_tmcv=new_tmcv+mcv;
							}
							if(corp.equals("changeiwf"))
							{
								double mcv_new=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(value));							
								Logging.debug("mcv in new=="+mcv_new);
								double mcv_old=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(corporateact.getValues()));							
								Logging.debug("mcv in old=="+mcv_old);
								mcv=mcv_new-mcv_old;
								Logging.debug("mcv in iwf=="+mcv);
								if(new_tmcv==0.0)
									new_tmcv=Double.parseDouble(tmcv)-mcv;
								else
									new_tmcv=new_tmcv-mcv;
							}
						}//else close
					}
				}				
			}//for end
			corporateact.setNewTmcv(Double.toString(new_tmcv));
			double divi=Double.parseDouble(div);
			if(divi==0.0)
			{
				double newdivisor=(new_tmcv/Double.parseDouble(index_close));
				corporateact.setNewdivisor(Double.toString(newdivisor));
				
			}
			else
			{
				double diff=cf.diffTMCV(Double.parseDouble(tmcv),new_tmcv);			
				double newdivisor=cf.RecalnewDivisorCorp(Double.parseDouble(tmcv),diff,Double.parseDouble(div));
				corporateact.setNewdivisor(Double.toString(newdivisor));
			}
			corporateact.setHash_error(hash_error);
			check_affect_index(con,corporateact,query1);
		}catch(Exception e){
			Logging.error("Error="+e.getMessage());
		}
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
			}
		}
	}
	public static void applyIndexDetail(Connection con,Connect connect,FixedIncomeCorporate corporateact)
	{	
		Logging.debug("connection after rec=="+con);	
//		CFormula cf=new CFormula();
		CFormula cf = ConnectInit.getCFormula();	
		String query=ConnectInit.queries.getProperty("select_stock_price_detail");
		String stk_qry=ConnectInit.queries.getProperty("get_undo_close_value");
        String query1=ConnectInit.queries.getProperty("get_latest_index_value_index_wise");//"select_index_detail");
        String ind_query=ConnectInit.queries.getProperty("select_resp_close");  //get index values for particular date
        String query2=ConnectInit.queries.getProperty("select_rep_cad");			
        String query3=ConnectInit.queries.getProperty("select_stock_detail");			        
        String affect_index=ConnectInit.queries.getProperty("select_affect_index");			        
        String child_index=ConnectInit.queries.getProperty("select_child_index");
        String index_comp=ConnectInit.queries.getProperty("select_index_comp");
        String stk_query=ConnectInit.queries.getProperty("detail_stock_master");
        
		String corp=corporateact.getCorpid();		
		String index=corporateact.getI_index();
		Hashtable hash_affect=corporateact.getHash_affind();
		hash_affect.clear();
		corporateact.setHash_affind(hash_affect);		
		try
		{			
			//as this method is used both for historic and general CA,so there should be date comparison
			String dt=UpdateCorp.accept_date();   //get the current date
			String apply=corporateact.getApply_date();
			int chk_dt=FixedIncomeComputeIndexForm.CompareDate(apply,dt);		//check for the current date and user's entered date
			
			Hashtable hash_error=corporateact.getHash_error();
			hash_error.clear();	
			corporateact.setHash_error(hash_error);
			hash_error=corporateact.getHash_error();
			ResultSet rs=null;
			if(chk_dt==0)
				rs=FixedIncomeListTypeClass1.getResult12(con,query1,index);
			else
				rs=FixedIncomeListTypeClass1.getResult_apply(con,ind_query,index,corporateact.getApply_date()); //1
			String tmcv=null;
			String div=null;
			String index_close=null;
			
			if(rs!=null && rs.next()){
				 tmcv=rs.getString("tmcv");
				Logging.debug("tmcv in apply=="+tmcv);
				div=rs.getString("divisor");
				Logging.debug("divisor in applyyy== "+div);
				index_close=rs.getString("index_closing_value");
				Logging.debug("index close value=="+index_close);
				rs.close();
			}
			Hashtable hash1=corporateact.getHash1();
			double mcv=0.0;
			double new_tmcv=0.0;
			String close=null,tis=null,ml=null;
			String value=null,stock=null;
			boolean check_rs=false;
			StringBuffer str=new StringBuffer();
			for(Enumeration enum1 =hash1.keys();enum1.hasMoreElements();)
			{				
				String id2 =(String)enum1.nextElement();				
				String div1[]=token(id2);	
				stock=div1[1];
				if(div1[0].equals("false"))
				{
					Logging.debug("-----------in false------------");
					if(chk_dt==0)
						rs=FixedIncomeListTypeClass1.getResult12(con,query,div1[1]);
					else
						rs=FixedIncomeListTypeClass1.getResult_apply(con,stk_qry,div1[1],corporateact.getApply_date());
					check_rs=rs.next();
					if(check_rs==false)
						hash_error.put(new String(div1[1]),new String(div1[1]));
					if(check_rs==true)
					{	
						close=rs.getString("adjusted_price");
						if(close==null)
							close=rs.getString("stock_closing_value");
						if(close==null)
						{
							hash_error.put(new String(div1[1]),new String(div1[1]));
							check_rs=false;
						}
						else
						{
							if(close.equals("0"))
							{
								hash_error.put(new String(div1[1]),new String(div1[1]));
								check_rs=false;
							}
							else
							{
								corporateact.setTmcv(tmcv);
								corporateact.setDivisor(div);	
								corporateact.setStid(div1[1]);
								stock=div1[1];
								if((corp.equals("deletestock"))|(corp.equals("changeiwf")))
									FixedIncomeListTypeClass1.affect_index_list(con,corporateact,affect_index,div1[1]);//2
								if(corp.equals("addstock"))						
									FixedIncomeListTypeClass1.affect_index_list(con,corporateact,child_index,div1[1]);					
								if(rs!=null)						
									rs.close();
								ResultSet rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,div1[1]);
								if(rs1!=null && rs1.next()){
									tis=rs1.getString("tis");
									ml=rs1.getString("market_lot");
									rs1.close();	
								}
								if(corp!=null)
								{
									if(corp.equals("addstock"))
									{								
										rs=FixedIncomeListTypeClass1.getResult12(con,query3,div1[1]);
										rs.next();
										 value=rs.getString("iwf");								 
									}
									if(corp.equals("deletestock"))
									{
										rs=FixedIncomeListTypeClass1.getResult_corp(con,index_comp,index,div1[1]);
									    rs.next();
										value=rs.getString("iwf");
									}								
									if(corp.equals("changeiwf"))
										value=corporateact.getValues();		
								}
							}
						}
					}//check rs true
				}//div false				
				if(div1[0].equals("true"))
				{
					Logging.debug("-----------in true------------");
					rs=FixedIncomeListTypeClass1.getAffected(con,query2,div1[1]);			
					rs.next();
					stock=rs.getString("stock_id");
					String val=rs.getString("values");
					rs.close();
					if(chk_dt==0)
						rs=FixedIncomeListTypeClass1.getResult12(con,query,stock);
					else
						rs=FixedIncomeListTypeClass1.getResult_apply(con,stk_qry,stock,corporateact.getApply_date());
					check_rs=rs.next();
					if(check_rs==false)
					{
						hash_error.put(new String(stock),new String(stock));
					}
					if(check_rs==true)
					{
						close=rs.getString("adjusted_price");
						if(close==null)
							close=rs.getString("stock_closing_value");
						if(close==null)
						{
							hash_error.put(new String(stock),new String(stock));
							check_rs=false;
						}
						else
						{
							if(close.equals("0"))
							{
								hash_error.put(new String(stock),new String(stock));
								check_rs=false;
							}
							else
							{
								corporateact.setTmcv(tmcv);
								corporateact.setDivisor(div);
								if((corp.equals("deletestock"))|(corp.equals("changeiwf")))
									FixedIncomeListTypeClass1.affect_index_list(con,corporateact,affect_index,stock);
								if(corp.equals("addstock"))						
									FixedIncomeListTypeClass1.affect_index_list(con,corporateact,child_index,stock);
								
								ResultSet rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,stock);
								rs1.next();
								tis=rs1.getString("tis");
								ml=rs1.getString("market_lot");
								rs1.close();					
								String nature=corporateact.getNature();						
								if(corp!=null)
								{
									if(corp.equals("addstock"))
									{
										 rs=FixedIncomeListTypeClass1.getResult12(con,query3,stock);
										 rs.next();
										 value=rs.getString("iwf");
									}
									if(corp.equals("deletestock"))
									{								
										rs=FixedIncomeListTypeClass1.getResult_corp(con,index_comp,index,stock);
									    rs.next();
										value=rs.getString("iwf");							
									}	
									if(corp.equals("changeiwf"))
									{
										if(nature!=null)
										{
											if(nature.equals("n"))
												value=corporateact.getValues();
											if(nature.equals("o"))
											{
												value=val;
												corporateact.setValues(val);
											}
										}
										else
											value=corporateact.getValues();
									}
								}
							}
						}
					}	
				}				
				if(check_rs==true)
				{					
					//get currency exchange rate
					FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,index,stock);
					mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(value));					
					Logging.debug("mcv==="+mcv);
					corporateact.setValues(value);
					if(corp!=null)
					{
						if(corp.equals("changeiwf"))
						{
							rs=FixedIncomeListTypeClass1.getResult_corp(con,index_comp,index,stock);
						    rs.next();
							String val=rs.getString("iwf");
							double mcv_old=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(val));							
							double mcv_new=mcv;
							mcv=mcv_new-mcv_old;
							if(new_tmcv==0.0)
								new_tmcv=Double.parseDouble(tmcv)+mcv;
							else
								new_tmcv=new_tmcv+mcv;
						}
						if(corp.equals("addstock"))
						{
							if(new_tmcv==0.0)
								new_tmcv=Double.parseDouble(tmcv)+mcv;
							else
								new_tmcv=new_tmcv+mcv;
						}
						if(corp.equals("deletestock"))
						{
							if(new_tmcv==0.0)
								new_tmcv=Double.parseDouble(tmcv)-mcv;
							else
								new_tmcv=new_tmcv-mcv;
						}
					}
				}
			}												
			corporateact.setNewTmcv(Double.toString(new_tmcv));
			double divi=Double.parseDouble(div);
			if(divi==0.0)
			{
				double newdivisor=(new_tmcv/Double.parseDouble(index_close));
				corporateact.setNewdivisor(Double.toString(newdivisor));				
			}
			else
			{
				double diff=cf.diffTMCV(Double.parseDouble(tmcv),new_tmcv);			
				double newdivisor=cf.newDivisorCorp(Double.parseDouble(tmcv),diff,Double.parseDouble(div));
				corporateact.setNewdivisor(Double.toString(newdivisor));
			}
		
		corporateact.setHash_error(hash_error);			
		check_affect_index(con,corporateact,query1);
		}catch(Exception e)
		{
			Logging.error("ERROR :" + e.getMessage());
		}
	}
	// used in fi event 
	public static void Historic_cal(FixedIncomeCorporate corp)
	{
		String ori_corpid=corp.getCorpid(); //data persistent
		String ori_tis=corp.getTis(); 
		String ori_close=corp.getClose();
		String ori_amt=corp.getAmt();
		String ori_shr=corp.getShare();
		String ori_r1=corp.getRatio1();
		String ori_r2=corp.getRatio2();		
		String ori_iwf=corp.getValues();
		String ori_index=corp.getI_index();
		String ori_ml=corp.getMark_lot();
		
		try{
			Connect connect = ConnectInit.getConnect();
			Connection Hist_con=null;
			if(Hist_con == null){
				 Hist_con =connect.getConnectionForHistTransaction();
			}		

			Hist_con.rollback();
			Hist_con.setAutoCommit(false);
	//		CFormula cf=new CFormula();						
			CFormula cf = ConnectInit.getCFormula();
			String qry=ConnectInit.queries.getProperty("get_cname_type"); //get CA shortname			  
			String stk_qry=ConnectInit.queries.getProperty("fixed_income_get_undo_close_value");//get stock closing value for respective date
			String stk_tis=ConnectInit.queries.getProperty("fixed_income_detail_stock_master");  //get stock tis
			String up_qry=ConnectInit.queries.getProperty("fixed_income_update_index"); //update index composition
			Vector v=corp.getV();			
			if(v.isEmpty()==false)
			{				
				for(Enumeration enumm=v.elements();enumm.hasMoreElements();)
				{
					String id=(String)enumm.nextElement();  //get diary id										
					ResultSet rs=FixedIncomeListTypeClass1.getAffected(Hist_con,qry,id);
					String type=null,fcorp_nm=null,corp_nm=null,date=null;
					boolean isData=false;
					if(rs!=null)
						isData=rs.next();
					/*String type=rs.getString("corporate_action_type");
					String fcorp_nm=null;
					String corp_nm=rs.getString("cam_shortname");
					String date=rs.getString("applied_date"); */
					if(isData){
					 type=rs.getString("corporate_action_type");
					 fcorp_nm=null;
					 corp_nm=rs.getString("cam_shortname");
					 date=rs.getString("applied_date");
					}
					
					//get respective ca name
					StringTokenizer st = new StringTokenizer(corp_nm," ");
					int val=st.countTokens();
					if(val > 1)
					{
						String div[]=token2(corp_nm);
						fcorp_nm=(div[0]+div[1]).toLowerCase().trim();
					}
					else
						fcorp_nm=corp_nm.toLowerCase().trim();
					corp.setCorpid(fcorp_nm);
					String sid=null,index_id=null;
					if(isData){
						corp.setAmt(rs.getString("amount"));
						corp.setShare(rs.getString("values"));
						corp.setRatio1(rs.getString("ratio_for_shares"));
						corp.setRatio2(rs.getString("ratio_shares_offered"));
						corp.setValues(rs.getString("values"));
						index_id=rs.getString("index_id");
						sid=rs.getString("fi_stock_id"); //get stock id
						corp.setStid(sid);
						corp.setStkid(sid);
					}
					if(!(corp_nm.equals("change indcurr")))
					{
						corp.setApplied_date(date);						
						ResultSet rs1=FixedIncomeListTypeClass1.getResult_apply(Hist_con,stk_qry,sid,date); //get stock closing value
						rs1.next();
						String sclose=rs1.getString("adjusted_price");
						if(sclose==null)
							sclose=rs1.getString("stock_close_value");
						corp.setClose(sclose);
						rs1.close();
						
						rs1=FixedIncomeListTypeClass1.getAffected(Hist_con,stk_tis,sid); //get stock tis  
						rs1.next();
						String tis=rs1.getString("tis");
						Logging.debug("tis in qry cond=="+tis);
						corp.setTis(tis);
						corp.setMark_lot(rs1.getString("market_lot"));
						corp.setIwfstk(rs1.getString("iwf"));
						corp.setFace(rs1.getString("face_value"));
						rs1.close();	
					}
					/*  do later index event
					if(type.equals("index event"))
					{
						if(corp_nm.equals("change indcurr"))
						{
							corp.setCad_id(id);							
							undo_currind(Hist_con,connect,corp);
						}
						else
						{
							corp.setCad_id(id);
							Hashtable affect=corp.getHash_affind();
							affect.clear();
							corp.setHash_affind(affect);
							
							//recalculate parent index old values
							undo_ind(Hist_con,connect,corp,sid,index_id,id,corp_nm,null);
						
							//recalculate child index old values
							affect=corp.getHash_affind();
							Logging.getDebug("ind aff=="+affect);
							if(affect.isEmpty()==false)
							{
								for(Enumeration enum1=affect.keys();enum1.hasMoreElements();)
								{
									String ch_id=(String)enum1.nextElement();
									String div1[]=token2(ch_id);
									String child_id=div1[1];
									undo_ind(Hist_con,connect,corp,sid,child_id,id,corp_nm,"1");
								}
							}
						}
					}*/
					if(type.equals("fixedincome event"))
					{	
						//recalculate old values
						if(corp_nm.equals("cash dividend"))
						{						
							corp.setNewTIS(corp.getTis());					    
						    corp.setAdjust("0");
						    corp.setNewLTP(corp.getClose());						    
						    double newmcv= cf.calMarketCap(Double.parseDouble(corp.getNewLTP()),Long.parseLong(corp.getMark_lot()),1,Long.parseLong(corp.getNewTIS()),Double.parseDouble(corp.getIwfstk()));
						    corp.setNewmcv(newmcv);
						}
						else
							reCalOnApply(corp);

						//temporary update stock master
						String face=corp.getNewFace();
						if(face==null)
							face=corp.getFace();						
						String query=ConnectInit.queries.getProperty("fixed_income_update_tis_stockmaster");
						UpdateCorp.updatestkmaster(Hist_con,query,corp,face);
						
						//temporary update stock price daily
//						get stock_price_daily id
						String undo_query=ConnectInit.queries.getProperty("fixed_income_get_undo_close_value");
						
						//update stock price daily for the particular date
						query =ConnectInit.queries.getProperty("fixed_income_update_undo_stock_price");
					 	UpdateCorp.update_undo_stkprice(Hist_con,undo_query,query,corp);
					 /* do later
					 	if((!(corp_nm.equals("stock dividend/bonus")))|(!(corp_nm.equals("cash dividend"))))
					 	{
							String ind_qry=connect.queries.getProperty("select_index_composition");
							ResultSet rs1=FixedIncomeListTypeClass1.getAffected(Hist_con,ind_qry,sid);
							while(rs1.next())
							{
								String index=rs1.getString("index_id");
								Logging.getDebug("sotck aff=="+index);
								//get currency exchange value and calculate MCV								
								NCorp_Action.get_currency(Hist_con,connect,corp,index,sid);								
								
								double mcv=cf.calMarketCap(Double.parseDouble(corp.getNewLTP()),Long.parseLong(corp.getMark_lot()),Double.parseDouble(corp.getCurr_val()),Long.parseLong(corp.getNewTIS()),Double.parseDouble(corp.getIwfstk()));
								corp.setNewmcv(mcv);
								
								//temporary update index composition
								UpdateCorp.update_hist_index(Hist_con,up_qry,rs1,corp);
								
								String query1=connect.queries.getProperty("get_undo_index_close");  //get index values for particular date
								ResultSet rs2=FixedIncomeListTypeClass1.getResult_apply(Hist_con,query1,index,date);
								rs2.next();
								corp.setDivisor(rs2.getString("divisor"));
								corp.setTmcv(rs2.getString("tmcv"));
								rs2.close();
								recaltoGetaffect(corp);						
								
								//temporary update index value daily
								corp.setI_index(index);
								corp.setNewTmcv(corp.getNewtmcv());
								query =connect.queries.getProperty("update_undo_ind_close");						 	
							 	UpdateCorp.update_undo_index_daily(Hist_con,query1,query,corp);
							}
					 	}	*/
					 	Logging.debug("all updation completed");
					}//stock event									
					
				}//for vector
			}//vector not empty	
						
	//calculate value for selected CA
			//get entered data
			corp.setAmt(ori_amt);
			corp.setShare(ori_shr);
			corp.setRatio1(ori_r1);
			corp.setRatio2(ori_r2);
			corp.setCorpid(ori_corpid);
			corp.setStid(corp.getS_stock());
			String sid=corp.getStid();
			corp.setValues(ori_iwf);
			Logging.debug("iwf in hist act=="+corp.getValues());
			corp.setI_index(ori_index);
			corp.setMark_lot(ori_ml);
			String date=corp.getApply_date();
			
			if(corp.getR_type().equals("fixedincome event"))
			{
				ResultSet rs1=FixedIncomeListTypeClass1.getResult_apply(Hist_con,stk_qry,sid,date); //get stock closing value
				rs1.next();
				String sclose=rs1.getString("adjusted_price");
				if(sclose==null)
					sclose=rs1.getString("stock_close_value");
				corp.setClose(sclose);
				rs1.close();
				
				rs1=FixedIncomeListTypeClass1.getAffected(Hist_con,stk_tis,sid); //get stock tis  
				rs1.next();
				String tis=rs1.getString("tis");
				corp.setTis(tis);
				corp.setFace(rs1.getString("face_value"));
				rs1.close();	
				
				FixedIncomeNCorp_Action.CalApply(Hist_con,connect,corp);//calculate new value
			}
			/*  do later
			if(corp.getR_type().equals("index event"))
			{
				String corp_nm=corp.getCorpid();
				if(corp_nm.equals("changeindcurr"))
				{
					cal_curr_ind(Hist_con,connect,corp,corp.getI_index());
					String chk=corp.getInd_div();
					if(chk.equals("t"))
					{
						corp.setNewdivisor(corp.getDivisor());
					}
					//get child indices
					String query=connect.queries.getProperty("resp_child_indcurr");
					FixedIncomeListTypeClass1.affect_child_list(Hist_con,corp,query);
					IndexCA_Action.tmcv_div_adj(corp);
				}												
			}*/
			/*if(corp.getR_type().equals("index event diary"))
			{				
				hist_applyIndex(Hist_con,connect,corp);				
			}	*/		
		}catch(Exception e){
			Logging.error("ERROR  :" + e.getMessage());
			}
		corp.setCorpid(ori_corpid);
	}
	public static void hist_affect_cal(FixedIncomeCorporate corp,String affect)
	{
		try{
			Connect connect = ConnectInit.getConnect();			
			Connection Hist_con=connect.getConnectionForHistTransaction();
			Logging.debug("coonection in affect==="+Hist_con);
			Logging.debug("connection =="+Hist_con.isClosed());
			String query1=ConnectInit.queries.getProperty("get_undo_index_close");  //get index values for particular date			
			ResultSet rs=FixedIncomeListTypeClass1.getResult_apply(Hist_con,query1,affect,corp.getApply_date());
			rs.next();
			String tmcv=rs.getString("tmcv");		
			Logging.debug("tmcv in affect=="+tmcv);
			corp.setTmcv(tmcv);
			String div=rs.getString("divisor");	
			corp.setDivisor(div);
			String index_val=rs.getString("index_closing_value");
			corp.setIndexval(index_val);
			rs.close();
			
			String corp_name=corp.getCorpid().trim();
			if(corp_name.equals("cashdividend"))
			{
//				check from system configuration table whether to adjust price or not for cash dividend
				String query=ConnectInit.queries.getProperty("select_system_config");
				try{
				 rs=FixedIncomeListTypeClass1.resultCorporate(Hist_con,query);
				 rs.next();
				 String type=rs.getString("adjust_stock_price").toLowerCase();
				 
				 if(type.equals("n"))   //if no
				 {
				 	corp.setNewtmcv(corp.getTmcv());
					corp.setNewdivisor(corp.getDivisor());
					FixedIncomeNCorp_Action.tmcv_div(corp);
				 }
				 if(type.equals("y"))  //if yes
				 {
				 	toGetaffect(corp);				 	
				 }
				 rs.close();
				 }catch(Exception e){Logging.error("error=="+e.getMessage());}
			}
			/* do later
			else
			{
				//in case of stock dividend/bonus there is no change in tmcv & divisor 
				if(!(corp_name.equals("stockdividend/bonus")))
				{
					toGetaffect(corp);		//reca
				}
				if(corp_name.equals("stockdividend/bonus"))
				{
					corp.setNewtmcv(corp.getTmcv());
					corp.setNewdivisor(corp.getDivisor());
					NCorp_Action.tmcv_div(corp);
				}
			}*/
		}catch(Exception e){
			Logging.error("ERROR :" + e.getMessage());
			}
	}
	public static void undo_ind(Connection con,Connect connect,FixedIncomeCorporate corp,String stock,String index,String diary,String corp_nm,String flag)
	{			
		String query1=ConnectInit.queries.getProperty("get_undo_index_close");  //get index values for particular date
		String affect_index=ConnectInit.queries.getProperty("select_affect_index");
        String child_index=ConnectInit.queries.getProperty("select_child_index");
        String query3=ConnectInit.queries.getProperty("select_stock_detail");
        String index_comp=ConnectInit.queries.getProperty("select_index_comp");
        
        try{
   //     	CFormula cf = new CFormula();        	
        	CFormula cf = ConnectInit.getCFormula();
        	double mcv=0.0;
			double new_tmcv=0.0;
			String value=null;			
			
			corp.setI_index(index);
			corp.setStid(stock); 
			
        	//get index value for particular date
        	String date=corp.getApplied_date();
        	ResultSet rs=FixedIncomeListTypeClass1.getResult_apply(con,query1,index,date);
			rs.next();
			String tmcv=rs.getString("tmcv");			
			String div=rs.getString("divisor");			
			String index_val=rs.getString("index_closing_value");
			rs.close();			
			
			if(flag==null) //not executed for child index
			{
				if((corp_nm.equals("add stock"))|(corp_nm.equals("change iwf")))
					FixedIncomeListTypeClass1.affect_index_list(con,corp,affect_index,stock);
				if(corp_nm.equals("delete stock"))						
					FixedIncomeListTypeClass1.affect_index_list(con,corp,child_index,stock);
			}
			//get IWF
			if((corp_nm.equals("add stock"))|(corp_nm.equals("change iwf")))
			{
				rs=FixedIncomeListTypeClass1.getResult_corp(con,index_comp,index,stock);
			    rs.next();
				value=rs.getString("iwf");	
			}
			if(corp_nm.equals("delete stock"))
			{
				rs=FixedIncomeListTypeClass1.getResult12(con,query3,stock);
				rs.next();
				value=rs.getString("iwf");	
			}
			//get TIS
			ResultSet rs1=FixedIncomeListTypeClass1.getResult12(con,query3,stock);
			rs1.next();
			String tis=rs1.getString("tis");
			String ml=rs1.getString("market_lot");
			rs1.close();	
			//get stock Close value
			String close=corp.getClose();
			
			//get currency exchange value			
			FixedIncomeNCorp_Action.get_currency(con,connect,corp,index,stock);
						
			//calculate MCV and NewTMCV
			if(corp_nm.equals("add stock"))
			{
				mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corp.getCurr_val()),Long.parseLong(tis),Double.parseDouble(value));					
				Logging.debug("mcv in add=="+mcv);				
				new_tmcv=Double.parseDouble(tmcv)-mcv;				
			}
			if(corp_nm.equals("delete stock"))
			{
				corp.setValues(value);
				mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corp.getCurr_val()),Long.parseLong(tis),Double.parseDouble(value));					
				Logging.debug("mcv in del=="+mcv);				
				new_tmcv=Double.parseDouble(tmcv)+mcv;				
			}
			if(corp_nm.equals("change iwf"))
			{
				double mcv_new=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corp.getCurr_val()),Long.parseLong(tis),Double.parseDouble(value));					
				Logging.debug("mcv in new=="+mcv_new);
				double mcv_old=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),1,Long.parseLong(tis),Double.parseDouble(corp.getValues()));				
				Logging.debug("mcv in old=="+mcv_old);
				mcv=mcv_new-mcv_old;
				Logging.debug("mcv in iwf=="+mcv);				
				new_tmcv=Double.parseDouble(tmcv)-mcv;				
			}			
			corp.setNewmcv(mcv);
			corp.setNewTmcv(Double.toString(new_tmcv));
			//claculate Divisor
			double newdivisor=(new_tmcv/Double.parseDouble(index_val));
			corp.setNewdivisor(Double.toString(newdivisor));
			
			//temporary delete from index composition
			if(corp_nm.equals("add stock"))
			{
				String delete=ConnectInit.queries.getProperty("delete_index_comp");
				try
				{
				PreparedStatement stmt = con.prepareStatement(delete);
				stmt.setString(1,index);
				stmt.setString(2,stock);
				stmt.executeQuery();
				}catch(Exception e){
					Logging.error("ListTypeClass:Error in DeleteStatement "+e.getMessage());
				}		
			}			
			//temporary add into index composition
			if(corp_nm.equals("delete stock"))
			{			
				String insert_index_comp=ConnectInit.queries.getProperty("insert_into_index_composition");
				UpdateCorp.insert_index_comp(con,insert_index_comp,corp);
			}
			//temporary update index composition
			if(corp_nm.equals("change iwf"))
			{
				String indexcomp=ConnectInit.queries.getProperty("update_index");
				UpdateCorp.update_index_comp(con,indexcomp,corp);
			}
			//update index value_daily for particular date
			String qry=ConnectInit.queries.getProperty("get_undo_index_close");					
		 	String query =ConnectInit.queries.getProperty("update_undo_ind_close");			
		 		//String ind_val_daily=connect.queries.getProperty("update_resp_index_value_daily");
			UpdateCorp.update_undo_index_daily(con,qry,query,corp);
		}catch(Exception e)
		{Logging.error("Error="+e.getMessage());}
	} 
	
	public static void undo_currind(Connection con,Connect connect,FixedIncomeCorporate corp)
	{
		try{
			//get details from diary
			String cid=corp.getCad_id();
			String qry=ConnectInit.queries.getProperty("select_rep_cad");
			ResultSet rs=FixedIncomeListTypeClass1.getAffected(con,qry,cid);
			rs.next();
			String index=rs.getString("index_id");
			corp.setI_index(index);
			String curr_val=rs.getString("amount");	
			String ft_currency=rs.getString("values");
			String date=rs.getString("applied_date");
			String desc=rs.getString("description");
			rs.close();
			
			//get new currency value
			double newcurr_val=1/(Double.parseDouble(curr_val));
			
			//calculate values for parent index
			temp_update_curr(con,connect,corp,date,newcurr_val,ft_currency,desc);
			//get child indices
			String query=ConnectInit.queries.getProperty("resp_child_indcurr");
			FixedIncomeListTypeClass1.affect_child_list(con,corp,query);
			
			//calculate values for child index
			Hashtable hash_aff=corp.getHash_affind();
			if(hash_aff.isEmpty()==false)
			{
				for(Enumeration enumm=hash_aff.keys();enumm.hasMoreElements();)
				{
					String id=(String)enumm.nextElement();
					String div[]=token2(id);
					corp.setI_index(div[1]);
					temp_update_curr(con,connect,corp,date,newcurr_val,ft_currency,desc);
				}
			}

		}catch(Exception e){
			Logging.error("Error="+e.getMessage());
			}
	}
	public static void temp_update_curr(Connection con,Connect connect,FixedIncomeCorporate corp,String date,double newcurr_val,String ft_currency,String desc)
	{
		try{
			//queries
			String stk_qry=ConnectInit.queries.getProperty("get_undo_close_value");//ConnectInit.queries.getProperty("get_resp_date_closevalue");//get stock closing value for respective date			
	//		CFormula cf = new CFormula();   
			CFormula cf = ConnectInit.getCFormula();
			double tmcv=0.0;
//			get index values			
			String qry=ConnectInit.queries.getProperty("get_undo_index_close");
			ResultSet rs=FixedIncomeListTypeClass1.getResult_apply(con,qry,corp.getI_index(),date);		
			rs.next();
			String old_tmcv=rs.getString("tmcv");
			String div=rs.getString("divisor");			
			String index_val=rs.getString("index_closing_value");			
			rs.close();			

//			get the list of stock belonging to the index  
			String query=ConnectInit.queries.getProperty("get_composition_of_parent");
			rs=FixedIncomeListTypeClass1.getAffected(con,query,corp.getI_index());
			while(rs.next())
			{
				String stock=rs.getString("stock_id");
				corp.setStid(stock);				
				String iwf=rs.getString("iwf");
				corp.setValues(iwf);
				
				//get stock details from stock master
				qry=ConnectInit.queries.getProperty("detail_stock_master");
				ResultSet rs1=FixedIncomeListTypeClass1.getAffected(con,qry,stock);
				rs1.next();
				String tis=rs1.getString("tis");
				String ml=rs1.getString("market_lot");
				rs1.close();
				
				//get stock closing value for particular date
				rs1=FixedIncomeListTypeClass1.getResult_apply(con,stk_qry,stock,date); //get stock closing value
				rs1.next();
				String sclose=rs1.getString("adjusted_price");
				if(sclose==null)
					sclose=rs1.getString("stock_closing_value");
				corp.setClose(sclose);
				rs1.close();
				
				//calculate MCV
				double mcv= cf.calMarketCap(Double.parseDouble(sclose),Long.parseLong(ml),newcurr_val,Long.parseLong(tis),Double.parseDouble(iwf));
				corp.setNewmcv(mcv);
				Logging.debug("newmcv=before="+mcv);
				tmcv=tmcv+mcv;				
				
				//temporary update index composition
				String indexcomp=ConnectInit.queries.getProperty("update_index");
				UpdateCorp.update_index_comp(con,indexcomp,corp);
			}			
			corp.setNewTmcv(Double.toString(tmcv));			
			double newdiv=0.0;
			if(desc.equals("td"))
			{
				newdiv=(tmcv/Double.parseDouble(index_val));
			}
			if(desc.equals("t"))
			{
				newdiv=Double.parseDouble(div);
			}
			corp.setNewdivisor(Double.toString(newdiv));
			
			//temporary update index_master
			//get from to currency id
			qry=ConnectInit.queries.getProperty("resp_ftcurr");
			ResultSet rs1=FixedIncomeListTypeClass1.getAffected(con,qry,ft_currency);
			rs1.next();
			String fcurr=rs1.getString("from_currency_id");			
			String tcurr=rs1.getString("to_currency_id");
			rs1.close();
			
			//get index base currency
			qry=null;
			qry=ConnectInit.queries.getProperty("select_index_name");
			rs1=FixedIncomeListTypeClass1.getAffected(con,qry,corp.getI_index());
			rs1.next();
			String bcurr=rs1.getString("base_currency_id");
			rs1.close();
			
			//compare from and to currency			
			String up_curr=null;
			if(bcurr.equals(fcurr))
				up_curr=tcurr;
			if(bcurr.equals(tcurr))
				up_curr=fcurr;
			
			//now update index master
			String ind_up=ConnectInit.queries.getProperty("update_indmaster");
			PreparedStatement  stmt = con.prepareStatement(ind_up);
			stmt.setString(1,up_curr);
			stmt.setString(2,corp.getI_index());
			stmt.executeUpdate();
			
			//temporary update index value daily
			qry=ConnectInit.queries.getProperty("get_undo_index_close");					
		 	query =ConnectInit.queries.getProperty("update_undo_ind_close");			
		 		//String ind_val_daily=connect.queries.getProperty("update_resp_index_value_daily");
			UpdateCorp.update_undo_index_daily(con,qry,query,corp);

		}catch(Exception e){
			Logging.error(" Error="+e.getMessage());
			}
	}
	public static void check_curr(FixedIncomeCorporate corporateact)
	{
	
			Connect connect = ConnectInit.getConnect();
			Connection con=null;
//			Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
			try{

				if(con == null){
					 con = connect.getdbConnection();
				}	 
				
				try{
					int oldcur=0;
					int newcur=0;
					String query=ConnectInit.queries.getProperty("resp_ftcurr");
					ResultSet rs=FixedIncomeListTypeClass1.getAffected(con,query,corporateact.getFtcurrency());
					int fcurr=0;
					int tcurr=0;
					if(rs!=null && rs.next()){
					 fcurr=rs.getInt("from_currency_id");
					 tcurr=rs.getInt("to_currency_id");
					rs.close();
					}
					int curid=Integer.parseInt(corporateact.getCurrencyid());
					if(curid==fcurr)
					{
						oldcur=fcurr;
						newcur=tcurr;				
					}
					if(curid==tcurr)
					{
						oldcur=tcurr;
						newcur=fcurr;
					}
					Hashtable affect=corporateact.getHash_affind();
					boolean check=affect.isEmpty();
					int flag=0;
					if(check==false)
					{
						for(Enumeration enum1 =affect.keys();enum1.hasMoreElements();)
						{
							String ele=(String)enum1.nextElement();
							String div[]=ActionCorp.token2(ele);
							String id2 =div[1];
							query=null;
							query=ConnectInit.queries.getProperty("select_index_name");
							rs=FixedIncomeListTypeClass1.getAffected(con,query,id2);
							rs.next();
							int bcid=rs.getInt("base_currency_id");
							if(bcid==newcur)
							{
								flag=1;
								break;
							}
						}
					}
					if(flag==1)
						corporateact.setCheck_curr(true);
			}catch(Exception e){
				Logging.error("ERROR :" + e.getMessage());
			}
		} catch (Exception e) {
				Logging.debug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
			}
		}
	}
	
	public static void check_affect_index(Connection con,FixedIncomeCorporate corporateact,String query)
	{		
		try{
			CorporateAction error=new CorporateAction();	
			Hashtable affect=corporateact.getHash_affind();			
			boolean check=affect.isEmpty();
			boolean rs_val=true,rs_value=true;
			String dt=UpdateCorp.accept_date();   //get the current date
			String apply=corporateact.getApply_date();
			int chk_dt=FixedIncomeComputeIndexForm.CompareDate(apply,dt);	//check for the current date and user's entered date			
			
				if(check==false)
				{
					for(Enumeration enum1 =affect.keys();enum1.hasMoreElements();)
					{
						String ele=(String)enum1.nextElement();
						String div[]=ActionCorp.token2(ele);
						String id2 =div[1];					
						ResultSet rs=null;
						if(chk_dt==0)
							rs=FixedIncomeListTypeClass1.getResult12(con,query,id2);
						else
							rs=FixedIncomeListTypeClass1.getResult_apply(con,query,id2,corporateact.getApply_date());
						rs_val= rs.next();					
						if(rs_val==false)
						{
							rs_value=false;						
							break;
						}
						else
						{
							String close_val=rs.getString("index_closing_value");
							if(close_val==null)
							{
								rs_value=false;						
								break;
							}
							else
							{
								if(close_val.equals("0"))
								{
									rs_value=false;						
									break;
								}
							}
						}
					}		
				
				}			
			corporateact.setCheck(rs_value);			
		}catch(Exception e){
			Logging.error("ERROR In Checking :" + e.getMessage());
		}
	}
	public static int check_val(FixedIncomeCorporate corporateact)
	{		
			int flag=0;
			Hashtable data=corporateact.getHash3();
			Hashtable data1=corporateact.getHash4();
			try
			{
				int l1=data.size();				
				int l2=data1.size();
				if(l1==0 & l2==0)
					flag=1;
				
			}catch(Exception e)
			{
				Logging.error("ERROR ON APPLY :" + e.getMessage());				
			}
			return flag;
	}
	public static int check(String query,String query1,Hashtable hash,Connection con,FixedIncomeCorporate corporateact,String get_name)
	{
		int flag=0;
		try
		{		
			ResultSet rs=null;
			int itemp=0,index=0;
			String itemp1=null;
			Hashtable hash1=new Hashtable();
			Hashtable hash2=new Hashtable();
			Hashtable hash5=new Hashtable();
			Hashtable hash6=new Hashtable();
			for(Enumeration enumm =hash.keys();enumm.hasMoreElements();)
			{
				 String id =(String)enumm.nextElement();				 
				 PreparedStatement stmt = con.prepareStatement(query);		
				 stmt.setInt(1,Integer.parseInt(id));				
				 rs = stmt.executeQuery();
				 rs.next();			 
				 int cid=0;
				 cid=rs.getInt("cam_id");
				 String corp=rs.getString("cam_id");
				 ResultSet rs2=FixedIncomeListTypeClass1.getAffected(con,get_name,corp);
				 rs2.next();
				 String corp_name=rs2.getString("cam_name");				 
				 String stock=rs.getString("stock_id");				 
				 String apply=rs.getString("apply_on_date");
				 String iwf=rs.getString("values");
				 String date1=UpdateCorp.accept_date();				 
				 ResultSet rs1=FixedIncomeListTypeClass1.getResult_apply(con,query1,id,date1);
					if(!(rs1.next()))
					{
						flag=5;
						break;
					}
				 int iid=rs.getInt("index_id");
				 String i_index=rs.getString("index_id");
				 corporateact.setI_index(i_index);				 
				 flag=0;
				 if(itemp1==null)
					itemp1=corp_name;
				 else
				 {
				 	if(((itemp1.equals("Add Stock"))|(itemp1.equals("Delete Stock"))|(itemp1.equals("Change IWF")))& corp_name.equals("Rebasing"))
				 	{
				 		flag=1;
				 		break;
				 	}
				 	if(((itemp1.equals("Rebasing"))&((corp_name.equals("Add Stock"))|(corp_name.equals("Delete Stock"))|(corp_name.equals("Change IWF")))))
				 	{
				 		flag=1;
				 		break;
				 	}
				 }
				 if(index==0)
				 	index=iid;
				 else
				 {
				 	if(index!=iid)
				 	{
				 		flag=2;
				 		break;
				 	}
				 }
				 if(!(corporateact.getLeng().equals("1")))
				 {
				 	if(itemp1.equals(corp_name))
				 	{	
				 		if(itemp1.equals("Rebasing"))
				 		{
				 			flag=3;
				 			break;
				 		}
				 	}
				 }
				 else
				 {
				 	if(itemp1.equals("Rebasing"))
				 	{
				 		flag=4;
				 		break;
				 	}
				 }
				 if((corp_name.equals("Add Stock")) | (corp_name.equals("Change IWF")))
				 {
				 	hash1.put(new Integer(stock).toString(),new String(id));
					hash5.put(new Integer(stock).toString(),new String(id));				 	
				 }
				 if(corp_name.equals("Delete Stock"))
				 {
				 	hash2.put(new Integer(stock).toString(),new String(id));
					hash6.put(new Integer(stock).toString(),new String(id));				 	
				 }
				corporateact.setHash1(hash1);
				corporateact.setHash2(hash2);
				corporateact.setHash5(hash5);
				corporateact.setHash6(hash6);				 
			}			
		}catch(Exception e)
		{
			Logging.error("ERROR ON APPLY :" + e.getMessage());
		}
		return flag;
	}	
	public static int check_cond(String corp,String leng,String value,FixedIncomeCorporate corporateact)
	{
		int flag=0;
		try
		{
			CorporateAction error=new CorporateAction();
			corporateact.setErrorMessage(null);
			if(flag==0)
			{
				if(corp.equals(""))			
				{
					error.isNullCorporate(corporateact);
						flag=1;				
				}
			}
			if(flag==0)
			{
				if(leng.equals("0"))			
				{
					error.isNullStock(corporateact);
						flag=1;				
				}
			}
			if(flag==0)
			{
				if(corp.equals("ChangeIWF"))
				{
					if(value.equals(""))			
					{
						error.checkiwf_val(corporateact);
						flag=1;				
					}	
					else
					{
						int val=Integer.parseInt(value);
						if((val <= 0)|(val > 1))
						{
							error.checkIwf(corporateact);
							flag=1;
						}
					}
					
				}
			}
		}catch(Exception e)
		{
			Logging.error("ERROR ON APPLY :" + e.getMessage());
		}
		return flag;
	}
	public static void hist_affect_ind(FixedIncomeCorporate corp,String affect)
	{
		try{
			Connect connect = ConnectInit.getConnect();
			Connection con=connect.getConnectionForHistTransaction();
			String corpnm=corp.getCorpid();
			if(corpnm.equals("changeindcurr"))
			{
				String oritmcv=corp.getTmcv();
				String ntmcv=corp.getNewTmcv();
				String oridiv=corp.getDivisor();
				String ndiv=corp.getNewdivisor();				
				//calculate value
				cal_curr_ind(con,connect,corp,affect);
				//reassign old values
				corp.setTmcv1(corp.getTmcv());
				corp.setDivisor1(corp.getDivisor());
				corp.setNewtmcv1(corp.getNewTmcv());
				if(corp.getInd_div().equals("t"))								
					corp.setNewdivisor1(corp.getDivisor1());
				else
					corp.setNewdivisor1(corp.getNewdivisor());
				corp.setTmcv(oritmcv);
				corp.setNewTmcv(ntmcv);
				corp.setDivisor(oridiv);
				corp.setNewdivisor(ndiv);						
			}
			else
			{
				String chk=corp.getInd_comp();
				if(chk.equals("c"))
					recal_affect_value(con,connect,corp,affect); //recalculate new value
			}
		}catch(Exception e){
			Logging.error("ERROR  ON APPLY :" + e.getMessage());
		}
	}
	public static void cal_curr_ind(Connection con,Connect connect,FixedIncomeCorporate corp,String index)
	{
		try{
	//		CFormula cf = new CFormula();    		
			CFormula cf = ConnectInit.getCFormula();	
//as this method is used both for historic and general CA, so there should be date comparision
			String dt=UpdateCorp.accept_date();   //get the current date
			String apply=corp.getApply_date();
			int chk_dt=FixedIncomeComputeIndexForm.CompareDate(apply,dt);
			double currval=0.0;
			currval=Double.parseDouble(corp.getCurr_val());
						
			Hashtable hash=corp.getHash_stock_error();
			hash.clear();
			corp.setHash_stock_error(hash);
			hash=corp.getHash_stock_error();
			ResultSet rs=null;
			
			if(chk_dt==0)
			{
				String query1=ConnectInit.queries.getProperty("get_latest_index_value_index_wise");
				rs=FixedIncomeListTypeClass1.getResult12(con,query1,index);
			}
			else
			{
				String query=ConnectInit.queries.getProperty("get_undo_index_close");				
				rs=FixedIncomeListTypeClass1.getResult_apply(con,query,index,corp.getApply_date());				
			}
			String tmcv=null;			
			String div=null;
			String index_val=null;

			if(rs!=null && 	rs.next()){
				 tmcv=rs.getString("tmcv");			
				corp.setTmcv(tmcv);
				 div=rs.getString("divisor");
				corp.setDivisor(div);
				 index_val=rs.getString("index_closing_value");
				rs.close();
			}
			
			double newtmcv=0.0;
			int flg=0;
			String query=null;
			
//			The list of stocks belonging to the selected index
			query=ConnectInit.queries.getProperty("get_composition_of_parent");  
			rs=FixedIncomeListTypeClass1.getAffected(con,query,index);
			while(rs.next())
			{
				double newmcv=0.0;				
				String stock=rs.getString("stock_id");				
				String iwf=rs.getString("iwf");				
				String qry=ConnectInit.queries.getProperty("detail_stock_master");
				ResultSet rs1=FixedIncomeListTypeClass1.getAffected(con,qry,stock);
				String tis=null;				
				String ml=null;				
				if(rs1!=null && rs1.next()){
					 tis=rs1.getString("tis");				
					 ml=rs1.getString("market_lot");				
					 rs1.close();
				}
				if(chk_dt==0)
				{
					String qry1=ConnectInit.queries.getProperty("get_latest_stock_closing_value_tis");
					rs1=FixedIncomeListTypeClass1.getResult12(con,qry1,stock);
				}
				else
				{
					String stk_qry=ConnectInit.queries.getProperty("get_undo_close_value"); //get stock_closing value for particular date
					rs1=FixedIncomeListTypeClass1.getResult_apply(con,stk_qry,stock,corp.getApply_date());
				}
				String close=null;
				if(rs1.next())
				{
					close=rs1.getString("adjusted_price");
					if(close==null)
						close=rs1.getString("stock_closing_value");
					if(close==null)
					{
						hash.put(new String(stock),new String(stock));
						flg=1;
					}
					else
					{
						if(close.equals("0"))
						{
							hash.put(new String(stock),new String(stock));
							flg=1;
						}
					}
				}
				else
				{
					hash.put(new String(stock),new String(stock));
					flg=1;
				}
				
				if(flg==0)
				{						
					newmcv= cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),currval,Long.parseLong(tis),Double.parseDouble(iwf));					
					newtmcv=newtmcv+newmcv;					
				}
			}//while end
			corp.setNewTmcv(Double.toString(newtmcv));
			
			double newdiv=(newtmcv/Double.parseDouble(index_val));
			corp.setNewdivisor(Double.toString(newdiv));
			corp.setHash_stock_error(hash);
			}catch(Exception e){
			Logging.error("ERROR ON APPLY :" + e.getMessage());
			}
	}
	
	public static StringBuffer affected_index_detail(Connection con,FixedIncomeCorporate corporateact,String check,String query)
	{
		StringBuffer str=new StringBuffer();		
		try
		{
			Hashtable affect=corporateact.getHash_affind();			
			boolean chk_affect=affect.isEmpty();			
			if(chk_affect==false)
			{					
			    Iterator it = affect.keySet().iterator();			
				Vector v = new Vector(affect.keySet());
				Collections.sort(v);
				    it = v.iterator();
				    
				while(it.hasNext())
				{
					String ele=(String)it.next();
					String div[]=ActionCorp.token2(ele);
					 String element =div[1];			 
					 	
					 ResultSet rs=FixedIncomeListTypeClass1.getAffected(con,query,element);
					 rs.next();
					 String iname=rs.getString("index_name");					 
					 if(check!=null)
					 {
					 	if(check.equals(""))
					 		check=null;					
					 }
					if(check==null)
					{	
						int val=str.indexOf(element);						
						if(val==-1)						
							str.append("<option value="+element+">"+iname+"</option>");					
					}				
					else
					{
						int val=str.indexOf(element);
						if(element.equals(check))
						{
							if(val==-1)
								str.append("<option selected value="+element+">"+iname+"</option>");
						}
						else
						{						
							if(val==-1)
								str.append("<option value="+element+">"+iname+"</option>");
						}
					}
				}
			}			
		}catch(Exception e){
			Logging.error("ERROR ON APPLY :" + e.getMessage());
		}
		return str;
	}
	public static void undo_recal_affect_value(FixedIncomeCorporate corporateact,String index)
	{
		Connect connect = ConnectInit.getConnect();
		Connection con=null;
//		Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		try{

		if(con == null){
			 con = connect.getdbConnection();
		}
		
		String query1=ConnectInit.queries.getProperty("get_undo_index_close");//"select_index_detail");
		String ind_com_query=ConnectInit.queries.getProperty("get_composition_of_parent");
		String query=ConnectInit.queries.getProperty("get_undo_close_value");
		String stk_query=ConnectInit.queries.getProperty("detail_stock_master");
		String query2=ConnectInit.queries.getProperty("select_rep_cad");		
		try{
			String date=corporateact.getApplied_date();
			ResultSet rs1=FixedIncomeListTypeClass1.getResult_apply(con,query1,index,corporateact.getApplied_date());
	 		rs1.next();
			corporateact.setTmcv1(rs1.getString("tmcv"));
			corporateact.setDivisor1(rs1.getString("divisor"));
			String index_val=rs1.getString("index_closing_value");
			rs1.close();
			String corp=corporateact.getCorpid();
			Hashtable hash1=corporateact.getHash1();
			Hashtable stk_error=corporateact.getHash_stock_error();
			ResultSet rs=FixedIncomeListTypeClass1.getResult1(con,ind_com_query,index);
			double tmcv=0.0,count=0;		
	//		CFormula cf=new CFormula();			
			CFormula cf = ConnectInit.getCFormula();
			while(rs.next())
			{
				String sid=rs.getString("stock_id");				
				String iwf=rs.getString("iwf");
				double mcv=0.0;
				//get currency exchange value
				FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,index,sid);
				
				boolean chk_hash=hash1.containsValue(sid);
				if(chk_hash==true)
				{
					boolean chk_stk=stk_error.containsKey(sid);
					if(chk_stk==false)
					{
						ResultSet rs2=FixedIncomeListTypeClass1.getResult_apply(con,query,sid,date);						
					 	rs2.next();			 	
					 	String close=rs2.getString("adjusted_price");
					 	if(close==null)
					 		close=rs2.getString("stock_closing_value");
					 	rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,sid);
						rs1.next();
						String tis=rs1.getString("tis");
						String ml=rs1.getString("market_lot");
						rs1.close();
						if(corp.equals("addstock"))
						{
							tmcv=tmcv;							
						}
						if(corp.equals("changeiwf"))
						{
							for(Enumeration enum1 =hash1.keys();enum1.hasMoreElements();)
		 					{
		 						String id2 =(String)enum1.nextElement();
		 						String div1[]=ActionCorp.token(id2);
		 						rs=FixedIncomeListTypeClass1.getAffected(con,query2,div1[1]);			
								rs.next();
								String stid=rs.getString("stock_id");
								String val=rs.getString("values");
								if(stid.equals(sid))
	 							{
									mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(val));									
	 							}																		
		 					}
							Logging.debug("mmcv==="+mcv);
							if(tmcv==0.0)
								tmcv=mcv;
							else
								tmcv=tmcv+mcv;							
						}
					}//chk_stk					
				}//chk_hash
				if(chk_hash==false)
				{
					ResultSet rs2=FixedIncomeListTypeClass1.getResult_apply(con,query,sid,date);
				 	rs2.next();			 	
				 	String close=rs2.getString("adjusted_price");
				 	if(close==null)
				 		close=rs2.getString("stock_closing_value");
				 	rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,sid);
					rs1.next();
					String tis=rs1.getString("tis");
					String ml=rs1.getString("market_lot");
					rs1.close();
					mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));					
					Logging.debug("mcv==="+mcv);
					if(tmcv==0.0)
						tmcv=mcv;
					else
						tmcv=tmcv+mcv;				 	
				}
			}//while
			if(corp.equals("deletestock"))
			{
				for(Enumeration enum1 =hash1.keys();enum1.hasMoreElements();)
				{				
					String id2 =(String)enum1.nextElement();
					Object obj=hash1.get(id2);
					String stid=obj.toString();
					ResultSet rs2=FixedIncomeListTypeClass1.getResult_apply(con,query,stid,date);
				 	rs2.next();			 	
				 	String close=rs2.getString("adjusted_price");
				 	if(close==null)
				 		close=rs2.getString("stock_closing_value");
				 	//get currency exchange value
				 	FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,index,stid);
				 	
				 	rs=FixedIncomeListTypeClass1.getAffected(con,stk_query,stid);
					rs.next();
					String tis=rs.getString("tis");	
					String ml=rs.getString("market_lot");
					String iwf=rs.getString("iwf");
				 	double mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));				 	
				 	Logging.debug("mcv==="+mcv);
		 			if(tmcv==0.0)
		 				tmcv=mcv;
		 			else
		 				tmcv=tmcv+mcv;		 			
				 }					
			}
			corporateact.setNewtmcv1(Double.toString(tmcv));
			double ndiv=tmcv/Double.parseDouble(index_val);
			corporateact.setNewdivisor1(Double.toString(ndiv));
		}catch(Exception e){
			Logging.error("error="+e.getMessage());
			}
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
			}
		}
	}
	public static void recal_affect_value(Connection con,Connect connect,FixedIncomeCorporate corporateact,String index)
	{			
		try{
//as this method is used both for historic and general CA, so there should be date comparison
			String dt=UpdateCorp.accept_date();   //get the current date
			String apply=corporateact.getApply_date();
			int chk_dt=FixedIncomeComputeIndexForm.CompareDate(apply,dt);		//check for the current date and user's entered date						

			String ind_com_query=ConnectInit.queries.getProperty("get_composition_of_parent");						
			String stk_query=ConnectInit.queries.getProperty("detail_stock_master");
			String query=ConnectInit.queries.getProperty("select_stock_price_detail");
			String stk_qry=ConnectInit.queries.getProperty("get_undo_close_value");
			
			ResultSet rs1=null;
			if(chk_dt==0)
			{
				String query1=ConnectInit.queries.getProperty("get_latest_index_value_index_wise");
				rs1=FixedIncomeListTypeClass1.getResult12(con,query1,index);
			}
			else
			{
				String query1=ConnectInit.queries.getProperty("get_undo_index_close");
				rs1=FixedIncomeListTypeClass1.getResult_apply(con,query1,index,corporateact.getApply_date());
			}
	 		rs1.next();
			corporateact.setTmcv1(rs1.getString("tmcv"));
			corporateact.setDivisor1(rs1.getString("divisor"));
			String index_val=rs1.getString("index_closing_value");
			rs1.close();
			
			String corp=corporateact.getCorpid();
			Hashtable hash1=corporateact.getHash1();
			Hashtable stk_error=corporateact.getHash_stock_error();
			ResultSet rs=FixedIncomeListTypeClass1.getResult1(con,ind_com_query,index);
			double tmcv=0.0,count=0;			
	//		CFormula cf=new CFormula();
			CFormula cf = ConnectInit.getCFormula();
			while(rs.next())
			{
				String sid=rs.getString("stock_id");				
				String iwf=rs.getString("iwf");
				//get currency exchange value
				FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,index,sid);
				
				double mcv=0.0;
				boolean chk_hash=hash1.containsValue(sid);
				if(chk_hash==true)
				{
					boolean chk_stk=stk_error.containsKey(sid);
					if(chk_stk==false)
					{
						ResultSet rs2=null;
						if(chk_dt==0)
							rs2=FixedIncomeListTypeClass1.getResult12(con,query,sid);						
						else
							rs2=FixedIncomeListTypeClass1.getResult_apply(con,stk_qry,sid,corporateact.getApply_date());						
					 	String close=null;
						if(rs2!=null && rs2.next()){			 	
					 		 close=rs2.getString("adjusted_price");
					 		 if(close==null)
					 		 	close=rs2.getString("stock_closing_value");
						}
					 	rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,sid);
					 	String tis=null;
					 	String ml=null;
						if(rs1!=null && rs1.next()){
							 tis=rs1.getString("tis");
							 ml=rs1.getString("market_lot");
							rs1.close();
						}
						if(corp.equals("deletestock"))
						{
							tmcv=tmcv;							
						}
						if(corp.equals("changeiwf"))
						{					
							
							mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(corporateact.getValues()));
							Logging.debug("mmcv==="+mcv);
							if(tmcv==0.0)
								tmcv=mcv;
							else
								tmcv=tmcv+mcv;							
						}
					}
				}//hash1 true
				if(chk_hash==false)
				{
					ResultSet rs2=null;
					if(chk_dt==0)
						rs2=FixedIncomeListTypeClass1.getResult12(con,query,sid);					
					else
						rs2=FixedIncomeListTypeClass1.getResult_apply(con,stk_qry,sid,corporateact.getApply_date());					
				 	rs2.next();			 	
				 	String close=rs2.getString("adjusted_price");
				 	if(close==null)
				 		close=rs2.getString("stock_closing_value");
				 	rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,sid);
					rs1.next();
					String tis=rs1.getString("tis");
					String ml=rs1.getString("market_lot");
					rs1.close();
					mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));					
					Logging.debug("mcv==="+mcv);
					if(tmcv==0.0)
						tmcv=mcv;
					else
						tmcv=tmcv+mcv;					
				}
			}
			
			if(corp.equals("addstock"))
			{
				for(Enumeration enum1 =hash1.keys();enum1.hasMoreElements();)
				{				
					String id2 =(String)enum1.nextElement();
					Object obj=hash1.get(id2);
					String stid=obj.toString();					
					ResultSet rs2=null;
					if(chk_dt==0)
						rs2=FixedIncomeListTypeClass1.getResult12(con,query,stid);
					else
						rs2=FixedIncomeListTypeClass1.getResult_apply(con,stk_qry,stid,corporateact.getApply_date());						
				 	rs2.next();			 	
				 	String close=rs2.getString("adjusted_price");
				 	if(close==null)
				 		close=rs2.getString("stock_closing_value");
				 	rs=FixedIncomeListTypeClass1.getAffected(con,stk_query,stid);
					rs.next();
					String tis=rs.getString("tis");		
					String ml=rs.getString("market_lot");
					String iwf=rs.getString("iwf");
					
					//get currency exchange value
					FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,index,stid);
					
				 	double mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));				 	
				 	Logging.debug("mcv==="+mcv);
		 			if(tmcv==0.0)
		 				tmcv=mcv;
		 			else
		 				tmcv=tmcv+mcv;		 			
				 }				
			}
			corporateact.setNewtmcv1(Double.toString(tmcv));
			double ndiv=tmcv/Double.parseDouble(index_val);
			corporateact.setNewdivisor1(Double.toString(ndiv));
		}catch(Exception e){
			Logging.error("error in recalculation=="+e.getMessage());
		}
		
	}
	public static void undoaffected_index_value(FixedIncomeCorporate corporateact,String index)
	{
		Connect connect = ConnectInit.getConnect();
		Connection con=null;
//		Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		try{

			if(con == null){
				 con = connect.getdbConnection();
			}		

		String query1=ConnectInit.queries.getProperty("get_undo_index_close");
		String query=ConnectInit.queries.getProperty("get_undo_close_value");		
		String stk_query=ConnectInit.queries.getProperty("detail_stock_master");
		String index_comp_val=ConnectInit.queries.getProperty("select_index_comp");		
		try{
			boolean affect_val=corporateact.isCheck();
			boolean check_rs=false;		
			corporateact.setCheck(affect_val);
			Hashtable hash_affect=corporateact.getHash_affind();				
			Hashtable hash1=corporateact.getHash1();
			String close=null,tis=null,ml=null;
			String value=null;
			String corp=corporateact.getCorpid();
			double new_tmcv=0.0;
			boolean chk_exist=false;	
			String tmcv=null,divi=null,index_close=null;	
			double mcv=0.0,tmcv1=0.0,divi1=0.0,t=0.0;		
			if(affect_val==true)
			{		
			//	CFormula cf = new CFormula();			
				CFormula cf = ConnectInit.getCFormula();
				String date=corporateact.getApplied_date();
				ResultSet rs=FixedIncomeListTypeClass1.getResult_apply(con,query1,index,date);
				if(rs!=null && rs.next()){						
					tmcv=rs.getString("tmcv");
					corporateact.setTmcv1(tmcv);
					divi=rs.getString("divisor");
					index_close=rs.getString("index_closing_value");	
					rs.close();
				}
				corporateact.setTmcv1(tmcv);	
				corporateact.setDivisor1(divi);
				String stock=null;
				for(Enumeration enum1 =hash1.keys();enum1.hasMoreElements();)
				{
					String id2 =(String)enum1.nextElement();					
					String div1[]=token(id2);
					Object obj=hash1.get(id2);
					stock=obj.toString();
					rs=FixedIncomeListTypeClass1.getResult_apply(con,query,stock,date);						
					check_rs=rs.next();
					if(check_rs==true)
					{												
						close=rs.getString("adjusted_price");
						if(close==null)
							close=rs.getString("stock_closing_value");
						if(close!=null)
						{
							if(!(close.equals("0")))
							{
								rs.close();
								ResultSet rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,stock);
								rs1.next();
								tis=rs1.getString("tis");
								ml=rs1.getString("market_lot");
								rs1.close();
								//get currency exchange value
								FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,index,stock);
								
								if(corp!=null)
								{
									if(corp.equals("addstock"))
									{
										rs=FixedIncomeListTypeClass1.getResult_corp(con,index_comp_val,index,stock);
									    rs.next();
										value=rs.getString("iwf");
										rs.close();
									}
									if(corp.equals("deletestock"))
									{
										rs=FixedIncomeListTypeClass1.getAffected(con,stk_query,stock);
										 rs.next();
										 value=rs.getString("iwf");
										 rs.close();
									}	
									if(corp.equals("changeiwf"))
										value=corporateact.getValues();		
									
									if(corp.equals("addstock"))
									{
										mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(value));									
										Logging.debug("mcv in add=="+mcv);
										if(new_tmcv==0.0)
											new_tmcv=Double.parseDouble(tmcv)-mcv;
										else
											new_tmcv=new_tmcv-mcv;									
									}
									if(corp.equals("deletestock"))
									{
										mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(value));									
										Logging.debug("mcv in del=="+mcv);
										if(new_tmcv==0.0)
											new_tmcv=Double.parseDouble(tmcv)+mcv;
										else
											new_tmcv=new_tmcv+mcv;
									}
									if(corp.equals("changeiwf"))
									{
										rs=FixedIncomeListTypeClass1.getResult_corp(con,index_comp_val,index,stock);
									    rs.next();
										String val=rs.getString("iwf");
										double mcv_new=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(val));									
										double mcv_old=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(value));									
										mcv=mcv_new-mcv_old;
										if(new_tmcv==0.0)
											new_tmcv=Double.parseDouble(tmcv)-mcv;
										else
											new_tmcv=new_tmcv-mcv;
									}
								}
							}//close if		
						}
					}//if check rs
				}//for hash1	
		//		org.jfree.chart.demo.servlet.AdjustDecimal adj=new org.jfree.chart.demo.servlet.AdjustDecimal();
				AdjustDecimal adj = ConnectInit.getAdjustDecimal();
				String new_tmcv1=adj.shareholdingpatt(new_tmcv);				
				corporateact.setNewtmcv1(new_tmcv1);	
				
				double div=Double.parseDouble(divi);
				if(div==0.0)
				{
					double newdivisor=(new_tmcv/Double.parseDouble(index_close));
					corporateact.setNewdivisor1(Double.toString(newdivisor));					
				}
				else
				{
					double diff=0.0,newdivisor=0.0;				
					diff=cf.diffTMCV(Double.parseDouble(tmcv),new_tmcv);			
					newdivisor=cf.RecalnewDivisorCorp(Double.parseDouble(tmcv),diff,Double.parseDouble(divi));
					String newdivisor1=adj.shareholdingpatt(newdivisor);;
					corporateact.setNewdivisor1(newdivisor1);
				}				
			}//if affect_val			
			corporateact.setValues(value);
				
		}catch(Exception e){
			Logging.error("error in recalculation=="+e.getMessage());			
		}

		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
			}
		}
	}
	
	public static void affected_index_value(FixedIncomeCorporate corporateact,String index)
	{
		Connect connect = ConnectInit.getConnect();
		Connection con=null;
		//Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		try{
		
		if(con == null){
			 con = connect.getdbConnection();
		}	
		
		try{
		String query=ConnectInit.queries.getProperty("select_stock_price_detail");
        String query1=ConnectInit.queries.getProperty("get_latest_index_value_index_wise");//"select_index_detail");			
	    String query2=ConnectInit.queries.getProperty("select_rep_cad");			
    	String query3=ConnectInit.queries.getProperty("select_stock_detail");			        
        String index_comp=ConnectInit.queries.getProperty("select_index_wise_stock_id");
        String index_base=ConnectInit.queries.getProperty("select_index_name");
        String index_comp_val=ConnectInit.queries.getProperty("select_index_comp");
        String stk_query=ConnectInit.queries.getProperty("detail_stock_master");
		
		boolean check_rs=false;		
		corporateact.setTmcv1("");
		corporateact.setDivisor1("");
		corporateact.setNewtmcv1("");
		corporateact.setNewdivisor1("");
		CorporateAction error=new CorporateAction();		
		boolean affect_val=corporateact.isCheck();
		corporateact.setCheck(affect_val);
		Hashtable hash_affect=corporateact.getHash_affind();				
		Hashtable hash1=corporateact.getHash1();		
		String close=null,tis=null,ml=null;
		String value=null;
		String corp=corporateact.getCorpid();				
		double new_tmcv=0.0;
		boolean chk_exist=false;	
		String tmcv=null,divi=null,index_close=null;	
		double mcv=0.0,tmcv1=0.0,divi1=0.0,t=0.0;
	//	CFormula cf=new CFormula();
		CFormula cf = ConnectInit.getCFormula();
				if(affect_val==true)
				{						 
					ResultSet rs=FixedIncomeListTypeClass1.getResult12(con,query1,index);			
					if(rs!=null && rs.next()){			
						tmcv=rs.getString("tmcv");
						corporateact.setTmcv1(tmcv);
						divi=rs.getString("divisor");
						index_close=rs.getString("index_closing_value");	
						rs.close();
					}
					corporateact.setTmcv1(tmcv);	
					corporateact.setDivisor1(divi);
					String stock=null;
					for(Enumeration enum1 =hash1.keys();enum1.hasMoreElements();)
					{
						String id2 =(String)enum1.nextElement();					
						String div1[]=token(id2);
						if(div1[0].equals("false"))
						{
							for(Enumeration enumm =hash_affect.keys();enumm.hasMoreElements();)
							{
								String aff=(String)enumm.nextElement();
								String div[]=ActionCorp.token2(aff);
								String affect_id =div[1];
								Object obj=hash_affect.get(aff);																
								String stval=obj.toString();
								if(stval.equals(div1[1]))
								{
									if(index.equals(affect_id))
									{
										chk_exist=true;
										break;
									}
									else
										chk_exist=false;
								}
								else
									chk_exist=false;
							}//for hash_affect														
							if(chk_exist==true)
							{
								stock=div1[1];
								Logging.debug("-----------in false------------");
								rs=FixedIncomeListTypeClass1.getResult12(con,query,div1[1]);			
								check_rs=rs.next();
								if(check_rs==true)
								{
									close=rs.getString("adjusted_price");
									if(close==null)
										close=rs.getString("stock_closing_value");																		
									rs.close();
									ResultSet rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,div1[1]);
									rs1.next();
									tis=rs1.getString("tis");
									ml=rs1.getString("market_lot");
									rs1.close();
									if(corp!=null)
									{
										if(corp.equals("addstock"))
										{											
											rs=FixedIncomeListTypeClass1.getResult12(con,query3,div1[1]);
											 rs.next();
											 value=rs.getString("iwf");
										}
										if(corp.equals("deletestock"))
										{
											rs=FixedIncomeListTypeClass1.getResult_corp(con,index_comp_val,index,div1[1]);
										    rs.next();
											value=rs.getString("iwf");
										}	
										if(corp.equals("changeiwf"))
											value=corporateact.getValues();		
									}
								}//check rs
							}//false check							
						}//false if						
						if(div1[0].equals("true"))
						{
							Logging.debug("-----------in true------------");
							rs=FixedIncomeListTypeClass1.getAffected(con,query2,div1[1]);
							String val=null;
							if(rs!=null && rs.next()){
								stock=rs.getString("stock_id");
							for(Enumeration enumm =hash_affect.keys();enumm.hasMoreElements();)
							{
								String ele=(String)enumm.nextElement();
								String div[]=ActionCorp.token2(ele);								
								String affect_id =div[1];
								Object obj=hash_affect.get(ele);
								String stval=obj.toString();
								if(stval.equals(stock))
								{
									if(affect_id.equals(index))
									{
										chk_exist=true;
										break;
									}
								}
								else
									chk_exist=false;
							}//for hash_affect
							val=rs.getString("values");
							rs.close();
							}
							if(chk_exist==true)
							{
								rs=FixedIncomeListTypeClass1.getResult12(con,query,stock);			
								check_rs=rs.next();
								if(check_rs==true)
								{												
									close=rs.getString("adjusted_price");
									if(close==null)
										close=rs.getString("stock_closing_value");
									
									ResultSet rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,stock);
									rs1.next();
									tis=rs1.getString("tis");
									ml=rs1.getString("market_lot");
									rs1.close();					
									String nature=corporateact.getNature();
									if(corp!=null)
									{
										if(corp.equals("addstock"))
										{
											 rs=FixedIncomeListTypeClass1.getResult12(con,query3,stock);
											 rs.next();
											 value=rs.getString("iwf");
										}
										if(corp.equals("deletestock"))
										{
											rs=FixedIncomeListTypeClass1.getResult_corp(con,index_comp_val,index,stock);
										    rs.next();
											value=rs.getString("iwf");
										}	
										if(corp.equals("changeiwf"))
										{
											if(nature!=null)
											{
												if(nature.equals("n"))
													value=corporateact.getValues();
												if(nature.equals("o"))
													value=val;
											}
											else
												value=corporateact.getValues();
										}
									}//if corp
								}								
							}//chk_exist
						}//true if
						if(chk_exist==true)
						{							
							if(check_rs==true)
							{
								//get currency exchange value
								FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,index,stock);
								
								mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(value));									
								Logging.debug("mcv=========="+mcv);								
								if(corp!=null)
								{
									if(corp.equals("changeiwf"))
									{
										rs=FixedIncomeListTypeClass1.getResult_corp(con,index_comp_val,index,stock);
									    rs.next();
										String val=rs.getString("iwf");
										double mcv_old=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(val));											
										double mcv_new=mcv;
										mcv=mcv_new-mcv_old;
										if(new_tmcv==0.0)
											new_tmcv=Double.parseDouble(tmcv)+mcv;
										else
											new_tmcv=new_tmcv+mcv;
									}
									if(corp.equals("addstock"))
									{
										if(new_tmcv==0.0)
											new_tmcv=Double.parseDouble(tmcv)+mcv;
										else
											new_tmcv=new_tmcv+mcv;
									}
									if(corp.equals("deletestock"))
									{
										if(new_tmcv==0.0)
											new_tmcv=Double.parseDouble(tmcv)-mcv;
										else
											new_tmcv=new_tmcv-mcv;	

									}																			
								}//corp null
							}//check_rs
						}//chk_exist
					}//for hash1					
		//			org.jfree.chart.demo.servlet.AdjustDecimal adj=new org.jfree.chart.demo.servlet.AdjustDecimal();					
					AdjustDecimal adj = ConnectInit.getAdjustDecimal();		
					String new_tmcv1=adj.shareholdingpatt(new_tmcv);
					corporateact.setNewtmcv1(new_tmcv1);	
					
					double div=Double.parseDouble(divi);
					if(div==0.0)
					{
						double newdivisor=(new_tmcv/Double.parseDouble(index_close));
						corporateact.setNewdivisor1(Double.toString(newdivisor));
						
					}
					else
					{
					double diff=0.0,newdivisor=0.0;
					
						diff=cf.diffTMCV(Double.parseDouble(tmcv),new_tmcv);			
						newdivisor=cf.newDivisorCorp(Double.parseDouble(tmcv),diff,Double.parseDouble(divi));
						String newdivisor1=adj.shareholdingpatt(newdivisor);;
						corporateact.setNewdivisor1(newdivisor1);
					}
					
						
						
				}//affect_val
				
				corporateact.setValues(value);
			}catch(Exception e){
			Logging.error("ERROR ON APPLY :" + e.getMessage());
		}
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
			}
		}
	}
	
	public static void hist_affected_index_value(FixedIncomeCorporate corporateact,String index)
	{
		Connect connect = ConnectInit.getConnect();	
		//Connection con = connect.getConnection();		
		Connection con=null;
//		Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		try{
			if(con==null)
			{
				con = connect.getdbConnection();
			}

		try{
		String query=ConnectInit.queries.getProperty("get_undo_close_value"); //get stock_closing value for particular date
		String query1=ConnectInit.queries.getProperty("select_resp_close");  //get index values for particular date			
	    String query2=ConnectInit.queries.getProperty("select_rep_cad");			
    	String query3=ConnectInit.queries.getProperty("select_stock_detail");			        
        String index_comp=ConnectInit.queries.getProperty("select_index_wise_stock_id");
        String index_base=ConnectInit.queries.getProperty("select_index_name");
        String index_comp_val=ConnectInit.queries.getProperty("select_index_comp");
        String stk_query=ConnectInit.queries.getProperty("detail_stock_master");
		
		boolean check_rs=false;		
		corporateact.setTmcv1("");
		corporateact.setDivisor1("");
		corporateact.setNewtmcv1("");
		corporateact.setNewdivisor1("");
		CorporateAction error=new CorporateAction();		
		boolean affect_val=corporateact.isCheck();
		corporateact.setCheck(affect_val);
		Hashtable hash_affect=corporateact.getHash_affind();				
		Hashtable hash1=corporateact.getHash1();		
		String close=null,tis=null,ml=null;
		String value=null;
		String corp=corporateact.getCorpid();				
		double new_tmcv=0.0;
		boolean chk_exist=false;	
		String tmcv=null,divi=null,index_close=null;	
		double mcv=0.0,tmcv1=0.0,divi1=0.0,t=0.0;		
		CFormula cf = ConnectInit.getCFormula();				
//		CFormula cf = new CFormula();
		ResultSet rs=FixedIncomeListTypeClass1.getResult_apply(con,query1,index,corporateact.getApply_date());			
		if(rs!=null && rs.next()){			
			tmcv=rs.getString("tmcv");
			corporateact.setTmcv1(tmcv);
			divi=rs.getString("divisor");
			index_close=rs.getString("index_closing_value");	
			rs.close();
		}
		corporateact.setTmcv1(tmcv);	
		corporateact.setDivisor1(divi);
		String stock=null;
		for(Enumeration enum1 =hash1.keys();enum1.hasMoreElements();)
		{
			String id2 =(String)enum1.nextElement();					
			String div1[]=token(id2);
			if(div1[0].equals("false"))
			{
				for(Enumeration enumm =hash_affect.keys();enumm.hasMoreElements();)
				{
					String aff=(String)enumm.nextElement();
					String div[]=ActionCorp.token2(aff);
					String affect_id =div[1];
					Object obj=hash_affect.get(aff);																
					String stval=obj.toString();
					if(stval.equals(div1[1]))
					{
						if(index.equals(affect_id))
						{
							chk_exist=true;
							break;
						}
						else
							chk_exist=false;
					}
					else
						chk_exist=false;
				}//for hash_affect														
				if(chk_exist==true)
				{
					stock=div1[1];
					Logging.debug("-----------in false------------");
					rs=FixedIncomeListTypeClass1.getResult_apply(con,query,div1[1],corporateact.getApply_date());			
					check_rs=rs.next();
					if(check_rs==true)
					{
						close=rs.getString("adjusted_price");
						if(close==null)
							close=rs.getString("stock_closing_value");																		
						rs.close();
						ResultSet rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,div1[1]);
						rs1.next();
						tis=rs1.getString("tis");
						ml=rs1.getString("market_lot");
						rs1.close();
						if(corp!=null)
						{
							if(corp.equals("addstock"))
							{											
								rs=FixedIncomeListTypeClass1.getResult12(con,query3,div1[1]);
								 rs.next();
								 value=rs.getString("iwf");
							}
							if(corp.equals("deletestock"))
							{
								rs=FixedIncomeListTypeClass1.getResult_corp(con,index_comp_val,index,div1[1]);
							    rs.next();
								value=rs.getString("iwf");
							}	
							if(corp.equals("changeiwf"))
								value=corporateact.getValues();		
						}
					}//check rs
				}//false check							
			}//false if						
			if(div1[0].equals("true"))
			{
				Logging.debug("-----------in true------------");
				rs=FixedIncomeListTypeClass1.getAffected(con,query2,div1[1]);			
				rs.next();
				stock=rs.getString("stock_id");
				for(Enumeration enumm =hash_affect.keys();enumm.hasMoreElements();)
				{
					String ele=(String)enumm.nextElement();
					String div[]=ActionCorp.token2(ele);								
					String affect_id =div[1];
					Object obj=hash_affect.get(ele);
					String stval=obj.toString();
					if(stval.equals(stock))
					{
						if(affect_id.equals(index))
						{
							chk_exist=true;
							break;
						}
					}
					else
						chk_exist=false;
				}//for hash_affect
				String val=rs.getString("values");
				rs.close();
				if(chk_exist==true)
				{
					rs=FixedIncomeListTypeClass1.getResult_apply(con,query,stock,corporateact.getApply_date());			
					check_rs=rs.next();
					if(check_rs==true)
					{												
						close=rs.getString("adjusted_price");
						if(close==null)
							close=rs.getString("stock_closing_value");
						
						ResultSet rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,stock);
						rs1.next();
						tis=rs1.getString("tis");
						ml=rs1.getString("market_lot");
						rs1.close();					
						String nature=corporateact.getNature();
						if(corp!=null)
						{
							if(corp.equals("addstock"))
							{
								 rs=FixedIncomeListTypeClass1.getResult12(con,query3,stock);
								 rs.next();
								 value=rs.getString("iwf");
							}
							if(corp.equals("deletestock"))
							{
								rs=FixedIncomeListTypeClass1.getResult_corp(con,index_comp_val,index,stock);
							    rs.next();
								value=rs.getString("iwf");
							}	
							if(corp.equals("changeiwf"))
							{
								if(nature!=null)
								{
									if(nature.equals("n"))
										value=corporateact.getValues();
									if(nature.equals("o"))
										value=val;
								}
								else
									value=corporateact.getValues();
							}
						}//if corp
					}								
				}//chk_exist
			}//true if
			if(chk_exist==true)
			{
				//get currency exchange value
				FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,index,stock);			
				if(check_rs==true)
				{								
					mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(value));								
					Logging.debug("mcv=========="+mcv);
					if(corp!=null)
					{
						if(corp.equals("changeiwf"))
						{
							rs=FixedIncomeListTypeClass1.getResult_corp(con,index_comp_val,index,stock);
						    rs.next();
							String val=rs.getString("iwf");
							double mcv_old=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(val));										
							double mcv_new=mcv;
							mcv=mcv_new-mcv_old;
							if(new_tmcv==0.0)
								new_tmcv=Double.parseDouble(tmcv)+mcv;
							else
								new_tmcv=new_tmcv+mcv;
						}
						if(corp.equals("addstock"))
						{
							if(new_tmcv==0.0)
								new_tmcv=Double.parseDouble(tmcv)+mcv;
							else
								new_tmcv=new_tmcv+mcv;
						}
						if(corp.equals("deletestock"))
						{
							if(new_tmcv==0.0)
								new_tmcv=Double.parseDouble(tmcv)-mcv;
							else
								new_tmcv=new_tmcv-mcv;	

						}																			
					}//corp null
				}//check_rs
			}//chk_exist
		}//for hash1
//		org.jfree.chart.demo.servlet.AdjustDecimal adj=new org.jfree.chart.demo.servlet.AdjustDecimal();					
		AdjustDecimal adj = ConnectInit.getAdjustDecimal();	
		String new_tmcv1=adj.shareholdingpatt(new_tmcv);
		corporateact.setNewtmcv1(new_tmcv1);	
		
		double div=Double.parseDouble(divi);
		if(div==0.0)
		{
			double newdivisor=(new_tmcv/Double.parseDouble(index_close));
			corporateact.setNewdivisor1(Double.toString(newdivisor));
			
		}
		else
		{
		double diff=0.0,newdivisor=0.0;
		
			diff=cf.diffTMCV(Double.parseDouble(tmcv),new_tmcv);			
			newdivisor=cf.newDivisorCorp(Double.parseDouble(tmcv),diff,Double.parseDouble(divi));
			String newdivisor1=adj.shareholdingpatt(newdivisor);;
			corporateact.setNewdivisor1(newdivisor1);
		}
		
			
			
	//}//affect_val
				
			corporateact.setValues(value);
		}catch(Exception e){
		Logging.error("ERROR ON APPLY :" + e.getMessage());
	}

		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
			}
		}
}
		public static void hist_recal_affect_index(FixedIncomeCorporate corporateact,String index)
		{
			try{
				Connect connect = ConnectInit.getConnect();
				Connection con=connect.getConnectionForHistTransaction();				
				String rad_val=corporateact.getInd_comp();
				if(rad_val!=null)
					if(rad_val.equals(""))
						rad_val=null;
				if(rad_val!=null)
					recal_affect_index(con,connect,corporateact,index);
				else
					affect_comp_index(con,connect,corporateact,index);
								
			}catch(Exception e){
				Logging.error("Error="+e.getMessage());
				}
		}
		public static void recal_affect_index(Connection con,Connect connect,FixedIncomeCorporate corporateact,String index)
		{			
			try{
				String query=ConnectInit.queries.getProperty("select_stock_price_detail");
				String stk_qry=ConnectInit.queries.getProperty("get_undo_close_value"); //get stock_closing value for particular date
				String ind_com_query=ConnectInit.queries.getProperty("get_composition_of_parent");
				String stk_query=ConnectInit.queries.getProperty("detail_stock_master");
				
				
//				as this method is used both for historic and general CA, so there should be date comparison
				String dt=UpdateCorp.accept_date();   //get the current date
				String apply=corporateact.getApply_date();
				int chk_dt=FixedIncomeComputeIndexForm.CompareDate(apply,dt);	//check for the current date and user's entered date					

				
				ResultSet rs1=null;
				if(chk_dt==0)
				{
					String query1=ConnectInit.queries.getProperty("get_latest_index_value_index_wise");
					rs1=FixedIncomeListTypeClass1.getResult12(con,query1,index);
				}	
				else
				{
					String query1=ConnectInit.queries.getProperty("get_undo_index_close");
					rs1=FixedIncomeListTypeClass1.getResult_apply(con,query1,index,corporateact.getApply_date());
				}
		 		rs1.next();
				corporateact.setTmcv1(rs1.getString("tmcv"));
				corporateact.setDivisor1(rs1.getString("divisor"));
				String index_val=rs1.getString("index_closing_value");
				rs1.close();
				
				Hashtable hash1=corporateact.getHash1();
				Hashtable hash2=corporateact.getHash2();
				Hashtable data2=corporateact.getHash12();
				

		//		CFormula cf=new CFormula();
				CFormula cf = ConnectInit.getCFormula();	
				ResultSet rs=FixedIncomeListTypeClass1.getResult1(con,ind_com_query,index);
				double tmcv=0.0,count=0;			
				while(rs.next())
				{					
					count++;
					String sid=rs.getString("stock_id");				
					String iwf=rs.getString("iwf");					
					boolean chk_hash=hash1.containsKey(sid);					
					boolean close_val=true;
					double mcv=0.0;
					//get currency exchange value
					FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,index,sid);
					
					ResultSet rs2=null;
					if(chk_dt==0)
						rs2=FixedIncomeListTypeClass1.getResult12(con,query,sid);
					else
						rs2=FixedIncomeListTypeClass1.getResult_apply(con,stk_qry,sid,corporateact.getApply_date());
				 	close_val=rs2.next();
				 	
					if(chk_hash==false)
					{
						boolean chk_hash2=hash2.containsKey(sid);						
						if(chk_hash2==true)
						{
							//------------Change IWF-----
						 	if(close_val==true)
						 	{			 		
						 		String close=rs2.getString("adjusted_price");
						 		if(close==null)
						 			close=rs2.getString("stock_closing_value");
						 		if(close!=null)
						 		{					 			
						 			rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,sid);
									rs1.next();
									String tis=rs1.getString("tis");
									String ml=rs1.getString("market_lot");
									rs1.close();								
									Object obj=null;
									for(Enumeration enumm =data2.keys();enumm.hasMoreElements();)
									{
										String val=(String)enumm.nextElement();
										if(val.equals(sid))
										{
											obj = data2.get(val);	
											break;
										}
									}
									
									iwf=obj.toString();
									mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));
									if(tmcv==0.0)
										tmcv=mcv;
									else
										tmcv=tmcv+mcv;
						 		}
						 	}						 	
						}
						else
						{										 	
						 	if(close_val==true)
						 	{			 		
						 		String close=rs2.getString("adjusted_price");
						 		if(close==null)
						 			close=rs2.getString("stock_closing_value");
						 		if(close!=null)
						 		{				 		
							 		rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,sid);
									rs1.next();
									String tis=rs1.getString("tis");
									String ml=rs1.getString("market_lot");
									rs1.close();
									
									mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));
									if(tmcv==0.0)
										tmcv=mcv;
									else
										tmcv=tmcv+mcv;									
						 		}
						 	}	 	
						 
						}
					}
					if(chk_hash==true)
					{
						//----------Deletion of stock--					 			 	
					 	if(close_val==true)
					 	{			 		
					 		String close=rs2.getString("adjusted_price");
					 		if(close==null)
					 			close=rs2.getString("stock_closing_value");
					 		if(close!=null)
					 		{
						 		rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,sid);
								rs1.next();
								String tis=rs1.getString("tis");
								String ml=rs1.getString("market_lot");
								rs1.close();								
					 		}
					 	}
					}				
				}//while end	
				if(hash2.isEmpty()==false)
				{
					//-------Add stock----
					String qry=ConnectInit.queries.getProperty("index_comp_detail");
					for(Enumeration enumm =hash2.keys();enumm.hasMoreElements();)
					{
						String id=(String)enumm.nextElement();
						ResultSet rs2=FixedIncomeListTypeClass1.getResult_corp(con,qry,index,id);
						boolean chk_rs=rs2.next();
						if(chk_rs==false)
						{
							ResultSet rs3=null;
							if(chk_dt==0)
								rs3=FixedIncomeListTypeClass1.getResult12(con,query,id);
							else
								rs3=FixedIncomeListTypeClass1.getResult_apply(con,stk_qry,id,corporateact.getApply_date());
						 	boolean close_val=rs3.next();						 	
						 	if(close_val==true)
						 	{			 		
						 		String close=rs3.getString("adjusted_price");						 		
						 		if(close==null)
						 			close=rs3.getString("stock_closing_value");
						 		if(close!=null)
						 		{
						 			if(close.equals("0"))
						 			{
								 		rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,id);
										rs1.next();
										String tis=rs1.getString("tis");
										String ml=rs1.getString("market_lot");
										rs1.close();
										
										 //get currency exchange value
										FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,index,id);
										
										Object obj1=null;
										for(Enumeration enum1 =data2.keys();enum1.hasMoreElements();)
										{
											String val=(String)enum1.nextElement();
											if(val.equals(id))
											{
												obj1 = data2.get(val);						
												break;
											}
										}
										
										String iwf=obj1.toString();									
										double mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));									
										Logging.debug("mcv==="+mcv);
										if(tmcv==0.0)
											tmcv=mcv;
										else
											tmcv=tmcv+mcv;	
							 		}
							 	}
						 	}
						}
					}
				}				
				corporateact.setNewtmcv1(Double.toString(tmcv));
				double newdivisor=tmcv/Double.parseDouble(index_val);
				corporateact.setNewdivisor1(Double.toString(newdivisor));				
				}catch(Exception e){Logging.error("error in recal=="+e.getMessage());}
		}
public static void affect_comp_index(Connection con,Connect connect,FixedIncomeCorporate corporateact,String index)
{
	try{
		String query1=ConnectInit.queries.getProperty("get_latest_index_value_index_wise");//"select_index_detail");
		String ind_query=ConnectInit.queries.getProperty("select_resp_close");
		String stk_qry=ConnectInit.queries.getProperty("get_undo_close_value");
		String query=ConnectInit.queries.getProperty("select_stock_price_detail");	
		String stk_query=ConnectInit.queries.getProperty("detail_stock_master");
		String ind_qry=ConnectInit.queries.getProperty("index_comp_detail");
		
		boolean affect_rs=corporateact.isCheck();
		boolean check_rs=false,chk_exist=false;		
		String tmcv=null,divi=null,new_tmcv1=null;			
		double mcv=0.0,tmcv1=0.0,divi1=0.0,t=0.0,index_close=0.0;
		double newTmcv_del=0;
		Hashtable hash1=corporateact.getHash1();						
		Hashtable hash2=corporateact.getHash2();		
		Hashtable data2=corporateact.getHash12();
		CFormula cf = ConnectInit.getCFormula();
	//	CFormula cf=new CFormula();
//as this method is used both for historic and general CA, so there should be date comparison
		String dt=UpdateCorp.accept_date();   //get the current date
		String apply=corporateact.getApply_date();
		int chk_dt=FixedIncomeComputeIndexForm.CompareDate(apply,dt);	//check for the current date and user's entered date					

		if(index!=null)
		{
			if(!(index.equals("Affected Child Index")))
			{				
				if(affect_rs==true)
				{
					String close=null,tis=null,iwf=null,ml=null;
					String value=null;
					ResultSet rs=null;
					if(chk_dt==0)
						rs=FixedIncomeListTypeClass1.getResult12(con,query1,index);
					else
						rs=FixedIncomeListTypeClass1.getResult_apply(con,ind_query,index,corporateact.getApply_date());
					   if(rs!=null && rs.next()){			
					   		tmcv=rs.getString("tmcv");
					   		t=rs.getDouble("tmcv");				
					   		corporateact.setTmcv1(tmcv);
					   		divi=rs.getString("divisor");			
					   		index_close=rs.getDouble("index_closing_value");
					   		rs.close();	
					  }
					corporateact.setTmcv1(tmcv);	
					corporateact.setDivisor1(divi);
					Hashtable hash_affect=corporateact.getHash_affind();
					for(Enumeration enum1 =hash1.keys();enum1.hasMoreElements();)
					{
						String id2 =(String)enum1.nextElement();						
						for(Enumeration enumm =hash_affect.keys();enumm.hasMoreElements();)
						{								
							String aff=(String)enumm.nextElement();
							String div[]=ActionCorp.token2(aff);
							String affect_id =div[1];
							Object obj=hash_affect.get(aff);																
							String stval=obj.toString();
							if(stval.equals(id2))
							{
								if(index.equals(affect_id))
								{
									chk_exist=true;
									break;
								}
								else
									chk_exist=false;
							}
							else
								chk_exist=false;							
							
						}// for hash_affect
						if(chk_exist==true)
						{
							if(chk_dt==0)
								rs=FixedIncomeListTypeClass1.getResult12(con,query,id2);
							else
								rs=FixedIncomeListTypeClass1.getResult_apply(con,stk_qry,id2,corporateact.getApply_date());
							check_rs=rs.next();					
							if(check_rs==true)
							{
								close=rs.getString("adjusted_price");
								if(close==null)
									close=rs.getString("stock_closing_value");
								if(close!=null && !(close.equals("0")))
								{									
									ResultSet rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,id2);
									if(rs1!=null && rs1.next()){
										tis=rs1.getString("tis");
										ml=rs1.getString("market_lot");
										rs1.close();
									}
									rs.close();	
									
									rs1=FixedIncomeListTypeClass1.getResult_corp(con,ind_qry,index,id2);
									if(rs1!=null && rs1.next()){
										iwf=rs1.getString("iwf");
										rs1.close();
									}
									//get currency exchange value
									FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,index,id2);
									
									mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));									
									Logging.debug("mcv===="+mcv); 
									if(newTmcv_del==0)	
										newTmcv_del=Double.parseDouble(tmcv)-mcv;
									else
										newTmcv_del=newTmcv_del-mcv;
								}
							}
						}
					}//for hash1
					double out=corporateact.getOutstanding();
					for(Enumeration enum1 =hash2.keys();enum1.hasMoreElements();)
					{						
						String id2 =(String)enum1.nextElement();
						Object ob=hash2.get(id2);
						String cad=ob.toString();
						for(Enumeration enumm =hash_affect.keys();enumm.hasMoreElements();)
						{	
							String aff=(String)enumm.nextElement();
							String div[]=ActionCorp.token2(aff);
							String affect_id =div[1];
							Object obj=hash_affect.get(aff);																
							String stval=obj.toString();
							if(stval.equals(id2))
							{
								if(index.equals(affect_id))
								{
									chk_exist=true;
									break;
								}
								else
									chk_exist=false;
							}
							else
								chk_exist=false;								
							
						}
						if(chk_exist==true)
						{
							if(chk_dt==0)
								rs=FixedIncomeListTypeClass1.getResult12(con,query,id2);
							else
								rs=FixedIncomeListTypeClass1.getResult_apply(con,stk_qry,id2,corporateact.getApply_date());
							check_rs=rs.next();
							if(check_rs==true)
							{	
								close=rs.getString("adjusted_price");
								if(close==null)
									close=rs.getString("stock_closing_value");
								if(close!=null && !(close.equals("0")))
								{
									String cquery=ConnectInit.queries.getProperty("select_rep_cad");
									ResultSet rs1=FixedIncomeListTypeClass1.getResult1(con,cquery,cad);
									rs1.next();
									String corp_id=rs1.getString("cam_id");
									rs1.close();
									String get_name=ConnectInit.queries.getProperty("get_corp_name");
									rs1=FixedIncomeListTypeClass1.getResult1(con,get_name,corp_id);
									rs1.next();
									String corp_nm=rs1.getString("cam_shortname").toLowerCase().trim();
									rs1.close();
									
									
									rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,id2);
									rs1.next();
									tis=rs1.getString("tis");
									ml=rs1.getString("market_lot");
									rs1.close();
									iwf=null;
									Object obj=null;
									
										for(Enumeration enumm =data2.keys();enumm.hasMoreElements();)
										{
											String val=(String)enumm.nextElement();
											if(val.equals(id2))
											{
												obj = data2.get(val);							
											}
										}							
										iwf=obj.toString();
										float iwf_val=Float.parseFloat(iwf);
										//get currency exchange value
										FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,index,id2);
										
										if(corp_nm.equals("change iwf"))
										{
											rs1=FixedIncomeListTypeClass1.getResult_corp(con,ind_qry,index,id2);
											rs1.next();
											String val=rs1.getString("iwf");
											double mcv_new=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));											
											double mcv_old=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(val));											
											mcv=mcv_new-mcv_old;
											if(newTmcv_del==0)	
												newTmcv_del=Double.parseDouble(tmcv) +mcv;
											else
												newTmcv_del=newTmcv_del +mcv;
										}
										if(corp_nm.equals("add stock"))
										{
											mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));											
											if(newTmcv_del==0)	
												newTmcv_del=Double.parseDouble(tmcv) +mcv;
											else
												newTmcv_del=newTmcv_del +mcv;
										}
								}
							}//check rs
						}								
						
					}//for hash2
					
					corporateact.setNewtmcv1(Double.toString(newTmcv_del));				
					
					double div=Double.parseDouble(divi);
					if(div==0.0)
					{
						double newdivisor=newTmcv_del/index_close;
						corporateact.setNewdivisor1(Double.toString(newdivisor));
					}
					else
					{
						double diff=cf.diffTMCV(Double.parseDouble(tmcv),newTmcv_del);						
						double newdivisor=cf.newDivisorCorp(Double.parseDouble(tmcv),diff,Double.parseDouble(divi));
						corporateact.setNewdivisor1(Double.toString(newdivisor));											
					}//affect rs
				}
			}//check index
		}//null index
	}catch(Exception e){
		Logging.error("ERROR ON APPLY :" + e.getMessage());
	}
}
	public static int check_iwfval(FixedIncomeCorporate corporateact)
	{
		int flag=0;
		Hashtable hash=corporateact.getHash1();
		if(!(hash.isEmpty()))
		{
			for(Enumeration enum1 =hash.keys();enum1.hasMoreElements();)
			{
				String id2 =(String)enum1.nextElement();
				String div[]=token(id2);
				if(div[0].equals("true"))
				{
					flag=1;
					break;
				}
			}
		}
		return flag;
	}
	protected void finalize()
	{
		Logging.debug("In finalize method");
		try{
		Connect.conForHistTransaction.close();
		Connect.conForHistTransaction=null;
		}catch(Exception e){
			Logging.error("Error ="+e.getMessage());
			}
	}
}
