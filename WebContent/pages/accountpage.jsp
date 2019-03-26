<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta http-equiv="Content-Language" content="en-us">
<title>Successful Registration</title>
<html:base/>

</head>

<body topmargin="0" leftmargin="0">

<table border="0" cellspacing="0" align="center" cellpadding="0">
  
  <tr>
    <td width="100%" height="99">
      <p align="center">&nbsp;</p>
      <p align="center">&nbsp;</p>
      <p align="center">&nbsp;</p>
      <p align="center">&nbsp;<font color="#0000FF">Thanks for Successful
      Registration</font></p>
      <p align="center"><font color="#0000FF">Please </font><i><font color="#FF0000"><html:link forward="logon">sign
      in</html:link></font></i><font color="#0000FF"> to proceed</font><html:errors/>
      </p>
    </td>
  </tr>
  <tr>
    <td width="100%" height="99"></td>
  </tr>

</table>

</BODY>
</HTML>
