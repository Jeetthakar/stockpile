package harrier.income.com.report;

import harrier.income.com.FormBean.ListTypeClass;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author neha
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BatchReportAction extends Action{
	 Logger Logging = Logger.getLogger(BatchReportAction.class);
	public ActionForward execute( ActionMapping mapping, ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response )
	throws IOException, ServletException {
		ActionForward fr;
		BatchReportForm brf=(BatchReportForm)form;
		HttpSession sess=request.getSession();
		
		String exeButton=brf.getExecuteButton();
		String radval=brf.getRadioButton();
		String cradio=brf.getCheckradio();
		
				
		String todate=request.getParameter("tod");
		String fromdate=request.getParameter("fromd");
		brf.getTableData();
		brf.getTableDatnew();
		
		String selectUser = brf.getSelectUser();
		String str[]=null;
		str=brf.getSelectReport();
		
		if(cradio.equals("yes"))
		{
			brf.setRadioButton(null);
		}
		if(exeButton !=null && exeButton.equals("Execute") && selectUser.equals("1")){
			if(radval.equals("all"))
			{
								
				brf.getTabledata3();
				brf.getTableData2();
				brf.getIndex_arraylist();
				brf.getCurrencyParam();			
				return fr= new ActionForward("/pages/reports/PreIndexReport.jsp");
			}
			else
			{
				
				if(str!=null){
					
					for(int i=0;i<str.length;i++)
					{
						String s=str[i];
						Logging.debug("report name " +s); 
						
						if(s.equals("Index Composition"))
						{
							
							brf.getTabledata3();
							
							
						}
						else if(s.equals("Index Wise PE,PB"))
						{
							
							brf.getIndex_arraylist();
							
							
							
						}
						else if(s.equals("Index Divisor"))
						{
							
							brf.getTableData2();
							
						}
						else if(s.equals("Index In Different Currency"))
						{
							
							brf.getCurrencyParam();	
							
						}
					} 
				}//end of if
				
				return fr= new ActionForward("/pages/reports/PreIndexSelectReport.jsp");
			}
			
		}
		if(exeButton !=null && exeButton.equals("Execute") && selectUser.equals("2")){
			if(radval.equals("all"))
			{
			brf.getTableDatas();
			brf.getTableDatac();
			
			
			return fr= new ActionForward("/pages/reports/BatReportOut.jsp");
			}
			else
			{
				if(str!=null){
					for(int i=0;i<str.length;i++)
					{
						String s=str[i];
						Logging.debug("report name " +s); 
						if(s.equals("Stock Details"))
						{
							
							brf.getTableDatas();
							
							
						}
						else if(s.equals("Capital Change"))
						{
							
							brf.getTableDatac();
							
															
						}
						
					}
				}//end of if
				
				return fr= new ActionForward("/pages/reports/BatReportSelectOut.jsp");
			}
			
		}
		
		if(exeButton !=null && exeButton.equals("Execute") && selectUser.equals("3")){
			if(radval.equals("all"))
			{
				brf.getCompanyWeightage();
				brf.getIndweighttable();
				brf.getStockcotriIndexchange();
				return fr= new ActionForward("/pages/reports/WeightReports1.jsp");
			}
			else
			{
				if(str!=null){
					for(int i=0;i<str.length;i++)
					{
						String s=str[i];
						Logging.debug("report name " +s); 
						if(s.equals("Industry Weightage"))
						{
							
							brf.getIndweighttable();
														
							
						}
						else if(s.equals("Company Weightage"))
						{
							
							brf.getCompanyWeightage();
																				
							
						}
						else if(s.equals("Stock Contribution To Change In Index"))
						{
							brf.getStockcotriIndexchange();
							
														
						}
					}
				}//end of if
				
				return fr= new ActionForward("/pages/reports/WeightReportSelect.jsp");
			}
			
		}
		
		if(exeButton !=null && exeButton.equals("Execute") && selectUser.equals("4")){
			if(radval.equals("all"))
			{
				
				brf.getFinal_Vector();
				brf.getOhlcParam();
				brf.getCorelParam();
			
				Vector vcom= new Vector();
				vcom=brf.getComParam();
		
				String fromd1=(String)vcom.get(0);
				String toDat1=(String)vcom.get(1);
				String[] var2={ (String)vcom.get(2),(String)vcom.get(3)};
				try {
		 			
		 					 			
		 			sess.setAttribute("indexids",var2);
		 			sess.removeAttribute("sfdate");
		 			sess.setAttribute("sfdate",fromd1);
		 			sess.removeAttribute("stdate");
		 			sess.setAttribute("stdate",toDat1);
				} catch(Exception e){
 				
 			}
						
			return fr= new ActionForward("/pages/reports/indexwiseReports1.jsp");
			} else {
				
				if(str!=null){
					for(int i=0;i<str.length;i++)
					{
						String s=str[i];
						Logging.debug("report name " +s); 
						
						if(s.equals("Index Comparison"))
						{
							Vector vcom= new Vector();
							vcom=brf.getComParam();
							
							String fromd1=(String)vcom.get(0);
							String toDat1=(String)vcom.get(1);
							String[] var2={ (String)vcom.get(2),(String)vcom.get(3)};
				 			try{
						 			
						 			
						 			
						 			sess.setAttribute("indexids",var2);
						 			sess.removeAttribute("sfdate");
						 			sess.setAttribute("sfdate",fromd1);
						 			sess.removeAttribute("stdate");
						 			sess.setAttribute("stdate",toDat1);
				 			}catch(Exception e){
				 				
				 			}
				 			
						}	
						else if(s.equals("Index Returns And Volatility"))
						{
							
							brf.getFinal_Vector();
							
							
						}
				 		else if(s.equals("Index Correlation"))
						{
							
				 			brf.getCorelParam();
							
						}
				 		else if(s.equals("Index Compare OHLC"))
						{
							
				 			brf.getOhlcParam();
							
						}
						
						
					}
				}//end of if
				
				return fr= new ActionForward("/pages/reports/indexwiseReportSelect.jsp");
			}
			
		}
		
		
		if(exeButton !=null && exeButton.equals("Execute") && selectUser.equals("5")){
			
			if(radval.equals("all"))
			{
				brf.getTableDataSD();
				brf.getTableDataTr();
				request.getSession().setAttribute("ci2",brf.getTrdVolVec());	
				return fr= new ActionForward("/pages/reports/stockReports1.jsp");
				
			}
			else {
				if(str != null){
					for(int i=0;i<str.length;i++)
					{
						String s=str[i];
						Logging.debug("report name " +s); 
						if(s.equals("Stock Dividend"))
						{
							
							brf.getTableDataSD();
														
						}
						else if(s.equals("Traded Volume"))
						{
							
							brf.getTableDataTr();
													
						}
						
					}
				}//end of if
				
				return fr= new ActionForward("/pages/reports/stockReportSelect.jsp");
			}
				
		}
				
		String resetButHV=brf.getResetButHV();
		
		if(resetButHV !=null && resetButHV.equals("yes")){
			brf.setSelectUser("0");
			brf.setRadioButton(null);
			brf.setFromd(null);
			brf.setTod(null);
			return fr= new ActionForward("/pages/reports/BatchReports.jsp");
			
		}
						
		return  mapping.getInputForward();
		
	}
	
}

