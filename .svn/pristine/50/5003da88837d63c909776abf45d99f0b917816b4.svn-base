<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<head>
<meta http-equiv="Content-Language" content="en-us">
</head>

<body bgcolor="#C0C0C0">

<form method="POST" >
	<p>&nbsp;</p>
	<p>&nbsp;</p>
	
	<table border="0" width="100%" height="111" bordercolor="#000000" cellspacing="0" cellpadding="0" bordercolorlight="#808080">
	<tr>
	<% int res = Integer.parseInt(request.getParameter("result"));
		System.err.println("vaule of res = ....."+res); %>
	<% String send = request.getParameter("sento"); %>
	<% String var = request.getParameter("var"); 
		System.err.println("vaule of var = ....."+var); %>
	
		<input type="hidden" value="" name="result">
		<input type="hidden" value="" name="var">
		<input type="hidden" value="" name="cas">
		<input type="hidden" value="<%=send%>" name="sento">
		<td colspan="2" height="39">
		<p style="margin-top: 0; margin-bottom: 0"><b>
		<% if(res == 1) 
		{ %>
		<font face="Arial" color="#008000">Message sent</font>
		<% }else if(res == 0)
		{%>
		<font face="Arial" color="red">Message not sent due to some errors. Please try again</font>
		<% } %>
		</b></p>
		<hr></td>
	</tr>
	<tr>
		<td width="100%" height="58" colspan="2"><font face="Arial">Sent to :&nbsp&nbsp<font color="#0000FF"><%= send%></font>&nbsp; 
		</font></td>
	</tr>
	<tr>
		<td width="1%">&nbsp;</td>
		<td width="100%"><font size="2" face="Arial" color="#0000FF">
		<% 
			String cas = request.getParameter("cas");
	   		System.err.println("vaule of cas = ....."+cas);
			String url = null;
			if (cas == "1")
				url = "./pages/reports/IndexComposeS.jsp";
			else if(cas == "2")
				url = "./pages/reports/CompanyWiseWeightageS.jsp?var="+var;
			else if(cas == "3")
				url = "./pages/reports/IndWiseWeightS.jsp?var="+var;
		 	else if(cas == "4")
				url = "./pages/reports/DisplayIndexS.jsp?var="+var;
			else url = "./pages/reports/DisplayIndexS.jsp";
		 %>
		 
        <a href= <%= url%> >Back</a><hr></td>
	</tr>
	</table>

	<p>&nbsp;</p>
</form>