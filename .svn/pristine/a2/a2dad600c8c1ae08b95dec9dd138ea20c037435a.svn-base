<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page language="java" import="app.*"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "harrier.income.com.masters.*" %>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.Locale,java.util.*" %><%@page import="org.apache.log4j.Logger"%>
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
<%
	response.setHeader("Cache-Control","no-cache"); 
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", -1);
%>
<jsp:useBean id="frtoCurrency" scope="session" class="harrier.income.com.masters.FrtoCurrency"/>
<html>
	<head>
		<html:base/>
		<meta http-equiv="Content-Language" content="en-us">
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
		<title><bean:message key="Masters.CurrConversion"/></title>
	</head>
<body onload="setf();" class="b2">
<%
	  
	   String check = request.getParameter("from");
	   
	   log.info("Enter Check "+check);
	  if(check!=null && check.trim().length() > 0)
	   {
		   log.info("Check "+check);
		   if(check.equals("menu"))
		   {
		     frtoCurrency.reset();
		      check = "action";
	        }
	 }
	  %>
<html:form action="/frto" onsubmit="return val_form();">
	<link rel="stylesheet" type="text/css" href="./StyleSheet.css"  title="Default" />   
	<html:errors/><font color="blue" >
			</font>
	<p></p>
		<table width="120%" class="sample">
        	<tr><td width="120%" nowrap="nowrap">
		<table border="0" width="100%" cellspacing="0" cellpadding="0">
		<tr>
		<tr>
			<td class="subHeader" nowrap="nowrap"><b>
			<bean:message key="Masters.CurrencyConversion" /></b></td>
		</tr>
		<tr>
			<td nowrap="nowrap">&nbsp;</td>
		</tr>
		<table border="0" width="100%" cellspacing="0" cellpadding="0">
		<tr>
		<tr>
			<td width="20%" align="right" nowrap="nowrap"><font face="Arial" size="2"><bean:message key="Masters.Currency1" /></font></td>
			<td colspan="3" nowrap="nowrap">
			<html:select property="selectCurrency1" size="1"   styleId="sIE">
					<html:optionsCollection property="selectCurrencyCollection1" name="FrtoCurrency"/>
				</html:select>
			&nbsp; <b><u>
			<font size="1" face="Arial" color="#0000FF">
			<a href="./AddCurrency.jsp"><bean:message key="stockmaster.new"/></font></u></b></td>
			<td width="35%" rowspan="6" align="left" valign="top" nowrap="nowrap">
			<p style="margin-top: 0; margin-bottom: 0"><font face="Arial" size="2"><bean:message key="Masters.ExistingConversions"/>
			</font></p>
			<p style="margin-top: 0; margin-bottom: 0">
			<html:select property="selectExistingConversion" size="1"  onchange="popdata()" styleId="sIE">
					<html:optionsCollection property="selectExistingConversionCollection" name="FrtoCurrency"/>
				</html:select>
			</td>
		</tr>
		<tr>
			<td width="20%" align="right" nowrap="nowrap"><font face="Arial" size="2"><bean:message key="Masters.Currency2" /></font></td>
			<td colspan="3" nowrap="nowrap">
			<html:select property="selectCurrency2" size="1"   styleId="sIE">
				<html:optionsCollection property="selectCurrencyCollection2" name="FrtoCurrency"/>
			</html:select>
			</td>
		</tr>
		<tr>
			<td width="20%" align="right" nowrap="nowrap"><font face="Arial" size="2"><bean:message key="Masters.Name4Conversion"/>
			</font></td>
			<td colspan="3" nowrap="nowrap">
			<html:text property="name_frto"  size="29" tabindex="1">
			</html:text></font></td>
		</tr>
		<tr>
			<td width="20%" align="right" nowrap="nowrap"><font face="Arial" size="2"><bean:message key="Master.Desc"/>
			</font></td>
			<td colspan="3" nowrap="nowrap">
			<html:textarea property="desc_frto" rows="2" cols="31" tabindex="2"></html:textarea></td> 
		</tr>
		<tr>
			<td width="20%" align="right" nowrap="nowrap">
			<font face="Arial" size="2"><bean:message key="corporate.Excval"/></font></td>
			<td colspan="3"  valign="bottom" nowrap="nowrap">
			<html:text property="exchange_rate"  size="20" tabindex="3">
			</html:text>
			&nbsp; <b><u>
			<font size="1" face="Arial" color="#0000FF">
			<a href="../ImportNewStock.jsp"><bean:message key="Masters.ImportFile"/></font></u></b>
		</td>
		</tr>
		<tr>
			<td width="20%" align="right" nowrap="nowrap">&nbsp;</td>
			<td colspan="3" nowrap="nowrap">
		<fieldset style="width: 214px; height: 44px; padding: 2">
		<legend><font size="2" face="Arial"><bean:message key="Masters.Action" /></font></legend>
		<input type="radio" name="new1" value="N" checked onclick="check1()"><font size="2" face="Arial"><bean:message key="stockmaster.new" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</font><font face="Arial">
		<input type="radio" name="update" value="U" onclick="check2()"></font>
		<font size="2" face="Arial"><bean:message key="classes.update" /></font></fieldset></td>
		</tr>
		<tr>
			<td width="20%" align="right" nowrap="nowrap">&nbsp;</td>
			<td width="9%" nowrap="nowrap"><input type="submit" value='<bean:message key="defineIndex30"/>' name="B1"></td>
			<td width="7%" nowrap="nowrap"><input type="button" value='<bean:message key="indexUpdate.reset"/>' name="B2" onclick="resetID();"></td>
			<td width="29%" nowrap="nowrap"><input type="button" value='<bean:message key="indexUpdate.cancel"/>' name="B3" onclick="history.go(-1)"></td>
			<td width="35%" nowrap="nowrap">&nbsp;</td>
		</tr>
	</table>
	<p>&nbsp;</p>
</html:form>
<html:form action="/frto">
	<p>&nbsp;</p>
	<html:hidden property="id_name" />
		<script language="JavaScript">
			if(document.forms[0].update.checked == true)
				document.forms[0].update.checked = false;
			document.forms[0].new1.checked = true;
		</script>	
	</td></tr></table>
</html:form>
<script language="JavaScript">
var res;
var ch_num;
function val_form()
{
	if(document.forms[0].update.checked == true)
	{ 
		if(res == 1)
		{
			alert("Please select from list to update.");
			return false;
		}
	}
	if (document.forms[0].selectCurrency1.options[0].selected)
	{
		alert('Please select currency.');
		return false;
	}
	if (document.forms[0].selectCurrency2.options[0].selected)
	{
		alert('Please select currency.');
			return false; 
	}
	if (document.forms[0].name_frto.value == "")
	{	
		alert('Please enter currency name.');
		return false; 
	}
	if(document.forms[0].new1.checked==false && document.forms[0].update.checked==false)
	{
		alert("Please select action.");
		return false;
	}
	if(document.forms[0].exchange_rate.value) 
	{
		var ch = check_number();
		if(ch == 0)
		{
			alert("Enter numeric values for exchange rate.");
			return false;
		}
	}
		return true;
}
function setf()
{
	document.forms[0].selectCurrency1.focus();
}
function popdata()
{
    document.forms[1].id_name.value = document.forms[0].selectExistingConversion.value;
    document.forms[1].submit();
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
function resetID()
{
	document.forms[0].selectCurrency1.value="";
	document.forms[0].selectCurrency2.value="";
	document.forms[0].name_frto.value="";
	document.forms[0].desc_frto.value="";
	document.forms[0].exchange_rate.value="";
	res = 1;
}
function check_number()
{
	var s = document.forms[0].exchange_rate.value;
	for (i = 0; i < s.length; i++)
	{ 
		var c = s.charAt(i);
		if(c != ".")
		{
			if (((c < "0") || (c > "9")))
			{
				return 0;
			}
		}
	}
	return 1;
}
</script>
</body>
</html>