<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<jsp:useBean id="regiform" scope="session" class="subscription.form.subscribeUserForm"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>My JSP 'tempform.jsp' starting page</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    
    
    
    <script language="javascript">
	var i=0;
	var sub_id = new Array();
	function appendcheckbox(a)
	{
	
	sub_id[i]=a;
	i=i+1;
	alert("hiii"+sub_id[i]);
	}

	
	
	</script>
    
  </head>
  
  <body>
  
  
 	
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<html:errors/>
	<html:form action="/submitregiform">
	<tr>
	<td>
	<html:errors/>
	</td>
	</tr>
	<tr>
	<td>User Id/Email Id:</td><td><html:text property="userid"  size="36" value="cmdehankar@gmail.com"></html:text></td>
	</tr>   
	
	<tr>
	<td>Password::</td><td><html:password property="password"  size="36" ></html:password></td>
	</tr> 
	<tr>
	<td>Retype Password:</td><td><html:password property="password"  size="36" ></html:password></td>
	</tr> 
	<tr>
	<td>firstname:</td><td><html:text property="firstname"  size="36" ></html:text></td>
	</tr> 
	<tr>
	<td>lastname:</td><td><html:text property="lastname"  size="36" ></html:text></td>
	</tr> 
	<tr>
	<td>add1:</td><td><html:text property="add1"  size="36" ></html:text></td>
	</tr> 
	<tr>
	<td>zipcode:</td><td><html:text property="zipcode" value="783783873" size="36" ></html:text></td>
	</tr> 
	<tr>
	<td>city:</td><td><html:text property="city"  size="36" ></html:text></td>
	</tr> 
	<tr>
	<td>country:</td><td><html:text property="country"  size="36" ></html:text></td>
	</tr> 
	<tr>
	<td>contactno:</td><td><html:text property="contactno"  size="36" ></html:text></td>
	</tr> 
	
   <html:submit property="submitValue" >Submit</html:submit>
   </html:form>
</table>

  
  </body>
</html>
