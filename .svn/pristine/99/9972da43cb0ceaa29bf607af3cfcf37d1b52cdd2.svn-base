<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.harrier.initializeation.ConnectInit"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page
	import="sysconfig.model.*,java.util.*,harrier.income.com.masters.*, org.apache.struts.util.*"%>
<%@ page language="java" import="app.*"%><%@page
	import="org.apache.log4j.Logger"%>
<%
	Logger log = Logger.getLogger(this.getClass());
	log.info("inside " + this.getClass().getSimpleName() + " page");
%>
<%
	LogonForm form = null;
	if (request.isRequestedSessionIdValid()) {
		form = (LogonForm) session.getAttribute("user");
		String locale = session.getAttribute("locale").toString();
		//	AcessControl asc=new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		asc.setLocale(locale);
	}

	if (form == null || (!request.isRequestedSessionIdValid()))
		response.sendRedirect("../userlogintemp.jsp");
%>
<jsp:useBean id="classesBean" scope="session"
	class="harrier.income.com.masters.classesForm" />
<html:html>
<html:errors />
<head>
<html:base />
<META Http-Equiv="Cache-Control" Content="no-cache">
<META Http-Equiv="Pragma" Content="no-cache">
<META Http-Equiv="Expires" Content="0">
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 6.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<link rel="stylesheet" type="text/css" href="./StyleSheet.css"
	title="Default" />


<title><bean:message key="classes.title" /></title>
</head>

<body onload="resetAll();" class="b2">
	<html:form action="classes-action"
		onsubmit="return validateClassesBean(this);">
		<table width="120%" class="sample">
			<tr>
				<td width="120%" nowrap="nowrap">
					<table border="0" width="78%" cellspacing="0" cellpadding="3">

						<td width="4%" nowrap="nowrap"></td>
						<td align="left" width="77%" nowrap="nowrap" class="subHeader">
							<b> <span id="name"><bean:message key="classes.title" /></span></b>
						</td>


						<tr>
							<td width="4%" nowrap="nowrap"></td>
							<td width="77%" align="left" nowrap="nowrap">
								<table width="700">
									<td width="180" align="left" nowrap="nowrap"><font
										face="Arial" size="2"> <bean:message
												key="industryClasificationMaster.selectIC" /></td>
									<td width="520" align="left" nowrap="nowrap"></font>
									
									 <html:select
											property="selectICName" size="1" onchange="changeFields()">
											<html:optionsCollection name="classesBean"
												property="icCollection" />
											</font>
										</html:select> <font face="Arial" size="1"> <a
											href="../masters/industryClassificationMaster.jsp"><bean:message
													key="classes.newInd" /></a href>
									</font></td>
									</font>
								</table>
							</td>

						</tr>

						<tr>
							<td width="4%" nowrap="nowrap"></td>
							<td width="77%" align="left" nowrap="nowrap">
								<table width="700">
									<td width="180" align="left" nowrap="nowrap"><font
										face="Arial" size="2"> <bean:message
												key="classificationLevel.selectClassificationLevel" /></td>
									<td width="520" align="left" nowrap="nowrap"></font> <html:select
											property="selectClassLevel" size="1"
											onchange="changeFieldClassLevel()">
											<html:optionsCollection name="classesBean"
												property="levelCollection" />
											</font>
										</html:select> <font face="Arial" size="1"> <a
											href="../masters/classificationLevel.jsp"><bean:message
													key="classes.newLevel" /></a href>
									</font></td>
									</font>
								</table>
							</td>

						</tr>

						<tr>
							<td width="4%" nowrap="nowrap"></td>
							<td width="77%" align="left" nowrap="nowrap">
								<table>

									<td width="180" align="left" nowrap="nowrap"></font> <html:radio
											property="radioButton" value="Add" onclick="disableFunc();" />
										<font face="Arial" size="2" color="#0080ff"><bean:message
												key="classes.add" /></font></td>
									<td width="500" align="left" nowrap="nowrap">
										<table>
											<td width="130" align="left" nowrap="nowrap"><font
												face="Arial" size="2"><span id="selectPClass"><bean:message
															key="classes.selectParentClass" /></span></font></td>
											<td width="150" align="left" nowrap="nowrap"><html:select
													property="selectParentClassLevel" size="1">
													<html:optionsCollection name="classesBean"
														property="parentLevelCollection" />
													</font>
												</html:select></td>
										</table>

									</td>
									</font>
								</table>
							</td>

						</tr>

						<tr>
							<td width="4%" nowrap="nowrap"></td>
							<td width="77%" align="left" nowrap="nowrap">
								<table>

									<td width="180" align="left" nowrap="nowrap"></font> <html:radio
											property="radioButton" value="Update"
											onclick="disableFunc1();" /> <font face="Arial" size="2"
										color="#0080ff"><bean:message key="classes.update" /></font></td>
									<td width="300" align="left" nowrap="nowrap">
										<table>
											<td width="130" align="left" nowrap="nowrap"><font
												face="Arial" size="2"><span id="selectClass"><bean:message
															key="classes.selectClass" /></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											</font></td>
											<td width="150" align="left" nowrap="nowrap"><html:select
													property="selectClass" size="1"
													onchange="selectClassFunc();">
													<html:optionsCollection name="classesBean"
														property="classCollection" />
													</font>
												</html:select></td>
										</table>

									</td>
									</font>
								</table>
							</td>

						</tr>

						<tr>
							<td width="4%" nowrap="nowrap"></td>
							<td width="77%" align="left" nowrap="nowrap">
								<table>
									<td width="180" align="left" nowrap="nowrap"><font
										face="Arial" size="2"> <bean:message
												key="classes.newClassName" /></td>

									<td width="200" align="left" nowrap="nowrap"></font> <html:text
											property="newClassName" size="64" /></td>
								</table>
							</td>
						</tr>

						<tr>
							<td width="4%" nowrap="nowrap"></td>
							<td width="77%" align="left" nowrap="nowrap">
								<table>
									<td width="180" align="left" nowrap="nowrap"><font
										face="Arial" size="2"> <bean:message
												key="classes.code" /></td>

									<td width="200" align="left" nowrap="nowrap"></font> <html:text
											property="classCode" size="64" /></td>
								</table>
							</td>
						</tr>

						<tr>
							<td width="4%" nowrap="nowrap"></td>
							<td width="77%" align="left" nowrap="nowrap">
								<table>
									<td width="180" align="left" nowrap="nowrap"><font
										face="Arial" size="2"> <bean:message
												key="industryClasificationMaster.ShortName" /></td>

									<td width="200" align="left" nowrap="nowrap"></font> <html:text
											property="shortName" size="64" /></td>
								</table>
							</td>
						</tr>
						<tr>
							<td width="4%" nowrap="nowrap"></td>
							<td width="77%" align="left" nowrap="nowrap">
								<table>
									<td width="180" align="left"><font face="Arial" size="2">

											<bean:message key="industryClasificationMaster.description" />

									</font></td>

									<td width="200" align="left" nowrap="nowrap"><html:text
											property="description" size="64" /></td>
								</table>
							</td>
						</tr>
						<tr>
							<td width="4%" align="right" nowrap="nowrap"></td>
							<td width="77%" align="left" nowrap="nowrap">

								<table>
									<td width="250" align="right" nowrap="nowrap"><html:hidden
											property="operation" /> <html:submit
											onclick="return SaveFunc();">
											<bean:message key="indexUpdate.save" />
										</html:submit></td>
									<td width="50" align="left" nowrap="nowrap"><html:button
											property="resetButton" onclick="resetFunc();">
											<bean:message key="indexUpdate.reset" />
										</html:button></td>
									<td width="150" align="left" nowrap="nowrap"><html:button
											onclick="history.go(-1)" property="">
											<bean:message key="indexUpdate.cancel" />
										</html:button></td>
								</table>

							</td>


						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>

	<script language="JavaScript">
		function clearAll() {
			document.forms[0].newClassName.value = "";
			document.forms[0].classCode.value = "";
			document.forms[0].shortName.value = "";
			document.forms[0].description.value = "";
		}
		if (document.forms[0].selectClassLevel.value == "value0") {
			clearAll();
		}
		function resetFunc() {
			clearAll();
			document.forms[0].radioButton[0].checked = true;
			document.forms[0].selectClass.disabled = true;
			document.forms[0].selectParentClassLevel.disabled = false;
			document.forms[0].selectClass.value = "value0";
		}
		function changeFields() {
			document.forms[0].operation.value = "changeFields";
			document.forms[0].submit();
		}
		function changeFieldClassLevel() {
			document.forms[0].operation.value = "changeFieldClassLevel";
			document.forms[0].newClassName.value = "";
			document.forms[0].classCode.value = "";
			document.forms[0].shortName.value = "";
			document.forms[0].description.value = "";
			document.forms[0].submit();
		}
		function SaveFunc() {
			document.forms[0].operation.value = "Save";
			if (document.forms[0].radioButton[1].checked == true) {
				if (document.forms[0].selectClass.value == "value0") {
					var val = document.getElementById("selectClass").innerHTML;
					alert(val);
					return false;
				}
			}
			if (document.forms[0].radioButton[0].checked == true) {

				if (document.forms[0].selectParentClassLevel.value == "value0") {
					var val2 = document.forms[0].selectClassLevel[0].selected;
					var val4 = document.forms[0].selectClassLevel[1].selected;
					var chk = true;
					if (val2 || val4) {
						chk = false;
					}

					if (chk) {

						var val1 = document.getElementById("selectPClass").innerHTML;
						alert(val1);
						return false;

					}
				}
			}

			return true;
		}
		function disableFunc() {
			document.forms[0].selectClass.disabled = true;
			document.forms[0].selectParentClassLevel.disabled = false;
		}
		function disableFunc1() {
			document.forms[0].selectParentClassLevel.disabled = true;
			document.forms[0].selectClass.disabled = false;
		}
		function selectClassFunc() {
			document.forms[0].operation.value = "selectClass";

			var val1 = document.forms[0].selectClass.value;
			if (val1 == "value0") {
				document.forms[0].newClassName.value = "";
				document.forms[0].classCode.value = "";
				document.forms[0].shortName.value = "";
				document.forms[0].description.value = "";
				return false;
			}
			document.forms[0].submit();
		}
		function resetAll() {
			if (document.forms[0].radioButton[0].checked == true) {
				disableFunc();
			} else {
				disableFunc1();
			}
		}
	</script>
	<html:javascript formName="classesBean" />
</body>
</html:html>
