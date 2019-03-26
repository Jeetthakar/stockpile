
<%@page import="com.harrier.initializeation.ConnectInit"%>
<%@page import="org.jfree.chart.demo.servlet.AdjustDecimal"%><%@ taglib
	uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page language="java" import="app.*,java.sql.*,java.util.*"%>

<%@ page import="org.apache.log4j.PropertyConfigurator"%>
<%@ page import="org.apache.struts.action.ActionForm"%>
<%@page import="org.apache.log4j.Logger"%>
<%
	Logger logger = Logger.getLogger(this.getClass());
	logger.info("inside " + this.getClass().getSimpleName() + " page");
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
<%
	System.out.println("**** IndexCompositionviv ****");
	String header = request.getHeader("REFERER");

	boolean hashPresentFromFile = false;
	Hashtable htFromFile = new Hashtable();

	if (session.getAttribute("hashtable_session") != null) {
		hashPresentFromFile = true;
		htFromFile = (Hashtable) session
		.getAttribute("hashtable_session");

		try {

	request = (HttpServletRequest) session
			.getAttribute("requestobject");

		} catch (Exception ex) {
	ex.printStackTrace();
		}
	}
	try {
		session.removeAttribute("hashtable_session");
	} catch (Exception ex) {

	}

	Enumeration e1 = request.getParameterNames();

	for (; e1.hasMoreElements();) {

		String nm = (String) e1.nextElement();

	}
%>
<%
	boolean iwferrors_flag = false;
	String iwferrors = "";
	//Logger logger =Logger.getLogger(Connect.resourceurl+"NewIndexDefine.jsp");
	PropertyConfigurator.configure(Connect.resourceurl
	+ "resources/log4j.properties");
	try {
		if (request.getAttribute("form") != null) {
	session.setAttribute("form", request.getAttribute("form"));
	session.setAttribute("applyCode",
			request.getAttribute("applyCode"));
		}
		String appcode = (String) session.getAttribute("applyCode");
		logger.info("appcode is :" + appcode);
		if (appcode != null) {
		}
	} catch (Exception e) {
		logger.info("error in getting form attributes from request" + e);
	}
	logger.info("Start1");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", -1);
	logger.info("Start2");
	Connection con = null;
	Connect c = null;

	String button = null;
	String oldIndexId;
	String exchangeCode = null;
	String stockid = null;
	org.jfree.chart.demo.servlet.FieldSort fsort = new org.jfree.chart.demo.servlet.FieldSort();
%>
<jsp:useBean id="stockCollection" scope="session"
	class="app.StockDetailsCollection" />
<jsp:useBean id="corporateact" scope="session" class="app.Corporate" />

<%
	//take care of request
	String flag = request.getParameter("flag");//come from corporate_diary			
	stockid = request.getParameter("stockid");
	exchangeCode = request.getParameter("exchange");
	//logger.info("Exchange code is "+exchangeCode);
	try {
		//	c = new Connect();
		c = ConnectInit.getConnect();
		con = (Connection) application
		.getAttribute("DataBase.Connection");
		if (con == null) {
	con = c.getConnection();
	application.setAttribute("DataBase.Connection", con);
		}
		button = request.getParameter("operation");
	} catch (Exception e) {
	}
	//take care of request
	String id[] = request.getParameterValues("stockid");
	String id1[] = request.getParameterValues("stockid1");
	//take care of request
	logger.info("After id get " + header);
	//CHAGNE
	logger.info("flag " + flag);
	if (flag == null) {
		//take care of request
		if (header != null
		&& (header.indexOf("NewIndexAction") != -1 || header
				.indexOf("NewIndexDefine.jsp") != -1)) {
	boolean ftoclearhasttable = true;
	stockCollection.setTotalMCV();
	try {
		if (button != null) {
			ftoclearhasttable = false;
		}
		if (exchangeCode != null) {
			ftoclearhasttable = false;
		}
	} catch (Exception ex) {
		logger.info("Inside catch of header " + ex);
	}
	if (ftoclearhasttable) {
		logger.info("Clearing old data  ");
		stockCollection.flushData();
	}

	//    stockCollection.flushData();
	logger.info("Inside if of header " + htFromFile.size());
		}
	}
	logger.debug("hashPresentFromFile is : " + " \n header "
	+ header.indexOf("NewIndexAction") + "  boolean "
	+ (!(header.indexOf("NewIndexAction") != 1)));
	if (hashPresentFromFile) {
		//to clear privious stocks
		if (header.indexOf("ImportNewStock") != -1
		|| header.indexOf("fileUploadNewStock") != -1) {
	logger.debug("manipulating source table");
	stockCollection.sourceTable.clear();
	//add new stocks got from file
	stockCollection.sourceTable.putAll(htFromFile);
		}
		logger.debug("stockCollection.sourceTable.size() after putting from file : "
		+ stockCollection.sourceTable.size());
	}
	if (button != null) {

		logger.info("button value is : " + button);
		if (button.equals("Remove") && id1 != null && id1.length > 0) {
	stockCollection.addStocksInSourceTable(id1);
		}

		if (button.equals("Add") && id != null && id.length > 0) {
	logger.info("Inside add");
	stockCollection.removeStocksFromSourceTable(id);
		}
		if (button.equals("Refresh") && id1 != null && id1.length > 0) {
	stockCollection.calculateAdjustedMktCap(id1, request);
		}

		if (button.equals("Submit")) {
	//take care of request
	logger.debug("TMVC is " + request.getParameter("T1"));
	if (request.getParameter("T1") != null) {
		try {
			iwferrors_flag = false;
			double tmar_cap = new Double(
					request.getParameter("T1")).doubleValue();
			if (tmar_cap == 0) {
				iwferrors_flag = true;
				iwferrors = "<LI>  Unable to Compute Index as total market Cap computes to zero </LI> ";
			}

		} catch (Exception e) {
			iwferrors_flag = true;
			iwferrors = "<LI> Unable to Compute Index as total market Cap computes to zero </LI> ";
		}

	} else {
		iwferrors_flag = true;
		iwferrors = "<LI> Unable to Compute Index as total market Cap computes to zero </LI> ";
	}
	if (iwferrors_flag) {
		String temp = "<H3><FONT color=\"red\">Validation Error</FONT></H3>You must correct the following error(s) before proceeding:<UL>";
		iwferrors = temp + iwferrors;
	}
	if (!iwferrors_flag) {

		//creating child indexes

		//       String queryforindexmaster = new Connect().queries.getProperty("insert_into_index_master");	        	       
		String queryforindexmaster = ConnectInit.queries
				.getProperty("insert_into_index_master");
		NewIndexForm form1 = (NewIndexForm) session
				.getAttribute("form");
		//take care of request
		String indextype = request.getParameter("indexType");

		//write code here to check for invalid iwf in case of free float index
		//take care of request
		if (!stockCollection.destinationTable.isEmpty()
				&& request.getParameter("indexType").trim()
						.equals("2")) {
			double individual_iwf = 0.0;

			Set set = stockCollection.destinationTable
					.entrySet();
			Map.Entry[] entries = (Map.Entry[]) set
					.toArray(new Map.Entry[set.size()]);
			Arrays.sort(entries, new Comparator() {
				public int compare(Object o1, Object o2) {
					//	StockDetails rs1 = (StockDetails)data.get(id);
					StockDetails v11 = (StockDetails) ((Map.Entry) o1)
							.getValue();
					StockDetails v22 = (StockDetails) ((Map.Entry) o2)
							.getValue();
					Object v1 = v11.getStockName();
					Object v2 = v22.getStockName();
					return ((Comparable) v1).compareTo(v2);
				}
			});
			int ix = 0;

			for (int i = 0; i < entries.length; i++) {
				//                         for(Enumeration e=stockCollection.destinationTable.keys();e.hasMoreElements();)
				//                    	{		
				String idiwf = (String) entries[i].getKey();
				//		                        	String idiwf = e.nextElement().toString();
				StockDetails rs = (StockDetails) stockCollection.destinationTable
						.get(idiwf);
				try {
					//take care of request                                 
					individual_iwf = new Double(
							request.getParameter("iwf:"
									+ rs.getStockID()))
							.doubleValue();
					if (individual_iwf < 0
							|| individual_iwf > 1) {

						iwferrors = iwferrors
								+ "<LI> IWF for Stock "
								+ rs.getStockName()
								+ " is invalid. Should be between 0 and 1</LI>";
						iwferrors_flag = true;
					}
				} catch (Exception ex) {
					iwferrors = iwferrors
							+ "<LI> IWF for Stock "
							+ rs.getStockName()
							+ " is invalid. Should be numeric</LI>";
					iwferrors_flag = true;

				}

			}
			if (iwferrors_flag) {
				String temp = "<H3><FONT color=\"red\">Validation Error</FONT></H3>You must correct the following error(s) before proceeding:<UL>";
				iwferrors = temp + iwferrors;
			}
		}
		//write code here to check for duplicate index name
		logger.info("iwferrors  : " + iwferrors);
		NewIndexForm form2 = (NewIndexForm) form1;
		String pid = form1.getI_indexID();
		if (!iwferrors_flag) {

			QueryClass.insertIntoIndexMaster(
					queryforindexmaster, form1, "", "");
			pid = form1.getI_indexID();

			stockCollection
					.storeComposition(
							pid,
							con,
							ConnectInit.queries
									.getProperty("insert_into_index_composition"));

			logger.info("After submit");
			stockCollection.flushData();
		}
		String url = response
				.encodeURL("/pages/IndexHome.jsp?D1=" + pid
						+ "&B2=Compute");

		//take care of request
		String flag1 = request
				.getParameter("checktocreatechild");

		String appcode = (String) session
				.getAttribute("applyCode");
		if (flag1 == null && appcode != null
				&& appcode.trim().equals("y")) {

			String url1 = "/pages/GetBaseValues.jsp?newIndexID="
					+ pid;
%>
<input type="hidden" name="indexName" value='<%=url1%>'>
<%
	if (!iwferrors_flag) {
%>
<jsp:forward page="<%=url1%>" />
<%
	}
		} else {
			//take care of request

			if (flag1 != null && appcode != null
					&& appcode.trim().equals("y")
					&& !iwferrors_flag) {
				//add to url to comput child indexes
				url = url + "&childcompute=yes";

				CallableStatement cs;
				cs = con.prepareCall("{ call public.define_and_compose_all_child_index(?) }");
				try {
					int inid = new Integer(pid).intValue();
					cs.setInt(1, inid);

					//   cs.execute(); 
				} catch (Exception ex) {

				}
			}
		}
		if (!iwferrors_flag) {
%>
<jsp:forward page="<%=url%>" />

<%
	}
	}
	//submit if ends here
		}
	}
%>



<table border="0" width="100%" cellspacing="0" cellpadding="0"
	height="176">
	<br>
	<tr>
		<td width="100%" colspan="3" bgcolor="#FFFFFF" height="1"
			align="center">
			<p>
				<font size="4" face="Arial Black">&nbsp;&nbsp; Index
					Composition</font>
		</td>
	</tr>
	<form action='<%=response.encodeURL("IndexCompositionviv.jsp")%>'
		method="POST">
		<!--      <input type="hidden" name="indexName" value="<request.getParameter('indexName')%>")>-->

		<input type="hidden" name="indexName"
			value='<%=request.getParameter("indexName")%>'> <input
			type="hidden" name="newIndexName"
			value='<%=request.getParameter("newIndexName")%>'> <input
			type="hidden" name="newIndexID"
			value='<%=request.getParameter("newIndexID")%>'> <input
			type="hidden" name="type" value='<%=request.getParameter("type")%>'>
		<input type="hidden" name="hasParent"
			value='<%=request.getParameter("hasParent")%>'> <input
			type="hidden" name="indexType"
			value='<%=request.getParameter("indexType")%>'> <input
			type="hidden" name="addflag"> <input type="hidden"
			name="exchangeflag"> <br>
		<tr>
			<%
				logger.info("Befor printing Printing errors");
					logger.info("sourceTable.size() a: "
							+ stockCollection.sourceTable.size());
					logger.info("destinationTable.size() a: "
							+ stockCollection.destinationTable.size());

					if (iwferrors_flag) {

						logger.info("Printing errors1");
			%>

			<%=iwferrors%>
			<br>
			<br>
			<%
				}
			%>

			<%
				try {

						if (!(request.getParameter("type").equals("1"))
								|| request.getParameter("hasParent").equals("false")) {
							logger.info("exchange is"
									+ request.getParameter("exchange"));
							String exchange = request.getParameter("exchange");
			%>


			<td width="28%" align="center" bgcolor="#D8D8D8" height="20">
				<p align="left">
					<font face="Arial" size="2">&nbsp;Mkt./Exchange</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font face="Arial" size="1"><select size="1" name="exchange"
						onchange="testexchg()">
							<option value="0" selected>Not Selected</option>

							<%=ListTypeClass.getListType(con,
							ConnectInit.queries
									.getProperty("stock_exchange_list"),
							exchange)%>

					</select>&nbsp; </font>
				</p>
			</td>
			<td width="28%" align="center" bgcolor="#D8D8D8" height="23"><input
				type="submit" value="Go" name="sub" style="float: left"></td>
			<%
				} else {
			%>
			<td width="28%" align="left" bgcolor="#D8D8D8" height="20">
				<h4>Composition of Parent Index</h4>
			</td>
			<td width="28%" align="center" bgcolor="#D8D8D8" height="23"></td>

			<%
				}
					} catch (Exception e) {
					}
			%>
			<td width="44%" align="center" bgcolor="#D8D8D8" height="23">
				<p>
					<font face="Arial" size="2">INDEX Name: </font>

					<%
						logger.info("sourceTable.size() a: "
											+ stockCollection.sourceTable.size());
									logger.info("destinationTable.size() a: "
											+ stockCollection.destinationTable.size());
					%>

					<!--     <font face="Arial" size="1"><select size="1" name="indexName">
          
         <%=ListTypeClass.getListTypeForComposition(con,
					ConnectInit.queries.getProperty("index_list_by_date_desc"),
					request.getParameter("indexName"))%> 
           	          
         </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></p> -->

					<%
						if (flag == null) {
										try {

											logger.info("sourceTable.size() b: "
													+ stockCollection.sourceTable.size());
											logger.info("destinationTable.size() b: "
													+ stockCollection.destinationTable.size());
											String name = request.getParameter("newIndexName");
											logger.info("name: " + name);
					%>
					<font face="Arial" size="2" color="red"><%=name%></font>
				</p> <%
 	} catch (Exception e) {
  		}
 %>
			</td>
			<%
				} else {
						try {

							logger.info("sourceTable.size() c: "
									+ stockCollection.sourceTable.size());
							logger.info("destinationTable.size() c: "
									+ stockCollection.destinationTable.size());
							String name = corporateact.getI_index();
							String query = ConnectInit.queries
									.getProperty("select_index_name");
							ResultSet rs = ListTypeClass1.getAffected(con, query, name);
							rs.next();
							String index_name = rs.getString("index_name");
			%>
			<font face="Arial" size="2" color="red"><%=index_name%></font>
			<%
				} catch (Exception e) {
						}
			%>
			<td width="56%" bgcolor="#D8D8D8" height="23" />
			<td width="44%" bgcolor="#D8D8D8" height="23" />
			<%
				}
			%>
		</tr>
		<tr>
			<td width="56%" bgcolor="#D8D8D8" height="19" colspan="2">
				&nbsp;</td>
			<td width="44%" bgcolor="#D8D8D8" height="19">&nbsp;</td>
		</tr>
		<tr>
			<td width="159%" colspan="3" bgcolor="#D8D8D8" height="1">
				<p align="center">
					&nbsp; <input type="submit" value="Add" name="add"
						onclick="return test1()" /> <input type="submit" value="Remove"
						name="remove" onclick="return test2()" /> <input type="submit"
						value="Refresh" name="Refresh" onclick="return test3()" />
		</tr>
		<tr>
			<td width="100%" colspan="3" bgcolor="#D8D8D8" height="19">
				<table border="1" cellpadding="0" cellspacing="0"
					style="border-collapse: collapse" bordercolor="#111111"
					width="100%" id="AutoNumber1">
					<tr>
						<td width="7%" align="center" bgcolor="#FFFFFF"><input
							type="checkbox" name="stockid" onclick="CheckAll(this)" /></td>
						<td width="24%" align="center" bgcolor="#FFFFFF"><font
							size="1" face="Arial Black">Stock Name</font></td>
						<td width="9%" align="center" bgcolor="#FFFFFF"><font
							size="1" face="Arial Black">IWF</font></td>
						<td width="9%" align="center" bgcolor="#FFFFFF"><font
							size="1" face="Arial Black">LTP</font></td>
						<td width="8%" align="center" bgcolor="#FFFFFF"><font
							size="1" face="Arial Black">Currency</font></td>
						<td width="11%" align="center" bgcolor="#FFFFFF"><font
							size="1" face="Arial Black">Tis</font></td>
						<td width="11%" align="center" bgcolor="#FFFFFF"><font
							size="1" face="Arial Black">Market Lot</font></td>
						<td width="11%" align="center" bgcolor="#FFFFFF"><font
							size="1" face="Arial Black">Date</font></td>
					</tr>

					<%
						try {
										String baseDate = ((NewIndexForm) session.getAttribute("form"))
												.getD_baseDate();

										/*if(!exchangeCode.euals(oldCode))
										{
											fsort.setcount();
										}
										int count=fsort.getcount();*/
										String oldCode = fsort.getOldCode();
										logger.info("Inside try");

										if (exchangeCode != null && !(exchangeCode.equals(oldCode))
												&& request.getParameter("hasParent").equals("true")) {

											logger.info("sourceTable.size() u: "
													+ stockCollection.sourceTable.size());
											logger.info("destinationTable.size() u: "
													+ stockCollection.destinationTable.size());
											String addflag = null;
											String exchangeflag1 = null;
											try {
												addflag = request.getParameter("addflag");
												exchangeflag1 = request.getParameter("exchangeflag");
											} catch (Exception e) {

											}

											boolean ff = false;
											if (exchangeflag1 != null) {
												if (exchangeflag1.trim().equals("yes")) {
													ff = true;
												}
												logger.info("exchangeflag1 is not null : ");
											}

											logger.info("sourceTable.size() m: "
													+ stockCollection.sourceTable.size());
											logger.info("destinationTable.size() m: "
													+ stockCollection.destinationTable.size());
											boolean dest_table_empty = stockCollection.destinationTable
													.isEmpty();
											if (addflag == null || ff) {
												logger.info("1.1 came here for 1st time ");
												if (ff) {
													logger.info("2");
													stockCollection
															.addStocksInSourceTable(
																	con,
																	ConnectInit.queries
																			.getProperty("stock_exchange_wise_stock_details1"),
																	exchangeCode, request
																			.getParameter("indexName"),
																	request.getParameter("type"), true,
																	baseDate);
												}
											} else {

											}

											logger.info("sourceTable.size() u1: "
													+ stockCollection.sourceTable.size());
											logger.info("destinationTable.size() u1: "
													+ stockCollection.destinationTable.size());

										}
										logger.info("new ex id is " + exchangeCode + " old ex -id"
												+ oldCode);

										if (request.getParameter("hasParent").equals("false")
												&& exchangeCode != null
												&& !(exchangeCode.equals(oldCode))) {

											//	if(count==1){
											logger.info("Inside parent false" + exchangeCode
													+ " count is ");
											if (!exchangeCode.trim().equals("0"))
												stockCollection
														.addStocksInSourceTable(
																con,
																ConnectInit.queries
																		.getProperty("stock_exchange_wise_stock_details"),
																exchangeCode);
											//	}
										}
										fsort.setOldCode(request.getParameter("exchange"));
										logger.info("after exchange " + request.getParameter("type"));
										if (request.getParameter("type").equals("2")
												&& stockCollection.destinationTable.isEmpty()) {

											logger.info("sourceTable.size() e: "
													+ stockCollection.sourceTable.size());
											logger.info("destinationTable.size() e: "
													+ stockCollection.destinationTable.size());
											logger.info("Inside if of try 2 ");
											boolean ff_toclearDestinationtable = false;
											String exchangeflag1 = null;
											Hashtable hashbackup = new Hashtable();
											try {

												if (request.getParameter("addflag") == null
														|| button.trim().equals("Remove")) {
													ff_toclearDestinationtable = true;

													hashbackup.putAll(stockCollection.destinationTable);
												}

											} catch (Exception e) {

											}
											Hashtable temp_dest = new Hashtable();
											temp_dest.putAll(stockCollection.destinationTable);
											stockCollection
													.addStocksInSourceTable(
															con,
															ConnectInit.queries
																	.getProperty("get_stock_details_for_child_index"),
															exchangeCode, request
																	.getParameter("indexName"), request
																	.getParameter("type"), false,
															baseDate);
											stockCollection.destinationTable.clear();
											stockCollection.destinationTable.putAll(temp_dest);

											if (ff_toclearDestinationtable) {
												stockCollection.destinationTable.clear();
												stockCollection.destinationTable.putAll(hashbackup);
											}
											stockCollection.setTotalMCV();
											logger.info("After setTotal");

										}
										logger.info("after type 2");
										if (request.getParameter("type").equals("1")
												&& stockCollection.sourceTable.isEmpty()) {

											String addflag = null;
											try {
												addflag = request.getParameter("addflag");
											} catch (Exception e) {

											}
											if (addflag == null) {
												stockCollection
														.addStocksInSourceTable(
																con,
																ConnectInit.queries
																		.getProperty("get_stock_details_for_child_index"),
																exchangeCode, request
																		.getParameter("indexName"),
																request.getParameter("type"), false,
																baseDate);
											}

										}
										logger.info("after type 1");

										oldIndexId = request.getParameter("indexName");
										logger.info("" + exchangeCode + " " + oldCode);

									} catch (Exception e) {
										logger.info("Error in catch " + e.getMessage());

									}
									logger.info("sourceTable.size() q: "
											+ stockCollection.sourceTable.size());
									logger.info("destinationTable.size() q: "
											+ stockCollection.destinationTable.size());

									if (flag != null) {
										String query = ConnectInit.queries
												.getProperty("select_from_stock_master");//c.queries.getProperty("get_stock_details_for_child_index");

										StringBuffer str = ListTypeClass.addRowsInTable2(corporateact,
												query, con);
										if (str != null) {
					%>
					<%=str.toString()%>
					<%
						}
									} else {
										StringBuffer str = ListTypeClass
												.addRowsInTable(stockCollection.sourceTable);
										if (str != null) {
					%>
					<%=str.toString()%>
					<%
						} else {
					%>

					<tr>
						<td width="7%" align="center">&nbsp;</td>
						<td width="24%" align="center">
							<p>&nbsp;</p>
						</td>
						<td width="9%" align="center">&nbsp;</td>
						<td width="9%" align="center">&nbsp;</td>
						<td width="8%" align="center">&nbsp;</td>
						<td width="11%" align="center">&nbsp;</td>
						<td width="11%" align="center">&nbsp;</td>
						<td width="11%" align="center">&nbsp;</td>
					</tr>
					<%
						}
									}
					%>

				</table>
			</td>
		</tr>
		<tr>
			<td width="100%" colspan="3" bgcolor="#D8D8D8" height="19">
				<p align="center">&nbsp;</p>
			</td>
		</tr>
		<tr>
			<td width="159%" colspan="3" bgcolor="#D8D8D8" height="1">
				<p align="center">
					<input type="hidden" name="operation"> <input type="submit"
						value="Add" name="add" onclick="return test1()" /> <input
						type="submit" value="Remove" name="remove"
						onclick="return test2()" /> <input type="submit" value="Refresh"
						name="Refresh" onclick="return test3()" />
		</tr>
		<tr>
			<%
				logger.info("sourceTable.size() r: "
							+ stockCollection.sourceTable.size());
					logger.info("destinationTable.size() r: "
							+ stockCollection.destinationTable.size());
					if (stockCollection.getErrorMessage() != null) {
			%>
			<font color="red" face="Courier New" size="3">&nbsp;&nbsp;&nbsp;<%=stockCollection.getErrorMessage()%></font>
			<%
				}
			%>
		</tr>

		<tr>
			<td width="100%" colspan="3" bgcolor="#D8D8D8" height="99">
				&nbsp;

				<table border="1" width="100%" bordercolorlight="#000000"
					cellspacing="0" cellpadding="0" bordercolordark="#000000">
					<tr>
						<td width="100%">
							<table border="1" width="100%" cellspacing="0" cellpadding="0">
								<tr>
									<td width="5%" align="center" bgcolor="#FFFFFF"><input
										type="checkbox" name="stockid1" onclick="CheckAll2(this)" /></td>
									<td width="16%" align="center" bgcolor="#FFFFFF"><font
										size="1" face="Arial">Stock Name</font></td>
									<td width="9%" align="center" bgcolor="#FFFFFF"><font
										size="1" face="Arial">IWF</font></td>
									<td width="10%" align="center" bgcolor="#FFFFFF"><font
										size="1" face="Arial">LTP</font></td>
									<td width="12%" align="center" bgcolor="#FFFFFF"><font
										size="1" face="Arial">Currency</font></td>
									<td width="13%" align="center" bgcolor="#FFFFFF"><font
										size="1" face="Arial">Outstanding</font></td>
									<td width="11%" align="center" bgcolor="#FFFFFF"><font
										size="1" face="Arial">Mkt. Capital</font></td>
									<td width="10%" align="center" bgcolor="#FFFFFF"><font
										size="1" face="Arial">Adjusted Mkt</font></td>
									<td width="26%" align="center" bgcolor="#FFFFFF"><font
										size="1" face="Arial">Weight age</font></td>
								</tr>
								<%
									logger.info("sourceTable.size() s: "
																	+ stockCollection.sourceTable.size());
															logger.info("destinationTable.size() s: "
																	+ stockCollection.destinationTable.size());

															StringBuffer str1 = ListTypeClass.addRowsInSecondTable(
																	stockCollection.destinationTable, request);
															if (str1 != null) {
								%>
								<%=str1.toString()%>
								<%
									} else {
								%>


								<tr>
									<td width="5%" align="center">&nbsp;</td>
									<td width="16%" align="center">&nbsp;</td>
									<td width="9%" align="center"><font size="1" face="Arial">
											<input type="text" name="T1" size="11">
									</font></td>
									<td width="10%" align="center">&nbsp;</td>
									<td width="12%" align="center">&nbsp;</td>
									<td width="13%" align="center">&nbsp;</td>
									<td width="11%" align="center">&nbsp;</td>
									<td width="10%" align="center">&nbsp;</td>
									<td width="26%" align="center">&nbsp;</td>
								</tr>
								<%
									}
								%>

							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%" bgcolor="#D8D8D8" height="40" colspan="3">
				<table border="0" width="100%">
					<tr>
						<td width="41%">
							<table border="0" width="100%">
								<tr>
									<td width="100%" align="left" colspan="2">
										<%
											if (request.getParameter("checktocreatechild") != null) {
										%> <input type="checkbox" name="checktocreatechild"
										checked="checked" /> <%
 	} else {
 %> <input type="checkbox" name="checktocreatechild" /> <%
 	}
 %> Create Child Indices using Parent's base value
									</td>
								</tr>
								<tr>
									<td width="28%" align="right"><input type="submit"
										value="<< Back" name="B3" style="float: left" onclick="" /></td>
									<td width="72%" align="left"><input type="submit"
										value="Compute" name="B3" style="float: left"
										onclick="return test4()" /></td>
								</tr>
							</table>
						</td>
						<td width="59%" align="right">
							<table border="0" width="100%">
								<tr>
									<td width="100%" align="right"><font size="1" face="Arial">No.
											of Stocks <input type="text" align="right" size="6"
											value="<%=stockCollection.getTotalStocks()%>">
									</font>&nbsp;&nbsp;&nbsp; <%
 	//	org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
  	AdjustDecimal ad = ConnectInit.getAdjustDecimal();
  	String total = ad.shareholdingpatt(stockCollection.getTotalMCV());
 %> <font size="1" face="Arial">Index. (Mkt. cap) Total <input
											type="text" name="T1" size="10" value="<%=total%>">&nbsp;
											Rs. Crores
									</font></td>
								</tr>
								<tr>
									<td width="100%" align="right"><input type="button"
										value="Browse..." name="B3">&nbsp; <font size="1"
										face="Arial"><input type="text" name="T1" size="6"></font>&nbsp;
										<!--  <input type="button" value="Upload" name="B3"></td>--> <!-- <a href="/Income/pages/ImportNewStock.jsp?from=ICompose">Import From file...</a></td> -->
										<%
											//		String sname=request.getServerName();			
																			//		int sport=request.getServerPort();
																			//		String contextpath=request.getContextPath();
																			//		String servletname="http://"+sname+":"+sport+contextpath+"/app.SaveRequest";
																			//		String servletname="http://"+sname+":"+sport+contextpath+"/callServlet";
																			String servletname = "/Income/pages/ImportNewStock.jsp?from=ICompose";
																			servletname = response
																					.encodeURL("ImportNewStock.jsp?from=ICompose");

																			if (header.indexOf("ImportNewStock") == -1
																					&& header.indexOf("fileUploadNewStock") == -1) {
																				logger.debug("In if setting requestobject attribute ");
																				if (header != null
																						&& (header.indexOf("NewIndexAction") != -1 || header
																								.indexOf("NewIndexDefine.jsp") != -1))
																					session.setAttribute("requestobject", request);
																			} else if (!(header.indexOf("IndexCompositionviv") == -1)) {
																				logger.debug("In else if setting requestobject attribute ");
																				session.setAttribute("requestobject",
																						session.getAttribute("requestobject"));
																			}
										%> <!--    <a href="<%=servletname%>" >Import From file...</a></td> -->
										<a href="<%=servletname%>">Import From file...</a></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<p align="right">&nbsp;
			</td>
		</tr>
		<%
			logger.info("sourceTable.size() t : "
					+ stockCollection.sourceTable.size());
			logger.info("destinationTable.size() t : "
					+ stockCollection.destinationTable.size());
		%>
		<tr>
	</form>
	<%
		if (header != null
			&& (header.indexOf("NewIndexAction.do") != -1 || header
					.indexOf("NewIndexDefine") != -1)) {
	%>
	<script>
		var object = eval('document.forms[0].checktocreatechild');
		object.checked = true;
	</script>
	<%
		}
	%>
	<form enctype="multipartform">
		</tr>
</table>
</form>
<p align="right">
	&nbsp;
	</td>
	</tr>
	</tbody>
</table>
<table border="0" width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td width="2%"></td>
		<td width="98%"></td>
	</tr>
</table>
<script language="javascript">
	function test1() {
		document.forms[0].operation.value = "Add";
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
				window.open('../Income/pages/GetBaseValues.jsp');
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
</script>


</html>