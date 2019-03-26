
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" import="app.*"%>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<jsp:useBean id="PatBean1" scope="session" class="app.PatForm1"/>
<html:html>
<html:errors />
<head>
 	<html:base/>
	<title></title>
	  
	<link href="./StyleSheet.css" rel="stylesheet" type="text/css" title="Default">
	<script language="javascript" src="../Script/date_script.js"></script>
	<script language="javascript" src="box_ex.js"></script>
	<script language="javascript">
		var c2 = new CodeThatCalendar(caldef2);
	</script>

</head>
<body onload="initialize()" >
<html:form action = "/Pat1">


<table width="100%"  border="0" cellpadding="2" cellspacing="3" bgcolor="#D8D8D8">
  <tr>
    <td align="center" class="subHeader" nowrap="nowrap"><strong><bean:message key="Financialdta.title"/></strong></td>
  </tr>
  <tr>
   <td><table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td nowrap><font size="2" face="Arial"><bean:message key="classCompany.selectCompany"/></font>
      <html:select property="selectExchange" style="width=200">
			<html:optionsCollection property="exchangeCollection" name="PatBean1"></html:optionsCollection>
	  </html:select>
	  </td>
	  
	  <td nowrap><font size="2" face="Arial"><bean:message key="Pat.Number"/></font></td>
        <td><html:text property="number" size="10"></html:text></td>
		
		<td nowrap><font size="2" face="Arial"><bean:message key="pat.xdate"/></font></td>
        <td><html:text property="xdate" size="10" onblur="checkdate(this)"></html:text></td>
	
  </tr>
  </table>
  </td>
  </tr>
  <tr>
    <td align="center" class="subHeader" nowrap="nowrap" bgcolor="#CBC9C9"><strong><bean:message key="Financialdta.data"/></strong></td>
  </tr>
  <tr>
    <td align="center"><table width="69%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="16%" height="37" align="right"><font size="2" face="Arial"><bean:message key="Pat.value"/></font></td><br>
        <td class=tab1 width="34%"><html:text property="pat" style="text-align:right" size="18" tabindex="4"></html:text> <bean:message key="pat.millions"/></td>
		
        <td width="16%" align="right"><font size="2" face="Arial"><bean:message key="Pat.Net"/></font></td>
        <td class=tab1 width="34%"><html:text property="net"  style="text-align:right" size="18" tabindex="4"></html:text> <bean:message key="pat.millions"/></td>
      </tr>
      <tr>
        <td width="16%" height="36" align="right"><font size="2" face="Arial"><bean:message key="Pat.Dividend"/></font></td>
        <td class=tab1 width="34%"><html:text property="divided" style="text-align:right" size="18" tabindex="4"></html:text> </td>
        <td width="16%" align="right"><font size="2" face="Arial"><bean:message key="Pat.Interest"/></font></td>
        <td class=tab1 width="34%"><html:text property="intrest" style="text-align:right" size="18" tabindex="4"></html:text> <bean:message key="pat.millions"/></td>
      </tr>
      <tr>
        <td width="16%" height="36" align="right"><font size="2" face="Arial"><bean:message key="Pat.Tax"/></font></td>
        <td class=tab1 width="34%"><html:text property="tax" style="text-align:right" size="18" tabindex="4"></html:text> <bean:message key="pat.millions"/></td>
        <td width="16%" align="right"><font size="2" face="Arial"><bean:message key="Pat.paidup"/></font></td>
        <td class=tab1 width="34%"><html:text property="paidup" style="text-align:right" size="18" tabindex="4"></html:text> <bean:message key="pat.millions"/></td>
      </tr>
      <tr>
        <td width="16%" height="35" align="right"><font size="2" face="Arial"><bean:message key="Pat.Debt"/></font></td>
        <td class=tab1 width="34%"><html:text property="depth" style="text-align:right" size="18" tabindex="4"></html:text> <bean:message key="pat.millions"/></td>
        <td width="16%" align="right"><font size="2" face="Arial"><bean:message key="Pat.Sales"/></font></td>
        <td class=tab1 width="34%"><html:text property="sales" style="text-align:right" size="18" tabindex="4"></html:text> <bean:message key="pat.millions"/></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="" bgcolor="#CBC9C9">&nbsp;</td>
  </tr>
  <tr>
    <td align="center"><table width="40%"  border="0" cellspacing="0" cellpadding="0">
      <tr align="center">      
        <td width="35%"><html:button property="button" value="Submit" onclick="return check1();"></html:button></td>
        <td width="30%"><html:button property="resetButton" value="Reset" onclick="reset1();"></html:button></td>
        <td width="35%"><html:button onclick="history.go(-1)" property="b11" value="Back" tabindex="12"></html:button> </td>
      </tr>
    </table></td>
  </tr>
</table>
   <table cellspacing=0 cellpadding=0 align=center border=0>	
		 <%
  try{
  if(PatForm.getDisp_buffer()!=null){	%>
  
					<%=PatForm1.getDisp_buffer() %>
				 <%}
				 }catch(Exception e){log.info("error"+e.getMessage());}
			
			%>
	</table>

<html:hidden property="hiddenVar" name="PatBean1" value="no"/>
<html:hidden property="resetvalue" name="PatBean1" value="no"/> 
 

</html:form>

</body>

 <script language="javascript">

	function initialize() {
	var today = new Date();
	var td = today.getDate();
	if(td<10)
	td="0"+td;
	var mnth = today.getMonth(); 
	mnth=mnth+1;
	if(mnth<10)
	mnth="0"+mnth;
	var yr = today.getFullYear();
	var s = "-";
	if((document.forms[0].to.value)=="")
	document.forms[0].to.value= td+ s + mnth + s + yr;
	initSort();
}	
function check1()
{
document.forms[0].hiddenVar.value="yes";
document.forms[0].submit();
}
function reset1()
{
document.forms[0].resetvalue.value="yes";
document.forms[0].submit();
}
</script>
</html:html>
