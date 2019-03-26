<%@ page language="java" contentType="text/html ; charset=ISO-8859-1"
	import="http.utils.multipartrequest.*,java.io.*,java.util.*"%>
<%@ page language="java" import="app.*"%><%@page
	import="org.apache.log4j.Logger"%>
<%
	Logger log = Logger.getLogger(this.getClass());
	log.info("inside " + this.getClass().getSimpleName() + " page");
%>
<html>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", -1);

		String fileName = "";
		try {
			String message = "";

			response.setContentType("text/html");
			MultipartRequest parser = new ServletMultipartRequest(request,
					MultipartRequest.MAX_READ_BYTES,
					MultipartRequest.IGNORE_FILES_IF_MAX_BYES_EXCEEDED,
					null);

			for (Enumeration e = parser.getFileParameterNames(); e
					.hasMoreElements();) {
				String name = (String) e.nextElement();
				InputStream in = parser.getFileContents(name);
				if (in != null) {
					BufferedInputStream input = new BufferedInputStream(in);
					String path = Connect.getCoolMenuspath();
					//log.info("path is : "+path);
					String str_dirName = path + "/CoolMenus";
					FileOutputStream file = null;
					try {
						File dirFile = new File(str_dirName);
						if (!dirFile.exists())
							if (dirFile.mkdirs())
								System.out
										.println("Successfully Created...");
						File demofile = new File(str_dirName,
								parser.getBaseFilename(name));

						file = new FileOutputStream(demofile);
					} catch (Exception ee) {
						System.out.println("E : " + ee);
					}
					System.out.println("Path : " + path);
					int read;
					byte[] buffer = new byte[4096];
					while ((read = input.read(buffer)) != -1) {
						file.write(buffer, 0, read);
					}
					file.close();
					input.close();
				}
				fileName = parser.getBaseFilename(name);
			}

		} catch (Exception e) {
		}
		log.info("File Name Is " + fileName);
		//out.flush(); 
		app.PopFileDialog pop = new app.PopFileDialog();
		pop.setStr_fileName(fileName);

		String url = response.encodeURL("/pages/ImportPrice.jsp");
	%>
	<jsp:forward page="<%=url%>" />

	<a href='<%=request.getHeader("REFERER")%>'>Back</a>



</body>
</html>
