<%@page import="com.harrier.initializeation.ConnectInit"%>
<%@ page info="Home Page" language="java" errorPage="errors.jsp"
	contentType="text/html;charset=utf-8"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page language="java" import="app.*,java.sql.*,java.util.*"%>
<%@ page import="org.apache.log4j.Logger"%>
<%@ page import="org.apache.log4j.PropertyConfigurator"%>
<%@ page import="org.apache.struts.action.ActionForm"%>
<%@ page language="java" import="app.*"%><%@page
	import="org.apache.log4j.Logger"%>
<%
	Logger log = Logger.getLogger(this.getClass());
	log.info("inside " + this.getClass().getSimpleName() + " page");
%>
<%
	System.out.println("*** IndexComposition ***");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");

	LogonForm form = null;
	if (request.isRequestedSessionIdValid()) {
		form = (LogonForm) session.getAttribute("user");
	}
	if (form == null || (!request.isRequestedSessionIdValid())) {
		response.sendRedirect("userlogintemp.jsp");
	}
%>
<html>
<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Index Composition</title>
<html:base />
</head>
<jsp:useBean id="stockCollection" scope="session"
	class="app.StockDetailsCollection" />
<jsp:useBean id="corporateact" scope="session" class="app.Corporate" />
<jsp:useBean id="indexComposition" scope="session"
	class="app.IndexCompositionForm" />
<jsp:useBean id="newIndexForm" scope="session" class="app.NewIndexForm" />
<jsp:useBean id="pstk" scope="session" class="app.PopFileDialogNewStock" />
<html:errors />
<table border="0" width="100%" cellspacing="0" cellpadding="0"
	height="176">
	<tr>
		<td width="100%" colspan="3" bgcolor="#DEE3EF" height="1"
			align="center">
			<p>
				<font size="4" face="Arial Black">&nbsp;&nbsp; <bean:message
						key="indexcompose.heading" /></font>
		</td>
	</tr>
	<html:form action="/Compute">
		<!--      <input type="hidden" name="indexName" value="<request.getParameter('indexName')%>")>-->
		<jsp:setProperty name="indexComposition" property="user_id"
			value='<%=session.getAttribute("userid").toString()%>' />
		<%
			String userId = session.getAttribute("userid").toString();
				System.out.println("User Id ****" + userId);
				String isIndexIsChildOrCustomized = newIndexForm
						.getB_isIndexIsChildOrCustomized();
				String currency = newIndexForm.getS_baseCurrency();
				String d_baseDate = newIndexForm.getD_baseDate();
				String indexID = newIndexForm.getI_indexID();
				String indexType = newIndexForm.getS_indexType();
				String parentIndex = newIndexForm.getS_parentIndex();

				System.out.println("isIndexIsChildOrCustomized **** "
						+ isIndexIsChildOrCustomized);
				System.out.println("currency **** " + currency);
				System.out.println("d_baseDate **** " + d_baseDate);
				System.out.println("indexID **** " + indexID);
				System.out.println("indexType **** " + indexType);
				System.out.println("parentIndex **** " + parentIndex);
		%>
		<input type="hidden" name="indexName">
		<input type="hidden" name="newIndexName">
		<input type="hidden" name="newIndexID">
		<input type="hidden" name="type">
		<input type="hidden" name="hasParent">
		<html:hidden property="indexType" />
		<input type="hidden" name="addflag">
		<input type="hidden" name="exchangeflag">
		<tr>
			<jsp:setProperty name="indexComposition" property="dbcon"
				value="<%=newIndexForm.getDatabaseConnection()%>" />
			<jsp:setProperty name="indexComposition" property="exchangeflag"
				value="no" />
			<jsp:setProperty name="indexComposition"
				property="b_isIndexIsChildOrCustomized"
				value="<%=newIndexForm.getB_isIndexIsChildOrCustomized()%>" />
			<jsp:setProperty name="indexComposition" property="parentCurrencyId"
				value="<%=newIndexForm.getS_baseCurrency()%>" />
			<jsp:setProperty name="indexComposition" property="baseDate"
				value="<%=newIndexForm.getD_baseDate()%>" />
			<jsp:setProperty name="indexComposition" property="indexId"
				value="<%=newIndexForm.getI_indexID()%>" />
			<jsp:setProperty name="indexComposition" property="indexType"
				value="<%=newIndexForm.getS_indexType()%>" />
			<jsp:setProperty name="indexComposition" property="parentIndexId"
				value="<%=newIndexForm.getS_parentIndex()%>" />
			<jsp:setProperty name="indexComposition" property="operation"
				value="-999" />
			<jsp:setProperty name="indexComposition" property="fromCreate"
				value="no" />
			<logic:present scope="request" parameter="fromimportfile">
				<logic:equal value="sudhir" scope="request"
					parameter="fromimportfile">

					<jsp:setProperty name="indexComposition" property="importHashtable"
						value="<%=pstk.getTargethashtable()%>" />

					<script>
						document.forms[0].submit();
					</script>
				</logic:equal>
			</logic:present>
			<%
				String lettersort = request.getParameter("letter");

					System.out.println("Letter sort *** " + lettersort);
					log.info("lettersort is " + lettersort);
			%>
			<jsp:setProperty name="indexComposition" property="letter"
				value="<%=lettersort%>" />


			<logic:equal value="create" scope="request" parameter="from">
				<jsp:setProperty name="indexComposition" property="fromCreate"
					value="yes" />
			</logic:equal>
			<logic:notEqual value="1" name="newIndexForm"
				property="b_isIndexIsChildOrCustomized">
				<td width="28%" align="center" bgcolor="#DEE3EF" height="20">
					<p align="left">
						<font face="Arial" size="2">&nbsp;<bean:message
								key="indexcompose.mkt.excg" /></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <font
							face="Arial" size="1"> <html:select property="exchange"
								onchange="testexchg()">
								<html:optionsCollection name="indexComposition"
									property="exchangeCollection" />
							</html:select> &nbsp;
						</font>
					</p>
				</td>
			</logic:notEqual>
			<logic:equal value="1" name="newIndexForm"
				property="b_isIndexIsChildOrCustomized">
				<td width="28%" align="left" bgcolor="#DEE3EF" height="20"><h4>
						<bean:message key="indexcompose.parentcomposition" />
					</h4></td>
			</logic:equal>
			<td width="28%" align="center" bgcolor="#DEE3EF" height="23"></td>
			<td width="44%" align="center" bgcolor="#DEE3EF" height="23">
				<p>
					<font face="Arial" size="2"><bean:message
							key="indexcompose.indexname" /> : </font> <font face="Arial" size="2"
						color="red"> <bean:write name="newIndexForm"
							property="s_indexName" />
					</font>
				</p>
			</td>
			<font face="Arial" size="2" color="red"></font>
			<td width="56%" bgcolor="#DEE3EF" height="23" />
			<td width="44%" bgcolor="#DEE3EF" height="23" />
		</tr>
		<tr>
			<td width="56%" bgcolor="#DEE3EF" height="19" colspan="2">
				&nbsp;</td>
			<td width="44%" bgcolor="#DEE3EF" height="19">&nbsp;</td>
		</tr>
		<tr border="1">
			<td width="159%" colspan="3" bgcolor="#DEE3EF" height="1">
				<p align="center">
					&nbsp; <input type="submit"
						value="<bean:message key='indexcompose.addbutton'/>" name="add"
						onclick="return test1()" /> <input type="submit"
						value="<bean:message key='indexcompose.removebutton'/>"
						name="remove" onclick="return test2()" /> <input type="submit"
						value="<bean:message key='indexcompose.refreshbutton'/>"
						name="Refresh" onclick="return test3()" /> <br>
		</tr>
		<tr>
			<td width="100%" colspan="3" bgcolor="#DEE3EF" height="19">
				<table border="1" cellpadding="0" cellspacing="0"
					style="border-collapse: collapse" bordercolor="#111111"
					width="100%" id="AutoNumber1">
					<tr>
						&nbsp;
						<p align="center">
							<%
								char letter1 = '\0';
									System.out.println("letter1****" + letter1);
									for (int m = 65; m <= 90; m++) {
										letter1 = (char) m;
										String strletter1 = new Character(letter1).toString();
										String strletter = "../pages/IndexComposition.jsp?letter="
												+ strletter1;
										System.out.println("strletter****" + strletter);
							%>
							<a href="<%=strletter%>"
								onmouseover="window.status='';return true">&nbsp;&nbsp;<%=letter1%></a>
							<%
								}
							%>
							</br>&nbsp;
					</tr>
					<tr>
						<td width="7%" align="center" bgcolor="#314573"><input
							type="checkbox" name="stockid" onclick="CheckAll(this)" /></td>
						<td width="24%" align="center" bgcolor="#314573"><font
							size="1" face="Arial Black" color="white">Stock Name</font></td>
						<td width="9%" align="center" bgcolor="#314573"><font
							size="1" face="Arial Black" color="white">IWF</font></td>
						<td width="9%" align="center" bgcolor="#314573"><font
							size="1" face="Arial Black" color="white">LTP</font></td>
						<td width="8%" align="center" bgcolor="#314573"><font
							size="1" face="Arial Black" color="white">Currency</font></td>
						<td width="11%" align="center" bgcolor="#314573"><font
							size="1" face="Arial Black" color="white">Tis</font></td>
						<td width="11%" align="center" bgcolor="#314573"><font
							size="1" face="Arial Black" color="white">Market Lot</font></td>
						<td width="11%" align="center" bgcolor="#314573"><font
							size="1" face="Arial Black" color="white">Date</font></td>
					</tr>
					<%
						String letter = request.getParameter("letter");
							System.out.println("****Letter******" + letter);
					%>
					<%=new FillTables().addRowsInTable(
						indexComposition.sourceTable,
						(request.getParameter("letter")))%>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%" colspan="3" bgcolor="#DEE3EF" height="19">
				<p align="center">&nbsp;</p>
			</td>
		</tr>
		<tr>
			<td width="159%" colspan="3" bgcolor="#DEE3EF" height="1">
				<p align="center">
					<html:hidden property="operation" />
					<input type="submit"
						value="<bean:message key='indexcompose.addbutton'/>" name="add"
						onclick="return test1()" /> <input type="submit"
						value="<bean:message key='indexcompose.removebutton'/>"
						name="remove" onclick="return test2()" /> <input type="submit"
						value="<bean:message key='indexcompose.refreshbutton'/>"
						name="Refresh" onclick="return test3()" />
		</tr>
		<tr border="1">

			<font color="red" face="Courier New" size="3">&nbsp;&nbsp;&nbsp;</font>

		</tr>

		<tr>
			<td width="100%" colspan="3" bgcolor="#DEE3EF" height="99">
				&nbsp;
				<table border="1" width="100%" bordercolorlight="#000000"
					cellspacing="0" cellpadding="0" bordercolordark="#000000">
					<tr>
						<td width="100%">
							<table border="1" width="100%" cellspacing="0" cellpadding="0">
								<tr>
									<td width="5%" align="center" bgcolor="#314573"><input
										type="checkbox" name="stockid1" onclick="CheckAll2(this)" /></td>
									<td width="15%" align="center" bgcolor="#314573"><font
										size="1" face="Arial" color="white">Stock Name</font></td>
									<td width="7%" align="center" bgcolor="#314573"><font
										size="1" face="Arial" color="white">IWF</font></td>
									<td width="9%" align="center" bgcolor="#314573"><font
										size="1" face="Arial" color="white">LTP</font></td>
									<td width="8%" align="center" bgcolor="#314573"><font
										size="1" face="Arial" color="white">Mkt. Lot</font></td>
									<td width="8%" align="center" bgcolor="#314573"><font
										size="1" face="Arial" color="white">Currency</font></td>
									<td width="13%" align="center" bgcolor="#314573"><font
										size="1" face="Arial" color="white">Outstanding</font></td>
									<td width="11%" align="center" bgcolor="#314573"><font
										size="1" face="Arial" color="white">Mkt. Capital <br>(in
											Index Curr.)
									</font></td>
									<td width="10%" align="center" bgcolor="#314573"><font
										size="1" face="Arial" color="white">Adjusted Mkt<br>(in
											Index Curr.)
									</font></td>
									<td width="26%" align="center" bgcolor="#314573"><font
										size="1" face="Arial" color="white">Weight age</font></td>
								</tr>
								<%=indexComposition.getDestinationtableString()%>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<html:hidden property="mcap_iwf_check" />
		<logic:equal value="yes" property="mcap_iwf_check"
			name="indexComposition">
			<%
				indexComposition.setMcap_iwf_check("no");
			%>
			<script>
				alert("Can Not Select Stock With IWF Less Than 1.0 for Market Cap. Index");
			</script>
		</logic:equal>
		<tr>
			<td width="100%" bgcolor="#DEE3EF" height="40" colspan="3">
				<table border="0" width="100%">
					<tr>
						<td width="41%">
							<table border="0" width="100%">
								<tr>
									<logic:equal value="on" property="s_applyClassification"
										name="newIndexForm">
										<jsp:setProperty name="indexComposition"
											property="applyclassification" value="y" />
										<td width="100%" align="left" colspan="2"><html:checkbox
												property="checktocreatechild" /> <bean:message
												key="indexcompose.parentbasevalue" /></td>
									</logic:equal>
									<logic:notEqual value="on" name="newIndexForm"
										property="s_applyClassification">
										<jsp:setProperty name="indexComposition"
											property="applyclassification" value="n" />
										<td width="100%" align="left" colspan="2"></td>
									</logic:notEqual>
								</tr>
								<tr>
									<html:checkbox property="marked" name="indexComposition"
										onclick="mark();"> Check for Compliance </html:checkbox>
								</tr>
								<tr>
									<td width="28%" align="right"><input type="submit"
										value="<bean:message key='indexcompose.backbutton'/>"
										name="B3" style="float: left" onclick="testback()" /></td>
									<td width="42%" align="left"><html:button
											property="cmpbutton" style="float: left"
											onclick="compliance();">
											<bean:message key="cmp.button" />
										</html:button></td>
									<td width="72%" align="left"><input type="submit"
										value="<bean:message key='indexcompose.computebutton'/>"
										name="B3" style="float: left" onclick="return test4()" /></td>
								</tr>
							</table>
						</td>
						<td width="65%" align="right">
							<table border="0" width="100%">
								<tr>
									<td width="100%" align="right" nowrap="nowrap"><p>
											<font size="1" face="Arial"><bean:message
													key="indexcompose.noofstocks" /> <html:text
													name="indexComposition" property="totalStocks" size="6"
													readonly="true" /> </font>&nbsp;&nbsp;&nbsp; <font size="1"
												face="Arial"><bean:message
													key="indexcompose.total.mkt.cap" /> <html:text
													name="indexComposition" property="totalMCV" size="10"
													readonly="true" /></font>&nbsp; <font face="Arial" size="2">
												<bean:write name="newIndexForm" property="index_currency" />&nbsp;
											</font><font face="Arial" size="1"><bean:message
													key="indexcompose.rs.incrores" /> </font>
										</p></td>
								</tr>

								<!--    <tr>
                  <td width="100%" align="right"><input type="button" value="Browse..." name="B3">&nbsp;
           	   <font size="1" face="Arial"><input type="text" name="T1" size="6"></font>&nbsp;
                <html:link page="/pages/ImportNewStock.jsp" paramId="from" paramProperty="from" paramName="indexComposition"><bean:message key="indexcompose.importfromfile"/></html:link></td>
                </tr> 
                -->
							</table>
						</td>
					</tr>
				</table>
				<p align="right">&nbsp;
			</td>
		</tr>
		<tr>

			<%
				app.IndexCompositionForm idf = new app.IndexCompositionForm();
					idf.getTabledata();
			%>


			<table id="hiddenTable" border="0" width="200" cellpadding="2"
				cellspacing="0" align="left">
				<tr>
					<td>
						<table class="sorted" ID="sortTable" border="1" align="center"
							cellpadding="2" cellspacing="0" bgcolor="white">
							<thead>
								<tr bgcolor="#993300" style="color: white">


									<th align="center" nowrap="nowrap" id="stockID"
										class="gridStyle-header"><span><b>Stock_id</b></span></th>
									<th align="center" nowrap="nowrap" id="stockName"
										class="gridStyle-header"><span><b>Stock_name</b></span></th>

								</tr>
							</thead>
							<tbody>

								<logic:iterate id="cmpbean" name="indexComposition"
									property="tabledata">


									<tr>
										<td width="40%" nowrap="nowrap" align="left"
											class="gridStyle-odd" axis="number" headers="stockID"
											title='<bean:write name="cmpbean" property="stockID"/>'>
											<font face="Verdana" color="blue" size="2"> <bean:write
													name="cmpbean" property="stockID" />
										</font>
										</td>
										<td width="40%" nowrap="nowrap" align="right"
											class="gridStyle-odd" axis="number" headers="stockName"
											title='<bean:write name="cmpbean" property="stockName"/>'>
											<font face="Verdana" color="blue" size="2"> <bean:write
													name="cmpbean" property="stockName" />
										</font>
										</td>

									</tr>

								</logic:iterate>
							</tbody>
						</table>
					</td>
				</tr>
			</table>

			<html:hidden property="hv1" value="no" />
	</html:form>

	<script>
		var object = eval('document.forms[0].checktocreatechild');
		object.checked = true;
	</script>


	<p align="right">&nbsp;
</table>
<table border="0" width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td width="2%"></td>
		<td width="98%"></td>
	</tr>
</table>
<script language="javascript">
	var man1 = document.getElementById("hiddenTable");
	man1.style.display = "none";

	function test1() {
		document.forms[0].submit();
	}
	function compliance() {

		var sOption = "toolbar=yes,location=no,directories=yes,menubar=yes,";
		sOption += "scrollbars=yes,left=50,top=25";
		while (document.getElementById("hv1") == "no") {
			var man2 = document.getElementById("hiddenTable");
			man2.style.display = "none";
			var hv1 = document.getElementById("hv1");
		}

		var winprint = window.open("", "", sOption);
		winprint.document.open();
		winprint.document.write('<html><body>');
		winprint.document
				.write('<font face="Arial"  color="blue" size="3" ><b><marquee>Stocks Satisfying Compliance Criteria </marquee> </b></font>');
		winprint.document.write('<br>');
		winprint.document.write('&nbsp;&nbsp;&nbsp;');
		winprint.document.write('&nbsp;&nbsp;&nbsp;');
		winprint.document.write('<br>');
		var moreDet = document.getElementById('hiddenTable').innerHTML;
		winprint.document.write(moreDet);

		winprint.document.write('</body></html>');

		winprint.document.close();
		winprint.focus();
		winprint.location.reload(true);
		winprint.focus();

	}

	function test1() {
		document.forms[0].operation.value = "Add";
		document.forms[0].addflag.value = "yes";
		return true;
	}
	function testback() {
		document.forms[0].operation.value = "back";
		document.forms[0].addflag.value = "yes";
		return true;
	}
	function test2() {
		document.forms[0].operation.value = "Remove";
		return true;
	}
	function test3() {
		document.forms[0].operation.value = "Refresh";
		return true;
	}
	function test4() {
		document.forms[0].operation.value = "Submit";

		if (false) {
			if (!(object == true)) {
				var object = eval('document.forms[0].checktocreatechild.checked');
				window.open('../pages/GetBaseValues.jsp');
			}
		}
		return true;
	}
	function CheckAll(chk) {
		for (var i = 0; i < document.forms[0].elements.length; i++) {
			var e = document.forms[0].elements[i];
			if (e.type == "checkbox" && e.name == "stockid") {
				e.checked = chk.checked;
			}
		}
	}
	function CheckAll2(chk) {
		for (var i = 0; i < document.forms[0].elements.length; i++) {
			var e = document.forms[0].elements[i];
			if (e.type == "checkbox" && e.name == "stockid1") {
				e.checked = chk.checked;
			}
		}
	}

	function testexchg() {
		document.forms[0].exchangeflag.value = "yes";
		document.forms[0].submit();
		return true;
	}
	function charSort() {
		document.forms[0].operation.value = "Refresh";
		return true;
	}
	function isit(inputval) {

		if (isNaN(parseFloat(inputval))) {
			alert("IWF should be a numeric");
			return false;
		} else {
			if (((parseFloat(inputval) < 1.0) || (parseFloat(inputval) == 1.0))
					&& parseFloat(inputval) > 0.0) {
				return true;
			} else {
				alert("IWF should be between 0.0 and 1.0");
				return false;
			}
		}
	}
	function func(sID, uu, mcap) {
		alert("in func");
		alert(sID + ",  " + uu + ",  " + mcap);
		var st = "indexComposition.iwf" + sID;
		alert("st : " + st + "st value : " + st.value);
		var iwf = document.forms[0].st.value;

		alert("text box value: " + iwf);

		var val = parseFloat(uu) * parseFloat(mcap);
		alert("val: " + val);
		//document.forms[0].st.value=val;
	}

	function mark() {
		document.forms[0].submit();
	}
</script>


</html>