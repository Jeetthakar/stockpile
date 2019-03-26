
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
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
				form = (LogonForm)session.getAttribute("user");
			}
			if(form==null ||(!request.isRequestedSessionIdValid())){
				response.sendRedirect("../userlogintemp.jsp");
			}
		%>
<html:html>
<html:base/>
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
<body onload="initSort()" >

<table width="100%" height="100%" >

<tr>
<td align="left" valign="top" bgcolor="#CCddee">

<DIV id="leftCol" style="DISPLAY: none; width:160; height:100%;">
<%@ include file="../tree3.jsp" %>
</div>
<IMG id="HideHandle" src="../openImage.gif" style="CURSOR: hand; position:absolute;" onclick='hideLeftCol("leftCol");' src="fold.gif" width="10" height="25">
</td>
<td align="left" valign="top">

<html:form action="/StockList">
<div id="PageTitle">
	<table width="1000" cellspacing="0" cellpadding="0" >
    	<tr>
			<td width="250" class="subHeader" nowrap="nowrap">
		    	&nbsp;
		  	</td>
		    <td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		    	<font size="3" face="Verdana">
		        	<b><bean:message key="StockList.title" /> </b>
		      	</font>
		  	</td> 
	   	</tr>
	</table> 
</div>
<jsp:setProperty name="stockListBean" property="filter" />
	<logic:equal  value="yes" parameter="compute" >	<!-- if compute ="yes" display links -->
	<logic:notEqual value="0" name="dataCount">
	<%
	  		String exch_name= request.getParameter("exchName");
	  		String stk_name= request.getParameter("stockName");
	  		System.out.println(" Exchange Name = " + stk_name);
	  		System.out.println(" Exchange Name = " + exch_name);
	  		String var=request.getParameter("selectExchange");
	  		String excel_path = "./FileDownload.jsp?var="+var+"&type=21&filename=StockList.xls"+"&exchName="+stk_name;
	  		String pdf_path = "./FileDownload_Pdf.jsp?var="+var+"&type=21&filename=StockList.pdf"+"&exchName="+stk_name;
	  		String xml_path = "./FileDownloadXmlNew.jsp?var="+var+"&type=21&filename=StockList.xml"+"&exchName="+stk_name;		
	  		String str_url = "./EmailReport.jsp?switch_type=21&cas=21&varid="+var+"&rname=StockList.xls";//&from=a&to=a";
	%>
	<table align="right">
		<tr>
	 		<td  width="212" align="right" nowrap="nowrap"></td> 
         	
         	
         	<!-- Links  -->
	 		<td > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 			<!-- Printer friendly -->
	 			<a href="javascript:PrintThisPage()" ><font size="1" face="Verdana" color="blue"> <bean:message key="LatestDivisor.printerf" /></font></a>&nbsp;
	 			
	 			<!-- DownLoad Excel -->
	 			<a href='<%= excel_path %>' ><font size="1" face="Verdana" color="blue"> <bean:message key="LatestDivisor.downloade" /></font></a>&nbsp;
	 			
	 			<!-- DownLoad Excel -->
	 			<a href='<%= xml_path %>' ><font size="1" face="Verdana" color="blue"> Export to Xml</font></a>&nbsp;
	 			
	 			<!-- Email Report -->
	 			<a href='<%= str_url %>' ><font size="1" face="Verdana" color="blue"> <bean:message key="LatestDivisor.emailr" /></font></a>&nbsp;
	 			<a href='<%= pdf_path %>' ><font size="1" face="Verdana" color="blue"><bean:message key="LatestDivisor.pdfr" /></font></a>&nbsp;
	 			
	 		</td> 	
	  	</tr>
	</table>
	</logic:notEqual>
	</logic:equal>
	
	<br>
	<br>
 
    <!-- Select List for Exchange names -->
    <table width="656">
    <tr>
		<td width="90" nowrap="nowrap" align="right">
			<font size="2" face="Verdana">&nbsp;
     		</font>
       	</td> 
	  	<td width="120" nowrap="nowrap" align="right">
			<font size="2" face="Verdana"><bean:message key="StockList.Exchange" /> 
			</font>
       	</td>                 
         
        <td width="400" nowrap="nowrap" align="left" height="30">
        	<html:select property="selectExchange" onchange="indiChange()" size="1" styleId="sIE">
				<html:optionsCollection property="selectExchgCollection" name="stockListBean"/>
			</html:select>
    	</td>
    	
    	<td nowrap="nowrap"> <!-- View Button -->
            <html:submit onclick="go();" onkeypress="go();"><bean:message key="Reports.View"/></html:submit>&nbsp;&nbsp;
		</td>
    </tr>
	</table>
	

	<logic:notEqual value="yes" parameter="compute">
	<table border="0" align="left" width="700" height="222" cellspacing="0" cellpadding="0">
		<tr>
        	<td width="99"></td>
			<td bgcolor="#84AADE" align="center" valign="middle">
          		<p style="margin-left: 9">
          		<b><font face="Verdana" color="blue" size="5">
          		<bean:message key="StockList.Name" />
          		</font></b>
          		</p>
          	</td>
      	</tr>
  	</table>
	</logic:notEqual>
	
	<logic:equal value="yes" parameter="compute">
	<p align="center" style="font size=15"> 

		<%		
      			String exchname = request.getParameter("selectExchange");
            	char letter='\0';
      			for(int m=65;m<=90;m++){
      			 	  letter=(char)m;
      			 	  String let = new Character(letter).toString();
      			 	  String lselect="./StockListS.jsp?compute=yes&selectExchange="+exchname+"&filter="+let; 
    
      %>
     
      <html:link href='<%=lselect %>'><%=letter %></html:link>&nbsp;
      
	<% 	}	 %>

	</p>
	
	<bean:define id="details" name="stockListBean" property="tableData"/>
	
	  	<bean:size id="dataCount" name="stockListBean" property="tableData"/>
	  		
	  	<logic:equal value="0" name="dataCount" >
	  	
	  	<table border="0" align="left" width="631" height="222" cellspacing="0" cellpadding="0">
	  	    <tr>
          		<td width="99"></td>
	  		
          		<td bgcolor="#84AADE" align="center" valign="middle">
          			<p style="margin-left: 9">
          			<b><font face="Verdana" color="blue" size="5">
          			<bean:message key="IndexCompareOHCL.ndata" />
          			</font></b></p>
          		</td>
        	</tr>
       	</table>
        </logic:equal>
          
        <logic:notEqual value="0" name="dataCount">
        
        <div id=contentstart> 
    	<table class="sorted" ID="sortTable" 
			border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#EFEFEF">
				<thead>
					<tr>
    	<!-- <table border="1" align="center" cellpadding="3" cellspacing="0" id="t1" class="sortable" bordercolor="#EFEFEF">
        	<tr> -->
        		
        		<!-- Table Heading -->
          		<th nowrap="nowrap" align="center" id="stockName" class="gridStyle-header">
          		<span><b><bean:message key="stockmaster.stockName" /></b></span></th>
          		<th align="center" nowrap="nowrap" id="Shares" class="gridStyle-header">
          		<span><b><bean:message key="StockList.Shares" /></b></span></th>
		        <th align="center" nowrap="nowrap" id="Price" class="gridStyle-header">
		        <span><b><bean:message key="StockList.Price" /></b></span></th>
        		<th align="center" nowrap="nowrap" id="Mar" class="gridStyle-header">
        		<span><b><bean:message key="StockPerformance.Mar" /></b></span></th>
          		<th align="center" nowrap="nowrap" id="faceValue" class="gridStyle-header">
          		<span><b><bean:message key="stockmaster.faceValue" /></b></span></th>
          		<th nowrap="nowrap" align="center" id="Date" class="gridStyle-header">
          		<span><b><bean:message key="corporate.Date" /></b></span></th>
      		    
          	</tr>   		
          	</thead>
		<tbody>
		 <%
 				int i=1;
 				String bcolor="";
 			%>
          	<!-- Iterate over the table data -->
          	<logic:iterate id="details" name="stockListBean" property="tableData">
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
          	
     			<td valign="middle" align="left" nowrap="nowrap"  axis="sstring" headers="stockName"
           			title='<bean:write name="details" property="stockName"/>'>
     			<a href='../masters/stockmaster2.jsp?s_stockid=<bean:write name="details" property="stockId"/>'>
        			<font face="Verdana" size="2" color="blue"><bean:write name="details" property="stockName"/></font></a>
        			
          		</td>
          		<td align="right"  valign="middle" nowrap="nowrap"  axis="number" headers="Shares"
           			title='<bean:write name="details" property="tis"/>'>
          			<font face="Verdana" size="2" color="blue"><bean:write name="details" property="tis"/></font>
          			
          		</td>
          		<td  align="right" valign="middle" nowrap="nowrap"  axis="number" headers="Price"
           			title='<bean:write name="details" property="closeVal"/>'>
          			<font face="Verdana" size="2" color="blue"><bean:write name="details" property="closeVal"/></font>
          			
          		</td>
          		<td  align="right" valign="middle" nowrap="nowrap" axis="number" headers="Mar"
           			title='<bean:write name="details" property="mcv"/>'>
          			<font face="Verdana" size="2" color="blue"><bean:write name="details" property="mcv"/></font>
          			
         	 	</td>
       		   	<td  align="left" valign="middle" nowrap="nowrap"  axis="number" headers="faceValue"
           			title='<bean:write name="details" property="faceVal"/>'>
     		   		<font face="Verdana" size="2" color="blue"><bean:write name="details" property="faceVal"/></font>
     		   		
       		   	</td>
         	 	<td nowrap="nowrap"  align="right" valign="middle"  axis="date" headers="Date"
           			title='<bean:write name="details" property="date"/>'>
         	 		<font face="Verdana" size="2" color="blue"><bean:write name="details" property="date"/></font>
         	 		
         		</td> 	
          
    		</tr>
    		<% i=i+1; %>
    		</logic:iterate>
           </tbody>
        </table> 
        </div>
    	</logic:notEqual>
   	</logic:equal>
	
	<!-- table for Printer Friendly -->
	<div id="Details">
    <table id="hiddenTable" height="100">
    	<tr>
    		<td >
    		</td>
    		<td><b><font face="Verdana"  >
    			<bean:message key="StockList.Exchange" /> :</font>
    			</b>
    		</td>
    		<td > <font face="Verdana"  >
    			<bean:write name="stockListBean" property="stockName"/></font>
    		</td>
    	</tr>
    	
    </table>
    </div>  
    <html:hidden property="exchName"></html:hidden>
	<html:hidden property="excelFormat" value="no"></html:hidden>
	<html:hidden property="stockName"></html:hidden>
	<html:hidden property="filter" value="" ></html:hidden>
	<html:hidden property="compute" value="yes"></html:hidden>
	
	</html:form>

</td>
</tr>
</table>	
	
</body>

<script language="javascript">
	var man1=document.getElementById("Details");
	man1.style.display= "none"; 
 

	function indiChange(){
		
		document.forms[0].compute.value="no";
		return true;
	}
 	function go() {
	 	document.forms[0].compute.value="yes";
						
	}
	
	function PrintThisPage() 
	{ 
   		var sOption="toolbar=yes,location=no,directories=yes,menubar=yes,"; 
       	sOption+="scrollbars=yes,width=750,height=600,left=100,top=25"; 

   		var sWinHTML = document.getElementById('contentstart').innerHTML; 
   		var printHead =document.getElementById('PageTitle').innerHTML;
   		var moreDet = document.getElementById('Details').innerHTML;
   		
   
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
	function checkSorting(val){
		alert(' Sorting letter = '+ val );
		document.forms[0].filter.value=val;
		alert(' After assigning sorting letter = '+ document.forms[0].filter.value);
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

	function ExcelReport(){
		alert('Inside xcel report');
		document.forms[0].excelFormat.value="yes";
	}
</script>

</html:html>
