<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page language="java"
	import="java.util.*,subscription.form.subscriptionForm"
	pageEncoding="UTF-8"%>
<jsp:useBean id="tempform" scope="session"
	class="subscription.form.subscriptionForm" />
<%@page import="org.apache.log4j.Logger"%>
<%
	Logger log = Logger.getLogger(this.getClass());
	log.info("inside " + this.getClass().getSimpleName() + " page");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>My JSP 'temp.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
<script language="javascript">
	function sendit() {

		document.forms[0].submit();

	}
</script>
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
	background-image: url(../images/page-bg.jpg);
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

<body ONLOAD="sendit();">
	<%
		subscriptionForm sub = new subscriptionForm();
		sub = (subscriptionForm) session.getAttribute("subscribe_for");
		System.out.println("11111 ---- " + sub.getSubscription_name());
		System.out.println("22222 ---- " + session.getId());
		System.out.println("33333 ---- "
				+ session.getAttribute("user_name"));
		System.out.println("44444 ---- " + sub.getSubscription_name());
		System.out.println("55555 ---- " + sub.getSubscription_name());
		System.out.println("66666 ---- " + sub.getSubscription_name());
	%>
	<h4></h4>

	<form action="https://www.paypal.com/cgi-bin/webscr" target="paypal"
		method="post">
		<img alt="" border="0"
			src="https://www.paypal.com/en_US/i/scr/pixel.gif" width="1"
			height="1"> <input type="hidden" name="cmd"
			value="_xclick-subscriptions"> <input type="hidden"
			name="business" value="amit.badiyani@harriersys.com"> <input
			type="hidden" name="item_name"
			value="<%=sub.getSubscription_name()%>"> <input type="hidden"
			name="no_shipping" value="1"> <input type="hidden"
			name="return"
			value="<%=session.getAttribute("server")%>subscriberdata.do;jsessionid=<%=session.getId()%>">
		<input type="hidden" name="cancel_return"
			value="<%=session.getAttribute("server")%>pages/SubscriptionDetails.jsp">
		<input type="hidden" name="no_note" value="1"> <input
			type="hidden" name="currency_code" value="USD"> <input
			type="hidden" name="lc" value="IN"> <input type="hidden"
			name="bn" value="PP-SubscriptionsBF"> <input type="hidden"
			name="a3" value="<%=sub.getCost()%>"> <input type="hidden"
			name="p3" value="<%=sub.getValidity()%>"> <input
			type="hidden" name="t3" value="M"> <input type="hidden"
			name="src" value="1"> <input type="hidden" name="sra"
			value="1"> <input type="hidden" name="custom"
			value="<%=session.getAttribute("user_name")%>">
	</form>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<!--<img src="../images/stockpile_banner.jpg" width="1000" height="41"></td>-->
		</tr>
		<tr>
			<td align="center" valign="top"><table width="800" border="0"
					cellspacing="0" cellpadding="0" height="100%" align="center">
					<tr>
						<td height="100%" align="center" valign="top"
							style="background-image: url(../images/bg1.jpg); background-repeat: repeat-x;"><table
								width="731" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td><table width="100%" border="0" cellspacing="0"
											cellpadding="0">
											<tr>
												<td background="../images/top-bg.jpg"></td>
											</tr>
											<tr>
												<td height="100%"><table width="100%" border="0"
														cellspacing="0" cellpadding="0">
														<tr>
															<td width="5"><img src="../images/tl.jpg" width="5"
																height="5" /></td>
															<td background="../images/hbar-table2.jpg"><img
																src="../images/hbar-table2.jpg" width="40" height="5" /></td>
															<td width="5"><img src="../images/tr.jpg" width="5"
																height="5" /></td>
														</tr>
														<tr>
															<td background="../images/vbar-table2.jpg"><img
																src="../images/vbar-table2.jpg" width="5" height="10" /></td>
															<td bgcolor="#83A8DB" height="100%"><table
																	width="100%" border="0" cellspacing="0" cellpadding="0"
																	height="100%">
																	<tr>
																		<td><table width="100%" border="0"
																				cellspacing="0" cellpadding="0">
																				<tr>
																					<td width="42"><img src="../images/tag.jpg"
																						width="42" height="42" /></td>
																					<td valign="baseline"
																						background="../images/tag-bg.jpg"
																						style="padding-left: 140px;"></td>
																				</tr>
																			</table></td>
																	</tr>
																	<tr>
																		<td style="padding-top: 15px;" height="100%"><table
																				width="100%" border="0" cellspacing="0"
																				cellpadding="0" height="100%">
																				<tr>
																					<td width="7" height="7"><img
																						src="../images/tl-inner.jpg" width="7" height="7" /></td>
																					<td background="../images/hbar-inner.jpg"><img
																						src="../images/hbar-inner.jpg" width="10"
																						height="7" /></td>
																					<td width="7"><img
																						src="../images/tr-inner.jpg" width="7" height="7" /></td>
																				</tr>
																				<tr>
																					<td background="../images/vbar-inner.jpg"
																						height="350"><img
																						src="../images/vbar-inner.jpg" width="7"
																						height="10" /></td>
																					<td align="center" valign="top" bgcolor="#DFE3EF"><table
																							width="97%" border="0" cellspacing="0"
																							cellpadding="0">
																							<tr>
																								<td><table width="100%" border="0"
																										cellspacing="0" cellpadding="0">
																										<tr>
																											<td class="defaultText">PayPal. The
																												safer, easier way to pay.</td>
																											<td align="right" class="redtext"></td>
																											<td></td>
																											<td align="right"><a href="#"></a></td>
																										</tr>
																										<tr>
																											<td align="left" class="redtext">redirecting..........</td>
																										</tr>
																									</table></td>
																							</tr>
																							<tr>
																								<td><hr color="#0B3081" /></td>
																							</tr>
																							<tr>
																								<td><table width="100%" border="0"
																										cellspacing="0" cellpadding="0">
																										<tr>

																										</tr>
																									</table></td>
																							</tr>
																							<tr>
																								<td>&nbsp;</td>
																							</tr>
																						</table></td>
																					<td background="../images/vbar-inner-2.jpg"><img
																						src="../images/vbar-inner-2.jpg" width="7"
																						height="10" /></td>
																				</tr>
																				<tr>
																					<td><img src="../images/bl-inner.jpg"
																						width="7" height="7" /></td>
																					<td background="../images/hbar-inner-2.jpg"><img
																						src="../images/hbar-inner-2.jpg" width="10"
																						height="7" /></td>
																					<td><img src="../images/br-inner.jpg"
																						width="7" height="7" /></td>
																				</tr>
																			</table></td>
																	</tr>
																</table></td>
															<td background="../images/vbar-table2.jpg"><img
																src="../images/vbar-table2.jpg" width="5" height="10" /></td>
														</tr>
														<tr>
															<td><img src="../images/bl.jpg" width="5" height="5" /></td>
															<td background="../images/hbar-table2.jpg"><img
																src="../images/hbar-table2.jpg" width="40" height="5" /></td>
															<td><img src="../images/br.jpg" width="5" height="5" /></td>
														</tr>
													</table></td>
											</tr>
										</table></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
							</table> <!-- Body here --></td>
					</tr>
					<tr>
						<td height="100%" align=center bgcolor="#003063"><img
							src="../images/footer-bg.jpg"><img align=center
							src="../images/paypallogo2.PNG">
					</tr>
					<!-- Footer here -->


					<tr>
						<td height="100%" align="center"><span class="style1">Copyright
								2008, All Rights Reserved</span></td>
					</tr>
				</table> <map name="MapMap">
					<area shape="rect" coords="317,3,361,18" href="#" alt="next page">
				</map></td>
		</tr>
	</table>
	<map name="Map">
		<area shape="rect" coords="254,5,298,20" href="confirm.html"
			alt="next page">
	</map>
</body>
</html>













