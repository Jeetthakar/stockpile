<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" import="app.*,harrier.income.com.masters.*,java.text.*" %>
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
<jsp:useBean id="exchangeHolidaysForm" scope="session" class="harrier.income.com.masters.ExchangeHolidaysForm"/>
<html:html> 
<head>
<html:base/>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 6.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title><bean:message key="defineIndex1" /></title>
   <meta name="Microsoft Theme" content="none"> 
	<script language="javascript" src="./codethatcalendarstd.js"></script>
	<script language="javascript" src="box_ex.js"></script>
	<script language="javascript">		
		var c2 = new CodeThatCalendar(caldef2);		
	</script> 	
</head>
 
<body onload="setf()" class="b2">  

<%
	  
	   String check = request.getParameter("from");
	   
	   log.info("Enter Check "+check);
	  if(check!=null && check.trim().length() > 0)
	   {
		   log.info("Check "+check);
		   if(check.equals("menu"))
		   {
		     exchangeHolidaysForm.reset();
		      check = "action";
	        }
	 }
	  %>
<html:form action="/holi" onsubmit="return validate();" >
	
	
	<link rel="stylesheet" type="text/css" href="./StyleSheet.css"  title="Default" />   
	<html:errors/><font color="blue" >
	</font>
	<p></p>
	
	
	<input type="hidden" name="flag" value="">
	<table width="120%" class="sample">
        <tr>
        	<td width="120%" nowrap="nowrap">
			<table border="0" width="100%"  height="10" cellspacing="0" cellpadding="0">
			<tr>
				<td align="left" height="37" class="subHeader" nowrap="nowrap">
					<b><bean:message key="Masters.XchgHolidays" /></b>
				</td>
			</tr>
			<tr >&nbsp;</tr>	
			<table border="0" width="100%"  height="243" cellspacing="0" cellpadding="0">
				<tr><td nowrap="nowrap"> &nbsp;</td></tr>
				<tr>
					<td width="17%" align="right" valign="top" nowrap="nowrap">
						<font face="Arial" size="2"><bean:message key="indCompliance.stockExc" /></font></td>
					<td  valign="top" nowrap="nowrap">
						<html:select property="selectExchange" size="1"  onchange="holiday_list1()" styleId="sIE">
							<html:optionsCollection property="selectExchangeCollection" name="ExchangeHolidaysForm"/>
						</html:select>    
					</td>
					<td align="left" valign="top" width="43%" nowrap="nowrap">
						<html:select property="selectExistingHolidays" size="1"   onchange="holiday_detail()" styleId="selectExistingHolidays">
							<html:optionsCollection property="selectExistingHolidaysCollection" name="ExchangeHolidaysForm"/>
						</html:select>  
					</td>
				</tr>
				<tr>
					<td  align="right" nowrap="nowrap">
						<font face="Arial" size="2"><bean:message key="Masters.HolidayName" /> </font></td>
					<td nowrap="nowrap">
						<html:text property="name"  size="26" tabindex="2">
						</html:text></td>
				</tr>
				<tr>
					<td width="17%" align="right" nowrap="nowrap">
						<font face="Arial" size="2"><bean:message key="Masters.HolidayDt" /></font></td>
					<td nowrap="nowrap">
			        	<html:text property="date_sel"  readonly="true" size="13"/>
			         	<input onclick="c2.popup('date_sel');" type="button" value="..." tabindex="3">
          			</td>
				</tr>
				<tr>
					<td width="17%" align="right" nowrap="nowrap">
						<font face="Arial" size="2"><bean:message key="Masters.HolidayDesc" /></font></td>
					<td width="40%" colspan="3" nowrap="nowrap">
						<html:text property="desc"  size="43" tabindex="4">
						</html:text></td>
				</tr>
				<tr>
					<td width="17%" align="right" nowrap="nowrap">&nbsp;</td>
					<td width="40%"  nowrap="nowrap">
						<fieldset style="width: 192px; height: 45px; padding: 2">
						<legend><font face="Arial" size="2"><bean:message key="Masters.Action" /></font></legend>
						<font face="Arial"><font size="2">&nbsp;&nbsp;&nbsp; </font>
						<html:radio property="new1" value="N" onclick="check1();" tabindex="5"></html:radio>
						<font size="2"><bean:message key="stockmaster.new" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</font>
						<html:radio property="update"  value="U" onclick="check2();" tabindex="6"></html:radio>
						<font size="2"><bean:message key="classes.update" /></font></font></fieldset></td>
				</tr>
				<tr>
					<td  align="right" nowrap="nowrap">&nbsp;</td>
					<td colspan="2"  align="left" nowrap="nowrap">
						<input type="submit" value='<bean:message key="defineIndex30"/>' name="B1" tabindex="7"  onclick="return Itestcheck()">
						<input type="button" value='<bean:message key="indexUpdate.reset"/>' name="B2" tabindex="8" onclick="resetID();">
						<html:button property="B3" tabindex="9" onclick="history.go(-1)"><bean:message key="indexUpdate.cancel"/></html:button></td>
					<td height="33" width="43%" nowrap="nowrap">&nbsp;</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<script language="JavaScript">
		document.forms[0].new1.checked = true;
		document.forms[0].update.checked = false;
	</script>
</html:form>
<html:form action="/holi">
	<input type="hidden" name="holidayID_h" value=" ">
	<input type="hidden" name="update_flag" value=" ">
	<input type="hidden" name="update_flag_copy" value=" ">
	<input type="hidden" name="exchange_h" value=" "> 
	
	</td></tr></table>
</html:form>
<script language="JavaScript">
var res;
function holiday_list1()
{
	document.forms[0].name.value = " ";
	document.forms[0].date_sel.value = " ";
	document.forms[0].desc.value = " ";
	if(document.forms[0].selectExchange.value != '')
	{
		document.forms[0].flag.value = "S";
		document.forms[1].exchange_h.value = document.forms[0].selectExchange.value;
		document.forms[0].submit();
	}
}
function holiday_detail()
{
	document.forms[1].holidayID_h.value = document.forms[0].selectExistingHolidays.value;
	
	document.forms[1].submit();
}
function validate()
{
	if(document.forms[0].flag.value != "S")
	{
		if(document.forms[0].selectExchange.value == 0)
		{
			alert("Please select Stock Exchange.");
			return false;
		}
		else if(document.forms[0].name.value == "")	
		{
			alert("Please enter holiday name.");
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
		else if(document.forms[0].new1.checked==false && document.forms[0].update.checked==false)
		{
			alert("Please select action.");
			return false;
		}
		else
		{
			return true;
		}
	}
}
function sub()
{
	document.forms[0].flag.value == "S"
}
function check14444()
{
	if(document.forms[0].update.checked==true)
	  window.document.forms[0].update.checked = false;
	document.forms[1].update_flag_copy.value = "0";
}
function check2444()
{
	if(document.forms[0].new1.checked==true)
	  window.document.forms[0].new1.checked = false;
}
function check1()
{
	if(document.forms[0].update.checked == true)
	  document.forms[0].update.checked = false;
}
function check2()
{
	if(document.forms[0].new1.checked == true)
	  document.forms[0].new1.checked = false;
}
function setf()
{
	window.document.forms[0].selectExchange.focus();
}
function resetID()
{
	document.forms[0].selectExchange.value = "0";
	document.forms[0].selectExistingHolidays.value = "0";
	document.forms[0].name.value = "";
	document.forms[0].date_sel.value = "";
	document.forms[0].desc.value = "";
	res = 1;
}

 function Itestcheck()
{  
    var msg='';
     if(document.forms[0].name.value == ""){
		msg=msg+'Please Enter Holiday Name\n';
	   }
       if(document.forms[0].date_sel.value == ""){
		msg=msg+'Please Enter Date\n';
	   }
	   if(msg=="") {
		return true;
		}
		else{
		alert(msg);
		return false;
	  }
 }   
</script>
</body>
</html:html>
