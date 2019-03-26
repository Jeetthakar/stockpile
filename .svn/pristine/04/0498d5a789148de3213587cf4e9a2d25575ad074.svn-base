<%@ page language="java" contentType="text/html ; charset=ISO-8859-1"
	import="http.utils.multipartrequest.*,java.io.*,app.*,javax.swing.*,java.util.*"%><%@page
	import="org.apache.log4j.Logger"%>
<%
	Logger log = Logger.getLogger(this.getClass());
	log.info("inside " + this.getClass().getSimpleName() + " page");
%>
<html>
<body>
	<%
		System.out.println("FILE UPLOAD PAGE");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", -1);
		//	JFrame frame = new JFrame();
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
				System.out.println("Name **" + name);
				InputStream in = parser.getFileContents(name);
				if (in != null) {
					System.out.println("Inside Ifff");
					BufferedInputStream input = new BufferedInputStream(in);
					String path = Connect.getCoolMenuspath();
					System.out.println("PATH ***" + path);
					//log.info("path is : "+path);
					String str_dirName = path + "/CoolMenus";
					System.out.println("DIR NAME ***" + str_dirName);
					FileOutputStream file = null;
					try {
						File dirFile = new File(str_dirName);
						if (!dirFile.exists())
							if (dirFile.mkdirs())
								System.out
										.println("Successfully Created...");
						File demofile = new File(str_dirName,
								parser.getBaseFilename(name));
						System.out.println("DEMO FILE NAME ***"
								+ demofile.getName() + " && PAth ***"+demofile.getPath());
						file = new FileOutputStream(demofile);
					
					} catch (Exception ee) {
						System.out.println("E : " + ee);
					}
					System.out.println("Path : " + path);
					//BufferedInputStream input = new BufferedInputStream(in);
					//					FileOutputStream file = new FileOutputStream(new File("CoolMenus", parser.getBaseFilename(name)));///CoolMenus					
					int read;
					byte[] buffer = new byte[4096];
					while ((read = input.read(buffer)) != -1) {
						System.out.println("Inside while ***");
						file.write(buffer, 0, read);
					}
					file.close();
					input.close();
				}
				log.info("File  name " + name);
				System.out.println("File  name ***" + name);
				fileName = parser.getBaseFilename(name);
				System.out.println("File  name 222***" + fileName);
			}

		} catch (Exception e) {
			System.out.println("GENERAL EXCEPTION ***" + e);
		}
		//out.flush(); 
		app.PopFileDialogNewStock pstk = new app.PopFileDialogNewStock();

		pstk.setStr_fileName(fileName);
		String file_type = PopFileDialog.getFiletype();
		System.out.println("file_type***" + file_type);
		String exchangeid = PopFileDialog.getExchangeid();
		System.out.println("exchangeid***" + exchangeid);
		String hdate = PopFileDialog.getHist_date();
		System.out.println("hdate***" + hdate);
		log.info("file_type is " + file_type + " exchangeid is "
				+ exchangeid + "hdate is " + hdate);
		String url = response.encodeURL("/pages/ImportNewStock.jsp?D1="
				+ exchangeid + "&D2=" + file_type + "&hist_Date=" + hdate);
		System.out.println("URL***" + url);
		// 		String url = response.encodeURL("/pages/ImportNewStock.jsp");
	%>
	<jsp:forward page="<%=url%>" />
	<a href='<%=request.getHeader("REFERER")%>'>Back</a>
</body>
</html>
