<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<jsp:useBean id="regiform" scope="session"
	class="subscription.form.subscribeUserForm" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type"
			content="text/html; charset=iso-8859-1" />
		<title>Harrier</title>
		<style type="text/css">
<!--
.style1 {
	color: #990000;
	font-family: Tahoma;
	font-size: 11px;
}

.defaultText {
	font-family: Tahoma;
	font-size: 11px;
	color: #000000;
}

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url(images/page-bg.jpg);
}

.bodytexthome {
	font-family: Tahoma;
	font-size: 11px;
	color: #1A0602;
}

.outerTableFeatureHdg {
	border-bottom: 1px solid #314573;
	border-left: 1px solid #314573;
	border-right: 1px solid #314573;
	border-top: 1px solid #314573;
	color: #000000;
	font-family: Tahoma;
	font-size: 11px;
}

.style2 {
	color: #072A6F
}

.featureHDG {
	color: #072A6F;
	font-size: 11px;
	font-family: Tahoma;
}

.footertext {
	color: #990000;
	font-family: Tahoma;
	font-size: 11px;
}

A.registerLink:link {
	color: #760618;
	font-size: 11px;
	font-family: Tahoma;
	text-decoration: none;
	font-weight: bold;
}

A.registerLink:visited {
	color: #760618;
	font-size: 11px;
	font-family: Tahoma;
	text-decoration: none;
	font-weight: bold;
}

A.registerLink:hover {
	color: #760618;
	font-size: 11px;
	font-family: Tahoma;
	text-decoration: none;
	font-weight: bold;
}

.labels {
	font-family: Tahoma;
	font-size: 11px;
	color: #084583;
}

.redstar {
	color: #FF0000
}

.redtext {
	font-family: Tahoma;
	font-size: 11px;
	color: #FF0000;
}
-->
</style>
	</head>

	<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<img src="images/stockpile_banner.jpg" width="1000" height="41" />
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="731" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="37" background="images/index_07.jpg">
								&nbsp;
							</td>
							<td width="721" valign="top">
								<table width="100" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="534" valign="top">
											<img src="images/index_03.jpg" width="534" height="86" />
										</td>
										<td rowspan="5" background="images/index_00-02.jpg"
											style="padding-top: 51px;" valign="top">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td>
														<table width="186" height="110" border="0" align="center"
															cellpadding="0" cellspacing="0">
															<tr>
																<td valign="top" background="images/loginbox.jpg"
																	style="padding-top: 8px;" align="center">
																	<table border="0" align="center" cellpadding="0"
																		cellspacing="0">
																		<tr>
																			<td>
																				<img src="images/signin.jpg" width="175" height="23" />
																			</td>
																		</tr>
																		<tr>
																			<td>
																				<table width="175" border="0" cellpadding="0"
																					cellspacing="0">
																					<html:form action="/login">
																						<tr>
																							<td>
																								<span class="defaultText">Username:</span>
																							</td>
																							<td>

																								<html:text property="userid"
																									style="border: 1px solid rgb(8, 69, 131)"
																									size="12" />
																							</td>
																						</tr>
																						<tr>
																							<td class="defaultText">
																								Password:
																							</td>
																							<td>
																								<html:password property="password"
																									style="border: 1px solid rgb(8, 69, 131)"
																									size="13.5" />
																							</td>
																						</tr>

																						<tr>
																							<td class="defaultText">

																							</td>
																						</tr>
																						<tr>
																							<td>
																								<html:image src="images/login.jpg"></html:image>
																							</td>
																							<td>
																								<%
																									if (session.getAttribute("userbeen") != null) {
																											if (session.getAttribute("noexist") != null) {
																												out.print("<span class='redtext'>");
																												out.print(session.getAttribute("noexist"));
																												out.print("</span>");
																											}
																											if (session.getAttribute("datafail") != null) {
																												out.print("<span class='redtext'>Error:</span>");
																												out.print("<span class='redtext'>");
																												out.print(session.getAttribute("datafail"));
																												out.print("</span>");
																											}
																											session.setAttribute("userbeen", null);
																											session.setAttribute("datafail", null);
																										}
																								%>

																							</td>

																						</tr>
																					</html:form>

																				</table>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td>
														&nbsp;
													</td>
												</tr>
												<tr>
													<td style="padding-left: 60px; padding-top: 5px;">
														<html:link page="/pages/userregistration.jsp">Register Now</html:link>
													</td>
												</tr>
											</table>
											<p>
												&nbsp;
											</p>
											<p>
												&nbsp;
											</p>
											<p>
												&nbsp;
											</p>
											<p>
												&nbsp;
											</p>
											<p>
												&nbsp;
											</p>
										</td>
									</tr>
									<tr>
										<td bgcolor="#83A8DB" style="padding-left: 5px;">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td style="padding: 5px;">
														<p align="justify" class="bodytexthome">
															'Harrier Stockpile' is a multi-exchange, multi-currency
															solution for computing stock market indices capable of
															picking up stock prices from stock exchanges across the
															globe with a user friendly front-end application that
															displays stock prices and index values on the screen.
														</p>
														<p align="justify" class="bodytexthome">
															<span class="style2">It is highly advanced,
																feature rich, value added product with immense
																capabilities. It automates the entire process of
																capturing, defining and maintaining indices while
																factoring in corporate actions in constituent stocks.</span>
															<br />
														</p>
													</td>
												</tr>
												<tr>
													<td>
														<table border="0" align="center" cellpadding="2"
															cellspacing="2">
															<tr>
																<td>
																	<table width="242" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="23" background="images/page-cat.jpg"
																				class="featureHDG" style="padding-left: 32px;">
																				Multi Exchange
																			</td>
																		</tr>
																		<tr>
																			<td height="7">
																				<img src="images/spacer.gif" height="7" width="1" />
																			</td>
																		</tr>
																		<tr>
																			<td>
																				<table width="100%" border="0" cellpadding="0"
																					cellspacing="0" class="outerTableFeatureHdg">
																					<tr>
																						<td
																							style="padding: 3px; text-align: justify; background- ../image: url(images/featurebox-bg.jpg);">
																							It has the capability of having an Index being
																							defined with constituent stocks from multiple
																							markets around the world. This helps in defining
																							indices for a region (e.g. Latin America, Europe)
																							or a group of countries (e.g. ASEAN, G15,
																							Emerging Markets).
																						</td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																	</table>
																</td>
																<td>
																	<table width="242" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="23" background="images/page-cat.jpg"
																				class="featureHDG" style="padding-left: 32px;">
																				Multi Exchange
																			</td>
																		</tr>
																		<tr>
																			<td height="7">
																				<img src="images/spacer.gif" height="7" width="1" />
																			</td>
																		</tr>
																		<tr>
																			<td>
																				<table width="100%" border="0" cellpadding="0"
																					cellspacing="0" class="outerTableFeatureHdg">
																					<tr>
																						<td
																							style="padding: 3px; text-align: justify; background- ../image: url(images/featurebox-bg.jpg);">
																							It has the capability of having an Index being
																							defined with constituent stocks from multiple
																							markets around the world. This helps in defining
																							indices for a region (e.g. Latin America, Europe)
																							or a group of countries (e.g. ASEAN, G15,
																							Emerging Markets).
																						</td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td>
																	<table width="242" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="23" background="images/page-cat.jpg"
																				class="featureHDG" style="padding-left: 32px;">
																				Multi Exchange
																			</td>
																		</tr>
																		<tr>
																			<td height="7">
																				<img src="images/spacer.gif" height="7" width="1" />
																			</td>
																		</tr>
																		<tr>
																			<td>
																				<table width="100%" border="0" cellpadding="0"
																					cellspacing="0" class="outerTableFeatureHdg">
																					<tr>
																						<td
																							style="padding: 3px; text-align: justify; background- ../image: url(images/featurebox-bg.jpg);">
																							It has the capability of having an Index being
																							defined with constituent stocks from multiple
																							markets around the world. This helps in defining
																							indices for a region (e.g. Latin America, Europe)
																							or a group of countries (e.g. ASEAN, G15,
																							Emerging Markets).
																						</td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																	</table>
																</td>
																<td>
																	<table width="242" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="23" background="images/page-cat.jpg"
																				class="featureHDG" style="padding-left: 32px;">
																				Multi Exchange
																			</td>
																		</tr>
																		<tr>
																			<td height="7">
																				<img src="images/spacer.gif" height="7" width="1" />
																			</td>
																		</tr>
																		<tr>
																			<td>
																				<table width="100%" border="0" cellpadding="0"
																					cellspacing="0" class="outerTableFeatureHdg">
																					<tr>
																						<td
																							style="padding: 3px; text-align: justify; background- ../image: url(images/featurebox-bg.jpg);">
																							It has the capability of having an Index being
																							defined with constituent stocks from multiple
																							markets around the world. This helps in defining
																							indices for a region (e.g. Latin America, Europe)
																							or a group of countries (e.g. ASEAN, G15,
																							Emerging Markets).
																						</td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
							<td width="10" valign="top" bgcolor="#83A8DB">
								<img src="images/index_05.jpg" width="10" height="86" />
							</td>
							<td background="images/index_07.jpg">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td background="images/index_07.jpg">
								&nbsp;
							</td>
							<td background="images/index-0-fller.jpg">
								<img src="images/index_03-rev.jpg" width="534" height="26" />
							</td>
							<td>
								<img src="images/index_05-rev.jpg" width="10" height="26" />
							</td>
							<td background="images/index_07.jpg">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td height="100%" bgcolor="#003063" colspan="4">
								<img src="images/footer-bg.jpg" width="9" height="51" />
							</td>
						</tr>
						<tr>
							<td height="100%" colspan="4" align="center" class="footertext">
								Copyright 2008, All Rights Reserved
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>

