
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
<%@ page language="java" import="app.*"%><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<jsp:useBean id="ipebf" scope="session"  class="harrier.income.com.report.IndexCompareOHLCForm"/>
<html:html>
<head>
	 <html:base/>
<link href="../pages/StyleSheet.css" rel="stylesheet" type="text/css">
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
<div id="title" >
	<table width="1000" cellspacing="0" cellpadding="0" >
        		<tr>
		          	<td width="260" class="subHeader" nowrap="nowrap">
		          		&nbsp;
		          	</td>
		          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          				<font size="3" face="Arial">
		          				<b>	<bean:message key="IndexCompareOHCL.title" /></b>
		          				</font>
		         	</td> 
	          </tr>
</table>
</div>
<html:form action="/indexCompareOHLC">

		 <table width="656" >
     		<tr>
	  		   <font size="2" face="Arial">
    				<td width="202" nowrap="nowrap" align="right">
				          <bean:message key="indexUpdate.selectIndex" /> :
        	    	</td>          
			        <td width="400" nowrap="nowrap" align="left">
						<html:select property="d1"  value="null" size="3" multiple="true">
							<html:optionsCollection property="vector_indexlist" name="IndexCompareOHLCBean"/>
						</html:select>
			 		</td>
	   		     </font>
			 </tr>
		</table>


		<table width="844">
         	<tr>
        		<td width="102" align="right" nowrap="nowrap">
					<html:checkbox property = "check"  value = "checked" onclick = "return test1()"/> &nbsp;
				</td>
		  	    <td width="144" nowrap="nowrap">	
				   <font size="2" face="Arial">  
						  <bean:message key="IndexComparision.showsi" />
				  </font>
 			  	</td>
				<td width="69" nowrap="nowrap"><font size="2" face="Arial" >
        	    	  <bean:message key="corporate.Fdate" /></font>
    	        </td> 
	        	<td width="78" nowrap="nowrap">
					<html:text property="from" size="10" readonly="true"/>
				</td>
				<td width="37" nowrap="nowrap">    
        	         <html:button property="" value="..." onclick="c2.popup('from');"/>
            	</td>
			
			    <td width="58" nowrap="nowrap"><font size="2" face="Arial">
                        <bean:message key="corporate.Tdate" /></font>
    	        </td>

        	    <td width="79" nowrap="nowrap">	
					<html:text property="to" size="10" readonly="true" />
				</td>

				<td width="37" nowrap="nowrap">    
        	         <html:button property="" value="..." onclick="c2.popup('to');" />
            	</td>
	            <td width="162" nowrap="nowrap">
  	                <html:hidden property="hiddenVar" name="IndexCompareOHLCBean"/>
					<html:submit property="b1" onclick="go()"> <bean:message key="Reports.View"/> 	</html:submit>
			    </td>
		</tr>
	</table>
	<logic:notEqual value="yes"  property="hiddenVar" name="IndexCompareOHLCBean">
	
		   <table  border="0" width="900" height="400" cellpadding="0" cellspacing="0" align="center" class="sortable">
			  <tr></tr><tr>
		          <td  bgcolor="#cacaca" align="center" valign="middle" nowrap="nowrap">
    	    	      <p style="margin-left: 9">
    		          	<b><font face="Arial" color="blue" size="5">
    	    		   	  <bean:message key="IndexCompareOHCL.messaged" />
	           		   </font></b>
        			  </p>
	        	 </td>
        	  </tr>
   	   </table>
	</logic:notEqual>
	<logic:equal value="yes" property="hiddenVar" name="IndexCompareOHLCBean" >
    	<logic:empty property="indexohlc" name="IndexCompareOHLCBean">
			   <table  border="0" width="900" height="400" cellpadding="0" cellspacing="0" align="center" class="sortable">	
    		 			<tr></tr><tr>
		         			<td  bgcolor="#cacaca" align="center" valign="middle" nowrap="nowrap">
		         				<p style="margin-left: 9">
		         					<b><font face="Arial" color="blue" size="5">
     									<bean:message key="IndexCompareOHCL.ndata" />
     								</font></b>
     							</p>
     						</td>
     					</tr>
     				</table>
   		</logic:empty>
<!-- for excel download link -->
<%
	String fromdate=request.getParameter("from");
	String toDate=request.getParameter("to");
	String[] var=request.getParameterValues("d1");
	String url = "";
	if(var != null)
	{
		for(int i=0;i < var.length;i++){
					url = url + "&d1=" + var[i];
		}
			
	}
	
   	String excel = "../pages/FileDownload.jsp?type=10"+url+"&from="+fromdate+"&to="+toDate+"&filename=IndexCompareOHLC.xls";

%>


   		<logic:notEmpty property="indexohlc"  name="IndexCompareOHLCBean">
   					<pre> 			<a href="javascript:PrintThisPage();" ><font size="2" > <bean:message key="LatestDivisor.printerf" /></font></a>  <a href="<%= excel %>" ><font size="2" > <bean:message key="LatestDivisor.downloade" /></font></a>   <a href="" ><font size="2" > <bean:message key="LatestDivisor.emailr" /></font></a> </pre>
		<div id="table">
            <table border="0" width="800"  cellpadding="2" cellspacing="0" align="left" id="tabular" >
		    	<tr>
					<td>
						<table border="1" width="80%" align="center" cellpadding="2" cellspacing="0" id="t1" class="sortable" >
							<tr>     
							<td width="10%" bgcolor="#EFEFEF" align="center">
								<bean:message key="corporate.Date" />						
							</td>
							<td width="10%" bgcolor="#EFEFEF" align="center">
								<bean:message key="IndexCompareOHCL.open" />							
							</td>
							<td width="10%" bgcolor="#EFEFEF" align="center">
								<bean:message key="IndexCompareOHCL.high" />							
							</td>
							<td width="10%" bgcolor="#EFEFEF" align="center">
								<bean:message key="IndexCompareOHCL.low" />							
							</td>
							<td width="10%" bgcolor="#EFEFEF" align="center">
								<bean:message key="IndexCompareOHCL.close" />						
							</td>
							</tr>
					    	<logic:iterate id="cw" property="indexohlc"  name="IndexCompareOHLCBean">
								<tr>
		    					   	<td width="10%" nowrap="nowrap" align="center" bgcolor="white">
				    				   	<font face="Arial" color="blue" size="2">
						  			 		<bean:write name="cw" property="date"/>		 			
										</font>
								  	</td>
		    					   	<td width="10%" nowrap="nowrap" align="center" bgcolor="white">
				    				   	<font face="Arial" color="blue" size="2">
						  			 		<bean:write name="cw" property="open"/>		 			
										</font>
								  	</td>
		    					   	<td width="10%" nowrap="nowrap" align="center" bgcolor="white">
				    				   	<font face="Arial" color="blue" size="2">
						  			 		<bean:write name="cw" property="high"/>		 			
										</font>
								  	</td>
		    					   	<td width="10%" nowrap="nowrap" align="center" bgcolor="white">
				    				   	<font face="Arial" color="blue" size="2">
						  			 		<bean:write name="cw" property="low"/>		 			
										</font>
								  	</td>
		    					   	<td width="10%" nowrap="nowrap" align="center" bgcolor="white">
				    				   	<font face="Arial" color="blue" size="2">
						  			 		<bean:write name="cw" property="close"/>		 			
										</font>
								  	</td>
							</logic:iterate>
							</tr>
					</div>
					</logic:notEmpty>
			</logic:equal>
	</html:form>
	</body>
<script language="javascript">
function test1(){
	document.forms[0].submit();
}
function go() {
	document.forms[0].hiddenVar.value="yes";
	return true;
}

function popprinter(url)
{
	newwindow=window.open(url,'name','height=500,width=900,scrollbars=yes,left=80,top=130,menubar=yes,status=yes');
	if (window.focus) {newwindow.focus()}
}

	function PrintThisPage() 
	{ 
  		var fromdate=document.forms[0].from.value;
  		var todate=document.forms[0].to.value;
  		var ttle =document.getElementById('title').innerHTML;
   		var tble = document.getElementById('table').innerHTML;
   		var winprint=window.open("","",""); 
      	winprint.document.open(); 
       	winprint.document.write(ttle);
      	winprint.document.write('<br><br>');
        winprint.document.write('&nbsp;&nbsp;&nbsp;'); 
      	winprint.document.write(' <bean:message key="corporate.Fdate" />');
      	winprint.document.write('&nbsp;'); 
      	winprint.document.write(fromdate);
      	winprint.document.write('&nbsp;&nbsp;&nbsp;'); 
     	winprint.document.write('&nbsp;&nbsp;&nbsp;'); 
      	winprint.document.write(' <bean:message key="corporate.Tdate" />');
     	winprint.document.write('&nbsp;'); 
		winprint.document.write(todate);
       	winprint.document.write(tble); 
       	winprint.document.write('<br>');
       	winprint.document.close(); 
       	winprint.focus(); 
	}

</script>
</html:html>
