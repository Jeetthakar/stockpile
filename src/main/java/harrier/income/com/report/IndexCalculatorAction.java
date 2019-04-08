/*
 * Created on Mar 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;
import java.awt.image.IndexColorModel;

import app.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			IndexCalculatorCollection indexCalculatorCollection =new IndexCalculatorCollection();
			String id1=null;
			String but_pressed=indexCalculatorForm.getB1();	
			id1=request.getParameter("index_id");
			String indexV=indexCalculatorCollection.dbIndexValue(id1);
		   
			HttpSession session=request.getSession();
			session.setAttribute("indexvalN",indexV);
			
			
		   if(but_pressed==null){//no button pressed 
			   	id1=request.getParameter("index_id");//get index selected
			   //To get index value according to the LTP
			   	String indexVLtp=indexCalculatorCollection.computeIndexNormallyLtp(id1);
			   	session.setAttribute("indexvalNLtp",indexVLtp);
			   	
			   	if(id1==null){
			   		return fr= new ActionForward("/pages/reports/IndexCalculator.jsp");//if no index selected return control back to index calculator page
			   	}
				//IndexCalculatorCollection.addStocksInIndexCalculatorTablePrice(id1,request);//generate buffer for displaying table content for index selected
				//indexCalculatorCollection.insertIndexValues(form);		   
				 return fr= new ActionForward("/pages/reports/IndexCalculator.jsp");
		   }else{
			   if(but_pressed.equals("Submit"))//if submit button pressed
			   {
			   	/////////////////
				   String priceArr=null;
				   priceArr=indexCalculatorForm.getPriceArray();
				  
				  if((priceArr.equals(""))||(priceArr==null))
				  {
					  session.setAttribute("UserPriceTest",null);
				  }
				  else
				  {
					  session.setAttribute("UserPriceTest","yes");
				  }
				   // System.out.print("Price Value="+priceArr);
				   ////////////////
				   
				   id1=request.getParameter("index_id");
			   	Logging.debug("Index id = "+id1);
			   	if(id1==null){
			   		indexCalculatorForm.setIndexValue("0.00");//set index value to zero if no index selected
			   		return fr= new ActionForward("/pages/reports/IndexCalculator.jsp");
			   	}
				//IndexCalculatorCollection.addStocksInIndexCalculatorTablePriceNew(id1,priceArr,request);//generate buffer for displaying table content for index selected
				String indexValue=IndexCalculatorCollection.computeIndexNormallyNew(id1,priceArr,request,indexCalculatorForm);//compute index value for index selected
				indexCalculatorForm.setIndexValue(indexValue);
				//indexCalculatorCollection.insertIndexValues(form);		   
				//indexCalculatorForm.setB1(null);	
				return fr= new ActionForward("/pages/reports/IndexCalculator.jsp");
			   }
			   if(but_pressed.equals("Reset"))//true if button pressed is reset
			   {
			   	Logging.debug("button pressed is reset");	
			    String button="Reset";			
				return fr= new ActionForward("/pages/reports/IndexCalculator.jsp?opt=Reset");
			   }	
		   }
		   return mapping.getInputForward();
		}
}
