
<%@page import="com.harrier.initializeation.ConnectInit"%><%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page language="java" import="app.*,java.sql.*" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.lang.*,app.*,harrier.income.com.masters.*,java.util.*" %>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %><%
			LogonForm form = (LogonForm)session.getAttribute("user");
			log.info("form is "+form);
			if(form == null){
				response.sendRedirect("../userlogintemp.jsp");
			} else {
				String fr2=request.getParameter("from");
				String locale=session.getAttribute("locale").toString();
			//	AcessControl asc=new AcessControl();
				AcessControl asc=ConnectInit.getAcessControl();
				asc.setLocale(locale);
			}
%>
<html>
<html:base/>
<link href="./StyleSheet.css" rel="stylesheet" type="text/css">
<jsp:useBean id="stockmasterbondscommodity" scope="session" class="harrier.income.com.masters.StockMasterBondsCommodities"/> 

<html:form  action="/bondsAction">
<html:hidden property="commit_button" name="stockmasterbondscommodity"/>
<script language="javascript" src="./codethatcalendarstd.js"></script>
	<script language="javascript" src="box_ex.js"></script>
	<script language="javascript">
		var c2 = new CodeThatCalendar(caldef2);
	</script>
<body>
  <%  boolean bodydisp=(stockmasterbondscommodity.bodycheck(request)); %>
  
  <logic:equal value="commit" property="commit_button" name="stockmasterbondscommodity">
	<logic:equal value="0" property="success_flag" name="stockmasterbondscommodity">
		<font size="2" color="green"> <b><bean:message key="stockmasterbonds.success"/></b></font>
	</logic:equal>
	<logic:equal value="1" property="success_flag" name="stockmasterbondscommodity">
			<font size="2" color="red"><b><bean:message key="stockmasterbonds.error"/></b></font>
	</logic:equal>
</logic:equal>
        <table border="1" width="100%" class="gridStyle" cellspacing="0" cellpadding="0">
        <tr><td width="75%"></td></tr>        
          <tr>
            <td width="64%">
              <table border="0" width="100%" class="gridStyle" cellspacing="0" cellpadding="2">
                <tr>
                  <td width="25%" class="subHeader" nowrap="nowrap"><b><bean:message key="stockmaster.BondsFixedIncome" /></b></td> 
                  <td width="25%" class="subHeader"><b>&nbsp;</b></td> 
                  <td width="25%" class="subHeader"><b>&nbsp;</b></td> 
                  <td width="25%" class="subHeader"><b>&nbsp;</b></td>                                                 
                </tr>  
      <%
	boolean erdisp=(stockmasterbondscommodity.getErrors(request,response));
	if(erdisp){
%> 
      <html:errors/>
      <% } %>   
       <logic:notEmpty property="s_stockID" name="stockmasterbondscommodity">    
          <tr>
                  <td width="25%" class="tab" nowrap="nowrap"><b><bean:message key="stockmaster.stockID" /></b></td> 
                  <td width="25%" class="tab"><html:text property="s_stockID" size="10" name="stockmasterbondscommodity"  /></td> 
                  <td width="25%" class="tab"><b>&nbsp;</b></td> 
                  <td width="25%" class="tab"><b>&nbsp;</b></td>                                                 
                </tr>      	     
     	 </logic:notEmpty>
     	  <tr>
                  <td width="25%" align="left" class="tab" ><bean:message key="stockmaster.stockName"/><font color="red">*</font></td>                 
                 
                  <td width="25%">
                  <logic:present name="stockmasterbondscommodity">
	            		<logic:empty property="s_stockName"  name="stockmasterbondscommodity">
		            			<html:text property="s_stockName" size="40"  value=""  />
		            	</logic:empty>
		              	<logic:notEmpty  property="s_stockName" name="stockmasterbondscommodity">
		            			<html:text property="s_stockName" size="40" name="stockmasterbondscommodity"  />
		            	</logic:notEmpty>
	            </logic:present>	</td>                                                  
                                  
              <td width="25%" align="left" class="tab"><bean:message key="stockmaster.listingDate"/></td>
               <td width="25%"><logic:present name="stockmasterbondscommodity">
	            		<logic:empty property="d_listingDate" name="stockmasterbondscommodity">
		            			<html:text readonly="true" property="d_listingDate" size="20"  value=""  />
		            	</logic:empty>
		              	<logic:notEmpty  property="d_listingDate" name="stockmasterbondscommodity">
		            			<html:text readonly="true" property="d_listingDate" size="20" name="stockmasterbondscommodity"  />
		            	</logic:notEmpty>
	            </logic:present><input onclick="c2.popup('d_listingDate');"  type="button" value="..."/></td>
              
                </tr>
                
			    <tr>
                  <td width="25%" align="left" class="tab"><bean:message key="stockmaster.stockType"/></td>
                  <td width="25%">
	            
	            <html:text property="s_stockType" size="40" name="stockmasterbondscommodity" value="FixedIncome/Bonds" disabled="true" />
                <td width="20%" align="left" class="tab"><bean:message key="stockmaster.iwf"/><font color="red">*</font></td>
                 <td width="25%"><logic:present name="stockmasterbondscommodity">
            		<logic:empty  property="d_iwf" name="stockmasterbondscommodity">
	            			<html:text property="d_iwf" size="20" maxlength="6" value=""  />
	            	</logic:empty>
	              <logic:notEmpty property="d_iwf" name="stockmasterbondscommodity">
	            			<html:text property="d_iwf" size="20" maxlength="6" name="stockmasterbondscommodity"  />
	            	</logic:notEmpty>
            	</logic:present> </td>                
                </tr>
                
                <tr>
                 <td width="25%" align="left" class="tab"><bean:message key="stockmaster.company"/><font color="red">*</font></td>
                 <td width="20%" nowrap="nowrap"> 
	            	<html:select property="s_companyName" size="1" onfocus="populate(event);" onkeydown="setSelection(event)" onkeypress="javascript:return false">
              			<html:optionsCollection name="stockmasterbondscommodity" property="companyListCollection" />
            		</html:select>	
                  <a href="./AddNewCompany.jsp?from=stkmaster"><bean:message key="stockmaster.new"/></a>
                  &nbsp;&nbsp;
                 </td>
                                  
                   <td width="25%" align="left" class="tab"><bean:message key="stockmaster.issuedShare"/><font color="red">*</font></td>
                    <td width="25%"><logic:present name="stockmasterbondscommodity">
                    <logic:empty property="s_stockID" name="stockmasterbondscommodity">
	            		<logic:empty property="f_issuedShares" name="stockmasterbondscommodity">
	            			<html:text property="f_issuedShares" size="20" value=""  />
	            		</logic:empty>
	              		<logic:notEmpty property="f_issuedShares" name="stockmasterbondscommodity">
	            			<html:text property="f_issuedShares" size="20" name="stockmasterbondscommodity"  />
	            		</logic:notEmpty>
	            	</logic:empty>
	              	<logic:notEmpty property="s_stockID" name="stockmasterbondscommodity">
	            			<logic:empty property="f_issuedShares" name="stockmasterbondscommodity">
	            				<html:text readonly="true" property="f_issuedShares" size="20" value=""  />
	            			</logic:empty>
	              			<logic:notEmpty property="f_issuedShares" name="stockmasterbondscommodity">
	            				<html:text readonly="true" property="f_issuedShares" size="20" name="stockmasterbondscommodity"  />
	            			</logic:notEmpty>
	            	</logic:notEmpty>
            	</logic:present></td>                  
                </tr>
                <tr>
                  <td width="25%" align="left" class="tab"><bean:message key="stockmaster.stockExchange"/><font color="red">*</font></td>
                 
                 <td width="25%"> 
	            	<html:select property="s_stockExchange" size="1" onfocus="populate(event);" onkeydown="setSelection(event)" onkeypress="javascript:return false">
              			<html:optionsCollection name="stockmasterbondscommodity" property="stockExcListCollection" />
            		</html:select>      
                  <input type="hidden" name="s_stockExchange_null" value="<%=stockmasterbondscommodity.getS_stockExchange() %>" />
                  <html:hidden property="b_accpt"/>
                 
                 <a href="./AddStockExchange.jsp?from=stkmaster" onclick="return test1()"><bean:message key="stockmaster.new"/></a>                                                  
                 </td>
                                
                   <td width="25%" align="left" class="tab"><bean:message key="stockmaster.country"/><font color="red">*</font></td>
                   	<td width="25%"> 
	            		<html:select property="s_countryName" size="1" onfocus="populate(event);" onkeydown="setSelection(event)" onkeypress="javascript:return false">
              				<html:optionsCollection name="stockmasterbondscommodity" property="countryListCollection" />
            			</html:select> 
					</td>                   
              </tr>
                <tr>
                  <td width="25%" align="left" class="tab"><bean:message key="stockmaster.faceValue"/><font color="red">*</font></td>
			     <td width="25%"><logic:present name="stockmasterbondscommodity">
                    <logic:empty property="s_stockID" name="stockmasterbondscommodity">
	            		<logic:empty property="f_faceValue" name="stockmasterbondscommodity">
	            			<html:text property="f_faceValue" size="20" value=""  />
	            		</logic:empty>
	              		<logic:notEmpty property="f_faceValue" name="stockmasterbondscommodity">
	            			<html:text property="f_faceValue" size="20" name="stockmasterbondscommodity"  />
	            		</logic:notEmpty>
	            	</logic:empty>
	              	<logic:notEmpty property="s_stockID" name="stockmasterbondscommodity">
	            			<logic:empty property="f_faceValue" name="stockmasterbondscommodity">
	            				<html:text readonly="true" property="f_faceValue" size="20" value=""  />
	            			</logic:empty>
	              			<logic:notEmpty property="f_faceValue" name="stockmasterbondscommodity">
	            				<html:text readonly="true" property="f_faceValue" size="20" name="stockmasterbondscommodity"  />
	            			</logic:notEmpty>
	            	</logic:notEmpty>
            	</logic:present></td>   
                   
                   <td width="25%" align="left" class="tab"><bean:message key="stockmaster.ratingCode"/></td>
                  <td width="25%"> 
	            		<html:select property="s_ratingCode" size="1" >
              				<html:optionsCollection name="stockmasterbondscommodity" property="ratingCodeListCollection" />
            			</html:select> 
                </td>
                </tr>

 <tr>
                   <td width="25%" align="left" class="tab"><bean:message key="stockmaster.paidValue"/><font color="red">*</font></td>
                    <td width="25%"><logic:empty property="d_paidValue" name="stockmasterbondscommodity">
	            				<html:text property="d_paidValue" size="20" value=""  />
	            			</logic:empty>
	              			<logic:notEmpty property="d_paidValue" name="stockmasterbondscommodity">
	            				<html:text property="d_paidValue" size="20" name="stockmasterbondscommodity"  />
	            			</logic:notEmpty></td>
                    <td width="25%" align="left" class="tab"><bean:message key="stockmaster.currency"/><font color="red">*</font></td>                  
                      <td width="25%"> 
	            		<html:select property="s_stockCurrency" size="1" onfocus="populate(event);" onkeydown="setSelection(event)" onkeypress="javascript:return false">
              				<html:optionsCollection name="stockmasterbondscommodity" property="currencyListCollection" />
            			</html:select> </td>
                
                  </tr>				
                  <td width="25%" align="left" class="tab"><bean:message key="stockmaster.alertPercentage"/><font color="red">*</font></td>                 
                   <td width="25%"><logic:empty property="f_alertPercent" name="stockmasterbondscommodity">
	            				<html:text property="f_alertPercent" size="20" value="10"  />&nbsp;%
	            			</logic:empty>
	              			<logic:notEmpty property="f_alertPercent" name="stockmasterbondscommodity">
	            				<html:text property="f_alertPercent" size="20" name="stockmasterbondscommodity"  />&nbsp;%
	            			</logic:notEmpty></td>
                  
                   <td width="25%" align="left" class="tab"><bean:message key="stockmaster.rejection"/><font color="red">*</font></td>
                  
          		 <td width="25%">
          		 		<logic:empty property="f_rejectionPercent" name="stockmasterbondscommodity">
	            				<html:text property="f_rejectionPercent" size="20" value="20"  />&nbsp;%
	            			</logic:empty>
	              			<logic:notEmpty property="f_rejectionPercent" name="stockmasterbondscommodity">
	            				<html:text property="f_rejectionPercent" size="20" name="stockmasterbondscommodity"  />&nbsp;%
	            			</logic:notEmpty></td>
                </tr>
               
 				<tr>
                  <td width="25%" align="left" class="tab"><bean:message key="stockmaster.withholdingTax"/><br> <bean:message key="stockmaster.applicable"/></td>
                  <td width="25%"> 
	            	<html:select property="b_withHoldingTaxApplicable" size="1"  onchange="chagelock()">
              			<html:optionsCollection name="stockmasterbondscommodity" property="withHoldingTaxApplicableCollection" />
            		</html:select> </td>   
				 
                   <td width="25%" align="left" class="tab"><bean:message key="stockmaster.withholdingTax"/></td>
                  <td width="25%"><logic:empty property="f_withholdingTaxPercent" name="stockmasterbondscommodity">
	            		<html:text property="f_withholdingTaxPercent" size="20" value=""  disabled="true"  />
	            	</logic:empty>
	              	<logic:notEmpty property="f_withholdingTaxPercent" name="stockmasterbondscommodity">
	            		<html:text property="f_withholdingTaxPercent" size="20" name="stockmasterbondscommodity" disabled="true" />&nbsp;%
	            	</logic:notEmpty></td>
                 </tr>
               
					<tr>
                   <td width="25%" align="left" class="tab"><bean:message key="stockmaster.mktLot"/><font color="red">*</font></td>
                   <td width="25%"><logic:empty property="s_marketLot" name="stockmasterbondscommodity">
	            		<html:text property="s_marketLot" size="20" value=""  />
	            	</logic:empty>
	              	<logic:notEmpty property="s_marketLot" name="stockmasterbondscommodity">
	            		<html:text property="s_marketLot" size="20" name="stockmasterbondscommodity"  />
	            	</logic:notEmpty></td>
                         	                    
  <td width="25%" align="left" class="tab"> <bean:message key="stockmaster.ispriceforLot"/></td>
                <td width="25%"> 
	            	<html:select property="b_isPriceForLot" size="1" >
              			<html:optionsCollection name="stockmasterbondscommodity" property="is_price_for_lotCollection" />
            		</html:select> </td>         
          </tr>
           
           <tr>
            <td width="25%" align="left" class="tab"><bean:message key="stockmaster.dirtyprice"/><font color="red">*</font></td>                 
                   <td width="25%"><logic:empty property="dirty_price" name="stockmasterbondscommodity">
	            				<html:text property="dirty_price" size="20" value=""  />
	            			</logic:empty>
	              			<logic:notEmpty property="dirty_price" name="stockmasterbondscommodity">
	            				<html:text property="dirty_price" size="20" name="stockmasterbondscommodity"  />
	            			</logic:notEmpty></td>
                  
                   <td width="25%" align="left" class="tab"><bean:message key="stockmaster.cleanprice"/><font color="red">*</font></td>
                  
          		 <td width="25%">
          		 		<logic:empty property="clean_price" name="stockmasterbondscommodity">
	            				<html:text property="clean_price" size="20" value=""  />
	            			</logic:empty>
	              			<logic:notEmpty property="clean_price" name="stockmasterbondscommodity">
	            				<html:text property="clean_price" size="20" name="stockmasterbondscommodity"  />
	            			</logic:notEmpty></td>
           
           </tr>
           
           
           
            <tr>
             <td width="25%" align="left" class="tab"><bean:message key="stockmaster.start"/></td>
                 <td width="25%"><logic:present name="stockmasterbondscommodity">
	            		<logic:empty property="start_date" name="stockmasterbondscommodity">
		            			<html:text readonly="true" property="start_date" size="20"  value=""  />
		            	</logic:empty>
		              	<logic:notEmpty  property="start_date" name="stockmasterbondscommodity">
		            			<html:text readonly="true" property="start_date" size="20" name="stockmasterbondscommodity"  />
		            	</logic:notEmpty>
	            </logic:present><input onclick="c2.popup('start_date');"  type="button" value="..."/></td>
                 <td width="25%" class="tab"> <bean:message key="stockmaster.maturity"/></td>
                 <td width="25%"><logic:present name="stockmasterbondscommodity">
	            		<logic:empty property="maturity_date" name="stockmasterbondscommodity">
		            			<html:text readonly="true" property="maturity_date" size="20"  value=""  />
		            	</logic:empty>
		              	<logic:notEmpty  property="maturity_date" name="stockmasterbondscommodity">
		            			<html:text readonly="true" property="maturity_date" size="20" name="stockmasterbondscommodity"  />
		            	</logic:notEmpty>
	            </logic:present><input onclick="c2.popup('maturity_date');"  type="button" value="..."/></td>                  
            </tr>  
      <tr>
                  <td width="25%" align="left" class="tab"><bean:message key="stockmaster.coupanp"/><font color="red">*</font></td>
                   <td width="25%"><logic:empty property="coupon_percentage" name="stockmasterbondscommodity">
	            		<html:text property="coupon_percentage" size="20" value=""  />
	            	</logic:empty>
	              	<logic:notEmpty property="coupon_percentage" name="stockmasterbondscommodity">
	            		<html:text property="coupon_percentage" size="20" name="stockmasterbondscommodity"  />
	            	</logic:notEmpty></td>  
	            	<td width="25%" align="left" class="tab"><bean:message key="stockmaster.coupanpe"/></td>
                   <td width="25%"><logic:empty property="coupon_period" name="stockmasterbondscommodity">
	            		<html:text property="coupon_period" size="20" value=""  />
	            	</logic:empty>
	              	<logic:notEmpty property="coupon_period" name="stockmasterbondscommodity">
	            		<html:text property="coupon_period" size="20" name="stockmasterbondscommodity"  />
	            	</logic:notEmpty></td>                
                </tr>
         <tr>
                  <td width="25%" align="left" class="tab"><bean:message key="stockmaster.coupanpay"/></td>
                   <td width="25%"><logic:empty property="coupon_payment_dates" name="stockmasterbondscommodity">
	            		<html:text property="coupon_payment_dates" size="20" value=""  />
	            	</logic:empty>
	              	<logic:notEmpty property="coupon_payment_dates" name="stockmasterbondscommodity">
	            		<html:text property="coupon_payment_dates" size="20" name="stockmasterbondscommodity"  />
	            	</logic:notEmpty></td>  
	            	<td width="25%" align="left" class="tab"><bean:message key="stockmaster.intrest"/></td>
                   <td width="25%"><logic:empty property="interest_basis_month" name="stockmasterbondscommodity">
	            		<html:text property="interest_basis_month" size="5" value=""  />
	            	</logic:empty>
	              	<logic:notEmpty property="interest_basis_month" name="stockmasterbondscommodity">
	            		<html:text property="interest_basis_month" size="5" name="stockmasterbondscommodity"  />
	            	</logic:notEmpty>/
	            	<logic:empty property="interest_basis_year" name="stockmasterbondscommodity">
	            		<html:text property="interest_basis_year" size="5" value=""  />
	            	</logic:empty>
	              	<logic:notEmpty property="interest_basis_year" name="stockmasterbondscommodity">
	            		<html:text property="interest_basis_year" size="5" name="stockmasterbondscommodity"  />
	            	</logic:notEmpty>
	            	</td>                    
                </tr>  
                <tr>
                  <td width="25%" align="left" class="tab"><bean:message key="stockmaster.accured"/><font color="red">*</font></td>
                   <td width="25%"><logic:empty property="accrued_interest" name="stockmasterbondscommodity">
	            		<html:text property="accrued_interest" size="20" value=""  />
	            	</logic:empty>
	              	<logic:notEmpty property="accrued_interest" name="stockmasterbondscommodity">
	            		<html:text property="accrued_interest" size="20" name="stockmasterbondscommodity"  />
	            	</logic:notEmpty></td>  
	            	<td width="25%" align="left" class="tab"><bean:message key="stockmaster.description"/></td>
                   <td width="25%"><logic:empty property="description" name="stockmasterbondscommodity">
	            		<html:text property="description" size="20" value=""  />
	            	</logic:empty>
	              	<logic:notEmpty property="description" name="stockmasterbondscommodity">
	            		<html:text property="description" size="20" name="stockmasterbondscommodity"  />
	            	</logic:notEmpty></td>                
                </tr>     
              

<tr> 
          
 </table>
 <table border="0" width="101%" class="gridStyle" cellspacing="0" cellpadding="4">
          
		 <tr>
           <td width="146%" valign="top" colspan="2">
           <table border="0" width="85%" class="gridStyle" cellspacing="0" cellpadding="0" bordercolordark="#808080" style="border-collapse: collapse" bordercolor="#111111">
           <tr>
           <td width="100%" class="tab"><b>&nbsp;<bean:message key="stockmaster.codes"/></b>
           <table border="0" width="100%" cellspacing="0" cellpadding="0" height="5%">
			<tr>
            <td width="50%" align="left" height="9">
             <div align="left">
             <table border="0" cellpadding="0" class="gridStyle" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber1">

			<tr>
			 <td width="8%" class="tab"> 			
			 &nbsp;&nbsp;<bean:message key="stockmaster.sedol"/>
			 </td>            
			  <td width="8%"><logic:empty property="b_sdl" name="stockmasterbondscommodity">
	            		<html:text property="b_sdl" size="12" value=""  />
	            	</logic:empty>
	              	<logic:notEmpty property="b_sdl" name="stockmasterbondscommodity">
	            		<html:text property="b_sdl" size="12" name="stockmasterbondscommodity"  />
	            	</logic:notEmpty>
			 </td>	 
			 <td width="5%" class="tab"> 
			 &nbsp;&nbsp;<bean:message key="stockmaster.isin"/>			   
			 <td width="10%"><logic:empty property="b_isn" name="stockmasterbondscommodity">
	            		<html:text property="b_isn" size="12" value=""  />
	            	</logic:empty>
	              	<logic:notEmpty property="b_isn" name="stockmasterbondscommodity">
	            		<html:text property="b_isn" size="12" name="stockmasterbondscommodity"  />
	            	</logic:notEmpty>
			 </td>	
 			 <td width="8%" class="tab"> 
			 &nbsp;&nbsp;<bean:message key="stockmaster.ric"/>
			  <td width="10%"><logic:empty property="b_ric" name="stockmasterbondscommodity">
	            		<html:text property="b_ric" size="12" value=""  />
	            	</logic:empty>
	              	<logic:notEmpty property="b_ric" name="stockmasterbondscommodity">
	            		<html:text property="b_ric" size="12" name="stockmasterbondscommodity"  />
	            	</logic:notEmpty>
			 </td>	
			 <td width="8%" class="tab"> 
			 &nbsp;&nbsp;<bean:message key="stockmaster.crisil"/>			   
			<td width="10%"><logic:empty property="b_crisil" name="stockmasterbondscommodity">
	            		<html:text property="b_crisil" size="12" value=""  />
	            	</logic:empty>
	              	<logic:notEmpty property="b_crisil" name="stockmasterbondscommodity">
	            		<html:text property="b_crisil" size="12" name="stockmasterbondscommodity"  />
	            	</logic:notEmpty>
			 </td>	
 			</tr>
			 <tr>
			<td width="8%" class="tab"> 
			 &nbsp;&nbsp;<bean:message key="stockmaster.cusip"/>
			<td width="10%"><logic:empty property="b_csp" name="stockmasterbondscommodity">
	            		<html:text property="b_csp" size="12" value=""  />
	            	</logic:empty>
	              	<logic:notEmpty property="b_csp" name="stockmasterbondscommodity">
	            		<html:text property="b_csp" size="12" name="stockmasterbondscommodity"  />
	            	</logic:notEmpty>
			 </td>
			<td width="15%" class="tab"> 
			 &nbsp;&nbsp;<bean:message key="stockmaster.exchangeCode"/>
			<td width="10%"><logic:empty property="b_exc_code" name="stockmasterbondscommodity">
	            		<html:text property="b_exc_code" size="12" value=""  />
	            	</logic:empty>
	              	<logic:notEmpty property="b_exc_code" name="stockmasterbondscommodity">
	            		<html:text property="b_exc_code" size="12" name="stockmasterbondscommodity"  />
	            	</logic:notEmpty>
			 </td>
 			 <td width="8%" class="tab"> 
			 &nbsp;&nbsp;<bean:message key="stockmaster.ticker"/>
			 <td width="10%"><logic:empty property="b_tkr" name="stockmasterbondscommodity">
	            		<html:text property="b_tkr" size="12" value=""  />
	            	</logic:empty>
	              	<logic:notEmpty property="b_tkr" name="stockmasterbondscommodity">
	            		<html:text property="b_tkr" size="12" name="stockmasterbondscommodity"  />
	            	</logic:notEmpty>
			 </td>
			 </td>                         
                 
             </tr>            
             </table>
             </div>
             </td>
             </tr>                                
           </table>
           </td>
           </tr>
           </table>
           </td>
          </tr>
               
          <tr>
            <td vAlign="top" width="97%" colSpan="2" class="subHeader">
              <p align="center">&nbsp;</p>
            </td>
            <td vAlign="top" width="3%">&nbsp;</td>
          </tr>		
			</tr>
			
			</table> 			 
          <tr>           
            <td width="100%" valign="top" colspan="3">
              <p align="center">  
              <% 
              		String stockid=stockmasterbondscommodity.getS_stockID();
              		if(stockid!=null){
              		String url="../CorporateDiary.jsp?ref_flag=2&s_stock="+stockid;
              %> 
                <input type="hidden" name="varify">
       		<!--<input type="hidden" name="corporateAction"> 
            <html:submit value="View Corporate Actions" property="b1" /> -->
             <a href="<%= url %>"><bean:message key="stockmaster.viewCorporateAction"/></a>&nbsp;&nbsp;&nbsp;
             <input type="hidden" name="operation">
             <html:submit  property="b1" onclick="return Utestcheck()" ><bean:message key="defineIndex30"/></html:submit>
             &nbsp;&nbsp;&nbsp;&nbsp;
              <% }else{  %>           
           		<html:submit property="b1" onclick="return Itestcheck()" ><bean:message key="defineIndex30"/></html:submit>&nbsp;&nbsp;&nbsp;&nbsp;
           <% } %>            
             <html:reset  property="B3"><bean:message key="indexUpdate.reset"/></html:reset>&nbsp;&nbsp;&nbsp;      
          </tr>      	
		<%=(stockmasterbondscommodity.getStockBelongsTo(request)).toString()%>        
      </td>		 
    </tr>        
</tbody>
</table>
 
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="28%" valign="top">
      <p></p>
    </td>
    <td width="72%" valign="top">
      <p>
    </td>
  </tr>      
</table>  
</html:form>  
<script language="JavaScript">
		
var keyTime, keyStr = '', allOpts, lastElement;
		var agt = navigator.userAgent.toLowerCase();
		var is_gecko = (agt.indexOf("gecko") != -1);
		function populate(srcEvent)
		{
		 // document.forms[0].selectICName.value="value0";
		  var element = (srcEvent)? ((srcEvent.target)? srcEvent.target : srcEvent.srcElement) : window.event.srcElement;
		  if(lastElement != element)
		  {
		    allOpts = new Array();
		    for(var i = 0; i < element.options.length; i++)
		      allOpts[i] = element.options[i].text.toLowerCase();
		    lastElement = element;
		  }
		}
		function setSelection(srcEvent)
		{
		  var myEvent = (srcEvent)? srcEvent : window.event;
		  var element = (myEvent.target)? myEvent.target : myEvent.srcElement;
		  var currentKey = unescape('%' + myEvent.keyCode.toString(16)).toLowerCase();
		  var idx, currentSIdx = element.selectedIndex, useOld = false;
		  var newTime = new Date().getTime();
		  if(keyTime != null && newTime - keyTime < 500) 
		  {
		    keyStr += currentKey;
		    idx = findIdx();
		    if(idx == -1) idx = currentSIdx; 
		  }
		  else 
		  {
		    keyStr = currentKey;
		    idx = currentSIdx + 1;
		    if(idx >= allOpts.length || allOpts[idx].length == 0 || allOpts[idx].charAt(0) != keyStr)
		      idx = findIdx();
		  }
		  if(idx >= 0) 
		  {
		    element.options[currentSIdx].selected = false;
		    var pattern = new RegExp('^' + keyStr.charAt(0) + '+$', "i"); 
		    if(is_gecko && pattern.test(keyStr) && idx > 0) element.options[idx-1].selected = true;
		    else element.options[idx].selected = true;
		  }
		  keyTime = newTime;
		}
			function findIdx()
			{
			    var len = keyStr.length;
			  for(var i = 0; i < allOpts.length; i++)
			    if(allOpts[i].length >= len && allOpts[i].substring(0, len) == keyStr)
			      return i;
			  return -1;
			}

	
function Utestcheck()
{
  	var conVal=confirm('Are You Sure to Update The Stock ?');
						if(conVal == true){
							
							return true;
						}
						else{
							return false;
						}
  	
  	return true;
  	//if(confirm('Are You Sure to Update The Stock ?')){
  //	}else{
  		//top.frmMain.location.reload();
  		//top.treeFrame.location.reload();
 		//alert("after reloading same page");	
  	//}  	  	
}
function Itestcheck()
{
var msg='';
  	var conVal=confirm('Are You Sure to Insert The Values For Stock ?');
				if(conVal == true){
						 if(document.forms[0].s_companyName.value == "")
						 {
						  msg=msg+'Please Enter Company Name\n';
						 }
						 
						 if(document.forms[0].d_iwf.value == "")
						 {  
						  msg=msg+'Please Enter IWF value\n';
						 }
						 
						 if(document.forms[0].f_issuedShares.value == "")
						 {  
						  msg=msg+'Please Enter Issued Shares\n';
						 }
						 
						 if(document.forms[0].s_stockName.value == "")
						 {  
						   msg=msg+'Please Enter Stock Name\n';
						 }
						 if(document.forms[0].f_faceValue.value == "")
						 {  
							msg=msg+'Please Enter Face Value\n';
						 }
						 if(document.forms[0].f_alertPercent.value == "")
						 {  
						   msg=msg+'Please Enter Alert Percentage\n';
						 }
						 if(document.forms[0].f_rejectionPercent.value == "")
						 {  
							msg-msg+'Please Enter Rejection Percentage\n';
						 }
						 if(document.forms[0].s_marketLot.value == "")
						 {  
						  msg=msg+'Please Enter Market Lot\n';
						 }
						 if(document.forms[0].b_isPriceForLot.value == "")
						 {  
						  msg=msg+'Please Specify Price for Lot\n';
						 }
						  if(document.forms[0].dirty_price.value == "")
						 {  
						  msg=msg+'Please Specify Dirty price\n';
						 }
						  if(document.forms[0].clean_price.value == "")
						 {  
						  msg=msg+'Please Specify Clean price\n';
						 }
						 if(document.forms[0].coupon_percentage.value == "")
						 {  
						  msg=msg+'Please Specify Coupan percentage\n';
						 }
						 if(document.forms[0].coupon_period.value == "")
						 {  
						  msg=msg+'Please Specify Coupan period\n';
						 }
						 if(document.forms[0].accrued_interest.value == "")
						 {  
						  msg=msg+'Please Specify Accured Interest\n';
						 }
						 
						 
						 
						 if(msg=="") {
						 		document.forms[0].commit_button.value="commit";
								return true;
						}else{
							alert(msg);
							return false;
						}
				}else{
							return false;
				}
}

function test1()
 { 
 	document.forms[0].operation.value="New";
 	return true;
 }
 function testcAction()
 { 
 	//alert("In corporate Action script");
 	document.forms[0].corporateAction.value="View Corporate Actions";
 	return true;
 }
 function testreloadpage()
 { 
 	if(document.forms[0].frompage.value!=null)
 	{
 		top.frmMain.location.reload(); 		
 	} 	
 }
 
 function treereload()
 {
 		//alert("in tree load");
		top.treeFrame.location.reload();
		//alert("after tree load");			
 }	
 function chagelock(){
 	var wht_val=document.forms[0].b_withHoldingTaxApplicable.value;
 	if(wht_val=='n'){
	 	document.forms[0].f_withholdingTaxPercent.disabled=true;
	}
	if(wht_val=='y'){
		document.forms[0].f_withholdingTaxPercent.disabled=false;
	}
 }
 
</script>

</body>
</html>