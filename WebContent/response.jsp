<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <%@ page import="java.io.*,java.util.*" %>
<!DOCTYPE html>
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
<title>EBS Response</title>
</head>
<body>
<center>
<h2>EBS Response</h2>
<table width="50%" border="1" align="center">
<tr bgcolor="#949494">
<th>Response Parameter Name</th><th>Response Value</th>
</tr>
<%
   Enumeration paramNames = request.getParameterNames();

   while(paramNames.hasMoreElements()) {
      String paramName = (String)paramNames.nextElement();
      out.print("<tr><td>" + paramName + "</td>\n");
      System.out.println("Name :- "+paramName);
      String paramValue = request.getParameter(paramName);
      out.println("<td> " + paramValue + "</td></tr>\n");
      System.out.println("Value :- "+paramName);
   }
%>
</table>
</center>
</body>
</html>
    