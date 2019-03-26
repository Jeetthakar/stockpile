<%@ page import="java.io.PrintWriter"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page language="java" import="app.*"%>
<%@ page import="harrier.income.com.report.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>

<%@ page language="java" import="app.*"%><%@page
	import="org.apache.log4j.Logger"%>
<%
	Logger log = Logger.getLogger(this.getClass());
	log.info("inside " + this.getClass().getSimpleName() + " page");
%>
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");

	LogonForm form = null;
	if (request.isRequestedSessionIdValid()) {
		form = (LogonForm) session.getAttribute("user");
	}
	if (form == null || (!request.isRequestedSessionIdValid())) {
		response.sendRedirect("../userlogintemp.jsp");
	}
%>
<html:html>
<html:base />

<head>
<SCRIPT language=javascript>
	function hideLeftCol(id) {
		if (this.document.getElementById(id).style.display == 'none') {
			this.document.getElementById(id).style.display = 'inline';
			document.getElementById("HideHandle").src = '../closeImage.gif';
			// this.document.getElementById(id).style.width='10px';
			Set_Cookie('showLeftCol', 'true', 30, '/', '', '');
			var show = Get_Cookie('showLeftCol');
			//document['HideHandle'].src = 'images/hide.gif';
			document.getElementById("HideHandle").src = '../open.gif';
		} else {
			// this.document.getElementById(id).style.display='none';
			this.document.getElementById(id).style.display = 'none';
			document.getElementById("HideHandle").src = '../openImage.gif';
			Set_Cookie('showLeftCol', 'false', 30, '/', '', '');
			var show = Get_Cookie('showLeftCol');
			//document['HideHandle'].src = 'images/show.gif';
		}
	}
</script>

<link href="./StyleSheet.css" rel="stylesheet" type="text/css">
<script language="javascript" src="./codethatcalendarstd.js"></script>
<script language="javascript" src="box_ex.js"></script>
<script type="text/javascript" src="../Script/SortedTable.js"></script>
<script type='text/javascript'
	src='/Stockpile/dwr/interface/MoveTable.js'></script>
<script type='text/javascript' src='/Stockpile/dwr/engine.js'></script>
<script type='text/javascript' src='/Stockpile/dwr/util.js'></script>

<script language="javascript">
	var c2 = new CodeThatCalendar(caldef2);
</script>
<script type="text/javascript" src="./sorttable.js"></script>
<style type="text/css">
/* Sortable tables */
table.sortable a.sortheader {
	background-color: #eee;
	font-size: 13px;
	font-family: Arial;
	color: black;
	text-decoration: none;
	display: block;
}

table.sortable span.sortarrow {
	color: black;
	text-decoration: none;
}

/* table styles*/
.sorted td, th {
	border: 0;
	padding: 2px 6px;
	margin: 0;
	border-right: 1px solid #336;
	border-bottom: 1px solid #336;
	background-color: #dddddd;
	color: #black;
	font-size: 10px;
	padding-left: 2px;
}

td[axis='number'], td[axis='date'] {
	text-align: right;
}

th {
	background-color: #cacaca;
	padding: 2px 20px;
	color: blue;
	font-size: 12px;
	vertical-align: baseline;
	line-height: 18px;
}

tfoot td {
	border-top: 0px solid #003;
}

thead th {
	border-bottom: 1px solid #003;
}

.sortedminus {
	background-color: #ecc;
}

.sortedplus {
	background-color: #cec;
}
</style>
</head>
<body onload="initialize()">


	<table width="100%" height="100%">

		<tr>
			<td align="left" valign="top" bgcolor="#CCddee">

				<DIV id="leftCol" style="DISPLAY: none; width: 160; height: 100%;">
					<%@ include file="../tree3.jsp"%>
				</div> <IMG id="HideHandle" src="../openImage.gif"
				style="CURSOR: hand; position: absolute;"
				onclick='hideLeftCol("leftCol");' src="fold.gif" width="10"
				height="25">
			</td>
			<td align="left" valign="top"><html:form
					action="/StockContribution">
					<%
						if (request.isRequestedSessionIdValid()) {
					%>
					<jsp:setProperty name="StockContributionBean" property="user_id"
						value='<%=session.getAttribute("userid").toString()%>' />
					<jsp:setProperty name="StockContributionBean" property="role_id1"
						value='<%=session.getAttribute("role_id").toString()%>' />


					<%
						}
					%>
					<div id="PageTitle">
						<table width="1000" cellspacing="0" cellpadding="0">
							<tr>
								<td width="250" class="subHeader" nowrap="nowrap">&nbsp;</td>
								<td class="subHeader" width="665" align="left" colspan="2"
									nowrap="nowrap"><font size="3" face="Verdana"> <b><bean:message
												key="StockContritoIndexChange.title" /></b>
								</font></td>
							</tr>
						</table>
					</div>
					<%
						String ajax1 = "no";
								try {
									ajax1 = request.getParameter("ajax1");
								} catch (Exception e) {
									// TODO: handle exception
									//		Logging.error(" Error :"+e.getMessage());
								}
					%>
					<logic:equal value="yes" parameter="compute">
						<logic:notEqual value="0" name="dataCount">
							<%
								String var = request.getParameter("index");
												String to = request.getParameter("to");
												String from = request.getParameter("from");
												String excel_path = "./FileDownload.jsp?var="
														+ var
														+ "&type=5&filename=StockContribution.xls&from="
														+ from + "&to=" + to;
												String xml_path = "./FileDownloadXmlNew.jsp?var="
														+ var
														+ "&type=5&filename=StockContribution.xml&from="
														+ from + "&to=" + to;
												String pdf_path = "./FileDownload_Pdf.jsp?type=5&filename=StockContribution.pdf";

												String str_url = "./EmailReport.jsp?switch_type=5&cas=5&varid="
														+ var
														+ "&rname=StockContribution.xls&from="
														+ from + "&to=" + to;
							%>
							<table align="right">
								<tr>
									<td colspan="2" nowrap="nowrap" align="right">&nbsp;&nbsp;&nbsp;&nbsp;
										<font size="1" face="Verdana" color="blue"> <!-- Printer friendly -->
											<a href="javascript:PrintThisPage();"><bean:message
													key="LatestDivisor.printerf" /></a> &nbsp;&nbsp; <!-- DownLoad Excel -->
											<a href='<%=excel_path%>'><bean:message
													key="LatestDivisor.downloade" /></a> &nbsp;&nbsp; <!-- DownLoad Xml -->
											<a href='<%=xml_path%>'>Export to Xml</a> &nbsp;&nbsp; <a
											href='<%=pdf_path%>'><bean:message
													key="LatestDivisor.pdfr" /></a> &nbsp;&nbsp; <!-- Email Report -->
											<a href='<%=str_url%>'><bean:message
													key="LatestDivisor.emailr" /></a>
									</font>
									</td>
								</tr>
							</table>
						</logic:notEqual>
					</logic:equal>
					<p>&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;</p>
					<table>
						<tr>
							<td align="center">
								<table>
									<tr>
										<td>&nbsp;
										<td />
										<td nowrap="true" align="right"><font size="2"
											face="Verdana"><bean:message
													key="indexcompose.indexname" />: </font></td>
										<td nowrap="true" align="left"><html:select
												property="index" size="1" styleId="sIE">
												<html:optionsCollection name="StockContributionBean"
													property="indexcollection" />
											</html:select></td>
									</tr>
								</table>
							</td>
						</tr>

						<tr>
							<td align="center">
								<table>
									<tr>
										<td align="right" nowrap="nowrap"><html:checkbox
												property="checkChart"></html:checkbox>&nbsp;</td>
										<td align="left" nowrap="nowrap"><font size="2"
											face="Verdana"> <bean:message
													key="IndexCompose.schart" />
										</font></td>
									</tr>
								</table>
							</td>
						</tr>

						<tr>
							<td align="center">
								<table>
									<tr>
										<td nowrap="nowrap" align="right"><font size="2"
											face="Verdana"> <bean:message key="corporate.Fdate" /></font>
										</td>
										<td nowrap="nowrap"><html:text property="from" size="10"
												readonly="true" /></td>
										<td nowrap="nowrap"><html:button property="" value="..."
												onclick="c2.popup('from')" /></td>

										<td nowrap="nowrap"><font size="2" face="Verdana">
												<bean:message key="corporate.Tdate" />
										</font></td>

										<td nowrap="nowrap"><html:text property="to" size="10"
												readonly="true" /></td>

										<td nowrap="nowrap"><html:button property="" value="..."
												onclick="c2.popup('to');" /></td>

										<td nowrap="nowrap">
											<%
												if (ajax1.equals("yes")) {
											%> <html:submit onclick="return go();">
												<bean:message key="Reports.View" />
											</html:submit> <%
 	} else {
 %> <input type="button" value="AjaxView"
											onclick="getStockcontriIndexChange()" /> <%
 	}
 %>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<p>

						<!-- 	======================================== For Ajax ================================================== -->
					<table width="" class="sorted" ID="sortTable" style="display: none"
						border="0" align="center" cellpadding="0" cellspacing="0"
						bgcolor="#84AADE">
						<!--  <table border="1"> -->
						<thead>
							<tr>
								<th width="90" class="gridStyle-header">Stock Name</th>
								<th width="180" class="gridStyle-header">Index Mkt Cap.
									Difference</th>
								<th width="180" class="gridStyle-header">Stock Mkt. Cap.
									Difference</th>
								<th width="110" class="gridStyle-header">Weightage (%)</th>
							</tr>
						</thead>
						<tbody id="indexMovingTable" bgcolor="#84AADE">
						</tbody>
					</table>

					<table id="nodata" border="0" align="center" width="631"
						height="222" cellspacing="0" cellpadding="0"
						style="display: none;">
						<!--  <tr>
          					<td id="selectindexmessage" bgcolor="#cacaca" align="center" valign="middle">
          						<p style="margin-left: 9"><font face="Verdana" color="blue" size="5">
            					<bean:message key="IndexWiseWeight.Parameters" /></font></p>
            				</td>
          				</tr> -->
						<tr>
							<td bgcolor="#84AADE" align="center" valign="middle">
								<p style="margin-left: 9">
									<font face="Verdana" color="blue" size="5"> <bean:message
											key="IndexCompareOHCL.ndata" />
									</font>
								</p>
							</td>
						</tr>

					</table>
					<!-- ============================================ For Ajax ================================================== -->


					<br>


<%-- 					<bean:define id="try4" name="StockContributionBean" --%>
<%-- 						property="stockcotriIndexchange" /> --%>
<%-- 					<bean:size id="dataCount" name="StockContributionBean" --%>
<%-- 						property="stockcotriIndexchange" /> --%>
					<div id="chart">
						<logic:equal name="StockContributionBean" property="checkChart"
							value="on">

							<%
								GraphMethodsPf.StockcontriReaddata(request);
											GraphMethodsPf gm = new GraphMethodsPf();
											String filename = gm.getGraphChartCompany1(session,
													new PrintWriter(out));
											String graphURL = request.getContextPath()
													+ "/servlet/DisplayChart?filename=" + filename;
							%>
							<p align="center">
								<img src="<%=graphURL%>" width=700 height=500 border=0
									align="middle">
							</p>
						</logic:equal>
					</div>
					<table>
						<tr>
							<td width="99"></td>
							<td><logic:notEqual value="yes" parameter="compute">
									<%
										log.info(" INside compute <> yes");
									%>
									<table border="1" align="center" width="631" height="222"
										cellspacing="0" cellpadding="0">
										<tr>
											<td id="selectindexmessage" bgcolor="#84AADE" align="center"
												valign="middle">
												<p style="margin-left: 9">
													<font face="Verdana" color="blue" size="5"> <bean:message
															key="IndexWiseWeight.Parameters" /></font>
												</p>
											</td>
										</tr>
									</table>
								</logic:notEqual> <logic:equal value="yes" parameter="compute">

									<logic:equal value="0" name="dataCount">
										<table border="1" align="center" width="631" height="222"
											cellspacing="0" cellpadding="0">
											<tr>
												<td id="noStrutsData" bgcolor="#84AADE" align="center"
													valign="middle">
													<p style="margin-left: 9">
														<font face="Verdana" color="blue" size="5"> <bean:message
																key="IndexCompareOHCL.ndata" />
														</font>
													</p>
												</td>
											</tr>
										</table>
									</logic:equal>
									<logic:notEqual value="0" name="dataCount">

										<div id=contentstart>
											<p align="center">
											<table border="1" width="70%" align="center" cellpadding="2"
												cellspacing="0" id="t1" class="sortable">
												<tr>
													<td class="gridStyle-header" width="25%" align="center"
														valign="middle" nowrap="nowrap"><bean:message
															key="StockWisePe.Name" /></td>
													<td class="gridStyle-header" width="15%" align="center"
														valign="middle" nowrap="nowrap"><bean:message
															key="StockContritoIndexChange.Index" /></td>
													<td class="gridStyle-header" width="15%" align="center"
														valign="middle" nowrap="nowrap"><bean:message
															key="StockContritoIndexChange.Stock" /></td>
													<td class="gridStyle-header" width="15%" align="center"
														valign="middle" nowrap="nowrap"><bean:message
															key="IndexWiseWeight.Weightage" /></td>
												</tr>
												<logic:iterate id="try4" property="stockcotriIndexchange"
													name="StockContributionBean">

													<tr>
														<td class="gridStyle-odd" width="25%" align="left"
															height="37"><font face="Verdana" size="2"
															color="blue"> <bean:write name="try4"
																	property="stockname" />
														</font></td>
														<td class="gridStyle-odd" width="15%" align="right"
															nowrap="nowrap"><font face="Verdana" size="2"
															color="blue"> <bean:write name="try4"
																	property="indexmarket" />
														</font></td>
														<td class="gridStyle-odd" width="15%" align="right"
															nowrap="nowrap"><font face="Verdana" size="2"
															color="blue"> <bean:write name="try4"
																	property="stockmarket" />
														</font></td>
														<td class="gridStyle-odd" width="15%" align="right"
															nowrap="nowrap"><font face="Verdana" size="2"
															color="blue"> <bean:write name="try4"
																	property="weightage" />
														</font></td>
													</tr>
												</logic:iterate>
											</table>
											</p>
									</logic:notEqual>
								</logic:equal></td>

						</tr>
					</table>
					</div>
					<div id="hiddenTable">
						<table id="hiddenTable" height="100">
							<tr>
								<td width="125" align="right"><b> <font size="2"
										face="Verdana"><bean:message key="defineIndex7" />: </font></b></td>
								<td><font size="2" face="Verdana"><b>&nbsp;&nbsp;&nbsp;
											<bean:write name="StockContributionBean" property="indexName" />
									</b> </font></td>
							</tr>

							<tr>
								<td width="125" align="right"><font size="2" face="Verdana"><b>
											<bean:message key="corporate.Fdate" />&nbsp;:
									</b> </font></td>
								<td><font size="2" face="Verdana"> <bean:write
											name="StockContributionBean" property="from" />
								</font></td>
							</tr>
							<tr>
								<td width="125" align="right"><font size="2" face="Verdana"><b>
											<bean:message key="corporate.Tdate" />&nbsp;:
									</b> </font></td>
								<td><font size="2" face="Verdana"> <bean:write
											name="StockContributionBean" property="to" />
								</font></td>
							</tr>
						</table>

					</div>
					<html:hidden property="indexName" name="StockContributionBean" />
					<html:hidden property="defaultVal" />
					<html:hidden property="compute" value="no"></html:hidden>
					<html:hidden property="clear"></html:hidden>
				</html:form></td>
		</tr>
	</table>


</body>
<script language="javascript">
	var man1 = document.getElementById("hiddenTable");
	man1.style.display = "none";
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

	function PrintThisPage() {
		var showChart = document.getElementById('chart').innerHTML;
		var sOption = "toolbar=yes,location=no,directories=yes,menubar=yes,";
		sOption += "scrollbars=yes,width=750,height=600,left=100,top=25";
		var dt = document.forms[0].from.value;
		var df = document.forms[0].to.value;
		var iname = document.forms[0].indexName.value;
		var sWinHTML = document.getElementById('contentstart').innerHTML;
		var printHead = document.getElementById('PageTitle').innerHTML;
		var moreDet = document.getElementById('hiddenTable').innerHTML;
		var winprint = window.open("", "", sOption);
		winprint.document.open();
		winprint.document.write('<html><body>');
		winprint.document.write(printHead);
		winprint.document.write('<br>');
		winprint.document.write(moreDet);
		winprint.document.write('<br>');
		winprint.document.write(showChart);
		winprint.document.write(sWinHTML);
		winprint.document.write('</body></html>');
		winprint.document.close();
		winprint.focus();
	}

	function test1() {
		document.forms[0].submit();
	}
	function go() {
		var objTo = document.forms[0].to;
		var i = 0;
		var fields = new Array();
		if (document.forms[0].from.value == "") {
			fields[i++] = "From Date is required";
		}
		if (document.forms[0].to.value == "") {
			fields[i++] = "To Date is required";
		}
		if (document.forms[0].index.value == 0) {
			fields[i++] = "Select Index is required";
		}
		if (fields.length > 0) {
			alert(fields.join('\n'));
			return false;
		}
		if ((checkdatecurrent(objTo)) == false) {
			alert("ToDate should be Less Than CurrentDate.");
			objTo.focus();
			objTo.select();
			return false;
		} else {
			document.forms[0].compute.value = "yes";
			document.forms[0].defaultVal.value = "no";
			return true;
		}
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
	function viewFunc() {
		var i = 0;
		if (document.forms[0].D1.value == "0") {
			alert("Index Name is required");
			return false;
		} else
			return true;
	}

	/*===================================== For DWR ========================================================================*/
	function getStockcontriIndexChange() {
		if (go()) {
			DWRUtil.useLoadingMessage();
			var todate = DWRUtil.getValue("to");
			var fromdate = DWRUtil.getValue("from");
			var index = DWRUtil.getValue("index");
			//alert(todate+fromdate+index);  
			//alert("");
			MoveTable.getStockcontriIndexchange(fillTable, index, fromdate,
					todate);
		}
	}

	var stockname = function(IndexPePbDetails) {
		return '<font face="Verdana" color="blue" size="2">'
				+ IndexPePbDetails.stockname + '</font>'
	};
	var indexmarket = function(IndexPePbDetails) {
		return '<font face="Verdana" color="blue" size="2">'
				+ IndexPePbDetails.indexmarket + '</font>'
	}; // if we return to using dates, add .toLocaleDateString()
	var stockmarket = function(IndexPePbDetails) {
		return '<font face="Verdana" color="blue" size="2">'
				+ IndexPePbDetails.stockmarket + '</font>'
	};
	var weightage = function(IndexPePbDetails) {
		return '<font face="Verdana" color="blue" size="2">'
				+ IndexPePbDetails.weightage + '</font>'
	};
	//alert(stockId);
	function fillTable(indexMovingTable) {
		/* changeDisplay("sortTable","inline");
		 changeDisplay("selectindexmessage","none");	
		 DWRUtil.removeAllRows("indexMovingTable");
		 DWRUtil.addRows("indexMovingTable",indexMovingTable, [ stockname, indexmarket, stockmarket, weightage ]);
		 //alert("I got it right");
		 */

		var a = new Array();
		a = indexMovingTable;
		var length = a.length;
		if (a.length) {
			//alert("");
			changeDisplay("selectindexmessage", "none");
			changeDisplay("nodata", "none");
			changeDisplay("noStrutsData", "none");
			changeDisplay("t1", "none");
			changeDisplay("sortTable", "inline");
			DWRUtil.removeAllRows("indexMovingTable");
			DWRUtil.addRows("indexMovingTable", indexMovingTable, [ stockname,
					indexmarket, stockmarket, weightage ]);
		} else {
			changeDisplay("sortTable", "none");
			changeDisplay("nodata", "inline");
			changeDisplay("t1", "none");
			changeDisplay("selectindexmessage", "none");
			changeDisplay("noStrutsData", "none");
		}
	}
</script>
</html:html>
