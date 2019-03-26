<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<html:html>
<html:base/>
<head>
	<html:base/>
	<title></title>
	<link href="StyleSheet.css" rel="stylesheet" type="text/css">
	<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
	<script language="javascript" src="box_ex.js"></script>
	<script language="javascript">
		var c2 = new CodeThatCalendar(caldef2);
	</script>
	<script type="text/javascript" src="./sorttable.js"></script>
	<style type="text/css">	
		/* Sortable tables */
		table.sortable a.sortheader {
    		background-color:#eee;
   	 		font-size: 13px; 
    		font-family: Arial;
    		color: black;
    		text-decoration: none;
    		display: block;
		}
		table.sortable span.sortarrow {
    		color: black;
    		text-decoration: none;
		}
	</style>
	

</head>
<body>
<div id="PageTitle">
	<table width="1000" cellspacing="0" cellpadding="0" >
    	<tr>
			<td width="250" class="subHeader" nowrap="nowrap">
		    	&nbsp;
		  	</td>
		    <td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		    	<font size="3" face="Arial">
		        	<b><bean:message key="TradeVolumeInd.title" /> </b>
		      	</font>
		  	</td> 
	   	</tr>
	</table> 
</div>

<html:form action="/TradedVolumeInd_exchS">
	<table>
		<tr>
	  		<td width="116" nowrap="nowrap" align="left">&nbsp;</td>
	  		<td width="125" nowrap="nowrap" align="left">
				<font size="2" face="Arial"><bean:message key="TradeVolumeInd.Filter" />: 
				</font>
        	</td>     
        	<td width="504" nowrap="nowrap" align="left" height="30">
        		<html:select size="1" property="filter" onchange="document.forms[0].submit();return true" >
        			<html:option value="0"><bean:message key="StockPerformance.notsel" /></html:option>
        			<html:option value="1"><bean:message key="TradeVolumeInd.Wise" /></html:option>
        			<html:option value="2"><bean:message key="TradeVolumeInd.Index" /></html:option>
        		</html:select>
        	</td>
        </tr>	
	</table>
	
	<!-- Table for cut volume textbox -->
	<table>
		<tr>
	  		<td width="116" nowrap="nowrap" align="left">&nbsp;</td>
	  		<td width="125" nowrap="nowrap" align="left">
				<font size="2" face="Arial"><bean:message key="TradeVolumeInd.Cut" />: 
				</font>
        	</td>     
        	<td width="504" nowrap="nowrap" align="left" height="30"> 
        		<html:text property="traded_volume" ></html:text>
        	</td>
        </tr>
	</table>    
	
	
	<logic:notEqual value="0" parameter="filter">
	<!-- Table to display exch name or index Name -->
	<table>
		<tr>
			<td width="116" nowrap="nowrap" align="left">&nbsp;</td>
			<td width="125" nowrap="nowrap">
				<logic:equal value="1" parameter="filter">
					<font size="2" face="Arial"><bean:message key="TradeVolumeInd.Stock" />: </font>
				</logic:equal>
				<logic:equal value="2" parameter="filter">
					<font size="2" face="Arial"><bean:message key="TradeVolumeInd.Name" /></font>
				</logic:equal>
			</td>
			<td align="left">
				<html:select size="1" property="selectIndExch" onchange="" styleId="sIE">
					<html:optionsCollection property="selectIndExchCollection" name="TradedVolumeBean"/>
				</html:select>
			</td>
		</tr>
	</table>
	</logic:notEqual>
		
	<!-- Table for To and From date -->
	<table>
	 	<tr>
	 		<td width="116" nowrap="nowrap" align="left">&nbsp;</td>
	 		<logic:equal value="2" parameter="filter">
	 		<td  align="right" nowrap="nowrap">
        		<html:checkbox property="checkShwIndices" onclick="test1()" ></html:checkbox>
        	</td>	
        	<td nowrap="nowrap" align="left">	
				<font size="2" face="Arial">  
	  				<bean:message key="IndexComparision.showsi" />
	  			</font>&nbsp;
	  		</td>
	 		</logic:equal>
	 		<td width="100" align="right"><font size="2" face="Arial">
            		<bean:message key="corporate.Fdate" />&nbsp;:
            	</font>
           	</td> 
           	<td nowrap="nowrap">
             	<html:text property="from" readonly="true" size="10"/>
            </td>
            <td  nowrap="nowrap" align="left"> 
				<html:button property="shwFrom" value="..." onclick="c2.popup('from');"/>&nbsp;&nbsp;&nbsp;
          	</td>
            <td nowrap="nowrap" align="right"><!-- To date-->
           		<font size="2" face="Arial">
                	<bean:message key="corporate.Tdate" />&nbsp;:
                </font>
            </td>
            <td nowrap="nowrap">
            	<html:text property="to" readonly="true" size="10"/>
            </td>
            <td  nowrap="nowrap" align="left">
				<html:button property="shwTo" value="..." onclick="c2.popup('to');"/>
          	</td>
            <td nowrap="nowrap"> <!-- View -->
            	<html:submit onclick="return go();" onkeypress="return go();"><bean:message key="Reports.View"/></html:submit>&nbsp;&nbsp;
			</td>
		</tr>
	</table>
	
	<logic:equal  value="yes" parameter="compute" >	<!-- if compute ="yes" display links -->
	<%
	
		String indExch= request.getParameter("selectIndExch");
	  	String var=request.getParameter("filter");
	  	String to = request.getParameter("to");
	  	String from = request.getParameter("from");
	  	String excel_path = "../pages/FileDownload.jsp?var="+var+"&type=25&filename=TradedVolume.xls&from="
	  			+from+"&to="+to+"&indExch="+indExch;
	  	String str_url = "../pages/EmailReport.jsp?switch_type=25&cas=25&varid="+var+"&rname=TradedVolume.xls&from="
	  				+from+"&to="+to+"&indExch="+indExch;
	%>
	<table>
		<tr>
	 		<td  width="212" align="right" nowrap="nowrap"> 
         	</td>
         	
         	<!-- Links  -->
	 		<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 			<!-- Printer friendly -->
	 			<a href="javascript:PrintThisPage()" ><font size="1" > <bean:message key="LatestDivisor.printerf" /></font></a>&nbsp;
	 			
	 			<!-- DownLoad Excel -->
	 			<a href='<%= excel_path %>'  ><font size="1" > <bean:message key="LatestDivisor.downloade" /></font></a>&nbsp;
	 			
	 			<!-- Email Report -->
	 			<a href='<%= str_url %>' ><font size="1" > <bean:message key="LatestDivisor.emailr" /></font></a>&nbsp;
	 			
	 		</td> 	
	  	</tr>
	</table>
	</logic:equal>


	<logic:notEqual value="yes" parameter="compute">
	
	<table border="0" align="left" width="700" height="222" cellspacing="0" cellpadding="0">
		<tr>
        	<td width="99"></td>
			<td class="gridStyle-message" align="center" valign="middle">
          		<p style="margin-left: 9"><bean:message key="TradeVolumeInd.View" /></p>
          	</td>
      	</tr>
  	</table>
	</logic:notEqual>
	
	<logic:equal  value="yes" parameter="compute" >
   	  
	  	<bean:define id="details" name="TradedVolumeBean" property="tableData"/>
	  	<bean:size id="dataCount" name="TradedVolumeBean" property="tableData"/>
	  		
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
        <div id=contentstart> 
    	<table border="1" width="60%" align="center" cellpadding="2" cellspacing="0" id="t1" class="sortable" bordercolor="#EFEFEF">
        	<tr>
        		<!-- Table Heading -->
          		<td nowrap="nowrap" align="center"><bean:message key="stockmaster.stockName" /></td>
          		<td align="center"><bean:message key="TradeVolumeInd.Traded" /></td>
		   	</tr>   		
          	
          	<!-- Iterate over the table data -->
          	<logic:iterate id="details" name="TradedVolumeBean" property="tableData">
          	
          	<tr>
     			<td valign="middle" bgcolor="white" align="left" >
        			<a href='../pages/stockmaster2.jsp?s_stockid=<bean:write name="details" property="stockId"/>'>
        			<font face="Arial" size="2" color="blue"><bean:write name="details" property="stockName"/>
        			</font></a>
        		</td>
          		<td align="right" bgcolor="white" valign="middle">
          			<font face="Arial" size="2" color="blue"><bean:write name="details" property="tradedVol"/></font>
          			
          		</td>
         	</tr>
    		</logic:iterate>
          
        </table> 
        </div>
    	</logic:notEqual>
   	</logic:equal>

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
	<td><font size="2" face="Arial"><b>&nbsp;&nbsp;&nbsp;
    	<bean:write name="TradedVolumeBean" property="indExchName" /></b>
         </font>
	</td>
	</tr>
	<tr>
	<td width="125" align="right"><font size="2" face="Arial"><b>
    	<bean:message key="corporate.Fdate" />&nbsp;:</b>
         </font>
   	</td>
   	<td><font size="2" face="Arial">
   		<bean:write name="TradedVolumeBean" property="from"/>
   		</font>
   	</td> 
	</tr>
	<tr>
	<td width="125" align="right"><font size="2" face="Arial"><b>
    	<bean:message key="corporate.Tdate" />&nbsp;:</b>
         </font>
   	</td>
   	<td><font size="2" face="Arial">
   		<bean:write name="TradedVolumeBean" property="to"/>
   		</font>
   	</td> 
	</tr>
</table>

</div>
	
 <html:hidden property="indExchName" ></html:hidden>	
 <html:hidden property="compute" value="no"></html:hidden>
 <html:hidden property="clear"></html:hidden> 
</html:form>
</body>
<script language="javascript">
var man1=document.getElementById("hiddenTable");
man1.style.display= "none"; 


function go() {
	document.forms[0].compute.value="yes";
	var i =0;
	var fields = new Array();
	if(document.forms[0].filter.value=="0"){
			fields[i++] = "Select Filter is required";
		}
		if(document.forms[0].selectIndExch.value==""){
			fields[i++] = "Select(Index/Exchange) is required";
		}
					
		if(document.forms[0].from.value==""){
			fields[i++] = "From Date is required";
		}
		
		if(document.forms[0].to.value=="") {
			fields[i++] = "To Date is required";
		}
		
		 if (fields.length > 0) {
                   alert(fields.join('\n'));
                   return false;
         }
         else {
         	return true;
         }
}

function PrintThisPage() { 
	var sOption="toolbar=yes,location=no,directories=yes,menubar=yes,"; 
    sOption+="scrollbars=yes,width=750,height=600,left=100,top=25"; 

   	var sWinHTML = document.getElementById('contentstart').innerHTML; 
   	var printHead =document.getElementById('PageTitle').innerHTML;
   	var moreDet = document.getElementById('hiddenTable').innerHTML;
   		
    var winprint=window.open("","",sOption); 
    winprint.document.open(); 
    winprint.document.write('<html><LINK href=StyleSheet.css rel=Stylesheet><body>'); 
    winprint.document.write(printHead);
    winprint.document.write('<br>');
    winprint.document.write(moreDet); 
    	
    winprint.document.write(sWinHTML); 
    //winprint.document.write(moreDet);          
    winprint.document.write('</body></html>'); 
    winprint.document.close(); 
    winprint.focus(); 
}

function test1(){
		
 document.forms[0].submit();
 return true;
}
</script>
</html:html>
