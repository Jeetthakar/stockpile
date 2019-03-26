
<%@page import="com.harrier.initializeation.ConnectInit"%><%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
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
				String locale=session.getAttribute("locale").toString();
			//	AcessControl asc=new AcessControl();
				AcessControl asc=ConnectInit.getAcessControl();
				asc.setLocale(locale);
			}
			if(form==null ||(!request.isRequestedSessionIdValid()))
				response.sendRedirect("../userlogintemp.jsp");
			
			
%>
<html>
<head>
<html:base/>
<title>Income: Stock Type</title>
</head>
<body onload=setf() class="b2">
<html:form action="/type" onsubmit = "return check();">
 	<link rel="stylesheet" type="text/css" href="./StyleSheet.css"  title="Default" /> 
	<%
		String from=null;
 		if(request.getParameter("from")!=null){
 			from=request.getParameter("from");
 		}
 		stocktype obj= new stocktype();
 		obj.setFrom(from);
	%>
	<html:errors/>
	<font color="blue" >
	</font>
	<p></p>
	<table width="120%" class="sample">
        <tr><td width="120%" nowrap="nowrap">
	<table border="0" width="100%" cellspacing="0" cellpadding="0" height="10">
		<tr>
			<td width="10"  valign="bottom" class="subHeader" nowrap="nowrap"></td>
			<td width="289" colspan="3"  valign="bottom" class="subHeader" nowrap="nowrap"><b>
				<bean:message key="Masters.StockTypes" /></font></b></td>
			<td  valign="bottom" class="subHeader" nowrap="nowrap"></td>
		</tr>
	<table border="0" width="100%" cellspacing="0" cellpadding="0" height="169" >
		<tr>
			<td width="170" nowrap="nowrap">&nbsp;</td>
			<td width="289" colspan="3" nowrap="nowrap">&nbsp;</td>
			<td nowrap="nowrap"><font size="2" face="Arial"><bean:message key="Masters.ExistingStockTypes" /></font></td>
		</tr>
		<tr>
			<td width="170" nowrap="nowrap">
				<p align="right"><font size="2" face="Arial"><bean:message key="Masters.EnterStockType" /> 
				name :<b> </b> </font></td>
			<td width="289" colspan="3" >
				<html:text property="name"  size="29" tabindex="1">
				</html:text>
			</td>
			<td align="left" nowrap="nowrap">
				<html:select property="selectStockType" size="1"   onchange="popdata()" styleId="sIE">
					<html:optionsCollection property="selectStockTypeCollection" name="stocktype"/>
				</html:select>   
		   </td>
		</tr>
		<tr>
			<td height="20" width="170" nowrap="nowrap">&nbsp;</td>
			<td height="20" width="289" colspan="3" nowrap="nowrap">
				<fieldset style="padding: 2; width:204px; height:40px">
				<legend><font face="Arial" size="2"><bean:message key="Masters.Action" /></font></legend>
				<font face="Arial">
				<input type="radio" value="N" name="new1" checked onclick="check1();"><font size="2">
				<bean:message key="stockmaster.new" /><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </b>
				</font>
				<input type="radio" name="update" value="U" onclick="check2();"><font size="2"><bean:message key="classes.update" /></font></font></fieldset></td>
			<td align="left" valign="top" nowrap="nowrap">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="16%" nowrap="nowrap" >&nbsp;</td>
			<td  width="7%" nowrap="nowrap"><font face="Arial"><html:submit property="B1"> <bean:message key="defineIndex30"/></html:submit></font></td>
			<td  width="6%" nowrap="nowrap"><font face="Arial">
				</font>
				<html:button property="B3" onclick="resetID();"> <bean:message key="indexUpdate.reset"/></html:button>
			</td>
			<td  width="27%" nowrap="nowrap">
				<html:button onclick="history.go(-1)" property="B2">
         			<bean:message key="indexUpdate.cancel"/>
      			</html:button>   
			</td>
		</tr>
	</table>
</html:form>
<html:form action="/type">
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
var res1 = 0;
function check()
{
	field_value = document.forms[0].name.value;
	if(field_value == "")
	{
		alert("Enter stock type name.");
		return false;
	}
	else if(document.forms[0].new1.checked==false && document.forms[0].update.checked==false)
	{
		alert("Please select action.");
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
function resetID()
{
	res1 = 1;
	
	document.forms[0].name.value="";
	document.forms[0].new1.checked=true;
	document.forms[0].update.checked=false;
}
function setf()
{
	document.forms[0].name.focus();
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
function popdata()
{
    document.forms[1].idname.value =	document.forms[0].selectStockType.value;
    document.forms[1].submit();
}
</script>
</body>
</html>