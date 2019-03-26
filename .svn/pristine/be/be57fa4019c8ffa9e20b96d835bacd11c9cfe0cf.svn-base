<%@ page import="harrier.income.com.report.IndexCalculatorForm"%>
<%@ page import="harrier.income.com.report.IndexCalculatorCollection"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page language="java"
	import="harrier.income.com.report.*,java.sql.*,java.util.*,java.text.*"%>
<%@ page language="java" import="app.*"%>
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

	String mnu = (String) request.getParameter("menuc");
	if (mnu != null) {
		if (mnu.equalsIgnoreCase("yes")) {
			session.removeAttribute("indexCalculatorBean");
		}
	}

	String var = request.getParameter("index_id");
	String option = null;
	option = request.getParameter("opt");

	String locale = session.getAttribute("locale").toString();
	//	AcessControl asc=new AcessControl();
	AcessControl asc = ConnectInit.getAcessControl();
	asc.setLocale(locale);

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
<SCRIPT language=javascript>
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
		document.getElementById("HideHandle").src = '<%=imagesbasePath%>openImage.gif';
		Set_Cookie('showLeftCol','false',30,'/','','');
		var show = Get_Cookie('showLeftCol');
		//document['HideHandle'].src = 'images/show.gif';	
	}
}

function PrintThisPage() 
{ 
  		var sOption="toolbar=yes,location=no,directories=yes,menubar=yes,"; 
       	sOption+="scrollbars=yes,width=750,height=500,left=100,top=25"; 
		//var dt=document.forms[0].from.value;
	/*	if(Button=="AjaxButton"){						
   			var sWinHTML = document.getElementById('Ajaxcontentstart').innerHTML; 
   		}*/
   		//else
   		
   		 var sWinHTML = document.getElementById('contentstart').innerHTML;    		    
   		//var sWinHTML = document.getElementById('contentstart').innerHTML; 
   		var printHead =document.getElementById('PageTitle').innerHTML;
   		 
   		var winprint=window.open("","",sOption); 
       	winprint.document.open(); 
       	winprint.document.write('<html><LINK href=StyleSheet.css rel=Stylesheet><body onload="self.print()" >'); 
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

function submitdata()
{
		var dotcount=0;
		var numdot='.';
		var flag=0;
		var spChars = " ";
		var sChars = "()!@#$%^&*=-[]\\\';,/{}|\":<>?~abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		var iChars = ".......................()!@#$%^&*=-[]\\\';,/{}|\":<>?~";
		var mChars = "!@#()$%^&*+=[]\\\';,/{}|\":<>?~";
		var integers = ".0123456789";
		var tisValue = document.getElementById("tis_value").value;
		if(tisValue!='')
		{
			
			if (iChars.indexOf(tisValue) != -1) 
			{
  				flag=1;
  				alert ('Character must be number.')
  				document.getElementById("tis_value").value='';
				document.getElementById("tis_value").focus();
				return false;
  			}
  			
  			if (integers.indexOf(document.getElementById("tis_value").value.charAt(0)) != -1) 
			{
				for (var i = 0; i < document.getElementById("tis_value").value.length; i++) 
  				{
  					numdot=document.getElementById("tis_value").value.charAt(i);
  					
  					if ((numdot== '.'))
  					{
  						dotcount=dotcount+1;
  					}
  				}
				
				if(dotcount > 1)
				{
					flag=1;
					alert ('Please enter valid number.');
					document.getElementById("tis_value").value='';
					document.getElementById("tis_value").focus();
					return false;
				}
					
			}
			
  			if (integers.indexOf(document.getElementById("tis_value").value.charAt(0)) == -1) 
			{
				flag=1;
				alert ('Please enter number.');
				document.getElementById("tis_value").value='';
				document.getElementById("tis_value").focus();
				return false;
			}
 			else 
  			{
  				for (var i = 0; i < document.getElementById("tis_value").value.length; i++) 
  				{
  					if ((sChars.indexOf(document.getElementById("tis_value").value.charAt(i)) != -1) )
  					{
  						flag=1;
  						alert ('Please enter number.');
  						document.getElementById("tis_value").value='';
						document.getElementById("tis_value").focus();
						return false;
  					}
  				}
  			}
	 	}
	 	else
		{
			flag=1;
			alert ('Please enter Total Mkt Cap. Value.');
			document.getElementById("tis_value").value='';
			return false;
		}
		
		
		if(flag==0)
 		{
 			document.forms[0].saveButton.value='Submit';
			return true;
 		}
}

</script>

<link href="./StyleSheet.css" rel="stylesheet" type="text/css">
<script type='text/javascript'
	src='/Stockpile/dwr/interface/MoveTable.js'></script>
<script type='text/javascript' src='/Stockpile/dwr/engine.js'></script>
<script type='text/javascript' src='/Stockpile/dwr/util.js'></script>
<script type="text/javascript" src="../Script/SortedTable.js"></script>


</head>
<body>
	<%
		log.info("imagesbasePath===>" + imagesbasePath);
	%>

	<table width="100%" height="100%">

		<tr>
			<td align="left" valign="top" bgcolor="#CCddee">
				<DIV id="leftCol" style="DISPLAY: none; width: 160; height: 100%;">
					<%@ include file="../tree3.jsp"%>
				</div> <IMG id="HideHandle" src='<%=imagesbasePath%>openImage.gif'
				style="CURSOR: hand; position: absolute;"
				onclick='hideLeft
	Col("leftCol");
	.gif' width="10" height="25">
			</td>
			<td align="left" valign="top"><html:form
					action="/portpolioTisCalculatorAction">
					<link href="./StyleSheet.css" rel="stylesheet" type="text/css">
					<div id="PageTitle">
						<table width="1000" cellspacing="0" cellpadding="0">
							<tr>
								<td width="335" class="subHeader" nowrap="nowrap">&nbsp;</td>
								<td class="subHeader" width="665" align="left" colspan="2"
									nowrap="nowrap"><font size="3" face="Verdana"> <b><bean:message
												key="PortTCalc.title" /> </b>
								</font></td>
							</tr>
						</table>
					</div>
					<p></p>
					<html:errors />



					<table align="right">
						<%
							//	Added by sushant for path issue 
									//  String path = request.getContextPath();
									String reportsbasePath = request.getScheme() + "://"
											+ request.getServerName() + ":"
											+ request.getServerPort() + bpath
											+ "/pages/reports/";

									String excel_path = reportsbasePath
											+ "FileDownload.jsp?&type=26&filename=PortfolioBasket.xls";
									String xml_path = reportsbasePath
											+ "FileDownloadXmlNew.jsp?&type=26&filename=PortfolioBasket.xml";
									String pdf_path = reportsbasePath
											+ "FileDownload_Pdf.jsp?&type=26&filename=PortfolioBasket.pdf";
									String str_url = reportsbasePath
											+ "EmailReport.jsp?switch_type=4&cas=4&rname=PortfolioBasket.xls";
									log.info("Report Path : ----- >  " + reportsbasePath);
						%>

						<tr>
							<td colspan="2" nowrap="nowrap" align="left"><font size="1"
								face="Verdana" color="blue"> <!-- Printer friendly --> <a
									href="javascript:PrintThisPage();"> <bean:message
											key="LatestDivisor.printerf" />
								</a> &nbsp;&nbsp; <!-- DownLoad Excel --> <a href='<%=excel_path%>'><bean:message
											key="LatestDivisor.downloade" /> </a> &nbsp;&nbsp; <!-- DownLoad Xml -->
									<a href='<%=xml_path%>'>Export to Xml</a> &nbsp;&nbsp; <!-- Email Report -->
									<a href='<%=str_url%>'><bean:message
											key="LatestDivisor.emailr" /> </a> <!-- Pdf Report --> <a
									href='<%=pdf_path%>'><bean:message key="LatestDivisor.pdfr" />
								</a>
							</font></td>
						</tr>
					</table>

					<table>
						<tr>
							<td>&nbsp;</td>
						</tr>
					</table>
					<table border="0" width="100%" class="gridStyle"
						bordercolorlight="#000000" cellspacing="0" cellpadding="0"
						bordercolordark="#000000">
						<tr>
							<td>
								<%
									String indexval1 = (String) session
													.getAttribute("indexvalN");
											IndexCalculatorCollection indexCalculatorCollection = new IndexCalculatorCollection();
											IndexCalculatorForm indexCalculatorfrm = new IndexCalculatorForm();
											String var1 = null;
											if (var1 == null) {

												var1 = "1";
											}
											String tis_value = "";
											session.setAttribute("indexvalN", null);
								%>


								<table width="656">
									<tr>
										<td width="81" nowrap="nowrap" align="left" height="30">
										</td>
										<td width="155" nowrap="nowrap" align="right" height="40">
											<font size="2" face="Verdana"> <bean:message
													key="port.selectIndex" />
										</font>
										</td>
										<td width="407" nowrap="nowrap" align="left" height="40">
											<html:select property="index_id" size="1"
												onchange="document.forms[0].submit();return true">
												<html:optionsCollection name="indexCalculatorBean"
													property="indexListCollection" />
											</html:select>
										</td>
									</tr>
								</table> <%
 	String cou = "";
 			cou = (String) request.getAttribute("coun");

 			if ((cou == null)) {
 				log.info("Flag in jsp :" + cou);
 				if (var == null) {
 					IndexCalculatorCollection
 							.addStocksInTisCalculatorTable("3252",
 									request, null);
 				} else {
 					IndexCalculatorCollection
 							.addStocksInTisCalculatorTable(var,
 									request, null);
 				}
 			}
 %>


								<table border="0" width="662" class="gridStyle"
									bordercolorlight="#000000" cellspacing="0" cellpadding="0"
									bordercolordark="#000000">
									<tr>
										<td width="245" align="right" nowrap="nowrap"><font
											size="2" face="Verdana"> <%
 	if (option != null && option.equals("Reset")) {
 				tis_value = indexCalculatorfrm.getTmcv();
 			} else {
 				tis_value = indexCalculatorfrm.getTmcv();
 			}
 %> <bean:message key="port.enterTmcv" />&nbsp;
										</font></td>
										<td width="266" nowrap="nowrap"><html:text
												property="tis_value" value="<%=tis_value%>" size="20" /></td>
										<td width="65" nowrap="nowrap"><html:submit
												property="saveButton" onclick="return submitdata();">
												<bean:message key="defineIndex30" />
											</html:submit></td>
										<td width="86" nowrap="nowrap"><html:submit
												property="saveButton" onclick="resetNumber();">
												<bean:message key="indexUpdate.reset" />
											</html:submit></td>
									</tr>
								</table>

								<p></p>

								<table border="0" width="900" cellpadding="0" cellspacing="0">
									<tr>
										<td width="75" nowrap="nowrap">&nbsp;</td>
										<td width="770" nowrap="nowrap">
											<%
												StringBuffer str = null;
														str = IndexCalculatorCollection
																.addStocksIncCalculatorTable(
																		IndexCalculatorCollection.table, request,
																		option);

														if (str != null) {
															//String cou="";
															//cou=(String)request.getAttribute("coun");
															//log.info("Stream Buffer :"+cou);	
															//if(cou!=null)	
															//{
															//if(cou.equals("1")){
											%> <%
 	log.info(" Inside Basket Cal ==null ");
 				//harrier.income.com.report.DisplayIndexForm di = new harrier.income.com.report.DisplayIndexForm();
 				Vector v = IndexCalculatorCollection.getIndex_calctr();
 				Object ci2 = null;
 				session.setAttribute("ci2", new Vector(v));
 %>
											<div id=contentstart>
												<table border="1" width="100%" class="gridStyle"
													cellspacing="0" cellpadding="0">
													<tr>
														<td width="10%" align="center" height="1"
															class="gridStyle-header"><b><font face="Verdana"
																color="White" size="2"><bean:message
																		key="port.srno" /> </font> </b></td>
														<td width="25%" align="center" height="1"
															class="gridStyle-header"><b><font face="Verdana"
																color="White" size="2"><bean:message
																		key="port.symbolName" /> </font> </b></td>
														<td width="20%" align="center" height="1"
															class="gridStyle-header"><b><font face="Verdana"
																color="White" size="2"><bean:message
																		key="port.lastTradedPrice" /> </font> </b></td>
														<td width="25%" align="center" height="1"
															class="gridStyle-header"><b><font face="Verdana"
																color="White" size="2"><bean:message
																		key="port.mcvValue" /> </font> </b></td>
														<td width="20%" align="center" height="1"
															class="gridStyle-header"><b><font face="Verdana"
																color="White" size="2"><bean:message
																		key="port.sharesCalculated" /> </font> </b></td>
													</tr>

													<%=str.toString()%>
												</table>
												<table>
													<tr>
														<td width="10%">&nbsp;</td>
														<td width="25%">&nbsp;</td>
														<td width="20%" nowrap="nowrap" align="center"><b><font
																face="Verdana" size="2">Total Mkt. Cap Value</font> </b></td>
														<td width="25%" nowrap="nowrap" align="right"><html:text
																property="tis_value" value="<%=tis_value%>" size="25"
																readonly="true" /></td>
														<td width="20%">&nbsp;</td>
													</tr>
												</table>
											</div> <%
 	} else {
 %>
											<div id=contentstart>
												<table>
													<tr>
														<td id="nodata" bgcolor="#7dbce7" valign="middle"
															style="display: inline">
															<p style="margin-left: 9">
																<font face="Verdana" color="blue" size="5"> <bean:message
																		key="IndexCompareOHCL.ndata" />
																</font>
															</p>
														</td>
													</tr>
												</table>
											</div> <%
 	}//}//}
 %>

										</td>
									</tr>
								</table>


							</td>
						</tr>
					</table>
				</html:form>
</body>
<script language="javascript">
	function validateNum() {
		submitdata();
		document.forms[0].saveButton.value = 'Submit';
		//	var a1=	 document.forms[0].saveButton.value;
		//	alert('Submit Button :'+a1);
		//document.forms[0].submit();	 	  
	}
	function resetNumber() {
		document.forms[0].saveButton.value = 'Reset';
	}
</script>


</html:html>
