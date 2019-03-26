<%@ page language="java" import="app.*"%><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setHeader("Expires","0");

LogonForm form = null;
if(request.isRequestedSessionIdValid())	{	
	form = (LogonForm)session.getAttribute("user");
}
if(form==null ||(!request.isRequestedSessionIdValid())){
	response.sendRedirect("userlogintemp.jsp");
	return;
}
		%>
<html>
	<head>
		<title>Select Date</title>
	<link href="ctc.css" rel="stylesheet" type="text/css"/>
        <script language="javascript" src="codethatcalendarstd.js"></script>
	</head>
	<body>
		<script>
			if(!window.opener)window.opener=window.parent;
			if(window.opener && window.opener.codethatcalendar)  
			{       document.write("<div class='clsBody'>")
				window.opener.codethatcalendar.create(document);
				document.write("</div>")
			}
		</script>
	</body>
</html>	