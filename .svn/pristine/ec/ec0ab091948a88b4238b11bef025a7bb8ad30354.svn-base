
<%@ page import = "java.io.PrintWriter" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "java.text.ParseException" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.Locale,java.util.*" %>
<%@ page import = "harrier.income.com.report.*" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" import="app.*"%>
<%@ page  import="harrier.income.com.report.*" %><%@page import="org.apache.log4j.Logger"%>
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
	<head>
		 <html:base/>
		 <link href="./StyleSheet.css" rel="stylesheet" type="text/css">
		 <script language="javascript" src="../Script/codethatcalendarstd.js"></script>
		 <script language="javascript" src="../ScriptI/date_script.js"></script>
		 <script language="javascript" src="box_ex.js"></script>
		 <script language="javascript">
			var c2 = new CodeThatCalendar(caldef2);
		 </script> 
		 <script type="text/javascript" src="./sorttable.js"></script>
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
	
	
		 <!-- 
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
		 -->
	</head>  	
	<body>
			<!-- 
			<table><tr>
			<td align="left" width="300"></td >
		    <td align="left">
		    		<html:submit onclick="compaweight()">Company weightage
		    		</html:submit>
		    </td>
		    <td align="left">
		    		<html:submit onclick="indwiseweight()">Ind wise
		    		</html:submit>
		    </td>
		    <td align="left">
		    		<html:submit onclick="stockcontriweight()">Stock Contri To Change
		    		</html:submit>
		    </td>
		    
		    </tr>
		    </table>
		    -->
		    
		    <p> </p><BR><BR><BR>	
  		    <ul id="tablist">
					<li><a href="#" onClick="return stockDiv()"><bean:message key="Stock_Dividend" /></a></li>
					<li><a href="#" onClick="return tradedVol()"><bean:message key="Traded_Volume" /></a></li>
					
			</ul>  
	   
	 <TABLE border="1" color="black" width="100%">
		<TR>
		<TD bgcolor="LavenderBlush" valign="top" width="100%" height="500">
	    
		    
			    
		    
		<div id=stodiv style="display:inline"> 
		 
		<bean:define id="details" name="batchReportBean" property="tableDataSD"/>
	  	<bean:size id="dataCount" name="batchReportBean" property="tableDataSD"/>
	  	
		    
		    <table width="1000" cellspacing="0" cellpadding="0" >
        		<tr>
		          	<td width="250" class="subHeader" nowrap="nowrap">
		          		&nbsp;
		          	</td>
		          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          		<font size="3" face="Verdana">
		          			<b><bean:message key="Stock_Dividend" /></b>
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
        
    	<table border="1" width="60%" align="center" cellpadding="3" cellspacing="0" id="t1" class="sortable" bordercolor="#EFEFEF">
        	
        	<tr>
        		<td nowrap="nowrap" align="center"  class="gridStyle-header"><bean:message key="stockmaster.stockName" /></td>
          		<td nowrap="nowrap" align="center" class="gridStyle-header"><bean:message key="corporate.Faceval" /></td>
          		<td nowrap="nowrap" align="center" class="gridStyle-header"><bean:message key="StockDivident.Share" /></td>
          		<td nowrap="nowrap" align="center" class="gridStyle-header"><bean:message key="StockDivident.Market" /></td>
          		<td nowrap="nowrap" align="center" class="gridStyle-header"><bean:message key="StockDivident.Dividend" /></td>
          		<td nowrap="nowrap" align="center" class="gridStyle-header"><bean:message key="corporate.Applied" /></td>
		   	</tr>   		
          	
          	
          	<logic:iterate id="details" name="batchReportBean" property="tableDataSD">
          	
          	<tr>
     			<td valign="middle" nowrap="nowrap" bgcolor="white" align="left" class="gridStyle-odd" >
        			<a href='../masters/stockmaster2.jsp?s_stockid=<bean:write name="details" property="stockId"/>'>
        			<font face="Verdana" size="2" color="blue"><bean:write name="details" property="stockName"/>
        			</font></a>
        		</td>
          		<td align="right" nowrap="nowrap" bgcolor="white" valign="middle" class="gridStyle-odd">
          			<font face="Verdana" size="2" color="blue"><bean:write name="details" property="faceVal"/></font>
          		</td>
          		<td align="right" nowrap="nowrap" bgcolor="white" valign="middle" class="gridStyle-odd">
          			<font face="Verdana" size="2" color="blue"><bean:write name="details" property="tis"/></font>
          		</td>
          		<td align="right" nowrap="nowrap" bgcolor="white" valign="middle" class="gridStyle-odd">
          			<font face="Verdana" size="2" color="blue"><bean:write name="details" property="mcv"/></font>
          		</td>
          		<td align="right" nowrap="nowrap" bgcolor="white" valign="middle" class="gridStyle-odd">
          			<font face="Verdana" size="2" color="blue"><bean:write name="details" property="amount"/></font>
          		</td>
          		<td align="right" nowrap="nowrap" bgcolor="white" valign="middle" class="gridStyle-odd">
          			<font face="Verdana" size="2" color="blue"><bean:write name="details" property="date"/></font>
          		</td>      
          	  </tr>
    		</logic:iterate>
        </table> 
       
    	</logic:notEqual>
   				
   		
		</div>	
			
			
		
			
		
		<div id="travol" style="display:none">
		<%
		harrier.income.com.report.BatchReportForm bTrade= new harrier.income.com.report.BatchReportForm();
		bTrade.setSelectUser("5");
		bTrade.getTableDataTr();
		Vector vTradedVolume=bTrade.getVTraded();
		String indExch=(String) vTradedVolume.get(0);
	  	//String var=request.getParameter("filter");
	  	String var="2";
	  	String from =(String)vTradedVolume.get(1);
	  	String to = (String) vTradedVolume.get(2);
	  	//String indExch =request.getParameter("selectIndExch");
	  	//String from =request.getParameter("from");
	  	//String to =request.getParameter("to");
	  	
	  	String excel_path = "./FileDownload.jsp?var="+var+"&type=25&filename=TradedVolume.xls&from="
	  			+from+"&to="+to+"&indExch="+indExch;
	  	String str_url = "./EmailReport.jsp?switch_type=25&cas=25&varid="+var+"&rname=TradedVolume.xls&from="
	  				+from+"&to="+to+"&indExch="+indExch;
	%>
		
		<table width="1000" cellspacing="0" cellpadding="0" >
        		<tr>
		          	<td width="250" class="subHeader" nowrap="nowrap">
		          		&nbsp;
		          	</td>
		          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          		<font size="3" face="Verdana">
		          			<b><bean:message key="Traded_Volume" /></b>
		          		</font>
		         	</td> 
	          </tr>
		</table> 
		
		
	
	<table align="right">
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
	 			<!-- Email Report -->
	 			<a href='<%= str_url %>' ><font size="1" face="Verdana" color="blue"> <bean:message key="LatestDivisor.emailr" /></font></a>
	 		</td> 	
	</table>
	

<br>
		<bean:define id="details" name="batchReportBean" property="tableDataTr"/>
	  	<bean:size id="dataCount" name="batchReportBean" property="tableDataTr"/>
	

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
			border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#EFEFEF">
			<thead>
				<tr>
    	   		<th nowrap="nowrap" align="center"  id="stockName" class="gridStyle-header">
          		<span><b><bean:message key="stockmaster.stockName" /></b></span></th>
          		<th align="center"  id="Traded" class="gridStyle-header">
          		<span><b><bean:message key="TradeVolumeInd.Traded" /></b></span></th>
		   	</tr>   		
          	</thead>
		<tbody>
          
          	<logic:iterate id="details" name="batchReportBean" property="tableDataTr">
          	
          	<tr>
          	  
          	 <td valign="middle" bgcolor="white" align="left"  class="gridStyle-odd" axis="sstring" headers="stockName"
           			title='<bean:write name="details" property="stockName"/>'>
        			<a href='../masters/stockmaster2.jsp?s_stockid=<bean:write name="details" property="stockId"/>'>
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
        
    	</logic:notEqual>
   		

		</div>	
		
		
		
	</TD>
 	</TR>
 	</TABLE> 	
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
	
	</tr>
	<tr>
	<td width="125" align="right"><font size="2" face="Arial"><b>
    	<bean:message key="corporate.Fdate" />&nbsp;:</b>
         </font>
   	</td>
   	<td><font size="2" face="Arial">
   		<bean:write name="batchReportBean" property="from"/>
   		</font>
   	</td> 
	</tr>
	<tr>
	<td width="125" align="right"><font size="2" face="Arial"><b>
    	<bean:message key="corporate.Tdate" />&nbsp;:</b>
         </font>
   	</td>
   	<td><font size="2" face="Arial">
   		<bean:write name="batchReportBean" property="to"/>
   		</font>
   	</td> 
	</tr>
</table>

</div>	
	
	<html:form action="/batchReportAction">
	<html:hidden property="indexName" name="batchReportBean" />
	<html:hidden property="defaultVal" ></html:hidden>
	<html:hidden property="compute" value="no"></html:hidden>
				
	 
</html:form>	

    
</body>

<script language="javascript">
var man1=document.getElementById("hiddenTable");
man1.style.display= "none"; 

var selectedtablink="";
var tcischecked=false;



function initialize() {
	
	
}
function stockDiv() {
		//alert("stockDiv");
		changeDisplay("stodiv","inline");
		changeDisplay("travol","none");
		
 		document.forms[0].compute.value="yes";
        document.forms[0].defaultVal.value="no";
       	//alert(document.forms[0].compute.value);
        return false;
}
	

function tradedVol(){
		//alert("tradedVol");
		changeDisplay("travol","inline");
		
		changeDisplay("stodiv","none");
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


function PrintThisPage() { 
	var sOption="toolbar=yes,location=no,directories=yes,menubar=yes,"; 
    sOption+="scrollbars=yes,width=750,height=600,left=100,top=25"; 

   	//var sWinHTML = document.getElementById('contentstart').innerHTML; 
   	var sWinHTML = document.getElementById('travol').innerHTML; 
   	//var printHead =document.getElementById('PageTitle').innerHTML;
   	var moreDet = document.getElementById('hiddenTable').innerHTML;
   		
    var winprint=window.open("","",sOption); 
    winprint.document.open(); 
    winprint.document.write('<html><body>'); 
    winprint.document.write(printHead);
    winprint.document.write('<br>');
    winprint.document.write(moreDet); 
    	
    winprint.document.write(sWinHTML); 
    //winprint.document.write(moreDet);          
    winprint.document.write('</body></html>'); 
    winprint.document.close(); 
    winprint.focus(); 
}


</script>
</html:html>






