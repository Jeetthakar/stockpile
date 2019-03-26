<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page language="java" import="harrier.income.com.masters.*"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "app.*" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.Locale,java.util.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "java.sql.*" %><%@page import="org.apache.log4j.Logger"%>
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
<html:html>
<head>
<html:base/>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Income:Add Currency.</title>
</head>
<body onload=setf_add() class="b2">
<html:form action="currency" onsubmit="return check_add();">
 	<link rel="stylesheet" type="text/css" href="./StyleSheet.css"  title="Default" />   
	<html:errors/><font color="blue" >
		</font>
		<p></p>
		<table width="120%" class="sample">
        	<tr><td width="120%" nowrap="nowrap">
		<table border="0" width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td  class="subHeader">
				<b>
				<bean:message key="Masters.AddCurrency" /></b>
			</td>
		</tr>
		<tr>
			<td width="205" height="20"  valign="bottom" nowrap="nowrap">
			&nbsp;
			</td>
		</tr>
	<table border="0" width="100%" cellspacing="0" cellpadding="0">
	    <tr>
			<td width="205" align="right" nowrap="nowrap">
				<font face="Arial" size="2"><bean:message key="Masters.CurrencyName" /></font></td>
			<td width="270" colspan="3" nowrap="nowrap">
				<html:text property="name_add"  size="36" tabindex="1">
				</html:text></font>
			<td rowspan="6" align="left" valign="top" nowrap="nowrap">
				<p style="margin-top: 0; margin-bottom: 0"><font face="Arial" size="2">
				<bean:message key="Masters.ExistingCurrency" /></font></p>
				<p style="margin-top: 0; margin-bottom: 0"></td>
			<td width="489" nowrap="nowrap" align="left" height="30">
          		<html:select property="selectCurrency" size="1"  onchange="popdata_add()" styleId="sIE">
					<html:option value="selectCurrency"></html:option>
					<html:optionsCollection property="selectCurrencyCollection" name="AddCurrency"/>
				</html:select>
         	</td>
		</tr>	
		<tr>
			<td width="205" align="right" nowrap="nowrap"><font size="2" face="Arial"><bean:message key="Masters.CurrencyCode" /></font></td>
			<td width="270" colspan="3" nowrap="nowrap">
				<html:text property="code_add"  size="36" tabindex="1">
				</html:text></font>
			</td>
		</tr>
		<tr>
			<td width="205" align="right" nowrap="nowrap">&nbsp;</td>
			<td width="270" colspan="3" nowrap="nowrap">
				<fieldset style="padding: 2; width:237; height:44">
				<legend><font size="2" face="Arial"><bean:message key="Masters.Action" /></font></legend>
				<html:radio property="new1_add" value="N" onclick="check1_add();" ></html:radio>
				<font size="2" face="Arial"><bean:message key="stockmaster.new" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<html:radio property="update_add" value="U" onclick="check2_add();" ></html:radio>
				<bean:message key="classes.update" /></font></fieldset>
			</td>
		</tr>
		<tr>
			<td width="205"  align="right" nowrap="nowrap">&nbsp;</td>
			<td width="82" nowrap="nowrap"><input type="submit" value='<bean:message key="defineIndex30"/>' name="B1" ></td>
			<td width="81" nowrap="nowrap"><html:button property="resetButton" onclick="resetFunc();"  > 
		       		<bean:message key="indexUpdate.reset"/>
             		</html:button></td>
			<td width="107" nowrap="nowrap"><input type="button" value='<bean:message key="indexUpdate.cancel"/>' name="B3" onclick="history.go(-1)"></td>
		</tr>
		<tr>
			<td width="205" nowrap="nowrap">&nbsp;</td>
			<td width="270" colspan="3" nowrap="nowrap">&nbsp;</td>
		</tr>
		<tr>
			<td width="205" nowrap="nowrap">&nbsp;</td>
			<td width="270"  colspan="3" nowrap="nowrap">&nbsp;</td>
		</tr>
	</table>
	<p>&nbsp;</p>
</html:form>
<html:form action="currency">
	<html:hidden property="idname_add" value=" "/>
	<script language="JavaScript">
			document.forms[0].new1_add.checked = true;
			document.forms[0].update_add.checked = false;
    	</script>	
	<p>&nbsp;</p>
	</td></tr></table>
</html:form>
<script language="JavaScript">
var field_value;
var res;
function check_add()
{
	field_value = document.forms[0].name_add.value;
	if(field_value == "")
	{
		alert("Please enter currency name."); 
		return false;
	}
	else if(document.forms[0].new1_add.checked==false && document.forms[0].update_add.checked==false)
	{
		alert("Please select action.");
		return false;
	}
	else if(document.forms[0].update_add.checked == true)
	{ 
		if(res == 1)
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
function setf_add()
{
	document.forms[0].name_add.focus();
}
function popdata_add()
{
    document.forms[1].idname_add.value =	document.forms[0].selectCurrency.value;
    document.forms[1].submit();
}
function check1_add()
{
	if(document.forms[0].update_add.checked==true)
	  document.forms[0].update_add.checked = false;
}
function check2_add()
{
	if(document.forms[0].new1_add.checked==true)
	  document.forms[0].new1_add.checked = false;
}
function resetFunc()
{
	res = 1;
	document.forms[0].new1_add.checked = true;
	document.forms[0].name_add.value="";
	document.forms[0].code_add.value="";
}
</script>
</body>
</html:html>