
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
			
	LogonForm form = null;				
	String fr2=null;
	String locale=null;
	AcessControl asc= ConnectInit.getAcessControl();
	
	if(request.isRequestedSessionIdValid()){
		log.info("inside sesssion != null"+session);
		form = (LogonForm)session.getAttribute("user");
		fr2=request.getParameter("from");
	 	locale=session.getAttribute("locale").toString();
//		asc=new AcessControl();
		 
		asc.setLocale(locale);
	}	
	
	if(form==null ||(!request.isRequestedSessionIdValid())){
		log.info("inside sesssion == null");
		response.sendRedirect("userlogintemp.jsp");
	}
%>
<html:html>
<html:errors />

<head>
<html:base/>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Untitled Document</title>
<link href="StyleSheet1.css" rel="stylesheet" type="text/css">
<meta name="Microsoft Theme" content="none"> 
	<script language="javascript" src="../Script/date_script.js"></script>
	<script language="javascript" src="box_ex.js"></script>
	<script language="javascript">
	var c2 = new CodeThatCalendar(caldef2);
	</script> 
<script language="javascript" src="../Script/calculate.js"></script>
  
  <script language="javascript">
 function noCheck(ct)
  {
      this.ct = ct;
	  //alert("value check");
	  if(ct.value == "")
	  {
	    //alert("Blank field !!!");
	     //ct.focus();
	     ct.value = 0;
	     return true;
	  }
	  else {
	         if( ct.value.match(/^\d+$/) || ct.value.match(/^\d+\.+\d+$/))
	          {
	           return true;
	              }else{
	             alert("Enter Numeric value !!");
	             ct.value="";
	             ct.focus();
	              return false;
	     	   }
	       }
	
    } //End noCheck()
  
    var gross_total=0;
    
   function addGross()
   {
      var no=0;
      var no1=0;
      var no2=0;
       
    if(!(document.forms[0].netSales.value == ""))
     no=parseFloat(document.forms[0].netSales.value);
              
    if(!(document.forms[0].otherIncome.value == ""))
    no1=parseFloat(document.forms[0].otherIncome.value);
    
    
    if(!(document.forms[0].nonRecurringIncome.value == ""))
    no2=parseFloat(document.forms[0].nonRecurringIncome.value);
    
    var total = no + no1 + no2 ;
    total = total.toPrecision(5);
    document.forms[0].grossIncome.value = total;
    
    if(total != gross_total)
    {
       gross_total = total ;
       addProfitDep();
     }
   }
  
    var init_profitdep = 0;
    
   function addProfitDep()
   {
      var no1=0;
      var no2=0;
      var no3=0;
      var no4=0;
      var no5=0;
      var no6=0;
      var no7=0;
     // alert("from ADDGROSS !");
   if(!(document.forms[0].grossIncome.value == ""))
    no1 = parseFloat(document.forms[0].grossIncome.value);
    //else
    //document.forms[0].grossIncome.value= 0;
    
    if(!(document.forms[0].incDecStock.value == ""))
    no2 = parseFloat(document.forms[0].incDecStock.value);
    //else
    //document.forms[0].incDecStock.value= 0;
    
    if(!(document.forms[0].rawMaterial.value == ""))
    no3 = parseFloat(document.forms[0].rawMaterial.value);
    //else
    //document.forms[0].rawMaterial.value= 0;
    
    if(!(document.forms[0].empCost.value == ""))
    no4 = parseFloat(document.forms[0].empCost.value);
    //else
    //document.forms[0].empCost.value= 0;
    
    if(!(document.forms[0].otherExpenditure.value == ""))
    no5 = parseFloat(document.forms[0].otherExpenditure.value);
   // else
    //document.forms[0].otherExpenditure.value= 0;
    
    if(!(document.forms[0].nonRecurringExpenditure.value == ""))
    no6 = parseFloat(document.forms[0].nonRecurringExpenditure.value);
    //else
    //document.forms[0].nonRecurringExpenditure.value= 0;
    
    if(!(document.forms[0].interest.value == ""))
    no7 = parseFloat(document.forms[0].interest.value);
    //else
    //document.forms[0].interest.value= 0;
    
   var total_profitdep = no1 - (no2 + no3 + no4 + no5 + no6 + no7 );
    total_profitdep = total_profitdep.toPrecision(5);
    //alert(total_profitdep);
    document.forms[0].profitBeforeDepreciation.value = total_profitdep;
    if(total_profitdep != init_profitdep)
     {
        init_profitdep = total_profitdep ;
         ProfitBefTax();
     }
   
   }
   
   var init_profTax = 0;
   
   function ProfitBefTax()
   {
      var no1=0;
      var no2=0;
     
     if(!(document.forms[0].profitBeforeDepreciation.value == ""))
    no1 = parseFloat(document.forms[0].profitBeforeDepreciation.value);
    //else
    //document.forms[0].profitBeforeDepreciation.value = 0;
    
    if(!(document.forms[0].depriciation.value == ""))
    no2 = parseFloat(document.forms[0].depriciation.value);
    //else
    //document.forms[0].depriciation.value = 0;
    
    var total = no1-no2;
    total = total.toPrecision(5);
    document.forms[0].profitBeforeTax.value = total;
     
     if(total != init_profTax)
     {
       init_profTax = total;
       calc_Pat();
     }
     
    }
      
      
    function calc_Pat()
      {
        var no1 = 0;
        var no2 = 0;
        var no3 = 0;
        var no4 = 0;
        
        if(!(document.forms[0].profitBeforeTax.value == ""))
         no1 = parseFloat(document.forms[0].profitBeforeTax.value);
        // else
          // document.forms[0].profitBeforeTax.value = 0;
           
        if(!(document.forms[0].provisionTax.value == ""))
         no2 = parseFloat(document.forms[0].provisionTax.value);
        // else
         //  document.forms[0].provisionTax.value = 0;
           
           if(!(document.forms[0].fringBefTax.value == ""))
            no3 = parseFloat(document.forms[0].fringBefTax.value);
            // else
            // document.forms[0].fringBefTax.value = 0;
             
           if(!(document.forms[0].defferedTaxExpenses.value == ""))
             no4 = parseFloat(document.forms[0].defferedTaxExpenses.value);
            //  else
             //   document.forms[0].defferedTaxExpenses.value = 0;
                
        var total_pat = no1 - (no2 + no3 + no4);
        total_pat = total_pat.toPrecision(5);
       // alert(total_pat);
        document.forms[0].profitAftTax.value = total_pat;
     
      }
  
   function calc_Bookvalue()
   {
      var no1 = 0;
      var no2 = 0;
      var total_bookvalue = 0; 
      if(!(document.forms[0].netWorth.value == ""))
            no1 = parseFloat(document.forms[0].netWorth.value);
            
       if(!(document.forms[0].numberOfShares.value == ""))
            no2 = parseFloat(document.forms[0].numberOfShares.value);
             
             if(!( no1 == 0 || no2 == 0)) {
             total_bookvalue = (no1/no2);
             total_bookvalue = total_bookvalue.toPrecision(5);
             }
             else
             {
             total_bookvalue = 0;
             }
             //alert(total_bookvalue);
              document.forms[0].bookValue.value = total_bookvalue ;
   }
  </script>
  
   
 </head>
	
<jsp:useBean id="FinanceResBean" scope="session" class="app.FinanceResultForm"/> 

<html:form  action="/FinanceResAction">

<body>
   <%
		boolean bodydisp=(FinanceResBean.bodycheck(request));
      %>
   <logic:equal value="Submit" property="b1" name="FinanceResBean">
	<logic:equal value="0" property="check_flag" name="FinanceResBean">
	<font size="2" color="green"> <b><bean:message key="FinanceResult.success"/></b></font>
	</logic:equal>
	<logic:equal value="1" property="check_flag" name="FinanceResBean">
	<font size="2" color="red"><b><bean:message key="FinanceResult.error"/></b></font>
	</logic:equal>
	</logic:equal>

<table width="100%"  border="0" cellpadding="2" cellspacing="3" bgcolor="#D8D8D8">
  <tr>
    <td align="center"><strong>FINANCIAL RESULT ENTRIES</strong></td>
  </tr>
  <tr>
    <td bgcolor="#446CA4" height="1"><img src="spacer.gif" height="1" width="1"></td>
  </tr>
  <tr>
    <td align="center"><table width="95%"  border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td align="left" class="tab1"><bean:message key="stockmaster.company"/> </td>
        <td align="left">
        <html:select property="s_companyName" size="1" style="width=200" onfocus="populate(event);" onkeydown="setSelection(event)" onkeypress="javascript:return false">
            <html:optionsCollection name="FinanceResBean" property="companyListCollection" />
        </html:select>	
        </td>
        <td align="left" class="tab1"><bean:message key="Pat.Number"/></td>
        <td align="left">
        <html:select property="months" size="1" onfocus="populate(event);" onkeydown="setSelection(event)" onkeypress="javascript:return false">
              <html:optionsCollection name="FinanceResBean" property="monthListCollection" />
         </html:select>	
        </td>
        <td align="left" class="tab1"><bean:message key="stockmaster.currency"/></td>
        <td align="left">
        <html:select property="currency" size="1" onfocus="populate(event);" onkeydown="setSelection(event)" onkeypress="javascript:return false">
              <html:optionsCollection name="FinanceResBean" property="currencyListCollection" />
          </html:select>
         </td>
      </tr>
      <tr>
       <td width="25%" align="left" class="tab1"><bean:message key="stockmaster.start"/></td>
                 <td width="25%">
                 <html:text property="start_date" onblur="checkdate(this)" size="10" />
			     </td>
        
        <td width="25%" align="left" class="tab1"><bean:message key="corporate.Tdate"/></td>
                 <td width="25%">
                 <html:text property="to_date" onblur="checkdate(this)" size="8"/>
			      </td>
	           
        <td align="left" class="tab1"><bean:message key="fin.unit"/></td>
        <td align="left">
        <html:select property="units" size="1" onfocus="populate(event);" onkeydown="setSelection(event)" onkeypress="javascript:return false">
              <html:optionsCollection name="FinanceResBean" property="unitListCollection" />
         </html:select>	
        </td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td align="center" bgcolor="#BFBFBF"><strong>FINANCIAL DATA</strong></td>
  </tr>
   <tr>
    <td bgcolor="#446CA4" height="1"><img src="spacer.gif" height="1" width="1"></td>
  </tr>
   
  <tr>
    <td align="center"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>&nbsp;</td>
        <td valign="top"><table width="350"  border="0" cellspacing="2" cellpadding="2">
            <tr>
              <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                  <tr align="center" bgcolor="#BFBFBF">
                    <td height="20" colspan="2" class="tab1">---Income---</td>
                  </tr>
                  <tr>
                    <td align="left" class="tab1">&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td align="left" class="tab1"><bean:message key="fin.netSales"/><font color="red">*</font></td>
                    <td width="52" >
                    <logic:present name="FinanceResBean">
	            		<logic:empty property="netSales"  name="FinanceResBean">
		            			<html:text property="netSales" style="text-align:right"   value=""  onblur="return noCheck(document.forms[0].netSales)" onchange="addGross()" />
		            	</logic:empty>
		              	<logic:notEmpty  property="netSales" name="FinanceResBean">
		            			<html:text property="netSales" style="text-align:right"  name="FinanceResBean" />
		            	</logic:notEmpty>
	            </logic:present>
                   </td>
                  </tr>
                  <tr>
                    <td align="left" class="tab1"><bean:message key="fin.otherIncome"/><font color="red">*</font></td>
                    <td>
                    <logic:present name="FinanceResBean">
	            		<logic:empty property="otherIncome"  name="FinanceResBean">
		            			<html:text property="otherIncome" style="text-align:right"  value="" onblur="return noCheck(document.forms[0].otherIncome)" onchange="addGross()" />
		            	</logic:empty>
		              	<logic:notEmpty  property="otherIncome" name="FinanceResBean">
		            			<html:text property="otherIncome" style="text-align:right"  name="FinanceResBean"/>
		            	</logic:notEmpty>
	            </logic:present>
                  </td>
                  </tr>
                  <tr>
                    <td align="left" class="tab1"><bean:message key="fin.nonRecurringIncome"/></td>
                    <td>
                    <logic:present name="FinanceResBean">
	            		<logic:empty property="nonRecurringIncome"  name="FinanceResBean">
		            			<html:text property="nonRecurringIncome" style="text-align:right"   value=""  onblur="addGross()" />
		            	</logic:empty>
		              	<logic:notEmpty  property="nonRecurringIncome" name="FinanceResBean">
		            			<html:text property="nonRecurringIncome" style="text-align:right" name="FinanceResBean" />
		            	</logic:notEmpty>
	            </logic:present>
                    </td>
                  </tr>
                  <tr>
                    <td height="20" align="left" class="tab1"><bean:message key="fin.grossIncome"/></td>
                    <td>
                      <logic:present name="FinanceResBean">
	            		<logic:empty property="grossIncome"  name="FinanceResBean">
		            			<html:text property="grossIncome" style="text-align:right"  value="" disabled="true" onblur="addProfitDep()" />
		            	</logic:empty>
		              	<logic:notEmpty  property="grossIncome" name="FinanceResBean">
		            			<html:text property="grossIncome" style="text-align:right" name="FinanceResBean" disabled="true" />
		            	</logic:notEmpty>
	            </logic:present>
                    </td>
                  </tr>
                  <tr>
                    <td align="left" class="tab1">&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
              </table></td>
            </tr>
            <tr>
              <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                  <tr align="center" bgcolor="#BFBFBF">
                    <td height="20" colspan="2" class="tab1">---Expenditure---</td>
                  </tr>
                  <tr>
                    <td align="left" class="tab1">&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td align="left" class="tab1"><bean:message key="fin.incDecStock"/></td>
                    <td width="52">
                    <logic:present name="FinanceResBean">
	            		<logic:empty property="incDecStock"  name="FinanceResBean">
		            			<html:text property="incDecStock" style="text-align:right" value="" onchange="return noCheck(document.forms[0].incDecStock)" onblur="addProfitDep()" />
		            	</logic:empty>
		              	<logic:notEmpty  property="incDecStock" name="FinanceResBean">
		            			<html:text property="incDecStock" style="text-align:right" name="FinanceResBean" />
		            	</logic:notEmpty>
	            </logic:present>
                    </td>
                  </tr>
                  <tr>
                    <td align="left" class="tab1"><bean:message key="fin.rawMaterial"/><font color="red">*</font></td>
                    <td>
                    <logic:present name="FinanceResBean">
	            		<logic:empty property="rawMaterial"  name="FinanceResBean">
		            			<html:text property="rawMaterial" style="text-align:right" value="" onchange="return noCheck(document.forms[0].rawMaterial)" onblur="addProfitDep()" />
		            	</logic:empty>
		              	<logic:notEmpty  property="rawMaterial" name="FinanceResBean">
		            			<html:text property="rawMaterial" style="text-align:right" name="FinanceResBean"  />
		            	</logic:notEmpty>
	            </logic:present>
                    </td>
                  </tr>
                  <tr>
                    <td align="left" class="tab1"><bean:message key="fin.empCost"/><font color="red">*</font> </td>
                    <td>
                   <logic:present name="FinanceResBean">
	            		<logic:empty property="empCost"  name="FinanceResBean">
		            			<html:text property="empCost" style="text-align:right"  value="" onchange="return noCheck(document.forms[0].empCost)" onblur="addProfitDep()" />
		            	</logic:empty>
		              	<logic:notEmpty  property="empCost" name="FinanceResBean">
		            			<html:text property="empCost" style="text-align:right" name="FinanceResBean"  />
		            	</logic:notEmpty>
	            </logic:present>
                    </td>
                  </tr>
                  <tr>
                    <td align="left" class="tab1"><bean:message key="fin.otherExpenditure"/></td>
                    <td>
                     <logic:present name="FinanceResBean">
	            		<logic:empty property="otherExpenditure"  name="FinanceResBean">
		            			<html:text property="otherExpenditure" style="text-align:right"  value="" onchange="return noCheck(document.forms[0].otherExpenditure)" onblur="addProfitDep()" />
		            	</logic:empty>
		              	<logic:notEmpty  property="otherExpenditure" name="FinanceResBean">
		            			<html:text property="otherExpenditure" style="text-align:right" name="FinanceResBean" />
		            	</logic:notEmpty>
	            </logic:present>
                    </td>
                  </tr>
                  <tr>
                    <td align="left" class="tab1"><bean:message key="fin.nonRecurringExpenditure"/></td>
                    <td>
                    <logic:present name="FinanceResBean">
	            		<logic:empty property="nonRecurringExpenditure"  name="FinanceResBean">
		            			<html:text property="nonRecurringExpenditure" style="text-align:right"  value="" onchange="return noCheck(document.forms[0].nonRecurringExpenditure)"  onblur="addProfitDep()"  />
		            	</logic:empty>
		              	<logic:notEmpty  property="nonRecurringExpenditure" name="FinanceResBean">
		            			<html:text property="nonRecurringExpenditure" style="text-align:right"  name="FinanceResBean" />
		            	</logic:notEmpty>
	            </logic:present>
                    </td>
                  </tr>
                  <tr>
                    <td align="left" class="tab1">&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
              </table></td>
            </tr>
            <tr>
              <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                  <tr align="center" bgcolor="#BFBFBF">
                    <td height="20" colspan="2" class="tab1">---Interest---</td>
                  </tr>
                  <tr>
                    <td align="left" class="tab1">&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td align="left" class="tab1"><bean:message key="fin.interest"/><font color="red">*</font></td>
                    <td>
                    <logic:present name="FinanceResBean">
	            		<logic:empty property="interest"  name="FinanceResBean">
		            			<html:text property="interest" style="text-align:right"  value="" onchange="return noCheck(document.forms[0].interest)" onblur="addProfitDep()" />
		            	</logic:empty>
		              	<logic:notEmpty  property="interest" name="FinanceResBean">
		            			<html:text property="interest" style="text-align:right" name="FinanceResBean"  />
		            	</logic:notEmpty>
	            </logic:present>
                    </td>
                  </tr>
                  <tr>
                    <td height="20" align="left" class="tab1"><bean:message key="fin.profitBeforeDepreciation"/> </td>
                    <td>
                    <logic:present name="FinanceResBean">
	            		<logic:empty property="profitBeforeDepreciation"  name="FinanceResBean">
		            			<html:text property="profitBeforeDepreciation" style="text-align:right"  value="" disabled="true" onchange="return noCheck(document.forms[0].profitBeforeDepreciation)" onblur="ProfitBefTax()" />
		            	</logic:empty>
		              	<logic:notEmpty  property="profitBeforeDepreciation" name="FinanceResBean">
		            			<html:text property="profitBeforeDepreciation" style="text-align:right" disabled="true" name="FinanceResBean" />
		            	</logic:notEmpty>
	            </logic:present>
                    </td>
                  </tr>
                  <tr>
                    <td align="left" class="tab1"><bean:message key="fin.depriciation"/><font color="red">*</font></td>
                    <td width="52">
                   <logic:present name="FinanceResBean">
	            		<logic:empty property="depriciation"  name="FinanceResBean">
		            			<html:text property="depriciation" style="text-align:right"  value="" onchange="return noCheck(document.forms[0].depriciation)" onblur="ProfitBefTax()" />
		            	</logic:empty>
		              	<logic:notEmpty  property="depriciation" name="FinanceResBean">
		            			<html:text property="depriciation" style="text-align:right" name="FinanceResBean"  />
		            	</logic:notEmpty>
	            </logic:present>
                    </td>
                  </tr>
                  <tr>
                    <td align="left" class="tab1"><bean:message key="fin.profitBeforeTax"/></td>
                    <td>
                    <logic:present name="FinanceResBean">
	            		<logic:empty property="profitBeforeTax"  name="FinanceResBean">
		            			<html:text property="profitBeforeTax" style="text-align:right" value="" disabled="true" onchange="calc_Pat()" />
		            	</logic:empty>
		              	<logic:notEmpty  property="profitBeforeTax" name="FinanceResBean">
		            			<html:text property="profitBeforeTax" style="text-align:right" disabled="true"  name="FinanceResBean" />
		            	</logic:notEmpty>
	            </logic:present>
                    </td>
                  </tr>
              </table></td>
            </tr>
        </table></td>
        <td>&nbsp;</td>
        <td valign="top"><table width="350"  border="0" cellspacing="2" cellpadding="2">
            <tr>
              <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                  <tr align="center" bgcolor="#BFBFBF">
                    <td height="20" colspan="2" class="tab1">---Tax---</td>
                  </tr>
                  <tr>
                    <td align="left" class="tab1"><bean:message key="fin.provisionTax"/> </td>
                    <td width="52">
                    <logic:present name="FinanceResBean">
	            		<logic:empty property="provisionTax"  name="FinanceResBean">
		            			<html:text property="provisionTax" style="text-align:right"  value="" onchange="return noCheck(document.forms[0].provisionTax)" onblur="calc_Pat()"  />
		            	</logic:empty>
		              	<logic:notEmpty  property="provisionTax" name="FinanceResBean">
		            			<html:text property="provisionTax"  style="text-align:right" name="FinanceResBean" onchange="calc_Pat()" />
		            	</logic:notEmpty>
	            </logic:present>
                    </td>
                  </tr>
                  <tr>
                    <td align="left" class="tab1"><bean:message key="fin.fringBefTax"/></td>
                    <td>
                    <logic:present name="FinanceResBean">
	            		<logic:empty property="fringBefTax"  name="FinanceResBean">
		            			<html:text property="fringBefTax" style="text-align:right"  value="" onchange="return noCheck(document.forms[0].fringBefTax)" onblur="calc_Pat()"  />
		            	</logic:empty>
		              	<logic:notEmpty  property="fringBefTax" name="FinanceResBean">
		            			<html:text property="fringBefTax" style="text-align:right" name="FinanceResBean" />
		            	</logic:notEmpty>
	            </logic:present>
                    </td>
                  </tr>
                  <tr>
                    <td align="left" class="tab1"><bean:message key="fin.defferedTaxExpenses"/><font color="red">*</font> </td>
                    <td>
                    <logic:present name="FinanceResBean">
	            		<logic:empty property="defferedTaxExpenses"  name="FinanceResBean">
		            			<html:text property="defferedTaxExpenses" style="text-align:right"  value="" onchange="return noCheck(document.forms[0].defferedTaxExpenses)" onblur="calc_Pat()"  />
		            	</logic:empty>
		              	<logic:notEmpty  property="defferedTaxExpenses" name="FinanceResBean">
		            			<html:text property="defferedTaxExpenses" style="text-align:right" name="FinanceResBean" />
		            	</logic:notEmpty>
	               </logic:present>
                    </td>
                  </tr>
              </table></td>
            </tr>
            <tr>
              <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                  <tr align="center" bgcolor="#BFBFBF">
                    <td height="20" colspan="2" class="tab1">---PAT---</td>
                  </tr>
                  <tr>
                    <td height="20" align="left" class="tab1"><bean:message key="fin.profitAftTax"/></td>
                    <td width="52">
                    <logic:present name="FinanceResBean">
	            		<logic:empty property="profitAftTax" name="FinanceResBean">
		            			<html:text property="profitAftTax" style="text-align:right"  value="" disabled="true"  />
		            	</logic:empty>
		              	<logic:notEmpty  property="profitAftTax" name="FinanceResBean">
		            			<html:text property="profitAftTax" style="text-align:right" disabled="true" name="FinanceResBean"  />
		            	</logic:notEmpty>
	               </logic:present>
                    </td>
                  </tr>
              </table></td>
            </tr>
            <tr>
              <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                  <tr align="center" bgcolor="#BFBFBF">
                    <td height="20" colspan="2" class="tab1">---Others---</td>
                  </tr>
                  <tr>
                    <td align="left" class="tab1"><bean:message key="fin.paidupShareCapital"/><font color="red">*</font></td>
                    <td width="52">
                    <logic:present name="FinanceResBean">
	            		<logic:empty property="paidupShareCapital"  name="FinanceResBean">
		            			<html:text property="paidupShareCapital" style="text-align:right"  value=""  onblur="return noCheck(document.forms[0].paidupShareCapital)"  />
		            	</logic:empty>
		              	<logic:notEmpty  property="paidupShareCapital" name="FinanceResBean">
		            			<html:text property="paidupShareCapital" style="text-align:right" name="FinanceResBean"  />
		            	</logic:notEmpty>
	               </logic:present>
                    </td>
                  </tr>
                  <tr>
                    <td align="left" class="tab1"><bean:message key="fin.reverseExcludingreval"/></td>
                    <td>
                    <logic:present name="FinanceResBean">
	            		<logic:empty property="reserveExcludingreval"  name="FinanceResBean">
		            			<html:text property="reserveExcludingreval" style="text-align:right"  value="" onblur="return noCheck(document.forms[0].reserveExcludingreval)"  />
		            	</logic:empty>
		              	<logic:notEmpty  property="reserveExcludingreval" name="FinanceResBean">
		            			<html:text property="reserveExcludingreval" style="text-align:right" name="FinanceResBean"  />
		            	</logic:notEmpty>
	               </logic:present>
                    </td>
                  </tr>
                  <tr>
                    <td align="left" class="tab1"><bean:message key="fin.eps"/><font color="red">*</font></td>
                    <td>
                    <logic:present name="FinanceResBean">
	            		<logic:empty property="eps"  name="FinanceResBean">
		            			<html:text property="eps"  style="text-align:right"  value="" onblur="return noCheck(document.forms[0].eps)"  />
		            	</logic:empty>
		              	<logic:notEmpty  property="eps" name="FinanceResBean">
		            			<html:text property="eps" style="text-align:right" name="FinanceResBean"  />
		            	</logic:notEmpty>
	               </logic:present>
                    </td>
                  </tr>
                  <tr>
                    <td align="left" class="tab1"><bean:message key="fin.dividend"/></td>
                    <td>
                    <logic:present name="FinanceResBean">
	            		<logic:empty property="dividend"  name="FinanceResBean">
		            			<html:text property="dividend" style="text-align:right"  value="" onblur="return noCheck(document.forms[0].dividend)" />
		            	</logic:empty>
		              	<logic:notEmpty  property="dividend" name="FinanceResBean">
		            			<html:text property="dividend" style="text-align:right" name="FinanceResBean"  />
		            	</logic:notEmpty>
	               </logic:present>
                    </td>
                  </tr>
                  <tr>
                    <td align="left" class="tab1"><bean:message key="fin.totalDebt"/><font color="red">*</font></td>
                    <td>
                    <logic:present name="FinanceResBean">
	            		<logic:empty property="totalDebt"  name="FinanceResBean">
		            			<html:text property="totalDebt" style="text-align:right"  value="" onblur="return noCheck(document.forms[0].totalDebt)" />
		            	</logic:empty>
		              	<logic:notEmpty  property="totalDebt" name="FinanceResBean">
		            			<html:text property="totalDebt" style="text-align:right" name="FinanceResBean"  />
		            	</logic:notEmpty>
	               </logic:present>
                    </td>
                  </tr>
                  <tr>
                    <td align="left" class="tab1"><bean:message key="fin.netWorth"/><font color="red">*</font></td>
                    <td>
                    <logic:present name="FinanceResBean">
	            		<logic:empty property="netWorth"  name="FinanceResBean">
		            			<html:text property="netWorth" style="text-align:right" value=""  onchange="return noCheck(document.forms[0].netWorth)" onblur="calc_Bookvalue()" />
		            	</logic:empty>
		              	<logic:notEmpty  property="netWorth" name="FinanceResBean">
		            			<html:text property="netWorth" style="text-align:right"  name="FinanceResBean"  />
		            	</logic:notEmpty>
	               </logic:present>
                    </td>
                  </tr>
              </table></td>
            </tr>
            <tr>
              <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                  <tr align="center" bgcolor="#BFBFBF">
                    <td height="20" colspan="2" class="tab1">---Aggregate of Non Promoter Shareholding---</td>
                  </tr>
                  <tr>
                    <td align="left" class="tab1"><bean:message key="fin.numberOfShares"/><font color="red">*</font> </td>
                    <td width="52">
                    <logic:present name="FinanceResBean">
	            		<logic:empty property="numberOfShares"  name="FinanceResBean">
		            			<html:text property="numberOfShares"  style="text-align:right" value="" onchange="return noCheck(document.forms[0].numberOfShares)" onblur="calc_Bookvalue()" />
		            	</logic:empty>
		              	<logic:notEmpty  property="numberOfShares" name="FinanceResBean">
		            			<html:text property="numberOfShares"  style="text-align:right" name="FinanceResBean" />
		            	</logic:notEmpty>
	               </logic:present>
                    </td>
                  </tr>
                  <tr>
                    <td align="left" class="tab1"><bean:message key="fin.percentageOfShareHolding"/><font color="red">*</font></td>
                    <td>
                    <logic:present name="FinanceResBean">
	            		<logic:empty property="percentageOfShareHolding"  name="FinanceResBean">
		            			<html:text property="percentageOfShareHolding"  style="text-align:right" value="" onblur="return noCheck(document.forms[0].percentageOfShareHolding)"  />
		            	</logic:empty>
		              	<logic:notEmpty  property="percentageOfShareHolding" name="FinanceResBean">
		            			<html:text property="percentageOfShareHolding" style="text-align:right" name="FinanceResBean"  />
		            	</logic:notEmpty>
	               </logic:present>
                    </td>
                  </tr>
                  <tr>
                    <td height="20" align="left" class="tab1"><bean:message key="fin.bookValue"/></td>
                    <td>
                    <logic:present name="FinanceResBean">
	            		<logic:empty property="bookValue"  name="FinanceResBean">
		            			<html:text property="bookValue" style="text-align:right" disabled="true"  value=""  />
		            	</logic:empty>
		              	<logic:notEmpty  property="bookValue" name="FinanceResBean">
		            			<html:text property="bookValue" style="text-align:right" disabled="true" name="FinanceResBean"  />
		            	</logic:notEmpty>
	               </logic:present>
                    </td>
                  </tr>
              </table></td>
            </tr>
            <tr>
              <td><table width="100%"  border="0" cellspacing="2" cellpadding="2">
                <tr align="center" bgcolor="#BFBFBF">
                  <td colspan="2" class="tab1">---Flags---</td>
                </tr>
                <tr>
                  <td width="59%" align="left"><span class="tab1"><bean:message key="fin.audited"/></span></td>
                  <td width="41%" align="left">
                  <html:checkbox  property="audited" name="FinanceResBean" />
                  </td>
                </tr>
                <tr>
                  <td align="left"><span class="tab1"><bean:message key="fin.cumulative"/></span></td>
                  <td align="left">
                  <html:checkbox  property="cumulative" name="FinanceResBean" />
                  </td>
                </tr>
                <tr>
                  <td align="left"><span class="tab1"><bean:message key="fin.consolidated"/></span></td>
                  <td align="left">
                  <html:checkbox  property="consolidated" name="FinanceResBean" />
                  </td>
                </tr>
              </table></td>
            </tr>
        </table></td>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td align="center">&nbsp;</td>
  </tr>
  <tr>
    <td align="center">
    
    <html:hidden property="operation"></html:hidden>
    
    <table width="40%"  border="0" cellspacing="0" cellpadding="0">
        <tr align="center">
        <logic:equal value="Submit" property="b1" name="FinanceResBean">
	<logic:equal value="0" property="check_flag" name="FinanceResBean">
	 <td><html:submit  property="b1" onclick="return Itestcheck();" disabled="true"><bean:message key="indexUpdate.save"/></html:submit> </td>
	 </logic:equal>
	</logic:equal>
	 <logic:notEqual value="0" property="check_flag" name="FinanceResBean">
          <td><html:submit  property="b1" onclick="return Itestcheck();"><bean:message key="indexUpdate.save"/></html:submit> </td>
        </logic:notEqual>
          <td><html:button  property="b2" onclick="check2();"><bean:message key="indexUpdate.reset"/></html:button></td>
          <td><input type="submit" name="Submit" value="Back..."></td>
        </tr>
    </table></td>
  </tr>
</table>

</body>

<script language="javascript">
  function  check1()
  {
    document.forms[0].operation.value="insert";
 	return true;
   }
   function check2()
   {
   //document.forms[0].operation.value="reset";
   document.forms[0].s_companyName.value ="0";
   document.forms[0].months.value = "0";
   document.forms[0].currency.value = "0";
   document.forms[0].units.value = "0";
   document.forms[0].start_date.value="";
   document.forms[0].to_date.value="";
   document.forms[0].netSales.value="";
   document.forms[0].otherIncome.value="";
   document.forms[0].nonRecurringIncome.value="";
   document.forms[0].incDecStock.value="";
   document.forms[0].rawMaterial.value = "";
   document.forms[0].empCost.value="";
   document.forms[0].otherExpenditure.value="";
   document.forms[0].nonRecurringExpenditure.value="";
   document.forms[0].interest.value="";
   document.forms[0].depriciation.value="";
   document.forms[0].provisionTax.value="";
   document.forms[0].fringBefTax.value="";
   document.forms[0].defferedTaxExpenses.value="";
   document.forms[0].paidupShareCapital.value="";
   document.forms[0].reserveExcludingreval.value="";
   document.forms[0].eps.value="";
   document.forms[0].dividend.value="";
   document.forms[0].totalDebt.value="";
   document.forms[0].netWorth.value="";
   document.forms[0].numberOfShares.value="";
   document.forms[0].percentageOfShareHolding.value="";
   document.forms[0].interest.value = "";
   document.forms[0].operation.value="reset";
   document.forms[0].submit();
   }
 
  function Itestcheck()
  {
	var msg='';
	var conVal=confirm('Are You Sure to Insert The Values ?');
	//alert("hi");
	if(conVal == true){
	
	if(document.forms[0].s_companyName.value == "0")
	{
	 msg=msg+'Please Select Company Name\n';
	}
	if(document.forms[0].months.value == "0")
	{
	 msg=msg+'Please Select Number Of Months\n';
	}
	if(document.forms[0].currency.value == "0")
	{
	 msg=msg+'Please Select Currency\n';
	}
	if(document.forms[0].units.value == "0")
	{
	 msg=msg+'Please Select Units!!\n';
	}
	if(document.forms[0].netSales.value == "" || document.forms[0].netSales.value == "0" )
	{
	 msg=msg+'Please Enter value for Net Sales!!\n';
	}
	if(document.forms[0].otherIncome.value == "" || document.forms[0].otherIncome.value == "0"  )
	{
	 msg=msg+'Please Enter value for Other Income!!\n';
	}
	if(document.forms[0].rawMaterial.value == "" || document.forms[0].rawMaterial.value == "0" )
	{
	 msg=msg+'Please Enter value for Raw Materials!!\n';
	}
	if(document.forms[0].empCost.value == "" || document.forms[0].empCost.value == "0" )
	{
	 msg=msg+'Please Enter value for Employee Cost!!\n';
	}
	if(document.forms[0].interest.value == "" || document.forms[0].interest.value == "0")
	{
	 msg=msg+'Please Enter value for Interest!!\n';
	}
	if(document.forms[0].depriciation.value == "" || document.forms[0].depriciation.value == "0")
	{
	 msg=msg+'Please Enter value for Depreciation!!\n';
	}
	if(document.forms[0].defferedTaxExpenses.value == "" || document.forms[0].defferedTaxExpenses.value == "0")
	{
	 msg=msg+'Please Enter value for Deffered Tax Expenses!!\n';
	}
	if(document.forms[0].eps.value == "" || document.forms[0].eps.value == "0")
	{
	 msg=msg+'Please Enter value for EPS!!\n';
	}
	if(document.forms[0].totalDebt.value == "" || document.forms[0].totalDebt.value == "0")
	{
	 msg=msg+'Please Enter value for Total Debt!!\n';
	}
	if(document.forms[0].paidupShareCapital.value == "" || document.forms[0].paidupShareCapital.value == "0")
	{
	 msg=msg+'Please Enter value for Paidup Share Capital!!\n';
	}
	 
	if(document.forms[0].netWorth.value == "" || document.forms[0].netWorth.value == "0")
	{
	 msg=msg+'Please Enter value for Networth!!\n';
	}
	if(document.forms[0]. numberOfShares.value == "" || document.forms[0]. numberOfShares.value == "0")
	{
	 msg=msg+'Please Enter value for Number of Shares!!\n';
	}
	if(document.forms[0].percentageOfShareHolding.value == "" || document.forms[0].percentageOfShareHolding.value == "0")
	{
	 msg=msg+'Please Enter value for Percentage Of ShareHolding!!\n';
	}
	 if(msg==""){ 
	    document.forms[0].operation.value="insert";
	   return true;
	  }else{
			alert(msg);
			return false;
		  }
	 }
	else{
	return false;
	 }
 }
  

</script>
</html:form> 
</html:html>
