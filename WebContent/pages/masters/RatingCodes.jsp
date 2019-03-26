<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page language="java" import="app.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Locale;import harrier.income.com.masters.*"%>
<%@ page import="java.sql.*"%><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
			LogonForm form = null;
			if(request.isRequestedSessionIdValid())	{	
				form = (LogonForm)session.getAttribute("user");
			}
			if(form==null ||(!request.isRequestedSessionIdValid())){
				response.sendRedirect("../userlogintemp.jsp");
			}
%>
<html>
<head>
<html:base />
</head>
<body onload=setf() class="b2">
<html:form action="/ratcode" onsubmit="return validate();">
	<meta http-equiv="Content-Type"
		content="text/html; charset=windows-1252">
	<meta name="GENERATOR" content="Microsoft FrontPage 6.0">
	<meta name="ProgId" content="FrontPage.Editor.Document">
	<link rel="stylesheet" type="text/css" href="./StyleSheet.css"
		title="Default" />
	<html:errors />
	<font color="blue"> </font>
	<p></p>
	<table width="120%" class="sample">
		<tr>
			<td width="120%" nowrap="nowrap">
			<table border="0" width="100%" height="10" cellspacing="0"
				cellpadding="0">
				<tr>
					<td width="2%" align="left" class="subHeader" nowrap="nowrap">&nbsp;</td>
					<td colspan="3" class="subHeader" nowrap="nowrap"><b> <bean:message
						key="Masters.RatingCodes" /></b></td>
					<td width="475" class="subHeader" nowrap="nowrap">&nbsp;</td>
				</tr>
				<table border="0" width="100%" height="123" cellspacing="0"
					cellpadding="0">
					<tr>
						<b></b>
					</tr>
					<tr>
						<td width="17%" align="right" nowrap="nowrap">&nbsp;</td>
						<td colspan="3" nowrap="nowrap">&nbsp;</td>
						<td width="475" align="left" valign="top" nowrap="nowrap"><font
							size="2" face="Arial"><bean:message
							key="Masters.ExistingRatingCodes" /></font></td>
					</tr>
					<tr>
						<td width="17%" align="right" nowrap="nowrap"><font face="Arial"
							size="2"><bean:message key="Masters.RatingCodeName" /><font
							color="red">*</font></font>&nbsp;:</td>
						<td colspan="3" nowrap="nowrap"><html:text property="rat_name"
							size="42" tabindex="1">
						</html:text></td>
						<td width="475" align="left" valign="top" nowrap="nowrap"><html:select
							property="selectRatingCodes" size="1" onchange="popdata()"
							styleId="sIE">
							<html:optionsCollection property="selectRatingCodesCollection"
								name="RatingCodes" />
						</html:select></td>
					</tr>
					<tr>
						<td width="17%" align="right" nowrap="nowrap">&nbsp;</td>
						<td colspan="3" nowrap="nowrap">&nbsp;</td>
						<td width="475" align="left" valign="top" nowrap="nowrap">&nbsp;</td>
					</tr>
					<tr>
						<td width="17%" align="right" height="15" nowrap="nowrap"><font
							face="Arial" size="2"><bean:message
							key="Masters.RatingCodeDetails" /></font></td>
						<td height="15" colspan="3" nowrap="nowrap"><html:text
							property="rat_desc" size="42" tabindex="1">
						</html:text></td>
						<td width="475" align="left" valign="top" nowrap="nowrap">&nbsp;</td>
					</tr>
					<tr>
						<td width="17%" align="right" height="14" nowrap="nowrap">&nbsp;</td>
						<td height="14" colspan="3" bordercolorlight="#000000"
							nowrap="nowrap">
						<fieldset style="width: 188px; height: 42px; padding: 2"><legend><font
							face="Arial" size="2"><bean:message key="Masters.Action" /></font></legend>
						<html:radio property="new1" value="N" onclick="check1();" /> <font
							face="Arial" size="2"><bean:message key="stockmaster.new" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</font> <html:radio property="update" value="U"
							onclick="check2();" /> <font face="Arial" size="2"><bean:message
							key="classes.update" /></font></fieldset>
					</tr>
					<tr>
						<td width="17%" align="right" nowrap="nowrap">&nbsp;</td>
						<td colspan="3" nowrap="nowrap">&nbsp;</td>
					</tr>
					<tr>
						<td width="17%" nowrap="nowrap">&nbsp;</td>
						<td width="8%" nowrap="nowrap"><input type="submit"
							value='<bean:message key="defineIndex30"/>' name="B1"></td>
						<td width="8%" nowrap="nowrap"><html:button property="resetButton"
							onclick="resetID1();">
							<bean:message key="indexUpdate.reset" />
						</html:button></td>
						<td width="22%" nowrap="nowrap"><html:button
							onclick="history.go(-1)" property="B2">
							<bean:message key="indexUpdate.cancel" />
						</html:button></td>
					</tr>
					<tr>
						<td width="17%" nowrap="nowrap">&nbsp;</td>
						<td colspan="3" nowrap="nowrap"></td>
					</tr>
				</table>
				</html:form>
				<html:form action="/ratcode">
					<html:hidden property="idname" />
					<script language="JavaScript">
		document.forms[0].new1.checked = true;
		document.forms[0].update.checked = false;
	</script>
					<p>&nbsp;</p>
					</td>
					</tr>
			</table>
			</html:form> <script language="JavaScript">
if(document.forms[0].new1.checked==false && document.forms[0].update.checked==false)
		window.document.forms[0].new1.checked = true;
var res1 = 0;
if(document.forms[0].update.checked==false && document.forms[0].new1.checked==false){
	document.forms[0].new1.checked = true;
}
function check1()
{
	if(document.forms[0].update.checked==true)
	  window.document.forms[0].update.checked = false;
}
function check2()
{
	if(document.forms[0].new1.checked==true)
	  window.document.forms[0].new1.checked = false;
}
function setf()
{
	window.document.forms[0].rat_name.focus();
}
var field_value;
function validate()
{
	field_value = window.document.forms[0].rat_name.value;
	if(field_value == "")
	{
		alert("Rating code name is required.");
		return false;
	}
	else if(document.forms[0].update.checked==true)
	{ 
		if(res1 == 1)
		{
			alert("Please select from list to update.");
			return false;
		}
	}
	else
	{
		return true;
	}
}
function popdata()
{
    document.forms[1].idname.value =	document.forms[0].selectRatingCodes.value;
    document.forms[1].submit();
}
function resetID1()
{
	document.forms[0].rat_name.value="";
	document.forms[0].rat_desc.value="";
	res1 = 1;
}
</script>
</body>
</html>
