
<%@ page import="java.util.*,java.io.*"%>
<%@ page  import="app.*"%>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%		String file_name = request.getParameter("filename");
		String var = request.getParameter("var");
		int type = Integer.parseInt(request.getParameter("type"));
		
		String pathf = Connect.getCoolMenuspath();
		pathf = pathf + "CoolMenus/"+file_name;
		File f = new File (pathf);
		//response.setContentType ("application/xls");
		MakeExcel obj = new MakeExcel();
		Vector v2 = (Vector)session.getAttribute("ci2");
		log.info(" checking for type...");
		
		if(type == 1)
		{
			log.info("size in download.."+v2.size());
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
			// case for stock details
			String fr = request.getParameter("from");
			String to = request.getParameter("to");
			//String sCol = request.getParameter("to1");
			String var1 = request.getParameter("var1");
			String stk_name= request.getParameter("stkName");
			String ind_name = request.getParameter("indName");
			log.info(" Inside FileDownLoad :"+ stk_name+ " : " + ind_name);
			//obj.create_file(var,type,v2,fr,to,var1);
			//create_file_stock_detail(var,switch_code,ai,fdate,tdate1,var1,tdate,param1,param2,param3 )
			obj.create_file_stock_detail(var,type,v2,fr,to,var1,"",stk_name,ind_name,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"StockHighLow.xls\"");
		}
		else if(type == 8)
		{
			obj.create_file("1",type,v2,null,null,null);
			log.info("file ...."+type);
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
			String fr11 =  request.getParameter("from");
			obj.create_file("1",type,v2,fr11,null,null);
			log.info("file ...."+type);
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
			String[] arr = request.getParameterValues("D1");
			String fr14 = request.getParameter("from");
			String to14 = request.getParameter("to");
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
			String fr = request.getParameter("from");
			String to = request.getParameter("to");
			String stk_name= request.getParameter("stkName");
			obj.create_file(stk_name,type,v2,fr,to,var);
			response.setHeader ("Content-Disposition", "attachment; filename=\"CapitalChangeToUniverse.xls\"");
		}
		else if(type == 17)
		{
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
			String fr = request.getParameter("from");
			String to = request.getParameter("to");
			log.info("size in download.."+v2.size());
			obj.create_file(var,type,v2,fr,to,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexDivisor.xls\"");
		}
		else if(type == 20)
		{
			log.info("size in download.."+v2.size());
			obj.create_file(var,type,v2,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexPe_Pb.xls\"");
		}
		else if(type == 21)
		{
			log.info("size in download.."+v2.size());
			String exch_name= request.getParameter("exchName");
			obj.create_file(var,type,v2,null,null,exch_name);
			response.setHeader ("Content-Disposition", "attachment; filename=\"StockList.xls\"");
		}
		else if(type == 22)
		{
			String fr = request.getParameter("from");
			String to = request.getParameter("to");
			
			String filter = request.getParameter("var");
			String indExch=request.getParameter("indExch");
			obj.create_file_stock_detail(var,type,v2,fr,to,filter,null,indExch,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"StockDivident.xls\"");
		}
		else if(type == 23)
		{
			String filter = request.getParameter("filter");
			String index=request.getParameter("index");
			String fDate=request.getParameter("from");
			String stock=request.getParameter("stock");
			//filter="+filter+"&type=23&filename=StockDetail.xls&index="+str+"&from="+str2+"&stock="+str3;
			//String var,int switch_code,Vector ai,String fdate, String tdate1, String var1,String tdate,String param1,String param2,String param3
			obj.create_file_stock_detail(null,type,v2,fDate,index,filter,stock,null,null,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"StockDetail.xls\"");
		}
		else if(type == 24)
		{
			String fr = request.getParameter("from");
			String to = request.getParameter("to");
			log.info("size in download.."+v2.size());
			obj.create_file(var,type,v2,fr,to,null);
			response.setHeader ("Content-Disposition", "attachment; filename=\"IndexMovement.xls\"");
		}
		else if(type == 25)
		{
			String indExch=request.getParameter("indExch");
			String fr = request.getParameter("from");
			String to = request.getParameter("to");
			obj.create_file(var,type,v2,fr,to,indExch);
			response.setHeader ("Content-Disposition", "attachment; filename=\"TradedVolume.xls\"");
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
       
%>
