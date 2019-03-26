<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<jsp:useBean id="forgotForm" scope="session" class="app.ForgotForm"/>
<HTML><HEAD><TITLE>Harrier! Sign-in Help</TITLE>
<META http-equiv=Content-Type content="text/html; charset=windows-1251">
<STYLE>PRE {
	BORDER-RIGHT: #f0f0f0 1px solid; PADDING-RIGHT: 10px; BORDER-TOP: #f0f0f0 1px solid; PADDING-LEFT: 10px; FONT-SIZE: 8pt; PADDING-BOTTOM: 10px; MARGIN-LEFT: 20px; BORDER-LEFT: #f0f0f0 1px solid; MARGIN-RIGHT: 20px; PADDING-TOP: 10px; BORDER-BOTTOM: #f0f0f0 1px solid; BACKGROUND-COLOR: #f8f8f8
}
P {
	MARGIN-TOP: 7px; FONT-SIZE: 8pt; MARGIN-LEFT: 7px; MARGIN-RIGHT: 7px; FONT-FAMILY: Verdana, Arial; TEXT-ALIGN: justify
}
</style>

        <meta name="Microsoft Theme" content="none">
	</head>
	<html:base/>
	<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
	<script language="javascript" src="box_ex.js"></script>
	<script language="javascript">
		var c2 = new CodeThatCalendar(caldef2);
	</script>



<body topmargin="0" leftmargin="0">

<table border="0" cellspacing="0" cellpadding="0" width="525">
  <tr>
 <!--   <td width="780" height="99"><img border="0" src="images/head2.jpg" width="780" height="106"></td>-->
  </tr>
  <tr>
    <td width="780" height="99">
      <html:errors/>
<html:form action="/ForgotSubmit" >
        <center>
        <table cellSpacing="0" cellPadding="0" width="100%" border="0">
          <tbody>
            <tr>
              <td align="left" width="1%"></td>
              <td vAlign="bottom" noWrap align="right" width="100%">
                <table cellSpacing="0" cellPadding="0" width="100%" border="0">
                  <tbody>
                    <tr>
                      <td vAlign="bottom" noWrap align="right" width="99%">
                        <hr align="center">
                      </td>
                    </tr>
                  </tbody>
                </table>
              </td>
            </tr>
          </tbody>
        </table>
        <div align="left">
          <table cellPadding="4" width="761" border="0">
            <tbody>
              <tr bgColor="#a0b8c8">
                <td align="left" width="1098" bgcolor="#EEEEEE"><font face="arial" size="+1"><b>Sign-in
                  Problems</b></font></td>
              </tr>
            </tbody>
          </table>
        </div>
        <table height="211" cellSpacing="0" cellPadding="4" width="524" border="0">
          <tbody>
            <tr>
              <td align="left" width="184" height="195" rowSpan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
              <td align="left" width="542" bgColor="#EEEEEE" height="12"><font face="Arial" size="+1">&nbsp;
                1. Confirm Your Identity</font></td>
            </tr>
            <tr>
              <td align="left" width="1128" height="183"><font face="Arial" size="-1">&nbsp;Please
                enter the Birthday, ZIP (or Postal) Code, and Country (or
                Territory) associated with your account.</font>
                <table height="77" cellPadding="2" width="632" border="0">
                  <tbody>
                    <tr>
                      <td align="right" width="170" height="14"><font face="Arial" size="-1">Your
                        <b>Birthday :-</b></font></td>
                      
                        <td vAlign="top" align="left" width="434" height="40" rowSpan="2" allign="top">
                        <table height="83" width="100%" border="0">
                          <tbody>
                            <tr>
                             <td align="top" width="100%" height="30">
                             <b>Day</b> 
                             <html:select size="1" property="birthDay" style="width=40">
                       		<html:optionsCollection name="forgotForm" property="dayCollection" /> 
                    	</html:select>
                             <%-- commented <input type="text" name="day" value="" size="3">--%> 
                             <b>Month</b>
                             <html:select size="1" property="birthMonth" style="width=40">
                       		<html:optionsCollection name="forgotForm" property="monthCollection" /> 
                    	</html:select>
                    	<b>Year</b>
                             <html:select size="1" property="birthYear" style="width=60">
                       		<html:optionsCollection name="forgotForm" property="yearCollection" /> 
                    	</html:select>
                             </td>
                            
                             <%-- commented because for codethatcalender button --%>
                             <%--<html:text readonly="true" property="id2"/>--%>
                              <%--<input onclick="c2.popup('id2');" type="button" value="..."></td>--%>
                              
                            </tr>
                            <tr>
                              <td width="100%" height="38"> <html:text property="zip"/></td>
                            </tr>
                          </tbody>
                          
                        </table>
                      </td>
                      
                    </tr>
                    <tr>
                      <td align="right" width="170" height="20"><font face="Arial" size="-1">Your
                        <b>ZIP (or Postal) Code</b></font></td>
                      <td width="2" height="20"></td>
                    </tr>
                    <tr>
                      <td vAlign="top" align="right" width="170" height="25"><font face="Arial" size="-1">Your
                        <b>Country or Territory</b></font></td>
                      <td width="434" height="25">&nbsp;
                         <html:select size="1" property="country">
                       		<html:optionsCollection name="forgotForm" property="countryIdCollection" /> 
                    	</html:select></td>
                      
                      </td>
                    </tr>
                  </tbody>
                </table>
              </td>
            </tr>
          </tbody>
        </table>
        <div align="center">
          <center>
          <table border="0" width="53%" cellspacing="0" cellpadding="3">
            <tr>
              <td width="100%" align="center" bgcolor="#EEEEEE"><font face="Arial" size="+1" bgcolor="#a0b8c8"><span style="background-color: #EEEEEE">Forgot
                your password?</span></font></td>
            </tr>
            <tr>
              <td width="100%" align="center"><font face="Arial" size="-1">Enter
                your <b>ID:</b></font></td>
            </tr>
            <tr>
              <td width="100%" align="center"><html:text property="id" size="20"/></td>
            </tr>
            <tr>
              <td width="100%" align="center"><input type="submit" value="Get  Password" name="get_password"></td>
            </tr>
          </table>
          </center>
        </div>
        </form>
        <hr align="center">
      </center>
      <p>&nbsp;</td>
  </tr>
  </html:form>
</table>

</body>

</html>
