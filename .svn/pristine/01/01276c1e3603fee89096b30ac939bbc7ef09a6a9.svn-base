
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page language="java" import="app.*"%>

<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.*"%><%@page import="org.apache.log4j.Logger"%>
<%
	Logger log = Logger.getLogger(this.getClass());
	log.info("inside " + this.getClass().getSimpleName() + " page");
%>
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
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

<jsp:useBean id="backTestIndexBean" scope="session"
	class="app.BackTestIndexForm" />

<html:html>
<html:base />
<head>

<SCRIPT language=javascript>
	function hideLeftCol(id) {
		if (this.document.getElementById(id).style.display == 'none') {
			this.document.getElementById(id).style.display = 'inline';
			document.getElementById("HideHandle").src = 'closeImage.gif';
			// this.document.getElementById(id).style.width='10px';
			Set_Cookie('showLeftCol', 'true', 30, '/', '', '');
			var show = Get_Cookie('showLeftCol');
			//document['HideHandle'].src = 'images/hide.gif';
			document.getElementById("HideHandle").src = '../open.gif';
		} else {
			// this.document.getElementById(id).style.display='none';
			this.document.getElementById(id).style.display = 'none';
			document.getElementById("HideHandle").src = 'openImage.gif';
			Set_Cookie('showLeftCol', 'false', 30, '/', '', '');
			var show = Get_Cookie('showLeftCol');
			//document['HideHandle'].src = 'images/show.gif';
		}
	}
</script>


<link rel="stylesheet" type="text/css" href="./StyleSheet.css"
	title="Default" />

<script language="javascript" src="box_ex.js"></script>
<title>Back Tracking</title>
<script language="javascript" src="./codethatcalendarstd.js"></script>

<script language="javascript" src="box_ex.js"></script>
<script language="javascript">
	var c2 = new CodeThatCalendar(caldef2);
</script>


</head>

<body>

	<table width="100%" height="100%">

		<tr>
			<td align="left" valign="top" bgcolor="#CCddee">

				<DIV id="leftCol" style="DISPLAY: none; width: 160; height: 100%;">
					<%@ include file="tree3.jsp"%>
				</div> <IMG id="HideHandle" src="openImage.gif"
				style="CURSOR: hand; position: absolute;"
				onclick='hideLeftCol("leftCol");' src="fold.gif" width="10"
				height="25">
			</td>
			<td align="left" valign="top"><html:form
					action="/backtestaction">



					<html:errors />
					<%
						if (request.isRequestedSessionIdValid()) {
					%>
					<jsp:setProperty name="backTestIndexBean" property="user_id"
						value='<%=session.getAttribute("userid").toString()%>' />
					<%
						}
					%>
					<p></p>
					<center>
						<table width="1000" cellspacing="0" cellpadding="0">
							<!-- Table for Displaying Title -->
							<tr>
								<td class="subHeader" width="665" align="left" colspan="2"
									nowrap="nowrap"><b>Index Back Tracking >> </b></td>
							</tr>

						</table>

						<table border="0" width="1000" cellspacing="0" cellpadding="0">


							<tr>
								<td>&nbsp;&nbsp;</td>

							</tr>
							<tr>
								<td align="left" nowrap="nowrap" width="800"><font
									face="arial-helvitica" size="2"> <b>Select Index
											Name &nbsp;&nbsp;&nbsp;&nbsp;</b>
								</font> <html:select property="index" onchange="go();"
										name="backTestIndexBean">
										<html:optionsCollection name="backTestIndexBean"
											property="indexcollection" />
									</html:select></td>

							</tr>

							<tr>
								<td>&nbsp;&nbsp;</td>

							</tr>

							<tr>
								<td width="1500" align="left"><font face="arial-helvitica"
									size="2"><b>Index name </b> </font> &nbsp;&nbsp; &nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <html:text
										property="index_name" name="backTestIndexBean" size="10"
										disabled="disabled" /> &nbsp;&nbsp; <font
									face="arial-helvitica" size="2"><b>Index Type </b></font>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <html:text property="index_type"
										name="backTestIndexBean" size="15" disabled="disabled" />
									&nbsp;&nbsp; <font face="arial-helvitica" size="2"><b>Index
											From Date</b> </font> &nbsp;&nbsp; <html:text property="index_from"
										name="backTestIndexBean" readonly="true" size="10"
										maxlength="10" onblur="checkdate(this)" /> <html:button
										property="shwFrom" value="..."
										onclick="c2.popup('index_from');" /></td>
							</tr>
							<tr>
								<td width="1000" align="left">
									<!-- <font face="arial-helvitica" size="2" ><b>Index From Date</b> </font>  &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
            		
             			<html:text property="index_from" name="backTestIndexBean" size="10"  onblur="checkdate(this)"/> &nbsp;&nbsp; -->

									<font face="arial-helvitica" size="2"><b>Base Date </b></font>
									&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

									<html:text property="base_date" name="backTestIndexBean"
										disabled="disabled" size="10" onblur="checkdate(this)" />

									&nbsp;&nbsp; <!--  <font face="arial" size="3" >Index Upto Date</font>
            		
             			<html:text property="index_upto" name="backTestIndexBean"  disabled="disabled"  size="10" onblur="checkdate(this)"/>
           			--> <font face="arial-helvitica" size="2"><b>Base
											value </b></font> &nbsp;&nbsp; &nbsp;&nbsp; <html:text
										property="base_value" name="backTestIndexBean" size="15"
										disabled="disabled" onblur="" /> &nbsp;&nbsp; <font
									face="arial-helvitica" size="2"><b> Base Currency </b></font>
									&nbsp;&nbsp; &nbsp;&nbsp; <html:text property="base_currency"
										name="backTestIndexBean" size="10" disabled="disabled"
										onblur=" " /> &nbsp;&nbsp;


								</td>
							</tr>

							<!--   <tr>       
                        <td width="800" align="left" >   	
	        		<font face="arial-helvitica" size="2" ><b> Base Currency </b></font> &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            		
             			<html:text property="base_currency" name="backTestIndexBean"  size="10"  disabled="disabled" onblur=" "/> &nbsp;&nbsp;
           			
          			<font face="arial-helvitica" size="2" ><b>Base value </b></font> &nbsp;&nbsp; &nbsp;&nbsp;
            		
             			<html:text property="base_value" name="backTestIndexBean"  size="10" disabled="disabled" onblur=""/> &nbsp;&nbsp; &nbsp;&nbsp;
           			 </td>
           			
                </tr> -->


							<tr>
								<td>&nbsp;&nbsp;</td>

							</tr>

							<tr>
								<td align="left" nowrap="nowrap" width="500"><font
									face="arial-helvitica" size="2"> <b>Select Stock
											Name &nbsp;&nbsp;&nbsp;&nbsp;</b>
								</font> <html:select property="stkselect" size="5"
										styleId="ac_selections" multiple="true" style="width:400px;">
										<html:optionsCollection name="backTestIndexBean"
											property="stkcollection" />
									</html:select></td>
								<td align="left" nowrap="nowrap" width="500"><font
									face="arial-helvitica" size="2"> <b>Select Stock
											Exchange &nbsp;&nbsp;&nbsp;&nbsp;</b>
								</font> <html:select property="exchselect" size="1"
										styleId="exc_selections_1" style="width:200px;">
										<html:optionsCollection name="backTestIndexBean"
											property="exchcollection" />
									</html:select> <html:submit property="v1" onclick="go1();"> View </html:submit>

									<html:select property="exchstkselect" size="5"
										styleId="exc_selections" multiple="true" style="width:400px;">
										<html:optionsCollection name="backTestIndexBean"
											property="exchstkcollection" />
									</html:select></td>
							</tr>

							<tr>
								<td>&nbsp;&nbsp;</td>

							</tr>



							<tr>
								<td align="left" nowrap="nowrap" width="500" class="tab"><font
									face="arial-helvitica" size="2"> <b>Exclusion Date
											&nbsp;&nbsp;&nbsp;&nbsp;</b>
								</font> <html:text property="excl_date" name="backTestIndexBean"
										size="10" readonly="true" disabled="disabled"
										onblur="checkdate(this)" /> <html:button property="shwexcl"
										value="..." onclick="c2.popup('excl_date');" /> <html:submit
										property="remove" onclick="remove_scrip();return false;done()">Remove</html:submit></td>

								<td align="left" nowrap="nowrap" width="500" class="tab"><font
									face="arial-helvitica" size="2"> <b>Inclusion Date
											&nbsp;&nbsp;&nbsp;&nbsp;</b>

								</font> <html:text property="incl_date" name="backTestIndexBean"
										size="10" readonly="true" disabled="disabled"
										onblur="checkdate(this)" /> <html:button property="shwincl"
										value="..." onclick="c2.popup('incl_date');" /> <html:submit
										property="add" onclick="add_scrip();return false;done()">Add</html:submit></td>

							</tr>
							<tr>
								<td><font face="arial-helvitica" color="red" size="2">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										Note:- Please apply the inclusion and exclusion events in
										reverse manner for backtracking </font> &nbsp;&nbsp;</td>

							</tr>

							<tr>
								<td align="left" nowrap="nowrap" width="500" class="tab"><font
									face="arial-helvitica" size="2"> <b>Excluded Stock
											&nbsp;&nbsp;&nbsp;&nbsp;</b>
								</font> <%-- 								<html:text property="excl_stock" name="backTestIndexBean" --%>
									<%-- 										size="20" readonly="true" disabled="disabled"> --%>
									<%-- 									</html:text> --%> <html:select
										property="excl_scrip" size="10" styleId="Rem_incl"
										multiple="true" style="width:400px;">
										<html:optionsCollection name="backTestIndexBean"
											property="excludedScripCollection" />
									</html:select></td>
								<td align="left" nowrap="nowrap" width="500" class="tab"><font
									face="arial-helvitica" size="2"> <b>Included Stock
											&nbsp;&nbsp;&nbsp;&nbsp;</b>
								</font> <%-- 								<html:text property="incl_stock" name="backTestIndexBean" --%>
									<%-- 										size="20" readonly="true" disabled="disabled"> --%>
									<%-- 									</html:text> --%> <html:select
										property="incl_scrip" size="10" styleId="Rem_excl"
										multiple="true" style="width:400px;">
										<html:optionsCollection name="backTestIndexBean"
											property="includedScripCollection" />
									</html:select></td>
							</tr>
							<tr>
								<td>&nbsp;&nbsp;</td>

							</tr>


							<tr>

								<td align="left" nowrap="nowrap" width="500" class="tab"><font
									face="arial-helvitica" size="2"> <b>BackTracked
											Index Value &nbsp;&nbsp;&nbsp;&nbsp;</b></font> <html:text
										property="btvalue" name="backTestIndexBean" size="15"
										readonly="true" disabled="disabled" /></td>

							</tr>
							<tr>
								<td>&nbsp;&nbsp;</td>

							</tr>


							<tr>
								<td width="700" align="right"><html:submit
										property="compute" onclick=" done()">Compute</html:submit> <!-- <html:button property="resetbutton"  onclick="reset1()">Reset</html:button> -->

									<html:button onclick="history.go(-1)" property="cancelButton">
         			cancel
      			</html:button> <html:submit property="reset" onclick="reset1();">Reset</html:submit>
								</td>
							</tr>
						</table>



						<html:hidden property="operation" value="no" />
				</html:form></td>
		</tr>
	</table>
	<script language="javascript">
		document.forms[0].index_name.disabled = true;
		document.forms[0].index_type.disabled = true;
		document.forms[0].base_date.disabled = true;
		document.forms[0].base_value.disabled = true;
		document.forms[0].base_currency.disabled = true;
		document.forms[0].index_upto.disabled = true;
	</script>



	<script language="javascript">
		function done() {

			document.forms[0].submit();

		}

		function done1() {
			document.forms[0].submit();
		}
		function go() {
			document.forms[0].submit();
		}
		function go1() {
			document.forms[0].submit();
		}
		function reset1() {
			document.forms[0].reset.value = "Reset";
			document.forms[0].submit();
		}
	</script>
	<script language="javascript">
		function resetFunc() {
			document.forms[0].operation.value = "Reset";
			var rhsSelect = document.getElementById("Rem");
			rhsSelect.options.length = 0;
			document.forms[0].selectRName.value = "value0";
		}
		function setExcChange() {
			document.forms[0].operation.value = "Save";
			document.forms[0].exchoperation.value = "exch";
			document.forms[0].submit();
		}
		function saveFunc() {
			//alert("inside the saveFunc");
			selectAll();
			document.forms[0].rem.value = nodesToSend;
			document.forms[0].operation.value = "Save";
			document.forms[0].operationSubmit.value = "Submit";
			var nodesToSend = new Array();
			var rhsSelect1 = document.getElementById("Rem");
			var numToRemove1 = 0;
			for ( var index2 = 0; index2 < rhsSelect1.options.length; index2++) {
				nodesToSend[numToRemove1++] = rhsSelect1.options[index2].value;

			}
			//alert("calling the select All function");
			//selectAll();      	
			return true;
		}
		function showDetails() {
			document.forms[0].rem.value = nodesToSend;
			document.forms[0].operation.value = "Save";
			document.form[0].submit();
			var nodesToSend = new Array();
			var rhsSelect1 = document.getElementById("Rem");
			var numToRemove1 = 0;
			for ( var index2 = 0; index2 < rhsSelect1.options.length; index2++) {
				nodesToSend[numToRemove1++] = rhsSelect1.options[index2].value;

			}
			return true;
		}
		function changeFields() {
			document.forms[0].operation.value = "Reset";
			document.forms[0].select_rname.value = document.forms[0].selectRName.value
			var val1 = document.forms[0].selectRName.value;

			if (val1 == "value0") {
				var rhsSelect = document.getElementById("Rem");
				var nodesToRemove = new Array();
				var numToRemove = 0;

				for ( var index = 0; index < rhsSelect.options.length; index++) {

					nodesToRemove[numToRemove++] = rhsSelect.options[index];
					var elementValue = rhsSelect.options[index].value;

				}
				for ( var index = 0; index < nodesToRemove.length; index++) {
					rhsSelect.removeChild(nodesToRemove[index]);

				}

				return false;
			}
			document.forms[0].submit();
		}
		function add_scrip() {
			if (document.forms[0].incl_date.value == "") {
				alert("Select Inclusion Date");
				return false;
			}
			var rhsSelect = document.getElementById("Rem_excl");
			var lhsSelect = document.getElementById("exc_selections");
			var j = 0;
			for ( var index = 0; index < lhsSelect.options.length; index++) {
				var flag = 0;
				for ( var index1 = 0; index1 < rhsSelect.options.length; index1++) {
					var nToRem = rhsSelect.options[index1].value;
					if (lhsSelect.options[index].selected == true) {
						var nToAdd = lhsSelect.options[index].value + "::"
								+ document.forms[0].incl_date.value;
						if (nToAdd == nToRem) {
							alert(rhsSelect.options[index1].innerHTML
									+ " is already Added");
							flag = 1;
							break;
						}
					}
				}
				if (flag == 0) {
					if (lhsSelect.options[index].selected == true) {
						var optionElement = document.createElement("option");
						optionElement.value = lhsSelect.options[index].value
								+ "::" + document.forms[0].incl_date.value;
						optionElement.innerHTML = lhsSelect.options[index].innerHTML
								+ "::" + document.forms[0].incl_date.value;
						rhsSelect.appendChild(optionElement).selected = true;
					}
				}
			}

			for ( var index = 1; index < rhsSelect.options.length; index++) {
				rhsSelect.options[index].selected = true;
			}

		}

		function remove_scrip() {
			if (document.forms[0].excl_date.value == "") {
				alert("Select Exclusion Date");
				return false;
			}
			var rhsSelect = document.getElementById("Rem_incl");
			var lhsSelect = document.getElementById("ac_selections");
			//alert(" date is "+document.forms[0].excl_date.value);	
			var j = 0;
			for ( var index = 0; index < lhsSelect.options.length; index++) {
				var flag = 0;
				for ( var index1 = 0; index1 < rhsSelect.options.length; index1++) {
					var nToRem = rhsSelect.options[index1].value;
					if (lhsSelect.options[index].selected == true) {
						var nToAdd = lhsSelect.options[index].value + "::"
								+ document.forms[0].excl_date.value;
						if (nToAdd == nToRem) {
							alert(rhsSelect.options[index1].innerHTML
									+ " is already Added");
							flag = 1;
							break;
						}
					}
				}
				if (flag == 0) {
					if (lhsSelect.options[index].selected == true) {
						var optionElement = document.createElement("option");
						optionElement.value = lhsSelect.options[index].value
								+ "::" + document.forms[0].excl_date.value;
						optionElement.innerHTML = lhsSelect.options[index].innerHTML
								+ "::" + document.forms[0].excl_date.value;
						rhsSelect.appendChild(optionElement).selected = true;
					}
				}
			}

			for ( var index = 1; index < rhsSelect.options.length; index++) {
				rhsSelect.options[index].selected = true;
			}

		}

		function excludeIndcludedScrips() {

			if (document.forms[0].incl_date.value == "") {
				alert("Select Inclusion Date");
				return false;
			}

			if (document.forms[0].excl_date.value == "") {
				alert("Select Exclusion Date");
				return false;
			}

			//alert("inclusion date is "+document.forms[0].incl_date.value);
			//alert("exclusion date is "+document.forms[0].excl_date.value);

			//put here the data validation 

			var rhsSelect = document.getElementById("Rem_incl");
			//alert("1 rhsSelect "+rhsSelect);
			//alert("1.1 rhsSelect.length == "+rhsSelect.options.length);
			var lhsSelect = document.getElementById("Rem_excl");
			var j = 0;

			//alert("2 lhsSelect "+lhsSelect)
			//alert("2.2 lhsSelect.length == "+lhsSelect.options.length);

			for ( var index = 0; index < lhsSelect.options.length; index++) {
				//alert("outer loop ...");
				var flag = 0;
				for ( var index1 = 0; index1 < rhsSelect.options.length; index1++) {
					//alert("inner loop ...");
					var tempy = rhsSelect.options[index1].value;
					//alert("rhsSelect.options["+index1+"].value "+ rhsSelect.options[index1].value);

					tempy = tempy.substring(0, tempy.indexOf(':'));
					tempy = tempy + "::" + document.forms[0].excl_date.value;
					alert("The left side value  = " + tempy);

					var nToRem = tempy;

					if (lhsSelect.options[index].selected == true) {
						//alert("lhsSelect.options["+index+"].selected "+lhsSelect.options[index].selected);

						var tempx = lhsSelect.options[index].value;

						//alert("lhsSelect.options["+index+"].value "+lhsSelect.options[index].value);
						tempx = tempx.substring(0, tempx.indexOf(':'));
						tempx = tempx + "::"
								+ document.forms[0].excl_date.value;

						//var nToAdd=lhsSelect.options[index].value+"::"+document.forms[0].incl_date.value;
						var nToAdd = tempx;

						if (nToAdd == nToRem) {
							//alert("The scrip to add is ************* "+nToRem);
							//alert("the scrip to delete is **********"+nToAdd);

							alert(rhsSelect.options[index1].innerHTML
									+ " is already Added");
							flag = 1;
							break;
						}

						//var temp = rhsSelect
						var inclComp = nToAdd.substring(
								nToAdd.lastIndexOf(':') + 1, nToAdd.length);
						//alert("the inclusion date is "+inclComp);
						var exclComp = document.forms[0].excl_date.value;
						var base = document.forms[0].base_date.value;

						var inclCompDate = new Date();
						var exclCompDate = new Date();
						var baseDate = new Date();

						inclCompDate.setFullYear(inclComp.substring(6, 10),
								inclComp.substring(3, 5), inclComp.substring(0,
										2));
						exclCompDate.setFullYear(exclComp.substring(6, 10),
								exclComp.substring(3, 5), exclComp.substring(0,
										2));
						baseDate.setFullYear(base.substring(6, 10), base
								.substring(3, 5), base.substring(0, 2));
						//alert("date collected");
						if (exclCompDate < inclCompDate) {
							alert("Exclusion Date cannot be less then Inclusion Date");
							return false;
						}

						if (baseDate < exclCompDate) {
							alert("Base Date cannot be less then Exclusion Date");
							return false;
						}

					}
				}
				//alert("ok4");		
				if (flag == 0) {
					//alert("ok5");
					//alert("lhsSelect.options["+index+"].selected ==  "+lhsSelect.options[index].selected);
					if (lhsSelect.options[index].selected == true) {
						var temp = document.forms[0].excl_date.value;

						var optionElement = document.createElement("option");
						var xp = lhsSelect.options[index].value;
						xp = xp.substring(0, xp.indexOf(':'));
						optionElement.value = xp + "::" + temp;
						alert("selected optionElement.value == "
								+ optionElement.value);
						//optionElement.innerHTML = (lhsSelect.options[index].innerHTML);//+"::"+document.forms[0].incl_date.value;
						var t = lhsSelect.options[index].innerHTML;
						//alert("t ==================== "+t);

						t = t.substring(0, t.indexOf(':'));
						//alert("t ==================== "+t);
						t = t + "::" + temp;
						//alert("t ==================== "+t);
						optionElement.innerHTML = t;
						alert("optionElement.innerHTML = " + t);
						//alert("===============element appended");
						rhsSelect.appendChild(optionElement).selected = true;
					}
				}
			}
			//alert("ok6");	
			for ( var index = 1; index < rhsSelect.options.length; index++) {
				rhsSelect.options[index].selected = true;
			}
			//alert("ok7");

		}

		function remove_scrip_x() {

			// 			if (document.forms[0].excl_date.value == "") {
			// 				alert("Select Exclusion Date");
			// 				return false;
			// 			}

			var rhsSelect = document.getElementById("Rem_incl");
			var lhsSelect = document.getElementById("Rem_excl");
			var j = 0;
			for ( var index = 0; index < lhsSelect.options.length; index++) {
				var flag = 0;
				for ( var index1 = 0; index1 < rhsSelect.options.length; index1++) {
					var nToRem = rhsSelect.options[index1].value;
					if (lhsSelect.options[index].selected == true) {
						var nToAdd = lhsSelect.options[index].value + "::"
								+ document.forms[0].incl_date.value;
						nToAdd = lhsSelect.options[index].value;

						nToAdd = nToAdd.substring(0, nToAdd.indexOf(':'));
						nToAdd = nToAdd + "::"
								+ document.forms[0].excl_date.value;
						if (nToAdd == nToRem) {
							alert(rhsSelect.options[index1].innerHTML
									+ " is already Added");
							flag = 1;
							break;
						}
					}
				}
				if (flag == 0) {
					if (lhsSelect.options[index].selected == true) {
						var optionElement = document.createElement("option");
						var tempVal = lhsSelect.options[index].value;
						tempVal = tempVal.substring(0, tempVal.indexOf(':'));
						tempVal = tempVal + "::"
								+ document.forms[0].excl_date.value;

						optionElement.value = lhsSelect.options[index].value
								+ "::" + document.forms[0].incl_date.value;
						optionElement.value = tempVal;
						optionElement.innerHTML = lhsSelect.options[index].innerHTML
								+ "::" + document.forms[0].incl_date.value;

						tempVal = lhsSelect.options[index].innerHTML;
						tempVal = tempVal.substring(0, tempVal.indexOf(':'));
						tempVal = tempVal + "::"
								+ document.forms[0].excl_date.value;

						optionElement.innerHTML = tempVal;

						rhsSelect.appendChild(optionElement).selected = true;
					}
				}
			}

			for ( var index = 1; index < rhsSelect.options.length; index++) {
				rhsSelect.options[index].selected = true;
			}

		}

		function selectAll() {
			//alert("inside select all");
			var rhsSelect = document.getElementById("Rem_incl");
			var lhsSelect = document.getElementById("Rem_excl");

			var lhsSelect1 = document.getElementById("exc_selections");
			var lhsSelect2 = document.getElementById("ac_selections");

			for ( var index = 0; index < rhsSelect.options.length; index++) {
				rhsSelect.options[index].selected = true;
			}

			for ( var index = 0; index < lhsSelect.options.length; index++) {
				lhsSelect.options[index].selected = true;
			}
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
			if ((document.forms[0].fromdate.value) == "")
				document.forms[0].fromdate.value = td + s + mnth + s + yr;
			if ((document.forms[0].todate.value) == "")
				document.forms[0].todate.value = td + s + mnth + s + yr;
			//alert(" initialize");

			var length_2 = document.forms[0].s_stock.length;
			arrSelectedIndex_2 = new Array(length_2);
			arrOldValues_2 = new Array(length_2);
			for ( var counter = 0; counter < length_2; counter++) {
				arrSelectedIndex_2[counter] = 0;
				arrOldValues_2[counter] = 0;
			}
		}
		function checkdate(objName) {
			var datefield = objName;
			if (datefield.value != '') {
				;
			}

			if (datefield.length < 8) {
				datefield.select();
				alert(" Please insert data in (dd/mm/yyyy) format. ");
				return false;
			}

			if (chkDate(objName) == false) {

				alert("That date is invalid.  Please try again.");
				datefield.select();

				datefield.focus();
				return false;
			} else {
				return true;
			}
		}

		function remove() {

			var pulld = document.forms[0].excl_scrip;
			//alert(pulld);
			var len = pulld.options.length;
			var i = 0
			for (i = 0; i < len; i++) {
				if (pulld[i].selected) {
					pulld[i] = null;
				}
			}
			return;
		}

		function remove1() {

			var pulld = document.forms[0].incl_scrip;
			//alert(pulld);
			var len = pulld.options.length;
			var i = 0
			for (i = 0; i < len; i++) {
				if (pulld[i].selected) {
					pulld[i] = null;
				}
			}
			return;
		}
	</script>

</body>
</html:html>
