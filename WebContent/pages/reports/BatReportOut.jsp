<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" import="app.*"%>
<%@ page language="java" import="harrier.income.com.report.*"%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="java.io.*"%>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setHeader("Expires","0");

%>
<jsp:useBean id="batchReportBean" scope="session" class="harrier.income.com.report.BatchReportForm"/>
<html:html>
<html:errors />
<head>
 	<html:base/>
	<title></title>
	<link href="StyleSheet.css" rel="stylesheet" type="text/css">
	<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
	<script language="javascript" src="box_ex.js"></script>
	<script language="javascript">
		var c2 = new CodeThatCalendar(caldef2);
	</script>
	
	<style type="text/css">

/*Eric Meyer's based CSS tab*/

#tablist{
padding: 3px 0;
margin-left: 0;
margin-bottom: 0;
margin-top: 0.1em;
font: bold 12px Verdana;
}

#tablist li{
list-style: none;
display: inline;
margin: 0;
}

}
#tablist li a{
text-decoration: none;
padding: 3px 0.5em;
margin-left: 3px;
border: 1px solid #778;
border-bottom: none;
background: white;
}

#tablist li a:link{
color:red;
background:"LavenderBlush";
}
#tablist li a:visited{
color: navy;
background:LavenderBlush
}

#tablist li a:hover{
color: #000000;
background: #C1C1FF;
border-color: #227;
}

#tablist li a.current{
background: lightyellow;
}
</style>
	
	
	
	
	
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
<body onload="initialize()">
<html:form action="/batchReportAction">	

		
	  <!--
		<table>
			<td nowrap="nowrap"> 
				<html:submit  onclick="return capitalchange()" ><bean:message key="Capital_Change"/></html:submit>&nbsp;&nbsp;
			</td>
			<td nowrap="nowrap"> 
				<html:submit  onclick="return stockdetail()" ><bean:message key="Stock_Details"/></html:submit>&nbsp;&nbsp;
	   		</td>
  		</table>
  		<hr>	
  		-->
  	<p> </p><BR><BR><BR>	
  	<ul id="tablist">
					<li><a href="#" onClick="return capitalchange()"><bean:message key="Capital_Change"/></a></li>
					<li><a href="#" onClick="return stockdetail()"><bean:message key="Stock_Details"/></a></li>
	</ul>  
	   
	 <TABLE border="1" color="black" width="100%">
		<TR>
		<TD bgcolor="LavenderBlush" valign="top" width="100%" height="500">
	   
  		
  		
		

		
		<div id=contentstart style="display:inline">
	  			<bean:define id="details" name="batchReportBean" property="tableDatac"/>
	  			<bean:size id="dataCount" name="batchReportBean" property="tableDatac"/>
	  				
        					<table width="600" cellspacing="0" cellpadding="0" >
								<td width="200" class="subHeader" nowrap="nowrap">&nbsp;</td>
								<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
								<font size="3" face="Verdana">
		    					<b><bean:message key="Capitalctuniverse.title" /></b>
								</font>
								</td> 
							 </table> 
							
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
        					<table class="sorted" ID="sortTable" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#EFEFEF">
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
          						<logic:iterate id="details" name="batchReportBean" property="tableDatac">
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
   			    	</logic:notEqual>
   			 </div>
   		
   	  		<div id="secondreport" style="display:none">
	  			<bean:define id="details" name="batchReportBean" property="tableDatas"/>
	  			<bean:size id="dataCount" name="batchReportBean" property="tableDatas"/>
	  				<table cellspacing="0" cellpadding="0" >
    							<tr>
									<td width="200" class="subHeader" nowrap="nowrap">&nbsp;</td>
		   							<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		   							<font size="3" face="Verdana">
		        					<b><bean:message key="StockHighLow.title" /></b>
		         					</font>
		   							</td> 
	  								</tr>
					</table> 
	 	 			
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
          					
				<table class="sorted" ID="sortTable" 
					border="1" align="center" cellpadding="0" cellspacing="0" bgcolor="#EFEFEF">
				<thead>
				<tr>
				<!-- <table border="1" width="100%" align="right" cellpadding="2" cellspacing="0" id="t1" class="sortable" bordercolor="#EFEFEF">
 		      		
          		<tr> -->
          			<th nowrap="nowrap" align="center" id="stockName" class="gridStyle-header">
          			<span><b><bean:message key="stockmaster.stockName" /></b></span></th>
          			<th nowrap="nowrap" align="center" id="ovalue" class="gridStyle-header">
          			<span><b><bean:message key="StockHighLow.ovalue" /></b></span></th>
		          	<th nowrap="nowrap" align="center" id="cloval" class="gridStyle-header">
		          	<span><b><bean:message key="StockHighLow.cloval" /></b></span></th>
        		  	<th nowrap="nowrap" align="center" id="lowval" class="gridStyle-header">
        		  	<span><b><bean:message key="StockHighLow.lowval" /></b></span></th>
          			<th nowrap="nowrap" align="center" id="hival" class="gridStyle-header">
          			<span><b><bean:message key="StockHighLow.hival" /></b></span></th>
          			<th nowrap="nowrap" align="center" id="trdvolume" class="gridStyle-header">
          			<span><b><bean:message key="StockHighLow.trdvolume" /></b></span></th>
      		    	<th nowrap="nowrap" align="center" id="trdvalue" class="gridStyle-header">
      		    	<span><b><bean:message key="StockHighLow.trdvalue" /></b></span></th>
          			<th nowrap="nowrap" align="center" id="mcp" class="gridStyle-header">
          			<span><b><bean:message key="IndexDivisor.mcp" /></b></span></th>
          			<th nowrap="nowrap" align="center" id="numotrd" class="gridStyle-header">
          			<span><b><bean:message key="StockHighLow.numotrd" /></b></span></th>
          			<th nowrap="nowrap" align="center" id="Date" class="gridStyle-header">
          			<span><b><bean:message key="corporate.Date" /></b></span></th>
     			</tr> 
     			</thead>
				<tbody>
     			<logic:iterate id="details" name="batchReportBean" property="tableDatas">
     			<tr>
     				<td valign="middle" bgcolor="white" align="left" class="gridStyle-odd" axis="sstring" headers="stockName"
           			title='<bean:write name="details" property="stockName"/>'>
        				<font face="Verdana" size="2" color="blue">
        				<bean:write name="details" property="stockName"/></font>
          			</td>
          			<td align="right" bgcolor="white" valign="middle" class="gridStyle-odd" axis="number" headers="ovalue"   
	  		title='<bean:write name="details" property="openVal"/>'>
          				<font face="Verdana" size="2" color="blue">
          				<bean:write name="details" property="openVal"/></font>
          			</td>
          			<td bgcolor="white" align="right" valign="middle" class="gridStyle-odd" axis="number" headers="cloval"   
	  		title='<bean:write name="details" property="closeVal"/>'>
          				<font face="Verdana" size="2" color="blue">
          				<bean:write name="details" property="closeVal"/></font>
          			</td>
          			<td bgcolor="white" align="right" valign="middle" class="gridStyle-odd" axis="number" headers="lowval"   
	  		title='<bean:write name="details" property="lowVal"/>'>
          				<font face="Verdana" size="2" color="blue">
          				<bean:write name="details" property="lowVal"/></font>
         		 	</td>
       			   	<td bgcolor="white" align="right" valign="middle" class="gridStyle-odd" axis="number" headers="hival"   
	  		title='<bean:write name="details" property="highVal"/>'>
       			   		<font face="Verdana" size="2" color="blue">
       			   		<bean:write name="details" property="highVal"/></font>
     		     	</td>
     		     	<td bgcolor="white" align="right" valign="middle" class="gridStyle-odd" axis="number" headers="trdvolume"   
	  		title='<bean:write name="details" property="tradedVol"/>'>
     		     		<font face="Verdana" size="2" color="blue">
     		     		<bean:write name="details" property="tradedVol"/></font>
       			   	</td>
         		 	<td bgcolor="white" align="right" valign="middle" class="gridStyle-odd" axis="number" headers="trdvalue"   
	  		title='<bean:write name="details" property="tradedVal"/>'>
         		 		<font face="Verdana" size="2" color="blue">
         		 		<bean:write name="details" property="tradedVal"/></font>
         		 	</td>
        		  	<td bgcolor="white" align="right" valign="middle" class="gridStyle-odd" axis="number" headers="mcp"   
	  		title='<bean:write name="details" property="mcv"/>'>
        		  		<font face="Verdana" size="2" color="blue">
        		  		<bean:write name="details" property="mcv"/></font>
        		  	</td>
      		    	<td bgcolor="white" align="right" valign="middle" class="gridStyle-odd" axis="number" headers="numotrd"   
	  		title='<bean:write name="details" property="noOfTrades"/>'>
      		    		<font face="Verdana" size="2" color="blue">
      		    		<bean:write name="details" property="noOfTrades"/></font>
      		    	</td>
      		    	<td bgcolor="white" align="center" valign="middle" nowrap="nowrap" class="gridStyle-odd" axis="date" headers="Date"   
	  		title='<bean:write name="details" property="date"/>'>
      		    		<font face="Verdana" size="2" color="blue">
      		    		<bean:write name="details" property="date"/></font>
       			   	</td>
    			</tr>
				</logic:iterate> 
				</tbody>
			</table>
			
			</logic:notEqual>
			</div>
  </TD>
 </TR>
 </TABLE> 		
   	
  	<html:hidden property="stockName"></html:hidden>
  	<html:hidden property="Pr" value="no"></html:hidden>
    <html:hidden property="defaultVal" ></html:hidden> 
    <html:hidden property="clear" value="no"></html:hidden>
    <html:hidden property="compute" value="no"></html:hidden> 
  </html:form>
  
</body>

<script language="javascript">
var man1=document.getElementById("hiddenTable");
man1.style.display= "none"; 

var selectedtablink=""
var tcischecked=false


function initialize() {
	
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
function capitalchange() {
		//alert("Capital Change");
		changeDisplay("contentstart","inline");
		changeDisplay("secondreport","none");
		
 		document.forms[0].compute.value="yes";
        document.forms[0].defaultVal.value="no";
       	//alert(document.forms[0].compute.value);
        return false;
          
}

function stockdetail(){
		
		changeDisplay("secondreport","inline");
		changeDisplay("contentstart","none");
		
		document.forms[0].compute.value="yes";
        document.forms[0].defaultVal.value="no";
        //alert(document.forms[0].compute.value);
        return false;
       	
}

function changeDisplay( elementId, setTo ) {
var theElement;
if( document.getElementById ) {
//DOM

theElement = document.getElementById( elementId );
} else if( document.all ) {
//Proprietary DOM
theElement = document.all[ elementId ];

}
if( !theElement ) {
/* The page has not loaded, or the browser claims to
support document.getElementById or document.all but
cannot actually use either */
return;
}

//Reference the style ...
if( theElement.style ) { theElement = theElement.style; }
if( typeof( theElement.display ) == 'undefined' ) {
//The browser does not allow us to change the display style
//Alert something sensible (not what I have here ...)
window.alert( 'Your browser does not support this' );
return;
}
//Change the display style
theElement.display = setTo;
}
</script>
</html:html>
