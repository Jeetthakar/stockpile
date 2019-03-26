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
		if(form==null ||(!request.isRequestedSessionIdValid())){
			response.sendRedirect("../userlogintemp.jsp");
		}
%>
<html>
<head>
<html:base/>
</head>
<body class="b2">
<html:form action="zone" onsubmit="return validateTimeZones(this);">
	<link rel="stylesheet" type="text/css" href="./StyleSheet.css"  title="Default" />   
	<html:errors/>
	<font color="blue" >
		</font>
	<p></p>
	 <table width="120%" class="sample">
        	<tr><td width="120%" nowrap="nowrap">
	<table border="0" width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td width="16%" class="subHeader" nowrap="nowrap">&nbsp;</td>
			<td  colspan="3" class="subHeader" nowrap="nowrap">
			<b><bean:message key="Masters.TimeZone" /></b></td>
			<td width="20%" class="subHeader" nowrap="nowrap">&nbsp;</td>
			<td class="subHeader" nowrap="nowrap"><b><bean:message key="Masters.ExistingTimeZones" /></b></td>
			<td  width="1%" class="subHeader" nowrap="nowrap">&nbsp;</td>
		</tr>
		<tr><td> &nbsp;</td></tr>
	<table border="0" width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td width="16%" nowrap="nowrap">
			<p align="right" ><font face="Arial" size="2"><bean:message key="timeZone.td"/></font></td>
			<td  colspan="3" nowrap="nowrap"><font face="Arial">
			<html:text property="time_d"  size="24" /><font size="2">&nbsp;&nbsp;&nbsp;</font></font></td>
			<td  rowspan="5" align="left" valign="top" nowrap="nowrap">
			<html:select property="selectTimeZone" size="9"   onchange="popdata();" styleId="sIE">
					<html:optionsCollection property="selectTimeZoneCollection" name="TimeZones"/>
				</html:select>
		</td>
			<td  width="1%" nowrap="nowrap">&nbsp;</td>
		</tr>
		<tr>
			<td width="16%" nowrap="nowrap">
			<p align="right"><font face="Arial" size="2"><bean:message key="timeZone.desc"/></font></td>
			<td  colspan="3" nowrap="nowrap">
			<html:text property="desc" size="55" /></td>
			<td  width="1%" nowrap="nowrap">&nbsp;</td>
		</tr>
		<tr>
			<td width="16%"  height="30" nowrap="nowrap">&nbsp;</td>
			<td  width="40%" colspan="3" height="30" nowrap="nowrap">
			<fieldset style="width: 269px; height: 51px; padding: 2">
			<legend><font face="Arial" size="2"><bean:message key="Masters.Action" /></font></legend>
			<font face="Arial">
			<html:radio property="new1" value="N" style="font-weight: 700" onclick="check1();"/><font size="2"><bean:message key="stockmaster.new" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</font>
			<html:radio property="update" value="U" style="font-weight: 700" onclick="check2();"/><font size="2"><bean:message key="classes.update" /></font></font></fieldset></font></td>
			<td  width="1%" rowspan="2" nowrap="nowrap">&nbsp;</td>
		</tr>
		<tr>
			<td width="16%" nowrap="nowrap">&nbsp;</td>
			<td  width="7%" nowrap="nowrap"><font face="Arial"><html:submit property="B1" onclick="return val_form();"><bean:message key="defineIndex30"/></html:submit></font></td>
			<td  width="6%" nowrap="nowrap"><font face="Arial">
			<html:hidden property="radioChk"></html:hidden>
			</font>
			<html:button  property="B1" onclick="resetFunc();"><bean:message key="indexUpdate.reset"/></html:button>
			<html:hidden property="id"></html:hidden>
			</td>
			<td  width="27%" nowrap="nowrap">
				<html:button onclick="history.go(-1);" property="B2">
         			<bean:message key="indexUpdate.cancel"/>
      			</html:button>   
			</td>
		</tr>
		<tr>
			<td width="16%" nowrap="nowrap">&nbsp;</td>
			<td  colspan="3" nowrap="nowrap">
		<font face="Arial" size="2">
		
			</font>
			</td>
			<td  width="1%" nowrap="nowrap">&nbsp;</td>
		</tr>
	</table>
	<p>&nbsp;</p>
</html:form>
<html:form action="/zone">
	<html:hidden property="idname" />
	<script language="JavaScript">
		document.forms[0].id.value=document.forms[1].idname.value;
	</script>	
	<p>&nbsp;</p>
	</td></tr></table>
</html:form>
<script language="JavaScript">
    document.forms[0].new1.checked=true;
	document.forms[0].update.checked=false;

function resetFunc(){
		document.forms[0].time_d.value="";
		document.forms[0].desc.value="";
		document.forms[0].new1.checked=true;
		document.forms[0].update.checked=false;
}
function val_form()
{
		 var v=document.forms[0].new1.checked;
 		 var k=document.forms[0].update.checked; 

 		if((v==false)){
 			if((k==false)){
 			alert("Please select action(New OR Update)");
			return false;
 			 	//document.forms[0].new1.checked=true;
 			}
 		}
	   
	   if(document.forms[0].update.checked==true){
			
	 		 document.forms[0].radioChk.value="update";
	   }
	   else if(document.forms[0].new1.checked==true){
	   		
	   		document.forms[0].radioChk.value="new1";
	   }
		return true;
}
function popdata()
{
    document.forms[1].idname.value =	document.forms[0].selectTimeZone.value;
    document.forms[1].submit();
}
function check1()
{
	if(document.forms[0].update.checked==true){
	  document.forms[0].update.checked = false;
	   
	   }
}
function check2()
{
	if(document.forms[0].new1.checked==true){
	 document.forms[0].new1.checked = false;
	  
	  }
}
</script>
<html:javascript formName="TimeZones"/>  
</body>
</html>