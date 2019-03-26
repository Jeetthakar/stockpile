
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
//Session Expiration changes : Manoj Adekar
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
		String technology = (String)session.getAttribute("technology");
		int type = Integer.parseInt(request.getParameter("type"));
		
		String pathf = app.Connect.getCoolMenuspath();
		pathf = pathf + "CoolMenus/"+file_name;
		File f = new File (pathf);
		
		harrier.income.com.report.MakeXmlNew obj = new harrier.income.com.report.MakeXmlNew();
		Vector v2 = (Vector)session.getAttribute("ci2");
		log.info(" checking for type...");
		
		
		 if(type == 19)
		{
			String fr = (String)session.getAttribute("from");
			String to = (String)session.getAttribute("to");
			String indexname=(String)session.getAttribute("selectIndex");
			 var=(String)session.getAttribute("selectIndex");
			String iename=indexname;
			obj.create_file(var,type,iename,v2,fr,to,indexname);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexDivisor.xml\"");
		}
		else if(type == 8)
		{
			obj.create_file("1",type,null,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"LatestIndexDivisor.xml\"");
		}
		else if(type == 10)
		{
			String[] arr = request.getParameterValues("D1");
			Vector vid = (Vector)session.getAttribute("vec_ind");
			String fr = request.getParameter("from");
			String to = request.getParameter("to");
			obj.create_file2(vid,type,v2,fr,to);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexCompareOHLC.xml\"");
		}
		else if(type == 12)
		{
			String fr = (String)session.getAttribute("dt");					
			obj.create_file("1",type,null,v2,fr,null,null);
			
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexPerformance.xml\"");
		}
		else if(type == 14)
		{
			String[] arr = (String[])session.getAttribute("indexList");
			log.info("Index array  "+arr);
			String fr = (String)session.getAttribute("from");
			String to = (String)session.getAttribute("to");
			
			obj.create_file1(arr,type,v2,fr,to);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexReturn.xml\"");
		}
		else if(type == 15)
		{
			Vector vid = (Vector)session.getAttribute("ci1");
			String fr = request.getParameter("from");
			String to = request.getParameter("to");
			obj.create_file2(vid,type,v2,fr,to);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexCorrelation.xml\"");
		}
		
		else if(type == 20)
		{
			String fdate=(String)session.getAttribute("fromDate");
			String tdate=(String)session.getAttribute("toDate");
			var=(String)session.getAttribute("var");
			String iename=request.getParameter("iename");
			obj.create_file(var,type,iename,v2,fdate,tdate,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexPe_Pb.xml\"");
		}
		else if(type == 16)
		{		
			String fr = null; String to = null; String stk_name	= null;String iename = null;
		    if(technology =="Ajax"){
		    	fr = (String)session.getAttribute("from");
				to = (String)session.getAttribute("to");
				stk_name = (String)session.getAttribute("stkName");
				var =(String)session.getAttribute("var");
				iename=var;
		 	 }
		    else{
		    	 fr = request.getParameter("from");
				 to = request.getParameter("to");
				 stk_name= request.getParameter("stkName");
			     iename=var;   	  		    
		    }
			obj.create_file(var,type,iename,v2,fr,to,stk_name);
			response.setHeader ("Content-Disposition", "attachment; filename=\"CapitalChangeToUniverse.xml\"");
		}
		
		else if(type == 4)
		{
			
			obj.create_file("1",type,null,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexList.xml\"");
		}
		else if(type == 1)
		{
			String iename=request.getParameter("iename");
			obj.create_file(var,type,iename,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexCompositionReport.xml\"");
		}
		else if(type ==2)
		{
			String iename=var;
			obj.create_file(var,type,iename,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"CompanyWiseWeightage.xml\"");
		}
		else if(type == 3)
		{
			String iename=var;
			obj.create_file(var,type,iename,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndWiseWeightage.xml\"");
		}
		
		else if(type == 5)
		{
			
			
			String fr = request.getParameter("from");
			String to = request.getParameter("to");
			String iename=var;
			obj.create_file(var,type,iename,v2,fr,to,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"StockContribution.xml\"");
		}
		
		else if(type == 6)
		{
			// case for stock details
			String fr = request.getParameter("from");
			String to = request.getParameter("to");
			String stk_name= request.getParameter("stkName");
			String ind_name = request.getParameter("indName");
			String iename=ind_name;
			obj.create_file(var,type,iename,v2,fr,to,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"StockDetails.xml\"");
		}
		else if(type == 17)
		{
			var=(String)session.getAttribute("var");
			type=17;
			obj.create_file(var,type,null,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"InactiveStockList.xml\"");
		}
		else if(type == 21)
		{
			
			String exch_name= request.getParameter("exchName");
			
			String iename=var;
			obj.create_file(var,type,iename,v2,null,null,exch_name);
			response.setHeader ("Content-Disposition", "attachment; filename=\"StockList.xml\"");
		}
		
       else if(type == 22)
		{
			
			String fr = request.getParameter("from");
			String to = request.getParameter("to");
			obj.create_file(var,type,null,v2,fr,to,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"StockDivident.xml\"");
		}
		else if(type == 24)
		{
			
			String fr = (String)session.getAttribute("from"); 
			String to = (String)session.getAttribute("to");
			var=(String)session.getAttribute("varIndexId");	
			String iename=var;
			obj.create_file(var,type,iename,v2,fr,to,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexMovement.xml\"");
		}
		else if(type == 25)
		{
			
			String indExch=request.getParameter("indExch");
			String fr = request.getParameter("from");
			String to = request.getParameter("to");
			obj.create_file(var,type,null,v2,fr,to,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"TradedVolume.xml\"");
		}
		else if(type == 26)
		{
			
			obj.create_file("26",type,null,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"PortfolioBasket.xml\"");
		}
		else if(type == 27)
		{
			obj.create_file("27",type,null,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexCalculatorRpt.xml\"");
		}
		InputStream in = new FileInputStream(f);
		ServletOutputStream outs = response.getOutputStream();
		int bit = 256;
		int i = 0;
		try {

			        while ((bit) >= 0) {
        				            bit = in.read();
        				             if(bit!=-1)
        				               { 	outs.write(bit);
        			                   }
        			            }
        			} catch (IOException ioe) {
            			ioe.printStackTrace(System.out);
            		       }
            		outs.flush();
            		outs.close();
            		in.close();
      }//closing  of session code	
       
%>
