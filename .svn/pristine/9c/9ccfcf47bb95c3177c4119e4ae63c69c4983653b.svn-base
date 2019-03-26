<%@ page import = "java.io.PrintWriter" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "java.text.ParseException" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.Locale,java.util.*" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page  import="app.*"%>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>

<html>
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
<head>
	 <html:base/>
<link href="./StyleSheet.css" rel="stylesheet" type="text/css">
   <script language="javascript" src="../Script/codethatcalendarstd.js"></script>
	<script language="javascript" src="./box_ex.js"></script>
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
<body  onload="CleanUp()" >

<table width="100%" height="100%" >

<tr>
<td align="left" valign="top" bgcolor="#CCddee">

<DIV id="leftCol" style="DISPLAY: none; width:160; height:100%;">
<%@ include file="../tree3.jsp" %>
</div>
<IMG id="HideHandle" src="../openImage.gif" style="CURSOR: hand; position:absolute;" onclick='hideLeftCol("leftCol");' src="fold.gif" width="10" height="25">
</td>
<td align="left" valign="top">

<html:form action="/indexpe_pb" >
	<%        if(request.isRequestedSessionIdValid()) {%>
			<jsp:setProperty name="Indexpe_pbBean" property="userid1" value='<%=session.getAttribute("userid")%>'/>
			<jsp:setProperty name="Indexpe_pbBean" property="roleId_pepb" value='<%=session.getAttribute("role_id").toString()%>'/>
	<%}%>


	<div id="title" >
       <table width="500" cellspacing="0" cellpadding="0"  align="center">
		          	<td width="235" class="subHeader" nowrap="nowrap">
		          		&nbsp;
		          	</td>
		          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          			<font size="3" face="Verdana">
		          				<b><bean:message key="Indexpe.title" /> </b>
		          			</font>
		         	</td> 
	</table> 
</div>
 	<%
 		String ajax1="no";
			try{
				ajax1=request.getParameter("ajax1");
				session.setAttribute("ajax1",ajax1);				
			}catch (Exception e) {
				// TODO: handle exception
			//	Logging.error(" Error :"+e.getMessage());
			}
        String var=request.getParameter("d1"); 
              
       	String excel = "./FileDownload.jsp?type=20&filename=IndexPe_Pb.xls";
       	String xml = "./FileDownloadXmlNew.jsp?type=20&filename=IndexPe_Pb.xml";
       	String path_pdf="./FileDownload_Pdf.jsp?type=20&filename=IndexPe_Pb.pdf";
       	String str_url = "./EmailReport.jsp?switch_type=20&cas=20&rname=IndexPe_Pb.xls";
   	    
   	 %>
<logic:equal value="yes" property="hiddenVar" name="Indexpe_pbBean">
<logic:notEmpty property="index_arraylist"  name="Indexpe_pbBean"> 
   	   
		<table id="strutslinks" align="right">
					<%
						session.setAttribute("var",request.getParameter("var"));
   						session.setAttribute("varid",request.getParameter("var"));
   						session.setAttribute("iename",request.getParameter("var"));   
					%>
		 			<td valign="middle"><a href="javascript:PrintThisPage();" ><font size="1" face="Verdana" color="blue" > <bean:message key="LatestDivisor.printerf" /></font></a>  </td>
		 			<td valign="middle"><a href="<%= excel %>" ><font size="1" face="Verdana" color="blue" > <bean:message key="LatestDivisor.downloade" /></font></a>   </td>
		 			<td valign="middle"><a href="<%= xml %>" ><font size="1" face="Verdana" color="blue" > Export to Xml</font></a>   </td>
		 			<td valign="middle"><a href="<%= path_pdf %>" ><font size="1" face="Verdana" color="blue" ><bean:message key="LatestDivisor.pdfr" /></font></a>   </td>
		 			<td valign="bottom" ><a href=" <%= str_url %>" ><font size="1" face="Verdana" color="blue" > <bean:message key="LatestDivisor.emailr" /></font></a></td>
		</table>
</logic:notEmpty>
</logic:equal>
	
		<table id="ajaxlinks" align="right" style="display: none">
		 			<td valign="middle"><a href="javascript:PrintThisPage();" ><font size="1" face="Verdana" color="blue" > <bean:message key="LatestDivisor.printerf" /></font></a>  </td>
		 			<td valign="middle"><a href="<%= excel %>" ><font size="1" face="Verdana" color="blue" > <bean:message key="LatestDivisor.downloade" /></font></a>   </td>
		 			<td valign="middle"><a href="<%= xml %>" ><font size="1" face="Verdana" color="blue" > Export to Xml</font></a>   </td>
		 			<td valign="middle"><a href="<%= path_pdf %>" ><font size="1" face="Verdana" color="blue" ><bean:message key="LatestDivisor.pdfr" /></font></a>   </td>
		 			<td valign="bottom" ><a href=" <%= str_url %>" ><font size="1" face="Verdana" color="blue" > <bean:message key="LatestDivisor.emailr" /></font></a></td>
		</table>
	<br>
<html:hidden property="indexName" name="Indexpe_pbBean"/>	 
 <table width="656" >
     	
     	
     	<tr>
  		   <font size="2" face="Verdana">
    			<td width="202" nowrap="nowrap" align="right">
		          <font size="2" face="Verdana">  <bean:message key="indexUpdate.selectIndex" />:</font>
        	    </td>          
		        <td width="400" nowrap="nowrap" align="left">
					<html:select property="d1"  onchange="test1()" >
						<html:optionsCollection property="vector_indexlist" name="Indexpe_pbBean"/>
					</html:select>
			 	</td>
		 </font>
	 </tr>
 </table>
	<!-- 	<table width="550" align="center">
        		<td nowrap="nowrap" align="right">
					<html:checkbox property = "check"  value = "checked" onclick = "return test1()"/> &nbsp;
				</td>
		  	    <td nowrap="nowrap" align="left">	
				   <font size="2" face="Verdana">  
						  <bean:message key="IndexComparision.showsi" />
				  </font>
 			  	</td>
 			</table> -->
 		
 			<table align="center">
				<td width="69" nowrap="nowrap"><font size="2" face="Verdana" >
        	    	  <bean:message key="corporate.Fdate" /></font>
    	        </td> 
	        	<td width="78" nowrap="nowrap">
					<html:text property="from" size="10" readonly="true"/>
				</td>
				<td width="37" nowrap="nowrap">    
        	         <html:button property="" value="..." onclick="c2.popup('from');"/>
            	</td>
			
			    <td width="58" nowrap="nowrap"><font size="2" face="Verdana">
                        <bean:message key="corporate.Tdate" /></font>
    	        </td>

        	    <td width="79" nowrap="nowrap">	
					<html:text property="to" size="10"  readonly="true" />
				</td>

				<td width="37" nowrap="nowrap">    
        	         <html:button property="" value="..." onclick="c2.popup('to');" />
            	</td>

	            <td width="162" nowrap="nowrap">
	           
                     <html:hidden property="hiddenVar"  name="Indexpe_pbBean"/>
                  <%	if(ajax1.equals("yes")){
	  			%>
					<html:submit property="b1" onclick="return go()"> <bean:message key="Reports.View"/> 	</html:submit>
				<% }else{
				%>
					<INPUT type="Button" name="AjaxView" value="AjaxView" onclick="indexWisePePb()"/>
				<% } %>
				</td>
		</tr>
	</table>
	<p>
	
	<!-- ============================================ For Ajax ================================================== -->	
	
	<table border="0" align="center" width="631" height="222" class="gridStyle" cellspacing="0" cellpadding="0">
       <!--  <tr>
	          <td id="selectindex" class="gridStyle-message" align="center" valign="middle">
    	          <p style="margin-left: 9"><bean:message key="StockPerformance.selData" />  </a></p>
	          </td>
         </tr>
        --> 

		 <tr>
 			<td ID="nodata" bgcolor="#84AADE" align="center" valign="middle" nowrap="nowrap" style="display:none">
 				<p style="margin-left: 9">
 					<b><font face="Verdana" color="blue" size="4">
							<bean:message key="IndexCompareOHCL.ndata" />
						</font>
					</b>
			</td>
		 </tr>
   </table>  
   <div id="Ajaxcontentstart">		
   
		<table width="160%" class ="sorted"  ID="tabular" style="display:none"
			border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#EFEFEF">    
             
				<thead >
				<tr>				
					<th width="90">Trading Date</th>
					<th width="50">Close</th>
					<th width="80">% Change</th>
					<th width="150">Mkt. Cap.(In Millions)</th>				
					<th width="100">Shares Traded</th>
					<th width="150">Turnover(In Millions)</th>
					<th width="70">P/E Ratio</th>
					<th width="70">P/B Ratio</th>
					<th width="100">Dividend Yield</th>					
				</tr>
				</thead>						
				<tbody id="indexMovingTable"> </tbody>								
		</table>
		
	</div>	   
	<!-- ============================================ For Ajax ================================================== -->
	

	<logic:notEqual value="yes"  name="Indexpe_pbBean" property="hiddenVar" >	
	<table border="0" align="center" width="631" height="222" class="gridStyle" cellspacing="0" cellpadding="0">
          <tr></tr><tr>
	          <td id="selectindex" bgcolor="#84AADE" align="center" valign="middle">
    	          <p style="margin-left: 9">
    	          <b><font face="Verdana" color="blue" size="4">
    	          <bean:message key="StockPerformance.selData" />  </a></font></p>
	          </td>
         </tr>
   </table>
   </logic:notEqual>
   
    <logic:equal value="yes"  name="Indexpe_pbBean" property="hiddenVar" >	
    	<logic:empty property="index_arraylist" name="Indexpe_pbBean">
    	<br><br>
			   <table  border="0" width="550" height="250" cellpadding="0" cellspacing="0" align="center" class="sortable">	
    		 			<tr></tr><tr>
		         			<td id = "noStrutsData" bgcolor="#84AADE" align="center" valign="middle" nowrap="nowrap">
		         				<p style="margin-left: 9">
		         					<b><font face="Verdana" color="blue" size="4">
     									<bean:message key="IndexCompareOHCL.ndata" />
     								</font></b>
     						</td>
     					</tr>
  			   </table>
   	    </logic:empty>

        <logic:notEmpty property="index_arraylist"  name="Indexpe_pbBean"> 

	

	<div id="table" >	
	    <table border="0" width="800"  cellpadding="2" cellspacing="0" align="left" id="tabular" >
    		<tr>
				<td>
					<table class="sorted" ID="sortTable" 
			border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#EFEFEF">
				<thead>
					<tr>
					<!-- <table border="1" width="80%" align="center" cellpadding="2" cellspacing="0" id="t1" class="sortable" >
						<tr> -->     
							<th bgcolor="#EFEFEF" align="center" nowrap="nowrap" id="Trading" class="gridStyle-header">
								<span><b><bean:message key="Indexpe.Trading" /></b></span>
							</th>
							<th  bgcolor="#EFEFEF" align="center" nowrap="nowrap" id="Close" class="gridStyle-header">
								<span><b><bean:message key="Indexpe.Close" /></b></span>
							</th>
							<th  bgcolor="#EFEFEF" align="center" nowrap="nowrap" id="Change" class="gridStyle-header">
								<span><b><bean:message key="DisplayIndexes1.Change" /></b></span>
							</th>
							<th  bgcolor="#EFEFEF" align="center" nowrap="nowrap" id="Mar" class="gridStyle-header">
								<span><b><bean:message key="StockPerformance.Mar" /></b></span>
							</th>
							<th  bgcolor="#EFEFEF" align="center" nowrap="nowrap" id="Traded" class="gridStyle-header">
								<span><b><bean:message key="Indexpe.Traded" /></b></span>
							</th>
							<th  bgcolor="#EFEFEF" align="center" nowrap="nowrap" id="Turnover" class="gridStyle-header">
								<span><b><bean:message key="Indexpe.Turnover" /></b></span>
							</th>
							<th  bgcolor="#EFEFEF" align="center" nowrap="nowrap" id="Ratio" class="gridStyle-header">
								<span><b><bean:message key="Indexpe.Ratio" /></b></span>
							</th>
							<th bgcolor="#EFEFEF" align="center" nowrap="nowrap" id="Ratiop" class="gridStyle-header">
								<span><b><bean:message key="Indexpe.Ratiop" /></b></span>
							</th>
							<th bgcolor="#EFEFEF" align="center" nowrap="nowrap" id="Yield" class="gridStyle-header">
								<span><b><bean:message key="Indexpe.Yield" /></b></span>
							</th>
			</tr>
		  </thead>
		<tbody>
	      <logic:iterate id="pepb" name="Indexpe_pbBean" property="index_arraylist">
		  	<tr>
		       <td width="10%" nowrap="nowrap" align="left" class="gridStyle-odd" axis="date" headers="Trading"
           			title='<bean:write name="pepb" property="training_date"/>'>
    		   		<font face="Verdana" color="blue" size="2">
						<bean:write name="pepb" property="training_date"/>		 	
					</font>
			  </td>
		      <td width="10%" nowrap="nowrap" align="right" class="gridStyle-odd" axis="number" headers="Close"   
	  		title='<bean:write name="pepb" property="close"/>'>
      			<font face="Verdana" color="blue" size="2">
					<bean:write name="pepb" property="close"/>
				</font>
		   	  </td>
		      <td width="10%" nowrap="nowrap" align="right" class="gridStyle-odd" axis="number" headers="Change"   
	  		title='<bean:write name="pepb" property="change"/>'>
		      	<font face="Verdana" color="blue" size="2">
					<bean:write name="pepb" property="change"/>
				</font>
		   	  </td>
		      <td width="10%" nowrap="nowrap" align="right" class="gridStyle-odd" axis="number" headers="Mar"   
	  		title='<bean:write name="pepb" property="mcap"/>'>
		      	<font face="Verdana" color="blue" size="2">
					<bean:write name="pepb" property="mcap"/>
				</font>
		   	  </td>
		      <td width="10%" nowrap="nowrap" align="right" class="gridStyle-odd" axis="number" headers="Traded"   
	  		title='<bean:write name="pepb" property="shares_traded"/>'>
		      	<font face="Verdana" color="blue" size="2">
					<bean:write name="pepb" property="shares_traded"/>
				</font>
	   		  </td>
    		  <td width="10%" nowrap="nowrap" align="right" class="gridStyle-odd" axis="number" headers="Turnover"   
	  		title='<bean:write name="pepb" property="turnover"/>'>
		      	<font face="Verdana" color="blue" size="2">
						<bean:write name="pepb" property="turnover"/>
				</font>
		   	  </td>
		      <td width="10%" nowrap="nowrap" align="right" class="gridStyle-odd" axis="number" headers="Ratio"   
	  		title='<bean:write name="pepb" property="peratio"/>'>
		      	<font face="Verdana" color="blue" size="2">
					<bean:write name="pepb" property="peratio"/>
				</font>
		   	  </td>
		      <td width="10%" nowrap="nowrap" align="right" class="gridStyle-odd" axis="number" headers="Ratiop"   
	  		title='<bean:write name="pepb" property="pbratio"/>'>
	      		<font face="Verdana" color="blue" size="2">
					<bean:write name="pepb" property="pbratio"/>
				</font>
   			  </td>
		      <td width="10%" nowrap="nowrap" align="right" class="gridStyle-odd" axis="number" headers="Yield"   
	  		title='<bean:write name="pepb" property="dividend"/>'>
      			<font face="Verdana" color="blue" size="2">
					<bean:write name="pepb" property="dividend"/>
				</font>
		   	  </td>
		  </tr>
		   
	</logic:iterate>
	</tbody>
	</table>
	</td>
	</tr>    
	</table>
	</div>
	</logic:notEmpty>
	</logic:equal>
</html:form>
</td>
</tr>
</table>
</body>
<script language="javascript">
 var Button;
function test1(){
document.forms[0].hiddenVar.value="no";
	document.forms[0].submit();
}
function popprinter(url)
{
	newwindow=window.open(url,'name','height=500,width=900,scrollbars=yes,left=80,top=130,menubar=yes,status=yes');
	if (window.focus) {newwindow.focus()}
}
function go() {
var msg="";
if(document.forms[0].d1.value==""){
		msg=msg+"Select Index\n";
}
if(document.forms[0].from.value==""){
		msg=msg+"Select FromDate\n";
}
if(document.forms[0].to.value==""){
		msg=msg+"Select ToDate\n";
}
if(msg!=""){
		alert(msg);
		return false;
}
document.forms[0].hiddenVar.value="yes";
return true;
}
function CleanUp(){
	document.clear();
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

	function PrintThisPage() 
	{ 
		var iname;
 		var ttle =document.getElementById('title').innerHTML;
   		//var tble = document.getElementById('table').innerHTML;
   		var tdate=document.forms[0].to.value;
   		var fdate=document.forms[0].from.value;
   		//var iname=document.forms[0].indexName.value
   		var winprint=window.open("","",""); 
      	winprint.document.open(); 
       	winprint.document.write(ttle);
      	winprint.document.write('<br>');
      	winprint.document.write('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;');
      	winprint.document.write('<bean:message key="defineIndex7"/>');
      	winprint.document.write(':&nbsp;&nbsp;&nbsp;'); 
      	if(Button=="AjaxButton"){	
      		iname=DWRUtil.getText("d1"); ;	
   			var sWinHTML = document.getElementById('Ajaxcontentstart').innerHTML;    
   		}
   		else{
   			iname=document.forms[0].indexName.value; 
   			var sWinHTML = document.getElementById('table').innerHTML; 
   		}
	   	winprint.document.write(iname); 
	   	winprint.document.write('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;');
	   	winprint.document.write(' <bean:message key="corporate.Fdate" />'); 
	   	winprint.document.write('&nbsp;');
	   	winprint.document.write(fdate); 
	   	winprint.document.write('&nbsp;&nbsp;&nbsp;');
	   	winprint.document.write(' <bean:message key="corporate.Tdate" />'); 
	   	winprint.document.write('&nbsp;');
	   	winprint.document.write(tdate); 
	   	
      	winprint.document.write('<br>');
	   	winprint.document.write('<br>');
	   	
       	winprint.document.write(sWinHTML); 
       	winprint.document.close(); 
       	winprint.focus(); 
	}
	
/*===================================== For DWR ========================================================================*/
function indexWisePePb() {
  if(go()){
		  DWRUtil.useLoadingMessage();	    
		  var todate = DWRUtil.getValue("to");
		  var fromdate = DWRUtil.getValue("from");
		  var index = DWRUtil.getValue("d1");    
		  MoveTable.getIndexWisePePb(fillTable, index, fromdate, todate);
	}
}

	var training_date = function(IndexPePbDetails) { return '<font face="Verdana" color="blue" size="2">'+IndexPePbDetails.training_date+'</font>' };			
	var close = function(IndexPePbDetails) { return '<font face="Verdana" color="blue" size="2">'+IndexPePbDetails.close+'</font>'}; // if we return to using dates, add .toLocaleDateString()
	var change = function(IndexPePbDetails) { return '<font face="Verdana" color="blue" size="2">'+IndexPePbDetails.change+'</font>'};
	var mcap = function(IndexPePbDetails) { return '<font face="Verdana" color="blue" size="2">'+IndexPePbDetails.mcap+'</font>'};
	var shares_traded = function(IndexPePbDetails) { return '<font face="Verdana" color="blue" size="2">'+IndexPePbDetails.shares_traded+'</font>'};
	var turnover = function(IndexPePbDetails) { return '<font face="Verdana" color="blue" size="2">'+IndexPePbDetails.turnover+'</font>'};
	var peratio = function(IndexPePbDetails) { return '<font face="Verdana" color="blue" size="2">'+IndexPePbDetails.peratio+'</font>'};
	var pbratio = function(IndexPePbDetails) { return '<font face="Verdana" color="blue" size="2">'+IndexPePbDetails.pbratio+'</font>'};
	var dividend = function(IndexPePbDetails) { return '<font face="Verdana" color="blue" size="2">'+IndexPePbDetails.dividend+'</font>'};
	
	//alert(stockId);
function fillTable(indexMovingTable) {
  
   var a = new Array();
	  a = indexMovingTable;   
 if(a.length){
   	  Button = "AjaxButton";
   	  changeDisplay("selectindex","none"); 
 	  changeDisplay("nodata","none");
 	  changeDisplay("sortTable","none");
 	  changeDisplay("noStrutsData","none");
 	  changeDisplay("strutslinks","none");
 	  changeDisplay("ajaxlinks","inline");
 	  changeDisplay("tabular","inline"); 	  	  	
	  DWRUtil.removeAllRows("indexMovingTable");
	  DWRUtil.addRows("indexMovingTable",indexMovingTable, [ training_date, close, change, mcap, shares_traded, turnover, peratio, pbratio, dividend ]);
 }
 else{ 
 	 changeDisplay("sortTable","none");
	 changeDisplay("tabular","none");
	 changeDisplay("noStrutsData","none");	
	 changeDisplay("selectindex","none");
	 changeDisplay("strutslinks","none");
 	 changeDisplay("ajaxlinks","none");
     changeDisplay("nodata","inline");
 } 
}

</script>

</html>

	 

