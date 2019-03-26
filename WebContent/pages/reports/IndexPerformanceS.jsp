<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

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
	<html:errors/>
	 <html:base/>
		<link href="../StyleSheetM.css" rel="stylesheet" type="text/css">
		<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
		<script language="javascript" src="box_ex.js"></script>
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
	<script type='text/javascript' src='/Stockpile/dwr/interface/MoveTable.js'></script>
	<script type='text/javascript' src='/Stockpile/dwr/engine.js'></script>
	<script type='text/javascript' src='/Stockpile/dwr/util.js'></script>

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
			
	</head>  	
	<body onload="initialize()" > 
	
	<table width="100%" height="100%" >

<tr>
<td align="left" valign="top" bgcolor="#CCddee">

<DIV id="leftCol" style="DISPLAY: none; width:160; height:100%;">
<%@ include file="../tree3.jsp" %>
</div>
<IMG id="HideHandle" src="../openImage.gif" style="CURSOR: hand; position:absolute;" onclick='hideLeftCol("leftCol");' src="fold.gif" width="10" height="25">
</td>
<td align="left" valign="top">

	
		<div id="title" >
			  <table width="1000" cellspacing="4" cellpadding="2"  >
        			<tr>
		    	       	<td  class="subHeader" width="865"  align="center" colspan="4" nowrap="nowrap">
		        	  			<font size="3" face="Verdana">
		          					<b><bean:message key="IndexPerformance.title"/></b>
		          				</font>
			         	</td> 
		          </tr>
		 </table>
	</div>
	<%
		String ajax2="no";
		try{
			ajax2=request.getParameter("ajax2");
		}catch (Exception e) {
			// TODO: handle exception
		//	Logging.error(" Error :"+e.getMessage());
		}
        String sdate=request.getParameter("date");
       	String excel = "./FileDownload.jsp?type=12&filename=IndexPerformance.xls";
       	String xml = "./FileDownloadXmlNew.jsp?type=12&filename=IndexPerformance.xml";
       	String path_pdf = "./FileDownload_Pdf.jsp?type=12&filename=IndexPerformance.pdf";
    	String str_url = "./EmailReport.jsp?switch_type=12&cas=12&rname=IndexPerformance.xls";
       	session.setAttribute("from",sdate);
	%>
	     <html:form action="/indexPerformance"  >
	     <jsp:setProperty name="IndexPerformanceBean" property="userid1" value='<%=session.getAttribute("userid")%>'/>
	  <logic:equal value="yes" property="hiddenVar" name="IndexPerformanceBean">       
	   <logic:notEmpty name="IndexPerformanceBean" property="index_arraylist" >
	   <!-- printer friendly -->
	   	    <table id="strutslinks" cellpadding="0" cellspacing="0" align="right">
    		  <tr>
					 <td  nowrap="nowrap" align="right">
	 					  	<font face="Verdana" color="blue" size="1">
					  			<a href="javascript:PrintThisPage();">
					  				<bean:message key="IndexPerformance.printerf"/>
					  	  	    </a>
					  	  	    &nbsp;&nbsp;
					  	  	  </font>
					 </td>
					<!-- for excel link -->
	   			
				  	<td nowrap="nowrap" align="center">
					  	<font face="Verdana" color="blue" size="1">
								<a href="<%= excel %>"><bean:message key="IndexPerformance.downloade"/></a>&nbsp;&nbsp;
						</font>
		 			</td>					
		 		</font>
		 		<td nowrap="nowrap" align="center">
					  	<font face="Verdana" color="blue" size="1">
								<a href="<%= xml %>">Export to Xml</a>&nbsp;&nbsp;
						</font>
		 			</td>
				<font size="1">			
					<td nowrap="nowrap" align="left">
						<font face="Verdana" color="blue" size="1">
							<a href="<%= str_url %>"><bean:message key="IndexPerformance.emailr"/></a>&nbsp;&nbsp;
						</font>
					 </td>
					 <td width="128" nowrap="nowrap" align="center"><font size="1"><a href=<%= path_pdf %>><bean:message key="LatestDivisor.pdfr" /></a></font></td>
				</font>
			</tr>
		</table>
		</logic:notEmpty>
	</logic:equal>	
	<table id="ajaxlinks" cellpadding="0" cellspacing="0" align="right" style="display: none">
    		  <tr>
					 <td  nowrap="nowrap" align="right">
	 					  	<font face="Verdana" color="blue" size="1">
					  			<a href="javascript:PrintThisPage();">
					  				<bean:message key="IndexPerformance.printerf"/>
					  	  	    </a>
					  	  	    &nbsp;&nbsp;
					  	  	  </font>
					 </td>
					<!-- for excel link -->
	   			
				  	<td nowrap="nowrap" align="center">
					  	<font face="Verdana" color="blue" size="1">
								<a href="<%= excel %>"><bean:message key="IndexPerformance.downloade"/></a>&nbsp;&nbsp;
						</font>
		 			</td>					
		 		</font>
		 		<td nowrap="nowrap" align="center">
					  	<font face="Verdana" color="blue" size="1">
								<a href="<%= xml %>">Export to Xml</a>&nbsp;&nbsp;
						</font>
		 			</td>
				<font size="1">			
					<td nowrap="nowrap" align="left">
						<font face="Verdana" color="blue" size="1">
							<a href="<%= str_url %>"><bean:message key="IndexPerformance.emailr"/></a>&nbsp;&nbsp;
						</font>
					 </td>
					 <td width="128" nowrap="nowrap" align="center"><font size="1"><a href=<%= path_pdf %>><bean:message key="LatestDivisor.pdfr" /></a></font></td>
				</font>
			</tr>
		</table>
	<br>

	 	<table width="656" >
         		<tr>
         			<td width="365" align="right" nowrap="nowrap">
         				<font face="Verdana" size="2">
							 <bean:message key="IndexPerformance.SelDate"/>:
					 	</font>
				    </td>
			                    <td width="89" nowrap="nowrap">
					 <html:text property="date"	 readonly="true"  size="10"/>
				    </td>
				    <td width="96" nowrap="nowrap">
                        <input onclick="c2.popup('date');" type="button" value="...">
                    </td>
                    <%	if(ajax2.equals("yes")){
	  				%>
             	    <td width="100" nowrap="nowrap">
	   	               <html:hidden property="hiddenVar" name="IndexPerformanceBean"/>
				  	   <html:submit property="b1"  onclick="return go()"  > <bean:message key="Reports.View"/> </html:submit>
			   	    </td>
			   	    <% }else{
					%>
			   	    <td width="100" nowrap="nowrap">
						<INPUT type="Button" name="AjaxView" value="AjaxView" onclick="indexPerformance()"/>
					</td>	
					<% } %>
			</tr>
   	    </table>
   	    
   	    <table align="center" border="0" width="600" height="300"  cellspacing="0" cellpadding="0" class="sortable">
    		     <!--  <tr>
        			  <td id="selectdate" class="gridStyle-message" align="center" valign="middle">
            			  <p style="margin-left: 9">
            				  	<bean:message key="IndexPerformance.selectdata" /> 
            			  </p>
		        	  </td>
	    	     </tr>
	    	     -->
	    	     <tr>
		         	 <td id="nodata" bgcolor="#84AADE" align="center" valign="middle" nowrap="nowrap" style="display:none">
		         				<p style="margin-left: 9">
		         					<b><font face="Verdana" color="blue" size="5">
     									<bean:message key="IndexCompareOHCL.ndata" />
     								</font></b>
     							</p>
     				</td>
     			</tr>
   	    </table>
		<p></p>
		   		     
    <!-- ========================== for ajax ======================================= -->
 	 <div id="Ajaxcontentstart" >
	  	 <table class ="sorted"  ID="sortTable" style="display:none"
			border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#EFEFEF">    
          <!--  <table border="1"> -->      
				<thead>
				<tr>				
					<th class="gridStyle-header">Index Name</th>
					<th class="gridStyle-header">1 Month(In %)</th>
					<th class="gridStyle-header">3 Months(In %)</th>
					<th class="gridStyle-header">6 Months(In %)</th>	
					<th class="gridStyle-header">1 Year(In %)</th>			
				</tr>
				</thead>						
				<tbody id="indexMovingTable" bgcolor="#DEE3EF"> </tbody>								
		</table>
	</div>	
<!-- ========================== for ajax ======================================= -->		

	
 		<logic:notEqual value="yes" property="hiddenVar" name="IndexPerformanceBean">
    	    <table align="center" border="0" width="600" height="300"  cellspacing="0" cellpadding="0" class="sortable">
    		      <tr>
        			  <td id="selectdate" bgcolor="#84AADE" align="center" valign="middle">
            			  <p style="margin-left: 9">
            				  <b><font face="Verdana" color="blue" size="5">
            				  	<bean:message key="IndexPerformance.selectdata" /> 
            				  	</font></b>
            			  </p>
		        	  </td>
	    	     </tr>
   	        </table>
       </logic:notEqual>
     
       <br><br>
       <div id="table" >
		   <logic:equal value="yes" property="hiddenVar" name="IndexPerformanceBean">       
		   		<logic:empty name="IndexPerformanceBean" property="index_arraylist" >
				   <table id = "noStrutsData" border="0" width="600" height="300" cellpadding="0" cellspacing="0" align="center" class="sortable">	
    		 			<tr></tr><tr>
		         			<td  bgcolor="#84AADE" align="center" valign="middle" nowrap="nowrap">
		         				<p style="margin-left: 9">
		         					<b><font face="Verdana" color="blue" size="5">
     									<bean:message key="IndexCompareOHCL.ndata" />
     								</font></b>
     							</p>
     						</td>
     					</tr>
     				</table>
        	</logic:empty>
        	
  	<logic:notEmpty name="IndexPerformanceBean" property="index_arraylist" >	
	   <table border="0" width="800"  cellpadding="2" cellspacing="0"  align="center" id="tabular" >
	    		  <tr>
					<td>
	        <table class="sorted" ID="sortTabletable" 
			border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#EFEFEF">
				<thead>
					<tr>
			<!-- <table border="1" width="0%" align="center" cellpadding="2" cellspacing="0" id="t1" class="sortable" bordercolor="#EFEFEF" >
				<tr> -->     
					<th   align="left" valign="middle" nowrap="nowrap" id="indexname" class="gridStyle-header" >
						<span><b><bean:message key="indexcompose.indexname"/></b></span></a>
					</th>
					<th align="right" valign="middle" nowrap="nowrap" id="montho" class="gridStyle-header" >
						<span><b><bean:message key="IndexPerformance.montho"/></b></span></a>
			         </th>
        		     <th  align="right" valign="middle" nowrap="nowrap" id="montht" class="gridStyle-header" >
             			<span><b><bean:message key="IndexPerformance.montht"/></b></span></a>
				     </th>
	    		     <th   align="right" valign="middle" nowrap="nowrap" id="months" class="gridStyle-header" >
	        	    	 <span><b><bean:message key="IndexPerformance.months"/></b></span></a>
		    	 	</th>
        		    <th   align="right" valign="middle" nowrap="nowrap" id="year" class="gridStyle-header" >
            			<span><b><bean:message key="IndexPerformance.year"/></b></span></a>
		     		</th>
			   </tr>
		</thead>
		<tbody>
       <%
 				int i=1;
 				String bcolor="";
 			%>
       <logic:iterate id="ia" name="IndexPerformanceBean" property="index_arraylist">
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
           <td align="left"  nowrap="nowrap" axis="sstring" headers="indexname"
           			title='<bean:write name="ia" property="indexName"/>'>
            	<font face="Verdana" size="2" color="blue">
          			<a href='./IndexComposeS.jsp?index=<bean:write name="ia" property="indexId"/>&compute=yes&ajax1=yes'>
          			<bean:write name="ia" property="indexName"/></a>
          		</font> 
	  		<td  align="right" valign="middle" nowrap="nowrap"  axis="number" headers="montho"   
	  		title='<bean:write name="ia" property="month1"/>'>
		  		<font face="Verdana" color="blue" size="2">
  				 	<bean:write name="ia" property="month1"/>		 
  			 	</font>
  			</td>
			<td  align="right" valign="middle" nowrap="nowrap"  axis="number" headers="montht"   
	  		title='<bean:write name="ia" property="month3"/>'>  			 	
				<font face="Verdana" color="blue" size="2">
     				<bean:write name="ia" property="month3"/>
	     		</font>
			</td>
			<td  align="right" valign="middle" nowrap="nowrap"  axis="number" headers="months"   
	  		title='<bean:write name="ia" property="month6"/>'>  			 	     			
				<font face="Verdana" color="blue" size="2">
      				<bean:write name="ia" property="month6"/>   		    		 	
      			</font>
      		</td>
	      	<td  align="right" valign="middle" nowrap="nowrap"  axis="number" headers="year"   
	  		title='<bean:write name="ia" property="month12"/>'>  			 	     
		      	<font face="Verdana" color="blue" size="2">
    	  			<bean:write name="ia" property="month12"/>
      			</font>
    		</td>
	     </tr>
	     <% i=i+1; %>
    </logic:iterate>
    </tbody>
  </table>
  </td>
  </tr>
  </table>
    </logic:notEmpty>
  <jsp:setProperty name="IndexPerformanceBean" property="hiddenVar" value="no"/>
</logic:equal>
</div>
</html:form>

</td>
</tr>
</table>

</body>

<script language="JavaScript">
 var Button;
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
	if((document.forms[0].date.value)=="")
	document.forms[0].date.value= td+ s + mnth + s + yr;
	initSort();
}	

function popprinter(url)
{
	newwindow=window.open(url,'name','height=500,width=900,scrollbars=yes,left=80,top=130,menubar=yes,status=yes');
	if (window.focus) {newwindow.focus()}
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
	var objTo=document.forms[0].date;
	if(document.forms[0].date.value==""){
		alert("Select Date is required");
		document.forms[0].hiddenVar.value="no";
	}else{
		document.forms[0].hiddenVar.value="yes";
	}
	if((checkdatecurrent(objTo))==false)	
	{
	alert("ToDate should be Less Than CurrentDate.");
	objTo.focus();
	objTo.select();
	return false;
	}
	else return true;
}
function PrintThisPage() 
{ 
 		var dt=document.forms[0].date.value;
  		var ttle =document.getElementById('title').innerHTML;
   		//var tble = document.getElementById('table').innerHTML;
   		var winprint=window.open("","",""); 
      	winprint.document.open(); 
       	winprint.document.write(ttle);
       	winprint.document.write('<html><body onload="self.print()">');
      	winprint.document.write('<br>');
      	winprint.document.write('&nbsp;&nbsp;&nbsp;'); 
      	winprint.document.write(' <bean:message key="corporate.Fdate" />'); 
      	winprint.document.write('&nbsp;&nbsp;');
       	winprint.document.write(dt); 
	   	winprint.document.write('<br><br><br>');
	   	if(Button=="AjaxButton"){		
   			var sWinHTML = document.getElementById('Ajaxcontentstart').innerHTML;    
   		}
   		else{ 
   			var sWinHTML = document.getElementById('table').innerHTML; 
   		}
       	winprint.document.write(sWinHTML); 
       	//winprint.document.write(tble);
       	winprint.document.write('</body></html>'); 
       	winprint.document.close(); 
       	winprint.focus(); 
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

 /*===================================== For DWR ========================================================================*/
function indexPerformance() {
  DWRUtil.useLoadingMessage();	    
  var date = DWRUtil.getValue("date");     
  MoveTable.getIndexPerformance(fillTable,date);
}

	var indexName = function(IndexPerformance) { 		
		var inurl = "./IndexComposeS.jsp?index="+IndexPerformance.indexId+"&compute=yes&ajax1=no";
		return '<a href='+inurl+'><font face="Verdana" color="blue" size="2">'+IndexPerformance.indexName+'</font></a>'};
	var month1 = function(IndexPerformance) { return '<font face="Verdana" color="blue" size="2">'+IndexPerformance.month1+'</font>'}; // if we return to using dates, add .toLocaleDateString()
	var month3 = function(IndexPerformance) { return '<font face="Verdana" color="blue" size="2">'+IndexPerformance.month3+'</font>' };
	var month6 = function(IndexPerformance) { return '<font face="Verdana" color="blue" size="2">'+IndexPerformance.month6+'</font>'};
	var month12 = function(IndexPerformance) { return '<font face="Verdana" color="blue" size="2">'+IndexPerformance.month12+'</font>'};
	//alert(stockId);
function fillTable(indexMovingTable) {
  /*changeDisplay("indextable","inline");
  changeDisplay("selectdate","none");	
  DWRUtil.removeAllRows("indexMovingTable");alert("");
  DWRUtil.addRows("indexMovingTable",indexMovingTable, [ indexName, month1, month3, month6, month12 ]);
  //alert("I got it right");
  */
  var a = new Array();
	  a = indexMovingTable;   
 if(a.length){
   	  //alert("");
   	  Button = "AjaxButton";
   	  changeDisplay("selectdate","none");
 	  changeDisplay("nodata","none");
 	  changeDisplay("noStrutsData","none");
 	  changeDisplay("sortTabletable","none");
 	  changeDisplay("strutslinks","none");
 	  changeDisplay("ajaxlinks","inline");
 	  changeDisplay("sortTable","inline"); 	  	  	
	  DWRUtil.removeAllRows("indexMovingTable");
	  DWRUtil.addRows("indexMovingTable",indexMovingTable, [ indexName, month1, month3, month6, month12 ]);
 }
 else{ 
 	 changeDisplay("sortTable","none");
 	 changeDisplay("sortTabletable","none");
	 changeDisplay("nodata","inline");
	 changeDisplay("noStrutsData","none");
	 changeDisplay("selectdate","none");
	 changeDisplay("strutslinks","none");
 	 changeDisplay("ajaxlinks","none");
 }  
}

</script>
</html:html>
