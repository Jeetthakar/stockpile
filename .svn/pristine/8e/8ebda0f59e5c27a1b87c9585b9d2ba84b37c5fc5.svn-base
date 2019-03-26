<%@ page language="java" import="app.*"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
			LogonForm form = (LogonForm)session.getAttribute("user");
			  if(form == null)
			response.sendRedirect("login1.jsp");
%>
<html>
<head>
<html:base/>
</head>  
<br></br>
<br></br>

<p align="center">
 	 <table border="1" width="65%" align= "center" cellspacing="0" cellpadding="80">
            <tr>
            <td width="65%" bgcolor="#cacaca" align="center" valign="middle"><font size="5" color="blue" face="Arial Black"> <bean:message key="Page_Not_Implemented" /> </a></font></td>  
            </tr>
        </table>
  </p>
</html>