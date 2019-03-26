<%@ page language="java" import="app.*"%>
<%@ page language="java" import="harrier.income.com.report.*"%>
<%@page import="com.harrier.initializeation.ConnectInit"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="java.util.*"%><%@page import="org.apache.log4j.Logger"%>
<%
	Logger log = Logger.getLogger(this.getClass());
	log.info("inside " + this.getClass().getSimpleName() + " page");
%>
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");

	//	AcessControl asc=new AcessControl();
	AcessControl asc = ConnectInit.getAcessControl();
	boolean flag = false;
	LogonForm form = null;

	if (request.isRequestedSessionIdValid()) {
		form = (LogonForm) session.getAttribute("user");
		Vector uname = asc.getUseActivitiesId(form);
		flag = asc.HasAcess("39", uname);
		//       log.info("flag is "+flag);
		//        log.info("1 = "+session.getAttribute("user"));
	}
	if (form == null || (!request.isRequestedSessionIdValid())) {
		response.sendRedirect("../userlogintemp.jsp");
		//		log.info("2 = "+session.getAttribute("user"));
		return;
	}
	//	log.info("3 = "+session.getAttribute("user"));
	/*	if(request.isRequestedSessionIdValid())	{	
		form = (LogonForm)session.getAttribute("user");
	}
	if(form==null ||(!request.isRequestedSessionIdValid())){
		response.sendRedirect("../userlogintemp.jsp");
	}
	 */
%>
<jsp:useBean id="DisplayIndexBean" scope="session"
	class="harrier.income.com.report.DisplayIndexForm" />
<%
	if (request.isRequestedSessionIdValid()) {
		log.info("User ID In Display Index List");
%>
<jsp:setProperty name="DisplayIndexBean" property="userid1"
	value='<%=session.getAttribute("userid")%>' />
<jsp:setProperty name="DisplayIndexBean" property="roleid1"
	value='<%=session.getAttribute("role_id")%>' />
<%
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
			//		this.document.getElementById(id).style.width='10px';
			Set_Cookie('showLeftCol', 'true', 30, '/', '', '');
			var show = Get_Cookie('showLeftCol');
			//document['HideHandle'].src = 'images/hide.gif';
			document.getElementById("HideHandle").src = '../open.gif';
		} else {
			//		this.document.getElementById(id).style.display='none';
			this.document.getElementById(id).style.display = 'none';
			document.getElementById("HideHandle").src = '../openImage.gif';
			Set_Cookie('showLeftCol', 'false', 30, '/', '', '');
			var show = Get_Cookie('showLeftCol');
			//document['HideHandle'].src = 'images/show.gif';	
		}
	}
</script>
<link href="./StyleSheet.css" rel="stylesheet" type="text/css">
<script type='text/javascript'
	src='/Stockpile/dwr/interface/MoveTable.js'></script>
<script type='text/javascript' src='/Stockpile/dwr/engine.js'></script>
<script type='text/javascript' src='/Stockpile/dwr/util.js'></script>
<script type="text/javascript" src="../Script/SortedTable.js"></script>
<script language="javascript" src="./codethatcalendarstd.js"></script>
<script language="javascript" src="box_ex.js"></script>


<script language="javascript">
	var c2 = new CodeThatCalendar(caldef2);
</script>
<!--<script type="text/javascript" src="./sorttable.js"></script>
		<style type="text/css"> 
/* Sortable tables 
table.sortable a.sortheader {
    background-color:#eee;
   font-size: 13px; 
    font-family: Arial;
    color: black;
    text-decoration: none;
    display: block;
}
table.sortable span.sortarrow {
    color: black;
    text-decoration: none;
}*/
</style> -->
<script type="text/javascript" src="../Script/Event.js"></script>
<script type="text/javascript" src="../Script/SortedTable.js"></script>

<style type="text/css">
/* table styles*/
.sorted td, th {
	border: 0;
	padding: 2px 6px;
	margin: 0;
	border-right: 1px solid #336;
	border-bottom: 1px solid #336;
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
<%
	if (request.getParameter("FromLogin") != null
				&& request.getParameter("FromLogin").equals("No")) {
			DisplayIndexBean.reset();
			log.info("in new" + request.getParameter("new"));
%>
<body onload="initialize()">
	<%
		} else {
	%>

<body onLoad="menuload()">
	<%
		}
	%>

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
			<td align="left" valign="top"><html:form action="/DisplayIndex">

					<b> <br>
						<div id="PageTitle">
							<p align="center" class="subHeader">
								<font size="3" face="Arial Black"> <bean:message
										key="DisplayIndexes1.title" />
								</font>
							</p>
						</div>
						<table align="right">
							<%
								String excel_path = "./FileDownload.jsp?&type=4&filename=IndexList.xls";
										String xml_path = "./FileDownloadXmlNew.jsp?&type=4&filename=IndexList.xml";
										String pdf_path = "./FileDownload_Pdf.jsp?&type=4&filename=IndexList.pdf";
										String str_url = "./EmailReport.jsp?switch_type=4&cas=4&rname=IndexList.xls";
							%>

							<td colspan="2" nowrap="nowrap" align="left"><font size="1"
								face="Verdana" color="blue"> <!-- Printer friendly --> <a
									href="javascript:PrintThisPage();"><bean:message
											key="LatestDivisor.printerf" /> </a> &nbsp;&nbsp; <!-- DownLoad Excel -->
									<a href='<%=excel_path%>'><bean:message
											key="LatestDivisor.downloade" /> </a> &nbsp;&nbsp; <!-- DownLoad Xml -->
									<a href='<%=xml_path%>'>Export to Xml</a> &nbsp;&nbsp; <!-- Email Report -->
									<a href='<%=str_url%>'><bean:message
											key="LatestDivisor.emailr" /> </a> <!-- Pdf Report --> <a
									href='<%=pdf_path%>'><bean:message
											key="LatestDivisor.pdfr" /> </a>
							</font></td>
						</table> <br>

						<center>
							&nbsp;&nbsp;&nbsp;
							<bean:message key="IndexPerformance.SelDate" />
							&nbsp;:&nbsp;&nbsp;&nbsp;
							<html:text property="from" readonly="true" size="10" />
							<html:button property="shwFrom" value="..."
								onclick="c2.popup('from');" />
							<html:submit onclick="return go();" onkeypress="return go();">
								<bean:message key="Reports.View" />
							</html:submit>
							<!-- <INPUT type="button" name="Aview" value="AjaxView" onclick="indexList()"> -->
							<logic:empty name="DisplayIndexBean" property="from">

								<%
									log.info(" Inside from ==null ");
												//harrier.income.com.report.DisplayIndexForm di = new harrier.income.com.report.DisplayIndexForm();
												Vector v = DisplayIndexBean.getIndex_details();
												Object ci2 = null;
												session.setAttribute("ci2", new Vector(v));
								%>
							</logic:empty>

						</center>
						<P>
							<!-- ============================================ For Ajax ================================================== -->

							<span id="Ajaxcontentstart">

								<table width="150%" class="sorted" ID="sortTableAjax"
									style="display: none" border="1" align="center" cellpadding="0"
									cellspacing="0" bgcolor="#EFEFEF">
									<!--  <table border="1"> -->
									<thead>
										<tr>
											<th width="100" class="gridStyle-header">Index Name</th>
											<th class="gridStyle-header">Value</th>
											<th class="gridStyle-header">Status</th>
											<th class="gridStyle-header">Open</th>
											<th class="gridStyle-header">High</th>
											<th class="gridStyle-header">Low</th>
											<th width="100" class="gridStyle-header">Last Closing</th>
											<th class="gridStyle-header">% Change</th>
											<th class="gridStyle-header">Market Cap</th>
											<th class="gridStyle-header">Divisor</th>
											<th class="gridStyle-header">currency</th>
											<th class="gridStyle-header">Date</th>
										</tr>
									</thead>
									<tbody id="indexMovingTable" bgcolor="#DEE3EF">
									</tbody>
								</table>
							</span>
						<table border="0" align="center" width="631" height="222"
							cellspacing="0" cellpadding="0" id="nodata"
							style="display: none;">

							<tr>
								<td bgcolor="#84AADE" align="center" valign="middle">
									<p style="margin-left: 9">
										<font face="Verdana" color="blue" size="5"> <bean:message
												key="IndexCompareOHCL.ndata" />
										</font>
									</p>
								</td>
							</tr>
						</table> <!-- ============================================ For Ajax ================================================== -->
						<br>
						<div id=contentstart>
							<table class="sorted" ID="sortTable" border="1" align="center"
								cellpadding="3" cellspacing="0">
								<thead>
									<tr>
										<!--<table border="1" align="center" cellpadding="3" cellspacing="0" id="t1" class="sortable" bgcolor="#EFEFEF">    
 		<tr> -->
										<!-- 
          	<td align="center">
          	<html:checkbox name="DisplayIndexBean" property="indexid1" onclick="CheckAll(this);" />
          	</td>
          -->
										<th align="center" nowrap="nowrap" id="indName"
											class="gridStyle-header"><span><b><bean:message
														key="indexcompose.indexname" /> </b> </span></th>
										<th align="center" nowrap="nowrap" id="update"
											class="gridStyle-header"><span><b><bean:message
														key="indexUpdate.Value" /> </b> </span></th>
										<th align="center" nowrap="nowrap" id="status"
											class="gridStyle-header">&nbsp;</th>
										<th align="center" nowrap="nowrap" id="open"
											class="gridStyle-header"><span><b><bean:message
														key="DisplayIndexes1.Open" /> </b> </span></th>
										<th align="center" nowrap="nowrap" id="high"
											class="gridStyle-header"><span><b><bean:message
														key="DisplayIndexes1.High" /> </b> </span></th>
										<th align="center" nowrap="nowrap" id="low"
											class="gridStyle-header"><span><b><bean:message
														key="DisplayIndexes1.Low" /> </b> </span></th>
										<th align="center" nowrap="nowrap" id="last"
											class="gridStyle-header"><b><bean:message
													key="DisplayIndexes1.Last" /> </b></span></th>
										<th align="center" nowrap="nowrap" id="change"
											class="gridStyle-header"><span><b><bean:message
														key="DisplayIndexes1.Change" /> </b> </span></th>
										<th align="center" nowrap="nowrap" id="market"
											class="gridStyle-header"><span><b><bean:message
														key="DisplayIndexes1.Market" /> </b> </span></th>
										<th align="center" nowrap="nowrap" id="divisor"
											class="gridStyle-header"><span><b><bean:message
														key="indcurrwise.divisor" /> </b> </span></th>
										<th align="center" nowrap="nowrap" id="currency"
											class="gridStyle-header"><span><b><bean:message
														key="stockmaster.currency" /> </b> </span></th>
										<th align="center" nowrap="nowrap" id="date"
											class="gridStyle-header"><span><b><bean:message
														key="corporate.Date" /> </b> </span></th>
									</tr>
								</thead>
								<tbody>
									<%
										int i = 1;
												String bcolor = "";
									%>
									<logic:iterate id="try2" property="details"
										name="DisplayIndexBean">

										<%
											if (i % 2 == 0) {
															bcolor = "#84AADE";
														} else {
															bcolor = "#DEE3EF";
														}
										%>
										<tr bgcolor=<%=bcolor%>>
											<!--  
     		<td align="center" bgcolor="white">
        		<html:checkbox name="DisplayIndexBean" property="indexid1" /> 
        		<%log.info(" inside checkbox val ");%>
           	</td>
          -->
											<td align="left" nowrap="nowrap" axis="sstring"
												headers="indName"
												title='<bean:write name="try2" property="indexname"/>'>

												<a
												href='./IndexComposeS.jsp?index=<bean:write name="try2" property="indexid"/>&compute=yes&ajax1=yes'>

													<bean:write name="try2" property="indexname" />
											</a>

											</td>
											<td align="right" axis="number" headers="update"
												nowrap="nowrap"
												title='<bean:write name="try2" property="current"/>'><bean:write
													name="try2" property="current" /></td>
											<td align="right" axis="sstring" headers="status"
												nowrap="nowrap"><logic:equal value="up" name="try2"
													property="status">
													<img border="0" src="images/up.gif" width="13"
														align="middle" height="12">
												</logic:equal> <logic:equal value="down" name="try2" property="status">
													<img border="0" src="images/down.gif" width="13"
														align="middle" height="12">
												</logic:equal> <logic:equal value="mid" name="try2" property="status">
													<img border="0" src="images/mid.gif" width="13"
														align="middle" height="12">
												</logic:equal></td>
											<td align="right" axis="number" headers="open"
												nowrap="nowrap"
												title='<bean:write name="try2" property="indexopen"/>'>

												<bean:write name="try2" property="indexopen" />
											</td>
											<td align="right" axis="number" headers="high"
												nowrap="nowrap"
												title='<bean:write name="try2" property="indexhigh"/>'>

												<bean:write name="try2" property="indexhigh" />
											</td>
											<td align="right" axis="number" headers="low" nowrap="nowrap"
												title='<bean:write name="try2" property="indexlow"/>'>

												<bean:write name="try2" property="indexlow" />
											</td>
											<td align="right" axis="number" headers="last"
												nowrap="nowrap"
												title='<bean:write name="try2" property="indexclsv"/>'>

												<bean:write name="try2" property="indexclsv" />
											</td>
											<td align="right" axis="number" headers="change"
												nowrap="nowrap"
												title='<bean:write name="try2" property="vachange"/>'>

												<bean:write name="try2" property="vachange" />
											</td>
											<td align="right" axis="number" headers="market"
												nowrap="nowrap"
												title='<bean:write name="try2" property="tmcv"/>'><bean:write
													name="try2" property="tmcv" /></td>
											<td align="right" axis="number" headers="divisor"
												nowrap="nowrap"
												title='<bean:write name="try2" property="divisor"/>'><bean:write
													name="try2" property="divisor" /></td>
											<td align="right" axis="sstring" headers="currency"
												nowrap="nowrap"
												title='<bean:write name="try2" property="currency"/>'>

												<bean:write name="try2" property="currency" />
											</td>
											<td align="right" nowrap="nowrap" axis="date" headers="date"
												title='<bean:write name="try2" property="indexdate"/>'>

												<bean:write name="try2" property="indexdate" />
											</td>
										</tr>
										<%
											i = i + 1;
										%>
									</logic:iterate>
								</tbody>
							</table>
						</div> <br></br>

						<div id="Details">
							<table id="hiddenTable">
								<tr>
									<td></td>
									<td><b> <font size="2" face="Arial"> <bean:message
													key="IndexPerformance.SelDate" />&nbsp;:
										</font>
									</b></td>
									<td><bean:write name="DisplayIndexBean" property="from" />
									</td>
								</tr>
							</table>
						</div> <html:hidden property="clear" /> <html:hidden property="compute"
							value="no"></html:hidden>
				</html:form> <script language="javascript">
					var Button;
					var man1 = document.getElementById("hiddenTable");
					man1.style.display = "none";
					var sourceTable, destTable;
					function initSort() {
						//	alert('initSort');

						mySorted = new SortedTable();
						mySorted.colorize = function() {
							for (var i = 0; i < this.elements.length; i++) {
								if (i % 2) {
									this.changeClass(this.elements[i], 'even',
											'odd');
								} else {
									this.changeClass(this.elements[i], 'odd',
											'even');
								}
							}
						}

						mySorted.onsort = mySorted.colorize;
						mySorted.onmove = mySorted.colorize;
						mySorted.colorize();
						//	alert('initSort end');
					}

					function initialize() {
						//	alert('initialize');
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
							document.forms[0].from.value = td + s + mnth + s
									+ yr;
						initSort();
					}

					function PrintThisPage() {
						var sOption = "toolbar=yes,location=no,directories=yes,menubar=yes,";
						sOption += "scrollbars=yes,width=750,height=500,left=100,top=25";
						var dt = document.forms[0].from.value;
						if (Button == "AjaxButton") {
							var sWinHTML = document
									.getElementById('Ajaxcontentstart').innerHTML;
						} else {
							var sWinHTML = document
									.getElementById('contentstart').innerHTML;
						}
						//var sWinHTML = document.getElementById('contentstart').innerHTML; 
						var printHead = document.getElementById('PageTitle').innerHTML;
						var moreDet = document.getElementById('hiddenTable').innerHTML;
						var winprint = window.open("", "", sOption);
						winprint.document.open();
						winprint.document
								.write('<html><LINK href=StyleSheet.css rel=Stylesheet><body onload="self.print()" >');
						winprint.document.write(printHead);
						winprint.document.write('<br>');
						winprint.document.write('&nbsp;&nbsp;&nbsp;');
						winprint.document.write('&nbsp;&nbsp;&nbsp;');
						winprint.document.write(moreDet);
						winprint.document.write('<br>');
						winprint.document.write(sWinHTML);
						winprint.document.write('</body></html>');
						winprint.document.close();
						winprint.focus();
					}

					function test1() {
						document.forms[0].operation.value = "Go";
						return true;
					}
					function menuload() {
						//	alert('menu Load');
						top.topFrame.location.reload();
						top.treeFrame.location.reload();
						initSort();
					}

					function go() {
						var objTo = document.forms[0].from;
						var i = 0;
						var fields = new Array();
						if (document.forms[0].from.value == "") {
							fields[i++] = "Date is required";
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
					function CheckAll(chk) {
						for (var i = 0; i < document.forms[0].elements.length; i++) {
							var e = document.forms[0].elements[i];
							if (e.type == "checkbox" && e.name == "indexid") {
								e.checked = chk.checked;
							}
						}
					}

					/*===================================== For DWR ========================================================================*/
					function indexList() {
						if (go()) {
							DWRUtil.useLoadingMessage();
							var date = DWRUtil.getValue("from");
							MoveTable.getDetails(fillTable, date);
						}
					}
					var indexname = function(detailindex) {
						var anchor = detailindex.indexid;
						var inurl = "./IndexComposeS.jsp?index=" + anchor
								+ "&compute=yes";
						var url = '<a href='+inurl+'><font face="Verdana" color="blue" size="2">'
								+ detailindex.indexname + '</font></a>';

						return url
					};
					var current = function(detailindex) {
						return detailindex.current
					}; // if we return to using dates, add .toLocaleDateString()
					var status = function(detailindex) {
						var Status = detailindex.status;
						if (Status == "up") {
							return '<img border="0" src="images/up.gif" width="13" align="middle" height="12">';
						}
						if (Status == "down") {
							return '<img border="0" src="images/down.gif" width="13" align="middle" height="12">';
						}
						if (Status == "middle") {
							return '<img border="0" src="images/middle.gif" width="13" align="middle" height="12">';
						}
						//return detailindex.status;
					};
					var indexopen = function(detailindex) {
						return detailindex.indexopen
					};
					var indexhigh = function(detailindex) {
						return detailindex.indexhigh
					};
					var indexlow = function(detailindex) {
						return detailindex.indexlow
					};
					var indexclosing = function(detailindex) {
						return detailindex.indexclsv
					};
					var vachange = function(detailindex) {
						return detailindex.vachange
					};
					var tmcv = function(detailindex) {
						return detailindex.tmcv
					};
					var divisor = function(detailindex) {
						return detailindex.divisor
					};
					var currency = function(detailindex) {
						return detailindex.currency
					};
					var indexdate = function(detailindex) {
						return detailindex.indexdate
					};

					function fillTable(indexMovingTable) {
						/*changeDisplay("indextable","inline");
						//changeDisplay("select_id_index","none");	
						DWRUtil.removeAllRows("indexMovingTable");
						DWRUtil.addRows("indexMovingTable",indexMovingTable, [ indexname, current, status, indexopen, indexhigh, indexlow, indexclosing, vachange, tmcv, divisor, currency, indexdate ]);
						//
						 */
						var a = new Array();
						a = indexMovingTable;
						if (a.length) {
							Button = "AjaxButton";
							changeDisplay("nodata", "none");
							changeDisplay("sortTableAjax", "inline");
							changeDisplay("sortTable", "none");
							DWRUtil.removeAllRows("indexMovingTable");
							DWRUtil.addRows("indexMovingTable",
									indexMovingTable,
									[ indexname, current, status, indexopen,
											indexhigh, indexlow, indexclosing,
											vachange, tmcv, divisor, currency,
											indexdate ]);
						} else {
							changeDisplay("sortTableAjax", "none");
							changeDisplay("nodata", "inline");
							changeDisplay("sortTable", "none");
						}
					}
				</script>
</body>
</html:html>