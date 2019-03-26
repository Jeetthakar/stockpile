<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="harrier.income.com.masters.*"%>
<%@ page language="java" import="app.*"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.Locale,java.util.*" %><%@page import="org.apache.log4j.Logger"%>
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

<html>
<head>
<html:base/>
<meta http-equiv="Content-Language" content="en-us">
<META Http-Equiv="Cache-Control" Content="no-cache">
	<META Http-Equiv="Pragma"        Content="no-cache">
	<META Http-Equiv="Expires"       Content="0">
</head>

<body  class="b2" onload="setf();">
<html:form action="/cont" onsubmit = "return check();">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
	<meta name="GENERATOR" content="Microsoft FrontPage 6.0">
	<meta name="ProgId" content="FrontPage.Editor.Document">
	<link rel="stylesheet" type="text/css" href="./StyleSheet.css"  title="Default" />   
	 <html:errors/><font color="blue">
			<%	String conf = null;
			conf = harrier.income.com.masters.Continents.getCon_flag();
			log.info("conf: "+conf);
			if(conf!=null && conf.equals("U"))
			{
			%> 
				Updated Successfully...
			<%	}
		    else if(conf!=null && conf.equals("N"))
			{
			%>
				Added Successfully...
			<%	} %>
			<% String Pr = null;
				Pr = request.getParameter("pr"); %>
		</font>
		<p></p>
		<table width="120%" class="sample">
        	<tr><td width="120%" nowrap="nowrap">
	<table border="0" width="100%" cellspacing="0" cellpadding="0" height="178">
		<tr >
		</tr>
		<tr>
			<td  width="20%"   height="35" nowrap="nowrap" class="subHeader"><b>
			&nbsp;&nbsp;&nbsp;&nbsp;Add Continents</b></td>
			<td colspan="3"      height="35" nowrap="nowrap" class="subHeader">&nbsp;</td>
			<td width="47%"  height="35" nowrap="nowrap" class="subHeader">&nbsp;</td>
		</tr>
		<tr >
		<td>&nbsp;</td>
		</tr>
		<tr>
			<td align="right" width="20%"  height="19" nowrap="nowrap">&nbsp;</td>
			<td colspan="3"  height="19" nowrap="nowrap">&nbsp;</td>
			<td width="47%"  height="19" nowrap="nowrap"><font size="2" face="Arial" >Existing list of 
			continents.</font></td>
		</tr>
		<tr> 
			<td align="right" width="20%" nowrap="nowrap">
			<font size="2" face="Arial">Continent Name :</font></td>
			<td colspan="3" nowrap="nowrap">
			 
			<input type="text" name="name" size="33" ></td>
			<input type="hidden" name="name_check" value="" >
			<input type="hidden" name="id_check" value="" >
			<input type="hidden" name="idform1" value="" >
			<% Continents conti = new Continents();
			%>
			<td width="47%" align="left" valign="top" nowrap="nowrap">
			<select name="list" size="1" onchange="popdata();" >
			<option value="0" >Select Continent</option>
			<%    
					harrier.income.com.masters.Continents cod = new harrier.income.com.masters.Continents();
        			conti.setEx_actions();
        			Vector vi  = conti.getEx_actions();
        			Iterator i = vi.iterator();
 					int id;
   					while(i.hasNext())
       				{     
    					  id= Integer.parseInt(i.next().toString());
    			%>
	                       	<option value="<%=id%>"><%= i.next() %></option>
           		<%  }
           		
				%>	
			</select></td>
		</tr>
		<tr>
			<td align="right" width="20%" nowrap="nowrap">&nbsp;</td>
			<td colspan="3" nowrap="nowrap">
			<fieldset style="width: 175px; height: 47px; padding: 2">
			<legend><font size="2" face="Arial">Action</font></legend>
			<font face="Arial">
			<input type="radio" name="new1" checked value="N" onclick="check1();"></font><font size="2" face="Arial">New <b>&nbsp;&nbsp;&nbsp; </b>
			</font><font face="Arial">
			<input type="radio" name="update" value="U" onclick="check2();" ></font><font size="2" face="Arial">Update</font></fieldset></td>
			<td width="47%" align="left" valign="top" nowrap="nowrap">
			&nbsp;</td>
		</tr>
		<tr>
			<td align="right" width="20%"  height="49" nowrap="nowrap"></td>
			<td width="3%"  height="49" nowrap="nowrap">
			<input type="submit" value="Submit" name="B1"></td>
			<td width="1%"  height="49" nowrap="nowrap">
			</td>
			<td width="25%"  height="49" nowrap="nowrap">
			<input type="reset" value="Reset" name="B2" onclick="resetID();">
			
		   <html:button  value="Back"  onclick="history.go(-1)" property="B2">
         			
      			</html:button>
			
		    </td>
			<td width="47%" align="left" valign="top"  height="49" nowrap="nowrap">
			&nbsp;</td>
		</tr>
	</table>
	<p>&nbsp;</p>
</html:form>
<html:form action="/cont">
	<html:hidden property="idname" />
	<% harrier.income.com.masters.Continents cont = new harrier.income.com.masters.Continents();
		String id = request.getParameter("idname");
		
		if(id != null)
		{
			cont.get_name_desc(id);
	%>
	
	<input type="hidden" name="cont_name" value="<%=cont.getCont_name()%>">
	
	<script language="JavaScript">
		document.forms[0].name.value = document.forms[1].cont_name.value;
		document.forms[0].name_check.value = document.forms[1].cont_name.value;
		document.forms[0].id_check.value = document.forms[1].idname.value;
		if(document.forms[0].new1.checked == true)
			document.forms[0].new1.checked = false;
		document.forms[0].update.checked = true;
	</script>	
	<%
		log.info("in jsp  "+cont.getCont_name());
		}
	%>
	<p>&nbsp;</p>
	</td></tr></table>
</html:form>

<script language="JavaScript">
var field_value;
var res;
function resetID()
{
	res = 1;
}
function check()
{
	field_value = document.forms[0].name.value;
	if(field_value == "")
	{
		alert("Please enter continent name.");
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
	document.forms[0].name.focus();
}
function popdata()
{
    document.forms[1].idname.value =	document.forms[0].list.value;
    document.forms[0].idform1.value = document.forms[1].idname.value;
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
</script>
</body>
</html>