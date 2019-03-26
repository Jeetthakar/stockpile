
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ page language="java" import="harrier.income.com.report.*"%>
<%@ page import = "java.io.PrintWriter" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "java.text.ParseException" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.Locale,java.util.*" %>
<%@ page  import="app.*"%><%@page import="org.apache.log4j.Logger"%>
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
			<title></title>
			<link href="StyleSheet.css" rel="stylesheet" type="text/css">
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
	
<body>
<html:form action="/batchReportAction">  
<link href="./StyleSheet.css" rel="stylesheet" type="text/css">
<table width="1000" cellspacing="0" cellpadding="0" >
        		<tr>
		          	
		          	<td class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          			<font size="3" face="Arial">
		          				<b><bean:message key="BatchReport.title"/></b>
		          			</font>
		         	</td> 
	          </tr>
</table>
<p></p>
		<table width="600">
			
  			<tr>
			<td width="175" >
			<font size="2" face="Verdana">
			<bean:message key="BatchReport.prefrence" />
			</font>
			</td>
			<td>
			<html:select property="selectUser"  size="1" styleId="sIE" onchange="go()">
				<html:optionsCollection property="selectUserCollection" name="batchReportBean"/>
			</html:select>
			</td>
			</tr>
			<tr>
			
   					<!-- <td align="left" height="69" class="tab" nowrap="nowrap"> -->
  					&nbsp;<td align="left" colspan="3" height="69" class="tab" nowrap="nowrap">
  					<fieldset style="width: 202px; height: 52px; padding: 2">
					<legend><bean:message key="BatchReport.prehead" /></legend>
					<html:radio property="radioButton" name="batchReportBean" value="all" onclick="check2()" />
					<bean:message key="BatchReport.all" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					<html:radio property="radioButton" name="batchReportBean" value="some" onclick="check1()"/>
					<bean:message key="BatchReport.some" /></fieldset>
				 <!--
   				   <td align="right" nowrap="nowrap"></td>
  					<td align="center" width="6%" nowrap="nowrap"></td>  					
  					<td align="center" nowrap="nowrap"></td> -->
  				</tr>	
			
		 </table>
		 <logic:equal value="yes" property="vanish" name="batchReportBean">
		 <table width="100%">
		 <tr>
		     <td width="150" align="left"><font size="2" face="Verdana">
			Select Report
			</font></td>
		    <td width="150"> <html:select property="selectReport"  size="2" styleId="sIE" onchange="" multiple="true">
				<html:optionsCollection property="selectReportCollection" name="batchReportBean" />
			</html:select>
			</td>
			<td> 
			<html:submit property="view" onclick="done()"><bean:message key="BatchReport.view" /></html:submit>
			</td>
			</tr>
		 	 </table>
		  </logic:equal>
	<p></p>	
	<!---   To view all reports under one preference   ---------- -->
	<logic:equal value="yes" property="compute" name="batchReportBean">
		 	
	<table align="left">
	     <tr>
	            <td nowrap="nowrap" align="right">
					<html:checkbox property ="checkdate"  /> &nbsp;
				</td>
				<td width="69" nowrap="nowrap"><font size="2" face="Verdana" >
        	    	  <bean:message key="corporate.Fdate" /></font>
    	        </td> 
	        	<td width="78" nowrap="nowrap">
					<html:text property="fromd" size="10" />
				</td>
				<td width="37" nowrap="nowrap">    
        	         <html:button property="shwf" value="..." onclick="c2.popup('fromd');"/>
            	</td>
			
			    <td width="58" nowrap="nowrap"><font size="2" face="Verdana">
                        <bean:message key="corporate.Tdate" /></font>
    	        </td>

        	    <td width="79" nowrap="nowrap">	
					<html:text property="tod" size="10"   />
				</td>

				<td width="37" nowrap="nowrap">    
        	         <html:button property="shwt" value="..." onclick="c2.popup('tod');" />
            	</td>
          	</tr>
       </table>	
         <br><br><br>
	   
		 <table>
			<logic:iterate id="BatchReportDetails" name="batchReportBean" property="tableData">
			<tr>
						
	   		   					
    		   		<td class="gridStyle-odd" width="20%" nowrap="nowrap" align="left" bgcolor="#EFEFEF">
    				   	<font face="Arial" color="blue" size="2">
    				   		 <bean:write name="BatchReportDetails" property="filePaths"/>
						</font>
				  	</td>
				  	
	   		   		
	   		   		
	   		   		<td class="gridStyle-odd" width="15%" nowrap="nowrap" align="left" bgcolor="#EFEFEF">
    				   	<font face="Arial" color="blue" size="2">
    				   		<bean:message key="BatchReport.from" />&nbsp;
		  			 		<html:text name="BatchReportDetails" size="10" readonly="true" property="fdate"/>
		  			 	</font>
				  	</td>
				  	
				  	<td class="gridStyle-odd" width="15%" nowrap="nowrap" align="left" bgcolor="#EFEFEF">
    				   	<font face="Arial" color="blue" size="2">
    				   		<bean:message key="BatchReport.to" />&nbsp;
		  			 		<html:text name="BatchReportDetails" size="10" readonly="true" property="tdate"/>
		  			 	</font>
				  	</td>
				  	
    		   		<td class="gridStyle-odd" width="30%" nowrap="nowrap" align="left" bgcolor="#EFEFEF">
    				   	<font face="Arial" color="blue" size="2">
    				   		<bean:message key="BatchReport.list1" />&nbsp;
		  			 		<html:text name="BatchReportDetails" readonly="true" property="index_name1"/>
		  			 	</font>
				  	</td>
				  	
				  	<td class="gridStyle-odd" width="30%" nowrap="nowrap" align="left" bgcolor="#EFEFEF">
    				   	<font face="Arial" color="blue" size="2">
    				   		<bean:message key="BatchReport.list2" />&nbsp;
		  			 		<html:text name="BatchReportDetails" readonly="true" property="list2"/>
		  			 	</font>
				  	</td>				  	
				  	<td class="gridStyle-odd" width="30%" nowrap="nowrap" align="left" bgcolor="#EFEFEF">
    				   	<font face="Arial" color="blue" size="2">
    				   		<bean:message key="BatchReport.list3" />&nbsp;
		  			 		<html:text name="BatchReportDetails" readonly="true" property="list1"/>
		  			 	</font>
				  	</td>
		  	
			   </tr>
           </logic:iterate>	
             </table>
         </logic:equal>
         <!--------- To view selected reports under one preference -->
         
       <logic:equal value="yes" property="fin" name="batchReportBean">
         <table align="left">
	     <tr>
	            <td nowrap="nowrap" align="right">
					<html:checkbox property ="checkdate"  /> &nbsp;
				</td>
				<td width="69" nowrap="nowrap"><font size="2" face="Verdana" >
        	    	  <bean:message key="corporate.Fdate" /></font>
    	        </td> 
	        	<td width="78" nowrap="nowrap">
					<html:text property="fromd" size="10" />
				</td>
				<td width="37" nowrap="nowrap">    
        	         <html:button property="shwf" value="..." onclick="c2.popup('fromd');"/>
            	</td>
			
			    <td width="58" nowrap="nowrap"><font size="2" face="Verdana">
                        <bean:message key="corporate.Tdate" /></font>
    	        </td>

        	    <td width="79" nowrap="nowrap">	
					<html:text property="tod" size="10"   />
				</td>

				<td width="37" nowrap="nowrap">    
        	         <html:button property="shwt" value="..." onclick="c2.popup('tod');" />
            	</td>
          	</tr>
       </table>	
         <br><br><br>
         <table>
			<logic:iterate id="BatchReportDetails" name="batchReportBean" property="tableDatnew">
			<tr>
						
	   		   					
    		   		<td class="gridStyle-odd" width="20%" nowrap="nowrap" align="left" bgcolor="#EFEFEF">
    				   	<font face="Arial" color="blue" size="2">
    				   		 <bean:write name="BatchReportDetails" property="filePaths"/>
						</font>
				  	</td>
				  	
	   		   		
	   		   		
	   		   		<td class="gridStyle-odd" width="15%" nowrap="nowrap" align="left" bgcolor="#EFEFEF">
    				   	<font face="Arial" color="blue" size="2">
    				   		<bean:message key="BatchReport.from" />&nbsp;
		  			 		<html:text name="BatchReportDetails" size="10" readonly="true" property="fdate"/>
		  			 	</font>
				  	</td>
				  	
				  	<td class="gridStyle-odd" width="15%" nowrap="nowrap" align="left" bgcolor="#EFEFEF">
    				   	<font face="Arial" color="blue" size="2">
    				   		<bean:message key="BatchReport.to" />&nbsp;
		  			 		<html:text name="BatchReportDetails" size="10" readonly="true" property="tdate"/>
		  			 	</font>
				  	</td>
				  	
    		   		<td class="gridStyle-odd" width="30%" nowrap="nowrap" align="left" bgcolor="#EFEFEF">
    				   	<font face="Arial" color="blue" size="2">
    				   		<bean:message key="BatchReport.list1" />&nbsp;
		  			 		<html:text name="BatchReportDetails" readonly="true" property="index_name1"/>
		  			 	</font>
				  	</td>
				  	
				  	<td class="gridStyle-odd" width="30%" nowrap="nowrap" align="left" bgcolor="#EFEFEF">
    				   	<font face="Arial" color="blue" size="2">
    				   		<bean:message key="BatchReport.list2" />&nbsp;
		  			 		<html:text name="BatchReportDetails" readonly="true" property="list2"/>
		  			 	</font>
				  	</td>				  	
				  	<td class="gridStyle-odd" width="30%" nowrap="nowrap" align="left" bgcolor="#EFEFEF">
    				   	<font face="Arial" color="blue" size="2">
    				   		<bean:message key="BatchReport.list3" />&nbsp;
		  			 		<html:text name="BatchReportDetails" readonly="true" property="list1"/>
		  			 	</font>
				  	</td>
		  	
			   </tr>
           </logic:iterate>	
             </table>  
          </logic:equal>
		<table>
		      <p>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         </p>     
           <tr>
	   	 	   	 		
		    
		    <td align="left">
		    		<html:submit property="executeButton" ><bean:message key="Batch.executeButton"/>
		    		</html:submit>
		    </td>
		    
	  		<td width="80" nowrap="nowrap" align="right"> 		
	   	 			<html:submit property="resetButton" onclick="return onResetButton();"><bean:message key="Batch.ResetButton"/>
		    		</html:submit>
	  		</td>
		</tr>	
		
	</table> 		
	<br>
	<html:hidden property="compute" value="no"></html:hidden>
	<html:hidden property="vanish" value="no"></html:hidden>
	<html:hidden property="fin" value="no"></html:hidden>
	<html:hidden property="checkradio" value="no"></html:hidden>
	<html:hidden property="resetButHV" value="no"></html:hidden>
	
	</html:form>
</body>
<script  language="javascript">


function go()
{

document.forms[0].checkradio.value="yes";
document.forms[0].submit();
}

function check1()
{
document.forms[0].radioButton.value ="some";
document.forms[0].vanish.value="yes";

document.forms[0].compute.value="no";
//alert(document.forms[0].vanish.value);
//alert(document.forms[0].compute.value);
//alert(document.forms[0].radioButton.value);
//alert(document.forms[0].selectUser.value);
document.forms[0].submit();

}

function check2()
{
document.forms[0].radioButton.value ="all";
document.forms[0].compute.value="yes";

document.forms[0].vanish.value="no";
document.forms[0].fin.value="no";
//alert(document.forms[0].compute.value);
//alert(document.forms[0].vanish.value);
//alert(document.forms[0].radioButton.value);
//alert(document.forms[0].selectUser.value);
document.forms[0].submit();

}
function done()
{
document.forms[0].vanish.value="yes";
document.forms[0].fin.value="yes";

//alert(document.forms[0].fin.value);

}
function onResetButton(){
	document.forms[0].resetButHV.value="yes";
}


	
</script>  
</html:html>
