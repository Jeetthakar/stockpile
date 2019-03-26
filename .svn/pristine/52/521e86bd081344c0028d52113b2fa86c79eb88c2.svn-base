<%@ page language="java"
	import="app.*,java.sql.*,java.util.*,java.text.*"%>
<%@ page import="harrier.income.com.report.IndexCalculatorCollection"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@page import="org.apache.log4j.Logger"%>
<%
	Logger log = Logger.getLogger(this.getClass());
	log.info("inside " + this.getClass().getSimpleName() + " page");
%>
<%!int numberOfRows = 0;%>
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");

	LogonForm form = (LogonForm) session.getAttribute("user");
	if (form == null || (!request.isRequestedSessionIdValid())) {
		response.sendRedirect("../userlogintemp.jsp");
		return;
	}
	String mnu = (String) request.getParameter("menum");
	if (mnu != null) {
		if (mnu.equalsIgnoreCase("yes")) {
			session.removeAttribute("indexCalculatorBean");
			//mnu=null;
		}

	}
	//log.info("Menu parameter="+mnu);

	String bpath = request.getContextPath();
	String imagesbasePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ bpath + "/pages/";
%>
<jsp:useBean id="indexCalculatorBean" scope="session"
	class="harrier.income.com.report.IndexCalculatorForm" />
<jsp:setProperty name="indexCalculatorBean" property="useridc"
	value='<%=session.getAttribute("userid")%>' />
<jsp:setProperty name="indexCalculatorBean" property="role_id1"
	value='<%=session.getAttribute("role_id")%>' />

<html:html>

<head>
<link href="./StyleSheet.css" rel="stylesheet" type="text/css">
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
</style>

<script type="text/javascript">

	function checkSelects()
	{
		var j = 1;
		var i = 0;
		var k = 1000;
		var l = 100;
		var nodesToAdd = new Array();
		var SelectCount = 7;
		var priceA="";
		var selval=document.getElementById(""+j).value;
		
		//alert("Check Value Out"+selval2);
		for (i = 0; selval!=0 ; i++)
		{
			try {
				var selval2=document.getElementById(""+k).value;
				var selval3=document.getElementById(""+l).value;
				selval=document.getElementById(""+j).value;
				nodesToAdd[i]=selval;
				priceA= priceA +"|"+selval3+"_"+selval2+"_"+selval;
				j=j+1;
				k=k+1;
				l=l+1;
			}
			catch(e)
			{
				break;
			}	
		} 
		//alert("Check Value : " +priceA+ " ");
		document.forms[0].priceArray.value = priceA;
		
	}
/*function Submitdata()
{
	document.forms[0].b1.value = 'Submit';
	document.forms[0].submit();
}
*/
function hideLeftCol(id)
{
	if(this.document.getElementById(id).style.display=='none')
	{
		this.document.getElementById(id).style.display='inline';
		document.getElementById("HideHandle").src = '<%=imagesbasePath%>closeImage.gif';
//		this.document.getElementById(id).style.width='10px';
		Set_Cookie('showLeftCol','true',30,'/','','');
		var show = Get_Cookie('showLeftCol');
		//document['HideHandle'].src = 'images/hide.gif';
		document.getElementById("HideHandle").src = 'open.gif';
	}
	else{
//		this.document.getElementById(id).style.display='none';
		this.document.getElementById(id).style.display='none';
		document.getElementById("HideHandle").src = '<%=imagesbasePath%>
	openImage.gif';
			Set_Cookie('showLeftCol', 'false', 30, '/', '', '');
			var show = Get_Cookie('showLeftCol');
			//document['HideHandle'].src = 'images/show.gif';	
		}
	}

	function PrintThisPage() {
		var sOption = "toolbar=yes,location=no,directories=yes,menubar=yes,";
		sOption += "scrollbars=yes,width=750,height=500,left=100,top=25";
		//var dt=document.forms[0].from.value;
		var sWinHTML = document.getElementById('contentstart').innerHTML;
		//var sWinHTML = document.getElementById('contentstart').innerHTML; 
		var printHead = document.getElementById('PageTitle').innerHTML;

		var winprint = window.open("", "", sOption);
		winprint.document.open();
		winprint.document
				.write('<html><LINK href=StyleSheet.css rel=Stylesheet><body onload="self.print()" >');
		winprint.document.write(printHead);
		winprint.document.write('<br>');
		winprint.document.write('&nbsp;&nbsp;&nbsp;');
		winprint.document.write('&nbsp;&nbsp;&nbsp;');

		winprint.document.write('<br>');
		winprint.document.write(sWinHTML);
		winprint.document.write('</body></html>');
		winprint.document.close();
		winprint.focus();
	}

	/*function submitdata()
	 {
	 var flag=0;
	 var spChars = " ";
	 var sChars = ".()!@#$%^&*=-[]\\\';,/{}|\":<>?~0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	 var iChars = ".()!@#$%^&*=-[]\\\';,/{}|\":<>?~0123456789";
	 var mChars = "!@#.()$%^&*+=[]\\\';,/{}|\":<>?~";
	 if(document.forms[0].activityName.value!='')
	 {
	
	 if (iChars.indexOf(document.forms[0].activityName.value.charAt(0)) != -1) 
	 {
	 flag=1;
	 alert ('First Character must be alphabet.')
	 document.forms[0].activityName.value='';
	 document.forms[0].activityName.focus();
	 return false;
	 }
	 if (sChars.indexOf(document.forms[0].activityName.value.charAt(0)) == -1) 
	 {
	 flag=1;
	 alert ('Please enter proper activity name,First Character must be alphabet.');
	 document.forms[0].activityName.value='';
	 document.forms[0].activityName.focus();
	 return false;
	 }
	 else 
	 {
	 for (var i = 0; i < document.forms[0].activityName.value.length; i++) 
	 {
	 if ((mChars.indexOf(document.forms[0].activityName.value.charAt(i)) != -1) )
	 {
	 flag=1;
	 alert ('Special Character(s) not allowed.');
	 document.forms[0].activityName.value='';
	 document.forms[0].activityName.focus();
	 return false;
	 }
	 }
	 }
	 }
	 else
	 {
	 flag=1;
	 alert ('Please enter activity name.');
	 document.forms[0].activityName.value='';
	 return false;
	 }
	
	 if(document.forms[0].activityDescription.value!='')
	 {
	 if (spChars.indexOf(document.forms[0].activityDescription.value.charAt(0)) != -1) 
	 {
	 flag=1;
	 alert ('Please enter proper Activity description,First Character must be alphabet.');
	 document.forms[0].activityDescription.value='';
	 document.forms[0].activityDescription.focus();
	 return false;
	 } 
	 }
	 if(flag==0)
	 {
	 document.forms[0].operation.value="save";
	 return true;
	 }
	 }*/

	function submitdata() {
		var dotcount = 0;
		var numdot = '.';
		var flag = 0;
		var spChars = " ";
		var sChars = "()!@#$%^&*=-[]\\\';,/{}|\":<>?~abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		var iChars = ".......................()!@#$%^&*=-[]\\\';,/{}|\":<>?~";
		var mChars = "!@#()$%^&*+=[]\\\';,/{}|\":<>?~";
		var integers = ".0123456789";
		var lastTradedPriceNew = "";

		var noOfRows = document.getElementById('numberOfRows').value;
		//alert(" Its in !"+noOfRows);

		for (var j = 1; j <= noOfRows; j++) {
			lastTradedPriceNew = document.getElementById(j).value;
			if (lastTradedPriceNew != '') {

				if (iChars.indexOf(lastTradedPriceNew) != -1) {
					flag = 1;
					alert('Character must be number.')
					document.getElementById(j).value = '';
					document.getElementById(j).focus();
					//return false;
					break;
				}

				if (integers
						.indexOf(document.getElementById(j).value.charAt(0)) != -1) {
					dotcount = 0;
					for (var i = 0; i < document.getElementById(j).value.length; i++) {
						numdot = document.getElementById(j).value.charAt(i);

						if ((numdot == '.')) {
							dotcount = dotcount + 1;
						}
					}
					if (dotcount > 1) {
						flag = 1;
						alert('Please enter valid number.');
						document.getElementById(j).value = '';
						document.getElementById(j).focus();
						//return false;
						break;
					}
				}

				//alert(integers.indexOf(document.getElementById(j).value.charAt(0)));
				if (integers
						.indexOf(document.getElementById(j).value.charAt(0)) == -1) {
					flag = 1;
					alert('Please enter number.');
					document.getElementById(j).value = '';
					document.getElementById(j).focus();
					//return false;
					break;
				} else {
					for (var i = 0; i < document.getElementById(j).value.length; i++) {
						if ((sChars.indexOf(document.getElementById(j).value
								.charAt(i)) != -1)) {
							flag = 1;
							alert('Please enter number.');
							document.getElementById(j).value = '';
							document.getElementById(j).focus();
							//return false;
							break;
						}
					}
				}
			} else {
				flag = 1;
				alert('Please enter My Rates Value.');
				document.getElementById(j).value = '';
				//return false;
				break;
			}

		}

		if (flag == 0) {
			checkSelects();
			//alert(lastTradedPriceNew );
			//document.forms[0].saveButton.value='Submit';
			return true;
		} else
			return false;
	}
</script>


</head>


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
				action="/indexCalculatorAction">
				<link href="./StyleSheetRpt.css" rel="stylesheet" type="text/css">
				<div id="PageTitle">
					<table width="1000" cellspacing="0" cellpadding="0">
						<tr>
							<td width="335" class="subHeader" nowrap="nowrap">&nbsp;</td>
							<td class="subHeader" width="665" align="left" colspan="2"
								nowrap="nowrap"><font size="3" face="Arial"> <b><bean:message
											key="IndexCalculator.title" /></b>
							</font></td>
						</tr>
					</table>
				</div>
				<p></p>

				<table align="right">
					<%
						//Added by sushant for path issue 
								//String path = request.getContextPath();
								String reportsbasePath = request.getScheme() + "://"
										+ request.getServerName() + ":"
										+ request.getServerPort() + bpath
										+ "/pages/reports/";

								String excel_path = reportsbasePath
										+ "FileDownload.jsp?&type=27&filename=IndexCalculatorRpt.xls";
								String xml_path = reportsbasePath
										+ "FileDownloadXmlNew.jsp?&type=27&filename=IndexCalculatorRpt.xml";
								String pdf_path = reportsbasePath
										+ "FileDownload_Pdf.jsp?&type=27&filename=IndexCalculatorRpt.pdf";
								String str_url = reportsbasePath
										+ "EmailReport.jsp?switch_type=4&cas=4&rname=IndexCalculatorRpt.xls";
					%>

					<td colspan="2" nowrap="nowrap" align="left"><font size="1"
						face="Verdana" color="blue"> <!-- Printer friendly --> <a
							href="javascript:PrintThisPage();"><bean:message
									key="LatestDivisor.printerf" /></a> &nbsp;&nbsp; <!-- DownLoad Excel -->
							<a href='<%=excel_path%>'><bean:message
									key="LatestDivisor.downloade" /></a> &nbsp;&nbsp; <!-- DownLoad Xml -->
							<a href='<%=xml_path%>'>Export to Xml</a> &nbsp;&nbsp; <!-- Email Report -->
							<a href='<%=str_url%>'><bean:message
									key="LatestDivisor.emailr" /></a> <!-- Pdf Report --> <a
							href='<%=pdf_path%>'><bean:message key="LatestDivisor.pdfr" /></a>

					</font></td>
				</table>


				<html:errors />
				<table>
					<tr>
						<td>&nbsp;</td>
					</tr>
				</table>

				<table width="656">
					<tr>
						<td width="81" nowrap="nowrap" align="left" height="30"></td>
						<td width="125" nowrap="nowrap" align="right" height="40"><font
							size="2" face="Arial"> <bean:message
									key="port.selectIndex" />
						</font></td>
						<td width="407" nowrap="nowrap" align="left" height="40"><html:select
								property="index_id" size="1"
								onchange="document.forms[0].submit();return true">
								<html:optionsCollection name="indexCalculatorBean"
									property="indexListCollection" />
							</html:select></td>
					</tr>
				</table>

					<table border="0" width="662" class="gridStyle"
					bordercolorlight="#000000" cellspacing="0" cellpadding="0"
					bordercolordark="#000000">
					<tr>
<!-- 						<td align="right" nowrap="nowrap"><font size="2" face="Arial"> -->
<!-- 								Index Value &nbsp; </font></td> -->
						<%
							String indexval1 = (String) session
											.getAttribute("indexvalN");
									System.out
											.println("Index Calculator jsp --- indexval1 *** "
													+ indexval1);
									IndexCalculatorCollection indexCalculatorCollection = new IndexCalculatorCollection();
						%>
						<td nowrap="nowrap">
							<%
								if (indexval1 != null) {
							%> <input name="indexValueNew" type="hidden" value="<%=indexval1%>"
							readonly="readonly" /> <%
 	session.setAttribute("indexvalN", null);
 			} else {
 				//code in else block commented by Samiksha
 				//  				String indexV = indexCalculatorCollection
 				//  						.dbIndexValue("3511");
 				//  				String indexV = indexCalculatorCollection
 				//  						.dbIndexValue(indexval1);

 				//  				System.out
 				//  						.println("Index Calculator jsp --- indexv *** "
 				//  								+ indexV);
 %> <input name="indexValueNew" type="hidden" readonly="readonly" /> <%--   <input name="indexValueNew" type="text" value="<%=indexV%>" readonly="readonly" /> --%>

							<%
								}
							%>

						</td>

						<%
							String indexvalLtp = (String) session
											.getAttribute("indexvalNLtp");
									System.out
											.println("Index Calculator jsp --- indexvalLtp *** "
													+ indexvalLtp);
						%>
						<td nowrap="nowrap" align="left">
							<%
								if (indexvalLtp != null) {
							%> <input name="indexValueLtp" type="hidden"
							value="<%=indexvalLtp%>" readonly="readonly" /> <%
 	session.setAttribute("indexvalNLtp", null);
 			} else {
 				//code in else block commented by Samiksha
 				//  				 				String indexVLtp = indexCalculatorCollection
 				//  				 						.computeIndexNormallyLtp("3511");
 				//  				String indexVLtp = indexCalculatorCollection
 				//  						.computeIndexNormallyLtp(indexvalLtp);
 				//  				session.setAttribute("indexvalNLtp", indexVLtp);
 %> <input name="indexValueLtp" type="hidden" readonly="readonly" /> <%--    <input name="indexValueLtp" type="text" value="<%=indexVLtp%>" --%>
							<!-- 														readonly="readonly" />  --> <%
 	}
 %>
						</td>
						<td></td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td></td> -->
<!-- 						<td nowrap="nowrap"><font size="2" face="Arial">(Using -->
<!-- 								Close Price)</font></td> -->
<!-- 						<td nowrap="nowrap" align="left"><font size="2" face="Arial">(Using -->
<!-- 								Last Traded Price)</font></td> -->
<!-- 						<td></td> -->
<!-- 					</tr> -->
					<tr>
						<td width="218" align="right" nowrap="nowrap"><font size="2"
							face="Arial"> <bean:message
									key="port.indexValueCalculated" />&nbsp;
						</font></td>

						<td width="266" nowrap="nowrap"><html:text
								property="indexValue" size="20" readonly="true" /></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td nowrap="nowrap" align="right"><html:submit property="b1"
								onclick="return submitdata();">
								<bean:message key="defineIndex30" />
							</html:submit></td>

						<td nowrap="nowrap" align="left"><html:submit property="b1">
								<bean:message key="indexUpdate.reset" />
							</html:submit></td>

						<td></td>

					</tr>
				</table>
				
				
				
				<p></p>
				<table border="0" width="900" cellpadding="0" cellspacing="0">
					<tr>
						<td width="75" nowrap="nowrap">&nbsp;</td>
						<td width="770" nowrap="nowrap"><bean:define id="td"
								name="indexCalculatorBean" property="tableData" /> <logic:empty
								property="tableDataNew" name="indexCalculatorBean">

								<table border="1" align="center" width="631" height="222"
									cellspacing="0" cellpadding="0">
									<tr>
										<td id="noStrutsData" bgcolor="#7dbce7" align="center"
											valign="middle">
											<p style="margin-left: 9">
												<font face="Verdana" color="blue" size="5"> <bean:message
														key="IndexCompareOHCL.ndata" />
												</font>
											</p>
										</td>
									</tr>
								</table>

							</logic:empty> <logic:notEmpty property="tableDataNew"
								name="indexCalculatorBean">
								<!-- table data -->
								<%
									log.info(" Inside index Calculator");
												Vector v = indexCalculatorBean.getIndex_cal();
												Object ci2 = null;
												session.setAttribute("ci2", new Vector(v));
								%>
								<div id=contentstart>
									<table border="1" class="gridStyle" width="80%" cellspacing="0"
										cellpadding="0">
										<tr>
											<!-- table headings -->
											<td class="gridStyle-header" width="10%" align="center"
												nowrap="nowrap" class="sortable"><bean:message
													key="port.srno" /></td>
											<td class="gridStyle-header" width="30%" align="center"
												nowrap="nowrap" class="sortable"><bean:message
													key="port.symbolName" /></td>
											<td class="gridStyle-header" width="20%" align="center"
												nowrap="nowrap" class="sortable"><bean:message
													key="port.lastTradedPrice" /></td>
											<td class="gridStyle-header" width="20%" align="center"
												nowrap="nowrap" class="sortable"><bean:message
													key="port.myRates" /></td>
										</tr>
										<%
											int j = 1;
														int k = 1000;
														int l = 100;
														String BgcolorA = ""; // use to give alternate bands of color to table row
														String UserPriceTestN = (String) session
																.getAttribute("UserPriceTest");
														System.out.println("PRICE ======= " + UserPriceTestN);
										%>
										<logic:iterate id="td" name="indexCalculatorBean"
											property="tableData">
											<%
												if (j % 2 != 0) {
																	BgcolorA = "#DEE3EF";
																} else {
																	BgcolorA = "#84AADE";
																}
											%>
											<tr bgcolor="<%=BgcolorA%>">
												<td width="10%" nowrap="nowrap" align="left"><font
													face="Arial" color="blue" size="2"> <bean:write
															name="td" property="serialNo" />
												</font></td>

												<td width="10%" nowrap="nowrap" align="left"><font
													face="Arial" color="blue" size="2"> <bean:write
															name="td" property="symbolName" />
												</font></td>
												<td width="10%" nowrap="nowrap" align="left"><font
													face="Arial" color="blue" size="2"> <bean:write
															name="td" property="lastTradedPrice" />
												</font></td>
												<td width="10%" nowrap="nowrap" align="left">
													<%
														if (UserPriceTestN != null) {
																			if (UserPriceTestN.equalsIgnoreCase("yes")) {
													%> <font face="Arial" color="blue" size="2"> <input
														name='lastTradedPriceNew' type="text"
														value='<bean:write name="td" property="lastTradedPriceNew"/>'
														id='<%=j%>' size='28' />
												</font> <%
 	}

 					} else {
 %> <font face="Arial" color="blue" size="2"> <input
														name='lastTradedPrice' type="text"
														value='<bean:write name="td" property="lastTradedPrice"/>'
														id='<%=j%>' size='28' />
												</font> <%
 	}
 %>
												</td>
												<input name='stockId' type="hidden"
													value='<bean:write name="td" property="stockId"/>'
													id='<%=k%>' />
												<input name='symbolName' type="hidden"
													value='<bean:write name="td" property="symbolName"/>'
													id='<%=l%>' />
											</tr>
											<%
												numberOfRows = j;
																j = j + 1;
																k = k + 1;
																l = l + 1;
											%>
										</logic:iterate>
										<%
											session.setAttribute("UserPriceTest", null);
										%>
									</table>
								</div>
							</logic:notEmpty></td>
					</tr>
					</tr>
				</table></td>
	</tr>
</table>
<!--  <input type="hidden" name="priceArray" id="parrays" value="yes"/> -->
<html:hidden property="priceArray" value="" name="indexCalculatorBean" />
<input type="hidden" name="numberOfRows" id="numberOfRows"
	value='<%=numberOfRows%>' />
</html:form>
</td>
</tr>
</table>


</html:html>