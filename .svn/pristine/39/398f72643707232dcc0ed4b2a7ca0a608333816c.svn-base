<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page import="app.*"%><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
		LogonForm form = null;
		if(request.isRequestedSessionIdValid())	{	
			form = (LogonForm)session.getAttribute("user");
		}
		if(form==null ||(!request.isRequestedSessionIdValid())){
			response.sendRedirect("userlogintemp.jsp");
		}
%>
<html>
<head>
<html:base/>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 6.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<link rel="stylesheet" type="text/css" href="./StyleSheet.css"  title="Default" />   
<title>Corporate Action</title>
</head>


 <jsp:useBean id="corporate" scope="session" class="app.Corporate"/> 
 
<html:form action="/indexCA_Action">
<html:errors/>
<table border="1" width="100%" cellspacing="0" cellpadding="0">
  <tr>
  <td width="100%">
  <table border="1" width="100%" cellspacing="0" cellpadding="0">  
  <tr>
   <td width="100%" ><center><b><font face="Arial" size="2">
   <bean:message key="corporate.Cadetail" /></font></b></center></td>
   <td></td>
    <td></td>
    <td></td>
    </tr>         
    
	<table border="1" width="100%" cellspacing="0" cellpadding="0">
  	<tr>
  	<td width="10%" ><b><font size="2" face="Arial">
	<bean:message key="corporate.Srno" /></font></b></td>     
	<td width="10%" ><b><font size="2" face="Arial">
	<bean:message key="defineIndex7" /></font></b></td>
	<td width="10%" ><b><font size="2" face="Arial">
	<bean:message key="corporate.stock" /></font></b></td>
   <td width="10%" ><b><font size="2" face="Arial">
   <bean:message key="corporate.Action" /></font></b></td>
   <td width="10%" ><b><font size="2" face="Arial">
	<bean:message key="corporate.Apply" /></font></b></td>
   <td width="10%" ><b><font size="2" face="Arial">
	<bean:message key="corporate.Value" /></font></b></td>             
   <td width="10%" ><b><font size="2" face="Arial">
   	<bean:message key="corporate.Status" /></font></b></td>   
   </tr>    
   </table>
   
<logic:iterate id="indlist" name="corporate" property="indlist" >    
   <table border="1" width="100%" cellspacing="0" cellpadding="0">
  	<tr>
<td width="10%" ><font size="2" face="Arial">
<html:multibox property="chk_but"><bean:write name="indlist" property="id"/></html:multibox>
</font></td>
<td width="10%" ><font size="2" face="Arial">&nbsp;
<bean:write name="indlist" property="iname"/></font></td>      
<td width="10%" ><font size="2" face="Arial">&nbsp;
<bean:write name="indlist" property="sname"/></font></td>      
<td width="10%" ><font size="2" face="Arial">&nbsp;
<bean:write name="indlist" property="action"/></font></td>  
<td width="10%" ><font size="2" face="Arial">
&nbsp;<bean:write name="indlist" property="apply"/></font></td>     	       
<td width="10%" ><font size="2" face="Arial">
&nbsp;<bean:write name="indlist" property="value"/></font></td>     	          	   
<td width="10%" ><font size="2" face="Arial">&nbsp;
<bean:write name="indlist" property="status"/></font></td>     	       
</tr>
</table>
 </logic:iterate>

<tr>

<td>
<p align="center">
<html:hidden property="corp_val" value="<%=corporate.getCorpid() %>" />
<html:hidden property="i_index" value="<%=corporate.getI_index() %>" />
<html:hidden property="exc" value="<%=corporate.getExc() %>" />
<html:hidden property="corpid" value="<%=corporate.getCorpid()%>" />
<html:hidden property="ap_co_button" value=""/>
<html:hidden property="values" value="<%=corporate.getValues()%>" />
<html:hidden property="ind_comp" value="<%=corporate.getInd_comp() %>" />
<html:submit property="ok" value="OK" onclick="return test1()"/>
<html:submit property="back" value="BACK" onclick="return test()"/>
</p>
</td>
</tr>
</table>
<logic:equal value="Ok" scope="request" parameter="ap_co_button">
<logic:equal value="changeiwf" property="corpid" name="corporate">
<p align="center"><font face="Arial" size="3" color="red"><b>
Please select either Original IWF or New IWF value</b></font><br><br>
<html:radio property="nature" value="n" onclick="return test2()">New IWF</html:radio>
<html:radio property="nature" value="o" onclick="return test2()">Original IWF</html:radio>
</p>				
</logic:equal>
</logic:equal>
</html:form>
 <Script language="javascript"> 
 function test1()
 {
 	document.forms[0].ap_co_button.value="Ok";
 	document.forms[0].submit();
 }
 function test2()
 {
  	document.forms[0].ap_co_button.value="Go";
 	document.forms[0].submit();
 }
 </script>
</html>