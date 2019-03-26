
<%@ page import = "org.jfree.chart.demo.servlet.WebHitDataSet" %>
<%@ page import = "java.io.PrintWriter" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "java.text.ParseException" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.Locale" %>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy",Locale.UK);
	Date dDate = null;
	try {
		dDate = sdf.parse("01-Aug-2002");
	} catch (ParseException e) {
		//  Leave at null
	}
//	String filename = WebHitChart.generatePieChart(dDate, session, new PrintWriter(out));
// 	String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + filename;
// 	log.info(graphURL);
	ArrayList dateList = WebHitDataSet.getDateList();
%>
<html>
<head>
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

	<p>The chart shown above has tooltips and drilldown enabled.</p>

	<table bordercolordark="FFFFFF" bordercolorlight="000000" width="400" cellpadding="20" cellspacing="0" border="1" class="panel">
	<tr><td>
		<table border=0 cellpadding=2 width=100%>
		<form method=POST action="pie_chart.jsp">
		<tr valign=top>
			<td><b>Hit Date</b></td>
			<td>
				<select name=hitDate class=pullDown>
				<option>All</option>
<%				Iterator iter = dateList.listIterator();
				while (iter.hasNext()) {
					Date optionDate = (Date)iter.next();
					if (optionDate.equals(dDate)) { %>
						<option selected><%= sdf.format(optionDate) %></option>
<%					} else { %>
						<option><%= sdf.format(optionDate) %></option>
<%					} %>
<%				} %>
				</select>
			</td>
			<td>
				<input type=image src="images/button_refresh.png" width=80 height=22 name=refresh>
			</td>
		</tr>
		</form>
		</table>
	</td></tr>
	</table>
	
	</td>
	</tr>
</table>
</body>
</html>
