<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="java.io.*"%>
<%@ page  import="harrier.income.com.report.*" %>
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
<html:errors /> 
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
		<link href="./StyleSheet.css" rel="stylesheet" type="text/css">
		<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
		<script language="javascript" src="box_ex.js"></script>
		<script type="text/javascript" src="../Script/SortedTable.js"></script>
		
		<script type='text/javascript' src='/Stockpile/dwr/interface/GraphMethods.js'></script>
		<script type='text/javascript' src='/Stockpile/dwr/interface/MoveTable.js'></script>
		<script type='text/javascript' src='/Stockpile/dwr/engine.js'></script>
		<script type='text/javascript' src='/Stockpile/dwr/util.js'></script>
			
		<script language="javascript">
			var c2 = new CodeThatCalendar(caldef2);			
		</script>
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
		
	<title><bean:message key="StockHighLow.title" /></title>
	
	
	
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

    <html:form action="/stockDetails">


<%        if(request.isRequestedSessionIdValid()) {%>
				<jsp:setProperty name="stockDetailsBean" property="userid1" value='<%=session.getAttribute("userid")%>'/>
				<jsp:setProperty name="stockDetailsBean" property="roleId_stk" value='<%=session.getAttribute("role_id").toString()%>'/>
		<%}%>

    <div id="PageTitle">
   	<table cellspacing="0" cellpadding="0" >
    <tr>
			<td width="200" class="subHeader" nowrap="nowrap">
		     	&nbsp;
		   </td>
		   <td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		   		<font size="3" face="Verdana">
		        	<b><bean:message key="StockHighLow.title" /></b>
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
	  		String stk_name= request.getParameter("stockName");
	  		String ind_name=request.getParameter("indexName");	  		
	  		String var=request.getParameter("selectStock");
	  		String to = request.getParameter("to");
	  		String from = request.getParameter("from");
	  		String excel_path = "./FileDownload.jsp?var="+var+"&type=6&filename=StockHighLow.xls&from="
	  				+from+"&to="+to+"&indName="+ind_name+"&stkName="+stk_name+"&technology=noAjax";
	  		String xml_path = "./FileDownloadXmlNew.jsp?var="+var+"&type=6&filename=StockDetails.xml&from="
	  				+from+"&to="+to+"&indName="+ind_name+"&stkName="+stk_name;		
	  		String pdf_path="./FileDownload_Pdf.jsp?type=6&filename=StockDetails.pdf";
	  		
	  		String str_url = "./EmailReport.jsp?switch_type=6&cas=6&rname=StockHighLow.xls&from="
	  				+from+"&to="+to+"&varid="+ind_name;
	  	 %>
	  	 <logic:equal  value="yes" parameter="compute">
	  	 <logic:notEqual value="0" name="dataCount">	
	  	 <br>
	 	<table id = "strutslinks" width="550" align="right">
	 	        <td  width="100">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td>
	 			<td><a href="javascript:PrintThisPage()" ><font face="Verdana" size="1" ><bean:message key="LatestDivisor.printerf" /></font></a></td> 
	 			<td><a href='  <%= excel_path%>' ><font face="Verdana" size="1" ><bean:message key="LatestDivisor.downloade" /></font></a></td> 
	 			<td><a href='  <%= xml_path%>' ><font face="Verdana" size="1" >Export to Xml</font></a></td> 
	 		    <td><a href='  <%= pdf_path%>' ><font face="Verdana" size="1" ><bean:message key="LatestDivisor.pdfr" /></font></a></td> 	
	 		    
	 		    <td><a href=' <%= str_url%>' ><font face="Verdana" size="1" ><bean:message key="LatestDivisor.emailr" /></font></a></td> 	
	  	</table>
	  	  
	  	</logic:notEqual>	
	  </logic:equal>
	  
	  <%	String excel_pathaj = "./FileDownload.jsp?type=6&filename=StockHighLow.xls&technology=Ajax";
	     	String xml_pathaj = "./FileDownloadXmlNew.jsp?type=6&filename=StockDetails.xml";		
	  		String pdf_pathaj="./FileDownload_Pdf.jsp?type=6&filename=StockDetails.pdf";	  		
	  		String str_urlaj = "./EmailReport.jsp?switch_type=6&cas=6&rname=StockHighLow.xls";
	  %>				
	  
	  <table id = "ajaxlinks" width="550" align="right" style="display: none">
	 	        <td  width="100">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td>
	 			<td><a href="javascript:PrintThisPage()" ><font face="Verdana" size="1" ><bean:message key="LatestDivisor.printerf" /></font></a></td> 
	 			<td><a href='  <%= excel_pathaj%>' ><font face="Verdana" size="1" ><bean:message key="LatestDivisor.downloade" /></font></a></td> 
	 			<td><a href='  <%= xml_pathaj%>' ><font face="Verdana" size="1" >Export to Xml</font></a></td> 
	 		    <td><a href='  <%= pdf_pathaj%>' ><font face="Verdana" size="1" ><bean:message key="LatestDivisor.pdfr" /></font></a></td> 		 		    
	 		    <td><a href=' <%= str_urlaj%>' ><font face="Verdana" size="1" ><bean:message key="LatestDivisor.emailr" /></font></a></td> 	
	  </table>
	<br>
	<logic:equal value="no" property="compute" name="stockDetailsBean">
		<table width="656">
       		<tr>
        		<td width="156" nowrap="nowrap" align="right">
					<font size="2" face="Verdana">
           				<bean:message key="Index.select"/>
           			</font>
        		</td>    
 				<td width="489" nowrap="nowrap" align="left" height="30">
          			<html:select property="selectIndex" size="1" onchange="indiChange();" styleId="sIE">
						<html:optionsCollection property="selectIndexCollection" name="stockDetailsBean"/>
					</html:select>
         		</td>
     		</tr>
 		</table> 
	</logic:equal>
	<logic:notEqual value="no" property="compute" name="stockDetailsBean">
		<table width="656">
       		<tr>
        		<td width="156" nowrap="nowrap" align="right">
					<font size="2" face="Verdana">
           				<bean:message key="Index.select"/>
           			</font>
        		</td>    
 				<td width="489" nowrap="nowrap" align="left" height="30">
          			<html:select property="selectIndex" size="1" onchange="indiChange();" styleId="sIE">
						<html:optionsCollection property="selectIndexCollection" name="stockDetailsBean"/>
					</html:select>
         		</td>
     		</tr>
 		</table> 
	</logic:notEqual>
	<logic:notEqual value="no" parameter="defaultVal" >
			<table width="656" >
				<tr>
					<td width="156" nowrap="nowrap" align="right">
						<font size="2" face="Verdana">
           					<bean:message key="StockHighLow.sstock" />
           				</font>
        			</td>
        			<td width="490" nowrap="nowrap" align="left" height="30">
        				<html:select property="selectStock" size="1" onchange=" return go();" styleId="Rem" style="width:300px" >
		 					<html:optionsCollection property="stockCollection" name="stockDetailsBean"/>
						</html:select>
         			</td>
        	 </tr>
 		</table>
 	</logic:notEqual>
 	<logic:equal value="no" parameter="defaultVal" >
			<table width="656" >
				<tr>
					<td width="156" nowrap="nowrap" align="right">
						<font size="2" face="Verdana">
           					<bean:message key="StockHighLow.sstock" />
           				</font>
        			</td>
        			<td width="490" nowrap="nowrap" align="left" height="30">
        				<html:select property="selectStock" size="1" onchange=" return go();" styleId="Rem" style="width:300px" >
		 					<html:optionsCollection property="stockCollection" name="stockDetailsBean"/>
						</html:select>
         			</td>
        	 </tr>
 		</table>
 	</logic:equal>	
 	<table >
     	
     <!-- 
     <tr>
     	  		<td  width="175" align="right" nowrap="nowrap">
        		<html:checkbox property="checkShwIndices" onclick="test1()" ></html:checkbox>
        	</td>
        	<td nowrap="nowrap" align="left">	
			  	<font size="2" face="Verdana">  
	  				<bean:message key="IndexComparision.showsi" />
	  			</font>&nbsp;
	  		</td>
		</tr>
		 -->
		
		<tr>
	  		<td width="175" align="right" nowrap="nowrap">
	  			<html:checkbox property="checkChart" ></html:checkbox>
	  		</td>
	  		<td nowrap="nowrap">	
				<font size="2" face="Verdana">  
	  				<bean:message key="IndexCompose.schart" />
	  			</font>
	  		</td>
	  		
	  	</tr> 
	  </table>
	  
	  <table >
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td width="79" nowrap="nowrap"><font size="2" face="Verdana" >
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
		<html:text property="to" size="10" readonly="true" />
		</td>

		<td width="37" nowrap="nowrap">
		<html:button property="" value="..." onclick="c2.popup('to');" />
		</td>
		<%	if(ajax1.equals("yes")){
	  	%>
		<td width="162" nowrap="nowrap">
		<html:submit property="b1"  onclick="return checkdate()" ><bean:message key="Reports.View"/></html:submit>&nbsp;&nbsp;
		</td>
		<% }else{
			%>
		<td> 
			<input type = "button" name = "view" value = "AjaxView" onclick = "getStockDetails()"/>
		</td>
		<% } %>
		</table>
		<p></p>
		
<!-- 	======================================== For Ajax ================================================== -->
	<div id = "Ajaxcontentstart">
   	    <table align="center">
   	 	<tr>
  	 		<img id="graph" align="top" width="500" height="270" border="0" style="display: none;">  
		</tr>  	 
   		<tr>  	 		
			<table width="170%" class ="sorted"  ID="sortTable" style="display:none"
				border="1" align="center" cellpadding="0" cellspacing="0" bgcolor="#EFEFEF">    
          		<!--  <table border="1"> -->      
				<thead >
				<tr>				
					<th width="100">Stock Name</th>
					<th width="120">Opening Value</th>
					<th width="100">Closing Value</th>
					<th width="130">Lowest Value</th>				
					<th width="100">Highest Value</th>
					<th width="120">Traded Volume</th>
					<th width="100">Traded Value</th>
					<th width="70">Mkt. Cap</th>
					<th width="110">No. Of Traders</th>
					<th width="90">Date</th>					
				</tr>
				</thead>						
				<tbody id="indexMovingTable"> </tbody>								
		   </table>
		</tr>
		</table>
	</div>	
		
		<table border="0" align="center" width="631" height="222" cellspacing="0" cellpadding="0">
	  		<!-- 	<tr id = "selectindexmessage">          			
          			<td class="gridStyle-message" align="center" valign="middle">
          				<p style="margin-left: 9"><bean:message key="StockHighLow.smess" /></p>
          			</td>
          		</tr>
          	-->	
          		<tr>          				
          			<td id = "nodata" style="display: none;" class="gridStyle-message" align="center" valign="middle">
          				<p style="margin-left: 9"><bean:message key="IndexCompareOHCL.ndata" /></p>
          			</td>
          		</tr>
       </table>
			   
<!-- ============================================ For Ajax ================================================== -->
 
	 <logic:notEqual value="yes" parameter="compute" >
	  		<table border="0" align="left" width="631" height="222" cellspacing="0" cellpadding="0">
	  			<tr>
          			<td width="99"></td>
          			<td id = "selectindexmessage" class="gridStyle-message" align="center" valign="middle">
          				<p style="margin-left: 9"><bean:message key="StockHighLow.smess" /></p>
          			</td>
          		</tr>
          	</table>
	  	</logic:notEqual >

	   	<logic:equal  value="yes" parameter="compute" >	
	  		<bean:size id="dataCount" name="stockDetailsBean" property="tableData"/>
	  		<bean:define id="details" name="stockDetailsBean" property="tableData"/>
	  		<div id="chart" >
	  		<table>
	  			<tr>
	  				<td>
	  				
	  				<logic:equal name="stockDetailsBean"  property="checkChart" value="on" >
	  				<jsp:setProperty name="stockDetailsBean" property="checkChart"/>
	  					
	  					  		<p align="center">
    		  		  		<% 	
    		  		  			Vector v1=(Vector)session.getAttribute("ci2");
    		  		  			//DatasetFactory1.candlestickReaddata(v1);
    		  		  			//String fname = Candlestick.generateChart(session, new PrintWriter(out));
    		  		  			
								GraphMethodsPf.candlestickReaddata(v1);
								String fname = GraphMethodsPf.generateChart(session, new PrintWriter(out));
								
								String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + fname;
								log.info("graphURL is"+graphURL);
								log.info("filename is"+fname);
    		  		  			
   							%>
								<img id = "strutsgraph" src='<%= graphURL %>' width=500 height=270 border=0 usemap='#<%= fname %>'>
							</p>
							
						</logic:equal>	
					
	  				</td>
	  			</tr>
	  		</table>
	  		</div>	
	  		<logic:equal value="0" name="dataCount" >
	  		<table border="1" align="left" width="631" height="222" cellspacing="0" cellpadding="0">
	  		
          		<tr>          				  		
          			<td id = "noStrutsData" class="gridStyle-message" align="center" valign="middle">
          				<p style="margin-left: 9"><bean:message key="IndexCompareOHCL.ndata" /></p>
          			</td>
          		</tr>
          	</table>
          	</logic:equal>
          	<logic:notEqual value="0" name="dataCount">
          	<div id="contentstart">  
			<table class="sorted" ID="sortTabletable" 
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
     			<logic:iterate id="details" name="stockDetailsBean" property="tableData">
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
			</div>
			</logic:notEqual>
	  		
<jsp:setProperty name="stockDetailsBean" property="compute" value="no"/>
	  		</logic:equal>	

<div id="hiddenTable">
<table id="hiddenTable" height="100">
	<tr>
	<td width="125" align="right"><b>
			<font size="2" face="Verdana"><bean:message key="defineIndex7" />: </font></b>
	</td>
	<td><font size="2" face="Verdana"><b>&nbsp;&nbsp;&nbsp;
    	<bean:write name="stockDetailsBean" property="indexName" /></b>
         </font>
	</td>
	</tr>
	
	<tr>
	<td width="125" align="right"><b>
			<font size="2" face="Verdana"><bean:message key="corporate.Stkname" />: </font></b>
		
	</td>
	<td><font size="2" face="Verdana"><b>&nbsp;&nbsp;&nbsp;
    	<bean:write name="stockDetailsBean" property="stockName" /></b>
         </font>
	</td>
	</tr>
	<tr>
	<td width="125" align="right"><font size="2" face="Verdana"><b>
    	<bean:message key="corporate.Fdate" />&nbsp;:</b>
         </font>
   	</td>
   	<td><font size="2" face="Verdana">
   		<bean:write name="stockDetailsBean" property="from"/>
   		</font>
   	</td> 
	</tr>
	<tr>
	<td width="125" align="right"><font size="2" face="Verdana"><b>
    	<bean:message key="corporate.Tdate" />&nbsp;:</b>
        </font>
   	</td>
   	<td><font size="2" face="Verdana">
   		<bean:write name="stockDetailsBean" property="to"/>
   		</font>
   	</td> 
	</tr>
</table>

</div>

<html:hidden property="stockName"></html:hidden>
<html:hidden property="indexName" ></html:hidden>
<html:hidden property="defaultVal" ></html:hidden>
<html:hidden property="clear" value="no"></html:hidden>
<html:hidden property="compute" value="no"></html:hidden>
</html:form>


</td>
</tr>
</table>

</body>
<script language="javascript">

var Button;
var man1=document.getElementById("hiddenTable");
man1.style.display= "none";
//alert(" Value of stock = " + document.forms[0].stockName.value);
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

function test1(x){
		var x=document.forms[0].checkShwIndices.checked;
		alert("in test1 value of x is "+x);	    
		if(x==true){
			document.forms[0].checkShwIndices.value="on";
			document.forms[0].compute.value="no";
			retrieveURL('/Stockpile/stockDetails.do?ask=COMMAND_NAME_1','stockDetailsBean');
		}else{
			document.forms[0].checkShwIndices.value="yes";
			document.forms[0].compute.value="no";
			retrieveURL('/Stockpile/stockDetails.do?ask=COMMAND_NAME_1','stockDetailsBean');
		}		
}

function PrintThisPage() { 
	
	var sOption="toolbar=yes,location=no,directories=yes,menubar=yes,"; 
    sOption+="scrollbars=yes,width=950,height=1900,left=100,top=25"; 

   	//var sWinHTML = document.getElementById('contentstart').innerHTML; 
   	var printHead =document.getElementById('PageTitle').innerHTML;
   	//var moreDet = document.getElementById('hiddenTable').innerHTML;
   //
   		
    var winprint=window.open("","",sOption); 
    winprint.document.open(); 
    winprint.document.write('<html><body>'); 
    winprint.document.write(printHead);
    winprint.document.write('<br>');
   // winprint.document.write(moreDet);
   if(Button=="AjaxButton"){		
			var indexname = DWRUtil.getText("selectIndex");
			var stockname = DWRUtil.getText("selectStock");
			var todate = DWRUtil.getValue("to");
	  		var fromdate = DWRUtil.getValue("from");			
   			var sWinHTML = document.getElementById('Ajaxcontentstart').innerHTML; 
   			winprint.document.write("<font size=4>IndexName  :"+indexname+"</br>StockName :"+stockname+"<br> FromDate :"
   			+fromdate+"<br>   ToDate :"+todate+"</font><br>");
   			
   		}
   		else{ 
   			var sWinHTML = document.getElementById('contentstart').innerHTML;
   			var moreDet = document.getElementById('hiddenTable').innerHTML;
   			var showChart = document.getElementById('chart').innerHTML;
   			winprint.document.write(moreDet+"<br>");
   			winprint.document.write(showChart);
   		}   
    //winprint.document.write('<br>');     	
    //
    winprint.document.write(sWinHTML);         
    winprint.document.write('</body></html>');     
    winprint.document.close(); 
    winprint.focus(); 
}
	
	function indiChange(){
		//alert('inside indichange');
		document.forms[0].clear.value="no";	
		document.forms[0].defaultVal.value="no";
		document.forms[0].compute.value="no";
		document.forms[0].submit();
		//retrieveURL('/Stockpile/stockDetails.do?ask=COMMAND_NAME_1','stockDetailsBean');
		//return true;
	}
function checkdate()
{
	var objTo=document.forms[0].to;
	if((checkdatecurrent(objTo))==false)	
	{
	alert("ToDate should be Less Than CurrentDate.");
	objTo.focus();
	objTo.select();
	return false;
	}
	else return true;
}
function go() {
	var i = 0;
    var fields = new Array();
    if(document.forms[0].selectIndex.value==0){
 		fields[i++] = "Select Index is required";
	}
    if(document.forms[0].from.value==""){
		fields[i++] = "From Date is required";
	}
	if(document.forms[0].to.value==""){
		fields[i++] = "To Date is required";
	}
	if (fields.length > 0) {
        alert(fields.join('\n'));
        return false;
    }
    else {
        var x=document.forms[0].checkChart.checked;
		if(x==true){
			document.forms[0].checkChart.value="on";
		}else{
			document.forms[0].checkChart.value="yes";
		}
        document.forms[0].compute.value="yes";
		document.forms[0].defaultVal.value="no";
        retrieveURL('/Stockpile/stockDetails.do?ask=COMMAND_NAME_1','stockDetailsBean');
    }
}
	 	
function ExcelFunction() {
	document.forms[0].excel.value="yes";
}
	
function excel(url) {
	var stockNameVal = document.forms[0].stockName.value;
	alert(" stock Name = " + stockName );
	url =url+stockName;
	alert(" url = " + url);
	//return url;
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
function email(url) {
	var stockName = document.forms[0].stockName.value;
	alert(" stock Name = " + stockName );
	url = url + stockName;
	alert(" url = " + url);
	//return url;
}
/*===================================== For DWR ========================================================================*/
function getStockDetails() {
 if(checkdate()){
	  DWRUtil.useLoadingMessage();	    
	  var todate = DWRUtil.getValue("to");
	  var fromdate = DWRUtil.getValue("from");
	  var index = DWRUtil.getValue("selectIndex");  
	  var stock = DWRUtil.getValue("selectStock");
	  MoveTable.StockDetailsBetweenDate(fillTable,index, stock, fromdate, todate);
	}
}

	var stockName = function(IndexPePbDetails) { return '<font face="Verdana" color="blue" size="2">'+IndexPePbDetails.stockName+'</font>' };			
	var openVal = function(IndexPePbDetails) { return '<font face="Verdana" color="blue" size="2">'+IndexPePbDetails.openVal+'</font>'}; // if we return to using dates, add .toLocaleDateString()
	var closeVal = function(IndexPePbDetails) { return '<font face="Verdana" color="blue" size="2">'+IndexPePbDetails.closeVal+'</font>'};
	var lowVal = function(IndexPePbDetails) { return '<font face="Verdana" color="blue" size="2">'+IndexPePbDetails.lowVal+'</font>'};
	var highVal = function(IndexPePbDetails) { return '<font face="Verdana" color="blue" size="2">'+IndexPePbDetails.highVal+'</font>'};	
	var tradedVol = function(IndexPePbDetails) { return '<font face="Verdana" color="blue" size="2">'+IndexPePbDetails.tradedVol+'</font>'};
	var tradedVal = function(IndexPePbDetails) { return '<font face="Verdana" color="blue" size="2">'+IndexPePbDetails.tradedVal+'</font>'};	
	var mcv = function(IndexPePbDetails) { return '<font face="Verdana" color="blue" size="2">'+IndexPePbDetails.mcv+'</font>'};	
	var noOfTrades = function(IndexPePbDetails) { return '<font face="Verdana" color="blue" size="2">'+IndexPePbDetails.noOfTrades+'</font>'};
	var date = function(IndexPePbDetails) { return '<font face="Verdana" color="blue" size="2">'+IndexPePbDetails.date+'</font>'};
		
function fillTable(indexMovingTable) { 
	var chart = DWRUtil.getValue("checkChart");
    var a = new Array();
	a = indexMovingTable;   
 if(a.length){   	  
 	  Button = "AjaxButton"; 	
   	  changeDisplay("selectindexmessage","none");
 	  changeDisplay("nodata","none");
 	  changeDisplay("noStrutsData","none");
 	  changeDisplay("sortTabletable","none");
 	  changeDisplay("sortTable","inline"); 
   	  changeDisplay("strutslinks","none");
 	  changeDisplay("Ajaxlinks","inline");	  
	  if(chart){
	       changeDisplay("graph","block");
		   changeDisplay("strutsgraph","none"); 				
	  	   GraphMethods.stockDetails(showGraph);  	  		
	  }	  	  	
	  DWRUtil.removeAllRows("indexMovingTable");
      DWRUtil.addRows("indexMovingTable",indexMovingTable, [ stockName, openVal, closeVal, lowVal, highVal, tradedVol, tradedVal, mcv, noOfTrades, date ]);
 }
 else{ 
 	 changeDisplay("sortTable","none");
 	 changeDisplay("noStrutsData","none");
 	 changeDisplay("sortTabletable","none");
	 changeDisplay("nodata","inline");
	 changeDisplay("graph","none");
     changeDisplay("strutsgraph","none");
	 changeDisplay("selectindexmessage","none");
  }  
}

function showGraph(graphURL){ 		 	 	
 	var imgid = $("graph"); 		
 	imgid.setAttribute("src",graphURL); 	
 }
</script>

</html:html>
	
	