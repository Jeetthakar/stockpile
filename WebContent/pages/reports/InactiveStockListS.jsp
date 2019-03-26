<%@ page import = "java.io.PrintWriter" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "java.text.ParseException" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.Locale,java.util.*" %>
<%@ page import = "harrier.income.com.report.*" %>
<%@page import="org.apache.log4j.Logger"%>
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
					
			LogonForm form = null;
			if(request.isRequestedSessionIdValid())	{	
				form = (LogonForm)session.getAttribute("user");
			}
			if(form==null ||(!request.isRequestedSessionIdValid())){
				response.sendRedirect("../userlogintemp.jsp");
			}
		//	Logger Logging = Logger.getLogger("InactiveStockListS.jsp");
%>
		
		
		
<html:html>
	<head>
		 <html:base/>
		<link href="./StyleSheet.css" rel="stylesheet" type="text/css">
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
	<body onload="initSort()" >
			<html:form action="inactiveStockList" method="post">

	  <link href="./StyleSheet.css" rel="stylesheet" type="text/css"> 
		<div id="title">
    	    <table width="1000" cellspacing="0" cellpadding="0" >
        			<tr>
		    	      	<td width="250" class="subHeader" nowrap="nowrap">
		        	  		&nbsp;
		          		</td>
			          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
			          			<font size="3" face="Verdana">
			          				<b><bean:message key="InactiveStockList.title" /></b>
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
					String excel="./FileDownload.jsp?type=17&filename=InactiveStockList.xls";
					String xml="./FileDownloadXmlNew.jsp?type=17&filename=InactiveStockList.xml";
					String path_pdf="./FileDownload_Pdf.jsp?type=17&filename=InactiveStock.pdf";
					String str_url="./EmailReport.jsp?switch_type=17&cas=17&rname=InactiveStockList.xls";					
				%>
						<!-- Table for Ajax links -->
	 				<table id = "AjaxLinks" align="right" style="display: none;">
					<td> 
					<a href="javascript:PrintThisPage();" ><font size="1" face="Verdana" color="blue"><bean:message key="LatestDivisor.printerf" /></font></a> 
				   
				    <a href="<%=excel%>" ><font size="1" face="Verdana" color="blue" > <bean:message key="LatestDivisor.downloade" /></font></a>  
				     <a href="<%=xml%>" ><font size="1" face="Verdana" color="blue" > Export to Xml</font></a>    
				    <a href="<%= str_url %>" > <font size="1" face="Verdana" color="blue"> <bean:message key="LatestDivisor.emailr" /></font></a> 
				    <a href="<%=path_pdf%>" ><font size="1" face="Verdana" color="blue" ><bean:message key="LatestDivisor.pdfr" /></font></a>   
		    		</td> 	
		    		</table>		    		
		    		
	   				<logic:notEmpty name="InactiveStockListBean" property="tableData">
	   					<!-- Table for Struts links -->				
	   				<table id ="StrutsLinks" align="right">
					<td> 
					<a href="javascript:PrintThisPage();" ><font size="1" face="Verdana" color="blue"><bean:message key="LatestDivisor.printerf" /></font></a> 
				   
				    <a href="<%=excel%>" ><font size="1" face="Verdana" color="blue" > <bean:message key="LatestDivisor.downloade" /></font></a>  
				     <a href="<%=xml%>" ><font size="1" face="Verdana" color="blue" > Export to Xml</font></a>    
				    <a href="<%= str_url %>" > <font size="1" face="Verdana" color="blue"> <bean:message key="LatestDivisor.emailr" /></font></a> 
				    <a href="<%=path_pdf%>" ><font size="1" face="Verdana" color="blue" ><bean:message key="LatestDivisor.pdfr" /></font></a>   
		    		</td> 	
		    		</table>	
		    		</logic:notEmpty>
	   <br><br>
			<html:hidden property="indexName"  name="InactiveStockListBean"/>
			<html:hidden property="hiddenVar" name="InactiveStockListBean" value="no" />
				<table width="70%" align="center">
				  	<tr>
						<td width="20%" nowrap="nowrap" align="left" height="30">
					       	<font size="2" face="Verdana" >
						         <bean:message key="StockList.Exchange" /> :
					        </font>
					     </td>
						<td width="40%" nowrap="nowrap" align="left" height="30">       
							<html:select property="d1" name="InactiveStockListBean" onchange="test1()">
								<html:optionsCollection property="vector_indexlist" name="InactiveStockListBean"/>
							</html:select>
						</td>
						<%	if(ajax1.equals("yes")){
	  					%>
						<td width="50" nowrap="nowrap" align="left" height="30">
							<html:submit property="b1" onclick="go()"> <bean:message key="Reports.View"/> </html:submit>
						</td>
						<% }else{
						%>
						<td width="50" nowrap="nowrap" align="left" height="30">
							<INPUT type="Button" name="view" value="View" onclick="getInactiveStockList()"/>
						</td>
						<% } %>
				    </tr>
				</table>
				<p></p>	
				
<!-- ============================================ For Ajax ================================================== -->	
	<div id="ajaxcontentstart">
	<table width="80%" class ="sorted"  ID="sortTable"
			border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#EFEFEF" style="display:none">    
          <!--  <table border="1"> -->      
				<thead >
				<tr>				
					<th width="80">Stock Name</th>
					<th width="130">Outstanding Shares</th>
					<th width="80">Face Value</th>																						
					<th width="50">Date</th>
				</tr>
				</thead>						
				<tbody id="indexMovingTable"> </tbody>								
	</table>
	</div>  
	<table border="0" class="gridStyle" align="center" width="600" height="222">
        	 			<!--  <tr>
			        		  <td id = "selectExchangeMessage" class="gridStyle-message" align="center" valign="middle">
    		    	    		  <b><bean:message key="CompanyWiseWeight.selmessage" /> 
    		    	    		  </b>
	        			      </td>
		    		      </tr>
		    		     --> 
		    		      <tr>
			    		      <td id = "nodata" class="gridStyle-message" align="center" valign="middle" style="display: none;">
											<b><bean:message key="StockPerformance.selDataAvai"/></b>	
			        		  </td>
				    	 </tr>
    </table> 		
		  
<!-- ============================================ For Ajax ================================================== -->				
			<script> </script>	
			 	<logic:notEqual value="yes" property="hiddenVar" name="InactiveStockListBean"> 
					<table border="0" class="gridStyle" align="center" width="600" height="222">
        	 			 <tr>
			        		  <td id = "selectExchangeMessage" class="gridStyle-message" align="center" valign="middle">
    		    	    		  <b><bean:message key="CompanyWiseWeight.selmessage" /> 
    		    	    		 </b>
	        			      </td>
		    		      </tr>
    			    </table> 
				</logic:notEqual>  
				<logic:equal value="yes"  property="hiddenVar" name="InactiveStockListBean" >
					<logic:empty   name="InactiveStockListBean" property="tableData">
							<table border="0" class="gridStyle" align="center" width="600" height="222">
        			 			 <tr>
			    		    		  <td id = "noStrutsData" class="gridStyle-message" align="center" valign="middle">
											<b><bean:message key="StockPerformance.selDataAvai"/></b>	
			        			      </td>
				    		      </tr>
    					    </table> 
				    </logic:empty>
		    		<logic:notEmpty name="InactiveStockListBean" property="tableData">	
		    		  		<div id="table">
		    		  			<table border="0" width="800"  cellpadding="2" cellspacing="0"  align="center" id="tabular" >
			    				  <tr>
									<td>
	 									<table class="sorted"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#EFEFEF">
				<thead>
					<tr>
	 									<!-- <table border="1" width="80%" align="center" cellpadding="2" cellspacing="0" id="t1" class="sortable" >
											<tr> -->     
												<th width="10%" bgcolor="#EFEFEF" align="center" id="Name" class="gridStyle-header">
										           <span><b><bean:message key="StockWisePe.Name"/></b></span>
												</th>
												<th width="10%" bgcolor="#EFEFEF" align="center" id="Shares" class="gridStyle-header">
													<span><b><bean:message key="StockList.Shares"/></b></span>
												</th>
												<th width="10%" bgcolor="#EFEFEF" align="center" id="faceValue" class="gridStyle-header">
													<span><b><bean:message key="stockmaster.faceValue"/></b></span>
												</th>
												<th width="10%" bgcolor="#EFEFEF" align="center" id="Date" class="gridStyle-header">
													<span><b><bean:message key="corporate.Date"/></b></span>
												</th>
										  </tr>
										  </thead>
											<tbody>
										  <logic:iterate id="td"  name="InactiveStockListBean" property="tableData">
											<tr>
							    		   		<td width="10%" nowrap="nowrap" align="left" bgcolor="white"  class="gridStyle-odd" axis="sstring" headers="Name"
           			title='<bean:write name="td" property="stockName"/>'>
					    						   	<font face="Verdana" color="blue" size="2">										
							    				   		<bean:write name="td" property="stockName"/>
							    				   	</font>
							    				 </td>
					    				   		<td width="10%" nowrap="nowrap" align="left" bgcolor="white" class="gridStyle-odd" axis="number" headers="Shares"
           			title='<bean:write name="td" property="outstandingShares"/>'>
							    				   	<font face="Verdana" color="blue" size="2">										
							    				   		<bean:write name="td" property="outstandingShares"/>
							    				   	</font>
					    						 </td>
							    		   		<td width="10%" nowrap="nowrap" align="left" bgcolor="white" class="gridStyle-odd" axis="number" headers="faceValue"
           			title='<bean:write name="td" property="faceValue"/>'>
							    				   	<font face="Verdana" color="blue" size="2">										
							    				   		<bean:write name="td" property="faceValue"/>
							    				   	</font>
							    				 </td>
							    		   		<td width="10%" nowrap="nowrap" align="left" bgcolor="white" class="gridStyle-odd" axis="date" headers="Date"
           			title='<bean:write name="td" property="date"/>'>
					    						   	<font face="Verdana" color="blue" size="2">										
					    						   		<bean:write name="td" property="date"/>
					    						   	</font>
							    				 </td>
											</tr>	
									      </logic:iterate>
									           </tbody> 
								</table>
							</div>	
					</logic:notEmpty>
				 </td>
		 	  </tr>
		  </table>
	  </logic:equal>
    </html:form>
  </body>


<script  language="javascript">
var Button;
function test1(){
	document.forms[0].hiddenVar.value="no"
	document.forms[0].submit();
}
function go(){

	document.forms[0].hiddenVar.value="yes"
	document.forms[0].submit();
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

function popprinter(url)
{
	newwindow=window.open(url,'name','height=500,width=900,scrollbars=yes,left=80,top=130,menubar=yes,status=yes');
	if (window.focus) {newwindow.focus()}
}
	function PrintThisPage() 
	{ 
  		var ttle =document.getElementById('title').innerHTML;
   		var winprint=window.open("","",""); 
      	winprint.document.open(); 
       	winprint.document.write('<br><b>');
      	
	   	if(Button=="AjaxButton"){
       		alert("inside if");
 			winprint.document.write('<br>');
       	  	var moreDet = DWRUtil.getText("d1");
   			var sWinHTML = document.getElementById('ajaxcontentstart').innerHTML; 
   			winprint.document.write("<font size=3>Exchange Name :"+moreDet+"</font>");
   		}
   		else{ 
   			alert("inside else");
   			var sWinHTML = document.getElementById('table').innerHTML;
   			var moreDet = document.forms[0].indexName.value;
   			winprint.document.write('<bean:message key="defineIndex7"/>');
      		winprint.document.write('&nbsp;&nbsp;');
   			winprint.document.write(moreDet);
   		}   
	   	
	   	winprint.document.write('</b><br>');
	   	alert("after if else");
       	winprint.document.write(sWinHTML); 
       	winprint.document.close(); 
       	winprint.focus(); 
	}
/*============================================== for dwr =========================================================== */	
function getInactiveStockList(){
  DWRUtil.useLoadingMessage();
  var exchange = DWRUtil.getValue("d1");  
  MoveTable.getInactiveStockList(fillTable, exchange );  
}
	var stockName = function(StockDetails) { return '<font face="Verdana" color="blue" size="2">'+StockDetails.stockName+'</font>' };			
	var outstandingShares = function(StockDetails) { return '<font face="Verdana" color="blue" size="2">'+StockDetails.outstandingShares+'</font>'};				
	var faceValue = function(StockDetails) { return '<font face="Verdana" color="blue" size="2">'+StockDetails.faceValue+'</font>'};									
	var date = function(StockDetails) { return '<font face="Verdana" color="blue" size="2">'+StockDetails.date+'</font>'};				
	//alert(stockId);
function fillTable(indexMovingTable) { 
   var a = new Array();
	   a = indexMovingTable;   
 if(a.length){
   	   Button = "AjaxButton";
   	  changeDisplay("selectExchangeMessage","none");
 	  changeDisplay("nodata","none");
 	  changeDisplay("noStrutsData","none");
 	  changeDisplay("tabular","none");
 	  changeDisplay("sortTable","inline"); 
 	  changeDisplay("AjaxLinks","inline");	  	  	
 	  changeDisplay("StrutsLinks","none");
	  DWRUtil.removeAllRows("indexMovingTable");
      DWRUtil.addRows("indexMovingTable",indexMovingTable, [ stockName, outstandingShares, faceValue, date ]);
 }
 else{ 
 	 changeDisplay("sortTable","none");
 	 changeDisplay("StrutsLinks","none");
 	 changeDisplay("AjaxLinks","none");
 	 changeDisplay("tabular","none");
 	 changeDisplay("noStrutsData","none");
	 changeDisplay("nodata","inline");
	 changeDisplay("selectExchangeMessage","none");
 }
}

/*============================================== for dwr =========================================================== */	
	
</script>
</html:html>
