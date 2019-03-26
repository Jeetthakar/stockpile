<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="app.*,java.util.*"%>
<%@page import="org.apache.log4j.Logger"%>
<%
	Logger log = Logger.getLogger(this.getClass());
	log.info("inside " + this.getClass().getSimpleName() + " page");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Harrier</title>
<style type="text/css">
<!--
.content {
	font-family: Arial;
	font-size: 12px;
	color: #333333;
	text-align: left;
}

.style1 {
	color: #990000;
	font-family: Tahoma;
	font-size: 11px;
}

.defaultText {
	font-family: Tahoma;
	font-size: 13px;
	color: #000000;
}

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.borderContent {
	border-left: 0px;
	border-right: 0px;
	border-top: 0px;
	font-family: Tahoma;
	font-size: 11px;
	color: #084583;
}

.titles1 {
	border-top: 1px solid #072A6F;
	border-bottom: 1px solid #072A6F;
	border-left: 0px;
	border-right: 0px;
	border-top: 1px;
	font-family: Tahoma;
	font-size: 11px;
	color: #8F213A;
	background-color: #6AAAF1;
	font-weight: bold;
}

.contentoftable2 {
	FONT-SIZE: 12px;
	color: #000000;
	font-weight: bold;
	font-family: Arial;
}

.contentoftable {
	FONT-SIZE: 12px;
	font-weight: bold;
	color: #006699;
	font-family: Verdana, Arial;
}

.bodytexthome {
	font-family: Tahoma;
	font-size: 11px;
	color: #000000;
}

.contentmiddle {
	font-family: Arial;
	font-size: 12px;
	color: #333333;
	text-align: justify;
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
	color: #000000;
	font-family: Tahoma;
	font-size: 11px;
}

.featureHDG {
	color: #072A6F;
	font-size: 12px;
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
<%
	response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
%>
<body onload="initialize();" locale="true">
	<html:form action="/LogonSubmit">
		<html:errors />
		<%
			if (request.getParameter("invalid") != null
							&& request.getParameter("invalid").equals("yes")) {
		%>
		<script language="javascript">
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
							log.info("Session is false");
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
		%>
		<script language="javascript">
			top.topFrame.location.reload();
			top.treeFrame.location.reload();
		</script>
		<%
			}
					}
		%>



		<table border="0" cellspacing="0" cellpadding="0">

			<tr>
				<td align="center">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">

						<tr>
							<td width="100%" valign="top">
								<table border="1" cellspacing="0" cellpadding="0">
									<tr>
										<td valign="top">
											<div>
												<table width="100%" border="1" cellspacing="0"
													cellpadding="0">
													<tr>

														<td align="center" class="borderContent">
															<h5></h5>
														</td>

													</tr>
													<tr>
														<td align="center" width="100%" valign="top">
															<table width="100%" border="1" cellspacing="0"
																cellpadding="0">
																<tr>
																	<td align="center" class="borderContent"><B>Stockpile
																			Features</B></td>
																</tr>
																<tr class="borderContent">
																	<td align="left" bgcolor="99CCFF" class="borderContent">
																		<B>Index</B>
																	</td>
																</tr>

																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Index Creation&nbsp;&nbsp;&nbsp;
																	</td>
																</tr>
																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Index Updation
																	</td>
																</tr>
																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Index Computation
																	</td>
																</tr>

																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Child Indices
																	</td>
																</tr>
																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Sectoral Indices
																	</td>
																</tr>
																<tr class="borderContent">

																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Index Backtracking
																	</td>

																</tr>
																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Compliance Criteria
																	</td>
																</tr>


																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Back Dated Index

																	</td>
																</tr>






																<tr class="borderContent">

																	<td align="left" bgcolor="99CCFF" class="borderContent">
																		<B>Corporate Action</B>
																	</td>

																</tr>

																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Addition of stock
																	</td>
																</tr>
																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Deletion of stock
																	</td>
																</tr>
																<tr class="borderContent">

																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Change in Free Float
																	</td>

																</tr>
																<tr class="borderContent">

																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Corporate Action Report
																	</td>

																</tr>

																<tr class="borderContent">

																	<td align="left" bgcolor="99CCFF" class="borderContent">
																		<B>Analytics</B>
																	</td>

																</tr>
																<tr class="borderContent">

																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Index List
																	</td>

																</tr>


																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Index Divisor

																	</td>
																</tr>
																<tr class="borderContent">

																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Index Movement
																	</td>
																</tr>
																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Index Comparison
																	</td>
																</tr>
																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Index Composition
																	</td>
																</tr>


																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Index Performance

																	</td>

																</tr>
																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Index Co-relation

																	</td>
																</tr>
																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Industry weightage

																	</td>
																</tr>

																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Latest Index Divisor

																	</td>
																</tr>

																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" /> &nbsp;
																		Index Returns & Volatility

																	</td>
																</tr>

																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Stock Contribution to
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Change
																		in Index
																	</td>
																</tr>





																<tr class="borderContent">

																	<td align="left" bgcolor="99CCFF" class="borderContent">
																		<b><i>Fearures Coming Soon!</i></b>
																	</td>
																</tr>
																<!--
                                                     
                                                     
                                                     <tr class="borderContent">
                                                      
                                                      <td  align="left" class="borderContent"><A  href="#Batch Report">Batch Report</A></td>
                                                     </tr>
                                                        -->
																<tr class="borderContent">

																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Batch Report
																	</td>
																</tr>

																<tr class="borderContent">

																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Query Reports
																	</td>
																</tr>
																<tr class="borderContent">

																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Traded volume
																	</td>
																</tr>
																<tr class="borderContent">

																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Fixed Income
																	</td>
																</tr>

																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Stock List
																	</td>
																</tr>
																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Stock dividend
																	</td>
																</tr>
																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Inactive stocks
																	</td>
																</tr>

																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Index Calculator
																	</td>
																</tr>



																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Index wise PE/PB
																	</td>
																</tr class="borderContent">


																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Company weightage
																	</td>
																</tr class="borderContent">

																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Add Captured Indices
																	</td>
																</tr class="borderContent">
																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Index Currency wise
																	</td>
																</tr class="borderContent">




																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Index Compare OHLC

																	</td>
																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Portfolio basket calculator

																	</td>
																</tr>
																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Index List with 52
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;weekH/L

																	</td>
																</tr>

																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Capital change to
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;universe
																	</td>
																</tr class="borderContent">




																<tr class="borderContent">
																	<td align="left" class="borderContent">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<img src="images/yello_star.gif" border="0" />
																		&nbsp;Stock Details between
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;dates


																	</td>
																</tr>


															</table>

														</td>
														<td width="100" valign="top">
															<table width="100%" border="0" cellspacing="0"
																cellpadding="0">
																<tr>
																	<td></td>
																	<td>&nbsp;&nbsp;&nbsp;</td>
																	<td rowspan="5" style="padding-top: 51px;" valign="top">
																		<table width="100%" border="0" cellspacing="0"
																			cellpadding="0">
																			<tr>
																				<td>
																					<table width="242" height="144" border="0"
																						align="center" cellpadding="0" cellspacing="0">
																						<tr>
																							<td align="left" valign="top" border="1"
																								background="images/loginbox443.JPG"
																								style="padding-top: 8px;" align="center">
																								<table border="0" align="center" cellpadding="0"
																									cellspacing="0">
																									<tr>
																										<td></td>
																										<td></td>
																										<td align="left" class="borderContent">
																											<h5>Sign In</h5>
																										</td>
																									</tr>

																									<tr>
																										<td></td>
																										<td></td>
																										<td>
																											<table align="left" width="100%" border="0"
																												cellpadding="0" cellspacing="0">
																												<tr>
																													<td><span class="defaultText">Username:&nbsp;</span>
																													</td>
																													<td><html:text property="username"
																															style="border: 1px solid rgb(8, 69, 131)"
																															size="12" /></td>
																												</tr>

																												<tr>
																													<td class="defaultText">Password:</td>
																													<td><html:password property="password"
																															style="border: 1px solid rgb(8, 69, 131)"
																															size="14.5" /></td>
																												</tr>

																												<tr>
																													<td class="defaultText"></td>
																												</tr>
																												<tr>
																													<td><html:image src="images/login.jpg"></html:image>
																													</td>
																													<td class="defaultText"><html:link
																															page="/pages/forgotpassword.jsp">
																															<span class="defaultText">Forgot
																																Password?</span>
																														</html:link></td>

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
																				<td>&nbsp;</td>
																			</tr>
																			<tr>
																				<td style="padding-left: 70px; padding-top: 5px;">
																					<html:link page="/pages/userregistration.jsp">
																						<span class="defaultText"><img
																							src="images/RegisterNowButton.jpg" border="0" /></span>
																					</html:link>
																				</td>
																			</tr>
																			<tr>
																				<td style="padding-left: 70px; padding-top: 5px;">
																					&nbsp;</td>
																			</tr>
																			<tr>
																				<td style="padding-left: 70px; padding-top: 5px;"
																					align="left">
																			<tr>
																				<td class="borderContent"><B>Contact Us :</B></td>
																			</tr>
																			<tr>
																				<td class="borderContent">India Ph :
																					91-712-3296558,07103-281212</td>
																			</tr>
																			<tr>
																				<td class="borderContent">USA Ph.&nbsp;:
																					1-646-367-9646</td>
																			</tr>
																			<tr>
																				<td class="borderContent">Email
																					&nbsp;&nbsp;&nbsp;&nbsp; : sales@harriersys.com</td>
																			</tr>
																			</td>

																			</tr>
																			<!--<tr>
                    <td style="padding-left:70px; padding-top:5px;"><a href="http://www.harriersys.com/Stockpile.pdf" target="stockpile" ><img align="left" src="images/banner.JPG" border="0" /></a></td>
                  </tr>
                -->
																		</table>
																		<p></p>

																		<p class="borderContent"></p>
																		<p class="borderContent"></p>
																		<p class="borderContent"></p>
																		<p class="borderContent"></p>
																	</td>
																</tr>
																<tr>
																	<td style="padding-left: 5px;">
																		<table width="100%" border="0" cellspacing="0"
																			cellpadding="0">
																			<!--
                 <TR>
          <TD class=content><IMG height=21 
            src="images/pro-hstock.gif" 
            width=240></TD></TR>
        -->
																			<TR>
																			<TR>
																				<TD class=contentmiddle vAlign=top><STRONG
																					class=contentoftable2>'Harrier Stockpile'</STRONG>,
																					the final word for computing stock market
																					indices.Harrier Stockpile is a multi-exchange,
																					multi-currency solution for computing stock market
																					indices. It automates the entire process of
																					capturing, defining and maintaining indices while
																					factoring in corporate actions in constituent
																					stocks and applying rule based manipulation of
																					various parameters related to the stocks thereby
																					completely eliminating any scope of human error.
																					The result is highly accurate indices,
																					consistently. <BR> <BR></TD>
																			</TR>

																			<tr>
																				<TD class=contentmiddle vAlign=top>Harrier
																					Stockpile helps to automate the process of
																					defining, computing and maintaining indices
																					including applying corporate actions, thereby
																					helping subscribers get correct index values on
																					time every time. <BR> <BR>
																				</TD>
																			</tr>



																			<TR>
																				<TD class=contentmiddle vAlign=top align=middle>
																					<TABLE cellSpacing=2 cellPadding=4 width="100%"
																						border=0>
																						<TBODY>
																							<TR>
																								<TD align=middle><A
																									href="http://www.harriersys.com/images/portfolio_big.png"
																									target=_blank><IMG height=331
																										src="images/portfolio.png" width=503 border=0></A>
																								</TD>
																							</TR>
																						</TBODY>
																					</TABLE>
																				</TD>
																			</TR>


																			<TR>
																				<TD class=subtitle><IMG height=21
																					src="images/stockpile-features.gif" width=240>
																					<BR> <BR></TD>
																			</TR>
																			<TR>
																				<TD class=contentmiddle vAlign=top><SPAN
																					class=contentoftable2>'Harrier Stockpile'</SPAN>
																					has following major features. <BR> <BR></TD>
																			</TR>
																			<TR>
																				<TD class=contentoftable>Multi exchange <BR>
																					<BR>
																				</TD>
																			</TR>
																			<TR>
																				<TD class=contentmiddle><SPAN
																					class=contentoftable2>'Harrier Stockpile'</SPAN> is
																					capable of allowing an index to be defined
																					comprising of stocks from multiple markets around
																					the world. This helps in defining indices for a
																					region (e.g. Latin America, Europe) or a group of
																					countries (e.g. ASEAN, G15, Emerging Markets). <BR>
																					<BR></TD>
																			</TR>
																			<TR>
																				<TD class=contentoftable>Multi currency <BR>
																					<BR>
																				</TD>
																			</TR>
																			<TR>
																				<TD class=contentmiddle>The prices of stocks
																					from different countries are converted from their
																					local currency to the base currency of the index
																					for computing the market capitalization of an
																					index. <BR> <BR>
																				</TD>
																			</TR>
																			<TR>
																				<TD class=contentoftable>Multiple Methodologies
																					<BR> <BR>
																				</TD>
																			</TR>
																			<TR>
																				<TD class=contentmiddle vAlign=top>The system
																					supports calculation of index values using Market
																					Cap method, Adjusted Market Cap method (here, the
																					Investible/float factor for a stock is considered),
																					currency index and total returns index. <BR> <BR>
																				</TD>
																			</TR>
																			<TR>
																				<TD class=contentoftable><a
																					name="Corporate Actions"></a>Corporate Actions <BR>
																					<BR></TD>
																			</TR>
																			<TR>
																				<TD class=contentmiddle><SPAN
																					class=contentoftable2>'Harrier Stockpile'</SPAN>
																					automatically applies corporate action on
																					respective stocks so you don't have to keep track
																					of various stock events. It lets you apply
																					corporate actions at Index levels <BR> <BR>
																					<BR></TD>
																			</TR>
																			<TR>
																				<TD vAlign=top align=middle><IMG height=315
																					src="images/stockpile.gif" width=550 border=1>
																					<BR> <BR></TD>
																			</TR>
																			<TR>
																				<TD class=contentoftable>Stock-wise, Index-wise
																					Investible Weights <BR> <BR>
																				</TD>
																			</TR>
																			<TR>
																				<TD class=contentmiddle>'Harrier Stockpile'
																					supports having different free float or investible
																					weights for the same stock in different indices. In
																					other words, the float may be specific to a
																					stock-index combination. This flexibility is highly
																					useful when different indices follow different
																					criteria for arriving at the float for a stock. <BR>
																					<BR>
																				</TD>
																			</TR>

																			<TR>
																				<TD class=contentoftable>Configurable Index
																					Hierarchy (Industry classification) <BR> <BR>
																				</TD>
																			</TR>
																			<TR>
																				<TD class=contentmiddle><SPAN
																					class=contentoftable2>'Harrier Stockpile'</SPAN>
																					has built in support for hierarchy of indices with
																					parent child relationship. You can define group
																					level, industry level and sectoral indices within a
																					top-level index using configurable industry
																					classification system. <BR> <BR></TD>
																			</TR>
																			<TR>
																				<TD class=contentoftable>Support for Index
																					Rules <BR> <BR>
																				</TD>
																			</TR>
																			<TR>
																				<TD class=contentmiddle><SPAN
																					class=contentoftable2>'Harrier Stockpile</SPAN>
																					lets you define rules/criteria for inclusion of
																					stocks in an index and ensures that the
																					constituents comply with the index rules. Based on
																					this, it provides reports with stocks eligible for
																					inclusion and the constituents that became
																					ineligible. This can also be used for creating
																					different compliance-criteria and to define indices
																					meeting particular criteria like indices that are
																					environment friendly or are compliant with
																					"Shari'ah", for example. <BR> <BR></TD>
																			</TR>


																			<TR>
																				<TD class=contentoftable>Security <BR> <BR>
																				</TD>
																			</TR>
																			<TR>
																				<TD class=contentmiddle><SPAN
																					class=contentoftable2>'Harrier Stockpile'</SPAN>
																					has excellent security features built in. Each
																					index can be assigned a threshold limit and a
																					rejection limit for deviation in index value from
																					the previous value. Each stock price can also be
																					assigned similar limits for deviation. <BR> <BR>
																				</TD>
																			</TR>

																			<TR>
																				<TD class=contentoftable>Support for Historical
																					Calculation <BR> <BR>
																				</TD>
																			</TR>
																			<TR>
																				<TD class=contentmiddle>It supports applying
																					corporate actions effective from past date and can
																					create indices based on a historic date. <BR>
																					<BR>
																				</TD>
																			</TR>

																			<TR>
																				<TD class=contentoftable>Multilingual <BR>
																					<BR>
																				</TD>
																			</TR>
																			<TR>
																				<TD class=contentmiddle><SPAN
																					class=contentoftable2>ËœHarrier Stockpile</SPAN>
																					dynamically customizes the user interface using the
																					language preferred by user while logging into the
																					system. It supports many languages like English,
																					German, French, Spanish. <BR> <BR></TD>
																			</TR>
																			<TR>
																				<TD class=contentoftable><a name="Batch Report"></a>Batch
																					reports <BR> <BR></TD>
																			</TR>
																			<TR>
																				<TD class=contentmiddle>Using batch reports,
																					user can generate multiple reports simultaneously
																					with a single mouse click. This is highly useful
																					for generating and emailing multiple reports
																					required daily or periodically, with a predefined
																					set of parameters. <BR> <BR>
																				</TD>
																			</TR>
																			<TR>
																				<TD class=contentoftable>Rich and intuitive
																					user interface <BR> <BR>
																				</TD>
																			</TR>
																			<TR>
																				<TD class=contentmiddle><SPAN
																					class=contentoftable2>'Harrier Stockpile'</SPAN>
																					provides rich and extremely friendly user interface
																					that makes it effortless to switch from your
																					current practice and start using Harrier Stockpile
																					for computing indices published by your
																					organization. It provides various reports with
																					graphical presentation of information. <BR> <BR>
																				</TD>
																			</TR>

																			<TR>
																				<TD>
																					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																				</TD>
																			</TR>
																			</TBODY>
																		</TABLE>
																	</TD>
																</TR>
																<TR>
																	<TD vAlign=top align=left>
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

																	</TD>
																</TR>
															</TABLE>
														</TD>
													</TR>



													<tr>
														<td height="100%" colspan="4" align="center"
															class="footertext">Copyright 2008, All Rights
															Reserved</td>
													</tr>
												</table>
											</div>
										</td>
								</table>
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

