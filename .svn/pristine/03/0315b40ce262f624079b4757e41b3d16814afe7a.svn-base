<%@ page info="Generic Error Processing Page"
    contentType="text/html;charset=utf-8"
    isErrorPage="true"
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<html:html locale="true">
  <head>
    <title></title>
  </head>
  <body >
  &nbsp;
  <p>&nbsp;</p>
  <p>
   
     <br>
   
  </p>
   
  <table border="0" width="100%" height="120">
    <tr>
      <td width="14%" height="1"></td>
      <td width="69%" height="1"></td>
      <td width="17%" height="1"></td>
    </tr>
    <tr>
      <td width="14%" height="206"></td>
      <td width="69%" height="206">
        <table border="1" width="100%" height="141" bgcolor="#C0C0C0">
          <tr>
            <td width="100%" height="46"><bean:message key="error.exception.begin" /></td>
          </tr>
          <tr>
            <td width="100%" height="34"><b>
 "<%= exception %>"</b> </td>
          </tr>
          <tr>
            <td width="100%" height="43"> <bean:message key="error.exception.end" />
    <bean:page id="name" property="request"/>
    
    <p>
    <% exception.printStackTrace(); %>
    </p></td>
          </tr>
        </table>
      </td>
      <td width="17%" height="206"></td>
    </tr>
    <tr>
      <td width="14%" height="140"></td>
      <td width="69%" height="140"></td>
      <td width="17%" height="140"></td>
    </tr>
  </table>
  </body>
</html:html>