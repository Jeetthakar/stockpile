<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page import="app.*"%><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
				
		String fr2=request.getParameter("from");
		LogonForm form = null;
		if(request.isRequestedSessionIdValid())	{
			log.info("inside session !=nulls");
			form = (LogonForm)session.getAttribute("user");
		}
		if(form==null ||(!request.isRequestedSessionIdValid())){
			log.info("inside session ==null");
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

<jsp:setProperty name="corporate" property="ncorp_button" value="null" />	 
<html:form action="/nCorp_Action1">
<html:errors/>
<table border="1" width="120%" cellspacing="0" cellpadding="0">

  <tr>
   <td width="120%">
    <table border="1" width="120%" cellspacing="0" cellpadding="0">
    <tr>
    <td width="120%" ><center><b><font face="Arial" size="2">
     <bean:message key="corporate.Cadetail" /></font></b></center></td>
    <td></td>
    <td></td>
    <td></td>
    </tr>   
    
    <table border="1" width="120%" cellspacing="0" cellpadding="0">
  	<tr>
  	<td width="6%" ><b><font size="2" face="Arial">
  	<bean:message key="corporate.Srno" /></font></b></td>     
	<td width="11%" ><b><font size="2" face="Arial">
  	<bean:message key="corporate.Stkname" /></font></b></td>
   <td width="13%" ><b><font size="2" face="Arial">
  	<bean:message key="corporate.Action" /></font></b></td>
   <td width="8%" ><b><font size="2" face="Arial">
   	<bean:message key="corporate.Apply" /></font></b></td>
   <td width="5%" ><b><font size="2" face="Arial">
   	<bean:message key="corporate.Amount" /></font></b></td>
   <td width="9%" ><b><font size="2" face="Arial">
   	<bean:message key="corporate.Ratioshare" /></font></b></td>
    <td width="11%" ><b><font size="2" face="Arial">
   	<bean:message key="corporate.Shareoffer" /></font></b></td>
   <td width="7%" ><b><font size="2" face="Arial">
  	<bean:message key="corporate.Percent" /></font></b></td>   
   <td width="10%" ><b><font size="2" face="Arial">
   	<bean:message key="corporate.NoShares" /></font></b></td>             
   <td width="12%" ><b><font size="2" face="Arial">
   	<bean:message key="corporate.Status" /></font></b></td>   
   </tr>

   <logic:iterate id="stklist" name="corporate" property="stocklist" >
    <tr>
   <td width="6%" ><font size="2" face="Arial">&nbsp;
   <html:radio property="chk_but" value="id" idName="stklist" />
   </font></td>        	
   <td width="11%" ><font size="2" face="Arial">&nbsp;<bean:write name="stklist" property="sname"/></font></td>      
   <td width="13%" ><font size="2" face="Arial">&nbsp;<bean:write name="stklist" property="action"/></font></td> 
   <td width="8%" ><font size="2" face="Arial">&nbsp; <bean:write name="stklist" property="apply"/></font></td>      
   <td width="5%" ><font size="2" face="Arial">&nbsp; <bean:write name="stklist" property="amount"/></font></td>        	   
   <td width="9%" ><font size="2" face="Arial">&nbsp; <bean:write name="stklist" property="ratio_shr"/></font></td>      	   
   <td width="11%" ><font size="2" face="Arial">&nbsp; <bean:write name="stklist" property="shr_offer"/></font></td>      	   
   <td width="7%" ><font size="2" face="Arial">&nbsp; <bean:write name="stklist" property="percent"/></font></td>      	   
   <td width="10%" ><font size="2" face="Arial">&nbsp; <bean:write name="stklist" property="no_share"/></font></td>      	   	   
   <td width="12%" ><font size="2" face="Arial">&nbsp;n</font></td>      	   	   
   </tr>

   </logic:iterate>   	     
   </table>

   <tr>
   <td><p align="center">
     <html:hidden property="cdetail_button" value="" />
     <html:hidden property="exc" value="<%=corporate.getExc()%>" />
     <html:hidden property="corpid" value="<%=corporate.getCorpid() %>" />
     <html:hidden property="s_stock" value="<%=corporate.getS_stock() %>" />
     <html:hidden property="share" value="<%=corporate.getShare()%>" />
     <html:hidden property="ratio1" value="<%=corporate.getRatio1()%>" />
     <html:hidden property="ratio2" value="<%=corporate.getRatio2()%>" />
     <html:hidden property="amt" value="<%=corporate.getAmt()%>" />
     <html:submit value="OK" onclick="return test()"/>
	<html:submit value="BACK" onclick="return test1()"/>	
	</p></td></tr>
   </table>
      
   <logic:equal value="Exist" scope="request" parameter="flag">
   	<p align="center"><b><font face="Arial" color="red" size="2">
   	<bean:message key="corporate.Detail" />
  	</font></b></p>
  	<p align="center">
 	<html:radio property="nature" value="n" onclick="return radtest()" />New Value
  	<html:radio property="nature" value="o" onclick="return radtest()" />Original Value
	</p>		
   </logic:equal>
</html:form>
<script language="javascript">
function test()
{
	document.forms[0].cdetail_button.value="Ok";
	document.forms[0].submit();
}
function test1()
{
	document.forms[0].cdetail_button.value="Back";
	document.forms[0].submit();
}
function radtest()
{
	document.forms[0].cdetail_button.value="Radio";
	document.forms[0].submit();
}
</script>
</html>