<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
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
	if(form==null ||(!request.isRequestedSessionIdValid()))
	response.sendRedirect("../userlogintemp.jsp");
	response.setHeader("Cache-Control","no-cache"); 
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", -1);
	
%>
<html>
	<head>
		<title><bean:message key="Masters.IncomeCountry" /></title>
	</head>
	<body onload="setf();" class="b2">
	<html:base/>
	<html:form action="/country" onsubmit="return check();">
	<link rel="stylesheet" type="text/css" href="./StyleSheet.css"  title="Default" />   
	<html:errors/><font color="blue" >
		<p></p>
	<table width="100%" class="sample">
    	<tr>
     		<td width="100%" nowrap="nowrap">
			<table border="0" width="101%" cellspacing="0" cellpadding="0">
		<tr>
        	<td align="left" class="subHeader" nowrap="nowrap">
				<b><bean:message key="Masters.NewCountry" /></b>
			</td>
     	</tr>   
    	<tr>
    		<td nowrap="nowrap"> <p>&nbsp;</p></td>
	 	</tr>
  		<table border="0" width="101%"   cellspacing="0" cellpadding="0" height="323">
  		<tr>
			<td align="right"  height="33" valign="top" nowrap="nowrap">
				<font size="2" face="Arial"><bean:message key="Masters.CountryName" /></font><font color="red">*</font></td>
			<td width="379" colspan="3"  height="33" align="left" valign="top" nowrap="nowrap">
				<html:text property="name1"  size="40" tabindex="1">
				</html:text></font></td>
			<td width="298" rowspan="6" align="left" valign="top" nowrap="nowrap">
				<p style="margin-top: 0; margin-bottom: 0">
				<font size="2" face="Arial">
				<bean:message key="Masters.ExistingCountries" /></font></p>
				<p style="margin-top: 0; margin-bottom: 0">
				<html:select property="selectCountry" size="1"   onchange="popdata()" styleId="sIE">
					<html:optionsCollection property="selectCountryCollection" name="countries"/>
				</html:select>
			</td>
		</tr>
		<tr>
			<td align="right"  height="35" valign="top" nowrap="nowrap">
				<font size="2" face="Arial" >
				<bean:message key="Masters.CountryShortName" /></font><font color="red">*</font></td>
			<td width="379" colspan="3"  height="35" align="left" valign="top" nowrap="nowrap">
				<html:text property="shname"  size="21" tabindex="2">
				</html:text>
			</td>
		</tr>
		<tr>
			<td align="right"  height="32" valign="top" nowrap="nowrap"><font size="2" face="Arial"><bean:message key="Masters.Continent" /></font><font color="red">*</font></td>
			<td width="379" colspan="3"  height="32" align="left" valign="top" nowrap="nowrap">
				<html:select property="selectContinent" size="1"    styleId="sIE">
					<html:optionsCollection property="selectContinentCollection" name="countries"/>
				</html:select>
				<font size="1" face="Arial" color="#0000FF">
				<a href="./Continents.jsp?pr=t">
				<bean:message key="Masters.AddContinent" /></a></font>
			</td>
		</tr>
		<tr>
			<td align="right"  height="31" valign="top" nowrap="nowrap">
				<font size="2" face="Arial" ><bean:message key="stockmaster.currency" /></font><font color="red">*</font>
			</td>
			<td width="379" colspan="3"  height="31" align="left" valign="top" nowrap="nowrap">
				<html:select property="selectCurrency" size="1"    styleId="sIE">
					<html:optionsCollection property="selectCurrencyCollection" name="countries"/>
				</html:select>
				<u><font color="#0000FF" size="1" face="Arial">
				<a href="./AddCurrency.jsp">
				<bean:message key="Masters.AddCurrency" /></a></font></u>
			</td>
		</tr>
		<tr>
			<td align="right"  height="59" valign="top" nowrap="nowrap">&nbsp;</td>
			<td width="379" colspan="3"  height="59" align="left" valign="top" nowrap="nowrap">
			<fieldset style="width: 167px; height: 44px; padding: 2">
				<legend><font face="Arial" size="2"><bean:message key="Masters.Action" /></font></legend>
				<font face="Arial"> 
				<b><font size="2">
				<input type="radio" name="new1" value="N" checked onclick="check1();"  ><bean:message key="stockmaster.new" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="update" value="U" onclick="check2();">
				<bean:message key="classes.update" /></font></b></font>
			</fieldset></td>
		</tr>
		<tr>
			<td align="right" nowrap="nowrap">&nbsp;</td>
			<td width="76"  align="left" valign="top" nowrap="nowrap"><font face="Arial">
				<input type="submit" value='<bean:message key="defineIndex30"/>' name="B2" ></font></td>
			<td width="67"  align="left" valign="top" nowrap="nowrap"><font face="Arial">
				<input type="button" value='<bean:message key="indexUpdate.reset"/>' name="B4" onclick="resetID();"></font></td>
			<td width="236"  align="left" valign="top" nowrap="nowrap">
				<html:button onclick="history.go(-1)" property="B3">
         			<bean:message key="indexUpdate.cancel"/>
      			</html:button> </td>
		</tr>
	</table>
	<p>&nbsp;</p>
		</td></tr></table> 
</html:form>
<html:form action="/country"  >
		<html:hidden property="idname" value=" "/>
	<script language="JavaScript">
		
			document.forms[0].new1.checked = true;
		document.forms[0].update.checked = false;
	</script>	
	<p>&nbsp;</p>
	</td></tr></table> 
</html:form>
<script language="JavaScript">
var field_value;
var res=0;
function check()
{
	field_value = document.forms[0].name1.value;
	function trim(s) {
                return s.replace( /^\s*/, "" ).replace( /\s*$/, "" );
    }
	if(trim(field_value).length == 0)
	{
		alert("Please enter country name.");
		return false;
	}
	else if(trim(document.forms[0].shname.value).length == 0)
	{
		alert("Please enter country short name.");
		return false;
	}
	else if(document.forms[0].new1.checked==false && document.forms[0].update.checked==false)
	{
		alert("Please select action.");
		return false;
	}
	else if(document.forms[0].selectContinent.value == 0)
	{
		alert("Please select continent.");
		return false;
	}
	else if(document.forms[0].selectCurrency.value == 0)
	{
		alert("Please select currency.");
		return false;
	}
	else
	{
		return true;
	}
}
function setf()
{
	document.forms[0].name1.focus();
}
function popdata()
{
    document.forms[1].idname.value =	document.forms[0].selectCountry.value;
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
	document.forms[0].name1.value="";
	document.forms[0].selectCountry.value="";
	document.forms[0].shname.value="";
	document.forms[0].selectContinent.value="";
	document.forms[0].selectCurrency.value="";
	res = 1;
}
</script>
</body>
</html>