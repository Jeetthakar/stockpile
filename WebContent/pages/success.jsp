
<%@page import="java.sql.ResultSet"%>
<%@page import="com.harrier.initializeation.ConnectInit"%><%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<html:html >
<head>
	<title></title>
</head>
<body background="aa.jpg">
<% String id=request.getParameter("id"); 
	String ans=request.getParameter("ans");

	String name=" ";String pass=" ";
	// new app.Connect().getConnection();
      //      java.sql.ResultSet rst=new app.Connect().getPass(id,ans);
        ResultSet rst = ConnectInit.getConnect().getPass(id,ans);
                if(rst.first()){
                    name=rst.getString("firstname");   
                    pass=rst.getString("password");     
            }
            
%>
<b><%= name %>&nbsp;&nbsp;</name>....</b>Your password is &nbsp;<b><%= pass %></b><br>
Want to <html:link forward="logon">Sign in ?</html:link></LI><br><br>
<a href="http:\\www.harriersys.com" >
<IMG src='ha.JPG' alt='Harrier Information Systems Pvt. Ltd.' width="100" height="50"></a>

	
</body>
</html:html>