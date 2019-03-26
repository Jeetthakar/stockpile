<!------------------Batch Report for Index ---------------------------- -->
<!-------------neha-------------------------------------------------- -->

<%@ page import = "java.io.PrintWriter" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "java.text.ParseException" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.Locale,java.util.*" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page  import="app.*"%>
<%@ page  import="harrier.income.com.report.*"%>
<%@ page import = "java.util.*" %>
<%@ page import = "java.io.*" %>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setHeader("Expires","0");
%>
<html:html>
<html:base/>
<head>
 			<link rel="stylesheet" type="text/css" href="./StyleSheet.css"  />
			<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
			<script language="javascript" src="box_ex.js"></script>
			
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
<style type="text/css">

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

#tablist li a:link, #tablist li a:visited{
color: navy;
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
<body onload="initSort()"  >
<jsp:useBean id="batchReportBean" scope="session" class="harrier.income.com.report.BatchReportForm"/> 
<jsp:useBean id="indexCalculatorCollection" scope="session" class="harrier.income.com.report.IndexCalculatorCollection"/> 

<html:form  action="/batchReportAction">

	
	<!--- Tab to view reports---------- -->
	  	     
	    <p>
	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    </p>
	    <ul id="tablist">
					<li><a href="#" onClick="final()"><bean:message key="PreIndex.idxpepb" /></a></li>
					<li><a href="#" onClick="done()"><bean:message key="PreIndex.idxdiv" /></a></li>
					<li><a href="#" onClick="go()"><bean:message key="PreIndex.idxcmp" /></a></li>
					<li><a href="#" onClick="clean()"><bean:message key="PreIndex.currency" /></a></li>
	    </ul>  
	   
	 <TABLE border="1" color="black" width="100%">
		<TR>
		<TD bgcolor="LavenderBlush" width="100%" valign="top" height="500">
	   
	   
	
	     
			
			<!------Index Composition start ------------- -->
		<div id="contentstart" style="display:none">	
	  		
	  		 <!-- div tag for printerfriendly -->
	   	   	   	   
	<%
	  		String var1=request.getParameter("index");
	  		harrier.income.com.report.BatchReportForm brform = new harrier.income.com.report.BatchReportForm();
	  		brform.setSelectUser("1");
	    	brform.getTabledata3();
	    	var1=brform.getIndex();
	    	session=request.getSession();
			session.setAttribute("ci2Composition",brform.getVw());	  	  		
		  	String excel_path = "./FileDownloadBatch.jsp?var="+var1+"&type=1&filename=IndexCompositionReport.xls";
		  	 String xml_path ="./FileDownloadXml.jsp?var="+var1+"&type=1&filename=IndexCompositionReport.xml";
		  	String str_url = "./EmailReportBatch.jsp?switch_type=1&cas=1&rname=IndexCompositionReport.xls&varid="+var1;
	 %>
	  		<table align="right">
	  		<tr>
			 <td colspan="2" nowrap="nowrap" align="left">&nbsp;&nbsp;&nbsp;&nbsp; 
                 <font size="1" face="Verdana" color="blue">
                     <!-- Printer friendly -->
                     <a href="javascript:PrintThisPage();" ><bean:message key="LatestDivisor.printerf" /></a>
                     &nbsp;&nbsp;
                     <!-- DownLoad Excel -->
                     <a href='<%= excel_path %>'><bean:message key="LatestDivisor.downloade" /></a>
					 &nbsp;&nbsp;
					 <!-- DownLoad Xml -->
                      <a href='<%= xml_path %>'>Xml File </a>
					 &nbsp;&nbsp;
					 <!-- Email Report -->
					 <a href='<%= str_url %>' ><bean:message key="LatestDivisor.emailr" /></a>
				</font>
            </td>
		</tr>
	   </table>
	  
	  		
			<table width="1000" cellspacing="0" cellpadding="0" >
        		<tr>
		           	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          			<font size="3" face="Verdana">
		          				<b><bean:message key="IndexCompose.title" /></b> 
		          				
		          			</font>
		          	</td>
		         </tr>
			</table>
		
			<bean:define id="try1" name="batchReportBean" property="tabledata3"/>
	  		
	  		<logic:empty property="tabledata3" name="batchReportBean">
	  			<table  border="0" width="550" height="250" cellpadding="0" cellspacing="0" align="center" class="sortable">	
    		 			<tr></tr><tr>
		         			<td  bgcolor="#cacaca" align="center" valign="middle" nowrap="nowrap">
		         				<p style="margin-left: 9">
		         					<b><font face="Verdana" color="blue" size="4">
     									<bean:message key="IndexCompareOHCL.ndata" />
     								</font></b>
     						</td>
     					</tr>
  				</table>
      		</logic:empty>
      		<logic:notEmpty property="tabledata3"  name="batchReportBean">
      		 
	  		      	
        	
        	
        	<table border="0" width="800"  cellpadding="2" cellspacing="0"  align="center" id="tabular" >
	    		  <tr>
					<td>
         	<table class="sorted" ID="sortTable" 
			border="1" align="center" cellpadding="3" cellspacing="0" bgcolor="#EFEFEF">
				<thead>
					<tr>
         	
           			<th align="center"  nowrap="nowrap"  id="stockName" class="gridStyle-header">
           			<span><b><bean:message key="stockmaster.stockName" /></b></span></th>
              		<th align="right" nowrap="nowrap" id="tshares" class="gridStyle-header">
              		<span><b><bean:message key="IndexCompose.tshares" /></b></span></th>
        			<th align="right" nowrap="nowrap" id="iwf" class="gridStyle-header">
        			<span><b><bean:message key="stockmaster.iwf" /></b></span></th>
     				<th align="right" nowrap="nowrap" id="mlot" class="gridStyle-header">
     				<span><b><bean:message key="IndexCompose.mlot" /></b></span></th>
            		<th align="right" nowrap="nowrap" id="priceltp" class="gridStyle-header">
            		<span><b><bean:message key="IndexCompose.priceltp" /></b></span></th>
              		<th align="right" nowrap="nowrap" id="pricelast" class="gridStyle-header">
              		<span><b><bean:message key="IndexCompose.pricelast" /></b></span></th>
              		<th align="center" nowrap="nowrap" id="currency" class="gridStyle-header">
              		<span><b><bean:message key="IndexCompose.currency" /></b></span></th>
          			<th align="center" nowrap="nowrap" id="cerate" class="gridStyle-header">
          			<span><b><bean:message key="IndexCompose.cerate" /></b></span></th>
            		<th align="center" nowrap="nowrap" id="mcap" class="gridStyle-header">
            		<span><b><bean:message key="IndexCompose.mcap" /></b></span></th>
              		<th align="center" nowrap="nowrap" id="amarket" class="gridStyle-header">
              		<span><b><bean:message key="IndexCompose.amarket" /></b></span></th>
             		<th align="right" nowrap="nowrap" id="weight" class="gridStyle-header">
             		<span><b><bean:message key="IndexCompose.weight" /></b></span></th>
            		<th align="center" nowrap="nowrap" id="Date" class="gridStyle-header">
            		<span><b><bean:message key="corporate.Date" /></b></span></th>
          		</tr>
          		</thead>
		<tbody>
				<!-- Iterate over the table data -->
 		 	<logic:iterate id="try1" property="tabledata3" name="batchReportBean">
				<tr>
            		<td nowrap="nowrap"   align="left" class="gridStyle-odd" axis="sstring" headers="stockName"
           			title='<bean:write name="try1" property="stockname1"/>'>
            			<font face="Verdana" size="2" color="blue">
            			<a href='../masters/stockmaster2.jsp?s_stockid=<bean:write name="try1" property="stockid"/>'>
            			<bean:write name="try1" property="stockname1"/></a></font></td>
            		<td nowrap="nowrap" class="gridStyle-odd" align="right" nowrap="nowrap" axis="number" headers="tshares"
           			title='<bean:write name="try1" property="tis"/>'>
              			<font face="Verdana" size="2" color="blue">
              			<bean:write name="try1" property="tis"/></font></td>
            		<td nowrap="nowrap" class="gridStyle-odd" align="right" nowrap="nowrap" axis="number" headers="iwf"
           			title='<bean:write name="try1" property="iwf"/>'>
              			<font face="Verdana" size="2" color="blue">
              			<bean:write name="try1" property="iwf"/></font></td>
     				<td nowrap="nowrap" class="gridStyle-odd" align="right" nowrap="nowrap" axis="number" headers="mlot"
           			title='<bean:write name="try1" property="market"/>'>
           				<font face="Verdana" size="2" color="blue">
           				<bean:write name="try1" property="market"/></font></td>
            		<td nowrap="nowrap" class="gridStyle-odd" align="right" nowrap="nowrap" axis="number" headers="priceltp"
           			title='<bean:write name="try1" property="adjusted"/>'>
      					<font face="Verdana" size="2" color="blue">
      					<bean:write name="try1" property="adjusted"/></font></td>
           			<td nowrap="nowrap" class="gridStyle-odd" align="right" nowrap="nowrap" axis="number" headers="pricelast"
           			title='<bean:write name="try1" property="last"/>'>
              			<font face="Verdana" size="2" color="blue">
              			<bean:write name="try1" property="last"/></font></td>
            		<td nowrap="nowrap" class="gridStyle-odd" align="left" nowrap="nowrap" axis="sstring" headers="currency"
           			title='<bean:write name="try1" property="currency"/>'>
            			<font face="Verdana" size="2" color="blue">
            			<bean:write name="try1" property="currency"/></font></td>
            		<td nowrap="nowrap" class="gridStyle-odd" align="right" nowrap="nowrap" axis="number" headers="cerate"
           			title='<bean:write name="try1" property="curr_exch_convIratecomp"/>'>
            			<font face="Verdana" size="2" color="blue">
              			<bean:write name="try1" property="curr_exch_convIratecomp"/></font></td>
            		<td nowrap="nowrap" class="gridStyle-odd" align="right" nowrap="nowrap" axis="number" headers="mcap"
           			title='<bean:write name="try1" property="mcv"/>'>
            			<font face="Verdana" size="2" color="blue">
              			<bean:write name="try1" property="mcv"/></font></td>
            		<td nowrap="nowrap" class="gridStyle-odd" align="right" nowrap="nowrap" axis="number" headers="amarket"
           			title='<bean:write name="try1" property="mcv"/>'>
            			<font face="Verdana" size="2" color="blue">
              			<bean:write name="try1" property="mcv"/></font></td>
            		<td nowrap="nowrap" class="gridStyle-odd" align="right" nowrap="nowrap" axis="number" headers="weight"
           			title='<bean:write name="try1" property="strweightage"/>'>
            			<font face="Verdana" size="2" color="blue">
              			<bean:write name="try1" property="strweightage"/></font></td>
           			<td nowrap="nowrap" class="gridStyle-odd" align="right" nowrap="nowrap" axis="date" headers="Date"
           			title='<bean:write name="try1" property="stockprice"/>'>
           				<font face="Verdana" size="2" color="blue">
              			<bean:write name="try1" property="stockprice"/></font></td>
           		</tr>
			</logic:iterate>
			<tr>
 			<td colspan="10" nowrap="nowrap" align="right"  class="gridStyle-odd"  >
	 			<font face="Verdana" size="2" color="blue">
 					<bean:message key="StockPerformance.Total" />
 				</font>
 			</td> 
			<td  align="right" nowrap="nowrap" class="gridStyle-odd" >
				<font face="Verdana" size="2" color="blue">
					<bean:write name="batchReportBean" property="total"/>000
				</font>
							
			</td>
			
			<td width="100" align="right" nowrap="nowrap" class="gridStyle-odd" >
			
			</td>
		</tr>
			 </tbody>
			</table> 
			</td>
			</tr>
			
			</tr>
			</table>
			</logic:notEmpty> 
			
 		
	</div>	
	
	
	<!----------- Start of Index wise PE/PB --------- -->
	<div id="table" style="display:none" >	
	
	<%
	  		harrier.income.com.report.BatchReportForm brform1 = new harrier.income.com.report.BatchReportForm();
	    	brform1.setSelectUser("1");
	    	brform1.getIndex_arraylist();
	    	String var20=brform1.getIndex();
	    	
	    	session=request.getSession();
			session.setAttribute("ci2pepb",brform1.getVExcel());
			String fdate=brform1.getFrom();
			String tdate=brform1.getTo();
					
       	String excel20 = "./FileDownloadBatch.jsp?var="+var20+"&from="+fdate+"&to="+tdate+"&type=20&filename=IndexPe_Pb.xls";
       	String str_url20 = "./EmailReportBatch.jsp?switch_type=20&from="+fdate+"&to="+tdate+"&cas=20&rname=IndexPe_Pb.xls&varid="+var20;
   	    
   	    %>
   	    
   	   
	   
   	   
	<table width="1000" cellspacing="0" cellpadding="0" >
		<tr>    
			<td colspan="2" nowrap="nowrap" align="right">&nbsp;&nbsp;&nbsp;&nbsp; 
                 <font size="1" face="Verdana" color="blue">
                     <!-- Printer friendly -->
                     <a href="javascript:PrintThisPage20();" ><bean:message key="LatestDivisor.printerf" /></a>
                     &nbsp;&nbsp;
                     <!-- DownLoad Excel -->
                     <a href='<%= excel20 %>'><bean:message key="LatestDivisor.downloade" /></a>
					 &nbsp;&nbsp;
					 <!-- Email Report -->
					 <a href='<%= str_url20 %>' ><bean:message key="LatestDivisor.emailr" /></a>
				  </font>
            </td> 
         </tr>
         <tr>        	
		     <td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          			<font size="3" face="Verdana">
		          				<b><bean:message key="Indexpe.title" /> </b>
		          			</font>
		     </td>
		 </tr> 
	</table> 
	
	<logic:empty property="index_arraylist" name="batchReportBean">
    	    <br><br>
			   <table  border="0" width="550" height="250" cellpadding="0" cellspacing="0" align="center" class="sortable">	
    		 			<tr></tr><tr>
		         			<td  bgcolor="#cacaca" align="center" valign="middle" nowrap="nowrap">
		         				<p style="margin-left: 9">
		         					<b><font face="Verdana" color="blue" size="4">
     									<bean:message key="IndexCompareOHCL.ndata" />
     								</font></b>
     						</td>
     					</tr>
  			   </table>
   	    </logic:empty>
   	    
	<logic:notEmpty property="index_arraylist"  name="batchReportBean"> 
	         
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
	      <logic:iterate id="pepb" name="batchReportBean" property="index_arraylist">
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
	
	</logic:notEmpty>
	
	
	</div>
         <!--------Start of Index Divisor ------- -->	
	
	
	<div id="divisor" style="display:none"> 
	
	<%
		harrier.income.com.report.BatchReportForm brform2 = new harrier.income.com.report.BatchReportForm();
	    	brform2.setSelectUser("1");
	    	brform2.getTableData2();
	    		
		    String var19=brform2.getIndex();
			session=request.getSession();
			session.setAttribute("selectIndex19",brform2.getIndex());
			Vector vVExcel= brform2.getVExcel();
			session.setAttribute("ci2divisor",vVExcel);
			//session.setAttribute("selectIndex19",var19);
			String fdate19=brform2.getFrom();
			String tdate19=brform2.getTo();
			session.setAttribute("fdate19",fdate19);
			session.setAttribute("tdate19",tdate19);
				    	
			 String temp_path19 = "./FileDownloadBatch.jsp?&type=19&filename=IndexDivisor.xls";
             //String temp_path19 = "./FileDownloadBatch.jsp?&type=19&from="+fdate19+"&to="+tdate19+"&filename=IndexDivisor.xls";
             String email_url19 = "./EmailReport.jsp?switch_type=19&cas=19&rname=IndexDivisor.xls";
             
             
	  		%>
	  			
	  		<table align="right">
	  		
	   </table>
	
			
	
	  <table width="1000" cellspacing="0" cellpadding="0" > <!-- Table for Displaying Title -->
	  
	  		<tr>
			 <td nowrap="nowrap" align="right">&nbsp;&nbsp;&nbsp;&nbsp; 
			  
                 <font size="1" face="Verdana" color="blue">
                   
                     <a href="javascript:PrintThisPage19();" ><bean:message key="LatestDivisor.printerf" /></a>
                     &nbsp;&nbsp;
                    
                     
                     <a href='<%= temp_path19 %>'><bean:message key="LatestDivisor.downloade" /></a>     
					 &nbsp;&nbsp;
					 
					 <a href='<%= email_url19 %>'><bean:message key="LatestDivisor.emailr"/></a>    
				</font>
            </td>
			</tr>
        		<tr>
		          	
		          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          			<font size="3" face="Verdana">
		          				<b><bean:message key="IndexDivisor.title" /></b>
		          			</font>
		         	</td>
	          	</tr>
	 </table>
	  		
	 <bean:define id="details" name="batchReportBean" property="tableData2"/>
	  		<bean:size id="dataCount" name="batchReportBean" property="tableData2"/>
	  			<logic:equal value="0" name="dataCount" >
	  				 <table  border="0" width="550" height="250" cellpadding="0" cellspacing="0" align="center" class="sortable">	
    		 			<tr></tr><tr>
		         			<td  bgcolor="#cacaca" align="center" valign="middle" nowrap="nowrap">
		         				<p style="margin-left: 9">
		         					<b><font face="Verdana" color="blue" size="4">
     									<bean:message key="IndexCompareOHCL.ndata" />
     								</font></b>
     						</td>
     					</tr>
  			   </table>
      			</logic:equal>
    
     <logic:notEqual value="0" name="dataCount"> 
     
	
	     
     		<table border="0" width="85%"  align="left" cellpadding="2" cellspacing="0" >
     	  		
	      	<tr>
	      		<td>
	      		   <table class="sorted" ID="sortTable" 
			border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#EFEFEF">
				<thead>
					<tr>
	       			<!-- <table border="1" width="50%" bgcolor="white"  align="center" cellpadding="2" cellspacing="0" id="t1" class="sortable" bordercolor="#EFEFEF">
	          	 		<tr> -->
	            		    <th width="15%" nowrap="nowrap" align="center" id="trddate">
	            		    	<span><b><bean:message key="IndexDivisor.trddate" /></b></span>
	            		    </th>
	            		    <th width="15%" nowrap="nowrap" align="center" id="close">
	            		    	<span><b><bean:message key="IndexCompareOHCL.close" /></b></span>
	            		    </th>
	            		    <th width="15%" nowrap="nowrap" align="center" id="mcp">
	            		    	<span><b><bean:message key="IndexDivisor.mcp" /></b></span>
	            		    </th>
	            		    <th width="15%" nowrap="nowrap" align="center" id="divisor">
	            		    	<span><b><bean:message key="indcurrwise.divisor" /></b></span>
	            		    </th>
	            	    </tr>
	            </thead>
				<tbody>
	           		<logic:iterate id="details" name="batchReportBean" property="tableData2">
     					<tr>
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
          	 		</logic:iterate>
          	 		 </tbody>
          	 		</table>
          	 	</td>
          	</tr>          	
         </table>
         
     </logic:notEqual> 
	 
   </div>
   
   
   
   <div id="currencyreport" style="display:inline">
   
   		<table width="1000" cellspacing="0" cellpadding="0" >
        		<tr>
		          	
		          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          		<font size="3" face="Verdana">
		          			<b><bean:message key="IndexCurrencyWise.title" /> </b>
		          		</font>
		         	</td> 
	          	</tr>
		</table> 
		
    
     	
     
		<table border="0" width="100%" class="gridStyle" cellspacing="0" cellpadding="0">
   			<tr> 
   			<%    
			harrier.income.com.report.BatchReportForm xyz= new harrier.income.com.report.BatchReportForm();
			xyz.setSelectUser("1");
	 		String var=xyz.getCurrencyParam();
   			log.info("var is "+var);
           		if(var==null){
           		
   					var="1";
   				} 
   			%>   	
   			<%                       
               
               if(var!=null){
                    log.info("var is "+var);
                    indexCalculatorCollection.addStocksInIndexCalculatorTablePrice(var,request);
               }
            %>  
                                         
   			</tr>  
            <%                                              
               if(var!=null) {
                    indexCalculatorCollection.addInIndexCurrencyWise(var,request);
               } else { var="1";  }
             
             %>  
             <br></br>                 
  
        	<tr><td><br></br></td></tr>
       	</table>
      	
      	
		
    	<table border="1" width="100%" class="gridStyle" cellspacing="0" cellpadding="0">
 			   
    		<tr>
       		  <td width="100%">
      			<table border="1" width="100%" class="gridStyle" cellspacing="0" cellpadding="0">
      	
         			<tr>   
         				<td width="5%" align="center" class="griStyle-header"><input type="checkbox" name="currid" onclick="CheckAll(this)"/></td>    
          				<td width="30%" align="center" class="gridStyle-header"><bean:message key="indcurrwise.currencyName"/></td>
           				<td width="23%" align="center" class="gridStyle-header"><bean:message key="indcurrwise.tmcv" /></td>
           				<td width="22%" align="center" class="gridStyle-header"><bean:message key="indcurrwise.divisor" /></td>           
           				<td width="20%" align="center" class="gridStyle-header"><bean:message key="indcurrwise.indValue" /></td>    
           			</tr> 
           	<%     
           		 StringBuffer str=null;
           		 str = indexCalculatorCollection.addRowsInIndexCurrencyWise(indexCalculatorCollection.table,request);     
           		 if(str!=null){
            %>
				<%=str.toString()%>
         	<%   } %> 
           		</table>
           	  </td>
           	</tr>
       </table>
       
   </div>
           
   </TD></TR>
   </TABLE>
   <div id="hiddenTable">		
	 	<table width="100%"  align="left" >
	 		<tr>
				<td width="125" align="right">
					<font size="2" face="Verdana"><b>
    					<bean:message key="defineIndex7" />&nbsp;:</b>
        			</font>
   				</td>
   				<td>
   					<bean:write name="batchReportBean" property="index_name2"/>
   				</td> 
   			</tr>
	     	<tr>
				<td width="125" align="right">
					<font size="2" face="Verdana"><b>
    					<bean:message key="corporate.Fdate" />&nbsp;:</b>
        			</font>
   				</td>
   				<td>
   					<bean:write name="batchReportBean" property="from"/>
   				</td> 
   				</tr>
				<tr>						
					<td width="125" align="right">
						<font size="2" face="Verdana"><b>
    						<bean:message key="corporate.Tdate" />&nbsp;:</b>
         				</font>
   					</td>
   					<td>
   						<bean:write name="batchReportBean" property="to"/>
   					</td> 
				</tr>			
          	</table>        
      </div>
      <div id="hiddenTable1">		
	 	<table align="left" >
	 		<tr>
				<td width="125" align="right">
					<font size="2" face="Verdana"><b>
    					<bean:message key="defineIndex7" />&nbsp;:</b>
        			</font>
   				</td>
   				<td>
   					<bean:write name="batchReportBean" property="index_name2"/>
   				</td> 
   				</tr>
	     			
          	</table>        
        </div>
<html:hidden property="dataCount" ></html:hidden>
<html:hidden property="itype" ></html:hidden>
</html:form> 
</body>

<script  language="javascript">
var man1=document.getElementById("hiddenTable");
var man2=document.getElementById("hiddenTable1");

	man1.style.display= "none"; 
	man2.style.display= "none"; 
var itype=0;
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
		var newwindow;
		
	function go() 
	{
	  
	  changeDisplay("table","none");
	  changeDisplay("divisor","none");
	  changeDisplay("currencyreport","none");	
	  changeDisplay("contentstart","inline");
	  document.forms[0].itype.value=1;	
	}
	function final() 
	{
	  changeDisplay("contentstart","none");
	  changeDisplay("divisor","none");
	  changeDisplay("currencyreport","none"); 
	  changeDisplay("table","inline");
	  document.forms[0].itype.value=20;
	  
	
	}
	function done() 
	{
	 
	 changeDisplay("contentstart","none");
	 changeDisplay("table","none");
	 changeDisplay("currencyreport","none");
	 changeDisplay("divisor","inline");
	 document.forms[0].itype.value=19;	
	
	}
	
	function clean() 
	{
	  
	  changeDisplay("contentstart","none");
	  changeDisplay("table","none");
	  changeDisplay("divisor","none");
	  changeDisplay("currencyreport","inline"); 	
		
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

function PrintThisPage() 
	{ 
		
   		var sOption="toolbar=yes,location=no,directories=yes,menubar=yes,"; 
       	sOption+="scrollbars=yes,left=50,top=25"; 
		//var iname=document.forms[0].index_name2.value;
   		var sWinHTML = document.getElementById('contentstart').innerHTML; 
   		//var printHead =document.getElementById('PageTitle').innerHTML;
   		var moreDet = document.getElementById('hiddenTable1').innerHTML;
   		
   		var winprint=window.open("","",sOption); 
       	winprint.document.open(); 
       	winprint.document.write('<html><body>'); 
       	//winprint.document.write(printHead);
       	winprint.document.write('<br>');
       	winprint.document.write('&nbsp;&nbsp;&nbsp;'); 
      	winprint.document.write('&nbsp;&nbsp;&nbsp;'); 
      	winprint.document.write('<br>');
       	winprint.document.write(moreDet);
       	winprint.document.write('<br>');
       	winprint.document.write(sWinHTML); 
       	if(checkChart=="on"){
              	var chrt=document.getElementById('chart').innerHTML;
               	winprint.document.write(chrt); 
        }
       	winprint.document.write('</body></html>'); 
       	
       	winprint.document.close(); 
       	winprint.focus(); 
	}


function PrintThisPage19() 
{ 
	var sOption="toolbar=yes,location=no,directories=yes,menubar=yes,"; 
    sOption+="scrollbars=yes,width=850,height=550,left=100,top=25"; 
	var sWinHTML = document.getElementById('divisor').innerHTML; 
   //	var printHead =document.getElementById('PageTitle').innerHTML;
	var moreDet = document.getElementById('hiddenTable').innerHTML;
   	var winprint=window.open("","",sOption); 
    winprint.document.open(); 
    winprint.document.write('<html><body>'); 
    //winprint.document.write(printHead);
    winprint.document.write('<br>');
    winprint.document.write(moreDet); 
 	winprint.document.write('<br>');
    winprint.document.write('<br>');
    winprint.document.write('<br>');
    winprint.document.write('<br>');
    winprint.document.write('<br>');
    winprint.document.write(sWinHTML);         
    winprint.document.write('</body></html>'); 
   	winprint.document.close(); 
    winprint.focus(); 
}

function PrintThisPage20() 
	{ 
 		//var ttle =document.getElementById('title').innerHTML;
   		var tble = document.getElementById('table').innerHTML;
   		
   		var winprint=window.open("","",""); 
      	winprint.document.open(); 
        //winprint.document.write(ttle);
      	winprint.document.write('<br>');
      	winprint.document.write('<br>');
	   	winprint.document.write('<br>');
       	winprint.document.write(tble); 
       	winprint.document.close(); 
       	winprint.focus(); 
	}

</script>    
</html:html>