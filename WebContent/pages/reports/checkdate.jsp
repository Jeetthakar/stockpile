


<%@ page language="java" import="harrier.income.com.report.*"%>
<%@ page import = "java.io.PrintWriter" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "java.text.ParseException" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.io.File" %>
<%@ page import = "java.util.Locale,java.util.*" %>
<%@ page  import="app.*"%>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<html>
<head>
             Test Jsp
</head>

<body>
<table align="center">
			
		<tr>
		<td width="100" align="center">
		<font size="2" face="Verdana" color="blue">Enter the price</font>
		</td>
		<td width="100">
		           
		           <input type="text" name="price" size="25" align="right" dir= "rtl" value="" >
		</td>
		</tr>
		<tr>
<td width="150" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font face="arial-helvitica" size="3"><b>email-id : </b></font>  </td>
<td width="150" ><input type="text" name="email" size="25" align="right" value="" title="Enter email id"   maxlength="20"></td>
</tr>
		
		
		 
	 
            
            
           
	  </table>	
	  </body>
	 </html>