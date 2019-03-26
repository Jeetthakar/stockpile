<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page
	import="java.util.*, org.apache.struts.util.*,sysconfig.action.*"%>
<%@ page language="java" import="app.*"%>
<%@page import="org.apache.log4j.Logger"%>
<%
	Logger log = Logger.getLogger(this.getClass());
	log.info("inside " + this.getClass().getSimpleName() + " page");
%>
<%
	LogonForm form = null;
	if (request.isRequestedSessionIdValid()) {
		form = (LogonForm) session.getAttribute("user");
	}
	if (form == null || (!request.isRequestedSessionIdValid())) {
		response.sendRedirect("userlogintemp.jsp");
	}
%>
<jsp:useBean id="indexUpdateBean" scope="session"
	class="sysconfig.action.IndexUpdateForm" />

<html:html>
<html:errors />
<head>

<SCRIPT language=javascript>
	function hideLeftCol(id) {
		if (this.document.getElementById(id).style.display == 'none') {
			this.document.getElementById(id).style.display = 'inline';
			document.getElementById("HideHandle").src = 'closeImage.gif';
			// this.document.getElementById(id).style.width='10px';
			Set_Cookie('showLeftCol', 'true', 30, '/', '', '');
			var show = Get_Cookie('showLeftCol');
			//document['HideHandle'].src = 'images/hide.gif';
			document.getElementById("HideHandle").src = '../open.gif';
		} else {
			// this.document.getElementById(id).style.display='none';
			this.document.getElementById(id).style.display = 'none';
			document.getElementById("HideHandle").src = 'openImage.gif';
			Set_Cookie('showLeftCol', 'false', 30, '/', '', '');
			var show = Get_Cookie('showLeftCol');
			//document['HideHandle'].src = 'images/show.gif';
		}
	}
</script>

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
<title><bean:message key="indexUpdate.title" /></title>
</head>

<body class="b2">
	
	<table width="100%" height="100%">

		<tr>
			<td align="left" valign="top" bgcolor="#CCddee">

				<DIV id="leftCol" style="DISPLAY: none; width: 160; height: 100%;">
					<%@ include file="tree3.jsp"%>
				</div> <IMG id="HideHandle" src="openImage.gif"
				style="CURSOR: hand; position: absolute;"
				onclick='hideLeftCol("leftCol");' src="fold.gif" width="10"
				height="25">
			</td>
			<td align="left" valign="top"><html:form action="/index-action"
					onsubmit="return validateIndexUpdateBean(this);">
					<%
						if (request.isRequestedSessionIdValid()) {
					%>
					<jsp:setProperty name="indexUpdateBean" property="user_id"
						value='<%=session.getAttribute("userid").toString()%>' />
					<%
						}
					%>
					<table width="100%" class="sample">
						<tr>
							<td width="100%" nowrap="nowrap">
						<tr>
							<td width="100%" colspan="2" height="10" nowrap="nowrap"
								class="subHeader">&nbsp;<b><bean:message
										key="indexUpdate.updateIndex" /></b> </font>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

								<font color="#0080ff" face="Arial" size="1"><bean:message
										key="indexUpdate.bold" /></font>
							</td>
						</tr>
						<td width="100%" colspan="2" height="10" nowrap="nowrap"><font
							color="blue">&nbsp; </font></td>

						</tr>

						<tr>
							<td width="1%" height="21" nowrap="nowrap">&nbsp;</td>
							<td width="100%" height="21" nowrap="nowrap"><font size="2"
								face="Arial"><b><font color="black"><bean:message
												key="indexUpdate.selectIndex" /></font><font color="red">*</font>
										&nbsp;&nbsp;</b></font> <html:select property="index_id" size="1"
									onchange="changeFields();">
									<html:optionsCollection name="indexUpdateBean"
										property="indexUpdateCollection" />
								</html:select> &nbsp;<font size="1" face="Arial"><html:checkbox
										property="test_index" disabled="true" />
									<bean:message key="indexUpdate.testIndex" /></font><b>&nbsp;&nbsp;
									&nbsp;<font size="1" face="Arial"> <logic:equal
											value="enableDropTestIndex" property="chkTestIndex"
											name="indexUpdateBean">
											<html:checkbox property="drop_test_index" disabled="false" />
										</logic:equal> <logic:equal value="disableDropTestIndex"
											property="chkTestIndex" name="indexUpdateBean">
											<html:checkbox property="drop_test_index" disabled="true"
												value="null" />
										</logic:equal> <bean:message key="indexUpdate.dropTestIndex" />
								</font><b>&nbsp;&nbsp; </td>
						</tr>
						<tr>
							<td width="1%" height="21" nowrap="nowrap"></td>
							<td width="194%" height="21" nowrap="nowrap"><html:checkbox
									property="show_child_index" onclick="return childIndexces();"
									value="show" /> <font color="black" face="Arial" size="1">
									<bean:message key="indexUpdate.child" />
							</font></td>
						</tr>
						<tr>
							<td width="1%" valign="top" height="291" nowrap="nowrap">&nbsp;</td>
							<td width="194%" valign="top" height="291" nowrap="nowrap">
								<table border="0" width="100%" cellspacing="0" cellpadding="0">
									<tr>
										<td width="100%" nowrap="nowrap">
											<table border="0" width="100%" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="33%" nowrap="nowrap"><font size="2"
														face="Arial" nowrap="nowrap"><bean:message
																key="defineIndex" /></font></td>
													<td width="8%" nowrap="nowrap">
														<p align="left">

															<html:radio property="is_child" value="child"
																disabled="true" />


														</p>
													</td>

													<td width="10%" nowrap="nowrap"><font size="2"
														face="Arial" nowrap="nowrap"> <bean:message
																key="defineIndex3" /></font></td>
													<td width="49%" align="left" nowrap="nowrap">
														<p align="left">
															<font size="1" face="Arial"> <html:text
																	property="parent_id" size="20" disabled="true" />

															</font>
														</p>
													</td>
												</tr>
												<tr>
													<td width="33%" nowrap="nowrap"><font size="2"
														face="Arial" nowrap="nowrap"><bean:message
																key="defineIndex5" /> </font></td>
													<td width="8%" nowrap="nowrap">

														<p align="left">
															<html:radio property="is_child" value="customized"
																disabled="true" />
														</p>
													</td>



													<td width="10%" align="right" nowrap="nowrap"></td>
													<td width="49%" align="left" nowrap="nowrap"></td>


												</tr>

											</table>
										</td>
									</tr>
									<tr>

										<td width="100%" nowrap="nowrap"><font color="white">----------------------------------------------------------------------------------------------------------------------------------------------------

										</font>
											<table border="0" width="100%" cellspacing="0"
												cellpadding="4">

												<tr>
													</font>
													<td width="13%" align="left"><font size="2"
														face="Arial" align="right"><b><bean:message
																	key="defineIndex7" /></b></font><font color="red">*</font></td>

													<td width="22%" align="left" nowrap="nowrap"><html:text
															property="index_name" size="20" /></td>
													<td width="13%" align="left" nowrap="nowrap"><font
														size="2" face="Arial" align="right"><b><bean:message
																	key="defineIndex6" /></b></font><font color="red">*</font></td>
													<td width="22%" align="left" nowrap="nowrap">
														<p align="left">

															<html:select property="index_type_id" size="1">
																<html:optionsCollection name="indexUpdateBean"
																	property="indexTypeCollection" />
															</html:select>


															<font size="1" face="Arial"> </font>
														</p>

													</td>
													<td width="13%" align="left" nowrap="nowrap"><font
														size="2" face="Arial" align="right"><bean:message
																key="defineIndex10" /></font></td>
													<td width="22%" align="center" nowrap="nowrap">
														<p align="left">
															<font size="1" face="Arial"> <html:text
																	property="industry_classification_id" size="25"
																	disabled="true" />


															</font>
														</p>
													</td>
												</tr>
												<tr>
													<td width="13%" align="left" nowrap="nowrap"><font
														size="2" face="Arial" align="right"><bean:message
																key="defineIndex9" /></font></td>
													<td width="22%" nowrap="nowrap"><font size="1"
														face="Arial" align="right"> <html:text
																property="creation_date" size="9" disabled="true" />&nbsp;<bean:message
																key="indexUpdate.ddmmyy" /></font></td>

													<td width="13%" align="left" nowrap="nowrap"><font
														size="2" face="Arial" align="right"><bean:message
																key="defineIndex8" /></font></td>

													<td width="22%" nowrap="nowrap">

														<p style="margin-left: 1">

															<html:text property="base_date" size="9" disabled="true" />

														</p>
													</td>
													<td width="13%" align="left" nowrap="nowrap"><font
														size="2" face="Arial" align="right"><b><bean:message
																	key="defineIndex15" /></b></font></td>
													<td width="22%" nowrap="nowrap"><font size="1"
														face="Arial" align="right"> <html:text
																property="o_ric" size="9" />
													</font></td>
												</tr>

												<tr>
													<td width="12%" align="left" nowrap="nowrap"><font
														face="Arial" size="2" align="right"><b><bean:message
																	key="defineIndex12" /></b></font><font color="red">*</font></td>

													<td width="21%" nowrap="nowrap"><font size="1"
														face="Arial" align="right"> <logic:equal value="0"
																name="indexUpdateBean" property="alert_percentage">
																<html:text property="alert_percentage" size="9" value="" />%
                     </logic:equal> <logic:notEqual value="0" name="indexUpdateBean"
																property="alert_percentage">
																<html:text property="alert_percentage" size="9" />%
                   	 </logic:notEqual>
													</font></td>
													<td width="13%" align="left" nowrap="nowrap"><font
														face="Arial" size="2" align="right"><b><bean:message
																	key="defineIndex14" /></b></font><font color="red">*</font></td>

													<td width="22%" nowrap="nowrap"><font size="1"
														face="Arial" align="right"> <logic:equal value="0"
																name="indexUpdateBean" property="rejection_percentage">
																<html:text property="rejection_percentage" size="9"
																	value="" />
															</logic:equal> <logic:notEqual value="0" name="indexUpdateBean"
																property="rejection_percentage">
																<html:text property="rejection_percentage" size="9" />
															</logic:notEqual> %
													</font></td>
													<td width="13%" align="left" nowrap="nowrap"><font
														size="2" face="Arial"><b><bean:message
																	key="defineIndex18" /></b></font></td>


													<td width="22%" nowrap="nowrap"><html:text
															property="isin" size="9" /></td>

												</tr>

												<tr>
													<td width="12%" align="left" nowrap="nowrap"><font
														size="2" face="Arial" align="right"><bean:message
																key="defineIndex16" /></font></td>

													<td width="21%" nowrap="nowrap"><font size="1"
														face="Arial" align="right"> <html:text
																property="base_currency_id" size="9" disabled="true" />


													</font></td>
													<td width="13%" align="left" nowrap="nowrap"><font
														size="2" face="Arial" align="right"><bean:message
																key="defineIndex17" /></font></td>
													<td width="22%" nowrap="nowrap"><logic:equal value="0"
															name="indexUpdateBean" property="base_value">
															<html:text property="base_value" size="9" value="" />
														</logic:equal> <logic:notEqual value="0" name="indexUpdateBean"
															property="base_value">
															<html:text property="base_value" size="9" disabled="true" />
														</logic:notEqual></td>
													<td align="left" nowrap="nowrap"><font size="2"
														face="Arial"><b><bean:message
																	key="defineIndex21" /></b> </font></td>
													<td nowrap="nowrap"><font size="1" face="Arial">

													</font>
													<p align="left">
															<font size="1" face="Arial"> </font>
															<html:checkbox property="calculate_settlement_value" />
														</p></td>

												</tr>

												<tr>
													<td width="12%" align="left" nowrap="nowrap"><font
														size="2" face="Arial"><b><bean:message
																	key="defineIndex19" /></b></font></td>



													<td width="21%" nowrap="nowrap"><font size="1"
														face="Arial" align="right"> <html:text
																property="m_start_time" size="9" />
													</font> <font size="1" face="Arial" align="right"><bean:message
																key="indexUpdate.hhmmss" /></font></td>
													<td width="13%" align="left" nowrap="nowrap"><font
														size="2" face="Arial"><b><bean:message
																	key="defineIndex20" /></b></font></td>


													<td width="22%" nowrap="nowrap"><font size="1"
														face="Arial" align="right"> <html:text
																property="n_stop_time" size="9" />
													</font> <font size="1" face="Arial" align="right"><bean:message
																key="indexUpdate.hhmmss" /></font></td>
													<td colspan="2" rowspan="2">


														<table border="1" width="50%" bordercolorlight="#9B9B9B"
															cellspacing="0" cellpadding="0">

															<tr>
																<td width="19%" nowrap="nowrap"><font size="2"
																	face="Arial"><b>&nbsp;<bean:message
																				key="defineIndex26" /></b></font></td>
																<td width="61%" nowrap="nowrap"><html:radio
																		property="growth_or_value" value="g" /> <font
																	size="2" face="Arial"><bean:message
																			key="indexUpdate.growth" /><br> </font> <html:radio
																		property="growth_or_value" value="v" /> <font
																	size="2" face="Arial"><bean:message
																			key="indexUpdate.Value" /></font><br> <html:radio
																		property="growth_or_value" value="n" /> <font
																	size="2" face="Arial"><bean:message
																			key="indexUpdate.Unknown" /></font></td>


															</tr>

														</table>


													</td>

												</tr>

												<tr>
													<td width="12%" align="left" nowrap="nowrap"><font
														size="2" face="Arial"><b><bean:message
																	key="defineIndex22" /></b></font></td>
													<td width="21%" align="center" nowrap="nowrap">

														<p align="left">
															<html:checkbox property="is_active" />
															<font size="1" face="Arial"> </font>


														</p>
													</td>
													<td width="13%" align="left" nowrap="nowrap"><font
														size="2" face="Arial"><b><bean:message
																	key="defineIndex23" /> </b></font></td>



													<td width="22%" nowrap="nowrap"><font size="1"
														face="Arial" align="right"> <logic:equal value="0"
																name="indexUpdateBean" property="computation_interval">
																<html:text property="computation_interval" size="9"
																	value="" />
															</logic:equal> <logic:notEqual value="0" name="indexUpdateBean"
																property="computation_interval">
																<html:text property="computation_interval" size="9" />
															</logic:notEqual>


													</font> <font size="1" face="Arial" align="right"><bean:message
																key="indexUpdate.inSeconds" /></font></td>

													<td width="22%" colspan="2" nowrap="nowrap"></td>

												</tr>
												<tr>
													<td width="12%" align="left" nowrap="nowrap"><font
														size="1" face="Arial"><bean:message
																key="defineIndex24" /></font></td>
													<td width="21%" nowrap="nowrap">
														<p align="left">
															<font size="2" face="Arial"> <html:checkbox
																	property="is_captured" disabled="true" />

															</font>
														</p>
													</td>

													<p align="left">&nbsp;</p>
													<td width="13%" align="left" nowrap="nowrap"><font
														size="2" face="Arial"><b><bean:message
																	key="defineIndex25" /> </b></font></td>
													<td width="17%" nowrap="nowrap">
														<p align="left">
															<html:select property="captured_from" size="1">
																<html:optionsCollection name="indexUpdateBean"
																	property="capturedFrom" />
															</html:select>

														</p>
													</td>

													<td width="22%" colspan="2" nowrap="nowrap">
														<table border="0" width="100%">
															<tr>
																<td width="100%" nowrap="nowrap">

																	<table border="1" width="50%"
																		bordercolorlight="#9B9B9B" cellspacing="0"
																		cellpadding="0">


																	</table>
																</td>
															</tr>
															<tr>
																<td width="50%" nowrap="nowrap"></td>
																<td width="50%" nowrap="nowrap"></td>
															</tr>
														</table>
													</td>
												</tr>
											</table></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr height="20">
							<td width="1%" valign="top" height="27" nowrap="nowrap">&nbsp;</td>
							<td width="99%" valign="top" height="27" nowrap="nowrap"></td>
						</tr>

						<tr>
							<td width="1%" valign="top" height="27" nowrap="nowrap">&nbsp;</td>
							<td width="50%" valign="top" height="27" nowrap="nowrap">
								<p align="center">
									<html:hidden property="operation" />
									<html:hidden property="formTwoRoleName" />
									<html:hidden property="isChild" />
									<html:hidden property="parentIndex" />
									<html:hidden property="baseCurrencyId" />
									<html:hidden property="industryClassificationId" />
									<html:hidden property="creationDate" />
									<html:hidden property="baseDate" />
									<html:hidden property="baseValue" />
									<html:hidden property="isCaptured" />
									<html:hidden property="capturedForm" />
									<html:hidden property="indexType" />
									<html:hidden property="testIndex" />
									<html:hidden property="selChild" />
								<table>
									<td width="50" align="right"><html:submit
											onclick="return saveFunc();">
											<bean:message key="indexUpdate.save" />&nbsp;
				                </html:submit></td>
									<td width="50"><html:reset value="Reset"
											onclick="return resetFunc();">
											<bean:message key="indexUpdate.reset" />
										</html:reset></td>
									<td width="90"><html:button onclick="history.go(-1)"
											property="">
											<bean:message key="indexUpdate.cancel" />
										</html:button></td>
								</table>
								</p>
							</td>
						</tr>
					</table></td>
		</tr>

		<script language="javascript">
			function changeFields() {
				document.forms[0].formTwoRoleName.value = document.forms[0].index_id.value;
				document.forms[0].operation.value = "changeFields";
				document.forms[0].selChild.value = "no";
				document.forms[0].submit();
			}
			function resetFunc() {
				document.forms[0].operation.value = "reset";

			}
		</script>
		</td>
		</tr>
	</table>
	</html:form>

	</td>
	</tr>
	</table>
	<script language="javascript">
		function childIndexces() {
			document.forms[0].selChild.value = "yes";
			document.forms[0].submit();
		}
		function saveFunc() {

			//Send the hiddden values with the submission of form
			document.forms[0].operation.value = "save";
			document.forms[0].baseCurrencyId.value = document.forms[0].base_currency_id.value;
			document.forms[0].baseDate.value = document.forms[0].base_date.value;
			document.forms[0].creationDate.value = document.forms[0].creation_date.value;
			document.forms[0].parentIndex.value = document.forms[0].parent_id.value;
			document.forms[0].industryClassificationId.value = document.forms[0].industry_classification_id.value;
			document.forms[0].baseValue.value = parseFloat(document.forms[0].base_value.value);
			var child = document.forms[0].is_child[0].checked;

			//Send the value of disabled fields(is_child,is_captured,test_index)
			var cus = document.forms[0].is_child[1].checked
			if (child == true) {
				document.forms[0].isChild.value = "child";
			} else if (cus == true) {
				document.forms[0].isChild.value = "customized";
			}
			var ck = document.forms[0].is_captured.checked;
			if (ck == true) {
				document.forms[0].isCaptured.value = "on";

			} else {
				document.forms[0].isCaptured.value = null;

			}
			var tIndex = document.forms[0].test_index.checked;
			if (tIndex == true) {
				document.forms[0].testIndex.value = "on";
			} else {
				document.forms[0].testIndex.value = null;

			}
			//Confirm that whether user is sure of dropping test index
			var dIndex = document.forms[0].drop_test_index.checked;
			if (dIndex == true) {

				var conVal = confirm("Are You Sure To Drop Test Index");
				if (conVal == true) {
					return true;
				} else {
					return false;
				}

			} else {
				return true;
			}
			return true;

		}
	</script>
	<html:javascript formName="indexUpdateBean" />
</body>
</html:html>