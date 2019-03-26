<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.harrier.initializeation.ConnectInit"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page import="sysconfig.model.*,java.util.*,harrier.income.com.masters.*, org.apache.struts.util.*" %>
<%@ page language="java" import="app.*"%><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
			LogonForm form = null;
			if(request.isRequestedSessionIdValid())	{	
				form = (LogonForm)session.getAttribute("user");
				String locale=session.getAttribute("locale").toString();
			//	AcessControl asc=new AcessControl();
				AcessControl asc=ConnectInit.getAcessControl();
				asc.setLocale(locale);
			}
			if(form==null ||(!request.isRequestedSessionIdValid()))
				response.sendRedirect("../userlogintemp.jsp");
			
			

%>
<jsp:useBean id="rolesBean" scope="session" class="harrier.income.com.masters.RolesForm"/> 
<html:html>
<html:errors /> 
 	<head>
 	<html:base/>
 	<META Http-Equiv="Cache-Control" Content="no-cache">
	<META Http-Equiv="Pragma"        Content="no-cache">
	<META Http-Equiv="Expires"       Content="0">
	<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
	<meta name="GENERATOR" content="Microsoft FrontPage 6.0">
	<meta name="ProgId" content="FrontPage.Editor.Document">
	<link rel="stylesheet" type="text/css" href="./StyleSheet.css"  title="Default" />  
 		<title>
      		<bean:message key="roles.title" />
    	</title>
	</head>
  	<body class="b2">
  	 	<html:form action="/Roles-action" onsubmit="return validateRolesBean(this);">
  	 	<table width="120%" class="sample">
        	<tr><td width="120%" nowrap="nowrap">
  	 	<table border="0" width="700" cellspacing="10" >
           	<td width="10"></td>
       		<td align="left" width="546" class="subHeader">
        		<b>
        			<span id="name"><bean:message key="roles.addRole"/></span></b>
			</td>
       <tr>
          	<td width="10"></td>
          	<td width="546" align="left">
          	<table>
          	 	<td width="130" align="left">
        		<font face="Arial" size="2" >
        			<bean:message key="rolesForm.selectRoleName" />
				</td>
				<td width="140" align="left">
				</font>
             		<html:select property="selectRoleName" size="1"  onchange="changeFields()">
               		 	<html:optionsCollection name="rolesBean" property="roleCollection" /></font>
               		</html:select>
	            </td>
	       		</font>
	       	</table>
	         </td>    
	    </tr>
        <tr>
          <td width="10"></td>
          	<td width="546" align="left">
            	<table>
          	 	<td width="130" align="left">
            	<font face="Arial" size="2" >
            		<bean:message key="rolesForm.RoleName" />
            	</td>
            	<td width="140" align="left">
            	</font>
          		     	<html:text property="roleName" size="30"  />
          		 </td>
          		 </table>
          	</td>
       </tr>
       <tr>
          <td width="10"></td>
          	<td width="546" align="left">
            	<table>
          	 	<td width="130" align="left">
            	<font face="Arial" size="2" >
            		<bean:message key="rolesForm.description"/>
            	</font>
         	   </td>
         	   	<td width="140" align="left">
              		<html:text property="roleDescription" size="30"/>
              	</td>
              	</table>
           	</td>
       </tr>
       <tr>
	   	 	<td width="10" align="right" ></td>
	   	 	<td width="546" align="left">
	   	 		<table>
	   	 		<td align="left" height="69" class="tab" nowrap="nowrap">
  					&nbsp;<td align="right" colspan="5" width="330" height="69" class="tab" nowrap="nowrap">
  					<fieldset style="width: 202px; height: 52px; padding: 2">
					<legend><bean:message key="Masters.Action"/></legend>
					<input type="radio" value="N" name="new1" onclick="check1();" checked> 
					<bean:message key="stockmaster.new"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					<input type="radio" name="update" value="U" onclick="check2();"><bean:message key="classes.update"/></fieldset><tr>
   					<td align="right" nowrap="nowrap">
  					&nbsp;<td align="center" width="6%" nowrap="nowrap">
   				<td width="175" align="right">
   				<html:hidden property="operation"/>
		       	<html:submit property="saveButton" onclick="saveFunc();"> 
		       			<bean:message key="indexUpdate.save"/>
                </html:submit>
		       	</td>
		       	<td width="50" align="left">
		       	<html:button property="resetButton" onclick="resetFunc();"> 
		       		<bean:message key="indexUpdate.reset"/>
             	</html:button>
		       	</td>
		       	<td width="150" align="left">
		       	<html:button onclick="history.go(-1)" property="">
         			<bean:message key="indexUpdate.cancel"/>
      			</html:button> 
      			</td>
		       </table>	    	
   			</td>
			</td></tr></table> 
			<script language="javascript">
	    document.forms[0].new1.checked = true;
		document.forms[0].update.checked = false;
		</script>
	 </html:form>
       <script language="JavaScript">
     
function changeFields(){
   	document.forms[0].operation.value		=	"changeFields";
   	var val1=document.forms[0].selectRoleName.value;
 	if(val1=="value0"){
  	 	var val=document.getElementById("name").innerHTML;
	    document.forms[0].roleName.value=val;
	    document.forms[0].roleName.select();
	    document.forms[0].roleName.focus();
  	 	document.forms[0].roleDescription.value="";
  	 	return false;
  	 }
  	 document.forms[0].submit();
}
  
function saveFunc(){
  	document.forms[0].operation.value		=	"Save";
    var roleName							=	document.forms[0].roleName.value;
}
    
function resetFunc(){
    document.forms[0].roleName.value="";
    document.forms[0].roleDescription.value="";
    document.forms[0].selectRoleName.value="value0";
    return true;
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
    </script>
    <html:javascript formName="RolesBean"/>
  </body>
</html:html>
