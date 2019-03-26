/*
 * Created on Sep 7, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * @author ashishi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReportPerAction extends Action {
	Logger Logging = Logger.getLogger(ReportPerAction.class);
	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Logging.debug("inside execute");
		ReportPerForm myForm = (ReportPerForm) form;
		String report = myForm.getSelectReport();
		Logging.debug("inside execute"+report);
		String hv = myForm.getHiddenVar();
		String temp1=myForm.getTemp1();
		Logging.debug("temp1---->"+temp1);
		
		if (hv.equals("yes")) {
			String str = null;
			str=myForm.inserdata();
			myForm.setHiddenVar(null);// this property is available in session scope,here we are making it null so that it will
									 //it will be freshly used next time.
			if(str.equals("true"))
			{	
				myForm.setSelectedStocks(null);
				myForm.setViewButton(null);
				return (new ActionForward("/pages/masters/roleAdded.jsp?flag=Batchreport&value=insert"));
			}else
			{	
				myForm.setSelectedStocks(null);
				myForm.setViewButton(null);
				return (new ActionForward("/pages/masters/roleAdded.jsp?flag=Batchreport&value=faill"));
			}
		}
		
		//added by Vikram on 08/01/2007
		//preference update action on click of Update button 
		// added by Vikram on 08/01/2007
		String updateButton = myForm.getUpdateButton();
		if(updateButton!=null && ! updateButton.equals("")){
		
			if(updateButton.equals("Update"))
			{
				String str = null;
				str=myForm.updatetable();
				myForm.setUpdateButton(null);// removing the values from session
				if(str.equals("true"))
				{
					//myForm.setSelectedStocks(null);
					myForm.setViewButton(null);
					return (new ActionForward("/pages/masters/roleAdded.jsp?flag=Batchreport&value=update"));
				}else
				{
					//myForm.setSelectedStocks(null);
					myForm.setViewButton(null);
					return (new ActionForward("/pages/masters/roleAdded.jsp?flag=Batchreport&value=faill"));
				}
			}
		}		
		//action on clicking the continue Radio button
		String radioButton=myForm.getRadioButton();
		if(radioButton!=null && ! radioButton.equals("")){
				if(radioButton.equals("continue")){
												
					myForm.setRadioButton(null);// removing the values from session
					return (new ActionForward("/pages/reports/ReportPre.jsp?radioButton="+radioButton));
				}
				else{
					myForm.setRadioButton(null);
				}
		}		
		//resetting action on click of Reset button 
		String resetButton=myForm.getResetButton();
		if(resetButton!=null && ! resetButton.equals("")){
			if(resetButton.equals("Reset")){
				myForm.resetOnResetButton();
			}
		}		
		//resetting filter and some others releted to filter for report preference 5(Traded Volume and Stock Dividend) 
		String resetFilterHidVar=myForm.getResetFilterHidVar();
		if(resetFilterHidVar!=null && ! resetFilterHidVar.equals("")){
			if(resetFilterHidVar.equals("yes")){
				if(myForm.getFilter()!=null){
					if(myForm.getFilter().equals("1")||myForm.getFilter().equals("2")){
						myForm.resetOnFilterOptions();
					}
				}
			}
		}
		//resetting some object  
		String resetSomeObj=myForm.getResetSomeObj();
		if(resetSomeObj!=null && ! resetSomeObj.equals("")){
			if(resetSomeObj.equals("yes")){
				myForm.setDifference(null);
			}
		}
		//conditional view of components on selection of report prefe
		String selectReportVal =myForm.getSelectReport().trim();
		Logging.debug("------->>>"+selectReportVal);
		
		// in this condition index component will be visible
		if(selectReportVal!=null && ! selectReportVal.equals("")){
			
			//1:Index Divisor,
			//2:Index Composition
			//3:Index wise PE,PB
			//0:not selected
			//6:Index in Different Currency
			//7:Company Weightage
			//8:Industry Weightage
			//9:Stock Contribution To Change In Index
			
			if(selectReportVal.equals("1")||
			   selectReportVal.equals("2")||
			   selectReportVal.equals("3")||
			   selectReportVal.equals("0")||
			   selectReportVal.equals("6")||
			   selectReportVal.equals("7")||
			   selectReportVal.equals("8")||
			   selectReportVal.equals("9")||
			   selectReportVal.equals("16")
			   )
			{
				//in this condition do not display filter,stockand view button components
				
				if(selectReportVal.equals("0")){
					myForm.setHideFilter("notHide");
					return (new ActionForward("/pages/reports/ReportPre.jsp?selectReportFlag=selectReportVal"));
				}
				
				myForm.setHideFilter("Hide");
				return (new ActionForward("/pages/reports/ReportPre.jsp?selectReportFlag=selectReportVal"));
			}
		}
		//conditional display on capital Change
		if(selectReportVal!=null && ! selectReportVal.equals("")){
			//5:Capital Change
			if(selectReportVal.equals("5") || selectReportVal.equals("0")){
				myForm.setHideFilter("notHide");
				return (new ActionForward("/pages/reports/ReportPre.jsp?capitalChangeFlag=capitalChangeVal"));
			}
		}
		
		String viewButton=myForm.getViewButton();
		System.out.print(">>>-------->>>>>"+viewButton);
		String selectedStocks[]=myForm.getSelectedStocks();
		for(int i=0;i<selectedStocks.length;i++){
			Logging.debug(">>>--selectedStocks------>>>>>"+selectedStocks[i]);
			
		}	
		//conditional display on stock Details
		if(selectReportVal!=null && ! selectReportVal.equals("")){
			//4:Stock Details
			if(selectReportVal.equals("4") || selectReportVal.equals("0")){
				myForm.setHideFilter("notHide");
				if(selectReportVal.equals("0")){
					myForm.setIndex(null);
				}
				
				return (new ActionForward("/pages/reports/ReportPre.jsp?stockDetailsFlag=stockDetailsVal"));
			}
		}
		//	conditional display of stock detail on click of view button
		
		
		
				
	/*	if(viewButton!=null && ! viewButton.equals("")){
			
			if(viewButton.equals("View") || selectReportVal.equals("0")){
				myForm.setHideFilter("notHide");
				return (new ActionForward("/pages/reports/ReportPre.jsp?stockDetailsFlag=stockDetailsVal"));
			}
		}
	*/	
	return (mapping.getInputForward());

	}

}