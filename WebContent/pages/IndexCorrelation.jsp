
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.ParseException"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Locale,java.util.*"%>
<%@page import="org.jfree.chart.demo.servlet.AdjustDecimal"%>
<%@page import="com.harrier.initializeation.ConnectInit"%>
<%@page import="org.jfree.chart.demo.servlet.ComposeIndex"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="app.*"%>
<%@page import="org.apache.log4j.Logger"%>
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
<head>
<html:base />
</head>
<body onload="initialize()">
	<link href="../pages/StyleSheet.css" rel="stylesheet" type="text/css">
	<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
	<script language="javascript" src="box_ex.js"></script>
	<script language="javascript">
		var c2 = new CodeThatCalendar(caldef2);
	</script>
	<table width="1000" cellspacing="0" cellpadding="0">
		<tr>
			<td width="335" class="subHeader" nowrap="nowrap">&nbsp;</td>
			<td class="subHeader" width="665" align="left" colspan="2"
				nowrap="nowrap"><font size="3" face="Arial"> <b><bean:message
							key="IndexCorrelation.title" /> </b>
			</font></td>
		</tr>
	</table>

	<%
		String fromdate = request.getParameter("from");
		String toDate = request.getParameter("to");
		String[] var = request.getParameterValues("D1");
		Object arr1 = null;
		session.setAttribute("arr1", var);
		String url = "";
		if (var != null) {
			for (int i = 0; i < var.length; i++) {
				url = url + "&D1=" + var[i];
			}
		}
	%>
	<form action="IndexCorrelation.jsp">
		<table width="656">
			<tr>
				<td width="202" nowrap="nowrap" align="right">
					<%
						String chkchecked = request.getParameter("check");
					%> <%
 	String pr = null;
 	pr = request.getParameter("Pr"); // Check if clicked on printer friendly link.
 	try {
 		if (pr.equals(null)) {
 			pr = "N";
 		}
 	} catch (Exception e) {
 		pr = "N";
 	}
 	if (!pr.equals("Y")) {
 %> <font size="2" face="Arial"> <bean:message
							key="indexUpdate.selectIndex" /> : 
				</td>
				<%
					}
				%>
				<%
					//  org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();
					// org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
					ComposeIndex ci = ConnectInit.getComposeIndex();
					AdjustDecimal ad = ConnectInit.getAdjustDecimal();
					org.jfree.chart.demo.servlet.CalculateCorrelation cc = new org.jfree.chart.demo.servlet.CalculateCorrelation();
					ci.setVector_indexlist(chkchecked);
					String str = "";
					if (var != null && var.length > 0) {
						for (int n = 0; n < var.length; n++) {
							log.info(var[n]);
							System.out.println("Var[n] ***** "+var[n]);
						}
					}
					Vector v3 = ci.getVector_indexlist();
					System.out.println("Vector vr size ***** "+v3.size());
					Iterator i1 = v3.iterator();
				%>
				<%
					if (!pr.equals("Y")) {
				%>
				<td width="400" nowrap="nowrap" align="left"><select size="3"
					name="D1" multiple>

						<%
							while (i1.hasNext()) {
									int id1 = Integer.parseInt(i1.next().toString());
									boolean flag = false;
									if (var != null && var.length > 0) {
										flag = ci.getflag(var, id1);
									}
									if (flag == true) {
						%>
						<option selected value="<%=id1%>"><%=i1.next()%></option>
						<%
							} else {
						%>
						<option value="<%=id1%>"><%=i1.next()%></option>
						<%
							}
								}
						%>
				</select> </select></td>
			</tr>
		</table>

		<table width="844">
			<tr>
				<td width="149" align="right" nowrap="nowrap">
					<%
						if ((request.getParameter("check")) != null
									&& (request.getParameter("check")).equals("checked")) {
					%>
					<input type="checkbox" name="check" value="checked"
					onclick="return test1()" checked />&nbsp; <%
 	} else {
 %> <input
					type="checkbox" name="check" value="checked"
					onclick="return test1()" />&nbsp; <%
 	}
 %>
				</td>
				<td width="144" nowrap="nowrap"><font size="2" face="Arial">
						<bean:message key="IndexReturnVolatility.Show" />
				</font></td>
				<td width="69" nowrap="nowrap"><font size="2" face="Arial">
						<bean:message key="corporate.Fdate" />: </td>
				<td width="78" nowrap="nowrap">
					<%
						if (fromdate == null) {
					%><input readOnly name="from" size="10">
					<%
						} else {
					%> <input readOnly name="from" value='<%=fromdate%>'
					size="10"> <%
 	}
 %>
				</td>
				<td width="37" nowrap="nowrap"><input
					onclick="c2.popup('from');" type="button" value="..."></td>
				<td width="58" nowrap="nowrap"><font size="2" face="Arial">
						<bean:message key="corporate.Tdate" />: </td>
				<td width="79" nowrap="nowrap">
					<%
						if (toDate == null) {
					%> <input readOnly name="to" size="10">
					<%
						} else {
					%><input readOnly name="to" value='<%=toDate%>' size="10">
					<%
						}
					%>
				</td>
				<td width="30" nowrap="nowrap"><input onclick="c2.popup('to');"
					type="button" value="..."></td>
				<td width="162" nowrap="nowrap"><input type="submit"
					value='<bean:message key="Reports.View"/>' name="B1"
					onclick="return checkdate()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>

					<%
						}
					%></td>
			</tr>
		</table>
		<br></br>
		<%
			if (var != null) {
				if (!pr.equals("Y")) {
					try {
						String astr = null;
						astr = "./IndexCorrelation.jsp?Pr=Y" + url + "&from="
								+ fromdate + "&to=" + toDate;
						String excel = "../pages/FileDownload.jsp?type=15"
								+ url + "&from=" + fromdate + "&to=" + toDate
								+ "&filename=IndexCorrelation.xls";
						String str_url = "../pages/EmailReport.jsp?switch_type=15&cas=15&rname=IndexCorrelation.xls&from="
								+ fromdate + "&to=" + toDate;
		%>
		<table width="800" cellpadding="0" cellspacing="0">
			<tr>
				<td width="285" nowrap="nowrap" align="right">&nbsp;</td>
				<td width="105" nowrap="nowrap" align="left"><font size="1">
						<a href="javascript:popprinter('<%=astr%>');"><bean:message
								key="IndexPerformance.printerf" /></a>
				</font></td>
				<td width="110" nowrap="nowrap"><font size="1"> <a
						href=<%=excel%>><bean:message
								key="IndexPerformance.downloade" /></a>
				</font></td>
				<td width="289" nowrap="nowrap"><font size="1"> <a
						href=<%=str_url%>><bean:message
								key="IndexPerformance.emailr" /></a>
				</font></td>
			</tr>
		</table>
		<br />
		<%
			} catch (Exception e) {
						log.info("refresh the page.");
					}
				}
			}
		%>

		<table border="0" width="900" cellpadding="0" cellspacing="0">
			<tr>
				<td width="130" nowrap="nowrap">&nbsp;</td>
				<td width="770" nowrap="nowrap">
					<%
						if (var == null) {
					%>
					<table border="0" align="left" width="631" height="222"
						class="gridStyle" cellspacing="0" cellpadding="0">
						<tr>
							<td align="center" valign="middle" class="gridStyle-message">
								<p style="margin-left: 9">
									<b><bean:message key="StockPerformance.selData" /></b> </a>
								</p>
							</td>
						</tr>
					</table> <%
 	} else {
 %>
					<table border="0" align="left" width="90%" class="gridStyle"
						cellspacing="0" cellpadding="5">
						<tr>
							<td width="15%" class="gridStyle-header" align="center"
								valign="middle"></td>
							<%
								Vector vid = cc.getId_name(var);
									Object ci1 = null;
									session.setAttribute("ci1", new Vector(vid));
									Iterator i_id = vid.iterator();
									while (i_id.hasNext()) {
										String id = (String) i_id.next();
							%>
							<td width="15%" class="gridStyle-header" align="center"
								valign="middle"><%=i_id.next()%></td>
							<%
								}
							%>
						</tr>
						<%
							Vector cor = cc.getCalculatedCorrelation(fromdate, toDate);
								Object ci2 = null;
								session.setAttribute("ci2", new Vector(cor));
								Iterator i_cor = cor.iterator();
								while (i_cor.hasNext()) {
						%>
						<tr>
							<td width="15%" align="left" valign="left" class="gridStyle-blue"><%=i_cor.next()%></td>
							<%
								Iterator i_id1 = vid.iterator();
										while (i_id1.hasNext()) {
											String id1 = (String) i_id1.next();
											String id2 = (String) i_id1.next();
							%>
							<td width="15%" align="center" valign="right"
								class="gridStyle-blue"><%=i_cor.next()%></td>
							<%
								}
							%>
						</tr>
						<%
							}
						%>
					</table> <%
 	}
 %>
				</td>
			</tr>
			</form>
			</tbody>
		</table>
		</td>
		</tr>

		</table>
		<table border="0" width="100%" cellspacing="1" cellpadding="0">
			<tr>
				<td width="28%" valign="top">
					<p></p>
				</td>
				<td width="72%" valign="top">df
					<p>
				</td>
			</tr>
		</table>
		<script language="javascript">
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
				if ((document.forms[0].from.value) == "")
					document.forms[0].from.value = td + s + mnth + s + yr;
				if ((document.forms[0].to.value) == "")
					document.forms[0].to.value = td + s + mnth + s + yr;
			}
			function checkdatecurrent(objName) {
				var datefield = objName;
				var strMonth;
				var strYear;
				var strDate;
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
						strDateArray = strDate
								.split(strSeparatorArray[intElement]);
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
				if ((intYear == int_yr) && (int_month == int_mnth)
						&& (intday > int_td)) {
					return false;
				} else {
					return true;
				}
			}
			function test1() {
				document.forms[0].submit();
			}
			function popprinter(url) {
				newwindow = window
						.open(url, 'name',
								'height=500,width=900,scrollbars=yes,left=80,top=130,menubar=yes,status=yes');
				if (window.focus) {
					newwindow.focus()
				}
			}
			function checkdate() {
				var objTo = document.forms[0].to;
				if ((checkdatecurrent(objTo)) == false) {
					alert("ToDate should be Less Than CurrentDate.");
					objTo.focus();
					objTo.select();
					return false;
				} else
					return true;
			}
		</script>
</body>

</html>