<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="app.*,java.util.*"%>
<%@page import="org.apache.log4j.Logger"%>
<%
	Logger log = Logger.getLogger(this.getClass());
	log.info("inside " + this.getClass().getSimpleName() + " page");
%>
<html:html>
<head>
<html:base />
</head>
<%
	response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
%>

<body onload="initialize();" locale="true">
	<br>
	<br>
	<br>
	<br>
	<table bgcolor="#84AADE" cellpadding="4" cellspacing="0" border="0"
		align="center" width="325">
		<th bgcolor="#DEE3EF" colspan="2" align=left><font size=3.5>Login</font>
		</th>
		<html:form action="/LogonSubmit" focus="username">
			<html:errors />
			<%
				if (request.getParameter("invalid") != null
								&& request.getParameter("invalid").equals("yes")) {
			%><script language="javascript">
			alert('Invalid userName Password');
		</script>
			<%
				}
			%>
			<%
				if (((request.getParameter("action")) != null)
								&& ((request.getParameter("action"))
										.equals("logout"))
								&& (request.isRequestedSessionIdValid())) {
							if ((request.getSession(false)) != null) {
								session.invalidate();
			%>
			<script language="javascript">
				top.topFrame.location.reload();
				top.treeFrame.location.reload();
			</script>
			<%
				}
						}

						if ((request.isRequestedSessionIdValid())
								&& ((request.getParameter("user")) == null)) {

							if ((request.getSession(false)) != null) {

								session.invalidate();
			%><script language="javascript">
		top.topFrame.location.reload();
		top.treeFrame.location.reload();
	</script>
			<%
				}
						}
			%>


			<%
				try {
							if (request.isRequestedSessionIdValid())

							{
								//	if(request.getParameter("action").equals("logout"))
								//	{
								//	session.invalidate();
			%>
			<!--	<script language="javascript">
	   				//alert("logout? refress menu");
	   				top.topFrame.location.reload();	
	   				
	   				//top.treeFrame.location.reload();	
	   			</script> -->
			<%
				}
						} catch (Exception e) {
						}

						request.setAttribute("ComingFrom",
								request.getHeader("REFERER"));
			%>

			<input type="hidden" name="referer"
				value='<%=request.getHeader("REFERER")%>' />

			<tr bgcolor="#84AADE" colspan=1>
				<td nowrap="nowrap"><font color="#84AADE"><br></font></td>
			</tr>

			<tr bgcolor="#84AADE">
				<td align="right" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;User
					Name</td>
				<td align=center nowrap="nowrap"><html:text property="username"
						size="25" /></td>
			</tr>
			<tr bgcolor="#84AADE">
				<td align="right" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;Password</td>
				<td align=center nowrap="nowrap"><html:password
						property="password" size="25" /></td>
			</tr>
			<!-- --------------------New Changes  ------------------------------------- -->
			<tr bgcolor="#84AADE">
				<td align="right" nowrap="nowrap"><FONT>Language</FONT></td>
				<td align="center" nowrap="nowrap"><FONT face="Verdana">
						<html:select property="userLang" style="width=175">
							<html:option value="en">English</html:option>
							<html:option value="fr">French</html:option>
							<html:option value="de">German</html:option>
							<html:option value="es">Spanish</html:option>
							<html:option value="it">Italian</html:option>
						</html:select>
				</FONT></td>
			</tr>

			<!-- ------------------ New Changes Ends --------------------------------- -->

			<tr bgcolor="#84AADE">
				<td align="right" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="checkbox" value="ON" name="C1"></td>
				<td align="left" nowrap="nowrap"><font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Remember
						my ID on this computer</font></td>
			</tr>
			<tr bgcolor="#84AADE">
				<td align="right" nowrap="nowrap"><a href="forgot.jsp"><font
						color="blue" size="2">Forgot Password</font></A></td>
				<td align="left" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					type=submit value="Submit" name="Submit" size="15"
					onclick="return validate();">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="Reset"
					name="Reset" size="15"></td>
			</tr>
	</table>
	</html:form>
</body>
<script language="javascript">
	function initialize() {
		document.forms[0].username.value = "";
		document.forms[0].password.value = "";
		document.forms[0].username.focus();
	}
	function validate() {
		if (document.forms[0].username.value == "") {
			alert("  Enter the username  ");
			document.forms[0].username.focus();
			return false;
		}

		if (document.forms[0].password.value == "") {
			alert("  Enter the password  ");
			document.forms[0].password.focus();
			return false;
		}

		document.forms[0].submit();
	}
</script>
</html:html>