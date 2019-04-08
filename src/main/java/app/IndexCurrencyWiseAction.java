/*
 * Created on Mar 29, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author sunil
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IndexCurrencyWiseAction extends Action{
	Logger Logging = Logger.getLogger(IndexCurrencyWiseAction.class);
	/**
	 * Action forward class for IndexCalculator.
	 */
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)
		{
			ActionForward fr = null;
			IndexCalculatorForm indexCalculatorForm=(IndexCalculatorForm) form;
			String id1=null;			
		   String but_pressed=indexCalculatorForm.getB1();	
		   id1=request.getParameter("index_id");//get index selected
		   	String indid=indexCalculatorForm.getIndex_id();
		   	String[] crid=request.getParameterValues("currencyid");		
		   if(but_pressed==null){//no button pressed 			   	
			   	if(id1==null){
			   		return fr= new ActionForward("/pages/IndexCurrencyWise.jsp");//if no index selected return control back to index calculator page
			   	}
				IndexCalculatorCollection.addInIndexCurrencyWise(id1,request);//generate buffer for displaying table content for index selected
				return fr= new ActionForward("/pages/IndexCurrencyWise.jsp");
		   }else{
			   if(but_pressed.equals("Save"))//if submit button pressed
			   {
			   	Logging.debug(" crid "+crid+" index id is "+id1+" indid is "+indid);
			   	if(id1==null || crid==null){			   		
			   		return fr= new ActionForward("/pages/IndexCurrencyWise.jsp");
			   	}else{			   		
			   		QueryClass.SaveAsCurrencyIndex(indid,request);
			   	}
				IndexCalculatorCollection.addInIndexCurrencyWise(indid,request);//generate buffer for displaying table content for index selected
				return fr= new ActionForward("/pages/IndexCurrencyWise.jsp");
			   }
			   if(but_pressed.equals("Reset"))//true if button pressed is reset
			   {
			   	Logging.debug("button pressed is Save");	
			    String button="Save";			
				return fr= new ActionForward("/pages/IndexCurrencyWise.jsp");
			   }	
		   }
		   
		   return mapping.getInputForward();
		}
}
