<%@ page import="java.util.*,java.io.*"%>
<%@ page  import="app.*"%>
<%@page import="org.apache.log4j.Logger"%>
 <%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<% 	
Logger Logging = Logger.getLogger("FileDownloadDyna.jsp");
     	    String astr = "../pages/FileDownload.jsp?type=121&filename=excel.csv";
      		String query = (String)session.getAttribute("query1");
      		
      		int type = Integer.parseInt(request.getParameter("type"));
      		Logging.debug("at file download type is:"+type);
      		String pathf = Connect.getCoolMenuspath();
      		//String type = request.getParameter("type");
      		//pathf = null;   //pathf + "CoolMenus/excel.csv";
			File f = null; //new File (pathf);
			//response.setContentType ("application/xls");
			org.jfree.chart.demo.servlet.DynamicRerpots ip = new org.jfree.chart.demo.servlet.DynamicRerpots();
			if(type == 1)
			{
				Logging.debug("at file download type 1:"+query);
				pathf = pathf + "CoolMenus/excel.csv";
				f = new File (pathf);
				response.setContentType ("application/xls");
				ip.makeCSV(query);
      			response.setHeader ("Content-Disposition", "attachment; filename=\"excel.csv\"");
      		}else{
      			Logging.debug("at file download type 2 2:"+query);
      			pathf = pathf + "CoolMenus/query.txt";
				f = new File (pathf);
				Logging.debug("at file download type 2 2:"+pathf);
				response.setContentType ("application/txt");
      			ip.makeQuery(query);
      			response.setHeader ("Content-Disposition", "attachment; filename=\"query.txt\"");
      		
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
            	finally{
            		outs.flush();
            		outs.close();
            		in.close();	
           	try{
		
				if(pathf!=null){  
		  	
		  			File del_file = new File(pathf);
			  		if(del_file.exists())
			  		{
			  			del_file.delete();
			  		}
				}
		  	}catch(Exception er){Logging.error("Error in deleting file xls file.:"+er);}
      		}
      	  %>