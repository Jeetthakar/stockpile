<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="java.io.*"%>
<%@ page  import="harrier.income.com.report.*" %>
<%@ page language="java" import="app.*"%><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
	LogonForm form = (LogonForm)session.getAttribute("user");
	if(form == null)
		response.sendRedirect("../login1.jsp");
%>
<html:html>
<html:errors /> 
<head>
 	<html:base/>
		<link href="./StyleSheet.css" rel="stylesheet" type="text/css">
		<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
		<script language="javascript" src="box_ex.js"></script>
		<script language="javascript">
			var c2 = new CodeThatCalendar(caldef2);			
		</script>
		<script language="javascript" src="../Script/Ajax.js"></script>
		<script type="text/javascript" src="./sorttable.js"></script>
		<style type="text/css">	
		/* Sortable tables */
		table.sortable a.sortheader {
    		background-color:#eee;
   	 		font-size: 13px; 
    		font-family: Arial;
    		color: black;
    		text-decoration: none;
    		display: block;
		}
		table.sortable span.sortarrow {
    		color: black;
    		text-decoration: none;
		}
		</style>
	<title><bean:message key="Top10.title" /></title>
</head>
<body onload="initialize()">
    <html:form action="/stockDetails2">
	<div id="PageTitle">
   	<table width="1000" cellspacing="0" cellpadding="0" >
    	<tr>
			<td width="250" class="subHeader" nowrap="nowrap">
		     	&nbsp;
		   </td>
		   <td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		   		<font size="3" face="Arial">
		        	<b><bean:message key="Top10.title" /></b>
		         </font>
		   	</td> 
	  	</tr>
	</table> 
	</div>    
	<logic:equal value="no" property="compute" name="Top10Bean">
		<span id="theTable1">	
		<table width="656">
       		<tr>
        		<td width="157" nowrap="nowrap" align="right">
					<font size="2" face="Arial">
           				<bean:message key="Index.select" />&nbsp;:
           			</font>
        		</td>    
 				<td width="489" nowrap="nowrap" align="left" height="30">
          			<html:select property="selectIndex" size="1" onchange="indiChange()" styleId="sIE">
						<html:optionsCollection property="selectIndexCollection" name="Top10Bean"/>
					</html:select>
         		</td>
     		</tr>
 		</table> 
 		</span>
	</logic:equal>
	<logic:notEqual value="no" property="compute" name="Top10Bean">
		<span id="theTable1">	
		<table width="656">
       		<tr>
        		<td width="157" nowrap="nowrap" align="right">
					<font size="2" face="Arial">
           				<bean:message key="Index.select" />&nbsp;:
           			</font>
        		</td>    
 				<td width="489" nowrap="nowrap" align="left" height="30">
          			<html:select property="selectIndex" size="1" onchange="indiChange()" styleId="sIE">
						<html:optionsCollection property="selectIndexCollection" name="Top10Bean"/>
					</html:select>
         		</td>
     		</tr>
 		</table> 
 		</span>
	</logic:notEqual>
	<logic:equal value="no" property="compute" name="Top10Bean">
		<span id="theTable1">	
		<table width="656">
       		<tr>
        		<td width="157" nowrap="nowrap" align="right">
					<font size="2" face="Arial">
           				<bean:message key="Top10.select" />&nbsp;:
           			</font>
        		</td>    
 				<td width="489" nowrap="nowrap" align="left" height="30">
          			<html:select property="selectCriteria" size="1" onchange="indiChange()" styleId="sIE">
						<html:optionsCollection property="selectCriteriaCollection" name="Top10Bean"/>
					</html:select>
         		</td>
     		</tr>
 		</table> 
 		</span>
	</logic:equal>
	<logic:notEqual value="no" property="compute" name="Top10Bean">
		<span id="theTable1">	
		<table width="656">
       		<tr>
        		<td width="157" nowrap="nowrap" align="right">
					<font size="2" face="Arial">
           				<bean:message key="Top10.select" />&nbsp;:
           			</font>
        		</td>    
 				<td width="489" nowrap="nowrap" align="left" height="30">
          			<html:select property="selectCriteria" size="1" onchange="indiChange()" styleId="sIE">
						<html:optionsCollection property="selectCriteriaCollection" name="Top10Bean"/>
					</html:select>
         		</td>
     		</tr>
 		</table> 
 		</span>
	</logic:notEqual>
	<table>
     	<tr>
     		<td  width="99" align="right" nowrap="nowrap">
        		<html:checkbox property="checkShwIndices" onclick="test1()" ></html:checkbox>
        	</td>
        	<td>
        	<table>
        		<tr>
        			<td nowrap="nowrap" align="left">	
			  		 	<font size="2" face="Arial">  
	  						<bean:message key="IndexComparision.showsi" />
	  					</font>&nbsp;
	  				</td>
	  				<td nowrap="nowrap"><font size="2" face="Arial" >
        		      	<bean:message key="corporate.Fdate" /></font>
       			    </td> 
            		<td nowrap="nowrap">
             			<html:text property="from" readonly="true" size="10"/>
            		</td>
            		<td  nowrap="nowrap"> 
						<html:button property="shwFrom" value="..." onclick="c2.popup('from');"/>
          			</td>
            		<td nowrap="nowrap">
           				<font size="2" face="Arial">
                			<bean:message key="corporate.Tdate" />
                		</font>
            		</td>
            		<td nowrap="nowrap">
             			<html:text property="to" readonly="true" size="10"/>
            		</td>
            		<td  nowrap="nowrap">
						<html:button property="shwTo" value="..." onclick="c2.popup('to');"/>
          			</td>
             		<td nowrap="nowrap"> 
               			<html:submit property="b1"  onclick="return go();" ><bean:message key="Reports.View"/></html:submit>&nbsp;&nbsp;
					</td>
				</tr>
        	</table>	
			</td>
	  	</tr>
		<tr>
	  		<td width="99" align="right" nowrap="nowrap">
	  			<html:checkbox property="checkChart" ></html:checkbox>
	  		</td>
	  		<td nowrap="nowrap">	
				<font size="2" face="Arial">  
	  				<bean:message key="IndexCompose.schart" />
	  			</font>
	  		</td>
	  	</tr> 
	  </table>
	  <logic:notEqual value="yes" parameter="compute" >
	  		<span id="theTable3">	
	  		<table border="0" align="left" width="631" height="222" cellspacing="0" cellpadding="0">
	  			<tr>
          			<td width="99"></td>
          			<td class="gridStyle-message" align="center" valign="middle">
          				<p style="margin-left: 9"><bean:message key="StockHighLow.smess" /></p>
          			</td>
          		</tr>
          	</table>
          	</span>
	  		</logic:notEqual >
	  	<logic:equal  value="yes" parameter="compute" >	
	  	<span id="theTable3">	
	  	<%
	  		String ind_name=request.getParameter("indexName");
	  		String criteria=request.getParameter("selectCriteria");
	  		String to = request.getParameter("to");
	  		String from = request.getParameter("from");
	  		String excel_path = "./FileDownload.jsp?type=6&filename=MarketGainerLooser.xls&from="
	  				+from+"&to="+to+"&indName="+ind_name;
	  		String str_url = "./EmailReport.jsp?switch_type=6&cas=6&rname=MarketGainerLooser.xls&from="
	  				+from+"&to="+to+"&varid="+ind_name;
	  	 %>
	 	<table>
	 	<tr>
	 		<td  width="99" align="right" nowrap="nowrap"></td>
	 		<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 			<a href="javascript:PrintThisPage()" ><font size="1" > <bean:message key="LatestDivisor.printerf" />
	 			</font></a>&nbsp;&nbsp;&nbsp;
	 			<a href='  <%= excel_path%>' ><font size="1" > <bean:message key="LatestDivisor.downloade" />
	 			</font></a>&nbsp;&nbsp;&nbsp;
	 			<a href=' <%= str_url%>' ><font size="1" > <bean:message key="LatestDivisor.emailr" /></font></a>&nbsp;
	 		</td> 	
	  	</tr>
	  	
	  	</table>	  	
	  		<bean:size id="dataCount" name="Top10Bean" property="tableData"/>
	  		<bean:define id="details" name="Top10Bean" property="tableData"/>
	  		<div id="chart" >
	  		<table>
	  			<tr>
	  				<td >
	  				<logic:equal name="Top10Bean"  property="checkChart" value="on" >
	  				<jsp:setProperty name="Top10Bean" property="checkChart"/>
	  					<p align="center">
    		  		  		<% 	
    		  		  			Vector v1=(Vector)session.getAttribute("ci2");
    		  		  			DatasetFactory1.candlestickReaddata(v1);
								String fname = Candlestick.generateChart(session, new PrintWriter(out));
								String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + fname;
								log.info("graphURL is"+graphURL);
								log.info("filename is"+fname);
    		  		  			
   							%>
								<img src='<%= graphURL %>' width=700 height=500 border=0 usemap='#<%= fname %>'>
							</p>
						</logic:equal>	
					</td>
	  			</tr>
	  		</table>
	  		</div>	
	  		<logic:equal value="0" name="dataCount" >
	  		<table border="0" align="left" width="631" height="222" cellspacing="0" cellpadding="0">
	  			<tr>
          			<td width="99"></td>
	  				<td class="gridStyle-message" align="center" valign="middle">
          				<p style="margin-left: 9"><bean:message key="IndexCompareOHCL.ndata" /></p>
          			</td>
          		</tr>
          	</table>
          	</logic:equal>
          	<logic:notEqual value="0" name="dataCount">
          	<div id="contentstart">  
			<table border="1" width="100%" align="right" cellpadding="2" cellspacing="0" id="t1" class="sortable" bordercolor="#EFEFEF">
 		      	<tr>
          			<td nowrap="nowrap" align="center"><bean:message key="Top10.rank" /></td>
          			<td nowrap="nowrap" align="center"><bean:message key="corporate.stockname" /></td>
          			<td nowrap="nowrap" align="center"><bean:message key="Top10.tradedvolume" /></td>
          		</tr> 
     		
     			<logic:iterate id="details" name="Top10Bean" property="tableData">
     			<tr>
     				<td align="left" bgcolor="white" valign="middle">
          				<font face="Arial" size="2" color="blue"><bean:write name="details" property="rank"/></font>
          			</td>
          			<td valign="middle" bgcolor="white" align="left" >
        				<font face="Arial" size="2" color="blue"><bean:write name="details" property="stockName"/></font>
          			</td>
     				<td bgcolor="white" align="left" valign="middle">
          				<font face="Arial" size="2" color="blue"><bean:write name="details" property="tradedVol"/></font>
          			</td>
     			</tr>
				</logic:iterate> 
			</table>
			</div>
			</logic:notEqual>
	  		</span>
	  		</logic:equal>	
<div id="hiddenTable">
<table id="hiddenTable" height="100">
	<tr>
	<td width="125" align="right"><b>
			<font size="2" face="Arial"><bean:message key="defineIndex7" />: </font></b>
	</td>
	<td><font size="2" face="Arial"><b>&nbsp;&nbsp;&nbsp;
    	<bean:write name="Top10Bean" property="indexName" /></b>
         </font>
	</td>
	</tr>
	<tr>
	<td width="125" align="right"><b>
			<font size="2" face="Arial"><bean:message key="corporate.Stkname" />: </font></b>
	</td>
	</tr>
	<tr>
	<td width="125" align="right"><font size="2" face="Arial"><b>
    	<bean:message key="corporate.Fdate" />&nbsp;:</b>
         </font>
   	</td>
   	<td><font size="2" face="Arial">
   		<bean:write name="Top10Bean" property="from"/>
   		</font>
   	</td> 
	</tr>
	<tr>
	<td width="125" align="right"><font size="2" face="Arial"><b>
    	<bean:message key="corporate.Tdate" />&nbsp;:</b>
        </font>
   	</td>
   	<td><font size="2" face="Arial">
   		<bean:write name="Top10Bean" property="to"/>
   		</font>
   	</td> 
	</tr>
</table>
</div>
<html:hidden property="indexName" ></html:hidden>
<html:hidden property="defaultVal" ></html:hidden>
<html:hidden property="clear" value="no"></html:hidden>
<html:hidden property="compute" value="no"></html:hidden>
</html:form>
</body>
<script language="javascript">
var man1=document.getElementById("hiddenTable");
man1.style.display= "none";
//alert(" Value of stock = " + document.forms[0].stockName.value);
function initialize() {
	var today = new Date();
	var td = today.getDate();
	if(td<10)
	td="0"+td;
	var mnth = today.getMonth(); 
	mnth=mnth+1;
	if(mnth<10)
	mnth="0"+mnth;
	var yr = today.getFullYear();
	var s = "-";
	if((document.forms[0].from.value)=="")
	document.forms[0].from.value= td+ s + mnth + s + yr;
	if((document.forms[0].to.value)=="")
	document.forms[0].to.value= td+ s + mnth + s + yr;
}	
function test1(){
	document.forms[0].submit();
	return true;	
}

function PrintThisPage() { 
	var sOption="toolbar=yes,location=no,directories=yes,menubar=yes,"; 
    sOption+="scrollbars=yes,width=950,height=1900,left=100,top=25"; 

   	var sWinHTML = document.getElementById('contentstart').innerHTML; 
   	var printHead =document.getElementById('PageTitle').innerHTML;
   	var moreDet = document.getElementById('hiddenTable').innerHTML;
   	var showChart = document.getElementById('chart').innerHTML;
   		
    var winprint=window.open("","",sOption); 
    winprint.document.open(); 
    winprint.document.write('<html><body>'); 
    winprint.document.write(printHead);
    winprint.document.write('<br>');
    winprint.document.write(moreDet); 
    winprint.document.write('<br>');
    winprint.document.write(showChart);
    winprint.document.write(sWinHTML);         
    winprint.document.write('</body></html>'); 
    winprint.document.close(); 
    winprint.focus(); 
}
	
	function indiChange(){
		//alert('inside indichange');
		document.forms[0].clear.value="no";	
		document.forms[0].defaultVal.value="no";
		document.forms[0].compute.value="no";
		//document.forms[0].submit();
		retrieveURL('/Stockpile/stockDetails.do?ask=COMMAND_NAME_1','Top10Bean');
		//return true;
	}
function checkdate()
{
	var objTo=document.forms[0].to;
	if((checkdatecurrent(objTo))==false)	
	{
	alert("ToDate should be Less Than CurrentDate.");
	objTo.focus();
	objTo.select();
	return false;
	}
	else return true;
}
function go1() {
	var i = 0;
    var fields = new Array();
    if(document.forms[0].selectIndex.value==0){
 		fields[i++] = "Select Index is required";
	}
    if(document.forms[0].from.value==""){
		fields[i++] = "From Date is required";
	}
	if(document.forms[0].to.value==""){
		fields[i++] = "To Date is required";
	}
	if (fields.length > 0) {
        alert(fields.join('\n'));
        return false;
    }
   else {
         	document.forms[0].compute.value="yes";
         	return true;
        }
}
 function go() { 
document.forms[0].compute.value="yes";
         	return true;
        }	 	
function ExcelFunction() {
	document.forms[0].excel.value="yes";
}
	
function excel(url) {
	
	
	
	
	
}
function checkdatecurrent(objName)
{
	var datefield = objName;
	var strMonth;
	var strYear;
	var strDate;
	var strDateArray;
	var intElement;
	var strSeparatorArray = new Array("-"," ","/",".");
	strDate = datefield.value;
    var intday;var int_td;var int_mnth;var int_yr;
	var int_month;
	var intYear;
	var today = new Date();
	var td = today.getDate();
	var mnth = today.getMonth(); 
	mnth=mnth+1;
	var yr = today.getFullYear();
	int_td=   parseInt(td, 10); 
	int_mnth=  parseInt(mnth, 10); 
	int_yr=   parseInt(yr, 10); 
	for (intElement = 0; intElement < strSeparatorArray.length; intElement++) {
	if (strDate.indexOf(strSeparatorArray[intElement]) != -1) {
			strDateArray=strDate.split(strSeparatorArray[intElement]);
			if (strDateArray.length != 3) {
				err = 1;
				alert(" DateArray length < 1: err :" + err);
				return false;
			}
			else {
			strDay = strDateArray[0];
			strMonth = strDateArray[1];
			strYear = strDateArray[2];
			}
		  }
		}
		intday = parseInt(strDay, 10);
		int_month=parseInt(strMonth,10);
		intYear= parseInt(strYear,10);
		if(intYear>int_yr)
		{
			return false;
		}
		if((intYear==int_yr)&&(int_month>int_mnth))
		{
			return false;
		}
		if((intYear==int_yr)&&(int_month==int_mnth)&&(intday>int_td))
		{
			return false;
		}
		else {
			return true;
	   }
}	
function email(url) {
	
	
	
	
	
}
</script>
</html:html>
	
	