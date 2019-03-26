<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page import="app.*,java.util.*"%> 
<%@page import="org.apache.log4j.Logger"%>
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
				response.sendRedirect("userlogintemp.jsp");
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
<jsp:useBean id="corporate" scope="session" class="app.Corporate"/> 

			<!--Form[0] starts here-->
<html:form action="/corpDairy_Action1">
<html:errors/>
<table width="102%" class="sample">
 <tr><td width="102%"> 
<table border="0" width="100%" cellspacing="0" cellpadding="0">		

<logic:equal value="1" property="exist" name="corporate">
<font size="2" face="Arial" color="Red"><b>
<bean:message key="corporate.Newexist" />
</b></font><BR>
<html:radio property="ind_comp" value="c" onclick="return radsub()"/>
<bean:message key="corporate.Continue" />
<html:radio property="ind_comp" value="b" onclick="return radsub()"/>
<bean:message key="corporate.Back" />
</logic:equal>

<tr>
<td width="20%" align="left" bgcolor="#D6D6D6"><font size="2" face="Arial"><b>
<bean:message key="corporate.indHeading" />
</b></font></td>
<td width="11%" bgcolor="#D6D6D6"/>
 <td width="46%" bgcolor="#D6D6D6" height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="23%" bgcolor="#D6D6D6" height="30">&nbsp;</td>
</tr>

<tr>
<td width="17%"  align="center">
 <p align="left"><font size="1" face="Arial"><b>
 <bean:message key="corporate.Index" />
  </b></font></td>
<td width="14%"  height="24" align="left" valign="top">
<p align="left"><html:select size="1" property="i_index" onchange="return test()">
<html:optionsCollection name="corporate" property="indCollection" />
</html:select>
</td>
<td width="46%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="23%"  height="30">&nbsp;</td>
</tr>

<tr>
<td width="17%"  height="24" align="left" valign="top">
 <p align="left"><font size="1" face="Arial"><b>
 <bean:message key="corporate.Action" />
  </b></font></td>
<td width="46%"  height="24" align="left" valign="top">
<p align="left"><html:select size="1" property="corpid" onchange="return test1()">
<html:optionsCollection name="corporate" property="indcorpCollection" />
</html:select>
</td>
 <td width="14%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="23%"  height="30">&nbsp;</td>
</tr>

<logic:equal value="rebasing" property="corpid" name="corporate">
<tr>
<td width="10%"  height="24" align="left" valign="top">
<p align="left"><font size="1" face="Arial"><b>
<bean:message key="corporate.Based" />
</b></font></td>
<td width="10%"  height="24" align="left" valign="top">
<html:text property="base_date" /><input onclick="c2.popup('base_date');" tabIndex="6" type="button" value="..."/>  			
</td>
<td width="32%"  height="30">
<p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
</b></font></td>
<td width="46%"  height="30">&nbsp;</td>
</tr>	

<tr>
<td width="10%"  height="24" align="left" valign="top">
<p align="left"><font size="1" face="Arial"><b>
<bean:message key="corporate.Basev" />
 </b></font></td>
<td width="10%"  height="24" align="left" valign="top">
<html:text property="value" />
</td>
 <td width="32%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="46%"  height="30">&nbsp;</td>
</tr>
</logic:equal>

<logic:equal value="addstock" property="corpid" name="corporate">
<tr>
<td width="17%"  align="center">
 <p align="left"><font size="1" face="Arial"><b>
 <bean:message key="sysConfigForm.stockExId" />
 </b></font></td>
<td width="14%"  height="24" align="left" valign="top">
<p align="left"><html:select size="1" property="exc" onchange="return test4()" >                  
<html:optionsCollection name="corporate" property="exc1Collection" />
</html:select>
</td>
 <td width="46%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="23%"  height="30">&nbsp;</td>
</tr>

<tr>
<td width="17%"  align="center">
 <p align="left"><font size="1" face="Arial"><b>
 <bean:message key="corporate.stock" />
  </b></font></td>
<td width="14%"  height="24" align="left" valign="top">
<p align="left"><html:select size="5" property="stock" multiple="true" >                  
<html:optionsCollection name="corporate" property="stkmulCollection" />		                   	 
</html:select>
</td>
 <td width="46%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="23%"  height="30">&nbsp;</td>
</tr>
</logic:equal>		

<logic:equal value="deletestock" property="corpid" name="corporate">
<tr>
<td width="17%"  align="center">
 <p align="left"><font size="1" face="Arial"><b>
 <bean:message key="corporate.stock" />
  </b></font></td>
<td width="14%"  height="24" align="left" valign="top">
<p align="left"><html:select size="5" property="stock" multiple="true" >                  
<html:optionsCollection name="corporate" property="stkmulCollection" />		                   	 
</html:select>
</td>
 <td width="46%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="23%"  height="30">&nbsp;</td>
</tr>
</logic:equal>

<logic:equal value="changeiwf" property="corpid" name="corporate">
<tr>
<td width="17%"  align="center">
 <p align="left"><font size="1" face="Arial"><b>
 <bean:message key="corporate.stock" />
  </b></font></td>
<td width="14%"  height="24" align="left" valign="top">
<p align="left"><html:select size="5" property="stock" multiple="true" >                  
<html:optionsCollection name="corporate" property="stkmulCollection" />		                   	 
</html:select>
</td>
 <td width="46%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="23%"  height="30">&nbsp;</td>
</tr>

<tr>
<td width="10%"  height="24" align="left" valign="top">
<p align="left"><font size="1" face="Arial"><b>
 <bean:message key="corporate.Value" />
   </b></font></td>
<td width="10%"  height="24" align="left" valign="top">
<html:text property="value" />
</td>
 <td width="32%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="46%"  height="30">&nbsp;</td>
</tr>
</logic:equal>

<tr>
<td width="17%"  height="24" align="left" valign="top">
 <p align="left"><font size="1" face="Arial"><b>
  <bean:message key="corporate.Announce" /> </b></font></td>
<td width="46%"  height="24" align="left" valign="top">
<html:text property="announce_date" readonly="true"/><input onclick="c2.popup('announce_date');" tabIndex="6" type="button" value="..."/>  	
</td>
<td width="14%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="23%"  height="30">&nbsp;</td>
</tr>

<tr>
<td width="17%"  height="24" align="left" valign="top">
 <p align="left"><font size="1" face="Arial"><b>
  <bean:message key="corporate.Ex" />
  </b></font></td>
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
  <bean:message key="corporate.Record" />
  </b></font></td>
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
  <bean:message key="corporate.Apply" />
  </b></font></td>
<td width="46%"  height="24" align="left" valign="top">
<html:text property="apply_date" readonly="true"/><input onclick="c2.popup('apply_date');" tabIndex="6" type="button" value="..."/>  		
</td>
 <td width="14%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="23%"  height="30">&nbsp;</td>
</tr>

<tr>
<td width="17%"  align="center">
 <p align="left"><font size="1" face="Arial"><b>
  <bean:message key="corporate.Status" />
   </b></font></td>
<td width="14%"  height="24" align="left" valign="top">
<html:text property="status" value="n" disabled="true"/>	
</td>
 <td width="46%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="23%"  height="30">&nbsp;</td>
</tr>

<tr>
<td width="17%" bgcolor="#D6D6D6" align="center">
<td width="14%" bgcolor="#D6D6D6">
<html:submit value="Submit"  style="float: left" onclick="return sub1()"/>
<html:submit value="Back"  style="float: left" onclick="return sub2()"/>
 <td width="46%" bgcolor="#D6D6D6" height="30">
</td>
<td width="23%" bgcolor="#D6D6D6" height="30">&nbsp;</td>
</tr>  

</table>
<html:hidden property="new_index_but" value=""/>
</td></tr></table>
<%
	  //for inactive stock display
	  ArrayList a1=corporate.getStk_status();
	  if(!(a1.isEmpty()))
	  {
		  Object obj[]=a1.toArray();
		  int len=obj.length;
		  if(len==1)
		  {%>
			<input type="hidden" name="obj_val"  value="<%=obj[0].toString()%>"  />			  
			<script>
	 	    document.forms[0].stock.options[document.forms[0].obj_val.value].style.color="Red";
			</script>
		  <%}else{		  		  
			  for(int i=0;i<obj.length;i++){%>
		 	  <input type="hidden" name="obj_val"  value="<%=obj[i].toString() %>"  />
			  <%}%>
			  <script>
		 	  var len=document.forms[0].obj_val.length;
		 	  for(var i=0;i<len;i++)
			 	  document.forms[0].stock.options[document.forms[0].obj_val[i].value].style.color="Red";
	 	      </script> 	  	 
			<%}
		}%>
</html:form> 
<script>
function test()
{
	document.forms[0].new_index_but.value="Index";
	document.forms[0].submit();
}
function test1()
{
	document.forms[0].new_index_but.value="Action";
	document.forms[0].submit();
}
function test4()
{
	document.forms[0].new_index_but.value="Exc";
	document.forms[0].submit();
}
function sub1()
{
	document.forms[0].new_index_but.value="Submit";	
}
function sub2()
{
	document.forms[0].new_index_but.value="Back";	
	document.forms[0].submit();
}
function radsub()
{	
	document.forms[0].new_index_but.value="Radio";
	document.forms[0].submit();
}
</script>
</html>