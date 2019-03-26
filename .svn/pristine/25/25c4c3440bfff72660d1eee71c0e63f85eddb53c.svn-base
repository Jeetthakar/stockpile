<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="app.*,java.util.*"%>
<%@page import="org.apache.log4j.Logger"%>
<%
	Logger log = Logger.getLogger(this.getClass());
	log.info("inside " + this.getClass().getSimpleName() + " page");
%>
<%
	LogonForm form = null;
	String fr2 = null;
	String locale = null;
	//	AcessControl asc= null;

	if (request.isRequestedSessionIdValid()) {
		log.info("inside sesssion != null" + session);
		form = (LogonForm) session.getAttribute("user");
		fr2 = request.getParameter("from");
		locale = session.getAttribute("locale").toString();
		//	asc=new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		asc.setLocale(locale);
	}

	if (form == null || (!request.isRequestedSessionIdValid())) {
		log.info("inside sesssion == null");
		response.sendRedirect("userlogintemp.jsp");
	}
%>
<html>
<html:base />

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

<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 6.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<link rel="stylesheet" type="text/css" href="./StyleSheet.css"
	title="Default" />
<title><bean:message key="corporate.Action" /></title>

<meta name="Microsoft Theme" content="none">
<script language="javascript" src="./codethatcalendarstd.js"></script>
<script language="javascript" src="box_ex.js"></script>
<script language="javascript">
	var c2 = new CodeThatCalendar(caldef2);
</script>
</head>

<body>
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
			<td align="left" valign="top"><jsp:useBean id="corporate"
					scope="session" class="app.Corporate" /> <jsp:setProperty
					name="corporate" property="userid1"
					value='<%=session.getAttribute("userid")%>' /> <logic:equal
					value="1" name="corporate" property="succ_butt">
					<font face="Arial" size="3" color="green"><b> <bean:message
								key="corporate.Success" />
					</b></font>
					<jsp:setProperty name="corporate" property="succ_butt" value="" />
				</logic:equal> <logic:equal value="1" scope="request" parameter="ref_flag">
					<%
						corporate.reset_indevent();
					%>
					<jsp:setProperty name="corporate" property="corpid"
						value='<%=request.getParameter("corp_name")%>' />
				</logic:equal> <logic:notEmpty property="hash_stock_error" name="corporate">
					<FONT face="Arial" size="2" color="red"><b><bean:message
								key="IndexEvents.ErrorNoClosingVal" /></b></FONT>
					<jsp:setProperty name="corporate" property="commit_butt" value="0" />
				</logic:notEmpty> <br> <logic:notEmpty property="hash_error" name="corporate">
					<font color="red" size="2" face="Arial"><b><%=corporate.getStr().toString()%></b></font>
					<font face="Arial" color="red" size="2"><b><bean:message
								key="IndexEvents.ErrorCantDelete" /> </b></font>
					<br>
					<%
						Hashtable hash = corporate.getHash_error();
							hash.clear();
							corporate.setHash_error(hash);
					%>
					<jsp:setProperty name="corporate" property="commit_butt" value="0" />
				</logic:notEmpty> <!-- Form [0] Starts Here--> <html:form action="/indexCA_Action1">
					<html:errors />
					<table width="100%">
						<tr>
							<td width="100%">
								<table border="0" width="100%" cellspacing="0" cellpadding="0"
									height="100">
									<tr>
										<td width="30%" colspan="2"><font size="2" face="Arial"><b>
													<bean:message key="corporate.indHeading" />
											</b></font></td>
										<td width="33%" height="30">&nbsp;</td>
										<td width="14%" height="30">&nbsp;</td>
										<td width="23%" height="30">&nbsp;</td>
									</tr>

									<tr>
										<td width="17%" height="30"><font face="Arial" size="1"><b>
													<bean:message key="corporate.Index" />
											</b></font></td>
										<td width="46%" height="30" colspan="2"><html:select
												size="1" property="i_index" onchange="return test()">
												<html:optionsCollection name="corporate"
													property="indCollection" />
											</html:select></td>
										<td width="14%" height="30">
											<p align="right">
												<font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
												</b></font>
										</td>
										<td width="23%" height="30">&nbsp;</td>
									</tr>


									<tr>
										<td width="30%" colspan="2" bgcolor="#84AADE" align="center"
											height="15">
											<p align="right">
												<b><font face="Arial" size="1"> <bean:message
															key="corporate.Indevhead1" /> <br> <br>
												</font></b>
										</td>
										<td width="33%" bgcolor="#84AADE" align="center" height="15">
											<p align="center">
										</td>
										<td width="37%" colspan="2" bgcolor="#84AADE" align="center"
											height="15">
											<p align="center">
												<b><font face="Arial" size="1"> <bean:message
															key="corporate.Indevhead2" /> <br> <br>
												</font></b>
										</td>
									</tr>

									<tr>
										<td width="55%" colspan="3" valign="top" height="205">
											<table border="0" width="90%" cellspacing="0" cellpadding="1">

												<tr>
													<td width="24%"><font size="1" face="Arial"> <bean:message
																key="defineIndex9" />
													</font></td>
													<td width="26%"><html:text property="i_cdate"
															size="15" disabled="true" /></td>
													<td width="28%"><font size="1" face="Arial"> <bean:message
																key="defineIndex8" />
													</font></td>
													<td width="13%"><font size="1" face="Arial"> <html:text
																property="i_basedate" size="15" disabled="true" /></font></td>
												</tr>

												<tr>
													<td width="24%"><font size="1" face="Arial"> <bean:message
																key="defineIndex17" />
													</font></td>
													<td width="26%"><html:text property="i_basevalue"
															size="15" disabled="true" /></td>
													<td width="28%"><font size="1" face="Arial"> <bean:message
																key="corporate.Mstart" />
													</font></td>
													<td width="23%"><html:text property="m_start_time"
															size="15" disabled="true" /></td>
												</tr>

												<tr>
													<td width="24%"><font size="1" face="Arial"> <bean:message
																key="corporate.Nstop" />
													</font></td>
													<td width="26%"><html:text property="n_stop_time"
															size="15" disabled="true" /></td>
													<td width="28%"><font size="1" face="Arial"> <bean:message
																key="sysConfigForm.computeInterval" />
													</font></td>
													<td width="23%"><html:text
															property="compute_interval" size="15" disabled="true" /></td>
												</tr>

												<tr>
													<td width="28%"><font size="1" face="Arial"> <bean:message
																key="corporate.Ric" />
													</font></td>
													<td width="23%"><html:text property="o_ric" size="15"
															disabled="true" /></td>
													<td width="24%"><font size="1" face="Arial"> <bean:message
																key="defineIndex18" />
													</font></td>
													<td width="26%"><html:text property="isin" size="15"
															disabled="true" /></td>
												</tr>

												<tr>
													<td width="28%"><font size="1" face="Arial"> <bean:message
																key="corporate.Ischild" />
													</font></td>
													<td width="23%"><html:text property="is_child"
															size="15" disabled="true" /></td>
													<td width="24%"><font size="1" face="Arial"> <bean:message
																key="defineIndex3" />
													</font></td>
													<td width="26%"><html:text property="parent"
															size="15" disabled="true" /></td>
												</tr>

												<tr>
													<td width="28%"><font size="1" face="Arial"> <bean:message
																key="defineIndex16" />
													</font></td>
													<td width="23%"><html:text property="currency"
															size="15" disabled="true" /></td>
													<td width="24%"><font size="1" face="Arial"> <bean:message
																key="corporate.Iscustom" />
													</font></td>
													<td width="26%"><html:text property="is_customized"
															size="15" disabled="true" /></td>
												</tr>

												<tr>
													<td width="28%"><font size="1" face="Arial"> <bean:message
																key="corporate.Indclasscode" />
													</font></td>
													<td width="23%"><html:text
															property="industry_class_code" size="15" disabled="true" /></td>
													<td width="24%"><font size="1" face="Arial"> <bean:message
																key="sysConfigForm.industryClassification" />
													</font></td>
													<td width="26%"><html:text property="industry_class"
															size="15" disabled="true"></html:text></td>
												</tr>

												<tr>
													<td width="28%"><font size="1" face="Arial"><html:radio
																property="b_natureindex" value="">
																<bean:message key="defineIndex27" />
															</html:radio></font></td>
													<td width="23%"><font size="1" face="Arial"><html:radio
																property="b_natureindex" value="">
																<bean:message key="defineIndex28" />
															</html:radio></font></td>
													<td width="24%"><font size="1" face="Arial"> <bean:message
																key="corporate.Indtypname" /></font></td>
													<td width="26%"><html:text property="index_type_name"
															disabled="true" size="15" /></td>
												</tr>


											</table>
										<td width="45%" colspan="2" valign="top" height="205">
											<table border="0" width="100%" cellspacing="0"
												cellpadding="0" height="157">

												<tr>
													<td width="40%" height="27"><font size="1"
														face="Arial"> <bean:message key="corporate.Action" />
													</font></td>
													<td width="60%" height="27" align="center"><html:select
															size="1" property="corpid" onchange="return test1()">
															<html:optionsCollection name="corporate"
																property="indcorpCollection" />
														</html:select></td>
												</tr>

												<logic:equal value="rebasing" property="corpid"
													name="corporate">
													<tr>
														<td width="40%" height="27"><font size="1"
															face="Arial"> <bean:message key="defineIndex8" />
														</font></td>
														<td width="60%" height="27" align="center">
															<p align="left">
																<font size="1" face="Arial"> <html:text
																		property="base_date" />
																</font>
															</p>
													</tr>

													<tr>
														<td width="40%" height="27"><font size="1"
															face="Arial"> <bean:message key="defineIndex17" />
														</font></td>
														<td width="60%" height="27" align="center">
															<p align="left">
																<font size="1" face="Arial"> <html:text
																		property="values" />
																</font>
															</p>
													</tr>
												</logic:equal>

												<logic:notEqual value="Undo" property="button"
													name="corporate">
													<logic:equal value="addstock" property="corpid"
														name="corporate">
														<tr>
															<td width="40%" height="30"><font face="Arial"
																size="1"><b> <bean:message
																			key="sysConfigForm.stockExId" />
																</b></font></td>
															<td width="60%" height="30" colspan="2">
																<p align="left">
																	<font size="1" face="Arial"> <html:select
																			size="1" property="exc" onchange="return test5()"></font>
																</p> <html:optionsCollection name="corporate"
																	property="exc1Collection" /> </html:select>
															</td>
														</tr>
													</logic:equal>
												</logic:notEqual>

												<logic:equal value="addstock" property="corpid"
													name="corporate">
													<tr>
														<td width="40%" height="30"><font face="Arial"
															size="1"><b> <bean:message
																		key="corporate.stock" /></b></font></td>
														<td width="60%" height="30" colspan="2">
															<p align="left">
																<font size="1" face="Arial"> <html:select
																		size="5" property="stock" multiple="true">
																		<html:optionsCollection name="corporate"
																			property="stkmulCollection" />
																	</html:select>
																</font>
															</p>
														</td>
													</tr>
												</logic:equal>

												<logic:equal value="Undo" property="button" name="corporate">
													<logic:equal value="deletestock" property="corpid"
														name="corporate">
														<tr>
															<td width="40%" height="30"><font face="Arial"
																size="1"><b> <bean:message
																			key="sysConfigForm.stockExId" />
																</b></font></td>
															<td width="60%" height="30" colspan="2">
																<p align="left">
																	<font size="1" face="Arial"> <html:select
																			size="1" property="exc" onchange="return test5()"></font>
																</p> <html:optionsCollection name="corporate"
																	property="exc1Collection" /> </html:select>
															</td>
														</tr>
													</logic:equal>
												</logic:equal>
												<logic:equal value="deletestock" property="corpid"
													name="corporate">
													<tr>
														<td width="40%" height="30"><font face="Arial"
															size="1"><b> <bean:message
																		key="corporate.stock" /></b></font></td>
														<td width="60%" height="30" colspan="2">
															<p align="left">
																<font size="1" face="Arial"> <html:select
																		size="5" property="stock" multiple="true">
																		<html:optionsCollection name="corporate"
																			property="stkmulCollection" />
																	</html:select>
																</font>
															</p>
														</td>
													</tr>

												</logic:equal>

												<logic:equal value="changeiwf" property="corpid"
													name="corporate">
													<tr>
														<td width="40%" height="30"><font face="Arial"
															size="1"><b> <bean:message
																		key="corporate.stock" />
															</b></font></td>
														<td width="60%" height="30" colspan="2">
															<p align="left">
																<font size="1" face="Arial"><html:select size="5"
																		property="stock" multiple="true">
																		<html:optionsCollection name="corporate"
																			property="stkmulCollection" />
																	</html:select></font>
															</p>
														</td>
													</tr>

													<tr>
														<td width="40%" height="27"><font size="1"
															face="Arial"> <bean:message key="corporate.Iwf" /></font></td>
														<td width="60%" height="27" align="center">
															<p align="left">
																<font size="1" face="Arial"><html:text
																		property="values" /> </font>
															</p>
													</tr>
												</logic:equal>

												<logic:equal value="changeindcurr" property="corpid"
													name="corporate">
													<tr>
														<td width="40%" height="27"><font size="1"
															face="Arial"> <bean:message
																	key="sysConfigForm.currencyId" /></font></td>
														<td width="60%" height="27" align="center">
															<p align="left">
																<font size="1" face="Arial"> <html:select
																		size="1" property="ftcurrency"
																		onchange="return test_cur()">
																		<html:optionsCollection name="corporate"
																			property="ftCollection" />
																	</html:select></font>
															</p>
													</tr>

												</logic:equal>

												<logic:notEqual value="Undo" property="button"
													name="corporate">
													<tr>
														<td width="60%"><font size="1" face="Arial">
																<bean:message key="corporate.Apply" />
														</font></td>
														<td width="40%"><logic:equal value="changeindcurr"
																property="corpid" name="corporate">
																<html:text property="apply_date" readonly="true"
																	onchange="currtest()" />
																<input onclick="c2.popup('apply_date');" tabIndex="6"
																	type="button" value="..." />
															</logic:equal> <logic:notEqual value="changeindcurr" property="corpid"
																name="corporate">
																<html:text property="apply_date" readonly="true" />
																<input onclick="c2.popup('apply_date');" tabIndex="6"
																	type="button" value="..." />
															</logic:notEqual></td>
													</tr>
												</logic:notEqual>

												<logic:equal value="changeindcurr" property="corpid"
													name="corporate">
													<tr>
														<td width="40%" height="27"><font size="1"
															face="Arial"> <bean:message key="corporate.Excval" /></font></td>
														<td width="60%" height="27" align="center">
															<p align="left">
																<font size="1" face="Arial"> <html:text
																		property="curr_val" disabled="true" />
																</font>
															</p>
													</tr>

													<tr>
														<td width="65%" height="30"><font size="1"
															face="Arial"> <html:radio property="ind_div"
																	value="t" />
																<bean:message key="corporate.Indval" />
														</font></td>
														<td width="35%" height="30"><font size="1"
															face="Arial"><html:radio property="ind_div"
																	value="td" />
																<bean:message key="corporate.AdjDiv" /> </font></td>
													</tr>
												</logic:equal>

												<tr>
													<td width="60%" height="27"><html:submit
															onclick="return test2()">
															<bean:message key="CorporateDiary.Apply" />
														</html:submit></td>
													<logic:equal value="1" name="corporate"
														property="commit_butt">
														<td width="40%" height="27"><html:submit
																onclick="return test3()">
																<bean:message key="CorporateAction.Commit" />
															</html:submit></td>
													</logic:equal>
													<logic:notEqual value="1" name="corporate"
														property="commit_butt">
														<td width="40%" height="27"><html:submit
																onclick="return test3()" disabled="true">
																<bean:message key="CorporateAction.Commit" />
															</html:submit></td>
													</logic:notEqual>

												</tr>
												<tr>
													<td width="60%">
														<p align="center">
															<a href="./CorporateDiary.jsp?ref_flag=1"
																onmouseover="window.status='';return true"><bean:message
																	key="CorporateAction.Diary" /> </a>&nbsp;&nbsp;
														</p>
													</td>
													<td width="40%">&nbsp;&nbsp;<a
														href="./IndexEvents.jsp?ref_flag=1&corp_name=<%=corporate.getCorpid()%>"
														onmouseover="window.status='';return true"><bean:message
																key="Admin.Cancel" /> </a>
													</td>
												</tr>
											</table>
										</td>


										<logic:equal value="Close" scope="request" parameter="close">
											<jsp:setProperty name="corporate" property="tmcv" value="" />
											<jsp:setProperty name="corporate" property="newtmcv" value="" />
											<jsp:setProperty name="corporate" property="newTmcv" value="" />
											<jsp:setProperty name="corporate" property="divisor" value="" />
											<jsp:setProperty name="corporate" property="newdivisor"
												value="" />

											<tr>
												<td width="30%" colspan="2">
													<p align="center">
														<b><font face="Arial" size="2" color="RED"> <bean:message
																	key="IndexEvents.SelRadioButton" />
														</font></b>
													</p>
												</td>
												<td height="15" width="33%">
													<P align="left">
														<B><font face="Arial" size="2" color="RED"> <bean:message
																	key="IndexEvents.ErrorParentIndex" />
														</FONT></b>
													</P>
												</TD>
												<td width="14%" height="15"><p align="left">
														<b> <font face="Arial" size="2" color="red">
																closing value.</font></b>
													</p></td>
												<td height="15" width="23%"></td>
											</tr>

											<tr>
												<td width="30%" colspan="2"><font face="Arial" size="2">
														<html:radio property="ind_comp" value="i"
															onclick="return radtest()">
															<bean:message key="IndexEvents.ComputeIndex" />
														</html:radio>
												</font></td>
												<td height="15" width="33%"><font face="Arial" size="2">
														<html:radio property="ind_comp" value="c"
															onclick="return radtest()">
															<bean:message key="IndexEvents.ReCalcIndexParam" />
														</html:radio>
												</font></td>
												<td width="14%" height="15"></td>
												<td height="15" width="23%"><font face="Arial" size="1">
												</font></td>
											</tr>

											<tr>
												<td width="30%" colspan="2"></td>
												<td height="15" width="33%" />
												<td width="14%" height="15"></td>
												<td height="15" width="23%"><font face="Arial" size="1">
												</font></td>
											</tr>
										</logic:equal>
									<tr>
										<td width="30%" bgcolor="#84AADE" colspan="2">
											<p align="center">
												<b><font face="Arial" size="1"> <bean:message
															key="corporate.Indhead1" />
												</font></b>
											</p>
										</td>
										<td bgcolor="#84AADE" height="15" width="33%" />
										<td width="23%" bgcolor="#84AADE" height="15">
											<p align="left">
												<b><font face="Arial" size="1"> <bean:message
															key="corporate.Indhead2" />
												</font></b>
											</p>
										</td>

										<td bgcolor="#84AADE" height="15" width="14%"></td>
									</tr>

									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td height="4" />
										</tr>

										<tr>
											<td height="20" width="40%"></td>
											<td height="20" width="40%">
												<p align="right">
													<b><font face="Arial" size="1"> <bean:message
																key="corporate.Index" />
													</font></b> &nbsp;&nbsp;&nbsp;
													<html:select size="1" property="affect"
														onchange="return test4()">
														<html:optionsCollection name="corporate"
															property="affindCollection" />
													</html:select>
												</p>
											</td>
											<td height="20" width="20%" />
										</tr>
										<tr>
											<td height="4">
										</tr>

										<tr>
											<td height="20" width="20%"><p align="left">
													<b><font face="Arial" size="1"> <bean:message
																key="corporate.oldtmcv" /> &nbsp;&nbsp;&nbsp;
															&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															<html:text property="tmcv" disabled="true" size="15" />
													</font></b>
												</p></td>
											<td height="20" width="40%">
												<p align="right">
													<b><font face="Arial" size="1"> <bean:message
																key="corporate.oldtmcv" />&nbsp;&nbsp;&nbsp; <html:text
																property="tmcv1" disabled="true" size="15" />
													</font></b>
												</p>
											</td>
											<td height="20" width="20%" />
										</tr>

										<tr>
											<td height="20" width="40%"><p align="left">
													<b><font face="Arial" size="1"> <bean:message
																key="corporate.newtmcv" />&nbsp;&nbsp;&nbsp;
															&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															<html:text property="newTmcv" disabled="true" size="15" />
													</font></b>
												</p></td>
											<td height="20" width="40%">
												<p align="right">
													<b><font face="Arial" size="1"> <bean:message
																key="corporate.newtmcv" />&nbsp;&nbsp; <html:text
																property="newtmcv1" disabled="true" size="15" />
													</font></b>
												</p>
											</td>
											<td height="20" width="20%"></td>
										</tr>

										<tr>
											<td height="20" width="40%"><p align="left">
													<b><font face="Arial" size="1"> <bean:message
																key="corporate.olddiv" />&nbsp;&nbsp;&nbsp;
															&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															<html:text property="divisor" disabled="true" size="15" />
													</font></b>
												</p></td>
											<td height="20" width="40%">
												<p align="right">
													<b><font face="Arial" size="1"> <bean:message
																key="corporate.olddiv" />&nbsp; <html:text
																property="divisor1" disabled="true" size="15" />
													</font></b>
												</p>
											</td>
											<td height="20" width="20%">
										</tr>

										<tr>
											<td height="20" width="40%"><p align="left">
													<b><font face="Arial" size="1"> <bean:message
																key="corporate.newdiv" />
															&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															<html:text property="newdivisor" disabled="true"
																size="15" />
													</font></b>
												</p></td>
											<td height="20" width="40%">
												<p align="right">
													<b><font face="Arial" size="1"> <bean:message
																key="corporate.newdiv" /> <html:text
																property="newdivisor1" disabled="true" size="15" />
													</font></b>
												</p>
											</td>
											<td height="20" width="23%"></td>
										</tr>

										<tr>
											<td width="30%" height="8" />
										</tr>
									</table>
								</table> <logic:equal value="Exist" scope="request" parameter="flag">
									<script language="javascript">
										document.forms[0].i_index.disabled = true;
										var val1 = document.forms[0].corpid.value;
										document.forms[0].corpid.disabled = true;
										document.forms[0].stock.disabled = true;
										if (val1 == "changeiwf")
											document.forms[0].values.disabled = true;
										if (val1 == "deletestock")
											document.forms[0].exc.disabled = true;
									</script>
								</logic:equal> <html:hidden property="corpid"
									value="<%=corporate.getCorpid()%>" /> <html:hidden
									property="values" value="<%=corporate.getValues()%>" /> <html:hidden
									property="affect" value="<%=corporate.getAffect()%>" /> <html:hidden
									property="ap_co_button" value="" /> <html:hidden
									property="ind_comp"
									value='<%=request.getParameter("ind_comp")%>' /> <html:hidden
									property="tmcv" value="<%=corporate.getTmcv()%>" /> <html:hidden
									property="tmcv1" value="<%=corporate.getTmcv1()%>" /> <html:hidden
									property="divisor" value="<%=corporate.getDivisor()%>" /> <html:hidden
									property="divisor1" value="<%=corporate.getDivisor1()%>" /> <html:hidden
									property="newTmcv" value="<%=corporate.getNewTmcv()%>" /> <html:hidden
									property="newtmcv1" value="<%=corporate.getNewtmcv1()%>" /> <html:hidden
									property="newdivisor" value="<%=corporate.getNewdivisor()%>" />
								<html:hidden property="newdivisor1"
									value="<%=corporate.getNewdivisor1()%>" />
							</td>
						</tr>
					</table>
					<%
						//for inactive stock display
							ArrayList a1 = corporate.getStk_status();
							if (!(a1.isEmpty())) {
								Object obj[] = a1.toArray();
								int len = obj.length;
								if (len == 1) {
					%>
					<input type="hidden" name="obj_val" value="<%=obj[0].toString()%>" />
					<script>
						document.forms[0].stock.options[document.forms[0].obj_val.value].style.color = "Red";
					</script>
					<%
						} else {
									for (int i = 0; i < obj.length; i++) {
					%>
					<input type="hidden" name="obj_val" value="<%=obj[i].toString()%>" />
					<%
						}
					%>
					<script>
						var len = document.forms[0].obj_val.length;
						for (var i = 0; i < len; i++)
							document.forms[0].stock.options[document.forms[0].obj_val[i].value].style.color = "Red";
					</script>
					<%
						}
							}
					%>
				</html:form></td>
		</tr>
	</table>
	<Script language="javascript">
		function test() {
			document.forms[0].ap_co_button.value = "Index";
			document.forms[0].submit();
		}
		function test1() {
			document.forms[0].ap_co_button.value = "Action";
			document.forms[0].submit();
		}
		function test2() {
			document.forms[0].ap_co_button.value = "Apply";
		}
		function test3() {
			document.forms[0].ap_co_button.value = "Commit";
			document.forms[0].submit();
		}
		function test4() {
			document.forms[0].ap_co_button.value = "Affect";
			document.forms[0].submit();
		}
		function test5() {
			document.forms[0].submit();
		}
		function radtest() {
			document.forms[0].ap_co_button.value = "Radio";
			document.forms[0].submit();
		}
		function currtest() {
			document.forms[0].ap_co_button.value = "Currency";
			document.forms[0].submit();
		}
		function test_cur() {
			document.forms[0].ap_co_button.value = "Check_Curr";
			document.forms[0].submit();
		}
	</script>
</html>