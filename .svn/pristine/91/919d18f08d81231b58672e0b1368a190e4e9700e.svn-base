<!-- Preference Master---------------- -->
<!-- neha ---------------------------- -->

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page language="java" import="app.*"%>
<%@ page import="harrier.income.com.report.*" %>
<%@ page import = "java.io.PrintWriter" %><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setHeader("Expires","0");

%>
<jsp:useBean id="ReportPerNameBean" scope="session" class="harrier.income.com.report.ReportPerNameForm"/>
<html:html> 
	<head>
	</head>
	<body onload="setf_add();" class="b2"> 
		<html:form action="/ReportPreName" onsubmit="return validate();">
 		<link href="./StyleSheet.css" rel="stylesheet" type="text/css">
		<html:errors/>
		<p></p>
		<center>
		<table width="100%" class="sample">
        	<tr><td width="100%" nowrap="nowrap">
			<table border="0" width="100%" cellspacing="0" cellpadding="0">	
  			<tr>
   				<td width="50%" nowrap="nowrap">
    			<table border="0" width="100%" cellspacing="0" cellpadding="0">
    			<tr>
    				<td  align="left"  colspan="2" nowrap="nowrap"></td>
					<td  align="left" colspan="2" nowrap="nowrap"></td>
               	    <td  align="left" bgcolor="white" width="6%" nowrap="nowrap"></td>
                    <td  align="left" bgcolor="white" width="6%" nowrap="nowrap"></td>
    		    </tr>
    		    <tr>
                    <td width="100%" valign="bottom" class="subHeader" nowrap="nowrap"><b><bean:message key="Report.prehead" /> </b></td>
                </tr> 
                <table border="0" width="100%" cellspacing="0" cellpadding="0" height="152">
  				<tr>
  					<td width="15%" height="42" align="right" valign="bottom" class="tab" nowrap="nowrap">
						<bean:message key="Report.prename"/>&nbsp;&nbsp;
  					</td>
  				    <td width="24%" colspan="4" height="42" align="left" valign="bottom" nowrap="nowrap">
      					<html:text property="prefer_name" name="ReportPerNameBean"  size="30" tabindex="1">
						</html:text>
    				</td>
                <td align="left" width="40%" class="tab" valign="bottom" nowrap="nowrap">
  						<bean:message key="Report.nameupdate" />
  						<html:select property="selectprename" size="1"  style="width=175" onchange="popdata();" styleId="sIE">
							<html:optionsCollection property="prenameCollection" name="ReportPerNameBean"/>
						</html:select>
	  				</td>
   				</tr>
                <tr>
   					<td align="left" height="69" class="tab" nowrap="nowrap">
  					&nbsp;<td align="left" colspan="5" height="69" class="tab" nowrap="nowrap">
  					<fieldset style="width: 202px; height: 52px; padding: 2">
					<legend><bean:message key="Report.action"/></legend>
					<input type="radio" value="A" name="additem" onclick="check1();" checked> 
					<bean:message key="Report.add"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					<input type="radio" name="update" value="U" onclick="check2();">
					<bean:message key="Report.update"/></fieldset><tr>
   					<td align="right" nowrap="nowrap">&nbsp;
   					<td align="center" width="6%" nowrap="nowrap">
  					<html:button property="add" onclick="final();" ><bean:message key="defineIndex30"/></html:button>
  					</td>
  					<td align="center" width="9%" >
  					<html:button property="resetButton" value ="Reset" onclick="resetFunc();"  > 
		       		<bean:message key="indexUpdate.reset"/>
             		</html:button>
             		</td>
             		<td align="center" width="6%" >
  					<html:button property="backButton" onclick="backFunction();"  > 
		       		<bean:message key="indexUpdate.back"/>
             		</html:button>
             		</td>
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

 <html:form action="/ReportPreName">
 <p>&nbsp;</p>
	<html:hidden property="id" value=" " />
	
	<script language="JavaScript">
			document.forms[0].prefer_name.focus();
			document.forms[0].additem.value = true;
			document.forms[0].update.value = false;
    </script>	
	</td></tr></table> 
</html:form> 
<script language="JavaScript">
var flag;
var res;

function final()
{

      if(document.forms[0].prefer_name.value =="")
    {
       alert("Please enter Preference name."); 
       document.forms[0].prefer_name.focus();
       return false;
    }
     else
    {
       document.forms[0].submit();
     }
}

function setf_add()
{
	
	document.forms[0].prefer_name.focus();
}
  function popdata()
{
	flag = 0;
    document.forms[1].id.value = document.forms[0].selectprename.value;
    document.forms[1].submit();
}

function done1()
{

//alert(document.forms[0].operation.value);
 document.forms[0].submit();
	
}	
 	
    
	
	  

function resetFunc()
{
	
	document.forms[0].prefer_name.value="";
	document.forms[0].prefer_name.focus();
}

function check()
{
    document.forms[0].submit();

}
function validate()
{
	field_value = document.forms[0].prefer_name.value;
	if(field_value == "")
	{
		alert("Please enter Preference  name."); 
		return false;
	}
	else if(document.forms[0].additem.checked==false && document.forms[0].update.checked==false)
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
function check1()
{
	if(document.forms[0].update.checked == true)
	  document.forms[0].update.checked = false;
}
function check2()
{
	if(document.forms[0].additem.checked == true)
	  document.forms[0].additem.checked = false;
} 	
function backFunction(){
history.go(-1);
}
</script>
</html:html>             
                
                
                
                      