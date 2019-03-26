
<%@page import="org.apache.log4j.Logger"%><%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
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
	log.info("inside session !=nulls");
	form = (LogonForm)session.getAttribute("user");
}
if(form==null ||(!request.isRequestedSessionIdValid())){
	log.info("inside session ==null");
	response.sendRedirect("../userlogintemp.jsp");
}
%>

<html:html>
<html:errors />
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
    		font-family: Verdana;
    		color: black;
    		text-decoration: none;
    		display: block;
		}
		table.sortable span.sortarrow {
    		color: black;
    		text-decoration: none;
		}
		</style> -->
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
	<html:form action="/capChangeUniv">
<!-- Heading  "Capital Change To Universe"  -->
<div id="PageTitle" >
<table width="600" cellspacing="0" cellpadding="0" >
		<td width="200" class="subHeader" nowrap="nowrap">
			&nbsp;
		</td>
		<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
			<font size="3" face="Verdana">
		    	<b><bean:message key="Capitalctuniverse.title" /></b>
			</font>
		</td> 
</table> 
</div>
	<!-- if compute ="yes" display links -->
	
	
	<%
//	Logger Logging = Logger.getLogger("CapitalChangeToUniverseS.jsp");
			String ajax1="no";
			try{
					ajax1=request.getParameter("ajax1");
			}catch (Exception e) {
				// TODO: handle exception
		//		Logging.error(" Error :"+e.getMessage());
			}
	  		String stk_name= request.getParameter("stockName"); 
	  		String var=request.getParameter("selectExchange");
	  		String to = request.getParameter("to");
	  		String from = request.getParameter("from");
	  		// String for Struts links
	  		String excel_path = "./FileDownload.jsp?var="+var+"&type=16&filename=CapitalChangeToUniverse.xls&from="
	  				+from+"&to="+to+"&stkName="+stk_name+"&technology=noAjax";
	  		String str_url = "./EmailReport.jsp?switch_type=16&cas=16&rname=CapitalChangeToUniverse.xls&from="
	  				+from+"&to="+to+"&varid="+var;
	  		String pdf_path="./FileDownload_Pdf.jsp?type=16&filename=CapitalChangeToUniverse.pdf";
	  		String xml_path = "./FileDownloadXmlNew.jsp?var="+var+"&type=16&filename=CapitalChangeToUniverse.xml&from="+from+"&to="+to+"&stkName="+stk_name;
	  		
	  		//Stirngs for AlaxLinks
	  		
	  		String excel_path_ajax = "./FileDownload.jsp?type=16&filename=CapitalChangeToUniverse.xls&technology=Ajax";
	  		String str_url_ajax = "./EmailReport.jsp?switch_type=16&cas=16&rname=CapitalChangeToUniverse.xls&technology=Ajax";
	  		String pdf_path_ajax="./FileDownload_Pdf.jsp?type=16&filename=CapitalChangeToUniverse.pdf&technology=Ajax";
	  		String xml_path_ajax = "./FileDownloadXmlNew.jsp?type=16&filename=CapitalChangeToUniverse.xml&technology=Ajax";
	  		
	  		
	  		
	  					
	  		%>
	  		<!-- Table fro AjaxLinks -->
	<table align="right" id = "AjaxLinks" style="display: none;">
         	<!-- Links  -->
	 		<td width="50">
	 			<!-- Printer friendly -->
	 			<a href="javascript:PrintThisPage();" ><font size="1" face="Verdana" color="blue" > <bean:message key="LatestDivisor.printerf" /></font></a>
	 		</td>
	 		<td width="50">
	 			<!-- DownLoad Excel -->
	 			<a href='<%= excel_path_ajax %>' ><font size="1" face="Verdana" color="blue" > <bean:message key="LatestDivisor.downloade" /></font></a>&nbsp;
	 		</td>
	 		<td width="90">
	 			<!-- DownLoad Xml -->
	 			<a href='<%= xml_path_ajax %>' ><font size="1" face="Verdana" color="blue" > Export to Xml</font></a>&nbsp;
	 		</td>
	 		<td width="50">
	 			<!-- DownLoad Xml -->
	 			<a href='<%= pdf_path_ajax %>' ><font size="1" face="Verdana" color="blue" > <bean:message key="LatestDivisor.pdfr" /></font></a>&nbsp;
	 		</td>
	 		<td width="50">
	 			<!-- Email Report -->
	 			<a href='<%= str_url_ajax %>' ><font size="1" face="Verdana" color="blue" > <bean:message key="LatestDivisor.emailr" /></font></a>&nbsp;
	 		</td> 	
	</table>
	  		
	  		
	  		<logic:equal  value="yes" parameter="compute" >	
	  		
	  		<p align="right">
	<table align="right" id = "StrutsLinks">
         	<!-- Struts Links  -->
	 		<td width="50">
	 			<!-- Printer friendly -->
	 			<a href="javascript:PrintThisPage();" ><font size="1" face="Verdana" color="blue" > <bean:message key="LatestDivisor.printerf" /></font></a>
	 		</td>
	 		<td width="50">
	 			<!-- DownLoad Excel -->
	 			<a href='<%= excel_path %>' ><font size="1" face="Verdana" color="blue" > <bean:message key="LatestDivisor.downloade" /></font></a>&nbsp;
	 		</td>
	 		<td width="90">
	 			<!-- DownLoad Xml -->
	 			<a href='<%= xml_path %>' ><font size="1" face="Verdana" color="blue" > Export to Xml</font></a>&nbsp;
	 		</td>
	 		<td width="50">
	 			<!-- DownLoad Xml -->
	 			<a href='<%= pdf_path %>' ><font size="1" face="Verdana" color="blue" > <bean:message key="LatestDivisor.pdfr" /></font></a>&nbsp;
	 		</td>
	 		<td width="50">
	 			<!-- Email Report -->
	 			<a href='<%= str_url %>' ><font size="1" face="Verdana" color="blue" > <bean:message key="LatestDivisor.emailr" /></font></a>&nbsp;
	 		</td> 	
	</table>
	</p>
	
	</logic:equal>
	<br>
	<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    <table width="656">
     	<tr>
     		<!-- Lable for "Select Stock Exchange"  -->
        	<td width="212" nowrap="nowrap" align="right">
				<font size="2" face="Verdana">
           			<bean:message key="Capitalctuniverse.ssexchange" />:
           		</font>
        	</td> 
        	<!-- Select List for Exchanges -->
        	<td width="489" nowrap="nowrap" align="left" height="30">
          		<html:select property="selectExchange" size="1" styleId="sIE">
					<html:optionsCollection property="selectExchgCollection" name="capChangeUnivBean"/>
				</html:select>
         	</td>
        </tr>
  	</table>
  	<table width="644" height="40">
    	<tr>
        	<td width="212" align="right" nowrap="nowrap"><!-- From date -->
         		<font size="2" face="Verdana">
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
            <td nowrap="nowrap">
            	<html:submit onclick=" return go();" onkeypress="return go();"><bean:message key="Reports.View"/></html:submit>&nbsp;&nbsp;
			</td>
			<% }else{
			%>
			<td>
				<INPUT type="button" name="view" value="View" onclick="getCapitalChangetoUniverse()">
			</td>
			<% } %>
		</tr>
	</table>
	
	  
          <!-- ============================================ For Ajax ================================================== -->	
   <div id="ajaxcontentstart">        
   <table width="160%" class ="sorted"  ID="sortTable"
			border="1" align="center" cellpadding="0" cellspacing="0" bgcolor="#EFEFEF" style="display:none">    
          <!--  <table border="1"> -->      
				<thead >
				<tr>				
					<th width="90">Stock Name</th>
					<th width="80">Face Value</th>
					<th width="150">Total Issued Shares</th>
					<th width="150">Market Cap. Value</th>				
					<th width="170">Investible Weight Factor</th>
					<th width="120">Corporate Action</th>
					<th width="90">Applied Date</th>
				</tr>
				</thead>						
				<tbody id="indexMovingTable"> </tbody>								
	</table>  
	</div>      
   <table border="0" align="center" width="700" height="222" cellspacing="0" cellpadding="0">
	  	<!-- 
		  	<tr>        	
				<td id="selectExchangeMessage" class="gridStyle-message" align="center" valign="middle">
	          		<p style="margin-left: 9"><bean:message key="StockHighLow.smess" /></p>
	          	</td>
	        </tr>
        -->
        <tr>  	
          	<td class="gridStyle-message" align="center" valign="middle" id="nodata" style="display: none;">
          			<p style="margin-left: 9"><bean:message key="IndexCompareOHCL.ndata" /></p>
          	</td>         	
      	</tr>
   </table>       
          
		   
	<!-- ============================================ For Ajax ================================================== -->
	
	<logic:notEqual value="yes" parameter="compute">
	  <table border="0" align="center" width="700" height="222" cellspacing="0" cellpadding="0">
	  	<tr>        	
			<td id="selectExchangeMessage" class="gridStyle-message" align="center" valign="middle">
          		<p style="margin-left: 9"><bean:message key="StockHighLow.smess" /></p>
          	</td>         	
      	</tr>
      </table>
    </logic:notEqual>
    <!-- 
    <logic:notEqual value="yes" parameter="compute" >
				 <table border="0" align="left" width="700" height="222" cellspacing="0" cellpadding="0">
	  				<tr>
        				<td width="99">
        				</td>
						<td id = "selectExchangeMessage" class="gridStyle-message" align="center" valign="middle">
          					<p style="margin-left: 9">
          						<bean:message key="StockHighLow.smess" />
          					</p>
          				</td>
      				</tr>
      			  </table>
    </logic:notEqual> 
	--> 
   	<logic:equal  value="yes" parameter="compute" >
   	  	
	  	<bean:define id="details" name="capChangeUnivBean" property="tableData"/>
	  	<bean:size id="dataCount" name="capChangeUnivBean" property="tableData"/>
	  		
	  	<logic:equal value="0" name="dataCount" >
	  	<table border="0" align="center" width="631" height="222" cellspacing="0" cellpadding="0">
	  	    <tr>         			  		
          		<td class="gridStyle-message" align="left" valign="middle" id = "noStrutsData">
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
    	<!-- <table border="1" width="100%" align="center" cellpadding="3" cellspacing="0" id="t1" class="sortable" bordercolor="#EFEFEF">
        	<tr> -->
        		
        		<!-- Table Heading -->
          		<th nowrap="nowrap" align="center"  id="stockName" class="gridStyle-header">
          		<span><b><bean:message key="stockmaster.stockName" /></b></span></th>
          		<th align="center" nowrap="nowrap" id="faceValue" class="gridStyle-header">
          		<span><b><bean:message key="stockmaster.faceValue" /></b></span></th>
		        <th align="center" nowrap="nowrap" id="totishares" class="gridStyle-header">
		        <span><b><bean:message key="Capitalctuniverse.totishares" /></b></span></th>
        		<th nowrap="nowrap" align="center" id="mcapvalue" class="gridStyle-header">
        		<span><b><bean:message key="Capitalctuniverse.mcapvalue" /></b></span></th>
          		<th align="center" nowrap="nowrap" id="invwf" class="gridStyle-header">
          		<span><b><bean:message key="Capitalctuniverse.invwf" /></b></span></th>
          		<th nowrap="nowrap" align="center" id="Action" class="gridStyle-header">
          		<span><b><bean:message key="corporate.Action" /></b></span></th>
      		    <th nowrap="nowrap" align="center" id="Applied" class="gridStyle-header">
      		    <span><b><bean:message key="corporate.Applied" /></b></span></th>
          	</tr>   		
          	</thead>
		<tbody>
          	<!-- Iterate over the table data -->
          	<logic:iterate id="details" name="capChangeUnivBean" property="tableData">
          	<tr>
     			<td valign="middle" bgcolor="white" align="left" class="gridStyle-odd" axis="sstring" headers="stockName"
           			title='<bean:write name="details" property="stockName"/>'>
        			<font face="Verdana" size="2" color="blue"><bean:write name="details" property="stockName"/></font>
          		</td>
          		<td align="right" nowrap="nowrap" bgcolor="white" valign="middle" class="gridStyle-odd" axis="number" headers="faceValue"
           			title='<bean:write name="details" property="faceVal"/>'>
          			<font face="Verdana" size="2" color="blue"><bean:write name="details" property="faceVal"/></font>
          		</td>
          		<td bgcolor="white" nowrap="nowrap" align="right" valign="middle" class="gridStyle-odd" axis="number" headers="totishares"
           			title='<bean:write name="details" property="tis"/>'>
          			<font face="Verdana" size="2" color="blue"><bean:write name="details" property="tis"/></font>
          		</td>
          		<td bgcolor="white" align="right" nowrap="nowrap" valign="middle" class="gridStyle-odd" axis="number" headers="mcapvalue"
           			title='<bean:write name="details" property="mcv"/>'>
          			<font face="Verdana" size="2" color="blue"><bean:write name="details" property="mcv"/></font>
         	 	</td>
       		   	<td bgcolor="white" align="right" nowrap="nowrap" valign="middle" class="gridStyle-odd" axis="number" headers="invwf"
           			title='<bean:write name="details" property="iwf"/>'>
       		 		<font face="Verdana" size="2" color="blue"><bean:write name="details" property="iwf"/></font>
     		    </td>
     		   	<td bgcolor="white" align="left" nowrap="nowrap" valign="middle" class="gridStyle-odd" axis="sstring" headers="Action"
           			title='<bean:write name="details" property="corpAction"/>'>
     		   		<font face="Verdana" size="2" color="blue"><bean:write name="details" property="corpAction"/></font>
       		   	</td>
         	 	<td bgcolor="white" align="right" nowrap="nowrap" valign="middle" class="gridStyle-odd" axis="date" headers="Applied"
           			title='<bean:write name="details" property="date"/>'>
         	 		<font face="Verdana" size="2" color="blue"><bean:write name="details" property="date"/></font>
         		</td> 	
          
    		</tr>
    		</logic:iterate>
             </tbody>  
        </table> 
        </div>
    	</logic:notEqual>
   	</logic:equal>
   	<div id="hiddenTable">
    <table id="hiddenTable" >
    	<tr>
    		<td><b>
    			<font size="2" face="Verdana">
            		<bean:message key="StockList.Exchange" />&nbsp;:
            	</font></b>
    		</td>
    		<td ><font size="2" face="Verdana"> 
    			<bean:write name="capChangeUnivBean" property="stockName"/>
    			</font>
    		</td>
    	</tr>
    	<tr>
    		<td><b><font size="2" face="Verdana">
            		<bean:message key="corporate.Fdate" />&nbsp;:
            	</font>
    		</b></td>
    		<td ><font size="2" face="Verdana">
    			<bean:write name="capChangeUnivBean" property="from"/>
    			</font>
    		</td>
    	</tr>
    	<tr>
    		<td><b><font size="2" face="Verdana">
                	<bean:message key="corporate.Tdate" />&nbsp;:
                </font>
    		</b></td>
    		<td><font size="2" face="Verdana">
    			<bean:write name="capChangeUnivBean" property="to"/>
    			</font>
    		</td>
    	</tr>
    </table>
    </div>    
  	<html:hidden property="stockName"></html:hidden>
  	<html:hidden property="Pr" value="no"></html:hidden>
    <html:hidden property="defaultVal" ></html:hidden>
    <html:hidden property="clear" value="no"></html:hidden>
    <html:hidden property="compute" value="no"></html:hidden> 
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
 		var i = 0;
        var fields = new Array();
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
			document.forms[0].defaultVal.value="no";
         	return true;
         }
}

function PrintThisPage() 
{ 		
 		var sOption="toolbar=yes,location=no,directories=yes,menubar=yes,"; 
       	sOption+="scrollbars=yes,width=750,height=600,left=100,top=25"; 
		//var sWinHTML = document.getElementById('contentstart').innerHTML; 
   		var printHead =document.getElementById('PageTitle').innerHTML;
   		var moreDet = document.getElementById('hiddenTable').innerHTML;
   		var winprint=window.open("","",sOption); 
       	winprint.document.open(); 
       	winprint.document.write('<html><body>'); 
       	winprint.document.write(printHead);
       	winprint.document.write('<br>');
       	//winprint.document.write(moreDet); 
       	winprint.document.write('<br>');
       	if(Button=="AjaxButton"){
       	
       		var from = DWRUtil.getValue("from"); 
	  		var to = DWRUtil.getValue("to");
	  		winprint.document.write('<br><b>From Date:');
	  		winprint.document.write(from); 
 			winprint.document.write('<br>To Date:');
 			winprint.document.write(to); 
 			winprint.document.write('<br>');
       	  	var moreDet = DWRUtil.getText("selectExchange");
   			var sWinHTML = document.getElementById('ajaxcontentstart').innerHTML; 
   			winprint.document.write("<font size=3>Exchange Name :"+moreDet+"</font></b>");
   		}
   		else{ 
   		
   			var sWinHTML = document.getElementById('contentstart').innerHTML;
   			var moreDet = document.getElementById('hiddenTable').innerHTML;
   			winprint.document.write(moreDet);
   		}   	
       	winprint.document.write(sWinHTML); 
       	//winprint.document.write(moreDet);          
       	winprint.document.write('</body></html>'); 
       	winprint.document.close(); 
       	winprint.focus(); 
}

/*===================================== For DWR ========================================================================*/
function getCapitalChangetoUniverse() {
  DWRUtil.useLoadingMessage();    
  var todate = DWRUtil.getValue("to");
  var fromdate = DWRUtil.getValue("from");
  var exchange = DWRUtil.getValue("selectExchange");
  var exchangeName = DWRUtil.getText("selectExchange");
  //alert(todate+fromdate+index);  
  MoveTable.getCapitalChangeToUniverse(fillTable, exchange, fromdate, todate,exchangeName);
}

	var stockName = function(StockDetails) { return '<font face="Verdana" color="blue" size="2">'+StockDetails.stockName+'</font>' };			
	var faceVal = function(StockDetails) { return '<font face="Verdana" color="blue" size="2">'+StockDetails.faceVal+'</font>'}; // if we return to using dates, add .toLocaleDateString()
	var tis = function(StockDetails) { return '<font face="Verdana" color="blue" size="2">'+StockDetails.tis+'</font>'};
	var mcv = function(StockDetails) { return '<font face="Verdana" color="blue" size="2">'+StockDetails.mcv+'</font>'};
	var iwf = function(StockDetails) { return '<font face="Verdana" color="blue" size="2">'+StockDetails.iwf+'</font>'};
	var corpAction = function(StockDetails) { return '<font face="Verdana" color="blue" size="2">'+StockDetails.corpAction+'</font>'};
	var date = function(StockDetails) { return '<font face="Verdana" color="blue" size="2">'+StockDetails.date+'</font>'};
	
	//alert(stockId);
function fillTable(indexMovingTable) {
  
  var a = new Array();
	a = indexMovingTable;   
 if(a.length){
   	  Button = "AjaxButton";
   	  changeDisplay("selectExchangeMessage","none");
 	  changeDisplay("noStrutsData","none");
 	  changeDisplay("nodata","none");
 	  changeDisplay("sortTabletable","none");
 	  changeDisplay("sortTable","inline");
 	  changeDisplay("AjaxLinks","inline"); 	  	  	
	  DWRUtil.removeAllRows("indexMovingTable");
	  DWRUtil.addRows("indexMovingTable",indexMovingTable, [ stockName, faceVal, tis, mcv, iwf, corpAction, date ]);
 }
 else{ 
 	 changeDisplay("sortTable","none");
 	 changeDisplay("sortTabletable","none");
 	 changeDisplay("noStrutsData","none");
	 changeDisplay("nodata","inline");
	 changeDisplay("selectExchangeMessage","none");
 }
}
</script>
</html:html>
