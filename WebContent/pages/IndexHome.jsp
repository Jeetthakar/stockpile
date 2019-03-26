
<%@page import="com.harrier.initializeation.ConnectInit"%>
<%@page import="org.jfree.chart.demo.servlet.ComposeIndex"%><%@ taglib
	uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="app.*,java.util.*"%>
<%@page import="org.apache.log4j.Logger"%>
<%
	Logger log = Logger.getLogger(this.getClass());
	log.info("inside " + this.getClass().getSimpleName() + " page");
%>
<%
	new QueryClass().changeSession(request);
%>
<%
	System.out.println("InDEX page");
	LogonForm form = null;
	boolean flag = false;
	if (request.isRequestedSessionIdValid()) {
		form = (LogonForm) session.getAttribute("user");
		Vector uname = new Vector();
		//	AcessControl asc=new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		String locale = session.getAttribute("locale").toString();
		asc.setLocale(locale);
		uname = asc.getUseActivitiesId(form);
		flag = asc.HasAcess("2", uname);
	}
	if (form == null || (!request.isRequestedSessionIdValid())) {
		response.sendRedirect("userlogintemp.jsp");
	}
	System.out.println("D1111 ****** "+request.getParameter("D1"));
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
<title>CA</title>
<script language="javascript" src="./codethatcalendarstd.js"></script>
<script language="javascript" src="box_ex.js"></script>
<script language="javascript">
	var c2 = new CodeThatCalendar(caldef2);
</script>
</head>
<body>
	<jsp:useBean id="computeIndex" scope="session"
		class="app.ComputeIndexForm" />
	<%
		if (request.getParameter("Message") != null) {
	%>
	<%=computeIndex.getMessages((String) request
						.getParameter("Message"))%>
	<%
		}
	%>

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
			<td align="left" valign="top"><br>
				<P align="center" class="subHeader">
					<b> <bean:message key="Admin.IndexComputation" /></b>
				</p> <br> <html:form action="/ComputeIndex">
					<html:errors />
					<input type="hidden" name="avoidclosingvalue" />
					<input type="hidden" name="operation" />
					<input type="hidden" name="operation1" />
					<input type="hidden" name="D1"
						value='<%=request.getParameter("D1")%>'>
					<logic:present scope="request" parameter="basedate">
						<jsp:setProperty name="computeIndex" property="fromcomposition"
							value="yes" />
						<%
							if (request.isRequestedSessionIdValid()) {
						%>
						<jsp:setProperty name="computeIndex" property="newIndexForm"
							value='<%=session.getAttribute("newIndexForm")%>' />
						<%
							}

									System.out.println("Index computation Base Date*** "
											+ request.getParameter("basedate"));
									System.out
											.println("Index computation Base nullNewIndexForm*** "
													+ computeIndex.nullNewIndexForm());
									System.out.println("Index computation formatDate*** "
											+ QueryClass.formatDate());
									System.out.println("Index computation newIndexForm*** "
											+ session.getAttribute("newIndexForm"));
									System.out.println("Index computation D11111111111*** "
											+ request.getParameter("D1"));
						%>

						<jsp:setProperty name="computeIndex" property="basedate1"
							value='<%=request.getParameter("basedate")%>' />
					</logic:present>

					<logic:notPresent scope="request" parameter="basedate">
						<jsp:setProperty name="computeIndex" property="fromcomposition"
							value="no" />

					<jsp:setProperty name="computeIndex" property="newIndexForm" 
							value='<%=computeIndex.nullNewIndexForm()%>' /> 
						<jsp:setProperty name="computeIndex" property="basedate1"
							value='<%=QueryClass.formatDate()%>' />
						
					</logic:notPresent>

					<jsp:setProperty name="computeIndex" property="indexid"
						value='<%=request.getParameter("D1")%>' />

					<jsp:setProperty name="computeIndex" property="index"
						value='<%=request.getParameter("D1")%>' />
					<%
						if (request.isRequestedSessionIdValid()) {
					%>
					<jsp:setProperty name="computeIndex" property="user_id"
						value='<%=session.getAttribute("userid").toString()%>' />
					<jsp:setProperty name="computeIndex" property="role_id"
						value='<%=session.getAttribute("role_id").toString()%>' />
					<%
						}
					%>
					<logic:present scope="request" parameter="basedate">
						<jsp:setProperty name="computeIndex" property="computeIndex"
							value="request" />

					</logic:present>

					<jsp:setProperty name="computeIndex" property="closingvalue" />

					<table border="0" width="751" class="girdStyle" height="6">
						<tr>
							<td width="422"></td>
							<td width="43%" align="left" height="2" class="tab">&nbsp;&nbsp;&nbsp;<bean:message
									key="Admin.SelDate" />&nbsp; <html:text readonly="true"
									property="hist_Date" size="14" name="computeIndex" /> <input
								onclick="c2.popup('hist_Date');" type="button" value="..." /></td>
						</tr>

						<tr>
							<td width="422" height="2" class="tab" nowrap="nowrap"><bean:message
									key="Admin.ComputeIndex" />&nbsp;:&nbsp; &nbsp;<html:select
									property="index" onchange="return test2()" name="computeIndex">

									<html:optionsCollection name="computeIndex"
										property="indexcollection" />
								</html:select>&nbsp;&nbsp;&nbsp;&nbsp;</td>


							<td width="43%" align="left" height="2" class="tab"
								nowrap="nowrap">&nbsp;&nbsp;&nbsp;<bean:message
									key="indcurrwise.indValue" /> :&nbsp; <html:text
									property="indexcalculated" readonly="true" size="14"
									style="text-align: Right" />&nbsp;&nbsp; <html:submit
									property="B3" onclick="return checkClose1()">
									<bean:message key="Admin.compute" />
								</html:submit></td>
						</tr>
					</table>
					<logic:equal value="75" name="computeIndex" property="role_id">

						<table border="0" width="840" class="gridStyle" height="8">
							<tr>
								<td width="420" height="1" class="tab"><logic:present
										scope="request" parameter="new">
										<script>
											document.forms[0].index.value = document.forms[0].D1
													.value
										</script>
									</logic:present>

									<p>
										<html:checkbox name="computeIndex" property="b_showChild"
											onclick="return test1()" disabled="true" />
										&nbsp;

										<bean:message key="Admin.ShwChildIndices" />
										&nbsp;&nbsp;
									</p></td>
								<td width="420" align="left" class="tab" height="1">

									<p>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<html:checkbox property="computeall" onclick="return test3()"
											disabled="true" />
										&nbsp;<input type="hidden" name="checktofindclosevalue" />
										<bean:message key="Admin.ComputeAllIndices" />
									</p>
								</td>
								<td width="21" height="1" class="tab">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
							</tr>
						</table>


						<table border="0" width="840" class="gridStyle">
							<tr>
								<td width="420">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
								<td width="420" class="tab">
									<p>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<html:checkbox property="computesame" disabled="true" />
										<bean:message key="Admin.ComputeIndicesOf" />
										<b><font face="Arial" size="2" color="#000000">&nbsp;<bean:message
													key="Admin.same" /></font></b>&nbsp;
										<bean:message key="Admin.type" />
										&nbsp;
									</p>
								</td>
							</tr>
							<tr>
								<td width="420">&nbsp;</td>
								<td width="420" class="tab">

									<p>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<html:checkbox property="computecurrency" disabled="true" />
										<bean:message key="Admin.ComputeAllChildOf" />
										<b> <bean:message key="stockmaster.currency" /></b>
									</p>
								</td>
							</tr>
							<tr>
								<td width="420">&nbsp;</td>
								<td width="420" class="tab">

									<p>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<html:checkbox property="computetotalreturns" disabled="true" />
										<bean:message key="Admin.ComputeAllChildOf" />
										<b>&nbsp;</b><b><bean:message key="Admin.TotReturns" /></b>&nbsp;&nbsp;
									</p>
								</td>
								<td width="17">&nbsp;&nbsp;&nbsp;&nbsp;</td>
							</tr>
							<tr>
								<td width="420">&nbsp;</td>
								<td width="420" class="tab">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:checkbox
										property="close" disabled="true" />&nbsp; <bean:message
										key="Admin.isCloseVal" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <html:checkbox
										property="settlement" disabled="true" />&nbsp;<bean:message
										key="Admin.isSettlementVal" /></td>
							</tr>
						</table>
					</logic:equal>

					<logic:equal value="76" name="computeIndex" property="role_id">

						<table border="0" width="840" class="gridStyle" height="8">
							<tr>
								<td width="420" height="1" class="tab"><logic:present
										scope="request" parameter="new">
										<script>
											document.forms[0].index.value = document.forms[0].D1
													.value
										</script>
									</logic:present>

									<p>
										<html:checkbox name="computeIndex" property="b_showChild"
											onclick="return test1()" />
										&nbsp;

										<bean:message key="Admin.ShwChildIndices" />
										&nbsp;&nbsp;
									</p></td>
								<td width="420" align="left" class="tab" height="1">

									<p>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<html:checkbox property="computeall" onclick="return test3()" />
										&nbsp;<input type="hidden" name="checktofindclosevalue" />
										<bean:message key="Admin.ComputeAllIndices" />
									</p>
								</td>
								<td width="21" height="1" class="tab">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
							</tr>
						</table>


						<table border="0" width="840" class="gridStyle">
							<tr>
								<td width="420">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
								<td width="420" class="tab">
									<p>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<html:checkbox property="computesame" />
										<bean:message key="Admin.ComputeIndicesOf" />
										<b><font face="Arial" size="2" color="#000000">&nbsp;<bean:message
													key="Admin.same" /></font></b>&nbsp;
										<bean:message key="Admin.type" />
										&nbsp;
									</p>
								</td>
							</tr>
							<tr>
								<td width="420">&nbsp;</td>
								<td width="420" class="tab">

									<p>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<html:checkbox property="computecurrency" />
										<bean:message key="Admin.ComputeAllChildOf" />
										<b> <bean:message key="stockmaster.currency" /></b>
									</p>
								</td>
							</tr>
							<tr>
								<td width="420">&nbsp;</td>
								<td width="420" class="tab">

									<p>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<html:checkbox property="computetotalreturns" />
										<bean:message key="Admin.ComputeAllChildOf" />
										<b>&nbsp;</b><b><bean:message key="Admin.TotReturns" /></b>&nbsp;&nbsp;
									</p>
								</td>
								<td width="17">&nbsp;&nbsp;&nbsp;&nbsp;</td>
							</tr>
							<tr>
								<td width="420">&nbsp;</td>
								<td width="420" class="tab">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:checkbox
										property="close" />&nbsp; <bean:message
										key="Admin.isCloseVal" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <html:checkbox
										property="settlement" />&nbsp;<bean:message
										key="Admin.isSettlementVal" /></td>
							</tr>
						</table>
					</logic:equal>

					<logic:equal value="1" name="computeIndex" property="role_id">

						<table border="0" width="840" class="gridStyle" height="8">
							<tr>
								<td width="420" height="1" class="tab"><logic:present
										scope="request" parameter="new">
										<script>
											document.forms[0].index.value = document.forms[0].D1
													.value
										</script>
									</logic:present>

									<p>
										<html:checkbox name="computeIndex" property="b_showChild"
											onclick="return test1()" />
										&nbsp;

										<bean:message key="Admin.ShwChildIndices" />
										&nbsp;&nbsp;
									</p></td>
								<td width="420" align="left" class="tab" height="1">

									<p>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<html:checkbox property="computeall" onclick="return test3()" />
										&nbsp;<input type="hidden" name="checktofindclosevalue" />
										<bean:message key="Admin.ComputeAllIndices" />
									</p>
								</td>
								<td width="21" height="1" class="tab">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
							</tr>
						</table>


						<table border="0" width="840" class="gridStyle">
							<tr>
								<td width="420">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
								<td width="420" class="tab">
									<p>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<html:checkbox property="computesame" />
										<bean:message key="Admin.ComputeIndicesOf" />
										<b><font face="Arial" size="2" color="#000000">&nbsp;<bean:message
													key="Admin.same" /></font></b>&nbsp;
										<bean:message key="Admin.type" />
										&nbsp;
									</p>
								</td>
							</tr>
							<tr>
								<td width="420">&nbsp;</td>
								<td width="420" class="tab">

									<p>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<html:checkbox property="computecurrency" />
										<bean:message key="Admin.ComputeAllChildOf" />
										<b> <bean:message key="stockmaster.currency" /></b>
									</p>
								</td>
							</tr>
							<tr>
								<td width="420">&nbsp;</td>
								<td width="420" class="tab">

									<p>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<html:checkbox property="computetotalreturns" />
										<bean:message key="Admin.ComputeAllChildOf" />
										<b>&nbsp;</b><b><bean:message key="Admin.TotReturns" /></b>&nbsp;&nbsp;
									</p>
								</td>
								<td width="17">&nbsp;&nbsp;&nbsp;&nbsp;</td>
							</tr>
							<tr>
								<td width="420">&nbsp;</td>
								<td width="420" class="tab">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:checkbox
										property="close" />&nbsp; <bean:message
										key="Admin.isCloseVal" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <html:checkbox
										property="settlement" />&nbsp;<bean:message
										key="Admin.isSettlementVal" /></td>
							</tr>
						</table>
					</logic:equal>

					<br>&nbsp;
	 <%
	 	//	org.jfree.chart.demo.servlet.ComposeIndex table=new org.jfree.chart.demo.servlet.ComposeIndex();
	 		ComposeIndex table = ConnectInit.getComposeIndex();
	 %>

					<%=table.getCompositionBuffer(request,
						request.getParameter("D1"))%>
					<br>

					<p align="right"></p>

					<logic:equal value="yes" property="closingvalueerror"
						name="computeIndex">
						<%
							log.info("aftercompute get the value"
											+ computeIndex.getIndex());
						%>
						<jsp:setProperty name="computeIndex" property="index"
							value="<%=computeIndex.getIndex()%>" />
						<script>
							if (confirm("Closing Value already exists. \n Are you sure you want to overwrite it ?? ")) {
								document.forms[0].avoidclosingvalue.value = "Compute";
								document.forms[0].D1.value = document.forms[0].index.value;
								document.forms[0].operation.value = "Compute";
								document.forms[0].submit();
							}
							//closingvaluefunction();
						</script>
					</logic:equal>

					<logic:notEqual value="nothing" property="alertreject"
						name="computeIndex">
						<logic:equal value="reject" property="alertreject"
							name="computeIndex">
							<script>
								var x = document.forms[0].indexval.value;
								var xx = document.forms[0].indexvalcal.value;
								var y = document.forms[0].alertvalue.value;
								var yy = document.forms[0].change.value;
								var z = document.forms[0].indname.value;
								alert("For Index "
										+ z
										+ "\n\n Privious Index value : "
										+ x
										+ "\n\n Index value Calculated : "
										+ xx
										+ "  \n\n Default Rejection percentage : "
										+ y + " \n\n Change in index values : "
										+ yy
										+ " \n\n So the value is ignored\n\n");
							</script>
						</logic:equal>

						<logic:equal value="alert" property="alertreject"
							name="computeIndex">
							<script>
								var x = document.forms[0].indexval.value;
								var y = document.forms[0].rejectvalue.value;
								var xx = document.forms[0].indexvalcal.value;
								var yy = document.forms[0].change.value;
								var z = document.forms[0].indname.value;
								if (confirm("For Index "
										+ z
										+ "         \n\n Privious Index value : "
										+ x
										+ "    \n\n Index value Calculated : "
										+ xx
										+ "  \n\n Default Alert percentage : "
										+ y
										+ " \n\n Change in index values : "
										+ yy
										+ " \n\n  Do you want to save the value\n\n")) {
									document.forms[0].rejectAlert.value = "yes";
									document.forms[0].operation.value = "Compute";
									document.forms[0].submit();
								} else {
									document.forms[0].indexcalculated.value = "---";
								}
							</script>
						</logic:equal>
					</logic:notEqual>

					<html:hidden property="alertvalue" />
					<input type="hidden" name="B2" />
					<html:hidden property="indexval" />
					<html:hidden property="indexvalcal" />
					<html:hidden property="change" />
					<html:hidden property="indname" />
					<html:hidden property="alertreject" />
					<html:hidden property="CAcheck" />
					<input type="hidden" name="combo" value="no" />



				</html:form></td>
		</tr>
	</table>

	<table border="0" width="100%" cellspacing="1" cellpadding="0">
		<tr>
			<td width="28%" valign="top">
				<p></p>
			</td>
			<td width="72%" valign="top">
				<p>
			</td>
		</tr>
	</table>

	<script language="javascript">
		function checkClose1() {
			var val = document.forms[0].CAcheck.value;
			////alert(" in checkClose1 "+val);
			if (val == 'no') {
				//alert("document.forms[0].D1.value "+document.forms[0].D1.value);
				if (document.forms[0].D1.value == "null") {
					alert("Select Index Before Computting");
					return false;
				}
				if (document.forms[0].D1.value != 0) {
					if (confirm("Are You Sure To Compute Index?")) {
						document.forms[0].operation.value = "Compute";
						document.forms[0].submit();
					} else
						return false;
				} else {
					alert("Select Index Before Computting");
					return false;
				}
			}
			if (val == 'yes') {
				if (confirm("Corporate Action applied on index Or it's constituned Stock. \n Do you wish to Continue Computation?? ")) {
					document.forms[0].operation.value = "Compute";
					document.forms[0].submit();
				} else
					return false;
			}
		}
		function test1() {
			document.forms[0].D1.value = document.forms[0].index.value;
			document.forms[0].operation1.value = "Change";
			document.forms[0].submit();
		}
		function testsubmit() {
			document.forms[0].D1.value = document.forms[0].index.value;
			document.forms[0].submit();
		}

		function checkClose() {
			//alert("in checkClose"):
			var x = document.forms[0].closingvalue.value;
			//alert("in checkClose"+x):
			document.forms[0].B2.value = "Compute";
			if (x == "true") {
				if (confirm("Closing Value already exists. \n Are you sure you want to overwrite it ?? ")) {
					document.forms[0].B2.value = "Compute";
					document.forms[0].D1.value = document.forms[0].index.value;
					document.forms[0].submit();
				} else {

				}

			} else {
				document.forms[0].B2.value = "Compute";
				document.forms[0].D1.value = document.forms[0].index.value;
				document.forms[0].indexcalculated.value = " ";
				document.forms[0].submit();
			}
		}
		function test6() {
			document.forms[0].computeall.disabled = false;
			document.forms[0].computesame.disabled = false;
			document.forms[0].computecurrency.disabled = false;
			document.forms[0].computetotalreturns.disabled = false;
			top.treeFrame.location.reload();
		}
		function test2() {
			//alert(document.forms[0].CAcheck.value)
			document.forms[0].D1.value = document.forms[0].index.value;
			document.forms[0].combo.value = "yes";
			document.forms[0].submit();
		}
		function test3() {
			var x = document.forms[0].computeall.checked;
			if (x == true) {
				document.forms[0].computesame.disabled = true;
				document.forms[0].computecurrency.disabled = true;
				document.forms[0].computetotalreturns.disabled = true;

			} else {
				document.forms[0].computesame.disabled = false;
				document.forms[0].computecurrency.disabled = false;
				document.forms[0].computetotalreturns.disabled = false;

			}
		}
		function closingvaluefunction() {
			//alert ("in closingvaluefunction");
			if (confirm("Closing Value already exists. \n Are you sure you want to overwrite it ?? ")) {
				document.forms[0].avoidclosingvalue.value = "Compute";
				document.forms[0].submit();
			}
		}
	</script>
</body>
</html>