/*
 * Created on Jan 28, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package sysconfig.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sysconfig.model.SysConfig;

/**
 * @author manoj
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public final class SysConfigAction extends Action{
	Logger Logging = Logger.getLogger(SysConfigAction.class);
	public final String FORWARD_start = "success";
	public ActionForward execute( ActionMapping mapping, ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response )
	throws IOException, ServletException {
		
		/**
		 * If Reset Button Is Clicked then Return To Main Page 
		 * */

		SysConfigForm scForm= (SysConfigForm)form;
		String resetValue=scForm.getResetButton();
		String submitbutton = scForm.getSaveButton();
		if(resetValue!=null && resetValue.equals("Reset")){
			return (new ActionForward("/sysConfigView.jsp"));
		}
		
		SysConfig sysconfig=new SysConfig();
		
		/**
		 * Use The Default Date Format If Required
		 * */
		String stringDate	=	scForm.getDate();
		String stringMonth	=	scForm.getMonth();
		String stringYear	=	scForm.getYear();
		char saperator	=	scForm.getValidator();
		String stringDateFormat=stringDate+saperator+stringMonth+saperator+stringYear;
		if((stringDate.endsWith("d")) && (stringMonth.endsWith("d"))){
			stringDateFormat="dd-mm-yyyy";
		}
		else if((stringDate.endsWith("d")) && (stringYear.endsWith("d"))){
			stringDateFormat="dd-mm-yyyy";
		}
		else if((stringDate.endsWith("m")) && (stringMonth.endsWith("m"))){
			stringDateFormat="dd-mm-yyyy";
		}
		else if((stringDate.endsWith("m")) && (stringYear.endsWith("m"))){
			stringDateFormat="dd-mm-yyyy";
		}
		else if((stringDate.endsWith("y")) && (stringMonth.endsWith("y"))){
			stringDateFormat="dd-mm-yyyy";
		}
		else if((stringDate.endsWith("y")) && (stringYear.endsWith("y"))){
			stringDateFormat="dd-mm-yyyy";
		}
		
		
		/**
		 * Use The Default Market Lot
		 * **/
		int mLot	=	scForm.getMarketLot();
		if(mLot==0){
			mLot=1;
		}
		/**
		 * Call The Constructor SysConfig To Make Datsbase Entries  
		 * **/
		if(submitbutton!=null && submitbutton.equals("Submit")){
			Logging.debug("SysConfigAction 93 execute");
			Logging.debug(scForm.getIndex_id()+"SysConfigAction 93 execute"+scForm.getS_indexType());
		sysconfig.AddSysConfig(scForm.getComputationInterval(),scForm.getMonitorRefreshRate(),scForm.getPrecisionValue(),scForm.getRateOfPriceFeed(),scForm.getCustomerName(),scForm.getNameLogoVerticalAlign(),scForm.getNameLogoHorizontalAlign(),scForm.getMaxNoOfCompanies(),scForm.getAlertPercentage(),scForm.getRejectionPercentage(),
				scForm.getIntraDayArchivalInterval(),scForm.getIndustryClassificationId(),scForm.getTimeZoneId(),scForm.getCustomerLogoPath(),stringDateFormat,mLot,scForm.getStockExId(),scForm.getCurrencyId(),scForm.getCountryId(),scForm.getDateDifference(),scForm.getAdjustStockPrice(),scForm.getPercentage_change_in_share(),
				scForm.getLanguage(),scForm.getIndex_id(),scForm.getFaceValue(),scForm.getPaidValue(),scForm.getS_indexType(),scForm.getS_stockType());
		Logging.debug("SysConfigAction 93 execute");
		return (new ActionForward(
		"/pages/masters/roleAdded.jsp?flag=sysconfig&value=update"));
		}
		//return  mapping.getInputForward();
		return mapping.findForward(FORWARD_start);
	}

}
