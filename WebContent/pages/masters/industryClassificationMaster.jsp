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
			//AcessControl asc=new AcessControl();
			AcessControl asc=ConnectInit.getAcessControl();
			asc.setLocale(locale);
			}
			if(form==null ||(!request.isRequestedSessionIdValid()))
				response.sendRedirect("../userlogintemp.jsp");
			
			

%>

<jsp:useBean id="industryClassificationBean" scope="session" class="harrier.income.com.masters.industryClassificationForm"/> 
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

      		<bean:message key="industryClasificationMaster.title" />
    	</title>
	</head>
  
	<body class="b2">
  	 	<html:form action="/industryClassification-action" onsubmit="return validateIndustryClassificationBean(this);">
  	    <table width="98%" class="sample" >
        	<tr><td width="96%" nowrap="nowrap">
  	    <table border="0" width="800" cellspacing="10" >
           	
       		<td width="10" nowrap="nowrap"></td>
       		<td align="left" width="700" nowrap="nowrap" class="subHeader">
        		<b><span id="name"><bean:message key="industryClasificationMaster.addIC"/></span></b>
			</td>
       
				
        <tr>
          	<td width="10" nowrap="nowrap"></td>
          	<td width="700" align="left" nowrap="nowrap">
          	<table>
          	 	<td width="175" align="left" nowrap="nowrap">
        		<font face="Arial" size="2" >
        			
        				<bean:message key="industryClasificationMaster.selectIC" />
					
				</td>
				<td width="140" align="left" nowrap="nowrap">
				</font>
             
					<html:select property="selectICName" size="1"  onchange="changeFields()">
               		 	<html:optionsCollection name="industryClassificationBean" property="icCollection" /></font>
               		</html:select>
	            </td>
	       		</font>
	       	</table>
	         </td>    
	         
	    </tr>
       
       
        <tr>
          <td width="10" nowrap="nowrap"></td>
          	<td width="700" align="left" nowrap="nowrap">
            	<table>
          	 	<td width="175" align="left" nowrap="nowrap">
            	<font face="Arial" size="2" >
            		
            			<bean:message key="industryClasificationMaster.ICName" />
            		
            	</td>
            	
          	 	<td width="140" align="left" nowrap="nowrap">
            	</font>
          		     	<html:text property="icName" size="64"  />
          		 </td>
          		 </table>
          	</td>
       </tr>
             
        <tr>
          <td width="10" nowrap="nowrap"></td>
          	<td width="700" align="left" nowrap="nowrap">
            	<table>
          	 	<td width="175" align="left" nowrap="nowrap">
            	<font face="Arial" size="2" >
            		
            			<bean:message key="industryClasificationMaster.ShortName" />
            		
            	</td>
            	
          	 	<td width="140" align="left" nowrap="nowrap">
            	</font>
          		     	<html:text property="shortName" size="64"  />
          		 </td>
          		 </table>
          	</td>
       </tr>
        <tr>
          <td width="10" nowrap="nowrap"></td>
          	<td width="700" align="left" nowrap="nowrap">
            	<table>
          	 	<td width="175" align="left" nowrap="nowrap">
            	<font face="Arial" size="2" >
            	
            		<bean:message key="industryClasificationMaster.description"/>
            	
         	 </font>
         	   </td>
         	   	
          	 	<td width="140" align="left" nowrap="nowrap">
              		<html:text property="description" size="64"/>
              	</td>
              	</table>
           	</td>
       </tr>
       <tr>
	   	 	<td width="10" align="right" nowrap="nowrap"></td>
	   	 	<td width="700" align="left" nowrap="nowrap">
	   	 		
   				<table>
   				<td width="245" align="right" nowrap="nowrap">
   				<html:hidden property="operation"/>
		       	<html:submit property="saveButton"  onclick="saveFunc();"> 
		       			<bean:message key="indexUpdate.save"/>&nbsp;
                </html:submit>
		       	</td>
		       	<td width="400" align="left" nowrap="nowrap">
		       	<html:button property="resetButton"   onclick="resetFunc();"> 
		       	   		<bean:message key="indexUpdate.reset" />
		       	</html:button>
		       	<html:button onclick="history.go(-1)" property="">
         			<bean:message key="indexUpdate.cancel"/>
      			</html:button>   
      			
		       	</td>
		       </table>	    	
   				
	   	 	</td>
			

		</tr>		
		</td></tr></table> 
	 </html:form>
        
    <script language="JavaScript">
    
    function resetFunc(){
    	document.forms[0].icName.value="";
    	document.forms[0].shortName.value="";
		document.forms[0].description.value="";
		document.forms[0].selectICName.value="value0";
    }
    function changeFields(){
    	document.forms[0].operation.value		=	"changeFields";
	    	if(document.forms[0].selectICName.value=="value0"){
	    		var val=document.getElementById("name").innerHTML;
		    	document.forms[0].icName.value=val;
		    	document.forms[0].icName.select();
		    	document.forms[0].icName.focus();
		    	document.forms[0].shortName.value="";
		    	document.forms[0].description.value="";
		    	return false;
	   		}
	   		else{
		    	document.forms[0].submit();
		    }
    }
  
    function saveFunc(){
       	document.forms[0].operation.value		=	"Save";
        
      		
    }
     
    </script>
    <html:javascript formName="industryClassificationBean"/>
  </body>
</html:html>
