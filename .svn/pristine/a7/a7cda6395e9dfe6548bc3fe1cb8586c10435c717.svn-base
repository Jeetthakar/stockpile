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
<jsp:useBean id="classificationLevelBean" scope="session" class="harrier.income.com.masters.classificationLevelForm"/> 
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
			<bean:message key="classificationLevel.title" />
    	</title>
	</head>
  	<body class="b2" onload="colorRow();">
  	 	<html:form action="classificationLevel-action"  onsubmit="return validateClassificationLevelBean(this);" >
  	 	 <table width="98%" class="sample" >
        	<tr><td width="96%" nowrap="nowrap">
  	    <table border="0" width="600" cellspacing="10" >
          
       		<td width="10" nowrap="nowrap"></td>
       		<td align="left" width="900" class="subHeader" nowrap="nowrap">
        		<b>
        			<span id="CLName"><bean:message key="classificationLevel.title"/></span></b>
			</td>
        <tr>
          	<td width="10" nowrap="nowrap"></td>
          	<td width="900" align="left" nowrap="nowrap">
          	<table>
          	 	<td width="190" align="left">
        		<font face="Arial" size="2" >
        			<bean:message key="industryClasificationMaster.selectIC" />
				</td>
				<td width="700" align="left" nowrap="nowrap">
				</font>
             		<html:select property="selectICName" size="1"  onchange="changeFields()">
               		 	<html:optionsCollection name="classificationLevelBean" property="icCollection" /></font>
               		</html:select>
               		<font face="Arial" size="1">
               			<a href="../masters/industryClassificationMaster.jsp"><bean:message key="classes.newInd"/></a href>
               		</font>
	            </td>
	       		</font>
	       	</table>
	         </td>    
	    </tr>
	    <tr>
          	<td width="10" nowrap="nowrap"></td>
          	<td width="900" align="left" nowrap="nowrap">
          	<table>
          	 	<td width="190" align="left" nowrap="nowrap">
        		<font face="Arial" size="2" >
        			<bean:message key="classificationLevel.selectClassificationLevel" />
				</td>
				<td width="700" align="left" nowrap="nowrap">
				</font>
             		<html:select property="selectClassLevel" size="1"  onchange="changeFieldClassLevel()" styleId="clLevel">
               		 	<html:optionsCollection name="classificationLevelBean" property="levelCollection" /></font>
               		</html:select>
               	</td>
	       		</font>
	       	</table>
	         </td>    
	    </tr>
	    <tr>
          <td width="10" nowrap="nowrap"></td>
          	<td width="900" align="left" nowrap="nowrap">
            	<table>
          	 	<td width="190" align="left" nowrap="nowrap">
            	<font face="Arial" size="2" >
            		   <bean:message key="classificationLevel.insert"/>	
            	</font>	
            	<logic:notEmpty name="classificationLevelBean" property="selectClassLevel">
            			<bean:define id="sel" name="classificationLevelBean" property="selectClassLevel" type="java.lang.String"/>
            	</logic:notEmpty>
            	<logic:empty name="classificationLevelBean" property="selectClassLevel">
            			<bean:define id="sel" value="value1" type="java.lang.String"/>
            	</logic:empty>
            	
            	</td>
            	 <td width="100" align="left" nowrap="nowrap">
            	     	<logic:equal value="value0" name="sel" >
            	     		<html:radio property="radioButton" value="Top" />
            	     	</logic:equal>
            	     	<logic:notEqual value="value0" name="sel">
            	     		<html:radio property="radioButton" value="Top" disabled="true"/>
            	     	</logic:notEqual>
            	     	<font face="Arial" size="2" ><bean:message key="classificationLevel.atTop"/>	</font>	
          		 </td>
          	  </table>
          	</td>
       </tr>
         <tr>
          	<td width="10" nowrap="nowrap"></td>
          	<td width="900" align="left" nowrap="nowrap">
          	<table>
          	 	<td width="190" align="left" nowrap="nowrap">
        		<font face="Arial" size="2" >
        		</td>
				<td width="140" align="left" nowrap="nowrap">
						<logic:equal value="value0" name="sel">
            	     		<html:radio property="radioButton" value="Bottom"/>
            	     	</logic:equal>
            	     	<logic:notEqual value="value0"  name="sel">
            	     		<html:radio property="radioButton" value="Bottom" disabled="true"/>
            	     	</logic:notEqual>
            	      	<font face="Arial" size="2" ><bean:message key="classificationLevel.atBottom"/>	</font>
	            </td>
	       		</font>
	       	</table>
	         </td>    
	     </tr>
	     <tr>
          	<td width="10" nowrap="nowrap"></td>
          	<td width="900" align="left" nowrap="nowrap">
          	<table>
          	 	<td width="190" align="left" nowrap="nowrap">
        		<font face="Arial" size="2" >
        		</td>
				<td width="70" align="left" nowrap="nowrap">
				</font>
						<logic:equal value="value0" name="sel">
            	     		<html:radio property="radioButton" value="After"/>
            	     	</logic:equal>
            	     	<logic:notEqual value="value0"  name="sel">
            	     		<html:radio property="radioButton" value="After" disabled="true"/>
            	     	</logic:notEqual>
            	      	<font face="Arial" size="2" ><bean:message key="classificationLevel.after"/>	</font>
            	</td>
	            <td width="140" align="left" nowrap="nowrap">
	            		<logic:equal value="value0" name="sel">
            	     		<html:select property="afterLevel" size="1"  styleId="aLevel">
               		 			<html:optionsCollection name="classificationLevelBean" property="afterCollection" /></font>
               				</html:select>
            	     	</logic:equal>
            	     	<logic:notEqual value="value0"  name="sel">
            	     		<html:select property="afterLevel" size="1" disabled="true" styleId="aLevel">
               		 			<html:optionsCollection name="classificationLevelBean" property="afterCollection" /></font>
               				</html:select>
            	     	</logic:notEqual>
	            	</td>
	       		</font>
	       	</table>
	         </td>    
	    </tr>
	    <tr>
          <td width="10" nowrap="nowrap"></td>
          	<td width="900" align="left" nowrap="nowrap">
            	<table>
          	 	<td width="190" align="left" nowrap="nowrap">
            	<font face="Arial" size="2" >
            		<bean:message key="classificationLevel.newClassificationLevel" />
            	</td>
                <td width="140" align="left" nowrap="nowrap">
            	</font>
          		    <html:text property="newClassLevel" size="64"  />
          		</td>
          		</table>
          	</td>
       </tr>
       <tr>
          <td width="10" nowrap="nowrap"></td>
          	<td width="900" align="left" nowrap="nowrap">
            	<table>
          	 	<td width="190" align="left" nowrap="nowrap">
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
          <td width="900" align="left" nowrap="nowrap">
           	<table>
          	 	<td width="190" align="left" nowrap="nowrap">
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
	   	 	<td width="900" align="left" nowrap="nowrap">
	   	 	  <table>
   				<td align="left" height="69" class="tab" nowrap="nowrap">
  					&nbsp;<td align="right" colspan="5" width="420" height="69" class="tab" nowrap="nowrap">
  					<fieldset style="width: 202px; height: 52px; padding: 2">
					<legend><bean:message key="Masters.Action"/></legend>
					<input type="radio" value="N" name="new1" onclick="check1();" checked> 
					<bean:message key="stockmaster.new"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					<input type="radio" name="update" value="U" onclick="check2();"><bean:message key="classes.update"/></fieldset><tr>
   					<td align="right" nowrap="nowrap">
  					&nbsp;<td align="center" width="6%" nowrap="nowrap">
   				
   				<td width="260" align="right" nowrap="nowrap">
   				<html:hidden property="operation"/>
		       	<html:submit onclick="SaveFunc()" > 
		      		<bean:message key="indexUpdate.save"/>
                </html:submit>
		       	</td>
		       	<td width="50" align="left" nowrap="nowrap">
		       	<html:button property="resetButton" onclick="resetFunc();"  > 
		       		<bean:message key="indexUpdate.reset"/>
             	</html:button>
	  			</td>
	  			<td width="150" align="left" nowrap="nowrap">
	  	       	<html:button onclick="history.go(-1)" property="">
         			<bean:message key="indexUpdate.cancel"/>
      			</html:button> 
      			</td>  
     		    </table>	    	
		    </td>
		</tr>	
		</td></tr></table> 
		<script language="javascript">
	    document.forms[0].new1.checked = true;
		document.forms[0].update.checked = false;
		</script>
	 </html:form>
<script language="JavaScript">
function changeFields(){
   	document.forms[0].operation.value		=	"changeFields";
   	document.forms[0].radioButton.value		=	"Bottom";
   	document.forms[0].selectClassLevel.value=	"value0";
   	document.forms[0].submit();
}

function changeFieldClassLevel(){
   	document.forms[0].operation.value		=	"changeFieldClassLevel";
   	if(document.forms[0].selectClassLevel.value=="value0"){
   		document.forms[0].radioButton[0].disabled=false;
		document.forms[0].radioButton[1].disabled=false;
		document.forms[0].radioButton[2].disabled=false;
  		document.forms[0].afterLevel.disabled=false;
  		var val=document.getElementById("CLName").innerHTML;
  		document.forms[0].newClassLevel.value=val;
	    document.forms[0].newClassLevel.select();
	    document.forms[0].newClassLevel.focus();
	    document.forms[0].shortName.value="";
	    document.forms[0].description.value="";
    	return false;
    }
    document.forms[0].submit();
}
     
function SaveFunc(){
   	document.forms[0].operation.value		=	"Save";
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

function resetFunc(){
   	 document.forms[0].newClassLevel.value="";
   	 document.forms[0].shortName.value="";
	 document.forms[0].description.value="";
}
    var aL		=	document.getElementById("clLevel");
    if(aL.options.length<=1){
  		
  		document.forms[0].radioButton[0].checked=true;
  		document.forms[0].radioButton[1].disabled=true;
  		document.forms[0].radioButton[2].disabled=true;
  		document.forms[0].afterLevel.disabled=true;
  	}
  	else{
  		document.forms[0].radioButton[1].checked=true;
  	}
    </script>
    <html:javascript formName="classificationLevelBean"/>
  </body>
</html:html>
