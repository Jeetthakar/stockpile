
<!------------FileDownload_Pdf.jsp for xml file --------------- -->
<!------------------Kapil------------------------------------------- -->

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
		String iename = request.getParameter("iename");
		String var = request.getParameter("var");
		int type = Integer.parseInt(request.getParameter("type"));
		String fdate = request.getParameter("from");
		String tdate = request.getParameter("to");
		Vector v2=new Vector();
		Vector v3=new Vector();
		String pathf = app.Connect.getCoolMenuspath();
		pathf = pathf + "CoolMenus/"+file_name;
		File f = new File (pathf);
		//response.setContentType ("application/xls");
		Make_Pdf obj = new Make_Pdf();
		
		log.info(" checking for type...");
		
		if(type == 4)
		{	 v2 = (Vector)session.getAttribute("ci2");
			log.info("size in download.."+v2.size());
			obj.create_file(null,type,null,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexList.pdf\"");
		}
		else if(type == 26)
		{	 v2 = (Vector)session.getAttribute("ci2");
			log.info("size in download.."+v2.size());
			obj.create_file(null,type,null,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"PortfolioBasket.pdf\"");
		}
		else if(type == 27)
		{	 v2 = (Vector)session.getAttribute("ci2");
			log.info("size in download.."+v2.size());
			obj.create_file(null,type,null,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexCalculatorRpt.pdf\"");
		}
		else if(type == 12)
		{
			v2 = (Vector)session.getAttribute("ci2");
			
			//String sdate = (String)session.getAttribute("sdate");
			//String to = (String)session.getAttribute("tdate19");
			//String indexname=(String)session.getAttribute("selectIndex19");
			//var=(String)session.getAttribute("var19");
			//log.info("from "+fr+"to"+to+"indexname"+indexname+"var"+var);
			//session.setAttribute("ci2",v2);
			//session.setAttribute("var",var);
			//session.setAttribute("selectIndex",indexname);
			
			obj.create_file(null,type,null,v2,fdate,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexPerformance.pdf\"");
		}
		/*else if(type == 1)
		{
			v2=(Vector)request.getSession().getAttribute("ci2");
			log.info("size of v2="+v2.size());
			obj.create_file(null,type,null,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexComposition.pdf\"");
		
		}*/
		//for OHLC
		
else if(type == 10)
		{
			
			String[] arr = request.getParameterValues("D1");
			 v2 = (Vector)session.getAttribute("ci2");// for 1st vector
			Vector vid = (Vector)session.getAttribute("vec_ind");
			String[] temp = (String[])session.getAttribute("arr");
			String fr = request.getParameter("from");
			String to = request.getParameter("to");
			obj.create_file(vid,type,iename,v2,fr,to);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexCompareOHLC.pdf\"");
		}
		else if(type == 8)
		{
			v2=(Vector)request.getSession().getAttribute("ci2");
			log.info("size of v2="+v2.size());
			obj.create_file(null,type,null,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"LatestIndexDivisor.pdf\"");
		
		}
		else if(type == 15)
		{
			v2=(Vector)request.getSession().getAttribute("ci2");
			v3=(Vector)request.getSession().getAttribute("ci1");
			//v3 = (Vector)session.getAttribute("ci2");
			log.info("size of v2="+v2.size());
			obj.create_file1(v3,null,type,null,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexCorrelation.pdf\"");
		
		}
		else if(type == 17)
		{
			v2=(Vector)request.getSession().getAttribute("ci2");
			log.info("size of v2="+v2.size());
			obj.create_file(null,type,null,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"InactiveStock.pdf\"");
		
		}
		
		else if(type == 10)
		{
			v2=(Vector)request.getSession().getAttribute("ci2");
			
			obj.create_file(null,type,null,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexCompareOHLC.pdf\"");
		}
		else if(type == 24)
		{
			v2=(Vector)request.getSession().getAttribute("ci2");
			log.info("size of v2="+v2.size());
			obj.create_file(null,type,null,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexMovement.pdf\"");
		
		}
		else if(type == 14)
		{
			v2=(Vector)request.getSession().getAttribute("ci2");
			log.info("size of v2="+v2.size());
			obj.create_file(null,type,null,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexReturn.pdf\"");
		
		}
		else if(type == 21)
		{
			v2=(Vector)request.getSession().getAttribute("ci2");
			log.info("size of v2="+v2.size());
			obj.create_file(null,type,null,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"StockList.pdf\"");
		
		}
		if(type == 1)
		{	
			//iename = request.getParameter("iename1");
			v2 = (Vector)session.getAttribute("ci2");
			obj.create_file(null,type,null,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexCompositionReport.pdf\"");
		}
		else if(type ==2)
		{
			//iename = request.getParameter("iename2");
			v2 = (Vector)session.getAttribute("ci2");
			obj.create_file(null,type,null,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"CompanyWiseWeightage.pdf\"");
		}
		else if(type == 3)
		{
			//iename = request.getParameter("iename3");
			v2 = (Vector)session.getAttribute("ci2");
			obj.create_file(null,type,null,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndWiseWeightage.pdf\"");
		}
		else if(type == 5)
		{
			//iename = request.getParameter("iename5");
			v2 = (Vector)session.getAttribute("ci2");
			//String fr = request.getParameter("from");
			//String to = request.getParameter("to");
			obj.create_file(null,type,null,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"StockContribution.pdf\"");
		}
		else if(type == 6)
		{
			// case for stock details
			//String var1 = request.getParameter("var1");
			//String stk_name= request.getParameter("stkName");
			//String ind_name = request.getParameter("indName");
			//iename =(String)session.getAttribute("iename6");
			v2 = (Vector)session.getAttribute("ci2");
			obj.create_file(null,type,null,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"StockDetails.pdf\"");
		}
		else if(type == 19)
		{
			//iename = request.getParameter("iename19");
			v2 = (Vector)session.getAttribute("ci2");
			
			
			obj.create_file(null,type,null,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexDivisor.pdf\"");
		}
		else if(type == 20)
		{
			//iename = request.getParameter("iename20");
			v2=(Vector)request.getSession().getAttribute("ci2");
			obj.create_file(null,type,null,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexPe_Pb.pdf\"");
		
		}
		
		else if(type == 22)
		{
			//iename = request.getParameter("iename22");
			v2 = (Vector)session.getAttribute("ci2");
			obj.create_file(null,type,null,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"StockDivident.pdf\"");
		}
		else if(type == 25)
		{
			//iename = request.getParameter("iename25");
			v2 = (Vector)session.getAttribute("ci2");
			//String indExch=request.getParameter("indExch");
			obj.create_file(null,type,null,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"TradedVolume.pdf\"");
		}
		else if(type == 16)
		{
			//iename = request.getParameter("iename16");
			v2 = (Vector)session.getAttribute("ci2");
			//obj.create_file(stk_name,type,v2,fr,to,var);
			obj.create_file(null,type,null,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"CapitalChangeToUniverse.pdf\"");
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
        	}    catch (IOException ioe) {
            			ioe.printStackTrace(System.out);
            		  }
            		outs.flush();
            		outs.close();
            		in.close();	
       
   }//closing  of session code 
%>	
