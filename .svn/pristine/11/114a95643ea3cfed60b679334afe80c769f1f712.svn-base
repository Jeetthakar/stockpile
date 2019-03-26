<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page  import="harrier.income.com.report.*"%>
<%@ page  import="java.util.*"%>
 <%@page import="org.apache.log4j.Logger"%>
<%-- <%  --%>
<!-- Logger log = Logger.getLogger(this.getClass()); -->
<!-- log.info("inside "+this.getClass().getSimpleName()+" page");	 -->
<!--  %> -->
<%
			app.LogonForm form = (app.LogonForm)session.getAttribute("user");
			  if(form == null)
			response.sendRedirect("../login1.jsp");
%>
<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">

</head>

<body bgcolor="#C0C0C0">
<jsp:useBean id="email" scope="session" class="app.EmailReportForm"/>
<html:form action="/email" onsubmit="return split_all();">
	<p style="margin-top: 0; margin-bottom: 0"><b>
	<font face="Arial" color="#3399FF">&nbsp;&nbsp;&nbsp; MAIL</font></b></p>
	<hr>
	<table border="0" width="100%" cellspacing="0" cellpadding="0" height="299">
		
	
		
		<%@ include file="FileDownloadExcelAll.jsp" %> 
		
		<input type="hidden" value="26" name="varid">
		<input type="hidden" value="26" name="switch_type">
		<input type="hidden" value="sudhir" name="username">
		<input type="hidden" value="AllinOne.xls" name="att">
		
		<tr>
			<td width="12%"><b><font face="Arial" size="2">&nbsp;&nbsp;&nbsp; To :</font></b></td>
			<td colspan="2"><input type="text" name="to" size="49"></td>
		</tr>
		<tr>
			<td width="12%" height="26"><b><font face="Arial" size="2">&nbsp;&nbsp;&nbsp; Cc :</font></b></td>
			<td height="26" colspan="2"><input type="text" name="cc" size="49"></td>
		</tr>
		<tr>
			<td width="12%" height="33"><b><font face="Arial" size="2">&nbsp;&nbsp;&nbsp; Subject :</font></b></td>
			<td height="33" colspan="2"><input type="text" name="subject" size="49"></td>
		</tr>
		<tr>
			<td width="12%" height="34"><b><font face="Arial" size="2">&nbsp;&nbsp;&nbsp; Attachment :</font></b></td>
			<td height="34" colspan="2"><input type="text" name="attachment" disabled size="49" value="AllinOne.xls"></td>
		</tr>
		<tr>
			<td width="12%" align="left" valign="top" height="147">
			<b>
			<font face="Arial" size="2">&nbsp;&nbsp;&nbsp; Description :</font></b></td>
			<td height="147" valign="top" colspan="2">
			<textarea rows="8" name="desc" cols="37"></textarea></td>
		</tr>
		<tr>
			<td width="12%" align="left" valign="top" height="26">
			<p align="right">&nbsp;</td>
			<td valign="top" height="26" width="7%">
			<input type="submit" value="Send" name="B1"></td>
			<td valign="top" height="26" width="81%">
			<input type="reset" value="Cancel" name="B2" onClick="history.back()"> 
			</td>
		</tr>
	</table>
	<hr>
	<p>&nbsp;</p>
</html:form>
<script language="JavaScript">
function split_all()
{
	var strEmail1 = document.forms[0].to.value;
	var strcc = document.forms[0].cc.value;
	var com = ",";
	if(strEmail1)
	{
		var to_array = strEmail1.split(",");
		for(i=0; i < to_array.length; i++)
		{
			var ret = validateEmail(to_array[i]);
			if(ret == false)
			{
				alert('Invalid email address.');
				document.forms[0].to.value.focus;
				return false;
			}
		}
		if(strcc)
		{
			var to_strcc = strcc.split(",");
			for(i=0; i < to_strcc.length; i++)
			{
				var ret = validateEmail(to_strcc[i]);
				if(ret == false)
				{
					alert('Invalid email in Cc address.');
					document.forms[0].to.value.focus;
					return false;
				}
			}		
		}
		return true;
	}	
	alert('Please enter email address.');
	return false;
}

function validateEmail(strEmail) 
{ 
   var at="@" ;
   var dot="." ;
   var lat=strEmail.indexOf(at) ;
   var lstr=strEmail.length ;
   var ldot=strEmail.indexOf(dot) ;
   if (strEmail.indexOf(at)==-1)
   {
      return false ;
   }
   if (strEmail.indexOf(at)==-1 || strEmail.indexOf(at)==0 || strEmail.indexOf(at)==lstr)
   {
       return false ;
   } 
   if (strEmail.indexOf(dot)==-1 || strEmail.indexOf(dot)==0 || strEmail.indexOf(dot)==lstr)
   {
      return false;
   } 
   if (strEmail.indexOf(at,(lat+1))!=-1)
   {
     return false;
   } 
  if (strEmail.substring(lat-1,lat)==dot || strEmail.substring(lat+1,lat+2)==dot)
  { 
     return false ;
  } 
  if (strEmail.indexOf(dot,(lat+2))==-1)
  { 
    return false;
  }
  if (strEmail.indexOf(" ")!=-1)
  { 
  	return false;
  }
  return true;
} 


</script>
</body>

</html>
