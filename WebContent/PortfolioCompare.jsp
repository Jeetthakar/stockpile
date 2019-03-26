<%@ page import = "java.util.Date" %>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.Locale,java.util.*" %>
<%@ page import = "org.jfree.chart.demo.servlet.ComposeIndex" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page  import="app.*"%>
<%@ page  import="harrier.income.com.report.*"%>
<%@ page import = "java.util.*" %>
<%@ page import = "java.io.*" %>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<!-- @author lokesh-->
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setHeader("Expires","0");

			LogonForm form = null;
			if(request.isRequestedSessionIdValid())	{	
				form = (LogonForm)session.getAttribute("user");
			}
			if(form==null ||(!request.isRequestedSessionIdValid())){
				response.sendRedirect("../userlogintemp.jsp");
			}
		%>
		
<html:html>
<html:base/>
 	<head>
 		
 			<link rel="stylesheet" type="text/css" href="./StyleSheet.css"  />
			<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
			<script language="javascript" src="box_ex.js"></script>
			<script type='text/javascript' src='/Stockpile/dwr/interface/IndexComposeReport.js'></script>
			<script type='text/javascript' src='/Stockpile/dwr/interface/GraphMethods.js'></script>
	
			<script type="text/javascript" src="../Script/Event.js"></script>
			<script type="text/javascript" src="../Script/SortedTable.js"></script>

		<style type="text/css">

			/* table styles*/
			.sorted td, th {border:0;padding:2px 6px;margin:0;border-right:1px solid #336;
				border-bottom:1px solid #336;background-color: #dddddd;color: #black;
				font-size: 10px;padding-left: 2px;}
			td[axis='number'], td[axis='date'] {text-align:right;}
			th {background-color:#cacaca;padding:2px 20px;color: blue;font-size: 12px;
				vertical-align: baseline;line-height: 18px;}
			tfoot td {border-top:0px solid #003;}
			thead th {border-bottom:1px solid #003;}
			.sortedminus {background-color:#ecc;}
			.sortedplus {background-color:#cec;}

		</style>
		
<script language="javascript">

function RowColorToOriginal(a){
// Get the last character of the row number
var lastCharacter=a.substring(a.length-1);
// depending on last character set the appropriate backgroundColor of the table row

if(lastCharacter%2==0){
document.getElementById(a).style.backgroundColor = "#FFDEAD";
}
else{
document.getElementById(a).style.backgroundColor = "#FFFFFF";
}
//document.getElementById(a).style.color = "blue";

}

function ChangeRowColor(a ){

//document.getElementById(a).style.color = "red";
document.getElementById(a).style.backgroundColor = "99FFFF";
}

<!-- ************************************************************* -->
</script>
</head>
<body>	
		<%
			String from	=(String)session.getAttribute("from");
			String to	=(String)session.getAttribute("to");
			String ind1="Portfolio";
			String ind2="Benchmark1";
			String ind3="Benchmark2";	
			ind1=(String)session.getAttribute("varIndexId");
			ind1=ComposeIndex.getIndexName(ind1); 
			String[] indid=(String[])session.getAttribute("indMulti");
			int len=indid.length;
			for(int i=0;i<len;i++){
			if(i==0){ind2=ComposeIndex.getIndexName(indid[0]); }
			if(i==1){ind3=ComposeIndex.getIndexName(indid[1]); }
			}
			%>
      	<table ID="sortTabletable" 
			border="1" align="center" cellpadding="3" cellspacing="0">
				<tr><h3>Portfolio Comaparision</h3></tr>
				<thead>
					<tr align="center">
					<b>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:message key="corporate.Fdate" />:&nbsp;<%=from%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:message key="corporate.Tdate" />:&nbsp;<%=to%>
					</b>
					</tr>
					<tr>
         	
           				<th nowrap="nowrap" align="center" id="trddate" class="gridStyle-header">
          									<span><b><bean:message key="IndexDivisor.trddate" /></b></span>
          								</th>
          								
          								<th align="center" nowrap="nowrap" id="close" class="gridStyle-header">
          									<span><b><%=ind1%></b></span>
          								</th>
          								
		        						<th align="center" nowrap="nowrap" id="mcapimil" class="gridStyle-header">
          									<span><b><%=ind2%></b></span>
          								</th>
          								
          								<th align="center" nowrap="nowrap" id="divisor" class="gridStyle-header">
          									<span><b><%=ind3%></b></span>
          								</th>
        							       		
          			</tr>
          		</thead>
				<tbody>
				<% int i=1;

						String Bgcolor=""; // use to give alternate bands of color to table row
						String RowLabel="";// use as a ID value for the table row
					%>
				<!-- Iterate over the table data -->
 			
 			<logic:iterate id="details"  name="PortfolioReportBean" property="compareData">
 			<%
					RowLabel="row"+i ; // identify the table row as row1, row2, .. etc
					/* if row number is even the table row Bgcolor="#FFDEAD" else Bgcolor="#C0C0C0" */
					if (i%2==0){
						Bgcolor="#FFDEAD";
					}else{
						Bgcolor="#FFFFFF";
					}
						i++;
					%>
				<tr id="<%=RowLabel %>" bgcolor="<%=Bgcolor %>" onMouseOver="ChangeRowColor('<%=RowLabel %>')" onMouseOut="RowColorToOriginal('<%=RowLabel %>')">
					<td valign="middle"  nowrap="nowrap" align="left" axis="date" headers="trddate"
          			title='<bean:write name="details" property="tradingDate"/>'>
							        			<font face="Verdana" size="2" color="blue">
							        				<bean:write name="details" property="tradingDate"/>
							        			</font>
							          		</td>
							          		
							          		<td align="right" valign="middle" axis="number" headers="close" nowrap="nowrap"
          			title='<bean:write name="details" property="close"/>'>
							          			<font face="Verdana" size="2" color="blue">
							          				<bean:write name="details" property="close"/>
							          			</font>
							          		</td>
							          		
							          		<td align="right"  valign="middle" axis="number" headers="mcapimil" nowrap="nowrap"
          			title='<bean:write name="details" property="marketCap"/>'>
							          			<font face="Verdana" size="2" color="blue">
							          				<bean:write name="details" property="marketCap"/>
							          			</font>
							          		</td>
							          		
							          		<td align="right" valign="middle" axis="number" headers="divisor" nowrap="nowrap"
          			title='<bean:write name="details" property="divisor"/>'>
							          			<font face="Verdana" size="2" color="blue">
							          				<bean:write name="details" property="divisor"/>
							          			</font>
							          		</td>
							         					         					          		
							          </tr>	
			</logic:iterate>
		
	 </tbody>
	 
			 
			</table> 
	
</body>
</html:html>