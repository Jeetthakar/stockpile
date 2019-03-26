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
<META Http-Equiv="Cache-Control" Content="no-cache">
<META Http-Equiv="Pragma"        Content="no-cache">
<META Http-Equiv="Expires"       Content="0">
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
			{
			 response.sendRedirect("../userlogintemp.jsp");
			}
			
			
%>
<jsp:useBean id="ActivityForm" scope="session" class="harrier.income.com.masters.ActivityForm" />
<html:html>
	<html:errors />   
	<head>
	<html:base/>
    	<title>
      		<bean:message key="activities.title" />
    	</title>
	</head>
  	<body  class="b2">
  	 	<html:form action="/Activity-action" onsubmit="return validateActivityBean(this);">
		  	 <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
			<meta name="GENERATOR" content="Microsoft FrontPage 6.0">
			<meta name="ProgId" content="FrontPage.Editor.Document">
			<link rel="stylesheet" type="text/css" href="./StyleSheet.css"  title="Default" />  
  	 <table width="120%" class="sample" >
        	<tr><td width="120%" nowrap="nowrap">
  	   	 <table border="0" width="78%" cellspacing="0" cellpadding="3" >
            <td width="10" nowrap="nowrap"></td>
       		<td align="left" width="546" class="subHeader" nowrap="nowrap">
        		<b>
        			<span id="name"><bean:message key="activity.AddActivity" /></span>
        		</b>
			</td>
        <tr>
        	<td width="10" nowrap="nowrap"></td>
          	<td width="546" align="left" nowrap="nowrap">
          		<table>
          		<td width="150" align="left" nowrap="nowrap">
        		<font face="Arial" size="2" >
        			<bean:message key="activityForm.selectActivityName" />&nbsp;&nbsp;&nbsp;
				</td>
				<td width="200" align="left" nowrap="nowrap">
				</font>
            		<html:select property="selectActivityName" size="1" onchange="changeFields();">
              			<html:optionsCollection name="ActivityForm" property="activityCollection" />
            		</html:select>	
				</td>
	          </table>
	       </td>    
	   </tr>
       <tr>
        	<td width="10" nowrap="nowrap"></td>
          	<td width="546" align="left" nowrap="nowrap">
            	<table>
          		<td width="150" align="left" nowrap="nowrap">
            	<font face="Arial" size="2" >
            		<bean:message key="activityForm.ActivityName"/></font>
            	</td>
            	</font>
            	<td width="150" align="left" nowrap="nowrap">
                        <html:text property="activityName" size="30" />		
                 </td>
                 </table>       
          	</td>
       </tr>
       <tr>
        	<td width="10" nowrap="nowrap"></td>
          	<td width="546" align="left" nowrap="nowrap">
            	<table>
          		<td width="150" align="left" nowrap="nowrap">
            	<font face="Arial" size="2" >
            		<bean:message key="activityForm.description" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;
            	</font> 
         	 	</td>
          		<td width="150" align="left" nowrap="nowrap">
               			<html:text property="activityDescription" size="30"  />
               	</td>
               	</table>
      		</td>
       </tr>
       <tr>
         	<td width="10" nowrap="nowrap"></td>
          	<td width="546" align="left" nowrap="nowrap">
            	<table>
          		<td width="150" align="left">
            	<font face="Arial" size="2" >
            		<bean:message key="activityForm.activityCode" />
            			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	</font>
         	 	</td>
          		<td width="150" align="left" nowrap="nowrap">
          	     	 		<%
								String saveB=request.getParameter("operation");
								String flag=request.getParameter("selectActivityName");
								
								if(flag==null || flag.equals("value0") || saveB.equals("Reset")){
                            %>
                            
                             <html:text property="activityCode" size="30" disabled="false" />
                             <%}
                             	else if(saveB.equals("Save")){
                             %>
                          	 <html:text property="activity_Code" size="30" disabled="true" />
                          	 <%}
                          	 	else{
                           	 %>
                          	  <html:text property="activityCode" size="30" disabled="true" />
                          	 <%}%>
          	    </td> 	 
          	    </table>
            </td>
        </tr>
       <tr>
	   	 	<td width="10" align="right" nowrap="nowrap"></td>
	   	 	<td width="546" align="left" nowrap="nowrap">
	   			<html:hidden property="operation" />
   				<html:hidden property="activity_Code"/>
   				<html:hidden property="activity_Code_flag"/>
   				<table>
   			<td align="left" height="69" class="tab" nowrap="nowrap">
  					&nbsp;<td align="right" colspan="5" width="350" height="69" class="tab" nowrap="nowrap">
  					<fieldset style="width: 202px; height: 52px; padding: 2">
					<legend><bean:message key="Masters.Action"/></legend>
					<input type="radio" value="N" name="new1" onclick="check1();" checked> 
					<bean:message key="stockmaster.new"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					<input type="radio" name="update" value="U" onclick="check2();"><bean:message key="classes.update"/></fieldset><tr>
   					<td align="right" nowrap="nowrap">
  					&nbsp;<td align="center" width="6%" nowrap="nowrap">
   				<td width="200" align="right" nowrap="nowrap">
		       	<html:submit property="saveButton" onclick=" return    saveFunc();"><bean:message key="defineIndex30"/></html:submit> 
		       	</td>
		       	<td width="50" align="left" nowrap="nowrap">
		       	<html:button property="resetButton" onclick="resetFunc();" ><bean:message key="indexUpdate.reset"/></html:button>
		       	</td>
		       	<td width="150" align="left" nowrap="nowrap">
		       		<html:button onclick="history.go(-1)" property="">
         			<bean:message key="indexUpdate.cancel"/>
      			</html:button> 
      			</td> 
		       </table>
	   	 	</td>
			<td width="56" align="left" nowrap="nowrap">
			<td width="220" align="right" nowrap="nowrap"></td>
		</tr>	
	<script language="javascript">
	    document.forms[0].new1.checked = true;
		document.forms[0].update.checked = false;
        var val=document.forms[0].selectActivityName.value;
		var operation=document.forms[0].operation.value;
		if(operation=="Save"){
		}
		else{	
		    if(val!=null && val=="value0" ){
				document.forms[0].activityCode.disabled=false;
			}
			else {
				document.forms[0].activityCode.disabled=true;
			}
		}
    </script>
</td></tr></table> 
</html:form>
<script language="JavaScript">
function changeFields(){
    document.forms[0].operation.value			=	"changeFields";
    document.forms[0].submit();
}
    
function resetFunc(){
    
    document.forms[0].selectActivityName.value="value0";
    document.forms[0].activityName.value= "";
    document.forms[0].activityDescription.value= "";
    document.forms[0].activityCode.disabled="false";
    document.forms[0].activityCode.value= "";
    document.forms[0].operation.value			=	"Reset";
    return true;
}

function saveFunc(){
   
    var msg='';
    
     if(document.forms[0].activityName.value == "")
       {
         msg=msg+'Please Enter Activity Name\n';
        }
      if(document.forms[0].activityDescription.value == "")
       {
          msg=msg+'Please Enter Activity Description\n';
       }
       if(document.forms[0].activityCode.value == "")
       {
          msg=msg+'Please Enter Activity Code\n';
       }
       
     if(msg=="") {
    document.forms[0].operation.value			=	"Save";
    document.forms[0].activity_Code.value 		=	document.forms[0].activityCode.value;
    return true;
     }
    else{
		alert(msg);
		return false;
		}
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

</body>
</html:html>
