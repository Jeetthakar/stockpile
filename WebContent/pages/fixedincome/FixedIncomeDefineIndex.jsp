<%@ page
    info="Home Page"
    language="java"
    errorPage="/pages/errors.jsp"
    contentType="text/html;charset=utf-8" %>
<%@page import="com.harrier.initializeation.ConnectInit"%>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page import="java.sql.*"%>
<%@ page import="app.*,harrier.income.com.masters.*"%>
<%@ page import="harrier.income.com.fixedincome.*"%>
<%@ page import="java.util.*"%>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
		LogonForm form = (LogonForm) session.getAttribute("user");
			if(form==null)
			response.sendRedirect("/pages/userlogintemp.jsp");	
			String locale=session.getAttribute("locale").toString();
		//	AcessControl asc=new AcessControl();
			AcessControl asc = ConnectInit.getAcessControl();
			asc.setLocale(locale);        
%>
 
   <%  %>
   <logic:equal value="yes"  scope="request" parameter="new">  
   <%
   try{
   session.removeAttribute("FixedIncomeDefineIndexForm");
   }catch(Exception e){   
   }
   %>
   <script>
   submit();
   </script>
  </logic:equal>
<html>
<link href="./../StyleSheet.css" rel="stylesheet" type="text/css">
<head>
<html:base/>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 6.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title><bean:message key="defineIndex1" /></title>
   <meta name="Microsoft Theme" content="none"> 
	<script language="javascript" src="./../Script/codethatcalendarstd.js"></script>
	<script language="javascript" src="box_ex.js"></script>
	<script language="javascript">		
		var c2 = new CodeThatCalendar(caldef2);		
	</script> 	
</head>                
<body onload="return checktype()">
<html:errors/>
  <html:form action="/FixedIncomeDefineIndexAction" >
  <jsp:setProperty name="FixedIncomeDefineIndexForm" property="user_id" value='<%= session.getAttribute("userid").toString()%>'/>
  <input type="hidden" name="copyparent_before">
  <%
     
   if(request.getAttribute("submitresult1")!=null && request.getAttribute("submitresult1").equals("true")){
      
   %>
  <bean:message key="message.succesfullIndexCreation"/>
 <%    
 }else if(request.getAttribute("submitresult1")!=null && request.getAttribute("submitresult1").equals("false")){
    
 %>
   <bean:message key="failedIndexCreation"/>
   <%
   
   }%>
               
        <table border="1" width="100%" bordercolorlight="#C0C0C0" cellspacing="0" cellpadding="0" height="406">
          <tr>
            <td width="100%" height="428">
              <table border="0" width="100%" cellspacing="0" cellpadding="3" height="409" >
                <tr>
            <td width="100%" colspan="2" class="tab" height="1">
              <p align="left"><b>&nbsp; <bean:message key="fixedincomedefineIndex1" />&nbsp; </b>
              </p></td></tr>  
                <tr>
            <td width="1%" class="tab">
              &nbsp;</td>
            <td width="194%" class="tab">&nbsp;</td>
                </tr>
                <tr>
            <td width="1%" valign="top" class="tab" height="345">
              &nbsp;
            </td>
            <td width="194%" valign="top" class="tab" height="345">
              <table border="0" width="100%" class="gridStyle" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="100%" >
                    <table border="0" width="105%" class="gridStyle" cellspacing="0" cellpadding="3">
                      <tr>
              			<td width="3%" align="left" class="tab">
              			<p align="left"><html:checkbox property="is_testIndex"  /></p></td>
              			<td width="45%" class="tab"><bean:message key="indexUpdate.testIndex"/></td>
              		 </tr>
                      <tr>
                      <td width="3%" align="left" class="tab">
                           <p align="left"><html:radio  value="1" property="b_isIndexIsChildOrCustomized" onclick="copycondition1()"  disabled="true" /></p></td>
                        <td width="45%" class="tab"><bean:message key="defineIndex"/></td>                        
                         	
                        	<td width="20%" align="right" class="tab">
                              <p align="right">
                           <bean:message key="defineIndex3"/></p>
                        </td>
                          <td width="46%" align="left" class="tab">
                           <p align="left">&nbsp;&nbsp;
                           <html:select property="s_parentIndex" onchange="return test3()" disabled="true">
                           <html:optionsCollection name="FixedIncomeDefineIndexForm" property="s_parentIndexCollection" />
                           </html:select>
                          &nbsp;&nbsp;&nbsp;</p>
   					<input type="hidden" name="parentId" value="false">
					<input type="hidden" name="currencyCode" >
                         </td>
                      </tr>
                      <tr>
                       <td width="3%" class="tab">
                        
                        <p align="left"><html:radio value="2" property="b_isIndexIsChildOrCustomized" onclick="copycondition1()"  disabled="true" /></p></td>
                        <td width="45%" class="tab"><bean:message key="defineIndex5"/></td>
                       
                        <td width="10%" align="left" class="tab"></td>
                        <td width="56%" align="left" class="tab">
                          &nbsp;&nbsp;<html:checkbox property="b_ShowChild" onclick="testshowchildindexes()" disabled="true"/>					
                         <bean:message key="defineIndex4"/>
                        </td>                                            
                      </tr>
				<tr>
				<td width="3%" class="tab">  <p align="left"><html:radio value="3" onclick="copycondition()" property="b_isIndexIsChildOrCustomized"  disabled="true" /></p></td>
				<td width="45%" class="tab"><bean:message key="message.copyofanotherindex"/></td>
				
				
				
				
						<!--<td width="2%"></td>-->
                   	    <td width="10%" align="left" class="tab">
                        <p align="left">&nbsp;&nbsp;</p>
                       	</td>
	<td width="56%" align="left" class="tab"><bean:write name="FixedIncomeDefineIndexForm" property="currency"/>
	</td>	
				
                    </table>
                    </tr>
                      
                  </td>
                </tr>
                
                
                <!-- added by neha for compliance -->
                    <tr>
			          <td   width="70%" align="middle" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			            <font face="Arial" size="2"><bean:message key="indCompliance.idx"/></font>
			          &nbsp;&nbsp;&nbsp;
			 	          <html:select size="1" property="name_list"  disabled="true">
				          <html:optionsCollection property="nameListCollection" name="FixedIncomeDefineIndexForm" />
			           </html:select>
		             </td>
		             </tr>
		           <!-- -------------- -->
				
                 <tr>
                  <td width="100%" class="tab">------------------------------------------------------------------------------------------------------------------------------------------------------------------<br><br>
                    <table border="0" width="100%" class="gridStyle" cellspacing="0" cellpadding="6" >
                      <tr> <td width="18%" align="right" class="tab"><bean:message key="defineIndex7"/><font color="red">*</font></td>
                        <td width="17%" class="tab" ><html:text property="s_indexName" size="7"/></td>
                        <td width="21%" align="right" class="tab"><bean:message key="defineIndex6"/><font color="red">*</font></td>
                        <td width="13%" align="center" class="tab">
                          <p align="left"> 
                          <html:select property="s_indexType" onchange="return checktype()">
                           <html:optionsCollection name="FixedIncomeDefineIndexForm" property="s_indexTypeIndexCollection"/>
                           </html:select>
                        </p></td>
                       <td width="21%" align="right" class="tab"><bean:message key="defineIndex24"/></td><td width="13%" align="left" class="tab">	
                                   <html:checkbox property="b_isCaptured" onclick="disableButton()" />
                          </td> 		                
                       </tr>
                      <tr>
                        <td width="19%" align="right" class="tab"><bean:message key="defineIndex9"/><font color="red">*</font></td>
                        <td width="13%" class="tab"> 
                          <html:text  property="d_creationDate" size="9" readonly="true"  /><input onclick="c2.popup('d_baseDate');" name="bdate"  type="button" value="..."> 
                       <bean:message key="defineIndex32"/>
                        </td>
                         <td width="17%" align="right" class="tab"><bean:message key="defineIndex8"/><font color="red">*</font>
                        </td>
                        <td width="17%" valign="bottom" class="tab">				   					
						<p style="margin-left: 1" align="left" valign="bottom">
						<html:text property="d_baseDate"  size="11"  /><input onclick="c2.popup('d_baseDate');" name="bdate"  type="button" value="...">
                   </p></td>
                          <td  width="21%"  align="right" class="tab"><p align="right" valign="bottom"><bean:message key="defineIndex25"/></p></td>
                        <td width="13%" align="left" class="tab" >
                          <html:select property="s_capturedFrom" onchange="return test4()">
                            <html:optionsCollection name="FixedIncomeDefineIndexForm" property="s_capturedFromCollection"/>
                          </html:select>
                          </td>

                         </tr>
  				     	          
 				<tr>
                        <td width="19%" align="right" class="tab"><bean:message key="defineIndex16"/><font color="red">*</font></td>
                     
                    <td width="13%" class="tab" > 
                    <html:select property="s_baseCurrency" size="1">
                           <html:optionsCollection name="FixedIncomeDefineIndexForm" property="s_baseCurrencyCollection"/>
                           </html:select>
                        
                    </td>
                        <td width="17%" align="right" class="tab" ><bean:message key="defineIndex17"/><font color="red">*</font></td>
		    <td width="17%" class="tab"> 
                         <html:text property="d_baseValue"  size="7"  />
                          </td>
                          
                          <td width="21%" align="right" ><font size="2" face="Arial" align="right"><bean:message key="defineIndex21"/></font></td>
			 			 <td width="13%"  > 
                          <html:checkbox property="b_computeSettlementValue" />
                          </td>                      
  	</tr>
  	
    <tr>
                      <td width="19%" align="right" class="tab"><font face="Arial" size="2" align="right"><bean:message key="defineIndex12"/></td>
                    
                     <td width="13%" class="tab"> 
                          <html:text property="s_alertPercent" size="7" />
                         
                     
                     %</td>
					 <td width="17%" align="right" class="tab"><bean:message key="defineIndex14"/></td>
                    
                     
                     <td width="13%" class="tab"> 
                           <html:text property="s_rejectionPercent" size="7" />
                     
                     
                     %</td>
                      <td width="21%" align="right" class="tab"><bean:message key="defineIndex18"/></td>		
                    <td width="13%" class="tab"> 
                        <html:text property="s_ISIN"  size="7" />
                        </td> 
                  </tr>     
    <tr>
 		 <td width="19%" align="right"  class="tab"><bean:message key="defineIndex11"/> 
                </td>
                   <td width="17%" class="tab"> 
                     <html:checkbox name="FixedIncomeDefineIndexForm" property="s_applyClassification"  />
                   </td>
                   
                    <td width="19%" align="right" class="tab"><bean:message key="defineIndex10"/></td>
                    <td width="68%" align="left" class="tab" colspan="3">
                     <p align="left"><html:select property="s_industryClassificationID" >
                     <html:optionsCollection name="FixedIncomeDefineIndexForm" property="s_industryClassificationIDCollection"/>
                     </html:select>
                   	</p></td> 

              </tr>         
                      <tr>                       
                        <td width="19%" align="right" class="tab"><bean:message key="defineIndex22"/></td>
                        <td width="13%" align="center" class="tab">		
  						<p align="left">  <html:checkbox property="b_isActive" />
                       </td>
                       
                      </tr>
                     
                   </table>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
         
		
		
     	<input type="hidden" name="submitvalue">                          
          <tr>
            <td width="1%" valign="top" class="tab" height="27">&nbsp;</td>
            <td width="99%" valign="top" class="tab" height="27">
            
  			<logic:equal name="FixedIncomeDefineIndexForm" property="b_isCaptured" value="on">
              <p align="center"> <input type="submit" value='<bean:message key="defineIndex30"/>' name="submit1" onclick="return test()"/>&nbsp;
              </logic:equal>
              <logic:notEqual name="FixedIncomeDefineIndexForm" property="b_isCaptured" value="on">
              	<p align="center"> <input type="submit" value='<bean:message key="defineIndex30"/>' name="submit1" disabled="disabled" onclick="return test()"/>&nbsp;
              </logic:notEqual>
             <input type="reset" value='<bean:message key="indexUpdate.reset"/>' name="submit2"/>
              <input type="submit" value='<bean:message key="defineIndex29"/>' name="submit2" onclick="return test1()"/>&nbsp;
            <html:hidden property="getSet" ></html:hidden>
            <!--<input type="reset" value="Cancel" name="submit4"/></td>-->
       </tr>
	  </table>          
      </td>
    </tr>
</table>
 <input type="hidden" name="copyradio_after">
  <input type="hidden" name="copyparent_after">
  <input type="hidden" name="copytype_after">
  
  <logic:equal name="FixedIncomeDefineIndexForm" property="copyparent_before" value="copy">
  <script>
  var y=document.getElementById("s_industryClassificationID");
	y.disabled=true;
	y=document.getElementById("s_indexType")
y.disabled=true;
document.getElementById("bdate").disabled=false;
document.getElementById("s_applyClassification").disabled=true;
document.getElementById("s_alertPercent").disabled=true;
document.getElementById("s_rejectionPercent").disabled=true;

document.getElementById("s_ISIN").disabled=true;
document.getElementById("s_baseCurrency").disabled=true;

document.getElementById("b_computeSettlementValue").disabled=true;
document.getElementById("b_isActive").disabled=true;

document.getElementById("b_isCaptured").disabled=true;
document.getElementById("s_capturedFrom").disabled=true;    
 document.getElementById("d_baseValue").disabled=true;
    
      
    </script>  
  </logic:equal>
  <logic:notEqual name="FixedIncomeDefineIndexForm" property="copyparent_before" value="copy">
  <script>
  var y=document.getElementById("s_industryClassificationID");
	y.disabled=false;
	y=document.getElementById("s_indexType")
y.disabled=false;
document.getElementById("bdate").disabled=true;
document.getElementById("s_applyClassification").disabled=false;
document.getElementById("s_alertPercent").disabled=false;
document.getElementById("s_rejectionPercent").disabled=false;

document.getElementById("s_ISIN").disabled=false;
document.getElementById("s_baseCurrency").disabled=false;

document.getElementById("b_computeSettlementValue").disabled=false;
document.getElementById("b_isActive").disabled=false;

document.getElementById("b_isCaptured").disabled=false;
document.getElementById("s_capturedFrom").disabled=false;    
 document.getElementById("d_baseValue").disabled=false;
    
      
    </script>      
  </logic:notEqual>
  
 
       
</html:form>

<script language="javascript">
function test()
 {  
 document.forms[0].getSet.value="no";
 document.forms[0].parentId.value="false";	
 	document.forms[0].submitvalue.value="Submit";
 	if (document.forms[0].b_isIndexIsChildOrCustomized[2].checked )
		{
			 x=document.getElementById("s_parentIndex");
			 var y=x.options[x.selectedIndex].value; 
 			 if(y!=0){ 				
					 
				 document.forms[0].copyparent_before.value="copy"; 
 			 }
			
		}
  	return true;
 }
function test1()
 {  
 document.forms[0].getSet.value="no";
 document.forms[0].parentId.value="false";	
 	document.forms[0].submitvalue.value="Next >>";
 	if (document.forms[0].b_isIndexIsChildOrCustomized[2].checked )
		{
			 x=document.getElementById("s_parentIndex");
			 var y=x.options[x.selectedIndex].value; 
 			 if(y!=0){ 					
					 
				 document.forms[0].copyparent_before.value="copy"; 
 			 }
			
		}
  	return true;
 }
function test3()
 {
 		document.forms[0].getSet.value="yes";
 		var user_input;	 		
 		for (i=0;i<document.forms[0].b_isIndexIsChildOrCustomized.length;i++)
		{	
		
			if (document.forms[0].b_isIndexIsChildOrCustomized[i].checked)
			{	if(i==2){
			     document.forms[0].copyparent_before.value="copy";
			      makeDisabletwo();
				}		
				user_input = "yes";				
				break;
			}
		}	
		
		if(user_input!=null &&  user_input=="yes"){ 	
			
		document.forms[0].parentId.value="true";	
		document.forms[0].submitvalue.value="fromParent";
		
		if(document.forms[0].b_isIndexIsChildOrCustomized[2].checked==true ){
		document.forms[0].copyparent_before.value="copy";
		
		}else{
		
		}	
 		document.forms[0].submit(); 
 		
		return false;
		}else{
		
		document.forms[0].b_isIndexIsChildOrCustomized[1].checked=true;
 	document.forms[0].parentId.value="true";
 	document.forms[0].submitvalue.value="fromParent";
 	document.forms[0].submit(); 
 	return true;
 	}
 }
 
 function test4()
 {
 var object = eval('document.forms[0].b_isCaptured'); 
 object.checked = true;  
 }
 
 function testshowchildindexes(){
document.forms[0].parentId.value="true";
document.forms[0].submitvalue.value="fromParent";
if (document.forms[0].b_isIndexIsChildOrCustomized[2].checked )
		{
			 x=document.getElementById("s_parentIndex");
			 var y=x.options[x.selectedIndex].value; 
 			 if(y!=0){ 					
				 document.forms[0].submitvalue.value="fromParent";	 
				 document.forms[0].copyparent_before.value="copy"; 
 			 }
			
		}
 document.forms[0].submit(); 
 }
  function disableButton(){
 	//alert(" in disable button ");
 	if (document.forms[0].b_isCaptured.checked ){
 		document.getElementById("submit1").disabled=false;
 		
 	}else{
 		document.getElementById("submit1").disabled=true;
 		
 	}
 }
 
 function goToBaseDate()
 {

document.getElementById("dt").focus(); 

 
 }
 function checktype(){
 
 var x=document.getElementById("s_indexType");
 var y=x.options[x.selectedIndex].value;
 
 if(y==4){
 
  makeDisabletwo();
  
 } else if(y==5){

 makeDisableone();
 }else{

 makeEnabletwo();
 }
 
 }
 
 function makeDisabletwo()
{
var y=document.getElementById("s_industryClassificationID")
y.disabled=true
var x=document.getElementById("s_baseCurrency")
x.disabled=true
}
function makeEnabletwo()
{
 var xx= document.forms[0].b_isIndexIsChildOrCustomized[2].value;
 var flag=false;
 
 if(document.forms[0].b_isIndexIsChildOrCustomized[2].checked){
 }else{
var y=document.getElementById("s_industryClassificationID")
y.disabled=false
var x=document.getElementById("s_baseCurrency")
x.disabled=false
}
}
 function makeDisableone()
{
var y=document.getElementById("s_industryClassificationID")
y.disabled=true
makeEnableone();

}
function makeEnableone()
{
var x= document.forms[0].b_isIndexIsChildOrCustomized[2].value;
if(document.forms[0].b_isIndexIsChildOrCustomized[2].checked){}else{
var y=document.getElementById("s_baseCurrency")
y.disabled=false
}

}

function copycondition()
 {  
 document.forms[0].getSet.value="no";
 var x= document.forms[0].b_isIndexIsChildOrCustomized[2].value;
 
 var flag=false;
 if(document.forms[0].b_isIndexIsChildOrCustomized[2].checked){
 x=document.getElementById("s_parentIndex");
 var y=x.options[x.selectedIndex].value;
 
  if(y!=0){
  
 	 document.forms[0].parentId.value="true";	
	 document.forms[0].submitvalue.value="fromParent";
	 document.forms[0].copyparent_before.value="copy";	 
	 document.forms[0].submit();
  }else{
 
  }
  
 if(y==4){
 
 }
 }
 
 
 }
 
 function copycondition1()
 {
 document.forms[0].getSet.value="no";
 if (document.forms[0].b_isIndexIsChildOrCustomized[0].checked || document.forms[0].b_isIndexIsChildOrCustomized[1].checked)
		{
			 x=document.getElementById("s_parentIndex");
			 var y=x.options[x.selectedIndex].value; 
 			 if(y!=0){
 				 document.forms[0].parentId.value="true";	
				 document.forms[0].submitvalue.value="fromParent";	  
				 document.forms[0].submit();  
 			 }
			
		}
 }
 
 function getdata()
{
    
     
}
</script>
</body>	
</html>