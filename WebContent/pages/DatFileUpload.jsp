
<%@ page import="java.util.*" %>
<%@ page language="java" import="app.*,java.io.*"%>
<%@ page import = "javax.servlet.http.HttpServletRequest" %>
<%@ page import = "javax.servlet.http.HttpServletResponse" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
	LogonForm form = null;
	if(request.isRequestedSessionIdValid())	{	
		form = (LogonForm)session.getAttribute("user");
	}
	if(form==null ||(!request.isRequestedSessionIdValid())){
		response.sendRedirect("userlogintemp.jsp");
	}
			
%>


<jsp:useBean id="DatFileUploadBean" scope="session" class="app.DatFileUploadForm"/>
<html:html>
<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
	<script language="javascript" src="box_ex.js"></script>
	<script language="javascript">
		var c2 = new CodeThatCalendar(caldef2);
	</script>
<link href="./StyleSheet.css" rel="stylesheet" type="text/css">		
<body onload="initialize()">
	<html:form action="/datFileUpload" enctype="multipart/form-data" method="post" >
<jsp:setProperty name="DatFileUploadBean" property="fileType" value='<%=request.getParameter("fType")%>'/>
<jsp:setProperty name="DatFileUploadBean" property="d1" value='<%=request.getParameter("Exchange")%>'/>

		<font  size="4"><b></font><p align="center" >Imoprt INDEXOHLC File </p></b></font>
		<hr>
		<table class="tab" >
		<tr>
			<td><bean:message key="Masters.SelectExch"/></td>
			<td>
				<html:select property="d1" name="DatFileUploadBean">
					<html:optionsCollection property="exchangeCollection" name="DatFileUploadBean"/>
				</html:select>
			</td>
		</tr>
		
		<tr>
			<td>
				<bean:message key="newUsers.File"/>
			</td>
			<td>
				<html:select property="fileType"  name="DatFileUploadBean">
					<html:optionsCollection property="fileTypeCollections" name="DatFileUploadBean" />
				</html:select>
			</td>
		</tr>
		<tr>
			<td>
				<bean:message key="newUsers.Date"/>
			</td>
			<td>
			<logic:equal value="yes" property="hiddenVar" name="DatFileUploadBean">
				<html:text property="date" name="DatFileUploadBean" onchange="checkdate(this)" />
			</logic:equal>
			<logic:notEqual value="yes" property="hiddenVar" name="DatFileUploadBean">
				<html:text property="date" name="DatFileUploadBean" onchange="checkdate(this)" />
			</logic:notEqual>
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
				<td>
					<html:hidden property="hiddenVar" name="DatFileUploadBean" value="no" />
					<html:submit property="b1"  onclick="setValue()" value="Show" ></html:submit>
				</td>
			<td>
				<html:button property="b2" onclick="setval1();" value="Save">  </html:button>
				<html:hidden property="b2var" name="DatFileUploadBean" value="no" ></html:hidden>
			</td>
		</tr>
		</table>
	<logic:equal value="yes" property="hiddenVar" name="DatFileUploadBean">
	
		<table class="tab" border="1"> 	
			<%			
			StringBuffer str = DatFileUploadForm.getDisp_buffer();
			try{
					if(str!=null){
						String delim=",";
						StringTokenizer stk = new StringTokenizer(str.toString(),",");
						String token="";
						String textname=null;
						int cnt=0;
						String skey=new String("");
						String key;
					%>
					
						<tr><td>Index Name</td><td>previous close</td><td>open close</td><td>high close</td><td>low close</td><td>close</td><td>52_week_high</td><td>52_week_low</td><td>percentage change</td><td>date</td></tr>
							
						<%	
						while(stk.hasMoreTokens()){
							textname=stk.nextToken();
							token=stk.nextToken();%>
							<tr>
							<% log.info("name of index  ctrl from jsp"+textname);%>							
							<td><input type="text" name="<%=textname%>" value="<%=token%>" ></td>
							<% log.info("value of index"+token);%>
							<%token=stk.nextToken();%>
							<td><input type="text" value="<%=token%>" disabled="disabled"></td>
							<%token=stk.nextToken();%>
							<td><input type="text" value="<%=token%>" disabled="disabled"></td>
							<%token=stk.nextToken();%>
							<td><input type="text" value="<%=token%>" disabled="disabled"></td>														
							<%token=stk.nextToken();%>
							<td><input type="text" value="<%=token%>" disabled="disabled"></td>																					
							<%token=stk.nextToken();%>
							<td><input type="text" value="<%=token%>" disabled="disabled"></td>
							<%token=stk.nextToken();%>
							<td><input type="text" value="<%=token%>" disabled="disabled"></td>
							<%token=stk.nextToken();%>
							<td><input type="text" value="<%=token%>" disabled="disabled"></td>
							<%token=stk.nextToken();%>
							<td><input type="text" value="<%=token%>" disabled="disabled"></td>														
							<%token=stk.nextToken();%>
							<td><input type="text" value="<%=token%>" disabled="disabled"></td>																					
  						    </tr>

						<% 	}
			}	}catch(Exception e){log.info("error"+e.getMessage());}
			
			%>
	</table>
	</logic:equal>
	<logic:equal value="yes" property="b2var" name="DatFileUploadBean">
	<table class="tab" border="1"> 	
			
			<%
			try{
			log.info("inside try block off save method ");
					if(DatFileUploadForm.getSave_buffer()!=null){	%>
					<%=DatFileUploadForm.getSave_buffer() %>
					<%	 	}
				}catch(Exception e){log.info("error****************"+e.getMessage());}
			
			%>
			</tr>
	</table>
	</logic:equal>
	
</html:form>
</body>

<script language="javascript"> 
function initialize() {
	var today = new Date();
	var td = today.getDate();
	if(td<10)
	td="0"+td;
	var mnth = today.getMonth(); 
	mnth=mnth+1;
	if(mnth<10)
	mnth="0"+mnth;
	var yr = today.getFullYear();
	var s = "-";
	if((document.forms[0].date.value)=="")
	document.forms[0].date.value= td+ s + mnth + s + yr;
	
}
function setValue(){
	document.forms[0].hiddenVar.value="yes";
	document.forms[0].submit();
}
function setval1(){
	document.forms[0].b2var.value="yes";
	document.forms[0].submit();
}
</script>
</html:html>