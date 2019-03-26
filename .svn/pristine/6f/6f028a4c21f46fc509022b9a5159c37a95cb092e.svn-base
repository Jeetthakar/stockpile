<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page language="java"
	import="java.util.*,subscription.form.UseraccountinfoForm"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="app.*"%>
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

<jsp:useBean id="tempform1" scope="session"
	class="subscription.form.UseraccountinfoForm" />
<jsp:setProperty name="tempform1" property="user_id"
	value='<%=session.getAttribute("userid").toString()%>' />
<jsp:setProperty name="tempform1" property="role_id"
	value='<%=session.getAttribute("role_id").toString()%>' />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>User Account</title>
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
	font-size: 11px;
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
													src="../images/myaccount.jpg" /></td>
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
																					UseraccountinfoForm t = new UseraccountinfoForm();
																					session.setAttribute("user_sub", t.getUseraccountinfo());
																					response.setContentType("text/html");
																				%>
																				<html:form action="/cancel">
																					<bean:define id="temp" name="tempform1"
																						property="useraccountinfo" />
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
																						style="padding-top: 35px;">
																						<table width="75%" border="0" cellspacing="0"
																							cellpadding="0">
																							<tr>
																								<td>

																									<table width="100%" border="0" valign="top"
																										cellspacing="0" cellpadding="0">
																										<tr>
																											<td bgcolor="#A2B2D8" class="bodytextMaroon"
																												style="padding-left: 10px;"><b>Select</b></td>
																											<td bgcolor="#A2B2D8" class="bodytextMaroon"
																												style="padding-left: 10px;"><b>Scheme
																													Name</b></td>
																											<td bgcolor="#A2B2D8" class="bodytextMaroon"
																												style="padding-left: 10px;"><b>Validity</b></td>
																											<td bgcolor="#A2B2D8" class="bodytextMaroon"
																												style="padding-left: 10px;"><b>No
																													Of Stock</b></td>
																											<td bgcolor="#A2B2D8" class="bodytextMaroon"
																												style="padding-left: 10px;"><b>Cost
																													In $</b></td>
																											<td bgcolor="#A2B2D8" class="bodytextMaroon"
																												style="padding-left: 10px;"><b>Subscribed
																													On</b></td>
																											<td bgcolor="#A2B2D8" class="bodytextMaroon"
																												style="padding-left: 10px;"><b>Index
																													Name</b></td>
																										</tr>

																										<%
																											session.setAttribute("user_sub", t.getUseraccountinfo());
																										%>
																										<logic:iterate id="temp"
																											property="useraccountinfo" name="tempform1">
																											<tr>
																												<!--  <td height="22" bgcolor="#D9DFED" class="bodytextMaroon" style="padding-left:10px;">
                                              <html:multibox name="temp" property="subscriprion_id_list" >
												<bean:write name="temp" property="subid"  />
												</html:multibox> -->
																												<td height="22" bgcolor="#D9DFED"
																													class="bodytextMaroon"
																													style="padding-left: 10px;"><html:multibox
																														name="temp" property="order_id_list">
																														<bean:write name="temp" property="orderid" />
																													</html:multibox></td>
																												<td bgcolor="#D9DFED" class="bodytextMaroon"
																													style="padding-left: 10px;"><bean:write
																														name="temp" property="subname" /></td>
																												<td bgcolor="#D9DFED" class="bodytextMaroon"
																													style="padding-left: 10px;"><bean:write
																														name="temp" property="validity" /></td>
																												<td bgcolor="#D9DFED" class="bodytextMaroon"
																													style="padding-left: 10px;"><bean:write
																														name="temp" property="stock" /></td>
																												<td bgcolor="#D9DFED" class="bodytextMaroon"
																													style="padding-left: 10px;"><bean:write
																														name="temp" property="charges" /></td>
																												<td bgcolor="#D9DFED" class="bodytextMaroon"
																													style="padding-left: 10px;"><bean:write
																														name="temp" property="subdate" /></td>

																												<td bgcolor="#D9DFED" class="bodytextMaroon"
																													style="padding-left: 10px;"><a
																													href=<bean:write name="temp" 
                                                 property="path"/>>
																														<bean:write name="temp"
																															property="indexname" />
																												</a></td>


																											</tr>
																										</logic:iterate>

																										<tr>
																											<td>&nbsp;</td>
																										</tr>
																										<!--  chand-->
																										<td></td>
																									</table>
																								</td>
																								<td></td>
																							</tr>



																							<tr>
																								<td></td>
																							</tr>
																							<tr>

																								<td></td>
																							</tr>
																							<tr>
																								<td>&nbsp;&nbsp;&nbsp;<input
																									type="checkbox" checked="true"
																									onclick="return checkCheckBox();"> </input> <a
																									href="javascript: void(0)"
																									onclick="window.open('popup.html','windowname1','width=500,height=500,scrollbars=yes'); 
                                            return false;"><span
																										class="labels">I agree term and
																											conditions</span></a>
																								</td>
																							</tr>
																							<tr>
																								<td>&nbsp;</td>
																							</tr>
																							<tr>
																								<td>&nbsp;&nbsp;&nbsp;&nbsp;<html:image
																										src="../images/cancel.jpg"
																										onclick="return chkForm(this.form)"></html:image>
																									</html:form>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<table width="100%" border="0" cellspacing="0"
																										cellpadding="0">
																										<tr>
																											<td bgcolor="#A2B2D8" height="24"
																												class="bodytextMaroon">
																												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;For
																												Joining More Subscription Click Here</td>
																											<td bgcolor="#A2B2D8"><html:link
																													page="/pages/SubscriptionDetails.jsp">
																													<img src="../images/subscribenow11.JPG"
																														width="100" height="21" border="0" /></img>
																												</html:link></td>

																										</tr>
																									</table>

																								</td>
																							</tr>
																						</table>

																					</td>

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
							</table> <!-- Body here bgcolor="#003063" --></td>
					</tr>
					<tr>
						<td height="100%" align=center bgcolor="#003063"><img
							src="../images/footer-bg.jpg"><img align=center
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

