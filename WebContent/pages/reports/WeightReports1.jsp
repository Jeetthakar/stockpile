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
	<html:form action="/batchReportAction">
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
  		    
  		   
					<li><a href="#" onClick="compaweight()"><bean:message key="Company_Weightage" /></a></li>
				
					<li><a href="#" onClick="indwiseweight()"><bean:message key="Industry_Weightage" /></a></li>
					
					<li><a href="#" onClick="stockcontriweight()"><bean:message key="BatchReport.StockContriToChange" /></a></li>
					
					
					
	
			</ul>  
	   
	 <TABLE border="1" color="black" width="100%">
		<TR>
		<TD bgcolor="LavenderBlush" valign="top" width="100%" height="500">
	    
		
		
		    
		    
		<div id=comweight style="display:inline"> 
			
				    
		    	<table width="1000" cellspacing="0" cellpadding="0" >
        			<tr>
		          	<td width="250" class="subHeader" nowrap="nowrap">
		          		&nbsp;
		          	</td>
		          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          		<font size="3" face="Verdana">
		          			<b><bean:message key="CompanyWiseWeight.title" /></b>
		          		</font>
		         	</td> 
	          		</tr>
				</table> 
			<bean:define id="cw" name="batchReportBean" property="companyWeightage"/>
			<logic:empty property="companyWeightage" name="batchReportBean">
			
	  		  					<table border="0" align="left" width="631" height="222" cellspacing="0" cellpadding="0">
	  	   	 						<tr>
          							<td width="99"></td>
	  			   					<td class="gridStyle-message" align="center" valign="middle">
          							<p style="margin-left: 9"><bean:message key="IndexCompareOHCL.ndata" /></p>
          							</td>
        						</tr>
       							</table>
       		</logic:empty>
       		
       		<logic:notEmpty property="companyWeightage"  name="batchReportBean">
			
			
					<table  border="0" width="800"  cellpadding="2" cellspacing="0" align="center" id="tabular" >
		    		  <tr>
						<td>
							<table border="1" width="80%" align="center" cellpadding="2" cellspacing="0" id="t1" class="sortable" >
								<tr>     
									<td class="gridStyle-header" width="10%" bgcolor="#EFEFEF" align="center">
										<bean:message key="CompanyWiseWeight.compname" />								
									</td>
									<td class="gridStyle-header" width="10%" bgcolor="#EFEFEF" align="center">
										<bean:message key="IndexCompose.mcap" />					
									</td>
									<td class="gridStyle-header" width="10%" bgcolor="#EFEFEF" align="center">
										<bean:message key="IndexCompose.weight" />
									</td>
				    	    	</tr>
								<logic:iterate id="cw" property="companyWeightage"  name="batchReportBean">
								   <tr>
				    		   		<td class="gridStyle-odd" width="10%" nowrap="nowrap" align="left" bgcolor="#EFEFEF">
				    				   	<font face="Verdana" color="blue" size="2">
							  			 		<bean:write name="cw" property="company_name"/>		 			
										</font>
								  	</td>
	    	   						<td class="gridStyle-odd" width="10%" nowrap="nowrap" align="right" bgcolor="#EFEFEF">
    								   	<font face="Verdana" color="blue" size="2">
					  					 	<bean:write name="cw" property="mcap"/>		
										</font>
							  		</td>
								    <td class="gridStyle-odd" width="10%" nowrap="nowrap" align="right" bgcolor="#EFEFEF">
    								   	<font face="Verdana" color="blue" size="2">
								  			 	<bean:write name="cw" property="weightage"/>		 				  	
										</font>
						 			 </td>
								</tr>
							</logic:iterate>
							</table>
    	 				</td>
					</tr>
				</table>
			</logic:notEmpty>
					   
		</div>	
		
		
		
		<div id="indreport" style="display:none">
		
		<table width="1000" cellspacing="0" cellpadding="0" >
        		<tr>
		          	<td width="250" class="subHeader" nowrap="nowrap">
		          		&nbsp;
		          	</td>
		          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          		<font size="3" face="Verdana">
		          			<b><bean:message key="IndexWiseWeight.title" /></b>
		          		</font>
		         	</td> 
	          </tr>
		</table> 
		<bean:define id="try3" name="batchReportBean" property="indweighttable"/>
		<logic:empty property="indweighttable" name="batchReportBean">
	  		  					<table border="0" align="left" width="631" height="222" cellspacing="0" cellpadding="0">
	  	   	 						<tr>
          							<td width="99"></td>
	  			   					<td class="gridStyle-message" align="center" valign="middle">
          							<p style="margin-left: 9"><bean:message key="IndexCompareOHCL.ndata" /></p>
          							</td>
        						</tr>
       							</table>
       	</logic:empty>
       		
       		<logic:notEmpty property="indweighttable"  name="batchReportBean">
		
			<table border="0" width="800"  cellpadding="2" cellspacing="0"  align="center" id="tabular" >
	    		  <tr>
					<td>
       			<table class="sorted" ID="sortTable" border="1" align="center" cellpadding="3" cellspacing="0" bgcolor="#EFEFEF">
				<thead>
					<tr>
       			<!-- <table border="1" width="80%" align="center" cellpadding="2" cellspacing="0" id="t1" class="sortable" bordercolor="white">      
             		<tr> -->
             			<th  class="gridStyle-header" id="Name" width="30%"  align="center" >
                    	<span><b><bean:message key="IndexWiseWeight.Name" /></b></span>
       					</th>
         			 	<th width="20%" class="gridStyle-header" id="Market" align="center" >
         			 	<span><b><bean:message key="IndexWiseWeight.Market" /></b></span> 
                        cap.
                      	</th>
         				<th  class="gridStyle-header" id="Weightage" width="20%"  align="center"  nowrap="nowrap">
         				<span><b><bean:message key="IndexWiseWeight.Weightage" />(%) </b></span>
                       	</th>
            	   </tr>
            	</thead>
			 <tbody>
           		   <logic:iterate id="try3" property="indweighttable" name="batchReportBean">
            	   <tr>
            			<td class="gridStyle-odd" width="30%" align="left" height="37" bgcolor="#EFEFEF" nowrap="nowrap" axis="sstring" headers="Name"
           			title='<bean:write name="try3" property="industryname"/>'>
            				<font face="Verdana" size="2" color="blue">
            				<bean:write name="try3" property="industryname"/></font>
       					</td>
       					<td class="gridStyle-odd" width="20%" align="right" bgcolor="#EFEFEF" nowrap="nowrap" axis="number" headers="Market"
           			title='<bean:write name="try3" property="marketcap"/>'>
              				<font face="Verdana" size="2" color="blue">
              				<bean:write name="try3" property="marketcap"/></font>
            			</td>
            			<td class="gridStyle-odd" width="20%" align="right" bgcolor="#EFEFEF" nowrap="nowrap" axis="number" headers="Weightage"
           			title='<bean:write name="try3" property="weightage"/>'>
              				<font face="Verdana" size="2" color="blue">
              				<bean:write name="try3" property="weightage"/>%</font>
           				</td>
            	   </tr>
       			   </logic:iterate>
       		 </tbody>  		
        <!--     	  
		<tr>
 			<td class="gridStyle-header" width="30%"  align="left" height="37" nowrap="nowrap"><font face="Verdana" size="2" color="blue">
 				<bean:message key="StockPerformance.Total" /></font></td>
			<td class="gridStyle-header" width="20%" align="right" nowrap="nowrap" >
				<font face="Verdana" size="2" color="blue">
				<bean:write name="batchReportBean" property="val"/></font></td>
			<td class="gridStyle-header" width="20%" align="right" nowrap="nowrap" >
				<font face="Verdana" size="2" color="blue">
				<bean:write name="batchReportBean" property="total"/>%</font>
			</td>
	
		</tr>
		-->
			</table> 
            	  
        	</td></tr>
         </table>   	
        </logic:notEmpty>
		
		
		</div>	
		
		
		
		<div id="stockcontri" style="display:none">
		
			<p align="center">
       	
        	<table width="1000" cellspacing="0" cellpadding="0" >
        		<tr>
		          	<td width="250" class="subHeader" nowrap="nowrap">
		          		&nbsp;
		          	</td>
		          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          			<font size="3" face="Verdana">
		          				<b><bean:message key="StockContritoIndexChange.title" /></b>
		          			</font>
		         	</td> 
	            </tr>
			</table> 
         	<bean:define id="try4" name="batchReportBean" property="stockcotriIndexchange"/>
       		<logic:empty property="stockcotriIndexchange" name="batchReportBean">
	  		  					<table border="0" align="left" width="631" height="222" cellspacing="0" cellpadding="0">
	  	   	 						<tr>
          							<td width="99"></td>
	  			   					<td class="gridStyle-message" align="center" valign="middle">
          							<p style="margin-left: 9"><bean:message key="IndexCompareOHCL.ndata" /></p>
          							</td>
        						</tr>
       							</table>
       	</logic:empty>
       		
       		<logic:notEmpty property="stockcotriIndexchange"  name="batchReportBean">
		
       
       		<table border="1" width="70%" align="center" cellpadding="2" cellspacing="0" id="t1" class="sortable">
          	<tr>
            	<td class="gridStyle-header" width="25%"  align="center" valign="middle" bgcolor="#EFEFEF" nowrap="nowrap"><bean:message key="StockWisePe.Name"/>
            	</td>
            	<td  class="gridStyle-header" width="15%"  align="center" valign="middle" bgcolor="#EFEFEF" nowrap="nowrap"><bean:message key="StockContritoIndexChange.Index"/>
               	</td>
            	<td  class="gridStyle-header" width="15%"  align="center" valign="middle" bgcolor="#EFEFEF" nowrap="nowrap"><bean:message key="StockContritoIndexChange.Stock"/>
             	</td>
            	<td  class="gridStyle-header" width="15%"  align="center" valign="middle" bgcolor="#EFEFEF" nowrap="nowrap"><bean:message key="IndexWiseWeight.Weightage"/>
             	</td>
          	</tr>
        	<logic:iterate id="try4" property="stockcotriIndexchange" name="batchReportBean">
        	<tr>
            	<td class="gridStyle-odd" width="25%" align="left" height="37" bgcolor="#EFEFEF" >
             	<font face="Verdana" size="2" color="blue">
              	<bean:write name="try4" property="stockname"/>
               	</font>      
            	</td>
           		<td class="gridStyle-odd" width="15%"  align="right" bgcolor="#EFEFEF" nowrap="nowrap">
              	<font face="Verdana" size="2" color="blue">
              	<bean:write name="try4" property="indexmarket"/>
            	</font>
            	</td>
            	<td class="gridStyle-odd" width="15%"  align="right" bgcolor="#EFEFEF" nowrap="nowrap">
              	<font face="Verdana" size="2" color="blue">
              	<bean:write name="try4" property="stockmarket"/>
            	</font>
            	</td>
            	<td class="gridStyle-odd" width="15%"  align="right" bgcolor="#EFEFEF" nowrap="nowrap">
              	<font face="Verdana" size="2" color="blue">
             	<bean:write name="try4" property="weightage"/> 
            	</font>
            	</td>
         	</tr>
        	</logic:iterate>
      		</table>
       
      	</logic:notEmpty>  
 
		</div>
		
	</TD>
 	</TR>
 	</TABLE> 		
	
	
	
	
				
	 
</html:form>	

    
</body>

<script language="javascript">
var man1=document.getElementById("hiddenTable");
man1.style.display= "none"; 

var selectedtablink=""
var tcischecked=false



function initialize() {
	
	
}
function compaweight() {
		//alert("compaweight");
		changeDisplay("comweight","inline");
		changeDisplay("indreport","none");
		changeDisplay("stockcontri","none");
	
}
	

function indwiseweight(){
		//alert("indwiseweight");
		changeDisplay("indreport","inline");
		changeDisplay("comweight","none");
		changeDisplay("stockcontri","none");
	 	
}

function stockcontriweight(){
		//alert("stockcontriweight()");
		changeDisplay("stockcontri","inline");
		changeDisplay("indreport","none");
		changeDisplay("comweight","none");
		       	
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






