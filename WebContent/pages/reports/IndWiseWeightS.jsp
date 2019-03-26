
<%@ page import="java.io.PrintWriter"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page language="java" import="app.*"%>
<%@ page import="harrier.income.com.report.*"%>

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


<link href="../StyleSheetM.css" rel="stylesheet" type="text/css">
<!-- <script type="text/javascript" src="./sorttable.js"></script>
		<style type="text/css"> 
/* Sortable tables */
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
}
</style> -->
<script type="text/javascript" src="../Script/Event.js"></script>
<script type="text/javascript" src="../Script/SortedTable.js"></script>
<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
<script type='text/javascript'
	src='/Stockpile/dwr/interface/MoveTable.js'></script>
<script type='text/javascript' src='/Stockpile/dwr/engine.js'></script>
<script type='text/javascript' src='/Stockpile/dwr/util.js'></script>
<script type='text/javascript'
	src='/Stockpile/dwr/interface/GraphMethods.js'></script>

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
<body onload="initSort()">


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
					action="/IndexWiseWeight">


					<%
						if (request.isRequestedSessionIdValid()) {
					%>
					<jsp:setProperty name="IndexWiseWeightBean" property="userid1"
						value='<%=session.getAttribute("userid")%>' />
					<jsp:setProperty name="IndexWiseWeightBean" property="roleId_wtg"
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
												key="IndexWiseWeight.title" /></b>
								</font></td>
							</tr>
						</table>
					</div>
					<br>
					<%
						String ajax1 = "no";
								try {
									ajax1 = request.getParameter("ajax1");

								} catch (Exception e) {
									// TODO: handle exception
									//	Logging.error(" Error :"+e.getMessage());
								}
					%>

					<logic:equal value="yes" parameter="compute">
						<!-- if compute ="yes" display links -->
						<%
							String var = request.getParameter("index");
										String excel_path = "./FileDownload.jsp?var=" + var
												+ "&type=3&filename=IndWiseWeightage.xls";
										String xml_path = "./FileDownloadXmlNew.jsp?var=" + var
												+ "&type=3&filename=IndWiseWeightage.xml";
										String path_pdf = "./FileDownload_Pdf.jsp?type=3&filename=IndWiseWeightage.pdf";

										String str_url = "./EmailReport.jsp?switch_type=3&cas=3&rname=IndWiseWeightage.xls&varid="
												+ var;
						%>
						<table id="strutslinks" cellpadding="0" cellspacing="0"
							align="right">

							<tr>
								<td width="220" nowrap="nowrap"></td>
								<td nowrap="nowrap" align="center"><font size="1"
									face="Verdana" color="#0000FF"> <a
										href="javascript:PrintThisPage();"><bean:message
												key="LatestDivisor.printerf" /></a> &nbsp;&nbsp; <a
										href='<%=excel_path%>'><bean:message
												key="LatestDivisor.downloade" /></a> &nbsp;&nbsp; <a
										href='<%=xml_path%>'>Export to Xml</a> &nbsp;&nbsp; <a
										href='<%=path_pdf%>'><bean:message
												key="LatestDivisor.pdfr" /></a> &nbsp;&nbsp; <a
										href='<%=str_url%>'><bean:message
												key="LatestDivisor.emailr" /></a>
								</font></td>
							</tr>
						</table>
					</logic:equal>

					<table id="ajaxlinks" cellpadding="0" cellspacing="0" align="right"
						style="display: none">
						<%
							//String varaj=request.getParameter("index");
									String excel_pathaj = "./FileDownload.jsp?type=3&filename=IndWiseWeightage.xls&technology=Ajax";
									String xml_pathaj = "./FileDownloadXmlNew.jsp?type=3&filename=IndWiseWeightage.xml";
									String path_pdfaj = "./FileDownload_Pdf.jsp?type=3&filename=IndWiseWeightage.pdf";

									String str_urlaj = "./EmailReport.jsp?switch_type=3&cas=3&rname=IndWiseWeightage.xls";
						%>
						<tr>
							<td width="220" nowrap="nowrap"></td>
							<td nowrap="nowrap" align="center"><font size="1"
								face="Verdana" color="#0000FF"> <a
									href="javascript:PrintThisPage();"><bean:message
											key="LatestDivisor.printerf" /></a> &nbsp;&nbsp; <a
									href='<%=excel_pathaj%>'><bean:message
											key="LatestDivisor.downloade" /></a> &nbsp;&nbsp; <a
									href='<%=xml_pathaj%>'>Export to Xml</a> &nbsp;&nbsp; <a
									href='<%=path_pdfaj%>'><bean:message
											key="LatestDivisor.pdfr" /></a> &nbsp;&nbsp; <a
									href='<%=str_urlaj%>'><bean:message
											key="LatestDivisor.emailr" /></a>
							</font></td>
						</tr>
					</table>


					<br>
					<table>

						<tr>
							<td width="185" align="right" nowrap="nowrap"><font size="2"
								face="Verdana"> <bean:message key="Index.select" />
							</font></td>
							<td width="489" nowrap="nowrap" align="left" height="30"><html:select
									property="index" size="1" styleId="sIE">
									<html:optionsCollection name="IndexWiseWeightBean"
										property="indexcollection" />
								</html:select></td>
						</tr>
						<tr>
							<!--  	<td width="125"  align="right" nowrap="nowrap"> 
        				<html:checkbox name="IndexWiseWeightBean" property="b_showChild" onclick="return test1();"  />
        			</td>
        			<td  nowrap="nowrap" align="left" >	
			   			<font size="2" face="Verdana">  
	  						<bean:message key="IndexComparision.showsi" />
	  					</font>
	  				</td> -->
							<!-- 	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> -->



							<td width="200" align="right" nowrap="nowrap"><html:checkbox
									property="checkChart"></html:checkbox> <font size="2"
								face="Verdana"> <bean:message key="IndexCompose.schart" />
							</font></td>


							<%
								if (ajax1.equals("yes")) {
							%>

							<td width="100" nowrap="nowrap" align="left"><html:submit
									onclick="return go();">
									<bean:message key="Reports.View" />
								</html:submit></td>
							<%
								} else {
							%>

							<td width="200" nowrap="nowrap" align="right"><INPUT
								type="Button" name="view" value="AjaxView"
								onclick="getIndexWeightageDetails()" /></td>
							<%
								}
							%>
						</tr>
					</table>


					<!-- ============================================ For Ajax ================================================= -->
					<div id="Ajaxcontentstart">
						<table align="center">
							<tr>
								<img id="graph" align="top" width="500" height="270" border="0"
									style="display: none;">
							</tr>
							<tr>
								<td>
									<table class="sorted" ID="sortTable" border="1" align="center"
										cellpadding="0" cellspacing="0" style="display: none">
										<thead>
											<tr>
												<th width="150" class="gridStyle-header">Industry Name</th>
												<th width="170" class="gridStyle-header">Mkt. Cap.(In
													Millions)</th>
												<th width="150" class="gridStyle-header">Weightage(%)</th>
											</tr>
										</thead>
										<tbody id="indexMovingTable" bgcolor="#DEE3EF">
										</tbody>
									</table>
								</td>
							</tr>
						</table>

						<table id="nodata" style="display: none" border="0" align="center"
							width="631" height="222" cellspacing="0" cellpadding="0">

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

					</div>
					<!-- ============================================ For Ajax ================================================== -->

					<bean:define id="try3" name="IndexWiseWeightBean"
						property="indweighttable" />
					<bean:size id="dataCount" name="IndexWiseWeightBean"
						property="indweighttable" />

					<div id="chart">
						<logic:equal name="IndexWiseWeightBean" property="checkChart"
							value="on">

							<%
								GraphMethodsPf.ReaddataIndweight(request);
											//	GraphMethodsPf gm = new GraphMethodsPf();
											GraphMethodsPf gm = ConnectInit.getGraphMethodsPf();
											String filename = gm.getGraphChartIndustry(session,
													new PrintWriter(out));
											String graphURL = request.getContextPath()
													+ "/servlet/DisplayChart?filename=" + filename;
							%>
							<table id="strutsgraph" border="0" width="50%" align="center"
								cellpadding="0" cellspacing="0">
								<tr>
									<td><img src="<%=graphURL%>" width="500" height="270"
										border="0"></td>
								</tr>
							</table>


						</logic:equal>
					</div>

					<br>
					</br>
					<table align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td><logic:notEqual value="yes" parameter="compute">

									<table id="selectExchangeMessage" border="1" align="center"
										width="631" height="222" cellspacing="0" cellpadding="0">

										<tr>
											<td bgcolor="#84AADE" align="center" valign="middle">
												<p style="margin-left: 9">
													<font face="Verdana" color="blue" size="5"> <bean:message
															key="IndexCompose.vdata" /></font>
												</p>
											</td>
										</tr>
									</table>

								</logic:notEqual> <logic:equal value="yes" parameter="compute">
									<logic:equal value="0" name="dataCount">

										<table id="noStrutsData" border="1" align="center" width="631"
											height="222" cellspacing="0" cellpadding="0">
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

									</logic:equal></td>
						</tr>
					</table>

					<logic:notEqual value="0" name="dataCount">
						<div id=contentstart>
							<table border="0" width="800" cellpadding="2" cellspacing="0"
								align="center" id="tabular">
								<tr>
									<td>
										<table class="sorted" ID="sortTabletable" border="1"
											align="center" cellpadding="3" cellspacing="0">
											<thead>
												<tr>
													<!-- <table border="1" width="80%" align="center" cellpadding="2" cellspacing="0" id="t1" class="sortable" bordercolor="white">      
             		<tr> -->
													<th class="gridStyle-header" id="Name" width="30%"
														align="center"><span><b><bean:message
																	key="IndexWiseWeight.Name" /></b></span></th>
													<th width="20%" class="gridStyle-header" id="Market"
														align="center"><span><b><bean:message
																	key="IndexWiseWeight.Market" /></b></span> cap.</th>
													<th class="gridStyle-header" id="Weightage" width="20%"
														align="center" nowrap="nowrap"><span><b><bean:message
																	key="IndexWiseWeight.Weightage" />(%) </b></span></th>
												</tr>
											</thead>
											<tbody>
												<%
													int i = 1;
																	String bcolor = "";
												%>
												<logic:iterate id="try3" property="indweighttable"
													name="IndexWiseWeightBean">
													<%
														if (i % 2 == 0) {
																				bcolor = "#84AADE";
																			} else {
																				bcolor = "#DEE3EF";
																			}
													%>
													<tr bgcolor=<%=bcolor%>>
														<td width="30%" align="left" height="37" nowrap="nowrap"
															axis="sstring" headers="Name"
															title='<bean:write name="try3" property="industryname"/>'>
															<font face="Verdana" size="2" color="blue"> <bean:write
																	name="try3" property="industryname" /></font>
														</td>
														<td width="20%" align="right" nowrap="nowrap"
															axis="number" headers="Market"
															title='<bean:write name="try3" property="marketcap"/>'>
															<font face="Verdana" size="2" color="blue"> <bean:write
																	name="try3" property="marketcap" /></font>
														</td>
														<td width="20%" align="right" nowrap="nowrap"
															axis="number" headers="Weightage"
															title='<bean:write name="try3" property="weightage"/>'>
															<font face="Verdana" size="2" color="blue"> <bean:write
																	name="try3" property="weightage" />%
														</font>
														</td>
													</tr>
													<%
														i = i + 1;
													%>
												</logic:iterate>
											</tbody>

											<tr>
												<td bgcolor="#314573" width="30%" align="left" height="37"
													nowrap="nowrap"><font face="Verdana" size="2"
													color="white"> <bean:message
															key="StockPerformance.Total" /></font></td>
												<td bgcolor="#314573" width="20%" align="right"
													nowrap="nowrap"><font face="Verdana" size="2"
													color="white"> <bean:write
															name="IndexWiseWeightBean" property="val" /></font></td>
												<td bgcolor="#314573" width="20%" align="right"
													nowrap="nowrap"><font face="Verdana" size="2"
													color="white"> <bean:write
															name="IndexWiseWeightBean" property="total" />%
												</font></td>

											</tr>
										</table>

									</td>
								</tr>
							</table>
						</div>
					</logic:notEqual>
					</logic:equal>



					<div id="Details">
						<table id="hiddenTable">
							<tr>
								<td></td>
								<td><b> <font size="2" face="Arial"> <bean:message
												key="Index.select" />&nbsp;:
									</font>
								</b></td>
								<td><bean:write name="IndexWiseWeightBean"
										property="indexName" /></td>
							</tr>
						</table>
					</div>
					<html:hidden property="indexName" name="IndexWiseWeightBean" />
					<html:hidden property="defaultVal" />
					<html:hidden property="clear" />
					<html:hidden property="compute" value="no"></html:hidden>
				</html:form></td>
		</tr>
	</table>
</body>

<script language="javascript">
	var Button;
	var man1 = document.getElementById("hiddenTable");
	man1.style.display = "none";

	function test1() {
		document.forms[0].submit();
	}
	function PrintThisPage() {
		var sOption = "toolbar=yes,location=no,directories=yes,menubar=yes,";
		sOption += "scrollbars=yes,width=750,height=600,left=100,top=25";
		var printHead = document.getElementById('PageTitle').innerHTML;
		//var moreDet = document.getElementById('hiddenTable').innerHTML;
		var winprint = window.open("", "", sOption);
		winprint.document.open();
		winprint.document.write('<html><body>');
		winprint.document.write(printHead);
		winprint.document.write('<br>');
		winprint.document.write('&nbsp;&nbsp;&nbsp;');
		if (Button == "AjaxButton") {
			var moreDet = DWRUtil.getText("index");
			var sWinHTML = document.getElementById('Ajaxcontentstart').innerHTML;
			winprint.document.write("<font size=3>Index :" + moreDet
					+ "</font>");
			winprint.document.write('<br><br>');
		} else {
			var sWinHTML = document.getElementById('contentstart').innerHTML;
			var moreDet = document.getElementById('hiddenTable').innerHTML;
			winprint.document.write(moreDet);
			winprint.document.write('<br><br>');
		}

		winprint.document.write('<p align="center">');
		winprint.document.write('</p>');
		var checkChart = document.getElementById('checkChart').innerHTML;
		if (checkChart == "on") {
			var chrt = document.getElementById('chart').innerHTML;
			winprint.document.write(chrt);
		}

		winprint.document.write('<p align="center">');
		winprint.document.write(sWinHTML);
		winprint.document.write('</p>');
		winprint.document.write('</body></html>');
		winprint.document.close();
		winprint.focus();
	}
	function PrintThisPageaj() {

		var sOption = "toolbar=yes,location=no,directories=yes,menubar=yes,";
		sOption += "scrollbars=yes,width=750,height=600,left=100,top=25";
		var showChart = document.getElementById('graph').innerHTML;
		var sWinHTML = document.getElementById('sortTable').innerHTML;
		var printHead = document.getElementById('PageTitle').innerHTML;
		var moreDet = document.getElementById('hiddenTable').innerHTML;
		var winprint = window.open("", "", sOption);
		winprint.document.open();
		winprint.document.write('<html><body>');
		winprint.document.write(printHead);
		winprint.document.write('<br>');
		winprint.document.write('&nbsp;&nbsp;&nbsp;');
		winprint.document.write('&nbsp;&nbsp;&nbsp;');
		winprint.document.write(moreDet);
		winprint.document.write('<br><br>');
		winprint.document.write('<p align="center">');
		winprint.document.write(showChart);
		winprint.document.write('</p>');
		winprint.document.write('<p align="center">');
		winprint.document.write(sWinHTML);
		winprint.document.write('</p>');
		winprint.document.write('</body></html>');
		winprint.document.close();
		winprint.focus();
	}
	function go() {
		var i = 0;

		var fields = new Array();
		if (document.forms[0].index.value == 0) {
			fields[i++] = "Select Index is required";
		}
		if (fields.length > 0) {
			alert(fields.join('\n'));
			return false;
		} else {
			document.forms[0].compute.value = "yes";
			document.forms[0].defaultVal.value = "no";
			return true;
		}
	}
	function initSort() {

		mySorted = new SortedTable();
		mySorted.colorize = function() {
			for (var i = 0; i < this.elements.length; i++) {
				if (i % 2) {
					this.changeClass(this.elements[i], 'even', 'odd');
				} else {
					this.changeClass(this.elements[i], 'odd', 'even');
				}
			}
		}
		mySorted.onsort = mySorted.colorize;
		mySorted.onmove = mySorted.colorize;
		mySorted.colorize();
	}

	function viewFunc() {
		var i = 0;
		if (document.forms[0].D1.value == "0") {
			alert("Index Name is required");
			return false;
		} else
			return true;
	}
	/*============================================== for dwr =========================================================== */
	function getIndexWeightageDetails() {
		DWRUtil.useLoadingMessage();
		var index = DWRUtil.getValue("index");
		MoveTable.getIndexWiseWeightage(fillTable, index);
	}
	var industryname = function(StockDetails) {
		return '<font face="Verdana" color="blue" size="2">'
				+ StockDetails.industryname + '</font>'
	};
	var marketcap = function(StockDetails) {
		return '<font face="Verdana" color="blue" size="2">'
				+ StockDetails.marketcap + '</font>'
	};
	var weightage = function(StockDetails) {
		return '<font face="Verdana" color="blue" size="2">'
				+ StockDetails.weightage + '</font>'
	};
	//alert(stockId);

	function fillTable(indexMovingTable) {
		var a = new Array();
		var index = DWRUtil.getValue("index");
		var check2 = DWRUtil.getValue("checkChart");
		a = indexMovingTable;
		var length = a.length;

		if (length != 1) {
			Button = "AjaxButton";
			changeDisplay("selectExchangeMessage", "none");
			changeDisplay("nodata", "none");
			changeDisplay("noStrutsData", "none");
			changeDisplay("sortTabletable", "none");
			changeDisplay("strutslinks", "none");
			changeDisplay("ajaxlinks", "inline");
			changeDisplay("sortTable", "inline");
			if (check2 != 0) {
				changeDisplay("strutsgraph", "none");
				changeDisplay("graph", "inline");
				//GraphMethods.getGraphChartCompany1(showGraph,index);
				GraphMethods.getGraphChartIndustry(showGraph, index);
			}
			DWRUtil.removeAllRows("indexMovingTable");
			DWRUtil.addRows("indexMovingTable", indexMovingTable, [
					industryname, marketcap, weightage ]);
		} else {
			changeDisplay("sortTabletable", "none");
			changeDisplay("sortTable", "none");
			changeDisplay("noStrutsData", "none");
			changeDisplay("strutslinks", "none");
			changeDisplay("ajaxlinks", "none");
			changeDisplay("nodata", "inline");
			changeDisplay("selectExchangeMessage", "none");
		}

	}
	function showGraph(graphURL) {
		//alert(graphURL);
		var index = DWRUtil.getValue("index");
		changeDisplay("graph", "inline");
		var imgid = $("graph");
		imgid.setAttribute("src", graphURL);

	}
</script>
</html:html>
