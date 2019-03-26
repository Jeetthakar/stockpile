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
			//LogonForm form = (LogonForm)session.getAttribute("user");
			//if(form == null){
				//response.sendRedirect("userlogintemp.jsp");
			//}	
			
			LogonForm form = null;
			if(request.isRequestedSessionIdValid())	
				form = (LogonForm)session.getAttribute("user");
			if(form==null ||(!request.isRequestedSessionIdValid())){
				response.sendRedirect("userlogintemp.jsp");
			}						
			String fr2=request.getParameter("from");
%>


<html>
<head>

<SCRIPT language=javascript>
function hideLeftCol(id)
{
if(this.document.getElementById(id).style.display=='none')
{
this.document.getElementById(id).style.display='inline';
document.getElementById("HideHandle").src = 'closeImage.gif';
// this.document.getElementById(id).style.width='10px';
Set_Cookie('showLeftCol','true',30,'/','','');
var show = Get_Cookie('showLeftCol');
//document['HideHandle'].src = 'images/hide.gif';
document.getElementById("HideHandle").src = '../open.gif';
}
else{
// this.document.getElementById(id).style.display='none';
this.document.getElementById(id).style.display='none';
document.getElementById("HideHandle").src = 'openImage.gif';
Set_Cookie('showLeftCol','false',30,'/','','');
var show = Get_Cookie('showLeftCol');
//document['HideHandle'].src = 'images/show.gif';
}
}
</script>

<html:base/>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 6.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<link rel="stylesheet" type="text/css" href="./StyleSheet.css"  title="Default" />   
<title>CA</title>


<meta name="Microsoft Theme" content="none"> 
<script language="javascript" src="./codethatcalendarstd.js"></script>
	<script language="javascript" src="box_ex.js"></script>
	<script language="javascript">
	var c2 = new CodeThatCalendar(caldef2);
	</script>  	
</head>

<jsp:useBean id="corporate" scope="session" class="app.Corporate"/> 

<!--Form[0] starts here-->


<table width="100%" height="100%" >

<tr>
<td align="left" valign="top" bgcolor="#CCddee">

<DIV id="leftCol" style="DISPLAY: none; width:160; height:100%;">
<%@ include file="tree3.jsp" %>
</div>
<IMG id="HideHandle" src="openImage.gif" style="CURSOR: hand; position:absolute;" onclick='hideLeftCol("leftCol");' src="fold.gif" width="10" height="25">
</td>
<td align="left" valign="top">


<html:form action="/corpDairy_Action2">
<html:errors/>
<table width="100%" class="sample">
<tr><td width="100%"> 
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

<logic:equal value="2" property="exist" name="corporate">
<font size="2" face="Arial" color="Red"><b>
<bean:message key="corporate.Newexist" />
</b></font><BR>
<html:radio property="ind_comp" value="c" onclick="return radsub()"/>
<bean:message key="corporate.Continue" />
<html:radio property="ind_comp" value="b" onclick="return radsub()"/>
<bean:message key="corporate.Back" />
</logic:equal>
 
<tr>
<td width="20%" align="left" ><font size="2" face="Arial"><b>
<bean:message key="corporate.stkHeading" />
</b></font></td>
<td width="11%" />
 <td width="46%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="23%"  height="30">&nbsp;</td>
</tr>

<tr>
<td nowrap="true"  align="center">
 <p align="left"><font size="1" face="Arial"><b>
     <bean:message key="sysConfigForm.stockExId" />
  </b></font></td>
 <td nowrap="true"  >
 
 <p align="left">
 <html:select size="1" property="exc" onchange="return test()">
<html:optionsCollection name="corporate" property="exc1Collection" />
</html:select></p>
 </td>


</tr>
<tr><td >&nbsp;</td></tr>
<tr>

<td width="17%"  align="center">
 <p align="left"><font size="1" face="Arial"><b>
 <bean:message key="corporate.stock" />
  </b></font></td>
<td width="46%"  height="24" align="left" valign="top">
<p align="left"><html:select size="1" property="s_stock">
<html:optionsCollection name="corporate" property="s_stkCollection" />
</html:select></p>
</td>
<td width="14%"  height="30">
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
<html:optionsCollection name="corporate" property="corpCollection" />
</html:select></p>    
</td>
 <td width="14%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="23%"  height="30">&nbsp;</td>
</tr>

<tr>
<td width="10%"  height="24" align="left" valign="top">
 <p align="center"><font size="1" face="Arial"><b>
 <html:radio property="amt_per" value="a">
 <bean:message key="corporate.Amount" /></html:radio>  </b></font></td>
<td width="10%"  height="24" align="left" valign="top">
 <p align="left"><font size="1" face="Arial"><b><html:radio property="amt_per" value="p">
  <bean:message key="corporate.Percent" />
  </html:radio>  </b></font>
</td>
 <td width="32%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="46%"  height="30">&nbsp;</td>
</tr>

<tr>
<td width="10%"  height="24" align="left" valign="top">
 <p align="left"><font size="1" face="Arial"><b>
 <bean:message key="corporate.AmtPer" />
 </b></font></td>
<td width="10%"  height="24" align="left" valign="top">
<html:text property="amount"/>
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
 <bean:message key="corporate.Ratio" />
 </font></td>
  <td width="14%" height="25" align="center" > <p align="left">
 <html:text property="s_ratio1" size="2"  />:<html:text property="s_ratio2" size="2"  />
</p></td>
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
 <bean:message key="corporate.Ex" />
 </b></font></td>
<td width="46%"  height="24" align="left" valign="top">
<html:text property="ex_date"  readonly="true"/><input onclick="c2.popup('ex_date');" tabIndex="6" type="button" value="..."/>  	
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
<html:text property="record_date" readonly="true" /><input onclick="c2.popup('record_date');" tabIndex="6" type="button" value="..."/>  	
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
<html:text property="apply_date" readonly="true" /><input onclick="c2.popup('apply_date');" tabIndex="6" type="button" value="..."/>  	
</td>
 <td width="14%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="23%"  height="30">&nbsp;</td>
</tr>

<tr>
<td width="17%"  height="24" align="left" valign="top">
 <p align="left"><font size="1" face="Arial" ><b>
 <bean:message key="corporate.Bookcs" />
 </b></font></td>
<td width="46%"  height="24" align="left" valign="top">
<html:text property="bc_start" readonly="true" /><input onclick="c2.popup('bc_start');" tabIndex="6" type="button" value="..."/>  	
</td>
 <td width="14%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="23%"  height="30">&nbsp;</td>
</tr>

<tr>
<td width="17%"  height="24" align="left" valign="top">
 <p align="left"><font size="1" face="Arial"><b>
  <bean:message key="corporate.Bookce" />
  </b></font></td>
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
 <bean:message key="corporate.Status" />
   </b></font></td>
<td width="14%"  height="24" align="left" valign="top">
<html:text property="status" value="n" disabled="true" />
</td>
 <td width="46%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="23%"  height="30">&nbsp;</td>
</tr>

<tr>
<td width="17%" height="27" align="center" />
<td width="14%" height="27" align="center" >
<html:submit value="Submit" onclick="return sub()" /></td>
 <td width="46%"  height="30">
 <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
  </b></font></td>
<td width="23%"  height="30">&nbsp;</td>
</tr>  
</table>

<logic:equal value="Update" scope="request" parameter="upflg">
	<script>
	document.forms[0].exc.disabled=true;
	document.forms[0].s_stock.disabled=true;
	</script>
</logic:equal>

 <html:hidden property="upflg" />
<html:hidden property="new_corp_but"/>
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
	 	    document.forms[0].s_stock.options[document.forms[0].obj_val.value].style.color="Red";
			</script>
		  <%}else{		  		  
			  for(int i=0;i<obj.length;i++){%>
		 	  <input type="hidden" name="obj_val"  value="<%=obj[i].toString() %>"  />
			  <%}%>
			  <script>
		 	  var len=document.forms[0].obj_val.length;
		 	  for(var i=0;i<len;i++)
			 	  document.forms[0].s_stock.options[document.forms[0].obj_val[i].value].style.color="Red";
	 	      </script> 	  	 
			<%}
		}%>



</html:form>
</td>
</tr>
</table>
<script language="javascript">
function test()
{
	document.forms[0].new_corp_but.value="Exc";
	document.forms[0].submit();
}
function test1()
{
	document.forms[0].new_corp_but.value="Action";
	document.forms[0].submit();
}
function sub()
{
	var val=document.forms[0].upflg.value;	
	if(val=="New")
		document.forms[0].new_corp_but.value="NewSubmit";
	if(val=="")
		document.forms[0].new_corp_but.value="NewSubmit";
	if(val=="Update")
		document.forms[0].new_corp_but.value="Submit";
	document.forms[0].submit();
}
function radsub()
{	
	document.forms[0].new_corp_but.value="Radio";
	document.forms[0].submit();
}
</script>
</html>		
		