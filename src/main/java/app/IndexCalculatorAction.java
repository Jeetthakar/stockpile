/*
 * Created on Mar 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
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
public class IndexCalculatorAction extends Action{
	Logger Logging = Logger.getLogger(IndexCalculatorAction.class);
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
		   if(but_pressed==null){//no button pressed 
			   	id1=request.getParameter("index_id");//get index selected
			   	if(id1==null){
			   		return fr= new ActionForward("/pages/IndexCalculator.jsp");//if no index selected return control back to index calculator page
			   	}
				IndexCalculatorCollection.addStocksInIndexCalculatorTablePrice(id1,request);//generate buffer for displaying table content for index selected
				//indexCalculatorCollection.insertIndexValues(form);		   
				 return fr= new ActionForward("/pages/IndexCalculator.jsp");
		   }else{
			   if(but_pressed.equals("Submit"))//if submit button pressed
			   {
			   	id1=request.getParameter("index_id");
			   	if(id1==null){
			   		indexCalculatorForm.setIndexValue("0.00");//set index value to zero if no index selected
			   		return fr= new ActionForward("/pages/IndexCalculator.jsp");
			   	}
				IndexCalculatorCollection.addStocksInIndexCalculatorTablePrice(id1,request);//generate buffer for displaying table content for index selected
				String indexValue=IndexCalculatorCollection.computeIndexNormally(id1,request);//compute index value for index selected
				indexCalculatorForm.setIndexValue(indexValue);
				//indexCalculatorCollection.insertIndexValues(form);		   
				 return fr= new ActionForward("/pages/IndexCalculator.jsp");
			   }
			   if(but_pressed.equals("Reset"))//true if button pressed is reset
			   {
			   	Logging.debug("button pressed is reset");	
			    String button="Reset";			
				return fr= new ActionForward("/pages/IndexCalculator.jsp?opt=Reset");
			   }	
		   }
		   
		   return mapping.getInputForward();
		}
}
