
<%@ page import="java.util.*,java.io.*"%>
<%@ page  import="harrier.income.com.report.*"%>
<%@ page language="java" import="app.*"%><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setHeader("Expires","0");

		LogonForm form = null;
			if(request.isRequestedSessionIdValid())	{	
				form = (LogonForm)session.getAttribute("user");
			}
			if(form==null ||(!request.isRequestedSessionIdValid())){
				response.sendRedirect("../userlogintemp.jsp");
			}		
		
		%>
<%		
	if(request.isRequestedSessionIdValid())	{

		String file_name = request.getParameter("filename");
		String var = request.getParameter("var");
		int type = Integer.parseInt(request.getParameter("type"));
		String technology = "noajax";
		technology = request.getParameter("technology");
		
		String pathf = app.Connect.getCoolMenuspath();
		pathf = pathf + "CoolMenus/"+file_name;
		File f = new File (pathf);
		//response.setContentType ("application/xls");
		harrier.income.com.report.MakeExcel obj = new harrier.income.com.report.MakeExcel();
		Vector v2 = (Vector)session.getAttribute("ci2");
		
		if(type == 1)
		{
			if(technology.equals("Ajax")){ 
				var=(String)session.getAttribute("var");
			}
			obj.create_file(var,type,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexCompositionReport.xls\"");
		}
		else if(type ==2)
		{
			obj.create_file(var,type,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"CompanyWiseWeightage.xls\"");
		}
		else if(type == 3)
		{
			if(technology.equals("Ajax")){ 
				var=(String)session.getAttribute("var");
			}
			obj.create_file(var,type,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndWiseWeightage.xls\"");
		}
		else if(type == 4)
		{
			
			obj.create_file("1",type,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexList.xls\"");
		}
		
		else if(type == 5)
		{
			String fr = request.getParameter("from");
			String to = request.getParameter("to");
			obj.create_file(var,type,v2,fr,to,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"StockContribution.xls\"");
		}
		else if(type == 6)
		{	
			String fr =null; String to = null; String var1 = null; String stk_name = null; String ind_name = null;
			
		 if(technology.equals("Ajax")){    // If request has come from Page using Ajax
				
				fr = (String)session.getAttribute("from");
				to = (String)session.getAttribute("to");
				var1 = (String)session.getAttribute("var1");
				stk_name= (String)session.getAttribute("stkName");
				ind_name = (String)session.getAttribute("indName");
			}
			else{	
				//if reuest has come from page using Struts
				// case for stock details
				fr = request.getParameter("from");
				to = request.getParameter("to");
				var1 = request.getParameter("var1");
				stk_name= request.getParameter("stkName");
				ind_name = request.getParameter("indName");
				
			}
			obj.create_file_stock_detail(var,type,v2,fr,to,var1,"",stk_name,ind_name,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"StockHighLow.xls\"");						
		}
		else if(type == 8)
		{
			obj.create_file("1",type,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"LatestIndexDivisor.xls\"");
		}
		else if(type == 10)
		{
			String[] arr = request.getParameterValues("D1");
			Vector vid = (Vector)session.getAttribute("vec_ind");
			String fr = request.getParameter("from");
			String to = request.getParameter("to");
			obj.create_file(vid,type,v2,fr,to);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexCompareOHLC.xls\"");
		}
		else if(type == 12)
		{
			String fr11 = (String)session.getAttribute("dt");					//request.getParameter("from");
			obj.create_file("1",type,v2,fr11,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexPerformance.xls\"");
		}
		else if(type == 13)
		{
			String fr = request.getParameter("from");
			String to = request.getParameter("to");
			obj.create_file(var,type,v2,fr,to,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"StockPerformanceReport.xls\"");
		}
		else if(type == 14)
		{
			String[] arr = (String[])session.getAttribute("indexList");
			String fr14 = (String)session.getAttribute("from");
			String to14 = (String)session.getAttribute("to");
			
			obj.create_file(arr,type,v2,fr14,to14);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexReturn.xls\"");
		}
		else if(type == 15)
		{
			Vector vid = (Vector)session.getAttribute("ci1");
			String fr = request.getParameter("from");
			String to = request.getParameter("to");
			obj.create_file(vid,type,v2,fr,to);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexCorrelation.xls\"");
		}
		else if(type == 16)
		{	
			String fr =null; String to = null; String stk_name = null;
			
			 if(technology.equals("Ajax")){    // If request has come from Page using Ajax				
				fr = (String)session.getAttribute("from");
				to = (String)session.getAttribute("to");				
				var = (String)session.getAttribute("var");
				stk_name= (String)session.getAttribute("stkName");			
			}
			else{	//if reuest has come from page using Struts
			
				fr = request.getParameter("from");
				to = request.getParameter("to");				
				var = request.getParameter("var");
				stk_name= request.getParameter("stkName");				
			
			}				
			obj.create_file(stk_name,type,v2,fr,to,var);
			response.setHeader ("Content-Disposition", "attachment; filename=\"CapitalChangeToUniverse.xls\"");
						
		}
		else if(type == 17)
		{
			var=(String)session.getAttribute("var");
			type=17;
			obj.create_file(var,type,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"InactiveStockList.xls\"");
		}
		else if(type == 18)
		{
			obj.create_file(var,type,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"ShareHoldingPattern.xls\"");
		}
		else if(type == 19)
		{
			String fr = (String)session.getAttribute("from");
			String to = (String)session.getAttribute("to");
			String indexname=(String)session.getAttribute("selectIndex");
			
			obj.create_file(var,type,v2,fr,to,indexname);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexDivisor.xls\"");
		}
		else if(type == 20)
		{

			log.info("size in download.."+v2.size());
			v2=(Vector)session.getAttribute("ci2");
			String fdate=(String)session.getAttribute("fromDate");
			String tdate=(String)session.getAttribute("toDate");
			var=(String)session.getAttribute("var");
			log.info("fdate"+fdate+"tdate"+tdate+"var"+var);
			obj.create_file(var,type,v2,fdate,tdate,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexPe_Pb.xls\"");
		}
		else if(type == 21)
		{
			//log.info("size in download.."+v2.size());
			String exch_name= request.getParameter("exchName");
			obj.create_file(var,type,v2,null,null,exch_name);
			response.setHeader ("Content-Disposition", "attachment; filename=\"StockList.xls\"");
		}
		else if(type == 22)
		{	
			if(technology.equals("Ajax")){
				String fr =(String) session.getAttribute("from");
				String to =(String) session.getAttribute("to");
				String filter = (String)session.getAttribute("var");
				String indExch=(String)session.getAttribute("indExch");
				log.info("in if"+filter+fr );
				obj.create_file_stock_detail(var,type,v2,fr,to,filter,null,indExch,null,null);
				
			}else{
				String fr = request.getParameter("from");
				String to = request.getParameter("to");
				String filter = request.getParameter("var");
				String indExch=request.getParameter("indExch");
				obj.create_file_stock_detail(var,type,v2,fr,to,filter,null,indExch,null,null);
				
			}	
			response.setHeader ("Content-Disposition", "attachment; filename=\"StockDivident.xls\"");
				
		}
		else if(type == 23)
		{
			String filter =(String)session.getAttribute("filter");
			String index=(String)session.getAttribute("index");
			String fDate=(String)session.getAttribute("fDate");
			String stock=(String)session.getAttribute("stock");
			Vector temp = new Vector();
			temp = (Vector)session.getAttribute("ci2");
			obj.create_file_stock_detail(null,type,temp,fDate,index,filter,stock,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"StockDetail.xls\"");
		}
		else if(type == 24)
		{
			type=24;
			String fr = (String)session.getAttribute("from"); //request.getParameter("from");
			String to = (String)session.getAttribute("to");
			var=(String)session.getAttribute("varIndexId");	//request.getParameter("to");
			
			obj.create_file(var,type,v2,fr,to,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexMovement.xls\"");
		}
		else if(type == 25)
		{	
			String indExch=null; String fr = null; String to = null;
			if(technology.equals("Ajax")){   // If request has come from Page using Ajax
				
				 var = (String)session.getAttribute("var");
				 fr = (String)session.getAttribute("from");
				 to =(String)session.getAttribute("to");
				 indExch = (String ) session.getAttribute("indExch");						
			}
			else{		 // If request has come from Page using Struts
				 indExch=request.getParameter("indExch");
				 fr = request.getParameter("from");
				 to = request.getParameter("to");		
			}
			obj.create_file(var,type,v2,fr,to,indExch);
			response.setHeader ("Content-Disposition", "attachment; filename=\"TradedVolume.xls\"");
		}
		//By Manoj Adekar
		else if(type == 26)
		{
			
			obj.create_file("26",type,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexTisCalculator.xls\"");
		}
		else if(type == 27)
		{
			obj.create_file("27",type,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexCalculatorRpt.xls\"");
		}
		
		
		InputStream in = new FileInputStream(f);
		ServletOutputStream outs = response.getOutputStream();
		int bit = 256;
		int i = 0;
		try {

			while ((bit) >= 0) {
        				bit = in.read();
        				outs.write(bit);
        			}
        			} catch (IOException ioe) {
            			ioe.printStackTrace(System.out);
            		}
            		outs.flush();
            		outs.close();
            		in.close();	
      
      } 
%>
