<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="app.*,java.util.*"%><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<html:html>
  <head>
    <script type="text/javascript">
       function initialize()
       {
       document.forms[0].loginName.focus();
       }
  
       function resetFunc()
       {
       res=1;
       document.forms[0].loginName.value="";
       document.forms[0].oldPassword.value="";
       document.forms[0].newPassword.value="";
       document.forms[0].confirmNewPassword.value="";
       document.forms[0].loginName.focus();
       }	
    </script>

    <link href="/Income/pages/StyleSheet.css" rel="stylesheet"
     type="text/css">
  </head>
  <body class="b2" onload="initialize();">

    <html:form action="/password">

		
      <html:errors />
      <p>
      </p>

      <font color="blue"> 
      </font>
      <p>
      </p>
      <table width="100%" class="sample" height="100%" >
        <tr>
          <td width="120%" nowrap="nowrap">
            <table border="0" width="100%" cellspacing="0" cellpadding="0" height="20%">
              <tr>
                <td class="subHeader" align="center">
                  <b> Change Password</b>
                </td>
              </tr>
              <tr>
                <td width="105" height="20" valign="bottom" nowrap="nowrap">
                   &nbsp;
                </td>
              </tr>
            </table>	
            <table border="0" width="100%" cellspacing="0" cellpadding="0" >
              <tr >
                <td width="50" align="left" nowrap="nowrap">
                <td width="205" align="left" nowrap="nowrap">
                  <font face="Arial" size="2">Login Name</font>
                </td>
                <td width="70" colspan="3" nowrap="nowrap" align="left">
                  <html:text property="loginName" size="36" tabindex="1"
                   readonly="readonly">
                  </html:text>
                <td width="250" align="left" nowrap="nowrap">
              <tr>
              <tr>
                <td width="50" align="left" nowrap="nowrap">
                <td width="205" align="left" nowrap="nowrap">
                  <font face="Arial" size="2">Old Password</font>
                </td>
                <td width="70" colspan="3" nowrap="nowrap" align="left">
                  <html:password property="oldPassword" size="36" tabindex="1" />
                <td width="250" align="left" nowrap="nowrap">
              <tr>
              <tr>
                <td width="50" align="left" nowrap="nowrap">
                <td width="205" align="left" nowrap="nowrap">
                  <font face="Arial" size="2">New Password</font>
                </td>
                <td width="70" colspan="3" nowrap="nowrap" align="left">
                  <html:password property="newPassword" size="36" tabindex="1" />
                <td width="250" align="left" nowrap="nowrap">
              <tr>
              <tr>
                <td width="50" align="left" nowrap="nowrap">
                <td width="205" align="left" nowrap="nowrap">
                  <font face="Arial" size="2">Confirm New Password</font>
                </td>
                <td width="70" colspan="3" nowrap="nowrap" align="left">
                  <html:password property="confirmNewPassword" size="36"
                   tabindex="1" />
                <td width="250" align="left" nowrap="nowrap">
              <tr>
                <td width="100%" height="37">
              </tr>
              <tr>
							
                <td width="180" nowrap="nowrap" align="right">
                   &nbsp;
                </td>
                <td width="180" nowrap="nowrap" align="right">
                  <html:button property="resetButton" onclick="resetFunc();"> 
                     Reset
                  </html:button>
                </td>
                <td width="156" nowrap="nowrap" align="right">
								
                  <input type="submit" value="Submit" name="submit" tabindex="6">
                </td>
              </tr>
            </table>
	</table>
	</html:form>
    <body>
</html:html>
