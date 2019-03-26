
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setHeader("Expires","0");

			  if((session.getAttribute("user"))== null){
		//response.sendRedirect("../login1.jsp");
		
		%>
		<jsp:forward page="../login1.jsp"></jsp:forward>
		 <% 
		}
		%>

<html:html locale="true">
<head>
		 	
			<title></title>
			<link href="StyleSheet.css" rel="stylesheet" type="text/css">
			<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
			<script language="javascript" src="box_ex.js"></script>
	<meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    </head>
 <body>


<p class="underline" >Password Summary<hr class="giveline"/></p>
 <p>
  <table align="center"><tr><td align="center">
    <h5>Password Saved sucessfully</h5> </td></tr><tr>
    <td align="center">
   Want to Sign in<LI><a href="pages/login1.jsp">Sign in</a></LI>
   </td>
   </tr>
</table>
   </p>
<p>&nbsp;</p>


</body>

</html:html>
