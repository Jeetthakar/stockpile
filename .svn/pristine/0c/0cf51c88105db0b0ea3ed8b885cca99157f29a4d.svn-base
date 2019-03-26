<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page import="app.*"%><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
			LogonForm form = (LogonForm)session.getAttribute("user");
			if(form == null){
				response.sendRedirect("login1.jsp");
			}	
				String fr2=request.getParameter("from");
%>
<html>
<head>
<html:base/>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 6.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<link rel="stylesheet" type="text/css" href="./StyleSheet.css"  title="Default" />   
<title>CA</title>
 
 <meta name="Microsoft Theme" content="none"> 
<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
<script language="javascript" src="box_ex.js"></script>
<script language="javascript">
	var c2 = new CodeThatCalendar(caldef2);
</script> 		 	
</head>	
 <html:form action="/corpDairy_Action3"> 
  <html:errors/>
  <table width="100%" class="sample">
        <tr><td width="100%">   
 <table border="0" width="100%" cellspacing="0" cellpadding="0">		
 <logic:equal value="1" scope="request" parameter="exist">
<font size="2" face="Arial" color="red"><b>
<bean:message key="corporate.Newexist" />
</b></font>
<BR>
<html:radio property="ind_comp" value="c" onclick="return radsub()"/>
<bean:message key="corporate.Continue" />
<html:radio property="ind_comp" value="b" onclick="return radsub()"/>
<bean:message key="corporate.Back" />
 </logic:equal>
 
 <tr>
<td width="22%" align="left" bgcolor="#D6D6D6"><font size="2" face="Arial"><b>
<bean:message key="corporate.Eventhead" />
</b></font></td>
<td width="12%" bgcolor="#D6D6D6"/>
 <td width="46%" bgcolor="#D6D6D6" height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="20%" bgcolor="#D6D6D6" height="30">&nbsp;</td>
</tr>

<tr>
<td width="15%"  height="24" align="left" valign="top">
<p align="left"><font size="1" face="Arial"><b>
<bean:message key="corporate.stock" />
</b></font></td>
<td width="12%"  height="24" align="left" valign="top">
<html:text property="name" size="30" disabled="true"/>
</td>
 <td width="46%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="27%"  height="30">&nbsp;</td>
</tr>

<tr>
<td width="17%"  height="24" align="left" valign="top">
 <p align="left"><font size="1" face="Arial"><b>
 <bean:message key="corporate.Action" />
  </b></font></td>
<td width="46%"  height="24" align="left" valign="top">
<p align="left"><html:select size="1" property="corpid" >
<html:optionsCollection name="corporate" property="corpCollection" />
</html:select>
</p>    
</td>
 <td width="14%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="23%"  height="30">&nbsp;</td>
</tr>

<tr>
<td width="10%"  height="24" align="left" valign="top">
 <p align="left"><font size="1" face="Arial"><b>
 <bean:message key="corporate.Amount" />
 </b></font></td>
<td width="10%"  height="24" align="left" valign="top">
<html:text property="amt" onchange="return amttest()"  />
</td>
 <td width="32%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="46%"  height="30">&nbsp;</td>
</tr>

<tr>
<td width="10%"  height="24" align="left" valign="top">
 <p align="left"><font size="1" face="Arial"><b>
 <bean:message key="corporate.Percent" />
  </b></font></td>
<td width="10%"  height="24" align="left" valign="top">
<html:text property="percent"  disabled="true" />
</td>
 <td width="32%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="46%"  height="30">&nbsp;</td>
</tr>

<tr>
<td width="17%"  height="24" align="left" valign="top">
 <p align="left"><font size="1" face="Arial"><b>
 <bean:message key="corporate.NoShares" />
 </b></font></td>
<td width="46%"  height="24" align="left" valign="top">
<html:text property="share" />
</td>
 <td width="14%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="23%"  height="30">&nbsp;</td>
</tr>

<tr>
 <td width="17%" height="25" ><font size="1" face="Arial">
 <bean:message key="corporate.Ratio" /></font></td>
  <td width="14%" height="25" align="center" >
   <p align="left">
<html:text property="ratio1" size="2" /> : <html:text property="ratio2" size="2"/></td>	
 <td width="46%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="23%"  height="30">&nbsp;</td>	
  </tr>
  
<tr>
<td width="17%"  height="24" align="left" valign="top">
 <p align="left"><font size="1" face="Arial"><b>
<bean:message key="corporate.Announce" />
</b></font></td>
<td width="46%"  height="24" align="left" valign="top">
<html:text property="announce_date" readonly="true" /><input onclick="c2.popup('announce_date');" tabIndex="6" type="button" value="..."/>  	
</td>
 <td width="14%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="23%"  height="30">&nbsp;</td>
</tr>

<tr>
<td width="17%"  height="24" align="left" valign="top">
 <p align="left"><font size="1" face="Arial"><b>
 <bean:message key="corporate.Ex" /></b></font></td>
<td width="46%"  height="24" align="left" valign="top">
<html:text property="ex_date" readonly="true"/><input onclick="c2.popup('ex_date');" tabIndex="6" type="button" value="..."/>  	
</td>
 <td width="14%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="23%"  height="30">&nbsp;</td>
</tr>

<tr>
<td width="17%"  height="24" align="left" valign="top">
 <p align="left"><font size="1" face="Arial"><b>
 <bean:message key="corporate.Record" /></b></font></td>
<td width="46%"  height="24" align="left" valign="top">
<html:text property="record_date" readonly="true"/><input onclick="c2.popup('record_date');" tabIndex="6" type="button" value="..."/>  	
</td>
 <td width="14%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="23%"  height="30">&nbsp;</td>
</tr>

<tr>
<td width="17%"  height="24" align="left" valign="top">
 <p align="left"><font size="1" face="Arial"><b>
 <bean:message key="corporate.Apply" /></b></font></td>
<td width="46%"  height="24" align="left" valign="top">
<html:text property="apply_date" readonly="true"/><input onclick="c2.popup('apply_date');" tabIndex="6" type="button" value="..."/>  	
</td>
 <td width="14%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="23%"  height="30">&nbsp;</td>
</tr>

<tr>
<td width="17%"  height="24" align="left" valign="top">
 <p align="left"><font size="1" face="Arial"><b>
 <bean:message key="corporate.Bookcs" /></b></font></td>
<td width="46%"  height="24" align="left" valign="top">
<html:text property="bc_start" readonly="true"/><input onclick="c2.popup('bc_start');" tabIndex="6" type="button" value="..."/>  	
</td>
 <td width="14%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="23%"  height="30">&nbsp;</td>
</tr>

<tr>
<td width="17%"  height="24" align="left" valign="top">
 <p align="left"><font size="1" face="Arial"><b>
  <bean:message key="corporate.Bookce" /></b></font></td>
<td width="46%"  height="24" align="left" valign="top">
<html:text property="bc_end" readonly="true"/><input onclick="c2.popup('bc_end');" tabIndex="6" type="button" value="..."/>  	
</td>
 <td width="14%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="23%"  height="30">&nbsp;</td>
</tr>

<tr>
<td width="17%"  height="24" align="left" valign="top">
 <p align="left"><font size="1" face="Arial"><b>
  <bean:message key="corporate.Nods" /></b></font></td>
<td width="46%"  height="24" align="left" valign="top">
<html:text property="nc_start" readonly="true"/><input onclick="c2.popup('nc_start');" tabIndex="6" type="button" value="..."/>  	
</td>
 <td width="14%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="23%"  height="30">&nbsp;</td>
</tr>

<tr>
<td width="17%"  height="24" align="left" valign="top">
 <p align="left"><font size="1" face="Arial"><b>
   <bean:message key="corporate.Node" /></b></font></td>
<td width="46%"  height="24" align="left" valign="top">
<html:text property="nc_end" readonly="true"/><input onclick="c2.popup('nc_end');" tabIndex="6" type="button" value="..."/>  	
</td>
 <td width="14%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="23%"  height="30">&nbsp;</td>
</tr>

<tr>
<td width="17%"  align="center">
 <p align="left"><font size="1" face="Arial"><b>
   <bean:message key="corporate.Status" /></b></font></td>
<td width="14%"  height="24" align="left" valign="top">
<html:text property="status" value="n" disabled="true" />	
</td>
 <td width="46%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="23%"  height="30">&nbsp;</td>
</tr>

<tr>
<td width="17%"  align="center">
 <p align="left"><font size="1" face="Arial"><b>
  <bean:message key="corporate.Desc" /></b></font></td>
<td width="14%"  height="24" align="left" valign="top">
<html:text property="description" />	
</td>
 <td width="46%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="23%"  height="30">&nbsp;</td>
</tr>

<tr>
<td width="17%" height="27" align="center" bgcolor="#D6D6D6"/>
<td width="14%" height="27" align="center" bgcolor="#D6D6D6">
<html:hidden property="event_button" value="" />
<html:submit value="Submit"  style="float: left" onclick="return test()" /></td>
<td width="46%" bgcolor="#D6D6D6" height="30"></td>
<td width="23%" bgcolor="#D6D6D6" height="30">&nbsp;</td>
 </table>
 </td></tr></table>
 </html:form>
 <SCRIPT language="javascript">
 function test()
 {
 	document.forms[0].event_button.value="Submit";
 	document.forms[0].submit();
 }
 function amttest()
 {
 	document.forms[0].event_button.value="Amt";
 	document.forms[0].submit();
 }
 function radsub()
{	
	document.forms[0].event_button.value="Radio";
	document.forms[0].submit();
}
 
 </SCRIPT>
 </html>