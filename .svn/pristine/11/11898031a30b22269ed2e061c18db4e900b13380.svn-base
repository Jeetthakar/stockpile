<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
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
				response.sendRedirect("../userlogintemp.jsp");
			}
		%>
<html:html>
<head>

<SCRIPT language=javascript>
function hideLeftCol(id)
{
if(this.document.getElementById(id).style.display=='none')
{
this.document.getElementById(id).style.display='inline';
document.getElementById("HideHandle").src = '../closeImage.gif';
// this.document.getElementById(id).style.width='10px';
Set_Cookie('showLeftCol','true',30,'/','','');
var show = Get_Cookie('showLeftCol');
//document['HideHandle'].src = 'images/hide.gif';
document.getElementById("HideHandle").src = '../open.gif';
}
else{
// this.document.getElementById(id).style.display='none';
this.document.getElementById(id).style.display='none';
document.getElementById("HideHandle").src = '../openImage.gif';
Set_Cookie('showLeftCol','false',30,'/','','');
var show = Get_Cookie('showLeftCol');
//document['HideHandle'].src = 'images/show.gif';
}
}
</script>

	<html:base/>
	<title></title>
	<link href="../StyleSheetM.css" rel="stylesheet" type="text/css">
	<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
	<script language="javascript" src="box_ex.js"></script>
	<script type="text/javascript" src="../Script/SortedTable.js"></script>
	<script type='text/javascript' src='/Stockpile/dwr/interface/MoveTable.js'></script>
	<script type='text/javascript' src='/Stockpile/dwr/engine.js'></script>
	<script type='text/javascript' src='/Stockpile/dwr/util.js'></script>
	
	<script language="javascript">
		var c2 = new CodeThatCalendar(caldef2);
	</script>
	<script type="text/javascript" src="./sorttable.js"></script>
	<style type="text/css">	
		/* Sortable tables */
		table.sortable a.sortheader {
    		background-color:#eee;
   	 		font-size: 13px; 
    		font-family: Verdana;
    		color: black;
    		text-decoration: none;
    		display: block;
		}
		table.sortable span.sortarrow {
    		color: black;
    		text-decoration: none;
		}
	</style>
	
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
	
</head>
<body onload="initialize()">



<table width="100%" height="100%" >

<tr>
<td align="left" valign="top" bgcolor="#CCddee">

<DIV id="leftCol" style="DISPLAY: none; width:160; height:100%;">
<%@ include file="../tree3.jsp" %>
</div>
<IMG id="HideHandle" src="../openImage.gif" style="CURSOR: hand; position:absolute;" onclick='hideLeftCol("leftCol");' src="fold.gif" width="10" height="25">
</td>
<td align="left" valign="top">

<html:form action="/StockDivident">
<div id="PageTitle">
<table width="1000" cellspacing="0" cellpadding="0" >
	<tr>
		<td width="300" class="subHeader" nowrap="nowrap">
			&nbsp;
		</td>
		<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
			<font size="3" face="Verdana">
		    	<b><bean:message key="StockDivident.title" /></b>
		   	</font>
		</td> 
	</tr>
</table> 
</div>
		<%
			String ajax1="no";
			try{
				ajax1=request.getParameter("ajax1");
				session.setAttribute("ajax1",ajax1);
			}catch (Exception e) {
				// TODO: handle exception
		//		Logging.error(" Error :"+e.getMessage());
			}
	  		String indExch=request.getParameter("indExchName");
	  		String var=request.getParameter("filter");
	  		String to = request.getParameter("to");
	  		String from = request.getParameter("from");
	  			  		String excel_path = "./FileDownload.jsp?var="+var+"&type=22&filename=StockDivident.xls&technology=Ajax&from="
	  				+from+"&to="+to+"&indExch="+indExch;
	  		String xml_path = "./FileDownloadXmlNew.jsp?var="+var+"&type=22&filename=StockDivident.xml&technology=Ajax&from="
	  				+from+"&to="+to+"&indExch="+indExch;
	  		String pdf_path="./FileDownload_Pdf.jsp?type=22&filename=StockDivident.pdf&technology=Ajax";
	  						
	  		String str_url = "./EmailReport.jsp?switch_type=22&cas=22&varid="+var+"&rname=StockDivident.xls&technology=Ajax&from="
	  	  				+from+"&to="+to+"&indExch="+indExch;

	  	 %>
	<logic:equal  value="yes" parameter="compute" >	<!-- if compute ="yes" display links -->
	<logic:notEqual value="0" name="dataCount">
	
	  	 <!-- Links  -->
	<table align="right" id="strutslinks">
         	
         	<tr>
	<td width=50>
	 			
	 			<a href="javascript:PrintThisPage()" ><font size="1" face="Verdana" color="blue" > <bean:message key="LatestDivisor.printerf" /></font></a>&nbsp;
	</td> 	 	
	 			<!-- DownLoad Excel -->
	 <td>			<a href='<%= excel_path %>' ><font size="1" face="Verdana" color="blue" > <bean:message key="LatestDivisor.downloade" /></font></a>&nbsp;
	 </td>
	 	<td>		<a href='<%= xml_path %>' ><font size="1" face="Verdana" color="blue" > Export to Xml</a>&nbsp;
	 </td>	
	 <td>			<a href='<%= pdf_path %>' ><font size="1" face="Verdana" color="blue" > <bean:message key="LatestDivisor.pdfr" /></a>&nbsp;
	 </td>		
	 			<!-- Email Report -->
	 <td>			<a href='<%= str_url %>' ><font size="1" face="Verdana" color="blue"> <bean:message key="LatestDivisor.emailr" /></font></a>&nbsp;
	 			
	 		</td> 	
			</tr>
	</table>
	</logic:notEqual>
	</logic:equal>
	
	<table id="ajaxlinks" align="right" style="display:none">
	
         	<!-- Links  -->
	<td width=50>
	 			<!-- Printer friendly -->
	 			<a href="javascript:PrintThisPage()" ><font size="1" face="Verdana" color="blue" > <bean:message key="LatestDivisor.printerf" /></font></a>&nbsp;
	</td> 	 	
	 			<!-- DownLoad Excel -->
	 <td>			<a href='<%= excel_path %>' ><font size="1" face="Verdana" color="blue" > <bean:message key="LatestDivisor.downloade" /></font></a>&nbsp;
	 </td>
	 <td>			<a href='<%= xml_path %>' ><font size="1" face="Verdana" color="blue" > Export to Xml</a>&nbsp;
	 </td>	
	 <td>			<a href='<%= pdf_path %>' ><font size="1" face="Verdana" color="blue" > <bean:message key="LatestDivisor.pdfr" /></a>&nbsp;
	 </td>		
	 			<!-- Email Report -->
	 <td>			<a href='<%= str_url %>' ><font size="1" face="Verdana" color="blue"> <bean:message key="LatestDivisor.emailr" /></font></a>&nbsp;
	 			
	 </td> 	

	</table>
<br>

<table width="656">
 	<tr>
	  	<td width="116" nowrap="nowrap" align="left">&nbsp;</td>
	  	<td width="125" nowrap="nowrap" align="left">
			<font size="2" face="Verdana"><bean:message key="TradeVolumeInd.Filter" />: 
			</font>
       	</td>
       	<td width="504" nowrap="nowrap" align="left" height="30">
       		<html:select size="1" property="filter" onchange="document.forms[0].submit();return true" >
        		<html:option value="0"><bean:message key="StockPerformance.notsel" /></html:option>
        		<html:option value="1"><bean:message key="TradeVolumeInd.Wise" /></html:option>
        		<html:option value="2"><bean:message key="TradeVolumeInd.Index" /></html:option>
        	</html:select>
    	</td>
  	</tr>	
</table>
<logic:notEqual value="0" parameter="filter">
	<!-- Table to display exch name or index Name -->
	<table>
		<tr>
			<td width="116" nowrap="nowrap" align="left">&nbsp;</td>
			<td width="125" nowrap="nowrap">
				<logic:equal value="1" parameter="filter">
					<font size="2" face="Verdana"><bean:message key="TradeVolumeInd.Stock" />: </font>
				</logic:equal>
				<logic:equal value="2" parameter="filter">
					<font size="2" face="Verdana"><bean:message key="TradeVolumeInd.Name" /></font>
				</logic:equal>
			</td>
			<logic:equal value="2" parameter="filter">
			<td align="left" nowrap="nowrap">
				<html:select size="1" property="selectIndExch" onchange="document.forms[0].submit();" styleId="sIE">
					<html:optionsCollection property="selectIndExchCollection" name="StockDividentBean"/>
				</html:select>
			</td>
			</logic:equal>
			<logic:equal value="1" parameter="filter">
			<td align="left" nowrap="nowrap">
				<html:select size="1" property="selectIndExch" onchange="document.forms[0].submit();" styleId="sIE">
					<html:optionsCollection property="selectIndExchCollection" name="StockDividentBean"/>
				</html:select>
			</td>
			</logic:equal>
		</tr>
	</table>
</logic:notEqual>
<!-- Table for To and From date -->
	<table>
	 	<tr>
	 		<td width="116" nowrap="nowrap" align="left">&nbsp;</td>
	 		<logic:equal value="2" parameter="filter">
	 		<td  align="right" nowrap="nowrap">
        		<html:checkbox property="checkShwIndices" onclick="test1()" ></html:checkbox>
        	</td>	
        	<td nowrap="nowrap" align="left">	
				<font size="2" face="Verdana">  
	  				<bean:message key="IndexComparision.showsi" />
	  			</font>&nbsp;
	  		</td>
	 		</logic:equal>
	 		<td width="80" align="right" nowrap="nowrap"><font size="2" face="Verdana">
            		<bean:message key="corporate.Fdate" />&nbsp;:
            	</font>
           	</td> 
           	<td nowrap="nowrap">
             	<html:text property="from" readonly="true" size="10"/>
            </td>
            <td  nowrap="nowrap" align="left"> 
				<html:button property="shwFrom" value="..." onclick="c2.popup('from');"/>&nbsp;&nbsp;&nbsp;
          	</td>
            <td nowrap="nowrap" align="right"><!-- To date-->
           		<font size="2" face="Verdana">
                	<bean:message key="corporate.Tdate" />&nbsp;:
                </font>
            </td>
            <td nowrap="nowrap">
            	<html:text property="to" readonly="true" size="10"/>
            </td>
            <td  nowrap="nowrap" align="left">
				<html:button property="shwTo" value="..." onclick="c2.popup('to');"/>
          	</td>
          	<%	if(ajax1.equals("yes")){
	  		%>
            <td nowrap="nowrap"> <!-- View -->
            	<html:submit onclick="return go();" onkeypress="return go();"><bean:message key="Reports.View"/></html:submit>&nbsp;&nbsp;
			</td>
			<% }else{
			%>
			<td>
				<INPUT type="Button" name="view" value="View" onclick = "getStockDividendDetails()"/>
			</td>	
			<% } %>
			</tr>
	</table>
	<p>
	
<!-- ============================================ For Ajax ================================================== -->	
	<div id = "Ajaxcontentstart">
	<table width="120%" class ="sorted"  ID="sortTable"	border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#EFEFEF" style="display:none">    
          <!--  <table border="1"> -->      
				<thead >
				<tr>				
					<th width="90" class="gridStyle-header">Stock Name</th>
					<th width="90" class="gridStyle-header">Face Value</th>	
					<th width="140" class="gridStyle-header">Total Issued Shares</th>
					<th width="150" class="gridStyle-header">Mkt. Cap.(In Millions)</th>	
					<th width="130" class="gridStyle-header">Dividend</th>												
					<th width="100" class="gridStyle-header">Applied Date</th>
				</tr>
				</thead>						
				<tbody id="indexMovingTable"  bgcolor="#DEE3EF"> </tbody>								
		</table>	
		</div>
		
		<table border="0" align="center" width="700" height="222" cellspacing="0" cellpadding="0">
		<!-- 	<tr>
	        	<td width="99"></td>
				<td id="selectExchangeMessage" class="gridStyle-message" align="center" valign="middle">
	          		<p style="margin-left: 9"><bean:message key="TradeVolumeInd.View" /></p>
	          	</td>
	      	</tr>
	     --> 	
	      	<tr>  						
		  		<td id ="nodata" bgcolor="#84AADE" align="center" valign="middle" style="display: none;">
		  			<p style="margin-left: 9">
		  			<font face="Verdana" color="blue" size="5">
		  			<bean:message key="IndexCompareOHCL.ndata" />
		  			</font>
		  			</p>
		  		</td>
        	</tr>
  		</table>
		  
<!-- ============================================ For Ajax ================================================== -->
	
 
	<logic:notEqual value="yes" parameter="compute">
	
	<table border="0" align="left" width="700" height="222" cellspacing="0" cellpadding="0">
		<tr>
        	<td width="99"></td>
			<td id="selectExchangeMessage" bgcolor="#84AADE" align="center" valign="middle">
          		<p style="margin-left: 9">
          		<font face="Verdana" color="blue" size="5">
          		
          		<bean:message key="TradeVolumeInd.View" />
          		</font>
          		</p>
          	</td>
      	</tr>
      	
  	</table>
	</logic:notEqual>

	<logic:equal  value="yes" parameter="compute" >
   	  
	  	<bean:define id="details" name="StockDividentBean" property="tableData"/>
	  	<bean:size id="dataCount" name="StockDividentBean" property="tableData"/>
	  		
	  	<logic:equal value="0" name="dataCount" >
	  	<table border="0" align="center" width="631" height="222" cellspacing="0" cellpadding="0">
	  	    <tr>
          	<!-- 	<td width="99"></td> -->
	  		
          		<td id = "noStrutsData" bgcolor="#84AADE" align="center" valign="middle">
          			<p style="margin-left: 9">
          			<font face="Verdana" color="blue" size="5">
          			<bean:message key="IndexCompareOHCL.ndata" />
          			</font>
          			</p>
          		</td>
        	</tr>
       	</table>
   </logic:equal>
   
   <logic:notEqual value="0" name="dataCount">
        <div id=contentstart> 
    	<table id = "sortTabletable" border="1" width="60%" align="center" cellpadding="3" cellspacing="0" id="t1" class="sortable" bordercolor="#EFEFEF">
        	<!-- Table Heading -->
        	<tr>
        		<td nowrap="nowrap" align="center"  class="gridStyle-header"><bean:message key="stockmaster.stockName" /></td>
          		<td nowrap="nowrap" align="center" class="gridStyle-header"><bean:message key="corporate.Faceval" /></td>
          		<td nowrap="nowrap" align="center" class="gridStyle-header"><bean:message key="StockDivident.Share" /></td>
          		<td nowrap="nowrap" align="center" class="gridStyle-header"><bean:message key="StockDivident.Market" /></td>
          		<td nowrap="nowrap" align="center" class="gridStyle-header"><bean:message key="StockDivident.Dividend" /></td>
          		<td nowrap="nowrap" align="center" class="gridStyle-header"><bean:message key="corporate.Applied" /></td>
		   	</tr>   		
          	
           <%
 				int i=1;
 				String bcolor="";
 			%>
          	<!-- Iterate over the table data -->
          	<logic:iterate id="details" name="StockDividentBean" property="tableData">
          	
          	<%if(i%2==0)
				{
					bcolor="#84AADE";
				}
				else
				{
					bcolor="#DEE3EF";
				}
				
				%>
				<tr bgcolor=<%=bcolor %>>
     			<td valign="middle" nowrap="nowrap" bgcolor="white" align="left" class="gridStyle-odd" >
        			<a href='../masters/stockmaster2.jsp?s_stockid=<bean:write name="details" property="stockId"/>'>
        			<font face="Verdana" size="2" color="blue"><bean:write name="details" property="stockName"/>
        			</font></a>
        		</td>
          		<td align="right" nowrap="nowrap"  valign="middle" >
          			<font face="Verdana" size="2" color="blue"><bean:write name="details" property="faceVal"/></font>
          		</td>
          		<td align="right" nowrap="nowrap"  valign="middle" >
          			<font face="Verdana" size="2" color="blue"><bean:write name="details" property="tis"/></font>
          		</td>
          		<td align="right" nowrap="nowrap"  valign="middle" >
          			<font face="Verdana" size="2" color="blue"><bean:write name="details" property="mcv"/></font>
          		</td>
          		<td align="right" nowrap="nowrap"  valign="middle" >
          			<font face="Verdana" size="2" color="blue"><bean:write name="details" property="amount"/></font>
          		</td>
          		<td align="right" nowrap="nowrap"  valign="middle" >
          			<font face="Verdana" size="2" color="blue"><bean:write name="details" property="date"/></font>
          		</td>      
          	  </tr>
          	  <% i=i+1; %>
    		</logic:iterate>
        </table> 
        </div>
    	</logic:notEqual>
   	</logic:equal>
<div id="hiddenTable">
<table>
	<tr>
	<td width="125" align="right">
		<logic:equal value="1" parameter="filter"><b>
			<font size="2" face="Verdana"><bean:message key="corporate.Stkexname" />: </font></b>
		</logic:equal>
		<logic:equal value="2" parameter="filter"><b>
			<font size="2" face="Verdana"><bean:message key="defineIndex7" />:</font></b>
		</logic:equal>
	</td>
	<td><font size="2" face="Verdana"><b>&nbsp;&nbsp;&nbsp;
    	<bean:write name="StockDividentBean" property="indExchName" /></b>
         </font>
	</td>
	</tr>
	<tr>
	<td width="125" align="right"><font size="2" face="Verdana"><b>
    	<bean:message key="corporate.Fdate" />&nbsp;:</b>
         </font>
   	</td>
   	<td><font size="2" face="Verdana">
   		<bean:write name="StockDividentBean" property="from"/></font>
  	</td> 
	</tr>
	<tr>
	<td width="125" align="right"><font size="2" face="Verdana"><b>
    	<bean:message key="corporate.Tdate" />&nbsp;:</b>
         </font>
   	</td>
   	<td><font size="2" face="Verdana">
   		<bean:write name="StockDividentBean" property="to"/>
   		</font>
   	</td> 
	</tr>
</table>
</div>
 <html:hidden property="indExchName" ></html:hidden>	
 <html:hidden property="compute" value="no"></html:hidden>
 <html:hidden property="clear"></html:hidden> 
</html:form>


</td>
</tr>
</table>
</body>
<script language="javascript">
var Button;
var man1=document.getElementById("hiddenTable");
man1.style.display= "none"; 
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

function go() {
		var objTo=document.forms[0].to;
		var i = 0;
     	var fields = new Array();
        if(document.forms[0].filter.value==0){
			fields[i++] = "Select Filter is required";
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
        if((checkdatecurrent(objTo))==false)	
		{
			alert("ToDate should be Less Than CurrentDate.");
			objTo.focus();
			objTo.select();
			return false;
		}
        else {
         	document.forms[0].compute.value="yes";
			return true;
        }
	
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
function PrintThisPage() { 
	var sOption="toolbar=yes,location=no,directories=yes,menubar=yes,"; 
    sOption+="scrollbars=yes,width=750,height=600,left=100,top=25"; 
	//var sWinHTML = document.getElementById('contentstart').innerHTML; 
   	var printHead =document.getElementById('PageTitle').innerHTML;
   	
   	var winprint=window.open("","",sOption); 
    winprint.document.open(); 
    winprint.document.write('<html><body>'); 
    winprint.document.write(printHead);
    winprint.document.write('<br>');
    
    if(Button=="AjaxButton"){
    		var todate = DWRUtil.getValue("to");
  			var fromdate = DWRUtil.getValue("from");
  			var indexchange = DWRUtil.getText("selectIndExch"); 	
    		winprint.document.write("<font size=4>Index/Exchange  :"+indexchange+"<br> FromDate :"
   			+fromdate+"<br>   ToDate :"+todate+"</font><br>");
   			var sWinHTML = document.getElementById('Ajaxcontentstart').innerHTML; 
   			//var sWinHTML = document.getElementById('sortTable').innerHTML;    
    } else{ 
    		var moreDet = document.getElementById('hiddenTable').innerHTML;
    		winprint.document.write(moreDet); 
   			var sWinHTML = document.getElementById('contentstart').innerHTML; 
   	}
    winprint.document.write(sWinHTML); 
    //winprint.document.write(moreDet);          
    winprint.document.write('</body></html>'); 
    winprint.document.close(); 
    winprint.focus(); 
}

function test1(){
	document.forms[0].submit();
 	return true;
}

/*============================================== for dwr =========================================================== */	
function getStockDividendDetails(){
  go();
  DWRUtil.useLoadingMessage();    
  var filter = DWRUtil.getValue("filter");    
  var todate = DWRUtil.getValue("to");
  var fromdate = DWRUtil.getValue("from");
  var exchange = DWRUtil.getValue("selectIndExch");
  var indExchname=DWRUtil.getText("selectIndExch");  
  //alert(todate+fromdate+index);
  //alert(exchange);
  MoveTable.getStockDividend(fillTable, exchange, fromdate, todate, filter,indExchname);  
}

	var stockName = function(StockDetails) { return '<a href="../masters/stockmaster2.jsp?s_stockid='+StockDetails.stockId+'"><font face="Verdana" color="blue" size="2">'+StockDetails.stockName+'</font></a>' };			
	var faceVal = function(StockDetails) { return '<font face="Verdana" color="blue" size="2">'+StockDetails.faceVal+'</font>'};				
	var tis = function(StockDetails) { return '<font face="Verdana" color="blue" size="2">'+StockDetails.tis+'</font>'};				
	var mcv = function(StockDetails) { return '<font face="Verdana" color="blue" size="2">'+StockDetails.mcv+'</font>'};				
	var amount = function(StockDetails) { return '<font face="Verdana" color="blue" size="2">'+StockDetails.amount+'</font>'};				
	var date = function(StockDetails) { return '<font face="Verdana" color="blue" size="2">'+StockDetails.date+'</font>'};				
	//alert(stockId);
function fillTable(indexMovingTable) {
 /* changeDisplay("sortTable","inline");
  changeDisplay("selectExchangeMessage","none");	
  DWRUtil.removeAllRows("indexMovingTable");
  DWRUtil.addRows("indexMovingTable",indexMovingTable, [ stockName, faceVal, tis, mcv, amount, date ]);
  //alert("I got it right");
  */
   var a = new Array();
	  a = indexMovingTable;   
 if(a.length){
   	  
   	  Button = "AjaxButton";
   	  changeDisplay("selectExchangeMessage","none");
 	  changeDisplay("nodata","none");
 	  changeDisplay("noStrutsData","none");
 	  changeDisplay("sortTabletable","none");
 	  changeDisplay("strutslinks","none");
 	  changeDisplay("ajaxlinks","inline");
 	  
 	  changeDisplay("sortTable","inline"); 	  	  	
	  DWRUtil.removeAllRows("indexMovingTable");
      DWRUtil.addRows("indexMovingTable",indexMovingTable, [ stockName, faceVal, tis, mcv, amount, date ]);
 }
 else{ 
 	 changeDisplay("sortTable","none");
 	 changeDisplay("noStrutsData","none");
 	 changeDisplay("sortTabletable","none"); 
 	 changeDisplay("strutslinks","none");
 	 changeDisplay("ajaxlinks","none");  	 
	 changeDisplay("nodata","inline");
	 changeDisplay("selectExchangeMessage","none");
 }
}

/*============================================== for dwr =========================================================== */
</script>
</html:html>
