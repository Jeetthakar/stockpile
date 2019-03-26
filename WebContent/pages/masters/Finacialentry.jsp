<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" import="app.*"%>
<%@ page import="harrier.income.com.report.*" %>
<%@ page import = "java.io.PrintWriter" %>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>

<jsp:useBean id="PatBean1" scope="session" class="app.PatForm1"/>
<html:html>
<html:errors />
<head>
		 	<html:base/>
			<title></title>
			<link href="StyleSheet.css" rel="stylesheet" type="text/css">
			<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
			<script language="javascript" src="box_ex.js"></script>
			<script language="javascript">
				var c2 = new CodeThatCalendar(caldef2);
			</script>
			<!-- <script type="text/javascript" src="./sorttable.js"></script>
				<style type="text/css">	
				/* Sortable tables */
				table.sortable a.sortheader {
		    		background-color:#eee;
		   	 		font-size: 13px; 
		    		font-family: Verdana;
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
		<script type='text/javascript' src='/Stockpile/dwr/interface/MoveTable.js'></script>
		<script type='text/javascript' src='/Stockpile/dwr/engine.js'></script>
		<script type='text/javascript' src='/Stockpile/dwr/util.js'></script>	

		<style type="text/css">

			/* table styles*/
			.sorted td, th {border:0;padding:2px 6px;margin:0;border-right:1px solid #336;
				border-bottom:1px solid #336;background-color: #dddddd;color: #black;
				font-size: 10px;padding-left: 2px;}
			td[axis='number'], td[axis='date'] {text-align:right;}
			th {background-color:#cacaca;padding:2px 20px;color: blue;font-size: 12px;
				vertical-align: baseline;line-height: 18px;}
			tfoot td {border-top:0px solid #003;}
			thead th {border-bottom:1px solid #003;}
			.sortedminus {background-color:#ecc;}
			.sortedplus {background-color:#cec;}


		</style>	
</head>

<body onload="initialize()">

<html:form action="/Pat1">

<table width="100%"  border="0" cellpadding="2" cellspacing="3" bgcolor="#D8D8D8">
  <tr>
    <td align="center"><strong>FINANCIAL RESULT ENTRIES</strong></td>
  </tr>
  <tr>
    <td align="center" bgcolor="#446CA4" height="1"><img src="spacer.gif" height="1" width="1"></td>
  </tr>
   <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td nowrap>Select Company:
      <html:select property="selectExchange" size="3" multiple="true">
			<html:optionsCollection property="exchangeCollection" name="PatBean1"/>
	  </html:select>
	  </td>
	  
	  <td nowrap>&nbsp;&nbsp;&nbsp;&nbsp;Number of Months: </td>
        <td><html:text property="number" size="10"></html:text></td>
		
		<td nowrap>&nbsp;&nbsp;&nbsp;XDate:</td>
        <td><html:text property="xdate" size="10" onblur="checkdate(this)" /></td>
		
			<td nowrap>&nbsp;&nbsp;&nbsp;Date to enter value: </td>
       <td><html:text property="to"  size="10" onblur="checkdate(this)" /></td>
	
  </tr>
  </table>
  </td>
  <tr>
    <td align="center" bgcolor="#CBC9C9"><strong>Financial Data </strong></td>
  </tr>
  <tr>
    <td align="center"><table width="61%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="16%" height="37" align="right">PAT:</td><br>
        <td width="34%"><html:text property="pat"  size="18" tabindex="4" /></td>
		
        <td width="22%" align="right">Net Worth:</td>
        <td width="28%"><html:text property="net"  size="18" tabindex="4" /></td>
      </tr>
      <tr>
        <td height="36" align="right">Dividend:</td>
        <td><html:text property="divided"  size="18" tabindex="4" /></td>
        <td align="right">Interest:</td>
        <td><html:text property="intrest"  size="18" tabindex="4" /></td>
      </tr>
      <tr>
        <td height="36" align="right">Tax:</td>
        <td><html:text property="tax"  size="18" tabindex="4" /></td>
        <td align="right">Paidup Capital:</td>
        <td><html:text property="paidup"  size="18" tabindex="4" /></td>
      </tr>
      <tr>
        <td height="35" align="right">Total Debt: </td>
        <td><html:text property="depth"  size="18" tabindex="4" /></td>
        <td align="right">Sales:</td>
        <td><html:text property="sales"  size="18" tabindex="4" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="" bgcolor="#CBC9C9">&nbsp;</td>
  </tr>
    </table></td>
  </tr>
  <tr>
    <td align="center"><table width="40%"  border="0" cellspacing="0" cellpadding="0">
    <table align = "center">
      <tr align="center">      
        <td width="35%"><input type="submit" name="Submit" value="Submit"></td>
        <td width="30%"><input type="reset" name="Reset" value="Reset"></td>
        <td width="35%"><input type="submit" name="Submit" value="Back..."></td>
      </tr>
    </table></td>
  </tr>
</table>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>

</html:form>
</body>
</html:html>
 
