<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page import="app.*"%>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
			LogonForm form = (LogonForm)session.getAttribute("user");
			if(form == null){
				response.sendRedirect("login1.jsp");
			}	
				String fr2=request.getParameter("from");
%>
<html>
<head>
<html:base/>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 6.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<link rel="stylesheet" type="text/css" href="./StyleSheet.css"  title="Default" />   
<title>CA</title>
</head>

<jsp:useBean id="corporate" scope="session" class="app.Corporate"/> 
          
<html:form action="/corpDairy_Action4">          
<p align="center"><font face="Arial" color="red" size="2"><b>
<bean:message key="corporate.Undohead"/>
</b></font></p>		
<table border="1" width="175%" cellpadding="0" cellspacing="0">
<tr>
<td width="4%" ><b><font size="2" face="Arial">&nbsp;<bean:message key="corporate.Srno" /></font></b></td>
<td width="11%" ><b><font size="2" face="Arial">&nbsp;<bean:message key="corporate.Action" /></font></b></td>
<td width="11%" ><b><font size="2" face="Arial">&nbsp;<bean:message key="corporate.Stkexname" /></font></b></td>
<td width="11%" ><b><font size="2" face="Arial">&nbsp;<bean:message key="corporate.Stkname" /></font></b></td>
<td width="12%" ><b><font size="2" face="Arial">&nbsp;<bean:message key="indexcompose.indexname" /></font></b></td>
<td width="8%" ><b><font size="2" face="Arial">&nbsp;<bean:message key="corporate.Apply" /></font></b></td>
<td width="7%" ><b><font size="2" face="Arial">&nbsp;<bean:message key="corporate.Applied" /></font></b></td>
<td width="5%" ><b><font size="2" face="Arial">&nbsp;<bean:message key="corporate.Amount" /></font></b></td>
<td width="5%" ><b><font size="2" face="Arial">&nbsp;<bean:message key="corporate.Percent" /></font></b></td>
<td width="9%" ><b><font size="2" face="Arial">&nbsp;<bean:message key="corporate.Ratioshare" /></font></b></td>
<td width="10%" ><b><font size="2" face="Arial">&nbsp;<bean:message key="corporate.Shareoffer" /></font></b></td>
<td width="10%" ><b><font size="2" face="Arial">&nbsp;<bean:message key="corporate.Value" /></font></b></td>
<td width="5%" ><b><font size="2" face="Arial">&nbsp;<bean:message key="corporate.Status" /></font></b></td>
</tr>	

<logic:iterate id="stklist" name="corporate" property="undolist" >
<tr>
<td width="4%" ><font size="2" face="Arial">&nbsp;<bean:write name="stklist" property="id" /></font></td>
<td width="11%" ><font size="2" face="Arial">&nbsp;<bean:write name="stklist" property="action"/></font></td>
<td width="11%" ><font size="2" face="Arial">&nbsp;<bean:write name="stklist" property="stkexname" /></font></td>
<td width="11%" ><font size="2" face="Arial">&nbsp;<bean:write name="stklist" property="sname" /></font></td>
<td width="12%" ><font size="2" face="Arial">&nbsp;<bean:write name="stklist" property="iname"/></font></td>
<td width="8%" ><font size="2" face="Arial">&nbsp;<bean:write name="stklist" property="apply"/></font></td>
<td width="7%" ><font size="2" face="Arial">&nbsp;<bean:write name="stklist" property="applied"/></font></td>
<td width="5%" ><font size="2" face="Arial">&nbsp;<bean:write name="stklist" property="amount"/></font></td>
<td width="5%" ><font size="2" face="Arial">&nbsp;<bean:write name="stklist" property="percent"/></font></td>
<td width="9%" ><font size="2" face="Arial">&nbsp;<bean:write name="stklist" property="ratio_shr"/></font></td>
<td width="10%" ><font size="2" face="Arial">&nbsp;<bean:write name="stklist" property="shr_offer"/></font></td>
<td width="9%" ><font size="2" face="Arial">&nbsp;<bean:write name="stklist" property="value"/></font></td>
<td width="10%" ><font size="2" face="Arial">&nbsp;<bean:write name="stklist" property="status"/></font></td>
<td width="5%" ></td>
</tr>			
</logic:iterate>

</table>		
<p align="center"><font face="Arial"  color="red" size="2"><b>
Sorry <u>UNDO</u> is not allowed!
</b></font><br><br>

<jsp:setProperty name="corporate" property="r_type" value="<%=corporate.getR_type()%>" />

<logic:equal value="1" property="leng" name="corporate">
	<logic:equal value="stock event" property="r_type" name="corporate">
	<logic:redirect href="/Stockpile/pages/NCorporateAction.jsp?flag=Exist" />
	</logic:equal>
	<logic:equal value="index event" property="r_type" name="corporate">
	<logic:redirect href="/Stockpile/pages/IndexEvents.jsp?flag=Exist" />
	</logic:equal>
</logic:equal>
<p align="center" >
<html:hidden property="r_type" value="<%=corporate.getR_type() %>"></html:hidden>
<html:hidden property="undo_button" value="" />
<html:submit value="Back" onclick="return test()"/> 
</p>
</html:form>
<script language="javascript">
function test()
{
	document.forms[0].undo_button.value="Back";
	document.forms[0].submit();
}
</script>
</html>          