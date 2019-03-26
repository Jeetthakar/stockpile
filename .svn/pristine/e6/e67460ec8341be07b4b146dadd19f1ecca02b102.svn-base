<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="java.sql.*,app.*,java.util.*,harrier.income.com.masters.*,harrier.income.com.entities.*"%>
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
<body onload="setf();" class="b2">
<html:form  action="/addStockExchAction" onsubmit="return validate(this);"> 
  		<link rel="stylesheet" type="text/css" href="./StyleSheet.css"  title="Default" />   
		 <html:errors/><font color="blue" >
			</font>
		<p></p>
<center>
 	<table width="120%" class="sample">
        	<tr>
        		<td width="120%" nowrap="nowrap">
					<table border="0" width="101%" cellspacing="0" cellpadding="0">
			<tr>
    			<td nowrap="nowrap"><b><font face="Arial" color="green" size="2"></font></b></td>
	 		</tr>
   			<tr>
   				<td width="100%" nowrap="nowrap">
    			  <table border="0" width="100%"  cellspacing="0" cellpadding="2">
   					<tr>
                		<td align="left" class="subHeader" nowrap="nowrap">
							<b><bean:message key="Masters.ExchDetails"/></b>
						</td>
               		</tr>   
  					<table border="0" width="101%"   cellspacing="0" cellpadding="0" height="323">
  					<tr>
  						<td width="22%" align="right" height="38" valign="bottom" nowrap="nowrap"><font size="2" face="Arial">&nbsp;<bean:message key="corporate.Stkexname"/><font color="red">*</font>
							:&nbsp;
  						</td>
  						<td width="32%" colspan="3" height="38" valign="bottom" nowrap="nowrap">
  							<html:text property="stk_name"  size="36" tabindex="1">
							</html:text></td>
   						<td width="45%" height="142" rowspan="4" valign="top" nowrap="nowrap">
							<p style="margin-top: 0; margin-bottom: 0"><font face="Arial" size="2"><bean:message key="Masters.ExchNameUpdate"/><font color="red">*</font></font></p>
							<p style="margin-top: 0; margin-bottom: 0"><font face="Arial" size="2">
							<html:select property="selectExchange" size="1"   onchange="popdata()" styleId="sIE">
								<html:optionsCollection property="selectExchangeCollection" name="AddStockExchForm"/>
							</html:select></font>
						</td>
   					</tr>
   					<tr>
    					<td width="22%" align="right" height="40" nowrap="nowrap"><font size="2" face="Arial" >&nbsp;<bean:message key="Masters.CountryName"/><font color="red">*</font>
  							:
  						</td>
   						<td colspan="3" height="40" nowrap="nowrap">
   							<html:select property="selectCountry" size="1"    styleId="sIE">
								<html:optionsCollection property="selectCountryCollection" name="AddStockExchForm"/>
							</html:select>
  							<font size="1" face="Arial" color="#0000FF">
							<a href="./countries.jsp">
							<bean:message key="stockmaster.new"/></a></font>
  					    </td>
    			 </tr>
   				 <tr>
					<td width="22%" align="right" height="34" nowrap="nowrap"><font size="2" face="Arial">&nbsp;<bean:message key="Masters.TimeZone"/> <font color="red">*</font>
					:</td>
   					<td colspan="3" height="34" nowrap="nowrap">
   						<html:select property="selectTimeZone" size="1"    styleId="sIE">
							<html:optionsCollection property="selectTimeZoneCollection" name="AddStockExchForm"/>
						</html:select>
   						<font size="1" face="Arial" color="#0000FF">
						<a href="./TimeZone.jsp">
						<bean:message key="stockmaster.new"/></a></font></td>     
    			</tr>
    			<tr>
    				<td width="22%" align="right" height="30" nowrap="nowrap"><font size="2" face="Arial">&nbsp;<bean:message key="stockmaster.exchangeCode"/><font color="red">*</font>
  					: 
  					</td>
   					<td colspan="3" height="30" nowrap="nowrap">
   						<html:text property="stk_ex_code"  size="25" tabindex="4">
						</html:text>
  						 <font size="1"></font>
   					</td>
    			</tr>
    			<tr >
    				<td width="22%" align="right" height="32" nowrap="nowrap"><font face="Arial" size="2">
						<bean:message key="Masters.ExchStartTime"/><font color="red">*</font> : </td>
   					<td colspan="3" height="32" nowrap="nowrap">
   						<font face="Arial"><font size="1">
  						<html:text property="start_time"  size="11" tabindex="5">
						</html:text>
    					</font><font size="1">HH:MI:SS</font></font></td>
   					<td height="32" nowrap="nowrap">&nbsp;</td>
    			</tr>
    			<tr>
    				<td width="22%" align="right" height="33" nowrap="nowrap"><font face="Arial" size="2">
						<bean:message key="Masters.ExchStopTime"/><font color="red">*</font> : </td>
   					<td colspan="3" height="33" nowrap="nowrap">
  						<font size="1" face="Arial">
    					<html:text property="stop_time"  size="11" tabindex="6">
						</html:text>HH:MI:SS</font></td>
   					<td height="33" nowrap="nowrap">&nbsp;</td>
    			</tr>
    			<tr>
    				<td width="22%" align="right" height="27" nowrap="nowrap"><font size="2" face="Arial">&nbsp;<bean:message key="Masters.ExchIDTime"/><font color="red">*</font>
  					:
  					</td>
  					<td colspan="3" height="27" nowrap="nowrap">
   						<html:text property="ex_identifier_code"  size="19" tabindex="7">
						</html:text>&nbsp;
						<html:select property="selectIdCode" size="1"   onchange="pop_id()" styleId="sIE">
							<html:optionsCollection property="selectIdCodeCollection" name="AddStockExchForm"/>
						</html:select>
					</td>
						<td height="27" nowrap="nowrap">&nbsp;</td>
    			</tr>
    			<tr>
    				<td width="22%" align="right" height="51">&nbsp;</td>
   					<td colspan="3" height="51" nowrap="nowrap">
						<fieldset style="width: 185px; height: 45px; padding: 2">
						<legend><font face="Arial" size="2"><bean:message key="Masters.Action"/></font></legend>
						<font face="Arial"><font size="2">&nbsp;&nbsp; </font>
						<input type="radio" value="N" checked name="new1" onclick="check1();" tabindex="8"><font size="2"><bean:message key="stockmaster.new"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</font><input type="radio" name="update" value="U" onclick="check2();" tabindex="9"><font size="2"><bean:message key="classes.update"/></font></font></fieldset></td>
   					<td height="51" nowrap="nowrap">&nbsp;</td>
    			</tr>
   				<tr>
	 				<td align="right" nowrap="nowrap">&nbsp;</td>
   					<td width="8%" nowrap="nowrap"><input type="submit" value='<bean:message key="defineIndex30"/>' name="btn_add" tabindex="10" >&nbsp;&nbsp; </td>
   					<td width="8%" nowrap="nowrap"><left>
					<html:button property="resetButton" onclick="resetID();"  > 
		       		<bean:message key="indexUpdate.reset"/>
             		</html:button>
					<td nowrap="nowrap">
   						<html:button onclick="history.go(-1)" property="B1" tabindex="12">
         					<bean:message key="indexUpdate.cancel"/>
      					</html:button> 
   					</td>
   					<td>&nbsp;</td>
  				</tr>
  			</table>
  		</table>
  	</td>
  	</tr>
  </table>
</center>
</html:form>
  <html:form action = "/addStockExchAction" onsubmit="return flag();">
  <html:hidden property="exch_id" value=" " />
  <html:hidden property="flag_err" value=" " />
	<script language="JavaScript">
		
			document.forms[0].new1.checked = true;
		document.forms[0].update.checked = false;
	</script>	
	</td></tr></table> 
  </html:form>
<script language="JavaScript">
var field_value;
var res;
function resetID()
{
	document.forms[0].stk_name.value="";
	document.forms[0].start_time.value="";
	document.forms[0].stop_time.value="";
	
	document.forms[0].stk_ex_code.value="";
	document.forms[0].selectCountry.value="0";
	document.forms[0].selectTimeZone.value="0";
	document.forms[0].ex_identifier_code.value ="";
	document.forms[0].selectExchange.value="0";
	res = 1;
}
function validate(form)
{
	var str_start = document.forms[0].start_time.value;
	var ind_start_s = str_start.indexOf(":"); 
	var ind_start_l = str_start.lastIndexOf(":"); 
	var str_stop = document.forms[0].stop_time.value;
	var ind_stop_s = str_stop.indexOf(":"); 
	var ind_stop_l = str_stop.lastIndexOf(":");
	field_value = document.forms[0].stk_name.value;
	if(field_value == "")
	{
		alert("Please enter stock exchange name.");
		return false;
	}
	else if(document.forms[0].selctCountry.value == 0)
	{
		alert("Please select country.");
		return false;
	}
	else if(document.forms[0].selectTimeZone.value == 0)
	{
		alert("Please select time zone.");
		return false;
	}
	else if(document.forms[0].stk_ex_code.value == "")
	{
		alert("Please enter stock exchange code.");
		return false;
	}
	else if(document.forms[0].start_time.value == "")
	{
		alert("Please enter start time of exchange.");
		return false;
	}
	else if(document.forms[0].stop_time.value == "")
	{ 
		alert("Please enter stop time of exchange");
		return false;
	}
	else if(document.forms[0].ex_identifier_code.value == "")
	{ 
		alert("Please enter Exchange Identifier Code");
		return false;
	}
	
	else if(document.forms[0].new1.checked==false && document.forms[0].update.checked==false)
	{
		alert("Please select action.");
		return false;
	}
	
                var i = 0;
                oMasked = new mask();
                for (x in oMasked) {
               
                        if (!matchPattern(document.forms[0].start_time.value, oMasked[x][2]("mask"))) {
                              
                              alert("Start time should be in format hh:mm:ss.");
                              return false;
                        }
                         if (!matchPattern(document.forms[0].stop_time.value, oMasked[x][2]("mask"))) {
                             
                              alert("Stop time should be in format hh:mm:ss.");
                              return false;
                        }
                }
              

	if(document.forms[0].update.checked == true)
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
	document.forms[0].stk_name.focus();
	document.forms[1].flag_err.value =	" ";
}
function popdata()
{
    document.forms[1].exch_id.value =	document.forms[0].selectExchange.value;
    document.forms[1].submit();
}
function flag()
{
    document.forms[1].flag_err.value =	"form1";
    return true;
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
function pop_id()
{
	document.forms[0].ex_identifier_code.value = document.forms[0].selectIdCode.value;
	if(document.forms[0].ex_identifier_code.value == 0)
		document.forms[0].ex_identifier_code.value = '';
}

function mask () { 
     this.aa = new Array("m_start_time", "Start Time is invalid.", new Function ("varName", "this.mask=/^\\d{2}:\\d{2}:\\d{2}$/;  return this[varName];"));
  
} 
function matchPattern(value, mask) {
            
             return mask.exec(value);
}
</script>
</body> 
</html>