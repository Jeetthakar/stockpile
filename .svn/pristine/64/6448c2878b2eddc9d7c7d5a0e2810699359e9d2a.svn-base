<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page language="java" import="harrier.income.com.masters.*,app.*,java.sql.*,java.util.*,java.text.*" %>
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

<SCRIPT language=javascript>
function hideLeftCol(id)
{
if(this.document.getElementById(id).style.display=='none')
{
this.document.getElementById(id).style.display='inline';
document.getElementById("HideHandle").src = '../closeImage.gif';
// this.document.getElementById(id).style.width='10px';
Set_Cookie('showLeftCol','true',30,'/','','');
var show = Get_Cookie('showLeftCol');
//document['HideHandle'].src = 'images/hide.gif';
document.getElementById("HideHandle").src = '../open.gif';
}
else{
// this.document.getElementById(id).style.display='none';
this.document.getElementById(id).style.display='none';
document.getElementById("HideHandle").src = '../openImage.gif';
Set_Cookie('showLeftCol','false',30,'/','','');
var show = Get_Cookie('showLeftCol');
//document['HideHandle'].src = 'images/show.gif';
}
}
</script>

<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 5.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<html:base/>
<script language="javascript" src="./codethatcalendarstd.js"></script>
	<script language="javascript" src="box_ex.js"></script>
	<script language="javascript">
		var c2 = new CodeThatCalendar(caldef2);
	</script>
	
</head>

<link href="./StyleSheet.css" rel="stylesheet" type="text/css">

<% 
	log.info("Start1");
	response.setHeader("Pragma","no-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires",-1);    
		log.info("Start2");	
		String id[] = request.getParameterValues("indexID");
		String option=request.getParameter("opt");
		log.info(option);	 
		
%>

<jsp:useBean id="capturedIndexForm" scope="session" class="harrier.income.com.masters.CapturedIndexForm"/> 
<jsp:useBean id="indexCollection" scope="session" class="app.CapturedIndexCollection"/> 


<table width="100%" height="100%" >

<tr>
<td align="left" valign="top" bgcolor="#CCddee">

<DIV id="leftCol" style="DISPLAY: none; width:160; height:100%;">
<%@ include file="../tree3.jsp" %>
</div>
<IMG id="HideHandle" src="../openImage.gif" style="CURSOR: hand; position:absolute;" onclick='hideLeftCol("leftCol");' src="fold.gif" width="10" height="25">
</td>
<td align="left" valign="top">



<p align="center" class="subHeader">
         <b> <bean:message key="Masters.AddCapturedInd"/></b></p> <br>
         
<html:form  action="/capturedIndexAction">
   
<html:errors/>

<%	
		indexCollection.addStocksInSourceTable(option,id,request);
				SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date dt = new java.util.Date();
				String l_date = fr.format(dt).toString();
				log.info(l_date);
				String date=request.getParameter("fdate");
				String index_name=request.getParameter("index_name");
			 		 		     		 
 %> 			
 <logic:equal value="m" property="messagess" name="capturedIndexForm">
	<%=capturedIndexForm.messages()%>
</logic:equal>
      			<p align="center"> 
      			&nbsp;&nbsp;&nbsp;  <bean:message key="corporate.Date"/>&nbsp;:&nbsp;&nbsp;&nbsp; 
						<% if(date==null){ %> <input readOnly name="fdate" value="<%=l_date%>" size="10"> <% }else{ %> <input readOnly name="from" value="<%=date%>" size="10">  <% } %>
                                <input onclick="c2.popup('fdate');" type="button" value="...">
      				</p>
 <table border="0" width="100%" class="gridStyle" bordercolorlight="#000000" cellspacing="0" cellpadding="0" bordercolordark="#000000">
    <tr>
       <td width="100%">
      <table border="1" class="gridStyle" width="100%" cellspacing="0" cellpadding="0">
      	
         <tr>
         <td width="4%" align="center" class="gridStyle-header" height="1"><input type="checkbox" name="indexID1" onclick="CheckAll(this)"/></td>
          <td width="22%" align="center" class="gridStyle-header" height="1"><bean:message key="defineIndex7"/></td>
 <!--           <td width="8%" align="center" class="gridStyle-header" height="1"><bean:message key="Masters.LatestVal"/></td> -->
           <td width="8%" align="center" class="gridStyle-header" height="1"><bean:message key="Masters.openVal"/></td>
           <td width="8%" align="center" class="gridStyle-header" height="1"><bean:message key="Masters.HighVal"/></td>
           <td width="8%" align="center" class="gridStyle-header" height="1"><bean:message key="Masters.LowVal"/></td>
           <td width="8%" align="center" class="gridStyle-header" height="1"><bean:message key="Masters.CloseVal"/>
            </td>
<!--            <td width="4%" align="center" class="gridStyle-header" height="1">% <bean:message key="Masters.Change"/></td> -->
             
             <td width="12%" align="center" class="gridStyle-header" height="1"><bean:message key="port.mcvValue"/></td>
            <!-- <td width="12%" align="center" class="gridStyle-header" height="1"><bean:message key="indcurrwise.divisor"/></td> -->
             <td width="3%" align="center" class="gridStyle-header" height="1"><bean:message key="stockmaster.currency"/></td>
             <td width="8%" align="center" class="gridStyle-header" height="1"><bean:message key="Masters.DtTime"/></td>
           </tr>  
           <%     
           	StringBuffer str=null;
           if(option!=null && option.equals("Reset")){      		
           		 str = ListTypeClass.addRowsIncIndexTable(indexCollection.table,request,option);     
           	}else{
           		 str = ListTypeClass.addRowsIncIndexTable(indexCollection.table,request);         		       
           	}    		       
			if(str!=null){%>
		<%=str.toString()%>
         <% } %>                 
           </table>
          </td>
          </tr>         
         <tr>       
         
           <td width="44%" align="center"  height="1">
            <br>
            <html:submit property="b1" ><bean:message key="defineIndex30"/></html:submit>
                                  
            <html:submit property="b1"  ><bean:message key="indexUpdate.reset"/></html:submit>
         &nbsp;&nbsp; &nbsp;<a href="../ImportNewStock.jsp"><bean:message key="stockmaster.importFromFile"/></a>
            <br></br>
            </td>
         </tr>
         
  </table> 
   
 </html:form>
 
 </td>
</tr>
</table>
 
 <script language="javascript">
 function CheckAll(chk)
{
  for (var i=0;i < document.forms[0].elements.length;i++)
	{
		var e = document.forms[0].elements[i];
		if (e.type == "checkbox" && e.name=="indexID")
		{
			e.checked = chk.checked;
		}
	}
}
function checkidlength()
{
	//alert("In checkidlength");
	//alert("document.frm.checkvalue.value");
	if(document.frm.checkvalue.value==0 || document.frm.checkvalue.value==null)
	{
		top.frmMain.location.reload();   		
	}
}
function test()
{
  document.frm.check_but.value="Add";
  return true;
}
</script>
 
 </html:html>         