<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page
	import="sysconfig.model.*,java.util.*, org.apache.struts.util.*"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ page language="java" import="app.*"%><%@page
	import="org.apache.log4j.Logger"%>
<%
	Logger log = Logger.getLogger(this.getClass());
	log.info("inside " + this.getClass().getSimpleName() + " page");
%>
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");

	LogonForm form = null;
	if (request.isRequestedSessionIdValid()) {
		form = (LogonForm) session.getAttribute("user");
	}
	if (form == null || (!request.isRequestedSessionIdValid())) {
		response.sendRedirect("../userlogintemp.jsp");
	}
%>

<html:html>
<html:errors />
<head>

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

<script language="javascript" src="./codethatcalendarstd.js"></script>
<script language="javascript" src="box_ex.js"></script>
<script language="javascript">
	var c2 = new CodeThatCalendar(caldef2);
</script>
</head>

<body onload="onLoad();" class="b2">
	<jsp:useBean id="indComplianceMasterForm" scope="session"
		class="harrier.income.com.masters.IndComplianceMasterForm" />


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
			<td align="left" valign="top"><html:form action="/compliance"
					onsubmit=" return validateIndComplianceMasterForm(this);"
					focus="d1">

					<%
						if (request.isRequestedSessionIdValid()) {
					%>
					<jsp:setProperty name="indComplianceMasterForm" property="user_id"
						value='<%=session.getAttribute("userid").toString()%>' />
					<%
						}
					%>

					<%
						String check = request.getParameter("from");

								log.info("Enter Check " + check);
								if (check != null && check.trim().length() > 0) {
									log.info("Check " + check);
									if (check.equals("menu")) {
										indComplianceMasterForm.reset();
										check = "action";
									}
								}
					%>

					<table width="98%" class="sample">
						<tr>
							<td width="96%" nowrap="nowrap">
								<table border="0" width="100%" cellspacing="0" cellpadding="0"
									height="30">
									<tr>
										<td nowrap="nowrap" class="subHeader"><b>&nbsp;<bean:message
													key="indCompliance.index_Compliance" /></b></td>
										<td nowrap="nowrap" class="subHeader"></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
								</table>
								<table border="0" width="100%" cellspacing="0" cellpadding="0"
									height="400">
									<tr>

										<td width="10%" nowrap="nowrap" id="validation1"><font
											face="Arial" size="2"> &nbsp;<bean:message
													key="indCompliance.Select_Action" />
										</font> <font size="1"><bean:message
													key="indCompliance.addModify" />&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</font></font></td>
										<td colspan="4"><html:select size="1" property="d1"
												onchange="check();">
												<html:option value="0">Not selected</html:option>
												<html:option value="1">Add new compliance</html:option>
												<html:option value="2">Modify compliance</html:option>
											</html:select></td>


									</tr>

									</tr>

									<tr id="man">

										<td width="10%" nowrap="nowrap"><font face="Arial">
												<font face="Arial" size="2">&nbsp;<bean:message
														key="indCompliance.select" /></font></td>
										<td colspan="4" nowrap="nowrap"><html:select size="1"
												property="name_list" onchange="getdata();">
												<html:optionsCollection property="nameListCollection"
													name="indComplianceMasterForm" />
											</html:select>
									</tr>

									<tr>
										<td width="17%" valign="top" height="44" id="validation3"
											nowrap="nowrap"><hr>
											<font size="2" face="Arial">&nbsp;<FONT FACE="Verdana"
												SIZE=-2 COLOR="#E30102">*</FONT>
											<bean:message key="indCompliance.name" /></font></td>
										<td valign="top" height="44" nowrap="nowrap"><hr> <html:text
												property="name1" size="30" /></td>
										<td width="17%" valign="top" height="44" nowrap="nowrap"><hr>
											<font size="2" face="Arial">&nbsp;<bean:message
													key="indCompliance.sname" /></font></td>
										<td valign="top" height="44" nowrap="nowrap"><hr>
											<html:text property="sh_name" size="15" /></td>
									</tr>

									<tr>


										<td width="17%" nowrap="nowrap"><font size="2"
											face="Arial">&nbsp;<bean:message
													key="indCompliance.des" /></font></td>
										<td><html:text property="desc" size="40" /></td>
									</tr>

									<tr>
										<td width="17%" nowrap="nowrap"><font size="2"
											face="Arial"><bean:message
													key="indCompliance.avg_trading_rank" /></font></td>

										<td nowrap="nowrap"><html:text
												property="avg_trading_rank" size="15" /><font size="1"
											face="Arial"><bean:message key="indCompliance.perDay" /></font>

										</td>

									</tr>
									<tr>

										<td width="20%" nowrap="nowrap"><font size="2"
											face="Arial"><bean:message
													key="indCompliance.volRange" /></font>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <font size="1"
											face="Arial"><bean:message key="indCompliance.from" /></font>
										</td>

										<td><html:text property="volumeRankFrom" size="10" />&nbsp;
											<font size="1" face="Arial"><bean:message
													key="indCompliance.to" /></font> <html:text
												property="volumeRankTo" size="10" /></td>
										<td width="17%" nowrap="nowrap"><font size="2"
											face="Arial"><bean:message
													key="indCompliance.volDuration" /></font></td>

										<td width="80" nowrap="nowrap" align="right"><font
											size="2" face="Arial"> <bean:message
													key="corporate.Fdate" /></td>
										<td width="80" align="right" nowrap="nowrap"><html:text
												property="fromvol" size="10" readonly="false" /></td>
										<td width="80" nowrap="nowrap"><html:button property=""
												value="..." onclick="c2.popup('fromvol');" /></td>

										<td width="50" nowrap="nowrap"><font size="2"
											face="Arial"> <bean:message key="corporate.Tdate" /></td>

										<td width="80" nowrap="nowrap"><html:text
												property="tovol" size="10" readonly="false" /></td>

										<td width="80" nowrap="nowrap"><html:button property=""
												value="..." onclick="c2.popup('tovol');" /></td>

									</tr>
									<tr>
										<td width="17%"><font size="2" face="Arial">&nbsp;<FONT
												FACE="Verdana" SIZE=-2 COLOR="#E30102">*</FONT>
											<bean:message key="stockmaster.stockType" /></font></td>
										<td><html:select property="stockType" size="1">
												<html:optionsCollection name="indComplianceMasterForm"
													property="stockTypeCollection" />
											</html:select></td>
										<td width="17%" nowrap="nowrap"><font size="2"
											face="Arial"><FONT FACE="Verdana" SIZE=-2
												COLOR="#E30102">*</FONT>
											<bean:message key="indCompliance.stockExc" /></font></td>

										<td colspan="4" nowrap="nowrap"><html:select
												property="stockExange" size="1">
												<html:optionsCollection name="indComplianceMasterForm"
													property="stockExCollection" />
											</html:select></td>
									</tr>
									<tr>

										<td width="17%" nowrap="nowrap"><font size="2"
											face="Arial"><bean:message
													key="indCompliance.avg_trading_value_rank" /></font></td>

										<td colspan="4"><html:text
												property="avg_trading_value_rank" size="15" /><font
											size="1" face="Arial"><bean:message
													key="indCompliance.perDay" /></font></td>

									</tr>

									<tr>
										<!-- <td width="17%" nowrap="nowrap"><font size="2" face="Arial">&nbsp;<bean:message key="sysConfigForm.currencyId"/></font></td>
			<td >
				<html:select property="currency" size="1" >
					<html:optionsCollection name="indComplianceMasterForm" property="currencyIdCollection" />
            	</html:select>
			</td> -->


										<td width="17%" nowrap="nowrap"><font size="2"
											face="Arial"> <bean:message
													key="indCompliance.valRange" /></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp; <font size="1" face="Arial"> <bean:message
													key="indCompliance.from" /></font></td>
										<td><html:text property="valueRankFrom" size="10" />&nbsp;
											<font size="1" face="Arial"><bean:message
													key="indCompliance.to" /></font> <html:text property="valueRankTo"
												size="10" /></td>
										<td width="17%" nowrap="nowrap"><font size="2"
											face="Arial"><bean:message
													key="indCompliance.valDuration" /></font></td>

										<td width="80" nowrap="nowrap" align="right"><font
											size="2" face="Arial"> <bean:message
													key="corporate.Fdate" /></td>
										<td width="80" align="right" nowrap="nowrap"><html:text
												property="fromval" size="10" readonly="false" /></td>
										<td width="80" nowrap="nowrap"><html:button property=""
												value="..." onclick="c2.popup('fromval');" /></td>

										<td width="50" nowrap="nowrap"><font size="2"
											face="Arial"> <bean:message key="corporate.Tdate" /></td>

										<td width="80" nowrap="nowrap"><html:text
												property="toval" size="10" readonly="false" /></td>

										<td width="80" nowrap="nowrap"><html:button property=""
												value="..." onclick="c2.popup('toval');" /></td>


									</tr>




									<tr>

										<td width="17%"><font size="2" face="Arial">&nbsp;<bean:message
													key="indCompliance.rType" /></font></td>
										<td><html:select size="1" property="rankingType">
												<html:option value="t">Top</html:option>
												<html:option value="b">Bottom</html:option>

											</html:select></td>

										<td width="17%" nowrap="nowrap"><font size="2"
											face="Arial"><bean:message
													key="indCompliance.listed_history" /></font></td>

										<td colspan="4" nowrap="nowrap"><html:text
												property="listed_history" size="15" /><font size="1"
											face="Arial"><bean:message key="indCompliance.months" /></font>

										</td>

									</tr>
									<tr>
										<td width="17%"><font size="2" face="Arial">&nbsp;<bean:message
													key="indCompliance.mcap_rank" /></font></td>
										<td><html:text property="mcap_rank" size="15" /><font
											size="1" face="Arial"><bean:message
													key="indCompliance.perDay" /></font></td>

									</tr>


									<tr>
										<td width="17%" nowrap="nowrap"><font size="2"
											face="Arial">&nbsp;<bean:message
													key="indCompliance.mcapRange" /></font>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <font size="1" face="Arial"><bean:message
													key="indCompliance.from" /></font></td>
										<td><html:text property="mcapRankFrom" size="10" />&nbsp;
											<font size="1" face="Arial"><bean:message
													key="indCompliance.to" /></font> <html:text property="mcapRankTo"
												size="10" /></td>

										<td width="17%" nowrap="nowrap"><font size="2"
											face="Arial">&nbsp;<bean:message
													key="indCompliance.mcapDuration" /></font></td>
										<td width="80" nowrap="nowrap" align="right"><font
											size="2" face="Arial"> <bean:message
													key="corporate.Fdate" /></td>
										<td width="80" align="right" nowrap="nowrap"><html:text
												property="frommcap" size="10" readonly="false" /></td>
										<td width="80" nowrap="nowrap"><html:button property=""
												value="..." onclick="c2.popup('frommcap');" /></td>

										<td width="50" nowrap="nowrap"><font size="2"
											face="Arial"> <bean:message key="corporate.Tdate" /></td>

										<td width="80" nowrap="nowrap"><html:text
												property="tomcap" size="10" readonly="false" /></td>

										<td width="80" nowrap="nowrap"><html:button property=""
												value="..." onclick="c2.popup('tomcap');" /></td>


									</tr>

									<tr>



										<!--	<td width="17%" nowrap="nowrap"><font size="2" face="Arial"><bean:message key="sysConfigForm.countryId"/></font>
				</td>
			<td colspan="4" nowrap="nowrap">
			<html:select property="country" size="1" >
					<html:optionsCollection name="indComplianceMasterForm" property="countryIdCollection" />
            </html:select>
				
			</td> -->
									</tr>

									<tr>
										<td width="17%" nowrap="nowrap"><font size="2"
											face="Arial">&nbsp;<bean:message
													key="indCompliance.weightage" /></font></td>
										<td><html:text property="weightage" size="15" /><font
											size="1" face="Arial">(%)</font></td>

										<td width="17%" nowrap="nowrap"><font size="2"
											face="Arial">&nbsp;<bean:message
													key="indCompliance.maxWeightage" /></font></td>
										<td><html:text property="maxWeightage" size="15" /><font
											size="1" face="Arial">(%)</font></td>


									</tr>
									<tr>

										<td width="19%" nowrap="nowrap"><font size="2"
											face="Arial"><bean:message
													key="indCompliance.floating_stock" /></font></td>

										<td colspan="4" nowrap="nowrap"><html:text
												property="floating_stock" size="10"
												onblur="validateNumber(this.form,this);" /></td>


									</tr>



								</table>

								<table border="0" width="100%" cellspacing="0" cellpadding="0"
									height="15">
									<td></td>
								</table>


								<table border="0" width="100%" cellspacing="0" cellpadding="0"
									height="50">
									<tr>
										<td width="17%" height="182" valign="top" nowrap="nowrap">
											<FONT FACE="Verdana" SIZE=-2 COLOR="#E30102">*</FONT><font
											face="Arial" size="2" align="right"><bean:message
													key="indCompliance.ind" /> </font>
										</td>
										<td width="30%" height="182" colspan="2" align="left"
											nowrap="nowrap"><html:select size="11" property="list1"
												styleId="ac_selections" multiple="true" style="width:310px;">
												<html:optionsCollection property="list1Collection"
													name="indComplianceMasterForm" />
											</html:select></td>

										<td width="6%" height="182" nowrap="nowrap">
											<p style="margin-top: 0; margin-bottom: 0">
												<b><font size="2" face="Arial">&nbsp;&nbsp; <bean:message
															key="roleActivities.Add" /></font></b>
											</p>
											<p style="margin-top: 0; margin-bottom: 0">
												&nbsp;&nbsp; <input type="button" value="--&gt;"
													name="add_rt" onclick="AddToRHS();">
											</p>
											<p style="margin-top: 0; margin-bottom: 0">
												&nbsp;&nbsp; <input type="button" value="&lt;--"
													name="remove_rt" onclick="RemoveFromRHS();">
											</p>
											<p style="margin-top: 0; margin-bottom: 0">
												<b><font size="2" face="Arial">&nbsp;&nbsp;<bean:message
															key="indCompliance.delete" /></font></b>
										</td>
										<td width="43%" height="182"><html:select size="11"
												multiple="true" property="d3" styleId="Rem"
												style="width:300px;">
												<html:optionsCollection property="d3Collection"
													name="indComplianceMasterForm" />
											</html:select></td>
									</tr>
									<tr>
										<td width="17%" height="41" nowrap="nowrap"></td>
										<td height="41" width="5%" nowrap="nowrap"><html:submit
												property="B1" onclick="sendNodes();">
												<bean:message key="indexUpdate.save" />&nbsp;
			</html:submit></td>
										<td height="41" nowrap="nowrap"><html:button
												property="B2" onclick="clearall();">
												<bean:message key="indexUpdate.reset" />
											</html:button></td>
										<td colspan="2" height="41" nowrap="nowrap">&nbsp;</td>
										<html:hidden property="methodToCall"></html:hidden>
										<html:hidden property="sendNode"></html:hidden>
										<html:hidden property="sendNodeHTML"></html:hidden>

									</tr>
								</table>
								<p>&nbsp;</p>

							</td>
						</tr>
					</table>
				</html:form></td>
		</tr>
	</table>

	<html:javascript formName="indComplianceMasterForm" />


	<script language="JavaScript">
		if (document.forms[0].mcap_rank.value == 0)
			document.forms[0].mcap_rank.value = "";
		if (document.forms[0].mcapRankFrom.value == 0)
			document.forms[0].mcapRankFrom.value = "";
		if (document.forms[0].mcapRankTo.value == 0)
			document.forms[0].mcapRankTo.value = "";
		if (document.forms[0].weightage.value == 0)
			document.forms[0].weightage.value = "";
		if (document.forms[0].maxWeightage.value == 0)
			document.forms[0].maxWeightage.value = "";
		if (document.forms[0].avg_trading_rank.value == 0)
			document.forms[0].avg_trading_rank.value = "";
		if (document.forms[0].volumeRankFrom.value == 0)
			document.forms[0].volumeRankFrom.value = "";
		if (document.forms[0].volumeRankTo.value == 0)
			document.forms[0].volumeRankTo.value = "";
		if (document.forms[0].avg_trading_value_rank.value == 0)
			document.forms[0].avg_trading_value_rank.value = "";
		if (document.forms[0].valueRankFrom.value == 0)
			document.forms[0].valueRankFrom.value = "";
		if (document.forms[0].valueRankTo.value == 0)
			document.forms[0].valueRankTo.value = "";
		if (document.forms[0].listed_history.value == 0)
			document.forms[0].listed_history.value = "";
		if (document.forms[0].floating_stock.value == 0)
			document.forms[0].floating_stock.value = "";

		function rhsSel() {
			var rhsSelect = document.getElementById("Rem");
			//alert(rhsSelect.options[0].selected);
			if (rhsSelect.options[0] != null)
				rhsSelect.options[0].selected = true;
		}
		function sendNodes() {

			document.forms[0].methodToCall.value = "Do";
			var nodesToSend = new Array();
			var nodesToSendHTML = new Array();
			var rhsSelect1 = document.getElementById("Rem");
			var numToRemove1 = 0;
			var numToRemove2 = 0;

			for (var index2 = 0; index2 < rhsSelect1.options.length; index2++) {
				nodesToSend[numToRemove1++] = rhsSelect1.options[index2].value;
				nodesToSendHTML[numToRemove2++] = rhsSelect1.options[index2].innerHTML;

			}

			document.forms[0].sendNode.value = nodesToSend;
			document.forms[0].sendNodeHTML.value = nodesToSendHTML;
			rhsSel();
		}
		function onLoad() {
			var man1 = document.getElementById("man");
			if (document.forms[0].d1.value == 1
					|| document.forms[0].d1.value == 0) {
				man1.style.display = "none";
				document.forms[0].name_list.value = "1";
				// clearAll1();
			} else {
				man1.style.display = "block";

			}
			rhsSel();
		}
		function check() {

			var man1 = document.getElementById("man");
			if (document.forms[0].d1.value == 1
					|| document.forms[0].d1.value == 0) {
				man1.style.display = "none";
				document.forms[0].name_list.value = "1";
				clearAll1();
			} else {
				man1.style.display = "block";
				document.forms[0].name_list.value = "0";

			}
		}

		function getdata() {
			document.forms[0].methodToCall.value = "notDo";
			if (document.forms[0].name_list.value == "0") {
				document.forms[0].d1.value = 0;
				document.forms[0].d1.focus();
				clearAll1();
				return false;
			}
			document.forms[0].submit();
		}
		function clearall() {
			document.forms[0].d1.value = 0;
			document.forms[0].name_list.value = 0;

			clearAll1();
		}
		function clearAll1() {

			var nodesToRemove = new Array();
			var numToRemove = 0;
			document.forms[0].name1.value = "";
			document.forms[0].sh_name.value = "";
			document.forms[0].desc.value = "";
			document.forms[0].stockType.value = 0;
			//document.forms[0].currency.value = 0;
			document.forms[0].rankingType.value = "t";
			document.forms[0].mcap_rank.value = "";
			document.forms[0].mcapRankFrom.value = "";
			document.forms[0].mcapRankTo.value = "";

			document.forms[0].weightage.value = "";
			document.forms[0].maxWeightage.value = "";
			document.forms[0].avg_trading_rank.value = "";
			document.forms[0].volumeRankFrom.value = "";
			document.forms[0].volumeRankTo.value = "";

			document.forms[0].avg_trading_value_rank.value = "";
			document.forms[0].valueRankFrom.value = "";
			document.forms[0].valueRankTo.value = "";

			document.forms[0].listed_history.value = "";
			document.forms[0].floating_stock.value = "";
			document.forms[0].stockExange.value = "";
			//document.forms[0].country.value="";

			var rhsSelect = document.getElementById("Rem");
			for (var index = 0; index < rhsSelect.options.length; index++) {
				// Save the node for removal from the right-hand side select, but outside this loop.

				nodesToRemove[numToRemove++] = rhsSelect.options[index];
			}
			// Remove the options from the right-hand side select.
			for (var index = 0; index < nodesToRemove.length; index++) {
				rhsSelect.removeChild(nodesToRemove[index]);
			}
			var man1 = document.getElementById("man");
			man1.style.display = "none";

		}
		function AddToRHS() {
			var rhsSelect = document.getElementById("Rem");
			var lhsSelect = document.getElementById("ac_selections");
			var nodesToAdd = new Array();
			var nodesToRemove = new Array();
			var numToAdd = 0;
			var numToRemove = 0;

			for (var index = 0; index < lhsSelect.options.length; index++) {
				if (lhsSelect.options[index].selected == false) {
					continue;
				}
				// Save the node for removal from the right-hand side select, but outside this loop.
				nodesToAdd[numToAdd++] = lhsSelect.options[index];
			}
			for (var index = 0; index < nodesToAdd.length; index++) {
				var nToAdd;
				var flag = 0;
				var fields = new Array();
				var i = 0;
				if (nodesToAdd != null)
					nToAdd = nodesToAdd[index].value; //51
				else
					break;

				for (var index1 = 0; index1 < rhsSelect.options.length; index1++) {
					var nToRem = rhsSelect.options[index1].value;
					if (nToAdd == nToRem) {
						alert(rhsSelect.options[index1].innerHTML
								+ " is already Added");
						flag = 1;
						index1 = rhsSelect.options.length;
						break;
					}
				}
				if (flag == 0) {

					var optionElement = document.createElement("option");
					optionElement.value = nodesToAdd[index].value;
					optionElement.innerHTML = nodesToAdd[index].innerHTML;
					rhsSelect.appendChild(optionElement);

				}
			}
			rhsSel();

		}
		function RemoveFromRHS() {
			var StoreSelections_Hash = new Array();
			var StoreSelections_Array = new Array();
			var rhsSelect = document.getElementById("Rem");
			var lhsSelect = document.getElementById("ac_selections");
			var nodesToRemove = new Array();
			var numToRemove = 0;
			for (var index = 0; index < rhsSelect.options.length; index++) {
				// For each selected element.
				//
				if (rhsSelect.options[index].selected == false) {
					continue;
				}

				// Save the node for removal from the right-hand side select, but outside this loop.

				nodesToRemove[numToRemove++] = rhsSelect.options[index];

				// Remove the value in the hash.
				var elementValue = rhsSelect.options[index].value;

			}

			// Remove the options from the right-hand side select.
			for (var index = 0; index < nodesToRemove.length; index++) {
				rhsSelect.removeChild(nodesToRemove[index]);

			}
			rhsSel();
		}
		function validateNumber(form, fobj) {

			var pin = fobj.value;

			var s = "0123456789.";
			var i;

			for (i = 0; i < pin.length; i++) {
				var c = pin.charAt(i);

				if (s.indexOf(c) == -1) {
					alert(" Invalid Number");
					fobj.value = "";
					fobj.focus();
					break;

				}
			}

		}
	</script>
</body>
</html:html>
>
