
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
			
			if(request.isRequestedSessionIdValid())	{	
				form = (LogonForm)session.getAttribute("user");
				String fr2=request.getParameter("from");
				String locale=session.getAttribute("locale").toString();
			//	AcessControl asc=new AcessControl();
				AcessControl asc=ConnectInit.getAcessControl();
				asc.setLocale(locale);
			}
			log.info("form is "+form);
			if(form==null ||(!request.isRequestedSessionIdValid())){
				response.sendRedirect("../userlogintemp.jsp");
			} 
%>
<html>
<html:base/>
<link href="./StyleSheet.css" rel="stylesheet" type="text/css">

<jsp:useBean id="stockmasterbondscommodity" scope="session" class="harrier.income.com.masters.StockMasterBondsCommodities"/> 

<html:form  action="/commodityAction">
<script language="javascript" src="./codethatcalendarstd.js"></script>
	<script language="javascript" src="box_ex.js"></script>
	<script language="javascript">
		var c2 = new CodeThatCalendar(caldef2);
	</script>

	<body>
    
	 <%

		boolean bodydisp=(stockmasterbondscommodity.bodycheck(request));
    
      %>
      
        <logic:equal value="Submit" property="b1" name="stockmasterbondscommodity">
	<logic:equal value="0" property="check_flag" name="stockmasterbondscommodity">
	<font size="2" color="green"> <b><bean:message key="stockmasterbonds.success"/></b></font>
	</logic:equal>
	<logic:equal value="1" property="check_flag" name="stockmasterbondscommodity">
	<font size="2" color="red"><b><bean:message key="stockmasterbonds.error"/></b></font>
	</logic:equal>
	</logic:equal>
        <table border="1" width="100%" class="gridStyle" cellspacing="0" cellpadding="0">
        <tr><td width="75%"></td></tr>        
          <tr>
            <td width="64%">
              <table border="0" width="100%" class="gridStyle" cellspacing="0" cellpadding="2">
                <tr>
                  <td width="25%" class="subHeader" nowrap="nowrap"><b><bean:message key="stockmaster.commodityDetail" /></b></td> 
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
                  <td width="25%" class="tab" nowrap="nowrap"><b><bean:message key="stockmaster.commodityID" /></b></td> 
                  <td width="25%" class="tab"><html:text property="s_stockID" size="10" name="stockmasterbondscommodity"  /></td> 
                  <td width="25%" class="tab"><b>&nbsp;</b></td> 
                  <td width="25%" class="tab"><b>&nbsp;</b></td>                                                 
                </tr>      	     
     	 </logic:notEmpty>
     	  <tr>
                  <td width="25%" align="left" class="tab" ><bean:message key="stockmaster.commodityName"/><font color="red">*</font></td>                 
                 
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
                  <td width="25%" align="left" class="tab"><bean:message key="stockmaster.commodityType"/></td>
                  <td width="20%"> 
	            	<html:text property="s_stockType" disabled="true" size="20"  maxlength="6"    value="Commodity"   />
                   </td>
                 
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
                 <td width="25%" align="left" class="tab"><bean:message key="commodity.deliverycenter"/></td>
                 <td width="20%" nowrap="nowrap"><logic:present name="stockmasterbondscommodity"> 
	            	
                  <logic:empty property="s_stockID" name="stockmasterbondscommodity">
	            		<logic:empty property="deliverycenter" name="stockmasterbondscommodity">
	            			<html:text property="deliverycenter" size="20" value=""  />
	            		</logic:empty>
	              		<logic:notEmpty property="deliverycenter" name="stockmasterbondscommodity">
	            			<html:text property="deliverycenter" size="20" name="stockmasterbondscommodity"  />
	            		</logic:notEmpty>
	            	</logic:empty>
	              	<logic:notEmpty property="s_stockID" name="stockmasterbondscommodity">
	            			<logic:empty property="deliverycenter" name="stockmasterbondscommodity">
	            				<html:text readonly="true" property="deliverycenter" size="20" value=""  />
	            			</logic:empty>
	              			<logic:notEmpty property="deliverycenter" name="stockmasterbondscommodity">
	            				<html:text readonly="true" property="deliverycenter" size="20" name="stockmasterbondscommodity"  />
	            			</logic:notEmpty>
	            	</logic:notEmpty>
	            	</logic:present>
                  &nbsp;&nbsp;
                 </td>
                                  
                   <td width="25%" align="left" class="tab"><bean:message key="commodity.measure"/></td>
                    <td width="25%"><logic:present name="stockmasterbondscommodity">
                    <logic:empty property="s_stockID" name="stockmasterbondscommodity">
	            		<logic:empty property="measure" name="stockmasterbondscommodity">
	            			<html:text property="measure" size="20" value=""  />
	            		</logic:empty>
	              		<logic:notEmpty property="measure" name="stockmasterbondscommodity">
	            			<html:text property="measure" size="20" name="stockmasterbondscommodity"  />
	            		</logic:notEmpty>
	            	</logic:empty>
	              	<logic:notEmpty property="s_stockID" name="stockmasterbondscommodity">
	            			<logic:empty property="measure" name="stockmasterbondscommodity">
	            				<html:text readonly="true" property="measure" size="20" value=""  />
	            			</logic:empty>
	              			<logic:notEmpty property="measure" name="stockmasterbondscommodity">
	            				<html:text readonly="true" property="measure" size="20" name="stockmasterbondscommodity"  />
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
                   <td width="25%" align="left" class="tab"><bean:message key="commodity.exp_date"/></td>
                    <td width="25%"><logic:present name="stockmasterbondscommodity">
	            		<logic:empty property="exp_date" name="stockmasterbondscommodity">
		            			<html:text readonly="true" property="exp_date" size="20"  value=""  />
		            	</logic:empty>
		              	<logic:notEmpty  property="exp_date" name="stockmasterbondscommodity">
		            			<html:text readonly="true" property="exp_date" size="20" name="stockmasterbondscommodity"  />
		            	</logic:notEmpty>
	            </logic:present><input onclick="c2.popup('exp_date');"  type="button" value="..."/></td>
                    
                    <td width="25%" align="left" class="tab"><bean:message key="stockmaster.currency"/></td>                  
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
                   <td width="25%" align="left" class="tab"><bean:message key="stockmaster.priceunit"/><font color="red">*</font></td>
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
          
 </table>
 <table border="0" width="101%" class="gridStyle" cellspacing="0" cellpadding="4">
          <tr>
            
	
	 <td width="97%" valign="top" class="tab">
              <p align="left">
             <b><bean:message key="stockmaster.active"/></b> 
              <html:checkbox property="b_isActive" /></p></td>
             
          </tr> 
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
            <html:submit property="b1" onclick="return test1()"><bean:message key="stockmaster.new"/></html:submit>
            &nbsp;&nbsp; &nbsp; <html:reset  property="B3"><bean:message key="indexUpdate.reset"/></html:reset>&nbsp;&nbsp;&nbsp;
         <!--<html:submit value="Import From file..." property="b1"/>-->
         	 
         <a href="../ImportNewStock.jsp"><bean:message key="stockmaster.importFromFile"/></a></td>        
          </tr>      	
		     
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
	//alert("hi");
	if(conVal == true){
	
	if(document.forms[0].s_stockName.value == "")
	{
	 msg=msg+'Please Enter Commodity Name\n';
	/* alert("Please Enter Stock Name");
	 return false; */
	}
	
	if(document.forms[0].d_iwf.value == "")
	{
	 msg=msg+'Please Enter IWF value\n';
	 /*alert("Please Enter IWF value");
	 return false;*/
	}
		
	if(document.forms[0].s_stockExchange.value == "")
	{
	 msg=msg+'Please Enter Stock Exchange\n';
	 /*alert("Please Enter Stock Exchange");
	 return false;*/
	}
	
	if(document.forms[0].s_countryName.value == "")
	{
	  msg=msg+'Please Enter Country\n';
	 /*alert("Please Enter Country");
	 return false;*/
	}
		if(document.forms[0].s_stockCurrency.value == "")
	{
       msg=msg+'Please Enter Currency\n';
	 /*alert("Please Enter Currency");
	 return false;*/
	}
	if(document.forms[0].f_alertPercent.value == "")
	{
      msg=msg+'Please Enter Alert Percentage\n';
	/*alert("Please Enter Alert Percentage");
	return false;*/
	}
	if(document.forms[0].f_rejectionPercent.value == "")
	{
	   msg=msg+'Please Enter Rejection Percentage\n';
	/*alert("Please Enter Rejection Percentage");
	return false;*/
	}
	if(document.forms[0].s_marketLot.value == "")
	{
	  msg=msg+'Please Enter Price Unit\n';
	/*alert("Please Enter Price Unit");
	return false;*/
	}
	if(document.forms[0].b_ric.value == "")
	{
	  msg=msg+'Please Enter RIC Code\n';
	/*alert("Please Enter RIC Code");
	return false;*/
	}
	if(document.forms[0].b_crisil.value == "")
	{
      msg=msg+'Please Enter Crisil code\n';
	/*alert("Please Enter Crisil code");
	return false;*/
	}
	if(document.forms[0].b_exc_code.value == "")
	{
      msg=msg+'Please Enter Exchange Code\n';
	/*alert("Please Enter Exchange Code");
	return false;*/
	}
	if(document.forms[0].b_tkr.value == "")
	{ 
	  msg=msg+'Please Enter Ticker code\n';
	/*alert("Please Enter Ticker code");
	return false;*/
	}
	 if(msg==""){ 
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
  function stktest()
 {
	document.location.href="/Stockpile/pages/masters/NewIssues.jsp?ref_flag=1";
 } 
 function treereload()
 {
 		//alert("in tree load");
		top.treeFrame.location.reload();
		//alert("after tree load");			
 }	
</script>

</body>
</html>