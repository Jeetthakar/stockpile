<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page language="java"
	import="java.util.*,subscription.form.subscriptionForm"
	pageEncoding="UTF-8"%>
<%@page import="org.apache.log4j.Logger"%>
<%
	Logger log = Logger.getLogger(this.getClass());
	log.info("inside " + this.getClass().getSimpleName() + " page");
%>
<jsp:useBean id="tempform" scope="session"
	class="subscription.form.subscriptionForm" />

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
		String amount = sub.getCost();
		String url = request.getRequestURI();
		StringBuffer sb = request.getRequestURL();
		url = sb.toString()
				.substring(0, sb.toString().lastIndexOf("/") + 1);
		url = url + "paymentthankyou.jsp";
		System.out.println("URL *** " + url);
	
	%>
	<h4></h4>
	<!-- Commented by Samiksha -->
	<!-- 	<form action="https://www.paypal.com/cgi-bin/webscr" target="paypal" -->
	<!-- 		method="post"> -->
	<!-- 		<img alt="" border="0" -->
	<!-- 			src="https://www.paypal.com/en_US/i/scr/pixel.gif" width="1" -->
	<!-- 			height="1"> <input type="hidden" name="cmd" -->
	<!-- 			value="_xclick-subscriptions"> <input type="hidden" -->
	<!-- 			name="business" value="manojadekar@yahoo.com"> <input -->
	<!-- 			type="hidden" name="item_name" -->
	<%-- 			value="<%=sub.getSubscription_name()%>"> <input type="hidden" --%>
	<!-- 			name="no_shipping" value="1"> <input type="hidden" -->
	<!-- 			name="return" -->
	<%-- 			value="<%=session.getAttribute("server")%>subscriberdata.do;jsessionid=<%=session.getId()%>"> --%>
	<!-- 		<input type="hidden" name="cancel_return" -->
	<%-- 			value="<%=session.getAttribute("server")%>pages/SubscriptionDetails.jsp"> --%>
	<!-- 		<input type="hidden" name="no_note" value="1"> <input -->
	<!-- 			type="hidden" name="currency_code" value="USD"> <input -->
	<!-- 			type="hidden" name="lc" value="IN"> <input type="hidden" -->
	<!-- 			name="bn" value="PP-SubscriptionsBF"> <input type="hidden" -->
	<%-- 			name="a3" value="<%=sub.getCost()%>"> <input type="hidden" --%>
	<%-- 			name="p3" value="<%=sub.getValidity()%>"> <input --%>
	<!-- 			type="hidden" name="t3" value="M"> <input type="hidden" -->
	<!-- 			name="src" value="1"> <input type="hidden" name="sra" -->
	<!-- 			value="1"> <input type="hidden" name="custom" -->
	<%-- 			value="<%=session.getAttribute("user_name")%>"> --%>
	<!-- 	</form> -->

	<!-- 	<form action="pay.jsp" method="post" name="frm" id="theForm" /> -->
	<form action="pay.jsp" method="get" name="frm" id="theForm" />
	<input type="hidden" name="V3URL"
		value="https://secure.ebs.in/pg/ma/payment/request" />
	<input type="text" id="account_id" name="account_id" value="22361"
		type="hidden" />
	<select id="channel" name="channel" type="hidden">
		<option value="0" selected="selected">Standard</option>
	</select>
	<input type="text" id="currency" name="currency" value="INR"
		type="hidden" />
	<!-- 	<input type="text" id="return_url" name="return_url" type="hidden" -->
	<!-- 		value="https://secure.ebs.in/" /> -->
	<input type="text" id="return_url" name="return_url" type="hidden"
		value=<%=url%> />
	<input id="mode" name="mode" type="text" value="TEST" type="hidden" />
	<select id="algo" name="algo" type="hidden">
		<option value="SHA512" selected="selected">SHA512</option>
	</select>
	<input type="text" id="reference_no" name="reference_no" type="hidden"
		value="abc123456" />
	<input type="text" id="amount" name="amount" value="<%=amount%>"
		type="hidden" />
	<input id="description" name="description" type="text"
		value="Testing Card Payment" />
	<input id="name" name="name" type="text" type="hidden"
		value="Harrier Information System Pvt Ltd" />
	<input id="address" name="address" type="text" type="hidden"
		value="First Floor, Shanti Plaza, Harihar Nagar, Besa,Nagpur - 440034" />
	<input id="city" name="city" type="text" value="Nagpur" type="hidden" />
	<input id="state" name="state" type="text" value="Maharashtra"
		type="hidden" />
	<input id="postal_code" name="postal_code" type="text" value="440034"
		type="hidden" />
	<input id="country" name="country" type="text" value="IND"
		type="hidden" />
	<input id="email" name="email" type="text" type="hidden"
		value="manish.sawankar@harriersys.com" />
	<td align="left"><input id="phone" name="phone" type="text"
		type="hidden" value="9860001615" /></td>
	<td align="left"><input id="ship_name" name="ship_name"
		type="hidden" type="text" value="Harrier Information System Pvt Ltd" /></td>
	<input id="ship_address" name="ship_address" type="text" type="hidden"
		value="First Floor, Shanti Plaza, Harihar Nagar, Besa,Nagpur - 440034" />
	<input id="ship_city" name="ship_city" type="text" value="Nagpur"
		type="hidden" />
	<input id="ship_state" name="ship_state" type="text" type="hidden"
		value="Maharashtra" />
	<input id="ship_country" name="ship_country" type="text" value="IND"
		type="hidden" />
	<input id="ship_phone" name="ship_phone" type="text" value="9860001615"
		type="hidden" />
	</td>
	<input id="ship_postal_code" name="ship_postal_code" type="text"
		type="hidden" value="440034" />
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













