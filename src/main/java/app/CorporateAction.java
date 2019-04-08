/*
 * Created on Sep 19, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import java.util.StringTokenizer;

import org.apache.log4j.Logger;

/**
 * @author kena
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
//import java.sql.*;


public class CorporateAction 

{
	Logger Logging = Logger.getLogger(CorporateAction.class);
	boolean check=true;
	public String errorMessage=null;
	public void checkRatio(String ratio,Corporate corporateact)throws NumberFormatException{
		Logging.debug(" Value of Ratio in Checking :" + ratio);
		Logging.debug("CHECKING OF RATIO");
		try{
			Logging.debug("IN TRY1 "+ratio);
			if(	corporateact.getErrorMessage().equals("null"))
			{
				if(ratio.equals("null:null" )|ratio.equals(":") ) {
					Logging.debug("IN TRY2");
					throw new NumberFormatException();
				}else{
					checkformat(ratio,corporateact);
				}
			}
			Logging.debug("After if");
		}catch(NumberFormatException e)
		{
			errorMessage= "Please Enter Ratio";
			corporateact.setErrorMessage(errorMessage);
			Logging.debug(corporateact.getErrorMessage());
		}
		
	}
	public void checkformat(String ratio,Corporate corporateact){
		String str[]= new String[2];
		StringTokenizer st = new StringTokenizer(ratio,":");
		try{
			while (st.hasMoreTokens()) {
				for(int i=0;i<str.length;i++)
				{
					str[i]= st.nextToken();
					int ir =Integer.parseInt(str[i]);
				}
			} 
		}catch(NumberFormatException e){
			errorMessage= "Invalid Ratio";
			corporateact.setErrorMessage(errorMessage);
			Logging.debug(corporateact.getErrorMessage());
		}
	}
	public void checkAmt(String amount,Corporate corporateact){
		Logging.debug(" Value of Amount in Checking :" + amount);
		Logging.debug("CHECKING OF Amount");
		try{
			Logging.debug("IN TRY1 "+amount);
			if(amount.equals("null")) {
				Logging.debug("IN TRY2");
				throw new NumberFormatException();
			}else{
				checktypeamt(amount,corporateact);
			}
			//	Logging.debug("After if");
		}catch(NumberFormatException e)
		{
			errorMessage= "Please Enter Amount";
			corporateact.setErrorMessage(errorMessage);
			Logging.debug(corporateact.getErrorMessage());
		}
	}
	public void checkShare(String share,Corporate corporateact){		
		try{
			Logging.debug("in share");
			if(share.equals("null")) {
				Logging.debug("IN TRY2");
				throw new NumberFormatException();
			}else{
				checktypeshr(share,corporateact);
			}
			//	Logging.debug("After if");
		}catch(NumberFormatException e)
		{
			errorMessage= "Please Enter Amount";
			corporateact.setErrorMessage(errorMessage);
			Logging.debug(corporateact.getErrorMessage());
		}
	}
	
	public void checktypeshr(String share,Corporate corporateact){
		try{
			double amt=Double.parseDouble(share);
			
		}catch(NumberFormatException e)
		{
			errorMessage= "Invalid No Of Shares";
			corporateact.setErrorMessage(errorMessage);
			Logging.debug(corporateact.getErrorMessage());
		}
	}	
	public void checktypeamt(String amount,Corporate corporateact){
		try{
			double amt=Double.parseDouble(amount);
			
		}catch(NumberFormatException e)
		{
			errorMessage= "Invalid Amount";
			corporateact.setErrorMessage(errorMessage);
			Logging.debug(corporateact.getErrorMessage());
		}
	}
	public void checktender(String tender,Corporate corporateact){
		//	Logging.debug(" Value of Amount in Checking :" + amount);
		//	Logging.debug("CHECKING OF Amount");
		try{
			//		Logging.debug("IN TRY1 "+amount);
			if(tender.equals("null")) {
				Logging.debug("IN TRY2");
				throw new NumberFormatException();
			}else{
				checktype(tender,corporateact);
			}
			Logging.debug("After if");
		}catch(NumberFormatException e)
		{
			errorMessage= "Please Enter tender number of shares";
			corporateact.setErrorMessage(errorMessage);
			Logging.debug(corporateact.getErrorMessage());
		}
	}
	public void checktype(String tender,Corporate corporateact){
		try{
			int ten=Integer.parseInt(tender);
			
		}catch(NumberFormatException e)
		{
			errorMessage= "Invalid tender no of share";
			corporateact.setErrorMessage(errorMessage);
			Logging.debug(corporateact.getErrorMessage());
		}
	}
	public void checkRights(String ratio,String amount,Corporate corporateact){
		Logging.debug(" Value of Amount in Checking :" + amount);
		Logging.debug("CHECKING OF Amount");
		checkAmt(amount,corporateact);
		if(	corporateact.getErrorMessage().equals("null"))
		{
			checkRatio(ratio,corporateact);
		}
	}
	public void checkRepurchase(String amount,String tender,Corporate corporateact){
		checkAmt(amount,corporateact);
		if(	corporateact.getErrorMessage().equals("null"))
		{
			checktender(tender,corporateact);
		}
		
	}
	public void checknowarrant(String warrant,Corporate corporateact){
		//	Logging.debug(" Value of Amount in Checking :" + amount);
		//	Logging.debug("CHECKING OF Amount");
		try{
			//		Logging.debug("IN TRY1 "+amount);
			if(warrant.equals("null")) {
				Logging.debug("IN TRY2");
				throw new NumberFormatException();
			}else
			{
				checkwarr(warrant,corporateact);
			}
			Logging.debug("After if");
		}catch(NumberFormatException e)
		{
			errorMessage= "Please Enter tender number of warrants";
			corporateact.setErrorMessage(errorMessage);
			Logging.debug(corporateact.getErrorMessage());
		}
	}
	public void checkwarr(String warrant,Corporate corporateact){
		try{
			int warr=Integer.parseInt(warrant);
			
		}catch(NumberFormatException e)
		{
			errorMessage= "Invalid numbers of Warrants";
			corporateact.setErrorMessage(errorMessage);
			Logging.debug(corporateact.getErrorMessage());
		}
	}
	
	
	
	public void checkWarrant(String noofwarrants,String ratio,Corporate corporateact){
		checknowarrant(noofwarrants,corporateact);
		if(	corporateact.getErrorMessage().equals("null"))
		{
			checkRatio(ratio,corporateact);
		}
	}

	public void checkspin(String ratio,String amt,Corporate corporateact){
		checkRatio(ratio,corporateact);
		if(	corporateact.getErrorMessage().equals("null"))
		{
			checkAmt(amt,corporateact);
		}
	}
	
	public void isNullStock(Corporate corporateact){
		try{
			throw new NumberFormatException();
		}catch(NumberFormatException e)
		{
			errorMessage= "Please select Stock";
			corporateact.setErrorMessage(errorMessage);
			Logging.debug(corporateact.getErrorMessage());
		}
	}
	
	public void isNullClose(Corporate corporateact){
		try{
			throw new NumberFormatException();
		}catch(NumberFormatException e)
		{
			errorMessage= "Select Stock having closing value.";
			corporateact.setErrorMessage(errorMessage);
			Logging.debug(corporateact.getErrorMessage());
		}
	}
	public void isNullCorporate(Corporate corporateact){
		try{
			throw new NumberFormatException();
		}catch(NumberFormatException e)
		{
			errorMessage= "Please select Corporate Action";
			corporateact.setErrorMessage(errorMessage);
			Logging.debug(corporateact.getErrorMessage());
		}
	}
	public void checkIwf(Corporate corporateact){
		try{
			throw new NumberFormatException();			
		}catch(NumberFormatException e)
		{
			errorMessage= "IWF should not be greater than 1 and less than 0!";
			corporateact.setErrorMessage(errorMessage);
			Logging.debug(corporateact.getErrorMessage());
		}
	}	
	public void checkiwf_val(Corporate corporateact){
		try{
			throw new NumberFormatException();			
		}catch(NumberFormatException e)
		{
			errorMessage= "IWF should not be blank!";
			corporateact.setErrorMessage(errorMessage);
			Logging.debug(corporateact.getErrorMessage());
		}
	}
	public void isNullIndex(Corporate corporateact){
		try{
			throw new NumberFormatException();
		}catch(NumberFormatException e)
		{
			errorMessage= "Please select Index";
			corporateact.setErrorMessage(errorMessage);
			Logging.debug(corporateact.getErrorMessage());
		}
	}	
	public  void isNullApply(Corporate corporateact){
		try{
			throw new NumberFormatException();
		}catch(NumberFormatException e)
		{
			errorMessage= "Please select Apply On Date";
			corporateact.setErrorMessage(errorMessage);
			Logging.debug(corporateact.getErrorMessage());
		}
	}
	public void isNullAffect(Corporate corporateact){
		try{
			throw new NumberFormatException();
		}catch(NumberFormatException e)
		{
			errorMessage= "Please Compute the Child Index First!";
			corporateact.setErrorMessage(errorMessage);
			Logging.debug(corporateact.getErrorMessage());
		}		
	}
}
