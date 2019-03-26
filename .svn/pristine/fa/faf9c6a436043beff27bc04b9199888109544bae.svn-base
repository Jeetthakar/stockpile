
<!------------FileDownloadPdf.jsp for pdf file --------------- -->
<!------------------lokesh------------------------------------------- -->

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
		//String iename = request.getParameter("iename");
		String var = request.getParameter("var");
		int type = Integer.parseInt(request.getParameter("type"));
		String fdate = request.getParameter("from");
		String tdate = request.getParameter("to");
		Vector v2=new Vector();
		String pathf = Connect.getCoolMenuspath();
		pathf = pathf + "CoolMenus/"+file_name;
		File f = new File (pathf);
		//response.setContentType ("application/xls");
		MakePdf obj = new MakePdf();
		
		log.info(" checking for type...");
		
		if(type == 1)
		{	
			iename = request.getParameter("iename1");
			v2 = (Vector)session.getAttribute("ci2Composition");
			obj.create_file(var,type,iename,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexCompositionReport.pdf\"");
		}
		else if(type ==2)
		{
			iename = request.getParameter("iename2");
			v2 = (Vector)session.getAttribute("ci2Cwv");
			obj.create_file(var,type,iename,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"CompanyWiseWeightage.pdf\"");
		}
		else if(type == 3)
		{
			iename = request.getParameter("iename3");
			v2 = (Vector)session.getAttribute("ci2Iww");
			obj.create_file(var,type,iename,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndWiseWeightage.pdf\"");
		}
		else if(type == 5)
		{
			iename = request.getParameter("iename5");
			v2 = (Vector)session.getAttribute("ci2Scc");
			//String fr = request.getParameter("from");
			//String to = request.getParameter("to");
			obj.create_file(var,type,iename,v2,fdate,tdate,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"StockContribution.pdf\"");
		}
		else if(type == 6)
		{
			// case for stock details
			//String var1 = request.getParameter("var1");
			//String stk_name= request.getParameter("stkName");
			//String ind_name = request.getParameter("indName");
			iename =(String)session.getAttribute("iename6");
			v2 = (Vector)session.getAttribute("ci2SDetails");
			obj.create_file(var,type,iename,v2,fdate,tdate,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"StockDetails.pdf\"");
		}
		else if(type == 10)
		{
			
			String[] arr = request.getParameterValues("D1");
			 v2 = (Vector)session.getAttribute("ci2h");// for 1st vector
			Vector vid = (Vector)session.getAttribute("vec_ind");
			String fr = request.getParameter("from");
			String to = request.getParameter("to");
			log.info("lokesh problem in pdf jsp");
			obj.create_file(vid,type,iename,v2,fr,to);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexCompareOHLC.pdf\"");
		}
		else if(type == 19)
		{
			iename = request.getParameter("iename19");
			v2 = (Vector)session.getAttribute("ci2divisor");
			
			//String fr = (String)session.getAttribute("fdate19");
			//String to = (String)session.getAttribute("tdate19");
			String indexname=(String)session.getAttribute("selectIndex19");
			var=(String)session.getAttribute("var19");
			session.setAttribute("ci2",v2);
			session.setAttribute("var",var);
			session.setAttribute("selectIndex",indexname);
			
			obj.create_file(var,type,iename,v2,fdate,tdate,indexname);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexDivisor.pdf\"");
		}
		else if(type == 20)
		{
			iename = request.getParameter("iename20");
			v2=(Vector)request.getSession().getAttribute("ci2pepb");
			obj.create_file(var,type,iename,v2,fdate,tdate,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexPe_Pb.pdf\"");
		
		}
		
		else if(type == 22)
		{
			iename = request.getParameter("iename22");
			v2 = (Vector)session.getAttribute("ci2SD");
			//String fr = request.getParameter("from");
			//String to = request.getParameter("to");
			
			//String filter = request.getParameter("var");
			//String indExch=request.getParameter("indExch");
			obj.create_file(var,type,iename,v2,fdate,tdate,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"StockDivident.pdf\"");
		}
		else if(type == 25)
		{
			iename = request.getParameter("iename25");
			v2 = (Vector)session.getAttribute("ci2Tr");
			String indExch=request.getParameter("indExch");
			//String fr = request.getParameter("from");
			//String to = request.getParameter("to");
			obj.create_file(var,type,iename,v2,fdate,tdate,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"TradedVolume.pdf\"");
		}
		else if(type == 14)
		{
			Vector v14=(Vector)session.getAttribute("ci2IRV");
			log.info("size of v14="+v14.size());
			obj.create_file(null,type,null,v14,fdate,tdate,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexReturn.pdf\"");
		
		}
		else if(type == 15)
		{
		v2=(Vector)request.getSession().getAttribute("ci2");
		Vector v3=(Vector)request.getSession().getAttribute("ci1");
		//v3 = (Vector)session.getAttribute("ci2");
		log.info("size of v2="+v2.size());
		obj.create_file1(v3,null,type,null,v2,null,null,null);
		response.setHeader ("Content-Disposition", "attachment; filename=\"IndexCorrelation.pdf\"");
		
		}

		else if(type == 16)
		{
			iename = request.getParameter("iename16");
			//String fr = request.getParameter("from");
			//String to = request.getParameter("to");
			String stk_name= request.getParameter("stkName");
			v2 = (Vector)session.getAttribute("ci2Cap");
			//obj.create_file(stk_name,type,v2,fr,to,var);
			obj.create_file(var,type,iename,v2,fdate,tdate,null);
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
       
%>	
