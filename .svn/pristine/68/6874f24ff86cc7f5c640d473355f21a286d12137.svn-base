
<!------------FileDownloadXml.jsp for xml file --------------- -->
<!------------------neha------------------------------------------- -->

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
%>


<%		String file_name = request.getParameter("filename");
		String iename =null;
		
		String var = request.getParameter("var");
		int type = Integer.parseInt(request.getParameter("type"));
		String fdate = request.getParameter("from");
		String tdate = request.getParameter("to");
		Vector v2=new Vector();
		String pathf = app.Connect.getCoolMenuspath();
		pathf = pathf + "CoolMenus/"+file_name;
		File f = new File (pathf);
		//response.setContentType ("application/xls");
		harrier.income.com.report.MakeXml obj = new harrier.income.com.report.MakeXml();
		
		if(type == 1)
		{	 v2 = (Vector)session.getAttribute("ci2Composition");
			 iename = (String)session.getAttribute("iename1");
			
			obj.create_file(var,type,iename,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexCompositionReport.xml\"");
		}
		else if(type ==2)
		{
			iename = (String)session.getAttribute("iename2");
			v2 = (Vector)session.getAttribute("ci2Cwv");
			obj.create_file(var,type,iename,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"CompanyWiseWeightage.xml\"");
		}
		/*
		else if(type == 10)
		{
			
			String[] arr = request.getParameterValues("D1");
			 v2 = (Vector)session.getAttribute("ci2h");// for 1st vector
			Vector vid = (Vector)session.getAttribute("vec_ind");
			String fr = request.getParameter("from");
			String to = request.getParameter("to");
		
			obj.create_file(vid,type,iename,v2,fr,to);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexCompareOHLC.xml\"");
		}
		*/
		else if(type == 10)
		{
			v2 = (Vector)session.getAttribute("ci2h");
			String[] arr = request.getParameterValues("D1");
			Vector vid = (Vector)session.getAttribute("vec_ind");
			String fr = request.getParameter("from");
			String to = request.getParameter("to");
			obj.create_file2(vid,type,v2,fr,to);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexCompareOHLC.xml\"");
		}
		else if(type == 14)
		{
			String[] arr = (String[])session.getAttribute("indexList");
			 v2 = (Vector)session.getAttribute("ci2IRV");
			String fr14 = request.getParameter("from");
			String to14 = request.getParameter("to");
			obj.create_file1(arr,type,v2,fr14,to14);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexReturn.xml\"");
		}
		else if(type == 15)
		{
			Vector vid = (Vector)session.getAttribute("ci1");
			 v2 = (Vector)session.getAttribute("ci2");
			String fr = request.getParameter("from");
			String to = request.getParameter("to");
			obj.create_file2(vid,type,v2,fr,to);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexCorrelation.xml\"");
		}
		else if(type == 19)
		{
			iename = (String)session.getAttribute("iename19");
			v2 = (Vector)session.getAttribute("ci2divisor");
			String indexname=(String)session.getAttribute("selectIndex19");
			var=(String)session.getAttribute("var19");
			
			session.setAttribute("ci2",v2);
			session.setAttribute("var",var);
			session.setAttribute("selectIndex",indexname);
			
			obj.create_file(var,type,iename,v2,fdate,tdate,indexname);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexDivisor.xml\"");
		}
		else if(type == 20)
		{
			iename = (String)session.getAttribute("iename20");
			v2=(Vector)request.getSession().getAttribute("ci2pepb");
			log.info("size of v2="+v2.size());
			obj.create_file(var,type,iename,v2,fdate,tdate,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexPe_Pb.xml\"");
		
		}
		else if(type == 3)
		{
			iename = (String)session.getAttribute("iename3");
			v2 = (Vector)session.getAttribute("ci2Iww");
			obj.create_file(var,type,iename,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndWiseWeightage.xml\"");
		}
		else if(type == 5)
		{
			iename = (String)session.getAttribute("iename5");
			v2 = (Vector)session.getAttribute("ci2Scc");
			obj.create_file(var,type,iename,v2,fdate,tdate,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"StockContribution.xml\"");
		}
		else if(type == 6)
		{
			
			iename = (String)session.getAttribute("iename6");
			v2 = (Vector)session.getAttribute("ci2SDetails");
			
			obj.create_file(var,type,iename,v2,fdate,tdate,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"StockDetails.xml\"");
		}
		
		else if(type == 22)
		{
			iename = (String)session.getAttribute("iename22");
			v2 = (Vector)session.getAttribute("ci2SD");
			obj.create_file(var,type,iename,v2,fdate,tdate,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"StockDivident.xml\"");
		}
		else if(type == 16)
		{
			iename = (String)session.getAttribute("iename16");
			String stk_name= request.getParameter("stkName");
			v2 = (Vector)session.getAttribute("ci2Cap");
			
			obj.create_file(var,type,iename,v2,fdate,tdate,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"CapitalChangeToUniverse.xml\"");
		}
		
		else if(type == 25)
		{
			iename = (String)session.getAttribute("iename25");
			v2 = (Vector)session.getAttribute("ci2Tr");
			String indExch=request.getParameter("indExch");
			obj.create_file(var,type,iename,v2,fdate,tdate,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"TradedVolume.xml\"");
		}
		InputStream in = new FileInputStream(f);
		ServletOutputStream outs = response.getOutputStream();
		int bit = 256;
		int i = 0;
		try {

			      while ((bit) >= 0) {
        				bit = in.read();
        				if(bit!=-1)
        				outs.write(bit);
        			}
        	}    catch (IOException ioe) {
            			ioe.printStackTrace(System.out);
            		  }
            		outs.flush();
            		outs.close();
            		in.close();	
       
%>	