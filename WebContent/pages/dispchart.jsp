
<%@ page import = "java.io.PrintWriter" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "java.text.ParseException" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.Locale" %><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy",Locale.UK);
	String sDate = request.getParameter("hitDate");
	if (sDate == null) sDate = "All";
	Date dDate = null;
	try {
		dDate = sdf.parse(sDate);
	} catch (ParseException e) {
		//  Leave at null
	}
// 	String filename = Pechart.generatePieChart(dDate, session, new PrintWriter(out));
// 	String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + filename;
// 	ArrayList dateList = WebHitDataSet.getDateList();
%>
<html>
<head>
<link rel="stylesheet" href="sample.css" type="text/css"/>
<title>Pie Chart Example</title>
</head>
<body>
<table border=0>
	<tr>
	<td>
	<h2>Pie Chart Example</h2>
	Date [<%= dDate == null ? "All dates" : sdf.format(dDate) %>]<br>
    <br>

<%-- 	<img src="<%= graphURL %>" width=500 height=300 border=0 usemap="#<%= filename %>"> --%>

		
	</td>
	</tr>
</table>
</body>
</html>
