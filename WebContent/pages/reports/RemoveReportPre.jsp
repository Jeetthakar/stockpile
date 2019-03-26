<!--  Remove Report/Preference   --- -->
<!--  Neha -----  7-04-2007 ----------- -->  
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
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

%>
 <jsp:useBean id="RemoveReportPerBean" scope="session" class="harrier.income.com.report.RemoveReportPerForm"/> 
<html:html>
<head>
		 	<html:base/>
			<title></title>
			
			<link href="./StyleSheet.css" rel="stylesheet" type="text/css">
			
</head>
<body onload="initialize()"  >
<html:form action="/RemoveReportPre">

	<html:errors/>
	
<table width="100%" cellspacing="0" cellpadding="0" > <!-- Table for Displaying Title -->
        		<tr>		          	
		          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap"> 
		          			<font size="3" face="Verdana"><b>Remove Preference</b>
		          				
		          			</font>
		         	</td>
	          	</tr>
        	
	</table> 
	<p align="left">
      <table border="0" width="100%"  cellspacing="0" cellpadding="3"   > 

      <tr>
         	<td align="left" width="400">
        	<font size="2" color="black" face="Verdana">&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="Report.Prefrence" />
        	</font>
        	</td>
        	<td align="left"  width="500">
        	    <html:select size="1" property="selectprefrence" onchange="check()" >  
			       		<html:optionsCollection name="RemoveReportPerBean" property="prefrencecollection"  />
            	</html:select>
             	
	      		<html:submit property="remove" onclick="check1();" value="Remove">Remove</html:submit>
            </td> 
            
                    	
        </tr>
            <tr>
            </tr>
        <tr>
            <td align="left" width="221" nowrap="nowrap" >
        	<font size="2" color="black" face="Verdana">&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="Report.select" />
        	</font>
        	</td>
        	<td align="left" nowrap="nowrap" width="771">
        	 <html:select size="3" property="selectReport"  multiple="true">  
			       		<html:optionsCollection name="RemoveReportPerBean" property="reportCollection"  />
            	</html:select>
	      		
            </td>          	
            </tr>
            <tr></tr>
             <tr></tr>
             <tr></tr>
             <tr></tr>
                  <tr>
                    <td  width="700" align="right">&nbsp;&nbsp;&nbsp;&nbsp;
  					<html:submit property="delete" onclick="final();" value="Delete">Delete</html:submit>
  									
  					<html:button property="resetButton" onclick="resetFunc();"  > 
		       		<bean:message key="indexUpdate.reset"/>
             		</html:button>
             		           		
  					<html:button property="backButton" onclick="backFunction();"> 
		       		<bean:message key="indexUpdate.back"/>
             		</html:button>
             		</td>
                   </tr>	
                  </table>	
      </html:form>
 
</body>
<script language="JavaScript">
function final()
{

      if(document.forms[0].selectprefrence.value =="0")
    {
       alert("Please select Preference name."); 
       document.forms[0].selectprefrence.focus();
      
    }
     if(document.forms[0].selectReport.value ==null)
    {
       alert("Please select report"); 
       document.forms[0].selectReport.focus();
      
    }
    
}
function check1()
{
      if(document.forms[0].selectprefrence.value =="0")
    {
       alert("Please select Preference name."); 
       document.forms[0].selectprefrence.focus();
      
    }

}
function check()
{
    document.forms[0].submit();

}
function resetFunc()
{
	
	document.forms[0].selectprefrence.value="";
	document.forms[0].selectprefrence.focus();
	document.forms[0].submit();
}
function backFunction(){
history.go(-1);
}
</script>
</html:html>
	
	
	
	
	
	
	
	
		