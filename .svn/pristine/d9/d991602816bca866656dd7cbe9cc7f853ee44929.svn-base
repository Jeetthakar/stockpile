

<%@ page import="java.util.*,java.io.*"%>
<%@ page  import="harrier.income.com.report.*"%>
<%@ page language="java" import="app.*"%>
 <%@ include file="MultiFact.jsp" %>  
 <%@page import="org.apache.log4j.Logger"%>
<%-- <%  --%>
<!-- Logger log = Logger.getLogger(this.getClass()); -->
<!-- log.info("inside "+this.getClass().getSimpleName()+" page");	 -->
<!--  %>    -->
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setHeader("Expires","0");
%>


<%		String file_name="BatchXml.xml";

		Vector v2=new Vector();
		String pathf = Connect.getCoolMenuspath();
		pathf = pathf + "CoolMenus/"+file_name;
		File f = new File (pathf);
		ArrayList list1 =new ArrayList();
		//response.setContentType ("application/xls");
		harrier.income.com.report.MakeXmlAll obj = new harrier.income.com.report.MakeXmlAll();
		 list1 = (ArrayList)session.getAttribute("getdata");
		 log.info("lisssst"+list1);
		//harrier.income.com.report.MultiFact FileBean= new harrier.income.com.report.MultiFact();
        try {
  			
  			
  			
  			if(list1!= null)
  			{
  			  Iterator it=list1.iterator();
  			  while(it.hasNext())
  			  {
  			     AddFactData d=(AddFactData)it.next();
  			    String var=d.var;
  			   String type1=d.type;
  			   int type=Integer.parseInt(type1);
  			   String iename=d.iename;
  			   String from=d.from;
  			   String to=d.to;
  			   String idxname=d.idxname;
  			    log.info("hello"+f);
  			   
  		
		if(type == 1)
		{	 v2 = (Vector)session.getAttribute("ci2Composition");
			 obj.create_file(var,type,iename,v2,null,null,null);
			
		}
		 if(type ==2)
		{
			v2 = (Vector)session.getAttribute("ci2Cwv");
			obj.create_file(var,type,iename,v2,null,null,null);
			
		}
		
		 if(type == 10)
		{
			v2 = (Vector)session.getAttribute("ci2h");
			//String[] arr = request.getParameterValues("D1");
			Vector vid = (Vector)session.getAttribute("vec_ind");
			
			obj.create_file2(vid,type,v2,from,to);
			
		}
		 if(type == 14)
		{ 
			String[] arr = (String[])session.getAttribute("idxList");
			 v2 = (Vector)session.getAttribute("ci2IRV");
			log.info("Index array  "+arr);
			
			obj.create_file1(arr,type,v2,from,to);
			
		}
		 if(type == 15)
		{ 
		   
			Vector vid = (Vector)session.getAttribute("ci1");
			 v2 = (Vector)session.getAttribute("ci2");
			
			obj.create_file2(vid,type,v2,from,to);
			
			
		}
		 if(type == 19)
		{
			log.info("divisor");
			v2 = (Vector)session.getAttribute("ci2divisor");
			log.info("size in download2.."+v2.size());
			obj.create_file(var,type,iename,v2,from,to,idxname);
		
		}
		 if(type == 20)
		{
			
			v2=(Vector)request.getSession().getAttribute("ci2pepb");
			log.info("size of v2="+v2.size());
			obj.create_file(var,type,iename,v2,from,to,null);
		
		}
		 if(type == 3)
		{
			
			v2 = (Vector)session.getAttribute("ci2Iww");
			obj.create_file(var,type,iename,v2,null,null,null);
			
			
		}
		 if(type == 5)
		{
			
			v2 = (Vector)session.getAttribute("ci2Scc");
			
			obj.create_file(var,type,iename,v2,from,to,null);
			
		}
		 if(type == 6)
		{
			
			v2 = (Vector)session.getAttribute("ci2SDetails");
			obj.create_file(var,type,iename,v2,from,to,null);
		
		}
		
		 if(type == 22)
		{ 
			
			v2 = (Vector)session.getAttribute("ci2SD");
			obj.create_file(var,type,iename,v2,from,to,null);
			
		}
		 if(type == 16)
		{ 
		
			
			String stk_name= request.getParameter("stkName");
			v2 = (Vector)session.getAttribute("ci2Cap");
		
			obj.create_file(var,type,iename,v2,from,to,null);
			
		}
		
		 if(type == 25)
		{ 
			
			v2 = (Vector)session.getAttribute("ci2Tr");
			String indExch=request.getParameter("indExch");
			obj.create_file(var,type,iename,v2,from,to,null);
			
		  }
		}
		}
		} catch(Exception e)
  			 { log.info(e);
  			 }  
  			 
  			  
		obj.buffclose();
		 response.setHeader ("Content-Disposition", "attachment; filename=\"BatchXml.xml\""); 	
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