<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="java.sql.*,app.*,harrier.income.com.masters.*,java.util.*,harrier.income.com.entities.*"%>
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
<html:html> 

	<head>
	
	</head>
	<body onload="setf_add();"> 
		<html:form action="/addCompanyAction" onsubmit="return validate();">
 		<link href="./StyleSheet.css" rel="stylesheet" type="text/css">
		<html:errors/>
		<p></p>
		<center>
		<table width="120%" class="sample">
        	<tr><td width="120%" nowrap="nowrap">
			<table border="0" width="100%" cellspacing="0" cellpadding="0" >	
  			<tr>
   				<td width="50%" nowrap="nowrap">
    			<table border="0" width="100%" cellspacing="0" cellpadding="0">
    			<tr>
    				<td  align="left"  colspan="2" nowrap="nowrap"></td>
					<td  align="left" colspan="2" nowrap="nowrap"></td>
               	    <td  align="left"  width="6%" nowrap="nowrap"></td>
                    <td  align="left"  width="6%" nowrap="nowrap"></td>
    		    </tr>
    		    <tr>
                    <td width="100%" valign="bottom"  nowrap="nowrap"><b><bean:message key="Masters.AddCompany" /> </b></td>
                </tr>                
			    <table border="0" width="100%" cellspacing="0" cellpadding="0" height="152" class="sample">
  				<tr>
  					<td width="15%" height="42" align="right" valign="bottom" class="tab" nowrap="nowrap">
						<bean:message key="Masters.CompanyName"/>&nbsp;&nbsp;
  					</td>
  				    <td width="34%" colspan="4" height="42" align="left" valign="bottom" nowrap="nowrap">
      					<html:text property="cmp_name"  size="46" tabindex="1">
						</html:text>
    				</td>
      				<td align="left" width="50%" class="tab" valign="bottom" nowrap="nowrap">
  						<bean:message key="Masters.CompanyUpdate" /><br>
  						<html:select property="selectCompany" size="1"   onchange="popdata()" styleId="sIE">
							<html:optionsCollection property="selectCompanyCollection" name="AddCompanyForm"/>
						</html:select>
	  				</td>
   				</tr>
   				<tr>
   					<td align="left" height="69" class="tab" nowrap="nowrap">
  					&nbsp;<td align="left" colspan="5" height="69" class="tab" nowrap="nowrap">
  					<fieldset style="width: 202px; height: 52px; padding: 2">
					<legend><bean:message key="Masters.Action"/></legend>
					<input type="radio" value="N" name="new1" onclick="check1();" checked> 
					<bean:message key="stockmaster.new"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					<input type="radio" name="update" value="U" onclick="check2();"><bean:message key="classes.update"/></fieldset><tr>
   					<td align="right" nowrap="nowrap">
  					&nbsp;<td align="center" width="6%" nowrap="nowrap">
  					<input type="submit" name="add" value='<bean:message key="defineIndex30"/>' ><td align="center" width="9%" >
  					<html:button property="resetButton" onclick="resetFunc();"  > 
		       		<bean:message key="indexUpdate.reset"/>
             		</html:button><td align="center" width="7%" >
  					<html:button onclick="history.go(-1)" property="">
         				<bean:message key="indexUpdate.cancel"/>
      				</html:button> 
  					&nbsp;<td align="center" nowrap="nowrap">
  					&nbsp;<td align="left" valign="top" width="50%" nowrap="nowrap">
  					&nbsp;</td>
   				</table>
  			</table>
  		</td>
  	</tr>
  </table>
 </center>
</html:form>
<html:form action="/addCompanyAction">

 <p>&nbsp;</p>
	<html:hidden property="id" value=" " />
	
	<script language="JavaScript">
			document.forms[0].cmp_name.focus();
			document.forms[0].new1.checked = true;
			document.forms[0].update.checked = false;
    </script>	
	</td></tr></table> 
</html:form>
<script language="JavaScript">
var field_value,flag;
var res;
function validate()
{
	field_value = document.forms[0].cmp_name.value;
	if(field_value == "")
	{
		alert("Please enter company name."); 
		return false;
	}
	else if(document.forms[0].new1.checked==false && document.forms[0].update.checked==false)
	{
		alert("Please select action.");
		return false;
	}
	else if(flag == 1)
	{
		alert("Please select from list to update.");
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
function setf_add()
{
	
	document.forms[0].cmp_name.focus();
}
function popdata()
{
	flag = 0;
    document.forms[1].id.value = document.forms[0].selectCompany.value;
    document.forms[1].submit();
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
function sel_new()
{
	if(document.forms[0].update.checked == false)
	  document.forms[0].new1.checked = true;
	  flag = 1;
}
function resetFunc()
{
	res=1;
	document.forms[0].cmp_name.value="";
	document.forms[0].cmp_name.focus();
}	
</script>
</html:html>