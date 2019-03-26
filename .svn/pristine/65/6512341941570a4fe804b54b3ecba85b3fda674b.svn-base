
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.ParseException"%>
<%@page import="com.harrier.initializeation.ConnectInit"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="app.*"%>
<%@ page import="harrier.income.com.report.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
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
		return;
	}
%>
<html:html>
<html:base />
<head>
<link rel="stylesheet" type="text/css" href="../StyleSheetM.css" />
<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
<script language="javascript" src="box_ex.js"></script>

<script type='text/javascript'
	src='/Stockpile/dwr/interface/IndexComposeReport.js'></script>
<script type='text/javascript' src='/Stockpile/dwr/engine.js'></script>
<script type='text/javascript' src='/Stockpile/dwr/util.js'></script>
<script type='text/javascript'
	src='/Stockpile/dwr/interface/GraphMethods.js'></script>

<script type="text/javascript" src="../Script/Event.js"></script>
<script type="text/javascript" src="../Script/SortedTable.js"></script>
<script type="text/javascript" src="../Script/SortedTable1.js"></script>

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


<style type="text/css">

/* table styles*/
.sorted1 td, th {
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
function hideLeftCol(id)
{
if(this.document.getElementById(id).style.display=='none')
{
this.document.getElementById(id).style.display='inline';
document.getElementById("HideHandle").src = '../closeImage.gif';
// this.document.getElementById(id).style.width='10px';
Set_Cookie('showLeftCol','true',30,'/','','');
var show = Get_Cookie('showLeftCol');
//document['HideHandle'].src = 'images/hide.gif';
document.getElementById("HideHandle").src = '../open.gif';
}
else{
// this.document.getElementById(id).style.display='none';
this.document.getElementById(id).style.display='none';
document.getElementById("HideHandle").src = '../openImage.gif';
Set_Cookie('showLeftCol','false',30,'/','','');
var show = Get_Cookie('showLeftCol');
//document['HideHandle'].src = 'images/show.gif';
}
}
</script>

</head>
<body onload="initSort()" onunload="checkforindex()">
	<jsp:useBean id="IndexComposeBean" scope="session"
		class="harrier.income.com.report.IndexComposeForm" />


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
			<td align="left" valign="top"><html:form action="/IndexCompose">
					<jsp:setProperty name="IndexComposeBean" property="index" />
					<jsp:setProperty name="IndexComposeBean" property="userid1"
						value='<%=session.getAttribute("userid")%>' />
					<jsp:setProperty name="IndexComposeBean" property="role_id1"
						value='<%=session.getAttribute("role_id")%>' />

					<!-- Heading  "Index Composition Report"  -->
					<div id="PageTitle">
						<table width="1000" cellspacing="0" cellpadding="0">
							<tr>
								<td class="subHeader" width="665" align="center" colspan="2"
									nowrap="nowrap"><font size="3" face="Verdana"> <b><bean:message
												key="IndexCompose.title" /></b>

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

					<!-- div tag for printerfriendly -->
					<logic:equal value="yes" parameter="compute">
						<!-- if compute ="yes" display links -->
						<!-- Links  -->
						<logic:notEqual value="0" name="dataCount">
							<%
								String var = request.getParameter("index");
												String iename1 = var;
												String excel_path = "./FileDownload.jsp?var="
														+ var
														+ "&type=1&filename=IndexCompositionReport.xls&technology=noAjax";
												String xml_path = "./FileDownloadXmlNew.jsp?var="
														+ var
														+ "&iename="
														+ iename1
														+ "&type=1&filename=IndexCompositionReport.xml";
												String pdf_path = "./FileDownload_Pdf.jsp?type=1&filename=IndexCompositionReport.pdf";
												String str_url = "./EmailReport.jsp?switch_type=1&cas=1&rname=IndexCompositionReport.xls&varid="
														+ var;
							%>
							<table id="strutslinks" align="center">
								<tr>
									<td colspan="2" nowrap="nowrap" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
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

					<%
						String excel_pathaj = "./FileDownload.jsp?type=1&filename=IndexCompositionReport.xls&technology=Ajax";
								String xml_pathaj = "./FileDownloadXmlNew.jsp?type=1&filename=IndexCompositionReport.xml";
								String pdf_pathaj = "./FileDownload_Pdf.jsp?type=1&filename=IndexCompositionReport.pdf";
								String str_urlaj = "./EmailReport.jsp?switch_type=1&cas=1&rname=IndexCompositionReport.xls";
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
					<br>
					<logic:equal value="on" property="checkChart"
						name="IndexComposeBean">
						<jsp:setProperty name="IndexComposeBean" property="graph"
							value='<%=request.getParameter("graphURL")%>' />
					</logic:equal>
					<html:hidden property="indexName" name="IndexComposeBean" />
					<table width="640">
						<tr>
							<!-- Lable for "Select IndexName"  -->
							<td width="185" align="right" nowrap="nowrap"><font size="2"
								face="Verdana"> <bean:message key="Index.select" />
							</font></td>
							<!-- Select List for Index -->
							<td width="489" nowrap="nowrap" align="left" height="30"><html:select
									property="index" size="1" styleId="sIE">
									<html:optionsCollection name="IndexComposeBean"
										property="indexcollection" />
								</html:select></td>
						</tr>
					</table>
					<html:hidden property="check" name="IndexComposeBean" />
					<table>
						<tr>

							<!--  	<td width="203" align="right" nowrap="nowrap"> 
        		<html:checkbox name="IndexComposeBean" property="b_showChild" onclick="return test1();"  />
        	</td>
        	<td  width="203" nowrap="nowrap" align="left" >	
			   	<font size="2" face="Verdana">
	  				<bean:message key="IndexComparision.showsi" />
	  			</font>
	  		</td> -->



							<td width="203" align="right" nowrap="nowrap"><html:checkbox
									property="checkChart" onclick="showChart();"></html:checkbox></td>
							<td nowrap="nowrap" align="left"><font size="2"
								face="Verdana"> <bean:message key="IndexCompose.schart" />
							</font></td>

							</td>


							<td width="203" nowrap="nowrap" align="right">
								<%
									if (ajax1.equals("yes")) {
								%> <html:submit onclick="return go();">
									<bean:message key="Reports.View" />
								</html:submit>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

								<%
									} else {
								%> <INPUT type="Button" name="view" value="AjaxView"
								onclick="getIndexCompositionReport()" /> <%
 	}
 %>

							</td>
						</tr>

					</table>

					<div id="Ajaxcontentstart">
						<table align="center">
							<tr>
								<img id="graph" align="top" width="500" height="270" border="0"
									style="display: none;">
							</tr>
							<tr>
								<td>
									<table width="200%" class="sorted1" ID="sortTable" border="1"
										align="center" cellpadding="0" cellspacing="0"
										bgcolor="#EFEFEF" style="display: none">

										<thead>
											<tr>
												<th width="90" class="gridStyle-header">Stock Name</th>
												<th width="90" class="gridStyle-header">Total Shares</th>
												<th width="180" class="gridStyle-header">Investible
													Weight Factor</th>
												<th class="gridStyle-header">Mkt.Lot</th>
												<th width="90" class="gridStyle-header">Price(LTP)</th>
												<th width="90" class="gridStyle-header">Pric(Last)</th>
												<th class="gridStyle-header">Curr.</th>
												<th width="130" class="gridStyle-header">Curr. Exch.
													Rate</th>
												<th class="gridStyle-header">Market Cap</th>
												<th width="150" class="gridStyle-header">Adjusted
													Market Cap</th>
												<th class="gridStyle-header">Weight</th>
												<th width="50" class="gridStyle-header">Date</th>
											</tr>
										</thead>

										<tbody id="tablebody" bgcolor="#DEE3EF">
										</tbody>
									</table>
								</td>
							</tr>

						</table>

					</div>



					<table id="nodata" border="0" align="center" width="631"
						height="222" cellspacing="0" cellpadding="0"
						style="display: none;">
						<!-- 	<tr>
          			<td id="indexcompose" bgcolor="#cacaca" align="center" valign="middle">
          				<p style="margin-left: 9"><font face="Verdana" color="blue" size="5">
            			<bean:message key="IndexCompose.vdata" /></font></p>
            		</td>
          		</tr>
          	-->
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

					<logic:notEqual value="yes" parameter="compute">

						<table id="indexcompose" border="1" align="center" width="631"
							height="222" cellspacing="0" cellpadding="0">
							<tr>
								<td bgcolor="#84AADE" align="center" valign="middle">
									<p style="margin-left: 9">
										<font face="Verdana" color="blue" size="5"> <bean:message
												key="IndexCompose.vdata" /></font>
									</p>
								</td>
							</tr>
						</table>

					</logic:notEqual>

					<bean:define id="try1" name="IndexComposeBean" property="tabledata" />
					<bean:size id="dataCount" name="IndexComposeBean"
						property="tabledata" />
					<div id=contentstart>
						<logic:equal name="IndexComposeBean" property="checkChart"
							value="on" parameter="compute">
							<div id="chart">
								<%
									GraphMethodsPf.ReaddataCompose(request);
												//	GraphMethodsPf gm = new GraphMethodsPf();
												GraphMethodsPf gm = ConnectInit.getGraphMethodsPf();
												String filename = gm.getGraphChartCompany1(session,
														new PrintWriter(out));
												String graphURL = request.getContextPath()
														+ "/servlet/DisplayChart?filename=" + filename;
								%>
								<table id="strutsgraph" border="0" width="50%" align="center"
									cellpadding="0" cellspacing="0">
									<tr>
										<td>
											<!-- Change by Manoj Adekar to Remove link from graph --> <img
											src="<%=graphURL%>" width="500" height="270" border="0">
										</td>
									</tr>
								</table>
							</div>
							<jsp:setProperty name="IndexComposeBean" property="checkChart"
								value="off" />
						</logic:equal>

						<logic:equal value="yes" parameter="compute">
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

							</logic:equal>

							<logic:notEqual value="0" name="dataCount">


								<%
									String indid = "0";
													indid = request.getParameter("index");
													harrier.income.com.report.IndexComposeForm Idf = new harrier.income.com.report.IndexComposeForm();
													Idf.setIndex(indid);
													Idf.getTabledata();
													session.setAttribute("ci2", Idf.getVw());
								%>

								<table border="0" width="800" cellpadding="2" cellspacing="0"
									align="center" id="tabular">
									<tr>
										<td>
											<table class="sorted" ID="sortTabletable" border="1"
												align="center" cellpadding="3" cellspacing="0"
												bgcolor="#EFEFEF">
												<thead>
													<tr>
														<!-- <table border="1" width="80%" align="center" cellpadding="2" cellspacing="0" id="t1" class="sortable" bordercolor="#EFEFEF">
          		< ! -- Table Heading -- >
          		<tr> -->
														<th align="center" nowrap="nowrap" id="stockName"
															class="gridStyle-header"><span><b><bean:message
																		key="stockmaster.stockName" /></b></span></th>
														<th align="right" nowrap="nowrap" id="tshares"
															class="gridStyle-header"><span><b><bean:message
																		key="IndexCompose.tshares" /></b></span></th>
														<th align="right" nowrap="nowrap" id="iwf"
															class="gridStyle-header"><span><b><bean:message
																		key="stockmaster.iwf" /></b></span></th>
														<th align="right" nowrap="nowrap" id="mlot"
															class="gridStyle-header"><span><b><bean:message
																		key="IndexCompose.mlot" /></b></span></th>
														<th align="right" nowrap="nowrap" id="priceltp"
															class="gridStyle-header"><span><b><bean:message
																		key="IndexCompose.priceltp" /></b></span></th>
														<th align="right" nowrap="nowrap" id="pricelast"
															class="gridStyle-header"><span><b><bean:message
																		key="IndexCompose.pricelast" /></b></span></th>
														<th align="center" nowrap="nowrap" id="currency"
															class="gridStyle-header"><span><b><bean:message
																		key="IndexCompose.currency" /></b></span></th>
														<th align="center" nowrap="nowrap" id="cerate"
															class="gridStyle-header"><span><b><bean:message
																		key="IndexCompose.cerate" /></b></span></th>
														<th align="center" nowrap="nowrap" id="mcap"
															class="gridStyle-header"><span><b><bean:message
																		key="IndexCompose.mcap" /></b></span></th>
														<th align="center" nowrap="nowrap" id="amarket"
															class="gridStyle-header"><span><b><bean:message
																		key="IndexCompose.amarket" /></b></span></th>
														<th align="right" nowrap="nowrap" id="weight"
															class="gridStyle-header"><span><b><bean:message
																		key="IndexCompose.weight" /></b></span></th>
														<th align="center" nowrap="nowrap" id="Date"
															class="gridStyle-header"><span><b><bean:message
																		key="corporate.Date" /></b></span></th>
													</tr>
												</thead>
												<tbody>

													<!-- Iterate over the table data -->
													<%
														int i = 1;
																		String bcolor = "";
													%>
													<logic:iterate id="try1" property="tabledata"
														name="IndexComposeBean">
														<%
															if (i % 2 == 0) {
																					bcolor = "#84AADE";
																				} else {
																					bcolor = "#DEE3EF";
																				}
														%>
														<tr bgcolor=<%=bcolor%>>
															<td nowrap="nowrap" align="left" axis="sstring"
																headers="stockName"
																title='<bean:write name="try1" property="stockname1"/>'>
																<font face="Verdana" size="2" color="blue"> <a
																	href='../masters/stockmaster2.jsp?s_stockid=<bean:write name="try1" property="stockid"/>'>
																		<bean:write name="try1" property="stockname1" />
																</a></font>
															</td>
															<td nowrap="nowrap" align="right" nowrap="nowrap"
																axis="number" headers="tshares"
																title='<bean:write name="try1" property="tis"/>'><font
																face="Verdana" size="2" color="blue"> <bean:write
																		name="try1" property="tis" /></font></td>
															<td nowrap="nowrap" align="right" nowrap="nowrap"
																axis="number" headers="iwf"
																title='<bean:write name="try1" property="iwf"/>'><font
																face="Verdana" size="2" color="blue"> <bean:write
																		name="try1" property="iwf" /></font></td>
															<td nowrap="nowrap" align="right" nowrap="nowrap"
																axis="number" headers="mlot"
																title='<bean:write name="try1" property="market"/>'>
																<font face="Verdana" size="2" color="blue"> <bean:write
																		name="try1" property="market" /></font>
															</td>
															<td nowrap="nowrap" align="right" nowrap="nowrap"
																axis="number" headers="priceltp"
																title='<bean:write name="try1" property="adjusted"/>'>
																<font face="Verdana" size="2" color="blue"> <bean:write
																		name="try1" property="adjusted" /></font>
															</td>
															<td nowrap="nowrap" align="right" nowrap="nowrap"
																axis="number" headers="pricelast"
																title='<bean:write name="try1" property="last"/>'>
																<font face="Verdana" size="2" color="blue"> <bean:write
																		name="try1" property="last" /></font>
															</td>
															<td nowrap="nowrap" align="left" nowrap="nowrap"
																axis="sstring" headers="currency"
																title='<bean:write name="try1" property="currency"/>'>
																<font face="Verdana" size="2" color="blue"> <bean:write
																		name="try1" property="currency" /></font>
															</td>
															<td nowrap="nowrap" align="right" nowrap="nowrap"
																axis="number" headers="cerate"
																title='<bean:write name="try1" property="curr_exch_convIratecomp"/>'>
																<font face="Verdana" size="2" color="blue"> <bean:write
																		name="try1" property="curr_exch_convIratecomp" /></font>
															</td>
															<td nowrap="nowrap" align="right" nowrap="nowrap"
																axis="number" headers="mcap"
																title='<bean:write name="try1" property="mcv"/>'><font
																face="Verdana" size="2" color="blue"> <bean:write
																		name="try1" property="mcv" /></font></td>
															<td nowrap="nowrap" align="right" nowrap="nowrap"
																axis="number" headers="amarket"
																title='<bean:write name="try1" property="adjustedmarketcap"/>'>
																<font face="Verdana" size="2" color="blue"> <bean:write
																		name="try1" property="adjustedmarketcap" /></font>
															</td>
															<td nowrap="nowrap" align="right" nowrap="nowrap"
																axis="number" headers="weight"
																title='<bean:write name="try1" property="strweightage"/>'>
																<font face="Verdana" size="2" color="blue"> <bean:write
																		name="try1" property="strweightage" /></font>
															</td>
															<td nowrap="nowrap" align="right" nowrap="nowrap"
																axis="date" headers="Date"
																title='<bean:write name="try1" property="stockprice"/>'>
																<font face="Verdana" size="2" color="blue"> <bean:write
																		name="try1" property="stockprice" /></font>
															</td>
														</tr>
														<%
															i = i + 1;
														%>
													</logic:iterate>
													<%--   ***************************** COMMENTED	:START COMMENT *************************
			<tr>
 			<td colspan=10 nowrap="nowrap" align="right"  class="gridStyle-odd"  >
	 			<font face="Verdana" size="2" color="blue">
 					<bean:message key="StockPerformance.Total" />
 				</font>
 			</td> 
			<td  align="right" nowrap="nowrap" class="gridStyle-odd" >
				<font face="Verdana" size="2" color="blue">
					<bean:write name="IndexComposeBean" property="total"/>000T
				</font>
							
			</td>
			
			<td width="100" align="right" nowrap="nowrap" class="gridStyle-odd" >
			
			</td>
		</tr>
		************************************COMMENTED:END COMMENT  --%>
												</tbody>
												<%-- Changes made by Ganesh --%>
												<%-- PROBLEM : IN ALIGNMENT OF TOTAL 100.0 UNDER WEIGHTAGE FIELD --%>
												<%--Also for applying sort operation on table --%>

												<tr>
													<td colspan=10 nowrap="nowrap" align="right"
														class="gridStyle-odd"><font face="Verdana" size="2"
														color="blue"> <bean:message
																key="StockPerformance.Total" />
													</font></td>
													<td align="right" nowrap="nowrap" class="gridStyle-odd">
														<font face="Verdana" size="2" color="blue"> <bean:write
																name="IndexComposeBean" property="total" />000
													</font>

													</td>
													<%-- dot is used to make table line continuous --%>
													<%-- font color is choosen so that it will match with table bgcolor --%>
													<td width="100" align="right" nowrap="nowrap"
														class="gridStyle-odd"><font color="silver">.</font>

													</td>
												</tr>

											</table>
										</td>
									</tr>


								</table>
								<!-- / div -->
							</logic:notEqual>



						</logic:equal>
					</div>
					<div id="Details">
						<table id="hiddenTable">
							<tr>
								<td></td>
								<td><b> <font size="2" face="Verdana"> <bean:message
												key="Index.select" />&nbsp;:
									</font>
								</b></td>
								<td><bean:write name="IndexComposeBean"
										property="indexName" /></td>
							</tr>
						</table>
					</div>
					<html:hidden property="defaultVal" />

					<html:hidden property="compute" value="no"></html:hidden>
				</html:form></td>
		</tr>
	</table>
</body>

<script language="javascript">

var Button;
var man1=document.getElementById("hiddenTable");
	man1.style.display= "none"; 


function initSort() {
	mySorted = new SortedTable();
	mySorted.colorize = function() {
		for (var i=0;i<this.elements.length;i++) {
			if (i%2){
				this.changeClass(this.elements[i],'even','odd');
			} else {
				this.changeClass(this.elements[i],'odd','even');
			}
		}
	}
	mySorted.onsort = mySorted.colorize;
	mySorted.onmove = mySorted.colorize;
	mySorted.colorize();
}

function PrintThisPage() 
	{ 
		var checkChart = document.forms[0].checkChart.value;
   		var sOption="toolbar=yes,location=no,directories=yes,menubar=yes,"; 
       	sOption+="scrollbars=yes,left=50,top=25"; 
		var iname=document.forms[0].indexName.value;
		
		
   		//var sWinHTML = document.getElementById('contentstart').innerHTML;    		
   		var printHead = document.getElementById('PageTitle').innerHTML;   		
   		
   		var winprint=window.open("","",sOption); 
       	winprint.document.open(); 
       	winprint.document.write('<html><body>'); 
       	winprint.document.write(printHead);
       	winprint.document.write('<br>');
       	winprint.document.write('&nbsp;&nbsp;&nbsp;'); 
      	winprint.document.write('&nbsp;&nbsp;&nbsp;'); 
      	if(Button=="AjaxButton"){		
			var moreDet = DWRUtil.getText("index");
   			var sWinHTML = document.getElementById('Ajaxcontentstart').innerHTML; 
   			winprint.document.write("<font size=3>Index :"+moreDet+"</font>");
   		}
   		else{ 
   			var sWinHTML = document.getElementById('contentstart').innerHTML;
   			var moreDet = document.getElementById('hiddenTable').innerHTML;
   			winprint.document.write(moreDet);
   		}   	
       	
       	winprint.document.write('<br>');
       	winprint.document.write(sWinHTML); 
       	if(checkChart=="on"){       			
              	var chrt=document.getElementById('chart').innerHTML;
               	winprint.document.write(chrt); 
        }
       	winprint.document.write('</body></html>'); 
       	
       	winprint.document.close(); 
       	winprint.focus(); 
	}

function test1()
	{

		document.forms[0].checkChart.checked=false;
   		document.forms[0].submit();
	}
	var newwindow;


	function go() 
	{
	var i = 0;
        var fields = new Array();
	 	if(document.forms[0].index.value==0){
			fields[i++] = "Select Index is required";
		}
		if (fields.length > 0) {
                   alert(fields.join('\n'));
                   return false;
         }
         else {
	 	document.forms[0].compute.value="yes";
		document.forms[0].defaultVal.value="no";
		return true;
	}
	}
function showChart()
	{
	//document.forms[0].submit();
	}
function viewFunc()
	{
	var i = 0;
	if(document.forms[0].D1.value=="0"){
		alert("Index Name is required") ;
		return false;
	}
	else return true;
	}
	
<!-- ================================= for Dwr ================================================================= -->	

function getIndexCompositionReport() {
  DWRUtil.useLoadingMessage();	
  var selectIndex = DWRUtil.getValue("index");     
  IndexComposeReport.getTabledata(fillTable,selectIndex);    
}

	var stockname = function(indexcompose1) {	 	
	 	var inurl = "../masters/stockmaster2.jsp?s_stockid="+indexcompose1.stockid+"&compute=yes";
		return '<a href='+inurl+'><font face="Verdana" color="blue" size="2">'+indexcompose1.stockname1+'</font></a>'};	
	var tis = function(indexcompose1) { return '<font face="Verdana" color="blue" size="2">'+indexcompose1.tis+'</font>'};
	var iwf = function(indexcompose1) { return '<font face="Verdana" color="blue" size="2">'+indexcompose1.iwf+'</font>'};
	var market = function(indexcompose1) {  return '<font face="Verdana" color="blue" size="2">'+indexcompose1.market+'</font>'};	
	var stockprice = function(indexcompose1) { return '<font face="Verdana" color="blue" size="2">'+indexcompose1.adjusted+'</font>' }; 
	var last = function(indexcompose1) { return '<font face="Verdana" color="blue" size="2">'+indexcompose1.last+'</font>' };
	var currency = function(indexcompose1) { return '<font face="Verdana" color="blue" size="2">'+indexcompose1.currency+'</font>' };
	var curr_exch_convIratecomp = function(indexcompose1) { return '<font face="Verdana" color="blue" size="2">'+indexcompose1.curr_exch_convIratecomp+'</font>' };
	var mcv = function(indexcompose1) { return '<font face="Verdana" color="blue" size="2">'+indexcompose1.mcv+'</font>' };
	var adjusted = function(indexcompose1) { return '<font face="Verdana" color="blue" size="2">'+indexcompose1.adjustedmarketcap+'</font>' };
	var strweightage = function(indexcompose1) { return '<font face="Verdana" color="blue" size="2">'+indexcompose1.strweightage+'</font>'};
	var date = function(indexcompose1) {  return '<font face="Verdana" color="blue" size="2">'+indexcompose1.stockprice+'</font>'};		
	
function fillTable(tabledata) {
	var selectIndex = DWRUtil.getValue("index");  
    var check2 = DWRUtil.getValue("checkChart");  
	var a = new Array();
	a = tabledata;   
	var length = a.length;
	
	if(length!=1){   	
		//alert("lok1");
	  Button = "AjaxButton"; 	  
   	  changeDisplay("indexcompose","none");   	  	 
 	  changeDisplay("nodata","none");
 	  changeDisplay("noStrutsData","none");
 	  changeDisplay("sortTabletable","none");
 	  changeDisplay("strutslinks","none");
 	  changeDisplay("ajaxlinks","inline");
 	  changeDisplay("sortTable","inline");
 	  changeDisplay("strutsgraph","none");
      changeDisplay("graph","none"); 	   	   	  
  	    	  	  	 	  
	  DWRUtil.removeAllRows("tablebody");
	  DWRUtil.addRows("tablebody",tabledata, [ stockname, tis, iwf, market, stockprice, last, currency, curr_exch_convIratecomp, mcv, adjusted, strweightage,date ]);
 	  if(check2 !=0){
  		changeDisplay("strutsgraph","none");
  		changeDisplay("graph","inline");   		
  		GraphMethods.getGraphChartCompany1(showGraph,selectIndex,"10-10-2007","1"); 
  				
	  } 	 
 }
 else{ 	
 	 changeDisplay("sortTable","none");
 	 changeDisplay("sortTabletable","none");
 	 changeDisplay("noStrutsData","none");
 	 changeDisplay("strutslinks","none");
 	 changeDisplay("ajaxlinks","none");
 	 changeDisplay("nodata","inline");
	 changeDisplay("indexcompose","none");
 }
  /*changeDisplay("compositiontable","inline");
  changeDisplay("indexcompose","none");	
  DWRUtil.removeAllRows("tablebody");
  DWRUtil.addRows("tablebody",tabledata, [ stockname, tis, iwf, market, stockprice, last, currency, curr_exch_convIratecomp, mcv, adjusted, strweightage, date])
  //alert("I got it right");*/
}
function showGraph(graphURL){
//alert(graphURL);	
	var selectIndex = DWRUtil.getValue("index");	
	var imgid = $("graph");
	imgid.setAttribute("src",graphURL);
	var chartType = DWRUtil.getValue("checkChart");	
}


<!-- ================================= for Dwr ================================================================= -->

</script>
</html:html>
