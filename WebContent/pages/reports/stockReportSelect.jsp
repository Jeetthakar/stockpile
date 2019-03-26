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
  		     <%
 
  harrier.income.com.report.BatchReportForm abc=(harrier.income.com.report.BatchReportForm)session.getAttribute("batchReportBean");
    String str1[]=null;
	str1=abc.getSelectReport();
	int j=0;
	int len=str1.length;
	
	
     if(str1!=null)
     {              
					for(j=0;j<len;j++)
					{
					
							String str=str1[j];
						
						
                          if(str.equals("Stock Dividend"))
						{
							
							
	 %>    			
	 					<li><a href="#" onClick="return stockDiv()"><bean:message key="Stock_Dividend" /></a></li>
					
					
		
	 <%
						}
             			if(str.equals("Traded Volume"))
						{
							
		%>						
						<li><a href="#" onClick="return tradedVol()"><bean:message key="Traded_Volume" /></a></li>
					
					
					<%		
						}
						}
						}
      %>
			</ul>  
	   
	 <TABLE border="1" color="black" width="100%">
		<TR>
		<TD bgcolor="LavenderBlush" valign="top" width="100%" height="500">
	    <%
 
  harrier.income.com.report.BatchReportForm brf=(harrier.income.com.report.BatchReportForm)session.getAttribute("batchReportBean");
    String str[]=null;
	str=brf.getSelectReport();
	int i=0;
	int k=str.length;
	
	
     if(str!=null)
     {              
					for(i=0;i<k;i++)
					{
					
							String s=str[i];
						
						
                          if(s.equals("Stock Dividend"))
						{
							
							
		%>    
		    
		
		    
		    
		<div id=stodiv style="display:inline"> 
		 
		
	  	
		    
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
		 <bean:define id="details" name="batchReportBean" property="tableDataSD"/>		
	  	
	  <logic:empty property="tableDataSD" name="batchReportBean">	
	 
	  	<table border="0" align="left" width="631" height="222" cellspacing="0" cellpadding="0">
	  	    <tr>
          		<td width="99"></td>
	  		
          		<td class="gridStyle-message" align="center" valign="middle">
          			<p style="margin-left: 9"><bean:message key="IndexCompareOHCL.ndata" /></p>
          		</td>
        	</tr>
       	</table>
   	 </logic:empty>
   
     <logic:notEmpty property="tableDataSD" name="batchReportBean">
        
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
       
    	</logic:notEmpty>
   				
   		
		</div>	
			
			
	<%
		}
            else if(s.equals("Traded Volume"))
						{
							
	%>		
			
			
			
			
		
		
		
		
		<div id="travol" style="display:inline">
		
		
	  	
	
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
		 <bean:define id="deta" name="batchReportBean" property="tableDataTr"/>
		  <logic:empty property="tableDataTr" name="batchReportBean">
	
	  		  					<table border="0" align="left" width="631" height="222" cellspacing="0" cellpadding="0">
	  	   	 						<tr>
          							<td width="99"></td>
	  			   					<td class="gridStyle-message" align="center" valign="middle">
          							<p style="margin-left: 9"><bean:message key="IndexCompareOHCL.ndata" /></p>
          							</td>
        						</tr>
       							</table>
       	</logic:empty>
   
     <logic:notEmpty property="tableDataTr" name="batchReportBean">
        
        
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
          
          	<logic:iterate id="deta" name="batchReportBean" property="tableDataTr">
          	
          	<tr>
          	  
          	 <td valign="middle" bgcolor="white" align="left"  class="gridStyle-odd" axis="sstring" headers="stockName"
           			title='<bean:write name="deta" property="stockName"/>'>
        			<a href='../masters/stockmaster2.jsp?s_stockid=<bean:write name="deta" property="stockId"/>'>
        			<font face="Arial" size="2" color="blue"><bean:write name="deta" property="stockName"/>
        			</font></a>
        		</td>
          		<td align="right" bgcolor="white" valign="middle" class="gridStyle-odd" axis="number" headers="Traded"
           			title='<bean:write name="deta" property="tradedVol"/>'>
          			<font face="Arial" size="2" color="blue"><bean:write name="deta" property="tradedVol"/></font>
          			
          		</td>
          	  
     		 
        		
          		
         	</tr>
    		</logic:iterate>
            </tbody> 
        </table> 
        
    	</logic:notEmpty>
   		

		</div>	
		
		<%		
						}
						}
						}
      	%>
		
		
	</TD>
 	</TR>
 	</TABLE> 		
	
	<html:form action="/batchReportAction">
	<html:hidden property="indexName" name="batchReportBean" />
	
				
	 
</html:form>	

    
</body>

<script language="javascript">
var man1=document.getElementById("hiddenTable");
man1.style.display= "none"; 

var selectedtablink=""
var tcischecked=false



function initialize() {
	
	
}
function stockDiv() {
		//alert("stockDiv");
		changeDisplay("stodiv","inline");
		changeDisplay("travol","none");
		
 		
       	//alert(document.forms[0].compute.value);
        
}
	

function tradedVol(){
		//alert("tradedVol");
		changeDisplay("travol","inline");
		changeDisplay("stodiv","none");
		
        //alert(document.forms[0].compute.value);
       
       	
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






