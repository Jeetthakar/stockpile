<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>	
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
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
<body onload="setf();"  class="b2">
<html:form action="/ind" onsubmit="return check_v();">
	<META Http-Equiv="Cache-Control" Content="no-cache">
	<META Http-Equiv="Pragma"        Content="no-cache">
	<META Http-Equiv="Expires"       Content="0">
	<link rel="stylesheet" type="text/css" href="./StyleSheet.css"  title="Default" />  
	<html:errors/><font color="blue" >
		</font>
		<p></p>
	<table width="100%" class="sample">
     <tr>
     	<td width="100%" nowrap="nowrap">
		<table border="0" width="100%" cellspacing="0" cellpadding="0" height="50">	
		<tr>
            <td align="left" class="subHeader" nowrap="nowrap">
				<b><bean:message key="defineIndex6" /></b>
			</td>
        </tr>   
    	<tr>
    		<td > <p>&nbsp;</p></td>
	 	</tr>
			<table border="0" width="100%" cellspacing="0" cellpadding="0" height="272">	
		<tr>
			<td width="238"  height="38" valign="bottom" nowrap="nowrap">
				<p align="right"><font face="Arial" size="2"><bean:message key="corporate.Indname" /><font color="red">*</font>&nbsp;:</font></td>
			<td width="350" colspan="3"  height="38" valign="bottom" nowrap="nowrap"><font face="Arial">
				<html:text property="name"  size="30" tabindex="1">
				</html:text></font></td>
			<td rowspan="6" align="left" valign="top" nowrap="nowrap">
				<p style="margin-top: 0; margin-bottom: 0"><font face="Arial" size="2"><bean:message key="Masters.ExistingIndexTypes" /></font></p>
				<p style="margin-top: 0; margin-bottom: 0">
				<font face="Arial">
				<html:select property="selectIndexType" size="1"   onchange="popdata()" styleId="sIE">
					<html:optionsCollection property="selectIndexTypeCollection" name="IndexType"/>
				</html:select>
				</font>
			</td>
		</tr>
		<tr>
			<td width="238"  height="39" nowrap="nowrap">
			<p align="right"><font face="Arial" size="2"><bean:message key="Masters.IndexDes" /><font color="red">*</font>&nbsp;:</font></td>
			<td width="350" colspan="3"  height="39" nowrap="nowrap"><font face="Arial" >
			<html:text property="desc"  size="46" tabindex="2">
			</html:text>
			</font></td>
		</tr>
		<tr>
			<td width="238" height="27" nowrap="nowrap">
			<p align="right"><font size="2" face="Arial"><bean:message key="Masters.IndexTypeCode" /><font color="red">*</font> : </td>
			<td width="350" height="27" colspan="3" nowrap="nowrap">
			</font></b></font>
			<html:text property="type_code"  size="17" tabindex="3">
			</html:text>
			</td>
		</tr>
		<tr>
			<td width="238" height="27" nowrap="nowrap">
			<p align="right"><font size="2" face="Arial"><bean:message key="Masters.IndexTypeSegment"/><font color="red">*</font> : </td>
			<td width="25%">
            	<html:select styleId="Rem" style="font-family:Arial, Helvetica, sans-serif;font-size:12px; width:148px;" property="selectSegment" >
                	<html:option value="0" >Not Selected</html:option>
                    <html:option value="EQ" >EQ</html:option>
                    <html:option value="FI" >FI</html:option>
                </html:select>
	        </td>  
		</tr>
		<tr>
			<td width="238" height="26" nowrap="nowrap">&nbsp;</td>
			<td width="350" height="26" colspan="3" nowrap="nowrap">
				<fieldset style="width: 183px; height: 46px; padding: 2">
				<legend><font face="Arial" size="2"><bean:message key="Masters.Action" /></font></legend>
				<font face="Arial">
				<input type="radio" value="N" name="new1" checked onclick="check1();" tabindex="4"><font size="2"><bean:message key="stockmaster.new" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</font>
				<input type="radio" name="update" value="U" onclick="check2();" tabindex="5"><font size="2"><bean:message key="classes.update" /></font></font></fieldset>
			</td>
		</tr>
		<tr>
			<td width="238"  height="38" nowrap="nowrap">&nbsp;</td>
			<td width="70"  height="38" nowrap="nowrap"><font face="Arial">
				<input type="submit" value='<bean:message key="defineIndex30"/>' name="B1" tabindex="6" onclick="return Itestcheck()"></font></td>
			<td width="59"  height="38" nowrap="nowrap"><font face="Arial">
				<html:button property="resetButton" onclick="resetID();"  > 
		       		<bean:message key="indexUpdate.reset"/>
             		</html:button></font>
			</td>
			<td width="221"  height="38" nowrap="nowrap"><font face="Arial" >
				<html:button onclick="history.go(-1)" property="B2" tabindex="7">
         			<bean:message key="indexUpdate.cancel"/>
      			</html:button>   </font>
      		</td>
		</tr>
		<tr>
			<td width="238"  height="53" nowrap="nowrap">&nbsp;</td>
			<td width="350" colspan="3"  height="53" nowrap="nowrap">
				<font face="Arial" size="2"></font>
		  	</td>
		</tr>
	</table>
 </table>
</td></tr></table> 	
</html:form>
<html:form action="/ind">
	<html:hidden property="idname" value=" "/>
	<script language="JavaScript">
			if(document.forms[0].new1.checked == true)
				document.forms[0].new1.checked = false;
			document.forms[0].update.checked = true;
	</script>	
	<p>&nbsp;</p>
	</td></tr></table> 		
</html:form>
<script language="JavaScript">
var field_value;
var res1 = 0;
function check_v()
{
	
	field_value = document.forms[0].name.value;
	if(field_value == "")
	{
		alert("Enter Index name.");
		return false;
	}
	
	
	else if(document.forms[0].desc.value == "")
   {
     alert("Please Enter Index description");
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
   
   else if(document.forms[0].selectSegment.value == "0"||document.forms[0].selectSegment.value == "Not Selected")
   {
     alert("Please Select Segment.");
     return false;
   }
	
	else
	{
		return true;
	}
}
function resetID()
{
	
	document.forms[0].name.value="";
	document.forms[0].desc.value="";
	document.forms[0].type_code.value="";
	document.forms[0].name.focus();
}
function setf()
{
	document.forms[0].name.focus();
}
function popdata()
{
	if(document.forms[0].selectIndexType.value != "")
	{
	    document.forms[1].idname.value =	document.forms[0].selectIndexType.value;
	    document.forms[1].submit();
    }
}
function check1()
{
	if(document.forms[0].update.checked==true)
	  document.forms[0].update.checked = false;
}
function check2()
{
	if(document.forms[0].new1.checked==true)
	  document.forms[0].new1.checked = false;
}

function Itestcheck()
{
	var iChars = ".()!@#$%^&*+=-[]\\\';,/{}|\":<>?0123456789";
	var mChars = "!@#$%^&*+=[]\\\';,/{}|\":<>?";
	var CodeChars = ".()!@#$%^&*+=-[]\\\';,/{}|\":<>?";

	var msg='';
   	if(document.forms[0].name.value == "")
   	{
     	//alert("Please Enter Index Name.");
      msg=msg+'Please Enter Index Name\n';
   	}
  	 else if(document.forms[0].name.value != "")
   	{
   		if (iChars.indexOf(document.forms[0].name.value.charAt(0)) != -1) {
  				alert ("The first character must be alphabet in Index name.");
  			return false;
  			}
  			else 
  			{
  				for (var i = 0; i < document.forms[0].name.value.length; i++) {
  						if (mChars.indexOf(document.forms[0].name.value.charAt(i)) != -1) 
  						{
  							alert ("This special character not allowed within Index name.");
							return false;
  						}
  					}
  			}
   }
   
   if(document.forms[0].desc.value == "")
   {
   		msg=msg+'Please Enter Index description\n';
   		//alert("Please Enter Index description");
   }
   else if(document.forms[0].desc.value != "")
   {
   		if (CodeChars.indexOf(document.forms[0].desc.value.charAt(0)) != -1) {
  				alert ("The first character must be alphabet in Index description.");
  			return false;
  			}
  			
   }
   
   if(document.forms[0].type_code.value == "")
   {
     msg=msg+'Please Enter Index type code\n';
   }
   else if(document.forms[0].type_code.value != "")
   {
   		if (CodeChars.indexOf(document.forms[0].type_code.value.charAt(0)) != -1) {
  				alert ("The first character must be alphabet in Index type code.");
  			return false;
  			}
  			else 
  			{
  				for (var i = 0; i < document.forms[0].type_code.value.length; i++) {
  						if (mChars.indexOf(document.forms[0].type_code.value.charAt(i)) != -1) 
  						{
  							alert ("This special character not allowed within Index type code.");
							return false;
  						}
  					}
  			}
   }
    if(msg==""){
	 return true;
	}else
	{
	 alert(msg);
 	 return false;
	 }
   
 }
</script>
</body>
</html>
