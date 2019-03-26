
<%@page import="com.harrier.initializeation.ConnectInit"%><%@ taglib uri="/tags/struts-html" prefix="html" %>
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
	String fr2=null;
	String locale=null;
//	AcessControl asc= null;
	
	if(request.isRequestedSessionIdValid()){
		log.info("inside sesssion != null"+session);
		form = (LogonForm)session.getAttribute("user");
		fr2=request.getParameter("from");
	 	locale=session.getAttribute("locale").toString();
		//asc=new AcessControl();
		AcessControl asc=ConnectInit.getAcessControl();
		asc.setLocale(locale);
	}	
	
	if(form==null ||(!request.isRequestedSessionIdValid())){
		log.info("inside sesssion == null");
		response.sendRedirect("userlogintemp.jsp");
	}
%>


<html>
<html:base/>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 6.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<link rel="stylesheet" type="text/css" href="./StyleSheet.css"  title="Default" />   
<title>CA</title>

<meta name="Microsoft Theme" content="none"> 
	<script language="javascript" src="./codethatcalendarstd.js"></script>
	<script language="javascript" src="box_ex.js"></script>
	<script language="javascript">
	var c2 = new CodeThatCalendar(caldef2);
	</script> 
	
</head>
<body>

<jsp:useBean id="corporate" scope="session" class="app.Corporate"/> 

<logic:equal value="New" scope="request" parameter="flag">
<jsp:setProperty name="corporate" property="check_type" value="New" />
</logic:equal>
<logic:equal value="Exist" scope="request" parameter="flag" >
<jsp:setProperty name="corporate" property="check_type" value="Exist" />
</logic:equal>

<logic:equal value="1" scope="request" parameter="ref_flag">
<%corporate.reset_stkevent();%>
<jsp:setProperty name="corporate" property="corpid" value='<%=request.getParameter("corp_name")%>' />
</logic:equal>

<logic:equal value="1" scope="request" parameter="check_flg">
<b><font face="Arial" color="red" size="3">
<bean:message key="corporate.Stkerror1" />
</font></b>
</logic:equal>

<logic:equal value="1" name="corporate" property="succ_butt">
<font face="Arial" size="3" color="green"><b>
<bean:message key="corporate.Success"/>
</b></font>
<jsp:setProperty name="corporate" property="succ_butt" value="" />
</logic:equal>
	<!--Forms[0] Starts here-->
	
<html:form action="/nCorp_Action">
<html:errors/>
 <table width="100%" class="sample">
        <tr><td width="100%">
 <table border="0" width="100%" cellspacing="0" cellpadding="0" >

 <logic:equal value="2" scope="request" parameter="check_flg">
<b><font face="Arial" color="red" size="2">
<bean:message key="corporate.Stkerror2" />
</font></b><br>								
<html:radio property="ind_comp" value="c" onclick="return indsub()" /><bean:message key="corporate.Continue"/>
<html:radio property="ind_comp" value="s" onclick="return indsub()" /><bean:message key="CorporateAction.Stop"/>
</logic:equal>

 <logic:equal value="3" scope="request" parameter="check_flg">
	<b><font face="Arial" color="red" size="2">
<bean:message key="corporate.Stkerror3" />
	</font></b><br>								
	<html:radio property="ind_comp" value="c" onclick="return indsub()" /><bean:message key="corporate.Continue"/>
	<html:radio property="ind_comp" value="s" onclick="return indsub()" /><bean:message key="CorporateAction.Stop"/> 
 </logic:equal>

 <logic:equal value="4" scope="request" parameter="check_flg">
 <b><font face="Arial" color="red" size="2">
<bean:message key="corporate.Stkerror4" />
</font></b><br>								
<html:radio property="ind_comp" value="c" onclick="return indsub()" /><bean:message key="corporate.Continue"/>
<html:radio property="ind_comp" value="s" onclick="return indsub()" /><bean:message key="CorporateAction.Stop"/>		 
 </logic:equal> 
 
   <tr>
    <td width="30%"  colspan="2"><font size="2" face="Arial">
    <b><bean:message key="corporate.stkHeading" /></b></font></td>
    <td width="33%"  >
      &nbsp;</td>
    <td width="14%"  >
      &nbsp;</td>
    <td width="23%"  >&nbsp;</td>
  </tr>  
  
    <tr>
    <td width="17%"  height="30"><font face="Arial" size="1"><b>
	<bean:message key="sysConfigForm.stockExId" />
	</b></font></td>
	<td width="46%" height="30" colspan="2">    	
    <html:select size="1" property="exc" onchange="return exctest()" >
	<html:optionsCollection name="corporate" property="exc1Collection" />    
    </html:select>

	 </td>
    <td width="14%" >
      <p align="right"><font size="1" face="Arial"><b>&nbsp;&nbsp;&nbsp;
      </b></font></td>
    <td width="23%" >&nbsp;</td>
     </tr> 
     <tr>           
     <td width="15%"   align="center" valign="top">
	  <p align="left"><font size="1" face="Arial"><b>
 	<bean:message key="corporate.stock" />
	  </b></font></td>
	<td width="48%" colspan="2" height="30"  align="center" valign="top">
    <p align="left">
    <html:select size="1" property="s_stock" onchange="return stktest()">              			  
	<html:optionsCollection name="corporate" property="s_stkCollection"/>        
    </html:select>
     </td>
     <td width="37%" colspan="2"  height="30" align="center" valign="top">
     &nbsp;</td>
     </tr> 
     
     <tr>
    <td width="63%" colspan="3"  valign="top" >
      <table border="0" width="101%" cellspacing="0" cellpadding="2">
	 <tr>
	  <td width="100%" bgcolor="#84AADE" colspan="4" align="center"><b><font face="Arial" size="1">
	  <bean:message key="corporate.stkHeading1" />
	  </font></b></td>
	</tr>      
	<tr>
  <td width="28%" ><font size="1" face="Arial">
   <bean:message key="corporate.sedol" /></font></td>
  <td width="23%">
  <html:text property="sedol" size="15" disabled="true" /></td>
  <td width="24%" ><font size="1" face="Arial">
    <bean:message key="defineIndex18" /></font></td>
  <td width="26%">
  <html:text property="isin" size="15" disabled="true" /></td>
    </tr>	
	<tr>
  <td width="28%" ><font size="1" face="Arial">
   <bean:message key="corporate.Ric" /></font></td>
  <td width="23%" >
  <html:text property="ric" size="15" disabled="true" /></td>
  <td width="24%" ><font size="1" face="Arial">
  <bean:message key="corporate.Cusip" /></font></td>
  <td width="26%">
  <html:text property="cus" size="15" disabled="true" /></td>
</tr>
<tr>
  <td width="28%" ><font size="1" face="Arial">
  <bean:message key="corporate.Excode" /></font></td>
  <td width="23%" >
  <html:text property="exch" size="15" disabled="true" /></td>
  <td width="24%" ><font size="1" face="Arial">
   <bean:message key="corporate.Ticker" /></font></td>
  <td width="26%">
  <html:text property="tic" size="15" disabled="true" /></td>
</tr>
<tr>
  <td width="28%" ><font size="1" face="Arial">
   <bean:message key="defineIndex16" /></font></td>
  <td width="23%" >
  <html:text property="cur" size="15" disabled="true" /></td>
  <td width="24%"><font size="1" face="Arial">
  <bean:message key="corporate.Listdate" /></font></td>
  <td width="26%" >
  <html:text property="date" size="15"  disabled="true" /></td>
</tr>
<tr>
  <td width="28%" ><font size="1" face="Arial">
  <bean:message key="corporate.Stkname" /></font></td>
  <td width="23%" >
  <html:text property="name" size="15"  disabled="true" /></td>
  <td width="24%" ><font size="1" face="Arial">
   <bean:message key="corporate.Iwf" /></font></td>
  <td width="26%" >
  <html:text property="iwfstk" size="15"  disabled="true" /></td>
</tr>
<tr>
  <td width="28%" ><font size="1" face="Arial">
	 <bean:message key="corporate.Comp" /></font></td>
  <td width="23%" >
  <html:text property="com" size="15" disabled="true" /></td>
  <td width="24%"><font size="1" face="Arial">
   <bean:message key="corporate.NoShares" /></font></td>
  <td width="26%" >
  <html:text property="tis" size="15" disabled="true" /></td>
</tr>
<tr>
  <td width="28%" ><font size="1" face="Arial">
 <bean:message key="corporate.AcPrice" /></font></td>
  <td width="23%" >
  <html:text property="close" size="15" disabled="true" /></td>
  <td width="24%" ><font size="1" face="Arial">
 <bean:message key="sysConfigForm.countryId" /></font></td>
  <td width="26%" >
  <html:text property="coun" size="15" disabled="true" /></td>
</tr>
<tr>
  <td width="28%" ><font size="1" face="Arial">
  <bean:message key="corporate.Faceval" /></font></td>
  <td width="23%" >
  <html:text property="face" size="15"  disabled="true" /></td>
  <td width="24%" ><font size="1" face="Arial">
  <bean:message key="corporate.Ratecode" /></font></td>
  <td width="26%" >
  <html:text property="rate" size="15" disabled="true" /></td>
</tr>
<tr>
  <td width="28%" ><font size="1" face="Arial">
 <bean:message key="corporate.Subind" /></font></td>
  <td width="23%" >
  <html:text property="sub" size="15" disabled="true" /></td>
  <td width="24%" ><font size="1" face="Arial">
 <bean:message key="corporate.Adr" /></font></td>
  <td width="26%" >
  <html:text property="adr" size="15" disabled="true" /></td>
</tr>
<tr>
<td width="50%" colspan="2" ><font size="1" face="Arial">
<html:radio property="nature" value="<%=corporate.getNature() %>">
<bean:message key="defineIndex27" /></html:radio>
</font></td>
<td width="50%" colspan="2" ><font size="1" face="Arial">
<html:radio property="nature" value="<%=corporate.getNature() %>">
<bean:message key="defineIndex28" /></html:radio>
</font></td>
</tr>
  </table>
  </td>
 <td width="37%" colspan="2"  valign="top">
  <table border="0" width="100%" cellspacing="0" cellpadding="1">
 </td>    
  
   <td width="37%" colspan="2"  valign="top">
  <table border="0" width="100%" cellspacing="0" cellpadding="1">
	<tr>              
	<td width="100%" bgcolor="#84AADE" colspan="4" align="center"><b><font face="Arial" size="1">
	  <bean:message key="corporate.Indevhead2" /></font></b></td>              
  </tr>  
	<tr>
  <td width="40%"><font size="1" face="Arial">
  <bean:message key="corporate.Action" />
  </font></td>
  <td width="60%" align="center" >
   <p align="left"><font size="1" face="Arial">
   <html:select size="1" property="corpid" > 
	<html:optionsCollection name="corporate" property="corpCollection" />       
   </html:select>
	</font></td></tr>
	<tr>
  	<td width="40%"  ><font size="1" face="Arial">
  	<bean:message key="corporate.Amount" />
  	</font></td>
  	<td width="60%"  align="center">
    <html:text property="amt" size="15" style="float: left" /></td>
    </tr>
    
	<tr>
  	<td width="40%" ><font size="1" face="Arial">
  	 <bean:message key="corporate.NoShares" />
  	 </font></td>
  	<td width="60%"  align="center" >    
  	<html:text property="share" size="15" style="float: left"/></td>  	
  	</tr>
  	

  	<tr>
    <td width="40%" ><font size="1" face="Arial">
   	<bean:message key="corporate.Ratio" />
    </font></td>
    <td width="60%"  align="center" >
    <p align="left">
	 <html:text property="ratio1" size="2"  /> : 
	 <html:text property="ratio2" size="2"  />                  			
	 </p></td>                  			              	
	 </tr>  	
 
  
  <logic:notEqual value="Undo" property="button" name="corporate">    
  <tr>
   <td width="40%" ><font size="1" face="Arial">
     	<bean:message key="corporate.Apply" />
   </font></td>
  <td width="40%"  >
  <html:text property="apply_date" readonly="true" /><input onclick="c2.popup('apply_date');" tabIndex="6" type="button" value="..."/>  	
  </td>                  			              	
  </tr>
  </logic:notEqual>

  <tr>
  <td width="40%" >
  <html:submit onclick="return test()" ><bean:message key="CorporateDiary.Apply" /></html:submit> </td>    
  <logic:notEqual value="0" scope="request" parameter="check_flg">
   <td width="60%" >
    <html:submit onclick="return comtest()" disabled="true"><bean:message key="CorporateAction.Commit" />  </html:submit>                                              
   </td>
  </logic:notEqual>
  
  <logic:equal value="0" scope="request" parameter="check_flg">
	  <logic:equal value="1" name="corporate" property="commit_butt">
	    <td width="60%"><html:submit onclick="return comtest()"><bean:message key="CorporateAction.Commit" />  </html:submit></td>                                              
	  </logic:equal>
  	
  	  <logic:notEqual value="1" name="corporate" property="commit_butt">
  	    <td width="60%" ><html:submit onclick="return comtest()" disabled="true"><bean:message key="CorporateAction.Commit" />  </html:submit></td>                                              
  	  </logic:notEqual>	
  </logic:equal>
  </tr>  
  
  
	<tr>
  <td width="40%"  align="center"><a href="./CorporateDiary.jsp?ref_flag=1" onmouseover="window.status='';return true"><bean:message key="CorporateAction.Diary" /> </a></td>                  
  <td width="60%" ><a href="./NCorporateAction.jsp?flag=<%=corporate.getCheck_type()%>&corpid=<%=corporate.getCorpid()%>&ref_flag=1" onmouseover="window.status='';return true"  ><bean:message key="Admin.Cancel" /> </a></td>                  				  				  				
    </tr>  
  <tr>
  <td width="100%"  colspan="2">&nbsp;
    <table border="0" width="100%" cellspacing="0" cellpadding="0">
      <tr>
        <td width="100%" colspan="2" bgcolor="#84AADE">
          <p align="center"><font face="Arial" size="1">
          <bean:message key="corporate.stkHeading2" /></font></td>
      </tr>
  <tr>
    <td width="40%" ><font size="1" face="Arial">
     <bean:message key="corporate.Shareaddred" /></font></td>
    <td width="60%"><font size="1" face="Arial"><html:text property="adjust" size="15" disabled="true" /></font></td>
  </tr>
	<tr>
    <td width="40%" ><font size="1" face="Arial">
    <bean:message key="corporate.Newshares" /></font></td>
    <td width="60%" ><font size="1" face="Arial"><html:text property="newTIS" size="15" disabled="true" /></font></td>
  </tr>  
  <tr>
    <td width="40%"><font size="1" face="Arial">
   <bean:message key="corporate.Adjprice" /></font></td>
    <td width="60%" ><font size="1" face="Arial"><html:text property="newLTP" size="15" disabled="true" /></font></td>
  </tr>   
  <logic:equal value="split" property="corpid" name="corporate">
    <tr>
    <td width="40%"><font size="1" face="Arial">
     <bean:message key="corporate.Newfaceval" /></font></td>
    <td width="60%" ><font size="1" face="Arial">
    <html:text property="newFace" size="15" disabled="true" /></font></td>
  </tr>     		  
  </logic:equal>
  
  <logic:equal value="reversesplit" property="corpid" name="corporate">
      <tr>
    <td width="40%"><font size="1" face="Arial">
     <bean:message key="corporate.Newfaceval" /></font></td>
    <td width="60%" ><font size="1" face="Arial">
    <html:text property="newFace" size="15" disabled="true" /></font></td>
  </tr>     		    
  </logic:equal>
  
  </table>  
  </td> 
  </tr>
</table>      
 </td>       
 </table>  
 </td>
  <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
<TBODY>

  <tr>
 <TD align="center" width="111%" bgColor="#84AADE" colSpan=6><B><FONT face=Arial 
size=1><bean:message key="corporate.Stkevhead1" /></font></b></td>
  </tr>  
  <tr>
  <td width="17%" ><html:select size="1" property="s_affectedIndex" onchange="return afftest()">
  <html:optionsCollection name="corporate" property="affstkCollection" />              
  </html:select>
  
  <td width="18%" />
  <td width="11%" ><font size="1" face="Arial">&nbsp;
  <bean:message key="corporate.oldtmcv" /></font></td>
	<td width="13%" >&nbsp;<html:text property="tmcv" size="15" disabled="true" /></td>  
 <td width="10%" >
  <p align="right"><font size="1" face="Arial">
  <bean:message key="corporate.olddiv" /></font></td>
  <td width="18%" >&nbsp;&nbsp;<html:text property="divisor" size="15" disabled="true" /></td>	
  </tr>  

<tr>
<td width="17%" ><font size="1" face="Arial">
<bean:message key="corporate.Lastval" />
</font></td>
 <td width="18%" ><p align="left">&nbsp;<html:text property="indexval" size="15" disabled="true" /></p></td> 
  <td width="12%" ><font size="1" face="Arial">&nbsp;
 <bean:message key="corporate.newtmcv" /></font></td>
   <td width="18%" >&nbsp;<html:text property="newtmcv" size="15" disabled="true" /></td>
	 <td width="11%" ><p align="right"><font size="1" face="Arial">
	 <bean:message key="corporate.newdiv" /></font></p></td>
       <td width="23%" >&nbsp;&nbsp;<html:text property="newdivisor" size="15" disabled="true" /></td>
</tr>
 
  <tr>
    <td width="100%" colspan="6">
      <p align="right"></td>
  </tr>  
</TBODY> 
  </table>
  </table>
  
  <html:hidden property="flag" value="<%=corporate.getCheck_type() %>"></html:hidden>
  <html:hidden property="ncorp_button"/>  
  <html:hidden property="tmcv" value="<%=corporate.getTmcv() %>"/>
  <html:hidden property="divisor" value="<%=corporate.getDivisor() %>"/>
  <html:hidden property="indexval" value="<%=corporate.getIndexval() %>"/>
  <html:hidden property="newtmcv" value="<%=corporate.getNewtmcv() %>" />
  <html:hidden property="newdivisor" value="<%=corporate.getNewdivisor()%>" />  
  
  <logic:equal value="Exist" property="check_type" name="corporate">
   <script language="javascript">
  	document.forms[0].exc.disabled=true;
  	document.forms[0].s_stock.disabled=true;
  	document.forms[0].corpid.disabled=true;
  	document.forms[0].amt.disabled=true;
  	document.forms[0].share.disabled=true;
  	document.forms[0].ratio1.disabled=true;
  	document.forms[0].ratio2.disabled=true;   
   </script>
	<logic:notEqual value="Undo" name="corporate" property="button">
	<script>
	document.forms[0].apply_date.disabled=true;
	</script>
	</logic:notEqual>   
  </logic:equal>
  
  <html:hidden property="corpid" value="<%=corporate.getCorpid()%>" />
  <logic:equal value="Exist" property="check_type" name="corporate">
  <html:hidden property="s_stock" value="<%=corporate.getS_stock()%>"/>   	
  <html:hidden property="amt" value="<%=corporate.getAmt() %>" />
  <html:hidden property="share" value="<%=corporate.getShare() %>" />
  <html:hidden property="ratio1" value="<%=corporate.getRatio1()%>" />
  <html:hidden property="ratio2" value="<%=corporate.getRatio2() %>" />     
    </logic:equal> 
  </td></tr></table> 
  
<%
	  //for inactive stock display
	  ArrayList a1=corporate.getStk_status();
	  if(!(a1.isEmpty()))
	  {
		  Object obj[]=a1.toArray();
	   	  int len=obj.length;
		  if(len==1)
		  {%>
			<input type="hidden" name="obj_val"  value="<%=obj[0].toString()%>"  />			  
			<script>
	 	    document.forms[0].s_stock.options[document.forms[0].obj_val.value].style.color="Red";
			</script>
		  <%}else{		  		  
			  for(int i=0;i<obj.length;i++){%>
		 	  <input type="hidden" name="obj_val"  value="<%=obj[i].toString() %>"  />
			  <%}%>
			  <script>
		 	  var len=document.forms[0].obj_val.length;
		 	  for(var i=0;i<len;i++)
			 	  document.forms[0].s_stock.options[document.forms[0].obj_val[i].value].style.color="Red";
	 	      </script> 	  	 
			<%}
		}%>
  </html:form>
<script language="javascript">
   function test()
   {   
		var val=document.forms[0].flag.value;   		
		if(val=="New")
		{			
			document.forms[0].ncorp_button.value="NApply";
		}
		if(val=="Exist")
			document.forms[0].ncorp_button.value="Apply";		
  }
   function afftest()
   {
   		document.forms[0].ncorp_button.value="Affect";
   		document.forms[0].submit();
   }   
   function cancel()
   {
   	document.forms[0].reset();
   }
   function comtest()
   {
   	document.forms[0].ncorp_button.value="Commit";
   	document.forms[0].submit();
   }
   function exctest()
   {
      	document.forms[0].ncorp_button.value="Exc";
		document.forms[0].submit();
   }
   function stktest()
   {
	   	document.forms[0].ncorp_button.value="Stock";
	   	document.forms[0].submit();
   }
   function indsub()
   {  
	   	document.forms[0].ncorp_button.value="Radio";   	
	   	document.forms[0].submit();
   }
 </script>
</html>