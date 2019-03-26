<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="java.util.*" %>
<%@ page language="java" import="app.*,java.io.*"%>
<%@ page import = "javax.servlet.http.HttpServletRequest" %>
<%@ page import = "javax.servlet.http.HttpServletResponse" %>
<%@ page language="java" import="app.*"%><%@page import="org.apache.log4j.Logger"%>
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
			response.sendRedirect("userlogintemp.jsp");
		}
		
		%>
<jsp:useBean id="GICSFileUploadBean" scope="session" class="app.GICSFileUploadForm"/>
<html:html>
<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
	<script language="javascript" src="box_ex.js"></script>
	<script language="javascript">
		var c2 = new CodeThatCalendar(caldef2);
	</script>
<link href="./StyleSheet.css" rel="stylesheet" type="text/css">	
			
<body>
<html:form action="/GICSFileUpload" method="post" enctype="multipart/form-data">
<jsp:setProperty name="GICSFileUploadBean" property="id" value='<%=request.getParameter("classification")%>'/>
<jsp:setProperty name="GICSFileUploadBean" property="d1" value='<%=request.getParameter("Exchange")%>'/>
		<font  size="4"><b></font><p align="center" ><bean:message key="newUsers.title"/></p></b></font>
		<hr>
		<table class="tab">
		<tr>
			<td><bean:message key="newUsers.selectcla"/>:</td>
			<td>
				<html:select property="id" name="GICSFileUploadBean">
					<html:optionsCollection property="vector_classlist" name="GICSFileUploadBean"/>
				</html:select>
			</td>
		</tr>
		
			
		<tr>
			<td><bean:message key="newUsers.select"/></td>
			<td>
				<html:select property="d1" name="GICSFileUploadBean">
					<html:optionsCollection property="exchangeCollection" name="GICSFileUploadBean"/>
				</html:select>
			</td>
		</tr>
		<tr>
			
		</tr>
		<tr>
			<td>
				<bean:message key="newUsers.File"/>
			</td>
			<td>
				<html:select property="fileType"  name="GICSFileUploadBean">
					<html:optionsCollection property="fileTypeCollections" name="GICSFileUploadBean" />
				</html:select>
			</td>
		</tr>
		<tr>
			<td>
					<bean:message key="newUsers.Attach"/>
			</td>
			<td>
				<html:file property="theFile"></html:file>
			</td>
			</tr>
			<tr>
			
				<td align="right" >
					<html:hidden property="hiddenVar" name="GICSFileUploadBean" />
					<html:submit property="b1"  onclick="setValue();" value="Show"></html:submit>
				</td>
				<td align="left" >
					<html:hidden property="b2var" name="GICSFileUploadBean"></html:hidden>
					<html:button property="b2" onclick="setval1();" value="Save"></html:button>
				</td>
				
		</tr>
		</table>
		<table class="tab" border="1"> 	
			<%
			try{
					if(GICSFileUploadForm.getDisp_buffer()!=null){	%>
					<%=GICSFileUploadForm.getDisp_buffer() %>
					<%	 	}
				}catch(Exception e){log.info("error"+e.getMessage());}
			
			%>
	</table>
</html:form>
</body>

<script language="javascript"> 
function setValue(){
	//alert("The file should be in .csv formate");
	document.forms[0].hiddenVar.value="show";
	document.forms[0].submit();
}
function setval1(){
	document.forms[0].hiddenVar.value="save";
	document.forms[0].submit();
}
</script>
</html:html>