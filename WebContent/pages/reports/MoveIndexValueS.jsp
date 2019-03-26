<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page language="java" import="app.*"%>
<%@ page import="harrier.income.com.report.*"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="uk.ltd.getahead.dwr.*"%>
<%@page import="org.apache.log4j.Logger"%>
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
<head>
<html:base />
<title></title>
<link href="../StyleSheetM.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
<script language="javascript" src="box_ex.js"></script>
<script language="javascript">
	var c2 = new CodeThatCalendar(caldef2);
</script>

<script type="text/javascript" src="../Script/Event.js"></script>
<script type="text/javascript" src="../Script/SortedTable.js"></script>
<script type='text/javascript'
	src='/Stockpile/dwr/interface/MoveTable.js'></script>
<script type='text/javascript'
	src='/Stockpile/dwr/interface/GraphMethods.js'></script>
<script type='text/javascript' src='/Stockpile/dwr/engine.js'></script>
<script type='text/javascript' src='/Stockpile/dwr/util.js'></script>

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
					action="MovingIndexValue">

					<%
						if (request.isRequestedSessionIdValid()) {
					%>
					<jsp:setProperty name="MovingIndexValueBean" property="userid1"
						value='<%=session.getAttribute("userid")%>' />
					<jsp:setProperty name="MovingIndexValueBean" property="roleId_move"
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
												key="MovingIndex.title" /></b>
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
									//	Logging.error(" Error :"+e.getMessage());
								}
					%>
					<!-- if compute ="yes" display links -->
					<logic:equal value="yes" parameter="varTableData">
						<logic:notEqual value="0" name="dataCount">
							<table align="right" id="strutslinks">
								<tr>
									<td width="212" align="right" nowrap="nowrap"></td>
									<%
										String temp_path = "./FileDownload.jsp?type=24&filename=IndexMovement.xls";
														String xml_path = "./FileDownloadXmlNew.jsp?type=24&filename=IndexMovement.xml";
														String path_pdf = "./FileDownload_Pdf.jsp?type=24&filename=IndexMovement.pdf";
														String email_url = "./EmailReport.jsp?switch_type=24&cas=24&rname=IndexMovement.xls";
									%>

									<td colspan="2" nowrap="nowrap" align="left"><font
										size="1" face="Verdana" color="blue"> <!-- Printer friendly -->
											<a href="javascript:PrintThisPage();"><font size="1"
												face="Verdana" color="blue"><bean:message
														key="LatestDivisor.printerf" /></font></a> &nbsp;&nbsp; <!-- DownLoad Excel -->
											<a href='<%=temp_path%>'><font size="1" face="Verdana"
												color="blue"><bean:message
														key="LatestDivisor.downloade" /></font> </a> &nbsp;&nbsp; <!-- DownLoad Xml -->
											<a href='<%=xml_path%>'><font size="1" face="Verdana"
												color="blue">Export to Xml</font> </a> &nbsp;&nbsp; <!-- Email Report -->
											<a href='<%=email_url%>'><font size="1" face="Verdana"
												color="blue"><bean:message key="LatestDivisor.emailr" /></font></a>
											<!-- PDF Report --> <a href='<%=path_pdf%>'><font
												size="1" face="Verdana" color="blue"><bean:message
														key="LatestDivisor.pdfr" /></font></a>
									</font></td>
								</tr>
							</table>
						</logic:notEqual>
					</logic:equal>

					<%
						String excel_pathaj = "./FileDownload.jsp?type=24&filename=IndexMovement.xls";
								String xml_pathaj = "./FileDownloadXmlNew.jsp?type=24&filename=IndexMovement.xml";
								String pdf_pathaj = "./FileDownload_Pdf.jsp?type=24&filename=IndexMovement.pdf";
								String str_urlaj = "./EmailReport.jsp?switch_type=24&cas=24&rname=IndexMovement.xls";
					%>

					<table id="ajaxlinks" align="right" style="display: none">
						<tr>
							<td colspan="2" nowrap="nowrap" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
								<font size="1" face="Verdana" color="blue"> <!-- Printer friendly -->
									<a href="javascript:PrintThisPage();"><bean:message
											key="LatestDivisor.printerf" /></a> &nbsp;&nbsp; <!-- DownLoad Excel -->
									<a href='<%=excel_pathaj%>'><bean:message
											key="LatestDivisor.downloade" /></a> &nbsp;&nbsp; <!-- DownLoad Xml -->
									<a href='<%=xml_pathaj%>'>Export to Xml</a> &nbsp;&nbsp; <a
									href='<%=pdf_pathaj%>'><bean:message
											key="LatestDivisor.pdfr" /></a> &nbsp;&nbsp; <!-- Email Report -->
									<a href='<%=str_urlaj%>'><bean:message
											key="LatestDivisor.emailr" /></a>
							</font>
							</td>
						</tr>
					</table>

					<br>


					<table border="0" width="100%" cellspacing="0" cellpadding="3"
						height="30">
						<tr>
							<td align="left" width="102" nowrap="nowrap">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td align="right" width="95" nowrap="nowrap"><font size="2"
								color="black" face="Verdana"> <bean:message
										key="LatestDivisor.iname" />&nbsp;:
							</font></td>
							<td align="left" nowrap="nowrap" width="724"><html:select
									property="selectIndex" size="1">
									<html:optionsCollection property="indexCollection" />
								</html:select></td>

						</tr>

						<tr>
							<td align="left" width="102" nowrap="nowrap">
								&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td align="right" nowrap="nowrap" width="20"><font
								face="Verdana" size="2"> <bean:message
										key="IndexDivisor.schart" />
							</font></td>
							<td align="left" nowrap="nowrap" width="328"><html:select
									property="selectChart" size="1" onblur="">
									<html:optionsCollection property="chartCollection" />
								</html:select></td>
						</tr>
						<!-- 	<tr>
			        	  <td align="left" width="102" nowrap="nowrap">
     	 	  				&nbsp;&nbsp;&nbsp;&nbsp;
       					  </td>
		          	  	  <td align="right" width="80" nowrap="nowrap" >
			        	    	<font face="Verdana" size="2">    
			        	    		 <html:checkbox property="check_moving_avg"/> 
			        	    		 
		       	 		    	 </font>	 
			 		 	  </td>
						 
						  <td align="left" nowrap="nowrap" width="100">  
		               			<font face="Verdana" size="2">
									 <bean:message key="IndexDivisor.vmavg" />
					 			</font>
					 	  </td> 
					</tr> -->
					</table>

					<!-- Third Table for Chechk box of View Moving Average , text box for "from date" and "to date": and viwe button-->
					<table border="0" width="100%" cellspacing="0" cellpadding="3"
						height="10">



						<tr>

							<td align="right" nowrap="nowrap" width="200"><font
								face="Verdana" size="2"> <bean:message
										key="corporate.Fdate" />
							</font></td>
							<td align="right" nowrap="nowrap" width="30"><html:text
									property="move_from" readonly="true" size="10" /></td>

							<td align="left" nowrap="nowrap" width="39"><html:button
									property="from_button" onclick="c2.popup('move_from');"
									value="..." /></td>

							<td align="right" nowrap="nowrap" width="90"><font
								face="Verdana" size="2"> <bean:message
										key="corporate.Tdate" />:
							</font></td>

							<td align="right" nowrap="nowrap" width="50"><font
								face="Verdana" size="2"> <html:text property="move_to"
										readonly="true" size="10" />
							</font></td>

							<td align="left" nowrap="nowrap" width="39"><html:button
									property="to_button" onclick="c2.popup('move_to');" value="..." />
							</td>

							<td align="left" nowrap="nowrap" width="380">
								<%
									if (ajax1.equals("yes")) {
								%> <html:submit onclick="return go()">
									<bean:message key="Reports.View" />
								</html:submit> <%
 	} else {
 %> <INPUT type="Button" name="View" value="AjaxView"
								onclick="callMovingIndexTableA()" /> <%
 	}
 %>
							</td>
						</tr>
					</table>

					<!-- ========================== For Ajax ================================================= -->
					<table id="nodata" border="0" align="center" width="631"
						height="222" cellspacing="0" cellpadding="0"
						style="display: none;">
						<!-- 	<tr>
          			<td id="indexcompose" bgcolor="#cacaca" align="center" valign="middle">
          				<p style="margin-left: 9"><font face="Verdana" color="blue" size="5">
            			<bean:message key="IndexCompose.vdata" /></font></p>
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
					<p></p>
					<div id="Ajaxcontentstart">
						<table align="center">
							<tr>
								<img id="graph" align="top" width="500" height="270" border="0"
									style="display: none;">
							</tr>
							<tr>
								<td>
									<table class="sorted" ID="sortTable" style="display: none"
										border="1" align="center" cellpadding="0" cellspacing="0"
										bgcolor="#EFEFEF">
										<!--  <table border="1"> -->
										<thead>
											<tr>
												<th class="gridStyle-header">Trading Date</th>
												<th class="gridStyle-header">Close</th>
												<th class="gridStyle-header">Mkt.Cap(in millions)</th>
												<th class="gridStyle-header">Divisor</th>
											</tr>
										</thead>

										<tbody id="indexMovingTable" bgcolor="#DEE3EF">
										</tbody>
									</table>
								<td>
							</tr>
						</table>
					</div>
					<!-- ========================== For Ajax ================================================= -->

					<logic:notEqual value="yes" parameter="varTableData">
						<table id="indexcompose" border="0" align="left" width="700"
							height="222" cellspacing="0" cellpadding="0">
							<tr>
								<td width="99"></td>
								<td bgcolor="#84AADE" align="center" valign="middle">
									<p style="margin-left: 9">
										<font face="Verdana" color="blue" size="5"> <bean:message
												key="StockHighLow.smess" />
										</font>
									</p>
								</td>
							</tr>
						</table>
					</logic:notEqual>


					<logic:equal value="yes" parameter="varTableData">

						<bean:define id="details" name="MovingIndexValueBean"
							property="indexMovingTable" />
						<bean:size id="dataCount" name="MovingIndexValueBean"
							property="indexMovingTable" />

						<logic:equal value="0" name="dataCount">

							<table id="noStrutsData" border="0" align="left" width="631"
								height="222" cellspacing="0" cellpadding="0">
								<tr>
									<td width="99"></td>
									<td class="gridStyle-message" align="center" valign="middle">
										<p style="margin-left: 9">
											<bean:message key="IndexCompareOHCL.ndata" />
										</p>
									</td>
								</tr>
							</table>
						</logic:equal>

						<logic:notEqual value="0" name="dataCount">
							<div id="contentstart">

								<%
									String varchart = request
															.getParameter("selectChart");
													if (!(varchart.equals("0"))) {
								%>
								<table border="0" width="50%" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td>
											<%
												//	GraphMethodsPf objGM = new GraphMethodsPf();
																	GraphMethodsPf objGM = ConnectInit
																			.getGraphMethodsPf();
																	objGM.getGraphChart1(session, new PrintWriter(
																			out), "maverage");
																	String filename = objGM.getFilename();
																	//String graphURL=objGM.getGraphURL(); 
																	String graphURL1 = request.getContextPath()
																			+ "/servlet/DisplayChart?filename="
																			+ filename;
																	log.info("After calling getGraphchart");
											%> <img id="strutsgraph" src="<%=graphURL1%>"
											width="500" height="270" border="0"> <!-- usemap="#< %= filename %>" -->
										</td>
									</tr>
								</table>
								<%
									}
								%>

								<table class="sorted" ID="sortTabletable" border="0"
									align="center" cellpadding="0" cellspacing="0"
									bgcolor="#EFEFEF">
									<thead>
										<tr>


											<!--<table border="1"  width="60%"  align="center" cellpadding="2" cellspacing="0" id="t1" class="sortable" bordercolor="#EFEFEF">
        					<tr>-->

											<th nowrap="nowrap" align="center" id="trddate"
												class="gridStyle-header"><span><b><bean:message
															key="IndexDivisor.trddate" /></b></span></th>

											<th align="center" nowrap="nowrap" id="close"
												class="gridStyle-header"><span><b><bean:message
															key="IndexCompareOHCL.close" /></b></span></th>

											<th align="center" nowrap="nowrap" id="mcapimil"
												class="gridStyle-header"><span><b><bean:message
															key="MovingIndex.mcapimil" /></b></span></th>

											<th align="center" nowrap="nowrap" id="divisor"
												class="gridStyle-header"><span><b><bean:message
															key="indcurrwise.divisor" /></b></span></th>


										</tr>
									</thead>
									<tbody>
										<%
											int i = 1;
															String bcolor = "";
										%>
										<logic:iterate id="details" name="MovingIndexValueBean"
											property="indexMovingTable">
											<%
												if (i % 2 == 0) {
																		bcolor = "#84AADE";
																	} else {
																		bcolor = "#DEE3EF";
																	}
											%>
											<tr bgcolor=<%=bcolor%>>

												<td valign="middle" nowrap="nowrap" align="left" axis="date"
													headers="trddate"
													title='<bean:write name="details" property="tradingDate"/>'>
													<font face="Verdana" size="2" color="blue"> <bean:write
															name="details" property="tradingDate" />
												</font>
												</td>

												<td align="right" valign="middle" axis="number"
													headers="close" nowrap="nowrap"
													title='<bean:write name="details" property="close"/>'>
													<font face="Verdana" size="2" color="blue"> <bean:write
															name="details" property="close" />
												</font>
												</td>

												<td align="right" valign="middle" axis="number"
													headers="mcapimil" nowrap="nowrap"
													title='<bean:write name="details" property="marketCap"/>'>
													<font face="Verdana" size="2" color="blue"> <bean:write
															name="details" property="marketCap" />
												</font>
												</td>

												<td align="right" valign="middle" axis="number"
													headers="divisor" nowrap="nowrap"
													title='<bean:write name="details" property="divisor"/>'>
													<font face="Verdana" size="2" color="blue"> <bean:write
															name="details" property="divisor" />
												</font>
												</td>

											</tr>
											<%
												i = i + 1;
											%>
										</logic:iterate>
									</tbody>
								</table>

							</div>
						</logic:notEqual>
					</logic:equal>

					<div id="hiddenTable">
						<table width="100%" align="left">

							<tr>
								<td width="125" align="right"><font size="2" face="Verdana"><b>
											<bean:message key="corporate.Fdate" />&nbsp;:
									</b> </font></td>

								<td><bean:write name="MovingIndexValueBean"
										property="move_from" /></td>

							</tr>

							<tr>
								<td width="125" align="right"><font size="2" face="Verdana"><b>
											<bean:message key="corporate.Tdate" />&nbsp;:
									</b> </font></td>

								<td><bean:write name="MovingIndexValueBean"
										property="move_to" /></td>
							</tr>
						</table>
					</div>
					<html:hidden property="varTableData" value="no"></html:hidden>
					<html:hidden property="chart"></html:hidden>

				</html:form></td>
		</tr>
	</table>
</body>
<!-- javascript for this JSP page -->
<script language="javascript">
	var Button;
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
		if ((document.forms[0].move_from.value) == "")
			document.forms[0].move_from.value = td + s + mnth + s + yr;
		if ((document.forms[0].move_to.value) == "")
			document.forms[0].move_to.value = td + s + mnth + s + yr;
		initSort();
	}
	function test1() {
		document.forms[0].submit();
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

	function popprinter(url) {
		newwindow = window
				.open(url, 'name',
						'height=500,width=900,scrollbars=yes,left=10,top=30,menubar=yes,status=yes');
		if (window.focus) {
			//newwindow.document.write(url);
			newwindow.focus()
		}
	}
	function go() {
		var objTo = document.forms[0].move_to;
		document.forms[0].varTableData.value = "yes";

		if (document.forms[0].check_moving_avg.checked == true)
			document.forms[0].check_moving_avg.value = "checked";
		else
			document.forms[0].check_moving_avg.value = "unchecked";
		if ((checkdatecurrent(objTo)) == false) {
			alert("ToDate should be Less Than CurrentDate.");
			objTo.focus();
			objTo.select();
			return false;
		} else
			return true;

	}

	function ExcelCheck() {
		alert('INside go()');
		document.forms[0].varExcelflag.value = "yes";
	}

	function selectChart() {
		//document.forms[0].chart.value="yes";
		alert(document.forms[0].selectchart.value)
		document.forms[0].chart.value = document.forms[0].selectchart.value;
	}

	function PrintThisPage() {
		var sOption = "toolbar=yes,location=no,directories=yes,menubar=yes,";
		sOption += "scrollbars=yes,width=850,height=550,left=100,top=25";
		//var sWinHTML = document.getElementById('contentstart').innerHTML; 
		var printHead = document.getElementById('PageTitle').innerHTML;
		//var moreDet = document.getElementById('hiddenTable').innerHTML;
		var winprint = window.open("", "", sOption);
		winprint.document.open();
		winprint.document.write('<html><body>');
		winprint.document.write(printHead);
		winprint.document.write('<br>');
		//winprint.document.write(moreDet); 	
		if (Button == "AjaxButton") {
			var moreDet = DWRUtil.getText("selectIndex");
			var fromdate = DWRUtil.getValue("move_from");
			var todate = DWRUtil.getValue("move_to");
			var sWinHTML = document.getElementById('Ajaxcontentstart').innerHTML;
			winprint.document.write("<font size=3>From Date :" + fromdate
					+ "<br>To Date :" + todate + "</font><br>");
			winprint.document.write("<font size=3>Index :" + moreDet
					+ "</font>");
		} else {
			var sWinHTML = document.getElementById('contentstart').innerHTML;
			var moreDet = document.getElementById('hiddenTable').innerHTML;
			winprint.document.write(moreDet);
		}
		winprint.document.write('<br>');
		winprint.document.write('<br>');
		winprint.document.write('<br>');
		winprint.document.write('<br>');
		winprint.document.write('<br>');
		winprint.document.write(sWinHTML);
		winprint.document.write('</body></html>');
		winprint.document.close();
		winprint.focus();
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

	/*===================================== For DWR ========================================================================*/

	function callMovingIndexTableA() {
		DWRUtil.useLoadingMessage();
		var move_from = DWRUtil.getValue("move_from");
		var move_to = DWRUtil.getValue("move_to");
		var selectIndex = DWRUtil.getValue("selectIndex");
		var chartType = DWRUtil.getValue("selectChart");
		//var span = DWRUtil.getValue("selectSpan");
		var mavg = DWRUtil.getValue("check_moving_avg");
		MoveTable.getIndexMovingTable(fillTable, move_from, move_to,
				selectIndex);

	}
	var getTradingDate = function(indexMovingDetail) {
		return '<font face="Verdana" color="blue" size="2">'
				+ indexMovingDetail.tradingDate + '</font>'
	};
	var getClose = function(indexMovingDetail) {
		return '<font face="Verdana" color="blue" size="2">'
				+ indexMovingDetail.close + '</font>'
	}; // if we return to using dates, add .toLocaleDateString()
	var getMarketCap = function(indexMovingDetail) {
		return '<font face="Verdana" color="blue" size="2">'
				+ indexMovingDetail.marketCap + '</font>'
	};
	var getDivisor = function(indexMovingDetail) {
		return '<font face="Verdana" color="blue" size="2">'
				+ indexMovingDetail.divisor + '</font>';
	};

	function fillTable(indexMovingTable) {
		var move_from = DWRUtil.getValue("move_from");
		var move_to = DWRUtil.getValue("move_to");
		var selectIndex = DWRUtil.getValue("selectIndex");
		var chartType = DWRUtil.getValue("selectChart");
		//var span = DWRUtil.getValue("selectSpan");
		var mavg = DWRUtil.getValue("check_moving_avg");
		var a = new Array();
		a = indexMovingTable;
		if (a.length) {
			//alert("");
			Button = "AjaxButton";
			changeDisplay("indexcompose", "none");
			changeDisplay("nodata", "none");
			changeDisplay("noStrutsData", "none");
			changeDisplay("sortTabletable", "none");
			changeDisplay("sortTable", "inline");
			changeDisplay("strutslinks", "none");
			changeDisplay("ajaxlinks", "inline");
			changeDisplay("graph", "none");
			changeDisplay("strutsgraph", "none");
			if (chartType != 0) {
				changeDisplay("graph", "block");
				changeDisplay("strutsgraph", "none");
				GraphMethods.getGraphChart1(showGraph, selectIndex, move_from,
						move_to, chartType, "maverage");
			}
			DWRUtil.removeAllRows("indexMovingTable");
			DWRUtil.addRows("indexMovingTable", indexMovingTable, [
					getTradingDate, getClose, getMarketCap, getDivisor ]);
		} else {
			changeDisplay("sortTable", "none");
			changeDisplay("noStrutsData", "none");
			changeDisplay("sortTabletable", "none");
			changeDisplay("indexcompose", "none");
			changeDisplay("nodata", "inline");
			changeDisplay("graph", "none");
			changeDisplay("strutsgraph", "none");
			changeDisplay("strutslinks", "none");
			changeDisplay("ajaxlinks", "none");
		}

	}

	function showGraph(graphURL) {
		//alert(graphURL); 	
		var imgid = $("graph");
		imgid.setAttribute("src", graphURL);
	}
</script>
</html:html>
