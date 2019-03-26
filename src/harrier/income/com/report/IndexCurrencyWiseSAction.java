package harrier.income.com.report;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import app.*;

/**
 * @author Ashish
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class IndexCurrencyWiseSAction extends Action{
	Logger Logging = Logger.getLogger(IndexCurrencyWiseSAction.class);
	/**
	 * Action forward class for IndexCalculator.
	 */
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)
		{
			ActionForward fr = null;
			IndexCurrencyWiseForm Icw=(IndexCurrencyWiseForm) form;
			String id1=null;			
		   String but_pressed=Icw.getB1();	
		   
		   id1=request.getParameter("index_id");//get index selected
		   Logging.debug("In Action : value is yessssssssssssssssss"+id1);
		   String indid=Icw.getIndex_id();
		   Logging.debug("In Action : value is yessssssssssssssssss"+indid);
		   String[] crid=request.getParameterValues("currencyid");		
		   if(but_pressed==null){//no button pressed 			   	
			   	if(id1==null){
			   		return fr= new ActionForward("/pages/reports/IndexCurrencyWiseS.jsp");//if no index selected return control back to index calculator page
			   	}
				IndexCalculatorCollection.addInIndexCurrencyWise(id1,request);//generate buffer for displaying table content for index selected
				return fr= new ActionForward("/pages/reports/IndexCurrencyWiseS.jsp");
		   }else{
			   if(but_pressed.equals("Save"))//if submit button pressed
			   {
			   	Logging.debug(" crid "+crid+" index id is "+id1+" indid is "+indid);
			   	if(id1==null || crid==null){			   		
			   		return fr= new ActionForward("/pages/reports/IndexCurrencyWiseS.jsp");
			   	}else{			   		
			   		QueryClass.SaveAsCurrencyIndex(indid,request);
			   	}
				IndexCalculatorCollection.addInIndexCurrencyWise(indid,request);//generate buffer for displaying table content for index selected
				return fr= new ActionForward("/pages/reports/IndexCurrencyWiseS.jsp");
			   }
			   if(but_pressed.equals("Reset"))//true if button pressed is reset
			   {
			   	Logging.debug("button pressed is Save");	
			    String button="Save";			
				return fr= new ActionForward("/pages/reports/IndexCurrencyWiseS.jsp");
			   }	
		   }
		   
		   return mapping.getInputForward();
		}
}

