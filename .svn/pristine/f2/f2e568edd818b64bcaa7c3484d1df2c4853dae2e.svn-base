
<%@page import="java.sql.ResultSet"%>
<%@page import="com.harrier.initializeation.ConnectInit"%><%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta http-equiv="Content-Language" content="en-us">
<title>Get Password</title>
<html:base/>

</head>

<body topmargin="0" leftmargin="0">

<table border="0" cellspacing="0" cellpadding="0">
 
 <%
 log.info("yourpass.jsp page");
 String que=" ";
 String name=" ";
 String id=" ";
 String id2=" ";
 String ans=" ";
 String password=" ";
             id=session.getAttribute("id").toString();
             id2=session.getAttribute("id2").toString();
             que=session.getAttribute("que").toString();
             ans=request.getParameter("ans");
           // new app.Connect().getConnection();
           // java.sql.ResultSet rst=new app.Connect().getPass("forgot_password_sec_que",id,ans,id2,que);
            ResultSet rst = ConnectInit.getConnect().getPass("forgot_password_sec_que",id,ans,id2,que);
            log.info("value of parameters in yourpass.jsp are"+id+ans+id2+que);
                if(rst.next()){      
                log.info("rst.next found in yourpass.jsp");             
                    name=rst.getString("first_name");  
                    password=rst.getString("password"); 
                        %>
                     <tr>
    <td width="22%" height="99">
     <td width="25%"></td>
     <td width="54%">
      <p align="center">&nbsp;</p>
      <p align="center">&nbsp;</p>
      <p align="center">&nbsp;</p>
      <p align="center">&nbsp;<font color="#0000FF">Hello </font><font color="#FF0000"> <%= name %> 
      </font><font color="#0000FF">! Your Password is </font><font color="#FF0000"> <%= password %></font></p>
      <p align="center"><font color="#0000FF">Please </font><i><font color="#FF0000"><b><html:link forward="logon">sign
      in</b></html:link></font></i><font color="#0000FF"> to proceed</font><html:errors/>
      </p>
    </td>
    </td>
  </tr>                    
                    
                    <%
            }
            else{
            log.info("rst.next not found in yourpass.jsp"); 
            %>
             <td width="100%" height="99">
      <p align="center">&nbsp;</p>
      <p align="center">&nbsp;</p>
      <p align="center">&nbsp;</p>
      <p align="center">&nbsp;<font color="#0000FF">Sorry ! No such User details Found</font></p>
      <p align="center"><font color="#0000FF">Please Click to Retry</font><i><font color="#FF0000"><a href="forgot.jsp"><font size="1" face="Arial">Forgot password</font></a></font></i><font color="#0000FF"> to proceed</font><html:errors/>
      </p>
            <p align="center"><font color="#0000FF">OR Click </font><i><font color="#FF0000"><html:link forward="logon">sign
      in</html:link></i><font color="#0000FF"> to proceed</font><html:errors/>
          </p>   
    </td>
  </tr>     
            
            <%
            
            }
%>
  
  <tr>
    <td width="22%" height="99"></td>
  </tr>
</table>

</BODY>
</HTML>
