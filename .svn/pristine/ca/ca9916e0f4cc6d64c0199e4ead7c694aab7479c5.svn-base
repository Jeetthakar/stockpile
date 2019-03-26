<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" import="app.*"%>
<%@ page import="harrier.income.com.report.*" %>
<%@ page import = "java.io.PrintWriter" %><%@page import="org.apache.log4j.Logger"%>
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
					<script language="javascript">
						var c2 = new CodeThatCalendar(caldef2);
					</script>
					
				<script type="text/javascript" src="../Script/Event.js"></script>		
				<script type="text/javascript" src="../Script/SortedTable.js"></script>
				<script type='text/javascript' src='/Stockpile/dwr/interface/MoveTable.js'></script>
				<script type='text/javascript' src='/Stockpile/dwr/engine.js'></script>
				<script type='text/javascript' src='/Stockpile/dwr/util.js'></script>
				<script type='text/javascript' src='/Stockpile/dwr/interface/GraphMethods.js'></script>
				
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
	
		<html:form action="/indexDivisorAction">
			<%        if(request.isRequestedSessionIdValid()) {%>
				<jsp:setProperty name="IndexDivisorBean" property="userid1" value='<%=session.getAttribute("userid")%>'/>
				<jsp:setProperty name="IndexDivisorBean" property="roleId_div" value='<%=session.getAttribute("role_id").toString()%>'/>
		<%}
		%>
			<div id="PageTitle">
	
			<p align="left">  
	
			<table width="1000" cellspacing="0" cellpadding="0" > <!-- Table for Displaying Title -->
        		<tr>
		          	<td width="300" class="subHeader" nowrap="nowrap">
		          		&nbsp;
		          	</td>
		          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          			<font size="3" face="Verdana">
		          				<b><bean:message key="IndexDivisor.title" /></b>
		          			</font>
		         	</td>
	          	</tr>
			</table>
			</div>
	
		<%			
				String ajax1="no";
				try{
					ajax1=request.getParameter("ajax1");
				}catch (Exception e) {
				// TODO: handle exception
				//Logging.error(" Error :"+e.getMessage());
				}
   				String xml_path = "./FileDownloadXmlNew.jsp?&type=19&filename=IndexDivisor.xml";
		        String email_url = "./EmailReport.jsp?switch_type=19&cas=19&rname=IndexDivisor.xls";
                String pdf_path="./FileDownload_Pdf.jsp?type=19&filename=IndexDivisor.pdf";
                String temp_path = "./FileDownload.jsp?&type=19&filename=IndexDivisor.xls";
       	
		%>
		
		<logic:equal  value="yes" parameter="compute" >	
	       	<table id="strutslinks" align="right"> 
	       	<tr>       
	 			<td  valign="baseline">
	 				<a href="javascript:PrintThisPage();"> 
	 				<font face="Verdana" size="1" ><bean:message key="LatestDivisor.printerf" /></font>
	 				</a>
	 			</td>
		
				 	<!-- for excel and email links -->
	   			<%
	   				session.setAttribute("selectIndex",request.getParameter("selectIndex"));
	   				session.setAttribute("from",request.getParameter("from"));
	   				session.setAttribute("to",request.getParameter("to"));   				
				%>
		
		 		 <td  valign="baseline">
		 			<a href="<%= temp_path %>" > 
		 				<font face="Verdana" size="1" ><bean:message key="LatestDivisor.downloade" /></font>
		 			</a>
		 		</td>
		
		 		<td  valign="baseline">
			 							<a href="<%= xml_path %>" > 
		 								<font face="Verdana" size="1" >Export to Xml</font>
		 							</a>
		 		</td>
		 		
		 		<td  valign="baseline">
		 							<a href="<%= pdf_path %>" > 
		 								<font face="Verdana" size="1" ><bean:message key="LatestDivisor.pdfr" /></font>
		 							</a>
		 		</td>
		
		 		<td  valign="baseline">
		 					  	<a href="<%= email_url %>"> 
		 					  		<font face="Verdana" size="1" ><bean:message key="LatestDivisor.emailr"/></font>
		 					  	</a>
		 		</td>
		 	</tr>
		  </table>
	  </logic:equal>
	
	<table id="ajaxlinks" align="right" style="display: none">        
 		<tr>
 			<td  valign="baseline">
 				<a href="javascript:PrintThisPage();"> 
 				<font face="Verdana" size="1" ><bean:message key="LatestDivisor.printerf" /></font>
 				</a>
 			</td>
			 	<!-- for excel and email links -->
   			
	 		<td  valign="baseline">
	 							<a href="<%= temp_path%>" > 
	 								<font face="Verdana" size="1" ><bean:message key="LatestDivisor.downloade" /></font>
	 							</a>
	 		</td>
	 		<td  valign="baseline">
	 							<a href="<%= xml_path%>" > 
	 								<font face="Verdana" size="1" >Export to Xml</font>
	 							</a>
	 		 </td>
	 		 <td  valign="baseline">
	 							<a href="<%= pdf_path%>" > 
	 								<font face="Verdana" size="1" ><bean:message key="LatestDivisor.pdfr" /></font>
	 							</a>
	 		 </td>
	 		  <td  valign="baseline">
	 					  	<a href="<%= email_url%>"> 
	 					  		<font face="Verdana" size="1" ><bean:message key="LatestDivisor.emailr"/></font>
	 					  	</a>
	 		 </td>
		</tr>
	</table>
	
	<p align="left">
      <table border="0" width="935"  cellspacing="0" cellpadding="3"  height="30"> <!-- Selectbox for displaying the Index List -->
        <tr>
        	<td align="left" width="19" nowrap="nowrap">
        		&nbsp;
          	</td>
			
			<td align="right" width="121" nowrap="nowrap" >
        		<font size="2" color="black" face="Verdana">
        			<bean:message key="LatestDivisor.iname" />
        		</font>
        	</td>
        	
        	<td align="left" nowrap="nowrap" width="771">
	      		<html:select property="selectIndex" size="1" onchange="" styleId="sIE">
					<html:optionsCollection property="selectIndexCollection" name="IndexDivisorBean"/>
				</html:select>
            </td>          	
        </tr>
      </table>
          
      <table width="886" height="40">
     	<tr>
    		<td width="50" align="right" nowrap="nowrap">
        			
        	</td>
	  		<td align="left" nowrap="nowrap" width="113">
            	<font face="Verdana" size="2">
	 				<bean:message key="IndexDivisor.aispan" />
	 			</font>
          	</td>	
          	<td align="left" nowrap="nowrap" width="90">
            	<html:select property="avgSpan" size="1" styleId="sIE">
					<html:optionsCollection property="selectAvgSpan" name="IndexDivisorBean"/>
				</html:select >
            </td>	
            <td align="left" nowrap="nowrap" width="86">
            	<font face="Verdana" size="2">
	 				<bean:message key="IndexDivisor.schart" />
	 			</font>
          	</td>	
          	<td align="left" nowrap="nowrap" width="308">
            	<html:select property="selchart" size="1" onblur="return selectChart()" styleId="sIE" >
					<html:optionsCollection property="selectChart" name="IndexDivisorBean"/>
				</html:select >
            </td>
         </tr>		
	   </table>
	  
	   <table border="0" width="940"  cellspacing="0" cellpadding="3" >
        	<tr>
        		<td  width="101" align="right" nowrap="nowrap"> 
        		<!-- 	<html:checkbox property="check_mavg" onblur="return mavg()"/>&nbsp; -->
        		</td>
        		
        		<td nowrap="nowrap" align="left" width="145">	
			   		<font size="2" face="Verdana">
	  				<!-- 	<bean:message key="IndexDivisor.vmavg" /> 	 -->
	  				</font>
	  			</td>
	  			<td nowrap="nowrap" width="90"><font size="2" face="Verdana" >		
	              	<bean:message key="corporate.Fdate" />
	            </td> 
            	<td nowrap="nowrap" width="80">
	             	<html:text property="from" readonly="true" size="10"/>
	            </td>
	            <td  nowrap="nowrap" width="45">  
					<html:button property="shwFrom" value="..." onclick="c2.popup('from');"/>
	          	</td>
	          	<td nowrap="nowrap" width="83">
	           		<font size="2" face="Verdana">
	                	<bean:message key="corporate.Tdate" />
	                </font>
	            </td>
	            <td nowrap="nowrap" width="78">
	             	<html:text property="to" readonly="true" size="10"/>
	            </td>
	            <td  nowrap="nowrap" width="45" >
					<html:button property="shwTo" value="..." onclick="c2.popup('to');"/>
	          	</td>
	          	<td nowrap="nowrap" width="212"> 
	          	
		          	<%	if(ajax1.equals("yes")){
		  			%>
		               	<html:submit onclick="return go1();"  ><bean:message key="Reports.View" /></html:submit>&nbsp;&nbsp;
					<% }else{
					%>	
						<INPUT type="Button" name="View" value="AjaxView" onclick = "indexDivisor()"/>
					<% } %>
				</td>	
				<td width="4"></td>
	  		</tr>
		</table>
		<p></p>
	
     
     <logic:equal  value="yes" parameter="compute" >
	  		
	  		<bean:define id="details" name="IndexDivisorBean" property="tableData"/>
	  		<bean:size id="dataCount" name="IndexDivisorBean" property="tableData"/>
	  
	  <logic:equal value="0" name="dataCount" >
	  	<table border="0" align="left" width="631" height="222" cellspacing="0" cellpadding="0">
        	<tr>
	  				<td width="99"></td>
	  				<td id = "noStrutsData" bgcolor="#84AADE" align="center" valign="middle">
	  					<p style="margin-left: 9"><font face="Verdana" color="blue" size="5">
	  						<bean:message key="IndexCompareOHCL.ndata" /></p>
	  				</td>
  			</tr>
		</table>
     </logic:equal>
    
     <logic:notEqual value="0" name="dataCount">
       
	<!-- Charts -->
      <% String varchart=request.getParameter("selchart");
     		if(!(varchart.equals("0")))     	
     		{%>
          				<table  border="0" width="600" align="center" cellpadding="0" cellspacing="0">
        					<tr>
        					<td width="99"></td>
        						<td>
        							<%GraphMethodsPf objGM =ConnectInit.getGraphMethodsPf();
        							//	GraphMethodsPf objGM = new GraphMethodsPf();
        								String check_chart=request.getParameter("selchart");
        						 		objGM.getGraphChart1(session,new PrintWriter(out),"inddivisor");
        						 	 	String filename=objGM.getFilename();
     							  		//String graphURL=objGM.getGraphURL();  
     							  		String graphURL1= request.getContextPath() + "/servlet/DisplayChart?filename=" + filename; 
     							  	%>
    							   <img id="strutsgraph" src="<%= graphURL1 %>" width="500" height="270" border="0" >
    							</td>
	 						</tr>
        				</table>
        	<%}%>
        	
    	<div id="contentstart" > 
     		<table border="0" width="85%"  align="left" cellpadding="2" cellspacing="0" >
     	  		
	      	<tr>
	      		<td><DIV id="m1">
	   				<table border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#EFEFEF">
						<thead>
								<tr>	       
			            		    <th width="15%" nowrap="nowrap" align="center" id="trddate" class="gridStyle-header">
			            		    	<span><b><bean:message key="IndexDivisor.trddate" /></b></span>
			            		    </th>
			            		    <th width="15%" nowrap="nowrap" align="center" id="close" class="gridStyle-header">
			            		    	<span><b><bean:message key="IndexCompareOHCL.close" /></b></span>
			            		    </th>
			            		    <th width="15%" nowrap="nowrap" align="center" id="mcp" class="gridStyle-header">
			            		    	<span><b><bean:message key="IndexDivisor.mcp" /></b></span>
			            		    </th>
			            		    <th width="15%" nowrap="nowrap" align="center" id="divisor" class="gridStyle-header">
			            		    	<span><b><bean:message key="indcurrwise.divisor" /></b></span>
			            		    </th>
			            	    </tr>
			           </thead>
	           
						<tbody>
				           <%
			 				int i=1;
			 				String bcolor="";
			 				%>
					       <logic:iterate id="details" name="IndexDivisorBean" property="tableData">
				     			
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
			          				<td width="15%" nowrap="nowrap" align="center" axis="date" headers="trddate"
		           						title='<bean:write name="details" property="tradingDate"/>'>
			          					<font face="Verdana" color="blue" size="2">
			          						<bean:write name="details" property="tradingDate"/>
			          					</font>
			          				</td>
			          				<td width="15%" nowrap="nowrap" align="right" axis="number" headers="close"   
			  							title='<bean:write name="details" property="close"/>'>
			            		    	<font face="Verdana" color="blue" size="2">
			            		    		<bean:write name="details" property="close"/>
			            		    	</font>
			            		    </td>
			            		    <td width="15%" nowrap="nowrap" align="right" axis="number" headers="mcp"   
			  							title='<bean:write name="details" property="mcap"/>'>
			            		    	<font face="Verdana" color="blue" size="2">
			            		    		<bean:write name="details" property="mcap"/>
			            		    	</font>
			            		    </td>
			            		    <td width="15%" nowrap="nowrap" align="right" axis="number" headers="divisor"   
			  							title='<bean:write name="details" property="divisor"/>'>
			            		    	<font face="Verdana" color="blue" size="2">
			            		    		<bean:write name="details" property="divisor"/>
			            		    	</font>
			            		    </td>
					           	</tr>
					            <% i=i+1; %>
				          	 </logic:iterate>
				        </tbody>
          	 		</table> 
          	 		</DIV>
          	 	</td>
          	</tr>          	
         </table>
     </div>    	 
     </logic:notEqual>
     </logic:equal>
     
     <div>		
	 	<table id="hiddenTable" style = "Display : none" width="100%"  align="left">
	     	<tr>
				<td width="125" align="right">
					<font size="2" face="Verdana"><b>
    					<bean:message key="corporate.Fdate" />&nbsp;:</b>
        			</font>
   				</td>
   				<td>
   					<bean:write name="IndexDivisorBean" property="from"/>
   				</td> 
   			</tr>
			<tr>						
					<td width="125" align="right">
						<font size="2" face="Verdana"><b>
    						<bean:message key="corporate.Tdate" />&nbsp;:</b>
         				</font>
   					</td>
   					<td>
   						<bean:write name="IndexDivisorBean" property="to"/>
   					</td> 
			</tr>			
         </table>        
       </div>
     	<html:hidden property="compute" value="no"></html:hidden>
        <html:hidden property="chart"></html:hidden>
</html:form>
</td>
</tr>
</table>


</body>
 
    <script language="javascript">
    var Button;
    var man1=document.getElementById("hiddenTable");
	man1.style.display = "none";

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
function mavg(){
   	//alert(document.forms[0].check_mavg.checked);
}
    
function test1(){
	document.forms[0].submit();
}
	
function assignchart(){
	document.forms[0].chart.value="no";
	document.forms[0].submit();
}
	
function go1() {
 	var objTo=document.forms[0].to;
 	document.forms[0].compute.value="yes";
	//document.forms[0].clear.value ="yes";
	//document.forms[0].defaultVal.value="no";
	if(document.forms[0].check_mavg.checked==true)
    	document.forms[0].check_mavg.value="checked";
    else
    	document.forms[0].check_mavg.value="unchecked";
   
    if((checkdatecurrent(objTo))==false)	
	{
		alert("ToDate should be Less Than CurrentDate.");
		objTo.focus();
		objTo.select();
		return false;
	}
	else return true;
}
    //document.forms[0].submit();
//}
function go() {
 	document.forms[0].compute.value="yes";
	//document.forms[0].clear.value ="yes";
	//document.forms[0].defaultVal.value="no";
	if(document.forms[0].check_mavg.checked==true)
    document.forms[0].check_mavg.value="checked";
    else
    document.forms[0].check_mavg.value="unchecked";
    document.forms[0].submit();
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
function selectChart() {
	//document.forms[0].chart.value="yes";
	document.forms[0].chart.value=document.forms[0].selchart.value;
}

	
function PrintThisPage() 
{ 
	var chartType = DWRUtil.getValue("selChart");
	var sOption="toolbar=yes,location=no,directories=yes,menubar=yes,"; 
    sOption+="scrollbars=yes,width=850,height=550,left=100,top=25"; 	
   	var printHead =document.getElementById('PageTitle').innerHTML;
	var moreDet = document.getElementById('hiddenTable').innerHTML;	
   	var winprint=window.open("","",sOption); 
    winprint.document.open(); 
    winprint.document.write('<html><body>'); 
    winprint.document.write(printHead);
    winprint.document.write('<br>');
    winprint.document.write(moreDet);     
    winprint.document.write('<br>');
    winprint.document.write('<br>');
    winprint.document.write('<br>');
    if(Button=="AjaxButton"){		
   			var sWinHTML = document.getElementById('Ajaxcontentstart').innerHTML;    
   		}
   		else{ 
   			var sWinHTML = document.getElementById('contentstart').innerHTML; 
   		}
    winprint.document.write(sWinHTML);         
    winprint.document.write('</body></html>'); 
   	winprint.document.close(); 
    winprint.focus(); 
}

/*===================================== For DWR ========================================================================*/

var i = 0;
var trddate;
var closes;
var mcaps;
var divisors;
var column_value = 0;

function arrange_field(val)
{
  column_value = val;
  indexDivisor();
}

function indexDivisor() {
   
   DWRUtil.useLoadingMessage();
      
  var todate = DWRUtil.getValue("to");
  var fromdate = DWRUtil.getValue("from");
  var index = DWRUtil.getValue("selectIndex");  
  var chartType = DWRUtil.getValue("selChart");
  var span = DWRUtil.getValue("avgSpan");
  var mavg = DWRUtil.getValue("check_mavg");
  
  if(chartType!=0){
  	
	GraphMethods.getGraphChart1(showGraph,index, fromdate, todate, chartType,"inddivisor");
	changeDisplay("strutsgraph","none");
	changeDisplay("graph","none");
	}
	else{
	changeDisplay("strutsgraph","none");
	changeDisplay("graph","none");
	MoveTable.getIndexDivisor(fillTable, index, fromdate, todate, column_value);
	}  	 	         
}
	var tradingDate = function(IndexDivisor) { 
			trddate = IndexDivisor.tradingDate;
			i = 1;
			return '<font face ="Verdana" color="blue" size="2">'+IndexDivisor.tradingDate+'</font>'};			
	var close = function(IndexDivisor) { 
			closes = IndexDivisor.close;
			i = 2; 
			return '<font face="Verdana" color="blue" size="2">'+IndexDivisor.close+'</font>'}; // if we return to using dates, add .toLocaleDateString()	
	var mcap = function(IndexDivisor) { 
			mcaps = IndexDivisor.mcap;	
			i = 3;
			return '<font face="Verdana" color="blue" size="2">'+IndexDivisor.mcap+'</font>'};	
	var divisor = function(IndexDivisor) {
			divisors = IndexDivisor.divisor;	
			i = 4;
			return '<font face="Verdana" color="blue" size="2">'+IndexDivisor.divisor+'</font>'};

function fillTable(indexMovingTable) {
	var a = new Array();
	a = indexMovingTable; 	  
 if(a.length){
   	  //alert("");
   	  Button = "AjaxButton";
   	  changeDisplay("selectIndexMessage","none");
 	  changeDisplay("nodata","none");
 	  changeDisplay("noStrutsData","none");
 	  changeDisplay("strutslinks","none");
 	  changeDisplay("ajaxlinks","inline");
 	  changeDisplay("sortTable","inline"); 	
 	  changeDisplay("sortTabletable","none");
 	  
	  DWRUtil.removeAllRows("indexMovingTable"); 
	  DWRUtil.addRows("indexMovingTable",indexMovingTable, [ tradingDate, close, mcap, divisor ]);
	 }
 else{ 
 	 changeDisplay("sortTable","none");
     changeDisplay("noStrutsData","none");
     changeDisplay("sortTabletable","none");
	 changeDisplay("selectIndexMessage","none");
	 changeDisplay("strutslinks","none");
 	 changeDisplay("ajaxlinks","none");
 	 changeDisplay("nodata","inline");
 }

}

function showGraph(graphURL){
//alert(graphURL);
	var move_from = DWRUtil.getValue("from");
  	var move_to = DWRUtil.getValue("to");
	var selectIndex = DWRUtil.getValue("selectIndex");
	changeDisplay("graph","inline");
	var imgid = $("graph");
	imgid.setAttribute("src",graphURL);
	MoveTable.getIndexDivisor(fillTable,selectIndex,move_from,move_to,column_value);	
}

			
</script>	
</html:html>
