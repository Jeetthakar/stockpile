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
		  	
		
		<html:form action="/roleActivities-action" onsubmit="return validateRoleActivitiesBean(this);">
  	     <table width="120%" class="sample">
        	<tr><td width="120%" nowrap="nowrap">  
       <table border="0" width="1085" cellspacing="" >
           	<td width="70">
       		</td>
       		<td align="left" width="680" class="subHeader">
        		<b><bean:message key="roleActivities.Role-Activities"/></b>
			</td>
       
				
        <tr>
          	<td width="70"></td>
          	<td width="680" align="left">
        		<font face="Arial" size="2" >
        			<b><span id="name"><bean:message key="rolesForm.selectRoleName" /></span>&nbsp;&nbsp;&nbsp;&nbsp;</b>
				</font>
			       	<html:select  property="selectRName" size="1"  onchange="changeFields();">
              			<html:optionsCollection name="roleActivitiesBean" property="roleCollection"  />
            		</html:select>
            		<a href="./rolesView.jsp"><font face="Arial" size="2"><bean:message key="roleActivities.newrole"/></font></a href>
	        </td>    
	    </tr>
       
       
        <tr>
          <td width="70" ></td>
          	<td width="680"  align="left">
            	<br/>
            	<font face="Arial" size="2" >
            		<b><bean:message key="roleActivities.selectActivitiesName" /></b>
            	</font>
            <td>
        <tr>
        	 <td width="70" ></td>
            <td>  	
             	<html:select  property="ac_selections" size="10"  styleId="ac_selections" multiple="true" style="width:400px;">
              			<html:optionsCollection name="roleActivitiesBean" property="activityCollection"  />
            	</html:select>
             
            
              <a href="./activitiesView.jsp"><font face="Arial" size="2"><bean:message key="roleActivities.newactivity"/></font></a href>
              
            </td>
          </tr>
          <tr>
          	 <td width="70" ></td>
          	<td>
          		<br/>
          		<table width="406" >
          			<td width="195" align="left">
		          		<html:button property="saveButton"  onclick="AddToRHS();return false;">
		          			&nbsp;<bean:message key="roleActivities.Add"/>&nbsp;
		          		</html:button>
		          		<html:img page="/pages/images/buttons/bs_dodown.gif" width="16" height="16"/>
	          		</td>
	          		<td width="195" align="right">
		          		<html:img page="/pages/images/buttons/bs_upup.gif" width="16" height="16"/>
		            	<html:button property="saveButton" onclick="RemoveFromRHS();return false;">
		            		<bean:message key="roleActivities.Remove"/>
		          		</html:button>
	          		</td>
            	</table>
           	</td>	
          </tr>
         
          <tr>
          	<td></td>
          	<td>
          		 <br/>
          		 <font face="Arial" size="2" >
          		 <b><bean:message key="roleActivities.addActivities" /></b>
          	</td>
          </tr>
          <tr>
              <td width="70" ></td>
              	<td>	     	          		            	
            	<html:select property="rhsSelection" size="10"  styleId="Rem" multiple="true" style="width:400px;">
              			<html:optionsCollection name="roleActivitiesBean" property="assignActivitiesCollection"  />
            	</html:select>
            		
            		
            	
         	</td>
       </tr>
    	
       <tr></tr>
       <tr></tr>
       <tr>
	   	 	<td width="70" align="left" ></td>
	   	 	<td width="480" align="left">
	   	 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   	 		&nbsp;&nbsp;&nbsp;
   				
		    	<html:hidden property="rem"/>
		    	<html:hidden property="operation"/>
		    	<html:hidden property="select_rname"/>
		    		
		    	<html:submit property="saveButton"  onclick="return saveFunc()"> 
		    		<bean:message key="indexUpdate.save"/>&nbsp;
          		</html:submit>
		       	<html:button property="resetButton"  onclick="return resetFunc()"> 
		       		<bean:message key="indexUpdate.reset"/>
          		</html:button>
          		<html:button onclick="history.go(-1)" property="">
         			<bean:message key="indexUpdate.cancel"/>
      			</html:button> 
	   	 	</td>
			<td width="56" align="left">
			<td width="220" align="right" ></td>

		</tr>	
			</td></tr></table> 	
	 </html:form>
	
	
 
<script language="javascript">

function resetFunc(){
		document.forms[0].operation.value="Reset";
		var rhsSelect = document.getElementById("Rem");
		rhsSelect.options.length=0;
		document.forms[0].selectRName.value="value0";
}
function saveFunc(){
		var nodesToSend=new Array();
		var rhsSelect1 = document.getElementById("Rem");
		var numToRemove1=0;
		for(var index2 = 0; index2 < rhsSelect1.options.length; index2++)
		{
			nodesToSend[ numToRemove1++ ] = rhsSelect1.options[ index2 ].value;
			
		}
		
      	document.forms[0].rem.value=nodesToSend;
      	document.forms[0].operation.value="Save";
 		return true;
}
function changeFields(){
		document.forms[0].operation.value="Reset";
		document.forms[0].select_rname.value=document.forms[0].selectRName.value
		var val1=document.forms[0].selectRName.value;
		
  	 	if(val1=="value0"){
  	 		var rhsSelect = document.getElementById("Rem");
			var nodesToRemove = new Array();
			var numToRemove = 0;
			
			for(var index = 0; index < rhsSelect.options.length; index++)
			{
				
				nodesToRemove[ numToRemove++ ] = rhsSelect.options[ index ];
				var elementValue = rhsSelect.options[index].value;
				
					
			}
			for ( var index = 0; index < nodesToRemove.length; index++ )
			{
				rhsSelect.removeChild(nodesToRemove[index]);
			
			}

  	 		return false;
  	 	}
    	document.forms[0].submit();
}
function AddToRHS()
{
	if(document.forms[0].selectRName.value=="value0"){
		var val=document.getElementById("name").innerHTML;
		alert(val);
		return false;
	}
	var value=document.forms[0].selectRName.value;
	var rhsSelect = document.getElementById("Rem");
	var lhsSelect = document.getElementById("ac_selections");
	var nodesToAdd = new Array();
	var nodesToRemove = new Array();
	var numToAdd = 0;
	var numToRemove = 0;
	
	for(var index = 0; index < lhsSelect.options.length; index++)
	{
		if ( lhsSelect.options[index].selected == false )
		{
			continue;
		}
		
		// Save the node for removal from the right-hand side select, but outside this loop.
		nodesToAdd[ numToAdd++ ] = lhsSelect.options[ index ];
		
	}
		
	for ( var index = 0; index< nodesToAdd.length; index++ )
	{
		var nToAdd;
		var flag			=	 0;
		var fields 			=	 new Array();
		var i				=	0;
		if(nodesToAdd!=null)
			nToAdd			=	nodesToAdd[index].value; //51
			
		else
			break;
				
		for(var index1=0; index1< rhsSelect.options.length; index1++ ) 
		{
			var nToRem	=	rhsSelect.options[index1].value;
			if(nToAdd==nToRem)
			{
				
				alert(rhsSelect.options[index1].innerHTML+" is already Added");
				flag=1;
				index1=rhsSelect.options.length;
				break;
			}
		}
		
		if(flag==0){	
				
				var optionElement = document.createElement("option");
				optionElement.value=nodesToAdd[index].value;
				optionElement.innerHTML = nodesToAdd[index].innerHTML;
				rhsSelect.appendChild(optionElement);
		}
	}
	
}
function RemoveFromRHS()
{	
	var StoreSelections_Hash = new Array();
	var StoreSelections_Array = new Array();
	var rhsSelect = document.getElementById("Rem");
	var lhsSelect = document.getElementById("ac_selections");
	var nodesToRemove = new Array();
	var numToRemove = 0;
	for(var index = 0; index < rhsSelect.options.length; index++)
	{
		// For each selected element.
		//
		if ( rhsSelect.options[index].selected == false )
		{
			continue;
		}

		// Save the node for removal from the right-hand side select, but outside this loop.
		
		nodesToRemove[ numToRemove++ ] = rhsSelect.options[ index ];

		// Remove the value in the hash.
		var elementValue = rhsSelect.options[index].value;
			
	}

	// Remove the options from the right-hand side select.
	for ( var index = 0; index < nodesToRemove.length; index++ )
	{
		rhsSelect.removeChild(nodesToRemove[index]);
	
	}

}
  
    </script>
	<html:javascript formName="roleActivitiesBean" />
	
  </body>
</html:html>
