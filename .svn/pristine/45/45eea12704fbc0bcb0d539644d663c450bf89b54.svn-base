<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="app.*,java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="javax.swing.*"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="org.jfree.chart.demo.servlet.ComposeIndex"%>
<%@page import="com.harrier.initializeation.ConnectInit"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%><%@page
	import="org.apache.log4j.Logger"%>
<%
	Logger log = Logger.getLogger(this.getClass());
	log.info("inside " + this.getClass().getSimpleName() + " page");
%>
<%
	LogonForm form = null;
	if (request.isRequestedSessionIdValid()) {
		form = (LogonForm) session.getAttribute("user");
	}
	if (form == null || (!request.isRequestedSessionIdValid())) {
		response.sendRedirect("userlogintemp.jsp");
	}
%>
<html>
<%
	System.out.println("***Import page***");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", -1);
	//String val=request.getParameter("D1");

	String from = null, hist_date = null;
	String fromTemp = request.getParameter("from");
	System.out.println("From Start **** " + fromTemp);
	if (request.getParameter("from") != null) {
		session.setAttribute("from1", request.getParameter("from"));
		from = request.getParameter("from");
	}

	if (session.getAttribute("from1") == null) {

		if (request.getParameter("from") == null)
			session.setAttribute("from2", "");
		else
			session.setAttribute("from2", request.getParameter("from"));

	}
	if (request.getParameter("B0") != null) {

	}
	System.out.println("hahahaah **** "
			+ request.getParameter("hist_Date"));
	log.info("hahahaah" + request.getParameter("hist_Date"));
	if (request.getParameter("hist_Date") == null) {
		hist_date = QueryClass.formatDate();
		//out.println("---->"+hist_date);
	} else {
		hist_date = request.getParameter("hist_Date");
		PopFileDialog.setHist_date(request.getParameter("hist_Date"));
		//out.println(hist_date);
	}
%>
<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
<script language="javascript" src="box_ex.js"></script>
<script language="javascript">
	var c2 = new CodeThatCalendar(caldef2);
</script>

<link href="./StyleSheet.css" rel="stylesheet" type="text/css">
<body onload="initialize()">
	<jsp:useBean id="pstk" scope="session"
		class="app.PopFileDialogNewStock" />
	<logic:equal value="ICompose" scope="request" parameter="from">
		<jsp:setProperty name="pstk" property="fromIndexcomposition"
			value="yes" />
		<jsp:setProperty name="pstk" property="D1"
			value='<%=request.getParameter("Exchange")%>' />

	</logic:equal>
	<%
		StringBuffer str = null;

		if (request.getParameter("event") == null) {
			//	session.setAttribute("from1",from);
			str = pstk.display(request);//request,response);	

			if (request.getAttribute("hashtable") != null) {
				Hashtable ht = (Hashtable) request
						.getAttribute("hashtable");
				session.setAttribute("hashtable_session", ht);

				request.removeAttribute("hashtable");
				String url = response
						.encodeURL("IndexCompositionviv.jsp?uname=sudhir");
				String oldfrom = (String) session.getAttribute("from1");
				// 				oldfrom="ICompose";
				// 				System.out.println("oldform *** " + oldfrom);
				//	session.removeAttribute("from1");

				if (oldfrom.trim().equalsIgnoreCase("ICompose")) {
					session.removeAttribute("from1");
	%>
	<jsp:setProperty name="pstk" property="targethashtable" value="<%=ht%>" />
	<jsp:forward page="/pages/IndexComposition.jsp">
		<jsp:param name="fromimportfile" value="sudhir" />
	</jsp:forward>
	<%
		}
			}
		}
		if (request.getParameter("event") != null
				&& request.getParameter("event").equals("Downlode")) {
			//UplodeBhav upl = new UplodeBhav();				
			//str=upl.storeStockbhv();	
			//str=pstk.getFtpFileDisp();	
			str = pstk.display(request);//request,response);				
		}
		try {

			if (request.getParameter("event").equals("Save")) {
				String correctedFile_flag = request
						.getParameter("CorrectedFile");
				System.out.println("correctedFile_flag***"
						+ correctedFile_flag);
				pstk.setCorrectedFile(correctedFile_flag);
				PopFileDialog.setCorrectedFile(correctedFile_flag);

				pstk.setHist_Date(request.getParameter("hist_Date"));
				PopFileDialog.setHist_date(request
						.getParameter("hist_Date"));
				//					log.info("Before Calling StoreStock()");
				if (from != null && (from.equals("ICompose"))) {
					String indcomposition = "/pages/IndexCompositionviv.jsp?";//+table;
					String url = response.encodeURL(indcomposition);
	%>
	<jsp:forward page="<%=url%>" />
	<%
		}
				System.out.println("Histdate Jsp page*** "
						+ request.getParameter("hist_Date"));
				str = pstk.storeStock();

			}
		} catch (Exception e) {
			System.out.println("Excepton *** "+e.getMessage());
		}
	%>

	<table class="gridStyle">
		<tr>
			<form action="./ImportNewStock.jsp">
				<td class="tab"><bean:message key="Masters.SelectExch" /></td>
				<td align="left" class="tab"><select size="1" name="D1">
						<option value="0"><bean:message key="Masters.SelectExch" /></option>
						<%
							int requestID = 0;
							try {
								requestID = Integer.parseInt(request.getParameter("D1"));
								System.out.println("Request id 1111***" + requestID);
							} catch (Exception e) {
							}
							//    org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();
							ComposeIndex ci = ConnectInit.getComposeIndex();
							String var;
							var = request.getParameter("D1");
							System.out.println("Request id var ***" + var);
							if (var == null) {
								var = ci.getDefaultExchange();
								System.out.println("If var == null ***" + var);
							}
							try {
								requestID = Integer.parseInt(var);
								System.out.println("Request id 222***" + requestID);
							} catch (Exception e) {
								log.info(e);
							}
							ci.setVector_exchangelist();

							pstk.setStock_exid(var);
							Vector vi = ci.getVector_exchangelist();
							Iterator i = vi.iterator();
							int id;
							while (i.hasNext()) {
								id = Integer.parseInt(i.next().toString());
								System.out.println("id in while ** " + id);
								System.out.println("requestID in while ** " + requestID);

								if (id == requestID) {
									pstk.getStockExchangeDetails(id);
						%>
						<option selected value="<%=id%>"><%=i.next()%></option>
						<%
							} else {
						%>
						<option value="<%=id%>"><%=i.next()%></option>
						<%
							}
							}
						%>
				</select></td>
		</tr>
		<tr>
			<td class="tab"><bean:message key="Admin.SelFile" /></td>
			<td align="left" class="tab">
				<%
					if (from != null && from.equals("ICompose")) {
				%> <select size="1" name="D2" onchange="testexchgicompose()">
					<%
						} else {
					%>
					<select size="1" name="D2" onchange="testexchg()">
						<%
							}
						%>
						<option value="0"><bean:message key="Admin.SelFile" /></option>
						<%
							int requestID1 = 0;
							try {
								requestID1 = Integer.parseInt(request.getParameter("D2"));
								System.out.println("Request ID 1 **" + requestID1);

							} catch (Exception e) {
							}
							pstk.setVector_importfilelist();
							String var1;
							var1 = request.getParameter("D2");
							System.out.println("var1**" + var1);
							if (var1 != null) {
								pstk.setFile_type(var1);
							}
							Vector vi1 = pstk.getVector_importfilelist();
							Iterator i1 = vi1.iterator();
							String fileselected = "";

							int id1;
							/*Code modified by samiksha*/
							while (i1.hasNext()) {
								id1 = Integer.parseInt(i1.next().toString());
								if (id1 == requestID1) {
						%><option selected="selected" value="<%=id1%>"><%=i1.next()%></option>
						<%
							} else {
						%><option value="<%=id1%>"><%=i1.next()%></option>
						<%
							}

							}
						%>
						<!-- Previous code -->
						<%-- while (i1.hasNext()) {
								id1 = Integer.parseInt(i1.next().toString());
								System.out.println("id1****" + id1);
								if (id1 == requestID1) {
									System.out.println("inside while id1 equals requestid1****"
											+ i1.next());
						%>
						<option selected value="<%=id1%>"><%=i1.next()%></option>

						<%
							} else {
						%>
												<option value="<%=id1%>"><%=i1.next()%></option>
						<%
							}
							}
						%> --%>
				</select>
					<!--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-->
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="hidden" name="Go" value="Go">
			</td>
			<%
				String temp = request.getParameter("from");
				System.out.println("From for hidden field **** " + temp);
			%>
			<input type="hidden" name="from"
				value='<%=request.getParameter("from")%>'>

		</tr>
		<%
			if ((request.getParameter("D2") != null && request.getParameter(
					"D2").equals("2"))
					|| (request.getParameter("D2") != null && request
							.getParameter("D2").equals("10"))
					|| (request.getParameter("D2") != null && request
							.getParameter("D2").equals("15"))
					|| (request.getParameter("D2") != null && request
							.getParameter("D2").equals("19"))) {
		%>
		<tr>
			<td class="tab"><bean:message key="Admin.SelDate" /></td>

			<%
// 				System.out.println("Hist date *** "
// 							+ request.getParameter("hist_Date"));

// 					if (request.getParameter("hist_Date") == null) {
			%>
			<td align="left" class="tab"><input type="text" name="hist_Date"
				id="hist_Date" size="14" /> <%
//  	} else {
 %>
<!-- 			<td align="left" class="tab"><input type="text" name="hist_Date" -->
<!-- 				id="hist_Date" size="14" -->
<%-- 				value='<%=request.getParameter("hist_Date")%>' /> --%>
				 <%
//  	}
 %> <input onclick="c2.popup('hist_Date');" type="button" value="..." /></td>


		</tr>
		<%
			} else {
				System.out.print("Please Select other file");
			}
		%>

		<tr>

			<%
				System.out.println("Set hisdate in pstl ***"
						+ request.getParameter("hist_Date"));
				//condition is applied by samiksha
				if (hist_date != null) {
					pstk.setHist_Date(hist_date);
				} else {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					//get current date time with Date()
					Date date = new Date();
					System.out.println("Hist date by curent date ***"
							+ dateFormat.format(date));
					pstk.setHist_Date(dateFormat.format(date));
				}
				// 				pstk.setHist_Date("30-08-2016");
				if (request.getParameter("D2") != null
						&& request.getParameter("D1") != null) {
					PopFileDialog.setFiletype(request.getParameter("D2"));
					PopFileDialog.setExchangeid(request.getParameter("D1"));
					//condition is applied by samiksha
					if (hist_date != null) {
						PopFileDialog.setHist_date(request
								.getParameter("hist_Date"));
					} else {
						DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						//get current date time with Date()
						Date date = new Date();
						System.out.println("Hist date by curent date ***"
								+ dateFormat.format(date));
						PopFileDialog.setHist_date(dateFormat.format(date));
					}
					// 					PopFileDialog.setHist_date("30-08-2016");
				}
			%>
			</form>
			<td class="tab"><bean:message key="Admin.AttachFile" /></td>
			<td class="tab" align="left">
				<p style="margin-left: 0">
				<form ENCTYPE="multipart/form-data" action="fileUploadNewStock.jsp"
					method="POST">
					<input type="file" name="file1" align="left">
			</td>

			<%
				if ((request.getParameter("D1") != null)
						&& (request.getParameter("D1").equals("84"))
						&& (((request.getParameter("D2") != null) && (request
								.getParameter("D2").equals("2")))
								|| ((request.getParameter("D2") != null) && (request
										.getParameter("D2").equals("4")))
								|| ((request.getParameter("D2") != null) && (request
										.getParameter("D2").equals("16")))
								|| ((request.getParameter("D2") != null) && (request
										.getParameter("D2").equals("17")))
								|| ((request.getParameter("D2") != null) && (request
										.getParameter("D2").equals("18"))) || ((request
								.getParameter("D2") != null) && (request
								.getParameter("D2").equals("15"))))) {
					String temp1 = request.getParameter("from");
					System.out.println("From 111 **** " + temp1);
					String url1 = response
							.encodeURL("/Stockpile/pages/ImportNewStock.jsp?event=Downlode&from="
									+ request.getParameter("from")
									+ "&D1="
									+ request.getParameter("D1")
									+ "&D2="
									+ request.getParameter("D2")
									+ "&hist_Date="
									+ hist_date);
			%>

			<td align="right" class="tab"><a href="<%=url1%>"
				onmouseover="window.status='go!!';return true"><bean:message
						key="Admin.Download" /></a></td>
			<%
				}
			%>
			<td class="tab"><br></td>
		</tr>
		<tr>
			<td class="tab"><br></td>
			<%
				String temp0 = request.getParameter("from");
				System.out.println("Temp URL save event 111**** " + temp0);
				String url = response
						.encodeURL("/Stockpile/pages/ImportNewStock.jsp?event=Save&from="
								+ request.getParameter("from")
								+ "&D1="
								+ request.getParameter("D1")
								+ "&D2="
								+ request.getParameter("D2")
								+ "&hist_Date="
								+ hist_date);
				System.out.println("URL save event 111*** " + url);
			%>
			<td align="left" class="tab"><input type="submit"
				value='<bean:message key="Admin.Show"/>' name="B0" onclick="test3()" />
				<!--onclick="return test()"/>--> <input type="reset"
				value='<bean:message key="Admin.Cancel"/>' name="B1"> <input
				type="button" value='<bean:message key="corporate.Back"/>' name="B1"
				onclick="history.back(-1)"> <%
 	if (request.getParameter("D2") != null
 			&& (request.getParameter("D2").equals("2") || request
 					.getParameter("D2").equals("10"))) {
 		//boolean samedateprice=pstk.isCheckPriceOnDate();
 		boolean samedateprice = pstk.CheckForPricesOnSameDate(request
 				.getParameter("D1"));

 		if (samedateprice == true) {

 			url = response
 					.encodeURL("/Stockpile/pages/ImportNewStock.jsp?event=Save&from="
 							+ request.getParameter("from")
 							+ "&CorrectedFile=on"
 							+ "&D1="
 							+ request.getParameter("D1")
 							+ "&D2="
 							+ request.getParameter("D2")
 							+ "&hist_Date=" + hist_date);
 			System.out.println("URL save event 222*** " + url);
 %> &nbsp;&nbsp;<a href="<%=url%>"
				onmouseover="window.status='go!!';return true"
				onclick="return checkprice();"><bean:message key="Admin.save" /></a>
				<%
					if (request.getParameter("event") != null
									&& request.getParameter("event").equals("Save")
									&& (request.getParameter("D2").equals("2") || request
											.getParameter("D2").equals("10"))) {
				%> <%
 	String url_compute = response
 						.encodeURL("/Stockpile/pages/IndexHome.jsp");
 %> &nbsp;&nbsp;<a href="<%=url_compute%>"
				onmouseover="window.status='go!!';return true">Compute</a></td>
		</tr>
		<%
			}
		%>
		<tr>
			<td align="left" class="tab">
				<p>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="checkbox"
						name="CorrectedFile" value="" onclick="Filecorrected();" />
					Corrected&nbsp;File
				</p>
			</td>
		</tr>
		<%
			} else {
		%>
		&nbsp;&nbsp;
		<a href="<%=url%>" onmouseover="window.status='go!!';return true"><bean:message
				key="Admin.save" /></a>
		<%
			if (request.getParameter("event") != null
							&& request.getParameter("event").equals("Save")
							&& (request.getParameter("D2").equals("2") || request
									.getParameter("D2").equals("10"))) {
		%>
		<%
			String url_compute = response
								.encodeURL("/Stockpile/pages/IndexHome.jsp");
		%>

		&nbsp;&nbsp;
		<a href="<%=url_compute%>"
			onmouseover="window.status='go!!';return true">Compute</a>

		<%
			}
		%>
		<%
			}
		%>
		<%
			} else {
		%>
		&nbsp;&nbsp;
		<a href="<%=url%>" onmouseover="window.status='go!!';return true"><bean:message
				key="Admin.save" /></a>
		<%
			if (request.getParameter("event") != null
						&& request.getParameter("event").equals("Save")
						&& (request.getParameter("D2").equals("2") || request
								.getParameter("D2").equals("10"))) {
		%>
		<%
			String url_compute = response
							.encodeURL("/Stockpile/pages/IndexHome.jsp");
		%>

		&nbsp;&nbsp;
		<a href="<%=url_compute%>"
			onmouseover="window.status='go!!';return true">Compute</a>

		<%
			}
		%>
		<%
			}
		%>
		<tr>
			<td class="tab"><br></td>
		</tr>
	</table>
	<table cellSpacing="0" cellPadding="2" width="100%" class="gridStyle"
		border="1">
		<%
			try {
				if (str != null) {
		%>
		<%=str.toString()%>
		<%
			} else {
		%>
		<tr>
			<td class="tab"></td>
		</tr>
		<%
			}
			} catch (Exception e) {
			}
		%>

	</table>

	<!-- 	<div -->
	<!-- 	> -->
	<!-- 	<H1>Display csv file data</H1> -->

	<!-- 	</div> -->
</body>
<script language="javascript">
	function Filecorrected() {
		// 		alert(" in Filecorrected ");
		document.forms[0].corrFile.value = "on";
		alert(document.forms[0].correctedFile.value);
	}
	function checkprice() {
		//alert("in price check on date");
		var conVal = confirm('Prices Exist For Date. Are You Sure To Overwrite Them?');
		if (conVal == true) {
			return true;
		} else {
			return false;
		}
		return true;
	}
	function test4() {
		document.forms[0].hidvar.value = "yes";
		document.forms[0].submit();
	}
	function test3() {

		document.forms[0].from.value = document.forms[0].from.value;
		document.forms[0].hist_Date.value = document.forms[0].hist_Date.value;
		var hist = document.forms[0].hist_Date.value;
		document.forms[0].submit();

	}
	function test2() {
		document.forms[0].from.value = document.forms[0].from.value;

		//	document.forms[0].submit();
		var objTo = document.forms[0].from;
		if ((checkdatecurrent(objTo)) == false) {
			alert("ToDate should be Less Than CurrentDate.");
			objTo.focus();
			objTo.select();
			return false;
		} else
			return true;

	}
	function testexchg() {
		document.forms[0].submit();
		document.forms[0].Go.value = "Go";
		return true;
	}
	function test() {
		alert("In script for compute");
		document.forms[0].submit();
		document.forms[0].B3.value = "Compute";
		alert("after setting value for copute" + document.forms[0].B3.value);
		return true;
	}
	function testexchgicompose() {
		alert("testexchgicompose()");
		//document.forms[0].from.value=document.forms[0].from.value; 	 
		document.forms[0].submit();
		document.forms[0].Go.value = "Go";
		return true;
	}

	function checkdatecurrent(objName) {
		var datefield = objName;
		var strMonth;
		var strYear;
		var strDate;
		var strDay;
		var strDateArray;
		var intElement;
		var strSeparatorArray = new Array("-", " ", "/", ".");
		strDate = datefield.value;
		var intday;
		var int_td;
		var int_mnth;
		var int_yr;
		var int_month;
		var intYear;
		var today = new Date();
		var td = today.getDate();
		var mnth = today.getMonth();
		mnth = mnth + 1;
		var yr = today.getFullYear();
		int_td = parseInt(td, 10);
		int_mnth = parseInt(mnth, 10);
		int_yr = parseInt(yr, 10);
		for (intElement = 0; intElement < strSeparatorArray.length; intElement++) {
			if (strDate.indexOf(strSeparatorArray[intElement]) != -1) {
				strDateArray = strDate.split(strSeparatorArray[intElement]);
				if (strDateArray.length != 3) {
					err = 1;
					alert(" DateArray length < 1: err :" + err);
					return false;
				} else {
					strDay = strDateArray[0];
					strMonth = strDateArray[1];
					strYear = strDateArray[2];
				}
			}
		}
		intday = parseInt(strDay, 10);
		int_month = parseInt(strMonth, 10);
		intYear = parseInt(strYear, 10);
		if (intYear > int_yr) {
			return false;
		}
		if ((intYear == int_yr) && (int_month > int_mnth)) {
			return false;
		}
		if ((intYear == int_yr) && (int_month == int_mnth) && (intday > int_td)) {
			return false;
		} else {
			return true;
		}
	}
	function checkdate() {
		var objTo = document.forms[0].hist_Date;
		if ((checkdatecurrent(objTo)) == false) {
			alert("ToDate should be Less Than CurrentDate.");
			objTo.focus();
			objTo.select();

			var today = new Date();
			var td = today.getDate();
			if (td < 10)
				td = "0" + td;
			var mnth = today.getMonth();
			mnth = mnth + 1;
			if (mnth < 10)
				mnth = "0" + mnth;
			var yr = today.getFullYear();
			var s = "-";

			document.forms[0].hist_Date.value = td + s + mnth + s + yr;

			return false;
		} else
			return true;
	}
	function initialize() {
		var today = new Date();
		var td = today.getDate();
		if (td < 10)
			td = "0" + td;
		var mnth = today.getMonth();
		mnth = mnth + 1;
		if (mnth < 10)
			mnth = "0" + mnth;
		var yr = today.getFullYear();
		var s = "-";
		if ((document.forms[0].hist_Date.value) == "")
			document.forms[0].hist_Date.value = td + s + mnth + s + yr;

	}
</script>
</html>

