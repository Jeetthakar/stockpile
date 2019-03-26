<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page language="java" import="app.*,java.sql.*" %>
<%@ page import="java.util.*"%>

<%@ page import="org.apache.log4j.PropertyConfigurator"%>
<%@page import="org.apache.log4j.Logger"%>
<% 
System.out.println("***** GetBaseValues *****");
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<html>
<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Child Index Composition</title>
<html:base/>
</head>
<jsp:useBean id="baseValues" scope="session" class="app.BaseValuesForm"/>
<jsp:useBean id="indexComposition" scope="session" class="app.IndexCompositionForm"/>
<jsp:useBean id="newIndexForm" scope="session" class="app.NewIndexForm"/>
<tr>
            <td width="100%" bgcolor="#FFFFFF" height="1" align="center">
              <p><font size="4" face="Arial Black">&nbsp;&nbsp; Child Index Composition</font>
              </p></td>
          </tr>	

          <%log.info("Form resubmitted2");%>   
<jsp:setProperty name="baseValues" property="newIndexForm" value="<%= newIndexForm %>"/>
<logic:equal value="yes" scope="request" parameter="fromcomposition">
<jsp:setProperty name="baseValues" property="fromComposition" value="yes"/>
<%log.info("Form resubmitted");%>
<script>
 document.forms[0].submit();
 </script>
 </logic:equal>
 <logic:notEqual value="yes" scope="request" parameter="fromcomposition">
<jsp:setProperty name="baseValues" property="fromComposition" value="no"/>
 </logic:notEqual>
	   
            
      <html:form action="/Proceed">
<!--      <input type="hidden" name="indexName" value="<request.getParameter('indexName')%>")>-->
    	   <br>
           <tr>
            <td width="44%" align="left" bgcolor="#D8D8D8" height="23">
              <p><font face="Arial" size="2">Parent INDEX
              Name: </font><font face="Arial" size="2" color="red"><bean:write name="newIndexForm" property="s_indexName"/></font></p>
	
		</td></tr>
<html:errors/>
          <tr>
            <td width="100%" bgcolor="#D8D8D8" height="99">
              &nbsp;

              <table border="1" width="71%" bordercolorlight="#000000" cellspacing="0" cellpadding="0" bordercolordark="#000000">
                <tr>
                  <td width="100%">
                    <table border="1" width="100%" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="5%" align="center" bgcolor="#FFFFFF"><b>Sr. No.</b></td>
                        <td width="23%" align="center" bgcolor="#FFFFFF"><font size="2" face="Arial"><b>Index
                          Name</b></font></td>
                        <td width="5%" align="center" bgcolor="#FFFFFF"><font size="2" face="Arial"><b>Base
                          Value</b></font></td>
                      </tr>
                      
                      <%= baseValues.getTableString() %>
                    </table>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
          <tr>
            <td width="100%" bgcolor="#D8D8D8" height="40">
              <table border="0" width="100%" height="74">
                <tr>
                  <td width="50%" height="70"><input type="submit" value="<< Back" name="B4" ></td>
                  <td width="50%" height="70"><input type="submit" value="Save and Continue" name="B3" ></td>
                </tr>
              </table>
 </td>
          </tr>
        <tr>
		<form enctype = "multipartform">
		  </form></tr> 
		 
<logic:equal value="empty" name="baseValues" property="tableString">
<script>
<%log.info("Form resubmitted1");%>
 document.forms[0].submit();
</script>
</logic:equal>		   
       </html:form>
        <p align="right">&nbsp;
</p><table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="2%"></td>
    <td width="98%"></td>
  </tr>
</table>
<script language="javascript">

function test4()
 { 
 	document.forms[0].B3.value="Submit";	
 	
  	return true;
 } 
 
 function isit(inputval) { 
	
	if( isNaN( parseFloat(inputval))) {
			 alert("BaseValue should be a numeric");	
   			 return false;
 	 }  else {  
 		 if(parseFloat(inputval)>0.0){
 			 return true;
 		 }else{
 		 	alert("IWF should be greater than 0.0");	
 			 return false;
  		}   
 	}
}

</script>


</html>