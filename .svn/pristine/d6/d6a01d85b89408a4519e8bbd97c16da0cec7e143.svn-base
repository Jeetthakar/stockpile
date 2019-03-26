<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page import="app.*,harrier.income.com.masters.*,java.util.*"%><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%		/*	LogonForm form = (LogonForm)session.getAttribute("user");
			if(form == null){
				response.sendRedirect("../userlogintemp.jsp");
			}	*/
				String fr2=request.getParameter("from");
%>
<html>
<html:base/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 6.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<link rel="stylesheet" type="text/css" href="./StyleSheet.css"  title="Default" />   
<title>SM</title>
</head>
<SCRIPT LANGUAGE="JavaScript" SRC="../Script/autocomplete.js"></SCRIPT>
<body>
<jsp:useBean id="stockMasterForm" scope="session" class="harrier.income.com.masters.StockMasterForm"/>
<logic:equal value="1" scope="request" parameter="ref_flag">
<%

stockMasterForm.setS_countryName("India");     stockMasterForm.setS_stockCurrency("India : Rupees"); 
stockMasterForm.setS_stockType("EQ"); %>
</logic:equal>



<jsp:setProperty name="stockMasterForm" property="b1" value="" />

<!-- -------------form starts here---------- -->
<html:form  action="/stockMasterAction1">
<html:errors/>
<table width="103%" class="sample">
  <tr><td width="103%">
   	<table border="0" width="103%" cellspacing="0" cellpadding="0" >
	  <tr>
	    <td width="30%"  colspan="2"><font size="2" face="Arial">
	    	<b><bean:message key="Newissues.head1" /></b></font></td>
	    <td width="33%"  >
	      	&nbsp;</td>
	    <td width="14%"  >
	      	&nbsp;</td>
	    <td width="23%"  >&nbsp;</td>
	  </tr>  
	  <tr>
	    <td width="17%"  height="30"><font face="Arial" size="2">
			<bean:message key="stockmaster.stockExchange"/>
			</font>
		</td>
		<td width="46%" height="30" colspan="2" > 
			<html:select property="s_stockExchange" size="1" onchange="return exctest()">
        		<html:optionsCollection name="stockMasterForm" property="unrecogExcCollection" />
        	</html:select>			  	
		</td>
	    <td width="14%" />	      
	    <td width="23%" >&nbsp;</td>
      </tr> 
	  <tr>
	    <td width="17%"  height="30"><font face="Arial" size="2">
			<bean:message key="stockmaster.stockName"/>
		</font></td>
		<td width="46%" height="30" colspan="2" >
			<input type="text" name="A_s_stockName" onblur="return stktest()" onblur="return stktest()" ONKEYUP="autoComplete(this,this.form.s_stockName,'text',true)">   
			<html:select property="s_stockName" size="5" onchange="return stktest()">
        		<html:optionsCollection name="stockMasterForm" property="unrecogstockCollection" />
        	</html:select>			  	
		</td>
	    <td width="14%" />	      
	    <td width="23%" >&nbsp;</td>
     </tr> 
     <tr>
	  <td width="103%" bgcolor="#BFBFBF" colspan="5" align="center">
	 	 <b><font face="Arial" size="2">
	     <bean:message key="corporate.stkHeading1" />
	  </font></b></td>
	</tr>        
	<tr/><tr/>
	<tr>
    <td width="103%" colspan="3"  valign="top" >
      <table cellSpacing="0" cellPadding="2" width="103%" border="0">
      	<tbody>
      	<tr>
	  		<td width="23%" ><font face="Arial" size="2">
	 			<bean:message key="stockmaster.stockType" />
	 		</font></td>
	  		<td width="28%">
	 	 		<html:text property="s_stockType" readonly="true"/></td>
	  		<td width="24%" ><font face="Arial" size="2">
	  			<bean:message key="Newissues.sc_code" /></font></td>
	  			<%
	  			 String sc_code =   stockMasterForm.getB_exc_code();
	  			  log.info("On JSP SC_CODE "+sc_code);
	  			 %>
	  		<td width="26%">
	  			<html:text property="b_exc_code"  readonly="true"  />   <!--replace property for sc_code-->
	  		</td>
	   </tr>	
	   <tr>
	   	<td width="23%" ><font face="Arial" size="2">
	 		<bean:message key="stockmaster.country" />
	 		</font></td>
	  	<td width="28%">
	 		<html:text property="s_countryName" readonly="true"/></td>
	  	<td width="24%" ><font face="Arial" size="2">
	  		<bean:message key="indcurrwise.currencyName" /></font></td>
	  	<td width="26%">
	  		<html:text property="s_stockCurrency" readonly="true" />   <!--replace property for sc_code-->
	  	</td>
	   	</tr>	       
     </tbody>
     </table></td></tr>   
     <tr/><tr/>
     <tr>
	  <td width="103%" bgcolor="#BFBFBF" colspan="5" align="center">
	 	 <b><font face="Arial" size="2">
	 	 <bean:message key="Newissues.price_head" />
	  </font></b></td>
	 </tr>        
     <tr/>    	
	</table>
<BR><BR>
	<table cellSpacing="0" cellPadding="0" width="103%" bgColor="#ffffff" border="1">
        <tbody>
            <tr>
                <td width="4%" align="center"><font face="Arial" size="2">Sr No.</font></td>
                <td width="6%" align="center"><font face="Arial" size="2">Price Date</font></td>
                <td width="11%" align="center"><font face="Arial" size="2">Day Open </font></td>
                <td width="11%" align="center"><font face="Arial" size="2">Day High</font></td>
                <td width="11%" align="center"><font face="Arial" size="2">Day Low</font></td>
                <td width="11%" align="center"><font face="Arial" size="2">Day Close </font></td>
                <td width="7%" align="center"><font face="Arial" size="2">No.Of Shares</font></td>
                <td width="9%" align="center"><font face="Arial" size="2">No.Of Trades</font></td>
                <td width="9%" align="center"><font face="Arial" size="2">Traded Value</font></td>
            </tr>
            <logic:iterate id="stklist" name="stockMasterForm" property="alllist" >
            <tr>
               <td width="4%" align="center"><font face="Arial" size="2">
            	   <bean:write name="stklist" property="id"/></font></td>
               <td width="6%" align="center"><font size="2" face="Arial">
               	   <bean:write name="stklist" property="action"/></font></td>
               <td width="11%" align="center"><font size="2" face="Arial">
					<logic:notEmpty name="stklist" property="apply">
			   			<bean:write name="stklist" property="apply"/>
			   		</logic:notEmpty>
			   <logic:empty property="apply" name="stklist">
				-
				</logic:empty>
				</font></td>	
				
				<td width="11%" align="center"><font size="2" face="Arial">
				<logic:notEmpty name="stklist" property="amount">
			   <bean:write name="stklist" property="amount"/>
			   </logic:notEmpty>
			   <logic:empty property="amount" name="stklist">
				-
				</logic:empty>
				</font></td>	
						  
				<td width="11%" align="center"><font size="2" face="Arial" >
				<logic:notEmpty name="stklist" property="ratio_shr">
			   <bean:write name="stklist" property="ratio_shr"/>
			   </logic:notEmpty>
			   <logic:empty property="ratio_shr" name="stklist">
				-
				</logic:empty>
				</font></td>	
				
			   <td width="11%" align="center"><font size="2" face="Arial">
			   <logic:notEmpty name="stklist" property="sname">
			   <bean:write name="stklist" property="sname"/>
			   </logic:notEmpty>
			   <logic:empty property="sname" name="stklist">
				-
			   </logic:empty>
			   </font></td>			
						
				<td width="7%" align="center"><font size="2" face="Arial" >				
				<logic:notEmpty property="shr_offer" name="stklist">
				<bean:write name="stklist" property="shr_offer"/>
				</logic:notEmpty>				
				<logic:empty property="shr_offer" name="stklist">
				-
				</logic:empty>
				</font></td>	
				<td width="9%" align="center"><font size="2" face="Arial" >
				<logic:notEmpty name="stklist" property="percent">
			   <bean:write name="stklist" property="percent"/>
			   </logic:notEmpty>
			   <logic:empty property="percent" name="stklist">
				-
				</logic:empty>
				</font></td>	
				
				
				<td width="9%" align="center"><font size="2" face="Arial" >				
				<logic:notEmpty property="no_share" name="stklist">
				<bean:write name="stklist" property="no_share"/>
				</logic:notEmpty>
				<logic:empty property="no_share" name="stklist">
				-
				</logic:empty>
				</font></td>
						
               </tr>
               </logic:iterate>
            </tbody>
          </table>
<BR><BR>
    <tr>
	<td width="103%" align="center">
	<html:submit value="Add" onclick="return addtest()" />
	<html:button property="b1" value="Cancel" onclick="return canceltest()" />
	</td>
	</tr>      	
   </td></tr> 
</table> 
<html:hidden property="d_listingDate" />
<html:hidden property="newissues_but" value="" />
<html:hidden property="newIssue" name="stockMasterForm" />
</html:form>
</body>
<script language="JavaScript">
function exctest()
{
//alert(document.forms[0].s_stockExchange.value);

	document.forms[0].newissues_but.value="Exc";
	document.forms[0].submit();
}
function stktest()
{
	//document.forms[0].A_s_stockName.focus();
	document.forms[0].newissues_but.value="Stock";
	document.forms[0].submit();
}
function canceltest()
{
	document.location.href="/Stockpile/pages/masters/stockmaster2.jsp?ref_flag=1";
}
function addtest()
{
	document.forms[0].newIssue.value="NewIssue";
	document.forms[0].newissues_but.value="AddStk";
	document.forms[0].submit();
}
</script>
</html>