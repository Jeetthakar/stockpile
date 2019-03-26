<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page import="app.*,java.util.*"%>


<%
		LogonForm form = null;
		if(request.isRequestedSessionIdValid())	{
						
			form = (LogonForm)session.getAttribute("user");
		}
		if(form==null ||(!request.isRequestedSessionIdValid())){%>
								
		<%
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

<BODY class="b2">





<jsp:useBean id="corporate" scope="session" class="app.Corporate"/>     

<!--  added by manoj/sushant -->
<%if(request.isRequestedSessionIdValid())	{%>

<jsp:setProperty name="corporate" property="userid1" value='<%=session.getAttribute("userid")%>'/>
<jsp:setProperty name="corporate" property="role_idc" value='<%= session.getAttribute("role_id").toString()%>'/>
<%}%>
<logic:equal value="1" scope="request" parameter="ref_flag">
<%corporate.selectdate(); %>
<jsp:setProperty name="corporate" property="exc_stk" value="a" />
<jsp:setProperty name="corporate" property="r_type" value="stock event" />
</logic:equal>

<logic:equal value="2" scope="request" parameter="ref_flag">
<%corporate.selectdate(); %>
<jsp:setProperty name="corporate" property="exc_stk" value="s" />
<jsp:setProperty name="corporate" property="r_type" value="stock event" />
<jsp:setProperty name="corporate" property="stkid" value='<%=request.getParameter("s_stock")%>' />
</logic:equal>

<table width="100%" height="100%" >

<tr>
<td align="left" valign="top" bgcolor="#CCddee">

<DIV id="leftCol" style="DISPLAY: none; width:160; height:100%;">
<%@ include file="tree3.jsp" %>
</div>
<IMG id="HideHandle" src="openImage.gif" style="CURSOR: hand; position:absolute;" onclick='hideLeftCol("leftCol");' src="fold.gif" width="10" height="25">
</td>
<td align="left" valign="top">


		<!--Forms[0] Starts Here-->			

<html:form action="/corpDairy_Action">
<html:errors/>


<logic:equal value="stock event" property="r_type" name="corporate">
<table border="1" width="100%" cellspacing="0" cellpadding="0">		
 <tr>
<td width="100%" align="left" class="td1" ><font size="2" face="Arial"><b>
<bean:message key="corporate.Diary" />
</b></font></td>
 </tr>
</logic:equal>

<logic:equal value="index event" property="r_type" name="corporate">
<table border="1" width="100%" cellspacing="0" cellpadding="0" >		
 <tr>
<td nowrap="true" align="left" class="td1" ><font size="2" face="Arial"><b>
<bean:message key="corporate.Diary" />
</b></font></td>
 </tr>
</logic:equal>

<logic:equal value="event" property="r_type" name="corporate">
<table border="1" width="100%" cellspacing="0" cellpadding="0">		
 <tr>
<td nowrap="true" align="left" class="td1"><font size="2" face="Arial"><b>
<bean:message key="corporate.Diary" />
</b></font></td>
</tr>	
</logic:equal>

 <tr>
 <td class="td1"  nowrap="true">
<font size="2" face="Arial"><b>
<bean:message key="corporate.Exsi" />
</b></font>	
<html:select property="exc_stk" onchange="return check()">
<html:option value="a" ><bean:message key="CorporateDiary.All" /></html:option>
<html:option value="e"><bean:message key="CorporateDiary.Exchange"/></html:option>
<html:option value="es"><bean:message key="CorporateDiary.ExchgNStock"/></html:option>
<html:option value="i"><bean:message key="corporate.Index"/></html:option>
<html:option value="is"><bean:message key="CorporateDiary.IndexNStock"/></html:option>
<html:option value="s"><bean:message key="corporate.stock"/></html:option>
</html:select>
</td>
 </tr> 
 
 <logic:equal value="e" property="exc_stk" name="corporate" >
  <tr>
 <td class="td1" nowrap="true">
 <font size="2" face="Arial"><b>
 <bean:message key="sysConfigForm.stockExId" />
 </b></font>	
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <html:select property="exch" onchange="return checktest()">
	<html:optionsCollection name="corporate" property="excCollection" />
 </html:select>
 </td>
 <tr> 	 
 </logic:equal>

<logic:equal value="es" property="exc_stk" name="corporate" >
  <tr>
 <td class="td1" nowrap="true">
 <font size="2" face="Arial"><b> 
 <bean:message key="sysConfigForm.stockExId" /></b></font>	
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
 <html:select property="exch" onchange="return checktest()">
<html:optionsCollection name="corporate" property="excCollection" />
 </html:select>
 </td>
 </tr> 
 
  <tr>
 <td class="td1" nowrap="true">
 <font size="2" face="Arial"><b>
 <bean:message key="corporate.stock" />
 </b></font>	
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
 <html:select property="stkid" onchange="return checktest()">
	<html:optionsCollection name="corporate" property="stkCollection"/>
 </html:select>
 </td>
 </tr> 	 	 
 </logic:equal>
 
 <logic:equal value="i" property="exc_stk" name="corporate">
  <tr>
 <td class="td1" nowrap="true">
 <font size="2" face="Arial"><b>
 <bean:message key="corporate.Index" />
 </b></font>	
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
 <html:select property="i_index" onchange="return checktest()">
	<html:optionsCollection name="corporate" property="indCollection"/>
 </html:select>
 </td>
 </tr> 	 	 
 </logic:equal>

<logic:equal value="is" property="exc_stk" name="corporate">
  <tr>
 <td class="td1" nowrap="true">
 <font size="2" face="Arial"><b>
  <bean:message key="corporate.Index" />
  </b></font>	
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
 <html:select property="i_index" onchange="return checktest()">
	<html:optionsCollection name="corporate" property="indCollection"/>
 </html:select>
 </td>
 </tr> 	 	 
 
 <tr>
 <td class="td1" nowrap="true">
 <font size="2" face="Arial"><b> 
 <bean:message key="corporate.stock" />
 </b></font>	
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <html:select property="stkid" onchange="return checktest()">
	<html:optionsCollection name="corporate" property="stkCollection"/>
 </html:select>
 </td>
 </tr> 	
  </logic:equal>
 
 <logic:equal value="s" property="exc_stk" name="corporate">
 <tr>
 <td class="td1" nowrap="true">
 <font size="2" face="Arial"><b>
  <bean:message key="corporate.stock" />
  </b></font>	
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <html:select property="stkid" onchange="return checktest()">
	<html:optionsCollection name="corporate" property="stkCollection"/>
 </html:select>
 </td>
 </tr> 	 
 </logic:equal>
</table> 

 

<logic:equal value="stock event" property="r_type" name="corporate">
<table border="0" width="100%" cellpadding="0" cellspacing="0">  
<tr>
<td nowrap="true"  class="td1"><font face="Arial" size="2">
<html:radio property="r_type" value="stock event" />
  <bean:message key="corporate.Stkevent" />
<html:radio property="r_type" value="index event" onclick="return test1()"/>
  <bean:message key="corporate.Indevent" />
<html:radio property="r_type" value="event" onclick="return test1()"/>
  <bean:message key="corporate.Event" />
</logic:equal>


<logic:equal value="index event" property="r_type" name="corporate">
<table border="0" width="100%" cellpadding="0" cellspacing="0"> 	  		
<tr>
<td nowrap="true" class="td1"><font face="Arial" size="2">	
<html:radio property="r_type" value="stock event" onclick="return test1()"/>
  <bean:message key="corporate.Stkevent" />	
<html:radio property="r_type" value="index event"/>
  <bean:message key="corporate.Indevent" />
<html:radio property="r_type" value="event" onclick="return test1()"/>
  <bean:message key="corporate.Event" />
</logic:equal>

<logic:equal value="event" property="r_type" name="corporate">
<table border="0" width="100%" cellpadding="0" cellspacing="0"> 	  		
<tr>
<td nowrap="true"  class="td1"><font face="Arial" size="2">	
<html:radio property="r_type" value="stock event" onclick="return test1()"/>
  <bean:message key="corporate.Stkevent" />
<html:radio property="r_type" value="index event" onclick="return test1()"/>
  <bean:message key="corporate.Indevent" />
<html:radio property="r_type" value="event"/>
  <bean:message key="corporate.Event" />
</logic:equal>
</font>
</td>

<td nowrap="true"  class="td1" align="left"><font face="Arial" size="2">
  <bean:message key="corporate.Fdate" />
<html:text property="fdate" disabled="true" size="10" /><input onclick="c2.popup('fdate');" tabIndex="6" type="button" value="..."/>  	
</font></td>
<td nowrap="true"  class="td1" align="left"><font face="Arial" size="2">
  <bean:message key="corporate.Tdate" />
<html:text property="tdate" disabled="true" size="10"/><input onclick="c2.popup('tdate');" tabIndex="7" type="button" value="..."/>  	
<html:submit  onclick="return test3()" ><bean:message key="Admin.Go" /></html:submit>
</font></td>
<td nowrap="true"  class="td1"> 
</td>	

<logic:notEqual value="1" name="corporate" property="role_idc">
		
		<logic:equal value="stock event" property="r_type" name="corporate">
			<td nowrap="true"  class="td1"/>			
			</table>
			<%@ include file="/pages/StockCorporateDiary.jsp"  %>
				<p align="left">
			<html:hidden property="r_type" value=""/>			
			<html:hidden property="type_value" value="1"/>
			<html:hidden property="ap_co_button1" value=""/>
			<html:submit  property="New" onclick="return test()" disabled="true"><bean:message key="stockmaster.new" /> </html:submit>
			<html:submit  onclick="return applyfun()" disabled="true"><bean:message key="CorporateDiary.Apply" /> </html:submit>
			<html:submit  property="Update" onclick="return updfun()" disabled="true"><bean:message key="classes.update" /> </html:submit>
			<html:submit property="Delete" onclick="return delfun()" disabled="true"><bean:message key="indCompliance.delete" /> </html:submit>
			<html:submit  property="Import" onclick="return import_test()" disabled="true"><bean:message key="Masters.ImportFile" /> </html:submit>
			<html:submit  onclick="return undotest()" disabled="true"><bean:message key="CorporateDiary.Undo" /> </html:submit>			
			<html:submit  onclick="return resettest()" disabled="true"><bean:message key="indexUpdate.reset" /> </html:submit>			
		</logic:equal>  
		
		<logic:equal value="index event" property="r_type" name="corporate">
			<!-- Change by Manoj Adekar Date : 17-07-2009 -->
			
			<logic:equal value="76" name="corporate" property="role_idc">
				</tr>
				</table>
				<table border="1" width="100%" cellpadding="0" cellspacing="0" >
				<%@ include file="/pages/IndexCorporateDiary.jsp"  %>
				<p align="center">			
				<html:hidden property="type_value" value="2"/>
				<html:hidden property="chk_delete" value=""/>
				<html:hidden property="ap_co_button2" value=""/>
				<html:hidden property="r_type" />			
				<html:submit  property="New" onclick="return test()"><bean:message key="stockmaster.new" /> </html:submit>
				<html:submit  onclick="return applyfun()"><bean:message key="CorporateDiary.Apply" /> </html:submit>
				<html:submit  property="Update" onclick="return updfun()"><bean:message key="classes.update" /> </html:submit>
				<html:submit  property="Delete" onclick="return delfun()"><bean:message key="indCompliance.delete" /> </html:submit>
				<html:submit    onclick="return undotest()"><bean:message key="CorporateDiary.Undo" /> </html:submit>				
				<html:submit   onclick="return resettest()"><bean:message key="indexUpdate.reset" /> </html:submit>		
				</p>
			</logic:equal>
			
			<logic:notEqual value="76" name="corporate" property="role_idc">
				</tr>
				</table>
				<table border="1" width="100%" cellpadding="0" cellspacing="0" >
				<%@ include file="/pages/IndexCorporateDiary.jsp"  %>
				<p align="center">			
				<html:hidden property="type_value" value="2"/>
				<html:hidden property="chk_delete" value=""/>
				<html:hidden property="ap_co_button2" value=""/>
				<html:hidden property="r_type" />			
				<html:submit  property="New" onclick="return test()" disabled="true"><bean:message key="stockmaster.new" /> </html:submit>
				<html:submit  onclick="return applyfun()" disabled="true"><bean:message key="CorporateDiary.Apply" /> </html:submit>
				<html:submit  property="Update" onclick="return updfun()" disabled="true"><bean:message key="classes.update" /> </html:submit>
				<html:submit  property="Delete" onclick="return delfun()" disabled="true"><bean:message key="indCompliance.delete" /> </html:submit>
				<html:submit    onclick="return undotest()" disabled="true"><bean:message key="CorporateDiary.Undo" /> </html:submit>				
				<html:submit   onclick="return resettest()" disabled="true"><bean:message key="indexUpdate.reset" /> </html:submit>		
				</p>
			</logic:notEqual>
			
			<!-- Up to here -->
	</logic:equal>

	<logic:equal value="event" property="r_type" name="corporate">
		<%@ include file="/pages/Events.jsp"  %>
		<p align="center">
		<html:hidden property="r_type" />			
		<html:hidden property="type_value" value="3"/>
		<html:hidden property="ap_co_button3" value=""/>
		<html:submit value="Update" onclick="return updfun()" disabled="true"><bean:message key="classes.update" /> </html:submit>
		<html:submit value="Delete" onclick="return delfun()" disabled="true"><bean:message key="indCompliance.delete" /> </html:submit>
		</p>					
	</logic:equal>
</logic:notEqual>


<logic:equal value="1" name="corporate" property="role_idc">

	<logic:equal value="stock event" property="r_type" name="corporate">
		<td nowrap="true"  class="td1"/>			
		</table>
		<%@ include file="/pages/StockCorporateDiary.jsp"  %>
			<p align="left">
		<html:hidden property="r_type" value=""/>			
		<html:hidden property="type_value" value="1"/>
		<html:hidden property="ap_co_button1" value=""/>
		<html:submit  property="New" onclick="return test()"><bean:message key="stockmaster.new" /> </html:submit>
		<html:submit  onclick="return applyfun()"><bean:message key="CorporateDiary.Apply" /> </html:submit>
		<html:submit  property="Update" onclick="return updfun()"><bean:message key="classes.update" /> </html:submit>
		<html:submit property="Delete" onclick="return delfun()"><bean:message key="indCompliance.delete" /> </html:submit>
		<html:submit  property="Import" onclick="return import_test()"><bean:message key="Masters.ImportFile" /> </html:submit>
		<html:submit  onclick="return undotest()"><bean:message key="CorporateDiary.Undo" /> </html:submit>			
		<html:submit  onclick="return resettest()"><bean:message key="indexUpdate.reset" /> </html:submit>			
	</logic:equal>  
	
	<logic:equal value="index event" property="r_type" name="corporate">
		</tr>
		</table>
		<table border="1" width="100%" cellpadding="0" cellspacing="0" >
		<%@ include file="/pages/IndexCorporateDiary.jsp"  %>
		<p align="center">			
		<html:hidden property="type_value" value="2"/>
		<html:hidden property="chk_delete" value=""/>
		<html:hidden property="ap_co_button2" value=""/>
		<html:hidden property="r_type" />			
		<html:submit  property="New" onclick="return test()"><bean:message key="stockmaster.new" /> </html:submit>
		<html:submit  onclick="return applyfun()"><bean:message key="CorporateDiary.Apply" /> </html:submit>
		<html:submit  property="Update" onclick="return updfun()"><bean:message key="classes.update" /> </html:submit>
		<html:submit  property="Delete" onclick="return delfun()"><bean:message key="indCompliance.delete" /> </html:submit>
		<html:submit    onclick="return undotest()"><bean:message key="CorporateDiary.Undo" /> </html:submit>				
		<html:submit   onclick="return resettest()"><bean:message key="indexUpdate.reset" /> </html:submit>		
		</p>
	</logic:equal>

	<logic:equal value="event" property="r_type" name="corporate">
		<%@ include file="/pages/Events.jsp"  %>
		<p align="center">
		<html:hidden property="r_type" />			
		<html:hidden property="type_value" value="3"/>
		<html:hidden property="ap_co_button3" value=""/>
		<html:submit value="Update" onclick="return updfun()"><bean:message key="classes.update" /> </html:submit>
		<html:submit value="Delete" onclick="return delfun()"><bean:message key="indCompliance.delete" /> </html:submit>
		</p>					
	</logic:equal>
</logic:equal>





<html:hidden property="ap_co_button" value=""/>
<html:hidden property="from_date" value="" />
<html:hidden property="to_date" value="" />
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
	 	    document.forms[0].stkid.options[document.forms[0].obj_val.value].style.color="Red";
			</script>
		  <%}else{		  
			  for(int i=0;i<obj.length;i++){%>
		 	  <input type="hidden" name="obj_val"  value="<%=obj[i].toString() %>"  />
			  <%}%>
			  <script>
		 	  var len=document.forms[0].obj_val.length;
		 	  for(var i=0;i<len;i++)
			 	  document.forms[0].stkid.options[document.forms[0].obj_val[i].value].style.color="Red";
	 	      </script> 	  	 
			<%}
		}%>
	
</html:form>		

</td>
</tr>
</table>

<logic:equal value="Undo" property="button" name="corporate">
<script>
document.forms[0].New.disabled=true;
document.forms[0].Update.disabled=true;
document.forms[0].Delete.disabled=true;
var val=document.forms[0].type_value.value;
if(val==1)
	document.forms[0].Import.disabled=true;		
</script>
</logic:equal>




</body>	
<Script language="javascript">
function test()
{
	var val=document.forms[0].type_value.value;
	if(val==2)
		document.forms[0].ap_co_button2.value="New";	
	if(val==1)
		document.forms[0].ap_co_button1.value="New";
	document.forms[0].submit();
}
function delfun()
{	
	var val=document.forms[0].type_value.value;
	var chk=confirm("Are You sure you want to Delete this records?");
	if(chk)
	{
		if(val==2)
			document.forms[0].ap_co_button2.value="YDelete";	
		if(val==1)
			document.forms[0].ap_co_button1.value="YDelete";	
		if(val==3)
			document.forms[0].ap_co_button3.value="YDelete";	
	}
	else
	{
		if(val==2)
			document.forms[0].ap_co_button2.value="NDelete";	
		if(val==1)
			document.forms[0].ap_co_button1.value="NDelete";	
		if(val==3)
			document.forms[0].ap_co_button3.value="NDelete";	
	}
	document.forms[0].submit();


}
function test1()
{
	document.forms[0].ap_co_button.value="Ctype";
	document.forms[0].submit();
}
function test3()
{
	var val=document.forms[0].fdate.value;
	document.forms[0].from_date.value=val;
	var val1=document.forms[0].tdate.value;
	document.forms[0].to_date.value=val1;
	document.forms[0].ap_co_button.value="Dairy_go";	
}
function updfun()
{
	var val=document.forms[0].type_value.value;
	if(val==2)
	{
		var chk=confirm("Are You sure you want to update this records?");
		if(chk)
			document.forms[0].ap_co_button2.value="Update";
		else
			document.forms[0].ap_co_button2.value="NUpdate";
	}
	if(val==1)
		document.forms[0].ap_co_button1.value="Update";
	if(val==3)
		document.forms[0].ap_co_button3.value="Update";
	document.forms[0].submit();
}
function applyfun()
{
	var val=document.forms[0].type_value.value;
	if(val==1)
		document.forms[0].ap_co_button1.value="Apply";
	if(val==2)
		document.forms[0].ap_co_button2.value="Apply";
	document.forms[0].submit();
}
function import_test()
{
	document.forms[0].ap_co_button1.value="Import";
	document.forms[0].submit();
}
function selectexs()
{
	document.forms[0].submit();
}
function check()
{
	document.forms[0].ap_co_button.value="Check";
	document.forms[0].submit();
}
function checktest()
{
	document.forms[0].submit();
}
function undotest()
{
	var val=document.forms[0].type_value.value;
	if(val==1)
		document.forms[0].ap_co_button1.value="Undo";
	if(val==2)	
		document.forms[0].ap_co_button2.value="Undo";
	document.forms[0].submit();
}
function resettest()
{
	var val=document.forms[0].type_value.value;
	if(val==1)
		document.forms[0].ap_co_button1.value="Reset";
	if(val==2)	
		document.forms[0].ap_co_button2.value="Reset";		
}
</script>
</html>			
			