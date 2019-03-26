<%@ page language="java" contentType="text/html ; charset=ISO-8859-1" import="http.utils.multipartrequest.*,java.io.*,
java.util.*"%>
<%@ page language="java" import="app.*" %>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<html>
<body>
<%
	response.setHeader("Cache-Control","no-cache"); 
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", -1);
	String fname = "";
	String fileName = "";
	try
		{
		   String message = "";
		   response.setContentType("text/html");
		   MultipartRequest parser = new ServletMultipartRequest(request,
																MultipartRequest.MAX_READ_BYTES,
																MultipartRequest.IGNORE_FILES_IF_MAX_BYES_EXCEEDED,
																null);
			for (Enumeration e = parser.getFileParameterNames() ; e.hasMoreElements() ;)
			{
				String name = (String) e.nextElement();
				InputStream in = parser.getFileContents(name);
				if (in!=null)
				{
					BufferedInputStream input = new BufferedInputStream(in);
					String path=Connect.getCoolMenuspath();
					//log.info("path is : "+path);
					String str_dirName = path+"CoolMenus";
					FileOutputStream file = new FileOutputStream(new File(str_dirName, parser.getBaseFilename(name)));
					fname = str_dirName;
					int read;
					byte[] buffer = new byte[4096];
					while ( (read=input.read(buffer))!=-1)
					{
						file.write(buffer, 0, read);
						//log.info("time passssssss >>>>>>> : "+read.toString());
					}
					file.close();
					input.close();
				}
				fileName = parser.getBaseFilename(name);
				fname = fname + "/" + fileName;
			}
		}
		catch (Exception e)
		{
		}
		log.info("File Name Is "+fname);
		//out.flush(); 
		//app.PopFileDialog pop = new app.PopFileDialog();
		//pop.setStr_fileName(fileName);		
		String url = response.encodeURL("/pages/reports/DynaReport.jsp?fname="+fname+"&file_query=1");
		%>
		<jsp:forward page="<%=url%>"/>
		<a href='<%=request.getHeader("REFERER")%>'>Back</a>
</body>
</html>		