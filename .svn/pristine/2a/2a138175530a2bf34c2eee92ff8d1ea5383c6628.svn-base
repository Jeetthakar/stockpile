
<!-- ------author lokesh---------->
<%@ page import="java.util.*,java.io.*"%>
<%@ page  import="harrier.income.com.report.*"%>
<%@ page language="java" import="app.*"%>
<%@page import="org.apache.log4j.Logger"%>
 <%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setHeader("Expires","0");
%>


<%		String file_name="AllinOne.pdf";
	//	Logger Logging = Logger.getLogger("PdfReportBatchAll.jsp");
		Vector v2=new Vector();
		String pathf = app.Connect.getCoolMenuspath();
		pathf = pathf + "CoolMenus/"+file_name;
		File f = new File (pathf);
		ArrayList list1 =new ArrayList();
		//response.setContentType ("application/xls");
		harrier.income.com.report.MakePdfOne obj = new harrier.income.com.report.MakePdfOne();
		 list1 = (ArrayList)session.getAttribute("getdata");
		
		
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
  			  //   Logging.debug("hello"+f);
  		 
  		
		if(type == 1)
		{	 Vector v2Composition = (Vector)session.getAttribute("ci2Composition");
			 obj.create_file(var,type,v2Composition,null,null,iename);
			
		}
		 if(type == 19)
		{
			
			Vector v2Divisor = (Vector)session.getAttribute("ci2divisor");
			obj.create_file(var,type,v2Divisor,from,to,iename);
		
		}
		
		 if(type == 20)
		{
			
			Vector v2pepb=(Vector)request.getSession().getAttribute("ci2pepb");
			log.info("size of v2="+v2pepb.size());
			obj.create_file(var,type,v2pepb,from,to,iename);
		
		}
		 if(type == 6)
		{
			
			Vector v2SDetails = (Vector)session.getAttribute("ci2SDetails");
			obj.create_file(var,type,v2SDetails,from,to,iename);
		
		}
		 if(type == 22)
		{ 
			log.info("var"+var+" type="+type+" iename"+iename+" from"+from+" to"+to);
			
			Vector v2SD = (Vector)session.getAttribute("ci2SD");
			obj.create_file(var,type,v2SD,from,to,iename);
			
		}
		
		 if(type ==2)
		{
			Vector v2Cwv = (Vector)session.getAttribute("ci2Cwv");
			obj.create_file(var,type,v2Cwv,from,to,iename);
			
		}
		 if(type == 3)
		{
			
			Vector v2Iww = (Vector)session.getAttribute("ci2Iww");
			obj.create_file(var,type,v2Iww,null,null,iename);
			
			
		}
		
		 if(type == 5)
		{
			
			Vector v2Scc = (Vector)session.getAttribute("ci2Scc");
			obj.create_file(var,type,v2Scc,from,to,iename);
			
		}
		
		 if(type == 16)
		{ 
		
			Vector v2Cap = (Vector)session.getAttribute("ci2Cap");
			obj.create_file(var,type,v2Cap,from,to,iename);
			
		}
		
		 if(type == 25)
		{ 
			log.info("var"+var+" type="+type+" iename"+iename+" from"+from+" to"+to);
			Vector v2Tr = (Vector)session.getAttribute("ci2Tr");
			obj.create_file(var,type,v2Tr,from,to,iename);
			
		}
		 
		 if(type == 10)
		{
			Vector v2ohlc = (Vector)session.getAttribute("ci2h");
			Vector vid = (Vector)session.getAttribute("vec_ind");			
			obj.create_file(vid,type,v2ohlc,from,to);
			
		}
		
		 if(type == 14)
		{ 
			Vector arr = (Vector)session.getAttribute("idxList");
			Vector v2IRV = (Vector)session.getAttribute("ci2IRV");
			obj.create_file(arr,type,v2IRV,from,to);
			
		}
		 if(type == 15)
		{ 		   
			Vector vid = (Vector)session.getAttribute("ci1");
			Vector  v2ICor = (Vector)session.getAttribute("ci2");
			
			obj.create_file(vid,type,v2ICor,from,to);
					
		}
		
			
		
		}
		}
		} catch(Exception e)
  			 { //Logging.error(e);
  			 }  
  			 
  			  
		obj.closePdf();
		
		response.setHeader ("Content-Disposition", "attachment; filename=\"AllinOne.pdf\""); 	
		InputStream in = new FileInputStream(f);
		ServletOutputStream outs = response.getOutputStream();
		int bit = 256;
		
		try {

			      while ((bit) >= 0) {
        				bit = in.read();
        				if(bit!=-1)
        				outs.write(bit);
        			}
        }catch (IOException ioe) {
            			ioe.printStackTrace(System.out);
        }
        outs.flush();
        outs.close();
        in.close();	
        
   
%>	