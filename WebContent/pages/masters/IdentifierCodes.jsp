<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ page language="java" import="app.*"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.Locale,harrier.income.com.masters.*,java.util.*" %>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %><%   
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
<html:base/>
</head>
<body onLoad="setf();" class="b2">
<html:form action="idtcode" onsubmit = "return check();">
	<link rel="stylesheet" type="text/css" href="./StyleSheet.css"  title="Default" />  
    <html:errors/><font color="blue" >
		</font>
	<p></p>
<table width="120%" class="sample">
        	<tr><td width="120%" nowrap="nowrap">
<table border="0" width="101%" cellspacing="0" cellpadding="0">
	 <tr>
    	<td class="subHeader" nowrap="nowrap"><b><bean:message key="Masters.IDCodes" /></b></td>
	 </tr>
	 <tr>
    	<td > <p>&nbsp;</p></td>
	 </tr>
     <tr>
    	<td class="subHeader" width="100" nowrap="nowrap"><b></b></td>
	 </tr>
     <tr>
       <td width="100%" nowrap="nowrap">
     <table border="0" width="100%"  cellspacing="0" cellpadding="2">
         <tr>
			<td width="205" nowrap="nowrap">
			<p align="right"><font face="Arial" size="2"><bean:message key="Masters.IDCodeName" /></font></td>
			<td width="354" colspan="3" nowrap="nowrap"><font face="Arial">			
			<html:text property="cname"  size="33" tabindex="1">
			</html:text></font></td>
			<td rowspan="5" align="left" valign="top" nowrap="nowrap">
			<p style="margin-top: 0; margin-bottom: 0">
			<font face="Arial" size="2"><bean:message key="Masters.ExistingIDCodes" /></font></p>
			<p style="margin-top: 0; margin-bottom: 0">
			<html:select property="selectIdCode" size="1"   onchange="popdata()" styleId="sIE">
					<html:optionsCollection property="selectIdCodeCollection" name="IdentifierCodes"/>
			</html:select>
			</td>
		</tr>
		<tr>
			<td width="205"  height="24" nowrap="nowrap">
			<p align="right"><font face="Arial" size="2"><bean:message key="timeZone.desc" /></font></td>
			<td width="354" colspan="3"  height="24" nowrap="nowrap">			
			<html:text property="desc"  size="33" tabindex="1">
			</html:text>
			</td>
		</tr>
		<tr>
			<td width="205"  height="50" nowrap="nowrap">&nbsp;</td>
			<td width="354"  colspan="3" height="50" nowrap="nowrap">
			<fieldset style="width: 184px; height: 44px; padding: 2">
			<legend><font face="Arial" size="2"><bean:message key="Masters.Action" /></font></legend>
			<font face="Arial"><input type="radio" name="new1" value="N" checked onclick="check1();" tabindex="3"><font size="2"><bean:message key="stockmaster.new" />&nbsp;&nbsp;&nbsp;&nbsp;
			</font><input type="radio" name="update" value="U" onclick="check2();" tabindex="4"><font size="2"><bean:message key="classes.update" /></font></font></fieldset></font></font></td>
		</tr>
		<tr>
			<td width="205"  height="35" nowrap="nowrap">&nbsp;</td>
			<td width="73"  height="35" nowrap="nowrap"><font face="Arial">
			<input type="submit" value='<bean:message key="defineIndex30"/>' name="B1" tabindex="5"></font></td>
			<td width="60"  height="35" nowrap="nowrap"><font face="Arial">
			<input type="button" value='<bean:message key="indexUpdate.reset"/>' name="B2" onclick="resetID();" tabindex="7"></font></td>
			<td width="221"  height="35" nowrap="nowrap">
			<html:button onclick="history.go(-1)" property="done" tabindex="6">
         			<bean:message key="indexUpdate.cancel"/>
      		</html:button>   
			</td>
		</tr>
		<tr>
			<td width="205"  height="22" nowrap="nowrap">&nbsp;</td>
			<td width="354" colspan="3"  height="22" nowrap="nowrap">
		<font face="Arial" size="2">
		</font>
			</td>
		</tr>
	</table>
	<p>&nbsp;</p>
</html:form>
<html:form action="idtcode">
	<html:hidden property="name"  />
	<html:hidden property="idname" />
	<script language="JavaScript">
		document.forms[0].new1.checked = true;
		document.forms[0].update.checked = false;
	</script>	
	<p>&nbsp;</p>
		</td></tr></table> 
</html:form>
<script language="JavaScript">
var field_value;
var res;
function check()
{
	field_value = document.forms[0].cname.value;
	
	if(field_value == "")
	{
		alert("Please enter identifier code name.");
		return false;
	}
	else if(document.forms[0].new1.checked==false && document.forms[0].update.checked==false)
	{
		alert("Please select action.");
		return false;
	}
	else if(document.forms[0].update.checked == true)
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
function setf()
{
	document.forms[0].cname.focus();
}
function popdata()
{
    document.forms[1].idname.value =	document.forms[0].selectIdCode.value;
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
	document.forms[0].cname.value="";
	document.forms[0].desc.value="";
	res = 1;
}
</script>
</body>
</html>
