<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page language="java" import="app.*"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "harrier.income.com.masters.*" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.Locale,java.util.*" %>
<%@ page import = "java.sql.*" %><%@page import="org.apache.log4j.Logger"%>
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
			response.sendRedirect("../userlogintemp.jsp");
		}		
%>
<%
	response.setHeader("Cache-Control","no-cache"); 
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", -1);
	
%>
<html>
<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">


</head>
<html:base/>
<jsp:useBean id="corp" scope="session" class="harrier.income.com.masters.CorporateActionMaster"/>		
<body onload="setf();" class="b2">
<html:form action="/corpaction" onsubmit="return val_form();" >
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
	<meta name="GENERATOR" content="Microsoft FrontPage 6.0">
	<meta name="ProgId" content="FrontPage.Editor.Document">
	<link rel="stylesheet" type="text/css" href="./StyleSheet.css"  title="Default" />   
	<html:errors/><font color="blue" >
		<%	String conf_act = (String)session.getAttribute("conf_act");
			log.info("value disp......."+conf_act);
			if(conf_act == "U")
			{
		%>
			<bean:message key="icmUpdate" />
		<%	}
		    else if(conf_act == "N")
			{
		%>
			<bean:message key="icmAdd" />
		<%	} %>
		</font>
	<p></p>
	<table width="120%" class="sample">
        	<tr><td width="120%" nowrap="nowrap">
	<table border="0" width="100%" height="10" cellspacing="0" cellpadding="0" >
		<tr>
			<td width="23%" height="28"  class="subHeader" nowrap="nowrap">
			<b><bean:message key="corporate.Action" /></b></td>
			
	    </tr>
	    
	<table border="0" width="100%" height="370" cellspacing="0" cellpadding="0" >
		
		<tr>
			<td width="23%" valign="bottom" nowrap="nowrap">
			<p align="right"><font size="2" face="Arial"><bean:message key="Masters.CAName" /></font></td>
			
			<input type="hidden" name="namefield">
				
			<td width="37%" colspan="3" valign="bottom" nowrap="nowrap">
			
			<input type="text" name="name" size="44" >
		   
			</td>
			<td rowspan="7" align="left" valign="top" width="39%" nowrap="nowrap">
			
			
			<p style="margin-top: 0; margin-bottom: 0">
				
			<font face="Arial" size="2" color="#0066FF">&nbsp&nbsp;<bean:message key="Masters.ExistingCAs" /></font></p>
		<p></p>
			<p style="margin-top: 0; margin-bottom: 0">
			<html:select property="selectCA" size="1"   onchange="sub_form()" styleId="sIE">
					<html:optionsCollection property="selectCACollection" name="corp"/>
			</html:select>
			
			<!--<select size="1" name="list" onchange="sub_form();">
			<option value="0"><bean:message key="Masters.SelectCA" /></option>
			<%    
					CorporateActionMaster con = new CorporateActionMaster();
        			con.setEx_actions();
        			Vector vi  = con.getEx_actions();
        			Iterator i = vi.iterator();
 					int id;
   					while(i.hasNext())
       				{     
    					  id= Integer.parseInt(i.next().toString());
    			      		
        	 	%>
	                       	<option value="<%=id%>"><%= i.next() %></option>
           		<%  }
           		
				%>	
			
			</select>-->
			</td>
		</tr>
		<tr>
			<td width="23%" nowrap="nowrap">
			<p align="right"><font size="2" face="Arial"><bean:message key="Masters.CAShortName" /></font>
			</td>
			<td width="37%" colspan="3" nowrap="nowrap">
			
			<input type="text" name="shname" size="28" ><font size="1" face="Arial" color="#0066FF"><bean:message key="Masters.NotUpdatable" /></font></td>
		</tr>
		<tr>
			<td width="23%" height="46" nowrap="nowrap">
			<p align="right"><font size="2" face="Arial"><bean:message key="Masters.CAType" /></font></td>
			<td width="37%" colspan="3" height="46" nowrap="nowrap">
			<fieldset style="padding: 2; width:236px; height:40px">
			<legend align="left"><font size="2" face="Arial"><bean:message key="Masters.SelectEvent" /></font></legend>
			&nbsp;<font face="Arial" size="2">&nbsp;&nbsp;<bean:message key="corporate.Index" />&nbsp;
			
			<input type="radio" value="I" name="event_index" onclick="check_event1();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="corporate.stock" />&nbsp;</font>&nbsp; 
			<input type="radio" name="event_stock" value="S" onclick="check_event2();"></fieldset></td>
		</tr>
		<tr>
			<td width="23%" height="38" nowrap="nowrap">
			<p align="right"><font size="2" face="Arial"><bean:message key="timeZone.desc" /></font></td>
			<td width="37%" colspan="3" height="38" nowrap="nowrap">
			
			<input type="text"  name="desc" size="44"></textarea></td>
		</tr>
		<tr>
		
			<td width="23%"  valign="bottom" height="44" nowrap="nowrap">
			<p align="right">&nbsp;</td>
			<td width="37%" colspan="3"  valign="bottom" height="44" nowrap="nowrap">
			<table border="1" width="90%" height="57" cellspacing="0" cellpadding="0" bordercolor="#D8D8D8">
				<tr>
					<td nowrap="nowrap"><font size="2" face="Arial"><bean:message key="Masters.AdjustPrice" />&nbsp;
					<input type="checkbox" name="price" value="ON">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					<bean:message key="Masters.AdjustDivisor" />&nbsp;
					<input type="checkbox" name="divisor" value="ON"></font></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="23%" height="40" nowrap="nowrap">&nbsp;</td>
			<td width="37%" colspan="3" height="40" nowrap="nowrap">
			<font size="2">
			<fieldset style="width: 240px; height: 43px; padding: 2">
			<legend><font face="Arial"><bean:message key="Masters.Action" /></font></legend>
				<font face="Arial">
			&nbsp;<bean:message key="Masters.AddNew" /><input type="radio" value="N" name="new1" onclick="check1();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			<bean:message key="classes.update" /> <input type="radio" name="update" value="U" onclick="check2();"></font></fieldset></font></td>
		</tr>
		<tr>
			<td width="23%" nowrap="nowrap">&nbsp;</td>
			<td width="9%" nowrap="nowrap">
			<input type="submit" value='<bean:message key="defineIndex30"/>' name="B1"></td>
			
			<td width="7%" nowrap="nowrap">
			<input type="reset" value='<bean:message key="indexUpdate.reset"/>' name="B3" onclick="resetID();" ></td>
			<td width="21%" nowrap="nowrap"><input type="button" value='<bean:message key="indexUpdate.cancel"/>' name="B2" onclick="history.go(-1)"></td>
		</tr>
	</table>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
</html:form>
<html:form action="/corpaction">
	<p><input type="hidden" name="id" size="20"></p>
	
	<%  session.setAttribute("conf_act",null);
		String id_field=null;
		id_field = request.getParameter("id");
		
		if(id_field != null)
		{
			corp.get_data(id_field);
				log.info("NAME IS.... "+id_field);
				%>
				<input type="hidden" name="name_h" value="<%=corp.getName_h()%>" >
				<input type="hidden" name="shname_h" value="<%=corp.getShname_h()%>">
				<input type="hidden" name="type_h" value="<%=corp.getType_h() %>" >
				<input type="hidden" name="desc_h" value="<%=corp.getDesc_h()%>">
				<input type="hidden" name="divisor_h" value="<%=corp.getDivisor_h()%>">
				<input type="hidden" name="price_h" value="<%=corp.getPrice_h()%>">
				<script language="JavaScript">
						document.forms[0].name.value = document.forms[1].name_h.value;
						var t = document.forms[1].shname_h.value;
						if(t)
						{
							document.forms[0].shname.value = document.forms[1].shname_h.value;
						}
						var tdesc = document.forms[1].desc_h.value;
						if(tdesc != 'null')
						{
							document.forms[0].desc.value = document.forms[1].desc_h.value;
						}
					   
					    var rad_type = document.forms[1].type_h.value;
					    
					    if(rad_type == 'index event')
					    {
					    	document.forms[0].event_index.checked = true;
					    	document.forms[0].event_stock.checked = false;
					    }
					    else if(rad_type == 'stock event')
					    {
					    	document.forms[0].event_stock.checked = true;
					    	document.forms[0].event_index.checked = false;
					    }
					    if(document.forms[1].divisor_h.value == 'y')
					    	document.forms[0].divisor.checked = true;
					    if(document.forms[1].price_h.value == 'y')
					    	document.forms[0].price.checked = true;
					    if(document.forms[0].new1.checked == true)
					    	document.forms[0].new1.checked = false;
					    document.forms[0].update.checked = true;
					    
			   </script>
		<% 	} %>
		</tr></td></table>
</html:form>

<script language="JavaScript">
var res;

if(document.forms[0].new1.checked==false && document.forms[0].update.checked==false){
	document.forms[0].new1.checked=true;
}
function val_form()
{
		if (document.forms[0].name.value == "")
		{	
			alert('Please enter corporate action name.');
			return false;
		}
		else if(document.forms[0].new1.checked == true)
		{
			if (document.forms[0].shname.value == "")
			{	
				alert('Please enter corporate action short name.');
				return false;
			}
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
		else return true;
}

function sub_form()
{
  document.forms[1].id.value=document.forms[0].selectCA.value;
  document.forms[1].submit();
}


function setf()
{
	document.forms[0].name.focus();
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
function check_event1()
{
	if(document.forms[0].event_stock.checked == true)
	  document.forms[0].event_stock.checked = false;
}
function check_event2()
{
	if(document.forms[0].event_index.checked == true)
	  document.forms[0].event_index.checked = false;
} 
function resetID()
{
	res = 1;
}	
</script>
</body>

</html>