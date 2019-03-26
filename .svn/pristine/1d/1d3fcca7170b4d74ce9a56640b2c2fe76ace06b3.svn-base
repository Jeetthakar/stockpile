<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page language="java" import="app.*,java.util.*,java.text.*"%>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setHeader("Expires","0");

LogonForm form = null;
			if(request.isRequestedSessionIdValid())	{	
				form = (LogonForm)session.getAttribute("user");
			}
			if(form==null ||(!request.isRequestedSessionIdValid())){
				response.sendRedirect("../userlogintemp.jsp");
			}
		%>
<html:html>
<html:base/>
<head>
<link href="../StyleSheetM.css" rel="stylesheet" type="text/css">

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

</head>

<jsp:useBean id="indexCalculatorCollection1" scope="session" class="harrier.income.com.report.IndexCalculatorCollection"/> 

<table width="100%" height="100%" >

<tr>
<td align="left" valign="top" bgcolor="#CCddee">

<DIV id="leftCol" style="DISPLAY: none; width:160; height:100%;">
<%@ include file="../tree3.jsp" %>
</div>
<IMG id="HideHandle" src="../openImage.gif" style="CURSOR: hand; position:absolute;" onclick='hideLeftCol("leftCol");' src="fold.gif" width="10" height="25">
</td>
<td align="left" valign="top">

<html:form action="/IndexCurrencyWise">
<jsp:setProperty name="IndexCurrencyWiseBean" property="userid1" value='<%=session.getAttribute("userid")%>'/>	
		<table width="1000" cellspacing="0" cellpadding="0" >
        		<tr>
		          	<td width="335"  nowrap="nowrap">
		          		&nbsp;
		          	</td>
		          	<td   width="665" align="left" colspan="2" nowrap="nowrap">
		          		<font size="3" face="Verdana">
		          			<b><bean:message key="IndexCurrencyWise.title" /> </b>
		          		</font>
		         	</td> 
	          	</tr>
		</table> 
		
		<table border="0" width="100%" class="gridStyle" cellspacing="0" cellpadding="0">
   			<tr> 
   			<%      String var=request.getParameter("index_id"); 
           		if(var==null){
   			var="3252";
   		}  %>   	
   				<td align="left" nowrap="nowrap" ><bean:message key="port.selectIndex" />
   					&nbsp;:&nbsp;&nbsp;
	        		<html:select property="index_id" size="1" onchange="document.forms[0].submit();return true">
              			<html:optionsCollection name="IndexCurrencyWiseBean" property="indexListCollection" />
            		</html:select>	
        		</td>
                       <%  /*                     
                        if(var!=null)
                        {
                        	log.info("var is "+var);
                        	indexCalculatorCollection1.addStocksInIndexCalculatorTablePrice(var,request);
                        }*/
                         %>  
                                         
   </tr>  
                       <%                                              
                        if(var!=null)
                        {
                        	indexCalculatorCollection1.addInIndexCurrencyWise(var,request);
                        }else{ var="1";  }
                         %>  
                         <br></br>                 
  
        	<tr><td><br></br></td></tr>
       	</table>
      	
      	
		
    	<table border="1" width="100%"  cellspacing="0" cellpadding="0">
 			<tr>
            	<td width="44%" align="center" height="1">
            		<br>        
            		<html:submit property="b1"  ><bean:message key="Admin.save"/></html:submit>
                    <html:submit property="b1"  ><bean:message key="indexUpdate.reset"/></html:submit>           
         			<br></br>
            	</td>
         	</tr>
   
    		<tr>
       		  <td width="100%">
      			<table border="1" width="100%" class="gridStyle" cellspacing="0" cellpadding="0">
      	
         			<tr>   
         				<td width="5%" align="center" class="griStyle-header"><input type="checkbox" name="currid" onclick="CheckAll(this)"/></td>     
          				<td width="30%" align="center" class="gridStyle-header"><bean:message key="indcurrwise.currencyName"/></td>
           				<td width="23%" align="center" class="gridStyle-header"><bean:message key="indcurrwise.tmcv" /></td>
           				<td width="22%" align="center" class="gridStyle-header"><bean:message key="indcurrwise.divisor" /></td>           
           				<td width="20%" align="center" class="gridStyle-header"><bean:message key="indcurrwise.indValue" /></td>    
           			</tr> 
           			<%     
           	StringBuffer str=null;
           		 str = indexCalculatorCollection1.addRowsInIndexCurrencyWise(indexCalculatorCollection1.table,request);     
           	if(str!=null){%>
		<%=str.toString()%>
         <% } %> 
           		</table>
           	  </td>
           	</tr>
       </table>

<html:hidden property="compute" value="no"></html:hidden>
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
		if (e.type == "checkbox" && e.name=="currencyid")
		{
			e.checked = chk.checked;
		}
	}
}
</script>      
</html:html>		