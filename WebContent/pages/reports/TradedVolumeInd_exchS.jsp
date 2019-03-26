
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
<html:base/>
<head>
	<html:base/>
	<title></title>
	<link href="StyleSheet.css" rel="stylesheet" type="text/css">
	
	<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
	<script language="javascript" src="box_ex.js"></script>
	<script type="text/javascript" src="../Script/SortedTable.js"></script>
	<script type='text/javascript' src='/Stockpile/dwr/interface/MoveTable.js'></script>
	<script type='text/javascript' src='/Stockpile/dwr/engine.js'></script>
	<script type='text/javascript' src='/Stockpile/dwr/util.js'></script>
	
	<script language="javascript">
		var c2 = new CodeThatCalendar(caldef2);
	</script>
	<!-- <script type="text/javascript" src="./sorttable.js"></script>
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
	</style>  -->
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

</head>
<body onload="initialize()" >
<html:form action="/TradedVolumeInd_exchS">
<div id="PageTitle">
	<table width="1000" cellspacing="0" cellpadding="0" >
    	<tr>
			<td width="250" class="subHeader" nowrap="nowrap">
		    	&nbsp;
		  	</td>
		    <td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		    	<font size="3" face="Arial">
		        	<b><bean:message key="TradeVolumeInd.title" /> </b>
		      	</font>
		  	</td> 
	   	</tr>
	</table> 
</div>

	<%	
//	Logger Logging = Logger.getLogger("TradedVolumeInd_exchS.jsp");
		String ajax1="no";
		try{
			ajax1=request.getParameter("ajax1");
			session.setAttribute("ajax1",ajax1);
		}catch (Exception e) {
			// TODO: handle exception
	//		Logging.error(" Error :"+e.getMessage());
		}
		//Strings for AjaxLinks			
	  	String excel_path_ajax = "./FileDownload.jsp?&type=25&filename=TradedVolume.xls&technology=Ajax";
	  	String xml_path_ajax = "./FileDownloadXmlNew.jsp?type=25&filename=TradedVolume.xml&technology=Ajax";
	    String pdf_path_ajax ="./FileDownload_Pdf.jsp?type=25&filename=TradedVolume.pdf";		
	  	String str_url_ajax = "./EmailReport.jsp?switch_type=25&cas=25&rname=TradedVolume.xls&technology=Ajax";  		  		  	
	%>
	<table align="right" id ="AjaxLinks" style="display: none;">
         	<!--Ajax Links  -->
	 		<td>
	 			<!-- Printer friendly -->
	 			<a href="javascript:PrintThisPage()" ><font size="1" face="Verdana" color="blue"> <bean:message key="LatestDivisor.printerf" /></font></a>
	 		</td>
	 		<td>
	 			<!-- DownLoad Excel -->
	 			<a href='<%= excel_path_ajax %>'  ><font size="1" face="Verdana" color="blue"> <bean:message key="LatestDivisor.downloade" /></font></a>
	 		</td>
	 		<td>
	 			<!-- DownLoad xml -->
	 			<a href='<%= xml_path_ajax %>'  ><font size="1" face="Verdana" color="blue">Export to Xml</font></a>
	 		</td>
	 		<td>
	 			<!-- DownLoad xml -->
	 			<a href='<%= pdf_path_ajax %>'  ><font size="1" face="Verdana" color="blue"><bean:message key="LatestDivisor.pdfr" /></font></a>
	 		</td>
	 		<td>
	 		
	 			<!-- Email Report -->
	 			<a href='<%= str_url_ajax %>' ><font size="1" face="Verdana" color="blue"> <bean:message key="LatestDivisor.emailr" /></font></a>
	 		</td> 	
	</table>
	
	<logic:equal  value="yes" parameter="compute" >	<!-- if compute ="yes" display links -->
	 <logic:notEqual value="0" name="dataCount">
	<%
	
		String indExch= request.getParameter("selectIndExch");
	  	String var=request.getParameter("filter");
	  	String to = request.getParameter("to");
	  	String from = request.getParameter("from");
	  	
	  	//Strins for Struts Links
	  	String excel_path = "./FileDownload.jsp?var="+var+"&type=25&filename=TradedVolume.xls&from="
	  			+from+"&to="+to+"&indExch="+indExch+"&technology=noAjax";
	  	String xml_path = "./FileDownloadXmlNew.jsp?var="+var+"&type=25&filename=TradedVolume.xml&from="
	  			+from+"&to="+to+"&indExch="+indExch;
	    String pdf_path="./FileDownload_Pdf.jsp?type=25&filename=TradedVolume.pdf";		
	  	String str_url = "./EmailReport.jsp?switch_type=25&cas=25&varid="+var+"&rname=TradedVolume.xls&from="
	  				+from+"&to="+to+"&indExch="+indExch;	  			
	  		  		  	
	%>	
	
	<table align="right" id = "StrutsLinks">
         	<!-- Links  -->
	 		<td>
	 			<!-- Printer friendly -->
	 			<a href="javascript:PrintThisPage()" ><font size="1" face="Verdana" color="blue"> <bean:message key="LatestDivisor.printerf" /></font></a>
	 		</td>
	 		<td>
	 			<!-- DownLoad Excel -->
	 			<a href='<%= excel_path %>'  ><font size="1" face="Verdana" color="blue"> <bean:message key="LatestDivisor.downloade" /></font></a>
	 		</td>
	 		<td>
	 			<!-- DownLoad xml -->
	 			<a href='<%= xml_path %>'  ><font size="1" face="Verdana" color="blue">Export to Xml</font></a>
	 		</td>
	 		<td>
	 			<!-- DownLoad xml -->
	 			<a href='<%= pdf_path %>'  ><font size="1" face="Verdana" color="blue"><bean:message key="LatestDivisor.pdfr" /></font></a>
	 		</td>
	 		<td>
	 		
	 			<!-- Email Report -->
	 			<a href='<%= str_url %>' ><font size="1" face="Verdana" color="blue"> <bean:message key="LatestDivisor.emailr" /></font></a>
	 		</td> 	
	</table>
	</logic:notEqual>
	</logic:equal>

<br>

	<table>
		<tr>
	  		<td width="116" nowrap="nowrap" align="left">&nbsp;</td>
	  		<td width="125" nowrap="nowrap" align="left">
				<font size="2" face="Arial"><bean:message key="TradeVolumeInd.Filter" />: 
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
	
	<!-- Table for cut volume textbox -->
	<table>
		<tr>
	  		<td width="116" nowrap="nowrap" align="left">&nbsp;</td>
	  		<td width="125" nowrap="nowrap" align="left">
				<font size="2" face="Arial"><bean:message key="TradeVolumeInd.Cut" />: 
				</font>
        	</td>     
        	<td width="504" nowrap="nowrap" align="left" height="30"> 
        		<html:text property="traded_volume" ></html:text>
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
					<font size="2" face="Arial"><bean:message key="TradeVolumeInd.Stock" />: </font>
				</logic:equal>
				<logic:equal value="2" parameter="filter">
					<font size="2" face="Arial"><bean:message key="TradeVolumeInd.Name" /></font>
				</logic:equal>
			</td>
			<logic:equal value="2" parameter="filter">
			<td align="left">
				<html:select size="1" property="selectIndExch" onchange="" styleId="sIE">
					<html:optionsCollection property="selectIndExchCollection" name="TradedVolumeBean"/>
				</html:select>
			</td>
			</logic:equal>
			<logic:equal value="1" parameter="filter">
			<td align="left">
				<html:select size="1" property="selectIndExch" onchange="" styleId="sIE">
					<html:optionsCollection property="selectIndExchCollection" name="TradedVolumeBean"/>
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
				<font size="2" face="Arial">  
	  				<bean:message key="IndexComparision.showsi" />
	  			</font>&nbsp;
	  		</td>
	 		</logic:equal>
	 		<td width="100" align="right"><font size="2" face="Arial">
            		<bean:message key="corporate.Fdate" />&nbsp;:
            	</font>
           	</td> 
           	<td nowrap="nowrap">
             	<html:text property="from" readonly="true" size="10"/>
            </td>
            <td  nowrap="nowrap" align="left"> 
				<html:button property="shwFrom" value="..." onclick="c2.popup('from');"/>&nbsp;&nbsp;&nbsp;
          	</td>
            <td nowrap="nowrap" align="right"><!-- To date -->
           		<font size="2" face="Arial">
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
				<input type="button" name="view" value="View" onclick ="getTraded_Volume()">   
			 </td>
			<% } %>
		</tr>
	</table>
	<p>

<!-- ============================================ For Ajax ================================================== -->	
	<div id="ajaxcontentstart"> 
	<table class ="sorted"  ID="sortTable"
			border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#EFEFEF" style="display:none">    
          <!--  <table border="1"> -->      
				<thead >
				<tr>				
					<th width="120">Stock Name</th>
					<th width="120">Traded Volume</th>					
				</tr>
				</thead>						
				<tbody id="indexMovingTable"> </tbody>								
		</table>	
	</div>	
	<table border="0" align="center" width="700" height="222" cellspacing="0" cellpadding="0">
		<!--  <tr>
        	
			<td id="selectExchangeMessage" class="gridStyle-message" align="center" valign="middle">
          		<p style="margin-left: 9"><bean:message key="TradeVolumeInd.View" /></p>
          	</td>
      	</tr>
      	-->
      	<tr>      			  		
          	<td id = "nodata" class="gridStyle-message" align="center" valign="middle" style="display:none">
          		<p style="margin-left: 9"><bean:message key="IndexCompareOHCL.ndata" /></p>
          	</td>
        </tr>
  	</table>	
		  
<!-- ============================================ For Ajax ================================================== -->

 
	<logic:notEqual value="yes" parameter="compute">
	
	<table border="0" align="left" width="700" height="222" cellspacing="0" cellpadding="0">
		<tr>
        	<td width="99"></td>
			<td id="selectExchangeMessage" class="gridStyle-message" align="center" valign="middle">
          		<p style="margin-left: 9"><bean:message key="TradeVolumeInd.View" /></p>
          	</td>
      	</tr>
  	</table>
	</logic:notEqual>

	<logic:equal  value="yes" parameter="compute" >
   	  
	  	<bean:define id="details" name="TradedVolumeBean" property="tableData"/>
	  	<bean:size id="dataCount" name="TradedVolumeBean" property="tableData"/>
	  		
	  	<logic:equal value="0" name="dataCount" >
	  	<table border="0" align="left" width="631" height="222" cellspacing="0" cellpadding="0">
	  	    <tr>
          		<td width="99"></td>
	  		
          		<td id = "noStrutsData" class="gridStyle-message" align="center" valign="middle">
          			<p style="margin-left: 9"><bean:message key="IndexCompareOHCL.ndata" /></p>
          		</td>
        	</tr>
       	</table>
        </logic:equal>
          
        <logic:notEqual value="0" name="dataCount">
        <div id=contentstart> 
    	<table class="sorted" ID="sortTabletable" 
			border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#EFEFEF">
				<thead>
					<tr>
    	<!-- <table border="1" width="60%" align="center" cellpadding="3" cellspacing="0" id="t1" class="sortable" bordercolor="#EFEFEF">
        	<tr> -->
        		<!-- Table Heading -->
          		<th nowrap="nowrap" align="center"  id="stockName" class="gridStyle-header">
          		<span><b><bean:message key="stockmaster.stockName" /></b></span></th>
          		<th align="center"  id="Traded" class="gridStyle-header">
          		<span><b><bean:message key="TradeVolumeInd.Traded" /></b></span></th>
		   	</tr>   		
          	</thead>
		<tbody>
          	<!-- Iterate over the table data -->
          	<logic:iterate id="details" name="TradedVolumeBean" property="tableData">
          	
          	<tr>
          		<td valign="middle" bgcolor="white" align="left"  class="gridStyle-odd" axis="sstring" headers="stockName"
           			title='<bean:write name="details" property="stockName"/>'>
        			<a href='../masters/stockmaster2.jsp?s_stockid=<bean:write name="details" property="rank"/>'>
        			<font face="Arial" size="2" color="blue"><bean:write name="details" property="stockName"/>
        			</font></a>
        		</td>
          		
          		<td align="right" bgcolor="white" valign="middle" class="gridStyle-odd" axis="number" headers="Traded"
           			title='<bean:write name="details" property="tradedVol"/>'>
          			<font face="Arial" size="2" color="blue"><bean:write name="details" property="tradedVol"/></font>
          			
          		</td>

         	</tr>
    		</logic:iterate>
            </tbody> 
        </table> 
        </div>
    	</logic:notEqual>
   	</logic:equal>

<div id="hiddenTable">
<table>
	<tr>
	<td width="125" align="right">
		<logic:equal value="1" parameter="filter"><b>
			<font size="2" face="Arial"><bean:message key="TradeVolumeInd.Stock" />: </font></b>
		</logic:equal>
		<logic:equal value="2" parameter="filter"><b>
			<font size="2" face="Arial"><bean:message key="TradeVolumeInd.Name" />:</font></b>
		</logic:equal>
	</td>
	<td><font size="2" face="Arial"><b>&nbsp;&nbsp;&nbsp;
    	<bean:write name="TradedVolumeBean" property="indExchName" /></b>
         </font>
	</td>
	</tr>
	<tr>
	<td width="125" align="right"><font size="2" face="Arial"><b>
    	<bean:message key="corporate.Fdate" />&nbsp;:</b>
         </font>
   	</td>
   	<td><font size="2" face="Arial">
   		<bean:write name="TradedVolumeBean" property="from"/>
   		</font>
   	</td> 
	</tr>
	<tr>
	<td width="125" align="right"><font size="2" face="Arial"><b>
    	<bean:message key="corporate.Tdate" />&nbsp;:</b>
         </font>
   	</td>
   	<td><font size="2" face="Arial">
   		<bean:write name="TradedVolumeBean" property="to"/>
   		</font>
   	</td> 
	</tr>
</table>

</div>
	
 <html:hidden property="indExchName" ></html:hidden>	
 <html:hidden property="compute" value="no"></html:hidden>
 <html:hidden property="clear"></html:hidden> 
</html:form>
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
	initSort();
}	
function initSort() {
	mySorted = new SortedTable();
	mySorted.colorize = function() {
		for (var i=0;i<this.elements.length;i++) {
			if (i%2){
				this.changeClass(this.elements[i],'even','odd');
			} else {
				this.changeClass(this.elements[i],'odd','even');
			}
		}
	}
	mySorted.onsort = mySorted.colorize;
	mySorted.onmove = mySorted.colorize;
	mySorted.colorize();
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
function go() {
	var objTo=document.forms[0].to;
	var i =0;
	var fields = new Array();
	if(document.forms[0].filter.value=="0"){
			fields[i++] = "Select Filter is required";
		}
		if(document.forms[0].selectIndExch.value==""){
			fields[i++] = "Select(Index/Exchange) is required";
		}
					
		if(document.forms[0].from.value==""){
			fields[i++] = "From Date is required";
		}
		
		if(document.forms[0].to.value=="") {
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

function PrintThisPage() { 
	var sOption="toolbar=yes,location=no,directories=yes,menubar=yes,"; 
    sOption+="scrollbars=yes,width=750,height=600,left=100,top=25"; 

   	var printHead =document.getElementById('PageTitle').innerHTML;
    var winprint=window.open("","",sOption); 
    winprint.document.open(); 
    winprint.document.write('<html><body>'); 
    winprint.document.write(printHead);
    winprint.document.write('<br><b>');
    if(Button=="AjaxButton"){		
			//var moreDet = DWRUtil.getText("index");
			var moreDet = DWRUtil.getText("selectIndExch");
			var fromd = DWRUtil.getValue("from");
			var tod = DWRUtil.getValue("to");
   			var sWinHTML = document.getElementById('ajaxcontentstart').innerHTML; 
   			winprint.document.write("<font size=3>Index :"+moreDet+"</font><br>");
   			winprint.document.write("<font size=3>From Date :"+fromd+"</font><br>");
   			winprint.document.write("<font size=3>To Date :"+tod+"</font>");
   		}
   		else{ 
   			var sWinHTML = document.getElementById('contentstart').innerHTML;
   			var moreDet = document.getElementById('hiddenTable').innerHTML;
   			winprint.document.write(moreDet);
   		}   	
         
    winprint.document.write('<br></b>');
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
function getTraded_Volume(){
  DWRUtil.useLoadingMessage();
  var filter = DWRUtil.getValue("filter"); 
  var trd_volume = DWRUtil.getValue("traded_volume");   
  var todate = DWRUtil.getValue("to");
  var fromdate = DWRUtil.getValue("from");
  var exchange = DWRUtil.getValue("selectIndExch"); 
    
  MoveTable.getTradedVolume(fillTable, exchange, trd_volume, fromdate, todate, filter);  
}

	var stockName = function(StockDetails) { 
		var url = "../masters/stockmaster2.jsp?s_stockid="+StockDetails.rank;
		return '<a href='+url+'><font face="Verdana" color="blue" size="2">'+StockDetails.stockName+'</font></a>' };			
	var tradedVol = function(StockDetails) { return '<font face="Verdana" color="blue" size="2">'+StockDetails.tradedVol+'</font>'};				
	//alert(stockId);
function fillTable(indexMovingTable) {
  
  var a = new Array();
	  a = indexMovingTable;   
 if(a.length){
   	  Button = "AjaxButton";
   	  changeDisplay("selectExchangeMessage","none");
 	  changeDisplay("nodata","none");
 	  changeDisplay("noStrutsData","none");
 	  changeDisplay("sortTabletable","none");
 	  changeDisplay("sortTable","inline"); 	 	  	
 	  changeDisplay("AjaxLinks","inline"); 	  	  	
 	  changeDisplay("StrutsLinks","none"); 	  	  	
	  DWRUtil.removeAllRows("indexMovingTable");
      DWRUtil.addRows("indexMovingTable",indexMovingTable, [ stockName, tradedVol ]);
 }
 else{ 
 	 changeDisplay("sortTable","none");
 	 changeDisplay("noStrutsData","none");
 	 changeDisplay("AjaxLinks","none"); 	  	  	
 	 changeDisplay("StrutsLinks","none"); 	  	  	
 	 changeDisplay("sortTabletable","none");
	 changeDisplay("nodata","inline");
	 changeDisplay("selectExchangeMessage","none");
 }
}

/*============================================== for dwr =========================================================== */

</script>
</html:html>
