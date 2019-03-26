<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
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
<%
	if (session.getAttribute("userid") == null) {
		response.sendRedirect("userlogintemp.jsp");
		return;
	}
%>






<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Pick A Plan</title>
<style type="text/css">
<!--
body {
	background-image: url(../images/page-bg.jpg);
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.style1 {
	color: #990000;
	font-family: Tahoma;
	font-size: 11px;
}

.labels {
	font-family: Tahoma;
	font-size: 11px;
	color: #084583;
}

.redstar {
	color: #FF0000
}

.bodytext {
	font-family: Tahoma;
	font-size: 11px;
	color: #003063;
}

.redtext {
	font-family: Tahoma;
	font-size: 14px;
	color: #FF0000;
}

.style2 {
	font-family: Tahoma;
	font-size: 11px;
	color: #003063;
	font-weight: bold;
}

.bodytextMaroon {
	font-family: Tahoma;
	font-size: 11px;
	color: #800404;
}

.tdData {
	color: #800404;
	height: 28px;
	border-bottom: 1px solid #000000;
	font-family: Tahoma;
	font-size: 11px;
	background-color: #C6C6C7;
}
-->
</style>


<SCRIPT type=text/javascript>
	function validate(form) {

		alert("value" + form.subscriprion_id.value);
		return false;

	}

	function checkCheckBox() {

		document.forms[0].reset();
		alert('you must agree the terms and condition');

	}

	function chkForm(checkbox) {
		var max = checkbox.length;
		var chk = 0;
		for (var i = 0; i < max - 1; i++) {

			if (checkbox[i].checked) {

				chk = chk + 1;

			}

		}

		if (chk == 0) {
			alert("Please Select Scheme");
			return false;

		} else {
			return true;
		}
	}
</SCRIPT>



</head>

<body>








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
												<td height="39" background="../images/top-bg.jpg"><img
													src="../images/subscriptionlist.jpg" /></td>
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
																				<%
																					subscriptionForm t = new subscriptionForm();
																					session.setAttribute("sub_coll", t.getSubscriptionList());
																				%>

																				<html:form action="/load">
																					<bean:define id="temp" name="tempform"
																						property="subscriptionList" />
																					<tr>
																						<td width="42"><img src="../images/tag.jpg"
																							width="42" height="42" /></td>
																						<td valign="baseline"
																							background="../images/tag-bg.jpg"
																							style="padding-left: 140px;">&nbsp;</td>
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
																					<td align="center" valign="top" bgcolor="#DFE3EF"
																						style="padding-top: 35px;"><table width="65%"
																							border="0" cellspacing="0" cellpadding="0">
																							<tr>
																								<td><table width="100%" border="0"
																										cellspacing="0" cellpadding="0">

																										<tr>
																											<td height="22" bgcolor="#A2B2D8"
																												class="bodytextMaroon"
																												style="padding-left: 10px;">Select
																												Scheme</td>
																											<td bgcolor="#A2B2D8" class="bodytextMaroon"
																												style="padding-left: 10px;">Scheme Name</td>
																											<td bgcolor="#A2B2D8" class="bodytextMaroon"
																												style="padding-left: 10px;">Validity</td>
																											<td bgcolor="#A2B2D8" class="bodytextMaroon"
																												style="padding-left: 10px;">No Of Stock
																											</td>
																											<td bgcolor="#A2B2D8" class="bodytextMaroon"
																												style="padding-left: 10px;">Cost In $</td>
																										</tr>
																										<%
																											session.setAttribute("sub_collect",
																														tempform.getSubscriptionList());
																										%>
																										<logic:iterate id="temp"
																											property="subscriptionList" name="tempform">
																											<tr>
																												<td height="22" bgcolor="#D9DFED"
																													class="bodytextMaroon"
																													style="padding-left: 10px;"><html:multibox
																														name="temp"
																														property="subscriprion_id_list">
																														<bean:write name="temp"
																															property="subscriprion_id" />
																													</html:multibox></td>
																												<td bgcolor="#D9DFED" class="bodytextMaroon"
																													style="padding-left: 10px;"><bean:write
																														name="temp" property="subscription_name" /></td>
																												<td bgcolor="#D9DFED" class="bodytextMaroon"
																													style="padding-left: 10px;"><bean:write
																														name="temp" property="textduration" /></td>
																												<td bgcolor="#D9DFED" class="bodytextMaroon"
																													style="padding-left: 10px;"><bean:write
																														name="temp" property="stock" /></td>
																												<td bgcolor="#D9DFED" class="bodytextMaroon"
																													style="padding-left: 10px;"><bean:write
																														name="temp" property="cost" /></td>
																											</tr>
																										</logic:iterate>
																										<tr>
																											<td height="22" bgcolor="#A2B2D8"
																												class="bodytextMaroon"
																												style="padding-left: 10px;">**Choose
																												any one.</td>
																											<td bgcolor="#A2B2D8" class="bodytextMaroon"
																												style="padding-left: 10px;"></td>
																											<td bgcolor="#A2B2D8" class="bodytextMaroon"
																												style="padding-left: 10px;"></td>
																											<td bgcolor="#A2B2D8" class="bodytextMaroon"
																												style="padding-left: 10px;"></td>
																											<td bgcolor="#A2B2D8" class="bodytextMaroon"
																												style="padding-left: 10px;"></td>
																										</tr>
																										<tr>
																											<td height="22" class="bodytextMaroon">
																												&nbsp;<html:checkbox property="agree"
																													onclick="return checkCheckBox();">

																													<a href="javascript: void(0)"
																														onclick="window.open('popup.html','windowname1','width=800, height=500,scrollbars=yes,'); 
                                            return false;"><span
																														class="labels">I agree term and
																															conditions</span> </a>
																												</html:checkbox>
																											</td>
																											<td></td>
																										</tr>

																										<tr>
																											<td><html:image
																													src="../images/subscribebutton.JPG"
																													onclick="return chkForm(this.form)"></html:image>
																											</td>
																											<td></td>
																										</tr>

																										</html:form>


																									</table> <%
 	if (session.getAttribute("userbeen") != null) {
 		if (session.getAttribute("subexist") != null) {
 			out.print("<span class='redtext'>");
 			out.print("Error:" + session.getAttribute("subexist"));
 			out.print("</span>");
 		}
 		if (session.getAttribute("datafail") != null) {
 			out.print("<span class='redtext'>");
 			out.print("Error:" + session.getAttribute("datafail"));
 			out.print("</span>");
 		}
 		if (session.getAttribute("multiple") != null) {
 			out.print("<span class='redtext'>");
 			out.print("Error:" + session.getAttribute("multiple"));
 			out.print("</span>");
 		}

 		session.setAttribute("subexist", null);
 		session.setAttribute("datafail", null);
 		session.setAttribute("multiple", null);

 	}
 %></td>
																							</tr>
																							<tr>
																								<td>&nbsp;</td>
																							</tr>
																							<tr>
																								<td><span class="bodytextMaroon">**All
																										Prices are in dollars.</span></td>
																							</tr>
																							<tr>
																								<td>&nbsp;</td>
																							</tr>
																							<tr>
																								<td></td>
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
						<td height="100%" bgcolor="#003063" align="center"><img
							src="../images/footer-bg.jpg"> <img align=center
							src="../images/paypallogo2.PNG"> <!-- Footer here --></td>
					</tr>

					<tr>
						<td height="100%" align="center"><span class="style1">Copyright
								2008, All Rights Reserved </span></td>
					</tr>
				</table></td>
		</tr>
	</table>
	<map name="Map">
		<area shape="rect" coords="254,5,298,20" href="confirm.html"
			alt="next page">
	</map>
</body>
</html>

