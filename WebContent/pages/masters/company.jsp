<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
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
<jsp:useBean id="companyBean" scope="session"
	class="harrier.income.com.masters.CompanyForm" />

<html:html>
<html:errors />
<head>
<!--  Manoj Adekar: Changes  for Tree in JSp -->
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

<SCRIPT language=javascript>
	function hideLeftCol(id) {
		if (this.document.getElementById(id).style.display == 'none') {
			this.document.getElementById(id).style.display = 'inline';
			document.getElementById("HideHandle").src = '../closeImage.gif';
			// this.document.getElementById(id).style.width='10px';
			Set_Cookie('showLeftCol', 'true', 30, '/', '', '');
			var show = Get_Cookie('showLeftCol');
			//document['HideHandle'].src = 'images/hide.gif';
			document.getElementById("HideHandle").src = '../open.gif';
		} else {
			// this.document.getElementById(id).style.display='none';
			this.document.getElementById(id).style.display = 'none';
			document.getElementById("HideHandle").src = '../openImage.gif';
			Set_Cookie('showLeftCol', 'false', 30, '/', '', '');
			var show = Get_Cookie('showLeftCol');
			//document['HideHandle'].src = 'images/show.gif';
		}
	}
</script>

<title><bean:message key="classCompany.title" /></title>
</head>

<body onload="colorRow();" class="b2">
	<table width="100%" height="100%">
		<tr>
			<td align="left" valign="top" bgcolor="#CCddee">
				<DIV id="leftCol" style="DISPLAY: none; width: 160; height: 100%;">
					<%@ include file="../tree3.jsp"%>
				</div> <IMG id="HideHandle" src="../openImage.gif"
				style="CURSOR: hand; position: absolute;"
				onclick='hideLeftCol("leftCol");' src="fold.gif" width="10"
				height="25">
			</td>
			<td align="left" valign="top"><html:form
					action="/company-action"
					onsubmit="return validateCompanyBean(this);">
					<table width="85%" class="sample">
						<tr>
							<td width="95%">
								<table border="0" width="1085" cellspacing="10" bgcolor="white">
									<td width="10" nowrap="nowrap"></td>
									<td align="left" width="700" nowrap="nowrap" class="subHeader">
										<b><bean:message key="classCompany.title" /></b>
									</td>

									<tr>
										<td width="10" nowrap="nowrap"></td>
										<td width="800" align="left" nowrap="nowrap">

											<table>
												<td width="180" align="left" nowrap="nowrap"><font
													face="Arial" size="2"><bean:message
															key="classCompany.selectCompany" /></font></td>
												<td width="600" align="left" nowrap="nowrap"><html:select
														property="selectCompany" size="1" onchange="onChange();"
														onfocus="populate(event);" onkeydown="setSelection(event)"
														onkeypress="javascript:return false">
														<html:optionsCollection name="companyBean"
															property="companyCollection" />
														</font>
													</html:select> <font face="Arial" size="1"> <a
														href="../masters/AddNewCompany.jsp"><bean:message
																key="classCompany.newC" /></a href>
												</font></td>
											</table>
											<table>
												<td width="180" align="left" nowrap="nowrap"><font
													face="Arial" size="2"><bean:message
															key="industryClasificationMaster.selectIC" /></td>

												<%-- 												<td width="700" align="left" nowrap="nowrap"></font> <html:select --%>
												<%-- 														property="selectICName" size="1" onchange="changeFields()"> --%>
												<%-- 														<html:optionsCollection name="classificationLevelBean" --%>
												<%-- 															property="icCollection" /> --%>
												<!-- 														</font> -->
												<%-- 													</html:select> <font face="Arial" size="1"> <a --%>
												<%-- 														href="../masters/industryClassificationMaster.jsp"><bean:message --%>
												<%-- 																key="classes.newInd" /></a href> --%>
												<!-- 												</font></td> -->



												<td width="600" align="left" nowrap="nowrap"></font> <html:select
														property="selectICName" size="1" onchange="changeFields()">
														<html:optionsCollection name="companyBean"
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
										<td width="10" nowrap="nowrap"></td>
										<td width="700" align="left" nowrap="nowrap">
									</tr>
									<tr>
										<td width="10" align="right"></td>
										<td width="650" align="left"><html:submit
												onclick="saveFunc();">
												<bean:message key="indexUpdate.save" />&nbsp;&nbsp;
                </html:submit> <html:button onclick="history.go(-1)" property="">
												<bean:message key="indexUpdate.cancel" />
											</html:button></td>
										<td width="56" align="left">
										<td width="220" align="right"></td>

									</tr>

									<tr>
										<td width="10" nowrap="nowrap"></td>
										<td width="700" align="left" nowrap="nowrap">

											<table border="0" width="650">
												<tr bgcolor="#DEE3EF">
													<th align="left"><font size="2" face="Arial"><bean:message
																key="Admin.Choice" /></font></th>
													<th align="left"><font size="2" face="Arial"><bean:message
																key="Admin.Classification" /></font></th>
												</tr>
												<%
													int num = 0;
												%>
												<logic:iterate id="theprod" name="companyBean"
													property="tableData">
													<tr>
														<%
															if (num % 2 == 0) {
														%>
														<td width="10%"><html:multibox
																property="selectedCheckbox" onclick="colorRow()">
																<bean:write name="theprod" property="partNumber" />
															</html:multibox></td>
														<bean:define id="sel" name="theprod" property="partNumber"
															type="java.lang.Integer" />
														<%
															String str = "name" + sel;
														%>
														<td width="90%" bgcolor="#84AADE"><font size="2"
															face="Arial" id="<%=str%>"> <bean:write
																	name="theprod" property="part" />
														</font></td>
														<%
															} else {
														%>
														<td width="10%" bgcolor="#DEE3EF"><html:multibox
																property="selectedCheckbox" onclick="colorRow()">
																<bean:write name="theprod" property="partNumber" />
															</html:multibox></td>
														<td width="90%" bgcolor="#DEE3EF"><bean:define
																id="sel1" name="theprod" property="partNumber"
																type="java.lang.Integer" /> <%
 	String str1 = "name" + sel1;
 %> <font size="2" face="Arial" id="<%=str1%>"> <bean:write
																	name="theprod" property="part" />
														</font></td>
														<%
															}
																		num++;
														%>
													
												</logic:iterate>
												</tr>
											</table>
										</td>
									</tr>


									<tr>
										<td width="10" align="right"></td>
										<td width="546" align="left"><html:hidden
												property="operation"></html:hidden> <html:submit
												onclick="saveFunc();">
												<bean:message key="indexUpdate.save" />&nbsp;&nbsp;
		       			
                </html:submit> <html:button onclick="history.go(-1)" property="">
												<bean:message key="indexUpdate.cancel" />
											</html:button></td>
										<td width="56" align="left">
										<td width="220" align="right"></td>

									</tr>


								</table>
							</td>
						</tr>
					</table>
				</html:form></td>
		</tr>
	</table>

	<script language="JavaScript">
		var keyTime, keyStr = '', allOpts, lastElement;
		var agt = navigator.userAgent.toLowerCase();
		var is_gecko = (agt.indexOf("gecko") != -1);
		function populate(srcEvent) {

			var element = (srcEvent) ? ((srcEvent.target) ? srcEvent.target
					: srcEvent.srcElement) : window.event.srcElement;
			if (lastElement != element) {
				allOpts = new Array();
				for (var i = 0; i < element.options.length; i++)
					allOpts[i] = element.options[i].text.toLowerCase();
				lastElement = element;
			}
		}
		function setSelection(srcEvent) {
			var myEvent = (srcEvent) ? srcEvent : window.event;
			var element = (myEvent.target) ? myEvent.target
					: myEvent.srcElement;
			var currentKey = unescape('%' + myEvent.keyCode.toString(16))
					.toLowerCase();
			var idx, currentSIdx = element.selectedIndex, useOld = false;
			var newTime = new Date().getTime();
			if (keyTime != null && newTime - keyTime < 500) {
				keyStr += currentKey;
				idx = findIdx();
				if (idx == -1)
					idx = currentSIdx;
			} else {
				keyStr = currentKey;
				idx = currentSIdx + 1;
				if (idx >= allOpts.length || allOpts[idx].length == 0
						|| allOpts[idx].charAt(0) != keyStr)
					idx = findIdx();
			}
			if (idx >= 0) {
				element.options[currentSIdx].selected = false;
				var pattern = new RegExp('^' + keyStr.charAt(0) + '+$', "i");
				if (is_gecko && pattern.test(keyStr) && idx > 0)
					element.options[idx - 1].selected = true;
				else
					element.options[idx].selected = true;
			}
			keyTime = newTime;
		}
		function findIdx() {
			var len = keyStr.length;
			for (var i = 0; i < allOpts.length; i++)
				if (allOpts[i].length >= len
						&& allOpts[i].substring(0, len) == keyStr)
					return i;
			return -1;
		}

		function onChange() {
			document.forms[0].selectICName.value = "value0";
		}
		function changeFields() {
			document.forms[0].operation.value = "ICChange";
			if (document.forms[0].selectICName.value == "value0")
				return false;
			else
				document.forms[0].submit();
		}

		function saveFunc() {
			document.forms[0].operation.value = "Save";

			return true;
		}

		//METHOD FOR COLORING FONT COLOR FOR THE SELECTED CHECK BOX
		function colorRow() {
			var len = document.forms[0].selectedCheckbox.length;
			for (var i = 0; i < len; i++) {

				if (document.forms[0].selectedCheckbox[i].checked == true) {
					var selChk = document.forms[0].selectedCheckbox[i].value;
					var val = "name" + selChk;
					var rhsSelect = document.getElementById(val);
					rhsSelect.style.color = "blue";

				} else {
					var selChk = document.forms[0].selectedCheckbox[i].value;
					var val = "name" + selChk;
					var rhsSelect = document.getElementById(val);
					rhsSelect.style.color = "black";
				}

			}
			return true;
		}
	</script>
	<html:javascript formName="companyBean" />

</body>
</html:html>