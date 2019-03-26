 <%@ page import = "java.util.Date" %>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.Locale,java.util.*" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page  import="app.*"%>
<%@ page  import="harrier.income.com.report.*"%>
<%@ page import = "java.util.*" %>
<%@ page import = "java.io.*" %><%@page import="org.apache.log4j.Logger"%>
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
 		
 			<link rel="stylesheet" type="text/css" href="../StyleSheetM.css"  />
			<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
			<script language="javascript" src="box_ex.js"></script>
			<script type='text/javascript' src='/Stockpile/dwr/interface/IndexComposeReport.js'></script>
			<script type='text/javascript' src='/Stockpile/dwr/interface/GraphMethods.js'></script>
	
			<script type="text/javascript" src="../Script/Event.js"></script>
			<script type="text/javascript" src="../Script/SortedTable.js"></script>

		<style type="text/css">

			/* table styles*/
			.sorted td, th {border:0;padding:2px 6px;margin:0;border-right:1px solid #336;
				border-bottom:1px solid #336;
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
		<table width="250"><tr><td><h4>Portfolio Composition</h4></td></tr>
				<tr><td><b>
    			<font size="2" face="Verdana">
            		<bean:message key="Index.select" />&nbsp;:
            		<bean:write name="PortfolioReportBean" property="indexName"/>
            	</font> 
    			</b></td></tr>
		</table>
      	<table ID="sortTabletable" border="1" align="center" cellpadding="3" cellspacing="0">
				<thead>
					<tr>
         	
           			<th align="center"  nowrap="nowrap"  id="stockName" class="gridStyle-header">
           			<span><b><bean:message key="stockmaster.stockName" /></b></span></th>
              		<th align="right" nowrap="nowrap" id="tshares" class="gridStyle-header">
              		<span><b><bean:message key="IndexCompose.tshares" /></b></span></th>
        			<th align="right" nowrap="nowrap" id="iwf" class="gridStyle-header">
        			<span><b>iwf</b></span></th>
     				<th align="right" nowrap="nowrap" id="mlot" class="gridStyle-header">
     				<span><b>mlot</b></span></th>
            		<th align="right" nowrap="nowrap" id="priceltp" class="gridStyle-header">
            		<span><b><bean:message key="IndexCompose.priceltp" /></b></span></th>
              		<th align="right" nowrap="nowrap" id="pricelast" class="gridStyle-header">
              		<span><b><bean:message key="IndexCompose.pricelast" /></b></span></th>
              		<th align="center" nowrap="nowrap" id="currency" class="gridStyle-header">
              		<span><b><bean:message key="IndexCompose.currency" /></b></span></th>
          			<th align="center" nowrap="nowrap" id="cerate" class="gridStyle-header">
          			<span><b>C.E.Rate</b></span></th>
            		<th align="center" nowrap="nowrap" id="mcap" class="gridStyle-header">
            		<span><b><bean:message key="IndexCompose.mcap" /></b></span></th>
              		<th align="center" nowrap="nowrap" id="amarket" class="gridStyle-header">
              		<span><b><bean:message key="IndexCompose.amarket" /></b></span></th>
             		<th align="right" nowrap="nowrap" id="weight" class="gridStyle-header">
             		<span><b><bean:message key="IndexCompose.weight" /></b></span></th>
            		<th align="center" nowrap="nowrap" id="Date" class="gridStyle-header">
            		<span><b><bean:message key="corporate.Date" /></b></span></th>
          		</tr>
          		</thead>
				<tbody>
				<% int i=1;

						String Bgcolor=""; // use to give alternate bands of color to table row
						String RowLabel="";// use as a ID value for the table row
					%>
				<!-- Iterate over the table data -->
 			<logic:iterate id="try1" property="tabledata" name="PortfolioReportBean">
 			<%
					RowLabel="row"+i ; // identify the table row as row1, row2, .. etc
					/* if row number is even the table row Bgcolor="#FFDEAD" else Bgcolor="#C0C0C0" */
					if (i%2==0){
						Bgcolor="#84AADE";
					}else{
						Bgcolor="#DEE3EF";
					}
						i++;
					%>
				<tr id="<%=RowLabel %>" bgcolor="<%=Bgcolor %>" >

            		<td nowrap="nowrap"   align="left" axis="sstring" headers="stockName"
           			title='<bean:write name="try1" property="stockname1"/>'>
            			<font face="Verdana" size="2" color="blue">
            			<a href='../masters/stockmaster2.jsp?s_stockid=<bean:write name="try1" property="stockid"/>'>
            			<bean:write name="try1" property="stockname1"/></a></font></td>
            		<td nowrap="nowrap" align="right" nowrap="nowrap" axis="number" headers="tshares"
           			title='<bean:write name="try1" property="tis"/>'>
              			<font face="Verdana" size="2" color="blue">
              			<bean:write name="try1" property="tis"/></font></td>
            		<td nowrap="nowrap" align="right" nowrap="nowrap" axis="number" headers="iwf"
           			title='<bean:write name="try1" property="iwf"/>'>
              			<font face="Verdana" size="2" color="blue">
              			<bean:write name="try1" property="iwf"/></font></td>
     				<td nowrap="nowrap" align="right" nowrap="nowrap" axis="number" headers="mlot"
           			title='<bean:write name="try1" property="market"/>'>
           				<font face="Verdana" size="2" color="blue">
           				<bean:write name="try1" property="market"/></font></td>
            		<td nowrap="nowrap" align="right" nowrap="nowrap" axis="number" headers="priceltp"
           			title='<bean:write name="try1" property="adjusted"/>'>
      					<font face="Verdana" size="2" color="blue">
      					<bean:write name="try1" property="adjusted"/></font></td>
           			<td nowrap="nowrap" align="right" nowrap="nowrap" axis="number" headers="pricelast"
           			title='<bean:write name="try1" property="last"/>'>
              			<font face="Verdana" size="2" color="blue">
              			<bean:write name="try1" property="last"/></font></td>
            		<td nowrap="nowrap" align="left" nowrap="nowrap" axis="sstring" headers="currency"
           			title='<bean:write name="try1" property="currency"/>'>
            			<font face="Verdana" size="2" color="blue">
            			<bean:write name="try1" property="currency"/></font></td>
            		<td nowrap="nowrap" align="right" nowrap="nowrap" axis="number" headers="cerate"
           			title='<bean:write name="try1" property="curr_exch_convIratecomp"/>'>
            			<font face="Verdana" size="2" color="blue">
              			<bean:write name="try1" property="curr_exch_convIratecomp"/></font></td>
            		<td nowrap="nowrap" align="right" nowrap="nowrap" axis="number" headers="mcap"
           			title='<bean:write name="try1" property="mcv"/>'>
            			<font face="Verdana" size="2" color="blue">
              			<bean:write name="try1" property="mcv"/></font></td>
            		<td nowrap="nowrap" align="right" nowrap="nowrap" axis="number" headers="amarket"
           			title='<bean:write name="try1" property="mcv"/>'>
            			<font face="Verdana" size="2" color="blue">
              			<bean:write name="try1" property="mcv"/></font></td>
            		<td nowrap="nowrap" align="right" nowrap="nowrap" axis="number" headers="weight"
           			title='<bean:write name="try1" property="strweightage"/>'>
            			<font face="Verdana" size="2" color="blue">
              			<bean:write name="try1" property="strweightage"/></font></td>
           			<td nowrap="nowrap" align="right" nowrap="nowrap" axis="date" headers="Date"
           			title='<bean:write name="try1" property="stockprice"/>'>
           				<font face="Verdana" size="2" color="blue">
              			<bean:write name="try1" property="stockprice"/></font></td>
           		</tr>
			</logic:iterate>
		
	 </tbody>
	
			 
			</table> 
	
</body>
</html:html>