<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page import="app.*,java.util.*"%>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
		LogonForm form = null;
		if(request.isRequestedSessionIdValid())	{	
			form = (LogonForm)session.getAttribute("user");
		}
		if(form==null ||(!request.isRequestedSessionIdValid())){
			response.sendRedirect("userlogintemp.jsp");
		}
		String fr2=request.getParameter("from");
%>
<html>
<html:base/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 6.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<link rel="stylesheet" type="text/css" href="./StyleSheet.css"  title="Default" />   
<title>CA</title>
</head>

<body>
<jsp:useBean id="stockMerger" scope="session" class="app.StockMergerForm"/> 

<logic:equal value="1" scope="request" parameter="ref_flag">
<%stockMerger.reset(); %>
</logic:equal>

<logic:equal value="f" name="stockMerger" property="merge_info">
<b><font face="Arial" size="3" color="Green">
<bean:message key="StockMerger.MergeSuccess" /> 

</font></b>
</logic:equal>

<logic:equal value="Add" scope="session" parameter="Addstk">
<font face="Arial" size="2" color="Red"><b>
<bean:message key="StockMerger.selStockForAcqSide" /> 

</b></font>
</logic:equal>
	<!--Forms[0] Starts here-->
	
<html:form action="/stockMergerAction">
<html:errors/>

<html:hidden property="mergeButt" />   

<logic:equal value="target" scope="session" parameter="Close">
<font face="Arial" size="2" color="Red">
<bean:message key="StockMerger.ErrorNoLatestCloseVal" /> 

<BR>
<html:radio property="rad_butt" value="ct" onclick="return rad_check()" /><bean:message key="Admin.compute" /> 
<html:radio property="rad_butt" value="rt" onclick="return rad_check()"/><bean:message key="StockMerger.ReCalculate" />
</font>
</logic:equal>

<logic:equal value="dest" scope="session" parameter="Close">
<font face="Arial" size="2" color="Red">
<bean:message key="StockMerger.ErrorAffectedIndices" /> 

<BR>
<html:radio property="rad_butt" value="cd" onclick="return rad_check()" /><bean:message key="Admin.compute" /> 
<html:radio property="rad_butt" value="rd" onclick="return rad_check()" /><bean:message key="StockMerger.ReCalculate" />
</font>
</logic:equal>

<logic:equal value="1" name="stockMerger" property="temp_type">
<b><font face="Arial" size="2" color="Green">
<bean:message key="StockMerger.OutIndMerge" /> 

</font></b>
</logic:equal>

<logic:equal value="2" name="stockMerger" property="temp_type">
<b><font face="Arial" size="2" color="Green">
<bean:message key="StockMerger.IndOutMerge" /> 
Index to Outside Merging !
</font></b>
</logic:equal>

<logic:equal value="3" name="stockMerger" property="temp_type">
<b><font face="Arial" size="2" color="Green">
<bean:message key="StockMerger.IndIndMerge" /> 

</font></b>
</logic:equal>

<logic:equal value="4" name="stockMerger" property="temp_type">
<b><font face="Arial" size="2" color="Green">
<bean:message key="StockMerger.OutOutMerge" /> 

</font></b>
</logic:equal>


<logic:equal value="d" name="stockMerger" property="merge_info">
<b><font face="Arial" size="2" color="Green">
<bean:message key="StockMerger.DeactMergedCompany" /> 

</font></b>
<BR>
<b><font face="Arial" size="2" color="Red">
<bean:message key="StockMerger.ContinueTrans" /> 

<BR>
<html:radio property="rad_trans" value="ct" onclick="return rad_tran()" /><bean:message key="corporate.Continue" />
<html:radio property="rad_trans" value="s" onclick="return rad_tran()" /><bean:message key="CorporateAction.Stop" />
</font></b>
</logic:equal>

<logic:equal value="st" name="stockMerger" property="merge_info">
<b><font face="Arial" size="2" color="Green">
<bean:message key="StockMerger.TransComplete" /> 

</font></b></logic:equal>

<logic:equal value="s" name="stockMerger" property="merge_info">
<b><font face="Arial" size="2" color="Green">
<bean:message key="StockMerger.ShareIssueOnAcqComp" />

</font></b></logic:equal>

<logic:equal value="de" name="stockMerger" property="merge_info">
<b><font face="Arial" size="2" color="Green">
<bean:message key="StockMerger.CompanyDeletedFromInd" />

</font></b></logic:equal>

<logic:equal value="a" name="stockMerger" property="merge_info">
<b><font face="Arial" size="2" color="Green">
<bean:message key="StockMerger.ReCalculate" />

</font></b>
<BR>
<b><font face="Arial" size="2" color="Red">
<bean:message key="StockMerger.ContinueTrans" />

<BR>
<html:radio property="rad_trans" value="cd" onclick="return rad_tran()" /><bean:message key="corporate.Continue" />
<html:radio property="rad_trans" value="s" onclick="return rad_tran()" /><bean:message key="CorporateAction.Stop" />
</font></b>
</logic:equal>

<logic:equal value="c" name="stockMerger" property="merge_info">
<b><font face="Arial" size="2" color="Green">
<bean:message key="StockMerger.AffectedIndComputed" />

</font></b>
</logic:equal>

<logic:equal value="r" name="stockMerger" property="merge_info">
<b><font face="Arial" size="2" color="Green">
<bean:message key="StockMerger.AffectedIndReCalculated" />
</font></b>
</logic:equal>

 <table border="0" width="100%" cellspacing="0" cellpadding="0" >
 <tr>
 <td width="50%" align="right">
 <font face="Arial" size="3"><b><bean:message key="corporate.stkMergerhead" />
 </b></font></td>
 <td />
 </tr>
 
 <tr>
<!----------				Source Company------------>   
 <td width="50%" valign="top" colspan="2">
 <br>
 <table width="100%" class="sample">
        <tr><td width="100%">
  <table border="0" width="100%" cellspacing="0" cellpadding="2" bgcolor="#CCCCCC" >
	  <tr>
	  <td width="100%" align="center" colspan="4"><font face="Arial" size="2"><b>
	  <bean:message key="corporate.Stkmerge1" />
	  </b></font>
	  </td>
	  </tr>	
	    
	<tr>
	<td width="100%" height="10" />
	</tr>
		
	   <tr>
    <td width="15%"  ><font face="Arial" size="1"><b>
	<bean:message key="sysConfigForm.stockExId" />
	</b></font></td>
	<td width="48%"  colspan="2"> 
	<logic:equal value="Add" scope="session" parameter="Addstk">   
	    <html:select size="1" property="exc1" onchange="return excTest1()" disabled="true">
	   	<html:optionsCollection name="stockMerger" property="exc1Collection" />    
	    </html:select>
    </logic:equal>
    <logic:notEqual value="Add" scope="session" parameter="Addstk">
        <html:select size="1" property="exc1" onchange="return excTest1()">
	   	<html:optionsCollection name="stockMerger" property="exc1Collection" />    
	    </html:select>    
    </logic:notEqual>
	 </td>
    <td width="37%" >
      <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
      </b></font></td>
     </tr> 
     
      <tr>           
     <td width="15%"   align="center" valign="top">
	  <p align="left"><font size="1" face="Arial"><b>
 	<bean:message key="corporate.stock" />
	  </b></font></td>
	<td width="48%" colspan="2"  align="center" valign="top">
    <p align="left">
   	<logic:equal value="Add" scope="session" parameter="Addstk">   
	    <html:select size="1" property="stock1" onchange="return stkTest1()" disabled="true">  
	    <html:optionsCollection name="stockMerger" property="stock1Collection" />        	            			
	    </html:select>
    </logic:equal>
    <logic:notEqual value="Add" scope="session" parameter="Addstk">
        <html:select size="1" property="stock1" onchange="return stkTest1()">  
	    <html:optionsCollection name="stockMerger" property="stock1Collection" />        	            			
	    </html:select>    
    </logic:notEqual>
     </td>
     <td width="37%" colspan="2"  align="center" valign="top">
     &nbsp;</td>
     </tr>      
     </table>
        <table border="0" width="100%" cellspacing="0" cellpadding="2" bgcolor="#CCCCCC" >  
     
			     <!-- Stock Details Of Source --->			
      <tr> 
    <td width="100%" align="center" colspan="4"><b><font face="Arial" size="2">		
	  <bean:message key="corporate.Stkmerge4" />
	  </font></b></td>
	</tr>     	
				
	<tr>
  <td width="18%" ><font size="1" face="Arial">
   <bean:message key="corporate.Excode" /></font></td>
  <td width="48%">
  <html:text property="exch1" size="15" disabled="true" />
	<font size="1" face="Arial">
     <bean:message key="corporate.Stkname" /></font></td>
  <td width="24%" align="left">
  <html:text property="name1" size="15"  disabled="true" /></td>
  <td width="10%"/> 
    </tr>	
    
    <tr>
  <td width="15%" ><font size="1" face="Arial">
   <bean:message key="defineIndex16" /></font></td>
  <td width="48%">
  <html:text property="cur1" size="15" disabled="true" />
	<font size="1" face="Arial">
     <bean:message key="corporate.Iwf" /></font></td>
  <td width="27%" align="left">
   <html:text property="iwfstk1" size="15"  disabled="true" /></td>
   <td width="10%"/> 
    </tr>		
    
    <tr>
  <td width="15%" ><font size="1" face="Arial">
	 <bean:message key="corporate.Comp" /></font></td>
  <td width="48%" >
  <html:text property="com1" size="15" disabled="true" />
	<font size="1" face="Arial">
   <bean:message key="corporate.NoShares" /></font></td>
  <td width="27%" align="left">
  <html:text property="tis1" size="15" disabled="true"/></td>
  <td width="10%"/> 
	</tr>					
	
	<tr>
  <td width="15%" ><font size="1" face="Arial">
 	<bean:message key="corporate.AcPrice" /></font></td>
  <td width="48%" >
  <html:text property="close1" size="15" disabled="true" />
	<font size="1" face="Arial">
 <bean:message key="corporate.Faceval" /></font></td>
  <td width="27%" align="left">
  <html:text property="face1" size="15"  disabled="true" /></td>
  <td width="10%"/> 
	</tr>
	
	<tr>
  <td width="15%" ><font size="1" face="Arial">
 	<bean:message key="sysConfigForm.countryId" /></font></td>
  <td width="48%" >
  <html:text property="coun1" size="15" disabled="true" />
	<font size="1" face="Arial">
 <bean:message key="corporate.Ratecode" /></font></td>
  <td width="27%" align="left">
 <html:text property="rate1" size="15" disabled="true" /></td>	
 <td width="10%"/> 
	</tr>
	
	<tr>
	<td width="50%" colspan="2" ><font size="1" face="Arial">
	<html:radio property="nature1" value="g">
	<bean:message key="defineIndex27" /></html:radio> 
	<html:radio property="nature1" value="v">
	<bean:message key="defineIndex28" /></html:radio>
	</font></td>
	<td width="50%" colspan="2"><font size="1" face="Arial">
	</font></td>
	</tr>
	
					<!--  Corporate Action parameter--->
	<tr>
    <td width="100%" align="center" colspan="4"><b><font face="Arial" size="2">		
	<bean:message key="corporate.Corpparameter" />
	  </font></b></td>
	</tr>  
	
	<tr>           
     <td width="15%" valign="top"><font size="1" face="Arial"><bean:message key="corporate.ForShares" /></font></td>
	<td width="50%" valign="top"><html:text property="share1" size="3" />
	<html:text property="share2" size="3" />/
	<html:text property="no_share" size="10" />   
	</td>
     <td width="35%" valign="top"> 
   	 <p align="left">
   	 </p>
     </td>
     </tr>      
     
     <tr>           
     <td width="15%" valign="top">
     <font size="1" face="Arial">
     <bean:message key="corporate.Apply" />
   	 </font>
     </td>
	<td width="48%" valign="top">
    <html:text property="apply_date" size="15" readonly="true" />    
	</td>
    <td width="37%" valign="top">
    &nbsp;</td>
    </tr> 
     
	<tr>
	  <td width="15%" ></td>
	  <td width="48%" align="left">
		<%if(stockMerger.getStock3()==null){ %>
	  <html:submit value="Apply" onclick="return test()" />
		<%}else{ %>
		<html:submit value="Apply" onclick="return test()" disabled="true" />
		<%}%>
	  </td>
	  <td width="37%" ></td>	
	  </tr>		 
     
        
     					
					<!--  Affected Indices Of Source-->				
    <tr>
    <td width="100%" align="center" colspan="4"><b><font face="Arial" size="2">		
	 <bean:message key="corporate.Stkmerge3" />
	  </font></b></td>
	</tr>     								     
     
     <tr>
  <td width="15%" ><font size="1" face="Arial">
  <bean:message key="corporate.stkAffect" /></font></td>
  <td width="48%" >
  <logic:equal value="1" name="stockMerger" property="commit_butt">
	  <html:select size="1" property="affectedIndex1" onchange="return afftest1()">
	  <html:optionsCollection name="stockMerger" property="aff1Collection" />    
	  </html:select>
  </logic:equal>
  <logic:notEqual value="1" name="stockMerger" property="commit_butt">
	  <logic:equal value="2" name="stockMerger" property="commit_butt">
		  <html:select size="1" property="affectedIndex1" onchange="return afftest1()">
		  <html:optionsCollection name="stockMerger" property="aff1Collection" />    
		  </html:select>
	  </logic:equal>
	  <logic:notEqual value="2" name="stockMerger" property="commit_butt">
		  <html:select size="1" property="affectedIndex1" onchange="return afftest1()" disabled="true">
		  <html:optionsCollection name="stockMerger" property="aff1Collection" />    
		  </html:select>  
	  </logic:notEqual>  	  
  </logic:notEqual>
  </td>
  <td width="37%" >
	</td>	
	<td />
	</tr>
	
	 <tr>
  <td width="15%" ><font size="1" face="Arial">
  <bean:message key="corporate.Lastval" /></font></td>
  <td width="48%" >
  <html:text property="indexval1" size="15" disabled="true" /></td>
  <td width="37%" >
	</td>
	<td />	
	</tr>
	
     <tr>
  <td width="15%" ><font size="1" face="Arial">
  <bean:message key="corporate.oldtmcv" /></font></td>
  <td width="48%" >
  <html:text property="tmcv1" size="15" disabled="true" />
	<font size="1" face="Arial">
 <bean:message key="corporate.newtmcv" /></font></td>
  <td width="37%" >
 <html:text property="newtmcv1" size="15" disabled="true" /></td>	
 	<td />
	</tr>
	
	 <tr>
  <td width="15%" ><font size="1" face="Arial">
   <bean:message key="corporate.olddiv" /></font></td>
  <td width="48%" >
  <html:text property="divisor1" size="15" disabled="true" />
	<font size="1" face="Arial">
 <bean:message key="corporate.newdiv" /></font></td>
  <td width="37%" >
 <html:text property="newdivisor1" size="15" disabled="true" /></td>	
 	<td />
	</tr>	
	</table>	
	</td></tr></table>
  </td>
<!----------				Destination Company------------>  
 <td width="50%" valign="top" colspan="2">
  <br>
  <table width="100%" class="sample">
        <tr><td width="100%">
	<table border="0" width="100%" cellspacing="0" cellpadding="2" bgcolor="#BFBFBF">
	<tr>   
	<td width="100%" align="center" colspan="4">
	  <font face="Arial" size="2"><b>
	  <bean:message key="corporate.Stkmerge2" /></b></font>
	  </td>
	  </tr>	  
	
	<tr>
	<td width="100%" height="10" />
	</tr> 
    
    <tr>
    <td width="15%"  ><font face="Arial" size="1"><b>
	<bean:message key="sysConfigForm.stockExId" />
	</b></font></td>
	<td width="48%" colspan="2">    
    <html:select size="1" property="exc2" onchange="return excTest2()">
    <html:optionsCollection name="stockMerger" property="exc2Collection" />    
    </html:select>
	 </td>
    <td width="37%" >
      <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
      </b></font></td>
     </tr> 
     
	<tr>           
     <td width="15%"   align="center" valign="top">
	  <p align="left"><font size="1" face="Arial"><b>
 	<bean:message key="corporate.stock" />
	  </b></font></td>
	<td width="48%" colspan="2"  align="center" valign="top">
    <p align="left"><html:select size="1" property="stock2" onchange="return stkTest2()">              		
    <html:optionsCollection name="stockMerger" property="stock2Collection" />        	
    </html:select>
     </td>
     <td width="37%" colspan="2"   align="center" valign="top">
     &nbsp;</td>
     </tr> 
	</table>
	
	<table border="0" width="100%" cellspacing="0" cellpadding="2" bgcolor="#BFBFBF">
	<!-- Stock Details Of Destination --->	
     
    <tr>
    <td width="100%" align="center" colspan="4"><b><font face="Arial" size="2">		
	  <bean:message key="corporate.Stkmerge4" />
	  </font></b></td>
	</tr>     	
	
	<tr>
  <td width="15%" ><font size="1" face="Arial">
   <bean:message key="corporate.Excode" /></font></td>
  <td width="48%">
  <html:text property="exch2" size="15" disabled="true" />
	<font size="1" face="Arial">
     <bean:message key="corporate.Stkname" /></font></td>
  <td width="37%">
  <html:text property="name2" size="15"  disabled="true" /></td>
  <td /> 
    </tr>	
			
 <tr>
  <td width="15%" ><font size="1" face="Arial">
   <bean:message key="defineIndex16" /></font></td>
  <td width="48%">
  <html:text property="cur2" size="15" disabled="true" />
	<font size="1" face="Arial">
     <bean:message key="corporate.Iwf" /></font></td>
  <td width="37%">
   <html:text property="iwfstk2" size="15"  disabled="true" /></td>
   <td /> 
    </tr>	
    
    <tr>
  <td width="15%" ><font size="1" face="Arial">
	 <bean:message key="corporate.Comp" /></font></td>
  <td width="50%" >
  <html:text property="com2" size="15" disabled="true" />
	<font size="1" face="Arial">
   <bean:message key="corporate.NoShares" /></font></td>
  <td width="35%" >
  <html:text property="tis2" size="15" disabled="true" /></td>
  <td /> 
	</tr>
	
	<tr>
  <td width="15%" ><font size="1" face="Arial">
 	<bean:message key="corporate.AcPrice" /></font></td>
  <td width="48%" >
  <html:text property="close2" size="15" disabled="true" />
	<font size="1" face="Arial">
 <bean:message key="corporate.Faceval" /></font></td>
  <td width="37%" >
  <html:text property="face2" size="15"  disabled="true" /></td>
  <td /> 
	</tr>	
	
	<tr>
  <td width="15%" ><font size="1" face="Arial">
 	<bean:message key="sysConfigForm.countryId" /></font></td>
  <td width="48%" >
  <html:text property="coun2" size="15" disabled="true" />
	<font size="1" face="Arial">
 <bean:message key="corporate.Ratecode" /></font></td>
  <td width="37%" >
 <html:text property="rate2" size="15" disabled="true" /></td>	
 <td /> 
	</tr>	
	
	<tr>
	<td width="50%" colspan="2" ><font size="1" face="Arial">
	<html:radio property="nature2" value="g">
	<bean:message key="defineIndex27" /></html:radio>
		<html:radio property="nature2" value="v">
	<bean:message key="defineIndex28" /></html:radio>	
	</font></td>
	<td width="50%" colspan="2" ></td>
	</tr>

			<!--  Corporate Action Effect--->
	 <tr>
    <td width="100%" align="center" colspan="4"><b><font face="Arial" size="2">		
	<bean:message key="corporate.stkHeading2" />
	  </font></b></td>
	</tr>  

 <tr>           
     <td width="15%" valign="top"><font size="1" face="Arial"><bean:message key="corporate.Shareaddred" /></font></td>
	<td width="48%" valign="top"><html:text property="adjust" size="15" disabled="true" />
	</td>
     <td width="37%" valign="top">
     &nbsp;</td>
     </tr>    

 <tr>           
     <td width="15%" valign="top"><font size="1" face="Arial"><bean:message key="corporate.NewShares" /></font></td>
	<td width="48%" valign="top" align="left">
     <html:text property="newTIS" size="15" disabled="true" />&nbsp;
	</td>
     <td width="37%" valign="top">
     &nbsp;</td>
     </tr>    
 <tr>
  <td width="15%" ></td>
  <td width="48%" >
  <logic:equal value="1" name="stockMerger" property="commit_butt">
  <html:submit value="Commit" onclick="return comtest()"/>
  </logic:equal>
  <logic:notEqual value="1" name="stockMerger" property="commit_butt">
  <html:submit value="Commit" onclick="return comtest()" disabled="true"/>
  </logic:notEqual>&nbsp;
  <%if(stockMerger.getStock3()!=null){ %>
  <html:submit value="Add"  onclick="return Addtest()"  />
  <%} %>  
  </td>
  <td width="37%" align="left" >
  </td>	
	</tr>
     
       			
    			<!---Affected Indices Of Destination--->    			
	      <tr>
    <td width="100%" align="center" colspan="4"><b><font face="Arial" size="2">		
	 <bean:message key="corporate.Stkmerge3" />
	  </font></b></td>
	</tr>      
	

	<tr>
  <td width="15%" ><font size="1" face="Arial">
  <bean:message key="corporate.stkAffect" /></font></td>
  <td width="48%" >
  <logic:equal value="1" name="stockMerger" property="commit_butt">
	  <html:select size="1" property="affectedIndex2" onchange="return afftest2()">
	  <html:optionsCollection name="stockMerger" property="aff2Collection" />    
	  </html:select>
  </logic:equal>
  <logic:notEqual value="1" name="stockMerger" property="commit_butt">
	  <logic:equal value="2" name="stockMerger" property="commit_butt">
	  	  <html:select size="1" property="affectedIndex2" onchange="return afftest2()">
		  <html:optionsCollection name="stockMerger" property="aff2Collection" />    
		  </html:select>	  
	  </logic:equal>
	  <logic:notEqual value="2" name="stockMerger" property="commit_butt">
		  <html:select size="1" property="affectedIndex2" onchange="return afftest2()" disabled="true">
		  <html:optionsCollection name="stockMerger" property="aff2Collection" />    
		  </html:select>
	  </logic:notEqual>  	  
  </logic:notEqual>
  </td>
  <td width="37%" >
	</td>	
	<td />
	</tr>
	
	<tr>
  <td width="15%" ><font size="1" face="Arial">
  <bean:message key="corporate.Lastval" /></font></td>
  <td width="48%" >
  <html:text property="indexval2" size="15" disabled="true" /></td>
  <td width="37%" >
	</td>	
	<td />
	</tr>
	
	<tr>
  <td width="15%" ><font size="1" face="Arial">
  <bean:message key="corporate.oldtmcv" /></font></td>
  <td width="48%" >
  <html:text property="tmcv2" size="15" disabled="true" />
	<font size="1" face="Arial">
 <bean:message key="corporate.newtmcv" /></font></td>
  <td width="37%" >
 <html:text property="newtmcv2" size="15" disabled="true" /></td>	
 	<td />
	</tr>
	
	<tr>
  <td width="15%" ><font size="1" face="Arial">
   <bean:message key="corporate.olddiv" /></font></td>
  <td width="48%" >
  <html:text property="divisor2" size="15" disabled="true" />
	<font size="1" face="Arial">
 <bean:message key="corporate.newdiv" /></font></td>
  <td width="37%" >
 <html:text property="newdivisor2" size="15" disabled="true" /></td>	
 	<td />
	</tr>	
	 </table>
	 </td></tr></table>

<%
	  //for inactive stock display
	  ArrayList a1=stockMerger.getS1_arr();
	  if(!(a1.isEmpty()))
	  {
		  Object obj[]=a1.toArray();
		  int val=0;
  		  int len=obj.length;
		  if(len==1)
		  {%>
			<input type="hidden" name="obj_val"  value="<%=obj[0].toString()%>"  />			  
			<script>
	 	    document.forms[0].stock1.options[document.forms[0].obj_val.value].style.color="Red";
			</script>
		  <%}else{		  		  
			  for(int i=0;i<obj.length;i++){%>
		 	  <input type="hidden" name="obj_val"  value="<%=obj[i].toString() %>"  />
			  <%}%>
			  <script>
		 	  var len=document.forms[0].obj_val.length;
		 	  for(var i=0;i<len;i++)
			 	  document.forms[0].stock1.options[document.forms[0].obj_val[i].value].style.color="Red";
	 	      </script> 	  	 
		  <%}
	  }
	  ArrayList a2=stockMerger.getS2_arr();
	  if(!(a2.isEmpty()))
	  {
		  Object obj[]=a2.toArray();
		  int val=0;
		  int len=obj.length;
		  if(len==1)
		  {%>
			<input type="hidden" name="obj_val1"  value="<%=obj[0].toString()%>"  />			  
			<script>
	 	    document.forms[0].stock2.options[document.forms[0].obj_val1.value].style.color="Red";
			</script>
		  <%}else{		  
			  for(int i=0;i<obj.length;i++){%>
		 	  <input type="hidden" name="obj_val1"  value="<%=obj[i].toString() %>"  />
			  <%}%>			  
			  <script>
		 	  var len=document.forms[0].obj_val1.length;
		 	  for(var i=0;i<len;i++)
			 	  document.forms[0].stock2.options[document.forms[0].obj_val1[i].value].style.color="Red";
	 	      </script> 	  	 
	      <%}
	}%>
</html:form>	


<logic:equal value="1" name="stockMerger" property="temp_type">
<script language="javascript">
setTimeout('fun1()',1500);
</script>
</logic:equal>

<logic:equal value="2" name="stockMerger" property="temp_type">
<script language="javascript">
setTimeout('fun2()',1500);
</script>
</logic:equal>

<logic:equal value="3" name="stockMerger" property="temp_type">
<script language="javascript">
setTimeout('fun2()',1500);
</script>
</logic:equal>

<logic:equal value="4" name="stockMerger" property="temp_type">
<script language="javascript">
setTimeout('fun1()',1500);
</script>
</logic:equal>

<logic:equal value="de" name="stockMerger" property="merge_info">
<script language="javascript">
setTimeout('fun1()',2000);
</script>
</logic:equal>

<logic:equal value="c" name="stockMerger" property="merge_info">
	<logic:equal value="1" name="stockMerger" property="merge_type">
	<script language="javascript">
	setTimeout('fun3()',3000);
	</script>
	</logic:equal>
	
	<logic:notEqual value="1" name="stockMerger" property="merge_type">
		<logic:equal value="ct" name="stockMerger" property="rad_butt">
			<%if(stockMerger.getStock3()==null){ %>
			<script language="javascript">
			setTimeout('fun4()',3000);
			</script>		
			<%}else{%>			
			<script language="javascript">
			setTimeout('fun5()',3000);
			</script>					
			<%}%>
		</logic:equal>
		<logic:equal value="cd" name="stockMerger" property="rad_butt">
		<script language="javascript">
		setTimeout('fun3()',3000);
		</script>	
		</logic:equal>
	</logic:notEqual>
</logic:equal>

<logic:equal value="r" name="stockMerger" property="merge_info">
	<logic:equal value="1" name="stockMerger" property="merge_type">
	<script language="javascript">
	setTimeout('fun3()',3000);
	</script>	
	</logic:equal>
	
	<logic:notEqual value="1" name="stockMerger" property="merge_type">
		<logic:equal value="rt" name="stockMerger" property="rad_butt">
		   <%if(stockMerger.getStock3()==null){ %>
			<script language="javascript">
			setTimeout('fun4()',3000);
			</script>	
			<%}else{%>
			<script language="javascript">
			setTimeout('fun5()',3000);
			</script>	
			<%} %>
		</logic:equal>
		<logic:equal value="rd" name="stockMerger" property="rad_butt" >
		<script language="javascript">
		setTimeout('fun3()',3000);
		</script>	
		</logic:equal>
	</logic:notEqual>
</logic:equal>


<logic:equal value="s" name="stockMerger" property="merge_info">
<jsp:setProperty name="stockMerger" property="merge_info" value="st" />
<script language="javascript">
setTimeout('fun6()',2000);
</script>	
</logic:equal>


</body>
<script language="javascript">
function fun1()
{
	document.forms[0].mergeButt.value="Apply1";
	document.forms[0].submit();
}
function fun2()
{
	document.forms[0].mergeButt.value="Apply3";
	document.forms[0].submit();
}
function fun3()
{
	document.forms[0].mergeButt.value="Apply2";
	document.forms[0].submit();
}
function fun4()
{
	document.forms[0].mergeButt.value="Apply5";
	document.forms[0].submit();
}
function fun5()
{
	document.forms[0].mergeButt.value="Apply4";
	document.forms[0].submit();
}
function fun6()
{
	document.forms[0].mergeButt.value="Comp";
	document.forms[0].submit();
}
function excTest1()
{
	document.forms[0].mergeButt.value="Exc1";
	document.forms[0].submit();
}
function excTest2()
{
	document.forms[0].mergeButt.value="Exc2";
	document.forms[0].submit();
}
function stkTest1()
{
	document.forms[0].mergeButt.value="Stock1";
	document.forms[0].submit();
}
function stkTest2()
{
	document.forms[0].mergeButt.value="Stock2";
	document.forms[0].submit();
}
function test()
{
	document.forms[0].mergeButt.value="Apply";
	document.forms[0].submit();
}
function rad_check()
{
	document.forms[0].mergeButt.value="Radio";
	document.forms[0].submit();
}
function comtest()
{
	document.forms[0].mergeButt.value="Commit";
	document.forms[0].submit();
}
function rad_tran()
{
	document.forms[0].mergeButt.value="Trans";
	document.forms[0].submit();
}
function afftest1()
{
	document.forms[0].mergeButt.value="Affect1";
	document.forms[0].submit();
}
function afftest2()
{
	document.forms[0].mergeButt.value="Affect2";
	document.forms[0].submit();
}
function Addtest()
{
	document.forms[0].mergeButt.value="Add";
	document.forms[0].submit();
}
</script>
</html>