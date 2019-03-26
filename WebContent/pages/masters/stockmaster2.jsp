<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page language="java" import="app.*,java.sql.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="harrier.income.com.masters.*,java.util.*,org.apache.struts.util.*"%>
<%@page import="org.apache.log4j.Logger"%>
<%
	Logger log = Logger.getLogger(this.getClass());
	log.info("inside *** " + this.getClass().getSimpleName() + " page");
%>
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");

	LogonForm form = null;
	if (request.isRequestedSessionIdValid()) {
		form = (LogonForm) session.getAttribute("user");
	//	String fr2 = request.getParameter("from");
		String locale = session.getAttribute("locale").toString();
		//	AcessControl asc=new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		asc.setLocale(locale);
	}
	if (form == null || (!request.isRequestedSessionIdValid())) {
		response.sendRedirect("../userlogintemp.jsp");
	}
%>
<html>
	<head>
<SCRIPT language=javascript>
function hideLeftCol(id)
{
if(this.document.getElementById(id).style.display=='none')
{
this.document.getElementById(id).style.display='inline';
document.getElementById("HideHandle").src = '../closeImage.gif';
// this.document.getElementById(id).style.width='10px';
Set_Cookie('showLeftCol','true',30,'/','','');
var show = Get_Cookie('showLeftCol');
//document['HideHandle'].src = 'images/hide.gif';
document.getElementById("HideHandle").src = '../open.gif';
}
else{
// this.document.getElementById(id).style.display='none';
this.document.getElementById(id).style.display='none';
document.getElementById("HideHandle").src = '../openImage.gif';
Set_Cookie('showLeftCol','false',30,'/','','');
var show = Get_Cookie('showLeftCol');
//document['HideHandle'].src = 'images/show.gif';
}
}
</script>


	</head>
	<html:base />
	<link rel="stylesheet" type="text/css" href="./StyleSheet.css"  title="Default" />  
	<jsp:useBean id="stockMasterForm" scope="session"
		class="harrier.income.com.masters.StockMasterForm" />
	<%
		if (request.isRequestedSessionIdValid()) {
	%>
	<jsp:setProperty name="stockMasterForm" property="role_ids"
		value='<%= session.getAttribute("role_id").toString()%>' />
	<%
		}
	%>

	<%=(stockMasterForm.getMessages(request)).toString()%>
	<table width="100%" height="100%">

		<tr>

			<td align="left" valign="top" bgcolor="#CCddee">
				<DIV id="leftCol" style="DISPLAY: none; width: 160; height: 100%;">
					<%@ include file="../tree3.jsp"%>
				</div>
				<IMG id="HideHandle" src="../openImage.gif"
					style="CURSOR: hand; position: absolute;"
					onclick='hideLeftCol("leftCol");' src="fold.gif" width="10"
					height="25">
			</td>
			<td align="left" valign="top">

				<html:form action="/stockMasterAction">
					<script language="javascript" src="./codethatcalendarstd.js"></script>
					<script language="javascript" src="box_ex.js"></script>
					<script language="javascript">
		var c2 = new CodeThatCalendar(caldef2);
	</script>
					<%
						boolean bodydisp = (stockMasterForm.bodycheck(request));
					%>



					<body>
						<!-- Changes By Manoj Adekar to Disable the components of StockMaster Form -->

						<logic:notEqual value="1" name="stockMasterForm"
							property="role_ids">
							<table border="1" width="100%" cellspacing="0" cellpadding="0">

								<tr>
									<td width="75%"></td>
								</tr>
								<tr>
									<td width="64%">
										<table border="0" width="100%" class="gridStyle"
											cellspacing="0" cellpadding="2">
											<tr>
												<td width="25%" class="subHeader" nowrap="nowrap">
													<b><bean:message key="stockmaster.stockDetail" />
													</b>
												</td>
												<td width="25%" class="subHeader">
													<b>&nbsp;</b>
												</td>
												<td width="25%" class="subHeader">
													<b>&nbsp;</b>
												</td>
												<td width="25%" class="subHeader">
													<b>&nbsp;</b>
												</td>
											</tr>
											</td>
											<%
												boolean erdisp = (stockMasterForm.getErrors(request,
																response));
														if (erdisp) {
											%>
											<html:errors />
											<%
												}
											%>


											<logic:notEmpty property="s_stockID" name="stockMasterForm">


												<tr>
													<td width="25%" class="tab" nowrap="nowrap">
														<b><bean:message key="stockmaster.stockID" />
														</b>
													</td>
													<td width="25%" class="tab">
														<html:text property="s_stockID" size="10"
															name="stockMasterForm" disabled="true" />
													</td>
													<td width="25%" class="tab">
														<b>&nbsp;</b>
													</td>
													<td width="25%" class="tab">
														<b>&nbsp;</b>
													</td>
												</tr>


											</logic:notEmpty>


											<tr>
												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.stockName" />
													<font color="red">*</font>
												</td>

												<td width="25%">
													<logic:present name="stockMasterForm">
														<logic:empty property="s_stockName" name="stockMasterForm">
															<html:text property="s_stockName" size="40" value=""
																 disabled="true" />
														</logic:empty>
														<logic:notEmpty property="s_stockName"
															name="stockMasterForm">
															<html:text property="s_stockName" size="40"
																name="stockMasterForm" disabled="true" />
														</logic:notEmpty>
													</logic:present>
												</td>

												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.listingDate" />
												</td>
												<td width="25%">
													<logic:present name="stockMasterForm">
														<logic:empty property="d_listingDate"
															name="stockMasterForm">
															<html:text readonly="true" property="d_listingDate"
																size="20" value="" disabled="true" />
														</logic:empty>
														<logic:notEmpty property="d_listingDate"
															name="stockMasterForm">
															<html:text readonly="true" property="d_listingDate"
																size="20" name="stockMasterForm" disabled="true" />
														</logic:notEmpty>
													</logic:present>
													<input onclick="c2.popup('d_listingDate');" type="button"
														value="..." disabled="true" />
												</td>

											</tr>

											<tr>


												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.stockType" />
													<font color="red">*</font>
												</td>
												<td width="20%">
													<html:select name="stockMasterForm" property="s_stockType"
														size="1" disabled="true">
														<html:optionsCollection name="stockMasterForm"
															property="stockTypeCollection" />
													</html:select>
													<font color="blue"><bean:message
															key="stockmaster.new" />
													</font>
												</td>




												<td width="20%" align="left" class="tab">
													<bean:message key="stockmaster.iwf" />
													<font color="red">*</font>
												</td>
												<td width="25%">
													<logic:present name="stockMasterForm">
														<logic:empty property="d_iwf" name="stockMasterForm">
															<html:text property="d_iwf" size="20" maxlength="6"
																value="" disabled="true"  />
														</logic:empty>
														<logic:notEmpty property="d_iwf" name="stockMasterForm">
															<html:text property="d_iwf" size="20" maxlength="6"
																name="stockMasterForm" disabled="true" />
														</logic:notEmpty>
													</logic:present>
												</td>
											</tr>

											<tr>
												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.company" />
													<font color="red">*</font>
												</td>
												<td width="20%" nowrap="nowrap">
													<html:select property="s_companyName" size="1"
														onfocus="populate(event);" onkeydown="setSelection(event)"
														onkeypress="javascript:return false" disabled="true">
														<html:optionsCollection name="stockMasterForm"
															property="companyListCollection" />
													</html:select>
													<font color="blue"><bean:message
															key="stockmaster.new" />
													</font> &nbsp;&nbsp;
												</td>

												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.issuedShare" />
													<font color="red">*</font>
												</td>
												<td width="25%">
													<logic:present name="stockMasterForm">
														<logic:empty property="s_stockID" name="stockMasterForm">
															<logic:empty property="f_issuedShares"
																name="stockMasterForm">
																<html:text property="f_issuedShares" size="20" value=""
																	disabled="true" />
															</logic:empty>
															<logic:notEmpty property="f_issuedShares"
																name="stockMasterForm">
																<html:text property="f_issuedShares" size="20"
																	name="stockMasterForm" disabled="true" />
															</logic:notEmpty>
														</logic:empty>
														<logic:notEmpty property="s_stockID"
															name="stockMasterForm">
															<logic:empty property="f_issuedShares"
																name="stockMasterForm">
																<html:text readonly="false" property="f_issuedShares"
																	size="20" value="" disabled="true" />
															</logic:empty>
															<logic:notEmpty property="f_issuedShares"
																name="stockMasterForm">
																<html:text readonly="false" property="f_issuedShares"
																	size="20" name="stockMasterForm" disabled="true" />
															</logic:notEmpty>
														</logic:notEmpty>
													</logic:present>
												</td>
											</tr>
											<tr>

												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.stockExchange" />
													<font color="red">*</font>
												</td>

												<td width="25%">
													<html:select property="s_stockExchange" size="1"
														onfocus="populate(event);" onkeydown="setSelection(event)"
														onkeypress="javascript:return false" disabled="true">
														<html:optionsCollection name="stockMasterForm"
															property="stockExcListCollection" />
													</html:select>
													<input type="hidden" name="s_stockExchange_null"
														value="<%=stockMasterForm.getS_stockExchange()%>" />
													<html:hidden property="b_accpt" />


													<font color="blue"><bean:message
															key="stockmaster.new" />
													</font>
												</td>


												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.country" />
													<font color="red">*</font>
												</td>
												<td width="25%">
													<html:select property="s_countryName" size="1"
														onfocus="populate(event);" onkeydown="setSelection(event)"
														onkeypress="javascript:return false" disabled="true">
														<html:optionsCollection name="stockMasterForm"
															property="countryListCollection" />
													</html:select>
												</td>

											</tr>


											<tr>
												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.faceValue" />
													<font color="red">*</font>
												</td>
												<td width="25%">
													<logic:present name="stockMasterForm">
														<logic:empty property="s_stockID" name="stockMasterForm">
															<logic:empty property="f_faceValue"
																name="stockMasterForm">
																<html:text property="f_faceValue" size="20" value=""
																	disabled="true" />
															</logic:empty>
															<logic:notEmpty property="f_faceValue"
																name="stockMasterForm">
																<html:text property="f_faceValue" size="20"
																	name="stockMasterForm" disabled="true" />
															</logic:notEmpty>
														</logic:empty>
														<logic:notEmpty property="s_stockID"
															name="stockMasterForm">
															<logic:empty property="f_faceValue"
																name="stockMasterForm">
																<html:text readonly="true" property="f_faceValue"
																	size="20" value="" disabled="true" />
															</logic:empty>
															<logic:notEmpty property="f_faceValue"
																name="stockMasterForm">
																<html:text readonly="true" property="f_faceValue"
																	size="20" name="stockMasterForm" disabled="true" />
															</logic:notEmpty>
														</logic:notEmpty>
													</logic:present>
												</td>

												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.ratingCode" />
													<font color="red">*</font>
												</td>
												<td width="25%">
													<html:select property="s_ratingCode" size="1"
														disabled="true">
														<html:optionsCollection name="stockMasterForm"
															property="ratingCodeListCollection" />
													</html:select>
												</td>
											</tr>

											<tr>
												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.paidValue" />
													<font color="red">*</font>
												</td>
												<td width="25%">
													<logic:empty property="d_paidValue" name="stockMasterForm">
														<html:text property="d_paidValue" size="20" value=""
															disabled="true" />
													</logic:empty>
													<logic:notEmpty property="d_paidValue"
														name="stockMasterForm">
														<html:text property="d_paidValue" size="20"
															name="stockMasterForm" disabled="true" />
													</logic:notEmpty>
												</td>
												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.currency" />
													<font color="red">*</font>
												</td>
												<td width="25%">
													<html:select property="s_stockCurrency" size="1"
														onfocus="populate(event);" onkeydown="setSelection(event)"
														onkeypress="javascript:return false" disabled="true">
														<html:optionsCollection name="stockMasterForm"
															property="currencyListCollection" />
													</html:select>
												</td>

											</tr>

											<tr>
												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.alertPercentage" />
													<font color="red">*</font>
												</td>
												<td width="25%">
													<logic:empty property="f_alertPercent"
														name="stockMasterForm">
														<html:text property="f_alertPercent" size="20" value=""
															disabled="true" />&nbsp;%
	            			</logic:empty>
													<logic:notEmpty property="f_alertPercent"
														name="stockMasterForm">
														<html:text property="f_alertPercent" size="20"
															name="stockMasterForm" disabled="true" />&nbsp;%
	            			</logic:notEmpty>
												</td>

												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.rejection" />
													<font color="red">*</font>
												</td>

												<td width="25%">
													<logic:empty property="f_rejectionPercent"
														name="stockMasterForm">
														<html:text property="f_rejectionPercent" size="20"
															value="" disabled="true" />&nbsp;%
	            			</logic:empty>
													<logic:notEmpty property="f_rejectionPercent"
														name="stockMasterForm">
														<html:text property="f_rejectionPercent" size="20"
															name="stockMasterForm" disabled="true" />&nbsp;%
	            			</logic:notEmpty>
												</td>
											</tr>


											<tr>
												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.withholdingTax" />
													<br>
													<bean:message key="stockmaster.applicable" />
												</td>
												<td width="25%">
													<html:select property="b_withHoldingTaxApplicable" size="1"
														disabled="true">
														<html:optionsCollection name="stockMasterForm"
															property="withHoldingTaxApplicableCollection" />
													</html:select>
												</td>

												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.withholdingTax" />
												</td>
												<td width="25%">
													<logic:empty property="f_withholdingTaxPercent"
														name="stockMasterForm">
														<html:text property="f_withholdingTaxPercent" size="20"
															value="" disabled="true" />
													</logic:empty>
													<logic:notEmpty property="f_withholdingTaxPercent"
														name="stockMasterForm">
														<html:text property="f_withholdingTaxPercent" size="20"
															name="stockMasterForm" disabled="true" />&nbsp;%
	            	</logic:notEmpty>
												</td>
											</tr>

											<tr>
												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.mktLot" />
													<font color="red">*</font>
												</td>
												<td width="25%">
													<logic:empty property="s_marketLot" name="stockMasterForm">
														<html:text property="s_marketLot" size="20" value=""
															disabled="true" />
													</logic:empty>
													<logic:notEmpty property="s_marketLot"
														name="stockMasterForm">
														<html:text property="s_marketLot" size="20"
															name="stockMasterForm" disabled="true" />
													</logic:notEmpty>
												</td>

												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.ispriceforLot" />
												</td>
												<td width="25%">
													<html:select property="b_isPriceForLot" size="1"
														 disabled="true">
														<html:optionsCollection name="stockMasterForm"
															property="is_price_for_lotCollection" />
													</html:select>
												</td>
											</tr>
											<tr>
												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.depository" />
													<br>
													<bean:message key="stockmaster.receipt" />
												</td>
												<td width="25%">
													<html:select property="adr_gdr_id" size="1" disabled="true">
														<html:optionsCollection name="stockMasterForm"
															property="depositoryReceiptListCollection" />
													</html:select>
												</td>
												<td width="25%" class="tab">
													<bean:message key="stockmaster.adrRatio" />
												</td>
												<td width="25%">
													<logic:empty property="s_adrRatio" name="stockMasterForm">
														<html:text property="s_adrRatio" size="5" value=""
															disabled="true" />
													</logic:empty>
													<logic:notEmpty property="s_adrRatio"
														name="stockMasterForm">
														<html:text property="s_adrRatio" size="5"
															name="stockMasterForm" disabled="true" />
													</logic:notEmpty>
													:
													<logic:empty property="s_adrRatio1" name="stockMasterForm">
														<html:text property="s_adrRatio1" size="5" value="" />
													</logic:empty>
													<logic:notEmpty property="s_adrRatio1"
														name="stockMasterForm">
														<html:text property="s_adrRatio1" size="5"
															name="stockMasterForm" disabled="true" />
													</logic:notEmpty>
												</td>
											</tr>
											<tr>
												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.global100" />
												</td>
												<td width="25%">
													<%
														// log.info("b_global100");
													%>
													<html:select property="b_global100" size="1"
														disabled="true">
														<html:optionsCollection name="stockMasterForm"
															property="global100Collection" />
													</html:select>
												</td>
											</tr>

										</table>
										<table border="0" width="101%" class="gridStyle"
											cellspacing="0" cellpadding="4">
											<tr>
												<td width="49%" valign="top">
													<table border="1" width="69%" class="gridStyle"
														cellspacing="0" cellpadding="0">
														<tr>
															<td width="100%" class="tab">
																<html:radio property="s_growthValueType" value="g"
																	disabled="true" />
																<bean:message key="stockmaster.growthStock" />
																<html:radio property="s_growthValueType" value="v"
																	disabled="true" />
																<bean:message key="stockmaster.valueStock" />
															</td>
														</tr>
													</table>
												</td>

												<td width="97%" valign="top" class="tab">
													<p align="left">
														<b><bean:message key="stockmaster.active" />
														</b>
														<html:checkbox property="b_isActive" disabled="true" />
													</p>
												</td>

											</tr>



											<tr>
												<td width="146%" valign="top" colspan="2">
													<table border="0" width="85%" class="gridStyle"
														cellspacing="0" cellpadding="0" bordercolordark="#808080"
														style="border-collapse: collapse" bordercolor="#111111">
														<tr>
															<td width="100%" class="tab">
																<b>&nbsp;<bean:message key="stockmaster.codes" />
																</b>
																<table border="0" width="100%" cellspacing="0"
																	cellpadding="0" height="5%">
																	<tr>
																		<td width="50%" align="left" height="9">
																			<div align="left">
																				<table border="0" cellpadding="0" class="gridStyle"
																					cellspacing="0" style="border-collapse: collapse"
																					bordercolor="#111111" width="100%" id="AutoNumber1">
																					<tr>
																						<td width="8%" class="tab">
																							&nbsp;&nbsp;
																							<bean:message key="stockmaster.sedol" />
																						</td>
																						<td width="8%">
																							<logic:empty property="b_sdl"
																								name="stockMasterForm">
																								<html:text property="b_sdl" size="12" value=""
																									disabled="true" />
																							</logic:empty>
																							<logic:notEmpty property="b_sdl"
																								name="stockMasterForm">
																								<html:text property="b_sdl" size="12"
																									name="stockMasterForm" disabled="true" />
																							</logic:notEmpty>
																						</td>
																						<td width="5%" class="tab">
																							&nbsp;&nbsp;
																							<bean:message key="stockmaster.isin" />
																							<td width="10%">
																								<logic:empty property="b_isn"
																									name="stockMasterForm">
																									<html:text property="b_isn" size="12" value=""
																										disabled="true" />
																								</logic:empty>
																								<logic:notEmpty property="b_isn"
																									name="stockMasterForm">
																									<html:text property="b_isn" size="12"
																										name="stockMasterForm" disabled="true" />
																								</logic:notEmpty>
																							</td>
																							<td width="8%" class="tab">
																								&nbsp;&nbsp;
																								<bean:message key="stockmaster.ric" />
																								<td width="10%">
																									<logic:empty property="b_ric"
																										name="stockMasterForm">
																										<html:text property="b_ric" size="12" value=""
																											disabled="true" />
																									</logic:empty>
																									<logic:notEmpty property="b_ric"
																										name="stockMasterForm">
																										<html:text property="b_ric" size="12"
																											name="stockMasterForm" disabled="true" />
																									</logic:notEmpty>
																								</td>
																								<td width="8%" class="tab">
																									&nbsp;&nbsp;
																									<bean:message key="stockmaster.crisil" />
																									<td width="10%">
																										<logic:empty property="b_crisil"
																											name="stockMasterForm">
																											<html:text property="b_crisil" size="12"
																												value="" disabled="true" />
																										</logic:empty>
																										<logic:notEmpty property="b_crisil"
																											name="stockMasterForm">
																											<html:text property="b_crisil" size="12"
																												name="stockMasterForm" disabled="true" />
																										</logic:notEmpty>
																									</td>
																					</tr>
																					<tr>
																						<td width="8%" class="tab">
																							&nbsp;&nbsp;
																							<bean:message key="stockmaster.cusip" />
																							<td width="10%">
																								<logic:empty property="b_csp"
																									name="stockMasterForm">
																									<html:text property="b_csp" size="12" value=""
																										disabled="true" />
																								</logic:empty>
																								<logic:notEmpty property="b_csp"
																									name="stockMasterForm">
																									<html:text property="b_csp" size="12"
																										name="stockMasterForm" disabled="true" />
																								</logic:notEmpty>
																							</td>
																							<td width="15%" class="tab">
																								&nbsp;&nbsp;
																								<bean:message key="stockmaster.exchangeCode" />
																								<td width="10%">
																									<logic:empty property="b_exc_code"
																										name="stockMasterForm">
																										<html:text property="b_exc_code" size="12"
																											value="" disabled="true" />
																									</logic:empty>
																									<logic:notEmpty property="b_exc_code"
																										name="stockMasterForm">
																										<html:text property="b_exc_code" size="12"
																											name="stockMasterForm" disabled="true" />
																									</logic:notEmpty>
																								</td>
																								<td width="8%" class="tab">
																									&nbsp;&nbsp;
																									<bean:message key="stockmaster.ticker" />
																									<td width="10%">
																										<logic:empty property="b_tkr"
																											name="stockMasterForm">
																											<html:text property="b_tkr" size="12"
																												value="" disabled="true" />
																										</logic:empty>
																										<logic:notEmpty property="b_tkr"
																											name="stockMasterForm">
																											<html:text property="b_tkr" size="12"
																												name="stockMasterForm" disabled="true" />
																										</logic:notEmpty>
																									</td>
																									<td></td>
																					</tr>
																				</table>
																			</div>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</td>
											</tr>

											<tr>
												<td vAlign="top" width="97%" colSpan="2" class="subHeader">
													<p align="center">
														&nbsp;
													</p>
												</td>
												<td vAlign="top" width="3%">
													&nbsp;
												</td>
											</tr>


										</table>
										<tr>
											<td width="100%" valign="top" colspan="3">
												<p align="center">
													<%
														String stockid = stockMasterForm.getS_stockID();
																if (stockid != null) {
																	String url = "../CorporateDiary.jsp?ref_flag=2&s_stock="
																			+ stockid;
													%>

													<!--<input type="hidden" name="corporateAction"> 
            <html:submit value="View Corporate Actions" property="b1" /> -->
													<font color="blue"><bean:message
															key="stockmaster.viewCorporateAction" />
													</font>&nbsp;&nbsp;&nbsp;
													<input type="hidden" name="operation">
													<html:submit property="b1" onclick="return Utestcheck()"
														disabled="true">
														<bean:message key="defineIndex30" />
													</html:submit>
													&nbsp;&nbsp;&nbsp;&nbsp;
													<%
														} else {
													%>
													<html:submit property="b1" onclick="return Itestcheck()"
														disabled="true">
														<bean:message key="defineIndex30" />
													</html:submit>
													&nbsp;&nbsp;&nbsp;&nbsp;
													<%
														}
													%>
													<html:submit property="b1" onclick="return test1()"
														disabled="true">
														<bean:message key="stockmaster.new" />
													</html:submit>
													&nbsp;&nbsp; &nbsp;
													<html:reset property="B3" disabled="true">
														<bean:message key="indexUpdate.reset" />
													</html:reset>
													&nbsp;&nbsp;&nbsp;
													<!--<html:submit value="Import From file..." property="b1"/>-->
													<html:button property="b1" value="New Issues"
														onclick="return stktest()" disabled="true" />
													<font color="blue"><bean:message
															key="stockmaster.importFromFile" />
													</font>
											</td>
											<%=(stockMasterForm.getStockBelongsTo(request))
							.toString()%>
										</tr>



									</td>
								</tr>
								</tbody>
							</table>


						</logic:notEqual>

						<logic:equal value="1" name="stockMasterForm" property="role_ids">
							<table border="1" width="100%" cellspacing="0" cellpadding="0">

								<tr>
									<td width="75%"></td>
								</tr>
								<tr>
									<td width="64%">
										<table border="0" width="100%" class="gridStyle"
											cellspacing="0" cellpadding="2">
											<tr>
												<td width="25%" class="subHeader" nowrap="nowrap">
													<b><bean:message key="stockmaster.stockDetail" />
													</b>
												</td>
												<td width="25%" class="subHeader">
													<b>&nbsp;</b>
												</td>
												<td width="25%" class="subHeader">
													<b>&nbsp;</b>
												</td>
												<td width="25%" class="subHeader">
													<b>&nbsp;</b>
												</td>
											</tr>
											<%
												boolean erdisp = (stockMasterForm.getErrors(request,
																response));
														if (erdisp) {
											%>
											<html:errors />
											<%
												}
											%>


											<logic:notEmpty property="s_stockID" name="stockMasterForm">
												<tr>
													<td width="25%" class="tab" nowrap="nowrap">
														<b><bean:message key="stockmaster.stockID" />
														</b>
													</td>
													<td width="25%" class="tab">
														<html:text property="s_stockID" size="10"
															name="stockMasterForm" />
													</td>
													<td width="25%" class="tab">
														<b>&nbsp;</b>
													</td>
													<td width="25%" class="tab">
														<b>&nbsp;</b>
													</td>
												</tr>
											</logic:notEmpty>


											<tr>
												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.stockName" />
													<font color="red">*</font>
												</td>

												<td width="25%">
													<logic:present name="stockMasterForm">
														<logic:empty property="s_stockName" name="stockMasterForm">
															<html:text property="s_stockName" size="40" value="" />
														</logic:empty>
														<logic:notEmpty property="s_stockName"
															name="stockMasterForm">
															<html:text property="s_stockName" size="40"
																name="stockMasterForm" />
														</logic:notEmpty>
													</logic:present>
												</td>

												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.listingDate" />
												</td>
												<td width="25%">
													<logic:present name="stockMasterForm">
														<logic:empty property="d_listingDate"
															name="stockMasterForm">
															<html:text readonly="true" property="d_listingDate"
																size="20" value="" />
														</logic:empty>
														<logic:notEmpty property="d_listingDate"
															name="stockMasterForm">
															<html:text readonly="true" property="d_listingDate"
																size="20" name="stockMasterForm" />
														</logic:notEmpty>
													</logic:present>
													<input onclick="c2.popup('d_listingDate');" type="button"
														value="..." />
												</td>

											</tr>

											<tr>


												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.stockType" />
													<font color="red">*</font>
												</td>
												<td width="20%">
													<html:select name="stockMasterForm" property="s_stockType"
														size="1">
														<html:optionsCollection name="stockMasterForm"
															property="stockTypeCollection" />
													</html:select>
													<a href="./StockType.jsp?from=stkmaster"><bean:message
															key="stockmaster.new" />
													</a>
												</td>




												<td width="20%" align="left" class="tab">
													<bean:message key="stockmaster.iwf" />
													<font color="red">*</font>
												</td>
												<td width="25%">
													<logic:present name="stockMasterForm">
														<logic:empty property="d_iwf" name="stockMasterForm">
															<html:text property="d_iwf" size="20" maxlength="6"
																value="" />
														</logic:empty>
														<logic:notEmpty property="d_iwf" name="stockMasterForm">
															<html:text property="d_iwf" size="20" maxlength="6"
																name="stockMasterForm" />
														</logic:notEmpty>
													</logic:present>
												</td>
											</tr>

											<tr>
												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.company" />
													<font color="red">*</font>
												</td>
												<td width="20%" nowrap="nowrap">
													<html:select property="s_companyName" size="1"
														onfocus="populate(event);" onkeydown="setSelection(event)"
														onkeypress="javascript:return false">
														<html:optionsCollection name="stockMasterForm"
															property="companyListCollection" />
													</html:select>
													<a href="./AddNewCompany.jsp?from=stkmaster"><bean:message
															key="stockmaster.new" />
													</a> &nbsp;&nbsp;
												</td>

												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.issuedShare" />
													<font color="red">*</font>
												</td>
												<td width="25%">
													<logic:present name="stockMasterForm">
														<logic:empty property="s_stockID" name="stockMasterForm">
															<logic:empty property="f_issuedShares"
																name="stockMasterForm">
																<html:text property="f_issuedShares" size="20" value="" />
															</logic:empty>
															<logic:notEmpty property="f_issuedShares"
																name="stockMasterForm">
																<html:text property="f_issuedShares" size="20"
																	name="stockMasterForm" />
															</logic:notEmpty>
														</logic:empty>
														<logic:notEmpty property="s_stockID"
															name="stockMasterForm">
															<logic:empty property="f_issuedShares"
																name="stockMasterForm">
																<html:text readonly="false" property="f_issuedShares"
																	size="20" value="" />
															</logic:empty>
															<logic:notEmpty property="f_issuedShares"
																name="stockMasterForm">
																<html:text readonly="false" property="f_issuedShares"
																	size="20" name="stockMasterForm" />
															</logic:notEmpty>
														</logic:notEmpty>
													</logic:present>
												</td>
											</tr>
											<tr>

												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.stockExchange" />
													<font color="red">*</font>
												</td>

												<td width="25%">
													<html:select property="s_stockExchange" size="1"
														onfocus="populate(event);" onkeydown="setSelection(event)"
														onkeypress="javascript:return false">
														<html:optionsCollection name="stockMasterForm"
															property="stockExcListCollection" />
													</html:select>
													<input type="hidden" name="s_stockExchange_null"
														value="<%=stockMasterForm.getS_stockExchange()%>" />
													<html:hidden property="b_accpt" />


													<a href="./AddStockExchange.jsp?from=stkmaster"
														onclick="return test1()"><bean:message
															key="stockmaster.new" />
													</a>
												</td>


												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.country" />
													<font color="red">*</font>
												</td>
												<td width="25%">
													<html:select property="s_countryName" size="1"
														onfocus="populate(event);" onkeydown="setSelection(event)"
														onkeypress="javascript:return false">
														<html:optionsCollection name="stockMasterForm"
															property="countryListCollection" />
													</html:select>
												</td>

											</tr>


											<tr>
												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.faceValue" />
													<font color="red">*</font>
												</td>
												<td width="25%">
													<logic:present name="stockMasterForm">
														<logic:empty property="s_stockID" name="stockMasterForm">
															<logic:empty property="f_faceValue"
																name="stockMasterForm">
																<html:text property="f_faceValue" size="20" value="" />
															</logic:empty>
															<logic:notEmpty property="f_faceValue"
																name="stockMasterForm">
																<html:text property="f_faceValue" size="20"
																	name="stockMasterForm" />
															</logic:notEmpty>
														</logic:empty>
														<logic:notEmpty property="s_stockID"
															name="stockMasterForm">
															<logic:empty property="f_faceValue"
																name="stockMasterForm">
																<html:text readonly="true" property="f_faceValue"
																	size="20" value="" />
															</logic:empty>
															<logic:notEmpty property="f_faceValue"
																name="stockMasterForm">
																<html:text readonly="true" property="f_faceValue"
																	size="20" name="stockMasterForm" />
															</logic:notEmpty>
														</logic:notEmpty>
													</logic:present>
												</td>

												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.ratingCode" />
													<font color="red">*</font>
												</td>
												<td width="25%">
													<html:select property="s_ratingCode" size="1">
														<html:optionsCollection name="stockMasterForm"
															property="ratingCodeListCollection" />
													</html:select>
												</td>
											</tr>

											<tr>
												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.paidValue" />
													<font color="red">*</font>
												</td>
												<td width="25%">
													<logic:empty property="d_paidValue" name="stockMasterForm">
														<html:text property="d_paidValue" size="20" value="" />
													</logic:empty>
													<logic:notEmpty property="d_paidValue"
														name="stockMasterForm">
														<html:text property="d_paidValue" size="20"
															name="stockMasterForm" />
													</logic:notEmpty>
												</td>
												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.currency" />
													<font color="red">*</font>
												</td>
												<td width="25%">
													<html:select property="s_stockCurrency" size="1"
														onfocus="populate(event);" onkeydown="setSelection(event)"
														onkeypress="javascript:return false">
														<html:optionsCollection name="stockMasterForm"
															property="currencyListCollection" />
													</html:select>
												</td>

											</tr>

											<tr>
												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.alertPercentage" />
													<font color="red">*</font>
												</td>
												<td width="25%">
													<logic:empty property="f_alertPercent"
														name="stockMasterForm">
														<html:text property="f_alertPercent" size="20" value="" />&nbsp;%
	            			</logic:empty>
													<logic:notEmpty property="f_alertPercent"
														name="stockMasterForm">
														<html:text property="f_alertPercent" size="20"
															name="stockMasterForm" />&nbsp;%
	            			</logic:notEmpty>
												</td>

												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.rejection" />
													<font color="red">*</font>
												</td>

												<td width="25%">
													<logic:empty property="f_rejectionPercent"
														name="stockMasterForm">
														<html:text property="f_rejectionPercent" size="20"
															value="" />&nbsp;%
	            			</logic:empty>
													<logic:notEmpty property="f_rejectionPercent"
														name="stockMasterForm">
														<html:text property="f_rejectionPercent" size="20"
															name="stockMasterForm" />&nbsp;%
	            			</logic:notEmpty>
												</td>
											</tr>


											<tr>
												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.withholdingTax" />
													<br>
													<bean:message key="stockmaster.applicable" />
												</td>
												<td width="25%">
													<html:select property="b_withHoldingTaxApplicable" size="1">
														<html:optionsCollection name="stockMasterForm"
															property="withHoldingTaxApplicableCollection" />
													</html:select>
												</td>

												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.withholdingTax" />
												</td>
												<td width="25%">
													<logic:empty property="f_withholdingTaxPercent"
														name="stockMasterForm">
														<html:text property="f_withholdingTaxPercent" size="20"
															value="" />
													</logic:empty>
													<logic:notEmpty property="f_withholdingTaxPercent"
														name="stockMasterForm">
														<html:text property="f_withholdingTaxPercent" size="20"
															name="stockMasterForm" />&nbsp;%
	            	</logic:notEmpty>
												</td>
											</tr>

											<tr>
												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.mktLot" />
													<font color="red">*</font>
												</td>
												<td width="25%">
													<logic:empty property="s_marketLot" name="stockMasterForm">
														<html:text property="s_marketLot" size="20" value="" />
													</logic:empty>
													<logic:notEmpty property="s_marketLot"
														name="stockMasterForm">
														<html:text property="s_marketLot" size="20"
															name="stockMasterForm" />
													</logic:notEmpty>
												</td>

												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.ispriceforLot" />
												</td>
												<td width="25%">
													<html:select property="b_isPriceForLot" size="1">
														<html:optionsCollection name="stockMasterForm"
															property="is_price_for_lotCollection" />
													</html:select>
												</td>
											</tr>
											<tr>
												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.depository" />
													<br>
													<bean:message key="stockmaster.receipt" />
												</td>
												<td width="25%">
													<html:select property="adr_gdr_id" size="1">
														<html:optionsCollection name="stockMasterForm"
															property="depositoryReceiptListCollection" />
													</html:select>
												</td>
												<td width="25%" class="tab">
													<bean:message key="stockmaster.adrRatio" />
												</td>
												<td width="25%">
													<logic:empty property="s_adrRatio" name="stockMasterForm">
														<html:text property="s_adrRatio" size="5" value="" />
													</logic:empty>
													<logic:notEmpty property="s_adrRatio"
														name="stockMasterForm">
														<html:text property="s_adrRatio" size="5"
															name="stockMasterForm" />
													</logic:notEmpty>
													:
													<logic:empty property="s_adrRatio1" name="stockMasterForm">
														<html:text property="s_adrRatio1" size="5" value="" />
													</logic:empty>
													<logic:notEmpty property="s_adrRatio1"
														name="stockMasterForm">
														<html:text property="s_adrRatio1" size="5"
															name="stockMasterForm" />
													</logic:notEmpty>
												</td>
											</tr>
											<tr>
												<td width="25%" align="left" class="tab">
													<bean:message key="stockmaster.global100" />
												</td>
												<td width="25%">
													<%
														// log.info("b_global100");
													%>
													<html:select property="b_global100" size="1">
														<html:optionsCollection name="stockMasterForm"
															property="global100Collection" />
													</html:select>
												</td>
											</tr>

										</table>
										<table border="0" width="101%" class="gridStyle"
											cellspacing="0" cellpadding="4">
											<tr>
												<td width="49%" valign="top">
													<table border="1" width="69%" class="gridStyle"
														cellspacing="0" cellpadding="0">
														<tr>
															<td width="100%" class="tab">
																<html:radio property="s_growthValueType" value="g" />
																<bean:message key="stockmaster.growthStock" />
																<html:radio property="s_growthValueType" value="v" />
																<bean:message key="stockmaster.valueStock" />
															</td>
														</tr>
													</table>
												</td>

												<td width="97%" valign="top" class="tab">
													<p align="left">
														<b><bean:message key="stockmaster.active" />
														</b>
														<html:checkbox property="b_isActive" />
													</p>
												</td>

											</tr>



											<tr>
												<td width="146%" valign="top" colspan="2">
													<table border="0" width="85%" class="gridStyle"
														cellspacing="0" cellpadding="0" bordercolordark="#808080"
														style="border-collapse: collapse" bordercolor="#111111">
														<tr>
															<td width="100%" class="tab">
																<b>&nbsp;<bean:message key="stockmaster.codes" />
																</b>
																<table border="0" width="100%" cellspacing="0"
																	cellpadding="0" height="5%">
																	<tr>
																		<td width="50%" align="left" height="9">
																			<div align="left">
																				<table border="0" cellpadding="0" class="gridStyle"
																					cellspacing="0" style="border-collapse: collapse"
																					bordercolor="#111111" width="100%" id="AutoNumber1">
																					<tr>
																						<td width="8%" class="tab">
																							&nbsp;&nbsp;
																							<bean:message key="stockmaster.sedol" />
																						</td>
																						<td width="8%">
																							<logic:empty property="b_sdl"
																								name="stockMasterForm">
																								<html:text property="b_sdl" size="12" value="" />
																							</logic:empty>
																							<logic:notEmpty property="b_sdl"
																								name="stockMasterForm">
																								<html:text property="b_sdl" size="12"
																									name="stockMasterForm" />
																							</logic:notEmpty>
																						</td>
																						<td width="5%" class="tab">
																							&nbsp;&nbsp;
																							<bean:message key="stockmaster.isin" />
																							<td width="10%">
																								<logic:empty property="b_isn"
																									name="stockMasterForm">
																									<html:text property="b_isn" size="12" value="" />
																								</logic:empty>
																								<logic:notEmpty property="b_isn"
																									name="stockMasterForm">
																									<html:text property="b_isn" size="12"
																										name="stockMasterForm" />
																								</logic:notEmpty>
																							</td>
																							<td width="8%" class="tab">
																								&nbsp;&nbsp;
																								<bean:message key="stockmaster.ric" />
																								<td width="10%">
																									<logic:empty property="b_ric"
																										name="stockMasterForm">
																										<html:text property="b_ric" size="12" value="" />
																									</logic:empty>
																									<logic:notEmpty property="b_ric"
																										name="stockMasterForm">
																										<html:text property="b_ric" size="12"
																											name="stockMasterForm" />
																									</logic:notEmpty>
																								</td>
																								<td width="8%" class="tab">
																									&nbsp;&nbsp;
																									<bean:message key="stockmaster.crisil" />
																									<td width="10%">
																										<logic:empty property="b_crisil"
																											name="stockMasterForm">
																											<html:text property="b_crisil" size="12"
																												value="" />
																										</logic:empty>
																										<logic:notEmpty property="b_crisil"
																											name="stockMasterForm">
																											<html:text property="b_crisil" size="12"
																												name="stockMasterForm" />
																										</logic:notEmpty>
																									</td>
																					</tr>
																					<tr>
																						<td width="8%" class="tab">
																							&nbsp;&nbsp;
																							<bean:message key="stockmaster.cusip" />
																							<td width="10%">
																								<logic:empty property="b_csp"
																									name="stockMasterForm">
																									<html:text property="b_csp" size="12" value="" />
																								</logic:empty>
																								<logic:notEmpty property="b_csp"
																									name="stockMasterForm">
																									<html:text property="b_csp" size="12"
																										name="stockMasterForm" />
																								</logic:notEmpty>
																							</td>
																							<td width="15%" class="tab">
																								&nbsp;&nbsp;
																								<bean:message key="stockmaster.exchangeCode" />
																								<td width="10%">
																									<logic:empty property="b_exc_code"
																										name="stockMasterForm">
																										<html:text property="b_exc_code" size="12"
																											value="" />
																									</logic:empty>
																									<logic:notEmpty property="b_exc_code"
																										name="stockMasterForm">
																										<html:text property="b_exc_code" size="12"
																											name="stockMasterForm" />
																									</logic:notEmpty>
																								</td>
																								<td width="8%" class="tab">
																									&nbsp;&nbsp;
																									<bean:message key="stockmaster.ticker" />
																									<td width="10%">
																										<logic:empty property="b_tkr"
																											name="stockMasterForm">
																											<html:text property="b_tkr" size="12"
																												value="" />
																										</logic:empty>
																										<logic:notEmpty property="b_tkr"
																											name="stockMasterForm">
																											<html:text property="b_tkr" size="12"
																												name="stockMasterForm" />
																										</logic:notEmpty>
																									</td>
																									<td></td>
																					</tr>
																				</table>
																			</div>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</td>
											</tr>

											<tr>
												<td vAlign="top" width="97%" colSpan="2" class="subHeader">
													<p align="center">
														&nbsp;
													</p>
												</td>
												<td vAlign="top" width="3%">
													&nbsp;
												</td>
											</tr>


										</table>
										<tr>
											<td width="100%" valign="top" colspan="3">
												<p align="center">
													<%
														String stockid = stockMasterForm.getS_stockID();
																if (stockid != null) {
																	String url = "../CorporateDiary.jsp?ref_flag=2&s_stock="
																			+ stockid;
													%>

													<!--<input type="hidden" name="corporateAction"> 
            <html:submit value="View Corporate Actions" property="b1" /> -->
													<a href="<%=url%>"><bean:message
															key="stockmaster.viewCorporateAction" />
													</a>&nbsp;&nbsp;&nbsp;
													<input type="hidden" name="operation">
													<html:submit property="b1" onclick="return Utestcheck()">
														<bean:message key="defineIndex30" />
													</html:submit>
													&nbsp;&nbsp;&nbsp;&nbsp;
													<%
														} else {
													%>
													<html:submit property="b1" onclick="return Itestcheck()">
														<bean:message key="defineIndex30" />
													</html:submit>
													&nbsp;&nbsp;&nbsp;&nbsp;
													<%
														}
													%>
													<html:submit property="b1" onclick="return test1()">
														<bean:message key="stockmaster.new" />
													</html:submit>
													&nbsp;&nbsp; &nbsp;
													<html:reset property="B3">
														<bean:message key="indexUpdate.reset" />
													</html:reset>
													&nbsp;&nbsp;&nbsp;
													<!--<html:submit value="Import From file..." property="b1"/>-->
													<html:button property="b1" value="New Issues"
														onclick="return stktest()" />
													<a href="../ImportNewStock.jsp"><bean:message
															key="stockmaster.importFromFile" />
													</a>
											</td>
											<%=(stockMasterForm.getStockBelongsTo(request))
							.toString()%>
										</tr>



									</td>
								</tr>
								</tbody>
							</table>

						</logic:equal>

						<!-- Upto Here -->
				</html:form>



				<script language="JavaScript">
		
var keyTime, keyStr = '', allOpts, lastElement;
		var agt = navigator.userAgent.toLowerCase();
		var is_gecko = (agt.indexOf("gecko") != -1);
		function populate(srcEvent)
		{
		 // document.forms[0].selectICName.value="value0";
		  var element = (srcEvent)? ((srcEvent.target)? srcEvent.target : srcEvent.srcElement) : window.event.srcElement;
		  if(lastElement != element)
		  {
		    allOpts = new Array();
		    for(var i = 0; i < element.options.length; i++)
		      allOpts[i] = element.options[i].text.toLowerCase();
		    lastElement = element;
		  }
		}
		function setSelection(srcEvent)
		{
		  var myEvent = (srcEvent)? srcEvent : window.event;
		  var element = (myEvent.target)? myEvent.target : myEvent.srcElement;
		  var currentKey = unescape('%' + myEvent.keyCode.toString(16)).toLowerCase();
		  var idx, currentSIdx = element.selectedIndex, useOld = false;
		  var newTime = new Date().getTime();
		  if(keyTime != null && newTime - keyTime < 500) 
		  {
		    keyStr += currentKey;
		    idx = findIdx();
		    if(idx == -1) idx = currentSIdx; 
		  }
		  else 
		  {
		    keyStr = currentKey;
		    idx = currentSIdx + 1;
		    if(idx >= allOpts.length || allOpts[idx].length == 0 || allOpts[idx].charAt(0) != keyStr)
		      idx = findIdx();
		  }
		  if(idx >= 0) 
		  {
		    element.options[currentSIdx].selected = false;
		    var pattern = new RegExp('^' + keyStr.charAt(0) + '+$', "i"); 
		    if(is_gecko && pattern.test(keyStr) && idx > 0) element.options[idx-1].selected = true;
		    else element.options[idx].selected = true;
		  }
		  keyTime = newTime;
		}
			function findIdx()
			{
			    var len = keyStr.length;
			  for(var i = 0; i < allOpts.length; i++)
			    if(allOpts[i].length >= len && allOpts[i].substring(0, len) == keyStr)
			      return i;
			  return -1;
			}

	
function Utestcheck()
{
  	var conVal=confirm('Are You Sure to Update The Stock ?');
						if(conVal == true){
							
							return true;
						}
						else{
							return false;
						}
  	
  	return true;
  	//if(confirm('Are You Sure to Update The Stock ?')){
  //	}else{
  		//top.frmMain.location.reload();
  		//top.treeFrame.location.reload();
 		//alert("after reloading same page");	
  	//}  	  	
}
function Itestcheck()
{  
    var msg='';
  	var conVal=confirm('Are You Sure to Insert The Values For Stock ?');
						if(conVal == true){
						 
						 if(document.forms[0].s_companyName.value == "")
						 {
						     msg=msg+'Please Enter Company Name\n';
						  /*alert("Please Enter Company Name");
						   return false;*/
						 }
						 
						 if(document.forms[0].d_iwf.value == "")
						 {  
						    msg=msg+'Please Enter IWF value\n';
						  /*alert("Please Enter IWF value");
						   return false;*/
						 }
						 
						 if(document.forms[0].f_issuedShares.value == "")
						 {  
						   msg=msg+'Please Enter Issued Shares\n';
						 /* alert("Please Enter Issued Shares");
						   return false;*/
						 }
						 
						 if(document.forms[0].s_stockName.value == "")
						 {  
						  msg=msg+'Please Enter Stock Name\n';
						  /*alert("Please Enter Stock Name");
						   return false;*/
						 }
						 
						 if(document.forms[0].f_faceValue.value == "")
						 {  
						   msg=msg+'Please Enter Face Value\n';
						  /*alert("Please Enter Face Value");
						   return false;*/
						 }
						 
						 if(document.forms[0].f_alertPercent.value == "")
						 {  
						   msg=msg+'Please Enter Alert Percentage\n';
						   /*alert("Please Enter Alert Percentage");
						   return false;*/
						 }
						 if(document.forms[0].f_rejectionPercent.value == "")
						 {  
						  msg-msg+'Please Enter Rejection Percentage\n';
						  /*alert("Please Enter Rejection Percentage");
						   return false;*/
						 }
						 if(document.forms[0].s_marketLot.value == "")
						 {  
						   msg=msg+'Please Enter Market Lot\n';
						  /*alert("Please Enter Market Lot");
						   return false;*/
						 }
						 if(document.forms[0].b_isPriceForLot.value == "")
						 {  
						   msg=msg+'Please Specify Price for Lot\n';
						  /*alert("Please Specify Price for Lot");
						   return false;*/
						 }
						 if(msg=="") {
						 	return true;
						}else{
							alert(msg);
							return false;
						  }
						
						}
						else{
							return false;
						}
               	}
  
  	
  	
function test1()
 { 
 	document.forms[0].operation.value="New";
 	return true;
 }
 function testcAction()
 { 
 	//alert("In corporate Action script");
 	document.forms[0].corporateAction.value="View Corporate Actions";
 	return true;
 }
 function testreloadpage()
 { 
 	if(document.forms[0].frompage.value!=null)
 	{
 		top.frmMain.location.reload(); 		
 	} 	
 }
  function stktest()
 {
	document.location.href="/Stockpile/pages/masters/NewIssues.jsp?ref_flag=1";
 } 
 function treereload()
 {
 		//alert("in tree load");
		top.treeFrame.location.reload();
		//alert("after tree load");			
 }	
</script>

				</body>

			</td>
		</tr>
	</table>
</html>