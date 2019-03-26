<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ page import="sysconfig.model.*,java.util.*, org.apache.struts.util.*" %>
<%@ page language="java" import="app.*"%><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<html:html>
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
<html:errors /> 
<head>
 	<html:base/>
 	<META Http-Equiv="Cache-Control" Content="no-cache">
	<META Http-Equiv="Pragma"        Content="no-cache">
	<META Http-Equiv="Expires"       Content="0">
	<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
	<meta name="GENERATOR" content="Microsoft FrontPage 6.0">
	<meta name="ProgId" content="FrontPage.Editor.Document">
	<link rel="stylesheet" type="text/css" href="./StyleSheet.css"  title="Default" />  
	<head>
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
		<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
		<script language="javascript" src="box_ex.js"></script>
		<script language="javascript">
		var c2 = new CodeThatCalendar(caldef2);
	</script>
    	<title>Stock Details From Date</title>
	</head>
	<body onload="initialize()" >
<html:form action="/stock-detailFD">
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
</style> -->

<div id="title">
 <table width="100%" >
        	<tr><td width="100%" nowrap="nowrap">
  		 		<table width="1000" cellspacing="0" cellpadding="0"  align="center">
        			<tr>
		          		<td width="330" class="subHeader" nowrap="nowrap">
		          			&nbsp;
			          	</td>
			          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
			          			<font size="3" face="Verdana">
		    	      				<b><bean:message key="StockDetailFromDate.title" /></b>
		        	  			</font>
		         		</td> 
		          </tr>
		      </table> 
	      </td></tr>
    </table>
  </div> 
  <logic:equal value="yes"  property="hiddenVar" name="stockDetailFDBean">
  <logic:notEmpty name="stockDetailFDBean" property="tableDate">
  <%	       					 String excel_path= "./FileDownload.jsp?&type=23&filename=StockDetail.xls";
	       						 String email_str="./EmailReport.jsp?switch_type=23&cas=23&rname=StockDetail.xls";
  %>

                       <table  align="right">
                       			<td><font size="1" face="Verdana" color="blue"><a href="javascript:PrintThisPage()"><bean:message key="IndexPerformance.printerf" /></a></font></td>
	                       		<td><font size="1" face="Verdana" color="blue"><a href="<%=excel_path%>"> <bean:message key="IndexPerformance.downloade" /></a> </font></td>
							 	<td ><font size="1" face="Verdana" color="blue"><a href="<%=email_str%>" ><bean:message key="IndexPerformance.emailr" /></a></font></td>
						</table>
						<br>
  </logic:notEmpty>
</logic:equal>
 	  <table border="0" width="100%"  cellspacing="0" cellpadding="3" height="80">
	        <tr>
    	    	<td align="left" width="30" nowrap="nowrap">
        			&nbsp;
       			</td>
	        	<td align="right" width="155" nowrap="nowrap" >
    	    		<font face="Verdana" size="2">
        				<span id="filterId"><bean:message key="TradeVolumeInd.Filter" />:</span>
          			</font>
	          	</td>
    	      	<td align="left" nowrap="nowrap" width="1210" >
        	    	<html:select name="stockDetailFDBean" property="filter" size="1" onchange="return filterChange();" styleId="sFilter">
            			<html:option value="0"><bean:message key="StockPerformance.notsel" /></html:option>
					    <html:option value="Index Wise"><bean:message key="TradeVolumeInd.Index" /></html:option>
				    	<html:option value="Exchange Wise"><bean:message key="TradeVolumeInd.Wise" /></html:option>
		         	</html:select>
    	      	</td>
          </tr>
          <tr>
        	<td align="left" width="35" nowrap="nowrap">
        		&nbsp;
          	</td>
        	<td align="center" width="155" nowrap="nowrap" >
        		<font face="Verdana" size="2">
        			<bean:message key="StockDetailFromDate.Select" />:
          		</font>
          	</td>
          	<td align="left" nowrap="nowrap" width="1210">
            	<html:select name="stockDetailFDBean" property="select" size="1"   onchange="indiChange();" styleId="sIE" >
					 		<html:optionsCollection property="selectCollection" name="stockDetailFDBean"/>
				</html:select>
          	</td>
        </tr>
        </table>
       <table border="0" width="59%"  cellspacing="0" cellpadding="3" height="80"> 
  <!--      <tr>
        	<td align="left" width="28" nowrap="nowrap">
        		&nbsp;
          	</td>
          	
        	<td align="right" width="151" nowrap="nowrap">
        		<font face="Verdana" size="2" >
        			&nbsp;
          		</font>
          	</td>
        </tr>
       -->
       
        <tr>
        	<td align="left" width="26" nowrap="nowrap">
        		&nbsp;
          	</td>
          	
        	<td align="right" width="151" nowrap="nowrap">
        		<font face="Verdana" size="2" >
        			<bean:message key="StockDetailFromDate.Stock" />:
          		</font>
          	</td>
          	<td align="left" nowrap="nowrap" width="400">
            	<font face="Verdana" size="2">
            		<logic:empty name="stockDetailFDBean" property="text" >
						<html:select name="stockDetailFDBean" property="selectStock" size="3"  styleId="Rem" style="width:300px" >
					 		<html:optionsCollection property="stockCollection" name="stockDetailFDBean"/>
						</html:select>
					</logic:empty>
					<logic:notEmpty name="stockDetailFDBean" property="text" >
						<html:select name="stockDetailFDBean" property="selectStock" size="3"  styleId="Rem" style="width:300px">
						</html:select>
					</logic:notEmpty>
            	</font>
          	</td>
        </tr>
       </table>
         <table border="0" width="59%"  cellspacing="0" cellpadding="3" height="40">
        <tr>
        	<td align="left" width="23" nowrap="nowrap">
        		&nbsp;
          	</td>
 			<td align="right" width="167" nowrap="nowrap" >
        		<font face="Verdana" size="2">
        			<bean:message key="corporate.Fdate" />:
          		</font>
          	</td>
          	<td align="left" nowrap="nowrap" width="100">
            	<font face="Verdana" size="2">
            	<html:text property="fromDate" name="stockDetailFDBean" size="10" readonly="true"/>
				           	
          		</font>
          	</td>
			<td align="left" width="543" nowrap="nowrap">
                   <input onclick="c2.popup('fromDate');" type="button" value="...">
        			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                <html:hidden property="hiddenVar" name="stockDetailFDBean"/>
        			<html:submit  property="b1" onclick="return go()"><bean:message key="Admin.Go"/></html:submit>
           	</td>
        </tr>
        </table>
         <table border="0" width="100%"  cellspacing="0" cellpadding="3" >
	        <tr>
    	    	<td align="left" width="20" nowrap="nowrap">
        			&nbsp;
          		</td>
          	<td align="left" nowrap="nowrap" width="110">
            	<font face="Verdana" size="2">
					<html:select name="stockDetailFDBean" property="selectStock1" size="1" styleId="combo">
					 	<html:optionsCollection property="stockCollection" name="stockDetailFDBean"/>
					</html:select>
            	</font>
          		
          	</td>
         </tr>
         </table>
                        		<%
					           	 
					           	 session.setAttribute("filter",request.getParameter("filter"));
						   		 session.setAttribute("index",request.getParameter("select"));
					 			 session.setAttribute("fDate",request.getParameter("fromDate"));
							 	 session.setAttribute("stock",request.getParameter("selectStock"));
                        		%>
         
<logic:notEqual value="yes"	property="hiddenVar" name="stockDetailFDBean">


		    <table align="center"  border="0" width="600" height="300"  cellspacing="0" cellpadding="0" class="sortable">
    		      <tr>
        			  <td  class="gridStyle-message" valign="middle" align="center">
         			  
            			 	<bean:message key="CompanyWiseWeight.selmessage" /> 
            			 
		        	  </td>
	    	     </tr>
   	        </table>
</logic:notEqual>

 <logic:equal value="yes"  property="hiddenVar" name="stockDetailFDBean">
			<logic:empty name="stockDetailFDBean" property="tableDate">
 			   <table  border="0" width="600" height="300" cellpadding="0" cellspacing="0" align="center" class="sortable">	
    		 			<tr></tr><tr>
		         			<td  bgcolor="#cacaca" align="center" valign="middle" nowrap="nowrap">
		         				<p style="margin-left: 9">
		         					<b><font face="Verdana" color="blue" size="5">
     									<bean:message key="StockDetailFromDate.No" />
     								</font></b>
     							</p>
     						</td>
     					</tr>
     				</table>
		   </logic:empty>

		<logic:notEmpty name="stockDetailFDBean" property="tableDate">
		<div id="table">
	      		<table border="0" width="800"  cellpadding="2" cellspacing="0"  align="center" id="tabular" >
	    		  <tr>
					<td>
					<table class="sorted" ID="sortTable" 
						border="1" align="center" cellpadding="3" cellspacing="0" bgcolor="#EFEFEF">
						<thead>
						<tr>
						<!-- <table border="1" width="80%" align="center" cellpadding="2" cellspacing="0" id="t1" class="sortable" >
							<tr> -->     
								<th width="10%" bgcolor="#EFEFEF" align="center" nowrap="nowrap" id="Symbol" class="gridStyle-header">
									<span><b><bean:message key="StockDetailFromDate.Symbol" /></b></span>
								</th>
								<th width="10%" bgcolor="#EFEFEF" align="center" nowrap="nowrap" id="Price" class="gridStyle-header">
									<span><b><bean:message key="StockDetailFromDate.Price" /></b></span>
								</th>
								<th width="10%" bgcolor="#EFEFEF" align="center" nowrap="nowrap" id="Pricee" class="gridStyle-header">
			            		    <span><b><bean:message key="StockDetailFromDate.Pricee" /></b></span>
			            		</th>
								<th width="10%" bgcolor="#EFEFEF" align="center" nowrap="nowrap" id="Priceu" class="gridStyle-header">	
	            		    		<span><b><bean:message key="StockDetailFromDate.Priceu" /></b></span>
		            		    </th>
		            		    <th width="10%" bgcolor="#EFEFEF" align="center" nowrap="nowrap" id="Tis" class="gridStyle-header">
	            					<span><b><bean:message key="StockDetailFromDate.Tis" /></b></span>
		            			</th>
		            			<th width="10%" bgcolor="#EFEFEF" align="center" nowrap="nowrap" id="MVC" class="gridStyle-header">
			            			<span><b><bean:message key="StockDetailFromDate.MVC" /></b></span>
			            		</th>
			            		<th width="10%" bgcolor="#EFEFEF" align="center" nowrap="nowrap" id="MVCU" class="gridStyle-header">
			                        <span><b><bean:message key="StockDetailFromDate.MVCU" /></b></span>
			                    </th>
			                    <th width="10%" bgcolor="#EFEFEF" align="center" nowrap="nowrap" id="Traded" class="gridStyle-header">
			                        <span><b><bean:message key="TradeVolumeInd.Traded" /></b></span>
			                    </th>
			                    <th width="10%" bgcolor="#EFEFEF" align="center" nowrap="nowrap" id="Traded111" class="gridStyle-header">
			                        <span><b><bean:message key="StockDetailFromDate.Traded" /></b></span>
			                    </th>
			                    <th width="10%" bgcolor="#EFEFEF" align="center" nowrap="nowrap" id="Series" class="gridStyle-header">
			                        <span><b><bean:message key="StockDetailFromDate.Series" /></b></span>
			                    </th>
			                    <th width="10%" bgcolor="#EFEFEF" align="center" nowrap="nowrap" id="C" class="gridStyle-header">
			                        <span><b><bean:message key="StockDetailFromDate.C" /></b></span>
			                    </th>
	          			</tr>
	          			</thead>
		<tbody>
   			<logic:iterate id="theprod" name="stockDetailFDBean" property="tableDate">
         		  <tr>
			    	<td width="10%" nowrap="nowrap" align="left" bgcolor="white"  class="gridStyle-odd" axis="sstring" headers="Symbol"
           			title='<bean:write name="theprod" property="stockName" />'>
			    	<font face="Verdana" color="blue" size="2">
			    	<bean:write name="theprod" property="stockName" /></font></td>
			    	<td width="10%" nowrap="nowrap" align="left" bgcolor="white" class="gridStyle-odd" axis="date" headers="Price"
           			title='<bean:write name="theprod" property="priceDate" />'>
			    	<font face="Verdana" color="blue" size="2">
			    	<bean:write name="theprod" property="priceDate" /></></td>
			    	<td width="10%" nowrap="nowrap" align="left" bgcolor="white" class="gridStyle-odd" axis="number" headers="Pricee"
           			title='<bean:write name="theprod" property="pusd" />'>
			    	<font face="Verdana" color="blue" size="2">
			    	<bean:write name="theprod" property="pusd" /></></td>
			    	<td width="10%" nowrap="nowrap" align="left" bgcolor="white" class="gridStyle-odd" axis="number" headers="Priceu"
           			title='<bean:write name="theprod" property="price" />'>
			    	<font face="Verdana" color="blue" size="2">
			    	<bean:write name="theprod" property="price" /></></td>
			    	<td width="10%" nowrap="nowrap" align="left" bgcolor="white" class="gridStyle-odd" axis="number" headers="Tis"
           			title='<bean:write name="theprod" property="tis" />'>
			    	<font face="Verdana" color="blue" size="2">
			    	<bean:write name="theprod" property="tis" /></></td>
			    	<td width="10%" nowrap="nowrap" align="left" bgcolor="white" class="gridStyle-odd" axis="number" headers="MVC"
           			title='<bean:write name="theprod" property="musd" />'>
			    	<font face="Verdana" color="blue" size="2">
			    	<bean:write name="theprod" property="musd" /></></td>
			    	<td width="10%" nowrap="nowrap" align="left" bgcolor="white" class="gridStyle-odd" axis="number" headers="MVCU"
           			title='<bean:write name="theprod" property="mcv" />'>
			    	<font face="Verdana" color="blue" size="2">
			    	<bean:write name="theprod" property="mcv" /></></td>
			    	<td width="10%" nowrap="nowrap" align="left" bgcolor="white" class="gridStyle-odd" axis="number" headers="Traded"
           			title='<bean:write name="theprod" property="tradedVolume" />'>
			    	<font face="Verdana" color="blue" size="2">
			    	<bean:write name="theprod" property="tradedVolume" /></></td>
			    	<td width="10%" nowrap="nowrap" align="left" bgcolor="white" class="gridStyle-odd" axis="number" headers="Traded111"
           			title='<bean:write name="theprod" property="tradedValue" />'>
			    	<font face="Verdana" color="blue" size="2">
			    	<bean:write name="theprod" property="tradedValue" /></></td>
			    	<td width="10%" nowrap="nowrap" align="left" bgcolor="white" class="gridStyle-odd" axis="sstring" headers="Series"
           			title='<bean:write name="theprod" property="series" />'>
			    	<font face="Verdana" color="blue" size="2">
			    	<bean:write name="theprod" property="series" /></></td>
			    	<td width="10%" nowrap="nowrap" align="left" bgcolor="white" class="gridStyle-odd" axis="sstring" headers="C"
           			title='<bean:write name="theprod" property="isCA" />'> 
			    	<font face="Verdana" color="blue" size="2">
			    	<bean:write name="theprod" property="isCA" /></></td>
          		 </tr> 
          		     
    		</logic:iterate>
    		 </tbody> 
  		</td></tr></table>
  		</table>
	</div>
 	</logic:notEmpty>
</logic:equal>	     				     			
		</td>
		</tr>
		</table>
		<html:hidden property="defaultVal"></html:hidden>
  	 	 <html:hidden property="clear"></html:hidden>
		</html:form>
</body>		
<script language="JavaScript">
	var man1=document.getElementById("combo");
	man1.style.display= "none"; 
	
	var keyTime, keyStr = '', allOpts1, lastElement;
	var agt = navigator.userAgent.toLowerCase();
	var is_gecko = (agt.indexOf("gecko") != -1);
	var rhsSelect = document.getElementById("Rem");
	allOpts1 = new Array();
	var element=document.getElementById("combo");
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
	if((document.forms[0].fromDate.value)=="")
	document.forms[0].fromDate.value= td+ s + mnth + s + yr;
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
function populate()
		{
			var rhsSelect = document.getElementById("Rem");
			var element=document.getElementById("combo");
			var nodesToRemove = new Array();
			var numToRemove=0;
			var textValue11=document.forms[0].text.value;
				if(textValue11=="" || textValue11==null){
					rhsSelect.length=0;
					for(var i = 0; i < element.options.length; i++){
				    	var optionElement = document.createElement("option");
		     			optionElement.value=element.options[i].value;
						optionElement.innerHTML = element.options[i].innerHTML;
						rhsSelect.appendChild(optionElement);
			       }
			       return;
			   }
			rhsSelect.length=0;
		    for(var i = 0; i < element.options.length; i++){
		    	var textValue1=document.forms[0].text.value;
		    	var length1=textValue1.length;
		    	textValue1=textValue1.toLowerCase();
		    	var textValue	=	element.options[i].text.toLowerCase();
		       	var textSub		=	textValue.substring(0,length1);
		       	if(textSub==textValue1){
		     		var optionElement = document.createElement("option");
		     		optionElement.value=element.options[i].value;
					optionElement.innerHTML = element.options[i].innerHTML;
					rhsSelect.appendChild(optionElement);
		    	}
		   }
		}
	function filterChange(){
		document.forms[0].clear.value="no";	
		document.forms[0].hiddenVar.value="no";
		document.forms[0].defaultVal.value="yes";
		document.forms[0].submit();
		return true;
	}
	function indiChange(){
		document.forms[0].hiddenVar.value="no";
		document.forms[0].clear.value="no";	
		document.forms[0].defaultVal.value="no";
		document.forms[0].submit();
		populate();
		return false;
	}

function go(){
	var objTo=document.forms[0].fromDate;
	document.forms[0].clear.value="yes";
	document.forms[0].hiddenVar.value="yes";
	document.forms[0].defaultVal.value="no";		
	var i = 0;
    var fields = new Array();
	if(document.forms[0].filter.value=="0"){
		fields[i++] = "Select Filter is required";
	}
	if(document.forms[0].select.value=="0"){
		fields[i++] = "Select(Index/Exchange) is required";
	}
	var rhsSelect = document.getElementById("Rem");
	if(rhsSelect.options[0]==null)
		fields[i++] = "Select Stock is required";
	var val1=document.forms[0].selectStock.selectedIndex;
	if(val1 == -1){
		fields[i++] = "Select Stock is required";
	}
	if(document.forms[0].fromDate.value==""){
		fields[i++] = "From Date is required";
	}
	if (fields.length > 0) {
        alert(fields.join('\n'));
        return false;
    }
    if((checkdatecurrent(objTo))==false)	
	{
	alert("Date should be Less Than CurrentDate.");
	objTo.focus();
	objTo.select();
	return false;
	}
    else {
        return true;
    }
}

function popprinter(url)
{
	var lhsSelect=document.getElementById("sIE");
	var lhsSelect1=document.getElementById("Rem");
	var IE1,stock,fDate1,sFilter;
	var val=document.forms[0].select.selectedIndex;
	IE1=lhsSelect.options[val].innerHTML;
	var val1=document.forms[0].selectStock.selectedIndex;
	if(val1 == -1){
		alert("Select Stock is required");
	}
	stock=lhsSelect1.options[val1].innerHTML;
	fDate1=document.forms[0].fromDate.value;
	url=url+"&IE="+IE1+"&stock="+stock+"&fDate="+fDate1;
	newwindow=window.open(url,'name','height=500,width=900,scrollbars=yes,left=80,top=130,menubar=yes,status=yes');
	if (window.focus) {newwindow.focus()}
}
function popExcel(usr){
	var lhsSelect=document.getElementById("sIE");
	var lhsSelect1=document.getElementById("Rem");
	var sFil=document.getElementById("sFilter");
	var IE1,stock,fDate1,sFilter;
	var val=document.forms[0].select.selectedIndex;
	IE1=lhsSelect.options[val].innerHTML;
	var val1=document.forms[0].selectStock.selectedIndex;
	if(val1 == -1){
		alert("Select Stock is required");
	}
	stock=lhsSelect1.options[val1].innerHTML;
	var val2=document.forms[0].filter.selectedIndex;
	filter=sFil.options[val2].innerHTML;
	fDate1=document.forms[0].fromDate.value;
	//filter="+filter+"&type=23&filename=StockDetail.xls&index="+str+"&from="+str2+"&stock="+str3;
	parent.document.frames[3].location.href="/FileDownload.jsp?filter="+filter+"&type=23&filename=StockDetail.xls&index="+IE1+"&from="+fDate1+"&stock="+stock;
}

function PrintThisPage() 
{ 
	var fdate=document.forms[0].fromDate.value;
	var ttle =document.getElementById('title').innerHTML;
   	var tble = document.getElementById('table').innerHTML;
   	var winprint=window.open("","",""); 
    winprint.document.open(); 
    winprint.document.write(ttle);
    winprint.document.write('<br>');
    winprint.document.write('<bean:message key="corporate.Fdate" />:');
    winprint.document.write(fdate);
    winprint.document.write('<br>');
	winprint.document.write('<br><br>');
    winprint.document.write(tble); 
    winprint.document.close(); 
    winprint.focus(); 
}
</script>

</html:html>